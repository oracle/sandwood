/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;

public class IRCastToInteger extends IRUniOp<DoubleVariable, IntVariable> {

    private IRCastToInteger(IRTreeReturn<DoubleVariable> input) {
        super(IRTreeType.CAST_INT, input, VariableType.IntVariable, "Cast to Integer");
    }

    @Override
    public TransTreeReturn<IntVariable> toTransformationTree() {
        return TransTree.castToInteger(input.toTransformationTree());
    }

    static IRCastToInteger getCastToInteger(IRTreeReturn<DoubleVariable> toCast) {
        return new IRCastToInteger(toCast);
    }

    @Override
    public IRCastToInteger applyTransformation(TreeTransformation t) {
        return new IRCastToInteger(t.transformReturn(input));
    }
}
