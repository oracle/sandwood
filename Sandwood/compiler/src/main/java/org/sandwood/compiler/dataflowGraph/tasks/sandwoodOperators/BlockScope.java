/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.ScopeTracking;
import org.sandwood.compiler.dataflowGraph.Id;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class BlockScope extends Id implements Scope {
    private final Scope scope;
    public final String comment;

    public BlockScope(String comment) {
        this(ScopeStack.getCurrentScope(), comment);
    }

    public BlockScope(Scope scope, String comment) {
        this.comment = comment;
        this.scope = scope;
    }

    @Override
    public int getSandwoodCodePrefix(StringBuilder sb, int indent, boolean compressSandwoodCode) {
        sb.append("{\n");
        return indent + 1;
    }

    @Override
    public int getSandwoodCodePostfix(StringBuilder sb, int indent) {
        sb.append("}\n");
        return indent - 1;
    }

    @Override
    public String getDescription() {
        return "Block " + id();
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
        sb.append("Block scope " + id() + (comment != null ? " " + comment : " No Comment") + "\n"
                + scope.toString(i + 1));
        return sb.toString();
    }

    @Override
    public Scope getEnclosingScope() {
        return scope;
    }

    @Override
    public ScopeType getScopeType() {
        return Scope.ScopeType.BLOCK;
    }

    @Override
    public boolean iterating() {
        return false;
    }

    @Override
    public boolean isSerial(CompilationContext compilationCtx) {
        return true;
    }

    @Override
    public IRTreeVoid getScopeTree(ScopeTracking tracking, IRTreeVoid tree, boolean reverseScopes,
            CompilationContext compilationCtx) {
        return IRTree.treeScope(tree, comment);
    }
}
