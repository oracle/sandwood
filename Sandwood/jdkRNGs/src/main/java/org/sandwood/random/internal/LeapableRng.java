/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.random.internal;

import java.util.random.RandomGenerator.JumpableGenerator;
import java.util.random.RandomGeneratorFactory;

import org.sandwood.random.RandomType;

/**
 * A wrapper for leapable RNGs. User code should not use this class.
 */
public class LeapableRng implements Rng {
    private final JumpableGenerator random;

    /**
     * Constructor taking the type and seed for the rng.
     * 
     * @param type The type of the rng.
     * @param seed The seed for the rng.
     */
    LeapableRng(RandomType type, long seed) {
        RandomGeneratorFactory<JumpableGenerator> f = RandomGeneratorFactory.of(type.toString());
        random = f.create(seed);
    }

    private LeapableRng(JumpableGenerator random) {
        this.random = random;
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
     * @param mean     the mean value of the distribution.
     * @param variance The variance of the distribution.
     *
     * @return Pseudorandom number picked from a normal or Gaussian distribution with the provided mean and standard
     *         deviation.
     */
    @Override
    public final double normal(double mean, double variance) {
        return random.nextGaussian(mean, Math.sqrt(variance));
    }
    
    /**
     * Returns a value picked from an exponential distribution with mean of 1.
     * @return Pseudorandom number picked from an exponential distribution with a mean of 1.
     */
    @Override
    public double exponential() {
        return random.nextExponential();
    }

    @Override
    public final LeapableRng nextRng() {
        JumpableGenerator nextRng = random.copy();
        nextRng.jump();
        return new LeapableRng(nextRng);
    }

}
