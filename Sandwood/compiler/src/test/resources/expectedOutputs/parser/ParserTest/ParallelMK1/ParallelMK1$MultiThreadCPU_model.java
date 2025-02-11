package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ParallelMK1$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements ParallelMK1$CoreInterface {
	
	// Declare the variables for the model.
	private boolean fixedFlag$sample19 = false;
	private boolean fixedFlag$sample23 = false;
	private boolean fixedProbFlag$sample19 = false;
	private boolean fixedProbFlag$sample23 = false;
	private double[] generated;
	private boolean[][] guard$sample19gaussian22$global;
	private double[] indirection;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$generated;
	private double logProbability$indirection;
	private double logProbability$sample;
	private double[] logProbability$sample19;
	private double[] logProbability$sample23;
	private double[] logProbability$var14;
	private double[] logProbability$var18;
	private double[] observed;
	private double[] sample;
	private boolean setFlag$generated = false;
	private boolean setFlag$indirection = false;
	private boolean setFlag$sample = false;
	private boolean system$gibbsForward = true;

	public ParallelMK1$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for fixedFlag$sample19.
	@Override
	public final boolean get$fixedFlag$sample19() {
		return fixedFlag$sample19;
	}

	// Setter for fixedFlag$sample19.
	@Override
	public final void set$fixedFlag$sample19(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample19 including if probabilities
		// need to be updated.
		fixedFlag$sample19 = cv$value;
		
		// Should the probability of sample 19 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample19 = (fixedFlag$sample19 && fixedProbFlag$sample19);
		
		// Should the probability of sample 23 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample23 = (fixedFlag$sample19 && fixedProbFlag$sample23);
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

	// Getter for generated.
	@Override
	public final double[] get$generated() {
		return generated;
	}

	// Setter for generated.
	@Override
	public final void set$generated(double[] cv$value) {
		// Set flags for all the side effects of generated including if probabilities need
		// to be updated.
		// Set generated with flag to mark that it has been set so another array doesn't need
		// to be constructed
		generated = cv$value;
		setFlag$generated = true;
		
		// Unset the fixed probability flag for sample 23 as it depends on generated.
		fixedProbFlag$sample23 = false;
	}

	// Getter for indirection.
	@Override
	public final double[] get$indirection() {
		return indirection;
	}

	// Setter for indirection.
	@Override
	public final void set$indirection(double[] cv$value) {
		// Set indirection with flag to mark that it has been set so another array doesn't
		// need to be constructed
		indirection = cv$value;
		setFlag$indirection = true;
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
		// Set observed with flag to mark that it has been set so another array doesn't need
		// to be constructed
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
		// Set sample with flag to mark that it has been set so another array doesn't need
		// to be constructed
		sample = cv$value;
		setFlag$sample = true;
	}

	// Calculate the probability of the samples represented by sample19 using sampled
	// values.
	private final void logProbabilityValue$sample19() {
		// Determine if we need to calculate the values for sample task 19 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample19) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = sample[((i - 0) / 1)];
					{
						{
							double var12 = 0.0;
							double var13 = 1.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((var12 <= cv$sampleValue) && (cv$sampleValue <= var13))?(-Math.log((var13 - var12))):Double.NEGATIVE_INFINITY));
							
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
				logProbability$var14[((i - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample19[((i - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that indirection is only updated once for this probability.
				boolean cv$guard$indirection = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					// If the probability of the variable has not already been updated
					if(!cv$guard$indirection) {
						// Set the guard so the update is only applied once.
						cv$guard$indirection = true;
						
						// Update the variable probability
						logProbability$indirection = (logProbability$indirection + cv$sampleProbability);
					}
				}
			}
			
			// Update the variable probability
			logProbability$sample = (logProbability$sample + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample19)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample19 = fixedFlag$sample19;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample19[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var14[((i - 0) / 1)] = cv$rvAccumulator;
				
				// Guard to ensure that indirection is only updated once for this probability.
				boolean cv$guard$indirection = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					// If the probability of the variable has not already been updated
					if(!cv$guard$indirection) {
						// Set the guard so the update is only applied once.
						cv$guard$indirection = true;
						
						// Update the variable probability
						logProbability$indirection = (logProbability$indirection + cv$sampleValue);
					}
				}
			}
			
			// Update the variable probability
			logProbability$sample = (logProbability$sample + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample19)
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
			for(int i = 0; i < length$observed; i += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = generated[i];
					{
						{
							double var17 = indirection[i];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - sample[((i - 0) / 1)]) / Math.sqrt(var17))) - (0.5 * Math.log(var17))));
							
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
				logProbability$var18[((i - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample23[((i - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$generated = (logProbability$generated + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample23 = (fixedFlag$sample23 && fixedFlag$sample19);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample23[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var18[((i - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$generated = (logProbability$generated + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 19 drawn from Uniform 14. Inference was performed using Metropolis-Hastings.
	private final void sample19(int i, int threadID$cv$i, Rng RNG$) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		{
			// Metropolis-Hastings
			cv$noStates = Math.max(cv$noStates, 2);
		}
		
		// The original value of the sample
		double cv$originalValue = sample[((i - 0) / 1)];
		
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability = 0.0;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		
		// Ensure the variance is at least 0.01
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		
		// The probability of the random variable generating the new sample value.
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// The value currently being tested
			double cv$currentValue;
			if((cv$valuePos == 0))
				// Set the current value to the current state of the tree.
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				
				// Update Sample and intermediate values
				{
					// Write out the new value of the sample.
					sample[((i - 0) / 1)] = cv$proposedValue;
					
					// Guards to ensure that indirection is only updated when there is a valid path.
					{
						{
							indirection[i] = cv$currentValue;
						}
					}
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var12;
				{
					cv$temp$0$var12 = 0.0;
				}
				double cv$temp$1$var13;
				{
					cv$temp$1$var13 = 1.0;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((cv$temp$0$var12 <= cv$currentValue) && (cv$currentValue <= cv$temp$1$var13))?(-Math.log((cv$temp$1$var13 - cv$temp$0$var12))):Double.NEGATIVE_INFINITY));
				
				// Processing random variable 18.
				{
					// Looking for a path between Sample 19 and consumer Gaussian 18.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[] guard$sample19gaussian22 = guard$sample19gaussian22$global[threadID$cv$i];
						for(int index$i$3_1 = 0; index$i$3_1 < length$observed; index$i$3_1 += 1) {
							if((i == index$i$3_1))
								// Set the flags to false
								guard$sample19gaussian22[((i - 0) / 1)] = false;
						}
						
						// Set the flags to false
						guard$sample19gaussian22[((i - 0) / 1)] = false;
						if(!guard$sample19gaussian22[((i - 0) / 1)]) {
							// The body will execute, so should not be executed again
							guard$sample19gaussian22[((i - 0) / 1)] = true;
							
							// Processing sample task 23 of consumer random variable null.
							{
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								{
									{
										{
											{
												double cv$temp$2$sample;
												{
													cv$temp$2$sample = cv$currentValue;
												}
												double cv$temp$3$var17;
												{
													// Constructing a random variable input for use later.
													double var17 = indirection[i];
													cv$temp$3$var17 = var17;
												}
												
												// Record the probability of sample task 23 generating output with current configuration.
												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[i] - cv$temp$2$sample) / Math.sqrt(cv$temp$3$var17))) - (0.5 * Math.log(cv$temp$3$var17)))) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[i] - cv$temp$2$sample) / Math.sqrt(cv$temp$3$var17))) - (0.5 * Math.log(cv$temp$3$var17)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													// If the second value is -infinity.
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[i] - cv$temp$2$sample) / Math.sqrt(cv$temp$3$var17))) - (0.5 * Math.log(cv$temp$3$var17))));
													else
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[i] - cv$temp$2$sample) / Math.sqrt(cv$temp$3$var17))) - (0.5 * Math.log(cv$temp$3$var17)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[i] - cv$temp$2$sample) / Math.sqrt(cv$temp$3$var17))) - (0.5 * Math.log(cv$temp$3$var17)))));
												}
												
												// Recorded the probability of reaching sample task 23 with the current configuration.
												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
											}
										}
									}
								}
								
								// A check to ensure rounding of floating point values can never result in a negative
								// value.
								cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
								
								// Multiply (log space add) in the probability of the sample task to the overall probability
								// for this configuration of the source random variable.
								if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
									else
										cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
								}
							}
						}
						double traceTempVariable$var17$5_1 = cv$currentValue;
						for(int index$i$5_2 = 0; index$i$5_2 < length$observed; index$i$5_2 += 1) {
							if((i == index$i$5_2)) {
								if(!guard$sample19gaussian22[((i - 0) / 1)]) {
									// The body will execute, so should not be executed again
									guard$sample19gaussian22[((i - 0) / 1)] = true;
									
									// Processing sample task 23 of consumer random variable null.
									{
										// Set an accumulator to sum the probabilities for each possible configuration of
										// inputs.
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											{
												{
													{
														double cv$temp$4$sample;
														{
															cv$temp$4$sample = cv$currentValue;
														}
														double cv$temp$5$var17;
														{
															// Constructing a random variable input for use later.
															double var17 = traceTempVariable$var17$5_1;
															cv$temp$5$var17 = var17;
														}
														
														// Record the probability of sample task 23 generating output with current configuration.
														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[index$i$5_2] - cv$temp$4$sample) / Math.sqrt(cv$temp$5$var17))) - (0.5 * Math.log(cv$temp$5$var17)))) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[index$i$5_2] - cv$temp$4$sample) / Math.sqrt(cv$temp$5$var17))) - (0.5 * Math.log(cv$temp$5$var17)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															// If the second value is -infinity.
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[index$i$5_2] - cv$temp$4$sample) / Math.sqrt(cv$temp$5$var17))) - (0.5 * Math.log(cv$temp$5$var17))));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[index$i$5_2] - cv$temp$4$sample) / Math.sqrt(cv$temp$5$var17))) - (0.5 * Math.log(cv$temp$5$var17)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((generated[index$i$5_2] - cv$temp$4$sample) / Math.sqrt(cv$temp$5$var17))) - (0.5 * Math.log(cv$temp$5$var17)))));
														}
														
														// Recorded the probability of reaching sample task 23 with the current configuration.
														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
													}
												}
											}
										}
										
										// A check to ensure rounding of floating point values can never result in a negative
										// value.
										cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
										
										// Multiply (log space add) in the probability of the sample task to the overall probability
										// for this configuration of the source random variable.
										if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
											cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
										else {
											// If the second value is -infinity.
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
											else
												cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
										}
									}
								}
							}
						}
					}
				}
				
				// Add the values for the source and any standard consumers for this configuration
				// of arguments to the source.
				if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
					cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
				else {
					// If the second value is -infinity.
					if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					else
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the probability of the original value.
			if((cv$valuePos == 0))
				cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			
			// Save the probability of the proposed value.
			else
				cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		
		// The probability ration for the proposed value and the current value.
		double cv$ratio = (cv$proposedProbability - cv$originalProbability);
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
			// If it is not revert the changes.
			// 
			// Set the sample value
			// Write out the new value of the sample.
			sample[((i - 0) / 1)] = cv$originalValue;
			
			// Guards to ensure that indirection is only updated when there is a valid path.
			{
				{
					indirection[i] = sample[((i - 0) / 1)];
				}
			}
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Calculate the largest index of i that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_i = 0;
		cv$max_i = Math.max(cv$max_i, ((length$observed - 0) / 1));
		
		// Allocation of guard$sample19gaussian22$global for multithreaded execution
		{
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			guard$sample19gaussian22$global = new boolean[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				guard$sample19gaussian22$global[cv$index] = new boolean[cv$max_i];
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If generated has not been set already allocate space.
		if(!setFlag$generated) {
			// Constructor for generated
			{
				generated = new double[length$observed];
			}
		}
		
		// If indirection has not been set already allocate space.
		if(!setFlag$indirection) {
			// Constructor for indirection
			{
				indirection = new double[length$observed];
			}
		}
		
		// If sample has not been set already allocate space.
		if(!setFlag$sample) {
			// Constructor for sample
			{
				sample = new double[((((length$observed - 1) - 0) / 1) + 1)];
			}
		}
		
		// Constructor for logProbability$var14
		{
			logProbability$var14 = new double[((((length$observed - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample19
		{
			logProbability$sample19 = new double[((((length$observed - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$var18
		{
			logProbability$var18 = new double[((((length$observed - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample23
		{
			logProbability$sample23 = new double[((((length$observed - 1) - 0) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample19)
							sample[((i - 0) / 1)] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$1)));
						if(!fixedFlag$sample19)
							indirection[i] = sample[((i - 0) / 1)];
						if(!fixedFlag$sample23)
							generated[i] = ((Math.sqrt(indirection[i]) * DistributionSampling.sampleGaussian(RNG$1)) + sample[((i - 0) / 1)]);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample19)
							sample[((i - 0) / 1)] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$1)));
						if(!fixedFlag$sample19)
							indirection[i] = sample[((i - 0) / 1)];
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample19)
							sample[((i - 0) / 1)] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$1)));
						if(!fixedFlag$sample19)
							indirection[i] = sample[((i - 0) / 1)];
					}
			}
		);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1) {
							if(!fixedFlag$sample19)
								sample19(i, threadID$i, RNG$1);
						}
				}
			);
		// Infer the samples in reverse chronological order.
		else
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1) {
							if(!fixedFlag$sample19)
								sample19(i, threadID$i, RNG$1);
						}
				}
			);
		
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
		for(int i = 0; i < length$observed; i += 1)
			logProbability$var14[((i - 0) / 1)] = 0.0;
		logProbability$sample = 0.0;
		logProbability$indirection = 0.0;
		if(!fixedProbFlag$sample19) {
			for(int i = 0; i < length$observed; i += 1)
				logProbability$sample19[((i - 0) / 1)] = 0.0;
		}
		for(int i = 0; i < length$observed; i += 1)
			logProbability$var18[((i - 0) / 1)] = 0.0;
		logProbability$generated = 0.0;
		if(!fixedProbFlag$sample23) {
			for(int i = 0; i < length$observed; i += 1)
				logProbability$sample23[((i - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample19)
			logProbabilityValue$sample19();
		logProbabilityValue$sample23();
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
		logProbabilityValue$sample19();
		logProbabilityValue$sample23();
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
		logProbabilityValue$sample19();
		logProbabilityValue$sample23();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample19)
							sample[((i - 0) / 1)] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$1)));
						if(!fixedFlag$sample19)
							indirection[i] = sample[((i - 0) / 1)];
					}
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
		// Deep copy between arrays
		double[] cv$source1 = observed;
		double[] cv$target1 = generated;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model ParallelMK1(double[] observed) {\n    double[] generated = new double[observed.length];\n    double[] indirection = new double[observed.length];\n\n    for(int i=0; i<observed.length; i++) {\n        double sample = uniform(0.0, 1.0).sample();\n        indirection[i] = sample;\n        generated[i] = gaussian(sample, indirection[i]).sample();\n    }\n\n    generated.observe(observed);\n}";
	}
}