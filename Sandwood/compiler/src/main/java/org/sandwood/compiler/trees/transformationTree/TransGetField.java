/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.Map;

import org.sandwood.compiler.dataflowGraph.variables.ObjectVariable;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.outputTree.OutputTreeReturn;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransGetField<A extends ObjectVariable<A>, X extends Variable<X>> extends TransTreeReturn<X> {

    private final VariableDescription<X> fieldDesc;
    private final TransTreeReturn<A> objectTree;

    TransGetField(TransTreeReturn<A> objectTree, VariableDescription<X> fieldDesc) {
        super(TransTreeType.GET_FIELD, 1 + objectTree.size());
        this.fieldDesc = fieldDesc;
        this.objectTree = objectTree;
    }

    @Override
    public TransTree<?>[] getChildren() {
        return new TransTree<?>[] { objectTree };
    }

    @Override
    public String getDescription() {
        return "." + fieldDesc;
    }

    @Override
    public OutputTreeReturn<X> toOutputTreeReturnInternal() {
        return OutputTree.getField(objectTree.toOutputTreeReturnInternal(), fieldDesc);
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        result = prime * result + fieldDesc.hashCode();
        result = prime * result + objectTree.equivalentHashCode();
        return result;
    }

    @Override
    public boolean equivalentInternal(TransTree<?> otherTree,
            Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == otherTree)
            return true;
        if((otherTree == null) || (type != otherTree.type))
            return false;
        TransGetField<?,?> other = (TransGetField<?,?>) otherTree;
        if(!fieldDesc.equals(other.fieldDesc))
            return false;
        return this.objectTree.equivalent(other.objectTree, substitutions);
    }

    /*
     * This should just be applyTransformation, but the cast and the alternative method are required to convince the
     * oracle compiler that it is type safe.
     */
    @Override
    public TransGetField<A,X> applyTransformationInternal(Transformer t) {
        // Cast included to keep Oracle compiler happy.
        return new TransGetField<>(t.transform(objectTree), fieldDesc);
    }

    @Override
    public Type<X> getOutputType() {
        return fieldDesc.type;
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(objectTree);
    }

    @Override
    public TransTreeReturn<X> maxValue(Bounds bounds) {
        return this;
    }

    @Override
    public TransTreeReturn<X> minValue(Bounds bounds) {
        // Only return for array lengths when all values are being returned.
        if(fieldDesc.name.getName().equals("length") && objectTree.getOutputType().isArray()) {
            TransTreeReturn<IntVariable> tree = constant(0);
            bounds.addTransformedTree(this, tree);
            return (TransTreeReturn<X>) tree;
        } else
            return this;
    }
}
