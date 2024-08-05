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

public class OutputMin<A extends ScalarVariable<A>> extends OutputTreeReturn<A> {

    protected final OutputTreeReturn<A> left;
    protected final OutputTreeReturn<A> right;

    protected OutputMin(OutputTreeReturn<A> left, OutputTreeReturn<A> right) {
        super(OutputTreeType.BINOP);
        this.left = left;
        if(left.type == OutputTreeType.CAST_TO_DOUBLE)
            ((OutputCastToDouble) left).setImplicit();
        this.right = right;
        if(right.type == OutputTreeType.CAST_TO_DOUBLE)
            ((OutputCastToDouble) right).setImplicit();
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        sb.append("Math.min(");
        left.toJava(sb, indent, requiredImports);
        sb.append(", ");
        right.toJava(sb, indent, requiredImports);
        sb.append(")");
    }

    @Override
    public OutputTree[] getChildren() {
        return new OutputTree[] { left, right };
    }

    @Override
    public String getDescription() {
        return "min(left, right)";
    }

    @Override
    protected OutputMin<A> copy(Map<OutputTree, OutputTree> results) {
        OutputMin<A> b = new OutputMin<>(left.copy(results), right.copy(results));
        results.put(this, b);
        return b;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + left.hashCode();
        result = prime * result + right.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj) || (getClass() != obj.getClass()))
            return false;
        OutputMin<?> other = (OutputMin<?>) obj;
        if(!left.equals(other.left))
            return false;
        return right.equals(other.right);
    }
}
