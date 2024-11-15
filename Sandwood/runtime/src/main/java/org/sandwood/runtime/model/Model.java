/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.random.RandomType;
import org.sandwood.runtime.exceptions.SandwoodJsonException;
import org.sandwood.runtime.internal.json.JsonModelDecoder;
import org.sandwood.runtime.internal.json.JsonModelEncoder;
import org.sandwood.runtime.internal.model.CoreModel;
import org.sandwood.runtime.internal.model.auxillary.ForwardPass;
import org.sandwood.runtime.internal.model.auxillary.GibbsCalculation;
import org.sandwood.runtime.internal.model.auxillary.ProbabilityCalculation;
import org.sandwood.runtime.internal.model.variables.ComputedObjectArrayInternal;
import org.sandwood.runtime.internal.model.variables.ComputedVariableInternal;
import org.sandwood.runtime.internal.model.variables.CurrentProbability;
import org.sandwood.runtime.internal.model.variables.HasProbabilityInternal;
import org.sandwood.runtime.internal.model.variables.ObservedVariableInternal;
import org.sandwood.runtime.internal.model.variables.ObservedVariableShapeableInternal;
import org.sandwood.runtime.model.variables.ComputedBoolean;
import org.sandwood.runtime.model.variables.ComputedBooleanArray;
import org.sandwood.runtime.model.variables.ComputedDouble;
import org.sandwood.runtime.model.variables.ComputedDoubleArray;
import org.sandwood.runtime.model.variables.ComputedInteger;
import org.sandwood.runtime.model.variables.ComputedIntegerArray;
import org.sandwood.runtime.model.variables.ComputedVariable;
import org.sandwood.runtime.model.variables.HasProbability;
import org.sandwood.runtime.model.variables.ObservedVariable;
import org.sandwood.runtime.model.variables.ObservedVariableShapeable;

/**
 * A class that implements the base functionality of a probabilistic model. All compiled models will extend this class.
 */
public abstract class Model implements HasProbability, AutoCloseable {
    // Has space been allocated. This can only occur after the observed parameters
    // that any array sizes depend on have been set.
    private boolean allocated = false;
    // Was the model last run forwards or with inference.
    private boolean lastForward = true;
    // Are the observed values propagated into the model. TODO can we merge this into existing flags.
    private boolean observationsPropagated = false;

    private CoreModel core;
    // Inputs that parameterize the model, for example the bias we wish to use.
    private Map<String, ObservedVariableInternal> modelInputs;
    // Inputs that are only used for training, for example the value of a flipped
    // coin
    private Map<String, ObservedVariableInternal> regularObservedVariables;
    // Inputs that are used for both parametrizing the model and for training.
    // For example and array of coins such that the length of the array is used as
    // an input.
    private Map<String, ObservedVariableShapeableInternal<?>> shapeableObservedVariables;
    private Map<String, ComputedVariableInternal> computedVariables;
    private HasProbabilityInternal[] probabilityVariables;
    private double logModelProbability = Double.NEGATIVE_INFINITY;
    // TODO Ensure that these flags get set and reset at the correct times.
    private boolean probabilityComputed = false;
    private boolean modelPrimed = false;

    private ExecutionTarget executionTarget = ExecutionTarget.singleThread;

    private int thinning = 0;
    private int burnin = 0;

    protected Model() {}

    protected void init(CoreModel core, Map<String, ObservedVariableInternal> modelInputs,
            Map<String, ObservedVariableInternal> regularObservedVariables,
            Map<String, ObservedVariableShapeableInternal<?>> shapeableObservedVariables,
            Map<String, ComputedVariableInternal> computedVariables, HasProbabilityInternal[] probabilityVariables) {
        this.core = core;
        this.modelInputs = modelInputs;
        this.regularObservedVariables = regularObservedVariables;
        this.shapeableObservedVariables = shapeableObservedVariables;
        this.computedVariables = computedVariables;
        this.probabilityVariables = probabilityVariables;
    }

