/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.names;

import org.sandwood.compiler.exceptions.CompilerException;

public class PackageName extends Name {

    public PackageName(String name) {
        super(name);
        if(name == null)
            throw new CompilerException("Null function name set");
    }

    /**
     * Test to check if this package name is set.
     * 
     * @return Returns true if the package name is not set.
     */
    public boolean isEmpty() {
        return name.isEmpty();
    }
}
