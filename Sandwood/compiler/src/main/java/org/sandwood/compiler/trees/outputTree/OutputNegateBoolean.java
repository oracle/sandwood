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

import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;

public class OutputNegateBoolean extends OutputTreeReturn<BooleanVariable> {

    private final OutputTreeReturn<BooleanVariable> toNegate;

    private OutputNegateBoolean(OutputTreeReturn<BooleanVariable> toNegate) {
        super(OutputTreeType.NEGATE);
        this.toNegate = toNegate;
    }

    @Override
    protected OutputNegateBoolean copy(Map<OutputTree, OutputTree> results) {
        OutputNegateBoolean r = OutputTree.negateBoolean(toNegate.copy(results));
        results.put(this, r);
        return r;
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        sb.append("!");
        toNegate.toJava(sb, indent, requiredImports);
    }

    @Override
    public OutputTree[] getChildren() {
        return new OutputTree[] { toNegate };
    }

    @Override
    public String getDescription() {
        return "Negate Value";
    }

    static OutputNegateBoolean getNegateBoolean(OutputTreeReturn<BooleanVariable> toNegate) {
        return new OutputNegateBoolean(toNegate);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + toNegate.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj) || (getClass() != obj.getClass()))
            return false;
        OutputNegateBoolean other = (OutputNegateBoolean) obj;
        return toNegate.equals(other.toNegate);
    }
}
