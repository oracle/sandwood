/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.scopes.ElseScope;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.IfScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope.ScopeType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRProxyTreeSeq;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class ScopeTracking {
    private class ScopeDesc {
        private class SubScopeDesc {
            final ScopeDesc scopeDesc;
            final ArrayList<IRTreeVoid> postScopeTrees = new ArrayList<>();

            public SubScopeDesc(ScopeDesc scopeDesc) {
                this.scopeDesc = scopeDesc;
            }
        }

        private ScopeDesc enclosingScope = null; // Null only if this is the outermost scope
        private final Scope scope;
        private IRTreeVoid scopeTree = null;
        private final IRProxyTreeSeq scopeBody;
        private int taskCount = 0;
        private final List<IRTreeVoid> initialTrees = new ArrayList<>();
        private final LinkedList<SubScopeDesc> subScopes = new LinkedList<>();

        private ScopeDesc(Scope scope, Set<ScopeDesc> constructedScopeDesc) {
            assert !scopes.containsKey(scope);
            this.scope = scope;
            scopeBody = IRTree.proxyTree(Tree.NoComment);
            constructedScopeDesc.add(this);
        }

        /**
         * Method to set the scope description of the scope that encloses this scope.
         * 
         * @param enclosingScope The scope description of the enclosing scope.
         */
        private void setEnclosingScope(ScopeDesc enclosingScope) {
            assert enclosingScope != null;
            assert this.enclosingScope == null;
            this.enclosingScope = enclosingScope;
            enclosingScope.addScope(this);
        }

        /**
         * Method to initialise the scope tree for this scope description. TODO come up with a clean way of getting the
         * else body to the if scope method without passing this object and having methods that expose some of its
         * internal structure (getScopeBodyTree).
         * 
         * @param tracking The tracking object that holds this scope description.
         */
        private void initialiseScopeTree(ScopeTracking tracking) {
            assert scope == GlobalScope.scope || enclosingScope != null;

            scopeTree = scope.getScopeTree(tracking, scopeBody, reverseScopes, compilationCtx);
        }

        /**
         * Method to add a tree to this scope.
         * 
         * @param tree The tree to add.
         */
        public void addTree(IRTreeVoid tree) {
            int pos = firstActiveScope();
            if(pos == 0)
                initialTrees.add(tree);
            else // pos - 1 is the last inactive scope description.
                subScopes.get(pos - 1).postScopeTrees.add(tree);
        }

        public void addTree(IRTreeVoid tree, DataflowTask<?> t, boolean reverseScopes) {
            List<IRTreeVoid> targetTrees = initialTrees;
            int size = subScopes.size();
            int id = getId(t);
            if(reverseScopes) {
                assert false; // This function is wrong, but as it will be removed soon I'm not going to fix it now.
                for(int pos = size - 1; pos >= 0 && subScopes.get(pos).scopeDesc.scope.id() < id; pos--)
                    targetTrees = subScopes.get(pos).postScopeTrees;
            } else {
                for(int pos = 0; pos < size && subScopes.get(pos).scopeDesc.scope.id() < id; pos++)
                    targetTrees = subScopes.get(pos).postScopeTrees;

            }
            targetTrees.add(tree);
        }

        /**
         * A method to find the correct id to search with. Normally this will just be the task id, but if the tree is
         * being placed into a further out scope then the id needs to be adjusted to an id that will appear in the scope
         * descriptor.
         * 
         * @param t
         * @return
         */
        private int getId(DataflowTask<?> t) {
            Set<Scope> seen = new HashSet<>();
            Scope taskScope = t.scope();
            seen.add(taskScope);
            taskScope = compilationCtx.substituteScope(taskScope);

            /*
             * This guard returns the tasks id to order values that are being placed directly into the scope, not into a
             * scope that is embedded in this scope
             */
            if(taskScope == scope)
                return t.id();

            seen.add(taskScope);
            while(taskScope != scope) {
                taskScope = taskScope.getEnclosingScope();
                if(taskScope == scope)
                    break;
                if(!seen.contains(taskScope)) {
                    seen.add(taskScope);
                    taskScope = compilationCtx.substituteScope(taskScope);
                }
            }
            return taskScope.id();
        }

        /**
         * Method to add a new subscope to this scopes tree.
         * 
         * @param scopeDesc The scope description object for the subscope
         */
        private void addScope(ScopeDesc scopeDesc) {
            SubScopeDesc p = new SubScopeDesc(scopeDesc);
            int pos = firstActiveScope();
            subScopes.add(pos, p);
        }

        /**
         * Method to find the index of the first active subscope. Added trees should appear immediately before this when
         * they are added to ensure that their values are calculated and stored before they are used.
         * 
         * @return The index of the first active subscope.
         */
        private int firstActiveScope() {
            int i = 0;
            int size = subScopes.size();
            while(i < size) {
                if(subScopes.get(i).scopeDesc.taskCount != 0)
                    return i;
                else
                    i++;
            }
            return size;
        }

        /**
         * Method to record that this scope is has another active task.
         */
        public void addEnterTask() {
            taskCount++;
            if(enclosingScope != null)
                enclosingScope.addEnterTask();
        }

        /**
         * Method to record that this scope has one less active task.
         */
        public void addLeaveTask() {
            taskCount--;
            if(enclosingScope != null)
                enclosingScope.addLeaveTask();
        }

        /**
         * Method to turn this scope description into a single IR tree holding all the trees added to this scope and its
         * subscopes.
         * 
         * @return The resulting tree.
         */
        private IRTreeVoid toTree() {
            if(scope.getScopeType() == ScopeType.ELSE)
                return IRTree.nop();
            else {
                Map<IRTree, List<IRTree>> m = scopeTree.copy();

                constructBody(m, this);

                if(scope.getScopeType() == ScopeType.IF) {
                    IfScope ifScope = (IfScope) scope;
                    ScopeDesc elseDesc = scopes.get(ifScope.elseScope);
                    if(elseDesc != null)
                        constructBody(m, elseDesc);
                }

                return (IRTreeVoid) m.get(scopeTree).get(0);
            }
        }

        /**
         * Method to merge all the added trees together and add the result into a copy of this scopes scope body object.
         * 
         * @param m A map containing copies of all the elements of the scope tree.
         * @param s The scope description holding the trees to be merged in.
         */
        private void constructBody(Map<IRTree, List<IRTree>> m, ScopeDesc s) {
            List<IRTree> copiedTrees = m.get(s.scopeBody);
            for(IRTree t:copiedTrees) {
                IRProxyTreeSeq b = (IRProxyTreeSeq) t;
                b.addTrees(s.initialTrees);
                for(SubScopeDesc p:s.subScopes) {
                    b.addTree(p.scopeDesc.toTree());
                    b.addTrees(p.postScopeTrees);
                }
            }
        }

        /**
         * Method for adding a comment to a scope.
         * 
         * @param comment The comment to add.
         */
        public void addComment(String comment) {
            scopeTree.postfixComment(comment);
        }
    }

    // A map containing information about the scopes required for any delayed
    // actions
    // such as what to do when we complete the last task in a scope.
    // private final Map<Scope, ScopeDesc> scopeDescriptions = new HashMap<>();

    // The computational context of the current compilation. This is used for
    // constructing the
    // Trees that represent the body of each scope.
    private final CompilationContext compilationCtx;

    // Map of scopes to descriptions that will hold the trees that represent the
    // scope.
    private final Map<Scope, ScopeDesc> scopes = new HashMap<>();

    // Boolean to record if the order of exection should be inverted. this is used when functions are being inverted as
    // part of the inference process.
    private boolean reverseScopes = false;

    // Constructor
    public ScopeTracking(CompilationContext compilationCtx) {
        this.compilationCtx = compilationCtx;
        addScope(GlobalScope.scope);
    }

    /**
     * Method to clear state when moving from the construction of one function to another.
     */
    public void clear() {
        scopes.clear();
        addScope(GlobalScope.scope);
        reverseScopes = false;
    }

    /**
     * Method for adding an IR tree to a scope.
     *
     * @param scope The scope to add the tree to.
     * @param tree  THe tree to add to the scope.
     */
    public void addTreeToScope(Scope scope, IRTreeVoid tree) {
        scope = compilationCtx.substituteScope(scope);
        enterScope(scope);
        ScopeDesc d = scopes.get(scope);
        d.addTree(tree);
        leaveScope(scope);
    }

    /**
     * Method for adding an IR tree to a tasks scope. TODO This is only used as part of the put operations, and once we
     * are not initialising variables anymore can be removed.
     * 
     * @param scope The scope that the tree should be added to.
     * @param tree  The tree to add
     * @param task  The task that the tree must not be in a scope that was created after this task w as created.
     */
    public void addTreeToScope(Scope scope, IRTreeVoid tree, DataflowTask<?> task) {
        scope = compilationCtx.substituteScope(scope);
        enterScope(scope);
        scopes.get(scope).addTree(tree, task, reverseScopes);
        leaveScope(scope);
    }

    /**
     * Method for adding a comment to a scope.
     * 
     * @param scope   The scope to add the comment to.
     * @param comment The comment to add.
     */
    public void addCommentToScope(Scope scope, String comment) {
        scope = compilationCtx.substituteScope(scope);
        enterScope(scope);
        ScopeDesc d = scopes.get(scope);
        d.addComment(comment);
        leaveScope(scope);
    }

    /**
     * A method to touch a scope to ensure it is built in the tracking. This is used for scopes that would not be
     * touched because all the uses of their variable are through synthetic named variables, or to ensure they are
     * placed before any substitutions are installed.
     * 
     * @param scope The scope to touch.
     */
    public void touchScope(Scope scope) {
        scope = compilationCtx.substituteScope(scope);

        if(!scopes.containsKey(scope))
            addScope(scope);
    }

    /**
     * Method that is called every time a task completes the construction of its IR. This allows the number of tasks
     * still working on this scope to be tracked, and events to be triggered when the last one completes.
     *
     * @param currentScope
     */
    public void leaveScope(Scope currentScope) {
        Scope scope = currentScope;
        scope = compilationCtx.substituteScope(scope);
        currentScope = scope;
        scopes.get(currentScope).addLeaveTask();
    }

    /**
     * Method called by tasks when they start constructing their IR. It constructs all the required scopes and sets them
     * up to be linked once the IR has been constructed.
     *
     * @param currentScope
     */
    public void enterScope(Scope currentScope) {
        Scope scope = currentScope;
        scope = compilationCtx.substituteScope(scope);
        currentScope = scope;

        if(!scopes.containsKey(currentScope))
            addScope(currentScope);

        scopes.get(currentScope).addEnterTask();
    }

    /**
     * Method to add a scope into the scopes map and tree.
     *
     * @param scope The scope to add.
     */
    private void addScope(Scope scope) {

        // Insert the scope description before doing anything else to ensure that if the construction of the scope tree
        // triggers its use it won't be constructed again. The reuse of a scope by its own scope tree can occur if a
        // substitute variable is used and that was constructed in a scope that is nested in this one.
        Set<ScopeDesc> constructedScopeDescs = new HashSet<>();
        addScopeDesc(scope, constructedScopeDescs);

        // Once all the scope descriptions are constructed, construct the scope trees.
        for(ScopeDesc scopeDesc:constructedScopeDescs) {
            scopeDesc.addEnterTask();
            scopeDesc.initialiseScopeTree(this);
            scopeDesc.addLeaveTask();
        }
        assert scopes.containsKey(scope);
    }

    /**
     * Method to construct the scope descriptions and any encompassing descriptions which may have not been constructed
     * yet.
     * 
     * @param scope The scope to construct the description for.
     * @return
     */
    private ScopeDesc addScopeDesc(Scope scope, Set<ScopeDesc> constructedScopeDescs) {
        assert !scopes.containsKey(scope);
        ScopeDesc scopeDesc = new ScopeDesc(scope, constructedScopeDescs);
        scopes.put(scope, scopeDesc);

        // If this is not the global scope find the outer scope and insert this scope into it.
        if(scope != GlobalScope.scope) {
            Scope enclosingScope = scope.getEnclosingScope();
            enclosingScope = compilationCtx.substituteScope(enclosingScope);
            ScopeDesc enclosingScopeDesc = scopes.get(enclosingScope);
            if(enclosingScopeDesc == null) {
                assert !scopes.containsKey(enclosingScope);
                enclosingScopeDesc = addScopeDesc(enclosingScope, constructedScopeDescs);
            }
            assert enclosingScopeDesc != null;
            scopeDesc.setEnclosingScope(enclosingScopeDesc);
        }

        // For if and else scopes add the corresponding scope if it is not already present.
        switch(scope.getScopeType()) {
            case BLOCK:
            case COMMENT:
            case FOR:
            case GLOBAL:
            case REDUCE: {
                break;
            }
            case ELSE: {
                IfScope ifScope = ((ElseScope) scope).ifScope;
                if(!scopes.containsKey(ifScope))
                    addScopeDesc(ifScope, constructedScopeDescs);
                break;
            }
            case IF: {
                // Ensures the else scope description is always constructed first.
                ElseScope elseScope = ((IfScope) scope).elseScope;
                if(!scopes.containsKey(elseScope))
                    addScopeDesc(elseScope, constructedScopeDescs);
                break;
            }
            default:
                throw new CompilerException("Unknown type");
        }

        return scopeDesc;
    }

    /**
     * Method to get the overall tree representing the trees that have been added.
     * 
     * @return
     */
    public IRTreeVoid getOutermostScopeTree() {
        return scopes.get(GlobalScope.scope).toTree();
    }

    /**
     * Method to set if the order that the scopes are constructed in should be reversed. This is used to construct some
     * optimisations where the convergence performance can be improved by changing the direction that the DAG is
     * traversed.
     * 
     * @param reverseScopes
     */
    public void setreverseScopes(boolean reverseScopes) {
        this.reverseScopes = reverseScopes;
    }

    @Override
    public String toString() {
        return getOutermostScopeTree().toString();
    }

    public IRTreeVoid getScopeBodyTree(Scope scope) {
        ScopeDesc scopeDesc = scopes.get(scope);
        return scopeDesc.scopeBody;
    }
}