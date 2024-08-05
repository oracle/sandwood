/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.compiler.compilation.util.CompileUtils;
import org.sandwood.compiler.exceptions.CompilerInputException;

/**
 * A class containing methods to allow Sandwood models to be compiled using string arguments similar to those used by
 * javac.
 */
public class SandwoodC {
    private static class ArgParser {

        private interface ApplyOption {
            void apply(String[] args, int i, CompilationOptions opts);
        }

        private static class OptionEntry {
            public final String[] optionNames;
            public final String argDesc;
            public final String errorMessage;
            public final String helpMessage;
            public final ApplyOption applyOption;

            public OptionEntry(String[] optionNames, String argDesc, String errorMessage, String helpMessage,
                    ApplyOption applyOption) {
                this.optionNames = optionNames;
                this.argDesc = argDesc;
                this.errorMessage = errorMessage;
                this.helpMessage = helpMessage;
                this.applyOption = applyOption;
            }
        }

        private final static Map<String, OptionEntry> options = new LinkedHashMap<>();

        static {
            addOption("-d", "<targetDir>", "Missing directory for option -d.",
                    "Set the target directory for the compiled files.",
                    (args, i, opts) -> opts.setTargetDirectory(trim(args[i++])));
            addOption("--calculateIndividualProbabilities",
                    "Calculate the probabilities for each random variable constructed in a loop instead of a single "
                            + "value for all instances.",
                    (args, i, opts) -> opts.setCalculateIndividualProbabilities());
            addOption("--jar", "Construct a Jar file to hold the resulting model.", (args, i, opts) -> opts.setToJar());
            addOption("--javadoc", "Construct Javadoc files for the resulting model.",
                    (args, i, opts) -> opts.setToJavadoc());
            addOption("--javadocDir", "<dir>", "Missing directory for option --javaDoc.",
                    "Set the location to output java docs to. If --jar is set the output will be a jar file. Setting "
                            + "this flag implicitly sets --javadoc.",
                    (args, i, opts) -> {
                        opts.setToJavadoc();
                        opts.setJavadocDir(trim(args[i++]));
                    });
            addOption("--partialInferenceWarning",
                    "Only warn if an inference step cannot be added. This can be used to complete compilation when the "
                            + "model will be used for evidence generation not inference.",
                    (args, i, opts) -> opts.fullInferenceRequired(false));
            addOption("--partialInferenceError", "Fail to compile if an inference step is missing.",
                    (args, i, opts) -> opts.fullInferenceRequired(true));
            addOption("--release", "<release>", "Missing release value for option --release.",
                    "Set the Java release that the byte-code should be compiled against.",
                    (args, i, opts) -> opts.setRelease(Integer.valueOf(args[i++])));
            addOption("--source", "<source>", "Missing source value for option --source.",
                    "Set the Java release the source code must adhere to.",
                    (args, i, opts) -> opts.setSource(Integer.valueOf(args[i++])));
            addOption("--sourceDestination", "<sourceDir>", "Missing directory for option --sourceDestination.",
                    "Set the target directory for the source files. If source is generated and this is not set it will "
                            + "be placed in the directory of the constructing model, or if jars are being constructed in the "
                            + "working directory.",
                    (args, i, opts) -> {
                        opts.setToSource();
                        opts.setSourceDestination(trim(args[i++]));
                    });
            addOption("--sourcepath", "<path>", "Missing directory for option --sourcepath.",
                    "The path to the directories that the model source code is located in.",
                    (args, i, opts) -> opts.setModelPath(trim(args[i++])));
            addOption("--target", "<target>", "Missing target value for option --target.",
                    "Set the Java release the compiled classes should target.",
                    (args, i, opts) -> opts.setTarget(Integer.valueOf(args[i++])));
            addOption("--toSource", "Output the source code for the generated models.",
                    (args, i, opts) -> opts.setToSource());
            addOption("--toSourceOnly", "Output just the source code for the generated models.", (args, i, opts) -> {
                opts.setToSource();
                opts.setNoCompile();
            });
            addOption("-v", "Print verbose details of the compilation process.", (args, i, opts) -> opts.setVerbose());
            String[] optionNames = { "-h", "--help" };
            addOption(optionNames, "Print this message.", (args, i, opts) -> {
                throw new SandwoodException("");
            });
        }

        private static void addOption(String optionName, String helpMessage, ApplyOption applyOption) {
            String[] optionNames = new String[1];
            optionNames[0] = optionName;
            addOption(optionNames, null, null, helpMessage, applyOption);
        }

        private static void addOption(String[] optionNames, String helpMessage, ApplyOption applyOption) {
            OptionEntry o = new OptionEntry(optionNames, null, null, helpMessage, applyOption);
            for(String name:optionNames)
                options.put(name, o);
        }

        private static void addOption(String optionName, String argDesc, String errorMessage, String helpMessage,
                ApplyOption applyOption) {
            String[] optionNames = new String[1];
            optionNames[0] = optionName;
            addOption(optionNames, argDesc, errorMessage, helpMessage, applyOption);
        }

