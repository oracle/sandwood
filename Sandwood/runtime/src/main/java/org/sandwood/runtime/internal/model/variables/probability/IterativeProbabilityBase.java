/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.variables.probability;

import org.sandwood.runtime.internal.model.util.Allocator;
import org.sandwood.runtime.internal.model.util.BaseType;
import org.sandwood.runtime.internal.model.variables.IteratedCurrentProbability;
import org.sandwood.runtime.internal.numericTools.LogSumExponential;

public abstract class IterativeProbabilityBase<A> implements IteratedVariableProbability<A> {

    private boolean probabilityComputed = false;
    protected final int arrayDimensions;
    protected A logProbability;
    protected Object logProbabilitySum = null;
    protected final IteratedCurrentProbability<A> c;

    public IterativeProbabilityBase(IteratedCurrentProbability<A> c, int arrayDimensions) {
        this.c = c;
        this.arrayDimensions = arrayDimensions;
    }

    @Override
    public final boolean probabilityComputed() {
        return probabilityComputed;
    }

    @Override
    public final A getProbability() {
        return convertProbability(logProbability, 0);
    }

    @Override
    public final A getLogProbability() {
        return logProbability;
    }

    @Override
    public final void startLogProbability() {
        if(logProbabilitySum == null) {
            A template = c.getCurrentLogProbability();
            startLogProbabilityInternal(template);
        } else {
            startLogProbabilityInternal();
        }
        probabilityComputed = false;
    }

    protected void startLogProbabilityInternal(A template) {
        logProbability = constructInitialProbability(template, 0);
        logProbabilitySum = constructLogSum(template);
    }

    protected void startLogProbabilityInternal() {
        clearInitialProbability(logProbability);
        clearProbabilitySum(logProbabilitySum);
    }

    @Override
    public final void completeLogProbability(int iterations) {
        completeLogProbabilityInternal(iterations);
        probabilityComputed = true;
    }

    protected abstract void completeLogProbabilityInternal(int iterations);

    /**
     * Method to take an array of double of unknown dimension, and copy it.
     * 
     * @param template
     * @return
     */
    private <B> B constructInitialProbability(B template, int depth) {
        int currentDimension = arrayDimensions - depth;
        if(currentDimension > 1) {
            Object[] objTemplate = (Object[]) template;
            int length = objTemplate.length;
            Object[] output = Allocator.allocate(BaseType.DOUBLE, length, currentDimension);
            for(int i = 0; i < length; i++)
                output[i] = constructInitialProbability(objTemplate[i], depth + 1);
            return (B) output;
        } else {
            double[] doubleTemplate = (double[]) template;
            return (B) new double[doubleTemplate.length];
        }
    }

    private void clearInitialProbability(Object o) {
        if(o instanceof Object[]) {
            Object[] objArray = (Object[]) o;
            int length = objArray.length;
            for(int i = 0; i < length; i++)
                clearInitialProbability(objArray[i]);
        } else {
            double[] doubleArray = (double[]) o;
            int length = doubleArray.length;
            for(int i = 0; i < length; i++)
                doubleArray[i] = 0;
        }
    }

    private <B> B convertProbability(B logProbabilities, int depth) {
        int currentDimension = arrayDimensions - depth;
        if(currentDimension > 1) {
            Object[] objArray = (Object[]) logProbabilities;
            int length = objArray.length;
            Object[] output = Allocator.allocate(BaseType.DOUBLE, length, currentDimension);
            for(int i = 0; i < length; i++)
                output[i] = convertProbability(objArray[i], depth + 1);
            return (B) output;
        } else {
            double[] leafArray = (double[]) logProbabilities;
            int size = leafArray.length;
            double[] output = new double[size];
            for(int i = 0; i < size; i++)
                output[i] = Math.exp(leafArray[i]);
            return (B) output;
        }
    }

    /**
     * Method to take an array of double of unknown dimension, and copy it.
     * 
     * @param template
     * @return
     */
    protected Object constructLogSum(Object template) {
        if(template instanceof Object[]) {
            Object[] objTemplate = (Object[]) template;
            int length = objTemplate.length;
            Object[] output = new Object[length];
            for(int i = 0; i < length; i++)
                output[i] = constructLogSum(objTemplate[i]);
            return output;
        } else {
            double[] doubleTemplate = (double[]) template;
            LogSumExponential[] l = new LogSumExponential[doubleTemplate.length];
            for(int i = 0; i < l.length; i++)
                l[i] = new LogSumExponential();
            return l;
        }
    }

    protected void clearProbabilitySum(Object o) {
        if(o instanceof Object[]) {
            Object[] objTemplate = (Object[]) o;
            int length = objTemplate.length;
            for(int i = 0; i < length; i++)
                clearProbabilitySum(objTemplate[i]);
        } else {
            LogSumExponential[] l = (LogSumExponential[]) o;
            for(int i = 0; i < l.length; i++)
                l[i].clear();
        }
    }

}