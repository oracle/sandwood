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
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.TruncatedGaussian;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;

public class TruncatedGaussianTask extends RandomVariableConstructorTask<DoubleVariable, TruncatedGaussian> {

    public TruncatedGaussianTask(DoubleVariable mean, DoubleVariable variance, DoubleVariable lower,
            DoubleVariable upper, Location location) {
        super(DFType.TRUNCATED_GAUSSIAN, VariableType.TruncatedGaussian, location, mean, variance, lower, upper);
    }
}
