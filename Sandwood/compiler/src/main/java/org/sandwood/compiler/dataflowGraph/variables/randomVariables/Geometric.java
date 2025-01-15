/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.randomVariables;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SingleNumericSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.GeometricTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Geometric extends RandomVariableImplementation<IntVariable, Geometric>
        implements NumericRandomVariable<IntVariable, Geometric> {

    public final DoubleVariable p;

    private Geometric(DoubleVariable p, RandomVariableConstructorTask<IntVariable, Geometric> parent) {
        super(parent, VariableType.IntVariable);
        this.p = p;
    }

    @Override
    public Geometric getCurrentInstance() {
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
    public IRTreeReturn<IntVariable> getSampleTree(IntVariable sample, CompilationContext compilationCtx) {
        return IRTree.functionCallReturn(FunctionType.SAMPLE, VariableType.IntVariable, getType(),
                p.getForwardIR(compilationCtx));
    }

    public static Geometric geometric(double p) {
        return geometric(Variable.doubleVariable(p));
    }

    public static Geometric geometric(DoubleVariable p) {
        return geometric(p, null);
    }

    public static Geometric geometric(DoubleVariable p, Location location) {
        return new Geometric(p, new GeometricTask(p, location));
    }

    public static Geometric geometric(IntVariable p) {
        return geometric(p.castToDouble());
    }

    public static Geometric geometric(IntVariable p, Location location) {
        return geometric(p.castToDouble(location), location);
    }

    @Override
    public IRTreeReturn<IntVariable> getMax(CompilationContext compilationCtx) {
        throw new CompilerException("The maximum value of a Geometric distribution cannot currently be calculated");
    }

    @Override
    public IRTreeReturn<IntVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.constant(0);
    }
}
