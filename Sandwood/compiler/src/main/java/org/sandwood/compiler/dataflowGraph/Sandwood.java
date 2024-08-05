/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.CompilationOutput;
import org.sandwood.compiler.SandwoodCompiler;
import org.sandwood.compiler.compilation.APICompile;
import org.sandwood.compiler.compilation.util.CompilationDesc;
import org.sandwood.compiler.dataflowGraph.scopes.ElseScope;
import org.sandwood.compiler.dataflowGraph.scopes.IfScope;
import org.sandwood.compiler.dataflowGraph.scopes.ReductionScope;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ArrayReductionInput;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ForTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.IfElseAssignmentTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.IfElseNumberAssignmentTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.NumberReductionInput;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ParForTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ReductionInput;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ReductionReturnArrayTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ReductionReturnNumberTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ReductionReturnTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableName;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.BooleanType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.NumberType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.exceptions.SandwoodModelException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;

public class Sandwood {
    
    /**
     * Private constructor to prevent this object being instantiated.
     */
    private Sandwood() {}
    
    public interface LoopBody<I extends Variable<I>> {
        void body(I i);
    }

    public interface ReduceBody<A extends Variable<A>> {
        A body(A i, A j);
    }

    public interface IfElseBody {
        void body();
    }

    public interface IfElseAssignmentInt {
        IntVariable body();
    }

    public interface IfElseAssignmentDouble {
        DoubleVariable body();
    }

    public interface IfElseAssignmentBoolean {
        BooleanVariable body();
    }

    public interface IfElseAssignmentArray<A extends Variable<A>> {
        A body();
    }

    public static ForTask forLoop(IntVariable start, IntVariable end, IntVariable step, boolean incrementing,
            LoopBody<IntVariable> function) {
        return forLoop(start, end, step, incrementing, null, function);
    }

    public static ForTask forLoop(IntVariable start, IntVariable end, IntVariable step, boolean incrementing,
            Location location, LoopBody<IntVariable> function) {
        ForTask forLoop = new ForTask(start, end, step, incrementing, location);

        initializeFor(function, forLoop);
        // Added so tools can get hold of the resulting scope.
        return forLoop;
    }

    public static ParForTask parFor(IntVariable start, IntVariable end, IntVariable step, boolean incrementing,
            LoopBody<IntVariable> function) {
        return parFor(start, end, step, incrementing, null, function);
    }

    public static ParForTask parFor(IntVariable start, IntVariable end, IntVariable step, boolean incrementing,
            Location location, LoopBody<IntVariable> function) {
        ParForTask parFor = new ParForTask(start, end, step, incrementing, location);

        initializeFor(function, parFor);
        // Added so tools can get hold of the resulting scope.
        return parFor;
    }

    private static void initializeFor(LoopBody<IntVariable> function, ForTask forLoop) {
        ScopeStack.pushScope(forLoop);

        IntVariable i = IntVariable.intVariable(forLoop);
        i.setScope(forLoop);

        function.body(i);

        ScopeStack.popScope(forLoop);
    }

    public static IfScope ifElse(BooleanVariable guard, IfElseBody ifBody, IfElseBody elseBody) {
        IfScope ifScope = ifScope(guard);
        ScopeStack.pushScope(ifScope);
        ifBody.body();
        ScopeStack.popScope(ifScope);

        ElseScope elseScope = ifScope.elseScope;
        ScopeStack.pushScope(elseScope);
        elseBody.body();
        ScopeStack.popScope(elseScope);

        // Added so tools can get hold of the resulting scope.
        return ifScope;
    }

    @SuppressWarnings("unchecked")
    public static <A extends Variable<A>, B extends NumberVariable<B>> A ifElseAssignment(BooleanVariable guard,
            A ifValue, A elseValue, Location location) {
        Type<A> type = ifValue.getType();
        if(type instanceof VariableType.NumberType) {
            return (A) ((NumberType<B>) type)
                    .getInstance(new IfElseNumberAssignmentTask<>(guard, (B) ifValue, (B) elseValue, location));
        } else if(type instanceof VariableType.BooleanType) {
            return (A) ((BooleanType) type).getInstance(new IfElseAssignmentTask<>(guard, (BooleanVariable) ifValue,
                    (BooleanVariable) elseValue, location));
        } else
            throw new CompilerException("Unexpected type " + type);
    }

