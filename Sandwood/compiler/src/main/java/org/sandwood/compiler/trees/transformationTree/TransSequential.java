/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.outputTree.OutputTree.OutputTreeType;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransSequential extends TransTreeVoid {

    private final List<TransTreeVoid> trees = new ArrayList<>();

    private TransSequential(List<TransTreeVoid> trees, int size, String comment) {
        super(TransTreeType.SEQUENTIAL, size, comment);
        for(TransTreeVoid t:trees) {
            if(t == null)
                throw new CompilerException("Trees with the value null are not allowed as inputs.");

            if(t.type == TransTreeType.SEQUENTIAL) {
                TransSequential ts = (TransSequential) t;
                this.trees.addAll(ts.trees);
                ts.trees.get(0).prefixComment(ts.comment);
            } else
                this.trees.add(t);
        }
        assert (this.trees.size() > 1);
    }

    @Override
    public TransTreeVoid[] getChildren() {
        return trees.toArray(new TransTreeVoid[trees.size()]);
    }

    @Override
    public String getDescription() {
        return "seq";
    }

    @Override
    public OutputTree toOutputTreeInternal() {
        List<OutputTree> outputTrees = new ArrayList<>(trees.size());

        for(int i = 0; i < trees.size(); i++) {
            OutputTree t = trees.get(i).toOutputTreeInternal();
            if(t.type != OutputTreeType.NOP)
                outputTrees.add(t);
        }

        return OutputTree.sequential(outputTrees, comment);
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        for(TransTreeVoid tree:trees)
            result = result * prime + tree.equivalentHashCode();
        return result;
    }

    @Override
    public boolean equivalentInternal(TransTree<?> tree,
            Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        TransSequential other = (TransSequential) tree;
        if(trees.size() != other.trees.size())
            return false;
        for(int i = 0; i < trees.size(); i++)
            if(!trees.get(i).equivalent(other.trees.get(i), substitutions))
                return false;
        return true;
    }

    @Override
    public TransTreeVoid applyTransformationInternal(Transformer t) {
        int size = trees.size();
        TransTreeVoid[] ts = new TransTreeVoid[size];
        for(int i = 0; i < size; i++)
            ts[i] = t.transform(trees.get(i));
        return getTransSequential(ts, comment);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        int size = trees.size();
        for(int i = 0; i < size; i++)
            v.visit(trees.get(i));
    }

    static TransTreeVoid getTransSequential(List<TransTreeVoid> ts, String comment) {
        if(ts.size() == 0)
            return TransTree.nop();
        if(ts.size() == 1) {
            TransTreeVoid t = ts.get(0);
            t.prefixComment(comment);
            return t;
        }
        int size = 1;
        for(TransTreeVoid t:ts)
            size += t.size();
        return new TransSequential(ts, size, comment);
    }

    static TransTreeVoid getTransSequential(TransTreeVoid[] ts, String comment) {
        List<TransTreeVoid> trees = new ArrayList<>();
        for(TransTreeVoid t:ts)
            if(t.type != TransTreeType.NOP)
                trees.add(t);
        return getTransSequential(trees, comment);
    }

    public List<TransTreeVoid> getTrees() {
        return new ArrayList<>(trees);
    }

    @Override
    public Set<String> declaredLocalVariables() {
        Set<String> s = new HashSet<>();
        for(TransTreeVoid t:trees)
            s.addAll(t.declaredLocalVariables());
        return s;
    }

    @Override
    public Set<String> declaredAllVariablesInternal() {
        Set<String> s = new HashSet<>();
        for(TransTreeVoid t:trees)
            s.addAll(t.declaredAllVariables());
        return s;
    }
}
