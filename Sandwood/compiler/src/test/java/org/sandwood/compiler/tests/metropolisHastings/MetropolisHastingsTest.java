/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.metropolisHastings;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.compilation.ChildFirstClassLoader;
import org.sandwood.compiler.compilation.util.CompileUtils;
import org.sandwood.compiler.compilation.util.SandwoodCompileError;
import org.sandwood.compiler.dataflowGraph.Sandwood;
import org.sandwood.compiler.tests.exceptions.SandwoodTestException;
import org.sandwood.compiler.tests.util.CompilerState;
import org.sandwood.compiler.tests.util.TestStringGenerator;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.RetentionPolicy;
import org.sandwood.runtime.model.variables.ObservedArrayShapeable;
import org.sandwood.runtime.model.variables.ObservedBoolean;
import org.sandwood.runtime.model.variables.ObservedBooleanArray;
import org.sandwood.runtime.model.variables.ObservedDouble;
import org.sandwood.runtime.model.variables.ObservedDoubleArray;
import org.sandwood.runtime.model.variables.ObservedInteger;
import org.sandwood.runtime.model.variables.ObservedIntegerArray;
import org.sandwood.runtime.model.variables.ObservedObjectArray;
import org.sandwood.runtime.model.variables.ObservedObjectArrayShapeable;

public class MetropolisHastingsTest {

    private static final String sourceDir = "src" + File.separator + "test" + File.separator + "resources"
            + File.separator + "testInputs";
    private static final int noSamples = 750;
    private static final int noPriorSamples = 10;
    private static final int burnin = 500;
    private static final double scalarBoundFraction = 0.075;
    private static final double arrayBoundFraction = 0.075;
    private ChildFirstClassLoader cl;
    private static final MetropolisHastingsTest classInstance = new MetropolisHastingsTest();

    private static class TestDesc {
        public final ExecutionTarget target;
        public final String file;

        public TestDesc(String file, ExecutionTarget target) {
            this.target = target;
            this.file = file;
        }

        @Override
        public String toString() {
            return "File: " + file + ", Target: " + target;
        }
    }

    private static String[] getFileNameArgs() {
        String packageDir = TestStringGenerator.getPackageDir(classInstance, sourceDir);

        File dir = new File(packageDir);
        assertTrue(dir.isDirectory());
        String[] files = dir.list((dir1, name) -> name.endsWith(".sandwood"));

        for(int i = 0; i < files.length; i++)
            files[i] = packageDir + File.separator + files[i];

        Arrays.sort(files);

        return files;
    }

    static Stream<TestDesc> getTestDescs() {
        List<ExecutionTarget> targets = new ArrayList<>();
        targets.add(null);
        for(ExecutionTarget e:ExecutionTarget.implementedTargets)
            targets.add(e);

        String[] files = getFileNameArgs();
        List<TestDesc> tests = new ArrayList<>(files.length * targets.size());
        for(String file:files)
            for(ExecutionTarget target:targets)
                tests.add(new TestDesc(file, target));
        return tests.stream();
    }

    private String getModelName(TestDesc testDesc) {
        return testDesc.file.substring(sourceDir.length() + 1, testDesc.file.length() - 9).replace(File.separator, ".");
    }

