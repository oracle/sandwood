/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.ArrayType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;

public class OutputNewArray<X extends Variable<X>> extends OutputTreeReturn<ArrayVariable<X>> {

    private final List<OutputTreeReturn<IntVariable>> lengths;
    private final ArrayType<X> type;

    OutputNewArray(List<OutputTreeReturn<IntVariable>> lengths, ArrayType<X> type) {
        super(OutputTreeType.ALLOCATE_ARRAY);
        this.lengths = lengths;
        this.type = type;
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        String typeString = type.getBaseJavaType(requiredImports);
        sb.append("new " + typeString);
        for(OutputTreeReturn<IntVariable> length:lengths) {
            sb.append("[");
            length.toJava(sb, indent, requiredImports);
            sb.append("]");
        }
        for(int depth = type.getDepth() - lengths.size(); depth != 0; depth--)
            sb.append("[]");
    }

    @Override
    public OutputTree[] getChildren() {
        OutputTree[] trees = new OutputTree[lengths.size()];
        lengths.toArray(trees);
        return trees;
    }

    @Override
    public String getDescription() {
        return "Construct array of type:" + type;
    }

    @Override
    protected OutputNewArray<X> copy(Map<OutputTree, OutputTree> results) {
        List<OutputTreeReturn<IntVariable>> copiedLengths = new ArrayList<>();
        for(OutputTreeReturn<IntVariable> length:lengths)
            copiedLengths.add(length.copy(results));
        OutputNewArray<X> n = new OutputNewArray<>(copiedLengths, type);
        results.put(this, n);
        return n;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + lengths.hashCode();
        result = prime * result + type.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj) || (getClass() != obj.getClass()))
            return false;
        OutputNewArray<?> other = (OutputNewArray<?>) obj;
        if(!type.equals(other.type))
            return false;
        return lengths.equals(other.lengths);
    }
}
