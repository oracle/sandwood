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
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.DistributableRandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class DistributionSampleTask<A extends Variable<A>, B extends DistributableRandomVariable<A, B>>
        extends SampleTask<A, B> {

    public final DistributableRandomVariable<A, B> randomVariable;
    private final Type<A> baseType;
    private ArrayVariable<DoubleVariable> probabilities;

    public DistributionSampleTask(Type<A> baseType, B randomVariable, Location location) {
        super(randomVariable, location);
        this.baseType = baseType;
        this.randomVariable = randomVariable;
    }

    @Override
    public Type<A> getBaseType() {
        return baseType;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return randomVariable.getExpression(compressSandwoodCode) + ".sampleDistribution()";
    }

    @Override
    protected IRTreeReturn<A> getForwardIRinternal(CompilationContext compilationCtx) {
        return randomVariable.getSampleTree(getOutput(), compilationCtx);
    }

    @Override
    protected IRTreeReturn<?> getInverseIRInternal(int argPos, IRTreeReturn<A> taskOutput, BackTraceInfo backTraceInfo,
            CompilationContext compilationCtx) {
        throw new CompilerException(checkInversionError(argPos));
    }

    @Override
    public String checkInversionError(int argPos) {
        return "Currently, there should not be any traces that pass through random variables.";
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        return other.id() == this.id();
    }

    @Override
    public boolean isDistribution() {
        return true;
    }

    @Override
    public void setIsDistribution() {}

    public ArrayVariable<DoubleVariable> getProbabilitiesArray() {
        return probabilities;
    }

    public void addProbabilityDistribution(ArrayVariable<DoubleVariable> probabilities) {
        this.probabilities = probabilities;
    }
}
