/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.model.variables;

/**
 * Interface implemented by all objects that represent variables in the model.
 */
public interface Variable {
    /**
     * Method that returns the variables name.
     * 
     * @return The name of the model variable that this class represents.
     */
    String name();
}