        private static void addOption(String[] optionNames, String argDesc, String errorMessage, String helpMessage,
                ApplyOption applyOption) {
            OptionEntry o = new OptionEntry(optionNames, argDesc, errorMessage, helpMessage, applyOption);
            for(String name:optionNames)
                options.put(name, o);
        }

        private static CompilationOptions parseArgs(String[] args) {
            int i = 0;
            int argCount = args.length;
            CompilationOptions opts = new CompilationOptions();

            // Get the options
            while(i < argCount && args[i].startsWith("-"))
                i = processArg(args, i, opts);

            // Get the file names
            String[] filenames = new String[argCount - i];
            if(filenames.length == 0) {
                throw new CompilerInputException("No source files provided.");
            }

            int j = 0;
            while(i < argCount && !args[i].startsWith("-"))
                filenames[j++] = getFilename(args[i++], opts.modelPath());

            if(i != argCount) {
                throw new CompilerInputException("Input files not at the end of the options string");
            }

            opts.setModelFiles(filenames);
            return opts;
        }

        private static int processArg(String[] args, int i, CompilationOptions opts) {
            String arg = args[i++];
            OptionEntry o = options.get(arg);
            if(o == null)
                throw new CompilerInputException("Failed to understand argument: " + args[i - 1]);
            if(o.argDesc == null)
                o.applyOption.apply(args, i, opts);
            else if(i < args.length && !args[i].startsWith("-"))
                o.applyOption.apply(args, i++, opts);
            else
                throw new CompilerInputException(o.errorMessage);
            return i;
        }

        private static String trim(String s) {
            // Tidy filenames in case a tool calling the api has included quotes or white
            // space.
            s = s.trim();
            if(s.startsWith("\"") && s.endsWith("\""))
                s = s.substring(1, s.length() - 1);
            return s;
        }

        private static void help() {
            final int consoleWidth = 100;
            int optionWidth = 0;

            // Find how many characters are needed before descriptions can be printed.
            for(OptionEntry o:options.values()) {
                int w = 0;
                for(String s:o.optionNames)
                    w += s.length() + 1;
                if(o.argDesc != null)
                    w += o.argDesc.length() + 1;
                if(w > optionWidth)
                    optionWidth = w;
            }

            // Build the help message
            StringBuilder sb = new StringBuilder();
            sb.append("SandwoodC [options] (inputFiles)+\n\n");
            sb.append("Options:");
            Set<OptionEntry> seen = new HashSet<>();
            for(OptionEntry o:options.values()) {
                if(!seen.contains(o)) {
                    seen.add(o);

                    sb.append("\n");

                    // Add the parameter and its options
                    int l = 0;
                    for(String s:o.optionNames) {
                        l += s.length() + 1;
                        sb.append(s + " ");
                    }

                    if(o.argDesc != null) {
                        l += o.argDesc.length() + 1;
                        sb.append(" " + o.argDesc);
                    }

                    // Add any white space required.
                    while(l < optionWidth) {
                        sb.append(" ");
                        l++;
                    }

                    // Split into words
                    String[] parts = o.helpMessage.split(" ");

                    // Add the first word.
                    if(parts.length != 0) {
                        String part = parts[0];
                        sb.append(part);
                        l += part.length();
                    }

                    // Add the remaining words
                    int i = 1;
                    while(i < parts.length) {
                        String part = parts[i++];
                        int pLength = part.length();
                        // If the word fits on this line. This will always be after a word has been
                        // applied.
                        if(l + pLength < consoleWidth) {
                            // Add 1 word;
                            sb.append(" " + part);
                            l += pLength + 1;
                        } else {
                            // Insert a newline and add the word to that.
                            sb.append("\n");
                            l = 0;

                            // Add any white space required.
                            while(l < optionWidth) {
                                sb.append(" ");
                                l++;
                            }

                            // Add the word
                            sb.append(part);
                            l += pLength;
                        }
                    }
                    sb.append("\n");
                }
            }

            // Print the help message
            System.out.println(sb);
        }
    }

    /**
     * A main method to allow this class to be called from the command line.
     * 
     * @param args The arguments that the class should be called with. Calling the class with no arguments will result
     *             in a printout of all the available arguments.
     *             <p>
     *             -d &lt;targetDir &gt; Set the target directory for the compiled files.
     *             <p>
     *             --calculateIndividualProbabilities Calculate the probabilities for each random variable constructed
     *             in a loop instead of a single value for all instances.
     *             <p>
     *             --javadoc Construct Javadoc files for the resulting model.
     *             <p>
     *             --javadocDir &lt; dir &gt; Set the location to output java docs to. If --jar is set the output will
     *             be a jar file. Setting this flag implicitly sets --javadoc.
     *             <p>
     *             --runtimeSource &lt; dir &gt; Set the location of the jar file containing the runtime source. This
     *             option should not be used unless you are calling the compiler directly. If your using the batch or
     *             shell scripts to run SandwoodC it is already set.
     *             <p>
     *             --partialInferenceWarning Only warn if an inference step cannot be added. This can be used to
     *             complete compilation when the model will be used for evidence generation not inference.
     *             <p>
     *             --partialInferenceError Fail to compile if an inference step is missing.
     *             <p>
     *             --release &lt; release &gt; Set the release that the byte-code should be compiled against.
     *             <p>
     *             --source &lt; source &gt; "Set the release the source code must adhere to.
     *             <p>
     *             "--sourceDestination &lt; sourceDir &gt; Set the target directory for the source files. If source is
     *             generated and this is not set it will be placed in the directory of the constructing model, or if
     *             jars are being constructed in the working directory.
     *             <p>
     *             --sourcepath &lt; path &gt; "The path to the directories that the model source code is located in.
     *             <p>
     *             --target &lt; target &gt; Set the release the compiled classes should target.
     *             <p>
     *             --toSource Output the source code for the generated models.
     *             <p>
     *             --toSourceOnly Output just the source code for the generated models.
     *             <p>
     *             -v Print verbose details of the compilation process.
     *             <p>
     *             -h --help Print the help message.
     */
    public static void main(String[] args) {
        if(compile(args))
            System.exit(0);
        else
            System.exit(1);
    }

