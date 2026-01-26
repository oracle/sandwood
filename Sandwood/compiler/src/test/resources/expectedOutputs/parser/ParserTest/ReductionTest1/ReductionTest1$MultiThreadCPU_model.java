package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class ReductionTest1$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements ReductionTest1$CoreInterface {
	
	// Declare the variables for the model.
	private int[][] ObsArr;
	private int T;
	private double[][] TimeFeat;
	private int[][] arr;
	private boolean[][] constrainedFlag$sample101;
	private boolean fixedFlag$sample101 = false;
	private boolean fixedProbFlag$sample101 = false;
	private boolean fixedProbFlag$sample165 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$arr;
	private double[][] logProbability$sample101;
	private double[][] logProbability$sample165;
	private double logProbability$sum_t;
	private double logProbability$time_coeff;
	private double logProbability$time_impact;
	private int n_ac;
	private double[][] sum_t;
	private boolean system$gibbsForward = true;
	private double[][] time_coeff;
	private int time_dim;
	private double[][][] time_impact;

	public ReductionTest1$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for ObsArr.
	@Override
	public final int[][] get$ObsArr() {
		return ObsArr;
	}

	// Setter for ObsArr.
	@Override
	public final void set$ObsArr(int[][] cv$value) {
		// Set ObsArr
		ObsArr = cv$value;
	}

	// Getter for T.
	@Override
	public final int get$T() {
		return T;
	}

	// Setter for T.
	@Override
	public final void set$T(int cv$value) {
		T = cv$value;
	}

	// Getter for TimeFeat.
	@Override
	public final double[][] get$TimeFeat() {
		return TimeFeat;
	}

	// Setter for TimeFeat.
	@Override
	public final void set$TimeFeat(double[][] cv$value) {
		// Set TimeFeat
		TimeFeat = cv$value;
	}

	// Getter for arr.
	@Override
	public final int[][] get$arr() {
		return arr;
	}

	// Getter for fixedFlag$sample101.
	@Override
	public final boolean get$fixedFlag$sample101() {
		return fixedFlag$sample101;
	}

	// Setter for fixedFlag$sample101.
	@Override
	public final void set$fixedFlag$sample101(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample101 including if probabilities
		// need to be updated.
		fixedFlag$sample101 = cv$value;
		
		// Should the probability of sample 101 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample101 = (fixedFlag$sample101 && fixedProbFlag$sample101);
		
		// Should the probability of sample 165 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample165 = (fixedFlag$sample101 && fixedProbFlag$sample165);
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

	// Getter for logProbability$arr.
	@Override
	public final double get$logProbability$arr() {
		return logProbability$arr;
	}

	// Getter for logProbability$sum_t.
	@Override
	public final double get$logProbability$sum_t() {
		return logProbability$sum_t;
	}

	// Getter for logProbability$time_coeff.
	@Override
	public final double get$logProbability$time_coeff() {
		return logProbability$time_coeff;
	}

	// Getter for logProbability$time_impact.
	@Override
	public final double get$logProbability$time_impact() {
		return logProbability$time_impact;
	}

	// Getter for n_ac.
	@Override
	public final int get$n_ac() {
		return n_ac;
	}

	// Setter for n_ac.
	@Override
	public final void set$n_ac(int cv$value) {
		n_ac = cv$value;
	}

	// Getter for sum_t.
	@Override
	public final double[][] get$sum_t() {
		return sum_t;
	}

	// Getter for time_coeff.
	@Override
	public final double[][] get$time_coeff() {
		return time_coeff;
	}

	// Setter for time_coeff.
	@Override
	public final void set$time_coeff(double[][] cv$value) {
		// Set flags for all the side effects of time_coeff including if probabilities need
		// to be updated.
		// Set time_coeff
		time_coeff = cv$value;
		
		// Unset the fixed probability flag for sample 101 as it depends on time_coeff.
		fixedProbFlag$sample101 = false;
		
		// Unset the fixed probability flag for sample 165 as it depends on time_coeff.
		fixedProbFlag$sample165 = false;
	}

	// Getter for time_dim.
	@Override
	public final int get$time_dim() {
		return time_dim;
	}

	// Getter for time_impact.
	@Override
	public final double[][][] get$time_impact() {
		return time_impact;
	}

	// Calculate the probability of the samples represented by sample101 using sampled
	// values.
	private final void logProbabilityValue$sample101() {
		// Determine if we need to calculate the values for sample task 101 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample101) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int var95 = 0; var95 < time_dim; var95 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						{
							// The sample value to calculate the probability of generating
							double cv$sampleValue = time_coeff[i$var80][var95];
							{
								{
									double var83 = 0.0;
									double var84 = 1.0;
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var84)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var83) / Math.sqrt(var84))) - (0.5 * Math.log(var84))):Double.NEGATIVE_INFINITY));
									
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
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					
					// Store the sample task probability
					logProbability$sample101[((i$var80 - 0) / 1)][((var95 - 0) / 1)] = cv$sampleProbability;
					
					// Guard to ensure that time_impact is only updated once for this probability.
					boolean cv$guard$time_impact = false;
					
					// Guard to ensure that sum_t is only updated once for this probability.
					boolean cv$guard$sum_t = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					// 
					// Looking for a path between Sample 101 and consumer double[][][] 138.
					{
						{
							for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1) {
								if((i$var80 == i$var119)) {
									for(int j = 0; j < time_dim; j += 1) {
										if((var95 == j)) {
											for(int t = (0 + 1); t < T; t += 1) {
												// If the probability of the variable has not already been updated
												if(!cv$guard$time_impact) {
													// Set the guard so the update is only applied once.
													cv$guard$time_impact = true;
													
													// Update the variable probability
													logProbability$time_impact = (logProbability$time_impact + cv$sampleProbability);
												}
											}
										}
									}
								}
							}
						}
					}
					
					// Looking for a path between Sample 101 and consumer double[][] 153.
					{
						{
							for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1) {
								if((i$var80 == i$var119)) {
									for(int j = 0; j < time_dim; j += 1) {
										if((var95 == j)) {
											for(int t = (0 + 1); t < T; t += 1) {
												for(int index$t$3_4 = (0 + 1); index$t$3_4 < T; index$t$3_4 += 1) {
													if((t == index$t$3_4)) {
														for(int index$i$3_5 = 0; index$i$3_5 < n_ac; index$i$3_5 += 1) {
															if((i$var119 == index$i$3_5)) {
																if(((0 <= j) && (j < time_dim))) {
																	// If the probability of the variable has not already been updated
																	if(!cv$guard$sum_t) {
																		// Set the guard so the update is only applied once.
																		cv$guard$sum_t = true;
																		
																		// Update the variable probability
																		logProbability$sum_t = (logProbability$sum_t + cv$sampleProbability);
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
					}
				}
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			}
			
			// Update the variable probability
			logProbability$time_coeff = (logProbability$time_coeff + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample101)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample101 = fixedFlag$sample101;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1) {
				double cv$rvAccumulator = 0.0;
				for(int var95 = 0; var95 < time_dim; var95 += 1) {
					double cv$sampleValue = logProbability$sample101[((i$var80 - 0) / 1)][((var95 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					
					// Guard to ensure that time_impact is only updated once for this probability.
					boolean cv$guard$time_impact = false;
					
					// Guard to ensure that sum_t is only updated once for this probability.
					boolean cv$guard$sum_t = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					// 
					// Looking for a path between Sample 101 and consumer double[][][] 138.
					{
						{
							for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1) {
								if((i$var80 == i$var119)) {
									for(int j = 0; j < time_dim; j += 1) {
										if((var95 == j)) {
											for(int t = (0 + 1); t < T; t += 1) {
												// If the probability of the variable has not already been updated
												if(!cv$guard$time_impact) {
													// Set the guard so the update is only applied once.
													cv$guard$time_impact = true;
													
													// Update the variable probability
													logProbability$time_impact = (logProbability$time_impact + cv$sampleValue);
												}
											}
										}
									}
								}
							}
						}
					}
					
					// Looking for a path between Sample 101 and consumer double[][] 153.
					{
						{
							for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1) {
								if((i$var80 == i$var119)) {
									for(int j = 0; j < time_dim; j += 1) {
										if((var95 == j)) {
											for(int t = (0 + 1); t < T; t += 1) {
												for(int index$t$5_4 = (0 + 1); index$t$5_4 < T; index$t$5_4 += 1) {
													if((t == index$t$5_4)) {
														for(int index$i$5_5 = 0; index$i$5_5 < n_ac; index$i$5_5 += 1) {
															if((i$var119 == index$i$5_5)) {
																if(((0 <= j) && (j < time_dim))) {
																	// If the probability of the variable has not already been updated
																	if(!cv$guard$sum_t) {
																		// Set the guard so the update is only applied once.
																		cv$guard$sum_t = true;
																		
																		// Update the variable probability
																		logProbability$sum_t = (logProbability$sum_t + cv$sampleValue);
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
					}
				}
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			
			// Update the variable probability
			logProbability$time_coeff = (logProbability$time_coeff + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample101)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample165 using sampled
	// values.
	private final void logProbabilityValue$sample165() {
		// Determine if we need to calculate the values for sample task 165 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample165) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int t = (0 + 1); t < T; t += 1) {
				for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						{
							// The sample value to calculate the probability of generating
							int cv$sampleValue = arr[t][i$var119];
							{
								{
									double var156 = sum_t[t][i$var119];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$sampleValue, var156));
									
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
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					
					// Store the sample task probability
					logProbability$sample165[((t - (0 + 1)) / 1)][((i$var119 - 0) / 1)] = cv$sampleProbability;
				}
			}
			
			// Update the variable probability
			logProbability$arr = (logProbability$arr + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample165 = fixedFlag$sample101;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int t = (0 + 1); t < T; t += 1) {
				for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample165[((t - (0 + 1)) / 1)][((i$var119 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			
			// Update the variable probability
			logProbability$arr = (logProbability$arr + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 101 drawn from Gaussian 85. Inference was performed using Metropolis-Hastings.
	private final void sample101(int i$var80, int var95, int threadID$cv$i$var80, Rng RNG$) {
		if(true) {
			constrainedFlag$sample101[((i$var80 - 0) / 1)][((var95 - 0) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// Metropolis-Hastings
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// The original value of the sample
			double cv$originalValue = time_coeff[i$var80][var95];
			
			// The probability of the random variable generating the originally sampled value
			double cv$originalProbability = 0.0;
			
			// Calculate a proposed variance.
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			
			// Ensure the variance is at least 0.01
			if((cv$var < 0.01))
				cv$var = 0.01;
			
			// The proposed new value for the sample
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
			
			// The probability of the random variable generating the new sample value.
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((constrainedFlag$sample101[((i$var80 - 0) / 1)][((var95 - 0) / 1)] || (cv$valuePos == 0))) {
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
						// 
						// Write out the value of the sample to a temporary variable prior to updating the
						// intermediate variables.
						double var96 = cv$proposedValue;
						
						// Guards to ensure that time_coeff is only updated when there is a valid path.
						{
							{
								{
									double[] var86 = time_coeff[i$var80];
									var86[var95] = cv$currentValue;
								}
							}
						}
						
						// Guards to ensure that time_impact is only updated when there is a valid path.
						// 
						// Looking for a path between Sample 101 and consumer double[][][] 138.
						{
							{
								for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1) {
									if((i$var80 == i$var119)) {
										for(int j = 0; j < time_dim; j += 1) {
											if((var95 == j)) {
												for(int t = (0 + 1); t < T; t += 1) {
													double[][] var129 = time_impact[t];
													double[] var130 = var129[i$var119];
													var130[j] = (TimeFeat[t][j] * time_coeff[i$var119][j]);
												}
											}
										}
									}
								}
							}
						}
						
						// Guards to ensure that sum_t is only updated when there is a valid path.
						// 
						// Looking for a path between Sample 101 and consumer double[][] 153.
						{
							{
								for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1) {
									if((i$var80 == i$var119)) {
										for(int j = 0; j < time_dim; j += 1) {
											if((var95 == j)) {
												for(int t = (0 + 1); t < T; t += 1) {
													for(int index$t$3_4 = (0 + 1); index$t$3_4 < T; index$t$3_4 += 1) {
														if((t == index$t$3_4)) {
															for(int index$i$3_5 = 0; index$i$3_5 < n_ac; index$i$3_5 += 1) {
																if((i$var119 == index$i$3_5)) {
																	if(((0 <= j) && (j < time_dim))) {
																		{
																			double[] var139 = sum_t[index$t$3_4];
																			
																			// Reduction of array null
																			// 
																			// A generated name to prevent name collisions if the reduction is implemented more
																			// than once in inference and probability code. Initialize the variable to the unit
																			// value
																			double reduceVar$var151$9 = 0.0;
																			
																			// For each index in the array to be reduced
																			for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1) {
																				// Set the left hand term of the reduction function to the return variable value.
																				double x = reduceVar$var151$9;
																				
																				// Set the right hand term to a value from the array var141
																				double y = time_impact[index$t$3_4][index$i$3_5][cv$reduction152Index];
																				
																				// Execute the reduction function, saving the result into the return value.
																				// 
																				// Copy the result of the reduction into the variable returned by the reduction.
																				reduceVar$var151$9 = (x + y);
																			}
																			var139[index$i$3_5] = reduceVar$var151$9;
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
						}
					}
					{
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						double cv$accumulatedProbabilities = (Math.log(1.0) + ((0.0 < 1.0)?(DistributionSampling.logProbabilityGaussian(((cv$currentValue - 0.0) / Math.sqrt(1.0))) - (0.5 * Math.log(1.0))):Double.NEGATIVE_INFINITY));
						
						// Processing random variable 157.
						{
							// Looking for a path between Sample 101 and consumer Poisson 157.
							{
								{
									double traceTempVariable$var134$4_1 = cv$currentValue;
									for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1) {
										if((i$var80 == i$var119)) {
											for(int j = 0; j < time_dim; j += 1) {
												if((var95 == j)) {
													for(int t = (0 + 1); t < T; t += 1) {
														double traceTempVariable$x$4_5 = (TimeFeat[t][j] * traceTempVariable$var134$4_1);
														for(int index$t$4_6 = (0 + 1); index$t$4_6 < T; index$t$4_6 += 1) {
															if((t == index$t$4_6)) {
																for(int index$i$4_7 = 0; index$i$4_7 < n_ac; index$i$4_7 += 1) {
																	if((i$var119 == index$i$4_7)) {
																		if(((0 <= j) && (j < time_dim))) {
																			if((0 < time_dim)) {
																				// Reduction of array null
																				// 
																				// A generated name to prevent name collisions if the reduction is implemented more
																				// than once in inference and probability code. Initialize the variable to the unit
																				// value
																				double reduceVar$var151$10 = 0.0;
																				
																				// Reduce for every value except a masked value which will be skipped.
																				for(int cv$reduction697Index = 0; cv$reduction697Index < j; cv$reduction697Index += 1) {
																					// Set the left hand term of the reduction function to the return variable value.
																					double x = reduceVar$var151$10;
																					
																					// Set the right hand term to a value from the array var141
																					double y = time_impact[index$t$4_6][index$i$4_7][cv$reduction697Index];
																					
																					// Execute the reduction function, saving the result into the return value.
																					// 
																					// Copy the result of the reduction into the variable returned by the reduction.
																					reduceVar$var151$10 = (x + y);
																				}
																				for(int cv$reduction697Index = (j + 1); cv$reduction697Index < time_dim; cv$reduction697Index += 1) {
																					// Set the left hand term of the reduction function to the return variable value.
																					double x = reduceVar$var151$10;
																					
																					// Set the right hand term to a value from the array var141
																					double y = time_impact[index$t$4_6][index$i$4_7][cv$reduction697Index];
																					
																					// Execute the reduction function, saving the result into the return value.
																					// 
																					// Copy the result of the reduction into the variable returned by the reduction.
																					reduceVar$var151$10 = (x + y);
																				}
																				double cv$reduced152 = reduceVar$var151$10;
																				
																				// Copy the result of the reduction into the variable returned by the reduction.
																				reduceVar$var151$10 = (traceTempVariable$x$4_5 + cv$reduced152);
																				double traceTempVariable$var151$4_8 = reduceVar$var151$10;
																				double traceTempVariable$var156$4_9 = traceTempVariable$var151$4_8;
																				for(int index$t$4_10 = (0 + 1); index$t$4_10 < T; index$t$4_10 += 1) {
																					if((index$t$4_6 == index$t$4_10)) {
																						for(int index$i$4_11 = 0; index$i$4_11 < n_ac; index$i$4_11 += 1) {
																							if((index$i$4_7 == index$i$4_11)) {
																								// Processing sample task 165 of consumer random variable null.
																								{
																									{
																										// Flag recording if this sample task of the consuming random variable is constrained.
																										boolean cv$sampleConstrained = true;
																										if(cv$sampleConstrained) {
																											// Mark that the sample has observed constrained data.
																											constrainedFlag$sample101[((i$var80 - 0) / 1)][((var95 - 0) / 1)] = true;
																											
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
																																// Record the probability of sample task 165 generating output with current configuration.
																																if(((Math.log(1.0) + DistributionSampling.logProbabilityPoisson(arr[index$t$4_10][index$i$4_11], traceTempVariable$var156$4_9)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityPoisson(arr[index$t$4_10][index$i$4_11], traceTempVariable$var156$4_9)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(arr[index$t$4_10][index$i$4_11], traceTempVariable$var156$4_9));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(arr[index$t$4_10][index$i$4_11], traceTempVariable$var156$4_9)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(arr[index$t$4_10][index$i$4_11], traceTempVariable$var156$4_9)));
																																}
																																
																																// Recorded the probability of reaching sample task 165 with the current configuration.
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
					
					// The probability ration for the proposed value and the current value.
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					
					// Test if the probability of the sample is sufficient to keep the value. This needs
					// to be less than or equal as otherwise if the proposed value is not possible and
					// the random value is 0 an impossible value will be accepted.
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
							// If it is not revert the changes.
							// 
							// Set the sample value
							// Write out the value of the sample to a temporary variable prior to updating the
							// intermediate variables.
							double var96 = cv$originalValue;
							
							// Guards to ensure that time_coeff is only updated when there is a valid path.
							{
								{
									{
										double[] var86 = time_coeff[i$var80];
										var86[var95] = var96;
									}
								}
							}
							
							// Guards to ensure that time_impact is only updated when there is a valid path.
							// 
							// Looking for a path between Sample 101 and consumer double[][][] 138.
							{
								{
									for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1) {
										if((i$var80 == i$var119)) {
											for(int j = 0; j < time_dim; j += 1) {
												if((var95 == j)) {
													for(int t = (0 + 1); t < T; t += 1) {
														double[][] var129 = time_impact[t];
														double[] var130 = var129[i$var119];
														var130[j] = (TimeFeat[t][j] * time_coeff[i$var119][j]);
													}
												}
											}
										}
									}
								}
							}
							
							// Guards to ensure that sum_t is only updated when there is a valid path.
							// 
							// Looking for a path between Sample 101 and consumer double[][] 153.
							{
								{
									for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1) {
										if((i$var80 == i$var119)) {
											for(int j = 0; j < time_dim; j += 1) {
												if((var95 == j)) {
													for(int t = (0 + 1); t < T; t += 1) {
														for(int index$t$9_4 = (0 + 1); index$t$9_4 < T; index$t$9_4 += 1) {
															if((t == index$t$9_4)) {
																for(int index$i$9_5 = 0; index$i$9_5 < n_ac; index$i$9_5 += 1) {
																	if((i$var119 == index$i$9_5)) {
																		if(((0 <= j) && (j < time_dim))) {
																			{
																				double[] var139 = sum_t[index$t$9_4];
																				
																				// Reduction of array null
																				// 
																				// A generated name to prevent name collisions if the reduction is implemented more
																				// than once in inference and probability code. Initialize the variable to the unit
																				// value
																				double reduceVar$var151$11 = 0.0;
																				
																				// For each index in the array to be reduced
																				for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1) {
																					// Set the left hand term of the reduction function to the return variable value.
																					double x = reduceVar$var151$11;
																					
																					// Set the right hand term to a value from the array var141
																					double y = time_impact[index$t$9_4][index$i$9_5][cv$reduction152Index];
																					
																					// Execute the reduction function, saving the result into the return value.
																					// 
																					// Copy the result of the reduction into the variable returned by the reduction.
																					reduceVar$var151$11 = (x + y);
																				}
																				var139[index$i$9_5] = reduceVar$var151$11;
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
		// If time_coeff has not been set already allocate space.
		if(!fixedFlag$sample101) {
			// Constructor for time_coeff
			{
				time_coeff = new double[n_ac][];
				for(int var18 = 0; var18 < n_ac; var18 += 1)
					time_coeff[var18] = new double[TimeFeat[0].length];
				for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1)
					time_coeff[i$var80] = new double[TimeFeat[0].length];
			}
		}
		
		// Constructor for sum_t
		{
			sum_t = new double[T][];
			for(int var31 = 0; var31 < T; var31 += 1)
				sum_t[var31] = new double[n_ac];
		}
		
		// Constructor for time_impact
		{
			time_impact = new double[T][][];
			for(int var44 = 0; var44 < T; var44 += 1) {
				double[][] subarray$0 = new double[n_ac][];
				time_impact[var44] = subarray$0;
				for(int var54 = 0; var54 < n_ac; var54 += 1)
					subarray$0[var54] = new double[TimeFeat[0].length];
			}
		}
		
		// Constructor for arr
		{
			arr = new int[T][];
			for(int var68 = 0; var68 < T; var68 += 1)
				arr[var68] = new int[n_ac];
		}
		
		// Constructor for constrainedFlag$sample101
		{
			constrainedFlag$sample101 = new boolean[((((n_ac - 1) - 0) / 1) + 1)][];
			for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1)
				constrainedFlag$sample101[((i$var80 - 0) / 1)] = new boolean[((((TimeFeat[0].length - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample101
		{
			logProbability$sample101 = new double[((((n_ac - 1) - 0) / 1) + 1)][];
			for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1)
				logProbability$sample101[((i$var80 - 0) / 1)] = new double[((((TimeFeat[0].length - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample165
		{
			logProbability$sample165 = new double[((((T - 1) - (0 + 1)) / 1) + 1)][];
			for(int t = (0 + 1); t < T; t += 1)
				logProbability$sample165[((t - (0 + 1)) / 1)] = new double[((((n_ac - 1) - 0) / 1) + 1)];
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, n_ac, 1,
			(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
						double[] var86 = time_coeff[i$var80];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, time_dim, 1,
							(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1) {
										if(!fixedFlag$sample101)
											var86[var95] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$2)) + 0.0);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, (0 + 1), T, 1,
			(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
						int t = index$t;
						int threadID$t = threadID$index$t;
						double[][] var129 = time_impact[t];
						double[] var139 = sum_t[t];
						int[] var154 = arr[t];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, n_ac, 1,
							(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
										int i$var119 = index$i$var119;
										int threadID$i$var119 = threadID$index$i$var119;
										
										//  Outer loop for dispatching multiple batches of iterations to execute in parallel
										parallelFor(RNG$2, 0, time_dim, 1,
											(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
												
													// Inner loop for running batches of iterations, each batch has its own random number
													// generator.
													for(int j = forStart$j; j < forEnd$j; j += 1) {
														double[] var130 = var129[i$var119];
														if(!fixedFlag$sample101)
															var130[j] = (TimeFeat[t][j] * time_coeff[i$var119][j]);
													}
											}
										);
										
										// Reduction of array null
										// 
										// A generated name to prevent name collisions if the reduction is implemented more
										// than once in inference and probability code. Initialize the variable to the unit
										// value
										double reduceVar$var151$12 = 0.0;
										
										// For each index in the array to be reduced
										for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1) {
											// Set the left hand term of the reduction function to the return variable value.
											double x = reduceVar$var151$12;
											
											// Set the right hand term to a value from the array var141
											double y = time_impact[t][i$var119][cv$reduction152Index];
											
											// Execute the reduction function, saving the result into the return value.
											if(!fixedFlag$sample101)
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$var151$12 = (x + y);
										}
										if(!fixedFlag$sample101)
											var139[i$var119] = reduceVar$var151$12;
										var154[i$var119] = DistributionSampling.samplePoisson(RNG$2, sum_t[t][i$var119]);
									}
							}
						);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, n_ac, 1,
			(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
						double[] var86 = time_coeff[i$var80];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, time_dim, 1,
							(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1) {
										if(!fixedFlag$sample101)
											var86[var95] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$2)) + 0.0);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, (0 + 1), T, 1,
			(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
						int t = index$t;
						int threadID$t = threadID$index$t;
						double[][] var129 = time_impact[t];
						double[] var139 = sum_t[t];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, n_ac, 1,
							(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
										int i$var119 = index$i$var119;
										int threadID$i$var119 = threadID$index$i$var119;
										
										//  Outer loop for dispatching multiple batches of iterations to execute in parallel
										parallelFor(RNG$2, 0, time_dim, 1,
											(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
												
													// Inner loop for running batches of iterations, each batch has its own random number
													// generator.
													for(int j = forStart$j; j < forEnd$j; j += 1) {
														double[] var130 = var129[i$var119];
														var130[j] = (TimeFeat[t][j] * time_coeff[i$var119][j]);
													}
											}
										);
										
										// Reduction of array null
										// 
										// A generated name to prevent name collisions if the reduction is implemented more
										// than once in inference and probability code. Initialize the variable to the unit
										// value
										double reduceVar$var151$16 = 0.0;
										
										// For each index in the array to be reduced
										for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1) {
											// Set the left hand term of the reduction function to the return variable value.
											double x = reduceVar$var151$16;
											
											// Set the right hand term to a value from the array var141
											double y = time_impact[t][i$var119][cv$reduction152Index];
											
											// Execute the reduction function, saving the result into the return value.
											// 
											// Copy the result of the reduction into the variable returned by the reduction.
											reduceVar$var151$16 = (x + y);
										}
										var139[i$var119] = reduceVar$var151$16;
									}
							}
						);
					}
			}
		);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, n_ac, 1,
			(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
						double[] var86 = time_coeff[i$var80];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, time_dim, 1,
							(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1) {
										if(!fixedFlag$sample101)
											var86[var95] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$2)) + 0.0);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, (0 + 1), T, 1,
			(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
						int t = index$t;
						int threadID$t = threadID$index$t;
						double[][] var129 = time_impact[t];
						double[] var139 = sum_t[t];
						int[] var154 = arr[t];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, n_ac, 1,
							(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
										int i$var119 = index$i$var119;
										int threadID$i$var119 = threadID$index$i$var119;
										
										//  Outer loop for dispatching multiple batches of iterations to execute in parallel
										parallelFor(RNG$2, 0, time_dim, 1,
											(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
												
													// Inner loop for running batches of iterations, each batch has its own random number
													// generator.
													for(int j = forStart$j; j < forEnd$j; j += 1) {
														double[] var130 = var129[i$var119];
														var130[j] = (TimeFeat[t][j] * time_coeff[i$var119][j]);
													}
											}
										);
										
										// Reduction of array null
										// 
										// A generated name to prevent name collisions if the reduction is implemented more
										// than once in inference and probability code. Initialize the variable to the unit
										// value
										double reduceVar$var151$13 = 0.0;
										
										// For each index in the array to be reduced
										for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1) {
											// Set the left hand term of the reduction function to the return variable value.
											double x = reduceVar$var151$13;
											
											// Set the right hand term to a value from the array var141
											double y = time_impact[t][i$var119][cv$reduction152Index];
											
											// Execute the reduction function, saving the result into the return value.
											// 
											// Copy the result of the reduction into the variable returned by the reduction.
											reduceVar$var151$13 = (x + y);
										}
										var139[i$var119] = reduceVar$var151$13;
										var154[i$var119] = DistributionSampling.samplePoisson(RNG$2, sum_t[t][i$var119]);
									}
							}
						);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, n_ac, 1,
			(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
						double[] var86 = time_coeff[i$var80];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, time_dim, 1,
							(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1) {
										if(!fixedFlag$sample101)
											var86[var95] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$2)) + 0.0);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, (0 + 1), T, 1,
			(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
						int t = index$t;
						int threadID$t = threadID$index$t;
						double[][] var129 = time_impact[t];
						double[] var139 = sum_t[t];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, n_ac, 1,
							(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
										int i$var119 = index$i$var119;
										int threadID$i$var119 = threadID$index$i$var119;
										
										//  Outer loop for dispatching multiple batches of iterations to execute in parallel
										parallelFor(RNG$2, 0, time_dim, 1,
											(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
												
													// Inner loop for running batches of iterations, each batch has its own random number
													// generator.
													for(int j = forStart$j; j < forEnd$j; j += 1) {
														double[] var130 = var129[i$var119];
														if(!fixedFlag$sample101)
															var130[j] = (TimeFeat[t][j] * time_coeff[i$var119][j]);
													}
											}
										);
										
										// Reduction of array null
										// 
										// A generated name to prevent name collisions if the reduction is implemented more
										// than once in inference and probability code. Initialize the variable to the unit
										// value
										double reduceVar$var151$14 = 0.0;
										
										// For each index in the array to be reduced
										for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1) {
											// Set the left hand term of the reduction function to the return variable value.
											double x = reduceVar$var151$14;
											
											// Set the right hand term to a value from the array var141
											double y = time_impact[t][i$var119][cv$reduction152Index];
											
											// Execute the reduction function, saving the result into the return value.
											if(!fixedFlag$sample101)
												// Copy the result of the reduction into the variable returned by the reduction.
												reduceVar$var151$14 = (x + y);
										}
										if(!fixedFlag$sample101)
											var139[i$var119] = reduceVar$var151$14;
									}
							}
						);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, n_ac, 1,
			(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
						double[] var86 = time_coeff[i$var80];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, time_dim, 1,
							(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1) {
										if(!fixedFlag$sample101)
											var86[var95] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$2)) + 0.0);
									}
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, (0 + 1), T, 1,
			(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
						int t = index$t;
						int threadID$t = threadID$index$t;
						double[][] var129 = time_impact[t];
						double[] var139 = sum_t[t];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, n_ac, 1,
							(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
										int i$var119 = index$i$var119;
										int threadID$i$var119 = threadID$index$i$var119;
										
										//  Outer loop for dispatching multiple batches of iterations to execute in parallel
										parallelFor(RNG$2, 0, time_dim, 1,
											(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
												
													// Inner loop for running batches of iterations, each batch has its own random number
													// generator.
													for(int j = forStart$j; j < forEnd$j; j += 1) {
														double[] var130 = var129[i$var119];
														var130[j] = (TimeFeat[t][j] * time_coeff[i$var119][j]);
													}
											}
										);
										
										// Reduction of array null
										// 
										// A generated name to prevent name collisions if the reduction is implemented more
										// than once in inference and probability code. Initialize the variable to the unit
										// value
										double reduceVar$var151$15 = 0.0;
										
										// For each index in the array to be reduced
										for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1) {
											// Set the left hand term of the reduction function to the return variable value.
											double x = reduceVar$var151$15;
											
											// Set the right hand term to a value from the array var141
											double y = time_impact[t][i$var119][cv$reduction152Index];
											
											// Execute the reduction function, saving the result into the return value.
											// 
											// Copy the result of the reduction into the variable returned by the reduction.
											reduceVar$var151$15 = (x + y);
										}
										var139[i$var119] = reduceVar$var151$15;
									}
							}
						);
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
			parallelFor(RNG$, 0, n_ac, 1,
				(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
							for(int var95 = 0; var95 < time_dim; var95 += 1) {
								if(!fixedFlag$sample101)
									sample101(i$var80, var95, threadID$i$var80, RNG$1);
							}
						}
				}
			);
		// Infer the samples in reverse chronological order.
		else
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, n_ac, 1,
				(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
							for(int var95 = (time_dim - ((((time_dim - 1) - 0) % 1) + 1)); var95 >= ((0 - 1) + 1); var95 -= 1) {
								if(!fixedFlag$sample101)
									sample101(i$var80, var95, threadID$i$var80, RNG$1);
							}
						}
				}
			);
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
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
		logProbability$time_coeff = 0.0;
		logProbability$time_impact = 0.0;
		logProbability$sum_t = 0.0;
		if(!fixedProbFlag$sample101) {
			for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1) {
				for(int var95 = 0; var95 < time_dim; var95 += 1)
					logProbability$sample101[((i$var80 - 0) / 1)][((var95 - 0) / 1)] = Double.NaN;
			}
		}
		logProbability$arr = 0.0;
		if(!fixedProbFlag$sample165) {
			for(int t = (0 + 1); t < T; t += 1) {
				for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1)
					logProbability$sample165[((t - (0 + 1)) / 1)][((i$var119 - 0) / 1)] = Double.NaN;
			}
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		time_dim = TimeFeat[0].length;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample101$1 = 0; index$constrainedFlag$sample101$1 < constrainedFlag$sample101.length; index$constrainedFlag$sample101$1 += 1) {
			boolean[] cv$constrainedFlag$sample101$1 = constrainedFlag$sample101[index$constrainedFlag$sample101$1];
			for(int index$constrainedFlag$sample101$2 = 0; index$constrainedFlag$sample101$2 < cv$constrainedFlag$sample101$1.length; index$constrainedFlag$sample101$2 += 1)
				cv$constrainedFlag$sample101$1[index$constrainedFlag$sample101$2] = true;
		}
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(fixedFlag$sample101)
			logProbabilityValue$sample101();
		logProbabilityValue$sample165();
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
		logProbabilityValue$sample101();
		logProbabilityValue$sample165();
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
		logProbabilityValue$sample101();
		logProbabilityValue$sample165();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Deep copy between arrays
		int[][] cv$source1 = ObsArr;
		int[][] cv$target1 = arr;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = cv$source1[cv$index1];
			int[] cv$target2 = cv$target1[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, (0 + 1), T, 1,
			(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
						int t = index$t;
						int threadID$t = threadID$index$t;
						double[][] var129 = time_impact[t];
						double[] var139 = sum_t[t];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, n_ac, 1,
							(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
										int i$var119 = index$i$var119;
										int threadID$i$var119 = threadID$index$i$var119;
										
										//  Outer loop for dispatching multiple batches of iterations to execute in parallel
										parallelFor(RNG$2, 0, time_dim, 1,
											(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
												
													// Inner loop for running batches of iterations, each batch has its own random number
													// generator.
													for(int j = forStart$j; j < forEnd$j; j += 1) {
														double[] var130 = var129[i$var119];
														var130[j] = (TimeFeat[t][j] * time_coeff[i$var119][j]);
													}
											}
										);
										
										// Reduction of array null
										// 
										// A generated name to prevent name collisions if the reduction is implemented more
										// than once in inference and probability code. Initialize the variable to the unit
										// value
										double reduceVar$var151$17 = 0.0;
										
										// For each index in the array to be reduced
										for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1) {
											// Set the left hand term of the reduction function to the return variable value.
											double x = reduceVar$var151$17;
											
											// Set the right hand term to a value from the array var141
											double y = time_impact[t][i$var119][cv$reduction152Index];
											
											// Execute the reduction function, saving the result into the return value.
											// 
											// Copy the result of the reduction into the variable returned by the reduction.
											reduceVar$var151$17 = (x + y);
										}
										var139[i$var119] = reduceVar$var151$17;
									}
							}
						);
					}
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model ReductionTest1(int T, int n_ac, int[][] ObsArr, double[][] TimeFeat) {\n"
		     + "    int time_dim = TimeFeat[0].length; //length of the feature vector\n"
		     + "\n"
		     + "\n"
		     + "    double[][] time_coeff = new double[n_ac][time_dim];\n"
		     + "    double[][] sum_t = new double[T][n_ac];\n"
		     + "    double[][][] time_impact = new double[T][n_ac][time_dim];\n"
		     + "    int[][] arr = new int[T][n_ac];\n"
		     + "    \n"
		     + "    for (int i : [0..n_ac))\n"
		     + "        time_coeff[i] = gaussian(0,1).sample(time_dim);\n"
		     + "\n"
		     + "    for (int t : (0..T)) {\n"
		     + "        for (int i : [0..n_ac)){\n"
		     + "            for (int j : [0..time_dim))\n"
		     + "                time_impact[t][i][j] = TimeFeat[t][j]*time_coeff[i][j];\n"
		     + "            //calculate sum\n"
		     + "            sum_t[t][i] = reduce(time_impact[t][i], 0, (x, y) -> { return x + y; });\n"
		     + "            arr[t][i] = poisson(sum_t[t][i]).sample();\n"
		     + "        }\n"
		     + "    }\n"
		     + "    arr.observe(ObsArr);\n"
		     + "}";
	}
}