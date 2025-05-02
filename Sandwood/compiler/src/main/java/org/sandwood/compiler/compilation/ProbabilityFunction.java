/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation;

import static org.sandwood.compiler.compilation.util.TreeUtils.getIndirectValue;
import static org.sandwood.compiler.compilation.util.TreeUtils.getScopeArgs;
import static org.sandwood.compiler.compilation.util.TreeUtils.putIndirectValue;
import static org.sandwood.compiler.compilation.util.TreeUtils.toArgTrees;
import static org.sandwood.compiler.traces.guards.ScopeConstructor.Guards.NO_GUARDS;
import static org.sandwood.compiler.trees.irTree.IRTree.addDD;
import static org.sandwood.compiler.trees.irTree.IRTree.and;
import static org.sandwood.compiler.trees.irTree.IRTree.constant;
import static org.sandwood.compiler.trees.irTree.IRTree.eq;
import static org.sandwood.compiler.trees.irTree.IRTree.functionCallReturn;
import static org.sandwood.compiler.trees.irTree.IRTree.ifElse;
import static org.sandwood.compiler.trees.irTree.IRTree.initializeVariable;
import static org.sandwood.compiler.trees.irTree.IRTree.load;
import static org.sandwood.compiler.trees.irTree.IRTree.log;
import static org.sandwood.compiler.trees.irTree.IRTree.negateBoolean;
import static org.sandwood.compiler.trees.irTree.IRTree.sequential;
import static org.sandwood.compiler.trees.irTree.IRTree.store;
import static org.sandwood.compiler.trees.irTree.IRTree.subtractDD;
import static org.sandwood.compiler.trees.irTree.IRTree.voidFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.sandwood.compiler.compilation.CompilationContext.AuxFunctionType;
import org.sandwood.compiler.compilation.CompilationContext.FieldType;
import org.sandwood.compiler.compilation.CompilationContext.SampleFunctionClass;
import org.sandwood.compiler.compilation.util.DAGUtils;
import org.sandwood.compiler.compilation.util.TreeUtils;
import org.sandwood.compiler.compilation.util.TreeUtils.ScopeDesc;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.ScalarVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.names.FunctionName;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.traces.TraceHandle;
import org.sandwood.compiler.traces.Traces;
import org.sandwood.compiler.traces.Traces.IntermediateDesc;
import org.sandwood.compiler.traces.Traces.SampleTraceDesc;
import org.sandwood.compiler.traces.Traces.SplitConditionalTraces;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.traces.guards.ScopeConstructor;
import org.sandwood.compiler.traces.guards.TreeBuilderInfo;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.Visibility;
import org.sandwood.compiler.trees.WrappedTree;
import org.sandwood.compiler.trees.irTree.IRSequential;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class ProbabilityFunction {
    private static class DependantVariables implements Iterable<Variable<?>> {
        final Map<Variable<?>, Set<TraceHandle>> perSampleVariables = new HashMap<>();
        final Map<Variable<?>, Set<TraceHandle>> accumulatorVariables = new HashMap<>();
        private Variable<?> sampleVariable;

        public DependantVariables(SampleTask<?, ?> sTask, CompilationContext compilationCtx) {
            sampleVariable = compilationCtx.traces.getSampleTrace(sTask).sampleVariable;
            IntermediateDesc intermediateDesc = compilationCtx.traces.getIntermediates(sTask);
            Set<Variable<?>> observedVariables = getObservedVariables(intermediateDesc);
            for(Variable<?> v:intermediateDesc.getVariables()) {
                if(v.instanceHandle() != sampleVariable.instanceHandle()) {
                    Set<TraceHandle> traces = intermediateDesc.getTraces(v);
                    traces = filterProbabilityTraces(traces, observedVariables);
                    if(!traces.isEmpty()) {
                        boolean perSampleValuesRequired = false;
                        for(TraceHandle t:traces) {
                            if(perSampleValueRequired(t)) {
                                perSampleValuesRequired = true;
                                break;
                            }
                        }

                        if(perSampleValuesRequired)
                            perSampleVariables.put(v, traces);
                        else
                            accumulatorVariables.put(v, traces);
                    }
                }
            }
        }

        private Set<Variable<?>> getObservedVariables(IntermediateDesc intermediateDesc) {
            Set<Variable<?>> observedVars = new HashSet<>();
            for(Variable<?> v:intermediateDesc.getVariables()) {
                if(v.isObserved()) {
                    for(TraceHandle h:intermediateDesc.getTraces(v)) {
                        for(DataflowTaskArgDesc d:h) {
                            Variable<?> output = d.task.getOutput();
                            if(output.isFixed())
                                observedVars.add(v);
                        }
                    }
                }
            }
            return observedVars;
        }

        private Set<TraceHandle> filterProbabilityTraces(Set<TraceHandle> traces, Set<Variable<?>> observedVariables) {
            Set<TraceHandle> filtered = new HashSet<>();
            for(TraceHandle t:traces)
                if(filterTrace(t, observedVariables))
                    filtered.add(t);
            return filtered;
        }

        /**
         * A method to filter out traces to variables the probability is accounted for by the Monte Carlo nature of the
         * sampling as this variable controls the control flow, not the value of the variable.
         * 
         * @param t
         * @param observedVariables
         * @return Returns true if the trace should be kept and false if it should be discarded.
         */
        private boolean filterTrace(TraceHandle t, Set<Variable<?>> observedVariables) {
            boolean observedTrace = t.peek().task.getOutput().isObserved();

            for(DataflowTaskArgDesc d:t) {
                Variable<?> output = d.task.getOutput();
                if(!observedTrace && observedVariables.contains(output))
                    return false;

                switch(d.task.getType()) {
                    case GET:
                        if(d.argPos != 0)
                            return false;
                        break;
                    case GET_LENGTH:
                        return false;
                    case IF_ASSIGNMENT:
                        if(d.argPos == 0)
                            return false;
                        break;
                    case PUT:
                        if(d.argPos != 2)
                            return false;
                        break;
                    default:
                        break;
                }
            }

            return true;
        }

        /**
         * A method to determine if the probabilities generated from a sample need be calculated on a one by one basis,
         * and so need their own set of scope constructors for their traces, or if they can be accumulated and only have
         * a single constraint applied to them.
         * 
         * @param t A trace from the sample task variable to the value the probability is being constructed for.
         * @return Returns true if the probabilities need to be considered at the per sample granularity.
         */
        private static boolean perSampleValueRequired(TraceHandle t) {
            SampleTask<?, ?> sTask = (SampleTask<?, ?>) t.get(0).task;
            Set<Scope> scopes = new HashSet<>();
            Scope s = sTask.scope();

            while(s != GlobalScope.scope) {
                if(s.iterating())
                    scopes.add(s);
                s = s.getEnclosingScope();
            }

            // A counter to track the dimensionality of the value that was sampled.
            int sampleDepth = sTask.getOutputType().getDepth();
            // A counter to track the depth of the array the sample is currently placed in.
            int arrayDepth = 0;
            for(DataflowTaskArgDesc d:t) {
                switch(d.task.getType()) {
                    case GET: {
                        if(d.argPos == 0) {
                            if(arrayDepth != 0)
                                return true;
                        } else {
                            return false;
                        }
                        break;
                    }
                    case GET_LENGTH: {
                        return false;
                    }
                    case IF_ASSIGNMENT: {
                        assert d.argPos > 0 : "Traces via the guard should have been filtered out.";
                        return true;
                    }
                    case PUT: {
                        if(d.argPos == 2) {
                            if(arrayDepth == 0) {
                                boolean iterating = false;
                                s = d.task.scope();
                                while(s != GlobalScope.scope) {
                                    if(scopes.contains(s)) {
                                        iterating = true;
                                        break;
                                    }
                                    s = s.getEnclosingScope();
                                }
                                // If the first put was not in an iterating scope of the sample task,
                                // no later put can be.
                                if(!iterating)
                                    return false;
                            }
                            arrayDepth++;
                        } else
                            return false;
                        break;
                    }
                    case REDUCE_INPUT: {
                        if(sampleDepth > 0 && arrayDepth == 0)
                            sampleDepth--;
                        else
                            arrayDepth--;
                        break;
                    }
                    default:
                        break;

                }

                if(d.task.getType() == DFType.PUT && d.argPos == 2)
                    arrayDepth++;
                else if(d.task.getType() == DFType.IF_ASSIGNMENT && d.argPos != 0) {
                    s = d.task.scope();
                    while(s != GlobalScope.scope) {
                        if(scopes.contains(s))
                            return true;
                        s = s.getEnclosingScope();
                    }
                    // If the first put was not in an iterating scope of the sample task,
                    // no later put can be.
                    return false;
                }
            }
            return false;
        }

        public Variable<?> getSampleVariable() {
            return sampleVariable;
        }

        public void removeVariable(Variable<?> v) {
            if(sampleVariable == v) {
                sampleVariable = null;
                return;
            }

            if(perSampleVariables.remove(v) != null)
                return;

            accumulatorVariables.remove(v);

        }

        private Set<Variable<?>> getVariables() {
            Set<Variable<?>> s = new HashSet<>();
            if(sampleVariable != null)
                s.add(sampleVariable);
            s.addAll(perSampleVariables.keySet());
            s.addAll(accumulatorVariables.keySet());
            return s;
        }

        @Override
        public Iterator<Variable<?>> iterator() {
            List<Variable<?>> l = new ArrayList<>(getVariables());
            Collections.sort(l);
            return l.iterator();
        }
    }

    private static class FunctionData<A extends Variable<A>> {
        final SampleTask<A, ?> sampleTask;
        final Scope sampleTaskScope;
        final Variable<?> sampleVariable;
        final TraceHandle traceToSampleVariable;
        final VariableDescription<A> sampleVariableName;
        final VariableDescription<?> sampleTaskName;
        final boolean sampleSkipable;

        // Store the code for initialising intermediate variable probabilities.
        final Set<WrappedTree<IRTree, IRTreeVoid>> toInitialize;

        final boolean useDistributions;

        final CompilationContext compilationCtx;
        final boolean singleSample;

        // Traces to any distributions that will need to be covered.
        final Set<Set<TraceHandle>> distributionTraces;

        // A list of the variables we will need to construct.
        final DependantVariables dependentVariables;

        // The scopes we will use to store probabilities the random variables.
        final RandomVariable<?, ?> randomVariable;
        final List<ScopeDesc> randomScopes;
        final Scope randomStoreScope;
        final List<IRTreeReturn<IntVariable>> randomArgTrees;
        final VariableDescription<DoubleVariable> randomProbName;
        final Set<DataflowTask<?>> consumers;
        final boolean randomSkipable;

        // Get the scopes we will use to store probabilities for samples.
        final VariableDescription<DoubleVariable> storedName;
        final List<ScopeDesc> storeScopes;
        final List<IRTreeReturn<IntVariable>> storeArgTrees;
        final boolean isPrivate;
        final Scope sampleStoreScope;

        final Map<ProducingDataflowTask<?>, Set<TraceHandle>> conditionalTraces;

        final VariableDescription<BooleanVariable> fixedFlagName;
        final VariableDescription<BooleanVariable> fixedProbFlagName;

        FunctionData(SampleTask<A, ?> sampleTask, Set<WrappedTree<IRTree, IRTreeVoid>> toInitialize,
                boolean useDistributions, CompilationContext compilationCtx) {
            this.sampleTask = sampleTask;
            randomVariable = sampleTask.randomVariable;
            sampleTaskScope = sampleTask.scope();
            SampleTraceDesc sampleVarDesc = compilationCtx.traces.getSampleTrace(sampleTask);
            sampleVariable = sampleVarDesc.sampleVariable;
            traceToSampleVariable = sampleVarDesc.traceToSampleVariable;
            sampleVariableName = VariableNames.calcVarName("sampleValue", sampleTask.getOutputType(), true);
            conditionalTraces = compilationCtx.traces.getTracesToConditionals(sampleTask);
            sampleTaskName = sampleTask.getUniqueVarDesc();
            sampleSkipable = DAGUtils.skipableTask(sampleTask);
            consumers = randomVariable.getConsumers();

            fixedFlagName = VariableNames.fixedFlagName(sampleTask);
            fixedProbFlagName = VariableNames.getProbabilityFixedFlag(sampleTask);

            this.toInitialize = toInitialize;
            this.useDistributions = useDistributions;
            this.compilationCtx = compilationCtx;

            singleSample = !compilationCtx.calculateIndividualProbabilities();

            // Find traces to any distributions that will need to be covered.
            if(useDistributions) {
                distributionTraces = findDistributionTraces(randomVariable, compilationCtx);
            } else {
                distributionTraces = Traces.noDistributionTraces;
            }

            // Get a list of the variables we will need to construct.
            dependentVariables = new DependantVariables(sampleTask, compilationCtx);

            // Field for random variable output;
            randomProbName = VariableNames.logProbabilityName(randomVariable.getUniqueVarDesc());

            // Get the scopes we will use to store probabilities the random variables.
            if(singleSample) {
                randomScopes = new ArrayList<>();
                randomScopes.add(new ScopeDesc(GlobalScope.scope, null, false));
                randomStoreScope = GlobalScope.scope;
            } else {
                randomScopes = TreeUtils.getScopeDescs(randomVariable);
                randomStoreScope = randomVariable.scope();
            }

            randomArgTrees = toArgTrees(getScopeArgs(randomScopes), compilationCtx);

            randomSkipable = DAGUtils.skipable(consumers);

            // Get the scopes we will use to store probabilities for samples.
            if(!dependentVariables.perSampleVariables.isEmpty()) {
                storedName = VariableNames.logProbabilityName(sampleTask.getUniqueVarDesc());
                storeScopes = TreeUtils.getScopeDescs(sampleTask.getOutput());
                isPrivate = true;
                sampleStoreScope = sampleTaskScope;
                storeArgTrees = toArgTrees(getScopeArgs(storeScopes), compilationCtx);
                // Test if we need random variables to be stored.
            } else if(randomStoreScope != GlobalScope.scope) {
                storedName = VariableNames.logProbabilityName(sampleTask.getUniqueVarDesc());
                storeScopes = randomScopes;
                isPrivate = true;
                sampleStoreScope = randomStoreScope;
                storeArgTrees = randomArgTrees;
            } else { // Otherwise just store the overall probability of the sample task
                Variable<?> sampleVariable = sampleTask.getOutput();
                storedName = VariableNames.logProbabilityName(sampleVariable.getVarDesc());
                dependentVariables.removeVariable(sampleVariable); // So we don't set this variable twice.
                storeScopes = randomScopes;
                isPrivate = (!sampleVariable.isIntermediate() && !sampleVariable.isSample())
                        || sampleVariable.isPrivate();
                sampleStoreScope = randomStoreScope;
                storeArgTrees = randomArgTrees;
            }
        }
    }

    private static final VariableDescription<DoubleVariable> evidenceProbName = VariableNames
            .logProbabilityName(VariableNames.internalName("evidence"));
    private static final VariableDescription<DoubleVariable> logModelProbName = VariableNames
            .logProbabilityName(VariableNames.internalName("model"));
    private static final VariableDescription<DoubleVariable> sampleProbName = VariableNames
            .calcVarName("sampleProbability", VariableType.DoubleVariable, true);
    private static final VariableDescription<DoubleVariable> accumulatorName = VariableNames.calcVarName("accumulator",
            VariableType.DoubleVariable, true);
    // Accumulator for the probabilities seen in the different distribution
    // configurations.
    private static final VariableDescription<DoubleVariable> disAccumulator = VariableNames
            .calcVarName("distributionAccumulator", VariableType.DoubleVariable, true);
    private static final VariableDescription<DoubleVariable> rvProbabilityName = VariableNames
            .calcVarName("rvAccumulator", VariableType.DoubleVariable, true);
    private static final VariableDescription<DoubleVariable> sampleValueName = VariableNames.calcVarName("sampleValue",
            VariableType.DoubleVariable, true);
    // Name for a second accumulator in the case that the same task is inside
    // another for loop relative to the random variable
    private static final VariableDescription<DoubleVariable> sampleAccumulatorName = VariableNames
            .calcVarName("sampleAccumulator", VariableType.DoubleVariable, true);
    // Accumulator for the probability spaces reached through the distributions.
    private static final VariableDescription<DoubleVariable> probabilityReached = VariableNames
            .calcVarName("probabilityReached", VariableType.DoubleVariable, true);
    private static final VariableDescription<DoubleVariable> weightedProbability = VariableNames
            .calcVarName("weightedProbability", VariableType.DoubleVariable, true);

    // Guard to check if the value has been reached.
    private static final VariableDescription<BooleanVariable> guardName = VariableNames.calcVarName("sampleReached",
            VariableType.BooleanVariable, true);

    public static void probabilityFunctions(CompilationContext compilationCtx) {
        // Store the code for initialising intermediate variable probabilities.
        Set<WrappedTree<IRTree, IRTreeVoid>> toInitialize = new LinkedHashSet<>();

        // Construct a field for the overall model. This field needs to be public as it
        // is directly accessed by the runtime.
        compilationCtx.addConstructedClassField(logModelProbName, FieldType.PUBLIC_PROBABILITY);
        toInitialize.add(new WrappedTree<>(store(logModelProbName, constant(0.0), Tree.NoComment)));

        // Construct a field for the model evidence. This field needs to be public as it
        // is directly accessed by the runtime.
        compilationCtx.addConstructedClassField(evidenceProbName, FieldType.PUBLIC_PROBABILITY);
        toInitialize.add(new WrappedTree<>(store(evidenceProbName, constant(0.0), Tree.NoComment)));

        PriorityQueue<SampleTask<?, ?>> p = new PriorityQueue<>(compilationCtx.traces.getAllSampleTasks());
        while(!p.isEmpty()) {
            SampleTask<?, ?> sampleTask = p.poll();
            constructProbabilityMethod(sampleTask, toInitialize, false, compilationCtx);
            if(sampleTask.isDistribution() || sampleTask.randomVariable.isDistribution())
                constructProbabilityMethod(sampleTask, toInitialize, true, compilationCtx);
        }
        constructIntermediateProbabilityInitialisationFunction(toInitialize, compilationCtx);
    }

    private static void constructIntermediateProbabilityInitialisationFunction(
            Set<WrappedTree<IRTree, IRTreeVoid>> toInitialize, CompilationContext compilationCtx) {
        /*
         * Collect all the variables. The map is used so that at a later stage we can initialize only the variables that
         * are required for the function. Specifically evidence functions don't require All the probabilities, and some
         * should be set to NaN.
         */
        List<IRTreeVoid> trees = new ArrayList<>();
        for(WrappedTree<IRTree, IRTreeVoid> w:toInitialize)
            trees.add(w.tree);

        /*
         * This will make the order stable for testing. It is not required for the correctness of the code as there are
         * no dependencies between trees.
         */
        Collections.sort(trees);

        IRSequential seq = IRTree.sequential(trees, "Set the probabilities of the random variable, "
                + "and the model as a whole to ready them to be reconstructed by the probability calls for each sample. "
                + "Sample probabilities are only reset for samples that are not fixed at a value that has already been calculated.");

        // And place the subtree in a void function.
        compilationCtx.addFunction(AuxFunctionType.INITIALIZE_LOG_PROBABILITY_FIELDS, Visibility.PRIVATE,
                new ArgDesc[0], seq, false,
                "A method to initialize all the probabilities in the model to 0/Log(1) ready for the current probabilities to be calculated "
                        + "by calculating the probability of each sample task, and its effect on the rest of the model.");
    }

    private static void constructProbabilityMethod(SampleTask<?, ?> sampleTask,
            Set<WrappedTree<IRTree, IRTreeVoid>> toInitialize, boolean useDistributions,
            CompilationContext compilationCtx) {
        // Initialize the compilationCtx
        compilationCtx.initialize();
        compilationCtx.pushIsSerial(true);
        constructProbabilityMethod(new FunctionData<>(sampleTask, toInitialize, useDistributions, compilationCtx));
        compilationCtx.popIsSerial();
    }

    // TODO remove the observed variable.
    private static <A extends Variable<A>> void constructProbabilityMethod(FunctionData<A> funcData) {
        // Allocate space for the results. Using scopes here is ok because
        // random is allocated inside these scopes, so can only exist in this number of
        // dimensions.
        funcData.toInitialize.add(new WrappedTree<>(TreeUtils.constructVariable(funcData.randomProbName,
                constant(funcData.randomSkipable ? Double.NaN : 0.0), funcData.randomScopes,
                funcData.randomVariable.isPrivate() ? FieldType.PRIVATE_PROBABILITY : FieldType.PUBLIC_PROBABILITY,
                funcData.compilationCtx)));

        List<ScopeDesc> outputScopes = new ArrayList<>();
        outputScopes.add(new ScopeDesc(GlobalScope.scope, null, false));
        for(Variable<?> intermediate:funcData.dependentVariables) {
            // TODO decide how many dimensions we want to return results in, for every
            // observation, for all observations, or somewhere in between.
            // TODO if we do add more dimensions we will need a different allocation
            // strategy as
            // scopes only allocate for the scope the current variable is in, and
            // intermediate
            // variables could be constructed in multiple places.
            // TODO consider removing this as it will all cancel through if the only scope
            // we are going to use is global.
            // Get the name of the output variable.

            VariableDescription<DoubleVariable> outputName = VariableNames
                    .logProbabilityName(intermediate.getUniqueVarDesc());
            IRTreeVoid initializeIntermediate = TreeUtils.constructVariable(outputName, constant(0.0), outputScopes,
                    intermediate.isPrivate() ? FieldType.PRIVATE_PROBABILITY : FieldType.PUBLIC_PROBABILITY,
                    intermediate.getComment(), funcData.compilationCtx);
            funcData.toInitialize.add(new WrappedTree<>(initializeIntermediate));
        }

        /*
         * Distributions have a probability of 1 which is achieved by not reducing the value with the sample method.
         * TODO fix this as it is not correct.
         */

        // Construct a space to store the probability.
        IRTreeVoid sampleAllocation = TreeUtils.constructVariable(funcData.storedName, constant(Double.NaN),
                funcData.storeScopes, funcData.isPrivate ? FieldType.PRIVATE_PROBABILITY : FieldType.PUBLIC_PROBABILITY,
                funcData.compilationCtx);

        // Construct a flag to determine if this probability is already calculated.
        funcData.compilationCtx.addConstructedClassField(funcData.fixedProbFlagName, constant(false));

        // Set the initialisation
        IRTreeReturn<BooleanVariable> guard = negateBoolean(load(funcData.fixedProbFlagName));
        funcData.toInitialize.add(new WrappedTree<IRTree, IRTreeVoid>(ifElse(guard, sampleAllocation, Tree.NoComment)));

        getInverseIR(funcData);

        // construct list of statements that will make up this function.
        List<IRTreeVoid> subtrees = new ArrayList<>();

        // Get the resulting tree.
        subtrees.add(funcData.compilationCtx.getOutermostScopeTree());

        // Test if we should set a fixed flag.
        IRTreeReturn<BooleanVariable> fixed;
        if(funcData.sampleVariable.isFixed()) {
            fixed = IRTree.constant(true);
        } else {
            fixed = load(funcData.fixedFlagName);
            // Add side effect to clear the flag if a dependency given the ability to changed
            IRTreeVoid restFixed = store(funcData.fixedProbFlagName,
                    and(load(funcData.fixedFlagName), load(funcData.fixedProbFlagName)),
                    "Should the probability of sample " + funcData.sampleTask.id()
                            + " be set to fixed. This will only every change the flag to false.");
            funcData.compilationCtx.addSetSideEffect(funcData.fixedFlagName, restFixed);

            // Add side effect to clear the flag if a dependency is changed.
            VariableDescription<?> sampleName = funcData.sampleVariable.getUniqueVarDesc();
            restFixed = store(funcData.fixedProbFlagName, constant(false),
                    "Unset the fixed probability flag for sample " + funcData.sampleTask.id() + " as it depends on "
                            + sampleName.name + ".");
            funcData.compilationCtx.addSetSideEffect(sampleName, restFixed);
        }

        // Gather all the sample tasks that are used as inputs.
        Set<SampleTask<?, ?>> inputSamples = new HashSet<>();
        for(Variable<?> v:funcData.randomVariable.getParent().getInputs()) {
            for(SampleTask<?, ?> s:funcData.compilationCtx.traces.getSourceSampleTasks(v)) {
                if(!s.getOutput().isFixed())
                    inputSamples.add(s);
            }
        }

        for(SampleTask<?, ?> s:funcData.compilationCtx.traces
                .getSourceSampleTasks(funcData.sampleTaskScope.getScopeCondition())) {
            if(!s.getOutput().isFixed())
                inputSamples.add(s);
        }
        
        // Construct an expression to determine if the flag should be set.
        if(!inputSamples.isEmpty()) {
            // Priority queue for fixed ordering of assignments.
            PriorityQueue<SampleTask<?, ?>> p = new PriorityQueue<>(inputSamples);
            while(!p.isEmpty()) {
                SampleTask<?, ?> s = p.poll();
                VariableDescription<BooleanVariable> sampleFixedName = VariableNames.fixedFlagName(s);
                fixed = IRTree.and(fixed, load(sampleFixedName));

                // Add side effect to clear the flag if a dependency given the ability to changed
                IRTreeVoid restFixed = store(funcData.fixedProbFlagName,
                        and(load(sampleFixedName), load(funcData.fixedProbFlagName)),
                        "Should the probability of sample " + funcData.sampleTask.id()
                                + " be set to fixed. This will only every change the flag to false.");
                funcData.compilationCtx.addSetSideEffect(sampleFixedName, restFixed);

                // Add side effect to clear the flag if a dependency is changed.
                SampleTraceDesc sampleDesc = funcData.compilationCtx.traces.getSampleTrace(s);
                VariableDescription<?> sampleName = sampleDesc.sampleVariable.getUniqueVarDesc();
                restFixed = store(funcData.fixedProbFlagName, constant(false),
                        "Unset the fixed probability flag for sample " + funcData.sampleTask.id() + " as it depends on "
                                + sampleName.name + ".");
                funcData.compilationCtx.addSetSideEffect(sampleName, restFixed);
            }
        }
        subtrees.add(store(funcData.fixedProbFlagName, fixed,
                "Now the probability is calculated store if it can be cached or if it needs to be recalculated next time."));

        // Join all the subtrees with a sequential block
        IRTreeVoid generateValues = sequential(subtrees, "Generating probabilities for sample task");
        if(funcData.sampleTask.isDistribution() && funcData.useDistributions)
            generateValues = ifElse(load(funcData.fixedFlagName), generateValues,
                    "Update the probability if the distribution is fixed to a specific value."
                            + " If it is not the value is implicitly log(1.0) so has no effect.");

        // Populate the behaviour if we just need to copy the pre-calculated results
        // back out.
        funcData.compilationCtx.pushScope();

        // Initialize variables
        funcData.compilationCtx.addTreeToScope(GlobalScope.scope,
                initializeVariable(accumulatorName, constant(0.0), Tree.NoComment));

        funcData.compilationCtx.addTreeToScope(funcData.randomStoreScope,
                initializeVariable(rvProbabilityName, constant(0.0), Tree.NoComment));

        if(funcData.sampleSkipable) {
            funcData.compilationCtx.enterScope(funcData.sampleTaskScope);
            funcData.compilationCtx.addTreeToScope(GlobalScope.scope, initializeVariable(guardName,
                    IRTree.constant(false), "A guard to check if the sample value is ever reached."));
            funcData.compilationCtx.leaveScope(funcData.sampleTaskScope);
        }

        if(funcData.storeArgTrees.isEmpty()) // We are not in a loop.
            funcData.compilationCtx.addTreeToScope(funcData.sampleStoreScope,
                    initializeVariable(sampleValueName, load(funcData.storedName), Tree.NoComment));
        else
            funcData.compilationCtx.addTreeToScope(funcData.sampleStoreScope, initializeVariable(sampleValueName,
                    TreeUtils.getIndirectValue(funcData.storedName, funcData.storeArgTrees), Tree.NoComment));

        // Accumulate values
        funcData.compilationCtx.addTreeToScope(funcData.sampleStoreScope,
                store(rvProbabilityName, addDD(load(rvProbabilityName), load(sampleValueName)), Tree.NoComment));
        if(funcData.sampleSkipable)
            funcData.compilationCtx.addTreeToScope(funcData.sampleTaskScope,
                    store(guardName, constant(true), "Record that the sample was reached."));
        funcData.compilationCtx.addTreeToScope(funcData.randomStoreScope,
                store(accumulatorName, addDD(load(accumulatorName), load(rvProbabilityName)), Tree.NoComment));

        // Update the values for the random variables
        updateRVProbabilities(funcData, load(rvProbabilityName));
        // If we are tracking individual values, embed in the correct set of for loops.

        // Update the values for the variables
        updateVarProbabilities(funcData, load(accumulatorName), load(sampleValueName));

        IRTreeVoid repopulateValues = funcData.compilationCtx.getOutermostScopeTree();
        repopulateValues.prefixComment(
                "Updating random variable and model probabilities using cached probabilities for this sample");
        funcData.compilationCtx.popScope();

        // Merge the values in a conditional
        IRTreeReturn<BooleanVariable> mergeGuard = negateBoolean(load(funcData.fixedProbFlagName));
        IRTreeVoid mergeResult = ifElse(
                mergeGuard, generateValues, "Determine if we need to calculate the values for sample task "
                        + funcData.sampleTask.id() + " or if we should just use cached values.",
                repopulateValues, "Using cached values.");

        // And place the subtree in a void function.
        FunctionName functionName = FunctionName.createFunctionName(
                funcData.useDistributions ? VariableNames.logProbabilityDistributionName(funcData.sampleTaskName)
                        : VariableNames.logProbabilityValueName(funcData.sampleTaskName));
        String comment = "Calculate the probability of the samples represented by " + funcData.sampleTaskName
                + (funcData.useDistributions ? " using probability distributions." : " using sampled values.");
        SampleFunctionClass functionClass = funcData.useDistributions
                ? SampleFunctionClass.LOG_PROBABILITY_DISTRIBUTIONS
                : SampleFunctionClass.LOG_PROBABILITY_VALUE;
        funcData.compilationCtx.addFunction(functionClass, funcData.sampleTask,
                voidFunction(Visibility.PRIVATE, functionName, new ArgDesc<?>[0], mergeResult, comment));
    }

    private static void getInverseIR(FunctionData<?> funcData) {
        /* Initialize accumulators */
        // Enter the scope of to force all allocations to the global scope to happen
        // before it.
        funcData.compilationCtx.enterScope(funcData.sampleTaskScope);

        // Construct the RV accumulator.
        funcData.compilationCtx.addTreeToScope(GlobalScope.scope, initializeVariable(accumulatorName,
                IRTree.constant(0.0), "Accumulator for probabilities of instances of the random variable"));

        IRTreeReturn<DoubleVariable> sampleAccumulatorValue = load(sampleAccumulatorName);

        // Initialise the variables.
        funcData.compilationCtx.addTreeToScope(funcData.randomStoreScope,
                initializeVariable(sampleAccumulatorName, IRTree.constant(0.0),
                        "Accumulator for sample probabilities for a specific instance of the random variable."));

        if(funcData.sampleSkipable)
            funcData.compilationCtx.addTreeToScope(GlobalScope.scope, initializeVariable(guardName,
                    IRTree.constant(false), "A guard to check if the sample value is ever reached."));

        // Having initialized all the state we can now leave the sample tasks scope.
        funcData.compilationCtx.leaveScope(funcData.sampleTaskScope);

        /* Explore the trace */
        IRTreeReturn<DoubleVariable> sampleProbability = distributionsProb(funcData);

        funcData.compilationCtx.addTreeToScope(funcData.sampleTaskScope,
                initializeVariable(sampleProbName, sampleProbability, Tree.NoComment));
        IRTreeReturn<DoubleVariable> sampleProbValue = load(sampleProbName);

        if(funcData.sampleSkipable)
            funcData.compilationCtx.addTreeToScope(funcData.sampleTaskScope,
                    store(guardName, constant(true), "Record that the sample was reached."));

        // Add statements to increment the accumulators.
        funcData.compilationCtx.addTreeToScope(funcData.sampleTaskScope,
                store(sampleAccumulatorName, addDD(sampleAccumulatorValue, load(sampleProbName)),
                        "Add the probability of this sample task to the sample task accumulator."));
        funcData.compilationCtx.addTreeToScope(funcData.randomStoreScope, store(accumulatorName,
                addDD(load(accumulatorName), sampleAccumulatorValue),
                "Add the probability of this instance of the random variable to the probability of all instances of the random variable."));

        // Update the RV probability
        updateRVProbabilities(funcData, sampleAccumulatorValue);

        // Update the stored probability for the random variable.
        IRTreeVoid updateSample;
        if(funcData.sampleStoreScope == funcData.sampleTaskScope) { // Storing each sample value.
            updateSample = TreeUtils.putIndirectValue(funcData.storedName, funcData.storeArgTrees, sampleProbValue,
                    "Store the sample task probability");
        } else if(funcData.sampleStoreScope == funcData.randomVariable.scope()) {
            // Store the accumulated value for each random variable.
            updateSample = TreeUtils.putIndirectValue(funcData.storedName, funcData.storeArgTrees,
                    sampleAccumulatorValue, "Store the random variable instance probability");
        } else {
            updateSample = TreeUtils.putIndirectValue(funcData.storedName, funcData.storeArgTrees,
                    load(accumulatorName), "Store the random variable instance probability");
        }
        if(funcData.singleSample && funcData.sampleSkipable)
            updateSample = ifElse(load(guardName), updateSample,
                    "Only update the sample if it was reached, otherwise the NaN will be \n"
                            + "erroneously over written.");

        funcData.compilationCtx.addTreeToScope(funcData.sampleStoreScope, updateSample);

        // Update the probability of the model variables.
        updateVarProbabilities(funcData, load(accumulatorName), load(sampleProbName));
    }

    private static <A extends Variable<A>> IRTreeReturn<DoubleVariable> distributionsProb(FunctionData<A> funcData) {
        funcData.compilationCtx.addTreeToScope(funcData.sampleTaskScope, initializeVariable(disAccumulator,
                constant(Double.NEGATIVE_INFINITY), "An accumulator for log probabilities."));

        funcData.compilationCtx.addTreeToScope(funcData.sampleTaskScope, initializeVariable(probabilityReached,
                constant(0.0), "An accumulator for the distributed probability space covered."));

        // Construct holder for new scopes.
        ScopeConstructor a = ScopeConstructor.construct(funcData.sampleTask, funcData.distributionTraces,
                "Look for paths between the variable and the sample task " + funcData.sampleTask.id()
                        + " including any distribution values.",
                Tree.NoComment, funcData.compilationCtx);
        a = a.addConstraint(funcData.traceToSampleVariable);

        a.addTree(0, (TreeBuilderInfo info) -> {
            IRTreeReturn<?> current = funcData.sampleVariable.getForwardIR(funcData.compilationCtx);
            int index = funcData.traceToSampleVariable.size() - 1;
            BackTraceInfo backTraceInfo = new BackTraceInfo();
            while(index >= 0) {
                DataflowTaskArgDesc d = funcData.traceToSampleVariable.get(index--);
                ProducingDataflowTask<?> t = d.task;

                switch(t.getType()) {
                    case SAMPLE:
                        break;
                    default:
                        current = t.getInverseIR(d.argPos, current, backTraceInfo, funcData.compilationCtx);
                }
            }

            funcData.compilationCtx.addTreeToScope(funcData.sampleTaskScope,
                    IRTree.initializeVariable(funcData.sampleVariableName, (IRTreeReturn<A>) current,
                            "The sample value to calculate the probability of generating"));
        });

        a = a.applyAllDistributedArguments();

        IRTreeReturn<A> sampleValue = load(funcData.sampleVariableName);
        a.addTree(0, (TreeBuilderInfo info) -> {

            // Calculate the probability of the value in current. If this happens inside an
            // iterating scope this may be called many times with different values.
            IRTreeReturn<DoubleVariable> sampleProbability = getSampleProbability(funcData.randomVariable, sampleValue,
                    true, funcData.compilationCtx);

            // Store the value of the function call, so the function call is only made once.
            funcData.compilationCtx.addTreeToScope(funcData.sampleTaskScope,
                    initializeVariable(weightedProbability, addDD(log(info.probability), sampleProbability),
                            "Store the value of the function call, so the function call is only made once."));
        });

        // Check if any of the traces from here lead to a conditional that might not be true. If it does the current
        // value could not be set in the model so the probability needs to be set to log(0) aka -infinity.
        for(ProducingDataflowTask<?> p:funcData.conditionalTraces.keySet()) {
            // Construct a scope constructor at the conditional
            Set<TraceHandle> ts = funcData.conditionalTraces.get(p);
            Set<TraceHandle> subTraces = new HashSet<>();
            for(TraceHandle t:ts)
                subTraces.add(t.subTrace(funcData.traceToSampleVariable));
            ScopeConstructor b = a.addConstraints(subTraces);

            SplitConditionalTraces scts = funcData.compilationCtx.traces.getSplitConditionalTraces(p);
            for(ProducingDataflowTask<?> sinkTask:scts.sinkToConditional.keySet()) {
                Variable<?> sink = sinkTask.getOutput();
                if(sink.isObserved()) {
                    for(DataflowTaskArgDesc taskDesc:scts.sinkToConditional.get(sinkTask).keySet()) {
                        if(scts.conditionalToSource.get(taskDesc).isEmpty()) {
                            for(TraceHandle sinkToConditional:scts.sinkToConditional.get(sinkTask).get(taskDesc)) {
                                // If this point is reached the trace is between an observed value and a constant.
                                // Add a constraint to ensure that the observed value can be reached.

                                // At this point the trace to the source will always be empty so there is no concern
                                // about duplicate split traces.
                                ScopeConstructor c = b.addConstraint(sinkToConditional);

                                c.addTree(2, (TreeBuilderInfo info) -> {
                                    IRTreeReturn<?> current = sink.getForwardIR(funcData.compilationCtx);
                                    int index = sinkToConditional.size() - 1;
                                    BackTraceInfo backTraceInfo = new BackTraceInfo();
                                    while(index >= 0) {
                                        DataflowTaskArgDesc currentDesc = sinkToConditional.get(index--);
                                        current = currentDesc.task.getInverseIR(currentDesc.argPos, current,
                                                backTraceInfo, funcData.compilationCtx);
                                    }

                                    constructObservationTest(weightedProbability, taskDesc, current,
                                            funcData.compilationCtx);
                                });
                            }
                        }
                    }
                }
            }
        }

        // Merge the resulting weighted probability into the accumulators.
        a.addTree(0, (TreeBuilderInfo info) -> {
            funcData.compilationCtx.addTreeToScope(funcData.sampleTaskScope,
                    TreeUtils.lseAdd(load(disAccumulator), load(weightedProbability), disAccumulator,
                            "Add the probability of this sample task to the distribution accumulator."));

            // Update the probability space seen
            funcData.compilationCtx.addTreeToScope(funcData.sampleTaskScope,
                    store(probabilityReached, addDD(load(probabilityReached), info.probability),
                            "Add the probability of this distribution configuration to the accumulator."));
        });

        IRTreeVoid normalise = ifElse(eq(load(probabilityReached), constant(0.0)),
                store(disAccumulator, constant(Double.NEGATIVE_INFINITY),
                        "Return negative infinity if no distribution probability space is reached."),
                Tree.NoComment, store(disAccumulator, subtractDD(load(disAccumulator), log(load(probabilityReached))),
                        "Scale the probability relative to the observed distribution space."),
                Tree.NoComment);
        funcData.compilationCtx.addTreeToScope(funcData.sampleTaskScope, normalise);

        return load(disAccumulator);
    }

    private static <A extends ScalarVariable<A>, B extends ScalarVariable<B>> void constructObservationTest(
            VariableDescription<DoubleVariable> varDesc, DataflowTaskArgDesc taskDesc, IRTreeReturn<?> observedValue,
            CompilationContext compilationCtx) {
        A inputVar = (A) taskDesc.task.getInput(taskDesc.argPos);
        IRTreeReturn<A> expected = inputVar.getForwardIR(compilationCtx);
        IRTreeReturn<BooleanVariable> guard = IRTree
                .negateBoolean(IRTree.eq((IRTreeReturn<B>) observedValue, expected));
        IRTreeVoid condition = IRTree.ifElse(guard,
                IRTree.store(varDesc, IRTree.constant(Double.NEGATIVE_INFINITY), Tree.NoComment),
                "If the observed value does not match the provided value the probability of generating this random variable is zero");
        compilationCtx.addTreeToScope(taskDesc.task.scope(), condition);
    }

    private static Set<Set<TraceHandle>> findDistributionTraces(RandomVariable<?, ?> randomVariable,
            CompilationContext compilationCtx) {
        return Traces.findDistributionTraces(randomVariable, compilationCtx);
    }

    private static IRTreeReturn<?>[] constructArguments(RandomVariable<?, ?> random, IRTreeReturn<?> value,
            CompilationContext compilationCtx) {
        List<Variable<?>> randomInputs = random.getParent().getInputs();
        int noInputs = randomInputs.size();
        List<IRTreeReturn<?>> args = new ArrayList<>();
        args.add(value);
        for(int i = 0; i < noInputs; i++) {
            Variable<?> v = randomInputs.get(i);
            constructInput(v, compilationCtx);
            args.add(loadArg(v, compilationCtx));
            if(v.getType().isArray())
                args.add(((ArrayVariable<?>) v).getLength(compilationCtx));
        }
        return args.toArray(new IRTreeReturn<?>[args.size()]);
    }

    public static IRTreeReturn<DoubleVariable> getSampleProbability(RandomVariable<?, ?> random,
            IRTreeReturn<?> current, boolean log, CompilationContext compilationCtx) {

        IRTreeReturn<?>[] args = constructArguments(random, current, compilationCtx);
        return functionCallReturn(log ? FunctionType.LOG_PROBABILITY : FunctionType.PROBABILITY,
                VariableType.DoubleVariable, random.getType(), args);
    }

    private static void updateVarProbabilities(FunctionData<?> funcData,
            IRTreeReturn<DoubleVariable> accumulatedProbability, IRTreeReturn<DoubleVariable> sampleProbability) {
        {
            Map<Variable<?>, VariableDescription<BooleanVariable>> guards = new HashMap<>();

            // Initialize guards

            PriorityQueue<Variable<?>> p = new PriorityQueue<>(funcData.dependentVariables.perSampleVariables.keySet());
            while(!p.isEmpty()) {
                Variable<?> v = p.poll();
                Variable<?> instance = v.instanceHandle();
                VariableDescription<BooleanVariable> multisetGuardName = guards.get(instance);
                if(multisetGuardName == null) {
                    multisetGuardName = VariableNames.calcVarName("guard", instance.getUniqueVarDesc().name.getName(),
                            VariableType.BooleanVariable, true);
                    guards.put(instance, multisetGuardName);
                    funcData.compilationCtx.addTreeToScope(funcData.sampleTaskScope,
                            initializeVariable(multisetGuardName, constant(false), "Guard to ensure that "
                                    + instance.getUniqueVarDesc() + " is only updated once for this probability."));
                }
            }

            p.addAll(funcData.dependentVariables.accumulatorVariables.keySet());
            while(!p.isEmpty()) {
                Variable<?> v = p.poll();
                Variable<?> instance = v.instanceHandle();
                VariableDescription<BooleanVariable> guardName = guards.get(instance);
                if(guardName == null) {
                    guardName = VariableNames.calcVarName("guard", instance.getUniqueVarDesc().name.getName(),
                            VariableType.BooleanVariable, true);
                    guards.put(instance, guardName);
                    funcData.compilationCtx.addTreeToScope(GlobalScope.scope,
                            initializeVariable(guardName, constant(false), "Guard to ensure that "
                                    + instance.getUniqueVarDesc() + " is only updated once for this probability."));
                }
            }

            // Set sample value
            Variable<?> sampleVariable = funcData.dependentVariables.getSampleVariable();
            if(sampleVariable != null) {
                funcData.compilationCtx.addTreeToScope(GlobalScope.scope, setValueProbability(accumulatedProbability,
                        sampleVariable, null, funcData.useDistributions, funcData.compilationCtx));
            }

            // Reset the priority queue.
            if(!funcData.dependentVariables.perSampleVariables.isEmpty()) {
                ScopeConstructor sPerSample = ScopeConstructor.construct(funcData.sampleTask,
                        "Add probability to constructed variables that have guards, so need per sample probabilities from the combined probability",
                        funcData.compilationCtx);

                p.addAll(funcData.dependentVariables.perSampleVariables.keySet());
                while(!p.isEmpty()) {
                    Variable<?> v = p.poll();
                    Set<TraceHandle> traces = funcData.dependentVariables.perSampleVariables.get(v);
                    VariableDescription<BooleanVariable> guardName = guards.get(v.instanceHandle());

                    // Add variables that require per variable values
                    ScopeConstructor perVarScopes = sPerSample.addConstraints(traces, NO_GUARDS);
                    perVarScopes.addTree((TreeBuilderInfo info) -> funcData.compilationCtx
                            .addTreeToScope(GlobalScope.scope, setValueProbability(sampleProbability, v, guardName,
                                    funcData.useDistributions, funcData.compilationCtx)));
                }
            }

            if(!funcData.dependentVariables.accumulatorVariables.isEmpty()) {
                ScopeConstructor sAccumulator = ScopeConstructor.construct(funcData.sampleTask, GlobalScope.scope,
                        "Add probability to constructed variables from the combined probability",
                        funcData.compilationCtx);

                p.addAll(funcData.dependentVariables.accumulatorVariables.keySet());
                while(!p.isEmpty()) {
                    Variable<?> v = p.poll();
                    Set<TraceHandle> traces = funcData.dependentVariables.accumulatorVariables.get(v);
                    VariableDescription<BooleanVariable> guardName = guards.get(v.instanceHandle());

                    // Add variables that require per variable values
                    ScopeConstructor perVarScopes = sAccumulator.addConstraints(traces, NO_GUARDS);
                    perVarScopes.addTree((TreeBuilderInfo info) -> funcData.compilationCtx
                            .addTreeToScope(GlobalScope.scope, setValueProbability(accumulatedProbability, v, guardName,
                                    funcData.useDistributions, funcData.compilationCtx)));

                }
            }
        }

        // merge in to Model probability.
        {
            IRTreeReturn<DoubleVariable> outputValue = load(logModelProbName);
            IRTreeReturn<DoubleVariable> mergedValue = addDD(outputValue, accumulatedProbability);
            funcData.compilationCtx.addTreeToScope(GlobalScope.scope,
                    store(logModelProbName, mergedValue, "Add probability to model"));
        }

        // merge into evidence
        {
            IRTreeReturn<DoubleVariable> outputValue = load(evidenceProbName);
            IRTreeReturn<DoubleVariable> mergedValue = addDD(outputValue, accumulatedProbability);
            IRTreeVoid t = store(evidenceProbName, mergedValue, Tree.NoComment);

            if(!funcData.compilationCtx.traces.isObserved(funcData.sampleTask)) {
                IRTreeReturn<BooleanVariable> guard = load(funcData.fixedFlagName);
                t = IRTree.ifElse(guard, t,
                        "If this value is fixed, add it to the probability of this model producing the fixed values");
            }
            funcData.compilationCtx.addTreeToScope(GlobalScope.scope, t);
        }
    }

    private static IRTreeVoid setValueProbability(IRTreeReturn<DoubleVariable> sampleProbability, Variable<?> v,
            VariableDescription<BooleanVariable> guardName, boolean useDistributions,
            CompilationContext compilationCtx) {
        VariableDescription<DoubleVariable> varName = VariableNames.logProbabilityName(v.getUniqueVarDesc());
        IRTreeReturn<DoubleVariable> outputValue = load(varName);
        IRTreeReturn<DoubleVariable> mergedValue = addDD(outputValue, sampleProbability);
        IRTreeVoid updateValue = store(varName, mergedValue, "Update the variable probability");

        IRTreeVoid guardedTree;
        if(guardName != null) {
            IRTreeVoid updateGuard = store(guardName, constant(true),
                    "Set the guard so the update is only applied once.");
            guardedTree = ifElse(negateBoolean(load(guardName)), sequential(Tree.NoComment, updateGuard, updateValue),
                    "If the probability of the variable has not already been updated");
        } else {
            guardedTree = updateValue;
        }

        // If we are using distributions test if all the inputs are fixed before
        // updating the probability.
        if(useDistributions && v.isDistribution()) {
            IRTreeReturn<BooleanVariable> fixedGuard = constant(true);
            for(SampleTask<?, ?> sTask:compilationCtx.traces.getSourceSampleTasks(v)) {
                if(!sTask.getOutput().isFixed())
                    fixedGuard = and(fixedGuard, load(VariableNames.fixedFlagName(sTask)));
            }
            guardedTree = ifElse(fixedGuard, guardedTree,
                    "Make sure all the inputs have been fixed so the variable is not a distribution.");
        }

        return guardedTree;
    }

    private static void updateRVProbabilities(FunctionData<?> funcData,
            IRTreeReturn<DoubleVariable> sampleProbability) {
        for(DataflowTask<?> c:funcData.consumers) {
            if(c.getType() == DFType.PUT)
                throw new CompilerException("If random variable can be put into arrays then"
                        + " the arrays need to have traces followed to determine how many sample tasks consume the random variable.");
        }

        // Determine if multiple assignments may be required.
        boolean multiple = funcData.consumers.size() > 1;

        // Calculate for the random sample
        IRTreeVoid assignment;
        if(multiple) {
            IRTreeReturn<DoubleVariable> outputValue = getIndirectValue(funcData.randomProbName,
                    funcData.randomArgTrees);
            if(funcData.randomSkipable) {
                // Test to ensure that the value is not still set to NaN
                IRTreeReturn<BooleanVariable> guard = functionCallReturn(ExternalFunction.IS_NAN,
                        VariableType.BooleanVariable, outputValue);

                if(funcData.singleSample)
                    guard = and(guard, load(guardName));
                IRTreeVoid ifTree = putIndirectValue(funcData.randomProbName, funcData.randomArgTrees,
                        sampleProbability, Tree.NoComment);

                IRTreeReturn<DoubleVariable> mergedValue = addDD(outputValue, sampleProbability);
                IRTreeVoid elseTree = putIndirectValue(funcData.randomProbName, funcData.randomArgTrees, mergedValue,
                        Tree.NoComment);
                assignment = ifElse(guard, ifTree, "Check that the value is not still set to NaN.", elseTree,
                        Tree.NoComment);
            } else {
                IRTreeReturn<DoubleVariable> mergedValue = addDD(outputValue, sampleProbability);
                assignment = putIndirectValue(funcData.randomProbName, funcData.randomArgTrees, mergedValue,
                        Tree.NoComment);
            }
        } else {
            assignment = putIndirectValue(funcData.randomProbName, funcData.randomArgTrees, sampleProbability,
                    Tree.NoComment);

            if(funcData.sampleSkipable && funcData.singleSample)
                assignment = ifElse(load(guardName), assignment, Tree.NoComment);
        }
        funcData.compilationCtx.addTreeToScope(funcData.randomStoreScope, assignment);
    }

    // Methods so that arguments constructed first in the program and then loaded.
    // These could be removed, but should make for clearer code.
    private static IRTreeReturn<?> loadArg(Variable<?> v, CompilationContext compilationCtx) {
        if(!v.isSample() && !v.isIntermediate())
            return load(v);
        else
            return v.getForwardIR(compilationCtx);
    }

    private static <A extends Variable<A>> void constructInput(Variable<A> p, CompilationContext compilationCtx) {
        if(!p.isSample() && !p.isIntermediate()) {
            DataflowTask<A> parent = p.getParent();
            VariableDescription<A> pName = p.getUniqueVarDesc();
            IRTreeReturn<A> pTree = p.getForwardIR(compilationCtx);
            if(!compilationCtx.initialized(p)) {
                compilationCtx.addTreeToScope(parent.scope(), initializeVariable(pName, pTree, Tree.NoComment));
                compilationCtx.addInitialized(p);
            }
        }
    }
}
