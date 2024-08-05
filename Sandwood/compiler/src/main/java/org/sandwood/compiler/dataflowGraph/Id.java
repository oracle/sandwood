/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph;

import java.util.concurrent.atomic.AtomicInteger;

public class Id {
    /** Next task Id. */
    private final static AtomicInteger nextID = new AtomicInteger();

    /** The id of the task */
    private final int id;

    public Id() {
        id = nextID.incrementAndGet();
    }

    /**
     * Method to reset the Id, this is called via reflection in testing.
     */
    @SuppressWarnings("unused")
    private static void reset() {
        nextID.set(0);
    }

    /**
     * Get the ID of the task
     *
     * @return The task ID.
     */
    public int id() {
        return id;
    }
}
