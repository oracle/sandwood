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
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.BetaTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Beta extends RandomVariableImplementation<DoubleVariable, Beta>
        implements NumericRandomVariable<DoubleVariable, Beta> {
    public final DoubleVariable alpha;
    public final DoubleVariable beta;

    public Beta(DoubleVariable alpha, DoubleVariable beta, RandomVariableConstructorTask<DoubleVariable, Beta> parent) {
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
    public Beta getCurrentInstance() {
        return this;
    }

    @Override
    public IRTreeReturn<DoubleVariable> getSampleTree(DoubleVariable sample, CompilationContext compilationCtx) {
        return IRTree.functionCallReturn(FunctionType.SAMPLE, VariableType.DoubleVariable, getType(),
                alpha.getForwardIR(compilationCtx), beta.getForwardIR(compilationCtx));
    }

    public static Beta beta(double alpha, double beta) {
        return beta(Variable.doubleVariable(alpha), Variable.doubleVariable(beta));
    }

    public static Beta beta(DoubleVariable alpha, double beta) {
        return beta(alpha, Variable.doubleVariable(beta));
    }

    public static Beta beta(double alpha, DoubleVariable beta) {
        return beta(Variable.doubleVariable(alpha), beta);
    }

    public static Beta beta(DoubleVariable alpha, DoubleVariable beta) {
        return new Beta(alpha, beta, new BetaTask(alpha, beta, null));
    }

    public static Beta beta(DoubleVariable alpha, DoubleVariable beta, Location location) {
        return new Beta(alpha, beta, new BetaTask(alpha, beta, location));
    }

    public static Beta beta(IntVariable alpha, DoubleVariable beta) {
        return beta(alpha.castToDouble(), beta);
    }

    public static Beta beta(IntVariable alpha, DoubleVariable beta, Location location) {
        return beta(alpha.castToDouble(location), beta, location);
    }

    public static Beta beta(DoubleVariable alpha, IntVariable beta) {
        return beta(alpha, beta.castToDouble());
    }

    public static Beta beta(DoubleVariable alpha, IntVariable beta, Location location) {
        return beta(alpha, beta.castToDouble(location), location);
    }

    public static Beta beta(IntVariable alpha, IntVariable beta) {
        return beta(alpha.castToDouble(), beta.castToDouble());
    }

    public static Beta beta(IntVariable alpha, IntVariable beta, Location location) {
        return beta(alpha.castToDouble(location), beta.castToDouble(location), location);
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
        return IRTree.constant(1.0);
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.constant(0.0);
    }
}
