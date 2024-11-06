/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.util;

import static org.sandwood.compiler.trees.transformationTree.TransTree.eq;
import static org.sandwood.compiler.trees.transformationTree.TransTree.load;
import static org.sandwood.compiler.trees.transformationTree.TransTree.max;
import static org.sandwood.compiler.trees.transformationTree.TransTree.min;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableName;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.TreeID;
import org.sandwood.compiler.trees.transformationTree.TransLoad;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTree.TransTreeType;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.util.KnownValuesTrans.KnownValue;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.ScopedVarSet;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VarDef;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VariableTracking;

public class Bounds {
    private static class MinMax {
        public final TransTreeReturn<IntVariable> min;
        public final TransTreeReturn<IntVariable> max;

        public MinMax(TransTreeReturn<IntVariable> a, TransTreeReturn<IntVariable> b) {
            this.min = a;
            this.max = b;
        }

        @Override
        public String toString() {
            return "Min: " + min.toString() + "\nMax: " + max.toString();
        }
    }

    // TODO constrain VariableDescription to types that extend number variable
    private final Map<VariableDescription<?>, Map<TreeID, Stack<MinMax>>> bounds = new HashMap<>();
    private final VariableTracking vars;
    public final IntGCD gcds;
    private String filter = null;

    public Bounds(ArgDesc<?>[] args, KnownValuesTrans knownValues, TransTree<?> tree) {
        Map<ArgDesc<?>, TransTreeReturn<IntVariable>> minTrees = new HashMap<>();
        Map<ArgDesc<?>, TransTreeReturn<IntVariable>> maxTrees = new HashMap<>();
        Set<TransTree<?>> additionalTrees = new HashSet<>();
        Set<VariableDescription<?>> argsDescs = new HashSet<>();

        for(ArgDesc<?> a:args) {
            // The bounds are the start and end of loops, so if the min is set the max will also be set.
            if(a.min != null) {
                TransTreeReturn<IntVariable> min = a.min.toTransformationTree();
                minTrees.put(a, min);
                additionalTrees.add(min);

                TransTreeReturn<IntVariable> max = a.max.toTransformationTree();
                maxTrees.put(a, max);
                additionalTrees.add(max);

                argsDescs.add(a.varDesc);
            }
        }

        for(KnownValue v:knownValues)
            additionalTrees.add(v.expr);

        vars = tree.getVariableTracking(additionalTrees, argsDescs);
        gcds = new IntGCD(tree, vars);

        for(ArgDesc<?> a:args) {
            if(a.min != null) {
                Stack<MinMax> s = new Stack<>();
                TransTreeReturn<IntVariable> min = minTrees.get(a);
                TransTreeReturn<IntVariable> max = maxTrees.get(a);
                s.push(new MinMax(min, max));
                Map<TreeID, Stack<MinMax>> m = new HashMap<>();
                m.put(TreeID.global, s);
                bounds.put(a.varDesc, m);

                // If possible add bound to the start and end values of the for loop.
                VariableDescription<IntVariable> minDesc = (VariableDescription<IntVariable>) getTargetName(min);
                if(minDesc != null) {
                    VariableDescription<IntVariable> indexDesc = (VariableDescription<IntVariable>) a.varDesc;
                    TransTreeReturn<IntVariable> startEndMax = RearrangeTree.rearrangeTree(eq(min, max), minDesc, gcds);
                    TransTreeReturn<IntVariable> startIndexMax = RearrangeTree.rearrangeTree(eq(min, load(indexDesc)),
                            minDesc, gcds);
                    TransTreeReturn<IntVariable> startMax;
                    if(startEndMax != null) {
                        if(startIndexMax != null)
                            startMax = min(startEndMax, startIndexMax);
                        else
                            startMax = startEndMax;
                    } else {
                        startMax = startIndexMax;
                    }
                    if(startMax != null) {
                        for(TreeID startTag:inScopeVars(tree).getVarDef(minDesc).writeLocations())
                            addRange(minDesc, startTag, null, startMax);
                    } else
                        minDesc = null;
                }

                VariableDescription<IntVariable> maxDesc = (VariableDescription<IntVariable>) getTargetName(max);
                if(maxDesc != null) {
                    VariableDescription<IntVariable> indexName = (VariableDescription<IntVariable>) a.varDesc;
                    TransTreeReturn<IntVariable> endStartMin = RearrangeTree.rearrangeTree(eq(min, max), maxDesc, gcds);
                    TransTreeReturn<IntVariable> endIndexMin = RearrangeTree.rearrangeTree(eq(max, load(indexName)),
                            maxDesc, gcds);
                    TransTreeReturn<IntVariable> endMin;
                    if(endStartMin != null) {
                        if(endIndexMin != null)
                            endMin = max(endStartMin, endIndexMin);
                        else
                            endMin = endStartMin;
                    } else {
                        endMin = endIndexMin;
                    }

                    if(endMin != null) {
                        ScopedVarSet vs = inScopeVars(tree);
                        VarDef v = vs.getVarDef(maxDesc);
                        for(TreeID endTag:v.writeLocations())
                            addRange(maxDesc, endTag, endMin, null);
                    } else
                        maxDesc = null;
                }
            }
        }
    }

