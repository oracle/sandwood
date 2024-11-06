/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.trees.TreeID;
import org.sandwood.compiler.trees.transformationTree.TransConditionalAssignment;
import org.sandwood.compiler.trees.transformationTree.TransFor;
import org.sandwood.compiler.trees.transformationTree.TransIfElse;
import org.sandwood.compiler.trees.transformationTree.TransStore;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.AssignmentDesc;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.ScopedVarSet;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VarDef;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VariableTracking;

public class ScalarReadFrequency {
    /**
     * Tags for the frequency with which each variable instance in the tree is read.
     */
    public enum Frequency {
        NEVER,
        ONCE,
        MULTIPLE
    }

    /**
     * A map recording for each variable write if it is read more than once.
     */
    private final Map<TaggedVariableName, Boolean> multipleReads;

    public ScalarReadFrequency(VariableTracking varTracking) {
        // Calculate which trees are read multiple times.
        multipleReads = new HashMap<>();
        // Populate the multipleReads Map.
        countReads(varTracking);

        // Filter out any trees that are not used for control flow, or writing values out to global state.
        filterTrees(varTracking);
    }

    private void countReads(VariableTracking varTracking) {
        for(TransTree<?> t:varTracking.trackedTrees()) {
            switch(t.type) {
                case LOAD: {
                    ScopedVarSet readVars = varTracking.readVars(t);
                    for(VariableDescription<?> baseDesc:readVars.getVars()) {
                        // Loads always only read from 1 variable definition.
                        Set<TreeID> instances = readVars.getVarDef(baseDesc).writeLocations();
                        for(TreeID tag:instances) {
                            TaggedVariableName instance = new TaggedVariableName(baseDesc.name, tag);
                            // This will put false the first time we encounter the value, and true for all
                            // subsequent encounters.
                            multipleReads.put(instance, multipleReads.containsKey(instance));
                        }
                    }
                    break;
                }
                case FOR: { // Any variables flagged as being used multiple times are marked as such.
                    TransFor tf = (TransFor) t;
                    recordMultipleReads(varTracking.readVars(tf.step));
                    recordMultipleReads(varTracking.readVars(tf.end));
                    recordMultipleReads(varTracking.readVars(tf.body));
                    break;
                }
                default:
                    break;
            }
        }
    }

    private void recordMultipleReads(ScopedVarSet readVars) {
        for(VariableDescription<?> baseDesc:readVars.getVars()) {
            VarDef v = readVars.getVarDef(baseDesc);

            for(TreeID instance:v.writeLocations())
                multipleReads.put(new TaggedVariableName(baseDesc.name, instance), true);
        }
    }

    private void filterTrees(VariableTracking varTracking) {
        // Mapping from id's to trees
        Map<TreeID, TransTree<?>> treeLookup = new HashMap<>();

        // Set of trees that have already been processed
        Set<TreeID> processed = new HashSet<>();

        // Set of stores that are used to update global state
        Set<TreeID> usedStores = new HashSet<>();

        // A stack to hold all the tree ids that should be considered.
        Stack<TreeID> toProcess = new Stack<>();

        // Initialise the data structures.
        for(TransTree<?> t:varTracking.trackedTrees()) {
            Set<TreeID> readIDs = null;
            switch(t.type) {
                case ALLOCATE_ARRAY:
                case ARRAY_GET:
                case ARRAY_PUT:
                    // Functions that are void must be altering global state.
                case LOCAL_FUNCTION_CALL:
                case RV_FUNCTION_CALL: {
                    ScopedVarSet read = varTracking.readVars(t);
                    readIDs = getReadIDs(read);
                    break;
                }
                case CONDITIONAL_ASSIGNMENT: {
                    TransConditionalAssignment<?> ca = (TransConditionalAssignment<?>) t;
                    ScopedVarSet read = varTracking.readVars(ca.condition);
                    readIDs = getReadIDs(read);
                    break;
                }
                case FOR: {
                    treeLookup.put(t.id, t);
                    TransFor tf = (TransFor) t;
                    ScopedVarSet read = varTracking.readVars(tf.start);
                    readIDs = getReadIDs(read);

                    read = varTracking.readVars(tf.end);
                    readIDs.addAll(getReadIDs(read));

                    read = varTracking.readVars(tf.step);
                    readIDs.addAll(getReadIDs(read));
                    break;
                }
                case IF: {
                    TransIfElse ifElse = (TransIfElse) t;
                    ScopedVarSet read = varTracking.readVars(ifElse.condition);
                    readIDs = getReadIDs(read);
                    break;
                }
                case INITIALIZE: {
                    treeLookup.put(t.id, t);
                    break;
                }
                case STORE: {
                    treeLookup.put(t.id, t);
                    TransStore<?> s = (TransStore<?>) t;
                    ScopedVarSet inScope = varTracking.inScopeVars(t);
                    if(inScope.getVarDef(s.varDesc).isGlobal()) {
                        ScopedVarSet read = varTracking.readVars(t);
                        readIDs = getReadIDs(read);
                        usedStores.add(t.id);
                    }
                    break;
                }
                default:
                    break;

            }

            // Add all the ids to process
            if(readIDs != null)
                toProcess.addAll(readIDs);
        }

        // Trace back through all the read values used for control flow to compute the set of needed stores.
        while(!toProcess.isEmpty()) {
            TreeID id = toProcess.pop();
            if(id != TreeID.global && !processed.contains(id)) {
                processed.add(id);
                usedStores.add(id);
                TransTree<?> t = treeLookup.get(id);
                ScopedVarSet read = varTracking.readVars(t);
                toProcess.addAll(getReadIDs(read));
            }
        }

        // Filter out any stores that are not in the used set and set them as never used.
        // The list is use to prevent modifications to the map while it is being iterated on.
        List<TaggedVariableName> toRemove = new ArrayList<>();
        for(TaggedVariableName name:multipleReads.keySet()) {
            if(!usedStores.contains(name.tag))
                toRemove.add(name);
        }
        for(TaggedVariableName name:toRemove)
            multipleReads.remove(name);
    }

    private static Set<TreeID> getReadIDs(ScopedVarSet read) {
        Set<TreeID> readIDs = new HashSet<>();
        for(VarDef v:read.getAllVarDefs()) {
            for(AssignmentDesc a:v.currentAssignments()) {
                readIDs.add(a.assignmentLocation());
            }
        }
        return readIDs;
    }

    /**
     * A method to determine how frequently an assignment is read.
     * 
     * @param name A tagged variable name describing a specific assignment of a variable.
     * @return An enumeration element describing how frequently this variable is read.
     */
    public Frequency frequency(TaggedVariableName name) {
        Boolean b = multipleReads.get(name);
        if(b == null)
            return Frequency.NEVER;
        else if(b)
            return Frequency.MULTIPLE;
        else
            return Frequency.ONCE;
    }
}
