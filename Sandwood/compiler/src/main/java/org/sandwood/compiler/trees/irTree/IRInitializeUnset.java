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
import org.sandwood.compiler.trees.Visibility;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransInitializeUnset;
import org.sandwood.compiler.trees.transformationTree.TransTree;

public class IRInitializeUnset<A extends Variable<A>> extends IRTreeVoid {

    private final VariableDescription<A> varDesc;
    private final Visibility visibility;

    IRInitializeUnset(Visibility visibility, VariableDescription<A> varDesc, String comment) {
        super(IRTreeType.INITIALIZE_UNSET, comment);
        this.varDesc = varDesc;
        this.visibility = visibility;
    }

    @Override
    public IRTree[] getChildren() {
        return new IRTree[0];
    }

    @Override
    public String getDescription() {
        return "varType name;";
    }

    @Override
    public TransInitializeUnset<A> toTransformationTree() {
        return TransTree.initializeUnsetVariable(visibility, varDesc, comment);
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + varDesc.hashCode();
        result = prime * result + visibility.hashCode();
        return result;
    }

    @Override
    public boolean equivalent(IRTree tree) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        IRInitializeUnset<?> other = (IRInitializeUnset<?>) tree;
        if(!varDesc.equals(other.varDesc))
            return false;
        return visibility == other.visibility;
    }

    @Override
    public IRInitializeUnset<A> applyTransformation(TreeTransformation t) {
        return new IRInitializeUnset<>(visibility, varDesc, comment);
    }

    @Override
    public void traverseTree(TreeVisitor v) {}
}
