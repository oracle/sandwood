/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.exceptions.SandwoodRuntimeException;
import org.sandwood.runtime.internal.model.execution.MultiThreadedCPUExecutionTarget;
import org.sandwood.runtime.model.ExecutionTarget;

/**
 * A class to hold any boilerplate code associated with Fork-Join execution.
 */
public abstract class CoreModelMultiThreadCPU extends CoreModelBase {

    public interface LoopBody {
        void run(int start, int end, int threadId, Rng random);
    }

    public interface MultiThreadedCPUEngine {
        /**
         *
         * @param random THe random number generator of the parent loop
         * @param start  The start value for the iterations
         * @param end    The end Value for the iterations.
         * @param step   The size of the step between iterations. The body will only execute for indexes i = start +
         *               n*step where n is an integer.
         * @param body   The body to execute.
         */
        void parallelFor(Rng random, int start, int end, int step, LoopBody body);

        void setThreadCount(int threadCount);

        int threadCount();

        void shutdown();
    }

    private final MultiThreadedCPUEngine engine;

    protected CoreModelMultiThreadCPU(ExecutionTarget target) {
        if(!(target instanceof MultiThreadedCPUExecutionTarget))
            throw new SandwoodRuntimeException(
                    "Core instantiated with execution type that" + "does not extend MultiThreadedCPUExecutionTarget");
        engine = ((MultiThreadedCPUExecutionTarget) target).getEngine();
    }

    /**
     *
     * @param random THe random number generator of the parent loop
     * @param start  The start value for the iterations
     * @param end    The end Value for the iterations.
     * @param step   The size of the step between iterations. The body will only execute for indexes i = start + n*step
     *               where n is an integer.
     * @param body   The body to execute.
     */
    protected final void parallelFor(Rng random, int start, int end, int step, LoopBody body) {
        engine.parallelFor(random, start, end, step, body);
    }

    @Override
    public final void setThreadCount(int threadCount) {
        engine.setThreadCount(threadCount);
    }

    @Override
    public final int threadCount() {
        return engine.threadCount();
    }

    @Override
    public final void shutdown() {
        engine.shutdown();
    }
}
