/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.traces.guards;

import static org.sandwood.compiler.trees.irTree.IRTree.and;
import static org.sandwood.compiler.trees.irTree.IRTree.arrayGet;
import static org.sandwood.compiler.trees.irTree.IRTree.arrayPut;
import static org.sandwood.compiler.trees.irTree.IRTree.constant;
import static org.sandwood.compiler.trees.irTree.IRTree.eq;
import static org.sandwood.compiler.trees.irTree.IRTree.forStmt;
import static org.sandwood.compiler.trees.irTree.IRTree.functionCallReturn;
import static org.sandwood.compiler.trees.irTree.IRTree.initializeVariable;
import static org.sandwood.compiler.trees.irTree.IRTree.load;
import static org.sandwood.compiler.trees.irTree.IRTree.max;
import static org.sandwood.compiler.trees.irTree.IRTree.multiplyDD;
import static org.sandwood.compiler.trees.irTree.IRTree.negateBoolean;
import static org.sandwood.compiler.trees.irTree.IRTree.newArray;
import static org.sandwood.compiler.trees.irTree.IRTree.sequential;
import static org.sandwood.compiler.trees.irTree.IRTree.store;
import static org.sandwood.compiler.trees.irTree.IRTree.treeScope;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.CompilationContext.CompilationPhase;
import org.sandwood.compiler.compilation.util.TreeUtils;
import org.sandwood.compiler.dataflowGraph.Sandwood;
import org.sandwood.compiler.dataflowGraph.scopes.BlockScope;
import org.sandwood.compiler.dataflowGraph.scopes.CommentScope;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.IfScope;
import org.sandwood.compiler.dataflowGraph.scopes.ReductionScope;
import org.sandwood.compiler.dataflowGraph.scopes.ReductionScopeCopied;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope.ScopeType;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.PutTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.DistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ForTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ParForTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.ArrayType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.DistributableRandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.ScalarVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.exceptions.MissingFeatureException;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.traces.Trace;
import org.sandwood.compiler.traces.TraceHandle;
import org.sandwood.compiler.traces.Traces;
import org.sandwood.compiler.traces.guards.ScopeDescription.Substitutions;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRArrayGet;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

/**
 * A class to construct all the required additional scopes for traces and enumerating possible values of distribution
 * samples. These scopes are with guards to ensure the code is only executed when a valid trace configuration exists,
 * i.e. there is a path along the provided trace with the current parameter configurations, and the target has not
 * already been reached with this configuration.
 */
public class ScopeConstructor {
    private static class GuardDesc {
        // Name for the guard that will ensure the body of this code is only executed once. If there is only 1 trace
        // there is only 1 opportunity for the code to run.
        final VariableDescription<BooleanVariable> varDesc;
        // List of scopes that the consumer is in that can change. For each combination of values from the scopes we
        // only want to execute once.
        final List<ForTask> scopes;

        GuardDesc(VariableDescription<BooleanVariable> guardVarDesc, List<ForTask> scopes) {
            this.varDesc = guardVarDesc;
            this.scopes = scopes;
        }
    }

    private static class TraceDesc {
        final DataflowTask<?> origin;
        final DataflowTask<?> consumer;

        TraceDesc(TraceHandle t) {
            origin = t.get(0).task;
            consumer = t.peek().task;
        }
    }

    public static class IfElseScopeConstructors {
        public final ScopeConstructor ifScopeConstructor;
        public final ScopeConstructor elseScopeConstructor;

        IfElseScopeConstructors(ScopeConstructor ifScopeConstructor, ScopeConstructor elseScopeConstructor) {
            this.ifScopeConstructor = ifScopeConstructor;
            this.elseScopeConstructor = elseScopeConstructor;
        }
    }

    /**
     * A list of all the distributions scopes constructed to satisfy the sample distributions passed to this object.
     */
    private final List<ScopeDescription> distributionScopes;

    /**
     * Class to wrap the integer counters
     */
    private static class Counter {
        private int counter = 1;

        public int next() {
            return counter++;
        }

        public int current() {
            return counter;
        }
    }

    /**
     * Identifies used to ensure that all variable ids are unique.
     */
    private static final ThreadLocal<Counter> id = ThreadLocal.withInitial(Counter::new);

    /**
     * A method for removing the id. This is called after each function is added to the compilation context in order to
     * prevent changes in one function propagating to other functions.
     */
    public static void clearId() {
        id.remove();
    }

    /**
     * The compilation context for the build.
     */
    private final CompilationContext compilationCtx;

    /**
     * Set of tasks that guards have been constructed for. TODO make this unmodifiable so it doesn't need to be
     * recreated for every scope context. Currently, this would be a pain to do, and the sharing of values is not a
     * correctness issue, so just leave it for now.
     */
    private final Map<DataflowTask<?>, Integer> guardTasks = new HashMap<>();

    /**
     * The task the different steps of the scope constructor were created for, and helper methods to get the scopes for
     * these tasks.
     */
    private final List<DataflowTask<?>> tasks;

    // Construct a comparator that works on the source sample tasks to provide a deterministic ordering. This is only
    // important for code order, not code correctness. TODO this will fail for example if we have two singleton sets
    // ending at the same point, fix this.
    private static final Comparator<Set<TraceHandle>> traceSetComparator = new Comparator<>() {
        @Override
        public int compare(Set<TraceHandle> o1, Set<TraceHandle> o2) {
            int i = 0;
            TraceHandle[] i1 = new TraceHandle[o1.size()];
            for(TraceHandle t:o1)
                i1[i++] = t;
            Arrays.sort(i1, traceComparator);

            i = 0;
            TraceHandle[] i2 = new TraceHandle[o2.size()];
            for(TraceHandle t:o2)
                i2[i++] = t;
            Arrays.sort(i2, traceComparator);

            return Arrays.compare(i1, i2);
        }
    };

    private static final Comparator<TraceHandle> traceComparator = (t1, t2) -> {
        int s1 = t1.size();
        int s2 = t2.size();
        // Shortest traces first
        if(s1 != s2)
            return s1 - s2;

        for(int i = 0; i < s1; i++) {
            DataflowTaskArgDesc d1 = t1.get(i);
            DataflowTaskArgDesc d2 = t2.get(i);

            int taskId1 = d1.task.id();
            int taskId2 = d2.task.id();

            if(taskId1 != taskId2)
                return taskId1 - taskId2;

            if(d1.argPos != d2.argPos)
                return d1.argPos - d2.argPos;
        }
        // They are identical
        return 0;
    };

    /**
     * Factory method for constructing a DistributedAdditionalScopes under the assumption that the only already
     * constructed scopes are the ones that the task is located in.
     *
     * @param task               The dataflow task that consumes the distributed arguments and the output of which will
     *                           act as the starting point for traces when we construct constraints.
     * @param distributionTraces The set of sets of traces that provide distributed arguments to the task. For any given
     *                           scope configuration holding the task only one of these sets will be satisfiable.
     * @param distComment        Comment if distributed traces are used.
     * @param noDistComment      Comment if there are no distributed traces.
     * @param compilationCtx     The compilation context being used to build the
     * @return A Distribution additional scopes.
     */

    public static ScopeConstructor construct(DataflowTask<?> task, Set<Set<TraceHandle>> distributionTraces,
            String distComment, String noDistComment, CompilationContext compilationCtx) {
        return construct(task, task.scope(), distributionTraces, distComment, noDistComment, compilationCtx);
    }

    /**
     * Factory method for constructing a DistributedAdditionalScopes under the assumption that the only already
     * constructed scopes are the ones that the task is located in.
     *
     * @param task               The dataflow task that this scope constructor is generating scopes for. in the traces
     *                           and is already constructed
     * @param targetScope        The scope that the resulting IRTrees should be written into.
     * @param distributionTraces The set of sets of traces that provide distributed arguments to the task. For any given
     *                           scope configuration holding the task only one of these sets will be satisfiable.
     * @param distComment        Comment if distributed traces are used.
     * @param noDistComment      Comment if there are no distributed traces.
     * @param compilationCtx     The compilation context being used to build the
     * @return A Distribution additional scopes.
     */
    public static ScopeConstructor construct(DataflowTask<?> task, Scope targetScope,
            Set<Set<TraceHandle>> distributionTraces, String distComment, String noDistComment,
            CompilationContext compilationCtx) {
        return new ScopeConstructor(task, targetScope, distributionTraces, distComment, noDistComment, compilationCtx);
    }

    /**
     * Factory method for constructing a DistributedAdditionalScopes under the assumption that the only already
     * constructed scopes are the ones that the task is located in.
     *
     * @param task           The dataflow task that this scope constructor is generating scopes for. in the traces and
     *                       is already constructed
     * @param targetScope    The scope that the resulting IRTrees should be written into.
     * @param comment        Comment if there are no distributed traces.
     * @param compilationCtx The compilation context being used to build the
     * @return A Distribution additional scopes.
     */
    public static ScopeConstructor construct(DataflowTask<?> task, Scope targetScope, String comment,
            CompilationContext compilationCtx) {
        return construct(task, targetScope, Traces.noDistributionTraces, Tree.NoComment, comment, compilationCtx);
    }

