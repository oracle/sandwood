/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor;

import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.srcTools.sourceToSource.Location;

public abstract class VariableConstructorTask<A extends Variable<A>> extends ProducingDataflowTaskImplementation<A> {

    public VariableConstructorTask(DFType dfType, VariableType.Type<A> outputType, Location location,
            Variable<?>... vars) {
        super(dfType, outputType, location, vars);
    }

    @Override
    public final String getSandwoodExpression(boolean compressSandwoodCode) {
        StringBuilder sb = new StringBuilder();
        sb.append("new " + getType().getDescription() + "(");

        boolean first = true;
        for(Variable<?> v:getInputs()) {
            if(first)
                first = false;
            else
                sb.append(", ");
            sb.append(v.getExpression(compressSandwoodCode));
        }
        sb.append(")");
        return sb.toString();
    }
}
