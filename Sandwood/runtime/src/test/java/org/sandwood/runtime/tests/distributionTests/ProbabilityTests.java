/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.tests.distributionTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.sandwood.common.tests.util.TestRuntime;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.internal.numericTools.Gamma;

public class ProbabilityTests {
    private static final String outputDir = "src" + File.separator + "test" + File.separator + "resources"
            + File.separator + "expectedOutputs" + File.separator + "ProbabilityTests";

    /*
     * private class DataWriter { private final FileWriter fileWriter; public DataWriter(String name) throws IOException
     * { File dir = new File(outputDir); dir.mkdirs(); File file = new File(dir, name + ".csv"); fileWriter = new
     * FileWriter(file); }
     * 
     * public void write(String ... values) throws IOException { for(String v:values) fileWriter.append(v + ",");
     * fileWriter.append("\n"); }
     * 
     * public void write(int v1, double v2, double v3) throws IOException { write(Integer.toString(v1),
     * Double.toString(v2), Double.toString(v3)); }
     * 
     * public void write(int v1, double v2, boolean v3, double v4) throws IOException { write(Integer.toString(v1),
     * Double.toString(v2), Boolean.toString(v3), Double.toString(v4)); }
     * 
     * public void write(double v1, double v2, double v3, double v4) throws IOException { write(Double.toString(v1),
     * Double.toString(v2), Double.toString(v3), Double.toString(v4)); }
     * 
     * public void write(double[] v1, double[] v2, double v3) throws IOException { write(Arrays.toString(v1),
     * Arrays.toString(v2), Double.toString(v3)); }
     * 
     * public void write(double v1, double v2, double v3) throws IOException { write(Double.toString(v1),
     * Double.toString(v2), Double.toString(v3)); }
     * 
     * public void close() throws IOException { fileWriter.close(); } }
     */

    private static class DataReader {
        private final BufferedReader fileReader;

        public DataReader(String name) throws FileNotFoundException {
            File file = new File(outputDir + File.separator + name + ".csv");
            fileReader = new BufferedReader(new FileReader(file));
        }

        private String[] getInputs() throws IOException {
            String line = fileReader.readLine();

            // The file should not be empty, but just in case.
            assertTrue(line != null);

            // Split out the stored values from the read line, handling
            // arrays as well as scalars.
            String[] parts = line.split("\\[");
            // Are there any array arguments?
            if(parts.length > 1) {
                List<String> elements = new ArrayList<>();
                // If the first argument is not an array process the arguments up to the first
                // array.
                if(!parts[0].equals(""))
                    for(String s:parts[0].split(","))
                        elements.add(s);
                // For each array argument handle the array and any further non array arguments.
                for(int i = 1; i < parts.length; i++) {
                    String[] p2 = parts[i].split("\\]");
                    elements.add(p2[0]);
                    // Only process non array arguments.
                    for(String s:p2[1].split(","))
                        if(!s.equals(""))
                            elements.add(s);
                }
                parts = elements.toArray(new String[elements.size()]);
            } else
                parts = parts[0].split(",");
            return parts;
        }

        private static void checkDouble(double value, double input) {
            if(!testDouble(value, input))
                // This assertion will always fail if called, but will provide a more useful output for debugging.
                assertEquals(value, input);
        }

        private static void checkDoubleArray(double[] v1, String input) {
            String[] inputs = input.split(",");
            assertEquals(v1.length, inputs.length);
            for(int i = 0; i < v1.length; i++)
                checkDouble(v1[i], Double.parseDouble(inputs[i]));
        }

        private static void checkIntArray(int[] v1, String input) {
            String[] inputs = input.split(",");
            assertEquals(v1.length, inputs.length);
            for(int i = 0; i < v1.length; i++)
                assertEquals(v1[i], Integer.parseInt(inputs[i]));
        }

        public void close() throws IOException {
            fileReader.close();
        }

