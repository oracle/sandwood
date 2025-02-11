/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.inference;

import static org.sandwood.compiler.trees.irTree.IRTree.load;
import static org.sandwood.compiler.trees.irTree.IRTree.negateBoolean;
import static org.sandwood.compiler.trees.irTree.IRTree.voidFunction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sandwood.common.execution.ExecutionType;
import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.util.FunctionUtils;
import org.sandwood.compiler.compilation.util.SampleDesc;
import org.sandwood.compiler.dataflowGraph.scopes.ElseScope;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.IfScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope.ScopeType;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.DistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ForTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.names.FunctionName;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.Trace;
import org.sandwood.compiler.traces.TraceHandle;
import org.sandwood.compiler.traces.Traces;
import org.sandwood.compiler.traces.guards.ScopeConstructor;
import org.sandwood.compiler.traces.guards.ScopeConstructor.IfElseScopeConstructors;
import org.sandwood.compiler.traces.guards.TraceArrayRestrictions;
import org.sandwood.compiler.traces.guards.TreeBuilderInfo;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.Visibility;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;
import org.sandwood.compiler.trees.irTree.IRVoidFunction;
import org.sandwood.compiler.trees.irTree.util.KnownValuesIR;
import org.sandwood.compiler.trees.irTree.util.KnownValuesIR.KnownValue;

