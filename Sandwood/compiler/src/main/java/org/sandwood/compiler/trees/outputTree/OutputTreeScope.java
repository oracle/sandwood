/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Class to implement a block of scope with limited scope.
 *
 */
public class OutputTreeScope extends OutputTree {

    public final OutputTree tree;

    OutputTreeScope(OutputTree tree, String comment) {
        super(OutputTreeType.SCOPE, true, comment);
        this.tree = tree;
        tree.setScopeStart();
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        sb.append("{\n");
        addIndent(sb, indent + 1);
        tree.toJava(sb, indent + 1, requiredImports);
        if(!tree.terminal)
            sb.append(";\n");
        addIndent(sb, indent);
        sb.append("}\n");
    }

    @Override
    public OutputTree[] getChildren() {
        return new OutputTree[] { tree };
    }

    @Override
    public String getDescription() {
        return "Scope";
    }

    @Override
    protected OutputTreeScope copy(Map<OutputTree, OutputTree> results) {
        OutputTreeScope t = new OutputTreeScope(tree.copy(results), comment);
        results.put(this, t);
        return t;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tree);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj) || (getClass() != obj.getClass()))
            return false;
        OutputTreeScope other = (OutputTreeScope) obj;
        return Objects.equals(tree, other.tree);
    }
}
