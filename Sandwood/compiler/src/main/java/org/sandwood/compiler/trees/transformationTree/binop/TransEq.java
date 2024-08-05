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
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.ScalarVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.outputTree.OutputBinOp;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;

public class TransEq<A extends ScalarVariable<A>, B extends ScalarVariable<B>>
        extends TransBinOp<A, B, BooleanVariable> {

    public TransEq(TransTreeReturn<A> left, TransTreeReturn<B> right) {
        super(left, right, true, TransTreeType.EQUALITY);
    }

    @Override
    protected String getOp() {
        return "==";
    }

    @Override
    public OutputBinOp<A, B, BooleanVariable> toOutputTreeReturnInternal() {
        return OutputBinOp.eq(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
    }

    @Override
    public TransTreeReturn<BooleanVariable> applyTransformationInternal(Transformer t) {
        return TransTree.eq(t.transform(left), t.transform(right));
    }

    @Override
    public Type<BooleanVariable> getOutputType() {
        return VariableType.BooleanVariable;
    }

    public static <A extends ScalarVariable<A>, B extends ScalarVariable<B>> TransEq<A, B> getEq(
            TransTreeReturn<A> left, TransTreeReturn<B> right) {
        return new TransEq<>(left, right);
    }

    @Override
    public TransTreeReturn<BooleanVariable> maxValueInternal(Bounds bounds) {
        throw new CompilerException("max value not supported for boolean values");
    }

    @Override
    public TransTreeReturn<BooleanVariable> minValueInternal(Bounds bounds) {
        throw new CompilerException("min value not supported for boolean values");
    }
}