        public double read(int v1, double v2) throws IOException {
            String[] inputs = getInputs();
            // Check the stored and passed values match
            assertEquals(inputs.length, 3);
            assertEquals(v1, Integer.parseInt(inputs[0]));
            checkDouble(v2, Double.parseDouble(inputs[1]));

            // Return the expected result.
            return Double.parseDouble(inputs[2]);
        }

        public double read(int v1, double v2, boolean v3) throws IOException {
            String[] inputs = getInputs();
            // Check the stored and passed values match
            assertEquals(inputs.length, 4);
            assertEquals(v1, Integer.parseInt(inputs[0]));
            checkDouble(v2, Double.parseDouble(inputs[1]));
            assertEquals(v3, Boolean.parseBoolean(inputs[2]));

            // Return the expected result.
            return Double.parseDouble(inputs[3]);
        }

        public double read(double v1, double v2, double v3) throws IOException {
            String[] inputs = getInputs();
            // Check the stored and passed values match
            assertEquals(inputs.length, 4);
            checkDouble(v1, Double.parseDouble(inputs[0]));
            checkDouble(v2, Double.parseDouble(inputs[1]));
            checkDouble(v3, Double.parseDouble(inputs[2]));

            // Return the expected result.
            return Double.parseDouble(inputs[3]);
        }

        public double read(double[] v1, double[] v2) throws IOException {
            String[] inputs = getInputs();
            // Check the stored and passed values match
            assertEquals(inputs.length, 3);
            checkDoubleArray(v1, inputs[0]);
            checkDoubleArray(v2, inputs[1]);

            // Return the expected result.
            return Double.parseDouble(inputs[2]);
        }

        public double read(double[] v1, int[] v2) throws IOException {
            String[] inputs = getInputs();
            // Check the stored and passed values match
            assertEquals(inputs.length, 3);
            checkDoubleArray(v1, inputs[0]);
            checkIntArray(v2, inputs[1]);

            // Return the expected result.
            return Double.parseDouble(inputs[2]);
        }

        public double read(double v1, double v2) throws IOException {
            String[] inputs = getInputs();
            // Check the stored and passed values match
            assertEquals(inputs.length, 3);
            checkDouble(v1, Double.parseDouble(inputs[0]));
            checkDouble(v2, Double.parseDouble(inputs[1]));

            // Return the expected result.
            return Double.parseDouble(inputs[2]);
        }
    }

    @Test
    void testBernoulli() throws IOException {
        DataReader d = new DataReader("bernoulli");

        for(double p = 0.0; p <= 1; p += 0.01) {
            double localValue = DistributionSampling.probabilityBernoulli(false, p);
            double storedValue = d.read(1, p, false);
            testValueNormal(localValue, storedValue, 0, p);

            double distributionSum = localValue;
            localValue = DistributionSampling.probabilityBernoulli(true, p);
            storedValue = d.read(1, p, true);
            testValueNormal(localValue, storedValue, 1, p);

            // Check the distribution sums to approximately 1.
            distributionSum += localValue;
            assertTrue(0.9999999 < distributionSum && distributionSum < 1.0000001);
        }
        d.close();
    }

    @Test
    void testLogBernoulli() throws IOException {
        DataReader d = new DataReader("logBernoulli");
        for(double p = 0.0; p <= 1; p += 0.01) {
            double localValue = DistributionSampling.logProbabilityBernoulli(false, p);
            double storedValue = d.read(1, p, false);
            testValueLog(localValue, storedValue, 0, p);

            localValue = DistributionSampling.logProbabilityBernoulli(true, p);
            storedValue = d.read(1, p, true);
            testValueLog(localValue, storedValue, 1, p);
        }
        d.close();
    }

