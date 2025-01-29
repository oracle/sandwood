package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class UniformBernoulli$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements UniformBernoulli$CoreInterface {
	
	// Declare the variables for the model.
	private boolean fixedFlag$sample16 = false;
	private boolean fixedFlag$sample8 = false;
	private boolean fixedProbFlag$sample16 = false;
	private boolean fixedProbFlag$sample8 = false;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$output;
	private double logProbability$prior;
	private double logProbability$var14;
	private double logProbability$var6;
	private boolean[] observed;
	private boolean[] output;
	private double prior;
	private boolean setFlag$output = false;
	private boolean system$gibbsForward = true;

	public UniformBernoulli$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for a.
	@Override
	public final double get$a() {
		return 0.0;
	}

	// Getter for b.
	@Override
	public final double get$b() {
		return 1.0;
	}

	// Getter for fixedFlag$sample16.
	@Override
	public final boolean get$fixedFlag$sample16() {
		return fixedFlag$sample16;
	}

	// Setter for fixedFlag$sample16.
	@Override
	public final void set$fixedFlag$sample16(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample16 including if probabilities
		// need to be updated.
		fixedFlag$sample16 = cv$value;
		
		// Should the probability of sample 16 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample16" with its value "cv$value".
		fixedProbFlag$sample16 = (cv$value && fixedProbFlag$sample16);
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
		
		// Should the probability of sample 16 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample8" with its value "cv$value".
		fixedProbFlag$sample16 = (cv$value && fixedProbFlag$sample16);
	}

	// Getter for length$observed.
	@Override
	public final int get$length$observed() {
		return length$observed;
	}

	// Setter for length$observed.
	@Override
	public final void set$length$observed(int cv$value) {
		length$observed = cv$value;
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

	// Getter for logProbability$output.
	@Override
	public final double get$logProbability$output() {
		return logProbability$output;
	}

	// Getter for logProbability$prior.
	@Override
	public final double get$logProbability$prior() {
		return logProbability$prior;
	}

	// Getter for observed.
	@Override
	public final boolean[] get$observed() {
		return observed;
	}

	// Setter for observed.
	@Override
	public final void set$observed(boolean[] cv$value) {
		// Set observed with flag to mark that it has been set so another array doesn't need
		// to be constructed
		observed = cv$value;
	}

	// Getter for output.
	@Override
	public final boolean[] get$output() {
		return output;
	}

	// Setter for output.
	@Override
	public final void set$output(boolean[] cv$value) {
		// Set flags for all the side effects of output including if probabilities need to
		// be updated.
		// Set output with flag to mark that it has been set so another array doesn't need
		// to be constructed
		output = cv$value;
		setFlag$output = true;
		
		// Unset the fixed probability flag for sample 16 as it depends on output.
		fixedProbFlag$sample16 = false;
	}

	// Getter for prior.
	@Override
	public final double get$prior() {
		return prior;
	}

	// Setter for prior.
	@Override
	public final void set$prior(double cv$value) {
		// Set flags for all the side effects of prior including if probabilities need to
		// be updated.
		prior = cv$value;
		
		// Unset the fixed probability flag for sample 8 as it depends on prior.
		fixedProbFlag$sample8 = false;
		
		// Unset the fixed probability flag for sample 16 as it depends on prior.
		fixedProbFlag$sample16 = false;
	}

	// Calculate the probability of the samples represented by sample16 using sampled
	// values.
	private final void logProbabilityValue$sample16() {
		// Determine if we need to calculate the values for sample task 16 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample16) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var13 = 0; var13 < length$observed; var13 += 1)
				// Add the probability of this sample task to the sample task accumulator.
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(output[var13], prior));
			logProbability$bernoulli = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var14 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$output = (logProbability$output + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample16 = (fixedFlag$sample16 && fixedFlag$sample8);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$bernoulli = logProbability$var14;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$output = (logProbability$output + logProbability$var14);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var14);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var14);
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
			double cv$distributionAccumulator = (((0.0 <= prior) && (prior <= 1.0))?0.0:Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var6 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$prior = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample8)
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
			fixedProbFlag$sample8 = fixedFlag$sample8;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var6 = logProbability$prior;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$prior);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample8)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$prior);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 8 drawn from Uniform 6. Inference was performed using Metropolis-Hastings.
	private final void sample8() {
		// The original value of the sample
		double cv$originalValue = prior;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		// 
		// The original value of the sample
		double cv$var = ((prior * prior) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		// 
		// The original value of the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + prior);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$a" with its value "0.0".
			// 
			// Set the current value to the current state of the tree.
			// 
			// The original value of the sample
			double cv$accumulatedProbabilities = (((0.0 <= prior) && (prior <= 1.0))?0.0:Double.NEGATIVE_INFINITY);
			
			// Processing sample task 16 of consumer random variable bernoulli.
			for(int var13 = 0; var13 < length$observed; var13 += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 16 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$2$prior's comment
				// Variable declaration of cv$temp$2$prior moved.
				// 
				// Set the current value to the current state of the tree.
				// 
				// The original value of the sample
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[var13], prior) + cv$accumulatedProbabilities);
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Update Sample and intermediate values
		// 
		// Write out the new value of the sample.
		prior = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$a" with its value "0.0".
		double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?0.0:Double.NEGATIVE_INFINITY);
		
		// Processing sample task 16 of consumer random variable bernoulli.
		for(int var13 = 0; var13 < length$observed; var13 += 1)
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 16 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// cv$temp$2$prior's comment
			// Variable declaration of cv$temp$2$prior moved.
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[var13], cv$proposedValue) + cv$accumulatedProbabilities);
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// The probability ration for the proposed value and the current value.
		// 
		// Variable declaration of cv$proposedProbability moved.
		// Declaration comment was:
		// The probability of the random variable generating the new sample value.
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			// If it is not revert the changes.
			// 
			// Set the sample value
			// 
			// Write out the new value of the sample.
			prior = cv$originalValue;
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If output has not been set already allocate space.
		if(!setFlag$output)
			// Constructor for output
			output = new boolean[length$observed];
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample8)
			prior = DistributionSampling.sampleUniform(RNG$);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample16) {
			for(int var13 = 0; var13 < length$observed; var13 += 1)
				output[var13] = DistributionSampling.sampleBernoulli(RNG$, prior);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample8)
			prior = DistributionSampling.sampleUniform(RNG$);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample8)
			prior = DistributionSampling.sampleUniform(RNG$);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample8)
			sample8();
		
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
		if(!fixedProbFlag$sample8)
			logProbability$prior = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$output = 0.0;
		if(!fixedProbFlag$sample16)
			logProbability$var14 = 0.0;
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
		if(fixedFlag$sample8)
			logProbabilityValue$sample8();
		logProbabilityValue$sample16();
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
		logProbabilityValue$sample8();
		logProbabilityValue$sample16();
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
		logProbabilityValue$sample8();
		logProbabilityValue$sample16();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample8)
			prior = DistributionSampling.sampleUniform(RNG$);
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = output.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			output[cv$index1] = observed[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model UniformBernoulli(boolean[] observed) {\n    double a = 0.0;\n    double b = 1.0;\n    double prior = uniform(a, b).sample();\n    Bernoulli bernoulli = bernoulli(prior);\n    boolean[] output = bernoulli.sample(observed.length);\n    output.observe(observed);\n}\n";
	}
}