/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.names;

import java.util.HashSet;
import java.util.Set;

import org.sandwood.common.execution.ExecutionType;
import org.sandwood.compiler.exceptions.CompilerException;

public class ModelClassName extends ClassName {
    private final Set<String> helperClasses;

    private ModelClassName(String name, Set<String> helperClasses) {
        super(name);
        this.helperClasses = helperClasses;
        if(helperClasses == null)
            throw new CompilerException("Set null helper classes set.");
    }

    public static ModelClassName modelName(String modelName, Set<String> helperClasses) {
        return new ModelClassName(modelName, helperClasses);
    }

    public ClassName backendName(ExecutionType target) {
        return new ClassName(name + prefix + target);
    }

    public ClassName interfaceName() {
        return new ClassName(name + prefix + "CoreInterface");
    }

    public Set<ClassName> helperClassNames() {
        Set<ClassName> classNames = new HashSet<>();
        for(String helperClass:helperClasses)
            classNames.add(new ClassName(name + prefix + helperClass));
        return classNames;
    }
}
