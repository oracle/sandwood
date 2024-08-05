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
import org.sandwood.compiler.trees.outputTree.OutputLog;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;

public class TransLog<A extends NumberVariable<A>> extends TransUniOp<A, DoubleVariable> {
    private TransLog(TransTreeReturn<A> input) {
        super(TransTreeType.LOG, input, VariableType.DoubleVariable, "log");
    }

    @Override
    public OutputLog<A> toOutputTreeReturnInternal() {
        return OutputTree.log(input.toOutputTreeReturnInternal());
    }

    static <A extends NumberVariable<A>> TransLog<A> getLog(TransTreeReturn<A> input) {
        return new TransLog<>(input);
    }

    @Override
    public TransLog<A> applyTransformationInternal(Transformer t) {
        return new TransLog<>(t.transform(input));
    }

    @Override
    public TransTreeReturn<DoubleVariable> maxValue(Bounds bounds) {
        TransTreeReturn<DoubleVariable> tree = new TransLog<>(input.maxValue(bounds));
        bounds.addTransformedTree(this, tree);
        return tree;
    }

    @Override
    public TransTreeReturn<DoubleVariable> minValue(Bounds bounds) {
        return new TransLog<>(input.minValue(bounds));
    }
}
