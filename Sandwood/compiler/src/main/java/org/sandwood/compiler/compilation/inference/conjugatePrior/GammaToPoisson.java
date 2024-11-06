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
import static org.sandwood.compiler.trees.irTree.IRTree.constant;
import static org.sandwood.compiler.trees.irTree.IRTree.load;
import static org.sandwood.compiler.trees.irTree.IRTree.sequential;
import static org.sandwood.compiler.trees.irTree.IRTree.store;

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
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.DistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Gamma;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
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

public class GammaToPoisson extends InferenceGeneratorScalar<DoubleVariable, Gamma, GammaToPoisson.GammaToPoissonData> {
    protected static class GammaToPoissonData
            extends InferenceGeneratorScalar.ScalarFunctionData<DoubleVariable, Gamma> {
        // Names for the different variables that need to be construct for this
        // function.
        final VariableDescription<DoubleVariable> sumName;
        final VariableDescription<IntVariable> countName;
        final VariableDescription<DoubleVariable> countNameDis;
        final boolean distributedConsumers;

        protected GammaToPoissonData(SampleTask<DoubleVariable, Gamma> sample, CompilationContext compilationCtx) {
            super(sample, false, compilationCtx);
            sumName = VariableNames.calcVarName("sum", VariableType.DoubleVariable, true);
            countName = VariableNames.calcVarName("count", VariableType.IntVariable, true);
            countNameDis = VariableNames.calcVarName("count", VariableType.DoubleVariable, true);

            boolean distributed = false;
            for(RandomVariable<?, ?> consumingRV:getConsumingRVs())
                distributed = distributed || consumingRV.isDistribution();
            distributedConsumers = distributed;
        }
    }

    private GammaToPoisson() {}

    // The singleton instance of this class that is given out to objects needing one.
    private static final GammaToPoisson singleton = new GammaToPoisson();

    public static InferenceGenerator<DoubleVariable, Gamma> getGenerator() {
        return singleton;
    }

    /**
     * Method to calculate the sample value once all the observed data has been collected together.
     *
     * @param compilationCtx The compilation context for this compilation process.
     * @param funcData       The function data for generating this inference method
     * @return The intermediate representation tree for the function call to generate a sample with this function data.
     */
    @Override
    protected IRTreeReturn<DoubleVariable> calculateSampleValue(CompilationContext compilationCtx,
            GammaToPoissonData funcData) {
        // TODO adjust this so it traces back to find the constructor, and get the
        // values
        // from them. This will allow arrays of random variables.

        // Get the arguments constructed
        IRTreeReturn<DoubleVariable> alpha = funcData.sourceRandom.alpha.getForwardIR(compilationCtx);
        IRTreeReturn<DoubleVariable> beta = funcData.sourceRandom.beta.getForwardIR(compilationCtx);

        // Construct a tree to construct the sample variable.
        IRTreeReturn<DoubleVariable> mean = IRTree.functionCallReturn(FunctionType.CONJUGATE_SAMPLE,
                VariableType.DoubleVariable, VariableType.Gamma, VariableType.Poisson, alpha, beta,
                load(funcData.sumName),
                funcData.distributedConsumers ? load(funcData.countNameDis) : load(funcData.countName));
        return mean;
    }

