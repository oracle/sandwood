/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class ArgDesc<A extends Variable<A>> {
    public final VariableDescription<A> varDesc;
    public final IRTreeReturn<IntVariable> min, max;

    public ArgDesc(VariableDescription<A> varDesc, IRTreeReturn<IntVariable> min, IRTreeReturn<IntVariable> max) {
        this.varDesc = varDesc;
        this.min = min;
        this.max = max;
    }

    public ArgDesc(VariableDescription<A> varDesc) {
        this.varDesc = varDesc;
        this.min = null;
        this.max = null;
    }
}
