/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.transformers;

import static org.sandwood.compiler.trees.transformationTree.TransTree.negateBoolean;

import java.util.ArrayList;
import java.util.List;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.transformationTree.TransConstBoolean;
import org.sandwood.compiler.trees.transformationTree.TransNegateBoolean;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTree.TransTreeType;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;
import org.sandwood.compiler.trees.transformationTree.binop.TransAnd;
import org.sandwood.compiler.trees.transformationTree.binop.TransOr;

public class MoveNegationTransformer extends Transformer {

    @Override
    public TransTreeVoid transformVoid(TransTreeVoid tree) {
        switch(tree.type) {
            case ARRAY_PUT:
            case FOR:
            case NAMED_FUNCTION_CALL:
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

    @SuppressWarnings("unchecked")
    @Override
    public <X extends Variable<X>> TransTreeReturn<X> transformReturn(TransTreeReturn<X> tree) {
        switch(tree.type) {
            case NEGATE_BOOLEAN:
                visitedNodes.add(tree);
                return (TransTreeReturn<X>) moveNegation((TransNegateBoolean) tree);

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
            case EXP:
            case LOCAL_FUNCTION_CALL_RETURN:
            case NAMED_FUNCTION_CALL_RETURN:
            case RV_FUNCTION_CALL_RETURN:
            case GET_FIELD:
            case LESS_THAN:
            case LESS_THAN_EQUAL:
            case LOAD:
            case LOG:
            case MAX:
            case MIN:
            case NEGATE:
            case OR:
                return tree.applyTransformation(this);

            default:
                throw new CompilerException("Unknown tree type " + tree.type + " encountered");
        }
    }

    private TransTreeReturn<BooleanVariable> moveNegation(TransNegateBoolean tree) {
        TransTreeReturn<BooleanVariable> newTree = tree.input.copy(visitedNodes);
        if(newTree.type == TransTreeType.AND) {
            List<TransTreeReturn<BooleanVariable>> newConstraints = new ArrayList<>();
            for(TransTreeReturn<BooleanVariable> c:((TransAnd) newTree).getConstraints()) {
                newConstraints.add(transform(negateBoolean(c)));
            }
            return TransTree.or(newConstraints);
        }

        if(newTree.type == TransTreeType.OR) {
            List<TransTreeReturn<BooleanVariable>> newConstraints = new ArrayList<>();
            for(TransTreeReturn<BooleanVariable> c:((TransOr) newTree).getConstraints()) {
                newConstraints.add(transform(negateBoolean(c)));
            }
            return TransTree.and(newConstraints);
        }

        if(newTree.type == TransTreeType.CONST_BOOLEAN)
            return TransTree.constant(!((TransConstBoolean) newTree).value);

        return negateBoolean(newTree);
    }
}
