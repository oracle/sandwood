/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
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

import org.sandwood.compiler.compilation.util.CompilationDesc;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.ConstructArrayInput;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.GetTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.PutTask;
import org.sandwood.compiler.dataflowGraph.tasks.nonReturnTasks.ObserveVariableTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.DistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.IfElseAssignmentTask;
import org.sandwood.compiler.dataflowGraph.transformations.DAGTransformations;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableName;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable.OuterArrayDesc;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Multinomial;
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
    private final Map<SampleTask<?, ?>, Map<Variable<?>, Set<TraceHandle>>> observedUnconditionalSampleTraces = new HashMap<>();
    private final Map<SampleTask<?, ?>, Map<Variable<?>, Set<TraceHandle>>> observedAllSampleTraces = new HashMap<>();

    // Parent |-> Map(Intermediate Variable |-> Stack of tasks linking them).
    private final Map<RandomVariable<?, ?>, Set<TraceHandle>> sampleVariableTraces = new HashMap<>();

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
    // Variable to the observed variable generating it.
    private final Map<Variable<?>, Set<Variable<?>>> sourceObservedVars = new HashMap<>();

    // Set of all the distribution sample tasks.
    private final Set<DistributionSampleTask<?, ?>> distributionSampleTasks = new HashSet<>();

    // Set of named variables on traces from a sample task that are read by a random variable argument and fixed by an
    // observation.
    private final Set<Variable<?>> consumedObservedVariables = new HashSet<>();

    // A map from tasks where computation can branch to the traces taking data through that branch point.
    private final Map<ProducingDataflowTask<?>, SplitConditionalTraces> conditionalTraces = new HashMap<>();
    // A map from sample tasks to points where that sample effects the control flow of the program.
    private final Map<SampleTask<?, ?>, Map<ProducingDataflowTask<?>, Set<TraceHandle>>> conditionalTraceTasks = new HashMap<>();
    // A map from observed variables to the traces from conditionals that end at them.
    private final Map<Variable<?>, Set<TraceHandle>> observedConditionalOutput = new HashMap<>();

    // A set of model generated variables whose value is fixed by either direct or later observations.
    private final Set<Variable<?>> evidenceVariables = new HashSet<>();

    // A map from tasks that generate an array from an array to traces that describe the possible histories of the array
    // in the outer array.
    private final Map<GetTask<?>, LengthTraceDesc> lengthTraces = new HashMap<>();

    // A map from variables to the traces that lead to observed fixed.
    private final Map<Variable<?>, Set<TraceHandle>> observedVariableTraces = new HashMap<>();

    /**
     * TODO currently using a variable as a means of collecting the graph, a neater way would be nice, but I don't want
     * state to leak between programs, so some form of context object would be required, and a clean way of adding that
     * to every variable in a given graph, but not every variable is not currently clear to me.
     */
    private TracesImplementation(CompilationDesc compDesc, Variable<?>[] vs) {
        constructTraces(compDesc, vs);
    }

    public static Traces getTraces(CompilationDesc compDesc, Variable<?>... vs) {
        // Apply DAG transformations before we start the rest of the compilation.
        DAGTransformations.apply(vs);
        return new TracesImplementation(compDesc, vs);
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

    private void constructTraces(CompilationDesc compDesc, Variable<?>[] vs) {
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
                if(v.isObserved()) {
                    toProcess.add(v.getObservation().source);
                    observedVariables.add(v.getObservation().target);
                }

                // constructed inputs.
                if(v.getParent().getType() == DFType.CONSTRUCT_INPUT) {
                    // Extra call as if no RV is reached, this variable will never be added with a
                    // trace.
                    observedVariables.add(v);
                }

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

        /*
         * Iterate through the variables starting trace generation. For anything other than random variables, observed
         * variables, and terminal variables this will do nothing, but by constructing it like this was can add in other
         * things we want to save just by overriding the method in the class.
         */
        Set<Scope> scopes = new HashSet<>();
        for(Variable<?> v:allVariables) {
            v.constructTrace(dagInfo);
            scopes.add(v.scope());
            ProducingDataflowTask<?> d = v.getParent();
            if(d.getType() == DFType.IF_ASSIGNMENT) {
                IfElseAssignmentTask<?> t = (IfElseAssignmentTask<?>) d;
                scopes.add(t.ifScope);
                scopes.add(t.ifScope.elseScope);
            }
        }

        for(Scope s:scopes)
            s.getScopeCondition().constructTrace(dagInfo::addScopeConstraint);

        // set non-deterministic and distribution flags.
        for(TraceSinkPair p:dagInfo.allTraces())
            setFlags(p);

        // Now the determinisitic variables are know propagate the variables whose value is determined by observations
        propagateObservations(compDesc);

        // Truncate any traces that go via a variable that is fixed by an observation that was not fixing the source of
        // the trace.
        dagInfo.filterTraces();

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
        for(TraceSinkPair p:dagInfo.scopeConstraintVarTraces())
            addScopeConstraintChild(p);

        constructDependencies(dagInfo);

        constructArrayLengthTraces(dagInfo);

        constructInputSets();

        setUniqueNames();
    }

    private class ObservationTracking {
        final CompilationDesc compDesc;

        private final Queue<Variable<?>> toFix = new LinkedList<>();
        private final Map<Variable<?>, Set<ObserveVariableTask<?>>> observations = new HashMap<>();
        private final Map<Variable<?>, Set<ObserveVariableTask<?>>> possibleObservations = new HashMap<>();
        private boolean possibleObs = false;

        // A map to track observations that are only possible for each variable. Variable observations that are not in
        // this map are definite.
        private final Set<Variable<?>> setPossibleObservations = new HashSet<>();

        ObservationTracking(CompilationDesc compDesc) {
            this.compDesc = compDesc;
        }

        public void addObservation(Variable<?> v, ObserveVariableTask<?> o) {
            observations.computeIfAbsent(v, k -> {
                toFix.add(k);
                return new HashSet<>();
            }).add(o);
        }

        public void addObservations(Variable<?> v, Set<ObserveVariableTask<?>> obs) {
            observations.computeIfAbsent(v, k -> {
                toFix.add(k);
                return new HashSet<>();
            }).addAll(obs);
        }

        public Set<ObserveVariableTask<?>> getObservations(Variable<?> v) {
            return observations.remove(v);
        }

        public void addPossibleObservations(Variable<?> v, Set<ObserveVariableTask<?>> obs) {
            possibleObservations.computeIfAbsent(v, k -> {
                return new HashSet<>();
            }).addAll(obs);
        }

        public void applyPossibleObservations() {
            assert toFix.isEmpty();
            assert observations.isEmpty();
            assert !possibleObs;
            observations.putAll(possibleObservations);
            toFix.addAll(observations.keySet());
            possibleObservations.clear();
            possibleObs = true;
        }

        public void setPossibleObservations(Variable<?> v) {
            setPossibleObservations.add(v);
        }

        public boolean isPossibleObservation(Variable<?> v) {
            return setPossibleObservations.contains(v);
        }

        public boolean hasValuesToFix() {
            return !toFix.isEmpty();
        }

        public Variable<?> nextValueToFix() {
            return toFix.remove();
        }
    }

    private void propagateObservations(CompilationDesc compDesc) {
        ObservationTracking tracking = new ObservationTracking(compDesc);
        propagateKnownObservations(tracking);
        propagatePossibleObservations(tracking);
    }

    /**
     * A method to propagate the observed tasks that are known to fix the variables.
     */
    private void propagateKnownObservations(ObservationTracking tracking) {

        // Set for temporary tracking of constraints.
        Set<ObserveVariableTask<?>> constraints = new HashSet<>();

        for(Variable<?> v:observedVariables) {
            if(v.isObserved())
                tracking.addObservation(v, v.getObservation());
        }

        // Fix all the variables whose value is known.
        while(tracking.hasValuesToFix()) {
            Variable<?> v = tracking.nextValueToFix();
            boolean vIsDeterministic = isDeterministic(v);
            Set<ObserveVariableTask<?>> os = tracking.getObservations(v);

            // Check for errors.
            if(vIsDeterministic) {
                for(ObserveVariableTask<?> o:os) {
                    String targetName = getVarName(o.target);
                    String outputName = getVarName(v);

                    tracking.compDesc.warnings.add(new SandwoodModelException(
                            "Variable \"" + outputName + "\" is deterministic. Setting the value of variable \""
                                    + targetName + "\" constrains this variable. There could be a contradiction.",
                            v.getLocation()));
                }
            } else if(v.isObserved() && (os.size() != 1 || !os.contains(v.getObservation()))) {
                for(ObserveVariableTask<?> o:os) {
                    if(o != v.getObservation()) {
                        String targetName = getVarName(o.target);
                        String outputName = getVarName(v);

                        tracking.compDesc.errors.add(new SandwoodModelException(
                                "Variable \"" + outputName
                                        + "\" is directly observed, and has its value fixed by the observation of \""
                                        + targetName + "\". This is not allowed as there could be a contradiction.",
                                v.getLocation()));
                    }
                }
            } else {
                // Fix the value.
                v.setFixedByObservations(os);

                // Test if any consumers should be fixed
                for(DataflowTask<?> d:v.getConsumers()) {
                    if(d instanceof ProducingDataflowTask) {
                        ProducingDataflowTask<?> p = (ProducingDataflowTask<?>) d;

                        switch(p.getType()) {
                            case GET: {
                                GetTask<?> gt = (GetTask<?>) p;
                                // If the array and index is fixed, then so is the output.
                                if(v == gt.array && (gt.index.isDeterministic() || gt.index.isFixed())) {
                                    Variable<?> output = gt.getOutput();
                                    // At this stage if the value is set it is a potential contradiction as the array is
                                    // set in its entirety.
                                    if(output.isFixed()) {
                                        Set<ObserveVariableTask<?>> unsetTasks = new HashSet<>(os);
                                        unsetTasks.removeAll(gt.index.getFixingObservations());
                                        unsetTasks.removeAll(output.getFixingObservations());
                                        observedContradictionError(tracking, v, unsetTasks);
                                    } else if(!output.getCurrentInstance().isDeterministic()) {
                                        tracking.addObservations(output, os);
                                        tracking.addObservations(output, gt.index.getFixingObservations());
                                    }
                                }
                                break;
                            }
                            case PUT: {
                                PutTask<?> pt = (PutTask<?>) p;
                                // If the array is fixed, it is still fixed as the output of the put task.
                                if(pt.array == v) {
                                    ArrayVariable<?> output = pt.getOutput();
                                    constraints.clear();
                                    constraints.addAll(os);
                                    constraints.removeAll(output.getFixingObservations());
                                    if(!constraints.isEmpty())
                                        tracking.addObservations(output, constraints);
                                } else if(v == pt.value && !pt.array.getFixingObservations()
                                        .containsAll(pt.value.getFixingObservations())) {
                                    constraints.clear();
                                    constraints.addAll(pt.value.getFixingObservations());
                                    constraints.removeAll(pt.array.getFixingObservations());
                                    constraints.removeAll(pt.scopeCondition.getFixingObservations());
                                    // If the value is fixed record it to propagate later.
                                    // Index variables are always deterministic for put tasks.
                                    if(!constraints.isEmpty())
                                        tracking.addPossibleObservations(pt.array, constraints);
                                } else if(v == pt.scopeCondition && pt.array.isFixed()) {
                                    constraints.clear();
                                    constraints.addAll(os);
                                    constraints.addAll(pt.array.getFixingObservations());
                                    constraints.removeAll(pt.value.getFixingObservations());
                                    if(!constraints.isEmpty())
                                        tracking.addObservations(pt.value, constraints);
                                }
                                break;
                            }
                            case SAMPLE: {
                                // Stop at sample tasks
                                break;
                            }
                            case IF_ASSIGNMENT: {
                                Variable<?> output = p.getOutput();
                                if(output.isFixed()) {
                                    IfElseAssignmentTask<?> ifElse = (IfElseAssignmentTask<?>) p;
                                    if(ifElse.guard.isDeterministic() || ifElse.guard.isFixed()) {
                                        // Calculate observations to pass
                                        Set<ObserveVariableTask<?>> unsetTasks = new HashSet<>(os);
                                        unsetTasks.addAll(output.getFixingObservations());
                                        unsetTasks.removeAll(ifElse.ifValue.getFixingObservations());
                                        unsetTasks.removeAll(ifElse.elseValue.getFixingObservations());

                                        // Try passing to the if branch
                                        if(ifElse.ifValue.isFixed())
                                            observedContradictionError(tracking, v, unsetTasks);
                                        else if(!ifElse.ifValue.isDeterministic() && !unsetTasks.isEmpty()) {
                                            Variable<?> input = ifElse.ifValue;
                                            tracking.addObservations(input, unsetTasks);
                                        }

                                        // Try passing to the else branch
                                        if(ifElse.elseValue.isFixed())
                                            observedContradictionError(tracking, v, unsetTasks);
                                        else if(!ifElse.ifValue.isDeterministic() && !unsetTasks.isEmpty()) {
                                            Variable<?> input = ifElse.elseValue;
                                            tracking.addObservations(input, unsetTasks);
                                        }
                                    }
                                } else {
                                    fixOutput(tracking, constraints, v, p, output);
                                }
                                break;
                            }
                            default: {
                                // Calculate the values to propagate
                                Variable<?> output = p.getOutput();
                                if(!(output.getType() instanceof VariableType.RandomVariableType)) {
                                    // If the output is fixed look to see if an input can be fixed
                                    if(output.isFixed()) {
                                        fixFreeInput(tracking, v, output.getFixingObservations(), p);
                                    } else {
                                        fixOutput(tracking, constraints, v, p, output);
                                    }
                                }
                                break;
                            }
                        }
                    }
                }

                // Test if any of the inputs are now fixed.
                ProducingDataflowTask<?> p = v.getParent();
                switch(p.getType()) {
                    case GET: {
                        GetTask<?> gt = (GetTask<?>) p;
                        if(gt.index.isDeterministic() || gt.index.isFixed()) {
                            // At this point if the array is fixed the whole array is fixed, so there is an error in the
                            // model.
                            if(gt.array.getCurrentInstance().isDeterministic() || gt.array.isFixed()) {
                                Set<ObserveVariableTask<?>> unsetTasks = new HashSet<>(os);
                                unsetTasks.removeAll(gt.index.getFixingObservations());
                                unsetTasks.removeAll(gt.array.getFixingObservations());
                                observedContradictionError(tracking, v, unsetTasks);
                            } else {
                                // Record the set of constraints that should be applied to the array as part of setting
                                // all possible constraints. Because of the indexing these cannot be propagated with
                                // such confidence.
                                constraints.clear();
                                constraints.addAll(os);
                                constraints.removeAll(gt.array.getFixingObservations());
                                if(!constraints.isEmpty())
                                    tracking.addPossibleObservations(gt.array, constraints);
                            }
                        }
                        break;
                    }
                    case PUT: {
                        PutTask<?> pt = (PutTask<?>) p;
                        // The array is fixed, so propagate this up the chain
                        constraints.clear();
                        constraints.addAll(os);
                        constraints.removeAll(pt.array.getFixingObservations());
                        if(!constraints.isEmpty())
                            tracking.addObservations(pt.array, constraints);

                        if(pt.value.isFixed()) {
                            // If the index is fixed we have potentially conflicting observations
                            Set<ObserveVariableTask<?>> unsetTasks = new HashSet<>(os);
                            unsetTasks.removeAll(pt.value.getFixingObservations());
                            observedContradictionError(tracking, pt.value, unsetTasks);
                        } else if(pt.value.getCurrentInstance().isDeterministic() && !pt.value.getType().isArray()) {
                            observedContradictionWarning(tracking, pt.value, os);
                        } else if(pt.scopeCondition.isDeterministic()) {
                            constraints.clear();
                            constraints.addAll(os);
                            constraints.removeAll(pt.value.getFixingObservations());
                            if(!constraints.isEmpty())
                                tracking.addObservations(pt.value, constraints);
                        } else if(pt.scopeCondition.isFixed()) {
                            constraints.clear();
                            constraints.addAll(os);
                            constraints.addAll(pt.scopeCondition.getFixingObservations());
                            constraints.removeAll(pt.value.getFixingObservations());
                            if(!constraints.isEmpty())
                                tracking.addObservations(pt.value, constraints);
                        }

                        break;
                    }
                    case SAMPLE: {
                        SampleTask<?, ?> s = (SampleTask<?, ?>) p;
                        RandomVariable<?, ?> rv = s.randomVariable;
                        if(rv.getType() == VariableType.Multinomial) {
                            Multinomial m = (Multinomial) rv;
                            tracking.addObservations(m.n, os);
                        }
                        break;
                    }
                    case IF_ASSIGNMENT: {
                        IfElseAssignmentTask<?> ifElse = (IfElseAssignmentTask<?>) p;
                        if(ifElse.guard.isDeterministic() || ifElse.guard.isFixed()) {
                            // Calculate observations to pass
                            Set<ObserveVariableTask<?>> unsetTasks = new HashSet<>(os);
                            unsetTasks.removeAll(ifElse.ifValue.getFixingObservations());
                            unsetTasks.removeAll(ifElse.elseValue.getFixingObservations());

                            // Try passing to the if branch
                            if(ifElse.ifValue.isFixed())
                                observedContradictionError(tracking, v, unsetTasks);
                            else if(!ifElse.ifValue.isDeterministic() && !unsetTasks.isEmpty())
                                tracking.addObservations(ifElse.ifValue, unsetTasks);

                            // Try passing to the else branch
                            if(ifElse.elseValue.isFixed())
                                observedContradictionError(tracking, v, unsetTasks);
                            else if(!ifElse.ifValue.isDeterministic() && !unsetTasks.isEmpty())
                                tracking.addObservations(ifElse.elseValue, unsetTasks);
                        }
                        break;
                    }
                    default: {
                        if(v.getParent().getType() != DFType.ARRAY_CONSTRUCTOR) {
                            // See if an input can be fixed.
                            fixFreeInput(tracking, v, os, p);
                        }
                        break;
                    }
                }
            }
        }
    }

    private void fixOutput(ObservationTracking tracking, Set<ObserveVariableTask<?>> constraints, Variable<?> v,
            ProducingDataflowTask<?> p, Variable<?> output) {
        // If all the inputs are fixed, propagate the union of the observations to the output.
        constraints.clear();
        boolean fixedInputs = true;
        for(Variable<?> input:p.getInputs())
            if(!input.isDeterministic()) {
                if(input.isFixed()) {
                    constraints.addAll(v.getFixingObservations());
                } else {
                    fixedInputs = false;
                    break;
                }
            }
        // All the inputs to this variable are fixed, so the value of the variable is known.
        if(fixedInputs) {
            tracking.addObservations(output, constraints);
        }
    }

    private void fixFreeInput(ObservationTracking tracking, Variable<?> v, Set<ObserveVariableTask<?>> os,
            ProducingDataflowTask<?> p) {
        int pos = -1;
        boolean multipleFreeInputs = false;
        Set<ObserveVariableTask<?>> unsetTasks = new HashSet<>(os);
        int count = p.getInputsCount();
        boolean vIsInput = false;
        for(int i = 0; i < count; i++) {
            Variable<?> input = p.getInput(i);
            if(input == v) {
                vIsInput = true;
            } else {
                if(!input.isDeterministic()) {
                    if(input.isFixed()) {
                        unsetTasks.removeAll(input.getFixingObservations());
                    } else {
                        // if there is more than 1 non deterministic input RECORD AND BREAK
                        if(pos != -1) {
                            multipleFreeInputs = true;
                            break;
                        } else {
                            // Otherwise save the position.
                            pos = i;
                        }
                    }
                }
            }
        }

        // If a position was not found to update and this is not an array that was updated elsewhere.
        if(pos == -1) {
            if(vIsInput)
                unsetTasks.removeAll(p.getOutput().getFixingObservations());
            observedContradictionError(tracking, v, unsetTasks);
        } else if(!unsetTasks.isEmpty() && !multipleFreeInputs && p.checkInversionError(pos) == null) {
            // if there is just 1 unset input and that is invertible, propagate the setting of the
            // variable.
            tracking.addObservations(p.getInput(pos), unsetTasks);
        }
    }

    /**
     * A method to propagate possible observations. All variables that have observations will be fixed, but not
     * necessarily by the observation provided. This occurs because observations added to arrays will mean that the
     * whole array is fixed, but it may take multiple observations to fix the whole array, when an array element to
     * recovered, it will be fixed, but which observation fixed it is not as clear. This means that errors cannot be as
     * definite as in the known observations case. A particular wrinkle is a value from an observed array may be fixed
     * by existing observations from the array, or maybe waiting for an observation to propagate across from another
     * array.
     * 
     * @param tracking
     */
    private void propagatePossibleObservations(ObservationTracking tracking) {
        tracking.applyPossibleObservations();

        // Set for temporary tracking of constraints.
        Set<ObserveVariableTask<?>> constraints = new HashSet<>();

        // Fix all the variables whose value is known.
        while(tracking.hasValuesToFix()) {
            Variable<?> v = tracking.nextValueToFix();
            boolean vIsDeterministic = isDeterministic(v);
            Set<ObserveVariableTask<?>> os = tracking.getObservations(v);

            // Check for errors.
            if(vIsDeterministic) {
                for(ObserveVariableTask<?> o:os) {
                    String targetName = getVarName(o.target);
                    String outputName = getVarName(v);

                    tracking.compDesc.warnings.add(new SandwoodModelException(
                            "Variable \"" + outputName + "\" is deterministic. Setting the value of variable \""
                                    + targetName + "\" constrains this variable. There could be a contradiction.",
                            v.getLocation()));
                }
            } else {
                // Fix the value.
                // TODO create a data structure that removes the need for this test.
                os.removeAll(v.getFixingObservations());

                if(!os.isEmpty()) {
                    // Set the possible observations
                    tracking.setPossibleObservations(v);
                    v.setFixedByObservations(os);

                    // Test if any consumers should be fixed
                    for(DataflowTask<?> d:v.getConsumers()) {
                        if(d instanceof ProducingDataflowTask) {
                            ProducingDataflowTask<?> p = (ProducingDataflowTask<?>) d;

                            switch(p.getType()) {
                                case GET: {
                                    GetTask<?> gt = (GetTask<?>) p;
                                    // If the array and index is fixed, then so is the output.
                                    if(v == gt.array && (gt.index.isDeterministic() || gt.index.isFixed())) {
                                        Variable<?> output = gt.getOutput();

                                        // If this is not deterministic, and either not fixed, or only fixed by possible
                                        // values rather than definite values propagate the possible values.
                                        if((!output.isFixed() || tracking.isPossibleObservation(output))
                                                && !output.getCurrentInstance().isDeterministic()) {
                                            tracking.addObservations(output, os);
                                            tracking.addObservations(output, gt.index.getFixingObservations());
                                        }
                                    }
                                    break;
                                }
                                case PUT: {
                                    PutTask<?> pt = (PutTask<?>) p;
                                    // If the array is fixed, it is still fixed as the output of the put task.
                                    if(pt.array == v || pt.value == v) {
                                        ArrayVariable<?> output = pt.getOutput();
                                        constraints.clear();
                                        constraints.addAll(os);
                                        constraints.removeAll(output.getFixingObservations());
                                        constraints.removeAll(pt.scopeCondition.getFixingObservations());
                                        if(!constraints.isEmpty())
                                            tracking.addObservations(output, constraints);
                                    } else if(v == pt.scopeCondition && pt.array.isFixed()) {
                                        constraints.clear();
                                        constraints.addAll(os);
                                        constraints.addAll(pt.array.getFixingObservations());
                                        constraints.removeAll(pt.value.getFixingObservations());
                                        if(!constraints.isEmpty())
                                            tracking.addObservations(pt.value, constraints);
                                    }
                                    break;
                                }
                                case SAMPLE: {
                                    // Stop at sample tasks
                                    break;
                                }
                                default: {
                                    // Calculate the values to propagate
                                    Variable<?> output = p.getOutput();
                                    if(!(output.getType() instanceof VariableType.RandomVariableType)) {
                                        if(output.isFixed() && tracking.isPossibleObservation(output)) {
                                            partialFixFreeInput(tracking, v, os, p);
                                        } else {
                                            // If all the inputs are fixed, propagate the union of the observations.
                                            constraints.clear();
                                            boolean fixedInputs = true;
                                            boolean partiallyFixedInput = false;
                                            for(Variable<?> input:p.getInputs())
                                                if(!input.isDeterministic()) {
                                                    if(input.isFixed()) {
                                                        constraints.addAll(v.getFixingObservations());
                                                        if(v != input && tracking.isPossibleObservation(input))
                                                            partiallyFixedInput = true;
                                                    } else {
                                                        fixedInputs = false;
                                                        break;
                                                    }
                                                }
                                            // All the inputs to this variable are fixed, so the value of the variable
                                            // is known.
                                            if(fixedInputs) {
                                                constraints.removeAll(output.getFixingObservations());
                                                if(partiallyFixedInput) {
                                                    // If there are multiple possible outputs to update we have to give
                                                    // up at this point.
                                                    String outputName = getVarName(v);
                                                    for(ObserveVariableTask<?> o:constraints) {
                                                        String targetName = getVarName(o.target);
                                                        tracking.compDesc.errors.add(new SandwoodModelException(
                                                                "Unable to track the fixed values from variable \""
                                                                        + outputName
                                                                        + "\" fixed by the observation of \""
                                                                        + targetName
                                                                        + "\" as there are too many possible paths from the constructing function.",
                                                                v.getLocation()));
                                                    }
                                                } else if(!constraints.isEmpty())
                                                    tracking.addPossibleObservations(output, constraints);
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                        }

                        // Test if any of the inputs are now fixed.
                        ProducingDataflowTask<?> p = v.getParent();
                        switch(p.getType()) {
                            case GET: {
                                GetTask<?> gt = (GetTask<?>) p;
                                if(gt.index.isDeterministic() || gt.index.isFixed()) {
                                    /*
                                     * At this point if the array is fixed the whole array is fixed, so there is an
                                     * error in the model.
                                     */
                                    if(gt.array.getCurrentInstance().isDeterministic()
                                            || (gt.array.isFixed() && tracking.isPossibleObservation(gt.array))) {
                                        Set<ObserveVariableTask<?>> unsetTasks = new HashSet<>(os);
                                        unsetTasks.removeAll(gt.index.getFixingObservations());
                                        unsetTasks.removeAll(gt.array.getFixingObservations());
                                        observedContradictionError(tracking, v, unsetTasks);
                                    } else {
                                        /*
                                         * Propagate the possible observations to the parent array.
                                         */
                                        constraints.clear();
                                        constraints.addAll(os);
                                        constraints.removeAll(gt.array.getFixingObservations());
                                        if(!constraints.isEmpty())
                                            tracking.addObservations(gt.array, constraints);
                                    }
                                }
                                break;
                            }
                            case PUT: {
                                PutTask<?> pt = (PutTask<?>) p;
                                // The array is fixed, so propagate this up the chain
                                constraints.clear();
                                constraints.addAll(os);
                                constraints.removeAll(pt.array.getFixingObservations());
                                if(!constraints.isEmpty())
                                    tracking.addObservations(pt.array, constraints);

                                if(pt.value.isFixed() && !tracking.isPossibleObservation(pt.value)) {
                                    // If the index is fixed we have potentially conflicting observations
                                    Set<ObserveVariableTask<?>> unsetTasks = new HashSet<>(os);
                                    unsetTasks.removeAll(pt.value.getFixingObservations());
                                    observedContradictionError(tracking, pt.value, unsetTasks);
                                } else if(pt.value.getCurrentInstance().isDeterministic()
                                        && !pt.value.getType().isArray()) {
                                    observedContradictionWarning(tracking, pt.value, os);
                                } else if(pt.scopeCondition.isDeterministic()) {
                                    constraints.clear();
                                    constraints.addAll(os);
                                    constraints.removeAll(pt.value.getFixingObservations());
                                    if(!constraints.isEmpty())
                                        tracking.addObservations(pt.value, constraints);
                                } else if(pt.scopeCondition.isFixed()) {
                                    constraints.clear();
                                    constraints.addAll(os);
                                    constraints.addAll(pt.scopeCondition.getFixingObservations());
                                    constraints.removeAll(pt.value.getFixingObservations());
                                    if(!constraints.isEmpty())
                                        tracking.addObservations(pt.value, constraints);
                                }

                                break;
                            }
                            case SAMPLE: {
                                SampleTask<?, ?> s = (SampleTask<?, ?>) p;
                                RandomVariable<?, ?> rv = s.randomVariable;
                                if(rv.getType() == VariableType.Multinomial) {
                                    Multinomial m = (Multinomial) rv;
                                    tracking.addObservations(m.n, os);
                                }
                                break;
                            }
                            case IF_ASSIGNMENT: {
                                IfElseAssignmentTask<?> ifElse = (IfElseAssignmentTask<?>) p;
                                if(ifElse.guard.isDeterministic()
                                        || (ifElse.guard.isFixed() && tracking.isPossibleObservation(ifElse.guard))) {
                                    // Calculate observations to pass
                                    Set<ObserveVariableTask<?>> unsetTasks = new HashSet<>(os);
                                    unsetTasks.removeAll(ifElse.ifValue.getFixingObservations());
                                    unsetTasks.removeAll(ifElse.elseValue.getFixingObservations());

                                    // Try passing to the if branch
                                    if(ifElse.ifValue.isFixed() && !tracking.isPossibleObservation(ifElse.ifValue))
                                        observedContradictionError(tracking, v, unsetTasks);
                                    else if(!ifElse.ifValue.isDeterministic() && !unsetTasks.isEmpty())
                                        tracking.addObservations(ifElse.ifValue, unsetTasks);

                                    // Try passing to the else branch
                                    if(ifElse.elseValue.isFixed() && !tracking.isPossibleObservation(ifElse.elseValue))
                                        observedContradictionError(tracking, v, unsetTasks);
                                    else if(!ifElse.ifValue.isDeterministic() && !unsetTasks.isEmpty()) {
                                        tracking.addObservations(ifElse.elseValue, unsetTasks);
                                    }
                                }
                                break;
                            }
                            default: {
                                if(v.getParent().getType() != DFType.ARRAY_CONSTRUCTOR)
                                    partialFixFreeInput(tracking, v, os, p);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private void partialFixFreeInput(ObservationTracking tracking, Variable<?> v, Set<ObserveVariableTask<?>> os,
            ProducingDataflowTask<?> p) {
        // See if an input can be fixed.
        int pos = -1;
        int freeInputs = 0;
        int partiallyFixedInputs = 0;
        //
        Set<ObserveVariableTask<?>> unsetTasks = new HashSet<>(os);
        int count = p.getInputsCount();
        boolean vIsInput = false;
        for(int i = 0; i < count; i++) {
            Variable<?> input = p.getInput(i);
            if(!input.isDeterministic()) {
                if(input.isFixed()) {
                    if(input == v) {
                        vIsInput = true;
                    } else {
                        unsetTasks.removeAll(input.getFixingObservations());
                        if(tracking.isPossibleObservation(input)) {
                            // record the partially fixed input
                            partiallyFixedInputs++;
                            pos = i;
                        }
                    }
                } else {
                    // Record the free input
                    freeInputs++;
                    pos = i;
                }
            }
        }

        // If a position was not found to update
        if(pos == -1) {
            if(vIsInput)
                unsetTasks.removeAll(p.getOutput().getFixingObservations());
            observedContradictionError(tracking, v, unsetTasks);
        } else if(!unsetTasks.isEmpty() && freeInputs + partiallyFixedInputs == 1
                && p.checkInversionError(pos) == null) {
            /*
             * if there is just 1 unset input and that is invertible, propagate the setting of the variable.
             */
            tracking.addObservations(p.getInput(pos), unsetTasks);
        } else if(partiallyFixedInputs > 0 && freeInputs == 0) {
            // If there are multiple possible outputs to update we have to give up at this point.
            String outputName = getVarName(v);
            for(ObserveVariableTask<?> o:unsetTasks) {
                String targetName = getVarName(o.target);
                tracking.compDesc.errors.add(new SandwoodModelException(
                        "Unable to track the fixed values from variable \"" + outputName
                                + "\" fixed by the observation of \"" + targetName
                                + "\" as there are too many possible paths from the constructing function.",
                        v.getLocation()));
            }
        }
    }

    private boolean isDeterministic(Variable<?> v) {
        if(v.getType().isArray()) {
            ArrayVariable<?> a = (ArrayVariable<?>) v;
            while(a.isSubArray())
                a = a.getOuterArrayDesc().getArray();
            return a.getCurrentInstance().isDeterministic();
        } else
            return v.isDeterministic();
    }

    private void observedContradictionError(ObservationTracking tracking, Variable<?> v,
            Set<ObserveVariableTask<?>> unsetTasks) {
        String outputName = getVarName(v);
        for(ObserveVariableTask<?> o:unsetTasks) {
            String targetName = getVarName(o.target);
            tracking.compDesc.errors.add(new SandwoodModelException("Variable " + outputName
                    + " already has all non-deterministic inputs fixed. Setting the value of variable " + targetName
                    + " constrains this variable. This is not allowed as there could be a contradiction.",
                    v.getLocation()));
        }
    }

    private void observedContradictionWarning(ObservationTracking tracking, Variable<?> v,
            Set<ObserveVariableTask<?>> unsetTasks) {
        String outputName = getVarName(v);
        for(ObserveVariableTask<?> o:unsetTasks) {
            String targetName = getVarName(o.target);
            tracking.compDesc.warnings.add(new SandwoodModelException(
                    "Variable " + outputName + " is non-deterministic and also observed. Setting the value of variable "
                            + targetName + " constrains this variable. There could be a contradiction.",
                    v.getLocation()));
        }
    }

    private String getVarName(Variable<?> var) {
        while(!var.aliasSet()) {
            ProducingDataflowTask<?> p = var.getParent();
            if(p.getType() == DFType.GET)
                var = ((GetTask<?>) p).array;
            else
                break;
        }

        return var.aliasSet() ? var.getAlias() : "unnamed";
    }

    /**
     * A method to extract from traces all the traces between the putting of an array into an array and the point that
     * array is returned by a get, or an array computed from the array is returned.
     * 
     * @param dagInfo
     */
    private void constructArrayLengthTraces(DAGInfo dagInfo) {
        List<Trace> partialTraces = new ArrayList<>();
        Trace sourceTrace = new Trace();
        for(TraceSinkPair p:dagInfo.allTraces()) {
            sourceTrace.clear();
            for(int i = 0; i < p.handle.size(); i++) {
                DataflowTaskArgDesc d = p.handle.get(i);
                sourceTrace.add(d);
                switch(d.task.getType()) {
                    case GET:
                        LengthTraceDesc ld = lengthTraces.computeIfAbsent((GetTask<?>) d.task,
                                k -> new LengthTraceDesc());
                        if(!partialTraces.isEmpty()) {
                            for(Trace t:partialTraces)
                                t.add(d);
                            Trace trace = partialTraces.remove(partialTraces.size() - 1);
                            ld.addTraceToPut(TraceHandle.getTraceHandle(trace));
                        }
                        break;
                    case GET_LENGTH:
                        partialTraces.clear();
                        break;
                    case PUT:
                        if(d.argPos == 2) {
                            PutTask<?> pt = (PutTask<?>) d.task;
                            if(pt.value.getType().isArray()) {
                                Trace trace = new Trace();
                                partialTraces.add(trace);
                                for(Trace t:partialTraces)
                                    t.add(d);
                            }
                        } else {
                            partialTraces.clear();
                        }
                        break;
                    case REDUCE_INPUT:
                        if(d.argPos == 2) {
                            for(Trace t:partialTraces)
                                t.add(d);
                        } else
                            partialTraces.clear();
                        break;
                    case REDUCTION_RETURN:
                        for(Trace t:partialTraces)
                            t.add(d);
                        break;
                    default:
                        assert partialTraces.isEmpty();
                        break;

                }
            }
            partialTraces.clear();
        }

    }

    @Override
    public LengthTraceDesc getLengthTraces(GetTask<?> t) {
        return lengthTraces.get(t);
    }

    private void constructInputSets() {
        // Index to allow the variables to be looked up by name quickly.
        Map<VariableName, Variable<?>> inputNames = new HashMap<>();
        for(Variable<?> v:observedVariables) {
            ProducingDataflowTask<?> d = v.getParent();
            if(d.getType() == DFType.CONSTRUCT_INPUT) {
                inputNames.put(v.getVarDesc().name, v);
                if(v.getType().isArray()) {
                    Variable<?> shape = ((ConstructArrayInput<?>) d).shapeVar();
                    if(shape != null)
                        inputNames.put(shape.getVarDesc().name, shape);
                }
            }
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
                if(v.isPrivate()) { // Look for outermost arrays and set them as intermediates.
                    if((v.getType().isArray() && !((ArrayVariable<?>) v).isSubArray())) {
                        v.setIntermediate();
                        if(v.isDeterministic())
                            deterministicVars.add(v);
                    }
                } else {
                    if(!v.getType().isArray() || !((ArrayVariable<?>) v).isSubArray())
                        v.setIntermediate();
                    if(v.isDeterministic())
                        deterministicVars.add(v);
                    else
                        computedVars.add(v.instanceHandle());
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

        DataflowTaskArgDesc d = handle.get(0);
        ProducingDataflowTask<?> sourceTask = d.task;
        switch(sourceTask.getType()) {
            case SAMPLE: {
                SampleTask<?, ?> sourceSample = (SampleTask<?, ?>) sourceTask;
                RandomVariable<?, ?> sourceRV = sourceSample.randomVariable;

                addVariableSource(handle, sourceSample);

                {
                    // Record the sample and all the traces leading to it.
                    allSampleTasks.add(sourceSample);
                    Map<Variable<?>, Set<TraceHandle>> sampleChildren = observedAllSampleTraces
                            .computeIfAbsent(sourceSample, k -> new LinkedHashMap<>());
                    Set<TraceHandle> sampleTraces = sampleChildren.computeIfAbsent(sink, k -> new HashSet<>());
                    sampleTraces.add(handle);
                }

                // Record conditional properties of this trace.
                DataflowTaskArgDesc conditionalGuard = recordConditionalTrace(sourceSample, handle);

                addIntermediates(handle, conditionalGuard);

                // Populate the evidence variables set.
                for(int i = handle.size() - 1; i >= 0; i--) {
                    DataflowTaskArgDesc dt = handle.get(i);
                    Variable<?> ev = dt.task.getOutput();
                    if(ev.isIntermediate() || ev.isObserved())
                        evidenceVariables.add(ev);
                    if(dt == conditionalGuard)
                        break;
                }

                if(conditionalGuard == null) {
                    Set<TraceHandle> sourceChildren = observedChildrenTraces.computeIfAbsent(sourceRV,
                            k -> new HashSet<>());
                    sourceChildren.add(handle);

                    Map<Variable<?>, Set<TraceHandle>> sampleChildren = observedUnconditionalSampleTraces
                            .computeIfAbsent(sourceSample, k -> new LinkedHashMap<>());
                    Set<TraceHandle> sampleTraces = sampleChildren.computeIfAbsent(sink, k -> new HashSet<>());
                    sampleTraces.add(handle);

                    observedSampleTasks.add(sourceSample);
                }

                addToVarToSample(sink, sourceRV);

                // Nothing progresses beyond this variable.
                if(sink.getConsumers().size() == 1 && sink.isObserved())
                    findTerminalVariables(handle);

                // Populate sampleToRVTraces and intermediateChildrenTraces
                Set<TraceHandle> setSampleVariableTraces = sampleVariableTraces.computeIfAbsent(sourceRV,
                        k -> new HashSet<>());

                SplitTrace splitTrace = splitTrace(handle);
                TraceHandle toSampleHandle = TraceHandle.getTraceHandle(splitTrace.toSample);
                setSampleVariableTraces.add(toSampleHandle);
                addToVarToSample(splitTrace.sampleVar, sourceRV);

                sampleTrace.computeIfAbsent(sourceSample,
                        k -> new SampleTraceDesc(splitTrace.sampleVar, toSampleHandle));

                addVariableObservationsTraces(handle);

                break;
            }
            case CONSTRUCT_INPUT: {
                Variable<?> output = sourceTask.getOutput();
                if(shapeParameterRequired(handle)) {
                    Variable<?> lengthVar = ((ConstructArrayInput<?>) sourceTask).shapeVar();
                    modelInputs.add(lengthVar);
                    namedVariables.put(lengthVar.getVarDesc().name, lengthVar);
                    allVariables.add(lengthVar);
                } else
                    modelInputs.add(output);
                /*
                 * Check that none of the deterministic intermediates have been removed. If they have this means that
                 * the model is consuming and conditioning on the same values. This is currently not supported as it
                 * does not allow the execution of models in cases such as linear regression. TODO In future we should
                 * weaken this by subclassing the model class and and providing models that do not support execution if
                 * this separation is not maintained.
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
            }
            case CONSTANT_BOOLEAN:
            case CONSTANT_DOUBLE:
            case CONSTANT_INT:
                break;
            default: {
                // This trace ended at an observed non input variable.

                Variable<?> observedVar = sourceTask.getInput(d.argPos);

                assert !observedVar.getFixingObservations().isEmpty();

                // Record the observed variable as a source.
                addVariableSource(handle, observedVar);

                // Nothing progresses beyond this variable.
                if(sink.getConsumers().size() == 1)
                    findTerminalVariables(handle);

                break;
            }
        }
    }

    private void addVariableObservationsTraces(TraceHandle handle) {
        // Find the index of the first observed variable.
        int end = handle.size() - 1;
        Variable<?> v = handle.get(end).task.getOutput();
        while(end != 0 && v.isFixed()) {
            end--;
            v = handle.get(end).task.getOutput();
        }

        // If the whole trace is fixed.
        if(v.isFixed())
            return;

        // Move end back to the fixed variable.
        end++;

        // Move the start back to the first variable that can be calculated by inverting the trace
        int start = end;
        if(start != 0) {
            DataflowTaskArgDesc d = handle.get(start);
            while(start != 0 && d.task.checkInversionError(d.argPos) == null)
                d = handle.get(--start);
        }

        // Search for sample and intermediate variables that link to the observed variable
        while(start < end) {
            v = handle.get(start).task.getOutput();
            if(v.isIntermediate() || v.isSample()) {
                TraceHandle t = handle.subTrace(start, end + 1);
                Set<TraceHandle> ts = observedVariableTraces.computeIfAbsent(v, k -> new HashSet<>());
                ts.add(t);
            }
            start++;
        }
    }

    @Override
    public Set<TraceHandle> getVariableObservationsTraces(Variable<?> v) {
        return observedVariableTraces.getOrDefault(v, Collections.emptySet());
    }

    private boolean shapeParameterRequired(TraceHandle handle) {
        ProducingDataflowTask<?> t = handle.get(0).task;
        if(t.getOutputType().isArray()) {
            if(((ConstructArrayInput<?>) t).shapeVar() == null)
                return false;
            int length = handle.size();
            for(int i = 1; i < length; i++) {
                DataflowTaskArgDesc d = handle.get(i);
                switch(d.task.getType()) {
                    case GET:
                        if(d.argPos != 0)
                            return false;
                        break;
                    case GET_LENGTH:
                        return true;
                    default:
                        return false;
                }
            }
        }
        return false;
    }

    private DataflowTaskArgDesc recordConditionalTrace(SampleTask<?, ?> sourceSample, TraceHandle handle) {
        DataflowTaskArgDesc conditionalGuard = null;
        for(int i = handle.size() - 1; i >= 0; i--) {
            DataflowTaskArgDesc d = handle.get(i);
            switch(d.task.getType()) {
                case IF_ASSIGNMENT: {
                    IfElseAssignmentTask<?> ifElse = (IfElseAssignmentTask<?>) d.task;
                    if(!ifElse.guard.isDeterministic()) {
                        // Store this value of the trace does not go via the condition, otherwise set the inferGuard
                        // flag
                        if(d.argPos == 0) {
                            conditionalGuard = d;
                            Map<ProducingDataflowTask<?>, Set<TraceHandle>> m = conditionalTraceTasks
                                    .computeIfAbsent(sourceSample, k -> new HashMap<>());
                            Set<TraceHandle> traces = m.computeIfAbsent(d.task, k -> new HashSet<>());
                            Trace trace = handle.getTrace();
                            while(trace.peek() != d)
                                trace.pop();
                            traces.add(TraceHandle.getTraceHandle(trace));

                            // Record the trace to the break point for the assignment of a deterministic value.
                            if(ifElse.ifValue.isDeterministic())
                                recordConditionalTrace(d, handle, 1);

                            // Record the trace to the break point for the assignment of a deterministic value.
                            if(ifElse.elseValue.isDeterministic())
                                recordConditionalTrace(d, handle, 2);

                        } else {
                            recordConditionalTrace(d, handle);
                        }
                    }
                    break;
                }
                case PUT:
                    PutTask<?> putTask = (PutTask<?>) d.task;
                    if(!putTask.scopeCondition.isDeterministic()) {
                        // Store this value of the trace does not go via the condition, otherwise set the inferGuard
                        // flag
                        if(d.argPos == 3) {
                            conditionalGuard = d;
                            Map<ProducingDataflowTask<?>, Set<TraceHandle>> m = conditionalTraceTasks
                                    .computeIfAbsent(sourceSample, k -> new HashMap<>());
                            Set<TraceHandle> traces = m.computeIfAbsent(d.task, k -> new HashSet<>());
                            Trace trace = handle.getTrace();
                            while(trace.peek() != d)
                                trace.pop();
                            traces.add(TraceHandle.getTraceHandle(trace));

                            // Record the trace to the break point for the assignment of a deterministic value.
                            if(putTask.value.isDeterministic())
                                recordConditionalTrace(d, handle, 2);
                        } else {
                            recordConditionalTrace(d, handle);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return conditionalGuard;
    }

    /**
     * Method to split a trace around a provided point storing the trace before and after this point.
     * 
     * @param breakPoint The point to split the trace at.
     * @param h          The trace to split.
     */
    private void recordConditionalTrace(DataflowTaskArgDesc breakPoint, TraceHandle h) {
        Trace end = new Trace();
        int i = 0;
        DataflowTaskArgDesc d = h.get(i++);
        while(d != breakPoint) {
            end.add(d);
            d = h.get(i++);
        }
        end.add(d);

        Trace start = new Trace();
        i--;
        while(i < h.size())
            start.add(h.get(i++));

        SplitConditionalTraces s = conditionalTraces.computeIfAbsent(breakPoint.task,
                k -> new SplitConditionalTraces());
        s.addSinkToConditional(TraceHandle.getTraceHandle(start));
        s.addConditionalToSource(d, (SampleTask<?, ?>) end.get(0).task, TraceHandle.getTraceHandle(end));

        recordObservedConditionalTraces(h);
    }

    /**
     * Method to split a trace with a deterministic input on one of its arguments. The trace is split around a provided
     * point storing the trace before and a description of the break point using the provided argument position to
     * create a new argument description. The to source trace is empty as there is no need to invert a deterministic
     * trace.
     * 
     * @param breakPoint The point to split the trace at.
     * @param h          The trace to split.
     * @param argPos     The argument position of the non deterministic argument.
     */
    private void recordConditionalTrace(DataflowTaskArgDesc breakPoint, TraceHandle h, int argPos) {
        int i = 0;
        DataflowTaskArgDesc d = h.get(i++);
        while(d != breakPoint)
            d = h.get(i++);

        Trace start = new Trace();
        start.add(new DataflowTaskArgDesc(breakPoint.task, argPos));
        while(i < h.size())
            start.add(h.get(i++));

        SplitConditionalTraces s = conditionalTraces.computeIfAbsent(breakPoint.task,
                k -> new SplitConditionalTraces());
        s.addSinkToConditional(TraceHandle.getTraceHandle(start));

        recordObservedConditionalTraces(h);
    }

    /**
     * Method to test if a trace has an observed value as a sink and record the trace from the observation to the last
     * value that would be set by the observation.
     * 
     * @param h The trace handle to process.
     */
    private void recordObservedConditionalTraces(TraceHandle h) {
        int i;
        DataflowTaskArgDesc d;
        Variable<?> sink = h.peek().task.getOutput();
        if(sink.isObserved()) {
            // find the break point
            int size = h.size();
            i = size;
            while(true) {
                d = h.get(--i);
                DFType type = d.task.getType();
                if(type == DFType.IF_ASSIGNMENT) {
                    IfElseAssignmentTask<?> ifElse = (IfElseAssignmentTask<?>) d.task;
                    if(!ifElse.guard.isDeterministic())
                        break;
                } else if(type == DFType.PUT) {
                    PutTask<?> putTask = (PutTask<?>) d.task;
                    if(!putTask.scopeCondition.isDeterministic()) {
                        break;
                    }
                }
            }

            // Copy the relevant portion of the trace into a new trace and save it.
            Trace t = new Trace();
            while(i < size)
                t.add(h.get(i++));

            observedConditionalOutput.computeIfAbsent(sink, k -> new HashSet<>()).add(TraceHandle.getTraceHandle(t));
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
        DataflowTaskArgDesc d = handle.get(0);
        ProducingDataflowTask<?> sourceTask = d.task;
        switch(sourceTask.getType()) {
            case SAMPLE: {
                // Get the sample task
                SampleTask<?, ?> sourceSample = (SampleTask<?, ?>) sourceTask;
                RandomVariable<?, ?> sourceRV = sourceSample.randomVariable;

                addIntermediates(handle);
                addVariableSource(handle, sourceSample);

                allSampleTasks.add(sourceSample);

                if(observedSampleTasks.contains(sourceTask)) {
                    /*
                     * The variable that would be inferred for this sample task is observed. As such the value will not
                     * be inferred, so this trace should not be recorded. However, a warning will be recorded to ensure
                     * that the user knows the value must be set as it won't be inferred. For example we will not allow
                     * part of an array to be observed and the rest to be inferred.
                     * 
                     * TODO We may want to weaken this constraint in the future by the addition of a test to see if a
                     * given value is observed before we attempt to infer it.
                     * 
                     * TODO Make this only trigger if the observed value has a possibility of being partially observed.
                     * i.e. it is a part of an array that is being observed.
                     */

                    // Get all the named variables and arrays in the trace from the observed variable.
                    SampleTraceDesc traceDesc = sampleTrace.get(sourceTask);
                    Set<Variable<?>> vs = new HashSet<>();
                    for(DataflowTaskArgDesc dt:traceDesc.traceToSampleVariable) {
                        Variable<?> v = dt.task.getOutput();
                        if(v.aliasSet() || v.getType().isArray())
                            vs.add(v);
                    }

                    // For each variable in the passed trace check if it is in this set. If it is record it to report in
                    // the compiler warning.
                    for(DataflowTaskArgDesc dt:handle) {
                        Variable<?> v = dt.task.getOutput();
                        if(vs.contains(v)) {
                            if(v.aliasSet())
                                consumedObservedVariables.add(v);

                            Type<?> t = v.getType();
                            if(t.isArray()) {
                                ArrayVariable<?> a = (ArrayVariable<?>) v;
                                while(a.isSubArray()) {
                                    OuterArrayDesc<?> o = a.getOuterArrayDesc();
                                    a = o.getArray();
                                    if(a.aliasSet())
                                        consumedObservedVariables.add(a);
                                }
                            }
                        }
                    }
                } else {
                    DataflowTaskArgDesc conditionalGuard = recordConditionalTrace(sourceSample, handle);

                    if(conditionalGuard == null) {
                        Map<RandomVariable<?, ?>, Set<TraceHandle>> rvTraces = tracesRVToSampleTask
                                .computeIfAbsent(sourceSample, k -> new LinkedHashMap<>());
                        Set<TraceHandle> traces = rvTraces.computeIfAbsent(sink, k -> new LinkedHashSet<>());
                        traces.add(handle);
                    }
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

                // Populate sampleToRVTraces and sampleVariableTraces
                Set<TraceHandle> setSampleVariableTraces = sampleVariableTraces.computeIfAbsent(sourceRV,
                        k -> new HashSet<>());

                SplitTrace splitTrace = splitTrace(handle);
                TraceHandle toSampleHandle = TraceHandle.getTraceHandle(splitTrace.toSample);
                setSampleVariableTraces.add(toSampleHandle);
                addToVarToSample(splitTrace.sampleVar, sourceRV);

                SampleTraceDesc sampleTraceDesc = sampleTrace.computeIfAbsent(sourceSample,
                        k -> new SampleTraceDesc(splitTrace.sampleVar, toSampleHandle));
                sampleTraceDesc.toConsumingRV.put(sink, TraceHandle.getTraceHandle(splitTrace.fromConsumer));
                break;
            }
            case CONSTRUCT_INPUT: {
                if(shapeParameterRequired(handle)) {
                    Variable<?> lengthVar = ((ConstructArrayInput<?>) sourceTask).shapeVar();
                    modelInputs.add(lengthVar);
                    namedVariables.put(lengthVar.getVarDesc().name, lengthVar);
                    allVariables.add(lengthVar);
                } else
                    modelInputs.add(sourceTask.getOutput());
                break;
            }
            case CONSTANT_BOOLEAN:
            case CONSTANT_DOUBLE:
            case CONSTANT_INT:
                break;
            default: {
                // This trace ended at an observed non input variable.

                Variable<?> observedVar = sourceTask.getInput(d.argPos);

                assert observedVar.isFixed();

                // Record the observed variable as a source.
                addVariableSource(handle, observedVar);

                break;
            }
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
        // Add it to the to sample trace
        s.toSample.add(d);

        // Record its scopes, and its scopes stopping at the first conditional as variables outside of this scope will
        // be set by a put task with a scope condition including the guard, or a conditional assignment.
        Set<Scope> sampleScopes = new HashSet<>();
        Set<Scope> innerScopes = new HashSet<>();
        {
            boolean collectInner = true;
            Scope sampleScope = d.task.scope();
            while(sampleScope != GlobalScope.scope) {
                sampleScopes.add(sampleScope);
                if(collectInner)
                    innerScopes.add(sampleScope);

                switch(sampleScope.getScopeType()) {
                    case IF:
                    case ELSE:
                        collectInner = false;
                        break;
                    default:
                        break;
                }

                sampleScope = sampleScope.getEnclosingScope();
            }

            sampleScopes.add(sampleScope);
            if(collectInner)
                innerScopes.add(sampleScope);
        }

        // Set the trace position ready to traverse the rest of the trace.
        int tracePos = 1;
        // Traverse through until we leave the scope or the traces diverge.
        // The value of d will always be the last task added to the sample trace.
        while(tracePos < noElements) {
            DataflowTaskArgDesc candidate = consumerTrace.get(tracePos);
            if(constructableSampleVariable(candidate, sampleScopes, innerScopes)) {
                s.toSample.add(candidate);
                tracePos++;
            } else
                break;
        }

        // Add any further put operations that store the value as long as this
        // is still a single stream and not moving out of a conditional guard of the sample.
        if(d.task.getType() == DFType.PUT && d.argPos == 2) {
            while(tracePos < noElements) {
                DataflowTaskArgDesc candidate = consumerTrace.get(tracePos);
                DFType t = candidate.task.getType();
                Variable<?> output = candidate.task.getOutput();
                if(t == DFType.PUT && d.argPos == 2 && !variableInScope(output, sampleScopes, innerScopes)) {
                    s.toSample.add(candidate);
                    tracePos++;
                } else
                    break;
            }
        }

        // Get the last task in the to sample trace as this will generate the output
        // that the sample variable should be set to.
        Variable<?> v = s.toSample.peek().task.getOutput();
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

    /**
     * Method to test if this variable could be constructed from a sample value stored later in the graph.
     * 
     * @param d
     * @param sampleScopes
     * @param innerScopes
     * @return
     */
    private boolean constructableSampleVariable(DataflowTaskArgDesc d, Set<Scope> sampleScopes,
            Set<Scope> innerScopes) {
        ProducingDataflowTask<?> task = d.task;
        Variable<?> output = task.getOutput();
        Variable<?> input = task.getInput(d.argPos);

        boolean invertible = d.task.checkInversionError(d.argPos) == null;
        boolean inputIntermediate = input.isIntermediate();
        boolean inputSample = input.isSample();
        boolean singleStream = singleStream(input);
        boolean singleSample = !usesOtherSampledValue(d);
        boolean varInScope = variableInScope(output, sampleScopes, innerScopes);

        return invertible && !inputIntermediate && !inputSample && singleStream && singleSample && varInScope;
    }

    /**
     * A guard to test that the output is still in scope an if this passes through a scope that has the potential to not
     * execute the value on the outside of this scope is fixed so cannot change as a result of whether the scope
     * executed or not.
     * 
     * @param output
     * @param sampleScopes
     * @param innerScopes
     * @return
     */
    private boolean variableInScope(Variable<?> output, Set<Scope> sampleScopes, Set<Scope> innerScopes) {
        return ((sampleScopes.contains(output.scope()) && output.isFixed()) || innerScopes.contains(output.scope()));
    }

    private boolean singleStream(Variable<?> v) {
        int consumers = 0;
        for(DataflowTask<?> d:v.getConsumers()) {
            switch(d.getType()) {
                case GET:
                    if(!((GetTask<?>) d).isImplicit())
                        consumers++;
                    break;
                case OBSERVE_VARIABLE:
                    break;
                case PUT:
                    if(((PutTask<?>) d).array != v)
                        consumers++;
                    break;
                default:
                    consumers++;
                    break;
            }
        }
        return consumers <= 1;
    }

    private boolean usesOtherSampledValue(DataflowTaskArgDesc d) {
        int size = d.task.getInputsCount();
        boolean isPut = d.task.getType() == DFType.PUT;
        for(int i = 0; i < size; i++) {
            if((i != d.argPos && !(isPut && i == 0)) && !d.task.getInput(i).isDeterministic())
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
    public Map<Variable<?>, Set<TraceHandle>> getUnconditionalObservedTraces(SampleTask<?, ?> source) {
        return observedUnconditionalSampleTraces.get(source);
    }

    @Override
    public Map<Variable<?>, Set<TraceHandle>> getAllObservedTraces(SampleTask<?, ?> source) {
        return observedAllSampleTraces.get(source);
    }

    @Override
    public List<TraceHandle> getSampleVariableTraces(RandomVariable<?, ?> source) {
        Set<TraceHandle> result = sampleVariableTraces.get(source);
        if(result != null) {
            List<TraceHandle> toReturn = new ArrayList<>(result);
            Collections.sort(toReturn);
            return toReturn;
        } else
            return Collections.emptyList();
    }

    @Override
    public IntermediateDesc getIntermediates(SampleTask<?, ?> sample) {
        return sampleToIntermediates.computeIfAbsent(sample, k -> new IntermediateDesc(sample));
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
        Set<Variable<?>> result = requiredIntermediates.get(v.instanceHandle());
        if(result == null)
            return Collections.emptySet();
        else
            return result;
    }

    private void addTerminalChild(TraceSinkPair p) {
        TraceHandle handle = p.handle;
        Variable<?> sink = p.sink;
        DataflowTaskArgDesc d = handle.get(0);
        ProducingDataflowTask<?> sourceTask = d.task;

        findTerminalVariables(handle);

        switch(sourceTask.getType()) {
            case SAMPLE: {
                SampleTask<?, ?> sourceSample = (SampleTask<?, ?>) sourceTask;
                RandomVariable<?, ?> sourceRV = sourceSample.randomVariable;
                addIntermediates(handle);
                allSampleTasks.add(sourceSample);
                sink = sink.getCurrentInstance();
                findTerminalVariables(handle);
                addToVarToSample(sink, sourceRV);
                addVariableSource(handle, sourceSample);

                SplitTrace splitTrace = splitTrace(handle);

                TraceHandle toSampleHandle = TraceHandle.getTraceHandle(splitTrace.toSample);
                SampleTraceDesc intermediateDesc = sampleTrace.get(sourceSample);
                if(intermediateDesc == null) {
                    intermediateDesc = new SampleTraceDesc(splitTrace.sampleVar, toSampleHandle);
                    sampleTrace.put(sourceSample, intermediateDesc);
                }
                break;
            }
            case CONSTRUCT_INPUT: {
                if(shapeParameterRequired(handle)) {
                    Variable<?> lengthVar = ((ConstructArrayInput<?>) sourceTask).shapeVar();
                    modelInputs.add(lengthVar);
                    namedVariables.put(lengthVar.getVarDesc().name, lengthVar);
                    allVariables.add(lengthVar);
                } else
                    modelInputs.add(sourceTask.getOutput());
                break;
            }
            case CONSTANT_BOOLEAN:
            case CONSTANT_DOUBLE:
            case CONSTANT_INT:
                break;
            default: {
                // This trace ended at an observed non input variable.
                Variable<?> observedVar = sourceTask.getInput(d.argPos);
                assert observedVar.isFixed();

                // Record the observed variable as a source.
                addVariableSource(handle, observedVar);
                break;
            }
        }
    }

    private void addScopeConstraintChild(TraceSinkPair p) {
        TraceHandle handle = p.handle;
        DataflowTaskArgDesc d = handle.get(0);
        ProducingDataflowTask<?> sourceTask = d.task;
        // Record the observed variable as a source.
        if(sourceTask.getType() == DFType.SAMPLE)
            addVariableSource(handle, (SampleTask<?, ?>) sourceTask);
    }

    private void setFlags(TraceSinkPair p) {
        if(!p.handle.get(0).task.isDeterministic()) {
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
        Set<SampleTask<?, ?>> toReturn = new HashSet<>(tracesRVToSampleTask.keySet());
        toReturn.addAll(conditionalTraceTasks.keySet());
        return toReturn;
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
        return tracesRVToSampleTask.getOrDefault(sampleTask, Collections.emptyMap());
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
        addIntermediates(h, null);
    }

    /**
     * A method to record the variables set by a sample task.
     * 
     * @param h           The trace from the sample task.
     * @param changePoint The point that a trace stops being observed, null if there is no point where the trace changes
     *                    from observed to unobserved.
     */
    private void addIntermediates(TraceHandle h, DataflowTaskArgDesc changePoint) {
        SampleTask<?, ?> sample = (SampleTask<?, ?>) h.get(0).task;
        Set<Variable<?>> intermediateVars = h.getIntermediates();

        IntermediateDesc intermediates = sampleToIntermediates.computeIfAbsent(sample,
                k -> new IntermediateDesc(sample));
        intermediates.addVariables(intermediateVars, h, changePoint);

        for(Variable<?> v:intermediateVars) {
            Set<SampleTask<?, ?>> s = intermediateSampleTaskDependencies.computeIfAbsent(v, k -> new LinkedHashSet<>());
            s.add(sample);
        }
    }

    private void addVariableSource(TraceHandle t, SampleTask<?, ?> sample) {
        int size = t.size();
        for(int i = 0; i < size; i++) {
            DataflowTask<?> d = t.get(i).task;
            Variable<?> v = d.getOutput();
            Set<SampleTask<?, ?>> s = sourceTasks.computeIfAbsent(v, k -> new HashSet<>());
            s.add(sample);
        }
    }

    private void addVariableSource(TraceHandle t, Variable<?> source) {
        assert source.isFixed();
        int max = t.size();
        for(int i = 0; i < max; i++) {
            DataflowTask<?> d = t.get(i).task;
            Variable<?> v = d.getOutput();
            Set<Variable<?>> s = sourceObservedVars.computeIfAbsent(v, k -> new HashSet<>());
            s.add(source);
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
            DataflowTaskArgDesc d = p.handle.get(0);
            if(d.task.getInputsCount() > 0) {
                Variable<?> sourceVar = d.task.getInput(d.argPos);
                if(sourceVar.isFixed())
                    requirements.add(sourceVar);
            }
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
    public Set<Variable<?>> getSourceObservedVariables(Variable<?> v) {
        Set<Variable<?>> toReturn = new HashSet<>();
        Set<Variable<?>> result = sourceObservedVars.get(v);
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

    @Override
    public Set<Variable<?>> getReadObservedVariables() {
        return consumedObservedVariables;
    }

    @Override
    public Map<ProducingDataflowTask<?>, Set<TraceHandle>> getTracesToConditionals(SampleTask<?, ?> sampleTask) {
        return conditionalTraceTasks.getOrDefault(sampleTask, Collections.emptyMap());
    }

    @Override
    public SplitConditionalTraces getSplitConditionalTraces(ProducingDataflowTask<?> task) {
        return conditionalTraces.get(task);
    }

    @Override
    public Map<Variable<?>, Set<TraceHandle>> getObservedConditionTraces() {
        return observedConditionalOutput;
    }

    @Override
    public Set<Variable<?>> getEvidenceVariables() {
        return evidenceVariables;
    }
}
