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

public class OutputArrayPut<X extends Variable<X>> extends OutputTree {

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + array.hashCode();
        result = prime * result + index.hashCode();
        result = prime * result + value.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj) || (getClass() != obj.getClass()))
            return false;
        OutputArrayPut<?> other = (OutputArrayPut<?>) obj;
        if(!array.equals(other.array))
            return false;
        if(!index.equals(other.index))
            return false;
        return value.equals(other.value);
    }

    private final OutputTreeReturn<ArrayVariable<X>> array;
    private final OutputTreeReturn<IntVariable> index;
    private final OutputTreeReturn<X> value;

    OutputArrayPut(OutputTreeReturn<ArrayVariable<X>> array, OutputTreeReturn<IntVariable> index,
            OutputTreeReturn<X> value, String comment) {
        super(OutputTreeType.ARRAY_PUT, false, comment);
        this.array = array;
        this.index = index;
        this.value = value;
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        array.toJava(sb, indent, requiredImports);
        sb.append("[");
        index.toJava(sb, indent, requiredImports);
        sb.append("] = ");
        value.toJava(sb, indent, requiredImports);
    }

    @Override
    public OutputTree[] getChildren() {
        return new OutputTree[] { array, index, value };
    }

    @Override
    public String getDescription() {
        return "array[index] = value";
    }

    @Override
    protected OutputArrayPut<X> copy(Map<OutputTree, OutputTree> results) {
        OutputArrayPut<X> a = new OutputArrayPut<>(array.copy(results), index.copy(results), value.copy(results),
                comment);
        results.put(this, a);
        return a;
    }
}
