/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables;

import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;

public class ScratchVariableDescription<A extends Variable<A>> extends ClassVariableDescription<A> {

    public ScratchVariableDescription(String name, Type<A> type, boolean comment) {
        this(new VariableName(name, comment), type);
    }

    public ScratchVariableDescription(VariableName name, Type<A> type) {
        super(name, type);
    }

    @Override
    public <B extends Variable<B>> ScratchVariableDescription<B> alternativeType(Type<B> type) {
        return new ScratchVariableDescription<>(name, type);
    }
}
