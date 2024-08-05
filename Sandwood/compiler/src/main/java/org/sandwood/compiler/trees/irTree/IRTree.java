/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.sandwood.compiler.names.ClassName;
import org.sandwood.compiler.names.FunctionName;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.Visibility;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.irTree.util.KnownValuesIR;
import org.sandwood.compiler.trees.transformationTree.TransTree;

public abstract class IRTree extends Tree<IRTree> {
    public interface TreeVisitor {
        void visit(IRTree tree);
    }

    protected static class VariableTracking {
        private final Map<IRTree, Set<String>> inScopeVars = new HashMap<>();
        private final Map<IRTree, Set<String>> usedVars = new HashMap<>();

        public void addTree(IRTree tree, Set<String> inScope, Set<String> used) {
            // TODO this could be improved on by having a mapping of sets to sets, so we
            // only have
            // one instance of each set containing a given set of variables.
            // This will only be an issue if this data structure starts to get too big.
            inScopeVars.put(tree, inScope);
            usedVars.put(tree, used);
        }

        public Set<String> inScopeVars(IRTree tree) {
            return inScopeVars.get(tree);
        }

        public Set<String> usedVars(IRTree tree) {
            return usedVars.get(tree);
        }

        public String getVariableName(String name, IRTree t) {
            return name + "-" + t.id;
        }
    }

    protected static class ScopedVariableTracking {
        private final ScopedVariableTracking parent;
        private final Map<String, String> variables = new HashMap<>();
        private Set<String> scopedNamesCache = null; // A cache to prevent multiple duplicates being constructed

        public ScopedVariableTracking() {
            parent = null;
        }

        public ScopedVariableTracking(ScopedVariableTracking scope) {
            parent = scope;
        }

        public void addVar(String name, IRTree t) {
            variables.put(name, t.id.toString());
            scopedNamesCache = null; // This is out of date now and can no longer be used.
        }

        public String getVariableName(String name) {
            String result = variables.get(name);
            if(result == null)
                if(parent == null)
                    return null;
                else
                    return parent.getVariableName(name);
            else
                return name + "-" + result;
        }

        // Method to get the scoped names complete with flags saying where they were
        // created.
        public Set<String> getInScopeVariables() {
            if(scopedNamesCache == null) {
                Set<String> inScopeNames = new HashSet<>();
                for(String name:getScopedNames()) {
                    String scopeName = getVariableName(name);
                    if(scopeName != null)
                        inScopeNames.add(scopeName);
                }
                scopedNamesCache = inScopeNames;
                return inScopeNames;
            } else
                return scopedNamesCache;
        }

        // Method to get the names without a flag saying which tree created them.
        private Set<String> getScopedNames() {
            if(parent == null)
                return new HashSet<>(variables.keySet());
            else {
                Set<String> scopedNames = parent.getScopedNames();
                scopedNames.addAll(variables.keySet());
                return scopedNames;
            }
        }
    }

    public enum IRTreeType {
        ALLOCATE_ARRAY,
        ARRAY_GET,
        ARRAY_PUT,
        BINOP,
        CAST_DOUBLE,
        CAST_INT,
        CONDITIONAL_ASSIGNMENT,
        CONST_BOOLEAN,
        CONST_DOUBLE,
        CONST_INT,
        CONSTANT_INFERENCE_CONSTRAINT,
        EXP,
        FOR,
        FUNCTION_CALL,
        FUNCTION_CALL_RETURN,
        GET_FIELD,
        IF_ELSE,
        INITIALIZE,
        INITIALIZE_UNSET,
        LOAD,
        LOG,
        MAX,
        MIN,
        NEGATE,
        NOP,
        PROXY_SEQ,
        SCOPE,
        SEQUENTIAL,
        STORE
    }

    public final IRTreeType type;

    protected IRTree(IRTreeType type) {
        super();
        this.type = type;
    }

    public abstract void traverseTree(TreeVisitor v);

    public abstract IRTree applyTransformation(TreeTransformation t);

    public abstract TransTree<?> toTransformationTree();

    @Override
    public abstract IRTree[] getChildren();

    @Override
    public final boolean equals(Object other) {
        return this == other;
    }

    @Override
    public final int hashCode() {
        return id.tag;
    }

