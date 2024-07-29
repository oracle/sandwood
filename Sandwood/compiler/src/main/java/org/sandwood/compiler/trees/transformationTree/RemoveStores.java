/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableName;
import org.sandwood.compiler.trees.transformationTree.TransTree.TransTreeType;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;

public class RemoveStores extends Transformer {

    private final Set<VariableName> toRemove;

    public RemoveStores(Set<VariableName> toRemove) {
        this.toRemove = toRemove;
    }

    @Override
    protected TransTreeVoid transformVoid(TransTreeVoid tree) {
        switch(tree.type) {
            case ARRAY_PUT: {
                TransArrayPut<?> put = (TransArrayPut<?>) tree;

                // Get the array name
                TransTreeReturn<?> source = put.array;
                while(source.type == TransTreeType.ARRAY_GET)
                    source = ((TransArrayGet<?>) source).array;
                VariableDescription<?> arrayDesc = ((TransLoad<?>) source).varDesc;

                // Decide if the store should be removed
                if(toRemove.contains(arrayDesc.name))
                    return TransTree.nop();
                else
                    return tree.applyTransformation(this);
            }
            case STORE: {
                TransStore<?> s = (TransStore<?>) tree;
                if(toRemove.contains(s.varDesc.name))
                    return TransTree.nop();
                else
                    return tree.applyTransformation(this);
            }
            default:
                return tree.applyTransformation(this);

        }
    }

    @Override
    protected <X extends Variable<X>> TransTreeReturn<X> transformReturn(TransTreeReturn<X> tree) {
        return tree.applyTransformation(this);
    }
}
