/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.exceptions;

import org.sandwood.compiler.dataflowGraph.variables.Variable;

public class LocationAlreadySetException extends CompilerException {

    private static final long serialVersionUID = 432507788381875824L;

    public LocationAlreadySetException(Variable<?> v) {
        super("The location of variable " + v.getAlias() + " is already set and it cannot be set again.");
    }
}
