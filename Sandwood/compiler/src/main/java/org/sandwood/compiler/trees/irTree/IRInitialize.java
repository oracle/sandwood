/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.trees.Visibility;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransInitialize;
import org.sandwood.compiler.trees.transformationTree.TransTree;

public class IRInitialize<A extends Variable<A>> extends IRTreeVoid {

    public final VariableDescription<A> varDesc;
    public final IRTreeReturn<A> value;
    public final Visibility visibility;

    IRInitialize(Visibility visibility, VariableDescription<A> varDesc, IRTreeReturn<A> value, String comment) {
        super(IRTreeType.INITIALIZE, comment);
        this.varDesc = varDesc;
        this.value = value;
        this.visibility = visibility;
        assert varDesc.type.equals(value.getOutputType());
    }

    @Override
    public IRTree[] getChildren() {
        return new IRTree[] { value };
    }

    @Override
    public String getDescription() {
        return "varType name = value;";
    }

    @Override
    public TransInitialize<A> toTransformationTree() {
        return TransTree.initializeVariable(visibility, varDesc, value.toTransformationTree(), comment);
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + varDesc.hashCode();
        result = prime * result + value.equivalentHashCode();
        result = prime * result + ((visibility != null) ? visibility.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equivalent(IRTree tree) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        IRInitialize<?> other = (IRInitialize<?>) tree;
        if(!varDesc.equals(other.varDesc))
            return false;
        if(!value.equivalent(other.value))
            return false;
        return visibility == other.visibility;
    }

    @Override
    public IRInitialize<A> applyTransformation(TreeTransformation t) {
        return new IRInitialize<>(visibility, varDesc, t.transformReturn(value), comment);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(value);
    }
}
