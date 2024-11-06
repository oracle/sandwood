/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.binop;

import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.trees.outputTree.OutputBinOp;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;

public abstract class TransDivide<L extends NumberVariable<L>, R extends NumberVariable<R>, RT extends NumberVariable<RT>>
        extends TransBinOp<L, R, RT> {
    public TransDivide(TransTreeReturn<L> left, TransTreeReturn<R> right) {
        super(left, right, false, TransTreeType.DIVIDE);
    }

    @Override
    protected String getOp() {
        return "/";
    }

    /**
     * A helper method for building trees for min max calculations
     * 
     * @param <RV>     The type of the result
     * @param <G>      The type of the values used in the guards
     * @param min      The minimum value that can be seen
     * @param max      The maximum value that can be seen
     * @param positive The result to return if all the values are positive
     * @param negative The result to return if all the values are negative
     * @param both     The results to return if the values can be positive and negative
     * @return The result
     */
    protected <RV extends NumberVariable<RV>, G extends NumberVariable<G>> TransTreeReturn<RV> rangeTree(
            TransTreeReturn<G> min, TransTreeReturn<G> max, TransTreeReturn<RV> positive, TransTreeReturn<RV> negative,
            TransTreeReturn<RV> both) {
        TransTreeReturn<DoubleVariable> zero = constant(0.0);

        return conditionalAssignment(lessThanEqual(zero, min),
                // Is positive
                positive, conditionalAssignment(lessThan(max, zero),
                        // Is negative
                        negative,
                        // Is both positive and negative
                        both));
    }

    private interface Operation<A extends NumberVariable<A>, B extends NumberVariable<B>, C extends NumberVariable<C>> {
        TransTreeReturn<C> apply(TransTreeReturn<A> left, TransTreeReturn<B> right);
    }

    protected TransTreeReturn<RT> maxValueBody(Operation<L, R, RT> op, Bounds bounds) {
        TransTreeReturn<L> leftMin = left.minValue(bounds);
        TransTreeReturn<L> leftMax = left.maxValue(bounds);
        TransTreeReturn<R> rightMin = right.minValue(bounds);
        TransTreeReturn<R> rightMax = right.maxValue(bounds);

        return rangeTree(leftMin, leftMax,
                // Non-negative left
                rangeTree(rightMin, rightMax, op.apply(leftMax, rightMin), op.apply(leftMin, rightMin),
                        (TransTreeReturn<RT>) constant(Double.POSITIVE_INFINITY)),
                // Negative left
                rangeTree(rightMin, rightMax, op.apply(leftMax, rightMax), op.apply(leftMin, rightMax),
                        (TransTreeReturn<RT>) constant(Double.POSITIVE_INFINITY)),
                // Positive or negative left
                rangeTree(rightMin, rightMax, op.apply(leftMax, rightMin), op.apply(leftMin, rightMax),
                        (TransTreeReturn<RT>) constant(Double.POSITIVE_INFINITY)));
    }

    protected TransTreeReturn<RT> minValueBody(Operation<L, R, RT> op, Bounds bounds) {
        TransTreeReturn<L> leftMin = left.minValue(bounds);
        TransTreeReturn<L> leftMax = left.maxValue(bounds);
        TransTreeReturn<R> rightMin = right.minValue(bounds);
        TransTreeReturn<R> rightMax = right.maxValue(bounds);

        return rangeTree(leftMin, leftMax,
                // Non-negative left
                rangeTree(rightMin, rightMax, op.apply(leftMin, rightMax), op.apply(leftMax, rightMax),
                        op.apply(leftMax, rightMax)),
                // Negative left
                rangeTree(rightMin, rightMax, op.apply(leftMin, rightMin), op.apply(leftMax, rightMin),
                        (TransTreeReturn<RT>) constant(Double.NEGATIVE_INFINITY)),
                // Positive or negative left
                rangeTree(rightMin, rightMax, op.apply(leftMin, rightMin), op.apply(leftMax, rightMin),
                        (TransTreeReturn<RT>) constant(Double.NEGATIVE_INFINITY)));
    }

    public static TransDivide<DoubleVariable, DoubleVariable, DoubleVariable> getDivideDD(
            TransTreeReturn<DoubleVariable> left, TransTreeReturn<DoubleVariable> right) {
        return new TransDivide<>(left, right) {
            @Override
            public OutputBinOp<DoubleVariable, DoubleVariable, DoubleVariable> toOutputTreeReturnInternal() {
                return OutputBinOp.divideDD(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
            }

            @Override
            public TransDivide<DoubleVariable, DoubleVariable, DoubleVariable> applyTransformationInternal(
                    Transformer t) {
                return TransTree.divideDD(t.transform(left), t.transform(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }

            @Override
            public TransTreeReturn<DoubleVariable> maxValueInternal(Bounds bounds) {
                return maxValueBody(TransTree::divideDD, bounds);
            }

            @Override
            public TransTreeReturn<DoubleVariable> minValueInternal(Bounds bounds) {
                return minValueBody(TransTree::divideDD, bounds);
            }
        };
    }

    public static TransDivide<IntVariable, DoubleVariable, DoubleVariable> getDivideID(
            TransTreeReturn<IntVariable> left, TransTreeReturn<DoubleVariable> right) {
        return new TransDivide<>(left, right) {
            @Override
            public OutputBinOp<IntVariable, DoubleVariable, DoubleVariable> toOutputTreeReturnInternal() {
                return OutputBinOp.divideID(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
            }

            @Override
            public TransDivide<IntVariable, DoubleVariable, DoubleVariable> applyTransformationInternal(Transformer t) {
                return TransTree.divideID(t.transform(left), t.transform(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }

            @Override
            public TransTreeReturn<DoubleVariable> maxValueInternal(Bounds bounds) {
                return maxValueBody(TransTree::divideID, bounds);
            }

            @Override
            public TransTreeReturn<DoubleVariable> minValueInternal(Bounds bounds) {
                return minValueBody(TransTree::divideID, bounds);
            }
        };
    }

    public static TransDivide<DoubleVariable, IntVariable, DoubleVariable> getDivideDI(
            TransTreeReturn<DoubleVariable> left, TransTreeReturn<IntVariable> right) {
        return new TransDivide<>(left, right) {
            @Override
            public OutputBinOp<DoubleVariable, IntVariable, DoubleVariable> toOutputTreeReturnInternal() {
                return OutputBinOp.divideDI(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
            }

            @Override
            public TransDivide<DoubleVariable, IntVariable, DoubleVariable> applyTransformationInternal(Transformer t) {
                return TransTree.divideDI(t.transform(left), t.transform(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }

            @Override
            public TransTreeReturn<DoubleVariable> maxValueInternal(Bounds bounds) {
                return maxValueBody(TransTree::divideDI, bounds);
            }

            @Override
            public TransTreeReturn<DoubleVariable> minValueInternal(Bounds bounds) {
                return minValueBody(TransTree::divideDI, bounds);
            }
        };
    }

    public static TransDivide<IntVariable, IntVariable, IntVariable> getDivideII(TransTreeReturn<IntVariable> left,
            TransTreeReturn<IntVariable> right) {
        return new TransDivide<>(left, right) {
            @Override
            public OutputBinOp<IntVariable, IntVariable, IntVariable> toOutputTreeReturnInternal() {
                return OutputBinOp.divideII(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
            }

            @Override
            public TransDivide<IntVariable, IntVariable, IntVariable> applyTransformationInternal(Transformer t) {
                return TransTree.divideII(t.transform(left), t.transform(right));
            }

            @Override
            public Type<IntVariable> getOutputType() {
                return VariableType.IntVariable;
            }

            @Override
            public TransTreeReturn<IntVariable> maxValueInternal(Bounds bounds) {
                TransTreeReturn<IntVariable> leftMin = left.minValue(bounds);
                TransTreeReturn<IntVariable> leftMax = left.maxValue(bounds);
                TransTreeReturn<IntVariable> rightMin = right.minValue(bounds);
                TransTreeReturn<IntVariable> rightMax = right.maxValue(bounds);

                // These assume that there will not be a divide by zero possibility as that would be an error
                // in the model
                return rangeTree(leftMin, leftMax,
                        // Non-negative left
                        rangeTree(rightMin, rightMax, TransTree.divideII(leftMax, rightMin),
                                TransTree.divideII(leftMin, rightMin), leftMax),
                        // Negative left
                        rangeTree(rightMin, rightMax, TransTree.divideII(leftMax, rightMax),
                                TransTree.divideII(leftMin, rightMax), negate(leftMin)),
                        // Positive or negative left
                        rangeTree(rightMin, rightMax, TransTree.divideII(leftMax, rightMin),
                                TransTree.divideII(leftMin, rightMax), max(leftMax, negate(leftMin))));
            }

            @Override
            public TransTreeReturn<IntVariable> minValueInternal(Bounds bounds) {
                TransTreeReturn<IntVariable> leftMin = left.minValue(bounds);
                TransTreeReturn<IntVariable> leftMax = left.maxValue(bounds);
                TransTreeReturn<IntVariable> rightMin = right.minValue(bounds);
                TransTreeReturn<IntVariable> rightMax = right.maxValue(bounds);

                // These assume that there will not be a divide by zero possibility as that would be an error
                // in the model
                return rangeTree(leftMin, leftMax,
                        // Non-negative left
                        rangeTree(rightMin, rightMax, TransTree.divideII(leftMin, rightMax),
                                TransTree.divideII(leftMax, rightMax), negate(leftMax)),
                        // Negative left
                        rangeTree(rightMin, rightMax, TransTree.divideII(leftMin, rightMin),
                                TransTree.divideII(leftMax, rightMin), leftMin),
                        // Positive or negative left
                        rangeTree(rightMin, rightMax, TransTree.divideII(leftMin, rightMin),
                                TransTree.divideII(leftMax, rightMin), min(negate(leftMax), leftMin)));
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static TransDivide<? extends NumberVariable<?>, ? extends NumberVariable<?>, ? extends NumberVariable<?>> getDivide(
            TransTreeReturn<? extends NumberVariable<?>> left, TransTreeReturn<? extends NumberVariable<?>> right) {
        if(left.getOutputType() == VariableType.IntVariable) {
            if(right.getOutputType() == VariableType.IntVariable) {
                return TransTree.divideII((TransTreeReturn<IntVariable>) left, (TransTreeReturn<IntVariable>) right);
            } else {
                return TransTree.divideID((TransTreeReturn<IntVariable>) left, (TransTreeReturn<DoubleVariable>) right);
            }
        } else {
            if(right.getOutputType() == VariableType.IntVariable) {
                return TransTree.divideDI((TransTreeReturn<DoubleVariable>) left, (TransTreeReturn<IntVariable>) right);
            } else {
                return TransTree.divideDD((TransTreeReturn<DoubleVariable>) left,
                        (TransTreeReturn<DoubleVariable>) right);
            }
        }
    }
}