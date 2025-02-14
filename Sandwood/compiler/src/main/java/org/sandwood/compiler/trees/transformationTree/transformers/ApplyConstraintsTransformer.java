/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.transformers;

import static org.sandwood.compiler.trees.transformationTree.TransTree.addII;
import static org.sandwood.compiler.trees.transformationTree.TransTree.and;
import static org.sandwood.compiler.trees.transformationTree.TransTree.constant;
import static org.sandwood.compiler.trees.transformationTree.TransTree.eq;
import static org.sandwood.compiler.trees.transformationTree.TransTree.forStmt;
import static org.sandwood.compiler.trees.transformationTree.TransTree.ifElse;
import static org.sandwood.compiler.trees.transformationTree.TransTree.lessThan;
import static org.sandwood.compiler.trees.transformationTree.TransTree.lessThanEqual;
import static org.sandwood.compiler.trees.transformationTree.TransTree.load;
import static org.sandwood.compiler.trees.transformationTree.TransTree.max;
import static org.sandwood.compiler.trees.transformationTree.TransTree.min;
import static org.sandwood.compiler.trees.transformationTree.TransTree.remainderII;
import static org.sandwood.compiler.trees.transformationTree.TransTree.sequential;
import static org.sandwood.compiler.trees.transformationTree.TransTree.subtractII;
import static org.sandwood.compiler.trees.transformationTree.TransTree.treeScope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.ScalarVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.transformationTree.TransFor;
import org.sandwood.compiler.trees.transformationTree.TransIfElse;
import org.sandwood.compiler.trees.transformationTree.TransLoad;
import org.sandwood.compiler.trees.transformationTree.TransNegateBoolean;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTree.TransTreeType;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;
import org.sandwood.compiler.trees.transformationTree.binop.TransEq;
import org.sandwood.compiler.trees.transformationTree.binop.TransLessThan;
import org.sandwood.compiler.trees.transformationTree.binop.TransLessThanEqual;
import org.sandwood.compiler.trees.transformationTree.binop.TransOr;
import org.sandwood.compiler.trees.transformationTree.util.IntGCD;
import org.sandwood.compiler.trees.transformationTree.util.RearrangeTree;

public class ApplyConstraintsTransformer extends Transformer {
    private TransTreeReturn<IntVariable> replacement = null;
    private final List<TransTreeReturn<BooleanVariable>> bounds = new ArrayList<>();
    private VariableDescription<IntVariable> target = null;
    private boolean canSetValue = true;
    private final IntGCD gcds;

    boolean incrementing;

    public ApplyConstraintsTransformer(TransTree<?> tree) {
        gcds = new IntGCD(tree, tree.getVariableTracking());
    }

