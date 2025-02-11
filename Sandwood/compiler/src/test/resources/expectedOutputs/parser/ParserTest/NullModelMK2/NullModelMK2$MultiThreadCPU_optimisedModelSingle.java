package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class NullModelMK2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements NullModelMK2$CoreInterface {
	
	// Declare the variables for the model.
	private double bias;
	private double eta;
	private boolean fixedFlag$sample10 = false;
	private boolean fixedFlag$sample12 = false;
	private boolean fixedProbFlag$sample10 = false;
	private boolean fixedProbFlag$sample12 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$binomial;
	private double logProbability$positiveCount;
	private double logProbability$var9;
	private double min;
	private int observedPositiveCount;
	private int observedSampleCount;
	private int positiveCount;
	private boolean system$gibbsForward = true;

	public NullModelMK2$MultiThreadCPU(ExecutionTarget target) {
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
		
		// Unset the fixed probability flag for sample 10 as it depends on bias.
		fixedProbFlag$sample10 = false;
		
		// Unset the fixed probability flag for sample 12 as it depends on bias.
		fixedProbFlag$sample12 = false;
	}

	// Getter for eta.
	@Override
	public final double get$eta() {
		return eta;
	}

	// Setter for eta.
	@Override
	public final void set$eta(double cv$value) {
		eta = cv$value;
	}

	// Getter for fixedFlag$sample10.
	@Override
	public final boolean get$fixedFlag$sample10() {
		return fixedFlag$sample10;
	}

	// Setter for fixedFlag$sample10.
	@Override
	public final void set$fixedFlag$sample10(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample10 including if probabilities
		// need to be updated.
		fixedFlag$sample10 = cv$value;
		
		// Should the probability of sample 10 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample10" with its value "cv$value".
		fixedProbFlag$sample10 = (cv$value && fixedProbFlag$sample10);
		
		// Should the probability of sample 12 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample10" with its value "cv$value".
		fixedProbFlag$sample12 = (cv$value && fixedProbFlag$sample12);
	}

	// Getter for fixedFlag$sample12.
	@Override
	public final boolean get$fixedFlag$sample12() {
		return fixedFlag$sample12;
	}

	// Setter for fixedFlag$sample12.
	@Override
	public final void set$fixedFlag$sample12(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample12 including if probabilities
		// need to be updated.
		fixedFlag$sample12 = cv$value;
		
		// Should the probability of sample 12 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample12" with its value "cv$value".
		fixedProbFlag$sample12 = (cv$value && fixedProbFlag$sample12);
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

	// Getter for min.
	@Override
	public final double get$min() {
		return min;
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
		
		// Unset the fixed probability flag for sample 12 as it depends on positiveCount.
		fixedProbFlag$sample12 = false;
	}

	// Calculate the probability of the samples represented by sample10 using sampled
	// values.
	private final void logProbabilityValue$sample10() {
		// Determine if we need to calculate the values for sample task 10 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample10) {
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
			double cv$distributionAccumulator = (((min <= bias) && (bias <= 1.0))?(-Math.log((1.0 - min))):Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var9 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample10)
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
			fixedProbFlag$sample10 = fixedFlag$sample10;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var9 = logProbability$bias;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$bias);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample10)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$bias);
		}
	}

	// Calculate the probability of the samples represented by sample12 using sampled
	// values.
	private final void logProbabilityValue$sample12() {
		// Determine if we need to calculate the values for sample task 12 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample12) {
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
			fixedProbFlag$sample12 = (fixedFlag$sample12 && fixedFlag$sample10);
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
	// by sample task 10 drawn from Uniform 9. Inference was performed using Metropolis-Hastings.
	private final void sample10() {
		// The original value of the sample
		double cv$originalValue = bias;
		
		// Calculate a proposed variance.
		// 
		// The original value of the sample
		double cv$var = ((bias * bias) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		// 
		// The original value of the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + bias);
		
		// Variable declaration of cv$originalProbability moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 12 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$min" with its value "min".
		// 
		// Set the current value to the current state of the tree.
		// 
		// The original value of the sample
		double cv$originalProbability = (DistributionSampling.logProbabilityBinomial(positiveCount, bias, observedSampleCount) + (((min <= bias) && (bias <= 1.0))?(-Math.log((1.0 - min))):Double.NEGATIVE_INFINITY));
		
		// Update Sample and intermediate values
		// 
		// Write out the new value of the sample.
		bias = cv$proposedValue;
		
		// Variable declaration of cv$accumulatedProbabilities moved.
		// Declaration comment was:
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$min" with its value "min".
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 12 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$min" with its value "min".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBinomial(positiveCount, cv$proposedValue, observedSampleCount) + (((min <= cv$proposedValue) && (cv$proposedValue <= 1.0))?(-Math.log((1.0 - min))):Double.NEGATIVE_INFINITY));
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// The probability ration for the proposed value and the current value.
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
			bias = cv$originalValue;
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
		if(!fixedFlag$sample10)
			bias = (min + ((1.0 - min) * DistributionSampling.sampleUniform(RNG$)));
		if(!fixedFlag$sample12)
			positiveCount = DistributionSampling.sampleBinomial(RNG$, bias, observedSampleCount);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample10)
			bias = (min + ((1.0 - min) * DistributionSampling.sampleUniform(RNG$)));
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample10)
			bias = (min + ((1.0 - min) * DistributionSampling.sampleUniform(RNG$)));
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample10)
			sample10();
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		min = ((eta * 4.0) / 5.0);
	}

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
		logProbability$var9 = 0.0;
		if(!fixedProbFlag$sample10)
			logProbability$bias = 0.0;
		logProbability$binomial = 0.0;
		if(!fixedProbFlag$sample12)
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
		if(fixedFlag$sample10)
			logProbabilityValue$sample10();
		logProbabilityValue$sample12();
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
		logProbabilityValue$sample10();
		logProbabilityValue$sample12();
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
		logProbabilityValue$sample10();
		logProbabilityValue$sample12();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample10)
			bias = (min + ((1.0 - min) * DistributionSampling.sampleUniform(RNG$)));
		
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model NullModelMK2(double eta, int observedSampleCount, int observedPositiveCount) {\n        double min = eta * 4.0/5.0;    \n        double bias = uniform(min, 1.0).sample();\n        \n        //Construct a binomial\n        Binomial binomial = binomial(bias, observedSampleCount);\n                \n        //Sample from it\n        int positiveCount = binomial.sample();\n        \n        //Link the sampled values to the observed values\n        positiveCount.observe(observedPositiveCount);\n}";
	}
}