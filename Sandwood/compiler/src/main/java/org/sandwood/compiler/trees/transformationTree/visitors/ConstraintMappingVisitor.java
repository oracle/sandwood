/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.visitors;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.transformationTree.TransFor;
import org.sandwood.compiler.trees.transformationTree.TransIfElse;
import org.sandwood.compiler.trees.transformationTree.TransInitialize;
import org.sandwood.compiler.trees.transformationTree.TransInitializeUnset;
import org.sandwood.compiler.trees.transformationTree.TransSequential;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTree.TransTreeType;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;
import org.sandwood.compiler.trees.transformationTree.binop.TransAnd;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.ScopedVarSet;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VarDef;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VariableTracking;

public class ConstraintMappingVisitor implements TreeVisitor {
    public final Map<TransTree<?>, Set<ConstraintDescription>> mapping = new HashMap<>();
    private final Stack<Set<ConstraintDescription>> availableConstraints = new Stack<>();
    public final VariableTracking varTracking;

    public ConstraintMappingVisitor(TransTree<?> t) {
        availableConstraints.push(new LinkedHashSet<>());
        varTracking = t.getVariableTracking();
        mapping.put(TransTree.nop(), Collections.emptySet());
    }

    @Override
    public void visit(TransTree<?> tree) {
        switch(tree.type) {
            case FOR:
                getConstraintMapping((TransFor) tree);
                break;

            case IF:
                getConstraintMapping((TransIfElse) tree);
                break;

            case SEQUENTIAL:
                getConstraintMapping((TransSequential) tree);
                break;

            case ALLOCATE_ARRAY:
            case AND:
            case ARRAY_GET:
            case ARRAY_PUT:
            case SUBTRACT:
            case MULTIPLY:
            case REMAINDER:
            case DIVIDE:
            case ADD:
            case CAST_DOUBLE:
            case CAST_INT:
            case CONDITIONAL_ASSIGNMENT:
            case CONST_BOOLEAN:
            case CONST_DOUBLE:
            case CONST_INT:
            case EQUALITY:
            case LOCAL_FUNCTION_CALL:
            case RV_FUNCTION_CALL:
            case LOCAL_FUNCTION_CALL_RETURN:
            case EXTERNAL_FUNCTION_CALL_RETURN:
            case RV_FUNCTION_CALL_RETURN:
            case GET_FIELD:
            case INITIALIZE:
            case INITIALIZE_UNSET:
            case LESS_THAN:
            case LESS_THAN_EQUAL:
            case LOAD:
            case MAX:
            case MIN:
            case NEGATE:
            case NEGATE_BOOLEAN:
            case NOP:
            case OR:
            case SCOPE:
            case STORE:
                tree.traverseTree(this);
                addTree(tree);
                break;
            default:
                throw new CompilerException("Unknown tree type " + tree.type);

        }
    }

    private void getConstraintMapping(TransFor tree) {
        tree.traverseTree(this);
        addTree(tree);
        Set<ConstraintDescription> toRemove = new HashSet<>();
        Set<ConstraintDescription> currentConstraints = availableConstraints.peek();
        for(ConstraintDescription c:currentConstraints)
            if(!c.stableGuard)
                toRemove.add(c);
        currentConstraints.removeAll(toRemove);
    }

    private void getConstraintMapping(TransIfElse t) {
        visit(t.ifBody);
        addTree(t); // This statement must come second to avoid over writing.

        if(t.elseBody.type == TransTreeType.NOP) {// Conditional can only be pushed out if there is not an else
            addTree(t, t.condition, t.ifBody);
        } else // If there is an else it might have shared constraints that can be drawn out.
            startElseBranch(t.elseBody);
    }

    private void getConstraintMapping(TransSequential s) {
        // At least one other value must exist else this would have been removed and
        // just the tree[0] returned.
        List<TransTreeVoid> trees = s.getTrees();
        int size = trees.size();
        int i = 0;
        while(i < size) {
            TransTreeVoid tree = trees.get(i++);
            if(tree.type != TransTreeType.INITIALIZE && tree.type != TransTreeType.INITIALIZE_UNSET) {
                visit(tree); // Find the constraints of the first tree
                break;
            }
        }

        // This statement must come second to avoid over writing. It adds the terms from
        // the first tree.
        // They will then be constrained to the union of any further branches.
        addTree(s);

        while(i < size) {
            TransTreeVoid tree = trees.get(i++);
            if(tree.type != TransTreeType.INITIALIZE && tree.type != TransTreeType.INITIALIZE_UNSET)
                startSeqSubTree(tree);
        }

        // Finally construct sets for any initialize statements from the union
        // of the constraints that follow it, and where the tree uses the value.
        for(i = size - 1; i >= 0; i--) {
            TransTreeVoid tree = trees.get(i);
            if(tree.type == TransTreeType.INITIALIZE) {
                VariableDescription<?> varDesc = ((TransInitialize<?>) tree).varDesc;
                addTree(tree, populateInitConditions(varDesc, i, size, trees));
            } else if(tree.type == TransTreeType.INITIALIZE_UNSET) {
                VariableDescription<?> varDesc = ((TransInitializeUnset<?>) tree).varDesc;
                addTree(tree, populateInitConditions(varDesc, i, size, trees));
            }
        }
    }

