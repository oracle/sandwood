/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.traces.guards;

import org.sandwood.compiler.dataflowGraph.variables.Variable;

//Class used to enforce that source and target variables are always of the same type.
//TODO This feels a bit like a hack, and should really be replaced with a wrapped map where
//the put and get methods enforce the typing.
class VariablePair<A extends Variable<A>> {
    final Variable<A> source, target;

    VariablePair(Variable<A> source, Variable<A> target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public String toString() {
        return target.getUniqueVarDesc() + " <= " + source.getUniqueVarDesc();
    }
}
