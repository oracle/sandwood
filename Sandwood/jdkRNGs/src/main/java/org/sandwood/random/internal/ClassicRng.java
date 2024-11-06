/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.random.internal;

import java.util.Random;

/**
 * A wrapper class for the classic java RNG. User code should not use this class.
 */
public class ClassicRng implements Rng {
    private final Random random;

    /**
     * Constructor for this wrapper class
     * 
     * @param random RNG to wrap.
     */
    private ClassicRng(Random random) {
        this.random = random;
    }

    /**
     * Constructor for this wrapper class
     * 
     * @param seed the seed for the classic rng.
     */
    public ClassicRng(long seed) {
        this.random = new java.util.Random(seed);
    }

    /**
     * Returns a double picked from a uniform distribution in the range 0 - 1.
     *
     * @return Pseudorandom number picked from a uniform distribution between 0 - 1.
     */
    @Override
    public final double uniform() {
        return random.nextDouble();
    }

    /**
     * Returns a double picked from a normal distribution.
     *
     * @return Pseudorandom number picked from a normal or Gaussian distribution with mean 0 and variance 1.
     */
    @Override
    public final double normal() {
        return random.nextGaussian();
    }

    /**
     * Returns a value picked from an exponential distribution with mean of 1.
     * 
     * @return Pseudorandom number picked from an exponential distribution with a mean of 1.
     */
    @Override
    public double exponential() {
        return random.nextExponential();
    }

    @Override
    public final ClassicRng nextRng() {
        long newSeed = random.nextLong() * 42;
        Random nextRng = new Random(newSeed);
        return new ClassicRng(nextRng);
    }

}
