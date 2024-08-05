/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.model;

/**
 * Enumeration for selecting inference techniques from. Not all inference techniques are supported by all variable
 * pairings.
 */
public enum InferenceTechnique {
    /**
     * Gibbs sampling
     */
    GIBBS,
    /**
     * Metropolis-Hastings sampling.
     */
    METROPOLIS_HASTINGS
}
