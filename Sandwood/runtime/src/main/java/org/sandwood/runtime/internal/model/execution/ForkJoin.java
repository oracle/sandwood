/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.execution;

import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU.MultiThreadedCPUEngine;

/**
 * A class describing a ForkJoin CPU execution target.
 */
public class ForkJoin extends MultiThreadedCPUExecutionTarget {

    /**
     * A singleton instance of the class
     */
    private static final ForkJoin executionInstance = new ForkJoin();

    /**
     * A private constructor to create the singleton instance.
     */
    private ForkJoin() {
        super("ForkJoin");
    }

    /**
     * A method to get an instance of this execution type.
     * 
     * @return Returns the singleton instance of this class.
     */
    public static ForkJoin getInstance() {
        return executionInstance;
    }

    @Override
    public boolean isSupported() {
        return true;
    }

    @Override
    public MultiThreadedCPUEngine getEngine() {
        return new ForkJoinEngine();
    }
}
