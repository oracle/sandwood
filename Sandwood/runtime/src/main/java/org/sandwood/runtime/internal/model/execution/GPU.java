/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.execution;

import org.sandwood.common.execution.ExecutionType;
import org.sandwood.runtime.model.ExecutionTarget;

/**
 * A class describing a GPU execution target.
 */
public class GPU extends ExecutionTarget {

    /**
     * A singleton instance of the class
     */
    private static final GPU executionInstance = new GPU();

    /**
     * A private constructor to create the singleton instance.
     */
    private GPU() {
        super("GPU", ExecutionType.GPU);
    }

    /**
     * A method to get an instance of this execution type.
     * 
     * @return Returns the singleton instance of this class.
     */
    public static GPU getInstance() {
        return executionInstance;
    }

    @Override
    public boolean isSupported() {
        return false;
    }
}
