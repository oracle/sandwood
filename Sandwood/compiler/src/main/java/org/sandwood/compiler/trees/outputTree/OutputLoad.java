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
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;

public class OutputLoad<X extends Variable<X>> extends OutputTreeReturn<X> {

    private final VariableDescription<X> varDesc;

    OutputLoad(VariableDescription<X> varDesc) {
        super(OutputTreeType.LOAD);
        this.varDesc = varDesc;
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        sb.append(varDesc.name);
    }

    @Override
    public OutputTree[] getChildren() {
        return new OutputTree[0];
    }

    @Override
    public String getDescription() {
        return varDesc.toString();
    }

    @Override
    protected OutputLoad<X> copy(Map<OutputTree, OutputTree> results) {
        OutputLoad<X> l = new OutputLoad<>(varDesc);
        results.put(this, l);
        return l;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + varDesc.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj) || (getClass() != obj.getClass()))
            return false;
        OutputLoad<?> other = (OutputLoad<?>) obj;
        return varDesc.equals(other.varDesc);
    }

}
