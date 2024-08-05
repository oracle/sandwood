/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.inference.conjugatePrior;

import static org.sandwood.compiler.trees.irTree.IRTree.addDD;
import static org.sandwood.compiler.trees.irTree.IRTree.arrayGet;
import static org.sandwood.compiler.trees.irTree.IRTree.arrayPut;
import static org.sandwood.compiler.trees.irTree.IRTree.constant;
import static org.sandwood.compiler.trees.irTree.IRTree.functionCall;
import static org.sandwood.compiler.trees.irTree.IRTree.initializeVariable;
import static org.sandwood.compiler.trees.irTree.IRTree.load;
import static org.sandwood.compiler.trees.irTree.IRTree.max;
import static org.sandwood.compiler.trees.irTree.IRTree.multiplyDD;
import static org.sandwood.compiler.trees.irTree.IRTree.multiplyDI;
import static org.sandwood.compiler.trees.irTree.IRTree.newArray;
import static org.sandwood.compiler.trees.irTree.IRTree.store;

import java.util.List;
import java.util.Set;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.compilation.inference.InferenceGenerator;
import org.sandwood.compiler.compilation.inference.InferenceGeneratorArray;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.DistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.RandomVariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Dirichlet;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.traces.TraceHandle;
import org.sandwood.compiler.traces.guards.TreeBuilderInfo;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRFor;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class DirichletToCategoricalMultinomial extends
        InferenceGeneratorArray<DoubleVariable, Dirichlet, DirichletToCategoricalMultinomial.DirichletToCategoricalData> {
    protected static class DirichletToCategoricalData
            extends InferenceGeneratorArray.ArrayFunctionData<DoubleVariable, Dirichlet> {
        // Names for the different variables that will be needed to construct for this
        // function.
        final VariableDescription<ArrayVariable<DoubleVariable>> countNameGlobal;

        protected DirichletToCategoricalData(SampleTask<ArrayVariable<DoubleVariable>, Dirichlet> sample,
                CompilationContext compilationCtx) {
            super(sample, false, compilationCtx);
            countNameGlobal = VariableNames.calcVarName(sampleDesc.output, "countGlobal",
                    VariableType.arrayType(VariableType.DoubleVariable));
        }
    }

    private static final VariableDescription<ArrayVariable<DoubleVariable>> countNameLocal = VariableNames
            .calcVarName("countLocal", VariableType.arrayType(VariableType.DoubleVariable), true);
    private static final VariableDescription<IntVariable> arrayLength = VariableNames.calcVarName("arrayLength",
            VariableType.IntVariable, true);
    private static final VariableDescription<IntVariable> loopIndex = VariableNames.calcVarName("loopIndex",
            VariableType.IntVariable, true);

    private DirichletToCategoricalMultinomial() {}

    // The singleton instance of this class that is given out to objects needing one.
    private static final DirichletToCategoricalMultinomial singleton = new DirichletToCategoricalMultinomial();

    public static InferenceGenerator<ArrayVariable<DoubleVariable>, Dirichlet> getGenerator() {
        return singleton;
    }

    /**
     * Method to calculate the sample value once all the observed data has been collected together.
     *
     * @param funcData       The functional data for this inference function.
     * @param target         The array that the result should be written out into.
     * @param compilationCtx The compilation context for the compilation process.
     * @return The intermediate representation tree for the call to construct a sample value.
     */
    @Override
    protected IRTreeVoid calculateSampleValue(DirichletToCategoricalData funcData, CompilationContext compilationCtx) {
        // Construct the arguments.
        IRTreeReturn<ArrayVariable<DoubleVariable>> betaArg = funcData.sourceRandom.beta.getForwardIR(compilationCtx);
        IRTreeReturn<ArrayVariable<DoubleVariable>> count = load(countNameLocal);

        // Construct a tree to construct the sample variable for a given element.
        return functionCall(FunctionType.CONJUGATE_SAMPLE, VariableType.Dirichlet, VariableType.Categorical,
                "Calculate a new sample value and write it into " + funcData.targetLocal + ".", betaArg, count,
                funcData.getTarget());
    }

    /**
     * Construct the Trees to initialize that data structures required for this function.
     *
     * @param compilationCtx The compilation context for the for this compilation process.
     * @param funcData       The function data for this inference function.
     */
    @Override
    protected void constructFunctionVariables(CompilationContext compilationCtx, DirichletToCategoricalData funcData) {
        // Set up a pointer for accessing local space.
        IRTreeReturn<ArrayVariable<DoubleVariable>> globalState = loadGlobalField(funcData.countNameGlobal, funcData,
                compilationCtx);
        IRTreeVoid getLocalState = initializeVariable(countNameLocal, globalState,
                "A local reference to the scratch space.");
        compilationCtx.addTreeToScope(GlobalScope.scope, getLocalState);

        IRTreeReturn<IntVariable> arrayLengthVal = funcData.sourceRandom.beta.scopedLength(null)
                .getForwardIR(compilationCtx);
        IRTreeVoid arrayLengthVar = initializeVariable(arrayLength, arrayLengthVal, "Get the length of the array");
        compilationCtx.addTreeToScope(GlobalScope.scope, arrayLengthVar);

        IRTreeReturn<ArrayVariable<DoubleVariable>> array = load(countNameLocal);
        IRTreeVoid body = arrayPut(array, load(loopIndex), constant(0.0), Tree.NoComment);
        IRFor loop = IRTree.forStmt(body, constant(0), load(arrayLength), constant(1), loopIndex, true,
                "Initialize the array values to 0.");
        compilationCtx.addTreeToScope(GlobalScope.scope, loop);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void getObservationToSampleIR(SampleTask<?, ?> task, IRTreeReturn<?> current,
            DirichletToCategoricalData funcData, TreeBuilderInfo info, CompilationContext compilationCtx) {

        IRTreeReturn<ArrayVariable<DoubleVariable>> count = load(countNameLocal);
        RandomVariableType<?, ?> type = task.randomVariable.getType();
        if(type == VariableType.Categorical) {
            IRTreeReturn<IntVariable> index = (IRTreeReturn<IntVariable>) current;
            IRTreeVoid tree = arrayPut(count, index, addDD(arrayGet(count, index), info.probability),
                    "Increment the sample counter with the value " + "sampled by sample task " + task.id()
                            + " of random variable " + task.randomVariable.getVarDesc());
            compilationCtx.addTreeToScope(task.scope(), tree);
        } else if(type == VariableType.Multinomial) {
            // Save the reference to the sample value for efficient access.
            IRTreeReturn<ArrayVariable<IntVariable>> sampleValue = (IRTreeReturn<ArrayVariable<IntVariable>>) current;
            VariableDescription<ArrayVariable<IntVariable>> sampleValueName = VariableNames.calcVarName("sampleValue",
                    sampleValue.getOutputType(), true);
            compilationCtx.addTreeToScope(GlobalScope.scope,
                    initializeVariable(sampleValueName, sampleValue, Tree.NoComment));

            // Construct a body to scale and increment the value.
            IRTreeReturn<DoubleVariable> scaledSample = multiplyDI(info.probability,
                    arrayGet(load(sampleValueName), load(loopIndex)));
            IRTreeReturn<DoubleVariable> incrementedCount = addDD(arrayGet(load(countNameLocal), load(loopIndex)),
                    scaledSample);
            IRTreeVoid body = arrayPut(load(countNameLocal), load(loopIndex), incrementedCount, Tree.NoComment);

            // Add the body to a for loop
            IRFor loop = IRTree.forStmt(body, constant(0), load(arrayLength), constant(1), loopIndex, true,
                    "Update all the counts");
            compilationCtx.addTreeToScope(GlobalScope.scope, loop);
        }

    }

    @Override
    protected DirichletToCategoricalData getFunctionData(SampleTask<ArrayVariable<DoubleVariable>, Dirichlet> sample,
            CompilationContext compilationCtx) {
        return new DirichletToCategoricalData(sample, compilationCtx);
    }

    @Override
    public boolean canAcceptTraces(SampleTask<ArrayVariable<DoubleVariable>, Dirichlet> sample,
            List<String> suggestions, CompilationContext compilationCtx) {
        // Test if the input values to the source random variable are distributions. If
        // they are this trace cannot be accepted.
        if(sample.randomVariable.isDistribution())
            return false;

        // Check the trace
        for(Set<TraceHandle> ts:compilationCtx.traces.getTracesRVToSampleTask(sample).values()) {
            for(TraceHandle t:ts) {
                int noTasks = t.size();
                for(int i = 1; i < noTasks - 1; i++) {
                    DataflowTaskArgDesc d = t.get(i);
                    DFType type = d.task.getType();
                    if(!(type == DFType.SAMPLE || type == DFType.IF_ASSIGNMENT || (type == DFType.GET && d.argPos != 1)
                            || (type == DFType.PUT && d.argPos != 1)))
                        return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void allocateGlobalState(CompilationContext compilationCtx, DirichletToCategoricalData funcData) {

        // Allocate space for storing the results.

        compilationCtx.pushScope();
        // Because of the reuse of max this needs to be serial. This could be overcome
        // this by taking a copy of the value of max, but as I am not sure that parallel
        // allocation is a beneficial, for now this is serial.
        compilationCtx.pushIsSerial(true);

        VariableDescription<IntVariable> maxName = VariableNames.calcVarName("max", VariableType.IntVariable, true);
        // Set initial value for the array.
        compilationCtx.addTreeToScope(GlobalScope.scope, initializeVariable(maxName, constant(0),
                "Calculate the longest array this random variable could produce and allocate an array large enough to handle this."));
        // Search for a larger value.
        IRTreeReturn<IntVariable> arrayLength = ((ArrayVariable<DoubleVariable>) funcData.sampleDesc.output)
                .scopedLength(null).getForwardIR(compilationCtx);
        IRTreeVoid updateMax = store(maxName, max(load(maxName), arrayLength), Tree.NoComment);
        compilationCtx.addTreeToScope(funcData.sampleDesc.getScope(), updateMax);
        // Allocate and store the largest value
        globalFieldAllocation(funcData.countNameGlobal,
                newArray(load(maxName), VariableType.arrayType(VariableType.DoubleVariable)), funcData, compilationCtx);
        // Get the allocator
        IRTreeVoid allocator = compilationCtx.getOutermostScopeTree();

        compilationCtx.popIsSerial();
        compilationCtx.popScope();

        createGlobalField(funcData.countNameGlobal, allocator, funcData, compilationCtx);
    }

    @Override
    protected void getDistributionSampleIR(DistributionSampleTask<?, ?> s,
            IRTreeReturn<DoubleVariable> sourceProbability, DirichletToCategoricalData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {

        VariableDescription<DoubleVariable> distributionProbability = VariableNames
                .calcVarName("distributionProbability", VariableType.DoubleVariable, true);
        compilationCtx.addTreeToScope(GlobalScope.scope,
                initializeVariable(distributionProbability, multiplyDD(sourceProbability, info.probability),
                        "The probability of reaching the consumer with this set of consumer arguments"));

        IRTreeReturn<ArrayVariable<DoubleVariable>> array = load(countNameLocal);
        IRTreeReturn<DoubleVariable> currentValue = arrayGet(array, load(loopIndex));

        IRTreeReturn<ArrayVariable<DoubleVariable>> probabilityArray = s.getProbabilitiesArray()
                .getForwardIR(compilationCtx);
        IRTreeReturn<DoubleVariable> rvProbability = arrayGet(probabilityArray, load(loopIndex));
        IRTreeReturn<DoubleVariable> mergeValue = addDD(currentValue,
                multiplyDD(rvProbability, load(distributionProbability)));

        IRTreeVoid body = arrayPut(array, load(loopIndex), mergeValue, Tree.NoComment);
        IRFor loop = IRTree.forStmt(body, constant(0), load(arrayLength), constant(1), loopIndex, true,
                "Merge the distribution probabilities into the count");
        compilationCtx.addTreeToScope(GlobalScope.scope, loop);
    }

    @Override
    protected void getConsumerRVInputIR(TreeBuilderInfo info, RandomVariable<?, ?> consumer,
            DirichletToCategoricalData funcData, CompilationContext compilationCtx) {}

    @Override
    protected void getPerSourceConfigStartIR(DirichletToCategoricalData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerSourceConfigEndIR(DirichletToCategoricalData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerSampleStartIR(DirichletToCategoricalData funcData, SampleTask<?, ?> s, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerSampleEndIR(DirichletToCategoricalData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void finalize(DirichletToCategoricalData funcData, CompilationContext compilationCtx) {}

    @Override
    protected Scope getBackTraceScope(DirichletToCategoricalData funcData) {
        return GlobalScope.scope;
    }

    @Override
    protected String getInferenceType() {
        return "a Dirichlet to Categorical conjugate prior";
    }

    @Override
    protected void addDistributionProbabilities(DirichletToCategoricalData funcData,
            CompilationContext compilationCtx) {
        throw new CompilerException(
                "Unable to merge distributions in this inference method. This is a bug in Sandwood.");
    }

    @Override
    protected void backTraceScopeStartIR(DirichletToCategoricalData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void backTraceScopeEndIR(DirichletToCategoricalData funcData, TreeBuilderInfo info,
            CompilationContext compilationCtx) {}

    @Override
    protected void getPerDistributedSampleStartIR(DirichletToCategoricalData funcData, DistributionSampleTask<?, ?> s,
            TreeBuilderInfo info, CompilationContext compilationCtx) {}

    @Override
    protected void getPerDistributedSampleEndIR(DirichletToCategoricalData funcData, DistributionSampleTask<?, ?> s,
            TreeBuilderInfo info, CompilationContext compilationCtx) {}
}
