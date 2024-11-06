/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.numericTools;

import org.apache.commons.numbers.gamma.Erf;
import org.apache.commons.numbers.gamma.InverseErf;

public class Gaussian {
    private static final double root2 = Math.sqrt(2);
    private static final double oneOverRoot2 = 1 / root2;

    /**
     * A method for calculating the CDF of a Gaussian distribution with mean 0 and variance 1.
     * 
     * @param value The value to calculate the CDF for.
     * @return The CDF for this value.
     */
    public static final double cdf(double value) {
        return 0.5 * (1 + Erf.value(value * oneOverRoot2));
    }

    /**
     * A method to calculate the inverse cdf function of a Gaussian distribution with mean set to 0 and variance set to
     * 1.
     * 
     * @param cdfValue The value between 0 and 1 to calculate for.
     * @return The variable in the Guassian distribution that corresponds to a CDF value of cdfValue.
     */
    public static final double inverseCDF(double cdfValue) {
        return root2 * InverseErf.value(2 * cdfValue - 1);
    }
}
