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

import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.names.VariableNames;

public class OutputParFor extends OutputTree {

    private final int parDepth;
    private final OutputTreeReturn<IntVariable> start, end, step;
    private final OutputTree body;

    public OutputParFor(int parDepth, OutputTreeReturn<IntVariable> start, OutputTreeReturn<IntVariable> end,
            OutputTreeReturn<IntVariable> step, OutputTree body, String comment) {
        super(OutputTreeType.FORK_JOIN_FOR, true, comment);
        this.parDepth = parDepth;
        this.start = start;
        this.end = end;
        this.step = step;
        this.body = body;
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        sb.append("parallelFor(" + VariableNames.rngName(parDepth) + ", ");
        start.toJava(sb, indent, requiredImports);
        sb.append(", ");
        end.toJava(sb, indent, requiredImports);
        sb.append(", ");
        step.toJava(sb, indent, requiredImports);
        sb.append(",\n");
        addIndent(sb, indent + 1);
        body.toJava(sb, indent + 1, requiredImports);
        addIndent(sb, indent);
        sb.append(");\n");
    }

    @Override
    public OutputTree[] getChildren() {
        return new OutputTree[] { start, end, step, body };
    }

    @Override
    public String getDescription() {
        return "parfor(start, end, step): body";
    }

    @Override
    protected OutputTree copy(Map<OutputTree, OutputTree> results) {
        OutputTreeReturn<IntVariable> start2 = start.copy(results);
        OutputTreeReturn<IntVariable> end2 = end.copy(results);
        OutputTreeReturn<IntVariable> step2 = step.copy(results);
        OutputTree body2 = body.copy(results);
        OutputParFor copy = new OutputParFor(parDepth, start2, end2, step2, body2, comment);
        results.put(this, copy);
        return copy;
    }
}
