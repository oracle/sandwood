/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.transformers;

import static org.sandwood.compiler.trees.transformationTree.TransTree.addII;
import static org.sandwood.compiler.trees.transformationTree.TransTree.constant;
import static org.sandwood.compiler.trees.transformationTree.TransTree.initializeVariable;
import static org.sandwood.compiler.trees.transformationTree.TransTree.load;
import static org.sandwood.compiler.trees.transformationTree.TransTree.remainderII;
import static org.sandwood.compiler.trees.transformationTree.TransTree.store;
import static org.sandwood.compiler.trees.transformationTree.TransTree.subtractII;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.TreeID;
import org.sandwood.compiler.trees.transformationTree.TransFor;
import org.sandwood.compiler.trees.transformationTree.TransLoad;
import org.sandwood.compiler.trees.transformationTree.TransParFor;
import org.sandwood.compiler.trees.transformationTree.TransParForLambda;
import org.sandwood.compiler.trees.transformationTree.TransStore;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.ScopedVarSet;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VarDef;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VariableTracking;

public class ParFor extends Transformer {
    private int depth = 0;
    private int id = 1;

    private VariableTracking varTracking;
    private Map<VariableDescription<?>, Stack<VariableDescription<?>>> substitutions = new HashMap<>();

    public ParFor(VariableTracking varTracking) {
        this.varTracking = varTracking;
    }

    private <V extends Variable<V>> void addSubstitution(VariableDescription<V> v, List<TransTreeVoid> stmts) {
        // Create the alternative name
        VariableDescription<V> sub = VariableNames.lambdaSubstitute(v, id);

        // Add the substitution statement
        VariableDescription<V> currentDesc = getSubstitution(v);
        stmts.add(initializeVariable(sub, load(getSubstitution(currentDesc)),
                "Alternative value for " + currentDesc.name + " to make it effectively final."));

        // Store the substitution for when the tree is transformed.
        Stack<VariableDescription<?>> subs = substitutions.get(v);
        if(subs == null) {
            subs = new Stack<>();
            substitutions.put(v, subs);
        }
        subs.push(sub);
    }

    private <V extends Variable<V>> void removeSubstitutions(VariableDescription<V> desc, boolean writtenTo,
            List<TransTreeVoid> stmts) {
        Stack<VariableDescription<?>> subs = substitutions.get(desc);
        @SuppressWarnings("unchecked")
        VariableDescription<V> sub = (VariableDescription<V>) subs.pop();
        if(subs.isEmpty())
            substitutions.remove(desc);

        if(writtenTo) {
            VariableDescription<V> originalDesc = getSubstitution(desc);
            stmts.add(store(originalDesc, load(sub), "Writing back and changes to the value " + originalDesc.name));
        }

    }

    @SuppressWarnings("unchecked")
    private <V extends Variable<V>> VariableDescription<V> getSubstitution(VariableDescription<V> name) {
        Stack<VariableDescription<?>> subs = substitutions.get(name);
        if(subs == null)
            return name;
        else
            return (VariableDescription<V>) subs.peek();
    }

