/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sandwood.common.execution.ExecutionType;
import org.sandwood.compiler.compilation.ExternalFunction;
import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.ArrayType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.RandomVariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.ScalarVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.names.FunctionName;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Tag;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.Visibility;
import org.sandwood.compiler.trees.outputTree.OutputTree;
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
import org.sandwood.compiler.trees.transformationTree.transformers.ApplyConstantsTransformer;
import org.sandwood.compiler.trees.transformationTree.transformers.ApplyConstraintsTransformer;
import org.sandwood.compiler.trees.transformationTree.transformers.CollapseConstantsTransformer;
import org.sandwood.compiler.trees.transformationTree.transformers.CollapseUnrequiredForLoopsTransformer;
import org.sandwood.compiler.trees.transformationTree.transformers.CopyTransformer;
import org.sandwood.compiler.trees.transformationTree.transformers.ExtractCommonContraintsTransformer;
import org.sandwood.compiler.trees.transformationTree.transformers.LoopUnrollingTransformer;
import org.sandwood.compiler.trees.transformationTree.transformers.MoveConstraintsOutTransformer;
import org.sandwood.compiler.trees.transformationTree.transformers.MoveNegationTransformer;
import org.sandwood.compiler.trees.transformationTree.transformers.OptimizeConditionals;
import org.sandwood.compiler.trees.transformationTree.transformers.ParFor;
import org.sandwood.compiler.trees.transformationTree.transformers.ParallelIndexes;
import org.sandwood.compiler.trees.transformationTree.transformers.RemoveUnusedScopesTransformer;
import org.sandwood.compiler.trees.transformationTree.transformers.SubstituteKnownValuesTransformer;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.KnownValuesTrans;
import org.sandwood.compiler.trees.transformationTree.visitors.ConstraintMappingVisitor;
import org.sandwood.compiler.trees.transformationTree.visitors.NodeEnumerator;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VariableTracking;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VariableTrackingVisitor;

public abstract class TransTree<T extends TransTree<T>> extends Tree<TransTree<?>> {

    private static class TreeCheckData {
        private final TransTree<?> tree;

        public TreeCheckData(TransTree<?> tree) {
            this.tree = tree;
        }

        public TreeCheckData() {
            this.tree = null;
        }
    }

    protected TreeCheckData checkTree(TreeCheckData lastTree, Set<TransTree<?>> visitedNodes) {
        TreeCheckData t = new TreeCheckData(this);

        // Check for the double use of nodes in the new tree.
        new TreeVisitor() {
            private final Set<TransTree<?>> seen = new HashSet<>();

            @Override
            public void visit(TransTree<?> tree) {
                // NOP is a special case, maybe it shouldn't be, and we should create an object
                if(tree != TransNOP.nop) {
                    // for every nop.
                    if(seen.contains(tree)) {
                        throw new CompilerException("Tree node used twice in the same tree.\n"
                                + "Error tree node reused in tree " + tree.id + " :\n" + tree);
                    } else {
                        seen.add(tree);
                        tree.traverseTree(this);
                    }
                }
            }
        }.visit(this);

        // Check all nodes in the last tree were visited.
        if(lastTree.tree != null) {
            new TreeVisitor() {
                @Override
                public void visit(TransTree<?> tree) {
                    if(!visitedNodes.contains(tree) && tree != TransNOP.nop) {
                        throw new CompilerException(
                                "Tree: " + tree.type + ":" + tree.id + " " + tree + " was not visited");
                    }
                    tree.traverseTree(this);
                }
            }.visit(lastTree.tree);
        }

        return t;
    }