    /**
     * Method to set the target execution backend. This is used to change how the code is executed ranging from single
     * threaded to GPU execution.
     * 
     * @param target The hardware/platform that the execution should target.
     */
    public synchronized void setExecutionTarget(ExecutionTarget target) {
        if(!target.isSupported())
            throw new SandwoodException(target + " runtime is not supported on this machine.");

        CoreModel oldCore = core;
        // This method call also copies the state.
        core = setExecutionTargetInternal(target);
        core.setRngType(oldCore.getRngType());
        executionTarget = target;

        if(allocated) {
            allocated = false;
            allocate();
            core.setIntermediates();
        }

        oldCore.shutdown();
    }

    protected abstract CoreModel setExecutionTargetInternal(ExecutionTarget target);

    /**
     * Method to determine what type of execution is currently set to be used
     * 
     * @return The type of execution being used.
     */
    public synchronized ExecutionTarget getCurrentExecutionTarget() {
        return executionTarget;
    }

    // RNG stuff
    /**
     * Method to initialize the seed of the random number generator. If the model has already been used, the threads
     * will have to be reinitialized to propagate these changes.
     * 
     * @param seed The seed for the random number generator.
     */
    public synchronized void initializeSeed(long seed) {
        core.initializeSeed(seed);
    }

    /**
     * Method to change the type of the random number generator. If the model has already been used, the threads will
     * have to be reinitialized to propagate these changes.
     * 
     * @param type The type of the random number generator.
     */
    public synchronized void setRNGType(RandomType type) {
        core.setRngType(type);
    }

    /**
     * Method to set both the type and seed of the random number generator. If the model has already been used, the
     * threads will have to be reinitialized to propagate these changes.
     *
     * @param type The type of the Random Number Generator
     * @param seed The seed to initialize the Random Number Generator with.
     */
    public synchronized void setRNGType(RandomType type, long seed) {
        core.setRngType(type, seed);
    }

    /**
     * A method to test if all the required variables have been set to infer values from the model.
     * 
     * @return A boolean marking if all the required variables have been set.
     */
    public synchronized boolean readyInferValues() {
        boolean ready = true;
        for(ObservedVariable v:modelInputs.values())
            ready = ready && v.isSet();
        for(ObservedVariable v:regularObservedVariables.values())
            ready = ready && v.isSet();
        for(ObservedVariable v:shapeableObservedVariables.values())
            ready = ready && v.isSet();
        return ready;
    }

    /**
     * A method to report any missing values.
     * 
     * @return a String listing the unset values.
     */
    public synchronized String missingInferValues() {
        StringBuilder sb = new StringBuilder();
        for(ObservedVariable v:modelInputs.values())
            if(!v.isSet())
                sb.append("Missing input value for " + v.name() + "\n");
        for(ObservedVariable v:regularObservedVariables.values())
            if(!v.isSet())
                sb.append("Missing input value for " + v.name() + "\n");
        for(ObservedVariable v:shapeableObservedVariables.values())
            if(!v.isSet())
                sb.append("Missing input value for " + v.name() + "\n");
        return sb.toString();
    }

    /**
     * A method to test if all the required variables have been set to execute the model as regular code.
     * 
     * @return A boolean marking if all the required variables have been set.
     */
    public synchronized boolean readyExecute() {
        boolean ready = true;
        for(ObservedVariable v:modelInputs.values())
            ready = ready && v.isSet();
        for(ObservedVariableShapeable v:shapeableObservedVariables.values())
            ready = ready && v.shapeSet();
        return ready;
    }

    /**
     * A method to report any missing values.
     * 
     * @return a String listing the unset values.
     */
    public synchronized String missingExecute() {
        StringBuilder sb = new StringBuilder();
        for(ObservedVariable v:modelInputs.values())
            if(!v.isSet())
                sb.append("Missing input value for " + v.name() + "\n");
        for(ObservedVariableShapeable v:shapeableObservedVariables.values())
            if(!v.shapeSet())
                sb.append("Missing shape for " + v.name() + "\n");
        return sb.toString();
    }

