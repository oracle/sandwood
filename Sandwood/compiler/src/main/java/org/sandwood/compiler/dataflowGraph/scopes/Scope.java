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
 * An interface for tasks that can act as the scope of a chunk of code.
 */

public interface Scope {
    enum ScopeType {
        GLOBAL,
        FOR,
        REDUCE,
        IF,
        ELSE,
        BLOCK,
        COMMENT
   }

    int id();

    int getSandwoodCodePrefix(StringBuilder sb, int indent, boolean compressSandwoodCode);

    int getSandwoodCodePostfix(StringBuilder sb, int indent);

    String getDescription();

    Scope getEnclosingScope();

    ScopeType getScopeType();

    boolean iterating();

    boolean isSerial(CompilationContext compilationCtx);

    IRTreeVoid getScopeTree(ScopeTracking tracking, IRTreeVoid tree, boolean reverseScopes,
            CompilationContext compilationCtx);

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);

    @Override
    String toString();

    String toString(int i);

    /**
     * A static method to find the innermost scope of a set of scopes.
     * 
     * @param scopes The scopes to consider.
     * @return The innermost of the scopes.
     */
    static Scope innerScope(Scope... scopes) {
        Scope innerScope = scopes[0];
        for(int i = 1; i < scopes.length; i++) {
            Scope s = scopes[i];
            boolean moved = false;
            while(s != innerScope && s != GlobalScope.scope) {
                s = s.getEnclosingScope();
                moved = true;
            }
            // If the innermost scope was met on the way from scope i
            // to global scope, scope i is at least as far in as the current
            // value so the current value should be updated.
            if(s != GlobalScope.scope || (s == innerScope && moved))
                innerScope = scopes[i];
        }
        return innerScope;
    }
}
