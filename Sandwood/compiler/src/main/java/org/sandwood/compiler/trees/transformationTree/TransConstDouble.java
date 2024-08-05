/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.Map;

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.trees.outputTree.OutputConst;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransConstDouble extends TransTreeReturn<DoubleVariable> {

    public final double value;

    TransConstDouble(double value) {
        super(TransTreeType.CONST_DOUBLE, 1);
        this.value = value;
    }

    @Override
    public TransTree<?>[] getChildren() {
        return new TransTree<?>[0];
    }

    @Override
    public String getDescription() {
        return Double.toString(value);
    }

    @Override
    public OutputConst<DoubleVariable> toOutputTreeReturnInternal() {
        return OutputTree.constant(value);
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) value;
        return result;
    }

    @Override
    public boolean equivalent(TransTree<?> tree, Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        TransConstDouble other = (TransConstDouble) tree;
        return value == other.value || (Double.isNaN(value) && Double.isNaN(other.value));
    }

    @Override
    public TransConstDouble applyTransformationInternal(Transformer t) {
        return new TransConstDouble(value);
    }

    @Override
    public Type<DoubleVariable> getOutputType() {
        return VariableType.DoubleVariable;
    }

    @Override
    public void traverseTree(TreeVisitor v) {}

    @Override
    public TransTreeReturn<DoubleVariable> maxValue(Bounds bounds) {
        return this;
    }

    @Override
    public TransTreeReturn<DoubleVariable> minValue(Bounds bounds) {
        return this;
    }
}
