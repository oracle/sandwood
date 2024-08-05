/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.common.exceptions;

/**
 * Class of Exceptions thrown by the model and or compiler.
 */
public class SandwoodException extends RuntimeException {

    /**
     * An Exception class to act as the root of all Sandwood Exceptions
     */
    private static final long serialVersionUID = 7505117952260637596L;

    /**
     * An SandwoodException
     * 
     * @param message A message explaining why the exception was thrown.
     */
    public SandwoodException(String message) {
        super(message);
    }
}
