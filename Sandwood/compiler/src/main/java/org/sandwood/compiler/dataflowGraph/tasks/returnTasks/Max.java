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
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRMax;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Max<A extends NumberVariable<A>> extends NumberProducingDataflowTaskImplementation<A> {

    protected final A a;
    protected final A b;

    private Max(VariableType.Type<A> outputType, Location location, A a, A b) {
        super(DFType.MAX, outputType, location, a, b);
        this.a = a;
        this.b = b;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return "max(" + a.getExpression(compressSandwoodCode) + ", " + b.getExpression(compressSandwoodCode) + ")";
    }

    @Override
    public IRTreeReturn<A> getInverseIRInternal(int argPos, IRTreeReturn<A> taskOutput, BackTraceInfo backTraceInfo,
            CompilationContext compilationCtx) {
        throw new CompilerException(checkInversionError(argPos));
    }

    @Override
    public String checkInversionError(int argPos) {
        return "Information is lost in Max, so it is not possible to construct an inverse function.";
    }

    @Override
    public IRMax<A> getForwardIRinternal(CompilationContext compilationCtx) {
        return IRTree.max(a.getForwardIR(compilationCtx), b.getForwardIR(compilationCtx));
    }

    @Override

    public boolean equivalent(DataflowTask<?> other) {
        if(this.getType() != other.getType())
            return false;
        Max<?> dft = (Max<?>) other;
        return ((a.equivalent(dft.a) && b.equivalent(dft.b)) || (a.equivalent(dft.b) && b.equivalent(dft.a)));
    }

    @Override
    public IRTreeReturn<A> getMax(CompilationContext compilationCtx) {
        return IRTree.max(a.getMax(compilationCtx), b.getMax(compilationCtx));
    }

    @Override
    public IRTreeReturn<A> getMin(CompilationContext compilationCtx) {
        return IRTree.min(a.getMin(compilationCtx), b.getMin(compilationCtx));
    }

    /* Factory methods for construction */

    public static DoubleVariable max(DoubleVariable a, double b) {
        return max(a, Variable.doubleVariable(b), null);
    }

    public static DoubleVariable max(double a, DoubleVariable b) {
        return max(Variable.doubleVariable(a), b, null);
    }

    public static DoubleVariable max(double a, double b) {
        return max(Variable.doubleVariable(a), Variable.doubleVariable(b), null);
    }

    public static DoubleVariable max(DoubleVariable a, DoubleVariable b) {
        return max(a, b, null);
    }

    public static DoubleVariable max(DoubleVariable a, double b, Location location) {
        return max(a, Variable.doubleVariable(b), location);
    }

    public static DoubleVariable max(double a, DoubleVariable b, Location location) {
        return max(Variable.doubleVariable(a), b, location);
    }

    public static DoubleVariable max(double a, double b, Location location) {
        return max(Variable.doubleVariable(a), Variable.doubleVariable(b), location);
    }

    public static DoubleVariable max(DoubleVariable a, DoubleVariable b, Location location) {
        return DoubleVariable.doubleVariable(new Max<>(VariableType.DoubleVariable, location, a, b));
    }

    public static IntVariable max(IntVariable a, int b) {
        return max(a, Variable.intVariable(b), null);
    }

    public static IntVariable max(int a, IntVariable b) {
        return max(Variable.intVariable(a), b, null);
    }

    public static IntVariable max(int a, int b) {
        return max(Variable.intVariable(a), Variable.intVariable(b), null);
    }

    public static IntVariable max(IntVariable a, IntVariable b) {
        return max(a, b, null);
    }

    public static IntVariable max(IntVariable a, int b, Location location) {
        return max(a, Variable.intVariable(b), location);
    }

    public static IntVariable max(int a, IntVariable b, Location location) {
        return max(Variable.intVariable(a), b, location);
    }

    public static IntVariable max(int a, int b, Location location) {
        return max(Variable.intVariable(a), Variable.intVariable(b), location);
    }

    public static IntVariable max(IntVariable a, IntVariable b, Location location) {
        return IntVariable.intVariable(new Max<>(VariableType.IntVariable, location, a, b));
    }
}