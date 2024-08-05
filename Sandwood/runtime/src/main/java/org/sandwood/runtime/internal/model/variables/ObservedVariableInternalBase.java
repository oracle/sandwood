/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.variables;

import org.sandwood.runtime.model.Model;

public abstract class ObservedVariableInternalBase implements ObservedVariableInternal {
    protected boolean set = false;

    protected final String name;
    protected final Model model;

    public ObservedVariableInternalBase(Model model, String name) {
        this.model = model;
        this.name = name;
    }

    @Override
    public final String name() {
        return name;
    }

    @Override
    public boolean isSet() {
        synchronized(model) {
            return set;
        }
    }
}
