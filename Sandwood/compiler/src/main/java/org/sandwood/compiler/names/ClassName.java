/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.names;

import org.sandwood.common.execution.ExecutionType;
import org.sandwood.compiler.exceptions.CompilerException;

public class ClassName extends Name {
    protected static final String coreBase = "org.sandwood.runtime.internal.model.CoreModel";

    protected ClassName(String name) {
        super(name);
        if(name == null)
            throw new CompilerException("Set null class name.");
    }

    public static ClassName coreBase(ExecutionType target) {
        return new ClassName(coreBase + target);
    }

    public static ClassName coreBase() {
        return new ClassName(coreBase);
    }
}
