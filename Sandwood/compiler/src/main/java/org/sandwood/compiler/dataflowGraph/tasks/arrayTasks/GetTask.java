/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.arrayTasks;

import static org.sandwood.compiler.trees.irTree.IRTree.arrayGet;

import java.util.Set;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.exceptions.SandwoodModelException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.TraceConstructionDesc;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public abstract class GetTask<A extends Variable<A>> extends ProducingDataflowTaskImplementation<A> {

    public final ArrayVariable<A> array;
    public final IntVariable index;

    public GetTask(ArrayVariable<A> array, IntVariable index, Location location) {
        super(DFType.GET, array.getElementType(), location, array.getCurrentInstance(), index);
        this.array = array.getCurrentInstance();
        this.index = index;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return getInput(0).getExpression(compressSandwoodCode) + "[" + getInput(1).getExpression(compressSandwoodCode)
                + "]";
    }

    @Override
    public IRTreeReturn<A> getForwardIRinternal(CompilationContext compilationCtx) {
        return arrayGet(array.getForwardIR(compilationCtx), index.getForwardIR(compilationCtx));
    }

    @Override
    public IRTreeReturn<ArrayVariable<A>> getInverseIRInternal(int argPos, IRTreeReturn<A> taskOutput,
            BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        /*
         * If argPos is 1 we are looking for the index i such that array[i]==current. This will only be valid if there
         * is exactly one such value. We won't implement this now, but it should be implemented by a function call
         * dataflow task. TODO implement function call dataflow task and appropriate runtime utility.
         */
        switch(argPos) {
            case 0:
                // This relies on the forward pass constructing the required allocators.
                // Only filling in some values is safe as the values we fill in are the ones
                // that appear in the chain.
                backTraceInfo.addGetValue(taskOutput);
                array.markStopPoint();
                IRTreeReturn<ArrayVariable<A>> t = array.getForwardIR(compilationCtx);
                array.unmarkStopPoint();
                return t;
            case 1:
                throw new CompilerException("Unique values are not guaranteed in arrays, this prevents indexes being "
                        + "calculated when inverting the model.");

            default:
                throw new CompilerException("There are only 2 arguments to a get, this value of argPos is not valid.");
        }
    }

    @Override
    public String checkInversionError(int argPos) {
        switch(argPos) {
            case 0:
                return null;
            case 1:
                return "Unique values are not guaranteed in arrays, this prevents indexes being "
                        + "calculated when inverting the model.";

            default:
                throw new CompilerException("There are only 2 arguments to a get, this value of argPos is not valid.");
        }
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(getType() != other.getType())
            return false;
        GetTask<?> t = (GetTask<?>) other;
        return index.equivalent(t.index) && array.equivalent(t.array);
    }

    @Override
    public void constructTrace(TraceConstructionDesc desc) {
        Set<IntVariable> restrictedIndexes = desc.checkRestrictedIndexes(this);
        if(!restrictedIndexes.isEmpty()) {
            String arrayName = array.getSource().getOutput().getAlias();
            String end, indexString;
            if(restrictedIndexes.size() == 1) {
                indexString = "index";
                end = " is";
            } else {
                indexString = "indexes";
                end = " are";
            }

            for(IntVariable i:restrictedIndexes)
                indexString = indexString + " \"" + i.getAlias() + "\"";

            indexString += end;

            throw new SandwoodModelException("Unable to perform array access on array \"" + arrayName + "\" as the "
                    + indexString + " used to write to this array in a trace after it is used for this read. This "
                    + "means that the compiler cannot statically determine how many iterations of these loops are "
                    + "needed when constructing inverse operations for the model.", this);
        }

        // Ensure this get is not providing the value to an implicit put on the same
        // array cell.
        if(!desc.trace.isEmpty()) {
            DataflowTaskArgDesc d = desc.trace.peek();
            if(d.task.getType() == DFType.PUT && d.argPos == 2) {
                @SuppressWarnings("unchecked")
                PutTask<A> pt = (PutTask<A>) d.task;
                if(pt.isImplicit() && pt.array == array && pt.index == index)
                    return;
            }
        }

        // Check that we have not already visited this get.

        // Explore the array
        desc.trace.push(new DataflowTaskArgDesc(this, 0));
        // Is this the first get in this part of the trace?
        if(desc.initialGet == null) {
            desc.initialGet = this;
            array.constructTrace(desc);
            desc.initialGet = null;
        } else {
            array.constructTrace(desc);
        }
        desc.trace.pop();

        // Explore the index
        desc.trace.push(new DataflowTaskArgDesc(this, 1));
        index.constructTrace(desc);
        desc.trace.pop();
    }
}