    public static IntVariable ifElseLambdaAssignment(BooleanVariable guard, IfElseAssignmentInt ifValueLambda,
            IfElseAssignmentInt elseValueLambda, Location location) {
        IfScope ifScope = ifScope(guard);
        ScopeStack.pushScope(ifScope);
        IntVariable ifValue = ifValueLambda.body();
        ScopeStack.popScope(ifScope);

        ElseScope elseScope = ifScope.elseScope;
        ScopeStack.pushScope(elseScope);
        IntVariable elseValue = elseValueLambda.body();
        ScopeStack.popScope(elseScope);

        return IntVariable.intVariable(new IfElseNumberAssignmentTask<>(guard, ifValue, elseValue, location));
    }

    public static DoubleVariable ifElseLambdaAssignment(BooleanVariable guard, IfElseAssignmentDouble ifValueLambda,
            IfElseAssignmentDouble elseValueLambda, Location location) {
        IfScope ifScope = ifScope(guard);
        ScopeStack.pushScope(ifScope);
        DoubleVariable ifValue = ifValueLambda.body();
        ScopeStack.popScope(ifScope);

        ElseScope elseScope = ifScope.elseScope;
        ScopeStack.pushScope(elseScope);
        DoubleVariable elseValue = elseValueLambda.body();
        ScopeStack.popScope(elseScope);

        return DoubleVariable.doubleVariable(new IfElseNumberAssignmentTask<>(guard, ifValue, elseValue, location));
    }

    public static DoubleVariable ifElseLambdaAssignment(BooleanVariable guard, IfElseAssignmentInt ifValueLambda,
            IfElseAssignmentDouble elseValueLambda, Location location) {
        IfScope ifScope = ifScope(guard);
        ScopeStack.pushScope(ifScope);
        IntVariable ifValue = ifValueLambda.body();
        ScopeStack.popScope(ifScope);

        ElseScope elseScope = ifScope.elseScope;
        ScopeStack.pushScope(elseScope);
        DoubleVariable elseValue = elseValueLambda.body();
        ScopeStack.popScope(elseScope);

        return DoubleVariable.doubleVariable(
                new IfElseNumberAssignmentTask<>(guard, ifValue.castToDouble(location), elseValue, location));
    }

    public static DoubleVariable ifElseLambdaAssignment(BooleanVariable guard, IfElseAssignmentDouble ifValueLambda,
            IfElseAssignmentInt elseValueLambda, Location location) {
        IfScope ifScope = ifScope(guard);
        ScopeStack.pushScope(ifScope);
        DoubleVariable ifValue = ifValueLambda.body();
        ScopeStack.popScope(ifScope);

        ElseScope elseScope = ifScope.elseScope;
        ScopeStack.pushScope(elseScope);
        IntVariable elseValue = elseValueLambda.body();
        ScopeStack.popScope(elseScope);

        return DoubleVariable
                .doubleVariable(new IfElseNumberAssignmentTask<>(guard, ifValue, elseValue.castToDouble(), location));
    }

    public static BooleanVariable ifElseLambdaAssignment(BooleanVariable guard, IfElseAssignmentBoolean ifValueLambda,
            IfElseAssignmentBoolean elseValueLambda, Location location) {
        IfScope ifScope = ifScope(guard);
        ScopeStack.pushScope(ifScope);
        BooleanVariable ifValue = ifValueLambda.body();
        ScopeStack.popScope(ifScope);

        ElseScope elseScope = ifScope.elseScope;
        ScopeStack.pushScope(elseScope);
        BooleanVariable elseValue = elseValueLambda.body();
        ScopeStack.popScope(elseScope);

        return BooleanVariable.booleanVariable(new IfElseAssignmentTask<>(guard, ifValue, elseValue, location));
    }

    public static IfScope ifScope(BooleanVariable guard) {
        return new IfScope(guard);
    }

    public static DoubleVariable reduce(ArrayVariable<DoubleVariable> a, double emptyValue,
            ReduceBody<DoubleVariable> function) {
        return reduce(a, emptyValue, null, function);
    }

    public static DoubleVariable reduce(ArrayVariable<DoubleVariable> a, double emptyValue, Location location,
            ReduceBody<DoubleVariable> function) {
        return reduceNumber(Variable.intVariable(0), a.length(), a, Variable.doubleVariable(emptyValue), location,
                function);
    }

    public static <A extends NumberVariable<A>, B extends NumberVariable<B>> A reduce(ArrayVariable<A> a, B emptyValue,
            ReduceBody<A> function) {
        return reduce(a, emptyValue, null, function);
    }

