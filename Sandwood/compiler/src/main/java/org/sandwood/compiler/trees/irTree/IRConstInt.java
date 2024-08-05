/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransConstInt;
import org.sandwood.compiler.trees.transformationTree.TransTree;

public class IRConstInt extends IRTreeReturn<IntVariable> {

    public final int value;

    IRConstInt(int value) {
        super(IRTreeType.CONST_INT);
        this.value = value;
    }

    @Override
    public IRTree[] getChildren() {
        return new IRTree[0];
    }

    @Override
    public String getDescription() {
        return Integer.toString(value);
    }

    @Override
    public TransConstInt toTransformationTree() {
        return TransTree.constant(value);
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + value;
        return result;
    }

    @Override
    public boolean equivalent(IRTree tree) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        IRConstInt other = (IRConstInt) tree;
        return value == other.value;
    }

    @Override
    public IRConstInt applyTransformation(TreeTransformation t) {
        return new IRConstInt(value);
    }

    @Override
    public Type<IntVariable> getOutputType() {
        return VariableType.IntVariable;
    }

    @Override
    public void traverseTree(TreeVisitor v) {}
}
