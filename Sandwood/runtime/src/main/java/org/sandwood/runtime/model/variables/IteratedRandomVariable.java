/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.model.variables;

/**
 * An object to represent the probability of random variables placed inside for loops. Unlike the standard random
 * variable that produces a single probability, this version will produce a probability for each iteration of the loop.
 *
 * @param <A> An n dimensional array of doubles where n is the depth of the nesting of the random variable.
 */
public interface IteratedRandomVariable<A> extends HasIteratedProbability<A> {
    /**
     * Method that returns the name of the variable.
     * 
     * @return The name of the model variable that this class represents.
     */
    String name();
}
