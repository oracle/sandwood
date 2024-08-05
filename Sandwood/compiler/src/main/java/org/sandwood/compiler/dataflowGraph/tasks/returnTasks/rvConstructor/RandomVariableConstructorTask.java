/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.RandomVariableType;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public abstract class RandomVariableConstructorTask<A extends Variable<A>, B extends RandomVariable<A, B>> extends VariableConstructorTask<B> {

    public RandomVariableConstructorTask(DFType vType, RandomVariableType<A, B> outputType, Location location,
            Variable<?>... vars) {
        super(vType, outputType, location, vars);
    }

    @Override
    public RandomVariableType<A, B> getOutputType() {
        return (RandomVariableType<A, B>) super.getOutputType();
    }
    
    @Override
    protected IRTreeReturn<B> getForwardIRinternal(CompilationContext compilationCtx) {
        throw new CompilerException("Method should never be called on a random variable constructor,"
                + " only on the values contained within the random variable.");
    }

    @Override
    public IRTreeReturn<?> getInverseIRInternal(int argPos, IRTreeReturn<B> taskOutput, BackTraceInfo backTraceInfo,
            CompilationContext compilationCtx) {
        throw new CompilerException(checkInversionError(argPos));
    }

    @Override
    public String checkInversionError(int argPos) {
        return "The only operation on a random variable should be sampling, which should terminate "
                + "a trace, so there should not be a trace from here.";
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(getType() != other.getType())
            return false;
        int size = getInputsCount();
        for(int i = 0; i < size; i++)
            if(getInput(i).equivalent(other.getInput(i)))
                return false;
        return true;
    }
}
