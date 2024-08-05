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

public class OutputFor extends OutputTree {

    private final OutputTree body;
    private final OutputTreeReturn<IntVariable> start;
    private final OutputTreeReturn<IntVariable> end;
    private final OutputTreeReturn<IntVariable> step;
    private final VariableDescription<IntVariable> indexDesc;
    private final boolean incrementing;
    // A flag to say if the end is not included in the range. This is used to try to
    // keep the code more conventional to read.
    private final boolean lessThan;

    OutputFor(OutputTree body, OutputTreeReturn<IntVariable> start, OutputTreeReturn<IntVariable> end,
            OutputTreeReturn<IntVariable> step, VariableDescription<IntVariable> indexDesc, boolean incrementing,
            boolean lessThan, String comment) {
        super(OutputTreeType.FOR, true, comment);
        while(body.type == OutputTreeType.SCOPE)
            body = ((OutputTreeScope) body).tree;
        this.body = body;
        body.setScopeStart();
        this.start = start;
        this.end = end;
        this.step = step;
        this.indexDesc = indexDesc;
        this.incrementing = incrementing;
        this.lessThan = lessThan;
    }

    @Override
    public void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        sb.append("for(int " + indexDesc + " = ");
        start.toJava(sb, indent, requiredImports);
        if(incrementing)
            sb.append("; " + indexDesc + (lessThan ? " < " : " <= "));
        else
            sb.append("; " + indexDesc + " >= ");
        end.toJava(sb, indent, requiredImports);
        sb.append("; " + indexDesc);
        sb.append(incrementing ? " += " : " -= ");
        step.toJava(sb, indent, requiredImports);
        sb.append(")");

        if(body.terminal || body.type == OutputTreeType.INITIALIZE || body.type == OutputTreeType.INITIALIZE_UNSET) {
            sb.append(" {");
            sb.append("\n");
            addIndent(sb, indent + 1);
            body.toJava(sb, indent + 1, requiredImports);
            if(!body.terminal)
                sb.append(";");
            addIndent(sb, indent);
            sb.append("}\n");
        } else {
            sb.append("\n");
            addIndent(sb, indent + 1);
            body.toJava(sb, indent + 1, requiredImports);
            sb.append(";\n");
        }
    }

    @Override
    public int scopeId(int scopeId) {
        return scopeId + 1;
    }

    @Override
    public OutputTree[] getChildren() {
        return new OutputTree[] { start, end, step, body };
    }

    @Override
    public String getDescription() {
        return incrementing
                ? "for(" + indexDesc + " = start; " + indexDesc + "!=end; " + indexDesc + " += step)\n{ body }"
                : "for(" + indexDesc + " = end; " + indexDesc + "!=start; " + indexDesc + " -= step)\n{ body }";
    }

    @Override
    protected OutputFor copy(Map<OutputTree, OutputTree> results) {
        OutputFor f = new OutputFor(body.copy(results), start.copy(results), end.copy(results), step.copy(results),
                indexDesc, incrementing, lessThan, comment);
        results.put(this, f);
        return f;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + body.hashCode();
        result = prime * result + end.hashCode();
        result = prime * result + indexDesc.hashCode();
        result = prime * result + start.hashCode();
        result = prime * result + step.hashCode();
        result = prime * result + (incrementing ? 1 : 0);
        result = prime * result + (lessThan ? 1 : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj) || (getClass() != obj.getClass()))
            return false;
        OutputFor other = (OutputFor) obj;
        if(!body.equals(other.body))
            return false;
        if(!end.equals(other.end))
            return false;
        if(!indexDesc.equals(other.indexDesc))
            return false;
        if(!start.equals(other.start))
            return false;
        if(!step.equals(other.step))
            return false;
        return incrementing == other.incrementing && lessThan == other.lessThan;
    }
}
