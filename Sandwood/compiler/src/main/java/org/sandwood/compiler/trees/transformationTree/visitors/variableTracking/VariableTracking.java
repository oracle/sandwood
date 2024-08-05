/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.visitors.variableTracking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.TreeID;
import org.sandwood.compiler.trees.transformationTree.TransLoad;
import org.sandwood.compiler.trees.transformationTree.TransStore;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

/**
 * A class to record where in a transformation tree each variable is read and written.
 * 
 * @author djgoodma
 *
 */
public class VariableTracking {
    /**
     * A map storing the set of in scope variables, their declaration location and the locations they were set from for
     * each tree encountered.
     */
    private final Map<TransTree<?>, ScopedVarSet> inScopeVars;

    /**
     * A map detailing for each tree the variables that are read, and the possible locations the read values are set at.
     * This set of possible locations will need to be maintained in any transformations of the tree.
     */
    private final Map<TransTree<?>, ScopedVarSet> readVars;

    /**
     * A map from trees to the set of variables that this tree may modify. There is no need to include additional
     * variable tags as the location is inside the tree identified by the tree id.
     */
    private final Map<TransTree<?>, Set<VariableDescription<?>>> writtenVars;

    /**
     * A map from trees to sets containing the variable names that reference arrays that may have changed during the
     * execution of the provided tree.
     */
    private final Map<TransTree<?>, Set<VariableDescription<?>>> arraysModified;

    /**
     * A map from names to a set of TreeIDs to record if the value is effectively final. This is only recording local
     * variables as global variables do not need to be effectively final for a lambda.
     */
    protected final Map<VariableDescription<?>, Set<TreeID>> effectivelyFinal = new HashMap<>();

    public VariableTracking() {
        inScopeVars = new HashMap<>();
        readVars = new HashMap<>();
        writtenVars = new HashMap<>();
        arraysModified = new HashMap<>();
    }

    /**
     * Method to get the set of variables that are in scope when starting to execute a given tree.
     * 
     * @param tree The tree we want to determine the variable writes that are in scope at the start of execution for.
     * @return The tags of all the variable writes that are in scope for this tree.
     */
    public ScopedVarSet inScopeVars(TransTree<?> tree) {
        return inScopeVars.get(tree);
    }

    /**
     * Method to get a map from variables read by a tree to the assignments to those variables.
     * 
     * @param tree The tree we want the read variables for.
     * @return A map of the variable names and assignments.
     */
    public ScopedVarSet readVars(TransTree<?> tree) {
        return readVars.get(tree);
    }

    /**
     * A method to determine if a given variable name is ever read within a tree.
     * 
     * @param tree The tree that needs to be examined.
     * @param name The name of the variable that we want reads for.
     * @return Was this variable read?
     */
    public boolean read(TransTree<?> tree, VariableDescription<?> name) {
        return readVars.get(tree).containsVar(name);
    }

    /**
     * Method to determine which variables a tree may write to.
     * 
     * @param tree The tree we want to query.
     * @return A set of variable names that may be written to by this tree.
     */
    public Set<VariableDescription<?>> written(TransTree<?> tree) {
        return writtenVars.get(tree);
    }

    /**
     * Method to determine which referenced arrays may be modified.
     * 
     * @param tree The tree we want to query.
     * @return A set of variable names that reference arrays that may be modified by this tree.
     */
    public Set<VariableDescription<?>> arraysModified(TransTree<?> tree) {
        return arraysModified.get(tree);
    }

    /**
     * Method to determine if a variable set by a given tree is declared globally or locally.
     * 
     * @param tree The tree setting the Variable.
     * @return Is the variable it is setting globally visible?
     */
    public boolean isGlobal(TransStore<?> tree) {
        return inScopeVars.get(tree).getVarDef(tree.varDesc).isGlobal();
    }

    /**
     * A method to add a new return tree based on an existing one. This should be used when new values are constructed
     * during a transformation. As this is a return tree, no new values can be created.
     * 
     * @param baseTree The tree locating where this new tree is being placed.
     * @param newTree  The new tree in the location of the old tree.
     */
    public void addTree(TransTree<?> baseTree, TransTreeReturn<?> newTree) {
        assert (inScopeVars.containsKey(baseTree));
        new TreeInsertionVisitor(inScopeVars.get(baseTree)).visit(newTree);
    }

