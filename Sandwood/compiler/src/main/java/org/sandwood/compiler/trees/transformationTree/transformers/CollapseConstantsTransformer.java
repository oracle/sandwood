/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.transformers;

import static org.sandwood.compiler.trees.transformationTree.TransTree.add;
import static org.sandwood.compiler.trees.transformationTree.TransTree.castToDouble;
import static org.sandwood.compiler.trees.transformationTree.TransTree.castToInteger;
import static org.sandwood.compiler.trees.transformationTree.TransTree.conditionalAssignment;
import static org.sandwood.compiler.trees.transformationTree.TransTree.constant;
import static org.sandwood.compiler.trees.transformationTree.TransTree.divide;
import static org.sandwood.compiler.trees.transformationTree.TransTree.eq;
import static org.sandwood.compiler.trees.transformationTree.TransTree.forStmt;
import static org.sandwood.compiler.trees.transformationTree.TransTree.ifElse;
import static org.sandwood.compiler.trees.transformationTree.TransTree.lessThan;
import static org.sandwood.compiler.trees.transformationTree.TransTree.lessThanEqual;
import static org.sandwood.compiler.trees.transformationTree.TransTree.load;
import static org.sandwood.compiler.trees.transformationTree.TransTree.max;
import static org.sandwood.compiler.trees.transformationTree.TransTree.min;
import static org.sandwood.compiler.trees.transformationTree.TransTree.multiply;
import static org.sandwood.compiler.trees.transformationTree.TransTree.negate;
import static org.sandwood.compiler.trees.transformationTree.TransTree.negateBoolean;
import static org.sandwood.compiler.trees.transformationTree.TransTree.remainder;
import static org.sandwood.compiler.trees.transformationTree.TransTree.subtract;
import static org.sandwood.compiler.trees.transformationTree.TransTree.treeScope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.compiler.compilation.ExternalFunction;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Tag;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.TreeID;
import org.sandwood.compiler.trees.transformationTree.TransCastToDouble;
import org.sandwood.compiler.trees.transformationTree.TransCastToInteger;
import org.sandwood.compiler.trees.transformationTree.TransConditionalAssignment;
import org.sandwood.compiler.trees.transformationTree.TransConstBoolean;
import org.sandwood.compiler.trees.transformationTree.TransConstDouble;
import org.sandwood.compiler.trees.transformationTree.TransConstInt;
import org.sandwood.compiler.trees.transformationTree.TransExternalFunctionCallReturn;
import org.sandwood.compiler.trees.transformationTree.TransFor;
import org.sandwood.compiler.trees.transformationTree.TransIfElse;
import org.sandwood.compiler.trees.transformationTree.TransInitialize;
import org.sandwood.compiler.trees.transformationTree.TransInitializeUnset;
import org.sandwood.compiler.trees.transformationTree.TransLoad;
import org.sandwood.compiler.trees.transformationTree.TransNOP;
import org.sandwood.compiler.trees.transformationTree.TransNegate;
import org.sandwood.compiler.trees.transformationTree.TransNegateBoolean;
import org.sandwood.compiler.trees.transformationTree.TransStore;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTree.TransTreeType;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;
import org.sandwood.compiler.trees.transformationTree.binop.TransAdd;
import org.sandwood.compiler.trees.transformationTree.binop.TransAnd;
import org.sandwood.compiler.trees.transformationTree.binop.TransBinOp;
import org.sandwood.compiler.trees.transformationTree.binop.TransDivide;
import org.sandwood.compiler.trees.transformationTree.binop.TransEq;
import org.sandwood.compiler.trees.transformationTree.binop.TransLessThan;
import org.sandwood.compiler.trees.transformationTree.binop.TransLessThanEqual;
import org.sandwood.compiler.trees.transformationTree.binop.TransMax;
import org.sandwood.compiler.trees.transformationTree.binop.TransMin;
import org.sandwood.compiler.trees.transformationTree.binop.TransMultiply;
import org.sandwood.compiler.trees.transformationTree.binop.TransOr;
import org.sandwood.compiler.trees.transformationTree.binop.TransRemainder;
import org.sandwood.compiler.trees.transformationTree.binop.TransSubtract;
import org.sandwood.compiler.trees.transformationTree.util.AddSubtractTrees;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;
import org.sandwood.compiler.trees.transformationTree.util.KnownValuesTrans;
import org.sandwood.compiler.trees.transformationTree.util.KnownValuesTrans.KnownValue;
import org.sandwood.compiler.trees.transformationTree.util.RearrangeTree;
import org.sandwood.compiler.trees.transformationTree.util.WrappedTransReturn;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.ScopedVarSet;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VarDef;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VariableTracking;
import org.sandwood.runtime.internal.numericTools.Gaussian;

public class CollapseConstantsTransformer extends Transformer {
    private class GuardBounds {
        private static class MinMax {
            TransTreeReturn<IntVariable> min = null, max = null;
        }

        private final Map<VariableDescription<?>, MinMax> ifBounds = new HashMap<>();
        private final Map<VariableDescription<?>, MinMax> elseBounds = new HashMap<>();
        private final ScopedVarSet inScope;

        GuardBounds(TransTreeReturn<BooleanVariable> guard) {
            inScope = bounds.inScopeVars(guard);
            construct(guard, false, true, true);
            updateBoundsVars(guard, ifBounds.values());
            updateBoundsVars(guard, elseBounds.values());
        }

        // TODO extend this to work with doubles
        private void construct(TransTreeReturn<BooleanVariable> guard, boolean negated, boolean ifPossible,
                boolean elsePossible) {
            switch(guard.type) {
                case AND: {
                    TransAnd and = (TransAnd) guard;
                    if(negated) {
                        for(TransTreeReturn<BooleanVariable> tree:and.getConstraints())
                            construct(tree, negated, false, elsePossible);
                    } else {
                        for(TransTreeReturn<BooleanVariable> tree:and.getConstraints())
                            construct(tree, negated, ifPossible, false);
                    }
                    break;
                }
                case LESS_THAN: {
                    TransLessThan<?, ?> lt = (TransLessThan<?, ?>) guard;
                    if(lt.left.getOutputType() == VariableType.IntVariable
                            && lt.right.getOutputType() == VariableType.IntVariable) {
                        if(lt.left.type == TransTreeType.LOAD) {
                            TransLoad<IntVariable> left = (TransLoad<IntVariable>) lt.left;
                            TransTreeReturn<IntVariable> right = (TransTreeReturn<IntVariable>) lt.right;
                            if(negated) {
                                if(ifPossible)
                                    addLowerBound(left, right, ifBounds, true);
                                if(elsePossible)
                                    addUpperBound(left, right, elseBounds, false);
                            } else {
                                if(ifPossible)
                                    addUpperBound(left, right, ifBounds, false);
                                if(elsePossible)
                                    addLowerBound(left, right, elseBounds, true);
                            }
                        }
                        if(lt.right.type == TransTreeType.LOAD) {
                            TransTreeReturn<IntVariable> left = (TransTreeReturn<IntVariable>) lt.left;
                            TransLoad<IntVariable> right = (TransLoad<IntVariable>) lt.right;
                            if(negated) {
                                if(ifPossible)
                                    addUpperBound(right, left, ifBounds, true);
                                if(elsePossible)
                                    addLowerBound(right, left, elseBounds, false);
                            } else {
                                if(ifPossible)
                                    addLowerBound(right, left, ifBounds, false);
                                if(elsePossible)
                                    addUpperBound(right, left, elseBounds, true);
                            }
                        }
                        /*
                         * TODO add in the ability to rearrange values here so that other bounds can be constructed.
                         * This can use the same code as is used for for loops, but will require the additional handling
                         * of inequalities described in the rearrangement code.
                         */
                    }
                    break;
                }
                case LESS_THAN_EQUAL: {
                    TransLessThanEqual<?, ?> lte = (TransLessThanEqual<?, ?>) guard;
                    if(lte.left.getOutputType() == VariableType.IntVariable
                            && lte.right.getOutputType() == VariableType.IntVariable) {
                        if(lte.left.type == TransTreeType.LOAD) {
                            TransLoad<IntVariable> left = (TransLoad<IntVariable>) lte.left;
                            TransTreeReturn<IntVariable> right = (TransTreeReturn<IntVariable>) lte.right;
                            if(negated) {
                                if(ifPossible)
                                    addLowerBound(left, right, ifBounds, false);
                                if(elsePossible)
                                    addUpperBound(left, right, elseBounds, true);
                            } else {
                                if(ifPossible)
                                    addUpperBound(left, right, ifBounds, true);
                                if(elsePossible)
                                    addLowerBound(left, right, elseBounds, false);
                            }
                        }
                        if(lte.right.type == TransTreeType.LOAD) {
                            TransTreeReturn<IntVariable> left = (TransTreeReturn<IntVariable>) lte.left;
                            TransLoad<IntVariable> right = (TransLoad<IntVariable>) lte.right;
                            if(negated) {
                                if(ifPossible)
                                    addUpperBound(right, left, ifBounds, false);
                                if(elsePossible)
                                    addLowerBound(right, left, elseBounds, true);
                            } else {
                                if(ifPossible)
                                    addLowerBound(right, left, ifBounds, true);
                                if(elsePossible)
                                    addUpperBound(right, left, elseBounds, false);
                            }
                        }
                        /*
                         * TODO add in the ability to rearrange values here so that other bounds can be constructed.
                         * This can use the same code as is used for for loops, but will require the additional handling
                         * of inequalities described in the rearrangement code.
                         */
                    }
                    break;
                }
                case NEGATE_BOOLEAN: {
                    TransNegateBoolean t = (TransNegateBoolean) guard;
                    construct(t.input, !negated, ifPossible, elsePossible);
                    break;
                }
                case OR: {
                    TransOr or = (TransOr) guard;
                    if(negated) {
                        for(TransTreeReturn<BooleanVariable> tree:or.getConstraints())
                            construct(tree, negated, ifPossible, false);
                    } else {
                        for(TransTreeReturn<BooleanVariable> tree:or.getConstraints())
                            construct(tree, negated, false, elsePossible);
                    }
                    break;
                }
                default:
                    break;
            }
        }

