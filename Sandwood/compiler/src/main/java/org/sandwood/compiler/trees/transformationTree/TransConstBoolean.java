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
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.outputTree.OutputConst;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransConstBoolean extends TransTreeReturn<BooleanVariable> {

    public final boolean value;

    TransConstBoolean(Boolean value) {
        super(TransTreeType.CONST_BOOLEAN, 1);
        this.value = value;
    }

    @Override
    public TransTree<?>[] getChildren() {
        return new TransTree<?>[0];
    }

    @Override
    public String getDescription() {
        return Boolean.toString(value);
    }

    @Override
    public OutputConst<BooleanVariable> toOutputTreeReturnInternal() {
        return OutputTree.constant(value);
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value) ? 7 : 3);
        return result;
    }

    @Override
    public boolean equivalentInternal(TransTree<?> tree,
            Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        TransConstBoolean other = (TransConstBoolean) tree;
        return value == other.value;
    }

    @Override
    public TransConstBoolean applyTransformationInternal(Transformer t) {
        return new TransConstBoolean(value);
    }

    @Override
    public Type<BooleanVariable> getOutputType() {
        return VariableType.BooleanVariable;
    }

    @Override
    public void traverseTree(TreeVisitor v) {}

    @Override
    public TransTreeReturn<BooleanVariable> maxValue(Bounds bounds) {
        throw new CompilerException("Max value is not supported on boolean values");
    }

    @Override
    public TransTreeReturn<BooleanVariable> minValue(Bounds bounds) {
        throw new CompilerException("Min value is not supported on boolean values");
    }
}
