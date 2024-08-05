package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK15$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK15$CoreInterface {
	
	// Declare the variables for the model.
	private double b;
	private double bias;
	private double[] c;
	private boolean fixedFlag$sample12 = false;
	private boolean fixedFlag$sample40 = false;
	private boolean fixedProbFlag$sample12 = false;
	private boolean fixedProbFlag$sample40 = false;
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
	private double logProbability$var35;
	private double logProbability$var9;
	private int samples;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK15$MultiThreadCPU(ExecutionTarget target) {
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
		b = cv$value;
	}

	// Getter for bias.
	@Override
	public final double get$bias() {
		return bias;
	}

	// Setter for bias.
	@Override
	public final void set$bias(double cv$value) {
		bias = cv$value;
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
		
		// Should the probability of sample 40 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample40 = (fixedFlag$sample12 && fixedProbFlag$sample40);
	}

	// Getter for fixedFlag$sample40.
	@Override
	public final boolean get$fixedFlag$sample40() {
		return fixedFlag$sample40;
	}

	// Setter for fixedFlag$sample40.
	@Override
	public final void set$fixedFlag$sample40(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample40 including if probabilities
		// need to be updated.
		fixedFlag$sample40 = cv$value;
		
		// Should the probability of sample 40 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample40 = (fixedFlag$sample40 && fixedProbFlag$sample40);
	}

	// Getter for flips.
	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	// Setter for flips.
	@Override
	public final void set$flips(boolean[] cv$value) {
		// Set flips with flag to mark that it has been set so another array doesn't need
		// to be constructed
		flips = cv$value;
		setFlag$flips = true;
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
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$c) {
					// Set the guard so the update is only applied once.
					cv$guard$c = true;
					
					// Update the variable probability
					logProbability$c = (logProbability$c + cv$accumulator);
				}
			}
			{
				if(guard1) {
					double traceTempVariable$bias$4_1 = b;
					
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
						if(((0 <= 0) && (0 < 2))) {
							{
								if(!guard1) {
									// Reduction of array c
									// 
									// A generated name to prevent name collisions if the reduction is implemented more
									// than once in inference and probability code. Initialize the variable to the unit
									// value
									double reduceVar$var28$21 = 0.0;
									
									// For each index in the array to be reduced
									for(int cv$reduction489Index = 0; cv$reduction489Index < 2; cv$reduction489Index += 1) {
										// Set the left hand term of the reduction function to the return variable value.
										double i$var25 = reduceVar$var28$21;
										
										// Set the right hand term to a value from the array c
										double j = c[cv$reduction489Index];
										
										// Execute the reduction function, saving the result into the return value.
										// 
										// Copy the result of the reduction into the variable returned by the reduction.
										reduceVar$var28$21 = (i$var25 + j);
									}
									double traceTempVariable$bias$5_1 = reduceVar$var28$21;
									
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
				if(!guard1) {
					if(!guard1) {
						if(((0 <= 1) && (1 < 2))) {
							{
								if(!guard1) {
									// Reduction of array c
									// 
									// A generated name to prevent name collisions if the reduction is implemented more
									// than once in inference and probability code. Initialize the variable to the unit
									// value
									double reduceVar$var28$22 = 0.0;
									
									// For each index in the array to be reduced
									for(int cv$reduction500Index = 0; cv$reduction500Index < 2; cv$reduction500Index += 1) {
										// Set the left hand term of the reduction function to the return variable value.
										double i$var25 = reduceVar$var28$22;
										
										// Set the right hand term to a value from the array c
										double j = c[cv$reduction500Index];
										
										// Execute the reduction function, saving the result into the return value.
										// 
										// Copy the result of the reduction into the variable returned by the reduction.
										reduceVar$var28$22 = (i$var25 + j);
									}
									double traceTempVariable$bias$6_1 = reduceVar$var28$22;
									
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
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$c) {
					// Set the guard so the update is only applied once.
					cv$guard$c = true;
					
					// Update the variable probability
					logProbability$c = (logProbability$c + cv$accumulator);
				}
			}
			{
				if(guard1) {
					double traceTempVariable$bias$9_1 = b;
					
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
						if(((0 <= 0) && (0 < 2))) {
							{
								if(!guard1) {
									// Reduction of array c
									// 
									// A generated name to prevent name collisions if the reduction is implemented more
									// than once in inference and probability code. Initialize the variable to the unit
									// value
									double reduceVar$var28$23 = 0.0;
									
									// For each index in the array to be reduced
									for(int cv$reduction518Index = 0; cv$reduction518Index < 2; cv$reduction518Index += 1) {
										// Set the left hand term of the reduction function to the return variable value.
										double i$var25 = reduceVar$var28$23;
										
										// Set the right hand term to a value from the array c
										double j = c[cv$reduction518Index];
										
										// Execute the reduction function, saving the result into the return value.
										// 
										// Copy the result of the reduction into the variable returned by the reduction.
										reduceVar$var28$23 = (i$var25 + j);
									}
									double traceTempVariable$bias$10_1 = reduceVar$var28$23;
									
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
				if(!guard1) {
					if(!guard1) {
						if(((0 <= 1) && (1 < 2))) {
							{
								if(!guard1) {
									// Reduction of array c
									// 
									// A generated name to prevent name collisions if the reduction is implemented more
									// than once in inference and probability code. Initialize the variable to the unit
									// value
									double reduceVar$var28$24 = 0.0;
									
									// For each index in the array to be reduced
									for(int cv$reduction529Index = 0; cv$reduction529Index < 2; cv$reduction529Index += 1) {
										// Set the left hand term of the reduction function to the return variable value.
										double i$var25 = reduceVar$var28$24;
										
										// Set the right hand term to a value from the array c
										double j = c[cv$reduction529Index];
										
										// Execute the reduction function, saving the result into the return value.
										// 
										// Copy the result of the reduction into the variable returned by the reduction.
										reduceVar$var28$24 = (i$var25 + j);
									}
									double traceTempVariable$bias$11_1 = reduceVar$var28$24;
									
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
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample40 using sampled
	// values.
	private final void logProbabilityValue$sample40() {
		// Determine if we need to calculate the values for sample task 40 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample40) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var34 = 0; var34 < samples; var34 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flips[var34];
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
			logProbability$var35 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample40 = (fixedFlag$sample40 && fixedFlag$sample12);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var35;
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
		double cv$proposedValue = DistributionSampling.sampleGaussian(RNG$, cv$originalValue, cv$var);
		
		// The probability of the random variable generating the new sample value.
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
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
					
					// Guards to ensure that c is only updated when there is a valid path.
					{
						{
							c[1] = (cv$currentValue / 2);
						}
					}
					
					// Guards to ensure that bias is only updated when there is a valid path.
					{
						// Guard to check that at most one copy of the code is executed for a given set of
						// loop iterations.
						boolean guard$sample12if34 = false;
						if(guard1) {
							double traceTempVariable$bias$3_1 = cv$currentValue;
							if(!guard$sample12if34) {
								// The body will execute, so should not be executed again
								guard$sample12if34 = true;
								{
									// Write out the new sample value.
									bias = traceTempVariable$bias$3_1;
								}
							}
						}
						if(!guard1) {
							if(!guard1) {
								if(((0 <= 0) && (0 < 2))) {
									{
										if(!guard1) {
											// Reduction of array c
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$var28$15 = 0.0;
											
											// For each index in the array to be reduced
											for(int cv$reduction331Index = 0; cv$reduction331Index < 2; cv$reduction331Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double i$var25 = reduceVar$var28$15;
												
												// Set the right hand term to a value from the array c
												double j = c[cv$reduction331Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$var28$15 = (i$var25 + j);
											}
											double traceTempVariable$bias$4_1 = reduceVar$var28$15;
											if(!guard$sample12if34) {
												// The body will execute, so should not be executed again
												guard$sample12if34 = true;
												{
													// Write out the new sample value.
													bias = traceTempVariable$bias$4_1;
												}
											}
										}
									}
								}
							}
						}
						if(!guard1) {
							if(!guard1) {
								if(((0 <= 1) && (1 < 2))) {
									{
										if(!guard1) {
											// Reduction of array c
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$var28$16 = 0.0;
											
											// For each index in the array to be reduced
											for(int cv$reduction344Index = 0; cv$reduction344Index < 2; cv$reduction344Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double i$var25 = reduceVar$var28$16;
												
												// Set the right hand term to a value from the array c
												double j = c[cv$reduction344Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$var28$16 = (i$var25 + j);
											}
											double traceTempVariable$bias$5_1 = reduceVar$var28$16;
											if(!guard$sample12if34) {
												// The body will execute, so should not be executed again
												guard$sample12if34 = true;
												{
													// Write out the new sample value.
													bias = traceTempVariable$bias$5_1;
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
				
				// Processing random variable 30.
				{
					{
						// Guard to check that at most one copy of the code is executed for a given set of
						// loop iterations.
						boolean guard$sample12bernoulli35 = false;
						if(guard1) {
							double traceTempVariable$bias$6_1 = cv$currentValue;
							double traceTempVariable$b$6_2 = cv$currentValue;
							if(!guard$sample12bernoulli35) {
								// The body will execute, so should not be executed again
								guard$sample12bernoulli35 = true;
								
								// Processing sample task 40 of consumer random variable bernoulli.
								{
									for(int var34 = 0; var34 < samples; var34 += 1) {
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
															cv$temp$2$bias = traceTempVariable$bias$6_1;
														}
														
														// Record the probability of sample task 40 generating output with current configuration.
														if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$2$bias)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$2$bias)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															// If the second value is -infinity.
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$2$bias));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$2$bias)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$2$bias)));
														}
														
														// Recorded the probability of reaching sample task 40 with the current configuration.
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
						if(!guard1) {
							double traceTempVariable$b$7_1 = cv$currentValue;
							double traceTempVariable$i$7_2 = (traceTempVariable$b$7_1 / 2);
							if(!guard1) {
								if(((0 <= 0) && (0 < 2))) {
									{
										if(((0 < c.length) && (0 < 2))) {
											// Reduction of array c
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$var28$17 = 0.0;
											
											// Reduce for every value except a masked value which will be skipped.
											for(int cv$reduction377Index = 0; cv$reduction377Index < 0; cv$reduction377Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double i$var25 = reduceVar$var28$17;
												
												// Set the right hand term to a value from the array c
												double j = c[cv$reduction377Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$var28$17 = (i$var25 + j);
											}
											for(int cv$reduction377Index = (0 + 1); cv$reduction377Index < 2; cv$reduction377Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double i$var25 = reduceVar$var28$17;
												
												// Set the right hand term to a value from the array c
												double j = c[cv$reduction377Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$var28$17 = (i$var25 + j);
											}
											double cv$reduced29 = reduceVar$var28$17;
											
											// Copy the result of the reduction into the variable returned by the reduction.
											reduceVar$var28$17 = (traceTempVariable$i$7_2 + cv$reduced29);
											double traceTempVariable$var28$7_3 = reduceVar$var28$17;
											if(!guard1) {
												double traceTempVariable$bias$7_4 = traceTempVariable$var28$7_3;
												if(!guard$sample12bernoulli35) {
													// The body will execute, so should not be executed again
													guard$sample12bernoulli35 = true;
													
													// Processing sample task 40 of consumer random variable bernoulli.
													{
														for(int var34 = 0; var34 < samples; var34 += 1) {
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
																				cv$temp$3$bias = traceTempVariable$bias$7_4;
																			}
																			
																			// Record the probability of sample task 40 generating output with current configuration.
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$3$bias)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$3$bias)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$3$bias));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$3$bias)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$3$bias)));
																			}
																			
																			// Recorded the probability of reaching sample task 40 with the current configuration.
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
						if(!guard1) {
							double traceTempVariable$b$8_1 = cv$currentValue;
							double traceTempVariable$i$8_2 = (traceTempVariable$b$8_1 / 2);
							if(!guard1) {
								if(((0 <= 1) && (1 < 2))) {
									{
										if(((0 < c.length) && (0 < 2))) {
											// Reduction of array c
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$var28$18 = 0.0;
											
											// Reduce for every value except a masked value which will be skipped.
											for(int cv$reduction400Index = 0; cv$reduction400Index < 1; cv$reduction400Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double i$var25 = reduceVar$var28$18;
												
												// Set the right hand term to a value from the array c
												double j = c[cv$reduction400Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$var28$18 = (i$var25 + j);
											}
											for(int cv$reduction400Index = (1 + 1); cv$reduction400Index < 2; cv$reduction400Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double i$var25 = reduceVar$var28$18;
												
												// Set the right hand term to a value from the array c
												double j = c[cv$reduction400Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$var28$18 = (i$var25 + j);
											}
											double cv$reduced29 = reduceVar$var28$18;
											
											// Copy the result of the reduction into the variable returned by the reduction.
											reduceVar$var28$18 = (traceTempVariable$i$8_2 + cv$reduced29);
											double traceTempVariable$var28$8_3 = reduceVar$var28$18;
											if(!guard1) {
												double traceTempVariable$bias$8_4 = traceTempVariable$var28$8_3;
												if(!guard$sample12bernoulli35) {
													// The body will execute, so should not be executed again
													guard$sample12bernoulli35 = true;
													
													// Processing sample task 40 of consumer random variable bernoulli.
													{
														for(int var34 = 0; var34 < samples; var34 += 1) {
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
																			double cv$temp$4$bias;
																			{
																				cv$temp$4$bias = traceTempVariable$bias$8_4;
																			}
																			
																			// Record the probability of sample task 40 generating output with current configuration.
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$4$bias)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$4$bias)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$4$bias));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$4$bias)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$4$bias)));
																			}
																			
																			// Recorded the probability of reaching sample task 40 with the current configuration.
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
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$, 0.0, 1.0))) || Double.isNaN(cv$ratio))) {
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
			
			// Guards to ensure that c is only updated when there is a valid path.
			{
				{
					c[1] = (b / 2);
				}
			}
			
			// Guards to ensure that bias is only updated when there is a valid path.
			{
				// Guard to check that at most one copy of the code is executed for a given set of
				// loop iterations.
				boolean guard$sample12if34 = false;
				if(guard1) {
					double traceTempVariable$bias$17_1 = b;
					if(!guard$sample12if34) {
						// The body will execute, so should not be executed again
						guard$sample12if34 = true;
						{
							// Write out the new sample value.
							bias = traceTempVariable$bias$17_1;
						}
					}
				}
				if(!guard1) {
					if(!guard1) {
						if(((0 <= 0) && (0 < 2))) {
							{
								if(!guard1) {
									// Reduction of array c
									// 
									// A generated name to prevent name collisions if the reduction is implemented more
									// than once in inference and probability code. Initialize the variable to the unit
									// value
									double reduceVar$var28$19 = 0.0;
									
									// For each index in the array to be reduced
									for(int cv$reduction449Index = 0; cv$reduction449Index < 2; cv$reduction449Index += 1) {
										// Set the left hand term of the reduction function to the return variable value.
										double i$var25 = reduceVar$var28$19;
										
										// Set the right hand term to a value from the array c
										double j = c[cv$reduction449Index];
										
										// Execute the reduction function, saving the result into the return value.
										// 
										// Copy the result of the reduction into the variable returned by the reduction.
										reduceVar$var28$19 = (i$var25 + j);
									}
									double traceTempVariable$bias$18_1 = reduceVar$var28$19;
									if(!guard$sample12if34) {
										// The body will execute, so should not be executed again
										guard$sample12if34 = true;
										{
											// Write out the new sample value.
											bias = traceTempVariable$bias$18_1;
										}
									}
								}
							}
						}
					}
				}
				if(!guard1) {
					if(!guard1) {
						if(((0 <= 1) && (1 < 2))) {
							{
								if(!guard1) {
									// Reduction of array c
									// 
									// A generated name to prevent name collisions if the reduction is implemented more
									// than once in inference and probability code. Initialize the variable to the unit
									// value
									double reduceVar$var28$20 = 0.0;
									
									// For each index in the array to be reduced
									for(int cv$reduction462Index = 0; cv$reduction462Index < 2; cv$reduction462Index += 1) {
										// Set the left hand term of the reduction function to the return variable value.
										double i$var25 = reduceVar$var28$20;
										
										// Set the right hand term to a value from the array c
										double j = c[cv$reduction462Index];
										
										// Execute the reduction function, saving the result into the return value.
										// 
										// Copy the result of the reduction into the variable returned by the reduction.
										reduceVar$var28$20 = (i$var25 + j);
									}
									double traceTempVariable$bias$19_1 = reduceVar$var28$20;
									if(!guard$sample12if34) {
										// The body will execute, so should not be executed again
										guard$sample12if34 = true;
										{
											// Write out the new sample value.
											bias = traceTempVariable$bias$19_1;
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
				c = new double[2];
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
				c[1] = (b / 2);
			
			// Reduction of array c
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$var28$25 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction29Index = 0; cv$reduction29Index < 2; cv$reduction29Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double i$var25 = reduceVar$var28$25;
				
				// Set the right hand term to a value from the array c
				double j = c[cv$reduction29Index];
				
				// Execute the reduction function, saving the result into the return value.
				if(!fixedFlag$sample12)
					// Copy the result of the reduction into the variable returned by the reduction.
					reduceVar$var28$25 = (i$var25 + j);
			}
			if(!fixedFlag$sample12)
				bias = reduceVar$var28$25;
		}
		if(!fixedFlag$sample12)
			bias = b;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$var34, int forEnd$var34, int threadID$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var34 = forStart$var34; var34 < forEnd$var34; var34 += 1) {
						if(!fixedFlag$sample40)
							flips[var34] = DistributionSampling.sampleBernoulli(RNG$1, bias);
					}
			}
		);
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
				c[1] = (b / 2);
			
			// Reduction of array c
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$var28$27 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction29Index = 0; cv$reduction29Index < 2; cv$reduction29Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double i$var25 = reduceVar$var28$27;
				
				// Set the right hand term to a value from the array c
				double j = c[cv$reduction29Index];
				
				// Execute the reduction function, saving the result into the return value.
				if(!fixedFlag$sample12)
					// Copy the result of the reduction into the variable returned by the reduction.
					reduceVar$var28$27 = (i$var25 + j);
			}
			if(!fixedFlag$sample12)
				bias = reduceVar$var28$27;
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
				c[1] = (b / 2);
			
			// Reduction of array c
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$var28$26 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction29Index = 0; cv$reduction29Index < 2; cv$reduction29Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double i$var25 = reduceVar$var28$26;
				
				// Set the right hand term to a value from the array c
				double j = c[cv$reduction29Index];
				
				// Execute the reduction function, saving the result into the return value.
				if(!fixedFlag$sample12)
					// Copy the result of the reduction into the variable returned by the reduction.
					reduceVar$var28$26 = (i$var25 + j);
			}
			if(!fixedFlag$sample12)
				bias = reduceVar$var28$26;
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
		logProbability$c = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample12)
			logProbability$b = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample40)
			logProbability$var35 = 0.0;
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
		logProbabilityValue$sample40();
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
		logProbabilityValue$sample40();
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
		logProbabilityValue$sample40();
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
				c[1] = (b / 2);
			
			// Reduction of array c
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$var28$28 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction29Index = 0; cv$reduction29Index < 2; cv$reduction29Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double i$var25 = reduceVar$var28$28;
				
				// Set the right hand term to a value from the array c
				double j = c[cv$reduction29Index];
				
				// Execute the reduction function, saving the result into the return value.
				if(!fixedFlag$sample12)
					// Copy the result of the reduction into the variable returned by the reduction.
					reduceVar$var28$28 = (i$var25 + j);
			}
			if(!fixedFlag$sample12)
				bias = reduceVar$var28$28;
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
		for(int i$var39 = (samples - ((((samples - 1) - 0) % 1) + 1)); i$var39 >= ((0 - 1) + 1); i$var39 -= 1)
			flips[i$var39] = flipsMeasured[i$var39];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		if(!guard1) {
			if(true)
				c[0] = (b / 2);
			if(true)
				c[1] = (b / 2);
			
			// Reduction of array c
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$var28$29 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction29Index = 0; cv$reduction29Index < 2; cv$reduction29Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double i$var25 = reduceVar$var28$29;
				
				// Set the right hand term to a value from the array c
				double j = c[cv$reduction29Index];
				
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$var28$29 = (i$var25 + j);
			}
			bias = reduceVar$var28$29;
		}
		if(true)
			bias = b;
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK15(boolean[] flipsMeasured, boolean guard1) {\n    int samples = flipsMeasured.length;\n        \n    double b = beta(1.0, 1).sample();\n    double bias;\n    if(guard1)\n      bias = b;\n    else {\n      double[] c = new double[2];\n      c[0] = b/2;\n      c[1] = b/2;\n      bias = reduce(c, 0, (i,j) -> {\n            return i + j;\n        });\n    }\n        \n    Bernoulli bernoulli = bernoulli(bias);\n    boolean[] flips = bernoulli.sample(samples);\n\n    for(int i:[0..samples))\n        flips[i].observe(flipsMeasured[i]);\n}\n";
	}
}