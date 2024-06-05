/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.autoDiff.DifferentialInfo;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class ReductionReturnNumberTask<A extends NumberVariable<A>> extends ReductionReturnTask<A>
        implements NumberProducingDataflowTask<A> {

    public ReductionReturnNumberTask(A var, NumberReductionInput<A> leftInput, NumberReductionInput<A> rightInput,
            Location location) {
        super(var, leftInput, rightInput, location);
    }

    @Override
    public IRTreeReturn<A> getMax(CompilationContext compilationCtx) {
        throw new CompilerException("The maximum value of a reduction cannot currently be calculated");
    }

    @Override
    public IRTreeReturn<A> getMin(CompilationContext compilationCtx) {
        throw new CompilerException("The minimum value of a reduction cannot currently be calculated");
    }
    
    @Override
    public DoubleVariable getDifferential(Variable<?> variable, CompilationContext compilationCtx) {
    	throw new CompilerException("ReductionReturnNumberTask is not differentiable.");
    }

	@Override
	public boolean isDifferentiable(Variable<?> variable) {
		return  getDifferentialInfo(variable).isDifferentiable();
	}
	
	@Override
	public DifferentialInfo getDifferentialInfo(Variable<?> variable) {
		// TODO: Explore this once we define differentials for reduction input.
		// For now, consider it as non-differentiable.
		return new DifferentialInfo(false, containsVariable(variable));
	}
}
