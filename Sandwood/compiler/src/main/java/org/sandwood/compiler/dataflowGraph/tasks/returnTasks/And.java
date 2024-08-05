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
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRBinOp;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class And extends ProducingDataflowTaskImplementation<BooleanVariable> {

    public final BooleanVariable left;
    public final BooleanVariable right;

    private And(BooleanVariable left, BooleanVariable right, Location location) {
        super(DFType.AND, VariableType.BooleanVariable, location, left, right);
        this.left = left;
        this.right = right;
    }

    @Override
    public String checkInversionError(int argPos) {
        return "There is no inverse to an \"and\" operation.";
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return left.getExpression(compressSandwoodCode) + " && " + right.getExpression(compressSandwoodCode);
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(this.getType() != other.getType())
            return false;
        And dft = (And) other;
        return (left.equivalent(dft.left) && right.equivalent(dft.right))
                || (left.equivalent(dft.right) && right.equivalent(dft.left));
    }

    @Override
    public IRBinOp<BooleanVariable, BooleanVariable, BooleanVariable> getForwardIRinternal(
            CompilationContext compilationCtx) {
        return IRTree.and(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
    }

    @Override
    public IRTreeReturn<BooleanVariable> getInverseIRInternal(int argPos, IRTreeReturn<BooleanVariable> taskOutput,
            BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        throw new CompilerException(checkInversionError(argPos));
    }

    /* Factory methods for construction */
    public static BooleanVariable and(BooleanVariable a, boolean b) {
        return and(a, Variable.booleanVariable(b), null);
    }

    public static BooleanVariable and(BooleanVariable a, boolean b, Location location) {
        return and(a, Variable.booleanVariable(b), location);
    }

    public static BooleanVariable and(BooleanVariable a, BooleanVariable b) {
        return and(a, b, null);
    }

    public static BooleanVariable and(BooleanVariable a, BooleanVariable b, Location location) {
        return BooleanVariable.booleanVariable(new And(a, b, location));
    }
}