    /**
     * Factory method to construct an object for constructing the required additional scopes and guards to ensure the
     * generated code is only executed when there is a configuration of the model that satisfies all the trace
     * requirements.
     * 
     * @param task           The task that these traces relate to.
     * @param comment        Any comment that should be associated with this piece of code.
     * @param compilationCtx The compilation context.
     * @return An object that will create the required additional scopes.
     */
    public static ScopeConstructor construct(SampleTask<?, ?> task, String comment, CompilationContext compilationCtx) {
        return construct(task, task.scope(), comment, compilationCtx);
    }

    /**
     * A constructor for an object that will create additional scopes allowing distribution samples to be evaluated, and
     * paths between variables that travel via arrays to be explored.
     *
     * @param task               The current dataflow task.
     * @param targetScope        The scope that the final constructed tree will be placed into.
     * @param distributionTraces A set of sets of trace handles. Each set represents a collection of traces, all of
     *                           which must be valid for the body of the code to be executed. For each configuration
     *                           only one of the sets will contain traces that can all be satisfied.
     * @param distComment        A comment to add to the constructed tree if distributions are present.
     * @param noDistComment      A comment to add to the constructed tree if no distributions are present.
     * @param compilationCtx     The compilation context within which this tree is being built.
     */
    private <A extends Variable<A>> ScopeConstructor(DataflowTask<?> task, Scope targetScope,
            Set<Set<TraceHandle>> distributionTraces, String distComment, String noDistComment,
            CompilationContext compilationCtx) {
        // Set the compilation context
        this.compilationCtx = compilationCtx;

        // Determine the comment to use.
        String comment = Traces.noDistributionTraces(distributionTraces) ? noDistComment : distComment;

        // Set the already constructed scopes.
        Set<Scope> scopes = getTaskScopes(task);
        Set<ForTask> forScopes = filterForTasks(scopes);
        List<DataflowTask<?>> ts = new ArrayList<>();
        ts.add(task);
        tasks = Collections.unmodifiableList(ts);

        // Construct the initial Distribution description.
        List<ScopeDescription> ds = new ArrayList<>();
        CommentScope commentScope = new CommentScope(targetScope, comment);
        ScopeDescription d = new ScopeDescription(commentScope, task, forScopes, distributionTraces, compilationCtx);
        // If the task is a distributed sample task add the task to set of know samples.
        if(task.isDistribution() && task.getType() == DFType.SAMPLE) {
            DistributionSampleTask<A, ?> sampleTask = (DistributionSampleTask<A, ?>) task;
            Variable<A> sampleVariable = compilationCtx.getSubstitute(sampleTask.getOutput());
            d = addSampleDesc(sampleTask, sampleVariable, d);
        }
        ds.add(d);
        distributionScopes = Collections.unmodifiableList(ds);
    }

    private Set<Scope> getTaskScopes(DataflowTask<?> task) {
        Set<Scope> scopes = new HashSet<>();
        Scope s = task.scope();
        while(s != null) {
            scopes.add(s);
            s = s.getEnclosingScope();
        }
        return scopes;
    }

    private Set<ForTask> filterForTasks(Set<Scope> scopes) {
        Set<ForTask> s = new HashSet<>();
        for(Scope scope:scopes)
            if(scope.getScopeType() == ScopeType.FOR)
                s.add((ForTask) scope);
        return s;
    }

    private List<ForTask> filterForTasks(List<Scope> scopes) {
        ArrayList<ForTask> s = new ArrayList<>();
        for(Scope scope:scopes)
            if(scope.getScopeType() == ScopeType.FOR)
                s.add((ForTask) scope);
        return s;
    }

    /**
     * A constructor used internally when creating isolated versions of the distributed descriptions
     * 
     * @param distributionScopes The list of distributed descriptions to use.
     * @param id                 The id to use to avoid name conflicts.
     * @param guardTasks         The guard tasks for tasks that have already had traces constructed for them
     * @param tasks              The tasks that are the origin of the traces.
     * @param compilationCtx     The compilation context for this compilation.
     */
    private ScopeConstructor(List<ScopeDescription> distributionScopes, int id,
            Map<DataflowTask<?>, Integer> guardTasks, List<DataflowTask<?>> tasks, CompilationContext compilationCtx) {
        assert (distributionScopes.size() != 0);
        this.distributionScopes = distributionScopes;
        this.guardTasks.putAll(guardTasks);
        this.tasks = tasks;
        this.compilationCtx = compilationCtx;
    }

    private ScopeConstructor(ScopeConstructor sc, DataflowTask<?> task) {
        List<ScopeDescription> ds = new ArrayList<>(sc.distributionScopes);
        this.distributionScopes = Collections.unmodifiableList(ds);
        this.guardTasks.putAll(sc.guardTasks);
        // Set the already constructed scopes.
        List<DataflowTask<?>> ts = new ArrayList<>(sc.tasks);
        ts.add(task);
        tasks = Collections.unmodifiableList(ts);
        this.compilationCtx = sc.compilationCtx;
    }

    /**
     * A method to extend a distribution description to also include distributions described in a set of sets of traces.
     * This is used when we want to consider the arguments to a consuming random variable in addition to the ability to
     * reach the random variable from the source random variable for which the distributions in its arguments will have
     * already been considered.
     * 
     * @param d                  The distribution to extend.
     * @param distributionTraces A set of sets of traces, each set representing a possible configuration of the incoming
     *                           traces. for any specific configuration provided by d only one of these will be valid,
     *                           but over the course of an execution multiple of these could be valid.
     * @param position           The position within the list of constraints that the substitutions should be applied up
     *                           to.
     * @return A list of constructed distributions.
     */
    private List<ScopeDescription> addInputDistributions(ScopeDescription d, Set<TraceHandle> distributionTraces,
            int position) {

        Set<Scope> endScopes = getTaskScopes(tasks.get(position));

        // Construct scopes for each of the sets of distribution traces. We use the substitutions from the withGuardDesc
        // as that is the point that we are heading towards.
        return constructDistributionScopes(d, distributionTraces, endScopes, position);
    }

    public ScopeConstructor resetProbabilities() {
        List<ScopeDescription> distributions = new ArrayList<>();
        for(ScopeDescription d:distributionScopes)
            distributions.add(d.setProbability(constant(1.0)));
        return new ScopeConstructor(Collections.unmodifiableList(distributions), id.get().current(), guardTasks, tasks,
                compilationCtx);
    }

    /**
     * A method to construct a list of distribution descriptions to represent the set of possible arrangements of
     * distributed samples based on the set of sets of distributed traces.
     * 
     * @param traces A set of sets of traces. each set of traces represents one possible combination of distributed
     *               samples that makes up this value. The conditional guards will only be valid for one set. The set
     *               contains the empty set if there are no distributions.
     * @return A list of distribution descriptions, with each entry in the list representing each possible combination
     *         of distribution samples.
     */
    private List<ScopeDescription> constructDistributionScopes(ScopeDescription d, Set<TraceHandle> traces,
            Set<Scope> endScopes, int position) {
        // Construct a list of starting points.
        List<ScopeDescription> distributions = new ArrayList<>();
        distributions.add(d);
        // Place the distribution traces in a priority queue to guarantee the code is deterministic in construction.
        PriorityQueue<TraceHandle> p = new PriorityQueue<>(traces);
        // For each sample construct the distribution descriptions, and set these distributions to be the distributions
        // used by the next trace.
        while(!p.isEmpty()) {
            TraceHandle t = p.poll();
            distributions = constructDistributionDescriptions(distributions, t, endScopes, position);
        }
        return distributions;
    }

    /**
     * A method to construct the extra scopes required for a trace to a distribution sample.
     *
     * @param distributions A list of distribution descriptions. The new distributions will be placed into each of
     *                      these.
     * @param trace         The trace to construct distributions for.
     * @return A list of distribution descriptions that extends the distributions in the list of distributions to with
     *         the extra scopes required for this sample task and constraints.
     */
    private List<ScopeDescription> constructDistributionDescriptions(List<ScopeDescription> distributions,
            TraceHandle trace, Set<Scope> endScopes, int position) {

        List<ScopeDescription> toReturn = new ArrayList<>();
        for(ScopeDescription d:distributions)
            toReturn.addAll(constructDistributionDescriptions(d, trace, endScopes, position));

        return toReturn;
    }

    /**
     * A method to construct the extra scopes required for a trace to a distribution sample.
     *
     * @param distribution A list of distribution descriptions. The new distributions will be placed into each of these.
     * @param trace        The trace to construct distributions for.
     * @return A list of distribution descriptions that extends the distributions in the list of distributions to with
     *         the extra scopes required for this sample task and constraints.
     */
    private List<ScopeDescription> constructDistributionDescriptions(ScopeDescription distribution, TraceHandle trace,
            Set<Scope> endScopes, int position) {

        List<ScopeDescription> toReturn = new ArrayList<>();
        // Add guards to the existing distributions for the extra traces
        toReturn.addAll(constructGuardedSample(trace, distribution, endScopes, position));
        // Then add in a new distribution scope.
        toReturn.addAll(constructScopedSample(trace, distribution, endScopes, position));

        return toReturn;
    }

