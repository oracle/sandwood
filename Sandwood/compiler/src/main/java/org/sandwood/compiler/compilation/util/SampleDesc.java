/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.util;

import java.util.List;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.PutTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.traces.Traces;
import org.sandwood.compiler.traces.Traces.IntermediateDesc;
import org.sandwood.compiler.traces.Traces.SampleTraceDesc;

/**
 * Class to describe a sample task during variable inference.
 * 
 * @param <A> The type of the sample task output.
 * @param <B> The Type of the random variable that the sample is drawn from.
 */
public abstract class SampleDesc<A extends Variable<A>, B extends RandomVariable<A, B>> {
    /** The sample task */
    public final SampleTask<A, B> sample;
    /** A list of all the scopes that the sample task is encased in. */
    public final List<Scope> scopes;

    public final List<IntVariable> variableIndexes;
    /** The id of the sample task */
    public final int id;
    /** The name to use for variables representing the output of the sample task. */
    public final VariableDescription<A> uniqueName;

    // Shorthand to keep dereferencing more readable
    /**
     * The intermediate variable that is the outermost point before the value starts being consumed.
     */
    public final A output;

    /** Collection of all the intermediate values. */
    private final IntermediateDesc intermediates;

    /** An object consisting the traces that construct and use this sample value. */
    public final SampleTraceDesc sampleVarDesc;

    protected SampleDesc(SampleTask<A, B> sample, Traces traces) {
        // Record the sample task.
        this.sample = sample;
        // Record the sample task scopes.
        scopes = TreeUtils.getScopes(sample);
        // Record the sample task ID and unique name.
        // These are here as shorthand to make the using code more
        // readable.
        id = sample.id();
        uniqueName = sample.getUniqueVarDesc();

        // For convenience copy the state of the intermediate description.
        output = sample.getOutput();

        // Get any intermediate variables that will need updating when the output of the
        // sample is updated.
        intermediates = traces.getIntermediates(sample);

        // Get the traces between the sample task and the consumers of its values.
        sampleVarDesc = traces.getSampleTrace(sample);

        // Add all the indexes used to get to the variable declaration.
        variableIndexes = TreeUtils.getScopeArgs((sampleVarDesc.sampleVariable));
        // Add any additional indexes used to assign the output of the sample task to
        // the
        // variable. For example if an array has been declared in global scope, these
        // will be the indexes used to place values into the array.
        for(DataflowTaskArgDesc d:sampleVarDesc.traceToSampleVariable.getTrace())
            if(d.task.getType() == DFType.PUT)
                variableIndexes.add(((PutTask<?>) d.task).index);
    }

    /**
     * Method to set each of the intermediate values dependent on the output of this sample task.
     * 
     * @param compilationCtx The compilation context.
     */
    protected void setIntermediateValues(CompilationContext compilationCtx) {
        intermediates.setIntermediateValues(sampleVarDesc.sampleVariable, compilationCtx);
    }

    /**
     * Method to get the innermost scope holding the sample task.
     * 
     * @return The scope directly holding the sample task.
     */
    public Scope getScope() {
        return scopes.get(scopes.size() - 1);
    }

    /**
     * A list containing all the scopes that encapsulate this sample task.
     * 
     * @return A list containing all the scopes encapsulating this sample task.
     */
    protected List<Scope> getScopes() {
        return scopes;
    }
}