    /**
     * A function that copies a tree and returns a map from every old node to every new node. Sets are used in case the
     * same tree is used twice. This is required as to keep scopes happy for multiple calls it is to copy the tree, but
     * for some patterns such as reduce the same tree is used twice. The alternative approach would be for scope
     * descriptions to contain an array of values to add to. Overall this approach is simpler.
     */
    public Map<IRTree, List<IRTree>> copy() {
        Map<IRTree, List<IRTree>> result = new HashMap<>();
        copy(result);
        return result;
    }

    protected final IRTree copy(final Map<IRTree, List<IRTree>> results) {
        IRTree t2 = applyTransformation(new TreeTransformation() {

            @Override
            public IRTreeVoid transform(IRTreeVoid tree) {
                IRTreeVoid t2 = tree.applyTransformation(this);
                List<IRTree> s = results.computeIfAbsent(tree, k -> new ArrayList<>());
                s.add(t2);
                return t2;
            }

            @Override
            public <X extends Variable<X>> IRTreeReturn<X> transformReturn(IRTreeReturn<X> tree) {
                IRTreeReturn<X> t2 = tree.applyTransformation(this);
                List<IRTree> s = results.computeIfAbsent(tree, k -> new ArrayList<>());
                s.add(t2);
                return t2;
            }
        });

        List<IRTree> s = results.computeIfAbsent(this, k -> new ArrayList<>());
        s.add(t2);
        return t2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toTransformationTree().toOutputTree(ExecutionType.SingleThreadCPU).toJava(sb, new HashSet<>());
        return sb.toString();
    }

    // Factory Methods
    public static <X extends Variable<X>> IRArrayGet<X> arrayGet(IRTreeReturn<ArrayVariable<X>> array,
            IRTreeReturn<IntVariable> index) {
        return new IRArrayGet<>(array, index);
    }

    public static <X extends Variable<X>> IRArrayPut<X> arrayPut(IRTreeReturn<ArrayVariable<X>> array,
            IRTreeReturn<IntVariable> index, IRTreeReturn<X> value, String comment) {
        return new IRArrayPut<>(array, index, value, comment);
    }

    public static IRConstInt constant(int i) {
        return new IRConstInt(i);
    }

    public static IRConstDouble constant(double d) {
        return new IRConstDouble(d);
    }

    public static IRConstBoolean constant(boolean b) {
        return new IRConstBoolean(b);
    }

    public static IRFor forStmt(IRTreeVoid body, IRTreeReturn<IntVariable> start, IRTreeReturn<IntVariable> end,
            IRTreeReturn<IntVariable> step, VariableDescription<IntVariable> indexDesc, boolean incrementing,
            String comment) {
        return new IRFor(body, start, end, step, indexDesc, false, incrementing, comment);
    }

    public static IRFor parallelForStmt(IRTreeVoid body, IRTreeReturn<IntVariable> start, IRTreeReturn<IntVariable> end,
            IRTreeReturn<IntVariable> step, VariableDescription<IntVariable> indexDesc, boolean incrementing,
            String comment) {
        return new IRFor(body, start, end, step, indexDesc, true, incrementing, comment);
    }

    public static IRFunctionCall functionCall(FunctionName name, String comment, IRTreeReturn<?>... args) {
        return new IRFunctionCall(name, args, comment);
    }

    public static <X extends Variable<X>> IRRVFunctionCall functionCall(FunctionType t, RandomVariableType<?, ?> source,
            String comment, List<IRTreeReturn<?>> args) {
        return functionCall(t, source, null, comment, args.toArray(new IRTreeReturn<?>[args.size()]));
    }

    public static <X extends Variable<X>> IRRVFunctionCall functionCall(FunctionType t, RandomVariableType<?, ?> source,
            String comment, IRTreeReturn<?>... args) {
        return functionCall(t, source, null, comment, args);
    }

    public static IRRVFunctionCall functionCall(FunctionType t, RandomVariableType<?, ?> source,
            RandomVariableType<?, ?> sink, String comment, IRTreeReturn<?>... args) {
        if(t == FunctionType.CONJUGATE_SAMPLE || t == FunctionType.SAMPLE)
            args = addRng(args);
        return new IRRVFunctionCall(t, source, sink, args, comment);
    }

