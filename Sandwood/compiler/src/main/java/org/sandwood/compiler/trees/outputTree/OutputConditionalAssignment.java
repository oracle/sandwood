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
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;

public class OutputConditionalAssignment<A extends Variable<A>> extends OutputTreeReturn<A> {

    private final OutputTreeReturn<BooleanVariable> condition;
    private final OutputTreeReturn<A> ifValue, elseValue;

    OutputConditionalAssignment(OutputTreeReturn<BooleanVariable> condition, OutputTreeReturn<A> ifValue,
            OutputTreeReturn<A> elseValue) {
        super(OutputTreeType.CONDITIONAL_ASSIGNMENT);
        this.condition = condition;
        this.ifValue = ifValue;
        this.elseValue = elseValue;
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        sb.append("(");
        condition.toJava(sb, indent, requiredImports);
        sb.append("?");
        ifValue.toJava(sb, indent, requiredImports);
        sb.append(":");
        elseValue.toJava(sb, indent, requiredImports);
        sb.append(")");
    }

    @Override
    public OutputTree[] getChildren() {
        return new OutputTree[] { condition, ifValue, elseValue };
    }

    @Override
    public String getDescription() {
        return "(condition?ifValue:elseValue)";
    }

    @Override
    protected OutputConditionalAssignment<A> copy(Map<OutputTree, OutputTree> results) {
        OutputConditionalAssignment<A> t = new OutputConditionalAssignment<>(condition.copy(results),
                ifValue.copy(results), elseValue.copy(results));
        results.put(t, this);
        return t;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + condition.hashCode();
        result = prime * result + elseValue.hashCode();
        result = prime * result + ifValue.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj) || (getClass() != obj.getClass()))
            return false;
        OutputConditionalAssignment<?> other = (OutputConditionalAssignment<?>) obj;
        if(!condition.equals(other.condition))
            return false;
        if(!elseValue.equals(other.elseValue))
            return false;
        return ifValue.equals(other.ifValue);
    }

}
