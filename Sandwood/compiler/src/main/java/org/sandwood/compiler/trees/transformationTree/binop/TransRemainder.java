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

public abstract class TransRemainder<L extends NumberVariable<L>, R extends NumberVariable<R>, RT extends NumberVariable<RT>>
        extends TransBinOp<L, R, RT> {

    public TransRemainder(TransTreeReturn<L> left, TransTreeReturn<R> right) {
        super(left, right, false, TransTreeType.REMAINDER);
    }

    @Override
    protected String getOp() {
        return "%";
    }

    public static TransRemainder<DoubleVariable, DoubleVariable, DoubleVariable> getRemainderDD(
            TransTreeReturn<DoubleVariable> left, TransTreeReturn<DoubleVariable> right) {
        return new TransRemainder<>(left, right) {
            @Override
            public OutputBinOp<DoubleVariable, DoubleVariable, DoubleVariable> toOutputTreeReturnInternal() {
                return OutputBinOp.remainderDD(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
            }

            @Override
            public TransBinOp<DoubleVariable, DoubleVariable, DoubleVariable> applyTransformationInternal(
                    Transformer t) {
                return remainderDD(t.transform(left), t.transform(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }

            @Override
            public TransTreeReturn<DoubleVariable> maxValueInternal(Bounds bounds) {
                return conditionalAssignment(lessThan(constant(0), left),
                        conditionalAssignment(lessThan(constant(0), right),
                                min(left.maxValue(bounds), right.maxValue(bounds)),
                                min(left.maxValue(bounds), negate(right.minValue(bounds)))),
                        constant(0.0));
            }

            @Override
            public TransTreeReturn<DoubleVariable> minValueInternal(Bounds bounds) {
                return conditionalAssignment(lessThan(constant(0), left), constant(0.0),
                        conditionalAssignment(lessThan(constant(0), right),
                                max(left.minValue(bounds), negate(right.maxValue(bounds))),
                                max(left.minValue(bounds), right.minValue(bounds))));
            }
        };
    }

    public static TransRemainder<IntVariable, DoubleVariable, DoubleVariable> getRemainderID(
            TransTreeReturn<IntVariable> left, TransTreeReturn<DoubleVariable> right) {
        return new TransRemainder<>(left, right) {

            @Override
            public OutputBinOp<IntVariable, DoubleVariable, DoubleVariable> toOutputTreeReturnInternal() {
                return OutputBinOp.remainderID(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
            }

            @Override
            public TransBinOp<IntVariable, DoubleVariable, DoubleVariable> applyTransformationInternal(Transformer t) {
                return TransTree.remainderID(t.transform(left), t.transform(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }

            @Override
            public TransTreeReturn<DoubleVariable> maxValueInternal(Bounds bounds) {
                return conditionalAssignment(lessThan(constant(0), left),
                        conditionalAssignment(lessThan(constant(0), right),
                                min(castToDouble(left.maxValue(bounds)), right.maxValue(bounds)),
                                min(castToDouble(left.maxValue(bounds)), negate(right.minValue(bounds)))),
                        constant(0.0));
            }

            @Override
            public TransTreeReturn<DoubleVariable> minValueInternal(Bounds bounds) {
                return conditionalAssignment(lessThan(constant(0), left), constant(0.0),
                        conditionalAssignment(lessThan(constant(0), right),
                                max(castToDouble(left.minValue(bounds)), negate(right.maxValue(bounds))),
                                max(castToDouble(left.minValue(bounds)), right.minValue(bounds))));
            }
        };
    }

    public static TransRemainder<DoubleVariable, IntVariable, DoubleVariable> getRemainderDI(
            TransTreeReturn<DoubleVariable> left, TransTreeReturn<IntVariable> right) {
        return new TransRemainder<>(left, right) {

            @Override
            public OutputBinOp<DoubleVariable, IntVariable, DoubleVariable> toOutputTreeReturnInternal() {
                return OutputBinOp.remainderDI(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
            }

            @Override
            public TransBinOp<DoubleVariable, IntVariable, DoubleVariable> applyTransformationInternal(Transformer t) {
                return TransTree.remainderDI(t.transform(left), t.transform(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }

            @Override
            public TransTreeReturn<DoubleVariable> maxValueInternal(Bounds bounds) {
                return conditionalAssignment(lessThan(constant(0), left),
                        conditionalAssignment(lessThan(constant(0), right),
                                min(left.maxValue(bounds), castToDouble(right.maxValue(bounds))),
                                min(left.maxValue(bounds), castToDouble(negate(right.minValue(bounds))))),
                        constant(0.0));
            }

            @Override
            public TransTreeReturn<DoubleVariable> minValueInternal(Bounds bounds) {
                return conditionalAssignment(lessThan(constant(0), left), constant(0.0),
                        conditionalAssignment(lessThan(constant(0), right),
                                max(left.minValue(bounds), castToDouble(negate(right.maxValue(bounds)))),
                                max(left.minValue(bounds), castToDouble(right.minValue(bounds)))));
            }
        };
    }

    public static TransRemainder<IntVariable, IntVariable, IntVariable> getRemainderII(
            TransTreeReturn<IntVariable> left, TransTreeReturn<IntVariable> right) {
        return new TransRemainder<>(left, right) {

            @Override
            public OutputBinOp<IntVariable, IntVariable, IntVariable> toOutputTreeReturnInternal() {
                return OutputBinOp.remainderII(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
            }

            @Override
            public TransBinOp<IntVariable, IntVariable, IntVariable> applyTransformationInternal(Transformer t) {
                return TransTree.remainderII(t.transform(left), t.transform(right));
            }

            @Override
            public Type<IntVariable> getOutputType() {
                return VariableType.IntVariable;
            }

            @Override
            public TransTreeReturn<IntVariable> maxValueInternal(Bounds bounds) {
                return conditionalAssignment(lessThan(constant(0), left),
                        conditionalAssignment(lessThan(constant(0), right),
                                min(left.maxValue(bounds), subtractII(right.maxValue(bounds), constant(1))),
                                min(left.maxValue(bounds), subtractII(negate(right.minValue(bounds)), constant(1)))),
                        constant(0));
            }

            @Override
            public TransTreeReturn<IntVariable> minValueInternal(Bounds bounds) {
                return conditionalAssignment(lessThan(constant(0), left), constant(0),
                        conditionalAssignment(lessThan(constant(0), right),
                                max(left.minValue(bounds), subtractII(constant(1), right.maxValue(bounds))),
                                max(left.minValue(bounds), addII(constant(1), right.minValue(bounds)))));
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static TransRemainder<? extends NumberVariable<?>, ? extends NumberVariable<?>, ? extends NumberVariable<?>> getRemainder(
            TransTreeReturn<? extends NumberVariable<?>> left, TransTreeReturn<? extends NumberVariable<?>> right) {
        if(left.getOutputType() == VariableType.IntVariable) {
            if(right.getOutputType() == VariableType.IntVariable) {
                return remainderII((TransTreeReturn<IntVariable>) left, (TransTreeReturn<IntVariable>) right);
            } else {
                return remainderID((TransTreeReturn<IntVariable>) left, (TransTreeReturn<DoubleVariable>) right);
            }
        } else {
            if(right.getOutputType() == VariableType.IntVariable) {
                return remainderDI((TransTreeReturn<DoubleVariable>) left, (TransTreeReturn<IntVariable>) right);
            } else {
                return remainderDD((TransTreeReturn<DoubleVariable>) left, (TransTreeReturn<DoubleVariable>) right);
            }
        }
    }
}
