/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.traces.guards;

import org.sandwood.compiler.dataflowGraph.Sandwood;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ForTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.names.VariableNames;

class ScopeUtils {
    public static ForTask constructForScope(Scope targetScope, ForTask t, boolean duplicate, String id) {
        // Construct the scope
        ScopeStack.pushScope(targetScope);
        ForTask newScope = Sandwood.forLoop(t.start, t.end, t.step, t.incrementing, (index) -> {
            // Set alias for better readability, this has no effect on the generated code.
            index.setAlias(t.getIndex().getAlias());
            index.setUniqueVarDesc(duplicate ? VariableNames.indexName(t.getIndex().getVarDesc(), id)
                    : t.getIndex().getUniqueVarDesc());
        });
        ScopeStack.popScope(targetScope);
        return newScope;
    }

    public static ForTask constructForScope(Scope targetScope, IntVariable start, IntVariable end, IntVariable step,
            VariableDescription<IntVariable> indexName, boolean incrementing) {
        // Construct the scope
        ScopeStack.pushScope(targetScope);
        ForTask newScope = Sandwood.forLoop(start, end, step, incrementing, (index) -> {
            // Set alias for better readability, this has no effect on the generated code.
            index.setAlias(indexName);
            index.setUniqueVarDesc(indexName);
        });
        ScopeStack.popScope(targetScope);
        return newScope;
    }
}
