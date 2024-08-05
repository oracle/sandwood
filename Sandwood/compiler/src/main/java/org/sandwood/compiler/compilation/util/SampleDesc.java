/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.util;

import java.util.List;
import java.util.PriorityQueue;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
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
import org.sandwood.compiler.traces.guards.ScopeConstructor;
import org.sandwood.compiler.traces.guards.TreeBuilderInfo;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

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
    public final Variable<A> output;

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

    /**
     * Method to set each of the intermediate values dependent on the output of this sample task.
     * 
     * @param compilationCtx The compilation context.
     */
    protected void setIntermediateValues(CompilationContext compilationCtx) {
        // Clear the of arrays used in the traces to these intermediates.
        // clearInitialisations(compilationCtx);

        // Mark a stop point so that the constructed trees do not go beyond this point
        output.markStopPoint();

        // TODO work out if this can be simplified. To do this we will need to work out
        // exactly
        // which variables are in intermediates. I think some of the guards on populate
        // can be removed
        // and possibly this first call to populateValue.
        populateValue(sampleVarDesc.sampleVariable, compilationCtx);

        PriorityQueue<Variable<?>> p = new PriorityQueue<>(intermediates.getVariables());
        while(!p.isEmpty()) {
            Variable<?> v = p.poll();
            if(v != sampleVarDesc.sampleVariable) {
                // Add a guard so that the values are only updated if the configuration of the
                // model
                // would allow it. Without this array out of bounds errors are possible as well
                // as serious inefficiency.
                ScopeConstructor a = ScopeConstructor.construct(sample, GlobalScope.scope, "Guards to ensure that "
                        + v.getUniqueVarDesc() + " is only updated when there is a valid path.", compilationCtx);
                a = a.addConstraints(intermediates.getTraces(v)); // TODO make this use variable passing to simplify the
                                                                  // setting of intermediates. This will probably also
                                                                  // require the output of the sample to be substituted.
                // Isolate the scopes ready to apply the tree in case the constraints do not
                // separate out the code. If the isolation is not required the optimisation
                // phase will remove it.
                a = a.addIsolation();
                a.addTree((TreeBuilderInfo info) -> populateValue(v, compilationCtx));
            }
        }
        output.unmarkStopPoint();
    }

    /**
     * A method to generate code to recalculate the value of a variable.
     * 
     * @param v              The variable to recalculate the value of.
     * @param compilationCtx The compilation context.
     */
    protected <X extends Variable<X>> void populateValue(Variable<X> v, CompilationContext compilationCtx) {
        // Test if the value has already been set.
        if(v != output) {
            // If it has not, construct a tree to set it.
            Variable<X> vSub = compilationCtx.getSubstitute(v);
            ProducingDataflowTask<X> task = vSub.getParent();
            IRTreeReturn<X> t = task.getForwardIR(compilationCtx);
            if(!(t.returning(v.getUniqueVarDesc()) || compilationCtx.initialized(v))) {
                compilationCtx.addTreeToScope(v.scope(),
                        TreeUtils.putIndirectValue(v, t, "Write out the new sample value.", compilationCtx));
                compilationCtx.addInitialized(v);
            }
        }
    }
}
