/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation;

public enum ExternalFunction {
    COS(false, false),
    EXP(true, true),
    FLOOR(true, true),
    GAUSSIAN_CDF(true, true),
    IS_NAN(false, false),
    LOG(true, true),
    POW(false, false),
    SIN(false, false),
    SQRT(true, true);

    public final boolean monotonic, increasing;

    private ExternalFunction(boolean monotonic, boolean increasing) {
        this.monotonic = monotonic;
        this.increasing = increasing;
    }
}
