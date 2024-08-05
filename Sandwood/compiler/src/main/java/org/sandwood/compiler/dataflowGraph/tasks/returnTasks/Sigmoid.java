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
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Sigmoid extends NumberProducingDataflowTaskImplementation<DoubleVariable> {

    private final DoubleVariable input;

    private Sigmoid(DoubleVariable d, Location location) {
        super(DFType.SIGMOID, VariableType.DoubleVariable, location, d);
        input = d;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return getType().getDescription() + "(" + input.getExpression(compressSandwoodCode) + ")";
    }

    public static DoubleVariable sigmoid(DoubleVariable d) {
        return sigmoid(d, null);
    }

    public static DoubleVariable sigmoid(DoubleVariable d, Location location) {
        return DoubleVariable.doubleVariable(new Sigmoid(d, location));
    }

    @Override
    public IRTreeReturn<DoubleVariable> getForwardIRinternal(
            CompilationContext compilationCtx) {
        return IRTree.divideDD(IRTree.constant(1.0),
                IRTree.addID(IRTree.constant(1), IRTree.exp(IRTree.negate(input.getForwardIR(compilationCtx)))));
    }

    @Override
    public IRTreeReturn<DoubleVariable> getInverseIRInternal(int argPos, IRTreeReturn<DoubleVariable> taskOutput,
            BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        return IRTree.negate(
                IRTree.log(IRTree.subtractDI(IRTree.divideDD(IRTree.constant(1.0), taskOutput), IRTree.constant(1))));
    }

    @Override
    public String checkInversionError(int argPos) {
        return null;
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
        return IRTree.divideDD(IRTree.constant(1.0),
                IRTree.addID(IRTree.constant(1), IRTree.exp(IRTree.negate(input.getMax(compilationCtx)))));
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.divideDD(IRTree.constant(1.0),
                IRTree.addID(IRTree.constant(1), IRTree.exp(IRTree.negate(input.getMin(compilationCtx)))));
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(getType() != other.getType())
            return false;
        return input.equivalent(((Sigmoid) other).input);
    }
}