    @Test
    void testBeta() throws IOException {
        DataReader d = new DataReader("beta");
        for(double alpha = 0.1; alpha < 5; alpha += 0.1) {
            for(double beta = 0.1; beta < 5; beta += 0.1) {
                for(double value = 0.1; value < 1; value += 0.1) {
                    double localValue = DistributionSampling.probabilityBeta(value, alpha, beta);
                    double storedValue = d.read(value, alpha, beta);
                    testValueNormal(localValue, storedValue, value, alpha, beta);
                }
            }
        }
        d.close();
    }

    @Test
    void testLogBeta() throws IOException {
        DataReader d = new DataReader("logBeta");
        for(double alpha = 0.1; alpha < 5; alpha += 0.1) {
            for(double beta = 0.1; beta < 5; beta += 0.1) {
                for(double value = 0.1; value < 1; value += 0.1) {
                    double localValue = DistributionSampling.logProbabilityBeta(value, alpha, beta);
                    double storedValue = d.read(value, alpha, beta);
                    testValueLog(localValue, storedValue, value, alpha, beta);
                }
            }
        }
        d.close();
    }

    @Test
    void testBinomial() throws IOException {
        DataReader d = new DataReader("binomial");
        for(int length = 1; length <= 128; length = 2 * length) {
            for(int t = 0; t <= length; t++) {
                for(double p = 0.0; p <= 1; p += 0.01) {
                    double localValue = DistributionSampling.probabilityBinomial(t, p, length);
                    double storedValue = d.read(t, p, length);
                    testValueNormal(localValue, storedValue, t, length, p);
                }
            }
        }
        d.close();
    }

    @Test
    void testLogBinomial() throws IOException {
        DataReader d = new DataReader("logBinomial");
        for(int length = 1; length <= 2048; length = 2 * length) {
            for(int t = 0; t <= length; t++) {
                for(double p = 0.0; p <= 1; p += 0.01) {
                    double localValue = DistributionSampling.logProbabilityBinomial(t, p, length);
                    double storedValue = d.read(t, p, length);
                    testValueLog(localValue, storedValue, t, length, p);
                }
            }
        }
        d.close();
    }

    // Categorical distributions are missed out as they are just a return of the
    // passed parameters.

    @Test
    void testDirichlet() throws IOException {
        DataReader d = new DataReader("dirichlet");
        double[] param = new double[3];
        double[] value = new double[3];

        for(double i = 0.1; i < 5; i++) {
            param[0] = i;
            for(double j = 0.1; j < 5; j++) {
                param[1] = j;
                for(double k = 0.1; k < 5; k++) {
                    param[2] = k;
                    for(double l = 0.1; l < 1 - 0.2; l += 0.1) {
                        value[0] = l;
                        for(double m = 0.1; m < 1 - l - 0.1; m += 0.1) {
                            value[1] = m;
                            for(double n = 0.1; n < 1 - (l + m); n += 0.1) {
                                value[2] = n;
                                double localValue = DistributionSampling.probabilityDirichlet(value, param);
                                double storedValue = d.read(param, value);
                                testValueNormal(localValue, storedValue, param[0], param[1], param[2], value[0],
                                        value[1], value[2]);
                            }
                        }
                    }
                }
            }
        }
        d.close();
    }

    @Test
    void testLogDirichlet() throws IOException {
        DataReader d = new DataReader("logDirichlet");
        double[] param = new double[3];
        double[] value = new double[3];

        for(double i = 0.1; i < 5; i++) {
            param[0] = i;
            for(double j = 0.1; j < 5; j++) {
                param[1] = j;
                for(double k = 0.1; k < 5; k++) {
                    param[2] = k;
                    for(double l = 0.1; l < 1 - 0.2; l += 0.1) {
                        value[0] = l;
                        for(double m = 0.1; m < 1 - l - 0.1; m += 0.1) {
                            value[1] = m;
                            for(double n = 0.1; n < 1 - (l + m); n += 0.1) {
                                value[2] = n;
                                double localValue = DistributionSampling.logProbabilityDirichlet(value, param);
                                double storedValue = d.read(param, value);
                                testValueLog(localValue, storedValue, param[0], param[1], param[2], value[0], value[1],
                                        value[2]);
                            }
                        }
                    }
                }
            }
        }
        d.close();
    }

