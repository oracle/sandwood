/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.traces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.nonReturnTasks.ObserveVariableTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.DistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableName;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.exceptions.SandwoodModelException;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.traces.DAGInfo.TraceSinkPair;

public class TracesImplementation extends Traces {
    // For each random variable store all the input sample tasks.
    private final Map<RandomVariable<?, ?>, List<Set<SampleTask<?, ?>>>> randomParents = new LinkedHashMap<>();
    // For each random variable record all the sample traces to each argument.
    private final Map<RandomVariable<?, ?>, List<Set<TraceHandle>>> randomTraces = new HashMap<>();
    // Set of all terminal variables
    private final Set<Variable<?>> terminalVariables = new HashSet<>();
    // Set of all variables fixed to an observed value
    private final Set<Variable<?>> observedVariables = new HashSet<>();
    // Set of inputs used to construct values in the model that are not observed to
    // fix other values.
    private final Set<Variable<?>> modelInputs = new HashSet<>();
    // Set of observed inputs used in the model whose shape is not used.
    private final Set<Variable<?>> observedOnlyInputs = new HashSet<>();
    // Map of observed inputs used in the model whose shape is used to the variable
    // representing the shape.
    private final Map<Variable<?>, Variable<?>> observedShapeableValues = new HashMap<>();
    // Set of observed intermediate variables
    private final Set<Variable<?>> obvervedIntermediateVars = new HashSet<>();
    // Set of deterministic intermediate variables
    private final Set<Variable<?>> deterministicVars = new HashSet<>();
    // Set of computed intermediate variables
    private final Set<Variable<?>> computedVars = new HashSet<>();
    // Set of all observed sampleTasks
    private final Set<SampleTask<?, ?>> observedSampleTasks = new HashSet<>();
    // Set of all variables holding sample values.
    private final Set<Variable<?>> sampleVariables = new HashSet<>();
    // Set of all variables
    private final Set<Variable<?>> allVariables = new HashSet<>();
    // Set of all sample tasks
    private final Set<SampleTask<?, ?>> allSampleTasks = new HashSet<>();
    // Map of top names to variables for top level variables. For Variables such as
    // arrays that might appear multiple times it is the instance variable that is
    // held.
    private final Map<VariableName, Variable<?>> namedVariables = new HashMap<>();
    // Set of all Samples that generate intermediate variables.
    private final Map<SampleTask<?, ?>, Map<RandomVariable<?, ?>, Set<TraceHandle>>> tracesRVToSampleTask = new HashMap<>();

    // To keep fields
    // Parent |-> Set(Stack of tasks linking them).
    private final Map<RandomVariable<?, ?>, Set<TraceHandle>> observedChildrenTraces = new HashMap<>();
    private final Map<SampleTask<?, ?>, Map<Variable<?>, Set<TraceHandle>>> observedSampleTraces = new HashMap<>();

    // Parent |-> Map(Intermediate Variable |-> Stack of tasks linking them).
    private final Map<RandomVariable<?, ?>, Set<TraceHandle>> intermediateChildrenTraces = new HashMap<>();

    // Sample |-> Set(IntermediateVariables).
    private final Map<SampleTask<?, ?>, IntermediateDesc> sampleToIntermediates = new HashMap<>();
    private final Map<Variable<?>, Set<SampleTask<?, ?>>> intermediateSampleTaskDependencies = new HashMap<>();
    private final Map<Variable<?>, Set<Variable<?>>> intermediateSampleVarDependencies = new HashMap<>();
    private final Map<Variable<?>, Set<Variable<?>>> requiredIntermediates = new HashMap<>();

    // Sample |-> SampleDesc
    private final Map<SampleTask<?, ?>, SampleTraceDesc> sampleTrace = new HashMap<>();

    // Variable |-> Set of random variables generating it.
    private final Map<Variable<?>, Set<RandomVariable<?, ?>>> varToRV = new HashMap<>();
    // Variable to the sample tasks generating it.
    private final Map<Variable<?>, Set<SampleTask<?, ?>>> sourceTasks = new HashMap<>();

    // Set of all the distribution sample tasks.
    private final Set<DistributionSampleTask<?, ?>> distributionSampleTasks = new HashSet<>();

