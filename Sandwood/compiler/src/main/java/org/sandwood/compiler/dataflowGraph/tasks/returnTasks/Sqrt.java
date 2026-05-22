/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.ExternalFunction;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Sqrt<A extends NumberVariable<A>> extends NumberProducingDataflowTaskImplementation<DoubleVariable> {

    protected final A a;

    private Sqrt(A a, Location location) {
        super(DFType.SQRT, VariableType.DoubleVariable, location, a);
        this.a = a;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return "sqrt(" + a.getExpression(compressSandwoodCode) + ")";
    }

    @Override
    public IRTreeReturn<DoubleVariable> getInverseIRInternal(int argPos, IRTreeReturn<DoubleVariable> taskOutput,
            BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        if(argPos == 0) {
            return IRTree.multiplyDD(taskOutput, taskOutput);
        } else
            throw new CompilerException(
                    "Argument position is " + argPos + ". Square root operations only take 1 argument.");
    }

    @Override
    public String checkInversionError(int argPos) {
        return null;
    }

    @Override
    public IRTreeReturn<DoubleVariable> getForwardIRinternal(CompilationContext compilationCtx) {
        return IRTree.functionCallReturn(ExternalFunction.SQRT, VariableType.DoubleVariable,
                a.getForwardIR(compilationCtx));
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(this.getType() != other.getType())
            return false;
        Sqrt<?> dft = (Sqrt<?>) other;
        return a.equivalent(dft.a);
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
        return IRTree.functionCallReturn(ExternalFunction.SQRT, VariableType.DoubleVariable, a.getMax(compilationCtx));
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.functionCallReturn(ExternalFunction.SQRT, VariableType.DoubleVariable, a.getMin(compilationCtx));
    }

    /* Factory methods for construction */

    public static DoubleVariable sqrt(double d) {
        return sqrt(d, null);
    }

    public static DoubleVariable sqrt(double d, Location location) {
        return sqrt(Variable.doubleVariable(d), location);
    }

    public static DoubleVariable sqrt(int i) {
        return sqrt(i, null);
    }

    public static DoubleVariable sqrt(int i, Location location) {
        return sqrt(Variable.doubleVariable(i), location);
    }

    public static <A extends NumberVariable<A>> DoubleVariable sqrt(A a) {
        return sqrt(a, null);
    }

    public static <A extends NumberVariable<A>> DoubleVariable sqrt(A a, Location location) {
        return DoubleVariable.doubleVariable(new Sqrt<>(a, location));
    }
}