/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */
package org.sandwood.fairness.bayesFactor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import org.sandwood.random.RandomType;

/**
 * A class that when run will read in data from a file with one line per test and construct the Bayes factor for each
 * test in parallel.
 */
public class TestFromFile {
    /*
     * All fields in this class are static and everything other than the main method is private as it is only intend to
     * be used once per call.
     */

    // Rng type
    private RandomType rngType = RandomType.L64X1024MixRandom;
    // Name of the input file
    private String inputFileName;
    // Name of the output file
    private String outputFileName;
    // Flag to record if the input should be included in the output file.
    private boolean includeInputInOutput = false;
    // An initial number of iterations to perform, if a bound on the iterations has not been provided this will be the
    // number of iterations.
    private int initialIterations = 1000;
    // Maximum number of iterations
    private int maxIterations = Integer.MAX_VALUE / 2;
    // The inference code will run until a percentage difference less than this is achieved. If this
    // is negative this means this is disabled.
    private double percentageDifference = -1;
    // Should verbose output be printed?
    private boolean verbose = false;

    public static void main(String[] args) {
        long start = System.nanoTime();
        TestFromFile f = new TestFromFile(args);
        f.runTests();
        long end = System.nanoTime();
        System.out.println("Runtime " + (end - start) / 1000000000 + " seconds");
    }

    private TestFromFile(String[] args) {
        parseArgs(args);
    }

    private class NoneTestLineTask implements Callable<String> {
        private String line;

        public NoneTestLineTask(String line) {
            this.line = line;
        }

        @Override
        public String call() {
            return line;
        }
    }

    private class TestLineTask implements Callable<String> {
        private String line;

        public TestLineTask(String line) {
            this.line = line;
        }

        @Override
        public String call() {
            // Construct models
            Model nullModel = new NullModelWrapper();
            nullModel.setRngType(rngType);
            Model alternativeModel = new AlternativeModelWrapper();
            alternativeModel.setRngType(rngType);
            // Run test
            return runTest(line, nullModel, alternativeModel);
        }
    }

    private void parseArgs(String[] args) {
        if(args.length < 2)
            printHelp();
        inputFileName = args[0];
        outputFileName = args[1];

        int index = 2;
        while(index < args.length) {
            switch(args[index++]) {
                case "-includeInput":
                    includeInputInOutput = true;
                    break;

                case "-i":
                case "-iterations":
                    if(index < args.length) {
                        try {
                            initialIterations = Integer.parseInt(args[index++]);
                        } catch(Exception e) {
                            System.err.println("Invalid value for iterations: " + args[index - 1]);
                            printHelp();
                        }
                    } else
                        printHelp();
                    break;

                case "-m":
                case "-max":
                    if(index < args.length) {
                        try {
                            maxIterations = Integer.parseInt(args[index++]);
                        } catch(Exception e) {
                            System.err.println("Invalid value for max iterations: " + args[index - 1]);
                            printHelp();
                        }
                    } else
                        printHelp();
                    break;

                case "-p":
                case "-percentage":
                    if(index < args.length) {
                        try {
                            percentageDifference = Double.parseDouble(args[index++]) / 100;
                        } catch(Exception e) {
                            System.err.println("Invalid value for percentageDifference: " + args[index - 1]);
                            printHelp();
                        }

                        if(percentageDifference <= 0) {
                            System.err.println("Invalid value for percentageDifference: " + args[index - 1]
                                    + "\nValues must be positive.");
                            printHelp();
                        }
                    } else
                        printHelp();
                    break;

                case "-r":
                case "-rng":
                    try {
                        rngType = RandomType.valueOf(args[index++]);
                    } catch(IllegalArgumentException e) {
                        System.err.println("Invalid value for rngType " + args[index - 1]);
                        printHelp();
                    }
                    break;

                case "-h":
                case "-help":
                    printHelp();
                    break;

                case "-v":
                case "-verbose":
                    verbose = true;
                    break;
                default:
                    printHelp();
            }
        }
    }