    /**
     * A method to add an additional guard to a distribution to ensure that if the resultant distribution is able to
     * execute, then the provided trace is satisfied.
     *
     * @param trace The trace that the guard will be derived from.
     * @param d     The distribution description to be extended with the guard.
     * @return
     */
    private <A extends ScalarVariable<A>> List<ScopeDescription> constructGuardedSample(TraceHandle trace,
            ScopeDescription d, Set<Scope> endScopes, int position) {
        // List to store the distributions to return.
        List<ScopeDescription> toReturn = new ArrayList<>();

        ProducingDataflowTask<?> sourceTask = trace.get(0).task;
        if(sourceTask.getType() == DFType.SAMPLE) {
            SampleTask<A, ?> sampleTask = (SampleTask<A, ?>) sourceTask;
            if(sampleTask.isDistribution()) {
                // For each distribution sample already created in this distribution description for the sample task at
                // the origin of this trace construct a guard to see if the created sample task satisfies this trace.
                DistributionSampleTask<A, ?> disSampleTask = (DistributionSampleTask<A, ?>) sampleTask;
                for(SampleDesc<?> sampleDesc:d.getExistingSamples(disSampleTask)) {
                    ScopeDescription sampleD = d.addSubstitution(position, disSampleTask.getOutput(),
                            (SampleDesc<A>) sampleDesc);
                    if(sampleDesc.sampleValue != null) {
                        // Create a new description that includes a guard checking this trace is satisfied by the scopes
                        // in subScopes.
                        toReturn.add(TraceArrayRestrictions.constructRestriction(trace, sampleDesc.scopeSubstitutions,
                                constructScopeSubstitutions(endScopes, sampleD, position), sampleD, id.get().next(),
                                true, position, compilationCtx));
                    }
                }
            }
        }
        // And return
        return toReturn;
    }

    /**
     * A method to construct a scope that generates the values for a distributed sampling.
     *
     * @param <A>   The type of the sample.
     * @param trace The trace to construct a sample from, the sample task that an instance of is being constructing from
     *              is found at the start of the trace.
     * @param d     The distribution description that is being extended.
     * @return A list of new distribution descriptions including sampling from the source of this trace.
     */
    private <A extends ScalarVariable<A>> List<ScopeDescription> constructScopedSample(TraceHandle trace,
            ScopeDescription d, Set<Scope> endScopes, int position) {

        // Construct a list to hold the values to return.
        List<ScopeDescription> toReturn = new ArrayList<>();

        // Retrieve the source of the trace.
        ProducingDataflowTask<?> sourceTask = trace.get(0).task;

        if(sourceTask.getType() == DFType.SAMPLE) {
            // Retrieve the sample task an instance of is to be constructed.
            SampleTask<A, ?> sampleTask = (SampleTask<A, ?>) sourceTask;

            // If the sample task is a distribution
            if(sampleTask.isDistribution()) {
                DistributionSampleTask<A, ?> disSampleTask = (DistributionSampleTask<A, ?>) sampleTask;

                // Get the flag to determine if earlier in the distribution description we have already worked out if
                // this value is fixed or not.
                Boolean flagValue = d.getFlag(disSampleTask);

                // If we have not met this sample task before.
                if(flagValue == null) {
                    // Construct a conditional so that distributions are only explored if the values are not fixed.
                    IRTreeReturn<BooleanVariable> ifGuard = load(VariableNames.fixedFlagName(disSampleTask));
                    IfScope ifScope = new IfScope(d.innerScope, ifGuard);

                    // Create a distribution based on this scope with the flag set to true
                    ScopeDescription fixedGuardDistribution = d.addFixedFlagScope(ifScope, disSampleTask, true,
                            compilationCtx);
                    // Construct a distribution that ensure that the trace is satisfied, but no values are changed as
                    // this distribution is fixed.
                    ScopeDescription fixedTrace = TraceArrayRestrictions.constructRestriction(trace,
                            Collections.emptyMap(),
                            constructScopeSubstitutions(endScopes, fixedGuardDistribution, position),
                            fixedGuardDistribution, id.get().next(), true, position, compilationCtx);
                    toReturn.add(fixedTrace);

                    // Construct a distribution with the flag set to false.
                    ScopeDescription elseGuardDistribution = d.addFixedFlagScope(ifScope.elseScope, disSampleTask,
                            false, compilationCtx);
                    // Construct a distribution here the output of the sample task is explored and add it to the list of
                    // distributions.
                    toReturn.add(
                            constructNonFixedValues(trace, endScopes, elseGuardDistribution, disSampleTask, position));
                } else
                // If the sample value is fixed.
                if(flagValue)
                    // We have met this sample task before, and if we are in this branch we know its values are fixed.
                    // Construct a distribution that ensure that the trace is satisfied, but no values are changed as
                    // this distribution is fixed.
                    toReturn.add(TraceArrayRestrictions.constructRestriction(trace, Collections.emptyMap(),
                            constructScopeSubstitutions(endScopes, d, position), d, id.get().next(), true, position,
                            compilationCtx));
                else
                    // We have met this sample task before and if we are in this branch we know its values are not
                    // fixed. Construct a distribution here the output of the sample task is explored and add it to the
                    // list of distributions.
                    toReturn.add(constructNonFixedValues(trace, endScopes, d, disSampleTask, position));
            } else {
                // Non distributed values are just treated as fixed values.
                toReturn.add(TraceArrayRestrictions.constructRestriction(trace, Collections.emptyMap(),
                        constructScopeSubstitutions(endScopes, d, position), d, id.get().next(), true, position,
                        compilationCtx));
            }
        } else {
            // Fixed values are just treated as fixed values.
            toReturn.add(TraceArrayRestrictions.constructRestriction(trace, Collections.emptyMap(),
                    constructScopeSubstitutions(endScopes, d, position), d, id.get().next(), true, position,
                    compilationCtx));
        }
        return toReturn;
    }

    private <A extends ScalarVariable<A>> ScopeDescription constructNonFixedValues(TraceHandle trace,
            Set<Scope> endScopes, ScopeDescription d, DistributionSampleTask<A, ?> sampleTask, int position) {
        // Construct a new description where the fixed scopes are the shared scopes of the consumer trace and the
        // existing distribution.
        List<Scope> changeableScopes = getChangeableScopes(sampleTask, trace);
        Set<Scope> fixedScopes = getTaskScopes(sampleTask);
        fixedScopes.removeAll(changeableScopes);

        // A map to hold the mapping from scopes enclosing the sample to the scopes created in this context.
        Map<ForTask, IntVariable> subScopes = constructScopeSubstitutions(fixedScopes, d, position);

        ScopeDescription knownScope = constructAdditionalScopes(filterForTasks(changeableScopes), subScopes, d,
                position);

        // Construct a scope to test if this scope has already been used. Push the enclosing scope onto the stack.

        IRTreeReturn<BooleanVariable> guard = constant(true);
        for(SampleDesc<?> s:d.getExistingSamples(sampleTask)) {
            IRTreeReturn<BooleanVariable> subGuard = constant(true);
            Map<ForTask, IntVariable> m = s.scopeSubstitutions;
            for(ForTask k:m.keySet()) {
                IntVariable iExisting = m.get(k);
                IntVariable iNew = subScopes.get(k);
                subGuard = and(subGuard, eq(iNew.getForwardIR(compilationCtx), iExisting.getForwardIR(compilationCtx)));
            }
            guard = and(guard, negateBoolean(subGuard));
        }

        // Construct the new scope.
        ScopeDescription guardScope = knownScope.insertScope(new IfScope(knownScope.innerScope, guard), compilationCtx);

        // And use it to construct the sample scope.
        ScopeDescription sampleSettingScope = constructSampleScope(guardScope, sampleTask, position);

        // Construct any extra scopes required for the trace restrictions, and add the guards.
        return TraceArrayRestrictions.constructRestriction(trace, subScopes,
                constructScopeSubstitutions(endScopes, d, position), sampleSettingScope, id.get().next(), true,
                position, compilationCtx);
    }

