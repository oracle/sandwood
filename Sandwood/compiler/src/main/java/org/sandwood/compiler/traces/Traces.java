/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.traces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.PutTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.DistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableName;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.exceptions.CompilerException;

public abstract class Traces {
    // TODO make SampleTraceDesc a generic class.
    public static class SampleTraceDesc {
        public final TraceHandle traceToSampleVariable;
        public final Variable<?> sampleVariable; // The variable that is the outermost point before the value starts
        // being consumed or the trace splits.
        public final Map<RandomVariable<?, ?>, TraceHandle> toConsumingRV = new HashMap<>(); // Traces to consumers.
        // This is used for finding
        // cycles.

        public SampleTraceDesc(Variable<?> sampleVariable, TraceHandle traceToSampleVariable) {
            this.sampleVariable = sampleVariable;
            this.traceToSampleVariable = traceToSampleVariable;
        }
    }

    public static class IntermediateDesc {
        private final SampleTask<?, ?> task;
        private final Map<Variable<?>, Set<TraceHandle>> traces = new HashMap<>();
        private final Map<Variable<?>, Set<ArrayVariable<?>>> requiredArrays = new HashMap<>();

        public IntermediateDesc(SampleTask<?, ?> task) {
            this.task = task;
        }

        public void addVariables(Set<Variable<?>> vs, TraceHandle h) {
            assert (h.get(0).task == task);

            // Populate a map that will be used to work out how much of the trace to copy
            // across.
            Map<DataflowTask<?>, Integer> index = new HashMap<>();
            int size = h.size();
            for(int i = 0; i < size; i++) {
                DataflowTaskArgDesc d = h.get(i);
                index.put(d.task, i);
            }

            // A set used to record all arrays met.
            Set<ArrayVariable<?>> arrays = new HashSet<>();

            for(Variable<?> v:vs) {
                // Initialize the trace construction
                Trace trace = new Trace();
                ProducingDataflowTask<?> t = v.getParent();
                Integer pos = index.get(t);

                // Add any puts to array tasks before the passed trace is reached
                while(pos == null) {
                    assert (t.getType() == DFType.PUT);
                    trace.push(new DataflowTaskArgDesc(t, 2));
                    PutTask<?> pt = (PutTask<?>) t;
                    arrays.add(pt.array);
                    t = pt.value.getParent();
                    pos = index.get(t);
                }

                // copy the rest of the trace
                for(int i = pos; i >= 0; i--) {
                    DataflowTaskArgDesc d = h.get(i);
                    if(d.task.getType() == DFType.PUT)
                        arrays.add(((PutTask<?>) d.task).array);
                    trace.push(d);
                }

                // And add the trace
                addTrace(v, trace, arrays);
                arrays.clear();
            }
        }

        private void addTrace(Variable<?> variable, Trace trace, Set<ArrayVariable<?>> arrays) {
            // Add the trace to traces, creating the set if it doesn't already exist.
            Set<TraceHandle> traceSet = traces.computeIfAbsent(variable, k -> new HashSet<>());
            traceSet.add(TraceHandle.getReversedTraceHandle(trace));

            requiredArrays.computeIfAbsent(variable, k -> new HashSet<>()).addAll(arrays);
        }

        public Set<Variable<?>> getVariables() {
            return traces.keySet();
        }

        public Set<TraceHandle> getTraces(Variable<?> v) {
            return traces.get(v);
        }

        public boolean containsVariable(Variable<?> v) {
            return traces.containsKey(v);
        }

        public Set<ArrayVariable<?>> getRequiredArrays(Variable<?> v) {
            return requiredArrays.get(v);
        }
    }

    /** Returns a set containing all the random variables in the graph */
    public abstract List<RandomVariable<?, ?>> getAllRandomVariables();

    /**
     * Method to return all variables in the graph that are fixed to the value of another variable by an observation.
     *
     * @return The set of variables whose value is fixed via an observation.
     */
    public abstract Set<Variable<?>> getAllObservedVariables();

    /**
     * Method to return the model inputs, this will not return observed inputs.
     * 
     * @return The set of model inputs that are not used in observations.
     */
    public abstract Set<Variable<?>> modelInputs();

