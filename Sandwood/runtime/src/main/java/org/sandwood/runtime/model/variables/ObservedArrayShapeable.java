/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.model.variables;

/**
 * An interface to be implemented by objects that represent observed arrays whose shape is used in the model
 * calculations during conventional execution.
 */
public interface ObservedArrayShapeable extends ObservedVariableShapeable {

    /**
     * Method to set the length of the array. This is used when the shape is important to the calculation, but because
     * we are inferring values we do not want to set the output values.
     * 
     * @param length The length of the array.
     */
    void setLength(int length);

    /**
     * Method to recover the length of the array.
     * 
     * @return the length of the array.
     */
    int getLength();

}
