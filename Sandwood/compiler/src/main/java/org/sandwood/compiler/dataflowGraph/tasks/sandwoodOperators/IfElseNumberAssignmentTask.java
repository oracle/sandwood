/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.IfScope;
import org.sandwood.compiler.dataflowGraph.autoDiff.DifferentialInfo;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class IfElseNumberAssignmentTask<A extends NumberVariable<A>> extends IfElseAssignmentTask<A>
        implements NumberProducingDataflowTask<A> {

    public IfElseNumberAssignmentTask(BooleanVariable guard, A ifValue, A elseValue, IfScope ifScope,
            Location location) {
        super(guard, ifValue, elseValue, ifScope, location);
    }

    @Override
    public IRTreeReturn<A> getMax(CompilationContext compilationCtx) {
        return IRTree.max(ifValue.getMax(compilationCtx), elseValue.getMax(compilationCtx));
    }

    @Override
    public IRTreeReturn<A> getMin(CompilationContext compilationCtx) {
        return IRTree.min(ifValue.getMin(compilationCtx), elseValue.getMin(compilationCtx));
    }

    @Override
    public DoubleVariable getDifferential(Variable<?> variable, CompilationContext compilationCtx) {
        ScopeStack.pushScope(scope());
        DoubleVariable ifValueDifferential = ifValue.getDifferential(variable, compilationCtx);
        DoubleVariable elseValueDifferential = elseValue.getDifferential(variable, compilationCtx);
        IfElseNumberAssignmentTask<DoubleVariable> ifElseDifferentialTask = new IfElseNumberAssignmentTask<DoubleVariable>(
                guard, ifValueDifferential, elseValueDifferential, ifScope, null);

        DoubleVariable differentialVariable = DoubleVariable.doubleVariable(ifElseDifferentialTask);
        ScopeStack.popScope(scope());
        return differentialVariable;
    }

    @Override
    public boolean isDifferentiable(Variable<?> variable) {
        return getDifferentialInfo(variable).isDifferentiable();
    }

    @Override
    public DifferentialInfo getDifferentialInfo(Variable<?> variable) {
        boolean containsVariable = containsVariable(variable);
        boolean isDifferentiable = (!guard.containsVariable(variable)
                && (ifValue.isDifferentiable(variable) && elseValue.isDifferentiable(variable)));

        return new DifferentialInfo(isDifferentiable, containsVariable);
    }
}