    /**
     * A method to construct the sample scope that will iterate through all the possible values of the sampling.
     *
     * @param <A>           The type of the value to sample.
     * @param scope         The scope to place the sample scope into.
     * @param disSampleTask The sample task that the values will be generated from.
     * @return
     */
    private <A extends ScalarVariable<A>> ScopeDescription constructSampleScope(ScopeDescription scope,
            DistributionSampleTask<A, ?> disSampleTask, int position) {
        scope.applySubstitutions(position, compilationCtx);

        // Get a name for the value index.
        DistributableRandomVariable<A, ?> r = disSampleTask.randomVariable;
        VariableDescription<IntVariable> indexName = VariableNames.indexName(disSampleTask.getUniqueVarDesc(),
                Integer.toString(id.get().next()));

        // Get the number of states that this variable could be generating.
        IntVariable noStates = r.getNoStates();

        // Construct the for loop to iterate through all the possible states.
        ScopeStack.pushScope(scope.innerScope);
        ForTask newScope = Sandwood.forLoop(Variable.intVariable(0), noStates, Variable.intVariable(1), true,
                (index) -> {
                    // Set alias for better readability, this has no effect on the generated code.
                    index.setAlias(indexName);
                    index.setUniqueVarDesc(indexName);
                });
        compilationCtx.touchScope(newScope);
        ScopeStack.popScope(scope.innerScope);
        String comment = "Enumerating the possible outputs of " + r.getType() + " " + r.getId() + ".";
        compilationCtx.addCommentToScope(newScope, comment);

        // Calculate the probability of this sampling. Generate a unique name for the variables. This must include an id
        // element as there may be several different values drawn from any given sample task.
        VariableDescription<DoubleVariable> probabilityName = VariableNames.calcVarName(
                "probabilitySample" + disSampleTask.id() + "Value" + id.get().next(), VariableType.DoubleVariable,
                false);

        // Get the index
        IRTreeReturn<IntVariable> loopIndex = newScope.getIndex().getForwardIR(compilationCtx);

        // Set the sample value.
        VariableDescription<A> tempName = VariableNames.distributionTempName(
                disSampleTask.getOutput().getVarDesc().name, id.get().next(), disSampleTask.getOutputType());
        compilationCtx.addTreeToScope(newScope,
                initializeVariable(tempName, r.getStateValue(load(indexName)), Tree.NoComment));
        Variable<A> sampleVariable = Variable.namedVariable(tempName, newScope);

        // Recover and store the probability value.
        IRTreeReturn<ArrayVariable<DoubleVariable>> probabilityArray = disSampleTask.getProbabilitiesArray()
                .getForwardIR(compilationCtx);
        IRArrayGet<DoubleVariable> localProbability = arrayGet(probabilityArray, loopIndex);
        compilationCtx.addTreeToScope(newScope,
                initializeVariable(probabilityName, multiplyDD(scope.probability, localProbability),
                        "Update the probability of sampling this value from the distribution value."));

        scope.removeSubstitutions(position, compilationCtx);

        return scope.addSampleScope(newScope, disSampleTask, sampleVariable, load(probabilityName), position,
                compilationCtx);
    }

    /**
     * A method for constructing restrictions where there are no traces to consider, and just the distributions are
     * required.
     *
     * @param treeBuilder A lambda to build a tree inside each conditional location.
     */
    public void addTree(TreeBuilder treeBuilder) {
        int position = noConstraints() - 1;
        for(ScopeDescription d:distributionScopes)
            addTree(d, position, treeBuilder);
    }

    /**
     * A method for constructing restrictions where there are no traces to consider, and just the distributions are
     * required.
     *
     * @param position    An integer describing which substitutions should be applied. 0 is just the substitutions the
     *                    scope constructor was created with, 1 is the substitutions for the first constraint, and so
     *                    on.
     * @param treeBuilder A lambda to build a tree inside each conditional location.
     */
    public void addTree(int position, TreeBuilder treeBuilder) {
        for(ScopeDescription d:distributionScopes)
            addTree(d, position, treeBuilder);
    }

    private int noConstraints() {
        return tasks.size();
    }

    /**
     * A method to apply all the distributed arguments known about by this ScopeConstructor.
     * 
     * @return A new scope constructor with the arguments applied.
     */
    public ScopeConstructor applyAllDistributedArguments() {
        List<ScopeDescription> toProcess = new ArrayList<>(distributionScopes);
        List<ScopeDescription> processed = new ArrayList<>();

        for(int position = 0; position < noConstraints(); position++) {
            for(ScopeDescription d:toProcess)
                processed.addAll(applyDistributedArguments(d, position));

            // Swap the lists ready to iterate on the next position.
            List<ScopeDescription> temp = toProcess;
            toProcess = processed;
            processed = temp;

            processed.clear();
        }
        return new ScopeConstructor(Collections.unmodifiableList(toProcess), id.get().current(), guardTasks, tasks,
                compilationCtx);
    }

    /**
     * A method to apply only one set of distributed arguments, specifically the arguments from the construction of the
     * object, or the least recently applied traces that have not already been applied.
     * 
     * @return A new scope constructor with the arguments applied.
     */
    public ScopeConstructor applyDistributedArguments(int position) {
        List<ScopeDescription> postTracesDescs = new ArrayList<>();
        for(ScopeDescription d:distributionScopes)
            postTracesDescs.addAll(applyDistributedArguments(d, position));
        return new ScopeConstructor(Collections.unmodifiableList(postTracesDescs), id.get().current(), guardTasks,
                tasks, compilationCtx);
    }

    private List<ScopeDescription> applyDistributedArguments(ScopeDescription d, int position) {
        List<ScopeDescription> postTracesDescs = new ArrayList<>();

        // For each group of distributed arguments apply the group.
        PriorityQueue<Set<TraceHandle>> postTracesQueue = new PriorityQueue<>(traceSetComparator);
        Set<Set<TraceHandle>> disArgTraces = d.getDistributionArgs(position);
        postTracesQueue.addAll(disArgTraces);
        while(!postTracesQueue.isEmpty()) {
            Set<TraceHandle> postTraces = postTracesQueue.poll();
            if(postTraces.isEmpty()) {
                Scope blockScope = new BlockScope(d.innerScope, Tree.NoComment);
                ScopeDescription block = d.insertScope(blockScope, compilationCtx);
                postTracesDescs.add(block);
            } else {
                d = d.insertScope(new CommentScope(d.innerScope, d.comment(position)), compilationCtx);
                for(ScopeDescription d1:addInputDistributions(d, postTraces, position))
                    postTracesDescs.add(d1.markDistArgsApplied(position));
            }
        }
        return postTracesDescs;
    }

    public ScopeConstructor addConstraint(TraceHandle consumerToSampleTrace) {
        Set<TraceHandle> traces = new HashSet<>();
        traces.add(consumerToSampleTrace);
        return addConstraints(traces);
    }

    public ScopeConstructor addConstraint(TraceHandle consumerToSampleTrace, Set<Set<TraceHandle>> rvDistTraces) {
        Set<TraceHandle> traces = new HashSet<>();
        traces.add(consumerToSampleTrace);
        return addConstraints(traces, rvDistTraces, false, false);
    }

    public ScopeConstructor addConstraints(Set<TraceHandle> consumerToSampleTraces) {
        return addConstraints(consumerToSampleTraces, Traces.noDistributionTraces, false, false);
    }

    public ScopeConstructor addConstraints(Set<TraceHandle> consumerToSampleTraces, boolean noGuards) {
        Set<Set<TraceHandle>> rvDistTraces = new HashSet<>();
        rvDistTraces.add(new HashSet<>());
        return addConstraints(consumerToSampleTraces, rvDistTraces, noGuards, false);
    }

    public ScopeConstructor addConstraints(Set<TraceHandle> traces, Set<Set<TraceHandle>> rvDistTraces,
            boolean passValues) {
        return addConstraints(traces, rvDistTraces, false, passValues);
    }

    /**
     *
     * @param traces
     * @param rvDistTraces
     * @param noGuards     A parameter to specify that guards are not needed to ensure the body of the scopes is only
     *                     executed once. This is used when an alternative guard is provided by the calling code.
     * @return
     */
    private ScopeConstructor addConstraints(Set<TraceHandle> traces, Set<Set<TraceHandle>> rvDistTraces,
            boolean noGuards, boolean passValues) {
        String comment = constructConstraintsComment(traces);
        ScopeConstructor sc = addIsolation(comment);
        return sc.addConstraintsInternal(traces, rvDistTraces, noGuards, passValues);
    }

    private ScopeConstructor addConstraintsInternal(Set<TraceHandle> traces, Set<Set<TraceHandle>> rvDistTraces,
            boolean noGuards, boolean passValues) {
        if(traces.isEmpty())
            throw new CompilerException("Attempting to apply constraints to an empty set of traces.");

        // Declare a parameter describing the origin, consumer
        TraceDesc traceDesc = constructTraceDescription(traces);
        // Ensure the scope constructors are linked.
        if(traceDesc.origin != tasks.get(noConstraints() - 1))
            throw new CompilerException("Traces: " + traces + " start at " + traceDesc.origin
                    + " they should start at the position of the proceeding Scope Constructor: "
                    + tasks.get(noConstraints() - 1));

        Map<TraceHandle, Set<TraceHandle>> simplifiedTraces = simplifyTraces(traces);

        // Split out the traces that need to be constructed before the SampleToConsumer traces and those that need to be
        // constructed after. At the same time gather any extra scopes needed for the guard. Set of traces that a guard
        // would be required for to track which parts of the distribution have been covered.
        Set<TraceHandle> distPreTraces = new LinkedHashSet<>();
        // A map of maps. The index to the map is the consumer to sample trace, and the value is a map from a set of
        // traces that have to be evaluated before the consumer to distributor trace, to a set of sets of traces that
        // are evaluated afterward.
        Map<TraceHandle, Map<Set<TraceHandle>, Set<Set<TraceHandle>>>> traceGroups = groupTraces(simplifiedTraces,
                rvDistTraces, distPreTraces);

        // Construct a description of the guard if required.
        GuardDesc guardDesc = null;
        if(!noGuards) {
            guardDesc = constructGuardDesc(traceGroups.keySet(), traceDesc, distPreTraces);

            // Test if we need to construct a guard and if we do build the required data structures.
            if(guardDesc != null) {
                // If it is a single boolean value add it to each distribution description, otherwise allocate a global
                // array for it.
                if(guardDesc.scopes.isEmpty()) {
                    VariableDescription<BooleanVariable> varDesc = guardDesc.varDesc;
                    addTree((TreeBuilderInfo info) -> compilationCtx.addTreeToScope(GlobalScope.scope,
                            initializeVariable(varDesc, constant(false),
                                    "Guard to check that at most one copy of the code is executed for a given set of loop iterations.")));
                } else {
                    initializeArrayGuard(guardDesc, traceGroups.keySet());
                }
            }
        }

        ScopeConstructor sc = new ScopeConstructor(this, traceDesc.consumer);
        return sc.addConstraintsInternal(traceGroups, simplifiedTraces, guardDesc, passValues);
    }

