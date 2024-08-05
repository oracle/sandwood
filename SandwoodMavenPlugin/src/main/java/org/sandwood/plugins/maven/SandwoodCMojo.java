/*
 * Sandwood
 *
 * Copyright (c) 2019, 2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.plugins.maven;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.artifact.handler.DefaultArtifactHandler;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectBuilder;
import org.apache.maven.project.ProjectBuildingRequest;
import org.apache.maven.shared.artifact.filter.resolve.ExclusionsFilter;
import org.apache.maven.shared.transfer.artifact.resolve.ArtifactResult;
import org.apache.maven.shared.transfer.dependencies.resolve.DependencyResolver;
import org.codehaus.plexus.util.DirectoryScanner;
import org.codehaus.plexus.util.FileUtils;

/**
 * A class implementing a Maven plugin to call the Sandwood compiler on
 * Sandwood models in the specified source directory.
 */
@Mojo(name = "sandwoodc", defaultPhase = LifecyclePhase.GENERATE_SOURCES, requiresDependencyResolution = ResolutionScope.TEST)
public class SandwoodCMojo extends AbstractMojo {
    /*
     * Parameters and components used to resolve the dependencies and the class path
     * that should be constructed.
     */
    private static final String SANDWOOD_GROUP_ID = "org.sandwood";
    private static final String COMPILER_ARTIFACT_ID = "sandwood-compiler";
    private static final String RUNTIME_ARTIFACT_ID = "sandwood-runtime";

    @Parameter(defaultValue = "${session}", readonly = true, required = true)
    private MavenSession session;

    @Parameter(defaultValue = "${project.remoteArtifactRepositories}", readonly = true, required = true)
    private List<ArtifactRepository> remoteRepositories;

    @Parameter(defaultValue = "${localRepository}", readonly = true, required = true)
    private ArtifactRepository localRepository;

    @Component
    private ProjectBuilder projectBuilder;

    @Component
    private DependencyResolver dependencyResolver;

    /**
     * Project property, set so that the project can be extended with the output of
     * the Sandwood compiler.
     */
    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

    /**
     * The class to execute the main method of in order to run the Sandwood
     * compiler.
     */
    private static final String compilerClass = "org.sandwood.compiler.SandwoodC";

    /**
     * The set of file extensions to look for when looking for Sandwood models.
     */
    private static final String[] includes = { "**/*.sandwood", "**/*.SANDWOOD" };

    /**
     * This option is used to obtain debugging information from SandwoodC. Setting
     * this option to <code>true</code> causes Sandwood to generate a trace of its
     * actions. Default value is <code>false</code>.
     */
    @Parameter
    private Boolean debug;

    /**
     * This option is used to stop SandwoodC from failing when some inference steps
     * cannot be constructed. Setting this option to <code>true</code> causes
     * Sandwood to just generate warnings on missing steps. Default value is
     * <code>false</code>.
     */
    @Parameter
    private Boolean partialInferenceWarning;

    /**
     * Parameter setting where to look for Sandwood models
     */
    @Parameter(defaultValue = "${basedir}/src/main/sandwood")
    private File sourceDirectory;

    /**
     * A setter method that takes a path as a String and turns it into the required
     * file.
     *
     * @param fileName
     */
    public void setSourceDirectory(String fileName) {
        sourceDirectory = new File(fileName);
    }

    /**
     * Parameter setting where to place compiled Sandwood models.
     */
    @Parameter(defaultValue = "${project.build.directory}/generated-sources/sandwood")
    private File outputDirectory;

    /**
     * A setter method that takes a path as a String and turns it into the required
     * file.
     *
     * @param fileName
     */
    public void setOutputDirectory(String fileName) {
        outputDirectory = new File(fileName);
    }

    /**
     * Parameter to hold the JVM executable.
     */
    @Parameter
    private String executable;
    
    /**
     * This option specifies if the probabilities for each random variable constructed 
     * in a loop should be calculated instead of a single value for all instances.
     */
    @Parameter
    private Boolean calculateIndividualProbabilities;
    
