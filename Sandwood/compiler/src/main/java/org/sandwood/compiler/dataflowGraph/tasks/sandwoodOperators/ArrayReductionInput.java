/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators;

import java.util.Set;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.ArrayProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.VariableWrapper;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class ArrayReductionInput<A extends Variable<A>> extends ReductionInput<ArrayVariable<A>>
        implements ArrayProducingDataflowTask<A> {

    private final ArrayVariable<A> emptyValue;

    public ArrayReductionInput(IntVariable start, IntVariable end, ArrayVariable<A> emptyValue,
            ArrayVariable<ArrayVariable<A>> a, boolean first, Location location) {
        super(start, end, emptyValue, a, first, location);
        this.emptyValue = emptyValue;
    }

    @Override
    public Set<VariableWrapper<IntVariable>> getPossibleLengths() {
        return emptyValue.getParent().getPossibleLengths();
    }

    @Override
    public IRTreeReturn<IntVariable> getMaxLength(CompilationContext compilationCtx) {
        return emptyValue.getMaxLength(compilationCtx);
    }

    @Override
    public IRTreeReturn<IntVariable> getMinLength(CompilationContext compilationCtx) {
        return emptyValue.getMinLength(compilationCtx);
    }
}
