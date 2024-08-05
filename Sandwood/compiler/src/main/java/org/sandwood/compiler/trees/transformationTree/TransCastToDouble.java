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
import org.sandwood.compiler.trees.outputTree.OutputCastToDouble;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;

public class TransCastToDouble extends TransUniOp<IntVariable, DoubleVariable> {

    private TransCastToDouble(TransTreeReturn<IntVariable> input) {
        super(TransTreeType.CAST_DOUBLE, input, VariableType.DoubleVariable, "Cast to double");
    }

    @Override
    public OutputCastToDouble toOutputTreeReturnInternal() {
        return OutputTree.castToDouble(input.toOutputTreeReturnInternal());
    }

    static TransTreeReturn<DoubleVariable> getCastToDouble(TransTreeReturn<IntVariable> input) {
        if(input.type == TransTreeType.CONST_INT) {
            double d = ((TransConstInt) input).value;
            return TransTree.constant(d);
        } else
            return new TransCastToDouble(input);
    }

    @Override
    public TransTreeReturn<DoubleVariable> applyTransformationInternal(Transformer t) {
        TransTreeReturn<IntVariable> tree = t.transform(input);
        if(tree.type == TransTreeType.CONST_INT) {
            double d = ((TransConstInt) tree).value;
            return TransTree.constant(d);
        } else
            return new TransCastToDouble(t.transform(input));
    }

    @Override
    public TransTreeReturn<DoubleVariable> maxValue(Bounds bounds) {
        TransTreeReturn<DoubleVariable> tree = new TransCastToDouble(input.maxValue(bounds));
        bounds.addTransformedTree(this, tree);
        return tree;
    }

    @Override
    public TransTreeReturn<DoubleVariable> minValue(Bounds bounds) {
        TransTreeReturn<DoubleVariable> tree = new TransCastToDouble(input.minValue(bounds));
        bounds.addTransformedTree(this, tree);
        return tree;
    }
}
