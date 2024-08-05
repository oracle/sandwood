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
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class IfScope extends Id implements Scope {

    public final BooleanVariable guard;
    protected final IRTreeReturn<BooleanVariable> guardTree;

    private final Scope scope;

    public final ElseScope elseScope;

    public IfScope(BooleanVariable guard) {
        this(ScopeStack.getCurrentScope(), guard);
    }

    public IfScope(Scope scope, BooleanVariable guard) {
        this.guard = guard;
        guardTree = null;
        this.scope = scope;
        elseScope = new ElseScope(scope, this);
    }

    public IfScope(IRTreeReturn<BooleanVariable> guardTree) {
        this(ScopeStack.getCurrentScope(), guardTree);
    }

    // TODO see if we can remove this and make all guards Boolean variables
    public IfScope(Scope scope, IRTreeReturn<BooleanVariable> guardTree) {
        this.guard = null;
        this.guardTree = guardTree;
        assert (guardTree != null);
        this.scope = scope;
        elseScope = new ElseScope(scope, this);
    }

    public IfScope(Scope scope, IfScope ifScope) {
        if(ifScope.guard == null) {
            guard = null;
            guardTree = ifScope.guardTree;
        } else {
            guard = ifScope.guard;
            guardTree = null;
        }
        this.scope = scope;
        elseScope = new ElseScope(scope, this);
    }

    @Override
    public boolean iterating() {
        return false;
    }

    @Override // TODO work out what the correct value for this should be.
    public int getSandwoodCodePrefix(StringBuilder sb, int indent, boolean compressSandwoodCode) {
        return indent;
    }

    @Override
    public IRTreeVoid getScopeTree(ScopeTracking tracking, IRTreeVoid tree, boolean reverseScopes,
            CompilationContext compilationCtx) {
        IRTreeVoid elseTree = tracking.getScopeBodyTree(elseScope);
        return IRTree.ifElse(getGuardTree(compilationCtx), tree, Tree.NoComment, elseTree, Tree.NoComment);
    }

    public IRTreeReturn<BooleanVariable> getGuardTree(CompilationContext compilationCtx) {
        if(guard != null)
            return guard.getForwardIR(compilationCtx);
        else
            return guardTree;
    }

    @Override
    public String getDescription() {
        return "If " + id();
    }

    @Override
    public ScopeType getScopeType() {
        return ScopeType.IF;
    }

    @Override // TODO work out what the correct value for this should be.
    public int getSandwoodCodePostfix(StringBuilder sb, int indent) {
        return 0;
    }

    @Override
    public Scope getEnclosingScope() {
        return scope;
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
        sb.append("if scope " + id() + " " + (guardTree == null ? guard.toString() : guardTree.toString()) + "\n"
                + scope.toString(i + 1));
        return sb.toString();
    }

    @Override
    public boolean isSerial(CompilationContext compilationCtx) {
        return true;
    }
}
