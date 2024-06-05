/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.autoDiff;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;

public interface Differentiable {
    DoubleVariable getDifferential(Variable<?> variable, CompilationContext compilationCtx);

    boolean isDifferentiable(Variable<?> variable);
    
    DifferentialInfo getDifferentialInfo(Variable<?> variable);
}
