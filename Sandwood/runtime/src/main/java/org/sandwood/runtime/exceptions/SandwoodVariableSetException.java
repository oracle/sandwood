/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.exceptions;

import org.sandwood.common.exceptions.SandwoodException;

/**
 * An exception to be thrown if an observed model parameter is set when it has already been set.
 * 
 * @author djgoodma
 *
 */
public class SandwoodVariableSetException extends SandwoodException {
    private static final long serialVersionUID = 735740358281801107L;

    /**
     * Constructor for this exception.
     * 
     * @param message The message the exception should return.
     */
    public SandwoodVariableSetException(String message) {
        super(message);
    }
}