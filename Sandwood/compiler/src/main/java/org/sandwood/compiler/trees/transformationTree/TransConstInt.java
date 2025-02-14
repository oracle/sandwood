/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.Map;

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.outputTree.OutputConst;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransConstInt extends TransTreeReturn<IntVariable> {

    public final int value;

    TransConstInt(int value) {
        super(TransTreeType.CONST_INT, 1);
        this.value = value;
    }

    @Override
    public TransTree<?>[] getChildren() {
        return new TransTree<?>[0];
    }

    @Override
    public String getDescription() {
        return Integer.toString(value);
    }

    @Override
    public OutputConst<IntVariable> toOutputTreeReturnInternal() {
        return OutputTree.constant(value);
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        result = prime * result + value;
        return result;
    }

    @Override
    public boolean equivalentInternal(TransTree<?> tree,
            Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        TransConstInt other = (TransConstInt) tree;
        return value == other.value;
    }

    @Override
    public TransConstInt applyTransformationInternal(Transformer t) {
        return new TransConstInt(value);
    }

    @Override
    public Type<IntVariable> getOutputType() {
        return VariableType.IntVariable;
    }

    @Override
    public void traverseTree(TreeVisitor v) {}

    @Override
    public TransTreeReturn<IntVariable> maxValue(Bounds bounds) {
        return this;
    }

    @Override
    public TransTreeReturn<IntVariable> minValue(Bounds bounds) {
        return this;
    }
}