    public enum TransTreeType {
        ADD,
        ALLOCATE_ARRAY,
        AND,
        ARRAY_GET,
        ARRAY_PUT,
        CAST_DOUBLE,
        CAST_INT,
        CONDITIONAL_ASSIGNMENT,
        CONST_BOOLEAN,
        CONST_DOUBLE,
        CONST_INT,
        DIVIDE,
        EQUALITY,
        EXTERNAL_FUNCTION_CALL_RETURN,
        FOR,
        FORK_JOIN_FOR,
        GET_FIELD,
        IF,
        INITIALIZE,
        INITIALIZE_UNSET,
        LESS_THAN,
        LESS_THAN_EQUAL,
        LOAD,
        LOCAL_FUNCTION_CALL,
        LOCAL_FUNCTION_CALL_RETURN,
        MAX,
        MIN,
        MULTIPLY,
        NEGATE,
        NEGATE_BOOLEAN,
        NOP,
        OR,
        PAR_FOR_LAMBDA,
        REMAINDER,
        RV_FUNCTION_CALL,
        RV_FUNCTION_CALL_RETURN,
        SCOPE,
        SEQUENTIAL,
        STORE,
        SUBTRACT
    }

    public final TransTreeType type;
    private final int size;

    protected TransTree(TransTreeType type, int size) {
        super();
        this.type = type;
        this.size = size;
    }

    public final int size() {
        return size;
    }

    /**
     * Method to test if a tree is a subtree of this tree.
     * 
     * @param t The tree to test if it is a component of this tree.
     * @return Returns true if at least one copy of this t is found in this tree.
     */
    public boolean containsEquivalent(TransTree<?> t) {
        if(size == t.size)
            return equivalent(t);
        else if(size > t.size) {
            for(TransTree<?> tree:getChildren())
                if(tree.containsEquivalent(t))
                    return true;
        }
        return false;
    }

    @Override
    public final boolean equals(Object other) {
        return this == other;
    }

    @Override
    public final int hashCode() {
        return id.tag;
    }

    boolean equivalentHashSet = false;
    int equivalentHash;

    @Override
    public final int equivalentHashCode() {
        if(!equivalentHashSet) {
            equivalentHash = equivalentHashCodeInternal();
            equivalentHashSet = true;
        }
        return equivalentHash;
    }

    protected abstract int equivalentHashCodeInternal();

    /**
     * A method for comparing two trees to see if they are equivalent. This method would be the equality method if it
     * wasn't to make equals equivalent to == when building dictionaries of subtrees for some of the transformations.
     * 
     * @param other The tree to compare this tree to.
     * @return Returns true if the structure of the two trees is the same.
     */
    @Override
    public final boolean equivalent(TransTree<?> other) {
        return equivalent(other, Collections.emptyMap());
    }

    /**
     * A method for comparing if two trees to see if they have the same structure and access the same variables if some
     * variable names are substituted.
     * 
     * An example of when this would be used is when comparing two loop bodies where the loop index has a different
     * name.
     * 
     * @param other         The tree to compare against.
     * @param substitutions A Map of variable names to substitute and the names they should be substituted with.
     * @return Return true if the structure of the two trees is the same once the substitution is applied.
     */
    public final boolean equivalent(TransTree<?> other,
            Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if((substitutions.isEmpty() && equivalentHashCode() != other.equivalentHashCode()) || size != other.size)
            return false;
        else
            return equivalentInternal(other, substitutions);
    }

    /**
     * A method for comparing if two trees to see if they have the same structure and access the same variables if some
     * variable names are substituted.
     * 
     * An example of when this would be used is when comparing two loop bodies where the loop index has a different
     * name.
     * 
     * @param other         The tree to compare against.
     * @param substitutions A Map of variable names to substitute and the names they should be substituted with.
     * @return Return true if the structure of the two trees is the same once the substitution is applied.
     */
    protected abstract boolean equivalentInternal(TransTree<?> other,
            Map<VariableDescription<?>, VariableDescription<?>> substitutions);

    protected int equivalentHashCode(TransTreeReturn<?>[] trees) {
        int prime = 31;
        int hash = 0;
        for(TransTreeReturn<?> tree:trees)
            hash = hash * prime + tree.equivalentHashCode();
        return hash;
    }

    public T applyConstants(Map<VariableDescription<?>, TransTreeReturn<?>> constants) {
        return applyConstants(constants, new HashSet<>());
    }

    private T applyConstants(Map<VariableDescription<?>, TransTreeReturn<?>> constants,
            Set<TransTree<?>> visitedNodes) {
        return new ApplyConstantsTransformer(constants).transform(this, visitedNodes);
    }

