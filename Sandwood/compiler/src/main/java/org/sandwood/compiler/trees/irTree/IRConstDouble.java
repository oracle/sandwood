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
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransConstDouble;
import org.sandwood.compiler.trees.transformationTree.TransTree;

public class IRConstDouble extends IRTreeReturn<DoubleVariable> {

    public final double value;

    IRConstDouble(double value) {
        super(IRTreeType.CONST_DOUBLE);
        this.value = value;
    }

    @Override
    public IRTree[] getChildren() {
        return new IRTree[0];
    }

    @Override
    public String getDescription() {
        return Double.toString(value);
    }

    @Override
    public TransConstDouble toTransformationTree() {
        return TransTree.constant(value);
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) value;
        return result;
    }

    @Override
    public boolean equivalent(IRTree tree) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        IRConstDouble other = (IRConstDouble) tree;
        return value == other.value || (Double.isNaN(value) && Double.isNaN(other.value));
    }

    @Override
    public IRConstDouble applyTransformation(TreeTransformation t) {
        return new IRConstDouble(value);
    }

    @Override
    public Type<DoubleVariable> getOutputType() {
        return VariableType.DoubleVariable;
    }

    @Override
    public void traverseTree(TreeVisitor v) {}
}
