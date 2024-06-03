/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
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
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Sin<A extends NumberVariable<A>> extends NumberProducingDataflowTaskImplementation<DoubleVariable> {

    private final A input;

    private Sin(A input, Location location) {
        super(DFType.SIN, VariableType.DoubleVariable, location, input);
        this.input = input;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return getType().getDescription() + "(" + input.getExpression(compressSandwoodCode) + ")";
    }

    public static <A extends NumberVariable<A>> DoubleVariable sin(A v) {
        return sin(v, null);
    }

    public static <A extends NumberVariable<A>> DoubleVariable sin(A v, Location location) {
        return DoubleVariable.doubleVariable(new Sin<>(v, location));
    }

    @Override
    public IRTreeReturn<DoubleVariable> getForwardIRinternal(CompilationContext compilationCtx) {
        return IRTree.sin(input.getForwardIR(compilationCtx));
    }

    @Override
    public IRTreeReturn<A> getInverseIRInternal(int argPos, IRTreeReturn<DoubleVariable> taskOutput,
            BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        throw new CompilerException(checkInversionError(argPos));
    }

    @Override
    public String checkInversionError(int argPos) {
        return "Sin is not invertible in the general case.";
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(getType() != other.getType())
            return false;
        return input.equivalent(((Sin<?>) other).input);
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
        return IRTree.constant(1.0);
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.constant(-1.0);
    }
}
