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

public abstract class TransAdd<L extends NumberVariable<L>, R extends NumberVariable<R>, RT extends NumberVariable<RT>>
        extends TransBinOp<L, R, RT> {
    public TransAdd(TransTreeReturn<L> left, TransTreeReturn<R> right) {
        super(left, right, true, TransTreeType.ADD);
    }

    @Override
    protected String getOp() {
        return "+";
    }

    public static TransAdd<DoubleVariable, DoubleVariable, DoubleVariable> getAddDD(
            TransTreeReturn<DoubleVariable> left, TransTreeReturn<DoubleVariable> right) {
        return new TransAdd<>(left, right) {

            @Override
            public OutputBinOp<DoubleVariable, DoubleVariable, DoubleVariable> toOutputTreeReturnInternal() {
                return OutputBinOp.addDD(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
            }

            @Override
            public TransAdd<DoubleVariable, DoubleVariable, DoubleVariable> applyTransformationInternal(Transformer t) {
                return TransTree.addDD(t.transform(left), t.transform(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }

            @Override
            public TransTreeReturn<DoubleVariable> maxValueInternal(Bounds bounds) {
                return TransTree.addDD(left.maxValue(bounds), right.maxValue(bounds));
            }

            @Override
            public TransTreeReturn<DoubleVariable> minValueInternal(Bounds bounds) {
                return TransTree.addDD(left.minValue(bounds), right.minValue(bounds));
            }
        };
    }

    public static TransAdd<IntVariable, DoubleVariable, DoubleVariable> getAddID(TransTreeReturn<IntVariable> left,
            TransTreeReturn<DoubleVariable> right) {
        return new TransAdd<>(left, right) {

            @Override
            public OutputBinOp<IntVariable, DoubleVariable, DoubleVariable> toOutputTreeReturnInternal() {
                return OutputBinOp.addID(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
            }

            @Override
            public TransAdd<IntVariable, DoubleVariable, DoubleVariable> applyTransformationInternal(Transformer t) {
                return TransTree.addID(t.transform(left), t.transform(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }

            @Override
            public TransTreeReturn<DoubleVariable> maxValueInternal(Bounds bounds) {
                return TransTree.addID(left.maxValue(bounds), right.maxValue(bounds));
            }

            @Override
            public TransTreeReturn<DoubleVariable> minValueInternal(Bounds bounds) {
                return TransTree.addID(left.minValue(bounds), right.minValue(bounds));
            }
        };
    }

    public static TransAdd<DoubleVariable, IntVariable, DoubleVariable> getAddDI(TransTreeReturn<DoubleVariable> left,
            TransTreeReturn<IntVariable> right) {
        return new TransAdd<>(left, right) {

            @Override
            public OutputBinOp<DoubleVariable, IntVariable, DoubleVariable> toOutputTreeReturnInternal() {
                return OutputBinOp.addDI(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
            }

            @Override
            public TransAdd<DoubleVariable, IntVariable, DoubleVariable> applyTransformationInternal(Transformer t) {
                return TransTree.addDI(t.transform(left), t.transform(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }

            @Override
            public TransTreeReturn<DoubleVariable> maxValueInternal(Bounds bounds) {
                return TransTree.addDI(left.maxValue(bounds), right.maxValue(bounds));
            }

            @Override
            public TransTreeReturn<DoubleVariable> minValueInternal(Bounds bounds) {
                return TransTree.addDI(left.minValue(bounds), right.minValue(bounds));
            }
        };
    }

    public static TransAdd<IntVariable, IntVariable, IntVariable> getAddII(TransTreeReturn<IntVariable> left,
            TransTreeReturn<IntVariable> right) {
        return new TransAdd<>(left, right) {

            @Override
            public OutputBinOp<IntVariable, IntVariable, IntVariable> toOutputTreeReturnInternal() {
                return OutputBinOp.addII(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
            }

            @Override
            public TransAdd<IntVariable, IntVariable, IntVariable> applyTransformationInternal(Transformer t) {
                return TransTree.addII(t.transform(left), t.transform(right));
            }

            @Override
            public Type<IntVariable> getOutputType() {
                return VariableType.IntVariable;
            }

            @Override
            public TransTreeReturn<IntVariable> maxValueInternal(Bounds bounds) {
                return TransTree.addII(left.maxValue(bounds), right.maxValue(bounds));
            }

            @Override
            public TransTreeReturn<IntVariable> minValueInternal(Bounds bounds) {
                return TransTree.addII(left.minValue(bounds), right.minValue(bounds));
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static TransAdd<? extends NumberVariable<?>, ? extends NumberVariable<?>, ? extends NumberVariable<?>> getAdd(
            TransTreeReturn<? extends NumberVariable<?>> left, TransTreeReturn<? extends NumberVariable<?>> right) {
        if(left.getOutputType() == VariableType.IntVariable) {
            if(right.getOutputType() == VariableType.IntVariable) {
                return TransTree.addII((TransTreeReturn<IntVariable>) left, (TransTreeReturn<IntVariable>) right);
            } else {
                return TransTree.addID((TransTreeReturn<IntVariable>) left, (TransTreeReturn<DoubleVariable>) right);
            }
        } else {
            if(right.getOutputType() == VariableType.IntVariable) {
                return TransTree.addDI((TransTreeReturn<DoubleVariable>) left, (TransTreeReturn<IntVariable>) right);
            } else {
                return TransTree.addDD((TransTreeReturn<DoubleVariable>) left, (TransTreeReturn<DoubleVariable>) right);
            }
        }
    }

}
