package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ParallelMK2$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements ParallelMK2$CoreInterface {
	
	// Declare the variables for the model.
	private boolean fixedFlag$sample26 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean fixedProbFlag$sample32 = false;
	private double[] generated;
	private boolean[] guard$sample26gaussian31$global;
	private double[] indirection;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$generated;
	private double logProbability$indirection;
	private double logProbability$sample;
	private double logProbability$var25;
	private double logProbability$var31;
	private double logProbability$var32;
	private double[] observed;
	private double[] sample;
	private boolean system$gibbsForward = true;

	public ParallelMK2$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for fixedFlag$sample26.
	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	// Setter for fixedFlag$sample26.
	@Override
	public final void set$fixedFlag$sample26(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample26 including if probabilities
		// need to be updated.
		fixedFlag$sample26 = cv$value;
		
		// Should the probability of sample 26 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample26" with its value "cv$value".
		fixedProbFlag$sample26 = (cv$value && fixedProbFlag$sample26);
		
		// Should the probability of sample 32 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample26" with its value "cv$value".
		fixedProbFlag$sample32 = (cv$value && fixedProbFlag$sample32);
	}

	// Getter for generated.
	@Override
	public final double[] get$generated() {
		return generated;
	}

	// Getter for indirection.
	@Override
	public final double[] get$indirection() {
		return indirection;
	}

	// Setter for indirection.
	@Override
	public final void set$indirection(double[] cv$value) {
		// Set indirection
		indirection = cv$value;
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

	// Getter for logProbability$generated.
	@Override
	public final double get$logProbability$generated() {
		return logProbability$generated;
	}

	// Getter for logProbability$indirection.
	@Override
	public final double get$logProbability$indirection() {
		return logProbability$indirection;
	}

	// Getter for logProbability$sample.
	@Override
	public final double get$logProbability$sample() {
		return logProbability$sample;
	}

	// Getter for observed.
	@Override
	public final double[] get$observed() {
		return observed;
	}

	// Setter for observed.
	@Override
	public final void set$observed(double[] cv$value) {
		// Set observed
		observed = cv$value;
	}

	// Getter for sample.
	@Override
	public final double[] get$sample() {
		return sample;
	}

	// Setter for sample.
	@Override
	public final void set$sample(double[] cv$value) {
		// Set sample
		sample = cv$value;
	}

	// Calculate the probability of the samples represented by sample26 using sampled
	// values.
	private final void logProbabilityValue$sample26() {
		// Determine if we need to calculate the values for sample task 26 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample26) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i = 0; i < length$observed; i += 1) {
				// The sample value to calculate the probability of generating
				double cv$sampleValue = sample[i];
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < 1.0))?0.0:Double.NEGATIVE_INFINITY));
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(cv$sampleReached) {
				logProbability$var25 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$sample = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$indirection = (logProbability$indirection + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample26)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample26 = fixedFlag$sample26;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if((0 < length$observed))
				// Record that the sample was reached.
				cv$sampleReached = true;
			if(cv$sampleReached)
				logProbability$var25 = logProbability$sample;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$indirection = (logProbability$indirection + logProbability$sample);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$sample);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample26)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$sample);
		}
	}

	// Calculate the probability of the samples represented by sample32 using sampled
	// values.
	private final void logProbabilityValue$sample32() {
		// Determine if we need to calculate the values for sample task 32 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample32) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i = 0; i < length$observed; i += 1) {
				double var30 = indirection[i];
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((generated[i] - sample[i]) / Math.sqrt(var30)))) - (Math.log(var30) * 0.5));
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(cv$sampleReached) {
				logProbability$var31 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var32 = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$generated = (logProbability$generated + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample32 = fixedFlag$sample26;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if((0 < length$observed))
				// Record that the sample was reached.
				cv$sampleReached = true;
			if(cv$sampleReached)
				logProbability$var31 = logProbability$var32;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$generated = (logProbability$generated + logProbability$var32);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var32);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var32);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 26 drawn from Uniform 25. Inference was performed using Metropolis-Hastings.
	private final void sample26(int i) {
		// The original value of the sample
		double cv$originalValue = sample[i];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$var23" with its value "0.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			guard$sample26gaussian31$global[i] = false;
			
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			if(!guard$sample26gaussian31$global[i]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample26gaussian31$global[i] = true;
				
				// Variable declaration of cv$temp$3$var30 moved.
				// 
				// Constructing a random variable input for use later.
				double cv$temp$3$var30 = indirection[i];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 32 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 32 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$2$sample" with its value "cv$currentValue".
				// 
				// Set the current value to the current state of the tree.
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((generated[i] - cv$originalValue) / Math.sqrt(cv$temp$3$var30))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$3$var30) * 0.5));
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!guard$sample26gaussian31$global[i]) {
				int index$i$5_2 = (i + 1);
				if((index$i$5_2 < length$observed))
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 32 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 32 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$4$sample" with its value "cv$currentValue".
					// 
					// Set the current value to the current state of the tree.
					// 
					// cv$temp$5$var30's comment
					// Variable declaration of cv$temp$5$var30 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Set the current value to the current state of the tree.
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((generated[index$i$5_2] - cv$originalValue) / Math.sqrt(cv$originalValue))) + cv$accumulatedProbabilities) - (Math.log(cv$originalValue) * 0.5));
			}
			
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
		sample[i] = cv$proposedValue;
		
		// Guards to ensure that indirection is only updated when there is a valid path.
		indirection[(i + 1)] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$var23" with its value "0.0".
		double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue < 1.0))?0.0:Double.NEGATIVE_INFINITY);
		
		// Set the flags to false
		// 
		// Guard to check that at most one copy of the code is executed for a given random
		// variable instance.
		guard$sample26gaussian31$global[i] = false;
		
		// Guard to check that at most one copy of the code is executed for a given random
		// variable instance.
		if(!guard$sample26gaussian31$global[i]) {
			// The body will execute, so should not be executed again
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			guard$sample26gaussian31$global[i] = true;
			
			// Variable declaration of cv$temp$3$var30 moved.
			// 
			// Constructing a random variable input for use later.
			double cv$temp$3$var30 = indirection[i];
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 32 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Processing sample task 32 of consumer random variable null.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "cv$temp$2$sample" with its value "cv$currentValue".
			cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((generated[i] - cv$proposedValue) / Math.sqrt(cv$temp$3$var30))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$3$var30) * 0.5));
		}
		int index$i$5_2 = (i + 1);
		if(((index$i$5_2 < length$observed) && !guard$sample26gaussian31$global[i])) {
			// The body will execute, so should not be executed again
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			guard$sample26gaussian31$global[i] = true;
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 32 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Processing sample task 32 of consumer random variable null.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "cv$temp$4$sample" with its value "cv$currentValue".
			// 
			// cv$temp$5$var30's comment
			// Variable declaration of cv$temp$5$var30 moved.
			// 
			// Constructing a random variable input for use later.
			cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((generated[index$i$5_2] - cv$proposedValue) / Math.sqrt(cv$proposedValue))) + cv$accumulatedProbabilities) - (Math.log(cv$proposedValue) * 0.5));
		}
		
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
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			// If it is not revert the changes.
			// 
			// Set the sample value
			// Write out the new value of the sample.
			sample[i] = cv$originalValue;
			
			// Guards to ensure that indirection is only updated when there is a valid path.
			indirection[(i + 1)] = sample[i];
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Constructor for guard$sample26gaussian31$global
		// 
		// Allocate scratch space.
		// 
		// Allocation of guard$sample26gaussian31$global for single threaded execution
		guard$sample26gaussian31$global = new boolean[length$observed];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for generated
		generated = new double[length$observed];
		
		// Constructor for indirection
		indirection = new double[(length$observed + 1)];
		
		// If sample has not been set already allocate space.
		if(!fixedFlag$sample26)
			// Constructor for sample
			sample = new double[length$observed];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		for(int i = 0; i < length$observed; i += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample26) {
				sample[i] = DistributionSampling.sampleUniform(RNG$);
				indirection[(i + 1)] = sample[i];
			}
			generated[i] = ((Math.sqrt(indirection[i]) * DistributionSampling.sampleGaussian(RNG$)) + sample[i]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample26) {
			for(int i = 0; i < length$observed; i += 1) {
				sample[i] = DistributionSampling.sampleUniform(RNG$);
				indirection[(i + 1)] = sample[i];
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample26) {
			for(int i = 0; i < length$observed; i += 1) {
				sample[i] = DistributionSampling.sampleUniform(RNG$);
				indirection[(i + 1)] = sample[i];
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(!fixedFlag$sample26) {
			// Infer the samples in chronological order.
			if(system$gibbsForward) {
				for(int i = 0; i < length$observed; i += 1)
					sample26(i);
			}
			// Infer the samples in reverse chronological order.
			else {
				for(int i = (length$observed - 1); i >= 0; i -= 1)
					sample26(i);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		indirection[0] = 1.0;
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
		logProbability$var25 = Double.NaN;
		logProbability$indirection = 0.0;
		if(!fixedProbFlag$sample26)
			logProbability$sample = Double.NaN;
		logProbability$var31 = Double.NaN;
		logProbability$generated = 0.0;
		if(!fixedProbFlag$sample32)
			logProbability$var32 = Double.NaN;
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
		if(fixedFlag$sample26)
			logProbabilityValue$sample26();
		logProbabilityValue$sample32();
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
		logProbabilityValue$sample26();
		logProbabilityValue$sample32();
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
		logProbabilityValue$sample26();
		logProbabilityValue$sample32();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample26) {
			for(int i = 0; i < length$observed; i += 1) {
				sample[i] = DistributionSampling.sampleUniform(RNG$);
				indirection[(i + 1)] = sample[i];
			}
		}
		
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
		int cv$length1 = generated.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			generated[cv$index1] = observed[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample26) {
			for(int i = 0; i < length$observed; i += 1)
				indirection[(i + 1)] = sample[i];
		}
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model ParallelMK2(double[] observed) {\n"
		     + "    double[] generated = new double[observed.length];\n"
		     + "    double[] indirection = new double[observed.length + 1];\n"
		     + "    indirection[0] = 1.0;\n"
		     + "\n"
		     + "    for(int i=0; i<observed.length; i++) {\n"
		     + "        double sample = uniform(0.0, 1.0).sample();\n"
		     + "        indirection[i + 1] = sample;\n"
		     + "        generated[i] = gaussian(sample, indirection[i]).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    generated.observe(observed);\n"
		     + "}";
	}
}