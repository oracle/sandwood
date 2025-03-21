package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DiscreteChoiceAlt$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements DiscreteChoiceAlt$CoreInterface {
	
	// Declare the variables for the model.
	private int[] ObsChoices;
	private int[] choices;
	private double[] exped;
	private boolean fixedFlag$sample24 = false;
	private boolean fixedProbFlag$sample24 = false;
	private boolean fixedProbFlag$sample78 = false;
	private boolean[] guard$sample24put65$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$choices;
	private double logProbability$exped;
	private double logProbability$prob;
	private double[] logProbability$sample24;
	private double logProbability$sum;
	private double logProbability$ut;
	private double logProbability$var23;
	private double logProbability$var65;
	private double logProbability$var77;
	private int noObs;
	private int noProducts;
	private double[] prob;
	private boolean setFlag$choices = false;
	private boolean setFlag$ut = false;
	private double sum;
	private boolean system$gibbsForward = true;
	private double[] ut;

	public DiscreteChoiceAlt$SingleThreadCPU(ExecutionTarget target) {
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

	// Getter for exped.
	@Override
	public final double[] get$exped() {
		return exped;
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
		
		// Should the probability of sample 78 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample78 = (fixedFlag$sample24 && fixedProbFlag$sample78);
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
		
		// Unset the fixed probability flag for sample 24 as it depends on ut.
		fixedProbFlag$sample24 = false;
		
		// Unset the fixed probability flag for sample 78 as it depends on ut.
		fixedProbFlag$sample78 = false;
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
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = ut[i$var18];
					{
						{
							double var21 = 0.0;
							double var22 = 10.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var21) / Math.sqrt(var22))) - (0.5 * Math.log(var22))));
							
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
				logProbability$sample24[((i$var18 - 1) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that exped is only updated once for this probability.
				boolean cv$guard$exped = false;
				
				// Guard to ensure that sum is only updated once for this probability.
				boolean cv$guard$sum = false;
				
				// Guard to ensure that prob is only updated once for this probability.
				boolean cv$guard$prob = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 24 and consumer double[] 39.
				{
					for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
						if((i$var18 == i$var36)) {
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
				
				// Looking for a path between Sample 24 and consumer double 50.
				{
					for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
						if((i$var18 == i$var36)) {
							if(((0 <= i$var36) && (i$var36 < noProducts))) {
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
				
				// Looking for a path between Sample 24 and consumer double[] 64.
				{
					for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
						if((i$var18 == i$var36)) {
							if(((0 <= i$var36) && (i$var36 < noProducts))) {
								{
									for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
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
					for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
						if((i$var18 == i$var36)) {
							for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
								if((i$var36 == i$var61)) {
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
			logProbability$var23 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample24)
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
			double cv$rvAccumulator = 0.0;
			for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1) {
				double cv$sampleValue = logProbability$sample24[((i$var18 - 1) / 1)];
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
				// Looking for a path between Sample 24 and consumer double[] 39.
				{
					for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
						if((i$var18 == i$var36)) {
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
				
				// Looking for a path between Sample 24 and consumer double 50.
				{
					for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
						if((i$var18 == i$var36)) {
							if(((0 <= i$var36) && (i$var36 < noProducts))) {
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
				
				// Looking for a path between Sample 24 and consumer double[] 64.
				{
					for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
						if((i$var18 == i$var36)) {
							if(((0 <= i$var36) && (i$var36 < noProducts))) {
								{
									for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
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
					for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
						if((i$var18 == i$var36)) {
							for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
								if((i$var36 == i$var61)) {
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
			logProbability$var23 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$ut = (logProbability$ut + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample78 using sampled
	// values.
	private final void logProbabilityValue$sample78() {
		// Determine if we need to calculate the values for sample task 78 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample78) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var76 = 0; var76 < noObs; var76 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = choices[var76];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noProducts))?Math.log(prob[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			logProbability$var65 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var77 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$choices = (logProbability$choices + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample78 = fixedFlag$sample24;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var77;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var65 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$choices = (logProbability$choices + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 24 drawn from Gaussian 23. Inference was performed using Metropolis-Hastings.
	private final void sample24(int i$var18) {
		// Calculate the number of states to evaluate.
		int cv$numNumStates = 0;
		{
			// Metropolis-Hastings
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		
		// The original value of the sample
		double cv$originalValue = ut[i$var18];
		
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
					double var24 = cv$proposedValue;
					ut[i$var18] = cv$currentValue;
					
					// Guards to ensure that exped is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 24 and consumer double[] 39.
					{
						for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								{
									exped[i$var36] = Math.exp(ut[i$var36]);
								}
							}
						}
					}
					
					// Guards to ensure that sum is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 24 and consumer double 50.
					{
						for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								if(((0 <= i$var36) && (i$var36 < noProducts))) {
									{
										{
											// Reduction of array exped
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$sum$0 = 0.0;
											
											// For each index in the array to be reduced
											for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double i$var47 = reduceVar$sum$0;
												
												// Set the right hand term to a value from the array exped
												double j = exped[cv$reduction44Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$0 = (i$var47 + j);
											}
											
											// Write out the new sample value.
											sum = reduceVar$sum$0;
										}
									}
								}
							}
						}
					}
					
					// Guards to ensure that prob is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 24 and consumer double[] 64.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[] guard$sample24put65 = guard$sample24put65$global;
						for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								if(((0 <= i$var36) && (i$var36 < noProducts))) {
									{
										for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1)
											// Set the flags to false
											guard$sample24put65[((i$var61 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
									if((i$var36 == i$var61))
										// Set the flags to false
										guard$sample24put65[((i$var61 - 0) / 1)] = false;
								}
							}
						}
						for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								if(((0 <= i$var36) && (i$var36 < noProducts))) {
									{
										for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
											if(!guard$sample24put65[((i$var61 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample24put65[((i$var61 - 0) / 1)] = true;
												{
													prob[i$var61] = (exped[i$var61] / sum);
												}
											}
										}
									}
								}
							}
						}
						for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
									if((i$var36 == i$var61)) {
										if(!guard$sample24put65[((i$var61 - 0) / 1)]) {
											// The body will execute, so should not be executed again
											guard$sample24put65[((i$var61 - 0) / 1)] = true;
											{
												prob[i$var61] = (exped[i$var61] / sum);
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
				double cv$temp$0$var21;
				{
					cv$temp$0$var21 = 0.0;
				}
				double cv$temp$1$var22;
				{
					cv$temp$1$var22 = 10.0;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var21) / Math.sqrt(cv$temp$1$var22))) - (0.5 * Math.log(cv$temp$1$var22))));
				
				// Processing random variable 65.
				{
					// Looking for a path between Sample 24 and consumer Categorical 65.
					{
						// Guard to check that at most one copy of the code is executed for a given set of
						// loop iterations.
						boolean guard$sample24categorical66 = false;
						double traceTempVariable$var37$7_1 = cv$currentValue;
						for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								double traceTempVariable$i$7_3 = Math.exp(traceTempVariable$var37$7_1);
								if(((0 <= i$var36) && (i$var36 < noProducts))) {
									{
										if((0 < noProducts)) {
											// Reduction of array exped
											// 
											// A generated name to prevent name collisions if the reduction is implemented more
											// than once in inference and probability code. Initialize the variable to the unit
											// value
											double reduceVar$sum$1 = 0.0;
											
											// Reduce for every value except a masked value which will be skipped.
											for(int cv$reduction265Index = 0; cv$reduction265Index < i$var36; cv$reduction265Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double i$var47 = reduceVar$sum$1;
												
												// Set the right hand term to a value from the array exped
												double j = exped[cv$reduction265Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$1 = (i$var47 + j);
											}
											for(int cv$reduction265Index = (i$var36 + 1); cv$reduction265Index < noProducts; cv$reduction265Index += 1) {
												// Set the left hand term of the reduction function to the return variable value.
												double i$var47 = reduceVar$sum$1;
												
												// Set the right hand term to a value from the array exped
												double j = exped[cv$reduction265Index];
												
												// Execute the reduction function, saving the result into the return value.
												// 
												// Execute the reduction function, saving the result into the return value.
												// 
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$sum$1 = (i$var47 + j);
											}
											double cv$reduced44 = reduceVar$sum$1;
											
											// Copy the result of the reduction into the variable returned by the reduction.
											reduceVar$sum$1 = (traceTempVariable$i$7_3 + cv$reduced44);
											double traceTempVariable$sum$7_4 = reduceVar$sum$1;
											if(!guard$sample24categorical66) {
												// The body will execute, so should not be executed again
												guard$sample24categorical66 = true;
												
												// Processing sample task 78 of consumer random variable null.
												{
													for(int var76 = 0; var76 < noObs; var76 += 1) {
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
																		int cv$temp$3$$var225;
																		{
																			// Constructing a random variable input for use later.
																			int $var225 = noProducts;
																			cv$temp$3$$var225 = $var225;
																		}
																		
																		// Record the probability of sample task 78 generating output with current configuration.
																		if(((Math.log(1.0) + (((0.0 <= choices[var76]) && (choices[var76] < cv$temp$3$$var225))?Math.log(cv$temp$2$prob[choices[var76]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[var76]) && (choices[var76] < cv$temp$3$$var225))?Math.log(cv$temp$2$prob[choices[var76]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[var76]) && (choices[var76] < cv$temp$3$$var225))?Math.log(cv$temp$2$prob[choices[var76]]):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[var76]) && (choices[var76] < cv$temp$3$$var225))?Math.log(cv$temp$2$prob[choices[var76]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[var76]) && (choices[var76] < cv$temp$3$$var225))?Math.log(cv$temp$2$prob[choices[var76]]):Double.NEGATIVE_INFINITY)));
																		}
																		
																		// Recorded the probability of reaching sample task 78 with the current configuration.
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
						double traceTempVariable$var37$8_1 = cv$currentValue;
						for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								double traceTempVariable$var62$8_3 = Math.exp(traceTempVariable$var37$8_1);
								for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
									if((i$var36 == i$var61)) {
										if(!guard$sample24categorical66) {
											// The body will execute, so should not be executed again
											guard$sample24categorical66 = true;
											
											// Processing sample task 78 of consumer random variable null.
											{
												for(int var76 = 0; var76 < noObs; var76 += 1) {
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
																	double[] cv$temp$4$prob;
																	{
																		cv$temp$4$prob = prob;
																	}
																	int cv$temp$5$$var226;
																	{
																		// Constructing a random variable input for use later.
																		int $var226 = noProducts;
																		cv$temp$5$$var226 = $var226;
																	}
																	
																	// Record the probability of sample task 78 generating output with current configuration.
																	if(((Math.log(1.0) + (((0.0 <= choices[var76]) && (choices[var76] < cv$temp$5$$var226))?Math.log(cv$temp$4$prob[choices[var76]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[var76]) && (choices[var76] < cv$temp$5$$var226))?Math.log(cv$temp$4$prob[choices[var76]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[var76]) && (choices[var76] < cv$temp$5$$var226))?Math.log(cv$temp$4$prob[choices[var76]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[var76]) && (choices[var76] < cv$temp$5$$var226))?Math.log(cv$temp$4$prob[choices[var76]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[var76]) && (choices[var76] < cv$temp$5$$var226))?Math.log(cv$temp$4$prob[choices[var76]]):Double.NEGATIVE_INFINITY)));
																	}
																	
																	// Recorded the probability of reaching sample task 78 with the current configuration.
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
			double var24 = cv$originalValue;
			ut[i$var18] = var24;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 24 and consumer double[] 39.
			{
				for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						{
							exped[i$var36] = Math.exp(ut[i$var36]);
						}
					}
				}
			}
			
			// Guards to ensure that sum is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 24 and consumer double 50.
			{
				for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						if(((0 <= i$var36) && (i$var36 < noProducts))) {
							{
								{
									// Reduction of array exped
									// 
									// A generated name to prevent name collisions if the reduction is implemented more
									// than once in inference and probability code. Initialize the variable to the unit
									// value
									double reduceVar$sum$2 = 0.0;
									
									// For each index in the array to be reduced
									for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1) {
										// Set the left hand term of the reduction function to the return variable value.
										double i$var47 = reduceVar$sum$2;
										
										// Set the right hand term to a value from the array exped
										double j = exped[cv$reduction44Index];
										
										// Execute the reduction function, saving the result into the return value.
										// 
										// Copy the result of the reduction into the variable returned by the reduction.
										reduceVar$sum$2 = (i$var47 + j);
									}
									
									// Write out the new sample value.
									sum = reduceVar$sum$2;
								}
							}
						}
					}
				}
			}
			
			// Guards to ensure that prob is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 24 and consumer double[] 64.
			{
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				boolean[] guard$sample24put65 = guard$sample24put65$global;
				for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						if(((0 <= i$var36) && (i$var36 < noProducts))) {
							{
								for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1)
									// Set the flags to false
									guard$sample24put65[((i$var61 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
							if((i$var36 == i$var61))
								// Set the flags to false
								guard$sample24put65[((i$var61 - 0) / 1)] = false;
						}
					}
				}
				for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						if(((0 <= i$var36) && (i$var36 < noProducts))) {
							{
								for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
									if(!guard$sample24put65[((i$var61 - 0) / 1)]) {
										// The body will execute, so should not be executed again
										guard$sample24put65[((i$var61 - 0) / 1)] = true;
										{
											prob[i$var61] = (exped[i$var61] / sum);
										}
									}
								}
							}
						}
					}
				}
				for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
							if((i$var36 == i$var61)) {
								if(!guard$sample24put65[((i$var61 - 0) / 1)]) {
									// The body will execute, so should not be executed again
									guard$sample24put65[((i$var61 - 0) / 1)] = true;
									{
										prob[i$var61] = (exped[i$var61] / sum);
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
		int cv$max_i$var61 = 0;
		cv$max_i$var61 = Math.max(cv$max_i$var61, ((noProducts - 0) / 1));
		
		// Allocation of guard$sample24put65$global for single threaded execution
		guard$sample24put65$global = new boolean[cv$max_i$var61];
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
		
		// Constructor for choices
		{
			choices = new int[noObs];
		}
		
		// Constructor for logProbability$sample24
		{
			logProbability$sample24 = new double[((((noProducts - 1) - 1) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1) {
			if(!fixedFlag$sample24)
				ut[i$var18] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
			if(!fixedFlag$sample24)
				exped[i$var36] = Math.exp(ut[i$var36]);
		}
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$3 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double i$var47 = reduceVar$sum$3;
			
			// Set the right hand term to a value from the array exped
			double j = exped[cv$reduction44Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!fixedFlag$sample24)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$3 = (i$var47 + j);
		}
		if(!fixedFlag$sample24)
			sum = reduceVar$sum$3;
		for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
			if(!fixedFlag$sample24)
				prob[i$var61] = (exped[i$var61] / sum);
		}
		for(int var76 = 0; var76 < noObs; var76 += 1)
			choices[var76] = DistributionSampling.sampleCategorical(RNG$, prob, noProducts);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1) {
			if(!fixedFlag$sample24)
				ut[i$var18] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
			if(!fixedFlag$sample24)
				exped[i$var36] = Math.exp(ut[i$var36]);
		}
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$5 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double i$var47 = reduceVar$sum$5;
			
			// Set the right hand term to a value from the array exped
			double j = exped[cv$reduction44Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!fixedFlag$sample24)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$5 = (i$var47 + j);
		}
		if(!fixedFlag$sample24)
			sum = reduceVar$sum$5;
		for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
			if(!fixedFlag$sample24)
				prob[i$var61] = (exped[i$var61] / sum);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1) {
			if(!fixedFlag$sample24)
				ut[i$var18] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
			if(!fixedFlag$sample24)
				exped[i$var36] = Math.exp(ut[i$var36]);
		}
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$4 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double i$var47 = reduceVar$sum$4;
			
			// Set the right hand term to a value from the array exped
			double j = exped[cv$reduction44Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!fixedFlag$sample24)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$4 = (i$var47 + j);
		}
		if(!fixedFlag$sample24)
			sum = reduceVar$sum$4;
		for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
			if(!fixedFlag$sample24)
				prob[i$var61] = (exped[i$var61] / sum);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1) {
				if(!fixedFlag$sample24)
					sample24(i$var18);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i$var18 = (noProducts - ((((noProducts - 1) - 1) % 1) + 1)); i$var18 >= ((1 - 1) + 1); i$var18 -= 1) {
				if(!fixedFlag$sample24)
					sample24(i$var18);
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
		logProbability$var23 = 0.0;
		logProbability$ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$sum = 0.0;
		logProbability$prob = 0.0;
		if(!fixedProbFlag$sample24) {
			for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1)
				logProbability$sample24[((i$var18 - 1) / 1)] = 0.0;
		}
		logProbability$var65 = 0.0;
		logProbability$choices = 0.0;
		if(!fixedProbFlag$sample78)
			logProbability$var77 = 0.0;
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
		if(fixedFlag$sample24)
			logProbabilityValue$sample24();
		logProbabilityValue$sample78();
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
		logProbabilityValue$sample24();
		logProbabilityValue$sample78();
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
		logProbabilityValue$sample24();
		logProbabilityValue$sample78();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1) {
			if(!fixedFlag$sample24)
				ut[i$var18] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
			if(!fixedFlag$sample24)
				exped[i$var36] = Math.exp(ut[i$var36]);
		}
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$6 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double i$var47 = reduceVar$sum$6;
			
			// Set the right hand term to a value from the array exped
			double j = exped[cv$reduction44Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!fixedFlag$sample24)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$6 = (i$var47 + j);
		}
		if(!fixedFlag$sample24)
			sum = reduceVar$sum$6;
		for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
			if(!fixedFlag$sample24)
				prob[i$var61] = (exped[i$var61] / sum);
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
		for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
			if(setFlag$ut)
				exped[i$var36] = Math.exp(ut[i$var36]);
		}
		if(setFlag$ut) {
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$7 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double i$var47 = reduceVar$sum$7;
				
				// Set the right hand term to a value from the array exped
				double j = exped[cv$reduction44Index];
				
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$7 = (i$var47 + j);
			}
			sum = reduceVar$sum$7;
		}
		for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
			if(setFlag$ut)
				prob[i$var61] = (exped[i$var61] / sum);
		}
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model DiscreteChoiceAlt(int noProducts, int noObs, int[] ObsChoices) {\n"
		     + "    // we just need an uninformative prior for utility intercepts\n"
		     + "\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = new double[noProducts];\n"
		     + "    ut[0] = 0.0;\n"
		     + "    for(int i=1; i<noProducts; i++) \n"
		     + "        ut[i]= gaussian(0, 10).sample();\n"
		     + "\n"
		     + "    // calculate choice probabilities\n"
		     + "    double[] exped = new double[noProducts];\n"
		     + "    for(int i : [0..noProducts)) {\n"
		     + "        exped[i] = exp(ut[i]);\n"
		     + "    }\n"
		     + "    double sum = reduce(exped, 0, (i, j) -> { return i + j; });\n"
		     + "    double[] prob = new double[noProducts];\n"
		     + "    for (int i : [0..noProducts)) {\n"
		     + "        prob[i] = exped[i] / sum;\n"
		     + "    }\n"
		     + "    // draw consumer choices according to the calculated probabilities\n"
		     + "    int[] choices = categorical(prob).sample(noObs);\n"
		     + "\n"
		     + "    // assert generated choices match observed choices\n"
		     + "    choices.observe(ObsChoices);\n"
		     + "}";
	}
}