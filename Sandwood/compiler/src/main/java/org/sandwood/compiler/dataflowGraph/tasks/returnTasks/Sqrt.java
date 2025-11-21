/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
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
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Sqrt extends NumberProducingDataflowTaskImplementation<DoubleVariable> {

    protected final DoubleVariable d;

    private Sqrt(Location location, DoubleVariable d) {
        super(DFType.SQRT, VariableType.DoubleVariable, location, d);
        this.d = d;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return "sqrt(" + d.getExpression(compressSandwoodCode) + ")";
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
                d.getForwardIR(compilationCtx));
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(this.getType() != other.getType())
            return false;
        Sqrt dft = (Sqrt) other;
        return d.equivalent(dft.d);
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
        return IRTree.functionCallReturn(ExternalFunction.SQRT, VariableType.DoubleVariable, d.getMax(compilationCtx));
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.functionCallReturn(ExternalFunction.SQRT, VariableType.DoubleVariable, d.getMin(compilationCtx));
    }

    /* Factory methods for construction */

    public static DoubleVariable sqrt(double d) {
        return sqrt(Variable.doubleVariable(d), null);
    }

    public static DoubleVariable sqrt(DoubleVariable d) {
        return sqrt(d, null);
    }

    public static DoubleVariable sqrt(double d, Location location) {
        return sqrt(Variable.doubleVariable(d), location);
    }

    public static DoubleVariable sqrt(DoubleVariable d, Location location) {
        return DoubleVariable.doubleVariable(new Sqrt(location, d));
    }

    public static DoubleVariable sqrt(int d) {
        return sqrt(Variable.doubleVariable(d), null);
    }

    public static DoubleVariable sqrt(IntVariable d) {
        return sqrt(d.castToDouble(), null);
    }

    public static DoubleVariable sqrt(int d, Location location) {
        return sqrt(Variable.doubleVariable(d), location);
    }

    public static DoubleVariable sqrt(IntVariable d, Location location) {
        return DoubleVariable.doubleVariable(new Sqrt(location, d.castToDouble(location)));
    }
}