    public T applyOptimisations(ArgDesc<?>[] args, Map<VariableDescription<?>, TransTreeReturn<?>> constants,
            KnownValuesTrans knownValues) {
        TransTree<T> oldTree;
        TransTree<T> tree = this;

        TreeCheckData tcd = new TreeCheckData();
        tcd = tree.checkTree(tcd, null);

        // Get rid of the really simple stuff first as this will make a big saving
        // elsewhere.
        Set<TransTree<?>> modifiedTrees = new HashSet<>();
        tree = tree.applyConstants(constants, modifiedTrees);
        tcd = tree.checkTree(tcd, modifiedTrees);

        modifiedTrees.clear();
        tree = tree.collapseConstants(args, knownValues, modifiedTrees);
        tcd = tree.checkTree(tcd, modifiedTrees);

        do {
            oldTree = tree;

            modifiedTrees.clear();
            tree = tree.unrollLoops(modifiedTrees);
            tcd = tree.checkTree(tcd, modifiedTrees);

            modifiedTrees.clear();
            tree = tree.substituteKnownValues(knownValues, modifiedTrees);
            tcd = tree.checkTree(tcd, modifiedTrees);

            modifiedTrees.clear();
            tree = tree.moveNegation(modifiedTrees);
            tcd = tree.checkTree(tcd, modifiedTrees);

            modifiedTrees.clear();
            tree = tree.extractCommonConstraints(modifiedTrees);
            tcd = tree.checkTree(tcd, modifiedTrees);

            modifiedTrees.clear();
            tree = tree.moveConstraintsOut(modifiedTrees);
            tcd = tree.checkTree(tcd, modifiedTrees);

            modifiedTrees.clear();
            tree = tree.optimizeConditionals(modifiedTrees);
            tcd = tree.checkTree(tcd, modifiedTrees);

            modifiedTrees.clear();
            tree = tree.applyConstraints(modifiedTrees);
            tcd = tree.checkTree(tcd, modifiedTrees);

            modifiedTrees.clear();
            tree = tree.collapseUnrequiredForLoops(modifiedTrees);
            tcd = tree.checkTree(tcd, modifiedTrees);

            modifiedTrees.clear();
            tree = tree.collapseConstants(args, knownValues, modifiedTrees);
            tcd = tree.checkTree(tcd, modifiedTrees);

            modifiedTrees.clear();
            tree = tree.removeUnusedScopes(modifiedTrees);
            tcd = tree.checkTree(tcd, modifiedTrees);
        } while(!oldTree.equivalent(tree));
        @SuppressWarnings("unchecked")
        T toReturn = (T) tree;
        return toReturn;
    }

    public abstract void traverseTree(TreeVisitor v);

    public VariableTracking getVariableTracking() {
        VariableTrackingVisitor v = new VariableTrackingVisitor();
        v.visit(this);
        return v.getVariableTrackingData();
    }

    public VariableTracking getVariableTracking(Set<TransTree<?>> additionalTrees) {
        return getVariableTracking(additionalTrees, Collections.emptySet());
    }

    public VariableTracking getVariableTracking(Set<TransTree<?>> additionalTrees,
            Set<VariableDescription<?>> knownGlobals) {
        VariableTrackingVisitor v = new VariableTrackingVisitor(knownGlobals);
        v.visit(this);
        for(TransTree<?> t:additionalTrees)
            v.visit(t);
        return v.getVariableTrackingData();
    }

    // TODO make this protected again.
    public T collapseConstants() {
        CollapseConstantsTransformer c = new CollapseConstantsTransformer(new ArgDesc[0],
                KnownValuesTrans.constructKnownValues(), this);
        return c.transform(this, new HashSet<>());
    }

    protected T collapseConstants(ArgDesc<?>[] args, KnownValuesTrans knownValues, Set<TransTree<?>> visitedNodes) {
        CollapseConstantsTransformer c = new CollapseConstantsTransformer(args, knownValues, this);
        return c.transform(this, visitedNodes);
    }

