/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import java.util.ArrayList;
import java.util.List;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.ArrayType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransNewArray;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;

public class IRNewArray<X extends Variable<X>> extends IRTreeReturn<ArrayVariable<X>> {

    private final List<IRTreeReturn<IntVariable>> lengths = new ArrayList<>();
    private final ArrayType<X> varType;

    IRNewArray(IRTreeReturn<IntVariable> length, ArrayType<X> varType) {
        super(IRTreeType.ALLOCATE_ARRAY);
        lengths.add(length);
        this.varType = varType;
    }

    IRNewArray(List<IRTreeReturn<IntVariable>> lengths, ArrayType<X> varType) {
        super(IRTreeType.ALLOCATE_ARRAY);
        this.lengths.addAll(lengths);
        this.varType = varType;
    }

    @Override
    public IRTree[] getChildren() {
        IRTree[] trees = new IRTree[lengths.size()];
        lengths.toArray(trees);
        return trees;
    }

    @Override
    public String getDescription() {
        return "Construct array of varType:" + varType;
    }

    @Override
    public TransNewArray<X> toTransformationTree() {
        List<TransTreeReturn<IntVariable>> transLengths = new ArrayList<>();
        for(IRTreeReturn<IntVariable> length:lengths)
            transLengths.add(length.toTransformationTree());
        return TransTree.newArray(transLengths, varType);
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = 1;
        for(IRTreeReturn<IntVariable> length:lengths)
            result = prime * result + length.equivalentHashCode();
        result = prime * result + varType.hashCode();
        return result;
    }

    @Override
    public boolean equivalent(IRTree tree) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        IRNewArray<?> other = (IRNewArray<?>) tree;
        if(!varType.equals(other.varType))
            return false;
        if(lengths.size() != other.lengths.size())
            return false;
        for(int i = 0; i < lengths.size(); i++)
            if(!lengths.get(i).equivalent(other.lengths.get(i)))
                return false;
        return true;
    }

    @Override
    public IRTreeReturn<ArrayVariable<X>> applyTransformation(TreeTransformation t) {
        List<IRTreeReturn<IntVariable>> transLengths = new ArrayList<>();
        for(IRTreeReturn<IntVariable> length:lengths)
            transLengths.add(t.transformReturn(length));
        return new IRNewArray<>(transLengths, varType);
    }

    @Override
    public Type<ArrayVariable<X>> getOutputType() {
        return varType;
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        for(IRTreeReturn<IntVariable> length:lengths)
            v.visit(length);
    }
}
