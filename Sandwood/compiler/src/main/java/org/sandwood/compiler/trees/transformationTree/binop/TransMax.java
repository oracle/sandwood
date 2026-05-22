/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.binop;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.outputTree.OutputTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;
import org.sandwood.compiler.trees.transformationTree.util.WrappedTransReturn;

public class TransMax<A extends NumberVariable<A>> extends TransBinOp<A, A, A> {

    private TransMax(TransTreeReturn<A> left, TransTreeReturn<A> right) {
        super(left, right, true, TransTreeType.MAX);
    }

    @Override
    public OutputTreeReturn<A> toOutputTreeReturnInternal() {
        return OutputTree.max(left.toOutputTreeReturnInternal(), right.toOutputTreeReturnInternal());
    }

    public static <A extends NumberVariable<A>> TransTreeReturn<A> max(TransTreeReturn<A> left,
            TransTreeReturn<A> right) {
        if(left.equivalent(right))
            return left;
        else {
            // Gather and transform all the terms.
            Stack<TransTreeReturn<A>> toProcess = new Stack<>();
            List<WrappedTransReturn<A>> seen = new ArrayList<>();
            int initialSize = 0;

            toProcess.push(right);
            toProcess.push(left);

            while(!toProcess.isEmpty()) {
                TransTreeReturn<A> t = toProcess.pop();
                switch(t.type) {
                    case MAX:
                        TransMax<A> m = (TransMax<A>) t;
                        toProcess.push(m.right);
                        toProcess.push(m.left);
                        initialSize++;
                        break;
                    default:
                        WrappedTransReturn<A> w = new WrappedTransReturn<>(t);
                        if(!seen.contains(w)) {
                            seen.add(w);
                        }
                        break;
                }
            }
            int seenSize = seen.size();
            if(seenSize == initialSize + 2)
                return new TransMax<>(left, right);
            else if(seenSize == 1)
                return seen.get(0).tree;
            else {
                TransTreeReturn<A> t = new TransMax<>(seen.get(0).tree, seen.get(1).tree);
                for(int i = 2; i < seenSize; i++)
                    t = new TransMax<>(t, seen.get(i).tree);
                return t;
            }
        }
    }

    @Override
    public TransTreeReturn<A> applyTransformationInternal(Transformer t) {
        return max(t.transform(left), t.transform(right));
    }

    @Override
    public Type<A> getOutputType() {
        return left.getOutputType();
    }

    @Override
    public TransTreeReturn<A> maxValueInternal(Bounds bounds) {
        return max(left.maxValue(bounds), right.maxValue(bounds));
    }

    @Override
    public TransTreeReturn<A> minValueInternal(Bounds bounds) {
        return max(left.minValue(bounds), right.minValue(bounds));
    }

    @Override
    protected String getOp() {
        return "Max";
    }
}