    protected T extractCommonConstraints(Set<TransTree<?>> visitedNodes) {
        return new ExtractCommonContraintsTransformer().transform(this, visitedNodes);
    }

    protected T moveNegation(Set<TransTree<?>> visitedNodes) {
        return new MoveNegationTransformer().transform(this, visitedNodes);
    }

    protected T substituteKnownValues(KnownValuesTrans knownValues, Set<TransTree<?>> visitedNodes) {
        return new SubstituteKnownValuesTransformer(knownValues, this).transform(this, visitedNodes);
    }

    protected T collapseUnrequiredForLoops(Set<TransTree<?>> visitedNodes) {
        return new CollapseUnrequiredForLoopsTransformer(getVariableTracking()).transform(this, visitedNodes);
    }

    protected T moveConstraintsOut(Set<TransTree<?>> visitedNodes) {
        ConstraintMappingVisitor v = new ConstraintMappingVisitor(this);
        v.visit(this);
        return new MoveConstraintsOutTransformer(v.mapping).transform(this, visitedNodes);
    }

    protected T optimizeConditionals(Set<TransTree<?>> visitedNodes) {
        return new OptimizeConditionals(this).transform(this, visitedNodes);
    }

    // TODO use reflection in tests and make this protected again.
    public T applyConstraints(Set<TransTree<?>> visitedNodes) {
        return new ApplyConstraintsTransformer(this).transform(this, visitedNodes);
    }

    protected T removeUnusedScopes(Set<TransTree<?>> visitedNodes) {
        return new RemoveUnusedScopesTransformer().transform(this, visitedNodes);
    }

    protected T unrollLoops(Set<TransTree<?>> visitedNodes) {
        return new LoopUnrollingTransformer().transform(this, visitedNodes);
    }

    public T applyTransformation(Transformer t) {
        t.visitedNodes.add(this);
        return applyTransformationInternal(t);
    }

    protected abstract T applyTransformationInternal(Transformer t);

    public abstract OutputTree toOutputTreeInternal();

    public OutputTree toOutputTree(ExecutionType target) {
        switch(target) {
            case SingleThreadCPU:
                return toOutputTreeInternal();
            case MultiThreadCPU:
                T tree = new ParallelIndexes().transform(this, new HashSet<>());
                tree = new ParFor(tree.getVariableTracking()).transform(tree, new HashSet<>());
                return tree.toOutputTreeInternal();
            case GPU:
            default:
                throw new CompilerException("Unable to transform for target: " + target);
        }
    }

    @Override
    public abstract TransTree<?>[] getChildren();

    /**
     * A function that copies a tree.
     */
    public T copy(Set<TransTree<?>> visitedNodes) {
        Transformer t = new CopyTransformer();
        return t.transform(this, visitedNodes);
    }

    public T copy() {
        return copy(new HashSet<>());
    }

    @Override
    public String toString() {
        return toOutputTreeInternal().toString();
    }

    public void addNodes(Set<TransTree<?>> visitedNodes) {
        NodeEnumerator n = new NodeEnumerator(visitedNodes);
        n.visit(this);
    }

    // Factory Methods
    public static <X extends Variable<X>> TransArrayGet<X> arrayGet(TransTreeReturn<ArrayVariable<X>> array,
            TransTreeReturn<IntVariable> index) {
        return new TransArrayGet<>(array, index);
    }

    public static <X extends Variable<X>> TransArrayPut<X> arrayPut(TransTreeReturn<ArrayVariable<X>> array,
            TransTreeReturn<IntVariable> index, TransTreeReturn<X> value, String comment) {
        return new TransArrayPut<>(array, index, value, comment);
    }

    public static TransConstInt constant(int i) {
        return new TransConstInt(i);
    }

    public static TransConstDouble constant(double d) {
        return new TransConstDouble(d);
    }

    public static TransConstBoolean constant(boolean b) {
        return new TransConstBoolean(b);
    }

