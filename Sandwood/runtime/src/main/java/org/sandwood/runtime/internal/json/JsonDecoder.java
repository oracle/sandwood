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
import java.util.ArrayList;
import java.util.List;

import org.sandwood.runtime.exceptions.SandwoodJsonException;
import org.sandwood.runtime.internal.model.util.Allocator;
import org.sandwood.runtime.internal.model.util.BaseType;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class JsonDecoder extends Json {

    private final JsonParser parser;

    public JsonDecoder(File file) throws IOException {
        parser = factory.createParser(file);
        parser.nextToken();
    }

    /**
     * Method to get a multidimensional array from a JSON object
     * 
     * @param <A>            The type of the element type of the array to be generated i.e. int[] for an array int[][].
     * @param baseType       The base type of the array. i.e. int for an array int[][].
     * @param arrayDimension The number of dimensions to the array.
     * @return The corresponding Java array.
     * @throws SandwoodJsonException An exception thrown if the structure of the JSON object doesn't match the provided
     *                               parameters.
     * @throws IOException           An exception thrown if there is an error reading the JSON file.
     */
    public final <A> A[] getObjectArray(BaseType baseType, int arrayDimension)
            throws SandwoodJsonException, IOException {
        // Get the start of the array.
        JsonToken t = parser.currentToken();
        switch(t) {
            case START_ARRAY:
                t = parser.nextToken();
                break;
            default:
                throw new SandwoodJsonException("Unexpected Json token " + t + ". A start array was expected.");
        }

        List<A> l = new ArrayList<>();

        if(arrayDimension > 2)
            while(parser.currentToken() != JsonToken.END_ARRAY)
                l.add((A) getObjectArray(baseType, arrayDimension - 1));
        else {
            switch(baseType) {
                case BOOLEAN:
                    while(parser.currentToken() != JsonToken.END_ARRAY)
                        l.add((A) getBooleanArray());
                    break;
                case DOUBLE:
                    while(parser.currentToken() != JsonToken.END_ARRAY)
                        l.add((A) getDoubleArray());
                    break;
                case INT:
                    while(parser.currentToken() != JsonToken.END_ARRAY)
                        l.add((A) getIntArray());
                    break;
                default:
                    throw new SandwoodJsonException("Unknown baseType " + baseType + " found for field.");
            }
        }

        // Move past the end array token.
        parser.nextToken();

        // Copy the values into an array and return.
        A[] array = Allocator.allocate(baseType, l.size(), arrayDimension);
        l.toArray(array);
        return array;
    }

    /**
     * Method to read an integer from the JSON file.
     * 
     * @return The integer read.
     * @throws IOException           Thrown if there is an issue reading the file.
     * @throws SandwoodJsonException Thrown if there is an issue with the JSON in the file.
     */
    public int getInt() throws IOException, SandwoodJsonException {
        JsonToken t = parser.currentToken();
        switch(t) {
            case VALUE_NUMBER_INT:
                int i = parser.getValueAsInt();
                parser.nextToken();
                return i;
            default:
                throw new SandwoodJsonException("Unexpected Json token " + t + ". An Integer was expected.");
        }
    }

    /**
     * Method to read an integer array from the JSON file.
     * 
     * @return The integer array read.
     * @throws IOException           Thrown if there is an issue reading the file.
     * @throws SandwoodJsonException Thrown if there is an issue with the JSON in the file.
     */
    public int[] getIntArray() throws IOException, SandwoodJsonException {
        // Get the start of the array.
        JsonToken t = parser.currentToken();
        switch(t) {
            case START_ARRAY:
                t = parser.nextToken();
                break;
            default:
                throw new SandwoodJsonException("Unexpected Json token " + t + ". A start array was expected.");
        }

        // Get the elements of the array.
        int s = 1024;
        int i = 0;
        int[] a = new int[s];
        while(t.equals(JsonToken.VALUE_NUMBER_INT)) {
            // If the array is full double its size.
            if(i == s) {
                int[] a2 = new int[s + s];
                if(s >= 0)
                    System.arraycopy(a, 0, a2, 0, s);
                a = a2;
                s = s + s;
            }

            // Populate the value and get the next token
            a[i++] = parser.getIntValue();
            t = parser.nextToken();
        }

        // Get the end of the array.
        switch(t) {
            case END_ARRAY:
                parser.nextToken();
                break;
            default:
                throw new SandwoodJsonException("Unexpected Json token " + t + ". An end array was expected.");
        }

        // Copy the values into an array and return.
        int[] toReturn = new int[i];
        System.arraycopy(a, 0, toReturn, 0, i);
        return toReturn;
    }

    /**
     * Method to read a double from the JSON file.
     * 
     * @return The double read.
     * @throws IOException           Thrown if there is an issue reading the file.
     * @throws SandwoodJsonException Thrown if there is an issue with the JSON in the file.
     */
    public double getDouble() throws IOException, SandwoodJsonException {
        JsonToken t = parser.currentToken();
        switch(t) {
            case VALUE_NUMBER_INT:
            case VALUE_NUMBER_FLOAT:
                double d = parser.getValueAsDouble();
                parser.nextToken();
                return d;
            default:
                throw new SandwoodJsonException("Unexpected Json token " + t + ". A double was expected.");
        }
    }

    /**
     * Method to read a double array from the JSON file.
     * 
     * @return The double array read.
     * @throws IOException           Thrown if there is an issue reading the file.
     * @throws SandwoodJsonException Thrown if there is an issue with the JSON in the file.
     */
    public double[] getDoubleArray() throws IOException, SandwoodJsonException {
        // Get the start of the array.
        JsonToken t = parser.currentToken();
        switch(t) {
            case START_ARRAY:
                t = parser.nextToken();
                break;
            default:
                throw new SandwoodJsonException("Unexpected Json token " + t + ". A start array was expected.");
        }

        // Get the elements of the array.
        int s = 1024;
        int i = 0;
        double[] d = new double[s];
        while(t.equals(JsonToken.VALUE_NUMBER_INT) || t.equals(JsonToken.VALUE_NUMBER_FLOAT)) {
            // If the array is full double its size.
            if(i == s) {
                double[] d2 = new double[s + s];
                if(s >= 0)
                    System.arraycopy(d, 0, d2, 0, s);
                d = d2;
                s = s + s;
            }

            // Populate the value and get the next token
            d[i++] = parser.getDoubleValue();
            t = parser.nextToken();
        }

        // Get the end of the array.
        switch(t) {
            case END_ARRAY:
                parser.nextToken();
                break;
            default:
                throw new SandwoodJsonException("Unexpected Json token " + t + ". An end array was expected.");
        }

        // Copy the values into an array and return.
        double[] toReturn = new double[i];
        System.arraycopy(d, 0, toReturn, 0, i);
        return toReturn;
    }

    /**
     * Method to read a boolean from the JSON file.
     * 
     * @return The boolean read.
     * @throws IOException           Thrown if there is an issue reading the file.
     * @throws SandwoodJsonException Thrown if there is an issue with the JSON in the file.
     */
    public boolean getBoolean() throws IOException, SandwoodJsonException {
        JsonToken t = parser.currentToken();
        switch(t) {
            case VALUE_TRUE:
                parser.nextToken();
                return true;
            case VALUE_FALSE:
                parser.nextToken();
                return false;
            default:
                throw new SandwoodJsonException("Unexpected Json token " + t + ". A boolean was expected.");
        }
    }

    /**
     * Method to read a boolean array from the JSON file.
     * 
     * @return The boolean array read.
     * @throws IOException           Thrown if there is an issue reading the file.
     * @throws SandwoodJsonException Thrown if there is an issue with the JSON in the file.
     */
    public boolean[] getBooleanArray() throws IOException, SandwoodJsonException {
        // Get the start of the array.
        JsonToken t = parser.currentToken();
        switch(t) {
            case START_ARRAY:
                t = parser.nextToken();
                break;
            default:
                throw new SandwoodJsonException("Unexpected Json token " + t + ". A start array was expected.");
        }

        // Get the elements of the array.
        int s = 1024;
        int i = 0;
        boolean[] b = new boolean[s];
        while(t.equals(JsonToken.VALUE_TRUE) || t.equals(JsonToken.VALUE_FALSE)) {
            // If the array is full double its size.
            if(i == s) {
                boolean[] b2 = new boolean[s + s];
                if(s >= 0)
                    System.arraycopy(b, 0, b2, 0, s);
                b = b2;
                s = s + s;
            }

            // Populate the value and get the next token
            b[i++] = t == JsonToken.VALUE_TRUE;
            t = parser.nextToken();
        }

        // Get the end of the array.
        switch(t) {
            case END_ARRAY:
                parser.nextTextValue();
                break;
            default:
                throw new SandwoodJsonException("Unexpected Json token " + t + ". An end array was expected.");
        }

        // Copy the values into an array and return.
        boolean[] toReturn = new boolean[i];
        System.arraycopy(b, 0, toReturn, 0, i);
        return toReturn;
    }

    /**
     * Method to read a String from the JSON file.
     * 
     * @return The String read.
     * @throws IOException           Thrown if there is an issue reading the file.
     * @throws SandwoodJsonException Thrown if there is an issue with the JSON in the file.
     */
    public String getString() throws SandwoodJsonException, IOException {
        JsonToken t = parser.getCurrentToken();
        switch(t) {
            case VALUE_STRING:
                String s = parser.getValueAsString();
                parser.nextToken();
                return s;
            default:
                throw new SandwoodJsonException("Unexpected JSON token " + t + " encountered, expected a string.");
        }
    }

    /**
     * Method to read a field name from the JSON file.
     * 
     * @return The field name read.
     * @throws IOException           Thrown if there is an issue reading the file.
     * @throws SandwoodJsonException Thrown if there is an issue with the JSON in the file.
     */
    public String getFieldName() throws IOException, SandwoodJsonException {
        JsonToken t = parser.currentToken();
        switch(t) {
            case FIELD_NAME:
                String s = parser.getCurrentName();
                parser.nextToken();
                return s;
            default:
                throw new SandwoodJsonException("Unexpected JSON token encountered, expected a field name.");
        }
    }

    public void enterObject() throws IOException, SandwoodJsonException {
        JsonToken t = parser.currentToken();
        switch(t) {
            case START_OBJECT:
                parser.nextToken();
                break;
            default:
                throw new SandwoodJsonException(
                        "Unexpected JSON token " + t + " encountered, expected the start of an object.");
        }
    }

    public void leaveObject() throws IOException, SandwoodJsonException {
        JsonToken t = parser.currentToken();
        switch(t) {
            case END_OBJECT:
                parser.nextToken();
                break;
            default:
                throw new SandwoodJsonException(
                        "Unexpected JSON token " + t + " encountered, expected the end of an object.");
        }
    }

    public boolean isObjectEnd() {
        return parser.currentToken() == JsonToken.END_OBJECT;
    }

    public void close() throws IOException, SandwoodJsonException {
        JsonToken t = parser.currentToken();
        if(t != null) {
            StringBuilder sb = new StringBuilder();
            while(t != null) {
                sb.append("\n" + t);
                t = parser.nextToken();
            }
            throw new SandwoodJsonException("Not all of the JSON file was read, remaining tokens are:" + sb);
        }
        parser.close();
    }
}
