/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransNOP extends TransTreeVoid {

    public static final TransNOP nop = new TransNOP();

    private TransNOP() {
        super(TransTreeType.NOP, 0, Tree.NoComment);
    }

    @Override
    public OutputTree toOutputTreeInternal() {
        return OutputTree.nop();
    }

    @Override
    public TransTree<?>[] getChildren() {
        return new TransTree<?>[0];
    }

    @Override
    public String getDescription() {
        return "nop";
    }

    @Override
    public boolean equivalentInternal(TransTree<?> tree,
            Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        return tree == nop;
    }

    @Override
    public int equivalentHashCodeInternal() {
        return 634;
    }

    @Override
    public TransNOP applyTransformationInternal(Transformer t) {
        return this;
    }

    @Override
    public void traverseTree(TreeVisitor v) {}

    @Override
    public Set<String> declaredAllVariablesInternal() {
        return Collections.emptySet();
    }

}
