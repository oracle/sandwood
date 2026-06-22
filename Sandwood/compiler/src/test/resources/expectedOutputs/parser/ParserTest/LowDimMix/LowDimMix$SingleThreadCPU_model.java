package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.internal.numericTools.Gaussian;
import org.sandwood.runtime.model.ExecutionTarget;

final class LowDimMix$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements LowDimMix$CoreInterface {
	
	// Declare the variables for the model.
	private int N;
	private boolean[] component;
	private boolean[] constrainedFlag$sample101;
	private boolean[] constrainedFlag$sample20;
	private boolean[] constrainedFlag$sample83;
	private boolean constrainedFlag$sample88 = true;
	private double[] cv$var97$stateProbabilityGlobal;
	private boolean fixedFlag$sample101 = false;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample83 = false;
	private boolean fixedFlag$sample88 = false;
	private boolean fixedProbFlag$sample101 = false;
	private boolean fixedProbFlag$sample138 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample83 = false;
	private boolean fixedProbFlag$sample88 = false;
	private boolean[] guard$sample20if124$global;
	private int length$yObserved;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$component;
	private double logProbability$componentDistribution;
	private double logProbability$mu;
	private double logProbability$rawMu;
	private double[] logProbability$sample138;
	private double[] logProbability$sample20;
	private double logProbability$sigma;
	private double logProbability$theta;
	private double logProbability$var79;
	private double logProbability$var97;
	private double logProbability$y;
	private double[] mu;
	private double[] rawMu;
	private double[] sigma;
	private boolean system$gibbsForward = true;
	private double theta;
	private double[] y;
	private double[] yObserved;

	public LowDimMix$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for N.
	@Override
	public final int get$N() {
		return N;
	}

	// Getter for component.
	@Override
	public final boolean[] get$component() {
		return component;
	}

	// Setter for component.
	@Override
	public final void set$component(boolean[] cv$value, boolean allocated$) {
		// Set flags for all the side effects of component including if probabilities need
		// to be updated.
		component = cv$value;
		
		// Unset the fixed probability flag for sample 101 as it depends on component.
		fixedProbFlag$sample101 = false;
		
		// Unset the fixed probability flag for sample 138 as it depends on component.
		fixedProbFlag$sample138 = false;
	}

	// Getter for fixedFlag$sample101.
	@Override
	public final boolean get$fixedFlag$sample101() {
		return fixedFlag$sample101;
	}

