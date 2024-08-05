/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.exceptions.SandwoodModelException;
import org.sandwood.compiler.exceptions.SandwoodParseException;
import org.sandwood.compiler.names.ClassName;
import org.sandwood.compiler.names.PackageName;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.srcTools.sourceToSource.ParseException;
import org.sandwood.compiler.srcTools.sourceToSource.TokenMapping;
import org.sandwood.compiler.trees.outputTree.OutputSandwoodClass;

public class CompileUtils {
    /**
     * A file object used to represent source coming from a string.
     */
    public static class JavaSourceFromString extends SimpleJavaFileObject {
        /**
         * The source code of this "file".
         */
        final String code;

        /**
         * Constructs a new JavaSourceFromString.
         *
         * @param name the name of the compilation unit represented by this file object
         * @param code the source code for the compilation unit represented by this file object
         */
        public JavaSourceFromString(String name, String code) {
            super(URI.create("string:///" + name.replace(".", "/") + Kind.SOURCE.extension), Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
    }

    public static DiagnosticCollector<JavaFileObject> compileToJava(List<OutputSandwoodClass> classes)
            throws IOException {
        return compileToJava(new CompilationOptions(), classes);
    }

    public static DiagnosticCollector<JavaFileObject> compileToJava(CompilationOptions opts,
            List<OutputSandwoodClass> classes) throws IOException {

        final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        List<JavaSourceFromString> compilationUnits = new ArrayList<>();

        for(OutputSandwoodClass c:classes) {
            StringBuilder sb = new StringBuilder();
            c.toJava(sb);
            PackageName packageName = c.getPackage();
            String className = ((packageName != null) ? packageName.getName() + '.' : "") + c.getName();
            if(opts.verbose())
                System.out.println("\nGenerated model code for " + className + ":\n" + sb);
            compilationUnits.add(new JavaSourceFromString(className, sb.toString()));
        }

        List<String> compilerOptions = constructArgs(opts);

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        compiler.getTask(null, fileManager, diagnostics, compilerOptions, null, compilationUnits).call();

        printErrorsSimple(diagnostics);

        fileManager.close();

        return diagnostics;
    }

    /**
     * A method for constructing the command line options of the java compiler.
     * 
     * @param opts The options passed by the user.
     * @return String representing the options.
     */
    private static List<String> constructArgs(CompilationOptions opts) {
        List<String> compilerOptions = new ArrayList<>();

        String outputDir = opts.targetDirectory();
        if(outputDir != null) {
            compilerOptions.add("-d");
            compilerOptions.add(outputDir);
        }

        if(opts.releaseSet()) {
            compilerOptions.add("--release");
            compilerOptions.add(Integer.toString(opts.release()));
        }

        if(opts.sourceSet()) {
            compilerOptions.add("-source");
            compilerOptions.add(Integer.toString(opts.source()));
        }

        if(opts.targetSet()) {
            compilerOptions.add("-target");
            compilerOptions.add(Integer.toString(opts.target()));
        }

        return compilerOptions;
    }

    public static DiagnosticCollector<JavaFileObject> compileToJava(String outputDir, PackageName packageName,
            ClassName className, String classBody) throws IOException {
        JavaSourceFromString compilationUnit = new JavaSourceFromString(
                (packageName.isEmpty() ? "" : packageName.getName() + '.') + className.getName(), classBody);
        List<JavaSourceFromString> compilationUnits = new ArrayList<>();
        compilationUnits.add(compilationUnit);
        return compileToJava(outputDir, compilationUnits);
    }

    public static DiagnosticCollector<JavaFileObject> compileToJava(String outputDir,
            List<JavaSourceFromString> compilationUnits) throws IOException {
        final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        List<String> compilerOptions = new ArrayList<>();
        if(outputDir != null) {
            compilerOptions.add("-d");
            compilerOptions.add(outputDir);
        }

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        compiler.getTask(null, fileManager, diagnostics, compilerOptions, null, compilationUnits).call();

        fileManager.close();

        return diagnostics;
    }

    public static void printErrorsSimple(DiagnosticCollector<JavaFileObject> diagnostics) {
        for(Diagnostic<? extends JavaFileObject> diagnostic:diagnostics.getDiagnostics()) {
            JavaFileObject sourceObject = diagnostic.getSource();
            if(sourceObject != null) {
                String source = sourceObject.getName().replace('/', File.separatorChar);
                long line = diagnostic.getLineNumber();
                System.out.println("Error on line " + line + " in " + source);
            }
            System.out.println(diagnostic.getMessage(null));
        }
    }

    public static String parseErrorsSandwood(DiagnosticCollector<JavaFileObject> diagnostics,
            TokenMapping tokenMapping) {
        StringBuilder sb = new StringBuilder();
        List<Diagnostic<? extends JavaFileObject>> diagnosticList = diagnostics.getDiagnostics();

        int skippedErrors = 0;
        for(Diagnostic<? extends JavaFileObject> diagnostic:diagnosticList) {

            String message = diagnostic.getMessage(null);
            if(javaFunctionUsage(message))
                message = "Java functions cannot be called from Sandwood models";
            else
                message = correctSandwoodTypes(message);

            if(message.trim().equals("")) {
                skippedErrors++;
            } else {
                long endPos = diagnostic.getEndPosition();
                long startPos = diagnostic.getStartPosition();
                if(endPos >= 0 && startPos >= 0) {
                    String source = diagnostic.getSource().getName().replace('/', File.separatorChar);
                    source = source.substring(1, source.length() - 5) + ".sandwood";

                    Location loc = tokenMapping.getLocation(startPos, endPos);

                    sb.append("-----\n\nError in " + source + ": " + loc + "\n");
                    sb.append(tokenMapping.getSource(loc) + "\n");
                } else {
                    sb.append("-----\n\nCompiler Error.\n");
                }
                sb.append(message + "\n\n");
            }
        }
        int numErrors = diagnosticList.size() - skippedErrors;
        if(numErrors != 0)
            sb.append("-----\n\n" + numErrors + ((numErrors == 1) ? " Error" : " Errors") + " found.\n");

        return sb.toString();
    }

    public static List<SandwoodCompileError> constructErrorsSandwood(DiagnosticCollector<JavaFileObject> diagnostics,
            Map<String, TokenMapping> mapping) {
        List<SandwoodCompileError> errors = new ArrayList<>();
        Location loc;
        String file;
        String source;
        for(Diagnostic<? extends JavaFileObject> diagnostic:diagnostics.getDiagnostics()) {
            JavaFileObject sourceObject = diagnostic.getSource();

            if(sourceObject != null) { // This is our code that has the error, print the location
                long endPos = diagnostic.getEndPosition();
                long startPos = diagnostic.getStartPosition();
                file = sourceObject.getName().replace('/', File.separatorChar);
                file = file.substring(1, file.length() - 5) + ".sandwood";

                TokenMapping tokenMapping = mapping.get(file);
                if(tokenMapping == null)
                    throw new CompilerException("Unable to find token mapping for \"" + file
                            + "\". Available files are " + mapping.keySet());
                loc = tokenMapping.getLocation(startPos, endPos);

                source = tokenMapping.getSource(loc);
            } else { // It is a general error.
                loc = null;
                file = null;
                source = null;
            }

            String message = diagnostic.getMessage(null);
            if(javaFunctionUsage(message))
                message = "Java functions cannot be called from Sandwood models";
            else
                message = correctSandwoodTypes(message);

            if(!message.trim().equals("")) {
                errors.add(new SandwoodCompileError(file, loc, source, message));
            }
        }
        return errors;
    }

    public static List<SandwoodCompileError> constructErrorsSandwood(String file,
            List<SandwoodModelException> exceptions, Map<String, TokenMapping> mapping) {
        List<SandwoodCompileError> errors = new ArrayList<>();

        Location loc;
        String source;
        for(SandwoodModelException e:exceptions) {
            if(e.loc != null) { // This is our code that has the error, print the location
                TokenMapping tokenMapping = mapping.get(file);
                loc = e.loc;
                source = tokenMapping.getSource(loc);
            } else { // It is a general error.
                loc = null;
                source = null;
            }

            errors.add(new SandwoodCompileError(file, loc, source, e.getMessage()));
        }
        return errors;
    }

    public static List<SandwoodCompileError> constructErrorsSandwood(String file, SandwoodModelException e,
            List<String> modelPath) throws IOException {
        List<SandwoodCompileError> errors = new ArrayList<>();
        String source = null;
        if(e.loc != null) // Our code has the error, print the location
            source = getSource(file, e.loc, modelPath);

        errors.add(new SandwoodCompileError(file, e.loc, source, e.getMessage()));
        return errors;
    }

    public static List<SandwoodCompileError> constructErrorsSandwood(String file, SandwoodParseException e,
            List<String> modelPath) throws IOException {
        List<SandwoodCompileError> errors = new ArrayList<>();
        String source = null;
        if(e.loc != null) // Our code has the error, print the location
            source = getSource(file, e.loc, modelPath);

        errors.add(new SandwoodCompileError(file, e.loc, source, e.getMessage()));
        return errors;
    }

    public static List<SandwoodCompileError> constructErrorsSandwood(String file, ParseException e,
            List<String> modelPath) throws IOException {
        List<SandwoodCompileError> errors = new ArrayList<>();
        if(e.currentToken != null) {
            Location loc = new Location(e.currentToken.beginLine, e.currentToken.beginColumn, e.currentToken.endLine,
                    e.currentToken.endColumn);
            errors.add(new SandwoodCompileError(file, loc, getSource(file, loc, modelPath), e.getMessage()));
        } else {
            errors.add(new SandwoodCompileError(file, e.getMessage()));
        }
        return errors;
    }

    public static List<SandwoodCompileWarning> constructWarningsSandwood(String file,
            List<SandwoodModelException> exceptions, Map<String, TokenMapping> mapping) {
        List<SandwoodCompileWarning> warnings = new ArrayList<>();

        Location loc;
        String source;
        for(SandwoodModelException e:exceptions) {
            if(e.loc != null) { // This is our code that has the error, print the location
                TokenMapping tokenMapping = mapping.get(file);
                loc = e.loc;
                source = tokenMapping.getSource(loc);
            } else { // It is a general error.
                loc = null;
                source = null;
            }

            warnings.add(new SandwoodCompileWarning(file, loc, source, e.getMessage()));
        }
        return warnings;
    }

    private static String getSource(String filename, Location loc, List<String> modelPaths) throws IOException {
        File file = new File(filename);
        if(!file.exists()) {
            //If the file does not exist check if it exists in one of the model paths, i.e. it is not the full filename.
            for(String modelPath:modelPaths) {
                file = new File(modelPath.equals("") ? filename : modelPath + File.separator + filename);
                if(file.exists())
                    break;
            }
        }

        int lineNo = 0;
        String source = null;
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line; (line = br.readLine()) != null;) {
                lineNo++;
                if(loc.startLine == lineNo) {
                    source = line;
                    break;
                }
            }
            for(String line; (line = br.readLine()) != null;) {
                lineNo++;
                if(lineNo <= loc.endLine) {
                    source = source + "\n" + line;
                } else
                    break;
            }
        }
        return source;
    }

