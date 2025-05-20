/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.variables.probability;

import org.sandwood.runtime.internal.model.variables.CurrentProbability;
import org.sandwood.runtime.internal.numericTools.LogSumExponential;

public final class UnskippableProbability implements VariableProbability {

    private double logProbability;
    private final LogSumExponential logProbabilitySum = new LogSumExponential();
    private boolean probabilityComputed = false;

    private final CurrentProbability c;

    public UnskippableProbability(CurrentProbability c) {
        this.c = c;
    }

    @Override
    public final void startLogProbability() {
        logProbabilitySum.clear();
        probabilityComputed = false;
    }

    @Override
    public final void ingestLogProbability() {
        logProbabilitySum.add(c.getCurrentLogProbability());
    }

    @Override
    public final void completeLogProbability(int iterations) {
        logProbability = logProbabilitySum.getSum() - Math.log(iterations);
        probabilityComputed = true;
    }

    @Override
    public final double getProbability() {
        return Math.exp(logProbability);
    }

    @Override
    public final double getLogProbability() {
        return logProbability;
    }

    @Override
    public final boolean probabilityComputed() {
        return probabilityComputed;
    }
}
