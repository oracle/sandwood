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

public class OutputGetIntField extends OutputTreeReturn<IntVariable> {

    private final String name;
    private final OutputTreeReturn<?> tree;

    // TODO tighten up the type of Tree return.
    OutputGetIntField(OutputTreeReturn<?> tree, String name) {
        super(OutputTreeType.GET_FIELD);
        this.name = name;
        this.tree = tree;
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        tree.toJava(sb, indent, requiredImports);
        sb.append("." + name);
    }

    @Override
    public OutputTree[] getChildren() {
        return new OutputTree[] { tree };
    }

    @Override
    public String getDescription() {
        return "." + name;
    }

    @Override
    protected OutputGetIntField copy(Map<OutputTree, OutputTree> results) {
        OutputGetIntField g = new OutputGetIntField(tree.copy(results), name);
        results.put(this, g);
        return g;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + name.hashCode();
        result = prime * result + tree.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj) || (getClass() != obj.getClass()))
            return false;
        OutputGetIntField other = (OutputGetIntField) obj;
        if(!name.equals(other.name))
            return false;
        return tree.equals(other.tree);
    }

}
