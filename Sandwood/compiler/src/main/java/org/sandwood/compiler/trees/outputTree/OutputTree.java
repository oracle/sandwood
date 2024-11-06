/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.compilation.ExternalFunction;
import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.ArrayType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.RandomVariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.names.FunctionName;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.Visibility;

public abstract class OutputTree extends Tree<OutputTree> {
    public static boolean includeComments = true;

    public final void toJava(StringBuilder sb, Set<String> requiredImports) {
        toJava(sb, 0, requiredImports);
    }

    protected final void toJava(StringBuilder sb, int indent, Set<String> requiredImports) {
        generateComment(sb, indent, comment);
        toJavaInternal(sb, indent, requiredImports);
    }

    protected void generateComment(StringBuilder sb, int indent, String comment) {
        if(comment != Tree.NoComment && includeComments) {
            if(!scopeStart) {
                sb.append("\n");
                addIndent(sb, indent);
            }
            String[] parts = comment.split("\n");
            boolean lastLineEmpty = true;

            // Split out bits that already have a new line.
            for(String part:parts) {
                // Test to make sure we only ever have one empty line in a sequence.
                boolean empty = part.equals("");
                if(!empty || !lastLineEmpty) {
                    lastLineEmpty = empty;

                    sb.append("//");
                    int counter = 2;

                    // For each of these split them into 80 character lengths.
                    String[] words = part.split(" ");
                    for(String word:words) { // If the comment is more than 80 characters long start a new line.
                        if(counter > 80) {
                            sb.append("\n");
                            addIndent(sb, indent);
                            sb.append("//");
                            counter = 2;
                        }

                        sb.append(" " + word);
                        counter += word.length() + 1;
                    }
                    sb.append("\n");
                    addIndent(sb, indent);
                }
            }
        }
    }

    protected void setScopeStart() {
        scopeStart = true;
    }

