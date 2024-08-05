/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.transformers;

import static org.sandwood.compiler.trees.transformationTree.TransTree.and;
import static org.sandwood.compiler.trees.transformationTree.TransTree.ifElse;
import static org.sandwood.compiler.trees.transformationTree.TransTree.sequential;
import static org.sandwood.compiler.trees.transformationTree.TransTree.treeScope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.transformationTree.TransIfElse;
import org.sandwood.compiler.trees.transformationTree.TransSequential;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTree.TransTreeType;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeScope;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;
import org.sandwood.compiler.trees.transformationTree.visitors.ConstraintDescription;

public class MoveConstraintsOutTransformer extends Transformer {

    final Stack<Set<ConstraintDescription>> setConstraints;
    final Map<TransTree<?>, Set<ConstraintDescription>> constraintMapping;

    public MoveConstraintsOutTransformer(Map<TransTree<?>, Set<ConstraintDescription>> constraintMapping) {
        setConstraints = new Stack<>();
        Set<ConstraintDescription> sc = new HashSet<>();
        setConstraints.push(sc);

        this.constraintMapping = constraintMapping;
    }

    @Override
    public TransTreeVoid transformVoid(TransTreeVoid tree) {
        switch(tree.type) {
            case IF:
                visitedNodes.add(tree);
                return moveConstraintsOut((TransIfElse) tree);

            case SCOPE:
                visitedNodes.add(tree);
                return moveConstraintsOut((TransTreeScope) tree);

            case SEQUENTIAL:
                visitedNodes.add(tree);
                return moveConstraintsOut((TransSequential) tree);
            case INITIALIZE:
            case INITIALIZE_UNSET:
            case NAMED_FUNCTION_CALL:
            case RV_FUNCTION_CALL:
            case NOP:
            case STORE:
            case ARRAY_PUT:
                visitedNodes.add(tree);
                return tree.applyTransformation(this);

            case FOR: {
                visitedNodes.add(tree);
                // TODO separate out the return trees and given them a separate path with check
                // to ensure they have no constraints.
                // Construct a set to hold the used constraints within this scope.
                Set<ConstraintDescription> sc = new HashSet<>(setConstraints.peek());
                setConstraints.push(sc);

                List<TransTreeReturn<BooleanVariable>> constraints = new ArrayList<>();
                // For each constraint that could be applied to this statement
                // A priority queue is used to make the ordering of the values produced
                // deterministic.
                PriorityQueue<ConstraintDescription> p = new PriorityQueue<>(constraintMapping.get(tree));
                while(!p.isEmpty()) {
                    ConstraintDescription d = p.poll();
                    // Test if it has been applied to an outer loop first. If it has not, apply it
                    // here and record this so that it does not get applied to a loop further in.
                    if(!sc.contains(d)) {
                        constraints.add(transform(d.constraint));
                        sc.add(d);
                    }
                }

                TransTreeVoid toReturn;
                if(constraints.size() == 0)
                    toReturn = tree.applyTransformation(this);
                else
                    toReturn = ifElse(and(constraints), transform(tree),
                            "Constraints moved from conditionals in inner loops/scopes/etc.", Collections.emptySet());

                // remove the constraint from the stack.
                setConstraints.pop();
                return toReturn;
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
            case NEGATE_BOOLEAN:
            case OR:
                return tree.applyTransformation(this);

            default:
                throw new CompilerException("Unknown tree type " + tree.type + " encountered");
        }
    }

    private TransTreeVoid moveConstraintsOut(TransIfElse t) {
        t.condition.addNodes(visitedNodes);

        // Construct a set to hold the used constraints within this scope.
        Set<ConstraintDescription> sc = new HashSet<>(setConstraints.peek());
        setConstraints.push(sc);

        List<TransTreeReturn<BooleanVariable>> constraints = new ArrayList<>();
        // For each constraint that could be applied to this statement
        // A priority queue is used to make the ordering of the values produced
        // deterministic.
        PriorityQueue<ConstraintDescription> p = new PriorityQueue<>(constraintMapping.get(t));
        while(!p.isEmpty()) {
            ConstraintDescription d = p.poll();
            // Test if it has been applied to an out loop first. If it has not, apply it
            // here and record this so that it does not get applied to a loop further in.
            if(!sc.contains(d)) {
                constraints.add(transform(d.constraint));
                sc.add(d);
            }
        }

        TransTreeVoid toReturn;
        if(constraints.size() == 0) {
            if(t.elseBody.type == TransTreeType.NOP)
                toReturn = scope(transform(t.ifBody));
            else
                toReturn = scope(t.applyTransformation(this));
        } else {
            if(t.elseBody.type == TransTreeType.NOP)
                toReturn = ifElse(and(constraints), transform(t.ifBody), t.getComment(), t.tags());
            else
                toReturn = ifElse(and(constraints), t.applyTransformation(this), t.getComment(), t.tags());
        }

        // remove the constraint from the stack.
        setConstraints.pop();
        return toReturn;
    }

    protected TransTreeVoid moveConstraintsOut(TransTreeScope tree) {

        // Construct a set to hold the used constraints within this scope.
        Set<ConstraintDescription> sc = new HashSet<>(setConstraints.peek());
        setConstraints.push(sc);

        List<TransTreeReturn<BooleanVariable>> constraints = new ArrayList<>();
        // For each constraint that could be applied to this statement
        // A priority queue is used to make the ordering of the values produced
        // deterministic.
        PriorityQueue<ConstraintDescription> p = new PriorityQueue<>(constraintMapping.get(tree));
        while(!p.isEmpty()) {
            ConstraintDescription d = p.poll();
            // Test if it has been applied to an outer loop first. If it has not, apply it
            // here, and record this so it does not get applied to a loop further in.
            if(!sc.contains(d)) {
                constraints.add(transform(d.constraint));
                sc.add(d);
            }
        }

        TransTreeVoid toReturn;
        if(constraints.size() == 0)
            toReturn = tree.applyTransformation(this);
        else
            toReturn = ifElse(and(constraints), transform(tree.tree),
                    "Constraints moved from conditionals in inner loops/scopes/etc.", Collections.emptySet());

        // remove the constraint from the stack.
        setConstraints.pop();
        return toReturn;
    }

    private TransTreeVoid moveConstraintsOut(TransSequential s) {
        Set<ConstraintDescription> sc = setConstraints.peek();
        Set<ConstraintDescription> possibleConstraints = new HashSet<>(constraintMapping.get(s));
        possibleConstraints.removeAll(sc);
        if(possibleConstraints.isEmpty())
            return mergeSequentialGuards(s);
        else {
            sc = new HashSet<>(sc);
            sc.addAll(possibleConstraints);
            setConstraints.push(sc);

            List<TransTreeReturn<BooleanVariable>> cs = new ArrayList<>();
            PriorityQueue<ConstraintDescription> p = new PriorityQueue<>(possibleConstraints);
            while(!p.isEmpty()) {
                ConstraintDescription d = p.poll();
                cs.add(transform(d.constraint));
            }

            TransTreeVoid newTree = ifElse(and(cs), mergeSequentialGuards(s),
                    "Constraints moved from conditionals in inner loops/scopes/etc.", Collections.emptySet());
            setConstraints.pop();
            return newTree;
        }
    }

    public TransTreeVoid mergeSequentialGuards(TransSequential s) {
        List<TransTreeVoid> trees = s.getTrees();
        // Construct a set to hold the used constraints within this scope.
        Set<ConstraintDescription> sc = setConstraints.peek();

        List<Set<ConstraintDescription>> constraints = new ArrayList<>(trees.size());
        for(TransTreeVoid tree:trees)
            constraints.add(constraintMapping.get(tree));

        int maxStart = 0;
        int maxEnd = 0;
        int maxLength = 0;

        for(ConstraintDescription c:constraints.get(0)) {
            int start = 0;
            int end = 1;
            if(!sc.contains(c) && c.stableGuard) {
                for(int i = 1; i < trees.size(); i++) {
                    if(constraints.get(i).contains(c))
                        end++;
                    else
                        break;
                }
            }

            int length = end - start;
            if(length > maxLength) {
                maxStart = start;
                maxEnd = end;
                maxLength = length;
            }
        }

        for(int j = 1; j < trees.size(); j++) {
            for(ConstraintDescription c:constraints.get(j)) {
                if(c.stableGuard) {
                    int start = j;
                    int end = j + 1;
                    Set<ConstraintDescription> previousConstraints = constraints.get(j - 1);
                    if(!sc.contains(c) && !previousConstraints.contains(c)) {
                        for(int i = j + 1; i < trees.size(); i++) {
                            if(constraints.get(i).contains(c))
                                end++;
                            else
                                break;
                        }
                    }

                    int length = end - start;
                    if(length > maxLength) {
                        maxStart = start;
                        maxEnd = end;
                        maxLength = length;
                    }
                }
            }
        }

        if(maxLength > 1) { // We know that maxlength is < trees.size() as those constraints are already in
            // sc.
            // Add trees before the segment
            List<TransTreeVoid> parts = new ArrayList<>();
            for(int i = 0; i < maxStart; i++)
                parts.add(transformVoid(trees.get(i)));

            // Construct set of trees in a conditional.
            // First we get the set of constraints that we will test.
            Set<ConstraintDescription> possibleConstraints = new HashSet<>(constraints.get(maxStart));
            possibleConstraints.removeAll(sc);

            Set<ConstraintDescription> toRemove = new HashSet<>();
            for(ConstraintDescription c:possibleConstraints)
                if(!c.stableGuard)
                    toRemove.add(c);
            possibleConstraints.removeAll(toRemove);

            for(int i = maxStart + 1; i < maxEnd; i++) {
                toRemove.clear();
                Set<ConstraintDescription> cs = constraints.get(i);
                for(ConstraintDescription c:possibleConstraints)
                    if(!cs.contains(c))
                        toRemove.add(c);
                possibleConstraints.removeAll(toRemove);
            }

            // Now construct the required tree.
            sc = new HashSet<>(sc);
            setConstraints.push(sc);
            sc.addAll(possibleConstraints);
            List<TransTreeVoid> subTrees = new ArrayList<>();
            for(int i = maxStart; i < maxEnd; i++)
                subTrees.add(scope(transformVoid(trees.get(i))));
            setConstraints.pop();

            // And place them in an if
            List<TransTreeReturn<BooleanVariable>> cs = new ArrayList<>();
            PriorityQueue<ConstraintDescription> p = new PriorityQueue<>(possibleConstraints);
            while(!p.isEmpty()) {
                ConstraintDescription d = p.poll();
                cs.add(transform(d.constraint));
            }

            parts.add(ifElse(and(cs), sequential(subTrees, Tree.NoComment),
                    "Constraints moved from conditionals in inner loops/scopes/etc.", Collections.emptySet()));

            // Add trees after the segment
            for(int i = maxEnd; i < trees.size(); i++)
                parts.add(transformVoid(trees.get(i)));
            return sequential(parts, s.getComment());
        }

        else
            return s.applyTransformation(this);
    }

    private TransTreeVoid scope(TransTreeVoid t) {
        switch(t.type) {
            case SEQUENTIAL: {
                boolean declaration = false;
                for(TransTreeVoid st:((TransSequential) t).getTrees()) {
                    switch(st.type) {
                        case INITIALIZE:
                        case INITIALIZE_UNSET:
                            declaration = true;
                            break;
                        default:
                            break;
                    }
                }
                if(declaration)
                    return treeScope(t, Tree.NoComment);
                else
                    return t;
            }
            default:
                return t;

        }
    }
}
