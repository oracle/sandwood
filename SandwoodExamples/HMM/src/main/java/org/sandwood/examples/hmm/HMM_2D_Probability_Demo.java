/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */
package org.sandwood.examples.hmm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class demonstrating how a trained 2D HMM model can be used to generate the probabilities of the model generating
 * provided sequences.
 */
public class HMM_2D_Probability_Demo {
    private static final String dataLocation = "originalData";

    public static void main(String[] args) throws IOException {
        if(args.length != 2) {
            System.out.println("Expected arguments are: no iterations, path to inputs");
            System.exit(1);
        }

        // Get the parameters for the test.
        int iterations = Integer.parseInt(args[0]);
        String parameterLocation = args[1];

        // Load the data
        String observedData = dataLocation + File.separator + "test-data-hmm.csv";
        System.out.println("Using dataset " + observedData);
        int[][] events = readEvents(observedData);
        int noStates = 20;
        int noEvents = getNoEvents(events);

        double[][] m = readValues(parameterLocation + File.separator + "stateTransitionDistribution.csv");
        double[][] bias = readValues(parameterLocation + File.separator + "emitDistribution.csv");
        double[] initialStateDistribution = readValues(
                parameterLocation + File.separator + "initialStateDistribution.csv")[0];

        // A temporary variable to hold a single session.
        int[][] subEvents = new int[1][];

        System.out.println("Length,Model Probability,Events Probability");
        // For each session
        for(int[] event:events) {
            subEvents[0] = event;
            System.out.print(subEvents[0].length);

            // Construct and parameterise a model.
            HMM_2D model = new HMM_2D(subEvents, noStates, noEvents);
            model.m.setValue(m);
            model.bias.setValue(bias);
            model.initialStateDistribution.setValue(initialStateDistribution);

            // Infer the probability
            model.inferProbabilities(iterations);

            // Output the probabilites of the whole mode, and just the events variable.
            System.out.print("," + model.getLogProbability());
            System.out.println("," + model.events.getLogProbability());

            model.close();
        }
    }

    // Helper methods again.
    private static int getNoEvents(int[][] events) {
        int max = 0;
        for(int[] eventsI:events) {
            for(int value:eventsI) {
                if(value > max)
                    max = value;
            }
        }
        return max;
    }

    private static int[][] readEvents(String filename) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(filename));
        List<int[]> lines = new ArrayList<>();
        String s = f.readLine();
        while(s != null) {
            String[] parts = s.split(",");
            if(parts.length != 0) { // This is mainly to catch trailing newlines.
                int noEvents = parts.length;
                int[] events = new int[noEvents];
                for(int i = 0; i < noEvents; i++)
                    events[i] = Integer.parseInt(parts[i]);
                lines.add(events);
            }
            s = f.readLine();
        }

        f.close();

        return lines.toArray(new int[lines.size()][]);
    }

    private static double[][] readValues(String filename) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(filename));
        List<double[]> lines = new ArrayList<>();
        String s = f.readLine();
        while(s != null) {
            String[] parts = s.split(",");
            if(parts.length != 0) { // This is mainly to catch trailing newlines.
                int noElements = parts.length;
                double[] events = new double[noElements];
                for(int i = 0; i < noElements; i++)
                    events[i] = Double.parseDouble(parts[i]);
                lines.add(events);
            }
            s = f.readLine();
        }

        f.close();

        return lines.toArray(new double[lines.size()][]);
    }
}
