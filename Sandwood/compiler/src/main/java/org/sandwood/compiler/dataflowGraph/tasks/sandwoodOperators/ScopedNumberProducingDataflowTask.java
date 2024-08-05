/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators;

import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;

public abstract class ScopedNumberProducingDataflowTask<A extends NumberVariable<A>>
        extends ScopedProducingDataflowTask<A> implements NumberProducingDataflowTask<A> {

    protected ScopedNumberProducingDataflowTask(DFType dfType, VariableType.Type<A> type, Location location,
            Variable<?>... vars) {
        super(dfType, type, location, vars);
    }
}
