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
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class IntToDouble extends NumberProducingDataflowTaskImplementation<DoubleVariable> {

    protected final IntVariable input;

    private IntToDouble(IntVariable input, Location location) {
        super(DFType.INT_TO_DOUBLE_CAST, VariableType.DoubleVariable, location, input);
        this.input = input;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return "((double)" + input.getExpression(compressSandwoodCode) + ")";
    }

    @Override
    protected IRTreeReturn<DoubleVariable> getForwardIRinternal(
            CompilationContext compilationCtx) {
        return IRTree.castToDouble(input.getForwardIR(compilationCtx));
    }

    @Override
    protected IRTreeReturn<IntVariable> getInverseIRInternal(int argPos, IRTreeReturn<DoubleVariable> taskOutput,
            BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        return IRTree.castToInteger(taskOutput);
    }

    @Override
    public String checkInversionError(int argPos) {
        return null;
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(this.getType() != other.getType())
            return false;
        else {
            IntToDouble dft = (IntToDouble) other;
            return input.equivalent(dft.input);
        }
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
        return IRTree.castToDouble(input.getMax(compilationCtx));
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.castToDouble(input.getMin(compilationCtx));
    }

    /* Factory methods for construction */
    public static DoubleVariable intToDouble(IntVariable input) {
        return intToDouble(input, null);
    }

    public static DoubleVariable intToDouble(IntVariable input, Location location) {
        return DoubleVariable.doubleVariable(new IntToDouble(input, location));
    }
}