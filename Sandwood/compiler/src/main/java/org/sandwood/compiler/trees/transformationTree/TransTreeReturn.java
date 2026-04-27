/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import org.sandwood.common.execution.ExecutionType;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.outputTree.OutputTreeReturn;
import org.sandwood.compiler.trees.transformationTree.transformers.AccessRedirection;
import org.sandwood.compiler.trees.transformationTree.transformers.LocalRng;
import org.sandwood.compiler.trees.transformationTree.transformers.ParallelIndexes;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;

public abstract class TransTreeReturn<X extends Variable<X>> extends TransTree<TransTreeReturn<X>> {

    private boolean deterministic = true;
    private boolean deterministicSet = false;

    protected TransTreeReturn(TransTreeType type, int size) {
        super(type, size);
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

    public boolean deterministic() {
        if(!deterministicSet) {
            for(TransTree<?> t:getChildren())
                deterministic = deterministic && ((TransTreeReturn<?>) t).deterministic();
            deterministicSet = true;
        }
        return deterministic;
    }

    @Override
    public OutputTreeReturn<X> toOutputTree(RNGLocation rngLocation, TreeLocation treeLocation, ExecutionType target) {
        return toOutputTreeReturn(rngLocation, treeLocation, target);
    }

    public OutputTreeReturn<X> toOutputTreeReturn(RNGLocation rngLocation, TreeLocation treeLocation, ExecutionType target) {
        TransTreeReturn<X> tree = switch(target) {
            case SingleThreadCPU -> this;
            case MultiThreadCPU -> {
                TransTreeReturn<X> t = new ParallelIndexes().transform(this);
                if(rngLocation == RNGLocation.LOCAL)
                    t = new LocalRng().transform(t);
                yield t;
            }
            case GPU -> throw new CompilerException("Unable to transform for target: " + target);
        };
        AccessRedirection ar = new AccessRedirection(treeLocation);
        tree = ar.transform(tree);
        return tree.toOutputTreeReturnInternal();
    }
}
