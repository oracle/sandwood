/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.variables.probability;

import org.sandwood.runtime.internal.model.util.Allocator;
import org.sandwood.runtime.internal.model.util.BaseType;
import org.sandwood.runtime.internal.model.variables.IteratedCurrentProbability;
import org.sandwood.runtime.internal.numericTools.LogSumExponential;

public final class IteratedSkippableProbability<A> implements IteratedVariableProbability<A> {

    private final int arrayDimensions;
    private boolean probabilityComputed = false;

    private A logProbability;
    private Object logProbabilitySum = null;

    private final IteratedCurrentProbability<A> c;

    public IteratedSkippableProbability(IteratedCurrentProbability<A> c, int arrayDimensions) {
        this.c = c;
        this.arrayDimensions = arrayDimensions;
    }

    @Override
    public final boolean probabilityComputed() {
        return probabilityComputed;
    }

    @Override
    public final A getProbability() {
        return (A) convertProbability(logProbability, 0);
    }

    @Override
    public final A getLogProbability() {
        return logProbability;
    }

    @Override
    public final void startLogProbability() {
        A template = c.getCurrentLogProbability();
        if(logProbabilitySum == null) {
            logProbability = constructInitialProbability(template, 0);
            logProbabilitySum = constructLogSum(template);
        } else {
            clearInitialProbability(logProbability);
            clearProbabilitySum(logProbabilitySum);
        }
        probabilityComputed = false;
    }

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

    private Object convertProbability(Object logProbabilities, int depth) {
        int currentDimension = arrayDimensions - depth;
        if(currentDimension > 1) {
            Object[] objArray = (Object[]) logProbabilities;
            int length = objArray.length;
            Object[] output = Allocator.allocate(BaseType.DOUBLE, length, currentDimension);
            for(int i = 0; i < length; i++)
                output[i] = convertProbability(objArray[i], depth + 1);
            return output;
        } else {
            double[] leafArray = (double[]) logProbabilities;
            int size = leafArray.length;
            double[] output = new double[size];
            for(int i = 0; i < size; i++)
                output[i] = Math.exp(leafArray[i]);
            return output;
        }
    }

    /**
     * Method to take an array of double of unknown dimension, and copy it.
     * 
     * @param template
     * @return
     */
    private Object constructLogSum(Object template) {
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

    private void clearProbabilitySum(Object o) {
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

    @Override
    public final void ingestLogProbability() {
        A newValue = c.getCurrentLogProbability();
        addNewLogValue(logProbabilitySum, newValue);
    }

    private void addNewLogValue(Object currentValue, Object newValue) {
        if(newValue instanceof Object[]) {
            Object[] currentV = (Object[]) currentValue;
            Object[] newV = (Object[]) newValue;
            for(int i = 0; i < currentV.length; i++)
                addNewLogValue(currentV[i], newV[i]);
        } else {
            LogSumExponential[] currentV = (LogSumExponential[]) currentValue;
            double[] newV = (double[]) newValue;
            for(int i = 0; i < currentV.length; i++)
                currentV[i].add(newV[i]);
        }
    }

    @Override
    public final void completeLogProbability(int iterations) {
        completeLogProbability(logProbabilitySum, logProbability, Math.log(iterations));
        probabilityComputed = true;
    }

    private void completeLogProbability(Object logSum, Object output, double logIterations) {
        if(output instanceof Object[]) {
            Object[] currentO = (Object[]) output;
            Object[] currentLS = (Object[]) logSum;
            for(int i = 0; i < currentO.length; i++)
                completeLogProbability(currentLS[i], currentO[i], logIterations);
        } else {
            double[] currentO = (double[]) output;
            LogSumExponential[] currentL = (LogSumExponential[]) logSum;
            for(int i = 0; i < currentO.length; i++)
                currentO[i] = currentL[i].getSum() - logIterations;
        }
    }
}
