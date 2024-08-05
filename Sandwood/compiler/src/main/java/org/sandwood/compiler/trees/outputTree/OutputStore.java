/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;

public class OutputStore<X extends Variable<X>> extends OutputTree {

    private final VariableDescription<X> varDesc;
    private final OutputTreeReturn<X> value;

    OutputStore(VariableDescription<X> varDesc, OutputTreeReturn<X> value, String comment) {
        super(OutputTreeType.STORE, false, comment);
        this.varDesc = varDesc;
        this.value = value;
        if(value.type == OutputTreeType.CAST_TO_DOUBLE)
            ((OutputCastToDouble) value).setImplicit();
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        sb.append(varDesc + " = ");
        value.toJava(sb, indent, requiredImports);
    }

    @Override
    public OutputTree[] getChildren() {
        return new OutputTree[] { value };
    }

    @Override
    public String getDescription() {
        return varDesc + " = value";
    }

    @Override
    protected OutputStore<X> copy(Map<OutputTree, OutputTree> results) {
        OutputStore<X> s = new OutputStore<>(varDesc, value.copy(results), comment);
        results.put(this, s);
        return s;
    }

    @Override
    public int hashCode() {
        return Objects.hash(varDesc, value);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj) || (getClass() != obj.getClass()))
            return false;
        OutputStore<?> other = (OutputStore<?>) obj;
        return Objects.equals(varDesc, other.varDesc) && Objects.equals(value, other.value);
    }
}
