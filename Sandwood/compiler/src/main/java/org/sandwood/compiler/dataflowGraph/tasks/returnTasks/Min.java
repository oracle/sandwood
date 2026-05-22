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
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRMin;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Min<A extends NumberVariable<A>> extends NumberProducingDataflowTaskImplementation<A> {

    protected final A a;
    protected final A b;

    private Min(A a, A b, Location location) {
        super(DFType.MIN, a.getType(), location, a, b);
        this.a = a;
        this.b = b;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return "min(" + a.getExpression(compressSandwoodCode) + ", " + b.getExpression(compressSandwoodCode) + ")";
    }

    @Override
    public IRTreeReturn<A> getInverseIRInternal(int argPos, IRTreeReturn<A> taskOutput, BackTraceInfo backTraceInfo,
            CompilationContext compilationCtx) {
        throw new CompilerException(checkInversionError(argPos));
    }

    @Override
    public String checkInversionError(int argPos) {
        return "Information is lost in Min, so it is not possible to construct an inverse function.";
    }

    @Override
    public IRMin<A> getForwardIRinternal(CompilationContext compilationCtx) {
        return IRTree.min(a.getForwardIR(compilationCtx), b.getForwardIR(compilationCtx));
    }

    @Override

    public boolean equivalent(DataflowTask<?> other) {
        if(this.getType() != other.getType())
            return false;
        Min<?> dft = (Min<?>) other;
        return ((a.equivalent(dft.a) && b.equivalent(dft.b)) || (a.equivalent(dft.b) && b.equivalent(dft.a)));
    }

    @Override
    public IRTreeReturn<A> getMax(CompilationContext compilationCtx) {
        return IRTree.min(a.getMax(compilationCtx), b.getMax(compilationCtx));
    }

    @Override
    public IRTreeReturn<A> getMin(CompilationContext compilationCtx) {
        return IRTree.min(a.getMin(compilationCtx), b.getMin(compilationCtx));
    }

    /* Factory methods for construction */

    public static DoubleVariable min(DoubleVariable a, double b) {
        return min(a, b, null);
    }

    public static DoubleVariable min(double a, DoubleVariable b) {
        return min(a, b, null);
    }

    public static DoubleVariable min(double a, double b) {
        return min(a, b, null);
    }

    public static DoubleVariable min(DoubleVariable a, double b, Location location) {
        return min(a, Variable.doubleVariable(b), location);
    }

    public static DoubleVariable min(double a, DoubleVariable b, Location location) {
        return min(Variable.doubleVariable(a), b, location);
    }

    public static DoubleVariable min(double a, double b, Location location) {
        return min(Variable.doubleVariable(a), Variable.doubleVariable(b), location);
    }

    public static IntVariable min(IntVariable a, int b) {
        return min(a, b, null);
    }

    public static IntVariable min(int a, IntVariable b) {
        return min(a, b, null);
    }

    public static IntVariable min(int a, int b) {
        return min(a, b, null);
    }

    public static IntVariable min(IntVariable a, int b, Location location) {
        return min(a, Variable.intVariable(b), location);
    }

    public static IntVariable min(int a, IntVariable b, Location location) {
        return min(Variable.intVariable(a), b, location);
    }

    public static IntVariable min(int a, int b, Location location) {
        return min(Variable.intVariable(a), Variable.intVariable(b), location);
    }

    public static <A extends NumberVariable<A>> A min(A a, A b) {
        return min(a, b, null);
    }

    public static <A extends NumberVariable<A>> A min(A a, A b, Location location) {
        if(a.getType() == VariableType.DoubleVariable)
            return (A) DoubleVariable
                    .doubleVariable((NumberProducingDataflowTask<DoubleVariable>) new Min<A>(a, b, location));
        else if(a.getType() == VariableType.IntVariable)
            return (A) DoubleVariable
                    .doubleVariable((NumberProducingDataflowTask<DoubleVariable>) new Min<A>(a, b, location));
        else
            throw new CompilerException("Unexpected number type " + a.getType());
    }
}