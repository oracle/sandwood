/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.exceptions;

import org.sandwood.compiler.dataflowGraph.tasks.nonReturnTasks.ObserveVariableTask;

public class ConstraintAlreadySetException extends SandwoodModelException {

    public ConstraintAlreadySetException(ObserveVariableTask<?> observation) {
        super("The value of " + observation.target.getVarDesc() + " has already been constrained to the value of "
                + observation.source.getVarDesc(), observation);
    }

    private static final long serialVersionUID = 1330941559219641395L;
}
