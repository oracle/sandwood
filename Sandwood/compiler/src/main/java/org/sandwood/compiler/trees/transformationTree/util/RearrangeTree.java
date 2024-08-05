/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.util;

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.ScalarVariable;
import org.sandwood.compiler.trees.transformationTree.TransConstDouble;
import org.sandwood.compiler.trees.transformationTree.TransConstInt;
import org.sandwood.compiler.trees.transformationTree.TransLoad;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTree.TransTreeType;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.binop.TransBinOp;
import org.sandwood.compiler.trees.transformationTree.binop.TransEq;
import org.sandwood.compiler.trees.transformationTree.binop.TransLessThan;
import org.sandwood.compiler.trees.transformationTree.binop.TransLessThanEqual;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VariableTracking;

public class RearrangeTree {
    // TODO Sort typing here.
    public static <A extends ScalarVariable<A>, B extends ScalarVariable<B>, C extends NumberVariable<C>, D extends NumberVariable<D>, E extends ScalarVariable<E>> TransTreeReturn<E> rearrangeTree(
            TransEq<A, B> eq, VariableDescription<E> target, IntGCD gcds) {
        if(eq.left.getOutputType() == VariableType.BooleanVariable) {
            TransEq<BooleanVariable, BooleanVariable> constraint = (TransEq<BooleanVariable, BooleanVariable>) eq;
            if(constraint.left.type == TransTreeType.LOAD
                    && ((TransLoad<BooleanVariable>) constraint.left).varDesc.equals(target))
                return (TransTreeReturn<E>) constraint.right.copy();
            else if(constraint.right.type == TransTreeType.LOAD
                    && ((TransLoad<BooleanVariable>) constraint.right).varDesc.equals(target))
                return (TransTreeReturn<E>) constraint.left.copy();
            else
                return null;
        } else {
            TransEq<C, D> constraint = (TransEq<C, D>) eq;
            assert (target != null);

            Data data = rearrangeTreeInternal(target, constraint.left, constraint.right, false, gcds);
            if(data == null)
                return null;

            if(data.simplifiedExpression.getOutputType().equals(data.residualExpression.getOutputType()))
                return (TransTreeReturn<E>) data.residualExpression;
            else {
                if(data.simplifiedExpression.getOutputType() == VariableType.DoubleVariable)
                    return (TransTreeReturn<E>) TransTree
                            .castToDouble((TransTreeReturn<IntVariable>) data.residualExpression);
                else
                    return (TransTreeReturn<E>) TransTree
                            .castToInteger((TransTreeReturn<DoubleVariable>) data.residualExpression);
            }
        }
    }

    public static <A extends NumberVariable<A>, B extends NumberVariable<B>> TransLessThan<A, B> rearrangeTree(
            TransLessThan<?, ?> constraint, VariableDescription<? extends NumberVariable<?>> target, IntGCD gcds) {
        assert (target != null);
        Data data = rearrangeTreeInternal(target, constraint.left, constraint.right, true, gcds);
        if(data == null)
            return null;

        if((data.firstTreeLeft && !data.inverted) || (!data.firstTreeLeft && data.inverted))
            return TransTree.lessThan((TransTreeReturn<A>) data.simplifiedExpression,
                    (TransTreeReturn<B>) data.residualExpression);
        else
            return TransTree.lessThan((TransTreeReturn<A>) data.residualExpression,
                    (TransTreeReturn<B>) data.simplifiedExpression);
    }

    public static <A extends NumberVariable<A>, B extends NumberVariable<B>> TransLessThanEqual<A, B> rearrangeTree(
            TransLessThanEqual<?, ?> constraint, VariableDescription<?> target, IntGCD gcds) {
        assert (target != null);
        Data data = rearrangeTreeInternal(target, constraint.left, constraint.right, true, gcds);
        if(data == null)
            return null;

        if((data.firstTreeLeft && !data.inverted) || (!data.firstTreeLeft && data.inverted))
            return TransTree.lessThanEqual((TransTreeReturn<A>) data.simplifiedExpression,
                    (TransTreeReturn<B>) data.residualExpression);
        else
            return TransTree.lessThanEqual((TransTreeReturn<A>) data.residualExpression,
                    (TransTreeReturn<B>) data.simplifiedExpression);
    }

    private static class Data {
        boolean inverted = false;
        final boolean firstTreeLeft;
        final boolean inequality;
        TransTreeReturn<? extends NumberVariable<?>> residualExpression;
        TransTreeReturn<? extends NumberVariable<?>> simplifiedExpression;

        public Data(boolean inequality, boolean firstTreeLeft) {
            this.inequality = inequality;
            this.firstTreeLeft = firstTreeLeft;
        }
    }

