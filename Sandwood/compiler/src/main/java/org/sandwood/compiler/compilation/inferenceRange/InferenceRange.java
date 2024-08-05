/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.inferenceRange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope.ScopeType;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.GetTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.PutTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ForTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.traces.Trace;
import org.sandwood.compiler.traces.TraceHandle;
import org.sandwood.compiler.traces.Traces;
import org.sandwood.compiler.traces.Traces.SampleTraceDesc;

/**
 * A class containing methods allowing dependency cycles to be detected and and recorded, and locations where multiple
 * invocations of the same sample task occur so that we can detect where parallelism is and is not possible.
 */
public abstract class InferenceRange {
    public static void getRanges(SampleTask<?, ?> sampleTask, CompilationContext compilationCtx) {

        // First off look for cycles and determine the set of scopes that will need to
        // be stepped.
        List<Cycle> cycles = findCycles(sampleTask, compilationCtx.traces);
        Set<ForTask> serialScopes = new HashSet<>();
        processCycles(cycles, serialScopes);
        compilationCtx.addSerialScopes(serialScopes, false);

        // Look for consuming random variable that are dependent on potentially different sample calls.
        serialScopes.clear();
        Map<RandomVariable<?, ?>, Set<TraceHandle>> toConsumingRV = compilationCtx.traces
                .getTracesRVToSampleTask(sampleTask);
        for(RandomVariable<?, ?> rv:toConsumingRV.keySet())
            multiSampleDependency(sampleTask, toConsumingRV.get(rv), serialScopes);
        // TODO modify this so that these scopes are only serial for specific sample tasks, not all sample tasks.
        compilationCtx.addSerialScopes(serialScopes, true);
    }

    /**
     * A method to determine if traces read from the sample task on more than one iteration of a loop. This method is
     * conservative, so may return false positives in the form of for loops that do not need to be executed as serial
     * code.
     * @param sampleTask 
     *
     * @param hs           The set of traces from a sample to a given task.
     * @param serialScopes The set of for loops that need to be executed as serial code to be added to. This may include
     *                     loops that could execute as parallel code.
     */
    private static void multiSampleDependency(SampleTask<?, ?> sampleTask, Set<TraceHandle> hs, Set<ForTask> serialScopes) {
        Set<IntVariable> serialIndexes = new HashSet<>();
        // Look for indexes that need to be set to execute in serial in the each trace.
        for(TraceHandle h:hs)
            samplesCombined(h, serialIndexes);

        // Split the traces into groups so that only traces that could be true at the same time are considered.
        Set<Set<TraceHandle>> groupedTraces = Traces.groupInputTraces(hs);
        for(Set<TraceHandle> ghs:groupedTraces) {
            List<TraceHandle> l = new ArrayList<>(ghs);
            int size = l.size();
            for(int i = 0; i < size - 1; i++) {
                TraceHandle t1 = l.get(i);
                for(int j = i + 1; j < size; j++) {
                    TraceHandle t2 = l.get(j);
                    samplesCombined(t1, t2, serialIndexes);
                }
            }
        }

        if(!serialIndexes.isEmpty()) {
            // Get all the variables used in the indexes, not including those used in the bounds of for loops.
            Set<Variable<?>> inputs = new HashSet<>();
            for(IntVariable index:serialIndexes) {
                inputs.addAll(index.collectInputVariables(DFType.FOR, DFType.PAR_FOR));
                inputs.add(index);
            }

            // Get all the for loops the sample task is embedded in.
            Set<ForTask> forScopes = getForScopes(sampleTask);

            // For each scope test if this scope needs to be run in serial.
            for(ForTask f:forScopes) {
                if(inputs.contains(f.getIndex()))
                    serialScopes.add(f);
            }
        }
    }

    /**
     * Iterator that returns the next array operation of interest in an iterator of a trace.
     */
    private static class ArrayTaskIterator implements Iterator<DataflowTaskArgDesc> {
        private final TraceHandle h;
        private final int size;
        private int pos = 0;
        private int currentDepth = 0;
        List<IntVariable> putIndexes = new ArrayList<>();
        IntVariable lastIndex = null;

        ArrayTaskIterator(TraceHandle h) {
            this.h = h;
            size = h.size();
            getNextPos();
        }

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public DataflowTaskArgDesc next() {
            DataflowTaskArgDesc d = h.get(pos);

            getNextPos();
            return d;
        }

