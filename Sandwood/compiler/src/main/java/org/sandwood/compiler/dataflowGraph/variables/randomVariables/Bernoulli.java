/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.randomVariables;

import static org.sandwood.compiler.trees.irTree.IRTree.constant;
import static org.sandwood.compiler.trees.irTree.IRTree.eq;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.DistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SingleSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.BernoulliTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Bernoulli extends DistributableRandomVariable<BooleanVariable, Bernoulli> {

    public final DoubleVariable p;

    private Bernoulli(DoubleVariable p, RandomVariableConstructorTask<BooleanVariable, Bernoulli> parent) {
        super(parent, VariableType.BooleanVariable);
        this.p = p;
    }

    @Override
    public Bernoulli getCurrentInstance() {
        return this;
    }

    @Override
    public BooleanVariable sample() {
        return sample((Location) null);
    }

    @Override
    public BooleanVariable sample(Location location) {
        return BooleanVariable.booleanVariable(new SingleSampleTask<>(VariableType.BooleanVariable, this, location));
    }

    @Override
    public BooleanVariable sampleDistribution() {
        return sampleDistribution(null);
    }

    @Override
    public BooleanVariable sampleDistribution(Location location) {
        return BooleanVariable
                .booleanVariable(new DistributionSampleTask<>(VariableType.BooleanVariable, this, location));
    }

    @Override
    public IRTreeReturn<BooleanVariable> getSampleTree(BooleanVariable sample, CompilationContext compilationCtx) {
        return IRTree.functionCallReturn(FunctionType.SAMPLE, VariableType.BooleanVariable, getType(),
                p.getForwardIR(compilationCtx));
    }

    @Override
    public IntVariable getNoStates() {
        return Variable.intVariable(2);
    }

    public static Bernoulli bernoulli(double p) {
        return bernoulli(Variable.doubleVariable(p));
    }

    public static Bernoulli bernoulli(DoubleVariable p) {
        return new Bernoulli(p, new BernoulliTask(p, null));
    }

    public static Bernoulli bernoulli(DoubleVariable p, Location location) {
        return new Bernoulli(p, new BernoulliTask(p, location));
    }

    public static Bernoulli bernoulli(IntVariable p) {
        return bernoulli(p.castToDouble());
    }

    public static Bernoulli bernoulli(IntVariable p, Location location) {
        return bernoulli(p.castToDouble(location), location);
    }

    @Override
    public IRTreeReturn<IntVariable> getMaxNoStates(CompilationContext compilationCtx) {
        return constant(2);
    }

    @Override
    public IRTreeReturn<BooleanVariable> getStateValue(IRTreeReturn<IntVariable> state) {
        return eq(state, constant(1));
    }
}
