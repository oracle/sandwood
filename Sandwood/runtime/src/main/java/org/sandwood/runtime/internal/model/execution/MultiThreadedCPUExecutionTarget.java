/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.execution;

import org.sandwood.common.execution.ExecutionType;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU.MultiThreadedCPUEngine;
import org.sandwood.runtime.model.ExecutionTarget;

/**
 * A constructor base type for execution targets that target multithreaded CPU targets.
 */
public abstract class MultiThreadedCPUExecutionTarget extends ExecutionTarget {

    /**
     * A constructor base type for execution targets that target multithreaded CPU targets.
     * 
     * @param name The name of the execution target.
     */
    protected MultiThreadedCPUExecutionTarget(String name) {
        super(name, ExecutionType.MultiThreadCPU);
    }

    /**
     * A method to get the underlying engine
     * 
     * @return The engine that will drive this target type.
     */
    public abstract MultiThreadedCPUEngine getEngine();
}