        private void addUpperBound(TransLoad<IntVariable> left, TransTreeReturn<IntVariable> right,
                Map<VariableDescription<?>, MinMax> boundMap, boolean includeEquals) {
            MinMax m = boundMap.get(left.varDesc);
            if(m == null) {
                m = new MinMax();
                boundMap.put(left.varDesc, m);
            }

            TransTreeReturn<IntVariable> rMax = right.maxValue(bounds);
            if(rMax != null) {
                // Move the bound as the values cannot be equal
                if(!includeEquals)
                    rMax = TransTree.subtractII(rMax, TransTree.constant(1));
                if(m.max != null)
                    m.max = TransTree.min(m.max, rMax);
                else
                    m.max = rMax;
                bounds.addTransformedTree(right, m.max);
            }
        }

        private void addLowerBound(TransLoad<IntVariable> left, TransTreeReturn<IntVariable> right,
                Map<VariableDescription<?>, MinMax> boundMap, boolean includeEquals) {
            MinMax m = boundMap.computeIfAbsent(left.varDesc, k -> new MinMax());
            TransTreeReturn<IntVariable> rMin = right.minValue(bounds);
            if(rMin != null) {
                // Move the bound as the values cannot be equal;
                if(!includeEquals)
                    rMin = TransTree.addII(rMin, TransTree.constant(1));
                if(m.min != null)
                    m.min = TransTree.max(m.min, rMin);
                else
                    m.min = rMin;
                bounds.addTransformedTree(right, m.min);
            }
        }

        private void updateBoundsVars(TransTreeReturn<BooleanVariable> guard, Collection<MinMax> trees) {
            for(MinMax minMax:trees) {
                if(minMax.min != null)
                    bounds.addTransformedTree(guard, minMax.min);
                if(minMax.max != null)
                    bounds.addTransformedTree(guard, minMax.max);
            }
        }

        public void applyIfBounds() {
            applyBounds(ifBounds);
        }

        public void removeIfBounds() {
            removeBounds(ifBounds);
        }

        public void applyElseBounds() {
            applyBounds(elseBounds);
        }

        public void removeElseBounds() {
            removeBounds(elseBounds);
        }

        private void applyBounds(Map<VariableDescription<?>, MinMax> boundsMap) {
            for(VariableDescription<?> desc:boundsMap.keySet()) {
                VarDef v = inScope.getVarDef(desc);
                MinMax minMax = boundsMap.get(desc);
                for(TreeID tag:v.writeLocations())
                    bounds.addRange(desc, tag, minMax.min, minMax.max);
            }
        }

        private void removeBounds(Map<VariableDescription<?>, MinMax> boundsMap) {
            for(VariableDescription<?> desc:boundsMap.keySet()) {
                VarDef v = inScope.getVarDef(desc);
                for(TreeID tag:v.writeLocations())
                    bounds.removeRange(desc, tag);
            }
        }
    }

    private static class UpdatedVars {
        record UpdatedVar(VariableDescription<?> desc, boolean declaration, TreeID id) {}

        private final List<UpdatedVar> updatedVars = new ArrayList<>();
        private final Bounds bounds;

        public UpdatedVars(Bounds bounds) {
            this.bounds = bounds;
        }

        public void add(VariableDescription<?> desc, boolean declaration) {
            updatedVars.add(new UpdatedVar(desc, declaration, null));
        }

        public void add(VariableDescription<?> desc, boolean declaration, TreeID id, TransTreeReturn<IntVariable> min,
                TransTreeReturn<IntVariable> max) {
            bounds.addRange(desc, id, min, max);
            updatedVars.add(new UpdatedVar(desc, declaration, id));
        }

        public int size() {
            return updatedVars.size();
        }

        public void remove(int stopPoint, TransTreeVoid parent) {
            int numToRemove = size() - stopPoint;
            for(int i = numToRemove - 1; i >= 0; i--) {
                int toRemove = stopPoint + i;
                UpdatedVar v = updatedVars.get(toRemove);
                if(v.declaration) {
                    if(v.id != null)
                        bounds.removeRange(v.desc, v.id);
                    updatedVars.remove(toRemove);
                } else {
                    Set<VariableDescription<?>> requiredVariables = bounds.requiredMinMaxVars(v.desc, v.id);
                    ScopedVarSet inScopeVars = bounds.inScopeVars(parent);
                    // Checking all the required variables are still in scope
                    for(VariableDescription<?> r:requiredVariables) {
                        if(!inScopeVars.containsVar(r)) {
                            bounds.removeRange(v.desc, v.id);
                            updatedVars.remove(toRemove);
                            break;
                        }
                    }
                }
            }
        }

        public VariableDescription<?> get(int i) {
            return updatedVars.get(i).desc;
        }
    }

    private static class TreeTracker {
        List<TransTreeReturn<?>> trees = new ArrayList<>();

        public boolean contains(TransTreeReturn<?> tree) {
            for(TransTreeReturn<?> t:trees) {
                if(tree.containsEquivalent(t))
                    return true;
            }
            return false;
        }

        public void add(TransTreeReturn<?> tree) {
            trees.add(tree);
        }

        public void remove() {
            trees.remove(trees.size() - 1);
        }

    }

    private final Bounds bounds;
    private final UpdatedVars updatedVars;
    private int embeddedDepth = 0;

    // A set to track all the trees see so that it is not possible to enter an infinite loop.
    private final TreeTracker seen = new TreeTracker();

    public CollapseConstantsTransformer(ArgDesc<?>[] args, KnownValuesTrans knownValues, TransTree<?> tree) {
        bounds = new Bounds(args, knownValues, tree);
        updatedVars = new UpdatedVars(bounds);
        for(ArgDesc<?> arg:args)
            updatedVars.add(arg.varDesc, true);

        if(!knownValues.isEmpty()) {
            // Create a visited nodes set for the transformations performed on the known values
            visitedNodes = new HashSet<>();
            // Apply any known values from the calling function.
            for(KnownValue v:knownValues) {
                GuardBounds gb = new GuardBounds(v.expr);
                /*
                 * Apply the optimisation to the known values in case this changes the value. This is deliberately done
                 * after the guard substitutions is created to prevent the read vars being effected, but will be used on
                 * the next iteration
                 */
                v.expr = transform(v.expr);
                if(v.value)
                    gb.applyIfBounds();
                else
                    gb.applyElseBounds();
            }
        }
    }

