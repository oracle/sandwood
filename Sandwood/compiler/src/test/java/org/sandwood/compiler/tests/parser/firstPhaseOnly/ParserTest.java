/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.parser.firstPhaseOnly;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.sandwood.compiler.compilation.ExtendedSandwoodParser;
import org.sandwood.compiler.compilation.util.CompileUtils;
import org.sandwood.compiler.exceptions.SandwoodModelException;
import org.sandwood.compiler.exceptions.SandwoodParseException;
import org.sandwood.compiler.names.ClassName;
import org.sandwood.compiler.names.PackageName;
import org.sandwood.compiler.srcTools.sourceToSource.SandwoodParser;
import org.sandwood.compiler.tests.util.CompilerState;
import org.sandwood.compiler.tests.util.TestStringGenerator;
import org.sandwood.compiler.util.StringUtil;

class ParserTest {
    private static final String tempDir = "tmp";
    private static final String outputDir = "src" + File.separator + "test" + File.separator + "resources"
            + File.separator + "expectedOutputs";
    private static final String sourceDir = "src" + File.separator + "test" + File.separator + "resources"
            + File.separator + "testInputs";
    private static final boolean constructingResults = false;
    private final TestStringGenerator tsg = new TestStringGenerator();
    private static final Map<String, String> errors;

