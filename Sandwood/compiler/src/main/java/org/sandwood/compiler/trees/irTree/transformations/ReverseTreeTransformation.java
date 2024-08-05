/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree.transformations;

import static org.sandwood.compiler.trees.irTree.IRTree.addII;
import static org.sandwood.compiler.trees.irTree.IRTree.constant;
import static org.sandwood.compiler.trees.irTree.IRTree.remainderII;
import static org.sandwood.compiler.trees.irTree.IRTree.subtractII;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.irTree.IRFor;
import org.sandwood.compiler.trees.irTree.IRProxyTreeSeq;
import org.sandwood.compiler.trees.irTree.IRSequential;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class ReverseTreeTransformation implements TreeTransformation {

    @Override
    public IRTreeVoid transform(IRTreeVoid tree) {
        switch(tree.type) {
            case FOR: {
                IRFor ft = (IRFor) tree;
                IRTreeReturn<IntVariable> start = transformReturn(ft.start);
                IRTreeReturn<IntVariable> end = transformReturn(ft.end);
                IRTreeReturn<IntVariable> step = transformReturn(ft.step);
                end = updateEnd(start, end, step, ft.incrementing);
                start = updateStart(start, ft.incrementing);
                IRTreeVoid body = transform(ft.body);
                if(ft.parallel)
                    return IRTree.parallelForStmt(body, end, start, step, ft.indexDesc, !ft.incrementing,
                            ft.getComment());
                else
                    return IRTree.forStmt(body, end, start, step, ft.indexDesc, !ft.incrementing, ft.getComment());
            }
            case INITIALIZE:
            case INITIALIZE_UNSET:
                throw new CompilerException("It is not possible to reverse trees that include internal storage.");
            case SEQUENTIAL: {
                IRSequential st = (IRSequential) tree;
                IRTreeVoid[] trees = st.trees;
                return IRTree.sequential(reverseTrees(trees), st.getComment());
            }
            case PROXY_SEQ: {
                IRProxyTreeSeq st = (IRProxyTreeSeq) tree;
                IRTreeVoid[] trees = st.getChildren();
                // We cannot create a proxy sequential with trees, and given it is part
                // of the scope tracking it is not clear that we should. TODO make getting
                // a tree from the scope tracker transform the tree removing proxies as it goes.
                return IRTree.sequential(reverseTrees(trees), st.getComment());
            }
            default:
                return tree.applyTransformation(this);
        }
    }

    /**
     * Transform the start to make sure that the loop will end on the value it would have started on normally.
     * 
     * @param start
     * @param incrementing
     * @return
     */

    private IRTreeReturn<IntVariable> updateStart(IRTreeReturn<IntVariable> start, boolean incrementing) {
        if(incrementing)// will be decrementing after the transformation
            return subtractII(start, constant(1));
        else
            return addII(start, constant(1));
    }

    private IRTreeVoid[] reverseTrees(IRTreeVoid[] trees) {
        int noTrees = trees.length;
        IRTreeVoid[] reversedTrees = new IRTreeVoid[noTrees];
        for(int i = 0; i < noTrees; i++)
            reversedTrees[noTrees - (i + 1)] = transform(trees[i]);
        return reversedTrees;
    }

    /**
     * Transform the end to make sure that the loop will start on the value it would have finished on normally.
     * 
     * @param start
     * @param end
     * @param step
     * @param incrementing
     * @return
     */
    private IRTreeReturn<IntVariable> updateEnd(IRTreeReturn<IntVariable> start, IRTreeReturn<IntVariable> end,
            IRTreeReturn<IntVariable> step, boolean incrementing) {
        if(incrementing)
            return subtractII(end,
                    addII(remainderII(subtractII(subtractII(end, constant(1)), start), step), constant(1)));
        else
            return addII(end, addII(remainderII(subtractII(start, addII(end, constant(1))), step), constant(1)));
    }

    @Override
    public <X extends Variable<X>> IRTreeReturn<X> transformReturn(IRTreeReturn<X> tree) {
        return tree.applyTransformation(this);
    }

}
