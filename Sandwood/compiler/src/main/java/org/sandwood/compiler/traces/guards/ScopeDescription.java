/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.traces.guards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.ReductionScope;
import org.sandwood.compiler.dataflowGraph.scopes.ReductionScopeCopied;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope.ScopeType;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.DistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ForTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.traces.TraceHandle;
import org.sandwood.compiler.traces.Traces;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

/**
 * A class to describe a distribution scope.
 */
class ScopeDescription {
    // ID to assist in debugging.
    private final static AtomicInteger globalID = new AtomicInteger();
    private final int id = globalID.incrementAndGet();

    public static class Substitutions {
        /**
         * A map of all the variable substitutions that should be applied when using this distribution. Maps are used
         * rather than just sets to ensure that each variable is only substituted once in each map.
         */
        public final Map<Variable<?>, VariablePair<?>> varSubstitutions;

        public final Map<ReductionScope<?>, ReductionScopeCopied<?>> reductionScopesSubstitutions;

        public Substitutions(Map<Variable<?>, VariablePair<?>> substitutions,
                Map<ReductionScope<?>, ReductionScopeCopied<?>> reductionScopesSubstitutions) {
            this.varSubstitutions = Collections.unmodifiableMap(substitutions);
            this.reductionScopesSubstitutions = Collections.unmodifiableMap(reductionScopesSubstitutions);
        }

        public Substitutions(Substitutions... ss) {
            Map<Variable<?>, VariablePair<?>> vs = new HashMap<>();
            Map<ReductionScope<?>, ReductionScopeCopied<?>> rs = new HashMap<>();
            for(Substitutions s:ss) {
                vs.putAll(s.varSubstitutions);
                rs.putAll(s.reductionScopesSubstitutions);
            }
            varSubstitutions = Collections.unmodifiableMap(vs);
            reductionScopesSubstitutions = Collections.unmodifiableMap(rs);
        }

        public Substitutions() {
            varSubstitutions = Collections.emptyMap();
            reductionScopesSubstitutions = Collections.emptyMap();
        }

        public Substitutions(VariablePair<?> v) {
            Map<Variable<?>, VariablePair<?>> vs = new HashMap<>();
            vs.put(v.source, v);
            varSubstitutions = Collections.unmodifiableMap(vs);
            reductionScopesSubstitutions = Collections.emptyMap();
        }

        public Substitutions(Substitutions s, VariablePair<?> v) {
            Map<Variable<?>, VariablePair<?>> vs = new HashMap<>(s.varSubstitutions);
            vs.put(v.source, v);
            varSubstitutions = Collections.unmodifiableMap(vs);
            reductionScopesSubstitutions = Collections.emptyMap();
        }

        @Override
        public String toString() {
            return "Substitutions: " + varSubstitutions.toString();
        }

        public <A extends Variable<A>> A get(Variable<A> v) {
            VariablePair<?> vp = varSubstitutions.get(v);
            if(vp == null)
                return null;
            else
                return (A) vp.target;
        }
    }

    private static class ConstraintData {
        private final Map<DataflowTask<?>, Substitutions> substitutions;

        public final DataflowTask<?> task;

        /**
         * The trace used to reach this location. This is null if no trace constraints where required to reach this
         * location. This should only be true if this is the first ConstraintData object.
         */
        public final TraceHandle trace;

        /**
         * The set of distributed arguments at this point that did not need to be applied for the trace to reach this
         * point, but do need to be applied before the consumer at the end of the trace can be evaluated.
         */
        public final Set<Set<TraceHandle>> postTraceSets;

        ConstraintData(TraceHandle trace, Set<Set<TraceHandle>> postTraceSets) {
            this.task = trace.peek().task;
            this.trace = trace;
            this.postTraceSets = postTraceSets;
            Map<DataflowTask<?>, Substitutions> subs = new HashMap<>();
            subs.put(task, new Substitutions());
            substitutions = Collections.unmodifiableMap(subs);
        }

        ConstraintData(DataflowTask<?> task, Set<Set<TraceHandle>> postTraceSets) {
            this.task = task;
            this.trace = null;
            this.postTraceSets = postTraceSets;
            Map<DataflowTask<?>, Substitutions> subs = new HashMap<>();
            subs.put(task, new Substitutions());
            substitutions = Collections.unmodifiableMap(subs);
        }

