/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import java.util.Map;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.trees.Tree;

public abstract class OutputTreeReturn<X extends Variable<X>> extends OutputTree {

    protected OutputTreeReturn(OutputTreeType type) {
        super(type, false, Tree.NoComment);
    }

    @Override
    protected abstract OutputTreeReturn<X> copy(Map<OutputTree, OutputTree> results);
}
