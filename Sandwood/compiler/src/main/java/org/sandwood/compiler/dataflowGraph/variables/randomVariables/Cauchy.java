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
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.CauchyTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Cauchy extends RandomVariableImplementation<DoubleVariable, Cauchy>
        implements NumericRandomVariable<DoubleVariable, Cauchy> {

    public final DoubleVariable location;
    public final DoubleVariable scale;

    private Cauchy(DoubleVariable location, DoubleVariable scale, Location sourceLocation) {
        super(new CauchyTask(location, scale, sourceLocation), VariableType.DoubleVariable);
        this.location = location;
        this.scale = scale;
    }

    @Override
    public Cauchy getCurrentInstance() {
        return this;
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
    public IRTreeReturn<DoubleVariable> getSampleTree(DoubleVariable sample, CompilationContext compilationCtx) {
        return IRTree.functionCallReturn(FunctionType.SAMPLE, VariableType.DoubleVariable, getType(),
                location.getForwardIR(compilationCtx), scale.getForwardIR(compilationCtx));
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
        return IRTree.constant(Double.POSITIVE_INFINITY);
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.constant(Double.NEGATIVE_INFINITY);
    }

    public static Cauchy cauchy(double location, double scale) {
        return cauchy(Variable.doubleVariable(location), Variable.doubleVariable(scale));
    }

    public static Cauchy cauchy(DoubleVariable location, double scale) {
        return cauchy(location, Variable.doubleVariable(scale));
    }

    public static Cauchy cauchy(double location, DoubleVariable scale) {
        return cauchy(Variable.doubleVariable(location), scale);
    }

    public static Cauchy cauchy(DoubleVariable location, DoubleVariable scale) {
        return new Cauchy(location, scale, null);
    }

    public static Cauchy cauchy(DoubleVariable location, DoubleVariable scale, Location sourceLocation) {
        return new Cauchy(location, scale, sourceLocation);
    }

    public static Cauchy cauchy(IntVariable location, double scale) {
        return cauchy(location.castToDouble(), Variable.doubleVariable(scale));
    }

    public static Cauchy cauchy(double location, IntVariable scale) {
        return cauchy(Variable.doubleVariable(location), scale.castToDouble());
    }

    public static Cauchy cauchy(IntVariable location, IntVariable scale) {
        return cauchy(location.castToDouble(), scale.castToDouble());
    }

    public static Cauchy cauchy(IntVariable location, IntVariable scale, Location sourceLocation) {
        return cauchy(location.castToDouble(sourceLocation), scale.castToDouble(sourceLocation), sourceLocation);
    }

    public static Cauchy cauchy(IntVariable location, DoubleVariable scale) {
        return cauchy(location.castToDouble(), scale);
    }

    public static Cauchy cauchy(IntVariable location, DoubleVariable scale, Location sourceLocation) {
        return cauchy(location.castToDouble(sourceLocation), scale, sourceLocation);
    }

    public static Cauchy cauchy(DoubleVariable location, IntVariable scale) {
        return cauchy(location, scale.castToDouble());
    }

    public static Cauchy cauchy(DoubleVariable location, IntVariable scale, Location sourceLocation) {
        return cauchy(location, scale.castToDouble(sourceLocation), sourceLocation);
    }
}
