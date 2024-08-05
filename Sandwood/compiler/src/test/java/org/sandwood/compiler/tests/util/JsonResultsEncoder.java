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
import java.util.Map;
import java.util.PriorityQueue;

import org.sandwood.compiler.tests.exceptions.SandwoodTestException;
import org.sandwood.runtime.internal.json.JsonEncoder;

public class JsonResultsEncoder {

    public static void writeData(String filename, Map<String, Object> outputs) throws IOException {
        File file = new File(filename);
        File dir = file.getParentFile();
        dir.mkdirs();
        JsonEncoder e = new JsonEncoder(file);

        e.addStartObject();
        PriorityQueue<String> p = new PriorityQueue<>(outputs.keySet());
        while(!p.isEmpty()) {
            String name = p.poll();
            Object o = outputs.get(name);
            e.addField(name);
            e.addStartObject();
            e.addString("type", getType(o));
            e.addObject("value", o);
            e.addEndObject();
        }

        e.addEndObject();
        e.close();
    }

    private static String getType(Object o) {
        if(o instanceof Integer)
            return "int";
        else if(o instanceof Double)
            return "double";
        else if(o instanceof Boolean)
            return "boolean";
        else if(o instanceof Object[])
            return getType(((Object[]) o)[0]) + "[]"; // This will fail for empty arrays.
        else if(o instanceof double[])
            return "double[]";
        else if(o instanceof int[])
            return "int[]";
        else if(o instanceof boolean[])
            return "boolean[]";

        throw new SandwoodTestException("Unknown type");
    }
}