    public static TransTreeVoid forStmt(TransTreeVoid body, TransTreeReturn<IntVariable> start,
            TransTreeReturn<IntVariable> end, TransTreeReturn<IntVariable> step,
            VariableDescription<IntVariable> indexDesc, boolean parallel, boolean incrementing, String comment) {
        return TransFor.getFor(body, start, end, step, indexDesc, parallel, incrementing, comment);
    }

    public static TransRVFunctionCall functionCall(FunctionType t, RandomVariableType<?, ?> source,
            RandomVariableType<?, ?> sink, TransTreeReturn<?>[] args, String comment) {
        return TransRVFunctionCall.getTransRVFunctionCall(t, source, sink, args, comment);
    }

    public static TransLocalFunctionCall functionCall(FunctionName name, TransTreeReturn<?>[] args, String comment) {
        return TransLocalFunctionCall.getTransLocalFunctionCall(name, args, comment);
    }

    public static <X extends Variable<X>> TransRVFunctionCallReturn<X> functionCallReturn(FunctionType t,
            VariableType.Type<X> outputType, RandomVariableType<?, ?> source, RandomVariableType<?, ?> sink,
            TransTreeReturn<?>... args) {
        return new TransRVFunctionCallReturn<>(t, outputType, source, sink, args);
    }

    public static <X extends Variable<X>> TransExternalFunctionCallReturn<X> functionCallReturn(ExternalFunction func,
            VariableType.Type<X> outputType, TransTreeReturn<?>... args) {
        return new TransExternalFunctionCallReturn<X>(func, outputType, args);
    }

    public static <X extends Variable<X>> TransLocalFunctionCallReturn<X> functionCallReturn(
            VariableType.Type<X> outputType, String name, TransTreeReturn<?>... args) {
        return new TransLocalFunctionCallReturn<>(outputType, name, args);
    }

    public static TransGetIntField getIntField(TransTreeReturn<?> tree, String name) {
        return new TransGetIntField(tree, name);
    }

    public static TransTreeVoid ifElse(TransTreeReturn<BooleanVariable> condition, TransTreeVoid ifBody, String comment,
            Set<Tag> tags) {
        return TransIfElse.getIfElse(condition, ifBody, comment, tags);
    }

    public static TransTreeVoid ifElse(TransTreeReturn<BooleanVariable> condition, TransTreeVoid ifBody,
            String ifComment, TransTreeVoid elseBody, String elseComment, Set<Tag> tags) {
        return TransIfElse.getIfElse(condition, ifBody, ifComment, elseBody, elseComment, tags);
    }

    public static <X extends Variable<X>> TransConditionalAssignment<X> conditionalAssignment(
            TransTreeReturn<BooleanVariable> condition, TransTreeReturn<X> ifValue, TransTreeReturn<X> elseValue,
            Set<Tag> tags) {
        return TransConditionalAssignment.getConditionalAssignment(condition, ifValue, elseValue, tags);
    }

    public static <X extends Variable<X>> TransConditionalAssignment<X> conditionalAssignment(
            TransTreeReturn<BooleanVariable> condition, TransTreeReturn<X> ifValue, TransTreeReturn<X> elseValue) {
        return TransConditionalAssignment.getConditionalAssignment(condition, ifValue, elseValue,
                Collections.emptySet());
    }

    public static <X extends Variable<X>> TransInitialize<X> initializeVariable(Visibility visibility,
            VariableDescription<X> varDesc, TransTreeReturn<X> value, String comment) {
        return new TransInitialize<>(visibility, varDesc, value, comment);
    }

    public static <X extends Variable<X>> TransInitialize<X> initializeVariable(VariableDescription<X> varDesc,
            TransTreeReturn<X> value, String comment) {
        return initializeVariable(Visibility.DEFAULT, varDesc, value, comment);
    }

    public static <X extends Variable<X>> TransInitializeUnset<X> initializeUnsetVariable(Visibility visibility,
            VariableDescription<X> varDesc, String comment) {
        return new TransInitializeUnset<>(visibility, varDesc, comment);
    }

    public static <X extends Variable<X>> TransInitializeUnset<X> initializeUnsetVariable(
            VariableDescription<X> varDesc, String comment) {
        return initializeUnsetVariable(Visibility.DEFAULT, varDesc, comment);
    }