    @BeforeEach
    void resetStaticValues() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        CompilerState.reset();
    }

    // Set temporary and target directories.
    private File targetDir;

    @ParameterizedTest
    @MethodSource("getTestDescs")
    @DisplayName("Test Metropolis Hastings")
    void testFile(TestDesc testDesc) {
        try {
            targetDir = Files.createTempDirectory(null).toFile();
            CompilationOptions opts = new CompilationOptions();
            opts.setTargetDirectory(targetDir.getAbsolutePath());
            // System.out.println("Compiling to: " + opts.getTargetDirectory());

            opts.setModelPath(sourceDir);
            opts.setModelFile(testDesc.file);
            List<SandwoodCompileError> errors = Sandwood.compileModel(opts).getErrors();
            assertEquals(CompileUtils.parseErrors(errors), "");

            testGibbsConstructor(testDesc, targetDir.getAbsolutePath());

        } catch(Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            assertTrue(false, e.getMessage());
        }
    }

    // TODO work out a better structure for this.
    // TODO change this so a wider range of priors is generated rather than a set of very similar priors.
    private void testGibbsConstructor(TestDesc testDesc, String compiledDir) throws Exception {
        Class<?> c = getClass(compiledDir, getModelName(testDesc));
        Model model = getModel(c, testDesc.target);
        model.setDefaultRetentionPolicy(RetentionPolicy.SAMPLE);

        if(testDesc.file.contains("Dirichlet")) {
            setShape(model, c, "observed", Integer.valueOf(noSamples));
            assertTrue(model.readyExecute());

            // Generate samples
            model.execute(noPriorSamples);
            // Recover results
            double[][] priorSamples = (double[][]) getSampleValues(c, model, "prior");
            assertEquals(priorSamples.length, noPriorSamples);
            Object[] rvSamples = (Object[]) getSampleValues(c, model, "output");
            assertEquals(rvSamples.length, noPriorSamples);

            model.close();

            double bound = getDirichletBound();
            double errorSum = 0;
            double errorMax = 0;
            // Construct inputs for inference
            for(int i = 0; i < noPriorSamples; i++) {
                // TODO remove the recreation of the model once new set values are allowed.
                model = getModel(c, testDesc.target);
                model.setDefaultRetentionPolicy(RetentionPolicy.SAMPLE);
                model.setBurnin(burnin);
                setValue(model, c, "observed", rvSamples[i]);

                // Infer priors
                model.inferValues(noSamples);

                // Get results
                double[][] generatedPriorSamples = (double[][]) getSampleValues(c, model, "prior");
                assertEquals(generatedPriorSamples.length, noSamples);

                // Process the priors and compare.
                double error = processPriors(priorSamples[i], generatedPriorSamples, bound);
                if(error > errorMax)
                    errorMax = error;
                errorSum += error;

                model.close();
            }
            System.out.println(
                    "Max Error: " + errorMax + " \tAverage difference: " + errorSum / noSamples + " \tBound: " + bound);
            assertTrue(errorSum / noSamples < bound / 10);
        } else if(testDesc.file.contains("Multinomial")) {
            setShape(model, c, "observed", Integer.valueOf(noSamples));
            assertTrue(model.readyExecute());

            // Generate samples
            model.execute(noPriorSamples);
            // Recover results
            int[][] priorSamples = (int[][]) getSampleValues(c, model, "prior");
            assertEquals(priorSamples.length, noPriorSamples);
            Object[] rvSamples = (Object[]) getSampleValues(c, model, "output");
            assertEquals(rvSamples.length, noPriorSamples);

            model.close();

            double bound = getMultinomialBound();
            double errorSum = 0;
            double errorMax = 0;
            // Construct inputs for inference
            for(int i = 0; i < noPriorSamples; i++) {
                // TODO remove the recreation of the model once new set values are allowed.
                model = getModel(c, testDesc.target);
                model.setDefaultRetentionPolicy(RetentionPolicy.SAMPLE);
                model.setBurnin(burnin);
                setValue(model, c, "observed", rvSamples[i]);

                // Infer priors
                model.inferValues(noSamples);

                // Get results
                int[][] generatedPriorSamples = (int[][]) getSampleValues(c, model, "prior");
                assertEquals(generatedPriorSamples.length, noSamples);

                // Process the priors and compare.
                double error = processPriors(priorSamples[i], generatedPriorSamples, bound);
                if(error > errorMax)
                    errorMax = error;
                errorSum += error;

                model.close();
            }
            System.out.println(
                    "Max Error: " + errorMax + " \tAverage difference: " + errorSum / noSamples + " \tBound: " + bound);
            assertTrue(errorSum / noSamples < bound / 10);
        } else if(testDesc.file.contains("Uniform")) {
            setShape(model, c, "observed", Integer.valueOf(noSamples));
            assertTrue(model.readyExecute());

            // Generate samples
            model.execute(noPriorSamples);
            // Recover results
            double[] priorSamples = (double[]) getSampleValues(c, model, "prior");
            assertEquals(priorSamples.length, noPriorSamples);
            Object[] rvSamples = (Object[]) getSampleValues(c, model, "output");
            assertEquals(rvSamples.length, noPriorSamples);

            model.close();

            double bound = getBound(priorSamples);
            double errorSum = 0;
            double errorMax = 0;
            // Construct inputs for inference
            for(int i = 0; i < noPriorSamples; i++) {
                // TODO remove the recreation of the model once new set values are allowed.
                model = getModel(c, testDesc.target);
                model.setDefaultRetentionPolicy(RetentionPolicy.SAMPLE);
                model.setBurnin(burnin);
                setValue(model, c, "observed", rvSamples[i]);

                // Infer priors
                model.inferValues(noSamples);

                // Get results
                double[] generatedPriorSamples = (double[]) getSampleValues(c, model, "prior");
                assertEquals(generatedPriorSamples.length, noSamples);

                // Process the priors and compare.
                double error = processPriors(priorSamples[i], generatedPriorSamples, bound);
                if(error > errorMax)
                    errorMax = error;
                errorSum += error;

                model.close();
            }
            System.out.println(
                    "Max Error: " + errorMax + " \tAverage difference: " + errorSum / noSamples + " \tBound: " + bound);
            assertTrue(errorSum / noSamples < bound / 5);
        } else {
            int noRVSamples = noSamples * 10;
            // Modifications to the sample count are due to there being less choices for the prior value, and mode
            // samples needed to stay within bounds. A better strategy is need for this in the long term.
            setShape(model, c, "observed", Integer.valueOf(noRVSamples));
            assertTrue(model.readyExecute());

            // Generate samples
            model.execute(noPriorSamples);
            // Recover results
            int[] priorSamples = (int[]) getSampleValues(c, model, "prior");
            assertEquals(priorSamples.length, noPriorSamples);
            Object[] rvSamples = (Object[]) getSampleValues(c, model, "output");
            assertEquals(rvSamples.length, noPriorSamples);

            model.close();

            double bound = getBound(priorSamples);
            double errorSum = 0;
            double errorMax = 0;
            // Construct inputs for inference
            for(int i = 0; i < noPriorSamples; i++) {
                // TODO remove the recreation of the model once new set values are allowed.
                model = getModel(c, testDesc.target);
                model.setDefaultRetentionPolicy(RetentionPolicy.SAMPLE);
                model.setBurnin(burnin);
                setValue(model, c, "observed", rvSamples[i]);

                // Infer priors
                model.inferValues(noRVSamples);

                // Get results
                int[] generatedPriorSamples = (int[]) getSampleValues(c, model, "prior");
                assertEquals(generatedPriorSamples.length, noRVSamples);

                // Process the priors and compare.
                double error = processPriors(priorSamples[i], generatedPriorSamples, bound);
                if(error > errorMax)
                    errorMax = error;
                errorSum += error;

                model.close();
            }
            System.out.println(
                    "Max Error: " + errorMax + " \tAverage difference: " + errorSum / noSamples + " \tBound: " + bound);
            assertTrue(errorSum / noSamples < bound / 5);
        }
    }

    private double getBound(int[] priorSamples) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for(double v:priorSamples) {
            if(v < min)
                min = v;
            if(v > max)
                max = v;
        }

        return (max - min) * scalarBoundFraction;
    }

    private double getBound(double[] priorSamples) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for(double v:priorSamples) {
            if(v < min)
                min = v;
            if(v > max)
                max = v;
        }

        return (max - min) * scalarBoundFraction;
    }

    private double getDirichletBound() {
        return arrayBoundFraction;
    }

    private double getMultinomialBound() {
        return arrayBoundFraction;
    }

    private double processPriors(double priorSample, double[] generatedPriorSamples, double bound) {
        double sum = 0;
        for(double generatedPriorSample:generatedPriorSamples)
            sum += generatedPriorSample;
        double average = sum / generatedPriorSamples.length;
        double averageDifference = Math.abs(priorSample - average);
        if(averageDifference >= bound) {
            System.out.println("Prior sample: " + priorSample + "\nAverage Generated: " + average + "\nDifference: "
                    + averageDifference + "\nBound: " + bound);
            assertTrue(false);
        }
        return averageDifference;
    }

    private double processPriors(int priorSample, int[] generatedPriorSamples, double bound) {
        double sum = 0;
        for(int generatedPriorSample:generatedPriorSamples)
            sum += generatedPriorSample;
        double average = sum / generatedPriorSamples.length;
        double averageDifference = Math.abs(priorSample - average);
        if(averageDifference >= bound) {
            System.out.println("Prior sample: " + priorSample + "\nAverage Generated: " + average + "\nDifference: "
                    + averageDifference + "\nBound: " + bound);
            assertTrue(false);
        }
        return averageDifference;
    }

    private double processPriors(double[] priorSample, double[][] generatedPriorSamples, double bound) {
        int length = priorSample.length;
        double sum[] = new double[length];
        for(double[] generatedPriorSample:generatedPriorSamples)
            for(int i = 0; i < length; i++)
                sum[i] += generatedPriorSample[i];
        for(int i = 0; i < length; i++)
            sum[i] /= generatedPriorSamples.length;

        double averageDifference = dist(priorSample, sum);

        if(averageDifference >= bound) {
            System.out.println("Prior sample: " + Arrays.toString(priorSample) + "\nAverage: " + Arrays.toString(sum)
                    + "\nDifference: " + averageDifference + "\nBound: " + bound);
            System.out.println(Arrays.deepToString(generatedPriorSamples));
            assertTrue(false);
        }
        return averageDifference;
    }

    private double processPriors(int[] priorSample, int[][] generatedPriorSamples, double bound) {
        int length = priorSample.length;
        double sum[] = new double[length];
        for(int[] generatedPriorSample:generatedPriorSamples)
            for(int i = 0; i < length; i++)
                sum[i] += generatedPriorSample[i];
        for(int i = 0; i < length; i++)
            sum[i] /= generatedPriorSamples.length;

        double averageDifference = dist(priorSample, sum);

        if(averageDifference >= bound) {
            System.out.println("Prior sample: " + Arrays.toString(priorSample) + "\nAverage: " + Arrays.toString(sum)
                    + "\nDifference: " + averageDifference + "\nBound: " + bound);
            System.out.println(Arrays.deepToString(generatedPriorSamples));
            assertTrue(false);
        }
        return averageDifference;
    }

    private double dist(double[] trueValue, double[] sampledValue) {
        double difference = 0;
        double length = 0;
        for(int j = 0; j < trueValue.length; j++) {
            double v1 = trueValue[j];
            length += v1 * v1;
            double v2 = sampledValue[j];
            v1 -= v2;
            difference += v1 * v1;
        }
        return Math.sqrt(difference) / Math.sqrt(length);
    }

    private double dist(int[] trueValue, double[] sampledValue) {
        double difference = 0;
        double length = 0;
        for(int j = 0; j < trueValue.length; j++) {
            int v1 = trueValue[j];
            length += v1 * v1;
            double v2 = sampledValue[j];
            v1 -= v2;
            difference += v1 * v1;
        }
        return Math.sqrt(difference) / Math.sqrt(length);
    }

    private Object getSampleValues(Class<?> c, Model model, String resultName)
            throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Field f = c.getField(resultName);
        Object outputField = f.get(model);

        Class<?> fieldClass = outputField.getClass();
        Method outputMethod = fieldClass.getMethod("getSamples");
        return outputMethod.invoke(outputField, new Object[0]);
    }

    private void setValue(Model model, Class<?> c, String inputName, Object input) throws Exception {
        Field f = c.getField(inputName);
        Object inputField = f.get(model);
        if(inputField instanceof ObservedDouble) {
            ((ObservedDouble) inputField).set((Double) input);
        } else if(inputField instanceof ObservedDoubleArray) {
            ((ObservedDoubleArray) inputField).set((double[]) input);
        } else if(inputField instanceof ObservedInteger) {
            ((ObservedInteger) inputField).set((Integer) input);
        } else if(inputField instanceof ObservedIntegerArray) {
            ((ObservedIntegerArray) inputField).set((int[]) input);
        } else if(inputField instanceof ObservedBoolean) {
            ((ObservedBoolean) inputField).set((Boolean) input);
        } else if(inputField instanceof ObservedBooleanArray) {
            ((ObservedBooleanArray) inputField).set((boolean[]) input);
        } else if(inputField instanceof ObservedObjectArray<?>) {
            for(Method m:inputField.getClass().getMethods()) {
                if(m.getName().equals("set")) {
                    m.invoke(inputField, input);
                    break;
                }
            }
        } else
            throw new SandwoodTestException("Unknown input type");
    }

    private void setShape(Model model, Class<?> c, String inputName, Object input) throws Exception {
        Field f = c.getField(inputName);
        Object inputField = f.get(model);
        if(inputField instanceof ObservedArrayShapeable) {
            ((ObservedArrayShapeable) inputField).setLength((Integer) input);
        } else if(inputField instanceof ObservedObjectArrayShapeable<?, ?>) {
            for(Method m:inputField.getClass().getMethods()) {
                if(m.getName().equals("setShape")) {
                    m.invoke(inputField, input);
                    break;
                }
            }
        } else
            throw new SandwoodTestException("Unknown input type");
    }

    private Class<?> getClass(String dir, String modelName) {
        try {
            // Construct a URL[] containing the output directory.
            File file = new File(dir);
            URL url = file.toURI().toURL();
            URL[] urls = new URL[] { url };

            // Load the contents
            cl = new ChildFirstClassLoader(urls);
            // Load the class

            return cl.loadClass(modelName);
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            assertTrue(false);
        }
        return null;
    }

    private Model getModel(Class<?> cls, ExecutionTarget target) {
        try {
            Class<?>[] types = new Class<?>[0];
            Object[] args = new Object[0];

            Constructor<?> cons = cls.getConstructor(types);
            Model m = (Model) cons.newInstance(args);
            if(target != null)
                m.setExecutionTarget(target);

            // Initialize the seed
            m.initializeSeed(42);

            return m;
        } catch(Exception e) {
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