    /**
     * Method to get the model inputs that are observed, but whose shape is not used by the model.
     * 
     * @return The set of model inputs that are observed, but whose shape is not observed.
     */
    public abstract Set<Variable<?>> observedOnlyInputs();

    /**
     * Method to get the model inputs that are observed, and whose shape is used by the model.
     * 
     * @return The set of model inputs that are observed, and whose shape is also observed.
     */
    public abstract Set<Variable<?>> observedShapeableValues();

    /**
     * Method to get the corresponding length variable to go with a shape variable.
     * 
     * @param v The shaped variable that a corresponding length variable is required for.
     * @return The corresponding shape variable.
     */
    public abstract Variable<?> observedShapeVariable(Variable<?> v);

    /**
     * A method to get the set of intermediate values that are not inputs and are only observed.
     * 
     * @return The set of intermediate variables that are not inputs and are only observed.
     */
    public abstract Set<Variable<?>> observedIntermediateVariables();

    /**
     * A method to get the set of intermediate variables in the model whose value is deterministic. Input values are not
     * included in this set as they do not need to be computed and observed values are not included as their inputs will
     * only be present if the value is set for inference.
     * 
     * @return The set of deterministic intermediate values that are not inputs or only observed.
     */
    public abstract Set<Variable<?>> deterministicVariables();

    /**
     * A method to get the set of intermediate variables in the model whose value has to be computed.
     * 
     * @return The set of intermediate variables in the model whose value is computed.
     */
    public abstract Set<Variable<?>> computedVariables();

    /**
     * Method to return all the variables that have no consumers.
     *
     * @return
     */
    public abstract Set<Variable<?>> getAllTerminalVariables();

    /**
     * Method to return all the variables that are sample variables in the graph.
     *
     * @return
     */
    public abstract Set<Variable<?>> getAllSampleVariables();

    public abstract Set<Variable<?>> getIntermediates();

    /**
     * Method to return all the non-random variables in the graph intermediate or otherwise.
     *
     * @return
     */

    public abstract Set<Variable<?>> getAllVariables();

    /**
     * Tasks a random variable and returns a list representing each argument to the constructor of the random variable.
     * Each element in the list is a set, and each set contains the sample task that sampled that output from the source
     * random variable.
     *
     * @param random
     * @return
     */
    public abstract List<Set<SampleTask<?, ?>>> getRandomVariablesPerArgument(RandomVariable<?, ?> random);

    /**
     * Method to get all the random variables that directly contribute to the result of a variable v.
     *
     * @param v
     * @return
     */
    public abstract Set<RandomVariable<?, ?>> getSourceRandomVariables(Variable<?> v);

    // ******************* Methods for generating back traces ********************************//
    /**
     * A method to get traces to all the observed variables that are reachable from this random variable without passing
     * through another random variable.
     *
     * @param rv
     * @return
     */
    public abstract List<TraceHandle> getObservedTraces(RandomVariable<?, ?> rv);

    /**
     * A method to get traces to all the intermediate variables that are reachable from this random variable without
     * passing through another random variable.
     *
     * @param rv
     * @return
     */
    public abstract List<TraceHandle> getIntermediateVariableTraces(RandomVariable<?, ?> rv);

    public abstract IntermediateDesc getIntermediates(SampleTask<?, ?> sample);

    /**
     * Method for getting the details of the traces from a sample from a random variable that lead to other random
     * variables. The returned type is a collection if intermediateDesc objects.
     * <p>
     * The output identifies the outermost point that is reached from the sample and calls this the intermediate point.
     * This is a variable common to all traces.
     *
     * @param sample The sample task that created the value from a parent Random Variable.
     * @return
     */
    public abstract SampleTraceDesc getSampleTrace(SampleTask<?, ?> sample);

    public abstract Set<SampleTask<?, ?>> getAllIntermediateSamples();

    /**
     * A method that takes a sample task, and returns a map mapping random variables to sets of traces leading from the
     * random variable to back up the tree to the sample task.
     * 
     * @param sampleTask The sample task all the traces will end at.
     * @return
     */
    public abstract Map<RandomVariable<?, ?>, Set<TraceHandle>> getTracesRVToSampleTask(SampleTask<?, ?> sampleTask);

