/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks.constant;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRConstDouble;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class ConstantDoubleTask extends ConstantNumberValueTask<DoubleVariable> {

    public final double v;

    public ConstantDoubleTask(double v, Location location) {
        super(DFType.CONSTANT_DOUBLE, VariableType.DoubleVariable, location);
        this.v = v;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return Double.toString(v);
    }

    @Override
    public IRConstDouble getForwardIRinternal(CompilationContext compilationCtx) {
        return IRTree.constant(v);
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(getType() != other.getType())
            return false;
        ConstantDoubleTask t = (ConstantDoubleTask) other;
        return v == t.v;
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
        return IRTree.constant(v);
    }

    @Override
    public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.constant(v);
    }
}
