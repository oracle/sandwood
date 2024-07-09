/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.transformers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.TreeID;
import org.sandwood.compiler.trees.transformationTree.TransArrayGet;
import org.sandwood.compiler.trees.transformationTree.TransArrayPut;
import org.sandwood.compiler.trees.transformationTree.TransFor;
import org.sandwood.compiler.trees.transformationTree.TransLoad;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTree.TransTreeType;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.ScopedVarSet;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VarDef;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VariableTracking;

public class LoopRearrangement extends Transformer {

    private final Stack<Stack<TransFor>> swapableLoops;
    private final Stack<Stack<TransTreeReturn<IntVariable>>> indexTrees = new Stack<>();
    private final Stack<VariableDescription<?>> arrayNames = new Stack<>();
    private boolean collectingIndexTrees = false;
    private final Map<TransFor, Set<TransFor>> dependentOn = new HashMap<>();
    private final Map<TransFor, TransFor> swapRequest = new HashMap<>();
    private final VariableTracking varTracking;

    public LoopRearrangement(VariableTracking varTracking) {
        this.varTracking = varTracking;
        swapableLoops = new Stack<>();
        swapableLoops.push(new Stack<>());
    }

    @Override
    protected TransTreeVoid transformVoid(TransTreeVoid tree) {
        switch(tree.type) {
            case FOR: {
                visitedNodes.add(tree);
                TransFor tf = (TransFor) tree;

                // Calculate if this loop is dependent on an outer loop.
                ScopedVarSet startVars = varTracking.readVars(tf.start);
                ScopedVarSet endVars = varTracking.readVars(tf.end);
                ScopedVarSet stepVars = varTracking.readVars(tf.step);

                for(TransFor outer:swapableLoops.peek()) {
                    VariableDescription<IntVariable> indexDesc = outer.indexDesc;
                    if(startVars.containsVar(indexDesc) || endVars.containsVar(indexDesc)
                            || stepVars.containsVar(indexDesc)) {
                        Set<TransFor> dependency = dependentOn.computeIfAbsent(tf, k -> new HashSet<>());
                        dependency.add(outer);
                    }
                }

                // Transform the body
                swapableLoops.peek().push(tf);
                TransTreeVoid transBody;
                if(tf.body.type == TransTreeType.FOR) {
                    transBody = transform(tf.body);
                } else {
                    swapableLoops.push(new Stack<>());
                    transBody = transform(tf.body);
                    swapableLoops.pop();
                }
                swapableLoops.peek().pop();

                // Calculate the loop to construct.
                TransFor toConstruct = swapRequest.getOrDefault(tf, tf);
                
                // Transform the loop parameters
                TransTreeReturn<IntVariable> transStart = transformReturn(toConstruct.start);
                TransTreeReturn<IntVariable> transEnd = transformReturn(toConstruct.end);
                TransTreeReturn<IntVariable> transStep = transformReturn(toConstruct.step);

                return TransTree.forStmt(transBody, transStart, transEnd, transStep, toConstruct.indexDesc,
                        toConstruct.parallel, toConstruct.incrementing, toConstruct.getComment());
            }
            case ARRAY_PUT: {
                visitedNodes.add(tree);
                TransArrayPut<?> pt = (TransArrayPut<?>) tree;
                return processPutTask(pt);
            }
            default:
                return tree.applyTransformation(this);
        }
    }

    private <X extends Variable<X>> TransTreeVoid processPutTask(TransArrayPut<X> pt) {
        TransTreeReturn<X> transValue = transformReturn(pt.value);
        TransTreeReturn<IntVariable> transIndex = transformReturn(pt.index);

        collectingIndexTrees = true;
        indexTrees.push(new Stack<>());
        indexTrees.peek().add(pt.index);

        TransTreeReturn<ArrayVariable<X>> transArray = transformReturn(pt.array);

        collectingIndexTrees = false;

        processPutIndexes(arrayNames.pop(), indexTrees.pop());

        return TransTree.arrayPut(transArray, transIndex, transValue, pt.getComment());
    }

