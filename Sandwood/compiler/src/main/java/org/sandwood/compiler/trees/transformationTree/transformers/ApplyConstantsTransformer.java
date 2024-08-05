/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.transformers;

import java.util.Map;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.trees.transformationTree.TransLoad;
import org.sandwood.compiler.trees.transformationTree.TransStore;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;

public class ApplyConstantsTransformer extends Transformer {

    private final Map<VariableDescription<?>, TransTreeReturn<?>> constants;

    public ApplyConstantsTransformer(Map<VariableDescription<?>, TransTreeReturn<?>> constants) {
        this.constants = constants;
    }

    @Override
    protected TransTreeVoid transformVoid(TransTreeVoid tree) {
        switch(tree.type) {
            case STORE: {
                TransStore<?> s = (TransStore<?>) tree;
                if(constants.containsKey(s.varDesc)) {
                    s.addNodes(visitedNodes);
                    return TransTree.nop();
                } else
                    return tree.applyTransformation(this);
            }
            default:
                return tree.applyTransformation(this);
        }
    }

    @Override
    protected <X extends Variable<X>> TransTreeReturn<X> transformReturn(TransTreeReturn<X> tree) {
        switch(tree.type) {
            case LOAD: {
                TransLoad<X> l = (TransLoad<X>) tree;
                @SuppressWarnings("unchecked")
                TransTreeReturn<X> v = (TransTreeReturn<X>) constants.get(l.varDesc);
                if(v == null)
                    return l.applyTransformation(this);
                else {
                    l.addNodes(visitedNodes);
                    return v.copy();
                }
            }
            default:
                return tree.applyTransformation(this);
        }
    }
}
