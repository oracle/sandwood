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

public class IRCastToDouble extends IRUniOp<IntVariable, DoubleVariable> {

    private IRCastToDouble(IRTreeReturn<IntVariable> input) {
        super(IRTreeType.CAST_DOUBLE, input, VariableType.DoubleVariable, "Cast to double");
    }

    @Override
    public TransTreeReturn<DoubleVariable> toTransformationTree() {
        return TransTree.castToDouble(input.toTransformationTree());
    }

    static IRTreeReturn<DoubleVariable> getCastToDouble(IRTreeReturn<IntVariable> toCast) {
        if(toCast.type == IRTreeType.CONST_INT) {
            double d = ((IRConstInt) toCast).value;
            return IRTree.constant(d);
        } else
            return new IRCastToDouble(toCast);
    }

    @Override
    public IRTreeReturn<DoubleVariable> applyTransformation(TreeTransformation t) {
        IRTreeReturn<IntVariable> tree = t.transformReturn(input);
        if(tree.type == IRTreeType.CONST_INT) {
            double d = ((IRConstInt) tree).value;
            return IRTree.constant(d);
        } else
            return new IRCastToDouble(t.transformReturn(input));
    }
}
