/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.variables;

import org.sandwood.runtime.internal.model.util.Allocator;
import org.sandwood.runtime.internal.model.util.BaseType;
import org.sandwood.runtime.internal.numericTools.LogSumExponential;
import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.variables.IteratedRandomVariable;

public abstract class IteratedRandomVariableInternal<A> implements HasProbabilityInternal, IteratedRandomVariable<A> {

    // TODO add in a test here for the first run, so that we don't construct the
    // arrays
    // for every call to probabilities.
    private boolean probabilityComputed = false;

    private A logProbability;
    private Object logProbabilitySum;

    private final String name;
    private final Model model;
    private final int arrayDimensions;

    public IteratedRandomVariableInternal(Model model, String name, int arrayDimensions) {
        this.name = name;
        this.model = model;
        this.arrayDimensions = arrayDimensions;
    }

    @Override
    public final String name() {
        return name;
    }

    public final boolean probabilityComputed() {
        return probabilityComputed;
    }

    @Override
    public final A getProbability() {
        synchronized(model) {
            return (A) convertProbability(logProbability, 0);
        }
    }

    @Override
    public final A getLogProbability() {
        synchronized(model) {
            return logProbability;
        }
    }

    @Override
    public final void startLogProbability() {
        A template = getCurrentLogProbability();
        logProbability = constructInitialProbability(template, 0);
        logProbabilitySum = constructLogSum(template);
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

    @Override
    public final void ingestLogProbability() {
        A newValue = getCurrentLogProbability();
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

    protected abstract A getCurrentLogProbability();

    @Override
    public final void completeLogProbability(int iterations) {
        extract(logProbabilitySum, logProbability, Math.log(iterations));
        probabilityComputed = true;
    }

    private void extract(Object logSum, Object output, double logIterations) {
        if(output instanceof Object[]) {
            Object[] currentO = (Object[]) output;
            Object[] currentLS = (Object[]) logSum;
            for(int i = 0; i < currentO.length; i++)
                extract(currentLS[i], currentO[i], logIterations);
        } else {
            double[] currentO = (double[]) output;
            LogSumExponential[] currentL = (LogSumExponential[]) logSum;
            for(int i = 0; i < currentO.length; i++)
                currentO[i] = currentL[i].getSum() - logIterations;
        }
    }
}
