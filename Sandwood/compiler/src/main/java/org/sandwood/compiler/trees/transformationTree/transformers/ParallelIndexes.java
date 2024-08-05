/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.transformers;

import static org.sandwood.compiler.trees.transformationTree.TransTree.ifElse;
import static org.sandwood.compiler.trees.transformationTree.TransTree.initializeVariable;
import static org.sandwood.compiler.trees.transformationTree.TransTree.load;
import static org.sandwood.compiler.trees.transformationTree.TransTree.sequential;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableName;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.transformationTree.TransFor;
import org.sandwood.compiler.trees.transformationTree.TransIfElse;
import org.sandwood.compiler.trees.transformationTree.TransInitialize;
import org.sandwood.compiler.trees.transformationTree.TransInitializeUnset;
import org.sandwood.compiler.trees.transformationTree.TransLoad;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;

public class ParallelIndexes extends Transformer {

    /**
     * A class that contains the parallel for loop that creates an index, and a flag to mark if the index is used in
     * another parallel for loop nested in this one.
     */
    private class IndexDesc {
        boolean usedInParallel = false;
        final TransFor lastParallel = parallelFors.peek();
    }

    /** Stack of the parallel for loops that preceed this location in the tree. */
    private final Stack<TransFor> parallelFors = new Stack<>();
    /**
     * Stack of mappings of variable names to the descriptions of indexes. Each map is a scope, so a new map is added
     * each time we enter a location with a new scope.
     */
    private final Stack<Map<VariableName, IndexDesc>> scopedVars = new Stack<>();
    /**
     * Map of substitution names used in loads, to the Variable descriptions that need to be used instead.
     */
    private final Map<VariableName, VariableDescription<IntVariable>> substitutions = new HashMap<>();

    /**
     * Constructor to initialize the state.
     */
    public ParallelIndexes() {
        parallelFors.push(null);
        scopedVars.push(new HashMap<>());
    }

    @Override
    protected TransTreeVoid transformVoid(TransTreeVoid tree) {
        switch(tree.type) {
            case FOR: {
                TransFor t = (TransFor) tree;
                // Get the index name.
                VariableDescription<IntVariable> indexDesc = t.indexDesc;

                // Transform the start
                TransTreeReturn<IntVariable> start = transformReturn(t.start);

                // If the loop is parallel push the for loop onto the stack.
                if(t.parallel)
                    parallelFors.push(t);
                // Push a new scope.
                scopedVars.push(new HashMap<>(scopedVars.peek()));
                // Add the index to the new scope.
                IndexDesc d = new IndexDesc();
                scopedVars.peek().put(indexDesc.name, d);

                // Transform the body.
                TransTreeVoid body = transformVoid(t.body);

                // Pop the for off the stack if it is on it, and remove the scope.
                if(t.parallel)
                    parallelFors.pop();
                scopedVars.pop();

                if(d.usedInParallel) {
                    // Create a new index name based on the existing index name, and initialize
                    // the index name to the value of the new index name. When the for loop is
                    // created it will use the new index name instead of the existing one. Append
                    // the body to this, and use this as the new body. This is done to make the
                    // value effectively final for any inner lambdas that use the index. This for
                    // loop
                    // will now have to create the index newIndex instead, though the range will be
                    // the same.
                    VariableDescription<IntVariable> newIndex = VariableNames.indexName(t.indexDesc);
                    List<TransTreeVoid> parts = new ArrayList<>();
                    parts.add(initializeVariable(indexDesc, load(newIndex), Tree.NoComment));
                    parts.add(body);
                    body = sequential(parts, Tree.NoComment);
                    // Add a substitution for the new index name when calculating the bound and
                    // step.
                    substitutions.put(indexDesc.name, newIndex);
                    TransTreeReturn<IntVariable> end = transformReturn(t.end);
                    TransTreeReturn<IntVariable> step = transformReturn(t.step);
                    substitutions.remove(indexDesc.name);

                    return TransTree.forStmt(body, start, end, step, newIndex, t.parallel, t.incrementing,
                            t.getComment());
                } else {
                    TransTreeReturn<IntVariable> end = transformReturn(t.end);
                    TransTreeReturn<IntVariable> step = transformReturn(t.step);

                    return TransTree.forStmt(body, start, end, step, indexDesc, t.parallel, t.incrementing,
                            t.getComment());
                }

            }
            case IF: {
                TransIfElse t = (TransIfElse) tree;

                TransTreeReturn<BooleanVariable> condition = transformReturn(t.condition);

                // Push a new scope for the if body before traversing the body.
                scopedVars.push(new HashMap<>(scopedVars.peek()));
                TransTreeVoid ifBody = transformVoid(t.ifBody);
                scopedVars.pop();

                if(t.elseBody != null) {
                    // If there is an else body push a scope for that before traversing the body.
                    scopedVars.push(new HashMap<>(scopedVars.peek()));
                    TransTreeVoid elseBody = transformVoid(t.elseBody);
                    scopedVars.pop();

                    return ifElse(condition, ifBody, t.getComment(), elseBody, t.getElseComment(), t.tags());
                }

                return ifElse(condition, ifBody, t.getComment(), t.tags());
            }
            case INITIALIZE: {
                return initialize((TransInitialize<?>) tree);
            }
            case INITIALIZE_UNSET: {
                TransInitializeUnset<?> t = (TransInitializeUnset<?>) tree;
                // The name is now used for the uninitialized value,
                // so remove it from the scope as it is now shadowed.
                scopedVars.peek().remove(t.varDesc.name);
                return tree.applyTransformation(this);
            }
            case SCOPE: {
                // If we enter a new scope, push a new scope map on to the
                // stack, populating it with the existing values.
                scopedVars.push(new HashMap<>(scopedVars.peek()));
                TransTreeVoid t = tree.applyTransformation(this);
                scopedVars.pop();
                return t;
            }
            default:
                return tree.applyTransformation(this);
        }
    }

    private <X extends Variable<X>> TransTreeVoid initialize(TransInitialize<X> t) {
        // Transform the value
        TransTreeReturn<X> value = transformReturn(t.value);
        VariableDescription<X> varDesc = t.varDesc;
        // The name is now used for the initialized value, so remove it from the scope
        // as it is now shadowed.
        scopedVars.peek().remove(varDesc.name);
        return initializeVariable(t.visibility, varDesc, value, t.getComment());
    }

    @Override
    protected <X extends Variable<X>> TransTreeReturn<X> transformReturn(TransTreeReturn<X> tree) {
        switch(tree.type) {
            case LOAD: {
                TransLoad<X> t = (TransLoad<X>) tree;
                // If the load is one of the in scope indexes get the details
                IndexDesc d = scopedVars.peek().get(t.varDesc.name);
                // If the load appears inside another parallel loop mark it as used in a
                // parallel loop so later an effectively final version of this parameter
                // can be created to keep the lambda for this inner for loop happy.
                if(d != null && d.lastParallel != parallelFors.peek())
                    d.usedInParallel = true;

                // If there is a substitution for this name, use the substitution.
                VariableDescription<IntVariable> sub = substitutions.get(t.varDesc.name);
                if(sub != null)
                    return (TransTreeReturn<X>) load(sub);
                else
                    return tree.applyTransformation(this);
            }
            default:
                return tree.applyTransformation(this);
        }

    }
}
