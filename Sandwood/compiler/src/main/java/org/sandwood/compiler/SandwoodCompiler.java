/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

import org.sandwood.compiler.compilation.ChildFirstClassLoader;
import org.sandwood.compiler.compilation.ExtendedSandwoodParser;
import org.sandwood.compiler.compilation.GeneratedAPIBuilder;
import org.sandwood.compiler.compilation.JarBuilder;
import org.sandwood.compiler.compilation.JavadocBuilder;
import org.sandwood.compiler.compilation.OutputSourceBuilder;
import org.sandwood.compiler.compilation.util.CompilationDesc;
import org.sandwood.compiler.compilation.util.CompileUtils;
import org.sandwood.compiler.compilation.util.CompileUtils.JavaSourceFromString;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.compilation.util.SandwoodCompileError;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.exceptions.SandwoodModelException;
import org.sandwood.compiler.exceptions.SandwoodParseException;
import org.sandwood.compiler.names.ClassName;
import org.sandwood.compiler.names.PackageName;
import org.sandwood.compiler.srcTools.sourceToSource.ParseException;
import org.sandwood.compiler.srcTools.sourceToSource.SandwoodParser;
import org.sandwood.compiler.srcTools.sourceToSource.TokenMapping;
import org.sandwood.compiler.trees.outputTree.OutputSandwoodClass;

/**
 * A class for compiling Sandwood models.
 */
public class SandwoodCompiler {
    private static class ClassDesc {
        final String sourceFilename;
        final PackageName packageName;
        final ClassName className;

        ClassDesc(String sourceFilename, PackageName packageName, ClassName className) {
            this.sourceFilename = sourceFilename;
            this.packageName = packageName;
            this.className = className;
        }
    }

    /** location of the source codes to include in the model output. */
    private static InputStream[] getJavadocSupportingSources() {
        InputStream[] streams = new InputStream[3];
        streams[0] = getSupportingSourceStream("runtime-interfaces-src.jar");
        streams[1] = getSupportingSourceStream("common-interfaces-src.jar");
        streams[2] = getSupportingSourceStream("jdk-rngs-interfaces-src.jar");
        return streams;
    }

    private static InputStream getSupportingSourceStream(String filename) {
        try {
            Class<?> cls = Class.forName("org.sandwood.compiler.SandwoodCompiler");

            // returns the ClassLoader object associated with this Class
            ClassLoader cLoader = cls.getClassLoader();
            InputStream stream = cLoader.getResourceAsStream(filename);
            if(stream != null)
                return stream;
        } catch(ClassNotFoundException e) {
            throw new CompilerException("Unable to find class SandwoodCompiler. This should not be possible.");
        }

        /*
         * This is left in so that the Javadoc can be generated when running Sandwoods tests in an IDE that has not
         * constructed the jar files.
         */
        try {
            return new FileInputStream("." + File.separator + "src" + File.separator + "main" + File.separator
                    + "resources" + File.separator + filename);
        } catch(FileNotFoundException e) {
            throw new CompilerException("Unable to find the file " + filename + " as a resource or in the "
                    + "directory ." + File.separator + "src" + File.separator + "main" + File.separator + "resources.");
        }
    }

