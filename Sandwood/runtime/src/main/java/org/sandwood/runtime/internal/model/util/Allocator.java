/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.util;

import org.sandwood.common.exceptions.SandwoodException;

/**
 * Utility class for constructing arrays with the correct constructor. This is only used internally, and user code
 * should not use this.
 */
public class Allocator {

    /**
     * A private constructor as this class should never be instantiated.
     */
    private Allocator() {}

    /**
     * Method for constructing arrays of different types.
     * 
     * @param <A>            The type of the array to construct.
     * @param baseType       The base type of the array to construct.
     * @param size           The size of the array.
     * @param arrayDimension The number of dimensions that the array should be made of.
     * @return Returns an array of the correct size and shape.
     */
    @SuppressWarnings("unchecked")
    public static <A> A allocate(BaseType baseType, int size, int arrayDimension) {
        switch(baseType) {
            case DOUBLE:
                switch(arrayDimension) {
                    case 1:
                        return (A) new double[size];
                    case 2:
                        return (A) new double[size][];
                    case 3:
                        return (A) new double[size][][];
                    case 4:
                        return (A) new double[size][][][];
                    case 5:
                        return (A) new double[size][][][][];
                    case 6:
                        return (A) new double[size][][][][][];
                    case 7:
                        return (A) new double[size][][][][][][];
                    case 8:
                        return (A) new double[size][][][][][][][];
                    case 9:
                        return (A) new double[size][][][][][][][][];
                }
                break;
            case INT:
                switch(arrayDimension) {
                    case 1:
                        return (A) new int[size];
                    case 2:
                        return (A) new int[size][];
                    case 3:
                        return (A) new int[size][][];
                    case 4:
                        return (A) new int[size][][][];
                    case 5:
                        return (A) new int[size][][][][];
                    case 6:
                        return (A) new int[size][][][][][];
                    case 7:
                        return (A) new int[size][][][][][][];
                    case 8:
                        return (A) new int[size][][][][][][][];
                    case 9:
                        return (A) new int[size][][][][][][][][];
                }
                break;
            case BOOLEAN:
                switch(arrayDimension) {
                    case 1:
                        return (A) new boolean[size];
                    case 2:
                        return (A) new boolean[size][];
                    case 3:
                        return (A) new boolean[size][][];
                    case 4:
                        return (A) new boolean[size][][][];
                    case 5:
                        return (A) new boolean[size][][][][];
                    case 6:
                        return (A) new boolean[size][][][][][];
                    case 7:
                        return (A) new boolean[size][][][][][][];
                    case 8:
                        return (A) new boolean[size][][][][][][][];
                    case 9:
                        return (A) new boolean[size][][][][][][][][];
                }
                break;
            default:
                break;
        }
        throw new SandwoodException("Unable to create array of this type and dimensions: Type: " + baseType
                + " dimensions: " + arrayDimension);
    }
}
