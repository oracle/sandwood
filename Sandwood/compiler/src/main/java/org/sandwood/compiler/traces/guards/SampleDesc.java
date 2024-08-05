/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.traces.guards;

import java.util.Collections;
import java.util.Map;

import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ForTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;

class SampleDesc<A extends Variable<A>> {
    public final Map<ForTask, IntVariable> scopeSubstitutions;
    public final Variable<A> sampleValue;

    SampleDesc(Variable<A> sampleValue, Map<ForTask, IntVariable> scopeSubstitutions) {
        this.scopeSubstitutions = Collections.unmodifiableMap(scopeSubstitutions);
        this.sampleValue = sampleValue;
    }
}
