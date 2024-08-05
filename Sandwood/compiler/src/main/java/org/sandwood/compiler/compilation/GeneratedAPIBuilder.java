/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation;

import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.compilation.util.CompilationDesc;

public abstract class GeneratedAPIBuilder {
    public abstract CompilationDesc buildClass(CompilationOptions opts);

    public CompilationDesc buildClass() {
        return buildClass(new CompilationOptions());
    }
}