        private void getNextPos() {
            skipMachedArrayOps();

            while(++pos < size) {
                // skipMachedArrayOps();
                DataflowTaskArgDesc d = h.get(pos);
                DFType type = d.task.getType();
                if(type == DFType.PUT && d.argPos == 2) {
                    if(currentDepth == putIndexes.size())
                        putIndexes.add(((PutTask<?>) d.task).index);
                    currentDepth++;
                    return;
                }

                // Gets where the trace travels via the index argument are not of interest as they are not recovering
                // the sample value from the array. This means they do not give it a means to interact with samples from
                // the same task generated on different iterations of the loop.
                if(type == DFType.GET && d.argPos == 0) {
                    if(--currentDepth >= 0)
                        lastIndex = putIndexes.get(currentDepth);
                    return;
                }

                if(type == DFType.REDUCE_INPUT && d.argPos == 3) {
                    if(--currentDepth >= 0)
                        lastIndex = putIndexes.get(currentDepth);
                    return;
                }

                skipMachedArrayOps();
            }
        }

        /**
         * Method to skip the indexing over matched array accesses such as code that goes via the array a so that the 2
         * traces generated here will not be considered as handling different samples.
         * <P>
         * s = sample(); a[i] = s; f(s, a[i]);
         */
        private void skipMachedArrayOps() {
            int i = pos + 1;
            if(i == size)
                return;

            int currentDepth = this.currentDepth;
            int initialDepth = currentDepth;
            DataflowTaskArgDesc d = h.get(i++);
            DFType type = d.task.getType();
            // If the current value is being placed into an array start looking to see if the value is recovered from
            // the same index of the array. If it is it is trivially still in the same iteration.
            while(type == DFType.PUT && d.argPos == 2) {
                // Record the number of puts in a sequence [index .. i)
                while(type == DFType.PUT && d.argPos == 2) {
                    if(currentDepth == putIndexes.size())
                        putIndexes.add(((PutTask<?>) d.task).index);
                    currentDepth++;

                    // If there is another task get it.
                    if(i < size) {
                        d = h.get(i++);
                        type = d.task.getType();
                    } else
                        return;
                }
                // Look for corresponding gets
                while(type == DFType.GET && d.argPos == 0) {
                    if(--currentDepth == initialDepth)
                        break;

                    // If there is another task get it.
                    if(i < size) {
                        d = h.get(i++);
                        type = d.task.getType();
                    } else
                        return;
                }

                // If not matching gets for all the puts are found check return.
                if(currentDepth != initialDepth)
                    return;

                // Check that the gets and puts have matching indexes
                int start = pos + 1;
                int end = i - 1;
                PutTask<?> pt;
                GetTask<?> gt;

                while(start < end) {
                    pt = (PutTask<?>) h.get(start++).task;
                    gt = (GetTask<?>) h.get(end--).task;
                    if(!pt.index.equivalent(gt.index))
                        return;
                }

                pos = i;

                // Update the values in case another iteration is required.
                i++;
                if(i == size)
                    return;

                d = h.get(i);
                type = d.task.getType();
            }
        }

        public IntVariable getLastPutIndex() {
            return lastIndex;
        }

    }

