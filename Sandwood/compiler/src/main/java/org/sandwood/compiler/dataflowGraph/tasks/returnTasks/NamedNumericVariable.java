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
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class NamedNumericVariable<A extends NumberVariable<A>> extends NamedVariable<A>
        implements NumberProducingDataflowTask<A> {

    public NamedNumericVariable(VariableDescription<A> varDesc) {
        super(varDesc);
    }

    @Override
    public IRTreeReturn<A> getMax(CompilationContext compilationCtx) {
        throw new CompilerException("Unable to calculate the "
                + "maximum value of a named variable. Reaching this point " + "is a bug in the compiler.");
    }

    @Override
    public IRTreeReturn<A> getMin(CompilationContext compilationCtx) {
        throw new CompilerException("Unable to calculate the "
                + "minimum value of a named variable. Reaching this point " + "is a bug in the compiler.");
    }
}
