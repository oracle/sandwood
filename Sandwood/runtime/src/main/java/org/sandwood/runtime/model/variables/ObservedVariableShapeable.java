/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.model.variables;

/**
 * An interface that is implemented by shapeable observed variables. It extends the basic ObservedVariable interface
 * with the ability to test if a shape has been set.
 */
public interface ObservedVariableShapeable extends ObservedVariable {

    /**
     * Test to see if the shape of this array has been set.
     * 
     * @return Has the shape of the array been set.
     */
    boolean shapeSet();

}
