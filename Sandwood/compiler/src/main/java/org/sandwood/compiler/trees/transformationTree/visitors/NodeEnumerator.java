/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.visitors;

import java.util.Set;

import org.sandwood.compiler.trees.transformationTree.TransTree;

public class NodeEnumerator implements TreeVisitor {

    private final Set<TransTree<?>> visitedNodes;

    public NodeEnumerator(Set<TransTree<?>> visitedNodes) {
        this.visitedNodes = visitedNodes;
    }

    @Override
    public void visit(TransTree<?> tree) {
        visitedNodes.add(tree);
        tree.traverseTree(this);
    }
}
