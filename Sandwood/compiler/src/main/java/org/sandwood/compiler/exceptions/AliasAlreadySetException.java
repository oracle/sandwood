/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.exceptions;

import org.sandwood.compiler.dataflowGraph.variables.Variable;

public class AliasAlreadySetException extends CompilerException {

    private static final long serialVersionUID = 432507788381875824L;

    public AliasAlreadySetException(Variable<?> v, String newAlias) {
        super("The alias of this variable is already set to " + v.getAlias() + " it cannot be set again to "
                + newAlias);
    }
}
