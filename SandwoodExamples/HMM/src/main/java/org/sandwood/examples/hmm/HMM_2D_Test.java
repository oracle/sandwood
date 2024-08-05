/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */
package org.sandwood.examples.hmm;

import java.util.Arrays;
import java.util.Random;

import org.sandwood.random.RandomType;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.RetentionPolicy;

/**
 * A class that uses an HMM model to construct a data set and then trains a new model on the constructed data set. This
 * tests and demonstrates models ability to execute as conventional code and to infer unknown values based on known
 * values.
 */
public class HMM_2D_Test {
    private static final int noStreams = 2000;
    private static final int averageLength = 100;
    private static final int lengthVariance = 40;
    private static final int noStates = 2;
    private static final int noEvents = 4;
    private static final Random r = new Random();

    /**
     * Method to generate a set of random lengths of event streams then be construct using the HMM model.
     * 
     * @return An array of event stream lengths.
     */
    private static int[] getEventShape() {
        int[] shape = new int[noStreams];
        for(int i = 0; i < noStreams; i++)
            shape[i] = r.nextInt(lengthVariance + 1) + averageLength - lengthVariance / 2;
        return shape;
    }

    /**
     * A method that returns a predetermined bias that can be provided to the model when generating streams of events.
     * 
     * @return The predetermined bias.
     */
    private static double[][] getEventsDistribution() {
        double[][] bias = { { 0.5, 0.5, 0, 0 }, { 0, 0, 0.5, 0.5 } };
        return bias;
    }

    /**
     * A method that returns a predetermined initial state bias that can be used when using the model to generate
     * streams of events.
     * 
     * @return The initial state bias
     */
    public static double[] getInitialStateDistribution() {
        double[] initialState = { 0.35, 0.65 };
        return initialState;
    }

    /**
     * A method to get a predetermined transition bias that can be used when getting the model to generate streams of
     * events.
     * 
     * @return The predetermined transition bias.
     */
    private static double[][] getTransistionDistribution() {
        double[][] transistion = { { 0.95, 0.05 }, { 0.1, 0.9 } };
        return transistion;
    }

    /**
     * A method that uses a model to generate noSteams streams of events. These can then be used to train another model
     * and the resultant values can be used to confirm the generation and the inference are both correct.
     * 
     * @return An array holding noStreams streams of events.
     */
    public static int[][] getEvents() {
        HMM_2D model = new HMM_2D(getEventShape(), noStates, noEvents);
        model.bias.setValue(getEventsDistribution());
        model.initialStateDistribution.setValue(getInitialStateDistribution());
        model.m.setValue(getTransistionDistribution());
        model.execute();
        int[][] events = model.events.getSamples()[0];
        model.close();
        return events;
    }

