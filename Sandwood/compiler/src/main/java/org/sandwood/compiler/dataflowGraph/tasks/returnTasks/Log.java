/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.autoDiff.DifferentialInfo;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Log<A extends NumberVariable<A>> extends NumberProducingDataflowTaskImplementation<DoubleVariable> {

    private final A input;

    private Log(A input, Location location) {
        super(DFType.LOG, VariableType.DoubleVariable, location, input);
        this.input = input;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return getType().getDescription() + "(" + input.getExpression(compressSandwoodCode) + ")";
    }

    public static <A extends NumberVariable<A>> DoubleVariable log(A v) {
        return log(v, null);
    }

    public static <A extends NumberVariable<A>> DoubleVariable log(A v, Location location) {
        return DoubleVariable.doubleVariable(new Log<>(v, location));
    }

    @Override
    public IRTreeReturn<DoubleVariable> getForwardIRinternal(
            CompilationContext compilationCtx) {
        return IRTree.log(input.getForwardIR(compilationCtx));
    }

    @SuppressWarnings("unchecked")
    @Override
    public IRTreeReturn<A> getInverseIRInternal(int argPos, IRTreeReturn<DoubleVariable> taskOutput,
            BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        IRTreeReturn<DoubleVariable> d = IRTree.exp(taskOutput);
        if(input instanceof IntVariable)
            return (IRTreeReturn<A>) IRTree.castToInteger(d);
        else
            return (IRTreeReturn<A>) d;
    }

    @Override
    public String checkInversionError(int argPos) {
        return null;
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(getType() != other.getType())
            return false;
        return input.equivalent(((Log<?>) other).input);
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
        return IRTree.log(input.getMax(compilationCtx));
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.log(input.getMin(compilationCtx));
    }
    
    @Override
    public DoubleVariable getDifferentialInternal(Variable<?> variable, CompilationContext compilationCtx) {
    	// (log(f(x)))' = f'(x)/f(x).
    	// Note: If we change Log to utilize a different base "b" than 10, we need to calculate
    	// differential based on the formula (logb(f(x)))' = f'(x)/(log(b)*f(x)).
    	if (containsVariable(variable)) {
			if(input instanceof IntVariable) {
				return input.getDifferential(variable, compilationCtx).divide((IntVariable) input);
			} else {
				return input.getDifferential(variable, compilationCtx).divide((DoubleVariable) input);
			}
    	}
    	return Variable.doubleVariable(0.0);
    }

    @Override
    public DifferentialInfo getDifferentialInfo(Variable<?> variable) {
    	
    	// NOTE: We are aware that this will return true for the case
    	// that the input is a 0, but the differential will essentially
    	// fail upon execution, due to divide-by-zero error.
    	return input.getDifferentialInfo(variable);
    }
}
