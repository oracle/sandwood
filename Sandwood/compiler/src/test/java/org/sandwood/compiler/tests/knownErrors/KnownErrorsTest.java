/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.knownErrors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import org.sandwood.compiler.tests.util.CompilerState;
import org.sandwood.compiler.tests.util.TestStringGenerator;
import org.sandwood.compiler.trees.outputTree.OutputSandwoodClass;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.util.Pair;

class KnownErrorsTest {
    private static final String tempDir = "tmp";
    private static final String resourceDir = "src" + File.separator + "test" + File.separator + "resources";
    private static final String sourceDir = resourceDir + File.separator + "testInputs";

    static Stream<String> getFileNameArgs() {
        String packageDir = TestStringGenerator.getPackageDir(new KnownErrorsTest(), sourceDir);

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
    @DisplayName("Generate basic Java code - no comments")
    void testFileBasicNoComments(String file) {
        try {
            System.out.println("File: " + file);
            // System.out.println("File Content:\n" + new
            // String(Files.readAllBytes(Paths.get(file))));

            Pair<GeneratedAPIBuilder, ClassName> p = buildAPI(file);

            // Build the unoptimised Sandwood class from the api.
            List<SandwoodModelException> errors = buildCode(p.a, false, true, false);
            
                for(SandwoodModelException e:errors)
                    System.out.println(e);
                assertTrue(errors.isEmpty());

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
    @DisplayName("Generate Optimised Java code - No Comments")
    void testFileOptimisedNoComments(String file) {
        try {
            System.out.println("File: " + file);
            // System.out.println("File Content:\n" + new
            // String(Files.readAllBytes(Paths.get(file))));

            Pair<GeneratedAPIBuilder, ClassName> p = buildAPI(file);

            // Build the optimised Sandwood class from the api.
            List<SandwoodModelException> errors = buildCode(p.a, true, true, false);

                for(SandwoodModelException e:errors)
                    System.out.println(e);
                assertTrue(errors.isEmpty());

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
            p.constructClass();

            ClassName apiClassName = p.getClassName();

            String[] fileParts = file.split(File.separator.equals("\\") ? "\\\\" : File.separator);
            String expectedModelName = fileParts[fileParts.length - 1].split("\\.")[0];
            assertEquals(expectedModelName, apiClassName.getName());

        } catch(Exception e) {
            System.err.println("Error with file: " + file);
            System.err.println(e.getMessage());
            e.printStackTrace();
            assertTrue(false);
        }
    }

    private List<SandwoodModelException> buildCode(GeneratedAPIBuilder apiClass,
            boolean optimised, boolean individualProbs, boolean comments) throws IOException {
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
            }

            diagnostics = CompileUtils.compileToJava(opts, compDesc.classes);
            if(!diagnostics.getDiagnostics().isEmpty()) {
                CompileUtils.printErrorsSimple(diagnostics);
                if(!optimised) {
                    for(Diagnostic<? extends JavaFileObject> diagnostic:diagnostics.getDiagnostics())
                            System.out.println(diagnostic.getMessage(null));
                            assertTrue(false);
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
