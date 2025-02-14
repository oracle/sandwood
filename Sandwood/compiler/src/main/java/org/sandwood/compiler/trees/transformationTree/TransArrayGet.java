/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.Map;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.ArrayType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.outputTree.OutputArrayGet;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransArrayGet<X extends Variable<X>> extends TransTreeReturn<X> {
    public final TransTreeReturn<ArrayVariable<X>> array;
    public final TransTreeReturn<IntVariable> index;

    TransArrayGet(TransTreeReturn<ArrayVariable<X>> array, TransTreeReturn<IntVariable> index) {
        super(TransTreeType.ARRAY_GET, 1 + array.size() + index.size());
        assert array != null : "Construction argument \"array\" equal to null";
        assert array.getOutputType() instanceof ArrayType;
        assert index != null : "Construction argument \"index\" equal to null";
        this.array = array;
        this.index = index;
    }

    @Override
    public TransTree<?>[] getChildren() {
        return new TransTree<?>[] { array, index };
    }

    @Override
    public String getDescription() {
        return "array[index]";
    }

    @Override
    public OutputArrayGet<X> toOutputTreeReturnInternal() {
        return OutputTree.arrayGet(array.toOutputTreeReturnInternal(), index.toOutputTreeReturnInternal());
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        result = prime * result + array.equivalentHashCode();
        result = prime * result + index.equivalentHashCode();
        return result;
    }

    @Override
    public boolean equivalentInternal(TransTree<?> tree,
            Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        TransArrayGet<?> other = (TransArrayGet<?>) tree;
        if(!array.equivalent(other.array, substitutions))
            return false;
        return index.equivalent(other.index, substitutions);
    }

    @Override
    public TransArrayGet<X> applyTransformationInternal(Transformer t) {
        return new TransArrayGet<>(t.transform(array), t.transform(index));
    }

    @Override
    public Type<X> getOutputType() {
        ArrayType<X> vt = (ArrayType<X>) array.getOutputType();
        return vt.getElementType();
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(array);
        v.visit(index);
    }

    @Override
    public TransTreeReturn<X> maxValue(Bounds bounds) {
        return this;
    }

    @Override
    public TransTreeReturn<X> minValue(Bounds bounds) {
        return this;
    }
}