    public static <X extends Variable<X>> IRRVFunctionCallReturn<X> functionCallReturn(FunctionType t,
            Type<X> outputType, RandomVariableType<?, ?> source, IRTreeReturn<?>... args) {
        return functionCallReturn(t, outputType, source, null, args);
    }

    public static <X extends Variable<X>> IRRVFunctionCallReturn<X> functionCallReturn(FunctionType t,
            Type<X> outputType, RandomVariableType<?, ?> source, List<IRTreeReturn<?>> args) {
        return functionCallReturn(t, outputType, source, null, args.toArray(new IRTreeReturn<?>[args.size()]));
    }

    public static <X extends Variable<X>> IRRVFunctionCallReturn<X> functionCallReturn(FunctionType t,
            Type<X> outputType, RandomVariableType<?, ?> source, RandomVariableType<?, ?> sink,
            IRTreeReturn<?>... args) {
        if(t == FunctionType.CONJUGATE_SAMPLE || t == FunctionType.SAMPLE)
            args = addRng(args);
        return new IRRVFunctionCallReturn<>(t, outputType, source, sink, args);
    }

    public static <X extends Variable<X>> IRFunctionCallReturn<X> functionCallReturn(ExternalFunction func,
            Type<X> outputType, IRTreeReturn<?>... args) {
        return new IRFunctionCallReturn<>(func, outputType, args);
    }

    // TODO push this back into the calling classes?
    private static IRTreeReturn<?>[] addRng(IRTreeReturn<?>[] args) {
        IRTreeReturn<?>[] newArgs = new IRTreeReturn<?>[args.length + 1];
        newArgs[0] = IRTree.load(VariableNames.rngName(0));
        System.arraycopy(args, 0, newArgs, 1, args.length);
        return newArgs;
    }

    public static <X extends Variable<X>> IRLocalFunctionCallReturn<X> functionCallReturn(
            VariableType.Type<X> outputType, String name, IRTreeReturn<?>... args) {
        return new IRLocalFunctionCallReturn<>(outputType, name, args);
    }

    public static IRGetIntField getIntField(IRTreeReturn<?> tree, String name) {
        return new IRGetIntField(tree, name);
    }

    public static <X extends Variable<X>> IRInitialize<X> initializeVariable(Visibility visibility,
            VariableDescription<X> varDesc, IRTreeReturn<X> value, String comment) {
        return new IRInitialize<>(visibility, varDesc, value, comment);
    }

    public static <X extends Variable<X>> IRInitialize<X> initializeVariable(Variable<X> var, IRTreeReturn<X> value,
            String comment) {
        return initializeVariable(var.getUniqueVarDesc(), value, comment);
    }

    public static <X extends Variable<X>> IRInitialize<X> initializeVariable(VariableDescription<X> varDesc,
            IRTreeReturn<X> value, String comment) {
        return initializeVariable(Visibility.DEFAULT, varDesc, value, comment);
    }

    public static <X extends Variable<X>> IRInitializeUnset<X> initializeUnsetVariable(Visibility visibility,
            VariableDescription<X> varDesc, String comment) {
        return new IRInitializeUnset<>(visibility, varDesc, comment);
    }

    public static <X extends Variable<X>> IRInitializeUnset<X> initializeUnsetVariable(VariableDescription<X> varDesc,
            String comment) {
        return initializeUnsetVariable(Visibility.DEFAULT, varDesc, comment);
    }

    public static <X extends Variable<X>> IRLoad<X> load(Variable<X> v) {
        return new IRLoad<>(v.getUniqueVarDesc());
    }

    /**
     * Method for loading synthetic values that don't exist in the DAG. The negative scope id signals that this is the
     * case.
     * 
     * @param varDesc A variable description describing the variable the tree should load.
     * @return A load node to load the described variable.
     */
    public static <X extends Variable<X>> IRLoad<X> load(VariableDescription<X> varDesc) {
        return new IRLoad<>(varDesc);
    }

    public static <X extends Variable<X>> IRNewArray<X> newArray(IRTreeReturn<IntVariable> length,
            Type<ArrayVariable<X>> type) {
        return new IRNewArray<>(length, (ArrayType<X>) type);
    }

    public static <X extends Variable<X>> IRNewArray<X> newArray(List<IRTreeReturn<IntVariable>> lengths,
            ArrayType<X> type) {
        return new IRNewArray<>(lengths, type);
    }

