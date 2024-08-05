/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.outputTree.OutputNegateBoolean;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;

public class TransNegateBoolean extends TransUniOp<BooleanVariable, BooleanVariable> {

    private TransNegateBoolean(TransTreeReturn<BooleanVariable> input) {
        super(TransTreeType.NEGATE_BOOLEAN, input, input.getOutputType(), "Negate Value");
    }

    @Override
    public OutputNegateBoolean toOutputTreeReturnInternal() {
        return OutputTree.negateBoolean(input.toOutputTreeReturnInternal());
    }

    static TransTreeReturn<BooleanVariable> getNegate(TransTreeReturn<BooleanVariable> toNegate) {
        if(toNegate.type == TransTreeType.NEGATE_BOOLEAN)
            return ((TransNegateBoolean) toNegate).input;
        return new TransNegateBoolean(toNegate);
    }

    @Override
    public TransNegateBoolean applyTransformationInternal(Transformer t) {
        return new TransNegateBoolean(t.transform(input));
    }

    @Override
    public TransTreeReturn<BooleanVariable> maxValue(Bounds bounds) {
        throw new CompilerException("Max value is not supported on boolean values.");
    }

    @Override
    public TransTreeReturn<BooleanVariable> minValue(Bounds bounds) {
        throw new CompilerException("min value is not supported on boolean values.");
    }
}
