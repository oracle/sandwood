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
import org.sandwood.compiler.trees.transformationTree.TransLog;
import org.sandwood.compiler.trees.transformationTree.TransTree;

public class IRLog<A extends NumberVariable<A>> extends IRUniOp<A, DoubleVariable> {
    private IRLog(IRTreeReturn<A> input) {
        super(IRTreeType.LOG, input, VariableType.DoubleVariable, "log");
    }

    @Override
    public TransLog<A> toTransformationTree() {
        return TransTree.log(input.toTransformationTree());
    }

    static <A extends NumberVariable<A>> IRLog<A> getLog(IRTreeReturn<A> input) {
        return new IRLog<>(input);
    }

    @Override
    public IRLog<A> applyTransformation(TreeTransformation t) {
        return new IRLog<>(t.transformReturn(input));
    }
}
