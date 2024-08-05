/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.exceptions.CompilerInputException;

public class CompilationOptions {

    private boolean fullInferenceRequired = true;
    private String targetDirectory = ".";
    private boolean verbose = false;
    private boolean toJar = false;
    private boolean toJavadoc = false;
    private String javadocDir = null;
    private int release = -1;
    private int source = -1;
    private int target = -1;
    private boolean noCompile = false;
    private final List<String> modelPath = new ArrayList<>();
    private boolean toSource = false;
    private String sourceDestination = null;
    private boolean optimise = true;
    private boolean calculateIndividualProbabilities = false;
    private String[] modelFiles = null;

    public CompilationOptions() {
        modelPath.add("");
    }

    public void apply(CompilationContext compilationContext) {
        compilationContext.setOptimisation(optimise);
        compilationContext.fullInferenceRequired(fullInferenceRequired);
        compilationContext.calculateIndividualProbabilities(calculateIndividualProbabilities);
    }

    public void fullInferenceRequired(boolean fullInferenceRequired) {
        this.fullInferenceRequired = fullInferenceRequired;
    }

    public String[] modelFiles() {
        return modelFiles;
    }

    public void setModelFile(String modelFile) {
        String[] modelFiles = new String[1];
        modelFiles[0] = modelFile;
        setModelFiles(modelFiles);
    }

    public void setModelFiles(String[] modelFiles) {
        for(int i = 0; i < modelFiles.length; i++)
            for(int j = i + 1; j < modelFiles.length; j++)
                if(modelFiles[i].equals(modelFiles[j]))
                    throw new CompilerInputException(
                            "File \"" + modelFiles[i] + "\" " + "appears twice in the list of file arguments.");
        this.modelFiles = modelFiles;
    }

    public String targetDirectory() {
        return targetDirectory;
    }

    public void setTargetDirectory(String targetDirectory) {
        this.targetDirectory = targetDirectory;
    }

    public boolean verbose() {
        return verbose;
    }

    public void setVerbose() {
        verbose = true;
    }

    public boolean toJar() {
        return toJar;
    }

    public void setToJar() {
        toJar = true;
    }

    public boolean toJavadoc() {
        return toJavadoc;
    }

    public void setToJavadoc() {
        toJavadoc = true;
    }

    public String javadocDir() {
        return javadocDir;
    }

    public void setJavadocDir(String javadocDir) {
        this.javadocDir = javadocDir;
    }

    public boolean releaseSet() {
        return release != -1;
    }

    public void setRelease(int release) {
        this.release = release;
    }

    public int release() {
        return release;
    }

    public boolean sourceSet() {
        return source != -1;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int source() {
        return source;
    }

    public boolean targetSet() {
        return target != -1;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int target() {
        return target;
    }

    public boolean noCompile() {
        return noCompile;
    }

    public void setNoCompile() {
        noCompile = true;
    }

    public List<String> modelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath.clear();
        Map<String, String> existingDirs = new HashMap<>();
        for(String dir:modelPath.split(File.pathSeparator)) {
            File dirFile = new File(dir);
            if(!dirFile.exists())
                throw new CompilerInputException("Source location \"" + dir + "\" does not exist.");

            if(!dirFile.isDirectory())
                throw new CompilerInputException("Source location \"" + dir + "\" is not a directory.");

            String absoluteDir = dirFile.getAbsolutePath();
            for(String existing:existingDirs.keySet()) {
                if(existing.startsWith(absoluteDir))
                    throw new CompilerInputException("Model path element \"" + existingDirs.get(existing)
                            + "\" is a subdirectory of model path element \"" + dir + "\".");
                if(absoluteDir.startsWith(existing))
                    throw new CompilerInputException("Model path element \"" + dir
                            + "\" is a subdirectory of model path element \"" + existingDirs.get(existing) + "\".");
            }
            existingDirs.put(absoluteDir, dir);
            this.modelPath.add(dir);
        }
    }

    public boolean toSource() {
        return toSource;
    }

    public void setToSource() {
        toSource = true;
    }

    public String sourceDestination() {
        return sourceDestination;
    }

    public void setSourceDestination(String sourceDestination) {
        this.sourceDestination = sourceDestination;
    }

    public void disableOptimisation() {
        optimise = false;
    }

    public void enableOptimisation() {
        optimise = true;
    }

    public boolean optimisationEnabled() {
        return optimise;
    }

    public void setCalculateIndividualProbabilities() {
        calculateIndividualProbabilities = true;
    }
}
