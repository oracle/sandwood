/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.binop;

import java.util.Map;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public abstract class TransBinOp<L extends Variable<L>, R extends Variable<R>, RT extends Variable<RT>>
        extends TransTreeReturn<RT> {

    public final TransTreeReturn<L> left;
    public final TransTreeReturn<R> right;
    protected final boolean commutative;

    protected TransBinOp(TransTreeReturn<L> left, TransTreeReturn<R> right, boolean commutative, TransTreeType type) {
        super(type, 1 + left.size() + right.size());
        this.left = left;
        this.right = right;
        this.commutative = commutative;
        assert (left != null);
        assert (right != null);
    }

    protected abstract String getOp();

    @Override
    public TransTree<?>[] getChildren() {
        return new TransTree<?>[] { left, right };
    }

    @Override
    public String getDescription() {
        return getOp();
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        if(commutative)
            result = prime * result + left.equivalentHashCode() + right.equivalentHashCode();
        else {
            result = prime * result + left.equivalentHashCode();
            result = prime * result + right.equivalentHashCode();
        }
        return result;
    }

    @Override
    public boolean equivalentInternal(TransTree<?> tree,
            Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        TransBinOp<?, ?, ?> other = (TransBinOp<?, ?, ?>) tree;
        if(commutative) {
            return (left.equivalent(other.left, substitutions) && right.equivalent(other.right, substitutions))
                    || (left.equivalent(other.right, substitutions) && right.equivalent(other.left, substitutions));
        } else {
            return left.equivalent(other.left, substitutions) && right.equivalent(other.right, substitutions);
        }
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(left);
        v.visit(right);
    }

    @Override
    public final TransTreeReturn<RT> maxValue(Bounds bounds) {
        TransTreeReturn<RT> tree = maxValueInternal(bounds);
        bounds.addTransformedTree(this, tree);
        return tree;
    }

    @Override
    public final TransTreeReturn<RT> minValue(Bounds bounds) {
        TransTreeReturn<RT> tree = minValueInternal(bounds);
        bounds.addTransformedTree(this, tree);
        return tree;
    }

    protected abstract TransTreeReturn<RT> maxValueInternal(Bounds bounds);

    protected abstract TransTreeReturn<RT> minValueInternal(Bounds bounds);
}
