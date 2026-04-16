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
    public static final ClassName coreBase = new ClassName("org.sandwood.runtime.internal.model.CoreModel", true);

    public static final ClassName wrapperBase = new ClassName("org.sandwood.runtime.model.Model", true);

    public final boolean useInImport;

    public ClassName(String name, boolean useInImport) {
        super(name);
        if(name == null)
            throw new CompilerException("Set null class name.");
        this.useInImport = useInImport;
    }

    public static ClassName coreBase(ExecutionType target) {
        return new ClassName(coreBase.getName() + target, true);
    }

    public static ClassName dereferencedName(String name) {
        return new ClassName(name, false);
    }

    public static ClassName QualifiedName(PackageName packageName, ClassName outerClassName, ClassName InnerClassName) {
        if(packageName.isEmpty())
            return new ClassName(outerClassName + "." + InnerClassName, false);
        else
            return new ClassName(packageName + "." + outerClassName + "." + InnerClassName, true);
    }

    public static ClassName QualifiedName(ClassName outerClassName, ClassName InnerClassName) {
        return new ClassName(outerClassName + "." + InnerClassName, false);
    }
}
