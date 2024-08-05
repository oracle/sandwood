/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public interface NumberProperties<A extends NumberVariable<A>> {
    IRTreeReturn<A> getMax(CompilationContext compilationCtx);

    IRTreeReturn<A> getMin(CompilationContext compilationCtx);
}
