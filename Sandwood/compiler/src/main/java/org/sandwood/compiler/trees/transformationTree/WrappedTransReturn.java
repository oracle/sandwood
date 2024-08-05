/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.trees.WrappedTree;

public class WrappedTransReturn<V extends Variable<V>> extends WrappedTree<TransTree<?>, TransTreeReturn<V>> {

    public WrappedTransReturn(TransTreeReturn<V> tree) {
        super(tree);
    }
}
