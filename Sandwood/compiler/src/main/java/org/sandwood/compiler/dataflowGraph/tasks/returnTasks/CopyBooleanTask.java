/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

/**
 * Task to copy another variable. As the models are single assignment this is just a proxy to the other variable.
 *
 * @param <A>
 */
public class CopyBooleanTask extends ProducingDataflowTaskImplementation<BooleanVariable> {

    public final BooleanVariable input;

    public CopyBooleanTask(BooleanVariable input) {
        this(input, null);
    }

    public CopyBooleanTask(BooleanVariable input, Location location) {
        super(DFType.COPY, input.getType(), location, input);
        this.input = input;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return input.getParent().getSandwoodExpression(compressSandwoodCode);
    }

    @Override
    protected IRTreeReturn<BooleanVariable> getForwardIRinternal(CompilationContext compilationCtx) {
        return input.getForwardIR(compilationCtx);
    }

    @Override
    protected IRTreeReturn<BooleanVariable> getInverseIRInternal(int argPos, IRTreeReturn<BooleanVariable> taskOutput,
            BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        return taskOutput;
    }

    @Override
    public String checkInversionError(int argPos) {
        return null;
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        return input.getParent().equivalent(other);
    }
}