/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.random.internal;

import java.util.concurrent.atomic.AtomicLong;

import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.random.RandomType;

/**
 * A class used in core classes to construct RNGs. User code should not use this class.
 */
public class RandomConstructor {
    private RandomType randomType;
    private final AtomicLong seedUniquifier = new AtomicLong(8682522807148012L);
    private long seed = seedUniquifier() ^ System.nanoTime(); // seed the RNG was initialized with so new copies can be
                                                              // created.

    // Taken from the original Java Random seed code. Probably a bit iffy as a
    // result.
    private long seedUniquifier() {
        // L'Ecuyer, "Tables of Linear Congruential Generators of
        // Different Sizes and Good Lattice Structure", 1999
        for(;;) {
            long current = seedUniquifier.get();
            long next = current * 1181783497276652981L;
            if(seedUniquifier.compareAndSet(current, next))
                return next;
        }
    }

    /**
     * Create a RandomConstructor set to create RNGs of the specified type.
     * 
     * @param randomType Type of RNGs to create.
     */
    public RandomConstructor(RandomType randomType) {
        this.randomType = randomType;
    }

    /**
     * Method to construct an RNG of the type the constructor is set to using the provided seed.
     * 
     * @param seed Seed for the RNG
     * @return An RNG constructed using the provided seed.
     */
    public Rng initializeSeed(long seed) {
        this.seed = seed;
        return constructRng();
    }

    /**
     * Method to determine the type of RNG this constructor is currently set to produce.
     * 
     * @return The current RNG type.
     */
    public RandomType getRngType() {
        return randomType;
    }

    /**
     * Method to update the RNG type of this constructor, and to produce a new RNG of this type.
     * 
     * @param randomType The new type of RNG to produce.
     * @return An instance of this new type of RNG.
     */
    public Rng setRngType(RandomType randomType) {
        this.randomType = randomType;
        return constructRng();
    }

    /**
     * Method to set the RNG type and the seed of the constructor, and to then construct a method using this type.
     * 
     * @param randomType The type of the RNG.
     * @param seed       The value of the seed for the RNG.
     * @return An instance of an RNG using these settings.
     */
    public Rng setRngType(RandomType randomType, long seed) {
        this.randomType = randomType;
        this.seed = seed;
        return constructRng();
    }

    /**
     * A method to construct a new RNG using the constructors settings.
     * 
     * @return A new RNG of the constructors type using the constructors seed.
     */
    public Rng constructRng() {
        switch(randomType.category) {
            case Classic:
                return new ClassicRng(seed);
            case Splittable:
                return new SplittableRng(randomType, seed);
            case Leapable:
                return new LeapableRng(randomType, seed);
            default:
                throw new SandwoodException("Unknown RNG type " + randomType);
        }
    }
}
