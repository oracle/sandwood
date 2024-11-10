/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.model.variables;

/**
 * Interface to be implemented by Objects that represent an observed double in the model.
 */
public interface ObservedDouble extends ObservedVariable {

    /**
     * Method to set the value of an observed variable.
     * 
     * @param value The value to set the variable to.
     */
    void setValue(double value);

    /**
     * Recover the value that the variable has been set to. This method is only for convenience, and should not be used
     * to modify the variable.
     * 
     * @return The value the observed variable is set to.
     */
    double getValue();

}