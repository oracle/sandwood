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
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.ScalarVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRBinOp;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Eq<A extends ScalarVariable<A>, B extends ScalarVariable<B>>
        extends ProducingDataflowTaskImplementation<BooleanVariable> {

    public final Variable<A> left;
    public final Variable<B> right;

    Eq(Variable<A> a, Variable<B> b, Location location) {
        super(DFType.EQ, VariableType.BooleanVariable, location, a, b);
        this.left = a;
        this.right = b;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return left.getExpression(compressSandwoodCode) + " == " + right.getExpression(compressSandwoodCode);
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(this.getType() != other.getType())
            return false;
        Eq<?, ?> dft = (Eq<?, ?>) other;
        return (left.equivalent(dft.left) && right.equivalent(dft.right))
                || (left.equivalent(dft.right) && right.equivalent(dft.left));
    }

    @Override
    public IRBinOp<A, B, BooleanVariable> getForwardIRinternal(CompilationContext compilationCtx) {
        return IRTree.eq(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
    }

    @Override
    public IRTreeReturn<? extends ScalarVariable<?>> getInverseIRInternal(int argPos,
            IRTreeReturn<BooleanVariable> taskOutput, BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        if(left.getType() == VariableType.BooleanVariable
                && right.getType() == VariableType.BooleanVariable) {
            switch(argPos) {
                case 0:
                    return IRTree.eq(((BooleanVariable) right).getForwardIR(compilationCtx), taskOutput);
                case 1:
                    return IRTree.eq(((BooleanVariable) left).getForwardIR(compilationCtx), taskOutput);
                default:
                    throw new CompilerException("Argument position " + argPos + " was provided for a 2 argument task.");
            }
        } else
            throw new CompilerException("There is no inverse to an equals operation on numeric values.");
    }

    @Override
    public String checkInversionError(int argPos) {
        if(left.getType() == VariableType.BooleanVariable
                && right.getType() == VariableType.BooleanVariable)
            return null;
        else
            return "There is no inverse to an equals operation on numeric values.";
    }

    /* Factory methods for construction */
    public static <A extends ScalarVariable<A>, B extends ScalarVariable<B>> BooleanVariable eq(Variable<A> a,
            Variable<B> b) {
        return eq(a, b, null);
    }

    public static <A extends ScalarVariable<A>, B extends ScalarVariable<B>> BooleanVariable eq(Variable<A> a,
            Variable<B> b, Location location) {
        return BooleanVariable.booleanVariable(new Eq<>(a, b, location));
    }
}