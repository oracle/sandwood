/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransStore;
import org.sandwood.compiler.trees.transformationTree.TransTree;

public class IRStore<X extends Variable<X>> extends IRTreeVoid {

    public final VariableDescription<X> varDesc;
    private final IRTreeReturn<X> value;

    IRStore(VariableDescription<X> varDesc, IRTreeReturn<X> value, String comment) {
        super(IRTreeType.STORE, comment);
        this.varDesc = varDesc;
        this.value = value;
    }

    @Override
    public IRTree[] getChildren() {
        return new IRTree[] { value };
    }

    @Override
    public String getDescription() {
        return varDesc + " = value";
    }

    @Override
    public TransStore<X> toTransformationTree() {
        return TransTree.store(varDesc, value.toTransformationTree(), comment);
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((varDesc == null) ? 0 : varDesc.hashCode());
        result = prime * result + ((value == null) ? 0 : value.equivalentHashCode());
        return result;
    }

    @Override
    public boolean equivalent(IRTree tree) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        IRStore<?> other = (IRStore<?>) tree;
        if(!varDesc.equals(other.varDesc))
            return false;
        return value.equivalent(other.value);
    }

    @Override
    public IRStore<X> applyTransformation(TreeTransformation t) {
        return new IRStore<>(varDesc, t.transformReturn(value), comment);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(value);
    }
}
