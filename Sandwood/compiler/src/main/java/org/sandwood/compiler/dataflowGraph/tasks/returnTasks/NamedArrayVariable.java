/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks;

import java.util.HashSet;
import java.util.Set;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.ArrayProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.VariableWrapper;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class NamedArrayVariable<A extends Variable<A>> extends NamedVariable<ArrayVariable<A>>
        implements ArrayProducingDataflowTask<A> {

    public NamedArrayVariable(VariableDescription<ArrayVariable<A>> varDesc) {
        super(varDesc);
    }

    @Override
    public Set<VariableWrapper<IntVariable>> getPossibleLengths() {
        return new HashSet<>();
    }

    @Override
    public IRTreeReturn<IntVariable> getMaxLength(CompilationContext compilationCtx) {
        throw new CompilerException("Named variables are constructed out of context, so "
                + "cannot have this operation performed on them. Attempts to call this are a bug in "
                + "the compiler.");
    }

    @Override
    public IRTreeReturn<IntVariable> getMinLength(CompilationContext compilationCtx) {
        throw new CompilerException("Named variables are constructed out of context, so "
                + "cannot have this operation performed on them. Attempts to call this are a bug in "
                + "the compiler.");
    }
}
