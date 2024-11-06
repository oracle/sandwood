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
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.GaussianTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Gaussian extends RandomVariableImplementation<DoubleVariable, Gaussian>
        implements NumericRandomVariable<DoubleVariable, Gaussian> {
    public final DoubleVariable mean;
    public final DoubleVariable variance;

    private Gaussian(DoubleVariable mean, DoubleVariable variance,
            RandomVariableConstructorTask<DoubleVariable, Gaussian> parent) {
        super(parent, VariableType.DoubleVariable);
        this.mean = mean;
        this.variance = variance;
    }

    @Override
    public Gaussian getCurrentInstance() {
        return this;
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
    public IRTreeReturn<DoubleVariable> getSampleTree(DoubleVariable sample, CompilationContext compilationCtx) {
        return IRTree.functionCallReturn(FunctionType.SAMPLE, VariableType.DoubleVariable, getType(),
                mean.getForwardIR(compilationCtx), variance.getForwardIR(compilationCtx));
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
        return IRTree.constant(Double.POSITIVE_INFINITY);
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.constant(Double.NEGATIVE_INFINITY);
    }

    public static Gaussian gaussian(double mean, double variance) {
        return gaussian(Variable.doubleVariable(mean), Variable.doubleVariable(variance));
    }

    public static Gaussian gaussian(DoubleVariable mean, double variance) {
        return gaussian(mean, Variable.doubleVariable(variance));
    }

    public static Gaussian gaussian(double mean, DoubleVariable variance) {
        return gaussian(Variable.doubleVariable(mean), variance);
    }

    public static Gaussian gaussian(DoubleVariable mean, DoubleVariable variance) {
        return new Gaussian(mean, variance, new GaussianTask(mean, variance, null));
    }

    public static Gaussian gaussian(DoubleVariable mean, DoubleVariable variance, Location location) {
        return new Gaussian(mean, variance, new GaussianTask(mean, variance, location));
    }

    public static Gaussian gaussian(IntVariable mean, DoubleVariable variance) {
        return gaussian(mean.castToDouble(), variance);
    }

    public static Gaussian gaussian(IntVariable mean, DoubleVariable variance, Location location) {
        return gaussian(mean.castToDouble(location), variance, location);
    }

    public static Gaussian gaussian(DoubleVariable mean, IntVariable variance) {
        return gaussian(mean, variance.castToDouble());
    }

    public static Gaussian gaussian(DoubleVariable mean, IntVariable variance, Location location) {
        return gaussian(mean, variance.castToDouble(location), location);
    }

    public static Gaussian gaussian(IntVariable mean, IntVariable variance) {
        return gaussian(mean.castToDouble(), variance.castToDouble());
    }

    public static Gaussian gaussian(IntVariable mean, IntVariable variance, Location location) {
        return gaussian(mean.castToDouble(location), variance.castToDouble(location), location);
    }
}