    private ScopeConstructor addConstraintsInternal(
            Map<TraceHandle, Map<Set<TraceHandle>, Set<Set<TraceHandle>>>> traceGroups,
            Map<TraceHandle, Set<TraceHandle>> simplifyTraces, GuardDesc guardDesc, boolean passValues) {
        int position = noConstraints() - 1;

        // Get the scopes at the start of the trace.
        Set<Scope> startScopes = getTaskScopes(tasks.get(position - 1));

        // Get the scope at the end of the trace
        DataflowTask<?> consumer = tasks.get(position);
        Set<Scope> endScopes = getTaskScopes(consumer);

        List<ScopeDescription> postTraceList = new ArrayList<>();

        // Construct the traces in reverse order as they will be reversed when added into the compilation context
        // scopes. Doing this results in the simplest trace first being handled first.
        PriorityQueue<TraceHandle> traceQueue = new PriorityQueue<>(traceComparator);
        traceQueue.addAll(traceGroups.keySet());
        while(!traceQueue.isEmpty()) {
            TraceHandle trace = traceQueue.poll();
            // Get the mapping for this trace between the sets of values that needs to run before the producer to
            // consumer trace completes, and the set of trace
            Map<Set<TraceHandle>, Set<Set<TraceHandle>>> traceGroup = traceGroups.get(trace);
            PriorityQueue<Set<TraceHandle>> preTraceQueue = new PriorityQueue<>(traceSetComparator);
            preTraceQueue.addAll(traceGroup.keySet());
            while(!preTraceQueue.isEmpty()) {
                Set<TraceHandle> preTraces = preTraceQueue.poll();
                Set<Set<TraceHandle>> postTracesSets = traceGroup.get(preTraces);

                // For each distribution scope Construct the restrictions from the producer to the consumer
                List<ScopeDescription> withTraceConstraint = new ArrayList<>();
                for(ScopeDescription d:distributionScopes) {
                    d = d.constructConstraintSpace(trace);
                    if(preTraces.isEmpty()) {
                        Map<ForTask, IntVariable> startSubstitutions = constructScopeSubstitutions(startScopes, d,
                                position - 1);
                        Map<ForTask, IntVariable> endSubstitutions = Collections.emptyMap();
                        d = TraceArrayRestrictions.constructRestriction(trace, simplifyTraces.get(trace),
                                startSubstitutions, endSubstitutions, d, id.get().next(), passValues, position,
                                compilationCtx);
                        // If the consumer is a distribution sample add a description for it to the scope description.
                        if(consumer.isDistribution() && consumer.getType() == DFType.SAMPLE)
                            d = addSampleDesc((SampleTask<?, ?>) consumer, null, d);
                        withTraceConstraint.add(d);
                    } else {
                        // Construct a new description where the fixed scopes are the shared scopes of the consumer
                        // trace and the existing distribution.
                        List<Scope> changeableScopes = getChangeableScopes(consumer, trace);
                        Set<Scope> fixedScopes = new HashSet<>(endScopes);
                        fixedScopes.removeAll(changeableScopes);
                        d = constructAdditionalScopes(filterForTasks(changeableScopes),
                                constructScopeSubstitutions(fixedScopes, d, position), d, position);
                        // If the consumer is a distribution sample add a description for it to the scope description.
                        if(consumer.isDistribution() && consumer.getType() == DFType.SAMPLE)
                            d = addSampleDesc((SampleTask<?, ?>) consumer, null, d);

                        // Now the end scopes have been constructed test all the other traces using fixing these end
                        // scopes.
                        List<ScopeDescription> preTraceDescriptions = new ArrayList<>();
                        preTraceDescriptions.add(d);
                        PriorityQueue<TraceHandle> p = new PriorityQueue<>(preTraces);
                        while(!p.isEmpty()) {
                            TraceHandle preTrace = p.poll();
                            preTraceDescriptions = constructDistributionDescriptions(preTraceDescriptions, preTrace,
                                    endScopes, position);
                        }

                        // Construct the distributions going to the consumer
                        for(ScopeDescription ptd:preTraceDescriptions) {
                            withTraceConstraint
                                    .add(TraceArrayRestrictions.constructRestriction(trace, simplifyTraces.get(trace),
                                            constructScopeSubstitutions(startScopes, ptd, position - 1),
                                            constructScopeSubstitutions(endScopes, ptd, position), ptd, id.get().next(),
                                            passValues, position, compilationCtx));
                        }
                    }
                }

                // Construct objects to list the distributions that are yet to be applied.
                List<ScopeDescription> withPostTraces = new ArrayList<>();
                for(ScopeDescription d:withTraceConstraint)
                    withPostTraces.add(d.addDistributionArguments(postTracesSets));

                // For each distribution from the producer to the consumer construct the guard.
                if(guardDesc != null)
                    for(ScopeDescription fromSourceDesc:withPostTraces)
                        postTraceList.add(addGuard(guardDesc, position - 1, fromSourceDesc));
                else
                    postTraceList.addAll(withPostTraces);
            }
        }

        return new ScopeConstructor(Collections.unmodifiableList(postTraceList), id.get().current(), guardTasks, tasks,
                compilationCtx);
    }

    private ScopeDescription constructAdditionalScopes(List<ForTask> scopes, Map<ForTask, IntVariable> substitutions,
            ScopeDescription d, int position) {

        Scope innerScope = d.innerScope;
        Set<ForTask> existingScopes = new HashSet<>(d.existingScopes);

        d.applySubstitutions(compilationCtx);

        for(ForTask t:scopes) {
            ForTask newScope = ScopeUtils.constructForScope(innerScope, t, existingScopes.contains(t),
                    Integer.toString(id.get().next()), compilationCtx);
            existingScopes.add(t);

            // Add in substitutes for the newly created scope
            IntVariable newIndex = newScope.getIndex();
            compilationCtx.addSubstitute(t.getIndex(), newIndex);
            // Store the substitute for later
            substitutions.put(t, newIndex);
            innerScope = newScope;
        }

        // Return the target scope, this scope will still have the same probability of reaching it. We use the
        // sub-scopes for d for this construction as this method is used to construct lots of scopes, not all of which
        // are to support a single sample task. In the case of constructing new scopes for a sample task those new
        // scopes are added in the calling method.
        compilationCtx.touchScope(innerScope);

        for(ForTask t:scopes)
            compilationCtx.removeSubstitute(t.getIndex());

        d.removeSubstitutions(compilationCtx);

        d = d.insertScope(innerScope, existingScopes, compilationCtx);
        Substitutions s = constructSubstitutions(d.getSubstitutions(position), substitutions);
        d = d.addSubstitutions(position, tasks.get(position), s);

        return d;
    }

    // TODO work out what happens here if a substitution is not set because we are using the original variable.
    private static Substitutions constructSubstitutions(Substitutions originalSubstitutions,
            Map<ForTask, IntVariable> substitutions) {
        HashMap<Variable<?>, VariablePair<?>> returnVarSubstitutions = new HashMap<>(
                originalSubstitutions.varSubstitutions);
        for(ForTask t:substitutions.keySet()) {
            IntVariable index = t.getIndex();
            returnVarSubstitutions.put(index, new VariablePair<>(index, substitutions.get(t)));
        }

        HashMap<ReductionScope<?>, ReductionScopeCopied<?>> returnReduceSubstitutions = new HashMap<>(
                originalSubstitutions.reductionScopesSubstitutions);

        return new Substitutions(returnVarSubstitutions, returnReduceSubstitutions);
    }

    private Map<TraceHandle, Set<TraceHandle>> simplifyTraces(Set<TraceHandle> traces) {
        Map<TraceHandle, Set<TraceHandle>> simplifiedTraces = new HashMap<>();
        for(TraceHandle trace:traces) {
            TraceHandle simplifiedTrace = simplifyTrace(trace);
            Set<TraceHandle> rawTraces = simplifiedTraces.computeIfAbsent(simplifiedTrace, k -> new HashSet<>());
            rawTraces.add(trace);
        }
        return simplifiedTraces;
    }

    private TraceHandle simplifyTrace(TraceHandle trace) {
        // Construct a new trace copying over just the first and last elements plus any puts and gets as these are the
        // only elements used to construct the restrictions
        Trace t = new Trace();
        // First element
        t.add(trace.get(0));
        // Intermediate puts and gets
        int size = trace.size() - 1;
        for(int i = 1; i < size; i++) {
            DataflowTaskArgDesc d = trace.get(i);
            DFType type = d.task.getType();
            if(type == DFType.GET || type == DFType.PUT || type == DFType.IF_ASSIGNMENT || type == DFType.REDUCE_INPUT)
                t.add(d);
        }
        // Last element
        t.add(trace.get(size));
        return TraceHandle.getTraceHandle(t);
    }

