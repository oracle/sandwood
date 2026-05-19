/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.traces.guards;

import org.sandwood.compiler.dataflowGraph.variables.Variable;

//Class used to enforce that source and target variables are always of the same type.
record VariablePair<A extends Variable<A>>(Variable<A> source, Variable<A> target) {

    @Override
    public String toString() {
        return target.getUniqueVarDesc() + " <= " + source.getUniqueVarDesc();
    }
}
