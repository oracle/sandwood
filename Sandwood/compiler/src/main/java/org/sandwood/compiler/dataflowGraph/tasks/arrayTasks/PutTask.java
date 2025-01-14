/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.arrayTasks;

import static org.sandwood.compiler.trees.irTree.IRTree.arrayGet;
import static org.sandwood.compiler.trees.irTree.IRTree.arrayPut;
import static org.sandwood.compiler.trees.irTree.IRTree.initializeVariable;
import static org.sandwood.compiler.trees.irTree.IRTree.load;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.ElseScope;
import org.sandwood.compiler.dataflowGraph.scopes.IfScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.ArrayProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ForTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableName;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.VariableWrapper;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.exceptions.SandwoodModelException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.TraceConstructionDesc;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.Visibility;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class PutTask<A extends Variable<A>> extends ProducingDataflowTaskImplementation<ArrayVariable<A>>
        implements ArrayProducingDataflowTask<A> {
    public final ArrayVariable<A> array;
    public final Variable<A> value;
    public final IntVariable index;
    private final boolean implicit;
    private final List<SandwoodModelException> loggedErrors = new ArrayList<>();

    public PutTask(ArrayVariable<A> array, IntVariable index, Variable<A> value, boolean implicit, Location location) {
        super(DFType.PUT, array.getType(), location, array.getCurrentInstance(), index, value);
        this.array = array.getCurrentInstance();
        this.value = value.getCurrentInstance();
        this.index = index;
        inlineableTask = false;
        this.implicit = implicit;

        // If the array only becomes a distribution now.
        if(value.isDistribution() && !array.isDistribution()) {
            setDistributions();
        }
    }

    private void setDistributions() {
        Scope s = scope();
        Scope outerScope = null;
        while(s != null) {
            if(s.iterating())
                outerScope = s;
            s = s.getEnclosingScope();
        }

        // If earlier operations could see the value placed here.
        if(outerScope != null)
            setDistributions(outerScope);
    }

    private void setDistributions(Scope outerScope) {
        DataflowTask<ArrayVariable<A>> parent = array.getParent();
        switch(parent.getType()) {
            case PUT: {
                Scope parentScope = parent.scope();
                while(parentScope != null && parentScope != outerScope)
                    parentScope = parentScope.getEnclosingScope();

                if(parentScope == outerScope) {
                    parent.setIsDistribution();
                    ((PutTask<A>) parent).setDistributions(outerScope);
                } else
                    setConsumerDistribution(outerScope, array);
                break;
            }
            case ARRAY_CONSTRUCTOR:
            case COPY:
            case GET:
            case NAMED_VARIABLE:
            case OBSERVE_VARIABLE:
            case REDUCTION_RETURN:
            case SAMPLE:
                setConsumerDistribution(outerScope, array);
                break;
            default:
                throw new CompilerException("Array parent " + parent
                        + " is not currently handled by the methods for setting distributions");
        }
    }

    private void setConsumerDistribution(Scope outerScope, ArrayVariable<A> array) {
        for(DataflowTask<?> t:array.getConsumers()) {
            if(t != this) {
                Scope taskScope = t.scope();
                while(taskScope != null && taskScope != outerScope)
                    taskScope = taskScope.getEnclosingScope();

                if(taskScope == outerScope)
                    t.setIsDistribution();
            }
        }
    }

    @Override
    public void testTask(List<SandwoodModelException> errors) {
        errors.addAll(loggedErrors);
        testIndex(errors);
        testArray(errors);
        testValue(errors);
    }

    public void assignmentToSameArrayElement() {
        VariableName arrayName = getArraySourceName(array);
        VariableName valueName = getArraySourceName((ArrayVariable<?>) value);

        loggedErrors.add(
                new SandwoodModelException("Array " + valueName + " is being assigned to the same location in array "
                        + arrayName + ". This is not permitted due to the single assignment semantics.", this));
    }

    public void assignmentToSameArrayDifferntElements() {
        VariableName arrayName = getArraySourceName(array);
        VariableName valueName = getArraySourceName((ArrayVariable<?>) value);

        loggedErrors
                .add(new SandwoodModelException(
                        "Array " + valueName + " is being assigned to the multiple location in array " + arrayName
                                + ". This is not permitted, each array may only exist in once in an another array.",
                        this));

    }

    public void assignmentToMultipleArrays() {
        VariableName arrayName1 = getArraySourceName(array);
        VariableName arrayName2 = getArraySourceName(((ArrayVariable<?>) value).getOuterArrayDesc().getArray());

        loggedErrors.add(new SandwoodModelException("References to arrays may not be held in multiple "
                + "different arrays. Attempt to assign the same array to " + arrayName1 + " and " + arrayName2 + ".",
                this));
    }

    public void testArray(List<SandwoodModelException> errors) {
        if(array.isInput && !implicit) {// Implicit puts are ignored to prevent duplicate error messages.
            VariableName arrayName = getArraySourceName(array);

            if(array.aliasSet() && !arrayName.getName().equals(array.getAlias()))
                errors.add(new SandwoodModelException("Unable to write to \"" + arrayName + "\" via \""
                        + array.getAlias() + "\" as it is an input and therefore" + " a constant.", this));
            else
                errors.add(new SandwoodModelException(
                        "Unable to write to \"" + arrayName + "\" as it " + "is an input and therefore a constant.",
                        this));
        }
    }

    /**
     * A method to test that the index uses all the indexes from any encompassing for scopes. If this is not the case we
     * cannot have single assignment semantics. The only possible exception to this would be if someone was using a for
     * loop as a conditional.
     */
    private void testIndex(List<SandwoodModelException> errors) {
        if(index.isDistribution())
            errors.add(new SandwoodModelException(
                    "Put indexes cannot currently be distributions as the evaluation would require recursion.", this));
        if(!index.isDeterministic())
            errors.add(new SandwoodModelException(
                    "Put indexes cannot be non-deterministic as doing so could result in values being assigned twice.",
                    this));

        if(!implicit) {
            // Build set of required indexes
            Scope endScope = array.getSource().scope();
            Set<IntVariable> requiredIndexes = new HashSet<>();
            Scope scope = scope();

            while(scope != endScope) {
                switch(scope.getScopeType()) {
                    case FOR:
                        ForTask ft = (ForTask) scope;
                        requiredIndexes.add(ft.getIndex());
                        break;
                    case BLOCK:
                    case COMMENT:
                    case ELSE:
                    case GLOBAL:
                    case IF:
                    case REDUCE:
                    default:
                        break;
                }
                scope = scope.getEnclosingScope();
            }

            // Construct a stack to hold all the values we want to examine.
            Stack<Variable<?>> toProcess = new Stack<>();

            // Add in the put and all the get indexes
            {
                toProcess.push(index);
                ArrayVariable<?> vec = array.instanceHandle();
                DataflowTask<?> t = vec.getParent();
                while(t.getType() == DFType.GET) {
                    GetTask<?> gt = (GetTask<?>) t;
                    toProcess.add(gt.index);
                    vec = gt.array.instanceHandle();
                    t = vec.getParent();
                }
            }

            // Add in all the if and else guards
            {
                Scope s = scope();
                Scope arrayScope = array.scope();
                while(s != arrayScope) {
                    switch(s.getScopeType()) {
                        case ELSE:
                            toProcess.add(((ElseScope) s).ifScope.guard);
                            break;
                        case IF:
                            toProcess.add(((IfScope) s).guard);
                            break;
                        default:
                            break;

                    }
                    s = s.getEnclosingScope();
                }
            }

            // Search to ensure that all indexes are present
            while(!toProcess.isEmpty()) {
                Variable<?> v = toProcess.pop();
                DataflowTask<?> t = v.getParent();

                // If we come across a for index check if it is restricted.
                DFType type = t.getType();
                if((type == DFType.FOR || type == DFType.PAR_FOR))
                    requiredIndexes.remove(v);
                // Otherwise continue to explore the graph if we have not reached a sample where
                // the trace will stop.
                else if(!(type == DFType.SAMPLE))
                    for(Variable<?> in:t.getInputs())
                        toProcess.push(in);
            }

            if(!requiredIndexes.isEmpty()) {
                String indexString;
                if(requiredIndexes.size() == 1)
                    indexString = "index";
                else
                    indexString = "indexes";

                for(IntVariable i:requiredIndexes)
                    indexString = indexString + " \"" + i.getAlias() + "\"";

                errors.add(new SandwoodModelException("Missing reference to " + indexString + " when writing to array "
                        + "\"" + array.getSource().getOutput().getVarDesc()
                        + "\". All loop indexes between the declaration "
                        + "of the array and the assignment to it must be used to ensure single assignment semantics are "
                        + "maintained.", this));
            }
        }
    }

    private void testValue(List<SandwoodModelException> errors) {
        if(!value.isPrivate() && value.getType().isArray()) {
            errors.add(new SandwoodModelException(
                    "Public arrays cannot be placed into arrays as this would provide multiple access points to the "
                            + "array's internal state.",
                    this));
        }
    }

    @Override
    public String getSandwoodString(boolean compressSandwoodCode) {
        return array.getExpression(false) + "[" + index.getExpression(compressSandwoodCode) + "] = "
                + value.getExpression(compressSandwoodCode) + ";";
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return null;
    }

    public boolean isImplicit() {
        return implicit;
    }

    @Override
    public IRTreeReturn<ArrayVariable<A>> getForwardIRinternal(CompilationContext compilationCtx) {

        // Setup the array ready for access
        IRTreeReturn<ArrayVariable<A>> arrayTree;
        if(compilationCtx.initialized(array)) {
            ArrayVariable<A> stopPoint = getStopPoint();
            stopPoint.markStopPoint();
            arrayTree = array.getForwardIR(compilationCtx);
            stopPoint.unmarkStopPoint();
        } else {
            arrayTree = array.getForwardIR(compilationCtx);
            // If the call did not initialise the array initialise it now.
            if(!array.isIntermediate() && !compilationCtx.initialized(array)) {
                IRTreeVoid t = initializeVariable(Visibility.DEFAULT, array.getUniqueVarDesc(), arrayTree,
                        Tree.NoComment);
                // Find the outermost safe scope to place the value in.
                Scope targetScope;
                if(array.aliasSet())
                    targetScope = array.scope();
                else {
                    List<Variable<?>> inputs = array.getParent().getInputs();
                    int size = inputs.size();
                    if(size == 0)
                        targetScope = array.scope();
                    else {
                        targetScope = inputs.get(0).scope();
                        for(int i = 1; i < size; i++)
                            targetScope = Scope.innerScope(targetScope, inputs.get(i).scope());
                    }
                }

                compilationCtx.addTreeToScope(targetScope, t);
                compilationCtx.addInitialized(array);
                arrayTree = load(array);
            }
        }

        // Apply the put
        if(compilationCtx.requiredArray(array) && !isAllocation()) {
            if(value.getType().isArray()) {
                /*
                 * We are assigning arrays to this array. This is only going to happen by a function that produces
                 * arrays or a call to put.
                 *
                 * For functions as we want to allocate all memory before we start, these functions take the array they
                 * are going to generate as an argument. As such we do not need to capture the output of the called
                 * function in value, but we do need to make sure the array it will output has been loaded into scope
                 * before it is used in the function call.
                 *
                 * For a put, we will load each value into scope until we reach the function generating the value, or a
                 * put that is taking base type. This final put will place the value into the array, so again all that
                 * is required is to ensure that the correct data is in scope.
                 *
                 * The exception to this is if the parent is a get. In this case a subarray of one array is being loaded
                 * and placed in another array. This will overwrite any state that is already in the array.
                 */
                ArrayVariable<?> arrayValue = (ArrayVariable<?>) value;
                if(!compilationCtx.initialized(arrayValue)) {
                    compilationCtx.addInitialized(arrayValue);
                    IRTreeVoid t = initializeVariable(Visibility.DEFAULT, value.getUniqueVarDesc(),
                            arrayGet(arrayTree, index.getForwardIR(compilationCtx)), Tree.NoComment);
                    Scope targetScope = value.aliasSet() ? value.scope()
                            : Scope.innerScope(array.scope(), index.scope());
                    compilationCtx.enterScope(value.scope());
                    // TODO once this and the call below is removed, remove this function from compilation context
                    compilationCtx.addTreeToScope(targetScope, t, value.instanceHandle().getParent());
                    compilationCtx.leaveScope(value.scope());
                }
                value.getForwardIR(compilationCtx);
            } else {
                /*
                 * This is not array an array that is being assigned other arrays. We treat this one normally.
                 */
                IRTreeVoid t = arrayPut(arrayTree, index.getForwardIR(compilationCtx),
                        value.getForwardIR(compilationCtx), Tree.NoComment);
                compilationCtx.addTreeToScope(scope(), t);
            }
        }

        // Return the array for any later tasks to use.
        return arrayTree;
    }

    private ArrayVariable<A> getStopPoint() {
        if(!array.isSubArray())
            return array;
        ArrayVariable<A> a = array;
        while(true) {
            Set<DataflowTask<?>> consumers = a.getConsumers();
            for(DataflowTask<?> c:consumers) {
                if(c.getType() == DFType.PUT && ((PutTask<?>) c).value == a)
                    return a;
            }

            DataflowTask<ArrayVariable<A>> p = a.getParent();
            if(p.getType() == DFType.PUT) {
                a = ((PutTask<A>) p).array;
            } else
                return a;
        }
    }

    private VariableName getArraySourceName(ArrayVariable<?> a) {
        return a.getSourceInstance().getVarDesc().name;
    }

    private boolean isAllocation() {
        Variable<?> v = value;
        ProducingDataflowTask<?> p = v.getParent();
        DFType t = p.getType();
        while(t == DFType.PUT) {
            v = ((PutTask<?>) p).value;
            p = v.getParent();
            t = p.getType();
        }
        return v.getParent().getType() == DFType.ARRAY_CONSTRUCTOR;
    }

    @Override
    public IRTreeReturn<?> getInverseIRInternal(int argPos, IRTreeReturn<ArrayVariable<A>> taskOutput,
            BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        // Update the substitutions of the scope constructor to reflect the position in
        // the trace that we have reached.
        backTraceInfo.updateSubstitutions(this, compilationCtx);
        switch(argPos) {
            case 0:
                throw new CompilerException("Assignments to arrays where the input is the array"
                        + " being assigned to should not appear in traces.");
            case 1:
                // The array was indexed by the value of a sample. If and only if we are sure
                // the array only contains unique elements
                // we could construct a reverse lookup and use that to determine the value of i
                // based on the forward value of arg 3 and
                // the current state of the array.
                throw new CompilerException("Unable to construct inverse function as put "
                        + "tasks cannot currently determine the index a value came from.");
            case 2:
                if(backTraceInfo.noGetValues() > 0)
                    return backTraceInfo.getGetValue();
                else
                    return IRTree.arrayGet(taskOutput, index.getForwardIR(compilationCtx));
        }
        throw new CompilerException("Unknown operation put only accepts arguments in positions 0-2");
    }

    @Override
    public String checkInversionError(int argPos) {
        switch(argPos) {
            case 0:
                throw new CompilerException("Assignments to arrays where the input is the array"
                        + " being assigned to should not appear in traces.");
            case 1:
                return "Unable to construct inverse function as put "
                        + "tasks cannot currently determine the index a value came from.";
            case 2:
                return null;
        }
        throw new CompilerException("Unknown operation put only accepts arguments in positions 0-2");
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(getType() != other.getType())
            return false;
        PutTask<?> t = (PutTask<?>) other;
        return index.equivalent(t.index) && value.equivalent(t.value) && array.equivalent(t.array);
    }

    @Override
    public Set<VariableWrapper<IntVariable>> getPossibleLengths() {
        return array.getPossibleLengths();
    }

    @Override
    public void constructTrace(TraceConstructionDesc desc) {
        // Test if this is just putting back a value read by an implicit get. If it is do not explore the
        // value.
        boolean skipImplicit = false;

        // Ensure this get is not providing the value to an implicit put on the same
        // array cell.
        if(!desc.trace.isEmpty()) {
            DataflowTaskArgDesc d = desc.trace.peek();
            if(d.task.getType() == DFType.GET) {
                @SuppressWarnings("unchecked")
                GetTask<A> gt = (GetTask<A>) d.task;

                /*
                 * If this put is writing the value to the array that get is reading do not explore the put.
                 */
                if(gt.getOutput().instanceHandle() == value.instanceHandle() && gt.array.instanceHandle() == array.instanceHandle()) {
                    skipImplicit = true;
                }
            }
        }

        int i = 0;
        DataflowTask<?> arrayEntryPoint = desc.arrayEntryPoint;
        for(Variable<?> in:getInputs()) {
            if(in == array) {
                // When handling traces only the put task that modifies the array element we are
                // interested in should be added to the trace. As such when the trace follows up
                // the array, the task should not be added to the trace.

                // Test if the last operation was an implicit put which this task generated the value for.
                boolean implicitValue;
                if(desc.trace.isEmpty())
                    implicitValue = false;
                else {
                    DataflowTaskArgDesc d = desc.trace.peek();
                    if(d.task.getType() == DFType.PUT) {
                        PutTask<?> pt = (PutTask<?>) d.task;
                        implicitValue = d.argPos == 2 && pt.isImplicit();
                    } else
                        implicitValue = false;
                }

                // The first put we find will trigger all the searches for this array so additional
                // searches will not be required. If this tasks is reached as the value of an implicit put, the other
                // arrays will be reached via other put operations.
                if(desc.firstPut && !implicitValue) {
                    // This is the first put for this array, so going forward it is false.
                    desc.firstPut = false;
                    desc.seenVar.remove(output);

                    // For most cases there will be a get task, if the put doesn't have a
                    // corresponding get we use the scope of the sink location.
                    if(desc.arrayEntryPoint == null) {
                        arrayEntryPoint = desc.sink.getParent();
                        desc.arrayEntryPoint = arrayEntryPoint;
                    }
                    Set<ArrayVariable<A>> altArrays = array.getPossibleInstances(desc.arrayEntryPoint.scope(),
                            desc.arrayEntryPoint.id());
                    // Remove this array as we are already exploring it and explore the remaining arrays.
                    altArrays.remove(output);
                    for(ArrayVariable<A> v:altArrays) {
                        v.constructTrace(desc);
                    }

                    desc.seenVar.add(output);
                    // restore the state of the flags
                    desc.firstPut = true;
                }
            } else {
                // Explore the other inputs if skip implicit is not true.
                if(!skipImplicit) {
                    if(!isImplicit())
                        desc.addRestrictedIndexes(this);

                    // Add this task to the trace, this is only done for values and indexes, as if
                    // the input is the array this task does not change the data covered by this
                    // trace
                    desc.trace.push(new DataflowTaskArgDesc(this, i));

                    // Set the first put flag to false, as an array is being added to
                    // this array, so this is still the first put.
                    boolean firstPut = desc.firstPut;
                    desc.firstPut = true;

                    // If this is an array variable then the value being placed into the array is
                    // another array. As such all possible puts to this array that could affect the
                    // original get are required.
                    if(in.getType().isArray()) {

                        in.constructTrace(desc);
                    } else {
                        // We are starting a new trace through arrays as we are following
                        // the index or a scalar value variable, so clear the initial get field.
                        desc.arrayEntryPoint = null;

                        // Explore the trace
                        in.constructTrace(desc);

                        // Restore the get task for this trace.
                        desc.arrayEntryPoint = arrayEntryPoint;
                    }

                    // restore the first put flag;
                    desc.firstPut = firstPut;

                    desc.trace.pop();

                    if(!isImplicit())
                        desc.removeRestrictedIndexes(this);
                }
            }
            i++;
        }
    }

    @Override
    public IRTreeReturn<IntVariable> getMaxLength(CompilationContext compilationCtx) {
        throw new CompilerException(
                "This can only be called on the creator of the instance handle, and put tasks cannot create instance handles.");
    }

    @Override
    public IRTreeReturn<IntVariable> getMinLength(CompilationContext compilationCtx) {
        throw new CompilerException(
                "This can only be called on the creator of the instance handle, and put tasks cannot create instance handles.");
    }

    /**
     * Method used to find the outermost array instance resulting from this put operation.
     * 
     * @return The outermost array instance resulting from this put operation.
     */
    public ArrayVariable<?> findOuterArray() {
        ArrayVariable<?> v = output;
        while(true) {
            // If this is an outer array, return.
            if(!v.isSubArray())
                return v;

            Set<DataflowTask<?>> consumers = v.getConsumers();
            boolean outerArrayFound = false;
            // Search for this array being put into an outer array.
            for(DataflowTask<?> consumer:consumers) {
                if(consumer.getType() == DFType.PUT) {
                    PutTask<?> p = (PutTask<?>) consumer;
                    if(p.value == v) {
                        outerArrayFound = true;
                        v = p.getOutput();
                        break;
                    }
                }
            }

            // If this array is not put into an outer array look to see if it is after the next assignment to this
            // array.
            if(!outerArrayFound) {
                v = v.getChildInstance();
                assert v != null;
            }
        }
    }
}
