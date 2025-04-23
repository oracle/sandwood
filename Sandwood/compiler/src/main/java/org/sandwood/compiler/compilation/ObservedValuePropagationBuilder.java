/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.sandwood.compiler.compilation.CompilationContext.AuxFunctionType;
import org.sandwood.compiler.compilation.CompilationContext.CompilationPhase;
import org.sandwood.compiler.compilation.ForwardExecutionBuilder.GuardStatus;
import org.sandwood.compiler.compilation.util.TreeUtils;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.GetTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.PutTask;
import org.sandwood.compiler.dataflowGraph.tasks.nonReturnTasks.ObserveVariableTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.IfElseAssignmentTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.exceptions.SandwoodModelException;
import org.sandwood.compiler.traces.Trace;
import org.sandwood.compiler.traces.TraceHandle;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.traces.guards.ScopeConstructor;
import org.sandwood.compiler.traces.guards.TreeBuilderInfo;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.Visibility;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class ObservedValuePropagationBuilder {
    /**
     * A record class for recording the trace to the value that will be the source followed by the set of traces to the
     * value that will be constructed from the source.
     */
    private static record VarTraces(TraceHandle traceToSource, Set<TraceHandle> tracesToVar) {}

    /**
     * Private class for sorting variables based on their dependencies. The class is created so that dependencies can be
     * cached. TODO see if this class can be replaced with VariableDependencyQueue.
     */
    private static class VariableDependencyDesc implements Comparable<VariableDependencyDesc> {
        public final Variable<?> variable;
        private final Set<Variable<?>> dependencies;

        private VariableDependencyDesc(Variable<?> v, VarTraces traces) {
            variable = v;
            dependencies = new HashSet<>();
            getDependencies(traces.traceToSource);
            for(TraceHandle t:traces.tracesToVar)
                getDependencies(t);
        }

        private void getDependencies(TraceHandle t) {
            if(!t.isEmpty()) {
                // Add the output as this will be the value being created by a later operation.
                dependencies.add(t.peek().task.getOutput());
                for(DataflowTaskArgDesc d:t) {
                    DataflowTask<?> task = d.task;
                    Variable<?> scopeCondition = task.scope().getScopeCondition();
                    addDependencies(scopeCondition);
                    switch(task.getType()) {
                        case GET: {
                            GetTask<?> gt = (GetTask<?>) task;
                            addDependencies(gt.index);
                            addArrayDependencies(gt.array);
                            break;
                        }
                        case IF_ASSIGNMENT: {
                            IfElseAssignmentTask<?> ifElse = (IfElseAssignmentTask<?>) task;
                            addDependencies(ifElse.guard);
                            break;
                        }
                        case PUT: {
                            PutTask<?> pt = (PutTask<?>) task;
                            addDependencies(pt.index);
                            addArrayDependencies(pt.array);
                            break;
                        }
                        default:
                            break;
                    }
                }
            }
        }

        private void addArrayDependencies(ArrayVariable<?> array) {
            array = array.instanceHandle();
            ProducingDataflowTask<?> parent = array.getParent();
            DFType type = parent.getType();
            while(true) {
                switch(type) {
                    case ARRAY_CONSTRUCTOR:
                    case SAMPLE:
                        return;
                    case GET: {
                        GetTask<?> gt = (GetTask<?>) parent;
                        addDependencies(gt.index);
                        array = gt.array.instanceHandle();
                        parent = array.getParent();
                        type = parent.getType();
                        break;
                    }
                    default:
                        throw new CompilerException("Unexpected task type: " + type);
                }
            }
        }

        private void addDependencies(Variable<?> v) {
            if(!v.isDeterministic()) {
                dependencies.add(v);
                dependencies.addAll(v.collectInputVariables(DFType.SAMPLE));
            }
        }

        @Override
        public int compareTo(VariableDependencyDesc o) {
            boolean contains = o.dependencies.contains(variable);
            if(dependencies.contains(o.variable)) {
                if(contains)
                    throw new SandwoodModelException("Observed variables include a dependency cycle between "
                            + variable.getAlias() + " and " + o.variable.getAlias(), variable, o.variable);
                else
                    return 1;
            } else {
                if(contains)
                    return -1;
                else
                    return variable.getId() - o.variable.getId();
            }
        }

        public static Set<VariableDependencyDesc> getVariableDependencyDescs(
                Map<Variable<?>, VarTraces> segmentedTraces) {
            Set<VariableDependencyDesc> vdds = new HashSet<>();
            for(Variable<?> v:segmentedTraces.keySet()) {
                // Construct the VariableDependencyDesc
                VariableDependencyDesc vdd = new VariableDependencyDesc(v, segmentedTraces.get(v));

                /*
                 * Add any recursive dependencies, this works because any dependencies as a later step will have already
                 * been added to the existing VariableDependencyDescs
                 */
                for(VariableDependencyDesc existing:vdds) {
                    if(vdd.dependencies.contains(existing.variable))
                        vdd.dependencies.addAll(existing.dependencies);
                }

                /* Add the dependencies of this variable to anything that depends on it. */
                for(VariableDependencyDesc existing:vdds) {
                    if(existing.dependencies.contains(v))
                        existing.dependencies.addAll(vdd.dependencies);
                }

                vdds.add(vdd);
            }
            return vdds;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\nVariable " + variable.getAlias() + ":" + variable.getId() + " dependent on:");
            for(Variable<?> v:dependencies) {
                if(v.aliasSet()) {
                    sb.append("\n\t" + v.getAlias() + ":" + v.getId());
                }
            }
            return sb.toString();
        }
    }

    public static void constructPropagateObservedValues(CompilationContext compilationCtx) {

        // Set any observed variables required as input to the observation task.
        IRTreeVoid t1 = initialiseConstants(compilationCtx);

        // Set to record the intermediates whose value has been fixed.
        Set<Variable<?>> setIntermediates = new HashSet<>();

        // Propagate back from the observation task to the intermediate variables in the model.
        IRTreeVoid t2 = backPropagateObservedVariables(setIntermediates, compilationCtx);

        // Set any intermediates that are fixed as their parents are fixed.
        IRTreeVoid t3 = forwardPropagateObservedVariables(setIntermediates, compilationCtx);

        IRTreeVoid funcBody = IRTree.sequential(Tree.NoComment, t1, t2, t3);
        compilationCtx.addFunction(AuxFunctionType.OBSERVATION_PROPAGATION, Visibility.PUBLIC, new ArgDesc[0], funcBody,
                true, "Method to propagate observed values back into the model.");
    }

    private static IRTreeVoid initialiseConstants(CompilationContext compilationCtx) {
        IRTreeVoid t1;
        // Set the phase
        compilationCtx.phase = CompilationPhase.INITIALIZATION_OF_CONSTANTS;
        // Clear function dependent values to prevent pollution from one context to
        // another.
        compilationCtx.initialize();

        // Order the required variables and construct the trees to generate them.
        PriorityQueue<Variable<?>> p = new PriorityQueue<>(compilationCtx.traces.observedIntermediateVariables());
        if(p.isEmpty())
            t1 = IRTree.nop();
        else {
            while(!p.isEmpty()) {
                Variable<?> v = p.poll();
                ForwardExecutionBuilder.constructForwardMethod(v, GuardStatus.NONE, true, compilationCtx);
            }

            t1 = IRTree.treeScope(compilationCtx.getOutermostScopeTree(),
                    "Setting intermediate variables that are used to constrain other variables.");
        }
        return t1;
    }

    private static IRTreeVoid backPropagateObservedVariables(Set<Variable<?>> setIntermediates,
            CompilationContext compilationCtx) {
        IRTreeVoid t2;
        // Set the phase
        compilationCtx.phase = CompilationPhase.MAIN_METHODS;
        // Clear function dependent values to prevent pollution from one context to another.
        compilationCtx.initialize();
        // Make this serial for now to protect the unit tests. This may be removed later, but it will break the unit
        // tests as multiple RNGs will be created giving a different stream of numbers.
        compilationCtx.pushIsSerial(true);
        compilationCtx.setreverseScopes(true);

        Map<Variable<?>, VarTraces> segmentedTraces = new HashMap<>();
        for(SampleTask<?, ?> sTask:compilationCtx.traces.getAllSampleTasks()) {
            if(compilationCtx.traces.isObserved(sTask)) {
                // Test that the traces are valid
                Map<Variable<?>, Set<TraceHandle>> t = compilationCtx.traces.getUnconditionalObservedTraces(sTask);
                if(t == null)
                    throw new CompilerException("No observed trace provided.");

                /*
                 * TODO weaken this guard so that traces that split on an if else are allowed, or traces that go via
                 * different arrays are allowed. This is non trivial as arrays can be used in may ways to recombine the
                 * traces.
                 */
                int size = t.size();
                if(size != 1) {
                    String s = "\"";
                    for(Variable<?> v:t.keySet()) {
                        s = s + v.getAlias();
                        if(--size != 0) {
                            if(size == 1)
                                s = s + "\", \"";
                            else
                                s = s + "\", and \"";
                        } else
                            s = s + "\"";
                    }

                    throw new SandwoodModelException(
                            "Multiple variables linked to sample task. This could result in inconsistencies. "
                                    + "Relevant variables are " + s + ".",
                            sTask);
                }

                ingestTrace(t.values(), segmentedTraces, setIntermediates);
            }
        }

        ingestTrace(compilationCtx.traces.getObservedConditionTraces().values(), segmentedTraces, setIntermediates);

        // Construct the code to populate the values.
        Set<VariableDependencyDesc> vdds = VariableDependencyDesc.getVariableDependencyDescs(segmentedTraces);
        PriorityQueue<VariableDependencyDesc> p = new PriorityQueue<>(vdds);

        Set<Variable<?>> copied = new HashSet<>();
        while(!p.isEmpty()) {
            Variable<?> end = p.poll().variable;
            Variable<?> instanceHandle = end.instanceHandle();
            if(end.isObserved()) {
                if(!copied.contains(instanceHandle)) {
                    copyObservation(end, compilationCtx);
                    copied.add(instanceHandle);
                }
            } else {
                VarTraces vt = segmentedTraces.get(end);
                for(TraceHandle th:vt.tracesToVar) {
                    ProducingDataflowTask<?> endTask = th.get(0).task;
                    ScopeConstructor sc = ScopeConstructor.construct(endTask, endTask.scope(), Tree.NoComment,
                            compilationCtx);
                    // make sure the path from the source to the value to be set is valid.
                    sc = sc.addConstraint(th);
                    // Make sure the path to the source is valid.
                    if(!vt.traceToSource.isEmpty())
                        sc = sc.addConstraint(vt.traceToSource);
                    // Set the value using the source.
                    sc.addTree(1, (TreeBuilderInfo info) -> {
                        Variable<?> start = th.peek().task.getOutput();
                        if(start.isObserved())
                            start = start.getObservation().source;
                        IRTreeReturn<?> current = start.instanceHandle().getForwardIR(compilationCtx);
                        processTask(th.size() - 1, th, current, info.backTraceInfo, compilationCtx);
                    });
                }
            }
        }

        compilationCtx.setreverseScopes(false);
        compilationCtx.popIsSerial();

        t2 = IRTree.treeScope(compilationCtx.getOutermostScopeTree(),
                "Propagating values back from observations into the models intermediate variables.");
        return t2;
    }

    private static IRTreeVoid forwardPropagateObservedVariables(Set<Variable<?>> setIntermediates,
            CompilationContext compilationCtx) {
        IRTreeVoid t3;
        PriorityQueue<Variable<?>> toFix = new PriorityQueue<>();
        for(Variable<?> v:compilationCtx.traces.getAllVariables()) {
            if(v.isIntermediate() && v.isFixed() && !setIntermediates.contains(v))
                toFix.add(v);
        }

        compilationCtx.pushScope();
        while(!toFix.isEmpty())
            ForwardExecutionBuilder.constructForwardMethod(toFix.poll(), GuardStatus.NONE, false, compilationCtx);

        t3 = IRTree.treeScope(compilationCtx.getOutermostScopeTree(),
                "Set any intermediates with fixed parent values.");
        compilationCtx.popScope();
        return t3;
    }

    // TODO Replace the deep copies here with shallow copies of just the references, and then reallocate the variables
    // assigned prior to forward execution.
    private static <A extends Variable<A>, B extends Variable<B>> void copyObservation(Variable<A> end,
            CompilationContext compilationCtx) {
        ObserveVariableTask<A> ot = end.getObservation();
        IRTreeReturn<A> current = ot.source.getForwardIR(compilationCtx);
        DataflowTask<A> source = ot.target.getParent();
        compilationCtx.enterScope(source.scope());
        switch(source.getType()) {
            case GET: {
                // get Version
                int outputDepth = end.getType().getDepth();
                GetTask<A> gt = (GetTask<A>) source;
                IRTreeReturn<ArrayVariable<A>> arrayTree = gt.array.instanceHandle().getForwardIR(compilationCtx);

                IRTreeReturn<IntVariable> indexTree = gt.index.getForwardIR(compilationCtx);
                if(outputDepth == 0) {
                    // If the observed value was a scalar just assign it to the array.
                    IRTreeVoid store = IRTree.arrayPut(arrayTree, indexTree, current, Tree.NoComment);
                    compilationCtx.addTreeToScope(gt.scope(), store);
                } else {
                    IRTreeVoid copyBody = TreeUtils.copyArray((IRTreeReturn<ArrayVariable<B>>) current,
                            (IRTreeReturn<ArrayVariable<B>>) IRTree.arrayGet(arrayTree, indexTree));
                    compilationCtx.addTreeToScope(end.scope(), copyBody);
                }
                break;
            }
            case PUT: {
                // Put version
                PutTask<A> pt = (PutTask<A>) source;

                // Get the tree for the array
                IRTreeReturn<ArrayVariable<A>> arrayTree = pt.array.getForwardIR(compilationCtx);

                IRTreeVoid copyBody = TreeUtils.copyArray((IRTreeReturn<ArrayVariable<A>>) current, arrayTree);
                compilationCtx.addTreeToScope(end.scope(), copyBody);
                break;
            }
            default: {
                // All other instances
                saveIntermediate(end, current, compilationCtx);
                break;
            }
        }
        compilationCtx.leaveScope(source.scope());
    }

    private static <A extends Variable<A>, B extends Variable<B>> void processTask(int index, TraceHandle traceHandle,
            IRTreeReturn<A> current, BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        DataflowTaskArgDesc d = traceHandle.get(index);
        ProducingDataflowTask<?> task = d.task;

        switch(task.getType()) {
            case SAMPLE: {
                // If this is an unobserved intermediate populate it with the current value.
                Variable<?> v = task.getOutput();
                if((v.isIntermediate() || v.isSample()) && v.getType().getDepth() == 0)
                    saveIntermediate(v, current, compilationCtx);
                if(index > 1) {
                    d = traceHandle.get(index - 1);
                    RandomVariableConstructorTask<A, ?> rv = (RandomVariableConstructorTask<A, ?>) d.task;
                    IRTreeReturn<?> rvArg = rv.getInverseArg(current, d.argPos, compilationCtx);
                    // Starting at the -2 position as the value at -1 has already been calculated to generate rvArg.
                    processTask(index - 2, traceHandle, rvArg, backTraceInfo, compilationCtx);
                }
                break;
            }
            case GET: {
                if(d.argPos == 1)
                    throw new CompilerException(
                            "Unable to invert an array lookup operation to determine the value of the index based "
                                    + "on the output. Array values are not guaranteed to be unique, this prevents indexes "
                                    + "being calculated when back tracking through the model.");
                if(d.argPos != 0)
                    throw new CompilerException(
                            "There are only 2 arguments to a get, this value of argPos is not valid.");

                compilationCtx.enterScope(task.scope());

                Variable<?> output = task.getOutput();
                int outputDepth = output.getType().getDepth();
                GetTask<A> gt = (GetTask<A>) task;
                IRTreeReturn<ArrayVariable<A>> arrayTree = gt.array.instanceHandle().getForwardIR(compilationCtx);

                if(outputDepth == 0) {
                    // If the value was a scalar just assign it to the array.
                    IRTreeReturn<IntVariable> indexTree = gt.index.getForwardIR(compilationCtx);
                    IRTreeVoid store = IRTree.arrayPut(arrayTree, indexTree, current, Tree.NoComment);
                    compilationCtx.addTreeToScope(gt.scope(), store);
                }
                // If the value was an array as arrays can only be assigned to a single parent array and this
                // will have happened in the allocator there is nothing to do.

                if(index != 0)
                    processTask(index - 1, traceHandle, arrayTree, backTraceInfo, compilationCtx);

                compilationCtx.leaveScope(task.scope());
                break;
            }
            case PUT: {
                PutTask<B> pt = (PutTask<B>) task;
                backTraceInfo.updateSubstitutions(pt, compilationCtx);
                switch(d.argPos) {
                    case 0:
                        throw new CompilerException("Assignments to arrays where the input is the array"
                                + " being assigned to should not appear in traces.");
                    case 1:
                        // The array was indexed by the value of a sample. If and only if we are sure
                        // the array only contains unique elements
                        // we could construct a reverse lookup and use that to determine the value of i
                        // based on the forward value of arg 3 and
                        // the current state of the array.
                        throw new CompilerException("Unable to construct inverse function as put "
                                + "tasks cannot currently determine the index a value came from.");
                    case 2:
                        compilationCtx.enterScope(task.scope());

                        // Get the tree for the array
                        IRTreeReturn<ArrayVariable<B>> arrayTree;
                        ArrayVariable<B> array = pt.array.instanceHandle();
                        if(compilationCtx.initialized(pt.array)) {
                            array.markStopPoint();
                            arrayTree = array.getForwardIR(compilationCtx);
                            array.unmarkStopPoint();
                        } else
                            arrayTree = array.getForwardIR(compilationCtx);

                        IRTreeReturn<IntVariable> indexTree = pt.index.getForwardIR(compilationCtx);

                        // If this is not the last step in this trace segment and an array is being placed into this
                        // array declare that array in scope so that it can be used in other code.
                        if(index != 0) {
                            Variable<B> value = pt.value.instanceHandle();
                            if(value.getType().getDepth() != 0 && index > 1) {
                                if(!compilationCtx.initialized(value)) {
                                    compilationCtx.addTreeToScope(value.scope(),
                                            IRTree.initializeUnsetVariable(value.getUniqueVarDesc(), Tree.NoComment));
                                    compilationCtx.addInitialized(value);
                                }
                                compilationCtx.addTreeToScope(pt.scope(), IRTree.store(value.getUniqueVarDesc(),
                                        IRTree.arrayGet(arrayTree, indexTree), Tree.NoComment));

                                processTask(index - 1, traceHandle, IRTree.load(value.getUniqueVarDesc()),
                                        backTraceInfo, compilationCtx);
                            } else {
                                processTask(index - 1, traceHandle, IRTree.arrayGet(arrayTree, indexTree),
                                        backTraceInfo, compilationCtx);
                            }
                        }

                        compilationCtx.leaveScope(task.scope());
                        break;

                    // Values are not propagated through scope conditions, instead we will have to infer these values
                    // and hope that we do not have an inconsistent system. Inconsistency will be detectable by a
                    // model probability of 0/-Infinity in normal/log space respectively.
                    case 3:
                        throw new CompilerException(
                                "Values are not propagated through scope conditions, instead we will have to infer these values.");

                    default:
                        throw new CompilerException("Unknown operation put only accepts arguments in positions 0-2");
                }
                break;
            }
            default: {
                if(index == 0) {
                    Variable<?> output = task.getOutput();
                    saveIntermediate(output, current, compilationCtx);
                } else {
                    IRTreeReturn<?> result = task.getInverseIR(d.argPos, current, backTraceInfo, compilationCtx);
                    processTask(index - 1, traceHandle, result, backTraceInfo, compilationCtx);
                }
            }
        }
    }

    private static <A extends Variable<A>> void saveIntermediate(Variable<A> variable, IRTreeReturn<?> value,
            CompilationContext compilationCtx) {
        IRTreeVoid tree = TreeUtils.putIndirectValue(variable, (IRTreeReturn<A>) value, Tree.NoComment, compilationCtx);
        compilationCtx.addTreeToScope(variable.getParent().scope(), tree);
    }

    /**
     * A function to work out the segments of trace that values should be propagated back over and to store the results
     * in a map indexed by the variable they create so they can be recovered and the required code computed in the
     * correct order.
     * 
     * @param observedTraces
     * @param segmentedTraces
     */
    private static void ingestTrace(Collection<Set<TraceHandle>> observedTraces,
            Map<Variable<?>, VarTraces> segmentedTraces, Set<Variable<?>> setIntermediates) {
        for(Set<TraceHandle> traces:observedTraces) {
            if(multiplePaths(traces)) {
                TraceHandle t = traces.iterator().next();
                Variable<?> output = t.peek().task.getOutput();
                throw new SandwoodModelException("Multiple traces to observed variable " + output.getAlias()
                        + ". This could result in inconsistencies.", output.getObservation());
            }

            for(TraceHandle t:traces) {
                Trace segment = new Trace();
                int i = 0;

                // Skip all the tasks before the first fixed sample or intermediate variable.
                DataflowTaskArgDesc d = t.get(i++);
                ProducingDataflowTask<?> task = d.task;
                Variable<?> output = task.getOutput();
                while((!output.isFixed() || !(output.isSample() || effectiveIntermediate(output))) && i < t.size()) {
                    d = t.get(i++);
                    output = d.task.getOutput();
                }

                // If there is a fixed sample or intermediate variable
                if(output.isFixed() && (output.isSample() || effectiveIntermediate(output))) {
                    saveIntermediate(output, setIntermediates);
                    // If the sample came from a put into an array skip it and find the outermost point of the trace.
                    while(d.task.getType() == DFType.PUT && !output.isObserved()) {
                        d = t.get(i++);
                        output = d.task.getOutput();
                    }

                    if(output.isObserved())
                        segmentedTraces.computeIfAbsent(output,
                                k -> new VarTraces(TraceHandle.emptyTrace(), Collections.emptySet()));
                    else {
                        Variable<?> result;
                        // If the result is a get
                        if(d.task.getType() == DFType.GET) {
                            if(d.argPos == 0)
                                result = ((GetTask<?>) d.task).array;
                            else
                                throw new CompilerException("Array indexes cannot be observed, this should have been "
                                        + "detected and prevented from happening by reporting an "
                                        + "error to the user before this point.");
                        } else
                            result = output;
                        while(true) {

                            // Add the value as this is where the trace segment ends
                            segment.add(d);
                            // If the output is observed, this is also the end of the trace so store the segment and
                            // stop.
                            if(output.isObserved()) {
                                int start = i - 1;
                                VarTraces segments = segmentedTraces.computeIfAbsent(result,
                                        k -> new VarTraces(t.subTrace(start), new HashSet<>()));
                                segments.tracesToVar.add(TraceHandle.getTraceHandle(segment));
                                break;
                            }

                            // Move along a segment
                            d = t.get(i++);
                            output = d.task.getOutput();
                            // If the next intermediate has been found store the segment and start the next segment.
                            // If the parent is a get this intermediate has already been accounted for.
                            if(effectiveIntermediate(output) && d.task.getType() != DFType.GET) {
                                saveIntermediate(output, setIntermediates);
                                // If the value came from a put into an array skip it and find the outermost point of
                                // the trace.
                                segment.add(d);

                                if(!output.isObserved()) {
                                    d = t.get(i++);
                                    output = d.task.getOutput();
                                    while(d.task.getType() == DFType.PUT && !output.isObserved()) {
                                        segment.add(d);
                                        d = t.get(i++);
                                        output = d.task.getOutput();
                                    }
                                    // If the output of the put is the final observed value add it.
                                    if(d.task.getType() == DFType.PUT) {
                                        segment.add(d);
                                    } else {
                                        // Otherwise rewind to the last task ready for the next segment to be computed.
                                        d = t.get(--i);
                                        output = d.task.getOutput();
                                    }
                                }

                                int start = i - 1;
                                VarTraces segments = segmentedTraces.computeIfAbsent(result,
                                        k -> new VarTraces(t.subTrace(start), new HashSet<>()));
                                segments.tracesToVar.add(TraceHandle.getTraceHandle(segment));

                                segment.clear();
                                result = output;
                            }
                        }
                    }
                }
            }
        }
    }

    private static void saveIntermediate(Variable<?> output, Set<Variable<?>> setIntermediates) {
        if(output.getType().isArray()) {
            ArrayVariable<?> a = (ArrayVariable<?>) output;
            while(a.isSubArray()) {
                ArrayVariable<?> outer = null;
                while(outer == null) {
                    // Look for the put task that set the value in the outer array. This will be an implicit put if the
                    // array was constructed by a get.
                    for(DataflowTask<?> d:a.getConsumers()) {
                        if(d.getType() == DFType.PUT) {
                            PutTask<?> pt = (PutTask<?>) d;
                            if(pt.value == a) {
                                outer = pt.getOutput();
                                break;
                            }
                        }
                    }

                    // If we did not find the put move to the next state of the array
                    if(outer == null)
                        a = a.getChildInstance();
                    else
                        a = outer;
                }
            }
            setIntermediates.add(a);
        } else
            setIntermediates.add(output);
    }

    private static boolean effectiveIntermediate(Variable<?> output) {
        return output.isIntermediate() || output.getType().isArray();
    }

    /**
     * A method to check if a set of traces represents more than one path through the DAG. A single path can have more
     * than one trace if there are implicit puts, and puts specified by the user in the model that are semantically
     * equivalent.
     * 
     * @param traces The set of traces to compare
     * @return Returns true if the traces represent at least 2 different patterns.
     */
    private static boolean multiplePaths(Set<TraceHandle> traces) {
        Iterator<TraceHandle> i = traces.iterator();
        TraceHandle pattern = i.next();
        int size = pattern.size();
        while(i.hasNext()) {
            TraceHandle test = i.next();

            // Remove puts, samples, and if assignments that will not result in generated code.
            int patternPrefix = 0;
            while(patternPrefix < size) {
                DataflowTaskArgDesc dt = pattern.get(patternPrefix);
                DFType type = dt.task.getType();
                if(type != DFType.PUT && type != DFType.IF_ASSIGNMENT && type != DFType.SAMPLE)
                    break;
                patternPrefix++;
            }

            int testSize = test.size();
            int testPrefix = 0;
            while(testPrefix < testSize) {
                DataflowTaskArgDesc dt = test.get(testPrefix);
                DFType type = dt.task.getType();
                if(type != DFType.PUT && type != DFType.IF_ASSIGNMENT && type != DFType.SAMPLE)
                    break;
                testPrefix++;
            }

            // Test the remaining suffixes
            if(size - patternPrefix != testSize - testPrefix)
                return true;

            int difference = testPrefix - patternPrefix;
            for(int j = patternPrefix; j < size; j++) {
                DataflowTaskArgDesc dt = test.get(j - difference);
                DataflowTaskArgDesc pt = pattern.get(j);
                if(!dt.equals(pt)) {
                    // If the 2 trace elements are not the same check that they are both puts entered through the same
                    // input and with the same index. This situation can occur because implicit puts are created when a
                    // value is put into an array that is itself in an array.

                    if(dt.argPos != pt.argPos)
                        return true;
                    if(dt.task.getType() != DFType.PUT || pt.task.getType() != DFType.PUT)
                        return true;
                    if(((PutTask<?>) dt.task).index != ((PutTask<?>) pt.task).index)
                        return true;
                }
            }
        }

        return false;
    }
}
