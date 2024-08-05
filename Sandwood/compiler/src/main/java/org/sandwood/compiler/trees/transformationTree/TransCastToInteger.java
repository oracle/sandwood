/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.outputTree.OutputCastToInteger;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;

public class TransCastToInteger extends TransUniOp<DoubleVariable, IntVariable> {

    private TransCastToInteger(TransTreeReturn<DoubleVariable> input) {
        super(TransTreeType.CAST_INT, input, VariableType.IntVariable, "Cast to Integer");
    }

    @Override
    public OutputCastToInteger toOutputTreeReturnInternal() {
        return OutputTree.castToInteger(input.toOutputTreeReturnInternal());
    }

    static TransTreeReturn<IntVariable> getCastToInteger(TransTreeReturn<DoubleVariable> input) {
        if(input.type == TransTreeType.CONST_DOUBLE) {
            int i = (int) ((TransConstDouble) input).value;
            return TransTree.constant(i);
        } else if(input.type == TransTreeType.CAST_DOUBLE) {
            return ((TransCastToDouble) input).input;
        }
        return new TransCastToInteger(input);
    }

    @Override
    public TransTreeReturn<IntVariable> applyTransformationInternal(Transformer t) {
        return new TransCastToInteger(t.transform(input));
    }

    @Override
    public TransTreeReturn<IntVariable> maxValue(Bounds bounds) {
        TransTreeReturn<IntVariable> tree = new TransCastToInteger(input.maxValue(bounds));
        bounds.addTransformedTree(this, tree);
        return tree;
    }

    @Override
    public TransTreeReturn<IntVariable> minValue(Bounds bounds) {
        TransTreeReturn<IntVariable> tree = new TransCastToInteger(input.minValue(bounds));
        bounds.addTransformedTree(this, tree);
        return tree;
    }
}