    private static Data rearrangeTreeInternal(VariableDescription<?> target,
            TransTreeReturn<? extends NumberVariable<?>> left, TransTreeReturn<? extends NumberVariable<?>> right,
            boolean inequality, IntGCD gcds) {
        // Get the parts of the tree using the index in each side of the equality.
        VariableTracking leftVars = left.getVariableTracking();
        VariableTracking rightVars = right.getVariableTracking();

        boolean usedLeft = leftVars.read(left, target);
        boolean usedRight = rightVars.read(right, target);
        if((usedLeft && usedRight) || !(usedLeft || usedRight))
            return null;

        VariableTracking vars;

        Data data;

        if(usedLeft) {
            data = new Data(inequality, true);
            vars = leftVars;
            data.residualExpression = right;
            data.simplifiedExpression = left;
        } else {
            data = new Data(inequality, false);
            vars = rightVars;
            data.residualExpression = left;
            data.simplifiedExpression = right;
        }

        while(true) {
            switch(data.simplifiedExpression.type) {

                case SUBTRACT:
                case MULTIPLY:
                case DIVIDE:
                case ADD: {
                    TransBinOp<? extends NumberVariable<?>, ? extends NumberVariable<?>, ? extends NumberVariable<?>> b = (TransBinOp<? extends NumberVariable<?>, ? extends NumberVariable<?>, ? extends NumberVariable<?>>) data.simplifiedExpression;
                    usedLeft = vars.read(b.left, target);
                    usedRight = vars.read(b.right, target);
                    // If it is in both branches we cannot rearrange to a single value (or not
                    // trivially anyway)
                    // If it is unused in both the left and the right then we cannot rearrange to
                    // find a formula
                    // based on name, so should stop.
                    if((usedLeft && usedRight))
                        return null;

                    switch(data.simplifiedExpression.type) {
                        case ADD: {
                            if(usedLeft) {
                                data.simplifiedExpression = b.left;
                                data.residualExpression = TransTree.subtract(data.residualExpression, b.right);
                            } else {
                                data.simplifiedExpression = b.right;
                                data.residualExpression = TransTree.subtract(data.residualExpression, b.left);
                            }
                            break;
                        }
                        case SUBTRACT: {
                            if(usedLeft) {
                                data.simplifiedExpression = b.left;
                                data.residualExpression = TransTree.add(b.right, data.residualExpression);
                            } else {
                                data.simplifiedExpression = b.right;
                                data.residualExpression = TransTree.subtract(b.left, data.residualExpression);
                            }
                            break;
                        }

                        case MULTIPLY: {
                            if(usedLeft) {
                                if(!safeInequality(data, b.right))
                                    return null;
                                data.simplifiedExpression = b.left;
                                data.residualExpression = TransTree.divide(data.residualExpression, b.right);
                            } else {
                                if(!safeInequality(data, b.left))
                                    return null;
                                data.simplifiedExpression = b.right;
                                data.residualExpression = TransTree.divide(data.residualExpression, b.left);
                            }
                            break;
                        }

                        case DIVIDE: {
                            // Test if information is lost in this operation that cannot be recovered. If it is the
                            // expression cannot be rearranged.
                            if(b.left.getOutputType() == VariableType.IntVariable) {
                                switch(b.right.type) {
                                    /*
                                     * For now we only consider values that are constant. TODO extend this to sets of
                                     * possible constants. When this is done there will still be no need to do tests
                                     * beyond this one as:
                                     * 
                                     * a/b = c => (x*b)/b = c iff gcd(a)%c==0 => x = c
                                     */
                                    case CONST_DOUBLE: {
                                        int gcd = gcds.getGCD((TransTreeReturn<IntVariable>) b.left);
                                        if(gcd % ((TransConstDouble) b.right).value != 0)
                                            return null;
                                        break;
                                    }
                                    case CONST_INT: {
                                        int gcd = gcds.getGCD((TransTreeReturn<IntVariable>) b.left);
                                        if(gcd % ((TransConstInt) b.right).value != 0)
                                            return null;
                                        break;
                                    }
                                    default:
                                        // Information may be lost so return.
                                        return null;
                                }
                            }

                            if(usedLeft) {
                                if(!safeInequality(data, b.right))
                                    return null;
                                data.simplifiedExpression = b.left;
                                data.residualExpression = TransTree.multiply(b.right, data.residualExpression);
                            } else {
                                if(!safeInequality(data, b.left))
                                    return null;
                                data.simplifiedExpression = b.right;
                                data.residualExpression = TransTree.divide(b.left, data.residualExpression);
                            }
                            break;
                        }
                        default:
                            return null;
                    }
                    break;
                }

                case LOAD:
                    return data;

                default:
                    return null;
            }
        }
    }

    // TODO extend this to take a bounds so it can calculate if an expression is always positive or always negative.
    private static boolean safeInequality(Data data, TransTreeReturn<? extends NumberVariable<?>> tree) {
        if(!data.inequality)
            return true;
        else { // TODO Modify this to use min and max values to increase the set of
               // inequalities that can be handled.
            if(tree.type == TransTreeType.CONST_INT) {
                int value = ((TransConstInt) tree).value;
                if(value == 0)
                    return false;
                if(value < 0)
                    data.inverted = !data.inverted;
                return true;
            } else if(tree.type == TransTreeType.CONST_DOUBLE) {
                double value = ((TransConstDouble) tree).value;
                if(value == 0.0)
                    return false;
                if(value < 0)
                    data.inverted = !data.inverted;
                return true;
            } else
                return false;
        }
    }
}
