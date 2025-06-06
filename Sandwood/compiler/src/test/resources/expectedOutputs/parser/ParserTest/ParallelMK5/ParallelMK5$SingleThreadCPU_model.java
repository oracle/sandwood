package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ParallelMK5$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements ParallelMK5$CoreInterface {
	
	// Declare the variables for the model.
	private boolean fixedFlag$sample41 = false;
	private boolean fixedFlag$sample65 = false;
	private boolean fixedProbFlag$sample41 = false;
	private boolean fixedProbFlag$sample65 = false;
	private int[] generated;
	private double[][] indirection1;
	private double[][] indirection2;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$generated;
	private double logProbability$indirection1;
	private double logProbability$indirection2;
	private double[][] logProbability$sample41;
	private double[] logProbability$sample65;
	private double[][] logProbability$var33;
	private double[] logProbability$var55;
	private int[] observed;
	private boolean setFlag$generated = false;
	private boolean setFlag$indirection1 = false;
	private boolean system$gibbsForward = true;

	public ParallelMK5$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for fixedFlag$sample41.
	@Override
	public final boolean get$fixedFlag$sample41() {
		return fixedFlag$sample41;
	}

	// Setter for fixedFlag$sample41.
	@Override
	public final void set$fixedFlag$sample41(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample41 including if probabilities
		// need to be updated.
		fixedFlag$sample41 = cv$value;
		
		// Should the probability of sample 41 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample41 = (fixedFlag$sample41 && fixedProbFlag$sample41);
		
		// Should the probability of sample 65 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample65 = (fixedFlag$sample41 && fixedProbFlag$sample65);
	}

	// Getter for fixedFlag$sample65.
	@Override
	public final boolean get$fixedFlag$sample65() {
		return fixedFlag$sample65;
	}

	// Setter for fixedFlag$sample65.
	@Override
	public final void set$fixedFlag$sample65(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample65 including if probabilities
		// need to be updated.
		fixedFlag$sample65 = cv$value;
		
		// Should the probability of sample 65 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample65 = (fixedFlag$sample65 && fixedProbFlag$sample65);
	}

	// Getter for generated.
	@Override
	public final int[] get$generated() {
		return generated;
	}

	// Setter for generated.
	@Override
	public final void set$generated(int[] cv$value) {
		// Set flags for all the side effects of generated including if probabilities need
		// to be updated.
		// Set generated with flag to mark that it has been set so another array doesn't need
		// to be constructed
		generated = cv$value;
		setFlag$generated = true;
		
		// Unset the fixed probability flag for sample 65 as it depends on generated.
		fixedProbFlag$sample65 = false;
	}

	// Getter for indirection1.
	@Override
	public final double[][] get$indirection1() {
		return indirection1;
	}

	// Setter for indirection1.
	@Override
	public final void set$indirection1(double[][] cv$value) {
		// Set flags for all the side effects of indirection1 including if probabilities need
		// to be updated.
		// Set indirection1 with flag to mark that it has been set so another array doesn't
		// need to be constructed
		indirection1 = cv$value;
		setFlag$indirection1 = true;
		
		// Unset the fixed probability flag for sample 41 as it depends on indirection1.
		fixedProbFlag$sample41 = false;
		
		// Unset the fixed probability flag for sample 65 as it depends on indirection1.
		fixedProbFlag$sample65 = false;
	}

	// Getter for indirection2.
	@Override
	public final double[][] get$indirection2() {
		return indirection2;
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

	// Getter for logProbability$indirection1.
	@Override
	public final double get$logProbability$indirection1() {
		return logProbability$indirection1;
	}

	// Getter for logProbability$indirection2.
	@Override
	public final double get$logProbability$indirection2() {
		return logProbability$indirection2;
	}

	// Getter for observed.
	@Override
	public final int[] get$observed() {
		return observed;
	}

	// Setter for observed.
	@Override
	public final void set$observed(int[] cv$value) {
		// Set observed with flag to mark that it has been set so another array doesn't need
		// to be constructed
		observed = cv$value;
	}

	// Calculate the probability of the samples represented by sample41 using sampled
	// values.
	private final void logProbabilityValue$sample41() {
		// Determine if we need to calculate the values for sample task 41 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample41) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i = 0; i < 10; i += 1) {
				for(int j = 0; j < length$observed; j += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = indirection1[i][j];
						{
							{
								double var31 = 0.0;
								double var32 = 1.0;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((var31 <= cv$sampleValue) && (cv$sampleValue <= var32))?(-Math.log((var32 - var31))):Double.NEGATIVE_INFINITY));
								
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
					logProbability$var33[((i - 0) / 1)][((j - 0) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample41[((i - 0) / 1)][((j - 0) / 1)] = cv$sampleProbability;
					
					// Guard to ensure that indirection2 is only updated once for this probability.
					boolean cv$guard$indirection2 = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					// 
					// Looking for a path between Sample 41 and consumer double[][] 49.
					{
						for(int l = 0; l < 10; l += 1) {
							if((i == l)) {
								for(int k = 0; k < length$observed; k += 1) {
									if((j == k)) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$indirection2) {
											// Set the guard so the update is only applied once.
											cv$guard$indirection2 = true;
											
											// Update the variable probability
											logProbability$indirection2 = (logProbability$indirection2 + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Update the variable probability
			logProbability$indirection1 = (logProbability$indirection1 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample41)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample41 = fixedFlag$sample41;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < 10; i += 1) {
				for(int j = 0; j < length$observed; j += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample41[((i - 0) / 1)][((j - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var33[((i - 0) / 1)][((j - 0) / 1)] = cv$rvAccumulator;
					
					// Guard to ensure that indirection2 is only updated once for this probability.
					boolean cv$guard$indirection2 = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					// 
					// Looking for a path between Sample 41 and consumer double[][] 49.
					{
						for(int l = 0; l < 10; l += 1) {
							if((i == l)) {
								for(int k = 0; k < length$observed; k += 1) {
									if((j == k)) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$indirection2) {
											// Set the guard so the update is only applied once.
											cv$guard$indirection2 = true;
											
											// Update the variable probability
											logProbability$indirection2 = (logProbability$indirection2 + cv$sampleValue);
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Update the variable probability
			logProbability$indirection1 = (logProbability$indirection1 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample41)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample65 using sampled
	// values.
	private final void logProbabilityValue$sample65() {
		// Determine if we need to calculate the values for sample task 65 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample65) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int m = 0; m < length$observed; m += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = generated[m];
					{
						{
							double[] var54 = indirection2[m];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var54.length))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
				logProbability$var55[((m - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample65[((m - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$generated = (logProbability$generated + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample65 = (fixedFlag$sample65 && fixedFlag$sample41);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int m = 0; m < length$observed; m += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample65[((m - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var55[((m - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$generated = (logProbability$generated + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 41 drawn from Uniform 33. Inference was performed using Metropolis-Hastings.
	private final void sample41(int i, int j) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		{
			// Metropolis-Hastings
			cv$noStates = Math.max(cv$noStates, 2);
		}
		
		// The original value of the sample
		double cv$originalValue = indirection1[i][j];
		
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
					// Write out the value of the sample to a temporary variable prior to updating the
					// intermediate variables.
					double var34 = cv$proposedValue;
					double[] var30 = indirection1[i];
					var30[j] = cv$currentValue;
					
					// Guards to ensure that indirection2 is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 41 and consumer double[][] 49.
					{
						for(int l = 0; l < 10; l += 1) {
							if((i == l)) {
								for(int k = 0; k < length$observed; k += 1) {
									if((j == k)) {
										{
											double[] var45 = indirection2[k];
											var45[l] = indirection1[l][k];
										}
									}
								}
							}
						}
					}
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var31;
				{
					cv$temp$0$var31 = 0.0;
				}
				double cv$temp$1$var32;
				{
					cv$temp$1$var32 = 1.0;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((cv$temp$0$var31 <= cv$currentValue) && (cv$currentValue <= cv$temp$1$var32))?(-Math.log((cv$temp$1$var32 - cv$temp$0$var31))):Double.NEGATIVE_INFINITY));
				
				// Processing random variable 55.
				{
					// Looking for a path between Sample 41 and consumer Categorical 55.
					{
						double traceTempVariable$var47$2_1 = cv$currentValue;
						for(int l = 0; l < 10; l += 1) {
							if((i == l)) {
								for(int k = 0; k < length$observed; k += 1) {
									if((j == k)) {
										for(int m = 0; m < length$observed; m += 1) {
											if((k == m)) {
												// Processing sample task 65 of consumer random variable null.
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
																	double[] cv$temp$2$var54;
																	{
																		// Constructing a random variable input for use later.
																		double[] var54 = indirection2[m];
																		cv$temp$2$var54 = var54;
																	}
																	
																	// Record the probability of sample task 65 generating output with current configuration.
																	if(((Math.log(1.0) + (((0.0 <= generated[m]) && (generated[m] < cv$temp$2$var54.length))?Math.log(cv$temp$2$var54[generated[m]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= generated[m]) && (generated[m] < cv$temp$2$var54.length))?Math.log(cv$temp$2$var54[generated[m]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= generated[m]) && (generated[m] < cv$temp$2$var54.length))?Math.log(cv$temp$2$var54[generated[m]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= generated[m]) && (generated[m] < cv$temp$2$var54.length))?Math.log(cv$temp$2$var54[generated[m]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= generated[m]) && (generated[m] < cv$temp$2$var54.length))?Math.log(cv$temp$2$var54[generated[m]]):Double.NEGATIVE_INFINITY)));
																	}
																	
																	// Recorded the probability of reaching sample task 65 with the current configuration.
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
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			double var34 = cv$originalValue;
			double[] var30 = indirection1[i];
			var30[j] = var34;
			
			// Guards to ensure that indirection2 is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 41 and consumer double[][] 49.
			{
				for(int l = 0; l < 10; l += 1) {
					if((i == l)) {
						for(int k = 0; k < length$observed; k += 1) {
							if((j == k)) {
								{
									double[] var45 = indirection2[k];
									var45[l] = indirection1[l][k];
								}
							}
						}
					}
				}
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
		// If generated has not been set already allocate space.
		if(!setFlag$generated) {
			// Constructor for generated
			{
				generated = new int[length$observed];
			}
		}
		
		// If indirection1 has not been set already allocate space.
		if(!setFlag$indirection1) {
			// Constructor for indirection1
			{
				indirection1 = new double[10][];
				for(int var11 = 0; var11 < 10; var11 += 1)
					indirection1[var11] = new double[length$observed];
			}
		}
		
		// Constructor for indirection2
		{
			indirection2 = new double[length$observed][];
			for(int var19 = 0; var19 < length$observed; var19 += 1)
				indirection2[var19] = new double[10];
		}
		
		// Constructor for logProbability$var33
		{
			logProbability$var33 = new double[((((10 - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < 10; i += 1)
				logProbability$var33[((i - 0) / 1)] = new double[((((length$observed - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample41
		{
			logProbability$sample41 = new double[((((10 - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < 10; i += 1)
				logProbability$sample41[((i - 0) / 1)] = new double[((((length$observed - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$var55
		{
			logProbability$var55 = new double[((((length$observed - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample65
		{
			logProbability$sample65 = new double[((((length$observed - 1) - 0) / 1) + 1)];
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		for(int i = 0; i < 10; i += 1) {
			double[] var30 = indirection1[i];
			for(int j = 0; j < length$observed; j += 1) {
				if(!fixedFlag$sample41)
					var30[j] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int k = 0; k < length$observed; k += 1) {
			double[] var45 = indirection2[k];
			for(int l = 0; l < 10; l += 1) {
				if(!fixedFlag$sample41)
					var45[l] = indirection1[l][k];
			}
		}
		for(int m = 0; m < length$observed; m += 1) {
			if(!fixedFlag$sample65)
				generated[m] = DistributionSampling.sampleCategorical(RNG$, indirection2[m]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int i = 0; i < 10; i += 1) {
			double[] var30 = indirection1[i];
			for(int j = 0; j < length$observed; j += 1) {
				if(!fixedFlag$sample41)
					var30[j] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int k = 0; k < length$observed; k += 1) {
			double[] var45 = indirection2[k];
			for(int l = 0; l < 10; l += 1) {
				if(!fixedFlag$sample41)
					var45[l] = indirection1[l][k];
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int i = 0; i < 10; i += 1) {
			double[] var30 = indirection1[i];
			for(int j = 0; j < length$observed; j += 1) {
				if(!fixedFlag$sample41)
					var30[j] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int k = 0; k < length$observed; k += 1) {
			double[] var45 = indirection2[k];
			for(int l = 0; l < 10; l += 1) {
				if(!fixedFlag$sample41)
					var45[l] = indirection1[l][k];
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			for(int i = 0; i < 10; i += 1) {
				for(int j = 0; j < length$observed; j += 1) {
					if(!fixedFlag$sample41)
						sample41(i, j);
				}
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i = (10 - ((((10 - 1) - 0) % 1) + 1)); i >= ((0 - 1) + 1); i -= 1) {
				for(int j = (length$observed - ((((length$observed - 1) - 0) % 1) + 1)); j >= ((0 - 1) + 1); j -= 1) {
					if(!fixedFlag$sample41)
						sample41(i, j);
				}
			}
		}
		
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
		for(int i = 0; i < 10; i += 1) {
			for(int j = 0; j < length$observed; j += 1)
				logProbability$var33[((i - 0) / 1)][((j - 0) / 1)] = 0.0;
		}
		logProbability$indirection2 = 0.0;
		logProbability$indirection1 = 0.0;
		if(!fixedProbFlag$sample41) {
			for(int i = 0; i < 10; i += 1) {
				for(int j = 0; j < length$observed; j += 1)
					logProbability$sample41[((i - 0) / 1)][((j - 0) / 1)] = 0.0;
			}
		}
		for(int m = 0; m < length$observed; m += 1)
			logProbability$var55[((m - 0) / 1)] = 0.0;
		logProbability$generated = 0.0;
		if(!fixedProbFlag$sample65) {
			for(int m = 0; m < length$observed; m += 1)
				logProbability$sample65[((m - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample41)
			logProbabilityValue$sample41();
		logProbabilityValue$sample65();
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
		logProbabilityValue$sample41();
		logProbabilityValue$sample65();
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
		logProbabilityValue$sample41();
		logProbabilityValue$sample65();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		for(int i = 0; i < 10; i += 1) {
			double[] var30 = indirection1[i];
			for(int j = 0; j < length$observed; j += 1) {
				if(!fixedFlag$sample41)
					var30[j] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int k = 0; k < length$observed; k += 1) {
			double[] var45 = indirection2[k];
			for(int l = 0; l < 10; l += 1) {
				if(!fixedFlag$sample41)
					var45[l] = indirection1[l][k];
			}
		}
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		// Deep copy between arrays
		int[] cv$source1 = observed;
		int[] cv$target1 = generated;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		for(int k = 0; k < length$observed; k += 1) {
			double[] var45 = indirection2[k];
			for(int l = 0; l < 10; l += 1) {
				if(setFlag$indirection1)
					var45[l] = indirection1[l][k];
			}
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model ParallelMK5(int[] observed) {\n    int[] generated = new int[observed.length];\n    double[][] indirection1 = new double[10][observed.length];\n    double[][] indirection2 = new double[observed.length][10];\n\n    for(int i=0; i<10; i++) {\n        for(int j=0; j<observed.length; j++) {\n            indirection1[i][j] = uniform(0.0, 1.0).sample();\n        }\n    }\n    \n    for(int k=0; k<observed.length; k++) {\n        for(int l=0; l<10; l++) {\n            indirection2[k][l] = indirection1[l][k];\n        }\n    }\n    \n    for(int m=0; m<observed.length; m++) {\n        generated[m] = categorical(indirection2[m]).sample();\n    }\n\n    generated.observe(observed);\n}";
	}
}