/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators;

import static org.sandwood.compiler.trees.irTree.IRTree.parallelForStmt;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.ScopeTracking;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class ParForTask extends ForTask {

    public ParForTask(IntVariable start, IntVariable end, IntVariable step, boolean incrementing) {
        super(DFType.PAR_FOR, start, end, step, incrementing, null);
    }

    public ParForTask(IntVariable start, IntVariable end, IntVariable step, boolean incrementing, Location location) {
        super(DFType.PAR_FOR, start, end, step, incrementing, location);
    }

    @Override
    public String getDescription() {
        return "ParFor " + id();
    }

    @Override
    public String toString() {
        return "parfor(" + getIndex().getVarDesc() + ") id " + this.id();
    }

    @Override
    public IRTreeVoid getScopeTree(ScopeTracking scopeTracking, IRTreeVoid tree, boolean reverseScopes,
            CompilationContext compilationCtx) {
        if(isSerial(compilationCtx))
            return super.getScopeTree(scopeTracking, tree, reverseScopes, compilationCtx);
        else {
            IRTreeReturn<IntVariable> startTree = start.getForwardIR(compilationCtx);
            IRTreeReturn<IntVariable> endTree = end.getForwardIR(compilationCtx);
            IRTreeReturn<IntVariable> stepTree = step.getForwardIR(compilationCtx);
            VariableDescription<IntVariable> indexDesc = index.getUniqueVarDesc();
            if(reverseScopes) {
                startTree = updateStart(startTree, incrementing);
                endTree = updateEnd(startTree, endTree, stepTree, incrementing);
                return parallelForStmt(tree, endTree, startTree, stepTree, indexDesc, !incrementing, Tree.NoComment);
            } else
                return parallelForStmt(tree, startTree, endTree, stepTree, indexDesc, incrementing, Tree.NoComment);
        }
    }

    @Override
    public boolean isSerial(CompilationContext compilationCtx) {
        return compilationCtx.serialScope(this) || compilationCtx.isSerial();
    }
}
