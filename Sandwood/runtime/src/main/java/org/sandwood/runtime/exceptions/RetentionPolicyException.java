/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.exceptions;

import org.sandwood.common.exceptions.SandwoodException;

public class RetentionPolicyException extends SandwoodException {

    private static final long serialVersionUID = -5404252602828581707L;

    public RetentionPolicyException(String message) {
        super(message);
    }
}
