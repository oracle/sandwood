/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.inference.marginalization;

import static org.sandwood.compiler.trees.irTree.IRTree.arrayGet;
import static org.sandwood.compiler.trees.irTree.IRTree.arrayPut;
import static org.sandwood.compiler.trees.irTree.IRTree.constant;
import static org.sandwood.compiler.trees.irTree.IRTree.divideDI;
import static org.sandwood.compiler.trees.irTree.IRTree.eq;
import static org.sandwood.compiler.trees.irTree.IRTree.exp;
import static org.sandwood.compiler.trees.irTree.IRTree.forStmt;
import static org.sandwood.compiler.trees.irTree.IRTree.functionCallReturn;
import static org.sandwood.compiler.trees.irTree.IRTree.getIntField;
import static org.sandwood.compiler.trees.irTree.IRTree.ifElse;
import static org.sandwood.compiler.trees.irTree.IRTree.initializeVariable;
import static org.sandwood.compiler.trees.irTree.IRTree.load;
import static org.sandwood.compiler.trees.irTree.IRTree.subtractDD;

import java.util.List;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.compilation.inference.InferenceGeneratorScalarProb;
import org.sandwood.compiler.compilation.util.TreeUtils;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.DistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.DistributableRandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.ScalarVariable;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

/**
 * Class that implements the specific method bodies to allow inference to be performed by marginalization. The formulas
 * for the probability of each value being marginalized are as follows:
 * <p>
 * The probabilities for the source RV generating the value, and each sample task from a consuming RV that produce a
 * fixed output our combined for efficiency reasons, but can be described for each part as.
 * <p>
 * Probability of the source generating the marginalized value = (Sum over possible distributed source arguments 'ds'
 * (P(source_value | ds) * p(ds))) / (Sum over possible distributed source arguments 'ds' P(ds))
 * <p>
 * This weights each possible value of the source RV relative to the probability of the arguments it was generated with.
 * The division normalises this so that the sum of all possible values will be 1.
 * <p>
 * The probability of a sample task generating the value v given the value generated by the source RV = 1 - ((Sum over
 * possible distributed source arguments 'ds' * (Sum over possible distributed consumer arguments 'dc' P(dc| ds) *
 * P(ds))) + (Sum over possible distributed source arguments 'ds' (Sum over possible distributed consumer arguments 'dc'
 * P(v | dc, ds) * P(dc | ds)* P(ds)))
 * <p>
 * The first 2 terms account for the situation where some of the outputs from the consumer do not occur in conjunction
 * with the source. Example of when this might happen include if the result from a distribution is used to index into an
 * array of values such that the path between the source and the consumer is only valid for some of the probability
 * space. In the cases when the consumer cannot be reached, its probability is set to 1, the identity value for
 * multiplication.
 * <p>
 * The probability of a sample task generating a distribution being produced by the output from the source. When
 * comparing distributions this is done by measuring the overlap between the distributions. This does have the issue of
 * what would happen if the probability of a value in one distribution was 0 and in the other it was non-zero, but for
 * now this seems the best comparison measure.
 * <p>
 * 1 - (Sum over possible distributed consumer arguments 'dc' ((Sum over possible distributed source arguments 'ds' P(ds
 * | dc) P(dc)))) + overlap(consumer sample task distribution, (Sum over possible distributed consumer arguments 'dc'
 * (distribution(consumer RV | dc) * p(dc) * (Sum over possible distributed source arguments 'ds' P(ds | dc)))
 * <p>
 * distribution is a function that provides a probability distribution for a given set of inputs. The summed
 * distributions are normalised to ensure that the resulting distribution sums to 1 before the overlap is calculated.
 * <p>
 * To prevent probabilities underflowing to zero the probabilities of proposed sample values are stored in log space.
 * The probabilities of specific configurations are stored in normal space for the time being, but this may be changed
 * later.
 *
 * @param <A>
 * @param <B>
 */