    public static <X extends Variable<X>> TransLoad<X> load(VariableDescription<X> varDesc) {
        return new TransLoad<>(varDesc);
    }

    public static <X extends Variable<X>> TransNewArray<X> newArray(List<TransTreeReturn<IntVariable>> lengths,
            ArrayType<X> type) {
        return new TransNewArray<>(lengths, type);
    }

    public static TransTreeVoid sequential(List<TransTreeVoid> trees, String comment) {
        return TransSequential.getTransSequential(trees, comment);
    }

    public static TransTreeVoid sequential(TransTreeVoid[] trees, String comment) {
        return TransSequential.getTransSequential(trees, comment);
    }

    public static TransTreeVoid sequential(TransTreeVoid... trees) {
        return TransSequential.getTransSequential(trees, Tree.NoComment);
    }

    public static <X extends Variable<X>> TransStore<X> store(VariableDescription<X> varDesc, TransTreeReturn<X> value,
            String comment) {
        return new TransStore<>(varDesc, value, comment);
    }

    public static TransTreeVoid treeScope(TransTreeVoid tree, String comment) {
        return TransTreeScope.getScope(tree, comment);
    }

    public static TransVoidFunction voidFunction(Visibility visibility, FunctionName name, ArgDesc<?>[] args,
            TransTreeVoid body, boolean override, String comment, KnownValuesTrans knownValues) {
        return new TransVoidFunction(visibility, name, args, body, override, comment, knownValues);
    }

    public static TransTreeReturn<DoubleVariable> castToDouble(TransTreeReturn<IntVariable> toCast) {
        return TransCastToDouble.getCastToDouble(toCast);
    }

    public static TransTreeReturn<IntVariable> castToInteger(TransTreeReturn<DoubleVariable> toCast) {
        return TransCastToInteger.getCastToInteger(toCast);
    }

    public static TransTreeReturn<BooleanVariable> negateBoolean(TransTreeReturn<BooleanVariable> toNegate) {
        return TransNegateBoolean.getNegate(toNegate);
    }

    public static <V extends NumberVariable<V>> TransTreeReturn<V> negate(TransTreeReturn<V> toNegate) {
        return TransNegate.getNegate(toNegate);
    }

    public static TransAdd<DoubleVariable, DoubleVariable, DoubleVariable> addDD(TransTreeReturn<DoubleVariable> a,
            TransTreeReturn<DoubleVariable> b) {
        return TransAdd.getAddDD(a, b);
    }

    public static TransAdd<DoubleVariable, IntVariable, DoubleVariable> addDI(TransTreeReturn<DoubleVariable> a,
            TransTreeReturn<IntVariable> b) {
        return TransAdd.getAddDI(a, b);
    }

    public static TransAdd<IntVariable, DoubleVariable, DoubleVariable> addID(TransTreeReturn<IntVariable> a,
            TransTreeReturn<DoubleVariable> b) {
        return TransAdd.getAddID(a, b);
    }

    public static TransAdd<IntVariable, IntVariable, IntVariable> addII(TransTreeReturn<IntVariable> a,
            TransTreeReturn<IntVariable> b) {
        return TransAdd.getAddII(a, b);
    }

    public static TransSubtract<DoubleVariable, DoubleVariable, DoubleVariable> subtractDD(
            TransTreeReturn<DoubleVariable> a, TransTreeReturn<DoubleVariable> b) {
        return TransSubtract.getSubtractDD(a, b);
    }

    public static TransSubtract<DoubleVariable, IntVariable, DoubleVariable> subtractDI(
            TransTreeReturn<DoubleVariable> a, TransTreeReturn<IntVariable> b) {
        return TransSubtract.getSubtractDI(a, b);
    }

    public static TransSubtract<IntVariable, DoubleVariable, DoubleVariable> subtractID(TransTreeReturn<IntVariable> a,
            TransTreeReturn<DoubleVariable> b) {
        return TransSubtract.getSubtractID(a, b);
    }