        ConstraintData(ConstraintData c, DataflowTask<?> task, Substitutions substitutions) {
            this.task = c.task;
            trace = c.trace;
            postTraceSets = c.postTraceSets;
            Map<DataflowTask<?>, Substitutions> subs = new HashMap<>(c.substitutions);
            subs.put(task, substitutions);
            this.substitutions = Collections.unmodifiableMap(subs);
        }

        public ConstraintData(ConstraintData c, Set<Set<TraceHandle>> postTraceSets) {
            task = c.task;
            trace = c.trace;
            this.postTraceSets = postTraceSets;
            substitutions = c.substitutions;
        }
    }

    /**
     * A map from distribution sample tasks to a list of maps of for scopes. Each map represents an instance of the
     * sample task in the distribution description. The keys in the map are the for scopes from the DAG, and the values
     * are the for scopes created within the distribution description. The values are what we need to compare against,
     * and the keys are the scopes we can get by querying the sample task.
     */
    private final Map<DistributionSampleTask<?, ?>, List<SampleDesc<?>>> sampleDescriptions;
    /** The inner scope that additional scopes or trees should be added too. */
    final Scope innerScope;
    /** A tree describing the probability of this distribution. */
    final IRTreeReturn<DoubleVariable> probability;
    /**
     * A list of classes describing the details required for additional operations on the distribution. One is created
     * when the ScopeConstructor is constructed, and one for every time a trace is applied the classes contain: A set of
     * traces from distributed inputs that need to be satisfied before using this distribution; A list of maps of all
     * the variable substitutions that should be applied when using this distribution; And a list of maps of all the
     * substitution of indexes that should be applied when using this distribution.
     */
    private final List<ConstraintData> constraintData;

    /**
     * A map of flags from SampleTasks to if the sample task is fixed. This is an optimisation to help keep control of
     * the size the constructed tree.
     */
    private final Map<SampleTask<?, ?>, Boolean> knownFlags;

    /**
     * A set containing all the scopes that have already been constructed at least once in this distribution description
     * so that if they are to be constructed again the names will need modifying to avoid collisions.
     */
    public final Set<ForTask> existingScopes;

    /**
     * The comment if any that goes with this set of distributions.
     */
    public final String comment(int position) {
        ConstraintData c = constraintData.get(position);
        if(Traces.noDistributionTraces(c.postTraceSets))
            return Tree.NoComment;
        else {
            Variable<?> consumer = c.task.getType() == DFType.SAMPLE ? ((SampleTask<?, ?>) c.task).randomVariable
                    : c.task.getOutput();
            if(position == 0) {
                return "Enumerating the possible arguments for " + consumer.getType() + " " + consumer.getId() + ".";
            } else {
                DataflowTask<?> source = constraintData.get(position - 1).task;
                return "Enumerating the possible arguments for the variable " + consumer.getType() + " "
                        + consumer.getId() + " which is consuming the output of " + source.getType() + " task "
                        + source.id() + ".";
            }
        }
    }

    /**
     * Basic constructor to start with a probability of 1.0, and the fixed scopes and starting scope provided.
     * 
     * @param innerScope         The scope that new scopes and trees should be placed into.
     * @param existingScopes     The scopes that are fixed and should not be recreated.
     * @param distributionTraces A set of sets of distributionTraces. Exactly one of these sets of traces will have all
     *                           its trace value valid at any one time.
     * @param compilationCtx     The compilation context for this compilation process.
     */
    public ScopeDescription(Scope innerScope, DataflowTask<?> task, Set<ForTask> existingScopes,
            Set<Set<TraceHandle>> distributionTraces, CompilationContext compilationCtx) {
        this.innerScope = innerScope;
        probability = IRTree.constant(1.0);
        this.existingScopes = Collections.unmodifiableSet(existingScopes);
        knownFlags = new HashMap<>();
        List<ConstraintData> cd = new ArrayList<>();
        sampleDescriptions = new HashMap<>();
        cd.add(new ConstraintData(task, distributionTraces));
        constraintData = Collections.unmodifiableList(cd);
        compilationCtx.touchScope(innerScope);
    }

    public ScopeDescription insertScope(Scope innerScope, Set<ForTask> existingScopes,
            CompilationContext compilationCtx) {
        return new ScopeDescription(innerScope, probability, Collections.unmodifiableSet(existingScopes),
                constraintData, sampleDescriptions, compilationCtx, this);
    }

