/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.arrayVariable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.Sandwood;
import org.sandwood.compiler.dataflowGraph.scopes.ElseScope;
import org.sandwood.compiler.dataflowGraph.StructureVerifier;
import org.sandwood.compiler.dataflowGraph.autoDiff.Differentiable;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.IfScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope.ScopeType;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.ArrayProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.ArrayConstructTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.ConstructArrayInput;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.GetArrayLengthTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.GetArrayTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.GetBooleanTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.GetNumberTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.GetTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.PutTask;
import org.sandwood.compiler.dataflowGraph.tasks.nonReturnTasks.ObserveVariableTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.NamedArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.LocalVariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.ObjectVariable;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableImplementation;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.ArrayType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.BooleanType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.NumberType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.VariableWrapper;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.exceptions.ConstraintAlreadySetException;
import org.sandwood.compiler.exceptions.SandwoodModelException;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class ArrayVariable<A extends Variable<A>> extends VariableImplementation<ArrayVariable<A>>
        implements ObjectVariable<ArrayVariable<A>> {

    public interface ArrayValueInit<V extends Variable<V>> {
        V getValue();
    }

    public static class OuterArrayDesc<B extends Variable<B>> {
        private OuterArrayDesc() {}

        /**
         * The array that contains a reference to this array if it exists, this value is null if this is the outer
         * array.
         */
        ArrayVariable<ArrayVariable<B>> array = null;

        /**
         * The index this array is placed at if this is a subarray, null otherwise.
         */
        IntVariable index = null;

        /**
         * The scope that the outer array was accessed in.
         */
        Scope scope = null;

        /**
         * The get task that the inner array was, or in the case of an array placed in the outer array by a put
         * operation, could, be recovered by.
         */
        GetArrayTask<B> getTask;

        /**
         * Method to get the array that contains a reference to this array.
         * 
         * @return The array that contains a reference to this array, or null if this is not a subarray.
         */
        public ArrayVariable<ArrayVariable<B>> getArray() {
            return array;
        }

        /**
         * Method to get the index value in array that this array is placed at if this is a subarray.
         * 
         * @return The index of the location this array is placed at if this is a subarray, or null if this is not a
         *         subarray.
         */
        public IntVariable getIndex() {
            return index;
        }

        /**
         * Method to get the scope that the subarray was accessed in.
         * 
         * @return The scope of the put operation that placed this array into the outer array, or recovered this array
         *         from the outer array if this is a subarray or null otherwise.
         */
        public Scope getScope() {
            return scope;
        }

        /**
         * 
         * @return
         */
        public GetArrayTask<B> getGetTask() {
            return getTask;
        }
    }

    /**
     * The most recent instance of this variable. This value is only guaranteed correct iff this == instanceHandle
     */
    private ArrayVariable<A> currentInstance;
    /**
     * The original instance of the variable. Current instance is valid in this variable
     */
    private final ArrayVariable<A> instanceHandle;
    /**
     * The instance that was generated when a put was performed on this instance
     */
    private ArrayVariable<A> childInstance = null;

    /** Set of lengths of arrays added to this array */
    private final Set<VariableWrapper<IntVariable>> childLengths = new HashSet<>();

    /**
     * Set of possible lengths of this array. If there is more than one length in here we will have to fall back on the
     * classic array.length code, but it is useful to keep the whole set so a max can be performed over it during
     * allocation if required.
     */
    private final Set<VariableWrapper<IntVariable>> arrayLengths;

    /**
     * A flag to mark if this array is an input and so its value is fixed.
     */
    public final boolean isInput;

    /**
     * An object holding details of the outer array if present.
     */
    private final OuterArrayDesc<A> outerArrayDesc;

    /**
     * Empty array constructor.
     *
     * @param parent The dataflow task that constructed the array.
     */
    private ArrayVariable(ArrayProducingDataflowTask<A> parent) {
        super(parent);
        currentInstance = this;
        instanceHandle = this;
        arrayLengths = parent.getPossibleLengths();
        parentInstance = null;
        outerArrayDesc = new OuterArrayDesc<>();

        switch(parent.getType()) {
            case GET:
                isInput = ((GetTask<?>) parent).array.isInput;
                break;
            case CONSTRUCT_INPUT:
                isInput = true;
                break;
            default:
                isInput = false;
                break;

        }
    }

    /**
     * Constructor for an updated array with length and type instance.
     *
     * @param parent The dataflow task that constructed the array.
     * @param source The variable that this is being created to represent an updated version of.
     */
    private ArrayVariable(ArrayProducingDataflowTask<A> parent, ArrayVariable<A> source) {
        super(parent);
        isInput = source.isInput;
        instanceHandle = source.instanceHandle;
        instanceHandle.updateCurrentInstance(this);
        parentInstance = source;
        alias = source.getAlias();
        comment = source.getComment();
        setScope(source.scope());
        arrayLengths = parent.getPossibleLengths();
        outerArrayDesc = source.outerArrayDesc;

        if(getElementType().getTypeSingleton() == VariableType.Array) {
            switch(parent.getType()) {
                case PUT:
                    Set<VariableWrapper<IntVariable>> newChildLengths = ((ArrayVariable<?>) ((PutTask<A>) parent).value)
                            .getPossibleLengths();
                    addChildLengths(newChildLengths);
                    Scope s = parent.scope();
                    Scope lastIterating = null;
                    while(s != GlobalScope.scope) {
                        if(s.iterating())
                            lastIterating = s;
                        s = s.getEnclosingScope();
                    }
                    if(s != null)
                        source.addChildLengths(newChildLengths, lastIterating);
                    break;
                default:
                    throw new CompilerException("Currently, only puts are allowed to modify arrays.");
            }
        }
    }

    private void addChildLengths(Set<VariableWrapper<IntVariable>> newChildLengths, Scope lastIterating) {
        Scope s = getParent().scope();
        while(s != GlobalScope.scope) {
            if(s == lastIterating) {
                childLengths.addAll(newChildLengths);
                if(parentInstance != null)
                    parentInstance.addChildLengths(newChildLengths, lastIterating);
                return;
            }
            s = s.getEnclosingScope();
        }
    }

    // TODO remove this once the type of parent is generic.
    @Override
    public ArrayProducingDataflowTask<A> getParent() {
        return (ArrayProducingDataflowTask<A>) super.getParent();
    }

    /**
     * Get method
     *
     * @param i position to get
     * @return a new instance of the value held by the array.
     */
    public A get(int i) {
        return get(Variable.intVariable(i), null);
    }

    /**
     * Get method
     *
     * @param i position to get
     * @return a new instance of the value held by the array.
     */
    public A get(int i, Location location) {
        return get(Variable.intVariable(i), location);
    }

    /**
     * Get method
     * 
     * @param <B>
     *
     * @param i   position to get
     * @return a new instance of the value held by the array.
     */
    public <B extends NumberVariable<B>, C extends Variable<C>> A get(IntVariable i) {
        return get(i, null);
    }

    /**
     * Get method
     * 
     * @param <B>
     *
     * @param i   position to get
     * @return a new instance of the value held by the array.
     */
    @SuppressWarnings("unchecked")
    public <B extends NumberVariable<B>, C extends Variable<C>> A get(IntVariable i, Location location) {
        Type<A> t = getElementType();
        if(t.getTypeSingleton() == VariableType.Array) {
            ArrayVariable<ArrayVariable<C>> array = (ArrayVariable<ArrayVariable<C>>) this;
            GetArrayTask<C> getTask = new GetArrayTask<>(array, i, location);
            ArrayVariable<C> a = ((ArrayType<C>) t).getInstance(getTask);
            a.outerArrayDesc.array = array;
            a.outerArrayDesc.index = i;
            a.outerArrayDesc.scope = ScopeStack.getCurrentScope();
            a.outerArrayDesc.getTask = getTask;
            return (A) a;
        } else if(t == VariableType.BooleanVariable)
            return (A) ((BooleanType) t)
                    .getInstance(new GetBooleanTask((ArrayVariable<BooleanVariable>) this, i, location));
        else
            return (A) ((NumberType<B>) t).getInstance(new GetNumberTask<>((ArrayVariable<B>) this, i, location));
    }

    /**
     * Method to get an instance of the type of item held by the array. This is used by methods that will access the
     * array without specific indexing. For example when performing reductions.
     *
     */
    public A booleanElementInstance(ProducingDataflowTask<BooleanVariable> task) {
        Type<A> t = getElementType();
        if(t == VariableType.BooleanVariable)
            return (A) ((BooleanType) t).getInstance(task);
        else
            throw new CompilerException("Cannot get a boolean variable from an array of " + t + ".");
    }

    /**
     * Method to get an instance of the type of item held by the array. This is used by methods that will access the
     * array without specific indexing. For example when performing reductions.
     */
    public <B extends NumberVariable<B>> A numberElementInstance(NumberProducingDataflowTask<B> task) {
        Type<A> t = getElementType();
        if(t instanceof NumberType)
            return (A) ((NumberType<B>) t).getInstance(task);
        else
            throw new CompilerException("Cannot get a number variable from an array of " + t + ".");
    }

    /**
     * Method to get an instance of the type of item held by the array. This is used by methods that will access the
     * array without specific indexing. For example when performing reductions.
     * <p>
     * TODO work out why this is requiring a fully qualified path.
     */
    public <B extends Variable<B>> A arrayElementInstance(ArrayProducingDataflowTask<B> task) {
        Type<A> t = getElementType();
        if(t instanceof ArrayType)
            return (A) ((ArrayType) t).getInstance(task);
        else
            throw new CompilerException("Cannot get an array variable from an array of " + t + ".");
    }

    /**
     * Method to represent a put in the array. This results in a new representation of the variable being placed in
     * put's arguments, and the current instance value of this array being updated. This instance of the array will
     * continue to be used for the rest of the api calls, but if this is working correctly the new value will be placed
     * into the inputs of all subsequent dataflow tasks when they take their arguments.
     * <p>
     * TODO add in constraints so puts cannot be added to observed variables.
     *
     * @param i     Location to put the data in
     * @param value The value to store.
     */
    public <B extends Variable<B>> void put(IntVariable i, B value) {
        put(i, value, false, null);
    }

    /**
     * Method to represent a put in the array. This results in a new representation of the variable being placed in
     * put's arguments, and the current instance value of this array being updated. This instance of the array will
     * continue to be used for the rest of the api calls, but if this is working correctly the new value will be placed
     * into the inputs of all subsequent dataflow tasks when they take their arguments.
     * <p>
     * TODO add in constraints so puts cannot be added to observed variables.
     *
     * @param i     Location to put the data in
     * @param value The value to store.
     */
    public <B extends Variable<B>> void put(IntVariable i, B value, Location location) {
        put(i, value, false, location);
    }

    private <B extends Variable<B>> void put(IntVariable index, B value, boolean implicit, Location location) {
        if(getElementType().equals(value.getType())) {
            typeCheckedPut(index, (A) value, implicit, location);
        } else if(getElementType() == VariableType.DoubleVariable && value.getType() == VariableType.IntVariable) {
            typeCheckedPut(index, (A) ((IntVariable) value).castToDouble(location), implicit, location);
        } else {
            throw new SandwoodModelException("Incompatible types, type " + value.getType().getJavaType()
                    + " cannot be assigned to an array of type " + getType().getJavaType() + ".", location);
        }
    }

    /**
     * The put operation after type checking it.
     * 
     * @param <C>
     * @param index
     * @param value
     * @param implicit
     * @param location
     */
    private <C extends Variable<C>> void typeCheckedPut(IntVariable index, A value, boolean implicit,
            Location location) {
        if(implicit)
            ScopeStack.pushScope(((ArrayVariable<?>) value).outerArrayDesc.scope);
        PutTask<A> putTask = new PutTask<>(this, index, value.getCurrentInstance(), implicit, location);
        ArrayVariable<A> newArray = new ArrayVariable<>(putTask, this);
        if(implicit)
            ScopeStack.popScope(((ArrayVariable<?>) value).outerArrayDesc.scope);

        // If this is a user call to add an array set the arrays outer parameters
        // to the values here. If the values are already set
        if(!implicit && value.getType().isArray()) {
            ArrayVariable<C> arrayValue = (ArrayVariable<C>) value;
            if(arrayValue.outerArrayDesc.array == null) {
                arrayValue.outerArrayDesc.array = (ArrayVariable<ArrayVariable<C>>) instanceHandle();
                arrayValue.outerArrayDesc.index = index;
                arrayValue.outerArrayDesc.scope = ScopeStack.getCurrentScope();
                arrayValue.outerArrayDesc.getTask = new GetArrayTask<>((PutTask<ArrayVariable<C>>) putTask);
            } else {
                // Generate an error message as multiple assignments are not allowed.
                ArrayVariable<A> thisArray = instanceHandle();
                ArrayVariable<A> exisitingOuterArray = (ArrayVariable<A>) arrayValue.outerArrayDesc.array;
                if(thisArray == exisitingOuterArray) {
                    IntVariable exisitingIndex = arrayValue.outerArrayDesc.index;
                    if(index.equivalent(exisitingIndex)) {
                        putTask.assignmentToSameArrayElement();
                    } else {
                        putTask.assignmentToSameArrayDifferntElements();
                    }
                } else
                    putTask.assignmentToMultipleArrays();
            }
        }

        if(outerArrayDesc.array != null)
            outerArrayDesc.array.put(outerArrayDesc.index, newArray, true, location);
    }

    /**
     * Method to represent a put in the array. This results in a new representation of the variable being placed in
     * put's arguments, and the current instance value of this array being updated. This instance of the array will
     * continue to be used for the rest of the api calls, but if this is working correctly the new value will be placed
     * into the inputs of all subsequent dataflow tasks when they take their arguments.
     * <p>
     * TODO add in constraints so puts cannot be added to observed variables.
     *
     * @param i     Location to put the data in
     * @param value The value to store.
     */
    public <B extends Variable<B>> void put(int i, B value) {
        put(Variable.intVariable(i), value);
    }

    /** TODO make observation available to all Variables */
    /**
     * Method to add a constraint through the observation of a variable. This can be used to place values into unset
     * variables in addition to us setting constants
     *
     * @param source The value we are asserting is equal to this value.
     * @throws ConstraintAlreadySetException Currently, variables only take one constraint, this may be relaxed for
     *                                       random variables. There may be other cases as well as we progress.
     */
    public void observe(ArrayVariable<A> source) throws ConstraintAlreadySetException {
        observe(source, null);
    }

    /**
     * Method to add a constraint through the observation of a variable. This can be used to place values into unset
     * variables in addition to us setting constants
     *
     * @param source The value we are asserting is equal to this value.
     * @throws ConstraintAlreadySetException Currently, variables only take one constraint, this may be relaxed for
     *                                       random variables. There may be other cases as well as we progress.
     */
    public void observe(ArrayVariable<A> source, Location location) throws ConstraintAlreadySetException {
        ObserveVariableTask<ArrayVariable<A>> observation = new ObserveVariableTask<>(this, source, location);
        addConstraint(observation);
    }

    /**
     * Method to use
     * 
     * @return
     */
    public IntVariable scopedLength(Location location) {
        Scope currentScope = scope();
        ScopeStack.pushScope(currentScope);
        IntVariable length = length(location);
        ScopeStack.popScope(currentScope);
        return length;
    }

    /**
     * Get the length of the array
     *
     * @return The length of the array.
     */
    public IntVariable length() {
        return length(null);
    }

    public IntVariable length(Location location) {
        return IntVariable.intVariable(new GetArrayLengthTask(this, location));
    }

    public ArrayProducingDataflowTask<?> getSource() {
        ArrayProducingDataflowTask<A> d = instanceHandle().getParent();
        if(d.getType() == DFType.GET)
            return ((GetArrayTask<A>) d).array.getSource();
        else
            return d;
    }

    public Set<VariableWrapper<IntVariable>> getPossibleLengths() {
        if(arrayLengths == null) {
            // Constructing the set before the search protects against loops.
            arrayLengths = new HashSet<>();
            arrayLengths.addAll(getParent().getPossibleLengths());
        }
        return arrayLengths;
    }

    public Set<VariableWrapper<IntVariable>> getPossibleElementLengths(DataflowTask<A> t) {
        Set<VariableWrapper<IntVariable>> elementLengths = new HashSet<>();
        for(PutTask<A> pt:getPuts(t.scope(), t.id())) {
            elementLengths.addAll(((ArrayVariable<?>) pt.value).getPossibleLengths());
        }
        return elementLengths;
    }

    /**
     * Method to gather any other instances of the variable that could be consumed by the consumer. This method is the
     * default for cases where there wouldn't be any, but for arrays it is possible to have a cycle, so they will
     * implement a case that searches just in case.
     * 
     * @param consumerScope
     * @return
     */
    public Set<ArrayVariable<A>> getPossibleInstances(Scope consumerScope, int destinationID) {
        Map<IfScope, ElseScope> conditionalScopes = new HashMap<>();
        {
            Scope s = consumerScope;
            while(s != GlobalScope.scope) {
                if(s.getScopeType() == ScopeType.ELSE) {
                    ElseScope e = (ElseScope) s;
                    conditionalScopes.put(e.ifScope, e);
                }
                s = s.getEnclosingScope();
            }
        }

        Set<ArrayVariable<A>> vars = new HashSet<>();

        // Get any puts that happen after the scope, but could reach it. This goes beyond
        // the arrays returned by possible instances because puts applied to the value
        // of puts could be present.
        ArrayVariable<A> vec = instanceHandle;
        while(vec != null) {
            if(vec.getParent().id() < destinationID) {
                Scope s = vec.scope();
                boolean iterating = false;
                boolean inScope = true;
                ElseScope lastElse = null;
                while(s != GlobalScope.scope) {
                    switch(s.getScopeType()) {
                        case ELSE:
                            lastElse = (ElseScope) s;
                            break;
                        case FOR:
                            iterating = true;
                            break;
                        case IF:
                            ElseScope e = conditionalScopes.get(s);
                            if(e != null)
                                inScope = inScope && e == lastElse;
                            break;
                        default:
                            break;
                    }

                    s = s.getEnclosingScope();
                }

                if(iterating || inScope)
                    vars.add(vec);
                vec = vec.childInstance;
            } else
                break;
        }

        if(vec != null)
            vec.tryChildInstances(consumerScope, vars);
        return vars;
    }

    private void tryChildInstances(Scope consumerScope, Set<ArrayVariable<A>> vars) {
        // Work out if the operations occur in compatible scopes.
        Scope varScope = getParent().scope();
        Set<Scope> knownScopes = new HashSet<>();
        while(varScope != null) {
        	knownScopes.add(varScope);
        	varScope = varScope.getEnclosingScope();
        }

        Scope s = consumerScope;
        while(s != null && !knownScopes.contains(s))
            s = s.getEnclosingScope();

        while(s != null) {
            if(s.iterating()) {
                vars.add(this);

                // If they do try the next child.
                if(childInstance != null)
                    childInstance.tryChildInstances(consumerScope, vars);
                return;
            }
            s = s.getEnclosingScope();
        }
    }

    /**
     * Get the type of the array for text output
     *
     * @return Type of the array as a String
     */
    @Override
    public ArrayType<A> getType() {
        return (ArrayType<A>) getParent().getOutputType();
    }

    /**
     * Get the type of the elements of the array.
     *
     * @return
     */
    public Type<A> getElementType() {
        return getType().getElementType();
    }

    /**
     * Update the reference to the latest instance of this variable
     *
     * @param value The latest instance of this variable
     */
    private void updateCurrentInstance(ArrayVariable<A> value) {
        currentInstance.childInstance = value;
        currentInstance = value;
    }

    /**
     * TODO make this protected again. Method to return the instance handle.
     *
     * @return The instance handle.
     */
    @Override
    public ArrayVariable<A> instanceHandle() {
        return instanceHandle;
    }

    /**
     * Get the value of the latest instance of this variable
     *
     * @return The latest instance of this variable
     */
    @Override
    public ArrayVariable<A> getCurrentInstance() {
        return instanceHandle().currentInstance;
    }

    /**
     * Get the instance that replaced this instance when a put operation was performed. If this is the current instance
     * this value will be null.
     * 
     * @return
     */
    public ArrayVariable<A> getChildInstance() {
        return childInstance;
    }

    /**
     * Method to see if this instance of the array is used before it is modified. TODO remove this if it is not used.
     *
     * @return
     */

    /* Factory methods */

    /*
     * Just length x2. This is a convenience method with the type being set on the first put. It should be avoided in
     * auto generated code.
     */
    /*
     * public static <A extends Variable<A>> ArrayVariable<A> arrayVariable(int length) { return
     * arrayVariable(Variable.intVariable(length)); }
     * 
     * public static <A extends Variable<A>> ArrayVariable<A> arrayVariable(IntVariable length) { return
     * arrayVariable(new ArrayConstructTask<A>(length)); }/*
     * 
     * /* length and type
     */
    public static <A extends Variable<A>> ArrayVariable<A> getArrayVariable(VariableType.Type<A> baseType,
            IntVariable... dims) {
        return getArrayVariable(baseType, dims, 0, null);
    }

    public static <A extends Variable<A>> ArrayVariable<A> getArrayVariable(Location location,
            VariableType.Type<A> baseType, IntVariable... dims) {
        return getArrayVariable(baseType, dims, 0, location);
    }

    @SuppressWarnings("unchecked")
    private static <A extends Variable<A>> ArrayVariable<A> getArrayVariable(Type<A> baseType, IntVariable[] dims,
            int i, Location location) {

        ArrayVariable<A> v = getArrayVariable(new ArrayConstructTask<>(baseType, dims[i], location));
        if(i + 1 < dims.length) {
            ArrayType<?> vType = (ArrayType<?>) baseType;
            try {
                Sandwood.parFor(Variable.intVariable(0, location), dims[i], Variable.intVariable(1, location), true,
                        location, (iv) -> v.put(iv, (A) getArrayVariable(vType.getElementType(), dims, i + 1, location),
                                location));
            } catch(SandwoodException e) {
                e.printStackTrace();
            }
        }
        return v;
    }

    public interface InitilisationBody<A extends Variable<A>> {
        ArrayVariable<A> initialise();
    }

    public static <A extends Variable<A>> ArrayVariable<A> getArrayVariable(InitilisationBody<A> i) {
        return i.initialise();
    }

    public boolean isSubArray() {
        return outerArrayDesc.array != null;
    }

    public IRTreeReturn<IntVariable> getLength(CompilationContext compilationCtx) {
        if(this != instanceHandle())
            return instanceHandle().getLength(compilationCtx);

        Set<VariableWrapper<IntVariable>> arrayLengths = getPossibleLengths();
        if(arrayLengths.size() == 1) {
            IntVariable length = arrayLengths.iterator().next().value;
            if(length.isConstant(this) && getParent().getType() != DFType.CONSTRUCT_INPUT) {
                return length.getForwardIR(compilationCtx);
            }
        }

        if(isDistribution())
            throw new SandwoodModelException(
                    "This array is generated by a distribution. Arrays like this must be of constant length.",
                    getLocation());

        compilationCtx.addLengthArray(this);
        IRTreeReturn<IntVariable> length = getParent().getLength(compilationCtx);
        compilationCtx.removeLengthArray(this);
        return length;
    }

    public IRTreeReturn<IntVariable> getMaxLength(CompilationContext compilationCtx) {
        if(this != instanceHandle())
            return instanceHandle.getMaxLength(compilationCtx);

        if(isObserved())
            return ((ArrayVariable<A>) getObservation().source).getMaxLength(compilationCtx);

        return getParent().getMaxLength(compilationCtx);
    }

    public IRTreeReturn<IntVariable> getMinLength(CompilationContext compilationCtx) {
        if(this != instanceHandle())
            return instanceHandle.getMinLength(compilationCtx);

        if(isObserved())
            return ((ArrayVariable<A>) getObservation().source).getMinLength(compilationCtx);

        return getParent().getMinLength(compilationCtx);
    }

    public Set<PutTask<A>> getPuts(Scope scope, int destinationID) {
        return getPuts(scope, destinationID, new HashSet<>());
    }

    private Set<PutTask<A>> getPuts(Scope scope, int destinationID, Set<ArrayVariable<?>> seenArrays) {
        Set<ArrayVariable<A>> possibleInstances = getPossibleInstances(scope, destinationID);
        seenArrays.addAll(possibleInstances);

        Set<PutTask<A>> putTasks = new HashSet<>();

        for(ArrayVariable<A> v:possibleInstances) {
            ProducingDataflowTask<ArrayVariable<A>> parent = v.getParent();
            switch(parent.getType()) {
                case CONSTRUCT_INPUT:
                case SAMPLE:
                    break;
                case ARRAY_CONSTRUCTOR: {
                    if(isSubArray()) {
                        GetArrayTask<A> getTask = getOuterArrayDesc().getGetTask();
                        // For each put task (p1) that can set a value for this get, add the puts (p2)
                        // that could build up the value set by p1.
                        for(PutTask<ArrayVariable<A>> pt:getTask.array.getPuts(scope, destinationID, seenArrays))
                            if(!seenArrays.contains(pt.value))
                                putTasks.addAll(
                                        ((ArrayVariable<A>) pt.value).getPuts(scope, destinationID, seenArrays));
                    }
                    break;
                }
                case GET: {
                    GetArrayTask<A> getTask = (GetArrayTask<A>) parent;
                    // For each put task (p1) that can set a value for this get, add the puts (p2)
                    // that could build up the value set by p1.
                    for(PutTask<ArrayVariable<A>> pt:getTask.array.getPuts(scope, destinationID, seenArrays))
                        if(!seenArrays.contains(pt.value))
                            putTasks.addAll(((ArrayVariable<A>) pt.value).getPuts(scope, destinationID, seenArrays));
                    break;
                }
                case PUT: {
                    PutTask<A> pt = (PutTask<A>) parent;
                    putTasks.add(pt);
                    break;
                }
                default:
                    throw new CompilerException("Unable to handle parent type " + parent.getType());
            }
        }
        return putTasks;
    }

    @Override
    public ArrayVariable<A> copy() {
        throw new SandwoodModelException("unable to assign array " + getAlias()
                + " to another reference as arrays are reference types and currently only"
                + " value types can be assigned to multiple variables.");
    }

    @Override
    public final ArrayVariable<A> copy(Location location) {
        throw new SandwoodModelException("unable to assign array " + getAlias()
                + " to another reference as arrays are reference types and currently only"
                + " value types can be assigned to multiple variables.", location);
    }

    @Override
    public void setAlias(VariableDescription<ArrayVariable<A>> alias) {
        super.setAlias(alias);
        if(childInstance != null)
            childInstance.setAlias(alias);
    }

    // TODO this name may need repositioning so the name only becomes fixed once we know what the target language is.
    public static LocalVariableDescription<IntVariable> lengthName = new LocalVariableDescription<>("length",
            VariableType.IntVariable, false);

    public static <X extends Variable<X>> IRTreeReturn<IntVariable> getLengthTree(IRTreeReturn<ArrayVariable<X>> tree) {
        return IRTree.getField(tree, lengthName);
    }

    /* parent */
    public static <A extends Variable<A>> ArrayVariable<A> getArrayVariable(ArrayProducingDataflowTask<A> parent) {
        return new ArrayVariable<>(parent);
    }

    /* length and initial values */
    public static <A extends Variable<A>, B extends Variable<B>> ArrayVariable<A> getArrayVariable(Type<A> baseType,
            ArrayValueInit<B> value, IntVariable... dims) {
        return getArrayVariable(baseType, value, dims, 0, null);
    }

    public static <A extends Variable<A>, B extends Variable<B>> ArrayVariable<A> getArrayVariable(Location location,
            Type<A> baseType, ArrayValueInit<B> value, IntVariable... dims) {
        return getArrayVariable(baseType, value, dims, 0, location);
    }

    private static <A extends Variable<A>, B extends Variable<B>> ArrayVariable<A> getArrayVariable(Type<A> baseType,
            ArrayValueInit<B> value, IntVariable[] dims, int i, Location location) {
        ArrayVariable<A> v = getArrayVariable(new ArrayConstructTask<>(baseType, dims[i], location));
        if(i + 1 < dims.length) {
            ArrayType<?> vType = (ArrayType<?>) baseType;
            try {
                Sandwood.parFor(Variable.intVariable(0), dims[i], Variable.intVariable(1), true, location,
                        (iv) -> v.put(iv, (A) getArrayVariable(vType.getElementType(), dims, i + 1, location)));
            } catch(SandwoodException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Sandwood.parFor(Variable.intVariable(0, location), dims[i], Variable.intVariable(1, location), true,
                        location, (iv) -> v.put(iv, (A) value.getValue(), location));
            } catch(SandwoodException e) {
                e.printStackTrace();

            }
        }
        return v;
    }

    /* Construct an array of unknown size from an observed field */
    public static <A extends Variable<A>> ArrayVariable<A> getArrayVariable(String fieldname, ArrayType<A> type,
            String comment) {
        return getArrayVariable(fieldname, type, comment, null);
    }

    public static <A extends Variable<A>> ArrayVariable<A> getArrayVariable(String fieldname, ArrayType<A> type,
            String comment, Location location) {
        ConstructArrayInput<A> o = new ConstructArrayInput<>(type, fieldname, location);
        ArrayVariable<A> v = new ArrayVariable<>(o);
        if(comment != null)
            v.setComment(comment);
        return v;
    }

    public static <A extends Variable<A>> ArrayVariable<A> getArrayVariable(String fieldname, ArrayType<A> type) {
        return getArrayVariable(fieldname, type, null, null);
    }

    public static <A extends Variable<A>> ArrayVariable<A> getArrayVariable(String fieldname, ArrayType<A> type,
            Location location) {
        String comment = null;
        return getArrayVariable(fieldname, type, comment, location);
    }

    public static <A extends Variable<A>> ArrayVariable<A> getNamedArray(VariableDescription<ArrayVariable<A>> varDesc,
            Scope scope) {
        ScopeStack.pushScope(scope);
        NamedArrayVariable<A> nv = new NamedArrayVariable<>(varDesc);
        ArrayVariable<A> v = new ArrayVariable<>(nv);
        ScopeStack.popScope(scope);
        return v;
    }

    public ArrayVariable<?> getSourceInstance() {
        ArrayVariable<?> a = this;
        while(a.outerArrayDesc.array != null)
            a = a.outerArrayDesc.array;
        return a.instanceHandle;
    }

    public OuterArrayDesc<A> getOuterArrayDesc() {
        return outerArrayDesc;
    }
    
    /**
     * Constructs a differential array of the same structure as a base array provided as argument.
     * The differentials of the values of the innermost array are calculated and assigned to the 
     * innermost differential array.
     * @param array the base array on which the differential array will be constructed.
     * @param variable the variable differentiating upon.
     * @param compilationCtx the compilation context.
     * @return the differential array, containing the structure and value differentials.
     * Precondition: The base type of the variable must always be double.
     * Precondition: array must not be null.
     * Precondition: variable must not be null.
     * Precondition: compilationCtx must not be null.
     * 
     * Postcondition: the differential array returned must not be null.
     */
    public static <A extends Variable<A>, B extends Variable<B>> ArrayVariable<A> getDifferentialArray(ArrayVariable<A> array,
    		Variable<?> variable, int id, CompilationContext compilationCtx) {
    	
    	assert array != null;
    	assert variable != null;
    	assert compilationCtx != null;
    	
    	// Precondition: The base type of the variable must always be double.
    	assert array.getElementType().getBaseType() == VariableType.DoubleVariable;
    	
    	ArrayVariable<A> differentialArray = (ArrayVariable<A>) compilationCtx.getDifferentialArray(array, variable);
    	// If the differential array for the base array exists in compilation context,
    	// obtain it, otherwise create it.
    	if (differentialArray == null) {

			ArrayProducingDataflowTask<A> arrayParentTask = array.instanceHandle().getParent();
			
			switch(arrayParentTask.getType()) {
		    
				// In case of array construction, an array of the same type and depth = (d - 1) 
				// compared to the base array is constructed and registered to compilation context
				// set using the element type of the initial array.
			    case ARRAY_CONSTRUCTOR: {

					Type<A> arrayType = array.getElementType();
					IntVariable arrayLength = ((ArrayConstructTask<A>) arrayParentTask).length;
					
					ScopeStack.pushScope(arrayParentTask.scope());
					
					// The array is constructed here.
					differentialArray =  
							Variable.arrayVariable(null, arrayType, arrayLength);
					differentialArray.setAlias(VariableNames.getDifferentialName(array, variable));
			
					ScopeStack.popScope(arrayParentTask.scope());

					break;
				}
			    
			    // Case GET - coming from a get(...) invocation.
			    case GET: {
			    	GetTask<A> getTask = (GetTask<A>) arrayParentTask;
			    	ScopeStack.pushScope(arrayParentTask.scope());
			    	
			    	// This is needed in case of a subarray:
			    	// obtain the outer array, generate its differential,
			    	// then apply the get(...) operation to the differential.
					OuterArrayDesc<A> outerArrayDesc = array.getOuterArrayDesc();
					ArrayVariable<ArrayVariable<A>> outerArray = outerArrayDesc.getArray();

					ArrayVariable<ArrayVariable<A>> outerDiffArray = getDifferentialArray(outerArray, variable, id, compilationCtx);;
					// This will apply a get(...) on the whole differential array.
					differentialArray = (ArrayVariable<A>) outerDiffArray.get(getTask.index);
					

					differentialArray.setAlias(VariableNames.getDifferentialName(array, variable));
					ScopeStack.popScope(arrayParentTask.scope());
					
					break;
			    }
	
				default:
					throw new CompilerException("ArrayVariable differential supports only Get and Array Constructor tasks.");
			}
			
			// Add differential array to the compilation context.
			compilationCtx.addDifferentialArray(array, variable, differentialArray);
			
			// Add the array as a differential variable.
    		compilationCtx.traces.addDifferentialVariable(differentialArray);
    		
    		// If not a sub-array, register it on traces as an intermediate as well.
    		if (!differentialArray.isSubArray()) {
    			compilationCtx.traces.addIntermediateDifferentialVariable(differentialArray);
    		}

    	}
    	
		// Obtain all array puts, to the extent of the differential array.
    	//differentialArray.getId()
    	Set<PutTask<A>> putsSet = array.getPuts(array.scope(), id);
		Queue<PutTask<A>> putTaskQueue = new PriorityQueue<>(putsSet);

		while (!putTaskQueue.isEmpty()) {
			PutTask<A> put = (PutTask<A>) putTaskQueue.poll();

			// Apply construction of the inner part, only if it is not already done for the respective put.
			if (!compilationCtx.containsPutTask(put.array, variable, put)) {
				
				compilationCtx.addPutTask(put.array, variable, put);

				// If the value is not an array, generate its differential,
				// then set it as part of the differential array
		    	if (!put.value.getType().isArray()) {

		    		// Note: This registers differential variable to traces.
		    		DoubleVariable value = ((Differentiable) put.value).getDifferential(variable, compilationCtx);
					// Utilize current instance of internal arrays.
					// in order to generate the proper differential value.
					ScopeStack.pushScope(put.scope());
					differentialArray.put(put.index, value);
					ScopeStack.popScope(put.scope());
					// Register the task to the compilation context.
				}
		    	
		    	// If the value is an array, then handle accordingly:
		    	// (1) In case of simple puts containing no get(...), fill the differential array
		    	// in a recursive manner.
		    	// (2) If there is a get(...).put(...) sequence, then the recursion will lead to stack overflow
		    	// as it will bounce between the puts and the GET construction.
		    	// For that matter, iterate bottom-up and construct the puts as you encounter a get(...).
		    	else {
		    		ArrayVariable<A> value = (ArrayVariable<A>) put.value;

					if (value.getParent().getType() == DFType.ARRAY_CONSTRUCTOR ||
							((PutTask<A>) value.getParent()).array.getParent().getType() != DFType.GET) {
						
						// Note: this recursion call will also handle the case of adding 
						// outerArray -> outerDIffArray association to the compilation context.
						ArrayVariable<A> valueDifferentialArray = (ArrayVariable<A>) compilationCtx.getDifferentialArray(value, variable);
						if (valueDifferentialArray == null) {
							ScopeStack.pushScope(put.scope());
							valueDifferentialArray = (ArrayVariable<A>) getDifferentialArray((ArrayVariable<A>) value, variable, id, compilationCtx);
							differentialArray.put(put.index, valueDifferentialArray);
							ScopeStack.popScope(put.scope());
						}

					} else {
						// Handle get(...).put(...) sequences recursively.
						differentiateGetPutValueArrays(value, differentialArray, put, variable, compilationCtx);
					}
		    	}
			}
		}



		assert differentialArray != null;
		
		return differentialArray;
    }
    
    /**
     * Generates the differentials for arrays in part, utilized to handle get(...).put(...) scenarios.
     * @param <A> A variable generic type.
     * @param putValueArray the value coming as an array variable.
     * @param differentialArray the differential array to populate.
     * @param put the corresponding put task.
     * @param variable the variable to differentiate upon.
     * @param compilationCtx the compilation context.
     * 
     * Precondition(s): All inputs must not be null.
     */
    private static <A extends Variable<A>> void differentiateGetPutValueArrays(ArrayVariable<A> putValueArray, 
    		ArrayVariable<A> differentialArray, PutTask<A> put, Variable<?> variable, CompilationContext compilationCtx) {
    	
    	assert putValueArray != null;
    	assert differentialArray != null;
    	assert put != null;
    	assert variable != null;
    	assert compilationCtx != null;
    	
    	PutTask<A> putTask = (PutTask<A>) putValueArray.getParent();

    	// if the value is not an array, then
    	// compute its differential and populate the 
    	// respective inner array in the differential array.
    	if (!compilationCtx.containsPutTask(putValueArray, variable, putTask)) {
    		
    		compilationCtx.addPutTask(putValueArray, variable, putTask);
    		
    		ScopeStack.pushScope(put.scope());
    		if (!putTask.value.getType().isArray()) {
    			// Add put task to the compilation context

				ArrayVariable<A> innerDifferential = (ArrayVariable<A>) differentialArray.get(put.index);
				DoubleVariable differential = ((Differentiable) putTask.value).getDifferential(variable, compilationCtx);
				innerDifferential.put(putTask.index, differential);
			}

			// In case value is an array, obtain the inner differential array and the
			// array value from the put, then recursively populate in a bottom-up manner.
    		else {
    			ArrayVariable<A> putTaskValue = (ArrayVariable<A>) putTask.value;
    			ArrayVariable<A> innerDifferential = (ArrayVariable<A>) differentialArray.get(put.index);
			
    			// Perform actions recursively for the cases of Puts.
    			if (putTaskValue.getParent().getType() == DFType.PUT) {
    				differentiateGetPutValueArrays(putTaskValue, innerDifferential, put, variable, compilationCtx);
    			}
    		}
    		ScopeStack.popScope(put.scope());
    	}
    }
    
    /**
     * Constructs the array differential, against a variable differentiating upon.
     * @param variable the variable differentiating upon.
     * @param compilationCtx the compilation context.
     * @return the differential array.
     * Precondition: variable must not be null.
     * Precondition: compilationCtx must not be null.
     * 
     * Postcondition: the differential array returned has the same structure with the array argument.
     */
    @SuppressWarnings("unchecked")
	public ArrayVariable<A> getDifferential(Variable<?> variable, CompilationContext compilationCtx) {	
    	assert variable != null;
    	assert compilationCtx != null;
    	
    	// If the differential array is already calculated,
    	// obtain it from the compilation context and return it.
    	ArrayVariable<A> ctxDifferentialArray = (ArrayVariable<A>) compilationCtx.getDifferentialArray(this, variable);
    	if (ctxDifferentialArray != null) {
    		return ctxDifferentialArray;
    	}

		ArrayVariable<A> differentialArray = getDifferentialArray(getCurrentInstance(), variable, 
				getCurrentInstance().getParent().id(), compilationCtx);

		// TODO: Construct trace on this step directly, so that the user does not have to do it explicitly.
		// Add this on every <Differentiable>.getDifferential as well.

		// TODO: Remove this upon development completion and move StructureVerifier to autoDiff tests package.
		// Postcondition: the structure of the differential array must be identical to the array.
		assert StructureVerifier.checkStructureEquality(getCurrentInstance(), differentialArray.getCurrentInstance());
		return differentialArray;
    }

    /**
     * Check if Array is differentiable. In order for an array to be differentiable,
     * its values must be differentiable.
     * @param variable the variable checking if its differentiable upon.
     * @return if the array is differentiable.
     */
	public boolean isDifferentiable(Variable<?> variable) {
		
		// Iterate puts and check if values are differentiable.
		Set<PutTask<A>> puts = getPuts(scope(), getCurrentInstance().getParent().id());
		for (PutTask<A> put : puts) {
			// If an array, call recursively.
			if (put.value.getType().isArray()) {
				if (!((ArrayVariable<A>) put.value).isDifferentiable(variable)) {
					return false;
				}
			} else {
				// Else, invoke check on Differentiable value.
				Differentiable value = (Differentiable) put.value;
				if(!value.isDifferentiable(variable)) {
					return false;
				}
			}
		}
		return true;
	}
}