    public static <A extends NumberVariable<A>, B extends NumberVariable<B>> A reduce(ArrayVariable<A> a, B emptyValue,
            Location location, ReduceBody<A> function) {
        NumberType<A> elementType = (NumberType<A>) a.getElementType();
        NumberType<B> emptyType = emptyValue.getType();
        if(elementType == emptyType)
            return reduceNumber(Variable.intVariable(0, location), a.length(location), a, (A) emptyValue, location,
                    function);
        else if(elementType == VariableType.DoubleVariable && emptyType == VariableType.IntVariable)
            return reduceNumber(Variable.intVariable(0, location), a.length(location), a,
                    (A) ((IntVariable) emptyValue).castToDouble(location), location, function);
        else
            throw new SandwoodModelException("Invalid type combination, " + emptyType.getJavaType()
                    + " not a suitable unit value for an array of type " + elementType.getJavaType() + ".");
    }

    public static IntVariable reduce(ArrayVariable<IntVariable> a, int emptyValue, ReduceBody<IntVariable> function) {
        return reduce(a, emptyValue, null, function);
    }

    public static IntVariable reduce(ArrayVariable<IntVariable> a, int emptyValue, Location location,
            ReduceBody<IntVariable> function) {
        return reduceNumber(Variable.intVariable(0), a.length(), a, Variable.intVariable(emptyValue), location,
                function);
    }

    public static BooleanVariable reduce(ArrayVariable<BooleanVariable> a, boolean emptyValue,
            ReduceBody<BooleanVariable> function) {
        return reduce(a, emptyValue, null, function);
    }

    public static BooleanVariable reduce(ArrayVariable<BooleanVariable> a, boolean emptyValue, Location location,
            ReduceBody<BooleanVariable> function) {
        return reduceBoolean(Variable.intVariable(0), a.length(), a, Variable.booleanVariable(emptyValue), location,
                function);
    }

    public static BooleanVariable reduce(ArrayVariable<BooleanVariable> a, BooleanVariable emptyValue,
            ReduceBody<BooleanVariable> function) {
        return reduce(a, emptyValue, null, function);
    }

    public static BooleanVariable reduce(ArrayVariable<BooleanVariable> a, BooleanVariable emptyValue,
            Location location, ReduceBody<BooleanVariable> function) {
        return reduceBoolean(Variable.intVariable(0), a.length(), a, emptyValue, location, function);
    }

    public static <A extends Variable<A>> ArrayVariable<A> reduce(ArrayVariable<ArrayVariable<A>> a,
            ArrayVariable<A> emptyValue, ReduceBody<ArrayVariable<A>> function) {
        return reduce(a, emptyValue, null, function);
    }

    public static <A extends Variable<A>> ArrayVariable<A> reduce(ArrayVariable<ArrayVariable<A>> a,
            ArrayVariable<A> emptyValue, Location location, ReduceBody<ArrayVariable<A>> function) {
        return reduceArray(Variable.intVariable(0), a.length(), a, emptyValue, location, function);
    }

    private static <A extends Variable<A>> ArrayVariable<A> reduceArray(IntVariable start, IntVariable end,
            ArrayVariable<ArrayVariable<A>> a, ArrayVariable<A> emptyValue, Location location,
            ReduceBody<ArrayVariable<A>> function) {
        ReductionScope<ArrayVariable<A>> reduction = new ReductionScope<>(start, end, a, emptyValue);
        ScopeStack.pushScope(reduction);

        ArrayVariable<A> i = a
                .arrayElementInstance(new ArrayReductionInput<>(start, end, emptyValue, a, true, location));
        reduction.setFirstArg(i);
        ArrayVariable<A> j = a
                .arrayElementInstance(new ArrayReductionInput<>(start, end, emptyValue, a, false, location));
        reduction.setSecondArg(j);

        ArrayVariable<A> r = function.body(i, j);

        ReductionReturnArrayTask<A> returnTask = new ReductionReturnArrayTask<>(r, location);
        r = r.getType().getInstance(returnTask);
        reduction.setReturnVar(r);

        ScopeStack.popScope(reduction);

        r.setScope(reduction);

        return r;
    }