    private <A extends Variable<A>> ScopeDescription addSampleDesc(SampleTask<A, ?> sampleTask, Variable<A> sampleValue,
            ScopeDescription d) {

        Map<ForTask, IntVariable> taskScopes = new HashMap<>();

        // Get all the for loops and construct alternative indexes for them so that they can be safely substituted.
        Scope s = sampleTask.scope();
        Scope target = d.innerScope;
        d.applySubstitutions(compilationCtx);
        while(s != null) {
            if(s.getScopeType() == ScopeType.FOR) {
                ForTask t = (ForTask) s;
                IntVariable index = t.getIndex();
                VariableDescription<IntVariable> indexName = index.getVarDesc();
                VariableDescription<IntVariable> newIndexName = VariableNames.indexName(indexName,
                        Integer.toString(id.get().next()));
                compilationCtx.addTreeToScope(target,
                        initializeVariable(newIndexName, load(compilationCtx.getSubstitute(index)),
                                "Copy of index so that its values can be safely substituted"));
                taskScopes.put(t, Variable.namedVariable(newIndexName, target));
            }
            s = s.getEnclosingScope();
        }
        d.removeSubstitutions(compilationCtx);

        d = d.insertScope(target, compilationCtx);
        SampleDesc<A> sampleDesc = new SampleDesc<>(sampleValue, taskScopes);
        d = d.addSampleDesc((DistributionSampleTask<?, ?>) sampleTask, sampleDesc, compilationCtx);
        return d;
    }

    private GuardDesc constructGuardDesc(Set<TraceHandle> consumerToSampleTraces, TraceDesc endPointDesc,
            Set<TraceHandle> distPreTraces) {
        // Test if the traces are distinct. This will occur if the traces only differ on if else branches or the puts
        // they recover from arrays and do not contain multiple paths to a reduction.
        if(!Traces.concurrentPaths(consumerToSampleTraces))
            return null;

        // Add a flag to the inside of each of the distribution scopes for tracking the execution. As only one of the
        // distribution scopes will be executed for any given configuration of the model The guard does not need to be
        // further out.

        // Check that the guard cannot be reached via distributed inputs to the trace. If it can, a guards that trace
        // the distributed values will need to be added. This could get very large and very messy.
        if(!distPreTraces.isEmpty())
            throw new MissingFeatureException(
                    "Currently, unable to handle multiple producer consumer traces where the traces depend on distribution samples.");

        // Check this task has not already been used for a guard.
        Integer guardNo = guardTasks.get(endPointDesc.consumer);
        if(guardNo == null)
            guardNo = Integer.valueOf(1);
        else
            guardNo = guardNo + 1;
        guardTasks.put(endPointDesc.consumer, guardNo);

        // Allocate guards if required.
        List<Scope> changeableScopes = getChangeableScopes(endPointDesc.consumer, consumerToSampleTraces);
        VariableDescription<BooleanVariable> guardName = VariableNames.guardName(endPointDesc.origin,
                endPointDesc.consumer, guardNo, VariableType.BooleanVariable);
        return new GuardDesc(guardName, filterForTasks(changeableScopes));
    }

    private List<Scope> getChangeableScopes(DataflowTask<?> consumer, Set<TraceHandle> toConsumerTraces) {
        // Collect all the scopes used in get operations as this will allow us to determine which scopes will remain the
        // same between the producer and the consumer.
        Set<Scope> getScopes = new HashSet<>();
        for(TraceHandle t:toConsumerTraces)
            getScopes.addAll(getUsedArrayScopes(t));

        return getChangeableScopesInternal(consumer, getScopes);
    }

    private List<Scope> getChangeableScopes(DataflowTask<?> consumer, TraceHandle toConsumerTrace) {
        // Collect all the scopes used in get operations as this will allow us to determine which scopes will remain the
        // same between the producer and the consumer.
        Set<Scope> getScopes = getUsedArrayScopes(toConsumerTrace);
        return getChangeableScopesInternal(consumer, getScopes);
    }

    private List<Scope> getChangeableScopesInternal(DataflowTask<?> consumer, Set<Scope> getScopes) {
        // get all the scopes of the consumer
        Stack<Scope> consumerScopes = new Stack<>();
        Scope s = consumer.scope();
        while(s != null) {
            consumerScopes.add(s);
            s = s.getEnclosingScope();
        }

        // Remove scopes that cannot change between the consumer and the producer, the remaining scopes are the ones we
        // will need to have guards for.
        while(!consumerScopes.isEmpty()) {
            s = consumerScopes.pop();
            // Once we find a scope containing an array, stop as all further scopes could change index value.
            if(getScopes.contains(s))
                break;
        }

        // Copy ForTasks to a new list. These are the only ones where the value can vary.
        List<Scope> changeable = new ArrayList<>();
        while(!consumerScopes.isEmpty())
            changeable.add(consumerScopes.pop());
        return changeable;
    }

    private String constructConstraintsComment(Set<TraceHandle> consumerToSampleTraces) {
        // Test if the path goes via an array, and if it does add a comment.
        for(TraceHandle t:consumerToSampleTraces) {
            DataflowTask<?> consumer = t.peek().task;
            DataflowTask<?> origin = t.get(0).task;
            boolean putFound = false;
            for(DataflowTaskArgDesc d:t) {
                DFType type = d.task.getType();
                if(type == DFType.PUT && d.argPos == 2)
                    putFound = true;
                else if(type == DFType.GET && d.argPos == 0 && putFound) {
                    Variable<?> outputVar = consumer.getOutput();
                    return "Looking for a path between " + origin.getType() + " " + origin.id() + " and consumer "
                            + outputVar.getType() + " " + outputVar.getId() + ".";
                }
            }
        }
        return Tree.NoComment;
    }

    private TraceDesc constructTraceDescription(Set<TraceHandle> consumerToSampleTraces) {
        Iterator<TraceHandle> i = consumerToSampleTraces.iterator();
        TraceDesc endPointDesc = new TraceDesc(i.next());
        // Check that all the traces start and end at the same point.
        while(i.hasNext()) {
            TraceHandle t = i.next();
            if(t.get(0).task != endPointDesc.origin)
                throw new CompilerException("All traces must have the same start point.");
            if(t.peek().task != endPointDesc.consumer)
                throw new CompilerException("All traces must have the same end point.");
        }
        return endPointDesc;
    }

    private Map<TraceHandle, Map<Set<TraceHandle>, Set<Set<TraceHandle>>>> groupTraces(
            Map<TraceHandle, Set<TraceHandle>> consumerToSampleTraces, Set<Set<TraceHandle>> rvDistTraces,
            Set<TraceHandle> distPreTraces) {
        Map<TraceHandle, Map<Set<TraceHandle>, Set<Set<TraceHandle>>>> traceGroups = new HashMap<>();
        for(TraceHandle consumerToSampleTrace:consumerToSampleTraces.keySet()) {
            DataflowTaskArgDesc d = consumerToSampleTrace.peek();
            int argPos = d.argPos;
            boolean distributedSample = d.task.getInput(argPos).isDistribution();

            Map<Set<TraceHandle>, Set<Set<TraceHandle>>> splitTraces = new LinkedHashMap<>();
            traceGroups.put(consumerToSampleTrace, splitTraces);
            Set<Integer> constraintGets = getConstraintGets(consumerToSampleTrace);
            // For each set of traces split and store the traces in the set.
            for(Set<TraceHandle> argTraces:rvDistTraces) {
                Set<TraceHandle> rawTraces = consumerToSampleTraces.get(consumerToSampleTrace);
                boolean containsTrace = false;
                for(TraceHandle t:rawTraces)
                    containsTrace = containsTrace || argTraces.contains(t);
                // Only consider the sets of traces that include this trace if the sample is a distribution. The
                // execution must include this trace, so the argTraces set must include it too. Empty sets are an
                // exception to this rule as they only occur when distributed traces are not being considered at all.
                if(!distributedSample || argTraces.isEmpty() || containsTrace) {
                    Set<TraceHandle> preTraces = new LinkedHashSet<>();
                    Set<TraceHandle> postTraces = new LinkedHashSet<>();
                    // For each trace see if it goes in the pre or the post set.
                    for(TraceHandle argTrace:argTraces) {
                        // Check that we are not including the trace we are building constrains for in the distributions
                        // we are constructing.
                        if(!rawTraces.contains(argTrace)) {
                            // If it goes to a different argument, or it is not used in an index or is used after the
                            // consumer trace executes it can be handled later
                            if(argTrace.peek().argPos != argPos || !guardTrace(argTrace, constraintGets))
                                postTraces.add(argTrace);
                            else
                                preTraces.add(argTrace);
                        }
                    }
                    // Store the results.
                    Set<Set<TraceHandle>> s = splitTraces.get(preTraces);
                    if(s == null) {
                        s = new LinkedHashSet<>();
                        splitTraces.put(preTraces, s);

                        // Give identifiers to each of the preTraces.
                        distPreTraces.addAll(preTraces);
                    }
                    s.add(postTraces);
                }
            }
        }
        return traceGroups;
    }

