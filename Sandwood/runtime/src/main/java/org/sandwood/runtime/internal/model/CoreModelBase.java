/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model;

import org.sandwood.random.RandomType;
import org.sandwood.random.internal.RandomConstructor;
import org.sandwood.random.internal.Rng;

public abstract class CoreModelBase<S extends CoreModelScratch> implements CoreModel {
    private final RandomConstructor rngConstructor = new RandomConstructor(RandomType.L32X64MixRandom);
    protected Rng RNG$ = rngConstructor.constructRng();
    protected S scratch;
    
    protected CoreModelBase() {}

    @Override
    public void allocateScratch() {
        scratch.allocateScratch();
    }
    
    @Override
    public void setRngType(RandomType randomType, long seed) {
        RNG$ = rngConstructor.setRngType(randomType, seed);
    }

    @Override
    public void setRngType(RandomType randomType) {
        RNG$ = rngConstructor.setRngType(randomType);
    }

    @Override
    public RandomType getRngType() {
        return rngConstructor.getRngType();
    }

    @Override
    public void initializeSeed(long seed) {
        RNG$ = rngConstructor.initializeSeed(seed);
    }

    @Override
    public void close() {
        shutdown();
    }
}
