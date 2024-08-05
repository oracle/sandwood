/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.model.variables;

/**
 * An interface that is implemented by all objects that represent variables can have a probability associated with them.
 * Examples of this include Random Variables and Computed Variables.
 */
public interface HasProbability {

    /**
     * Method to return the estimated probability of this entity in the current model. The entity could range from a
     * single variable to the entire model.
     * 
     * @return The estimated probability of the entity.
     */
    double getProbability();

    /**
     * Method to return the estimated log probability of this entity in the current model. The entity could range from a
     * single variable to the entire model.
     * 
     * @return The estimated probability of the entity.
     */
    double getLogProbability();

    /**
     * Test to see if a probability has been computed.
     * 
     * @return Has the probability value been computed.
     */
    boolean probabilityComputed();

}