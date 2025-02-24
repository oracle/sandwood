/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks;

import java.util.Set;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.ArrayProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.VariableWrapper;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.ArrayRandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class SingleArraySampleTask<A extends Variable<A>, B extends ArrayRandomVariable<A, B>>
        extends SingleSampleTask<ArrayVariable<A>, B> implements ArrayProducingDataflowTask<A> {

    // TODO change this to shape so we can support random variables that generate
    // multidimensional arrays.
    public SingleArraySampleTask(Type<ArrayVariable<A>> baseType, B randomVariable, Location location) {
        super(baseType, randomVariable, location);
    }

    @Override
    public Set<VariableWrapper<IntVariable>> getPossibleLengths() {
        return randomVariable.getPossibleLengths();
    }

    @Override
    public IRTreeReturn<IntVariable> getLength(CompilationContext compilationCtx) {
        return randomVariable.getLength(compilationCtx);
    }

    @Override
    public IRTreeReturn<IntVariable> getMaxLength(CompilationContext compilationCtx) {
        return randomVariable.getMaxLength(compilationCtx);
    }

    @Override
    public IRTreeReturn<IntVariable> getMinLength(CompilationContext compilationCtx) {
        return randomVariable.getMinLength(compilationCtx);
    }
}