    /**
     * A method to perform basic sanity checks on the data to ensure that the generated data is within expected bounds.
     * 
     * @param events The streams of events to test
     */
    public static void checkData(int[][] events) {
        int l = events[0].length;
        int sum = l;
        int min = l;
        int max = l;
        for(int i = 1; i < events.length; i++) {
            l = events[i].length;
            sum += l;
            if(min > l)
                min = l;
            else if(max < l)
                max = l;
        }

        System.out.println(events.length + " sequences" + "\nAverage length: " + ((double) sum) / events.length
                + "\nMin length: " + min + "\nMax length: " + max);

        double st1 = 0;
        double st2 = 0;
        for(int[] event:events) {
            if(event[0] <= 2)
                st1++;
            else
                st2++;
        }
        System.out.println("Initial state ration: " + st1 / events.length + "," + st2 / events.length);

        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
        int v4 = 0;

        for(int[] sequence:events) {
            for(int element:sequence) {
                switch(element) {
                    case 1:
                        v1++;
                        break;
                    case 2:
                        v2++;
                        break;
                    case 3:
                        v3++;
                        break;
                    case 4:
                        v4++;
                        break;
                }
            }
        }

        System.out.println("State ratio: " + ((double) (v1 + v2)) / sum + ":" + ((double) (v3 + v4)) / sum);
        System.out.println("State 1 event ratio: " + ((double) v1) / (v1 + v2) + ":" + ((double) v2) / (v1 + v2));
        System.out.println("State 2 event ratio: " + ((double) v3) / (v3 + v4) + ":" + ((double) v4) / (v3 + v4));

        // We will only count states that we switch out of so that state lengths are calculated
        // based on m, not the sequence length.
        int noState1 = 0;
        int noState2 = 0;
        int stateLength1 = 0;
        int stateLength2 = 0;
        int noSingleState = 0;
        for(int[] sequence:events) {
            int stateLength = 1;
            boolean state1 = sequence[0] <= 2;
            boolean firstState = true;
            for(int j = 1; j < sequence.length; j++) {
                if(state1 != sequence[j] <= 2) {// If we have changed state.
                    firstState = false;
                    if(state1) {
                        noState1++;
                        stateLength1 += stateLength;
                    } else {
                        noState2++;
                        stateLength2 += stateLength;
                    }
                    stateLength = 1;
                    state1 = !state1;
                } else
                    stateLength++;
            }
            if(firstState)
                noSingleState++;
        }

        System.out.println("Average state 1 length: " + ((double) stateLength1) / noState1);
        System.out.println("Average state 2 length: " + ((double) stateLength2) / noState2);
        System.out.println("Number of single state sequences: " + noSingleState);
    }

    /**
     * A method to drive the process of testing a constructed model by first using it to generate a data set and then by
     * training another instance on the generated data set and ensuring that the resulting model matches the
     * parameterisation of the first modulo the reflection in the state indexing. i.e. states 0 and 1 can swap places
     * with each other without invalidating the correctness of the model.
     * 
     * @param args This method does not read any values from the arguments.
     */
    public static void main(String[] args) {
        System.out.println("Construct input.");
        // Construct the data we will train against.
        int[][] events = getEvents();

        checkData(events);

        // How many iterations do we want the Gibbs sampler to take?
        int iterations = 2000;

        // Construct a model
        System.out.println("Constructing Model");
        HMM_2D model = new HMM_2D(events, noStates, noEvents);
        model.setExecutionTarget(ExecutionTarget.forkJoin);
        // Set that we want to retain the most probable set of parameters.
        model.setDefaultRetentionPolicy(RetentionPolicy.MAP);
        // Pick a random number generator. By default we use the old poor
        // quality Java one. The newer ones are faster and better quality.
        model.setRNGType(RandomType.L64X1024MixRandom);
        // Run the inference
        System.out.println("Running inference for " + iterations + " steps.");

        // The call that actually runs the model.
        System.out.println("Model probability per iteration\nIteration,Probability");
        int inferenceIterations = 100;
        for(int i = 0; i < iterations; i += inferenceIterations) {
            model.inferValues(inferenceIterations);
            System.out.println((i + inferenceIterations) + "," + model.spotLogProbability());
        }

        // Now recover the parameters we want, and save them to csv files in the output location.
        System.out.println("\nOutputing results");
        System.out.println("Initial state distribution\n" + Arrays.toString(model.initialStateDistribution.getMAP()));
        System.out.println("Expected\n" + Arrays.toString(getInitialStateDistribution()));

        double[][] m = model.m.getMAP();
        System.out.println("State transition distribution\n" + Arrays.toString(m[0]) + "\n" + Arrays.toString(m[1]));
        System.out.println("Expected\n" + Arrays.deepToString(getTransistionDistribution()));

        double[][] bias = model.bias.getMAP();
        System.out.println("Bias\n" + Arrays.toString(bias[0]) + "\n" + Arrays.toString(bias[1]));
        System.out.println("Expected\n" + Arrays.deepToString(getEventsDistribution()));

        System.out.println("Finished");

        model.close();
    }
}