    public static TransSubtract<IntVariable, IntVariable, IntVariable> subtractII(TransTreeReturn<IntVariable> a,
            TransTreeReturn<IntVariable> b) {
        return TransSubtract.getSubtractII(a, b);
    }

    public static TransMultiply<DoubleVariable, DoubleVariable, DoubleVariable> multiplyDD(
            TransTreeReturn<DoubleVariable> a, TransTreeReturn<DoubleVariable> b) {
        return TransMultiply.getMultiplyDD(a, b);
    }

    public static TransMultiply<DoubleVariable, IntVariable, DoubleVariable> multiplyDI(
            TransTreeReturn<DoubleVariable> a, TransTreeReturn<IntVariable> b) {
        return TransMultiply.getMultiplyDI(a, b);
    }

    public static TransMultiply<IntVariable, DoubleVariable, DoubleVariable> multiplyID(TransTreeReturn<IntVariable> a,
            TransTreeReturn<DoubleVariable> b) {
        return TransMultiply.getMultiplyID(a, b);
    }

    public static TransMultiply<IntVariable, IntVariable, IntVariable> multiplyII(TransTreeReturn<IntVariable> a,
            TransTreeReturn<IntVariable> b) {
        return TransMultiply.getMultiplyII(a, b);
    }

    public static TransDivide<DoubleVariable, DoubleVariable, DoubleVariable> divideDD(
            TransTreeReturn<DoubleVariable> a, TransTreeReturn<DoubleVariable> b) {
        return TransDivide.getDivideDD(a, b);
    }

    public static TransDivide<DoubleVariable, IntVariable, DoubleVariable> divideDI(TransTreeReturn<DoubleVariable> a,
            TransTreeReturn<IntVariable> b) {
        return TransDivide.getDivideDI(a, b);
    }

    public static TransDivide<IntVariable, DoubleVariable, DoubleVariable> divideID(TransTreeReturn<IntVariable> a,
            TransTreeReturn<DoubleVariable> b) {
        return TransDivide.getDivideID(a, b);
    }

    public static TransDivide<IntVariable, IntVariable, IntVariable> divideII(TransTreeReturn<IntVariable> a,
            TransTreeReturn<IntVariable> b) {
        return TransDivide.getDivideII(a, b);
    }

    public static TransAdd<? extends NumberVariable<?>, ? extends NumberVariable<?>, ? extends NumberVariable<?>> add(
            TransTreeReturn<? extends NumberVariable<?>> a, TransTreeReturn<? extends NumberVariable<?>> b) {
        return TransAdd.getAdd(a, b);
    }

    public static TransSubtract<? extends NumberVariable<?>, ? extends NumberVariable<?>, ? extends NumberVariable<?>> subtract(
            TransTreeReturn<? extends NumberVariable<?>> a, TransTreeReturn<? extends NumberVariable<?>> b) {
        return TransSubtract.getSubtract(a, b);
    }

    public static TransMultiply<? extends NumberVariable<?>, ? extends NumberVariable<?>, ? extends NumberVariable<?>> multiply(
            TransTreeReturn<? extends NumberVariable<?>> a, TransTreeReturn<? extends NumberVariable<?>> b) {
        return TransMultiply.getMultiply(a, b);
    }

    public static TransDivide<? extends NumberVariable<?>, ? extends NumberVariable<?>, ? extends NumberVariable<?>> divide(
            TransTreeReturn<? extends NumberVariable<?>> a, TransTreeReturn<? extends NumberVariable<?>> b) {
        return TransDivide.getDivide(a, b);
    }

    @SafeVarargs
    public static TransTreeReturn<BooleanVariable> and(TransTreeReturn<BooleanVariable>... a) {
        List<TransTreeReturn<BooleanVariable>> l = new ArrayList<>();
        l.addAll(Arrays.asList(a));
        return and(l);
    }

    @SafeVarargs
    public static TransTreeReturn<BooleanVariable> or(TransTreeReturn<BooleanVariable>... a) {
        List<TransTreeReturn<BooleanVariable>> l = new ArrayList<>();
        l.addAll(Arrays.asList(a));
        return or(l);
    }