    @Override
    public TransTreeVoid transformVoid(TransTreeVoid tree) {
        switch(tree.type) {
            case FOR:
                visitedNodes.add(tree);
                return collapseConstants((TransFor) tree);

            case IF:
                visitedNodes.add(tree);
                return collapseConstants((TransIfElse) tree);

            case SCOPE: {
                int size = updatedVars.size();
                TransTreeVoid toReturn = tree.applyTransformation(this);
                updatedVars.remove(size, tree);
                return toReturn;
            }

            case INITIALIZE:
                return collapseConstants((TransInitialize<?>) tree);

            case INITIALIZE_UNSET:
                updatedVars.add(((TransInitializeUnset<?>) tree).varDesc, true);
                return tree.applyTransformation(this);

            case STORE:
                return collapseConstants((TransStore<?>) tree);

            case ARRAY_PUT: // TODO Add code to add the constraint that the index is positive so 0 can be a lower bound.
            case LOCAL_FUNCTION_CALL:
            case RV_FUNCTION_CALL:
            case NOP:
            case SEQUENTIAL:
                return tree.applyTransformation(this);

            default:
                throw new CompilerException("Unknown tree type " + tree.type + " encountered");
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public <X extends Variable<X>> TransTreeReturn<X> transformReturn(TransTreeReturn<X> tree) {
        TransTreeReturn<X> toReturn = null;

        // Check we are not in a loop.
        if(!seen.contains(tree)) {
            seen.add(tree);

            switch(tree.type) {
                case LESS_THAN:
                case LESS_THAN_EQUAL:
                    visitedNodes.add(tree);
                    toReturn = collapseConstants((TransBinOp) tree);
                    break;
                case EQUALITY:
                    TransEq<?, ?> eq = (TransEq<?, ?>) tree;
                    if(eq.left.getOutputType() == VariableType.BooleanVariable) {
                        if(eq.right.getOutputType() == VariableType.BooleanVariable)
                            toReturn = collapseBooleanEq((TransEq<BooleanVariable, BooleanVariable>) eq);
                        else
                            toReturn = tree.applyTransformation(this);
                    } else {
                        if(eq.right.getOutputType() == VariableType.BooleanVariable)
                            toReturn = tree.applyTransformation(this);
                        else
                            toReturn = collapseConstants(
                                    (TransEq<? extends NumberVariable, ? extends NumberVariable>) eq);
                    }
                    break;

                case EXTERNAL_FUNCTION_CALL_RETURN:
                    visitedNodes.add(tree);
                    toReturn = collapseConstants((TransExternalFunctionCallReturn<X>) tree);
                    break;

                case MAX:
                    visitedNodes.add(tree);
                    toReturn = (TransTreeReturn<X>) collapseConstants((TransMax<?>) tree);
                    break;

                case MIN:
                    visitedNodes.add(tree);
                    toReturn = (TransTreeReturn<X>) collapseConstants((TransMin<?>) tree);
                    break;

                case DIVIDE:
                    visitedNodes.add(tree);
                    toReturn = (TransTreeReturn<X>) collapseConstants((TransDivide<?, ?, ?>) tree);
                    break;

                case REMAINDER:
                    visitedNodes.add(tree);
                    toReturn = (TransTreeReturn<X>) collapseConstants((TransRemainder<?, ?, ?>) tree);
                    break;

                case ADD:
                    visitedNodes.add(tree);
                    toReturn = (TransTreeReturn<X>) collapseConstants((TransAdd<?, ?, ?>) tree);
                    break;

                case SUBTRACT:
                    visitedNodes.add(tree);
                    toReturn = (TransTreeReturn<X>) collapseConstants((TransSubtract<?, ?, ?>) tree);
                    break;

                case MULTIPLY:
                    visitedNodes.add(tree);
                    toReturn = (TransTreeReturn<X>) collapseConstants((TransMultiply<?, ?, ?>) tree);
                    break;

                case NEGATE_BOOLEAN:
                    visitedNodes.add(tree);
                    toReturn = (TransTreeReturn<X>) collapseConstants((TransNegateBoolean) tree);
                    break;

                case NEGATE:
                    visitedNodes.add(tree);
                    toReturn = (TransTreeReturn<X>) collapseConstants((TransNegate<?>) tree);
                    break;

                case CAST_DOUBLE:
                    visitedNodes.add(tree);
                    toReturn = (TransTreeReturn<X>) collapseConstants((TransCastToDouble) tree);
                    break;

                case CAST_INT:
                    visitedNodes.add(tree);
                    toReturn = (TransTreeReturn<X>) collapseConstants((TransCastToInteger) tree);
                    break;

                case CONDITIONAL_ASSIGNMENT:
                    visitedNodes.add(tree);
                    toReturn = collapseConstants((TransConditionalAssignment) tree);
                    break;

                case ALLOCATE_ARRAY: // TODO Add code to add the constraint that the index is positive so 0 can be a
                                     // lower bound.
                case AND: // Constants are collapsed at construction time
                case ARRAY_GET: // TODO Add code to add the constraint the that index is positive so 0 can be a lower
                                // bound.
                case CONST_BOOLEAN:
                case CONST_DOUBLE:
                case CONST_INT:
                case LOCAL_FUNCTION_CALL_RETURN:
                case RV_FUNCTION_CALL_RETURN:
                case GET_FIELD:
                case LOAD:
                case OR: // Constants are collapsed at construction time
                    toReturn = tree.applyTransformation(this);
                    break;

                default:
                    throw new CompilerException("Unknown tree type " + tree.type + " encountered");
            }

            seen.remove();
        }

        if(toReturn != null)
            bounds.addTransformedTree(tree, toReturn);
        else
            toReturn = tree;

        return toReturn;
    }

    private <X extends Variable<X>> TransTreeReturn<X> collapseConstants(TransExternalFunctionCallReturn<X> tree) {
        ExternalFunction f = tree.func;
        switch(f) {
            case EXP: {
                TransTreeReturn<X> value = transform((TransTreeReturn<X>) tree.args[0]);
                switch(value.type) {
                    case CONST_DOUBLE:
                        return (TransTreeReturn<X>) constant(Math.exp(((TransConstDouble) value).value));
                    case CONST_INT:
                        return (TransTreeReturn<X>) constant(Math.exp(((TransConstInt) value).value));
                    case EXTERNAL_FUNCTION_CALL_RETURN: {
                        TransExternalFunctionCallReturn<X> arg = (TransExternalFunctionCallReturn<X>) value;
                        if(arg.func == ExternalFunction.LOG) {
                            if(arg.args[0].getOutputType() == VariableType.DoubleVariable)
                                return (TransTreeReturn<X>) arg.args[0];
                            else
                                return (TransTreeReturn<X>) castToDouble((TransTreeReturn<IntVariable>) arg.args[0]);
                        }
                        break;
                    }
                    default:
                        break;
                }
                return TransTree.functionCallReturn(f, tree.outputType, value);
            }
            case GAUSSIAN_CDF: {
                TransTreeReturn<DoubleVariable> value = transform((TransTreeReturn<DoubleVariable>) tree.args[0]);
                if(value.type == TransTreeType.CONST_DOUBLE)
                    return (TransTreeReturn<X>) constant(Gaussian.cdf(((TransConstDouble) value).value));
                else
                    return TransTree.functionCallReturn(f, tree.outputType, value);
            }
            case IS_NAN: {
                TransTreeReturn<DoubleVariable> value = (TransTreeReturn<DoubleVariable>) transform(tree.args[0]);
                if(value.type == TransTreeType.CONST_DOUBLE)
                    return (TransTreeReturn<X>) constant(Double.isNaN(((TransConstDouble) value).value));
                else
                    return TransTree.functionCallReturn(f, tree.outputType, value);
            }
            case LOG: {
                TransTreeReturn<X> value = (TransTreeReturn<X>) transform(tree.args[0]);
                switch(value.type) {
                    case CONST_DOUBLE:
                        return (TransTreeReturn<X>) constant(Math.log(((TransConstDouble) value).value));
                    case CONST_INT:
                        return (TransTreeReturn<X>) constant(Math.log(((TransConstInt) value).value));
                    case EXTERNAL_FUNCTION_CALL_RETURN: {
                        TransExternalFunctionCallReturn<X> arg = (TransExternalFunctionCallReturn<X>) value;
                        if(arg.func == ExternalFunction.EXP) {
                            if(arg.args[0].getOutputType() == VariableType.DoubleVariable)
                                return (TransTreeReturn<X>) arg.args[0];
                            else
                                return (TransTreeReturn<X>) castToDouble((TransTreeReturn<IntVariable>) arg.args[0]);
                        }
                        break;
                    }
                    default:
                        break;
                }
                return TransTree.functionCallReturn(f, tree.outputType, value);
            }
            case SQRT: {
                TransTreeReturn<X> value = (TransTreeReturn<X>) transform(tree.args[0]);
                switch(value.type) {
                    case CONST_DOUBLE:
                        return (TransTreeReturn<X>) constant(Math.sqrt(((TransConstDouble) value).value));
                    case CONST_INT:
                        return (TransTreeReturn<X>) constant(Math.sqrt(((TransConstInt) value).value));
                    case MULTIPLY: {
                        TransMultiply<?, ?, ?> arg = (TransMultiply<?, ?, ?>) value;
                        if(arg.left.equivalent(arg.right)) {
                            if(arg.left.getOutputType() == VariableType.DoubleVariable)
                                return (TransTreeReturn<X>) arg.left;
                            else
                                return (TransTreeReturn<X>) castToDouble((TransTreeReturn<IntVariable>) arg.left);
                        }
                        break;
                    }
                    default:
                        break;
                }
                return TransTree.functionCallReturn(f, tree.outputType, value);
            }
            default: {
                throw new SandwoodException("Unknown Function type " + f);
            }
        }
    }

    private <X extends Variable<X>> TransTreeReturn<X> collapseBooleanEq(TransEq<BooleanVariable, BooleanVariable> eq) {
        visitedNodes.add(eq);
        TransTreeReturn<BooleanVariable> left = transform(eq.left);
        TransTreeReturn<BooleanVariable> right = transform(eq.right);

        if(left.type == TransTreeType.CONST_BOOLEAN) {
            TransConstBoolean lb = (TransConstBoolean) left;
            if(lb.value)
                return (TransTreeReturn<X>) right;
            else
                return (TransTreeReturn<X>) negateBoolean(right);
        }

        if(right.type == TransTreeType.CONST_BOOLEAN) {
            TransConstBoolean rb = (TransConstBoolean) right;
            if(rb.value)
                return (TransTreeReturn<X>) left;
            else
                return (TransTreeReturn<X>) negateBoolean(left);
        }

        return (TransTreeReturn<X>) TransTree.eq(left, right);
    }

    private TransTreeVoid collapseConstants(TransInitialize<?> tree) {
        TransTreeVoid toReturn = tree.applyTransformation(this);
        if(tree.varDesc.type == VariableType.IntVariable)
            updatedVars.add(tree.varDesc, true, tree.id, (TransTreeReturn<IntVariable>) tree.value.minValue(bounds),
                    (TransTreeReturn<IntVariable>) tree.value.maxValue(bounds));
        else
            updatedVars.add(tree.varDesc, true);
        return toReturn;
    }

    private TransTreeVoid collapseConstants(TransStore<?> tree) {
        TransTreeVoid toReturn = tree.applyTransformation(this);
        if(tree.varDesc.type == VariableType.IntVariable)
            updatedVars.add(tree.varDesc, false, tree.id, (TransTreeReturn<IntVariable>) tree.value.minValue(bounds),
                    (TransTreeReturn<IntVariable>) tree.value.maxValue(bounds));
        else
            updatedVars.add(tree.varDesc, false);
        return toReturn;
    }

    private TransTreeReturn<DoubleVariable> collapseConstants(TransCastToDouble tree) {
        TransTreeReturn<IntVariable> newInput = transform(tree.input);
        if(newInput.type == TransTreeType.CONST_INT)
            return constant((double) ((TransConstInt) newInput).value);
        else
            return castToDouble(newInput);
    }

    private TransTreeReturn<IntVariable> collapseConstants(TransCastToInteger tree) {
        TransTreeReturn<DoubleVariable> newInput = transform(tree.input);
        if(newInput.type == TransTreeType.CONST_DOUBLE)
            return constant((int) ((TransConstDouble) newInput).value);
        else
            return castToInteger(newInput);
    }

    private <A extends NumberVariable<A>, B extends NumberVariable<B>> TransTreeReturn<BooleanVariable> collapseConstants(
            TransBinOp<A, B, BooleanVariable> tree) {
        visitedNodes.add(tree);

        // Collapse the inputs
        TransTreeReturn<A> newLeft = transform(tree.left);
        TransTreeReturn<B> newRight = transform(tree.right);

        AddSubtractTrees<A> leftTrees = new AddSubtractTrees<>(tree.left.getOutputType(), this, bounds, newLeft);
        leftTrees.consumeTree(newLeft);

        AddSubtractTrees<B> rightTrees = new AddSubtractTrees<>(tree.right.getOutputType(), this, bounds, newRight);
        rightTrees.consumeTree(newRight);

        leftTrees.cancelTerms(rightTrees);

        switch(tree.type) {
            case EQUALITY:
                return collapseConstantsEquals(leftTrees, rightTrees);
            case LESS_THAN:
                return collapseConstantsLessThan(leftTrees, rightTrees);
            case LESS_THAN_EQUAL:
                return collapseConstantsLessThanEqual(leftTrees, rightTrees);
            default:
                throw new CompilerException("Unknown tree type " + tree.type + " encountered");
        }
    }

    private <A extends NumberVariable<A>, B extends NumberVariable<B>> TransTreeReturn<BooleanVariable> collapseConstantsEquals(
            AddSubtractTrees<A> left, AddSubtractTrees<B> right) {
        if(left.isConstant() && right.isConstant()) {
            return TransTree.constant((left.outputType == VariableType.IntVariable ? left.getIntConstant()
                    : left.getDoubleConstant()) == (right.outputType == VariableType.IntVariable
                            ? right.getIntConstant()
                            : right.getDoubleConstant()));
        }
        // Check if bounds mean that this is always false.
        {
            TransTreeReturn<BooleanVariable> testTree = collapseConstantsLessThan(left, right);
            if(testTree.type == TransTreeType.CONST_BOOLEAN && ((TransConstBoolean) testTree).value)
                return TransTree.constant(false); // max value on the left is less than the min on the right, then this
            // is always true.
        }

        {
            TransTreeReturn<BooleanVariable> testTree = collapseConstantsLessThan(right, left);
            if(testTree.type == TransTreeType.CONST_BOOLEAN && ((TransConstBoolean) testTree).value)
                return TransTree.constant(false); // min value on the left is more than the max on the right, then this
            // is always false.
        }

        left.mergeConstants(right);
        return TransTree.eq(left.getTree(), right.getTree());
        // In generate nodes in the tree should never appear twice.
    }

    private VariableDescription<?> getTargetDesc(TransTreeReturn<?> t) {
        VariableTracking v = t.getVariableTracking();
        Set<VariableDescription<?>> readVars = v.readVars(t).getVars();
        int size = updatedVars.size();
        for(int i = size - 1; i > 0; i--) {
            VariableDescription<?> desc = updatedVars.get(i);
            if(readVars.contains(desc))
                return desc;
        }

        if(readVars.isEmpty())
            return null;

        // if we are using multiple global labels and no local labels pick the first
        // alphabetically. Filtering out lengths as we already have some information
        // on them unless there are no other variables.
        PriorityQueue<VariableDescription<?>> p = new PriorityQueue<>(readVars);
        VariableDescription<?> first = p.poll();
        VariableDescription<?> current = first;
        while(!p.isEmpty() && VariableNames.isLengthName(current.name))
            current = p.poll();
        if(!VariableNames.isLengthName(current.name))
            return current;
        return first;
    }

    private TransTreeVoid collapseConstants(TransFor tf) {
        if(tf.body == TransNOP.nop) {
            tf.addNodes(visitedNodes);
            return TransTree.nop();
        } else {
            TransTreeReturn<IntVariable> min;
            TransTreeReturn<IntVariable> max;
            if(tf.incrementing) {
                min = transform(tf.start);
                max = transform(tf.end);
            } else {
                min = transform(tf.end);
                max = transform(tf.start);
            }

            // Test is it is possible to execute this loop.
            TransTreeReturn<BooleanVariable> validRange = lessThanEqual(min, max);
            bounds.addTransformedTree(tf.end, validRange);
            validRange = transform(validRange);
            if(validRange.type == TransTreeType.CONST_BOOLEAN && !((TransConstBoolean) validRange).value) {
                tf.addNodes(visitedNodes);
                return TransTree.nop();
            }

            TransTreeReturn<IntVariable> transStep = transform(tf.step);

            // Add the bounds and declared index
            int size = updatedVars.size();
            updatedVars.add(tf.indexDesc, true, tf.id, min, max);

            // If possible add bound to the start and end values of the for loop.
            VariableDescription<IntVariable> minDesc = (VariableDescription<IntVariable>) getTargetDesc(min);
            if(minDesc != null) {
                TransTreeReturn<IntVariable> startEndMax = RearrangeTree.rearrangeTree(eq(min, max), minDesc,
                        bounds.gcds);
                TransTreeReturn<IntVariable> startIndexMax = RearrangeTree.rearrangeTree(eq(min, load(tf.indexDesc)),
                        minDesc, bounds.gcds);
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
                    bounds.addTransformedTree(tf.end, startMax);
                    VarDef v = bounds.inScopeVars(min).getVarDef(minDesc);
                    for(TreeID startTag:v.writeLocations())
                        bounds.addRange(minDesc, startTag, null, startMax);
                } else
                    minDesc = null;
            }

            VariableDescription<IntVariable> maxName = (VariableDescription<IntVariable>) getTargetDesc(max);
            if(maxName != null) {
                TransTreeReturn<IntVariable> endStartMin = RearrangeTree.rearrangeTree(eq(min, max), maxName,
                        bounds.gcds);
                TransTreeReturn<IntVariable> endIndexMin = RearrangeTree.rearrangeTree(eq(max, load(tf.indexDesc)),
                        maxName, bounds.gcds);
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
                    // Using the read set for the end value as the index maybe in used in this value.
                    bounds.addTransformedTree(tf.end, endMin);
                    VarDef v = bounds.inScopeVars(max).getVarDef(maxName);
                    for(TreeID endTag:v.writeLocations())
                        bounds.addRange(maxName, endTag, endMin, null);
                } else
                    maxName = null;
            }

            // Process the loop
            TransTreeVoid transBody = transform(tf.body);
            TransTreeVoid toReturn;
            if(tf.incrementing)
                toReturn = forStmt(transBody, min, max, transStep, tf.indexDesc, tf.parallel, true, tf.getComment());
            else
                toReturn = forStmt(transBody, max, min, transStep, tf.indexDesc, tf.parallel, false, tf.getComment());

            // Remove the bounds and any values declared in the scope of the for loop.
            updatedVars.remove(size, tf);

            if(minDesc != null) {
                VarDef v = bounds.inScopeVars(min).getVarDef(minDesc);
                for(TreeID startTag:v.writeLocations())
                    bounds.removeRange(minDesc, startTag);
            }

            if(maxName != null) {
                VarDef v = bounds.inScopeVars(max).getVarDef(maxName);
                for(TreeID endTag:v.writeLocations())
                    bounds.removeRange(maxName, endTag);
            }

            return toReturn;
        }
    }

