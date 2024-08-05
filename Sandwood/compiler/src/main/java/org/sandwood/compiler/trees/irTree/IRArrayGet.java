/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.ArrayType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransArrayGet;
import org.sandwood.compiler.trees.transformationTree.TransTree;

public class IRArrayGet<X extends Variable<X>> extends IRTreeReturn<X> {
    public final IRTreeReturn<ArrayVariable<X>> array;
    private final IRTreeReturn<IntVariable> index;

    IRArrayGet(IRTreeReturn<ArrayVariable<X>> array, IRTreeReturn<IntVariable> index) {
        super(IRTreeType.ARRAY_GET);
        assert array != null : "Construction argument \"array\" equal to null";
        assert array.getOutputType() instanceof ArrayType;
        assert index != null : "Construction argument \"index\" equal to null";
        this.array = array;
        this.index = index;
    }

    @Override
    public IRTree[] getChildren() {
        return new IRTree[] { array, index };
    }

    @Override
    public String getDescription() {
        return "array[index]";
    }

    @Override
    public TransArrayGet<X> toTransformationTree() {
        return TransTree.arrayGet(array.toTransformationTree(), index.toTransformationTree());
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + array.equivalentHashCode();
        result = prime * result + index.equivalentHashCode();
        return result;
    }

    @Override
    public boolean equivalent(IRTree tree) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        IRArrayGet<?> other = (IRArrayGet<?>) tree;
        if(!array.equivalent(other.array))
            return false;
        return index.equivalent(other.index);
    }

    @Override
    public IRArrayGet<X> applyTransformation(TreeTransformation t) {
        return new IRArrayGet<>(t.transformReturn(array), t.transformReturn(index));
    }

    @Override
    public Type<X> getOutputType() {
        return ((ArrayType<X>) array.getOutputType()).getElementType();
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(array);
        v.visit(index);
    }
}
