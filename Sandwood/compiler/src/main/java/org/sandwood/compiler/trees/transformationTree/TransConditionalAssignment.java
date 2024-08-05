/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.Tag;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.outputTree.OutputTreeReturn;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransConditionalAssignment<A extends Variable<A>> extends TransTreeReturn<A> {

    public final TransTreeReturn<BooleanVariable> condition;
    public final TransTreeReturn<A> ifValue, elseValue;

    private TransConditionalAssignment(TransTreeReturn<BooleanVariable> condition, TransTreeReturn<A> ifValue,
            TransTreeReturn<A> elseValue, Set<Tag> tags) {
        super(TransTreeType.CONDITIONAL_ASSIGNMENT,
                1 + condition.treeSize() + ifValue.treeSize() + elseValue.treeSize());
        this.condition = condition;
        this.ifValue = ifValue;
        this.elseValue = elseValue;
        addTags(tags);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(condition);
        v.visit(ifValue);
        v.visit(elseValue);
    }

    @Override
    public TransTreeReturn<A> applyTransformationInternal(Transformer t) {
        return getConditionalAssignment(t.transform(condition), t.transform(ifValue), t.transform(elseValue), tags);
    }

    @Override
    public OutputTreeReturn<A> toOutputTreeReturnInternal() {
        return OutputTree.conditionalAssignment(condition.toOutputTreeReturnInternal(),
                ifValue.toOutputTreeReturnInternal(), elseValue.toOutputTreeReturnInternal());

    }

    @Override
    public TransTree<?>[] getChildren() {
        return new TransTree<?>[] { condition, ifValue, elseValue };
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        result = prime * result + condition.equivalentHashCode();
        result = prime * result + ifValue.equivalentHashCode();
        result = prime * result + elseValue.equivalentHashCode();
        return result;
    }

    @Override
    public boolean equivalent(TransTree<?> tree, Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == tree)
            return true;
        if((tree == null) || (tree.type != TransTreeType.CONDITIONAL_ASSIGNMENT))
            return false;
        TransConditionalAssignment<?> other = (TransConditionalAssignment<?>) tree;
        if(!condition.equivalent(other.condition, substitutions))
            return false;
        if(!ifValue.equivalent(other.ifValue, substitutions))
            return false;
        return elseValue.equivalent(other.elseValue, substitutions);
    }

    @Override
    public String getDescription() {
        return "(condition?ifValue:elseValue)";
    }

    public static <A extends Variable<A>> TransConditionalAssignment<A> getConditionalAssignment(
            TransTreeReturn<BooleanVariable> condition, TransTreeReturn<A> ifValue, TransTreeReturn<A> elseValue,
            Set<Tag> tags) {
        return new TransConditionalAssignment<>(condition, ifValue, elseValue, tags);
    }

    @Override
    public Type<A> getOutputType() {
        return ifValue.getOutputType();
    }

    @Override
    public TransTreeReturn<A> maxValue(Bounds bounds) {
        // TODO remove this guard once numeric trees are added.
        if(ifValue.getOutputType() == VariableType.BooleanVariable)
            throw new CompilerException("max value is not supported on boolean values");
        return internalMax(bounds);
    }

    // TODO Once numeric trees are added in line the function.
    private <B extends NumberVariable<B>> TransTreeReturn<A> internalMax(Bounds bounds) {
        return (TransTreeReturn<A>) max((TransTreeReturn<B>) ifValue.maxValue(bounds),
                (TransTreeReturn<B>) elseValue.maxValue(bounds));
    }

    @Override
    public TransTreeReturn<A> minValue(Bounds bounds) {
        // TODO Remove this guard once numeric trees are added.
        if(ifValue.getOutputType() == VariableType.BooleanVariable)
            throw new CompilerException("max value is not supported on boolean values");
        return internalMin(bounds);
    }

    // TODO Once numeric trees are added in line the function.
    private <B extends NumberVariable<B>> TransTreeReturn<A> internalMin(Bounds bounds) {
        return (TransTreeReturn<A>) min((TransTreeReturn<B>) ifValue.minValue(bounds),
                (TransTreeReturn<B>) elseValue.minValue(bounds));
    }
}
