/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.model;

/**
 * Enumeration for describing what the retention policy for the model or for a variable should be.
 */
public enum RetentionPolicy {
    /**
     * MAP retains the value when the model was in its most probable state.
     */
    MAP,
    /**
     * Sample retains all the values sampled excluding any thinning and warm up sampling.
     */
    SAMPLE,
    /**
     * None No values will be retained.
     */
    NONE
}
