/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.inference;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.CompilationContext.CompilationPhase;
import org.sandwood.compiler.compilation.util.SampleDescScalar;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.ScalarVariable;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public abstract class InferenceGeneratorScalar<A extends ScalarVariable<A>, B extends RandomVariable<A, B>, FuncData extends InferenceGeneratorBase.FunctionData<A, B, SampleDescScalar<A, B>>>
        extends InferenceGeneratorBase<A, B, SampleDescScalar<A, B>, FuncData> {

    public abstract static class ScalarFunctionData<A extends ScalarVariable<A>, B extends RandomVariable<A, B>>
            extends FunctionData<A, B, SampleDescScalar<A, B>> {

        protected ScalarFunctionData(SampleTask<A, B> sample, boolean sampleUpdated,
                CompilationContext compilationCtx) {
            super(new SampleDescScalar<>(sample, compilationCtx.traces), sampleUpdated, compilationCtx);
        }
    }

    @Override
    protected void addSampleValueTree(FuncData funcData) {
        IRTreeReturn<A> sampleValue = calculateSampleValue(funcData);
        funcData.sampleDesc.updateSample(sampleValue, funcData.compilationCtx);
    }

    protected abstract IRTreeReturn<A> calculateSampleValue(FuncData funcData);

    // Methods passing through to the subclasses. this provides a point where we can add in code that should be executed
    // for all scalar values, but not for arrays.
    @Override
    protected void allocateGlobalStateInternal(FuncData funcData) {
        CompilationPhase phase = funcData.compilationCtx.phase;
        funcData.compilationCtx.phase = CompilationPhase.ALLOCATION;
        allocateGlobalState(funcData);
        funcData.compilationCtx.phase = phase;
    }

    protected abstract void allocateGlobalState(FuncData funcData);

    @Override
    protected void constructFunctionVariablesInternal(FuncData funcData) {
        constructFunctionVariables(funcData);
    }

    protected abstract void constructFunctionVariables(FuncData funcData);
}