    /**
     * Method to check if a pair of traces are able to link different instances of a sample task together. It is
     * conservative, so may return indexes when multiple samples are not being mixed in, but should never return fail to
     * return a relevant index if multiple instances of the same sample task do contribute to the result at the end of
     * the trace.
     * 
     * @param serialIndexes
     */
    private static void samplesCombined(TraceHandle t1, TraceHandle t2, Set<IntVariable> serialIndexes) {
        ArrayTaskIterator i1 = new ArrayTaskIterator(t1);
        ArrayTaskIterator i2 = new ArrayTaskIterator(t2);
        boolean divergence = false;

        DataflowTaskArgDesc d1 = null;
        DataflowTaskArgDesc d2 = null;
        while(!divergence && i1.hasNext() && i2.hasNext()) {
            d1 = i1.next();
            d2 = i2.next();
            switch(d1.task.getType()) {
                case GET: {
                    DFType d2Type = d2.task.getType();
                    // If the other task is a reduction the serial requirement for the reduction is recorded elsewhere
                    // and it must include this sample task.
                    if(d2Type == DFType.REDUCE_INPUT)
                        break;

                    // If it is not a reduction it needs to be a get for the traces to not be diverging.
                    if(d2Type != DFType.GET) {
                        divergence = true;
                        break;
                    }
                    GetTask<?> gt1 = (GetTask<?>) d1.task;
                    GetTask<?> gt2 = (GetTask<?>) d2.task;
                    // Gets have to be equivalent, but they do not have to be the same task.
                    if(!gt1.equivalent(gt2)) {
                        divergence = true;
                        break;
                    }
                    break;
                }
                case PUT: {
                    // If this is not true the result is being saved into multiple arrays and may come back to the same
                    // point. TODO look at weakening this to allow for journeys via multiple different arrays.
                    if(d1.task != d2.task)
                        divergence = true;
                    break;
                }

                case REDUCE_INPUT: {
                    // If the task is a reduction the serial requirement for the reduction is recorded elsewhere
                    // and it must include this sample task.
                    DFType d2type = d2.task.getType();
                    if(d2type != DFType.REDUCE_INPUT && d2type != DFType.GET)
                        divergence = true;
                    break;
                }
                default:
                    throw new CompilerException("This should not be reachable, this is an error in the iterators.");
            }
        }

        /*
         * If the end of the trace has been reached without finding a point where different samples maybe introduced
         * then both iterators will be empty and no indexes need to be added. If either of the traces has additional
         * array interactions then the corresponding iterator will not be empty and the indexes that correspond to the
         * remaining get operations will be added.
         */
        consumeIndexes(i1, d1, divergence, serialIndexes);
        consumeIndexes(i2, d2, divergence, serialIndexes);
    }

    private static void consumeIndexes(ArrayTaskIterator i, DataflowTaskArgDesc d, boolean divergence,
            Set<IntVariable> serialIndexes) {
        // If the traces diverged at the last task and the task was a get record the corresponding put as requiring
        // serial execution.
        if(divergence && recordableType(d)) {
            IntVariable lastIndex = i.getLastPutIndex();
            if(lastIndex != null)
                serialIndexes.add(lastIndex);
        }

        // For all the remaining gets mark their corresponding puts as sequential.
        while(i.hasNext()) {
            d = i.next();
            if(recordableType(d)) {
                IntVariable lastIndex = i.getLastPutIndex();
                if(lastIndex != null)
                    serialIndexes.add(lastIndex);
            }
        }
    }

    // Helper method to check the task we want to record serial indexes for. Reductions are already recorded elsewhere,
    // so do not need to be handled here.
    private static boolean recordableType(DataflowTaskArgDesc d) {
        DFType type = d.task.getType();
        return (type == DFType.GET && d.argPos == 0);
    }

    private record GetIndexMap(Set<IntVariable> getLoopIndexes, Set<IntVariable> serialIndexes) {}