    /**
     * Method to determine which get operations in a trace will be used to create guards.
     * 
     * @param constraintTrace
     * @return A set containing the task ids of the gets that will be used in constraint generation.
     */
    private Set<Integer> getConstraintGets(TraceHandle constraintTrace) {
        Set<Integer> validGets = new HashSet<>();
        int validPuts = 0;
        for(DataflowTaskArgDesc d:constraintTrace)
            switch(d.task.getType()) {
                case GET:
                    if(d.argPos == 0 && validPuts > 0) {
                        validPuts--;
                        validGets.add(d.task.id());
                    }
                    break;
                case PUT:
                    if(d.argPos == 2)
                        validPuts++;
                    break;
                case REDUCE_INPUT:
                    validPuts--;
                    break;
                default:
                    break;

            }
        return validGets;
    }

    /**
     * Method to test if this trace is used in the construction of guards. If it is its construction will be required
     * before the traces that depend on that guard are also constructed.
     * 
     * @param argTrace The trace to check.
     * @return Will the value constructed by this guard be used
     */
    private boolean guardTrace(TraceHandle argTrace, Set<Integer> validGets) {
        for(DataflowTaskArgDesc d:argTrace) {
            switch(d.task.getType()) {
                case GET:
                    // Does this go through the index?
                    if(d.argPos == 1 && validGets.contains(d.task.id()))
                        return true;
                    break;
                case IF_ASSIGNMENT:
                    // Need logic here to check if the value was used in the guard.
                    // ***** This can only support one distributed value in the guard as it must
                    // only be true once for a set of distributions. Otherwise, we need to work out
                    // how to separate the pre-random and post-random variable tree builders. We
                    // also need guards that track the conditions that have been met. ****
                    throw new MissingFeatureException("Logic for an \"if\" needs to be added.");
                default:
                    break;
            }
        }
        return false;
    }

    private <A extends Variable<A>> void initializeArrayGuard(GuardDesc guardDesc,
            Set<TraceHandle> consumerToSampleTraces) {
        // TODO change the allocation here to use an allocator that allows guard name to have a type of boolean.
        // TODO Likewise with the loading of global.
        VariableDescription<ArrayVariable<A>> globalGuardName = (VariableDescription<ArrayVariable<A>>) VariableNames
                .globalGuardName(guardDesc.varDesc,
                        VariableType.getType(VariableType.BooleanVariable, guardDesc.scopes.size()));
        // Get the parallel scope, null if this code is not executed in parallel.
        ParForTask parallelScope = getParallelScope(tasks.get(noConstraints() - 1).scope());
        allocateGlobalState(guardDesc.scopes, globalGuardName, parallelScope);

        addTree((TreeBuilderInfo info) -> compilationCtx.addTreeToScope(GlobalScope.scope, initializeVariable(
                VariableNames.altTypeName(guardDesc.varDesc, globalGuardName.type),
                loadGlobalField(globalGuardName, parallelScope),
                "Guard to check that at most one copy of the code is executed for a given random variable instance.")));

        ScopeConstructor sc = addConstraintsInternal(consumerToSampleTraces, Traces.noDistributionTraces, true, false);
        sc.addTree(noConstraints() - 1, (TreeBuilderInfo info) -> {
            // Construct all the indexes
            List<IRTreeReturn<IntVariable>> indexes = constructGuardScopes(guardDesc.scopes);

            // Then use tree utils to place the value.
            compilationCtx.addTreeToScope(GlobalScope.scope,
                    TreeUtils.putIndirectValue(guardDesc.varDesc, indexes, constant(false), "Set the flags to false"));
        });
    }

    private List<IRTreeReturn<IntVariable>> constructGuardScopes(List<ForTask> guardScopes) {
        List<IRTreeReturn<IntVariable>> indexes = new ArrayList<>();
        for(ForTask t:guardScopes) {
            ScopeStack.pushScope(t);
            IntVariable index = t.getIndex().subtract(t.getStart()).divide(t.getStep());
            ScopeStack.popScope(t);
            indexes.add(index.getForwardIR(compilationCtx));
        }
        return indexes;
    }

    private void addTree(ScopeDescription targetScope, int position, TreeBuilder treeBuilder) {
        // Create a new scope to construct the user provided tree in.
        compilationCtx.pushScope();
        compilationCtx.pushInitializedArrays();

        TreeBuilderInfo treeBuilderInfo = new TreeBuilderInfo(targetScope, tasks);

        // Apply the required substitutions
        treeBuilderInfo.applySubstitutions(position, compilationCtx);

        // Build and recover the tree.
        treeBuilder.buildTree(treeBuilderInfo);
        IRTreeVoid tree = compilationCtx.getOutermostScopeTree();

        // Remove the set substitutions
        treeBuilderInfo.removeSubstitutions(compilationCtx);

        // Restore the initial compilation context.
        compilationCtx.popInitializedArrays();
        compilationCtx.popScope();

        if(!treeBuilderInfo.backTraceInfo.traceConstructedCorrectly())
            throw new CompilerException(
                    "Failed to construct a back trace correctly as get operations were not accounted for.");

        // And add the constructed tree to the newly created scope.
        compilationCtx.addTreeToScope(targetScope.innerScope, tree);
    }

    private Set<Scope> getUsedArrayScopes(TraceHandle t) {
        Set<Scope> scopes = new HashSet<>();
        int count = 0;
        for(int pos = t.size() - 1; pos > 0; pos--) {// Greater than 0 as we know position 0 is a sample task.
            DataflowTask<?> task = t.get(pos).task;
            switch(task.getType()) {
                case GET:
                case REDUCE_INPUT:
                    count++;
                    break;
                case PUT:
                    if(count != 0) {
                        count--;
                        PutTask<?> pt = (PutTask<?>) task;
                        scopes.add(pt.array.scope());
                    }
                    break;
                default:
                    break;
            }
        }

        return scopes;
    }

    private ScopeDescription addGuard(GuardDesc guardDesc, int position, ScopeDescription targetDesc) {
        targetDesc.applySubstitutions(position, compilationCtx);
        List<IRTreeReturn<IntVariable>> indexes = constructGuardScopes(guardDesc.scopes);

        IRTreeReturn<BooleanVariable> c = IRTree.negateBoolean(TreeUtils.getIndirectValue(guardDesc.varDesc, indexes));

        ScopeDescription guardedDesc = targetDesc.insertScope(new IfScope(targetDesc.innerScope, c), compilationCtx);

        // Required to construct the scope before the substitutions are applied.
        compilationCtx.addTreeToScope(guardedDesc.innerScope, TreeUtils.putIndirectValue(guardDesc.varDesc, indexes,
                constant(true), "The body will execute, so should not be executed again"));
        targetDesc.removeSubstitutions(position, compilationCtx);
        return guardedDesc;
    }

    private Map<ForTask, IntVariable> constructScopeSubstitutions(Set<Scope> scopes, ScopeDescription d, int position) {
        d.applySubstitutions(position, compilationCtx);
        Map<ForTask, IntVariable> toReturn = new HashMap<>();
        for(Scope scope:scopes)
            if(scope.getScopeType() == ScopeType.FOR) {
                ForTask t = (ForTask) scope;
                toReturn.put(t, (IntVariable) compilationCtx.getSubstitute(t.getIndex()));
            }
        d.removeSubstitutions(position, compilationCtx);
        return toReturn;
    }

    // TODO pull these methods out and make them static util methods that can be called by this class and
    // InferenceGenerator base.
    private <A extends Variable<A>> void allocateGlobalState(List<ForTask> scopes,
            VariableDescription<ArrayVariable<A>> guardName, ParForTask parallelScope) {
        Map<ForTask, IntVariable> noStates = new HashMap<>();
        for(ForTask t:scopes) {
            Scope enclosingScope = t.getEnclosingScope();
            ScopeStack.pushScope(enclosingScope);
            noStates.put(t, t.getEnd().subtract(t.getStart()).divide(t.getStep()));
            ScopeStack.popScope(enclosingScope);
        }

        compilationCtx.pushScope();
        compilationCtx.pushSubstitutions();
        // Because of the reuse of max this needs to be serial. We could overcome this by taking a copy of the value of
        // max in a new in, but as I am not sure that parallel allocation is a beneficial, for now we will make this
        // serial and skip any overhead.
        compilationCtx.pushIsSerial(true);

        CompilationPhase phase = compilationCtx.phase;
        compilationCtx.phase = CompilationPhase.ALLOCATION;
        List<IRTreeReturn<IntVariable>> dims = new ArrayList<>();
        for(ForTask t:scopes) {
            VariableDescription<IntVariable> maxName = VariableNames
                    .calcVarName("max_" + t.getIndex().getUniqueVarDesc(), VariableType.IntVariable, true);

            // Set initial value for the array.
            compilationCtx.enterScope(t);
            compilationCtx.addTreeToScope(GlobalScope.scope,
                    initializeVariable(maxName, constant(0),
                            "Calculate the largest index of " + t.getIndex().getVarDesc() + " that is possible and "
                                    + "allocate an array to hold the guard for each of these."));
            compilationCtx.leaveScope(t);
            // Search for a larger value.
            IRTreeVoid updateMax = store(maxName, max(load(maxName), noStates.get(t).getForwardIR(compilationCtx)),
                    Tree.NoComment);
            compilationCtx.addTreeToScope(t.getEnclosingScope(), updateMax);

            dims.add(load(maxName));
        }

        ArrayType<A> arrayType = (ArrayType<A>) VariableType.getType(VariableType.BooleanVariable, dims.size());

        // Allocate and store the largest value
        globalFieldAllocation(guardName, newArray(dims, arrayType), parallelScope == null);
        // Get the allocator
        IRTreeVoid allocator = compilationCtx.getOutermostScopeTree();

        compilationCtx.phase = phase;

        compilationCtx.popIsSerial();
        compilationCtx.popSubstitutions();
        compilationCtx.popScope();

        createGlobalField(guardName, allocator, parallelScope == null);
    }