    /**
     * A method to test if all the required variables have been set for generating the probabilities of the model.
     * 
     * @return A boolean marking if all the required variables have been set.
     */
    public synchronized boolean readyInferProbabilities() {
        boolean ready = true;
        for(ObservedVariable v:modelInputs.values())
            ready = ready && v.isSet();
        for(ObservedVariable v:regularObservedVariables.values())
            ready = ready && v.isSet();
        for(ObservedVariable v:shapeableObservedVariables.values())
            ready = ready && v.isSet();
        return ready;
    }

    /**
     * A method to report any missing values required to infer probabilities.
     * 
     * @return a String listing the unset values.
     */
    public synchronized String missingInferProbabilities() {
        StringBuilder sb = new StringBuilder();
        for(ObservedVariable v:modelInputs.values())
            if(!v.isSet())
                sb.append("Missing input value for " + v.name() + "\n");
        for(ObservedVariable v:regularObservedVariables.values())
            if(!v.isSet())
                sb.append("Missing input value for " + v.name() + "\n");
        for(ObservedVariableShapeable v:shapeableObservedVariables.values())
            if(!v.shapeSet())
                sb.append("Missing shape for " + v.name() + "\n");
        return sb.toString();
    }

    /**
     * Method to get the probability of the model.
     */
    @Override
    public synchronized double getProbability() {
        return Math.exp(logModelProbability);
    }

    /**
     * Method to get the probability of the model.
     */
    @Override
    public synchronized double getLogProbability() {
        return logModelProbability;
    }

    /**
     * Set the inference technique to use for inference passes.
     * 
     * @param i Inference technique.
     */
    /*
     * public void setDefaultInferenceTechnique(InferenceTechnique i) { // TODO Auto-generated method stub }
     */

    /**
     * Get the set of available techniques for this model.
     * 
     * @return Available techniques.
     */
    public synchronized Set<InferenceTechnique> availableInferenceTechniques() {
        Set<InferenceTechnique> available = new HashSet<>();
        available.add(InferenceTechnique.GIBBS);
        return available;
    }

    /**
     * Set the retention policy for the variables in the model.
     * 
     * @param p The retention policy to set.
     */
    public synchronized void setDefaultRetentionPolicy(RetentionPolicy p) {
        for(ComputedVariableInternal v:computedVariables.values())
            v.setRetentionPolicy(p);
    }

    /**
     * Method to set the level of thinning that this model should use when performing inference on the model. By
     * default, this value is set to 0;
     * 
     * @param thinning The number of runs that should be ignored between each run that takes a sample.
     */
    public synchronized void setThinning(int thinning) {
        this.thinning = thinning;
    }

    /**
     * Method to set the number of steps of burnin that should be used when performing inference on the model. The
     * default value is 0.
     * 
     * @param burnin The number of cycles before starting to collect values.
     */
    public synchronized void setBurnin(int burnin) {
        this.burnin = burnin;
    }

    /**
     * Perform a single pass generating values from the model.
     */
    public synchronized void execute() {
        execute(1);
    }

    /**
     * Perform multiple passes over the model generating new values with each pass.
     * 
     * @param iterations The number of iterations to perform.
     */
    public synchronized void execute(int iterations) {
        if(!readyExecute())
            throw new SandwoodException(
                    "Unable to execute: The input values and output shapes have not been set.\n" + missingExecute());
        allocate();
        if(iterations > 0) {
            ForwardPass.forward(iterations, computedVariables.values(), core);
            lastForward = true;
            modelPrimed = false;
            observationsPropagated = false;
        }
    }

