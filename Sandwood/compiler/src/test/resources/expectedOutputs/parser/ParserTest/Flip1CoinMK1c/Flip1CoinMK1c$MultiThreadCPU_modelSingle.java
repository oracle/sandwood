package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK1c$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK1c$CoreInterface {
	
	// Declare the variables for the model.
	private double a;
	private double b;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$flips;
	private double logProbability$var19;
	private double logProbability$var5;
	private double logProbability$var6;
	private int samples;
	private boolean system$gibbsForward = true;
	private double var6;

	public Flip1CoinMK1c$MultiThreadCPU(ExecutionTarget target) {
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

	// Getter for logProbability$bernoulli.
	@Override
	public final double get$logProbability$bernoulli() {
		return logProbability$bernoulli;
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

	// Getter for var6.
	@Override
	public final double get$var6() {
		return var6;
	}

	// Setter for var6.
	@Override
	public final void set$var6(double cv$value) {
		var6 = cv$value;
	}

	// Calculate the probability of the samples represented by sample19 using sampled
	// values.
	private final void logProbabilityValue$sample19() {
		// Generating probabilities for sample task
		// Accumulator for probabilities of instances of the random variable
		double cv$accumulator = 0.0;
		
		// Accumulator for sample probabilities for a specific instance of the random variable.
		double cv$sampleAccumulator = 0.0;
		
		// A guard to check if the sample value is ever reached.
		boolean cv$sampleReached = false;
		for(int var18 = 0; var18 < samples; var18 += 1) {
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				// The sample value to calculate the probability of generating
				boolean cv$sampleValue = flips[var18];
				{
					{
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var6:(1.0 - var6))));
						
						// Add the probability of this sample task to the distribution accumulator.
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							// If the second value is -infinity.
							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
								cv$distributionAccumulator = cv$weightedProbability;
							else
								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
						}
						
						// Add the probability of this distribution configuration to the accumulator.
						cv$probabilityReached = (cv$probabilityReached + 1.0);
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				// Return negative infinity if no distribution probability space is reached.
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				// Scale the probability relative to the observed distribution space.
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			
			// Record that the sample was reached.
			cv$sampleReached = true;
			
			// Add the probability of this sample task to the sample task accumulator.
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
		}
		
		// Add the probability of this instance of the random variable to the probability
		// of all instances of the random variable.
		cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
		if(cv$sampleReached)
			logProbability$bernoulli = cv$sampleAccumulator;
		
		// Only update the sample if it was reached, otherwise the NaN will be
		// erroneously over written.
		if(cv$sampleReached)
			// Store the random variable instance probability
			logProbability$var19 = cv$sampleAccumulator;
		
		// Update the variable probability
		logProbability$flips = (logProbability$flips + cv$accumulator);
		
		// Add probability to model
		logProbability$$model = (logProbability$$model + cv$accumulator);
		logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
	}

	// Calculate the probability of the samples represented by sample6 using sampled values.
	private final void logProbabilityValue$sample6() {
		// Generating probabilities for sample task
		// Accumulator for probabilities of instances of the random variable
		double cv$accumulator = 0.0;
		
		// Accumulator for sample probabilities for a specific instance of the random variable.
		double cv$sampleAccumulator = 0.0;
		
		// An accumulator for log probabilities.
		double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
		
		// An accumulator for the distributed probability space covered.
		double cv$probabilityReached = 0.0;
		{
			// The sample value to calculate the probability of generating
			double cv$sampleValue = var6;
			{
				{
					// Store the value of the function call, so the function call is only made once.
					double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, a, b));
					
					// Add the probability of this sample task to the distribution accumulator.
					if((cv$weightedProbability < cv$distributionAccumulator))
						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
					else {
						// If the second value is -infinity.
						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
							cv$distributionAccumulator = cv$weightedProbability;
						else
							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
					}
					
					// Add the probability of this distribution configuration to the accumulator.
					cv$probabilityReached = (cv$probabilityReached + 1.0);
				}
			}
		}
		if((cv$probabilityReached == 0.0))
			// Return negative infinity if no distribution probability space is reached.
			cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
		else
			// Scale the probability relative to the observed distribution space.
			cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
		double cv$sampleProbability = cv$distributionAccumulator;
		
		// Add the probability of this sample task to the sample task accumulator.
		cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
		
		// Add the probability of this instance of the random variable to the probability
		// of all instances of the random variable.
		cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
		logProbability$var5 = cv$sampleAccumulator;
		
		// Store the sample task probability
		logProbability$var6 = cv$sampleProbability;
		
		// Add probability to model
		logProbability$$model = (logProbability$$model + cv$accumulator);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 6 drawn from Beta 5. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample6() {
		if(true) {
			// Local variable to record the number of true samples.
			int cv$sum = 0;
			
			// Local variable to record the number of samples.
			int cv$count = 0;
			{
				// Processing random variable 7.
				{
					{
						// Processing sample task 19 of consumer random variable bernoulli.
						{
							for(int var18 = 0; var18 < samples; var18 += 1) {
								// Include the value sampled by task 19 from random variable bernoulli.
								// Increment the number of samples.
								cv$count = (cv$count + 1);
								
								// If the sample value was positive increase the count
								if(flips[var18])
									cv$sum = (cv$sum + 1);
							}
						}
					}
				}
			}
			
			// Write out the new value of the sample.
			var6 = Conjugates.sampleConjugateBetaBinomial(RNG$, a, b, cv$sum, cv$count);
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
		flips = new boolean[length$flipsMeasured];
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		var6 = DistributionSampling.sampleBeta(RNG$, a, b);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$var18, int forEnd$var18, int threadID$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var18 = forStart$var18; var18 < forEnd$var18; var18 += 1)
						flips[var18] = DistributionSampling.sampleBernoulli(RNG$1, var6);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		var6 = DistributionSampling.sampleBeta(RNG$, a, b);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		var6 = DistributionSampling.sampleBeta(RNG$, a, b);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$var18, int forEnd$var18, int threadID$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var18 = forStart$var18; var18 < forEnd$var18; var18 += 1)
						flips[var18] = DistributionSampling.sampleBernoulli(RNG$1, var6);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		var6 = DistributionSampling.sampleBeta(RNG$, a, b);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		var6 = DistributionSampling.sampleBeta(RNG$, a, b);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward)
			sample6();
		// Infer the samples in reverse chronological order.
		else
			sample6();
		
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
		logProbability$var5 = 0.0;
		logProbability$var6 = Double.NaN;
		logProbability$bernoulli = Double.NaN;
		logProbability$flips = 0.0;
		logProbability$var19 = Double.NaN;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		logProbabilityValue$sample19();
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
		logProbabilityValue$sample6();
		logProbabilityValue$sample19();
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
		logProbabilityValue$sample6();
		logProbabilityValue$sample19();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Deep copy between arrays
		boolean[] cv$source1 = flipsMeasured;
		boolean[] cv$target1 = flips;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
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
		     + "public model Flip1CoinMK1c(boolean[] flipsMeasured, double a, double b) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    Bernoulli bernoulli = bernoulli(beta(a, b).sample());\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}