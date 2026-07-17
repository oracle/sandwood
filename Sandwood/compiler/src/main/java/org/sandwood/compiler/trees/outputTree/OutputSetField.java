/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.ObjectVariable;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;

public class OutputSetField<A extends ObjectVariable<A>, X extends Variable<X>> extends OutputTree {

    private final OutputTreeReturn<A> objectTree;
    private final VariableDescription<X> fieldDesc;
    private final OutputTreeReturn<X> value;
    public OutputSetField(OutputTreeReturn<A> objectTree, VariableDescription<X> fieldDesc, OutputTreeReturn<X> value, String comment) {
        super(OutputTreeType.SET_FIELD, false, comment);
        Objects.nonNull(objectTree);
        this.objectTree = objectTree;
        Objects.nonNull(fieldDesc);
        this.fieldDesc = fieldDesc;
        Objects.nonNull(value);
        this.value = value;
    }

    @Override
    protected OutputSetField<A,X> copy(Map<OutputTree, OutputTree> results) {
        OutputSetField<A,X> s = new OutputSetField<>(objectTree, fieldDesc, value, comment);
        results.put(this, s);
        return s;
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        objectTree.toJavaInternal(sb, indent, requiredImports);
        sb.append("." + fieldDesc + " = ");
        value.toJavaInternal(sb, indent, requiredImports);
    }

    @Override
    public OutputTree[] getChildren() {
        return new OutputTree[] { objectTree, value };
    }

    @Override
    public String getDescription() {
        return "." + fieldDesc + " = value";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + fieldDesc.hashCode();
        result = prime * result + objectTree.hashCode();
        result = prime * result + value.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj))
            return false;
        if(getClass() != obj.getClass())
            return false;
        OutputSetField<?,?> other = (OutputSetField<?,?>) obj;
        if(!fieldDesc.equals(other.fieldDesc))
            return false;
        if(!objectTree.equals(other.objectTree))
            return false;
        return value.equals(other.value);
    }
}
