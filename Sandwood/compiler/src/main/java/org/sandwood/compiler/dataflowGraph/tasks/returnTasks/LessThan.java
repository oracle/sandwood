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
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRBinOp;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class LessThan<L extends NumberVariable<L>, R extends NumberVariable<R>>
        extends ProducingDataflowTaskImplementation<BooleanVariable> {

    public final Variable<L> left;
    public final Variable<R> right;

    private LessThan(Variable<L> a, Variable<R> b, Location location) {
        super(DFType.LESS_THAN, VariableType.BooleanVariable, location, a, b);
        this.left = a;
        this.right = b;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return left.getExpression(compressSandwoodCode) + " >= " + right.getExpression(compressSandwoodCode);
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(this.getType() != other.getType())
            return false;
        LessThan<?, ?> dft = (LessThan<?, ?>) other;
        return (left.equivalent(dft.left) && right.equivalent(dft.right));
    }

    @Override
    public IRBinOp<L, R, BooleanVariable> getForwardIRinternal(CompilationContext compilationCtx) {
        return IRTree.lessThan(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
    }

    @Override
    public IRTreeReturn<? extends NumberVariable<?>> getInverseIRInternal(int argPos,
            IRTreeReturn<BooleanVariable> taskOutput, BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        throw new CompilerException(checkInversionError(argPos));
    }

    @Override
    public String checkInversionError(int argPos) {
        return "There is no inverse to a less than operation.";
    }

    /* Factory methods for construction */
    public static <L extends NumberVariable<L>> BooleanVariable lessThan(Variable<L> a, double d) {
        return lessThan(a, Variable.doubleVariable(d), null);
    }

    public static <L extends NumberVariable<L>> BooleanVariable lessThan(Variable<L> a, double d, Location location) {
        return lessThan(a, Variable.doubleVariable(d), location);
    }

    public static <L extends NumberVariable<L>> BooleanVariable lessThan(Variable<L> a, int i) {
        return lessThan(a, Variable.intVariable(i), null);
    }

    public static <L extends NumberVariable<L>> BooleanVariable lessThan(Variable<L> a, int i, Location location) {
        return lessThan(a, Variable.intVariable(i), location);
    }

    public static <R extends NumberVariable<R>> BooleanVariable lessThan(double d, Variable<R> b) {
        return lessThan(Variable.doubleVariable(d), b, null);
    }

    public static <R extends NumberVariable<R>> BooleanVariable lessThan(double d, Variable<R> b, Location location) {
        return lessThan(Variable.doubleVariable(d), b, location);
    }

    public static <R extends NumberVariable<R>> BooleanVariable lessThan(int i, Variable<R> b) {
        return lessThan(Variable.intVariable(i), b, null);
    }

    public static <R extends NumberVariable<R>> BooleanVariable lessThan(int i, Variable<R> b, Location location) {
        return lessThan(Variable.intVariable(i), b, location);
    }

    public static <L extends NumberVariable<L>, R extends NumberVariable<R>> BooleanVariable lessThan(Variable<L> a,
            Variable<R> b) {
        return lessThan(a, b, null);
    }

    public static <L extends NumberVariable<L>, R extends NumberVariable<R>> BooleanVariable lessThan(Variable<L> a,
            Variable<R> b, Location location) {
        return BooleanVariable.booleanVariable(new LessThan<>(a, b, location));
    }
}