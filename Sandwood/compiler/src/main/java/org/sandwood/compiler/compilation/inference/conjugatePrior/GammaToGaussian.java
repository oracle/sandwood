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
import static org.sandwood.compiler.trees.irTree.IRTree.initializeVariable;
import static org.sandwood.compiler.trees.irTree.IRTree.load;
import static org.sandwood.compiler.trees.irTree.IRTree.multiplyDD;
import static org.sandwood.compiler.trees.irTree.IRTree.sequential;
import static org.sandwood.compiler.trees.irTree.IRTree.store;
import static org.sandwood.compiler.trees.irTree.IRTree.subtractDD;

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
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.DistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Divide;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.constant.ConstantDoubleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.constant.ConstantIntTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Gamma;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Gaussian;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.traces.TraceHandle;
import org.sandwood.compiler.traces.guards.TreeBuilderInfo;
import org.sandwood.compiler.trees.irTree.IRRVFunctionCallReturn;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class GammaToGaussian
        extends InferenceGeneratorScalar<DoubleVariable, Gamma, GammaToGaussian.GammaToGaussianData> {
    protected static class GammaToGaussianData
            extends InferenceGeneratorScalar.ScalarFunctionData<DoubleVariable, Gamma> {
        // Names for the different variables that will be needed to construct for this
        // function.
        final VariableDescription<DoubleVariable> sumName;
        final VariableDescription<IntVariable> countName;

        protected GammaToGaussianData(SampleTask<DoubleVariable, Gamma> sample, CompilationContext compilationCtx) {
            super(sample, false, compilationCtx);
            sumName = VariableNames.calcVarName("sum", VariableType.DoubleVariable, true);
            countName = VariableNames.calcVarName("count", VariableType.IntVariable, true);
        }
    }

    private GammaToGaussian() {}

    // The singleton instance of this class that is given out to objects needing one.
    private static final GammaToGaussian singleton = new GammaToGaussian();

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
    protected IRRVFunctionCallReturn<DoubleVariable> calculateSampleValue(CompilationContext compilationCtx,
            GammaToGaussianData funcData) {
        // TODO adjust this so it traces back to find the constructor, and get the
        // values
        // from them. This will allow arrays of random variables.
        // Get the arguments constructed
        IRTreeReturn<DoubleVariable> alpha = funcData.sourceRandom.alpha.getForwardIR(compilationCtx);
        IRTreeReturn<DoubleVariable> beta = funcData.sourceRandom.beta.getForwardIR(compilationCtx);

        // Construct a tree to construct the sample variable.
        IRRVFunctionCallReturn<DoubleVariable> mean = IRTree.functionCallReturn(FunctionType.CONJUGATE_SAMPLE,
                VariableType.DoubleVariable, VariableType.Gamma, VariableType.Gaussian, alpha, beta,
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
    protected void constructFunctionVariables(CompilationContext compilationCtx, GammaToGaussianData funcData) {

        // add a trees to initialize the temporary variables.
        compilationCtx.addTreeToScope(GlobalScope.scope, IRTree.initializeVariable(funcData.sumName, constant(0.0),
                "Variable to track the sum of the difference between the samples and the random variables mean squared."));

        compilationCtx.addTreeToScope(GlobalScope.scope, IRTree.initializeVariable(funcData.countName, constant(0),
                "Variable to record how many samples have been included in this calculation."));
    }

    @Override
    protected void getConsumerRVInputIR(TreeBuilderInfo info, RandomVariable<?, ?> consumer,
            GammaToGaussianData funcData, CompilationContext compilationCtx) {
        VariableDescription<DoubleVariable> muName = VariableNames.calcVarName(consumer, "mu",
                VariableType.DoubleVariable);
        compilationCtx.addTreeToScope(consumer.getParent().scope(),
                initializeVariable(muName, ((Gaussian) consumer).mean.getForwardIR(compilationCtx),
                        "The mean parameter for Gaussian " + consumer.getVarDesc() + "."));
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void getObservationToSampleIR(SampleTask<?, ?> task, IRTreeReturn<?> current,
            GammaToGaussianData funcData, TreeBuilderInfo info, CompilationContext compilationCtx) {
        VariableDescription<DoubleVariable> muName = VariableNames.calcVarName(task.randomVariable, "mu",
                VariableType.DoubleVariable);
        VariableDescription<DoubleVariable> diffName = VariableNames.calcVarName(task.randomVariable, "diff",
                VariableType.DoubleVariable);

        List<IRTreeVoid> trees = new ArrayList<>();

        trees.add(initializeVariable(diffName, subtractDD(load(muName), (IRTreeReturn<DoubleVariable>) current),
                "The difference between the mean parameter and the value sampled from the Gaussian."));
        trees.add(store(funcData.sumName, addDD(load(funcData.sumName), multiplyDD(load(diffName), load(diffName))),
                "Include this sample by adding the square of the difference to the sum."));

        trees.add(store(funcData.countName, addII(load(funcData.countName), constant(1)),
                "Increment the number of samples in the calculation."));

        compilationCtx.addTreeToScope(task.scope(), sequential(trees, "Consume sample task " + task.id()
                + " from random variable " + task.randomVariable.getVarDesc() + "."));
    }

    @Override
    protected GammaToGaussianData getFunctionData(SampleTask<DoubleVariable, Gamma> sample,
            CompilationContext compilationCtx) {
        return new GammaToGaussianData(sample, compilationCtx);
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

        // Check for transformations to the value between a Gaussian, and a Gamma.
        for(Set<TraceHandle> ts:compilationCtx.traces.getTracesRVToSampleTask(sample).values()) {
            for(TraceHandle t:ts) {
                boolean recipricated = false;
                int noTasks = t.size();
                for(int i = 1; i < noTasks - 1; i++) {
                    DataflowTaskArgDesc d = t.get(i);
                    DFType type = d.task.getType();
                    switch(type) {
                        case COPY:
                        case IF_ASSIGNMENT:
                            break;
                        case DIVISION: {
                            // division can only be used if the value is the denominator and the numerator is 1.
                            if(d.argPos == 0)
                                return false;
                            ProducingDataflowTask<?> numerator = ((Divide<?, ?, ?>) d.task).left.getParent();
                            switch(numerator.getType()) {
                                case CONSTANT_DOUBLE: {
                                    ConstantDoubleTask c = (ConstantDoubleTask) numerator;
                                    if(c.v != 1.0)
                                        return false;
                                    break;
                                }
                                case CONSTANT_INT: {
                                    ConstantIntTask c = (ConstantIntTask) numerator;
                                    if(c.v != 1)
                                        return false;
                                    break;
                                }
                                default:
                                    return false;
                            }

                            // Record the reciprocation.
                            recipricated = !recipricated;
                            break;
                        }
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

                // Test if the final result is 1/sample.
                if(!recipricated) {
                    suggestions
                            .add("Unable to use a conjugate prior here as Gaussians in Sandwood take the variance not"
                                    + " the precision as their second argument. Consider using an Inverse Gamma instead of the Gamma,"
                                    + " or taking the reciprocal of the Gamma sample.");
                    return false;
                }
            }
        }
        return true;
    }

    @Override // No global state, so nothing to do here.
    protected void allocateGlobalState(CompilationContext compilationCtx, GammaToGaussianData funcData) {}

    @Override
    protected void getDistributionSampleIR(DistributionSampleTask<?, ?> s,
            IRTreeReturn<DoubleVariable> sourceProbability, GammaToGaussianData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {
        throw new CompilerException("Distribution samples are not yet supported in this inference "
                + "technique. If this has been reached there is a bug in the compiler.");
    }

    @Override
    protected void getPerSourceConfigStartIR(GammaToGaussianData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerSourceConfigEndIR(GammaToGaussianData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerSampleStartIR(GammaToGaussianData funcData, SampleTask<?, ?> s, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerSampleEndIR(GammaToGaussianData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void finalize(GammaToGaussianData funcData, CompilationContext compilationCtx) {}

    @Override
    protected Scope getBackTraceScope(GammaToGaussianData funcData) {
        return GlobalScope.scope;
    }

    @Override
    protected String getInferenceType() {
        return "a Gamma to Gaussian conjugate prior";
    }

    @Override
    protected void addDistributionProbabilities(GammaToGaussianData funcData, CompilationContext compilationCtx) {
        throw new CompilerException(
                "Unable to merge distributions in this inference method. This is a bug in Sandwood.");
    }

    @Override
    protected void backTraceScopeStartIR(GammaToGaussianData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void backTraceScopeEndIR(GammaToGaussianData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerDistributedSampleStartIR(GammaToGaussianData funcData, DistributionSampleTask<?, ?> s,
            TreeBuilderInfo info, CompilationContext compilationCtx) {}

    @Override
    protected void getPerDistributedSampleEndIR(GammaToGaussianData funcData, DistributionSampleTask<?, ?> s,
            TreeBuilderInfo info, CompilationContext compilationCtx) {}
}
