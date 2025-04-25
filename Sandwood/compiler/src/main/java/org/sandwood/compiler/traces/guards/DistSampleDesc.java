/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.traces.guards;

import java.util.Collections;
import java.util.Map;

import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;

public class DistSampleDesc<A extends Variable<A>> {
    public final Map<Scope, IntVariable> scopeSubstitutions;
    public final Variable<A> sampleValue;

    DistSampleDesc(Variable<A> sampleValue, Map<Scope, IntVariable> scopeSubstitutions) {
        this.scopeSubstitutions = Collections.unmodifiableMap(scopeSubstitutions);
        this.sampleValue = sampleValue;
    }
}