    /**
     * A method to determine if a trace is able to mix multiple samples into a single value via array manipulation. It
     * is conservative, so may return indexes to make serial when multiple samples are not being mixed in, but should
     * never fail to return an index if multiple instances of the same sample task do contribute to the result at the
     * end of the trace.
     * 
     * @param h             The trace to consider.
     * @param serialIndexes The set of indexes to mark as serial.
     */
    private static void samplesCombined(TraceHandle h, Set<IntVariable> serialIndexes) {

        // A stack storing the indexes that need to be serialised if the trace stops at this point. Each set in the
        // stack is the loops for a specific index so to get the complete set the union of the sets must be taken.
        Stack<Set<IntVariable>> putSerialIndexes = new Stack<>();
        // A list of maps, one per get that index from the loop indexes used in get indexes to the original put indexes
        // that correspond with them. This is used so that if arguments get reordered the changes can be tracked.
        List<GetIndexMap> getIndexMap = new ArrayList<>();

        // Look at each element in the trace.
        for(DataflowTaskArgDesc d:h) {
            switch(d.task.getType()) {
                case GET:
                    switch(d.argPos) {
                        case 0:
                            getIndexMap.add(constructIndexMap(putSerialIndexes.pop(), (GetTask<?>) d.task));
                            break;
                        case 1:
                            // At this point the data being return is a single int which is single sample within a
                            // single trace by definition, and there is no more opportunity to mix multiple samples.
                            return;
                        default:
                            throw new CompilerException("This case should never appear in a trace");
                    }
                    break;

                case PUT:
                    switch(d.argPos) {
                        case 0:
                            throw new CompilerException("This case should never appear in a trace");
                        case 1:
                            // Puts are not allowed to be indexed by sampled values as it undermines single assignment
                            // semantics.
                            throw new CompilerException("This case should never appear in a trace");
                        case 2:
                            PutTask<?> pt = (PutTask<?>) d.task;
                            // If the value being pushed is a single sample array and either the index it got it
                            // from or the index it is put in to is constant then it must remain as a single
                            // sample.
                            if(getIndexMap.isEmpty()) {
                                Set<IntVariable> s = new HashSet<>();
                                s.add(pt.index);
                                putSerialIndexes.push(s);
                            } else {
                                putSerialIndexes.push(getSerialIndexes(getIndexMap, pt));
                            }
                            break;
                        default:
                            throw new CompilerException("This case should never appear in a trace");
                    }
                    break;

                case REDUCE_INPUT:
                    // If the array is reduced all the scopes used to place the values in that dimension of the array
                    // must also be reduced.
                    if(d.argPos == 3) {
                        Set<IntVariable> c = putSerialIndexes.pop();
                        serialIndexes.addAll(c);
                    }
                    break;

                case SAMPLE:
                    int depth = d.task.getOutputType().getDepth();
                    for(int i = 0; i <= depth; i++)
                        putSerialIndexes.push(Collections.emptySet());
                    break;

                default:
                    break;
            }
        }

        // Add the detected indexes.
        for(Set<IntVariable> indexes:putSerialIndexes)
            serialIndexes.addAll(indexes);
    }

    /**
     * A method to construct the set of set of indexes to serialise for a put task based on the get tasks that preceded
     * it in the trace.
     * 
     * @param getSerialMaps The list of maps between loop indexes and the sets of indexes to make serial.
     * @param pt            The put task to compute the set for.
     * @return The set of indexes that would need to be serial if there is not a get operation to extract a single value
     *         from the array this put is updating.
     */
    private static Set<IntVariable> getSerialIndexes(List<GetIndexMap> getSerialMaps, PutTask<?> pt) {
        Set<IntVariable> set = new HashSet<>();

        Set<IntVariable> constantIndexes = constantIndexes(pt.array);
        Set<Variable<?>> vars = pt.index.collectInputVariables(DFType.FOR, DFType.PAR_FOR);
        vars.add(pt.index);
        for(Variable<?> input:vars) {
            DFType type = input.getParent().getType();
            if((type == DFType.FOR || type == DFType.PAR_FOR) && !constantIndexes.contains(input)) {
                for(GetIndexMap m:getSerialMaps) {
                    if(m.getLoopIndexes.contains(input))
                        set.addAll(m.serialIndexes);
                }
            }
        }

        return set;
    }

    /**
     * A method to construct a map from loop indexes used in the get task index to a set of indexes that the
     * corresponding put task is dependent on.
     * 
     * @param putIndexes The set indexes that the corresponding put task is dependent on.
     * @param gt         The get task to construct the mapping for.
     * @return The getIndexMapping for this task pairing.
     */
    private static GetIndexMap constructIndexMap(Set<IntVariable> putIndexes, GetTask<?> gt) {
        Set<IntVariable> getIndexes = new HashSet<>();

        Set<IntVariable> constantIndexes = constantIndexes(gt.array);
        Set<Variable<?>> vars = gt.index.collectInputVariables(DFType.FOR, DFType.PAR_FOR);
        vars.add(gt.index);
        for(Variable<?> input:vars) {
            DFType type = input.getParent().getType();
            if((type == DFType.FOR || type == DFType.PAR_FOR) && !constantIndexes.contains(input))
                getIndexes.add((IntVariable) input);
        }

        return new GetIndexMap(getIndexes, putIndexes);
    }

