/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
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
import static org.sandwood.compiler.trees.irTree.IRTree.or;
import static org.sandwood.compiler.trees.irTree.IRTree.store;
import static org.sandwood.compiler.trees.irTree.IRTree.subtractDD;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.ExternalFunction;
import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.compilation.inference.InferenceGeneratorArrayProb;
import org.sandwood.compiler.compilation.util.TreeUtils;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.LocalVariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.traces.guards.ScopeConstructor;
import org.sandwood.compiler.traces.guards.TreeBuilderInfo;
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

        public ScopeConstructor sampleTargetScope;

        protected MetropolisHastingsArrayData(SampleTask<ArrayVariable<A>, B> sample,
                CompilationContext compilationCtx) {
            super(sample, IntVariable.intVariable(2), compilationCtx);
        }
    }

    private static final LocalVariableDescription<DoubleVariable> originalProbabilityName = VariableNames
            .localCalcVarName("originalProbability", VariableType.DoubleVariable, true);
    private static final LocalVariableDescription<DoubleVariable> proposedProbabilityName = VariableNames
            .localCalcVarName("proposedProbability", VariableType.DoubleVariable, true);

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
     * Method to construct the scope for the mMetropolisHastings trials to occur in, complete with guards for early
     * termination if there is no observed data.
     * 
     * @param funcData The functionData.
     * @return The scope to operate in.
     * 
     */
    @Override
    protected ScopeConstructor getBackTraceScope(MetropolisHastingsArrayData<A, B> funcData) {
        ScopeConstructor targetScope = super.getBackTraceScope(funcData);
        IRTreeReturn<BooleanVariable> guard = or(
                TreeUtils.getIsConstrained(funcData.sampleDesc.sample, funcData.compilationCtx),
                eq(funcData.valuePos, constant(0)));
        targetScope = targetScope.addCondition(guard).ifScopeConstructor();
        funcData.sampleTargetScope = targetScope;
        return targetScope;
    }

    @Override
    protected ScopeConstructor getSampleTaskScope(MetropolisHastingsArrayData<A, B> funcData) {
        return funcData.sampleTargetScope;
    }

    /**
     * Allocate storage for the probabilities of generating each of these. Each of these probabilities will be extended
     * with the probability of generating sample, and the probability of each consuming RVs generating there samples
     * samples given the current and proposed values.
     */
    @Override
    protected void constructFunctionVariablesProb(MetropolisHastingsArrayData<A, B> funcData) {
        funcData.targetScope.addTree((TreeBuilderInfo info) -> {
            // Original Probability
            IRTreeReturn<DoubleVariable> probOriginalValue = constant(0.0);
            IRTreeVoid originalProbability = initializeVariable(originalProbabilityName, probOriginalValue,
                    "Calculate the probability of the random variable generating the original sampled value.");
            info.compilationCtx.addTreeToScope(GlobalScope.scope, originalProbability);

            // Create a space for the probability of the proposed value.
            IRTreeReturn<DoubleVariable> probProposedValue = constant(0.0);
            IRTreeVoid proposedProbability = initializeVariable(proposedProbabilityName, probProposedValue,
                    "The probability of the random variable generating the new sample value.");
            info.compilationCtx.addTreeToScope(GlobalScope.scope, proposedProbability);
        });
    }

    // No global state is required.
    @Override
    protected void allocateGlobalStateProb(MetropolisHastingsArrayData<A, B> funcData) {}

    /**
     * This method contains the meat of the code generation for this inference step. First we generate the intermediate
     * as normal. We then calculate the probability of the new values. If this passes the guard the function exits. If
     * they don't the function reverts.
     * <p>
     * TODO make the lower bound of the uniform distribution a user configurable parameter.
     *
     * @param funcData the function data for this inference function generator.
     */
    @Override
    protected void addSampleValueTree(MetropolisHastingsArrayData<A, B> funcData) {

        LocalVariableDescription<DoubleVariable> ratioName = VariableNames.localCalcVarName("ratio", VariableType.DoubleVariable,
                true);
        IRTreeReturn<DoubleVariable> ratio = subtractDD(load(proposedProbabilityName), load(originalProbabilityName));
        funcData.compilationCtx.addTreeToScope(GlobalScope.scope, initializeVariable(ratioName, ratio,
                "Ratio of the probability of proposed and original sample values"));

        IRTreeReturn<DoubleVariable> bound = log(functionCallReturn(FunctionType.SAMPLE, VariableType.DoubleVariable,
                VariableType.Uniform, constant(0.0), constant(1.0)));
        // This needs to be less than or equal as otherwise if the proposed value is not
        // possible and the random value is 0 an impossible value will be accepted.
        IRTreeReturn<BooleanVariable> guard = lessThanEqual(load(ratioName), bound);
        guard = or(guard, functionCallReturn(ExternalFunction.IS_NAN, VariableType.BooleanVariable, load(ratioName)));

        ScopeConstructor targetScope = ScopeConstructor.construct(funcData.sampleDesc.sample,
                "Test if the probability of the sample is sufficient to keep the value. This needs to be less than or equal "
                        + "as otherwise if the proposed value is not possible and the random value is 0 an impossible value will be "
                        + "accepted.",
                funcData.compilationCtx);
        targetScope = targetScope.addCondition(eq(funcData.valuePos, constant(1))).ifScopeConstructor();
        targetScope = targetScope.addCondition(guard).ifScopeConstructor();
        targetScope = targetScope
                .addComment("If it is not revert the sample value and intermediates to their original values.");

        // Update set tree to include resetting of the sample value, and then reusing
        // the tree to set all the intermediate variables etc.
        targetScope = targetScope.addComment("Set the sample value");
        targetScope.addTree((TreeBuilderInfo info) -> {
            super.addSampleValueTree(funcData);
        });
    }

    /**
     * This method is overridden so that the value it returns can be changed depending on if the proposal was
     * unsuccessful and needs to be rolled back.
     */
    @Override
    protected IRTreeVoid calculateSampleValue(MetropolisHastingsArrayData<A, B> funcData) {
        return updateSampleValue(funcData, true);
    }

    @Override
    protected void setSampleValue(MetropolisHastingsArrayData<A, B> funcData) {
        ScopeConstructor sc = ScopeConstructor.construct(funcData.sampleDesc.sample, Tree.NoComment,
                funcData.compilationCtx);
        sc = sc.addCondition(eq(funcData.valuePos, constant(1))).ifScopeConstructor();
        sc = sc.addIsolation("Update Sample and intermediate values");

        sc.addTree((TreeBuilderInfo info) -> {
            funcData.sampleDesc.updateSample(updateSampleValue(funcData, false), info.compilationCtx);
        });
    }

    protected abstract IRTreeVoid updateSampleValue(MetropolisHastingsArrayData<A, B> funcData, boolean resetting);

    @Override
    protected void saveBackTraceProbability(MetropolisHastingsArrayData<A, B> funcData,
            IRTreeReturn<DoubleVariable> value) {
        funcData.compilationCtx.addTreeToScope(GlobalScope.scope, ifElse(eq(funcData.valuePos, constant(0)),
                store(originalProbabilityName, value, Tree.NoComment), "Save the probability of the original value.",
                store(proposedProbabilityName, value, Tree.NoComment), "Save the probability of the proposed value."));
    }
}
