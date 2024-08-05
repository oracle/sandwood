/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.model.variables;

/**
 * Interface to be implemented by Objects that represent an observed array of double in the model when the shape of the
 * observed variable is required for conventional execution of the model.
 */
public interface ObservedBooleanArrayShapeable extends ObservedBooleanArray, ObservedArrayShapeable {}