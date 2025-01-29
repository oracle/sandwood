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
import org.sandwood.compiler.compilation.CompilationContext.SampleFunctionClass;
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
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.names.FunctionName;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.traces.TraceHandle;
import org.sandwood.compiler.traces.Traces;
import org.sandwood.compiler.traces.Traces.IntermediateDesc;
import org.sandwood.compiler.traces.Traces.SampleTraceDesc;
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
            for(Variable<?> v:intermediateDesc.getVariables()) {
                if(v.instanceHandle() != sampleVariable.instanceHandle()) {
                    Set<TraceHandle> traces = intermediateDesc.getTraces(v);

                    boolean perSampleValuesRequired = false;
                    for(TraceHandle t:traces)
                        if(perSampleValueRequired(t, sTask)) {
                            perSampleValuesRequired = true;
                            break;
                        }

                    if(perSampleValuesRequired)
                        perSampleVariables.put(v, traces);
                    else
                        accumulatorVariables.put(v, traces);
                }
            }
        }

        private static boolean perSampleValueRequired(TraceHandle t, SampleTask<?, ?> sTask) {
            Set<Scope> scopes = new HashSet<>();
            Scope s = sTask.scope();

            while(s != GlobalScope.scope) {
                if(s.iterating())
                    scopes.add(s);
                s = s.getEnclosingScope();
            }

            for(DataflowTaskArgDesc d:t) {
                if(d.task.getType() == DFType.PUT) {
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

        public Set<Variable<?>> getVariables() {
            Set<Variable<?>> s = new HashSet<>();
            if(sampleVariable != null)
                s.add(sampleVariable);
            s.addAll(perSampleVariables.keySet());
            s.addAll(accumulatorVariables.keySet());
            return s;
        }

        @Override
        public Iterator<Variable<?>> iterator() {
            return getVariables().iterator();
        }
    }

    private static final VariableDescription<DoubleVariable> evidenceProbName = VariableNames
            .logProbabilityName(VariableNames.internalName("evidence"));
    private static final VariableDescription<DoubleVariable> logModelProbName = VariableNames
            .logProbabilityName(VariableNames.internalName("model"));

    public static void probabilityFunctions(CompilationContext compilationCtx) {
        // Store the code for initialising intermediate variable probabilities.
        Set<WrappedTree<IRTree, IRTreeVoid>> toInitialize = new LinkedHashSet<>();

        // Construct a field for the overall model. This field needs to be public as it
        // is directly accessed by the runtime.
        compilationCtx.addConstructedClassField(logModelProbName, null, null, true, false, false, false, false, false,
                null, null);
        toInitialize.add(new WrappedTree<>(store(logModelProbName, constant(0.0), Tree.NoComment)));

        // Construct a field for the model evidence. This field needs to be public as it
        // is directly accessed by the runtime.
        compilationCtx.addConstructedClassField(evidenceProbName, null, null, true, false, false, false, false, false,
                null, null);
        toInitialize.add(new WrappedTree<>(store(evidenceProbName, constant(0.0), Tree.NoComment)));

        PriorityQueue<SampleTask<?, ?>> p = new PriorityQueue<>(compilationCtx.traces.getAllSampleTasks());
        while(!p.isEmpty()) {
            SampleTask<?, ?> sampleTask = p.poll();
            constructProbabilityMethod(sampleTask, toInitialize, false, compilationCtx);
            constructProbabilityMethod(sampleTask, toInitialize, true, compilationCtx);
        }

        constructIntermediateProbabilityInitialisationFunction(toInitialize, compilationCtx);
    }

    private static void constructIntermediateProbabilityInitialisationFunction(
            Set<WrappedTree<IRTree, IRTreeVoid>> toInitialize, CompilationContext compilationCtx) {
        // Collect all the variables. The map is used so that at a later stage we can
        // initialize only the
        // variables that are required for the function. Specifically evidence functions
        // don't require
        // All the probabilities, and some should be set to NaN.

        List<IRTreeVoid> trees = new ArrayList<>();

        for(WrappedTree<IRTree, IRTreeVoid> w:toInitialize)
            trees.add(w.tree);

        // This will make the order stable for testing. It is not required for the
        // correctness of the code
        // as there are no dependencies between trees.
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

    // TODO remove the observed variable.
    private static void constructProbabilityMethod(SampleTask<?, ?> sampleTask,
            Set<WrappedTree<IRTree, IRTreeVoid>> toInitialize, boolean useDistributions,
            CompilationContext compilationCtx) {
        // Find traces to any distributions that will need to be covered.
        Set<Set<TraceHandle>> distributionTraces;
        if(useDistributions) {
            distributionTraces = findDistributionTraces(sampleTask, compilationCtx);
            if(Traces.noDistributionTraces(distributionTraces) && !sampleTask.isDistribution())
                return;
        } else {
            distributionTraces = Traces.noDistributionTraces;
        }

        // Initialize the compilationCtx
        compilationCtx.initialize();
        compilationCtx.pushIsSerial(true);

        // Get a list of the variables we will need to construct.
        DependantVariables dependentVariables = new DependantVariables(sampleTask, compilationCtx);

        // Get the scopes we will use to store probabilities the random variables.
        RandomVariable<?, ?> random = sampleTask.randomVariable;
        List<ScopeDesc> randomScopes;
        Scope randomStoreScope;
        boolean constructIndividualProbabilities = compilationCtx.calculateIndividualProbabilities();
        if(constructIndividualProbabilities) {
            randomScopes = TreeUtils.getScopeDescs(random);
            randomStoreScope = random.scope();
        } else {
            randomScopes = new ArrayList<>();
            randomScopes.add(new ScopeDesc(GlobalScope.scope, null, false));
            randomStoreScope = GlobalScope.scope;
        }

        List<IRTreeReturn<IntVariable>> randomArgTrees = toArgTrees(getScopeArgs(randomScopes), compilationCtx);

        // Get the scopes we will use to store probabilities for samples.
        VariableDescription<DoubleVariable> storedName;
        List<ScopeDesc> storeScopes;
        List<IRTreeReturn<IntVariable>> storeArgTrees;
        boolean isPrivate;
        Scope sampleStoreScope;
        // Test if sample values need to be stored
        if(!dependentVariables.perSampleVariables.isEmpty()) {
            storedName = VariableNames.logProbabilityName(sampleTask.getUniqueVarDesc());
            storeScopes = TreeUtils.getScopeDescs(sampleTask.getOutput());
            isPrivate = true;
            sampleStoreScope = sampleTask.scope();
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
            isPrivate = (!sampleVariable.isIntermediate() && !sampleVariable.isSample()) || sampleVariable.isPrivate();
            sampleStoreScope = randomStoreScope;
            storeArgTrees = randomArgTrees;
        }

        // Construct field for random variable output;
        VariableDescription<DoubleVariable> randomName = VariableNames.logProbabilityName(random.getUniqueVarDesc());
        // Allocate space for the results. Using scopes here is ok because
        // random is allocated inside these scopes, so can only exist in this number of
        // dimensions.
        toInitialize.add(new WrappedTree<>(TreeUtils.constructVariable(randomName, constant(0.0), randomScopes,
                random.isPrivate(), compilationCtx)));

        List<ScopeDesc> outputScopes = new ArrayList<>();
        outputScopes.add(new ScopeDesc(GlobalScope.scope, null, false));
        for(Variable<?> intermediate:dependentVariables) {
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
                    intermediate.isPrivate(), intermediate.getComment(), compilationCtx);
            toInitialize.add(new WrappedTree<>(initializeIntermediate));
        }

        // Distributions have a probability of 1 which is achieved by not reducing the
        // value with the sample method.
        // TODO fix this as it is not correct.

        // Construct a space to store the probability.
        IRTreeVoid sampleAllocation = TreeUtils.constructVariable(storedName, constant(0.0), storeScopes, isPrivate,
                compilationCtx);

        // Construct a flag to determine if this probability is already calculated.
        VariableDescription<BooleanVariable> fixedFlagName = VariableNames.getProbabilityFixedFlag(sampleTask);
        compilationCtx.addConstructedClassField(fixedFlagName, null, null, false, false, false, false, false, false,
                constant(false), null);

        // Set the initialisation
        IRTreeReturn<BooleanVariable> guard = negateBoolean(load(fixedFlagName));
        toInitialize.add(new WrappedTree<IRTree, IRTreeVoid>(ifElse(guard, sampleAllocation, Tree.NoComment)));

        getInverseIR(sampleTask, dependentVariables, randomName, randomArgTrees, randomStoreScope, storedName,
                storeArgTrees, sampleStoreScope, distributionTraces, useDistributions, compilationCtx);

        // construct list of statements that will make up this function.
        List<IRTreeVoid> subtrees = new ArrayList<>();

        // Get the resulting tree.
        subtrees.add(compilationCtx.getOutermostScopeTree());

        // Test if we should set a fixed flag.
        VariableDescription<BooleanVariable> sampleFixedName = VariableNames.fixedFlagName(sampleTask);
        IRTreeReturn<BooleanVariable> fixed = load(sampleFixedName);
        // Add side effect to clear the flag if a dependency given the ability to changed
        IRTreeVoid restFixed = store(fixedFlagName, and(load(sampleFixedName), load(fixedFlagName)),
                "Should the probability of sample " + sampleTask.id()
                        + " be set to fixed. This will only every change the flag to false.");
        compilationCtx.addSetSideEffect(sampleFixedName, restFixed);
        
        // Add side effect to clear the flag if a dependency is changed.
        SampleTraceDesc sampleDesc = compilationCtx.traces.getSampleTrace(sampleTask);
        VariableDescription<?> sampleName = sampleDesc.sampleVariable.getUniqueVarDesc();
        restFixed = store(fixedFlagName, constant(false), "Unset the fixed probability flag for sample "
                + sampleTask.id() + " as it depends on " + sampleName.name + ".");
        compilationCtx.addSetSideEffect(sampleName, restFixed);

        // Priority queue for fixed ordering of assignments.
        PriorityQueue<SampleTask<?, ?>> p = new PriorityQueue<>();
        for(Variable<?> v:random.getParent().getInputs())
            p.addAll(compilationCtx.traces.getSourceSampleTasks(v));

        // Construct an expression to determine if the flag should be set.
        if(!p.isEmpty()) {
            while(!p.isEmpty()) {
                SampleTask<?, ?> s = p.poll();
                sampleFixedName = VariableNames.fixedFlagName(s);
                fixed = IRTree.and(fixed, load(sampleFixedName));

                // Add side effect to clear the flag if a dependency given the ability to changed
                restFixed = store(fixedFlagName, and(load(sampleFixedName), load(fixedFlagName)),
                        "Should the probability of sample " + sampleTask.id()
                                + " be set to fixed. This will only every change the flag to false.");
                compilationCtx.addSetSideEffect(sampleFixedName, restFixed);

                // Add side effect to clear the flag if a dependency is changed.
                sampleDesc = compilationCtx.traces.getSampleTrace(s);
                sampleName = sampleDesc.sampleVariable.getUniqueVarDesc();
                restFixed = store(fixedFlagName, constant(false), "Unset the fixed probability flag for sample "
                        + sampleTask.id() + " as it depends on " + sampleName.name + ".");
                compilationCtx.addSetSideEffect(sampleName, restFixed);
            }
        }
        subtrees.add(store(fixedFlagName, fixed,
                "Now the probability is calculated store if it can be cached or if it needs to be recalculated next time."));

        // Join all the subtrees with a sequential block
        IRTreeVoid generateValues = sequential(subtrees, "Generating probabilities for sample task");
        if(sampleTask.isDistribution() && useDistributions)
            generateValues = ifElse(load(VariableNames.fixedFlagName(sampleTask)), generateValues,
                    "Update the probability if the distribution is fixed to a specific value."
                            + " If it is not the value is implicitly log(1.0) so has no effect.");

        // Populate the behaviour if we just need to copy the pre-calculated results
        // back out.
        compilationCtx.pushScope();

        // Initialize variables
        VariableDescription<DoubleVariable> accumulatorName = VariableNames.calcVarName("accumulator",
                VariableType.DoubleVariable, true);
        compilationCtx.addTreeToScope(GlobalScope.scope,
                initializeVariable(accumulatorName, constant(0.0), Tree.NoComment));

        VariableDescription<DoubleVariable> rvProbabilityName = VariableNames.calcVarName("rvAccumulator",
                VariableType.DoubleVariable, true);
        compilationCtx.addTreeToScope(randomStoreScope,
                initializeVariable(rvProbabilityName, constant(0.0), Tree.NoComment));

        VariableDescription<DoubleVariable> sampleValueName = VariableNames.calcVarName("sampleValue",
                VariableType.DoubleVariable, true);
        if(storeArgTrees.isEmpty()) // We are not in a loop.
            compilationCtx.addTreeToScope(sampleStoreScope,
                    initializeVariable(sampleValueName, load(storedName), Tree.NoComment));
        else
            compilationCtx.addTreeToScope(sampleStoreScope, initializeVariable(sampleValueName,
                    TreeUtils.getIndirectValue(storedName, storeArgTrees), Tree.NoComment));

        // Accumulate values
        compilationCtx.addTreeToScope(sampleStoreScope,
                store(rvProbabilityName, addDD(load(rvProbabilityName), load(sampleValueName)), Tree.NoComment));
        compilationCtx.addTreeToScope(randomStoreScope,
                store(accumulatorName, addDD(load(accumulatorName), load(rvProbabilityName)), Tree.NoComment));

        // Update the values for the random variables
        IRTreeVoid assignments = updateRVProbabilities(load(rvProbabilityName), randomArgTrees, randomName, random);
        compilationCtx.addTreeToScope(randomStoreScope, assignments);
        // If we are tracking individual values, embed in the correct set of for loops.

        // Update the values for the variables
        updateVarProbabilities(sampleTask, load(accumulatorName), load(sampleValueName), dependentVariables,
                useDistributions, compilationCtx);

        IRTreeVoid repopulateValues = compilationCtx.getOutermostScopeTree();
        repopulateValues.prefixComment(
                "Updating random variable and model probabilities using cached probabilities for this sample");
        compilationCtx.popScope();

        // Merge the values in a conditional
        IRTreeReturn<BooleanVariable> mergeGuard = negateBoolean(load(fixedFlagName));
        IRTreeVoid mergeResult = ifElse(
                mergeGuard, generateValues, "Determine if we need to calculate the values for sample task "
                        + sampleTask.id() + " or if we should just use cached values.",
                repopulateValues, "Using cached values.");

        // And place the subtree in a void function.
        VariableDescription<?> taskName = sampleTask.getUniqueVarDesc();
        FunctionName functionName = FunctionName
                .createFunctionName(useDistributions ? VariableNames.logProbabilityDistributionName(taskName)
                        : VariableNames.logProbabilityValueName(taskName));
        String comment = "Calculate the probability of the samples represented by " + sampleTask.getUniqueVarDesc()
                + (useDistributions ? " using probability distributions." : " using sampled values.");
        SampleFunctionClass functionClass = useDistributions ? SampleFunctionClass.LOG_PROBABILITY_DISTRIBUTIONS
                : SampleFunctionClass.LOG_PROBABILITY_VALUE;
        compilationCtx.addFunction(functionClass, sampleTask,
                voidFunction(Visibility.PRIVATE, functionName, new ArgDesc<?>[0], mergeResult, comment));

        compilationCtx.popIsSerial();
    }

    private static void getInverseIR(SampleTask<?, ?> sTask, DependantVariables dependantVariables,
            VariableDescription<DoubleVariable> randomName, List<IRTreeReturn<IntVariable>> randomArgTrees,
            Scope randomStoreScope, VariableDescription<DoubleVariable> storedName,
            List<IRTreeReturn<IntVariable>> sampleArgTrees, Scope sampleStoreScope,
            Set<Set<TraceHandle>> distributionTraces, boolean useDistributions, CompilationContext compilationCtx) {

        SampleTraceDesc sampleVarDesc = compilationCtx.traces.getSampleTrace(sTask);
        TraceHandle traceHandle = sampleVarDesc.traceToSampleVariable;

        getInverseIRTrace(sTask, dependantVariables, sampleVarDesc.sampleVariable, traceHandle, randomName,
                randomArgTrees, randomStoreScope, storedName, sampleArgTrees, sampleStoreScope, distributionTraces,
                useDistributions, compilationCtx);
    }

    private static void getInverseIRTrace(SampleTask<?, ?> sTask, DependantVariables dependantVariables,
            Variable<?> current, TraceHandle traceHandle, VariableDescription<DoubleVariable> randomName,
            List<IRTreeReturn<IntVariable>> randomArgTrees, Scope randomStoreScope,
            VariableDescription<DoubleVariable> storedName, List<IRTreeReturn<IntVariable>> sampleArgTrees,
            Scope sampleStoreScope, Set<Set<TraceHandle>> distributionTraces, boolean useDistributions,
            CompilationContext compilationCtx) {

        // Calculate the global scope for these variables.
        GlobalScope globalScope = GlobalScope.scope;

        Scope sTaskScope = traceHandle.get(0).task.scope();

        /* Initialize accumulators */
        // Enter the scope of to force all allocations to the global scope to happen
        // before it.
        compilationCtx.enterScope(sTaskScope);

        // If the sample task is in another loop, we need to sum the values in that
        // loop.
        VariableDescription<DoubleVariable> accumulatorName = VariableNames.calcVarName("accumulator",
                VariableType.DoubleVariable, true);
        IRTreeReturn<DoubleVariable> accumulatorValue = load(accumulatorName);

        // Construct the RV accumulator.
        compilationCtx.addTreeToScope(globalScope, initializeVariable(accumulatorName, IRTree.constant(0.0),
                "Accumulator for probabilities of instances of the random variable"));

        // Name for a second accumulator in the case that the same task is inside
        // another for loop relative to the random variable
        VariableDescription<DoubleVariable> sampleAccumulatorName = VariableNames.calcVarName("sampleAccumulator",
                VariableType.DoubleVariable, true);
        IRTreeReturn<DoubleVariable> sampleAccumulatorValue = load(sampleAccumulatorName);

        // Construct the allocator.
        compilationCtx.addTreeToScope(randomStoreScope, initializeVariable(sampleAccumulatorName, IRTree.constant(0.0),
                "Accumulator for sample probabilities for a specific instance of the random variable."));

        // Having initialized all the state we can now leave the sample tasks scope.
        compilationCtx.leaveScope(sTaskScope);

        /* Explore the trace */
        IRTreeReturn<DoubleVariable> sampleProbability = distributionsProb(current, sTask, distributionTraces,
                traceHandle, compilationCtx);

        VariableDescription<DoubleVariable> sampleProbName = VariableNames.calcVarName("sampleProbability",
                VariableType.DoubleVariable, true);
        compilationCtx.addTreeToScope(sTaskScope,
                initializeVariable(sampleProbName, sampleProbability, Tree.NoComment));
        IRTreeReturn<DoubleVariable> sampleProbValue = load(sampleProbName);

        // Add statements to increment the accumulators.
        compilationCtx.addTreeToScope(sTaskScope,
                store(sampleAccumulatorName, addDD(sampleAccumulatorValue, load(sampleProbName)),
                        "Add the probability of this sample task to the sample task accumulator."));
        compilationCtx.addTreeToScope(randomStoreScope, store(accumulatorName,
                addDD(accumulatorValue, sampleAccumulatorValue),
                "Add the probability of this instance of the random variable to the probability of all instances of the random variable."));

        // Update the RV probability
        IRTreeVoid rvAssignment = updateRVProbabilities(sampleAccumulatorValue, randomArgTrees, randomName,
                sTask.randomVariable);
        compilationCtx.addTreeToScope(randomStoreScope, rvAssignment);

        // Update the stored probability for the random variable.
        IRTreeVoid updateSample;
        if(sampleStoreScope == sTaskScope) { // Storing each sample value.
            updateSample = TreeUtils.putIndirectValue(storedName, sampleArgTrees, sampleProbValue,
                    "Store the sample task probability");
        } else if(sampleStoreScope == sTask.randomVariable.scope()) { // Store the accumulated value for
            // each random variable.
            updateSample = TreeUtils.putIndirectValue(storedName, sampleArgTrees, sampleAccumulatorValue,
                    "Store the random variable instance probability");
        } else {
            updateSample = TreeUtils.putIndirectValue(storedName, sampleArgTrees, accumulatorValue,
                    "Store the random variable instance probability");
        }
        compilationCtx.addTreeToScope(sampleStoreScope, updateSample);

        // Update the probability of the model variables.
        updateVarProbabilities(sTask, accumulatorValue, load(sampleProbName), dependantVariables, useDistributions,
                compilationCtx);
    }

    private static <A extends Variable<A>> IRTreeReturn<DoubleVariable> distributionsProb(Variable<?> start,
            SampleTask<A, ?> sampleTask, Set<Set<TraceHandle>> distributionTraces, TraceHandle traceHandle,
            CompilationContext compilationCtx) {
        Scope sampleTaskScope = sampleTask.scope();

        // Accumulator for the probabilities seen in the different distribution
        // configurations.
        VariableDescription<DoubleVariable> disAccumulator = VariableNames.calcVarName("distributionAccumulator",
                VariableType.DoubleVariable, true);
        compilationCtx.addTreeToScope(sampleTaskScope, initializeVariable(disAccumulator,
                constant(Double.NEGATIVE_INFINITY), "An accumulator for log probabilities."));

        // Accumulator for the probability spaces reached through the distributions.
        VariableDescription<DoubleVariable> probabilityReached = VariableNames.calcVarName("probabilityReached",
                VariableType.DoubleVariable, true);
        compilationCtx.addTreeToScope(sampleTaskScope, initializeVariable(probabilityReached, constant(0.0),
                "An accumulator for the distributed probability space covered."));

        // Construct holder for new scopes.
        ScopeConstructor a = ScopeConstructor
                .construct(
                        sampleTask, distributionTraces, "Look for paths between the variable and the sample task "
                                + sampleTask.id() + " including any distribution values.",
                        Tree.NoComment, compilationCtx);
        a = a.addConstraint(traceHandle);

        VariableDescription<A> sampleVariableName = VariableNames.calcVarName("sampleValue", sampleTask.getOutputType(),
                true);

        a.addTree(0, (TreeBuilderInfo info) -> {
            IRTreeReturn<?> current = start.getForwardIR(compilationCtx);
            int index = traceHandle.size() - 1;
            BackTraceInfo backTraceInfo = new BackTraceInfo();
            while(index >= 0) {
                DataflowTaskArgDesc d = traceHandle.get(index--);
                ProducingDataflowTask<?> t = d.task;

                switch(t.getType()) {
                    case SAMPLE:
                        break;
                    default:
                        current = t.getInverseIR(d.argPos, current, backTraceInfo, compilationCtx);
                }
            }

            compilationCtx.addTreeToScope(sampleTask.scope(), IRTree.initializeVariable(sampleVariableName,
                    (IRTreeReturn<A>) current, "The sample value to calculate the probability of generating"));
        });

        a = a.applyAllDistributedArguments();

        IRTreeReturn<A> sampleValue = load(sampleVariableName);
        a.addTree(0, (TreeBuilderInfo info) -> {

            // Calculate the probability of the value in current. If this happens inside an
            // iterating scope this may be called many times with different values.
            IRTreeReturn<DoubleVariable> sampleProbability = getSampleProbability(sampleTask.randomVariable,
                    sampleValue, true, compilationCtx);

            // Store the value of the function call, so the function call is only made once.
            VariableDescription<DoubleVariable> weightedProbability = VariableNames.calcVarName("weightedProbability",
                    VariableType.DoubleVariable, true);
            compilationCtx.addTreeToScope(sampleTaskScope,
                    initializeVariable(weightedProbability, addDD(log(info.probability), sampleProbability),
                            "Store the value of the function call, so the function call is only made once."));

            compilationCtx.addTreeToScope(sampleTaskScope,
                    TreeUtils.lseAdd(load(disAccumulator), load(weightedProbability), disAccumulator,
                            "Add the probability of this sample task to the distribution accumulator."));

            // Update the probability space seen
            compilationCtx.addTreeToScope(sampleTaskScope,
                    store(probabilityReached, addDD(load(probabilityReached), info.probability),
                            "Add the probability of this distribution configuration to the accumulator."));
        });

        IRTreeVoid normalise = ifElse(eq(load(probabilityReached), constant(0.0)),
                store(disAccumulator, constant(Double.NEGATIVE_INFINITY),
                        "Return negative infinity if no distribution probability space is reached."),
                Tree.NoComment, store(disAccumulator, subtractDD(load(disAccumulator), log(load(probabilityReached))),
                        "Scale the probability relative to the observed distribution space."),
                Tree.NoComment);
        compilationCtx.addTreeToScope(sampleTaskScope, normalise);

        return load(disAccumulator);
    }

    private static Set<Set<TraceHandle>> findDistributionTraces(SampleTask<?, ?> sTask,
            CompilationContext compilationCtx) {
        return Traces.findDistributionTraces(sTask.randomVariable, compilationCtx);
    }

    private static IRTreeReturn<?>[] constructArguments(RandomVariable<?, ?> random, IRTreeReturn<?> value,
            CompilationContext compilationCtx) {
        List<Variable<?>> randomInputs = random.getParent().getInputs();
        int noInputs = randomInputs.size();
        IRTreeReturn<?>[] args = new IRTreeReturn<?>[noInputs + 1];
        args[0] = value;
        for(int i = 0; i < noInputs; i++) {
            Variable<?> v = randomInputs.get(i);
            constructInput(v, compilationCtx);
            args[i + 1] = loadArg(v, compilationCtx);
        }
        return args;
    }

    public static IRTreeReturn<DoubleVariable> getSampleProbability(RandomVariable<?, ?> random,
            IRTreeReturn<?> current, boolean log, CompilationContext compilationCtx) {

        IRTreeReturn<?>[] args = constructArguments(random, current, compilationCtx);
        return functionCallReturn(log ? FunctionType.LOG_PROBABILITY : FunctionType.PROBABILITY,
                VariableType.DoubleVariable, random.getType(), args);
    }

    private static void updateVarProbabilities(SampleTask<?, ?> sTask,
            IRTreeReturn<DoubleVariable> accumulatedProbability, IRTreeReturn<DoubleVariable> sampleProbability,
            DependantVariables variables, boolean useDistributions, CompilationContext compilationCtx) {
        {
            Map<Variable<?>, VariableDescription<BooleanVariable>> guards = new HashMap<>();

            // Initialize guards

            PriorityQueue<Variable<?>> p = new PriorityQueue<>(variables.perSampleVariables.keySet());
            while(!p.isEmpty()) {
                Variable<?> v = p.poll();
                Variable<?> instance = v.instanceHandle();
                VariableDescription<BooleanVariable> guardName = guards.get(instance);
                if(guardName == null) {
                    guardName = VariableNames.calcVarName("guard", instance.getUniqueVarDesc().name.getName(),
                            VariableType.BooleanVariable, true);
                    guards.put(instance, guardName);
                    compilationCtx.addTreeToScope(sTask.scope(),
                            initializeVariable(guardName, constant(false), "Guard to ensure that "
                                    + instance.getUniqueVarDesc() + " is only updated once for this probability."));
                }
            }

            p.addAll(variables.accumulatorVariables.keySet());
            while(!p.isEmpty()) {
                Variable<?> v = p.poll();
                Variable<?> instance = v.instanceHandle();
                VariableDescription<BooleanVariable> guardName = guards.get(instance);
                if(guardName == null) {
                    guardName = VariableNames.calcVarName("guard", instance.getUniqueVarDesc().name.getName(),
                            VariableType.BooleanVariable, true);
                    guards.put(instance, guardName);
                    compilationCtx.addTreeToScope(GlobalScope.scope,
                            initializeVariable(guardName, constant(false), "Guard to ensure that "
                                    + instance.getUniqueVarDesc() + " is only updated once for this probability."));
                }
            }

            // Set sample value
            Variable<?> sampleVariable = variables.getSampleVariable();
            if(sampleVariable != null) {
                compilationCtx.addTreeToScope(GlobalScope.scope, setValueProbability(accumulatedProbability,
                        sampleVariable, null, useDistributions, compilationCtx));
            }

            // Rest the priority queue.
            if(!variables.perSampleVariables.isEmpty()) {
                ScopeConstructor sPerSample = ScopeConstructor.construct(sTask,
                        "Add probability to constructed variables that have guards, so need per sample probabilities from the combined probability",
                        compilationCtx);

                p.addAll(variables.perSampleVariables.keySet());
                while(!p.isEmpty()) {
                    Variable<?> v = p.poll();
                    Set<TraceHandle> traces = variables.perSampleVariables.get(v);
                    VariableDescription<BooleanVariable> guardName = guards.get(v.instanceHandle());

                    // Add variables that require per variable values
                    ScopeConstructor perVarScopes = sPerSample.addConstraints(traces, true);
                    perVarScopes.addTree((TreeBuilderInfo info) -> compilationCtx.addTreeToScope(GlobalScope.scope,
                            setValueProbability(sampleProbability, v, guardName, useDistributions, compilationCtx)));
                }
            }

            if(!variables.accumulatorVariables.isEmpty()) {
                ScopeConstructor sAccumulator = ScopeConstructor.construct(sTask, GlobalScope.scope,
                        "Add probability to constructed variables from the combined probability", compilationCtx);

                p.addAll(variables.accumulatorVariables.keySet());
                while(!p.isEmpty()) {
                    Variable<?> v = p.poll();
                    Set<TraceHandle> traces = variables.accumulatorVariables.get(v);
                    VariableDescription<BooleanVariable> guardName = guards.get(v.instanceHandle());

                    // Add variables that require per variable values
                    ScopeConstructor perVarScopes = sAccumulator.addConstraints(traces, true);
                    perVarScopes.addTree((TreeBuilderInfo info) -> compilationCtx.addTreeToScope(GlobalScope.scope,
                            setValueProbability(accumulatedProbability, v, guardName, useDistributions,
                                    compilationCtx)));

                }
            }
        }

        // merge in to Model probability.
        {
            IRTreeReturn<DoubleVariable> outputValue = load(logModelProbName);
            IRTreeReturn<DoubleVariable> mergedValue = addDD(outputValue, accumulatedProbability);
            compilationCtx.addTreeToScope(GlobalScope.scope,
                    store(logModelProbName, mergedValue, "Add probability to model"));
        }

        // merge into evidence
        {
            IRTreeReturn<DoubleVariable> outputValue = load(evidenceProbName);
            IRTreeReturn<DoubleVariable> mergedValue = addDD(outputValue, accumulatedProbability);
            IRTreeVoid t = store(evidenceProbName, mergedValue, Tree.NoComment);

            if(!compilationCtx.traces.isObserved(sTask)) {
                IRTreeReturn<BooleanVariable> guard = load(VariableNames.fixedFlagName(sTask));
                t = IRTree.ifElse(guard, t,
                        "If this value is fixed, add it to the probability of this model producing the fixed values");
            }
            compilationCtx.addTreeToScope(GlobalScope.scope, t);
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
            for(SampleTask<?, ?> sTask:compilationCtx.traces.getSourceSampleTasks(v))
                fixedGuard = and(fixedGuard, load(VariableNames.fixedFlagName(sTask)));
            guardedTree = ifElse(fixedGuard, guardedTree,
                    "Make sure all the inputs have been fixed so the variable is not a distribution.");
        }

        return guardedTree;
    }

    private static IRTreeVoid updateRVProbabilities(IRTreeReturn<DoubleVariable> sampleProbability,
            List<IRTreeReturn<IntVariable>> argTrees, VariableDescription<DoubleVariable> randomName,
            RandomVariable<?, ?> rv) {

        Set<DataflowTask<?>> consumers = rv.getConsumers();
        boolean multiple = consumers.size() > 1;
        for(DataflowTask<?> c:consumers) {
            if(c.getType() == DFType.PUT)
                throw new CompilerException("If random variable can be put into arrays then"
                        + " the arrays need to have traces followed to determine how many sample tasks consume the random variable.");
        }

        // Calculate for the random sample
        if(multiple) {
            IRTreeReturn<DoubleVariable> outputValue = getIndirectValue(randomName, argTrees);
            IRTreeReturn<DoubleVariable> mergedValue = addDD(outputValue, sampleProbability);
            return putIndirectValue(randomName, argTrees, mergedValue, Tree.NoComment);
        } else
            return putIndirectValue(randomName, argTrees, sampleProbability, Tree.NoComment);
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
