/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.scopesState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sandwood.compiler.dataflowGraph.variables.Variable;

public abstract class InitializedDescs {
    protected final InitializedDescs parent;
    private final List<InitializedDescs> children = new ArrayList<>();
    protected final Map<Variable<?>, VersionId> versionCount = new HashMap<>();

    InitializedDescs(InitializedDescs parent) {
        this.parent = parent;
        if(parent != null) {
            parent.children.add(this);
            versionCount.putAll(parent.versionCount);
        }
    }

    protected void updateVersionCount(Variable<?> v, int vc) {
        VersionId id = new VersionId(vc, vc);
        versionCount.put(v, id);
        for(InitializedDescs c:children)
            c.updateChild(v, id);
        if(parent != null) {
            parent.updateParent(v, id);
        }
    }

    /**
     * Method to propagate back to parents the new max value, but not update the used value. 0 is the same as unused.
     * 
     * @param v
     * @param id
     */
    protected void updateParent(Variable<?> v, VersionId id) {
        VersionId current = versionCount.get(v);
        if(current == null) {
            if(id.usedId() != 0)
                id = new VersionId(0, id.maxId());
        } else {
            if(current.maxId() < id.maxId())
                id = new VersionId(current.usedId(), id.maxId());
            else
                return;
        }
        versionCount.put(v, id);
        if(parent != null)
            parent.updateParent(v, id);
    }

    private void updateChild(Variable<?> v, VersionId id) {
        versionCount.put(v, id);
        for(InitializedDescs c:children)
            c.updateChild(v, id);
    }

    abstract <A extends Variable<A>> Variable<A> getInitializedVariable(Variable<A> v);

    abstract boolean isInitialized(Variable<?> v);

    abstract <A extends Variable<A>> Variable<A> addInitialized(Variable<A> v);
}