    static {
        errors = new HashMap<>();
        errors.put(
                sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator + "compiler"
                        + File.separator + "tests" + File.separator + "parser" + File.separator + "firstPhaseOnly"
                        + File.separator + "LogisticRegressionTest_Error1.sandwood",
                "-----\n" + "\n" + sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator
                        + "compiler" + File.separator + "tests" + File.separator + "parser" + File.separator
                        + "firstPhaseOnly" + File.separator + "LogisticRegressionTest_Error1.sandwood\n"
                        + "Error on line: 1 between columns 1 and 5\n"
                        + "Parse error: Unexpected token \"Error\" encountered on line 1 column 1\n" + "\n\""
                        + "Error/*\"\n" + "\n" + "-----\n" + "\n" + "1 Error found.\n");
        errors.put(
                sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator + "compiler"
                        + File.separator + "tests" + File.separator + "parser" + File.separator + "firstPhaseOnly"
                        + File.separator + "LogisticRegressionTest_Error2.sandwood",
                "-----\n" + "\n" + sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator
                        + "compiler" + File.separator + "tests" + File.separator + "parser" + File.separator
                        + "firstPhaseOnly" + File.separator + "LogisticRegressionTest_Error2.sandwood\n"
                        + "Error between lines: 1 column 1 and line 9 column 5\n"
                        + "Parse error: Unexpected token \"Error\" encountered on line 9 column 1\n" + "\n\"" + "/*\n"
                        + " * Sandwood\n" + " *\n" + " * Copyright (c) 2019-2023, Oracle and/or its affiliates\n"
                        + " * \n"
                        + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
                        + " */\n" + "\n" + "Error\"\n" + "\n" + "-----\n" + "\n" + "1 Error found.\n");
        errors.put(
                sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator + "compiler"
                        + File.separator + "tests" + File.separator + "parser" + File.separator + "firstPhaseOnly"
                        + File.separator + "LogisticRegressionTest_Error3.sandwood",
                "-----\n" + "\n" + sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator
                        + "compiler" + File.separator + "tests" + File.separator + "parser" + File.separator
                        + "firstPhaseOnly" + File.separator + "LogisticRegressionTest_Error3.sandwood\n"
                        + "Error between lines: 11 column 69 and line 13 column 5\n"
                        + "Parse error: Unexpected token \"Error\" encountered on line 13 column 1\n" + "\n\""
                        + "import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Sigmoid;\n" + "\n" + "Error\"\n"
                        + "\n" + "-----\n" + "\n" + "1 Error found.\n");
        errors.put(
                sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator + "compiler"
                        + File.separator + "tests" + File.separator + "parser" + File.separator + "firstPhaseOnly"
                        + File.separator + "LogisticRegressionTest_Error4.sandwood",
                "-----\n" + "\n" + sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator
                        + "compiler" + File.separator + "tests" + File.separator + "parser" + File.separator
                        + "firstPhaseOnly" + File.separator + "LogisticRegressionTest_Error4.sandwood\n"
                        + "Error on line: 13 between columns 7 and 34\n"
                        + "Parse error: Unexpected token \"Error\" encountered on line 13 column 30\n" + "\n\""
                        + "model LogisticRegressionTest Error(double[][] x, boolean[] yMeasured) {\"\n" + "\n"
                        + "-----\n" + "\n" + "1 Error found.\n");
        errors.put(
                sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator + "compiler"
                        + File.separator + "tests" + File.separator + "parser" + File.separator + "firstPhaseOnly"
                        + File.separator + "LogisticRegressionTest_Error5.sandwood",
                "-----\n" + "\n" + sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator
                        + "compiler" + File.separator + "tests" + File.separator + "parser" + File.separator
                        + "firstPhaseOnly" + File.separator + "LogisticRegressionTest_Error5.sandwood\n"
                        + "Error on line: 19 between columns 5 and 9\n" + "Unknown type: doble\n" + "\n\""
                        + "\tdoble[] weights = gaussian(0,10).sample(k);\"\n" + "\n" + "-----\n" + "\n"
                        + "1 Error found.\n");
        errors.put(
                sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator + "compiler"
                        + File.separator + "tests" + File.separator + "parser" + File.separator + "firstPhaseOnly"
                        + File.separator + "LogisticRegressionTest_Error6.sandwood",
                "-----\n" + "\n" + sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator
                        + "compiler" + File.separator + "tests" + File.separator + "parser" + File.separator
                        + "firstPhaseOnly" + File.separator + "LogisticRegressionTest_Error6.sandwood\n"
                        + "Error on line: 25 between columns 45 and 46\n"
                        + "Parse error: Unexpected token \"*\" encountered on line 25 column 46\n" + "\n\""
                        + "\t\t\tindicatorValues[j] = weights[j] ** x[i][j];\"\n" + "\n" + "-----\n" + "\n"
                        + "1 Error found.\n");
        errors.put(
                sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator + "compiler"
                        + File.separator + "tests" + File.separator + "parser" + File.separator + "firstPhaseOnly"
                        + File.separator + "LogisticRegressionTest_Error7.sandwood",
                "-----\n" + "\n" + sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator
                        + "compiler" + File.separator + "tests" + File.separator + "parser" + File.separator
                        + "firstPhaseOnly" + File.separator + "LogisticRegressionTest_Error7.sandwood\n"
                        + "Error between lines: 17 column 32 and line 19 column 10\n"
                        + "Parse error: Unexpected token \"double\" encountered on line 19 column 5\n" + "\n\""
                        + "\tboolean[] y = new boolean[n]\n" + "\n"
                        + "\tdouble[] weights = gaussian(0,10).sample(k);\"\n" + "\n" + "-----\n" + "\n"
                        + "1 Error found.\n");
        errors.put(
                sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator + "compiler"
                        + File.separator + "tests" + File.separator + "parser" + File.separator + "firstPhaseOnly"
                        + File.separator + "LogisticRegressionTest_Error8.sandwood",
                "-----\n" + "\n" + sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator
                        + "compiler" + File.separator + "tests" + File.separator + "parser" + File.separator
                        + "firstPhaseOnly" + File.separator + "LogisticRegressionTest_Error8.sandwood\n"
                        + "Error on line: 22 between columns 13 and 14\n"
                        + "Parse error: Unexpected token \"[\" encountered on line 22 column 14\n" + "\n\""
                        + "\tfor(int i[0..n)) {\"\n" + "\n" + "-----\n" + "\n" + "1 Error found.\n");
        errors.put(
                sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator + "compiler"
                        + File.separator + "tests" + File.separator + "parser" + File.separator + "firstPhaseOnly"
                        + File.separator + "LogisticRegressionTest_Error9.sandwood",
                "-----\n" + "\n" + sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator
                        + "compiler" + File.separator + "tests" + File.separator + "parser" + File.separator
                        + "firstPhaseOnly" + File.separator + "LogisticRegressionTest_Error9.sandwood\n"
                        + "Error between lines: 28 column 5 and line 30 column 9\n"
                        + "Parse error: Unexpected token \"Error\" encountered on line 30 column 5\n" + "\n\"" + "\t}\n"
                        + "\n" + "\tError\"\n" + "\n" + "-----\n" + "\n" + "1 Error found.\n");
        errors.put(
                sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator + "compiler"
                        + File.separator + "tests" + File.separator + "parser" + File.separator + "firstPhaseOnly"
                        + File.separator + "LogisticRegressionTest_Error10.sandwood",
                "-----\n" + "\n" + "Error in org" + File.separator + "sandwood" + File.separator + "compiler"
                        + File.separator + "tests" + File.separator + "parser" + File.separator + "firstPhaseOnly"
                        + File.separator + "LogisticRegressionTest_Error10.sandwood: Line 27 columns 12 to 23\n"
                        + "        y[i] = bernulli(success).sample();\n" + "cannot find symbol\n"
                        + "  symbol:   method bernulli(double)\n"
                        + "  location: class org.sandwood.compiler.tests.parser.firstPhaseOnly.LogisticRegressionTest_Error10\n"
                        + "\n" + "-----\n" + "\n" + "1 Error found.\n");
        errors.put(
                sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator + "compiler"
                        + File.separator + "tests" + File.separator + "parser" + File.separator + "firstPhaseOnly"
                        + File.separator + "LogisticRegressionTest_Error11.sandwood",
                "-----\n" + "\n" + sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator
                        + "compiler" + File.separator + "tests" + File.separator + "parser" + File.separator
                        + "firstPhaseOnly" + File.separator + "LogisticRegressionTest_Error11.sandwood\n"
                        + "Error on line: 22 at column 18\n"
                        + "Left hand argument of for loop guard is not the identifier declared in the loop.\n" + "\n\""
                        + "\tfor(int i=0; n>i; i++) {\"\n" + "\n" + "-----\n" + "\n" + "1 Error found.\n");
        errors.put(
                sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator + "compiler"
                        + File.separator + "tests" + File.separator + "parser" + File.separator + "firstPhaseOnly"
                        + File.separator + "LogisticRegressionTest_Error12.sandwood",
                "-----\n" + "\n" + sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator
                        + "compiler" + File.separator + "tests" + File.separator + "parser" + File.separator
                        + "firstPhaseOnly" + File.separator + "LogisticRegressionTest_Error12.sandwood\n"
                        + "Error on line: 22 at column 27\n"
                        + "First expression term when modifying the variable declared in a for loop must be the declared\n"
                        + "variable. In this case i, not the token 2.\n" + "\n\"" + "\tfor(int i=0; i<n; i = 2*i) {\"\n"
                        + "\n" + "-----\n" + "\n" + "1 Error found.\n");
        errors.put(
                sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator + "compiler"
                        + File.separator + "tests" + File.separator + "parser" + File.separator + "firstPhaseOnly"
                        + File.separator + "LogisticRegressionTest_Error13.sandwood",
                "-----\n" + "\n" + sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator
                        + "compiler" + File.separator + "tests" + File.separator + "parser" + File.separator
                        + "firstPhaseOnly" + File.separator + "LogisticRegressionTest_Error13.sandwood\n"
                        + "Error on line: 22 at column 28\n"
                        + "The loop variable i must be incremented by a constant value.\n" + "\n\""
                        + "\tfor(int i=0; i<n; i = i*i) {\"\n" + "\n" + "-----\n" + "\n" + "1 Error found.\n");
        errors.put(
                sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator + "compiler"
                        + File.separator + "tests" + File.separator + "parser" + File.separator + "firstPhaseOnly"
                        + File.separator + "LogisticRegressionTest_Error14.sandwood",
                "-----\n" + "\n" + sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator
                        + "compiler" + File.separator + "tests" + File.separator + "parser" + File.separator
                        + "firstPhaseOnly" + File.separator + "LogisticRegressionTest_Error14.sandwood\n"
                        + "Error on line: 22 at column 29\n"
                        + "First expression term when modifying the variable declared in a for loop must be the declared\n"
                        + "variable. In this case i, not the token 2.\n" + "\n\""
                        + "\tfor(int i=n-1; i>0; i = 2*i) {\"\n" + "\n" + "-----\n" + "\n" + "1 Error found.\n");
        errors.put(
                sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator + "compiler"
                        + File.separator + "tests" + File.separator + "parser" + File.separator + "firstPhaseOnly"
                        + File.separator + "LogisticRegressionTest_Error15.sandwood",
                "-----\n" + "\n" + sourceDir + File.separator + "org" + File.separator + "sandwood" + File.separator
                        + "compiler" + File.separator + "tests" + File.separator + "parser" + File.separator
                        + "firstPhaseOnly" + File.separator + "LogisticRegressionTest_Error15.sandwood\n"
                        + "Error on line: 22 at column 30\n"
                        + "The loop variable i must be decremented by a constant value.\n" + "\n\""
                        + "\tfor(int i=n-1; i>0; i = i*i) {\"\n" + "\n" + "-----\n" + "\n" + "1 Error found.\n");
    }

