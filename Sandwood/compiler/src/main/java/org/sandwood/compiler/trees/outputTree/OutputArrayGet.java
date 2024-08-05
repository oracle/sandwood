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

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;

public class OutputArrayGet<X extends Variable<X>> extends OutputTreeReturn<X> {
    private final OutputTreeReturn<ArrayVariable<X>> array;
    private final OutputTreeReturn<IntVariable> index;

    OutputArrayGet(OutputTreeReturn<ArrayVariable<X>> array, OutputTreeReturn<IntVariable> index) {
        super(OutputTreeType.ARRAY_GET);
        assert array != null : "Construction argument \"array\" equal to null";
        assert index != null : "Construction argument \"index\" equal to null";
        this.array = array;
        this.index = index;
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        array.toJava(sb, indent, requiredImports);
        sb.append("[");
        index.toJava(sb, indent, requiredImports);
        sb.append("]");
    }

    @Override
    public OutputTree[] getChildren() {
        return new OutputTree[] { array, index };
    }

    @Override
    public String getDescription() {
        return "array[index]";
    }

    @Override
    protected OutputArrayGet<X> copy(Map<OutputTree, OutputTree> results) {
        OutputArrayGet<X> a = new OutputArrayGet<>(array.copy(results), index.copy(results));
        results.put(this, a);
        return a;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + array.hashCode();
        result = prime * result + index.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj) || (getClass() != obj.getClass()))
            return false;
        OutputArrayGet<?> other = (OutputArrayGet<?>) obj;
        if(!array.equals(other.array))
            return false;
        return index.equals(other.index);
    }
}
