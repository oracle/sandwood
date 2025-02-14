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

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.trees.outputTree.OutputStore;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransStore<X extends Variable<X>> extends TransTreeVoid {

    public final VariableDescription<X> varDesc;
    public final TransTreeReturn<X> value;

    TransStore(VariableDescription<X> varDesc, TransTreeReturn<X> value, String comment) {
        super(TransTreeType.STORE, value.size() + 1, comment);
        this.varDesc = varDesc;
        this.value = value;
    }

    @Override
    public TransTree<?>[] getChildren() {
        return new TransTree<?>[] { value };
    }

    @Override
    public String getDescription() {
        return varDesc + " = value";
    }

    @Override
    public OutputStore<X> toOutputTreeInternal() {
        return OutputTree.store(varDesc, value.toOutputTreeReturnInternal(), comment);
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((varDesc == null) ? 0 : varDesc.hashCode());
        result = prime * result + ((value == null) ? 0 : value.equivalentHashCode());
        return result;
    }

    @Override
    public boolean equivalentInternal(TransTree<?> tree,
            Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        TransStore<?> other = (TransStore<?>) tree;
        VariableDescription<?> altVarDesc = substitutions.get(varDesc);
        if(altVarDesc == null) {
            if(!varDesc.equals(other.varDesc))
                return false;
        } else {
            if(!altVarDesc.equals(other.varDesc))
                return false;
        }
        return value.equivalent(other.value);
    }

    @Override
    public TransStore<X> applyTransformationInternal(Transformer t) {
        return new TransStore<>(varDesc, t.transform(value), comment);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(value);
    }

    @Override
    public Set<String> declaredAllVariablesInternal() {
        return Collections.emptySet();
    }
}
