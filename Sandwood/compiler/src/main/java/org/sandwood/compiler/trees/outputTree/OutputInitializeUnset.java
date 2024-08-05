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
import org.sandwood.compiler.trees.Visibility;

public class OutputInitializeUnset<B extends Variable<B>> extends OutputTree {

    private final VariableDescription<B> varDesc;
    private final Visibility visibility;

    protected OutputInitializeUnset(Visibility visibility, VariableDescription<B> varDesc, String comment) {
        super(OutputTreeType.INITIALIZE_UNSET, true, comment);
        this.varDesc = varDesc;
        this.visibility = visibility;
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        if(visibility != Visibility.DEFAULT)
            sb.append(visibility.toJava() + " ");
        sb.append(varDesc.type.getJavaType(requiredImports) + " " + varDesc.name + ";\n");
    }

    @Override
    public OutputTree[] getChildren() {
        return new OutputTree[0];
    }

    @Override
    public String getDescription() {
        return "type name;";
    }

    @Override
    protected OutputInitializeUnset<B> copy(Map<OutputTree, OutputTree> results) {
        OutputInitializeUnset<B> i = new OutputInitializeUnset<>(visibility, varDesc, comment);
        results.put(this, i);
        return i;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + varDesc.hashCode();
        result = prime * result + type.hashCode();
        result = prime * result + visibility.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj) || (getClass() != obj.getClass()))
            return false;
        OutputInitializeUnset<?> other = (OutputInitializeUnset<?>) obj;
        if(!varDesc.equals(other.varDesc))
            return false;
        if(!type.equals(other.type))
            return false;
        return visibility == other.visibility;
    }
}
