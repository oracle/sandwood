/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.transformers;

import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;

public abstract class Transformer {

    public Set<TransTree<?>> visitedNodes;

    public <T extends TransTree<T>> T transform(TransTree<T> transTree, Set<TransTree<?>> visitedNodes) {
        this.visitedNodes = visitedNodes;
        return transform(transTree);
    }

    @SuppressWarnings("unchecked")
    public <T extends TransTree<T>> T transform(TransTree<T> tree) {
        if(tree instanceof TransTreeVoid) {
            return (T) transformVoid((TransTreeVoid) tree);
        } else if(tree instanceof TransTreeReturn) {
            return (T) transformReturn((TransTreeReturn<?>) tree);
        } else
            throw new CompilerException("Unknown tree type");

    }

    protected abstract TransTreeVoid transformVoid(TransTreeVoid tree);

    protected abstract <X extends Variable<X>> TransTreeReturn<X> transformReturn(TransTreeReturn<X> tree);
}
