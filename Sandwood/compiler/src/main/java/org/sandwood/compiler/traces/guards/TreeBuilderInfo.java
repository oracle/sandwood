/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
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
    public final CompilationContext compilationCtx;
    private final ScopeDescription d;
    private final List<DataflowTask<?>> tasks;
    private int position;
    private DataflowTask<?> task;

    TreeBuilderInfo(ScopeDescription d, List<DataflowTask<?>> tasks, CompilationContext compilationCtx) {
        probability = d.probability;
        this.d = d;
        this.tasks = tasks;
        backTraceInfo = new BackTraceInfo(this);
        this.compilationCtx = compilationCtx;
    }

    public void applySubstitutions(int position) {
        applySubstitutions(position, tasks.get(position));
    }

    private void applySubstitutions(int position, DataflowTask<?> task) {
        this.position = position;
        this.task = task;
        d.applySubstitutions(position, task, compilationCtx);
    }

    public TraceHandle getTrace() {
        return d.getTrace(position);
    }

    public void removeSubstitutions() {
        d.removeSubstitutions(position, task, compilationCtx);
    }

    public void changeSubstitutions(int position, DataflowTask<?> task) {
        removeSubstitutions();
        applySubstitutions(position, task);
    }

    public void changeSubstitutions(int position) {
        changeSubstitutions(position, tasks.get(position));
    }

    public void changeSubstitutions(DataflowTask<?> task) {
        changeSubstitutions(position, task);
    }
}
