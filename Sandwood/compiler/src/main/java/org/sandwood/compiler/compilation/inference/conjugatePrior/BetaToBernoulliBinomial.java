/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.inference.conjugatePrior;

import static org.sandwood.compiler.trees.irTree.IRTree.addDD;
import static org.sandwood.compiler.trees.irTree.IRTree.addII;
import static org.sandwood.compiler.trees.irTree.IRTree.arrayGet;
import static org.sandwood.compiler.trees.irTree.IRTree.constant;
import static org.sandwood.compiler.trees.irTree.IRTree.ifElse;
import static org.sandwood.compiler.trees.irTree.IRTree.initializeVariable;
import static org.sandwood.compiler.trees.irTree.IRTree.load;
import static org.sandwood.compiler.trees.irTree.IRTree.multiplyDD;
import static org.sandwood.compiler.trees.irTree.IRTree.multiplyDI;
import static org.sandwood.compiler.trees.irTree.IRTree.sequential;
import static org.sandwood.compiler.trees.irTree.IRTree.store;
import static org.sandwood.compiler.trees.irTree.IRTree.treeScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.compilation.inference.InferenceGenerator;
import org.sandwood.compiler.compilation.inference.InferenceGeneratorScalar;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.DistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.RandomVariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Beta;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Binomial;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.traces.TraceHandle;
import org.sandwood.compiler.traces.guards.TreeBuilderInfo;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class BetaToBernoulliBinomial
        extends InferenceGeneratorScalar<DoubleVariable, Beta, BetaToBernoulliBinomial.BetaToBernoulliBinomialData> {
    protected static class BetaToBernoulliBinomialData
            extends InferenceGeneratorScalar.ScalarFunctionData<DoubleVariable, Beta> {
        // Names for the different variables that will be needed to construct for this
        // function.s
        final VariableDescription<IntVariable> noTrueName;
        final VariableDescription<IntVariable> noTrialsName;

        final VariableDescription<DoubleVariable> noTrueNameDis;
        final VariableDescription<DoubleVariable> noTrialsNameDis;

        final boolean distributedConsumers;

        protected BetaToBernoulliBinomialData(SampleTask<DoubleVariable, Beta> sample,
                CompilationContext compilationCtx) {
            super(sample, false, compilationCtx);

            noTrueName = VariableNames.calcVarName("sum", VariableType.IntVariable, true);
            noTrialsName = VariableNames.calcVarName("count", VariableType.IntVariable, true);

            noTrueNameDis = VariableNames.calcVarName("sum", VariableType.DoubleVariable, true);
            noTrialsNameDis = VariableNames.calcVarName("count", VariableType.DoubleVariable, true);

            boolean distributed = false;
            for(RandomVariable<?, ?> consumingRV:getConsumingRVs())
                distributed = distributed || consumingRV.isDistribution() || consumingRV.distributionSampled();
            distributedConsumers = distributed;
        }
    }

    private BetaToBernoulliBinomial() {}

    // The singleton instance of this class that is given out to objects needing one.
    private static final BetaToBernoulliBinomial singleton = new BetaToBernoulliBinomial();

    public static InferenceGenerator<DoubleVariable, Beta> getGenerator() {
        return singleton;
    }

    /**
     * Method to calculate the sample value once all the observed data has been collected together.
     *
     * @param compilationCtx The compilation context for this compilation process.
     * @param funcData       The function data for this inference method constructor.
     * @return The intermediate tree representation of the method to infer a sample value.
     */
    @Override
    protected IRTreeReturn<DoubleVariable> calculateSampleValue(CompilationContext compilationCtx,
            BetaToBernoulliBinomialData funcData) {
        // TODO adjust this so it traces back to find the constructor, and get the
        // values
        // from them. This will allow arrays of random variables to be constructed.
        // Get the arguments constructed
        IRTreeReturn<DoubleVariable> alphaArg = funcData.sourceRandom.alpha.getForwardIR(compilationCtx);
        IRTreeReturn<DoubleVariable> betaArg = funcData.sourceRandom.beta.getForwardIR(compilationCtx);

        // Construct a tree to construct the sample variable.
        if(funcData.distributedConsumers)
            return IRTree.functionCallReturn(FunctionType.CONJUGATE_SAMPLE, VariableType.DoubleVariable,
                    VariableType.Beta, VariableType.Binomial, alphaArg, betaArg, load(funcData.noTrueNameDis),
                    load(funcData.noTrialsNameDis));
        else
            return IRTree.functionCallReturn(FunctionType.CONJUGATE_SAMPLE, VariableType.DoubleVariable,
                    VariableType.Beta, VariableType.Binomial, alphaArg, betaArg, load(funcData.noTrueName),
                    load(funcData.noTrialsName));
    }

    /**
     * Construct the Trees to initialize that data structures required for this function.
     *
     * @param compilationCtx The compilation context for this compilation process.
     * @param funcData       The function data for this inference method constructor.
     */
    @Override
    protected void constructFunctionVariables(CompilationContext compilationCtx, BetaToBernoulliBinomialData funcData) {

        // add a trees to initialize the temporary variables.
        if(funcData.distributedConsumers) {
            compilationCtx.addTreeToScope(GlobalScope.scope, IRTree.initializeVariable(funcData.noTrueNameDis,
                    constant(0.0), "Local variable to record the number of true samples."));

            compilationCtx.addTreeToScope(GlobalScope.scope, IRTree.initializeVariable(funcData.noTrialsNameDis,
                    constant(0.0), "Local variable to record the number of samples."));
        } else {
            compilationCtx.addTreeToScope(GlobalScope.scope, IRTree.initializeVariable(funcData.noTrueName, constant(0),
                    "Local variable to record the number of true samples."));

            compilationCtx.addTreeToScope(GlobalScope.scope, IRTree.initializeVariable(funcData.noTrialsName,
                    constant(0), "Local variable to record the number of samples."));
        }
    }

    @Override
    protected void getConsumerRVInputIR(TreeBuilderInfo info, RandomVariable<?, ?> consumer,
            BetaToBernoulliBinomialData funcData, CompilationContext compilationCtx) {}

    @SuppressWarnings("unchecked")
    @Override
    protected void getObservationToSampleIR(SampleTask<?, ?> task, IRTreeReturn<?> current,
            BetaToBernoulliBinomialData funcData, TreeBuilderInfo info, CompilationContext compilationCtx) {

        RandomVariableType<?, ?> type = task.randomVariable.getType();
        IRTreeVoid[] trees = new IRTreeVoid[2];
        if(type == VariableType.Bernoulli) {
            if(funcData.distributedConsumers) {
                trees[0] = store(funcData.noTrialsNameDis, addDD(load(funcData.noTrialsNameDis), info.probability),
                        "Increment the number of samples.");
                trees[1] = ifElse(
                        (IRTreeReturn<BooleanVariable>) current, store(funcData.noTrueNameDis,
                                addDD(load(funcData.noTrueNameDis), info.probability), Tree.NoComment),
                        "If the sample value was positive increase the count");
            } else {
                trees[0] = store(funcData.noTrialsName, addII(load(funcData.noTrialsName), constant(1)),
                        "Increment the number of samples.");
                trees[1] = ifElse((IRTreeReturn<BooleanVariable>) current,
                        store(funcData.noTrueName, addII(load(funcData.noTrueName), constant(1)), Tree.NoComment),
                        "If the sample value was positive increase the count");
            }
        } else if(type == VariableType.Binomial) {
            Binomial binomial = (Binomial) task.randomVariable;
            if(funcData.distributedConsumers) {
                trees[0] = store(funcData.noTrialsNameDis,
                        addDD(load(funcData.noTrialsNameDis),
                                multiplyDI(info.probability, binomial.length.getForwardIR(compilationCtx))),
                        "Increment the number of booleans sampled.");
                trees[1] = store(funcData.noTrueNameDis,
                        addDD(load(funcData.noTrueNameDis),
                                multiplyDI(info.probability, (IRTreeReturn<IntVariable>) current)),
                        "Add to the count the number of booleans that were true.");
            } else {
                trees[0] = store(funcData.noTrialsName,
                        addII(load(funcData.noTrialsName), binomial.length.getForwardIR(compilationCtx)),
                        "Increment the number of booleans sampled.");
                trees[1] = store(funcData.noTrueName,
                        addII(load(funcData.noTrueName), (IRTreeReturn<IntVariable>) current),
                        "Add to the count the number of booleans that were true.");
            }
        } else
            throw new CompilerException(
                    "This inference technique cannot be used with this type of pairing. There is an error in the selection mechanism");

        compilationCtx.addTreeToScope(GlobalScope.scope, sequential(trees, "Include the value sampled by task "
                + task.id() + " from random variable " + task.randomVariable.getVarDesc() + "."));
    }

    @Override
    protected BetaToBernoulliBinomialData getFunctionData(SampleTask<DoubleVariable, Beta> sample,
            CompilationContext compilationCtx) {
        return new BetaToBernoulliBinomialData(sample, compilationCtx);
    }

    @Override
    public boolean canAcceptTraces(SampleTask<DoubleVariable, Beta> sample, List<String> suggestions,
            CompilationContext compilationCtx) {
        // Test if the input values to the beta value are distributions. If there are
        // the trace cannot be accepted.
        if(sample.randomVariable.isDistribution())
            return false;

        // Test for transformations elsewhere in the trace.
        for(Set<TraceHandle> ts:compilationCtx.traces.getTracesRVToSampleTask(sample).values()) {
            for(TraceHandle t:ts) {
                int noTasks = t.size();
                for(int i = 1; i < noTasks - 1; i++) {
                    DataflowTaskArgDesc d = t.get(i);
                    DFType type = d.task.getType();
                    if(!(type == DFType.SAMPLE || type == DFType.IF_ASSIGNMENT || (type == DFType.GET && d.argPos != 1)
                            || (type == DFType.PUT && d.argPos != 1)))
                        return false;
                }
            }
        }
        return true;
    }

    @Override // No global state, so nothing to do here.
    protected void allocateGlobalState(CompilationContext compilationCtx, BetaToBernoulliBinomialData funcData) {}

    @Override
    protected void getDistributionSampleIR(DistributionSampleTask<?, ?> task,
            IRTreeReturn<DoubleVariable> sourceProbability, BetaToBernoulliBinomialData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {
        VariableDescription<DoubleVariable> distributionProbability = VariableNames
                .calcVarName("distributionProbability", VariableType.DoubleVariable, true);
        compilationCtx.addTreeToScope(GlobalScope.scope,
                initializeVariable(distributionProbability, multiplyDD(sourceProbability, info.probability),
                        "The probability of reaching the consumer with this set of consumer arguments"));
        ArrayVariable<DoubleVariable> distribution = task.getProbabilitiesArray();
        IRTreeVoid[] trees = new IRTreeVoid[2];
        if(task.randomVariable.getType() == VariableType.Bernoulli) {
            trees[0] = store(funcData.noTrialsNameDis,
                    addDD(load(funcData.noTrialsNameDis), load(distributionProbability)),
                    "Increment the number of booleans sampled.");
            trees[1] = store(funcData.noTrueNameDis,
                    addDD(load(funcData.noTrueNameDis),
                            multiplyDD(load(distributionProbability), arrayGet(load(distribution), constant(1)))),
                    "Add to the count the probability that the boolean was true.");
        } else if(task.randomVariable.getType() == VariableType.Binomial) {
            Binomial binomial = (Binomial) task.randomVariable;
            trees[0] = store(funcData.noTrialsNameDis,
                    addDD(load(funcData.noTrialsNameDis),
                            multiplyDI(load(distributionProbability), load(binomial.length))),
                    "Increment the number of booleans sampled.");
            VariableDescription<DoubleVariable> accumulator = VariableNames.calcVarName("accumulator",
                    VariableType.DoubleVariable, true);
            VariableDescription<IntVariable> indexName = VariableNames.indexName(distribution.getUniqueVarDesc());
            List<IRTreeVoid> scopedTrees = new ArrayList<>();
            scopedTrees.add(initializeVariable(accumulator, constant(0.0),
                    "An accumulator to calculate the probable number of true booleans"));
            IRTreeVoid forBody = store(accumulator,
                    addDD(load(accumulator),
                            multiplyDI(arrayGet(load(distribution), load(indexName)), load(indexName))),
                    Tree.NoComment);
            scopedTrees.add(IRTree.forStmt(forBody, constant(1), distribution.length().getForwardIR(compilationCtx),
                    constant(1), indexName, true,
                    "Loop over all the possible boolean variable counts adding them to the accumulator. "
                            + "Zero is missed as it will always have the value zero."));
            scopedTrees.add(store(funcData.noTrueNameDis, multiplyDD(load(distributionProbability), load(accumulator)),
                    "multiply in the probability of reaching this sample task."));
            trees[1] = treeScope(sequential(scopedTrees, "Add in the probabilities of different boolean counts"),
                    Tree.NoComment);
        } else
            throw new CompilerException(
                    "This inference technique cannot be used with this type of pairing. There is an error in the selection mechanism");

        compilationCtx.addTreeToScope(GlobalScope.scope, sequential(trees, "Include the distribution sampled by task "
                + task.id() + " from random variable " + task.randomVariable.getVarDesc() + "."));
    }

    @Override
    protected void getPerSourceConfigStartIR(BetaToBernoulliBinomialData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerSourceConfigEndIR(BetaToBernoulliBinomialData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerSampleStartIR(BetaToBernoulliBinomialData funcData, SampleTask<?, ?> s, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerSampleEndIR(BetaToBernoulliBinomialData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void finalize(BetaToBernoulliBinomialData funcData, CompilationContext compilationCtx) {}

    @Override
    protected Scope getBackTraceScope(BetaToBernoulliBinomialData funcData) {
        return GlobalScope.scope;
    }

    @Override
    protected String getInferenceType() {
        return "a Beta to Bernoulli/Binomial conjugate prior";
    }

    @Override
    protected void addDistributionProbabilities(BetaToBernoulliBinomialData funcData,
            CompilationContext compilationCtx) {
        throw new CompilerException(
                "Unable to merge distributions in this inference method. This is a bug in Sandwood.");
    }

    @Override
    protected void backTraceScopeStartIR(BetaToBernoulliBinomialData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void backTraceScopeEndIR(BetaToBernoulliBinomialData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerDistributedSampleStartIR(BetaToBernoulliBinomialData funcData, DistributionSampleTask<?, ?> s,
            TreeBuilderInfo info, CompilationContext compilationCtx) {}

    @Override
    protected void getPerDistributedSampleEndIR(BetaToBernoulliBinomialData funcData, DistributionSampleTask<?, ?> s,
            TreeBuilderInfo info, CompilationContext compilationCtx) {}
}