    /**
     * Calculate the parameters of the model based on a fixed set of inputs and outputs.
     * 
     * @param iterations   The number of sampling iterations to perform. The total number of iterations performed will
     *                     be burnin + (1+thinning)*(samples-1) + 1. The values of thinning and burnin are set with
     *                     separate method calls.
     * @param mapVariables This is an optional list of computed variables that will be used to pick which iteration
     *                     should be used to return a mapped value.
     */
    public synchronized void inferValues(int iterations, ComputedVariable... mapVariables) {
        inferValues(iterations, burnin, thinning, mapVariables);
    }

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
    public synchronized void inferValues(int iterations, int burnin, int thinning, ComputedVariable... mapVariables) {
        if(!readyInferValues())
            throw new SandwoodException("Unable to execute: The input values and output shapes have not been set.\n"
                    + missingInferValues());
        if(iterations + burnin + thinning != 0) {
            CurrentProbability[] p;
            if(mapVariables.length == 0) {
                p = new CurrentProbability[1];
                p[0] = core;
            } else {
                int size = mapVariables.length;
                p = new CurrentProbability[size];
                for(int i = 0; i < size; i++) {
                    String name = mapVariables[i].name();
                    p[i] = computedVariables.get(name);
                }
            }

            allocate();
            if(!modelPrimed) {
                core.forwardGenerationDistributionsNoOutputs();
                modelPrimed = true;
                lastForward = false;
            }
            if(!observationsPropagated) {
                core.propogateObservedValues();
                observationsPropagated = true;
            }
            GibbsCalculation.infer(iterations, burnin, thinning, computedVariables.values(), core, p);
        }
    }

    /**
     * Calculate the probability of each variable and the probability of the overall model.
     * 
     * @param iterations The number of iterations to perform when calculating these values.
     */
    public synchronized void inferProbabilities(int iterations) {
        if(!readyInferProbabilities())
            throw new SandwoodException("Unable to execute: The input values and output shapes have not been set.\n"
                    + missingInferProbabilities());
        allocate();
        if(!observationsPropagated) {
            core.propogateObservedValues();
            observationsPropagated = true;
        }
        logModelProbability = ProbabilityCalculation.generateLogProbabilities(iterations, core, probabilityVariables);
        probabilityComputed = true;
        lastForward = true;
        modelPrimed = false;
    }

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
    public synchronized void inferProbabilities(double variance, int initialIterations, int maxIterations) {
        if(!readyInferProbabilities())
            throw new SandwoodException("Unable to execute: The input values and output shapes have not been set.\n"
                    + missingInferProbabilities());
        allocate();
        if(!observationsPropagated) {
            core.propogateObservedValues();
            observationsPropagated = true;
        }
        logModelProbability = ProbabilityCalculation.generateLogProbabilities(variance, initialIterations,
                maxIterations, core, probabilityVariables);
        probabilityComputed = true;
        lastForward = true;
        modelPrimed = false;
    }

    /**
     * Calculate the probability of each variable and the probability of the overall model. This method will iterate
     * until the variance of the overall model drops below the value provide for variance is reached.
     *
     * @param variance          The maximum variance in the models overall probability.
     * @param initialIterations The number of iterations to use to start with. Having too low a value here can result in
     *                          premature termination as the model may not have enough runs to estimate the variance
     *                          accurately.
     */
    public synchronized void inferProbabilities(double variance, int initialIterations) {
        if(!readyInferProbabilities())
            throw new SandwoodException("Unable to execute: The input values and output shapes have not been set.\n"
                    + missingInferProbabilities());
        allocate();
        if(!observationsPropagated) {
            core.propogateObservedValues();
            observationsPropagated = true;
        }
        logModelProbability = ProbabilityCalculation.generateLogProbabilities(variance, initialIterations,
                Integer.MAX_VALUE, core, probabilityVariables);
        probabilityComputed = true;
        lastForward = true;
        modelPrimed = false;
    }

    /**
     * Test to see if a probability has been computed.
     * 
     * @return Has the probability value been computed.
     */
    @Override
    public synchronized boolean probabilityComputed() {
        return probabilityComputed;
    }