    public ScopeDescription insertScope(Scope innerScope, CompilationContext compilationCtx) {
        return new ScopeDescription(innerScope, probability, existingScopes, constraintData, sampleDescriptions,
                compilationCtx, this);
    }

    public ScopeDescription setProbability(IRTreeReturn<DoubleVariable> probability) {
        // The exceptions cannot be thrown.
        try {
            return new ScopeDescription(innerScope, probability, existingScopes, constraintData, sampleDescriptions,
                    null, this);
        } catch(CompilerException e) {
            e.printStackTrace();
        }
        throw new CompilerException("This should be unreachable.");
    }

    public <A extends Variable<A>> ScopeDescription addSampleScope(Scope scope, DistributionSampleTask<A, ?> sampleTask,
            Variable<A> sampleValue, IRTreeReturn<DoubleVariable> probability, int position,
            CompilationContext compilationCtx) {
        List<ConstraintData> newConstraintData = addSubstitution(constraintData, position,
                new VariablePair<>(sampleTask.getOutput(), sampleValue));

        Substitutions substitutions = getSubstitutions(position);
        Map<ForTask, IntVariable> sampleIndexes = new HashMap<>();
        Scope s = sampleTask.scope();
        while(s != null) {
            if(s.getScopeType() == ScopeType.FOR) {
                ForTask t = (ForTask) s;
                IntVariable index = t.getIndex();
                IntVariable altIndex = substitutions.get(index);
                if(altIndex == null)
                    sampleIndexes.put(t, index);
                else
                    sampleIndexes.put(t, altIndex);
            }
            s = s.getEnclosingScope();
        }

        SampleDesc<A> sampleDesc = new SampleDesc<>(sampleValue, sampleIndexes);

        Map<DistributionSampleTask<?, ?>, List<SampleDesc<?>>> sampleDescriptions = new HashMap<>(
                this.sampleDescriptions);
        List<SampleDesc<?>> l = sampleDescriptions.get(sampleTask);
        if(l == null)
            l = new ArrayList<>();
        else
            l = new ArrayList<>(l);
        l.add(sampleDesc);
        sampleDescriptions.put(sampleTask, Collections.unmodifiableList(l));

        return new ScopeDescription(scope, probability, existingScopes, newConstraintData,
                Collections.unmodifiableMap(sampleDescriptions), compilationCtx, this);
    }

    /**
     * Construct a new distribution description based on an existing distribution, but with a new scope and probability.
     * 
     * @param innerScope  The scope for this distribution.
     * @param probability The probability for this distribution
     * @param d           The distribution to base this distribution on.
     */
    private ScopeDescription(Scope innerScope, IRTreeReturn<DoubleVariable> probability, Set<ForTask> existingScopes,
            List<ConstraintData> constraintData,
            Map<DistributionSampleTask<?, ?>, List<SampleDesc<?>>> sampleDescriptions,
            CompilationContext compilationCtx, ScopeDescription d) {
        this.innerScope = innerScope;
        this.probability = probability;
        this.existingScopes = existingScopes;
        this.sampleDescriptions = sampleDescriptions;

        this.constraintData = constraintData;

        knownFlags = d.knownFlags;
        if(d.innerScope != innerScope)
            compilationCtx.touchScope(innerScope);
    }

    public ScopeDescription addFixedFlagScope(Scope innerScope, SampleTask<?, ?> task, boolean flagValue,
            CompilationContext compilationCtx) {
        return new ScopeDescription(innerScope, task, flagValue, compilationCtx, this);
    }

    public ScopeDescription addFixedFlag(SampleTask<?, ?> task, boolean flagValue) {
        // The exceptions cannot be thrown
        try {
            return new ScopeDescription(innerScope, task, flagValue, null, this);
        } catch(CompilerException e) {
            e.printStackTrace();
        }
        throw new CompilerException("This should be unreachable.");
    }

    /**
     * Constructor to produce a distributed description based on an existing distribution description, but with a
     * different scope, and with an additional flag the fixed status of a sample task.
     * 
     * @param innerScope The scope for this distribution description.
     * @param task       The task to flag as being fixed or variable.
     * @param flagValue  The value of the flag.
     * @param d          The distribution to base the newly constructed distribution on.
     */
    private ScopeDescription(Scope innerScope, SampleTask<?, ?> task, boolean flagValue,
            CompilationContext compilationCtx, ScopeDescription d) {
        this.innerScope = innerScope;
        probability = d.probability;
        existingScopes = d.existingScopes;
        sampleDescriptions = d.sampleDescriptions;

        constraintData = d.constraintData;

        knownFlags = new HashMap<>(d.knownFlags);
        knownFlags.put(task, flagValue);
        if(innerScope != d.innerScope)
            compilationCtx.touchScope(innerScope);
    }

