/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
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
import org.sandwood.compiler.trees.outputTree.OutputInitializeUnset;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransInitializeUnset<A extends Variable<A>> extends TransTreeVoid {

    public final VariableDescription<A> varDesc;
    private final Visibility visibility;

    TransInitializeUnset(Visibility visibility, VariableDescription<A> varDesc, String comment) {
        super(TransTreeType.INITIALIZE_UNSET, 1, comment);
        this.varDesc = varDesc;
        this.visibility = visibility;
    }

    @Override
    public TransTree<?>[] getChildren() {
        return new TransTree<?>[0];
    }

    @Override
    public String getDescription() {
        return "varType name;";
    }

    @Override
    public OutputInitializeUnset<A> toOutputTreeInternal() {
        return OutputTree.initializeUnsetVariable(visibility, varDesc, comment);
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        result = prime * result + varDesc.hashCode();
        result = prime * result + visibility.hashCode();
        return result;
    }

    @Override
    public boolean equivalentInternal(TransTree<?> tree,
            Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        TransInitializeUnset<?> other = (TransInitializeUnset<?>) tree;
        if(visibility != other.visibility)
            return false;
        VariableDescription<?> altVarDesc = substitutions.get(varDesc);
        if(altVarDesc == null)
            return varDesc.equals(other.varDesc);
        else
            return altVarDesc.equals(other.varDesc);
    }

    @Override
    public TransInitializeUnset<A> applyTransformationInternal(Transformer t) {
        return new TransInitializeUnset<>(visibility, varDesc, comment);
    }

    @Override
    public void traverseTree(TreeVisitor v) {}

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
