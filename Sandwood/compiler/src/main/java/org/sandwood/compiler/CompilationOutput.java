/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.sandwood.compiler.compilation.util.SandwoodCompileError;
import org.sandwood.compiler.compilation.util.SandwoodCompileWarning;

/**
 * A wrapper class to hold errors and warnings output by the compiler.
 */
public class CompilationOutput {
    /** List to store any errors generated when compiling the model. */
    private final List<SandwoodCompileError> errors = new ArrayList<>();

    /** List to store any warnings generated when compiling the model. */
    private final List<SandwoodCompileWarning> warnings = new ArrayList<>();

    public void addError(SandwoodCompileError error) {
        if(!errors.contains(error))
            errors.add(error);
    }

    public void addErrors(Collection<SandwoodCompileError> errors) {
        for(SandwoodCompileError e:errors)
            addError(e);
    }

    public List<SandwoodCompileError> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public boolean noErrors() {
        return errors.isEmpty();
    }

    public void addWarning(SandwoodCompileWarning warning) {
        if(!warnings.contains(warning))
            warnings.add(warning);
    }

    public void addWarnings(Collection<SandwoodCompileWarning> warnings) {
        for(SandwoodCompileWarning w:warnings)
            addWarning(w);
    }

    public List<SandwoodCompileWarning> getWarnings() {
        return Collections.unmodifiableList(warnings);
    }

    public boolean noWarnings() {
        return warnings.isEmpty();
    }
}
