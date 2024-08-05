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
import org.sandwood.compiler.dataflowGraph.Id;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class ElseScope extends Id implements Scope {

    private final Scope scope;
    public final IfScope ifScope;

    public ElseScope(Scope scope, IfScope ifScope) {
        this.scope = scope;
        this.ifScope = ifScope;
    }

    @Override // TODO work out what the correct value for this should be.
    public int getSandwoodCodePrefix(StringBuilder sb, int indent, boolean compressSandwoodCode) {
        return indent;
    }

    @Override // TODO work out what the correct value for this should be.
    public int getSandwoodCodePostfix(StringBuilder sb, int indent) {
        return indent;
    }

    @Override
    public String getDescription() {
        return "Else " + id();
    }

    @Override
    public Scope getEnclosingScope() {
        return scope;
    }

    @Override
    public ScopeType getScopeType() {
        return ScopeType.ELSE;
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
    public IRTreeVoid getScopeTree(ScopeTracking scopeTracking, IRTreeVoid tree, boolean reverseScopes,
            CompilationContext compilationCtx) {
        return IRTree.nop();
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
        sb.append("else scope " + id() + " !"
                + (ifScope.guardTree == null ? ifScope.guard.toString() : ifScope.guardTree.toString()) + "\n"
                + scope.toString(i + 1));
        return sb.toString();
    }
}
