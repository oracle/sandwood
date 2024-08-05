/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.exceptions;

import org.sandwood.common.exceptions.SandwoodException;

public class CompilerInputException extends SandwoodException {

    private static final long serialVersionUID = 2338926000025959640L;

    public CompilerInputException(String msg) {
        super("There was a problem with the compiler inputs." + "\nThe error message was: " + msg);
    }
}
