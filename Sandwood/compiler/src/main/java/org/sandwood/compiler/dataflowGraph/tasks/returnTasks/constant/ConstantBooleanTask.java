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
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRConstBoolean;
import org.sandwood.compiler.trees.irTree.IRTree;

public class ConstantBooleanTask extends ConstantValueTask<BooleanVariable> {

    private final boolean b;

    public ConstantBooleanTask(boolean b, Location location) {
        super(DFType.CONSTANT_BOOLEAN, VariableType.BooleanVariable, location);
        this.b = b;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return Boolean.toString(b);
    }

    @Override
    public IRConstBoolean getForwardIRinternal(CompilationContext compilationCtx) {
        return IRTree.constant(b);
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(getType() != other.getType())
            return false;
        ConstantBooleanTask t = (ConstantBooleanTask) other;
        return b == t.b;
    }
}
