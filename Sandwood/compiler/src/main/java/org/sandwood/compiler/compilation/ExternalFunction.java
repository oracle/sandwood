/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation;

public enum ExternalFunction {
    EXP(true, true),
    FLOOR(true, true),
    GAUSSIAN_CDF(true, true),
    IS_NAN(false, false),
    LOG(true, true),
    SQRT(true, true);

    public final boolean monotonic, increasing;

    private ExternalFunction(boolean monotonic, boolean increasing) {
        this.monotonic = monotonic;
        this.increasing = increasing;
    }
}
