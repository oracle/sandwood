/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;

public class IRIfElse extends IRTreeVoid {

    private final IRTreeReturn<BooleanVariable> condition;
    private final IRTreeVoid ifBody;
    private final IRTreeVoid elseBody;
    private final String elseComment;

    protected IRIfElse(IRTreeReturn<BooleanVariable> condition, IRTreeVoid ifBody, String ifComment,
            IRTreeVoid elseBody, String elseComment) {
        super(IRTreeType.IF_ELSE, ifComment);
        this.condition = condition;
        this.ifBody = ifBody;
        this.elseBody = elseBody;
        this.elseComment = elseComment;
        assert (condition != null);
        assert (ifBody != null);
        assert (elseBody != null);
    }

    protected IRIfElse(IRTreeReturn<BooleanVariable> condition, IRTreeVoid ifBody, String ifComment) {
        this(condition, ifBody, ifComment, IRTree.nop(), Tree.NoComment);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(condition);
        v.visit(ifBody);
        v.visit(elseBody);
    }

    @Override
    public IRTreeVoid applyTransformation(TreeTransformation t) {
        IRIfElse ifElse = new IRIfElse(t.transformReturn(condition), t.transform(ifBody), comment,
                t.transform(elseBody), elseComment);
        ifElse.addTags(tags);
        return ifElse;
    }

    @Override
    public TransTreeVoid toTransformationTree() {
        return TransTree.ifElse(condition.toTransformationTree(), ifBody.toTransformationTree(), comment,
                elseBody.toTransformationTree(), elseComment, tags);
    }

    @Override
    public IRTree[] getChildren() {
        if(elseBody == null) {
            return new IRTree[] { condition, ifBody };
        } else {
            return new IRTree[] { condition, ifBody, elseBody };
        }
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + condition.equivalentHashCode();
        result = prime * result + ifBody.equivalentHashCode();
        if(elseBody != null)
            result = prime * result + elseBody.equivalentHashCode();
        return result;
    }

    @Override
    public boolean equivalent(IRTree tree) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        IRIfElse other = (IRIfElse) tree;
        // TODO make this able to handle different orders of conditional statements.
        if(!condition.equivalent(other.condition))
            return false;
        if(!ifBody.equivalent(other.ifBody))
            return false;
        if(elseBody == null) {
            return other.elseBody == null;
        } else {
            return elseBody.equivalent(other.elseBody);
        }
    }

    @Override
    public String getDescription() {
        return "If(condition) ifBody [elseBody]";
    }
}
