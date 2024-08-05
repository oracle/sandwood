/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTree.TransTreeType;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;

/**
 * Class to implement a block of scope with limited scope.
 *
 */
public class IRTreeScope extends IRTreeVoid {

    private final IRTreeVoid tree;

    IRTreeScope(IRTreeVoid tree, String comment) {
        super(IRTreeType.SCOPE, comment);
        this.tree = tree;
    }

    @Override
    public IRTree[] getChildren() {
        return new IRTree[] { tree };
    }

    @Override
    public String getDescription() {
        return "Scope";
    }

    @Override
    public TransTreeVoid toTransformationTree() {
        TransTreeVoid t = tree.toTransformationTree();
        if(t.type == TransTreeType.NOP)
            return t;
        else
            return TransTree.treeScope(t, comment);
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + tree.equivalentHashCode();
        return result;
    }

    @Override
    public boolean equivalent(IRTree t) {
        if(this == t)
            return true;
        if((t == null) || (type != t.type))
            return false;
        IRTreeScope other = (IRTreeScope) t;
        return tree.equivalent(other.tree);
    }

    @Override
    public IRTreeScope applyTransformation(TreeTransformation t) {
        return new IRTreeScope(t.transform(tree), comment);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(tree);
    }
}
