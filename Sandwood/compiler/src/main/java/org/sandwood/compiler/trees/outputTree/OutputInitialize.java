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
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.trees.Visibility;

public class OutputInitialize<A extends Variable<A>> extends OutputTree {

    private final VariableDescription<A> varDesc;
    private final Type<A> type;
    private final OutputTreeReturn<A> value;
    private final Visibility visibility;

    OutputInitialize(Visibility visibility, Type<A> type, VariableDescription<A> varDesc, OutputTreeReturn<A> value,
            String comment) {
        super(OutputTreeType.INITIALIZE, true, comment);
        this.varDesc = varDesc;
        this.type = type;
        this.value = value;
        this.visibility = visibility;
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        if(visibility != Visibility.DEFAULT)
            sb.append(visibility.toJava() + " ");
        sb.append(type.getJavaType(requiredImports) + " " + varDesc + " = ");
        value.toJava(sb, indent, requiredImports);
        sb.append(";\n");
    }

    @Override
    public OutputTree[] getChildren() {
        return new OutputTree[] { value };
    }

    @Override
    public String getDescription() {
        return "type name = value;";
    }

    @Override
    protected OutputInitialize<A> copy(Map<OutputTree, OutputTree> results) {
        OutputInitialize<A> i = new OutputInitialize<>(visibility, type, varDesc, value.copy(results), comment);
        results.put(this, i);
        return i;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + varDesc.hashCode();
        result = prime * result + type.hashCode();
        result = prime * result + value.hashCode();
        result = prime * result + visibility.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj) || (getClass() != obj.getClass()))
            return false;
        OutputInitialize<?> other = (OutputInitialize<?>) obj;
        if(!varDesc.equals(other.varDesc))
            return false;
        if(!type.equals(other.type))
            return false;
        if(!value.equals(other.value))
            return false;
        return visibility == other.visibility;
    }
}
