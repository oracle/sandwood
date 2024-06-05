/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;

public abstract class NumberProducingDataflowTaskImplementation<A extends NumberVariable<A>>
        extends ProducingDataflowTaskImplementation<A> implements NumberProducingDataflowTask<A> {

    public NumberProducingDataflowTaskImplementation(DFType dfType, VariableType.Type<A> type, 
            boolean deterministic, Location location, Variable<?>... vars) {
        super(dfType, type, deterministic, location, vars);
    }

    public NumberProducingDataflowTaskImplementation(DFType dfType, VariableType.Type<A> type, 
            Location location, Variable<?>... vars) {
        super(dfType, type, location, vars);
    }
    
    @Override
    public DoubleVariable getDifferential(Variable<?> variable, CompilationContext compilationCtx) {
    	ScopeStack.pushScope(scope());
    	DoubleVariable differential = getDifferentialInternal(variable, compilationCtx);
    	ScopeStack.popScope(scope());
    	return differential;
    }

    @Override
    public boolean isDifferentiable(Variable<?> variable) {
    	return getDifferentialInfo(variable).isDifferentiable();
    }

    protected abstract DoubleVariable getDifferentialInternal(Variable<?> variable, CompilationContext compilationCtx);
}
