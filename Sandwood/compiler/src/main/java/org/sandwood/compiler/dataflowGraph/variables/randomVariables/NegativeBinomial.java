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
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.NegativeBinomialTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class NegativeBinomial extends RandomVariableImplementation<IntVariable, NegativeBinomial> implements NumericRandomVariable<IntVariable, NegativeBinomial> {

    public final DoubleVariable p;
    public final IntVariable positiveTests;

    private NegativeBinomial(DoubleVariable p, IntVariable positiveTests,
            RandomVariableConstructorTask<IntVariable, NegativeBinomial> parent) {
        super(parent, VariableType.IntVariable);
        this.p = p;
        this.positiveTests = positiveTests;
    }

    @Override
    public NegativeBinomial getCurrentInstance() {
        return this;
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
    public IRTreeReturn<IntVariable> getSampleTree(IntVariable sample, CompilationContext compilationCtx) {
        return IRTree.functionCallReturn(FunctionType.SAMPLE, VariableType.IntVariable, getType(),
                p.getForwardIR(compilationCtx), positiveTests.getForwardIR(compilationCtx));
    }

    public static NegativeBinomial negativeBinomial(double p, int positiveTests) {
        return negativeBinomial(Variable.doubleVariable(p), Variable.intVariable(positiveTests));
    }

    public static NegativeBinomial negativeBinomial(DoubleVariable p, int positiveTests) {
        return negativeBinomial(p, Variable.intVariable(positiveTests));
    }

    public static NegativeBinomial negativeBinomial(double p, IntVariable positiveTests) {
        return negativeBinomial(Variable.doubleVariable(p), positiveTests);
    }

    public static NegativeBinomial negativeBinomial(DoubleVariable p, IntVariable positiveTests) {
        return negativeBinomial(p, positiveTests, null);
    }

    public static NegativeBinomial negativeBinomial(DoubleVariable p, IntVariable positiveTests, Location location) {
        return new NegativeBinomial(p, positiveTests, new NegativeBinomialTask(p, positiveTests, location));
    }

    public static NegativeBinomial negativeBinomial(IntVariable p, IntVariable positiveTests) {
        return negativeBinomial(p.castToDouble(), positiveTests);
    }

    public static NegativeBinomial negativeBinomial(IntVariable p, IntVariable positiveTests, Location location) {
        return negativeBinomial(p.castToDouble(location), positiveTests, location);
    }

    @Override
    public IRTreeReturn<IntVariable> getMax(CompilationContext compilationCtx) {
        return IRTree.constant(Integer.MAX_VALUE);
    }

    @Override
    public IRTreeReturn<IntVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.constant(0);
    }
}
