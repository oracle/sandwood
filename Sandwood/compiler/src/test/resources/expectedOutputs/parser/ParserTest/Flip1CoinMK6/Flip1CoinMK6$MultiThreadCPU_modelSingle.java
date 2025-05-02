package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK6$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK6$CoreInterface {
	
	// Declare the variables for the model.
	private double bias;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample22 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample9 = false;
	private boolean[] flips1;
	private boolean[] flips2;
	private boolean[] flipsMeasured1;
	private boolean[] flipsMeasured2;
	private int length$flipsMeasured1;
	private int length$flipsMeasured2;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips1;
	private double logProbability$flips2;
	private double logProbability$var22;
	private double logProbability$var35;
	private double logProbability$var8;
	private int samples1;
	private int samples2;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK6$MultiThreadCPU(ExecutionTarget target) {
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
		
		// Unset the fixed probability flag for sample 9 as it depends on bias.
		fixedProbFlag$sample9 = false;
		
		// Unset the fixed probability flag for sample 22 as it depends on bias.
		fixedProbFlag$sample22 = false;
		
		// Unset the fixed probability flag for sample 35 as it depends on bias.
		fixedProbFlag$sample35 = false;
	}

	// Getter for fixedFlag$sample9.
	@Override
	public final boolean get$fixedFlag$sample9() {
		return fixedFlag$sample9;
	}

	// Setter for fixedFlag$sample9.
	@Override
	public final void set$fixedFlag$sample9(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample9 including if probabilities
		// need to be updated.
		fixedFlag$sample9 = cv$value;
		
		// Should the probability of sample 9 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample9 = (fixedFlag$sample9 && fixedProbFlag$sample9);
		
		// Should the probability of sample 22 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample22 = (fixedFlag$sample9 && fixedProbFlag$sample22);
		
		// Should the probability of sample 35 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample35 = (fixedFlag$sample9 && fixedProbFlag$sample35);
	}

	// Getter for flips1.
	@Override
	public final boolean[] get$flips1() {
		return flips1;
	}

	// Getter for flips2.
	@Override
	public final boolean[] get$flips2() {
		return flips2;
	}

	// Getter for flipsMeasured1.
	@Override
	public final boolean[] get$flipsMeasured1() {
		return flipsMeasured1;
	}

	// Setter for flipsMeasured1.
	@Override
	public final void set$flipsMeasured1(boolean[] cv$value) {
		// Set flipsMeasured1
		flipsMeasured1 = cv$value;
	}

	// Getter for flipsMeasured2.
	@Override
	public final boolean[] get$flipsMeasured2() {
		return flipsMeasured2;
	}

	// Setter for flipsMeasured2.
	@Override
	public final void set$flipsMeasured2(boolean[] cv$value) {
		// Set flipsMeasured2
		flipsMeasured2 = cv$value;
	}

	// Getter for length$flipsMeasured1.
	@Override
	public final int get$length$flipsMeasured1() {
		return length$flipsMeasured1;
	}

	// Setter for length$flipsMeasured1.
	@Override
	public final void set$length$flipsMeasured1(int cv$value) {
		length$flipsMeasured1 = cv$value;
	}

	// Getter for length$flipsMeasured2.
	@Override
	public final int get$length$flipsMeasured2() {
		return length$flipsMeasured2;
	}

	// Setter for length$flipsMeasured2.
	@Override
	public final void set$length$flipsMeasured2(int cv$value) {
		length$flipsMeasured2 = cv$value;
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

	// Getter for logProbability$flips1.
	@Override
	public final double get$logProbability$flips1() {
		return logProbability$flips1;
	}

	// Getter for logProbability$flips2.
	@Override
	public final double get$logProbability$flips2() {
		return logProbability$flips2;
	}

	// Getter for samples1.
	@Override
	public final int get$samples1() {
		return samples1;
	}

	// Getter for samples2.
	@Override
	public final int get$samples2() {
		return samples2;
	}

	// Calculate the probability of the samples represented by sample22 using sampled
	// values.
	private final void logProbabilityValue$sample22() {
		// Determine if we need to calculate the values for sample task 22 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample22) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var21 = 0; var21 < samples1; var21 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flips1[var21];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?bias:(1.0 - bias))));
							
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
			
			// Check that the value is not still set to NaN.
			if((Double.isNaN(logProbability$bernoulli) && cv$sampleReached))
				logProbability$bernoulli = cv$sampleAccumulator;
			else
				logProbability$bernoulli = (logProbability$bernoulli + cv$sampleAccumulator);
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var22 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$flips1 = (logProbability$flips1 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample22 = fixedFlag$sample9;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var21 = 0; var21 < samples1; var21 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var22;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Check that the value is not still set to NaN.
			if((Double.isNaN(logProbability$bernoulli) && cv$sampleReached))
				logProbability$bernoulli = cv$rvAccumulator;
			else
				logProbability$bernoulli = (logProbability$bernoulli + cv$rvAccumulator);
			
			// Update the variable probability
			logProbability$flips1 = (logProbability$flips1 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample35 using sampled
	// values.
	private final void logProbabilityValue$sample35() {
		// Determine if we need to calculate the values for sample task 35 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample35) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var34 = 0; var34 < samples2; var34 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flips2[var34];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?bias:(1.0 - bias))));
							
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
			
			// Check that the value is not still set to NaN.
			if((Double.isNaN(logProbability$bernoulli) && cv$sampleReached))
				logProbability$bernoulli = cv$sampleAccumulator;
			else
				logProbability$bernoulli = (logProbability$bernoulli + cv$sampleAccumulator);
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var35 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$flips2 = (logProbability$flips2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample35 = fixedFlag$sample9;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var34 = 0; var34 < samples2; var34 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var35;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Check that the value is not still set to NaN.
			if((Double.isNaN(logProbability$bernoulli) && cv$sampleReached))
				logProbability$bernoulli = cv$rvAccumulator;
			else
				logProbability$bernoulli = (logProbability$bernoulli + cv$rvAccumulator);
			
			// Update the variable probability
			logProbability$flips2 = (logProbability$flips2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample9 using sampled values.
	private final void logProbabilityValue$sample9() {
		// Determine if we need to calculate the values for sample task 9 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample9) {
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
				double cv$sampleValue = bias;
				{
					{
						double var5 = 1.0;
						double var7 = 1.0;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var5, var7));
						
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
			logProbability$var8 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$bias = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample9 = fixedFlag$sample9;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$bias;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var8 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 9 drawn from Beta 8. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample9() {
		if(true) {
			// Local variable to record the number of true samples.
			int cv$sum = 0;
			
			// Local variable to record the number of samples.
			int cv$count = 0;
			{
				// Processing random variable 10.
				{
					{
						// Processing sample task 22 of consumer random variable bernoulli.
						{
							for(int var21 = 0; var21 < samples1; var21 += 1) {
								// Include the value sampled by task 22 from random variable bernoulli.
								// Increment the number of samples.
								cv$count = (cv$count + 1);
								
								// If the sample value was positive increase the count
								if(flips1[var21])
									cv$sum = (cv$sum + 1);
							}
						}
						
						// Processing sample task 35 of consumer random variable bernoulli.
						{
							for(int var34 = 0; var34 < samples2; var34 += 1) {
								// Include the value sampled by task 35 from random variable bernoulli.
								// Increment the number of samples.
								cv$count = (cv$count + 1);
								
								// If the sample value was positive increase the count
								if(flips2[var34])
									cv$sum = (cv$sum + 1);
							}
						}
					}
				}
			}
			
			// Write out the new value of the sample.
			bias = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
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
		// Constructor for flips1
		{
			flips1 = new boolean[length$flipsMeasured1];
		}
		
		// Constructor for flips2
		{
			flips2 = new boolean[length$flipsMeasured2];
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample9)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples1, 1,
			(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
						flips1[var21] = DistributionSampling.sampleBernoulli(RNG$1, bias);
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples2, 1,
			(int forStart$var34, int forEnd$var34, int threadID$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var34 = forStart$var34; var34 < forEnd$var34; var34 += 1)
						flips2[var34] = DistributionSampling.sampleBernoulli(RNG$1, bias);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample9)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample9)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample9)
				sample9();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!fixedFlag$sample9)
				sample9();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		samples1 = length$flipsMeasured1;
		samples2 = length$flipsMeasured2;
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
		logProbability$var8 = 0.0;
		if(!fixedProbFlag$sample9)
			logProbability$bias = Double.NaN;
		logProbability$bernoulli = Double.NaN;
		logProbability$flips1 = 0.0;
		if(!fixedProbFlag$sample22)
			logProbability$var22 = Double.NaN;
		logProbability$flips2 = 0.0;
		if(!fixedProbFlag$sample35)
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
		if(fixedFlag$sample9)
			logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample35();
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
		logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample35();
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
		logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample35();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample9)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		for(int i$var46 = (samples1 - ((((samples1 - 1) - 0) % 1) + 1)); i$var46 >= ((0 - 1) + 1); i$var46 -= 1)
			flips1[i$var46] = flipsMeasured1[i$var46];
		for(int i$var58 = (samples2 - ((((samples2 - 1) - 0) % 1) + 1)); i$var58 >= ((0 - 1) + 1); i$var58 -= 1)
			flips2[i$var58] = flipsMeasured2[i$var58];
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
		     + "public model Flip1CoinMK6(boolean[] flipsMeasured1, boolean[] flipsMeasured2) {\n"
		     + "    int samples1 = flipsMeasured1.length;\n"
		     + "    int samples2 = flipsMeasured2.length;\n"
		     + "        \n"
		     + "    double bias = beta(1.0, 1).sample();\n"
		     + "        \n"
		     + "    Bernoulli bernoulli = bernoulli(bias);\n"
		     + "    boolean[] flips1 = bernoulli.sample(samples1);\n"
		     + "    boolean[] flips2 = bernoulli.sample(samples2);\n"
		     + "\n"
		     + "    for(int i:[0..samples1))\n"
		     + "        flips1[i].observe(flipsMeasured1[i]);\n"
		     + "\n"
		     + "    for(int i:[0..samples2))\n"
		     + "        flips2[i].observe(flipsMeasured2[i]);\n"
		     + "}\n"
		     + "";
	}
}