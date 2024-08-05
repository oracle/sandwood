/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class IfElseNumberAssignmentTask<A extends NumberVariable<A>> extends IfElseAssignmentTask<A>
        implements NumberProducingDataflowTask<A> {

    public IfElseNumberAssignmentTask(BooleanVariable guard, A ifValue, A elseValue, Location location) {
        super(guard, ifValue, elseValue, location);
    }

    @Override
    public IRTreeReturn<A> getMax(CompilationContext compilationCtx) {
        return IRTree.max(ifValue.getMax(compilationCtx), elseValue.getMax(compilationCtx));
    }

    @Override
    public IRTreeReturn<A> getMin(CompilationContext compilationCtx) {
        return IRTree.min(ifValue.getMin(compilationCtx), elseValue.getMin(compilationCtx));
    }
}
