/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree.transformations;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public interface TreeTransformation {
    IRTreeVoid transform(IRTreeVoid tree);

    <X extends Variable<X>> IRTreeReturn<X> transformReturn(IRTreeReturn<X> tree);
}
