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

import org.sandwood.common.exceptions.SandwoodException;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;

public class JsonEncoder extends Json {

    private final JsonGenerator generator;

    public JsonEncoder(File output) throws IOException {
        generator = factory.createGenerator(output, JsonEncoding.UTF8);
    }

    public void addStartObject() throws IOException {
        generator.writeStartObject();
    }

    public void addEndObject() throws IOException {
        generator.writeEndObject();
    }

    public void close() throws IOException {
        generator.close();
    }

    public void addField(String name) throws IOException {
        generator.writeFieldName(name);
    }

    public void addString(String name, String s) throws IOException {
        generator.writeFieldName(name);
        generator.writeString(s);
    }

    public void addInt(String name, int i) throws IOException {
        generator.writeFieldName(name);
        generator.writeNumber(i);
    }

    public void addDouble(String name, double d) throws IOException {
        generator.writeFieldName(name);
        generator.writeNumber(d);
    }

    public void addBoolean(String name, boolean b) throws IOException {
        generator.writeFieldName(name);
        generator.writeBoolean(b);
    }

    public void addObject(String name, Object o) throws IOException {
        generator.writeFieldName(name);
        writeObject(o);
    }

    private void writeBooleanArray(boolean[] ab) throws IOException {
        generator.writeStartArray();
        for(boolean element:ab)
            generator.writeBoolean(element);
        generator.writeEndArray();
    }

    private void writeDoubleArray(double[] ad) throws IOException {
        generator.writeStartArray();
        for(double element:ad)
            generator.writeNumber(element);
        generator.writeEndArray();
    }

    private void writeIntArray(int[] ai) throws IOException {
        generator.writeStartArray();
        for(int element:ai)
            generator.writeNumber(element);
        generator.writeEndArray();
    }

    private void writeObjectArray(Object[] ao) throws IOException {
        generator.writeStartArray();
        for(Object element:ao)
            writeObject(element);
        generator.writeEndArray();
    }

    private void writeObject(Object o) throws IOException {
        if(o instanceof Object[]) {
            writeObjectArray((Object[]) o);
        } else if(o instanceof double[]) {
            writeDoubleArray((double[]) o);
        } else if(o instanceof int[]) {
            writeIntArray((int[]) o);
        } else if(o instanceof boolean[]) {
            writeBooleanArray((boolean[]) o);
        } else if(o instanceof Double) {
            generator.writeNumber(((Double) o).doubleValue());
        } else if(o instanceof Integer) {
            generator.writeNumber(((Integer) o).intValue());
        } else if(o instanceof Boolean) {
            generator.writeBoolean(((Boolean) o).booleanValue());
        } else
            throw new SandwoodException("Unknown object type.");
    }
}
