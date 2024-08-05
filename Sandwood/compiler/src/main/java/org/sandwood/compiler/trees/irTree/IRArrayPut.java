/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransArrayPut;
import org.sandwood.compiler.trees.transformationTree.TransTree;

public class IRArrayPut<X extends Variable<X>> extends IRTreeVoid {

    private final IRTreeReturn<ArrayVariable<X>> array;
    private final IRTreeReturn<IntVariable> index;
    private final IRTreeReturn<X> value;

    IRArrayPut(IRTreeReturn<ArrayVariable<X>> array, IRTreeReturn<IntVariable> index, IRTreeReturn<X> value,
            String comment) {
        super(IRTreeType.ARRAY_PUT, comment);
        this.array = array;
        this.index = index;
        this.value = value;
    }

    @Override
    public IRTree[] getChildren() {
        return new IRTree[] { array, index, value };
    }

    @Override
    public String getDescription() {
        return "array[index] = value";
    }

    @Override
    public TransArrayPut<X> toTransformationTree() {
        return TransTree.arrayPut(array.toTransformationTree(), index.toTransformationTree(),
                value.toTransformationTree(), comment);
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + array.equivalentHashCode();
        result = prime * result + index.equivalentHashCode();
        result = prime * result + value.equivalentHashCode();
        return result;
    }

    @Override
    public boolean equivalent(IRTree tree) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        IRArrayPut<?> other = (IRArrayPut<?>) tree;
        if(!array.equivalent(other.array))
            return false;
        if(!index.equivalent(other.index))
            return false;
        return value.equivalent(other.value);
    }

    @Override
    public IRArrayPut<X> applyTransformation(TreeTransformation t) {
        return new IRArrayPut<>(t.transformReturn(array), t.transformReturn(index), t.transformReturn(value), comment);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(array);
        v.visit(index);
        v.visit(value);
    }
}
