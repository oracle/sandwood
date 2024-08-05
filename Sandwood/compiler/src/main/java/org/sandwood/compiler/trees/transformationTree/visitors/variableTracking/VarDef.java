/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.visitors.variableTracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.TreeID;

/**
 * A helper class to record the tags that a variable at a specific point could have received values from, and the point
 * in the tree where the variable was declared.
 */
public class VarDef {
    /**
     * A flag to mark if this VarDef is part of a chain of VarDefs that consume updates passed from a parent
     */
    private final boolean isListener;

    /**
     * The locations that the variable described here could have got its values from. For each assignment a list of tags
     * is provided describing the assignment and the for loops that have to be entered after the assignment to reach
     * this location.
     */
    private final Set<AssignmentDesc> currentAssignments = new HashSet<>();
    /**
     * The location this variable was declared at. For the current use this could be replaced with a boolean to record
     * if the variable was global or not.
     */
    public final TreeID declarationId;
    /**
     * A list of VarDefs that need to be updated every time a new definition is added to this VarDef. These chains of
     * variable definitions is the core of this method.
     */
    private final List<VarDef> listeners = new ArrayList<>();

    /**
     * Construct a variable definition taking a set of locations that it is possibly set from, and a location it is
     * declared at.
     * 
     * @param v The description to base this on.
     */
    private VarDef(VarDef v, boolean dependent) {
        this.isListener = dependent;
        declarationId = v.declarationId;
        currentAssignments.addAll(v.currentAssignments);
    }

    /**
     * Construct a variable definition that only consists of the declaration of the variable.
     * 
     * @param id The location that this variable is both declared.
     */
    public VarDef(TreeID id) {
        isListener = false;
        declarationId = id;
    }

    /**
     * Construct a variable definition for a variable that was declared in one location and whose only possible value
     * was set at a possibly different location.
     * 
     * @param declarationId The location the variable was declared at.
     * @param id            The location the current value of the variable was set at.
     */
    public VarDef(TreeID declarationId, TreeID id) {
        isListener = false;
        this.declarationId = declarationId;
        currentAssignments.add(new AssignmentDesc(id));
    }

    private VarDef(VarDef a, VarDef b, boolean includeB) {
        this(a, a.isListener || (b.isListener && includeB));
        if(a.declarationId != b.declarationId)
            throw new CompilerException("incorrect declaration ids.");
        // This works because if a and b are dependent, they are dependent to the same
        // source.
        if(a.isListener)
            a.listeners.add(this);
        else if(b.isListener && includeB)
            b.listeners.add(this);
        currentAssignments.addAll(b.currentAssignments);
    }

    /**
     * Method to merge a variable definition into this variable definition.
     * 
     * @param b        The VarDef to merge in.
     * @param includeB Flag to mark if b should be included as a dependency.
     */
    public VarDef merge(VarDef b, boolean includeB) {
        return new VarDef(this, b, includeB);
    }

    /**
     * Method to merge a variable definition into this variable definition.
     * 
     * @param v The VarDef to merge in.
     * @param t The tag of the tree the variable definition comes from.
     */
    public void updateDependents(VarDef v, TreeID t) {
        if(!listeners.isEmpty()) {
            assert (declarationId == v.declarationId);
            Set<AssignmentDesc> newIds = new HashSet<>();
            for(AssignmentDesc a:v.currentAssignments) {
                // If this is an assignment that happened since this
                // VarDef was created, add the tree it has come via
                // as a tag and place it in the set to pass on.
                if(!currentAssignments.contains(a)) {
                    newIds.add(new AssignmentDesc(a, t));
                }
            }

            for(VarDef vd:listeners)
                vd.updateDependentsInternal(newIds);
        }
    }

    /**
     * Merge into a VarDef the values in another variable definition.
     * 
     * @param newIds The variable id values that should be merged in.
     */
    private void updateDependentsInternal(Set<AssignmentDesc> newIds) {
        currentAssignments.addAll(newIds);
        for(VarDef vd:listeners)
            vd.updateDependentsInternal(newIds);
    }

    public VarDef copy() {
        VarDef v = new VarDef(this, isListener);
        if(isListener)
            listeners.add(v);
        return v;
    }

    public VarDef getDependent() {
        VarDef v = new VarDef(this, true);
        listeners.add(v);
        return v;
    }

    public Set<TreeID> writeLocations() {
        Set<TreeID> s = new HashSet<>();
        for(AssignmentDesc a:currentAssignments)
            s.add(a.assignmentLocation());
        return s;
    }

    public Set<AssignmentDesc> currentAssignments() {
        return currentAssignments;
    }

    @Override
    public String toString() {
        return "Declaration ID: " + declarationId + " Current IDs: " + currentAssignments;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = currentAssignments.hashCode();
        result = prime * result + declarationId.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if((obj == null) || (getClass() != obj.getClass()))
            return false;
        VarDef other = (VarDef) obj;
        if(!currentAssignments.equals(other.currentAssignments))
            return false;
        return declarationId.equals(other.declarationId);
    }

    public boolean isGlobal() {
        return declarationId.isGlobal();
    }

    public boolean containsInstances(VarDef varDef) {
        return currentAssignments.containsAll(varDef.currentAssignments);
    }

    /**
     * A method to return the number of possible assignments to the declared value.
     * 
     * @return The number of possible assignments.
     */
    public int noPossibleAssignments() {
        return currentAssignments.size();
    }

    public boolean isDependent(VarDef v) {
        for(VarDef dependent:listeners)
            if(dependent == v)
                return true;
        return false;
    }
}
