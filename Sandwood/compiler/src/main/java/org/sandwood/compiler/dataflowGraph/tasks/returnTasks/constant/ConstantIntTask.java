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
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRConstInt;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class ConstantIntTask extends ConstantNumberValueTask<IntVariable> {

    public final int v;

    public ConstantIntTask(int v, Location location) {
        super(DFType.CONSTANT_INT, VariableType.IntVariable, location);
        this.v = v;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return Integer.toString(v);
    }

    @Override
    public IRConstInt getForwardIRinternal(CompilationContext compilationCtx) {
        return IRTree.constant(v);
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(getType() != other.getType())
            return false;
        ConstantIntTask t = (ConstantIntTask) other;
        return v == t.v;
    }

    @Override
    public IRTreeReturn<IntVariable> getMax(CompilationContext compilationCtx) {
        return IRTree.constant(v);
    }

    @Override
    public IRTreeReturn<IntVariable> getMin(CompilationContext compilationCtx) {
        return IRTree.constant(v);
    }
}
