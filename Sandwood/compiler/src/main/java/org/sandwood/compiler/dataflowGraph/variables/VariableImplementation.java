/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.CompilationContext.CompilationPhase;
import org.sandwood.compiler.compilation.util.TreeUtils;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask.TraceCallback;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.nonReturnTasks.ObserveVariableTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.CopyNumberTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable.OuterArrayDesc;
import org.sandwood.compiler.exceptions.AliasAlreadySetException;
import org.sandwood.compiler.exceptions.ConstraintAlreadySetException;
import org.sandwood.compiler.exceptions.LocationAlreadySetException;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.DAGInfo;
import org.sandwood.compiler.traces.TraceConstructionDesc;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public abstract class VariableImplementation<A extends Variable<A>> implements Variable<A> {

    private enum VisibilityMod {
        PRIVATE,
        PUBLIC,
        NONE
    }

    /** String to hold the expression used to construct the variable */
    private String inLineValue;
    /** The alias of the variable */
    protected String alias = null;
    /** A comment about the variable if set */
    protected String comment = null;
    /** Unique id for the variable. */
    private final int id;
    /**
     * The location where this variable was declared. This is only set for named variables.
     */
    private Location location = null;
    /**
     * If this variable has been set to the same value as another variable this is the task that made that binding
     */
    // TODO this gets set in the instance handle, sort some wrapper to ensure that
    // when this happens it also happens to all instances.
    private ObserveVariableTask<A> constraint;
    /**
     * The task that constructed the variable. TODO replace the type declaration with a generic type.
     */
    private ProducingDataflowTask<A> parent;
    /** Set of tasks that consume the variable */
    // TODO figure out what to do about arrays here
    private final Set<DataflowTask<?>> consumers;
    /** The scope of the variable */
    private Scope enclosingScope;
    /** Is this variable an intermediate variable? */
    private boolean intermediateVariable = false;
    /** Calculate intermediates */
    private boolean calculateIntermediate = false;
    /** Should we stop at this point when constructing forward traces? */
    private boolean isSample;
    /** Should we stop at this point when constructing forward traces? */
    private boolean stopPoint = false;
    /** Is this a variable we should include getters and setters for in our model */
    private VisibilityMod visibility = VisibilityMod.NONE;

    /** Counter of variable ids */
    private final static AtomicInteger nextID = new AtomicInteger();

    /**
     * A flag to say if this value will be the same in every iteration of the code. The value may vary for example it is
     * an index in a for loop, but it is not dependent on any randomised elements.
     **/
    private boolean deterministic;

    /**
     * Flag set if this value is based on a distribution
     */
    private boolean distribution;

    private VariableDescription<A> uniqueName;

    /**
     * Constructor for Variable. This gives variable a unique id, sets the dataflow task that constructed it and sets
     * the scope of the variable to be the same as the scope of the parents task. This is normally correct, but is
     * occasional updated immediately after. A change to the constructors to make this an atomic step should be made at
     * some point soon. This constructor is not constructing an updated variable so currentInstance and instance handle
     * point to the variable.
     * <p>
     * TODO merge in constructing variables with different scopes
     *
     * @param parent The dataflow task that constructed the variable.
     */
    @SuppressWarnings("unchecked")
    protected VariableImplementation(ProducingDataflowTask<A> parent) {
        id = nextID.incrementAndGet();
        this.parent = parent;
        parent.setOutput((A) this);
        consumers = new HashSet<>();
        enclosingScope = parent.scope();
        deterministic = parent.deterministic();
        distribution = parent.isDistribution();
        if(uniqueName == null)
            constructUniqueName(parent.getOutputType());
    }

    /**
     * Resets static variables. This is called via reflection in testing.
     */
    @SuppressWarnings("unused")
    private static void reset() {
        nextID.set(0);
    }

    @Override
    public boolean isDistribution() {
        return distribution;
    }

    @Override
    public void setIsDistribution() {
        if(!distribution) { // if the flag is not already set
            distribution = true;
            for(DataflowTask<?> t:consumers)
                t.setIsDistribution();
        }
    }

    @Override
    public boolean isDeterministic() {
        return deterministic;
    }

    @Override
    public void setNonDeterministic() {
        deterministic = false;
    }

    /**
     * Method to get the expression that represents the value of this variable. If inline is true and there is an
     * inlineable variable (this will only be true if the value is used only once) it will return the expression
     * otherwise it will return the name of the variable.
     *
     * @param inLine Do we try to inline expressions?
     * @return The expression that represents the variable if we are inlining, otherwise the variable name.
     */
    @Override
    public String getExpression(boolean inLine) {
        if(inLine && inLineValue != null)
            return inLineValue;
        else
            return getVarDesc().name.getName();
    }

    /**
     * Method to get the variable identifier. This will either be the variables handle id, or the variables alias if we
     * are using aliases and the alias has been set.
     *
     * @return The name of the variable.
     */
    @Override
    public VariableDescription<A> getVarDesc() {
        String alias = instanceHandle().getAlias();
        if(alias != null)
            return new VariableDescription<>(alias, getType(), false);
        else
            return new VariableDescription<>("var" + getHandleId(), getType(), true);
    }

    /**
     * Method to get a unique variable identifier. This will be the var id if the alias is not set, and a combination of
     * the two if the alias is set.
     *
     * @return The name of the variable.
     */
    @Override
    public VariableDescription<A> getUniqueVarDesc() {
        Variable<A> i = instanceHandle();
        if(this == i)
            return uniqueName;
        else
            return i.getUniqueVarDesc();
    }

    private void constructUniqueName(Type<A> type) {
        Variable<?> i = instanceHandle();
        String alias = (i != null) ? i.getAlias() : this.alias;

        uniqueName = VariableNames.variableName(alias, getHandleId(), type, false);
    }

    /**
     * Set the string that represents the code to create value of this variable.
     *
     * @param inLineValue Expression to create the value of this variable.
     */
    @Override
    public void setInLineValue(String inLineValue) {
        this.inLineValue = inLineValue;
    }

    /**
     * Method to set the location in the model code where the variable is declared.
     *
     * @param location The location that the variable is declared at. This is used for constructing more specific error
     *                 messages.
     * @throws LocationAlreadySetException Locations can only be set once, trying to set a location a second time will
     *                                     cause this exception to be thrown.
     */
    @Override
    public void setLocation(Location location) {
        if(this == instanceHandle() || instanceHandle() == null) { // instance handle can be null if it is called as
            // part of the constructor.
            if(this.location == null)
                this.location = location;
            else
                throw new LocationAlreadySetException(this);
        } else
            instanceHandle().setLocation(location);
    }

    /**
     * Method to set the value of the alias for a variable. Calls to this should only be added by tooling, not by a
     * user.
     *
     * @param alias The alias for this variable
     * @throws AliasAlreadySetException Aliases can only be set once, trying to reset an alias will cause this exception
     *                                  to be thrown.
     */
    @Override
    public void setAlias(String alias) throws AliasAlreadySetException {
        if(this == instanceHandle() || instanceHandle() == null) { // instance handle can be null if it is called as
            // part of the constructor.
            if(this.alias == null) {
                this.alias = alias;
                constructUniqueName(getType());
            } else
                throw new AliasAlreadySetException(this, alias);
        } else
            instanceHandle().setAlias(alias);
    }

    /**
     * Method to set the value of the alias for a variable. Calls to this should only be added by tooling, not by a
     * user.
     *
     * @param alias The alias for this variable
     * @throws AliasAlreadySetException Aliases can only be set once, trying to reset an alias will cause this exception
     *                                  to be thrown.
     */
    @Override
    public void setAlias(VariableDescription<A> alias) {
        setAlias(alias.name.getName());
    }

    @Override
    public void setUniqueVarDesc(VariableDescription<A> varDesc) {
        if(this == instanceHandle() || instanceHandle() == null) {
            this.uniqueName = varDesc;
        } else
            instanceHandle().setUniqueVarDesc(varDesc);
    }

    /**
     * Get the value of the alias, returns null if no value is set.
     *
     * @return The value of the alias, null if no alias is set.
     */
    @Override
    public String getAlias() {
        if(instanceHandle() == this)
            return alias;
        else
            return instanceHandle().getAlias();
    }

    /**
     * Returns if the alias has been set
     *
     * @return Has the alias been set
     */
    @Override
    public boolean aliasSet() {
        if(instanceHandle() == this)
            return alias != null;
        else
            return instanceHandle().aliasSet();
    }

    /**
     * Method to set the value of the comment for a variable. Calls to this should only be added by tooling, not by a
     * user.
     *
     * @param comment The comment for this variable
     */
    @Override
    public void setComment(String comment) {
        if(this == instanceHandle() || instanceHandle() == null) // instance handle can be null if it is called as
            this.comment = comment; // part of the constructor.
        else
            instanceHandle().setComment(comment);
    }

    /**
     * Get the value of the variable comment, returns null if no value is set.
     *
     * @return The value of the comment, null if no comment is set.
     */
    @Override
    public String getComment() {
        if(instanceHandle() == this)
            return comment;
        else
            return instanceHandle().getComment();
    }

    /**
     * Method to record the tasks that consume this variable.
     *
     * @param task A dataflow task that consumes this variable.
     */
    @Override
    public void addConsumer(DataflowTask<?> task) {
        consumers.add(task);
    }

    /**
     * Getter method for the scope of the variable
     *
     * @return Scope of the variable
     */
    @Override
    public Scope scope() {
        return enclosingScope;
    }

    /**
     * Method to set the enclosing scope of a variable.
     * 
     * @param enclosingScope The scope that encloses the variable.
     */
    @Override
    public void setScope(Scope enclosingScope) {
        this.enclosingScope = enclosingScope;
    }

    /**
     * Method that takes a set of tasks and adds any extra tasks that are required to construct this variable.
     *
     * @param tasks possibly empty set of tasks that will be augmented with any additional tasks required to construct
     *              this variable.
     */
    @Override
    public void getTasks(Set<DataflowTask<?>> tasks) {
        if(!tasks.contains(parent)) {
            tasks.add(parent);
            List<Variable<?>> vars = parent.getInputs();
            for(Variable<?> v:vars)
                v.getTasks(tasks);
        }

        if(constraint != null && !tasks.contains(constraint)) {
            tasks.add(constraint);
            List<Variable<?>> vars = ((DataflowTask<?>) constraint).getInputs();
            for(Variable<?> v:vars)
                v.getTasks(tasks);
        }
    }

    /*
     * A method to calculate the variables in a Markov Blanket. Currently, this does not handle arrays. TODO add in the
     * correct handling of arrays. TODO add in correct handling of random variables.
     *
     * @param var The variable to construct the blanket for.
     * 
     * @return The set of variables that make up the blanket.
     */
    /*
     * public Set<RandomVariable<?,?>> markovBlanket() { Set<RandomVariable<?,?>> markovBlanket = new HashSet<>();
     *
     * Set<RandomVariable<?,?>> children = findRandomChildren(); for(Variable<?> v:children)
     * for(Pair<RandomVariable<?,?>,SampleTask<?,?>>p:v.findRandomParents()) markovBlanket.add(p.a);
     * markovBlanket.addAll(children);
     *
     * for(Pair<RandomVariable<?,?>,SampleTask<?,?>>p:findRandomParents()) markovBlanket.add(p.a);
     *
     * markovBlanket.remove(this); return markovBlanket; }
     */

    /**
     * Method to return the variable ID.
     *
     * @return
     */
    @Override
    public int getId() {
        return id;
    }

    @Override
    public ProducingDataflowTask<A> getParent() {
        return parent;
    }

    @Override
    public ObserveVariableTask<A> getObservation() {
        Variable<A> i = instanceHandle();
        if(this == i)
            return constraint;
        else
            return i.getObservation();
    }

    /**
     * Method to use to change the parent of a variable when restructuring the dag.
     * 
     * @param alternative A new parent to use instead of the original one.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void changeParent(ProducingDataflowTask<A> alternative) {
        this.parent = alternative;
        parent.setOutput((A) this);
    }

    /**
     * Method to remove a task from the set of consumers when restructuring the graph.
     * 
     * @param t The task to remove
     */
    @Override
    public void removeConsumer(ProducingDataflowTask<?> t) {
        consumers.remove(t);
    }

    /**
     * Method to return the type of the variable
     *
     * @return Type of the variable.
     */
    @Override
    public abstract Type<A> getType();

    /**
     * return the instance of the variable that is being used by the api.
     *
     * @return
     */
    @Override
    public Variable<A> instanceHandle() {
        return this;
    }

    /**
     * Get the value of the latest instance of this variable
     *
     * @return The latest instance of this variable
     */
    @Override
    public Variable<A> getCurrentInstance() {
        return this;
    }

    /**
     * Get the id of the first instance of this variable
     *
     * @return ID of the first instance of this variable.
     */
    @Override
    public int getHandleId() {
        Variable<?> i = instanceHandle();
        if(i != null)
            return i.getId();
        else
            return getId();
    }

    /**
     * Method to add a constraint to this variable stating it must be the same as the value held by the observe task.
     *
     * @param observation The task performing the observation that constrains this variable
     * @throws ConstraintAlreadySetException Only one constraint can currently be applied per variable.
     */
    protected void addConstraint(ObserveVariableTask<A> observation) throws ConstraintAlreadySetException {
        if(constraint == null)
            constraint = observation;
        else
            throw new ConstraintAlreadySetException(observation);
    }

    /**
     * Method to set this to a stop point
     */
    @Override
    public void markStopPoint() {
        stopPoint = true;
    }

    /**
     * Method to unset this as a stop point
     */
    @Override
    public void unmarkStopPoint() {
        stopPoint = false;
    }

    @Override
    public boolean isStopPoint() {
        return stopPoint;
    }

    /**
     * Method to mark this variable as an intermediate variable.
     */
    @Override
    public void setIntermediate() {
        Variable<A> i = instanceHandle();
        if(this == i)
            intermediateVariable = true;
        else
            i.setIntermediate();
    }

    /**
     * Test if this variable is an intermediate variable.
     *
     * @return
     */
    @Override
    public boolean isIntermediate() {
        Variable<A> i = instanceHandle();
        if(this == i)
            return intermediateVariable;
        else
            return i.isIntermediate();
    }

    @Override
    public void calculateIntermediate(boolean calculateIntermediate) {
        this.calculateIntermediate = calculateIntermediate;
    }

    @Override
    public boolean isSample() {
        return isSample;
    }

    @Override
    public void setSample() {
        isSample = true;
        visibility = VisibilityMod.PUBLIC;
    }

    /**
     * Method to pass a description of this variable to a string buffer. This is mainly used for debugging and testing.
     *
     * @param sb The string buffer to populate.
     */
    @Override
    public void getDescription(StringBuilder sb) {
        sb.append("[ var" + id);
        sb.append(", type: " + getType().getJavaType());
        sb.append(", handle var" + getHandleId());
        sb.append(", parent: task" + parent.id());
        sb.append(", alias: " + alias);
        sb.append(", comment: " + comment);
        sb.append(", scope: task" + enclosingScope.id());
        if(constraint != null)
            sb.append(", constraint: var" + constraint.id());
        sb.append(" ]");
    }

    @Override
    public IRTreeReturn<A> getForwardIR(CompilationContext compilationCtx) {
        // If the value is substituted call gerForwardIR on the substitute
        Variable<A> altVar = compilationCtx.getSubstitute(this);
        if(altVar != this) {
            compilationCtx.addIgnoreSubstitute(this);
            IRTreeReturn<A> toReturn = altVar.getForwardIR(compilationCtx);
            compilationCtx.removeIgnoreSubstitute(this);
            return toReturn;
        }

        if(isSample()) {
            if(getType().isArray()) {
                ArrayVariable<?> array = (ArrayVariable<?>) this;
                if(array.isSubArray()) {
                    OuterArrayDesc<?> outer = array.getOuterArrayDesc();
                    return (IRTreeReturn<A>) IRTree.arrayGet(outer.getArray().getForwardIR(compilationCtx),
                            outer.getIndex().getForwardIR(compilationCtx));
                } else
                    return TreeUtils.getIndirectValue(this, compilationCtx);
            } else {
                return TreeUtils.getIndirectValue(this, compilationCtx);
            }
        }

        /*
         * Dereference and return a class variable if it is: a) An intermediate array (Not a sub-array which would be a
         * method variable) b) A non deterministic intermediate. c) A deterministic intermediate and the deterministic
         * values have been constructed. TODO make this final point something that is checked against a set in the
         * compilationCtx so that constructed values can be used while other intermediates are being constructed.
         */
        if(!calculateIntermediate && isIntermediate()
                && (getType().isArray() || !isDeterministic()
                        || (isDeterministic() && (compilationCtx.phase == CompilationPhase.MAIN_METHODS
                                || compilationCtx.phase == CompilationPhase.INITIALIZATION_OF_INTERMEDIATES))))
            return TreeUtils.getIndirectValue(this, compilationCtx);

        // If the tree is stopping on a method variable just return it.
        if(isStopPoint() && !isIntermediate())
            return IRTree.load(this);

        // If none of the above just calculate the value of the tree from the parent.
        return parent.getForwardIR(compilationCtx);
    }

    /**
     * Method that returns all the consumers of a given variable.
     *
     * @return Set of consumers of a variable.
     */
    @Override
    public Set<DataflowTask<?>> getConsumers() {
        return consumers;
    }

    /**
     * Method to trigger the construction of traces. It does nothing for most variables, but will start a trace for
     * observed variables, and be overridden to start traces from RandomVariables initially, and later other types as
     * required.
     *
     * @param dagInfo A DAGInfo object to collect interesting points in the DAG.
     */
    @Override
    public void constructTrace(DAGInfo dagInfo) {
        // Observed variables.
        if(isObserved()) {
            // Extra call as if no RV is reached, this variable will never be added with a
            // trace.
            dagInfo.addObservedVariable(this);
            ObserveVariableTask<A> o = instanceHandle().getObservation();
            if(this == o.target) { // Ensure the trace is only constructed once from the variable that observation
                // was applied to.
                constructTrace(dagInfo::addObservedChild);
                o.source.constructTrace(dagInfo::addObservedSource);
            }
        } else if(consumers.isEmpty() && scope() == GlobalScope.scope && !this.isPrivate()) { // Terminal
            // variables.
            constructTrace(dagInfo::addTerminalChild);
        }

        // constructed inputs.
        if(getParent().getType() == DFType.CONSTRUCT_INPUT) {
            // Extra call as if no RV is reached, this variable will never be added with a
            // trace.
            dagInfo.addObservedVariable(this);
        }
    }

    @Override
    public void constructTrace(TraceCallback c) {
        TraceConstructionDesc desc = new TraceConstructionDesc(this, c);
        constructTrace(desc);
    }

    @Override
    public void constructTrace(TraceConstructionDesc desc) {
        if(!desc.seenVar.contains(this)) {
            desc.seenVar.add(this);
            // If this is the start of the trace or this is not an observed value build the trace.
            if(desc.trace.isEmpty() || !isObserved())
                parent.constructTrace(desc);
            else {
                // Otherwise record the trace here.
                desc.c.callback(desc.trace, desc.sink);
            }

            desc.seenVar.remove(this);
        }
    }

    /**
     * Method to set this variable to be private.
     */
    @Override
    public void setPrivate() {
        if(this == instanceHandle())
            visibility = VisibilityMod.PRIVATE;
        else
            instanceHandle().setPrivate();
    }

    /**
     * Method to set this variable to be private.
     */
    @Override
    public void setPublic() {
        if(this == instanceHandle())
            visibility = VisibilityMod.PUBLIC;
        else
            instanceHandle().setPublic();
    }

    /**
     * Method to determine if this is a private variable
     */
    @Override
    public boolean isPrivate() {
        if(this == instanceHandle())
            return getAlias() == null || visibility == VisibilityMod.PRIVATE
                    || (enclosingScope != GlobalScope.scope && visibility != VisibilityMod.PUBLIC && !isSample());
        else
            return instanceHandle().isPrivate();
    }

    /**
     * Method to determine if this variable is set to an observed value.
     */
    @Override
    public boolean isObserved() {
        if(this == instanceHandle())
            return constraint != null;
        else
            return instanceHandle().isObserved();
    }

    @Override
    public Set<Variable<?>> collectInputVariables(DFType... stopTypes) {
        Set<Variable<?>> var = new HashSet<>();
        var.add(this);
        return Variable.collectInputVariable(var, stopTypes);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if((obj == null) || (getClass() != obj.getClass()))
            return false;
        Variable<?> other = (Variable<?>) obj;
        return id == other.getId();
    }

    @Override
    public boolean equivalent(Variable<?> other) {
        // Simple test are these actually the same variable
        if(id == other.getId())
            return true;

        // Strip out any copy tasks.
        while(other.getParent().getType() == DFType.COPY) {
            other = ((CopyNumberTask<?>) other.getParent()).input;
            if(id == other.getId()) // Testing for variable equality along the way.
                return true;
        }

        // If they are not the same variable start comparing the generating dags.
        if(constraint != null) {
            if(other.isObserved())
                return false;
            else
                return constraint.equivalent(other.getObservation());
        } else
            return parent.equivalent(other.getParent());
    }

    @Override
    public int compareTo(Variable<?> other) {
        return id - other.getId();
    }

    @Override
    public String toString() {
        return Variable.getSandwoodCode(false, this);
    }

    /**
     * Get the location that the variable appears in the model file at.
     * 
     * @return The variable location.
     */
    @Override
    public Location getLocation() {
        return location;
    }
}
