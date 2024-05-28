package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Conditional2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Conditional2$CoreInterface {
	
	// Declare the variables for the model.
	private double[] cv$var6$stateProbabilityGlobal;
	private boolean fixedFlag$sample24 = false;
	private boolean fixedFlag$sample7 = false;
	private boolean fixedProbFlag$sample24 = false;
	private boolean fixedProbFlag$sample7 = false;
	private boolean guard;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$guard;
	private double logProbability$sample24;
	private double logProbability$value;
	private double logProbability$value2;
	private double logProbability$var20;
	private double[] observedValue;
	private boolean setFlag$value = false;
	private boolean system$gibbsForward = true;
	private double[] value;
	private double[] value2;

	public Conditional2$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for fixedFlag$sample24.
	@Override
	public final boolean get$fixedFlag$sample24() {
		return fixedFlag$sample24;
	}

	// Setter for fixedFlag$sample24.
	@Override
	public final void set$fixedFlag$sample24(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample24 including if probabilities
		// need to be updated.
		fixedFlag$sample24 = cv$value;
		
		// Should the probability of sample 24 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample24 = (fixedFlag$sample24 && fixedProbFlag$sample24);
	}

	// Getter for fixedFlag$sample7.
	@Override
	public final boolean get$fixedFlag$sample7() {
		return fixedFlag$sample7;
	}

	// Setter for fixedFlag$sample7.
	@Override
	public final void set$fixedFlag$sample7(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample7 including if probabilities
		// need to be updated.
		fixedFlag$sample7 = cv$value;
		
		// Should the probability of sample 7 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample7 = (fixedFlag$sample7 && fixedProbFlag$sample7);
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
		
		// Unset the fixed probability flag for sample 7 as it depends on guard.
		fixedProbFlag$sample7 = false;
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

	// Getter for logProbability$value2.
	@Override
	public final double get$logProbability$value2() {
		return logProbability$value2;
	}

	// Getter for observedValue.
	@Override
	public final double[] get$observedValue() {
		return observedValue;
	}

	// Setter for observedValue.
	@Override
	public final void set$observedValue(double[] cv$value) {
		// Set observedValue with flag to mark that it has been set so another array doesn't
		// need to be constructed
		observedValue = cv$value;
	}

	// Getter for value.
	@Override
	public final double[] get$value() {
		return value;
	}

	// Setter for value.
	@Override
	public final void set$value(double[] cv$value) {
		// Set flags for all the side effects of value including if probabilities need to
		// be updated.
		// Set value with flag to mark that it has been set so another array doesn't need
		// to be constructed
		value = cv$value;
		setFlag$value = true;
		
		// Unset the fixed probability flag for sample 24 as it depends on value.
		fixedProbFlag$sample24 = false;
	}

	// Getter for value2.
	@Override
	public final double[] get$value2() {
		return value2;
	}

	// Calculate the probability of the samples represented by sample24 using sampled
	// values.
	private final void logProbabilityValue$sample24() {
		// Determine if we need to calculate the values for sample task 24 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample24) {
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
						double cv$sampleValue = value[0];
						{
							{
								double var18 = 0.0;
								double var19 = 1.0;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((var18 <= cv$sampleValue) && (cv$sampleValue <= var19))?(-Math.log((var19 - var18))):Double.NEGATIVE_INFINITY));
								
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
				logProbability$var20 = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample24 = cv$sampleProbability;
			}
			
			// Guard to ensure that value2 is only updated once for this probability.
			boolean cv$guard$value2 = false;
			
			// Update the variable probability
			logProbability$value = (logProbability$value + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			// 
			// Looking for a path between Sample 24 and consumer double[] 29.
			{
				if(!guard) {
					if((0 == 0)) {
						// If the probability of the variable has not already been updated
						if(!cv$guard$value2) {
							// Set the guard so the update is only applied once.
							cv$guard$value2 = true;
							
							// Update the variable probability
							logProbability$value2 = (logProbability$value2 + cv$accumulator);
						}
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample24 = fixedFlag$sample24;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			if(!guard) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample24;
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var20 = cv$rvAccumulator;
			}
			
			// Guard to ensure that value2 is only updated once for this probability.
			boolean cv$guard$value2 = false;
			
			// Update the variable probability
			logProbability$value = (logProbability$value + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			// 
			// Looking for a path between Sample 24 and consumer double[] 29.
			{
				if(!guard) {
					if((0 == 0)) {
						// If the probability of the variable has not already been updated
						if(!cv$guard$value2) {
							// Set the guard so the update is only applied once.
							cv$guard$value2 = true;
							
							// Update the variable probability
							logProbability$value2 = (logProbability$value2 + cv$accumulator);
						}
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample7 using sampled values.
	private final void logProbabilityValue$sample7() {
		// Determine if we need to calculate the values for sample task 7 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample7) {
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
						double var4 = 0.5;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var4));
						{
							if(guard) {
								// Looking for a path between Put 19 and consumer double[] 29.
								{
									if(guard) {
										if((0 == 0)) {
											// If the observed value does not match the provided value the probability of generating
											// this random variable is zero
											if(!(value2[0] == 1.0))
												cv$weightedProbability = Double.NEGATIVE_INFINITY;
										}
									}
								}
							}
						}
						
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
			
			// Guard to ensure that value is only updated once for this probability.
			boolean cv$guard$value = false;
			
			// Guard to ensure that value2 is only updated once for this probability.
			boolean cv$guard$value2 = false;
			
			// Add probability to constructed variables from the combined probability
			{
				if(guard) {
					// If the probability of the variable has not already been updated
					if(!cv$guard$value) {
						// Set the guard so the update is only applied once.
						cv$guard$value = true;
						
						// Update the variable probability
						logProbability$value = (logProbability$value + cv$accumulator);
					}
				}
			}
			{
				if(!guard) {
					// If the probability of the variable has not already been updated
					if(!cv$guard$value) {
						// Set the guard so the update is only applied once.
						cv$guard$value = true;
						
						// Update the variable probability
						logProbability$value = (logProbability$value + cv$accumulator);
					}
				}
			}
			{
				if(guard) {
					if((0 == 0)) {
						// If the probability of the variable has not already been updated
						if(!cv$guard$value2) {
							// Set the guard so the update is only applied once.
							cv$guard$value2 = true;
							
							// Update the variable probability
							logProbability$value2 = (logProbability$value2 + cv$accumulator);
						}
					}
				}
				if(!guard) {
					if((0 == 0)) {
						// If the probability of the variable has not already been updated
						if(!cv$guard$value2) {
							// Set the guard so the update is only applied once.
							cv$guard$value2 = true;
							
							// Update the variable probability
							logProbability$value2 = (logProbability$value2 + cv$accumulator);
						}
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample7)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample7 = fixedFlag$sample7;
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
			
			// Guard to ensure that value is only updated once for this probability.
			boolean cv$guard$value = false;
			
			// Guard to ensure that value2 is only updated once for this probability.
			boolean cv$guard$value2 = false;
			
			// Add probability to constructed variables from the combined probability
			{
				if(guard) {
					// If the probability of the variable has not already been updated
					if(!cv$guard$value) {
						// Set the guard so the update is only applied once.
						cv$guard$value = true;
						
						// Update the variable probability
						logProbability$value = (logProbability$value + cv$accumulator);
					}
				}
			}
			{
				if(!guard) {
					// If the probability of the variable has not already been updated
					if(!cv$guard$value) {
						// Set the guard so the update is only applied once.
						cv$guard$value = true;
						
						// Update the variable probability
						logProbability$value = (logProbability$value + cv$accumulator);
					}
				}
			}
			{
				if(guard) {
					if((0 == 0)) {
						// If the probability of the variable has not already been updated
						if(!cv$guard$value2) {
							// Set the guard so the update is only applied once.
							cv$guard$value2 = true;
							
							// Update the variable probability
							logProbability$value2 = (logProbability$value2 + cv$accumulator);
						}
					}
				}
				if(!guard) {
					if((0 == 0)) {
						// If the probability of the variable has not already been updated
						if(!cv$guard$value2) {
							// Set the guard so the update is only applied once.
							cv$guard$value2 = true;
							
							// Update the variable probability
							logProbability$value2 = (logProbability$value2 + cv$accumulator);
						}
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample7)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 7 drawn from bernoulli. Inference was performed using variable marginalization.
	private final void sample7() {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		{
			// variable marginalization
			cv$noStates = Math.max(cv$noStates, 2);
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var6$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
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
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var4;
				{
					cv$temp$0$var4 = 0.5;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$currentValue, cv$temp$0$var4));
				
				// Processing conditional point19.
				{
					{
						if(cv$currentValue) {
							{
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								
								// Processing observed variable value2
								{
									{
										// Looking for a path between Put 19 and consumer double[] 29.
										{
											if(cv$currentValue) {
												if((0 == 0)) {
													// Check observed variable is possible
													if((value2[0] == 1.0)) {
														// Record if the conditional is valid.
														if((Math.log(1.0) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((Math.log(1.0) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															// If the second value is -infinity.
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = Math.log(1.0);
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(1.0))) + 1)) + Math.log(1.0));
														}
													}
													
													// Recorded the probability of reaching branch with the current configuration.
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
				
				// Processing conditional point25.
				{
					{
						if(!cv$currentValue) {
							{
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								
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
			for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
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
				for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the new value of the sample.
		guard = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal) == 1);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocation of cv$var6$stateProbabilityGlobal for single threaded execution
		cv$var6$stateProbabilityGlobal = new double[2];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If value has not been set already allocate space.
		if(!setFlag$value) {
			// Constructor for value
			{
				value = new double[1];
			}
		}
		
		// Constructor for value2
		{
			value2 = new double[1];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample7)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(guard) {
			if(!fixedFlag$sample7)
				value[0] = 1.0;
		} else {
			if(!(fixedFlag$sample7 && fixedFlag$sample24))
				value[0] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		if(!(fixedFlag$sample7 && fixedFlag$sample24))
			value2[0] = value[0];
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample7)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample7)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample7)
				sample7();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!fixedFlag$sample7)
				sample7();
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
		logProbability$value = 0.0;
		logProbability$value2 = 0.0;
		if(!fixedProbFlag$sample7)
			logProbability$guard = 0.0;
		logProbability$var20 = 0.0;
		if(!fixedProbFlag$sample24)
			logProbability$sample24 = 0.0;
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
		if(fixedFlag$sample7)
			logProbabilityValue$sample7();
		logProbabilityValue$sample24();
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
		logProbabilityValue$sample7();
		logProbabilityValue$sample24();
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
		logProbabilityValue$sample7();
		logProbabilityValue$sample24();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		// 
		// Generate sample values for every call to sample in the model.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		{
			// Deep copy between arrays
			double[] cv$source1 = observedValue;
			double[] cv$target1 = value2;
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				cv$target1[cv$index1] = cv$source1[cv$index1];
		}
		{
			{
				value[0] = value2[0];
			}
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		if(guard) {
			if(true)
				value[0] = 1.0;
		}
		if(setFlag$value)
			value2[0] = value[0];
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Conditional2(double[] observedValue)  {\n        \n    //Construct a bernoulli\n    Bernoulli bernoulli = bernoulli(0.5);\n                \n    //Sample from it\n    boolean guard = bernoulli.sample();\n    \n    double[] value = new double[1];\n        \n    if(guard)\n        value[0] = 1.0;\n    else\n        value[0] = uniform(0.0, 1.0).sample();\n    \n    double [] value2 = new double[1];\n    \n    value2[0] = value[0];\n    \n    //Link the sampled value to the observed value\n    value2.observe(observedValue);\n}";
	}
}