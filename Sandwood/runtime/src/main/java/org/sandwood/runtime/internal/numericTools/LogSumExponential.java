/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.numericTools;

/**
 * Class implementing the log sum exponential technique for summing log values. This protects against under and overflow
 * issues. This should not be used by user code.
 */
public class LogSumExponential {

    double sum;
    double offset;
    boolean first = true;

    /**
     * Constructor for a log sum exponential class. This class is used for summing numbers in log format.
     */
    public LogSumExponential() {}

    /**
     * Reset the sum object so that it can be used again.
     */
    public final void clear() {
        first = true;
    }

    /**
     * Add a value to the sum
     * 
     * @param v The value in log space to be added to the sum.
     */
    public final void add(double v) {
        if(first) {
            sum = 1.0;
            offset = v;
            first = false;
        } else {
            // No need to add values of zero, and trying can result in NaN if the offset is
            // zero too.
            if(v != Double.NEGATIVE_INFINITY) {
                if(offset < v) {
                    double value = Math.log(sum) + offset;

                    sum = 1.0;
                    if(value < v) {
                        offset = v;
                        v = value; // If the element is the new offset, the previous sum becomes the element.
                    } else
                        offset = value;
                }
                double difference = v - offset;
                double expDifference = Math.exp(difference);
                sum += expDifference;
            }
        }
    }

    /**
     * Method to get the sum of all the values added.
     * 
     * @return The sum in log space of all the log space values added.
     */
    public final double getSum() {
        if(first)
            return Double.NEGATIVE_INFINITY;
        else {
            double result = Math.log(sum) + offset;
            assert result != Double.POSITIVE_INFINITY;
            return result;
        }
    }

    /**
     * Method for performing Log Sum Exponential on an array of inputs.
     * 
     * @param inputs An array of values in log space.
     * @return The sum of the values, also in log space.
     */
    public static double lse(double[] inputs) {
        int noElements = inputs.length;
        double sum = 0;
        double offset = inputs[0];

        for(int i = 1; i < noElements; i++)
            if(offset < inputs[i])
                offset = inputs[i];

        // If the biggest value is negative infinity all the values are
        // negative infinity. Carrying on with the calculation can trigger
        // a NaN value as it is effectively divide by zero.
        if(offset == Double.NEGATIVE_INFINITY)
            return offset;
        else {
            for(int i = 0; i < noElements; i++)
                sum += Math.exp(inputs[i] - offset);
            return Math.log(sum) + offset;
        }
    }
}