    /**
     * The method to call to invoke the compiler with the command line style arguments.
     * 
     * @param args The arguments that the class should be called with. Calling the class with no arguments will result
     *             in a printout of all the available arguments.
     *             <p>
     *             -d &lt;targetDir &gt; Set the target directory for the compiled files.
     *             <p>
     *             --calculateIndividualProbabilities Calculate the probabilities for each random variable constructed
     *             in a loop instead of a single value for all instances.
     *             <p>
     *             --javadoc Construct Javadoc files for the resulting model.
     *             <p>
     *             --javadocDir &lt; dir &gt; Set the location to output java docs to. If --jar is set the output will
     *             be a jar file. Setting this flag implicitly sets --javadoc.
     *             <p>
     *             --runtimeSource &lt; dir &gt; Set the location of the jar file containing the runtime source. This
     *             option should not be used unless you are calling the compiler directly. If your using the batch or
     *             shell scripts to run SandwoodC it is already set.
     *             <p>
     *             --partialInferenceWarning Only warn if an inference step cannot be added. This can be used to
     *             complete compilation when the model will be used for evidence generation not inference.
     *             <p>
     *             --partialInferenceError Fail to compile if an inference step is missing.
     *             <p>
     *             --release &lt; release &gt; Set the release that the byte-code should be compiled against.
     *             <p>
     *             --source &lt; source &gt; "Set the release the source code must adhere to.
     *             <p>
     *             "--sourceDestination &lt; sourceDir &gt; Set the target directory for the source files. If source is
     *             generated and this is not set it will be placed in the directory of the constructing model, or if
     *             jars are being constructed in the working directory.
     *             <p>
     *             --sourcepath &lt; path &gt; "The path to the directories that the model source code is located in.
     *             <p>
     *             --target &lt; target &gt; Set the release the compiled classes should target.
     *             <p>
     *             --toSource Output the source code for the generated models.
     *             <p>
     *             --toSourceOnly Output just the source code for the generated models.
     *             <p>
     *             -v Print verbose details of the compilation process.
     *             <p>
     *             -h --help Print the help message.
     * @return A boolean flag reporting if the compilation process executed successfully. If it failed the resulting
     *         errors will have been printed to the console. If this is not the desired behaviour then the compilation
     *         call should instead be made to the class {@link org.sandwood.compiler.SandwoodCompiler}.
     */
    public static boolean compile(String[] args) {
        if(args.length == 0) {
            ArgParser.help();
            return false;
        }

        CompilationOptions opts;
        try {
            opts = ArgParser.parseArgs(args);
        } catch(CompilerInputException e) {
            ArgParser.help();
            System.out.println(e.getMessage());
            return false;
        } catch(SandwoodException e) { // This should only be thrown here if the user requested the
            // help should be output. TODO come up with a better way of handing this.
            ArgParser.help();
            System.out.println(e.getMessage());
            return false;
        }

        try {
            CompilationOutput output = SandwoodCompiler.compileModel(opts);
            System.out.println(CompileUtils.parseWarnings(output.getWarnings()));
            if(output.noErrors())
                return true;
            else {
                System.out.println(CompileUtils.parseErrors(output.getErrors()));
                return false;
            }
        } catch(NoSuchFileException e) {
            // This should not be reachable as the files are tested before they are passed
            // to the compiler.
            System.out.println("Unable to find file: " + e.getMessage());
            return false;
        } catch(IOException | SandwoodException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private static String getFilename(String name, List<String> modelPath) {
        // Convert the name to a file name.
        String processedName;
        if(name.endsWith(".sandwood"))
            processedName = name.trim();
        else
            processedName = name.trim().replace(".", File.separator) + ".sandwood";

        for(String dir:modelPath) {
            // Construct a file for each model path directory.
            String fullFilePath = dir.equals("") ? processedName : dir + File.separator + processedName;
            File f = new File(fullFilePath);
            if(f.exists() && f.isFile())
                return fullFilePath;
        }

        throw new CompilerInputException("Unable to find file: " + name);
    }
}
