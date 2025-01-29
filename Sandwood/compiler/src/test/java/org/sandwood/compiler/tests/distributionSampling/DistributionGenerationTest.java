/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.distributionSampling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.compilation.ChildFirstClassLoader;
import org.sandwood.compiler.compilation.util.CompileUtils;
import org.sandwood.compiler.compilation.util.SandwoodCompileError;
import org.sandwood.compiler.dataflowGraph.Sandwood;
import org.sandwood.compiler.tests.exceptions.SandwoodTestException;
import org.sandwood.compiler.tests.util.CompilerState;
import org.sandwood.compiler.tests.util.TestStringGenerator;
import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.RetentionPolicy;
import org.sandwood.runtime.model.variables.ComputedBoolean;
import org.sandwood.runtime.model.variables.ComputedBooleanArray;
import org.sandwood.runtime.model.variables.ComputedDouble;
import org.sandwood.runtime.model.variables.ComputedDoubleArray;
import org.sandwood.runtime.model.variables.ComputedInteger;
import org.sandwood.runtime.model.variables.ComputedIntegerArray;
import org.sandwood.runtime.model.variables.ComputedObjectArray;
import org.sandwood.runtime.model.variables.ObservedBoolean;
import org.sandwood.runtime.model.variables.ObservedBooleanArray;
import org.sandwood.runtime.model.variables.ObservedDouble;
import org.sandwood.runtime.model.variables.ObservedDoubleArray;
import org.sandwood.runtime.model.variables.ObservedInteger;
import org.sandwood.runtime.model.variables.ObservedIntegerArray;
import org.sandwood.runtime.model.variables.ObservedObjectArray;

public class DistributionGenerationTest {

    private static final String sourceDir = "src" + File.separator + "test" + File.separator + "resources"
            + File.separator + "testInputs";
    private static final int noSamples = 1000000;
    private ChildFirstClassLoader cl;
    private static final DistributionGenerationTest classInstance = new DistributionGenerationTest();
    private static final String packageDir = TestStringGenerator.getPackageDir(classInstance, sourceDir);

    @Test
    void testBernoulli() throws Exception {
        String modelFile = getModelFile("Bernoulli");
        compileModel(modelFile);

        Object[] args = new Object[1];
        for(double p = 0.0; p <= 1; p += 0.1) {
            args[0] = p;
            testDistribution(modelFile, args);
        }
    }

    @Test
    void testBeta() throws Exception {
        String modelFile = getModelFile("Beta");
        compileModel(modelFile);

        Object[] args = new Object[2];
        for(double alpha = 0.1; alpha < 2.2; alpha++) {
            args[0] = alpha;
            for(double beta = 0.1; beta < 2.2; beta++) {
                args[1] = beta;
                testDistribution(modelFile, args);
            }
        }
    }

    @Test
    void testBinomial() throws Exception {
        String modelFile = getModelFile("Binomial");
        compileModel(modelFile);

        Object[] args = new Object[2];
        for(double p = 0.0; p <= 1; p += 0.2) {
            args[0] = p;
            for(int length = 1; length <= 128; length = 2 * length) {
                args[1] = length;
                testDistribution(modelFile, args);
            }
        }
    }

    @Test
    void testCategorical() throws Exception {
        String modelFile = getModelFile("Categorical");
        compileModel(modelFile);

        Object[] args = new Object[1];
        {
            double[] probs = { 1.0 };
            args[0] = probs;
            testDistribution(modelFile, args);
        }
        {
            double[] probs = { 0.0, 0.1, 0.2, 0.7 };
            args[0] = probs;
            testDistribution(modelFile, args);
        }
        {
            double[] probs = { 0.0, 0.0, 0.3, 0.7 };
            args[0] = probs;
            testDistribution(modelFile, args);
        }
        {
            double[] probs = { 0.7, 0.3, 0.0, 0.0 };
            args[0] = probs;
            testDistribution(modelFile, args);
        }
        {
            double[] probs = { 0.7, 0.3, 0.1, 0.0 };
            args[0] = probs;
            testDistribution(modelFile, args);
        }
    }

    @Test
    void testExponential() throws Exception {
        String modelFile = getModelFile("Exponential");
        compileModel(modelFile);

        Object[] args = new Object[1];
        for(double rate = 0.1; rate < 5.1; rate += 0.5) {
            args[0] = rate;
            testDistribution(modelFile, args);
        }
    }

