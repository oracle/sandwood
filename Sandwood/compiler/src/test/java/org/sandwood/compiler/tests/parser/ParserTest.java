/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
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
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.compilation.ChildFirstClassLoader;
import org.sandwood.compiler.compilation.GeneratedAPIBuilder;
import org.sandwood.compiler.compilation.util.CompilationDesc;
import org.sandwood.compiler.compilation.util.CompileUtils;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.exceptions.SandwoodModelException;
import org.sandwood.compiler.names.ClassName;
import org.sandwood.compiler.names.ModelClassName;
import org.sandwood.compiler.names.PackageName;
import org.sandwood.compiler.srcTools.sourceToSource.ParseException;
import org.sandwood.compiler.srcTools.sourceToSource.SandwoodParser;
import org.sandwood.compiler.tests.parser.ExpectedErrors.ErrorDesc;
import org.sandwood.compiler.tests.util.CompilerState;
import org.sandwood.compiler.tests.util.TestStringGenerator;
import org.sandwood.compiler.trees.outputTree.OutputSandwoodClass;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.util.Pair;
import org.sandwood.compiler.util.StringUtil;

class ParserTest {
    private static final String tempDir = "tmp";
    private static final String resourceDir = "src" + File.separator + "test" + File.separator + "resources";
    private static final String outputDir = resourceDir + File.separator + "expectedOutputs";
    private static final String sourceDir = resourceDir + File.separator + "testInputs";
    private static final boolean constructAll = false;
    private static final boolean constructingAPI = constructAll || false;
    private static final boolean constructingModel = constructAll || false;
    private static final boolean constructingModelNoComments = constructAll || false;
    private static final boolean constructingOptimisedModel = constructAll || false;
    private static final boolean constructingModelSingle = constructAll || false;
    private static final boolean constructingOptimisedModelSingle = constructAll || false;
    private static final boolean constructingOptimisedModelNoComments = constructAll || false;
    private final TestStringGenerator tsg = new TestStringGenerator();
    private static final Set<String> validErrors = new HashSet<>();

