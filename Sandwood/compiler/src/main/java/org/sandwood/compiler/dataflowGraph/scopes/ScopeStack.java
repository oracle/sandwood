/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.scopes;

import java.util.Stack;

import org.sandwood.compiler.exceptions.CompilerException;

public class ScopeStack {
    private static final ThreadLocal<Stack<Scope>> currentScope;

    static {
        currentScope = ThreadLocal.withInitial(() -> {
            Stack<Scope> s = new Stack<>();
            s.push(GlobalScope.scope);
            return s;
        });
    }

    public static void remove() {
        currentScope.remove();
    }

    public static Scope getCurrentScope() {
        return currentScope.get().peek();
    }

    public static void pushScope(Scope scope) {
        currentScope.get().push(scope);
    }

    public static void popScope(Scope scope) {
        Stack<Scope> s = currentScope.get();
        if(s.peek() == scope)
            s.pop();
        else
            throw new CompilerException("Scope on stack and argument should match.");
    }
    
    public static void reset() {
        Stack<Scope> s = currentScope.get();
        s.clear();
        s.add(GlobalScope.scope);
    }
}
