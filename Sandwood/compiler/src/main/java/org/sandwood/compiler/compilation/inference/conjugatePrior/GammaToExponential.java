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
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class GammaToExponential
        extends InferenceGeneratorScalar<DoubleVariable, Gamma, GammaToExponential.GammaToExponentialData> {
    protected static class GammaToExponentialData
            extends InferenceGeneratorScalar.ScalarFunctionData<DoubleVariable, Gamma> {
        // Names for the different variables that will be needed to construct for this
        // function.
        final VariableDescription<DoubleVariable> sumName;
        final VariableDescription<IntVariable> countName;

        protected GammaToExponentialData(SampleTask<DoubleVariable, Gamma> sample, CompilationContext compilationCtx) {
            super(sample, false, compilationCtx);
            sumName = VariableNames.calcVarName("sum", VariableType.DoubleVariable, true);
            countName = VariableNames.calcVarName("count", VariableType.IntVariable, true);
        }
    }

    private GammaToExponential() {}

    // The singleton instance of this class that is given out to objects needing one.
    private static final GammaToExponential singleton = new GammaToExponential();

    public static InferenceGenerator<DoubleVariable, Gamma> getGenerator() {
        return singleton;
    }

    /**
     * Method to calculate the sample value once all the observed data has been collected together.
     *
     * @param compilationCtx The compilation context for this compilation process.
     * @param funcData       The function data for this inference function.
     * @return The function call to generate a sample value for this function data.
     */
    @Override
    protected IRTreeReturn<DoubleVariable> calculateSampleValue(CompilationContext compilationCtx,
            GammaToExponentialData funcData) {
        /*
         * TODO adjust this so it traces back to find the constructor, and get the values from them. This will allow
         * arrays of random variables. Get the arguments constructed
         */
        IRTreeReturn<DoubleVariable> alpha = funcData.sourceRandom.alpha.getForwardIR(compilationCtx);
        IRTreeReturn<DoubleVariable> beta = funcData.sourceRandom.beta.getForwardIR(compilationCtx);

        // Construct a tree to construct the sample variable.
        IRTreeReturn<DoubleVariable> mean = IRTree.functionCallReturn(FunctionType.CONJUGATE_SAMPLE,
                VariableType.DoubleVariable, VariableType.Gamma, VariableType.Exponential, alpha, beta,
                load(funcData.sumName), load(funcData.countName));
        return mean;
    }

    /**
     * Construct the Trees to initialize that data structures required for this function.
     *
     * @param compilationCtx The compilation context for this compilation process.
     * @param funcData       The function data for this inference function.
     */
    @Override
    protected void constructFunctionVariables(CompilationContext compilationCtx, GammaToExponentialData funcData) {

        // add a trees to initialize the temporary variables.
        compilationCtx.addTreeToScope(GlobalScope.scope, IRTree.initializeVariable(funcData.sumName, constant(0.0),
                "Variable to track the sum of the samples."));

        compilationCtx.addTreeToScope(GlobalScope.scope, IRTree.initializeVariable(funcData.countName, constant(0),
                "Variable to record how many samples have been included in this calculation."));
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void getObservationToSampleIR(SampleTask<?, ?> task, IRTreeReturn<?> current,
            GammaToExponentialData funcData, TreeBuilderInfo info, CompilationContext compilationCtx) {

        List<IRTreeVoid> trees = new ArrayList<>();
        trees.add(store(funcData.sumName, addDD(load(funcData.sumName), (IRTreeReturn<DoubleVariable>) current),
                "Include this sample by adding the value to the sum."));

        trees.add(store(funcData.countName, addII(load(funcData.countName), constant(1)),
                "Increment the number of samples in the calculation."));

        compilationCtx.addTreeToScope(task.scope(), sequential(trees, "Consume sample task " + task.id()
                + " from random variable " + task.randomVariable.getVarDesc() + "."));
    }

    @Override
    protected GammaToExponentialData getFunctionData(SampleTask<DoubleVariable, Gamma> sample,
            CompilationContext compilationCtx) {
        return new GammaToExponentialData(sample, compilationCtx);
    }

    @Override
    public boolean canAcceptTraces(SampleTask<DoubleVariable, Gamma> sample, List<String> suggestions,
            CompilationContext compilationCtx) {
        // Test if the input values to the beta value are distributions. If they are
        // this trace cannot be accepted.
        if(sample.randomVariable.isDistribution())
            return false;

        // For each random variable that consumes the results of this sample.
        for(RandomVariable<?, ?> consumingRV:compilationCtx.traces.getTracesRVToSampleTask(sample).keySet()) {
            // Test if any of the outputs of the consumer are distributions
            for(DataflowTask<?> t:consumingRV.getConsumers()) {
                if(t.getType() == DFType.SAMPLE && ((SampleTask<?, ?>) t).isDistribution())
                    return false;
            }

            // Test if any inputs to the consumers are distributions.
            for(Set<SampleTask<?, ?>> s:compilationCtx.traces.getRandomVariablesPerArgument(consumingRV))
                for(SampleTask<?, ?> t:s)
                    if(t.isDistribution())
                        return false;
        }

        // Check for transformations to the value between an Exponential, and a Gamma.
        for(Set<TraceHandle> ts:compilationCtx.traces.getTracesRVToSampleTask(sample).values()) {
            for(TraceHandle t:ts) {
                int noTasks = t.size();
                for(int i = 1; i < noTasks - 1; i++) {
                    DataflowTaskArgDesc d = t.get(i);
                    DFType type = d.task.getType();
                    switch(type) {
                        case COPY:
                        case IF_ASSIGNMENT:
                            break;
                        case GET:
                            if(d.argPos == 1)
                                return false;
                            break;
                        case PUT:
                            if(d.argPos == 1)
                                return false;
                            break;
                        default:
                            return false;
                    }
                }
            }
        }
        return true;
    }

    @Override // No global state, so nothing to do here.
    protected void allocateGlobalState(CompilationContext compilationCtx, GammaToExponentialData funcData) {}

    @Override
    protected void getDistributionSampleIR(DistributionSampleTask<?, ?> s,
            IRTreeReturn<DoubleVariable> sourceProbability, GammaToExponentialData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {
        throw new CompilerException("Distribution samples are not yet supported in this inference "
                + "technique. If this has been reached there is a bug in the compiler.");
    }

    @Override
    protected void getPerSourceConfigStartIR(GammaToExponentialData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerSourceConfigEndIR(GammaToExponentialData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerSampleStartIR(GammaToExponentialData funcData, SampleTask<?, ?> s, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerSampleEndIR(GammaToExponentialData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void finalize(GammaToExponentialData funcData, CompilationContext compilationCtx) {}

    @Override
    protected Scope getBackTraceScope(GammaToExponentialData funcData) {
        return GlobalScope.scope;
    }

    @Override
    protected String getInferenceType() {
        return "a Gamma to Exponential conjugate prior";
    }

    @Override
    protected void addDistributionProbabilities(GammaToExponentialData funcData, CompilationContext compilationCtx) {
        throw new CompilerException(
                "Unable to merge distributions in this inference method. This is a bug in Sandwood.");
    }

    @Override
    protected void backTraceScopeStartIR(GammaToExponentialData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void backTraceScopeEndIR(GammaToExponentialData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerDistributedSampleStartIR(GammaToExponentialData funcData, DistributionSampleTask<?, ?> s,
            TreeBuilderInfo info, CompilationContext compilationCtx) {}

    @Override
    protected void getPerDistributedSampleEndIR(GammaToExponentialData funcData, DistributionSampleTask<?, ?> s,
            TreeBuilderInfo info, CompilationContext compilationCtx) {}

    @Override
    protected void getConsumerRVInputIR(TreeBuilderInfo info, RandomVariable<?, ?> consumer,
            GammaToExponentialData funcData, CompilationContext compilationCtx) {}
}
