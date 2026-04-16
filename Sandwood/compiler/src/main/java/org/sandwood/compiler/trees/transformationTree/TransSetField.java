/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.ObjectVariable;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransSetField<A extends ObjectVariable<A>, X extends Variable<X>> extends TransTreeVoid {

    private final TransTreeReturn<A> objectTree;
    private final VariableDescription<X> fieldDesc;
    private final TransTreeReturn<X> value;

    public TransSetField(TransTreeReturn<A> objectTree, VariableDescription<X> fieldDesc, TransTreeReturn<X> value,
            String comment) {
        super(TransTreeType.SET_FIELD, 1 + objectTree.size() + value.size(), comment);
        this.objectTree = objectTree;
        Objects.nonNull(fieldDesc);
        this.fieldDesc = fieldDesc;
        this.value = value;
    }

    @Override
    protected Set<String> declaredAllVariablesInternal() {
        return Collections.emptySet();
    }

    @Override
    protected int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        result = prime * result + objectTree.equivalentHashCode();
        result = prime * result + fieldDesc.hashCode();
        result = prime * result + value.equivalentHashCode();
        return result;
    }

    @Override
    protected boolean equivalentInternal(TransTree<?> tree,
            Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        TransSetField<?,?> other = (TransSetField<?,?>) tree;
        VariableDescription<?> altFieldDesc = substitutions.get(fieldDesc);
        if(altFieldDesc == null) {
            if(!fieldDesc.equals(other.fieldDesc))
                return false;
        } else {
            if(!altFieldDesc.equals(other.fieldDesc))
                return false;
        }
        if(!objectTree.equivalent(other.objectTree))
            return false;
        return value.equivalent(other.value);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(objectTree);
        v.visit(value);
    }

    @Override
    protected TransTreeVoid applyTransformationInternal(Transformer t) {
        return new TransSetField<>(t.transform(objectTree), fieldDesc, t.transform(value), comment);
    }

    @Override
    public OutputTree toOutputTreeInternal() {
        return OutputTree.setField(objectTree.toOutputTreeReturnInternal(), fieldDesc, value.toOutputTreeReturnInternal(), comment);
    }

    @Override
    public TransTree<?>[] getChildren() {
        return new TransTree<?>[] { objectTree, value };
    }

    @Override
    public String getDescription() {
        return "." + fieldDesc + " = value";
    }

}
