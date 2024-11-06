/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.transformers;

import static org.sandwood.compiler.trees.transformationTree.TransTree.forStmt;
import static org.sandwood.compiler.trees.transformationTree.TransTree.ifElse;
import static org.sandwood.compiler.trees.transformationTree.TransTree.treeScope;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.transformationTree.TransFor;
import org.sandwood.compiler.trees.transformationTree.TransIfElse;
import org.sandwood.compiler.trees.transformationTree.TransSequential;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTree.TransTreeType;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeScope;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;

public class RemoveUnusedScopesTransformer extends Transformer {

    boolean first = true;

    public RemoveUnusedScopesTransformer() {}

    @Override
    public TransTreeVoid transformVoid(TransTreeVoid tree) {
        boolean f = first;
        first = false;
        switch(tree.type) {
            case FOR:
                visitedNodes.add(tree);
                return removeUnusedScopes((TransFor) tree);

            case IF:
                visitedNodes.add(tree);
                return removeUnusedScopes((TransIfElse) tree);

            case SCOPE:
                visitedNodes.add(tree);
                return removeUnusedScopes(f, (TransTreeScope) tree);

            case SEQUENTIAL:
                visitedNodes.add(tree);
                return removeUnusedScopes((TransSequential) tree);

            case INITIALIZE:
            case INITIALIZE_UNSET:
            case ARRAY_PUT:
            case LOCAL_FUNCTION_CALL:
            case RV_FUNCTION_CALL:
            case NOP:
            case STORE: {
                return tree.applyTransformation(this);
            }

            default:
                throw new CompilerException("Unknown tree type " + tree.type + " encountered");
        }
    }

    private TransTreeVoid removeUnusedScopes(TransSequential tree) {
        List<TransTreeVoid> trees = tree.getTrees();
        int size = trees.size();
        Set<String> declared = new HashSet<>();
        TransTreeVoid[] newTrees = new TransTreeVoid[size];

        for(int i = size - 1; i >= 0; i--) {
            TransTreeVoid t = transform(trees.get(i));
            switch(t.type) {
                case SCOPE: {
                    TransTreeScope ts = (TransTreeScope) t;
                    boolean clash = false;
                    // find if the scoped value contains a declaration that clashes with a later
                    // declaration.
                    Set<String> treeDeclared = ts.tree.declaredLocalVariables();
                    for(String s:treeDeclared)
                        clash = clash || declared.contains(s);
                    // If there is no clash we can remove the scope.
                    if(!clash) {
                        ts.tree.prefixComment(ts.getComment());
                        newTrees[i] = ts.tree;
                    } else {
                        newTrees[i] = t;
                    }

                    declared.addAll(t.declaredAllVariables());
                    break;
                }
                default: {
                    // Add the tree to trees for the sequential and
                    // update the declarations
                    declared.addAll(t.declaredAllVariables());
                    newTrees[i] = t;
                    break;
                }

            }
        }
        return TransTree.sequential(newTrees, tree.getComment());
    }

    @Override
    public <X extends Variable<X>> TransTreeReturn<X> transformReturn(TransTreeReturn<X> tree) {
        switch(tree.type) {
            case ALLOCATE_ARRAY:
            case AND:
            case ARRAY_GET:
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
            case SUBTRACT:
            case MULTIPLY:
            case REMAINDER:
            case DIVIDE:
            case ADD:
                return tree.applyTransformation(this);

            default:
                throw new CompilerException("Unknown tree type " + tree.type + " encountered");
        }
    }

    private TransTreeVoid removeUnusedScopes(TransIfElse tree) {
        TransTreeReturn<BooleanVariable> transCondition = transform(tree.condition);
        TransTreeVoid transIfBody = stripTree(transform(tree.ifBody));
        TransTreeVoid transElseBody = stripTree(transform(tree.elseBody));
        return ifElse(transCondition, transIfBody, tree.getComment(), transElseBody, tree.getElseComment(),
                tree.tags());
    }

    private TransTreeVoid removeUnusedScopes(TransFor tree) {
        TransTreeVoid transBody = stripTree(transform(tree.body));

        return forStmt(transBody, transform(tree.start), transform(tree.end), transform(tree.step), tree.indexDesc,
                tree.parallel, tree.incrementing, tree.getComment());
    }

    private TransTreeVoid removeUnusedScopes(boolean first, TransTreeScope tree) {
        TransTreeVoid transTree = stripTree(transform(tree.tree));
        if(first) {
            transTree.prefixComment(tree.getComment());
            return transTree;
        } else
            return treeScope(transTree, tree.getComment());
    }

    public TransTreeVoid stripTree(TransTreeVoid t) {
        // Because the tree will have been transformed before this is called
        // there will be at most 1 scope to remove.
        if(t.type == TransTreeType.SCOPE) {
            String comment = t.getComment();
            t = ((TransTreeScope) t).tree;
            t.prefixComment(comment);
        }
        return t;
    }
}
