/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;

public class IRMax<A extends NumberVariable<A>> extends IRTreeReturn<A> {

    protected final IRTreeReturn<A> left;
    protected final IRTreeReturn<A> right;

    private IRMax(IRTreeReturn<A> left, IRTreeReturn<A> right) {
        super(IRTreeType.MAX);
        this.left = left;
        this.right = right;
    }

    @Override
    public IRTree[] getChildren() {
        return new IRTree[] { left, right };
    }

    @Override
    public String getDescription() {
        return "max(left, right)";
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + left.equivalentHashCode();
        result = prime * result + right.equivalentHashCode();
        return result;
    }

    @Override
    public boolean equivalent(IRTree tree) {
        if((tree == null) || (type != tree.type))
            return false;
        IRMax<?> other = (IRMax<?>) tree;
        return (left.equivalent(other.left) && right.equivalent(other.right))
                || (left.equivalent(other.right) && right.equivalent(other.left));
    }

    @Override
    public TransTreeReturn<A> toTransformationTree() {
        return TransTree.max(left.toTransformationTree(), right.toTransformationTree());
    }

    public static <A extends NumberVariable<A>> IRMax<A> max(IRTreeReturn<A> left, IRTreeReturn<A> right) {
        return new IRMax<>(left, right);
    }

    @Override
    public IRMax<A> applyTransformation(TreeTransformation t) {
        return new IRMax<>(t.transformReturn(left), t.transformReturn(right));
    }

    @Override
    public Type<A> getOutputType() {
        return left.getOutputType();
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(left);
        v.visit(right);
    }
}