    /**
     * Method to get a variable by name.
     *
     * @param name The name of the variable we are trying to get.
     * @return
     */
    public abstract Variable<?> getVariable(VariableName name);

    /**
     * Return all sample tasks
     * 
     * @return All the sample tasks in the model.
     */
    public abstract Set<SampleTask<?, ?>> getAllSampleTasks();

    public abstract Set<Variable<?>> getIntermediateSampleVarDependencies(Variable<?> v);

    public abstract Set<Variable<?>> getRequiredIntermediates(Variable<?> v);

    public abstract Set<SampleTask<?, ?>> getSourceSampleTasks(Variable<?> intermediate);

    public abstract Set<SampleTask<?, ?>> getObservedSampleTasks();

    public abstract boolean isObserved(SampleTask<?, ?> sTask);

    public abstract Map<Variable<?>, Set<TraceHandle>> getObservedTraces(SampleTask<?, ?> source);

    /**
     * Method to get all the distribution sample tasks.
     * 
     * @return A set containing all the distribution sample tasks.
     */
    public abstract Set<DistributionSampleTask<?, ?>> getDistributionSampleTasks();

    /**
     * A method to get the traces from samples to a specific argument of this random variable.
     * 
     * @param rv       The random variable consuming the traces.
     * @param argIndex The index of the random variable that consumes the traces.
     * @return A set of all the traces from samples to the specified argument of the random variable.
     */
    public abstract Set<TraceHandle> getTraces(RandomVariable<?, ?> rv, int argIndex);

    /**
     * A method to get all the tasks in the graph;
     * 
     * @return A set containing all the tasks in the graph.
     */
    public abstract Set<DataflowTask<?>> getAllTasks();

    // Recursive method to construct the cross product of the sets in each of the
    // argument positions.
    private static Set<Set<TraceHandle>> crossProduct(Set<Set<TraceHandle>> s1, Set<Set<TraceHandle>> s2) {
        Set<Set<TraceHandle>> toReturn = new LinkedHashSet<>();

        for(Set<TraceHandle> p:s2) {
            for(Set<TraceHandle> a:s1) {
                Set<TraceHandle> toAdd = new LinkedHashSet<>();
                toAdd.addAll(p);
                toAdd.addAll(a);
                toReturn.add(toAdd);
            }
        }
        return toReturn;
    }

    /**
     * Method to take a set of traces leading to a consumer and groups them into the minimum number of sets so that each
     * trace is in at least one set, and within a set all the traces are dependent on all the other traces in the set.
     * <p>
     * The effect of these sets is that all the traces in a set must be valid for the configuration of the system to be
     * correct to generate a result for the consumer, and at any time at most one set is capable of being valid.
     * 
     * @param traces The set of traces to group.
     * @return The set of sets of traces.
     */
    private static Set<Set<TraceHandle>> groupTraces(Set<TraceHandle> traces) {
        // Construct the set to return and prime it with an empty set.
        Set<Set<TraceHandle>> groupedTraces = new LinkedHashSet<>();
        groupedTraces.add(new LinkedHashSet<>());

        // For each handle
        for(TraceHandle traceAdd:traces) {
            Set<Set<TraceHandle>> toAddTraceSets = new LinkedHashSet<>();

            // For each existing set
            for(Set<TraceHandle> s:groupedTraces) {
                Set<TraceHandle> dependentTraces = new LinkedHashSet<>();

                // Construct a set containing all the traces in s that are
                // dependent on the trace that is to be added.
                for(TraceHandle traceCurrent:s) {
                    if(dependentTraces(traceAdd, traceCurrent))
                        dependentTraces.add(traceCurrent);
                }
                // If all the traces are dependent on the trace we are adding.
                if(s.size() == dependentTraces.size())
                    s.add(traceAdd);
                // If we couldn't add it anywhere, create a set containing the things
                // that are dependent on each other and add that as a new set.
                else {
                    dependentTraces.add(traceAdd);
                    toAddTraceSets.add(dependentTraces);
                }
            }
            groupedTraces.addAll(toAddTraceSets);
        }
        return groupedTraces;
    }

