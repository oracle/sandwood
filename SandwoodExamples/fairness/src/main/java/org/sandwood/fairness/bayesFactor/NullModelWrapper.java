/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */
package org.sandwood.fairness.bayesFactor;

import org.sandwood.fairness.models.NullModel;
import org.sandwood.random.RandomType;

public class NullModelWrapper extends Model {

    private NullModel model = new NullModel();

    @Override
    public void setObservations(int samples, int positives, double eta) {
        model.observedSampleCount.setValue(samples);
        model.observedPositiveCount.setValue(positives);
        model.eta.setValue(eta);
    }

    @Override
    protected double getProbability(int iterations) {
        model.inferProbabilities(iterations);
        return model.positiveCount.getProbability();
    }

    @Override
    public void setRngType(RandomType rngType) {
        model.setRNGType(rngType);
    }

    @Override
    protected double getProbability(int iterations, int maxIterations, double percentageDifference) {
        model.inferProbabilities(percentageDifference, iterations, maxIterations);
        return model.getProbability();
    }
}