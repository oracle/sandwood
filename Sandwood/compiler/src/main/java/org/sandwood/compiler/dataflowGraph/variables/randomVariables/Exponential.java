/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.randomVariables;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SingleNumericSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.ExponentialTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRRVFunctionCallReturn;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Exponential extends RandomVariableImplementation<DoubleVariable, Exponential>
        implements NumericRandomVariable<DoubleVariable, Exponential> {

    public final DoubleVariable lambda;

    private Exponential(DoubleVariable lambda, RandomVariableConstructorTask<DoubleVariable, Exponential> parent) {
        super(parent, VariableType.DoubleVariable);
        this.lambda = lambda;
    }

    @Override
    public DoubleVariable sample() {
        return sample((Location) null);
    }

    @Override
    public DoubleVariable sample(Location location) {
        return DoubleVariable
                .doubleVariable(new SingleNumericSampleTask<>(VariableType.DoubleVariable, this, location));
    }

    @Override
    public Exponential getCurrentInstance() {
        return this;
    }

    @Override
    public IRRVFunctionCallReturn<DoubleVariable> getSampleTree(DoubleVariable sample,
            CompilationContext compilationCtx) {
        return IRTree.functionCallReturn(FunctionType.SAMPLE, VariableType.DoubleVariable, getType(),
                lambda.getForwardIR(compilationCtx));
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
        return IRTree.constant(Double.POSITIVE_INFINITY);
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.constant(0.0);
    }

    public static Exponential exponential(double lambda) {
        return exponential(Variable.doubleVariable(lambda));
    }

    public static Exponential exponential(DoubleVariable lambda) {
        return new Exponential(lambda, new ExponentialTask(lambda, null));
    }

    public static Exponential exponential(DoubleVariable lambda, Location location) {
        return new Exponential(lambda, new ExponentialTask(lambda, location));
    }

    public static Exponential exponential(IntVariable lambda) {
        return exponential(lambda.castToDouble());
    }

    public static Exponential exponential(IntVariable lambda, Location location) {
        return exponential(lambda.castToDouble(location), location);
    }
}
