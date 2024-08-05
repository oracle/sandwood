/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */
package org.sandwood.fairness.bayesFactor;

import org.sandwood.fairness.models.AlternativeModel;
import org.sandwood.random.RandomType;

public class AlternativeModelWrapper extends Model {

    private AlternativeModel.AllInputs inputs;
    private AlternativeModel model = new AlternativeModel();

    @Override
    public void setObservations(int samples, int positives, double eta) {
        inputs = new AlternativeModel.AllInputs(samples, positives);
    }

    @Override
    protected double getProbability(int iterations) {
        AlternativeModel.Probabilities probabilities = model.inferProbabilities(iterations, inputs);
        return probabilities.getModelProbability();
    }

    @Override
    public void setRngType(RandomType rngType) {
        model.setRNGType(rngType);
    }

    @Override
    protected double getProbability(int iterations, int maxIterations, double percentageDifference) {
        AlternativeModel.Probabilities probabilities = model.inferProbabilities(percentageDifference, iterations,
                maxIterations, inputs);
        return probabilities.getModelProbability();
    }
}
