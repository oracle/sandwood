/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.visitors.variableTracking;

import java.util.ArrayList;
import java.util.List;

import org.sandwood.compiler.trees.TreeID;

/**
 * A class to wrap the list of TreeIDs that describe this assignment. The first entry in the list is the location the
 * assignment occurred. Any later entries are for loops that encompass this assignment that have been left and
 * reentered. They appear in the order that they were reentered.
 */
public class AssignmentDesc {
    private final List<TreeID> assignmentPath;

    AssignmentDesc(TreeID assignment) {
        assignmentPath = new ArrayList<>(1);
        assignmentPath.add(assignment);
    }

    AssignmentDesc(AssignmentDesc a, TreeID additionalPathID) {
        assignmentPath = new ArrayList<>(a.assignmentPath.size() + 1);
        assignmentPath.addAll(a.assignmentPath);
        assignmentPath.add(additionalPathID);
    }

    public TreeID assignmentLocation() {
        return assignmentPath.get(0);
    }

    public int pathLength() {
        return assignmentPath.size() - 1;
    }

    @Override
    public int hashCode() {
        return assignmentPath.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if((obj == null) || (getClass() != obj.getClass()))
            return false;
        AssignmentDesc other = (AssignmentDesc) obj;
        return assignmentPath.equals(other.assignmentPath);
    }

    @Override
    public String toString() {
        return assignmentPath.toString();
    }
}
