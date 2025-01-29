package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK16$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK16$CoreInterface {
	
	// Declare the variables for the model.
	private double bias;
	private boolean fixedFlag$sample11 = false;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample11 = false;
	private boolean fixedProbFlag$sample9 = false;
	private boolean flip;
	private boolean flipMeasured;
	private double guard;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flip;
	private double logProbability$var6;
	private boolean setFlag$bias = false;
	private boolean setFlag$flip = false;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK16$MultiThreadCPU(ExecutionTarget target) {
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
		
		// Unset the fixed probability flag for sample 9 as it depends on bias.
		fixedProbFlag$sample9 = false;
		
		// Unset the fixed probability flag for sample 11 as it depends on bias.
		fixedProbFlag$sample11 = false;
	}

	// Getter for fixedFlag$sample11.
	@Override
	public final boolean get$fixedFlag$sample11() {
		return fixedFlag$sample11;
	}

	// Setter for fixedFlag$sample11.
	@Override
	public final void set$fixedFlag$sample11(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample11 including if probabilities
		// need to be updated.
		fixedFlag$sample11 = cv$value;
		
		// Should the probability of sample 11 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample11" with its value "cv$value".
		fixedProbFlag$sample11 = (cv$value && fixedProbFlag$sample11);
	}

	// Getter for fixedFlag$sample9.
	@Override
	public final boolean get$fixedFlag$sample9() {
		return fixedFlag$sample9;
	}

	// Setter for fixedFlag$sample9.
	@Override
	public final void set$fixedFlag$sample9(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample9 including if probabilities
		// need to be updated.
		fixedFlag$sample9 = cv$value;
		
		// Should the probability of sample 9 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample9" with its value "cv$value".
		fixedProbFlag$sample9 = (cv$value && fixedProbFlag$sample9);
		
		// Should the probability of sample 11 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample9" with its value "cv$value".
		fixedProbFlag$sample11 = (cv$value && fixedProbFlag$sample11);
	}

	// Getter for flip.
	@Override
	public final boolean get$flip() {
		return flip;
	}

	// Setter for flip.
	@Override
	public final void set$flip(boolean cv$value) {
		// Set flags for all the side effects of flip including if probabilities need to be
		// updated.
		flip = cv$value;
		
		// Unset the fixed probability flag for sample 11 as it depends on flip.
		fixedProbFlag$sample11 = false;
	}

	// Getter for flipMeasured.
	@Override
	public final boolean get$flipMeasured() {
		return flipMeasured;
	}

	// Setter for flipMeasured.
	@Override
	public final void set$flipMeasured(boolean cv$value) {
		flipMeasured = cv$value;
	}

	// Getter for guard.
	@Override
	public final double get$guard() {
		return guard;
	}

	// Setter for guard.
	@Override
	public final void set$guard(double cv$value) {
		guard = cv$value;
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

	// Getter for logProbability$bernoulli.
	@Override
	public final double get$logProbability$bernoulli() {
		return logProbability$bernoulli;
	}

	// Getter for logProbability$bias.
	@Override
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	// Getter for logProbability$flip.
	@Override
	public final double get$logProbability$flip() {
		return logProbability$flip;
	}

	// Calculate the probability of the samples represented by sample11 using sampled
	// values.
	private final void logProbabilityValue$sample11() {
		// Determine if we need to calculate the values for sample task 11 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample11) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			if(Double.isNaN(guard))
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
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
				cv$sampleAccumulator = DistributionSampling.logProbabilityBernoulli(flip, bias);
			logProbability$bernoulli = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$flip = cv$sampleAccumulator;
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample11 = (fixedFlag$sample11 && fixedFlag$sample9);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$bernoulli = logProbability$flip;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$flip);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$flip);
		}
	}

	// Calculate the probability of the samples represented by sample9 using sampled values.
	private final void logProbabilityValue$sample9() {
		// Determine if we need to calculate the values for sample task 9 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample9) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			if(Double.isNaN(guard))
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
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
				cv$sampleAccumulator = DistributionSampling.logProbabilityBeta(bias, 1.0, 1.0);
			logProbability$var6 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$bias = cv$sampleAccumulator;
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample9)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample9 = fixedFlag$sample9;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var6 = logProbability$bias;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$bias);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample9)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$bias);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 9 drawn from Beta 6. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample9() {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// If the sample value was positive increase the count
		if(flip)
			// Local variable to record the number of true samples.
			cv$sum = 1;
		
		// Write out the new value of the sample.
		// 
		// Processing random variable 8.
		// 
		// Processing sample task 11 of consumer random variable bernoulli.
		// 
		// Include the value sampled by task 11 from random variable bernoulli.
		// 
		// Increment the number of samples.
		// 
		// Local variable to record the number of samples.
		bias = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, 1);
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
		if(Double.isNaN(guard)) {
			if(!fixedFlag$sample9)
				bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!fixedFlag$sample11)
				flip = DistributionSampling.sampleBernoulli(RNG$, bias);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if((Double.isNaN(guard) && !fixedFlag$sample9))
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if((Double.isNaN(guard) && !fixedFlag$sample9))
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((Double.isNaN(guard) && !fixedFlag$sample9))
			sample9();
		
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
		logProbability$var6 = 0.0;
		if(!fixedProbFlag$sample9)
			logProbability$bias = 0.0;
		logProbability$bernoulli = 0.0;
		if(!fixedProbFlag$sample11)
			logProbability$flip = 0.0;
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
		if(fixedFlag$sample9)
			logProbabilityValue$sample9();
		logProbabilityValue$sample11();
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
		logProbabilityValue$sample9();
		logProbabilityValue$sample11();
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
		logProbabilityValue$sample9();
		logProbabilityValue$sample11();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if((Double.isNaN(guard) && !fixedFlag$sample9))
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(Double.isNaN(guard))
			flip = flipMeasured;
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK16(boolean flipMeasured, double guard) {\n    if(isNaN(guard)) {\n        double bias = beta(1.0, 1.0).sample();\n        Bernoulli bernoulli = bernoulli(bias);\n        boolean flip = bernoulli.sample();\n        flip.observe(flipMeasured);\n    }\n}\n";
	}
}