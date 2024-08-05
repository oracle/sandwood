/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.sandwood.compiler.compilation.util.SandwoodCompileError;
import org.sandwood.compiler.names.PackageName;
import org.sandwood.compiler.trees.outputTree.OutputSandwoodClass;

public class OutputSourceBuilder {

    public static List<SandwoodCompileError> buildOutputSource(List<OutputSandwoodClass> classes, String sourceDir,
            String sourceFilename, PackageName packageName) {
        String sourceOut = getSourceDirectory(sourceDir, sourceFilename, packageName);
        return buildOutputSource(classes, sourceOut);
    }

    // TODO consider catching the exception and returning false.
    public static List<SandwoodCompileError> buildOutputSource(List<OutputSandwoodClass> classes, String sourceDir) {
        try {
            for(OutputSandwoodClass c:classes)
                c.toFile(sourceDir);
            return Collections.emptyList();
        } catch(IOException e) {
            List<SandwoodCompileError> errors = new ArrayList<>();
            errors.add(new SandwoodCompileError("IO Error: " + e.getMessage()));
            return errors;
        }
    }

    private static String getSourceDirectory(String sourceDir, String sourceFilename, PackageName packageName) {
        if(sourceDir != null) // Get the directory that the file came from if no source dir is set.
            return sourceDir;
        else {
            String separator = (File.separator.equals("\\")) ? "\\\\" : File.separator;
            String[] parts = sourceFilename.split(separator);

            // Set end so that we don't include the filename
            int end = parts.length - 1;

            // Remove the package from the set of directories we are composing.
            if(packageName != null) {
                String[] packageParts = packageName.getName().split("\\.");
                int toMatch = packageParts.length;
                int offset = end - toMatch;
                if(offset >= 0) { // Check that there are enough directories to match the package directory.
                    while(toMatch > 0 && packageParts[toMatch - 1].equals(parts[offset + toMatch - 1]))
                        toMatch--;
                }

                if(toMatch == 0) // If we have successfully matched the entire package remove it from the path.
                    end -= packageParts.length;
            }

            if(end <= 0)// If there was just the file name and package directories return dot.
                return ".";

            // Else compose the remaining bits together.
            String sourceOut = parts[0];
            for(int i = 1; i < end; i++)
                sourceOut += File.separator + parts[i];
            return sourceOut;
        }
    }
}
