/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.common.execution;

/**
 * An enumeration of different execution models, not all of these will be supported.
 */
public enum ExecutionType {
    /**
     * Execution with a single thread on a CPU
     */
    SingleThreadCPU,
    /**
     * Execution with multiple threads on a CPU
     */
    MultiThreadCPU,
    /**
     * Execution on a GPU
     */
    GPU;

    /**
     * An array listing the currently supported execution models.
     */
    public static final ExecutionType[] supportedTypes = { SingleThreadCPU, MultiThreadCPU };
}