public abstract class InferenceGeneratorBase<A extends Variable<A>, B extends RandomVariable<A, B>, S extends SampleDesc<A, B>, FuncData extends InferenceGeneratorBase.FunctionData<A, B, S>>
        implements InferenceGenerator<A, B> {

    static protected abstract class FunctionData<A extends Variable<A>, B extends RandomVariable<A, B>, S extends SampleDesc<A, B>> {

        public final S sampleDesc;

        public final boolean isSerial;

        /**
         * Map of random variables to the traces from them to the intermediate variable
         */
        private final Map<RandomVariable<?, ?>, Set<TraceHandle>> toConsumingRV;

        /** The source random variable */
        public final B sourceRandom;

        public final Set<Set<TraceHandle>> distributionTraces;

        /**
         * A flag to mark if the sample has been updated in the current phase.
         */
        public final boolean sampleUpdated;

        /**
         *
         * @param sampleDesc
         * @param sampleUpdated  Does this inference approach update the sample value? Typically the answer to this will
         *                       be no for conjugate priors, and yes for Metropolis-Hastings, and marginalization.
         * @param compilationCtx
         */

        protected FunctionData(S sampleDesc, boolean sampleUpdated, CompilationContext compilationCtx) {

            this.sampleDesc = sampleDesc;

            // Determine if it is possible to have multiple instances of this sampling calculated at the same time.
            boolean isSerial = true;
            for(Scope d:sampleDesc.scopes)
                isSerial = isSerial && d.isSerial(compilationCtx);
            this.isSerial = isSerial;

            sourceRandom = sampleDesc.sample.randomVariable;

            toConsumingRV = compilationCtx.traces.getTracesRVToSampleTask(sampleDesc.sample);

            /*
             * TODO if we change to having traces between random variables and the samples they generate that go via
             * arrays this will need to change as we no longer have a just the one possible random variable for the
             * sample, but will need to use traces to work out which random variable we are interested in.
             */
            distributionTraces = Traces.findDistributionTraces(sampleDesc.sample.randomVariable, compilationCtx);

            this.sampleUpdated = sampleUpdated;
        }

        protected Set<RandomVariable<?, ?>> getConsumingRVs() {
            return toConsumingRV.keySet();
        }

        /**
         * Set of traces from the provided random variable to the sample task we are currently calculating a value for.
         * 
         * @param consumingRV The consuming random variable.
         * @return
         */
        public Set<TraceHandle> getToConsumingRV(RandomVariable<?, ?> consumingRV) {
            return toConsumingRV.get(consumingRV);
        }
    }

    protected abstract FuncData getFunctionData(SampleTask<A, B> sample, CompilationContext compilationCtx);

    /**
     * Method to generate a function calculate the result of a conjugate pairing.
     *
     * @param sample         The sample task that generated the value we what to calculate.
     * @param compilationCtx file tracking all the details of the compilation of this class.
     */
    @Override
    public IRVoidFunction constructFunction(SampleTask<A, B> sample, CompilationContext compilationCtx) {
        // Clear function dependent values to prevent pollution from one context to another.
        compilationCtx.initialize();

        // Mark that an inference function is being constructed.
        compilationCtx.setInInference(true);

        // Get and object for passing function data around and keeping it all together.
        FuncData funcData = getFunctionData(sample, compilationCtx);

        // Add any constructors before we start restructuring scopes.
        allocateGlobalStateInternal(compilationCtx, funcData);

        Scope targetScope = constructTargetScope(funcData, compilationCtx);

        // Clear the context ready to start
        compilationCtx.pushScope();
        constructFunctionVariablesInternal(compilationCtx, funcData);

        compilationCtx.pushScope();

        ScopeConstructor a = ScopeConstructor.construct(funcData.sampleDesc.sample, getBackTraceScope(funcData),
                funcData.distributionTraces,
                "Exploring all the possible distribution values for random variable "
                        + funcData.sampleDesc.sample.randomVariable.getId() + " creating sample task "
                        + funcData.sampleDesc.id + ".",
                Tree.NoComment, compilationCtx);

        a = a.addFixedFlag(funcData.sampleDesc.sample, false);

        a.addTree((TreeBuilderInfo info) -> backTraceScopeStartIR(funcData, info, compilationCtx));

        { // Handle all the normal samples
            ScopeConstructor sourceDisArgs = a.applyAllDistributedArguments();
            sourceDisArgs.addTree(0,
                    (TreeBuilderInfo info) -> getPerSourceConfigStartIR(funcData, info, compilationCtx));

            // For each random variable that consumes the results of this sample.
            for(RandomVariable<?, ?> consumingRV:funcData.getConsumingRVs()) {
                ScopeConstructor b = sourceDisArgs.resetProbabilities();
                b = b.addIsolation("Processing random variable " + consumingRV.getId() + ".");

                Map<SampleTask<?, ?>, Set<TraceHandle>> groupedTraces = new LinkedHashMap<>();
                addTraces(groupedTraces, compilationCtx.traces.getIntermediateVariableTraces(consumingRV));
                Set<Set<TraceHandle>> rvDistTraces = Traces.findDistributionTraces(consumingRV, compilationCtx);
                // Get the trace of operations between the consuming random variable, and the
                // sample task.
                Set<TraceHandle> consumerTraces = funcData.getToConsumingRV(consumingRV);

                b = b.addConstraints(consumerTraces, rvDistTraces, funcData.sampleUpdated);

                // Inside this loop we have a Source Sample -> ConsumerRV -> Consumer Sample
                // task mapping, and now explore the distributed properties.
                for(SampleTask<?, ?> s:groupedTraces.keySet()) {
                    if(s.isDistribution()) {
                        // We know that the "sample" value is not fixed because we are calculating it.
                        if(s != sample) {
                            IRTreeReturn<BooleanVariable> ifGuard = load(VariableNames.fixedFlagName(s));
                            IfElseScopeConstructors ifsc = b.addCondition(ifGuard);
                            processSample(s, consumingRV, ifsc.ifScopeConstructor, groupedTraces.get(s), funcData,
                                    compilationCtx);
                        }
                    } else {
                        processSample(s, consumingRV, b, groupedTraces.get(s), funcData, compilationCtx);
                    }
                }
            }

            sourceDisArgs.addTree(0, (TreeBuilderInfo info) -> getPerSourceConfigEndIR(funcData, info, compilationCtx));
        }

        { // Handle all the distributed samples
          // For each random variable that consumes the results of this sample.
            for(RandomVariable<?, ?> consumingRV:funcData.getConsumingRVs()) {

                // Find any distribution sample tasks for this random variable.
                Set<DistributionSampleTask<?, ?>> tasks = new HashSet<>();
                for(DataflowTask<?> d:consumingRV.getConsumers()) {
                    if(d.getType() == DFType.SAMPLE) {
                        SampleTask<?, ?> s = (SampleTask<?, ?>) d;
                        if(s.isDistribution())
                            tasks.add((DistributionSampleTask<?, ?>) d);
                    }
                }

                if(!tasks.isEmpty()) {
                    ScopeConstructor b = a.addIsolation("Processing random variable " + consumingRV.getId() + ".");

                    Set<Set<TraceHandle>> rvDistTraces = Traces.findDistributionTraces(consumingRV, compilationCtx);
                    // Get the trace of operations between the consuming random variable, and the
                    // sample task.
                    Set<TraceHandle> consumerTraces = funcData.getToConsumingRV(consumingRV);

                    b = b.addConstraints(consumerTraces, rvDistTraces, funcData.sampleUpdated);

                    // Inside this loop we have a Source Sample -> ConsumerRV -> Consumer Sample
                    // task mapping, and now explore the distributed properties.
                    for(DistributionSampleTask<?, ?> ds:tasks) {
                        if(ds == sample) // If the distributed sample is the same as the sample we know it is not
                            // fixed, otherwise we have to test.
                            processDistributionSample(ds, consumingRV, b, funcData, compilationCtx);
                        else {
                            IRTreeReturn<BooleanVariable> ifGuard = negateBoolean(
                                    load(VariableNames.fixedFlagName(ds)));
                            IfElseScopeConstructors ifsc = b.addCondition(ifGuard);
                            processDistributionSample(ds, consumingRV, ifsc.ifScopeConstructor, funcData,
                                    compilationCtx);
                        }
                    }
                }
            }
        }

        a.addTree((TreeBuilderInfo info) -> backTraceScopeEndIR(funcData, info, compilationCtx));

        IRTreeVoid t = compilationCtx.getOutermostScopeTree();
        compilationCtx.popScope();
        compilationCtx.addTreeToScope(GlobalScope.scope, t);

        finalize(funcData, compilationCtx);

        // Construct sample value and all the intermediate variables.
        if(funcData.sampleDesc.sample.isDistribution())
            addDistributionProbabilities(funcData, compilationCtx);
        else {
            compilationCtx.pushScope();
            addSampleValueTree(funcData, compilationCtx);
            IRTreeVoid valueTree = compilationCtx.getOutermostScopeTree();
            compilationCtx.popScope();
            compilationCtx.addTreeToScope(GlobalScope.scope, valueTree);
        }

        // Get the constructed tree.
        IRTreeVoid result = compilationCtx.getOutermostScopeTree();

        // Place the result in the correct scope.
        compilationCtx.popScope();
        compilationCtx.addTreeToScope(targetScope, result);
        result = compilationCtx.getOutermostScopeTree();

        // Clear any leftover substitutions.
        removeTargetScopeSubstitutes(funcData, compilationCtx);

        /*
         * Get the values names of any loops, and use them as the arguments for this function. At the same time known
         * true and known false expressions are collected.
         */
        ArgDesc<?>[] functionArgs = constructGibbsArgs(funcData, compilationCtx);
        KnownValuesIR knownValues = constructKnownValues(funcData, compilationCtx);

        RandomVariable<?, ?> rv = funcData.sourceRandom;
        String randomVariableName = rv.aliasSet() ? rv.getAlias() : rv.getType().toString() + " " + rv.getId();
        String comment = "Method to perform the inference steps to calculate new values for the samples generated by sample task "
                + funcData.sampleDesc.id + " drawn from " + randomVariableName + ". Inference was performed using "
                + getInferenceType() + ".";

        // Mark that the construction of the inference function is complete.
        compilationCtx.setInInference(false);

        // And place the subtree in a void function.
        return voidFunction(Visibility.PRIVATE, FunctionName.createFunctionName(funcData.sampleDesc.uniqueName),
                functionArgs, result, comment, knownValues);
    }

    private KnownValuesIR constructKnownValues(FuncData funcData, CompilationContext compilationCtx) {
        List<KnownValuesIR.KnownValue> values = new ArrayList<>();
        for(Scope s:funcData.sampleDesc.scopes) {
            switch(s.getScopeType()) {
                case ELSE: {
                    ElseScope elseScope = (ElseScope) s;
                    values.add(new KnownValue(elseScope.ifScope.getGuardTree(compilationCtx), false));
                    break;
                }
                case IF: {
                    IfScope ifScope = (IfScope) s;
                    values.add(new KnownValue(ifScope.getGuardTree(compilationCtx), true));
                    break;
                }
                default:
                    break;
            }
        }

        return KnownValuesIR.constructKnownValues(values);
    }

    /**
     * Method for collecting and ordering all the required arguments to Gibbs inference methods for the stepped ranges.
     *
     * @param funcData       The function data.
     * @param compilationCtx The compilation context.
     * @return
     */
    private ArgDesc<?>[] constructGibbsArgs(FuncData funcData, CompilationContext compilationCtx) {

        VariableDescription<IntVariable> threadIdName = null;
        List<ArgDesc<?>> args = new ArrayList<>();

        int noScopes = funcData.sampleDesc.scopes.size();
        for(int i = 1; i < noScopes; i++) { // First scope will always be global.
            Scope scope = funcData.sampleDesc.scopes.get(i);
            if(scope.getScopeType() == ScopeType.FOR) {
                ForTask f = (ForTask) scope;

                VariableDescription<IntVariable> indexName = f.getIndex().getUniqueVarDesc();
                IRTreeReturn<IntVariable> start = f.getStart().getForwardIR(compilationCtx);
                IRTreeReturn<IntVariable> end = f.getEnd().getForwardIR(compilationCtx);
                if(f.incrementing)
                    args.add(new ArgDesc<>(indexName, start, IRTree.subtractII(end, IRTree.constant(1))));
                else
                    args.add(new ArgDesc<>(indexName, end, start));

                if(!f.isSerial(compilationCtx) && (compilationCtx.target == ExecutionType.MultiThreadCPU)) {
                    threadIdName = VariableNames
                            .threadIdName(VariableNames.calcVarName(f.getIndex().getUniqueVarDesc().name));
                }
            }
        }

        // Pass in the threads random number generator.
        if(threadIdName != null) {
            args.add(new ArgDesc<>(threadIdName));
            args.add(new ArgDesc<>(VariableNames.rngName(0)));
        }

        return args.toArray(new ArgDesc[args.size()]);
    }

    private void processSample(SampleTask<?, ?> s, RandomVariable<?, ?> consumer, ScopeConstructor b,
            Set<TraceHandle> observationTraces, FuncData funcData, CompilationContext compilationCtx) {
        /*
         * Construct the trace from the RV to the sample. TODO if we go to having RVs as part of arrays then we need
         * capture this trace in the Traces object and get it by querying.
         */
        TraceHandle th = getTraceRVToSample(s, consumer);
        ScopeConstructor c = b.addComment(
                "Processing sample task " + s.id() + " of consumer random variable " + consumer.getAlias() + ".")
                .addConstraint(th);

        c.addTree(2, (TreeBuilderInfo info) -> getPerSampleStartIR(funcData, s, info, compilationCtx));

        ScopeConstructor dConsumerAllArgs = c.applyAllDistributedArguments();

        for(TraceHandle h:observationTraces)
            /*
             * This will not depend on distributions as it goes either to a sample value, so can only have put
             * operations, or to an observed value so is not allowed to be a distribution. It does need to have a
             * constraint on the trace though to make sure the trace is valid.
             */
            getInverseIR(h, consumer, funcData, dConsumerAllArgs, compilationCtx);

        c.addTree(2, (TreeBuilderInfo info) -> getPerSampleEndIR(funcData, info, compilationCtx));
    }

    private void processDistributionSample(DistributionSampleTask<?, ?> s, RandomVariable<?, ?> consumer,
            ScopeConstructor b, FuncData funcData, CompilationContext compilationCtx) {
        /*
         * Construct the trace from the RV to the sample. TODO if we go to having RVs as part of arrays then we need
         * capture this trace in the Traces object and get it by querying.
         */
        TraceHandle th = getTraceRVToSample(s, consumer);
        ScopeConstructor c = b.addComment(
                "Processing sample task " + s.id() + " of consumer random variable " + consumer.getAlias() + ".")
                .addConstraint(th);

        c.addTree(2, (TreeBuilderInfo info) -> getPerDistributedSampleStartIR(funcData, s, info, compilationCtx));

        ScopeConstructor dConsumerAllArgs = c.applyDistributedArguments(1).applyDistributedArguments(2);

        VariableDescription<DoubleVariable> reachedSourceName = VariableNames.scopeVarName("reachedSourceProbability",
                VariableType.DoubleVariable);
        dConsumerAllArgs.addTree((TreeBuilderInfo info) -> compilationCtx.addTreeToScope(GlobalScope.scope,
                IRTree.initializeVariable(reachedSourceName, IRTree.constant(0.0),
                        "Declare and zero an accumulator for tracking the reached source probability space.")));

        ScopeConstructor dSourceAllArgs = dConsumerAllArgs.resetProbabilities().applyDistributedArguments(0);
        dSourceAllArgs.addTree((TreeBuilderInfo info) -> compilationCtx.addTreeToScope(GlobalScope.scope,
                IRTree.store(reachedSourceName, IRTree.addDD(IRTree.load(reachedSourceName), info.probability),
                        "Add the probability of this argument configuration.")));

        dConsumerAllArgs.addTree(1, (TreeBuilderInfo info) -> {
            /*
             * Construct the arguments for the consumer random variable. If we could apply the distributions before the
             * sample task is fixed this could be moved further out and run only once. However, as in the future the may
             * be distributions in the sample trace that is not possible, so it is placed here with the expectation that
             * the optimisation phase can move shared values out where appropriate.
             */
            getConsumerRVInputIR(info, consumer, funcData, compilationCtx);
            info.changeSubstitutions(2, compilationCtx);
            getDistributionSampleIR(s, IRTree.load(reachedSourceName), funcData, info, compilationCtx);
        });

        c.addTree(2, (TreeBuilderInfo info) -> getPerDistributedSampleEndIR(funcData, s, info, compilationCtx));
    }

    private TraceHandle getTraceRVToSample(SampleTask<?, ?> s, RandomVariable<?, ?> rv) {
        Trace t = new Trace();
        t.push(new DataflowTaskArgDesc(rv.getParent(), -1));
        t.push(new DataflowTaskArgDesc(s, 0));
        return TraceHandle.getTraceHandle(t);
    }

    protected abstract void backTraceScopeStartIR(FuncData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx);

    protected abstract void backTraceScopeEndIR(FuncData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx);

    protected abstract void finalize(FuncData funcData, CompilationContext compilationCtx);

    protected abstract void addDistributionProbabilities(FuncData funcData, CompilationContext compilationCtx);

    protected abstract String getInferenceType();

    protected abstract Scope getBackTraceScope(FuncData funcData);

    protected abstract void getDistributionSampleIR(DistributionSampleTask<?, ?> s,
            IRTreeReturn<DoubleVariable> sourceProbability, FuncData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx);

    protected abstract void getPerSourceConfigStartIR(FuncData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx);

    protected abstract void getPerSourceConfigEndIR(FuncData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx);

    protected abstract void getPerSampleStartIR(FuncData funcData, SampleTask<?, ?> s, TreeBuilderInfo info,
            CompilationContext compilationCtx);

    protected abstract void getPerSampleEndIR(FuncData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx);

    protected abstract void getPerDistributedSampleStartIR(FuncData funcData, DistributionSampleTask<?, ?> s,
            TreeBuilderInfo info, CompilationContext compilationCtx);

    protected abstract void getPerDistributedSampleEndIR(FuncData funcData, DistributionSampleTask<?, ?> s,
            TreeBuilderInfo info, CompilationContext compilationCtx);

    protected abstract void getConsumerRVInputIR(TreeBuilderInfo info, RandomVariable<?, ?> consumer, FuncData funcData,
            CompilationContext compilationCtx);

    private void removeTargetScopeSubstitutes(FuncData funcData, CompilationContext compilationCtx) {
        int noScopes = funcData.sampleDesc.scopes.size();
        for(int i = 1; i < noScopes; i++) { // First scope will always be global.
            Scope innerScope = funcData.sampleDesc.scopes.get(i);
            compilationCtx.removeScopeSubstitute(innerScope);
        }
    }

    private void addTraces(Map<SampleTask<?, ?>, Set<TraceHandle>> groupedTraces, List<TraceHandle> traces) {
        for(TraceHandle t:traces) {
            SampleTask<?, ?> s = (SampleTask<?, ?>) t.get(0).task;
            Set<TraceHandle> h = groupedTraces.computeIfAbsent(s, k -> new LinkedHashSet<>());
            h.add(t);
        }
    }

    protected abstract void addSampleValueTree(FuncData funcData, CompilationContext compilationCtx);

    private Scope constructTargetScope(FuncData funcData, CompilationContext compilationCtx) {
        Scope scope = GlobalScope.scope;
        int noScopes = funcData.sampleDesc.scopes.size();
        for(int i = 1; i < noScopes; i++) { // First scope will always be global.
            Scope innerScope = funcData.sampleDesc.scopes.get(i);
            compilationCtx.addScopeSubstitute(innerScope, GlobalScope.scope);
        }

        return scope;
    }

    protected abstract void constructFunctionVariablesInternal(CompilationContext compilationCtx, FuncData funcData);

    /**
     * Method to get the inverse intermediate representation tree.
     * 
     * @param traceHandle    The trace to traverse backward over.
     * @param funcData       The function data for this inference process.
     * @param sc             The scope constructor describing the scopes that this inference process might happen in.
     * @param compilationCtx The compilation context for this compilation process.
     */
    private <X extends Variable<X>> void getInverseIR(TraceHandle traceHandle, RandomVariable<?, ?> consumer,
            FuncData funcData, ScopeConstructor sc, CompilationContext compilationCtx) {
        if(TraceArrayRestrictions.restrictionRequired(traceHandle))
            sc = sc.addComment("Check that the value can reach the sample task");
        ScopeConstructor a = sc.addConstraint(traceHandle); // Constraint still added to maintain a predictable
        // structure to the ScopeConstructor.

        @SuppressWarnings("unchecked")
        SampleTask<X, ?> sTask = (SampleTask<X, ?>) traceHandle.get(0).task;

        a.addTree(1, (TreeBuilderInfo info) -> {

            /*
             * Construct the arguments for the consumer random variable. If we could apply the distributions before the
             * sample task is fixed this could be moved further out and run only once. However, as in the future the may
             * be distributions in the sample trace that is not possible, so, it is placed here with the expectation
             * that the optimisation phase can move shared values out where appropriate.
             */
            getConsumerRVInputIR(info, consumer, funcData, compilationCtx);

            info.changeSubstitutions(3, compilationCtx);
            Trace trace = traceHandle.getTrace();

            DataflowTaskArgDesc d = trace.pop();
            ProducingDataflowTask<?> t = d.task;

            Variable<?> v = t.getOutput();
            IRTreeReturn<?> current = v.getForwardIR(compilationCtx);

            while(t.getType() != DFType.SAMPLE) {
                current = t.getInverseIR(d.argPos, current, info.backTraceInfo, compilationCtx);
                d = trace.pop();
                t = d.task;
            }

            if(info.backTraceInfo.noGetValues() != 0) {
                Location start = traceHandle.get(0).task.getLocation();
                Location end = traceHandle.peek().task.getLocation();
                throw new CompilerException("Unaccounted for get in trace " + traceHandle + "\nStarting at line "
                        + start.startLine + " through to line " + end.endLine);
            }

            getObservationToSampleIR(sTask, current, funcData, info, compilationCtx);
        });
    }

    protected abstract void getObservationToSampleIR(SampleTask<?, ?> task, IRTreeReturn<?> current, FuncData funcData,
            TreeBuilderInfo info, CompilationContext compilationCtx);

    protected abstract void allocateGlobalStateInternal(CompilationContext compilationCtx, FuncData funcData);

    protected <V extends Variable<V>> void globalFieldAllocation(VariableDescription<V> fieldName,
            IRTreeReturn<V> localAllocation, FunctionData<A, B, S> funcData, CompilationContext compilationCtx) {
        List<Scope> scopes = funcData.sampleDesc.scopes;
        FunctionUtils.globalFieldAllocation(fieldName, localAllocation, funcData.isSerial,
                scopes.get(scopes.size() - 1), compilationCtx);
    }

    protected <V extends Variable<V>> void createGlobalField(VariableDescription<V> fieldName, IRTreeVoid allocator,
            FunctionData<A, B, S> funcData, CompilationContext compilationCtx) {
        List<Scope> scopes = funcData.sampleDesc.scopes;
        FunctionUtils.createGlobalField(fieldName, allocator, funcData.isSerial, scopes.get(scopes.size() - 1),
                compilationCtx);
    }

    protected <V extends Variable<V>> IRTreeReturn<V> loadGlobalField(VariableDescription<V> varDesc,
            FunctionData<A, B, S> funcData, CompilationContext compilationCtx) {
        List<Scope> scopes = funcData.sampleDesc.scopes;
        return FunctionUtils.loadGlobalField(varDesc, funcData.isSerial, scopes.get(scopes.size() - 1), compilationCtx);
    }
}
