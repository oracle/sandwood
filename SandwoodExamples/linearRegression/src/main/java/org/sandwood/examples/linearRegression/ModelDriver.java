/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.examples.linearRegression;

import java.text.DecimalFormat;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.RetentionPolicy;

/**
 * A class to demonstrate the API calls the use the linear regression model. As it stands the arrays of x and y values
 * are allocated but not populated. This step is left to the user.
 */
public class ModelDriver {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.000");
        RandomGenerator rand = RandomGeneratorFactory.getDefault().create();

        final int numTrain = 10000;
        final int numTest = 10;
        final int numSamples = 10000;

        // Construct parameters to construct training data
        double b0Real = rand.nextDouble(1, 10);
        double b1Real = rand.nextDouble(-10, 10);
        double varianceReal = rand.nextDouble(0, 3);

        // Construct training data
        double[] xsTraining = pickXs(rand, numTrain);
        double[] ysTraining = calculateYs(rand, b0Real, b1Real, varianceReal, xsTraining);

        // Print out the generated data and the parameters used to construct the y values.
        System.out.println("Training data (First 20 of " + numTrain + " entries)");
        System.out.println("=========================================");

        for(int i = 0; i < 20; i++)
            System.out.println("xs: " + df.format(xsTraining[i]) + ", ys: " + df.format(ysTraining[i]));
        System.out.println("\n");

        System.out.println("Real Model Values");
        System.out.println("=================");
        System.out.println("b0: " + df.format(b0Real));
        System.out.println("b1: " + df.format(b1Real));
        System.out.println("Variance: " + df.format(varianceReal));
        System.out.println("\n");

        // Construct an instance of the model
        LinearRegression l = new LinearRegression(xsTraining, ysTraining);

        // Configure the model
        l.setDefaultRetentionPolicy(RetentionPolicy.MAP);
        l.setExecutionTarget(ExecutionTarget.forkJoin);

        // Train the model with 1000 steps of Gibbs sampling
        l.inferValues(1000);

        // Inspect the inferred values
        double b0Inferred = l.b0.getMAP();
        double b1Inferred = l.b1.getMAP();
        double varianceInferred = l.variance.getMAP();

        System.out.println("Inferred Model Values");
        System.out.println("=====================");
        System.out.println("b0: " + df.format(b0Inferred));
        System.out.println("b1: " + df.format(b1Inferred));
        System.out.println("Variance: " + df.format(varianceInferred));
        System.out.println("\n");

        // Close the model
        l.close();

        // Construct test data
        double[] xsTest = pickXs(rand, numTest);

        // Construct a new model and fix the model parameters to the most probable values
        l = new LinearRegression(xsTest);
        l.b0.setValue(b0Inferred);
        l.b1.setValue(b1Inferred);
        l.variance.setValue(varianceInferred);

        // Set all values a ys to be saved
        l.y.setRetentionPolicy(RetentionPolicy.SAMPLE);

        // Generate nSamples possible y values for each x value
        l.execute(numSamples);

        // Recover the y samples
        double[][] ysGenerated = l.y.getSamples();
        l.close();

        // Calculate the expected results
        double[] ysExpected = calculateYs(b0Inferred, b1Inferred, xsTest);

        // Test the values of the expected results
        System.out.println("Generated Values");
        System.out.println("================");
        for(int i = 0; i < numTest; i++) {
            double sum = 0;
            double sumsq = 0;
            for(int j = 0; j < numSamples; j++) {
                double y = ysGenerated[j][i];
                sum += y;
                sumsq += y * y;
            }

            double mean = sum / numSamples;
            double variance = sumsq / numSamples - mean * mean;

            System.out.println("x: " + df.format(xsTest[i]) + " y expected: " + df.format(ysExpected[i]) + " y mean "
                    + df.format(mean) + " variance: " + df.format(variance));
        }
    }

    private static double[] pickXs(RandomGenerator rand, int n) {
        double[] xs = new double[n];
        for(int i = 0; i < n; i++)
            xs[i] = rand.nextDouble(-1000, 1000);
        return xs;
    }

    /**
     * Construct an array of y values that correspond to the values in xs under the line defined by b0 and b1.
     * 
     * @param b0 The coefficient b0
     * @param b1 The coefficient b1
     * @param xs An array containing the x values
     * @return The corresponding y values under the linear transformation
     */
    private static double[] calculateYs(double b0, double b1, double[] xs) {
        int n = xs.length;
        double[] ys = new double[n];
        for(int i = 0; i < n; i++)
            ys[i] = b0 + b1 * xs[i];
        return ys;
    }

    /**
     * Construct an array of y values that correspond to the values in xs under the line defined by b0, b1, and the
     * variance.
     * 
     * @param rand     The random number generator to use constructing the samples.
     * @param b0       The coefficient b0
     * @param b1       The coefficient b1
     * @param variance The variance that should be applied as noise to the points
     * @param xs       An array containing the x values
     * @return The corresponding y values under the linear transformation
     */
    private static double[] calculateYs(RandomGenerator rand, double b0, double b1, double variance, double[] xs) {
        int n = xs.length;
        double sd = Math.sqrt(variance);
        double[] ys = new double[n];
        for(int i = 0; i < n; i++)
            ys[i] = rand.nextGaussian(b0 + b1 * xs[i], sd);
        return ys;
    }
}
