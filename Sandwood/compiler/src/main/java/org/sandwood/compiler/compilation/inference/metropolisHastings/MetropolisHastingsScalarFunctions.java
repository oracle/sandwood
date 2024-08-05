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
import static org.sandwood.compiler.trees.irTree.IRTree.or;
import static org.sandwood.compiler.trees.irTree.IRTree.sequential;
import static org.sandwood.compiler.trees.irTree.IRTree.store;
import static org.sandwood.compiler.trees.irTree.IRTree.subtractDD;
import static org.sandwood.compiler.trees.irTree.IRTree.treeScope;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.ExternalFunction;
import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.compilation.inference.InferenceGeneratorScalarProb;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.ScalarVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.traces.TraceHandle;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

/**
 * A class to perform Metropolis-Harstings to generate a sample value. To prevent probabilities going to zero the
 * probabilities are generated and stored in log space.
 */

public abstract class MetropolisHastingsScalarFunctions<A extends ScalarVariable<A>, B extends RandomVariable<A, B>>
        extends InferenceGeneratorScalarProb<A, B, MetropolisHastingsScalarFunctions.MetropolisHastingsData<A, B>> {
    protected static class MetropolisHastingsData<A extends ScalarVariable<A>, B extends RandomVariable<A, B>>
            extends InferenceGeneratorScalarProb.ScalarProbFunctionData<A, B> {
        final VariableDescription<DoubleVariable> originalProbabilityName;
        final VariableDescription<DoubleVariable> proposedProbabilityName;
        final VariableDescription<A> originalValueName;
        final VariableDescription<A> proposedValueName;
        /**
         * Value used to capture the tree that sets the new value. This is done so that the value can be placed in a
         * scope to prevent any name collisions with the code to reset in the event of a failed proposal.
         */
        IRTreeVoid sampleTree;

        protected MetropolisHastingsData(SampleTask<A, B> sample, CompilationContext compilationCtx) {
            super(sample, IntVariable.intVariable(2), compilationCtx);
            originalProbabilityName = VariableNames.calcVarName("originalProbability", VariableType.DoubleVariable,
                    true);
            originalValueName = VariableNames.calcVarName("originalValue", sample.getOutputType(), true);
            proposedProbabilityName = VariableNames.calcVarName("proposedProbability", VariableType.DoubleVariable,
                    true);
            proposedValueName = VariableNames.calcVarName("proposedValue", sample.getOutputType(), true);
        }
    }

    @Override
    protected String getInferenceType() {
        return "Metropolis-Hastings";
    }

    @Override
    protected MetropolisHastingsData<A, B> getFunctionData(SampleTask<A, B> sample, CompilationContext compilationCtx) {
        return new MetropolisHastingsData<>(sample, compilationCtx);
    }

    /**
     * Allocate space to store the current value and the proposed sample value, and allocate storage for the
     * probabilities of generating each of these. Each of these probabilities will be extended with the probability of
     * generating sample, and the probability of each consuming RVs generating there samples samples given the current
     * and proposed values.
     * <p>
     * TODO Make the parameters of the normal distribution generating the proposals user configurable.
     */
    @Override
    protected void constructFunctionVariablesProb(CompilationContext compilationCtx,
            MetropolisHastingsData<A, B> funcData) {
        IRTreeReturn<A> originalValueInit = constructOriginal(funcData, compilationCtx);
        IRTreeVoid originalValueTreeInit = initializeVariable(funcData.originalValueName, originalValueInit,
                "The original value of the sample");
        compilationCtx.addTreeToScope(GlobalScope.scope, originalValueTreeInit);

        IRTreeReturn<DoubleVariable> probOriginalValue = constant(0.0);
        IRTreeVoid originalProbability = initializeVariable(funcData.originalProbabilityName, probOriginalValue,
                "The probability of the random variable generating the originally sampled value");
        compilationCtx.addTreeToScope(GlobalScope.scope, originalProbability);

        getProposedValue(funcData, compilationCtx);

        IRTreeReturn<DoubleVariable> probProposedValue = constant(0.0);
        IRTreeVoid proposedProbability = initializeVariable(funcData.proposedProbabilityName, probProposedValue,
                "The probability of the random variable generating the new sample value.");
        compilationCtx.addTreeToScope(GlobalScope.scope, proposedProbability);
    }

    protected abstract void getProposedValue(MetropolisHastingsData<A, B> funcData, CompilationContext compilationCtx);

    // No global state is required.
    @Override
    protected void allocateGlobalStateProb(MetropolisHastingsData<A, B> funcData, CompilationContext compilationCtx) {}

    /**
     * Method to back track through the trace from the original stored value to the sample task to construct the value
     * it generated.
     * 
     * @param funcData
     * @param compilationCtx
     * @return
     */
    private IRTreeReturn<A> constructOriginal(MetropolisHastingsData<A, B> funcData,
            CompilationContext compilationCtx) {
        TraceHandle trace = funcData.sampleDesc.sampleVarDesc.traceToSampleVariable;
        /*
         * The trace comes from the sample value and so does not have any arithmetic performed on it, instead at most it
         * will be a sequence of puts, and so this is safe. To generate the back info would require constructing a
         * constraint we know to be empty to handle a back trace we know does not need this information. Given this the
         * complexity of the constraint is worse that the empty back information object.
         */
        BackTraceInfo backTraceInfo = new BackTraceInfo();

        int index = trace.size() - 1;
        DataflowTaskArgDesc d = trace.get(index--);
        ProducingDataflowTask<?> t = d.task;

        IRTreeReturn<?> current = funcData.sampleDesc.sampleVarDesc.sampleVariable.getForwardIR(compilationCtx);

        while(true) {
            switch(t.getType()) {
                case SAMPLE:
                    return (IRTreeReturn<A>) current;

                default:
                    current = t.getInverseIR(d.argPos, current, backTraceInfo, compilationCtx);
                    d = trace.get(index--);
                    t = d.task;
            }
        }
    }

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
    protected void addSampleValueTree(MetropolisHastingsData<A, B> funcData, CompilationContext compilationCtx) {
        VariableDescription<DoubleVariable> ratioName = VariableNames.calcVarName("ratio", VariableType.DoubleVariable,
                true);
        IRTreeReturn<DoubleVariable> ratio = subtractDD(load(funcData.proposedProbabilityName),
                load(funcData.originalProbabilityName));
        compilationCtx.addTreeToScope(GlobalScope.scope, initializeVariable(ratioName, ratio,
                "The probability ration for the proposed value and the current value."));
        IRTreeReturn<DoubleVariable> bound = log(functionCallReturn(FunctionType.SAMPLE, VariableType.DoubleVariable,
                VariableType.Uniform, constant(0.0), constant(1.0)));
        // This needs to be less than or equal as otherwise if the proposed value is not possible and the random value
        // is 0 an impossible value will be accepted.
        IRTreeReturn<BooleanVariable> guard = lessThanEqual(ratio, bound);
        guard = or(guard, functionCallReturn(ExternalFunction.IS_NAN, VariableType.BooleanVariable, load(ratioName)));

        // Update set tree to include resetting of the sample value, and then reusing
        // the tree to set all the intermediate variables etc.
        getSetSampleValueTree(funcData, compilationCtx);
        IRTreeVoid tree = funcData.sampleTree;
        tree.prefixComment("If it is not revert the changes.");

        compilationCtx.addTreeToScope(GlobalScope.scope, ifElse(guard, tree,
                "Test if the probability of the sample is sufficient to keep the value. This needs to be less "
                        + "than or equal as otherwise if the proposed value is not possible and the random value is 0 "
                        + "an impossible value will be accepted."));
    }

    /**
     * Method so that the value can be collected and placed in a scope tree to prevent name collisions on any generated
     * variables.
     *
     * @param funcData       the function data for this inference function generator.
     * @param compilationCtx The compilation context for this compilation process.
     */
    private void getSetSampleValueTree(MetropolisHastingsData<A, B> funcData, CompilationContext compilationCtx) {
        compilationCtx.pushScope();
        super.addSampleValueTree(funcData, compilationCtx);
        funcData.sampleTree = compilationCtx.getOutermostScopeTree();
        funcData.sampleTree.postfixComment("Set the sample value");
        compilationCtx.popScope();
    }

    /**
     * This method is overridden so that the value it returns can be changed and rolled back if the proposal was
     * unsuccessful.
     */
    @Override
    protected IRTreeReturn<A> calculateSampleValue(CompilationContext compilationCtx,
            MetropolisHastingsData<A, B> funcData) {
        return load(funcData.originalValueName);
    }

    @Override
    protected void setSampleValue(MetropolisHastingsData<A, B> funcData, CompilationContext compilationCtx) {
        IRTreeVoid updateCurrent = setCurrentValue(load(funcData.proposedValueName), Tree.NoComment);
        compilationCtx.pushScope();
        funcData.sampleDesc.updateSample(load(funcData.proposedValueName), compilationCtx);
        IRTreeVoid sampleUpdate = compilationCtx.getOutermostScopeTree();
        compilationCtx.popScope();
        IRTreeVoid combinedTree = treeScope(sampleUpdate, "Update Sample and intermediate values");
        IRTreeVoid elseBody = sequential(Tree.NoComment, updateCurrent, combinedTree);
        compilationCtx.addTreeToScope(GlobalScope.scope,
                ifElse(eq(funcData.valuePos, constant(0)),
                        setCurrentValue(load(funcData.originalValueName),
                                "Set the current value to the current state of the tree."),
                        Tree.NoComment, elseBody, Tree.NoComment));
    }

    @Override
    protected void saveBackTraceProbability(MetropolisHastingsData<A, B> funcData, IRTreeReturn<DoubleVariable> value,
            CompilationContext compilationCtx) {
        compilationCtx.addTreeToScope(GlobalScope.scope,
                ifElse(eq(funcData.valuePos, constant(0)),
                        store(funcData.originalProbabilityName, value, Tree.NoComment),
                        "Save the probability of the original value.",
                        store(funcData.proposedProbabilityName, value, Tree.NoComment),
                        "Save the probability of the proposed value."));
    }

    @Override
    protected void addDistributionProbabilities(MetropolisHastingsData<A, B> funcData,
            CompilationContext compilationCtx) {
        throw new CompilerException("Distributions generation is not supported in Metropolis Hastings Inference.");
    }
}
