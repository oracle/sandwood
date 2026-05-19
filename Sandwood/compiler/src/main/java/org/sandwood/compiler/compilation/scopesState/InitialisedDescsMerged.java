/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.scopesState;

import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.variables.Variable;

class InitialisedDescsMerged extends InitialisedDescsSingle {
    private final InitializedDescs merged;
    // Values initialised in merge in the merge scope or below will be returned, but not if they are in outer scopes.
    private final Scope mergeScope;

    InitialisedDescsMerged(InitializedDescs parent, InitializedDescs merged, Scope mergeScope) {
        super(parent, mergeScope);
        this.merged = merged;
        this.mergeScope = mergeScope;
    }

    @Override
    public <A extends Variable<A>> Variable<A> getInitializedVariable(Variable<A> v) {
        if(super.isInitialized(v))
            return super.getInitializedVariable(v);
        else
            return merged.getInitializedVariable(v);
    }

    @Override
    public boolean isInitialized(Variable<?> v) {
        if(super.isInitialized(v))
            return true;

        // Test if the scope is a sub scope of merged scope. Sub scopes are not included in the merge.
        Scope s = v.scope();
        if(s != mergeScope) {
            while(s != mergeScope && s != GlobalScope.scope)
                s = s.getEnclosingScope();
            if(s == mergeScope)
                return false;
        }

        return merged.isInitialized(v);
    }

    @Override
    protected void updateParent(Variable<?> v, VersionId id) {
        super.updateParent(v, id);
        merged.updateParent(v, id);
    }
}
