/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.randomVariables;

import static org.sandwood.compiler.trees.irTree.IRTree.addII;
import static org.sandwood.compiler.trees.irTree.IRTree.constant;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.NumericDistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SingleNumericSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.BinomialTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Binomial extends NumericDistributableRandomVariable<IntVariable, Binomial> {

    public final DoubleVariable p;
    public final IntVariable length;

    private Binomial(DoubleVariable p, IntVariable length,
            RandomVariableConstructorTask<IntVariable, Binomial> parent) {
        super(parent, VariableType.IntVariable);
        this.p = p;
        this.length = length;
    }

    @Override
    public Binomial getCurrentInstance() {
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
    public IntVariable sampleDistribution() {
        return sampleDistribution(null);
    }

    @Override
    public IntVariable sampleDistribution(Location location) {
        return IntVariable.intVariable(new NumericDistributionSampleTask<>(VariableType.IntVariable, this, location));
    }

    @Override
    public IRTreeReturn<IntVariable> getSampleTree(IntVariable sample, CompilationContext compilationCtx) {
        return IRTree.functionCallReturn(FunctionType.SAMPLE, VariableType.IntVariable, getType(),
                p.getForwardIR(compilationCtx), length.getForwardIR(compilationCtx));
    }

    @Override
    public IntVariable getNoStates() {
        Scope enclosingScope = length.scope();
        ScopeStack.pushScope(enclosingScope);
        IntVariable noStates = length.add(Variable.intVariable(1));
        ScopeStack.popScope(enclosingScope);
        return noStates;
    }

    public static Binomial binomial(double p, int length) {
        return binomial(Variable.doubleVariable(p), Variable.intVariable(length));
    }

    public static Binomial binomial(DoubleVariable p, int length) {
        return binomial(p, Variable.intVariable(length));
    }

    public static Binomial binomial(double p, IntVariable length) {
        return binomial(Variable.doubleVariable(p), length);
    }

    public static Binomial binomial(DoubleVariable p, IntVariable length) {
        return binomial(p, length, null);
    }

    public static Binomial binomial(DoubleVariable p, IntVariable length, Location location) {
        return new Binomial(p, length, new BinomialTask(p, length, location));
    }

    public static Binomial binomial(IntVariable p, IntVariable length) {
        return binomial(p.castToDouble(), length);
    }

    public static Binomial binomial(IntVariable p, IntVariable length, Location location) {
        return binomial(p.castToDouble(location), length, location);
    }

    @Override
    public IRTreeReturn<IntVariable> getMax(CompilationContext compilationCtx) {
        return length.getMax(compilationCtx);
    }

    @Override
    public IRTreeReturn<IntVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.constant(0);
    }

    @Override
    public IRTreeReturn<IntVariable> getMaxNoStates(CompilationContext compilationCtx) {
        return addII(length.getMax(compilationCtx), constant(1));
    }

    @Override
    public IRTreeReturn<IntVariable> getStateValue(IRTreeReturn<IntVariable> state) {
        return state;
    }
}
