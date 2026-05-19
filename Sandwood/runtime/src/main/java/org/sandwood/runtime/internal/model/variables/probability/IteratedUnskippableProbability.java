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

public final class IteratedUnskippableProbability<A> extends IterativeProbabilityBase<A> {

    public IteratedUnskippableProbability(IteratedCurrentProbability<A> c, int arrayDimensions) {
        super(c, arrayDimensions);
    }

    @Override
    public void ingestLogProbability() {
        A newValue = c.getCurrentLogProbability();
        addNewLogValue(logProbabilitySum, newValue);
    }

    protected void addNewLogValue(Object currentValue, Object newValue) {
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
    protected void completeLogProbabilityInternal(int iterations) {
        completeLogProbability(logProbabilitySum, logProbability, Math.log(iterations));
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
