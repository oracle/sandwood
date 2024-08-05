/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.basicCompileAndRun;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.common.execution.ExecutionType;
import org.sandwood.common.tests.util.TestRuntime;
import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.compilation.ChildFirstClassLoader;
import org.sandwood.compiler.compilation.util.CompilationDesc;
import org.sandwood.compiler.compilation.util.CompileUtils;
import org.sandwood.compiler.tests.exceptions.SandwoodTestException;
import org.sandwood.compiler.tests.util.CompilerState;
import org.sandwood.compiler.tests.util.JsonResultsDecoder;
import org.sandwood.compiler.tests.util.JsonResultsEncoder;
import org.sandwood.compiler.tests.util.TestStringGenerator;
import org.sandwood.compiler.trees.outputTree.OutputSandwoodClass;
import org.sandwood.compiler.util.StringUtil;
import org.sandwood.runtime.exceptions.SandwoodJsonException;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.variables.ComputedVariable;
import org.sandwood.runtime.model.variables.IteratedRandomVariable;
import org.sandwood.runtime.model.variables.RandomVariable;

public abstract class ModelTestSkeleton {

    enum TestType {
        Gibbs,
        Evidence,
        EvidenceLog,
        Forward
    }

    private final Map<TestType, Map<ExecutionTarget, Map<String, Object>>> results;

    private final String tempDir = "tmp";
    private final String sourceDir = "src" + File.separator + "test" + File.separator + "resources" + File.separator
            + "expectedOutputs" + File.separator + "basicCompileAndRun";
    private final String className;

    private final boolean constructJava = false;
    private static final Set<TestType> constructing = new HashSet<>();

    private final TestStringGenerator tsg = new TestStringGenerator();

    private static final List<ExecutionTarget> targets = new ArrayList<>();

    private final boolean testValues;

    static {
        targets.add(null);
        for(ExecutionTarget e:ExecutionTarget.implementedTargets)
            targets.add(e);

        /*
         * constructing.add(TestType.Evidence); constructing.add(TestType.EvidenceLog);
         * constructing.add(TestType.Forward); constructing.add(TestType.Gibbs);
         */

    }

    protected boolean forwardInitialisationRequired = true;

    protected abstract CompilationDesc buildClass(CompilationOptions opts);

    protected abstract void initializeClass(Class<?> cls, Object o)
            throws IllegalAccessException, IllegalArgumentException, SecurityException, NoSuchFieldException;

    protected abstract void initializeClassForward(Class<?> cls, Object o)
            throws IllegalAccessException, IllegalArgumentException, SecurityException, NoSuchFieldException;

    protected abstract String[] getOutputFields(TestType testType);

    public ModelTestSkeleton() throws SandwoodJsonException, IOException {
        String[] parts = getClass().getName().split("\\.");
        className = parts[parts.length - 1];

        results = readResults(sourceDir, className);

        testValues = TestRuntime.checkEnvironment();
    }

    private Map<TestType, Map<ExecutionTarget, Map<String, Object>>> readResults(String sourceDir, String className)
            throws SandwoodJsonException, IOException {
        Map<TestType, Map<ExecutionTarget, Map<String, Object>>> m1 = new HashMap<>();
        for(TestType testType:TestType.values()) {
            if(!constructing.contains(testType)) {
                Map<ExecutionTarget, Map<String, Object>> m2 = new HashMap<>();
                for(ExecutionTarget target:targets) {
                    if(target != null) {
                        String filename = sourceDir + File.separator + className + File.separator + target.executionType
                                + "-" + testType + ".json";
                        Map<String, Object> data = JsonResultsDecoder.readData(filename);
                        m2.put(target, data);
                    }
                }
                m1.put(testType, m2);
            }
        }
        return m1;
    }

    private void writeResults(TestType testType, ExecutionType target, Map<String, Object> outputs) throws IOException {
        if(target != null) {
            String filename = sourceDir + File.separator + className + File.separator + target + "-" + testType
                    + ".json";
            JsonResultsEncoder.writeData(filename, outputs);
        }
    }

