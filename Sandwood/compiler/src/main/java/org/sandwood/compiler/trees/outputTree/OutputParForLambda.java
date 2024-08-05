/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.trees.Tree;

public class OutputParForLambda extends OutputTree {

    private final int parDepth;
    private final VariableDescription<IntVariable> startDesc, endDesc;
    private final OutputTree body;
    private final VariableDescription<IntVariable> threadID;

    public OutputParForLambda(int parDepth, VariableDescription<IntVariable> startDesc,
            VariableDescription<IntVariable> endDesc, VariableDescription<IntVariable> threadID, OutputTree body) {
        super(OutputTreeType.PAR_FOR_LAMBDA, false, Tree.NoComment);
        this.parDepth = parDepth;
        this.startDesc = startDesc;
        this.endDesc = endDesc;
        this.body = body;
        this.threadID = threadID;

    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        sb.append("(int " + startDesc + ", int " + endDesc + ", int " + threadID + ", org.sandwood.random.internal.Rng "
                + VariableNames.rngName(parDepth) + ") -> { \n");
        addIndent(sb, indent + 1);
        body.toJava(sb, indent + 2, requiredImports);
        addIndent(sb, indent);
        sb.append("}\n");
    }

    @Override
    public OutputTree[] getChildren() {
        return new OutputTree[] { body };
    }

    @Override
    public String getDescription() {
        return "Parallel for Lambda:" + startDesc + ", " + endDesc;
    }

    @Override
    protected OutputTree copy(Map<OutputTree, OutputTree> results) {
        OutputTree b2 = body.copy(results);
        OutputParForLambda p2 = new OutputParForLambda(parDepth, startDesc, endDesc, threadID, b2);
        results.put(this, p2);
        return p2;
    }

}
