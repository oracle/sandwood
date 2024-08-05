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
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class DoubleToInt extends NumberProducingDataflowTaskImplementation<IntVariable> {

    public final DoubleVariable input;

    private DoubleToInt(DoubleVariable input, Location location) {
        super(DFType.DOUBLE_TO_INT_CAST, VariableType.IntVariable, location, input);
        this.input = input;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return "((int)" + input.getExpression(compressSandwoodCode) + ")";
    }

    @Override
    protected IRTreeReturn<IntVariable> getForwardIRinternal(
            CompilationContext compilationCtx) {
        return IRTree.castToInteger(input.getForwardIR(compilationCtx));
    }

    @Override
    protected IRTreeReturn<DoubleVariable> getInverseIRInternal(int argPos, IRTreeReturn<IntVariable> taskOutput,
            BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        throw new CompilerException(checkInversionError(argPos));
    }

    @Override
    public String checkInversionError(int argPos) {
        return "Cannot invert the cast from double to integer as information is lost.";
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(this.getType() != other.getType())
            return false;
        else {
            DoubleToInt dft = (DoubleToInt) other;
            return input.equivalent(dft.input);
        }
    }

    @Override
    public IRTreeReturn<IntVariable> getMax(CompilationContext compilationCtx) {
        return IRTree.castToInteger(input.getMax(compilationCtx));
    }

    @Override
    public IRTreeReturn<IntVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.castToInteger(input.getMin(compilationCtx));
    }

    /* Factory methods for construction */
    public static IntVariable doubleToInt(DoubleVariable input) {
        return doubleToInt(input, null);
    }

    public static IntVariable doubleToInt(DoubleVariable input, Location location) {
        return IntVariable.intVariable(new DoubleToInt(input, location));
    }
}