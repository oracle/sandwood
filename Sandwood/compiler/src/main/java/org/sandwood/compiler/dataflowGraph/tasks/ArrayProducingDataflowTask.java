/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks;

import java.util.Set;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.VariableWrapper;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public interface ArrayProducingDataflowTask<A extends Variable<A>> extends ProducingDataflowTask<ArrayVariable<A>> {

    Set<VariableWrapper<IntVariable>> getPossibleLengths();

    IRTreeReturn<IntVariable> getMaxLength(CompilationContext compilationCtx);

    IRTreeReturn<IntVariable> getMinLength(CompilationContext compilationCtx);
}
