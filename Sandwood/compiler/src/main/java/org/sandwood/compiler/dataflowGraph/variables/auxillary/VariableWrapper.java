/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.auxillary;

import org.sandwood.compiler.dataflowGraph.variables.Variable;

/** Wrapper class to map equals to equality on a variable. */
public class VariableWrapper<A extends Variable<A>> {
    public final A value;

    public VariableWrapper(A value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if((obj == null) || (getClass() != obj.getClass()))
            return false;
        VariableWrapper<?> other = (VariableWrapper<?>) obj;
        if(value == null) {
            return other.value == null;
        } else
            return value.equivalent(other.value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}