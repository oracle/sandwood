/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.transformers;

import static org.sandwood.compiler.trees.transformationTree.TransTree.constant;
import static org.sandwood.compiler.trees.transformationTree.TransTree.or;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.transformationTree.TransNegateBoolean;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTree.TransTreeType;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;
import org.sandwood.compiler.trees.transformationTree.WrappedTransReturn;
import org.sandwood.compiler.trees.transformationTree.binop.TransAnd;
import org.sandwood.compiler.trees.transformationTree.binop.TransOr;

public class ExtractCommonContraintsTransformer extends Transformer {

    @Override
    public TransTreeVoid transformVoid(TransTreeVoid tree) {
        switch(tree.type) {
            case ARRAY_PUT:
            case FOR:
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

    @SuppressWarnings("unchecked")
    @Override
    public <X extends Variable<X>> TransTreeReturn<X> transformReturn(TransTreeReturn<X> tree) {
        switch(tree.type) {
            case AND:
                visitedNodes.add(tree);
                return (TransTreeReturn<X>) extractCommonConstraints((TransAnd) tree);

            case OR:
                visitedNodes.add(tree);
                return (TransTreeReturn<X>) extractCommonConstraints((TransOr) tree);

            case ALLOCATE_ARRAY:
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
                return tree.applyTransformation(this);

            default:
                throw new CompilerException("Unknown tree type " + tree.type + " encountered");
        }
    }

    private TransTreeReturn<BooleanVariable> extractCommonConstraints(TransAnd tree) {
        List<TransTreeReturn<BooleanVariable>> newConstraints = new ArrayList<>();

        Set<WrappedTransReturn<BooleanVariable>> negatedTrees = new HashSet<>();
        Set<WrappedTransReturn<BooleanVariable>> nonNegatedTrees = new HashSet<>();

        Map<WrappedTransReturn<BooleanVariable>, Set<List<WrappedTransReturn<BooleanVariable>>>> parts = new LinkedHashMap<>();
        WrappedTransReturn<BooleanVariable> unitValue = new WrappedTransReturn<>(TransTree.constant(false));

        for(WrappedTransReturn<BooleanVariable> c:tree.getWrappedConstraints()) {
            // Extract common components from the child constraints
            TransTreeReturn<BooleanVariable> newTree = transform(c.tree);

            // Check if the negation of this value exists in the relations we have seen so
            // far.
            if(newTree.type == TransTreeType.NEGATE_BOOLEAN) {
                if(nonNegatedTrees.contains(c))
                    return constant(false);
                else
                    negatedTrees.add(new WrappedTransReturn<>(((TransNegateBoolean) newTree).input));
            } else {

                if(negatedTrees.contains(c))
                    return constant(false);
                else
                    nonNegatedTrees.add(new WrappedTransReturn<>(newTree));
            }

            // construct a mapping of all the expressions so that we can look for common
            // terms.
            if(newTree.type == TransTreeType.OR) {
                TransOr o = (TransOr) newTree;
                List<WrappedTransReturn<BooleanVariable>> ocs = o.getWrappedConstraints();
                for(WrappedTransReturn<BooleanVariable> oc:ocs) {
                    Set<List<WrappedTransReturn<BooleanVariable>>> s = parts.computeIfAbsent(oc,
                            k -> new LinkedHashSet<>());
                    s.add(ocs);
                }
            } else {
                List<WrappedTransReturn<BooleanVariable>> ocs = new ArrayList<>(2);
                WrappedTransReturn<BooleanVariable> oc = new WrappedTransReturn<>(newTree);
                ocs.add(oc);
                ocs.add(unitValue); // A unit type for or so singleton terms can be included in the list.

                Set<List<WrappedTransReturn<BooleanVariable>>> s = parts.computeIfAbsent(oc,
                        k -> new LinkedHashSet<>());
                s.add(ocs);

                s = parts.computeIfAbsent(unitValue, k -> new LinkedHashSet<>());
                s.add(ocs);
            }
        }

        // See if the same element appears in more than 1 and statement.
        int maxSize = 0;
        WrappedTransReturn<BooleanVariable> maxElement = null;
        for(WrappedTransReturn<BooleanVariable> c:parts.keySet()) {
            if(c != unitValue) {
                Set<List<WrappedTransReturn<BooleanVariable>>> s = parts.get(c);
                int sSize = s.size();
                if(maxSize < sSize) {
                    maxSize = sSize;
                    maxElement = c;
                }
            }
        }

        // Extract the most commonly repeated element.
        if(maxSize > 1) {
            // get the elements we are going to encode.
            Set<List<WrappedTransReturn<BooleanVariable>>> s = parts.remove(maxElement);

            // Remove the list that appear in set s from all the other locations they appear
            // in parts.
            for(List<WrappedTransReturn<BooleanVariable>> l:s)
                for(WrappedTransReturn<BooleanVariable> e:l)
                    if(!e.equals(maxElement))
                        parts.get(e).remove(l);

            List<TransTreeReturn<BooleanVariable>> andParts = new ArrayList<>();
            for(List<WrappedTransReturn<BooleanVariable>> l:s) {
                List<TransTreeReturn<BooleanVariable>> orConstraints = new ArrayList<>();
                for(WrappedTransReturn<BooleanVariable> e:l)
                    if(!e.equals(maxElement))
                        orConstraints.add(e.tree);
                andParts.add(TransTree.or(orConstraints));
            }

            TransTreeReturn<BooleanVariable> and = TransTree.and(andParts);
            and = transform(and);
            newConstraints.add(TransTree.or(maxElement.tree, and));
        }

        // Add all the elements that we not part of the merge back into the expression.
        Set<List<WrappedTransReturn<BooleanVariable>>> orParts = new LinkedHashSet<>();
        for(Set<List<WrappedTransReturn<BooleanVariable>>> s:parts.values())
            orParts.addAll(s);
        for(List<WrappedTransReturn<BooleanVariable>> l:orParts) {
            List<TransTreeReturn<BooleanVariable>> orConstraints = new ArrayList<>();
            for(WrappedTransReturn<BooleanVariable> c:l)
                orConstraints.add(c.tree);

            if(orConstraints.size() != 0) // Test required in cases where all the lists that were in the set have been
                                          // removed.
                newConstraints.add(transform(TransTree.or(orConstraints)));
        }

        TransTreeReturn<BooleanVariable> toReturn = TransTree.and(newConstraints);
        // If we made a change run this again to see if we can make another.
        if(maxSize > 1)
            toReturn = transform(toReturn);

        return toReturn;
    }

    private TransTreeReturn<BooleanVariable> extractCommonConstraints(TransOr tree) {
        List<TransTreeReturn<BooleanVariable>> newConstraints = new ArrayList<>();

        Set<WrappedTransReturn<BooleanVariable>> negatedTrees = new HashSet<>();
        Set<WrappedTransReturn<BooleanVariable>> nonNegatedTrees = new HashSet<>();

        Map<WrappedTransReturn<BooleanVariable>, Set<List<WrappedTransReturn<BooleanVariable>>>> parts = new LinkedHashMap<>();
        WrappedTransReturn<BooleanVariable> unitValue = new WrappedTransReturn<>(TransTree.constant(true));

        for(WrappedTransReturn<BooleanVariable> c:tree.getWrappedConstraints()) {
            // Extract common components from the child constraints
            TransTreeReturn<BooleanVariable> newTree = transform(c.tree);

            // Check if the negation of this value exists in the relations we have seen so
            // far.
            if(newTree.type == TransTreeType.NEGATE_BOOLEAN) {
                if(nonNegatedTrees.contains(c))
                    return TransTree.constant(true);
                else
                    negatedTrees.add(new WrappedTransReturn<>(((TransNegateBoolean) newTree).input));
            } else {

                if(negatedTrees.contains(c))
                    return TransTree.constant(true);
                else
                    nonNegatedTrees.add(new WrappedTransReturn<>(newTree));
            }

            // For and expressions construct a mapping of elements to all the lists of
            // constraints they appear in.
            if(newTree.type == TransTreeType.AND) {
                TransAnd a = (TransAnd) newTree;
                List<WrappedTransReturn<BooleanVariable>> acs = a.getWrappedConstraints();
                for(WrappedTransReturn<BooleanVariable> ac:acs) {
                    Set<List<WrappedTransReturn<BooleanVariable>>> s = parts.computeIfAbsent(ac,
                            k -> new LinkedHashSet<>());
                    s.add(acs);
                }
            } else {
                List<WrappedTransReturn<BooleanVariable>> ocs = new ArrayList<>(2);
                WrappedTransReturn<BooleanVariable> oc = new WrappedTransReturn<>(newTree);
                ocs.add(oc);
                ocs.add(unitValue); // A unit type for or so singleton terms can be included in the list.

                Set<List<WrappedTransReturn<BooleanVariable>>> s = parts.computeIfAbsent(oc,
                        k -> new LinkedHashSet<>());
                s.add(ocs);

                s = parts.computeIfAbsent(unitValue, k -> new LinkedHashSet<>());
                s.add(ocs);
            }
        }

        // See if the same element appears in more than 1 and statement.
        int maxSize = 0;
        WrappedTransReturn<BooleanVariable> maxElement = null;
        for(WrappedTransReturn<BooleanVariable> c:parts.keySet()) {
            if(c != unitValue) {
                Set<List<WrappedTransReturn<BooleanVariable>>> s = parts.get(c);
                int sSize = s.size();
                if(maxSize < sSize) {
                    maxSize = sSize;
                    maxElement = c;
                }
            }
        }

        // Extract the most commonly repeated element.
        if(maxSize > 1) {
            // get the elements we are going to encode.
            Set<List<WrappedTransReturn<BooleanVariable>>> s = parts.remove(maxElement);

            // Remove the list that appear in set s from all the other locations they appear
            // in parts.
            for(List<WrappedTransReturn<BooleanVariable>> l:s)
                for(WrappedTransReturn<BooleanVariable> e:l)
                    if(!e.equals(maxElement))
                        parts.get(e).remove(l);

            List<TransTreeReturn<BooleanVariable>> orParts = new ArrayList<>();
            for(List<WrappedTransReturn<BooleanVariable>> l:s) {
                List<TransTreeReturn<BooleanVariable>> andConstraints = new ArrayList<>();
                for(WrappedTransReturn<BooleanVariable> e:l)
                    if(!e.equals(maxElement))
                        andConstraints.add(e.tree);
                orParts.add(TransTree.and(andConstraints));
            }

            TransTreeReturn<BooleanVariable> or = TransTree.or(orParts);
            or = transform(or);
            newConstraints.add(TransTree.and(maxElement.tree, or));
        }

        // Add all the elements that we not part of the merge back into the expression.
        Set<List<WrappedTransReturn<BooleanVariable>>> andParts = new LinkedHashSet<>();
        for(Set<List<WrappedTransReturn<BooleanVariable>>> s:parts.values())
            andParts.addAll(s);
        for(List<WrappedTransReturn<BooleanVariable>> l:andParts) {
            List<TransTreeReturn<BooleanVariable>> andConstraints = new ArrayList<>();
            for(WrappedTransReturn<BooleanVariable> c:l)
                andConstraints.add(c.tree);

            if(andConstraints.size() != 0) // Test required in cases where all the lists that were in the set have been
                                           // removed.
                newConstraints.add(TransTree.and(andConstraints));
        }

        TransTreeReturn<BooleanVariable> toReturn = or(newConstraints);
        // If we made a change run this again to see if we can make another.
        if(maxSize > 1)
            toReturn = transform(toReturn);

        return toReturn;
    }
}