    /**
     * A method to determine the loop indexes that come from for loops, but are effectively final as they cannot change
     * from the perspective of the array.
     * 
     * @param a The array to calculate the set for.
     * @return THe resulting set of effectively final indexes.
     */
    private static Set<IntVariable> constantIndexes(ArrayVariable<?> a) {
        Set<IntVariable> indexes = new HashSet<>();
        a = a.getSourceInstance();
        Scope s = a.scope();
        while(s != GlobalScope.scope) {
            if(s.getScopeType() == ScopeType.FOR)
                indexes.add(((ForTask) s).getIndex());
            s = s.getEnclosingScope();
        }
        return indexes;
    }

    /**
     * Method to process a set of cycles working out which scopes within them need to be serialised to break the cycle.
     * 
     * @param cycles       The set of cycles.
     * @param serialScopes A set to record the scopes that need to be serialised.
     */
    private static void processCycles(List<Cycle> cycles, Set<ForTask> serialScopes) {
        for(Cycle cycle:cycles) {
            processCycle(cycle, serialScopes);
        }
    }

    /**
     * Method for processing a single cycle to work out which scopes need to be executed as serial code for correct
     * execution.
     * 
     * @param cycle        The cycle to process.
     * @param serialScopes A set to record which set should be executed as serial code.
     */
    private static void processCycle(Cycle cycle, Set<ForTask> serialScopes) {
        PriorityQueue<DataflowTask<?>> tasks = new PriorityQueue<>();
        // Collect all the get and put tasks in the cycle.
        while(!cycle.isEmpty()) {
            CycleStep step = cycle.pop();
            ingestTrace(step.traceToConsumingRV, tasks);
            ingestTrace(step.traceToIntermediate, tasks);
        }

        Map<ArrayVariable<?>, Map<Integer, Set<IntVariable>>> seenIndexes = new HashMap<>();
        // Go through the queue in order storing gets and testing puts.
        while(!tasks.isEmpty()) {
            DataflowTask<?> task = tasks.poll();

            // Find root and depth
            switch(task.getType()) {
                case GET: {
                    GetTask<?> get = (GetTask<?>) task;
                    // Find the root and depth of the task.
                    int depth = 0;
                    ArrayVariable<?> array = get.array;
                    DataflowTask<?> parent = array.instanceHandle().getParent();
                    while(parent.getType() == DFType.GET) {
                        array = ((GetTask<?>) parent).array;
                        parent = array.instanceHandle().getParent();
                        depth++;
                    }

                    Set<ForTask> forScopes = getForScopes(get);

                    // Collect all the variables used to construct this indexes.
                    IntVariable index = get.index;
                    Set<Variable<?>> inputs = index.collectInputVariables(DFType.FOR, DFType.PAR_FOR);
                    inputs.add(index);

                    // If any of the inputs are scope values, store the index.
                    for(ForTask f:forScopes) {
                        if(inputs.contains(f.getIndex())) {
                            addGet(seenIndexes, array, depth, index);
                            break;
                        }
                    }
                    break;
                }

                case PUT: {
                    PutTask<?> put = (PutTask<?>) task;
                    // Find the root and depth of the task.
                    int depth = 0;
                    ArrayVariable<?> array = put.array;
                    DataflowTask<?> parent = array.instanceHandle().getParent();
                    while(parent.getType() == DFType.GET) {
                        array = ((GetTask<?>) parent).array;
                        parent = array.instanceHandle().getParent();
                        depth++;
                    }

                    // Get the set of previous indexes into this array at this position.
                    Set<IntVariable> indexes = getIndexValues(seenIndexes, array, depth);
                    IntVariable index = put.index;
                    for(IntVariable i:indexes) {
                        if(!i.equivalent(index)) {// Test if we are writing to a different location to the one we read
                            // from.
                            Set<ForTask> forScopes = getForScopes(put);

                            // Set of variables used, not including those used in the bounds of for loops.
                            Set<Variable<?>> inputs = index.collectInputVariables(DFType.FOR, DFType.PAR_FOR);
                            inputs.add(index);

                            for(ForTask f:forScopes) {
                                // Test if this scope needs to be run in serial.
                                if(inputs.contains(f.getIndex()))
                                    serialScopes.add(f);
                            }
                        }
                    }
                    break;
                }

                default:
                    throw new CompilerException("This code should be unreachable as the tasks are already filtered.");
            }
        }
    }