    @Override
    public TransTreeVoid transformVoid(TransTreeVoid tree) {
        switch(tree.type) {
            case FOR:
                visitedNodes.add(tree);
                return applyConstraints((TransFor) tree);

            default:
                return tree.applyTransformation(this);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <X extends Variable<X>> TransTreeReturn<X> transformReturn(TransTreeReturn<X> tree) {
        switch(tree.type) {
            case EQUALITY:
                visitedNodes.add(tree);
                return (TransTreeReturn<X>) applyConstraints((TransEq<?, ?>) tree);

            case LESS_THAN:
                visitedNodes.add(tree);
                return (TransTreeReturn<X>) applyConstraints((TransLessThan<?, ?>) tree);

            case LESS_THAN_EQUAL:
                visitedNodes.add(tree);
                return (TransTreeReturn<X>) applyConstraints((TransLessThanEqual<?, ?>) tree);

            case NEGATE_BOOLEAN:
                visitedNodes.add(tree);
                return (TransTreeReturn<X>) applyConstraints((TransNegateBoolean) tree);

            case OR:
                visitedNodes.add(tree);
                return (TransTreeReturn<X>) applyConstraints((TransOr) tree);

            default:
                return tree.applyTransformation(this);
        }
    }

    private TransTreeReturn<BooleanVariable> applyConstraints(TransLessThan<?, ?> tree) {
        if(target != null && canSetValue) {
            TransLessThan<?, ?> rearranged = RearrangeTree.rearrangeTree(tree, target, gcds);
            if(rearranged != null) {
                tree.addNodes(visitedNodes);
                bounds.add(rearranged);
                return constant(true);
            }
        }

        // We are inside a negation or an or'ed condition, or are not looking to
        // rearrange this value, or failed to rearrange this value
        return tree.applyTransformation(this);
    }

    private TransTreeReturn<BooleanVariable> applyConstraints(TransLessThanEqual<?, ?> tree) {
        if(target != null && canSetValue) {
            TransLessThanEqual<?, ?> rearranged = RearrangeTree.rearrangeTree(tree, target, gcds);
            if(rearranged != null) {
                tree.addNodes(visitedNodes);
                bounds.add(rearranged);
                return constant(true);
            }
        }

        // We are inside a negation or an or'ed condition, or are not looking to
        // rearrange this value, or failed to rearrange this value
        return tree.applyTransformation(this);
    }

    private <A extends ScalarVariable<A>, B extends ScalarVariable<B>> TransTreeReturn<BooleanVariable> applyConstraints(
            TransEq<A, B> tree) {
        if(target != null && replacement == null && canSetValue) {
            replacement = RearrangeTree.rearrangeTree(tree, target, gcds);
            if(replacement != null) {
                tree.addNodes(visitedNodes);
                return constant(true);
            }
        }

        // We are inside a negation or an or'ed condition, or are not looking to
        // rearrange this value, or failed to rearrange this value
        return tree.applyTransformation(this);
    }

    /**
     * Method to apply the constraints to a loop removing as many of the tests as possible by detecting duplication, and
     * evaluating constant expressions where possible.
     */
    private TransTreeVoid applyConstraints(TransFor tf) {
        TransTreeReturn<IntVariable> transStart = transform(tf.start);
        TransTreeReturn<IntVariable> transEnd = transform(tf.end);
        TransTreeReturn<IntVariable> transStep = transform(tf.step);

        // If there are no constraints to apply, just generate a normal for node.
        if(tf.body.type != TransTreeType.IF || ((TransIfElse) tf.body).elseBody.type != TransTreeType.NOP) {
            return forStmt(transform(tf.body), transStart, transEnd, transStep, tf.indexDesc, tf.parallel,
                    tf.incrementing, tf.getComment());
        } else { // Otherwise apply the constraints.
            visitedNodes.add(tf.body);

            target = tf.indexDesc;
            incrementing = tf.incrementing;

            TransIfElse ifElse = (TransIfElse) tf.body;
            TransTreeReturn<BooleanVariable> condition = transform(ifElse.condition);

            if(!bounds.isEmpty()) {
                TransTreeReturn<IntVariable> originalStart = transStart;
                for(TransTreeReturn<BooleanVariable> t:bounds) {
                    // TODO add in handling for doubles here.
                    switch(t.type) {
                        case LESS_THAN: {
                            TransLessThan<IntVariable, IntVariable> lt = (TransLessThan<IntVariable, IntVariable>) t;
                            if(lt.left.type == TransTreeType.LOAD
                                    && ((TransLoad<?>) lt.left).varDesc.equals(tf.indexDesc)) {
                                if(incrementing) {
                                    TransTreeReturn<IntVariable> upperBound = subtractII(lt.right, constant(1));
                                    transEnd = min(transEnd, upperBound);
                                } else {
                                    TransTreeReturn<IntVariable> upperBound = subtractII(lt.right, constant(1));
                                    TransTreeReturn<IntVariable> offset = remainderII(
                                            subtractII(transStep,
                                                    remainderII(subtractII(originalStart, upperBound), transStep)),
                                            transStep).copy();
                                    transStart = min(transStart, subtractII(upperBound, offset));
                                }
                            } else { // The right tree is the index.
                                if(incrementing) {
                                    TransTreeReturn<IntVariable> lowerBound = addII(lt.left, constant(1));
                                    TransTreeReturn<IntVariable> offset = remainderII(
                                            subtractII(transStep,
                                                    remainderII(subtractII(lowerBound, originalStart), transStep)),
                                            transStep).copy();
                                    transStart = max(transStart, addII(lowerBound, offset));
                                } else {
                                    TransTreeReturn<IntVariable> lowerBound = TransTree.addII(lt.left,
                                            TransTree.constant(1));
                                    transEnd = max(transEnd, lowerBound);
                                }
                            }
                            break;
                        }
                        case LESS_THAN_EQUAL:
                            TransLessThanEqual<IntVariable, IntVariable> lte = (TransLessThanEqual<IntVariable, IntVariable>) t;
                            if(lte.left.type == TransTreeType.LOAD
                                    && ((TransLoad<?>) lte.left).varDesc.equals(tf.indexDesc)) {
                                if(incrementing) {
                                    transEnd = min(transEnd, lte.right);
                                } else {
                                    TransTreeReturn<IntVariable> upperBound = lte.right;
                                    TransTreeReturn<IntVariable> offset = remainderII(
                                            subtractII(transStep,
                                                    remainderII(subtractII(originalStart, upperBound), transStep)),
                                            transStep).copy();
                                    transStart = min(transStart, subtractII(upperBound, offset));
                                }
                            } else { // The right tree is the index.
                                if(incrementing) {
                                    TransTreeReturn<IntVariable> lowerBound = lte.left;
                                    TransTreeReturn<IntVariable> offset = remainderII(
                                            subtractII(transStep,
                                                    remainderII(subtractII(lowerBound, originalStart), transStep)),
                                            transStep).copy();
                                    transStart = max(transStart, addII(lowerBound, offset));
                                } else {
                                    transEnd = max(transEnd, lte.left);
                                }
                            }
                            break;
                        default:
                            throw new CompilerException("Unexpected tree type.");

                    }
                }
                bounds.clear();
            }

            // There are equalities that fix the value of the for loop we have a constant,
            // we will not be returning a for, just a conditional.
            if(replacement != null) {
                TransTreeVoid[] trees = new TransTreeVoid[2];

                // Replace the for loop with an initialized variable name. This does rely on the
                // replacement value
                // being safe to read before the other guards have been evaluated to true. This
                // is currently the case,
                // but if this changes in the future we may need to add restrictions to prevent
                // arrays being read if they
                // are not the first term of the guard.
                trees[0] = TransTree.initializeVariable(tf.indexDesc, replacement, Tree.NoComment);

                TransLoad<IntVariable> value = load(tf.indexDesc);

                // TODO remove this or move it to collapse constants, it is here to simplify
                // tracking changes as we move the way loop bounds are encoded.
                if(incrementing) {
                    int endSize = transEnd.size();
                    TransTreeReturn<IntVariable> altEnd = addII(transEnd, constant(1)).collapseConstants();
                    if(altEnd.size() <= endSize)
                        condition = and(lessThanEqual(transStart, value).copy(visitedNodes),
                                lessThan(value, altEnd).copy(visitedNodes), condition);
                    else
                        condition = and(lessThanEqual(transStart, value).copy(visitedNodes),
                                lessThanEqual(value, transEnd).copy(visitedNodes), condition);
                } else
                    condition = and(lessThanEqual(value, transStart).copy(visitedNodes),
                            lessThanEqual(transEnd, value).copy(visitedNodes), condition);

                // Add a constraint to ensure the constant is one of the values that for loop
                // would produce.
                TransTreeReturn<BooleanVariable> stepCondition = eq(constant(0), remainderII(
                        subtractII(load(tf.indexDesc), transStart.copy(visitedNodes)), transStep.copy(visitedNodes)));
                condition = and(stepCondition, condition);

                replacement = null;
                target = null;

                TransTreeVoid transBody = transform(ifElse.ifBody);
                // Transform condition again so any substitutions that should be applied to
                // remaining parts of the condition can be.
                condition = transform(condition);
                trees[1] = ifElse(condition, transBody, ifElse.getComment(), Collections.emptySet());

                return treeScope(sequential(trees), Tree.NoComment);

            } else { // Else we have to keep the for loop.
                target = null;
                TransTreeVoid transBody = ifElse(condition, transform(ifElse.ifBody), ifElse.getComment(),
                        Collections.emptySet());
                return forStmt(transBody, transStart, transEnd, transStep, tf.indexDesc, tf.parallel, tf.incrementing,
                        tf.getComment());
            }
        }
    }

    private TransTreeReturn<BooleanVariable> applyConstraints(TransNegateBoolean tree) {
        boolean canSetValue = this.canSetValue;
        this.canSetValue = false;
        TransTreeReturn<BooleanVariable> toReturn = tree.applyTransformationInternal(this);
        this.canSetValue = canSetValue;
        return toReturn;
    }

    private TransTreeReturn<BooleanVariable> applyConstraints(TransOr tree) {
        boolean canSetValue = this.canSetValue;
        this.canSetValue = false;
        TransTreeReturn<BooleanVariable> t = tree.applyTransformation(this);
        this.canSetValue = canSetValue;
        return t;
    }
}
