/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.outputTree.OutputTree.OutputTreeType;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

/**
 * Class to implement a block of scope with limited scope. *
 */
public class TransTreeScope extends TransTreeVoid {

    public final TransTreeVoid tree;

    private TransTreeScope(TransTreeVoid tree, String comment) {
        super(TransTreeType.SCOPE, comment);
        this.tree = tree;
    }

    @Override
    public TransTreeVoid[] getChildren() {
        return new TransTreeVoid[] { tree };
    }

    @Override
    public String getDescription() {
        return "Scope";
    }

    @Override
    public OutputTree toOutputTreeInternal() {
        OutputTree t = tree.toOutputTreeInternal();
        if(t.type == OutputTreeType.NOP)
            return t;
        else
            return OutputTree.treeScope(t, comment);
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        result = prime * result + tree.equivalentHashCode();
        return result;
    }

    @Override
    public boolean equivalent(TransTree<?> t, Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == t)
            return true;
        if((t == null) || (type != t.type))
            return false;
        TransTreeScope other = (TransTreeScope) t;
        return tree.equivalent(other.tree, substitutions);
    }

    @Override
    public TransTreeVoid applyTransformationInternal(Transformer t) {
        return getScope(t.transform(tree), comment);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(tree);
    }

    @Override
    public Set<String> declaredAllVariablesInternal() {
        return tree.declaredAllVariables();
    }

    public static TransTreeVoid getScope(TransTreeVoid tree, String comment) {
        if(tree.type == TransTreeType.NOP)
            return tree;
        else
            return new TransTreeScope(tree, comment);
    }
}
