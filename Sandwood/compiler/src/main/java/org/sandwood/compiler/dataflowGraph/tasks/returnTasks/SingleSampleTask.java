/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class SingleSampleTask<A extends Variable<A>, B extends RandomVariable<A, B>> extends SampleTask<A, B> {

    public final RandomVariable<A, B> randomVariable;
    private final Type<A> baseType;

    public SingleSampleTask(Type<A> baseType, B randomVariable, Location location) {
        super(randomVariable, location);
        this.randomVariable = randomVariable;
        this.baseType = baseType;
    }

    @Override
    public Type<A> getBaseType() {
        return baseType;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return randomVariable.getExpression(compressSandwoodCode) + ".sample()";
    }

    @Override
    public IRTreeReturn<?> getInverseIRInternal(int argPos, IRTreeReturn<A> taskOutput, BackTraceInfo backTraceInfo,
            CompilationContext compilationCtx) {
        throw new CompilerException(checkInversionError(argPos));
    }

    @Override
    public String checkInversionError(int argPos) {
        return "Currently, there should not be any traces that pass through random variables.";
    }

    @Override
    protected IRTreeReturn<A> getForwardIRinternal(CompilationContext compilationCtx) {
        return randomVariable.getSampleTree(getOutput(), compilationCtx);
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        return other.id() == this.id();
    }

    @Override
    public boolean isDistribution() {
        return false;
    }

    @Override
    public void setIsDistribution() {}
}
