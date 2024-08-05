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
import org.sandwood.compiler.trees.transformationTree.TransGetIntField;
import org.sandwood.compiler.trees.transformationTree.TransTree;

//TODO Rename to IRGetLength and tighten arguments, or think about how to tighten arguments in general.
public class IRGetIntField extends IRTreeReturn<IntVariable> {

    private final String name;
    private final IRTreeReturn<?> tree;

    IRGetIntField(IRTreeReturn<?> tree, String name) {
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
    public TransGetIntField toTransformationTree() {
        return TransTree.getIntField(tree.toTransformationTree(), name);
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
        IRGetIntField other = (IRGetIntField) tree;
        if(!name.equals(other.name))
            return false;
        return tree.equivalent(other.tree);
    }

    @Override
    public IRGetIntField applyTransformation(TreeTransformation t) {
        return new IRGetIntField(t.transformReturn(tree), name);
    }

    @Override
    public Type<IntVariable> getOutputType() {
        return VariableType.IntVariable;
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(tree);
    }
}
