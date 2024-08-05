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
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransExp;
import org.sandwood.compiler.trees.transformationTree.TransTree;

public class IRExp<A extends NumberVariable<A>> extends IRUniOp<A, DoubleVariable> {

    private IRExp(IRTreeReturn<A> input) {
        super(IRTreeType.EXP, input, VariableType.DoubleVariable, "exp");
    }

    @Override
    public TransExp<A> toTransformationTree() {
        return TransTree.exp(input.toTransformationTree());
    }

    static <A extends NumberVariable<A>> IRExp<A> getExp(IRTreeReturn<A> input) {
        return new IRExp<>(input);
    }

    @Override
    public IRExp<A> applyTransformation(TreeTransformation t) {
        return new IRExp<>(t.transformReturn(input));
    }
}