    /**
     * TODO currently using a variable as a means of collecting the graph, a neater way would be nice, but I don't want
     * state to leak between programs, so some form of context object would be required, and a clean way of adding that
     * to every variable in a given graph, but not every variable is not currently clear to me.
     */
    private TracesImplementation(Variable<?>[] vs) {
        constructTraces(vs);
    }

    public static Traces getTraces(Variable<?>[] vs) {
        return new TracesImplementation(vs);
    }

    @Override
    public List<RandomVariable<?, ?>> getAllRandomVariables() {
        List<RandomVariable<?, ?>> toReturn = new ArrayList<>(randomParents.keySet());
        Collections.sort(toReturn);
        return toReturn;
    }

    @Override
    public List<Set<SampleTask<?, ?>>> getRandomVariablesPerArgument(RandomVariable<?, ?> child) {
        // This can never be null as lists are constructed for every random variable
        // during the construction of
        // the traces.
        return randomParents.get(child);
    }

    @Override
    public Set<TraceHandle> getTraces(RandomVariable<?, ?> rv, int argIndex) {
        return randomTraces.get(rv).get(argIndex);
    }

    private void constructTraces(Variable<?>[] vs) {
        // Get all the variables
        Queue<Variable<?>> toProcess = new LinkedList<>(Arrays.asList(vs));
        while(!toProcess.isEmpty()) {
            Variable<?> v = toProcess.remove();
            if(!allVariables.contains(v)) {
                allVariables.add(v);
                // if(v.getEnclosingScope()==GlobalScope.scope) TODO come back and revisit this.
                namedVariables.put(v.getVarDesc().name, v.instanceHandle());

                // Add all the inputs to the parent task.
                toProcess.addAll(v.getParent().getInputs());

                // if observed add these.
                if(v.isObserved())
                    toProcess.add(v.getObservation().source);

                // Explore the variables generated by the consumers
                Set<DataflowTask<?>> consumers = v.getConsumers();
                for(DataflowTask<?> t:consumers) {
                    toProcess.addAll(t.getInputs());
                    if(t.outputSet())
                        toProcess.add(t.getOutput());
                }
            }
        }

        DAGInfo dagInfo = new DAGInfo();
        // Iterate through the variables starting trace generation. For anything other
        // than random variables, observed variables, and terminal variables this will
        // do nothing,
        // but by constructing it like this was can add in other things we want to save
        // just by overriding the method in the class.
        for(Variable<?> v:allVariables)
            v.constructTrace(dagInfo);

        // set non-deterministic and distribution flags.
        for(TraceSinkPair p:dagInfo.allTraces())
            setFlags(p);

        addObservedVariables(dagInfo.getObservedVariables());

        for(RandomVariable<?, ?> r:dagInfo.getRandomVariables())
            addRandomVariable(r);

        setIntermediates();

        // ingest traces
        for(TraceSinkPair p:dagInfo.observedSourceTraces())
            addObservedSource(p);
        for(TraceSinkPair p:dagInfo.observedVarTraces())
            addObservedChild(p);
        for(TraceSinkPair p:dagInfo.randomVarTraces())
            addRandomChild(p);
        for(TraceSinkPair p:dagInfo.terminalVarTraces())
            addTerminalChild(p);

        constructDependencies(dagInfo);

        constructInputSets();

        setUniqueNames();
    }

    private void constructInputSets() {
        // Index to allow the variables to be looked up by name quickly.
        Map<VariableName, Variable<?>> inputNames = new HashMap<>();
        for(Variable<?> v:observedVariables) {
            if(v.getParent().getType() == DFType.CONSTRUCT_INPUT)
                inputNames.put(v.getVarDesc().name, v);
        }

        for(Variable<?> v:observedVariables) {
            if(v.getParent().getType() == DFType.CONSTRUCT_INPUT) {
                VariableName name = v.getVarDesc().name;
                if(!VariableNames.isLengthName(name)) {
                    VariableName lengthName = VariableNames.lengthName(name);
                    Variable<?> inputLength = inputNames.get(lengthName);

                    if(modelInputs.contains(v)) {
                        if(inputLength != null)
                            // Model inputs will always be set, so the length is not needed.
                            modelInputs.remove(inputLength);
                    } else if(inputLength != null) {
                        modelInputs.remove(inputLength);
                        observedShapeableValues.put(v, inputLength);
                    } else
                        observedOnlyInputs.add(v);
                }
            }
        }
    }