    public static <L extends NumberVariable<L>, R extends NumberVariable<R>> TransLessThan<L, R> lessThan(
            TransTreeReturn<L> a, TransTreeReturn<R> b) {
        return TransLessThan.getLessThan(a, b);
    }

    public static <L extends NumberVariable<L>, R extends NumberVariable<R>> TransLessThanEqual<L, R> lessThanEqual(
            TransTreeReturn<L> a, TransTreeReturn<R> b) {
        return TransLessThanEqual.getLessThanEqual(a, b);
    }

    public static <A extends ScalarVariable<A>, B extends ScalarVariable<B>> TransEq<A, B> eq(TransTreeReturn<A> a,
            TransTreeReturn<B> b) {
        return TransEq.getEq(a, b);
    }

    public static TransBinOp<IntVariable, IntVariable, BooleanVariable> greaterThan(TransTreeReturn<IntVariable> a,
            TransTreeReturn<IntVariable> b) {
        return TransLessThan.getLessThan(b, a);
    }

    public static TransBinOp<IntVariable, IntVariable, BooleanVariable> greaterThanEqual(TransTreeReturn<IntVariable> a,
            TransTreeReturn<IntVariable> b) {
        return TransLessThanEqual.getLessThanEqual(b, a);
    }

    public static TransRemainder<? extends NumberVariable<?>, ? extends NumberVariable<?>, ? extends NumberVariable<?>> remainder(
            TransTreeReturn<? extends NumberVariable<?>> a, TransTreeReturn<? extends NumberVariable<?>> b) {
        return TransRemainder.getRemainder(a, b);
    }

    public static TransRemainder<IntVariable, IntVariable, IntVariable> remainderII(TransTreeReturn<IntVariable> a,
            TransTreeReturn<IntVariable> b) {
        return TransRemainder.getRemainderII(a, b);
    }

    public static TransRemainder<IntVariable, DoubleVariable, DoubleVariable> remainderID(
            TransTreeReturn<IntVariable> a, TransTreeReturn<DoubleVariable> b) {
        return TransRemainder.getRemainderID(a, b);
    }

    public static TransRemainder<DoubleVariable, IntVariable, DoubleVariable> remainderDI(
            TransTreeReturn<DoubleVariable> a, TransTreeReturn<IntVariable> b) {
        return TransRemainder.getRemainderDI(a, b);
    }

    public static TransRemainder<DoubleVariable, DoubleVariable, DoubleVariable> remainderDD(
            TransTreeReturn<DoubleVariable> a, TransTreeReturn<DoubleVariable> b) {
        return TransRemainder.getRemainderDD(a, b);
    }

    public static <A extends NumberVariable<A>> TransMax<A> max(TransTreeReturn<A> a, TransTreeReturn<A> b) {
        return TransMax.max(a, b);
    }

    public static <A extends NumberVariable<A>> TransMin<A> min(TransTreeReturn<A> a, TransTreeReturn<A> b) {
        return TransMin.min(a, b);
    }

    public static TransNOP nop() {
        return TransNOP.nop;
    }

    public static TransTreeReturn<BooleanVariable> and(List<TransTreeReturn<BooleanVariable>> constraints) {
        return TransAnd.getTransAnd(constraints);
    }

    public static TransTreeReturn<BooleanVariable> or(List<TransTreeReturn<BooleanVariable>> constraints) {
        return TransOr.getTransOr(constraints);
    }

    public static <A extends Variable<A>> TransReturnFunction<A> returnFunction(Visibility visibility,
            Type<A> returnType, FunctionName name, ArgDesc<?>[] args, TransTreeReturn<A> body, boolean override,
            String comment, KnownValuesTrans knownValues) {
        return new TransReturnFunction<>(visibility, returnType, name, args, body, override, comment, knownValues);
    }

    public static TransConstructorFunction constructorFunction(Visibility visibility, FunctionName name,
            ArgDesc<?>[] args, TransTreeVoid body, String comment) {
        return new TransConstructorFunction(visibility, name, args, body, comment);
    }
}
