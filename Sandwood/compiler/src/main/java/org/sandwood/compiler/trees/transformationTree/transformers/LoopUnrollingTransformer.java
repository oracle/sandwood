/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.transformers;

import static org.sandwood.compiler.trees.transformationTree.TransTree.constant;
import static org.sandwood.compiler.trees.transformationTree.TransTree.initializeVariable;
import static org.sandwood.compiler.trees.transformationTree.TransTree.sequential;
import static org.sandwood.compiler.trees.transformationTree.TransTree.store;
import static org.sandwood.compiler.trees.transformationTree.TransTree.treeScope;

import java.util.ArrayList;
import java.util.List;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.transformationTree.TransConstInt;
import org.sandwood.compiler.trees.transformationTree.TransFor;
import org.sandwood.compiler.trees.transformationTree.TransTree.TransTreeType;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;

public class LoopUnrollingTransformer extends Transformer {

    private static final int maxLoops = 4;

    public LoopUnrollingTransformer() {}

    @Override
    protected TransTreeVoid transformVoid(TransTreeVoid tree) {
        switch(tree.type) {
            case FOR:
                TransFor tf = (TransFor) tree;
                // TODO weaken this so that we can unroll parallel loops and execute them as parallel tasks.
                if(tf.parallel)
                    return tree.applyTransformation(this);

                if(!(tf.start.type == TransTreeType.CONST_INT && tf.step.type == TransTreeType.CONST_INT
                        && tf.end.type == TransTreeType.CONST_INT))
                    return tree.applyTransformation(this);

                TransConstInt startTree = (TransConstInt) tf.start;
                TransConstInt stepTree = (TransConstInt) tf.step;
                TransConstInt endTree = (TransConstInt) tf.end;

                if(tf.incrementing) {
                    int start = startTree.value;
                    int step = stepTree.value;
                    int end = endTree.value + step; // Plus step to account for the end being inclusive at this stage.

                    if((end - start) / step > maxLoops)
                        return tree.applyTransformation(this);

                    visitedNodes.add(tf);
                    visitedNodes.add(startTree);
                    visitedNodes.add(stepTree);
                    visitedNodes.add(endTree);

                    List<TransTreeVoid> loopParts = new ArrayList<>();

                    TransTreeVoid body = tf.body.applyTransformation(this);
                    loopParts.add(initializeVariable(tf.indexDesc, constant(start), Tree.NoComment));
                    loopParts.add(treeScope(body, Tree.NoComment));

                    for(int i = start + step; i < end; i += step) {
                        loopParts.add(store(tf.indexDesc, constant(i), Tree.NoComment));
                        loopParts.add(treeScope(body.copy(), Tree.NoComment));
                    }

                    return treeScope(sequential(loopParts, "Unrolled loop"), Tree.NoComment);
                } else { // Handle the decrementing case.
                    int start = startTree.value;
                    int step = stepTree.value;
                    int end = endTree.value - step;// Minus step to account for the end being inclusive at this stage.

                    if((start - end) / step > maxLoops)
                        return tree.applyTransformation(this);

                    visitedNodes.add(tf);
                    visitedNodes.add(startTree);
                    visitedNodes.add(stepTree);
                    visitedNodes.add(endTree);

                    List<TransTreeVoid> loopParts = new ArrayList<>();

                    TransTreeVoid body = tf.body.applyTransformation(this);
                    loopParts.add(initializeVariable(tf.indexDesc, constant(start), Tree.NoComment));
                    loopParts.add(treeScope(body, Tree.NoComment));

                    for(int i = start - step; i > end; i -= step) {
                        loopParts.add(store(tf.indexDesc, constant(i), Tree.NoComment));
                        loopParts.add(treeScope(body.copy(), Tree.NoComment));
                    }

                    return treeScope(sequential(loopParts, "Unrolled loop"), Tree.NoComment);
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