    @Test
    void testGamma() throws IOException {
        DataReader d = new DataReader("gamma");
        for(double alpha = 0.1; alpha < 5; alpha += 0.1) {
            for(double beta = 0.1; beta < 5; beta += 0.1) {
                for(double value = 0.1; value < 100; value++) {
                    double localValue = DistributionSampling.probabilityGamma(value, alpha, beta);
                    double storedValue = d.read(value, alpha, beta);
                    testValueNormal(localValue, storedValue, value, alpha, beta);
                }
            }
        }
        d.close();
    }

    @Test
    void testLogGamma() throws IOException {
        DataReader d = new DataReader("logGamma");
        for(double alpha = 0.1; alpha < 5; alpha += 0.1) {
            for(double beta = 0.1; beta < 5; beta += 0.1) {
                for(double value = 0.1; value < 100; value++) {
                    double localValue = DistributionSampling.logProbabilityGamma(value, alpha, beta);
                    double storedValue = d.read(value, alpha, beta);
                    testValueLog(localValue, storedValue, value, alpha, beta);
                }
            }
        }
        d.close();
    }

    @Test
    void testMultinomial() throws IOException {
        DataReader d = new DataReader("multinomial");
        double[] param = new double[3];
        int[] value = new int[3];

        for(double i = 0; i < 1; i += 0.1) {
            param[0] = i;
            for(double j = 0; j < 1 - i; j += 0.1) {
                param[1] = j;
                param[2] = 1 - (i + j);
                for(int n = 0; n < 10; n++) {
                    for(int l = 0; l <= n; l++) {
                        value[0] = l;
                        for(int m = 0; m <= n - l; m++) {
                            value[1] = m;
                            value[2] = n - (l + m);
                            double localValue = DistributionSampling.probabilityMultinomial(value, param, n);
                            double storedValue = d.read(param, value);
                            testValueNormal(localValue, storedValue, param[0], param[1], param[2], value[0], value[1],
                                    value[2]);
                        }
                    }
                }
            }
        }
        d.close();
    }

    @Test
    void testLogMultinomial() throws IOException {
        DataReader d = new DataReader("logMultinomial");
        double[] param = new double[3];
        int[] value = new int[3];

        for(double i = 0; i < 1; i += 0.1) {
            param[0] = i;
            for(double j = 0; j < 1 - i; j += 0.1) {
                param[1] = j;
                param[2] = 1 - (i + j);
                for(int n = 0; n < 10; n++) {
                    for(int l = 0; l <= n; l++) {
                        value[0] = l;
                        for(int m = 0; m <= n - l; m++) {
                            value[1] = m;
                            value[2] = n - (l + m);
                            double localValue = DistributionSampling.logProbabilityMultinomial(value, param, n);
                            double storedValue = d.read(param, value);
                            testValueLog(localValue, storedValue, param[0], param[1], param[2], value[0], value[1],
                                    value[2]);
                        }
                    }
                }
            }
        }
        d.close();
    }

    @Test
    void testPoisson() throws IOException {
        DataReader d = new DataReader("poisson");
        for(double lambda = 0.5; lambda < 100; lambda += 5) {
            for(int value = 0; value < 100; value++) {
                double localValue = DistributionSampling.probabilityPoisson(value, lambda);
                double storedValue = d.read(value, lambda);
                testValueNormal(localValue, storedValue, value, lambda);
            }
        }
        d.close();
    }

    @Test
    void testLogPoisson() throws IOException {
        DataReader d = new DataReader("logPoisson");
        for(double lambda = 0.5; lambda < 100; lambda += 5) {
            for(int value = 0; value < 100; value++) {
                double localValue = DistributionSampling.logProbabilityPoisson(value, lambda);
                double storedValue = d.read(value, lambda);
                testValueLog(localValue, storedValue, value, lambda);
            }
        }
        d.close();
    }

