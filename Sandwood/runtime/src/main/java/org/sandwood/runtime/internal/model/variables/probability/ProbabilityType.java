/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.variables.probability;

public enum ProbabilityType {
    /**
     * This value is for variables whose probability will be calculated for every iterations of the Monte Carlo
     * integration.
     */
    UNSKIPPABLE,
    /**
     * This value is for variables that can be skipped in some iterations.
     */
    SKIPPABLE;
}