    public static IRProxyTreeSeq proxyTree(String comment) {
        return new IRProxyTreeSeq(comment);
    }

    public static IRSequential sequential(List<IRTreeVoid> trees, String comment) {
        return new IRSequential(trees.toArray(new IRTreeVoid[trees.size()]), comment);
    }

    public static IRSequential sequential(String comment, IRTreeVoid... trees) {
        return new IRSequential(trees, comment);
    }

    public static IRSequential sequential(IRTreeVoid[] trees, String comment) {
        return new IRSequential(trees, comment);
    }

    public static <X extends Variable<X>> IRStore<X> store(VariableDescription<X> varDesc, IRTreeReturn<X> value,
            String comment) {
        return new IRStore<>(varDesc, value, comment);
    }

    public static IRTreeScope treeScope(IRTreeVoid tree, String comment) {
        return new IRTreeScope(tree, comment);
    }

    public static IRVoidFunction voidFunction(Visibility visibility, FunctionName name, ArgDesc<?>[] args,
            IRTreeVoid body, boolean override, String comment) {
        return voidFunction(visibility, name, args, body, override, comment, KnownValuesIR.constructKnownValues());
    }

    public static IRVoidFunction voidFunction(Visibility visibility, FunctionName name, ArgDesc<?>[] args,
            IRTreeVoid body, String comment) {
        return voidFunction(visibility, name, args, body, false, comment, KnownValuesIR.constructKnownValues());
    }

    public static IRVoidFunction voidFunction(Visibility visibility, FunctionName name, ArgDesc<?>[] args,
            IRTreeVoid body, boolean override, String comment, KnownValuesIR knownValues) {
        return new IRVoidFunction(visibility, name, args, body, override, comment, knownValues);
    }

    public static IRVoidFunction voidFunction(Visibility visibility, FunctionName name, ArgDesc<?>[] args,
            IRTreeVoid body, String comment, KnownValuesIR knownValues) {
        return voidFunction(visibility, name, args, body, false, comment, knownValues);
    }

    public static IRTreeReturn<DoubleVariable> castToDouble(IRTreeReturn<IntVariable> toCast) {
        return IRCastToDouble.getCastToDouble(toCast);
    }

    public static IRCastToInteger castToInteger(IRTreeReturn<DoubleVariable> toCast) {
        return IRCastToInteger.getCastToInteger(toCast);
    }

    public static IRBinOp<DoubleVariable, DoubleVariable, DoubleVariable> addDD(IRTreeReturn<DoubleVariable> a,
            IRTreeReturn<DoubleVariable> b) {
        return IRBinOp.getAddDD(a, b);
    }

    public static IRBinOp<DoubleVariable, IntVariable, DoubleVariable> addDI(IRTreeReturn<DoubleVariable> a,
            IRTreeReturn<IntVariable> b) {
        return IRBinOp.getAddDI(a, b);
    }

    public static IRBinOp<IntVariable, DoubleVariable, DoubleVariable> addID(IRTreeReturn<IntVariable> a,
            IRTreeReturn<DoubleVariable> b) {
        return IRBinOp.getAddID(a, b);
    }

    public static IRBinOp<IntVariable, IntVariable, IntVariable> addII(IRTreeReturn<IntVariable> a,
            IRTreeReturn<IntVariable> b) {
        return IRBinOp.getAddII(a, b);
    }

    public static IRBinOp<DoubleVariable, DoubleVariable, DoubleVariable> subtractDD(IRTreeReturn<DoubleVariable> a,
            IRTreeReturn<DoubleVariable> b) {
        return IRBinOp.getSubtractDD(a, b);
    }

    public static IRBinOp<DoubleVariable, IntVariable, DoubleVariable> subtractDI(IRTreeReturn<DoubleVariable> a,
            IRTreeReturn<IntVariable> b) {
        return IRBinOp.getSubtractDI(a, b);
    }

    public static IRBinOp<IntVariable, DoubleVariable, DoubleVariable> subtractID(IRTreeReturn<IntVariable> a,
            IRTreeReturn<DoubleVariable> b) {
        return IRBinOp.getSubtractID(a, b);
    }

    public static IRBinOp<IntVariable, IntVariable, IntVariable> subtractII(IRTreeReturn<IntVariable> a,
            IRTreeReturn<IntVariable> b) {
        return IRBinOp.getSubtractII(a, b);
    }

