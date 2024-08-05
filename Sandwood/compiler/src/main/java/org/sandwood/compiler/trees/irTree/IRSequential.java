/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import java.util.ArrayList;
import java.util.List;

import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTree.TransTreeType;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;

public class IRSequential extends IRTreeVoid {

    public final IRTreeVoid[] trees;

    IRSequential(IRTreeVoid[] trees, String comment) {
        super(IRTreeType.SEQUENTIAL, comment);
        this.trees = trees;
        for(IRTree t:trees)
            if(t == null)
                throw new CompilerException("Null trees are not allowed");
    }

    @Override
    public IRTree[] getChildren() {
        return trees;
    }

    @Override
    public String getDescription() {
        return "seq";
    }

    @Override
    public TransTreeVoid toTransformationTree() {
        List<TransTreeVoid> outputTrees = new ArrayList<>(trees.length);

        for(IRTreeVoid tree:trees) {
            TransTreeVoid t = tree.toTransformationTree();
            if(t.type != TransTreeType.NOP)
                outputTrees.add(t);
        }

        if(outputTrees.size() == 0)
            return TransTree.nop();
        else if(outputTrees.size() == 1) {
            TransTreeVoid t = outputTrees.get(0);
            t.postfixComment(comment);
            return t;
        } else
            return TransTree.sequential(outputTrees, comment);
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = 1;
        for(IRTreeVoid t:trees)
            result = prime * result + t.equivalentHashCode();
        return result;
    }

    @Override
    public boolean equivalent(IRTree tree) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        IRSequential other = (IRSequential) tree;
        if(trees.length != other.trees.length)
            return false;
        for(int i = 0; i < trees.length; i++)
            if(!trees[i].equivalent(other.trees[i]))
                return false;
        return true;
    }

    @Override
    public IRSequential applyTransformation(TreeTransformation t) {
        int size = trees.length;
        IRTreeVoid[] ts = new IRTreeVoid[size];
        for(int i = 0; i < size; i++)
            ts[i] = t.transform(trees[i]);
        return new IRSequential(ts, comment);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        int size = trees.length;
        for(int i = 0; i < size; i++)
            v.visit(trees[i]);
    }
}
