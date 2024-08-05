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

public abstract class TransMultiply<L extends NumberVariable<L>, R extends NumberVariable<R>, RT extends NumberVariable<RT>>
        extends TransBinOp<L, R, RT> {
    protected TransMultiply(TransTreeReturn<L> left, TransTreeReturn<R> right) {
        super(left, right, true, TransTreeType.MULTIPLY);
    }

    @Override
    protected String getOp() {
        return "*";
    }

    private interface Operation<A extends NumberVariable<A>, B extends NumberVariable<B>, C extends NumberVariable<C>> {
        TransTreeReturn<C> apply(TransTreeReturn<A> left, TransTreeReturn<B> right);
    }
    
    protected TransTreeReturn<RT> maxValueBody(Operation<L, R, RT> op, Bounds bounds) {
        TransTreeReturn<L> leftMin = left.minValue(bounds);
        TransTreeReturn<L> leftMax = left.maxValue(bounds);
        TransTreeReturn<R> rightMin = right.minValue(bounds);
        TransTreeReturn<R> rightMax = right.maxValue(bounds);

        TransTreeReturn<RT> maxMax = op.apply(leftMax, rightMax);
        TransTreeReturn<RT> minMin = op.apply(leftMin, rightMin);
        TransTreeReturn<RT> maxMinMax = TransTree.max(maxMax, minMin);

        TransTreeReturn<DoubleVariable> zero = constant(0.0);

        return conditionalAssignment(lessThanEqual(zero, leftMin),
                // Left is positive
                maxMax, conditionalAssignment(lessThan(leftMax, zero),
                        // Left is negative
                        minMin, conditionalAssignment(lessThanEqual(zero, rightMin),
                                // Right is positive
                                maxMax, conditionalAssignment(lessThan(rightMax, zero),
                                        // Right is negative
                                        minMin,
                                        // They both span positive and negative values so the larger of two
                                        // values
                                        // must be taken.
                                        maxMinMax))));
    }

    protected TransTreeReturn<RT> minValueBody(Operation<L, R, RT> op, Bounds bounds) {
        TransTreeReturn<L> leftMin = left.minValue(bounds);
        TransTreeReturn<L> leftMax = left.maxValue(bounds);
        TransTreeReturn<R> rightMin = right.minValue(bounds);
        TransTreeReturn<R> rightMax = right.maxValue(bounds);

        TransTreeReturn<RT> maxMax = op.apply(leftMax, rightMax);
        TransTreeReturn<RT> minMin = op.apply(leftMin, rightMin);
        TransTreeReturn<RT> minMinMax = TransTree.min(maxMax, minMin);

        TransTreeReturn<DoubleVariable> zero = constant(0.0);

        return conditionalAssignment(lessThanEqual(zero, leftMin),
                // Left is positive
                minMin, conditionalAssignment(lessThan(leftMax, zero),
                        // Left is negative
                        maxMax, conditionalAssignment(lessThanEqual(zero, rightMin),
                                // Right is positive
                                minMin, conditionalAssignment(lessThan(rightMax, zero),
                                        // Right is negative
                                        maxMax,
                                        // They both span positive and negative values so the larger of two
                                        // values
                                        // must be taken.
                                        minMinMax))));
    }

    public static TransMultiply<DoubleVariable, DoubleVariable, DoubleVariable> getMultiplyDD(
            TransTreeReturn<DoubleVariable> left, TransTreeReturn<DoubleVariable> right) {
        return new TransMultiply<>(left, right) {
            @Override
            public OutputBinOp<DoubleVariable, DoubleVariable, DoubleVariable> toOutputTreeReturnInternal() {
                return OutputBinOp.multiplyDD(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
            }

            @Override
            public TransMultiply<DoubleVariable, DoubleVariable, DoubleVariable> applyTransformationInternal(
                    Transformer t) {
                return TransTree.multiplyDD(t.transform(left), t.transform(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }

            @Override
            public TransTreeReturn<DoubleVariable> maxValueInternal(Bounds bounds) {
                return maxValueBody(TransTree::multiplyDD, bounds);
            }

            @Override
            public TransTreeReturn<DoubleVariable> minValueInternal(Bounds bounds) {
                return minValueBody(TransTree::multiplyDD, bounds);
            }
        };
    }

    public static TransMultiply<IntVariable, DoubleVariable, DoubleVariable> getMultiplyID(
            TransTreeReturn<IntVariable> left, TransTreeReturn<DoubleVariable> right) {
        return new TransMultiply<>(left, right) {
            @Override
            public OutputBinOp<IntVariable, DoubleVariable, DoubleVariable> toOutputTreeReturnInternal() {
                return OutputBinOp.multiplyID(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
            }

            @Override
            public TransMultiply<IntVariable, DoubleVariable, DoubleVariable> applyTransformationInternal(
                    Transformer t) {
                return TransTree.multiplyID(t.transform(left), t.transform(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }

            @Override
            public TransTreeReturn<DoubleVariable> maxValueInternal(Bounds bounds) {
                return maxValueBody(TransTree::multiplyID, bounds);
            }

            @Override
            public TransTreeReturn<DoubleVariable> minValueInternal(Bounds bounds) {
                return minValueBody(TransTree::multiplyID, bounds);
            }
        };
    }

    public static TransMultiply<DoubleVariable, IntVariable, DoubleVariable> getMultiplyDI(
            TransTreeReturn<DoubleVariable> left, TransTreeReturn<IntVariable> right) {
        return new TransMultiply<>(left, right) {
            @Override
            public OutputBinOp<DoubleVariable, IntVariable, DoubleVariable> toOutputTreeReturnInternal() {
                return OutputBinOp.multiplyDI(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
            }

            @Override
            public TransMultiply<DoubleVariable, IntVariable, DoubleVariable> applyTransformationInternal(
                    Transformer t) {
                return TransTree.multiplyDI(t.transform(left), t.transform(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }

            @Override
            public TransTreeReturn<DoubleVariable> maxValueInternal(Bounds bounds) {
                return maxValueBody(TransTree::multiplyDI, bounds);
            }

            @Override
            public TransTreeReturn<DoubleVariable> minValueInternal(Bounds bounds) {
                return minValueBody(TransTree::multiplyDI, bounds);
            }
        };
    }

    public static TransMultiply<IntVariable, IntVariable, IntVariable> getMultiplyII(TransTreeReturn<IntVariable> left,
            TransTreeReturn<IntVariable> right) {
        return new TransMultiply<>(left, right) {
            @Override
            public OutputBinOp<IntVariable, IntVariable, IntVariable> toOutputTreeReturnInternal() {
                return OutputBinOp.multiplyII(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
            }

            @Override
            public TransMultiply<IntVariable, IntVariable, IntVariable> applyTransformationInternal(Transformer t) {
                return TransTree.multiplyII(t.transform(left), t.transform(right));
            }

            @Override
            public Type<IntVariable> getOutputType() {
                return VariableType.IntVariable;
            }

            @Override
            public TransTreeReturn<IntVariable> maxValueInternal(Bounds bounds) {
                return maxValueBody(TransTree::multiplyII, bounds);
            }

            @Override
            public TransTreeReturn<IntVariable> minValueInternal(Bounds bounds) {
                return minValueBody(TransTree::multiplyII, bounds);
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static TransMultiply<? extends NumberVariable<?>, ? extends NumberVariable<?>, ? extends NumberVariable<?>> getMultiply(
            TransTreeReturn<? extends NumberVariable<?>> left, TransTreeReturn<? extends NumberVariable<?>> right) {
        if(left.getOutputType() == VariableType.IntVariable) {
            if(right.getOutputType() == VariableType.IntVariable) {
                return TransTree.multiplyII((TransTreeReturn<IntVariable>) left, (TransTreeReturn<IntVariable>) right);
            } else {
                return TransTree.multiplyID((TransTreeReturn<IntVariable>) left,
                        (TransTreeReturn<DoubleVariable>) right);
            }
        } else {
            if(right.getOutputType() == VariableType.IntVariable) {
                return TransTree.multiplyDI((TransTreeReturn<DoubleVariable>) left,
                        (TransTreeReturn<IntVariable>) right);
            } else {
                return TransTree.multiplyDD((TransTreeReturn<DoubleVariable>) left,
                        (TransTreeReturn<DoubleVariable>) right);
            }
        }
    }
}