    public <A extends Variable<A>> ScopeDescription addSubstitution(int position, Variable<A> sampleOutput,
            SampleDesc<A> sampleDesc) {
        return new ScopeDescription(position, sampleOutput, sampleDesc, this);
    }

    private <A extends Variable<A>> ScopeDescription(int position, Variable<A> sampleOutput, SampleDesc<A> sampleDesc,
            ScopeDescription d) {
        innerScope = d.innerScope;
        probability = d.probability;
        existingScopes = d.existingScopes;
        sampleDescriptions = d.sampleDescriptions;

        constraintData = addSubstitution(d.constraintData, position,
                new VariablePair<>(sampleOutput, sampleDesc.sampleValue));

        knownFlags = d.knownFlags;
    }

    public ScopeDescription addDistributionArguments(Set<Set<TraceHandle>> postTraceSets) {
        return new ScopeDescription(postTraceSets, this);
    }

    private ScopeDescription(Set<Set<TraceHandle>> postTraceSets, ScopeDescription d) {
        innerScope = d.innerScope;
        probability = d.probability;
        existingScopes = d.existingScopes;
        sampleDescriptions = d.sampleDescriptions;
        knownFlags = d.knownFlags;
        constraintData = new ArrayList<>();

        int noConstraints = d.constraintData.size() - 1;
        for(int i = 0; i < noConstraints; i++) {
            ConstraintData c = d.constraintData.get(i);
            constraintData.add(c);
        }
        ConstraintData c = d.constraintData.get(noConstraints);
        if(c.postTraceSets == null) {
            constraintData.add(new ConstraintData(c, postTraceSets));
        } else
            throw new CompilerException(
                    "Trying to add distribution traces to a class that already has its distribution traces set.");
    }

    public ScopeDescription constructConstraintSpace(TraceHandle trace) {
        return new ScopeDescription(trace, this);
    }

    private ScopeDescription(TraceHandle trace, ScopeDescription d) {
        innerScope = d.innerScope;
        probability = d.probability;
        existingScopes = d.existingScopes;
        sampleDescriptions = d.sampleDescriptions;
        knownFlags = d.knownFlags;

        List<ConstraintData> cd = new ArrayList<>(d.constraintData);
        cd.add(new ConstraintData(trace, null));
        constraintData = Collections.unmodifiableList(cd);
    }

    private ScopeDescription(int position, DataflowTask<?> task, Substitutions substitutions, ScopeDescription d) {
        innerScope = d.innerScope;
        probability = d.probability;
        existingScopes = d.existingScopes;
        sampleDescriptions = d.sampleDescriptions;
        knownFlags = d.knownFlags;

        int length = d.constraintData.size();
        if(position >= length)
            throw new CompilerException("This should not be possible.");
        constraintData = new ArrayList<>(length);
        for(int i = 0; i < position; i++) {
            ConstraintData c = d.constraintData.get(i);
            constraintData.add(c);
        }

        ConstraintData c = d.constraintData.get(position);
        Substitutions s = c.substitutions.get(task);
        if(s == null)
            s = substitutions;
        else
            s = new Substitutions(s, substitutions);
        constraintData.add(new ConstraintData(c, task, substitutions));

        for(int i = position + 1; i < length; i++) {
            c = d.constraintData.get(i);
            constraintData.add(c);
        }
    }

    public ScopeDescription markDistArgsApplied(int position) {
        return new ScopeDescription(position, this);
    }

    private ScopeDescription(int position, ScopeDescription d) {
        innerScope = d.innerScope;
        probability = d.probability;
        existingScopes = d.existingScopes;
        sampleDescriptions = d.sampleDescriptions;
        knownFlags = d.knownFlags;
        constraintData = new ArrayList<>();
        for(int i = 0; i < position; i++)
            constraintData.add(d.constraintData.get(i));
        ConstraintData c = d.constraintData.get(position);
        constraintData.add(new ConstraintData(c, Traces.noDistributionTraces));
        int size = d.constraintData.size();
        for(int i = position + 1; i < size; i++)
            constraintData.add(d.constraintData.get(i));
    }

