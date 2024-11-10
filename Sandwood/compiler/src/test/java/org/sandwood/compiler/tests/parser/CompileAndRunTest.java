/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.sandwood.common.tests.util.TestRuntime;
import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.compilation.ChildFirstClassLoader;
import org.sandwood.compiler.compilation.util.CompileUtils;
import org.sandwood.compiler.compilation.util.SandwoodCompileError;
import org.sandwood.compiler.dataflowGraph.Sandwood;
import org.sandwood.compiler.tests.exceptions.SandwoodTestException;
import org.sandwood.compiler.tests.util.CompilerState;
import org.sandwood.compiler.tests.util.JsonResultsDecoder;
import org.sandwood.compiler.tests.util.JsonResultsEncoder;
import org.sandwood.compiler.tests.util.TestStringGenerator;
import org.sandwood.compiler.util.StringUtil;
import org.sandwood.runtime.exceptions.SandwoodJsonException;
import org.sandwood.runtime.model.ExecutionTarget;
import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.RetentionPolicy;
import org.sandwood.runtime.model.variables.ObservedBoolean;
import org.sandwood.runtime.model.variables.ObservedBooleanArray;
import org.sandwood.runtime.model.variables.ObservedBooleanArrayShapeable;
import org.sandwood.runtime.model.variables.ObservedDouble;
import org.sandwood.runtime.model.variables.ObservedDoubleArray;
import org.sandwood.runtime.model.variables.ObservedDoubleArrayShapeable;
import org.sandwood.runtime.model.variables.ObservedInteger;
import org.sandwood.runtime.model.variables.ObservedIntegerArray;
import org.sandwood.runtime.model.variables.ObservedIntegerArrayShapeable;
import org.sandwood.runtime.model.variables.ObservedObjectArray;
import org.sandwood.runtime.model.variables.ObservedObjectArrayShapeable;

public class CompileAndRunTest {
    private interface ResultGetter {
        Object get(String resultName)
                throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;
    }

    public enum TestType {
        Gibbs,
        Evidence,
        EvidenceLog,
        Forward
    }

    public static class TestData {
        public final Map<String, Object> inputs = new HashMap<>();
        public final Set<String> shapes = new HashSet<>();
        // Args contains the key values of inputs allowing the use of constructors.
        public String[] args;
        public String[] outputNames;
    }

    public static class TestDesc {
        public final ExecutionTarget executionTarget;
        public final String filename;
        public final String modelName;
        public final String packageName;
        public final Map<TestType, TestData> inputData;

        public TestDesc(ExecutionTarget target, String filename, Map<TestType, TestData> testData) {
            this.executionTarget = target;
            this.filename = filename;
            this.inputData = testData;

            String[] parts = filename.split("\\.")[0].split(Pattern.quote(File.separator));
            int length = parts.length;

            modelName = parts[length - 1];

            StringBuilder sb = new StringBuilder();
            if(parts.length > 3)
                sb.append(parts[4]);
            for(int i = 5; i < length - 1; i++) {
                sb.append(".");
                sb.append(parts[i]);
            }
            packageName = sb.toString();
        }

        @Override
        public String toString() {
            return modelName + " " + (executionTarget == null ? "Default" : executionTarget);
        }
    }

    private static final String baseDir = "src" + File.separator + "test" + File.separator + "resources";
    private static final String sourceDir = baseDir + File.separator + "testInputs";
    private static final String outputDir = baseDir + File.separator + "expectedOutputs";

    private ChildFirstClassLoader cl;
    private static final CompileAndRunTest classInstance = new CompileAndRunTest();

    protected static final boolean constructingJson = false;
    private final TestStringGenerator tsg = new TestStringGenerator();

    private final boolean testValues;

    protected CompileAndRunTest() {
        testValues = TestRuntime.checkEnvironment();
    }

    protected Map<String, Object> getOutputs(TestType testType, TestDesc test)
            throws SandwoodJsonException, IOException {
        return getOutputs(testType, test, null);
    }

    protected Map<String, Object> getOutputs(TestType testType, TestDesc test, String tag)
            throws SandwoodJsonException, IOException {
        String filename = getFilename(testType, test.executionTarget, test.modelName, tag);
        return JsonResultsDecoder.readData(filename);
    }

