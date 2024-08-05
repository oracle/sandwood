/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */
package org.sandwood.fairness.bayesFactor;

import org.sandwood.random.RandomType;

public abstract class Model {
    public abstract void setObservations(int samples, int positives, double eta);

    protected abstract double getProbability(int iterations);

    public abstract void setRngType(RandomType rngType);

    protected abstract double getProbability(int iterations, int maxIterations, double percentageDifference);
}
