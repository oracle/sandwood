package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Conditional4$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Conditional4$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private double[] cv$var4$stateProbabilityGlobal;
	private boolean fixedFlag$sample21 = false;
	private boolean fixedFlag$sample4 = false;
	private boolean fixedProbFlag$sample21 = false;
	private boolean fixedProbFlag$sample27 = false;
	private boolean fixedProbFlag$sample4 = false;
	private boolean guard;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$guard;
	private double logProbability$sample21;
	private double logProbability$value;
	private double logProbability$var18;
	private double logProbability$var24;
	private double observedValue;
	private boolean system$gibbsForward = true;
	private double value;

	public Conditional4$SingleThreadCPU(ExecutionTarget target) {
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
		
		// Unset the fixed probability flag for sample 21 as it depends on bias.
		fixedProbFlag$sample21 = false;
		
		// Unset the fixed probability flag for sample 27 as it depends on bias.
		fixedProbFlag$sample27 = false;
	}

	// Getter for fixedFlag$sample21.
	@Override
	public final boolean get$fixedFlag$sample21() {
		return fixedFlag$sample21;
	}

	// Setter for fixedFlag$sample21.
	@Override
	public final void set$fixedFlag$sample21(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample21 including if probabilities
		// need to be updated.
		fixedFlag$sample21 = cv$value;
		
		// Should the probability of sample 21 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample21 = (fixedFlag$sample21 && fixedProbFlag$sample21);
		
		// Should the probability of sample 27 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample27 = (fixedFlag$sample21 && fixedProbFlag$sample27);
	}

	// Getter for fixedFlag$sample4.
	@Override
	public final boolean get$fixedFlag$sample4() {
		return fixedFlag$sample4;
	}

	// Setter for fixedFlag$sample4.
	@Override
	public final void set$fixedFlag$sample4(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample4 including if probabilities
		// need to be updated.
		fixedFlag$sample4 = cv$value;
		
		// Should the probability of sample 4 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample4 = (fixedFlag$sample4 && fixedProbFlag$sample4);
		
		// Should the probability of sample 27 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample27 = (fixedFlag$sample4 && fixedProbFlag$sample27);
	}

	// Getter for guard.
	@Override
	public final boolean get$guard() {
		return guard;
	}

	// Setter for guard.
	@Override
	public final void set$guard(boolean cv$value) {
		// Set flags for all the side effects of guard including if probabilities need to
		// be updated.
		guard = cv$value;
		
		// Unset the fixed probability flag for sample 4 as it depends on guard.
		fixedProbFlag$sample4 = false;
		
		// Unset the fixed probability flag for sample 27 as it depends on guard.
		fixedProbFlag$sample27 = false;
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

	// Getter for logProbability$guard.
	@Override
	public final double get$logProbability$guard() {
		return logProbability$guard;
	}

	// Getter for logProbability$value.
	@Override
	public final double get$logProbability$value() {
		return logProbability$value;
	}

	// Getter for observedValue.
	@Override
	public final double get$observedValue() {
		return observedValue;
	}

	// Setter for observedValue.
	@Override
	public final void set$observedValue(double cv$value) {
		observedValue = cv$value;
	}

	// Getter for value.
	@Override
	public final double get$value() {
		return value;
	}

	// Calculate the probability of the samples represented by sample21 using sampled
	// values.
	private final void logProbabilityValue$sample21() {
		// Determine if we need to calculate the values for sample task 21 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample21) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			if(!guard) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					if(!guard) {
						// The sample value to calculate the probability of generating
						double cv$sampleValue = bias[0];
						{
							{
								double var16 = 0.0;
								double var17 = 0.5;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((var16 <= cv$sampleValue) && (cv$sampleValue < var17))?(-Math.log((var17 - var16))):Double.NEGATIVE_INFINITY));
								
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
				logProbability$var18 = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample21 = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample21 = fixedFlag$sample21;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			if(!guard) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample21;
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var18 = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample27 using sampled
	// values.
	private final void logProbabilityValue$sample27() {
		// Determine if we need to calculate the values for sample task 27 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample27) {
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
				double cv$sampleValue = value;
				{
					{
						double var22 = bias[0];
						double var23 = 1.0;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var22, var23));
						
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
			logProbability$var24 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$value = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample27 = (fixedFlag$sample4 && fixedFlag$sample21);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$value;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var24 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample4 using sampled values.
	private final void logProbabilityValue$sample4() {
		// Determine if we need to calculate the values for sample task 4 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample4) {
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
				boolean cv$sampleValue = guard;
				{
					{
						double var2 = 0.5;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var2:(1.0 - var2))));
						
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
			logProbability$bernoulli = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$guard = cv$sampleProbability;
			
			// Guard to ensure that bias is only updated once for this probability.
			boolean cv$guard$bias = false;
			
			// Add probability to constructed variables from the combined probability
			{
				if(guard) {
					// If the probability of the variable has not already been updated
					if(!cv$guard$bias) {
						// Set the guard so the update is only applied once.
						cv$guard$bias = true;
						
						// Update the variable probability
						logProbability$bias = (logProbability$bias + cv$accumulator);
					}
				}
			}
			{
				if(!guard) {
					// If the probability of the variable has not already been updated
					if(!cv$guard$bias) {
						// Set the guard so the update is only applied once.
						cv$guard$bias = true;
						
						// Update the variable probability
						logProbability$bias = (logProbability$bias + cv$accumulator);
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample4)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample4 = fixedFlag$sample4;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$guard;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bernoulli = cv$rvAccumulator;
			
			// Guard to ensure that bias is only updated once for this probability.
			boolean cv$guard$bias = false;
			
			// Add probability to constructed variables from the combined probability
			{
				if(guard) {
					// If the probability of the variable has not already been updated
					if(!cv$guard$bias) {
						// Set the guard so the update is only applied once.
						cv$guard$bias = true;
						
						// Update the variable probability
						logProbability$bias = (logProbability$bias + cv$accumulator);
					}
				}
			}
			{
				if(!guard) {
					// If the probability of the variable has not already been updated
					if(!cv$guard$bias) {
						// Set the guard so the update is only applied once.
						cv$guard$bias = true;
						
						// Update the variable probability
						logProbability$bias = (logProbability$bias + cv$accumulator);
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample4)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 21 drawn from Uniform 18. Inference was performed using Metropolis-Hastings.
	private final void sample21() {
		if(true) {
			// Calculate the number of states to evaluate.
			int cv$numNumStates = 0;
			{
				// Metropolis-Hastings
				cv$numNumStates = Math.max(cv$numNumStates, 2);
			}
			
			// The original value of the sample
			double cv$originalValue = bias[0];
			
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
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
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
						double var19 = cv$proposedValue;
						
						// Guards to ensure that bias is only updated when there is a valid path.
						{
							if(!guard) {
								{
									bias[0] = cv$currentValue;
								}
							}
						}
					}
				}
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$temp$0$var16;
					{
						cv$temp$0$var16 = 0.0;
					}
					double cv$temp$1$var17;
					{
						cv$temp$1$var17 = 0.5;
					}
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((cv$temp$0$var16 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var17))?(-Math.log((cv$temp$1$var17 - cv$temp$0$var16))):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 24.
					{
						// Looking for a path between Sample 21 and consumer Beta 24.
						{
							if(!guard) {
								double traceTempVariable$var22$2_1 = cv$currentValue;
								if((0 == 0)) {
									// Processing sample task 27 of consumer random variable null.
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
														double cv$temp$2$var22;
														{
															// Constructing a random variable input for use later.
															double var22 = traceTempVariable$var22$2_1;
															cv$temp$2$var22 = var22;
														}
														double cv$temp$3$var23;
														{
															cv$temp$3$var23 = 1.0;
														}
														
														// Record the probability of sample task 27 generating output with current configuration.
														if(((Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$2$var22, cv$temp$3$var23)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$2$var22, cv$temp$3$var23)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															// If the second value is -infinity.
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$2$var22, cv$temp$3$var23));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$2$var22, cv$temp$3$var23)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$2$var22, cv$temp$3$var23)));
														}
														
														// Recorded the probability of reaching sample task 27 with the current configuration.
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
				double var19 = cv$originalValue;
				
				// Guards to ensure that bias is only updated when there is a valid path.
				{
					if(!guard) {
						{
							bias[0] = var19;
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 4 drawn from bernoulli. Inference was performed using variable marginalization.
	private final void sample4() {
		if(true) {
			// Calculate the number of states to evaluate.
			int cv$numNumStates = 0;
			{
				// variable marginalization
				cv$numNumStates = Math.max(cv$numNumStates, 2);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var4$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				// Initialize the summed probabilities to 0.
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				
				// Initialize a counter to track the reached distributions.
				double cv$reachedDistributionSourceRV = 0.0;
				
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				double cv$accumulatedDistributionProbabilities = 0.0;
				
				// The value currently being tested
				boolean cv$currentValue;
				
				// Value of the variable at this index
				cv$currentValue = (cv$valuePos == 1);
				
				// Write out the new value of the sample.
				guard = cv$currentValue;
				
				// Guards to ensure that bias is only updated when there is a valid path.
				{
					if(cv$currentValue) {
						{
							bias[0] = 0.5;
						}
					}
				}
				
				// Guards to ensure that bias is only updated when there is a valid path.
				{
					if(!cv$currentValue) {
						{
							bias[0] = (0.0 + ((0.5 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
						}
					}
				}
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$temp$0$var2;
					{
						cv$temp$0$var2 = 0.5;
					}
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + Math.log((cv$currentValue?cv$temp$0$var2:(1.0 - cv$temp$0$var2))));
					
					// Processing conditional point22.
					{
						{
							if(!cv$currentValue) {
								{
									// Looking for a path between Put 22 and consumer Beta 24.
									{
										if(!cv$currentValue) {
											if((0 == 0)) {
												// Processing sample task 27 of consumer random variable null.
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
																	{
																		double cv$temp$1$var22;
																		{
																			// Constructing a random variable input for use later.
																			double var22 = bias[0];
																			cv$temp$1$var22 = var22;
																		}
																		double cv$temp$2$var23;
																		{
																			cv$temp$2$var23 = 1.0;
																		}
																		
																		// Record the probability of sample task 27 generating output with current configuration.
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$1$var22, cv$temp$2$var23)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$1$var22, cv$temp$2$var23)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$1$var22, cv$temp$2$var23));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$1$var22, cv$temp$2$var23)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$1$var22, cv$temp$2$var23)));
																		}
																		
																		// Recorded the probability of reaching sample task 27 with the current configuration.
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
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
									{
										if(!cv$currentValue) {
											// Processing sample task 21 of consumer random variable null.
											{
												if(!cv$currentValue) {
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
																	double cv$temp$3$var16;
																	{
																		cv$temp$3$var16 = 0.0;
																	}
																	double cv$temp$4$var17;
																	{
																		cv$temp$4$var17 = 0.5;
																	}
																	
																	// Record the probability of sample task 21 generating output with current configuration.
																	if(((Math.log(1.0) + (((cv$temp$3$var16 <= bias[0]) && (bias[0] < cv$temp$4$var17))?(-Math.log((cv$temp$4$var17 - cv$temp$3$var16))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((cv$temp$3$var16 <= bias[0]) && (bias[0] < cv$temp$4$var17))?(-Math.log((cv$temp$4$var17 - cv$temp$3$var16))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((cv$temp$3$var16 <= bias[0]) && (bias[0] < cv$temp$4$var17))?(-Math.log((cv$temp$4$var17 - cv$temp$3$var16))):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((cv$temp$3$var16 <= bias[0]) && (bias[0] < cv$temp$4$var17))?(-Math.log((cv$temp$4$var17 - cv$temp$3$var16))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((cv$temp$3$var16 <= bias[0]) && (bias[0] < cv$temp$4$var17))?(-Math.log((cv$temp$4$var17 - cv$temp$3$var16))):Double.NEGATIVE_INFINITY)));
																	}
																	
																	// Recorded the probability of reaching sample task 21 with the current configuration.
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
					
					// Processing conditional point16.
					{
						{
							if(cv$currentValue) {
								{
									// Looking for a path between Put 16 and consumer Beta 24.
									{
										if(cv$currentValue) {
											double traceTempVariable$var22$10_1 = 0.5;
											if((0 == 0)) {
												// Processing sample task 27 of consumer random variable null.
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
																	{
																		double cv$temp$5$var22;
																		{
																			// Constructing a random variable input for use later.
																			double var22 = bias[0];
																			cv$temp$5$var22 = var22;
																		}
																		double cv$temp$6$var23;
																		{
																			cv$temp$6$var23 = 1.0;
																		}
																		
																		// Record the probability of sample task 27 generating output with current configuration.
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$5$var22, cv$temp$6$var23)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$5$var22, cv$temp$6$var23)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$5$var22, cv$temp$6$var23));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$5$var22, cv$temp$6$var23)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$5$var22, cv$temp$6$var23)));
																		}
																		
																		// Recorded the probability of reaching sample task 27 with the current configuration.
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
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
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			
			// The sum of all the probabilities in log space
			double cv$logSum = 0.0;
			
			// Sum all the values
			{
				// Initialise the max to the first element.
				double cv$lseMax = cv$stateProbabilityLocal[0];
				
				// Find max value.
				for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
					double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
					if((cv$lseMax < cv$lseElementValue))
						cv$lseMax = cv$lseElementValue;
				}
				
				// If the maximum value is -infinity return -infinity.
				if((cv$lseMax == Double.NEGATIVE_INFINITY))
					cv$logSum = Double.NEGATIVE_INFINITY;
				
				// Sum the values in the array.
				else {
					// Initialise the sum of the array elements
					double cv$lseSum = 0.0;
					
					// Offset values, move to normal space, and sum.
					for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
						cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
					
					// Increment the value of the target, moving the value back into log space.
					cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
				}
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numNumStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			guard = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates) == 1);
			
			// Guards to ensure that bias is only updated when there is a valid path.
			{
				if(guard) {
					{
						bias[0] = 0.5;
					}
				}
			}
			
			// Guards to ensure that bias is only updated when there is a valid path.
			{
				if(!guard) {
					{
						bias[0] = (0.0 + ((0.5 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
					}
				}
			}
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocation of cv$var4$stateProbabilityGlobal for single threaded execution
		cv$var4$stateProbabilityGlobal = new double[2];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If bias has not been set already allocate space.
		if(!(fixedFlag$sample4 && fixedFlag$sample21)) {
			// Constructor for bias
			{
				bias = new double[1];
			}
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(guard)
			bias[0] = 0.5;
		else {
			if(!fixedFlag$sample21)
				bias[0] = (0.0 + ((0.5 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		value = DistributionSampling.sampleBeta(RNG$, bias[0], 1.0);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(guard)
			bias[0] = 0.5;
		else {
			if(!fixedFlag$sample21)
				bias[0] = (0.0 + ((0.5 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(guard)
			bias[0] = 0.5;
		else {
			if(!fixedFlag$sample21)
				bias[0] = (0.0 + ((0.5 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample4)
				sample4();
			if(!guard) {
				if(!fixedFlag$sample21)
					sample21();
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!guard) {
				if(!fixedFlag$sample21)
					sample21();
			}
			if(!fixedFlag$sample4)
				sample4();
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
		logProbability$bernoulli = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample4)
			logProbability$guard = 0.0;
		logProbability$var18 = 0.0;
		if(!fixedProbFlag$sample21)
			logProbability$sample21 = 0.0;
		logProbability$var24 = 0.0;
		if(!fixedProbFlag$sample27)
			logProbability$value = 0.0;
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
		if(fixedFlag$sample4)
			logProbabilityValue$sample4();
		if(fixedFlag$sample21)
			logProbabilityValue$sample21();
		logProbabilityValue$sample27();
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
		logProbabilityValue$sample4();
		logProbabilityValue$sample21();
		logProbabilityValue$sample27();
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
		logProbabilityValue$sample4();
		logProbabilityValue$sample21();
		logProbabilityValue$sample27();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(guard)
			bias[0] = 0.5;
		else {
			if(!fixedFlag$sample21)
				bias[0] = (0.0 + ((0.5 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		value = observedValue;
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		if(guard)
			bias[0] = 0.5;
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
		     + "public model Conditional4(double observedValue)  {\n"
		     + "        \n"
		     + "    //Construct a bernoulli\n"
		     + "    Bernoulli bernoulli = bernoulli(0.5);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    boolean guard = bernoulli.sample();\n"
		     + "    \n"
		     + "    double[] bias = new double[1];\n"
		     + "        \n"
		     + "    if(guard)\n"
		     + "        bias[0] = 0.5;\n"
		     + "    else\n"
		     + "        bias[0] = uniform(0.0, 0.5).sample();\n"
		     + "    \n"
		     + "    double value = beta(bias[0],1.0).sample();\n"
		     + "    \n"
		     + "    //Link the sampled value to the observed value\n"
		     + "    value.observe(observedValue);\n"
		     + "}";
	}
}