    // Inverse gamma is missing as it is not in apache math common, we do test the
    // two variants produce the same results.

    @Test
    void testInverseGamma() throws IOException {
        DataReader d = new DataReader("inverseGamma");
        for(double alpha = 0.1; alpha < 5; alpha += 0.1) {
            for(double beta = 0.1; beta < 5; beta += 0.1) {
                for(double value = 0.1; value < 100; value++) {
                    double localValue = DistributionSampling.probabilityInverseGamma(value, alpha, beta);
                    double storedValue = d.read(value, alpha, beta);
                    testValueLog(localValue, storedValue, value, alpha, beta);
                }
            }
        }
        d.close();
    }

    @Test
    void testLogInverseGamma() throws IOException {
        DataReader d = new DataReader("logInverseGamma");
        for(double alpha = 0.1; alpha < 5; alpha += 0.1) {
            for(double beta = 0.1; beta < 5; beta += 0.1) {
                for(double value = 0.1; value < 100; value++) {
                    double localValue = DistributionSampling.logProbabilityInverseGamma(value, alpha, beta);
                    double storedValue = d.read(value, alpha, beta);
                    testValueLog(localValue, storedValue, value, alpha, beta);
                }
            }
        }
        d.close();
    }

    @Test
    void testStudentT() throws IOException {
        DataReader d = new DataReader("studentT");
        for(double v = 0.1; v < 100; v += 0.25) {
            for(double value = -50; value <= 50; value += 0.5) {
                double localValue = DistributionSampling.probabilityStudentT(value, v);
                double storedValue = d.read(value, v);
                testValueNormal(localValue, storedValue, value, v);
            }
        }
        d.close();
    }

    @Test
    void testLogStudentT() throws IOException {
        DataReader d = new DataReader("logStudentT");
        for(double v = 0.1; v < 100; v += 0.25) {
            for(double value = -50; value <= 50; value += 0.5) {
                double localValue = DistributionSampling.logProbabilityStudentT(value, v);
                double storedValue = d.read(value, v);
                testValueLog(localValue, storedValue, value, v);
            }
        }
        d.close();
    }

    @Test
    void testCauchy() throws IOException {
        DataReader d = new DataReader("cauchy");
        for(double location = -3; location <= 3; location += 0.5) {
            for(double scale = 0.1; scale < 10; scale += 0.1) {
                for(double value = -50; value <= 50; value += 0.5) {
                    double localValue = DistributionSampling.probabilityCauchy(value, location, scale);
                    double storedValue = d.read(value, location, scale);
                    testValueNormal(localValue, storedValue, value, location, scale);
                }
            }
        }
        d.close();
    }

    @Test
    void testLogCauchy() throws IOException {
        DataReader d = new DataReader("logCauchy");
        for(double location = -3; location <= 3; location += 0.5) {
            for(double scale = 0.1; scale < 10; scale += 0.1) {
                for(double value = -50; value <= 50; value += 0.5) {
                    double localValue = DistributionSampling.logProbabilityCauchy(value, location, scale);
                    double storedValue = d.read(value, location, scale);
                    testValueLog(localValue, storedValue, value, location, scale);
                }
            }
        }
        d.close();
    }

    @Test
    void testHalfCauchy() throws IOException {
        DataReader d = new DataReader("halfCauchy");
        for(double location = -3; location <= 3; location += 0.5) {
            for(double scale = 0.1; scale < 10; scale += 0.1) {
                for(double value = -50; value <= 50; value += 0.5) {
                    double localValue = DistributionSampling.probabilityHalfCauchy(value, location, scale);
                    double storedValue = d.read(value, location, scale);
                    if(value < location)
                        testValueNormal(localValue, 0, value, location, scale);
                    else
                        testValueNormal(localValue, 2 * storedValue, value, location, scale);
                }
            }
        }
        d.close();
    }

