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
import org.sandwood.compiler.trees.irTree.IRNegate;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Negate<A extends NumberVariable<A>> extends NumberProducingDataflowTaskImplementation<A> {

    private final A input;

    private Negate(A input, Location location) {
        super(DFType.NEGATE, input.getType(), location, input);
        this.input = input;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return getType().getDescription() + "(" + input.getExpression(compressSandwoodCode) + ")";
    }

    public static <A extends NumberVariable<A>> A negate(A value) {
        return negate(value, null);
    }

    @SuppressWarnings("unchecked")
    public static <A extends NumberVariable<A>> A negate(A value, Location location) {
        if(value.getType() == VariableType.DoubleVariable)
            return (A) DoubleVariable.doubleVariable(new Negate<>((DoubleVariable) value, location));
        else
            return (A) IntVariable.intVariable(new Negate<>((IntVariable) value, location));
    }

    @Override
    public IRNegate<A> getForwardIRinternal(CompilationContext compilationCtx) {
        return IRTree.negate(input.getForwardIR(compilationCtx));
    }

    @Override
    public IRTreeReturn<A> getInverseIRInternal(int argPos, IRTreeReturn<A> taskOutput, BackTraceInfo backTraceInfo,
            CompilationContext compilationCtx) {
        return IRTree.negate(taskOutput);
    }

    @Override
    public String checkInversionError(int argPos) {
        return null;
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(getType() != other.getType())
            return false;
        return input.equivalent(((Negate<?>) other).input);
    }

    @Override
    public IRTreeReturn<A> getMax(CompilationContext compilationCtx) {
        return IRTree.negate(input.getMin(compilationCtx));
    }

    @Override
    public IRTreeReturn<A> getMin(CompilationContext compilationCtx) {
        return IRTree.negate(input.getMax(compilationCtx));
    }
}
