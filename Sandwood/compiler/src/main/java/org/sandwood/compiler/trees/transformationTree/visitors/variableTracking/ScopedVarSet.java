/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.visitors.variableTracking;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;

public class ScopedVarSet {
    protected final Map<VariableDescription<?>, VarDef> scopeVarSet = new HashMap<>();

    public ScopedVarSet() {}

    public ScopedVarSet(ScopedVarSet s) {
        scopeVarSet.putAll(s.scopeVarSet);
    }

    public ScopedVarSet(Map<VariableDescription<?>, VarDef> m) {
        scopeVarSet.putAll(m);
    }

    public boolean containsVar(VariableDescription<?> desc) {
        return scopeVarSet.containsKey(desc);
    }

    public VarDef getVarDef(VariableDescription<?> desc) {
        return scopeVarSet.get(desc);
    }

    public Set<VariableDescription<?>> getVars() {
        return scopeVarSet.keySet();
    }

    public Collection<VarDef> getAllVarDefs() {
        return scopeVarSet.values();
    }

    public int size() {
        return scopeVarSet.size();
    }

    @Override
    public int hashCode() {
        return scopeVarSet.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if((obj == null) || (getClass() != obj.getClass()))
            return false;
        ScopedVarSet other = (ScopedVarSet) obj;
        return scopeVarSet.equals(other.scopeVarSet);
    }
}
