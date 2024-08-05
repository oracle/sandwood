/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.util;

import org.sandwood.compiler.srcTools.sourceToSource.Location;

public class SandwoodCompileError extends SandwoodCompileOutputMessage {
    public SandwoodCompileError(String file, Location loc, String source, String message) {
        super(file, loc, source, message);
    }

    public SandwoodCompileError(String file, String message) {
        super(file, message);
    }

    public SandwoodCompileError(String message) {
        super(message);
    }

    @Override
    protected String messageType() {
        return "Error";
    }
}
