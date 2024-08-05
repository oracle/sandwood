/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.sandwood.compiler.compilation.util.SandwoodCompileError;

public class JarBuilder {
    public static List<SandwoodCompileError> buildJar(File sourceDir, String name, String targetDir)
            throws IOException {
        List<SandwoodCompileError> errors = new ArrayList<>();
        if(!sourceDir.isDirectory()) {
            errors.add(new SandwoodCompileError(
                    "Source location " + sourceDir.getAbsolutePath() + " is not a directory."));
            return errors;
        }

        // Construct a manifest in the jar file.
        constructManifest(sourceDir);

        // Create the directory to hold the jar file.
        File target = new File(targetDir);
        if(!target.exists() && !target.mkdirs()) {
            errors.add(new SandwoodCompileError(
                    "Unable to create directory for jar file: " + target.getAbsolutePath() + "."));
            return errors;
        }

        // Zip the content of the source directory and place it in a jar file.
        File jarFile = new File(targetDir + File.separator + name + ".jar");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(jarFile));
        addFiles(out, sourceDir);
        out.close();
        return errors;
    }

    private static void constructManifest(File sourceDir) throws IOException {
        // Create directory for the manifest
        File dir = new File(sourceDir.getAbsolutePath() + File.separator + "META-INF");
        if(!dir.exists() && !dir.mkdirs())
            throw new IOException("Unable to create directory for meta data: " + dir.getAbsolutePath() + ".");

        // Create the manifest file.
        File manifest = new File(dir.getAbsolutePath() + File.separator + "MANIFEST.MF");
        FileWriter out = new FileWriter(manifest);

        // Write the content.
        out.write("Manifest-Version: 1.0\n");

        // Close the file.
        out.close();
    }

    // Handle the initial directory.
    private static void addFiles(ZipOutputStream out, File file) throws IOException {
        // Add all the files in the subdirectory.
        for(File f:file.listFiles())
            addFiles(out, f, "");
    }

    private static void addFiles(ZipOutputStream out, File file, String path) throws IOException {
        if(file.isDirectory()) {
            // Update the path
            path = path + file.getName() + File.separator;

            // Add all the files in the subdirectory.
            for(File f:file.listFiles())
                addFiles(out, f, path);

        } else { // This is a file
            // Complete the path
            path = path + file.getName();

            // Construct an entry for the file in the zip file
            ZipEntry e = new ZipEntry(path);
            out.putNextEntry(e);

            // Read the contents of the file
            FileInputStream fileStream = new FileInputStream(file);
            byte[] data = fileStream.readAllBytes();
            fileStream.close();

            // Write the file contents to the entry and close it.
            out.write(data, 0, data.length);
            out.closeEntry();
        }
    }
}