    static Stream<String> getFileNameArgs() {
        String packageDir = TestStringGenerator.getPackageDir(new ParserTest(), sourceDir);

        File dir = new File(packageDir);
        assertTrue(dir.isDirectory());
        String[] files = dir.list((dir1, name) -> name.endsWith(".sandwood"));

        for(int i = 0; i < files.length; i++)
            files[i] = packageDir + File.separator + files[i];

        Arrays.sort(files);

        return Stream.of(files);
    }

    @BeforeEach
    void resetStaticValues() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        CompilerState.reset();
    }

    @ParameterizedTest
    @MethodSource("getFileNameArgs")
    @DisplayName("Compile model to class file")
    void testFile(String file) throws IOException {
        try {
            System.out.println("File: " + file);
            // System.out.println("File Content:\n" + new
            // String(Files.readAllBytes(Paths.get(file))));

            // Parse input
            SandwoodParser p = new ExtendedSandwoodParser(file);
            p.parse();
            String apiBody = p.constructClass();
            // Normalize newline characters
            apiBody = StringUtil.normalizeNewLines(apiBody);

            String[] fileParts = file.split(File.separator.equals("\\") ? "\\\\" : File.separator);
            String expectedModelName = fileParts[fileParts.length - 1].split("\\.")[0];
            assertEquals(expectedModelName, p.getClassName().getName());

            // Assert api file is as expected
            String expectedApiFilename = tsg.processString(this, outputDir, p.getClassName().getName(), apiBody,
                    constructingResults, "java"); // Tool to make
            // changes to the
            // expected result
            // easier to
            // incorporate.
            String expectedApiCode = new String(Files.readAllBytes(Paths.get(expectedApiFilename)));
            // Normalise newline characters This second use is required because GitHub
            // rewrites the data files.
            expectedApiCode = StringUtil.normalizeNewLines(expectedApiCode);
            assertEquals(expectedApiCode, apiBody);

            ClassName apiClassName = p.getClassName();
            PackageName packageName = p.getPackageName();

            DiagnosticCollector<JavaFileObject> diagnostics = CompileUtils.compileToJava(tempDir, packageName,
                    apiClassName, apiBody);
            if(!diagnostics.getDiagnostics().isEmpty()) {
                String message = errors.get(file);
                String generatedMessage = CompileUtils.parseErrorsSandwood(diagnostics, p.getTokenMapping());
                generatedMessage = StringUtil.normalizeNewLines(generatedMessage);
                if(message == null || !message.equals(generatedMessage)) {
                    System.err.println(
                            "Generated message \"" + StringUtil.escapeSpecialCharacters(generatedMessage) + "\"");
                    if(message == null) {
                        System.err.println("Expected message: null");
                        assertEquals("", generatedMessage);
                    } else {
                        System.err.println("Expected message \"" + StringUtil.escapeSpecialCharacters(message) + "\"");
                        assertEquals(message, generatedMessage);
                    }
                }
            } else if(errors.containsKey(file))
                assertEquals(errors.get(file), "");

            assertFalse(constructingResults, "Results are being written to files, this invalidates the tests.");

        } catch(SandwoodParseException e) {
            List<String> modelPath = new ArrayList<>(1);
            modelPath.add("");
            String message = errors.get(file);
            String generatedMessage = CompileUtils
                    .parseErrors(CompileUtils.constructErrorsSandwood(file, e, modelPath));
            generatedMessage = StringUtil.normalizeNewLines(generatedMessage);
            if(message == null || !message.equals(generatedMessage)) {
                e.printStackTrace();
                System.err
                        .println("Generated message \"" + StringUtil.escapeSpecialCharacters(generatedMessage) + "\"");
                if(message == null) {
                    System.err.println("Expected message: null");
                    assertEquals("", generatedMessage);
                } else {
                    System.err.println("Expected message \"" + StringUtil.escapeSpecialCharacters(message) + "\"");
                    assertEquals(message, generatedMessage);
                }
            }
        } catch(SandwoodModelException e) {
            List<String> modelPath = new ArrayList<>(1);
            modelPath.add("");
            String message = errors.get(file);
            String generatedMessage = CompileUtils
                    .parseErrors(CompileUtils.constructErrorsSandwood(file, e, modelPath));
            generatedMessage = StringUtil.normalizeNewLines(generatedMessage);
            if(message == null || !message.equals(generatedMessage)) {
                e.printStackTrace();
                System.err
                        .println("Generated message \"" + StringUtil.escapeSpecialCharacters(generatedMessage) + "\"");
                if(message == null) {
                    System.err.println("Expected message: null");
                    assertEquals("", generatedMessage);
                } else {
                    System.err.println("Expected message \"" + StringUtil.escapeSpecialCharacters(message) + "\"");
                    assertEquals(message, generatedMessage);
                }
            }
        } catch(Exception e) {
            String message = errors.get(file);
            String generatedMessage = StringUtil.normalizeNewLines(e.getMessage());
            if(message == null || !message.equals(generatedMessage)) {
                e.printStackTrace();
                System.err
                        .println("Generated message \"" + StringUtil.escapeSpecialCharacters(generatedMessage) + "\"");
                if(message == null) {
                    System.err.println("Expected message: null");
                    assertEquals("", generatedMessage);
                } else {
                    System.err.println("Expected message \"" + StringUtil.escapeSpecialCharacters(message) + "\"");
                    assertEquals(message, generatedMessage);
                }
            }
        }
    }

    @AfterEach
    void deleteOutputDir() {
        deleteFile(tempDir);
    }

    private void deleteFile(String file) {
        deleteFile(new File(file));
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
