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
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.trees.outputTree.OutputExp;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;

public class TransExp<A extends NumberVariable<A>> extends TransUniOp<A, DoubleVariable> {

    private TransExp(TransTreeReturn<A> input) {
        super(TransTreeType.EXP, input, VariableType.DoubleVariable, "exp");
    }

    @Override
    public OutputExp<A> toOutputTreeReturnInternal() {
        return OutputTree.exp(input.toOutputTreeReturnInternal());
    }

    static <A extends NumberVariable<A>> TransExp<A> getExp(TransTreeReturn<A> input) {
        return new TransExp<>(input);
    }

    @Override
    public TransExp<A> applyTransformationInternal(Transformer t) {
        return new TransExp<>(t.transform(input));
    }

    @Override
    public TransTreeReturn<DoubleVariable> maxValue(Bounds bounds) {
        TransTreeReturn<DoubleVariable> tree = new TransExp<>(input.maxValue(bounds));
        bounds.addTransformedTree(this, tree);
        return tree;
    }

    @Override
    public TransTreeReturn<DoubleVariable> minValue(Bounds bounds) {
        TransTreeReturn<DoubleVariable> tree = new TransExp<>(input.minValue(bounds));
        bounds.addTransformedTree(this, tree);
        return tree;
    }
}
