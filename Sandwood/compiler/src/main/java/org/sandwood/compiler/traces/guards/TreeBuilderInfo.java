/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.traces.guards;

import java.util.List;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.traces.TraceHandle;
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
    }

    public TraceHandle getTrace() {
        return d.getTrace(position);
    }

    public void removeSubstitutions(CompilationContext compilationCtx) {
        d.removeSubstitutions(position, task, compilationCtx);
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
