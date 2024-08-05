/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
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

public abstract class TransSubtract<L extends NumberVariable<L>, R extends NumberVariable<R>, RT extends NumberVariable<RT>>
        extends TransBinOp<L, R, RT> {
    public TransSubtract(TransTreeReturn<L> left, TransTreeReturn<R> right) {
        super(left, right, false, TransTreeType.SUBTRACT);
    }

    @Override
    protected String getOp() {
        return "-";
    }

    public static TransSubtract<DoubleVariable, DoubleVariable, DoubleVariable> getSubtractDD(
            TransTreeReturn<DoubleVariable> left, TransTreeReturn<DoubleVariable> right) {
        return new TransSubtract<>(left, right) {
            @Override
            public OutputBinOp<DoubleVariable, DoubleVariable, DoubleVariable> toOutputTreeReturnInternal() {
                return OutputBinOp.subtractDD(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
            }

            @Override
            public TransSubtract<DoubleVariable, DoubleVariable, DoubleVariable> applyTransformationInternal(
                    Transformer t) {
                return TransTree.subtractDD(t.transform(left), t.transform(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }

            @Override
            public TransTreeReturn<DoubleVariable> maxValueInternal(Bounds bounds) {
                return TransTree.subtractDD(left.maxValue(bounds), right.minValue(bounds));
            }

            @Override
            public TransTreeReturn<DoubleVariable> minValueInternal(Bounds bounds) {
                return TransTree.subtractDD(left.minValue(bounds), right.maxValue(bounds));
            }
        };
    }

    public static TransSubtract<IntVariable, DoubleVariable, DoubleVariable> getSubtractID(
            TransTreeReturn<IntVariable> left, TransTreeReturn<DoubleVariable> right) {
        return new TransSubtract<>(left, right) {
            @Override
            public OutputBinOp<IntVariable, DoubleVariable, DoubleVariable> toOutputTreeReturnInternal() {
                return OutputBinOp.subtractID(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
            }

            @Override
            public TransSubtract<IntVariable, DoubleVariable, DoubleVariable> applyTransformationInternal(
                    Transformer t) {
                return TransTree.subtractID(t.transform(left), t.transform(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }

            @Override
            public TransTreeReturn<DoubleVariable> maxValueInternal(Bounds bounds) {
                return TransTree.subtractID(left.maxValue(bounds), right.minValue(bounds));
            }

            @Override
            public TransTreeReturn<DoubleVariable> minValueInternal(Bounds bounds) {
                return TransTree.subtractID(left.minValue(bounds), right.maxValue(bounds));
            }
        };
    }

    public static TransSubtract<DoubleVariable, IntVariable, DoubleVariable> getSubtractDI(
            TransTreeReturn<DoubleVariable> left, TransTreeReturn<IntVariable> right) {
        return new TransSubtract<>(left, right) {
            @Override
            public OutputBinOp<DoubleVariable, IntVariable, DoubleVariable> toOutputTreeReturnInternal() {
                return OutputBinOp.subtractDI(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
            }

            @Override
            public TransSubtract<DoubleVariable, IntVariable, DoubleVariable> applyTransformationInternal(
                    Transformer t) {
                return TransTree.subtractDI(t.transform(left), t.transform(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }

            @Override
            public TransTreeReturn<DoubleVariable> maxValueInternal(Bounds bounds) {
                return TransTree.subtractDI(left.maxValue(bounds), right.minValue(bounds));
            }

            @Override
            public TransTreeReturn<DoubleVariable> minValueInternal(Bounds bounds) {
                return TransTree.subtractDI(left.minValue(bounds), right.maxValue(bounds));
            }
        };
    }

    public static TransSubtract<IntVariable, IntVariable, IntVariable> getSubtractII(TransTreeReturn<IntVariable> left,
            TransTreeReturn<IntVariable> right) {
        return new TransSubtract<>(left, right) {
            @Override
            public OutputBinOp<IntVariable, IntVariable, IntVariable> toOutputTreeReturnInternal() {
                return OutputBinOp.subtractII(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
            }

            @Override
            public TransSubtract<IntVariable, IntVariable, IntVariable> applyTransformationInternal(Transformer t) {
                return TransTree.subtractII(t.transform(left), t.transform(right));
            }

            @Override
            public Type<IntVariable> getOutputType() {
                return VariableType.IntVariable;
            }

            @Override
            public TransTreeReturn<IntVariable> maxValueInternal(Bounds bounds) {
                return TransTree.subtractII(left.maxValue(bounds), right.minValue(bounds));
            }

            @Override
            public TransTreeReturn<IntVariable> minValueInternal(Bounds bounds) {
                return TransTree.subtractII(left.minValue(bounds), right.maxValue(bounds));
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static TransSubtract<? extends NumberVariable<?>, ? extends NumberVariable<?>, ? extends NumberVariable<?>> getSubtract(
            TransTreeReturn<? extends NumberVariable<?>> left, TransTreeReturn<? extends NumberVariable<?>> right) {
        if(left.getOutputType() == VariableType.IntVariable) {
            if(right.getOutputType() == VariableType.IntVariable) {
                return TransTree.subtractII((TransTreeReturn<IntVariable>) left, (TransTreeReturn<IntVariable>) right);
            } else {
                return TransTree.subtractID((TransTreeReturn<IntVariable>) left,
                        (TransTreeReturn<DoubleVariable>) right);
            }
        } else {
            if(right.getOutputType() == VariableType.IntVariable) {
                return TransTree.subtractDI((TransTreeReturn<DoubleVariable>) left,
                        (TransTreeReturn<IntVariable>) right);
            } else {
                return TransTree.subtractDD((TransTreeReturn<DoubleVariable>) left,
                        (TransTreeReturn<DoubleVariable>) right);
            }
        }
    }
}
