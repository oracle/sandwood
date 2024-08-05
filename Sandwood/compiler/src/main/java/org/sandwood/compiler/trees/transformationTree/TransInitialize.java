/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.trees.Visibility;
import org.sandwood.compiler.trees.outputTree.OutputInitialize;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransInitialize<A extends Variable<A>> extends TransTreeVoid {

    public final VariableDescription<A> varDesc;
    public final TransTreeReturn<A> value;
    public final Visibility visibility;

    TransInitialize(Visibility visibility, VariableDescription<A> varDesc, TransTreeReturn<A> value, String comment) {
        super(TransTreeType.INITIALIZE, comment);
        this.varDesc = varDesc;
        this.value = value;
        this.visibility = visibility;
    }

    @Override
    public TransTree<?>[] getChildren() {
        return new TransTree<?>[] { value };
    }

    @Override
    public String getDescription() {
        return "varType name = value;";
    }

    @Override
    public OutputInitialize<A> toOutputTreeInternal() {
        return OutputTree.initializeVariable(visibility, value.getOutputType(), varDesc,
                value.toOutputTreeReturnInternal(), comment);
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        result = prime * result + varDesc.hashCode();
        result = prime * result + value.equivalentHashCode();
        result = prime * result + ((visibility != null) ? visibility.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equivalent(TransTree<?> tree, Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        TransInitialize<?> other = (TransInitialize<?>) tree;
        if(visibility != other.visibility)
            return false;
        VariableDescription<?> altVarDesc = substitutions.get(varDesc);
        if(altVarDesc == null) {
            if(!varDesc.equals(other.varDesc))
                return false;
        } else {
            if(!altVarDesc.equals(other.varDesc))
                return false;
        }
        return value.equivalent(other.value, substitutions);
    }

    @Override
    public TransInitialize<A> applyTransformationInternal(Transformer t) {
        return new TransInitialize<>(visibility, varDesc, t.transform(value), comment);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(value);
    }

    @Override
    public Set<String> declaredLocalVariables() {
        Set<String> s = new HashSet<>();
        s.add(varDesc.name.getName());
        return s;
    }

    @Override
    public Set<String> declaredAllVariablesInternal() {
        Set<String> s = new HashSet<>();
        s.add(varDesc.name.getName());
        return s;
    }
}
