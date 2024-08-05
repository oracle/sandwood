/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class ReductionReturnNumberTask<A extends NumberVariable<A>> extends ReductionReturnTask<A>
        implements NumberProducingDataflowTask<A> {

    public ReductionReturnNumberTask(A var, Location location) {
        super(var, location);
    }

    @Override
    public IRTreeReturn<A> getMax(CompilationContext compilationCtx) {
        throw new CompilerException("The maximum value of a reduction cannot currently be calculated");
    }

    @Override
    public IRTreeReturn<A> getMin(CompilationContext compilationCtx) {
        throw new CompilerException("The minimum value of a reduction cannot currently be calculated");
    }
}
