package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class AlternativeModelMK3$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements AlternativeModelMK3$CoreInterface {
	
	// Declare the variables for the model.
	private double bias;
	private boolean fixedFlag$sample6 = false;
	private boolean fixedFlag$sample8 = false;
	private boolean fixedProbFlag$sample6 = false;
	private boolean fixedProbFlag$sample8 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$binomial;
	private double logProbability$positiveCount;
	private double logProbability$var5;
	private int observedPositiveCount;
	private int observedSampleCount;
	private int positiveCount;
	private boolean system$gibbsForward = true;

	public AlternativeModelMK3$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for bias.
	@Override
	public final double get$bias() {
		return bias;
	}

	// Setter for bias.
	@Override
	public final void set$bias(double cv$value) {
		// Set flags for all the side effects of bias including if probabilities need to be
		// updated.
		bias = cv$value;
		
		// Unset the fixed probability flag for sample 6 as it depends on bias.
		fixedProbFlag$sample6 = false;
		
		// Unset the fixed probability flag for sample 8 as it depends on bias.
		fixedProbFlag$sample8 = false;
	}

	// Getter for fixedFlag$sample6.
	@Override
	public final boolean get$fixedFlag$sample6() {
		return fixedFlag$sample6;
	}

	// Setter for fixedFlag$sample6.
	@Override
	public final void set$fixedFlag$sample6(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample6 including if probabilities
		// need to be updated.
		fixedFlag$sample6 = cv$value;
		
		// Should the probability of sample 6 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample6" with its value "cv$value".
		fixedProbFlag$sample6 = (cv$value && fixedProbFlag$sample6);
		
		// Should the probability of sample 8 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample6" with its value "cv$value".
		fixedProbFlag$sample8 = (cv$value && fixedProbFlag$sample8);
	}

	// Getter for fixedFlag$sample8.
	@Override
	public final boolean get$fixedFlag$sample8() {
		return fixedFlag$sample8;
	}

	// Setter for fixedFlag$sample8.
	@Override
	public final void set$fixedFlag$sample8(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample8 including if probabilities
		// need to be updated.
		fixedFlag$sample8 = cv$value;
		
		// Should the probability of sample 8 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample8" with its value "cv$value".
		fixedProbFlag$sample8 = (cv$value && fixedProbFlag$sample8);
	}

	// Getter for logProbability$$evidence.
	@Override
	public final double get$logProbability$$evidence() {
		return logProbability$$evidence;
	}

	// Getter for the probability of logProbability$$model.
	@Override
	public final double getCurrentLogProbability() {
		return logProbability$$model;
	}

	// Getter for logProbability$bias.
	@Override
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	// Getter for logProbability$binomial.
	@Override
	public final double get$logProbability$binomial() {
		return logProbability$binomial;
	}

	// Getter for logProbability$positiveCount.
	@Override
	public final double get$logProbability$positiveCount() {
		return logProbability$positiveCount;
	}

	// Getter for observedPositiveCount.
	@Override
	public final int get$observedPositiveCount() {
		return observedPositiveCount;
	}

	// Setter for observedPositiveCount.
	@Override
	public final void set$observedPositiveCount(int cv$value) {
		observedPositiveCount = cv$value;
	}

	// Getter for observedSampleCount.
	@Override
	public final int get$observedSampleCount() {
		return observedSampleCount;
	}

	// Setter for observedSampleCount.
	@Override
	public final void set$observedSampleCount(int cv$value) {
		observedSampleCount = cv$value;
	}

	// Getter for positiveCount.
	@Override
	public final int get$positiveCount() {
		return positiveCount;
	}

	// Setter for positiveCount.
	@Override
	public final void set$positiveCount(int cv$value) {
		// Set flags for all the side effects of positiveCount including if probabilities
		// need to be updated.
		positiveCount = cv$value;
		
		// Unset the fixed probability flag for sample 8 as it depends on positiveCount.
		fixedProbFlag$sample8 = false;
	}

	// Calculate the probability of the samples represented by sample6 using sampled values.
	private final void logProbabilityValue$sample6() {
		// Determine if we need to calculate the values for sample task 6 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample6) {
			// Generating probabilities for sample task
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(bias, 1.0, 1.0);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var5 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$bias = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample6)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample6 = fixedFlag$sample6;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var5 = logProbability$bias;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$bias);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample6)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$bias);
		}
	}

	// Calculate the probability of the samples represented by sample8 using sampled values.
	private final void logProbabilityValue$sample8() {
		// Determine if we need to calculate the values for sample task 8 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample8) {
			// Generating probabilities for sample task
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBinomial(positiveCount, bias, observedSampleCount);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$binomial = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$positiveCount = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample8 = (fixedFlag$sample8 && fixedFlag$sample6);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$binomial = logProbability$positiveCount;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$positiveCount);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$positiveCount);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 6 drawn from Beta 5. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample6() {
		// Write out the new value of the sample.
		// 
		// Processing random variable 7.
		// 
		// Processing sample task 8 of consumer random variable binomial.
		// 
		// Include the value sampled by task 8 from random variable binomial.
		// 
		// Increment the number of booleans sampled.
		// 
		// Local variable to record the number of samples.
		bias = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, positiveCount, observedSampleCount);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample6)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample8)
			positiveCount = DistributionSampling.sampleBinomial(RNG$, bias, observedSampleCount);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample6)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample6)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample6)
			sample6();
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {}

	// A method to initialize all the probabilities in the model to 0/Log(1) ready for
	// the current probabilities to be calculated by calculating the probability of each
	// sample task, and its effect on the rest of the model.
	private final void initializeLogProbabilityFields() {
		// Set the probabilities of the random variable, and the model as a whole to ready
		// them to be reconstructed by the probability calls for each sample. Sample probabilities
		// are only reset for samples that are not fixed at a value that has already been
		// calculated.
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var5 = 0.0;
		if(!fixedProbFlag$sample6)
			logProbability$bias = 0.0;
		logProbability$binomial = 0.0;
		if(!fixedProbFlag$sample8)
			logProbability$positiveCount = 0.0;
	}

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	@Override
	public final void logEvidenceGeneration() {
		// Generate values for all the samples in the model that were not fixed or observed.
		forwardGenerationValuesNoOutputs();
		
		// Calculate the probability for the resulting model.
		logEvidenceProbabilities();
	}

	// Construct the evidence probabilities.
	private final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(fixedFlag$sample6)
			logProbabilityValue$sample6();
		logProbabilityValue$sample8();
	}

	// Method to calculate the probabilities of all the samples in the model including
	// those generating fixed data. In the process probabilities for all the random variables
	// and for the model as a whole will be calculated. This model uses distributions
	// when possible.
	@Override
	public final void logModelProbabilitiesDist() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using distributions where
		// appropriate.
		// 
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using values only.
		logProbabilityValue$sample6();
		logProbabilityValue$sample8();
	}

	// Method to calculate the probabilities of all the samples in the model including
	// those generating fixed data. In the process probabilities for all the random variables
	// and for the model as a whole will be calculated. This model only uses values.
	@Override
	public final void logModelProbabilitiesVal() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using distributions where
		// appropriate.
		// 
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using values only.
		logProbabilityValue$sample6();
		logProbabilityValue$sample8();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample6)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		positiveCount = observedPositiveCount;
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model AlternativeModelMK3(int observedSampleCount, int observedPositiveCount)  {    \n    double bias = new Beta(1.0, 1.0).sample();\n        \n    //Construct a binomial\n    Binomial binomial = new Binomial(bias, observedSampleCount);\n                \n    //Sample from it\n    int positiveCount = binomial.sample();\n        \n    //Link the sampled values to the observed values\n    positiveCount.observe(observedPositiveCount);\n}";
	}
}