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

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.outputTree.OutputTree.OutputTreeType;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransFor extends TransTreeVoid {

    public final TransTreeVoid body;
    public final TransTreeReturn<IntVariable> start;
    public final TransTreeReturn<IntVariable> end;
    public final TransTreeReturn<IntVariable> step;
    public final VariableDescription<IntVariable> indexDesc;
    public final boolean parallel;
    public final boolean incrementing;

    public static TransTreeVoid getFor(TransTreeVoid body, TransTreeReturn<IntVariable> start,
            TransTreeReturn<IntVariable> end, TransTreeReturn<IntVariable> step,
            VariableDescription<IntVariable> indexDesc, boolean parallel, boolean incrementing, String comment) {
        if(body.type == TransTreeType.NOP)
            return body;
        else
            return new TransFor(body, start, end, step, indexDesc, parallel, incrementing, comment);
    }

    private TransFor(TransTreeVoid body, TransTreeReturn<IntVariable> start, TransTreeReturn<IntVariable> end,
            TransTreeReturn<IntVariable> step, VariableDescription<IntVariable> indexDesc, boolean parallel,
            boolean incrementing, String comment) {
        super(TransTreeType.FOR, comment);
        this.body = body;
        this.start = start;
        this.end = end;
        this.step = step;
        this.indexDesc = indexDesc;
        this.parallel = parallel;
        this.incrementing = incrementing;
        assert end != null;
    }

    @Override
    public int scopeId(int scopeId) {
        return scopeId + 1;
    }

    @Override
    public TransTree<?>[] getChildren() {
        return new TransTree<?>[] { start, end, step, body };
    }

    @Override
    public String getDescription() {
        return incrementing
                ? "for(" + indexDesc + " = start; " + indexDesc + "!=end; " + indexDesc + " += step)\n{ body }"
                : "for(" + indexDesc + " = start; " + indexDesc + "!=end; " + indexDesc + " -= step)\n{ body }";
    }

    @Override
    public OutputTree toOutputTreeInternal() {
        OutputTree bodyTree = body.toOutputTreeInternal();
        if(bodyTree.type == OutputTreeType.NOP)
            return bodyTree; // NOP
        else {
            if(incrementing) {
                int endSize = end.treeSize();
                TransTreeReturn<IntVariable> altEnd = addII(end, constant(1)).collapseConstants();
                int altEndSize = altEnd.treeSize();
                if(altEndSize <= endSize)
                    return OutputTree.forStmt(bodyTree, start.toOutputTreeReturnInternal(),
                            altEnd.toOutputTreeReturnInternal(), step.toOutputTreeReturnInternal(), indexDesc,
                            incrementing, true, comment);
                else
                    return OutputTree.forStmt(bodyTree, start.toOutputTreeReturnInternal(),
                            end.toOutputTreeReturnInternal(), step.toOutputTreeReturnInternal(), indexDesc,
                            incrementing, false, comment);
            } else {
                return OutputTree.forStmt(bodyTree, start.toOutputTreeReturnInternal(),
                        end.toOutputTreeReturnInternal(), step.toOutputTreeReturnInternal(), indexDesc, incrementing,
                        false, comment);
            }
        }
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        result = prime * result + body.equivalentHashCode();
        result = prime * result + end.equivalentHashCode();
        result = prime * result + indexDesc.hashCode();
        result = prime * result + start.equivalentHashCode();
        result = prime * result + step.equivalentHashCode();
        result = prime * result + (parallel ? 1 : 0);
        result = prime * result + (incrementing ? 1 : 0);
        return result;
    }

    @Override
    public boolean equivalent(TransTree<?> tree, Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        TransFor other = (TransFor) tree;
        if(!body.equivalent(other.body, substitutions))
            return false;
        if(!end.equivalent(other.end, substitutions))
            return false;
        if(!indexDesc.equals(other.indexDesc)) {
            VariableDescription<?> altIndexDesc = substitutions.get(indexDesc);
            if(altIndexDesc == null || !altIndexDesc.equals(other.indexDesc))
                return false;
        }
        if(parallel != other.parallel)
            return false;
        if(!start.equivalent(other.start, substitutions))
            return false;
        if(!step.equivalent(other.step, substitutions))
            return false;
        return incrementing == other.incrementing;
    }

    @Override
    public TransTreeVoid applyTransformationInternal(Transformer t) {
        return getFor(t.transform(body), t.transform(start), t.transform(end), t.transform(step), indexDesc, parallel,
                incrementing, comment);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(start);
        v.visit(end);
        v.visit(step);
        v.visit(body);
    }

    @Override
    public Set<String> declaredAllVariablesInternal() {
        Set<String> s = new HashSet<>(body.declaredAllVariables());
        s.add(indexDesc.name.getName());
        return s;
    }
}
