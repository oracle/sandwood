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
import org.sandwood.runtime.internal.model.util.BaseType;
import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.variables.ObservedObjectArrayShapeable;

public abstract class ObservedObjectArrayShapeableInternal<A, B> extends ObservedObjectArrayInternal<A>
        implements ObservedObjectArrayShapeable<A, B>, ObservedVariableShapeableInternal<A[]> {
    protected boolean shapeSet = false;

    public ObservedObjectArrayShapeableInternal(Model model, String name, BaseType baseType, int arrayDimension) {
        super(model, name, baseType, arrayDimension);
    }

    @Override
    public final boolean shapeSet() {
        synchronized(model) {
            return shapeSet;
        }
    }

    @Override
    public final void setShape(B shape) {
        synchronized(model) {
            if(shapeSet)
                throw new SandwoodVariableSetException(
                        "Unable to set the shape of " + name + " as its shape has already been set.");
            setShapeInternal(shape);
            shapeSet = true;
            set = false;
        }
    }

    @Override
    public final void shapeToJson(JsonEncoder e) throws IOException {
        e.addObject(name() + shapePostfix, getShape());
    }

    @Override
    public final void setValue(A[] value) {
        synchronized(model) {
            if(set)
                throw new SandwoodVariableSetException(
                        "Unable to set the value of " + name + " as it has already been set.");
            if(shapeSet)
                throw new SandwoodVariableSetException(
                        "Unable to set the value of " + name + " as its shape has already been set.");
            super.setValue(value);
            shapeSet = true;
        }
    }

    protected abstract void setShapeInternal(B shape);
}