    /**
     * A static method to compile a Sandwood Model.
     * 
     * @param opts A compiler options object describing the compilation to undertake.
     * @return A list containing any errors that were detected in the model when it was compiled.
     * @throws IOException Thrown if there are problems reading the model, or problems accessing the file system to
     *                     create temporary files.
     */
    public static CompilationOutput compileModel(CompilationOptions opts) throws IOException {
        CompilationOutput compilationOutput = new CompilationOutput();

        List<JavaSourceFromString> compilationUnits = new ArrayList<>();
        List<ClassDesc> apiClasses = new ArrayList<>();

        // Set temporary and target directories.
        File tempDir = Files.createTempDirectory(null).toFile();
        Map<String, TokenMapping> mapping = new HashMap<>();

        for(String filename:opts.modelFiles()) {
            System.out.println("File: " + filename);
            if(opts.verbose())
                System.out.println("File Content:\n" + new String(Files.readAllBytes(Paths.get(filename))));

            // Parse input
            String apiBody;
            SandwoodParser p;
            try {
                p = new ExtendedSandwoodParser(filename);
                p.parse();
                apiBody = p.constructClass();
            } catch(SandwoodModelException e) {
                compilationOutput.addErrors(CompileUtils.constructErrorsSandwood(filename, e, opts.modelPath()));
                return compilationOutput;
            } catch(SandwoodParseException e) {
                compilationOutput.addErrors(CompileUtils.constructErrorsSandwood(filename, e, opts.modelPath()));
                return compilationOutput;
            } catch(ParseException e) {
                compilationOutput.addErrors(CompileUtils.constructErrorsSandwood(filename, e, opts.modelPath()));
                return compilationOutput;
            }

            if(opts.verbose())
                System.out.println("API code:\n" + apiBody);

            File file = new File(filename);

            // Check the file name and model name match
            ClassName apiClassName = p.getClassName();
            // remove .sandwood extension.
            String expectedApiClassName = file.getName().split("\\.")[0];
            if(!expectedApiClassName.equals(apiClassName.getName())) {
                compilationOutput.addErrors(constructCompilerError("Model " + apiClassName
                        + " should be in a file called " + apiClassName + ".sandwood, not " + file.getName()));
                return compilationOutput;
            }

            // Check the package name and the directory match.
            PackageName packageName = p.getPackageName();
            File expectedDirFile = file.getParentFile();
            String expectedDirName = expectedDirFile.getPath();
            String absoluteExpectedDir = expectedDirFile.getAbsolutePath();

            // Because of the handling of arguments in the argument parser we know that one
            // directory will be a prefix of the file, and only one directory.
            for(String dir:opts.modelPath()) {
                String absoluteDir = new File(dir).getAbsolutePath();
                if(absoluteExpectedDir.startsWith(absoluteDir)) {
                    String expectedPackageName = absoluteExpectedDir.substring(absoluteDir.length() + 1)
                            .replace(File.separator, ".");

                    if(!expectedPackageName.equals(packageName.getName())) {
                        compilationOutput.addError(new SandwoodCompileError("Model " + apiClassName
                                + " should be in the directory:\n" + dir + File.separator
                                + packageName.getName().replace(".", File.separator) + ", not:\n" + expectedDirName));
                        return compilationOutput;
                    }
                    break;
                }
            }

            String completePath = (packageName.isEmpty() ? "" : packageName + ".") + apiClassName;
            mapping.put(completePath.replace(".", File.separator) + ".sandwood", p.getTokenMapping());
            if(opts.verbose())
                System.out.println("compiling file to " + tempDir
                        + packageName.getName().replace('.', File.separatorChar) + File.separator + apiClassName);
            compilationUnits.add(new JavaSourceFromString(packageName + "." + apiClassName, apiBody));
            apiClasses.add(new ClassDesc(filename, packageName, apiClassName));
        }
        DiagnosticCollector<JavaFileObject> diagnostics = CompileUtils.compileToJava(tempDir.getAbsolutePath(),
                compilationUnits);
        if(!diagnostics.getDiagnostics().isEmpty()) {
            compilationOutput.addErrors(CompileUtils.constructErrorsSandwood(diagnostics, mapping));
            return compilationOutput;
        }

        // Having compiles all the API classes generated by the models that were passed
        // (Phase 1), Invoke each of those API classes to compile them to their final
        // model.

        // (Phase 2)

        // Construct a URL[] containing the output directory.
        URL url = tempDir.toURI().toURL();
        URL[] urls = new URL[] { url };

        // List to store any classes to javadoc later
        List<OutputSandwoodClass> javadocClasses = new ArrayList<>();

        // Load the contents
        ChildFirstClassLoader cl = new ChildFirstClassLoader(urls);
        for(ClassDesc apiClassDesc:apiClasses) {
            // Load the class
            String className = ((apiClassDesc.packageName != null) ? apiClassDesc.packageName + "." : "")
                    + apiClassDesc.className;
            GeneratedAPIBuilder apiClass;
            try {
                Class<?> cls = cl.loadClass(className);
                Constructor<?> cons = cls.getConstructor();
                Object o = cons.newInstance();
                apiClass = (GeneratedAPIBuilder) o;

            } catch(ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                    | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                String message = "Error at:\n" + sw + "\nReflection Error: " + e.getMessage();
                throw new CompilerException(message);
            }

            // Build the Sandwood class from the api.
            CompilationDesc compDesc;
            try {
                compDesc = apiClass.buildClass(opts);
            } catch(SandwoodModelException e) {
                ScopeStack.reset();
                compilationOutput.addErrors(CompileUtils.constructErrorsSandwood(
                        className.replace(".", File.separator) + ".sandwood", e, opts.modelPath()));
                continue;
            }

            // Record any warnings
            compilationOutput.addWarnings(CompileUtils.constructWarningsSandwood(
                    className.replace(".", File.separator) + ".sandwood", compDesc.warnings, mapping));

            // Test if there are errors before proceeding with this class.
            if(!compDesc.errors.isEmpty()) {
                compilationOutput.addErrors(CompileUtils.constructErrorsSandwood(
                        className.replace(".", File.separator) + ".sandwood", compDesc.errors, mapping));
            } else if(!compDesc.classes.isEmpty()) {
                if(opts.toJar()) { // If compiling to a JarFile
                    // Compile into a temporary directory that the contents of can then be placed in
                    // a Jar file.
                    File tempJarDir = Files.createTempDirectory(null).toFile();
                    String targetDir = opts.targetDirectory();

                    // Build the supporting java doc if required.
                    if(opts.toJavadoc()) {
                        if(opts.javadocDir() == null && !opts.noCompile())
                            compilationOutput.addErrors(JavadocBuilder.buildJavadoc(compDesc.classes,
                                    getJavadocSupportingSources(), tempJarDir.getAbsolutePath()));
                        else {
                            File tempJavaDocDir = Files.createTempDirectory(null).toFile();

                            compilationOutput.addErrors(JavadocBuilder.buildJavadoc(compDesc.classes,
                                    getJavadocSupportingSources(), tempJavaDocDir.getAbsolutePath()));
                            compilationOutput.addErrors(JarBuilder.buildJar(tempJavaDocDir,
                                    apiClassDesc.className + "-doc", opts.javadocDir()));

                            deleteFile(tempJavaDocDir);
                        }
                    }

                    // Build the supporting java src if required.
                    if(opts.toSource()) {
                        if(opts.sourceDestination() == null && !opts.noCompile()) {
                            // Place the source files in the same directory as the compiled files so they
                            // all are placed in the same jar file.
                            compilationOutput.addErrors(OutputSourceBuilder.buildOutputSource(compDesc.classes,
                                    tempJarDir.getAbsolutePath()));
                        } else {
                            File tempJavaSrcDir = Files.createTempDirectory(null).toFile();

                            compilationOutput.addErrors(OutputSourceBuilder.buildOutputSource(compDesc.classes,
                                    tempJavaSrcDir.getAbsolutePath()));

                            String sourceOut = (opts.sourceDestination() == null) ? "." : opts.sourceDestination();
                            compilationOutput.addErrors(
                                    JarBuilder.buildJar(tempJavaSrcDir, apiClassDesc.className + "-src", sourceOut));

                            deleteFile(tempJavaSrcDir);
                        }
                    }

                    if(!opts.noCompile()) {
                        // Compile the class and place it in a jar
                        opts.setTargetDirectory(tempJarDir.getAbsolutePath());
                        diagnostics = CompileUtils.compileToJava(opts, compDesc.classes);
                        compilationOutput.addErrors(
                                JarBuilder.buildJar(tempJarDir, apiClassDesc.className.getName(), targetDir));
                    }

                    // Delete the temporary directory
                    deleteFile(tempJarDir);
                } else { // Otherwise just compile the classes and place them in the target directory.
                    if(!opts.noCompile())
                        diagnostics = CompileUtils.compileToJava(opts, compDesc.classes);

                    // Build the supporting javadoc if required.
                    if(opts.toJavadoc()) {
                        javadocClasses.addAll(compDesc.classes);
                    }

                    if(opts.toSource()) {
                        compilationOutput.addErrors(OutputSourceBuilder.buildOutputSource(compDesc.classes,
                                opts.sourceDestination(), apiClassDesc.sourceFilename, apiClassDesc.packageName));
                    }
                }
                if(!diagnostics.getDiagnostics().isEmpty()) {
                    CompileUtils.printErrorsSimple(diagnostics);
                    cl.close();
                    throw new CompilerException(
                            "Compilation of generated class failed. This is probably an incomplete part of Sandwood");
                }
            }
        }

        deleteFile(tempDir);
        cl.close();

        // If there have been errors return them.
        if(!compilationOutput.noErrors())
            return compilationOutput;

        if(!javadocClasses.isEmpty()) {
            if(opts.javadocDir() == null)
                compilationOutput.addErrors(JavadocBuilder.buildJavadoc(javadocClasses, getJavadocSupportingSources(),
                        opts.targetDirectory()));
            else
                compilationOutput.addErrors(
                        JavadocBuilder.buildJavadoc(javadocClasses, getJavadocSupportingSources(), opts.javadocDir()));
        }
        return compilationOutput;
    }

    private static List<SandwoodCompileError> constructCompilerError(String message) {
        List<SandwoodCompileError> error = new ArrayList<>();
        error.add(new SandwoodCompileError(message));
        return error;
    }

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
