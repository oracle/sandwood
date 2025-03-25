/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.util;

import java.util.ArrayList;
import java.util.List;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.PutTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.traces.Trace;
import org.sandwood.compiler.traces.Traces;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

/**
 * An object to describe the operations involved in setting new values for an array.
 *
 * @param <A> The type of the elements of the array.
 * @param <B> The random variable that the sample is drawn from.
 */
public class SampleDescArray<A extends Variable<A>, B extends RandomVariable<ArrayVariable<A>, B>>
        extends SampleDesc<ArrayVariable<A>, B> {
    private Variable<?> targetIntermediate;
    private final List<IntVariable> targetIndexes = new ArrayList<>();

    public SampleDescArray(SampleTask<ArrayVariable<A>, B> sample, Traces traces) {
        super(sample, traces);

        // Search for an intermediate variable we can write to.
        Trace trace = sampleVarDesc.traceToSampleVariable.getTrace();
        DataflowTaskArgDesc d = trace.pop();
        while(d.task.getType() == DFType.PUT) {
            if(d.argPos == 2)
                targetIndexes.add(((PutTask<?>) d.task).index);
            else
                throw new CompilerException(
                        "Uninvertable put task found in the trace from the sample task to the sample variable.");
            d = trace.pop();
        }

        if(d.task.getType() != DFType.SAMPLE)
            throw new CompilerException(
                    "Unexpected task found in the trace from the sample task to the sample variable.");

        // If we have found a usable variable use it, otherwise clear the indexes ready
        // for the next try.
        targetIntermediate = sampleVarDesc.sampleVariable;
    }

    /**
     * Method to update the value of the sample.
     * 
     * @param sampleValue    A tree describing the new value to use.
     * @param compilationCtx The compilation context.
     */
    public void updateSample(IRTreeVoid sampleValue, CompilationContext compilationCtx) {
        // The tree will already load the array to be set, so it just needs adding to
        // the scopes.
        compilationCtx.addTreeToScope(GlobalScope.scope, sampleValue);
        // Then any intermediates need setting.
        setIntermediateValues(compilationCtx);
    }

    /**
     * Method to determine if an array to target the new values into has been located.
     * 
     * @return Has a target array been found.
     */
    public boolean targetFound() {
        return targetIntermediate != null;
    }

    /**
     * Method to get the index values used to access the appropriate part of the target array.
     * 
     * @return A list of IntVariables describing the correct point in the target array to write to.
     */
    public List<IntVariable> targetIndexes() {
        return targetIndexes;
    }

    /**
     * Method to get the variable that is being used as the target.
     * 
     * @return The variable that the new state should be written into.
     */
    public Variable<?> targetIntermediate() {
        return targetIntermediate;
    }
}
