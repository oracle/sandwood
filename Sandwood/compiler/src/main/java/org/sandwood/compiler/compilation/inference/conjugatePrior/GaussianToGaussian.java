/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.inference.conjugatePrior;

import static org.sandwood.compiler.trees.irTree.IRTree.addDD;
import static org.sandwood.compiler.trees.irTree.IRTree.addDI;
import static org.sandwood.compiler.trees.irTree.IRTree.addID;
import static org.sandwood.compiler.trees.irTree.IRTree.constant;
import static org.sandwood.compiler.trees.irTree.IRTree.divideDD;
import static org.sandwood.compiler.trees.irTree.IRTree.divideDI;
import static org.sandwood.compiler.trees.irTree.IRTree.divideID;
import static org.sandwood.compiler.trees.irTree.IRTree.ifElse;
import static org.sandwood.compiler.trees.irTree.IRTree.initializeVariable;
import static org.sandwood.compiler.trees.irTree.IRTree.load;
import static org.sandwood.compiler.trees.irTree.IRTree.multiplyDD;
import static org.sandwood.compiler.trees.irTree.IRTree.multiplyDI;
import static org.sandwood.compiler.trees.irTree.IRTree.multiplyID;
import static org.sandwood.compiler.trees.irTree.IRTree.negate;
import static org.sandwood.compiler.trees.irTree.IRTree.sequential;
import static org.sandwood.compiler.trees.irTree.IRTree.store;
import static org.sandwood.compiler.trees.irTree.IRTree.subtractDD;
import static org.sandwood.compiler.trees.irTree.IRTree.subtractDI;
import static org.sandwood.compiler.trees.irTree.IRTree.subtractID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.compilation.inference.InferenceGenerator;
import org.sandwood.compiler.compilation.inference.InferenceGeneratorScalar;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.ReductionScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.PutTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Add;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.DistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Divide;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Multiply;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Negate;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Subtract;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ReductionReturnTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ReductionInput;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Gaussian;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.traces.TraceHandle;
import org.sandwood.compiler.traces.guards.TreeBuilderInfo;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRRVFunctionCallReturn;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class GaussianToGaussian
        extends InferenceGeneratorScalar<DoubleVariable, Gaussian, GaussianToGaussian.GaussianToGaussianData> {
    protected static class GaussianToGaussianData
            extends InferenceGeneratorScalar.ScalarFunctionData<DoubleVariable, Gaussian> {
        // Names for the different variables that need to be constructed for this
        // function.
        final VariableDescription<DoubleVariable> sumName;
        final VariableDescription<DoubleVariable> denominatorSquareSumName;
        final VariableDescription<DoubleVariable> sigmaValueName;
        final VariableDescription<BooleanVariable> sigmaNotFoundName;

        final VariableDescription<DoubleVariable> denominatorName;
        final VariableDescription<DoubleVariable> numeratorName;

        protected GaussianToGaussianData(SampleTask<DoubleVariable, Gaussian> sample,
                CompilationContext compilationCtx) {
            super(sample, false, compilationCtx);
            sumName = VariableNames.calcVarName("sum", VariableType.DoubleVariable, true);
            denominatorSquareSumName = VariableNames.calcVarName("denominatorSquareSum", VariableType.DoubleVariable,
                    true);
            sigmaValueName = VariableNames.calcVarName("sigmaValue", VariableType.DoubleVariable, true);
            sigmaNotFoundName = VariableNames.calcVarName("sigmaNotFound", VariableType.BooleanVariable, true);

            denominatorName = VariableNames.calcVarName("denominator", VariableType.DoubleVariable, false);
            numeratorName = VariableNames.calcVarName("numerator", VariableType.DoubleVariable, false);
        }
    }

    private GaussianToGaussian() {}

    // The singleton instance of this class that is given out to objects needing one.
    private static final GaussianToGaussian singleton = new GaussianToGaussian();

    public static InferenceGenerator<DoubleVariable, Gaussian> getGenerator() {
        return singleton;
    }

    /**
     * Method to calculate the sample value once all the observed data has been collected together.
     *
     * @param compilationCtx The compilation context for this compilation process.
     * @param funcData       The function data for this function inference method.
     * @return The intermediate representation tree for generating a sample value based on the function data.
     */
    @Override
    protected IRRVFunctionCallReturn<DoubleVariable> calculateSampleValue(CompilationContext compilationCtx,
            GaussianToGaussianData funcData) {
        // TODO adjust this so we trace back to find the constructor, and get the values
        // from them. This will allow us to have arrays of random variables.
        // Get the arguments constructed
        IRTreeReturn<DoubleVariable> mean0 = funcData.sourceRandom.mean.getForwardIR(compilationCtx);
        IRTreeReturn<DoubleVariable> variance0 = funcData.sourceRandom.variance.getForwardIR(compilationCtx);

        // Construct a tree to construct the sample variable.
        IRRVFunctionCallReturn<DoubleVariable> mean = IRTree.functionCallReturn(FunctionType.CONJUGATE_SAMPLE,
                VariableType.DoubleVariable, VariableType.Gaussian, VariableType.Gaussian, mean0, variance0,
                load(funcData.sigmaValueName), load(funcData.sumName), load(funcData.denominatorSquareSumName));
        return mean;
    }

    /**
     * Construct the Trees to initialize that data structures required for this function.
     *
     * @param compilationCtx The compilation context for this compilation process.
     * @param funcData       The function data for this function inference method.
     */
    @Override
    protected void constructFunctionVariables(CompilationContext compilationCtx, GaussianToGaussianData funcData) {

        // add a trees to initialize the temporary variables.
        compilationCtx.addTreeToScope(GlobalScope.scope, IRTree.initializeVariable(funcData.sumName, constant(0.0),
                "State to record the weighting of each sample that is consumed. This is the:\nsum of the sample denominator*(the sample value - the sample nominator)."));
        compilationCtx.addTreeToScope(GlobalScope.scope, IRTree.initializeVariable(funcData.denominatorSquareSumName,
                constant(0.0), "State for storing the sum of the squares of the sample denominators."));
        compilationCtx.addTreeToScope(GlobalScope.scope, IRTree.initializeVariable(funcData.sigmaNotFoundName,
                constant(true), "Flag to record if we have a value for Sigma."));
        compilationCtx.addTreeToScope(GlobalScope.scope, IRTree.initializeVariable(funcData.sigmaValueName,
                constant(1.0), "State for the value of sigma once we find it."));
    }

    @Override
    protected void getConsumerRVInputIR(TreeBuilderInfo info, RandomVariable<?, ?> consumer,
            GaussianToGaussianData funcData, CompilationContext compilationCtx) {

        // Get the trace of operations between the consuming random variable, and the
        // sample task.
        Set<TraceHandle> consumerTraces = funcData.getToConsumingRV(consumer);
        TraceHandle consumerTrace = consumerTraces.iterator().next();

        Map<DataflowTask<?>, DataflowTask<?>> scopeChangePoints = getScopeChangePoints(consumerTrace);

        IRTreeVoid denomInit = initializeVariable(funcData.denominatorName, constant(1.0),
                "State for tracking the changes that happen to the sampled value between it being consumed and it being produced.");
        compilationCtx.addTreeToScope(consumer.getParent().scope(), denomInit);
        IRTreeVoid numeratorInit = initializeVariable(funcData.numeratorName, constant(0.0), Tree.NoComment);
        compilationCtx.addTreeToScope(consumer.getParent().scope(), numeratorInit);

        // Stack to record which index we are currently working on.
        Stack<IRTreeReturn<IntVariable>> indexes = new Stack<>();

        // TODO this is not the observation to the sample this is the producer to the
        // consumer and should be evaluated
        for(int i = 0; i < consumerTrace.size(); i++) { // TODO check if task.scope can be replaced with the current
                                                        // task.scope
            DataflowTaskArgDesc d = consumerTrace.get(i);
            DataflowTask<?> t = d.task;
            // Check if the environment should move to a different set of substitutions.
            DataflowTask<?> cp = scopeChangePoints.get(t);
            if(cp != null)
                info.changeSubstitutions(cp, compilationCtx);

            switch(t.getType()) {
                case ADDITION: {
                    Add<?, ?, ?> a = (Add<?, ?, ?>) t;
                    if(d.argPos == 0) {
                        if(a.right.getType() == VariableType.IntVariable) {
                            IRTreeReturn<IntVariable> rightTree = ((IntVariable) a.right).getForwardIR(compilationCtx);
                            IRTreeReturn<DoubleVariable> numeratorTerm = addDI(load(funcData.numeratorName), rightTree);
                            compilationCtx.addTreeToScope(a.scope(),
                                    store(funcData.numeratorName, numeratorTerm, Tree.NoComment));
                        } else {
                            IRTreeReturn<DoubleVariable> rightTree = ((DoubleVariable) a.right)
                                    .getForwardIR(compilationCtx);
                            IRTreeReturn<DoubleVariable> numeratorTerm = addDD(load(funcData.numeratorName), rightTree);
                            compilationCtx.addTreeToScope(a.scope(),
                                    store(funcData.numeratorName, numeratorTerm, Tree.NoComment));
                        }
                    } else {
                        if(a.left.getType() == VariableType.IntVariable) {
                            IRTreeReturn<IntVariable> leftTree = ((IntVariable) a.left).getForwardIR(compilationCtx);
                            IRTreeReturn<DoubleVariable> numeratorTerm = addID(leftTree, load(funcData.numeratorName));
                            compilationCtx.addTreeToScope(a.scope(),
                                    store(funcData.numeratorName, numeratorTerm, Tree.NoComment));
                        } else {
                            IRTreeReturn<DoubleVariable> leftTree = ((DoubleVariable) a.left)
                                    .getForwardIR(compilationCtx);
                            IRTreeReturn<DoubleVariable> numeratorTerm = addDD(leftTree, load(funcData.numeratorName));
                            compilationCtx.addTreeToScope(a.scope(),
                                    store(funcData.numeratorName, numeratorTerm, Tree.NoComment));
                        }
                    }
                    break;
                }
                case DIVISION: {
                    Divide<?, ?, ?> div = (Divide<?, ?, ?>) t;
                    if(d.argPos == 0) {
                        if(div.right.getType() == VariableType.IntVariable) {
                            IRTreeReturn<IntVariable> rightTree = ((IntVariable) div.right)
                                    .getForwardIR(compilationCtx);
                            IRTreeReturn<DoubleVariable> numeratorTerm = divideDI(load(funcData.numeratorName),
                                    rightTree);
                            compilationCtx.addTreeToScope(div.scope(),
                                    store(funcData.numeratorName, numeratorTerm, Tree.NoComment));
                            IRTreeReturn<DoubleVariable> denominatorTerm = divideDI(load(funcData.denominatorName),
                                    rightTree);
                            compilationCtx.addTreeToScope(div.scope(),
                                    store(funcData.denominatorName, denominatorTerm, Tree.NoComment));
                        } else {
                            IRTreeReturn<DoubleVariable> rightTree = ((DoubleVariable) div.right)
                                    .getForwardIR(compilationCtx);
                            IRTreeReturn<DoubleVariable> numeratorTerm = divideDD(load(funcData.numeratorName),
                                    rightTree);
                            compilationCtx.addTreeToScope(div.scope(),
                                    store(funcData.numeratorName, numeratorTerm, Tree.NoComment));
                            IRTreeReturn<DoubleVariable> denominatorTerm = divideDD(load(funcData.denominatorName),
                                    rightTree);
                            compilationCtx.addTreeToScope(div.scope(),
                                    store(funcData.denominatorName, denominatorTerm, Tree.NoComment));
                        }
                    } else {
                        if(div.left.getType() == VariableType.IntVariable) {
                            IRTreeReturn<IntVariable> leftTree = ((IntVariable) div.left).getForwardIR(compilationCtx);
                            IRTreeReturn<DoubleVariable> numeratorTerm = divideID(leftTree,
                                    load(funcData.numeratorName));
                            compilationCtx.addTreeToScope(div.scope(),
                                    store(funcData.numeratorName, numeratorTerm, Tree.NoComment));
                            IRTreeReturn<DoubleVariable> denominatorTerm = divideID(leftTree,
                                    load(funcData.denominatorName));
                            compilationCtx.addTreeToScope(div.scope(),
                                    store(funcData.denominatorName, denominatorTerm, Tree.NoComment));
                        } else {
                            IRTreeReturn<DoubleVariable> leftTree = ((DoubleVariable) div.left)
                                    .getForwardIR(compilationCtx);
                            IRTreeReturn<DoubleVariable> numeratorTerm = divideDD(leftTree,
                                    load(funcData.numeratorName));
                            compilationCtx.addTreeToScope(div.scope(),
                                    store(funcData.numeratorName, numeratorTerm, Tree.NoComment));
                            IRTreeReturn<DoubleVariable> denominatorTerm = divideDD(leftTree,
                                    load(funcData.denominatorName));
                            compilationCtx.addTreeToScope(div.scope(),
                                    store(funcData.denominatorName, denominatorTerm, Tree.NoComment));
                        }
                    }
                    break;
                }
                case GET:
                    indexes.pop();
                    break;
                case MULTIPLICATION: {
                    Multiply<?, ?, ?> m = (Multiply<?, ?, ?>) t;
                    if(d.argPos == 0) {
                        if(m.right.getType() == VariableType.IntVariable) {
                            IRTreeReturn<IntVariable> rightTree = ((IntVariable) m.right).getForwardIR(compilationCtx);
                            IRTreeReturn<DoubleVariable> numeratorTerm = multiplyDI(load(funcData.numeratorName),
                                    rightTree);
                            compilationCtx.addTreeToScope(m.scope(),
                                    store(funcData.numeratorName, numeratorTerm, Tree.NoComment));
                            IRTreeReturn<DoubleVariable> denominatorTerm = multiplyDI(load(funcData.denominatorName),
                                    rightTree);
                            compilationCtx.addTreeToScope(m.scope(),
                                    store(funcData.denominatorName, denominatorTerm, Tree.NoComment));
                        } else {
                            IRTreeReturn<DoubleVariable> rightTree = ((DoubleVariable) m.right)
                                    .getForwardIR(compilationCtx);
                            IRTreeReturn<DoubleVariable> numeratorTerm = multiplyDD(load(funcData.numeratorName),
                                    rightTree);
                            compilationCtx.addTreeToScope(m.scope(),
                                    store(funcData.numeratorName, numeratorTerm, Tree.NoComment));
                            IRTreeReturn<DoubleVariable> denominatorTerm = multiplyDD(load(funcData.denominatorName),
                                    rightTree);
                            compilationCtx.addTreeToScope(m.scope(),
                                    store(funcData.denominatorName, denominatorTerm, Tree.NoComment));
                        }
                    } else {
                        if(m.left.getType() == VariableType.IntVariable) {
                            IRTreeReturn<IntVariable> leftTree = ((IntVariable) m.left).getForwardIR(compilationCtx);
                            IRTreeReturn<DoubleVariable> numeratorTerm = multiplyID(leftTree,
                                    load(funcData.numeratorName));
                            compilationCtx.addTreeToScope(m.scope(),
                                    store(funcData.numeratorName, numeratorTerm, Tree.NoComment));
                            IRTreeReturn<DoubleVariable> denominatorTerm = multiplyID(leftTree,
                                    load(funcData.denominatorName));
                            compilationCtx.addTreeToScope(m.scope(),
                                    store(funcData.denominatorName, denominatorTerm, Tree.NoComment));
                        } else {
                            IRTreeReturn<DoubleVariable> leftTree = ((DoubleVariable) m.left)
                                    .getForwardIR(compilationCtx);
                            IRTreeReturn<DoubleVariable> numeratorTerm = multiplyDD(leftTree,
                                    load(funcData.numeratorName));
                            compilationCtx.addTreeToScope(m.scope(),
                                    store(funcData.numeratorName, numeratorTerm, Tree.NoComment));
                            IRTreeReturn<DoubleVariable> denominatorTerm = multiplyDD(leftTree,
                                    load(funcData.denominatorName));
                            compilationCtx.addTreeToScope(m.scope(),
                                    store(funcData.denominatorName, denominatorTerm, Tree.NoComment));
                        }
                    }
                    break;
                }
                case NEGATE: {
                    Negate<?> n = (Negate<?>) t;
                    IRTreeReturn<DoubleVariable> numeratorTerm = negate(load(funcData.numeratorName));
                    compilationCtx.addTreeToScope(n.scope(),
                            store(funcData.numeratorName, numeratorTerm, Tree.NoComment));
                    IRTreeReturn<DoubleVariable> denominatorTerm = negate(load(funcData.denominatorName));
                    compilationCtx.addTreeToScope(n.scope(),
                            store(funcData.denominatorName, denominatorTerm, Tree.NoComment));
                }
                    break;
                case PUT:
                    if(d.argPos == 2) {
                        PutTask<?> pt = (PutTask<?>) t;
                        indexes.push(pt.index.getForwardIR(compilationCtx));
                    }
                    break;
                case SUBTRACTION: {
                    Subtract<?, ?, ?> s = (Subtract<?, ?, ?>) t;
                    if(d.argPos == 0) {
                        if(s.right.getType() == VariableType.IntVariable) {
                            IRTreeReturn<IntVariable> rightTree = ((IntVariable) s.right).getForwardIR(compilationCtx);
                            IRTreeReturn<DoubleVariable> numeratorTerm = subtractDI(load(funcData.numeratorName),
                                    rightTree);
                            compilationCtx.addTreeToScope(s.scope(),
                                    store(funcData.numeratorName, numeratorTerm, Tree.NoComment));
                        } else {
                            IRTreeReturn<DoubleVariable> rightTree = ((DoubleVariable) s.right)
                                    .getForwardIR(compilationCtx);
                            IRTreeReturn<DoubleVariable> numeratorTerm = subtractDD(load(funcData.numeratorName),
                                    rightTree);
                            compilationCtx.addTreeToScope(s.scope(),
                                    store(funcData.numeratorName, numeratorTerm, Tree.NoComment));
                        }
                    } else {
                        if(s.left.getType() == VariableType.IntVariable) {
                            IRTreeReturn<IntVariable> leftTree = ((IntVariable) s.left).getForwardIR(compilationCtx);
                            IRTreeReturn<DoubleVariable> numeratorTerm = subtractID(leftTree,
                                    load(funcData.numeratorName));
                            compilationCtx.addTreeToScope(s.scope(),
                                    store(funcData.numeratorName, numeratorTerm, Tree.NoComment));
                        } else {
                            IRTreeReturn<DoubleVariable> leftTree = ((DoubleVariable) s.left)
                                    .getForwardIR(compilationCtx);
                            IRTreeReturn<DoubleVariable> numeratorTerm = subtractDD(leftTree,
                                    load(funcData.numeratorName));
                            compilationCtx.addTreeToScope(s.scope(),
                                    store(funcData.numeratorName, numeratorTerm, Tree.NoComment));
                        }
                    }
                    break;
                }
                case REDUCE_INPUT: {
                    ReductionInput<?> ri = (ReductionInput<?>) t;
                    switch(d.argPos) {
                        case 0: // Start
                            // This should never be reached as the can accept trace test should catch it.
                            throw new CompilerException(
                                    "It is currently not possible to track through the start value for a reduction range.");

                        case 1: // End
                            // This should never be reached as the can accept trace test should catch it.
                            throw new CompilerException(
                                    "It is currently not possible to track through the end value for a reduction range.");
                        case 2: // Empty Value
                            reduceEmptyValue(ri, compilationCtx);
                            break;
                        default: // one of the inputs.
                            reduceArrayValue(ri, indexes.pop(), compilationCtx);
                            break;
                    }
                    break;
                }
                case REDUCTION_RETURN: {
                    ReductionReturnTask<?> rrt = (ReductionReturnTask<?>)t;
                    ReductionScope<?> reductionScope = rrt.getReductionScope();
                    compilationCtx.removeScopeSubstitute(reductionScope);
                    compilationCtx.removeSubstitute(reductionScope.j);
                    break;
                }
                case COPY:
                case GAUSSIAN:
                case IF_ASSIGNMENT:
                case SAMPLE:
                    break;
                default:
                    throw new CompilerException(
                            "One of the steps on the trace between variables is not currently supported: "
                                    + t.getType());

            }
        }
    }

    private Map<DataflowTask<?>, DataflowTask<?>> getScopeChangePoints(TraceHandle consumerTrace) {
        Map<DataflowTask<?>, DataflowTask<?>> changePoints = new HashMap<>();
        if(consumerTrace.isEmpty())
            return changePoints;

        DataflowTask<?> cp = consumerTrace.get(0).task;

        int size = consumerTrace.size();
        for(int i = 1; i < size - 1; i++) {
            DataflowTask<?> t = consumerTrace.get(i).task;
            if(t.getType() == DFType.PUT) {
                changePoints.put(cp, t);
                cp = consumerTrace.get(i + 1).task;
            }
        }

        changePoints.put(cp, consumerTrace.peek().task);

        return changePoints;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void getObservationToSampleIR(SampleTask<?, ?> task, IRTreeReturn<?> current,
            GaussianToGaussianData funcData, TreeBuilderInfo info, CompilationContext compilationCtx) {
        List<IRTreeVoid> trees = new ArrayList<>();
        trees.add(store(funcData.denominatorSquareSumName,
                addDD(load(funcData.denominatorSquareSumName),
                        multiplyDD(load(funcData.denominatorName), load(funcData.denominatorName))),
                "Add the denominator squared to the sample denominator"));
        trees.add(store(funcData.sumName,
                addDD(load(funcData.sumName),
                        multiplyDD(load(funcData.denominatorName),
                                subtractDD((IRTreeReturn<DoubleVariable>) current, load(funcData.numeratorName)))),
                "Add the weighting of the sample to the sum."));

        trees.add(ifElse(load(funcData.sigmaNotFoundName), sequential(Tree.NoComment,
                store(funcData.sigmaValueName, ((Gaussian) task.randomVariable).variance.getForwardIR(compilationCtx),
                        Tree.NoComment),
                store(funcData.sigmaNotFoundName, constant(false), Tree.NoComment)),
                "If we have not got the value of sigma yet record it and set a flag so it is not recorded again."));

        compilationCtx.addTreeToScope(task.scope(),
                sequential(trees, "Record the value of a sample generated by a consuming sample " + task.id()
                        + " of random variable " + task.randomVariable.getVarDesc() + "."));
    }

    private <A extends Variable<A>> void reduceEmptyValue(ReductionInput<A> ri, CompilationContext compilationCtx) {
        ReductionScope<A> reductionScope = ri.scope();
        Variable<A> reducedValue = reductionScope.reduceEmptyValue(ri, compilationCtx);
        compilationCtx.addScopeSubstitute(ri.scope(), reducedValue.scope());
        compilationCtx.addSubstitute(reductionScope.j, reducedValue);
    }

    private <A extends Variable<A>> void reduceArrayValue(ReductionInput<A> ri, IRTreeReturn<IntVariable> mask,
            CompilationContext compilationCtx) {
        ReductionScope<A> reductionScope = ri.scope();
        Variable<A> reducedValue = reductionScope.reduceArrayValue(ri, mask, compilationCtx);
        compilationCtx.addScopeSubstitute(ri.scope(), reducedValue.scope());
        compilationCtx.addSubstitute(reductionScope.j, reducedValue);
    }

    @Override
    protected GaussianToGaussianData getFunctionData(SampleTask<DoubleVariable, Gaussian> sample,
            CompilationContext compilationCtx) {
        return new GaussianToGaussianData(sample, compilationCtx);
    }

    @Override
    public boolean canAcceptTraces(SampleTask<DoubleVariable, Gaussian> sample, List<String> suggestions,
            CompilationContext compilationCtx) {
        // Test if the input values to the beta value are distributions we cannot accept
        // this trace.
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

        // Check for transformations to the value between the 2 Gaussians. TODO work on
        // weakening this restriction.
        for(Set<TraceHandle> ts:compilationCtx.traces.getTracesRVToSampleTask(sample).values()) {
            if(ts.size() != 1) // TODO replace this constraint with the traces are the same transformation.
                               // When we do this we will also need to add in a check so that the between
                               // sample traces is only calculated once.
                return false;
            for(TraceHandle t:ts) {
                int noTasks = t.size();
                for(int i = 1; i < noTasks - 1; i++) {
                    DataflowTaskArgDesc d = t.get(i);
                    DFType type = d.task.getType();
                    switch(type) {
                        case ADDITION:
                        case COPY:
                        case DIVISION:
                        case IF_ASSIGNMENT:
                        case MULTIPLICATION:
                        case NEGATE:
                        case REDUCTION_RETURN:
                        case SUBTRACTION:
                            break;
                        case GET:
                            if(d.argPos == 1)
                                return false;
                            break;
                        case PUT:
                            if(d.argPos == 1)
                                return false;
                            break;
                        case REDUCE_INPUT:
                            if(d.argPos == 0 || d.argPos == 1)
                                return false;
                            break;
                        default:
                            return false;

                    }
                }
            }
        }

        // Check all the Gaussians have the same variance.
        Iterator<Set<TraceHandle>> sampleTraces = compilationCtx.traces.getTracesRVToSampleTask(sample).values()
                .iterator();
        Set<TraceHandle> ts = sampleTraces.next();
        TraceHandle t = ts.iterator().next();
        DataflowTaskArgDesc d = t.peek();
        if(d.argPos != 0)
            return false;
        DoubleVariable variance = ((Gaussian) d.task.getOutput()).variance;
        while(sampleTraces.hasNext()) {
            ts = sampleTraces.next();
            t = ts.iterator().next();
            d = t.peek();
            if((d.argPos != 0) || !variance.equivalent(((Gaussian) d.task.getOutput()).variance))
                return false;
        }

        return true;
    }

    @Override // No global state, so nothing to do here.
    protected void allocateGlobalState(CompilationContext compilationCtx, GaussianToGaussianData funcData) {}

    @Override
    protected void getDistributionSampleIR(DistributionSampleTask<?, ?> s,
            IRTreeReturn<DoubleVariable> sourceProbability, GaussianToGaussianData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {
        throw new CompilerException("Distribution samples are not yet supported for Gaussian to Gaussian inference. "
                + "If this has been reached there is a bug in the compiler.");
    }

    @Override
    protected void getPerSourceConfigStartIR(GaussianToGaussianData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerSourceConfigEndIR(GaussianToGaussianData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerSampleStartIR(GaussianToGaussianData funcData, SampleTask<?, ?> s, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerSampleEndIR(GaussianToGaussianData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void finalize(GaussianToGaussianData funcData, CompilationContext compilationCtx) {}

    @Override
    protected Scope getBackTraceScope(GaussianToGaussianData funcData) {
        return GlobalScope.scope;
    }

    @Override
    protected String getInferenceType() {
        return "a Gaussian to Gaussian conjugate prior";
    }

    @Override
    protected void addDistributionProbabilities(GaussianToGaussianData funcData, CompilationContext compilationCtx) {
        throw new CompilerException(
                "Unable to merge distributions in Gaussian Gaussian inference. This is a bug in Sandwood.");
    }

    @Override
    protected void backTraceScopeStartIR(GaussianToGaussianData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void backTraceScopeEndIR(GaussianToGaussianData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerDistributedSampleStartIR(GaussianToGaussianData funcData, DistributionSampleTask<?, ?> s,
            TreeBuilderInfo info, CompilationContext compilationCtx) {}

    @Override
    protected void getPerDistributedSampleEndIR(GaussianToGaussianData funcData, DistributionSampleTask<?, ?> s,
            TreeBuilderInfo info, CompilationContext compilationCtx) {}
}
