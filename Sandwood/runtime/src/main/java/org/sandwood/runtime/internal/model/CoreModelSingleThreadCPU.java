/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model;

import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.runtime.exceptions.SandwoodRuntimeException;
import org.sandwood.runtime.model.ExecutionTarget;

/**
 * A class to hold any boilerplate code associated with single threaded execution. This will probably end up being null
 * methods for setting thread counts etc.
 */
public abstract class CoreModelSingleThreadCPU extends CoreModelBase {
    protected CoreModelSingleThreadCPU(ExecutionTarget target) {
        if(target != ExecutionTarget.singleThread)
            throw new SandwoodRuntimeException(
                    "Unexpected execution target for this backend. Target " + target + " was passed as an argument");
    }

    @Override
    public void setThreadCount(int count) {
        if(count != 1)
            throw new SandwoodException(
                    "A single threaded backend can only work with 1 thread. Please change the backend for parallel execution before setting a thread count.");
    }

    @Override
    public int threadCount() {
        return 1;
    }

    @Override
    public void shutdown() {}
}