    /**
     * Method to return the log probability of the model object with the variables as they are currently set. This
     * method DOES NOT integrate over variables whose value is not fixed.
     * 
     * @return The probability of the current state of the model only. Because this does not include integration the
     *         values are NOT constrained to being less than 0.
     */
    public synchronized double spotLogProbability() {
        if(lastForward)
            core.logModelProbabilitiesVal();
        else
            core.logModelProbabilitiesDist();
        return core.get$logProbability$$evidence();
    }

    /**
     * Method to return the probability of the model object with the variables as they are currently set. This method
     * DOES NOT integrate over variables whose value is not fixed.
     * 
     * @return The probability of the current state of the model only. Because this does not include integration the
     *         values are NOT constrained to being less than 1.
     */
    public synchronized double spotProbability() {
        return Math.exp(spotLogProbability());
    }

    /**
     * Construct a Jar file containing a trained version of the model.
     *
     * @param f File representing the location that the new model should be placed into.
     */
    public synchronized void constructTrainedModel(File f) {
        throw new UnsupportedOperationException("This functionality has not yet been implemented.");
    }

    /**
     * Get the Sandwood code that this model was originally generated from.
     * 
     * @return The code the Sandwood model is generated from.
     */
    public String getModelCode() {
        return core.modelCode();
    }

    private void allocate() {
        if(!allocated) {
            // TODO merge initializeConstants and allocate
            // in case an allocation depends on an earlier constant.
            core.allocator();
            core.initializeConstants();
            allocated = true;
        }
    }

    /**
     * Helper method for populating a class after model value inference
     * 
     * @param arg The value that the inferred results are to be taken from.
     * @return An array holding the inferred results, or null if the retention policy was None.
     */
    protected double[] getInferredValue(ComputedDouble arg) {
        switch(arg.getRetentionPolicy()) {
            case MAP:
                double[] toReturn = new double[1];
                toReturn[0] = arg.getMAP();
                return toReturn;

            case SAMPLE:
                return arg.getSamples();

            case NONE:
                return null;
        }
        // Unreachable code.
        return null;
    }

    /**
     * Helper method for populating a class after model value inference
     * 
     * @param arg The value that the inferred results are to be taken from.
     * @return An array holding the inferred results, or null if the retention policy was None.
     */
    protected double[][] getInferredValue(ComputedDoubleArray arg) {
        switch(arg.getRetentionPolicy()) {
            case MAP:
                double[][] toReturn = new double[1][];
                toReturn[0] = arg.getMAP();
                return toReturn;

            case SAMPLE:
                return arg.getSamples();

            case NONE:
                return null;
        }
        // Unreachable code.
        return null;
    }

    /**
     * Helper method for populating a class after model value inference
     * 
     * @param arg The value that the inferred results are to be taken from.
     * @return An array holding the inferred results, or null if the retention policy was None.
     */
    protected boolean[] getInferredValue(ComputedBoolean arg) {
        switch(arg.getRetentionPolicy()) {
            case MAP:
                boolean[] toReturn = new boolean[1];
                toReturn[0] = arg.getMAP();
                return toReturn;

            case SAMPLE:
                return arg.getSamples();

            case NONE:
                return null;
        }
        // Unreachable code.
        return null;
    }

    /**
     * Helper method for populating a class after model value inference
     * 
     * @param arg The value that the inferred results are to be taken from.
     * @return An array holding the inferred results, or null if the retention policy was None.
     */
    protected boolean[][] getInferredValue(ComputedBooleanArray arg) {
        switch(arg.getRetentionPolicy()) {
            case MAP:
                boolean[][] toReturn = new boolean[1][];
                toReturn[0] = arg.getMAP();
                return toReturn;

            case SAMPLE:
                return arg.getSamples();

            case NONE:
                return null;
        }
        // Unreachable code.
        return null;
    }

