/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.sandwood.common.execution.ExecutionType;

public class TestStringGenerator {

    public String processString(Object o, String sourceDir, String testName, String data, boolean constructingResults,
            String extension) {
        return processString(o, sourceDir, testName, null, null, data, constructingResults, extension);
    }

    public String processString(Object o, String sourceDir, String testName, String tag, String data,
            boolean constructingResults, String extension) {
        return processString(o, sourceDir, testName, null, tag, data, constructingResults, extension);
    }

    public String processString(Object o, String sourceDir, String testName, ExecutionType type, String data,
            boolean constructingResults, String extension) {
        return processString(o, sourceDir, testName, type, null, data, constructingResults, extension);
    }

    public String processString(Object o, String sourceDir, String testName, ExecutionType type, String tag,
            String data, boolean constructingResults, String extension) {
        if(constructingResults) {
            String fileName = getFilename(o, sourceDir, testName, type, tag, extension);
            System.out.println("Writing to " + fileName);

            try {
                File file = new File(fileName);
                File directory = file.getParentFile();
                directory.mkdirs();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.append(data);
                fileWriter.close();
            } catch(IOException e) {
                e.printStackTrace();
            }

            return fileName;
        } else {
            return getFilename(o, sourceDir, testName, type, tag, extension);
        }
    }

    public String getFilename(Object o, String outputDir, String testName, String extension) {
        return getFilename(o, outputDir, testName, null, null, extension);
    }

    public String getFilename(Object o, String outputDir, String testName, String tag, String extension) {
        return getFilename(o, outputDir, testName, null, tag, extension);
    }

    public String getFilename(Object o, String outputDir, String testName, ExecutionType type, String extension) {
        return getFilename(o, outputDir, testName, type, null, extension);
    }

    public String getFilename(Object o, String outputDir, String testName, ExecutionType type, String tag,
            String extension) {
        String filename = outputDir;

        String className = o.getClass().getName();
        String[] classParts = className.split("\\.");
        for(int i = 4; i < classParts.length; i++) // Ignore the org.sandwood.tests bit.
            filename += File.separator + classParts[i];

        filename += File.separator + testName;

        if(tag != null && !tag.isEmpty())
            filename += File.separator + tag;

        if(type != null)
            filename += "-" + type;

        filename += "." + extension;
        return filename;
    }

    public static String getPackageDir(Object o, String outputDir) {
        String packageName = o.getClass().getPackageName();
        String[] classParts = packageName.split("\\.");
        String packageDir = outputDir;
        for(String classPart:classParts)
            if(packageDir.equals(""))
                packageDir = classPart;
            else
                packageDir += File.separator + classPart;
        return packageDir;
    }
}