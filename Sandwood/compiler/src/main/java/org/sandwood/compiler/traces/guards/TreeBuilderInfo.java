/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.traces.guards;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class TreeBuilderInfo {
    public final IRTreeReturn<DoubleVariable> probability;
    public final BackTraceInfo backTraceInfo;
    private final ScopeDescription d;
    private final List<DataflowTask<?>> tasks;
    private int position;
    private DataflowTask<?> task;

    TreeBuilderInfo(ScopeDescription d, List<DataflowTask<?>> tasks) {
        probability = d.probability;
        this.d = d;
        this.tasks = tasks;
        backTraceInfo = new BackTraceInfo(this);
    }

    public void applySubstitutions(int position, CompilationContext compilationCtx) {
        applySubstitutions(position, tasks.get(position), compilationCtx);
    }

    private void applySubstitutions(int position, DataflowTask<?> task, CompilationContext compilationCtx) {
        this.position = position;
        this.task = task;
        d.applySubstitutions(position, task, compilationCtx);
        // Set all base and additional scopes to point to global, the provided scopes
        // will already have had this done.
        for(Scope scope:getScopes(task))
            compilationCtx.addScopeSubstitute(scope, GlobalScope.scope);
    }

    private Set<Scope> getScopes(DataflowTask<?> task) {
        Set<Scope> scopes = new HashSet<>();
        Scope s = task.scope();
        while(s != null) {
            scopes.add(s);
            s = s.getEnclosingScope();
        }
        return scopes;
    }

    public void removeSubstitutions(CompilationContext compilationCtx) {
        d.removeSubstitutions(position, task, compilationCtx);
        for(Scope scope:getScopes(task))
            compilationCtx.removeScopeSubstitute(scope);
    }

    public void changeSubstitutions(int position, DataflowTask<?> task, CompilationContext compilationCtx) {
        removeSubstitutions(compilationCtx);
        applySubstitutions(position, task, compilationCtx);
    }

    public void changeSubstitutions(int position, CompilationContext compilationCtx) {
        changeSubstitutions(position, tasks.get(position), compilationCtx);
    }

    public void changeSubstitutions(DataflowTask<?> task, CompilationContext compilationCtx) {
        changeSubstitutions(position, task, compilationCtx);
    }
}
