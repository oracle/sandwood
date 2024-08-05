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
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransLoad;
import org.sandwood.compiler.trees.transformationTree.TransTree;

public class IRLoad<X extends Variable<X>> extends IRTreeReturn<X> {

    public final VariableDescription<X> varDesc;

    IRLoad(VariableDescription<X> varDesc) {
        super(IRTreeType.LOAD);
        this.varDesc = varDesc;
    }

    @Override
    public IRTree[] getChildren() {
        return new IRTree[0];
    }

    @Override
    public String getDescription() {
        return varDesc.toString();
    }

    @Override
    public TransLoad<X> toTransformationTree() {
        return TransTree.load(varDesc);
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + varDesc.hashCode();
        return result;
    }

    @Override
    public boolean equivalent(IRTree tree) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        IRLoad<?> other = (IRLoad<?>) tree;
        return varDesc.equals(other.varDesc);
    }

    @Override
    public IRLoad<X> applyTransformation(TreeTransformation t) {
        return new IRLoad<>(varDesc);
    }

    @Override
    public Type<X> getOutputType() {
        return varDesc.type;
    }

    @Override
    public void traverseTree(TreeVisitor v) {}
    
    @Override
    public boolean returning(VariableDescription<X> varDesc) {
        return this.varDesc.equals(varDesc);
    }
}
