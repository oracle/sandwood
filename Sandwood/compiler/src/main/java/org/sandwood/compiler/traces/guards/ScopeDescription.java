/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.traces.guards;

import static org.sandwood.compiler.traces.guards.ScopeConstructor.Direction.FORWARDS;
import static org.sandwood.compiler.trees.irTree.IRTree.load;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.scopesState.ScopeState.InitializedState;
import org.sandwood.compiler.compilation.scopesState.ScopeTracking.ScopeTrackingState;
import org.sandwood.compiler.dataflowGraph.scopes.BlockScope;
import org.sandwood.compiler.dataflowGraph.scopes.CommentScope;
import org.sandwood.compiler.dataflowGraph.scopes.IfScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope.ScopeType;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.DistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ForTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.IfElseAssignmentTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ReductionInput;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.traces.TraceHandle;
import org.sandwood.compiler.traces.Traces;
import org.sandwood.compiler.traces.guards.ScopeConstructor.Direction;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

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

        public Substitutions(Map<Variable<?>, VariablePair<?>> substitutions) {
            this.varSubstitutions = Collections.unmodifiableMap(substitutions);
        }

        public Substitutions(Substitutions... ss) {
            Map<Variable<?>, VariablePair<?>> vs = new HashMap<>();
            for(Substitutions s:ss) {
                vs.putAll(s.varSubstitutions);
            }
            varSubstitutions = Collections.unmodifiableMap(vs);
        }

        public Substitutions() {
            varSubstitutions = Collections.emptyMap();
        }

        public Substitutions(VariablePair<?> v) {
            Map<Variable<?>, VariablePair<?>> vs = new HashMap<>();
            vs.put(v.source(), v);
            varSubstitutions = Collections.unmodifiableMap(vs);
        }

        public Substitutions(Substitutions s, VariablePair<?> v) {
            Map<Variable<?>, VariablePair<?>> vs = new HashMap<>(s.varSubstitutions);
            vs.put(v.source(), v);
            varSubstitutions = Collections.unmodifiableMap(vs);
        }

        public Substitutions(Substitutions s, Collection<VariablePair<?>> subs) {
            Map<Variable<?>, VariablePair<?>> vs = new HashMap<>(s.varSubstitutions);
            for(VariablePair<?> v:subs)
                vs.put(v.source(), v);
            varSubstitutions = Collections.unmodifiableMap(vs);
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
                return (A) vp.target();
        }
    }

    public record TaskState(Substitutions substitutions, Initializations initializations) {}

    public record Initializations(Scope scope, InitializedState initialized) {}

    private static class ConstraintData {
        private final Map<DataflowTask<?>, TaskState> states;

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

        private final Scope changeableScope;

        ConstraintData(TraceHandle trace, Direction direction, ConstraintData c) {
            this.task = (direction == FORWARDS ? trace.peek() : trace.get(0)).task;
            Objects.requireNonNull(trace);
            this.trace = trace;
            // Null is used here to mark that the value has not yet been set vs Traces.noDistributionTraces for no
            // traces.
            this.postTraceSets = null;
            Map<DataflowTask<?>, TaskState> m = new HashMap<>();
            m.put(task, c.states.get(c.task));
            states = Collections.unmodifiableMap(m);
            changeableScope = c.changeableScope;
        }

        ConstraintData(DataflowTask<?> task, Set<Set<TraceHandle>> postTraceSets, TaskState state) {
            this.task = task;
            this.trace = null;
            this.postTraceSets = postTraceSets;
            Map<DataflowTask<?>, TaskState> m = new HashMap<>();
            m.put(task, state);
            states = Collections.unmodifiableMap(m);
            changeableScope = null;
        }

        ConstraintData(ConstraintData c, Collection<VariablePair<?>> substitutions) {
            this.task = c.task;
            trace = c.trace;
            postTraceSets = c.postTraceSets;
            Map<DataflowTask<?>, TaskState> m = new HashMap<>(c.states);
            TaskState taskState = c.states.get(c.task);
            TaskState state = new TaskState(new Substitutions(taskState.substitutions, substitutions),
                    taskState.initializations);
            m.put(task, state);
            states = Collections.unmodifiableMap(m);
            changeableScope = c.changeableScope;
        }

        ConstraintData(ConstraintData c, VariablePair<?> substitution) {
            this(c, List.of(substitution));
        }

        ConstraintData(ConstraintData c, Scope changeableScope, TaskState state) {
            this.task = c.task;
            trace = c.trace;
            postTraceSets = c.postTraceSets;
            Map<DataflowTask<?>, TaskState> m = new HashMap<>(c.states);
            m.put(task, state);
            states = Collections.unmodifiableMap(m);
            assert c.changeableScope == null || changeableScope == null;
            this.changeableScope = changeableScope;
        }

        public ConstraintData(ConstraintData c, Set<Set<TraceHandle>> postTraceSets) {
            task = c.task;
            trace = c.trace;
            assert c.postTraceSets == null || postTraceSets == Traces.noDistributionTraces;
            this.postTraceSets = postTraceSets;
            states = c.states;
            changeableScope = c.changeableScope;
        }

        // TODO Add factory methods to avoid the clash on constructor signatures.
        public ConstraintData(ConstraintData c, DataflowTask<?> saveTask) {
            task = c.task;
            trace = c.trace;
            postTraceSets = c.postTraceSets;
            Map<DataflowTask<?>, TaskState> m = new HashMap<>(c.states);
            m.put(saveTask, c.states.get(c.task));
            states = Collections.unmodifiableMap(m);
            changeableScope = c.changeableScope;
        }
    }

    public record ScopeState(Scope scope, TaskState taskState) {}

    /**
     * A map from distribution sample tasks to a list of maps of for scopes. Each map represents an instance of the
     * sample task in the distribution description. The keys in the map are the for scopes from the DAG, and the values
     * are the for scopes created within the distribution description. The values are what we need to compare against,
     * and the keys are the scopes we can get by querying the sample task.
     */
    private final Map<DistributionSampleTask<?, ?>, List<DistSampleDesc<?>>> sampleDescriptions;
    /** The inner scope that additional scopes or trees should be added too. */
    private final Scope innerScope;
    /**
     * The scope state for the inner scope.
     */
    private final ScopeTrackingState scopeState;

    /** A tree describing the probability of this distribution. */
    final IRTreeReturn<DoubleVariable> probability;
    /**
     * A list of classes describing the details required for additional operations on the distribution. One is created
     * when the ScopeConstructor is constructed, and one for every time a trace is applied the classes contain: A set of
     * traces from distributed inputs that need to be satisfied before using this distribution; A list of maps of all
     * the variable substitutions that should be applied when using this distribution; And a list of maps of all the
     * substitution of indexes that should be applied when using this distribution.
     */
    private List<ConstraintData> constraintData;

    /**
     * A map of flags from SampleTasks to if the sample task is fixed. This is an optimisation to help keep control of
     * the size the constructed tree.
     */
    private final Map<SampleTask<?, ?>, Boolean> knownFlags;

    /**
     * A set containing all the scopes that have already been constructed at least once in this distribution description
     * so that if they are to be constructed again the names will need modifying to avoid collisions.
     */
    public final Set<Scope> existingScopes;

    /**
     * A set of all the arrays that have been reduced with values passed as only one of these can appear for each
     */
    public final Set<ReductionInput<?>> reductionInputs;

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
    public ScopeDescription(ScopeState scopeState, DataflowTask<?> task, Set<Scope> existingScopes,
            Set<ReductionInput<?>> reductionInputs, Set<Set<TraceHandle>> distributionTraces,
            CompilationContext compilationCtx) {
        this.innerScope = scopeState.scope;
        this.scopeState = compilationCtx.getState(scopeState.scope);
        probability = IRTree.constant(1.0);
        this.existingScopes = Collections.unmodifiableSet(existingScopes);
        this.reductionInputs = Collections.unmodifiableSet(reductionInputs);
        knownFlags = new HashMap<>();
        List<ConstraintData> cd = new ArrayList<>();
        sampleDescriptions = new HashMap<>(compilationCtx.peekExploredDistSamples());
        cd.add(new ConstraintData(task, distributionTraces, scopeState.taskState));
        constraintData = Collections.unmodifiableList(cd);
    }

    public ScopeDescription insertBlockScope(String comment, CompilationContext compilationCtx) {
        BlockScope blockScope = new BlockScope(innerScope, comment);
        return insertScope(blockScope, compilationCtx);
    }

    public ScopeDescription insertCommentScope(String comment, CompilationContext compilationCtx) {
        CommentScope commentScope = new CommentScope(innerScope, comment);
        return insertScope(commentScope, compilationCtx);
    }

    public record IfElseScopes(ScopeDescription ifScope, ScopeDescription elseScope) {}

    public IfElseScopes insertIfElseScope(IRTreeReturn<BooleanVariable> guard, CompilationContext compilationCtx) {
        IfScope ifScope = new IfScope(innerScope, guard);
        return insertIfElseScope(ifScope, compilationCtx);
    }

    public IfElseScopes insertIfElseScope(BooleanVariable guard, CompilationContext compilationCtx) {
        IfScope ifScope = new IfScope(innerScope, guard);
        return insertIfElseScope(ifScope, compilationCtx);
    }

    private IfElseScopes insertIfElseScope(IfScope scope, CompilationContext compilationCtx) {
        int position = constraintData.size() - 1;
        applySubstitutions(position, compilationCtx);
        compilationCtx.touchScope(scope);
        ScopeTrackingState ifScopeTrackingState = compilationCtx.getState(scope);
        ScopeTrackingState elseScopeTrackingState = compilationCtx.getState(scope.elseScope);
        removeSubstitutions(position, compilationCtx);

        return new IfElseScopes(new ScopeDescription(scope, ifScopeTrackingState, this),
                new ScopeDescription(scope.elseScope, elseScopeTrackingState, this));
    }

    public ScopeDescription insertIfScope(IRTreeReturn<BooleanVariable> guard, CompilationContext compilationCtx) {
        IfScope ifScope = new IfScope(innerScope, guard);
        return insertScope(ifScope, compilationCtx);
    }

    public ScopeDescription insertIfScope(BooleanVariable guard, CompilationContext compilationCtx) {
        IfScope ifScope = new IfScope(innerScope, guard);
        return insertScope(ifScope, compilationCtx);
    }

    public ScopeDescription insertElseScope(IRTreeReturn<BooleanVariable> guard, CompilationContext compilationCtx) {
        IfScope ifScope = new IfScope(innerScope, guard);
        return insertScope(ifScope.elseScope, compilationCtx);
    }

    public ScopeDescription insertElseScope(BooleanVariable guard, CompilationContext compilationCtx) {
        IfScope ifScope = new IfScope(innerScope, guard);
        return insertScope(ifScope.elseScope, compilationCtx);
    }

    public ScopeDescription insertForScope(IntVariable start, IntVariable end, IntVariable step,
            VariableDescription<IntVariable> indexName, boolean incrementing, String comment, int position,
            CompilationContext compilationCtx) {
        ForTask f = ScopeUtils.constructForScope(innerScope, start, end, step, indexName, incrementing);
        ScopeDescription s = insertScope(f, position, compilationCtx);
        s.applySubstitutions(position, compilationCtx);
        compilationCtx.addCommentToScope(f, comment);
        s.removeSubstitutions(position, compilationCtx);
        return s;
    }

    public ScopeDescription insertForScope(ForTask t, String id, int position, CompilationContext compilationCtx) {
        // Perform the insertion of the created scope
        applySubstitutions(position, compilationCtx);

        boolean duplicateScope = existingScopes.contains(t);
        ForTask newFor = ScopeUtils.constructForScope(innerScope, t, duplicateScope, id);

        compilationCtx.touchScope(newFor);
        ScopeTrackingState scopeTrackingState = compilationCtx.getState(newFor);
        removeSubstitutions(position, compilationCtx);

        // Construct new constraint data
        int size = constraintData.size();
        List<ConstraintData> cs = new ArrayList<ConstraintData>(size);

        // Construct values before position
        for(int i = 0; i < position; i++)
            cs.add(constraintData.get(i));

        // Add the substitution for this position
        ConstraintData c = constraintData.get(position);
        TaskState originalTaskState = c.states.get(c.task);
        HashMap<Variable<?>, VariablePair<?>> varSubstitutions = new HashMap<>(
                originalTaskState.substitutions.varSubstitutions);
        IntVariable oldIndex = t.getIndex();
        IntVariable newIndex = newFor.getIndex();
        varSubstitutions.put(oldIndex, new VariablePair<>(oldIndex, newIndex));
        Substitutions substitutions = new Substitutions(varSubstitutions);
        cs.add(new ConstraintData(c, t, new TaskState(substitutions, originalTaskState.initializations)));

        // Copy values after position
        for(int i = position + 1; i < size; i++)
            cs.add(constraintData.get(i));

        // Make the list unmodifiable and create a new scope description.
        cs = Collections.unmodifiableList(cs);
        if(duplicateScope)
            return new ScopeDescription(newFor, scopeTrackingState, existingScopes, cs, this);
        else {
            Set<Scope> newExistingScopes = new HashSet<>(existingScopes);
            newExistingScopes.add(t);
            newExistingScopes = Collections.unmodifiableSet(newExistingScopes);
            return new ScopeDescription(newFor, scopeTrackingState, newExistingScopes, cs, this);
        }
    }

    private ScopeDescription insertScope(Scope scope, int position, CompilationContext compilationCtx) {
        if(compilationCtx.scopeConstructed(scope)) {
            ScopeTrackingState scopeTrackingState = compilationCtx.getState(scope);
            return new ScopeDescription(scope, scopeTrackingState, this);
        } else {
            applySubstitutions(position, compilationCtx);
            compilationCtx.touchScope(scope);
            ScopeTrackingState scopeTrackingState = compilationCtx.getState(scope);
            removeSubstitutions(position, compilationCtx);
            return new ScopeDescription(scope, scopeTrackingState, this);
        }
    }

    // TODO make this private or make a different public method for pointing a target at an existing scope.
    public ScopeDescription insertScope(Scope scope, CompilationContext compilationCtx) {
        return insertScope(scope, constraintData.size() - 1, compilationCtx);
    }

    public ScopeDescription setProbability(IRTreeReturn<DoubleVariable> probability) {
        return new ScopeDescription(probability, this);
    }

    public <A extends Variable<A>> ScopeDescription addSampleScope(DistributionSampleTask<A, ?> sampleTask,
            Variable<A> sampleValue, IRTreeReturn<DoubleVariable> probability, int position) {
        List<ConstraintData> cs = addSubstitution(constraintData, position,
                new VariablePair<>(sampleTask.getOutput(), sampleValue));
        cs = Collections.unmodifiableList(cs);

        Substitutions substitutions = getConstraintState(position).substitutions;
        Map<Scope, IntVariable> scopes = new LinkedHashMap<>();
        Scope s = sampleTask.scope();
        while(s != null) {
            switch(s.getScopeType()) {
                case IF:
                case ELSE: {
                    scopes.put(s, null);
                    break;
                }
                case FOR: {
                    ForTask t = (ForTask) s;
                    IntVariable index = t.getIndex();
                    IntVariable altIndex = substitutions.get(index);
                    scopes.put(t, altIndex);
                    break;
                }
                case BLOCK:
                case COMMENT:
                case GLOBAL:
                case REDUCE:
                    break;
                default:
                    throw new CompilerException("Unexpected scope type " + s.getScopeType());
            }
            s = s.getEnclosingScope();
        }

        DistSampleDesc<A> sampleDesc = new DistSampleDesc<>(sampleValue, scopes);

        Map<DistributionSampleTask<?, ?>, List<DistSampleDesc<?>>> sampleDescriptions = new HashMap<>(
                this.sampleDescriptions);
        List<DistSampleDesc<?>> l = sampleDescriptions.get(sampleTask);
        if(l == null)
            l = new ArrayList<>();
        else
            l = new ArrayList<>(l);
        l.add(sampleDesc);
        sampleDescriptions.put(sampleTask, Collections.unmodifiableList(l));

        return new ScopeDescription(probability, cs, Collections.unmodifiableMap(sampleDescriptions), this);
    }

    public ScopeDescription saveTaskState(int position, DataflowTask<?> task) {
        ConstraintData c = constraintData.get(position);
        int size = constraintData.size();
        List<ConstraintData> cs = new ArrayList<>(constraintData.size());

        for(int i = 0; i < position; i++)
            cs.add(constraintData.get(i));

        cs.add(new ConstraintData(c, task));

        for(int i = position + 1; i < size; i++)
            cs.add(constraintData.get(i));

        cs = Collections.unmodifiableList(cs);

        return new ScopeDescription(cs, this);
    }

    public ScopeDescription(ForTask innerScope, ScopeTrackingState scopeState, Set<Scope> existingScopes,
            List<ConstraintData> constraintData, ScopeDescription d) {
        this.innerScope = innerScope;
        this.scopeState = scopeState;
        probability = d.probability;
        this.existingScopes = existingScopes;
        reductionInputs = d.reductionInputs;
        sampleDescriptions = d.sampleDescriptions;
        this.constraintData = constraintData;
        knownFlags = d.knownFlags;
        assert d.constraintData.size() == constraintData.size();
    }

    public ScopeDescription(Scope innerScope, ScopeTrackingState scopeState, ScopeDescription d) {
        this.innerScope = innerScope;
        this.scopeState = scopeState;
        probability = d.probability;
        existingScopes = d.existingScopes;
        reductionInputs = d.reductionInputs;
        sampleDescriptions = d.sampleDescriptions;
        constraintData = d.constraintData;
        knownFlags = d.knownFlags;
    }

    public ScopeDescription(IRTreeReturn<DoubleVariable> probability, ScopeDescription d) {
        innerScope = d.innerScope;
        scopeState = d.scopeState;
        this.probability = probability;
        existingScopes = d.existingScopes;
        reductionInputs = d.reductionInputs;
        sampleDescriptions = d.sampleDescriptions;
        constraintData = d.constraintData;
        knownFlags = d.knownFlags;
    }

    private ScopeDescription(IRTreeReturn<DoubleVariable> probability, List<ConstraintData> constraintData,
            Map<DistributionSampleTask<?, ?>, List<DistSampleDesc<?>>> sampleDescriptions, ScopeDescription d) {
        innerScope = d.innerScope;
        scopeState = d.scopeState;
        this.probability = probability;
        existingScopes = d.existingScopes;
        reductionInputs = d.reductionInputs;
        this.sampleDescriptions = sampleDescriptions;
        this.constraintData = constraintData;
        knownFlags = d.knownFlags;
        assert d.constraintData.size() == constraintData.size();
    }

    private ScopeDescription(List<ConstraintData> constraintData, ScopeDescription d) {
        innerScope = d.innerScope;
        scopeState = d.scopeState;
        probability = d.probability;
        existingScopes = d.existingScopes;
        reductionInputs = d.reductionInputs;
        sampleDescriptions = d.sampleDescriptions;
        this.constraintData = constraintData;
        knownFlags = d.knownFlags;
        assert d.constraintData.size() == constraintData.size();
    }

    private ScopeDescription(Map<DistributionSampleTask<?, ?>, List<DistSampleDesc<?>>> sampleDescriptions,
            ScopeDescription d) {
        innerScope = d.innerScope;
        scopeState = d.scopeState;
        probability = d.probability;
        existingScopes = d.existingScopes;
        reductionInputs = d.reductionInputs;
        this.sampleDescriptions = sampleDescriptions;
        constraintData = d.constraintData;
        knownFlags = d.knownFlags;
    }

    public record FixedFlagScopes(ScopeDescription fixed, ScopeDescription free) {}

    public FixedFlagScopes constructFixedFlagScopes(SampleTask<?, ?> task, CompilationContext compilationCtx) {
        this.applySubstitutions(compilationCtx);

        // Construct a conditional so that distributions are only explored if the values are not fixed.
        IRTreeReturn<BooleanVariable> ifGuard = load(VariableNames.fixedFlagName(task));
        IfScope ifScope = new IfScope(innerScope, ifGuard);

        compilationCtx.touchScope(ifScope);

        ScopeDescription ifDesc = new ScopeDescription(ifScope, compilationCtx.getState(ifScope), task, true, this,
                constraintData);
        ScopeDescription elseDesc = new ScopeDescription(ifScope.elseScope, compilationCtx.getState(ifScope.elseScope),
                task, false, this, constraintData);

        this.removeSubstitutions(compilationCtx);
        return new FixedFlagScopes(ifDesc, elseDesc);
    }

    public ScopeDescription addFixedFlag(SampleTask<?, ?> task, boolean flagValue) {
        return new ScopeDescription(innerScope, scopeState, task, flagValue, this, constraintData);
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
    private ScopeDescription(Scope innerScope, ScopeTrackingState scopeState, SampleTask<?, ?> task, boolean flagValue,
            ScopeDescription d, List<ConstraintData> constraintData) {
        this.innerScope = innerScope;
        this.scopeState = scopeState;
        probability = d.probability;
        existingScopes = d.existingScopes;
        reductionInputs = d.reductionInputs;
        sampleDescriptions = d.sampleDescriptions;
        this.constraintData = constraintData;
        knownFlags = new HashMap<>(d.knownFlags);
        knownFlags.put(task, flagValue);
        assert d.constraintData.size() == constraintData.size();
    }

    public <A extends Variable<A>> ScopeDescription addSubstitution(int position, Variable<A> sampleOutput,
            DistSampleDesc<A> sampleDesc) {
        return addSubstitution(position, sampleOutput, sampleDesc.sampleValue);
    }

    public ScopeDescription addSubstitutions(int position, List<VariablePair<?>> subs) {
        return new ScopeDescription(position, subs, this);
    }

    public <A extends Variable<A>> ScopeDescription addSubstitution(int position, Variable<A> source,
            Variable<A> target) {
        VariablePair<A> vp = new VariablePair<>(source, target);
        List<VariablePair<?>> l = new java.util.ArrayList<>();
        l.add(vp);
        return new ScopeDescription(position, l, this);
    }

    private <A extends Variable<A>> ScopeDescription(int position, Collection<VariablePair<?>> substitutions,
            ScopeDescription d) {
        innerScope = d.innerScope;
        scopeState = d.scopeState;
        probability = d.probability;
        existingScopes = d.existingScopes;
        reductionInputs = d.reductionInputs;
        sampleDescriptions = d.sampleDescriptions;

        constraintData = Collections.unmodifiableList(addSubstitutions(d.constraintData, position, substitutions));

        knownFlags = d.knownFlags;
    }

    public ScopeDescription addDistributionArguments(Set<Set<TraceHandle>> postTraceSets) {
        return new ScopeDescription(postTraceSets, this);
    }

    private ScopeDescription(Set<Set<TraceHandle>> postTraceSets, ScopeDescription d) {
        innerScope = d.innerScope;
        scopeState = d.scopeState;
        probability = d.probability;
        existingScopes = d.existingScopes;
        reductionInputs = d.reductionInputs;
        sampleDescriptions = d.sampleDescriptions;
        knownFlags = d.knownFlags;
        List<ConstraintData> cd = new ArrayList<>();

        int noConstraints = d.constraintData.size() - 1;
        for(int i = 0; i < noConstraints; i++) {
            ConstraintData c = d.constraintData.get(i);
            cd.add(c);
        }
        ConstraintData c = d.constraintData.get(noConstraints);
        if(c.postTraceSets == null) {
            cd.add(new ConstraintData(c, postTraceSets));
        } else
            throw new CompilerException(
                    "Trying to add distribution traces to a class that already has its distribution traces set.");

        assert d.constraintData.size() == cd.size();
        constraintData = Collections.unmodifiableList(cd);
    }

    public ScopeDescription constructConstraintSpace(TraceHandle trace, Direction direction) {
        return new ScopeDescription(trace, this, direction);
    }

    private ScopeDescription(TraceHandle trace, ScopeDescription d, Direction direction) {
        innerScope = d.innerScope;
        scopeState = d.scopeState;
        probability = d.probability;
        existingScopes = d.existingScopes;
        reductionInputs = d.reductionInputs;
        sampleDescriptions = d.sampleDescriptions;
        knownFlags = d.knownFlags;

        List<ConstraintData> cd = new ArrayList<>(d.constraintData);
        ConstraintData c = cd.get(cd.size() - 1);
        cd.add(new ConstraintData(trace, direction, c));
        constraintData = Collections.unmodifiableList(cd);
    }

    public ScopeDescription markDistArgsApplied(int position) {
        return new ScopeDescription(position, this);
    }

    private ScopeDescription(int position, ScopeDescription d) {
        innerScope = d.innerScope;
        this.scopeState = d.scopeState;
        probability = d.probability;
        existingScopes = d.existingScopes;
        reductionInputs = d.reductionInputs;
        sampleDescriptions = d.sampleDescriptions;
        knownFlags = d.knownFlags;
        List<ConstraintData> cs = new ArrayList<>(d.constraintData.size());
        for(int i = 0; i < position; i++)
            cs.add(d.constraintData.get(i));
        ConstraintData c = d.constraintData.get(position);
        cs.add(new ConstraintData(c, Traces.noDistributionTraces));
        int size = d.constraintData.size();
        for(int i = position + 1; i < size; i++)
            cs.add(d.constraintData.get(i));
        constraintData = Collections.unmodifiableList(cs);
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
    public ScopeDescription addSampleDesc(DistributionSampleTask<?, ?> sampleTask, DistSampleDesc<?> sampleDesc) {
        Map<DistributionSampleTask<?, ?>, List<DistSampleDesc<?>>> sampleDescriptions = new HashMap<>(
                this.sampleDescriptions);
        List<DistSampleDesc<?>> newDescs;
        List<DistSampleDesc<?>> existingDescs = sampleDescriptions.get(sampleTask);
        if(existingDescs != null)
            newDescs = new ArrayList<>(existingDescs);
        else
            newDescs = new ArrayList<>();
        newDescs.add(sampleDesc);
        sampleDescriptions.put(sampleTask, Collections.unmodifiableList(newDescs));
        return new ScopeDescription(Collections.unmodifiableMap(sampleDescriptions), this);
    }

    /**
     * Add a new variable substitution. TODO merge in with the function below once the updated scopes are working
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
        toReturn.add(new ConstraintData(c, v));

        for(int i = position + 1; i < size; i++)
            toReturn.add(constraintData.get(i));

        return toReturn;
    }

    /**
     * Add a new variable substitution.
     * 
     * @param position The position in the list of constraint descriptions that this substitution should be added.
     * @param v        A variable pair containing the value to substitute and the value to replace it with.
     */
    private static List<ConstraintData> addSubstitutions(List<ConstraintData> constraintData, int position,
            Collection<VariablePair<?>> substitutions) {
        int size = constraintData.size();
        if(position >= size)
            throw new CompilerException("This should not be possible.");

        List<ConstraintData> toReturn = new ArrayList<>(size);
        for(int i = 0; i < position; i++)
            toReturn.add(constraintData.get(i));

        ConstraintData c = constraintData.get(position);
        toReturn.add(new ConstraintData(c, substitutions));

        for(int i = position + 1; i < size; i++)
            toReturn.add(constraintData.get(i));

        return toReturn;
    }

    /**
     * Method to apply the all substitutions of indexes and variables described in this description.
     * 
     * @param compilationCtx The compilation context for this compilation.
     */
    public void applySubstitutions(int position, DataflowTask<?> task, CompilationContext compilationCtx) {
        ConstraintData c = constraintData.get(position);
        TaskState t = c.states.get(task);
        // Additional scopes can only be added on to the end of the scope construction, they will not effect back
        // tracking states.
        if(t.initializations.scope != innerScope && task == c.task) {
            ArrayList<ConstraintData> l = new ArrayList<>();
            for(int i = 0; i < position; i++)
                l.add(constraintData.get(i));

            t = new TaskState(t.substitutions, new Initializations(innerScope,
                    t.initializations.initialized.addChangeableScope(c.changeableScope)));
            l.add(new ConstraintData(c, null, t));

            int size = constraintData.size();
            for(int i = position + 1; i < size; i++)
                l.add(constraintData.get(i));

            constraintData = l;
        }

        for(VariablePair<?> v:t.substitutions.varSubstitutions.values())
            addVarPairSubstitute(v, compilationCtx);

        Set<Scope> scopes = getScopes(c.trace, task, scopeState);
        compilationCtx.pushScopeState(scopes, scopeState, t.initializations.initialized);
    }

    private Set<Scope> getScopes(TraceHandle trace, DataflowTask<?> task, ScopeTrackingState scopeState) {
        Set<Scope> taskScopes = new HashSet<>();
        Scope s = scopeState.getScope();
        while(s != null) {
            taskScopes.add(s);
            s = s.getEnclosingScope();
        }

        s = task.scope();
        while(s != null && taskScopes.add(s))
            s = s.getEnclosingScope();

        if(trace == null)
            return taskScopes;
        int i = trace.size() - 1;
        // Find where the task appears in the trace.
        while(trace.get(i).task != task)
            i--;

        while(i >= 0) {
            DataflowTaskArgDesc d = trace.get(i);
            DFType type = d.task.getType();
            if(type == DFType.PUT)
                // When an array is reached the generation of new values stops.
                break;

            // Add the task constraints
            if(type == DFType.IF_ASSIGNMENT) {
                if(d.argPos == 1)
                    taskScopes.add(((IfElseAssignmentTask<?>) d.task).ifScope);
                else if(d.argPos == 2)
                    taskScopes.add(((IfElseAssignmentTask<?>) d.task).ifScope.elseScope);
            }
            s = d.task.scope();
            while(s.getScopeType() != ScopeType.REDUCE && taskScopes.add(s))
                s = s.getEnclosingScope();
            i--;
        }

        return taskScopes;
    }

    /**
     * Method to apply the all substitutions of indexes and variables described in this description.
     * 
     * @param compilationCtx The compilation context for this compilation.
     */
    public void applySubstitutions(int position, CompilationContext compilationCtx) {
        applySubstitutions(position, constraintData.get(position).task, compilationCtx);
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
        compilationCtx.addSubstitute(v.source(), v.target());
    }

    /**
     * Remove substitutions to indexes and variables applied by this distribution.
     * 
     * @param compilationCtx The compilationCtx for this compilation.
     */
    public void removeSubstitutions(int index, DataflowTask<?> task, CompilationContext compilationCtx) {
        ConstraintData c = constraintData.get(index);
        TaskState t = c.states.get(task);
        for(Variable<?> v:t.substitutions.varSubstitutions.keySet())
            compilationCtx.removeSubstitute(v);
        compilationCtx.popScopeState();
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
    public List<DistSampleDesc<?>> getExistingSamples(DistributionSampleTask<?, ?> sampleTask) {
        List<DistSampleDesc<?>> scopes = sampleDescriptions.get(sampleTask);
        if(scopes == null)
            return Collections.emptyList();
        else
            return scopes;
    }

    /**
     * Method to get a map of substitution maps for all constructed distribution sample task. Each element in each list
     * represents an instance of the sample task represented by this distribution description.
     * 
     * @return
     */
    public Map<DistributionSampleTask<?, ?>, List<DistSampleDesc<?>>> getExistingSamples() {
        return sampleDescriptions;
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

    public TaskState getConstraintState(int position) {
        ConstraintData c = constraintData.get(position);
        return c.states.get(c.task);
    }

    public TraceHandle getTrace(int position) {
        assert position != 0;
        TraceHandle t = constraintData.get(position).trace;
        assert t != null;
        return t;
    }

    public <X extends Variable<X>> X constructVariableInScope(VariableDescription<X> varName) {
        return Variable.namedVariable(varName, innerScope);
    }

    public void addTreeToScope(IRTreeVoid tree, CompilationContext compilationCtx) {
        applySubstitutions(compilationCtx);
        compilationCtx.addTreeToScope(innerScope, tree);
        removeSubstitutions(compilationCtx);
    }

    public ScopeDescription mergeInInitialized(int position, InitializedState toMergeState, Scope outerExisting) {
        ConstraintData c = constraintData.get(position);
        TaskState t = c.states.get(c.task);
        // Additional scopes can only be added on to the end of the scope construction, they will not effect back
        // tracking states.

        ArrayList<ConstraintData> l = new ArrayList<>();
        for(int i = 0; i < position; i++)
            l.add(constraintData.get(i));

        t = new TaskState(t.substitutions, new Initializations(innerScope,
                t.initializations.initialized.mergeInExistingInitializations(toMergeState, outerExisting)));
        l.add(new ConstraintData(c, null, t));

        int size = constraintData.size();
        for(int i = position + 1; i < size; i++)
            l.add(constraintData.get(i));

        return new ScopeDescription(l, this);
    }

    public <A extends Variable<A>> ScopeDescription updateDistribution(DistributionSampleTask<?, ?> sampleTask,
            Variable<A> currentValue, Variable<A> newValue) {
        Map<DistributionSampleTask<?, ?>, List<DistSampleDesc<?>>> newSampleDescriptions = new HashMap<>(
                sampleDescriptions);

        List<DistSampleDesc<?>> existingDescs = sampleDescriptions.get(sampleTask);
        if(existingDescs == null)
            return this;

        List<DistSampleDesc<?>> newDescs = new ArrayList<>();
        for(DistSampleDesc<?> d:existingDescs) {
            if(d.sampleValue == currentValue)
                newDescs.add(new DistSampleDesc<A>(newValue, d.scopeSubstitutions));
            else
                newDescs.add(d);
        }
        newSampleDescriptions.put(sampleTask, Collections.unmodifiableList(newDescs));
        return new ScopeDescription(Collections.unmodifiableMap(newSampleDescriptions), this);
    }

    public boolean containsSampleDesc(SampleTask<?, ?> sampleTask, Variable<?> sampleValue) {
        List<DistSampleDesc<?>> l = sampleDescriptions.get(sampleTask);
        if(l == null)
            return false;
        for(DistSampleDesc<?> d:l) {
            if(d.sampleValue == sampleValue)
                return true;
        }
        return false;
    }
}