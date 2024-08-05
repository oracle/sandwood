/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */
package org.sandwood.fairness.bayesFactor;

import org.sandwood.fairness.models.AlternativeModel;
import org.sandwood.fairness.models.NullModel;

public class ModelUseExample {

    /**
     * A main method that demonstrates different ways of instantiating models and using them to calculate the likelihood
     * of a model calculating a specific value.
     * 
     * @param args
     */
    public static void main(String[] args) {
        AlternativeModel altModel = new AlternativeModel();
        altModel.observedPositiveCount.set(38);
        altModel.observedSampleCount.set(200);
        System.out.println("Starting alt model probability inference");
        altModel.inferProbabilities(0.00001, 1000, 1000000);
        System.out.println("Alt model probability: " + altModel.getProbability());

        // We will initialize the model a different way for the null model.
        NullModel nullModel = new NullModel();
        NullModel.AllInputs nullModelInputs = new NullModel.AllInputs(200, 38, 0.2);
        System.out.println("Starting null model probability inference");
        nullModel.inferProbabilities(0.00001, 1000, 1000000, nullModelInputs);
        System.out.println("Null model probability: " + nullModel.getProbability());
        nullModel.close();

        // Third model just to demonstrate all the possible ways of executing.
        nullModel = new NullModel(200, 38, 0.2);
        System.out.println("Starting null model probability inference");
        nullModel.inferProbabilities(0.00001, 1000, 1000000, nullModelInputs);
        System.out.println("Null model probability: " + nullModel.getProbability());

        System.out.println("Bayes factor: " + altModel.getProbability() / nullModel.getProbability());

        // Close models to release resources
        altModel.close();
        nullModel.close();
    }
}
