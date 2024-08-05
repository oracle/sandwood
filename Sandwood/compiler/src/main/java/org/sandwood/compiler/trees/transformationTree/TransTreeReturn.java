/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.HashSet;

import org.sandwood.common.execution.ExecutionType;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.outputTree.OutputTreeReturn;
import org.sandwood.compiler.trees.transformationTree.transformers.ParallelIndexes;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;

public abstract class TransTreeReturn<X extends Variable<X>> extends TransTree<TransTreeReturn<X>> {

    private boolean deterministic = true;
    private boolean deterministicSet = false;
    private final int size;

    protected TransTreeReturn(TransTreeType type, int size) {
        super(type);
        this.size = size;
    }

    public abstract Type<X> getOutputType();

    public abstract OutputTreeReturn<X> toOutputTreeReturnInternal();

    @Override
    public OutputTree toOutputTreeInternal() {
        return toOutputTreeReturnInternal();
    }

    // TODO create a numeric super class to hold these. This will require the creation of supporting interfaces.
    public abstract TransTreeReturn<X> maxValue(Bounds bounds);

    public abstract TransTreeReturn<X> minValue(Bounds bounds);

    public final int treeSize() {
        return size;
    }

    public boolean deterministic() {
        if(!deterministicSet) {
            for(TransTree<?> t:getChildren())
                deterministic = deterministic && ((TransTreeReturn<?>) t).deterministic();
            deterministicSet = true;
        }
        return deterministic;
    }

    @Override
    public OutputTreeReturn<X> toOutputTree(ExecutionType target) {
        return toOutputTreeReturn(target);
    }

    public OutputTreeReturn<X> toOutputTreeReturn(ExecutionType target) {
        switch(target) {
            case SingleThreadCPU:
                return toOutputTreeReturnInternal();
            case MultiThreadCPU:
                TransTreeReturn<X> tree = new ParallelIndexes().transform(this, new HashSet<>());
                return tree.toOutputTreeReturnInternal();
            case GPU:
            default:
                throw new CompilerException("Unable to transform for target: " + target);
        }
    }
}
