package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip2CoinsMK10$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip2CoinsMK10$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private int coins;
	private boolean fixedFlag$sample10 = false;
	private boolean fixedFlag$sample23 = false;
	private boolean fixedProbFlag$sample10 = false;
	private boolean fixedProbFlag$sample23 = false;
	private boolean fixedProbFlag$sample48 = false;
	private boolean[][] flips;
	private boolean[][] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli;
	private double logProbability$beta;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample48;
	private double logProbability$var10;
	private double logProbability$var23;
	private int[] shape;
	private boolean system$gibbsForward = true;

	public Flip2CoinsMK10$MultiThreadCPU(ExecutionTarget target) {
		super(target);
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
		
		// Unset the fixed probability flag for sample 23 as it depends on bias.
		fixedProbFlag$sample23 = false;
		
		// Unset the fixed probability flag for sample 48 as it depends on bias.
		fixedProbFlag$sample48 = false;
	}

	// Getter for coins.
	@Override
	public final int get$coins() {
		return coins;
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
		
		// Should the probability of sample 48 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample10" with its value "cv$value".
		fixedProbFlag$sample48 = (cv$value && fixedProbFlag$sample48);
	}

	// Getter for fixedFlag$sample23.
	@Override
	public final boolean get$fixedFlag$sample23() {
		return fixedFlag$sample23;
	}

	// Setter for fixedFlag$sample23.
	@Override
	public final void set$fixedFlag$sample23(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample23 including if probabilities
		// need to be updated.
		fixedFlag$sample23 = cv$value;
		
		// Should the probability of sample 23 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample23" with its value "cv$value".
		fixedProbFlag$sample23 = (cv$value && fixedProbFlag$sample23);
		
		// Should the probability of sample 48 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample23" with its value "cv$value".
		fixedProbFlag$sample48 = (cv$value && fixedProbFlag$sample48);
	}

	// Getter for flips.
	@Override
	public final boolean[][] get$flips() {
		return flips;
	}

	// Getter for flipsMeasured.
	@Override
	public final boolean[][] get$flipsMeasured() {
		return flipsMeasured;
	}

	// Setter for flipsMeasured.
	@Override
	public final void set$flipsMeasured(boolean[][] cv$value) {
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

	// Getter for logProbability$beta.
	@Override
	public final double get$logProbability$beta() {
		return logProbability$beta;
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

	// Getter for shape.
	@Override
	public final int[] get$shape() {
		return shape;
	}

	// Setter for shape.
	@Override
	public final void set$shape(int[] cv$value) {
		// Set shape
		shape = cv$value;
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
			logProbability$beta = (logProbability$beta + cv$distributionAccumulator);
			
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
			logProbability$beta = (logProbability$beta + logProbability$var10);
			
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

	// Calculate the probability of the samples represented by sample23 using sampled
	// values.
	private final void logProbabilityValue$sample23() {
		// Determine if we need to calculate the values for sample task 23 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample23) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i = 1; i < coins; i += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(bias[i], 1.0, 1.0));
			logProbability$beta = (logProbability$beta + cv$sampleAccumulator);
			
			// Store the random variable instance probability
			logProbability$var23 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample23)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample23 = fixedFlag$sample23;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$beta = (logProbability$beta + logProbability$var23);
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$bias = (logProbability$bias + logProbability$var23);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var23);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample23)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var23);
		}
	}

	// Calculate the probability of the samples represented by sample48 using sampled
	// values.
	private final void logProbabilityValue$sample48() {
		// Determine if we need to calculate the values for sample task 48 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample48) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < coins; j += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int var47 = 0; var47 < shape[j]; var47 += 1)
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
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[j][var47], bias[j]));
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$bernoulli[j] = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				logProbability$sample48[j] = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample48 = (fixedFlag$sample10 && fixedFlag$sample23);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < coins; j += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample48[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli[j] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 10 drawn from beta. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample10() {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		
		// j's comment
		// Processing random variable 37.
		// 
		// Looking for a path between Sample 10 and consumer Bernoulli 37.
		if((0 < coins)) {
			// Processing sample task 48 of consumer random variable bernoulli.
			// 
			// Substituted "j" with its value "0".
			for(int var47 = 0; var47 < shape[0]; var47 += 1) {
				// Include the value sampled by task 48 from random variable bernoulli.
				// Increment the number of samples.
				cv$count = (cv$count + 1);
				
				// If the sample value was positive increase the count
				// 
				// Substituted "j" with its value "0".
				if(flips[0][var47])
					cv$sum = (cv$sum + 1);
			}
		}
		
		// Guards to ensure that bias is only updated when there is a valid path.
		// 
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		bias[0] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 23 drawn from beta. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample23(int i, int threadID$cv$i, Rng RNG$) {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		
		// Processing sample task 48 of consumer random variable bernoulli.
		// 
		// Substituted "j" with its value "i".
		for(int var47 = 0; var47 < shape[i]; var47 += 1) {
			// Include the value sampled by task 48 from random variable bernoulli.
			// Increment the number of samples.
			cv$count = (cv$count + 1);
			
			// If the sample value was positive increase the count
			// 
			// Substituted "j" with its value "i".
			if(flips[i][var47])
				cv$sum = (cv$sum + 1);
		}
		
		// Guards to ensure that bias is only updated when there is a valid path.
		// 
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		bias[i] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for flips
		flips = new boolean[shape.length][];
		for(int j = 0; j < shape.length; j += 1)
			flips[j] = new boolean[shape[j]];
		
		// If bias has not been set already allocate space.
		if((!fixedFlag$sample10 || !fixedFlag$sample23))
			// Constructor for bias
			bias = new double[shape.length];
		
		// Constructor for logProbability$bernoulli
		logProbability$bernoulli = new double[shape.length];
		
		// Constructor for logProbability$sample48
		logProbability$sample48 = new double[shape.length];
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample10)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample23)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							bias[i] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, coins, 1,
			(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
						int j = index$j;
						int threadID$j = threadID$index$j;
						boolean[] var38 = flips[j];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, shape[j], 1,
							(int forStart$var47, int forEnd$var47, int threadID$var47, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var47 = forStart$var47; var47 < forEnd$var47; var47 += 1)
										var38[var47] = DistributionSampling.sampleBernoulli(RNG$2, bias[j]);
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
		if(!fixedFlag$sample10)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample23)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							bias[i] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample10)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample23)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							bias[i] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample10)
				sample10();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample23)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 1, coins, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int i = forStart$i; i < forEnd$i; i += 1)
								sample23(i, threadID$i, RNG$1);
					}
				);

		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample23)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 1, coins, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int i = forStart$i; i < forEnd$i; i += 1)
								sample23(i, threadID$i, RNG$1);
					}
				);

			if(!fixedFlag$sample10)
				sample10();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		coins = shape.length;
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
		logProbability$beta = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample10)
			logProbability$var10 = 0.0;
		if(!fixedProbFlag$sample23)
			logProbability$var23 = 0.0;
		for(int j = 0; j < coins; j += 1)
			logProbability$bernoulli[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample48) {
			for(int j = 0; j < coins; j += 1)
				logProbability$sample48[j] = 0.0;
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
		if(fixedFlag$sample23)
			logProbabilityValue$sample23();
		logProbabilityValue$sample48();
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
		logProbabilityValue$sample23();
		logProbabilityValue$sample48();
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
		logProbabilityValue$sample23();
		logProbabilityValue$sample48();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample10)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample23)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							bias[i] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
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
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[] cv$source2 = flipsMeasured[cv$index1];
			boolean[] cv$target2 = flips[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
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
		     + "public model Flip2CoinsMK10(boolean[][] flipsMeasured, int[] shape) {\n"
		     + "    int coins = shape.length;\n"
		     + "         \n"
		     + "    boolean[][] flips = new boolean[coins][];\n"
		     + "    double [] bias = new double[coins];\n"
		     + "        \n"
		     + "    Beta beta = beta(1.0, 1.0);\n"
		     + "        \n"
		     + "    bias[0] = beta.sample();\n"
		     + "        \n"
		     + "    for(int i:[1..coins))\n"
		     + "        bias[i] = beta.sample();\n"
		     + "        \n"
		     + "    for(int j:[0..coins)) {\n"
		     + "        int samples = shape[j];\n"
		     + "        Bernoulli bernoulli = bernoulli(bias[j]);\n"
		     + "        flips[j] = bernoulli.sample(samples);\n"
		     + "    }\n"
		     + "        \n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}";
	}
}