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
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.StudentTTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class StudentT extends RandomVariableImplementation<DoubleVariable, StudentT>
        implements NumericRandomVariable<DoubleVariable, StudentT> {

    public final DoubleVariable v;

    private StudentT(DoubleVariable v, Location location) {
        super(new StudentTTask(v, location), VariableType.DoubleVariable);
        this.v = v;
    }

    @Override
    public StudentT getCurrentInstance() {
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
                v.getForwardIR(compilationCtx));
    }

    public static StudentT studentT(double v) {
        return studentT(Variable.doubleVariable(v));
    }

    public static StudentT studentT(DoubleVariable v) {
        return new StudentT(v, null);
    }

    public static StudentT studentT(DoubleVariable v, Location location) {
        return new StudentT(v, location);
    }

    public static StudentT studentT(IntVariable v) {
        return studentT(v.castToDouble());
    }

    public static StudentT studentT(IntVariable v, Location location) {
        return studentT(v.castToDouble(location), location);
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
        return IRTree.constant(Double.POSITIVE_INFINITY);
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.constant(Double.NEGATIVE_INFINITY);
    }
}
