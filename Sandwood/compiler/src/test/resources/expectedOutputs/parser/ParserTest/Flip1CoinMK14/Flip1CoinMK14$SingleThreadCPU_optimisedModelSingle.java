package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK14$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip1CoinMK14$CoreInterface {
	
	// Declare the variables for the model.
	private double b;
	private double bias;
	private double[] c;
	private boolean fixedFlag$sample8 = false;
	private boolean fixedProbFlag$sample37 = false;
	private boolean fixedProbFlag$sample8 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private boolean guard1;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$c;
	private double logProbability$flips;
	private double logProbability$sample8;
	private double logProbability$var35;
	private double logProbability$var7;
	private int samples;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK14$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for b.
	@Override
	public final double get$b() {
		return b;
	}

	// Setter for b.
	@Override
	public final void set$b(double cv$value) {
		// Set flags for all the side effects of b including if probabilities need to be updated.
		b = cv$value;
		
		// Unset the fixed probability flag for sample 8 as it depends on b.
		fixedProbFlag$sample8 = false;
		
		// Unset the fixed probability flag for sample 37 as it depends on b.
		fixedProbFlag$sample37 = false;
	}

	// Getter for bias.
	@Override
	public final double get$bias() {
		return bias;
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
		
		// Should the probability of sample 37 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample8" with its value "cv$value".
		fixedProbFlag$sample37 = (cv$value && fixedProbFlag$sample37);
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

	// Getter for guard1.
	@Override
	public final boolean get$guard1() {
		return guard1;
	}

	// Setter for guard1.
	@Override
	public final void set$guard1(boolean cv$value) {
		guard1 = cv$value;
	}

	// Getter for length$flipsMeasured.
	@Override
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	// Setter for length$flipsMeasured.
	@Override
	public final void set$length$flipsMeasured(int cv$value) {
		length$flipsMeasured = cv$value;
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

	// Getter for logProbability$b.
	@Override
	public final double get$logProbability$b() {
		return logProbability$b;
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

	// Calculate the probability of the samples represented by sample37 using sampled
	// values.
	private final void logProbabilityValue$sample37() {
		// Determine if we need to calculate the values for sample task 37 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample37) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var34 = 0; var34 < samples; var34 += 1) {
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
				cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((flips[var34]?bias:(1.0 - bias))));
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(cv$sampleReached) {
				logProbability$bernoulli = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				logProbability$var35 = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample37 = fixedFlag$sample8;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if((0 < samples))
				// Record that the sample was reached.
				cv$sampleReached = true;
			if(cv$sampleReached)
				logProbability$bernoulli = logProbability$var35;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$flips = (logProbability$flips + logProbability$var35);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var35);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var35);
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(b, 1.0, 1.0);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var7 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$sample8 = cv$distributionAccumulator;
			
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
			logProbability$b = (logProbability$b + cv$distributionAccumulator);
			
			// Add probability to constructed variables that have guards, so need per sample probabilities
			// from the combined probability
			// 
			// Looking for a path between Sample 8 and consumer double 22.
			if(guard1)
				// Update the variable probability
				logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				// Update the variable probability
				logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
				
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
				logProbability$c = (logProbability$c + cv$distributionAccumulator);
			}
			
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
			logProbability$var7 = logProbability$sample8;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$b = (logProbability$b + logProbability$sample8);
			
			// Add probability to constructed variables that have guards, so need per sample probabilities
			// from the combined probability
			// 
			// Looking for a path between Sample 8 and consumer double 22.
			if(guard1)
				// Update the variable probability
				logProbability$bias = (logProbability$bias + logProbability$sample8);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				// Update the variable probability
				logProbability$bias = (logProbability$bias + logProbability$sample8);
				
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$c = (logProbability$c + logProbability$sample8);
			}
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$sample8);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample8)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$sample8);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 8 drawn from Beta 7. Inference was performed using Metropolis-Hastings.
	private final void sample8() {
		// The original value of the sample
		double cv$originalValue = b;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		// 
		// The original value of the sample
		double cv$var = ((b * b) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		// 
		// The original value of the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + b);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var6" with its value "1.0".
			// 
			// Set the current value to the current state of the tree.
			// 
			// The original value of the sample
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(b, 1.0, 1.0);
			
			// Processing random variable 23.
			// 
			// Looking for a path between Sample 8 and consumer Bernoulli 23.
			if(guard1) {
				// Processing sample task 37 of consumer random variable bernoulli.
				for(int var34 = 0; var34 < samples; var34 += 1)
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 37 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$2$bias" with its value "cv$currentValue".
					// 
					// Set the current value to the current state of the tree.
					// 
					// The original value of the sample
					cv$accumulatedProbabilities = (Math.log((flips[var34]?b:(1.0 - b))) + cv$accumulatedProbabilities);
			} else {
				// Set the current value to the current state of the tree.
				// 
				// The original value of the sample
				double traceTempVariable$var21$5_2 = (b / 2);
				
				// Processing sample task 37 of consumer random variable bernoulli.
				for(int var34 = 0; var34 < samples; var34 += 1)
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 37 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "cv$temp$3$bias" with its value "traceTempVariable$var21$5_2".
					cv$accumulatedProbabilities = (Math.log((flips[var34]?traceTempVariable$var21$5_2:(1.0 - traceTempVariable$var21$5_2))) + cv$accumulatedProbabilities);
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
		b = cv$proposedValue;
		
		// Guards to ensure that bias is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 8 and consumer double 22.
		if(guard1)
			bias = cv$proposedValue;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			c[0] = (cv$proposedValue / 2);
			bias = c[0];
		}
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var6" with its value "1.0".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
		
		// Processing random variable 23.
		// 
		// Looking for a path between Sample 8 and consumer Bernoulli 23.
		if(guard1) {
			// Processing sample task 37 of consumer random variable bernoulli.
			for(int var34 = 0; var34 < samples; var34 += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 37 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$2$bias" with its value "cv$currentValue".
				cv$accumulatedProbabilities = (Math.log((flips[var34]?cv$proposedValue:(1.0 - cv$proposedValue))) + cv$accumulatedProbabilities);
		} else {
			double traceTempVariable$var21$5_2 = (cv$proposedValue / 2);
			
			// Processing sample task 37 of consumer random variable bernoulli.
			for(int var34 = 0; var34 < samples; var34 += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 37 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "cv$temp$3$bias" with its value "traceTempVariable$var21$5_2".
				cv$accumulatedProbabilities = (Math.log((flips[var34]?traceTempVariable$var21$5_2:(1.0 - traceTempVariable$var21$5_2))) + cv$accumulatedProbabilities);
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
			b = cv$originalValue;
			
			// Guards to ensure that bias is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 8 and consumer double 22.
			if(guard1)
				// b's comment
				// Write out the new value of the sample.
				bias = cv$originalValue;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				// b's comment
				// Write out the new value of the sample.
				c[0] = (cv$originalValue / 2);
				bias = c[0];
			}
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!guard1)
			c = new double[1];
		
		// Constructor for flips
		flips = new boolean[length$flipsMeasured];
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample8) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(guard1)
				bias = b;
			else {
				c[0] = (b / 2);
				bias = c[0];
			}
		}
		for(int var34 = 0; var34 < samples; var34 += 1)
			flips[var34] = DistributionSampling.sampleBernoulli(RNG$, bias);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample8) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(guard1)
				bias = b;
			else {
				c[0] = (b / 2);
				bias = c[0];
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample8) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(guard1)
				bias = b;
			else {
				c[0] = (b / 2);
				bias = c[0];
			}
		}
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
	public final void initializeConstants() {
		samples = length$flipsMeasured;
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
		logProbability$var7 = 0.0;
		logProbability$b = 0.0;
		logProbability$c = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample8)
			logProbability$sample8 = Double.NaN;
		logProbability$bernoulli = Double.NaN;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample37)
			logProbability$var35 = Double.NaN;
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
		logProbabilityValue$sample37();
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
		logProbabilityValue$sample37();
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
		logProbabilityValue$sample37();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample8) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(guard1)
				bias = b;
			else {
				c[0] = (b / 2);
				bias = c[0];
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
		for(int i = (samples - 1); i >= 0; i -= 1)
			flips[i] = flipsMeasured[i];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		if(fixedFlag$sample8) {
			if(guard1)
				bias = b;
			else {
				c[0] = (b / 2);
				bias = c[0];
			}
		}
	}

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
		     + "public model Flip1CoinMK14(boolean[] flipsMeasured, boolean guard1) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    double b = beta(1.0, 1).sample();\n"
		     + "    double bias;\n"
		     + "    if(guard1)\n"
		     + "      bias = b;\n"
		     + "    else {\n"
		     + "      double[] c = new double[1];\n"
		     + "      c[0] = b/2;\n"
		     + "      bias = c[0];\n"
		     + "    }\n"
		     + "        \n"
		     + "    Bernoulli bernoulli = bernoulli(bias);\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "\n"
		     + "    for(int i:[0..samples))\n"
		     + "        flips[i].observe(flipsMeasured[i]);\n"
		     + "}\n"
		     + "";
	}
}