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
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.sandwood.random.RandomType;
import org.sandwood.runtime.model.RetentionPolicy;

public class HMM_Demo {
    private static final String fileLocation = "simpleHmmData";
    private static String outputFileLocation = "simpleHmmResults";

    public static void main(String[] args) throws IOException {
        System.out.println("Reading input.");
        //Load in the data to train against.
        int[] events = readEvents(fileLocation + File.separator + "train-data-hmm-simple.csv");

        //Set the other parameters for the model
        int noStates = 20;
        int noEvents = getNoEvents(events);

        //How many iterations should the Gibbs sampler run for?
        int iterations = 2000;

        //Construct a directory for the results.
        Date date = new Date(System.currentTimeMillis());
        outputFileLocation = outputFileLocation + File.separator +
                ((args.length>0)?args[0] + "_":"") +
                date.toString().replace(" ", "_").replace(":", "-");
        File target = new File(outputFileLocation);

        //If the target directory can be instantiated start the inference.
        if(target.mkdirs()) {
            //Construct a model
            System.out.println("Constructing Model");
            HMM model = new HMM(events, noStates, noEvents);
            //Set that the most probable set of parameters should be retained.
            model.setDefaultRetentionPolicy(RetentionPolicy.MAP);
            //Pick a random number generator. The newer ones are faster and better quality.
            model.setRNGType(RandomType.L64X1024MixRandom);
            //Run the inference
            System.out.println("Running inference for " + iterations + " steps.");
            int run = 0;
            for(int i=1;i<=100;i++) {
                //Steps to allow a printout of progress.
                int thisStep = (iterations * i)/100 - run;
                run += thisStep;

                //The call that actually runs the model.
                model.inferValues(thisStep);

                //Print the progress.
                System.out.println(i + "%");
            }
            //Now recover the required parameters and save them to csv files in the output location.
            System.out.println("Outputting results");
            output(model.initialStateDistribution.getMAP(), "initialStateDistribution");
            output(model.m.getMAP(), "stateTransitionDistribution");
            output(model.bias.getMAP(), "emitDistribution");

            model.close();
            
            System.out.println("Finished");
        }
    }


    //Helper functions for reading and writing files etc.
    private static void output(double[][] value, String name) throws IOException {
        FileWriter output = new FileWriter(outputFileLocation + File.separator + name + ".csv");
        for(double[] da:value) {
            for(double d:da)
                output.write(d + ",");
            output.write("\n");
        }
        output.close();
    }

    private static void output(double[] value, String name) throws IOException {
        FileWriter output = new FileWriter(outputFileLocation + File.separator + name + ".csv");
        for(double d:value)
            output.write(d + ",");
        output.close();
    }

    private static int getNoEvents(int[] events) {
        int max=0;
        for (int value:events) {
            if(value>max)
                max = value;
        }
        return max;
    }

    private static int[] readEvents(String filename) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(filename));
        String s = f.readLine();
        String[] parts = s.split(",");
        int noEvents = parts.length;
        int[] events = new int[noEvents];
        for(int i=0;i<noEvents;i++)
            events[i] = Integer.parseInt(parts[i]);

        f.close();

        return events;
    }
}