    /**
     * Method to determine if two traces are dependent on each other, i.e. if the value produced by the source of one
     * trace changes, the value produced by the other trace changes too. This is used when working out which traces need
     * to be grouped together for the construction of distributed scopes. This is achieved by following the traces from
     * the consuming variable constructor to the point that the traces diverge.
     * <p>
     * If the trace is anything other than a put into an array at the point they diverge then they are dependent. If it
     * is a put into an array they are dependent if and only if both traces are acting on the same element in the array.
     * This can be tested by ensuring neither trace proceeds via the array input as to do so would mean that trace is
     * updating a different element in an earlier state of the array.
     *
     * @param a The first trace to analyse.
     * @param b The second trace to analyse.
     * @return Returns true if the traces are dependent, and false otherwise.
     */
    private static boolean dependentTraces(TraceHandle a, TraceHandle b) {
        int aPos = a.size() - 1;
        int bPos = b.size() - 1;
        Stack<DataflowTask<?>> accesses = new Stack<>();
        while(aPos >= 0 && bPos >= 0) {
            DataflowTaskArgDesc aElement = a.get(aPos--);
            DataflowTaskArgDesc bElement = b.get(bPos--);
            if(!aElement.equals(bElement)) {
                // Test if the tasks are different in which case the traces independent,
                // otherwise we have different arguments at the divergence, but it is into the
                // same task so they are dependent.
                int aId = aElement.task.id();
                int bId = bElement.task.id();
                // This is the same task with different arguments, and the arguments are not the if and else branches of
                // a conditional assignment
                if(aId == bId)
                    return aElement.task.getType() != DFType.IF_ASSIGNMENT || aElement.argPos == 0
                            || bElement.argPos == 0;
                else {
                    // The traces are diverging by setting multiple elements in an array.
                    return
                    // The consumer is consuming the whole array
                    accesses.isEmpty() ||
                    // The array is being consumed by a reduction and both traces feed into the array.
                    /*
                     * TODO there is a bug here if the range feature on a reduction is being used as the trace may or
                     * may not feed into the covered range, or may feed into it only some of the time. For now this is
                     * being overcome by preventing ranges being used in the parser.
                     */
                            accesses.peek().getType() != DFType.GET;
                }
            } else {
                switch(aElement.task.getType()) {
                    case GET:
                        if(aElement.argPos == 0)
                            accesses.add(aElement.task);
                        break;
                    case PUT:
                        if(!accesses.isEmpty())
                            accesses.pop();
                        break;
                    case REDUCE_INPUT:
                        accesses.add(aElement.task);
                        break;
                    default:
                        break;

                }
            }
        }
        throw new CompilerException("This point should be unreachable as the traces should not be identical.");
    }

    public static Set<Set<TraceHandle>> findDistributionTraces(RandomVariable<?, ?> rv,
            CompilationContext compilationCtx) {
        Set<Set<TraceHandle>> distributionTraces = noDistributionTraces;
        if(rv.isDistribution()) {
            DataflowTask<?> t = rv.getParent();
            int noArgs = t.getInputsCount();
            for(int i = 0; i < noArgs; i++) {
                if(t.getInput(i).isDistribution()) {
                    // Get the traces from samples to distributed argument i of the random variable.
                    Set<TraceHandle> argTraces = compilationCtx.traces.getTraces(rv, i);
                    // Group the traces into sets that should be handled independently
                    Set<Set<TraceHandle>> argumentTraces = groupTraces(argTraces);
                    // Merge all the sets for each argument to get the final set of sets.
                    distributionTraces = crossProduct(argumentTraces, distributionTraces);
                }
            }
        }

        return distributionTraces;
    }

