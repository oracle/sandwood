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
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransParFor extends TransTreeVoid {

    private final int parDepth;
    private final TransTreeReturn<IntVariable> start, end, step;
    private final TransParForLambda body;

    private TransParFor(int parDepth, TransTreeReturn<IntVariable> start, TransTreeReturn<IntVariable> end,
            TransTreeReturn<IntVariable> step, TransParForLambda body, String comment) {
        super(TransTreeType.FORK_JOIN_FOR, comment);
        this.parDepth = parDepth;
        this.start = start;
        this.end = end;
        this.step = step;
        this.body = body;
    }

    public static TransTreeVoid getParFor(int parDepth, TransTreeReturn<IntVariable> start,
            TransTreeReturn<IntVariable> end, TransTreeReturn<IntVariable> step, TransTreeVoid body, String comment) {
        if(body.type == TransTreeType.NOP)
            return body;
        else
            return new TransParFor(parDepth, start, end, step, (TransParForLambda) body, comment);
    }

    @Override
    public boolean equivalent(TransTree<?> tree, Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(tree.type != TransTreeType.FORK_JOIN_FOR)
            return false;
        TransParFor other = (TransParFor) tree;

        return body.equivalent(other.body, substitutions) && end.equivalent(other.end, substitutions)
                && start.equivalent(other.start, substitutions) && step.equivalent(other.step, substitutions);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(start);
        v.visit(end);
        v.visit(step);
        v.visit(body);
    }

    @Override
    public TransTreeVoid applyTransformationInternal(Transformer t) {
        return getParFor(parDepth, t.transform(start), t.transform(end), t.transform(step), t.transform(body), comment);
    }

    @Override
    public OutputTree toOutputTreeInternal() {
        return OutputTree.ForkJoinFor(parDepth, start.toOutputTreeReturnInternal(),
                end.collapseConstants().toOutputTreeReturnInternal(), step.toOutputTreeReturnInternal(),
                body.toOutputTreeInternal(), comment);
    }

    @Override
    public TransTree<?>[] getChildren() {
        return new TransTree<?>[] { start, end, step, body };
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = body.equivalentHashCode();
        result = prime * result + end.equivalentHashCode();
        result = prime * result + start.equivalentHashCode();
        result = prime * result + step.equivalentHashCode();
        return result;
    }

    @Override
    public String getDescription() {
        return "ParFor(Start, End, Step): body";
    }

    @Override
    public Set<String> declaredAllVariablesInternal() {
        return body.declaredAllVariables();
    }

}
