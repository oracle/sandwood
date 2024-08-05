/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;

public class IRNOP extends IRTreeVoid {

    static final IRNOP nop = new IRNOP();

    private IRNOP() {
        super(IRTreeType.NOP, Tree.NoComment);
    }

    @Override
    public TransTreeVoid toTransformationTree() {
        return TransTree.nop();
    }

    @Override
    public IRTree[] getChildren() {
        return new IRTree[0];
    }

    @Override
    public String getDescription() {
        return "nop";
    }

    @Override
    public boolean equivalent(IRTree tree) {
        return tree == nop;
    }

    @Override
    public int equivalentHashCode() {
        return 634;
    }

    @Override
    public IRNOP applyTransformation(TreeTransformation t) {
        return this;
    }

    @Override
    public void traverseTree(TreeVisitor v) {}

}