    public static Set<Set<TraceHandle>> groupInputTraces(Set<TraceHandle> traces) {
        // Construct the base case for the returned set.
        Set<Set<TraceHandle>> groupedTraces = new HashSet<>();
        groupedTraces.add(Collections.emptySet());

        Iterator<TraceHandle> ts = traces.iterator();
        if(ts.hasNext()) {
            // Split all the traces by the argument they go to.
            TraceHandle t = ts.next();
            DataflowTaskArgDesc d = t.peek();
            int noArgs = d.task.getInputsCount();
            List<Set<TraceHandle>> perArgTraces = new ArrayList<>(noArgs);
            for(int i = 0; i < noArgs; i++)
                perArgTraces.add(new HashSet<TraceHandle>());
            perArgTraces.get(d.argPos).add(t);

            while(ts.hasNext()) {
                t = ts.next();
                d = t.peek();
                perArgTraces.get(d.argPos).add(t);
            }

            for(Set<TraceHandle> argTraces:perArgTraces) {
                // Group the traces into sets that should be handled independently
                Set<Set<TraceHandle>> groupdArgTraces = groupTraces(argTraces);
                // Merge all the sets for each argument to get the final set of sets.
                groupedTraces = crossProduct(groupdArgTraces, groupedTraces);
            }
        }
        return groupedTraces;
    }

    public static boolean noDistributionTraces(Set<Set<TraceHandle>> s) {
        for(Set<TraceHandle> ts:s)
            for(TraceHandle t:ts)
                if(t.peek().task.getOutput().isDistribution())
                    return false;
        return true;
    }

    public static boolean concurrentPaths(Set<TraceHandle> consumerToSampleTraces) {
        // Test if there are multiple paths through a single trace.
        for(TraceHandle t:consumerToSampleTraces)
            if(containsMultiPathReduction(t))
                return true;

        List<TraceHandle> ts = new ArrayList<>(consumerToSampleTraces);
        int size = ts.size();
        // Test if the traces go to more than one task that is consumed by the consumer.
        if(size > 0) {
            int taskID = ts.get(0).peek().task.id();
            for(int i = 1; i < size; i++) {
                if(ts.get(i).peek().task.id() != taskID)
                    return true;
            }
        }

        // Test if any 2 traces are dependent on each other.
        for(Set<TraceHandle> s:groupTraces(consumerToSampleTraces)) {
            if(s.size() != 1)
                return true;
        }

        return false;
    }

    /**
     * A method to see if this trace could enter the reduction multiple times. This can occur if a put is in an
     * iterating scope relative the the source of the value it is putting, and this happens before the reduce is reached
     * in the trace.
     * 
     * @param toConsumerTrace The trace to examine.
     * @return Returns true if for a given source and consumer this trace is potentially valid more than once during its
     *         evaluation.
     */
    public static boolean containsMultiPathReduction(TraceHandle toConsumerTrace) {
        Stack<Scope> scopes = new Stack<>();
        DataflowTaskArgDesc d = toConsumerTrace.get(0);
        scopes.push(d.task.scope());

        boolean multiplePaths = false;
        for(int i = 1; i < toConsumerTrace.size(); i++) {
            d = toConsumerTrace.get(0);
            switch(d.task.getType()) {
                case GET:
                    scopes.push(d.task.scope());
                    break;
                case PUT:
                    if(!scopes.isEmpty()) {
                        Scope valueScope = scopes.pop();
                        // Gather all the scopes incase the get happened in an if else statement.
                        Set<Scope> valueScopes = new HashSet<>();
                        while(valueScope != null) {
                            valueScopes.add(valueScope);
                            valueScope = valueScope.getEnclosingScope();
                        }

                        Scope setScope = d.task.scope();
                        boolean iterating = false;
                        while(!valueScopes.contains(setScope)) {
                            iterating = iterating || setScope.iterating();
                            setScope = setScope.getEnclosingScope();
                        }
                        multiplePaths = multiplePaths || iterating;
                    }
                    break;
                case REDUCE_INPUT:
                    if(multiplePaths)
                        return false;
                    else
                        break;
                default:
                    break;
            }
        }
        return false;
    }

    /**
     * A variable representing a distribution with no traces.
     */
    public static final Set<Set<TraceHandle>> noDistributionTraces;

    static {
        Set<Set<TraceHandle>> s = new HashSet<>();
        s.add(Collections.emptySet());
        noDistributionTraces = Collections.unmodifiableSet(s);
    }
}
