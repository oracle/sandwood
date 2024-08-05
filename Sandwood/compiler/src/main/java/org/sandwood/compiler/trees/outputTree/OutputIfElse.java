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

import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.trees.Tree;

public class OutputIfElse extends OutputTree {

    private final OutputTreeReturn<BooleanVariable> condition;
    private final OutputTree ifBody, elseBody;
    private final String elseComment;

    protected OutputIfElse(OutputTreeReturn<BooleanVariable> condition, OutputTree ifBody, String ifComment,
            OutputTree elseBody, String elseComment) {
        super(OutputTreeType.IF, true, ifComment);
        this.condition = condition;
        this.ifBody = ifBody;
        ifBody.setScopeStart();
        this.elseBody = elseBody;
        elseBody.setScopeStart();
        this.elseComment = elseComment;
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        boolean brackets = ifBody.type == OutputTreeType.IF || ifBody.type == OutputTreeType.SEQUENTIAL
                || ifBody.type == OutputTreeType.FOR || ifBody.type == OutputTreeType.SCOPE
                || ifBody.type == OutputTreeType.INITIALIZE || ifBody.type == OutputTreeType.INITIALIZE_UNSET;

        sb.append("if(");
        condition.toJava(sb, indent, requiredImports);

        sb.append(")" + ((brackets) ? " {" : "") + "\n");
        addIndent(sb, indent + 1);

        ifBody.toJava(sb, indent + 1, requiredImports);

        if(!ifBody.terminal)
            sb.append(";\n");

        if(brackets) {
            addIndent(sb, indent);
            sb.append("}");
        }

        if(elseBody.type != OutputTreeType.NOP) {
            if(brackets) {
                if(elseComment == Tree.NoComment || !OutputTree.includeComments)
                    sb.append(" ");
                else {
                    sb.append("\n");
                    addIndent(sb, indent);
                    generateComment(sb, indent, elseComment);
                }
            } else {
                addIndent(sb, indent);
                generateComment(sb, indent, elseComment);
            }

            brackets = elseBody.type == OutputTreeType.IF || elseBody.type == OutputTreeType.SEQUENTIAL
                    || elseBody.type == OutputTreeType.FOR || elseBody.type == OutputTreeType.SCOPE
                    || elseBody.type == OutputTreeType.INITIALIZE || elseBody.type == OutputTreeType.INITIALIZE_UNSET;
            sb.append("else" + ((brackets) ? " {" : "") + "\n");
            addIndent(sb, indent + 1);

            elseBody.toJava(sb, indent + 1, requiredImports);

            if(!elseBody.terminal)
                sb.append(";\n");

            if(brackets) {
                addIndent(sb, indent);
                sb.append("}\n");
            }
        } else {
            if(ifBody.terminal)
                sb.append("\n");
        }

    }

    @Override
    public OutputTree[] getChildren() {
        if(elseBody == null) {
            return new OutputTree[] { condition, ifBody };
        } else {
            return new OutputTree[] { condition, ifBody, elseBody };
        }
    }

    @Override
    public String getDescription() {
        return "if(condition) then ifBody else elseBody";
    }

    @Override
    protected OutputIfElse copy(Map<OutputTree, OutputTree> results) {
        OutputIfElse t = new OutputIfElse(condition.copy(results), ifBody.copy(results), comment,
                elseBody.copy(results), elseComment);
        results.put(t, this);
        return t;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + condition.hashCode();
        result = prime * result + elseBody.hashCode();
        result = prime * result + ifBody.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj) || (getClass() != obj.getClass()))
            return false;
        OutputIfElse other = (OutputIfElse) obj;
        if(!condition.equals(other.condition))
            return false;
        if(!elseBody.equals(other.elseBody))
            return false;
        return ifBody.equals(other.ifBody);
    }

}
