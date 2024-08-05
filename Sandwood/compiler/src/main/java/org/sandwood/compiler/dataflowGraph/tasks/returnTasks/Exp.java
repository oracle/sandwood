/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Exp<A extends NumberVariable<A>> extends NumberProducingDataflowTaskImplementation<DoubleVariable> {

    public final A input;

    private Exp(A input, Location location) {
        super(DFType.EXP, VariableType.DoubleVariable, location, input);
        this.input = input;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return getType().getDescription() + "(" + input.getExpression(compressSandwoodCode) + ")";
    }

    public static <A extends NumberVariable<A>> DoubleVariable exp(A v) {
        return DoubleVariable.doubleVariable(new Exp<>(v, null));
    }

    public static <A extends NumberVariable<A>> DoubleVariable exp(A v, Location location) {
        return DoubleVariable.doubleVariable(new Exp<>(v, location));
    }

    @Override
    public IRTreeReturn<DoubleVariable> getForwardIRinternal(
            CompilationContext compilationCtx) {
        return IRTree.exp(input.getForwardIR(compilationCtx));
    }

    @SuppressWarnings("unchecked")
    @Override
    public IRTreeReturn<A> getInverseIRInternal(int argPos, IRTreeReturn<DoubleVariable> taskOutput,
            BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        IRTreeReturn<DoubleVariable> d = IRTree.log(taskOutput);
        if(input instanceof IntVariable)
            return (IRTreeReturn<A>) IRTree.castToInteger(d);
        else
            return (IRTreeReturn<A>) d;
    }

    @Override
    public String checkInversionError(int argPos) {
        return null;
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(getType() != other.getType())
            return false;
        return input.equivalent(((Exp<?>) other).input);
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
        return IRTree.exp(input.getMax(compilationCtx));
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.exp(input.getMin(compilationCtx));
    }
}