public class MarginalizationFunctions<A extends ScalarVariable<A>, B extends DistributableRandomVariable<A, B>>
        extends InferenceGeneratorScalarProb<A, B, MarginalizationFunctions.MarginalizationData<A, B>> {
    protected static class MarginalizationData<A extends ScalarVariable<A>, B extends DistributableRandomVariable<A, B>>
            extends InferenceGeneratorScalarProb.ScalarProbFunctionData<A, B> {

        // Flags for the different variables that we will need to construct for this
        // function.
        final VariableDescription<ArrayVariable<DoubleVariable>> statesProbabilityNameGlobal;
        final VariableDescription<ArrayVariable<DoubleVariable>> statesProbabilityNameLocal;

        protected MarginalizationData(SampleTask<A, B> sample, CompilationContext compilationCtx) {
            super(sample, sample.randomVariable.getNoStates(), compilationCtx);

            statesProbabilityNameGlobal = VariableNames.calcVarName(sampleDesc.output, "stateProbabilityGlobal",
                    VariableType.arrayType(VariableType.DoubleVariable));
            statesProbabilityNameLocal = VariableNames.calcVarName("stateProbabilityLocal",
                    VariableType.arrayType(VariableType.DoubleVariable), true);
        }
    }

    @Override
    protected String getInferenceType() {
        return "variable marginalization";
    }

    @Override
    public boolean canAcceptTraces(SampleTask<A, B> sample, List<String> suggestions,
            CompilationContext compilationCtx) {
        return !sample.randomVariable.isInfinite();
    }

    @Override
    protected MarginalizationData<A, B> getFunctionData(SampleTask<A, B> sample, CompilationContext compilationCtx) {
        return new MarginalizationData<>(sample, compilationCtx);
    }

    @Override
    protected void constructFunctionVariablesProb(CompilationContext compilationCtx,
            MarginalizationData<A, B> funcData) {
        // Set up a pointer for accessing local space.
        IRTreeReturn<ArrayVariable<DoubleVariable>> globalState = loadGlobalField(funcData.statesProbabilityNameGlobal,
                funcData, compilationCtx);
        IRTreeVoid getLocalState = initializeVariable(funcData.statesProbabilityNameLocal, globalState,
                "Get a local reference to the scratch space.");
        compilationCtx.addTreeToScope(GlobalScope.scope, getLocalState);
    }

    @Override
    protected void allocateGlobalStateProb(MarginalizationData<A, B> funcData, CompilationContext compilationCtx) {
        allocateGlobalArray(compilationCtx, funcData, funcData.sourceRandom, funcData.statesProbabilityNameGlobal);
    }

    @Override
    protected IRTreeReturn<A> calculateSampleValue(CompilationContext compilationCtx,
            MarginalizationData<A, B> funcData) {
        IRTreeReturn<ArrayVariable<DoubleVariable>> arrayValue = load(funcData.statesProbabilityNameLocal);
        normalizeArray(arrayValue, arrayValue, compilationCtx);

        return funcData.sourceRandom.getStateValue(functionCallReturn(FunctionType.SAMPLE, VariableType.IntVariable,
                VariableType.Categorical, arrayValue));
    }

    private void normalizeArray(IRTreeReturn<ArrayVariable<DoubleVariable>> sourceArray,
            IRTreeReturn<ArrayVariable<DoubleVariable>> targetArray, CompilationContext compilationCtx) {
        VariableDescription<DoubleVariable> sumName = VariableNames.calcVarName("logSum", VariableType.DoubleVariable,
                true);
        VariableDescription<IntVariable> indexName = VariableNames.calcVarName("indexName", VariableType.IntVariable,
                true);
        compilationCtx.addTreeToScope(GlobalScope.scope,
                initializeVariable(sumName, constant(0.0), "The sum of all the probabilities in log space"));
        compilationCtx.addTreeToScope(GlobalScope.scope,
                TreeUtils.lseAdd(sourceArray, sumName, load(noStatesName), "Sum all the values"));

        IRTreeReturn<DoubleVariable> normalizedIfValue = divideDI(constant(1.0), load(noStatesName));
        IRTreeVoid ifLoopBody = arrayPut(targetArray, load(indexName), normalizedIfValue, Tree.NoComment);
        IRTreeVoid ifBody = forStmt(ifLoopBody, constant(0), load(noStatesName), constant(1), indexName, true,
                "Normalize log space values and move to normal space");

        IRTreeReturn<DoubleVariable> normalizedElseValue = exp(
                subtractDD(arrayGet(sourceArray, load(indexName)), load(sumName)));
        IRTreeVoid elseLoopBody = arrayPut(targetArray, load(indexName), normalizedElseValue, Tree.NoComment);
        IRTreeVoid elseBody = forStmt(elseLoopBody, constant(0), load(noStatesName), constant(1), indexName, true,
                "Normalize log space values and move to normal space");

        compilationCtx.addTreeToScope(GlobalScope.scope, ifElse(eq(load(sumName), constant(Double.NEGATIVE_INFINITY)),
                ifBody, "If all the sum is zero, just share the probability evenly.", elseBody, Tree.NoComment));

        IRTreeVoid zeroLoopBody = arrayPut(targetArray, load(indexName), constant(Double.NEGATIVE_INFINITY),
                Tree.NoComment);
        IRTreeVoid zeroLoop = forStmt(zeroLoopBody, load(noStatesName), getIntField(sourceArray, "length"), constant(1),
                indexName, true, "Set array values that are not computed for the input to negative infinity.");
        compilationCtx.addTreeToScope(GlobalScope.scope, zeroLoop);

    }

    @Override
    protected void setSampleValue(MarginalizationData<A, B> funcData, CompilationContext compilationCtx) {
        compilationCtx.addTreeToScope(GlobalScope.scope, setCurrentValue(
                funcData.sourceRandom.getStateValue(funcData.valuePos), "Value of the variable at this index"));
        // This is still required for the case that this is not a distribution, in which
        // case independent values that are not the sample value still need to be set.
        if(!funcData.sampleDesc.sample.isDistribution())
            funcData.sampleDesc.updateSample(getCurrentValue(), compilationCtx);
    }

    @Override
    protected void saveBackTraceProbability(MarginalizationData<A, B> funcData, IRTreeReturn<DoubleVariable> value,
            CompilationContext compilationCtx) {
        IRTreeReturn<ArrayVariable<DoubleVariable>> array = load(funcData.statesProbabilityNameLocal);
        IRTreeVoid arraySet = arrayPut(array, funcData.valuePos, value,
                "Save the calculated index value into the array of index value probabilities");
        compilationCtx.addTreeToScope(GlobalScope.scope, arraySet);
    }

    @Override
    protected void addDistributionProbabilities(MarginalizationData<A, B> funcData, CompilationContext compilationCtx) {
        compilationCtx.pushScope();
        IRTreeVoid t;

        IRTreeReturn<ArrayVariable<DoubleVariable>> arrayValue = load(funcData.statesProbabilityNameLocal);

        // Get the probability array
        VariableDescription<ArrayVariable<DoubleVariable>> localProbability = VariableNames
                .calcVarName("localProbability", VariableType.arrayType(VariableType.DoubleVariable), true);
        IRTreeReturn<ArrayVariable<DoubleVariable>> probabilityArray = ((DistributionSampleTask<?, ?>) funcData.sampleDesc.sample)
                .getProbabilitiesArray().getForwardIR(compilationCtx);
        compilationCtx.addTreeToScope(GlobalScope.scope,
                initializeVariable(localProbability, probabilityArray, "Local copy of the probability array"));

        normalizeArray(arrayValue, load(localProbability), compilationCtx);

        t = compilationCtx.getOutermostScopeTree();
        compilationCtx.popScope();
        t.prefixComment("Set the calculated probabilities to be the distribution values, and normalize");

        compilationCtx.addTreeToScope(GlobalScope.scope, t);
    }
}
