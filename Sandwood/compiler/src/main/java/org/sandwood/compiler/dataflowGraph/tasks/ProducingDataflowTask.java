/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.traces.TraceConstructionDesc;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public interface ProducingDataflowTask<A extends Variable<A>> extends DataflowTask<A> {

    Type<A> getOutputType();// TODO replace Type<a> with a generic type so that we can get rid of unchecked
                            // casts.

    void setNonDeterministic();

    boolean deterministic();

    void constructTrace(TraceConstructionDesc desc);

    IRTreeReturn<?> getInverseIR(int argPos, IRTreeReturn<?> taskOutput, BackTraceInfo bacTraceInfo,
            CompilationContext compilationCtx);

    /**
     * Is this task invertible for the provided argument? This method returns null if there are no issues, and a String
     * describing the problem if there is a problem.
     * 
     * @param argPos The position of the argument that is being reconstructed.
     * @return A String describing any issues with inverting this argument. This method returns null if there are no
     *         issues..
     */
    String checkInversionError(int argPos);
}