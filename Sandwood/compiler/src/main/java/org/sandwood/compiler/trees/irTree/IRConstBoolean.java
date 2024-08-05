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
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransConstBoolean;
import org.sandwood.compiler.trees.transformationTree.TransTree;

public class IRConstBoolean extends IRTreeReturn<BooleanVariable> {

    public final boolean value;

    IRConstBoolean(Boolean value) {
        super(IRTreeType.CONST_BOOLEAN);
        this.value = value;
    }

    @Override
    public IRTree[] getChildren() {
        return new IRTree[0];
    }

    @Override
    public String getDescription() {
        return Boolean.toString(value);
    }

    @Override
    public TransConstBoolean toTransformationTree() {
        return TransTree.constant(value);
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value) ? 7 : 3);
        return result;
    }

    @Override
    public boolean equivalent(IRTree tree) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        IRConstBoolean other = (IRConstBoolean) tree;
        return value == other.value;
    }

    @Override
    public IRConstBoolean applyTransformation(TreeTransformation t) {
        return new IRConstBoolean(value);
    }

    @Override
    public Type<BooleanVariable> getOutputType() {
        return VariableType.BooleanVariable;
    }

    @Override
    public void traverseTree(TreeVisitor v) {}
}
