/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.model.variables;

/**
 * An interface for implemented by objects representing multidimensional arrays where the size of the array is used by
 * the model during conventional execution.
 *
 * @param <A> The type of the objects in the array.
 * @param <B> The type of the shape of the array.
 */
public interface ObservedObjectArrayShapeable<A, B> extends ObservedObjectArray<A>, ObservedVariableShapeable {

    /**
     * Method to set the shape of the array. This is used when the shape is important to the calculation, but because we
     * are inferring values we do not want to set the output values.
     * 
     * @param shape The shape of the array. This will be an integer array of n-1 dimensions. where n is the number of
     *              dimensions of the array. For example if the array holds the following values {{A,B,C},{D,E,F,G}}
     *              then the array that would be needed to describe this shape is {3,4}.
     */
    void setShape(B shape);

    /**
     * Method to recover the shape of the observed variable.
     * 
     * @return The shape of the array.
     */
    B getShape();
}