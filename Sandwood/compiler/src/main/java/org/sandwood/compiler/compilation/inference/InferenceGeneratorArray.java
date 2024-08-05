/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
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
import org.sandwood.compiler.compilation.util.TreeUtils.ArrayDesc;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.trees.Tree;
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

    // Methods passing through to the subclasses. this provides a point where we
    // can add in code that should be executed for all array values, but not for
    // scalars.
    @Override
    protected void addSampleValueTree(FuncData funcData, CompilationContext compilationCtx) {
        IRTreeVoid sampleValue = calculateSampleValue(funcData, compilationCtx);
        sampleValue.prefixComment("Calculate the new sample value");
        funcData.sampleDesc.updateSample(sampleValue, compilationCtx);
    }

    protected abstract IRTreeVoid calculateSampleValue(FuncData funcData, CompilationContext compilationCtx);

    @Override
    protected void constructFunctionVariablesInternal(CompilationContext compilationCtx, FuncData funcData) {
        // Set up a pointer for accessing local space.
        IRTreeReturn<ArrayVariable<A>> globalState;
        if(funcData.sampleDesc.targetFound()) {
            List<IRTreeReturn<IntVariable>> indexes = TreeUtils.toArgTrees(funcData.sampleDesc.targetIndexes(),
                    compilationCtx);
            // Dirty hack to keep getIndirect happy. TODO come up with a cleaner solutions.
            // Recasting the variable type so that the get indirect method can be used in
            // the same way it would be used for variables declared inside a loop.
            VariableDescription<ArrayVariable<A>> targetName = VariableNames.altTypeName(
                    funcData.sampleDesc.targetIntermediate().getUniqueVarDesc(), funcData.sampleDesc.output.getType());
            globalState = TreeUtils.getIndirectValue(targetName, indexes);
        } else {
            List<IRTreeReturn<IntVariable>> indexes = TreeUtils
                    .toArgTrees(TreeUtils.getScopeArgs(funcData.sampleDesc.output), compilationCtx);
            globalState = TreeUtils.getIndirectValue(funcData.sampleDesc.output.getUniqueVarDesc(), indexes);
        }
        IRTreeVoid getLocalState = initializeVariable(funcData.targetLocal, globalState,
                "A reference local to the function for the sample variable.");
        compilationCtx.addTreeToScope(GlobalScope.scope, getLocalState);
        constructFunctionVariables(compilationCtx, funcData);
    }

    protected abstract void constructFunctionVariables(CompilationContext compilationCtx, FuncData funcData);

    @Override
    protected void allocateGlobalStateInternal(CompilationContext compilationCtx, FuncData funcData) {
        if(!funcData.sampleDesc.output.isSample()) {
            if(!funcData.sampleDesc.selectTarget()) {
                // Allocate space for storing the .
                ArrayDesc<A> arrayDesc = (ArrayDesc<A>) TreeUtils.getArrayDescription(funcData.sampleDesc.output);
                VariableDescription<ArrayVariable<A>> intermediateName = funcData.sampleDesc.output.getUniqueVarDesc();
                IRTreeVoid allocator = TreeUtils.allocate(intermediateName, arrayDesc, compilationCtx);
                compilationCtx.addConstructedClassField(intermediateName, allocator, true, Tree.NoComment);
            }
        }
        CompilationPhase phase = compilationCtx.phase;
        compilationCtx.phase = CompilationPhase.ALLOCATION;
        allocateGlobalState(compilationCtx, funcData);
        compilationCtx.phase = phase;
    }

    protected abstract void allocateGlobalState(CompilationContext compilationCtx, FuncData funcData);
}
