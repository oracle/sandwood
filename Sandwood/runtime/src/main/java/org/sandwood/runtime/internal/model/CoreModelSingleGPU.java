/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model;

import org.sandwood.runtime.exceptions.SandwoodRuntimeException;
import org.sandwood.runtime.model.ExecutionTarget;

/**
 * A class to hold any boilerplate code associated with single GPU execution. This will probably end up being null
 * methods for setting thread counts etc.
 */
public abstract class CoreModelSingleGPU extends CoreModelBase {
    protected CoreModelSingleGPU(ExecutionTarget target) {
        if(target != ExecutionTarget.gpu)
            throw new SandwoodRuntimeException(
                    "Unexpected execution target for this backend. Target " + target + " was passed as an argument");
    }
}
