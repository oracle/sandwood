package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK16$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip1CoinMK16$CoreInterface {
	
	// Declare the variables for the model.
	private double bias;
	private boolean fixedFlag$sample14 = false;
	private boolean fixedProbFlag$sample14 = false;
	private boolean fixedProbFlag$sample16 = false;
	private boolean flip;
	private boolean flipMeasured;
	private double guard;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flip;
	private double logProbability$var11;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK16$SingleThreadCPU(ExecutionTarget target) {
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
		
		// Unset the fixed probability flag for sample 14 as it depends on bias.
		fixedProbFlag$sample14 = false;
		
		// Unset the fixed probability flag for sample 16 as it depends on bias.
		fixedProbFlag$sample16 = false;
	}

	// Getter for fixedFlag$sample14.
	@Override
	public final boolean get$fixedFlag$sample14() {
		return fixedFlag$sample14;
	}

	// Setter for fixedFlag$sample14.
	@Override
	public final void set$fixedFlag$sample14(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample14 including if probabilities
		// need to be updated.
		fixedFlag$sample14 = cv$value;
		
		// Should the probability of sample 14 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample14" with its value "cv$value".
		fixedProbFlag$sample14 = (cv$value && fixedProbFlag$sample14);
		
		// Should the probability of sample 16 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample14" with its value "cv$value".
		fixedProbFlag$sample16 = (cv$value && fixedProbFlag$sample16);
	}

	// Getter for flip.
	@Override
	public final boolean get$flip() {
		return flip;
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

	// Calculate the probability of the samples represented by sample14 using sampled
	// values.
	private final void logProbabilityValue$sample14() {
		// Determine if we need to calculate the values for sample task 14 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample14) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if(Double.isNaN(guard)) {
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(cv$sampleReached) {
				logProbability$var11 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$bias = cv$sampleAccumulator;
			}
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample14)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample14 = fixedFlag$sample14;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if(Double.isNaN(guard))
				// Record that the sample was reached.
				cv$sampleReached = true;
			if(cv$sampleReached)
				logProbability$var11 = logProbability$bias;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$bias);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample14)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$bias);
		}
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if(Double.isNaN(guard)) {
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = Math.log((flip?bias:(1.0 - bias)));
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(cv$sampleReached) {
				logProbability$bernoulli = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$flip = cv$sampleAccumulator;
			}
			
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
			fixedProbFlag$sample16 = fixedFlag$sample14;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if(Double.isNaN(guard))
				// Record that the sample was reached.
				cv$sampleReached = true;
			if(cv$sampleReached)
				logProbability$bernoulli = logProbability$flip;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$flip);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$flip);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 14 drawn from Beta 11. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample14() {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// If the sample value was positive increase the count
		if(flip)
			// Local variable to record the number of true samples.
			cv$sum = 1;
		
		// Write out the new value of the sample.
		// 
		// Processing random variable 13.
		// 
		// Processing sample task 16 of consumer random variable bernoulli.
		// 
		// Include the value sampled by task 16 from random variable bernoulli.
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
			if(!fixedFlag$sample14)
				bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			flip = DistributionSampling.sampleBernoulli(RNG$, bias);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if((Double.isNaN(guard) && !fixedFlag$sample14))
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(Double.isNaN(guard)) {
			if(!fixedFlag$sample14)
				bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			flip = DistributionSampling.sampleBernoulli(RNG$, bias);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if((Double.isNaN(guard) && !fixedFlag$sample14))
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if((Double.isNaN(guard) && !fixedFlag$sample14))
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((Double.isNaN(guard) && !fixedFlag$sample14))
			sample14();
		
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
		logProbability$var11 = Double.NaN;
		if(!fixedProbFlag$sample14)
			logProbability$bias = Double.NaN;
		logProbability$bernoulli = Double.NaN;
		if(!fixedProbFlag$sample16)
			logProbability$flip = Double.NaN;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(fixedFlag$sample14)
			logProbabilityValue$sample14();
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
		logProbabilityValue$sample14();
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
		logProbabilityValue$sample14();
		logProbabilityValue$sample16();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(Double.isNaN(guard))
			flip = flipMeasured;
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2023, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model Flip1CoinMK16(boolean flipMeasured, double guard) {\n"
		     + "    if(isNaN(guard)) {\n"
		     + "        double bias = beta(1.0, 1.0).sample();\n"
		     + "        Bernoulli bernoulli = bernoulli(bias);\n"
		     + "        boolean flip = bernoulli.sample();\n"
		     + "        flip.observe(flipMeasured);\n"
		     + "    }\n"
		     + "}\n"
		     + "";
	}
}