    private TransTreeVoid collapseConstants(TransIfElse t) {
        TransTreeReturn<BooleanVariable> transCondition = transform(t.condition);
        if(transCondition.type != TransTreeType.CONST_BOOLEAN) {
            if(t.ifBody.equivalent(t.elseBody)) {
                t.elseBody.addNodes(visitedNodes);
                return treeScope(transform(t.ifBody), Tree.NoComment);
            } else {
                bounds.addTransformedTree(t.condition, transCondition);
                GuardBounds gb = new GuardBounds(transCondition);
                // Determine how many variables have been declared.
                int size = updatedVars.size();
                gb.applyIfBounds();
                TransTreeVoid ifBody = transform(t.ifBody);
                gb.removeIfBounds();
                // Remove any declared variables.
                updatedVars.remove(size, t);

                gb.applyElseBounds();
                TransTreeVoid elseBody = transform(t.elseBody);
                gb.removeElseBounds();
                // Remove any declared variables.
                updatedVars.remove(size, t);
                // Construct a new tree.
                return lseRule(ifElse(transCondition, ifBody, t.getComment(), elseBody, t.getElseComment(), t.tags()));
            }
        } else {
            if(((TransConstBoolean) transCondition).value) {
                // Determine how many variables have been declared.
                int size = updatedVars.size();
                TransTreeVoid ifBody = transform(t.ifBody);
                // Remove any declared variables.
                updatedVars.remove(size, t);
                t.elseBody.addNodes(visitedNodes);
                return treeScope(ifBody, Tree.NoComment);
            } else {
                // Determine how many variables have been declared.
                int size = updatedVars.size();
                TransTreeVoid elseBody = transform(t.elseBody);
                // Remove any declared variables.
                updatedVars.remove(size, t);
                t.ifBody.addNodes(visitedNodes);
                return treeScope(elseBody, Tree.NoComment);
            }
        }
    }

