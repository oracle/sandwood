/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.arrayVariable;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.Sandwood;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.ArrayProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
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
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.CopyNumberTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.NamedArrayVariable;
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
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.exceptions.ConstraintAlreadySetException;
import org.sandwood.compiler.exceptions.SandwoodModelException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class ArrayVariable<A extends Variable<A>> extends VariableImplementation<ArrayVariable<A>> {

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
    /**
     * The instance this array was generated from, null if it is an unmodified array.
     */
    private final ArrayVariable<A> parentInstance;

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
        addChildLengths(source.getCurrentInstance().getPossibleChildLengths());
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
                    if(lastIterating != null)
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

    private <B extends Variable<B>> void put(IntVariable i, B value, boolean implicit, Location location) {
        if(getElementType().equals(value.getType())) {
            typeCheckedPut(i, (A) value, implicit, location);
        } else if(getElementType() == VariableType.DoubleVariable && value.getType() == VariableType.IntVariable) {
            typeCheckedPut(i, (A) ((IntVariable) value).castToDouble(location), implicit, location);
        } else {
            throw new SandwoodModelException("Incompatible types, type " + value.getType().getJavaType()
                    + " cannot be assigned to an array of type " + getType().getJavaType() + ".", location);
        }
    }

    private <C extends Variable<C>> void typeCheckedPut(IntVariable i, A value, boolean implicit, Location location) {
        if(implicit)
            ScopeStack.pushScope(((ArrayVariable<?>) value).outerArrayDesc.scope);
        PutTask<A> putTask = new PutTask<>(this, i, value.getCurrentInstance(), implicit, location);
        ArrayVariable<A> newArray = new ArrayVariable<>(putTask, this);
        if(implicit)
            ScopeStack.popScope(((ArrayVariable<?>) value).outerArrayDesc.scope);

        // If this is a user call to add an array set the arrays outer parameters
        // to the values here. If the values are already set
        if(!implicit && value.getType().isArray()) {
            ArrayVariable<C> arrayValue = (ArrayVariable<C>) value;
            if(arrayValue.outerArrayDesc.array == null) {
                arrayValue.outerArrayDesc.array = (ArrayVariable<ArrayVariable<C>>) instanceHandle();
                arrayValue.outerArrayDesc.index = i;
                arrayValue.outerArrayDesc.scope = ScopeStack.getCurrentScope();
                arrayValue.outerArrayDesc.getTask = new GetArrayTask<>((PutTask<ArrayVariable<C>>) putTask);
            } else {
                // Generate an error message as multiple assignments are not allowed.
                ArrayVariable<A> thisArray = instanceHandle();
                ArrayVariable<A> exisitingOuterArray = (ArrayVariable<A>) arrayValue.outerArrayDesc.array;
                if(thisArray == exisitingOuterArray) {
                    IntVariable exisitingIndex = arrayValue.outerArrayDesc.index;
                    if(i.equivalent(exisitingIndex)) {
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
        // Trace the origin of the array
        Stack<GetTask<?>> gets = new Stack<>();
        Variable<?> lengthVar = traceSource(gets);

        if(lengthVar != null) { // If it came from an input construct an array to hold the lengths and variables
            // representing the lengths.
            // Calculate the dimension of the input array
            int arrayDim = lengthVar.getType().getDepth() + 1;

            // Construct the gets required to get the correct value.
            while(!gets.isEmpty()) {
                GetTask<?> g = gets.pop();
                lengthVar = ((ArrayVariable<?>) lengthVar).get(g.index, location);
                arrayDim--;
            }

            // Construct the length for this array.
            if(arrayDim == 1) {
                CopyNumberTask<IntVariable> lengthCopy = new CopyNumberTask<>((IntVariable) lengthVar, location);
                GetArrayLengthTask lengthTask = new GetArrayLengthTask(instanceHandle(), lengthCopy, location);
                return IntVariable.intVariable(lengthTask);
            } else {
                GetArrayLengthTask lengthTask = new GetArrayLengthTask((ArrayVariable<?>) lengthVar, location);
                GetArrayLengthTask lengthTask2 = new GetArrayLengthTask(instanceHandle(), lengthTask, location);
                return IntVariable.intVariable(lengthTask2);
            }
        } else {// If it did not come from an input just give the length
            return IntVariable.intVariable(new GetArrayLengthTask(this, location));
        }
    }

    public ArrayProducingDataflowTask<?> getSource() {
        ArrayProducingDataflowTask<A> d = instanceHandle().getParent();
        if(d.getType() == DFType.GET)
            return ((GetArrayTask<A>) d).array.getSource();
        else
            return d;
    }

    /**
     * Method to find the outermost array of a given array value. It will return this array if it was an input, or
     * return null if this array was declared inside the model.
     * 
     * @param gets
     * @return
     */
    private Variable<?> traceSource(Stack<GetTask<?>> gets) {
        ArrayVariable<?> v = this;
        ProducingDataflowTask<?> p = v.instanceHandle().getParent();

        // Run through all the parents to the ultimate source.
        while(p.getType() == DFType.GET) {
            GetTask<?> get = (GetTask<?>) p;
            gets.push(get);
            v = get.array;
            p = v.instanceHandle().getParent();
        }

        // If the array came from an input construction return it, otherwise return
        // null.
        if(p.getType() == DFType.CONSTRUCT_INPUT) {
            ConstructArrayInput<?> c = (ConstructArrayInput<?>) p;
            return c.lengthVar;
        } else {
            return null;
        }
    }

    public Set<VariableWrapper<IntVariable>> getPossibleLengths() {
        return new HashSet<>(arrayLengths);
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
        Set<ArrayVariable<A>> vars = new HashSet<>();

        // Get any puts that happen after the scope, but could reach it. This goes beyond
        // the arrays returned by possible instances because puts applied to the value
        // of puts could be present.
        ArrayVariable<A> vec = instanceHandle;
        while(vec != null) {
            if(vec.getParent().id() < destinationID) {
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

    public Set<VariableWrapper<IntVariable>> getPossibleChildLengths() {
        return childLengths;
    }

    public void addChildLengths(Set<VariableWrapper<IntVariable>> possibleLengths) {
        childLengths.addAll(possibleLengths);
    }

    public boolean isSubArray() {
        return outerArrayDesc.array != null;
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
        Set<ArrayVariable<A>> possibleInstances = getPossibleInstances(scope, destinationID);

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
                        for(PutTask<ArrayVariable<A>> pt:getTask.array.getPuts(scope, destinationID))
                            if(pt.value.instanceHandle().getParent() != getTask)
                                putTasks.addAll(((ArrayVariable<A>) pt.value).getPuts(scope, destinationID));
                    }
                    break;
                }
                case GET: {
                    GetArrayTask<A> getTask = (GetArrayTask<A>) parent;
                    // For each put task (p1) that can set a value for this get, add the puts (p2)
                    // that could build up the value set by p1.
                    for(PutTask<ArrayVariable<A>> pt:getTask.array.getPuts(scope, destinationID))
                        if(pt.value.instanceHandle().getParent() != getTask)
                            putTasks.addAll(((ArrayVariable<A>) pt.value).getPuts(scope, destinationID));
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
        throw new SandwoodModelException("Array variables cannot be assigned to multiple references.");
    }

    @Override
    public ArrayVariable<A> copy(Location location) {
        throw new SandwoodModelException("Array variables cannot be assigned to multiple references.", location);
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
        v.setIntermediate();
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
}
