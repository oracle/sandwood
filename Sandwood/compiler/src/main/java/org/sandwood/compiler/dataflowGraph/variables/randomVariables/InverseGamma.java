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
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.InverseGammaTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRRVFunctionCallReturn;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class InverseGamma extends RandomVariableImplementation<DoubleVariable, InverseGamma>
        implements NumericRandomVariable<DoubleVariable, InverseGamma> {

    public final DoubleVariable alpha;
    public final DoubleVariable beta;

    private InverseGamma(DoubleVariable alpha, DoubleVariable beta,
            RandomVariableConstructorTask<DoubleVariable, InverseGamma> parent) {
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
    public InverseGamma getCurrentInstance() {
        return this;
    }

    @Override
    public IRRVFunctionCallReturn<DoubleVariable> getSampleTree(DoubleVariable sample,
            CompilationContext compilationCtx) {
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

    public static InverseGamma inverseGamma(double alpha, double beta) {
        return inverseGamma(Variable.doubleVariable(alpha), Variable.doubleVariable(beta));
    }

    public static InverseGamma inverseGamma(DoubleVariable alpha, double beta) {
        return inverseGamma(alpha, Variable.doubleVariable(beta));
    }

    public static InverseGamma inverseGamma(double alpha, DoubleVariable beta) {
        return inverseGamma(Variable.doubleVariable(alpha), beta);
    }

    public static InverseGamma inverseGamma(DoubleVariable alpha, DoubleVariable beta) {
        return new InverseGamma(alpha, beta, new InverseGammaTask(alpha, beta, null));
    }

    public static InverseGamma inverseGamma(DoubleVariable alpha, DoubleVariable beta, Location location) {
        return new InverseGamma(alpha, beta, new InverseGammaTask(alpha, beta, location));
    }

    public static InverseGamma inverseGamma(IntVariable alpha, DoubleVariable beta) {
        return inverseGamma(alpha.castToDouble(), beta);
    }

    public static InverseGamma inverseGamma(IntVariable alpha, DoubleVariable beta, Location location) {
        return inverseGamma(alpha.castToDouble(location), beta, location);
    }

    public static InverseGamma inverseGamma(DoubleVariable alpha, IntVariable beta) {
        return inverseGamma(alpha, beta.castToDouble());
    }

    public static InverseGamma inverseGamma(DoubleVariable alpha, IntVariable beta, Location location) {
        return inverseGamma(alpha, beta.castToDouble(location), location);
    }

    public static InverseGamma inverseGamma(IntVariable alpha, IntVariable beta) {
        return inverseGamma(alpha.castToDouble(), beta.castToDouble());
    }

    public static InverseGamma inverseGamma(IntVariable alpha, IntVariable beta, Location location) {
        return inverseGamma(alpha.castToDouble(location), beta.castToDouble(location), location);
    }
}