    private void writeOutputs(TestType testType, TestDesc test, Map<String, Object> outputs) throws IOException {
        writeOutputs(testType, test, null, outputs);
    }

    private void writeOutputs(TestType testType, TestDesc test, String tag, Map<String, Object> outputs)
            throws IOException {
        if(test.executionTarget != null) {
            String filename = getFilename(testType, test.executionTarget, test.modelName, tag);
            JsonResultsEncoder.writeData(filename, outputs);
        }
    }

    private String getFilename(TestType testType, ExecutionTarget target, String modelName, String tag) {
        String[] parts = modelName.split(Pattern.quote(File.separator));
        modelName = parts[parts.length - 1].split("\\.")[0];
        String filename = tsg.getFilename(classInstance, outputDir, modelName,
                (target == null ? ExecutionTarget.singleThread : target).executionType,
                testType.toString() + (tag == null ? "" : tag), "json");
        return filename;
    }

    @BeforeEach
    void resetStaticValues() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        CompilerState.reset();
    }

    // Set temporary and target directories.
    private File targetDir;

    static Stream<TestDesc> getTargets() {
        List<ExecutionTarget> executionTargets = getExecutionTargets();
        Map<String, Map<TestType, TestData>> modelTargets = getModelTargets();
        List<TestDesc> targets = new ArrayList<>();
        for(String filename:modelTargets.keySet()) {
            for(ExecutionTarget e:executionTargets)
                targets.add(new TestDesc(e, filename, modelTargets.get(filename)));
        }
        return targets.stream();
    }

    protected static Map<String, Map<TestType, TestData>> getModelTargets() {
        Map<String, Map<TestType, TestData>> modelData = new LinkedHashMap<>();
        String packageDir = TestStringGenerator.getPackageDir(classInstance, sourceDir);

        File dir = new File(packageDir);
        assertTrue(dir.isDirectory());
        String[] files = dir.list((dir1, name) -> name.endsWith(".sandwood"));

        Arrays.sort(files);

        for(String filename:files) {
            try {
                String packageName = classInstance.getClass().getPackageName();
                String classname = filename.split("\\.")[0];
                Class<?> c = Class.forName(packageName + ".compileAndRunInputs." + classname);
                @SuppressWarnings("unchecked")
                Map<TestType, TestData> t = (Map<TestType, TestData>) c.getMethod("getInputs").invoke(null);
                modelData.put(packageDir + File.separator + filename, t);
            } catch(Exception e) {
            }
        }
        return modelData;
    }

    private static List<ExecutionTarget> getExecutionTargets() {
        List<ExecutionTarget> executionTargets = new ArrayList<>();
        executionTargets.add(null);
        for(ExecutionTarget e:ExecutionTarget.implementedTargets)
            executionTargets.add(e);
        return executionTargets;
    }

    @ParameterizedTest
    @MethodSource("getTargets")
    @DisplayName("Compile model to class file")
    void testFile(TestDesc testDesc) {
        try {
            targetDir = Files.createTempDirectory(null).toFile();
            CompilationOptions opts = new CompilationOptions();
            opts.setTargetDirectory(targetDir.getAbsolutePath());
            opts.fullInferenceRequired(false);
            opts.setCalculateIndividualProbabilities();
            // System.out.println("Compiling to: " + opts.getTargetDirectory());
            opts.setModelPath(sourceDir);
            opts.setModelFile(testDesc.filename);
            List<SandwoodCompileError> errors = Sandwood.compileModel(opts).getErrors();
            assertEquals(CompileUtils.parseErrors(errors), "");

            runModelTests(testDesc, targetDir.getAbsolutePath());

            assertFalse(constructingJson, "Results are being written to files, this invalidates the tests.");

        } catch(Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            assertTrue(false, e.getMessage());
        }
    }

    private void runModelTests(TestDesc testDesc, String compiledDir) throws Exception {
        for(TestType test:testDesc.inputData.keySet()) {
            switch(test) {
                case Gibbs: {
                    Map<String, Object> outputs = constructingJson ? null : getOutputs(test, testDesc, "Sample");
                    testGibbsConstructor(testDesc, compiledDir, outputs);
                    outputs = constructingJson ? null : getOutputs(test, testDesc, "Map");
                    testGibbsVariableSet(testDesc, compiledDir, outputs);
                    if(!constructingJson)
                        testJSONLoad(testDesc, compiledDir, outputs);
                    break;
                }
                case Evidence: {
                    Map<String, Object> outputs = constructingJson ? null : getOutputs(test, testDesc);
                    testEvidenceConstructor(testDesc, compiledDir, outputs);
                    if(!constructingJson)
                        testEvidenceVariableSet(testDesc, compiledDir, outputs);
                    break;
                }
                case EvidenceLog: {
                    Map<String, Object> outputs = constructingJson ? null : getOutputs(test, testDesc);
                    testLogEvidenceConstructor(testDesc, compiledDir, outputs);
                    if(!constructingJson)
                        testLogEvidenceVariableSet(testDesc, compiledDir, outputs);
                    break;
                }
                case Forward: {
                    Map<String, Object> outputs = constructingJson ? null : getOutputs(test, testDesc);
                    testForwardConstructor(testDesc, compiledDir, outputs);
                    if(!constructingJson)
                        testForwardVariableSet(testDesc, compiledDir, outputs);
                    break;
                }
            }
        }
    }

    private void testForwardConstructor(TestDesc test, String compiledDir, Map<String, Object> outputs)
            throws Exception {
        TestData data = test.inputData.get(TestType.Forward);
        Class<?> c = getClass(compiledDir, test);
        Model model = getModel(c, test, data);

        assertTrue(model.readyExecute());

        // Call the backward pass method
        model.execute(1);

        if(constructingJson) {
            outputs = new HashMap<>();
            for(String resultName:data.outputNames) {
                Object generated = getSamplesValue(c, model, resultName);
                outputs.put(resultName, generated);
            }
            writeOutputs(TestType.Forward, test, outputs);
        } else
            compare(outputs, (String name) -> getSamplesValue(c, model, name));

        // Test the output JSON method for sample values
        String outputJsonFile = compiledDir + File.separator + test.modelName + "_forward.json";
        model.exportToJson(outputJsonFile);
        String json = new String(Files.readAllBytes(Paths.get(outputJsonFile)));
        json = StringUtil.normalizeNewLines(json);
        // Using null as the model name to avoid repetition as modelName is already the
        // last part of the class name.
        String expectedJsonFilename = tsg.processString(classInstance, outputDir, test.modelName,
                ((test.executionTarget == null) ? ExecutionTarget.singleThread : test.executionTarget).executionType,
                "ForwardRun", json, constructingJson, "json"); // Tool to make
        // changes to the
        // expected result
        // easier to
        // incorporate.
        if(testValues) {
            String expectedJson = new String(Files.readAllBytes(Paths.get(expectedJsonFilename)));
            expectedJson = StringUtil.normalizeNewLines(expectedJson);
            assertEquals(splitObjects(expectedJson), splitObjects(json));
        }

        model.close();
    }

    private void testForwardVariableSet(TestDesc test, String compiledDir, Map<String, Object> outputs)
            throws Exception {
        Class<?> c = getClass(compiledDir, test);
        Model model = getModel(c, test);

        TestData data = test.inputData.get(TestType.Forward);

        assertFalse(model.readyExecute() && data.args.length > 0);

        for(String inputName:data.inputs.keySet()) {
            if(data.shapes.contains(inputName))
                setShape(model, c, inputName, data.inputs.get(inputName));
            else
                setValue(model, c, inputName, data.inputs.get(inputName));
        }

        assertTrue(model.readyExecute());

        // Call the backward pass method
        model.execute(1);

        compare(outputs, (String name) -> getSamplesValue(c, model, name));

        model.close();
    }

    private void testLogEvidenceConstructor(TestDesc test, String compiledDir, Map<String, Object> outputs)
            throws Exception {
        TestData data = test.inputData.get(TestType.EvidenceLog);
        Class<?> c = getClass(compiledDir, test);
        Model model = getModel(c, test, data);

        assertTrue(model.readyInferProbabilities());

        // Call the backward pass method
        model.inferProbabilities(1);

        if(constructingJson) {
            outputs = new HashMap<>();
            outputs.put("modelProbability", model.getLogProbability());
            for(String resultName:data.outputNames) {
                Object generated = getLogProbability(c, model, resultName);
                outputs.put(resultName, generated);
            }
            writeOutputs(TestType.EvidenceLog, test, outputs);
        } else {
            if(testValues) {
                assertEquals(outputs.get("modelProbability"), model.getLogProbability());
                compare(outputs, data.outputNames, (String name) -> getLogProbability(c, model, name));
            }
        }

        model.close();
    }

    private void testEvidenceConstructor(TestDesc test, String compiledDir, Map<String, Object> outputs)
            throws Exception {
        TestData data = test.inputData.get(TestType.Evidence);

        Class<?> c = getClass(compiledDir, test);
        Model model = getModel(c, test, data);

        assertTrue(model.readyInferProbabilities());

        // Call the backward pass method
        model.inferProbabilities(1);

        if(constructingJson) {
            outputs = new HashMap<>();
            outputs.put("modelProbability", model.getProbability());
            for(String resultName:data.outputNames) {
                Object generated = getProbability(c, model, resultName);
                outputs.put(resultName, generated);
            }
            writeOutputs(TestType.Evidence, test, outputs);
        } else {
            if(testValues) {
                assertEquals(outputs.get("modelProbability"), model.getProbability());
                compare(outputs, data.outputNames, (String name) -> getProbability(c, model, name));
            }
        }

        model.close();
    }

    private void testEvidenceVariableSet(TestDesc test, String compiledDir, Map<String, Object> outputs)
            throws Exception {
        Class<?> c = getClass(compiledDir, test);
        Model model = getModel(c, test);

        TestData data = test.inputData.get(TestType.Evidence);

        assertFalse(model.readyInferProbabilities() && data.args.length > 0);

        for(String inputName:data.inputs.keySet()) {
            setValue(model, c, inputName, data.inputs.get(inputName));
        }

        assertTrue(model.readyInferProbabilities());

        // Call the backward pass method
        model.inferProbabilities(1);
        if(testValues) {
            assertEquals(outputs.get("modelProbability"), model.getProbability());
            compare(outputs, data.outputNames, (String name) -> getProbability(c, model, name));
        }

        model.close();
    }

    private void testLogEvidenceVariableSet(TestDesc test, String compiledDir, Map<String, Object> outputs)
            throws Exception {
        Class<?> c = getClass(compiledDir, test);
        Model model = getModel(c, test);

        TestData data = test.inputData.get(TestType.EvidenceLog);

        assertFalse(model.readyInferProbabilities() && data.args.length > 0);

        for(String inputName:data.inputs.keySet()) {
            setValue(model, c, inputName, data.inputs.get(inputName));
        }

        assertTrue(model.readyInferProbabilities());

        // Call the backward pass method
        model.inferProbabilities(1);
        if(testValues) {
            assertEquals(outputs.get("modelProbability"), model.getLogProbability());
            compare(outputs, data.outputNames, (String name) -> getLogProbability(c, model, name));
        }

        model.close();
    }

    private Object getProbability(Class<?> c, Model model, String resultName)
            throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Field f = c.getField(resultName);
        Object outputField = f.get(model);
        Method outputMethod = outputField.getClass().getMethod("probabilityComputed");
        assertTrue((boolean) outputMethod.invoke(outputField, new Object[0]));
        outputMethod = outputField.getClass().getMethod("getProbability");
        return outputMethod.invoke(outputField, new Object[0]);
    }

    private Object getLogProbability(Class<?> c, Model model, String resultName)
            throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Field f = c.getField(resultName);
        Object outputField = f.get(model);
        Method outputMethod = outputField.getClass().getMethod("probabilityComputed");
        assertTrue((boolean) outputMethod.invoke(outputField, new Object[0]));
        outputMethod = outputField.getClass().getMethod("getLogProbability");
        return outputMethod.invoke(outputField, new Object[0]);
    }

    private void testGibbsVariableSet(TestDesc test, String compiledDir, Map<String, Object> outputs) throws Exception {
        Class<?> c = getClass(compiledDir, test);
        Model model = getModel(c, test);

        model.setDefaultRetentionPolicy(RetentionPolicy.MAP);

        TestData data = test.inputData.get(TestType.Gibbs);

        assertFalse(model.readyInferValues() && data.args.length > 0);

        for(String inputName:data.inputs.keySet()) {
            setValue(model, c, inputName, data.inputs.get(inputName));
        }

        assertTrue(model.readyInferValues());

        // Call the backward pass method
        model.inferValues(1);
        if(constructingJson) {
            outputs = new HashMap<>();
            for(String resultName:data.outputNames) {
                Object generated = getMapValue(c, model, resultName);
                outputs.put(resultName, generated);
            }
            writeOutputs(TestType.Gibbs, test, "Map", outputs);
        } else {
            // Call the get results method
            compare(outputs, (String name) -> getMapValue(c, model, name));
        }

        {
            // Test the output JSON method for All variables
            String outputJsonFile = compiledDir + File.separator + test.modelName + ".json";
            model.exportToJson(outputJsonFile);
            String json = new String(Files.readAllBytes(Paths.get(outputJsonFile)));
            json = StringUtil.normalizeNewLines(json);
            // Using null as the model name to avoid repetition as modelName is already the
            // last part of the class name.
            String expectedJsonFilename = tsg.processString(classInstance, outputDir, test.modelName,
                    (test.executionTarget == null ? ExecutionTarget.singleThread : test.executionTarget).executionType,
                    "CompleteModel", json, (constructingJson), "json"); // Tool to make
            // changes to the
            // expected result
            // easier to
            // incorporate.
            if(testValues) {
                String expectedJson = new String(Files.readAllBytes(Paths.get(expectedJsonFilename)));
                expectedJson = StringUtil.normalizeNewLines(expectedJson);
                assertEquals(splitObjects(expectedJson), splitObjects(json));
            }
        }

        {
            // Test the output JSON method for sample values
            String outputJsonFile = compiledDir + File.separator + test.modelName + ".json";
            model.saveModel(outputJsonFile);
            String json = new String(Files.readAllBytes(Paths.get(outputJsonFile)));
            json = StringUtil.normalizeNewLines(json);
            // Using null as the model name to avoid repetition as modelName is already the
            // last part of the class name.
            String expectedJsonFilename = tsg.processString(classInstance, outputDir, test.modelName,
                    (test.executionTarget == null ? ExecutionTarget.singleThread : test.executionTarget).executionType,
                    "SamplesOnly", json, (constructingJson), "json"); // Tool to make
            // changes to the
            // expected result
            // easier to
            // incorporate.
            if(testValues) {
                String expectedJson = new String(Files.readAllBytes(Paths.get(expectedJsonFilename)));
                expectedJson = StringUtil.normalizeNewLines(expectedJson);
                assertEquals(splitObjects(expectedJson), splitObjects(json));
            }
        }

        model.close();
    }

    private String splitObjects(String json) {
        int size = json.length();
        StringBuilder sb = new StringBuilder((int) (size * 1.1));
        int depth = 0;
        boolean comma = false;
        boolean insideQuotes = false;
        for(int i = 0; i < size; i++) {
            char c = json.charAt(i);
            switch(c) {
                case '{': {
                    if(!insideQuotes) {
                        sb.append("\n");
                        depth++;
                        addTabs(sb, depth);
                    }
                    sb.append(c);
                    break;
                }
                case '}': {
                    if(!insideQuotes) {
                        sb.append("\n");
                        depth--;
                        addTabs(sb, depth);
                    }
                    sb.append(c);
                    break;
                }
                case '\\': {
                    if(++i < size) {
                        c = json.charAt(i);
                        if(c == 'n') {
                            sb.append('\n');
                            addTabs(sb, depth);
                        } else {
                            sb.append('\\');
                            sb.append(c);
                        }
                    } else
                        sb.append(c);
                    break;
                }
                case '"': {
                    insideQuotes = !insideQuotes;
                    if(comma) {
                        sb.append('\n');
                        addTabs(sb, depth);
                    }
                    sb.append(c);
                    break;
                }
                default: {
                    sb.append(c);
                }
            }
            comma = c == ',';
        }
        return sb.toString();
    }

    private void addTabs(StringBuilder sb, int depth) {
        for(int i = 0; i < depth; i++)
            sb.append('\t');
    }

    private void testJSONLoad(TestDesc test, String compiledDir, Map<String, Object> outputs) throws Exception {
        Class<?> c = getClass(compiledDir, test);
        Model model = getModel(c, test);

        String filename = tsg.getFilename(classInstance, outputDir, test.modelName,
                (test.executionTarget == null ? ExecutionTarget.singleThread : test.executionTarget).executionType,
                "SamplesOnly", "json");
        // Call the backward pass method
        model.loadModel(filename);

        // Call the get results method
        TestData data = test.inputData.get(TestType.Gibbs);
        compare(data.inputs, (String name) -> getInputValue(c, model, name));

        // Call the get results method
        compare(outputs, (String name) -> getMapValue(c, model, name));

        model.close();
    }

    private void testGibbsConstructor(TestDesc test, String compiledDir, Map<String, Object> outputs) throws Exception {
        Class<?> c = getClass(compiledDir, test);

        TestData data = test.inputData.get(TestType.Gibbs);
        Model model = getModel(c, test, data);

        assertTrue(model.readyInferValues());

        // Call the backward pass method
        model.inferValues(1);

        if(constructingJson) {
            outputs = new HashMap<>();
            for(String resultName:data.outputNames) {
                Object generated = getSamplesValue(c, model, resultName);
                outputs.put(resultName, generated);
            }
            writeOutputs(TestType.Gibbs, test, "Sample", outputs);
        } else {
            // Call the get results method
            compare(outputs, (String name) -> getSamplesValue(c, model, name));
        }

        model.close();
    }

    private void compare(Map<String, Object> outputs, ResultGetter result)
            throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        compare(outputs, outputs.keySet(), result);
    }

    private void compare(Map<String, Object> outputs, String[] valueNames, ResultGetter result)
            throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        List<String> listNames = new ArrayList<>();
        for(String s:valueNames)
            listNames.add(s);
        compare(outputs, listNames, result);
    }

    private void compare(Map<String, Object> outputs, Collection<String> valueNames, ResultGetter result)
            throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if(testValues) {
            for(String resultName:valueNames) {
                Object expected = outputs.get(resultName);
                Object generated = result.get(resultName);
                compare(expected, generated);
            }
        }
    }

    private Object getSamplesValue(Class<?> c, Model model, String resultName)
            throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Field f = c.getField(resultName);
        Object outputField = f.get(model);

        Class<?> fieldClass = outputField.getClass();
        Method outputMethod = fieldClass.getMethod("getSamples");
        return outputMethod.invoke(outputField, new Object[0]);
    }

    private Object getMapValue(Class<?> c, Model model, String resultName)
            throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Field f = c.getField(resultName);
        Object outputField = f.get(model);

        Class<?> fieldClass = outputField.getClass();
        Method outputMethod = fieldClass.getMethod("getMAP");

        return outputMethod.invoke(outputField, new Object[0]);
    }

    private Object getInputValue(Class<?> c, Model model, String resultName)
            throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Field f = c.getField(resultName);
        Object outputField = f.get(model);

        Class<?> fieldClass = outputField.getClass();
        Method outputMethod = fieldClass.getMethod("getValue");
        // Not sure why reflection requires this. TODO track it down at some point and
        // fix it.
        outputMethod.setAccessible(true);
        return outputMethod.invoke(outputField, new Object[0]);
    }

    private void setValue(Model model, Class<?> c, String inputName, Object input) throws Exception {
        Field f = c.getField(inputName);
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
        } else
            throw new SandwoodTestException("Unknown input type");
    }

    @SuppressWarnings("unchecked")
    private void setShape(Model model, Class<?> c, String inputName, Object input) throws Exception {
        Field f = c.getField(inputName);
        Object inputField = f.get(model);

        if(inputField instanceof ObservedDoubleArrayShapeable) {
            ((ObservedDoubleArrayShapeable) inputField).setLength((int) input);
        } else if(inputField instanceof ObservedBooleanArrayShapeable) {
            ((ObservedBooleanArrayShapeable) inputField).setLength((int) input);
        } else if(inputField instanceof ObservedIntegerArrayShapeable) {
            ((ObservedIntegerArrayShapeable) inputField).setLength((int) input);
        } else if(inputField instanceof ObservedObjectArrayShapeable<?, ?>) {
            // Hack due to a bug in the JVM stopping the correct evaluation of can this
            // method be seen if reflection is used.
            ((ObservedObjectArrayShapeable<?, int[]>) inputField).setShape((int[]) input);
        } else
            throw new SandwoodTestException("Unknown type");
    }

    private Class<?> getClass(String dir, TestDesc desc) {
        try {
            // Skip the first 3 elements as they are not part of the package.
            String className = desc.packageName + "." + desc.modelName;

            // Construct a URL[] containing the output directory.
            File file = new File(dir);
            URL url = file.toURI().toURL();
            URL[] urls = new URL[] { url };

            // Load the contents
            cl = new ChildFirstClassLoader(urls);
            // Load the class
            return cl.loadClass(className);
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            assertTrue(false);
        }
        return null;
    }

    private Model getModel(Class<?> cls, TestDesc test) {
        return getModel(cls, test, null);
    }

    private Model getModel(Class<?> cls, TestDesc test, TestData data) {
        ExecutionTarget target = test.executionTarget;
        try {
            Class<?>[] types;
            Object[] args;

            if(data != null) {
                int noArgs = data.args.length;
                types = new Class<?>[noArgs];
                args = new Object[noArgs];

                for(int i = 0; i < noArgs; i++) {
                    Object arg = data.inputs.get(data.args[i]);
                    args[i] = arg;
                    types[i] = arg.getClass();
                }
                insertBaseTypes(types);

            } else {
                types = new Class<?>[0];
                args = new Object[0];
            }

            Constructor<?> cons = cls.getConstructor(types);
            Model m = (Model) cons.newInstance(args);
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
                        throw new SandwoodTestException("Unknown execution target " + target);
                }
            }

            // Call initializeSeed(42)
            m.initializeSeed(42);

            return m;
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            assertTrue(false);
        }

        return null;
    }

    private void insertBaseTypes(Class<?>[] types) {
        for(int i = 0; i < types.length; i++) {
            Class<?> c = types[i];
            if(c.equals(Integer.class))
                types[i] = int.class;
            else if(c.equals(Double.class))
                types[i] = double.class;
            else if(c.equals(Boolean.class))
                types[i] = boolean.class;
        }
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

    // Method to test results;
    private void compare(Object expected, Object generated) {
        if(expected instanceof Double) {
            double e = ((Double) expected).doubleValue();
            double g = ((Double) generated).doubleValue();
            assertEquals(e, g);
        } else if(expected instanceof double[]) {
            double[] e = (double[]) expected;
            double[] g = (double[]) generated;
            // we only check the entries stored as might be returning a lot of entries.
            for(int i = 0; i < e.length && i < g.length; i++)
                assertEquals(e[i], g[i], "Mismatch at position " + i);
        } else if(expected instanceof Integer) {
            int e = ((Integer) expected).intValue();
            int g = ((Integer) generated).intValue();
            assertEquals(e, g);
        } else if(expected instanceof int[]) {
            int[] e = (int[]) expected;
            int[] g = (int[]) generated;
            // we only check the entries stored as might be returning a lot of entries.
            for(int i = 0; i < e.length && i < g.length; i++)
                assertEquals(e[i], g[i], "Mismatch at position " + i);
        } else if(expected instanceof Boolean) {
            boolean e = ((Boolean) expected).booleanValue();
            boolean g = ((Boolean) generated).booleanValue();
            assertEquals(e, g);
        } else if(expected instanceof boolean[]) {
            boolean[] e = (boolean[]) expected;
            boolean[] g = (boolean[]) generated;
            // we only check the entries stored as might be returning a lot of entries.
            for(int i = 0; i < e.length && i < g.length; i++)
                assertEquals(e[i], g[i], "Mismatch at position " + i);
        } else if(expected instanceof Object[]) {
            Object[] e = (Object[]) expected;
            Object[] g = (Object[]) generated;
            assertEquals(e.length, g.length);
            for(int i = 0; i < e.length; i++) {
                compare(e[i], g[i]);
            }
        } else
            assert false : "unknown return type";
    }
}
