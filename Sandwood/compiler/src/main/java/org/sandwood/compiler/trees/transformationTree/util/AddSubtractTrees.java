/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.transformationTree.TransCastToDouble;
import org.sandwood.compiler.trees.transformationTree.TransCastToInteger;
import org.sandwood.compiler.trees.transformationTree.TransConstDouble;
import org.sandwood.compiler.trees.transformationTree.TransConstInt;
import org.sandwood.compiler.trees.transformationTree.TransLoad;
import org.sandwood.compiler.trees.transformationTree.TransNegate;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTree.TransTreeType;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.binop.TransAdd;
import org.sandwood.compiler.trees.transformationTree.binop.TransSubtract;
import org.sandwood.compiler.trees.transformationTree.transformers.CollapseConstantsTransformer;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class AddSubtractTrees<X extends NumberVariable<X>> {
    // Non Constants
    private final List<TransTreeReturn<IntVariable>> addInt = new ArrayList<>();
    private final List<TransTreeReturn<IntVariable>> subtractInt = new ArrayList<>();
    private final List<TransTreeReturn<DoubleVariable>> addDouble = new ArrayList<>();
    private final List<TransTreeReturn<DoubleVariable>> subtractDouble = new ArrayList<>();

    // Summed Constants
    private int vi = 0;
    private double vd = 0.0;

    // Dependencies
    private final Map<VariableDescription<?>, Set<TransTreeReturn<?>>> varToTree = new HashMap<>();
    private final Map<TransTreeReturn<?>, Set<VariableDescription<?>>> treeToVar = new HashMap<>();

    private boolean collapsed = false;

    // OutputType
    public final Type<X> outputType;
    private final TransTreeReturn<?> sourceTree;
    private final CollapseConstantsTransformer transformer;
    private final Bounds bounds;

    public AddSubtractTrees(Type<X> outputType, CollapseConstantsTransformer transformer, Bounds bounds,
            TransTreeReturn<?> sourceTree) {
        this.outputType = outputType;
        this.transformer = transformer;
        this.bounds = bounds;
        this.sourceTree = sourceTree;
    }

    public AddSubtractTrees<X> copy() {
        AddSubtractTrees<X> ast = new AddSubtractTrees<>(outputType, transformer, bounds, sourceTree);
        ast.addInt.addAll(addInt);
        ast.addDouble.addAll(addDouble);
        ast.subtractInt.addAll(subtractInt);
        ast.subtractDouble.addAll(subtractDouble);
        ast.vi = vi;
        ast.vd = vd;
        ast.collapsed = collapsed;
        for(VariableDescription<?> desc:varToTree.keySet())
            ast.varToTree.put(desc, new HashSet<>(varToTree.get(desc)));
        ast.treeToVar.putAll(treeToVar);
        return ast;
    }

    public <N extends NumberVariable<N>> void consumeTree(TransTreeReturn<? extends NumberVariable<?>> t) {
        consumeTree(t, false);
    }

    private <N extends NumberVariable<N>> void consumeTree(TransTreeReturn<? extends NumberVariable<?>> t,
            boolean negated) {
        collapsed = false;
        // Remove casting and negation.
        boolean run = true;
        while(run) {
            transformer.visitedNodes.add(t);
            switch(t.type) {
                case CAST_DOUBLE: {
                    t = ((TransCastToDouble) t).input;
                    break;
                }

                case CAST_INT: {
                    t = processIntCast((TransCastToInteger) t);
                    run = false;
                    break;
                }

                case NEGATE: {
                    t = ((TransNegate<?>) t).input;
                    negated = !negated;
                    break;
                }

                default:
                    run = false;
            }
        }

        // Having stripped tree decide what to do.
        switch(t.type) {
            case ADD: {
                TransAdd<?, ?, ?> a = (TransAdd<?, ?, ?>) t;
                consumeTree(a.left, negated);
                consumeTree(a.right, negated);
                break;
            }
            case SUBTRACT: {
                TransSubtract<?, ?, ?> a = (TransSubtract<?, ?, ?>) t;
                consumeTree(a.left, negated);
                consumeTree(a.right, !negated);
                break;
            }
            default: {
                // transform the tree
                t = (TransTreeReturn<? extends NumberVariable<?>>) transformer.transform(t);

                // Remove any additional negations
                run = true;
                while(run) {
                    switch(t.type) {
                        case CAST_DOUBLE: {
                            t = ((TransCastToDouble) t).input;
                            break;
                        }
                        case CAST_INT: {
                            t = processIntCast((TransCastToInteger) t);
                            run = false;
                            break;
                        }

                        case NEGATE: {
                            t = ((TransNegate<?>) t).input;
                            negated = !negated;
                            break;
                        }
                        default:
                            run = false;
                    }
                }

                // and file whatever the result is
                switch(t.type) {
                    case CONST_DOUBLE: {
                        if(negated)
                            vd -= ((TransConstDouble) t).value;
                        else
                            vd += ((TransConstDouble) t).value;
                        break;
                    }
                    case CONST_INT: {
                        if(negated)
                            vi -= ((TransConstInt) t).value;
                        else
                            vi += ((TransConstInt) t).value;
                        break;
                    }
                    default: {
                        addNonConstantTree((TransTreeReturn<N>) t, negated);
                        break;
                    }
                }
                break;
            }
        }
    }

    private TransTreeReturn<IntVariable> processIntCast(TransCastToInteger t) {
        AddSubtractTrees<DoubleVariable> as = new AddSubtractTrees<>(VariableType.DoubleVariable, transformer, bounds,
                sourceTree);
        as.consumeTree(t.input);
        TransTreeReturn<IntVariable> newTree = TransTree.castToInteger(as.getTree());
        bounds.addTransformedTree(t, newTree);
        return newTree;
    }

    @SuppressWarnings("unchecked")
    private <N extends NumberVariable<N>> void addNonConstantTree(TransTreeReturn<N> t, boolean negated) {
        transformer.visitedNodes.add(t);
        addDependencies(t);
        Type<?> outputType = t.getOutputType();
        if(outputType == VariableType.DoubleVariable) {
            if(negated)
                subtractDouble.add((TransTreeReturn<DoubleVariable>) t);
            else
                addDouble.add((TransTreeReturn<DoubleVariable>) t);
        } else if(outputType == VariableType.IntVariable) {
            if(negated)
                subtractInt.add((TransTreeReturn<IntVariable>) t);
            else
                addInt.add((TransTreeReturn<IntVariable>) t);
        } else
            throw new CompilerException("We seem to have a numeric type that is not a double or int.");
    }

    private void addDependencies(TransTreeReturn<?> t) {
        Set<VariableDescription<?>> vars = getVariableNames(t);

        for(VariableDescription<?> v:vars) {
            Set<TransTreeReturn<?>> trees = varToTree.computeIfAbsent(v, k -> new HashSet<>());
            trees.add(t);
        }

        treeToVar.put(t, vars);
    }

    private static class UsedVariableVisitor implements TreeVisitor {
        public final Set<VariableDescription<?>> usedVars = new HashSet<>();

        @Override
        public void visit(TransTree<?> tree) {
            switch(tree.type) {
                case LOAD:
                    usedVars.add(((TransLoad<?>) tree).varDesc);
                    break;
                default:
                    tree.traverseTree(this);
                    break;
            }
        }
    }

    private Set<VariableDescription<?>> getVariableNames(TransTreeReturn<?> tree) {
        UsedVariableVisitor v = new UsedVariableVisitor();
        v.visit(tree);
        return v.usedVars;
    }

    public TransTreeReturn<X> getTree() {
        simplifyTerms();

        if(vd != 0) {
            if(vd == Double.NEGATIVE_INFINITY || vd == Double.POSITIVE_INFINITY || Double.isNaN(vd)) {
                if(outputType == VariableType.DoubleVariable) {
                    TransTreeReturn<X> toReturn = (TransTreeReturn<X>) TransTree.constant(vd);
                    bounds.addTransformedTree(sourceTree, toReturn);
                    return toReturn;
                } else
                    throw new CompilerException(
                            "Optimisations added in a cast that should not have been added and resulted in the loss of information");
            }
            vd += vi;
            vi = 0;
        }

        // Add up the computed values. Subtractions are added as we will perform the
        // subtraction step at the end.
        TransTreeReturn<IntVariable> addIntTree = null;
        if(!addInt.isEmpty()) {
            int size = addInt.size();
            addIntTree = addInt.get(0);

            for(int i = 1; i < size; i++)
                addIntTree = TransTree.addII(addIntTree, addInt.get(i));
        }

        if(vi > 0) {
            if(addIntTree == null)
                addIntTree = TransTree.constant(vi);
            else
                addIntTree = TransTree.addII(addIntTree, TransTree.constant(vi));
        }

        TransTreeReturn<IntVariable> subtractIntTree = null;
        if(!subtractInt.isEmpty()) {
            int size = subtractInt.size();
            subtractIntTree = subtractInt.get(0);

            for(int i = 1; i < size; i++)
                subtractIntTree = TransTree.addII(subtractIntTree, subtractInt.get(i));
        }

        if(vi < 0) {
            if(subtractIntTree == null)
                subtractIntTree = TransTree.constant(-vi);
            else
                subtractIntTree = TransTree.addII(subtractIntTree, TransTree.constant(-vi));
        }

        TransTreeReturn<DoubleVariable> addDoubleTree = null;
        if(!addDouble.isEmpty()) {
            int size = addDouble.size();
            addDoubleTree = addDouble.get(0);

            for(int i = 1; i < size; i++)
                addDoubleTree = TransTree.addDD(addDoubleTree, addDouble.get(i));
        }

        if(vd > 0) {
            if(addDoubleTree == null)
                addDoubleTree = TransTree.constant(vd);
            else
                addDoubleTree = TransTree.addDD(addDoubleTree, TransTree.constant(vd));
        }

        TransTreeReturn<DoubleVariable> subtractDoubleTree = null;
        if(!subtractDouble.isEmpty()) {
            int size = subtractDouble.size();
            subtractDoubleTree = subtractDouble.get(0);

            for(int i = 1; i < size; i++)
                subtractDoubleTree = TransTree.addDD(subtractDoubleTree, subtractDouble.get(i));
        }

        if(vd < 0) {
            if(subtractDoubleTree == null)
                subtractDoubleTree = TransTree.constant(-vd);
            else
                subtractDoubleTree = TransTree.addDD(subtractDoubleTree, TransTree.constant(-vd));
        }

        // 16 possible combinations at this point that need to be merged.

        // Merge in subtraction for ints if there are adds too
        if(addIntTree != null && subtractIntTree != null) {
            addIntTree = TransTree.subtractII(addIntTree, subtractIntTree);
            subtractIntTree = null;
        }

        // Merge adds into double adds
        if(addDoubleTree != null && addIntTree != null) {
            addDoubleTree = TransTree.addDI(addDoubleTree, addIntTree);
            addIntTree = null;
        }

        // Merge in subtraction for doubles if there are adds too
        if(addDoubleTree != null && subtractDoubleTree != null) {
            addDoubleTree = TransTree.subtractDD(addDoubleTree, subtractDoubleTree);
            subtractDoubleTree = null;
        }

        if(addDoubleTree != null && subtractIntTree != null) { // implicit addInt == null
            addDoubleTree = TransTree.subtractDI(addDoubleTree, subtractIntTree);
            subtractIntTree = null;
        }

        if(addIntTree != null && subtractDoubleTree != null) { // implicit addDouble == null
            addDoubleTree = TransTree.subtractID(addIntTree, subtractDoubleTree);
            addIntTree = null;
            subtractDoubleTree = null;
        }

        if(subtractIntTree != null && subtractDoubleTree != null) { // implicit addDouble and addInt == null
            subtractDoubleTree = TransTree.addDI(subtractDoubleTree, subtractIntTree);
            subtractIntTree = null;
        }

        // Negation is not used for constants as it makes them simpler elsewhere.
        if(subtractIntTree != null) { // implicit addDouble, addInt, and subtractDouble are null
            if(subtractIntTree.type == TransTreeType.CONST_INT)
                addIntTree = TransTree.constant(vi);
            else
                addIntTree = TransTree.negate(subtractIntTree);
            subtractIntTree = null;
        }

        // Negation is not used for constants as it makes them simpler elsewhere.
        if(subtractDoubleTree != null) {// implicit addDouble, addInt, and subtractInt are null
            if(subtractDoubleTree.type == TransTreeType.CONST_DOUBLE)
                addDoubleTree = TransTree.constant(vd);
            else
                addDoubleTree = TransTree.negate(subtractDoubleTree);
            subtractDoubleTree = null;
        }

        if(addIntTree != null)
            return constructOutputFromInt(addIntTree);
        else if(addDoubleTree != null) {
            if(outputType == VariableType.DoubleVariable) {
                bounds.addTransformedTree(sourceTree, addDoubleTree);
                return (TransTreeReturn<X>) addDoubleTree;
            } else
                throw new CompilerException(
                        "Optimisations added in a cast that should not have been added and resulted in the loss of information");
        } else
            return constructOutputZero();
    }

    @SuppressWarnings("unchecked")
    private TransTreeReturn<X> constructOutputFromInt(TransTreeReturn<IntVariable> addIntTree) {
        TransTreeReturn<X> toReturn;
        if(outputType == VariableType.DoubleVariable)
            toReturn = (TransTreeReturn<X>) TransTree.castToDouble(addIntTree);
        else
            toReturn = (TransTreeReturn<X>) addIntTree;
        bounds.addTransformedTree(sourceTree, toReturn);
        return toReturn;
    }

    @SuppressWarnings("unchecked")
    private TransTreeReturn<X> constructOutputZero() {
        TransTreeReturn<X> toReturn;
        if(outputType == VariableType.DoubleVariable)
            toReturn = (TransTreeReturn<X>) TransTree.constant(0.0);
        else
            toReturn = (TransTreeReturn<X>) TransTree.constant(0);
        bounds.addTransformedTree(sourceTree, toReturn);
        return toReturn;
    }

    // Remove any terms that cancel each other out.
    private void simplifyTerms() {
        if(!collapsed) {
            collapsed = true;

            int addSize = addInt.size();
            int subSize = subtractInt.size();
            for(int i = 0; i < addSize; i++) {
                TransTreeReturn<IntVariable> w = addInt.get(i);
                for(int j = 0; j < subSize; j++)
                    if(subtractInt.get(j).equivalent(w)) {
                        removeTree(addInt, i);
                        removeTree(subtractInt, j);
                        addSize--;
                        subSize--;
                        i--; // Step back one space as the for loop will increment despite removing this
                        // element.
                        break;
                    }
            }

            addSize = addDouble.size();
            subSize = subtractDouble.size();
            for(int i = 0; i < addSize; i++) {
                TransTreeReturn<DoubleVariable> w = addDouble.get(i);
                for(int j = 0; j < subSize; j++)
                    if(subtractDouble.get(j).equivalent(w)) {
                        removeTree(addDouble, i);
                        removeTree(subtractDouble, j);
                        addSize--;
                        subSize--;
                        i--; // Step back one space as the for loop will increment despite removing this
                        // element.
                        break;
                    }
            }

            // Deal with infinity case
            if(vd == Double.POSITIVE_INFINITY || vd == Double.NEGATIVE_INFINITY || Double.isNaN(vd)) {
                vi = 0;
                addInt.clear();
                subtractInt.clear();
                addDouble.clear();
                subtractDouble.clear();
            }
        }
    }

    public void cancelTerms(AddSubtractTrees<?> otherTerm) {
        simplifyTerms();
        otherTerm.simplifyTerms();

        if(vd != Double.NEGATIVE_INFINITY && vd != Double.POSITIVE_INFINITY && !Double.isNaN(vd)) {
            if(vi + vd != 0 && otherTerm.vi + otherTerm.vd != 0) {
                vi -= otherTerm.vi;
                vd -= otherTerm.vd;
                otherTerm.vi = 0;
                otherTerm.vd = 0;
            }

            // Cancel added ints.
            int size = addInt.size();
            int otherSize = otherTerm.addInt.size();
            for(int i = 0; i < size; i++) {
                TransTreeReturn<IntVariable> w = addInt.get(i);
                for(int j = 0; j < otherSize; j++)
                    if(otherTerm.addInt.get(j).equivalent(w)) {
                        removeTree(addInt, i);
                        otherTerm.removeTree(otherTerm.addInt, j);
                        size--;
                        otherSize--;
                        i--; // Step back one space as the for loop will increment despite removing this
                        // element.
                        break;
                    }
            }

            // Cancel subtracted ints.
            size = subtractInt.size();
            otherSize = otherTerm.subtractInt.size();
            for(int i = 0; i < size; i++) {
                TransTreeReturn<IntVariable> w = subtractInt.get(i);
                for(int j = 0; j < otherSize; j++)
                    if(otherTerm.subtractInt.get(j).equivalent(w)) {
                        removeTree(subtractInt, i);
                        otherTerm.removeTree(otherTerm.subtractInt, j);
                        size--;
                        otherSize--;
                        i--; // Step back one space as the for loop will increment despite removing this
                        // element.
                        break;
                    }
            }

            // Cancel added double.
            size = addDouble.size();
            otherSize = otherTerm.addDouble.size();
            for(int i = 0; i < size; i++) {
                TransTreeReturn<DoubleVariable> w = addDouble.get(i);
                for(int j = 0; j < otherSize; j++)
                    if(otherTerm.addDouble.get(j).equivalent(w)) {
                        removeTree(addDouble, i);
                        otherTerm.removeTree(otherTerm.addDouble, j);
                        size--;
                        otherSize--;
                        i--; // Step back one space as the for loop will increment despite removing this
                        // element.
                        break;
                    }
            }

            // Cancel subtracted doubles.
            size = subtractDouble.size();
            otherSize = otherTerm.subtractDouble.size();
            for(int i = 0; i < size; i++) {
                TransTreeReturn<DoubleVariable> w = subtractDouble.get(i);
                for(int j = 0; j < otherSize; j++)
                    if(otherTerm.subtractDouble.get(j).equivalent(w)) {
                        removeTree(subtractDouble, i);
                        otherTerm.removeTree(otherTerm.subtractDouble, j);
                        size--;
                        otherSize--;
                        i--; // Step back one space as the for loop will increment despite removing this
                        // element.
                        break;
                    }
            }
        }
    }

    public void mergeConstants(AddSubtractTrees<?> right) {
        if((isConstant() && (outputType == VariableType.DoubleVariable || right.vd == 0))
                || (right.outputType != VariableType.DoubleVariable && vd != 0)) {
            vd -= right.vd;
            right.vd = 0;
            vi -= right.vi;
            right.vi = 0;
        } else {
            right.vd -= vd;
            vd = 0;
            right.vi -= vi;
            vi = 0;
        }
    }

    private <A extends Variable<A>> void removeTree(List<TransTreeReturn<A>> l, int i) {
        TransTreeReturn<A> t = l.remove(i);
        removeDependencies(t);
    }

    private void removeDependencies(TransTreeReturn<?> t) {
        Set<VariableDescription<?>> vars = treeToVar.remove(t);
        for(VariableDescription<?> v:vars) {
            Set<TransTreeReturn<?>> trees = varToTree.get(v);
            if(trees.size() == 1)
                varToTree.remove(v);
            else
                trees.remove(t);
        }
    }

    public boolean isConstant() {
        simplifyTerms();
        return addDouble.isEmpty() && addInt.isEmpty() && subtractDouble.isEmpty() && subtractInt.isEmpty();
    }

    public int getIntConstant() {
        assert (vd == 0.0 && isConstant());
        return vi;
    }

    public double getDoubleConstant() {
        assert (isConstant());
        return vi + vd;
    }

    public Set<VariableDescription<?>> getVariableDescs() {
        return varToTree.keySet();
    }

    public boolean maxValue(VariableDescription<?> target) {
        Set<TransTreeReturn<?>> trees = varToTree.get(target);
        if(trees == null)
            return false;

        bounds.setFilter(target.name.getName());

        boolean safe = true;
        for(TransTreeReturn<?> tree:trees) {
            if(null == tree.maxValue(bounds)) {
                safe = false;
                break;
            }
        }

        if(safe) {
            applyMax(addInt, trees, false);
            applyMax(addDouble, trees, false);
        }

        safe = true;
        for(TransTreeReturn<?> tree:trees) {
            if(null == tree.minValue(bounds)) {
                safe = false;
                break;
            }
        }

        if(safe) {
            applyMin(subtractInt, bounds, transformer, trees, true);
            applyMin(subtractDouble, bounds, transformer, trees, true);
        }

        bounds.clearFilter();
        simplifyTerms();
        return true;
    }

    public boolean minValue(VariableDescription<?> target) {
        Set<TransTreeReturn<?>> trees = varToTree.get(target);
        if(trees == null)
            return false;

        bounds.setFilter(target.name.getName());

        boolean safe = true;
        for(TransTreeReturn<?> tree:trees) {
            if(null == tree.minValue(bounds)) {
                safe = false;
                break;
            }
        }

        if(safe) {
            applyMin(addInt, bounds, transformer, trees, false);
            applyMin(addDouble, bounds, transformer, trees, false);
        }

        safe = true;
        for(TransTreeReturn<?> tree:trees) {
            if(null == tree.maxValue(bounds)) {
                safe = false;
                break;
            }
        }

        if(safe) {
            applyMax(subtractInt, trees, true);
            applyMax(subtractDouble, trees, true);
        }

        bounds.clearFilter();
        simplifyTerms();
        return true;
    }

    public void maxValue() {
        applyMax(addInt, false);
        applyMax(addDouble, false);
        applyMin(subtractInt, true);
        applyMin(subtractDouble, true);
    }

    public void minValue() {
        applyMin(addInt, false);
        applyMin(addDouble, false);
        applyMax(subtractInt, true);
        applyMax(subtractDouble, true);
    }

    private <A extends NumberVariable<A>> void applyMax(List<TransTreeReturn<A>> list, Set<TransTreeReturn<?>> trees,
            boolean negated) {

        Set<TransTreeReturn<A>> toUpdate = new HashSet<>();
        int size = list.size();
        for(int i = 0; i < size;) {
            TransTreeReturn<A> t = list.get(i);
            if(trees.contains(t)) {
                toUpdate.add(t);
                removeTree(list, i);
                size--;
            } else
                i++;
        }

        for(TransTreeReturn<A> t:toUpdate) {
            TransTreeReturn<A> newTree = t.maxValue(bounds);
            bounds.addTransformedTree(t, newTree);
            consumeTree(newTree, negated);
        }
    }

    private <A extends NumberVariable<A>> void applyMin(List<TransTreeReturn<A>> list, Bounds bounds,
            CollapseConstantsTransformer transformer, Set<TransTreeReturn<?>> trees, boolean negated) {

        Set<TransTreeReturn<A>> toUpdate = new HashSet<>();
        int size = list.size();
        for(int i = 0; i < size;) {
            TransTreeReturn<A> t = list.get(i);
            if(trees.contains(t)) {
                toUpdate.add(t);
                removeTree(list, i);
                size--;
            } else
                i++;
        }

        for(TransTreeReturn<A> t:toUpdate) {
            TransTreeReturn<A> newTree = t.minValue(bounds);
            bounds.addTransformedTree(t, newTree);
            consumeTree(newTree, negated);
        }
    }

    private <A extends NumberVariable<A>> void applyMax(List<TransTreeReturn<A>> list, boolean negated) {

        Set<TransTreeReturn<A>> toUpdate = new HashSet<>();
        int size = list.size();
        for(int i = 0; i < size;) {
            TransTreeReturn<A> t = list.get(i);
            toUpdate.add(t);
            removeTree(list, i);
            size--;
        }

        for(TransTreeReturn<A> t:toUpdate) {
            TransTreeReturn<A> lastTree;
            Set<VariableDescription<?>> names = getVariableNames(t);
            Set<VariableDescription<?>> newNames = Collections.emptySet();
            do {
                names.addAll(newNames);
                lastTree = t;
                t = t.maxValue(bounds);
                bounds.addTransformedTree(lastTree, t);

                // Simplify the tree before continuing.
                t = transformer.transformReturn(t);

                newNames = getVariableNames(t);
            } while(!names.containsAll(newNames));

            consumeTree(t, negated);
        }
    }

    private <A extends NumberVariable<A>> void applyMin(List<TransTreeReturn<A>> list, boolean negated) {

        Set<TransTreeReturn<A>> toUpdate = new HashSet<>();
        int size = list.size();
        for(int i = 0; i < size;) {
            TransTreeReturn<A> t = list.get(i);
            toUpdate.add(t);
            removeTree(list, i);
            size--;
        }

        for(TransTreeReturn<A> t:toUpdate) {
            TransTreeReturn<A> lastTree;
            Set<VariableDescription<?>> names = getVariableNames(t);
            Set<VariableDescription<?>> newNames = Collections.emptySet();
            do {
                names.addAll(newNames);
                lastTree = t;
                t = t.minValue(bounds);
                bounds.addTransformedTree(lastTree, t);

                newNames = getVariableNames(t);
            } while(!names.containsAll(newNames));

            consumeTree(t, negated);
        }
    }
}