    /**
     * This option instructs the plugin to construct JavaDoc for the models that are 
     * compiled.
     */
    @Parameter
    private Boolean javadoc;
    
    /**
     * This option tells the plugin where to place JavaDoc files for the model.
     */
    @Parameter(defaultValue = "${project.build.directory}/javaDoc/sandwood")
    private File javadocDirectory;
    
    /**
     * A setter method that takes a path as a String and turns it into the required
     * file.
     * @param fileName
     */
    public void setJavadocDirectory(String fileName) {
        javadocDirectory = new File(fileName);
    }
    
    /**
     * Parameter to set the Java release the source code must adhere to.
     */
    private Integer sourceVersion;
    
    /**
     * A parameter to allow a maximum compile time to be set, after this time the
     * process is killed. Setting the value of this parameter to 0 allows unlimited
     * compile times. The default value is 0.
     */
    @Parameter(defaultValue = "0")
    private int timeout;

    /**
     * Default constructor for injection.
     */
    public SandwoodCMojo() {}

    /**
     * The main method for the plugin. Searches for models and compiles to Java code
     * any models found.
     */
    @Override
    public void execute() throws MojoExecutionException {
        getLog().info("Running SandwoodC");

        // Ensure the output directory is set.
        if(outputDirectory == null) {
            getLog().error("No output directory set.");
            return;
        }

        // Ensure target directory is constructed.
        if(!outputDirectory.exists()) {
            getLog().info("Constructing output directory " + outputDirectory);
            outputDirectory.mkdirs();
        }
        getLog().info("Placing generated model code into " + outputDirectory);

        // Scan for and build any found models.
        List<String> models = scanForModels();
        if(models == null)
            return;
        else if(models.isEmpty())
            getLog().info("No models found to compile.");
        else {
            // Build the models
            // TODO restructure this so that the files are compiled together, otherwise
            // models split across multiple models will not work.
            String[] commandLine = getCommandLine();

            for(String file:models)
                processModel(file, commandLine);

            getLog().info("Processed " + models.size() + " models");
        }

        // Add the output directory to the set of folders the compiler should build.
        if(project != null) {
            getLog().debug("Adding directory as source root: " + outputDirectory);
            project.addCompileSourceRoot(outputDirectory.getAbsolutePath());
        }

        getLog().info("Finished SandwoodC");
    }

    /**
     * Method to scan the source directory for any Sandwood models.
     *
     * @return A list of Strings each representing a Sandwood model.
     */
    private List<String> scanForModels() {
        // Ensure the source directory is set.
        if(sourceDirectory == null) {
            getLog().error("Source directory is not set.");
            return null;
        }

        // Check the directory exists.
        if(!sourceDirectory.isDirectory()) {
            getLog().error("Source directory does not exist: " + sourceDirectory);
            return null;
        }

        // Setup and run a scanner.
        getLog().info("Scanning for sandwood models: " + sourceDirectory);
        DirectoryScanner scanner = new DirectoryScanner();
        scanner.setFollowSymlinks(true);
        scanner.addDefaultExcludes();
        scanner.setIncludes(includes);
        scanner.setBasedir(sourceDirectory);
        scanner.scan();
        String[] includedFileNames = scanner.getIncludedFiles();

        // Filter the results to remove models that where last build more recently than
        // the source file was updated.
        List<String> models = new ArrayList<>();
        for(String includedFile:includedFileNames) {
            File sourceFile = new File(sourceDirectory, includedFile);
            File targetFile = new File(outputDirectory, FileUtils.removeExtension(includedFile));
            if(targetFile.exists() && targetFile.lastModified() > sourceFile.lastModified())
                getLog().info("Skipping model " + sourceFile.getPath()
                        + " model was built since the source file was last modified.");
            else
                models.add(includedFile);
        }

        getLog().info("Found " + models.size() + " sandwood models that need compling");

        return models;
    }

