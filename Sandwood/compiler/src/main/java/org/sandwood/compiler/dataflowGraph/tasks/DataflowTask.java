/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks;

import java.util.List;
import java.util.Map;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.exceptions.SandwoodModelException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.Trace;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public interface DataflowTask<A extends Variable<A>> extends Comparable<DataflowTask<?>> {

    /**
     * Return the inputs
     *
     * @return The inputs to this task.
     */
    List<Variable<?>> getInputs();

    /**
     * Return a specific input.
     *
     * @param i The input to return.
     * @return The corresponding input.
     */
    Variable<?> getInput(int i);

    /**
     * The number of inputs.
     *
     * @return The number of inputs.
     */
    int getInputsCount();

    /**
     * Return the output of the dataflow task.
     *
     * @return The output
     */
    A getOutput();

    /**
     * Method used by Variable to set itself as an output of the task.
     *
     * @param output The variable to become and output of this task.
     */
    void setOutput(A output);

    /**
     * Method to check if the output has been set.
     *
     * @return boolean representing if the value has been set.
     */
    boolean outputSet();

    /**
     * Function to return the name of the task. This is used for debugging and graphical output etc.
     *
     * @return The name of the task.
     */
    String getName();

    /**
     * Method to be implemented by subclasses specifying the type of the task TODO replace this with something more
     * typed like we have with Random Variables.
     *
     * @return The type of the task
     */
    DFType getType();

    /*
     * TODO Use subclassing to have versions of this method that do not return a value i.e. put and observe, and
     * versions that do return a value i.e. get.
     */
    /**
     * Method to generate Sandwood code for most tasks.
     *
     * @param sb                   The StringBuilder to put the results into.
     * @param indent               How much to indent the code by.
     * @param inlineableVariables  Mapping of variables that can be inlined.
     * @param compressSandwoodCode Should variable expressions be inlined where possible.
     * @return How big the indent is after this tasks code.
     */
    int getSandwoodCode(StringBuilder sb, int indent, Map<Variable<?>, Boolean> inlineableVariables,
            boolean compressSandwoodCode);

    /**
     * Method for the task to implement to provide the sandwood code for the task. This just provides the expression,
     * assignment etc. is added by getSandwoodString which calls this method along with type etc. on the output.
     *
     * @param compressSandwoodCode Are values inlined where possible?
     * @return the code to return
     */
    String getSandwoodExpression(boolean compressSandwoodCode);

    /**
     * The full string for most tasks. This is overridden by control tasks etc. where required.
     *
     * @param compressSandwoodCode Are values inlined where possible.
     * @return the code to return
     */
    String getSandwoodString(boolean compressSandwoodCode);

    /**
     * Get the ID of the task
     *
     * @return The task ID.
     */
    int id();

    /**
     * Does this task cause iteration. This is almost always false, with just control tasks such as parFor having a
     * different value.
     *
     * @return Does the task cause iterations in its sub graph.
     */
    boolean iterating();

    /**
     * Method to generate a text description for testing and debugging.
     *
     * @param sb A StringBuffer to store the output.
     */
    void getDescription(StringBuilder sb);

    IRTreeReturn<A> getForwardIR(CompilationContext compilationCtx);

    boolean equivalent(DataflowTask<?> other);

    Scope scope();

    interface TraceCallback {
        void callback(Trace trace, Variable<?> sink);
    }

    void testTask(List<SandwoodModelException> errors);

    /**
     * Get the location of the task in the Sandwood model
     * 
     * @return Location of the task in the model file.
     */
    Location getLocation();

    /**
     * Method to determine if this task is producing a distribution, or a single value. TODO remove dataflow task. We
     * want rid of the observation task so that all dataflow tasks are producing tasks.
     * 
     * @return
     */
    boolean isDistribution();

    void setIsDistribution();
}