    static {
        validErrors.add("code too large");
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
    @DisplayName("Generate basic Java code")
    void testFileBasic(String file) {
        try {
            System.out.println("File: " + file);
            // System.out.println("File Content:\n" + new
            // String(Files.readAllBytes(Paths.get(file))));

            Pair<GeneratedAPIBuilder, ClassName> p = buildAPI(file);

            // Build the unoptimised Sandwood class from the api.
            List<SandwoodModelException> errors = buildCode(p.a, p.b, "_model", false, true, true, constructingModel);

            assertFalse(constructingModel, "Results are being written to files, this invalidates the tests.");

            ErrorDesc ed = ExpectedErrors.getErrorDesc(file.substring(sourceDir.length() + 1));
            if(ed != null) {
                assertEquals(ed.messages.size(), errors.size());
                for(int i = 0; i < errors.size(); i++)
                    assertEquals(ed.messages.get(i), errors.get(i).getMessage());
            } else {
                for(SandwoodModelException e:errors)
                    System.out.println(e);
                assertTrue(errors.isEmpty());
            }

        } catch(Exception e) {
            System.err.println("Error with file: " + file);
            System.err.println(e.getMessage());
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @ParameterizedTest
    @MethodSource("getFileNameArgs")
    @DisplayName("Generate basic Java code - no comments")
    void testFileBasicNoComments(String file) {
        try {
            System.out.println("File: " + file);
            // System.out.println("File Content:\n" + new
            // String(Files.readAllBytes(Paths.get(file))));

            Pair<GeneratedAPIBuilder, ClassName> p = buildAPI(file);

            // Build the unoptimised Sandwood class from the api.
            List<SandwoodModelException> errors = buildCode(p.a, p.b, "_modelNoComments", false, true, false,
                    constructingModelNoComments);

            assertFalse(constructingModelNoComments, "Results are being written to files, this invalidates the tests.");

            ErrorDesc ed = ExpectedErrors.getErrorDesc(file.substring(sourceDir.length() + 1));
            if(ed != null) {
                assertEquals(ed.messages.size(), errors.size());
                for(int i = 0; i < errors.size(); i++)
                    assertEquals(ed.messages.get(i), errors.get(i).getMessage());
            } else {
                for(SandwoodModelException e:errors)
                    System.out.println(e);
                assertTrue(errors.isEmpty());
            }

        } catch(Exception e) {
            System.err.println("Error with file: " + file);
            System.err.println(e.getMessage());
            e.printStackTrace();

            OutputTree.includeComments = true;
            assertTrue(false);
        } catch(Error e) {
            OutputTree.includeComments = true;
            throw e;
        }
    }

    @ParameterizedTest
    @MethodSource("getFileNameArgs")
    @DisplayName("Generate Optimised Java code")
    void testFileOptimised(String file) {
        try {
            System.out.println("File: " + file);
            // System.out.println("File Content:\n" + new
            // String(Files.readAllBytes(Paths.get(file))));

            Pair<GeneratedAPIBuilder, ClassName> p = buildAPI(file);

            // Build the optimised Sandwood class from the api.
            List<SandwoodModelException> errors = buildCode(p.a, p.b, "_optimisedModel", true, true, true,
                    constructingOptimisedModel);

            assertFalse(constructingOptimisedModel, "Results are being written to files, this invalidates the tests.");

            ErrorDesc ed = ExpectedErrors.getErrorDesc(file.substring(sourceDir.length() + 1));
            if(ed != null) {
                assertEquals(ed.messages.size(), errors.size());
                for(int i = 0; i < errors.size(); i++)
                    assertEquals(ed.messages.get(i), errors.get(i).getMessage());
            } else {
                for(SandwoodModelException e:errors)
                    System.out.println(e);
                assertTrue(errors.isEmpty());
            }

        } catch(Exception e) {
            System.err.println("Error with file: " + file);
            System.err.println(e.getMessage());
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @ParameterizedTest
    @MethodSource("getFileNameArgs")
    @DisplayName("Generate Optimised Java code - No Comments")
    void testFileOptimisedNoComments(String file) {
        try {
            System.out.println("File: " + file);
            // System.out.println("File Content:\n" + new
            // String(Files.readAllBytes(Paths.get(file))));

            Pair<GeneratedAPIBuilder, ClassName> p = buildAPI(file);

            // Build the optimised Sandwood class from the api.
            List<SandwoodModelException> errors = buildCode(p.a, p.b, "_optimisedModelNoComments", true, true, false,
                    constructingOptimisedModelNoComments);

            assertFalse(constructingOptimisedModelNoComments,
                    "Results are being written to files, this invalidates the tests.");

            ErrorDesc ed = ExpectedErrors.getErrorDesc(file.substring(sourceDir.length() + 1));
            if(ed != null) {
                assertEquals(ed.messages.size(), errors.size());
                for(int i = 0; i < errors.size(); i++)
                    assertEquals(ed.messages.get(i), errors.get(i).getMessage());
            } else {
                for(SandwoodModelException e:errors)
                    System.out.println(e);
                assertTrue(errors.isEmpty());
            }

        } catch(Exception e) {
            System.err.println("Error with file: " + file);
            System.err.println(e.getMessage());
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @ParameterizedTest
    @MethodSource("getFileNameArgs")
    @DisplayName("Generate basic Java code single")
    void testFileBasicSingle(String file) {
        try {
            System.out.println("File: " + file);
            // System.out.println("File Content:\n" + new
            // String(Files.readAllBytes(Paths.get(file))));

            Pair<GeneratedAPIBuilder, ClassName> p = buildAPI(file);

            // Build the unoptimised Sandwood class from the api.
            List<SandwoodModelException> errors = buildCode(p.a, p.b, "_modelSingle", false, false, true,
                    constructingModelSingle);

            assertFalse(constructingModelSingle, "Results are being written to files, this invalidates the tests.");

            ErrorDesc ed = ExpectedErrors.getErrorDesc(file.substring(sourceDir.length() + 1));
            if(ed != null) {
                assertEquals(ed.messages.size(), errors.size());
                for(int i = 0; i < errors.size(); i++)
                    assertEquals(ed.messages.get(i), errors.get(i).getMessage());
            } else {
                for(SandwoodModelException e:errors)
                    System.out.println(e);
                assertTrue(errors.isEmpty());
            }

        } catch(Exception e) {
            System.err.println("Error with file: " + file);
            System.err.println(e.getMessage());
            e.printStackTrace();

            OutputTree.includeComments = true;
            assertTrue(false);
        } catch(Error e) {
            OutputTree.includeComments = true;
            throw e;
        }
    }

    @ParameterizedTest
    @MethodSource("getFileNameArgs")
    @DisplayName("Generate Optimised Java code single")
    void testFileOptimisedSingle(String file) {
        try {
            System.out.println("File: " + file);
            // System.out.println("File Content:\n" + new
            // String(Files.readAllBytes(Paths.get(file))));

            Pair<GeneratedAPIBuilder, ClassName> p = buildAPI(file);

            // Build the optimised Sandwood class from the api.
            List<SandwoodModelException> errors = buildCode(p.a, p.b, "_optimisedModelSingle", true, false, true,
                    constructingOptimisedModelSingle);

            assertFalse(constructingOptimisedModelSingle,
                    "Results are being written to files, this invalidates the tests.");

            ErrorDesc ed = ExpectedErrors.getErrorDesc(file.substring(sourceDir.length() + 1));
            if(ed != null) {
                assertEquals(ed.messages.size(), errors.size());
                for(int i = 0; i < errors.size(); i++)
                    assertEquals(ed.messages.get(i), errors.get(i).getMessage());
            } else {
                for(SandwoodModelException e:errors)
                    System.out.println(e);
                assertTrue(errors.isEmpty());
            }

        } catch(Exception e) {
            System.err.println("Error with file: " + file);
            System.err.println(e.getMessage());
            e.printStackTrace();
            assertTrue(false);
        }
    }

    private Pair<GeneratedAPIBuilder, ClassName> buildAPI(String file)
            throws IOException, ParseException, ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // Parse input
        SandwoodParser p = new SandwoodParser(file);
        p.parse();

        String apiBody = p.constructClass();
        ModelClassName apiClassName = p.getClassName();

        PackageName packageName = p.getPackageName();

        DiagnosticCollector<JavaFileObject> diagnostics = CompileUtils.compileToJava(tempDir, packageName, apiClassName,
                apiBody);
        if(!diagnostics.getDiagnostics().isEmpty()) {
            System.out.println(CompileUtils.parseErrorsSandwood(diagnostics, p.getTokenMapping()));
            assertTrue(false);
        }

        // Construct a URL[] containing the output directory.
        File outputDir = new File(tempDir);
        URL url = outputDir.toURI().toURL();
        URL[] urls = new URL[] { url };

        // Load the contents
        ChildFirstClassLoader cl = new ChildFirstClassLoader(urls);
        // Load the class
        Class<?> cls = cl.loadClass((packageName.isEmpty() ? "" : packageName + ".") + apiClassName);
        for(ClassName helperClassName:apiClassName.helperClassNames())
            cl.loadClass((packageName.isEmpty() ? "" : packageName + ".") + helperClassName);
        Constructor<?> cons = cls.getConstructor();
        GeneratedAPIBuilder apiClass = (GeneratedAPIBuilder) cons.newInstance();
        cl.close();

        return new Pair<>(apiClass, apiClassName);
    }

    @ParameterizedTest
    @MethodSource("getFileNameArgs")
    @DisplayName("Generate Java code API")
    void testFileAPI(String file) {
        try {
            System.out.println("File: " + file);
            // System.out.println("File Content:\n" + new
            // String(Files.readAllBytes(Paths.get(file))));

            // Parse input
            SandwoodParser p = new SandwoodParser(file);
            p.parse();
            String apiBody = p.constructClass();

            ClassName apiClassName = p.getClassName();

            String[] fileParts = file.split(File.separator.equals("\\") ? "\\\\" : File.separator);
            String expectedModelName = fileParts[fileParts.length - 1].split("\\.")[0];
            assertEquals(expectedModelName, apiClassName.getName());

            // Assert api file is as expected
            String expectedApiFilename = tsg.processString(this, outputDir, apiClassName.getName(), "api", apiBody,
                    constructingAPI, "java"); // Tool to make
            // changes to the
            // expected result
            // easier to
            // incorporate.
            String expectedApiCode = new String(Files.readAllBytes(Paths.get(expectedApiFilename)));

            // Normalize new lines
            apiBody = StringUtil.normalizeNewLines(apiBody);
            // This second use is required because GitHub rewrites the data files.
            expectedApiCode = StringUtil.normalizeNewLines(expectedApiCode);

            assertEquals(expectedApiCode, apiBody);

            assertFalse(constructingAPI, "Results are being written to files, this invalidates the tests.");

            // OutputDesc ed = ExpectedOutputs.getExceptionDesc(file);
            // if(ed != null && !ed.apiExcluded)
            // assertEquals(ed.message, errContent.toString());

        } catch(Exception e) {
            System.err.println("Error with file: " + file);
            System.err.println(e.getMessage());
            e.printStackTrace();
            assertTrue(false);
        }
    }

    private List<SandwoodModelException> buildCode(GeneratedAPIBuilder apiClass, ClassName className, String tag,
            boolean optimised, boolean individualProbs, boolean comments, boolean rebuild) throws IOException {
        DiagnosticCollector<JavaFileObject> diagnostics;
        CompilationOptions opts = new CompilationOptions();
        if(optimised)
            opts.enableOptimisation();
        else
            opts.disableOptimisation();
        opts.fullInferenceRequired(false);
        opts.setTargetDirectory(tempDir);
        if(individualProbs)
            opts.setCalculateIndividualProbabilities();
        OutputTree.includeComments = comments;

        CompilationDesc compDesc;
        try {
            compDesc = apiClass.buildClass(opts);
        } catch(SandwoodModelException e) {
            ScopeStack.reset();
            List<SandwoodModelException> errors = new ArrayList<>();
            errors.add(e);
            return errors;
        } catch(Exception e) {
            ScopeStack.reset();
            throw e;
        }
        if(!compDesc.classes.isEmpty()) { // Did the model include a model, or just functions?
            for(OutputSandwoodClass c:compDesc.classes) {
                StringBuilder sb = new StringBuilder();
                c.toJava(sb);
                String modelJavaCode = sb.toString();
                // Normalise newline characters.
                modelJavaCode = StringUtil.normalizeNewLines(modelJavaCode);
                // Tool to make changes to the expected result easier to incorporate.
                String expectedModelFilename = tsg.processString(this, outputDir, className.getName(),
                        c.getName() + tag, modelJavaCode, rebuild, "java");

                String expectedModelJavaCode;
                try {
                    expectedModelJavaCode = new String(Files.readAllBytes(Paths.get(expectedModelFilename)));
                } catch(IOException e) {
                    expectedModelJavaCode = "";
                    System.err.println("Failed to read file \"" + expectedModelFilename + "\"");
                }

                // Normalise newline characters This second use is required because GitHub
                // rewrites the data files.
                expectedModelJavaCode = StringUtil.normalizeNewLines(expectedModelJavaCode);
                assertEquals(expectedModelJavaCode, modelJavaCode);
            }

            diagnostics = CompileUtils.compileToJava(opts, compDesc.classes);
            if(!diagnostics.getDiagnostics().isEmpty()) {
                CompileUtils.printErrorsSimple(diagnostics);
                if(!optimised) {
                    for(Diagnostic<? extends JavaFileObject> diagnostic:diagnostics.getDiagnostics())
                        if(!validErrors.contains(diagnostic.getMessage(null))) {
                            System.out.println(diagnostic.getMessage(null));
                            assertTrue(false);
                        }
                } else
                    assertTrue(false);
            }
        }

        OutputTree.includeComments = true;
        return compDesc.errors;
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
