/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.examplePrograms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.tests.util.CompilerState;
import org.sandwood.compiler.tests.util.TestStringGenerator;
import org.sandwood.compiler.util.StringUtil;

public abstract class ModelTestSkeleton {

    private String expectedGraphString;
    private String expectedCode;
    private String expectedCodeInline;

    protected abstract ArrayList<Variable<?>> buildGraph();

    private final String sourceDir = "src" + File.separator + "test" + File.separator + "resources" + File.separator
            + "expectedOutputs";
    private final boolean constructingResults = false;
    private final TestStringGenerator tsg = new TestStringGenerator();

    private final String graphStringTag = "graphString";
    private final String verboseCodeTag = "verboseCode";
    private final String inlineCodeTag = "inlineCode";

    public ModelTestSkeleton() {
        super();

        // expectedGraphString
        String filename = tsg.getFilename(this, sourceDir, graphStringTag, "txt");
        try {
            expectedGraphString = new String(Files.readAllBytes(Paths.get(filename)));
            // Normalise newline characters This second use is required because GitHub
            // rewrites the data files.
            expectedGraphString = StringUtil.normalizeNewLines(expectedGraphString);
        } catch(IOException e) {
            expectedGraphString = "";
            System.err.println("Failed to read file \"" + filename + "\"");
        }

        // expectedCode
        filename = tsg.getFilename(this, sourceDir, verboseCodeTag, "txt");
        try {
            expectedCode = new String(Files.readAllBytes(Paths.get(filename)));
            // Normalise newline characters This second use is required because GitHub
            // rewrites the data files.
            expectedCode = StringUtil.normalizeNewLines(expectedCode);
        } catch(IOException e) {
            expectedCode = "";
            System.err.println("Failed to read file \"" + filename + "\"");
        }

        // expectedCodeInline
        filename = tsg.getFilename(this, sourceDir, inlineCodeTag, "txt");
        try {
            expectedCodeInline = new String(Files.readAllBytes(Paths.get(filename)));
            // Normalise newline characters This second use is required because GitHub
            // rewrites the data files.
            expectedCodeInline = StringUtil.normalizeNewLines(expectedCodeInline);
        } catch(IOException e) {
            expectedCodeInline = "";
            System.err.println("Failed to read file \"" + filename + "\"");
        }
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
    protected void testGetSandwoodCodeInline() {
        try {
            ArrayList<Variable<?>> output = buildGraph();
            String generatedCode = Variable.getSandwoodCode(true, true, output);
            // normalise new line characters.
            generatedCode = StringUtil.normalizeNewLines(generatedCode);
            tsg.processString(this, sourceDir, inlineCodeTag, generatedCode, constructingResults, "txt"); // Tool to
                                                                                                          // make
                                                                                                          // changes to
                                                                                                          // the
                                                                                                          // expected
                                                                                                          // result
            // easier to incorporate.
            assertEquals(expectedCodeInline, generatedCode);
            assertFalse(constructingResults, "Results are being written to files, this invalidates the tests.");
        } catch(SandwoodException e) {
            fail("This code should not throw exceptions");
            e.printStackTrace();
        }
    }

    /**
     * A test method to check that the code generated from a model has not changed.
     */
    @Test
    protected void testGetSandwoodCode() {
        try {
            ArrayList<Variable<?>> output = buildGraph();
            String generatedCode = Variable.getSandwoodCode(false, true, output);
            // normalise new line characters.
            generatedCode = StringUtil.normalizeNewLines(generatedCode);

            tsg.processString(this, sourceDir, verboseCodeTag, generatedCode, constructingResults, "txt"); // Tool to
                                                                                                           // make
                                                                                                           // changes to
                                                                                                           // the
                                                                                                           // expected
                                                                                                           // result
            // easier to incorporate.
            assertEquals(expectedCode, generatedCode);
            assertFalse(constructingResults, "Results are being written to files, this invalidates the tests.");
        } catch(SandwoodException e) {
            fail("This code should not throw exceptions:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * A test method to check that the XML describing a visualisation of a model has not changed.
     */
    @Test
    protected void testGetGraphString() {
        try {
            ArrayList<Variable<?>> output = buildGraph();
            String graphString = Variable.getGraphAsString(false, output);

            tsg.processString(this, sourceDir, graphStringTag, graphString, constructingResults, "txt"); // Tool to make
                                                                                                         // changes to
                                                                                                         // the expected
                                                                                                         // result
            // easier to incorporate.

            // normalise new line characters.
            graphString = StringUtil.normalizeNewLines(graphString);
            assertEquals(expectedGraphString, graphString);
            assertFalse(constructingResults, "Results are being written to files, this invalidates the tests.");
        } catch(SandwoodException e) {
            fail("This code should not throw exceptions");
            e.printStackTrace();
        }
    }
}
