/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.traces.guards;

import java.util.Stack;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.PutTask;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class BackTraceInfo {
    private final Stack<IRTreeReturn<?>> getValues = new Stack<>();
    private final TreeBuilderInfo treeBuilderInfo;

    public BackTraceInfo() {
        treeBuilderInfo = null;
    }

    public BackTraceInfo(TreeBuilderInfo treeBuilderInfo) {
        this.treeBuilderInfo = treeBuilderInfo;
    }

    public void addGetValue(IRTreeReturn<?> value) {
        getValues.push(value);
    }

    public IRTreeReturn<?> getGetValue() {
        return getValues.pop();
    }

    public int noGetValues() {
        return getValues.size();
    }

    public void updateSubstitutions(PutTask<?> putTask, CompilationContext compilationCtx) {
        if(treeBuilderInfo != null)
            treeBuilderInfo.changeSubstitutions(putTask, compilationCtx);
    }

    public boolean traceConstructedCorrectly() {
        return getValues.isEmpty();
    }

    public void clearGets() {
        getValues.clear();
    }
}