    /**
     * Helper method for populating a class after model value inference
     * 
     * @param arg The value that the inferred results are to be taken from.
     * @return An array holding the inferred results, or null if the retention policy was None.
     */
    protected int[] getInferredValue(ComputedInteger arg) {
        switch(arg.getRetentionPolicy()) {
            case MAP:
                int[] toReturn = new int[1];
                toReturn[0] = arg.getMAP();
                return toReturn;

            case SAMPLE:
                return arg.getSamples();

            case NONE:
                return null;
        }
        // Unreachable code.
        return null;
    }

    /**
     * Helper method for populating a class after model value inference
     * 
     * @param arg The value that the inferred results are to be taken from.
     * @return An array holding the inferred results, or null if the retention policy was None.
     */
    protected int[][] getInferredValue(ComputedIntegerArray arg) {
        switch(arg.getRetentionPolicy()) {
            case MAP:
                int[][] toReturn = new int[1][];
                toReturn[0] = arg.getMAP();
                return toReturn;

            case SAMPLE:
                return arg.getSamples();

            case NONE:
                return null;
        }
        // Unreachable code.
        return null;
    }

    /**
     * Helper method for populating a class after model value inference
     * 
     * @param <A>
     * @param arg The value that the inferred results are to be taken from.
     * @return An array holding the inferred results, or null if the retention policy was None.
     */
    protected <A> A[][] getInferredValue(ComputedObjectArrayInternal<A> arg) {
        switch(arg.getRetentionPolicy()) {
            case MAP:
                A[][] toReturn = arg.constructArray(1);
                toReturn[0] = arg.getMAP();
                return toReturn;

            case SAMPLE:
                return arg.getSamples();

            case NONE:
                return null;
        }
        // Unreachable code.
        return null;
    }

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
    public List<ComputedVariable> setToMAPValues() {
        List<ComputedVariable> toReturn = new ArrayList<>();
        for(ComputedVariable c:computedVariables.values()) {
            if(c.isSettable()) {
                if(c.setToMAPValue())
                    toReturn.add(c);
            }
        }
        return toReturn;
    }

    /**
     * Saves all the computed values in a model as a JSON file.
     * 
     * @param filename name of the file to save to.
     * @throws IOException Thrown if there is a problem with the filename.
     */

    public synchronized void exportToJson(String filename) throws IOException {
        exportToJson(new File(filename));
    }

    /**
     * Saves all the computed values in a model as a JSON file.
     * 
     * @param file Object representing the file to save to.
     * @throws IOException Thrown if there is a problem with the filename.
     */
    public synchronized void exportToJson(File file) throws IOException {
        JsonModelEncoder e = new JsonModelEncoder(file, this);
        // This can return all sampled variables, but when assigning to variables we
        // will only want to assign to sampled variables.
        // TODO construct a dependency graph of these variables, so when one is pinned
        // we know which others to set and pin.
        PriorityQueue<String> p = new PriorityQueue<>(computedVariables.keySet());
        while(!p.isEmpty()) {
            String name = p.poll();
            ComputedVariableInternal v = computedVariables.get(name);
            e.addVariable(v);
        }

        p = new PriorityQueue<>(modelInputs.keySet());
        while(!p.isEmpty()) {
            String name = p.poll();
            ObservedVariableInternal v = modelInputs.get(name);
            e.addVariable(v);
        }

        p = new PriorityQueue<>(regularObservedVariables.keySet());
        while(!p.isEmpty()) {
            String name = p.poll();
            ObservedVariableInternal v = regularObservedVariables.get(name);
            e.addVariable(v);
        }

        p = new PriorityQueue<>(shapeableObservedVariables.keySet());
        while(!p.isEmpty()) {
            String name = p.poll();
            ObservedVariableShapeableInternal<?> v = shapeableObservedVariables.get(name);
            e.addShapedVariable(v);
        }

        e.close();
    }

