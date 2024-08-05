/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.tools.DocumentationTool;
import javax.tools.ToolProvider;

import org.sandwood.compiler.compilation.util.SandwoodCompileError;
import org.sandwood.compiler.trees.outputTree.OutputSandwoodClass;

public class JavadocBuilder {

    public static List<SandwoodCompileError> buildJavadoc(List<OutputSandwoodClass> classes,
            InputStream[] supportingSources, String targetDir) throws IOException {
        File tempDir = Files.createTempDirectory(null).toFile();

        // Ensure the target directory exists.
        (new File(targetDir)).mkdirs();

        DocumentationTool documentationTool = ToolProvider.getSystemDocumentationTool();
        int result = documentationTool.run(null, null, null, getArgs(toFiles(classes, tempDir),
                unzip(supportingSources, tempDir), "-public", "-d", targetDir, "-quiet"));

        deleteFile(tempDir);
        if(result == 0)
            return Collections.emptyList();
        else {
            List<SandwoodCompileError> l = new ArrayList<>();
            l.add(new SandwoodCompileError(
                    "Compilation failed as there were errors when constructing JavaDoc for the model."));
            return l;
        }
    }

    /**
     * Method to arrange all the arguments for the call to Javadoc. These are placed in an array with args appearing
     * first, followed by the contents of files, and supportingSources.
     * 
     * @param files             An array containing the files we want to create javadoc for.
     * @param supportingSources An array containing model files for the interfaces we want to present to the user in the
     *                          resultant java docs.
     * @param args              The arguments we want to pass to Javadoc.
     * @return An array of arguments for the tool.
     */
    private static String[] getArgs(String[] files, String[] supportingSources, String... args) {
        // Construct an array for the returned args.
        String[] combinedArgs = new String[args.length + files.length + supportingSources.length];

        // Copy the provided args.
        System.arraycopy(args, 0, combinedArgs, 0, args.length);
        int offset = args.length;

        // Add in the files
        System.arraycopy(files, 0, combinedArgs, 0 + offset, files.length);
        offset += files.length;

        // Add in the supporting sources
        System.arraycopy(supportingSources, 0, combinedArgs, offset, supportingSources.length);

        return combinedArgs;
    }

    /**
     * Method for taking an array of classes and saving them to disk in the targetDirectory, returning an array of all
     * the file locations.
     * 
     * @param classes       List of classes to instantiate.
     * @param targetDirFile Location to create the files in.
     * @return An array listing the locations of all the files.
     * @throws IOException
     */
    private static String[] toFiles(List<OutputSandwoodClass> classes, File targetDirFile) throws IOException {
        String targetDir = targetDirFile.getAbsolutePath();
        int noFiles = classes.size();
        String[] files = new String[noFiles];
        for(int i = 0; i < noFiles; i++)
            files[i] = classes.get(i).toFile(targetDir);
        return files;
    }

    /**
     * Method that takes a list of zip files and extracts the java file in them into a target directory.
     * 
     * @param zipFiles A list of zip files.
     * @param tempDir  The target directory.
     * @return A list of all the files extracted.
     * @throws IOException
     */
    private static String[] unzip(InputStream[] zipStreams, File tempDir) throws IOException {
        Set<String> files = new HashSet<>();
        for(InputStream zipStream:zipStreams)
            files.addAll(unzip(zipStream, tempDir));
        return files.toArray(new String[files.size()]);
    }

    /**
     * Method to unzip all the java files in a stream from a zip file into a destination directory, returning a list of
     * all the java files unzipped.
     * 
     * @param steam          A stream from the zipped resource.
     * @param destinationDir The location to unzip the java files into.
     * @return A set of all the files extracted.
     * @throws IOException
     */
    private static Set<String> unzip(InputStream stream, File destinationDir) throws IOException {
        Set<String> files = new HashSet<>();

        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(stream);
        ZipEntry zipEntry = zis.getNextEntry();
        while(zipEntry != null) {
            String name = zipEntry.getName();
            if(name.endsWith(".java")) {
                File newFile = new File(destinationDir, name);
                new File(newFile.getParent()).mkdirs();

                files.add(newFile.getAbsolutePath());

                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();

        return files;
    }

    /**
     * Method to delete a file, and if the file is a directory all of its contents.
     * 
     * @param file The file to delete.
     */
    private static void deleteFile(File file) {
        if(file.exists()) {
            if(file.isDirectory()) {
                for(File subFile:file.listFiles())
                    deleteFile(subFile);
            }
            file.delete();
        }
    }
}
