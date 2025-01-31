/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.variables;

import org.sandwood.runtime.exceptions.RetentionPolicyException;
import org.sandwood.runtime.internal.numericTools.LogSumExponential;
import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.RetentionPolicy;
import org.sandwood.runtime.model.variables.ComputedVariable;

public abstract class ComputedVariableInternal
        implements ComputedVariable, HasProbabilityInternal, JsonVariable, CurrentProbability {
    protected RetentionPolicy p = RetentionPolicy.SAMPLE;

    private boolean valueComputed = false;
    protected boolean valueSet = false;

    private double logProbability;
    private final LogSumExponential logProbabilitySum = new LogSumExponential();
    private boolean probabilityComputed = false;

    protected final String name;
    protected final Model model;
    public final boolean isSample;

    protected int i;

    public ComputedVariableInternal(Model model, String name, boolean isSample) {
        this.name = name;
        this.model = model;
        this.isSample = isSample;
    }

    @Override
    public final String name() {
        return name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see forGenerator.ComputedVariableInterface#isSet()
     */
    @Override
    public final boolean valueComputed() {
        synchronized(model) {
            return valueComputed;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see forGenerator.ComputedVariableInterface#isSet()
     */
    @Override
    public final boolean isSet() {
        synchronized(model) {
            return valueComputed || valueSet;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see forGenerator.ComputedVariableInterface#isSettable()
     */
    @Override
    public boolean isSettable() {
        return isSample;
    }

    @Override
    public final boolean probabilityComputed() {
        synchronized(model) {
            return probabilityComputed;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see forGenerator.ComputedVariableInterface#setRetentionPolicy(forGenerator. RetentionPolicy)
     */
    @Override
    public final void setRetentionPolicy(RetentionPolicy p) {
        synchronized(model) {
            if(p == RetentionPolicy.NA)
                throw new RetentionPolicyException(
                        "Unable to set the retention policy to NA. This is not a retention policy "
                                + "variables can be assigned, it can only be returned by variables if their "
                                + "value is fixed.");
            if(p != this.p) {
                this.p = p;
                valueComputed = false;
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see forGenerator.ComputedVariableInterface#getRetentionPolicy()
     */
    @Override
    public final RetentionPolicy getRetentionPolicy() {
        synchronized(model) {
            if(isFixed() == Immutability.FIXED)
                return RetentionPolicy.NA;
            else
                return p;
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

    @Override
    public final void completeLogProbability(int iterations) {
        logProbability = logProbabilitySum.getSum() - Math.log(iterations);
        probabilityComputed = true;
    }

    public abstract void initializeSamples(int iterations);

    public abstract void ingestMap();

    public abstract void ingestSample();

    public void computeComplete() {
        valueComputed = true;
    }

    /**
     * A function that will throw an exception if the variable cannot be set by a user.
     */
    protected void testSettable() {}
}