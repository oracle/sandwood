/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.getSetTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.compilation.ChildFirstClassLoader;
import org.sandwood.compiler.compilation.util.CompilationDesc;
import org.sandwood.compiler.compilation.util.CompileUtils;
import org.sandwood.compiler.tests.util.CompilerState;
import org.sandwood.compiler.trees.outputTree.OutputSandwoodClass;
import org.sandwood.runtime.model.Model;
import org.sandwood.runtime.model.RetentionPolicy;
import org.sandwood.runtime.model.variables.ComputedVariable;
import org.sandwood.runtime.model.variables.ComputedVariable.Immutability;

public abstract class ModelTestSkeleton {
    private final String tempDir = "tmp";

    protected abstract CompilationDesc buildClass(CompilationOptions opts);

    protected abstract List<String> getVariables();

    @BeforeEach
    void resetStaticValues() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        CompilerState.reset();
    }

    @Test
    void testVariableConfiguration() {
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
            testSettings(cls, m);

            m.close();
            cl.close();
        } catch(Exception e) {
            e.printStackTrace();
            fail("This code should not throw exceptions:" + e.getMessage());
        }
    }

    private void testSettings(Class<?> cls, Model m) throws IllegalAccessException, NoSuchFieldException {
        assertTrue(m.readyExecute());
        m.execute();

        // Call the get results method
        List<String> variables = getVariables();
        for(String variable:variables) {
            Field f = cls.getField(variable);
            Object variableObj = f.get(m);

                ComputedVariable c = (ComputedVariable) variableObj;
                assertEquals(c.isFixed(), Immutability.FREE);
                assertEquals(c.getRetentionPolicy(), RetentionPolicy.SAMPLE);
                
                c.setFixed(true);
                assertEquals(c.isFixed(), Immutability.FIXED);
                assertEquals(c.getRetentionPolicy(), RetentionPolicy.NA);
                
                c.setRetentionPolicy(RetentionPolicy.MAP);
                assertEquals(c.isFixed(), Immutability.FIXED);
                assertEquals(c.getRetentionPolicy(), RetentionPolicy.NA);
                
                c.setFixed(false);
                assertEquals(c.isFixed(), Immutability.FREE);
                assertEquals(c.getRetentionPolicy(), RetentionPolicy.MAP);
        }
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
}
