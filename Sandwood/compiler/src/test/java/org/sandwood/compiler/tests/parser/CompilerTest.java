/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.compilation.util.CompileUtils;
import org.sandwood.compiler.compilation.util.SandwoodCompileError;
import org.sandwood.compiler.dataflowGraph.Sandwood;
import org.sandwood.compiler.tests.parser.ExpectedErrors.ErrorDesc;
import org.sandwood.compiler.tests.util.CompilerState;
import org.sandwood.compiler.tests.util.TestStringGenerator;

class CompilerTest {
    private static final String sourceDir = "src" + File.separator + "test" + File.separator + "resources"
            + File.separator + "testInputs";
    private static final Set<String> ignore = new HashSet<>();
    private static final Set<String> partialInference = new HashSet<>();
    private static final CompilerTest classInstance = new CompilerTest();

    static Stream<String> getFileNameArgsOptimise() {
        String packageDir = TestStringGenerator.getPackageDir(classInstance, sourceDir);

        File dir = new File(packageDir);
        assertTrue(dir.isDirectory());
        String[] files = dir.list((dir1, name) -> name.endsWith(".sandwood"));

        for(int i = 0; i < files.length; i++)
            files[i] = packageDir + File.separator + files[i];

        Arrays.sort(files);

        return Stream.of(files);
    }

    static Stream<String> getFileNameArgs() {
        String packageDir = TestStringGenerator.getPackageDir(classInstance, sourceDir);

        File dir = new File(packageDir);
        assertTrue(dir.isDirectory());
        String[] files = dir.list((dir1, name) -> name.endsWith(".sandwood") && !ignore.contains(name));

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

    // Set temporary and target directories.
    File targetDir;

    @ParameterizedTest
    @MethodSource("getFileNameArgs")
    @DisplayName("Compile model to class file")
    void testFile(String file) {
        try {
            targetDir = Files.createTempDirectory(null).toFile();
            CompilationOptions opts = new CompilationOptions();
            opts.setTargetDirectory(targetDir.getAbsolutePath());
            opts.disableOptimisation();
            if(partialInference.contains(file))
                opts.fullInferenceRequired(false);
            opts.setToJavadoc();
            // System.out.println("Compiling to: " + opts.getTargetDirectory());
            opts.setModelPath(sourceDir);
            opts.setModelFile(file);
            List<SandwoodCompileError> errors = Sandwood.compileModel(opts).getErrors();

            // Check any errors match with those expected.
            ErrorDesc ed = ExpectedErrors.getErrorDesc(file.substring(sourceDir.length() + 1));
            if(ed == null) {
                if(!errors.isEmpty())
                    assertEquals("", CompileUtils.parseErrors(errors));
            } else {
                if(ed.annotatedMessages.size() != errors.size()) {
                    StringBuilder sb = new StringBuilder();
                    for(String s:ed.annotatedMessages)
                        sb.append(s + "\n");
                    assertEquals(sb.toString(), CompileUtils.parseErrors(errors));
                }
                for(int i = 0; i < errors.size(); i++)
                    assertEquals(ed.annotatedMessages.get(i), errors.get(i).toString());
            }

        } catch(Exception e) {
            System.err.println("Error with file: " + file);
            System.err.println(e.getMessage());
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @ParameterizedTest
    @MethodSource("getFileNameArgsOptimise")
    @DisplayName("Compile model to optimised class file")
    void testOptimisedFile(String file) {
        try {
            targetDir = Files.createTempDirectory(null).toFile();
            CompilationOptions opts = new CompilationOptions();
            opts.setTargetDirectory(targetDir.getAbsolutePath());
            opts.fullInferenceRequired(false);
            // System.out.println("Compiling to: " + opts.getTargetDirectory());
            opts.setModelPath(sourceDir);
            opts.setModelFile(file);
            List<SandwoodCompileError> errors = Sandwood.compileModel(opts).getErrors();

            // Check any errors match with those expected.
            ErrorDesc ed = ExpectedErrors.getErrorDesc(file.substring(sourceDir.length() + 1));
            if(ed == null)
                assertTrue(errors.isEmpty());
            else {
                assertEquals(ed.annotatedMessages.size(), errors.size());
                for(int i = 0; i < errors.size(); i++)
                    assertEquals(ed.annotatedMessages.get(i), errors.get(i).toString());
            }

        } catch(Exception e) {
            System.err.println("Error with file: " + file);
            System.err.println(e.getMessage());
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @AfterEach
    protected void cleanup() {
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
