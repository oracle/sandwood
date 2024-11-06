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
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SingleNumericSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.UniformTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Uniform extends RandomVariableImplementation<DoubleVariable, Uniform>
        implements NumericRandomVariable<DoubleVariable, Uniform> {

    public final DoubleVariable start;
    public final DoubleVariable end;

    private Uniform(DoubleVariable start, DoubleVariable end,
            RandomVariableConstructorTask<DoubleVariable, Uniform> parent) {
        super(parent, VariableType.DoubleVariable);
        this.start = start;
        this.end = end;
    }

    @Override
    public DoubleVariable sample() {
        return sample((Location) null);
    }

    @Override
    public DoubleVariable sample(Location location) {
        return DoubleVariable
                .doubleVariable(new SingleNumericSampleTask<>(VariableType.DoubleVariable, this, location));
    }

    @Override
    public Uniform getCurrentInstance() {
        return this;
    }

    @Override
    public IRTreeReturn<DoubleVariable> getSampleTree(DoubleVariable sample, CompilationContext compilationCtx) {
        return IRTree.functionCallReturn(FunctionType.SAMPLE, VariableType.DoubleVariable, getType(),
                start.getForwardIR(compilationCtx), end.getForwardIR(compilationCtx));
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
        return end.getMax(compilationCtx);
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
        return start.getMin(compilationCtx);
    }

    public static Uniform uniform(double start, double end) {
        return uniform(Variable.doubleVariable(start), Variable.doubleVariable(end));
    }

    public static Uniform uniform(DoubleVariable start, double end) {
        return uniform(start, Variable.doubleVariable(end));
    }

    public static Uniform uniform(double start, DoubleVariable end) {
        return uniform(Variable.doubleVariable(start), end);
    }

    public static Uniform uniform(DoubleVariable start, DoubleVariable end) {
        return new Uniform(start, end, new UniformTask(start, end, null));
    }

    public static Uniform uniform(DoubleVariable start, DoubleVariable end, Location location) {
        return new Uniform(start, end, new UniformTask(start, end, location));
    }

    public static Uniform uniform(IntVariable start, DoubleVariable end) {
        return uniform(start.castToDouble(), end);
    }

    public static Uniform uniform(IntVariable start, DoubleVariable end, Location location) {
        return uniform(start.castToDouble(location), end, location);
    }

    public static Uniform uniform(DoubleVariable start, IntVariable end) {
        return uniform(start, end.castToDouble());
    }

    public static Uniform uniform(DoubleVariable start, IntVariable end, Location location) {
        return uniform(start, end.castToDouble(location), location);
    }

    public static Uniform uniform(IntVariable start, IntVariable end) {
        return uniform(start.castToDouble(), end.castToDouble());
    }

    public static Uniform uniform(IntVariable start, IntVariable end, Location location) {
        return uniform(start.castToDouble(location), end.castToDouble(location), location);
    }
}
