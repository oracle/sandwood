/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.state;

import org.sandwood.random.RandomType;
import org.sandwood.random.internal.RandomConstructor;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.variables.CurrentProbability;

public abstract class CoreModelState implements CurrentProbability{
    public final RandomConstructor rngConstructor = new RandomConstructor(RandomType.L32X64MixRandom);
    public Rng RNG$;
    
    public abstract void allocate();
    
    public abstract double get$logProbability$$evidence();
}
