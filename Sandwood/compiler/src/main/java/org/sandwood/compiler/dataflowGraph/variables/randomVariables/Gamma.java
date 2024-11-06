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
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.GammaTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Gamma extends RandomVariableImplementation<DoubleVariable, Gamma>
        implements NumericRandomVariable<DoubleVariable, Gamma> {

    public final DoubleVariable alpha;
    public final DoubleVariable beta;

    private Gamma(DoubleVariable alpha, DoubleVariable beta,
            RandomVariableConstructorTask<DoubleVariable, Gamma> parent) {
        super(parent, VariableType.DoubleVariable);
        this.alpha = alpha;
        this.beta = beta;
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
    public Gamma getCurrentInstance() {
        return this;
    }

    @Override
    public IRTreeReturn<DoubleVariable> getSampleTree(DoubleVariable sample, CompilationContext compilationCtx) {
        return IRTree.functionCallReturn(FunctionType.SAMPLE, VariableType.DoubleVariable, getType(),
                alpha.getForwardIR(compilationCtx), beta.getForwardIR(compilationCtx));
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
        return IRTree.constant(Double.POSITIVE_INFINITY);
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.constant(0.0);
    }

    public static Gamma gamma(double alpha, double beta) {
        return gamma(Variable.doubleVariable(alpha), Variable.doubleVariable(beta));
    }

    public static Gamma gamma(DoubleVariable alpha, double beta) {
        return gamma(alpha, Variable.doubleVariable(beta));
    }

    public static Gamma gamma(double alpha, DoubleVariable beta) {
        return gamma(Variable.doubleVariable(alpha), beta);
    }

    public static Gamma gamma(DoubleVariable alpha, DoubleVariable beta) {
        return new Gamma(alpha, beta, new GammaTask(alpha, beta, null));
    }

    public static Gamma gamma(DoubleVariable alpha, DoubleVariable beta, Location location) {
        return new Gamma(alpha, beta, new GammaTask(alpha, beta, location));
    }

    public static Gamma gamma(IntVariable alpha, DoubleVariable beta) {
        return gamma(alpha.castToDouble(), beta);
    }

    public static Gamma gamma(IntVariable alpha, DoubleVariable beta, Location location) {
        return gamma(alpha.castToDouble(location), beta, location);
    }

    public static Gamma gamma(DoubleVariable alpha, IntVariable beta) {
        return gamma(alpha, beta.castToDouble());
    }

    public static Gamma gamma(DoubleVariable alpha, IntVariable beta, Location location) {
        return gamma(alpha, beta.castToDouble(location), location);
    }

    public static Gamma gamma(IntVariable alpha, IntVariable beta) {
        return gamma(alpha.castToDouble(), beta.castToDouble());
    }

    public static Gamma gamma(IntVariable alpha, IntVariable beta, Location location) {
        return gamma(alpha.castToDouble(location), beta.castToDouble(location), location);
    }
}
