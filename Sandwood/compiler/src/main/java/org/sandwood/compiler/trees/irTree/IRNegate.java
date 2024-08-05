/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;

public class IRNegate<A extends NumberVariable<A>> extends IRUniOp<A, A> {

    private IRNegate(IRTreeReturn<A> input) {
        super(IRTreeType.NEGATE, input, input.getOutputType(), "negate");
    }

    @Override
    public TransTreeReturn<A> toTransformationTree() {
        return TransTree.negate(input.toTransformationTree());
    }

    static <A extends NumberVariable<A>> IRNegate<A> getNegate(IRTreeReturn<A> input) {
        return new IRNegate<>(input);
    }

    @Override
    public IRNegate<A> applyTransformation(TreeTransformation t) {
        return new IRNegate<>(t.transformReturn(input));
    }
}
