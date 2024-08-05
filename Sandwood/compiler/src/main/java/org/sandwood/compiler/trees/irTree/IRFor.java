/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTree.TransTreeType;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;

public class IRFor extends IRTreeVoid {

    // TODO private removed for debugging, add back in
    public final IRTreeVoid body;
    public final IRTreeReturn<IntVariable> start;
    public final IRTreeReturn<IntVariable> end;
    public final IRTreeReturn<IntVariable> step;
    public final VariableDescription<IntVariable> indexDesc;
    public final boolean parallel;
    public final boolean incrementing;

    IRFor(IRTreeVoid body, IRTreeReturn<IntVariable> start, IRTreeReturn<IntVariable> end,
            IRTreeReturn<IntVariable> step, VariableDescription<IntVariable> indexDesc, boolean parallel,
            boolean incrementing, String comment) {
        super(IRTreeType.FOR, comment);
        this.body = body;
        this.start = start;
        this.end = end;
        this.step = step;
        this.indexDesc = indexDesc;
        this.parallel = parallel;
        this.incrementing = incrementing;
        assert end != null;

        IRProxyTreeSeq.testTree(body, this);
    }

    @Override
    public int scopeId(int scopeId) {
        return scopeId + 1;
    }

    @Override
    public IRTree[] getChildren() {
        return new IRTree[] { start, end, step, body };
    }

    @Override
    public String getDescription() {
        return "for(" + indexDesc + " = value; " + indexDesc + "!=end; " + indexDesc + " += step)\n{ body }";
    }

    @Override
    public TransTreeVoid toTransformationTree() {
        TransTreeVoid bodyTree = body.toTransformationTree();
        if(bodyTree.type == TransTreeType.NOP)
            return bodyTree; // NOP
        else {
            // This includes a transformation from [start..end) to [start..end]
            // and if decrementing adjust end to be equal to start + n*step when n
            // is a non-negative integer. It is done here as that way we can be
            // sure it is only every applied once.
            IRTreeReturn<IntVariable> altEnd = (incrementing) ? subtractII(end, constant(1)) : addII(end, constant(1));
            return TransTree.forStmt(bodyTree, start.toTransformationTree(), altEnd.toTransformationTree(),
                    step.toTransformationTree(), indexDesc, parallel, incrementing, comment);
        }
    }

    @Override
    public int equivalentHashCode() {
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
    public boolean equivalent(IRTree tree) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        IRFor other = (IRFor) tree;
        if(!body.equivalent(other.body))
            return false;
        if(!end.equivalent(other.end))
            return false;
        if(!indexDesc.equals(other.indexDesc))
            return false;
        if(parallel != other.parallel)
            return false;
        if(!start.equivalent(other.start))
            return false;
        if(!step.equivalent(other.step))
            return false;
        return incrementing == other.incrementing;
    }

    @Override
    public IRFor applyTransformation(TreeTransformation t) {
        return new IRFor(t.transform(body), t.transformReturn(start), t.transformReturn(end), t.transformReturn(step),
                indexDesc, parallel, incrementing, comment);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(body);
        v.visit(start);
        v.visit(end);
        v.visit(step);
    }
}
