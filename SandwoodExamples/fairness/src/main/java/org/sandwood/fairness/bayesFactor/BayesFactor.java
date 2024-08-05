/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */
package org.sandwood.fairness.bayesFactor;

/**
 * A class providing a number of different static methods for calculating the Bayes factor between two models.
 */
public class BayesFactor {
    public final static boolean USE_LOGS = true;

    public static double calculateBayesFactor(Model nullHypothesis, Model alternativeModel, int samples, int positives,
            double eta, int iterations) {
        nullHypothesis.setObservations(samples, positives, eta);
        alternativeModel.setObservations(samples, positives, eta);
        double nullEvidence = nullHypothesis.getProbability(iterations);
        double altEvidence = alternativeModel.getProbability(iterations);

        if(USE_LOGS)
            return Math.log(altEvidence / nullEvidence);
        else
            return altEvidence / nullEvidence;
    }

    public static double calculateBayesFactor(Model nullHypothesis, Model alternativeModel, int samples, int positives,
            double eta, int iterations, int maxIterations, double percentageDifference) {
        // Construct the models
        nullHypothesis.setObservations(samples, positives, eta);
        alternativeModel.setObservations(samples, positives, eta);

        double altEvidence = alternativeModel.getProbability(iterations, maxIterations, percentageDifference);
        double nullEvidence = nullHypothesis.getProbability(iterations, maxIterations, percentageDifference);

        if(USE_LOGS)
            return Math.log(altEvidence / nullEvidence);
        else
            return altEvidence / nullEvidence;
    }

    public static double[] calculateBayesFactor(Model nullHypothesis, Model alternativeModel, double[] eta,
            int[] positives, int[] samples, int iterations) {
        int size = eta.length;
        double[] results = new double[size];
        for(int i = 0; i < size; i++) {
            nullHypothesis.setObservations(samples[i], positives[i], eta[i]);
            alternativeModel.setObservations(samples[i], positives[i], eta[i]);
            results[i] = alternativeModel.getProbability(iterations) / nullHypothesis.getProbability(iterations);
        }
        return results;
    }

    public static double calculateBayesFactorAndVarianceEstimate(Model nullHypothesis, Model alternativeModel,
            int samples, int positives, double eta, int iterations) {
        nullHypothesis.setObservations(samples, positives, eta);
        alternativeModel.setObservations(samples, positives, eta);
        return alternativeModel.getProbability(iterations) / nullHypothesis.getProbability(iterations);
    }

    /**
     * A main method demonstrating how two models can be instantiated and used with these functions.
     * 
     * @param args
     */
    public static void main(String[] args) {
        Model nullModel = new NullModelWrapper();
        Model alternativeModel = new AlternativeModelWrapper();
        long startTime = System.nanoTime();
        System.out.println(calculateBayesFactor(nullModel, alternativeModel, 100, 51, 0.8, 10000000));
        System.out
                .println(calculateBayesFactorAndVarianceEstimate(nullModel, alternativeModel, 100, 51, 0.8, 10000000));
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Time elapsed: " + ((double) elapsedTime) / 1000000000 + "s");
    }
}
