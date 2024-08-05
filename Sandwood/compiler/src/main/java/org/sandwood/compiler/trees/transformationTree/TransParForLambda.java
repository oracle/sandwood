/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransParForLambda extends TransTreeVoid {

    public final int parDepth;
    public final VariableDescription<IntVariable> startDesc, endDesc;
    public final TransTreeVoid body;
    public final VariableDescription<IntVariable> threadID;

    private TransParForLambda(int parDepth, VariableDescription<IntVariable> startDesc,
            VariableDescription<IntVariable> endDesc, VariableDescription<IntVariable> threadID, TransTreeVoid body) {
        super(TransTreeType.PAR_FOR_LAMBDA, Tree.NoComment);
        this.parDepth = parDepth;
        this.startDesc = startDesc;
        this.endDesc = endDesc;
        this.body = body;
        this.threadID = threadID;
    }

    public static TransTreeVoid getParForLambda(int parDepth, VariableDescription<IntVariable> startName,
            VariableDescription<IntVariable> endName, VariableDescription<IntVariable> threadID, TransTreeVoid body) {
        if(body.type == TransTreeType.NOP)
            return body;
        else
            return new TransParForLambda(parDepth, startName, endName, threadID, body);
    }

    @Override
    public boolean equivalent(TransTree<?> tree, Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(tree.type != TransTreeType.PAR_FOR_LAMBDA)
            return false;
        TransParForLambda p = (TransParForLambda) tree;
        if(!startDesc.equals(p.startDesc) || !endDesc.equals(p.endDesc))
            return false;
        return body.equivalent(p.body, substitutions);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(body);
    }

    @Override
    public TransTreeVoid applyTransformationInternal(Transformer t) {
        return getParForLambda(parDepth, startDesc, endDesc, threadID, t.transform(body));
    }

    @Override
    public OutputTree toOutputTreeInternal() {
        return OutputTree.parForLambda(parDepth, startDesc, endDesc, threadID, body.toOutputTreeInternal());
    }

    @Override
    public TransTree<?>[] getChildren() {
        return new TransTree<?>[] { body };
    }

    @Override
    public String getDescription() {
        return "Parallel for Lambda:" + startDesc + ", " + endDesc;
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = body.equivalentHashCode();
        result = prime * result + endDesc.hashCode();
        result = prime * result + startDesc.hashCode();
        return result;
    }

    @Override
    public Set<String> declaredAllVariablesInternal() {
        return Collections.emptySet();
    }
}
