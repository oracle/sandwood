package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK5$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip1CoinMK5$CoreInterface {
	
	// Declare the variables for the model.
	private double bias;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample23 = false;
	private boolean fixedFlag$sample30 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample23 = false;
	private boolean fixedProbFlag$sample30 = false;
	private boolean[] flips1;
	private boolean[] flips2;
	private boolean[] flipsMeasured1;
	private boolean[] flipsMeasured2;
	private int length$flipsMeasured1;
	private int length$flipsMeasured2;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli1;
	private double logProbability$bernoulli2;
	private double logProbability$bias;
	private double logProbability$flips1;
	private double logProbability$flips2;
	private double logProbability$var12;
	private double logProbability$var19;
	private double logProbability$var26;
	private int samples1;
	private int samples2;
	private boolean setFlag$flips1 = false;
	private boolean setFlag$flips2 = false;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK5$SingleThreadCPU(ExecutionTarget target) {
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
		
		// Unset the fixed probability flag for sample 17 as it depends on bias.
		fixedProbFlag$sample17 = false;
		
		// Unset the fixed probability flag for sample 23 as it depends on bias.
		fixedProbFlag$sample23 = false;
		
		// Unset the fixed probability flag for sample 30 as it depends on bias.
		fixedProbFlag$sample30 = false;
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
		fixedProbFlag$sample17 = (fixedFlag$sample17 && fixedProbFlag$sample17);
		
		// Should the probability of sample 23 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample23 = (fixedFlag$sample17 && fixedProbFlag$sample23);
		
		// Should the probability of sample 30 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample30 = (fixedFlag$sample17 && fixedProbFlag$sample30);
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
		fixedProbFlag$sample23 = (fixedFlag$sample23 && fixedProbFlag$sample23);
	}

	// Getter for fixedFlag$sample30.
	@Override
	public final boolean get$fixedFlag$sample30() {
		return fixedFlag$sample30;
	}

	// Setter for fixedFlag$sample30.
	@Override
	public final void set$fixedFlag$sample30(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample30 including if probabilities
		// need to be updated.
		fixedFlag$sample30 = cv$value;
		
		// Should the probability of sample 30 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample30 = (fixedFlag$sample30 && fixedProbFlag$sample30);
	}

	// Getter for flips1.
	@Override
	public final boolean[] get$flips1() {
		return flips1;
	}

	// Setter for flips1.
	@Override
	public final void set$flips1(boolean[] cv$value) {
		// Set flags for all the side effects of flips1 including if probabilities need to
		// be updated.
		// Set flips1 with flag to mark that it has been set so another array doesn't need
		// to be constructed
		flips1 = cv$value;
		setFlag$flips1 = true;
		
		// Unset the fixed probability flag for sample 23 as it depends on flips1.
		fixedProbFlag$sample23 = false;
	}

	// Getter for flips2.
	@Override
	public final boolean[] get$flips2() {
		return flips2;
	}

	// Setter for flips2.
	@Override
	public final void set$flips2(boolean[] cv$value) {
		// Set flags for all the side effects of flips2 including if probabilities need to
		// be updated.
		// Set flips2 with flag to mark that it has been set so another array doesn't need
		// to be constructed
		flips2 = cv$value;
		setFlag$flips2 = true;
		
		// Unset the fixed probability flag for sample 30 as it depends on flips2.
		fixedProbFlag$sample30 = false;
	}

	// Getter for flipsMeasured1.
	@Override
	public final boolean[] get$flipsMeasured1() {
		return flipsMeasured1;
	}

	// Setter for flipsMeasured1.
	@Override
	public final void set$flipsMeasured1(boolean[] cv$value) {
		// Set flipsMeasured1 with flag to mark that it has been set so another array doesn't
		// need to be constructed
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
		// Set flipsMeasured2 with flag to mark that it has been set so another array doesn't
		// need to be constructed
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

	// Getter for logProbability$bernoulli1.
	@Override
	public final double get$logProbability$bernoulli1() {
		return logProbability$bernoulli1;
	}

	// Getter for logProbability$bernoulli2.
	@Override
	public final double get$logProbability$bernoulli2() {
		return logProbability$bernoulli2;
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

	// Calculate the probability of the samples represented by sample17 using sampled
	// values.
	private final void logProbabilityValue$sample17() {
		// Determine if we need to calculate the values for sample task 17 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample17) {
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
						double var9 = 1.0;
						double var11 = 1.0;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var9, var11));
						
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
			logProbability$var12 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$bias = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample17 = fixedFlag$sample17;
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
			logProbability$var12 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample23 using sampled
	// values.
	private final void logProbabilityValue$sample23() {
		// Determine if we need to calculate the values for sample task 23 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample23) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var18 = 0; var18 < samples1; var18 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flips1[var18];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, bias));
							
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
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$bernoulli1 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var19 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$flips1 = (logProbability$flips1 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample23 = (fixedFlag$sample23 && fixedFlag$sample17);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var19;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bernoulli1 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$flips1 = (logProbability$flips1 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample30 using sampled
	// values.
	private final void logProbabilityValue$sample30() {
		// Determine if we need to calculate the values for sample task 30 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample30) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var25 = 0; var25 < samples2; var25 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flips2[var25];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, bias));
							
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
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$bernoulli2 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var26 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$flips2 = (logProbability$flips2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample30 = (fixedFlag$sample30 && fixedFlag$sample17);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var26;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bernoulli2 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$flips2 = (logProbability$flips2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 17 drawn from Beta 12. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample17() {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		{
			// Processing random variable 14.
			{
				{
					// Processing sample task 23 of consumer random variable bernoulli1.
					{
						for(int var18 = 0; var18 < samples1; var18 += 1) {
							// Include the value sampled by task 23 from random variable bernoulli1.
							// Increment the number of samples.
							cv$count = (cv$count + 1);
							
							// If the sample value was positive increase the count
							if(flips1[var18])
								cv$sum = (cv$sum + 1);
						}
					}
				}
			}
			
			// Processing random variable 21.
			{
				{
					// Processing sample task 30 of consumer random variable bernoulli2.
					{
						for(int var25 = 0; var25 < samples2; var25 += 1) {
							// Include the value sampled by task 30 from random variable bernoulli2.
							// Increment the number of samples.
							cv$count = (cv$count + 1);
							
							// If the sample value was positive increase the count
							if(flips2[var25])
								cv$sum = (cv$sum + 1);
						}
					}
				}
			}
		}
		
		// Write out the new value of the sample.
		bias = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If flips1 has not been set already allocate space.
		if(!setFlag$flips1) {
			// Constructor for flips1
			{
				flips1 = new boolean[length$flipsMeasured1];
			}
		}
		
		// If flips2 has not been set already allocate space.
		if(!setFlag$flips2) {
			// Constructor for flips2
			{
				flips2 = new boolean[length$flipsMeasured2];
			}
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample17)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		for(int var18 = 0; var18 < samples1; var18 += 1) {
			if(!fixedFlag$sample23)
				flips1[var18] = DistributionSampling.sampleBernoulli(RNG$, bias);
		}
		for(int var25 = 0; var25 < samples2; var25 += 1) {
			if(!fixedFlag$sample30)
				flips2[var25] = DistributionSampling.sampleBernoulli(RNG$, bias);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample17)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample17)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample17)
				sample17();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!fixedFlag$sample17)
				sample17();
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
		logProbability$var12 = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$bias = 0.0;
		logProbability$bernoulli1 = 0.0;
		logProbability$flips1 = 0.0;
		if(!fixedProbFlag$sample23)
			logProbability$var19 = 0.0;
		logProbability$bernoulli2 = 0.0;
		logProbability$flips2 = 0.0;
		if(!fixedProbFlag$sample30)
			logProbability$var26 = 0.0;
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
		logProbabilityValue$sample23();
		logProbabilityValue$sample30();
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
		logProbabilityValue$sample23();
		logProbabilityValue$sample30();
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
		logProbabilityValue$sample23();
		logProbabilityValue$sample30();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample17)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		for(int i$var44 = ((0 - 1) + (((((2 * samples2) - 2) - ((0 - 1) + 1)) % 2) + 1)); i$var44 < ((samples2 * 2) - 1); i$var44 += 2)
			flips2[(i$var44 / 2)] = flipsMeasured2[(i$var44 / 2)];
		for(int i$var33 = ((-1) + ((((samples1 - 1) - ((-1) + 1)) % 1) + 1)); i$var33 < samples1; i$var33 += 1)
			flips1[i$var33] = flipsMeasured1[i$var33];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK5(boolean[] flipsMeasured1, boolean[] flipsMeasured2) {\n    int samples1 = flipsMeasured1.length;\n    int samples2 = flipsMeasured2.length;\n        \n    double bias = beta(1.0, 1).sample();\n        \n    Bernoulli bernoulli1 = bernoulli(bias);\n    boolean[] flips1 = bernoulli1.sample(samples1);\n        \n    Bernoulli bernoulli2 = bernoulli(bias);\n    boolean[] flips2 = bernoulli2.sample(samples2);\n\n    for(int i=samples1-1; i>-1; --i)\n        flips1[i].observe(flipsMeasured1[i]);\n\n    for(int i=2*samples2-2; i>=0; i = i-2)\n        flips2[i/2].observe(flipsMeasured2[i/2]);\n}\n";
	}
}