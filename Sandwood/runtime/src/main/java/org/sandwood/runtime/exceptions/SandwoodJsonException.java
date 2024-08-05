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
 * Class of exception thrown when a problem is discovered saving a model to JSON, or loading a model from JSON.
 * 
 * @author djgoodma
 *
 */
public class SandwoodJsonException extends SandwoodException {
    private static final long serialVersionUID = -1030622621137146851L;

    /**
     * Constructor for JSON exception.
     * 
     * @param message The message the exception should take.
     */
    public SandwoodJsonException(String message) {
        super(message);
    }
}
