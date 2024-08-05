/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.util;

import java.util.ArrayList;
import java.util.List;

import org.sandwood.compiler.exceptions.SandwoodModelException;
import org.sandwood.compiler.trees.outputTree.OutputSandwoodClass;

public class CompilationDesc {
    public final List<OutputSandwoodClass> classes = new ArrayList<>();
    public final List<SandwoodModelException> errors = new ArrayList<>();
    public final List<SandwoodModelException> warnings = new ArrayList<>();

    public boolean successful() {
        return errors.isEmpty();
    }
}