    @Override
    protected <X extends Variable<X>> TransTreeReturn<X> transformReturn(TransTreeReturn<X> tree) {
        switch(tree.type) {
            /*
             * case ADD: break; case ALLOCATE_ARRAY: break; case AND: break;
             */
            case ARRAY_GET: {
                this.visitedNodes.add(tree);
                TransArrayGet<X> gt = (TransArrayGet<X>) tree;
                if(!collectingIndexTrees) {
                    TransTreeReturn<IntVariable> transIndex = transformReturn(gt.index);

                    collectingIndexTrees = true;
                    indexTrees.push(new Stack<>());
                    indexTrees.peek().add(gt.index);

                    TransTreeReturn<ArrayVariable<X>> transArray = transformReturn(gt.array);
                    collectingIndexTrees = false;

                    processGetIndexes(arrayNames.pop(), indexTrees.pop());

                    return TransTree.arrayGet(transArray, transIndex);

                } else {
                    collectingIndexTrees = false;
                    TransTreeReturn<IntVariable> transIndex = transformReturn(gt.index);
                    collectingIndexTrees = true;

                    TransTreeReturn<ArrayVariable<X>> transArray = transformReturn(gt.array);

                    indexTrees.peek().add(gt.index);

                    return TransTree.arrayGet(transArray, transIndex);
                }
            }
            /*
             * case CAST_DOUBLE: break; case CAST_INT: break; case CONDITIONAL_ASSIGNMENT: break; case CONST_BOOLEAN:
             * break; case CONST_DOUBLE: break; case CONST_INT: break; case DIVIDE: break; case EQUALITY: break; case
             * EXP: break; case FOR: break; case FORK_JOIN_FOR: break; case GET_FIELD: break; case IF: break; case
             * INITIALIZE: break; case INITIALIZE_UNSET: break; case LESS_THAN: break; case LESS_THAN_EQUAL: break;
             */
            case LOAD: {
                this.visitedNodes.add(tree);
                TransLoad<X> l = (TransLoad<X>) tree;
                if(collectingIndexTrees)
                    arrayNames.push(l.varDesc);
                return TransTree.load(l.varDesc);
            }
            /*
             * case LOCAL_FUNCTION_CALL_RETURN: break; case LOG: break; case MAX: break; case MIN: break; case MULTIPLY:
             * break; case NAMED_FUNCTION_CALL: break; case NAMED_FUNCTION_CALL_RETURN: break; case NEGATE: break; case
             * NEGATE_BOOLEAN: break; case NOP: break; case OR: break; case PAR_FOR_LAMBDA: break; case REMAINDER:
             * break; case RV_FUNCTION_CALL: break; case RV_FUNCTION_CALL_RETURN: break; case SCOPE: break; case
             * SEQUENTIAL: break; case STORE: break; case SUBTRACT: break;
             */
            default:
                return tree.applyTransformation(this);
        }
    }

    private void processGetIndexes(VariableDescription<?> array, Stack<TransTreeReturn<IntVariable>> indexes) {
        processIndexes(indexes);
    }

    private void processIndexes(Stack<TransTreeReturn<IntVariable>> indexes) {
        int numIndexes = indexes.size();
        if(numIndexes < 2)
            return;
        List<Set<TransFor>> usedFors = new ArrayList<>(numIndexes);
        // Get the variables read by each index. This step reverses the list putting them in the order they would be
        // written in the code.
        while(!indexes.isEmpty())
            // These indexes will need to be saved for the restrictions that will need to be applied later.
            usedFors.add(getUsedFors(indexes.pop()));
        for(Stack<TransFor> sl:swapableLoops) {
            int numSwapable = sl.size();
            if(numSwapable > 1) {
                List<Set<TransFor>> forTracking = new ArrayList<>(numIndexes);
                for(int i = 0; i<numIndexes; i++)
                    forTracking.add(new HashSet<>());


                for(int i=0; i<numSwapable; i++) {
                    TransFor t = sl.get(i);
                    int min = numIndexes;
                    int max = 0;
                    for(int j=0; j<numIndexes; j++) {
                        if(usedFors.get(j).contains(t)) {
                            forTracking.get(j).add(t);
                            max = j;
                            //Only update min the first time.
                            if(min == numIndexes)
                                min = j;
                        }
                    }

                    //Add all the for loops that could be swapped with this one.
                    for(int j = min + 1; j<numIndexes; j++)
                        addPossibleSwap(t, forTracking.get(j), sl);

                    //Add restrictions for all the loops that could not be swapped with this one
                    for(int j = max - 1; j>=0; j--)
                        addRestriction(t, forTracking.get(j));
                }
            }
        }
    }

    private void addRestriction(TransFor t, Set<TransFor> dependencies) {
        // TODO Auto-generated method stub

    }

    private void addPossibleSwap(TransFor t, Set<TransFor> swaps, Stack<TransFor> sl) {
        for(TransFor t2:sl) {
            if(swaps.contains(t2)) {
                if(!swapRequest.containsKey(t2)) {
                    swapRequest.put(t2, t);
                    swapRequest.put(t, t2);
                }
                break;
            }
        }
    }

    /**
     * Method to get all the for tasks used directly to construct this index. TODO add tracking that marks this as an
     * unsafe index if anything that is not from a for loop is used to construct this index. Then weaken this with time.
     */
    private Set<TransFor> getUsedFors(TransTreeReturn<IntVariable> index) {
        ScopedVarSet read = varTracking.readVars(index);
        Set<TransFor> usedFors = new HashSet<>();
        for(VarDef d:read.getAllVarDefs()) {
            if(d.declarationId != TreeID.global) {
                TransTreeVoid t = (TransTreeVoid) varTracking.treeLookup(d.declarationId);
                if(t.type == TransTreeType.FOR)
                    usedFors.add((TransFor) t);
            }
        }
        return usedFors;
    }

    private void processPutIndexes(VariableDescription<?> array, Stack<TransTreeReturn<IntVariable>> indexes) {
        processIndexes(indexes);
    }
}