    public static IRBinOp<DoubleVariable, DoubleVariable, DoubleVariable> multiplyDD(IRTreeReturn<DoubleVariable> a,
            IRTreeReturn<DoubleVariable> b) {
        return IRBinOp.getMultiplyDD(a, b);
    }

    public static IRBinOp<DoubleVariable, IntVariable, DoubleVariable> multiplyDI(IRTreeReturn<DoubleVariable> a,
            IRTreeReturn<IntVariable> b) {
        return IRBinOp.getMultiplyDI(a, b);
    }

    public static IRBinOp<IntVariable, DoubleVariable, DoubleVariable> multiplyID(IRTreeReturn<IntVariable> a,
            IRTreeReturn<DoubleVariable> b) {
        return IRBinOp.getMultiplyID(a, b);
    }

    public static IRBinOp<IntVariable, IntVariable, IntVariable> multiplyII(IRTreeReturn<IntVariable> a,
            IRTreeReturn<IntVariable> b) {
        return IRBinOp.getMultiplyII(a, b);
    }

    public static IRBinOp<DoubleVariable, DoubleVariable, DoubleVariable> divideDD(IRTreeReturn<DoubleVariable> a,
            IRTreeReturn<DoubleVariable> b) {
        return IRBinOp.getDivideDD(a, b);
    }

    public static IRBinOp<DoubleVariable, IntVariable, DoubleVariable> divideDI(IRTreeReturn<DoubleVariable> a,
            IRTreeReturn<IntVariable> b) {
        return IRBinOp.getDivideDI(a, b);
    }

    public static IRBinOp<IntVariable, DoubleVariable, DoubleVariable> divideID(IRTreeReturn<IntVariable> a,
            IRTreeReturn<DoubleVariable> b) {
        return IRBinOp.getDivideID(a, b);
    }

    public static IRBinOp<IntVariable, IntVariable, IntVariable> divideII(IRTreeReturn<IntVariable> a,
            IRTreeReturn<IntVariable> b) {
        return IRBinOp.getDivideII(a, b);
    }

    public static IRBinOp<BooleanVariable, BooleanVariable, BooleanVariable> and(IRTreeReturn<BooleanVariable> a,
            IRTreeReturn<BooleanVariable> b) {
        return IRBinOp.getAnd(a, b);
    }

    public static IRBinOp<BooleanVariable, BooleanVariable, BooleanVariable> or(IRTreeReturn<BooleanVariable> a,
            IRTreeReturn<BooleanVariable> b) {
        return IRBinOp.getOr(a, b);
    }

    public static <L extends NumberVariable<L>, R extends NumberVariable<R>> IRBinOp<L, R, BooleanVariable> lessThan(
            IRTreeReturn<L> a, IRTreeReturn<R> b) {
        return IRBinOp.getLessThan(a, b);
    }

    public static <L extends NumberVariable<L>, R extends NumberVariable<R>> IRBinOp<L, R, BooleanVariable> lessThanEqual(
            IRTreeReturn<L> a, IRTreeReturn<R> b) {
        return IRBinOp.getLessThanEqual(a, b);
    }

    public static <A extends ScalarVariable<A>, B extends ScalarVariable<B>> IRBinOp<A, B, BooleanVariable> eq(
            IRTreeReturn<A> a, IRTreeReturn<B> b) {
        return IRBinOp.getEq(a, b);
    }

    public static <L extends NumberVariable<L>, R extends NumberVariable<R>> IRBinOp<R, L, BooleanVariable> greaterThan(
            IRTreeReturn<L> a, IRTreeReturn<R> b) {
        return IRBinOp.getGreaterThan(a, b);
    }

    public static <L extends NumberVariable<L>, R extends NumberVariable<R>> IRBinOp<R, L, BooleanVariable> greaterThanEqual(
            IRTreeReturn<L> a, IRTreeReturn<R> b) {
        return IRBinOp.getGreaterThanEqual(a, b);
    }

    public static IRBinOp<IntVariable, IntVariable, IntVariable> remainderII(IRTreeReturn<IntVariable> a,
            IRTreeReturn<IntVariable> b) {
        return IRBinOp.getRemainderII(a, b);
    }

