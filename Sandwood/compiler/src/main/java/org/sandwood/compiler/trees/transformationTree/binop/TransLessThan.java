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
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.outputTree.OutputBinOp;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;

public class TransLessThan<L extends NumberVariable<L>, R extends NumberVariable<R>>
        extends TransBinOp<L, R, BooleanVariable> {

    public TransLessThan(TransTreeReturn<L> left, TransTreeReturn<R> right) {
        super(left, right, false, TransTreeType.LESS_THAN);
    }

    @Override
    protected String getOp() {
        return "<";
    }

    @Override
    public OutputBinOp<L, R, BooleanVariable> toOutputTreeReturnInternal() {
        return OutputBinOp.lessThan(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
    }

    @Override
    public TransLessThan<L, R> applyTransformationInternal(Transformer t) {
        return TransTree.lessThan(t.transform(left), t.transform(right));
    }

    @Override
    public TransTreeReturn<BooleanVariable> maxValueInternal(Bounds bounds) {
        throw new CompilerException("max value not supported for boolean values");
    }

    @Override
    public TransTreeReturn<BooleanVariable> minValueInternal(Bounds bounds) {
        throw new CompilerException("min value not supported for boolean values");
    }

    public static <L extends NumberVariable<L>, R extends NumberVariable<R>> TransLessThan<L, R> getLessThan(
            TransTreeReturn<L> left, TransTreeReturn<R> right) {
        return new TransLessThan<>(left, right);
    }

    @Override
    public Type<BooleanVariable> getOutputType() {
        return VariableType.BooleanVariable;
    }
}
