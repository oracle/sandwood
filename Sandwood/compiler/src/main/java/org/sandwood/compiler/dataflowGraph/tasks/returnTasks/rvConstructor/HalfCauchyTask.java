/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor;

import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.HalfCauchy;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;

public class HalfCauchyTask extends RandomVariableConstructorTask<DoubleVariable, HalfCauchy> {

    public HalfCauchyTask(DoubleVariable location, DoubleVariable scale, Location sourceLocation) {
        super(DFType.HALF_CAUCHY, VariableType.HalfCauchy, sourceLocation, location, scale);
    }
}