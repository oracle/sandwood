/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.execution;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU.LoopBody;

/**
 * A class to hold any boilerplate code associated with Fork-Join execution.
 */
public class ForkJoinEngine implements CoreModelMultiThreadCPU.MultiThreadedCPUEngine {

    private class WorkUnit extends RecursiveAction {
        private static final long serialVersionUID = 774257845826640886L;
        private final int start, end;
        private final Rng random;
        private final LoopBody body;

        public WorkUnit(Rng random, int start, int end, LoopBody body) {
            this.start = start;
            this.end = end;
            this.random = random.nextRng();
            this.body = body;
        }

        @Override
        protected void compute() {
            long threadId = Thread.currentThread().getId();
            Integer dataId = threadIds.get(threadId);

            if(dataId == null) {
                int d = 0;
                synchronized(usedIds) {
                    while(usedIds[d])
                        d++;
                    usedIds[d] = true;
                }
                threadIds.put(threadId, d);
                body.run(start, end, d, random);
                threadIds.remove(threadId);

                // This doesn't need synchronisation.
                usedIds[d] = false;
            } else {
                body.run(start, end, dataId, random);
            }
        }
    }

    private class RootUnit extends RecursiveAction {
        private static final long serialVersionUID = 1L;
        private final List<WorkUnit> workUnits;

        public RootUnit(List<WorkUnit> workUnits) {
            this.workUnits = workUnits;
        }

        @Override
        protected void compute() {
            ForkJoinTask.invokeAll(workUnits);
        }
    }

    private ForkJoinPool pool = new ForkJoinPool();

    private final ConcurrentMap<Long, Integer> threadIds = new ConcurrentHashMap<>();
    private boolean[] usedIds = new boolean[threadCount()];

    /**
     *
     * @param random THe random number generator of the parent loop
     * @param start  The start value for the iterations
     * @param end    The end Value for the iterations.
     * @param step   The size of the step between iterations. The body will only execute for indexes i = start + n*step
     *               where n is an integer.
     * @param body   The body to execute.
     */
    @Override
    public final void parallelFor(Rng random, int start, int end, int step, LoopBody body) {
        int runs = end - start;
        int iterations = runs / step;

        int threads = threadCount();
        List<WorkUnit> workUnits = new ArrayList<>(threads);
        if(iterations <= threads) { // If there is only one iteration per thread.
            int offsetStart = 0;
            int offsetEnd = step;
            for(int i = 0; i < iterations; i++) {
                workUnits.add(new WorkUnit(random, start + offsetStart, start + offsetEnd, body));
                offsetStart = offsetEnd;
                offsetEnd += step;
            }
        } else { // If there is more than one iteration per thread.
                 // TODO work out a more sophisticated way of determining the correct
                 // granularity.
            final int workUnitsPerThread = Math.min(3, iterations / threads);
            int workUnitCount = workUnitsPerThread * threads;
            int extraLoops = iterations % workUnitCount;
            int baseLoops = iterations / workUnitCount;

            for(int i = 0; i < workUnitCount; i++) {
                int offset = Math.min(i, extraLoops);
                int iStart = start + i * baseLoops + offset;
                int iEnd = iStart + baseLoops + ((i < extraLoops) ? 1 : 0);
                workUnits.add(new WorkUnit(random, iStart, iEnd, body));
            }
        }

        run(workUnits);
    }

    private void run(List<WorkUnit> workUnits) {
        if(ForkJoinTask.inForkJoinPool())
            ForkJoinTask.invokeAll(workUnits);
        else
            pool.invoke(new RootUnit(workUnits));
    }

    @Override
    public void setThreadCount(int threadCount) {
        pool.shutdown();
        pool = new ForkJoinPool(threadCount);
        usedIds = new boolean[threadCount];
    }

    @Override
    public int threadCount() {
        return pool.getParallelism();
    }

    @Override
    public void shutdown() {
        pool.shutdown();
    }
}
