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

public final class SkippableProbability implements VariableProbability {

    private double logProbability;
    private final LogSumExponential logProbabilitySum = new LogSumExponential();
    private boolean probabilityComputed = false;
    private int count;

    private final CurrentProbability c;

    public SkippableProbability(CurrentProbability c) {
        this.c = c;
    }

    @Override
    public final void startLogProbability() {
        logProbabilitySum.clear();
        count = 0;
        probabilityComputed = false;
    }

    @Override
    public final void ingestLogProbability() {
        double p = c.getCurrentLogProbability();
        if(!Double.isNaN(p)) {
            logProbabilitySum.add(p);
            count++;
        }
    }

    @Override
    public final void completeLogProbability(int iterations) {
        logProbability = logProbabilitySum.getSum() - Math.log(count);
        probabilityComputed = true;
    }

    @Override
    public final double getProbability() {
        if(count == 0)
            return Double.NaN;
        else
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