    /**
     * Construct the Trees to initialize that data structures required for this function.
     *
     * @param compilationCtx The compilation context for this compilation process.
     * @param funcData       The function data for generating this inference function.
     */
    @Override
    protected void constructFunctionVariables(CompilationContext compilationCtx, GammaToPoissonData funcData) {

        // add a trees to initialize the temporary variables.
        compilationCtx.addTreeToScope(GlobalScope.scope, IRTree.initializeVariable(funcData.sumName, constant(0.0),
                "Variable to store the sum of all the samples from consuming random variables."));
        if(funcData.distributedConsumers)
            compilationCtx.addTreeToScope(GlobalScope.scope, IRTree.initializeVariable(funcData.countNameDis,
                    constant(0.0), "Variable to record the number of samples from consuming random variables."));
        else
            compilationCtx.addTreeToScope(GlobalScope.scope, IRTree.initializeVariable(funcData.countName, constant(0),
                    "Variable to record the number of samples from consuming random variables."));
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void getObservationToSampleIR(SampleTask<?, ?> task, IRTreeReturn<?> current, GammaToPoissonData funcData,
            TreeBuilderInfo info, CompilationContext compilationCtx) {
        List<IRTreeVoid> trees = new ArrayList<>();
        if(funcData.distributedConsumers) {
            trees.add(store(funcData.sumName,
                    addDD(load(funcData.sumName),
                            IRTree.multiplyDD(info.probability, (IRTreeReturn<DoubleVariable>) current)),
                    Tree.NoComment));
            trees.add(
                    store(funcData.countNameDis, addDD(load(funcData.countNameDis), info.probability), Tree.NoComment));
        } else {
            trees.add(store(funcData.sumName, addDD(load(funcData.sumName), (IRTreeReturn<DoubleVariable>) current),
                    Tree.NoComment));
            trees.add(store(funcData.countName, addII(load(funcData.countName), constant(1)), Tree.NoComment));
        }

        compilationCtx.addTreeToScope(task.scope(),
                sequential(trees, "Add the value of a sample from consuming random variable "
                        + task.randomVariable.getVarDesc() + " to the inference state."));
    }

    @Override
    protected GammaToPoissonData getFunctionData(SampleTask<DoubleVariable, Gamma> sample,
            CompilationContext compilationCtx) {
        return new GammaToPoissonData(sample, compilationCtx);
    }

    @Override
    public boolean canAcceptTraces(SampleTask<DoubleVariable, Gamma> sample, List<String> suggestions,
            CompilationContext compilationCtx) {
        // Test if the input values to the beta value are distributions. Traces with
        // distributions cannot be accepted.
        if(sample.randomVariable.isDistribution())
            return false;

        // For each random variable that consumes the results of this sample.
        for(RandomVariable<?, ?> consumingRV:compilationCtx.traces.getTracesRVToSampleTask(sample).keySet()) {
            // Test if any of the outputs of the consumer are distributions
            for(DataflowTask<?> t:consumingRV.getConsumers()) {
                if(t.getType() == DFType.SAMPLE && ((SampleTask<?, ?>) t).isDistribution())
                    return false;
            }
        }

        // Look for transformations in the traces.
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
    protected void allocateGlobalState(CompilationContext compilationCtx, GammaToPoissonData funcData) {}

    @Override
    protected void getDistributionSampleIR(DistributionSampleTask<?, ?> s,
            IRTreeReturn<DoubleVariable> sourceProbability, GammaToPoissonData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {
        throw new CompilerException("Distribution samples are not yet supported in this inference "
                + "technique. If this has been reached there is a bug in the compiler.");
    }

    @Override
    protected void getPerSourceConfigStartIR(GammaToPoissonData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerSourceConfigEndIR(GammaToPoissonData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerSampleStartIR(GammaToPoissonData funcData, SampleTask<?, ?> s, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerSampleEndIR(GammaToPoissonData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void finalize(GammaToPoissonData funcData, CompilationContext compilationCtx) {}

    @Override
    protected Scope getBackTraceScope(GammaToPoissonData funcData) {
        return GlobalScope.scope;
    }

    @Override
    protected String getInferenceType() {
        return "a Gamma to Poisson conjugate prior";
    }

    @Override
    protected void addDistributionProbabilities(GammaToPoissonData funcData, CompilationContext compilationCtx) {
        throw new CompilerException(
                "Unable to merge distributions in Gamma to Poisson inference method. This is a bug in Sandwood.");
    }

    @Override
    protected void backTraceScopeStartIR(GammaToPoissonData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void backTraceScopeEndIR(GammaToPoissonData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerDistributedSampleStartIR(GammaToPoissonData funcData, DistributionSampleTask<?, ?> s,
            TreeBuilderInfo info, CompilationContext compilationCtx) {}

    @Override
    protected void getPerDistributedSampleEndIR(GammaToPoissonData funcData, DistributionSampleTask<?, ?> s,
            TreeBuilderInfo info, CompilationContext compilationCtx) {}

    @Override
    protected void getConsumerRVInputIR(TreeBuilderInfo info, RandomVariable<?, ?> consumer,
            GammaToPoissonData funcData, CompilationContext compilationCtx) {}
}
