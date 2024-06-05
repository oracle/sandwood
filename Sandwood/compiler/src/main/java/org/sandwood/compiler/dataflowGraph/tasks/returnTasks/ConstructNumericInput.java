/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.autoDiff.DifferentialInfo;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class ConstructNumericInput<A extends NumberVariable<A>> extends ConstructInput<A>
        implements NumberProducingDataflowTask<A> {

    public ConstructNumericInput(Type<A> type, String name, Location location) {
        super(type, name, location);
    }

    @Override
    public IRTreeReturn<A> getMax(CompilationContext compilationCtx) {
        return IRTree.load(getOutput().getUniqueVarDesc());
    }

    @Override
    public IRTreeReturn<A> getMin(CompilationContext compilationCtx) {
        return IRTree.load(getOutput().getUniqueVarDesc());
    }

    @Override
    public DoubleVariable getDifferential(Variable<?> variable, CompilationContext compilationCtx) {
    	ScopeStack.pushScope(scope());
    	DoubleVariable differential = Variable.doubleVariable(0.0);
    	ScopeStack.popScope(scope());
    	return differential;
    }
	
	@Override
	public boolean isDifferentiable(Variable<?> variable) {
		return getDifferentialInfo(variable).isDifferentiable();
	}
	
	@Override
	public DifferentialInfo getDifferentialInfo(Variable<?> variable) {
		return new DifferentialInfo(true, false);
	}
}
