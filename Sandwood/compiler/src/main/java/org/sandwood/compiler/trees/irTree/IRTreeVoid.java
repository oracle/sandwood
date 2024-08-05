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
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;

public abstract class IRTreeVoid extends IRTree {

    protected String comment;

    public IRTreeVoid(IRTreeType type, String comment) {
        super(type);
        this.comment = comment;
    }

    @Override
    public abstract TransTreeVoid toTransformationTree();

    @Override
    public abstract IRTreeVoid applyTransformation(TreeTransformation t);

    public void prefixComment(String prefix) {
        if(prefix != Tree.NoComment) {
            if(comment == Tree.NoComment)
                comment = prefix;
            else
                comment = prefix + "\n\n" + comment;
        }
    }

    public void postfixComment(String postfix) {
        if(postfix != Tree.NoComment) {
            if(comment == Tree.NoComment)
                comment = postfix;
            else
                comment = comment + "\n\n" + postfix;
        }
    }

    public String getComment() {
        return comment;
    }
}
