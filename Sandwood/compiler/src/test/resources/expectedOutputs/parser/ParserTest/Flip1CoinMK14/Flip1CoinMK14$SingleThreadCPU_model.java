package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK14$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip1CoinMK14$CoreInterface {
	
	// Declare the variables for the model.
	private double b;
	private double bias;
	private double[] c;
	private boolean fixedFlag$sample12 = false;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedProbFlag$sample12 = false;
	private boolean fixedProbFlag$sample29 = false;
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
	private double logProbability$var25;
	private double logProbability$var9;
	private int samples;
	private boolean setFlag$flips = false;
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
		
		// Unset the fixed probability flag for sample 12 as it depends on b.
		fixedProbFlag$sample12 = false;
		
		// Unset the fixed probability flag for sample 29 as it depends on b.
		fixedProbFlag$sample29 = false;
	}

	// Getter for bias.
	@Override
	public final double get$bias() {
		return bias;
	}

	// Getter for fixedFlag$sample12.
	@Override
	public final boolean get$fixedFlag$sample12() {
		return fixedFlag$sample12;
	}

	// Setter for fixedFlag$sample12.
	@Override
	public final void set$fixedFlag$sample12(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample12 including if probabilities
		// need to be updated.
		fixedFlag$sample12 = cv$value;
		
		// Should the probability of sample 12 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample12 = (fixedFlag$sample12 && fixedProbFlag$sample12);
		
		// Should the probability of sample 29 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample29 = (fixedFlag$sample12 && fixedProbFlag$sample29);
	}

	// Getter for fixedFlag$sample29.
	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	// Setter for fixedFlag$sample29.
	@Override
	public final void set$fixedFlag$sample29(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample29 including if probabilities
		// need to be updated.
		fixedFlag$sample29 = cv$value;
		
		// Should the probability of sample 29 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample29 = (fixedFlag$sample29 && fixedProbFlag$sample29);
	}

	// Getter for flips.
	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	// Setter for flips.
	@Override
	public final void set$flips(boolean[] cv$value) {
		// Set flags for all the side effects of flips including if probabilities need to
		// be updated.
		// Set flips with flag to mark that it has been set so another array doesn't need
		// to be constructed
		flips = cv$value;
		setFlag$flips = true;
		
		// Unset the fixed probability flag for sample 29 as it depends on flips.
		fixedProbFlag$sample29 = false;
	}

	// Getter for flipsMeasured.
	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	// Setter for flipsMeasured.
	@Override
	public final void set$flipsMeasured(boolean[] cv$value) {
		// Set flipsMeasured with flag to mark that it has been set so another array doesn't
		// need to be constructed
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

	// Calculate the probability of the samples represented by sample12 using sampled
	// values.
	private final void logProbabilityValue$sample12() {
		// Determine if we need to calculate the values for sample task 12 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample12) {
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
				double cv$sampleValue = b;
				{
					{
						double var6 = 1.0;
						double var8 = 1.0;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var6, var8));
						
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
			logProbability$var9 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$b = cv$sampleProbability;
			
			// Guard to ensure that c is only updated once for this probability.
			boolean cv$guard$c = false;
			
			// Guard to ensure that bias is only updated once for this probability.
			boolean cv$guard$bias = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$c) {
					// Set the guard so the update is only applied once.
					cv$guard$c = true;
					
					// Update the variable probability
					logProbability$c = (logProbability$c + cv$accumulator);
				}
			}
			
			// Looking for a path between Sample 12 and consumer double 19.
			{
				if(guard1) {
					double traceTempVariable$bias$3_1 = b;
					
					// If the probability of the variable has not already been updated
					if(!cv$guard$bias) {
						// Set the guard so the update is only applied once.
						cv$guard$bias = true;
						
						// Update the variable probability
						logProbability$bias = (logProbability$bias + cv$accumulator);
					}
				}
				if(!guard1) {
					if(!guard1) {
						if((0 == 0)) {
							if(!guard1) {
								double traceTempVariable$bias$4_1 = c[0];
								
								// If the probability of the variable has not already been updated
								if(!cv$guard$bias) {
									// Set the guard so the update is only applied once.
									cv$guard$bias = true;
									
									// Update the variable probability
									logProbability$bias = (logProbability$bias + cv$accumulator);
								}
							}
						}
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample12 = fixedFlag$sample12;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$b;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var9 = cv$rvAccumulator;
			
			// Guard to ensure that c is only updated once for this probability.
			boolean cv$guard$c = false;
			
			// Guard to ensure that bias is only updated once for this probability.
			boolean cv$guard$bias = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$c) {
					// Set the guard so the update is only applied once.
					cv$guard$c = true;
					
					// Update the variable probability
					logProbability$c = (logProbability$c + cv$accumulator);
				}
			}
			
			// Looking for a path between Sample 12 and consumer double 19.
			{
				if(guard1) {
					double traceTempVariable$bias$6_1 = b;
					
					// If the probability of the variable has not already been updated
					if(!cv$guard$bias) {
						// Set the guard so the update is only applied once.
						cv$guard$bias = true;
						
						// Update the variable probability
						logProbability$bias = (logProbability$bias + cv$accumulator);
					}
				}
				if(!guard1) {
					if(!guard1) {
						if((0 == 0)) {
							if(!guard1) {
								double traceTempVariable$bias$7_1 = c[0];
								
								// If the probability of the variable has not already been updated
								if(!cv$guard$bias) {
									// Set the guard so the update is only applied once.
									cv$guard$bias = true;
									
									// Update the variable probability
									logProbability$bias = (logProbability$bias + cv$accumulator);
								}
							}
						}
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample29 using sampled
	// values.
	private final void logProbabilityValue$sample29() {
		// Determine if we need to calculate the values for sample task 29 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample29) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var24 = 0; var24 < samples; var24 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flips[var24];
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
			logProbability$bernoulli = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var25 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample29 = (fixedFlag$sample29 && fixedFlag$sample12);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var25;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bernoulli = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 12 drawn from Beta 9. Inference was performed using Metropolis-Hastings.
	private final void sample12() {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		{
			// Metropolis-Hastings
			cv$noStates = Math.max(cv$noStates, 2);
		}
		
		// The original value of the sample
		double cv$originalValue = b;
		
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
					b = cv$proposedValue;
					
					// Guards to ensure that c is only updated when there is a valid path.
					{
						{
							c[0] = (cv$currentValue / 2);
						}
					}
					
					// Guards to ensure that bias is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 12 and consumer double 19.
					{
						if(guard1) {
							double traceTempVariable$bias$2_1 = cv$currentValue;
							{
								// Write out the new sample value.
								bias = traceTempVariable$bias$2_1;
							}
						}
						if(!guard1) {
							if(!guard1) {
								if((0 == 0)) {
									if(!guard1) {
										double traceTempVariable$bias$3_1 = c[0];
										{
											// Write out the new sample value.
											bias = traceTempVariable$bias$3_1;
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
				double cv$temp$0$var6;
				{
					cv$temp$0$var6 = 1.0;
				}
				double cv$temp$1$var8;
				{
					cv$temp$1$var8 = 1.0;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, cv$temp$0$var6, cv$temp$1$var8));
				
				// Processing random variable 20.
				{
					// Looking for a path between Sample 12 and consumer Bernoulli 20.
					{
						if(guard1) {
							double traceTempVariable$bias$4_1 = cv$currentValue;
							double traceTempVariable$b$4_2 = cv$currentValue;
							
							// Processing sample task 29 of consumer random variable bernoulli.
							{
								for(int var24 = 0; var24 < samples; var24 += 1) {
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
													double cv$temp$2$bias;
													{
														cv$temp$2$bias = traceTempVariable$bias$4_1;
													}
													
													// Record the probability of sample task 29 generating output with current configuration.
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var24], cv$temp$2$bias)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var24], cv$temp$2$bias)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var24], cv$temp$2$bias));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var24], cv$temp$2$bias)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var24], cv$temp$2$bias)));
													}
													
													// Recorded the probability of reaching sample task 29 with the current configuration.
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
						if(!guard1) {
							double traceTempVariable$b$5_1 = cv$currentValue;
							double traceTempVariable$var18$5_2 = (traceTempVariable$b$5_1 / 2);
							if(!guard1) {
								if((0 == 0)) {
									if(!guard1) {
										double traceTempVariable$bias$5_3 = traceTempVariable$var18$5_2;
										
										// Processing sample task 29 of consumer random variable bernoulli.
										{
											for(int var24 = 0; var24 < samples; var24 += 1) {
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
																double cv$temp$3$bias;
																{
																	cv$temp$3$bias = traceTempVariable$bias$5_3;
																}
																
																// Record the probability of sample task 29 generating output with current configuration.
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var24], cv$temp$3$bias)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var24], cv$temp$3$bias)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var24], cv$temp$3$bias));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var24], cv$temp$3$bias)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var24], cv$temp$3$bias)));
																}
																
																// Recorded the probability of reaching sample task 29 with the current configuration.
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
			b = cv$originalValue;
			
			// Guards to ensure that c is only updated when there is a valid path.
			{
				{
					c[0] = (b / 2);
				}
			}
			
			// Guards to ensure that bias is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 12 and consumer double 19.
			{
				if(guard1) {
					double traceTempVariable$bias$11_1 = b;
					{
						// Write out the new sample value.
						bias = traceTempVariable$bias$11_1;
					}
				}
				if(!guard1) {
					if(!guard1) {
						if((0 == 0)) {
							if(!guard1) {
								double traceTempVariable$bias$12_1 = c[0];
								{
									// Write out the new sample value.
									bias = traceTempVariable$bias$12_1;
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
		// Constructor for c
		{
			if(!guard1)
				c = new double[1];
		}
		
		// If flips has not been set already allocate space.
		if(!setFlag$flips) {
			// Constructor for flips
			{
				flips = new boolean[length$flipsMeasured];
			}
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample12)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!guard1) {
			if(!fixedFlag$sample12)
				c[0] = (b / 2);
			if(!fixedFlag$sample12)
				bias = c[0];
		}
		if(!fixedFlag$sample12)
			bias = b;
		for(int var24 = 0; var24 < samples; var24 += 1) {
			if(!fixedFlag$sample29)
				flips[var24] = DistributionSampling.sampleBernoulli(RNG$, bias);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample12)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!guard1) {
			if(!fixedFlag$sample12)
				c[0] = (b / 2);
			if(!fixedFlag$sample12)
				bias = c[0];
		}
		if(!fixedFlag$sample12)
			bias = b;
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample12)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!guard1) {
			if(!fixedFlag$sample12)
				c[0] = (b / 2);
			if(!fixedFlag$sample12)
				bias = c[0];
		}
		if(!fixedFlag$sample12)
			bias = b;
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample12)
				sample12();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!fixedFlag$sample12)
				sample12();
		}
		
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
		logProbability$var9 = 0.0;
		logProbability$bias = 0.0;
		logProbability$c = 0.0;
		if(!fixedProbFlag$sample12)
			logProbability$b = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$var25 = 0.0;
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
		if(fixedFlag$sample12)
			logProbabilityValue$sample12();
		logProbabilityValue$sample29();
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
		logProbabilityValue$sample12();
		logProbabilityValue$sample29();
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
		logProbabilityValue$sample12();
		logProbabilityValue$sample29();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample12)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!guard1) {
			if(!fixedFlag$sample12)
				c[0] = (b / 2);
			if(!fixedFlag$sample12)
				bias = c[0];
		}
		if(!fixedFlag$sample12)
			bias = b;
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		for(int i = (samples - ((((samples - 1) - 0) % 1) + 1)); i >= ((0 - 1) + 1); i -= 1)
			flips[i] = flipsMeasured[i];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		if(!guard1) {
			if(true)
				c[0] = (b / 2);
			bias = c[0];
		}
		if(true)
			bias = b;
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK14(boolean[] flipsMeasured, boolean guard1) {\n    int samples = flipsMeasured.length;\n        \n    double b = beta(1.0, 1).sample();\n    double bias;\n    if(guard1)\n      bias = b;\n    else {\n      double[] c = new double[1];\n      c[0] = b/2;\n      bias = c[0];\n    }\n        \n    Bernoulli bernoulli = bernoulli(bias);\n    boolean[] flips = bernoulli.sample(samples);\n\n    for(int i:[0..samples))\n        flips[i].observe(flipsMeasured[i]);\n}\n";
	}
}