    private void printHelp() {
        System.out.println("Usage:\n" + "java TestFromFile InputFileName OutputFileName [Options]\n"
                + "Available options:\n"
                + "-includeInput                Should the original input be included in the output file. This will result in a 5 column CSV file being generated as the output.\n"
                + "\n"
                + "-i n or -iterations n        Sets n, the number of iterations to perform when computing the Bayes Factors. If a percentage difference is provided, this is\n"
                + "                             the initial number of iterations that will be performed. It is not recommended to set this value to less than 1000 iterations\n"
                + "                             in this case.\n" + "\n"
                + "-p n or -percentage n        Sets the minimum percentage difference between 10 runs that is acceptable. This value must be positive. If the initial number\n"
                + "                             of iterations fails to achieve this bound, the number of iterations will be increased until the bound is met. Once this bound\n"
                + "                             has been met all calculated results will be combined to produce an even more accurate result.\n"
                + "\n" + "-r string or -rng string     Sets the RNG to use, accepts values "
                + Arrays.toString(RandomType.values()) + ".\n" + "\n"
                + "-m n or -max n               Set the maximum number of iterations that a call to a model can request.\n"
                + "\n" + "-h or -help                  Prints this output.\n" + "\n"
                + "-v or -verbose               Prints out verbose output detailing the operations of the tool.");
        System.exit(1);
    }

