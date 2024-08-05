/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.Collections;
import java.util.Set;

import org.sandwood.compiler.trees.Tree;

public abstract class TransTreeVoid extends TransTree<TransTreeVoid> {

    protected String comment;
    private Set<String> allVariables;

    protected TransTreeVoid(TransTreeType type, String comment) {
        super(type);
        this.comment = comment;
    }

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

    // The variables declared in the tree that will be visible to later trees.
    public Set<String> declaredLocalVariables() {
        return Collections.emptySet();
    }

    // All the variable declared in the tree.
    protected abstract Set<String> declaredAllVariablesInternal();

    public Set<String> declaredAllVariables() {
        if(allVariables == null)
            allVariables = declaredAllVariablesInternal();
        return allVariables;
    }
}
