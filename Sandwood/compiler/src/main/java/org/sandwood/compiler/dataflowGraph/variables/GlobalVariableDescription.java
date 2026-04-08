/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables;

import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;

public class GlobalVariableDescription<A extends Variable<A>> extends ClassVariableDescription<A> {

    public GlobalVariableDescription(String name, Type<A> type, boolean comment) {
        this(new VariableName(name, comment), type);
    }

    public GlobalVariableDescription(VariableName name, Type<A> type) {
        super(name, type);
    }

    @Override
    public <B extends Variable<B>> GlobalVariableDescription<B> alternativeType(Type<B> type) {
        return new GlobalVariableDescription<>(name, type);
    }
}