    private <X extends Variable<X>> TransTreeReturn<X> collapseConstants(TransConditionalAssignment<X> t) {
        TransTreeReturn<BooleanVariable> transCondition = transform(t.condition);
        if(transCondition.type != TransTreeType.CONST_BOOLEAN) {
            if(t.ifValue.equivalent(t.elseValue)) {
                t.elseValue.addNodes(visitedNodes);
                return transform(t.ifValue);
            } else {
                bounds.addTransformedTree(t.condition, transCondition);
                GuardBounds gb = new GuardBounds(transCondition);
                gb.applyIfBounds();
                TransTreeReturn<X> ifValue = transform(t.ifValue);
                gb.removeIfBounds();

                gb.applyElseBounds();
                TransTreeReturn<X> elseValue = transform(t.elseValue);
                gb.removeElseBounds();
                // Construct a new tree.
                TransConditionalAssignment<X> newTree = conditionalAssignment(transCondition, ifValue, elseValue,
                        t.tags());
                bounds.addTransformedTree(t, newTree);
                return newTree;
            }
        } else {
            if(((TransConstBoolean) transCondition).value) {
                t.elseValue.addNodes(visitedNodes);
                return transform(t.ifValue);
            } else {
                t.ifValue.addNodes(visitedNodes);
                return transform(t.elseValue);
            }
        }
    }

    /**
     * A rule to apply optimisations to LSE trees.
     * 
     * @param tree The tree to check if it is an LSE that can be optimised.
     * @return The resulting tree.
     */
    private TransTreeVoid lseRule(TransTreeVoid tree) {
        switch(tree.type) {
            case IF:
                TransIfElse ifElse = (TransIfElse) tree;
                if(!ifElse.containsTag(Tag.LSE))
                    return ifElse;

                switch(ifElse.condition.type) {
                    case LESS_THAN:
                        TransTreeReturn<?> left = ((TransLessThan<?, ?>) ifElse.condition).left;
                        if(left.type == TransTreeType.CONST_DOUBLE)
                            if(((TransConstDouble) left).value == Double.NEGATIVE_INFINITY)
                                return ifElse.ifBody;
                        return ifElse;

                    default:
                        throw new CompilerException("Unexpected guard for an LSE calculation.");
                }

            default:
                return tree;

        }
    }

