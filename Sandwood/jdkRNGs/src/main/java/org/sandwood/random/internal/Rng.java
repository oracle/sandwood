/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.random.internal;

/**
 * An interface for Random Number generators.
 */
public interface Rng {
    /**
     * Returns a double picked from a uniform distribution in the range 0 - 1.
     *
     * @return Random number picked from a uniform distribution between 0 - 1.
     */
    double uniform();

    /**
     * Returns a double picked from a normal distribution.
     *
     * @param mean     The mean value of the distribution.
     * @param variance The variance of the distribution.
     *
     * @return Pseudorandom number picked from a normal or Gaussian distribution with the provided mean and variance.
     */
    double normal(double mean, double variance);
    
    /**
     * Returns a value picked from an exponential distribution with mean of 1.
     * @return Pseudorandom number picked from an exponential distribution with a mean of 1.
     */
    double exponential();

    /**
     * Returns a new random number generator based on this random number generator.
     *
     * @return Random number generator based on this random number generator.
     */
    Rng nextRng();
}
