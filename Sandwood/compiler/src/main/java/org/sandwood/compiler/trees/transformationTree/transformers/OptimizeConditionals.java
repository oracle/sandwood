/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.transformers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.transformationTree.TransIfElse;
import org.sandwood.compiler.trees.transformationTree.TransNegateBoolean;
import org.sandwood.compiler.trees.transformationTree.TransSequential;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTree.TransTreeType;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VariableTracking;

public class OptimizeConditionals extends Transformer {

    private final VariableTracking vars;

    public OptimizeConditionals(TransTree<?> tree) {
        vars = tree.getVariableTracking();
    }

    @Override
    protected TransTreeVoid transformVoid(TransTreeVoid tree) {
        switch(tree.type) {
            case SEQUENTIAL: {
                visitedNodes.add(tree);
                TransSequential seq = (TransSequential) tree;
                List<TransTreeVoid> originalTrees = seq.getTrees();
                List<TransTreeVoid> ts = new ArrayList<>();
                TransIfElse lastIfElse = null;
                Set<VariableDescription<?>> lastIfElseWrites = null;
                Set<VariableDescription<?>> lastIfElseArrayMods = null;
                for(TransTreeVoid t:originalTrees) {
                    if(t.type == TransTreeType.IF) {
                        visitedNodes.add(t);
                        TransIfElse newIfElse = (TransIfElse) t;
                        if(lastIfElse == null) {
                            lastIfElse = newIfElse;
                            lastIfElseWrites = vars.written(newIfElse);
                            lastIfElseArrayMods = vars.arraysModified(newIfElse);
                        } else {
                            if(lastIfElse.condition.equivalent(newIfElse.condition)) {
                                if(mergeable(lastIfElseWrites, lastIfElseArrayMods, lastIfElse, newIfElse)) {
                                    // Added here in case they are removed by the sequential statement merging the
                                    // values.
                                    visitedNodes.add(lastIfElse.ifBody);
                                    visitedNodes.add(lastIfElse.elseBody);
                                    visitedNodes.add(newIfElse.ifBody);
                                    visitedNodes.add(newIfElse.elseBody);

                                    TransTreeReturn<BooleanVariable> condition = lastIfElse.condition;
                                    newIfElse.condition.addNodes(visitedNodes);
                                    TransTreeVoid ifBody = TransTree.sequential(
                                            TransTree.treeScope(lastIfElse.ifBody, Tree.NoComment),
                                            TransTree.treeScope(newIfElse.ifBody, Tree.NoComment));
                                    String ifComment = mergeComments(lastIfElse.getComment(), newIfElse.getComment());
                                    String elseComment = mergeComments(lastIfElse.getElseComment(),
                                            newIfElse.getElseComment());

                                    TransTreeVoid elseBody = TransTree.sequential(
                                            TransTree.treeScope(lastIfElse.elseBody, Tree.NoComment),
                                            TransTree.treeScope(newIfElse.elseBody, Tree.NoComment));
                                    lastIfElse = (TransIfElse) TransTree.ifElse(condition, ifBody, ifComment, elseBody,
                                            elseComment, Collections.emptySet());
                                    lastIfElseWrites = new HashSet<>(lastIfElseWrites);
                                    lastIfElseWrites.addAll(vars.written(newIfElse));
                                    lastIfElseArrayMods = new HashSet<>(lastIfElseWrites);
                                    lastIfElseArrayMods.addAll(vars.arraysModified(newIfElse));
                                } else {
                                    ts.add(normalise(transform(lastIfElse)));
                                    lastIfElse = newIfElse;
                                    lastIfElseWrites = vars.written(newIfElse);
                                    lastIfElseArrayMods = vars.arraysModified(newIfElse);
                                }
                            } else if(// Test if either guard is the negation of the other.
                            (lastIfElse.condition.type == TransTreeType.NEGATE_BOOLEAN
                                    && ((TransNegateBoolean) lastIfElse.condition).input
                                            .equivalent(newIfElse.condition))
                                    || (newIfElse.condition.type == TransTreeType.NEGATE_BOOLEAN
                                            && ((TransNegateBoolean) newIfElse.condition).input
                                                    .equivalent(lastIfElse.condition))) {
                                if(mergeable(lastIfElseWrites, lastIfElseArrayMods, lastIfElse, newIfElse)) {
                                    // Added here in case they are removed by the sequential statement merging the
                                    // values.
                                    visitedNodes.add(lastIfElse.ifBody);
                                    visitedNodes.add(lastIfElse.elseBody);
                                    visitedNodes.add(newIfElse.ifBody);
                                    visitedNodes.add(newIfElse.elseBody);

                                    TransTreeReturn<BooleanVariable> condition = lastIfElse.condition;
                                    newIfElse.condition.addNodes(visitedNodes);
                                    TransTreeVoid ifBody = TransTree.sequential(
                                            TransTree.treeScope(lastIfElse.ifBody, Tree.NoComment),
                                            TransTree.treeScope(newIfElse.elseBody, Tree.NoComment));
                                    String ifComment = mergeComments(lastIfElse.getComment(),
                                            newIfElse.getElseComment());
                                    String elseComment = mergeComments(lastIfElse.getElseComment(),
                                            newIfElse.getComment());

                                    TransTreeVoid elseBody = TransTree.sequential(
                                            TransTree.treeScope(lastIfElse.elseBody, Tree.NoComment),
                                            TransTree.treeScope(newIfElse.ifBody, Tree.NoComment));
                                    lastIfElse = (TransIfElse) TransTree.ifElse(condition, ifBody, ifComment, elseBody,
                                            elseComment, Collections.emptySet());
                                    lastIfElseWrites = new HashSet<>(lastIfElseWrites);
                                    lastIfElseWrites.addAll(vars.written(newIfElse));
                                    lastIfElseArrayMods = new HashSet<>(lastIfElseWrites);
                                    lastIfElseArrayMods.addAll(vars.arraysModified(newIfElse));
                                } else {
                                    ts.add(normalise(transform(lastIfElse)));
                                    lastIfElse = newIfElse;
                                    lastIfElseWrites = vars.written(newIfElse);
                                    lastIfElseArrayMods = vars.arraysModified(newIfElse);
                                }
                            } else { // These two conditionals cannot be merged.
                                ts.add(normalise(transform(lastIfElse)));
                                lastIfElse = newIfElse;
                                lastIfElseWrites = vars.written(newIfElse);
                                lastIfElseArrayMods = vars.arraysModified(newIfElse);
                            }
                        }
                    } else { // This is not an ifElse, so add the last IfElse if it is set, then add the
                             // tree.
                        if(lastIfElse != null) {
                            ts.add(normalise(transform(lastIfElse)));
                            lastIfElse = null;
                            lastIfElseWrites = null;
                            lastIfElseArrayMods = null;
                        }
                        ts.add(transform(t));
                    }
                }

                // Add the last if else, if it has not been added already.
                if(lastIfElse != null)
                    ts.add(normalise(transform(lastIfElse)));

                return TransTree.sequential(ts, tree.getComment());
            }
            default:
                return tree.applyTransformation(this);
        }
    }

