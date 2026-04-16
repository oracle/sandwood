/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.dataflowGraph.variables.ObjectVariable;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;

public class IRGetField<A extends ObjectVariable<A>, X extends Variable<X>> extends IRTreeReturn<X> {

    private final VariableDescription<X> name;
    private final IRTreeReturn<A> tree;

    IRGetField(IRTreeReturn<A> tree, VariableDescription<X> name) {
        super(IRTreeType.GET_FIELD);
        this.name = name;
        this.tree = tree;
    }

    @Override
    public IRTree[] getChildren() {
        return new IRTree[] { tree };
    }

    @Override
    public String getDescription() {
        return "." + name;
    }

    @Override
    public TransTreeReturn<X> toTransformationTree() {
        return TransTree.getField(tree.toTransformationTree(), name);
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + name.hashCode();
        result = prime * result + tree.equivalentHashCode();
        return result;
    }

    @Override
    public boolean equivalent(IRTree tree) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        IRGetField<?,?> other = (IRGetField<?,?>) tree;
        if(!name.equals(other.name))
            return false;
        return tree.equivalent(other.tree);
    }

    @Override
    public IRTreeReturn<X> applyTransformation(TreeTransformation t) {
        return new IRGetField<>(t.transformReturn(tree), name);
    }

    @Override
    public Type<X> getOutputType() {
        return name.type;
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(tree);
    }
}