    private static Set<IntVariable> getIndexValues(Map<ArrayVariable<?>, Map<Integer, Set<IntVariable>>> seenIndexes,
            ArrayVariable<?> array, int depth) {
        Map<Integer, Set<IntVariable>> arrayIndexes = seenIndexes.get(array);
        if(arrayIndexes == null)
            return Collections.emptySet();

        Set<IntVariable> indexes = arrayIndexes.get(depth);
        if(indexes == null)
            return Collections.emptySet();
        else
            return indexes;
    }

    private static void addGet(Map<ArrayVariable<?>, Map<Integer, Set<IntVariable>>> seenIndexes,
            ArrayVariable<?> array, int depth, IntVariable index) {
        Map<Integer, Set<IntVariable>> arrayIndexes = seenIndexes.computeIfAbsent(array, k -> new HashMap<>());

        Set<IntVariable> indexes = arrayIndexes.computeIfAbsent(depth, k -> new HashSet<>());

        indexes.add(index);
    }

    private static void ingestTrace(TraceHandle h, PriorityQueue<DataflowTask<?>> tasks) {
        Trace trace = h.getTrace();
        while(!trace.isEmpty()) {
            DataflowTaskArgDesc step = trace.pop();
            DataflowTask<?> task = step.task;
            switch(task.getType()) {
                case PUT:
                case GET:
                    tasks.add(task);
                    break;
                default:
                    break;
            }
        }
    }

    private static List<Cycle> findCycles(SampleTask<?, ?> sampleTask, Traces traces) {
        Set<RandomVariable<?, ?>> seen = new HashSet<>();
        List<Cycle> cycles = new ArrayList<>();
        Cycle cycle = new Cycle();

        // Get the traces from it to consuming random variables
        findCycles(sampleTask, sampleTask.randomVariable, traces, seen, cycles, cycle);

        return cycles;
    }

    private static void findCycles(SampleTask<?, ?> sampleTask, RandomVariable<?, ?> origin, Traces traces,
            Set<RandomVariable<?, ?>> seen, List<Cycle> cycles, Cycle cycle) {
        SampleTraceDesc d = traces.getSampleTrace(sampleTask);
        if(d != null) {
            // Get the consuming random variables for this intermediate variable
            for(RandomVariable<?, ?> rv:d.toConsumingRV.keySet()) {
                // If we've not seen this before, and we are in the scope of the index we are
                // looking at.
                if(!seen.contains(rv)) {
                    // Mark it as seen
                    seen.add(rv);
                    // Add it to the cycle
                    cycle.push(new CycleStep(d.traceToSampleVariable, d.toConsumingRV.get(rv)));

                    // Test if we have a cycle.
                    if(rv == origin)
                        cycles.add(cycle.copy()); // We have found a cycle, add it to the list.
                    else { // Else keep looking by adding any samples from this random variable,
                        for(DataflowTask<?> task:rv.getConsumers())
                            if(task.getType() == DFType.SAMPLE)
                                findCycles((SampleTask<?, ?>) task, origin, traces, seen, cycles, cycle);
                    }

                    // Remove the last element and go looking for new elements.
                    cycle.pop();
                    seen.remove(rv);
                }
            }
        }
    }

    private static class Cycle extends Stack<CycleStep> {
        private static final long serialVersionUID = 2847150857703323416L;

        public Cycle copy() {
            return (Cycle) clone();
        }
    }

    private static class CycleStep {
        public final TraceHandle traceToIntermediate;
        public final TraceHandle traceToConsumingRV;

        public CycleStep(TraceHandle traceToIntermediate, TraceHandle traceToConsumingRV) {
            this.traceToIntermediate = traceToIntermediate;
            this.traceToConsumingRV = traceToConsumingRV;
        }
    }

    private static Set<ForTask> getForScopes(DataflowTask<?> task) {
        Set<ForTask> scopeIndexValues = new HashSet<>();
        Scope scope = task.scope();
        while(scope != GlobalScope.scope) {
            switch(scope.getScopeType()) {
                case FOR:
                    ForTask parFor = (ForTask) scope;
                    scopeIndexValues.add(parFor);
                    scope = scope.getEnclosingScope();
                    break;
                case REDUCE:
                case IF:
                case ELSE:
                case BLOCK:
                case COMMENT:
                    scope = scope.getEnclosingScope();
                    break;
                default:
                    throw new CompilerException("Unknown scope type " + scope.getScopeType());
            }
        }
        return scopeIndexValues;
    }
}
