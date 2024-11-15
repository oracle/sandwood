/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.json;

import java.io.File;
import java.io.IOException;

import org.sandwood.runtime.internal.model.variables.ComputedVariableInternal;
import org.sandwood.runtime.internal.model.variables.ObservedVariableInternal;
import org.sandwood.runtime.internal.model.variables.ObservedVariableShapeableInternal;
import org.sandwood.runtime.model.Model;

public class JsonModelEncoder extends JsonModel {
    private final JsonEncoder e;

    public JsonModelEncoder(File output, Model model) throws IOException {
        e = new JsonEncoder(output);
        e.addStartObject();
        e.addString(modelCodeName, model.getModelCode());
    }

    public void close() throws IOException {
        e.addEndObject();
        e.close();
    }

    public void addVariable(ComputedVariableInternal v) throws IOException {
        if(v.isSet())
            v.toJson(e);
    }

    public void addVariable(ObservedVariableInternal v) throws IOException {
        if(v.isSet())
            v.toJson(e);

    }

    public void addShapedVariable(ObservedVariableShapeableInternal<?> v) throws IOException {
        if(v.isSet())
            v.toJson(e);
        else if(v.shapeSet())
            v.shapeToJson(e);
    }
}
