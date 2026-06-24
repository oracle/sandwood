/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables;

import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;

public final class LocalVariableDescription<A extends Variable<A>> extends VariableDescription<A> {

    public LocalVariableDescription(String name, Type<A> type, boolean comment) {
        this(new VariableName(name, comment), type);
    }

    public LocalVariableDescription(VariableName name, Type<A> type) {
        super(name, type);
    }

    @Override
    public <B extends Variable<B>> LocalVariableDescription<B> alternativeType(Type<B> type) {
        return new LocalVariableDescription<>(name, type);
    }
}
