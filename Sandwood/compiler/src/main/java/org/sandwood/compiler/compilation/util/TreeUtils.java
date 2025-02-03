/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.util;

import static org.sandwood.compiler.trees.irTree.IRTree.addDD;
import static org.sandwood.compiler.trees.irTree.IRTree.addDI;
import static org.sandwood.compiler.trees.irTree.IRTree.arrayGet;
import static org.sandwood.compiler.trees.irTree.IRTree.arrayPut;
import static org.sandwood.compiler.trees.irTree.IRTree.constant;
import static org.sandwood.compiler.trees.irTree.IRTree.eq;
import static org.sandwood.compiler.trees.irTree.IRTree.exp;
import static org.sandwood.compiler.trees.irTree.IRTree.forStmt;
import static org.sandwood.compiler.trees.irTree.IRTree.getIntField;
import static org.sandwood.compiler.trees.irTree.IRTree.ifElse;
import static org.sandwood.compiler.trees.irTree.IRTree.initializeVariable;
import static org.sandwood.compiler.trees.irTree.IRTree.lessThan;
import static org.sandwood.compiler.trees.irTree.IRTree.load;
import static org.sandwood.compiler.trees.irTree.IRTree.log;
import static org.sandwood.compiler.trees.irTree.IRTree.newArray;
import static org.sandwood.compiler.trees.irTree.IRTree.sequential;
import static org.sandwood.compiler.trees.irTree.IRTree.store;
import static org.sandwood.compiler.trees.irTree.IRTree.subtractDD;
import static org.sandwood.compiler.trees.irTree.IRTree.treeScope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.CompilationContext.CompilationPhase;
import org.sandwood.compiler.dataflowGraph.Sandwood;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope.ScopeType;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.PutTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ForTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.ArrayType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.ScalarVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.trees.Tag;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class TreeUtils {

    /**
     * A class to hold a description of an array. TODO make the genetics work here. so array elements are bound to a
     * type.
     * 
     * @param <A>
     */
    public static abstract class ArrayDesc<A extends Variable<A>> {
        // The length of the array
        public final IntVariable length;
        // The type of the elements of the array.
        public final ArrayType<A> type;
        // The scope that the array is to be constructed in.
        public final Scope scope;

        protected ArrayDesc(IntVariable length, ArrayType<A> type, Scope scope) {
            this.length = length;
            this.type = type;
            this.scope = scope;
        }

        public abstract ArrayDesc<A> merge(ArrayDesc<A> subarray);
    }

    public static class ArrayDescArray<A extends Variable<A>> extends ArrayDesc<ArrayVariable<A>> {
        // The elements that make up the array. This map may be empty or incomplete if
        // not all
        // elements of the array are initialized. It is stored as a map so that elements
        // can be
        // merged if they are assigned to the same index.
        private final Map<IntVariable, ArrayElement<A>> elements = new LinkedHashMap<>();

        // InnerScope is only set when constructing an array description from a list of
        // scopes.
        // Its use allows us to save the scope that the variable is used in so that
        // index names are correct etc.
        private ArrayDescArray(IntVariable length, ArrayType<ArrayVariable<A>> type, Scope scope) {
            super(length, type, scope);
        }

        public void addElement(ArrayElement<A> e) {
            ArrayElement<A> existingElement = elements.get(e.index);
            if(existingElement == null)
                elements.put(e.index, e);
            else
                elements.put(e.index, existingElement.merge(e));
        }

        public void addElements(List<ArrayElement<A>> es) {
            for(ArrayElement<A> e:es)
                addElement(e);
        }

        public List<ArrayElement<A>> getElements() {
            return new ArrayList<>(elements.values());
        }

        @Override
        public ArrayDesc<ArrayVariable<A>> merge(ArrayDesc<ArrayVariable<A>> newDesc) {
            assert (type == newDesc.type);

            if(!(newDesc instanceof ArrayDescBase)) {
                ArrayDescArray<A> a = (ArrayDescArray<A>) newDesc;
                for(ArrayElement<A> e:a.elements.values())
                    addElement(e);
            }
            return this;
        }
    }

    public static class ArrayDescBase<A extends Variable<A>> extends ArrayDesc<A> {
        // The scope that the elements of the array are constructed in. This is only
        // used if
        // elements is empty.
        public final ScopeDesc innerScope;

        // InnerScope is only set when constructing an array description from a list of
        // scopes.
        // Its use allows us to save the scope that the variable is used in so that
        // index names are correct etc.
        private ArrayDescBase(IntVariable length, ArrayType<A> type, Scope scope) {
            super(length, type, scope);
            innerScope = getInnerScope(length, scope);
        }

        private ArrayDescBase(IntVariable length, ArrayType<A> type, Scope scope, ScopeDesc innerScope) {
            super(length, type, scope);
            this.innerScope = innerScope;
        }

        @Override
        public ArrayDesc<A> merge(ArrayDesc<A> newDesc) {
            // Asserting lengths are the same is problematic as the lengths are generated as
            // part of the array description construction process.
            assert (type == newDesc.type);
            assert (scope == newDesc.scope);

            if(newDesc instanceof ArrayDescBase)
                return this;
            else
                return newDesc;
        }
    }

    /**
     * A class describing the values that make up part of an array.
     *
     * @param <A>
     */
    public static class ArrayElement<A extends Variable<A>> {
        /**
         * The range this element describes.
         */
        public final Scope scope;

        /**
         * The value of a sub arrays that needs to be inserted.
         */
        public final ArrayDesc<A> subarray;

        /**
         * The function for calculating the index of the subarrays. This allows both complex indexes to be saved from
         * the DAG, or offsetting and scaling to be added to constructed arrays.
         */
        public final IntVariable index;

        public ArrayElement(ScopeDesc scopeDesc, ArrayDesc<A> subarray) {
            this.scope = scopeDesc.scope;
            this.index = scopeDesc.index;
            this.subarray = subarray;
        }

        private ArrayElement(Scope scope, IntVariable index, ArrayDesc<A> subarray) {
            this.scope = scope;
            this.index = index;
            this.subarray = subarray;
        }

        public ArrayElement<A> merge(ArrayElement<A> e) {
            assert (index == e.index);
            ArrayDesc<A> newSubArray = subarray.merge(e.subarray);
            return new ArrayElement<>(scope, index, newSubArray);
        }
    }

    public static class ScopeDesc {
        // Scope that we are running in
        public final Scope scope;
        // Scope to index formula
        public final IntVariable index; // This will be null for Global, but must be set for all other scopes.
        public final boolean scaling;

        public ScopeDesc(Scope scope, IntVariable index, boolean scaling) {
            this.scope = scope;
            this.index = index;
            this.scaling = scaling;
        }
    }

    private static ScopeDesc getInnerScope(IntVariable length, Scope scope) {
        ScopeDesc innerScope = null;

        if(length != null) {
            ScopeStack.pushScope(scope);
            try {
                ForTask p = Sandwood.forLoop(Variable.intVariable(0), length, Variable.intVariable(1), true,
                        (i) -> i.setAlias("i0"));
                innerScope = new ScopeDesc(p, p.getIndex(), false);
            } catch(SandwoodException e) {
                throw new CompilerException("This should not be reachable");
            }
            ScopeStack.popScope(scope);
        }

        return innerScope;
    }

    private static class Index {
        private final IntVariable index;

        public Index(IntVariable index) {
            assert (index != null);
            this.index = index;
        }

        @Override
        public int hashCode() {
            return 31 + index.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;
            Index other = (Index) obj;
            return index.equivalent(other.index);
        }
    }

    /**
     * Method for constructing a tree representing an allocator for a named array to the shape described by an array
     * description.
     * 
     * @param name           The name of the array the output tree should construct
     * @param desc           An array descriptor detailing the shape of the array to construct.
     * @param compilationCtx
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <A extends Variable<A>, B extends Variable<B>> IRTreeVoid allocate(
            VariableDescription<ArrayVariable<A>> name, ArrayDesc<A> desc, CompilationContext compilationCtx) {
        if(desc == null)
            return null;
        // Set up the scope tracking to record the tree we generate.
        compilationCtx.pushScope();
        compilationCtx.pushSubstitutions();
        Scope scope = desc.scope;
        compilationCtx.enterScope(scope);

        // Update phase
        CompilationPhase currentPhase = compilationCtx.phase;
        compilationCtx.phase = CompilationPhase.ALLOCATION;

        if(desc.length == null)
            throw new CompilerException("Cannot allocate from a description for a subarray.");

        IRTreeReturn<IntVariable> length = desc.length.getForwardIR(compilationCtx);
        ArrayType<A> vType = desc.type;
        compilationCtx.addTreeToScope(scope, store(name, newArray(length, vType), Tree.NoComment));

        int[] suffix = new int[1];
        Map<Integer, Map<Scope, Map<Index, VariableDescription<?>>>> declaredSubArrays = new HashMap<>();
        if(desc instanceof ArrayDescArray)
            populateArray(name, suffix, (ArrayDescArray<B>) desc, declaredSubArrays, compilationCtx);

        // Finish and return the tree that we have constructed.
        compilationCtx.leaveScope(scope);
        IRTreeVoid t = compilationCtx.getOutermostScopeTree();

        // Restore the old scope
        compilationCtx.phase = currentPhase;
        compilationCtx.popSubstitutions();
        compilationCtx.popScope();
        return t;
    }

    /**
     * A method to allocate sub arrays in an array.
     * 
     * @param name              The name of the array the elements are being allocated into.
     * @param suffix            A suffix to add to variable names when temporary variable names are required.
     * @param desc              The description of the sub arrays that need to be allocated.
     * @param declaredSubArrays The names of any local variables that have already been declared in scope.
     * @param compilationCtx    The compilation context for this process.
     */
    private static <A extends Variable<A>> void populateArray(VariableDescription name, int[] suffix,
            ArrayDescArray desc, Map<Integer, Map<Scope, Map<Index, VariableDescription<?>>>> declaredSubArrays,
            CompilationContext compilationCtx) {
        List<ArrayElement> elements = desc.getElements();
        if(!elements.isEmpty()) {
            // Are we creating arrays that will hold sub arrays
            if(elements.get(0).subarray instanceof ArrayDescArray) {
                populateMultiDimensionalArray(name, suffix, elements, declaredSubArrays, compilationCtx);
            } else { // Or just creating the outermost arrays.
                populate2DimensionalArray(name, elements, compilationCtx);
            }
        }
    }

    /**
     * A method to allocate 1 dimensional arrays into a 2 dimensional array.
     * 
     * @param <A>            The type of the 1 dimensional arrays being allocated.
     * @param name           The name of the array being allocated into.
     * @param elements       The elements describing the 1 dimensional arrays to allocate.
     * @param compilationCtx The compilation context for this process.
     */
    private static <A extends Variable<A>> void populate2DimensionalArray(VariableDescription name,
            List<ArrayElement> elements, CompilationContext compilationCtx) {
        // For each element construct the arrays it describes. As the element may be in an iterative scope, each element
        // can potentially construct many arrays.
        for(ArrayElement<A> e:elements) {
            compilationCtx.enterScope(e.scope);
            IRTreeReturn<ArrayVariable<ArrayVariable<A>>> array = load(
                    (VariableDescription<ArrayVariable<ArrayVariable<A>>>) name);
            IRTreeReturn<IntVariable> index = e.index.getForwardIR(compilationCtx);
            IRTreeReturn<IntVariable> length = e.subarray.length.getForwardIR(compilationCtx);
            IRTreeReturn<ArrayVariable<A>> value = newArray(length, e.subarray.type);
            IRTreeVoid t = arrayPut(array, index, value, Tree.NoComment);
            compilationCtx.addTreeToScope(e.scope, t);

            compilationCtx.leaveScope(e.scope);
        }
    }

    /**
     * A method to allocate arrays into an array where the constructed arrays will require further allocation.
     * 
     * @param <A>               The type of the arrays to allocate.
     * @param name              The name of the array values are being allocated into.
     * @param suffix            A suffix in case temporary variables need to be constructed.
     * @param elements          The elements that describe the sub arrays to be constructed.
     * @param declaredSubArrays A map of all the temporary variables already constructed and the scope they were
     *                          constructed in.
     * @param compilationCtx    The compilation context for this process.
     */
    private static <A extends Variable<A>> void populateMultiDimensionalArray(VariableDescription name, int[] suffix,
            List<ArrayElement> elements, Map<Integer, Map<Scope, Map<Index, VariableDescription<?>>>> declaredSubArrays,
            CompilationContext compilationCtx) {
        IRTreeReturn<ArrayVariable<ArrayVariable<ArrayVariable<A>>>> array = load(
                (VariableDescription<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>) name);

        for(ArrayElement<ArrayVariable<A>> e:elements) {
            compilationCtx.enterScope(e.scope);
            ArrayDescArray<A> subarray = (ArrayDescArray<A>) e.subarray;
            VariableDescription<ArrayVariable<ArrayVariable<A>>> subarrayName;
            // If this section of the array has already been allocated somewhere else.
            if(subarray.length == null) {
                subarrayName = getSubArray(e, suffix, declaredSubArrays, compilationCtx, () -> {
                    IRTreeReturn<IntVariable> index = e.index.getForwardIR(compilationCtx);
                    return arrayGet(array, index);
                });
            } else { // Allocate this section of the array here.
                subarrayName = getSubArray(e, suffix, declaredSubArrays, compilationCtx, () -> {
                    IRTreeReturn<IntVariable> length = subarray.length.getForwardIR(compilationCtx);
                    return newArray(length, subarray.type);
                });

                // Populate the element array;
                IRTreeReturn<IntVariable> index = e.index.getForwardIR(compilationCtx);
                IRTreeReturn<ArrayVariable<ArrayVariable<A>>> value = load(subarrayName);
                IRTreeVoid t = arrayPut(array, index, value, Tree.NoComment);
                compilationCtx.addTreeToScope(e.scope, t);
            }

            populateArray(subarrayName, suffix, subarray, declaredSubArrays, compilationCtx);
            compilationCtx.leaveScope(e.scope);
        }
    }

    /**
     * An interface to allow values to be passed into the getSubArray method as lambdas so that they are only
     * constructed if required.
     */
    private interface GetArrayValue<A extends Variable<A>> {
        IRTreeReturn<ArrayVariable<ArrayVariable<A>>> get();
    }

    /**
     * A method to look and see if the array an element is describing has already been declared as a local variable. If
     * it has not it will declare the array as a local variable and assign it the passed value.
     * 
     * @param <A>            The type of the elements of the subarrays.
     * @param element        The element describing the sub arrays.
     * @param suffix         A suffix to add to temporary variable names if they need to be constructed.
     * @param declaredArrays A map recording the local variables that have already been constructed and the scope they
     *                       are constructed in.
     * @param compilationCtx The compilation context for this process.
     * @param value          A lambda to describe how to create a value to initialise the local variable if it needs to
     *                       be constructed.
     * @return The name of the local variable.
     */
    private static <A extends Variable<A>> VariableDescription<ArrayVariable<ArrayVariable<A>>> getSubArray(
            ArrayElement<ArrayVariable<A>> element, int[] suffix,
            Map<Integer, Map<Scope, Map<Index, VariableDescription<?>>>> declaredArrays,
            CompilationContext compilationCtx, GetArrayValue<A> value) {
        // Look to see if the array already exists as a local variable.
        int depth = element.subarray.type.getDepth();
        Map<Scope, Map<Index, VariableDescription<?>>> declaredSubArrays = declaredArrays.computeIfAbsent(depth,
                k -> new HashMap<>());
        Scope s = element.scope;
        VariableDescription<ArrayVariable<ArrayVariable<A>>> name;
        while(s != null) {
            Map<Index, VariableDescription<?>> d = declaredSubArrays.get(s);
            if(d != null) {
                name = (VariableDescription<ArrayVariable<ArrayVariable<A>>>) d.get(new Index(element.index));
                if(name != null)
                    return name;
            }
            s = s.getEnclosingScope();
        }

        // Construct and record the name if not present.
        name = VariableNames.subarrayName(suffix[0]++, element.subarray.type);
        declaredSubArrays.computeIfAbsent(element.scope, k -> new HashMap<>()).put(new Index(element.index), name);

        // Initialise a local variable with the name.
        compilationCtx.addTreeToScope(element.scope, initializeVariable(name, value.get(), Tree.NoComment));

        return name;
    }

    /**
     * Utility method, to construct and initialize a multi-dimension array. //TODO this is only used inside tree utils,
     * does it want to exist outside it?
     * <p>
     * if dimensions = [] type name = value
     * <p>
     * else for however many dimensions are required create and and populate a multi dimension array.
     * 
     * @param name
     * @param value
     * @param desc
     * @param compilationCtx
     * @return
     */
    private static <A extends Variable<A>, B extends Variable<B>> IRTreeVoid initialize(
            VariableDescription<ArrayVariable<B>> name, IRTreeReturn<A> value, ArrayDesc<B> desc,
            CompilationContext compilationCtx) {

        // Set up the scope tracking to record the tree we generate.
        compilationCtx.pushScope();
        initializeInScope(name, value, desc, compilationCtx);
        IRTreeVoid t = compilationCtx.getOutermostScopeTree();
        compilationCtx.popScope();
        return t;
    }

    /**
     * Method to initialize the elements in an array to a value where the array is described by an array description.
     * 
     * @param varDesc        The name of the array to initialize
     * @param value          The value to initialize its elements to.
     * @param desc           An array description describing the shape of the array to initialize, and importantly the
     *                       scopes to use.
     * @param compilationCtx
     */
    @SuppressWarnings("unchecked")
    private static <A extends Variable<A>, B extends Variable<B>, C extends Variable<C>> void initializeInScope(
            VariableDescription<C> varDesc, IRTreeReturn<A> value, ArrayDesc<B> desc,
            CompilationContext compilationCtx) {
        Scope scope = desc.scope;
        compilationCtx.enterScope(scope);

        if(desc instanceof ArrayDescBase) {
            ScopeDesc innerScope = ((ArrayDescBase<B>) desc).innerScope;

            compilationCtx.enterScope(innerScope.scope);
            IRTreeReturn<IntVariable> index = innerScope.index.getForwardIR(compilationCtx);
            IRTreeVoid body = arrayPut(load((VariableDescription<ArrayVariable<A>>) varDesc), index, value,
                    Tree.NoComment);
            compilationCtx.addTreeToScope(innerScope.scope, body);
            compilationCtx.leaveScope(innerScope.scope);
        } else {
            for(ArrayElement<B> e:((ArrayDescArray<B>) desc).getElements())
                initializeArray(load((VariableDescription<ArrayVariable<B>>) varDesc), e, value, compilationCtx);
        }

        // Finish and return the tree that we have constructed.
        compilationCtx.leaveScope(scope);
    }

    /**
     * Method for initialising part of an array based on an array element.
     * 
     * @param tree           The IRTree to set the value as it is constructed so far.
     * @param e              The element describing the part of the array we want to initialize.
     * @param value          The value the outermost element will be set to.
     * @param compilationCtx The compilation context for this compilation process.
     */
    private static <A extends Variable<A>, B extends Variable<B>> void initializeArray(
            IRTreeReturn<ArrayVariable<A>> tree, ArrayElement<A> e, IRTreeReturn<B> value,
            CompilationContext compilationCtx) {
        compilationCtx.enterScope(e.scope);

        IRTreeReturn<IntVariable> index = e.index.getForwardIR(compilationCtx);
        IRTreeReturn<A> tree2 = IRTree.arrayGet(tree, index);

        // Initialize the values of this temporary array.
        initialize(tree2, e.subarray, value, compilationCtx);

        compilationCtx.leaveScope(e.scope);
    }

    /**
     * Method that initializes an array from an array description, and a value to place in the outermost cells of the
     * array description.
     * 
     * @param tree           An IR Tree that loads the required array.
     * @param desc           The description of the size of the array.
     * @param value          The value to set in the cells of the described array.
     * @param compilationCtx THe compilation context for this compilation process.
     */
    @SuppressWarnings("unchecked")
    private static <A extends Variable<A>, B extends Variable<B>, C extends Variable<C>> void initialize(
            IRTreeReturn<A> tree, ArrayDesc<A> desc, IRTreeReturn<B> value, CompilationContext compilationCtx) {

        // If there are no elements in the description, this is the outermost point,
        // so apply the value to every point in the inner scope.
        if(desc instanceof ArrayDescBase) {
            Scope scope = desc.scope;
            compilationCtx.enterScope(scope);
            ScopeDesc innerScope = ((ArrayDescBase<A>) desc).innerScope;

            compilationCtx.enterScope(innerScope.scope);
            IRTreeReturn<IntVariable> index = innerScope.index.getForwardIR(compilationCtx);

            IRTreeVoid body = arrayPut((IRTreeReturn<ArrayVariable<B>>) tree, index, value, Tree.NoComment);
            // Add the inner scope to the compilation tree.
            compilationCtx.addTreeToScope(innerScope.scope, body);
            compilationCtx.leaveScope(innerScope.scope);

            // Finish and return the tree that we have constructed.
            compilationCtx.leaveScope(scope);
        }
        // Otherwise for each element initialize the array.
        else
            for(ArrayElement<?> e:((ArrayDescArray<?>) desc).getElements())
                initializeArray((IRTreeReturn<ArrayVariable<C>>) tree, (ArrayElement<C>) e, value, compilationCtx);
    }

    /**
     * Method to put a value into an indirection
     *
     * @param variableName The name of the variable.
     * @param args         The values of the indices used to set the values.
     * @param value        The tree generating the value we want to store.
     * @param comment      The comment to go with this variable assignment.
     */
    @SuppressWarnings("unchecked")
    public static <A extends Variable<A>> IRTreeVoid putIndirectValue(VariableDescription<A> variableName,
            List<IRTreeReturn<IntVariable>> args, IRTreeReturn<A> value, String comment) {
        int size = args.size();
        // If there are no indices, just store the value.
        if(size == 0) {
            return store(variableName, value, comment);
        } else {
            // Otherwise for each index add a get till we reach the outermost one where we
            // add a put.
            // The put will add implicit puts to all the outer arrays to ensure that the
            // state changes are tracked.
            Type<?> vType = VariableType.getType(value.getOutputType(), size);
            @SuppressWarnings("rawtypes")
            IRTreeReturn t = load(VariableNames.altTypeName(variableName, vType));
            for(int i = 0; i < size - 1; i++)
                t = arrayGet(t, args.get(i));
            return arrayPut(t, args.get(size - 1), value, comment);
        }
    }

    /**
     * This method puts a value into a variable based on the scopes it is currently in. This only works for compiler
     * constructed code as there is no control over index orders or formulas in user code.
     *
     * @param var            The variable to put the value into
     * @param value          The value to place
     * @param compilationCtx
     * @return
     */
    public static <A extends Variable<A>> IRTreeVoid putIndirectValue(Variable<A> var, IRTreeReturn<A> value,
            String comment, CompilationContext compilationCtx) {
        VariableDescription<A> varDesc = var.getUniqueVarDesc();
        List<IntVariable> argsVars = getScopeArgs(var);
        List<IRTreeReturn<IntVariable>> argsTrees = toArgTrees(argsVars, compilationCtx);
        return putIndirectValue(varDesc, argsTrees, value, comment);
    }

    /**
     * Method to construct a tree adding in the indirection when accessing variables to compute the sampling.
     *
     * @param variableName The name of the variable.
     * @param args         The indexes needed to dereference the variable.
     * @return A tree accessing the variable complete with the required dereferencing.
     */
    @SuppressWarnings("unchecked")
    public static <A extends Variable<A>> IRTreeReturn<A> getIndirectValue(VariableDescription<A> variableName,
            List<IRTreeReturn<IntVariable>> args) {
        int size = args.size();
        @SuppressWarnings("rawtypes")
        IRTreeReturn t = load(VariableNames.altTypeName(variableName, VariableType.getType(variableName.type, size)));
        for(int i = 0; i < size; i++) {
            IRTreeReturn<IntVariable> arg = args.get(i);
            t = arrayGet(t, arg);
        }
        return t;
    }

    /**
     * This method gets a value from an array based on the scopes it is currently in. This only works for compiler
     * constructed code as there is no control over index orders or formulas in user code.
     * 
     * Method to construct a variable adding in the indirection when accessing variables to compute the sampling. The
     * indirection is into arrays created by the compiler for holding a value for each iteration of each for loop.
     *
     * @param args A list of the indexes from the scope the variable is created in to the global scope.
     * @param var  The variable in the inner level
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <A extends Variable<A>> IRTreeReturn<A> getIndirectValue(Variable<A> var,
            CompilationContext compilationCtx) {
        List<ScopeDesc> scopes = TreeUtils.getScopeDescs(var);
        List<IntVariable> args = TreeUtils.getScopeArgs(scopes);

        int implicitDepth = args.size();

        @SuppressWarnings("rawtypes")
        IRTreeReturn result = load(
                VariableNames.altTypeName(var.getUniqueVarDesc(), VariableType.getType(var.getType(), implicitDepth)));
        for(int i = 0; i < implicitDepth; i++) {
            IntVariable arg = args.get(i);
            result = arrayGet(result, arg.getForwardIR(compilationCtx));
        }

        return result;
    }

    /**
     * Method to get a list of the scopes surrounding a variable.
     * 
     * @param v The variable we want to get the scopes for.
     * @return
     */
    public static List<ScopeDesc> getScopeDescs(Variable<?> v) {
        return getScopeDescs(v.instanceHandle().getParent());
    }

    /**
     * Method to get the scopes surrounding a task.
     * 
     * @param t The task we want to get the scopes for.
     * @return
     */
    private static List<ScopeDesc> getScopeDescs(DataflowTask<?> t) {
        // Add each scope to the list before moving to the enclosing scope
        List<ScopeDesc> results = new ArrayList<>();
        List<Scope> scopes = getScopes(t);
        for(Scope scope:scopes) {
            switch(scope.getScopeType()) {
                case GLOBAL: {
                    results.add(new ScopeDesc(scope, null, false));
                    break;
                }
                case IF:
                case ELSE:
                    break;

                case FOR: {
                    ForTask p = (ForTask) scope;
                    IntVariable index = p.getIndex().subtract(p.getStart()).divide(p.getStep());
                    results.add(new ScopeDesc(scope, index, true));
                    scope = scope.getEnclosingScope();
                    break;
                }
                default:
                    throw new CompilerException("Attempting to get a scope from an unsupported type");
            }
        }
        return results;
    }

    /**
     * Method to get the scopes surrounding a task.
     * 
     * @param t The task we want to get the scopes for.
     * @return
     */
    public static List<Scope> getScopes(DataflowTask<?> t) {
        // Add each scope to the list before moving to the enclosing scope
        List<Scope> results = new ArrayList<>();
        Scope scope = t.scope();
        while(scope != GlobalScope.scope) {
            ScopeType scopeType = scope.getScopeType();
            switch(scopeType) {
                case BLOCK:
                case COMMENT:
                    scope = scope.getEnclosingScope();
                    break;

                case IF:
                case ELSE:
                case FOR:
                    results.add(scope);
                    scope = scope.getEnclosingScope();
                    break;

                case REDUCE:
                case GLOBAL:
                default:
                    throw new CompilerException("Attempting to get a scope from an unsupported type");
            }
        }
        results.add(scope);

        // Reverse the list so the outermost scope is first.
        Collections.reverse(results);
        return results;
    }

    /**
     * Method to harvest the argument indexes out of a list of scopes. This can only be used to construct arguments for
     * arrays constructed from a list of scopes, i.e. temporary data structures constructed by the compiler, not data
     * structures constructed by the user as they may have out of order indexing.
     *
     * @param scopes The scopes to harvest indexes from.
     * @return
     */
    public static List<IntVariable> getScopeArgs(List<ScopeDesc> scopes) {
        List<IntVariable> args = new ArrayList<>();
        for(ScopeDesc s:scopes) {
            switch(s.scope.getScopeType()) {
                case FOR:
                    args.add(s.index);
                    break;
                case GLOBAL:
                    break;
                default:
                    throw new CompilerException("Attempting to get a scope from an unsupported type");
            }
        }
        return args;
    }

    public static List<IntVariable> getScopeArgs(Variable<?> v) {
        return getScopeArgs(getScopeDescs(v));
    }

    public static List<IntVariable> getScopeArgs(DataflowTask<?> t) {
        return getScopeArgs(getScopeDescs(t));
    }

    /**
     * Method to get an array descriptor to construct the storage required for a variable. Unlike array descriptors that
     * are constructed from scopes, and will only describe the storage required for the parts of the variable that are
     * in scope, this will describe the storage required for the entire variable. If this is drawn from a parent array,
     * it won't describe any part of that parent array that is not used in this scope.
     * 
     * @param v The variable we want to construct storage for.
     * @return
     */
    public static ArrayDesc<?> getArrayDescription(Variable<?> v) {
        return getArrayDescription(v, Collections.emptyList(), v.getType().getBaseType());
    }

    /**
     * Method to get an array descriptor to construct the storage required for a variable. Unlike array descriptors that
     * are constructed from scopes, and will only describe the storage required for the parts of the variable that are
     * in scope, this will describe the storage required for the entire variable. If this is drawn from a parent array,
     * it won't describe any part of that parent array that is not used in this scope.
     * 
     * @param v         The variable we want to construct storage in the shape of.
     * @param innerType The type we want at the innermost point of the array.
     * @return
     */
    public static ArrayDesc<?> getArrayDescription(Variable<?> v, Type<?> innerType) {
        return getArrayDescription(v, Collections.emptyList(), innerType);
    }

    /**
     * Method to get an array descriptor to construct the storage required based on a variable, a set of scopes, and a
     * data type. The later elements are used to allow the creation of more complex elements in the array so that the
     * array can be used to store complex temporary data.
     * <p>
     * Unlike array descriptors that are constructed from scopes, and will only describe the storage required for the
     * parts of the variable that are in scope, this will describe the storage required for the entire variable. If this
     * is drawn from a parent array, it won't describe any part of that parent array that is not used in this scope.
     * 
     * @param v            The variable we want to construct storage for.
     * @param elementScope The scope describing the structure of the array we want to construct in the elements of the
     *                     array.
     * @param innerType    The type we want at the innermost point of the array.
     * @return
     */
    public static ArrayDesc<?> getArrayDescription(Variable<?> v, ScopeDesc elementScope, Type<?> innerType) {
        return getArrayDescription(v, Collections.singletonList(elementScope), innerType);
    }

    /**
     * Method to get an array descriptor to construct the storage required based on a variable, a set of scopes, and a
     * data type. The later elements are used to allow the creation of more complex elements in the array so that the
     * array can be used to store complex temporary data.
     * <p>
     * Unlike array descriptors that are constructed from scopes, and will only describe the storage required for the
     * parts of the variable that are in scope, this will describe the storage required for the entire variable. If this
     * is drawn from a parent array, it won't describe any part of that parent array that is not used in this scope.
     * 
     * @param v             The variable we want to construct storage for.
     * @param elementScopes The scopes describing the structure of the array we want to construct in the elements of the
     *                      array.
     * @param innerType     The type we want at the innermost point of the array.
     * @return
     */
    public static ArrayDesc<?> getArrayDescription(Variable<?> v, List<ScopeDesc> elementScopes, Type<?> innerType) {
        // Get the scopes this variable appears in.
        List<ScopeDesc> scopes = getScopeDescs(v);
        // Construct the type of the expression by allowing for the outer scopes, the
        // inner scopes, and the depth of the variable.
        // The -1 is because global will always appear in scopes.
        int arrayDepth = (scopes.size() - 1) + v.getType().getDepth() + elementScopes.size();
        // If there is no array to describe
        if(arrayDepth == 0)
            return null;
        ArrayType<?> type = (ArrayType<?>) VariableType.getType(innerType, arrayDepth);
        return getArrayDescriptionInternal(scopes, v, elementScopes, type);
    }

    // TODO work out how to encode the relationship between A and C.
    private static <A extends Variable<A>, B extends Variable<B>, C extends Variable<C>> ArrayDesc<A> getArrayDescriptionInternal(
            ArrayVariable<B> v, ArrayType<A> type, List<ScopeDesc> elementScopes) {

        // Get the length
        ArrayVariable<B> currentInstance = v.getCurrentInstance();
        IntVariable length = currentInstance.scopedLength(null);

        boolean fromGet = v.instanceHandle().getParent().getType() == DFType.GET;

        // V is an array of arrays, so we need to construct elements.
        if(v.getType().getElementType().isArray()) {
            // Construct a list to store any elements we find. We do this as we will want to
            // reverse their order before adding them to the description.
            List<ArrayElement<C>> elements = new ArrayList<>();
            // Get the constructor
            DataflowTask<?> t = v.instanceHandle().getParent();
            // Start with the current instance,
            v = currentInstance;

            ArrayType<C> vecType = (ArrayType<C>) type.getElementType();

            DataflowTask<ArrayVariable<B>> p = v.getParent();

            List<PutTask<B>> puts = new ArrayList<>();
            // While we have not reached the constructor
            while(p != t) {
                switch(p.getType()) {
                    // For every put if the put is of an array construct a descriptor for that put
                    // by
                    // use of a recursive call.
                    case PUT:
                        PutTask<B> put = (PutTask<B>) p;
                        p = put.array.getParent();
                        puts.add(put);
                        break;
                    default:
                        throw new CompilerException("Array modified by an operation other than a put.");
                }
            }

            // Reverse the list so the first put is the first element. Because of single
            // assignment semantics,
            // this is unnecessary, but it makes the resulting data structure and output
            // code more intuitive.
            Collections.reverse(puts);

            // For every put, if the put value is an array, construct a descriptor for that
            // put by use of a recursive call.
            for(PutTask<B> put:puts) {
                ArrayDesc<C> subarrayDesc = getArrayDescriptionInternal((ArrayVariable<?>) put.value, vecType,
                        elementScopes);
                if(subarrayDesc != null)
                    elements.add(new ArrayElement<>(new ScopeDesc(put.scope(), put.index, false), subarrayDesc));
            }

            if(elements.size() == 0 && fromGet)
                return null;

            // TODO tidy up so null is not used for no known length.
            ArrayDescArray<C> desc = new ArrayDescArray<>((fromGet) ? null : length, (ArrayType<ArrayVariable<C>>) type,
                    v.scope());
            // Having constructed the descriptor, populate its elements
            desc.addElements(elements);
            return (ArrayDesc<A>) desc;
        }
        // V is an array of base types.
        else {
            // If V came from a get we cannot know its size, so return null.
            if(fromGet)
                return null;
            else if(elementScopes.isEmpty()) // There is no alternative type to place instead of the original base
                // type.
                return new ArrayDescBase<>(length, type, v.scope());
            else {
                return (ArrayDesc<A>) constructArrayDescArray(v.scope(), (ArrayType<ArrayVariable<C>>) type,
                        elementScopes, length);
            }
        }
    }

    private static <A extends Variable<A>> ArrayDesc<ArrayVariable<A>> constructArrayDescArray(Scope vScope,
            ArrayType<ArrayVariable<A>> type, List<ScopeDesc> elementScopes, IntVariable length) {
        ArrayDescArray<A> desc = new ArrayDescArray<>(length, type, vScope);
        ScopeDesc innerScope = getInnerScope(length, desc.scope);
        elementScopes = rebaseScopes(innerScope.scope, elementScopes);

        // Having constructed the descriptor, populate it with the descriptor from the
        // element scopes
        ArrayDesc<A> elementDesc = getArrayDescriptionInternal(innerScope.scope, elementScopes,
                (ArrayType<A>) type.getElementType());
        ArrayElement<A> e = new ArrayElement<>(innerScope, elementDesc);
        desc.addElement(e);

        return desc;
    }

    /**
     * Method to get an array description for an array without setting a value for the innermost part.
     * 
     * @param scopes
     * @param type
     * @return
     */
    public static <A extends Variable<A>> ArrayDesc<A> getArrayDescription(List<ScopeDesc> scopes, Type<?> type) {
        int size = scopes.size();
        if(size < 2) // This will only be the case if Global is passed, and this doesn't generate an
            // array.
            return null;
        else {
            @SuppressWarnings("unchecked")
            ArrayType<A> arrayType = (ArrayType<A>) VariableType.getType(type, size - 1);
            return getArrayDescriptionInternal(scopes, arrayType);
        }
    }

    /**
     * Method for constructing an array description from a list of scopes, a type, and a value to set the array values
     * to. This is only suitable for constructing variables that are used just in the range of the list of scopes, a
     * variable that is constructed across a number of for loops, for example one loop constructing the first n
     * elements, and a second loop constructing the second n elements could not be described with this as only the one
     * half will be described.
     * 
     * @param scope  The outer scope, this could be global for which there is no index.
     * @param scopes The scope descriptions for the inner elements.
     * @param type   The type of the array.
     * @return
     */
    private static <A extends Variable<A>> ArrayDesc<A> getArrayDescriptionInternal(Scope scope, List<ScopeDesc> scopes,
            ArrayType<A> type) {
        return getArrayDescriptionInternal(scope, scopes, 0, type);
    }

    /**
     * Method for constructing an array description from a list of scopes, a type, and a value to set the array values
     * to. This is only suitable for constructing variables that are used just in the range of the list of scopes, a
     * variable that is constructed across a number of for loops, for example one loop constructing the first n
     * elements, and a second loop constructing the second n elements could not be described with this as only the one
     * half will be described.
     * 
     * @param scopes The scope descriptions for the elements.
     * @param type   The type of the array.
     * @return
     */
    private static <A extends Variable<A>> ArrayDesc<A> getArrayDescriptionInternal(List<ScopeDesc> scopes,
            ArrayType<A> type) {
        return getArrayDescriptionInternal(scopes.get(0).scope, scopes, 1, type);
    }

    /**
     * Method for constructing an array description from a list of scopes, a type, and a value to set the array values
     * to. This is only suitable for constructing variables that are used just in the range of the list of scopes, a
     * variable that is constructed across a number of for loops, for example one loop constructing the first n
     * elements, and a second loop constructing the second n elements could not be described with this as only the one
     * half will be described.
     * 
     * @param scopes        The scope descriptions for the elements.
     * @param v             The variable the array description is being constructed for.
     * @param elementScopes The scopes of the inner elements of the array.
     * @param type          The type of the array.
     * @return
     */
    private static ArrayDesc<?> getArrayDescriptionInternal(List<ScopeDesc> scopes, Variable<?> v,
            List<ScopeDesc> elementScopes, ArrayType<?> type) {
        if(scopes.size() == 1) { // There are no outer scopes to apply, so just move on the inner parts.
            // is this an array?
            if(v.getType().isArray())
                return getArrayDescriptionInternal((ArrayVariable<?>) v, type, elementScopes);
            else
                return getArrayDescriptionInternal(scopes.get(0).scope, elementScopes, type);
        } else
            return getArrayDescriptionInternal(scopes.get(0).scope, scopes, v, elementScopes, 1, type);
    }

    /**
     * Method for constructing an array description from an array of scopes, a type, and a value to set the array values
     * to.
     * 
     * @param scopes
     * @param pos    The position in the list of scopes that we have reached. This is used internally, anything calling
     *               this method should use a value of 0.
     * @param type   The type of the array.
     * @return
     */
    private static <A extends Variable<A>, B extends Variable<B>> ArrayDesc<A> getArrayDescriptionInternal(Scope scope,
            List<ScopeDesc> scopes, int pos, ArrayType<A> type) {
        // Get the scope the array is constructed in, and the scope that will be used
        // to initialize the arrays values. As this is constructed from a list of scopes
        // There will be at most one array element.
        ScopeDesc innerScopeDesc = scopes.get(pos);
        Scope innerScope = innerScopeDesc.scope;
        if(innerScope.getScopeType() != ScopeType.FOR)
            throw new CompilerException(
                    "Generating descriptions from scope type " + innerScope.getScopeType() + " is not implemented.");

        ForTask t = (ForTask) innerScope;
        // Construct the array with the upper bound of the scope as the length.
        ScopeStack.pushScope(scope);
        IntVariable one = Variable.intVariable(1);
        IntVariable length = t.getEnd().subtract(one).subtract(t.getStart()).divide(t.getStep()).add(one);
        ScopeStack.popScope(scope);

        if(pos + 1 == scopes.size()) { // This is the outermost point, construct a base type and stop.
            return new ArrayDescBase<>(length, type, scope, innerScopeDesc);
        } else { // If we have another scope, iteratively call.
            ArrayType<ArrayVariable<B>> vecType = (ArrayType<ArrayVariable<B>>) type;
            ArrayDescArray<B> arrayDesc = new ArrayDescArray<>(length, vecType, scope);
            ArrayDesc<B> subarray = getArrayDescriptionInternal(innerScope, scopes, pos + 1,
                    (ArrayType<B>) vecType.getElementType());
            ArrayElement<B> e = new ArrayElement<>(innerScopeDesc, subarray);
            arrayDesc.addElement(e);
            return (ArrayDesc<A>) arrayDesc;
        }
    }

    /**
     * Method for constructing an array description from an array of scopes, a type, and a value to set the array values
     * to.
     *
     * @param scope         The scope the description values should be constructed in.
     * @param scopes        A list of scopes to construct inner elements of the arrays in.
     * @param v             The variable this array description is being constructed for.
     * @param elementScopes The scopes describing the structure of the array we want to construct in the elements of the
     *                      array.
     * @param pos           The position in the list of scopes that we have reached. This is used internally, anything
     *                      calling this method should use a value of 0.
     * @param type          The type of the array.
     * @return An ArrayDescription
     */
    private static <A extends Variable<A>, B extends Variable<B>, C extends Variable<C>> ArrayDesc<A> getArrayDescriptionInternal(
            Scope scope, List<ScopeDesc> scopes, Variable<C> v, List<ScopeDesc> elementScopes, int pos,
            ArrayType<A> type) {
        // Get the scope the array is constructed in, and the scope that will be used
        // to initialize the arrays values. As this is constructed from a list of scopes
        // There will be at most one array element.
        ScopeDesc innerScopeDesc = scopes.get(pos);
        Scope innerScope = innerScopeDesc.scope;
        if(innerScope.getScopeType() != ScopeType.FOR)
            throw new CompilerException(
                    "Generating descriptions from scope type " + innerScope.getScopeType() + " is not implemented.");

        ForTask t = (ForTask) innerScope;
        // Construct the array with the upper bound of the scope as the length.
        ScopeStack.pushScope(scope);
        IntVariable one = Variable.intVariable(1);
        IntVariable length = t.getEnd().subtract(one).subtract(t.getStart()).divide(t.getStep()).add(one);
        ScopeStack.popScope(scope);

        if(pos + 1 == scopes.size() && !v.getType().isArray() && elementScopes.isEmpty()) {
            // This is the outermost point, of these scopes.
            return new ArrayDescBase<>(length, type, scope, innerScopeDesc);
        } else { // If we have another scope, iteratively call.
            ArrayType<ArrayVariable<B>> vecType = (ArrayType<ArrayVariable<B>>) type;
            ArrayDescArray<B> arrayDesc = new ArrayDescArray<>(length, vecType, scope);
            ArrayDesc<B> subarray;
            if(pos + 1 < scopes.size())
                subarray = getArrayDescriptionInternal(innerScope, scopes, v, elementScopes, pos + 1,
                        (ArrayType<B>) vecType.getElementType());
            else if(v.getType().isArray())
                subarray = getArrayDescriptionInternal((ArrayVariable<?>) v, (ArrayType<B>) vecType.getElementType(),
                        elementScopes);
            else
                subarray = getArrayDescriptionInternal(innerScope, elementScopes,
                        (ArrayType<B>) vecType.getElementType());
            ArrayElement<B> e = new ArrayElement<>(innerScopeDesc, subarray);
            arrayDesc.addElement(e);
            return (ArrayDesc<A>) arrayDesc;
        }
    }

    /**
     * Utility method to turn an array of IntVariables into a list of IR trees. If other variables are required to be
     * constructed as part of the overall function they will be added to the scope from the getForwardIR calls in this
     * method, and can be recovered from the compilation context.
     * 
     * @param argVars        The array of variables we want to generate trees for.
     * @param compilationCtx THe context in which the trees will be generated.
     * @return A list of IRTrees that represent the array of variables passed.
     */
    public static List<IRTreeReturn<IntVariable>> toArgTrees(IntVariable[] argVars, CompilationContext compilationCtx) {
        List<IRTreeReturn<IntVariable>> trees = new ArrayList<>();
        for(IntVariable v:argVars) {
            IRTreeReturn<IntVariable> tree = v.getForwardIR(compilationCtx);
            trees.add(tree);
        }
        return trees;
    }

    /**
     * Utility method to turn a list of IntVariables into a list of IR trees. If other variables are required to be
     * constructed as part of the overall function they will be added to the scope from the getForwardIR calls in this
     * method, and can be recovered from the compilation context.
     * 
     * @param argVars        The array of variables we want to generate trees for.
     * @param compilationCtx THe context in which the trees will be generated.
     * @return
     */
    public static List<IRTreeReturn<IntVariable>> toArgTrees(List<IntVariable> argVars,
            CompilationContext compilationCtx) {
        return toArgTrees(argVars.toArray(new IntVariable[argVars.size()]), compilationCtx);
    }

    /**
     * Method to construct a variable to the sizes set by a list of scopes. This is not appropriate for variables that
     * are defined in models.
     * 
     * @param varDesc        The name of the variable.
     * @param value          The value to set each element in the newly constructed variable to.
     * @param scopes         The set of scopes that define the size of the variable.
     * @param isPrivate      Should this field be private?
     * @param compilationCtx
     * @return
     */
    public static <X extends Variable<X>> IRTreeVoid constructVariable(VariableDescription<X> varDesc,
            IRTreeReturn<X> value, List<ScopeDesc> scopes, boolean isPrivate, CompilationContext compilationCtx) {
        return constructVariable(varDesc, value, scopes, isPrivate, null, compilationCtx);
    }

    /**
     * Method to construct a variable to the sizes set by a list of scopes. This is not appropriate for variables that
     * are defined in models.
     * 
     * @param varDesc        The name of the variable.
     * @param value          The value to set each element in the newly constructed variable to.
     * @param scopes         The set of scopes that define the size of the variable.
     * @param isPrivate      Should this field be private?
     * @param comment        Any comment that should go with this variable.
     * @param compilationCtx
     * @return
     */
    public static <A extends Variable<A>, X extends Variable<X>> IRTreeVoid constructVariable(
            VariableDescription<X> varDesc, IRTreeReturn<X> value, List<ScopeDesc> scopes, boolean isPrivate,
            String comment, CompilationContext compilationCtx) {
        int size = scopes.size() - 1;
        if(size == 0) {
            compilationCtx.addConstructedClassField(varDesc, isPrivate);
            return store(varDesc, value, Tree.NoComment);
        } else {
            // Construct a descriptor of the array.
            ArrayDesc<A> arrayDesc = getArrayDescription(scopes, varDesc.type);
            VariableDescription<ArrayVariable<A>> arrayName = VariableNames.altTypeName(varDesc, arrayDesc.type);
            compilationCtx.addConstructedClassField(arrayName,
                    // Allocate the variable
                    allocate(arrayName, arrayDesc, compilationCtx), isPrivate, comment);
            // Initialize it to value.

            return initialize(arrayName, value, arrayDesc, compilationCtx);
        }
    }

    /**
     * Method to construct a variable to the sizes set by a list of scopes. This is not appropriate for variables that
     * are defined in models.
     * 
     * @param varDesc        The name of the variable
     * @param value          The value to set each element in the newly constructed variable to.
     * @param pattern        A variable to use as a pattern for the shape of the variable to construct. This is used for
     *                       arrays what should be the same shape as a variable described in the model, but possibly not
     *                       the same type.
     * @param isPrivate      Should this field be private?
     * @param comment        Any comment that should go with this variable.
     * @param compilationCtx The compilation context for this compilation process.
     * @return
     */
    public static <A extends Variable<A>, X extends ScalarVariable<X>> IRTree constructVariable(
            VariableDescription<X> varDesc, IRTreeReturn<X> value, Variable<?> pattern, boolean isPrivate,
            String comment, CompilationContext compilationCtx) {
        if(pattern.getType().getDepth() == 0) {
            compilationCtx.addConstructedClassField(varDesc, isPrivate);
            return store(varDesc, value, Tree.NoComment);
        } else {
            // Construct a descriptor of the array.
            @SuppressWarnings("unchecked")
            ArrayDesc<A> arrayDesc = (ArrayDesc<A>) getArrayDescription(pattern, Collections.emptyList(), varDesc.type);
            VariableDescription<ArrayVariable<A>> arrayName = VariableNames.altTypeName(varDesc, arrayDesc.type);
            compilationCtx.addConstructedClassField(varDesc,
                    // Allocate the variable
                    allocate(arrayName, arrayDesc, compilationCtx), isPrivate, comment);
            // Initialize it to value.
            return initialize(arrayName, value, arrayDesc, compilationCtx);
        }
    }

    /**
     * Method to construct a new iteration, with the correct outer scope. Additional checks may be required here.
     * 
     * @param scope    The new scope that the tasks should use.
     * @param toRebase The for statement that we are copying with the new base.
     * @return
     *
     *         The for is rebased, not the indexes it uses this means the start, end, step and index are in the wrong
     *         scope. This will only be a problem when they are generated from values in a different scope, that
     *         required the allocation of variables. This should never happen, the only possible time is the allocation
     *         of arrays and then their lengths being used as the length of the for, but tracking array lengths should
     *         prevent this being an issue.
     */
    private static ScopeDesc rebaseScope(Scope scope, ScopeDesc toRebase) {
        if(toRebase.scope.getScopeType() != ScopeType.FOR)
            throw new CompilerException("Scope type: " + toRebase.scope.getScopeType() + " cannot be rebased.");
        ForTask f = (ForTask) toRebase.scope;
        ScopeStack.pushScope(scope);
        IntVariable start = f.getStart().copy();
        IntVariable end = f.getEnd().copy();

        ForTask newNoStates = null;
        try {
            newNoStates = Sandwood.forLoop(start, end, Variable.intVariable(1), true, (i) -> { // TODO work out is we
                // need a test here to
                // consider making the
                // for parallel?
                i.setUniqueVarDesc(f.getIndex().getUniqueVarDesc());
            });
        } catch(SandwoodException e) {
            // This should never throw.
            e.printStackTrace();
        }

        ScopeStack.popScope(scope);

        return new ScopeDesc(newNoStates, toRebase.index, false);
    }

    /**
     * Method that takes a list of linked scopes and copies them so that the outer scope is now the provides scope.
     * Additional checks may be required here.
     * 
     * @param scope    The new scope that the tasks should use.
     * @param toRebase List of scopes to move to a new base.
     * @return
     */
    private static List<ScopeDesc> rebaseScopes(Scope scope, List<ScopeDesc> toRebase) {
        List<ScopeDesc> newScopes = new ArrayList<>();
        for(ScopeDesc s:toRebase) {
            ScopeDesc scopeDesc = rebaseScope(scope, s);
            scope = scopeDesc.scope;
            newScopes.add(scopeDesc);
        }

        return newScopes;
    }

    public interface StoreValue<A extends Variable<A>> {
        IRTreeVoid store(IRTreeReturn<A> value);
    }

    /**
     * Method to perform LSE on a pair of variables storing the result in target. Which of these variables is larger is
     * not required to be known.
     * 
     * @param accumulator One of the variables this should be the one expected to be larger, and initialized to
     *                    -Infinity if one value is.
     * @param increment   The other variable, this should be the one calculated directly.
     * @param target      The name of the location to store the result.
     * @param comment
     * @return The resulting tree structure.
     */
    public static IRTreeVoid lseAdd(IRTreeReturn<DoubleVariable> accumulator, IRTreeReturn<DoubleVariable> increment,
            VariableDescription<DoubleVariable> target, String comment) {
        return lseAdd(accumulator, increment,
                (IRTreeReturn<DoubleVariable> value) -> store(target, value, Tree.NoComment), comment);
    }

    public static IRTreeVoid lseAdd(IRTreeReturn<DoubleVariable> accumulator, IRTreeReturn<DoubleVariable> increment,
            StoreValue<DoubleVariable> target, String comment) {
        IRTreeReturn<BooleanVariable> guard = lessThan(increment, accumulator);
        IRTreeVoid bOffset = lse(increment, accumulator, target); // b is greater so is used as the offset.
        // A is greater than or equal, so we need to test that both values are not
        // negative infinity
        // to avoid NaN issues.
        IRTreeVoid aOffset = ifElse(eq(accumulator, constant(Double.NEGATIVE_INFINITY)), target.store(increment),
                "If the second value is -infinity.", lse(accumulator, increment, target), Tree.NoComment);
        IRTreeVoid toReturn = ifElse(guard, bOffset, comment, aOffset, Tree.NoComment);
        toReturn.addTag(Tag.LSE);
        return toReturn;
    }

    /**
     * Method to perform LSE on a pair of variables storing the result in target. Variable a must be less than equal to
     * variable b.
     * 
     * @param a      One of the variables
     * @param b      The other variable
     * @param target The name of the location to store the result.
     * @return The resulting tree structure.
     */
    private static IRTreeVoid lse(IRTreeReturn<DoubleVariable> a, IRTreeReturn<DoubleVariable> b,
            StoreValue<DoubleVariable> target) {
        // Using b as the offset.
        IRTreeReturn<DoubleVariable> t = subtractDD(a, b);
        t = exp(t);
        // b-b will be 0, so the exponent of this will be 1.
        t = addDI(t, constant(1));
        t = log(t);
        t = addDD(t, b);
        return target.store(t);
    }

    public static IRTreeVoid lseAdd(IRTreeReturn<ArrayVariable<DoubleVariable>> arrayValue,
            VariableDescription<DoubleVariable> targetName, String comment) {
        VariableDescription<DoubleVariable> maxName = VariableNames.calcVarName("lseMax", VariableType.DoubleVariable,
                true);
        VariableDescription<DoubleVariable> elementName = VariableNames.calcVarName("lseElementValue",
                VariableType.DoubleVariable, true);
        VariableDescription<IntVariable> indexName = VariableNames.calcVarName("lseIndex", VariableType.IntVariable,
                true);
        VariableDescription<DoubleVariable> sumName = VariableNames.calcVarName("lseSum", VariableType.DoubleVariable,
                true);

        List<IRTreeVoid> stmts = new ArrayList<>();

        // Initialize value for the max
        stmts.add(initializeVariable(maxName, arrayGet(arrayValue, IRTree.constant(0)),
                "Initialise the max to the first element."));

        // Find the max value
        List<IRTreeVoid> maxBody = new ArrayList<>();
        maxBody.add(initializeVariable(elementName, arrayGet(arrayValue, load(indexName)), Tree.NoComment));
        maxBody.add(ifElse(lessThan(load(maxName), load(elementName)),
                store(maxName, load(elementName), Tree.NoComment), Tree.NoComment));
        stmts.add(forStmt(sequential(maxBody, Tree.NoComment), constant(1), getIntField(arrayValue, "length"),
                constant(1), indexName, true, "Find max value."));

        // Sum all the values if max is not -infinity, otherwise return -infinity
        List<IRTreeVoid> sumStmts = new ArrayList<>();
        sumStmts.add(initializeVariable(sumName, constant(0.0), "Initialise the sum of the array elements"));
        IRTreeVoid sumBody = store(sumName,
                addDD(load(sumName), exp(subtractDD(arrayGet(arrayValue, load(indexName)), load(maxName)))),
                Tree.NoComment);
        sumStmts.add(forStmt(sumBody, constant(0), getIntField(arrayValue, "length"), constant(1), indexName, true,
                "Offset values, move to normal space, and sum."));
        sumStmts.add(store(targetName, addDD(load(targetName), addDD(log(load(sumName)), load(maxName))),
                "Increment the value of the target, moving the value back into log space."));

        // Guard against all values being -infinity.
        stmts.add(ifElse(eq(load(maxName), constant(Double.NEGATIVE_INFINITY)),
                store(targetName, constant(Double.NEGATIVE_INFINITY), Tree.NoComment),
                "If the maximum value is -infinity return -infinity.", sequential(sumStmts, Tree.NoComment),
                "Sum the values in the array."));

        return treeScope(sequential(stmts, Tree.NoComment), comment);
    }

    public static <A extends Variable<A>> IRTreeVoid copyArray(IRTreeReturn<ArrayVariable<A>> source,
            IRTreeReturn<ArrayVariable<A>> target) {
        IRTreeVoid toReturn = arrayCopy(source, target, 1);
        toReturn.postfixComment("Deep copy between arrays");
        toReturn.addTag(Tag.ARRAY_COPY);
        return IRTree.treeScope(toReturn, Tree.NoComment);
    }

    private static <A extends Variable<A>, B extends Variable<B>> IRTreeVoid arrayCopy(
            IRTreeReturn<ArrayVariable<A>> source, IRTreeReturn<ArrayVariable<A>> target, int i) {
        List<IRTreeVoid> ts = new ArrayList<>();

        VariableDescription<ArrayVariable<A>> sourceName = VariableNames.calcVarName("source" + i,
                source.getOutputType(), true);
        VariableDescription<ArrayVariable<A>> targetName = VariableNames.calcVarName("target" + i,
                source.getOutputType(), true);
        VariableDescription<IntVariable> lengthName = VariableNames.calcVarName("length" + i, VariableType.IntVariable,
                true);
        VariableDescription<IntVariable> indexName = VariableNames.calcVarName("index" + i, VariableType.IntVariable,
                true);

        // Local variables for the arrays.
        ts.add(initializeVariable(sourceName, source, Tree.NoComment));
        ts.add(initializeVariable(targetName, target, Tree.NoComment));
        ts.add(initializeVariable(lengthName, getIntField(load(targetName), "length"), Tree.NoComment));

        // Copy the data one dimension down
        IRTreeVoid body;
        if(source.getOutputType().getDepth() == 1)
            body = arrayPut(load(targetName), load(indexName), arrayGet(load(sourceName), load(indexName)),
                    Tree.NoComment);
        else
            body = arrayCopy((IRTreeReturn<ArrayVariable<B>>) arrayGet(load(sourceName), load(indexName)),
                    (IRTreeReturn<ArrayVariable<B>>) arrayGet(load(targetName), load(indexName)), i + 1);

        ts.add(forStmt(body, constant(0), load(lengthName), constant(1), indexName, true, Tree.NoComment));

        return sequential(ts, Tree.NoComment);
    }
}