    private void runTests() {
        if(verbose) {
            if(percentageDifference > 0) {
                System.out.println("Percentage difference: " + percentageDifference);
                System.out.println("Initial iterations: " + initialIterations);
            } else
                System.out.println("Iterations: " + initialIterations);
        }

        File inputFile = new File(inputFileName);
        File outputFile = new File(outputFileName);

        // Construct the input reader
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(inputFile));
            if(verbose)
                System.out.println("Reading input from: " + inputFileName);
        } catch(FileNotFoundException e) {
            System.err.println("Unable to find input file: " + inputFileName);
            System.exit(1);
        }

        // Construct the output writer
        BufferedWriter output = null;
        try {
            output = new BufferedWriter(new FileWriter(outputFile));
            if(verbose)
                System.out.println("Writing output to file: " + outputFileName);
        } catch(IOException e) {
            System.err.println(e.getMessage());
            System.err.println("Unable to find/construct/write to the output file: " + outputFileName);
            System.exit(1);
        }

        // List to hold all the futures
        List<Future<String>> tasks = new ArrayList<>();
        // Thread pool to run the tasks
        AbstractExecutorService pool = new ForkJoinPool();

        // Read in the lines and run a test for each
        String line = null;
        line = readLine(input);

        while(line != null) {
            line = line.trim();
            if((line.startsWith("#") || line.equals("")) && includeInputInOutput) {
                tasks.add(pool.submit(new NoneTestLineTask(line + "\n")));
            } else {
                tasks.add(pool.submit(new TestLineTask(line)));
            }

            // Get the next line.
            line = readLine(input);
        }

        try {
            input.close();
        } catch(IOException e) {
            System.err.println(e.getMessage());
            System.err.println("Unable to close input file: " + outputFileName);
            System.exit(1);
        }

        pool.shutdown();

        int totalTasks = tasks.size();
        int i = 1;
        for(Future<String> f:tasks) {
            try {
                output.write(f.get());
                System.out.println("Complete " + i++ + "/" + totalTasks);
            } catch(IOException e) {
                e.printStackTrace();
            } catch(InterruptedException e) {
                e.printStackTrace();
            } catch(ExecutionException e) {
                e.printStackTrace();
            }
        }

        try {
            output.close();
        } catch(IOException e) {
            System.err.println(e.getMessage());
            System.err.println("Unable to close output file: " + outputFileName);
            System.exit(1);
        }
    }

    private String runTest(String line, Model nullModel, Model alternativeModel) {
        if(verbose)
            System.out.println("Processing test: " + line);

        String[] parts = line.split(",");
        if(parts.length < 4)
            return outputFailure(line, "Test failed insufficient inputs");

        final int locApplicantCountM = 0;
        final int locAcceptanceCountM = 1;
        final int locApplicantCountF = 2;
        final int locAcceptanceCountF = 3;

        try {
            int applicantCountM = Integer.parseInt(parts[locApplicantCountM].trim());

            if(verbose)
                System.out.println("\tMale applicant count: " + applicantCountM);

            if(applicantCountM <= 0) {
                if(verbose)
                    System.out.println("Test failed - Male applicant count is not positive.");
                return outputFailure(line, "Male applicant count is not a positive value");
            }

            try {
                int acceptanceCountM = Integer.parseInt(parts[locAcceptanceCountM].trim());

                if(verbose)
                    System.out.println("\tAccepted male count: " + acceptanceCountM);

                if(acceptanceCountM < 0) {
                    if(verbose)
                        System.out.println("Test failed - Accepted male count is negative.");
                    return outputFailure(line, "Test failed - Accepted male count is negative.");
                }

                if(applicantCountM < acceptanceCountM) {
                    if(verbose)
                        System.out
                                .println("Test failed - Female acceptance count is greater than male applicant count.");
                    return outputFailure(line, "male acceptance count is greater than male applicant count.");
                }

                try {
                    int applicantCountF = Integer.parseInt(parts[locApplicantCountF].trim());

                    if(verbose)
                        System.out.println("\tFemale applicant count: " + applicantCountF);

                    if(applicantCountF <= 0) {
                        if(verbose)
                            System.out.println("Test failed - Female applicant count is not positive.");
                        return outputFailure(line, "Female applicant count is not a positive value");
                    }

                    try {
                        int acceptanceCountF = Integer.parseInt(parts[locAcceptanceCountF].trim());

                        if(verbose)
                            System.out.println("\tAccepted female count: " + acceptanceCountF);

                        if(acceptanceCountF < 0) {
                            if(verbose)
                                System.out.println("Test failed - Accepted female count is negative.");
                            return outputFailure(line, "Test failed - Accepted female count is negative.");
                        }

                        if(applicantCountF < acceptanceCountF) {
                            if(verbose)
                                System.out.println(
                                        "Test failed - Female acceptance count is greater than female applicant count.");
                            return outputFailure(line,
                                    "Female acceptance count is greater than female applicant count.");
                        }

                        double selectionRateM = ((double) acceptanceCountM) / applicantCountM;
                        double selectionRateF = ((double) acceptanceCountF) / applicantCountF;

                        double selectionRate;
                        int applicantCount, acceptanceCount;

                        if(selectionRateM < selectionRateF) {
                            selectionRate = selectionRateF;
                            applicantCount = applicantCountM;
                            acceptanceCount = acceptanceCountM;
                        } else if(selectionRateM > selectionRateF) {
                            selectionRate = selectionRateM;
                            applicantCount = applicantCountF;
                            acceptanceCount = acceptanceCountF;
                        } else if(applicantCountF < applicantCountM) {
                            selectionRate = selectionRateM;
                            applicantCount = applicantCountF;
                            acceptanceCount = acceptanceCountF;
                        } else {
                            selectionRate = selectionRateF;
                            applicantCount = applicantCountM;
                            acceptanceCount = acceptanceCountM;
                        }

                        if(verbose) {
                            System.out.println("\n\tMale selection rate: " + selectionRateM);
                            System.out.println("\tFemale selection rate: " + selectionRateF);
                            System.out.println("\tSelection rate: " + selectionRate);
                            System.out.println("\tApplicant Count: " + applicantCount);
                            System.out.println("\tAcceptance Count: " + acceptanceCount);
                        }

                        double result;
                        if(percentageDifference > 0)
                            result = BayesFactor.calculateBayesFactor(nullModel, alternativeModel, applicantCount,
                                    acceptanceCount, selectionRate, initialIterations, maxIterations,
                                    percentageDifference);
                        else
                            result = BayesFactor.calculateBayesFactor(nullModel, alternativeModel, applicantCount,
                                    acceptanceCount, selectionRate, initialIterations);

                        if(verbose)
                            System.out.println("Bayes Factor: " + result);

                        return outputResult(line, result);

                    } catch(NumberFormatException e) {
                        return outputFailure(line,
                                "Unable to parse " + parts[locAcceptanceCountF] + " for the female acceptance count.");
                    }

                } catch(NumberFormatException e) {
                    return outputFailure(line,
                            "Unable to parse " + parts[locApplicantCountF] + " for the female application count.");
                }

            } catch(NumberFormatException e) {
                return outputFailure(line,
                        "Unable to parse " + parts[locAcceptanceCountM] + " for the male acceptance count.");
            }

        } catch(NumberFormatException e) {
            return outputFailure(line,
                    "Unable to parse " + parts[locApplicantCountM] + " for the male application count.");
        }
    }

    private String outputResult(String line, double result) {
        if(includeInputInOutput)
            return line + "," + result + "\n";
        else
            return result + "\n";
    }

    private String outputFailure(String line, String message) {
        if(includeInputInOutput)
            return line + "," + message + "\n";
        else
            return message + "\n";
    }

    /**
     * Method to read a line handling any exceptions.
     *
     * @param input
     * @return
     */
    private String readLine(BufferedReader input) {
        try {
            return input.readLine();
        } catch(IOException e) {
            System.err.println("Error reading input file: " + inputFileName);
            System.exit(1);
        }
        return null;
    }
}
