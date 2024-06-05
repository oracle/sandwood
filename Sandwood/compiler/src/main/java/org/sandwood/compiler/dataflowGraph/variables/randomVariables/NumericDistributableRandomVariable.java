/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.randomVariables;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.autoDiff.DifferentialInfo;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.CompilerException;

public abstract class NumericDistributableRandomVariable<A extends NumberVariable<A>, B extends NumericDistributableRandomVariable<A, B>>
        extends DistributableRandomVariable<A, B> implements NumericRandomVariable<A, B> {

    protected NumericDistributableRandomVariable(RandomVariableConstructorTask<A, B> parent, Type<A> outputType) {
        super(parent, outputType);
    }
    
	@Override
	public DoubleVariable getDifferential(Variable<?> variable, CompilationContext compilationCtx) {
		throw new CompilerException("Variable non-differentiable.");
	}
	
	
	@Override
	public boolean isDifferentiable(Variable<?> variable) {
		return false;
	}

	@Override
	public final DifferentialInfo getDifferentialInfo(Variable<?> variable) {
		return new DifferentialInfo(false, containsVariable(variable));
	}
}
