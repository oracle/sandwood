/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.exceptions;

import org.sandwood.common.exceptions.SandwoodException;

public class SandwoodTestException extends SandwoodException {

    public SandwoodTestException(String msg) {
        super("An error has occurred in one of the Sandwood tests. This is "
                + "a bug in the Sandwood test suite, and we request that you "
                + "report it at https://github.com/oracle/sandwood/issues" + "\nThe error message was:" + msg);
    }

    private static final long serialVersionUID = 2069374645205196085L;

}
