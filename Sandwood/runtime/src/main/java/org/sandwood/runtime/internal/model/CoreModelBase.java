/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model;

import org.sandwood.random.RandomType;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.model.state.CoreModelState;
import org.sandwood.runtime.internal.model.variables.CurrentProbability;

public abstract class CoreModelBase<STATE extends CoreModelState, SCRATCH extends CoreModelScratch> implements CurrentProbability, AutoCloseable {
    protected final STATE state;
    protected SCRATCH scratch;
    
    protected CoreModelBase(STATE state) {
        this.state = state;
    }

    public void allocate() {
        state.allocate();
        state.RNG$ = state.rngConstructor.constructRng();
        allocateScratch();
    }
    
    public void allocateScratch() {
        scratch.allocateScratch();
    }
    
    public void setRngType(RandomType randomType, long seed) {
        state.RNG$ = state.rngConstructor.setRngType(randomType, seed);
    }

    public void setRngType(RandomType randomType) {
        state.RNG$ = state.rngConstructor.setRngType(randomType);
    }

    public RandomType getRngType() {
        return state.rngConstructor.getRngType();
    }

    public void initializeSeed(long seed) {
        state.RNG$ = state.rngConstructor.initializeSeed(seed);
    }

    @Override
    public void close() {
        shutdown();
    }

    @Override
    public double getCurrentLogProbability() {
        return state.getCurrentLogProbability();
    }
    
    public double get$logProbability$$evidence() {
        return state.get$logProbability$$evidence();
    }
    
    public abstract void gibbsRound();

    public abstract void logModelProbabilitiesVal();

    public abstract void logModelProbabilitiesDist();

    public abstract void logEvidenceProbabilities();

    public abstract void forwardGeneration();

    public abstract void forwardGenerationPrime();

    public abstract void forwardGenerationValuesNoOutputs();

    public abstract void forwardGenerationValuesNoOutputsPrime();

    public abstract void forwardGenerationDistributionsNoOutputsPrime();

    // TODO move into state
    public abstract void initializeModel();

    // TODO move into state
    public abstract void propagateObservedValues();

    // TODO move into state
    public abstract void setIntermediates();

    // TODO move this into wrapped class
    public abstract String modelCode();

    public abstract void setThreadCount(int count);

    public abstract int threadCount();

    public abstract void shutdown();
}
