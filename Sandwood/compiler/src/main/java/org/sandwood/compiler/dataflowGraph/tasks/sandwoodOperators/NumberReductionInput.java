/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.autoDiff.DifferentialInfo;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class NumberReductionInput<A extends NumberVariable<A>> extends ReductionInput<A>
        implements NumberProducingDataflowTask<A> {

    public NumberReductionInput(IntVariable start, IntVariable end, Variable<A> emptyValue, ArrayVariable<A> a,
            boolean first, Location location) {
        super(start, end, emptyValue, a, first, location);
    }

    @Override
    public IRTreeReturn<A> getMax(CompilationContext compilationCtx) {
        throw new CompilerException("Max is not currently supported on reductions");
    }

    @Override
    public IRTreeReturn<A> getMin(CompilationContext compilationCtx) {
        throw new CompilerException("Min is not currently supported on reductions");
    }
    
    @Override
    public DoubleVariable getDifferential(Variable<?> variable, CompilationContext compilationCtx) {
    	throw new CompilerException("ReductionReturnNumberTask is not differentiable.");
    }


	@Override
	public boolean isDifferentiable(Variable<?> variable) {
		return getDifferentialInfo(variable).isDifferentiable();
	}
	
	// TODO: Revise.
	@Override
	public DifferentialInfo getDifferentialInfo(Variable<?> variable) {
		// Note for now we set it as non-differentiable, but we will need to implement
		// differentials for arrays and define how this reduction will exactly be differentiated.
		// Inputs here are: start, end, emptyValue, a.
		return new DifferentialInfo(false, containsVariable(variable));
	}
}
