package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip2CoinsMK5b$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip2CoinsMK5b$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private int coins;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample36 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample36 = false;
	private boolean[][] flips;
	private boolean[][] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample20;
	private double[] logProbability$sample36;
	private double[] logProbability$var16;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private int[] shape;
	private boolean system$gibbsForward = true;

	public Flip2CoinsMK5b$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for bias.
	@Override
	public final double[] get$bias() {
		return bias;
	}

	// Setter for bias.
	@Override
	public final void set$bias(double[] calculationVariable$value) {
		// Set bias with flag to mark that it has been set so another array doesn't need to
		// be constructed
		bias = calculationVariable$value;
		setFlag$bias = true;
	}

	// Getter for coins.
	@Override
	public final int get$coins() {
		return coins;
	}

	// Getter for fixedFlag$sample20.
	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	// Setter for fixedFlag$sample20.
	@Override
	public final void set$fixedFlag$sample20(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample20 including if probabilities
		// need to be updated.
		fixedFlag$sample20 = calculationVariable$value;
		
		// Should the probability of sample 20 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample20" with its value "calculationVariable$value".
		fixedProbFlag$sample20 = (calculationVariable$value && fixedProbFlag$sample20);
		
		// Should the probability of sample 36 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample20" with its value "calculationVariable$value".
		fixedProbFlag$sample36 = (calculationVariable$value && fixedProbFlag$sample36);
	}

	// Getter for fixedFlag$sample36.
	@Override
	public final boolean get$fixedFlag$sample36() {
		return fixedFlag$sample36;
	}

	// Setter for fixedFlag$sample36.
	@Override
	public final void set$fixedFlag$sample36(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample36 including if probabilities
		// need to be updated.
		fixedFlag$sample36 = calculationVariable$value;
		
		// Should the probability of sample 36 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample36" with its value "calculationVariable$value".
		fixedProbFlag$sample36 = (calculationVariable$value && fixedProbFlag$sample36);
	}

	// Getter for flips.
	@Override
	public final boolean[][] get$flips() {
		return flips;
	}

	// Setter for flips.
	@Override
	public final void set$flips(boolean[][] calculationVariable$value) {
		// Set flips with flag to mark that it has been set so another array doesn't need
		// to be constructed
		flips = calculationVariable$value;
		setFlag$flips = true;
	}

	// Getter for flipsMeasured.
	@Override
	public final boolean[][] get$flipsMeasured() {
		return flipsMeasured;
	}

	// Setter for flipsMeasured.
	@Override
	public final void set$flipsMeasured(boolean[][] calculationVariable$value) {
		// Set flipsMeasured with flag to mark that it has been set so another array doesn't
		// need to be constructed
		flipsMeasured = calculationVariable$value;
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

	// Getter for shape.
	@Override
	public final int[] get$shape() {
		return shape;
	}

	// Setter for shape.
	@Override
	public final void set$shape(int[] calculationVariable$value) {
		// Set shape with flag to mark that it has been set so another array doesn't need
		// to be constructed
		shape = calculationVariable$value;
	}

	// Calculate the probability of the samples represented by sample20 using sampled
	// values.
	private final void logProbabilityValue$sample20() {
		// Determine if we need to calculate the values for sample task 20 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample20) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double calculationVariable$accumulator = 0.0;
			for(int i=0; i<coins; i+=1) {
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
				double calculationVariable$distributionAccumulator = DistributionSampling.logProbabilityBeta(bias[i], 1.0, 1.0);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$distributionAccumulator);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$var16[i] = calculationVariable$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample20[i] = calculationVariable$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + calculationVariable$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample20 = fixedFlag$sample20;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			for(int i=0; i<coins; i+=1) {
				// Variable declaration of calculationVariable$RVaccumulator moved.
				double calculationVariable$RVaccumulator = logProbability$sample20[i];
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
				logProbability$var16[i] = calculationVariable$RVaccumulator;
			}
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + calculationVariable$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample36 using sampled
	// values.
	private final void logProbabilityValue$sample36() {
		// Determine if we need to calculate the values for sample task 36 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample36) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double calculationVariable$accumulator = 0.0;
			for(int j=0; j<coins; j+=1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double calculationVariable$sampleAccumulator = 0.0;
				for(int var32=0; var32<shape[j]; var32+=1)
					// Add the probability of this sample task to the sample task accumulator.
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
					calculationVariable$sampleAccumulator = (calculationVariable$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flipsMeasured[j][var32], bias[(coins - (j + 1))]));
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$sampleAccumulator);
				logProbability$bernoulli[j] = calculationVariable$sampleAccumulator;
				
				// Store the random variable instance probability
				logProbability$sample36[j] = calculationVariable$sampleAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + calculationVariable$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample36 = (fixedFlag$sample36 && fixedFlag$sample20);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			for(int j=0; j<coins; j+=1) {
				// Variable declaration of calculationVariable$RVaccumulator moved.
				double calculationVariable$RVaccumulator = logProbability$sample36[j];
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
				logProbability$bernoulli[j] = calculationVariable$RVaccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + calculationVariable$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 20 drawn from Beta 16. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample20(int i, int threadID$calculationVariable$i, Rng RNG$) {
		// Local variable to record the number of true samples.
		int calculationVariable$Sum = 0;
		
		// Local variable to record the number of samples.
		int calculationVariable$Count = 0;
		
		// Processing random variable 28.
		// 
		// Looking for a path between Sample 20 and consumer Bernoulli 28.
		int j = (coins - (i + 1));
		if((0 <= j)) {
			// Processing sample task 36 of consumer random variable bernoulli.
			for(int var32=0; var32<shape[j]; var32+=1) {
				// Include the value sampled by task 36 from random variable bernoulli.
				// Increment the number of samples.
				calculationVariable$Count = (calculationVariable$Count + 1);
				
				// If the sample value was positive increase the count
				if(flipsMeasured[j][var32])
					calculationVariable$Sum = (calculationVariable$Sum + 1);
			}
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		bias[i] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, calculationVariable$Sum, calculationVariable$Count);
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
			bias = new double[shape.length];
		
		// If flips has not been set already allocate space.
		if(!setFlag$flips) {
			// Constructor for flips
			flips = new boolean[shape.length][];
			for(int j=0; j<shape.length; j+=1)
				flips[j] = new boolean[shape[j]];
		}
		
		// Constructor for logProbability$var16
		logProbability$var16 = new double[shape.length];
		
		// Constructor for logProbability$sample20
		logProbability$sample20 = new double[shape.length];
		
		// Constructor for logProbability$bernoulli
		logProbability$bernoulli = new double[shape.length];
		
		// Constructor for logProbability$sample36
		logProbability$sample36 = new double[shape.length];
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample20)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i=forStart$i; i<forEnd$i; i+=1)
							bias[i] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample36)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$j=forStart$index$j; index$j<forEnd$index$j; index$j+=1) {
							int j = index$j;
							boolean[] var29 = flips[j];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, shape[j], 1,
								(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int var32=forStart$var32; var32<forEnd$var32; var32+=1)
											var29[var32] = DistributionSampling.sampleBernoulli(RNG$2, bias[(coins - (j + 1))]);
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
		if(!fixedFlag$sample20)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i=forStart$i; i<forEnd$i; i+=1)
							bias[i] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample20)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i=forStart$i; i<forEnd$i; i+=1)
							bias[i] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(!fixedFlag$sample20) {
			// Infer the samples in chronological order.
			if(system$gibbsForward)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, coins, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int i=forStart$i; i<forEnd$i; i+=1)
								sample20(i, threadID$i, RNG$1);
					}
				);
			// Infer the samples in reverse chronological order.
			else
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, coins, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int i=forStart$i; i<forEnd$i; i+=1)
								sample20(i, threadID$i, RNG$1);
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
		for(int i=0; i<coins; i+=1)
			logProbability$var16[i] = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample20) {
			for(int i=0; i<coins; i+=1)
				logProbability$sample20[i] = 0.0;
		}
		for(int j=0; j<coins; j+=1)
			logProbability$bernoulli[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample36) {
			for(int j=0; j<coins; j+=1)
				logProbability$sample36[j] = 0.0;
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
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		logProbabilityValue$sample36();
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
		logProbabilityValue$sample20();
		logProbabilityValue$sample36();
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
		logProbabilityValue$sample20();
		logProbabilityValue$sample36();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample20)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i=forStart$i; i<forEnd$i; i+=1)
							bias[i] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip2CoinsMK5b(boolean[][] flipsMeasured, int[] shape) {\n     \n    int coins = shape.length;\n    double[] bias = new double[coins];\n    for(int i:[0..coins))\n      bias[i] = beta(1.0, 1.0).sample();\n        \n    boolean[][] flips = new boolean[coins][];\n        \n    for(int j:[0..coins)) {\n        int samples = shape[j];\n        Bernoulli bernoulli = bernoulli(bias[coins-(j+1)]);\n        flips[j] = bernoulli.sample(samples);\n    }\n        \n    flips.observe(flipsMeasured);\n}\n\n";
	}
}