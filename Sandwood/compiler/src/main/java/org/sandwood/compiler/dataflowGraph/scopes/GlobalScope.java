/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.scopes;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.ScopeTracking;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

/**
 * A top level class to act as a scope when no tasks are acting as the scope. *
 */
public class GlobalScope implements Scope {

    public static final GlobalScope scope = new GlobalScope();

    private GlobalScope() {}

    @Override
    public int id() {
        return 0;
    }

    @Override
    public int getSandwoodCodePrefix(StringBuilder sb, int indent, boolean compressSandwoodCode) {
        return indent;
    }

    @Override
    public int getSandwoodCodePostfix(StringBuilder sb, int indent) {
        return 0;
    }

    @Override
    public Scope getEnclosingScope() {
        return null;
    }

    @Override
    public boolean iterating() {
        return false;
    }

    @Override
    public IRTreeVoid getScopeTree(ScopeTracking scopeTracking, IRTreeVoid tree, boolean reverseScopes,
            CompilationContext compilationCtx) {
        return tree;
    }

    @Override
    public String getDescription() {
        return "Global 0";
    }

    @Override
    public ScopeType getScopeType() {
        return ScopeType.GLOBAL;
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
        sb.append("Global Scope\n");
        return sb.toString();
    }

    @Override
    public boolean isSerial(CompilationContext compilationCtx) {
        return true;
    }
}
