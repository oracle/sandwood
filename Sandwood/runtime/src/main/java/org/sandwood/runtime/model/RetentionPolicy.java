/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
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
     * Map and Sample retains the value when the model was in its most probable state and all the values sampled
     * excluding any thinning and warm up sampling. If this retention policy is used when saving a model to an object or
     * to a JSON document, only the MAP value will be saved.
     */
    MAP_AND_SAMPLE,
    /**
     * None No values will be retained.
     */
    NONE,
    /**
     * NA Not applicable. This value will be returned if the current retention policy is not applicable because the
     * value is fixed.
     */
    NA
}
