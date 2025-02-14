/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.transformers;

import static org.sandwood.compiler.trees.transformationTree.TransTree.addII;
import static org.sandwood.compiler.trees.transformationTree.TransTree.constant;
import static org.sandwood.compiler.trees.transformationTree.TransTree.ifElse;

import java.util.Collections;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.transformationTree.TransFor;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VariableTracking;

public class CollapseUnrequiredForLoopsTransformer extends Transformer {

    private final VariableTracking vars;

    public CollapseUnrequiredForLoopsTransformer(VariableTracking vars) {
        this.vars = vars;
    }

    @Override
    public TransTreeVoid transformVoid(TransTreeVoid tree) {
        switch(tree.type) {
            case FOR:
                return collapseUnrequiredForLoops((TransFor) tree);
            case ARRAY_PUT:
            case LOCAL_FUNCTION_CALL:
            case RV_FUNCTION_CALL:
            case IF:
            case INITIALIZE:
            case INITIALIZE_UNSET:
            case NOP:
            case SCOPE:
            case SEQUENTIAL:
            case STORE: {
                return tree.applyTransformation(this);
            }

            default:
                throw new CompilerException("Unknown tree type " + tree.type + " encountered");
        }
    }

    @Override
    public <X extends Variable<X>> TransTreeReturn<X> transformReturn(TransTreeReturn<X> tree) {
        switch(tree.type) {
            case ALLOCATE_ARRAY:
            case AND:
            case ARRAY_GET:
            case SUBTRACT:
            case MULTIPLY:
            case REMAINDER:
            case DIVIDE:
            case ADD:
            case CAST_DOUBLE:
            case CAST_INT:
            case CONST_BOOLEAN:
            case CONST_DOUBLE:
            case CONST_INT:
            case CONDITIONAL_ASSIGNMENT:
            case EQUALITY:
            case LOCAL_FUNCTION_CALL_RETURN:
            case EXTERNAL_FUNCTION_CALL_RETURN:
            case RV_FUNCTION_CALL_RETURN:
            case GET_FIELD:
            case LESS_THAN:
            case LESS_THAN_EQUAL:
            case LOAD:
            case MAX:
            case MIN:
            case NEGATE:
            case NEGATE_BOOLEAN:
            case OR:
                return tree.applyTransformation(this);

            default:
                throw new CompilerException("Unknown tree type " + tree.type + " encountered");
        }
    }

    protected TransTreeVoid collapseUnrequiredForLoops(TransFor tf) {
        if(vars.read(tf.body, tf.indexDesc))
            return tf.applyTransformation(this);

        // If we are here, the index does not appear in the body, or in any constraints.
        // If there are no constraints remove the for loop.
        tf.addNodes(visitedNodes);
        TransTreeReturn<BooleanVariable> guard;
        if(tf.incrementing) {
            int endSize = tf.end.size();
            TransTreeReturn<IntVariable> altEnd = addII(tf.end, constant(1)).collapseConstants();
            int altEndSize = altEnd.size();
            if(altEndSize <= endSize)
                guard = TransTree.lessThan(tf.start, altEnd);
            else
                guard = TransTree.lessThanEqual(tf.start, tf.end);
        } else
            guard = TransTree.lessThanEqual(tf.end, tf.start);
        return ifElse(guard, transform(tf.body), tf.getComment(), Collections.emptySet());
    }
}
