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

import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.ScalarVariable;

public class OutputConst<X extends ScalarVariable<X>> extends OutputTreeReturn<X> {

    private final String value;

    OutputConst(String value) {
        super(OutputTreeType.CONST);
        this.value = value;
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        sb.append(value);
    }

    @Override
    public OutputTree[] getChildren() {
        return new OutputTree[0];
    }

    @Override
    public String getDescription() {
        return value;
    }

    @Override
    protected OutputConst<X> copy(Map<OutputTree, OutputTree> results) {
        OutputConst<X> c = new OutputConst<>(value);
        results.put(this, c);
        return c;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + value.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj) || (getClass() != obj.getClass()))
            return false;
        OutputConst<?> other = (OutputConst<?>) obj;
        return value.equals(other.value);
    }
}
