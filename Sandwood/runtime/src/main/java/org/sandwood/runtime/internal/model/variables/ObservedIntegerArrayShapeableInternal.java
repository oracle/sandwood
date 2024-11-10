/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.variables;

import java.io.IOException;

import org.sandwood.runtime.exceptions.SandwoodVariableSetException;
import org.sandwood.runtime.internal.json.JsonEncoder;
import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.variables.ObservedIntegerArrayShapeable;

public abstract class ObservedIntegerArrayShapeableInternal extends ObservedIntegerArrayInternal
        implements ObservedIntegerArrayShapeable, ObservedVariableShapeableInternal<int[]> {
    private boolean shapeSet = false;

    public ObservedIntegerArrayShapeableInternal(Model model, String name) {
        super(model, name);
    }

    @Override
    public final boolean shapeSet() {
        synchronized(model) {
            return shapeSet;
        }
    }

    public final void setShape(int length) {
        if(shapeSet)
            throw new SandwoodVariableSetException(
                    "Unable to set the shape of " + name + " as its shape has already been set.");
        setShapeInternal(length);
        shapeSet = true;
        set = false;
    }

    @Override
    public final void setLength(int length) {
        synchronized(model) {
            if(shapeSet)
                throw new SandwoodVariableSetException(
                        "Unable to set the length of " + name + " as its length has already been set.");
            setShape(length);
        }
    }

    @Override
    public final int getLength() {
        synchronized(model) {
            return getShape();
        }
    }

    @Override
    public final void shapeToJson(JsonEncoder e) throws IOException {
        e.addInt(name() + shapePostfix, getShape());
    }

    @Override
    public final void setValue(int[] value) {
        synchronized(model) {
            if(set)
                throw new SandwoodVariableSetException(
                        "Unable to set the value of " + name + " as it has already been set.");
            if(shapeSet)
                throw new SandwoodVariableSetException(
                        "Unable to set the value of " + name + " as its length has already been set.");
            super.setValue(value);
            shapeSet = true;
        }
    }

    public abstract int getShape();

    protected abstract void setShapeInternal(int length);
}
