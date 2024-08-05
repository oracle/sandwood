/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.util;

import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;

public class DAGUtils {

    /**
     * Method to see if scope is nested in or equal to outerScope.
     * 
     * @param outerScope
     * @param scope
     * @return
     */
    public static boolean nestedScope(Scope outerScope, Scope scope) {
        while(scope != outerScope && scope != GlobalScope.scope)
            scope = scope.getEnclosingScope();
        return scope == outerScope;
    }
}
