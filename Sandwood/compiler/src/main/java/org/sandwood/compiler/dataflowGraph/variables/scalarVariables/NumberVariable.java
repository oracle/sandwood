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
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.NumberType;
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
        return (NumberType<A>)getParent().getOutputType();
    }

    @Override
    public IRTreeReturn<A> getMax(CompilationContext compilationCtx) {
        return getParent().getMax(compilationCtx);
    }

    @Override
    public IRTreeReturn<A> getMin(CompilationContext compilationCtx) {
        return getParent().getMin(compilationCtx);
    }
}
