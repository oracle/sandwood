/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.scopesState;

import org.sandwood.compiler.exceptions.CompilerException;

record VersionId(int usedId, int maxId) {
    VersionId {
        if( usedId < 0 || maxId < 0)
            throw new CompilerException("Negative ids are not allowed");
        if( maxId < usedId)
            throw new CompilerException("Max id cannot be less than used id");
    }
}
