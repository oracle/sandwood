/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.exceptions;

import org.sandwood.common.exceptions.SandwoodException;

/**
 * A class of exception to be thrown when there is an error in the runtime caused by a bug.
 */
public class SandwoodModelStateException extends SandwoodException {

    private static final long serialVersionUID = 2338926000025959640L;

    /**
     * Constructor for this class of exception.
     * 
     * @param msg A message describing the nature of the bug.
     */
    public SandwoodModelStateException(String msg) {
        super("An error has occurred in the Sandwood model state.\n" + msg);
    }
}