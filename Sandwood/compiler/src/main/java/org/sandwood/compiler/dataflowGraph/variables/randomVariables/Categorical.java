/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.randomVariables;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.NumericDistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SingleNumericSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.CategoricalTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Categorical extends NumericDistributableRandomVariable<IntVariable, Categorical> {

    public final ArrayVariable<DoubleVariable> elementWeights;

    private Categorical(ArrayVariable<DoubleVariable> elementWeights,
            RandomVariableConstructorTask<IntVariable, Categorical> parent) {
        super(parent, VariableType.IntVariable);
        this.elementWeights = elementWeights.getCurrentInstance();
    }

    @Override
    public Categorical getCurrentInstance() {
        return this;
    }

    @Override
    public IntVariable sample() {
        return sample((Location) null);
    }

    @Override
    public IntVariable sample(Location location) {
        return IntVariable.intVariable(new SingleNumericSampleTask<>(VariableType.IntVariable, this, location));
    }

    @Override
    public IntVariable sampleDistribution() {
        return sampleDistribution(null);
    }

    @Override
    public IntVariable sampleDistribution(Location location) {
        return IntVariable.intVariable(new NumericDistributionSampleTask<>(VariableType.IntVariable, this, location));
    }

    @Override
    public IRTreeReturn<IntVariable> getSampleTree(IntVariable sample, CompilationContext compilationCtx) {
        return IRTree.functionCallReturn(FunctionType.SAMPLE, VariableType.IntVariable, getType(),
                elementWeights.getForwardIR(compilationCtx));
    }

    @Override
    public IntVariable getNoStates() {
        return elementWeights.scopedLength(getParent().getLocation());
    }

    public static Categorical categorical(ArrayVariable<DoubleVariable> elementWeights) {
        return new Categorical(elementWeights, new CategoricalTask(elementWeights, null));
    }

    public static Categorical categorical(ArrayVariable<DoubleVariable> elementWeights, Location location) {
        return new Categorical(elementWeights, new CategoricalTask(elementWeights, location));
    }

    @Override
    public IRTreeReturn<IntVariable> getMax(CompilationContext compilationCtx) {
        return IRTree.subtractII(elementWeights.getMaxLength(compilationCtx), IRTree.constant(1));
    }

    @Override
    public IRTreeReturn<IntVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.constant(0);
    }

    @Override
    public IRTreeReturn<IntVariable> getMaxNoStates(CompilationContext compilationCtx) {
        return elementWeights.getMaxLength(compilationCtx);
    }

    @Override
    public IRTreeReturn<IntVariable> getStateValue(IRTreeReturn<IntVariable> state) {
        return state;
    }
}
