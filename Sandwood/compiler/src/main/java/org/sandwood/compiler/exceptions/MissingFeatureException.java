/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.exceptions;

import org.sandwood.common.exceptions.SandwoodException;

public class MissingFeatureException extends SandwoodException {

    public MissingFeatureException(String msg) {
        super("The feature required to do this is not implemented yet. "
                + "If you require this feature please let us know at " + "https://github.com/oracle/sandwood"
                + "\nThe error message was:" + msg);
    }

    private static final long serialVersionUID = 8113777670616407760L;

}
