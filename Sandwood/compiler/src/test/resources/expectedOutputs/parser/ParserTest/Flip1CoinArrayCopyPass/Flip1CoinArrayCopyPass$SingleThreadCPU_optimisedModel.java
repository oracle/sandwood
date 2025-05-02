package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinArrayCopyPass$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip1CoinArrayCopyPass$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private boolean fixedFlag$sample10 = false;
	private boolean fixedProbFlag$sample10 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample26;
	private double logProbability$var10;
	private double logProbability$var9;
	private int samples;
	private boolean system$gibbsForward = true;

	public Flip1CoinArrayCopyPass$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for a.
	@Override
	public final double get$a() {
		return 1.0;
	}

	// Getter for b.
	@Override
	public final double get$b() {
		return 1.0;
	}

	// Getter for bias.
	@Override
	public final double[] get$bias() {
		return bias;
	}

	// Setter for bias.
	@Override
	public final void set$bias(double[] cv$value) {
		// Set flags for all the side effects of bias including if probabilities need to be
		// updated.
		// Set bias
		bias = cv$value;
		
		// Unset the fixed probability flag for sample 10 as it depends on bias.
		fixedProbFlag$sample10 = false;
		
		// Unset the fixed probability flag for sample 26 as it depends on bias.
		fixedProbFlag$sample26 = false;
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
		
		// Should the probability of sample 26 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample10" with its value "cv$value".
		fixedProbFlag$sample26 = (cv$value && fixedProbFlag$sample26);
	}

	// Getter for flips.
	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	// Getter for flipsMeasured.
	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	// Setter for flipsMeasured.
	@Override
	public final void set$flipsMeasured(boolean[] cv$value) {
		// Set flipsMeasured
		flipsMeasured = cv$value;
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
	public final double[] get$logProbability$bernoulli() {
		return logProbability$bernoulli;
	}

	// Getter for logProbability$bias.
	@Override
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	// Getter for logProbability$flips.
	@Override
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	// Getter for samples.
	@Override
	public final int get$samples() {
		return samples;
	}

	// Setter for samples.
	@Override
	public final void set$samples(int cv$value) {
		samples = cv$value;
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(bias[0], 1.0, 1.0);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var9 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var10 = cv$distributionAccumulator;
			
			// Update the variable probability
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
			logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			
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
			logProbability$var9 = logProbability$var10;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$bias = (logProbability$bias + logProbability$var10);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var10);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample10)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var10);
		}
	}

	// Calculate the probability of the samples represented by sample26 using sampled
	// values.
	private final void logProbabilityValue$sample26() {
		// Determine if we need to calculate the values for sample task 26 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample26) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i = 0; i < samples; i += 1) {
				double var24 = bias[i];
				
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
				double cv$distributionAccumulator = Math.log((flips[i]?var24:(1.0 - var24)));
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$bernoulli[i] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample26[i] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample26 = fixedFlag$sample10;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < samples; i += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample26[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli[i] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 10 drawn from Beta 9. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample10() {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < samples)) {
			// Processing sample task 26 of consumer random variable bernoulli.
			// 
			// Include the value sampled by task 26 from random variable bernoulli.
			// 
			// Increment the number of samples.
			// 
			// Local variable to record the number of samples.
			cv$count = 1;
			
			// If the sample value was positive increase the count
			// 
			// Substituted "i" with its value "0".
			if(flips[0])
				// Local variable to record the number of true samples.
				cv$sum = 1;
		}
		for(int i = 0; i < samples; i += 1) {
			int index$i$2_2 = (i + 1);
			if((index$i$2_2 < samples)) {
				// Processing sample task 26 of consumer random variable bernoulli.
				// 
				// Include the value sampled by task 26 from random variable bernoulli.
				// Increment the number of samples.
				cv$count = (cv$count + 1);
				
				// If the sample value was positive increase the count
				if(flips[index$i$2_2])
					cv$sum = (cv$sum + 1);
			}
		}
		
		// Guards to ensure that bias is only updated when there is a valid path.
		// 
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		bias[0] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		
		// Guards to ensure that bias is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 10 and consumer double[] 32.
		for(int i = 0; i < samples; i += 1)
			bias[(i + 1)] = bias[0];
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If bias has not been set already allocate space.
		if(!fixedFlag$sample10)
			// Constructor for bias
			bias = new double[(samples + 1)];
		
		// Constructor for flips
		flips = new boolean[samples];
		
		// Constructor for logProbability$bernoulli
		logProbability$bernoulli = new double[samples];
		
		// Constructor for logProbability$sample26
		logProbability$sample26 = new double[samples];
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample10)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		for(int i = 0; i < samples; i += 1) {
			flips[i] = DistributionSampling.sampleBernoulli(RNG$, bias[i]);
			if(!fixedFlag$sample10)
				bias[(i + 1)] = bias[0];
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample10)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample10)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
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
		logProbability$var9 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample10)
			logProbability$var10 = Double.NaN;
		for(int i = 0; i < samples; i += 1)
			logProbability$bernoulli[i] = Double.NaN;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample26) {
			for(int i = 0; i < samples; i += 1)
				logProbability$sample26[i] = Double.NaN;
		}
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
		logProbabilityValue$sample26();
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
		logProbabilityValue$sample26();
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
		logProbabilityValue$sample26();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample10)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = flipsMeasured[cv$index1];
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
		     + "public model Flip1CoinArrayCopyPass(int samples, boolean[] flipsMeasured) {\n"
		     + "    /*\n"
		     + "     * This is a bad example as there is a separation between the size of \n"
		     + "     * flips measured, and the size of noSamples.\n"
		     + "     */\n"
		     + "    double a = 1.0;\n"
		     + "    double b = 1.0;\n"
		     + "    double[] bias = new double[samples+1];\n"
		     + "    bias[0] = beta(a, b).sample();\n"
		     + "    boolean[] flips = new boolean[samples];\n"
		     + "    for(int i:[0..samples)) {\n"
		     + "        Bernoulli bernoulli = bernoulli(bias[i]);\n"
		     + "        flips[i] = bernoulli.sample();\n"
		     + "        bias[i+1] = bias[0];\n"
		     + "    }\n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}";
	}
}