    /**
     * Method to construct the command line that will execute the compiler. The last
     * element in the array is not set as this will hold the file name in each
     * execution.
     *
     * @return An array holding the elements of the command line.
     * @throws MojoExecutionException
     */
    private String[] getCommandLine() throws MojoExecutionException {
        String classpath = constructClasspath();
        String executablePath = getExecutable();

        List<String> commandLine = new ArrayList<>();

        commandLine.add(executablePath);
        commandLine.add("-classpath");
        commandLine.add(classpath);
        commandLine.add(compilerClass);

        if(debug != null && debug == true) {
            getLog().info("Sandwood debugging enabled.");
            commandLine.add("-v");
        }

        if(partialInferenceWarning != null && partialInferenceWarning == true) {
            getLog().info("Partial inference allowed in the Sandwood compiler.");
            commandLine.add("--partialInferenceWarning");
        }
        
        if(calculateIndividualProbabilities != null &&
                calculateIndividualProbabilities == true)
            commandLine.add("--calculateIndividualProbabilities");

        // Set the source path
        commandLine.add("--sourcepath");
        commandLine.add(sourceDirectory.getAbsolutePath());

        // Set the location the Java code output by the Sandwood compiler should be
        // placed in.
        commandLine.add("--sourceDestination");
        commandLine.add(outputDirectory.getAbsolutePath());

        // Tell the compiler to stop at source code and not to compile the models to
        // byte code.
        // This later step will be carried out by the Maven compiler.
        commandLine.add("--toSourceOnly");
        
        // Tell the compiler which version of Java the source should target.
        if(sourceVersion != null) {
            commandLine.add("--source");
            commandLine.add(sourceVersion.toString());
        }

        //Tell the compiler if it should generate javadoc
        if(javadoc != null && javadoc == true) {
            commandLine.add("--javadocDir");
            commandLine.add(javadocDirectory.getAbsolutePath());
        }

        // Array has a spare element at the end to take the file name.
        return commandLine.toArray(new String[commandLine.size() + 1]);
    }

    // Method to get the JVM to use.
    private String getExecutable() {
        if(executable == null)
            executable = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        getLog().info("Using executable: " + executable);
        return executable;
    }

    /**
     * Method to compile a Sandwood model.
     *
     * @param sourceFile  A source file containing the model to compile.
     * @param commandLine The command line to use to compile the model. The final
     *                    cell in the array must be empty to provide a space to
     *                    write the file name.
     * @throws MojoExecutionException
     */
    private void processModel(String sourceFile, String[] commandLine) throws MojoExecutionException {
        // Set the file name to execute.
        getLog().info("Processing: " + sourceFile);
        commandLine[commandLine.length - 1] = sourceFile;
        getLog().info("Executing command line: " + printCommand(commandLine));

        try {
            // Run the command
            Process p = Runtime.getRuntime().exec(commandLine);

            if(timeout > 0) {
                // Wait to complete. If the process times out, stop the process and throw an exception.
                if(!p.waitFor(timeout, TimeUnit.SECONDS)) {
                    String output = getOutput(p.getInputStream());
                    String error = getOutput(p.getErrorStream());
                    p.destroy();
                    String combined = "";
                    if(!output.isEmpty()) {
                        getLog().info(output);
                        combined = output + "\n";
                    }
                    if(!error.isEmpty()) {
                        getLog().error(error);
                        combined = combined + error + "\n";
                    }
                    String message = "Timeout. Process ran longer than the time out of " + timeout + "s.\n";
                    getLog().error(message);
                    throw new MojoExecutionException(combined + message);
                }
            } else
                // If no time out is set just wait.
                p.waitFor();
            // Check the exit code.
            int exitCode = p.exitValue();
            if(exitCode != 0) {
                String output = getOutput(p.getInputStream());
                String error = getOutput(p.getErrorStream());
                String combined = "";
                if(!output.isEmpty()) {
                    getLog().info(output);
                    combined = output + "\n";
                }
                if(!error.isEmpty()) {
                    getLog().error(error);
                    combined = combined + error + "\n";
                }
                String message = "Command finished with non zero exit code: '" + exitCode + "'.";
                getLog().error(message);
                throw new MojoExecutionException(combined + message);
            }

            String output = getOutput(p.getInputStream());
            String error = getOutput(p.getErrorStream());
            if(!output.isEmpty())
                getLog().info(output);

            if(!error.isEmpty())
                getLog().error(error);
        }

        catch(IOException | InterruptedException e) {
            getLog().error("Execution failed.", e);
            throw new MojoExecutionException("Execution failed.", e);
        }
    }

