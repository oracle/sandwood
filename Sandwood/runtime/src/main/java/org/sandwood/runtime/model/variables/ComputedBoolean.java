/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.model.variables;

/**
 * Interface for variables that represent the values of boolean's computed during the execution of the model.
 */
public interface ComputedBoolean extends ComputedVariable {

    /**
     * Get the samples generated when running the model.
     * 
     * @return An array of samples, one for each iteration of the model
     */
    boolean[] getSamples();

    /**
     * Get the value that corresponds to the most probable model. This is drawn from the iterations the model has
     * already made.
     * 
     * @return The most probable value.
     */
    boolean getMAP();

    /**
     * Method to set a value of an element in the model. This would be used to fix the value of the element in the
     * model.
     * 
     * @param value The value to set the element to.
     */
    void setValue(boolean value);
}