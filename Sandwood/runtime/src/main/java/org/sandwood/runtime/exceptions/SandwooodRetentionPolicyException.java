/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.exceptions;

import org.sandwood.common.exceptions.SandwoodException;

/**
 * An exception class that is thrown if a user tries to set the model retention policy to a non valid type. Typically
 * this would be if they tried to set the policy to NA which is only returned in the cases that a policy is not
 * currently in effect because of the configuration of the model.
 */
public class SandwooodRetentionPolicyException extends SandwoodException {

    private static final long serialVersionUID = -5404252602828581707L;

    /**
     * Constructor taking a message to explain why the exception was created.
     * 
     * @param message A message to explain why this exception was created.
     */
    public SandwooodRetentionPolicyException(String message) {
        super(message);
    }
}
