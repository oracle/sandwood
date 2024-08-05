/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.Map;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public abstract class TransUniOp<A extends Variable<A>, B extends Variable<B>> extends TransTreeReturn<B> {

    public final TransTreeReturn<A> input;
    private final Type<B> outputType;
    private final String description;

    public TransUniOp(TransTreeType type, TransTreeReturn<A> input, Type<B> outputType, String description) {
        super(type, 1 + input.treeSize());
        this.input = input;
        this.outputType = outputType;
        this.description = description;
    }

    @Override
    public TransTree<?>[] getChildren() {
        return new TransTree<?>[] { input };
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = type.hashCode();
        result = prime * result + input.equivalentHashCode();
        return result;
    }

    @Override
    public boolean equivalent(TransTree<?> tree, Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        TransUniOp<?, ?> other = (TransUniOp<?, ?>) tree;
        return input.equivalent(other.input, substitutions);
    }

    @Override
    public Type<B> getOutputType() {
        return outputType;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(input);
    }

    public TransTreeReturn<A> getInput() {
        return input;
    }
}