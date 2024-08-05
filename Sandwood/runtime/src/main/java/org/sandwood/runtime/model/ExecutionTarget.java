/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.model;

import org.sandwood.common.execution.ExecutionType;
import org.sandwood.runtime.internal.model.execution.ForkJoin;
import org.sandwood.runtime.internal.model.execution.GPU;
import org.sandwood.runtime.internal.model.execution.SingleThread;

/**
 * A class describing the execution techniques used to set the hardware/platform the model inference should target.
 */
public abstract class ExecutionTarget {

    /**
     * Single threaded execution
     */
    public static final SingleThread singleThread = SingleThread.getInstance();
    /**
     * Parallel execution using the Java Fork-Join framework.
     */
    public static final ForkJoin forkJoin = ForkJoin.getInstance();
    /**
     * Execution on a GPU if present.
     */
    public static final GPU gpu = GPU.getInstance();

    /**
     * The name of this execution model.
     */
    private final String name;

    /**
     * The execution model this target backend implements.
     */
    public final ExecutionType executionType;

    /**
     * An array containing the execution types that are currently implemented. Being in this list does not mean that the
     * type is supported on this system.
     */
    public static final ExecutionTarget[] implementedTargets = { singleThread, forkJoin };

    protected ExecutionTarget(String name, ExecutionType executionType) {
        this.name = name;
        this.executionType = executionType;
    }

    /**
     * Returns the name of the execution technique.
     * 
     * @return The name of the execution technique.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * A method to test if is Execution Target is supported on the current machine. For example is a suitable GPU and
     * supporting libraries present.
     * 
     * @return True if this Execution Target is supported, false otherwise.
     */
    public abstract boolean isSupported();
}
