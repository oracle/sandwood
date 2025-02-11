package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip2CoinsMK7$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip2CoinsMK7$CoreInterface {
	
	// Declare the variables for the model.
	private double a;
	private double b;
	private double[] bias;
	private int coins;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean[][] flips;
	private boolean[][] flipsMeasured;
	private int[] length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$var14;
	private double logProbability$var15;
	private double logProbability$var31;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;

	public Flip2CoinsMK7$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for a.
	@Override
	public final double get$a() {
		return a;
	}

	// Setter for a.
	@Override
	public final void set$a(double cv$value) {
		a = cv$value;
	}

	// Getter for b.
	@Override
	public final double get$b() {
		return b;
	}

	// Setter for b.
	@Override
	public final void set$b(double cv$value) {
		b = cv$value;
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
		// Set bias with flag to mark that it has been set so another array doesn't need to
		// be constructed
		bias = cv$value;
		setFlag$bias = true;
		
		// Unset the fixed probability flag for sample 17 as it depends on bias.
		fixedProbFlag$sample17 = false;
		
		// Unset the fixed probability flag for sample 34 as it depends on bias.
		fixedProbFlag$sample34 = false;
	}

	// Getter for coins.
	@Override
	public final int get$coins() {
		return coins;
	}

	// Getter for fixedFlag$sample17.
	@Override
	public final boolean get$fixedFlag$sample17() {
		return fixedFlag$sample17;
	}

	// Setter for fixedFlag$sample17.
	@Override
	public final void set$fixedFlag$sample17(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample17 including if probabilities
		// need to be updated.
		fixedFlag$sample17 = cv$value;
		
		// Should the probability of sample 17 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample17" with its value "cv$value".
		fixedProbFlag$sample17 = (cv$value && fixedProbFlag$sample17);
		
		// Should the probability of sample 34 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample17" with its value "cv$value".
		fixedProbFlag$sample34 = (cv$value && fixedProbFlag$sample34);
	}

	// Getter for fixedFlag$sample34.
	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	// Setter for fixedFlag$sample34.
	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample34 including if probabilities
		// need to be updated.
		fixedFlag$sample34 = cv$value;
		
		// Should the probability of sample 34 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample34" with its value "cv$value".
		fixedProbFlag$sample34 = (cv$value && fixedProbFlag$sample34);
	}

	// Getter for flips.
	@Override
	public final boolean[][] get$flips() {
		return flips;
	}

	// Setter for flips.
	@Override
	public final void set$flips(boolean[][] cv$value) {
		// Set flags for all the side effects of flips including if probabilities need to
		// be updated.
		// Set flips with flag to mark that it has been set so another array doesn't need
		// to be constructed
		flips = cv$value;
		setFlag$flips = true;
		
		// Unset the fixed probability flag for sample 34 as it depends on flips.
		fixedProbFlag$sample34 = false;
	}

	// Getter for flipsMeasured.
	@Override
	public final boolean[][] get$flipsMeasured() {
		return flipsMeasured;
	}

	// Setter for flipsMeasured.
	@Override
	public final void set$flipsMeasured(boolean[][] cv$value) {
		// Set flipsMeasured with flag to mark that it has been set so another array doesn't
		// need to be constructed
		flipsMeasured = cv$value;
	}

	// Getter for length$flipsMeasured.
	@Override
	public final int[] get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	// Setter for length$flipsMeasured.
	@Override
	public final void set$length$flipsMeasured(int[] cv$value) {
		// Set length$flipsMeasured with flag to mark that it has been set so another array
		// doesn't need to be constructed
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

	// Calculate the probability of the samples represented by sample17 using sampled
	// values.
	private final void logProbabilityValue$sample17() {
		// Determine if we need to calculate the values for sample task 17 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample17) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i = 0; i < coins; i += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta((1 - bias[i]), a, b));
			logProbability$var14 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var15 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample17)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample17 = fixedFlag$sample17;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var14 = logProbability$var15;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$bias = (logProbability$bias + logProbability$var15);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var15);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample17)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var15);
		}
	}

	// Calculate the probability of the samples represented by sample34 using sampled
	// values.
	private final void logProbabilityValue$sample34() {
		// Determine if we need to calculate the values for sample task 34 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample34) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int j = 0; j < coins; j += 1) {
				for(int var30 = 0; var30 < length$flipsMeasured[j]; var30 += 1)
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
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[j][var30], bias[j]));
			}
			logProbability$bernoulli = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var31 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample34 = (fixedFlag$sample34 && fixedFlag$sample17);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$bernoulli = logProbability$var31;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$flips = (logProbability$flips + logProbability$var31);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var31);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var31);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 17 drawn from Beta 14. Inference was performed using Metropolis-Hastings.
	private final void sample17(int i, int threadID$cv$i, Rng RNG$) {
		// The original value of the sample
		double cv$originalValue = (1 - bias[i]);
		
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
			// Substituted "cv$temp$1$b" with its value "b".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$originalValue, a, b);
			
			// Processing random variable 26.
			// 
			// Looking for a path between Sample 17 and consumer Bernoulli 26.
			// 
			// Set the current value to the current state of the tree.
			double traceTempVariable$var25$1_1 = (1 - cv$originalValue);
			
			// Processing sample task 34 of consumer random variable bernoulli.
			// 
			// Substituted "j" with its value "i".
			for(int var30 = 0; var30 < length$flipsMeasured[i]; var30 += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 34 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "j" with its value "i".
				// 
				// cv$temp$2$var25's comment
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[i][var30], traceTempVariable$var25$1_1) + cv$accumulatedProbabilities);
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Update Sample and intermediate values
		bias[i] = (1 - cv$proposedValue);
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$b" with its value "b".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, a, b);
		
		// Processing random variable 26.
		// 
		// Looking for a path between Sample 17 and consumer Bernoulli 26.
		double traceTempVariable$var25$1_1 = (1 - cv$proposedValue);
		
		// Processing sample task 34 of consumer random variable bernoulli.
		// 
		// Substituted "j" with its value "i".
		for(int var30 = 0; var30 < length$flipsMeasured[i]; var30 += 1)
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 34 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "j" with its value "i".
			// 
			// cv$temp$2$var25's comment
			// Constructing a random variable input for use later.
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[i][var30], traceTempVariable$var25$1_1) + cv$accumulatedProbabilities);
		
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
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			bias[i] = (1 - cv$originalValue);
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
		if(!setFlag$bias)
			// Constructor for bias
			bias = new double[length$flipsMeasured.length];
		
		// If flips has not been set already allocate space.
		if(!setFlag$flips) {
			// Constructor for flips
			flips = new boolean[length$flipsMeasured.length][];
			for(int j = 0; j < length$flipsMeasured.length; j += 1)
				flips[j] = new boolean[length$flipsMeasured[j]];
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample17)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							bias[i] = (1 - DistributionSampling.sampleBeta(RNG$1, a, b));
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample34)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
							int j = index$j;
							boolean[] var27 = flips[j];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, length$flipsMeasured[j], 1,
								(int forStart$var30, int forEnd$var30, int threadID$var30, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var30 = forStart$var30; var30 < forEnd$var30; var30 += 1)
											var27[var30] = DistributionSampling.sampleBernoulli(RNG$2, bias[j]);
								}
							);
						}
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample17)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							bias[i] = (1 - DistributionSampling.sampleBeta(RNG$1, a, b));
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample17)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							bias[i] = (1 - DistributionSampling.sampleBeta(RNG$1, a, b));
				}
			);

	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(!fixedFlag$sample17) {
			// Infer the samples in chronological order.
			if(system$gibbsForward)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, coins, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int i = forStart$i; i < forEnd$i; i += 1)
								sample17(i, threadID$i, RNG$1);
					}
				);
			// Infer the samples in reverse chronological order.
			else
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, coins, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int i = forStart$i; i < forEnd$i; i += 1)
								sample17(i, threadID$i, RNG$1);
					}
				);
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		coins = length$flipsMeasured.length;
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
		logProbability$var14 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$var15 = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample34)
			logProbability$var31 = 0.0;
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
		if(fixedFlag$sample17)
			logProbabilityValue$sample17();
		logProbabilityValue$sample34();
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
		logProbabilityValue$sample17();
		logProbabilityValue$sample34();
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
		logProbabilityValue$sample17();
		logProbabilityValue$sample34();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample17)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							bias[i] = (1 - DistributionSampling.sampleBeta(RNG$1, a, b));
				}
			);

		
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
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[] cv$source2 = flipsMeasured[cv$index1];
			boolean[] cv$target2 = flips[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip2CoinsMK7(double a, double b, boolean[][] flipsMeasured) {\n     \n    int coins = flipsMeasured.length;\n    double[] bias = new double[coins];\n    for(int i:[0..coins)) \n        bias[i] = 1 - beta(a, b).sample();\n                \n    boolean[][] flips = new boolean[coins][];\n        \n    for(int j:[0..coins)) {\n        int samples = flipsMeasured[j].length;\n        Bernoulli bernoulli = bernoulli(bias[j]);\n        flips[j] = bernoulli.sample(samples);\n    }\n\n    flips.observe(flipsMeasured);\n}\n";
	}
}