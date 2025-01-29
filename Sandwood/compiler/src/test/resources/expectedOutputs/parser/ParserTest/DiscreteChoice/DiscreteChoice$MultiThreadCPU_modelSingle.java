package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DiscreteChoice$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DiscreteChoice$CoreInterface {
	
	// Declare the variables for the model.
	private int[] ObsChoices;
	private int[] choices;
	private double[] exped;
	private boolean fixedFlag$sample19 = false;
	private boolean fixedFlag$sample49 = false;
	private boolean fixedProbFlag$sample19 = false;
	private boolean fixedProbFlag$sample49 = false;
	private boolean[] guard$sample19put43$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$choices;
	private double logProbability$exped;
	private double logProbability$prob;
	private double[] logProbability$sample19;
	private double logProbability$sum;
	private double logProbability$ut;
	private double logProbability$var17;
	private double logProbability$var42;
	private double logProbability$var47;
	private int noObs;
	private int noProducts;
	private double[] prob;
	private boolean setFlag$choices = false;
	private boolean setFlag$ut = false;
	private double sum;
	private boolean system$gibbsForward = true;
	private double[] ut;

	public DiscreteChoice$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for ObsChoices.
	@Override
	public final int[] get$ObsChoices() {
		return ObsChoices;
	}

	// Setter for ObsChoices.
	@Override
	public final void set$ObsChoices(int[] cv$value) {
		// Set ObsChoices with flag to mark that it has been set so another array doesn't
		// need to be constructed
		ObsChoices = cv$value;
	}

	// Getter for choices.
	@Override
	public final int[] get$choices() {
		return choices;
	}

	// Setter for choices.
	@Override
	public final void set$choices(int[] cv$value) {
		// Set flags for all the side effects of choices including if probabilities need to
		// be updated.
		// Set choices with flag to mark that it has been set so another array doesn't need
		// to be constructed
		choices = cv$value;
		setFlag$choices = true;
		
		// Unset the fixed probability flag for sample 49 as it depends on choices.
		fixedProbFlag$sample49 = false;
	}

	// Getter for exped.
	@Override
	public final double[] get$exped() {
		return exped;
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
		
		// Should the probability of sample 49 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample49 = (fixedFlag$sample19 && fixedProbFlag$sample49);
	}

	// Getter for fixedFlag$sample49.
	@Override
	public final boolean get$fixedFlag$sample49() {
		return fixedFlag$sample49;
	}

	// Setter for fixedFlag$sample49.
	@Override
	public final void set$fixedFlag$sample49(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample49 including if probabilities
		// need to be updated.
		fixedFlag$sample49 = cv$value;
		
		// Should the probability of sample 49 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample49 = (fixedFlag$sample49 && fixedProbFlag$sample49);
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

	// Getter for logProbability$choices.
	@Override
	public final double get$logProbability$choices() {
		return logProbability$choices;
	}

	// Getter for logProbability$exped.
	@Override
	public final double get$logProbability$exped() {
		return logProbability$exped;
	}

	// Getter for logProbability$prob.
	@Override
	public final double get$logProbability$prob() {
		return logProbability$prob;
	}

	// Getter for logProbability$sum.
	@Override
	public final double get$logProbability$sum() {
		return logProbability$sum;
	}

	// Getter for logProbability$ut.
	@Override
	public final double get$logProbability$ut() {
		return logProbability$ut;
	}

	// Getter for noObs.
	@Override
	public final int get$noObs() {
		return noObs;
	}

	// Setter for noObs.
	@Override
	public final void set$noObs(int cv$value) {
		noObs = cv$value;
	}

	// Getter for noProducts.
	@Override
	public final int get$noProducts() {
		return noProducts;
	}

	// Setter for noProducts.
	@Override
	public final void set$noProducts(int cv$value) {
		noProducts = cv$value;
	}

	// Getter for prob.
	@Override
	public final double[] get$prob() {
		return prob;
	}

	// Getter for sum.
	@Override
	public final double get$sum() {
		return sum;
	}

	// Getter for ut.
	@Override
	public final double[] get$ut() {
		return ut;
	}

	// Setter for ut.
	@Override
	public final void set$ut(double[] cv$value) {
		// Set flags for all the side effects of ut including if probabilities need to be
		// updated.
		// Set ut with flag to mark that it has been set so another array doesn't need to
		// be constructed
		ut = cv$value;
		setFlag$ut = true;
		
		// Unset the fixed probability flag for sample 19 as it depends on ut.
		fixedProbFlag$sample19 = false;
		
		// Unset the fixed probability flag for sample 49 as it depends on ut.
		fixedProbFlag$sample49 = false;
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
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = ut[i$var12];
					{
						{
							double var15 = 0.0;
							double var16 = 10.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var15) / Math.sqrt(var16))) - (0.5 * Math.log(var16))));
							
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
				
				// Store the sample task probability
				logProbability$sample19[((i$var12 - 1) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that exped is only updated once for this probability.
				boolean cv$guard$exped = false;
				
				// Guard to ensure that sum is only updated once for this probability.
				boolean cv$guard$sum = false;
				
				// Guard to ensure that prob is only updated once for this probability.
				boolean cv$guard$prob = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 19 and consumer double[] 26.
				{
					for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
						if((i$var12 == i$var23)) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$exped) {
								// Set the guard so the update is only applied once.
								cv$guard$exped = true;
								
								// Update the variable probability
								logProbability$exped = (logProbability$exped + cv$sampleProbability);
							}
						}
					}
				}
				
				// Looking for a path between Sample 19 and consumer double 34.
				{
					for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
						if((i$var12 == i$var23)) {
							if(((0 <= i$var23) && (i$var23 < noProducts))) {
								{
									// If the probability of the variable has not already been updated
									if(!cv$guard$sum) {
										// Set the guard so the update is only applied once.
										cv$guard$sum = true;
										
										// Update the variable probability
										logProbability$sum = (logProbability$sum + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 19 and consumer double[] 41.
				{
					for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
						if((i$var12 == i$var23)) {
							if(((0 <= i$var23) && (i$var23 < noProducts))) {
								{
									for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$prob) {
											// Set the guard so the update is only applied once.
											cv$guard$prob = true;
											
											// Update the variable probability
											logProbability$prob = (logProbability$prob + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
					for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
						if((i$var12 == i$var23)) {
							for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
								if((i$var23 == i$var38)) {
									// If the probability of the variable has not already been updated
									if(!cv$guard$prob) {
										// Set the guard so the update is only applied once.
										cv$guard$prob = true;
										
										// Update the variable probability
										logProbability$prob = (logProbability$prob + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var17 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$accumulator);
			
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
			double cv$rvAccumulator = 0.0;
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1) {
				double cv$sampleValue = logProbability$sample19[((i$var12 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Guard to ensure that exped is only updated once for this probability.
				boolean cv$guard$exped = false;
				
				// Guard to ensure that sum is only updated once for this probability.
				boolean cv$guard$sum = false;
				
				// Guard to ensure that prob is only updated once for this probability.
				boolean cv$guard$prob = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 19 and consumer double[] 26.
				{
					for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
						if((i$var12 == i$var23)) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$exped) {
								// Set the guard so the update is only applied once.
								cv$guard$exped = true;
								
								// Update the variable probability
								logProbability$exped = (logProbability$exped + cv$sampleValue);
							}
						}
					}
				}
				
				// Looking for a path between Sample 19 and consumer double 34.
				{
					for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
						if((i$var12 == i$var23)) {
							if(((0 <= i$var23) && (i$var23 < noProducts))) {
								{
									// If the probability of the variable has not already been updated
									if(!cv$guard$sum) {
										// Set the guard so the update is only applied once.
										cv$guard$sum = true;
										
										// Update the variable probability
										logProbability$sum = (logProbability$sum + cv$sampleValue);
									}
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 19 and consumer double[] 41.
				{
					for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
						if((i$var12 == i$var23)) {
							if(((0 <= i$var23) && (i$var23 < noProducts))) {
								{
									for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$prob) {
											// Set the guard so the update is only applied once.
											cv$guard$prob = true;
											
											// Update the variable probability
											logProbability$prob = (logProbability$prob + cv$sampleValue);
										}
									}
								}
							}
						}
					}
					for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
						if((i$var12 == i$var23)) {
							for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
								if((i$var23 == i$var38)) {
									// If the probability of the variable has not already been updated
									if(!cv$guard$prob) {
										// Set the guard so the update is only applied once.
										cv$guard$prob = true;
										
										// Update the variable probability
										logProbability$prob = (logProbability$prob + cv$sampleValue);
									}
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var17 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample19)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample49 using sampled
	// values.
	private final void logProbabilityValue$sample49() {
		// Determine if we need to calculate the values for sample task 49 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample49) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var46 = 0; var46 < noObs; var46 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = choices[var46];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < prob.length))?Math.log(prob[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			logProbability$var42 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var47 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$choices = (logProbability$choices + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample49 = (fixedFlag$sample49 && fixedFlag$sample19);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var47;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var42 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$choices = (logProbability$choices + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 19 drawn from Gaussian 17. Inference was performed using Metropolis-Hastings.
	private final void sample19(int i$var12) {
		// The original value of the sample
		double cv$originalValue = ut[i$var12];
		
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
					// Write out the value of the sample to a temporary variable prior to updating the
					// intermediate variables.
					double var18 = cv$proposedValue;
					ut[i$var12] = cv$currentValue;
					
					// Guards to ensure that exped is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 19 and consumer double[] 26.
					{
						for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
							if((i$var12 == i$var23)) {
								{
									exped[i$var23] = Math.exp(ut[i$var23]);
								}
							}
						}
					}
					
					// Guards to ensure that sum is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 19 and consumer double 34.
					{
						for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
							if((i$var12 == i$var23)) {
								if(((0 <= i$var23) && (i$var23 < noProducts))) {
									{
										{
											// Reduction of array exped
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$sum$8 = 0.0;
											
											// For each index in the array to be reduced
											for(int cv$reduction366Index = 0; cv$reduction366Index < noProducts; cv$reduction366Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double i$var31 = reduceVar$sum$8;
												
												// Set the right hand term to a value from the array exped
												double j = exped[cv$reduction366Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$8 = (i$var31 + j);
											}
											
											// Write out the new sample value.
											sum = reduceVar$sum$8;
										}
									}
								}
							}
						}
					}
					
					// Guards to ensure that prob is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 19 and consumer double[] 41.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[] guard$sample19put43 = guard$sample19put43$global;
						for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
							if((i$var12 == i$var23)) {
								if(((0 <= i$var23) && (i$var23 < noProducts))) {
									{
										for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
											// Set the flags to false
											guard$sample19put43[((i$var38 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
							if((i$var12 == i$var23)) {
								for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
									if((i$var23 == i$var38))
										// Set the flags to false
										guard$sample19put43[((i$var38 - 0) / 1)] = false;
								}
							}
						}
						for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
							if((i$var12 == i$var23)) {
								if(((0 <= i$var23) && (i$var23 < noProducts))) {
									{
										for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
											if(!guard$sample19put43[((i$var38 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample19put43[((i$var38 - 0) / 1)] = true;
												{
													prob[i$var38] = (exped[i$var38] / sum);
												}
											}
										}
									}
								}
							}
						}
						for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
							if((i$var12 == i$var23)) {
								for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
									if((i$var23 == i$var38)) {
										if(!guard$sample19put43[((i$var38 - 0) / 1)]) {
											// The body will execute, so should not be executed again
											guard$sample19put43[((i$var38 - 0) / 1)] = true;
											{
												prob[i$var38] = (exped[i$var38] / sum);
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
				double cv$temp$0$var15;
				{
					cv$temp$0$var15 = 0.0;
				}
				double cv$temp$1$var16;
				{
					cv$temp$1$var16 = 10.0;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var15) / Math.sqrt(cv$temp$1$var16))) - (0.5 * Math.log(cv$temp$1$var16))));
				
				// Processing random variable 42.
				{
					// Looking for a path between Sample 19 and consumer Categorical 42.
					{
						// Guard to check that at most one copy of the code is executed for a given set of
						// loop iterations.
						boolean guard$sample19categorical44 = false;
						double traceTempVariable$var24$7_1 = cv$currentValue;
						for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
							if((i$var12 == i$var23)) {
								double traceTempVariable$i$7_3 = Math.exp(traceTempVariable$var24$7_1);
								if(((0 <= i$var23) && (i$var23 < noProducts))) {
									{
										if((0 < noProducts)) {
											// Reduction of array exped
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$sum$9 = 0.0;
											
											// Reduce for every value except a masked value which will be skipped.
											for(int cv$reduction431Index = 0; cv$reduction431Index < i$var23; cv$reduction431Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double i$var31 = reduceVar$sum$9;
												
												// Set the right hand term to a value from the array exped
												double j = exped[cv$reduction431Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$9 = (i$var31 + j);
											}
											for(int cv$reduction431Index = (i$var23 + 1); cv$reduction431Index < noProducts; cv$reduction431Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double i$var31 = reduceVar$sum$9;
												
												// Set the right hand term to a value from the array exped
												double j = exped[cv$reduction431Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$9 = (i$var31 + j);
											}
											double cv$reduced32 = reduceVar$sum$9;
											
											// Copy the result of the reduction into the variable returned by the reduction.
											reduceVar$sum$9 = (traceTempVariable$i$7_3 + cv$reduced32);
											double traceTempVariable$sum$7_4 = reduceVar$sum$9;
											if(!guard$sample19categorical44) {
												// The body will execute, so should not be executed again
												guard$sample19categorical44 = true;
												
												// Processing sample task 49 of consumer random variable null.
												{
													for(int var46 = 0; var46 < noObs; var46 += 1) {
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
																		double[] cv$temp$2$prob;
																		{
																			cv$temp$2$prob = prob;
																		}
																		
																		// Record the probability of sample task 49 generating output with current configuration.
																		if(((Math.log(1.0) + (((0.0 <= choices[var46]) && (choices[var46] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[var46]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[var46]) && (choices[var46] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[var46]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[var46]) && (choices[var46] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[var46]]):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[var46]) && (choices[var46] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[var46]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[var46]) && (choices[var46] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[var46]]):Double.NEGATIVE_INFINITY)));
																		}
																		
																		// Recorded the probability of reaching sample task 49 with the current configuration.
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
						double traceTempVariable$var24$8_1 = cv$currentValue;
						for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
							if((i$var12 == i$var23)) {
								double traceTempVariable$var39$8_3 = Math.exp(traceTempVariable$var24$8_1);
								for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
									if((i$var23 == i$var38)) {
										if(!guard$sample19categorical44) {
											// The body will execute, so should not be executed again
											guard$sample19categorical44 = true;
											
											// Processing sample task 49 of consumer random variable null.
											{
												for(int var46 = 0; var46 < noObs; var46 += 1) {
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
																	double[] cv$temp$3$prob;
																	{
																		cv$temp$3$prob = prob;
																	}
																	
																	// Record the probability of sample task 49 generating output with current configuration.
																	if(((Math.log(1.0) + (((0.0 <= choices[var46]) && (choices[var46] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[var46]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[var46]) && (choices[var46] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[var46]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[var46]) && (choices[var46] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[var46]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[var46]) && (choices[var46] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[var46]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[var46]) && (choices[var46] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[var46]]):Double.NEGATIVE_INFINITY)));
																	}
																	
																	// Recorded the probability of reaching sample task 49 with the current configuration.
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
			double var18 = cv$originalValue;
			ut[i$var12] = var18;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 19 and consumer double[] 26.
			{
				for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
					if((i$var12 == i$var23)) {
						{
							exped[i$var23] = Math.exp(ut[i$var23]);
						}
					}
				}
			}
			
			// Guards to ensure that sum is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 19 and consumer double 34.
			{
				for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
					if((i$var12 == i$var23)) {
						if(((0 <= i$var23) && (i$var23 < noProducts))) {
							{
								{
									// Reduction of array exped
									// 
									// A generated name to prevent name collisions if the reduction is implemented more
									// than once in inference and probability code. Initialize the variable to the unit
									// value
									double reduceVar$sum$10 = 0.0;
									
									// For each index in the array to be reduced
									for(int cv$reduction474Index = 0; cv$reduction474Index < noProducts; cv$reduction474Index += 1) {
										// Set the left hand term of the reduction function to the return variable value.
										double i$var31 = reduceVar$sum$10;
										
										// Set the right hand term to a value from the array exped
										double j = exped[cv$reduction474Index];
										
										// Execute the reduction function, saving the result into the return value.
										// 
										// Copy the result of the reduction into the variable returned by the reduction.
										reduceVar$sum$10 = (i$var31 + j);
									}
									
									// Write out the new sample value.
									sum = reduceVar$sum$10;
								}
							}
						}
					}
				}
			}
			
			// Guards to ensure that prob is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 19 and consumer double[] 41.
			{
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				boolean[] guard$sample19put43 = guard$sample19put43$global;
				for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
					if((i$var12 == i$var23)) {
						if(((0 <= i$var23) && (i$var23 < noProducts))) {
							{
								for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
									// Set the flags to false
									guard$sample19put43[((i$var38 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
					if((i$var12 == i$var23)) {
						for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
							if((i$var23 == i$var38))
								// Set the flags to false
								guard$sample19put43[((i$var38 - 0) / 1)] = false;
						}
					}
				}
				for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
					if((i$var12 == i$var23)) {
						if(((0 <= i$var23) && (i$var23 < noProducts))) {
							{
								for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
									if(!guard$sample19put43[((i$var38 - 0) / 1)]) {
										// The body will execute, so should not be executed again
										guard$sample19put43[((i$var38 - 0) / 1)] = true;
										{
											prob[i$var38] = (exped[i$var38] / sum);
										}
									}
								}
							}
						}
					}
				}
				for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
					if((i$var12 == i$var23)) {
						for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
							if((i$var23 == i$var38)) {
								if(!guard$sample19put43[((i$var38 - 0) / 1)]) {
									// The body will execute, so should not be executed again
									guard$sample19put43[((i$var38 - 0) / 1)] = true;
									{
										prob[i$var38] = (exped[i$var38] / sum);
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
	public final void allocateScratch() {
		// Calculate the largest index of i that is possible and allocate an array to hold
		// the guard for each of these.
		int cv$max_i$var38 = 0;
		cv$max_i$var38 = Math.max(cv$max_i$var38, ((noProducts - 0) / 1));
		
		// Allocation of guard$sample19put43$global for single threaded execution
		guard$sample19put43$global = new boolean[cv$max_i$var38];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If ut has not been set already allocate space.
		if(!setFlag$ut) {
			// Constructor for ut
			{
				ut = new double[noProducts];
			}
		}
		
		// Constructor for exped
		{
			exped = new double[noProducts];
		}
		
		// Constructor for prob
		{
			prob = new double[noProducts];
		}
		
		// If choices has not been set already allocate space.
		if(!setFlag$choices) {
			// Constructor for choices
			{
				choices = new int[noObs];
			}
		}
		
		// Constructor for logProbability$sample19
		{
			logProbability$sample19 = new double[((((noProducts - 1) - 1) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 1, noProducts, 1,
			(int forStart$i$var12, int forEnd$i$var12, int threadID$i$var12, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var12 = forStart$i$var12; i$var12 < forEnd$i$var12; i$var12 += 1) {
						if(!fixedFlag$sample19)
							ut[i$var12] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var23, int forEnd$i$var23, int threadID$i$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var23 = forStart$i$var23; i$var23 < forEnd$i$var23; i$var23 += 1) {
						if(!fixedFlag$sample19)
							exped[i$var23] = Math.exp(ut[i$var23]);
					}
			}
		);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$11 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction32Index = 0; cv$reduction32Index < noProducts; cv$reduction32Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double i$var31 = reduceVar$sum$11;
			
			// Set the right hand term to a value from the array exped
			double j = exped[cv$reduction32Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!fixedFlag$sample19)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$11 = (i$var31 + j);
		}
		if(!fixedFlag$sample19)
			sum = reduceVar$sum$11;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var38, int forEnd$i$var38, int threadID$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var38 = forStart$i$var38; i$var38 < forEnd$i$var38; i$var38 += 1) {
						if(!fixedFlag$sample19)
							prob[i$var38] = (exped[i$var38] / sum);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!fixedFlag$sample49)
							choices[var46] = DistributionSampling.sampleCategorical(RNG$1, prob);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 1, noProducts, 1,
			(int forStart$i$var12, int forEnd$i$var12, int threadID$i$var12, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var12 = forStart$i$var12; i$var12 < forEnd$i$var12; i$var12 += 1) {
						if(!fixedFlag$sample19)
							ut[i$var12] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var23, int forEnd$i$var23, int threadID$i$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var23 = forStart$i$var23; i$var23 < forEnd$i$var23; i$var23 += 1) {
						if(!fixedFlag$sample19)
							exped[i$var23] = Math.exp(ut[i$var23]);
					}
			}
		);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$13 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction32Index = 0; cv$reduction32Index < noProducts; cv$reduction32Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double i$var31 = reduceVar$sum$13;
			
			// Set the right hand term to a value from the array exped
			double j = exped[cv$reduction32Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!fixedFlag$sample19)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$13 = (i$var31 + j);
		}
		if(!fixedFlag$sample19)
			sum = reduceVar$sum$13;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var38, int forEnd$i$var38, int threadID$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var38 = forStart$i$var38; i$var38 < forEnd$i$var38; i$var38 += 1) {
						if(!fixedFlag$sample19)
							prob[i$var38] = (exped[i$var38] / sum);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 1, noProducts, 1,
			(int forStart$i$var12, int forEnd$i$var12, int threadID$i$var12, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var12 = forStart$i$var12; i$var12 < forEnd$i$var12; i$var12 += 1) {
						if(!fixedFlag$sample19)
							ut[i$var12] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var23, int forEnd$i$var23, int threadID$i$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var23 = forStart$i$var23; i$var23 < forEnd$i$var23; i$var23 += 1) {
						if(!fixedFlag$sample19)
							exped[i$var23] = Math.exp(ut[i$var23]);
					}
			}
		);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$12 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction32Index = 0; cv$reduction32Index < noProducts; cv$reduction32Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double i$var31 = reduceVar$sum$12;
			
			// Set the right hand term to a value from the array exped
			double j = exped[cv$reduction32Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!fixedFlag$sample19)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$12 = (i$var31 + j);
		}
		if(!fixedFlag$sample19)
			sum = reduceVar$sum$12;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var38, int forEnd$i$var38, int threadID$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var38 = forStart$i$var38; i$var38 < forEnd$i$var38; i$var38 += 1) {
						if(!fixedFlag$sample19)
							prob[i$var38] = (exped[i$var38] / sum);
					}
			}
		);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1) {
				if(!fixedFlag$sample19)
					sample19(i$var12);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i$var12 = (noProducts - ((((noProducts - 1) - 1) % 1) + 1)); i$var12 >= ((1 - 1) + 1); i$var12 -= 1) {
				if(!fixedFlag$sample19)
					sample19(i$var12);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		ut[0] = 0.0;
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
		logProbability$var17 = 0.0;
		logProbability$sum = 0.0;
		logProbability$ut = 0.0;
		logProbability$prob = 0.0;
		logProbability$exped = 0.0;
		if(!fixedProbFlag$sample19) {
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1)
				logProbability$sample19[((i$var12 - 1) / 1)] = 0.0;
		}
		logProbability$var42 = 0.0;
		logProbability$choices = 0.0;
		if(!fixedProbFlag$sample49)
			logProbability$var47 = 0.0;
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
		logProbabilityValue$sample49();
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
		logProbabilityValue$sample49();
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
		logProbabilityValue$sample49();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 1, noProducts, 1,
			(int forStart$i$var12, int forEnd$i$var12, int threadID$i$var12, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var12 = forStart$i$var12; i$var12 < forEnd$i$var12; i$var12 += 1) {
						if(!fixedFlag$sample19)
							ut[i$var12] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var23, int forEnd$i$var23, int threadID$i$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var23 = forStart$i$var23; i$var23 < forEnd$i$var23; i$var23 += 1) {
						if(!fixedFlag$sample19)
							exped[i$var23] = Math.exp(ut[i$var23]);
					}
			}
		);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$14 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction32Index = 0; cv$reduction32Index < noProducts; cv$reduction32Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double i$var31 = reduceVar$sum$14;
			
			// Set the right hand term to a value from the array exped
			double j = exped[cv$reduction32Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!fixedFlag$sample19)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$14 = (i$var31 + j);
		}
		if(!fixedFlag$sample19)
			sum = reduceVar$sum$14;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var38, int forEnd$i$var38, int threadID$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var38 = forStart$i$var38; i$var38 < forEnd$i$var38; i$var38 += 1) {
						if(!fixedFlag$sample19)
							prob[i$var38] = (exped[i$var38] / sum);
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
		int[] cv$source1 = ObsChoices;
		int[] cv$target1 = choices;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var23, int forEnd$i$var23, int threadID$i$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var23 = forStart$i$var23; i$var23 < forEnd$i$var23; i$var23 += 1) {
						if(setFlag$ut)
							exped[i$var23] = Math.exp(ut[i$var23]);
					}
			}
		);
		if(setFlag$ut) {
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$15 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction32Index = 0; cv$reduction32Index < noProducts; cv$reduction32Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double i$var31 = reduceVar$sum$15;
				
				// Set the right hand term to a value from the array exped
				double j = exped[cv$reduction32Index];
				
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$15 = (i$var31 + j);
			}
			sum = reduceVar$sum$15;
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var38, int forEnd$i$var38, int threadID$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var38 = forStart$i$var38; i$var38 < forEnd$i$var38; i$var38 += 1) {
						if(setFlag$ut)
							prob[i$var38] = (exped[i$var38] / sum);
					}
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model DiscreteChoice(int noProducts, int noObs, int[] ObsChoices) {\n    // we just need an uninformative prior for utility intercepts\n\n    // draw utilities\n    double[] ut = new double[noProducts];\n    ut[0] = 0.0;\n    for(int i=1; i<noProducts; i++) {\n        ut[i]= gaussian(0, 10).sample();\n    }\n\n    // calculate choice probabilities\n    double[] exped = new double[noProducts];\n    for(int i : [0..noProducts)) {\n        exped[i] = exp(ut[i]);\n    }\n    double sum = reduce(exped, 0, (i, j) -> { return i + j; });\n    double[] prob = new double[noProducts];\n    for (int i : [0..noProducts)) {\n        prob[i] = exped[i] / sum;\n    }\n    // draw consumer choices according to the calculated probabilities\n    int[] choices = categorical(prob).sample(noObs);\n\n    // assert generated choices match observed choices\n    choices.observe(ObsChoices);\n}";
	}
}