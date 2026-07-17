/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.transformers;

import static org.sandwood.compiler.trees.transformationTree.TransTree.load;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.internal.RandomNumberGenerator;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.trees.transformationTree.TransLoad;
import org.sandwood.compiler.trees.transformationTree.TransTree.TransTreeType;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;

/**
 * A transformation to change the types of RNG loads in functions where the RNG is passed in as an argument to local.
 */
public class LocalRng extends Transformer {

    private static final VariableDescription<RandomNumberGenerator> rngName = VariableNames.rngName();

    @Override
    protected TransTreeVoid transformVoid(TransTreeVoid tree) {
        return tree.applyTransformation(this);
    }

    @Override
    protected <X extends Variable<X>> TransTreeReturn<X> transformReturn(TransTreeReturn<X> tree) {
        if(tree.type == TransTreeType.LOAD) {
            TransLoad<X> l = (TransLoad<X>) tree;
            if(l.varDesc.equals(rngName))
                return (TransTreeReturn<X>) load(VariableNames.localRngName(0));
        }

        return tree.applyTransformation(this);
    }
}