    /**
     * Saves the samples of the model.
     * 
     * @param filename name of the file to save to.
     * @throws IOException Thrown if there is a problem with the filename.
     */
    public synchronized void saveModel(String filename) throws IOException {
        saveModel(new File(filename));
    }

    /**
     * Saves the samples of the model.
     * 
     * @param file Object representing the file to save to.
     * @throws IOException Thrown if there is a problem with the filename.
     */
    public synchronized void saveModel(File file) throws IOException {
        JsonModelEncoder e = new JsonModelEncoder(file, this);

        // This can return all sampled variables, but when assigning to variables we
        // will only want to assign to sampled variables.
        // TODO construct a dependency graph of these variables, so when one is pinned
        // we know which others to set and pin.
        PriorityQueue<String> p = new PriorityQueue<>(computedVariables.keySet());
        while(!p.isEmpty()) {
            String name = p.poll();
            ComputedVariableInternal v = computedVariables.get(name);
            if(v.isSample) {
                if(v.getRetentionPolicy() == RetentionPolicy.MAP)
                    e.addVariable(v);
                else // TODO work out what we should do here, as we may want to just average sampled
                     // values etc.
                    throw new SandwoodException(
                            "Variable " + name + " needs a retention policy of map to store value.");
            }
        }

        p = new PriorityQueue<>(modelInputs.keySet());
        while(!p.isEmpty()) {
            String name = p.poll();
            ObservedVariableInternal v = modelInputs.get(name);
            e.addVariable(v);
        }

        p = new PriorityQueue<>(regularObservedVariables.keySet());
        while(!p.isEmpty()) {
            String name = p.poll();
            ObservedVariableInternal v = regularObservedVariables.get(name);
            e.addVariable(v);
        }

        p = new PriorityQueue<>(shapeableObservedVariables.keySet());
        while(!p.isEmpty()) {
            String name = p.poll();
            ObservedVariableInternal v = shapeableObservedVariables.get(name);
            e.addVariable(v);
        }

        e.close();
    }

    /**
     * Method to load the state of a model from a file.
     * 
     * @param filename The name of the file to load the model from.
     * @throws SandwoodJsonException Exception thrown by the model if there is an internal issue parsing the JSON.
     * @throws IOException           Exception thrown if there is a problem reading the file.
     */
    public synchronized void loadModel(String filename) throws IOException, SandwoodJsonException {
        loadModel(new File(filename));
    }

    /**
     * Method to load the state of a model from a file.
     * 
     * @param file The file to load the model from.
     * @throws IOException           Exception thrown if there is a problem reading the file.
     * @throws SandwoodJsonException Exception thrown if there is a problem parsing the file.
     */
    public synchronized void loadModel(File file) throws IOException, SandwoodJsonException {
        JsonModelDecoder d = new JsonModelDecoder(file);
        d.parse(modelInputs, regularObservedVariables, shapeableObservedVariables, computedVariables, getModelCode());

        if(!readyExecute())
            throw new SandwoodException(
                    "Unable to allocate model space: The input values and output shapes have not been set.\n"
                            + missingExecute());
        allocate();

        core.setIntermediates();
    }

    /**
     * Method to set the maximum number of threads we should execute with. The system will aim to use this number of
     * threads whenever possible.
     * 
     * @param count The maximum number of threads to execute with.
     */
    public synchronized void setThreadCount(int count) {
        if(count != core.threadCount()) {
            core.setThreadCount(count);
            core.allocateScratch();
        }
    }

    /**
     * Method to return the maximum number of threads that can be used in execution.
     * 
     * @return Maximum number of threads that can be used in execution.
     */
    public synchronized int threadCount() {
        return core.threadCount();
    }

    /**
     * A method to shut down any system resources such as thread pools the model may have been using.
     */
    public synchronized void shutdown() {
        core.shutdown();
    }

    /**
     * A method to shut down any system resources such as thread pools the model may have been using. This has the same
     * functionality as shutdown.
     */
    @Override
    public synchronized void close() {
        shutdown();
    }
}