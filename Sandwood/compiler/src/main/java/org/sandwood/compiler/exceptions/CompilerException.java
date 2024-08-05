/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.exceptions;

import org.sandwood.common.exceptions.SandwoodException;

public class CompilerException extends SandwoodException {

    private static final long serialVersionUID = 2338926000025959640L;

    public CompilerException(String msg) {
        super("An error has occurred in the Sandwood compiler. This is " + "a bug and we request that you report it at "
                + "https://github.com/oracle/sandwood/issues" + "\nThe error message was: " + msg);
    }
}
