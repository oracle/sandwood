/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.binop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.outputTree.OutputTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransConstBoolean;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.WrappedTransReturn;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransAnd extends TransTreeReturn<BooleanVariable> {
    private final Set<WrappedTransReturn<BooleanVariable>> constraints = new LinkedHashSet<>();

    private TransAnd(List<WrappedTransReturn<BooleanVariable>> constraints) {
        super(TransTreeType.AND, treeSize(constraints));
        assert (constraints.size() > 1);
        this.constraints.addAll(constraints);
    }

    public static TransTreeReturn<BooleanVariable> getTransAnd(List<TransTreeReturn<BooleanVariable>> constraints) {
        List<WrappedTransReturn<BooleanVariable>> toPass = new ArrayList<>();
        for(TransTreeReturn<BooleanVariable> t:constraints) {
            if(t.type == TransTreeType.AND) {
                Set<WrappedTransReturn<BooleanVariable>> andConstraints = ((TransAnd) t).constraints;
                toPass.addAll(andConstraints);
            } else {
                if(t.type == TransTreeType.CONST_BOOLEAN) {
                    if(!((TransConstBoolean) t).value)
                        return TransTree.constant(false);
                } else
                    toPass.add(new WrappedTransReturn<>(t));
            }
        }

        if(toPass.size() == 0)
            return TransTree.constant(true);
        if(toPass.size() == 1)
            return toPass.get(0).tree;
        else
            return new TransAnd(toPass);
    }

    private static int treeSize(List<WrappedTransReturn<BooleanVariable>> constraints) {
        int treeSize = constraints.size() - 1;
        for(WrappedTransReturn<BooleanVariable> w:constraints)
            treeSize += w.tree.treeSize();
        return treeSize;
    }

    @Override
    public OutputTreeReturn<BooleanVariable> toOutputTreeReturnInternal() {
        Iterator<WrappedTransReturn<BooleanVariable>> iter = constraints.iterator();
        OutputTreeReturn<BooleanVariable> toReturn = iter.next().tree.toOutputTreeReturnInternal();
        while(iter.hasNext())
            toReturn = OutputTree.and(toReturn, iter.next().tree.toOutputTreeReturnInternal());
        return toReturn;
    }

    @Override
    public TransTreeReturn<BooleanVariable> applyTransformationInternal(Transformer t) {
        List<TransTreeReturn<BooleanVariable>> cs = new ArrayList<>();
        for(WrappedTransReturn<BooleanVariable> c:constraints)
            cs.add(t.transform(c.tree));
        return getTransAnd(cs);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        for(WrappedTransReturn<BooleanVariable> t:constraints)
            v.visit(t.tree);

    }

    @Override
    public TransTree<?>[] getChildren() {
        TransTree<?>[] toReturn = new TransTree<?>[constraints.size()];
        int i = 0;
        for(WrappedTransReturn<BooleanVariable> w:constraints)
            toReturn[i++] = w.tree;
        return toReturn;
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        result = prime * result + constraints.hashCode();
        return result;
    }

    @Override
    public String getDescription() {
        return "And";
    }

    @Override
    public boolean equivalent(TransTree<?> tree, Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(type != tree.type)
            return false;
        TransAnd andTree = (TransAnd) tree;
        int size = constraints.size();
        if(andTree.constraints.size() != size)
            return false;
        for(WrappedTransReturn<BooleanVariable> t:constraints)
            if(!andTree.constraints.contains(t)) {
                if(substitutions.isEmpty())
                    return false;
                else {
                    boolean found = false;
                    for(WrappedTransReturn<BooleanVariable> w:andTree.constraints) {
                        found = t.tree.equivalent(w.tree, substitutions);
                        if(found)
                            break;
                    }
                    if(!found)
                        return false;
                }
            }
        return true;
    }

    @Override
    public Type<BooleanVariable> getOutputType() {
        return VariableType.BooleanVariable;
    }

    @Override
    public TransTreeReturn<BooleanVariable> maxValue(Bounds bounds) {
        throw new CompilerException("max value is not supported on boolean values");
    }

    @Override
    public TransTreeReturn<BooleanVariable> minValue(Bounds bounds) {
        throw new CompilerException("min value is not supported on boolean values");
    }

    public List<TransTreeReturn<BooleanVariable>> getConstraints() {
        List<TransTreeReturn<BooleanVariable>> toReturn = new ArrayList<>();
        for(WrappedTransReturn<BooleanVariable> t:constraints) {
            toReturn.add(t.tree);
        }
        return toReturn;
    }

    public List<WrappedTransReturn<BooleanVariable>> getWrappedConstraints() {
        return new ArrayList<>(constraints);
    }
}