    private <A extends NumberVariable<A>> TransTreeReturn<A> collapseConstants(TransMax<A> tree) {
        // Gather and transform all the terms.
        Stack<TransTreeReturn<A>> toProcess = new Stack<>();
        toProcess.push(tree.right);
        toProcess.push(tree.left);

        Set<WrappedTransReturn<A>> seen = new HashSet<>();
        List<TransTreeReturn<A>> processed = new ArrayList<>();

        while(!toProcess.isEmpty()) {
            TransTreeReturn<A> t = toProcess.pop();
            switch(t.type) {
                case MAX:
                    TransMax<A> m = (TransMax<A>) t;
                    this.visitedNodes.add(m);
                    toProcess.push(m.right);
                    toProcess.push(m.left);
                    break;
                default:
                    WrappedTransReturn<A> w = new WrappedTransReturn<>(t);
                    if(!seen.contains(w)) {
                        seen.add(w);
                        processed.add(transform(t));
                    } else
                        t.addNodes(visitedNodes);
                    break;
            }
        }

        // Filter out terms that are know to be less than other terms.
        int noTerms = processed.size();
        for(int i = 0; i < noTerms; i++) {
            TransTreeReturn<A> left = processed.get(i);
            boolean removed = false;
            for(int j = 0; j < i && !removed; j++) {
                TransTreeReturn<A> right = processed.get(j);
                TransTreeReturn<BooleanVariable> test = lessThanEqual(left, right);
                bounds.addTransformedTree(tree, test);
                test = transform(test);
                switch(test.type) {
                    case CONST_BOOLEAN:
                        if(((TransConstBoolean) test).value) {
                            processed.remove(i);
                            i--;
                            noTerms--;
                            removed = true;
                        }
                        break;
                    default:
                        break;
                }
            }

            for(int j = i + 1; j < noTerms && !removed; j++) {
                TransTreeReturn<A> right = processed.get(j);
                TransTreeReturn<BooleanVariable> test = lessThanEqual(left, right);
                bounds.addTransformedTree(tree, test);
                test = transform(test);
                switch(test.type) {
                    case CONST_BOOLEAN:
                        if(((TransConstBoolean) test).value) {
                            processed.remove(i);
                            i--;
                            noTerms--;
                            removed = true;
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        // Reconstruct the max term.
        TransTreeReturn<A> toReturn = processed.get(noTerms - 1);
        for(int i = noTerms - 2; i >= 0; i--)
            toReturn = max(processed.get(i), toReturn);
        return toReturn;
    }

    // TODO add in handling of casting here if we expand to tests on doubles.
    private <A extends NumberVariable<A>> TransTreeReturn<A> collapseConstants(TransMin<A> tree) {
        // Gather and transform all the terms.
        Stack<TransTreeReturn<A>> toProcess = new Stack<>();
        toProcess.push(tree.right);
        toProcess.push(tree.left);

        Set<WrappedTransReturn<A>> seen = new HashSet<>();
        List<TransTreeReturn<A>> processed = new ArrayList<>();

        while(!toProcess.isEmpty()) {
            TransTreeReturn<A> t = toProcess.pop();
            switch(t.type) {
                case MIN:
                    TransMin<A> m = (TransMin<A>) t;
                    this.visitedNodes.add(m);
                    toProcess.push(m.right);
                    toProcess.push(m.left);
                    break;
                default:
                    WrappedTransReturn<A> w = new WrappedTransReturn<>(t);
                    if(!seen.contains(w)) {
                        seen.add(w);
                        processed.add(transform(t));
                    } else
                        t.addNodes(visitedNodes);
                    break;
            }
        }

        // Filter out terms that are know to be less than other terms.
        int noTerms = processed.size();
        for(int i = 0; i < noTerms; i++) {
            TransTreeReturn<A> right = processed.get(i);
            boolean removed = false;
            for(int j = 0; j < i && !removed; j++) {
                TransTreeReturn<A> left = processed.get(j);
                TransTreeReturn<BooleanVariable> test = lessThanEqual(left, right);
                bounds.addTransformedTree(tree, test);
                test = transformReturn(test);
                switch(test.type) {
                    case CONST_BOOLEAN:
                        if(((TransConstBoolean) test).value) {
                            processed.remove(i);
                            i--;
                            noTerms--;
                            removed = true;
                        }
                        break;
                    default:
                        break;
                }
            }

            for(int j = i + 1; j < noTerms && !removed; j++) {
                TransTreeReturn<A> left = processed.get(j);
                TransTreeReturn<BooleanVariable> test = lessThanEqual(left, right);
                bounds.addTransformedTree(tree, test);
                test = transformReturn(test);
                switch(test.type) {
                    case CONST_BOOLEAN:
                        if(((TransConstBoolean) test).value) {
                            processed.remove(i);
                            i--;
                            noTerms--;
                            removed = true;
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        // Reconstruct the min term.
        TransTreeReturn<A> toReturn = processed.get(noTerms - 1);
        for(int i = noTerms - 2; i >= 0; i--)
            toReturn = min(processed.get(i), toReturn);
        return toReturn;
    }

    private TransTreeReturn<BooleanVariable> collapseConstants(TransNegateBoolean tree) {
        TransTreeReturn<BooleanVariable> newInput = transform(tree.input);
        if(newInput.type == TransTreeType.CONST_BOOLEAN)
            return constant(!((TransConstBoolean) newInput).value);
        else
            return negateBoolean(newInput);
    }

    @SuppressWarnings("unchecked")
    private <V extends NumberVariable<V>> TransTreeReturn<V> collapseConstants(TransNegate<V> tree) {
        TransTreeReturn<V> newInput = transform(tree.input);
        if(newInput.type == TransTreeType.CONST_DOUBLE) {
            double value = ((TransConstDouble) newInput).value;
            return (TransTreeReturn<V>) constant(-value);
        } else if(newInput.type == TransTreeType.CONST_INT) {
            int value = ((TransConstInt) newInput).value;
            return (TransTreeReturn<V>) constant(-value);
        } else
            return negate(newInput);
    }

    /*
     * Unrequired casts used to keep the oracle compiler happy.
     */
    // TODO Simplify this by just cancelling on construction.
    private <L extends NumberVariable<L>, R extends NumberVariable<R>, RT extends NumberVariable<RT>> TransTreeReturn<RT> collapseConstants(
            TransDivide<L, R, RT> tree) {
        TransTreeReturn<L> left = transform(tree.left);
        TransTreeReturn<R> right = transform(tree.right);

        // Cancel through for zero's
        switch(left.type) {
            case CONST_DOUBLE: { // Perform the partial evaluation
                TransConstDouble l = (TransConstDouble) left;
                if(l.value == 0.0)
                    return (TransTreeReturn<RT>) constant(0.0);
                break;
            }
            case CONST_INT: {
                TransConstInt l = (TransConstInt) left;
                if(l.value == 0)
                    return constructConstant(0, tree.getOutputType());
                break;
            }
            default:
                break;
        }

        switch(right.type) {
            case CONST_DOUBLE: {
                TransConstDouble r = (TransConstDouble) right;
                if(r.value == 1.0) // Cancel the one
                    return applyCast(left, tree.getOutputType());

                switch(left.type) {
                    case CONST_DOUBLE: { // Perform the partial evaluation
                        TransConstDouble l = (TransConstDouble) left;
                        return (TransTreeReturn<RT>) constant(l.value / r.value);
                    }

                    case CONST_INT: { // Perform the partial evaluation
                        TransConstInt l = (TransConstInt) left;
                        return (TransTreeReturn<RT>) constant(l.value / r.value);
                    }
                    default:
                        break;
                }
                break;
            }
            case CONST_INT: {
                TransConstInt r = (TransConstInt) right;
                if(r.value == 1) // Cancel the one
                    return applyCast(left, tree.getOutputType());

                switch(left.type) {
                    case CONST_DOUBLE: { // Perform the partial evaluation
                        TransConstDouble l = (TransConstDouble) left;
                        return (TransTreeReturn<RT>) constant(l.value / r.value);
                    }
                    case CONST_INT: { // Perform the partial evaluation
                        TransConstInt l = (TransConstInt) left;
                        return (TransTreeReturn<RT>) constant(l.value / r.value);
                    }
                    default:
                        break;
                }
                break;
            }
            default:
                break;
        }

        // No collapsing available, just return a copy using the new trees.
        return applyCast(divide(left, right), tree.getOutputType());
    }

    private <L extends NumberVariable<L>, R extends NumberVariable<R>> TransTreeReturn<BooleanVariable> collapseConstantsLessThan(
            AddSubtractTrees<L> left, AddSubtractTrees<R> right) {
        if(isEmbedded()) {
            TransTreeReturn<BooleanVariable> toReturn = collapseConstantLessThanInner(left, right);
            if(toReturn != null)
                return toReturn;

            TransTreeReturn<L> lt = left.getTree();
            TransTreeReturn<R> rt = right.getTree();
            Set<TransTreeReturn<L>> mins = splitMins(lt);
            Set<TransTreeReturn<R>> maxes = splitMaxes(rt);

            // If there is no new information just return.
            if(mins != null || maxes != null) {

                // Setup unit sets for the case that the tree was not split.
                if(mins == null) {
                    mins = new HashSet<>();
                    mins.add(lt);
                }

                if(maxes == null) {
                    maxes = new HashSet<>();
                    maxes.add(rt);
                }

                AddSubtractTrees<DoubleVariable> zero = new AddSubtractTrees<>(VariableType.DoubleVariable, this,
                        bounds, lt);

                // For each split test it
                for(TransTreeReturn<L> min:mins) {
                    TransTreeReturn<L> negated = negate(min);

                    bounds.addTransformedTree(min, negated);
                    for(TransTreeReturn<R> max:maxes) {
                        AddSubtractTrees<DoubleVariable> combined = new AddSubtractTrees<>(VariableType.DoubleVariable,
                                this, bounds, max);
                        combined.consumeTree(max);
                        combined.consumeTree(negated);
                        toReturn = collapseConstantLessThanInner(zero, combined);
                        if(toReturn != null)
                            return toReturn;
                    }
                }
            }

            return null;
        }
        // There is no need to test if the trees are equivalent as this is already
        // accounted for when the left and right sides are cancelled.

        // Check if bounds mean that this is always true or always false.
        else {
            TransTreeReturn<BooleanVariable> t = processInequality(left, right, true);
            if(t != null)
                return t;
            t = processInequality(right, left, false);
            if(t != null)
                return constant(false);

            left.mergeConstants(right);
            return lessThan(left.getTree(), right.getTree());
        }
    }

    private <L extends NumberVariable<L>, R extends NumberVariable<R>> TransTreeReturn<BooleanVariable> collapseConstantLessThanInner(
            AddSubtractTrees<L> left, AddSubtractTrees<R> right) {
        // Check for constant values
        boolean leftConstant = left.isConstant();
        boolean rightConstant = right.isConstant();
        if(leftConstant) {
            if(rightConstant)
                return TransTree.constant((left.outputType == VariableType.IntVariable ? left.getIntConstant()
                        : left.getDoubleConstant()) < (right.outputType == VariableType.IntVariable
                                ? right.getIntConstant()
                                : right.getDoubleConstant()));

            if(left.outputType == VariableType.DoubleVariable && left.getDoubleConstant() == Double.POSITIVE_INFINITY)
                return constant(false);
        }

        if(rightConstant && right.outputType == VariableType.DoubleVariable
                && right.getDoubleConstant() == Double.NEGATIVE_INFINITY)
            return constant(false);

        return null;
    }

    private <L extends NumberVariable<L>, R extends NumberVariable<R>> TransTreeReturn<BooleanVariable> collapseConstantsLessThanEqual(
            AddSubtractTrees<L> left, AddSubtractTrees<R> right) {

        if(isEmbedded()) {
            TransTreeReturn<BooleanVariable> toReturn = collapseConstantLessThanEqualInner(left, right);
            if(toReturn != null)
                return toReturn;

            TransTreeReturn<L> lt = left.getTree();
            TransTreeReturn<R> rt = right.getTree();
            Set<TransTreeReturn<L>> mins = splitMins(lt);
            Set<TransTreeReturn<R>> maxes = splitMaxes(rt);

            // If there is no new information just return.
            if(mins != null || maxes != null) {

                // Setup unit sets for the case that the tree was not split.
                if(mins == null) {
                    mins = new HashSet<>();
                    mins.add(lt);
                }

                if(maxes == null) {
                    maxes = new HashSet<>();
                    maxes.add(rt);
                }

                AddSubtractTrees<DoubleVariable> zero = new AddSubtractTrees<>(VariableType.DoubleVariable, this,
                        bounds, lt);

                // For each split test it
                for(TransTreeReturn<L> min:mins) {
                    TransTreeReturn<L> negated = negate(min);
                    bounds.addTransformedTree(min, negated);
                    for(TransTreeReturn<R> max:maxes) {
                        AddSubtractTrees<DoubleVariable> combined = new AddSubtractTrees<>(VariableType.DoubleVariable,
                                this, bounds, max);
                        combined.consumeTree(max);
                        combined.consumeTree(negated);
                        toReturn = collapseConstantLessThanEqualInner(zero, combined);
                        if(toReturn != null)
                            return toReturn;
                    }
                }
            }
            return null;
        }

        // There is no need to test if the trees are equivalent as this is already
        // accounted for when the left and right sides are cancelled.

        // Check if bounds mean that this is always true or always false.
        else {
            TransTreeReturn<BooleanVariable> t = processInequality(left, right, false);
            if(t != null)
                return t;
            t = processInequality(right, left, true);
            if(t != null)
                return constant(false);

            left.mergeConstants(right);
            return lessThanEqual(left.getTree(), right.getTree());
        }
    }

    private <L extends NumberVariable<L>, R extends NumberVariable<R>> TransTreeReturn<BooleanVariable> collapseConstantLessThanEqualInner(
            AddSubtractTrees<L> left, AddSubtractTrees<R> right) {
        boolean leftConstant = left.isConstant();
        boolean rightConstant = right.isConstant();
        if(leftConstant) {
            if(rightConstant)
                return TransTree.constant((left.outputType == VariableType.IntVariable ? left.getIntConstant()
                        : left.getDoubleConstant()) <= (right.outputType == VariableType.IntVariable
                                ? right.getIntConstant()
                                : right.getDoubleConstant()));

            if(left.outputType == VariableType.DoubleVariable && left.getDoubleConstant() == Double.NEGATIVE_INFINITY)
                return constant(true);
        }

        if(rightConstant && right.outputType == VariableType.DoubleVariable
                && right.getDoubleConstant() == Double.POSITIVE_INFINITY)
            return constant(true);
        return null;
    }

    private <L extends NumberVariable<L>, R extends NumberVariable<R>> TransTreeReturn<BooleanVariable> processInequality(
            AddSubtractTrees<L> left, AddSubtractTrees<R> right, boolean lessThan) {
        left = left.copy();
        right = right.copy();

        // First test the expression without changing values.
        enterEmbedded();
        TransTreeReturn<BooleanVariable> testTree = (lessThan) ? collapseConstantsLessThan(left, right)
                : collapseConstantsLessThanEqual(left, right);
        leaveEmbedded();
        if(testTree != null && testTree.type == TransTreeType.CONST_BOOLEAN)
            return testTree;

        int size = updatedVars.size();
        for(int i = size - 1; i >= 0; i--) {
            Set<VariableDescription<?>> usedVariables = new HashSet<>();
            usedVariables.addAll(left.getVariableDescs());
            usedVariables.addAll(right.getVariableDescs());

            while(i >= 0 && !usedVariables.contains(updatedVars.get(i)))
                i--;

            if(i >= 0) {
                VariableDescription<?> desc = updatedVars.get(i);
                boolean change = left.maxValue(desc);
                change = change || right.minValue(desc);

                if(change) {
                    left.cancelTerms(right);

                    enterEmbedded();
                    testTree = (lessThan) ? collapseConstantsLessThan(left, right)
                            : collapseConstantsLessThanEqual(left, right);
                    leaveEmbedded();
                    if(testTree != null && testTree.type == TransTreeType.CONST_BOOLEAN
                            && ((TransConstBoolean) testTree).value)
                        // max value on the left is less than the min on the right, then this is always true.
                        return testTree;
                }
            }
        }
        // One last run, replacing anything we can.
        if(!isEmbedded()) {
            enterEmbedded();
            left.maxValue();
            left.cancelTerms(right);

            right.minValue();
            left.cancelTerms(right);

            testTree = (lessThan) ? collapseConstantsLessThan(left, right)
                    : collapseConstantsLessThanEqual(left, right);
            leaveEmbedded();

            if(testTree != null && testTree.type == TransTreeType.CONST_BOOLEAN && ((TransConstBoolean) testTree).value)
                // max value on the left is less than the min on the right, then this is always true.
                return testTree;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private <RT extends NumberVariable<RT>> TransTreeReturn<RT> applyCast(
            TransTreeReturn<? extends NumberVariable<?>> tree, Type<?> outputType) {

        Type<?> treeType = tree.getOutputType();
        if(outputType != treeType) {
            if(outputType == VariableType.IntVariable) {
                if(treeType == VariableType.DoubleVariable)
                    return (TransTreeReturn<RT>) TransTree.castToInteger((TransTreeReturn<DoubleVariable>) tree);
                else
                    throw new CompilerException("Cannot cast from this type to Integer");
            } else if(outputType == VariableType.DoubleVariable) {
                if(treeType == VariableType.IntVariable)
                    if(tree.type == TransTreeType.CONST_INT) // Remove unrequired casts by reforming the tree.
                        return (TransTreeReturn<RT>) constant((double) ((TransConstInt) tree).value);
                    else
                        return (TransTreeReturn<RT>) TransTree.castToDouble((TransTreeReturn<IntVariable>) tree);
                else
                    throw new CompilerException("Cannot cast from this type to Double");
            } else
                throw new CompilerException("Cannot cast to this type");
        } else
            return (TransTreeReturn<RT>) tree;
    }

    private <L extends NumberVariable<L>, R extends NumberVariable<R>, RT extends NumberVariable<RT>> TransTreeReturn<RT> collapseConstants(
            TransRemainder<L, R, RT> tree) {

        TransTreeReturn<L> left = transform(tree.left);
        TransTreeReturn<R> right = transform(tree.right);

        switch(right.type) {
            case CONST_DOUBLE: {
                TransConstDouble r = (TransConstDouble) right;
                switch(left.type) {
                    case CONST_DOUBLE: {
                        TransConstDouble l = (TransConstDouble) left;
                        return (TransTreeReturn<RT>) TransTree.constant(l.value % r.value);
                    }
                    case CONST_INT: {
                        TransConstInt l = (TransConstInt) left;
                        return (TransTreeReturn<RT>) TransTree.constant(l.value % r.value);
                    }
                    default:
                        break;
                }

                break;
            }
            case CONST_INT: {
                TransConstInt r = (TransConstInt) right;
                Type<L> leftType = left.getOutputType();
                if(leftType == VariableType.IntVariable) {
                    TransTreeReturn<IntVariable> leftInt = (TransTreeReturn<IntVariable>) left;
                    if(bounds.gcds.getGCD(leftInt) % r.value == 0)
                        return (TransTreeReturn<RT>) TransTree.constant(0);
                }

                switch(left.type) {
                    case CONST_DOUBLE: {
                        TransConstDouble l = (TransConstDouble) left;
                        return (TransTreeReturn<RT>) TransTree.constant(l.value % r.value);
                    }
                    case CONST_INT: {
                        TransConstInt l = (TransConstInt) left;
                        return (TransTreeReturn<RT>) TransTree.constant(l.value % r.value);
                    }
                    default:
                        break;
                }
                break;
            }
            default:
                break;
        }

        // No collapsing available, just return a copy using the new trees.
        return (TransTreeReturn<RT>) remainder(left, right);
    }

    private <RT extends NumberVariable<RT>> TransTreeReturn<RT> collapseConstants(TransAdd<?, ?, RT> tree) {
        AddSubtractTrees<RT> trees = new AddSubtractTrees<>(tree.getOutputType(), this, bounds, tree);
        trees.consumeTree(tree);
        return trees.getTree();
    }

    /*
     * Unrequired casts used to keep the oracle compiler happy.
     */
    private <L extends NumberVariable<L>, R extends NumberVariable<R>, RT extends NumberVariable<RT>> TransTreeReturn<RT> collapseConstants(
            TransMultiply<L, R, RT> tree) {
        TransTreeReturn<L> left = transform(tree.left);
        TransTreeReturn<R> right = transform(tree.right);

        switch(left.type) {
            case CONST_DOUBLE: {
                TransConstDouble l = (TransConstDouble) left;

                if(l.value == 0.0) // return 0
                    return (TransTreeReturn<RT>) constant(0.0);

                if(l.value == 1.0) // Cancel out the one
                    return applyCast(right, tree.getOutputType());

                switch(right.type) {
                    case CONST_DOUBLE: {// Perform the partial evaluation
                        TransConstDouble r = (TransConstDouble) right;
                        return (TransTreeReturn<RT>) constant(l.value * r.value);
                    }
                    case CONST_INT: {// Perform the partial evaluation
                        TransConstInt r = (TransConstInt) right;
                        return (TransTreeReturn<RT>) constant(l.value * r.value);
                    }
                    default: {
                        return (TransTreeReturn<RT>) multiply(right, left);
                    }
                }
            }

            case CONST_INT: {
                TransConstInt l = (TransConstInt) left;
                if(l.value == 0)
                    return constructConstant(0, tree.getOutputType());

                if(l.value == 1) // Cancel out the one
                    return applyCast(right, tree.getOutputType());

                switch(right.type) {
                    case CONST_DOUBLE: {// Perform the partial evaluation
                        TransConstDouble r = (TransConstDouble) right;
                        return (TransTreeReturn<RT>) constant(l.value * r.value);
                    }
                    case CONST_INT: {// Perform the partial evaluation
                        TransConstInt r = (TransConstInt) right;
                        return (TransTreeReturn<RT>) constant(l.value * r.value);
                    }
                    default: {
                        return (TransTreeReturn<RT>) multiply(right, left);
                    }
                }
            }
            default:
                break;
        }

        switch(right.type) {
            case CONST_DOUBLE: {
                TransConstDouble r = (TransConstDouble) right;
                if(r.value == 0.0)
                    return (TransTreeReturn<RT>) constant(0.0);

                if(r.value == 1.0)
                    return applyCast(left, tree.getOutputType());
                break;
            }
            case CONST_INT: {
                TransConstInt r = (TransConstInt) right;
                if(r.value == 0)
                    return constructConstant(0, tree.getOutputType());

                if(r.value == 1)
                    return applyCast(left, tree.getOutputType());
                break;
            }
            default:
                break;
        }

        // No collapsing available, just return a copy using the new trees.
        return (TransTreeReturn<RT>) multiply(left, right);
    }

    private <RT extends NumberVariable<RT>> TransTreeReturn<RT> constructConstant(int i, Type<RT> type) {
        if(type == VariableType.IntVariable)
            return (TransTreeReturn<RT>) constant(i);
        else
            return (TransTreeReturn<RT>) constant((double) i);
    }

    private <RT extends NumberVariable<RT>> TransTreeReturn<RT> collapseConstants(TransSubtract<?, ?, RT> tree) {
        AddSubtractTrees<RT> trees = new AddSubtractTrees<>(tree.getOutputType(), this, bounds, tree);
        trees.consumeTree(tree);
        return trees.getTree();
    }

    private final boolean isEmbedded() {
        return embeddedDepth != 0;
    }

    private final void enterEmbedded() {
        embeddedDepth++;
    }

    private final void leaveEmbedded() {
        embeddedDepth--;
    }

    /**
     * Returns a set containing the simple splits or null if the tree is not split.
     * 
     * @param <X>  The return type of the tree.
     * @param tree The tree to split.
     * @return The set of possible splits of the max function, or null if there are no splits.
     */
    private <A extends NumberVariable<A>, B extends NumberVariable<B>, X extends NumberVariable<X>> Set<TransTreeReturn<X>> splitMins(
            TransTreeReturn<X> tree) {
        Set<TransTreeReturn<X>> mins = splitMinsInternal(tree);
        if(mins != null) {
            for(TransTreeReturn<X> m:mins)
                bounds.addTransformedTree(tree, m);
        }
        return mins;
    }

    /**
     * Returns a set containing the simple splits or null if the tree is not split.
     * 
     * @param <X>  The return type of the tree.
     * @param tree The tree to split.
     * @return The set of possible splits of the min function, or null if there are no splits.
     */
    private <A extends NumberVariable<A>, B extends NumberVariable<B>, X extends NumberVariable<X>> Set<TransTreeReturn<X>> splitMaxes(
            TransTreeReturn<X> tree) {
        Set<TransTreeReturn<X>> maxes = splitMaxesInternal(tree);
        if(maxes != null) {
            for(TransTreeReturn<X> m:maxes)
                bounds.addTransformedTree(tree, m);
        }
        return maxes;
    }

    /**
     * Returns a set containing the simple splits or null if the tree is not split.
     * 
     * @param <X>  The return type of the tree.
     * @param tree The tree to split.
     * @return The set of possible splits of the max function, or null if there are no splits.
     */
    private <A extends NumberVariable<A>, B extends NumberVariable<B>, X extends NumberVariable<X>> Set<TransTreeReturn<X>> splitMinsInternal(
            TransTreeReturn<X> tree) {
        switch(tree.type) {
            case ADD: {
                TransAdd<A, B, X> add = (TransAdd<A, B, X>) tree;
                Set<TransTreeReturn<A>> left = splitMinsInternal(add.left);
                Set<TransTreeReturn<B>> right = splitMinsInternal(add.right);
                if(left == null) {
                    if(right == null) {
                        return null;
                    } else {
                        Set<TransTreeReturn<X>> toReturn = new HashSet<>();
                        for(TransTreeReturn<B> b:right)
                            toReturn.add((TransTreeReturn<X>) add(add.left, b));
                        return toReturn;
                    }
                } else {
                    if(right == null) {
                        Set<TransTreeReturn<X>> toReturn = new HashSet<>();
                        for(TransTreeReturn<A> a:left)
                            toReturn.add((TransTreeReturn<X>) add(a, add.right));
                        return toReturn;
                    } else {
                        Set<TransTreeReturn<X>> toReturn = new HashSet<>();
                        for(TransTreeReturn<A> a:left)
                            for(TransTreeReturn<B> b:right)
                                toReturn.add((TransTreeReturn<X>) add(a, b));
                        return toReturn;
                    }
                }
            }
            case EXTERNAL_FUNCTION_CALL_RETURN: {
                TransExternalFunctionCallReturn<X> t = (TransExternalFunctionCallReturn<X>) tree;
                List<Set<TransTreeReturn<A>>> args = new ArrayList<>();
                if(t.func.monotonic) {
                    if(t.func.increasing) {
                        for(TransTreeReturn<?> arg:t.args) {
                            Set<TransTreeReturn<A>> input = splitMinsInternal((TransTreeReturn<A>) arg);
                            if(input == null)
                                return null;
                            args.add(input);
                        }
                    } else {
                        for(TransTreeReturn<?> arg:t.args) {
                            Set<TransTreeReturn<A>> input = splitMaxesInternal((TransTreeReturn<A>) arg);
                            if(input == null)
                                return null;
                            args.add(input);
                        }
                    }
                } else
                    return null;
                Set<TransTreeReturn<A>[]> inputs = crossProduct(args);
                Set<TransTreeReturn<X>> toReturn = new HashSet<>();
                for(TransTreeReturn<A>[] i:inputs)
                    toReturn.add((TransTreeReturn<X>) TransTree.functionCallReturn(t.func, t.outputType, i));
                return toReturn;
            }
            case MIN: {
                TransMin<X> min = (TransMin<X>) tree;
                Set<TransTreeReturn<X>> left = splitMinsInternal(min.left);
                Set<TransTreeReturn<X>> right = splitMinsInternal(min.right);
                if(left == null) {
                    if(right == null) {
                        Set<TransTreeReturn<X>> toReturn = new HashSet<>();
                        toReturn.add(min.left);
                        toReturn.add(min.right);
                        return toReturn;
                    } else {
                        right.add(min.left);
                        return right;
                    }
                } else {
                    if(right == null) {
                        left.add(min.right);
                        return left;
                    } else {
                        left.addAll(right);
                        return left;
                    }
                }
            }
            case NEGATE: {
                Set<TransTreeReturn<X>> input = splitMaxesInternal(((TransNegate<X>) tree).input);
                if(input == null)
                    return null;
                Set<TransTreeReturn<X>> toReturn = new HashSet<>();
                for(TransTreeReturn<X> i:input)
                    toReturn.add(negate(i));
                return toReturn;
            }
            case SUBTRACT: {
                TransSubtract<A, B, X> add = (TransSubtract<A, B, X>) tree;
                Set<TransTreeReturn<A>> left = splitMinsInternal(add.left);
                Set<TransTreeReturn<B>> right = splitMaxesInternal(add.right);
                if(left == null) {
                    if(right == null) {
                        return null;
                    } else {
                        Set<TransTreeReturn<X>> toReturn = new HashSet<>();
                        for(TransTreeReturn<B> b:right)
                            toReturn.add((TransTreeReturn<X>) subtract(add.left, b));
                        return toReturn;
                    }
                } else {
                    if(right == null) {
                        Set<TransTreeReturn<X>> toReturn = new HashSet<>();
                        for(TransTreeReturn<A> a:left)
                            toReturn.add((TransTreeReturn<X>) subtract(a, add.right));
                        return toReturn;
                    } else {
                        Set<TransTreeReturn<X>> toReturn = new HashSet<>();
                        for(TransTreeReturn<A> a:left)
                            for(TransTreeReturn<B> b:right)
                                toReturn.add((TransTreeReturn<X>) subtract(a, b));
                        return toReturn;
                    }
                }
            }
            default:
                return null;
        }
    }

    /**
     * Returns a set containing the simple splits or null if the tree is not split.
     * 
     * @param <X>  The return type of the tree.
     * @param tree The tree to split.
     * @return The set of possible splits of the min function, or null if there are no splits.
     */
    private <A extends NumberVariable<A>, B extends NumberVariable<B>, X extends NumberVariable<X>> Set<TransTreeReturn<X>> splitMaxesInternal(
            TransTreeReturn<X> tree) {
        switch(tree.type) {
            case ADD: {
                TransAdd<A, B, X> add = (TransAdd<A, B, X>) tree;
                Set<TransTreeReturn<A>> left = splitMaxesInternal(add.left);
                Set<TransTreeReturn<B>> right = splitMaxesInternal(add.right);
                if(left == null) {
                    if(right == null) {
                        return null;
                    } else {
                        Set<TransTreeReturn<X>> toReturn = new HashSet<>();
                        for(TransTreeReturn<B> b:right)
                            toReturn.add((TransTreeReturn<X>) add(add.left, b));
                        return toReturn;
                    }
                } else {
                    if(right == null) {
                        Set<TransTreeReturn<X>> toReturn = new HashSet<>();
                        for(TransTreeReturn<A> a:left)
                            toReturn.add((TransTreeReturn<X>) add(a, add.right));
                        return toReturn;
                    } else {
                        Set<TransTreeReturn<X>> toReturn = new HashSet<>();
                        for(TransTreeReturn<A> a:left)
                            for(TransTreeReturn<B> b:right)
                                toReturn.add((TransTreeReturn<X>) add(a, b));
                        return toReturn;
                    }
                }
            }
            case EXTERNAL_FUNCTION_CALL_RETURN: {
                TransExternalFunctionCallReturn<X> t = (TransExternalFunctionCallReturn<X>) tree;
                List<Set<TransTreeReturn<A>>> args = new ArrayList<>();
                if(t.func.monotonic) {
                    if(t.func.increasing) {
                        for(TransTreeReturn<?> arg:t.args) {
                            Set<TransTreeReturn<A>> input = splitMaxesInternal((TransTreeReturn<A>) arg);
                            if(input == null)
                                return null;
                            args.add(input);
                        }
                    } else {
                        for(TransTreeReturn<?> arg:t.args) {
                            Set<TransTreeReturn<A>> input = splitMinsInternal((TransTreeReturn<A>) arg);
                            if(input == null)
                                return null;
                            args.add(input);
                        }
                    }
                } else
                    return null;
                Set<TransTreeReturn<A>[]> inputs = crossProduct(args);
                Set<TransTreeReturn<X>> toReturn = new HashSet<>();
                for(TransTreeReturn<A>[] i:inputs)
                    toReturn.add((TransTreeReturn<X>) TransTree.functionCallReturn(t.func, t.outputType, i));
                return toReturn;
            }
            case MAX: {
                TransMax<X> max = (TransMax<X>) tree;
                Set<TransTreeReturn<X>> left = splitMaxesInternal(max.left);
                Set<TransTreeReturn<X>> right = splitMaxesInternal(max.right);
                if(left == null) {
                    if(right == null) {
                        Set<TransTreeReturn<X>> toReturn = new HashSet<>();
                        toReturn.add(max.left);
                        toReturn.add(max.right);
                        return toReturn;
                    } else {
                        right.add(max.left);
                        return right;
                    }
                } else {
                    if(right == null) {
                        left.add(max.right);
                        return left;
                    } else {
                        left.addAll(right);
                        return left;
                    }
                }
            }
            case NEGATE: {
                Set<TransTreeReturn<X>> input = splitMinsInternal(((TransNegate<X>) tree).input);
                if(input == null)
                    return null;
                Set<TransTreeReturn<X>> toReturn = new HashSet<>();
                for(TransTreeReturn<X> i:input)
                    toReturn.add(negate(i));
                return toReturn;
            }
            case SUBTRACT: {
                TransSubtract<A, B, X> add = (TransSubtract<A, B, X>) tree;
                Set<TransTreeReturn<A>> left = splitMaxesInternal(add.left);
                Set<TransTreeReturn<B>> right = splitMinsInternal(add.right);
                if(left == null) {
                    if(right == null) {
                        return null;
                    } else {
                        Set<TransTreeReturn<X>> toReturn = new HashSet<>();
                        for(TransTreeReturn<B> b:right)
                            toReturn.add((TransTreeReturn<X>) subtract(add.left, b));
                        return toReturn;
                    }
                } else {
                    if(right == null) {
                        Set<TransTreeReturn<X>> toReturn = new HashSet<>();
                        for(TransTreeReturn<A> a:left)
                            toReturn.add((TransTreeReturn<X>) subtract(a, add.right));
                        return toReturn;
                    } else {
                        Set<TransTreeReturn<X>> toReturn = new HashSet<>();
                        for(TransTreeReturn<A> a:left)
                            for(TransTreeReturn<B> b:right)
                                toReturn.add((TransTreeReturn<X>) subtract(a, b));
                        return toReturn;
                    }
                }
            }
            default:
                return null;
        }
    }

    private <A extends NumberVariable<A>> Set<TransTreeReturn<A>[]> crossProduct(List<Set<TransTreeReturn<A>>> args) {
        Set<TransTreeReturn<A>[]> inputs = new HashSet<>();
        crossProduct(new ArrayList<>(), inputs, args, 0);
        return inputs;
    }

    private <A extends NumberVariable<A>> void crossProduct(ArrayList<TransTreeReturn<A>> current,
            Set<TransTreeReturn<A>[]> inputs, List<Set<TransTreeReturn<A>>> args, int i) {
        if(i < args.size()) {
            for(TransTreeReturn<A> t:args.get(i)) {
                current.add(t);
                crossProduct(current, inputs, args, i + 1);
                current.remove(i);
            }
        } else {
            inputs.add((TransTreeReturn<A>[]) current.toArray(new TransTreeReturn<?>[current.size()]));
        }
    }
}
