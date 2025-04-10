/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
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
import org.sandwood.compiler.traces.TraceConstructionDesc;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public abstract class RandomVariableConstructorTask<A extends Variable<A>, B extends RandomVariable<A, B>>
        extends VariableConstructorTask<B> {

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
        return "In the general case this random variable argument cannot be directly calculated from an output sample.";
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

    @Override
    public void constructTrace(TraceConstructionDesc desc) {
        // Skip traces that do not start here.
        if(desc.sink == output)
            super.constructTrace(desc);
    }

    /**
     * A method to where possible construct a tree providing the value of the requested random variable argument based
     * on the value of a sample.
     * 
     * @param current        An IRTree providing the value of the sample.
     * @param argPos         The position of the argument to calculate.
     * @param compilationCtx The compilation context.
     * @return A tree returning the value of the argument.
     */
    public IRTreeReturn<?> getInverseArg(IRTreeReturn<A> current, int argPos, CompilationContext compilationCtx) {
        throw new CompilerException(
                "This random variable does not have an implemented method to invert argument " + argPos + ".");
    }
}
