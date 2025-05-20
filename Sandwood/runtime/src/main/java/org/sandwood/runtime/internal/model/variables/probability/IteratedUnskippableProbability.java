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

public final class IteratedUnskippableProbability<A> implements IteratedVariableProbability<A> {

    private final int arrayDimensions;
    private boolean probabilityComputed = false;

    private A logProbability;
    private Object logProbabilitySum = null;
    private Object probabilityCount = null;

    private final IteratedCurrentProbability<A> c;

    public IteratedUnskippableProbability(IteratedCurrentProbability<A> c, int arrayDimensions) {
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
            probabilityCount = constructCount(template);
        } else {
            clearInitialProbability(logProbability);
            clearProbabilitySum(logProbabilitySum);
            clearCount(probabilityCount);
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

    private Object constructCount(Object template) {
        if(template instanceof Object[]) {
            Object[] objTemplate = (Object[]) template;
            int length = objTemplate.length;
            Object[] output = new Object[length];
            for(int i = 0; i < length; i++)
                output[i] = constructLogSum(objTemplate[i]);
            return output;
        } else {
            double[] doubleTemplate = (double[]) template;
            return new int[doubleTemplate.length];
        }
    }

    private void clearCount(Object o) {
        if(o instanceof Object[]) {
            Object[] objTemplate = (Object[]) o;
            int length = objTemplate.length;
            for(int i = 0; i < length; i++)
                clearProbabilitySum(objTemplate[i]);
        } else {
            int[] a = (int[]) o;
            for(int i = 0; i < a.length; i++)
                a[i] = 0;
        }
    }

    @Override
    public final void ingestLogProbability() {
        A newValue = c.getCurrentLogProbability();
        addNewLogValue(logProbabilitySum, probabilityCount, newValue);
    }

    private void addNewLogValue(Object currentValue, Object currentCount, Object newValue) {
        if(newValue instanceof Object[]) {
            Object[] currentV = (Object[]) currentValue;
            Object[] currentC = (Object[]) currentCount;
            Object[] newV = (Object[]) newValue;
            for(int i = 0; i < currentV.length; i++)
                addNewLogValue(currentV[i], currentC[i], newV[i]);
        } else {
            LogSumExponential[] currentV = (LogSumExponential[]) currentValue;
            int[] currentC = (int[]) currentCount;
            double[] newV = (double[]) newValue;
            for(int i = 0; i < currentV.length; i++) {
                double p = newV[i];
                if(!Double.isNaN(p)) {
                    currentV[i].add(newV[i]);
                    currentC[i]++;
                }
            }
        }
    }

    @Override
    public final void completeLogProbability(int iterations) {
        completeLogProbability(logProbabilitySum, probabilityCount, logProbability);
        probabilityComputed = true;
    }

    private void completeLogProbability(Object logSum, Object count, Object output) {
        if(output instanceof Object[]) {
            Object[] currentLS = (Object[]) logSum;
            Object[] currentC = (Object[]) count;
            Object[] currentO = (Object[]) output;
            for(int i = 0; i < currentO.length; i++)
                completeLogProbability(currentLS[i], currentC[i], currentO[i]);
        } else {
            LogSumExponential[] currentL = (LogSumExponential[]) logSum;
            int[] currentC = (int[]) count;
            double[] currentO = (double[]) output;
            for(int i = 0; i < currentO.length; i++) {
                int c = currentC[i];
                if(c == 0)
                    currentO[i] = Double.NaN;
                else
                    currentO[i] = currentL[i].getSum() - Math.log(c);
            }
        }
    }
}