    /**
     * Method to get the flag for a task, returns null if the flag has not been set.
     * 
     * @param task Sample task that we want to know if it is fixed.
     * @return Is the sample task fixed, null if the flag is not set.
     */
    public Boolean getFlag(DistributionSampleTask<?, ?> task) {
        return knownFlags.get(task);
    }

    /**
     * Method to add a new set of scopes to the distribution description for a new instance of a sample task.
     * 
     * @param sampleTask The sample task the scopes relate to.
     * @param sampleDesc The map of scopes from the DAG to scopes that were created for this instance of the sample
     *                   task.
     */
    public ScopeDescription addSampleDesc(DistributionSampleTask<?, ?> sampleTask, SampleDesc<?> sampleDesc,
            CompilationContext compilationCtx) {
        Map<DistributionSampleTask<?, ?>, List<SampleDesc<?>>> sampleDescriptions = new HashMap<>(
                this.sampleDescriptions);
        List<SampleDesc<?>> newDescs;
        List<SampleDesc<?>> existingDescs = sampleDescriptions.get(sampleTask);
        if(existingDescs != null)
            newDescs = new ArrayList<>(existingDescs);
        else
            newDescs = new ArrayList<>();
        newDescs.add(sampleDesc);
        sampleDescriptions.put(sampleTask, Collections.unmodifiableList(newDescs));
        return new ScopeDescription(innerScope, probability, existingScopes, constraintData,
                Collections.unmodifiableMap(sampleDescriptions), compilationCtx, this);
    }

    /**
     * Add a new set of substitutions to apply when using the scope described in this distribution description. This is
     * used when alternative values have been provided as part of following through a trace.
     * 
     * @param position      The position in the list of constraint descriptions that this substitution should be added.
     * @param substitutions A map of the variable substitutions to add. If a key value is already in the substitutions
     *                      that value is overwritten.
     */
    public ScopeDescription addSubstitutions(int position, Substitutions substitutions) {
        int size = constraintData.size();
        if(position >= size)
            throw new CompilerException("This should not be possible.");
        ConstraintData c = constraintData.get(position);
        return addSubstitutions(position, c.task, substitutions);
    }

    /**
     * Add a new set of substitutions to apply when using the scope described in this distribution description. This is
     * used when alternative values have been provided as part of following through a trace.
     * 
     * @param position      The position in the list of constraint descriptions that this substitution should be added.
     * @param substitutions A map of the variable substitutions to add. If a key value is already in the substitutions
     *                      that value is overwritten.
     */
    public ScopeDescription addSubstitutions(int position, DataflowTask<?> task, Substitutions substitutions) {
        return new ScopeDescription(position, task, substitutions, this);
    }

    /**
     * Add a new variable substitution.
     * 
     * @param position The position in the list of constraint descriptions that this substitution should be added.
     * @param v        A variable pair containing the value to substitute and the value to replace it with.
     */
    private static List<ConstraintData> addSubstitution(List<ConstraintData> constraintData, int position,
            VariablePair<?> v) {
        int size = constraintData.size();
        if(position >= size)
            throw new CompilerException("This should not be possible.");

        List<ConstraintData> toReturn = new ArrayList<>(size);
        for(int i = 0; i < position; i++)
            toReturn.add(constraintData.get(i));

        ConstraintData c = constraintData.get(position);
        Substitutions s = c.substitutions.get(c.task);
        if(s == null)
            toReturn.add(new ConstraintData(c, c.task, new Substitutions(v)));
        else
            toReturn.add(new ConstraintData(c, c.task, new Substitutions(s, v)));

        for(int i = position + 1; i < size; i++)
            toReturn.add(constraintData.get(i));

        return toReturn;
    }

    /**
     * Method to apply the all substitutions of indexes and variables described in this description.
     * 
     * @param compilationCtx The compilation context for this compilation.
     */
    public void applySubstitutions(int index, DataflowTask<?> task, CompilationContext compilationCtx) {
        ConstraintData c = constraintData.get(index);
        Substitutions s = c.substitutions.get(task);
        for(VariablePair<?> v:s.varSubstitutions.values())
            addVarPairSubstitute(v, compilationCtx);
        for(ReductionScope<?> rs:s.reductionScopesSubstitutions.keySet()) {
            ReductionScopeCopied<?> rsSub = s.reductionScopesSubstitutions.get(rs);
            compilationCtx.addScopeSubstitute(rs, rsSub);
            Scope scope = rsSub.getEnclosingScope();
            // If this is not enclosed in a reduction scope as reductions scopes are already being substituted here.
            if(scope.getScopeType() != ScopeType.REDUCE)
                compilationCtx.addScopeSubstitute(scope, GlobalScope.scope);
        }
    }

