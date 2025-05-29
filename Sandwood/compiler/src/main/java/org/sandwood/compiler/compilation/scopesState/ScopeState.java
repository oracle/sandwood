/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.scopesState;

import java.util.Set;
import java.util.Stack;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.scopesState.ScopeTracking.ScopeTrackingState;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class ScopeState {
    public class InitializedState {
        private final InitializedDescs i;
        
        private InitializedState(InitializedDescs i) {
            this.i = i;
        }
        
        public InitializedState addChangeableScope(Scope changeableScope) {
            InitializedDescs d = new InitialisedDescsSingle(i, changeableScope);
            return new InitializedState(d);
        }

        public InitializedState mergeInExisitingInitilizations(InitializedState toMergeState, Scope outerExisiting) {
            InitializedDescs d = new InitialisedDescsMerged(i, toMergeState.i, outerExisiting);
            return new InitializedState(d);
        }
    }
    
    public class ScopeStateDesc {
        private final ScopeTracking scopes;
        private final InitializedState initialised;
        
        ScopeStateDesc(CompilationContext compilationCtx) {
            scopes = new ScopeTracking(compilationCtx);
            initialised = new InitializedState(new InitialisedDescsSingle());
        }
        
        ScopeStateDesc (ScopeTracking scopes, InitializedState initialised) {
            this.scopes = scopes;
            this.initialised = initialised;
        }

        public Scope getTargetScope(Scope scope) {
            return scopes.getTargetScope(scope);
        }
    }
    
    private final Stack<ScopeStateDesc> states;
    private final CompilationContext compilationCtx;

    public ScopeState(CompilationContext compilationCtx) {
        this.compilationCtx = compilationCtx;
        states = new Stack<>();
        states.push(new ScopeStateDesc(compilationCtx));
    }

    public <A extends Variable<A>> Variable<A> getInitializedVariable(Variable<A> v) {
        return states.peek().initialised.i.getInitializedVariable(v);
    }

    public boolean initializedInScope(Variable<?> v) {
        return states.peek().initialised.i.isInitialized(v);
    }

    public <A extends Variable<A>> Variable<A> addInitialized(Variable<A> v) {
        return states.peek().initialised.i.addInitialized(v);
    }    /**
     * Method for adding trees to the scope described by the dataflow task.
    *
    * @param scope
    * @param tree
    */
   public void addTreeToScope(Scope scope, IRTreeVoid tree) {
       states.peek().scopes.addTreeToScope(scope, tree);
   }

   /**
    * Method for adding a comment to a scope
    * 
    * @param scope
    * @param comment
    */
   public void addCommentToScope(Scope scope, String comment) {
       states.peek().scopes.addCommentToScope(scope, comment);
   }

   public IRTreeVoid getOutermostScopeTree() {
       return states.peek().scopes.getOutermostScopeTree();
   }

   public void enterScope(Scope s) {
       states.peek().scopes.enterScope(s);
   }

   public void leaveScope(Scope s) {
       states.peek().scopes.leaveScope(s);
   }

   public void setreverseScopes(boolean reverseScopes) {
       states.peek().scopes.setreverseScopes(reverseScopes);
   }

   /**
    * A method to touch a scope to ensure it is built in the tracking. This is used for scopes that would not be
    * touched because all the uses of their variable are through synthetic named variables, or to ensure they are
    * placed before any substitutions are installed.
    * 
    * @param s The scope to touch.
    */
   public void touchScope(Scope s) {
       states.peek().scopes.touchScope(s);
   }

    public void clear() {
        states.clear();
        states.push(new ScopeStateDesc(compilationCtx));
    }
    
    public void push() {
        states.push(new ScopeStateDesc(compilationCtx));
    }
    
    public ScopeStateDesc pop() {
        return states.pop();
    }

    public ScopeTrackingState getState(Scope scope) {
        return states.peek().scopes.getState(scope);
        
    }
    
    public Scope getTargetScope(Scope scope) {
        return states.peek().getTargetScope(scope);
    }

    public void push(Set<Scope> taskScopes, ScopeTrackingState scopeTrackingState, InitializedState initialised, CompilationContext compilationCtx) {
        ScopeTracking st = new ScopeTracking(taskScopes, scopeTrackingState, compilationCtx);
        states.push(new ScopeStateDesc(st, initialised));
    }

    public InitializedState getInitializedState() {
        return states.peek().initialised;
    }

    public boolean scopeConstructed(Scope scope) {
        return states.peek().scopes.contiansScope(scope);
    }
}
