/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables;

import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;

public abstract class ClassVariableDescription<A extends Variable<A>> extends VariableDescription<A> {
    
    ClassVariableDescription(VariableName name, Type<A> type) {
        super(name, type);
    }
}
