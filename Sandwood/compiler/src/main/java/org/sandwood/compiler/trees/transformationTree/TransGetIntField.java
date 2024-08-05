/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.Map;

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.outputTree.OutputGetIntField;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

//TODO Rename to TransGetLength and tighten arguments, or think about how to tighten arguments in general.
public class TransGetIntField extends TransTreeReturn<IntVariable> {

    private final String name;
    private final TransTreeReturn<?> tree;

    TransGetIntField(TransTreeReturn<?> tree, String name) {
        super(TransTreeType.GET_FIELD, 1 + tree.treeSize());
        this.name = name;
        this.tree = tree;
    }

    @Override
    public TransTree<?>[] getChildren() {
        return new TransTree<?>[] { tree };
    }

    @Override
    public String getDescription() {
        return "." + name;
    }

    @Override
    public OutputGetIntField toOutputTreeReturnInternal() {
        return OutputTree.getIntField(tree.toOutputTreeReturnInternal(), name);
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        result = prime * result + name.hashCode();
        result = prime * result + tree.equivalentHashCode();
        return result;
    }

    @Override
    public boolean equivalent(TransTree<?> otherTree, Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == otherTree)
            return true;
        if((otherTree == null) || (type != otherTree.type))
            return false;
        TransGetIntField other = (TransGetIntField) otherTree;
        if(!name.equals(other.name))
            return false;
        return this.tree.equivalent(other.tree, substitutions);
    }

    /*
     * This should just be applyTransformation, but the cast and the alternative method are required to convince the
     * oracle compiler that it is type safe.
     */
    @Override
    public TransGetIntField applyTransformationInternal(Transformer t) {
        // Cast included to keep Oracle compiler happy.
        return new TransGetIntField((TransTreeReturn<?>) t.transform(tree), name);
    }

    @Override
    public Type<IntVariable> getOutputType() {
        return VariableType.IntVariable;
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(tree);
    }

    @Override
    public TransTreeReturn<IntVariable> maxValue(Bounds bounds) {
        return this;
    }

    @Override
    public TransTreeReturn<IntVariable> minValue(Bounds bounds) {
        // Only return for array lengths when all values are being returned.
        if(bounds.getFilter() == null && name.equals("length") && tree.getOutputType().isArray()) {
            TransTreeReturn<IntVariable> tree = constant(0);
            bounds.addTransformedTree(this, tree);
            return tree;
        } else
            return this;
    }
}