    public String getOutput(InputStream stream) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        try {
            while((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private String printCommand(String[] commandLine) {
        StringBuilder sb = new StringBuilder();
        int length = commandLine.length;
        for(int i = 0; i < length; i++) {
            if(i != 0)
                sb.append(" ");
            sb.append(commandLine[i]);
        }
        return sb.toString();
    }

    /**
     * Method to get the required class path for the command.
     *
     * @return A string representing the class path.
     * @throws MojoExecutionException
     */
    private String constructClasspath() throws MojoExecutionException {
        StringBuilder stringBuilder = new StringBuilder();
        boolean first = true;
        // For each file the plugin depends on concatenate it into a list.
        for(Artifact artifact:determinePluginDependencies()) {
            getLog().debug("Adding plugin dependency artifact: " + artifact.getArtifactId() + " to classpath");
            if(first)
                first = false;
            else
                stringBuilder.append(File.pathSeparator);
            stringBuilder.append(artifact.getFile().getPath());
        }

        return stringBuilder.toString();
    }

    /**
     * Determine the Artifacts that this plugin depends on.
     *
     * @return Set of all the artifacts that the compiler depends on.
     * @throws MojoExecutionException
     */
    private Set<Artifact> determinePluginDependencies() throws MojoExecutionException {
        Artifact runtimeArtifact = null;
        // Search the plugin dependencies to find the compiler artifact.
        for(Artifact artifact:project.getArtifacts()) {
            if(artifact.getGroupId().equals(SANDWOOD_GROUP_ID)
                    && artifact.getArtifactId().equals(RUNTIME_ARTIFACT_ID)) {
                runtimeArtifact = artifact;
                break;
            }
        }

        if(runtimeArtifact == null) {
            // If we didn't find the runtime artifact in the project's pom.
            throw new MojoExecutionException("Failed to find Sandwood runtime artifact, expected to find: "
                    + SANDWOOD_GROUP_ID + ":" + COMPILER_ARTIFACT_ID);
        }

        getLog().debug("Constructing dependencies from Sandwood runtime " + runtimeArtifact);

        Artifact compilerArtifact = new DefaultArtifact(SANDWOOD_GROUP_ID, COMPILER_ARTIFACT_ID, 
                runtimeArtifact.getVersion(), "compile", "jar", null, new DefaultArtifactHandler("jar"));

        // Resolve the executable dependencies for the specified project
        Set<Artifact> executableDependencies = new LinkedHashSet<>();
        try {
            ProjectBuildingRequest buildingRequest = session.getProjectBuildingRequest();
            buildingRequest.setRemoteRepositories(remoteRepositories);
            buildingRequest.setLocalRepository(localRepository);

            MavenProject executableProject = projectBuilder.build(compilerArtifact, true, buildingRequest)
                    .getProject();

            ExclusionsFilter filter = new ExclusionsFilter(new ArrayList<>());
            for(ArtifactResult artifactResult:dependencyResolver.resolveDependencies(buildingRequest,
                    executableProject.getModel(), filter))
                executableDependencies.add(artifactResult.getArtifact());
        } catch(Exception ex) {
            throw new MojoExecutionException("Encountered problems resolving the dependencies of " + COMPILER_ARTIFACT_ID, ex);
        }
        return executableDependencies;
    }
}