    @Override
    protected TransTreeVoid transformVoid(TransTreeVoid tree) {
        switch(tree.type) {
            case FOR: {
                TransFor t = (TransFor) tree;
                if(t.parallel) {
                    // Setup alternative variables to ensure that all variables in the
                    // for loop are effectively final.

                    // First step determine the reads and writes that need substituting.
                    ScopedVarSet readVars = varTracking.readVars(t.body);
                    Set<VariableDescription<?>> writtenVars = varTracking.written(t.body);

                    Set<VariableDescription<?>> toSubstitute = new HashSet<>();
                    // Set of variables that are modified and need to be written back. TODO
                    // Determine if these are ever read again, and only write the value back
                    // if they are. This could be done by tagging the initialised variables
                    // so they are not removed by a pass of the remove unused variables
                    // transformer, and then running the transformer.
                    Set<VariableDescription<?>> toWriteBack = new HashSet<>();
                    for(VariableDescription<?> v:readVars.getVars()) {
                        if(!v.equals(t.indexDesc)) {
                            VarDef vd = readVars.getVarDef(v);
                            TreeID declarationId = vd.declarationId;
                            if(declarationId != TreeID.global) {
                                if(!varTracking.effectivelyFinal(v, declarationId)) {
                                    toSubstitute.add(v);
                                    if(writtenVars.contains(v))
                                        toWriteBack.add(v);
                                }
                            }
                        }
                    }

                    // Second step setup the
                    List<TransTreeVoid> stmts = new ArrayList<>();
                    if(!toSubstitute.isEmpty()) {
                        for(VariableDescription<?> v:toSubstitute)
                            addSubstitution(v, stmts);
                        id++;
                    }

                    // Process the for loop
                    depth++;
                    TransTreeVoid body = transformVoid(t.body);
                    depth--;

                    TransTreeReturn<IntVariable> start;
                    TransTreeReturn<IntVariable> end;
                    if(t.incrementing) {
                        start = transformReturn(t.start);
                        end = addII(transformReturn(t.end), constant(1));
                        end = end.collapseConstants();
                    } else {
                        start = addII(transformReturn(t.end), remainderII(
                                subtractII(transformReturn(t.start), transformReturn(t.end)), transformReturn(t.step)));
                        start = start.collapseConstants();

                        end = addII(transformReturn(t.start), constant(1));
                        end = end.collapseConstants();

                    }
                    TransTreeReturn<IntVariable> step = transformReturn(t.step);

                    // called again to ensure the tree remains a tree.
                    TransTreeReturn<IntVariable> innerStep = step.copy(visitedNodes);
                    VariableDescription<IntVariable> innerStartName = VariableNames.parForStart(t.indexDesc);
                    TransTreeReturn<IntVariable> innerStart = load(innerStartName);
                    VariableDescription<IntVariable> innerEndName = VariableNames.parForEnd(t.indexDesc);
                    TransTreeReturn<IntVariable> innerEnd = subtractII(load(innerEndName), constant(1));

                    TransTreeVoid innerFor = TransTree.forStmt(body, innerStart, innerEnd, innerStep, t.indexDesc,
                            false, true,
                            "Inner loop for running batches of iterations, each batch has its own random number generator.");

                    TransTreeVoid l = TransParForLambda.getParForLambda(depth + 1, innerStartName, innerEndName,
                            VariableNames.threadIdName(t.indexDesc.name), innerFor);

                    TransTreeVoid f = TransParFor.getParFor(depth, start, end, step, l,
                            " Outer loop for dispatching multiple batches of iterations to execute in parallel");

                    stmts.add(f);

                    // Remove substitutions and set any required write backs.
                    for(VariableDescription<?> v:toSubstitute) {
                        removeSubstitutions(v, toWriteBack.contains(v), stmts);
                    }

                    // Return the result.
                    return TransTree.sequential(stmts, Tree.NoComment);
                } else
                    return tree.applyTransformation(this);
            }

            case STORE: {
                return transformStore((TransStore<?>) tree);
            }

            case RV_FUNCTION_CALL:
            case NAMED_FUNCTION_CALL:
            case ARRAY_PUT:
            case IF:
            case INITIALIZE:
            case INITIALIZE_UNSET:
            case NOP:
            case SCOPE:
            case SEQUENTIAL:
                return tree.applyTransformation(this);
            default:
                throw new CompilerException("Unhandled type " + tree.type);
        }
    }

    private <V extends Variable<V>> TransTreeVoid transformStore(TransStore<V> store) {
        TransTreeReturn<V> value = transform(store.value);
        VariableDescription<V> name = getSubstitution(store.varDesc);
        return store(name, value, store.getComment());
    }

    @Override
    protected <X extends Variable<X>> TransTreeReturn<X> transformReturn(TransTreeReturn<X> tree) {
        switch(tree.type) {
            case LOAD: {
                TransLoad<X> l = (TransLoad<X>) tree;
                if(l.varDesc.equals(VariableNames.rngName(0))) {
                    return (TransTreeReturn<X>) load(VariableNames.rngName(depth));
                } else
                    return load(getSubstitution(l.varDesc));
            }

            case RV_FUNCTION_CALL_RETURN:
            case NAMED_FUNCTION_CALL_RETURN:
            case LOCAL_FUNCTION_CALL_RETURN:
            case ADD:
            case ALLOCATE_ARRAY:
            case AND:
            case ARRAY_GET:
            case CAST_DOUBLE:
            case CAST_INT:
            case CONST_BOOLEAN:
            case CONST_DOUBLE:
            case CONST_INT:
            case CONDITIONAL_ASSIGNMENT:
            case DIVIDE:
            case EQUALITY:
            case EXP:
            case GET_FIELD:
            case LESS_THAN:
            case LESS_THAN_EQUAL:
            case LOG:
            case MAX:
            case MIN:
            case REMAINDER:
            case MULTIPLY:
            case NEGATE:
            case NEGATE_BOOLEAN:
            case OR:
            case SUBTRACT:
                // This could in theory be changed to just tree, but for now we will copy the
                // tree at the same time.
                return tree.applyTransformation(this);
            default:
                throw new CompilerException("Unhandled type " + tree.type);

        }
    }
}
