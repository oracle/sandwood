/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.trees.outputTree.OutputNegate;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;

public class TransNegate<V extends NumberVariable<V>> extends TransUniOp<V, V> {

    private TransNegate(TransTreeReturn<V> input) {
        super(TransTreeType.NEGATE, input, input.getOutputType(), "Negate Value");
    }

    @Override
    public OutputNegate<V> toOutputTreeReturnInternal() {
        return OutputTree.negate(input.toOutputTreeReturnInternal());
    }

    static <V extends NumberVariable<V>> TransTreeReturn<V> getNegate(TransTreeReturn<V> toNegate) {
        if(toNegate.type == TransTreeType.NEGATE)
            return ((TransNegate<V>) toNegate).input;
        return new TransNegate<>(toNegate);
    }

    @Override
    public TransNegate<V> applyTransformationInternal(Transformer t) {
        return new TransNegate<>(t.transform(input));
    }

    @Override
    public TransTreeReturn<V> maxValue(Bounds bounds) {
        TransTreeReturn<V> tree = new TransNegate<>(input.minValue(bounds));
        bounds.addTransformedTree(this, tree);
        return tree;
    }

    @Override
    public TransTreeReturn<V> minValue(Bounds bounds) {
        TransTreeReturn<V> tree = new TransNegate<>(input.maxValue(bounds));
        bounds.addTransformedTree(this, tree);
        return tree;
    }
}
