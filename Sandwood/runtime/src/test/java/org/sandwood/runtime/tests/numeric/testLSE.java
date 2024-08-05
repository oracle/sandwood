/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.tests.numeric;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.sandwood.runtime.internal.numericTools.LogSumExponential;

public class testLSE {
    private static final double e = 0.000000001;
    private static final double offset = 1000 * Math.log(Double.MIN_NORMAL);

    @Test
    void lse() {
        double sum = 1.25;
        double[] values = getValues(35, sum);
        LogSumExponential lse = new LogSumExponential();
        for(double value:values)
            lse.add(value);
        double result = Math.exp(lse.getSum() - offset);
        assertTrue(sum + e > result && sum - e < result);
    }

    @Test
    void lseValues() {
        double sum = 1.25;
        double[] values = getValues(35, sum);
        double result = Math.exp(LogSumExponential.lse(values) - offset);
        assertTrue(sum + e > result && sum - e < result);
    }

    private double[] getValues(int length, double sum) {
        double[] values = new double[length];
        double r = 1000;
        double v = sum * (1 - r) / (1 - Math.pow(r, length));
        for(int i = 0; i < length; i++) {
            values[i] = Math.log(v) + offset;
            v *= r;
        }

        // Shuffle results so they are not monotonically increasing.
        Random rand = new Random(42);
        for(int i = 1; i < length; i++) {
            int p = rand.nextInt(i);
            double t = values[i];
            values[i] = values[p];
            values[p] = t;
        }

        return values;
    }
}