    private static BooleanVariable reduceBoolean(IntVariable start, IntVariable end, ArrayVariable<BooleanVariable> a,
            BooleanVariable emptyValue, Location location, ReduceBody<BooleanVariable> function) {
        ReductionScope<BooleanVariable> reduction = new ReductionScope<>(start, end, a, emptyValue);
        ScopeStack.pushScope(reduction);

        BooleanVariable i = a.booleanElementInstance(new ReductionInput<>(start, end, emptyValue, a, true, location));
        reduction.setFirstArg(i);
        BooleanVariable j = a.booleanElementInstance(new ReductionInput<>(start, end, emptyValue, a, false, location));
        reduction.setSecondArg(j);

        BooleanVariable r = function.body(i, j);

        ScopeStack.popScope(reduction);
        
        ReductionReturnTask<BooleanVariable> returnTask = new ReductionReturnTask<>(r, location);
        r = r.getType().getInstance(returnTask);
        reduction.setReturnVar(r);

        return r;
    }

    private static <A extends NumberVariable<A>> A reduceNumber(IntVariable start, IntVariable end, ArrayVariable<A> a,
            A emptyValue, Location location, ReduceBody<A> function) {
        ReductionScope<A> reduction = new ReductionScope<>(start, end, a, emptyValue);
        ScopeStack.pushScope(reduction);

        A i = a.numberElementInstance(new NumberReductionInput<>(start, end, emptyValue, a, true, location));
        reduction.setFirstArg(i);
        A j = a.numberElementInstance(new NumberReductionInput<>(start, end, emptyValue, a, false, location));
        reduction.setSecondArg(j);

        A r = function.body(i, j);
        
        ScopeStack.popScope(reduction);
        
        ReductionReturnNumberTask<A> returnTask = new ReductionReturnNumberTask<>(r, location);
        r = r.getType().getInstance(returnTask);
        reduction.setReturnVar(r);

        return r;
    }

    /**
     * Factory method for constructing locations to go into the DAG.
     * 
     * @param startLine The start line for this fragment of the model source code.
     * @param startCol  The start column for this fragment of the model source code.
     * @param endLine   The end line for this fragment of the model source code.
     * @param endCol    The end column for this fragment of the model source code.
     * @return A location representing this fragment of the model source code.
     */
    public static Location location(int startLine, int startCol, int endLine, int endCol) {
        return new Location(startLine, startCol, endLine, endCol);
    }

    /**
     * TODO come up with a better way linking compile to the graph, this should include any restrictions of scope that
     * are required to avoid unnecessary computation. This is going to start as a hacky way of compiling a program, with
     * the aim of getting it refactored and then working in a more meaningful way later.
     *
     * @param v A variable in the DAG we want to compile. This can be any variable, but we should think if there is a
     *          better handle we can choose.
     */
    public static CompilationDesc compileAPI(CompilationOptions opts, Variable<?> v, String className,
            String packageName, String[] constructorArgs, String modelCode, String comment) {
        Variable<?>[] vs = new Variable<?>[1];
        vs[0] = v;
        return compileAPI(opts, vs, className, Collections.emptySet(), packageName, constructorArgs, modelCode,
                comment);
    }

    public static CompilationDesc compileAPI(CompilationOptions opts, Variable<?> v, String className,
            Set<String> helperClasses, String packageName, String[] constructorArgs, String modelCode, String comment) {
        Variable<?>[] vs = new Variable<?>[1];
        vs[0] = v;
        return compileAPI(opts, vs, className, helperClasses, packageName, constructorArgs, modelCode, comment);
    }

    public static CompilationDesc compileAPI(CompilationOptions opts, Variable<?>[] vs, String className,
            String packageName, String[] constructorArgs, String modelCode, String comment) {
        return compileAPI(opts, vs, className, Collections.emptySet(), packageName, constructorArgs, modelCode,
                comment);
    }

    public static CompilationDesc compileAPI(CompilationOptions opts, Variable<?>[] vs, String className,
            Set<String> helperClasses, String packageName, String[] constructorArgs, String modelCode, String comment) {
        int noArgs = constructorArgs.length;
        VariableName[] constructorArgNames = new VariableName[noArgs];
        for(int i = 0; i < noArgs; i++)
            constructorArgNames[i] = new VariableName(constructorArgs[i], false);
        return APICompile.compile(opts, vs, className, helperClasses, packageName, constructorArgNames, modelCode,
                comment);
    }

    public static CompilationOutput compileModel(CompilationOptions opts)
            throws SecurityException, IllegalArgumentException, IOException {
        return SandwoodCompiler.compileModel(opts);
    }
}
