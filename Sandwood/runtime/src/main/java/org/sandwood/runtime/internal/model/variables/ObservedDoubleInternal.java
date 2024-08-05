/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.variables;

import java.io.IOException;

import org.sandwood.runtime.exceptions.SandwoodJsonException;
import org.sandwood.runtime.exceptions.SandwoodVariableSetException;
import org.sandwood.runtime.internal.json.JsonDecoder;
import org.sandwood.runtime.internal.json.JsonEncoder;
import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.variables.ObservedDouble;

public abstract class ObservedDoubleInternal extends ObservedVariableInternalBase implements ObservedDouble {

    public ObservedDoubleInternal(Model model, String name) {
        super(model, name);
    }

    @Override
    public final void set(double value) {
        synchronized(model) {
            if(set)
                throw new SandwoodVariableSetException(
                        "Unable to set the value of " + name + " as it has already been set.");
            set = true;
            setValue(value);
        }
    }

    @Override
    public final void toJson(JsonEncoder e) throws IOException {
        e.addDouble(name(), get());
    }

    @Override
    public final void fromJSON(JsonDecoder decoder) throws SandwoodJsonException, IOException {
        set(decoder.getDouble());
    }

    @Override
    public abstract double get();

    protected abstract void setValue(double value);
}
