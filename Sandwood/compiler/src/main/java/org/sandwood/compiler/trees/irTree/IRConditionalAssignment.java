/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransConditionalAssignment;
import org.sandwood.compiler.trees.transformationTree.TransTree;

public class IRConditionalAssignment<X extends Variable<X>> extends IRTreeReturn<X> {

    private final IRTreeReturn<BooleanVariable> condition;
    private final IRTreeReturn<X> ifValue;
    private final IRTreeReturn<X> elseValue;

    protected IRConditionalAssignment(IRTreeReturn<BooleanVariable> condition, IRTreeReturn<X> ifValue,
            IRTreeReturn<X> elseValue) {
        super(IRTreeType.CONDITIONAL_ASSIGNMENT);
        this.condition = condition;
        this.ifValue = ifValue;
        this.elseValue = elseValue;
        assert (condition != null);
        assert (ifValue != null);
        assert (elseValue != null);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(condition);
        v.visit(ifValue);
        v.visit(elseValue);
    }

    @Override
    public IRTreeReturn<X> applyTransformation(TreeTransformation t) {
        IRConditionalAssignment<X> conditionalAssignment = new IRConditionalAssignment<>(t.transformReturn(condition),
                t.transformReturn(ifValue), t.transformReturn(elseValue));
        conditionalAssignment.addTags(tags);
        return conditionalAssignment;
    }

    @Override
    public TransConditionalAssignment<X> toTransformationTree() {
        return TransTree.conditionalAssignment(condition.toTransformationTree(), ifValue.toTransformationTree(),
                elseValue.toTransformationTree(), tags);
    }

    @Override
    public IRTree[] getChildren() {
        return new IRTree[] { condition, ifValue, elseValue };
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + condition.equivalentHashCode();
        result = prime * result + ifValue.equivalentHashCode();
        return prime * result + elseValue.equivalentHashCode();
    }

    @Override
    public boolean equivalent(IRTree tree) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        IRConditionalAssignment<?> other = (IRConditionalAssignment<?>) tree;
        // TODO make this able to handle different orders of conditional statements.
        if(!condition.equivalent(other.condition))
            return false;
        if(!ifValue.equivalent(other.ifValue))
            return false;
        else
            return elseValue.equivalent(other.elseValue);
    }

    @Override
    public String getDescription() {
        return "If(condition) ifBody [elseBody]";
    }

    @Override
    public Type<X> getOutputType() {
        return ifValue.getOutputType();
    }
}