	// Setter for fixedFlag$sample101.
	@Override
	public final void set$fixedFlag$sample101(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample101 including if probabilities
		// need to be updated.
		fixedFlag$sample101 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample101$1 = 0; index$constrainedFlag$sample101$1 < constrainedFlag$sample101.length; index$constrainedFlag$sample101$1 += 1)
				constrainedFlag$sample101[index$constrainedFlag$sample101$1] = fixedFlag$sample101;
		}
		
		// Should the probability of sample 101 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample101 = (fixedFlag$sample101 && fixedProbFlag$sample101);
		
		// Should the probability of sample 138 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample138 = (fixedFlag$sample101 && fixedProbFlag$sample138);
	}

	// Getter for fixedFlag$sample20.
	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	// Setter for fixedFlag$sample20.
	@Override
	public final void set$fixedFlag$sample20(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample20 including if probabilities
		// need to be updated.
		fixedFlag$sample20 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample20$1 = 0; index$constrainedFlag$sample20$1 < constrainedFlag$sample20.length; index$constrainedFlag$sample20$1 += 1)
				constrainedFlag$sample20[index$constrainedFlag$sample20$1] = fixedFlag$sample20;
		}
		
		// Should the probability of sample 20 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample20 = (fixedFlag$sample20 && fixedProbFlag$sample20);
		
		// Should the probability of sample 138 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample138 = (fixedFlag$sample20 && fixedProbFlag$sample138);
	}

	// Getter for fixedFlag$sample83.
	@Override
	public final boolean get$fixedFlag$sample83() {
		return fixedFlag$sample83;
	}

	// Setter for fixedFlag$sample83.
	@Override
	public final void set$fixedFlag$sample83(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample83 including if probabilities
		// need to be updated.
		fixedFlag$sample83 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample83$1 = 0; index$constrainedFlag$sample83$1 < constrainedFlag$sample83.length; index$constrainedFlag$sample83$1 += 1)
				constrainedFlag$sample83[index$constrainedFlag$sample83$1] = fixedFlag$sample83;
		}
		
		// Should the probability of sample 83 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample83 = (fixedFlag$sample83 && fixedProbFlag$sample83);
		
		// Should the probability of sample 138 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample138 = (fixedFlag$sample83 && fixedProbFlag$sample138);
	}

	// Getter for fixedFlag$sample88.
	@Override
	public final boolean get$fixedFlag$sample88() {
		return fixedFlag$sample88;
	}

	// Setter for fixedFlag$sample88.
	@Override
	public final void set$fixedFlag$sample88(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample88 including if probabilities
		// need to be updated.
		fixedFlag$sample88 = cv$value;
		constrainedFlag$sample88 = (fixedFlag$sample88 || constrainedFlag$sample88);
		
		// Should the probability of sample 88 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample88 = (fixedFlag$sample88 && fixedProbFlag$sample88);
		
		// Should the probability of sample 101 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample101 = (fixedFlag$sample88 && fixedProbFlag$sample101);
	}

	// Getter for length$yObserved.
	@Override
	public final int get$length$yObserved() {
		return length$yObserved;
	}

	// Setter for length$yObserved.
	@Override
	public final void set$length$yObserved(int cv$value, boolean allocated$) {
		length$yObserved = cv$value;
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

	// Getter for logProbability$component.
	@Override
	public final double get$logProbability$component() {
		return logProbability$component;
	}

	// Getter for logProbability$componentDistribution.
	@Override
	public final double get$logProbability$componentDistribution() {
		return logProbability$componentDistribution;
	}

	// Getter for logProbability$mu.
	@Override
	public final double get$logProbability$mu() {
		return logProbability$mu;
	}

	// Getter for logProbability$rawMu.
	@Override
	public final double get$logProbability$rawMu() {
		return logProbability$rawMu;
	}

	// Getter for logProbability$sigma.
	@Override
	public final double get$logProbability$sigma() {
		return logProbability$sigma;
	}

	// Getter for logProbability$theta.
	@Override
	public final double get$logProbability$theta() {
		return logProbability$theta;
	}

	// Getter for logProbability$y.
	@Override
	public final double get$logProbability$y() {
		return logProbability$y;
	}

	// Getter for mu.
	@Override
	public final double[] get$mu() {
		return mu;
	}

	// Getter for rawMu.
	@Override
	public final double[] get$rawMu() {
		return rawMu;
	}

	// Setter for rawMu.
	@Override
	public final void set$rawMu(double[] cv$value, boolean allocated$) {
		// Set flags for all the side effects of rawMu including if probabilities need to
		// be updated.
		rawMu = cv$value;
		
		// Unset the fixed probability flag for sample 20 as it depends on rawMu.
		fixedProbFlag$sample20 = false;
		
		// Unset the fixed probability flag for sample 138 as it depends on rawMu.
		fixedProbFlag$sample138 = false;
	}

	// Getter for sigma.
	@Override
	public final double[] get$sigma() {
		return sigma;
	}

	// Setter for sigma.
	@Override
	public final void set$sigma(double[] cv$value, boolean allocated$) {
		// Set flags for all the side effects of sigma including if probabilities need to
		// be updated.
		sigma = cv$value;
		
		// Unset the fixed probability flag for sample 83 as it depends on sigma.
		fixedProbFlag$sample83 = false;
		
		// Unset the fixed probability flag for sample 138 as it depends on sigma.
		fixedProbFlag$sample138 = false;
	}

	// Getter for theta.
	@Override
	public final double get$theta() {
		return theta;
	}

	// Setter for theta.
	@Override
	public final void set$theta(double cv$value, boolean allocated$) {
		// Set flags for all the side effects of theta including if probabilities need to
		// be updated.
		theta = cv$value;
		
		// Unset the fixed probability flag for sample 88 as it depends on theta.
		fixedProbFlag$sample88 = false;
		
		// Unset the fixed probability flag for sample 101 as it depends on theta.
		fixedProbFlag$sample101 = false;
	}

	// Getter for y.
	@Override
	public final double[] get$y() {
		return y;
	}

	// Getter for yObserved.
	@Override
	public final double[] get$yObserved() {
		return yObserved;
	}

	// Setter for yObserved.
	@Override
	public final void set$yObserved(double[] cv$value, boolean allocated$) {
		yObserved = cv$value;
	}

	// Pick a value from the distribution for the unconditioned variable from sample101
	private final void drawValueSample101(int var96) {
		component[var96] = DistributionSampling.sampleBernoulli(RNG$, theta);
	}

	// Pick a value from the distribution for the unconditioned variable from sample20
	private final void drawValueSample20(int var19) {
		rawMu[var19] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		
		// Guards to ensure that mu is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 20 and consumer double[] 41.
		{
			// Guard to check that at most one copy of the code is executed for a given set of
			// loop iterations.
			boolean guard$sample20put43 = false;
			{
				if((var19 == 0)) {
					if(!guard$sample20put43) {
						// The body will execute, so should not be executed again
						guard$sample20put43 = true;
						{
							double var39;
							if((rawMu[0] < rawMu[1]))
								var39 = rawMu[0];
							else
								var39 = rawMu[1];
							mu[0] = var39;
						}
					}
				}
			}
			{
				if((var19 == 1)) {
					if(!guard$sample20put43) {
						// The body will execute, so should not be executed again
						guard$sample20put43 = true;
						{
							double var39;
							if((rawMu[0] < rawMu[1]))
								var39 = rawMu[0];
							else
								var39 = rawMu[1];
							mu[0] = var39;
						}
					}
				}
			}
			{
				if((rawMu[0] < rawMu[1])) {
					if((var19 == 0)) {
						if((rawMu[0] < rawMu[1])) {
							if(!guard$sample20put43) {
								// The body will execute, so should not be executed again
								guard$sample20put43 = true;
								{
									double var39;
									if((rawMu[0] < rawMu[1]))
										var39 = rawMu[0];
									else
										var39 = rawMu[1];
									mu[0] = var39;
								}
							}
						}
					}
				}
			}
			{
				if(!(rawMu[0] < rawMu[1])) {
					if((var19 == 1)) {
						if(!(rawMu[0] < rawMu[1])) {
							if(!guard$sample20put43) {
								// The body will execute, so should not be executed again
								guard$sample20put43 = true;
								{
									double var39;
									if((rawMu[0] < rawMu[1]))
										var39 = rawMu[0];
									else
										var39 = rawMu[1];
									mu[0] = var39;
								}
							}
						}
					}
				}
			}
		}
		
		// Guards to ensure that mu is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 20 and consumer double[] 59.
		{
			// Guard to check that at most one copy of the code is executed for a given set of
			// loop iterations.
			boolean guard$sample20put63 = false;
			{
				if((var19 == 0)) {
					if(!guard$sample20put63) {
						// The body will execute, so should not be executed again
						guard$sample20put63 = true;
						{
							double var57;
							if((rawMu[0] < rawMu[1]))
								var57 = rawMu[1];
							else
								var57 = rawMu[0];
							mu[1] = var57;
						}
					}
				}
			}
			{
				if((var19 == 1)) {
					if(!guard$sample20put63) {
						// The body will execute, so should not be executed again
						guard$sample20put63 = true;
						{
							double var57;
							if((rawMu[0] < rawMu[1]))
								var57 = rawMu[1];
							else
								var57 = rawMu[0];
							mu[1] = var57;
						}
					}
				}
			}
			{
				if((rawMu[0] < rawMu[1])) {
					if((var19 == 1)) {
						if((rawMu[0] < rawMu[1])) {
							if(!guard$sample20put63) {
								// The body will execute, so should not be executed again
								guard$sample20put63 = true;
								{
									double var57;
									if((rawMu[0] < rawMu[1]))
										var57 = rawMu[1];
									else
										var57 = rawMu[0];
									mu[1] = var57;
								}
							}
						}
					}
				}
			}
			{
				if(!(rawMu[0] < rawMu[1])) {
					if((var19 == 0)) {
						if(!(rawMu[0] < rawMu[1])) {
							if(!guard$sample20put63) {
								// The body will execute, so should not be executed again
								guard$sample20put63 = true;
								{
									double var57;
									if((rawMu[0] < rawMu[1]))
										var57 = rawMu[1];
									else
										var57 = rawMu[0];
									mu[1] = var57;
								}
							}
						}
					}
				}
			}
		}
	}

	// Pick a value from the distribution for the unconditioned variable from sample83
	private final void drawValueSample83(int var78) {
		sigma[var78] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleTruncatedGaussian(RNG$, ((0.0 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((0.0 - 0.0) / Math.sqrt((2.0 * 2.0)))), ((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0)))))) + 0.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample88
	private final void drawValueSample88() {
		theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 101 drawn from componentDistribution. Inference was performed using
	// variable marginalization.
	private final void inferSample101(int var96) {
		if(true) {
			constrainedFlag$sample101[((var96 - 0) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var97$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
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
				
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				boolean var97 = cv$currentValue;
				
				// Guards to ensure that component is only updated when there is a valid path.
				{
					{
						{
							component[var96] = cv$currentValue;
						}
					}
				}
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= theta) && (theta <= 1.0))?Math.log((cv$currentValue?theta:(1.0 - theta))):Double.NEGATIVE_INFINITY));
					
					// Processing conditional point124.
					{
						// Looking for a path between Sample 101 and consumer double 118.
						{
							{
								for(int n = 0; n < N; n += 1) {
									if((var96 == n)) {
										{
											{
												{
													if(component[n]) {
														double traceTempVariable$componentMu$3_1 = mu[0];
														
														// Processing sample task 138 of consumer random variable null.
														{
															{
																// Flag recording if this sample task of the consuming random variable is constrained.
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	// Mark that the sample has observed constrained data.
																	constrainedFlag$sample101[((var96 - 0) / 1)] = true;
																	
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
																						{
																							double componentSigma;
																							if(component[n])
																								componentSigma = sigma[0];
																							else
																								componentSigma = sigma[1];
																							
																							// Constructing a random variable input for use later.
																							double var128 = (componentSigma * componentSigma);
																							
																							// Record the probability of sample task 138 generating output with current configuration.
																							if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$3_1) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$3_1) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$3_1) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$3_1) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$3_1) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																							}
																							
																							// Recorded the probability of reaching sample task 138 with the current configuration.
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
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
											
											// Looking for a path between Sample 20 and consumer double 118.
											{
												// Guard to check that at most one copy of the code is executed for a given random
												// variable instance.
												boolean[] guard$sample20if124 = guard$sample20if124$global;
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 0)) {
															if(component[n]) {
																if((0 == 0)) {
																	if(component[n])
																		// Set the flags to false
																		guard$sample20if124[((var19 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 1)) {
															if(component[n]) {
																if((0 == 0)) {
																	if(component[n])
																		// Set the flags to false
																		guard$sample20if124[((var19 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((rawMu[0] < rawMu[1])) {
															if((var19 == 0)) {
																if((rawMu[0] < rawMu[1])) {
																	if(component[n]) {
																		if((0 == 0)) {
																			if(component[n])
																				// Set the flags to false
																				guard$sample20if124[((var19 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if(!(rawMu[0] < rawMu[1])) {
															if((var19 == 1)) {
																if(!(rawMu[0] < rawMu[1])) {
																	if(component[n]) {
																		if((0 == 0)) {
																			if(component[n])
																				// Set the flags to false
																				guard$sample20if124[((var19 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 0)) {
															if(component[n]) {
																if((1 == 0)) {
																	if(component[n])
																		// Set the flags to false
																		guard$sample20if124[((var19 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 1)) {
															if(component[n]) {
																if((1 == 0)) {
																	if(component[n])
																		// Set the flags to false
																		guard$sample20if124[((var19 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((rawMu[0] < rawMu[1])) {
															if((var19 == 1)) {
																if((rawMu[0] < rawMu[1])) {
																	if(component[n]) {
																		if((1 == 0)) {
																			if(component[n])
																				// Set the flags to false
																				guard$sample20if124[((var19 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if(!(rawMu[0] < rawMu[1])) {
															if((var19 == 0)) {
																if(!(rawMu[0] < rawMu[1])) {
																	if(component[n]) {
																		if((1 == 0)) {
																			if(component[n])
																				// Set the flags to false
																				guard$sample20if124[((var19 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 0)) {
															if(component[n]) {
																if((0 == 0)) {
																	if(component[n]) {
																		if(!guard$sample20if124[((var19 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample20if124[((var19 - 0) / 1)] = true;
																			
																			// Processing sample task 20 of consumer random variable null.
																			{
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
																									// Constructing a random variable input for use later.
																									double var6 = (2.0 * 2.0);
																									
																									// Record the probability of sample task 20 generating output with current configuration.
																									if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										// If the second value is -infinity.
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
																									}
																									
																									// Recorded the probability of reaching sample task 20 with the current configuration.
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 1)) {
															if(component[n]) {
																if((0 == 0)) {
																	if(component[n]) {
																		if(!guard$sample20if124[((var19 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample20if124[((var19 - 0) / 1)] = true;
																			
																			// Processing sample task 20 of consumer random variable null.
																			{
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
																									// Constructing a random variable input for use later.
																									double var6 = (2.0 * 2.0);
																									
																									// Record the probability of sample task 20 generating output with current configuration.
																									if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										// If the second value is -infinity.
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
																									}
																									
																									// Recorded the probability of reaching sample task 20 with the current configuration.
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((rawMu[0] < rawMu[1])) {
															if((var19 == 0)) {
																if((rawMu[0] < rawMu[1])) {
																	if(component[n]) {
																		if((0 == 0)) {
																			if(component[n]) {
																				if(!guard$sample20if124[((var19 - 0) / 1)]) {
																					// The body will execute, so should not be executed again
																					guard$sample20if124[((var19 - 0) / 1)] = true;
																					
																					// Processing sample task 20 of consumer random variable null.
																					{
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
																											// Constructing a random variable input for use later.
																											double var6 = (2.0 * 2.0);
																											
																											// Record the probability of sample task 20 generating output with current configuration.
																											if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												// If the second value is -infinity.
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
																											}
																											
																											// Recorded the probability of reaching sample task 20 with the current configuration.
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if(!(rawMu[0] < rawMu[1])) {
															if((var19 == 1)) {
																if(!(rawMu[0] < rawMu[1])) {
																	if(component[n]) {
																		if((0 == 0)) {
																			if(component[n]) {
																				if(!guard$sample20if124[((var19 - 0) / 1)]) {
																					// The body will execute, so should not be executed again
																					guard$sample20if124[((var19 - 0) / 1)] = true;
																					
																					// Processing sample task 20 of consumer random variable null.
																					{
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
																											// Constructing a random variable input for use later.
																											double var6 = (2.0 * 2.0);
																											
																											// Record the probability of sample task 20 generating output with current configuration.
																											if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												// If the second value is -infinity.
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
																											}
																											
																											// Recorded the probability of reaching sample task 20 with the current configuration.
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 0)) {
															if(component[n]) {
																if((1 == 0)) {
																	if(component[n]) {
																		if(!guard$sample20if124[((var19 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample20if124[((var19 - 0) / 1)] = true;
																			
																			// Processing sample task 20 of consumer random variable null.
																			{
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
																									// Constructing a random variable input for use later.
																									double var6 = (2.0 * 2.0);
																									
																									// Record the probability of sample task 20 generating output with current configuration.
																									if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										// If the second value is -infinity.
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
																									}
																									
																									// Recorded the probability of reaching sample task 20 with the current configuration.
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 1)) {
															if(component[n]) {
																if((1 == 0)) {
																	if(component[n]) {
																		if(!guard$sample20if124[((var19 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample20if124[((var19 - 0) / 1)] = true;
																			
																			// Processing sample task 20 of consumer random variable null.
																			{
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
																									// Constructing a random variable input for use later.
																									double var6 = (2.0 * 2.0);
																									
																									// Record the probability of sample task 20 generating output with current configuration.
																									if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										// If the second value is -infinity.
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
																									}
																									
																									// Recorded the probability of reaching sample task 20 with the current configuration.
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((rawMu[0] < rawMu[1])) {
															if((var19 == 1)) {
																if((rawMu[0] < rawMu[1])) {
																	if(component[n]) {
																		if((1 == 0)) {
																			if(component[n]) {
																				if(!guard$sample20if124[((var19 - 0) / 1)]) {
																					// The body will execute, so should not be executed again
																					guard$sample20if124[((var19 - 0) / 1)] = true;
																					
																					// Processing sample task 20 of consumer random variable null.
																					{
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
																											// Constructing a random variable input for use later.
																											double var6 = (2.0 * 2.0);
																											
																											// Record the probability of sample task 20 generating output with current configuration.
																											if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												// If the second value is -infinity.
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
																											}
																											
																											// Recorded the probability of reaching sample task 20 with the current configuration.
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if(!(rawMu[0] < rawMu[1])) {
															if((var19 == 0)) {
																if(!(rawMu[0] < rawMu[1])) {
																	if(component[n]) {
																		if((1 == 0)) {
																			if(component[n]) {
																				if(!guard$sample20if124[((var19 - 0) / 1)]) {
																					// The body will execute, so should not be executed again
																					guard$sample20if124[((var19 - 0) / 1)] = true;
																					
																					// Processing sample task 20 of consumer random variable null.
																					{
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
																											// Constructing a random variable input for use later.
																											double var6 = (2.0 * 2.0);
																											
																											// Record the probability of sample task 20 generating output with current configuration.
																											if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												// If the second value is -infinity.
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
																											}
																											
																											// Recorded the probability of reaching sample task 20 with the current configuration.
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
											}
											{
												{
													if(!component[n]) {
														double traceTempVariable$componentMu$30_1 = mu[1];
														
														// Processing sample task 138 of consumer random variable null.
														{
															{
																// Flag recording if this sample task of the consuming random variable is constrained.
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	// Mark that the sample has observed constrained data.
																	constrainedFlag$sample101[((var96 - 0) / 1)] = true;
																	
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
																						{
																							double componentSigma;
																							if(component[n])
																								componentSigma = sigma[0];
																							else
																								componentSigma = sigma[1];
																							
																							// Constructing a random variable input for use later.
																							double var128 = (componentSigma * componentSigma);
																							
																							// Record the probability of sample task 138 generating output with current configuration.
																							if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$30_1) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$30_1) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$30_1) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$30_1) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$30_1) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																							}
																							
																							// Recorded the probability of reaching sample task 138 with the current configuration.
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
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
											
											// Looking for a path between Sample 20 and consumer double 118.
											{
												// Guard to check that at most one copy of the code is executed for a given random
												// variable instance.
												boolean[] guard$sample20if124 = guard$sample20if124$global;
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 0)) {
															if(!component[n]) {
																if((0 == 1)) {
																	if(!component[n])
																		// Set the flags to false
																		guard$sample20if124[((var19 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 1)) {
															if(!component[n]) {
																if((0 == 1)) {
																	if(!component[n])
																		// Set the flags to false
																		guard$sample20if124[((var19 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((rawMu[0] < rawMu[1])) {
															if((var19 == 0)) {
																if((rawMu[0] < rawMu[1])) {
																	if(!component[n]) {
																		if((0 == 1)) {
																			if(!component[n])
																				// Set the flags to false
																				guard$sample20if124[((var19 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if(!(rawMu[0] < rawMu[1])) {
															if((var19 == 1)) {
																if(!(rawMu[0] < rawMu[1])) {
																	if(!component[n]) {
																		if((0 == 1)) {
																			if(!component[n])
																				// Set the flags to false
																				guard$sample20if124[((var19 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 0)) {
															if(!component[n]) {
																if((1 == 1)) {
																	if(!component[n])
																		// Set the flags to false
																		guard$sample20if124[((var19 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 1)) {
															if(!component[n]) {
																if((1 == 1)) {
																	if(!component[n])
																		// Set the flags to false
																		guard$sample20if124[((var19 - 0) / 1)] = false;
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((rawMu[0] < rawMu[1])) {
															if((var19 == 1)) {
																if((rawMu[0] < rawMu[1])) {
																	if(!component[n]) {
																		if((1 == 1)) {
																			if(!component[n])
																				// Set the flags to false
																				guard$sample20if124[((var19 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if(!(rawMu[0] < rawMu[1])) {
															if((var19 == 0)) {
																if(!(rawMu[0] < rawMu[1])) {
																	if(!component[n]) {
																		if((1 == 1)) {
																			if(!component[n])
																				// Set the flags to false
																				guard$sample20if124[((var19 - 0) / 1)] = false;
																		}
																	}
																}
															}
														}
													}
												}
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 0)) {
															if(!component[n]) {
																if((0 == 1)) {
																	if(!component[n]) {
																		if(!guard$sample20if124[((var19 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample20if124[((var19 - 0) / 1)] = true;
																			
																			// Processing sample task 20 of consumer random variable null.
																			{
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
																									// Constructing a random variable input for use later.
																									double var6 = (2.0 * 2.0);
																									
																									// Record the probability of sample task 20 generating output with current configuration.
																									if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										// If the second value is -infinity.
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
																									}
																									
																									// Recorded the probability of reaching sample task 20 with the current configuration.
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 1)) {
															if(!component[n]) {
																if((0 == 1)) {
																	if(!component[n]) {
																		if(!guard$sample20if124[((var19 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample20if124[((var19 - 0) / 1)] = true;
																			
																			// Processing sample task 20 of consumer random variable null.
																			{
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
																									// Constructing a random variable input for use later.
																									double var6 = (2.0 * 2.0);
																									
																									// Record the probability of sample task 20 generating output with current configuration.
																									if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										// If the second value is -infinity.
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
																									}
																									
																									// Recorded the probability of reaching sample task 20 with the current configuration.
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((rawMu[0] < rawMu[1])) {
															if((var19 == 0)) {
																if((rawMu[0] < rawMu[1])) {
																	if(!component[n]) {
																		if((0 == 1)) {
																			if(!component[n]) {
																				if(!guard$sample20if124[((var19 - 0) / 1)]) {
																					// The body will execute, so should not be executed again
																					guard$sample20if124[((var19 - 0) / 1)] = true;
																					
																					// Processing sample task 20 of consumer random variable null.
																					{
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
																											// Constructing a random variable input for use later.
																											double var6 = (2.0 * 2.0);
																											
																											// Record the probability of sample task 20 generating output with current configuration.
																											if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												// If the second value is -infinity.
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
																											}
																											
																											// Recorded the probability of reaching sample task 20 with the current configuration.
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if(!(rawMu[0] < rawMu[1])) {
															if((var19 == 1)) {
																if(!(rawMu[0] < rawMu[1])) {
																	if(!component[n]) {
																		if((0 == 1)) {
																			if(!component[n]) {
																				if(!guard$sample20if124[((var19 - 0) / 1)]) {
																					// The body will execute, so should not be executed again
																					guard$sample20if124[((var19 - 0) / 1)] = true;
																					
																					// Processing sample task 20 of consumer random variable null.
																					{
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
																											// Constructing a random variable input for use later.
																											double var6 = (2.0 * 2.0);
																											
																											// Record the probability of sample task 20 generating output with current configuration.
																											if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												// If the second value is -infinity.
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
																											}
																											
																											// Recorded the probability of reaching sample task 20 with the current configuration.
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 0)) {
															if(!component[n]) {
																if((1 == 1)) {
																	if(!component[n]) {
																		if(!guard$sample20if124[((var19 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample20if124[((var19 - 0) / 1)] = true;
																			
																			// Processing sample task 20 of consumer random variable null.
																			{
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
																									// Constructing a random variable input for use later.
																									double var6 = (2.0 * 2.0);
																									
																									// Record the probability of sample task 20 generating output with current configuration.
																									if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										// If the second value is -infinity.
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
																									}
																									
																									// Recorded the probability of reaching sample task 20 with the current configuration.
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((var19 == 1)) {
															if(!component[n]) {
																if((1 == 1)) {
																	if(!component[n]) {
																		if(!guard$sample20if124[((var19 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample20if124[((var19 - 0) / 1)] = true;
																			
																			// Processing sample task 20 of consumer random variable null.
																			{
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
																									// Constructing a random variable input for use later.
																									double var6 = (2.0 * 2.0);
																									
																									// Record the probability of sample task 20 generating output with current configuration.
																									if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										// If the second value is -infinity.
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
																									}
																									
																									// Recorded the probability of reaching sample task 20 with the current configuration.
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if((rawMu[0] < rawMu[1])) {
															if((var19 == 1)) {
																if((rawMu[0] < rawMu[1])) {
																	if(!component[n]) {
																		if((1 == 1)) {
																			if(!component[n]) {
																				if(!guard$sample20if124[((var19 - 0) / 1)]) {
																					// The body will execute, so should not be executed again
																					guard$sample20if124[((var19 - 0) / 1)] = true;
																					
																					// Processing sample task 20 of consumer random variable null.
																					{
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
																											// Constructing a random variable input for use later.
																											double var6 = (2.0 * 2.0);
																											
																											// Record the probability of sample task 20 generating output with current configuration.
																											if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												// If the second value is -infinity.
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
																											}
																											
																											// Recorded the probability of reaching sample task 20 with the current configuration.
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
												{
													for(int var19 = 0; var19 < 2; var19 += 1) {
														if(!(rawMu[0] < rawMu[1])) {
															if((var19 == 0)) {
																if(!(rawMu[0] < rawMu[1])) {
																	if(!component[n]) {
																		if((1 == 1)) {
																			if(!component[n]) {
																				if(!guard$sample20if124[((var19 - 0) / 1)]) {
																					// The body will execute, so should not be executed again
																					guard$sample20if124[((var19 - 0) / 1)] = true;
																					
																					// Processing sample task 20 of consumer random variable null.
																					{
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
																											// Constructing a random variable input for use later.
																											double var6 = (2.0 * 2.0);
																											
																											// Record the probability of sample task 20 generating output with current configuration.
																											if(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												// If the second value is -infinity.
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((rawMu[var19] - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY)));
																											}
																											
																											// Recorded the probability of reaching sample task 20 with the current configuration.
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
											}
										}
									}
								}
							}
						}
					}
					
					// Processing conditional point135.
					{
						// Looking for a path between Sample 101 and consumer double 127.
						{
							{
								for(int n = 0; n < N; n += 1) {
									if((var96 == n)) {
										{
											{
												{
													if(component[n]) {
														double traceTempVariable$componentSigma$58_1 = sigma[0];
														
														// Processing sample task 138 of consumer random variable null.
														{
															{
																// Flag recording if this sample task of the consuming random variable is constrained.
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	// Mark that the sample has observed constrained data.
																	constrainedFlag$sample101[((var96 - 0) / 1)] = true;
																	
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
																						{
																							double componentMu;
																							if(component[n])
																								componentMu = mu[0];
																							else
																								componentMu = mu[1];
																							
																							// Constructing a random variable input for use later.
																							double var128 = (traceTempVariable$componentSigma$58_1 * traceTempVariable$componentSigma$58_1);
																							
																							// Record the probability of sample task 138 generating output with current configuration.
																							if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																							}
																							
																							// Recorded the probability of reaching sample task 138 with the current configuration.
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
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
											
											// Looking for a path between Sample 83 and consumer double 127.
											{
												{
													for(int var78 = 0; var78 < 2; var78 += 1) {
														if(component[n]) {
															if((var78 == 0)) {
																if(component[n]) {
																	// Processing sample task 83 of consumer random variable null.
																	{
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
																							// Constructing a random variable input for use later.
																							double var63 = (2.0 * 2.0);
																							
																							// Record the probability of sample task 83 generating output with current configuration.
																							if(((Math.log(1.0) + (((((0.0 <= sigma[var78]) && (sigma[var78] <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((sigma[var78] - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((((0.0 <= sigma[var78]) && (sigma[var78] <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((sigma[var78] - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((((0.0 <= sigma[var78]) && (sigma[var78] <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((sigma[var78] - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((((0.0 <= sigma[var78]) && (sigma[var78] <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((sigma[var78] - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((((0.0 <= sigma[var78]) && (sigma[var78] <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((sigma[var78] - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY)));
																							}
																							
																							// Recorded the probability of reaching sample task 83 with the current configuration.
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
											{
												{
													if(!component[n]) {
														double traceTempVariable$componentSigma$63_1 = sigma[1];
														
														// Processing sample task 138 of consumer random variable null.
														{
															{
																// Flag recording if this sample task of the consuming random variable is constrained.
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	// Mark that the sample has observed constrained data.
																	constrainedFlag$sample101[((var96 - 0) / 1)] = true;
																	
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
																						{
																							double componentMu;
																							if(component[n])
																								componentMu = mu[0];
																							else
																								componentMu = mu[1];
																							
																							// Constructing a random variable input for use later.
																							double var128 = (traceTempVariable$componentSigma$63_1 * traceTempVariable$componentSigma$63_1);
																							
																							// Record the probability of sample task 138 generating output with current configuration.
																							if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																							}
																							
																							// Recorded the probability of reaching sample task 138 with the current configuration.
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
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
											
											// Looking for a path between Sample 83 and consumer double 127.
											{
												{
													for(int var78 = 0; var78 < 2; var78 += 1) {
														if(!component[n]) {
															if((var78 == 1)) {
																if(!component[n]) {
																	// Processing sample task 83 of consumer random variable null.
																	{
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
																							// Constructing a random variable input for use later.
																							double var63 = (2.0 * 2.0);
																							
																							// Record the probability of sample task 83 generating output with current configuration.
																							if(((Math.log(1.0) + (((((0.0 <= sigma[var78]) && (sigma[var78] <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((sigma[var78] - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((((0.0 <= sigma[var78]) && (sigma[var78] <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((sigma[var78] - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((((0.0 <= sigma[var78]) && (sigma[var78] <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((sigma[var78] - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((((0.0 <= sigma[var78]) && (sigma[var78] <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((sigma[var78] - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((((0.0 <= sigma[var78]) && (sigma[var78] <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((sigma[var78] - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY)));
																							}
																							
																							// Recorded the probability of reaching sample task 83 with the current configuration.
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
			if(constrainedFlag$sample101[((var96 - 0) / 1)]) {
				// The sum of all the probabilities in log space
				double cv$logSum = 0.0;
				
				// Sum all the values
				{
					// Initialize the max to the first element.
					double cv$lseMax = cv$stateProbabilityLocal[0];
					
					// Find max value.
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					
					// If the maximum value is -infinity return -infinity.
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					
					// Sum the values in the array.
					else {
						// Initialize the sum of the array elements
						double cv$lseSum = 0.0;
						
						// Offset values, move to normal space, and sum.
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						
						// Increment the value of the target, moving the value back into log space.
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				
				// If all the sum is zero, just share the probability evenly.
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				
				// Set array values that are not computed for the input to negative infinity.
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				boolean var97 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
				
				// Guards to ensure that component is only updated when there is a valid path.
				{
					{
						{
							component[var96] = var97;
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 20 drawn from Gaussian 7. Inference was performed using Metropolis-Hastings.
	private final void inferSample20(int var19) {
		if(true) {
			constrainedFlag$sample20[((var19 - 0) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// Metropolis-Hastings
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// The original value of the sample
			double cv$originalValue = rawMu[var19];
			
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
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((constrainedFlag$sample20[((var19 - 0) / 1)] || (cv$valuePos == 0))) {
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
						double var20 = cv$proposedValue;
						
						// Guards to ensure that rawMu is only updated when there is a valid path.
						{
							{
								{
									rawMu[var19] = cv$currentValue;
								}
							}
						}
						
						// Guards to ensure that mu is only updated when there is a valid path.
						// 
						// Looking for a path between Sample 20 and consumer double[] 41.
						{
							// Guard to check that at most one copy of the code is executed for a given set of
							// loop iterations.
							boolean guard$sample20put43 = false;
							{
								if((var19 == 0)) {
									if(!guard$sample20put43) {
										// The body will execute, so should not be executed again
										guard$sample20put43 = true;
										{
											double var39;
											if((rawMu[0] < rawMu[1]))
												var39 = rawMu[0];
											else
												var39 = rawMu[1];
											mu[0] = var39;
										}
									}
								}
							}
							{
								if((var19 == 1)) {
									if(!guard$sample20put43) {
										// The body will execute, so should not be executed again
										guard$sample20put43 = true;
										{
											double var39;
											if((rawMu[0] < rawMu[1]))
												var39 = rawMu[0];
											else
												var39 = rawMu[1];
											mu[0] = var39;
										}
									}
								}
							}
							{
								if((rawMu[0] < rawMu[1])) {
									if((var19 == 0)) {
										if((rawMu[0] < rawMu[1])) {
											if(!guard$sample20put43) {
												// The body will execute, so should not be executed again
												guard$sample20put43 = true;
												{
													double var39;
													if((rawMu[0] < rawMu[1]))
														var39 = rawMu[0];
													else
														var39 = rawMu[1];
													mu[0] = var39;
												}
											}
										}
									}
								}
							}
							{
								if(!(rawMu[0] < rawMu[1])) {
									if((var19 == 1)) {
										if(!(rawMu[0] < rawMu[1])) {
											if(!guard$sample20put43) {
												// The body will execute, so should not be executed again
												guard$sample20put43 = true;
												{
													double var39;
													if((rawMu[0] < rawMu[1]))
														var39 = rawMu[0];
													else
														var39 = rawMu[1];
													mu[0] = var39;
												}
											}
										}
									}
								}
							}
						}
						
						// Guards to ensure that mu is only updated when there is a valid path.
						// 
						// Looking for a path between Sample 20 and consumer double[] 59.
						{
							// Guard to check that at most one copy of the code is executed for a given set of
							// loop iterations.
							boolean guard$sample20put63 = false;
							{
								if((var19 == 0)) {
									if(!guard$sample20put63) {
										// The body will execute, so should not be executed again
										guard$sample20put63 = true;
										{
											double var57;
											if((rawMu[0] < rawMu[1]))
												var57 = rawMu[1];
											else
												var57 = rawMu[0];
											mu[1] = var57;
										}
									}
								}
							}
							{
								if((var19 == 1)) {
									if(!guard$sample20put63) {
										// The body will execute, so should not be executed again
										guard$sample20put63 = true;
										{
											double var57;
											if((rawMu[0] < rawMu[1]))
												var57 = rawMu[1];
											else
												var57 = rawMu[0];
											mu[1] = var57;
										}
									}
								}
							}
							{
								if((rawMu[0] < rawMu[1])) {
									if((var19 == 1)) {
										if((rawMu[0] < rawMu[1])) {
											if(!guard$sample20put63) {
												// The body will execute, so should not be executed again
												guard$sample20put63 = true;
												{
													double var57;
													if((rawMu[0] < rawMu[1]))
														var57 = rawMu[1];
													else
														var57 = rawMu[0];
													mu[1] = var57;
												}
											}
										}
									}
								}
							}
							{
								if(!(rawMu[0] < rawMu[1])) {
									if((var19 == 0)) {
										if(!(rawMu[0] < rawMu[1])) {
											if(!guard$sample20put63) {
												// The body will execute, so should not be executed again
												guard$sample20put63 = true;
												{
													double var57;
													if((rawMu[0] < rawMu[1]))
														var57 = rawMu[1];
													else
														var57 = rawMu[0];
													mu[1] = var57;
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
						
						// Constructing a random variable input for use later.
						double var6 = (2.0 * 2.0);
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						double cv$accumulatedProbabilities = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((cv$currentValue - 0.0) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
						
						// Processing random variable 129.
						{
							// Looking for a path between Sample 20 and consumer Gaussian 129.
							{
								{
									double traceTempVariable$var36$10_1 = cv$currentValue;
									if((rawMu[0] < rawMu[1])) {
										if((var19 == 0)) {
											if((rawMu[0] < rawMu[1])) {
												double traceTempVariable$var39$10_2 = traceTempVariable$var36$10_1;
												double traceTempVariable$var115$10_3 = traceTempVariable$var39$10_2;
												for(int n = 0; n < N; n += 1) {
													if(component[n]) {
														if((0 == 0)) {
															if(component[n]) {
																double traceTempVariable$componentMu$10_5 = traceTempVariable$var115$10_3;
																
																// Processing sample task 138 of consumer random variable null.
																{
																	{
																		// Flag recording if this sample task of the consuming random variable is constrained.
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			// Mark that the sample has observed constrained data.
																			constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																			
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
																								double componentSigma;
																								if(component[n])
																									componentSigma = sigma[0];
																								else
																									componentSigma = sigma[1];
																								
																								// Constructing a random variable input for use later.
																								double var128 = (componentSigma * componentSigma);
																								
																								// Record the probability of sample task 138 generating output with current configuration.
																								if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$10_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$10_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$10_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$10_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$10_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 138 with the current configuration.
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
								{
									double traceTempVariable$var36$11_1 = cv$currentValue;
									if((rawMu[0] < rawMu[1])) {
										if((var19 == 0)) {
											if((rawMu[0] < rawMu[1])) {
												double traceTempVariable$var39$11_2 = traceTempVariable$var36$11_1;
												double traceTempVariable$var117$11_3 = traceTempVariable$var39$11_2;
												for(int n = 0; n < N; n += 1) {
													if(!component[n]) {
														if((0 == 1)) {
															if(!component[n]) {
																double traceTempVariable$componentMu$11_5 = traceTempVariable$var117$11_3;
																
																// Processing sample task 138 of consumer random variable null.
																{
																	{
																		// Flag recording if this sample task of the consuming random variable is constrained.
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			// Mark that the sample has observed constrained data.
																			constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																			
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
																								double componentSigma;
																								if(component[n])
																									componentSigma = sigma[0];
																								else
																									componentSigma = sigma[1];
																								
																								// Constructing a random variable input for use later.
																								double var128 = (componentSigma * componentSigma);
																								
																								// Record the probability of sample task 138 generating output with current configuration.
																								if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$11_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$11_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$11_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$11_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$11_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 138 with the current configuration.
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
								{
									double traceTempVariable$var38$12_1 = cv$currentValue;
									if(!(rawMu[0] < rawMu[1])) {
										if((var19 == 1)) {
											if(!(rawMu[0] < rawMu[1])) {
												double traceTempVariable$var39$12_2 = traceTempVariable$var38$12_1;
												double traceTempVariable$var115$12_3 = traceTempVariable$var39$12_2;
												for(int n = 0; n < N; n += 1) {
													if(component[n]) {
														if((0 == 0)) {
															if(component[n]) {
																double traceTempVariable$componentMu$12_5 = traceTempVariable$var115$12_3;
																
																// Processing sample task 138 of consumer random variable null.
																{
																	{
																		// Flag recording if this sample task of the consuming random variable is constrained.
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			// Mark that the sample has observed constrained data.
																			constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																			
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
																								double componentSigma;
																								if(component[n])
																									componentSigma = sigma[0];
																								else
																									componentSigma = sigma[1];
																								
																								// Constructing a random variable input for use later.
																								double var128 = (componentSigma * componentSigma);
																								
																								// Record the probability of sample task 138 generating output with current configuration.
																								if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$12_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$12_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$12_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$12_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$12_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 138 with the current configuration.
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
								{
									double traceTempVariable$var38$13_1 = cv$currentValue;
									if(!(rawMu[0] < rawMu[1])) {
										if((var19 == 1)) {
											if(!(rawMu[0] < rawMu[1])) {
												double traceTempVariable$var39$13_2 = traceTempVariable$var38$13_1;
												double traceTempVariable$var117$13_3 = traceTempVariable$var39$13_2;
												for(int n = 0; n < N; n += 1) {
													if(!component[n]) {
														if((0 == 1)) {
															if(!component[n]) {
																double traceTempVariable$componentMu$13_5 = traceTempVariable$var117$13_3;
																
																// Processing sample task 138 of consumer random variable null.
																{
																	{
																		// Flag recording if this sample task of the consuming random variable is constrained.
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			// Mark that the sample has observed constrained data.
																			constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																			
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
																								double componentSigma;
																								if(component[n])
																									componentSigma = sigma[0];
																								else
																									componentSigma = sigma[1];
																								
																								// Constructing a random variable input for use later.
																								double var128 = (componentSigma * componentSigma);
																								
																								// Record the probability of sample task 138 generating output with current configuration.
																								if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$13_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$13_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$13_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$13_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$13_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 138 with the current configuration.
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
								{
									double traceTempVariable$var54$14_1 = cv$currentValue;
									if((rawMu[0] < rawMu[1])) {
										if((var19 == 1)) {
											if((rawMu[0] < rawMu[1])) {
												double traceTempVariable$var57$14_2 = traceTempVariable$var54$14_1;
												double traceTempVariable$var115$14_3 = traceTempVariable$var57$14_2;
												for(int n = 0; n < N; n += 1) {
													if(component[n]) {
														if((1 == 0)) {
															if(component[n]) {
																double traceTempVariable$componentMu$14_5 = traceTempVariable$var115$14_3;
																
																// Processing sample task 138 of consumer random variable null.
																{
																	{
																		// Flag recording if this sample task of the consuming random variable is constrained.
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			// Mark that the sample has observed constrained data.
																			constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																			
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
																								double componentSigma;
																								if(component[n])
																									componentSigma = sigma[0];
																								else
																									componentSigma = sigma[1];
																								
																								// Constructing a random variable input for use later.
																								double var128 = (componentSigma * componentSigma);
																								
																								// Record the probability of sample task 138 generating output with current configuration.
																								if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$14_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$14_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$14_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$14_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$14_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 138 with the current configuration.
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
								{
									double traceTempVariable$var54$15_1 = cv$currentValue;
									if((rawMu[0] < rawMu[1])) {
										if((var19 == 1)) {
											if((rawMu[0] < rawMu[1])) {
												double traceTempVariable$var57$15_2 = traceTempVariable$var54$15_1;
												double traceTempVariable$var117$15_3 = traceTempVariable$var57$15_2;
												for(int n = 0; n < N; n += 1) {
													if(!component[n]) {
														if((1 == 1)) {
															if(!component[n]) {
																double traceTempVariable$componentMu$15_5 = traceTempVariable$var117$15_3;
																
																// Processing sample task 138 of consumer random variable null.
																{
																	{
																		// Flag recording if this sample task of the consuming random variable is constrained.
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			// Mark that the sample has observed constrained data.
																			constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																			
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
																								double componentSigma;
																								if(component[n])
																									componentSigma = sigma[0];
																								else
																									componentSigma = sigma[1];
																								
																								// Constructing a random variable input for use later.
																								double var128 = (componentSigma * componentSigma);
																								
																								// Record the probability of sample task 138 generating output with current configuration.
																								if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$15_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$15_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$15_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$15_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$15_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 138 with the current configuration.
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
								{
									double traceTempVariable$var56$16_1 = cv$currentValue;
									if(!(rawMu[0] < rawMu[1])) {
										if((var19 == 0)) {
											if(!(rawMu[0] < rawMu[1])) {
												double traceTempVariable$var57$16_2 = traceTempVariable$var56$16_1;
												double traceTempVariable$var115$16_3 = traceTempVariable$var57$16_2;
												for(int n = 0; n < N; n += 1) {
													if(component[n]) {
														if((1 == 0)) {
															if(component[n]) {
																double traceTempVariable$componentMu$16_5 = traceTempVariable$var115$16_3;
																
																// Processing sample task 138 of consumer random variable null.
																{
																	{
																		// Flag recording if this sample task of the consuming random variable is constrained.
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			// Mark that the sample has observed constrained data.
																			constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																			
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
																								double componentSigma;
																								if(component[n])
																									componentSigma = sigma[0];
																								else
																									componentSigma = sigma[1];
																								
																								// Constructing a random variable input for use later.
																								double var128 = (componentSigma * componentSigma);
																								
																								// Record the probability of sample task 138 generating output with current configuration.
																								if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$16_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$16_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$16_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$16_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$16_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 138 with the current configuration.
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
								{
									double traceTempVariable$var56$17_1 = cv$currentValue;
									if(!(rawMu[0] < rawMu[1])) {
										if((var19 == 0)) {
											if(!(rawMu[0] < rawMu[1])) {
												double traceTempVariable$var57$17_2 = traceTempVariable$var56$17_1;
												double traceTempVariable$var117$17_3 = traceTempVariable$var57$17_2;
												for(int n = 0; n < N; n += 1) {
													if(!component[n]) {
														if((1 == 1)) {
															if(!component[n]) {
																double traceTempVariable$componentMu$17_5 = traceTempVariable$var117$17_3;
																
																// Processing sample task 138 of consumer random variable null.
																{
																	{
																		// Flag recording if this sample task of the consuming random variable is constrained.
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			// Mark that the sample has observed constrained data.
																			constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																			
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
																								double componentSigma;
																								if(component[n])
																									componentSigma = sigma[0];
																								else
																									componentSigma = sigma[1];
																								
																								// Constructing a random variable input for use later.
																								double var128 = (componentSigma * componentSigma);
																								
																								// Record the probability of sample task 138 generating output with current configuration.
																								if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$17_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$17_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$17_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$17_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$17_5) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 138 with the current configuration.
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
						
						// Processing conditional point41.
						{
							// Looking for a path between Sample 20 and consumer double 39.
							{
								// Guard to check that at most one copy of the code is executed for a given set of
								// loop iterations.
								boolean guard$sample20if41 = false;
								{
									if((var19 == 0)) {
										if(!guard$sample20if41) {
											// The body will execute, so should not be executed again
											guard$sample20if41 = true;
											{
												// Looking for a path between If 41 and consumer Gaussian 129.
												{
													{
														if((rawMu[0] < rawMu[1])) {
															double traceTempVariable$var39$36_1 = rawMu[0];
															double traceTempVariable$var115$36_2 = traceTempVariable$var39$36_1;
															for(int n = 0; n < N; n += 1) {
																if(component[n]) {
																	if((0 == 0)) {
																		if(component[n]) {
																			double traceTempVariable$componentMu$36_4 = traceTempVariable$var115$36_2;
																			
																			// Processing sample task 138 of consumer random variable null.
																			{
																				{
																					// Flag recording if this sample task of the consuming random variable is constrained.
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						// Mark that the sample has observed constrained data.
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						
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
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												
																												// Constructing a random variable input for use later.
																												double var128 = (componentSigma * componentSigma);
																												
																												// Record the probability of sample task 138 generating output with current configuration.
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$36_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$36_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$36_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$36_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$36_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																												}
																												
																												// Recorded the probability of reaching sample task 138 with the current configuration.
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
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
													{
														if((rawMu[0] < rawMu[1])) {
															double traceTempVariable$var39$38_1 = rawMu[0];
															double traceTempVariable$var117$38_2 = traceTempVariable$var39$38_1;
															for(int n = 0; n < N; n += 1) {
																if(!component[n]) {
																	if((0 == 1)) {
																		if(!component[n]) {
																			double traceTempVariable$componentMu$38_4 = traceTempVariable$var117$38_2;
																			
																			// Processing sample task 138 of consumer random variable null.
																			{
																				{
																					// Flag recording if this sample task of the consuming random variable is constrained.
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						// Mark that the sample has observed constrained data.
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						
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
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												
																												// Constructing a random variable input for use later.
																												double var128 = (componentSigma * componentSigma);
																												
																												// Record the probability of sample task 138 generating output with current configuration.
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$38_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$38_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$38_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$38_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$38_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																												}
																												
																												// Recorded the probability of reaching sample task 138 with the current configuration.
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
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
												
												// Looking for a path between Sample 20 and consumer double 39.
												{
													{
														for(int index$var19$48_1 = 0; index$var19$48_1 < 2; index$var19$48_1 += 1) {
															if((rawMu[0] < rawMu[1])) {
																if((index$var19$48_1 == 0)) {
																	if((rawMu[0] < rawMu[1])) {
																		// Processing sample task 20 of consumer random variable null.
																		{
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
																								// Record the probability of sample task 20 generating output with current configuration.
																								if(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$48_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$48_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$48_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$48_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$48_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 20 with the current configuration.
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
												
												// Looking for a path between If 41 and consumer Gaussian 129.
												{
													{
														if(!(rawMu[0] < rawMu[1])) {
															double traceTempVariable$var39$52_1 = rawMu[1];
															double traceTempVariable$var115$52_2 = traceTempVariable$var39$52_1;
															for(int n = 0; n < N; n += 1) {
																if(component[n]) {
																	if((0 == 0)) {
																		if(component[n]) {
																			double traceTempVariable$componentMu$52_4 = traceTempVariable$var115$52_2;
																			
																			// Processing sample task 138 of consumer random variable null.
																			{
																				{
																					// Flag recording if this sample task of the consuming random variable is constrained.
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						// Mark that the sample has observed constrained data.
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						
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
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												
																												// Constructing a random variable input for use later.
																												double var128 = (componentSigma * componentSigma);
																												
																												// Record the probability of sample task 138 generating output with current configuration.
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$52_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$52_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$52_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$52_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$52_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																												}
																												
																												// Recorded the probability of reaching sample task 138 with the current configuration.
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
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
													{
														if(!(rawMu[0] < rawMu[1])) {
															double traceTempVariable$var39$54_1 = rawMu[1];
															double traceTempVariable$var117$54_2 = traceTempVariable$var39$54_1;
															for(int n = 0; n < N; n += 1) {
																if(!component[n]) {
																	if((0 == 1)) {
																		if(!component[n]) {
																			double traceTempVariable$componentMu$54_4 = traceTempVariable$var117$54_2;
																			
																			// Processing sample task 138 of consumer random variable null.
																			{
																				{
																					// Flag recording if this sample task of the consuming random variable is constrained.
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						// Mark that the sample has observed constrained data.
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						
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
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												
																												// Constructing a random variable input for use later.
																												double var128 = (componentSigma * componentSigma);
																												
																												// Record the probability of sample task 138 generating output with current configuration.
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$54_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$54_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$54_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$54_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$54_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																												}
																												
																												// Recorded the probability of reaching sample task 138 with the current configuration.
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
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
												
												// Looking for a path between Sample 20 and consumer double 39.
												{
													{
														for(int index$var19$64_1 = 0; index$var19$64_1 < 2; index$var19$64_1 += 1) {
															if(!(rawMu[0] < rawMu[1])) {
																if((index$var19$64_1 == 1)) {
																	if(!(rawMu[0] < rawMu[1])) {
																		// Processing sample task 20 of consumer random variable null.
																		{
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
																								// Record the probability of sample task 20 generating output with current configuration.
																								if(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$64_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$64_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$64_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$64_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$64_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 20 with the current configuration.
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
								}
								{
									if((var19 == 1)) {
										if(!guard$sample20if41) {
											// The body will execute, so should not be executed again
											guard$sample20if41 = true;
											{
												// Looking for a path between If 41 and consumer Gaussian 129.
												{
													{
														if((rawMu[0] < rawMu[1])) {
															double traceTempVariable$var39$37_1 = rawMu[0];
															double traceTempVariable$var115$37_2 = traceTempVariable$var39$37_1;
															for(int n = 0; n < N; n += 1) {
																if(component[n]) {
																	if((0 == 0)) {
																		if(component[n]) {
																			double traceTempVariable$componentMu$37_4 = traceTempVariable$var115$37_2;
																			
																			// Processing sample task 138 of consumer random variable null.
																			{
																				{
																					// Flag recording if this sample task of the consuming random variable is constrained.
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						// Mark that the sample has observed constrained data.
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						
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
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												
																												// Constructing a random variable input for use later.
																												double var128 = (componentSigma * componentSigma);
																												
																												// Record the probability of sample task 138 generating output with current configuration.
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$37_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$37_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$37_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$37_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$37_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																												}
																												
																												// Recorded the probability of reaching sample task 138 with the current configuration.
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
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
													{
														if((rawMu[0] < rawMu[1])) {
															double traceTempVariable$var39$39_1 = rawMu[0];
															double traceTempVariable$var117$39_2 = traceTempVariable$var39$39_1;
															for(int n = 0; n < N; n += 1) {
																if(!component[n]) {
																	if((0 == 1)) {
																		if(!component[n]) {
																			double traceTempVariable$componentMu$39_4 = traceTempVariable$var117$39_2;
																			
																			// Processing sample task 138 of consumer random variable null.
																			{
																				{
																					// Flag recording if this sample task of the consuming random variable is constrained.
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						// Mark that the sample has observed constrained data.
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						
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
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												
																												// Constructing a random variable input for use later.
																												double var128 = (componentSigma * componentSigma);
																												
																												// Record the probability of sample task 138 generating output with current configuration.
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$39_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$39_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$39_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$39_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$39_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																												}
																												
																												// Recorded the probability of reaching sample task 138 with the current configuration.
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
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
												
												// Looking for a path between Sample 20 and consumer double 39.
												{
													{
														for(int index$var19$49_1 = 0; index$var19$49_1 < 2; index$var19$49_1 += 1) {
															if((rawMu[0] < rawMu[1])) {
																if((index$var19$49_1 == 0)) {
																	if((rawMu[0] < rawMu[1])) {
																		// Processing sample task 20 of consumer random variable null.
																		{
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
																								// Record the probability of sample task 20 generating output with current configuration.
																								if(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$49_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$49_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$49_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$49_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$49_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 20 with the current configuration.
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
												
												// Looking for a path between If 41 and consumer Gaussian 129.
												{
													{
														if(!(rawMu[0] < rawMu[1])) {
															double traceTempVariable$var39$53_1 = rawMu[1];
															double traceTempVariable$var115$53_2 = traceTempVariable$var39$53_1;
															for(int n = 0; n < N; n += 1) {
																if(component[n]) {
																	if((0 == 0)) {
																		if(component[n]) {
																			double traceTempVariable$componentMu$53_4 = traceTempVariable$var115$53_2;
																			
																			// Processing sample task 138 of consumer random variable null.
																			{
																				{
																					// Flag recording if this sample task of the consuming random variable is constrained.
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						// Mark that the sample has observed constrained data.
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						
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
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												
																												// Constructing a random variable input for use later.
																												double var128 = (componentSigma * componentSigma);
																												
																												// Record the probability of sample task 138 generating output with current configuration.
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$53_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$53_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$53_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$53_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$53_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																												}
																												
																												// Recorded the probability of reaching sample task 138 with the current configuration.
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
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
													{
														if(!(rawMu[0] < rawMu[1])) {
															double traceTempVariable$var39$55_1 = rawMu[1];
															double traceTempVariable$var117$55_2 = traceTempVariable$var39$55_1;
															for(int n = 0; n < N; n += 1) {
																if(!component[n]) {
																	if((0 == 1)) {
																		if(!component[n]) {
																			double traceTempVariable$componentMu$55_4 = traceTempVariable$var117$55_2;
																			
																			// Processing sample task 138 of consumer random variable null.
																			{
																				{
																					// Flag recording if this sample task of the consuming random variable is constrained.
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						// Mark that the sample has observed constrained data.
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						
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
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												
																												// Constructing a random variable input for use later.
																												double var128 = (componentSigma * componentSigma);
																												
																												// Record the probability of sample task 138 generating output with current configuration.
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$55_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$55_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$55_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$55_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$55_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																												}
																												
																												// Recorded the probability of reaching sample task 138 with the current configuration.
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
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
												
												// Looking for a path between Sample 20 and consumer double 39.
												{
													{
														for(int index$var19$65_1 = 0; index$var19$65_1 < 2; index$var19$65_1 += 1) {
															if(!(rawMu[0] < rawMu[1])) {
																if((index$var19$65_1 == 1)) {
																	if(!(rawMu[0] < rawMu[1])) {
																		// Processing sample task 20 of consumer random variable null.
																		{
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
																								// Record the probability of sample task 20 generating output with current configuration.
																								if(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$65_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$65_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$65_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$65_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$65_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 20 with the current configuration.
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
								}
							}
						}
						
						// Processing conditional point61.
						{
							// Looking for a path between Sample 20 and consumer double 57.
							{
								// Guard to check that at most one copy of the code is executed for a given set of
								// loop iterations.
								boolean guard$sample20if61 = false;
								{
									if((var19 == 0)) {
										if(!guard$sample20if61) {
											// The body will execute, so should not be executed again
											guard$sample20if61 = true;
											{
												// Looking for a path between If 61 and consumer Gaussian 129.
												{
													{
														if((rawMu[0] < rawMu[1])) {
															double traceTempVariable$var57$70_1 = rawMu[1];
															double traceTempVariable$var115$70_2 = traceTempVariable$var57$70_1;
															for(int n = 0; n < N; n += 1) {
																if(component[n]) {
																	if((1 == 0)) {
																		if(component[n]) {
																			double traceTempVariable$componentMu$70_4 = traceTempVariable$var115$70_2;
																			
																			// Processing sample task 138 of consumer random variable null.
																			{
																				{
																					// Flag recording if this sample task of the consuming random variable is constrained.
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						// Mark that the sample has observed constrained data.
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						
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
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												
																												// Constructing a random variable input for use later.
																												double var128 = (componentSigma * componentSigma);
																												
																												// Record the probability of sample task 138 generating output with current configuration.
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$70_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$70_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$70_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$70_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$70_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																												}
																												
																												// Recorded the probability of reaching sample task 138 with the current configuration.
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
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
													{
														if((rawMu[0] < rawMu[1])) {
															double traceTempVariable$var57$72_1 = rawMu[1];
															double traceTempVariable$var117$72_2 = traceTempVariable$var57$72_1;
															for(int n = 0; n < N; n += 1) {
																if(!component[n]) {
																	if((1 == 1)) {
																		if(!component[n]) {
																			double traceTempVariable$componentMu$72_4 = traceTempVariable$var117$72_2;
																			
																			// Processing sample task 138 of consumer random variable null.
																			{
																				{
																					// Flag recording if this sample task of the consuming random variable is constrained.
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						// Mark that the sample has observed constrained data.
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						
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
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												
																												// Constructing a random variable input for use later.
																												double var128 = (componentSigma * componentSigma);
																												
																												// Record the probability of sample task 138 generating output with current configuration.
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$72_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$72_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$72_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$72_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$72_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																												}
																												
																												// Recorded the probability of reaching sample task 138 with the current configuration.
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
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
												
												// Looking for a path between Sample 20 and consumer double 57.
												{
													{
														for(int index$var19$82_1 = 0; index$var19$82_1 < 2; index$var19$82_1 += 1) {
															if((rawMu[0] < rawMu[1])) {
																if((index$var19$82_1 == 1)) {
																	if((rawMu[0] < rawMu[1])) {
																		// Processing sample task 20 of consumer random variable null.
																		{
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
																								// Record the probability of sample task 20 generating output with current configuration.
																								if(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$82_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$82_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$82_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$82_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$82_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 20 with the current configuration.
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
												
												// Looking for a path between If 61 and consumer Gaussian 129.
												{
													{
														if(!(rawMu[0] < rawMu[1])) {
															double traceTempVariable$var57$86_1 = rawMu[0];
															double traceTempVariable$var115$86_2 = traceTempVariable$var57$86_1;
															for(int n = 0; n < N; n += 1) {
																if(component[n]) {
																	if((1 == 0)) {
																		if(component[n]) {
																			double traceTempVariable$componentMu$86_4 = traceTempVariable$var115$86_2;
																			
																			// Processing sample task 138 of consumer random variable null.
																			{
																				{
																					// Flag recording if this sample task of the consuming random variable is constrained.
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						// Mark that the sample has observed constrained data.
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						
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
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												
																												// Constructing a random variable input for use later.
																												double var128 = (componentSigma * componentSigma);
																												
																												// Record the probability of sample task 138 generating output with current configuration.
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$86_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$86_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$86_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$86_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$86_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																												}
																												
																												// Recorded the probability of reaching sample task 138 with the current configuration.
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
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
													{
														if(!(rawMu[0] < rawMu[1])) {
															double traceTempVariable$var57$88_1 = rawMu[0];
															double traceTempVariable$var117$88_2 = traceTempVariable$var57$88_1;
															for(int n = 0; n < N; n += 1) {
																if(!component[n]) {
																	if((1 == 1)) {
																		if(!component[n]) {
																			double traceTempVariable$componentMu$88_4 = traceTempVariable$var117$88_2;
																			
																			// Processing sample task 138 of consumer random variable null.
																			{
																				{
																					// Flag recording if this sample task of the consuming random variable is constrained.
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						// Mark that the sample has observed constrained data.
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						
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
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												
																												// Constructing a random variable input for use later.
																												double var128 = (componentSigma * componentSigma);
																												
																												// Record the probability of sample task 138 generating output with current configuration.
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$88_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$88_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$88_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$88_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$88_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																												}
																												
																												// Recorded the probability of reaching sample task 138 with the current configuration.
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
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
												
												// Looking for a path between Sample 20 and consumer double 57.
												{
													{
														for(int index$var19$98_1 = 0; index$var19$98_1 < 2; index$var19$98_1 += 1) {
															if(!(rawMu[0] < rawMu[1])) {
																if((index$var19$98_1 == 0)) {
																	if(!(rawMu[0] < rawMu[1])) {
																		// Processing sample task 20 of consumer random variable null.
																		{
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
																								// Record the probability of sample task 20 generating output with current configuration.
																								if(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$98_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$98_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$98_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$98_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$98_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 20 with the current configuration.
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
								}
								{
									if((var19 == 1)) {
										if(!guard$sample20if61) {
											// The body will execute, so should not be executed again
											guard$sample20if61 = true;
											{
												// Looking for a path between If 61 and consumer Gaussian 129.
												{
													{
														if((rawMu[0] < rawMu[1])) {
															double traceTempVariable$var57$71_1 = rawMu[1];
															double traceTempVariable$var115$71_2 = traceTempVariable$var57$71_1;
															for(int n = 0; n < N; n += 1) {
																if(component[n]) {
																	if((1 == 0)) {
																		if(component[n]) {
																			double traceTempVariable$componentMu$71_4 = traceTempVariable$var115$71_2;
																			
																			// Processing sample task 138 of consumer random variable null.
																			{
																				{
																					// Flag recording if this sample task of the consuming random variable is constrained.
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						// Mark that the sample has observed constrained data.
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						
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
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												
																												// Constructing a random variable input for use later.
																												double var128 = (componentSigma * componentSigma);
																												
																												// Record the probability of sample task 138 generating output with current configuration.
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$71_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$71_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$71_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$71_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$71_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																												}
																												
																												// Recorded the probability of reaching sample task 138 with the current configuration.
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
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
													{
														if((rawMu[0] < rawMu[1])) {
															double traceTempVariable$var57$73_1 = rawMu[1];
															double traceTempVariable$var117$73_2 = traceTempVariable$var57$73_1;
															for(int n = 0; n < N; n += 1) {
																if(!component[n]) {
																	if((1 == 1)) {
																		if(!component[n]) {
																			double traceTempVariable$componentMu$73_4 = traceTempVariable$var117$73_2;
																			
																			// Processing sample task 138 of consumer random variable null.
																			{
																				{
																					// Flag recording if this sample task of the consuming random variable is constrained.
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						// Mark that the sample has observed constrained data.
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						
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
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												
																												// Constructing a random variable input for use later.
																												double var128 = (componentSigma * componentSigma);
																												
																												// Record the probability of sample task 138 generating output with current configuration.
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$73_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$73_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$73_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$73_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$73_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																												}
																												
																												// Recorded the probability of reaching sample task 138 with the current configuration.
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
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
												
												// Looking for a path between Sample 20 and consumer double 57.
												{
													{
														for(int index$var19$83_1 = 0; index$var19$83_1 < 2; index$var19$83_1 += 1) {
															if((rawMu[0] < rawMu[1])) {
																if((index$var19$83_1 == 1)) {
																	if((rawMu[0] < rawMu[1])) {
																		// Processing sample task 20 of consumer random variable null.
																		{
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
																								// Record the probability of sample task 20 generating output with current configuration.
																								if(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$83_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$83_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$83_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$83_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$83_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 20 with the current configuration.
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
												
												// Looking for a path between If 61 and consumer Gaussian 129.
												{
													{
														if(!(rawMu[0] < rawMu[1])) {
															double traceTempVariable$var57$87_1 = rawMu[0];
															double traceTempVariable$var115$87_2 = traceTempVariable$var57$87_1;
															for(int n = 0; n < N; n += 1) {
																if(component[n]) {
																	if((1 == 0)) {
																		if(component[n]) {
																			double traceTempVariable$componentMu$87_4 = traceTempVariable$var115$87_2;
																			
																			// Processing sample task 138 of consumer random variable null.
																			{
																				{
																					// Flag recording if this sample task of the consuming random variable is constrained.
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						// Mark that the sample has observed constrained data.
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						
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
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												
																												// Constructing a random variable input for use later.
																												double var128 = (componentSigma * componentSigma);
																												
																												// Record the probability of sample task 138 generating output with current configuration.
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$87_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$87_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$87_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$87_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$87_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																												}
																												
																												// Recorded the probability of reaching sample task 138 with the current configuration.
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
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
													{
														if(!(rawMu[0] < rawMu[1])) {
															double traceTempVariable$var57$89_1 = rawMu[0];
															double traceTempVariable$var117$89_2 = traceTempVariable$var57$89_1;
															for(int n = 0; n < N; n += 1) {
																if(!component[n]) {
																	if((1 == 1)) {
																		if(!component[n]) {
																			double traceTempVariable$componentMu$89_4 = traceTempVariable$var117$89_2;
																			
																			// Processing sample task 138 of consumer random variable null.
																			{
																				{
																					// Flag recording if this sample task of the consuming random variable is constrained.
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						// Mark that the sample has observed constrained data.
																						constrainedFlag$sample20[((var19 - 0) / 1)] = true;
																						
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
																											{
																												double componentSigma;
																												if(component[n])
																													componentSigma = sigma[0];
																												else
																													componentSigma = sigma[1];
																												
																												// Constructing a random variable input for use later.
																												double var128 = (componentSigma * componentSigma);
																												
																												// Record the probability of sample task 138 generating output with current configuration.
																												if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$89_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$89_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$89_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$89_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$componentMu$89_4) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																												}
																												
																												// Recorded the probability of reaching sample task 138 with the current configuration.
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
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
												
												// Looking for a path between Sample 20 and consumer double 57.
												{
													{
														for(int index$var19$99_1 = 0; index$var19$99_1 < 2; index$var19$99_1 += 1) {
															if(!(rawMu[0] < rawMu[1])) {
																if((index$var19$99_1 == 0)) {
																	if(!(rawMu[0] < rawMu[1])) {
																		// Processing sample task 20 of consumer random variable null.
																		{
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
																								// Record the probability of sample task 20 generating output with current configuration.
																								if(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$99_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$99_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$99_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$99_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < (2.0 * 2.0))?(DistributionSampling.logProbabilityGaussian(((rawMu[index$var19$99_1] - 0.0) / Math.sqrt((2.0 * 2.0)))) - (0.5 * Math.log((2.0 * 2.0)))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 20 with the current configuration.
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
							double var20 = cv$originalValue;
							
							// Guards to ensure that rawMu is only updated when there is a valid path.
							{
								{
									{
										rawMu[var19] = var20;
									}
								}
							}
							
							// Guards to ensure that mu is only updated when there is a valid path.
							// 
							// Looking for a path between Sample 20 and consumer double[] 41.
							{
								// Guard to check that at most one copy of the code is executed for a given set of
								// loop iterations.
								boolean guard$sample20put43 = false;
								{
									if((var19 == 0)) {
										if(!guard$sample20put43) {
											// The body will execute, so should not be executed again
											guard$sample20put43 = true;
											{
												double var39;
												if((rawMu[0] < rawMu[1]))
													var39 = rawMu[0];
												else
													var39 = rawMu[1];
												mu[0] = var39;
											}
										}
									}
								}
								{
									if((var19 == 1)) {
										if(!guard$sample20put43) {
											// The body will execute, so should not be executed again
											guard$sample20put43 = true;
											{
												double var39;
												if((rawMu[0] < rawMu[1]))
													var39 = rawMu[0];
												else
													var39 = rawMu[1];
												mu[0] = var39;
											}
										}
									}
								}
								{
									if((rawMu[0] < rawMu[1])) {
										if((var19 == 0)) {
											if((rawMu[0] < rawMu[1])) {
												if(!guard$sample20put43) {
													// The body will execute, so should not be executed again
													guard$sample20put43 = true;
													{
														double var39;
														if((rawMu[0] < rawMu[1]))
															var39 = rawMu[0];
														else
															var39 = rawMu[1];
														mu[0] = var39;
													}
												}
											}
										}
									}
								}
								{
									if(!(rawMu[0] < rawMu[1])) {
										if((var19 == 1)) {
											if(!(rawMu[0] < rawMu[1])) {
												if(!guard$sample20put43) {
													// The body will execute, so should not be executed again
													guard$sample20put43 = true;
													{
														double var39;
														if((rawMu[0] < rawMu[1]))
															var39 = rawMu[0];
														else
															var39 = rawMu[1];
														mu[0] = var39;
													}
												}
											}
										}
									}
								}
							}
							
							// Guards to ensure that mu is only updated when there is a valid path.
							// 
							// Looking for a path between Sample 20 and consumer double[] 59.
							{
								// Guard to check that at most one copy of the code is executed for a given set of
								// loop iterations.
								boolean guard$sample20put63 = false;
								{
									if((var19 == 0)) {
										if(!guard$sample20put63) {
											// The body will execute, so should not be executed again
											guard$sample20put63 = true;
											{
												double var57;
												if((rawMu[0] < rawMu[1]))
													var57 = rawMu[1];
												else
													var57 = rawMu[0];
												mu[1] = var57;
											}
										}
									}
								}
								{
									if((var19 == 1)) {
										if(!guard$sample20put63) {
											// The body will execute, so should not be executed again
											guard$sample20put63 = true;
											{
												double var57;
												if((rawMu[0] < rawMu[1]))
													var57 = rawMu[1];
												else
													var57 = rawMu[0];
												mu[1] = var57;
											}
										}
									}
								}
								{
									if((rawMu[0] < rawMu[1])) {
										if((var19 == 1)) {
											if((rawMu[0] < rawMu[1])) {
												if(!guard$sample20put63) {
													// The body will execute, so should not be executed again
													guard$sample20put63 = true;
													{
														double var57;
														if((rawMu[0] < rawMu[1]))
															var57 = rawMu[1];
														else
															var57 = rawMu[0];
														mu[1] = var57;
													}
												}
											}
										}
									}
								}
								{
									if(!(rawMu[0] < rawMu[1])) {
										if((var19 == 0)) {
											if(!(rawMu[0] < rawMu[1])) {
												if(!guard$sample20put63) {
													// The body will execute, so should not be executed again
													guard$sample20put63 = true;
													{
														double var57;
														if((rawMu[0] < rawMu[1]))
															var57 = rawMu[1];
														else
															var57 = rawMu[0];
														mu[1] = var57;
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

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 83 drawn from TruncatedGaussian 66. Inference was performed using
	// Metropolis-Hastings.
	private final void inferSample83(int var78) {
		if(true) {
			constrainedFlag$sample83[((var78 - 0) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// Metropolis-Hastings
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// The original value of the sample
			double cv$originalValue = sigma[var78];
			
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
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((constrainedFlag$sample83[((var78 - 0) / 1)] || (cv$valuePos == 0))) {
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
						double var79 = cv$proposedValue;
						
						// Guards to ensure that sigma is only updated when there is a valid path.
						{
							{
								{
									sigma[var78] = cv$currentValue;
								}
							}
						}
					}
					{
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						
						// Constructing a random variable input for use later.
						double var63 = (2.0 * 2.0);
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((((0.0 <= cv$currentValue) && (cv$currentValue <= 1.0E100)) && (0.0 < 1.0E100)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((cv$currentValue - 0.0) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt(var63))) - Gaussian.cdf(((0.0 - 0.0) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY));
						
						// Processing random variable 129.
						{
							// Looking for a path between Sample 83 and consumer Gaussian 129.
							{
								{
									double traceTempVariable$var124$2_1 = cv$currentValue;
									for(int n = 0; n < N; n += 1) {
										if(component[n]) {
											if((var78 == 0)) {
												if(component[n]) {
													double traceTempVariable$componentSigma$2_3 = traceTempVariable$var124$2_1;
													
													// Processing sample task 138 of consumer random variable null.
													{
														{
															// Flag recording if this sample task of the consuming random variable is constrained.
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																// Mark that the sample has observed constrained data.
																constrainedFlag$sample83[((var78 - 0) / 1)] = true;
																
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
																					double componentMu;
																					if(component[n])
																						componentMu = mu[0];
																					else
																						componentMu = mu[1];
																					
																					// Constructing a random variable input for use later.
																					double var128 = (traceTempVariable$componentSigma$2_3 * traceTempVariable$componentSigma$2_3);
																					
																					// Record the probability of sample task 138 generating output with current configuration.
																					if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						// If the second value is -infinity.
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																					}
																					
																					// Recorded the probability of reaching sample task 138 with the current configuration.
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
								{
									double traceTempVariable$var126$3_1 = cv$currentValue;
									for(int n = 0; n < N; n += 1) {
										if(!component[n]) {
											if((var78 == 1)) {
												if(!component[n]) {
													double traceTempVariable$componentSigma$3_3 = traceTempVariable$var126$3_1;
													
													// Processing sample task 138 of consumer random variable null.
													{
														{
															// Flag recording if this sample task of the consuming random variable is constrained.
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																// Mark that the sample has observed constrained data.
																constrainedFlag$sample83[((var78 - 0) / 1)] = true;
																
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
																					double componentMu;
																					if(component[n])
																						componentMu = mu[0];
																					else
																						componentMu = mu[1];
																					
																					// Constructing a random variable input for use later.
																					double var128 = (traceTempVariable$componentSigma$3_3 * traceTempVariable$componentSigma$3_3);
																					
																					// Record the probability of sample task 138 generating output with current configuration.
																					if(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						// If the second value is -infinity.
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY)));
																					}
																					
																					// Recorded the probability of reaching sample task 138 with the current configuration.
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
							double var79 = cv$originalValue;
							
							// Guards to ensure that sigma is only updated when there is a valid path.
							{
								{
									{
										sigma[var78] = var79;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 88 drawn from Beta 83. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void inferSample88() {
		if(true) {
			constrainedFlag$sample88 = false;
			
			// Local variable to record the number of true samples.
			int cv$sum = 0;
			
			// Local variable to record the number of samples.
			int cv$count = 0;
			{
				// Processing random variable 85.
				{
					{
						{
							// Processing sample task 101 of consumer random variable componentDistribution.
							{
								{
									for(int var96 = 0; var96 < N; var96 += 1) {
										// Flag recording if this sample task of the consuming random variable is constrained.
										boolean cv$sampleConstrained = (fixedFlag$sample101 || constrainedFlag$sample101[((var96 - 0) / 1)]);
										if(cv$sampleConstrained) {
											// Mark that the sample has observed constrained data.
											constrainedFlag$sample88 = true;
											{
												{
													{
														{
															{
																// Include the value sampled by task 101 from random variable componentDistribution.
																// Increment the number of samples.
																cv$count = (cv$count + 1);
																
																// If the sample value was positive increase the count
																if(component[var96])
																	cv$sum = (cv$sum + 1);
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
			if(constrainedFlag$sample88)
				// Write out the new value of the sample.
				theta = Conjugates.sampleConjugateBetaBinomial(RNG$, 5.0, 5.0, cv$sum, cv$count);
		}
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
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var96 = 0; var96 < N; var96 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = component[var96];
						{
							{
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= theta) && (theta <= 1.0))?Math.log((cv$sampleValue?theta:(1.0 - theta))):Double.NEGATIVE_INFINITY));
								
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
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$componentDistribution = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var97 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$component = (logProbability$component + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample101)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample101 = (fixedFlag$sample101 && fixedFlag$sample88);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var96 = 0; var96 < N; var96 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var97;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$componentDistribution = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$component = (logProbability$component + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample101)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample138 using sampled
	// values.
	private final void logProbabilityValue$sample138() {
		// Determine if we need to calculate the values for sample task 138 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample138) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int n = 0; n < N; n += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = y[n];
						{
							{
								double componentMu;
								if(component[n])
									componentMu = mu[0];
								else
									componentMu = mu[1];
								double componentSigma;
								if(component[n])
									componentSigma = sigma[0];
								else
									componentSigma = sigma[1];
								double var128 = (componentSigma * componentSigma);
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - componentMu) / Math.sqrt(var128))) - (0.5 * Math.log(var128))):Double.NEGATIVE_INFINITY));
								
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
				logProbability$sample138[((n - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$y = (logProbability$y + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample138 = ((fixedFlag$sample20 && fixedFlag$sample83) && fixedFlag$sample101);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int n = 0; n < N; n += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample138[((n - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			
			// Update the variable probability
			logProbability$y = (logProbability$y + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample20 using sampled
	// values.
	private final void logProbabilityValue$sample20() {
		// Determine if we need to calculate the values for sample task 20 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample20) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var19 = 0; var19 < 2; var19 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = rawMu[var19];
						{
							{
								double var3 = 0.0;
								double var6 = (2.0 * 2.0);
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var6)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var3) / Math.sqrt(var6))) - (0.5 * Math.log(var6))):Double.NEGATIVE_INFINITY));
								
								// Looking for a path between Put 21 and consumer double 39.
								{
									// Guard to check that at most one copy of the code is executed for a given set of
									// loop iterations.
									boolean guard$put21if41 = false;
									{
										if((var19 == 0)) {
											if(!guard$put21if41)
												// The body will execute, so should not be executed again
												guard$put21if41 = true;
										}
									}
									{
										if((var19 == 1)) {
											if(!guard$put21if41)
												// The body will execute, so should not be executed again
												guard$put21if41 = true;
										}
									}
								}
								
								// Looking for a path between Put 21 and consumer double 57.
								{
									// Guard to check that at most one copy of the code is executed for a given set of
									// loop iterations.
									boolean guard$put21if61 = false;
									{
										if((var19 == 0)) {
											if(!guard$put21if61)
												// The body will execute, so should not be executed again
												guard$put21if61 = true;
										}
									}
									{
										if((var19 == 1)) {
											if(!guard$put21if61)
												// The body will execute, so should not be executed again
												guard$put21if61 = true;
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
				logProbability$sample20[((var19 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that mu is only updated once for this probability.
				boolean cv$guard$mu = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 20 and consumer double[] 41.
				{
					{
						if((rawMu[0] < rawMu[1])) {
							if((var19 == 0)) {
								if((rawMu[0] < rawMu[1])) {
									// If the probability of the variable has not already been updated
									if(!cv$guard$mu) {
										// Set the guard so the update is only applied once.
										cv$guard$mu = true;
										
										// Update the variable probability
										logProbability$mu = (logProbability$mu + cv$sampleProbability);
									}
								}
							}
						}
					}
					{
						if(!(rawMu[0] < rawMu[1])) {
							if((var19 == 1)) {
								if(!(rawMu[0] < rawMu[1])) {
									// If the probability of the variable has not already been updated
									if(!cv$guard$mu) {
										// Set the guard so the update is only applied once.
										cv$guard$mu = true;
										
										// Update the variable probability
										logProbability$mu = (logProbability$mu + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 20 and consumer double[] 59.
				{
					{
						if((rawMu[0] < rawMu[1])) {
							if((var19 == 1)) {
								if((rawMu[0] < rawMu[1])) {
									// If the probability of the variable has not already been updated
									if(!cv$guard$mu) {
										// Set the guard so the update is only applied once.
										cv$guard$mu = true;
										
										// Update the variable probability
										logProbability$mu = (logProbability$mu + cv$sampleProbability);
									}
								}
							}
						}
					}
					{
						if(!(rawMu[0] < rawMu[1])) {
							if((var19 == 0)) {
								if(!(rawMu[0] < rawMu[1])) {
									// If the probability of the variable has not already been updated
									if(!cv$guard$mu) {
										// Set the guard so the update is only applied once.
										cv$guard$mu = true;
										
										// Update the variable probability
										logProbability$mu = (logProbability$mu + cv$sampleProbability);
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
			
			// Update the variable probability
			logProbability$rawMu = (logProbability$rawMu + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample20 = fixedFlag$sample20;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var19 = 0; var19 < 2; var19 += 1) {
				double cv$sampleValue = logProbability$sample20[((var19 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Guard to ensure that mu is only updated once for this probability.
				boolean cv$guard$mu = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 20 and consumer double[] 41.
				{
					{
						if((rawMu[0] < rawMu[1])) {
							if((var19 == 0)) {
								if((rawMu[0] < rawMu[1])) {
									// If the probability of the variable has not already been updated
									if(!cv$guard$mu) {
										// Set the guard so the update is only applied once.
										cv$guard$mu = true;
										
										// Update the variable probability
										logProbability$mu = (logProbability$mu + cv$sampleValue);
									}
								}
							}
						}
					}
					{
						if(!(rawMu[0] < rawMu[1])) {
							if((var19 == 1)) {
								if(!(rawMu[0] < rawMu[1])) {
									// If the probability of the variable has not already been updated
									if(!cv$guard$mu) {
										// Set the guard so the update is only applied once.
										cv$guard$mu = true;
										
										// Update the variable probability
										logProbability$mu = (logProbability$mu + cv$sampleValue);
									}
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 20 and consumer double[] 59.
				{
					{
						if((rawMu[0] < rawMu[1])) {
							if((var19 == 1)) {
								if((rawMu[0] < rawMu[1])) {
									// If the probability of the variable has not already been updated
									if(!cv$guard$mu) {
										// Set the guard so the update is only applied once.
										cv$guard$mu = true;
										
										// Update the variable probability
										logProbability$mu = (logProbability$mu + cv$sampleValue);
									}
								}
							}
						}
					}
					{
						if(!(rawMu[0] < rawMu[1])) {
							if((var19 == 0)) {
								if(!(rawMu[0] < rawMu[1])) {
									// If the probability of the variable has not already been updated
									if(!cv$guard$mu) {
										// Set the guard so the update is only applied once.
										cv$guard$mu = true;
										
										// Update the variable probability
										logProbability$mu = (logProbability$mu + cv$sampleValue);
									}
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			logProbability$rawMu = (logProbability$rawMu + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample83 using sampled
	// values.
	private final void logProbabilityValue$sample83() {
		// Determine if we need to calculate the values for sample task 83 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample83) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var78 = 0; var78 < 2; var78 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = sigma[var78];
						{
							{
								double var60 = 0.0;
								double var63 = (2.0 * 2.0);
								double var64 = 0.0;
								double var65 = 1.0E100;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((((var64 <= cv$sampleValue) && (cv$sampleValue <= var65)) && (var64 < var65)) && (0.0 < var63))?(((0.0 < var63)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var60) / Math.sqrt(var63))) - (0.5 * Math.log(var63))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((var65 - var60) / Math.sqrt(var63))) - Gaussian.cdf(((var64 - var60) / Math.sqrt(var63)))))):Double.NEGATIVE_INFINITY));
								
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
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			
			// Store the random variable instance probability
			logProbability$var79 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$sigma = (logProbability$sigma + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample83)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample83 = fixedFlag$sample83;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var78 = 0; var78 < 2; var78 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var79;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			logProbability$sigma = (logProbability$sigma + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample83)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample88 using sampled
	// values.
	private final void logProbabilityValue$sample88() {
		// Determine if we need to calculate the values for sample task 88 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample88) {
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
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = theta;
					{
						{
							double var81 = 5.0;
							double var82 = 5.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var81, var82));
							
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
			
			// Store the sample task probability
			logProbability$theta = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample88 = fixedFlag$sample88;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$theta;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var97$stateProbabilityGlobal
		{
			// Allocation of cv$var97$stateProbabilityGlobal for single threaded execution
			cv$var97$stateProbabilityGlobal = new double[2];
		}
		
		// Constructor for guard$sample20if124$global
		{
			// Calculate the largest index of var19 that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_var19 = 0;
			cv$max_var19 = Math.max(cv$max_var19, ((2 - 0) / 1));
			
			// Allocation of guard$sample20if124$global for single threaded execution
			guard$sample20if124$global = new boolean[cv$max_var19];
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If rawMu has not been set already allocate space.
		if(!fixedFlag$sample20) {
			// Constructor for rawMu
			{
				rawMu = new double[2];
			}
		}
		
		// Constructor for mu
		{
			mu = new double[2];
		}
		
		// If sigma has not been set already allocate space.
		if(!fixedFlag$sample83) {
			// Constructor for sigma
			{
				sigma = new double[2];
			}
		}
		
		// If component has not been set already allocate space.
		if(!fixedFlag$sample101) {
			// Constructor for component
			{
				component = new boolean[length$yObserved];
			}
		}
		
		// Constructor for y
		{
			y = new double[length$yObserved];
		}
		
		// Constructor for constrainedFlag$sample101
		{
			constrainedFlag$sample101 = new boolean[((((length$yObserved - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for constrainedFlag$sample20
		{
			constrainedFlag$sample20 = new boolean[((((2 - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for constrainedFlag$sample83
		{
			constrainedFlag$sample83 = new boolean[((((2 - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample20
		{
			logProbability$sample20 = new double[((((2 - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample138
		{
			logProbability$sample138 = new double[((((length$yObserved - 1) - 0) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		for(int var19 = 0; var19 < 2; var19 += 1) {
			if(!fixedFlag$sample20)
				rawMu[var19] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		double var39 = 0.0;
		if((rawMu[0] < rawMu[1])) {
			if(!fixedFlag$sample20)
				var39 = rawMu[0];
		} else {
			if(!fixedFlag$sample20)
				var39 = rawMu[1];
		}
		if(!fixedFlag$sample20)
			mu[0] = var39;
		double var57 = 0.0;
		if((rawMu[0] < rawMu[1])) {
			if(!fixedFlag$sample20)
				var57 = rawMu[1];
		} else {
			if(!fixedFlag$sample20)
				var57 = rawMu[0];
		}
		if(!fixedFlag$sample20)
			mu[1] = var57;
		for(int var78 = 0; var78 < 2; var78 += 1) {
			if(!fixedFlag$sample83)
				sigma[var78] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleTruncatedGaussian(RNG$, ((0.0 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((0.0 - 0.0) / Math.sqrt((2.0 * 2.0)))), ((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0)))))) + 0.0);
		}
		if(!fixedFlag$sample88)
			theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
		for(int var96 = 0; var96 < N; var96 += 1) {
			if(!fixedFlag$sample101)
				component[var96] = DistributionSampling.sampleBernoulli(RNG$, theta);
		}
		for(int n = 0; n < N; n += 1) {
			double componentMu;
			if(component[n])
				componentMu = mu[0];
			else
				componentMu = mu[1];
			double componentSigma;
			if(component[n])
				componentSigma = sigma[0];
			else
				componentSigma = sigma[1];
			y[n] = ((Math.sqrt((componentSigma * componentSigma)) * DistributionSampling.sampleGaussian(RNG$)) + componentMu);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int var19 = 0; var19 < 2; var19 += 1) {
			if(!fixedFlag$sample20)
				rawMu[var19] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		double var39;
		if((rawMu[0] < rawMu[1]))
			var39 = rawMu[0];
		else
			var39 = rawMu[1];
		mu[0] = var39;
		double var57;
		if((rawMu[0] < rawMu[1]))
			var57 = rawMu[1];
		else
			var57 = rawMu[0];
		mu[1] = var57;
		for(int var78 = 0; var78 < 2; var78 += 1) {
			if(!fixedFlag$sample83)
				sigma[var78] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleTruncatedGaussian(RNG$, ((0.0 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((0.0 - 0.0) / Math.sqrt((2.0 * 2.0)))), ((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0)))))) + 0.0);
		}
		if(!fixedFlag$sample88)
			theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
		for(int var96 = 0; var96 < N; var96 += 1) {
			if(!fixedFlag$sample101)
				component[var96] = DistributionSampling.sampleBernoulli(RNG$, theta);
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		for(int var19 = 0; var19 < 2; var19 += 1) {
			if(!fixedFlag$sample20)
				rawMu[var19] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		double var39;
		if((rawMu[0] < rawMu[1]))
			var39 = rawMu[0];
		else
			var39 = rawMu[1];
		mu[0] = var39;
		double var57;
		if((rawMu[0] < rawMu[1]))
			var57 = rawMu[1];
		else
			var57 = rawMu[0];
		mu[1] = var57;
		for(int var78 = 0; var78 < 2; var78 += 1) {
			if(!fixedFlag$sample83)
				sigma[var78] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleTruncatedGaussian(RNG$, ((0.0 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((0.0 - 0.0) / Math.sqrt((2.0 * 2.0)))), ((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0)))))) + 0.0);
		}
		if(!fixedFlag$sample88)
			theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
		for(int var96 = 0; var96 < N; var96 += 1) {
			if(!fixedFlag$sample101)
				component[var96] = DistributionSampling.sampleBernoulli(RNG$, theta);
		}
		for(int n = 0; n < N; n += 1) {
			double componentMu;
			if(component[n])
				componentMu = mu[0];
			else
				componentMu = mu[1];
			double componentSigma;
			if(component[n])
				componentSigma = sigma[0];
			else
				componentSigma = sigma[1];
			y[n] = ((Math.sqrt((componentSigma * componentSigma)) * DistributionSampling.sampleGaussian(RNG$)) + componentMu);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var19 = 0; var19 < 2; var19 += 1) {
			if(!fixedFlag$sample20)
				rawMu[var19] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		double var39 = 0.0;
		if((rawMu[0] < rawMu[1])) {
			if(!fixedFlag$sample20)
				var39 = rawMu[0];
		} else {
			if(!fixedFlag$sample20)
				var39 = rawMu[1];
		}
		if(!fixedFlag$sample20)
			mu[0] = var39;
		double var57 = 0.0;
		if((rawMu[0] < rawMu[1])) {
			if(!fixedFlag$sample20)
				var57 = rawMu[1];
		} else {
			if(!fixedFlag$sample20)
				var57 = rawMu[0];
		}
		if(!fixedFlag$sample20)
			mu[1] = var57;
		for(int var78 = 0; var78 < 2; var78 += 1) {
			if(!fixedFlag$sample83)
				sigma[var78] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleTruncatedGaussian(RNG$, ((0.0 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((0.0 - 0.0) / Math.sqrt((2.0 * 2.0)))), ((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0)))))) + 0.0);
		}
		if(!fixedFlag$sample88)
			theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
		for(int var96 = 0; var96 < N; var96 += 1) {
			if(!fixedFlag$sample101)
				component[var96] = DistributionSampling.sampleBernoulli(RNG$, theta);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		for(int var19 = 0; var19 < 2; var19 += 1) {
			if(!fixedFlag$sample20)
				rawMu[var19] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		double var39;
		if((rawMu[0] < rawMu[1]))
			var39 = rawMu[0];
		else
			var39 = rawMu[1];
		mu[0] = var39;
		double var57;
		if((rawMu[0] < rawMu[1]))
			var57 = rawMu[1];
		else
			var57 = rawMu[0];
		mu[1] = var57;
		for(int var78 = 0; var78 < 2; var78 += 1) {
			if(!fixedFlag$sample83)
				sigma[var78] = ((Math.sqrt((2.0 * 2.0)) * DistributionSampling.sampleTruncatedGaussian(RNG$, ((0.0 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((0.0 - 0.0) / Math.sqrt((2.0 * 2.0)))), ((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0))), Gaussian.cdf(((1.0E100 - 0.0) / Math.sqrt((2.0 * 2.0)))))) + 0.0);
		}
		if(!fixedFlag$sample88)
			theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
		for(int var96 = 0; var96 < N; var96 += 1) {
			if(!fixedFlag$sample101)
				component[var96] = DistributionSampling.sampleBernoulli(RNG$, theta);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			for(int var19 = 0; var19 < 2; var19 += 1) {
				if(!fixedFlag$sample20)
					inferSample20(var19);
			}
			for(int var78 = 0; var78 < 2; var78 += 1) {
				if(!fixedFlag$sample83)
					inferSample83(var78);
			}
			if(!fixedFlag$sample88)
				inferSample88();
			for(int var96 = 0; var96 < N; var96 += 1) {
				if(!fixedFlag$sample101)
					inferSample101(var96);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int var96 = (N - ((((N - 1) - 0) % 1) + 1)); var96 >= ((0 - 1) + 1); var96 -= 1) {
				if(!fixedFlag$sample101)
					inferSample101(var96);
			}
			if(!fixedFlag$sample88)
				inferSample88();
			for(int var78 = (2 - ((((2 - 1) - 0) % 1) + 1)); var78 >= ((0 - 1) + 1); var78 -= 1) {
				if(!fixedFlag$sample83)
					inferSample83(var78);
			}
			for(int var19 = (2 - ((((2 - 1) - 0) % 1) + 1)); var19 >= ((0 - 1) + 1); var19 -= 1) {
				if(!fixedFlag$sample20)
					inferSample20(var19);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
		for(int var19 = 0; var19 < 2; var19 += 1) {
			if(!constrainedFlag$sample20[((var19 - 0) / 1)])
				drawValueSample20(var19);
		}
		for(int var78 = 0; var78 < 2; var78 += 1) {
			if(!constrainedFlag$sample83[((var78 - 0) / 1)])
				drawValueSample83(var78);
		}
		if(!constrainedFlag$sample88)
			drawValueSample88();
		for(int var96 = 0; var96 < N; var96 += 1) {
			if(!constrainedFlag$sample101[((var96 - 0) / 1)])
				drawValueSample101(var96);
		}
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
		logProbability$rawMu = 0.0;
		logProbability$mu = 0.0;
		if(!fixedProbFlag$sample20) {
			for(int var19 = 0; var19 < 2; var19 += 1)
				logProbability$sample20[((var19 - 0) / 1)] = Double.NaN;
		}
		logProbability$sigma = 0.0;
		if(!fixedProbFlag$sample83)
			logProbability$var79 = Double.NaN;
		if(!fixedProbFlag$sample88)
			logProbability$theta = Double.NaN;
		logProbability$componentDistribution = Double.NaN;
		logProbability$component = 0.0;
		if(!fixedProbFlag$sample101)
			logProbability$var97 = Double.NaN;
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample138) {
			for(int n = 0; n < N; n += 1)
				logProbability$sample138[((n - 0) / 1)] = Double.NaN;
		}
	}

	// Method for initializing the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		N = length$yObserved;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample101$1 = 0; index$constrainedFlag$sample101$1 < constrainedFlag$sample101.length; index$constrainedFlag$sample101$1 += 1)
			constrainedFlag$sample101[index$constrainedFlag$sample101$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample20$1 = 0; index$constrainedFlag$sample20$1 < constrainedFlag$sample20.length; index$constrainedFlag$sample20$1 += 1)
			constrainedFlag$sample20[index$constrainedFlag$sample20$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample83$1 = 0; index$constrainedFlag$sample83$1 < constrainedFlag$sample83.length; index$constrainedFlag$sample83$1 += 1)
			constrainedFlag$sample83[index$constrainedFlag$sample83$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		if(fixedFlag$sample83)
			logProbabilityValue$sample83();
		if(fixedFlag$sample88)
			logProbabilityValue$sample88();
		if(fixedFlag$sample101)
			logProbabilityValue$sample101();
		logProbabilityValue$sample138();
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
		logProbabilityValue$sample20();
		logProbabilityValue$sample83();
		logProbabilityValue$sample88();
		logProbabilityValue$sample101();
		logProbabilityValue$sample138();
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
		logProbabilityValue$sample20();
		logProbabilityValue$sample83();
		logProbabilityValue$sample88();
		logProbabilityValue$sample101();
		logProbabilityValue$sample138();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Deep copy between arrays
		double[] cv$source1 = yObserved;
		double[] cv$target1 = y;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		double var39;
		if((rawMu[0] < rawMu[1]))
			var39 = rawMu[0];
		else
			var39 = rawMu[1];
		mu[0] = var39;
		double var57;
		if((rawMu[0] < rawMu[1]))
			var57 = rawMu[1];
		else
			var57 = rawMu[0];
		mu[1] = var57;
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2026, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model LowDimMix(double[] yObserved) {\n"
		     + "    int N = yObserved.length;\n"
		     + "\n"
		     + "    // Stan parameter: ordered[2] mu; prior: mu ~ normal(0, 2)\n"
		     + "    // Sampling two unconstrained normal values and sorting them gives the same ordered support up to\n"
		     + "    // the constant normalisation factor for the ordered constraint.\n"
		     + "    double[] rawMu = gaussian(0.0, 2.0 * 2.0).sample(2);\n"
		     + "    double[] mu = new double[2];\n"
		     + "    mu[0] = rawMu[0] < rawMu[1] ? rawMu[0] : rawMu[1];\n"
		     + "    mu[1] = rawMu[0] < rawMu[1] ? rawMu[1] : rawMu[0];\n"
		     + "\n"
		     + "    // Stan parameter: array[2] real<lower=0> sigma; prior: sigma ~ normal(0, 2)\n"
		     + "    double[] sigma = truncatedGaussian(0.0, 2.0 * 2.0, 0.0, 1.0e100).sample(2);\n"
		     + "\n"
		     + "    // Stan parameter: real<lower=0, upper=1> theta; prior: theta ~ beta(5, 5)\n"
		     + "    double theta = beta(5.0, 5.0).sample();\n"
		     + "\n"
		     + "    // Stan likelihood:\n"
		     + "    // target += log_mix(theta, normal_lpdf(y[n] | mu[1], sigma[1]),\n"
		     + "    //                   normal_lpdf(y[n] | mu[2], sigma[2]));\n"
		     + "    // In Sandwood, represent the same two-component mixture with explicit latent component indicators.\n"
		     + "    Bernoulli componentDistribution = bernoulli(theta);\n"
		     + "    boolean[] component = componentDistribution.sample(N);\n"
		     + "    double[] y = new double[N];\n"
		     + "\n"
		     + "    for(int n = 0; n < N; n++) {\n"
		     + "        double componentMu = component[n] ? mu[0] : mu[1];\n"
		     + "        double componentSigma = component[n] ? sigma[0] : sigma[1];\n"
		     + "        y[n] = gaussian(componentMu, componentSigma * componentSigma).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    y.observe(yObserved);\n"
		     + "}\n"
		     + "";
	}
}