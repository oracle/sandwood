/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.numericTools;

import static java.lang.Math.PI;
import static java.lang.Math.log;

/**
 * Class providing a method to generate an approximate value of factorials.
 * <p>
 * Advice on calculating the log factorial taken from this website
 * <a href= "https://www.johndcook.com/blog/2010/08/16/how-to-compute-log-factorial">
 * https://www.johndcook.com/blog/2010/08/16/how-to-compute-log-factorial/</a>
 *
 */
public class ApproximateFactorial {

    /**
     * Private constructor as this class should never be instantiated.
     */
    private ApproximateFactorial() {}

    private static final double[] logFacLookup = new double[257];
    private static final double halfLogTwoPi = 0.5 * log(2 * PI);
    static {
        logFacLookup[0] = log(1);
        double value = 0;
        for(int i = 1; i <= 256; i++) {
            value += log(i);
            logFacLookup[i] = value;
        }
    }

    /**
     * Method to calculate an approximation of the log of the factorial of the parameter value.
     * <p>
     * For values less than or equal to 256 the value is returned from cached values. For larger values it is calculated
     * using Stirling's approximation. The error is less than 1/(360*x^3) so for x > 256 1.6X10^-10
     *
     * @param value The value to calculate the factorial for.
     * @return An approximation of the factorial of value.
     */
    public static double approxLogFac(int value) {
        if(value <= 256)
            return logFacLookup[value];
        else {
            int x = value + 1;
            return (x - 0.5) * log(x) - x + halfLogTwoPi + 1 / (12 * x);
        }
    }
}