    public static String parseErrors(List<SandwoodCompileError> errors) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<SandwoodCompileError> p = new PriorityQueue<>(errors);
        while(!p.isEmpty()) {
            sb.append("-----\n\n");
            SandwoodCompileError e = p.poll();
            sb.append(e + "\n");
        }

        int numErrors = errors.size();
        if(numErrors != 0)
            sb.append("-----\n\n" + numErrors + ((numErrors == 1) ? " Error" : " Errors") + " found.\n");
        return sb.toString();
    }

    public static String parseWarnings(List<SandwoodCompileWarning> warnings) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<SandwoodCompileWarning> p = new PriorityQueue<>(warnings);
        while(!p.isEmpty()) {
            sb.append("-----\n\n");
            SandwoodCompileWarning w = p.poll();
            sb.append(w + "\n");
        }

        int numWarnings = warnings.size();
        if(numWarnings != 0)
            sb.append("-----\n\n" + numWarnings + ((numWarnings == 1) ? " Warning" : " Warnings") + " found.\n");
        return sb.toString();
    }

    private static boolean javaFunctionUsage(String message) {
        String[] types = { "double", "int", "float", "byte", "boolean", "long", "char", "short" };
        for(String type:types) {
            if(containsJavaType(message, type))
                return true;
        }
        return false;
    }

    private static boolean containsJavaType(String message, String type) {
        return message.contains(" " + type + " ") || message.contains(" " + type + "\n")
                || message.contains(" " + type + "[") || message.endsWith(type);
    }

    private static String correctSandwoodTypes(String message) {

        /*
         * Split the message on the entry into arrays so we can track when the string is in a generic. This is done to
         * prevent > symbols in guards etc being replace erroneously.
         */
        message = message.replace("org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable<", "$");
        String[] messageParts = message.split("\\$");

        StringBuilder sb = new StringBuilder();
        sb.append(messageParts[0]);
        int leftBraces = 1;
        for(int i = 1; i < messageParts.length; i++) {
            String[] closeParts = messageParts[i].split(">", leftBraces + 1);
            sb.append(closeParts[0]);
            for(int j = 1; j < closeParts.length; j++) {
                sb.append("[]" + closeParts[j]);
                leftBraces--;
            }
            leftBraces++;
        }
        message = sb.toString();

        message = message.replace("org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable", "int");
        message = message.replace("org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable",
                "double");
        message = message.replace("org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable",
                "boolean");

        message = message.replace(",org.sandwood.compiler.srcTools.sourceToSource.Location", "");
        message = message.replace("org.sandwood.compiler.dataflowGraph.variables.Variable.", "");
        message = message.replace("org.sandwood.compiler.dataflowGraph.variables.randomVariables.", "");

        /*
         * Remove message from the Java compiler that does not apply to the Sandwood compiler. This is compiler
         * specific, so will not work on all occasions, but is better than nothing.
         */
        message = message
                .replace("Some messages have been simplified; recompile with -Xdiags:verbose to get full output", "");
        return message;
    }
}
