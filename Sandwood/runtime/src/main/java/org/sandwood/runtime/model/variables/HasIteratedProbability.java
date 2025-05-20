/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.model.variables;

/**
 * An interface that is implemented by all objects that represent variables can have a probability associated with them
 * for each iteration of the loops they are embedded in. his only occurs for random variables if single probability has
 * been set to false.
 * 
 * @param <A> An n dimensional array of doubles where n is the depth of the nesting of the random variable.
 */
public interface HasIteratedProbability<A> {

    /**
     * Method to return the estimated probability of an entity embedded in one or more for loop. For each loop this
     * variable is embedded into another dimension is added to the array. Each iteration of the loop results in a single
     * entry in the array.
     * 
     * @return The estimated probability of the entity for each iteration.
     */
    A getProbability();

    /**
     * Method to return the estimated log probability of an entity embedded in one or more for loop. For each loop this
     * variable is embedded into another dimension is added to the array. Each iteration of the loop results in a single
     * entry in the array.
     * 
     * @return The estimated log probability of the entity for each iteration.
     */
    A getLogProbability();

    /**
     * Test to see if a probability has been computed.
     * 
     * @return Has the probability value been computed.
     */
    boolean probabilityComputed();

}