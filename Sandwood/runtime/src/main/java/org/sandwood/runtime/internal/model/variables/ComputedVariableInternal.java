/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.variables;

import org.sandwood.runtime.exceptions.SandwoodRuntimeException;
import org.sandwood.runtime.exceptions.SandwooodRetentionPolicyException;
import org.sandwood.runtime.internal.model.variables.probability.ProbabilityType;
import org.sandwood.runtime.internal.model.variables.probability.SkippableProbability;
import org.sandwood.runtime.internal.model.variables.probability.UnskippableProbability;
import org.sandwood.runtime.internal.model.variables.probability.VariableProbability;
import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.RetentionPolicy;
import org.sandwood.runtime.model.variables.ComputedVariable;

public abstract class ComputedVariableInternal
        implements ComputedVariable, HasProbabilityInternal, JsonVariable, CurrentProbability {
    protected RetentionPolicy p = RetentionPolicy.SAMPLE;

    // A value is stored in this object.
    protected boolean valueComputed = false;
    // A value is set in the model core
    protected boolean valueSet = false;

    private final VariableProbability prob;

    protected final String name;
    protected final Model model;
    private final boolean isSettable;
    private final boolean isSample;
    private final boolean isPrivate;

    protected int i;

    public ComputedVariableInternal(Model model, String name, boolean isSettable, boolean isSample, boolean isPrivate,
            ProbabilityType probType) {
        this.name = name;
        this.model = model;
        this.isSettable = isSettable;
        this.isSample = isSample;
        this.isPrivate = isPrivate;
        if(isPrivate)
            // This object should never be accessed in a private variable.
            prob = null;
        else {
            switch(probType) {
                case SKIPPABLE:
                    prob = new SkippableProbability(this);
                    break;
                case UNSKIPPABLE:
                    prob = new UnskippableProbability(this);
                    break;
                default:
                    throw new SandwoodRuntimeException("Unexpected probability type.");
            }
        }
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
    public final boolean isSettable() {
        return isSettable;
    }

    public final boolean isSample() {
        return isSample;
    }
    
    public final boolean isPrivate() {
        return isPrivate;
    }

    @Override
    public final boolean probabilityComputed() {
        synchronized(model) {
            return prob.probabilityComputed();
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
                throw new SandwooodRetentionPolicyException(
                        "Unable to set the retention policy to NA. This is not a retention policy "
                                + "variables can be assigned, it can only be returned by variables if their "
                                + "value is fixed.");
            if(p != this.p) {
                switch(p) {
                    case MAP:
                        this.p = p;
                        clearSample();
                        break;
                    case MAP_AND_SAMPLE:
                        if(isPrivate)
                            this.p = RetentionPolicy.MAP;
                        else
                            this.p = p;
                        break;
                    case NONE:
                        clearMap();
                        clearSample();
                        this.p = p;
                        break;
                    case SAMPLE:
                        if(isPrivate)
                            this.p = RetentionPolicy.NONE;
                        else
                            this.p = p;
                        clearMap();
                        break;
                    default:
                        break;
                }
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
            if(!isPrivate && isFixed() == Immutability.FIXED)
                return RetentionPolicy.NA;
            else
                return p;
        }
    }

    @Override
    public final double getProbability() {
        synchronized(model) {
            return prob.getProbability();
        }
    }

    @Override
    public final double getLogProbability() {
        synchronized(model) {
            return prob.getLogProbability();
        }
    }

    @Override
    public final void startLogProbability() {
        prob.startLogProbability();
    }

    @Override
    public final void ingestLogProbability() {
        prob.ingestLogProbability();
    }

    @Override
    public final void completeLogProbability(int iterations) {
        prob.completeLogProbability(iterations);
    }

    public abstract void initializeSamples(int iterations);

    public abstract void ingestMap();

    public abstract void ingestSample();

    protected abstract void clearMap();

    protected abstract void clearSample();

    public void computeComplete() {
        valueComputed = true;
    }

    /**
     * A function that will throw an exception if the variable cannot be set by a user.
     */
    protected void testSettable() {}
}