    private TransTreeVoid normalise(TransTreeVoid t) {
        if(t.type == TransTreeType.IF) {
            TransIfElse ifElse = (TransIfElse) t;
            if(ifElse.condition.type == TransTreeType.NEGATE_BOOLEAN && ifElse.elseBody.type != TransTreeType.NOP) {
                return normalise(TransTree.ifElse(((TransNegateBoolean) ifElse.condition).input, ifElse.elseBody,
                        ifElse.getElseComment(), ifElse.ifBody, ifElse.getComment(), ifElse.tags()));
            } else
                return t;
        } else
            return t;
    }

    private String mergeComments(String comment1, String comment2) {
        if(comment1 == Tree.NoComment) {
            return comment2;
        } else {
            if(comment2 == Tree.NoComment) {
                return comment1;
            } else {
                return comment1 + "\n\n" + comment2;
            }
        }
    }

    /**
     * Test if the last ifElse could have modified values in the guard of the new IfElse. TODO this needs to have a
     * check to make sure that we don't merge none deterministic guards.
     * 
     * @param lastIfElse
     * @param newIfElse
     * @return
     */
    private boolean mergeable(Set<VariableDescription<?>> written, Set<VariableDescription<?>> modifiedArrays,
            TransIfElse lastIfElse, TransIfElse newIfElse) {
        if(!lastIfElse.condition.deterministic() || !newIfElse.condition.deterministic())
            return false;
        for(VariableDescription<?> desc:vars.readVars(newIfElse.condition).getVars())
            if(written.contains(desc) || modifiedArrays.contains(desc))
                return false;
        return true;
    }

    @Override
    protected <X extends Variable<X>> TransTreeReturn<X> transformReturn(TransTreeReturn<X> tree) {
        return tree.applyTransformation(this);
    }
}