    /**
     * Method to apply the all substitutions of indexes and variables described in this description.
     * 
     * @param compilationCtx The compilation context for this compilation.
     */
    public void applySubstitutions(int index, CompilationContext compilationCtx) {
        applySubstitutions(index, constraintData.get(index).task, compilationCtx);
    }

    /**
     * Method to apply the substitutions of variables described in this description.
     * 
     * @param compilationCtx The compilation context for this compilation.
     */
    public void applySubstitutions(CompilationContext compilationCtx) {
        applySubstitutions(constraintData.size() - 1, compilationCtx);
    }

    private <A extends Variable<A>> void addVarPairSubstitute(VariablePair<A> v, CompilationContext compilationCtx) {
        compilationCtx.addSubstitute(v.source, v.target);
    }

    /**
     * Remove substitutions to indexes and variables applied by this distribution.
     * 
     * @param compilationCtx The compilationCtx for this compilation.
     */
    public void removeSubstitutions(int index, DataflowTask<?> task, CompilationContext compilationCtx) {
        ConstraintData c = constraintData.get(index);
        Substitutions s = c.substitutions.get(task);
        for(Variable<?> v:s.varSubstitutions.keySet())
            compilationCtx.removeSubstitute(v);
        for(ReductionScope<?> rs:s.reductionScopesSubstitutions.keySet()) {
            compilationCtx.removeScopeSubstitute(rs);
            ReductionScopeCopied<?> rsSub = s.reductionScopesSubstitutions.get(rs);
            Scope scope = rsSub.getEnclosingScope();
            // If this is not enclosed in a reduction scope as reductions scopes are already being removed here.
            if(scope.getScopeType() != ScopeType.REDUCE)
                compilationCtx.removeScopeSubstitute(scope);
        }
    }

    /**
     * Remove substitutions to variables applied by this distribution.
     * 
     * @param compilationCtx The compilationCtx for this compilation.
     */
    public void removeSubstitutions(int position, CompilationContext compilationCtx) {
        removeSubstitutions(position, constraintData.get(position).task, compilationCtx);
    }

    /**
     * Remove substitutions to variables applied by this distribution.
     * 
     * @param compilationCtx The compilationCtx for this compilation.
     */
    public void removeSubstitutions(CompilationContext compilationCtx) {
        removeSubstitutions(constraintData.size() - 1, compilationCtx);
    }

    /**
     * Method to get a list of substitution maps for a given sample task. Each element in the list represents an
     * instance of the sample task represented by this distribution description.
     *
     * @param sampleTask The sample task that the substitution instances are wanted for.
     * @return A list of maps with each map representing the substitutions for one execution of the sampleTask. Multiple
     *         executions happen when the task appears inside a loop.
     */
    public List<SampleDesc<?>> getExistingSamples(DistributionSampleTask<?, ?> sampleTask) {
        List<SampleDesc<?>> scopes = sampleDescriptions.get(sampleTask);
        if(scopes == null)
            return Collections.emptyList();
        else
            return scopes;
    }

    /**
     * A to string method to make debugging easier.
     */
    @Override
    public String toString() {
        return "Distribution Description " + id + "\n" + innerScope.toString();
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if((obj == null) || (getClass() != obj.getClass()))
            return false;
        ScopeDescription other = (ScopeDescription) obj;
        return id == other.id;
    }

    public Set<Set<TraceHandle>> getDistributionArgs(int position) {
        return constraintData.get(position).postTraceSets;
    }

    public Substitutions getSubstitutions(int position, DataflowTask<?> task) {
        return constraintData.get(position).substitutions.get(task);
    }

    public Substitutions getSubstitutions(int position) {
        ConstraintData c = constraintData.get(position);
        return c.substitutions.get(c.task);
    }

    public TraceHandle getTrace(int position) {
        assert position != 0;
        TraceHandle t = constraintData.get(position).trace;
        assert t != null;
        return t;
    }
}