    protected abstract void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports);

    public enum OutputTreeType {
        ALLOCATE_ARRAY,
        ARRAY_GET,
        ARRAY_PUT,
        BINOP,
        CAST_TO_DOUBLE,
        CAST_TO_INT,
        CONDITIONAL_ASSIGNMENT,
        CONST,
        FOR,
        FORK_JOIN_FOR,
        FUNCTION_CALL,
        FUNCTION_CALL_RETURN,
        GET_FIELD,
        IF,
        INITIALIZE,
        INITIALIZE_UNSET,
        LOAD,
        NEGATE,
        NOP,
        PAR_FOR_LAMBDA,
        SCOPE,
        SEQUENTIAL,
        STORE
    }

    public final OutputTreeType type;
    public final boolean terminal; // Terminal statements don't require termination with a semicolon.
    public final String comment;
    private boolean scopeStart = false;

    protected OutputTree(OutputTreeType type, boolean terminal, String comment) {
        super();
        this.type = type;
        this.terminal = terminal;
        this.comment = comment;
    }

    /**
     * Method to add an indent to a string buffer
     *
     * @param sb     StringBuffer to add the indent to.
     * @param indent Depth of indent to add.
     */
    public static void addIndent(StringBuilder sb, int indent) {
        for(int i = 0; i < indent; i++)
            sb.append("\t");
    }

    @Override
    public abstract OutputTree[] getChildren();

    @Override
    public abstract String getDescription();

    /**
     * A function that copies a tree and returns a map from every old node to every new node. TODO this is not used any
     * more. Remove it?
     */
    public Map<OutputTree, OutputTree> copy() {
        Map<OutputTree, OutputTree> result = new HashMap<>();
        copy(result);
        return result;
    }

    protected abstract OutputTree copy(Map<OutputTree, OutputTree> results);

    @Override
    public final boolean equivalent(OutputTree tree) {
        return equals(tree);
    }

    @Override
    public int equivalentHashCode() {
        return hashCode();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + type.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj) || (getClass() != obj.getClass()))
            return false;
        OutputTree other = (OutputTree) obj;
        return type == other.type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.toJava(sb, new HashSet<>());
        return sb.toString();
    }

    // Factory Methods
    public static <X extends Variable<X>> OutputArrayGet<X> arrayGet(OutputTreeReturn<ArrayVariable<X>> array,
            OutputTreeReturn<IntVariable> index) {
        return new OutputArrayGet<>(array, index);
    }

    public static <X extends Variable<X>> OutputArrayPut<X> arrayPut(OutputTreeReturn<ArrayVariable<X>> array,
            OutputTreeReturn<IntVariable> index, OutputTreeReturn<X> value, String comment) {
        return new OutputArrayPut<>(array, index, value, comment);
    }

    public static OutputConst<IntVariable> constant(int i) {
        return new OutputConst<>(Integer.toString(i));
    }

    public static OutputConst<DoubleVariable> constant(double d) {
        String value;
        if(d == Double.NEGATIVE_INFINITY)
            value = "Double.NEGATIVE_INFINITY";
        else if(d == Double.POSITIVE_INFINITY)
            value = "Double.POSITIVE_INFINITY";
        else if(Double.isNaN(d))
            value = "Double.NaN";
        else
            value = Double.toString(d);
        return new OutputConst<>(value);
    }

    public static OutputConst<BooleanVariable> constant(boolean b) {
        return new OutputConst<>(Boolean.toString(b));
    }

    public static OutputFor forStmt(OutputTree body, OutputTreeReturn<IntVariable> start,
            OutputTreeReturn<IntVariable> end, OutputTreeReturn<IntVariable> step,
            VariableDescription<IntVariable> indexDesc, boolean incrementing, boolean lessThan, String comment) {
        return new OutputFor(body, start, end, step, indexDesc, incrementing, lessThan, comment);
    }

    public static OutputFunctionCall functionCall(FunctionType t, RandomVariableType<?, ?> source,
            RandomVariableType<?, ?> sink, OutputTreeReturn<?>[] args, String comment) {
        return OutputFunctionCall.getFunctionCall(t, source, sink, args, comment);
    }

    public static OutputFunctionCall functionCall(FunctionName name, OutputTreeReturn<?>[] args, String comment) {
        return OutputFunctionCall.getFunctionCall(name, args, comment);
    }

    public static <X extends Variable<X>> OutputFunctionCallReturn<X> functionCallReturn(FunctionType t,
            RandomVariableType<?, ?> source, RandomVariableType<?, ?> sink, OutputTreeReturn<?>... args) {
        return OutputFunctionCallReturn.getFunctionCallReturn(t, source, sink, args);
    }

    public static <X extends Variable<X>> OutputFunctionCallReturn<X> functionCallReturn(ExternalFunction func,
            OutputTreeReturn<?>... args) {
        return OutputFunctionCallReturn.getFunctionCallReturn(func, args);
    }

    public static <X extends Variable<X>> OutputFunctionCallReturn<X> functionCallReturn(String name,
            OutputTreeReturn<?>... args) {
        return OutputFunctionCallReturn.getFunctionCallReturn(name, args);
    }

    public static OutputGetIntField getIntField(OutputTreeReturn<?> tree, String name) {
        return new OutputGetIntField(tree, name);
    }

    public static OutputIfElse ifElse(OutputTreeReturn<BooleanVariable> condition, OutputTree ifBody, String ifComment,
            OutputTree elseBody, String elseComment) {
        return new OutputIfElse(condition, ifBody, ifComment, elseBody, elseComment);
    }

    public static <A extends Variable<A>> OutputInitialize<A> initializeVariable(Visibility visibility, Type<A> type,
            VariableDescription<A> varDesc, OutputTreeReturn<A> value, String comment) {
        return new OutputInitialize<>(visibility, type, varDesc, value, comment);
    }

    public static <A extends Variable<A>> OutputInitialize<A> initializeVariable(Type<A> type,
            VariableDescription<A> varDesc, OutputTreeReturn<A> value, String comment) {
        return new OutputInitialize<>(null, type, varDesc, value, comment);
    }

    public static <B extends Variable<B>> OutputInitializeUnset<B> initializeUnsetVariable(Visibility visibility,
            VariableDescription<B> varDesc, String comment) {
        return new OutputInitializeUnset<>(visibility, varDesc, comment);
    }

    public static <B extends Variable<B>> OutputInitializeUnset<B> initializeUnsetVariable(
            VariableDescription<B> varDesc, String comment) {
        return initializeUnsetVariable(null, varDesc, comment);
    }

    public static <X extends Variable<X>> OutputLoad<X> load(VariableDescription<X> varDesc) {
        return new OutputLoad<>(varDesc);
    }

    public static <X extends Variable<X>> OutputNewArray<X> newArray(List<OutputTreeReturn<IntVariable>> lengths,
            ArrayType<X> type) {
        return new OutputNewArray<>(lengths, type);
    }

    public static OutputSequential sequential(List<OutputTree> trees, String comment) {
        return new OutputSequential(trees.toArray(new OutputTree[trees.size()]), comment);
    }

    public static OutputSequential sequential(OutputTree[] trees, String comment) {
        return new OutputSequential(trees, comment);
    }

    public static <X extends Variable<X>> OutputStore<X> store(VariableDescription<X> varDesc,
            OutputTreeReturn<X> value, String comment) {
        return new OutputStore<>(varDesc, value, comment);
    }

    public static OutputTreeScope treeScope(OutputTree tree, String comment) {
        return new OutputTreeScope(tree, comment);
    }

    public static OutputVoidFunction voidFunction(Visibility visibility, FunctionName name, ArgDesc<?>[] args,
            OutputTree body, boolean override, String comment) {
        return new OutputVoidFunction(visibility, name, args, body, override, comment);
    }

    public static OutputConstructorFunction constructorFunction(Visibility visibility, FunctionName name,
            ArgDesc<?>[] args, OutputTree body, String comment) {
        return new OutputConstructorFunction(visibility, name, args, body, comment);
    }

    public static <A extends Variable<A>> OutputReturnFunction<A> returnFunction(Visibility visibility,
            Type<A> returnType, FunctionName name, ArgDesc<?>[] args, OutputTreeReturn<A> body, boolean override,
            String comment) {
        return new OutputReturnFunction<>(visibility, returnType, name, args, body, override, comment);
    }

    public static OutputCastToDouble castToDouble(OutputTreeReturn<IntVariable> toCast) {
        return OutputCastToDouble.getCastToDouble(toCast);
    }

    public static OutputCastToInteger castToInteger(OutputTreeReturn<DoubleVariable> toCast) {
        return OutputCastToInteger.getCastToInteger(toCast);
    }

    public static <V extends NumberVariable<V>> OutputNegate<V> negate(OutputTreeReturn<V> toNegate) {
        return OutputNegate.getNegate(toNegate);
    }

    public static OutputNegateBoolean negateBoolean(OutputTreeReturn<BooleanVariable> toNegate) {
        return OutputNegateBoolean.getNegateBoolean(toNegate);
    }

    public static <X extends NumberVariable<X>> OutputMax<X> max(OutputTreeReturn<X> left, OutputTreeReturn<X> right) {
        return new OutputMax<>(left, right);
    }

    public static <X extends NumberVariable<X>> OutputMin<X> min(OutputTreeReturn<X> left, OutputTreeReturn<X> right) {
        return new OutputMin<>(left, right);
    }

    public static OutputBinOp<BooleanVariable, BooleanVariable, BooleanVariable> and(
            OutputTreeReturn<BooleanVariable> left, OutputTreeReturn<BooleanVariable> right) {
        return OutputBinOp.and(left, right);
    }

    public static OutputBinOp<BooleanVariable, BooleanVariable, BooleanVariable> or(
            OutputTreeReturn<BooleanVariable> left, OutputTreeReturn<BooleanVariable> right) {
        return OutputBinOp.or(left, right);
    }

    public static OutputNOP nop() {
        return OutputNOP.nop;
    }

    public static OutputTree parForLambda(int parDepth, VariableDescription<IntVariable> startDesc,
            VariableDescription<IntVariable> endDesc, VariableDescription<IntVariable> threadID,
            OutputTree outputTreeInternal) {
        return new OutputParForLambda(parDepth, startDesc, endDesc, threadID, outputTreeInternal);
    }

    public static OutputTree ForkJoinFor(int parDepth, OutputTreeReturn<IntVariable> start,
            OutputTreeReturn<IntVariable> end, OutputTreeReturn<IntVariable> step, OutputTree body, String comment) {
        return new OutputParFor(parDepth, start, end, step, body, comment);
    }

    public static <A extends Variable<A>> OutputConditionalAssignment<A> conditionalAssignment(
            OutputTreeReturn<BooleanVariable> condition, OutputTreeReturn<A> ifValue, OutputTreeReturn<A> elseValue) {
        return new OutputConditionalAssignment<>(condition, ifValue, elseValue);
    }
}
