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
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Gaussian;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.InverseGamma;
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

public class InverseGammaToGaussian extends
        InferenceGeneratorScalar<DoubleVariable, InverseGamma, InverseGammaToGaussian.InverseGammaToGaussianData> {
    protected static class InverseGammaToGaussianData
            extends InferenceGeneratorScalar.ScalarFunctionData<DoubleVariable, InverseGamma> {
        // Names for the different variables that we will need to construct for this
        // function.
        final VariableDescription<DoubleVariable> sumName;
        final VariableDescription<IntVariable> countName;
        final VariableDescription<DoubleVariable> countNameDis;
        final boolean distributedConsumers;

        protected InverseGammaToGaussianData(SampleTask<DoubleVariable, InverseGamma> sample,
                CompilationContext compilationCtx) {
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

    private InverseGammaToGaussian() {}

    // The singleton instance of this class that is given out to objects needing one.
    private static final InverseGammaToGaussian singleton = new InverseGammaToGaussian();

    public static InferenceGenerator<DoubleVariable, InverseGamma> getGenerator() {
        return singleton;
    }

    /**
     * Method to calculate the sample value once all the observed data has been collected together.
     *
     * @param compilationCtx The compilation context for this compilation process.
     * @param funcData       The function data for this inference function generator.
     * @return The intermediate representation tree to calculate a sample value based on the function data.
     */
    @Override
    protected IRTreeReturn<DoubleVariable> calculateSampleValue(CompilationContext compilationCtx,
            InverseGammaToGaussianData funcData) {
        // TODO adjust this so we trace back to find the constructor, and get the values
        // from them. This will allow us to have arrays of random variables.
        // Get the arguments constructed
        IRTreeReturn<DoubleVariable> alpha = funcData.sourceRandom.alpha.getForwardIR(compilationCtx);
        IRTreeReturn<DoubleVariable> beta = funcData.sourceRandom.beta.getForwardIR(compilationCtx);

        // Construct a tree to construct the sample variable.
        IRTreeReturn<DoubleVariable> mean = IRTree.functionCallReturn(FunctionType.CONJUGATE_SAMPLE,
                VariableType.DoubleVariable, VariableType.InverseGamma, VariableType.Gaussian, alpha, beta,
                load(funcData.sumName),
                funcData.distributedConsumers ? load(funcData.countNameDis) : load(funcData.countName));
        return mean;
    }

    /**
     * Construct the Trees to initialize that data structures required for this function.
     *
     * @param compilationCtx The compilation context for this compilation process.
     * @param funcData       The function data for this inference function generator.
     */
    @Override
    protected void constructFunctionVariables(CompilationContext compilationCtx, InverseGammaToGaussianData funcData) {

        // add a trees to initialize the temporary variables.
        compilationCtx.addTreeToScope(GlobalScope.scope, IRTree.initializeVariable(funcData.sumName, constant(0.0),
                "Variable to track the sum of the difference between the samples and the random variables mean squared."));

        if(funcData.distributedConsumers)
            compilationCtx.addTreeToScope(GlobalScope.scope, IRTree.initializeVariable(funcData.countNameDis,
                    constant(0.0), "Variable to record the number of samples from consuming random variables."));
        else
            compilationCtx.addTreeToScope(GlobalScope.scope, IRTree.initializeVariable(funcData.countName, constant(0),
                    "Variable to record the number of samples from consuming random variables."));
    }

    @Override
    protected void getConsumerRVInputIR(TreeBuilderInfo info, RandomVariable<?, ?> consumer,
            InverseGammaToGaussianData funcData, CompilationContext compilationCtx) {
        VariableDescription<DoubleVariable> muName = VariableNames.calcVarName(consumer, "mu",
                VariableType.DoubleVariable);

        compilationCtx.addTreeToScope(consumer.getParent().scope(),
                initializeVariable(muName, ((Gaussian) consumer).mean.getForwardIR(compilationCtx),
                        "The mean parameter for Gaussian " + consumer.getVarDesc() + "."));
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void getObservationToSampleIR(SampleTask<?, ?> task, IRTreeReturn<?> current,
            InverseGammaToGaussianData funcData, TreeBuilderInfo info, CompilationContext compilationCtx) {

        VariableDescription<DoubleVariable> muName = VariableNames.calcVarName(task.randomVariable, "mu",
                VariableType.DoubleVariable);
        VariableDescription<DoubleVariable> diffName = VariableNames.calcVarName(task.randomVariable, "diff",
                VariableType.DoubleVariable);

        List<IRTreeVoid> trees = new ArrayList<>();
        trees.add(initializeVariable(diffName, subtractDD(load(muName), (IRTreeReturn<DoubleVariable>) current),
                "The difference between the mean parameter and the value sampled from the Gaussian."));

        if(funcData.distributedConsumers) {
            trees.add(store(funcData.sumName, addDD(load(funcData.sumName), multiplyDD(load(diffName), load(diffName))),
                    "Include this sample by adding the square of the difference to the sum."));
            trees.add(store(funcData.countNameDis, addDD(load(funcData.countNameDis), constant(1.0)),
                    "Increment the number of samples in the calculation."));
        } else {
            trees.add(store(funcData.sumName, addDD(load(funcData.sumName), multiplyDD(load(diffName), load(diffName))),
                    "Include this sample by adding the square of the difference to the sum."));
            trees.add(store(funcData.countName, addII(load(funcData.countName), constant(1)),
                    "Increment the number of samples in the calculation."));
        }

        compilationCtx.addTreeToScope(task.scope(), sequential(trees, "Consume sample task " + task.id()
                + " from random variable " + task.randomVariable.getVarDesc() + "."));
    }

    @Override
    protected InverseGammaToGaussianData getFunctionData(SampleTask<DoubleVariable, InverseGamma> sample,
            CompilationContext compilationCtx) {
        return new InverseGammaToGaussianData(sample, compilationCtx);
    }

    @Override
    public boolean canAcceptTraces(SampleTask<DoubleVariable, InverseGamma> sample, List<String> suggestions,
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
                if(recipricated) {
                    suggestions
                            .add("Unable to use a conjugate prior here as Gaussians in Sandwood take the variance not"
                                    + " the precision as their second argument. Consider using a Gamma instead of the Inverse Gamma,"
                                    + " or not taking the reciprocal of the Inverse Gamma sample.");
                    return false;
                }
            }
        }
        return true;
    }

    @Override // No global state, so nothing to do here.
    protected void allocateGlobalState(CompilationContext compilationCtx, InverseGammaToGaussianData funcData) {}

    @Override
    protected void getDistributionSampleIR(DistributionSampleTask<?, ?> s,
            IRTreeReturn<DoubleVariable> sourceProbability, InverseGammaToGaussianData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {
        throw new CompilerException(
                "Distribution samples are not yet supported in " + getInferenceType() + " inference.");
    }

    @Override
    protected void getPerSourceConfigStartIR(InverseGammaToGaussianData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerSourceConfigEndIR(InverseGammaToGaussianData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerSampleStartIR(InverseGammaToGaussianData funcData, SampleTask<?, ?> s, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerSampleEndIR(InverseGammaToGaussianData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void finalize(InverseGammaToGaussianData funcData, CompilationContext compilationCtx) {}

    @Override
    protected Scope getBackTraceScope(InverseGammaToGaussianData funcData) {
        return GlobalScope.scope;
    }

    @Override
    protected String getInferenceType() {
        return "a Inverse Gamma to Gaussian conjugate prior";
    }

    @Override
    protected void addDistributionProbabilities(InverseGammaToGaussianData funcData,
            CompilationContext compilationCtx) {
        throw new CompilerException("Unable to merge distributions in this " + getInferenceType() + " inference.");
    }

    @Override
    protected void backTraceScopeStartIR(InverseGammaToGaussianData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void backTraceScopeEndIR(InverseGammaToGaussianData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerDistributedSampleStartIR(InverseGammaToGaussianData funcData, DistributionSampleTask<?, ?> s,
            TreeBuilderInfo info, CompilationContext compilationCtx) {}

    @Override
    protected void getPerDistributedSampleEndIR(InverseGammaToGaussianData funcData, DistributionSampleTask<?, ?> s,
            TreeBuilderInfo info, CompilationContext compilationCtx) {}
}
