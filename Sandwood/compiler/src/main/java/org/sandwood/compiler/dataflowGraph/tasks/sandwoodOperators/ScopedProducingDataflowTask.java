/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators;

import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.srcTools.sourceToSource.Location;

public abstract class ScopedProducingDataflowTask<A extends Variable<A>> extends ProducingDataflowTaskImplementation<A>
        implements Scope {

    protected ScopedProducingDataflowTask(DFType dfType, VariableType.Type<A> type, Location location,
            Variable<?>... vars) {
        super(dfType, type, location, vars);
    }

    @Override
    public Scope getEnclosingScope() {
        return scope();
    }
}
