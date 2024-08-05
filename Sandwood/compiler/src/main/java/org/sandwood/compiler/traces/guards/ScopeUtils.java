/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.traces.guards;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.Sandwood;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ForTask;
import org.sandwood.compiler.names.VariableNames;

class ScopeUtils {
    public static ForTask constructForScope(Scope targetScope, ForTask t, boolean duplicate, String id,
            CompilationContext compilationCtx) {
        // Construct the scope
        ScopeStack.pushScope(targetScope);
        ForTask newScope = Sandwood.forLoop(t.start, t.end, t.step, t.incrementing, (index) -> {
            // Set alias for better readability, this has no effect on the generated code.
            index.setAlias(t.getIndex().getAlias());
            index.setUniqueVarDesc(duplicate ? VariableNames.indexName(t.getIndex().getVarDesc(), id)
                    : t.getIndex().getUniqueVarDesc());
        });
        ScopeStack.popScope(targetScope);
        compilationCtx.touchScope(newScope);
        return newScope;
    }

    public static ForTask constructForScope(Scope targetScope, ForTask t, CompilationContext compilationCtx) {
        // Construct the scope
        ScopeStack.pushScope(targetScope);
        ForTask newScope = Sandwood.forLoop(t.start, t.end, t.step, t.incrementing, (index) -> {
            // Set alias for better readability, this has no effect on the generated code.
            index.setAlias(t.getIndex().getAlias());
            index.setUniqueVarDesc(t.getIndex().getUniqueVarDesc());
        });
        ScopeStack.popScope(targetScope);
        compilationCtx.touchScope(newScope);
        return newScope;
    }

}
