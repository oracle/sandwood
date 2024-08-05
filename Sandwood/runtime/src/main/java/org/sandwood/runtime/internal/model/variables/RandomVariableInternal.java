/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.variables;

import org.sandwood.runtime.internal.numericTools.LogSumExponential;
import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.variables.RandomVariable;

public abstract class RandomVariableInternal implements HasProbabilityInternal, RandomVariable {

    private double logProbability;
    private final LogSumExponential logProbabilitySum = new LogSumExponential();
    private boolean probabilityComputed = false;

    private final String name;
    private final Model model;

    public RandomVariableInternal(Model model, String name) {
        this.model = model;
        this.name = name;
    }

    @Override
    public final String name() {
        return name;
    }

    @Override
    public final boolean probabilityComputed() {
        synchronized(model) {
            return probabilityComputed;
        }
    }

    @Override
    public final double getProbability() {
        synchronized(model) {
            return Math.exp(logProbability);
        }
    }

    @Override
    public final double getLogProbability() {
        synchronized(model) {
            return logProbability;
        }
    }

    @Override
    public final void startLogProbability() {
        logProbabilitySum.clear();
        probabilityComputed = false;
    }

    @Override
    public final void ingestLogProbability() {
        logProbabilitySum.add(getCurrentLogProbability());
    }

    public abstract double getCurrentLogProbability();

    @Override
    public final void completeLogProbability(int iterations) {
        logProbability = logProbabilitySum.getSum() - Math.log(iterations);
        probabilityComputed = true;
    }
}
