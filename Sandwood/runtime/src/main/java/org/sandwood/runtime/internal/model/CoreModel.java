/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model;

import org.sandwood.random.RandomType;
import org.sandwood.runtime.internal.model.variables.CurrentProbability;

public interface CoreModel extends CurrentProbability, AutoCloseable {

    void setRngType(RandomType randomType, long seed);

    void setRngType(RandomType randomType);

    RandomType getRngType();

    void initializeSeed(long seed);

    void logProbabilityGeneration();

    void gibbsRound();

    void logModelProbabilitiesVal();

    void logModelProbabilitiesDist();

    double get$logProbability$$evidence();

    void forwardGeneration();

    void forwardGenerationValuesNoOutputs();

    void forwardGenerationDistributionsNoOutputs();

    void initializeConstants();

    void propogateObservedValues();

    void allocator();

    void allocateScratch();

    void setIntermediates();

    String modelCode();

    void setThreadCount(int count);

    int threadCount();

    void shutdown();
}
