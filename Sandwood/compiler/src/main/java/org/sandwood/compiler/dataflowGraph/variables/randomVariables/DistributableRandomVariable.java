/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.randomVariables;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public abstract class DistributableRandomVariable<A extends Variable<A>, B extends RandomVariable<A, B>>
        extends RandomVariableImplementation<A, B> {

    protected DistributableRandomVariable(RandomVariableConstructorTask<A, B> parent, VariableType.Type<A> outputType) {
        super(parent, outputType);
    }

    public abstract A sampleDistribution();

    public abstract A sampleDistribution(Location location);

    public abstract IntVariable getNoStates();

    public abstract IRTreeReturn<IntVariable> getMaxNoStates(CompilationContext compilationCtx);

    public abstract IRTreeReturn<A> getStateValue(IRTreeReturn<IntVariable> state);
}
