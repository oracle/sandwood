/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.scalarVariables;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.NumberProperties;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.NumberType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.traces.DAGInfo;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public abstract class NumberVariable<A extends NumberVariable<A>> extends ScalarVariable<A>
        implements NumberProperties<A> {

    protected NumberVariable(NumberProducingDataflowTask<A> parent) {
        super(parent);
    }

    @Override
    public NumberProducingDataflowTask<A> getParent() {
        // TODO add generics to parent so that these casts can be removed.
        return (NumberProducingDataflowTask<A>) super.getParent();
    }

    @Override
    public NumberType<A> getType() {
        // TODO add generics so that these casts can be removed.
        return (NumberType<A>) getParent().getOutputType();
    }

    @Override
    public IRTreeReturn<A> getMax(CompilationContext compilationCtx) {
        return getParent().getMax(compilationCtx);
    }

    @Override
    public IRTreeReturn<A> getMin(CompilationContext compilationCtx) {
        return getParent().getMin(compilationCtx);
    }
    
	@Override
	public boolean isDifferentiable(Variable<?> variable) {
		return getDifferentialInfo(variable).isDifferentiable();
	}
	 

	@Override
	public final DoubleVariable getDifferential(Variable<?> variable, CompilationContext compilationCtx) {

		// If the differential variable is already calculated, obtain and return it.
		DoubleVariable ctxDifferentialVariable = compilationCtx.getDifferentialScalar(this, variable);
		if (ctxDifferentialVariable != null) {
			return ctxDifferentialVariable;
		}

		ScopeStack.pushScope(scope());
		DoubleVariable differential = null;
		if (this == variable) {
			differential =  DoubleVariable.doubleVariable(1.0);
        } else {
        	differential = getParent().getDifferential(variable, compilationCtx);
        }
	  	ScopeStack.popScope(scope());

		// Add the association of base -> variable -> differential
	  	// to compilation context and its traces.
	  	if (compilationCtx.getDifferentialScalar(this, variable) == null) { 
	  		compilationCtx.addDifferentialScalar(this, variable, differential);
	  		compilationCtx.traces.addDifferentialVariable(differential);
	  	}

	  	return differential;
	}

	public abstract DoubleVariable add(DoubleVariable variable);
	public abstract A add(IntVariable variable);

    @Override
    public boolean isDifferentiable(Variable<?> variable) {
        return getDifferentialInfo(variable).isDifferentiable();
    }

    @Override
    public final DoubleVariable getDifferential(Variable<?> variable, CompilationContext compilationCtx) {
        ScopeStack.pushScope(scope());
        DoubleVariable differential = null;
        if(this == variable) {
            differential = DoubleVariable.doubleVariable(1.0);
        } else {
            differential = getParent().getDifferential(variable, compilationCtx);
        }
        ScopeStack.popScope(scope());
        return differential;
    }

    public abstract DoubleVariable add(DoubleVariable variable);

    public abstract A add(IntVariable variable);

    public abstract DoubleVariable subtract(DoubleVariable variable);

    public abstract A subtract(IntVariable variable);

    public abstract DoubleVariable times(DoubleVariable variable);

    public abstract A times(IntVariable variable);

    public abstract DoubleVariable divide(DoubleVariable variable);

    public abstract A divide(A variable);

    public abstract A divide(IntVariable variable);

    public abstract DoubleVariable remainder(DoubleVariable variable);

    public abstract A remainder(IntVariable variable);
}