    private void setIntermediates() {
        for(Variable<?> v:allVariables) {
            if(!(v.getParent().getType() == DFType.CONSTRUCT_INPUT || (v instanceof RandomVariable))) {
                if(!v.isPrivate()) {
                    v.setIntermediate();
                    if(v.isDeterministic())
                        deterministicVars.add(v);
                    else
                        computedVars.add(v.instanceHandle());
                } else { // Look for outermost arrays and set them as intermediates.
                    if((v.getType().isArray() && !((ArrayVariable<?>) v).isSubArray())) {
                        v.setIntermediate();
                        if(v.isDeterministic())
                            deterministicVars.add(v);
                    }
                }
            }
        }
    }

    /**
     * Method to merge back in unique names where possible and to detect if the same name is used twice for public
     * variables.
     */
    private void setUniqueNames() {
        // Group all variables by name
        Map<VariableName, Set<Variable<?>>> vars = new HashMap<>();
        for(Variable<?> v:allVariables) {
            VariableDescription<?> varDesc = v.getVarDesc();
            Set<Variable<?>> nv = vars.computeIfAbsent(varDesc.name, k -> new HashSet<>());
            nv.add(v.instanceHandle());
        }

        // For each name look at the variables using it.
        for(VariableName name:vars.keySet()) {
            Set<Variable<?>> nv = vars.get(name);
            if(nv.size() == 1) { // If there is only 1 variable using this name we can dispense with complex
                // names.
                Variable<?> v = nv.iterator().next();
                setUniqueName(v);
            } else {
                boolean publicSet = false;
                for(Variable<?> v:nv) {
                    if(!v.isPrivate()) {
                        if(publicSet)
                            throw new SandwoodModelException("Multiple instances of public variable \"" + name + "\".",
                                    v);
                        else {
                            publicSet = true;
                            setUniqueName(v);
                        }
                    }
                }
            }
        }
    }

    private <A extends Variable<A>> void setUniqueName(Variable<A> v) {
        v.setUniqueVarDesc(v.getVarDesc());
    }

    /**
     * Method used to add each random variable encountered to the data structures.
     *
     * @param randomVariable
     */
    private void addRandomVariable(RandomVariable<?, ?> randomVariable) {
        int inputsCount = randomVariable.getParent().getInputsCount();
        List<Set<SampleTask<?, ?>>> inputSamples = new ArrayList<>();
        List<Set<TraceHandle>> inputTraces = new ArrayList<>();
        for(int i = 0; i < inputsCount; i++) {
            inputSamples.add(new LinkedHashSet<>());
            inputTraces.add(new LinkedHashSet<>());
        }
        randomParents.put(randomVariable, inputSamples);
        randomTraces.put(randomVariable, inputTraces);

    }

