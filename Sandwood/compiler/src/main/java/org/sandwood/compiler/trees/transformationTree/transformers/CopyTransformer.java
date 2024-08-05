/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.transformers;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;

public class CopyTransformer extends Transformer {

    @Override
    protected TransTreeVoid transformVoid(TransTreeVoid tree) {
        return tree.applyTransformation(this);
    }

    @Override
    protected <X extends Variable<X>> TransTreeReturn<X> transformReturn(TransTreeReturn<X> tree) {
        return tree.applyTransformation(this);
    }
}
