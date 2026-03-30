/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.inference;

import static org.sandwood.compiler.trees.irTree.IRTree.initializeVariable;
import static org.sandwood.compiler.trees.irTree.IRTree.load;

import java.util.List;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.CompilationContext.CompilationPhase;
import org.sandwood.compiler.compilation.inference.InferenceGeneratorArray.ArrayFunctionData;
import org.sandwood.compiler.compilation.util.SampleDescArray;
import org.sandwood.compiler.compilation.util.TreeUtils;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.traces.guards.TreeBuilderInfo;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public abstract class InferenceGeneratorArray<A extends Variable<A>, B extends RandomVariable<ArrayVariable<A>, B>, FuncData extends ArrayFunctionData<A, B>>
        extends InferenceGeneratorBase<ArrayVariable<A>, B, SampleDescArray<A, B>, FuncData> {

    public abstract static class ArrayFunctionData<A extends Variable<A>, B extends RandomVariable<ArrayVariable<A>, B>>
            extends FunctionData<ArrayVariable<A>, B, SampleDescArray<A, B>> {
        public final VariableDescription<ArrayVariable<A>> targetLocal;

        protected ArrayFunctionData(SampleTask<ArrayVariable<A>, B> sample, boolean sampleUpdated,
                CompilationContext compilationCtx) {
            super(new SampleDescArray<>(sample, compilationCtx.traces), sampleUpdated, compilationCtx);

            targetLocal = VariableNames.calcVarName("targetLocal", sample.getOutputType(), true);
        }

        public IRTreeReturn<ArrayVariable<A>> getTarget() {
            return load(targetLocal);
        }
    }

    // Methods passing through to the subclasses. this provides a point where we can add in code that should be executed
    // for all array values, but not for scalars.
    @Override
    protected void addSampleValueTree(FuncData funcData) {
        IRTreeVoid sampleValue = calculateSampleValue(funcData);
        sampleValue.prefixComment("Calculate the new sample value");
        funcData.sampleDesc.updateSample(sampleValue, funcData.compilationCtx);
    }

    protected abstract IRTreeVoid calculateSampleValue(FuncData funcData);

    @Override
    protected void constructFunctionVariablesInternal(FuncData funcData) {
        // Set up a pointer for accessing local space.
        IRTreeReturn<ArrayVariable<A>> globalState;
        if(funcData.sampleDesc.targetFound()) {
            List<IRTreeReturn<IntVariable>> indexes = TreeUtils.toArgTrees(funcData.sampleDesc.targetIndexes(),
                    funcData.compilationCtx);
            // Dirty hack to keep getIndirect happy. TODO come up with a cleaner solutions.
            // Recasting the variable type so that the get indirect method can be used in
            // the same way it would be used for variables declared inside a loop.
            VariableDescription<ArrayVariable<A>> targetName = VariableNames.altTypeName(
                    funcData.sampleDesc.targetIntermediate().getUniqueVarDesc(), funcData.sampleDesc.output.getType());
            globalState = TreeUtils.getIndirectValue(targetName, indexes);
        } else {
            List<IRTreeReturn<IntVariable>> indexes = TreeUtils
                    .toArgTrees(TreeUtils.getScopeArgs(funcData.sampleDesc.output), funcData.compilationCtx);
            globalState = TreeUtils.getIndirectValue(funcData.sampleDesc.output.getUniqueVarDesc(), indexes);
        }
        IRTreeVoid getLocalState = initializeVariable(funcData.targetLocal, globalState,
                "A reference local to the function for the sample variable.");
        funcData.targetScope.addTree((TreeBuilderInfo info) -> {
            info.compilationCtx.addTreeToScope(GlobalScope.scope, getLocalState);
        });
        constructFunctionVariables(funcData);
    }

    protected abstract void constructFunctionVariables(FuncData funcData);

    @Override
    protected void allocateGlobalStateInternal(FuncData funcData) {
        CompilationPhase phase = funcData.compilationCtx.phase;
        funcData.compilationCtx.phase = CompilationPhase.ALLOCATION;
        allocateGlobalState(funcData);
        funcData.compilationCtx.phase = phase;
    }

    protected abstract void allocateGlobalState(FuncData funcData);
}
