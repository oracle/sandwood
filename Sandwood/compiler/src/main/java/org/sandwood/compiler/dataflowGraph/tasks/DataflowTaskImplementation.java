/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.Id;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.exceptions.SandwoodModelException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public abstract class DataflowTaskImplementation<A extends Variable<A>> extends Id implements DataflowTask<A> {

    /** The inputs to the task */
    protected final List<Variable<?>> inputs = new ArrayList<>();
    /** The outputs from the task */
    protected A output = null;
    /** The scope the task operates in */
    private final Scope scope;
    /**
     * The location in the model source that resulted in the creation of this task. Null if it is created as a result of
     * a later step, or the location is not set.
     **/
    private final Location location;

    /**
     * Flag to mark if this task can be inlined. examples of tasks that cannot include puts on arrays
     */
    protected boolean inlineableTask = true;

    private boolean isDistribution = false;
    
    private final DFType dfType;

    /**
     * Constructor, takes the inputs as its arguments, and gets the most up-to-date instance of each of these.
     *
     * @param vars The variables that this dataflow task will take as inputs.
     */
    public DataflowTaskImplementation(DFType dfType, Location location, Variable<?>... vars) {
        this.dfType = dfType;
        scope = ScopeStack.getCurrentScope();
        this.location = location;
        for(Variable<?> v:vars) {
            inputs.add(v.getCurrentInstance());
            v.addConsumer(this);
            isDistribution = isDistribution || v.isDistribution();
        }
    }

    /**
     * Return the inputs
     *
     * @return The inputs to this task.
     */
    @Override
    public final List<Variable<?>> getInputs() {
        return inputs;
    }

    /**
     * Return a specific input.
     *
     * @param i The input to return.
     * @return The corresponding input.
     */
    @Override
    public Variable<?> getInput(int i) {
        return inputs.get(i);
    }

    /**
     * The number of inputs.
     *
     * @return The number of inputs.
     */
    @Override
    public int getInputsCount() {
        return inputs.size();
    }

    /**
     * Return the output
     * 
     * @return The output
     */
    @Override
    public A getOutput() {
        assert (outputSet());
        return output;
    }

    /**
     * Method used by Variable to set itself as an output of the task.
     *
     * @param output The variable to become and output of this task.
     */
    @Override
    public void setOutput(A output) {
        assert (!outputSet());
        this.output = output;
    }

    /**
     * Method to check if the output value has been set.
     *
     * @return boolean stating if the output is set.
     */
    @Override
    public boolean outputSet() {
        return output != null;
    }

    /**
     * Function to return the name of the task. This is used for debugging and graphical output etc.
     *
     * @return The name of the task.
     */
    @Override
    public String getName() {
        return "Task " + getType().getDescription() + " " + id();
    }

    /**
     * Method specifying the type of the task.
     *
     * @return The type of the task
     */
    @Override
    public final DFType getType() {
        return dfType;
    }

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
    @Override
    public int getSandwoodCode(StringBuilder sb, int indent, Map<Variable<?>, Boolean> inlineableVariables,
            boolean compressSandwoodCode) {
        Variable<A> var = getOutput();
        Boolean inlineableVariable = inlineableVariables.get(var);
        if(!inlineableTask || !compressSandwoodCode || (inlineableVariable != null && inlineableVariable)) {
            addIndent(sb, indent);
            sb.append(getSandwoodString(compressSandwoodCode) + "\n");
        } else {
            var.setInLineValue(getSandwoodExpression(compressSandwoodCode));
        }
        return indent;
    }

    /**
     * Method for the task to implement, provide the sandwood code for the task. This just provides the expression,
     * assignment etc. is added by getSandwoodString which calls this method along with type etc. on the output.
     *
     * @param compressSandwoodCode Are values inlined where possible?
     * @return the code to return
     */
    @Override
    public abstract String getSandwoodExpression(boolean compressSandwoodCode);

    /**
     * The full string for most tasks. This is overridden by control tasks etc. where required.
     *
     * @param compressSandwoodCode Are values inlined where possible.
     * @return the code to return
     */
    @Override
    public String getSandwoodString(boolean compressSandwoodCode) {
        Variable<A> var = getOutput();
        return var.getType().getJavaType() + " " + var.getVarDesc() + " = "
                + getSandwoodExpression(compressSandwoodCode) + ";";
    }

    @Override
    public Scope scope() {
        return scope;
    }

    /**
     * Does this task cause iteration. This is almost always false, with just control tasks such as parFor having a
     * different value.
     *
     * @return Does the task cause iterations in its sub graph.
     */
    @Override
    public boolean iterating() {
        return false;
    }

    /**
     * Method to add an indent to a string buffer
     *
     * @param sb    StringBuffer to add the indent to.
     * @param depth Depth of indent to add.
     */
    protected void addIndent(StringBuilder sb, int depth) {
        for(int i = 0; i < depth; i++)
            sb.append("\t");
    }

    /**
     * Method to generate a text description for testing and debugging.
     *
     * @param sb A StringBuffer to store the output.
     */
    @Override
    public void getDescription(StringBuilder sb) {
        sb.append("[ task" + id());
        sb.append(", type: " + getType().getDescription());
        sb.append(", scope: task" + scope.id());
        sb.append(", input [");
        boolean first = true;
        for(Variable<?> v:inputs) {
            if(first)
                first = false;
            else
                sb.append(", ");
            sb.append("var" + v.getId());
        }
        sb.append("], output [");
        if(outputSet())
            sb.append("var" + output.getId());
        sb.append("], Sandwood Code: " + getSandwoodString(false) + " ]");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if((obj == null) || (getClass() != obj.getClass()))
            return false;
        DataflowTaskImplementation<?> other = (DataflowTaskImplementation<?>) obj;

        return id() == other.id();
    }

    @Override
    public int compareTo(DataflowTask<?> other) {
        return id() - other.id();
    }

    protected abstract IRTreeReturn<A> getForwardIRinternal(CompilationContext compilationCtx);

    @Override
    public IRTreeReturn<A> getForwardIR(CompilationContext compilationCtx) {
        compilationCtx.enterScope(scope);
        IRTreeReturn<A> t = getForwardIRinternal(compilationCtx);
        compilationCtx.leaveScope(scope);
        return t;
    }

    @Override
    public abstract boolean equivalent(DataflowTask<?> other);

    @Override
    public String toString() {
        return getType().name() + ":" + id();
    }

    @Override
    public void testTask(List<SandwoodModelException> errors) {}

    /**
     * Get the location of the task in the Sandwood model
     * 
     * @return Location of the task in the model file.
     */
    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public boolean isDistribution() {
        return isDistribution;
    }

    @Override
    public void setIsDistribution() {
        if(!isDistribution) { // If the flag is not already set
            isDistribution = true;
            if(outputSet())
                getOutput().setIsDistribution();
        }
    }
}