    static Stream<ExecutionTarget> getTargets() {
        return targets.stream();
    }

    @BeforeEach
    void resetStaticValues() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        CompilerState.reset();
    }

    /**
     * A test method to check that the code generated from a simple has not changed.
     */
    @Test
    void testJavaCode() {
        try {
            CompilationOptions opts = new CompilationOptions();
            opts.setCalculateIndividualProbabilities();
            List<OutputSandwoodClass> classes = buildClass(opts).classes;
            for(OutputSandwoodClass c:classes) {
                StringBuilder sb = new StringBuilder();
                c.toJava(sb);
                String java = sb.toString();
                // Normalise newline characters
                java = StringUtil.normalizeNewLines(java);
                // Tool to make changes to the expected result easier to incorporate.
                String filename = tsg.processString(this, sourceDir, c.getName(), java, constructJava, "txt");
                String javaCode;
                try {
                    javaCode = new String(Files.readAllBytes(Paths.get(filename)));
                } catch(IOException e) {
                    javaCode = "";
                    System.err.println("Failed to read file \"" + filename + "\"");
                }
                // Normalise newline characters This second use is required because GitHub
                // rewrites the data files.
                javaCode = StringUtil.normalizeNewLines(javaCode);
                assertEquals(javaCode, java);
            }
            assertFalse(constructJava, "Results are being written to files, this invalidates the tests.");
        } catch(SandwoodException e) {
            e.printStackTrace();
            fail("This code should not throw exceptions:" + e.getMessage());
        }
    }

    /**
     * A test method to check that we can compile to Java code.
     */

    @DisplayName("Compile and run l Gibbs iteration")
    @ParameterizedTest
    @MethodSource("getTargets")
    void testCompileToFilesAndRunGibbsRound(ExecutionTarget target) {
        try {
            CompilationOptions opts = new CompilationOptions();
            opts.setCalculateIndividualProbabilities();
            List<OutputSandwoodClass> classes = buildClass(opts).classes;
            opts.setTargetDirectory(tempDir);
            DiagnosticCollector<JavaFileObject> diagnostics = CompileUtils.compileToJava(opts, classes);
            if(!diagnostics.getDiagnostics().isEmpty()) {
                CompileUtils.printErrorsSimple(diagnostics);
                assertTrue(false);
            }

            // Construct a URL[] containing the output directory.
            File file = new File(tempDir);
            URL url = file.toURI().toURL();
            URL[] urls = new URL[] { url };

            // Load the contents
            ChildFirstClassLoader cl = new ChildFirstClassLoader(urls);
            // Load the class
            Class<?> cls = cl.loadClass("ClassName");
            Constructor<?> cons = cls.getConstructor();
            Model m = (Model) cons.newInstance();
            if(target != null) {
                m.setExecutionTarget(target);
                switch(target.executionType) {
                    case SingleThreadCPU:
                        m.setThreadCount(1);
                        break;
                    case MultiThreadCPU:
                        m.setThreadCount(6);
                        break;
                    default:
                        cl.close();
                        throw new SandwoodTestException("Unknown execution target " + target);
                }
            }

            initializeRandom(m);
            assertFalse(m.readyInferValues());
            initializeClass(cls, m);
            assertTrue(m.readyInferValues());

            // Call the backward pass method
            m.inferValues(1);

            // Call the get results method
            if(constructing.contains(TestType.Gibbs)) {
                if(target != null) {
                    Map<String, Object> outputs = new HashMap<>();
                    for(String resultName:getOutputFields(TestType.Gibbs)) {
                        Field f = cls.getField(resultName);
                        Object outputField = f.get(m);

                        Class<?> c = outputField.getClass();
                        Method outputMethod = c.getMethod("getSamples", new Class<?>[0]);
                        Object generated = outputMethod.invoke(outputField, new Object[0]);
                        outputs.put(resultName, generated);
                    }
                    writeResults(TestType.Gibbs, target.executionType, outputs);
                }
                assertTrue(false, "Results are being written");
            } else {
                if(testValues) {
                    Map<String, Object> gibbsResult = results.get(TestType.Gibbs)
                            .get((target == null) ? ExecutionTarget.singleThread : target);
                    for(String resultName:getOutputFields(TestType.Gibbs)) {
                        Object expected = gibbsResult.get(resultName);
                        Field f = cls.getField(resultName);
                        Object outputField = f.get(m);

                        Class<?> c = outputField.getClass();
                        Method outputMethod = c.getMethod("getSamples", new Class<?>[0]);
                        Object generated = outputMethod.invoke(outputField, new Object[0]);

                        compareValues(expected, generated);
                    }
                }
            }
            m.close();
            cl.close();
        } catch(Exception e) {
            e.printStackTrace();
            fail("This code should not throw exceptions:" + e.getMessage());
        }
    }

    /**
     * A test method to check that we can compile to Java code.
     */
    @DisplayName("Compile and run log evidence pass")
    @ParameterizedTest
    @MethodSource("getTargets")
    void testCompileToFilesAndRunEvidence(ExecutionTarget target) {
        runEvidence(TestType.Evidence, target);
    }

    /**
     * A test method to check that we can compile to Java code.
     */
    @DisplayName("Compile and run log evidence pass")
    @ParameterizedTest
    @MethodSource("getTargets")
    void testLogCompileToFilesAndRunEvidence(ExecutionTarget target) {

        runEvidence(TestType.EvidenceLog, target);
    }

    private void runEvidence(TestType testType, ExecutionTarget target) {
        try {
            CompilationOptions opts = new CompilationOptions();
            opts.setCalculateIndividualProbabilities();
            List<OutputSandwoodClass> classes = buildClass(opts).classes;
            opts.setTargetDirectory(tempDir);
            DiagnosticCollector<JavaFileObject> diagnostics = CompileUtils.compileToJava(opts, classes);
            if(!diagnostics.getDiagnostics().isEmpty()) {
                CompileUtils.printErrorsSimple(diagnostics);
                assertTrue(false);
            }

            // Construct a URL[] containing the output directory.
            File file = new File(tempDir);
            URL url = file.toURI().toURL();
            URL[] urls = new URL[] { url };

            // Load the contents
            ChildFirstClassLoader cl = new ChildFirstClassLoader(urls);
            // Load the class
            Class<?> cls = cl.loadClass("ClassName");
            Constructor<?> cons = cls.getConstructor();
            Model m = (Model) cons.newInstance();
            if(target != null) {
                m.setExecutionTarget(target);
                switch(target.executionType) {
                    case SingleThreadCPU:
                        m.setThreadCount(1);
                        break;
                    case MultiThreadCPU:
                        m.setThreadCount(6);
                        break;
                    default:
                        cl.close();
                        throw new SandwoodTestException("Unknown execution target " + target);
                }
            }

            initializeRandom(m);
            assertFalse(m.readyInferProbabilities());
            initializeClass(cls, m);
            assertTrue(m.readyInferProbabilities());

            // Call the evidence generation method
            m.inferProbabilities(1);

            // Call the get results method
            if(constructing.contains(testType)) { // Save the results if we are constructing outputs.
                if(target != null) {
                    Map<String, Object> outputs = new HashMap<>();
                    for(String resultName:getOutputFields(testType)) {
                        Field f = cls.getField(resultName);
                        Object outputField = f.get(m);
                        Object generated;

                        if(outputField instanceof RandomVariable) {
                            generated = (testType == TestType.EvidenceLog)
                                    ? ((RandomVariable) outputField).getLogProbability()
                                    : ((RandomVariable) outputField).getProbability();
                        } else if(outputField instanceof ComputedVariable) {
                            generated = (testType == TestType.EvidenceLog)
                                    ? ((ComputedVariable) outputField).getLogProbability()
                                    : ((ComputedVariable) outputField).getProbability();
                        } else {
                            generated = (testType == TestType.EvidenceLog)
                                    ? ((IteratedRandomVariable<?>) outputField).getLogProbability()
                                    : ((IteratedRandomVariable<?>) outputField).getProbability();
                            outputs.put(resultName, generated);
                        }
                        outputs.put(resultName, generated);
                    }
                    if(testType == TestType.EvidenceLog)
                        outputs.put("modelProbabilities", m.getLogProbability());
                    else
                        outputs.put("modelProbabilities", m.getProbability());

                    writeResults(testType, target.executionType, outputs);
                }
                assertTrue(false, "Results are being written");
            } else { // Test the results
                if(testValues) {
                    Map<String, Object> result = results.get(testType)
                            .get((target == null) ? ExecutionTarget.singleThread : target);

                    if(testType == TestType.EvidenceLog)
                        assertEquals(result.get("modelProbabilities"), m.getLogProbability());
                    else
                        assertEquals(result.get("modelProbabilities"), m.getProbability());
                    for(String resultName:getOutputFields(testType)) {
                        Field f = cls.getField(resultName);
                        Object outputField = f.get(m);

                        if(outputField instanceof RandomVariable) {
                            Double generated = (testType == TestType.EvidenceLog)
                                    ? ((RandomVariable) outputField).getLogProbability()
                                    : ((RandomVariable) outputField).getProbability();
                            Double expected = (Double) result.get(resultName);
                            assertEquals(expected, generated);
                        } else if(outputField instanceof ComputedVariable) {
                            Double generated = (testType == TestType.EvidenceLog)
                                    ? ((ComputedVariable) outputField).getLogProbability()
                                    : ((ComputedVariable) outputField).getProbability();
                            Double expected = (Double) result.get(resultName);
                            assertEquals(expected, generated);
                        } else {
                            Object generated = (testType == TestType.EvidenceLog)
                                    ? ((IteratedRandomVariable<?>) outputField).getLogProbability()
                                    : ((IteratedRandomVariable<?>) outputField).getProbability();
                            Object expected = result.get(resultName);

                            compareValues(expected, generated);
                        }

                    }
                }
            }
            m.close();
            cl.close();
        } catch(Exception e) {
            e.printStackTrace();
            fail("This code should not throw exceptions:" + e.getMessage());
        }
    }

    /**
     * A test method to check that we can compile to Java code.
     */

    @DisplayName("Compile and run forward pass")
    @ParameterizedTest
    @MethodSource("getTargets")
    void testCompileToFilesAndRunForwardPass(ExecutionTarget target) {
        try {
            CompilationOptions opts = new CompilationOptions();
            opts.setCalculateIndividualProbabilities();
            List<OutputSandwoodClass> classes = buildClass(opts).classes;
            opts.setTargetDirectory(tempDir);
            DiagnosticCollector<JavaFileObject> diagnostics = CompileUtils.compileToJava(opts, classes);
            if(!diagnostics.getDiagnostics().isEmpty()) {
                CompileUtils.printErrorsSimple(diagnostics);
                assertTrue(false);
            }

            // Construct a URL[] containing the output directory.
            File file = new File(tempDir);
            URL url = file.toURI().toURL();
            URL[] urls = new URL[] { url };

            // Load the contents
            ChildFirstClassLoader cl = new ChildFirstClassLoader(urls);
            // Load the class
            Class<?> cls = cl.loadClass("ClassName");
            Constructor<?> cons = cls.getConstructor();
            Model m = (Model) cons.newInstance();
            if(target != null) {
                m.setExecutionTarget(target);
                switch(target.executionType) {
                    case SingleThreadCPU:
                        m.setThreadCount(1);
                        break;
                    case MultiThreadCPU:
                        m.setThreadCount(6);
                        break;
                    default:
                        cl.close();
                        throw new SandwoodTestException("Unknown execution target " + target);
                }
            }
            forwardTestModel(cls, m, (target == null) ? ExecutionTarget.singleThread : target);

            m.close();
            cl.close();
        } catch(Exception e) {
            e.printStackTrace();
            fail("This code should not throw exceptions:" + e.getMessage());
        }
    }

    private void forwardTestModel(Class<?> cls, Model m, ExecutionTarget target) throws IllegalAccessException,
            NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IOException {
        initializeRandom(m);
        assertFalse(forwardInitialisationRequired && m.readyExecute());
        initializeClassForward(cls, m);
        assertTrue(m.readyExecute());

        // Call the forward method
        m.execute();

        // Call the get results method
        if(constructing.contains(TestType.Forward)) {
            if(target != null) {
                Map<String, Object> outputs = new HashMap<>();
                for(String resultName:getOutputFields(TestType.Forward)) {
                    Field f = cls.getField(resultName);
                    Object outputField = f.get(m);

                    Class<?> c = outputField.getClass();
                    Method outputMethod = c.getMethod("getSamples", new Class<?>[0]);
                    Object generated = outputMethod.invoke(outputField, new Object[0]);
                    outputs.put(resultName, generated);
                }
                writeResults(TestType.Forward, target.executionType, outputs);
            }
            assertTrue(false, "Results are being written");
        } else {
            if(testValues) {
                Map<String, Object> localResult = results.get(TestType.Forward).get(target);
                for(String resultName:getOutputFields(TestType.Forward)) {
                    Object expected = localResult.get(resultName);
                    Field f = cls.getField(resultName);
                    Object outputField = f.get(m);

                    Class<?> c = outputField.getClass();
                    Method outputMethod = c.getMethod("getSamples", new Class<?>[0]);
                    Object generated = outputMethod.invoke(outputField, new Object[0]);

                    compareValues(expected, generated);
                }
            }
        }
    }

    private void compareValues(Object expected, Object generated) {
        if(expected instanceof Double) {
            double e = (Double) expected;
            double g = (Double) generated;
            assertEquals(e, g);
        } else if(expected instanceof double[]) {
            double[] e = (double[]) expected;
            double[] g = (double[]) generated;
            // we only check the entries stored as might be returning a lot of entries.
            for(int i = 0; i < e.length && i < g.length; i++)
                assertEquals(e[i], g[i], "Values differ at position " + i + ".");
        } else if(expected instanceof Object[]) {
            Object[] e = (Object[]) expected;
            Object[] g = (Object[]) generated;
            assertEquals(e.length, g.length);
            for(int i = 0; i < e.length; i++) {
                compareValues(e[i], g[i]);
            }
        } else if(expected instanceof Integer) {
            int e = (Integer) expected;
            int g = (Integer) generated;
            assertEquals(e, g);
        } else if(expected instanceof int[]) {
            int[] e = (int[]) expected;
            int[] g = (int[]) generated;
            // we only check the entries stored as might be returning a lot of entries.
            for(int i = 0; i < e.length && i < g.length; i++)
                assertEquals(e[i], g[i], "Values differ at position " + i + ".");
        } else if(expected instanceof Boolean) {
            boolean e = (Boolean) expected;
            boolean g = (Boolean) generated;
            assertEquals(e, g);
        } else if(expected instanceof boolean[]) {
            boolean[] e = (boolean[]) expected;
            boolean[] g = (boolean[]) generated;
            // we only check the entries stored as might be returning a lot of entries.
            for(int i = 0; i < e.length && i < g.length; i++)
                assertEquals(e[i], g[i], "Values differ at position " + i + ".");
        } else
            assert false : "unknown return type";
    }

    @AfterEach
    void deleteOutputDir() {
        // Construct a URL[] containing the output directory.
        File dir = new File(tempDir);
        if(dir.exists()) {
            if(dir.isDirectory()) {
                for(File file:dir.listFiles())
                    file.delete();
            }
            dir.delete();
        }
    }

    protected void initializeRandom(Model model) {
        // Call initializeSeed(42)
        model.initializeSeed(42);
    }
}