    private Set<ConstraintDescription> populateInitConditions(VariableDescription<?> desc, int start, int end,
            List<TransTreeVoid> trees) {
        boolean first = true;
        Set<ConstraintDescription> toReturn = new LinkedHashSet<>();
        for(int j = end - 1; j > start; j--) {
            TransTreeVoid t2 = trees.get(j);
            if(varTracking.read(t2, desc) && first) {
                first = false;
                for(ConstraintDescription d:mapping.get(t2))
                    if(!d.requiredScope.containsVar(desc))
                        toReturn.add(d);
            }
            // Once we have met the first element that uses the values we must ensure that
            // the
            // initialisations are in the same scope, so it become the union of all
            // constraints
            // after that. This means that if the initialisation will be in the same scope
            // or
            // an outer scope.
            else if(!first) {
                Set<ConstraintDescription> toRemove = new LinkedHashSet<>();
                Set<ConstraintDescription> t2Constraints = mapping.get(t2);
                for(ConstraintDescription c:toReturn) {
                    if(!t2Constraints.contains(c))
                        toRemove.add(c);
                }
                toReturn.removeAll(toRemove);
            }
        }

        return toReturn;
    }

    // Method for adding a tree with an artificially constructed set of constraints.
    // This is only used for init statements.
    private void addTree(TransTree<?> t, Set<ConstraintDescription> constraints) {
        mapping.put(t, constraints);
    }

    private Set<ConstraintDescription> addTree(TransTree<?> t) {
        // Work out which constraints are possible. As addition is done on the way out
        // of a traversal Any constraint that cannot be met here cannot be met further
        // out, so is removed.
        Set<ConstraintDescription> possibleConstraints = new LinkedHashSet<>();
        ScopedVarSet currentScope = varTracking.inScopeVars(t);
        for(ConstraintDescription d:availableConstraints.pop()) {
            boolean allVars = true;
            for(VariableDescription<?> varDesc:d.requiredScope.getVars()) {
                VarDef inScopeWrites = currentScope.getVarDef(varDesc);
                if(inScopeWrites != null) {
                    VarDef requiredWrites = d.requiredScope.getVarDef(varDesc);
                    allVars = allVars && (inScopeWrites.containsInstances(requiredWrites));
                } else
                    allVars = false;
            }

            if(allVars)
                possibleConstraints.add(d);
        }

        mapping.put(t, possibleConstraints);
        availableConstraints.push(possibleConstraints);

        return possibleConstraints;
    }

    private void addTree(TransTree<?> t, TransTreeReturn<BooleanVariable> constraint, TransTreeVoid body) {

        Set<ConstraintDescription> possibleConstraints = (mapping.containsKey(t)) ? mapping.get(t) : addTree(t);

        if(constraint != null) {
            if(constraint.type == TransTreeType.AND) {
                for(TransTreeReturn<BooleanVariable> c:((TransAnd) constraint).getConstraints()) {
                    ConstraintDescription d = constraintDescription(c, body);
                    // Local constraints are always possible, so don't need testing.
                    possibleConstraints.add(d);
                }
            } else {
                ConstraintDescription d = constraintDescription(constraint, body);
                // Local constraints are always possible, so don't need testing.
                possibleConstraints.add(d);
            }
        }
    }

    public ConstraintDescription constraintDescription(TransTreeReturn<BooleanVariable> constraint,
            TransTreeVoid body) {
        ScopedVarSet readVars = varTracking.readVars(constraint);
        Set<VariableDescription<?>> written = varTracking.written(body);
        Set<VariableDescription<?>> arraysModified = varTracking.arraysModified(body);
        boolean stableGuard = true;
        for(VariableDescription<?> v:readVars.getVars())
            stableGuard = stableGuard && !written.contains(v) && !arraysModified.contains(v);

        ConstraintDescription d = new ConstraintDescription(readVars, stableGuard, constraint);
        return d;
    }

    private void startElseBranch(TransTree<?> branch) {
        availableConstraints.push(new LinkedHashSet<>());
        visit(branch);

        // Calculate the union of the existing set, and the results from the branch.
        Set<ConstraintDescription> branchConstraints = availableConstraints.pop();
        Set<ConstraintDescription> toRemove = new HashSet<>();
        Set<ConstraintDescription> toAdd = new HashSet<>();

        Set<ConstraintDescription> currentConstraints = availableConstraints.peek();
        for(ConstraintDescription c1:currentConstraints) {
            boolean found = false;
            for(ConstraintDescription c2:branchConstraints) {
                if(c1.constraint.equivalent(c2.constraint) && c1.requiredScope.equals(c2.requiredScope)) {
                    if(c2.stableGuard)
                        found = true;
                    else
                        toAdd.add(c2);
                }
            }
            if(!found)
                toRemove.add(c1);
        }

        currentConstraints.removeAll(toRemove);
        currentConstraints.addAll(toAdd);
    }

    private void startSeqSubTree(TransTree<?> subTree) {
        availableConstraints.push(new LinkedHashSet<>());
        visit(subTree);

        // Calculate the union of the existing set, and the results from the branch.
        Set<ConstraintDescription> subTreeConstraints = availableConstraints.pop();
        Set<ConstraintDescription> toRemove = new HashSet<>();
        Set<ConstraintDescription> toAdd = new HashSet<>();

        Set<ConstraintDescription> currentConstraints = availableConstraints.peek();
        for(ConstraintDescription c1:currentConstraints) {
            boolean found = false;
            for(ConstraintDescription c2:subTreeConstraints) {
                if(c1.constraint.equivalent(c2.constraint) && c1.requiredScope.equals(c2.requiredScope)) {
                    if(c1.stableGuard) {
                        if(c2.stableGuard)
                            found = true;
                        else
                            toAdd.add(c2);
                    }
                }
            }
            if(!found)
                toRemove.add(c1);
        }

        currentConstraints.removeAll(toRemove);
        currentConstraints.addAll(toAdd);
    }
}
