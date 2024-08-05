/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.model.variables;

/**
 * Interface to be implemented by all observed variables.
 */
public interface ObservedVariable extends Variable {

    /**
     * Method to test is an observed variable has been set.
     * 
     * @return Boolean indicating if the observed variable has been set.
     */
    boolean isSet();

}