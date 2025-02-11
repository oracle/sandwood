/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.scopes;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.ScopeTracking;
import org.sandwood.compiler.dataflowGraph.Id;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class CommentScope extends Id implements Scope {
    private final Scope scope;
    public final String comment;

    public CommentScope(String comment) {
        this(ScopeStack.getCurrentScope(), comment);
    }

    public CommentScope(Scope scope, String comment) {
        this.scope = scope;
        this.comment = comment;
    }

    @Override
    public IRTreeVoid getScopeTree(ScopeTracking tracking, IRTreeVoid tree, boolean reverseScopes,
            CompilationContext compilationCtx) {
        tree.prefixComment(comment);
        return tree;
    }

    @Override
    public int getSandwoodCodePrefix(StringBuilder sb, int indent, boolean compressSandwoodCode) {
        sb.append("\\ " + comment + "\n");
        addIndent(sb, indent);
        return indent;
    }

    @Override
    public int getSandwoodCodePostfix(StringBuilder sb, int indent) {
        return indent;
    }

    @Override
    public String getDescription() {
        return "Comment: " + comment;
    }

    @Override
    public Scope getEnclosingScope() {
        return scope;
    }

    @Override
    public ScopeType getScopeType() {
        return Scope.ScopeType.COMMENT;
    }

    @Override
    public boolean iterating() {
        return false;
    }

    @Override
    public boolean isSerial(CompilationContext compilationCtx) {
        return true;
    }

    protected void addIndent(StringBuilder sb, int depth) {
        for(int i = 0; i < depth; i++)
            sb.append("\t");
    }

    @Override
    public String toString() {
        return toString(0);
    }

    @Override
    public String toString(int i) {
        StringBuilder sb = new StringBuilder();
        for(int j = 0; j < i; j++)
            sb.append("\t");
        sb.append("Comment " + id() + " " + (comment == Tree.NoComment ? "No comment" : comment) + "\n"
                + scope.toString(i + 1));
        return sb.toString();
    }
}