    @Test
    void testGamma() throws Exception {
        String modelFile = getModelFile("Gamma");
        compileModel(modelFile);

        Object[] args = new Object[2];
        for(double alpha = 0.1; alpha < 5.2; alpha += 1) {
            args[0] = alpha;
            for(double beta = 0.1; beta < 5.2; beta += 1) {
                args[1] = beta;
                testDistribution(modelFile, args);
            }
        }
    }

    @Test
    void testGaussian() throws Exception {
        String modelFile = getModelFile("Gaussian");
        compileModel(modelFile);

        Object[] args = new Object[2];
        for(double mean = -1; mean < 1.1; mean += 1) {
            args[0] = mean;
            for(double variance = 0.1; variance < 5.2; variance += 1) {
                args[1] = variance;
                testDistribution(modelFile, args);
            }
        }
    }

    @Test
    void testGeometric() throws Exception {
        String modelFile = getModelFile("Geometric");
        compileModel(modelFile);

        Object[] args = new Object[1];
        for(double p = 0.1; p <= 1; p += 0.1) {
            args[0] = p;
            testDistribution(modelFile, args);
        }
    }

    @Test
    void testNegativeBinomial() throws Exception {
        String modelFile = getModelFile("NegativeBinomial");
        compileModel(modelFile);

        Object[] args = new Object[2];
        for(double p = 0.1; p <= 1; p += 0.1) {
            args[0] = p;
            for(int r = 1; r <= 5; r++) {
                args[1] = r;
                testDistribution(modelFile, args);
            }
        }
    }

    @Test
    void testPoisson() throws Exception {
        String modelFile = getModelFile("Poisson");
        compileModel(modelFile);

        Object[] args = new Object[1];
        for(double lambda = 0.5; lambda < 100; lambda += 5) {
            args[0] = lambda;
            testDistribution(modelFile, args);
        }
    }

    @Test
    void testInverseGamma() throws Exception {
        String modelFile = getModelFile("InverseGamma");
        compileModel(modelFile);

        Object[] args = new Object[2];
        for(double alpha = 0.1; alpha < 5; alpha += 1.5) {
            args[0] = alpha;
            for(double beta = 0.1; beta < 5; beta += 1.5) {
                args[1] = beta;
                testDistribution(modelFile, args);
            }
        }
    }

    @Test
    void testStudentT() throws Exception {
        String modelFile = getModelFile("StudentT");
        compileModel(modelFile);

        Object[] args = new Object[1];
        for(double v = 0.1; v < 105; v *= 2) {
            args[0] = v;
            testDistribution(modelFile, args);
        }
    }

    @Test
    void testCauchy() throws Exception {
        String modelFile = getModelFile("Cauchy");
        compileModel(modelFile);

        Object[] args = new Object[2];
        for(double location = -2.5; location <= 1.5; location += 1) {
            args[0] = location;
            for(double scale = 0.1; scale < 10.2; scale += 2) {
                args[1] = scale;
                testDistribution(modelFile, args);
            }
        }
    }

    @Test
    void testHalfCauchy() throws Exception {
        String modelFile = getModelFile("HalfCauchy");
        compileModel(modelFile);

        Object[] args = new Object[2];
        for(double location = -2.5; location <= 1.5; location += 1) {
            args[0] = location;
            for(double scale = 0.1; scale < 10.2; scale += 2) {
                args[1] = scale;
                testDistribution(modelFile, args);
            }
        }
    }

    private String getModelFile(String modelName) {
        return packageDir + File.separator + modelName + ".sandwood";
    }

    private String getModelClassName(String file) {
        return file.substring(sourceDir.length() + 1, file.length() - 9).replace(File.separator, ".");
    }

