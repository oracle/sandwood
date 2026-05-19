/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.variables.probability;

import org.sandwood.runtime.internal.model.variables.IteratedCurrentProbability;
import org.sandwood.runtime.internal.numericTools.LogSumExponential;

public final class IteratedSkippableProbability<A> extends IterativeProbabilityBase<A> {
    
    private Object probabilityCount = null;

    public IteratedSkippableProbability(IteratedCurrentProbability<A> c, int arrayDimensions) {
        super(c, arrayDimensions);
    }

    @Override
    protected void startLogProbabilityInternal(A template) {
        probabilityCount = constructCount(template);
        super.startLogProbabilityInternal(template);
    }

    @Override
    protected void startLogProbabilityInternal() {
        clearCount(probabilityCount);
        super.startLogProbabilityInternal();
    }


    private Object constructCount(Object template) {
        if(template instanceof Object[]) {
            Object[] objTemplate = (Object[]) template;
            int length = objTemplate.length;
            Object[] output = new Object[length];
            for(int i = 0; i < length; i++)
                output[i] = constructCount(objTemplate[i]);
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
    public void ingestLogProbability() {
        A newValue = c.getCurrentLogProbability();
        addNewLogValue(logProbabilitySum, newValue, probabilityCount);
    }

    private void addNewLogValue(Object currentValue, Object newValue, Object currentCount) {
        if(newValue instanceof Object[]) {
            Object[] currentV = (Object[]) currentValue;
            Object[] currentC = (Object[]) currentCount;
            Object[] newV = (Object[]) newValue;
            for(int i = 0; i < currentV.length; i++)
                addNewLogValue(currentV[i], newV[i], currentC[i]);
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
    protected void completeLogProbabilityInternal(int iterations) {
        completeLogProbability(logProbabilitySum, logProbability, probabilityCount);
    }

    private void completeLogProbability(Object logSum, Object output, Object count) {
        if(output instanceof Object[]) {
            Object[] currentLS = (Object[]) logSum;
            Object[] currentO = (Object[]) output;
            Object[] currentC = (Object[]) count;
            for(int i = 0; i < currentO.length; i++)
                completeLogProbability(currentLS[i], currentO[i], currentC[i]);
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