    @Test
    void testLogHalfCauchy() throws IOException {
        DataReader d = new DataReader("logHalfCauchy");
        for(double location = -3; location <= 3; location += 0.5) {
            for(double scale = 0.1; scale < 10; scale += 0.1) {
                for(double value = -50; value <= 50; value += 0.5) {
                    double localValue = DistributionSampling.logProbabilityHalfCauchy(value, location, scale);
                    double storedValue = d.read(value, location, scale);
                    if(value < location)
                        testValueLog(localValue, Double.NEGATIVE_INFINITY, value, location, scale);
                    else
                        testValueLog(localValue, storedValue + Math.log(2), value, location, scale);
                }
            }
        }
        d.close();
    }

    private static void testValueLog(double localValue, double storedValue, double... args) {
        boolean valuesCloseEnough = testDouble(localValue, storedValue);

        if(!valuesCloseEnough) {
            System.err.println("Local value = " + localValue + " Stored Value = " + storedValue);
            System.err.print("Arguments were:");
            for(double d:args)
                System.err.print(" " + d);
            System.err.println();
        }

        assertTrue(valuesCloseEnough);
    }

    private static void testValueNormal(double localValue, double storedValue, double... args) {
        boolean valuesCloseEnough = testDouble(localValue, storedValue);

        if(!valuesCloseEnough || localValue < 0) {
            System.err.println("Local value = " + localValue + " Stored Value = " + storedValue);
            System.err.print("Arguments were:");
            for(double d:args)
                System.err.print(" " + d);
            System.err.println();
        }

        assertTrue(valuesCloseEnough);
    }

    private static boolean testDouble(double localValue, double storedValue) {
        double epsilon = 0.001; // Level of error we will tolerate. This is higher than I would like due to the
        // approximation of factorial.

        if(storedValue == 0.0) { // If common value is zero, just look at the difference.
            double diff = Math.abs(localValue);
            return diff < epsilon;
        } else if(storedValue == Double.NEGATIVE_INFINITY || storedValue == Double.POSITIVE_INFINITY) {
            return localValue == storedValue;
        } else {
            double abs = Math.abs(localValue - storedValue);
            double diff = Math.abs(abs / storedValue);
            return diff < epsilon || abs < 1e-7;
        }
    }

    @Test
    void bernoulliProbability() {
        assertEquals(0.75, DistributionSampling.probabilityBernoulli(true, 0.75));
        assertEquals(0.25, DistributionSampling.probabilityBernoulli(false, 0.75));
    }

    @Test
    void logBernoulliProbability() {
        assertEquals(Math.log(0.75), DistributionSampling.logProbabilityBernoulli(true, 0.75));
        assertEquals(Math.log(0.25), DistributionSampling.logProbabilityBernoulli(false, 0.75));
    }

    @Test
    void betaProbability() {
        assertEquals(1, DistributionSampling.probabilityBeta(0.5, 1, 1));
        assertEquals(0, DistributionSampling.probabilityBeta(-0.5, 1, 1));
        assertEquals(0, DistributionSampling.probabilityBeta(1.5, 1, 1));
    }

    @Test
    void logBetaProbability() {
        assertEquals(0, DistributionSampling.logProbabilityBeta(0.5, 1, 1));
        assertEquals(Double.NEGATIVE_INFINITY, DistributionSampling.logProbabilityBeta(-0.5, 1, 1));
        assertEquals(Double.NEGATIVE_INFINITY, DistributionSampling.logProbabilityBeta(1.5, 1, 1));
    }

    @Test
    void binomialProbability() {
        assertEquals(0, DistributionSampling.probabilityBinomial(2, 1, 4));
        if(TestRuntime.checkProcessor()) {
            assertEquals(0.37499999999999983, DistributionSampling.probabilityBinomial(2, 0.5, 4));
            assertEquals(3.090430470547806E-80, DistributionSampling.probabilityBinomial(10, 0.2, 1000));
        }
    }