    @BeforeEach
    void resetStaticValues() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        CompilerState.reset();
    }

    // Set temporary and target directories.
    private File targetDir;

    void compileModel(String file) {
        try {
            targetDir = Files.createTempDirectory(null).toFile();
            CompilationOptions opts = new CompilationOptions();
            opts.setTargetDirectory(targetDir.getAbsolutePath());

            opts.setModelPath(sourceDir);
            opts.setModelFile(file);
            List<SandwoodCompileError> errors = Sandwood.compileModel(opts).getErrors();
            assertEquals(CompileUtils.parseErrors(errors), "");

        } catch(Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            assertTrue(false, e.getMessage());
        }
    }

    private void testDistribution(String file, Object[] args) throws Exception {
        Class<?> c = getClass(file);
        Model model = getModel(c, args);
        model.setDefaultRetentionPolicy(RetentionPolicy.SAMPLE);

        assertTrue(model.readyExecute());

        // Generate samples
        model.execute(noSamples);

        // Recover results
        Object output = getOutputValues(c, model);

        // Test results
        Class<?> outputClass = output.getClass();
        if(outputClass == boolean[].class) {
            testBoolean((boolean[]) output, c, model);
        } else if(outputClass == double[].class) {
            testDouble((double[]) output, c, model);
        } else {
            testInteger((int[]) output, c, model);
        }

        // Tidy the model
        model.close();
    }

    private void testInteger(int[] values, Class<?> c, Model model) throws Exception {
        int numOutputs = values.length;

        Arrays.sort(values);

        int numSteps = 10000;

        int start = 0;
        int lastPos = 0;
        int lastValue = values[lastPos];
        double prob = getOutputProbability(Integer.valueOf(lastValue), c, model);

        int pos = 1;
        // Advance pos to the next value to be considered.
        while(pos < numOutputs && values[pos] == lastValue)
            pos++;

        double expected = 1 / numSteps;
        double total = 0;

        for(int i = 0; i < numSteps; i++) {
            int end = (int) (((i + 1) * (long) numOutputs) / numSteps);

            double cumulative = 0;
            boolean sum = true;

            while(sum) {
                if(pos < end) {
                    if(lastPos < start) {
                        cumulative += (prob * (pos - start)) / (pos - lastPos);
                    } else {
                        cumulative += prob;
                    }

                    int vStart = values[lastPos] + 1;
                    int vEnd = values[pos];
                    for(int v = vStart; v < vEnd; v++) {
                        cumulative += getOutputProbability(Integer.valueOf(v), c, model);
                    }

                    lastPos = pos;
                    lastValue = values[lastPos];
                    prob = getOutputProbability(Integer.valueOf(lastValue), c, model);

                    // Advance pos to the next value to be considered.
                    while(pos < numOutputs && values[pos] == lastValue)
                        pos++;
                } else {
                    if(lastPos < start) {
                        cumulative += (prob * (end - start)) / (pos - lastPos);
                    } else {
                        cumulative += (prob * (end - lastPos)) / (pos - lastPos);
                    }
                    sum = false;
                }
            }
            start = end;

            total += cumulative;
            double error = Math.abs(cumulative - expected);
            assertTrue(error < 0.0002);
        }
        assertTrue(Math.abs(total - 1) < 0.000005);
    }

    private void testDouble(double[] values, Class<?> c, Model model) throws Exception {
        int numOutputs = values.length;

        Arrays.sort(values);

        int numSteps = 100;
        double expectedValue = 1.0 / numSteps;
        double total = 0;
        double under = 0;
        double over = 0;

        // Offset clips the ends to remove parts where the distribution goes asymptotic.
        int offset = numOutputs / (2 * numSteps);
        int start = offset;
        double startValue = values[start];
        double startProb = getOutputProbability(Double.valueOf(startValue), c, model);
        for(int i = 0; i < numSteps - 1; i++) {
            int end = offset + (int) (((i + 1) * (long) numOutputs) / numSteps);

            double cumulative = 0;
            for(int j = start + 1; j < end; j++) {
                double endValue = values[j];
                double endProb = getOutputProbability(Double.valueOf(endValue), c, model);
                double distance = endValue - startValue;
                cumulative += (startProb + endProb) * distance / 2;

                startValue = endValue;
                startProb = endProb;
            }

            start = end;

            total += cumulative;
            double difference = (cumulative - expectedValue) / expectedValue;
            if(difference < under)
                under = difference;
            if(over < difference)
                over = difference;
        }

        assertTrue(-0.06 < under);
        assertTrue(over < 0.06);
        double residule = Math.abs(total - 99 * expectedValue);
        assertTrue(residule < 0.04);

        // Check that the min and max values generated can have probabilities calculated for them.
        double prob = getOutputProbability(Double.valueOf(values[0]), c, model);
        assertTrue(0 < prob);
        assertTrue(prob < Double.POSITIVE_INFINITY);

        prob = getOutputProbability(Double.valueOf(values[numOutputs - 1]), c, model);
        assertTrue(0 < prob);
        assertTrue(prob < Double.POSITIVE_INFINITY);
    }

    private void testBoolean(boolean[] output, Class<?> c, Model model) throws Exception {
        int sum = 0;
        for(int i = 0; i < output.length; i++) {
            if(output[i])
                sum++;
        }

        double pTrue = getOutputProbability(Boolean.TRUE, c, model);
        double pFalse = getOutputProbability(Boolean.FALSE, c, model);

        assertTrue((pTrue * output.length - sum) / output.length < 0.001);
        assertTrue((pFalse * output.length - (output.length - sum)) / output.length < 0.001);
    }

    private Object getOutputValues(Class<?> c, Model model)
            throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Field f = c.getField("output");
        Object outputField = f.get(model);

        Class<?> fieldClass = outputField.getClass();
        Method outputMethod = fieldClass.getMethod("getSamples");
        return outputMethod.invoke(outputField, new Object[0]);
    }

    private double getOutputProbability(Object output, Class<?> c, Model model) throws Exception {
        setOutputValue(model, c, output);
        model.inferProbabilities(1);
        return model.getProbability();
    }

    private void setOutputValue(Model model, Class<?> c, Object input) throws Exception {
        Field f = c.getField("output");
        Object inputField = f.get(model);
        if(inputField instanceof ObservedDouble) {
            ((ObservedDouble) inputField).setValue((Double) input);
        } else if(inputField instanceof ObservedDoubleArray) {
            ((ObservedDoubleArray) inputField).setValue((double[]) input);
        } else if(inputField instanceof ObservedInteger) {
            ((ObservedInteger) inputField).setValue((Integer) input);
        } else if(inputField instanceof ObservedIntegerArray) {
            ((ObservedIntegerArray) inputField).setValue((int[]) input);
        } else if(inputField instanceof ObservedBoolean) {
            ((ObservedBoolean) inputField).setValue((Boolean) input);
        } else if(inputField instanceof ObservedBooleanArray) {
            ((ObservedBooleanArray) inputField).setValue((boolean[]) input);
        } else if(inputField instanceof ObservedObjectArray<?>) {
            for(Method m:inputField.getClass().getMethods()) {
                if(m.getName().equals("setValue")) {
                    m.invoke(inputField, input);
                    break;
                }
            }
        } else if(inputField instanceof ComputedDouble) {
            ((ComputedDouble) inputField).setValue((Double) input);
        } else if(inputField instanceof ObservedDoubleArray) {
            ((ComputedDoubleArray) inputField).setValue((double[]) input);
        } else if(inputField instanceof ComputedInteger) {
            ((ComputedInteger) inputField).setValue((Integer) input);
        } else if(inputField instanceof ComputedIntegerArray) {
            ((ComputedIntegerArray) inputField).setValue((int[]) input);
        } else if(inputField instanceof ComputedBoolean) {
            ((ComputedBoolean) inputField).setValue((Boolean) input);
        } else if(inputField instanceof ComputedBooleanArray) {
            ((ComputedBooleanArray) inputField).setValue((boolean[]) input);
        } else if(inputField instanceof ComputedObjectArray<?>) {
            for(Method m:inputField.getClass().getMethods()) {
                if(m.getName().equals("setValue")) {
                    m.invoke(inputField, input);
                    break;
                }
            }
        } else
            throw new SandwoodTestException("Unknown input type");
    }

    private Class<?> getClass(String file) {
        try {
            // Construct a URL[] containing the output directory.
            URL url = targetDir.toURI().toURL();
            URL[] urls = new URL[] { url };

            // Load the contents
            cl = new ChildFirstClassLoader(urls);
            // Load the class
            String modelName = getModelClassName(file);
            return cl.loadClass(modelName);
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            assertTrue(false);
        }
        return null;
    }

    private Model getModel(Class<?> cls, Object[] args) {
        try {
            int numArgs = args.length;
            Class<?>[] types = new Class<?>[numArgs];
            for(int i = 0; i < numArgs; i++) {
                Class<?> type = args[i].getClass();
                if(type == Boolean.class)
                    types[i] = boolean.class;
                else if(type == Double.class)
                    types[i] = double.class;
                else if(type == Integer.class)
                    types[i] = int.class;
                else
                    types[i] = type;
            }

            Constructor<?> cons = cls.getConstructor(types);
            Model m = (Model) cons.newInstance(args);

            // Initialize the seed
            m.initializeSeed(42);

            return m;
        } catch(Exception e) {
            for(Constructor<?> con:cls.getConstructors()) {
                System.out.println(Arrays.toString(con.getParameterTypes()));
            }

            e.printStackTrace();
            System.err.println(e.getMessage());
            assertTrue(false);
        }

        return null;
    }

    @AfterEach
    protected void cleanup() throws IOException {
        // close the class loader if it was opened.
        if(cl != null) {
            cl.close();
            cl = null;
        }
        if(targetDir != null) {
            deleteFile(targetDir);
            targetDir = null;
        }
    }

    private void deleteFile(File file) {
        if(file.exists()) {
            if(file.isDirectory()) {
                for(File subFile:file.listFiles())
                    deleteFile(subFile);
            }
            file.delete();
        }
    }
}
