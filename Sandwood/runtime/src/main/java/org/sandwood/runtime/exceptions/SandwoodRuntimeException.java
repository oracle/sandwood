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
 * A class of exception to be thrown when there is an error in the runtime caused by a bug.
 */
public class SandwoodRuntimeException extends SandwoodException {

    private static final long serialVersionUID = 2338926000025959640L;

    /**
     * Constructor for this class of exception.
     * 
     * @param msg A message describing the nature of the bug.
     */
    public SandwoodRuntimeException(String msg) {
        super("An error has occurred in the Sandwood runtime. This is " + "a bug and we request that you report it at "
                + "https://github.com/oracle/sandwood/issues" + "\nThe error message was: " + msg);
    }
}