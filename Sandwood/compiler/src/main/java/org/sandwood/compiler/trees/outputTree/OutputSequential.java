/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class OutputSequential extends OutputTree {

    private final OutputTree[] trees;

    OutputSequential(OutputTree[] trees, String comment) {
        super(OutputTreeType.SEQUENTIAL, true, comment);
        this.trees = trees;
        // We know by construction there are at least 2 statements.
        trees[0].setScopeStart();
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        boolean first = true;
        for(OutputTree t:trees) {
            if(first)
                first = false;
            else
                addIndent(sb, indent);
            t.toJava(sb, indent, requiredImports);
            if(!t.terminal)
                sb.append(";\n");
        }
    }

    @Override
    public OutputTree[] getChildren() {
        return trees;
    }

    @Override
    public String getDescription() {
        return "seq";
    }

    @Override
    protected OutputSequential copy(Map<OutputTree, OutputTree> results) {
        int size = trees.length;
        OutputTree[] ts = new OutputTree[size];
        for(int i = 0; i < size; i++)
            ts[i] = trees[i].copy(results);
        OutputSequential s = new OutputSequential(ts, comment);
        results.put(this, s);
        return s;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(trees);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj) || (getClass() != obj.getClass()))
            return false;
        OutputSequential other = (OutputSequential) obj;
        return Arrays.equals(trees, other.trees);
    }
}
