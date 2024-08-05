/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.ExternalFunction;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class IsNaN<A extends NumberVariable<A>> extends ProducingDataflowTaskImplementation<BooleanVariable> {

    public final A input;

    private IsNaN(Location location, A input) {
        super(DFType.IS_NAN, VariableType.BooleanVariable, location, input);
        this.input = input;
    }

    @Override
    public String checkInversionError(int argPos) {
        return "There is no inverse to the check isNaN.";
    }

    @Override
    protected IRTreeReturn<?> getInverseIRInternal(int argPos, IRTreeReturn<BooleanVariable> taskOutput,
            BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        throw new CompilerException(checkInversionError(argPos));
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return getType().getDescription() + "(" + input.getExpression(compressSandwoodCode) + ")";
    }

    @Override
    protected IRTreeReturn<BooleanVariable> getForwardIRinternal(CompilationContext compilationCtx) {
        return IRTree.functionCallReturn(ExternalFunction.IS_NAN, VariableType.BooleanVariable,
                input.getForwardIR(compilationCtx));
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(getType() != other.getType())
            return false;
        return input.equivalent(((IsNaN<?>) other).input);
    }

    public static BooleanVariable isNaN(DoubleVariable v, Location location) {
        return BooleanVariable.booleanVariable(new IsNaN<>(location, v));
    }
}
