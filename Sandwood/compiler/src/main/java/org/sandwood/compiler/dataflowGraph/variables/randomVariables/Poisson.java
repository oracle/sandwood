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
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.PoissonTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Poisson extends RandomVariableImplementation<IntVariable, Poisson>
        implements NumericRandomVariable<IntVariable, Poisson> {
    public final DoubleVariable rate;

    public Poisson(DoubleVariable rate, RandomVariableConstructorTask<IntVariable, Poisson> parent) {
        super(parent, VariableType.IntVariable);
        this.rate = rate;
    }

    @Override
    public IntVariable sample() {
        return sample((Location) null);
    }

    @Override
    public IntVariable sample(Location location) {
        return IntVariable.intVariable(new SingleNumericSampleTask<>(VariableType.IntVariable, this, location));
    }

    @Override
    public Poisson getCurrentInstance() {
        return this;
    }

    @Override
    public IRTreeReturn<IntVariable> getSampleTree(IntVariable sample, CompilationContext compilationCtx) {
        return IRTree.functionCallReturn(FunctionType.SAMPLE, VariableType.IntVariable, getType(),
                rate.getForwardIR(compilationCtx));
    }

    @Override
    public IRTreeReturn<IntVariable> getMax(CompilationContext compilationCtx) {
        return IRTree.constant(Integer.MAX_VALUE);
    }

    @Override
    public IRTreeReturn<IntVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.constant(0);
    }

    public static Poisson poisson(double rate) {
        return poisson(Variable.doubleVariable(rate));
    }

    public static Poisson poisson(DoubleVariable rate) {
        return new Poisson(rate, new PoissonTask(rate, null));
    }

    public static Poisson poisson(DoubleVariable rate, Location location) {
        return new Poisson(rate, new PoissonTask(rate, location));
    }

    public static Poisson poisson(IntVariable rate) {
        return poisson(rate.castToDouble());
    }

    public static Poisson poisson(IntVariable rate, Location location) {
        return poisson(rate.castToDouble(location), location);
    }
}
