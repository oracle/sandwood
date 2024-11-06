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
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.TruncatedGaussianTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class TruncatedGaussian extends RandomVariableImplementation<DoubleVariable, TruncatedGaussian>
        implements NumericRandomVariable<DoubleVariable, TruncatedGaussian> {
    public final DoubleVariable mean;
    public final DoubleVariable variance;
    public final DoubleVariable lower;
    public final DoubleVariable upper;

    private TruncatedGaussian(DoubleVariable mean, DoubleVariable variance, DoubleVariable lower, DoubleVariable upper,
            RandomVariableConstructorTask<DoubleVariable, TruncatedGaussian> parent) {
        super(parent, VariableType.DoubleVariable);
        this.mean = mean;
        this.variance = variance;
        this.lower = lower;
        this.upper = upper;
    }

    @Override
    public TruncatedGaussian getCurrentInstance() {
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
                mean.getForwardIR(compilationCtx), variance.getForwardIR(compilationCtx),
                lower.getForwardIR(compilationCtx), upper.getForwardIR(compilationCtx));
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
        return upper.getMax(compilationCtx);
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
        return lower.getMin(compilationCtx);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, double variance, double lower, double upper) {
        return truncatedGaussian(Variable.doubleVariable(mean), Variable.doubleVariable(variance),
                Variable.doubleVariable(lower), Variable.doubleVariable(upper));
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, double variance, double lower,
            double upper) {
        return truncatedGaussian(mean, Variable.doubleVariable(variance), Variable.doubleVariable(lower),
                Variable.doubleVariable(upper));
    }

    public static TruncatedGaussian truncatedGaussian(double mean, DoubleVariable variance, double lower,
            double upper) {
        return truncatedGaussian(Variable.doubleVariable(mean), variance, Variable.doubleVariable(lower),
                Variable.doubleVariable(upper));
    }

    public static TruncatedGaussian truncatedGaussian(double mean, double variance, DoubleVariable lower,
            double upper) {
        return truncatedGaussian(Variable.doubleVariable(mean), Variable.doubleVariable(variance), lower,
                Variable.doubleVariable(upper));
    }

    public static TruncatedGaussian truncatedGaussian(double mean, double variance, double lower,
            DoubleVariable upper) {
        return truncatedGaussian(Variable.doubleVariable(mean), Variable.doubleVariable(variance),
                Variable.doubleVariable(lower), upper);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, DoubleVariable variance, double lower,
            double upper) {
        return truncatedGaussian(mean, variance, Variable.doubleVariable(lower), Variable.doubleVariable(upper));
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, double variance, DoubleVariable lower,
            double upper) {
        return truncatedGaussian(mean, Variable.doubleVariable(variance), lower, Variable.doubleVariable(upper));
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, double variance, double lower,
            DoubleVariable upper) {
        return truncatedGaussian(mean, Variable.doubleVariable(variance), Variable.doubleVariable(lower), upper);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, DoubleVariable variance, DoubleVariable lower,
            double upper) {
        return truncatedGaussian(Variable.doubleVariable(mean), variance, lower, Variable.doubleVariable(upper));
    }

    public static TruncatedGaussian truncatedGaussian(double mean, DoubleVariable variance, double lower,
            DoubleVariable upper) {
        return truncatedGaussian(Variable.doubleVariable(mean), variance, Variable.doubleVariable(lower), upper);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, double variance, DoubleVariable lower,
            DoubleVariable upper) {
        return truncatedGaussian(Variable.doubleVariable(mean), Variable.doubleVariable(variance), lower, upper);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, DoubleVariable variance,
            DoubleVariable lower, double upper) {
        return truncatedGaussian(mean, variance, lower, Variable.doubleVariable(upper));
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, DoubleVariable variance, double lower,
            DoubleVariable upper) {
        return truncatedGaussian(mean, variance, Variable.doubleVariable(lower), upper);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, double variance, DoubleVariable lower,
            DoubleVariable upper) {
        return truncatedGaussian(mean, Variable.doubleVariable(variance), lower, upper);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, DoubleVariable variance, DoubleVariable lower,
            DoubleVariable upper) {
        return truncatedGaussian(Variable.doubleVariable(mean), variance, lower, upper);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, DoubleVariable variance,
            DoubleVariable lower, DoubleVariable upper) {
        return truncatedGaussian(mean, variance, lower, upper, null);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, double variance, double lower, double upper,
            Location location) {
        return truncatedGaussian(Variable.doubleVariable(mean), Variable.doubleVariable(variance),
                Variable.doubleVariable(lower), Variable.doubleVariable(upper), location);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, double variance, double lower, double upper,
            Location location) {
        return truncatedGaussian(mean, Variable.doubleVariable(variance), Variable.doubleVariable(lower),
                Variable.doubleVariable(upper), location);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, DoubleVariable variance, double lower, double upper,
            Location location) {
        return truncatedGaussian(Variable.doubleVariable(mean), variance, Variable.doubleVariable(lower),
                Variable.doubleVariable(upper), location);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, double variance, DoubleVariable lower, double upper,
            Location location) {
        return truncatedGaussian(Variable.doubleVariable(mean), Variable.doubleVariable(variance), lower,
                Variable.doubleVariable(upper), location);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, double variance, double lower, DoubleVariable upper,
            Location location) {
        return truncatedGaussian(Variable.doubleVariable(mean), Variable.doubleVariable(variance),
                Variable.doubleVariable(lower), upper, location);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, DoubleVariable variance, double lower,
            double upper, Location location) {
        return truncatedGaussian(mean, variance, Variable.doubleVariable(lower), Variable.doubleVariable(upper),
                location);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, double variance, DoubleVariable lower,
            double upper, Location location) {
        return truncatedGaussian(mean, Variable.doubleVariable(variance), lower, Variable.doubleVariable(upper),
                location);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, double variance, double lower,
            DoubleVariable upper, Location location) {
        return truncatedGaussian(mean, Variable.doubleVariable(variance), Variable.doubleVariable(lower), upper,
                location);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, DoubleVariable variance, DoubleVariable lower,
            double upper, Location location) {
        return truncatedGaussian(Variable.doubleVariable(mean), variance, lower, Variable.doubleVariable(upper),
                location);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, DoubleVariable variance, double lower,
            DoubleVariable upper, Location location) {
        return truncatedGaussian(Variable.doubleVariable(mean), variance, Variable.doubleVariable(lower), upper,
                location);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, double variance, DoubleVariable lower,
            DoubleVariable upper, Location location) {
        return truncatedGaussian(Variable.doubleVariable(mean), Variable.doubleVariable(variance), lower, upper,
                location);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, DoubleVariable variance,
            DoubleVariable lower, double upper, Location location) {
        return truncatedGaussian(mean, variance, lower, Variable.doubleVariable(upper), location);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, DoubleVariable variance, double lower,
            DoubleVariable upper, Location location) {
        return truncatedGaussian(mean, variance, Variable.doubleVariable(lower), upper, location);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, double variance, DoubleVariable lower,
            DoubleVariable upper, Location location) {
        return truncatedGaussian(mean, Variable.doubleVariable(variance), lower, upper, location);
    }

    public static TruncatedGaussian truncatedGaussian(double mean, DoubleVariable variance, DoubleVariable lower,
            DoubleVariable upper, Location location) {
        return truncatedGaussian(Variable.doubleVariable(mean), variance, lower, upper, location);
    }

    public static TruncatedGaussian truncatedGaussian(DoubleVariable mean, DoubleVariable variance,
            DoubleVariable lower, DoubleVariable upper, Location location) {
        return new TruncatedGaussian(mean, variance, lower, upper,
                new TruncatedGaussianTask(mean, variance, lower, upper, location));
    }
}
