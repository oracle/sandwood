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

import org.sandwood.compiler.dataflowGraph.variables.ObjectVariable;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;

public class OutputGetField<A extends ObjectVariable<A>, X extends Variable<X>> extends OutputTreeReturn<X> {

    private final VariableDescription<X> varDesc;
    private final OutputTreeReturn<A> tree;

    OutputGetField(OutputTreeReturn<A> tree, VariableDescription<X> varDesc) {
        super(OutputTreeType.GET_FIELD);
        this.varDesc = varDesc;
        this.tree = tree;
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        tree.toJava(sb, indent, requiredImports);
        sb.append("." + varDesc.name);
    }

    @Override
    public OutputTree[] getChildren() {
        return new OutputTree[] { tree };
    }

    @Override
    public String getDescription() {
        return "." + varDesc;
    }

    @Override
    protected OutputGetField<A,X> copy(Map<OutputTree, OutputTree> results) {
        OutputGetField<A,X> g = new OutputGetField<>(tree.copy(results), varDesc);
        results.put(this, g);
        return g;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + varDesc.hashCode();
        result = prime * result + tree.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj) || (getClass() != obj.getClass()))
            return false;
        OutputGetField<?,?> other = (OutputGetField<?,?>) obj;
        if(!varDesc.equals(other.varDesc))
            return false;
        return tree.equals(other.tree);
    }
}