    public static IRBinOp<IntVariable, DoubleVariable, DoubleVariable> remainderID(IRTreeReturn<IntVariable> a,
            IRTreeReturn<DoubleVariable> b) {
        return IRBinOp.getRemainderID(a, b);
    }

    public static IRBinOp<DoubleVariable, IntVariable, DoubleVariable> remainderDI(IRTreeReturn<DoubleVariable> a,
            IRTreeReturn<IntVariable> b) {
        return IRBinOp.getRemainderDI(a, b);
    }

    public static IRBinOp<DoubleVariable, DoubleVariable, DoubleVariable> remainderDD(IRTreeReturn<DoubleVariable> a,
            IRTreeReturn<DoubleVariable> b) {
        return IRBinOp.getRemainderDD(a, b);
    }

    public static <A extends NumberVariable<A>> IRMax<A> max(IRTreeReturn<A> a, IRTreeReturn<A> b) {
        return IRMax.max(a, b);
    }

    public static <A extends NumberVariable<A>> IRMin<A> min(IRTreeReturn<A> a, IRTreeReturn<A> b) {
        return IRMin.min(a, b);
    }

    public static IRNOP nop() {
        return IRNOP.nop;
    }

    public static IRIfElse ifElse(IRTreeReturn<BooleanVariable> condition, IRTreeVoid ifBody, String comment) {
        return new IRIfElse(condition, ifBody, comment);
    }

    public static IRIfElse ifElse(IRTreeReturn<BooleanVariable> condition, IRTreeVoid ifBody, String ifComment,
            IRTreeVoid elseBody, String elseComment) {
        return new IRIfElse(condition, ifBody, ifComment, elseBody, elseComment);
    }

    public static <X extends Variable<X>> IRConditionalAssignment<X> conditionalAssignment(
            IRTreeReturn<BooleanVariable> condition, IRTreeReturn<X> ifValue, IRTreeReturn<X> elseValue) {
        return new IRConditionalAssignment<X>(condition, ifValue, elseValue);
    }

    public static <A extends NumberVariable<A>> IRExp<A> exp(IRTreeReturn<A> input) {
        return IRExp.getExp(input);
    }

    public static <A extends NumberVariable<A>> IRLog<A> log(IRTreeReturn<A> input) {
        return IRLog.getLog(input);
    }

    public static <A extends NumberVariable<A>> IRNegate<A> negate(IRTreeReturn<A> input) {
        return IRNegate.getNegate(input);
    }

    public static IRNegateBoolean negateBoolean(IRTreeReturn<BooleanVariable> input) {
        return IRNegateBoolean.getNegate(input);
    }

    public static IRTreeReturn<BooleanVariable> and(List<IRTreeReturn<BooleanVariable>> constraints) {
        if(constraints.isEmpty())
            return constant(true);
        IRTreeReturn<BooleanVariable> toReturn = constraints.get(0);
        int size = constraints.size();
        for(int i = 1; i < size; i++)
            toReturn = and(toReturn, constraints.get(i));
        return toReturn;
    }

    public static IRTreeReturn<BooleanVariable> or(List<IRTreeReturn<BooleanVariable>> constraints) {
        if(constraints.isEmpty())
            return constant(true);
        IRTreeReturn<BooleanVariable> toReturn = constraints.get(0);
        int size = constraints.size();
        for(int i = 1; i < size; i++)
            toReturn = or(toReturn, constraints.get(i));
        return toReturn;
    }

    public static IRConstructorFunction constructorFunction(Visibility visibility, ClassName className,
            ArgDesc<?>[] args, IRTreeVoid body, String comment) {
        return new IRConstructorFunction(visibility, className, args, body, comment);
    }

    public static <A extends Variable<A>> IRReturnFunction<A> returnFunction(Visibility visibility, Type<A> returnType,
            FunctionName name, ArgDesc<?>[] args, IRTreeReturn<A> body, boolean override, String comment) {
        return returnFunction(visibility, returnType, name, args, body, override, comment,
                KnownValuesIR.constructKnownValues());
    }

    public static <A extends Variable<A>> IRReturnFunction<A> returnFunction(Visibility visibility, Type<A> returnType,
            FunctionName name, ArgDesc<?>[] args, IRTreeReturn<A> body, boolean override, String comment,
            KnownValuesIR knownValues) {
        return new IRReturnFunction<>(visibility, returnType, name, args, body, override, comment, knownValues);
    }
}
