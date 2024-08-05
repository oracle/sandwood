/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.util;

import java.util.HashMap;
import java.util.Map;

import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.TreeID;
import org.sandwood.compiler.trees.transformationTree.TransConditionalAssignment;
import org.sandwood.compiler.trees.transformationTree.TransConstInt;
import org.sandwood.compiler.trees.transformationTree.TransFor;
import org.sandwood.compiler.trees.transformationTree.TransInitialize;
import org.sandwood.compiler.trees.transformationTree.TransLoad;
import org.sandwood.compiler.trees.transformationTree.TransNegate;
import org.sandwood.compiler.trees.transformationTree.TransStore;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTree.TransTreeType;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;
import org.sandwood.compiler.trees.transformationTree.binop.TransAdd;
import org.sandwood.compiler.trees.transformationTree.binop.TransDivide;
import org.sandwood.compiler.trees.transformationTree.binop.TransMax;
import org.sandwood.compiler.trees.transformationTree.binop.TransMin;
import org.sandwood.compiler.trees.transformationTree.binop.TransMultiply;
import org.sandwood.compiler.trees.transformationTree.binop.TransSubtract;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.AssignmentDesc;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VarDef;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VariableTracking;

/**
 * A Utility class for returning the greatest common denominator of a tree returning an int if it can be calculated, and
 * returning 1 otherwise.
 */
public class IntGCD {

    private final TransTree<?> tree;
    private final VariableTracking vars;
    private Map<TreeID, GcdInfo> gcds = null;
    private Map<TransLoad<IntVariable>, TransLoad<IntVariable>> newLoads = new HashMap<>();

    private class GcdInfo {
        final TransTreeVoid source;
        int gcd = 0; // 0 used to mark the gcd as uncomputed.

        GcdInfo(TransTreeVoid source) {
            this.source = source;
        }
    }

    public IntGCD(TransTree<?> tree, VariableTracking vars) {
        this.tree = tree;
        this.vars = vars;
    }

    public int getGCD(TransTreeReturn<IntVariable> t) {
        // Delay these steps till they are required.
        if(gcds == null)
            initializeGCDMap();

        return gcd(t);
    }

    /**
     * Method to calculate the GCD of two integers
     * 
     * @param a
     * @param b
     * @return
     */
    private int gcd(int a, int b) {
        while(true) {
            if(a < b) {
                if(b % a == 0)
                    return a;
                int c = b / a;
                b -= a * c;
            } else {
                if(a % b == 0)
                    return b;
                int c = a / b;
                a -= b * c;
            }
        }
    }

    private int gcd(TransTreeReturn<IntVariable> t) {
        switch(t.type) {
            case ADD: {
                TransAdd<IntVariable, IntVariable, IntVariable> ta = (TransAdd<IntVariable, IntVariable, IntVariable>) t;
                return gcd(gcd(ta.left), gcd(ta.right));
            }
            case CONDITIONAL_ASSIGNMENT: {
                TransConditionalAssignment<IntVariable> ca = (TransConditionalAssignment<IntVariable>) t;
                return gcd(gcd(ca.ifValue), gcd(ca.elseValue));
            }
            case CONST_INT: {
                TransConstInt ci = (TransConstInt) t;
                if(ci.value > 0)
                    return ci.value;
                if(ci.value == 0)
                    return 1;
                return -ci.value;
            }
            case DIVIDE: {
                TransDivide<IntVariable, IntVariable, IntVariable> td = (TransDivide<IntVariable, IntVariable, IntVariable>) t;
                int left = gcd(td.left);
                int right = gcd(td.right);
                if(left % right == 0)
                    return left / right;
                else
                    return 1;
            }
            case LOAD: {
                TransLoad<IntVariable> tl = (TransLoad<IntVariable>) t;
                // Dereference this load if it has been recreated since vars was constructed.
                tl = newLoads.computeIfAbsent(tl, k -> k);

                // Get the locations the value could have been written from.
                VarDef read = vars.readVars(t).getVarDef(tl.varDesc);
                boolean first = true;
                int gcd = 1;

                for(AssignmentDesc a:read.currentAssignments()) {
                    TreeID location = a.assignmentLocation();
                    if(location == TreeID.global)
                        return 1;

                    GcdInfo g = gcds.get(location);
                    if(g.gcd == 0) {
                        switch(g.source.type) {
                            case FOR: {
                                TransFor tf = (TransFor) g.source;
                                if(tf.start.type == TransTreeType.CONST_INT && ((TransConstInt) tf.start).value == 0)
                                    g.gcd = gcd(tf.step);
                                else
                                    g.gcd = gcd(gcd(tf.start), gcd(tf.step));
                                break;
                            }
                            case INITIALIZE: {
                                TransInitialize<IntVariable> ti = (TransInitialize<IntVariable>) g.source;
                                g.gcd = gcd(ti.value);
                                break;
                            }
                            case STORE: {
                                TransStore<IntVariable> ts = (TransStore<IntVariable>) g.source;
                                g.gcd = gcd(ts.value);
                                break;
                            }
                            default:
                                throw new CompilerException("Unexpected source type");
                        }
                    }

                    // Merge the gcd results together.
                    if(first) {
                        gcd = g.gcd;
                        first = false;
                    } else
                        gcd = gcd(gcd, g.gcd);
                }
                return gcd;
            }
            case MAX: {
                TransMax<IntVariable> tm = (TransMax<IntVariable>) t;
                return gcd(gcd(tm.left), gcd(tm.right));
            }
            case MIN: {
                TransMin<IntVariable> tm = (TransMin<IntVariable>) t;
                return gcd(gcd(tm.left), gcd(tm.right));
            }
            case MULTIPLY: {
                TransMultiply<IntVariable, IntVariable, IntVariable> tm = (TransMultiply<IntVariable, IntVariable, IntVariable>) t;
                return gcd(tm.left) * gcd(tm.right);
            }
            case NEGATE: {
                TransNegate<IntVariable> tn = (TransNegate<IntVariable>) t;
                return gcd(tn.input);
            }
            case SUBTRACT: {
                TransSubtract<IntVariable, IntVariable, IntVariable> ts = (TransSubtract<IntVariable, IntVariable, IntVariable>) t;
                return gcd(gcd(ts.left), gcd(ts.right));
            }
            default:
                return 1;
        }
    }

    private void initializeGCDMap() {
        gcds = new HashMap<>();
        new TreeVisitor() {

            @Override
            public void visit(TransTree<?> tree) {
                switch(tree.type) {
                    case FOR:
                    case INITIALIZE:
                    case STORE:
                        gcds.put(tree.id, new GcdInfo((TransTreeVoid) tree));
                        break;
                    default:
                        break;
                }
                tree.traverseTree(this);
            }

        }.visit(tree);
    }

    /**
     * Method for adding in mappings between loads as new trees are constructed.
     * 
     * @param oldTree The tree that represented the same load in the last iteration.
     * @param newTree The new tree to be added.
     */
    public void addMapping(TransLoad<IntVariable> oldTree, TransLoad<IntVariable> newTree) {
        // Add the new value dereferencing the old value if it is already in the map.
        newLoads.put(newTree, newLoads.computeIfAbsent(oldTree, k -> k));
    }
}