    @Test
    void logBinomialProbability() {
        assertEquals(Double.NEGATIVE_INFINITY, DistributionSampling.logProbabilityBinomial(2, 1, 4));
        if(TestRuntime.checkProcessor()) {
            assertEquals(-0.9808292530117266, DistributionSampling.logProbabilityBinomial(2, 0.5, 4));
            assertEquals(-183.07849704745996, DistributionSampling.logProbabilityBinomial(10, 0.2, 1000));
        }
    }

    /*
     * @Test void uniformProbability() { assertEquals(1.0, DistributionSampling.probabilityUniform(0.5, -0.4, 0.6));
     * assertEquals(0.5, DistributionSampling.probabilityUniform(0.5, 0, 2)); assertEquals(0.0,
     * DistributionSampling.probabilityUniform(0.5, 2, 3)); assertEquals(0.5,
     * DistributionSampling.probabilityUniform(-1.5, -3, -1)); }
     * 
     * @Test void logUniformProbability() { assertEquals(Math.log(1.0),
     * Math.abs(DistributionSampling.logProbabilityUniform(0.5, -0.4, 0.6))); assertEquals(Math.log(0.5),
     * DistributionSampling.logProbabilityUniform(0.5, 0, 2)); assertEquals(Double.NEGATIVE_INFINITY,
     * DistributionSampling.logProbabilityUniform(0.5, 2, 3)); assertEquals(Math.log(0.5),
     * DistributionSampling.logProbabilityUniform(-1.5, -3, -1)); }
     */

    // class Gamma Test
    static final double accuracyGood = 0x1.0p-48;
    static final double accuracyOkay = 0x1.0p-45;
    static final double accuracySloppy = 0x1.0p-43;

    private void approxEqualsCheck(double x, double observed, double expected, String test, double accuracy) {
        double numerator = observed - expected;
        double ratio = numerator / expected;
        ratio = Math.abs(ratio);
        assertTrue(ratio < accuracy,
                "Fail: Gamma(" + x + ") = " + observed + " but should be " + expected + " by " + test);
    }

    @Test
    void gammaFunctionTests() {
        // Identity: Gamma(x) = (x-1)!
        for(int j = 1, n = 1; j <= 12; n *= j, ++j) {
            double x = j;
            double expected = n;
            double observed = Gamma.gamma(x);
            approxEqualsCheck(x, observed, expected, "Gamma(x) = (x-1)!", accuracyGood);
        }
        // Identity: Gamma(x) = (x-1)*Gamma(x-1)
        for(int j = 0; j <= 1000; ++j) {
            double x = (j - 500) / 50.0;
            if(x > 1.0 || x != Math.floor(x)) {
                double observed = Gamma.gamma(x);
                double expected = (x - 1.0) * Gamma.gamma(x - 1);
                approxEqualsCheck(x, observed, expected, "Gamma(x) = (x-1)*Gamma(x-1)", accuracyOkay);
            }
        }
        // Identity: Gamma(x) = pi/(Gamma(1-x)*sin(pi*x))
        for(int j = 0; j <= 1000; ++j) {
            double x = j / 50.0;
            if(x != Math.floor(x)) {
                double observed = Gamma.gamma(x);
                double expected = Math.PI / (Gamma.gamma(1.0 - x) * Math.sin(Math.PI * x));
                approxEqualsCheck(x, observed, expected, "Gamma(x) = pi/(Gamma(1-x)*sin(pi*x))", accuracySloppy);
            }
        }
        // Identity: Gamma(x) = exp(lgamma(x))
        for(int j = 1; j <= 1000; ++j) {
            double x = j / 50.0;
            if(x > 0 || x != Math.floor(x)) {
                double observed = Gamma.gamma(x);
                double expected = Math.exp(Gamma.logGamma(x));
                approxEqualsCheck(x, observed, expected, "Gamma(x) = exp(lgamma(x))", accuracyGood);
            }
        }
    }
}
