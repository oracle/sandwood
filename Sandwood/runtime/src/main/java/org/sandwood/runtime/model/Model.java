package org.sandwood.runtime.model;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.sandwood.random.RandomType;
import org.sandwood.runtime.exceptions.SandwoodJsonException;
import org.sandwood.runtime.model.variables.ComputedVariable;
import org.sandwood.runtime.model.variables.HasProbability;

/**
 * An interface for classes that implements the base functionality of a probabilistic model. All compiled models will
 * implement this interface.
 */
public interface Model extends HasProbability, AutoCloseable {

    /**
     * Method to set the target execution backend. This is used to change how the code is executed ranging from single
     * threaded to GPU execution.
     * 
     * @param target The hardware/platform that the execution should target.
     */
    void setExecutionTarget(ExecutionTarget target);

    /**
     * Method to determine what type of execution is currently set to be used
     * 
     * @return The type of execution being used.
     */
    ExecutionTarget getCurrentExecutionTarget();

    // RNG stuff
    /**
     * Method to initialize the seed of the random number generator. If the model has already been used, the threads
     * will have to be reinitialized to propagate these changes.
     * 
     * @param seed The seed for the random number generator.
     */
    void initializeSeed(long seed);

    /**
     * Method to change the type of the random number generator. If the model has already been used, the threads will
     * have to be reinitialized to propagate these changes.
     * 
     * @param type The type of the random number generator.
     */
    void setRNGType(RandomType type);

    /**
     * Method to set both the type and seed of the random number generator. If the model has already been used, the
     * threads will have to be reinitialized to propagate these changes.
     *
     * @param type The type of the Random Number Generator
     * @param seed The seed to initialize the Random Number Generator with.
     */
    void setRNGType(RandomType type, long seed);

    /**
     * A method to test if all the required variables have been set to infer values from the model.
     * 
     * @return A boolean marking if all the required variables have been set.
     */
    boolean readyInferValues();

    /**
     * A method to report any missing values.
     * 
     * @return a String listing the unset values.
     */
    String missingInferValues();

    /**
     * A method to test if all the required variables have been set to execute the model as regular code.
     * 
     * @return A boolean marking if all the required variables have been set.
     */
    boolean readyExecute();

    /**
     * A method to report any missing values.
     * 
     * @return a String listing the unset values.
     */
    String missingExecute();

    /**
     * A method to test if all the required variables have been set for generating the probabilities of the model.
     * 
     * @return A boolean marking if all the required variables have been set.
     */
    boolean readyInferProbabilities();

    /**
     * A method to report any missing values required to infer probabilities.
     * 
     * @return a String listing the unset values.
     */
    String missingInferProbabilities();

    /**
     * Get the set of available techniques for this model.
     * 
     * @return Available techniques.
     */
    Set<InferenceTechnique> availableInferenceTechniques();

    /**
     * Set the retention policy for the variables in the model.
     * 
     * @param p The retention policy to set.
     */
    void setDefaultRetentionPolicy(RetentionPolicy p);

    /**
     * Method to set the level of thinning that this model should use when performing inference on the model. By
     * default, this value is set to 0;
     * 
     * @param thinning The number of runs that should be ignored between each run that takes a sample.
     */
    void setThinning(int thinning);

    /**
     * Method to set the number of steps of burnin that should be used when performing inference on the model. The
     * default value is 0.
     * 
     * @param burnin The number of cycles before starting to collect values.
     */
    void setBurnin(int burnin);

    /**
     * Perform a single pass generating values from the model.
     */
    void execute();

    /**
     * Perform multiple passes over the model generating new values with each pass.
     * 
     * @param iterations The number of iterations to perform.
     */
    void execute(int iterations);

    /**
     * Calculate the parameters of the model based on a fixed set of inputs and outputs.
     * 
     * @param iterations   The number of sampling iterations to perform. The total number of iterations performed will
     *                     be burnin + (1+thinning)*(samples-1) + 1. The values of thinning and burnin are set with
     *                     separate method calls.
     * @param mapVariables This is an optional list of computed variables that will be used to pick which iteration
     *                     should be used to return a mapped value.
     */
    void inferValues(int iterations, ComputedVariable... mapVariables);

    /**
     * Calculate the parameters of the model based on a fixed set of inputs and outputs.
     * 
     * @param iterations   The number of sampling iterations to perform. The total number of iterations performed will
     *                     be burnin + (1+thinning)*samples.
     * @param burnin       The value of burnin for this inference of the model. This is the number of steps that are
     *                     taken before samples start being taken from the model. This value will not change the burnin
     *                     value for the model that is used in later calls to inferModel.
     * @param thinning     The value of thinning for this inference of the model. This is the number of iterations
     *                     between each sample that is taken from the model. This value will not change the thinning
     *                     value for the model that is used in later calls to inferModel.
     * @param mapVariables This is an optional list of computed variables that will be used to pick which iteration
     *                     should be used to return a mapped value.
     */
    void inferValues(int iterations, int burnin, int thinning, ComputedVariable... mapVariables);

    /**
     * Calculate the probability of each variable and the probability of the overall model.
     * 
     * @param iterations The number of iterations to perform when calculating these values.
     */
    void inferProbabilities(int iterations);