    /**
     * Method to get the set of all trees with information stored in this object.
     * 
     * @return A set of all the trees that have information stored in this object.
     */
    public Set<TransTree<?>> trackedTrees() {
        return readVars.keySet();
    }

    public boolean effectivelyFinal(VariableDescription<?> desc, TreeID declaration) {
        assert (declaration != TreeID.global);
        Set<TreeID> initialisations = effectivelyFinal.get(desc);
        if(initialisations == null)
            return false;
        return initialisations.contains(declaration);
    }

    /**
     * Method to add a tree to the store.
     * 
     * @param tree             The tree to add.
     * @param inScope          A map of the variables that are in scope for the tree.
     * @param readVariables    A map of the variables that are read by the tree, and the frequency of the reads.
     * @param writtenVariables The set of variables written by the tree.
     */
    protected void addTree(TransTree<?> tree, ScopedVarSet inScope, ScopedVarSet readVariables,
            Set<VariableDescription<?>> writtenVariables, Set<VariableDescription<?>> arraysChanged) {
        inScopeVars.put(tree, inScope);
        readVars.put(tree, readVariables);
        writtenVars.put(tree, writtenVariables);
        arraysModified.put(tree, arraysChanged);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("inScopeVars " + inScopeVars + "\n");
        sb.append("readVars " + readVars + "\n");
        sb.append("writtenVars " + writtenVars + "\n");
        sb.append("arraysModified " + arraysModified + "\n");
        return sb.toString();
    }

    private class TreeInsertionVisitor implements TreeVisitor {

        private class UpdatableInScopeVars extends ScopedVarSet {
            private boolean seen = false;

            /**
             * A method that is allowed to modify the map, but can only be called before the state of the object is
             * queried.
             * 
             * @param name
             * @param varDef
             */
            private void setVarDef(VariableDescription<?> desc, VarDef varDef) {
                if(seen)
                    throw new CompilerException(
                            "Trying to update a scoped variable set after the set has been observed.");
                if(varDef == null)
                    throw new CompilerException("Trying to store a null variable definition for variable " + desc);
                scopeVarSet.put(desc, varDef);
            }

            private void setAll(ScopedVarSet toAdd) {
                scopeVarSet.putAll(toAdd.scopeVarSet);
            }

            @Override
            public VarDef getVarDef(VariableDescription<?> desc) {
                seen = true;
                return super.getVarDef(desc);
            }

            @Override
            public Set<VariableDescription<?>> getVars() {
                seen = true;
                return super.getVars();
            }

            @Override
            public int size() {
                seen = true;
                return super.size();
            }
        }

        private UpdatableInScopeVars treeReadVars = new UpdatableInScopeVars();
        // No new variables can be declared in a return tree, so we will keep the same
        // in scope vars.
        private final ScopedVarSet treeInScopeVars;

        public TreeInsertionVisitor(ScopedVarSet treeInScopeVars) {
            assert treeInScopeVars != null;
            this.treeInScopeVars = treeInScopeVars;
        }

        @Override
        public void visit(TransTree<?> tree) {
            // If this tree has already been saved, just store the result.
            if(readVars.containsKey(tree)) {
                treeReadVars.setAll(readVars.get(tree));
            } else { // Otherwise traverse it to calculate the result.
                UpdatableInScopeVars tempReadVars = treeReadVars;
                treeReadVars = new UpdatableInScopeVars();
                switch(tree.type) {
                    case LOAD: {
                        TransLoad<?> load = (TransLoad<?>) tree;
                        VariableDescription<?> desc = load.varDesc;
                        treeReadVars.setVarDef(desc, treeInScopeVars.getVarDef(desc));
                        break;
                    }
                    default:
                        tree.traverseTree(this);
                }

                // Save tree
                addTree(tree, treeInScopeVars, treeReadVars, new HashSet<>(), new HashSet<>());

                // Update the read variables for surrounding trees. This will not
                // propagate the reads to sub trees. TODO fix this.
                tempReadVars.setAll(treeReadVars);
                treeReadVars = tempReadVars;
            }
        }
    }
}
