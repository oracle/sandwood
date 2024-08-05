/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.sandwood.compiler.tests.exceptions.SandwoodTestException;
import org.sandwood.runtime.exceptions.SandwoodJsonException;
import org.sandwood.runtime.internal.json.JsonDecoder;
import org.sandwood.runtime.internal.model.util.BaseType;

public class JsonResultsDecoder {

    public static Map<String, Object> readData(String filename) throws SandwoodJsonException, IOException {
        JsonDecoder d = new JsonDecoder(new File(filename));

        Map<String, Object> results = new HashMap<>();
        d.enterObject();

        while(!d.isObjectEnd()) {

            String name = d.getFieldName();
            d.enterObject();

            // Read type;
            String s = d.getFieldName();
            if(!s.equals("type"))
                throw new SandwoodJsonException("Expected the field \"type\"");
            String type = d.getString();
            String[] parts = type.split("\\[");
            int depth = parts.length - 1;

            // Read the value field
            s = d.getFieldName();
            if(!s.equals("value"))
                throw new SandwoodJsonException("Expected the field \"value\"");
            switch(parts[0]) {
                case "int":
                    if(depth == 0)
                        results.put(name, d.getInt());
                    else if(depth == 1)
                        results.put(name, d.getIntArray());
                    else
                        results.put(name, d.getObjectArray(BaseType.INT, depth));
                    break;
                case "double":
                    if(depth == 0)
                        results.put(name, d.getDouble());
                    else if(depth == 1)
                        results.put(name, d.getDoubleArray());
                    else
                        results.put(name, d.getObjectArray(BaseType.DOUBLE, depth));
                    break;
                case "boolean":
                    if(depth == 0)
                        results.put(name, d.getBoolean());
                    else if(depth == 1)
                        results.put(name, d.getBooleanArray());
                    else
                        results.put(name, d.getObjectArray(BaseType.BOOLEAN, depth));
                    break;
                default:
                    d.close();
                    throw new SandwoodTestException("Unknown type");
            }
            d.leaveObject();
        }
        d.leaveObject();
        d.close();

        return results;
    }
}
