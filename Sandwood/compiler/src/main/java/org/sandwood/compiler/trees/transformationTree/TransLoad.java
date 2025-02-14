/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.Map;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.trees.outputTree.OutputLoad;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransLoad<X extends Variable<X>> extends TransTreeReturn<X> {

    public final VariableDescription<X> varDesc;

    TransLoad(VariableDescription<X> varDesc) {
        super(TransTreeType.LOAD, 1);
        this.varDesc = varDesc;
    }

    @Override
    public TransTree<?>[] getChildren() {
        return new TransTree<?>[0];
    }

    @Override
    public String getDescription() {
        return varDesc.toString();
    }

    @Override
    public OutputLoad<X> toOutputTreeReturnInternal() {
        return OutputTree.load(varDesc);
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        result = prime * result + varDesc.hashCode();
        return result;
    }

    @Override
    public boolean equivalentInternal(TransTree<?> tree,
            Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        TransLoad<?> other = (TransLoad<?>) tree;
        VariableDescription<?> altVarDesc = substitutions.get(varDesc);
        if(altVarDesc == null)
            return varDesc.equals(other.varDesc);
        else
            return altVarDesc.equals(other.varDesc);
    }

    @Override
    public TransLoad<X> applyTransformationInternal(Transformer t) {
        return new TransLoad<>(varDesc);
    }

    @Override
    public Type<X> getOutputType() {
        return varDesc.type;
    }

    @Override
    public void traverseTree(TreeVisitor v) {}

    // TODO construct a sub class for loading numbers that contains these methods.
    @Override
    public TransTreeReturn<X> maxValue(Bounds bounds) {
        @SuppressWarnings("unchecked")
        // Get the maximum value of the returned tree.
        TransTreeReturn<X> t = (TransTreeReturn<X>) bounds.getMax(this);
        if(t != null)
            return t;
        else
            return this;
    }

    @Override
    public TransTreeReturn<X> minValue(Bounds bounds) {
        @SuppressWarnings("unchecked")
        // Get the minimum value of the returned tree.
        TransTreeReturn<X> t = (TransTreeReturn<X>) bounds.getMin(this);
        if(t != null)
            return t;
        else
            return this;
    }
}
