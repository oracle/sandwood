/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;

public abstract class IRTreeReturn<X extends Variable<X>> extends IRTree {

    protected IRTreeReturn(IRTreeType type) {
        super(type);
    }

    public boolean returning(VariableDescription<X> varDesc) {
        return false;
    }
    
    public abstract Type<X> getOutputType();

    @Override
    public abstract TransTreeReturn<X> toTransformationTree();

    @Override
    public abstract IRTreeReturn<X> applyTransformation(TreeTransformation t);
}
