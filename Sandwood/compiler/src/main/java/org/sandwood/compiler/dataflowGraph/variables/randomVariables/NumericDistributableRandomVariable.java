/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.randomVariables;

import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;

public abstract class NumericDistributableRandomVariable<A extends NumberVariable<A>, B extends NumericDistributableRandomVariable<A, B>>
        extends DistributableRandomVariable<A, B> implements NumericRandomVariable<A, B> {

    protected NumericDistributableRandomVariable(RandomVariableConstructorTask<A, B> parent, Type<A> outputType) {
        super(parent, outputType);
    }
}