    private <A extends Variable<A>> void createGlobalField(VariableDescription<A> fieldName, IRTreeVoid allocator,
            boolean isSerial) {
        switch(compilationCtx.target) {
            case SingleThreadCPU:
                compilationCtx.addConstructedClassField(fieldName, allocator, null);
                break;
            case MultiThreadCPU: {
                if(isSerial)
                    compilationCtx.addConstructedClassField(fieldName, allocator, null);
                else {
                    VariableDescription<ArrayVariable<A>> arrayName = VariableNames.altTypeName(fieldName,
                            VariableType.arrayType(fieldName.type));
                    compilationCtx.addConstructedClassField(arrayName, allocator, null);
                }
                break;
            }
            case GPU:
            default:
                throw new CompilerException("Unknown compilation type " + compilationCtx.target);
        }
    }

    private <V extends Variable<V>> IRTreeReturn<V> loadGlobalField(VariableDescription<V> varDesc,
            ParForTask parallelScope) {
        switch(compilationCtx.target) {
            case SingleThreadCPU:
                return load(varDesc);
            case MultiThreadCPU: {
                if(parallelScope == null) {
                    return load(varDesc);
                } else {
                    VariableDescription<ArrayVariable<V>> altName = VariableNames.altTypeName(varDesc,
                            VariableType.arrayType(varDesc.type));
                    IRTreeReturn<ArrayVariable<V>> array = load(altName);

                    // First we have to construct the index name used in the new target, and then we can construct the
                    // thread id name.
                    VariableDescription<IntVariable> threadID = VariableNames
                            .threadIdName(VariableNames.calcVarName(parallelScope.getIndex().getUniqueVarDesc().name));
                    IRTreeReturn<IntVariable> index = load(threadID);
                    return arrayGet(array, index);
                }
            }
            case GPU:
            default:
                throw new CompilerException("Unknown compilation type " + compilationCtx.target);
        }
    }

    private ParForTask getParallelScope(Scope sourceScope) {
        while(sourceScope != null && sourceScope.isSerial(compilationCtx))
            sourceScope = sourceScope.getEnclosingScope();
        return (ParForTask) sourceScope;
    }

    private <A extends Variable<A>> void globalFieldAllocation(VariableDescription<A> fieldName,
            IRTreeReturn<A> localAllocation, boolean isSerial) {
        switch(compilationCtx.target) {
            case SingleThreadCPU:
            case MultiThreadCPU: {
                if(isSerial) {
                    IRTreeVoid storeArray = store(fieldName, localAllocation,
                            "Allocation of " + fieldName + " for single threaded execution");
                    compilationCtx.addTreeToScope(GlobalScope.scope, storeArray);
                    break;
                } else {
                    List<IRTreeVoid> subtrees = new ArrayList<>();

                    VariableDescription<ArrayVariable<A>> arrayName = VariableNames.altTypeName(fieldName,
                            VariableType.arrayType(fieldName.type));
                    VariableDescription<IntVariable> threadCount = VariableNames.calcVarName("threadCount",
                            VariableType.IntVariable, true);
                    subtrees.add(initializeVariable(threadCount,
                            functionCallReturn(VariableType.IntVariable, "threadCount"), "Get the thread count."));
                    subtrees.add(store(arrayName, newArray(load(threadCount), (ArrayType<A>) arrayName.type),
                            "Allocate an array to hold a copy per thread"));
                    VariableDescription<IntVariable> index = VariableNames.calcVarName("index",
                            VariableType.IntVariable, true);
                    IRTreeVoid body = arrayPut(load(arrayName), load(index), localAllocation, Tree.NoComment);
                    subtrees.add(forStmt(body, constant(0), load(threadCount), constant(1), index, true,
                            "Populate the array with a copy per thread"));

                    compilationCtx.addTreeToScope(GlobalScope.scope, treeScope(sequential(subtrees, Tree.NoComment),
                            "Allocation of " + fieldName + " for multithreaded execution"));
                }
                break;
            }
            case GPU:
            default:
                throw new CompilerException("Unknown compilation type " + compilationCtx.target);
        }
    }

    public ScopeConstructor addIsolation() {
        return addIsolation(Tree.NoComment);
    }

    public ScopeConstructor addIsolation(String comment) {
        List<ScopeDescription> isolatedDescriptions = new ArrayList<>();
        for(ScopeDescription d:distributionScopes) {
            Scope s = new BlockScope(d.innerScope, comment);
            isolatedDescriptions.add(d.insertScope(s, compilationCtx));
        }
        return new ScopeConstructor(Collections.unmodifiableList(isolatedDescriptions), id.get().current(), guardTasks,
                tasks, compilationCtx);
    }

    public ScopeConstructor addComment(String comment) {
        List<ScopeDescription> commentDescriptions = new ArrayList<>();
        for(ScopeDescription d:distributionScopes) {
            Scope s = new CommentScope(d.innerScope, comment);
            commentDescriptions.add(d.insertScope(s, compilationCtx));
        }
        return new ScopeConstructor(Collections.unmodifiableList(commentDescriptions), id.get().current(), guardTasks,
                tasks, compilationCtx);
    }

    public IfElseScopeConstructors addCondition(IRTreeReturn<BooleanVariable> guard) {
        List<ScopeDescription> ifDescriptions = new ArrayList<>();
        List<ScopeDescription> elseDescriptions = new ArrayList<>();

        for(ScopeDescription d:distributionScopes) {
            IfScope s = new IfScope(d.innerScope, guard);
            ifDescriptions.add(d.insertScope(s, compilationCtx));
            elseDescriptions.add(d.insertScope(s.elseScope, compilationCtx));
        }

        return new IfElseScopeConstructors(
                new ScopeConstructor(Collections.unmodifiableList(ifDescriptions), id.get().current(), guardTasks,
                        tasks, compilationCtx),
                new ScopeConstructor(Collections.unmodifiableList(elseDescriptions), id.get().current(), guardTasks,
                        tasks, compilationCtx));
    }

    public ScopeConstructor addFixedFlag(SampleTask<?, ?> task, boolean value) {
        List<ScopeDescription> fixedDescriptions = new ArrayList<>();
        for(ScopeDescription d:distributionScopes) {
            fixedDescriptions.add(d.addFixedFlag(task, value));
        }
        return new ScopeConstructor(Collections.unmodifiableList(fixedDescriptions), id.get().current(), guardTasks,
                tasks, compilationCtx);
    }

    public ScopeConstructor insertScopes(Scope insertScope) {
        // Get a list of all the scopes to add, and reverse it so the outermost scope comes first.
        List<Scope> scopes = new ArrayList<>();
        while(insertScope != null) {
            scopes.add(insertScope);
            insertScope = compilationCtx.substituteScope(insertScope.getEnclosingScope());
        }
        Collections.reverse(scopes);

        List<ScopeDescription> replacementDescs = new ArrayList<>(distributionScopes);
        for(Scope s:scopes) {
            List<ScopeDescription> newDescs = new ArrayList<>();
            switch(s.getScopeType()) {
                case BLOCK: {
                    BlockScope blockScope = (BlockScope) s;
                    for(ScopeDescription d:replacementDescs) {
                        BlockScope newBlockScope = new BlockScope(d.innerScope, blockScope.comment);
                        newDescs.add(d.insertScope(newBlockScope, compilationCtx));
                    }
                    break;
                }
                case COMMENT: {
                    CommentScope commentScope = (CommentScope) s;
                    for(ScopeDescription d:replacementDescs) {
                        CommentScope newCommentScope = new CommentScope(d.innerScope, commentScope.comment);
                        newDescs.add(d.insertScope(newCommentScope, compilationCtx));
                    }
                    break;
                }
                case ELSE:
                    throw new CompilerException("Else branches are not allowed as inserted distributions scopes.");
                case FOR:
                    ForTask forScope = (ForTask) s;
                    for(ScopeDescription d:replacementDescs) {
                        ForTask newForScope = ScopeUtils.constructForScope(d.innerScope, forScope, compilationCtx);
                        newDescs.add(d.insertScope(newForScope, compilationCtx));
                    }
                    break;
                case GLOBAL:
                    newDescs.addAll(distributionScopes);
                    break;
                case IF: {
                    IfScope ifScope = (IfScope) s;
                    for(ScopeDescription d:replacementDescs) {
                        IfScope newIfScope = new IfScope(d.innerScope, ifScope);
                        newDescs.add(d.insertScope(newIfScope, compilationCtx));
                    }
                    break;
                }
                case REDUCE:
                    throw new MissingFeatureException(
                            "Reduce operations are not currently allowed as inserted distributions scopes.");
            }
            replacementDescs.clear();
            replacementDescs.addAll(newDescs);
        }

        for(ScopeDescription d:replacementDescs)
            compilationCtx.touchScope(d.innerScope);

        return new ScopeConstructor(Collections.unmodifiableList(replacementDescs), id.get().current(), guardTasks,
                tasks, compilationCtx);
    }
}