    private void addObservedChild(TraceSinkPair p) {
        TraceHandle handle = p.handle;
        Variable<?> sink = p.sink;

        ProducingDataflowTask<?> sourceTask = handle.get(0).task;
        switch(sourceTask.getType()) {
            case SAMPLE: {
                SampleTask<?, ?> sourceSample = (SampleTask<?, ?>) sourceTask;
                RandomVariable<?, ?> sourceRV = sourceSample.randomVariable;

                addIntermediates(handle);
                addVariableSource(handle, sourceSample);

                allSampleTasks.add(sourceSample);

                {
                    Set<TraceHandle> sourceChildren = observedChildrenTraces.computeIfAbsent(sourceRV,
                            k -> new HashSet<>());
                    sourceChildren.add(handle);
                }

                {
                    Map<Variable<?>, Set<TraceHandle>> sampleChildren = observedSampleTraces
                            .computeIfAbsent(sourceSample, k -> new HashMap<>());
                    Set<TraceHandle> sampleTraces = sampleChildren.computeIfAbsent(sink, k -> new HashSet<>());
                    sampleTraces.add(handle);
                }

                observedSampleTasks.add(sourceSample);
                addToVarToSample(sink, sourceRV);

                // Nothing progresses beyond this variable.
                if(sink.getConsumers().size() == 1)
                    findTerminalVariables(handle);

                // Populate sampleToRVTraces and intermediateChildrenTraces
                Set<TraceHandle> intermediateVariableTraces = intermediateChildrenTraces.computeIfAbsent(sourceRV,
                        k -> new HashSet<>());

                SplitTrace splitTrace = splitTrace(handle);
                TraceHandle toSampleHandle = TraceHandle.getTraceHandle(splitTrace.toSample);
                intermediateVariableTraces.add(toSampleHandle);
                addToVarToSample(splitTrace.sampleVar, sourceRV);

                SampleTraceDesc sampleTraceDesc = sampleTrace.get(sourceSample);
                if(sampleTraceDesc == null) {
                    sampleTraceDesc = new SampleTraceDesc(splitTrace.sampleVar, toSampleHandle);
                    sampleTrace.put(sourceSample, sampleTraceDesc);
                }
                break;
            }
            case CONSTRUCT_INPUT:
                Variable<?> output = sourceTask.getOutput();
                modelInputs.add(output);
                /*
                 * Check that none of the deterministic intermediates have been removed. If they have this means that
                 * the model is consuming and conditioning on the same values. This is currently not supported as it
                 * does not allow the execution of models in cases such as linear regression. In future we should weaken
                 * this by subclassing the model class and and providing models that do not support execution if this
                 * separation is not maintained.
                 */
                int size = handle.size();
                for(int i = 1; i < size; i++) {
                    DataflowTask<?> task = handle.get(i).task;
                    output = task.getOutput();
                    if(output.isDeterministic() && output.isIntermediate() && !deterministicVars.contains(output)) {
                        obvervedIntermediateVars.remove(output);
                        deterministicVars.add(output);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void addObservedSource(TraceSinkPair p) {
        TraceHandle t = p.handle;
        for(DataflowTaskArgDesc d:t) {
            DataflowTask<?> task = d.task;
            Variable<?> output = task.getOutput();
            if(output.isIntermediate()) {
                if(deterministicVars.remove(output))
                    obvervedIntermediateVars.add(output);
            }
        }
    }

    private void findTerminalVariables(TraceHandle h) {
        for(DataflowTaskArgDesc d:h) {
            Variable<?> v = d.task.getOutput();
            if(v.getConsumers().isEmpty())
                terminalVariables.add(v);
        }
    }

    private static class SplitTrace {
        // Splitting the trace into two parts, the part containing the gets and the part
        // containing the puts
        public final Trace fromConsumer = new Trace(); // Trace from the consuming task to the intermediate value.
        public final Trace toSample = new Trace();
        public Variable<?> sampleVar; // The variable holding the stored values.

        @Override
        public String toString() {
            return "From the consumer:\n" + fromConsumer + "\nTo the sample task:\n" + toSample + "\nSample variable: "
                    + sampleVar.getVarDesc() + "\n======\n";
        }
    }

    private void addRandomChild(TraceSinkPair p) {
        TraceHandle handle = p.handle;
        RandomVariable<?, ?> sink = (RandomVariable<?, ?>) p.sink;

        // Record the random variable constructor. //TODO revisit
        // this if we are passing random variables in arrays.
        DataflowTaskArgDesc constructor = handle.peek();

        // Record the traces to all RV arguments.
        if(filterDistributedArgTrace(handle))
            randomTraces.get(sink).get(constructor.argPos).add(handle);

        // Process the source, currently this only works on sample tasks
        // as everything else is deterministic.
        ProducingDataflowTask<?> sourceTask = handle.get(0).task;
        switch(sourceTask.getType()) {
            case SAMPLE: {
                // Get the sample task
                SampleTask<?, ?> sourceSample = (SampleTask<?, ?>) sourceTask;
                RandomVariable<?, ?> sourceRV = sourceSample.randomVariable;

                addIntermediates(handle);
                addVariableSource(handle, sourceSample);

                allSampleTasks.add(sourceSample);

                {
                    Map<RandomVariable<?, ?>, Set<TraceHandle>> rvTraces = tracesRVToSampleTask
                            .computeIfAbsent(sourceSample, k -> new LinkedHashMap<>());
                    Set<TraceHandle> traces = rvTraces.computeIfAbsent(sink, k -> new LinkedHashSet<>());
                    traces.add(handle);
                }

                // Populate random parents trace. This will have been initialized when the
                // random variable was first discovered.
                List<Set<SampleTask<?, ?>>> args = randomParents.get(sink);

                // Add arguments for inference generator selection
                Set<SampleTask<?, ?>> arg = args.get(constructor.argPos);
                arg.add(sourceSample);

                // Test if this sample task is a distribution, and record the producing RV.
                if(sourceSample.isDistribution())
                    distributionSampleTasks.add((DistributionSampleTask<?, ?>) sourceSample);

                // Populate sampleToRVTraces and intermediateChildrenTraces
                Set<TraceHandle> intermediateVariableTraces = intermediateChildrenTraces.computeIfAbsent(sourceRV,
                        k -> new HashSet<>());

                SplitTrace splitTrace = splitTrace(handle);
                TraceHandle toSampleHandle = TraceHandle.getTraceHandle(splitTrace.toSample);
                intermediateVariableTraces.add(toSampleHandle);
                addToVarToSample(splitTrace.sampleVar, sourceRV);

                SampleTraceDesc sampleTraceDesc = sampleTrace.get(sourceSample);
                if(sampleTraceDesc == null) {
                    sampleTraceDesc = new SampleTraceDesc(splitTrace.sampleVar, toSampleHandle);
                    sampleTrace.put(sourceSample, sampleTraceDesc);
                }
                sampleTraceDesc.toConsumingRV.put(sink, TraceHandle.getTraceHandle(splitTrace.fromConsumer));
                break;
            }
            case CONSTRUCT_INPUT:
                modelInputs.add(sourceTask.getOutput());
                break;
            default:
                break;
        }
    }

    // TODO make this filtering more generic
    private static boolean filterDistributedArgTrace(TraceHandle trace) {
        if(trace.get(0).task.getType() == DFType.SAMPLE)
            // Add anything that is from a sample
            return true;
        else {
            for(DataflowTaskArgDesc d:trace) {
                switch(d.task.getType()) {
                    // If it goes via an array constructor that is not a value that is returned by the task so the trace
                    // should be ignored.
                    case ARRAY_CONSTRUCTOR:
                        return false;
                    // If the value goes via condition the trace should be ignored as the condition will appear in the
                    // traces that go via the values.
                    case IF_ASSIGNMENT:
                        if(d.task.getOutputType().isArray() && d.argPos == 0)
                            return false;
                        break;
                    // As all passed arrays are populated this operation does not need to be filtered out.
                    case CONSTRUCT_INPUT:
                        break;
                    case GET:
                    case PUT:
                        break;
                    default:
                        if(d.task.getOutputType().isArray())
                            throw new CompilerException("Unexpected array generating task.");
                        break;
                }
            }

            // Add the trace if a guard is required to test
            boolean putValue = false;
            for(DataflowTaskArgDesc d:trace) {
                switch(d.task.getType()) {
                    case GET:
                        return putValue && d.argPos == 0;
                    case PUT:
                        if(d.argPos == 2)
                            putValue = true;
                        else
                            // If the trace is not entering via the value there will be traces that do that will pass
                            // this test.
                            return false;
                        break;
                    default:
                        break;
                }
            }
        }
        return false;
    }

    /**
     * A method to take a trace and split it into two parts, the parts leading from the sample task to the value that
     * will become the sample value and the parts leading from this value to the consumer. For a given instance of a
     * sample task, all traces leading to the sample value will be the same.
     * 
     * @param consumerTrace The trace to split
     * @return A split trace object containing the two traces, and the variable that holds the sample value.
     */
    private SplitTrace splitTrace(TraceHandle consumerTrace) {
        // Construct an object to hold the result.
        SplitTrace s = new SplitTrace();

        // Calculate the number of values that we should consider.
        // If the trace ends in the construction of a random variable
        // remove that as we want to stop at the argument used to create
        // the random variable.
        int noElements = consumerTrace.size();
        if(consumerTrace.peek().task.getOutput() instanceof RandomVariable)
            noElements--;

        // Get the sample task
        DataflowTaskArgDesc d = consumerTrace.get(0);
        // Record its scope
        s.toSample.add(d);
        // Add it to the to sample trace
        Scope sampleScope = d.task.scope();
        // And determine if it is only consumed by a single task.
        Variable<?> output = d.task.getOutput();
        boolean singleStream = output.getConsumers().size() == 1;
        boolean intermediateFound = output.isIntermediate();

        // Set the trace position ready to traverse the rest of the trace.
        int tracePos = 1;
        // Traverse through until we leave the scope, reach an array operation,
        // or the traces diverge.
        while(tracePos < noElements && singleStream && !intermediateFound) {
            // Get the element
            d = consumerTrace.get(tracePos); // We know the first element is there as there is always a sample.
            if(d.task.checkInversionError(d.argPos) != null || d.task.scope() != sampleScope || usesSampledValue(d))
                // Final test is used to ensure that we have
                // not gone into some other part of the
                // program. TODO This is a stop gap till we
                // change to non private named variables.
                break;

            // Store the trace element.
            s.toSample.add(d);
            // This value was consumed, so increment the position to the next value.
            tracePos++;

            // Test to see if the trace diverges at this point.
            output = d.task.getOutput();
            // Calculate if this is still a single stream.
            singleStream = output.getConsumers().size() == 1;
            // Make sure we stop at the first intermediate in the trace.
            intermediateFound = output.isIntermediate();
        }

        // Add any further put operations that store the value as long as this
        // is still a single stream.
        while(tracePos < noElements && singleStream && !intermediateFound) {
            d = consumerTrace.get(tracePos); // We know the element is always there as the last element is the RV
            // constructor.
            DFType t = d.task.getType();
            if(t != DFType.PUT || d.argPos != 2)
                break;

            // Store the trace element.
            s.toSample.add(d);

            // This value was consumed, so increment it to the next value.
            tracePos++;

            // Test to see if the trace diverges at this point.
            output = d.task.getOutput();
            if(output.getConsumers().size() != 1)
                singleStream = false;
            // Make sure we stop at the first intermediate in the trace.
            if(output.isIntermediate())
                intermediateFound = true;
        }

        // Get the last task in the to sample trace as this will generate the output
        // that the sample variable should be set to.
        DataflowTask<?> task = s.toSample.peek().task;
        Variable<?> v = task.getOutput();
        // Mark the variable as a sample
        v.setSample();
        // Store in the SplitTrace object, and in the traces implementation.
        s.sampleVar = v;
        sampleVariables.add(v);

        // Copy the remaining values
        while(tracePos < noElements)
            s.fromConsumer.push(consumerTrace.get(tracePos++));

        assert (noElements == s.fromConsumer.size() + s.toSample.size());
        return s;
    }

    private boolean usesSampledValue(DataflowTaskArgDesc d) {
        int size = d.task.getInputsCount();
        for(int i = 0; i < size; i++) {
            if(i != d.argPos && !d.task.getInput(i).isDeterministic())
                return true;
        }
        return false;
    }

    /**
     * Return all the observed variables in the graph.
     */
    @Override
    public Set<Variable<?>> getAllObservedVariables() {
        return observedVariables;
    }

    /**
     * Return all the variables that have no consumers.
     */
    @Override
    public Set<Variable<?>> getAllTerminalVariables() {
        return terminalVariables;
    }

    /**
     * Return all sample tasks
     */
    @Override
    public Set<SampleTask<?, ?>> getAllSampleTasks() {
        return allSampleTasks;
    }

    /**
     * Return all the variables we meet.
     */
    @Override
    public Set<Variable<?>> getAllVariables() {
        return allVariables;
    }

    private void addObservedVariables(Set<Variable<?>> vs) {
        observedVariables.addAll(vs);
    }

    @Override
    public List<TraceHandle> getObservedTraces(RandomVariable<?, ?> source) {
        Set<TraceHandle> result = observedChildrenTraces.get(source);
        if(result != null) {
            List<TraceHandle> toReturn = new ArrayList<>(result);
            Collections.sort(toReturn);
            return toReturn;
        } else
            return Collections.emptyList();
    }

    @Override
    public Map<Variable<?>, Set<TraceHandle>> getObservedTraces(SampleTask<?, ?> source) {
        return observedSampleTraces.get(source);
    }

    @Override
    public List<TraceHandle> getIntermediateVariableTraces(RandomVariable<?, ?> source) {
        Set<TraceHandle> result = intermediateChildrenTraces.get(source);
        if(result != null) {
            List<TraceHandle> toReturn = new ArrayList<>(result);
            Collections.sort(toReturn);
            return toReturn;
        } else
            return Collections.emptyList();
    }

    @Override
    public IntermediateDesc getIntermediates(SampleTask<?, ?> sample) {
        IntermediateDesc result = sampleToIntermediates.get(sample);
        if(result == null) {
            result = new IntermediateDesc(sample);
            sampleToIntermediates.put(sample, result);
        }
        return result;
    }

    @Override
    public Set<Variable<?>> getIntermediateSampleVarDependencies(Variable<?> v) {
        Set<Variable<?>> result = intermediateSampleVarDependencies.get(v);
        if(result == null)
            return Collections.emptySet();
        else
            return result;
    }

    @Override
    public Set<Variable<?>> getRequiredIntermediates(Variable<?> v) {
        Set<Variable<?>> result = requiredIntermediates.get(v);
        if(result == null)
            return Collections.emptySet();
        else
            return result;
    }

    private void addTerminalChild(TraceSinkPair p) {
        TraceHandle h = p.handle;
        Variable<?> sink = p.sink;
        ProducingDataflowTask<?> sourceTask = h.get(0).task;

        switch(sourceTask.getType()) {
            case SAMPLE: {
                SampleTask<?, ?> sourceSample = (SampleTask<?, ?>) sourceTask;
                RandomVariable<?, ?> sourceRV = sourceSample.randomVariable;
                addIntermediates(h);
                allSampleTasks.add(sourceSample);
                sink = sink.getCurrentInstance();
                findTerminalVariables(h);
                addToVarToSample(sink, sourceRV);
                addVariableSource(h, sourceSample);

                SplitTrace splitTrace = splitTrace(h);

                TraceHandle toSampleHandle = TraceHandle.getTraceHandle(splitTrace.toSample);
                SampleTraceDesc intermediateDesc = sampleTrace.get(sourceSample);
                if(intermediateDesc == null) {
                    intermediateDesc = new SampleTraceDesc(splitTrace.sampleVar, toSampleHandle);
                    sampleTrace.put(sourceSample, intermediateDesc);
                }
                break;
            }
            case CONSTRUCT_INPUT:
                modelInputs.add(sourceTask.getOutput());
                break;
            default:
                break;

        }
    }

    private void setFlags(TraceSinkPair p) {
        if(!p.handle.get(0).task.deterministic()) {
            for(DataflowTaskArgDesc a:p.handle)
                a.task.setNonDeterministic();
            p.sink.setNonDeterministic();
        }

        if(p.handle.get(0).task.isDistribution()) {
            for(DataflowTaskArgDesc a:p.handle)
                a.task.setIsDistribution();
            p.sink.setIsDistribution();
        }
    }

    /**
     * Method storing the mapping from sampled random variables to the intermediate variables that depend on them.
     * 
     * @param sink   The variable consuming results from the source random variable.
     * @param source The random variable being sampled to generate data used to produce the value of the sink.
     */
    private void addToVarToSample(Variable<?> sink, RandomVariable<?, ?> source) {
        // Populate the sink to source lookup.
        Set<RandomVariable<?, ?>> s = varToRV.computeIfAbsent(sink, k -> new HashSet<>());
        s.add(source);
    }

    @Override
    public Set<RandomVariable<?, ?>> getSourceRandomVariables(Variable<?> v) {
        Set<RandomVariable<?, ?>> sources = varToRV.get(v);
        if(sources == null)
            return Collections.emptySet();
        else
            return sources;
    }

    @Override
    public Set<SampleTask<?, ?>> getAllIntermediateSamples() {
        return tracesRVToSampleTask.keySet();
    }

    /**
     * A method that takes a sample task, and returns a map mapping random variables to sets of traces leading from the
     * random variable to back up the tree to the sample task.
     * 
     * @param sampleTask The sample task all the traces will end at.
     * @return
     */
    @Override
    public Map<RandomVariable<?, ?>, Set<TraceHandle>> getTracesRVToSampleTask(SampleTask<?, ?> sampleTask) {
        return tracesRVToSampleTask.get(sampleTask);
    }

    @Override
    public Variable<?> getVariable(VariableName name) {
        return namedVariables.get(name);
    }

    @Override
    public SampleTraceDesc getSampleTrace(SampleTask<?, ?> sample) {
        return sampleTrace.get(sample);
    }

    private void addIntermediates(TraceHandle h) {
        SampleTask<?, ?> sample = (SampleTask<?, ?>) h.get(0).task;
        Set<Variable<?>> intermediateVars = h.getIntermediates();

        IntermediateDesc intermediates = sampleToIntermediates.get(sample);
        if(intermediates == null) {
            intermediates = new IntermediateDesc(sample);
            sampleToIntermediates.put(sample, intermediates);
        }

        intermediates.addVariables(intermediateVars, h);

        for(Variable<?> v:intermediateVars) {
            Set<SampleTask<?, ?>> s = intermediateSampleTaskDependencies.computeIfAbsent(v, k -> new LinkedHashSet<>());
            s.add(sample);
        }
    }

    private void addVariableSource(TraceHandle t, SampleTask<?, ?> sample) {
        int max = t.size();
        for(int i = 0; i < max; i++) {
            DataflowTask<?> d = t.get(i).task;
            Variable<?> v = d.getOutput();
            Set<SampleTask<?, ?>> s = sourceTasks.computeIfAbsent(v, k -> new HashSet<>());
            s.add(sample);
        }
    }

    private void constructDependencies(DAGInfo dagInfo) {
        for(Variable<?> v:intermediateSampleTaskDependencies.keySet()) {
            Set<Variable<?>> vdep = new HashSet<>();
            for(SampleTask<?, ?> s:intermediateSampleTaskDependencies.get(v)) {
                SampleTraceDesc sDesc = sampleTrace.get(s);
                if(sDesc != null)
                    vdep.add(sDesc.sampleVariable.instanceHandle());
            }
            if(!vdep.isEmpty())
                intermediateSampleVarDependencies.put(v, vdep);
        }

        Set<Variable<?>> requirements = new HashSet<>();
        for(TraceSinkPair p:dagInfo.allTraces()) {
            for(DataflowTaskArgDesc a:p.handle) {
                Variable<?> v = a.task.getOutput().instanceHandle();
                if(v.isIntermediate()) {
                    Set<Variable<?>> vs = requiredIntermediates.computeIfAbsent(v, k -> new HashSet<>());
                    vs.addAll(requirements);
                    vs.remove(v); // A variable cannot depend on itself.
                    requirements.add(v);
                }
            }

            requirements.clear();
        }
    }

    @Override
    public Set<Variable<?>> getAllSampleVariables() {
        return sampleVariables;
    }

    @Override
    public Set<Variable<?>> getIntermediates() {
        return intermediateSampleVarDependencies.keySet();
    }

    @Override
    public Set<SampleTask<?, ?>> getObservedSampleTasks() {
        return observedSampleTasks;
    }

    @Override
    public Set<SampleTask<?, ?>> getSourceSampleTasks(Variable<?> v) {
        Set<SampleTask<?, ?>> toReturn = new HashSet<>();
        Set<SampleTask<?, ?>> result = sourceTasks.get(v);
        if(result != null)
            toReturn.addAll(result);
        return toReturn;
    }

    @Override
    public boolean isObserved(SampleTask<?, ?> sTask) {
        return observedSampleTasks.contains(sTask);
    }

    @Override
    public Set<DistributionSampleTask<?, ?>> getDistributionSampleTasks() {
        return distributionSampleTasks;
    }

    @Override
    public Set<DataflowTask<?>> getAllTasks() {
        Set<DataflowTask<?>> allTasks = new HashSet<>();
        for(Variable<?> v:allVariables)
            allTasks.add(v.getParent());
        for(Variable<?> v:observedVariables) {
            ObserveVariableTask<?> o = v.getObservation();
            // Check this is not an input value, but is a constrained value.
            if(o != null)
                allTasks.add(o);
        }
        return allTasks;
    }

    @Override
    public Set<Variable<?>> modelInputs() {
        return modelInputs;
    }

    @Override
    public Set<Variable<?>> observedOnlyInputs() {
        return observedOnlyInputs;
    }

    @Override
    public Set<Variable<?>> observedShapeableValues() {
        return observedShapeableValues.keySet();
    }

    @Override
    public Variable<?> observedShapeVariable(Variable<?> v) {
        return observedShapeableValues.get(v);
    }

    @Override
    public Set<Variable<?>> observedIntermediateVariables() {
        return obvervedIntermediateVars;
    }

    @Override
    public Set<Variable<?>> deterministicVariables() {
        return deterministicVars;
    }

    @Override
    public Set<Variable<?>> computedVariables() {
        return computedVars;
    }
}
