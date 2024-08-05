/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.json;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.sandwood.runtime.exceptions.SandwoodJsonException;
import org.sandwood.runtime.internal.model.variables.ComputedVariableInternal;
import org.sandwood.runtime.internal.model.variables.ObservedVariableInternal;
import org.sandwood.runtime.internal.model.variables.ObservedVariableShapeableInternal;

public class JsonModelDecoder extends JsonModel {

    private final File file;
    private final JsonDecoder d;

    public JsonModelDecoder(File file) throws IOException {
        this.file = file;
        d = new JsonDecoder(file);
    }

    public void parse(Map<String, ObservedVariableInternal> modelInputs,
            Map<String, ObservedVariableInternal> regularObservedVariables,
            Map<String, ObservedVariableShapeableInternal<?>> shapeableObservedVariables,
            Map<String, ComputedVariableInternal> computedVariables, String modelCode)
            throws IOException, SandwoodJsonException {

        Set<String> seen = new HashSet<>();
        boolean codeChecked = false;

        d.enterObject();

        while(!d.isObjectEnd()) {
            String name = d.getFieldName();

            // Check each field is only seen once.
            if(seen.contains(name))
                throw new SandwoodJsonException("Field " + name + " appears in the model twice.");
            seen.add(name);

            // Parse the field value.
            if(modelInputs.containsKey(name)) {
                ObservedVariableInternal v = modelInputs.get(name);
                v.fromJSON(d);
            } else if(regularObservedVariables.containsKey(name)) {
                ObservedVariableInternal v = regularObservedVariables.get(name);
                v.fromJSON(d);
            } else if(shapeableObservedVariables.containsKey(name)) {
                ObservedVariableShapeableInternal<?> v = shapeableObservedVariables.get(name);
                v.fromJSON(d);
            } else if(computedVariables.containsKey(name)) {
                ComputedVariableInternal v = computedVariables.get(name);
                if(v.isSample)
                    v.fromJSON(d);
                else
                    throw new SandwoodJsonException("Tried to set non sample value " + name);
            } else if(name.equals(modelCodeName)) {
                compareModelCode(modelCode);
                codeChecked = true;
            }
        }

        d.leaveObject();

        d.close();

        if(!codeChecked) {
            throw new SandwoodJsonException("Model code was not present in the Json file: " + file.getName());
        }
    }

    private void compareModelCode(String modelCode) throws SandwoodJsonException, IOException {
        if(!d.getString().equals(modelCode))
            throw new SandwoodJsonException("File " + file.getName() + " was not constructed with this model.");
    }
}
