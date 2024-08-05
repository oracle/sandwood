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

import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;

/**
 * A class that accepts trees and forwards calls from its parents to its children. This class is used to allow the tree
 * to be constructed out of order.
 */
public class IRProxyTreeSeq extends IRTreeVoid {

    private final List<IRTreeVoid> trees;

    IRProxyTreeSeq(String comment) {
        super(IRTreeType.PROXY_SEQ, comment);
        trees = new ArrayList<>();
    }

    private IRProxyTreeSeq(List<IRTreeVoid> trees, String comment) {
        super(IRTreeType.PROXY_SEQ, comment);
        this.trees = trees;
    }

    public IRTreeVoid getTree() {
        IRTreeVoid[] ts = getChildren();
        if(ts.length > 1)
            return sequential(ts, comment);
        else if(ts.length == 1) {
            IRTreeVoid t = ts[0];
            t.prefixComment(comment);
            return t;
        } else
            return null;
    }

    @Override
    public IRTreeVoid[] getChildren() {
        IRTreeVoid[] ts = new IRTreeVoid[trees.size()];
        int i = 0;
        for(IRTreeVoid t:trees)
            ts[i++] = t;
        return ts;
    }

    @Override
    public String getDescription() {
        return "Proxy";
    }

    public void addTree(IRTreeVoid t) {
        testTree(t, this);
        trees.add(t);
    }

    public void addTrees(List<IRTreeVoid> ts) {
        for(IRTreeVoid t:ts)
            addTree(t);
    }

    public static void testTree(IRTree t, IRTree start) {
        if(t == start)
            System.out.println("There is a cycle in the IRTree structure");
        // This is the only way to get a cycle, so if there is a cycle it starts here.
        if(t.type == IRTreeType.PROXY_SEQ) {
            List<IRTreeVoid> proxyTrees = ((IRProxyTreeSeq) t).trees;
            for(IRTreeVoid proxyTree:proxyTrees)
                testTree(proxyTree, start);
        }

        if(t.type == IRTreeType.FOR) {
            testTree(((IRFor) t).body, start);
        }
    }

    /**
     * Method to get the tree currently represented by this proxy tree.
     * 
     * @return Returns the tree currently represented by this proxy tree.
     */
    @Override
    public TransTreeVoid toTransformationTree() {
        IRTreeVoid t = getTree();
        if(t == null)
            return TransTree.nop();
        else
            return t.toTransformationTree();
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = 1;
        for(IRTreeVoid tree:trees)
            result = prime * result + tree.equivalentHashCode();
        return result;
    }

    @Override
    public boolean equivalent(IRTree tree) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        IRProxyTreeSeq other = (IRProxyTreeSeq) tree;
        int noTrees = trees.size();
        if(noTrees != other.trees.size())
            return false;
        for(int i = 0; i < noTrees; i++)
            if(!trees.get(i).equivalent(other.trees.get(i)))
                return false;
        return true;
    }

    @Override
    public IRProxyTreeSeq applyTransformation(TreeTransformation t) {
        List<IRTreeVoid> ts = new ArrayList<>();

        for(IRTreeVoid tree:trees)
            ts.add(t.transform(tree));

        return new IRProxyTreeSeq(ts, comment);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        for(IRTree tree:trees)
            v.visit(tree);
    }
}
