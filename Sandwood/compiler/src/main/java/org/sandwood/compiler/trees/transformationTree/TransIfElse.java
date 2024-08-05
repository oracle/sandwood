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
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.trees.Tag;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.outputTree.OutputIfElse;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransIfElse extends TransTreeVoid {

    public final TransTreeReturn<BooleanVariable> condition;
    public final TransTreeVoid ifBody, elseBody;
    private final String elseComment;

    private TransIfElse(TransTreeReturn<BooleanVariable> condition, TransTreeVoid ifBody, String ifComment,
            TransTreeVoid elseBody, String elseComment, Set<Tag> tags) {
        super(TransTreeType.IF, ifComment);
        this.condition = condition;
        this.ifBody = ifBody;
        this.elseBody = elseBody;
        this.elseComment = elseComment;
        addTags(tags);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(condition);
        v.visit(ifBody);
        v.visit(elseBody);
    }

    @Override
    public TransTreeVoid applyTransformationInternal(Transformer t) {
        return getIfElse(t.transform(condition), t.transform(ifBody), comment, t.transform(elseBody), elseComment,
                tags);
    }

    @Override
    public OutputIfElse toOutputTreeInternal() {
        return OutputTree.ifElse(condition.toOutputTreeReturnInternal(), ifBody.toOutputTreeInternal(), comment,
                elseBody.toOutputTreeInternal(), elseComment);

    }

    @Override
    public TransTree<?>[] getChildren() {
        if(elseBody.type == TransTreeType.NOP) {
            return new TransTree<?>[] { condition, ifBody };
        } else {
            return new TransTree<?>[] { condition, ifBody, elseBody };
        }
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        result = prime * result + condition.equivalentHashCode();
        result = prime * result + ifBody.equivalentHashCode();
        result = prime * result + elseBody.equivalentHashCode();
        return result;
    }

    @Override
    public boolean equivalent(TransTree<?> tree, Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == tree)
            return true;
        if((tree == null) || (tree.type != TransTreeType.IF))
            return false;
        TransIfElse other = (TransIfElse) tree;
        if(!condition.equivalent(other.condition, substitutions))
            return false;
        if(!ifBody.equivalent(other.ifBody, substitutions))
            return false;
        return elseBody.equivalent(other.elseBody, substitutions);
    }

    @Override
    public String getDescription() {
        return "if(condition) then, else";
    }

    public static TransTreeVoid getIfElse(TransTreeReturn<BooleanVariable> condition, TransTreeVoid ifBody,
            String comment, Set<Tag> tags) {
        return getIfElse(condition, ifBody, comment, TransTree.nop(), Tree.NoComment, tags);
    }

    public static TransTreeVoid getIfElse(TransTreeReturn<BooleanVariable> condition, TransTreeVoid ifBody,
            String ifComment, TransTreeVoid elseBody, String elseComment, Set<Tag> tags) {
        // If the if body is a nop, invert the guard and swap the clauses.
        if(ifBody.type == TransTreeType.NOP) {
            TransTreeVoid temp = elseBody;
            elseBody = ifBody;
            ifBody = temp;
            condition = TransTree.negateBoolean(condition);
        }

        // If the if body is still a nop the whole thing is a nop.
        if(ifBody.type == TransTreeType.NOP)
            return TransTree.nop();

        return new TransIfElse(condition, ifBody, ifComment, elseBody, elseComment, tags);
    }

    public String getElseComment() {
        return elseComment;
    }

    @Override
    public Set<String> declaredAllVariablesInternal() {
        Set<String> s = new HashSet<>();
        s.addAll(ifBody.declaredAllVariables());
        s.addAll(elseBody.declaredAllVariables());
        return s;
    }
}