    /**
     * Calculate the probability of each variable and the probability of the overall model. This method will iterate
     * until the variance of the overall model drops below the value provide for variance, or the maximum number of
     * iterations is reached.
     * 
     * @param variance          The maximum variance in the models overall probability.
     * @param initialIterations The number of iterations to use to start with. Having too low a value here can result in
     *                          premature termination as the model may not have enough runs to estimate the variance
     *                          accurately.
     * @param maxIterations     The maximum number of iterations that the model can perform to generate the
     *                          probabilities.
     */
    void inferProbabilities(double variance, int initialIterations, int maxIterations);

    /**
     * Calculate the probability of each variable and the probability of the overall model. This method will iterate
     * until the variance of the overall model drops below the value provide for variance is reached.
     *
     * @param variance          The maximum variance in the models overall probability.
     * @param initialIterations The number of iterations to use to start with. Having too low a value here can result in
     *                          premature termination as the model may not have enough runs to estimate the variance
     *                          accurately.
     */
    void inferProbabilities(double variance, int initialIterations);

    /**
     * Method to return the log probability of the model object with the variables as they are currently set. This
     * method DOES NOT integrate over variables whose value is not fixed.
     * 
     * @return The probability of the current state of the model only. Because this does not include integration the
     *         values are NOT constrained to being less than 0.
     */
    double spotLogProbability();

    /**
     * Method to return the probability of the model object with the variables as they are currently set. This method
     * DOES NOT integrate over variables whose value is not fixed.
     * 
     * @return The probability of the current state of the model only. Because this does not include integration the
     *         values are NOT constrained to being less than 1.
     */
    double spotProbability();

    /**
     * Construct a Jar file containing a trained version of the model.
     *
     * @param f File representing the location that the new model should be placed into.
     */
    void constructTrainedModel(File f);

    /**
     * Get the Sandwood code that this model was originally generated from.
     * 
     * @return The code the Sandwood model is generated from.
     */
    String getModelCode();

    /**
     * A method to set all the settable computed values that have a MAP value computed to use that value in future
     * calculations. A MAP value will only be present to set to the variables value if the {@link RetentionPolicy
     * retention policy} for the variable was set to MAP and {@link #inferValues(int, ComputedVariable...) variable
     * inference} was the last inference operation performed on the model. The retention policy can be set to MAP by
     * either setting the MAP policy for the whole model and not overriding the policy for this variable, or setting the
     * policy to MAP specifically for this variable. Retention policies are set via the methods
     * {@link #setDefaultRetentionPolicy(RetentionPolicy) setDefaultRetentionPolicy} for the model and
     * {@link ComputedVariable#setRetentionPolicy(RetentionPolicy) setRetentionPolicy} for the variable.
     * 
     * @return Returns a list of variables that have had their value set to their current MAP value.
     */
    List<ComputedVariable> setToMAPValues();

    /**
     * Saves all the computed values in a model as a JSON file.
     * 
     * @param filename name of the file to save to.
     * @throws IOException Thrown if there is a problem with the filename.
     */

    void exportToJson(String filename) throws IOException;

    /**
     * Saves all the computed values in a model as a JSON file.
     * 
     * @param filename  name of the file to save to.
     * @param allValues should non-sample value be included in the output?
     * @throws IOException Thrown if there is a problem with the filename.
     */

    void exportToJson(String filename, boolean allValues) throws IOException;

    /**
     * Saves all the computed values in a model as a JSON file.
     * 
     * @param file Object representing the file to save to.
     * @throws IOException Thrown if there is a problem with the filename.
     */
    void exportToJson(File file) throws IOException;

    /**
     * Saves all the computed values in a model as a JSON file.
     * 
     * @param file      Object representing the file to save to.
     * @param allValues should non-sample value be included in the output?
     * @throws IOException Thrown if there is a problem with the filename.
     */
    void exportToJson(File file, boolean allValues) throws IOException;

    /**
     * Method to load the state of a model from a file.
     * 
     * @param filename The name of the file to load the model from.
     * @throws SandwoodJsonException Exception thrown by the model if there is an internal issue parsing the JSON.
     * @throws IOException           Exception thrown if there is a problem reading the file.
     */
    void loadModel(String filename) throws IOException, SandwoodJsonException;

    /**
     * Method to load the state of a model from a file.
     * 
     * @param file The file to load the model from.
     * @throws IOException           Exception thrown if there is a problem reading the file.
     * @throws SandwoodJsonException Exception thrown if there is a problem parsing the file.
     */
    void loadModel(File file) throws IOException, SandwoodJsonException;

    /**
     * Method to set the maximum number of threads we should execute with. The system will aim to use this number of
     * threads whenever possible.
     * 
     * @param count The maximum number of threads to execute with.
     */
    void setThreadCount(int count);

    /**
     * Method to return the maximum number of threads that can be used in execution.
     * 
     * @return Maximum number of threads that can be used in execution.
     */
    int threadCount();

    /**
     * A method to shut down any system resources such as thread pools the model may have been using.
     */
    void shutdown();

    /**
     * A method to shut down any system resources such as thread pools the model may have been using. This has the same
     * functionality as shutdown.
     */
    void close();

}