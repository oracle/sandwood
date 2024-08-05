/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.NumericRandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class SingleNumericSampleTask<A extends NumberVariable<A>, B extends NumericRandomVariable<A, B>>
        extends SingleSampleTask<A, B> implements NumberProducingDataflowTask<A> {

    public SingleNumericSampleTask(Type<A> baseType, B randomVariable, Location location) {
        super(baseType, randomVariable, location);
    }

    @Override
    public IRTreeReturn<A> getMax(CompilationContext compilationCtx) {
        return ((NumericRandomVariable<A, B>) randomVariable).getMax(compilationCtx);
    }

    @Override
    public IRTreeReturn<A> getMin(CompilationContext compilationCtx) {
        return ((NumericRandomVariable<A, B>) randomVariable).getMin(compilationCtx);
    }
}