    private static VariableDescription<?> getTargetName(TransTreeReturn<?> t) {
        VariableTracking v = t.getVariableTracking();
        Set<VariableDescription<?>> readVars = v.readVars(t).getVars();
        if(readVars.isEmpty())
            return null;

        // if we are using multiple global labels and no local labels pick the first
        // alphabetically.
        return new PriorityQueue<>(readVars).poll();
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getFilter() {
        return filter;
    }

    public void clearFilter() {
        filter = null;
    }

    public <X extends NumberVariable<X>> TransTreeReturn<?> getMin(TransLoad<?> tree) {
        VariableDescription<?> desc = tree.varDesc;
        if(filter != null && !desc.name.getName().equals(filter))
            return null;

        Map<TreeID, Stack<MinMax>> m = bounds.get(desc);
        if(m == null) {
            // Special case for array lengths.
            if(VariableNames.isLengthName(desc.name.getName()))
                return (TransTreeReturn<X>) TransTree.constant(0);
            else
                return null;
        }

        ScopedVarSet v = vars.readVars(tree);
        if(v == null)
            throw new CompilerException("Unable to recover tags for known bound: " + tree + " id: " + tree.id);

        PriorityQueue<TreeID> tags = new PriorityQueue<>(v.getVarDef(desc).writeLocations());

        // Find the first element;
        TransTreeReturn<X> t = getMin(desc, m.get(tags.poll()));
        if(t == null)// TODO use recursion here to reach forward and find out what the min and max
            // values are going to be.
            return null;

        // If there are more elements merge them in.
        if(!tags.isEmpty()) {
            List<TransTreeReturn<X>> trees = new ArrayList<>();
            trees.add(t);
            do {
                Stack<MinMax> s = m.get(tags.poll());
                // If there are values that are yet to be set as they appear later in an
                // iteration leave. This
                // limits the range of values we can calculate bounds for, but only for values
                // that would be set to a
                // value not dependent on the iteration count later. As such this is not going
                // to be a common restriction
                // for the code we generate.
                if(s == null)
                    return null;

                t = getMin(desc, s);
                if(t == null)// TODO use recursion here to reach forward and find out what the min and max
                    // values are going to be.
                    return null;

                // Check for duplicates;
                for(TransTreeReturn<X> t2:trees)
                    if(t.equivalent(t2)) {
                        t = null;
                        break;
                    }

                // If there are none add t;
                if(t != null)
                    trees.add(t);
            } while(!tags.isEmpty());

            // Construct the return value
            if(trees.size() == 1)
                t = trees.get(0).copy();
            else {
                Iterator<TransTreeReturn<X>> i = trees.iterator();
                t = i.next().copy();
                while(i.hasNext())
                    // This is multiple possible values with the same identifier, so the minimum
                    // value is the
                    // minimum of all the possible values.
                    t = TransTree.min(t, i.next().copy());
            }
        } else
            t = t.copy();

        addTransformedTree(tree, t);
        return t;
    }

    @SuppressWarnings("unchecked")
    private <X extends Variable<X>> TransTreeReturn<X> getMin(VariableDescription<?> desc, Stack<MinMax> s) {
        if(s == null) {
            // Special case for array lengths.
            if(VariableNames.isLengthName(desc.name.getName()))
                return (TransTreeReturn<X>) TransTree.constant(0);
            else
                return null;
        } else
            return (TransTreeReturn<X>) s.peek().min;
    }

    @SuppressWarnings("unchecked")
    // TODO add check to ensure that all values used by the bounds are in scope.
    public <X extends NumberVariable<X>> TransTreeReturn<?> getMax(TransLoad<?> tree) {
        VariableDescription<?> desc = tree.varDesc;
        VariableName name = desc.name;
        if(filter != null && !name.getName().equals(filter))
            return null;

        Map<TreeID, Stack<MinMax>> m = bounds.get(desc);
        if(m == null)
            return null;

        ScopedVarSet v = vars.readVars(tree);
        if(v == null) {
            System.out.println("Unable to recover tags for known bound: " + tree + " id: " + tree.id);
            return null;
        }

        PriorityQueue<TreeID> tags = new PriorityQueue<>(v.getVarDef(desc).writeLocations());

        // Find the first element, the first element must always be set as the variable
        // must have been set before it is used. The only case where this could not be true
        // is if each statement is guarded by an if, and the ifs cannot be collapsed because
        // the later statement is a conjunction of multiple earlier guards. for exmple:
        // if(set_a)
        // x = 5;
        // if(set_b)
        // y = 7;
        // if(set_a && set_b)
        // z = x + y;
        TreeID tag = tags.poll();
        Stack<MinMax> s = m.get(tag);
        if(s == null)
            return null;
        TransTreeReturn<X> t = (TransTreeReturn<X>) s.peek().max;
        if(t == null)
            return null;

        // If there are more elements merge them in.
        if(!tags.isEmpty()) {
            List<TransTreeReturn<X>> trees = new ArrayList<>();
            trees.add(t);
            do {
                s = m.get(tags.poll());
                // If there are values that are yet to be set as they appear later in an
                // iteration leave. This
                // limits the range of values we can calculate bounds for, but only for values
                // that would be set to a
                // value not dependent on the iteration count later. As such this is not going
                // to be a common restriction
                // for the code we generate.
                if(s == null)
                    return null;

                t = (TransTreeReturn<X>) s.peek().max;
                if(t == null)// TODO use recursion here to reach forward and find out what the min and max
                    // values are going to be.
                    return null;
                // Check for duplicates;
                for(TransTreeReturn<X> t2:trees) {
                    if(t.equivalent(t2)) {
                        t = null;
                        break;
                    }
                }
                // If there are none add t;
                if(t != null)
                    trees.add(t);
            } while(!tags.isEmpty());

            // Construct the return value
            if(trees.size() == 1)
                t = trees.get(0).copy();
            else {
                Iterator<TransTreeReturn<X>> i = trees.iterator();
                t = i.next().copy();
                while(i.hasNext())
                    // This is multiple possible values with the same identifier, so the minimum
                    // value is the
                    // minimum of all the possible values.
                    t = TransTree.max(t, i.next().copy());
            }
        } else
            t = t.copy();

        addTransformedTree(tree, t);
        return t;
    }

    public void removeRange(VariableDescription<?> desc, TreeID tag) {
        Map<TreeID, Stack<MinMax>> m = bounds.get(desc);
        if(m != null) {
            Stack<MinMax> s = m.get(tag);
            if(s != null) {
                s.pop();
                if(s.isEmpty())
                    bounds.remove(desc);
            }
        }
    }

    public void addRange(VariableDescription<?> desc, TreeID tag, TransTreeReturn<IntVariable> min,
            TransTreeReturn<IntVariable> max) {
        Map<TreeID, Stack<MinMax>> m = bounds.computeIfAbsent(desc, k -> new HashMap<>());
        Stack<MinMax> s = m.get(tag);
        if(s == null) {
            s = new Stack<>();
            s.push(new MinMax(min, max));
            m.put(tag, s);
        } else {
            // If we reach here we are augmenting the bounds on an already constructed
            // variable. i.e. we have
            // just gone through a conditional on an if. As such we take the max of the
            // mins, and the min of the maxs.
            MinMax minMax = s.peek();
            if(min != null) {
                if(minMax.min != null)
                    min = TransTree.max(min, minMax.min);
            } else
                min = minMax.min;
            if(max != null) {
                if(minMax.max != null)
                    max = TransTree.min(max, minMax.max);
            } else
                max = minMax.max;
            s.push(new MinMax(min, max));
        }
    }

    @SuppressWarnings("unchecked")
    public void addTransformedTree(TransTreeReturn<?> originalTree, TransTreeReturn<?> newTree) {
        vars.addTree(originalTree, newTree);
        if(originalTree.type == TransTreeType.LOAD && newTree.type == TransTreeType.LOAD
                && originalTree.getOutputType() == VariableType.IntVariable)
            gcds.addMapping((TransLoad<IntVariable>) originalTree, (TransLoad<IntVariable>) newTree);
    }

    public ScopedVarSet inScopeVars(TransTree<?> t) {
        return vars.inScopeVars(t);
    }

    /**
     * A method that takes a variable name and id and returns all the variables that must be in scope for its reads and
     * writes to be valid.
     * 
     * @param desc The variable description
     * @param id   The id of the store operation
     * @return The variables that need to be in scope for the min and max to be valid.
     */
    public Set<VariableDescription<?>> requiredMinMaxVars(VariableDescription<?> desc, TreeID id) {
        Map<TreeID, Stack<MinMax>> m = bounds.get(desc);
        if(m == null)
            return Collections.emptySet();

        Set<VariableDescription<?>> s = new HashSet<>();
        MinMax mm = m.get(id).peek();
        if(mm.min != null)
            addReads(mm.min, s);
        if(mm.max != null)
            addReads(mm.max, s);

        return s;
    }

    private void addReads(TransTreeReturn<?> tree, Set<VariableDescription<?>> s) {
        Stack<TransTreeReturn<?>> toProcess = new Stack<>();
        toProcess.push(tree);
        while(!toProcess.isEmpty()) {
            TransTreeReturn<?> t = toProcess.pop();
            if(t.type == TransTreeType.LOAD) {
                s.add(((TransLoad<?>) t).varDesc);
            } else {
                for(TransTree<?> c:t.getChildren())
                    toProcess.add((TransTreeReturn<?>) c);
            }
        }
    }
}
