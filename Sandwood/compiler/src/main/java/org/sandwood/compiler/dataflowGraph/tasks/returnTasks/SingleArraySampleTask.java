/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
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

    private final Set<VariableWrapper<IntVariable>> possibleLengths;

    // TODO change this to shape so we can support random variables that generate
    // multidimensional arrays.
    public SingleArraySampleTask(Type<ArrayVariable<A>> baseType, B randomVariable,
            Set<VariableWrapper<IntVariable>> possibleLengths, Location location) {
        super(baseType, randomVariable, location);
        this.possibleLengths = possibleLengths;
    }

    @Override
    public Set<VariableWrapper<IntVariable>> getPossibleLengths() {
        return possibleLengths;
    }

    @Override
    public IRTreeReturn<IntVariable> getMaxLength(CompilationContext compilationCtx) {
        return ((ArrayRandomVariable<A, B>) randomVariable).getMaxLength(compilationCtx);
    }

    @Override
    public IRTreeReturn<IntVariable> getMinLength(CompilationContext compilationCtx) {
        return ((ArrayRandomVariable<A, B>) randomVariable).getMinLength(compilationCtx);
    }
}
