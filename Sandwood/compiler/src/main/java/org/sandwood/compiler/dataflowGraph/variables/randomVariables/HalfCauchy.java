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
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.HalfCauchyTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRRVFunctionCallReturn;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class HalfCauchy extends RandomVariableImplementation<DoubleVariable, HalfCauchy>
        implements NumericRandomVariable<DoubleVariable, HalfCauchy> {

    public final DoubleVariable location;
    public final DoubleVariable scale;

    private HalfCauchy(DoubleVariable location, DoubleVariable scale, Location sourceLocation) {
        super(new HalfCauchyTask(location, scale, sourceLocation), VariableType.DoubleVariable);
        this.location = location;
        this.scale = scale;
    }

    @Override
    public HalfCauchy getCurrentInstance() {
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
    public IRRVFunctionCallReturn<DoubleVariable> getSampleTree(DoubleVariable sample,
            CompilationContext compilationCtx) {
        return IRTree.functionCallReturn(FunctionType.SAMPLE, VariableType.DoubleVariable, getType(),
                location.getForwardIR(compilationCtx), scale.getForwardIR(compilationCtx));
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
        return IRTree.constant(Double.POSITIVE_INFINITY);
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.constant(0.0);
    }

    public static HalfCauchy halfCauchy(double location, double scale) {
        return halfCauchy(Variable.doubleVariable(location), Variable.doubleVariable(scale));
    }

    public static HalfCauchy halfCauchy(DoubleVariable location, double scale) {
        return halfCauchy(location, Variable.doubleVariable(scale));
    }

    public static HalfCauchy halfCauchy(double location, DoubleVariable scale) {
        return halfCauchy(Variable.doubleVariable(location), scale);
    }

    public static HalfCauchy halfCauchy(DoubleVariable location, DoubleVariable scale) {
        return new HalfCauchy(location, scale, null);
    }

    public static HalfCauchy halfCauchy(DoubleVariable location, DoubleVariable scale, Location sourceLocation) {
        return new HalfCauchy(location, scale, sourceLocation);
    }

    public static HalfCauchy halfCauchy(IntVariable location, double scale) {
        return halfCauchy(location.castToDouble(), Variable.doubleVariable(scale));
    }

    public static HalfCauchy halfCauchy(double location, IntVariable scale) {
        return halfCauchy(Variable.doubleVariable(location), scale.castToDouble());
    }

    public static HalfCauchy halfCauchy(IntVariable location, IntVariable scale) {
        return halfCauchy(location.castToDouble(), scale.castToDouble());
    }

    public static HalfCauchy halfCauchy(IntVariable location, IntVariable scale, Location sourceLocation) {
        return halfCauchy(location.castToDouble(sourceLocation), scale.castToDouble(sourceLocation), sourceLocation);
    }

    public static HalfCauchy halfCauchy(IntVariable location, DoubleVariable scale) {
        return halfCauchy(location.castToDouble(), scale);
    }

    public static HalfCauchy halfCauchy(IntVariable location, DoubleVariable scale, Location sourceLocation) {
        return halfCauchy(location.castToDouble(sourceLocation), scale, sourceLocation);
    }

    public static HalfCauchy halfCauchy(DoubleVariable location, IntVariable scale) {
        return halfCauchy(location, scale.castToDouble());
    }

    public static HalfCauchy halfCauchy(DoubleVariable location, IntVariable scale, Location sourceLocation) {
        return halfCauchy(location, scale.castToDouble(sourceLocation), sourceLocation);
    }
}
