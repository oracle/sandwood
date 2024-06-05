/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks.constant;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;

public abstract class ConstantNumberValueTask<A extends NumberVariable<A>> extends ConstantValueTask<A>
        implements NumberProducingDataflowTask<A> {

    ConstantNumberValueTask(DFType dfType, Type<A> type, Location location) {
        super(dfType, type, location);
    }
    
	@Override
	public DoubleVariable getDifferential(Variable<?> variable, CompilationContext compilationCtx) {
		ScopeStack.pushScope(scope());
		DoubleVariable differential = DoubleVariable.doubleVariable(0.0);
		ScopeStack.popScope(scope());
		return differential;
	}

	@Override
	public boolean isDifferentiable(Variable<?> variable) {
		return getDifferentialInfo(variable).isDifferentiable();
	}
}
