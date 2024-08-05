/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.inference.metropolisHastings;

import static org.sandwood.compiler.trees.irTree.IRTree.constant;
import static org.sandwood.compiler.trees.irTree.IRTree.eq;
import static org.sandwood.compiler.trees.irTree.IRTree.functionCallReturn;
import static org.sandwood.compiler.trees.irTree.IRTree.ifElse;
import static org.sandwood.compiler.trees.irTree.IRTree.initializeVariable;
import static org.sandwood.compiler.trees.irTree.IRTree.lessThanEqual;
import static org.sandwood.compiler.trees.irTree.IRTree.load;
import static org.sandwood.compiler.trees.irTree.IRTree.log;
import static org.sandwood.compiler.trees.irTree.IRTree.store;
import static org.sandwood.compiler.trees.irTree.IRTree.subtractDD;
import static org.sandwood.compiler.trees.irTree.IRTree.treeScope;

import java.util.List;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.compilation.inference.InferenceGeneratorArrayProb;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

/**
 * A class to perform Metropolis-Harstings on array samples. To prevent probabilities going to zero the probabilities
 * are generated and stored in log space.
 */
public abstract class MetropolisHastingsArrayFunctions<A extends Variable<A>, B extends RandomVariable<ArrayVariable<A>, B>>
        extends InferenceGeneratorArrayProb<A, B, MetropolisHastingsArrayFunctions.MetropolisHastingsArrayData<A, B>> {
    protected static class MetropolisHastingsArrayData<A extends Variable<A>, B extends RandomVariable<ArrayVariable<A>, B>>
            extends InferenceGeneratorArrayProb.ArrayProbFunctionData<A, B> {

        /**
         * Value used to capture the tree that sets the new value. This is done so that the value can be placed in a
         * scope to prevent any name collisions with the code to reset in the event of a failed proposal.
         */
        public IRTreeVoid sampleTree;
        public List<IRTreeReturn<?>> consumerRVArgs;

        protected MetropolisHastingsArrayData(SampleTask<ArrayVariable<A>, B> sample,
                CompilationContext compilationCtx) {
            super(sample, IntVariable.intVariable(2), compilationCtx);
        }
    }

    private static final VariableDescription<DoubleVariable> originalProbabilityName = VariableNames
            .calcVarName("originalProbability", VariableType.DoubleVariable, true);
    private static final VariableDescription<DoubleVariable> proposedProbabilityName = VariableNames
            .calcVarName("proposedProbability", VariableType.DoubleVariable, true);

    @Override
    protected String getInferenceType() {
        return "Metropolis-Hastings";
    }

    @Override
    protected MetropolisHastingsArrayData<A, B> getFunctionData(SampleTask<ArrayVariable<A>, B> sample,
            CompilationContext compilationCtx) {
        return new MetropolisHastingsArrayData<>(sample, compilationCtx);
    }

    /**
     * Allocate storage for the probabilities of generating each of these. Each of these probabilities will be extended
     * with the probability of generating sample, and the probability of each consuming RVs generating there samples
     * samples given the current and proposed values.
     */
    @Override
    protected void constructFunctionVariablesProb(CompilationContext compilationCtx,
            MetropolisHastingsArrayData<A, B> funcData) {
        // Original Probability
        IRTreeReturn<DoubleVariable> probOriginalValue = constant(0.0);
        IRTreeVoid originalProbability = initializeVariable(originalProbabilityName, probOriginalValue,
                "Calculate the probability of the random variable generating the original sampled value.");
        compilationCtx.addTreeToScope(GlobalScope.scope, originalProbability);

        // Create a space for the probability of the proposed value.
        IRTreeReturn<DoubleVariable> probProposedValue = constant(0.0);
        IRTreeVoid proposedProbability = initializeVariable(proposedProbabilityName, probProposedValue,
                "The probability of the random variable generating the new sample value.");
        compilationCtx.addTreeToScope(GlobalScope.scope, proposedProbability);
    }

    // No global state is required.
    @Override
    protected void allocateGlobalStateProb(MetropolisHastingsArrayData<A, B> funcData,
            CompilationContext compilationCtx) {}

    /**
     * This method contains the meat of the code generation for this inference step. First we generate the intermediate
     * as normal. We then calculate the probability of the new values. If this passes the guard the function exits. If
     * they don't the function reverts.
     * <p>
     * TODO make the lower bound of the uniform distribution a user configurable parameter.
     *
     * @param funcData       the function data for this inference function generator.
     * @param compilationCtx The compilation context for this compilation process.
     */
    @Override
    protected void addSampleValueTree(MetropolisHastingsArrayData<A, B> funcData, CompilationContext compilationCtx) {

        IRTreeReturn<DoubleVariable> ratio = subtractDD(load(proposedProbabilityName), load(originalProbabilityName));
        VariableDescription<DoubleVariable> ratioName = VariableNames.calcVarName("ratio", VariableType.DoubleVariable,
                true);
        compilationCtx.addTreeToScope(GlobalScope.scope, initializeVariable(ratioName, ratio,
                "Ratio of the probability of proposed and original sample values"));
        IRTreeReturn<DoubleVariable> bound = log(functionCallReturn(FunctionType.SAMPLE, VariableType.DoubleVariable,
                VariableType.Uniform, constant(0.0), constant(1.0)));
        // This needs to be less than or equal as otherwise if the proposed value is not
        // possible and the random value is 0 an impossible value will be accepted.
        IRTreeReturn<BooleanVariable> guard = lessThanEqual(ratio, bound);

        // Update set tree to include resetting of the sample value, and then reusing
        // the tree to set all the intermediate variables etc.
        constructSetSampleValueTree(funcData, compilationCtx);
        IRTreeVoid tree = funcData.sampleTree;
        tree.prefixComment("If it is not revert the sample value and intermediates to their original values.");

        compilationCtx.addTreeToScope(GlobalScope.scope, ifElse(guard, tree,
                "Test if the probability of the sample is sufficient to keep the value. This needs to be less "
                        + "than or equal as otherwise if the proposed value is not possible and the random value is 0 "
                        + "an impossible value will be accepted."));
    }

    /**
     * Method so that the value can be collected and placed in a scope tree to prevent name collisions on any generated
     * variables.
     *
     * @param funcData       The function data for this inference function generator.
     * @param compilationCtx The compilation context for this compilation process.
     */
    private void constructSetSampleValueTree(MetropolisHastingsArrayData<A, B> funcData,
            CompilationContext compilationCtx) {
        compilationCtx.pushScope();
        super.addSampleValueTree(funcData, compilationCtx);
        funcData.sampleTree = compilationCtx.getOutermostScopeTree();
        funcData.sampleTree.postfixComment("Set the sample value");
        compilationCtx.popScope();
    }

    /**
     * This method is overridden so that the value it returns can be changed depending on if the proposal was
     * unsuccessful and needs to be rolled back.
     */
    @Override
    protected IRTreeVoid calculateSampleValue(MetropolisHastingsArrayData<A, B> funcData,
            CompilationContext compilationCtx) {
        return updateSampleValue(funcData, true);
    }

    @Override
    protected void setSampleValue(MetropolisHastingsArrayData<A, B> funcData, CompilationContext compilationCtx) {
        compilationCtx.pushScope();
        funcData.sampleDesc.updateSample(updateSampleValue(funcData, false), compilationCtx);
        IRTreeVoid sampleUpdate = compilationCtx.getOutermostScopeTree();
        compilationCtx.popScope();
        IRTreeVoid combinedTree = treeScope(sampleUpdate, "Update Sample and intermediate values");
        compilationCtx.addTreeToScope(GlobalScope.scope,
                ifElse(eq(funcData.valuePos, constant(1)), combinedTree, Tree.NoComment));
    }

    protected abstract IRTreeVoid updateSampleValue(MetropolisHastingsArrayData<A, B> funcData, boolean resetting);

    @Override
    protected void saveBackTraceProbability(MetropolisHastingsArrayData<A, B> funcData,
            IRTreeReturn<DoubleVariable> value, CompilationContext compilationCtx) {
        compilationCtx.addTreeToScope(GlobalScope.scope, ifElse(eq(funcData.valuePos, constant(0)),
                store(originalProbabilityName, value, Tree.NoComment), "Save the probability of the original value.",
                store(proposedProbabilityName, value, Tree.NoComment), "Save the probability of the proposed value."));
    }
}
