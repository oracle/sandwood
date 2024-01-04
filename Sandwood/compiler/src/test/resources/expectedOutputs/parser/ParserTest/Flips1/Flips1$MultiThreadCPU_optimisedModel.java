package coinFlips;

import org.sandwood.runtime.model.ExecutionTarget;

class Flips1$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flips1$CoreInterface {
	
	// Declare the variables for the model.
	private boolean coin1;
	private boolean coin2;
	private boolean fixedFlag$sample4 = false;
	private boolean fixedFlag$sample6 = false;
	private boolean fixedProbFlag$sample4 = false;
	private boolean fixedProbFlag$sample6 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$coin1;
	private double logProbability$coin2;
	private double logProbability$twoHeads;
	private double logProbability$var3;
	private double logProbability$var5;
	private boolean system$gibbsForward = true;
	private boolean twoHeads;

	public Flips1$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for coin1.
	@Override
	public final boolean get$coin1() {
		return coin1;
	}

	// Setter for coin1.
	@Override
	public final void set$coin1(boolean calculationVariable$value) {
		coin1 = calculationVariable$value;
	}

	// Getter for coin2.
	@Override
	public final boolean get$coin2() {
		return coin2;
	}

	// Setter for coin2.
	@Override
	public final void set$coin2(boolean calculationVariable$value) {
		coin2 = calculationVariable$value;
	}

	// Getter for fixedFlag$sample4.
	@Override
	public final boolean get$fixedFlag$sample4() {
		return fixedFlag$sample4;
	}

	// Setter for fixedFlag$sample4.
	@Override
	public final void set$fixedFlag$sample4(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample4 including if probabilities
		// need to be updated.
		fixedFlag$sample4 = calculationVariable$value;
		
		// Should the probability of sample 4 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample4" with its value "calculationVariable$value".
		fixedProbFlag$sample4 = (calculationVariable$value && fixedProbFlag$sample4);
	}

	// Getter for fixedFlag$sample6.
	@Override
	public final boolean get$fixedFlag$sample6() {
		return fixedFlag$sample6;
	}

	// Setter for fixedFlag$sample6.
	@Override
	public final void set$fixedFlag$sample6(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample6 including if probabilities
		// need to be updated.
		fixedFlag$sample6 = calculationVariable$value;
		
		// Should the probability of sample 6 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample6" with its value "calculationVariable$value".
		fixedProbFlag$sample6 = (calculationVariable$value && fixedProbFlag$sample6);
	}

	// Getter for heads1.
	@Override
	public final double get$heads1() {
		return 0.5;
	}

	// Getter for heads2.
	@Override
	public final double get$heads2() {
		return 0.6;
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

	// Getter for logProbability$coin1.
	@Override
	public final double get$logProbability$coin1() {
		return logProbability$coin1;
	}

	// Getter for logProbability$coin2.
	@Override
	public final double get$logProbability$coin2() {
		return logProbability$coin2;
	}

	// Getter for logProbability$twoHeads.
	@Override
	public final double get$logProbability$twoHeads() {
		return logProbability$twoHeads;
	}

	// Getter for twoHeads.
	@Override
	public final boolean get$twoHeads() {
		return twoHeads;
	}

	// Setter for twoHeads.
	@Override
	public final void set$twoHeads(boolean calculationVariable$value) {
		twoHeads = calculationVariable$value;
	}

	// Calculate the probability of the samples represented by sample4 using sampled values.
	private final void logProbabilityValue$sample4() {
		// Determine if we need to calculate the values for sample task 4 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample4) {
			// Generating probabilities for sample task
			// Variable declaration of calculationVariable$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of calculationVariable$distributionAccumulator moved.
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
			// Variable declaration of calculationVariable$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(coin1, 0.5);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var3 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$coin1 = calculationVariable$distributionAccumulator;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
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
			logProbability$twoHeads = (logProbability$twoHeads + calculationVariable$distributionAccumulator);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
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
			logProbability$$model = (logProbability$$model + calculationVariable$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample4)
				// Variable declaration of calculationVariable$accumulator moved.
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
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample4 = fixedFlag$sample4;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var3 = logProbability$coin1;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$twoHeads = (logProbability$twoHeads + logProbability$coin1);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$coin1);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample4)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$coin1);
		}
	}

	// Calculate the probability of the samples represented by sample6 using sampled values.
	private final void logProbabilityValue$sample6() {
		// Determine if we need to calculate the values for sample task 6 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample6) {
			// Generating probabilities for sample task
			// Variable declaration of calculationVariable$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of calculationVariable$distributionAccumulator moved.
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
			// Variable declaration of calculationVariable$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double calculationVariable$distributionAccumulator = org.sandwood.runtime.internal.numericTools.DistributionSampling.logProbabilityBernoulli(coin2, 0.6);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var5 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$coin2 = calculationVariable$distributionAccumulator;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
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
			logProbability$twoHeads = (logProbability$twoHeads + calculationVariable$distributionAccumulator);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
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
			logProbability$$model = (logProbability$$model + calculationVariable$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample6)
				// Variable declaration of calculationVariable$accumulator moved.
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
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample6 = fixedFlag$sample6;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var5 = logProbability$coin2;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$twoHeads = (logProbability$twoHeads + logProbability$coin2);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$coin2);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample6)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$coin2);
		}
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
		if(!fixedFlag$sample4)
			coin1 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(!fixedFlag$sample6)
			coin2 = org.sandwood.runtime.internal.numericTools.DistributionSampling.sampleBernoulli(RNG$, 0.6);
		if((!fixedFlag$sample4 || !fixedFlag$sample6))
			twoHeads = (coin1 && coin2);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
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
		logProbability$var3 = 0.0;
		logProbability$twoHeads = 0.0;
		if(!fixedProbFlag$sample4)
			logProbability$coin1 = 0.0;
		logProbability$var5 = 0.0;
		if(!fixedProbFlag$sample6)
			logProbability$coin2 = 0.0;
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
		if(fixedFlag$sample4)
			logProbabilityValue$sample4();
		if(fixedFlag$sample6)
			logProbabilityValue$sample6();
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
		logProbabilityValue$sample4();
		logProbabilityValue$sample6();
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
		logProbabilityValue$sample4();
		logProbabilityValue$sample6();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		// 
		// Generate sample values for every call to sample in the model.
		logModelProbabilitiesVal();
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		twoHeads = (coin1 && coin2);
	}

	@Override
	public String modelCode() {
		return "package coinFlips;\n\n/*\n * % Probabilistic facts:\n * 0.5::heads1.\n * 0.6::heads2.\n *\n * % Rules:\n * twoHeads :- heads1, heads2.\n *\n * % Queries:\n * query(heads1).\n * query(heads2).\n * query(twoHeads).\n */\n\npublic model Flips1() {\n    double heads1 = 0.5;\n    double heads2 = 0.6;\n    boolean coin1 = bernoulli(heads1).sample();\n    boolean coin2 = bernoulli(heads2).sample();\n    boolean twoHeads = coin1 && coin2;\n}";
	}
}