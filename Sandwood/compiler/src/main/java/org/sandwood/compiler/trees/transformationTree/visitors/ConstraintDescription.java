/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.visitors;

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.ScopedVarSet;

public class ConstraintDescription implements Comparable<ConstraintDescription> {
    public final ScopedVarSet requiredScope;
    public final TransTreeReturn<BooleanVariable> constraint;
    /**
     * Can this guard be merged with other instances of the guard? If false the tree it guards could change the value of
     * the guard.
     */
    public final boolean stableGuard;

    public ConstraintDescription(ScopedVarSet requiredScope, boolean stableGuard,
            TransTreeReturn<BooleanVariable> constraint) {
        this.requiredScope = requiredScope;
        this.constraint = constraint;
        this.stableGuard = stableGuard;
    }

    @Override
    public int compareTo(ConstraintDescription o) {
        return constraint.id.tag - o.constraint.id.tag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ConstraintDescription(" + constraint.toString());
        sb.append(", stable " + stableGuard);
        for(VariableDescription<?> s:requiredScope.getVars()) {
            sb.append(", " + s + "(" + requiredScope.getVarDef(s) + ")");
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = stableGuard ? 0 : 1;
        result = prime * result + constraint.equivalentHashCode();
        result = prime * result + requiredScope.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if((obj == null) || (getClass() != obj.getClass()))
            return false;
        ConstraintDescription other = (ConstraintDescription) obj;
        if(stableGuard != other.stableGuard)
            return false;
        if(!constraint.equivalent(other.constraint))
            return false;
        return requiredScope.equals(other.requiredScope);
    }
}
