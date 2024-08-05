/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;

public class IRNegateBoolean extends IRUniOp<BooleanVariable, BooleanVariable> {

    private IRNegateBoolean(IRTreeReturn<BooleanVariable> input) {
        super(IRTreeType.NEGATE, input, input.getOutputType(), "negate");
    }

    @Override
    public TransTreeReturn<BooleanVariable> toTransformationTree() {
        return TransTree.negateBoolean(input.toTransformationTree());
    }

    static IRNegateBoolean getNegate(IRTreeReturn<BooleanVariable> input) {
        return new IRNegateBoolean(input);
    }

    @Override
    public IRNegateBoolean applyTransformation(TreeTransformation t) {
        return new IRNegateBoolean(t.transformReturn(input));
    }
}
