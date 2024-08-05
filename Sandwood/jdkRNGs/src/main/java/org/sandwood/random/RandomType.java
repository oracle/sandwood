/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.random;

/**
 * Enumeration of the different types of random number generator supported.
 */
public enum RandomType {
    /**
     * The original Java RNG
     */
    JavaOriginal(RandomCategory.Classic),
    /**
     * The splittable RNG from Java
     */
    SplittableRandom(RandomCategory.Splittable),
    /**
     * The L128X1024MixRandom RNG from Java
     */
    L128X1024MixRandom(RandomCategory.Splittable),
    /**
     * The L128X128MixRandom RNG from Java
     */
    L128X128MixRandom(RandomCategory.Splittable),
    /**
     * The L128X256MixRandom RNG from Java
     */
    L128X256MixRandom(RandomCategory.Splittable),
    /**
     * The L32X64MixRandom RNG from Java
     */
    L32X64MixRandom(RandomCategory.Splittable),
    /**
     * The L64X1024MixRandom RNG from Java
     */
    L64X1024MixRandom(RandomCategory.Splittable),
    /**
     * The L64X128MixRandom RNG from Java
     */
    L64X128MixRandom(RandomCategory.Splittable),
    /**
     * The L64X128StarStarRandom RNG from Java
     */
    L64X128StarStarRandom(RandomCategory.Splittable),
    /**
     * The L64X256MixRandom RNG from Java
     */
    L64X256MixRandom(RandomCategory.Splittable),
    /**
     * The Xoroshiro128PlusPlus RNG from Java
     */
    Xoroshiro128PlusPlus(RandomCategory.Leapable),
    /**
     * The Xoshiro256PlusPlus RNG from Java
     */
    Xoshiro256PlusPlus(RandomCategory.Leapable);

    /**
     * An enumeration to describe the categories that different random number generator fit into.
     */
    public enum RandomCategory {
        /**
         * Random number generators of this type fit the classic Java RNG behaviour.
         */
        Classic,

        /**
         * Random number generators of this type implement the splitable interface. These random number generators work
         * best with the fork-join execution environments.
         */
        Splittable,

        /**
         * Random number generators of this type implement the leapable interface.
         */
        Leapable
    }

    /**
     * The category that this random number generator is drawn from.
     */
    public final RandomCategory category;

    RandomType(RandomCategory category) {
        this.category = category;
    }
}
