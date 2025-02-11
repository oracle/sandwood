package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Vulcano2012basicDG$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Vulcano2012basicDG$CoreInterface {
	
	// Declare the variables for the model.
	private int[][] ObsSales;
	private int[] arrivals;
	private boolean[][] avail;
	private double[] exped;
	private double[] expedNorm;
	private boolean fixedFlag$sample125 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample88 = false;
	private boolean fixedFlag$sample90 = false;
	private boolean fixedProbFlag$sample125 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample88 = false;
	private boolean fixedProbFlag$sample90 = false;
	private boolean[] guard$sample34multinomial124$global;
	private boolean[][] guard$sample34put102$global;
	private boolean[][] guard$sample34put123$global;
	private boolean[] guard$sample34put61$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$arrivals;
	private double logProbability$exped;
	private double logProbability$expedNorm;
	private double logProbability$lambda;
	private double logProbability$sales;
	private double[] logProbability$sample125;
	private double[] logProbability$sample34;
	private double logProbability$ut;
	private double logProbability$var111;
	private double logProbability$var23;
	private double logProbability$var77;
	private double logProbability$var79;
	private double logProbability$var80;
	private double logProbability$weekly_rates;
	private double logProbability$weekly_sales;
	private double logProbability$weekly_ut;
	private int numTimeSteps;
	private double r;
	private int[][] sales;
	private boolean setFlag$arrivals = false;
	private boolean setFlag$lambda = false;
	private boolean setFlag$weekly_sales = false;
	private boolean system$gibbsForward = true;
	private double[] ut;
	private double[][] weekly_rates;
	private int[][] weekly_sales;
	private double[][] weekly_ut;

	public Vulcano2012basicDG$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for ObsSales.
	@Override
	public final int[][] get$ObsSales() {
		return ObsSales;
	}

	// Setter for ObsSales.
	@Override
	public final void set$ObsSales(int[][] cv$value) {
		// Set ObsSales with flag to mark that it has been set so another array doesn't need
		// to be constructed
		ObsSales = cv$value;
	}

	// Getter for arrivals.
	@Override
	public final int[] get$arrivals() {
		return arrivals;
	}

	// Setter for arrivals.
	@Override
	public final void set$arrivals(int[] cv$value) {
		// Set arrivals with flag to mark that it has been set so another array doesn't need
		// to be constructed
		arrivals = cv$value;
		setFlag$arrivals = true;
	}

	// Getter for avail.
	@Override
	public final boolean[][] get$avail() {
		return avail;
	}

	// Setter for avail.
	@Override
	public final void set$avail(boolean[][] cv$value) {
		// Set avail with flag to mark that it has been set so another array doesn't need
		// to be constructed
		avail = cv$value;
	}

	// Getter for fixedFlag$sample125.
	@Override
	public final boolean get$fixedFlag$sample125() {
		return fixedFlag$sample125;
	}

	// Setter for fixedFlag$sample125.
	@Override
	public final void set$fixedFlag$sample125(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample125 including if probabilities
		// need to be updated.
		fixedFlag$sample125 = cv$value;
		
		// Should the probability of sample 125 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample125 = (fixedFlag$sample125 && fixedProbFlag$sample125);
	}

	// Getter for fixedFlag$sample34.
	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	// Setter for fixedFlag$sample34.
	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample34 including if probabilities
		// need to be updated.
		fixedFlag$sample34 = cv$value;
		
		// Should the probability of sample 34 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample34 = (fixedFlag$sample34 && fixedProbFlag$sample34);
		
		// Should the probability of sample 125 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample125 = (fixedFlag$sample34 && fixedProbFlag$sample125);
	}

	// Getter for fixedFlag$sample88.
	@Override
	public final boolean get$fixedFlag$sample88() {
		return fixedFlag$sample88;
	}

	// Setter for fixedFlag$sample88.
	@Override
	public final void set$fixedFlag$sample88(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample88 including if probabilities
		// need to be updated.
		fixedFlag$sample88 = cv$value;
		
		// Should the probability of sample 88 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample88 = (fixedFlag$sample88 && fixedProbFlag$sample88);
		
		// Should the probability of sample 90 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample90 = (fixedFlag$sample88 && fixedProbFlag$sample90);
	}

	// Getter for fixedFlag$sample90.
	@Override
	public final boolean get$fixedFlag$sample90() {
		return fixedFlag$sample90;
	}

	// Setter for fixedFlag$sample90.
	@Override
	public final void set$fixedFlag$sample90(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample90 including if probabilities
		// need to be updated.
		fixedFlag$sample90 = cv$value;
		
		// Should the probability of sample 90 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample90 = (fixedFlag$sample90 && fixedProbFlag$sample90);
		
		// Should the probability of sample 125 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample125 = (fixedFlag$sample90 && fixedProbFlag$sample125);
	}

	// Getter for lambda.
	@Override
	public final double[] get$lambda() {
		return lambda;
	}

	// Setter for lambda.
	@Override
	public final void set$lambda(double[] cv$value) {
		// Set lambda with flag to mark that it has been set so another array doesn't need
		// to be constructed
		lambda = cv$value;
		setFlag$lambda = true;
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

	// Getter for logProbability$arrivals.
	@Override
	public final double get$logProbability$arrivals() {
		return logProbability$arrivals;
	}

	// Getter for logProbability$lambda.
	@Override
	public final double get$logProbability$lambda() {
		return logProbability$lambda;
	}

	// Getter for logProbability$weekly_sales.
	@Override
	public final double get$logProbability$weekly_sales() {
		return logProbability$weekly_sales;
	}

	// Getter for numTimeSteps.
	@Override
	public final int get$numTimeSteps() {
		return numTimeSteps;
	}

	// Getter for r.
	@Override
	public final double get$r() {
		return r;
	}

	// Getter for weekly_sales.
	@Override
	public final int[][] get$weekly_sales() {
		return weekly_sales;
	}

	// Setter for weekly_sales.
	@Override
	public final void set$weekly_sales(int[][] cv$value) {
		// Set weekly_sales with flag to mark that it has been set so another array doesn't
		// need to be constructed
		weekly_sales = cv$value;
		setFlag$weekly_sales = true;
	}

	// Calculate the probability of the samples represented by sample125 using sampled
	// values.
	private final void logProbabilityValue$sample125() {
		// Determine if we need to calculate the values for sample task 125 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample125) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						int[] cv$sampleValue = weekly_sales[((t - 0) / 1)];
						{
							{
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(cv$sampleValue, weekly_rates[((t - 0) / 1)], arrivals[((t - 0) / 1)]));
								
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
					logProbability$sample125[((t - 0) / 1)] = cv$sampleProbability;
					
					// Guard to ensure that sales is only updated once for this probability.
					boolean cv$guard$sales = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					{
						if((0 < numTimeSteps)) {
							for(int j$var116 = 0; j$var116 < avail[0].length; j$var116 += 1) {
								if((0 < numTimeSteps)) {
									// If the probability of the variable has not already been updated
									if(!cv$guard$sales) {
										// Set the guard so the update is only applied once.
										cv$guard$sales = true;
										
										// Update the variable probability
										logProbability$sales = (logProbability$sales + cv$sampleProbability);
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
			logProbability$var111 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample125 = ((fixedFlag$sample125 && fixedFlag$sample34) && fixedFlag$sample90);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$sampleValue = logProbability$sample125[((t - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Guard to ensure that sales is only updated once for this probability.
					boolean cv$guard$sales = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					{
						if((0 < numTimeSteps)) {
							for(int j$var116 = 0; j$var116 < avail[0].length; j$var116 += 1) {
								if((0 < numTimeSteps)) {
									// If the probability of the variable has not already been updated
									if(!cv$guard$sales) {
										// Set the guard so the update is only applied once.
										cv$guard$sales = true;
										
										// Update the variable probability
										logProbability$sales = (logProbability$sales + cv$sampleValue);
									}
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var111 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample34 using sampled
	// values.
	private final void logProbabilityValue$sample34() {
		// Determine if we need to calculate the values for sample task 34 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample34) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int var27 = 0; var27 < avail[0].length; var27 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = ut[var27];
						{
							{
								double var21 = 0.0;
								double var22 = 1.0;
								
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
					logProbability$sample34[((var27 - 0) / 1)] = cv$sampleProbability;
					
					// Guard to ensure that exped is only updated once for this probability.
					boolean cv$guard$exped = false;
					
					// Guard to ensure that expedNorm is only updated once for this probability.
					boolean cv$guard$expedNorm = false;
					
					// Guard to ensure that weekly_ut is only updated once for this probability.
					boolean cv$guard$weekly_ut = false;
					
					// Guard to ensure that weekly_rates is only updated once for this probability.
					boolean cv$guard$weekly_rates = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					// 
					// Looking for a path between Sample 34 and consumer double[] 36.
					{
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
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
							}
						}
					}
					
					// Looking for a path between Sample 34 and consumer double[] 54.
					{
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																// If the probability of the variable has not already been updated
																if(!cv$guard$expedNorm) {
																	// Set the guard so the update is only applied once.
																	cv$guard$expedNorm = true;
																	
																	// Update the variable probability
																	logProbability$expedNorm = (logProbability$expedNorm + cv$sampleProbability);
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
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																// If the probability of the variable has not already been updated
																if(!cv$guard$expedNorm) {
																	// Set the guard so the update is only applied once.
																	cv$guard$expedNorm = true;
																	
																	// Update the variable probability
																	logProbability$expedNorm = (logProbability$expedNorm + cv$sampleProbability);
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
					
					// Looking for a path between Sample 34 and consumer double[] 90.
					{
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									// If the probability of the variable has not already been updated
																									if(!cv$guard$weekly_ut) {
																										// Set the guard so the update is only applied once.
																										cv$guard$weekly_ut = true;
																										
																										// Update the variable probability
																										logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleProbability);
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
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								// If the probability of the variable has not already been updated
																								if(!cv$guard$weekly_ut) {
																									// Set the guard so the update is only applied once.
																									cv$guard$weekly_ut = true;
																									
																									// Update the variable probability
																									logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleProbability);
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
					
					// Looking for a path between Sample 34 and consumer double[] 110.
					{
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if((0 < numTimeSteps)) {
																										if(avail[t][j$var86]) {
																											if((0 < numTimeSteps)) {
																												if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																													{
																														for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																															// If the probability of the variable has not already been updated
																															if(!cv$guard$weekly_rates) {
																																// Set the guard so the update is only applied once.
																																cv$guard$weekly_rates = true;
																																
																																// Update the variable probability
																																logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleProbability);
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
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if((0 < numTimeSteps)) {
																										if(avail[t][j$var86]) {
																											if((0 < numTimeSteps)) {
																												for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																													if((0 < numTimeSteps)) {
																														if((j$var86 == j$var107)) {
																															// If the probability of the variable has not already been updated
																															if(!cv$guard$weekly_rates) {
																																// Set the guard so the update is only applied once.
																																cv$guard$weekly_rates = true;
																																
																																// Update the variable probability
																																logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleProbability);
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
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										if((0 < numTimeSteps)) {
																											if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																												{
																													for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																														// If the probability of the variable has not already been updated
																														if(!cv$guard$weekly_rates) {
																															// Set the guard so the update is only applied once.
																															cv$guard$weekly_rates = true;
																															
																															// Update the variable probability
																															logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleProbability);
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
								}
							}
						}
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										if((0 < numTimeSteps)) {
																											for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																												if((0 < numTimeSteps)) {
																													if((j$var86 == j$var107)) {
																														// If the probability of the variable has not already been updated
																														if(!cv$guard$weekly_rates) {
																															// Set the guard so the update is only applied once.
																															cv$guard$weekly_rates = true;
																															
																															// Update the variable probability
																															logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleProbability);
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
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample34 = fixedFlag$sample34;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int var27 = 0; var27 < avail[0].length; var27 += 1) {
					double cv$sampleValue = logProbability$sample34[((var27 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Guard to ensure that exped is only updated once for this probability.
					boolean cv$guard$exped = false;
					
					// Guard to ensure that expedNorm is only updated once for this probability.
					boolean cv$guard$expedNorm = false;
					
					// Guard to ensure that weekly_ut is only updated once for this probability.
					boolean cv$guard$weekly_ut = false;
					
					// Guard to ensure that weekly_rates is only updated once for this probability.
					boolean cv$guard$weekly_rates = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					// 
					// Looking for a path between Sample 34 and consumer double[] 36.
					{
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
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
							}
						}
					}
					
					// Looking for a path between Sample 34 and consumer double[] 54.
					{
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																// If the probability of the variable has not already been updated
																if(!cv$guard$expedNorm) {
																	// Set the guard so the update is only applied once.
																	cv$guard$expedNorm = true;
																	
																	// Update the variable probability
																	logProbability$expedNorm = (logProbability$expedNorm + cv$sampleValue);
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
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																// If the probability of the variable has not already been updated
																if(!cv$guard$expedNorm) {
																	// Set the guard so the update is only applied once.
																	cv$guard$expedNorm = true;
																	
																	// Update the variable probability
																	logProbability$expedNorm = (logProbability$expedNorm + cv$sampleValue);
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
					
					// Looking for a path between Sample 34 and consumer double[] 90.
					{
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									// If the probability of the variable has not already been updated
																									if(!cv$guard$weekly_ut) {
																										// Set the guard so the update is only applied once.
																										cv$guard$weekly_ut = true;
																										
																										// Update the variable probability
																										logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleValue);
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
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								// If the probability of the variable has not already been updated
																								if(!cv$guard$weekly_ut) {
																									// Set the guard so the update is only applied once.
																									cv$guard$weekly_ut = true;
																									
																									// Update the variable probability
																									logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleValue);
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
					
					// Looking for a path between Sample 34 and consumer double[] 110.
					{
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if((0 < numTimeSteps)) {
																										if(avail[t][j$var86]) {
																											if((0 < numTimeSteps)) {
																												if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																													{
																														for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																															// If the probability of the variable has not already been updated
																															if(!cv$guard$weekly_rates) {
																																// Set the guard so the update is only applied once.
																																cv$guard$weekly_rates = true;
																																
																																// Update the variable probability
																																logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
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
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if((0 < numTimeSteps)) {
																										if(avail[t][j$var86]) {
																											if((0 < numTimeSteps)) {
																												for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																													if((0 < numTimeSteps)) {
																														if((j$var86 == j$var107)) {
																															// If the probability of the variable has not already been updated
																															if(!cv$guard$weekly_rates) {
																																// Set the guard so the update is only applied once.
																																cv$guard$weekly_rates = true;
																																
																																// Update the variable probability
																																logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
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
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										if((0 < numTimeSteps)) {
																											if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																												{
																													for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																														// If the probability of the variable has not already been updated
																														if(!cv$guard$weekly_rates) {
																															// Set the guard so the update is only applied once.
																															cv$guard$weekly_rates = true;
																															
																															// Update the variable probability
																															logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
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
								}
							}
						}
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										if((0 < numTimeSteps)) {
																											for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																												if((0 < numTimeSteps)) {
																													if((j$var86 == j$var107)) {
																														// If the probability of the variable has not already been updated
																														if(!cv$guard$weekly_rates) {
																															// Set the guard so the update is only applied once.
																															cv$guard$weekly_rates = true;
																															
																															// Update the variable probability
																															logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
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
			if(fixedFlag$sample34)
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
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = lambda[((t - 0) / 1)];
						{
							{
								double var75 = 10.0;
								double var76 = 10.0;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGamma(cv$sampleValue, var75, var76));
								
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
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var77 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$lambda = cv$accumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample88 = fixedFlag$sample88;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$lambda;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var77 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample90 using sampled
	// values.
	private final void logProbabilityValue$sample90() {
		// Determine if we need to calculate the values for sample task 90 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample90) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// Reduction of array null
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						int reduceVar$numSales$4 = 0;
						
						// For each index in the array to be reduced
						for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							int k$var69 = reduceVar$numSales$4;
							
							// Set the right hand term to a value from the array var64
							int l$var70 = ObsSales[t][cv$reduction78Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$numSales$4 = (k$var69 + l$var70);
						}
						
						// The sample value to calculate the probability of generating
						int cv$sampleValue = (arrivals[((t - 0) / 1)] - reduceVar$numSales$4);
						{
							{
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$sampleValue, lambda[((t - 0) / 1)]));
								
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
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var79 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var80 = cv$accumulator;
			
			// Update the variable probability
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample90)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample90 = (fixedFlag$sample90 && fixedFlag$sample88);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var80;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var79 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample90)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 34 drawn from Gaussian 23. Inference was performed using Metropolis-Hastings.
	private final void sample34(int var27) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		{
			// Metropolis-Hastings
			cv$noStates = Math.max(cv$noStates, 2);
		}
		
		// The original value of the sample
		double cv$originalValue = ut[var27];
		
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
					double var28 = cv$proposedValue;
					ut[var27] = cv$currentValue;
					
					// Guards to ensure that exped is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 34 and consumer double[] 36.
					{
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											{
												exped[j$var33] = Math.exp(ut[j$var33]);
											}
										}
									}
								}
							}
						}
					}
					
					// Guards to ensure that expedNorm is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 34 and consumer double[] 54.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[] guard$sample34put61 = guard$sample34put61$global;
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1)
																// Set the flags to false
																guard$sample34put61[((j$var50 - 0) / 1)] = false;
														}
													}
												}
											}
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50))
																// Set the flags to false
																guard$sample34put61[((j$var50 - 0) / 1)] = false;
														}
													}
												}
											}
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																if(!guard$sample34put61[((j$var50 - 0) / 1)]) {
																	// The body will execute, so should not be executed again
																	guard$sample34put61[((j$var50 - 0) / 1)] = true;
																	{
																		// Reduction of array exped
																		// 
																		// A generated name to prevent name collisions if the reduction is implemented more
																		// than once in inference and probability code. Initialize the variable to the unit
																		// value
																		double reduceVar$sum$0 = 0.0;
																		
																		// For each index in the array to be reduced
																		for(int cv$reduction280Index = 0; cv$reduction280Index < avail[0].length; cv$reduction280Index += 1) {
																			// Set the left hand term of the reduction function to the return variable value.
																			double k$var44 = reduceVar$sum$0;
																			
																			// Set the right hand term to a value from the array exped
																			double l$var45 = exped[cv$reduction280Index];
																			
																			// Execute the reduction function, saving the result into the return value.
																			// 
																			// Copy the result of the reduction into the variable returned by the reduction.
																			reduceVar$sum$0 = (k$var44 + l$var45);
																		}
																		expedNorm[j$var50] = (exped[j$var50] / (r * reduceVar$sum$0));
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
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if(!guard$sample34put61[((j$var50 - 0) / 1)]) {
																	// The body will execute, so should not be executed again
																	guard$sample34put61[((j$var50 - 0) / 1)] = true;
																	{
																		// Reduction of array exped
																		// 
																		// A generated name to prevent name collisions if the reduction is implemented more
																		// than once in inference and probability code. Initialize the variable to the unit
																		// value
																		double reduceVar$sum$1 = 0.0;
																		
																		// For each index in the array to be reduced
																		for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1) {
																			// Set the left hand term of the reduction function to the return variable value.
																			double k$var44 = reduceVar$sum$1;
																			
																			// Set the right hand term to a value from the array exped
																			double l$var45 = exped[cv$reduction50Index];
																			
																			// Execute the reduction function, saving the result into the return value.
																			// 
																			// Copy the result of the reduction into the variable returned by the reduction.
																			reduceVar$sum$1 = (k$var44 + l$var45);
																		}
																		expedNorm[j$var50] = (exped[j$var50] / (r * reduceVar$sum$1));
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
					
					// Guards to ensure that weekly_ut is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 34 and consumer double[] 90.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[][] guard$sample34put102 = guard$sample34put102$global;
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86))
																									// Set the flags to false
																									guard$sample34put102[((t - 0) / 1)][((j$var86 - 0) / 1)] = false;
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
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86))
																								// Set the flags to false
																								guard$sample34put102[((t - 0) / 1)][((j$var86 - 0) / 1)] = false;
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
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if(!guard$sample34put102[((t - 0) / 1)][((j$var86 - 0) / 1)]) {
																										// The body will execute, so should not be executed again
																										guard$sample34put102[((t - 0) / 1)][((j$var86 - 0) / 1)] = true;
																										{
																											weekly_ut[((t - 0) / 1)][j$var86] = expedNorm[j$var86];
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
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if(!guard$sample34put102[((t - 0) / 1)][((j$var86 - 0) / 1)]) {
																									// The body will execute, so should not be executed again
																									guard$sample34put102[((t - 0) / 1)][((j$var86 - 0) / 1)] = true;
																									{
																										weekly_ut[((t - 0) / 1)][j$var86] = expedNorm[j$var86];
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
					
					// Guards to ensure that weekly_rates is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 34 and consumer double[] 110.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[][] guard$sample34put123 = guard$sample34put123$global;
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if((0 < numTimeSteps)) {
																										if(avail[t][j$var86]) {
																											if((0 < numTimeSteps)) {
																												if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																													{
																														for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1)
																															// Set the flags to false
																															guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)] = false;
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
							}
						}
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if((0 < numTimeSteps)) {
																										if(avail[t][j$var86]) {
																											if((0 < numTimeSteps)) {
																												for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																													if((0 < numTimeSteps)) {
																														if((j$var86 == j$var107))
																															// Set the flags to false
																															guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)] = false;
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
							}
						}
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										if((0 < numTimeSteps)) {
																											if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																												{
																													for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1)
																														// Set the flags to false
																														guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)] = false;
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
						}
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										if((0 < numTimeSteps)) {
																											for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																												if((0 < numTimeSteps)) {
																													if((j$var86 == j$var107))
																														// Set the flags to false
																														guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)] = false;
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
						}
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if((0 < numTimeSteps)) {
																										if(avail[t][j$var86]) {
																											if((0 < numTimeSteps)) {
																												if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																													{
																														for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																															if(!guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)]) {
																																// The body will execute, so should not be executed again
																																guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)] = true;
																																{
																																	// Reduction of array weekly_ut
																																	// 
																																	// A generated name to prevent name collisions if the reduction is implemented more
																																	// than once in inference and probability code. Initialize the variable to the unit
																																	// value
																																	double reduceVar$denom$0 = 0.0;
																																	
																																	// For each index in the array to be reduced
																																	for(int cv$reduction719Index = 0; cv$reduction719Index < (avail[0].length + 1); cv$reduction719Index += 1) {
																																		// Set the left hand term of the reduction function to the return variable value.
																																		double k$var99 = reduceVar$denom$0;
																																		
																																		// Set the right hand term to a value from the array weekly_ut
																																		double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction719Index];
																																		
																																		// Execute the reduction function, saving the result into the return value.
																																		// 
																																		// Copy the result of the reduction into the variable returned by the reduction.
																																		reduceVar$denom$0 = (k$var99 + l$var100);
																																	}
																																	weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$0);
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
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if((0 < numTimeSteps)) {
																										if(avail[t][j$var86]) {
																											if((0 < numTimeSteps)) {
																												for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																													if((0 < numTimeSteps)) {
																														if((j$var86 == j$var107)) {
																															if(!guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)]) {
																																// The body will execute, so should not be executed again
																																guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)] = true;
																																{
																																	// Reduction of array weekly_ut
																																	// 
																																	// A generated name to prevent name collisions if the reduction is implemented more
																																	// than once in inference and probability code. Initialize the variable to the unit
																																	// value
																																	double reduceVar$denom$1 = 0.0;
																																	
																																	// For each index in the array to be reduced
																																	for(int cv$reduction111Index = 0; cv$reduction111Index < (avail[0].length + 1); cv$reduction111Index += 1) {
																																		// Set the left hand term of the reduction function to the return variable value.
																																		double k$var99 = reduceVar$denom$1;
																																		
																																		// Set the right hand term to a value from the array weekly_ut
																																		double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction111Index];
																																		
																																		// Execute the reduction function, saving the result into the return value.
																																		// 
																																		// Copy the result of the reduction into the variable returned by the reduction.
																																		reduceVar$denom$1 = (k$var99 + l$var100);
																																	}
																																	weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$1);
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
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										if((0 < numTimeSteps)) {
																											if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																												{
																													for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																														if(!guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)]) {
																															// The body will execute, so should not be executed again
																															guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)] = true;
																															{
																																// Reduction of array weekly_ut
																																// 
																																// A generated name to prevent name collisions if the reduction is implemented more
																																// than once in inference and probability code. Initialize the variable to the unit
																																// value
																																double reduceVar$denom$2 = 0.0;
																																
																																// For each index in the array to be reduced
																																for(int cv$reduction819Index = 0; cv$reduction819Index < (avail[0].length + 1); cv$reduction819Index += 1) {
																																	// Set the left hand term of the reduction function to the return variable value.
																																	double k$var99 = reduceVar$denom$2;
																																	
																																	// Set the right hand term to a value from the array weekly_ut
																																	double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction819Index];
																																	
																																	// Execute the reduction function, saving the result into the return value.
																																	// 
																																	// Copy the result of the reduction into the variable returned by the reduction.
																																	reduceVar$denom$2 = (k$var99 + l$var100);
																																}
																																weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$2);
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
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										if((0 < numTimeSteps)) {
																											for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																												if((0 < numTimeSteps)) {
																													if((j$var86 == j$var107)) {
																														if(!guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)]) {
																															// The body will execute, so should not be executed again
																															guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)] = true;
																															{
																																// Reduction of array weekly_ut
																																// 
																																// A generated name to prevent name collisions if the reduction is implemented more
																																// than once in inference and probability code. Initialize the variable to the unit
																																// value
																																double reduceVar$denom$3 = 0.0;
																																
																																// For each index in the array to be reduced
																																for(int cv$reduction111Index = 0; cv$reduction111Index < (avail[0].length + 1); cv$reduction111Index += 1) {
																																	// Set the left hand term of the reduction function to the return variable value.
																																	double k$var99 = reduceVar$denom$3;
																																	
																																	// Set the right hand term to a value from the array weekly_ut
																																	double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction111Index];
																																	
																																	// Execute the reduction function, saving the result into the return value.
																																	// 
																																	// Copy the result of the reduction into the variable returned by the reduction.
																																	reduceVar$denom$3 = (k$var99 + l$var100);
																																}
																																weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$3);
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
					cv$temp$1$var22 = 1.0;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var21) / Math.sqrt(cv$temp$1$var22))) - (0.5 * Math.log(cv$temp$1$var22))));
				
				// Processing random variable 111.
				{
					// Looking for a path between Sample 34 and consumer Multinomial 111.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[] guard$sample34multinomial124 = guard$sample34multinomial124$global;
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if((0 < numTimeSteps)) {
																										if(avail[t][j$var86]) {
																											if((0 < numTimeSteps)) {
																												if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																													{
																														// Set the flags to false
																														guard$sample34multinomial124[((t - 0) / 1)] = false;
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
							}
						}
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if((0 < numTimeSteps)) {
																										if(avail[t][j$var86]) {
																											if((0 < numTimeSteps)) {
																												for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																													if((0 < numTimeSteps)) {
																														if((j$var86 == j$var107))
																															// Set the flags to false
																															guard$sample34multinomial124[((t - 0) / 1)] = false;
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
							}
						}
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										if((0 < numTimeSteps)) {
																											if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																												{
																													// Set the flags to false
																													guard$sample34multinomial124[((t - 0) / 1)] = false;
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
						}
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										if((0 < numTimeSteps)) {
																											for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																												if((0 < numTimeSteps)) {
																													if((j$var86 == j$var107))
																														// Set the flags to false
																														guard$sample34multinomial124[((t - 0) / 1)] = false;
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
						}
						if((0 < numTimeSteps)) {
							double traceTempVariable$var34$22_1 = cv$currentValue;
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												double traceTempVariable$k$22_3 = Math.exp(traceTempVariable$var34$22_1);
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if(((0 < exped.length) && (0 < avail[0].length))) {
																// Reduction of array exped
																// 
																// A generated name to prevent name collisions if the reduction is implemented more
																// than once in inference and probability code. Initialize the variable to the unit
																// value
																double reduceVar$sum$2 = 0.0;
																
																// Reduce for every value except a masked value which will be skipped.
																for(int cv$reduction1123Index = 0; cv$reduction1123Index < j$var33; cv$reduction1123Index += 1) {
																	// Set the left hand term of the reduction function to the return variable value.
																	double k$var44 = reduceVar$sum$2;
																	
																	// Set the right hand term to a value from the array exped
																	double l$var45 = exped[cv$reduction1123Index];
																	
																	// Execute the reduction function, saving the result into the return value.
																	// 
																	// Copy the result of the reduction into the variable returned by the reduction.
																	reduceVar$sum$2 = (k$var44 + l$var45);
																}
																for(int cv$reduction1123Index = (j$var33 + 1); cv$reduction1123Index < avail[0].length; cv$reduction1123Index += 1) {
																	// Set the left hand term of the reduction function to the return variable value.
																	double k$var44 = reduceVar$sum$2;
																	
																	// Set the right hand term to a value from the array exped
																	double l$var45 = exped[cv$reduction1123Index];
																	
																	// Execute the reduction function, saving the result into the return value.
																	// 
																	// Execute the reduction function, saving the result into the return value.
																	// 
																	// Copy the result of the reduction into the variable returned by the reduction.
																	reduceVar$sum$2 = (k$var44 + l$var45);
																}
																double cv$reduced50 = reduceVar$sum$2;
																
																// Copy the result of the reduction into the variable returned by the reduction.
																reduceVar$sum$2 = (traceTempVariable$k$22_3 + cv$reduced50);
																double traceTempVariable$sum$22_4 = reduceVar$sum$2;
																if((0 < numTimeSteps)) {
																	for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																		if((0 < numTimeSteps)) {
																			double traceTempVariable$var89$22_6 = (exped[j$var50] / (r * traceTempVariable$sum$22_4));
																			if((0 < numTimeSteps)) {
																				for(int t = 0; t < numTimeSteps; t += 1) {
																					if((0 < numTimeSteps)) {
																						for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																							if((0 < numTimeSteps)) {
																								if(avail[t][j$var86]) {
																									if((j$var50 == j$var86)) {
																										if((0 < numTimeSteps)) {
																											if(avail[t][j$var86]) {
																												double traceTempVariable$k$22_9 = traceTempVariable$var89$22_6;
																												if((0 < numTimeSteps)) {
																													if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																														{
																															if(((0 < weekly_ut[((t - 0) / 1)].length) && (0 < (avail[0].length + 1)))) {
																																// Reduction of array weekly_ut
																																// 
																																// A generated name to prevent name collisions if the reduction is implemented more
																																// than once in inference and probability code. Initialize the variable to the unit
																																// value
																																double reduceVar$denom$4 = 0.0;
																																
																																// Reduce for every value except a masked value which will be skipped.
																																for(int cv$reduction1160Index = 0; cv$reduction1160Index < j$var86; cv$reduction1160Index += 1) {
																																	// Set the left hand term of the reduction function to the return variable value.
																																	double k$var99 = reduceVar$denom$4;
																																	
																																	// Set the right hand term to a value from the array weekly_ut
																																	double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction1160Index];
																																	
																																	// Execute the reduction function, saving the result into the return value.
																																	// 
																																	// Copy the result of the reduction into the variable returned by the reduction.
																																	reduceVar$denom$4 = (k$var99 + l$var100);
																																}
																																for(int cv$reduction1160Index = (j$var86 + 1); cv$reduction1160Index < (avail[0].length + 1); cv$reduction1160Index += 1) {
																																	// Set the left hand term of the reduction function to the return variable value.
																																	double k$var99 = reduceVar$denom$4;
																																	
																																	// Set the right hand term to a value from the array weekly_ut
																																	double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction1160Index];
																																	
																																	// Execute the reduction function, saving the result into the return value.
																																	// 
																																	// Execute the reduction function, saving the result into the return value.
																																	// 
																																	// Copy the result of the reduction into the variable returned by the reduction.
																																	reduceVar$denom$4 = (k$var99 + l$var100);
																																}
																																double cv$reduced111 = reduceVar$denom$4;
																																
																																// Copy the result of the reduction into the variable returned by the reduction.
																																reduceVar$denom$4 = (traceTempVariable$k$22_9 + cv$reduced111);
																																double traceTempVariable$denom$22_10 = reduceVar$denom$4;
																																if(!guard$sample34multinomial124[((t - 0) / 1)]) {
																																	// The body will execute, so should not be executed again
																																	guard$sample34multinomial124[((t - 0) / 1)] = true;
																																	
																																	// Processing sample task 125 of consumer random variable null.
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
																																						double[] cv$temp$2$weekly_rates;
																																						{
																																							cv$temp$2$weekly_rates = weekly_rates[((t - 0) / 1)];
																																						}
																																						int cv$temp$3$arrivals;
																																						{
																																							cv$temp$3$arrivals = arrivals[((t - 0) / 1)];
																																						}
																																						
																																						// Record the probability of sample task 125 generating output with current configuration.
																																						if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$arrivals)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$arrivals)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$arrivals));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$arrivals)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$arrivals)));
																																						}
																																						
																																						// Recorded the probability of reaching sample task 125 with the current configuration.
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
						if((0 < numTimeSteps)) {
							double traceTempVariable$var34$23_1 = cv$currentValue;
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												double traceTempVariable$k$23_3 = Math.exp(traceTempVariable$var34$23_1);
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if(((0 < exped.length) && (0 < avail[0].length))) {
																// Reduction of array exped
																// 
																// A generated name to prevent name collisions if the reduction is implemented more
																// than once in inference and probability code. Initialize the variable to the unit
																// value
																double reduceVar$sum$3 = 0.0;
																
																// Reduce for every value except a masked value which will be skipped.
																for(int cv$reduction1195Index = 0; cv$reduction1195Index < j$var33; cv$reduction1195Index += 1) {
																	// Set the left hand term of the reduction function to the return variable value.
																	double k$var44 = reduceVar$sum$3;
																	
																	// Set the right hand term to a value from the array exped
																	double l$var45 = exped[cv$reduction1195Index];
																	
																	// Execute the reduction function, saving the result into the return value.
																	// 
																	// Copy the result of the reduction into the variable returned by the reduction.
																	reduceVar$sum$3 = (k$var44 + l$var45);
																}
																for(int cv$reduction1195Index = (j$var33 + 1); cv$reduction1195Index < avail[0].length; cv$reduction1195Index += 1) {
																	// Set the left hand term of the reduction function to the return variable value.
																	double k$var44 = reduceVar$sum$3;
																	
																	// Set the right hand term to a value from the array exped
																	double l$var45 = exped[cv$reduction1195Index];
																	
																	// Execute the reduction function, saving the result into the return value.
																	// 
																	// Execute the reduction function, saving the result into the return value.
																	// 
																	// Copy the result of the reduction into the variable returned by the reduction.
																	reduceVar$sum$3 = (k$var44 + l$var45);
																}
																double cv$reduced50 = reduceVar$sum$3;
																
																// Copy the result of the reduction into the variable returned by the reduction.
																reduceVar$sum$3 = (traceTempVariable$k$23_3 + cv$reduced50);
																double traceTempVariable$sum$23_4 = reduceVar$sum$3;
																if((0 < numTimeSteps)) {
																	for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																		if((0 < numTimeSteps)) {
																			double traceTempVariable$var89$23_6 = (exped[j$var50] / (r * traceTempVariable$sum$23_4));
																			if((0 < numTimeSteps)) {
																				for(int t = 0; t < numTimeSteps; t += 1) {
																					if((0 < numTimeSteps)) {
																						for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																							if((0 < numTimeSteps)) {
																								if(avail[t][j$var86]) {
																									if((j$var50 == j$var86)) {
																										if((0 < numTimeSteps)) {
																											if(avail[t][j$var86]) {
																												double traceTempVariable$var108$23_9 = traceTempVariable$var89$23_6;
																												if((0 < numTimeSteps)) {
																													for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																														if((0 < numTimeSteps)) {
																															if((j$var86 == j$var107)) {
																																if(!guard$sample34multinomial124[((t - 0) / 1)]) {
																																	// The body will execute, so should not be executed again
																																	guard$sample34multinomial124[((t - 0) / 1)] = true;
																																	
																																	// Processing sample task 125 of consumer random variable null.
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
																																						double[] cv$temp$4$weekly_rates;
																																						{
																																							cv$temp$4$weekly_rates = weekly_rates[((t - 0) / 1)];
																																						}
																																						int cv$temp$5$arrivals;
																																						{
																																							cv$temp$5$arrivals = arrivals[((t - 0) / 1)];
																																						}
																																						
																																						// Record the probability of sample task 125 generating output with current configuration.
																																						if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$arrivals)) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$arrivals)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$arrivals));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$arrivals)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$arrivals)));
																																						}
																																						
																																						// Recorded the probability of reaching sample task 125 with the current configuration.
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
						if((0 < numTimeSteps)) {
							double traceTempVariable$var34$24_1 = cv$currentValue;
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												double traceTempVariable$var51$24_3 = Math.exp(traceTempVariable$var34$24_1);
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	// Reduction of array exped
																	// 
																	// A generated name to prevent name collisions if the reduction is implemented more
																	// than once in inference and probability code. Initialize the variable to the unit
																	// value
																	double reduceVar$sum$4 = 0.0;
																	
																	// For each index in the array to be reduced
																	for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1) {
																		// Set the left hand term of the reduction function to the return variable value.
																		double k$var44 = reduceVar$sum$4;
																		
																		// Set the right hand term to a value from the array exped
																		double l$var45 = exped[cv$reduction50Index];
																		
																		// Execute the reduction function, saving the result into the return value.
																		// 
																		// Copy the result of the reduction into the variable returned by the reduction.
																		reduceVar$sum$4 = (k$var44 + l$var45);
																	}
																	double traceTempVariable$var89$24_5 = (traceTempVariable$var51$24_3 / (r * reduceVar$sum$4));
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										double traceTempVariable$k$24_8 = traceTempVariable$var89$24_5;
																										if((0 < numTimeSteps)) {
																											if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																												{
																													if(((0 < weekly_ut[((t - 0) / 1)].length) && (0 < (avail[0].length + 1)))) {
																														// Reduction of array weekly_ut
																														// 
																														// A generated name to prevent name collisions if the reduction is implemented more
																														// than once in inference and probability code. Initialize the variable to the unit
																														// value
																														double reduceVar$denom$5 = 0.0;
																														
																														// Reduce for every value except a masked value which will be skipped.
																														for(int cv$reduction1283Index = 0; cv$reduction1283Index < j$var86; cv$reduction1283Index += 1) {
																															// Set the left hand term of the reduction function to the return variable value.
																															double k$var99 = reduceVar$denom$5;
																															
																															// Set the right hand term to a value from the array weekly_ut
																															double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction1283Index];
																															
																															// Execute the reduction function, saving the result into the return value.
																															// 
																															// Copy the result of the reduction into the variable returned by the reduction.
																															reduceVar$denom$5 = (k$var99 + l$var100);
																														}
																														for(int cv$reduction1283Index = (j$var86 + 1); cv$reduction1283Index < (avail[0].length + 1); cv$reduction1283Index += 1) {
																															// Set the left hand term of the reduction function to the return variable value.
																															double k$var99 = reduceVar$denom$5;
																															
																															// Set the right hand term to a value from the array weekly_ut
																															double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction1283Index];
																															
																															// Execute the reduction function, saving the result into the return value.
																															// 
																															// Execute the reduction function, saving the result into the return value.
																															// 
																															// Copy the result of the reduction into the variable returned by the reduction.
																															reduceVar$denom$5 = (k$var99 + l$var100);
																														}
																														double cv$reduced111 = reduceVar$denom$5;
																														
																														// Copy the result of the reduction into the variable returned by the reduction.
																														reduceVar$denom$5 = (traceTempVariable$k$24_8 + cv$reduced111);
																														double traceTempVariable$denom$24_9 = reduceVar$denom$5;
																														if(!guard$sample34multinomial124[((t - 0) / 1)]) {
																															// The body will execute, so should not be executed again
																															guard$sample34multinomial124[((t - 0) / 1)] = true;
																															
																															// Processing sample task 125 of consumer random variable null.
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
																																				double[] cv$temp$6$weekly_rates;
																																				{
																																					cv$temp$6$weekly_rates = weekly_rates[((t - 0) / 1)];
																																				}
																																				int cv$temp$7$arrivals;
																																				{
																																					cv$temp$7$arrivals = arrivals[((t - 0) / 1)];
																																				}
																																				
																																				// Record the probability of sample task 125 generating output with current configuration.
																																				if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$6$weekly_rates, cv$temp$7$arrivals)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$6$weekly_rates, cv$temp$7$arrivals)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					// If the second value is -infinity.
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$6$weekly_rates, cv$temp$7$arrivals));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$6$weekly_rates, cv$temp$7$arrivals)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$6$weekly_rates, cv$temp$7$arrivals)));
																																				}
																																				
																																				// Recorded the probability of reaching sample task 125 with the current configuration.
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
													}
												}
											}
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							double traceTempVariable$var34$25_1 = cv$currentValue;
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												double traceTempVariable$var51$25_3 = Math.exp(traceTempVariable$var34$25_1);
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	// Reduction of array exped
																	// 
																	// A generated name to prevent name collisions if the reduction is implemented more
																	// than once in inference and probability code. Initialize the variable to the unit
																	// value
																	double reduceVar$sum$5 = 0.0;
																	
																	// For each index in the array to be reduced
																	for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1) {
																		// Set the left hand term of the reduction function to the return variable value.
																		double k$var44 = reduceVar$sum$5;
																		
																		// Set the right hand term to a value from the array exped
																		double l$var45 = exped[cv$reduction50Index];
																		
																		// Execute the reduction function, saving the result into the return value.
																		// 
																		// Copy the result of the reduction into the variable returned by the reduction.
																		reduceVar$sum$5 = (k$var44 + l$var45);
																	}
																	double traceTempVariable$var89$25_5 = (traceTempVariable$var51$25_3 / (r * reduceVar$sum$5));
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										double traceTempVariable$var108$25_8 = traceTempVariable$var89$25_5;
																										if((0 < numTimeSteps)) {
																											for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																												if((0 < numTimeSteps)) {
																													if((j$var86 == j$var107)) {
																														if(!guard$sample34multinomial124[((t - 0) / 1)]) {
																															// The body will execute, so should not be executed again
																															guard$sample34multinomial124[((t - 0) / 1)] = true;
																															
																															// Processing sample task 125 of consumer random variable null.
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
																																				double[] cv$temp$8$weekly_rates;
																																				{
																																					cv$temp$8$weekly_rates = weekly_rates[((t - 0) / 1)];
																																				}
																																				int cv$temp$9$arrivals;
																																				{
																																					cv$temp$9$arrivals = arrivals[((t - 0) / 1)];
																																				}
																																				
																																				// Record the probability of sample task 125 generating output with current configuration.
																																				if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$8$weekly_rates, cv$temp$9$arrivals)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$8$weekly_rates, cv$temp$9$arrivals)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					// If the second value is -infinity.
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$8$weekly_rates, cv$temp$9$arrivals));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$8$weekly_rates, cv$temp$9$arrivals)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$8$weekly_rates, cv$temp$9$arrivals)));
																																				}
																																				
																																				// Recorded the probability of reaching sample task 125 with the current configuration.
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
			double var28 = cv$originalValue;
			ut[var27] = var28;
			
			// Guards to ensure that exped is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 34 and consumer double[] 36.
			{
				if((0 < numTimeSteps)) {
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									{
										exped[j$var33] = Math.exp(ut[j$var33]);
									}
								}
							}
						}
					}
				}
			}
			
			// Guards to ensure that expedNorm is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 34 and consumer double[] 54.
			{
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				boolean[] guard$sample34put61 = guard$sample34put61$global;
				if((0 < numTimeSteps)) {
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
												{
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1)
														// Set the flags to false
														guard$sample34put61[((j$var50 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if((0 < numTimeSteps)) {
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
												if((0 < numTimeSteps)) {
													if((j$var33 == j$var50))
														// Set the flags to false
														guard$sample34put61[((j$var50 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if((0 < numTimeSteps)) {
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
												{
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if(!guard$sample34put61[((j$var50 - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample34put61[((j$var50 - 0) / 1)] = true;
															{
																// Reduction of array exped
																// 
																// A generated name to prevent name collisions if the reduction is implemented more
																// than once in inference and probability code. Initialize the variable to the unit
																// value
																double reduceVar$sum$6 = 0.0;
																
																// For each index in the array to be reduced
																for(int cv$reduction1465Index = 0; cv$reduction1465Index < avail[0].length; cv$reduction1465Index += 1) {
																	// Set the left hand term of the reduction function to the return variable value.
																	double k$var44 = reduceVar$sum$6;
																	
																	// Set the right hand term to a value from the array exped
																	double l$var45 = exped[cv$reduction1465Index];
																	
																	// Execute the reduction function, saving the result into the return value.
																	// 
																	// Copy the result of the reduction into the variable returned by the reduction.
																	reduceVar$sum$6 = (k$var44 + l$var45);
																}
																expedNorm[j$var50] = (exped[j$var50] / (r * reduceVar$sum$6));
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
				if((0 < numTimeSteps)) {
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
												if((0 < numTimeSteps)) {
													if((j$var33 == j$var50)) {
														if(!guard$sample34put61[((j$var50 - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample34put61[((j$var50 - 0) / 1)] = true;
															{
																// Reduction of array exped
																// 
																// A generated name to prevent name collisions if the reduction is implemented more
																// than once in inference and probability code. Initialize the variable to the unit
																// value
																double reduceVar$sum$7 = 0.0;
																
																// For each index in the array to be reduced
																for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1) {
																	// Set the left hand term of the reduction function to the return variable value.
																	double k$var44 = reduceVar$sum$7;
																	
																	// Set the right hand term to a value from the array exped
																	double l$var45 = exped[cv$reduction50Index];
																	
																	// Execute the reduction function, saving the result into the return value.
																	// 
																	// Copy the result of the reduction into the variable returned by the reduction.
																	reduceVar$sum$7 = (k$var44 + l$var45);
																}
																expedNorm[j$var50] = (exped[j$var50] / (r * reduceVar$sum$7));
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
			
			// Guards to ensure that weekly_ut is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 34 and consumer double[] 90.
			{
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				boolean[][] guard$sample34put102 = guard$sample34put102$global;
				if((0 < numTimeSteps)) {
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
												{
													if((0 < numTimeSteps)) {
														for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
															if((0 < numTimeSteps)) {
																if((0 < numTimeSteps)) {
																	for(int t = 0; t < numTimeSteps; t += 1) {
																		if((0 < numTimeSteps)) {
																			for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																				if((0 < numTimeSteps)) {
																					if(avail[t][j$var86]) {
																						if((j$var50 == j$var86))
																							// Set the flags to false
																							guard$sample34put102[((t - 0) / 1)][((j$var86 - 0) / 1)] = false;
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
				if((0 < numTimeSteps)) {
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
												if((0 < numTimeSteps)) {
													if((j$var33 == j$var50)) {
														if((0 < numTimeSteps)) {
															if((0 < numTimeSteps)) {
																for(int t = 0; t < numTimeSteps; t += 1) {
																	if((0 < numTimeSteps)) {
																		for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																			if((0 < numTimeSteps)) {
																				if(avail[t][j$var86]) {
																					if((j$var50 == j$var86))
																						// Set the flags to false
																						guard$sample34put102[((t - 0) / 1)][((j$var86 - 0) / 1)] = false;
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
				if((0 < numTimeSteps)) {
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
												{
													if((0 < numTimeSteps)) {
														for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
															if((0 < numTimeSteps)) {
																if((0 < numTimeSteps)) {
																	for(int t = 0; t < numTimeSteps; t += 1) {
																		if((0 < numTimeSteps)) {
																			for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																				if((0 < numTimeSteps)) {
																					if(avail[t][j$var86]) {
																						if((j$var50 == j$var86)) {
																							if(!guard$sample34put102[((t - 0) / 1)][((j$var86 - 0) / 1)]) {
																								// The body will execute, so should not be executed again
																								guard$sample34put102[((t - 0) / 1)][((j$var86 - 0) / 1)] = true;
																								{
																									weekly_ut[((t - 0) / 1)][j$var86] = expedNorm[j$var86];
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
				if((0 < numTimeSteps)) {
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
												if((0 < numTimeSteps)) {
													if((j$var33 == j$var50)) {
														if((0 < numTimeSteps)) {
															if((0 < numTimeSteps)) {
																for(int t = 0; t < numTimeSteps; t += 1) {
																	if((0 < numTimeSteps)) {
																		for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																			if((0 < numTimeSteps)) {
																				if(avail[t][j$var86]) {
																					if((j$var50 == j$var86)) {
																						if(!guard$sample34put102[((t - 0) / 1)][((j$var86 - 0) / 1)]) {
																							// The body will execute, so should not be executed again
																							guard$sample34put102[((t - 0) / 1)][((j$var86 - 0) / 1)] = true;
																							{
																								weekly_ut[((t - 0) / 1)][j$var86] = expedNorm[j$var86];
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
			
			// Guards to ensure that weekly_rates is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 34 and consumer double[] 110.
			{
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				boolean[][] guard$sample34put123 = guard$sample34put123$global;
				if((0 < numTimeSteps)) {
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
												{
													if((0 < numTimeSteps)) {
														for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
															if((0 < numTimeSteps)) {
																if((0 < numTimeSteps)) {
																	for(int t = 0; t < numTimeSteps; t += 1) {
																		if((0 < numTimeSteps)) {
																			for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																				if((0 < numTimeSteps)) {
																					if(avail[t][j$var86]) {
																						if((j$var50 == j$var86)) {
																							if((0 < numTimeSteps)) {
																								if(avail[t][j$var86]) {
																									if((0 < numTimeSteps)) {
																										if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																											{
																												for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1)
																													// Set the flags to false
																													guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)] = false;
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
					}
				}
				if((0 < numTimeSteps)) {
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
												{
													if((0 < numTimeSteps)) {
														for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
															if((0 < numTimeSteps)) {
																if((0 < numTimeSteps)) {
																	for(int t = 0; t < numTimeSteps; t += 1) {
																		if((0 < numTimeSteps)) {
																			for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																				if((0 < numTimeSteps)) {
																					if(avail[t][j$var86]) {
																						if((j$var50 == j$var86)) {
																							if((0 < numTimeSteps)) {
																								if(avail[t][j$var86]) {
																									if((0 < numTimeSteps)) {
																										for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																											if((0 < numTimeSteps)) {
																												if((j$var86 == j$var107))
																													// Set the flags to false
																													guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)] = false;
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
					}
				}
				if((0 < numTimeSteps)) {
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
												if((0 < numTimeSteps)) {
													if((j$var33 == j$var50)) {
														if((0 < numTimeSteps)) {
															if((0 < numTimeSteps)) {
																for(int t = 0; t < numTimeSteps; t += 1) {
																	if((0 < numTimeSteps)) {
																		for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																			if((0 < numTimeSteps)) {
																				if(avail[t][j$var86]) {
																					if((j$var50 == j$var86)) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((0 < numTimeSteps)) {
																									if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																										{
																											for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1)
																												// Set the flags to false
																												guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)] = false;
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
				}
				if((0 < numTimeSteps)) {
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
												if((0 < numTimeSteps)) {
													if((j$var33 == j$var50)) {
														if((0 < numTimeSteps)) {
															if((0 < numTimeSteps)) {
																for(int t = 0; t < numTimeSteps; t += 1) {
																	if((0 < numTimeSteps)) {
																		for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																			if((0 < numTimeSteps)) {
																				if(avail[t][j$var86]) {
																					if((j$var50 == j$var86)) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((0 < numTimeSteps)) {
																									for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																										if((0 < numTimeSteps)) {
																											if((j$var86 == j$var107))
																												// Set the flags to false
																												guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)] = false;
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
				}
				if((0 < numTimeSteps)) {
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
												{
													if((0 < numTimeSteps)) {
														for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
															if((0 < numTimeSteps)) {
																if((0 < numTimeSteps)) {
																	for(int t = 0; t < numTimeSteps; t += 1) {
																		if((0 < numTimeSteps)) {
																			for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																				if((0 < numTimeSteps)) {
																					if(avail[t][j$var86]) {
																						if((j$var50 == j$var86)) {
																							if((0 < numTimeSteps)) {
																								if(avail[t][j$var86]) {
																									if((0 < numTimeSteps)) {
																										if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																											{
																												for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																													if(!guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)]) {
																														// The body will execute, so should not be executed again
																														guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)] = true;
																														{
																															// Reduction of array weekly_ut
																															// 
																															// A generated name to prevent name collisions if the reduction is implemented more
																															// than once in inference and probability code. Initialize the variable to the unit
																															// value
																															double reduceVar$denom$6 = 0.0;
																															
																															// For each index in the array to be reduced
																															for(int cv$reduction1904Index = 0; cv$reduction1904Index < (avail[0].length + 1); cv$reduction1904Index += 1) {
																																// Set the left hand term of the reduction function to the return variable value.
																																double k$var99 = reduceVar$denom$6;
																																
																																// Set the right hand term to a value from the array weekly_ut
																																double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction1904Index];
																																
																																// Execute the reduction function, saving the result into the return value.
																																// 
																																// Copy the result of the reduction into the variable returned by the reduction.
																																reduceVar$denom$6 = (k$var99 + l$var100);
																															}
																															weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$6);
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
								}
							}
						}
					}
				}
				if((0 < numTimeSteps)) {
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
												{
													if((0 < numTimeSteps)) {
														for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
															if((0 < numTimeSteps)) {
																if((0 < numTimeSteps)) {
																	for(int t = 0; t < numTimeSteps; t += 1) {
																		if((0 < numTimeSteps)) {
																			for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																				if((0 < numTimeSteps)) {
																					if(avail[t][j$var86]) {
																						if((j$var50 == j$var86)) {
																							if((0 < numTimeSteps)) {
																								if(avail[t][j$var86]) {
																									if((0 < numTimeSteps)) {
																										for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																											if((0 < numTimeSteps)) {
																												if((j$var86 == j$var107)) {
																													if(!guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)]) {
																														// The body will execute, so should not be executed again
																														guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)] = true;
																														{
																															// Reduction of array weekly_ut
																															// 
																															// A generated name to prevent name collisions if the reduction is implemented more
																															// than once in inference and probability code. Initialize the variable to the unit
																															// value
																															double reduceVar$denom$7 = 0.0;
																															
																															// For each index in the array to be reduced
																															for(int cv$reduction111Index = 0; cv$reduction111Index < (avail[0].length + 1); cv$reduction111Index += 1) {
																																// Set the left hand term of the reduction function to the return variable value.
																																double k$var99 = reduceVar$denom$7;
																																
																																// Set the right hand term to a value from the array weekly_ut
																																double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction111Index];
																																
																																// Execute the reduction function, saving the result into the return value.
																																// 
																																// Copy the result of the reduction into the variable returned by the reduction.
																																reduceVar$denom$7 = (k$var99 + l$var100);
																															}
																															weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$7);
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
								}
							}
						}
					}
				}
				if((0 < numTimeSteps)) {
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
												if((0 < numTimeSteps)) {
													if((j$var33 == j$var50)) {
														if((0 < numTimeSteps)) {
															if((0 < numTimeSteps)) {
																for(int t = 0; t < numTimeSteps; t += 1) {
																	if((0 < numTimeSteps)) {
																		for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																			if((0 < numTimeSteps)) {
																				if(avail[t][j$var86]) {
																					if((j$var50 == j$var86)) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((0 < numTimeSteps)) {
																									if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																										{
																											for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																												if(!guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)]) {
																													// The body will execute, so should not be executed again
																													guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)] = true;
																													{
																														// Reduction of array weekly_ut
																														// 
																														// A generated name to prevent name collisions if the reduction is implemented more
																														// than once in inference and probability code. Initialize the variable to the unit
																														// value
																														double reduceVar$denom$8 = 0.0;
																														
																														// For each index in the array to be reduced
																														for(int cv$reduction2004Index = 0; cv$reduction2004Index < (avail[0].length + 1); cv$reduction2004Index += 1) {
																															// Set the left hand term of the reduction function to the return variable value.
																															double k$var99 = reduceVar$denom$8;
																															
																															// Set the right hand term to a value from the array weekly_ut
																															double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction2004Index];
																															
																															// Execute the reduction function, saving the result into the return value.
																															// 
																															// Copy the result of the reduction into the variable returned by the reduction.
																															reduceVar$denom$8 = (k$var99 + l$var100);
																														}
																														weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$8);
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
							}
						}
					}
				}
				if((0 < numTimeSteps)) {
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
												if((0 < numTimeSteps)) {
													if((j$var33 == j$var50)) {
														if((0 < numTimeSteps)) {
															if((0 < numTimeSteps)) {
																for(int t = 0; t < numTimeSteps; t += 1) {
																	if((0 < numTimeSteps)) {
																		for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																			if((0 < numTimeSteps)) {
																				if(avail[t][j$var86]) {
																					if((j$var50 == j$var86)) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((0 < numTimeSteps)) {
																									for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																										if((0 < numTimeSteps)) {
																											if((j$var86 == j$var107)) {
																												if(!guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)]) {
																													// The body will execute, so should not be executed again
																													guard$sample34put123[((t - 0) / 1)][((j$var107 - 0) / 1)] = true;
																													{
																														// Reduction of array weekly_ut
																														// 
																														// A generated name to prevent name collisions if the reduction is implemented more
																														// than once in inference and probability code. Initialize the variable to the unit
																														// value
																														double reduceVar$denom$9 = 0.0;
																														
																														// For each index in the array to be reduced
																														for(int cv$reduction111Index = 0; cv$reduction111Index < (avail[0].length + 1); cv$reduction111Index += 1) {
																															// Set the left hand term of the reduction function to the return variable value.
																															double k$var99 = reduceVar$denom$9;
																															
																															// Set the right hand term to a value from the array weekly_ut
																															double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction111Index];
																															
																															// Execute the reduction function, saving the result into the return value.
																															// 
																															// Copy the result of the reduction into the variable returned by the reduction.
																															reduceVar$denom$9 = (k$var99 + l$var100);
																														}
																														weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$9);
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
							}
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 88 drawn from Gamma 77. Inference was performed using a Gamma to
	// Poisson conjugate prior.
	private final void sample88(int t) {
		// Variable to store the sum of all the samples from consuming random variables.
		double cv$sum = 0.0;
		
		// Variable to record the number of samples from consuming random variables.
		int cv$count = 0;
		{
			// Processing random variable 79.
			{
				{
					// Processing sample task 90 of consumer random variable null.
					{
						{
							{
								{
									{
										// Reduction of array null
										// 
										// A generated name to prevent name collisions if the reduction is implemented more
										// than once in inference and probability code. Initialize the variable to the unit
										// value
										int reduceVar$numSales$0 = 0;
										
										// For each index in the array to be reduced
										for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1) {
											// Set the left hand term of the reduction function to the return variable value.
											int k$var69 = reduceVar$numSales$0;
											
											// Set the right hand term to a value from the array var64
											int l$var70 = ObsSales[t][cv$reduction78Index];
											
											// Execute the reduction function, saving the result into the return value.
											// 
											// Copy the result of the reduction into the variable returned by the reduction.
											reduceVar$numSales$0 = (k$var69 + l$var70);
										}
										
										// Add the value of a sample from consuming random variable var79 to the inference
										// state.
										cv$sum = (cv$sum + (arrivals[((t - 0) / 1)] - reduceVar$numSales$0));
										cv$count = (cv$count + 1);
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Write out the new value of the sample.
		lambda[((t - 0) / 1)] = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 90 drawn from Poisson 79. Inference was performed using Metropolis-Hastings.
	private final void sample90(int t) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		{
			// Metropolis-Hastings
			cv$noStates = Math.max(cv$noStates, 2);
		}
		
		// Reduction of array null
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		int reduceVar$numSales$1 = 0;
		
		// For each index in the array to be reduced
		for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			int k$var69 = reduceVar$numSales$1;
			
			// Set the right hand term to a value from the array var64
			int l$var70 = ObsSales[t][cv$reduction78Index];
			
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			reduceVar$numSales$1 = (k$var69 + l$var70);
		}
		
		// The original value of the sample
		int cv$originalValue = (arrivals[((t - 0) / 1)] - reduceVar$numSales$1);
		
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability = 0.0;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		
		// Ensure the variance is at least 1
		if((cv$var < 1.0))
			cv$var = 1.0;
		
		// An offset for the current value
		double cv$offset = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		
		// Make sure the offset is not 0
		cv$offset = ((cv$offset <= 0.0)?(cv$offset - 1):(cv$offset + 1));
		
		// The proposed new value for the sample
		int cv$proposedValue = (cv$originalValue + (int)cv$offset);
		
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
			int cv$currentValue;
			if((cv$valuePos == 0))
				// Set the current value to the current state of the tree.
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				
				// Update Sample and intermediate values
				{
					// Write out the value of the sample to a temporary variable prior to updating the
					// intermediate variables.
					int var80 = cv$proposedValue;
					
					// Reduction of array null
					// 
					// A generated name to prevent name collisions if the reduction is implemented more
					// than once in inference and probability code. Initialize the variable to the unit
					// value
					int reduceVar$numSales$2 = 0;
					
					// For each index in the array to be reduced
					for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1) {
						// Set the left hand term of the reduction function to the return variable value.
						int k$var69 = reduceVar$numSales$2;
						
						// Set the right hand term to a value from the array var64
						int l$var70 = ObsSales[t][cv$reduction78Index];
						
						// Execute the reduction function, saving the result into the return value.
						// 
						// Copy the result of the reduction into the variable returned by the reduction.
						reduceVar$numSales$2 = (k$var69 + l$var70);
					}
					
					// Write out the new sample value.
					arrivals[((t - 0) / 1)] = (reduceVar$numSales$2 + cv$currentValue);
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$lambda;
				{
					cv$temp$0$lambda = lambda[((t - 0) / 1)];
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$currentValue, cv$temp$0$lambda));
				
				// Processing random variable 111.
				{
					{
						int traceTempVariable$arrivals$1_1 = arrivals[((t - 0) / 1)];
						
						// Processing sample task 125 of consumer random variable null.
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
											double[] cv$temp$1$weekly_rates;
											{
												cv$temp$1$weekly_rates = weekly_rates[((t - 0) / 1)];
											}
											int cv$temp$2$arrivals;
											{
												cv$temp$2$arrivals = traceTempVariable$arrivals$1_1;
											}
											
											// Record the probability of sample task 125 generating output with current configuration.
											if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$arrivals)) < cv$accumulatedConsumerProbabilities))
												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$arrivals)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
											else {
												// If the second value is -infinity.
												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$arrivals));
												else
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$arrivals)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$arrivals)));
											}
											
											// Recorded the probability of reaching sample task 125 with the current configuration.
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
			int var80 = cv$originalValue;
			
			// Reduction of array null
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			int reduceVar$numSales$3 = 0;
			
			// For each index in the array to be reduced
			for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				int k$var69 = reduceVar$numSales$3;
				
				// Set the right hand term to a value from the array var64
				int l$var70 = ObsSales[t][cv$reduction78Index];
				
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$numSales$3 = (k$var69 + l$var70);
			}
			
			// Write out the new sample value.
			arrivals[((t - 0) / 1)] = (reduceVar$numSales$3 + var80);
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for guard$sample34put61$global
		{
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var50 = 0;
			if((0 < avail.length))
				cv$max_j$var50 = Math.max(cv$max_j$var50, ((avail[0].length - 0) / 1));
			
			// Allocation of guard$sample34put61$global for single threaded execution
			guard$sample34put61$global = new boolean[cv$max_j$var50];
		}
		
		// Constructor for guard$sample34put102$global
		{
			// Calculate the largest index of t that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_t = 0;
			
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var86 = 0;
			if((0 < avail.length)) {
				for(int t = 0; t < avail.length; t += 1)
					cv$max_j$var86 = Math.max(cv$max_j$var86, ((avail[0].length - 0) / 1));
				cv$max_t = Math.max(cv$max_t, ((avail.length - 0) / 1));
			}
			
			// Allocation of guard$sample34put102$global for single threaded execution
			guard$sample34put102$global = new boolean[cv$max_t][cv$max_j$var86];
		}
		
		// Constructor for guard$sample34put123$global
		{
			// Calculate the largest index of t that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_t = 0;
			
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var107 = 0;
			if((0 < avail.length)) {
				for(int t = 0; t < avail.length; t += 1)
					cv$max_j$var107 = Math.max(cv$max_j$var107, (((avail[0].length + 1) - 0) / 1));
				cv$max_t = Math.max(cv$max_t, ((avail.length - 0) / 1));
			}
			
			// Allocation of guard$sample34put123$global for single threaded execution
			guard$sample34put123$global = new boolean[cv$max_t][cv$max_j$var107];
		}
		
		// Constructor for guard$sample34multinomial124$global
		{
			// Calculate the largest index of t that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_t = 0;
			if((0 < avail.length))
				cv$max_t = Math.max(cv$max_t, ((avail.length - 0) / 1));
			
			// Allocation of guard$sample34multinomial124$global for single threaded execution
			guard$sample34multinomial124$global = new boolean[cv$max_t];
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for ut
		{
			if((0 < numTimeSteps))
				ut = new double[avail[0].length];
		}
		
		// Constructor for exped
		{
			if((0 < numTimeSteps))
				exped = new double[avail[0].length];
		}
		
		// Constructor for expedNorm
		{
			if((0 < numTimeSteps))
				expedNorm = new double[avail[0].length];
		}
		
		// Constructor for sales
		{
			if((0 < numTimeSteps)) {
				sales = new int[avail.length][];
				for(int var58 = 0; var58 < avail.length; var58 += 1)
					sales[var58] = new int[avail[0].length];
			}
		}
		
		// If lambda has not been set already allocate space.
		if(!setFlag$lambda) {
			// Constructor for lambda
			{
				lambda = new double[((((avail.length - 1) - 0) / 1) + 1)];
			}
		}
		
		// If arrivals has not been set already allocate space.
		if(!setFlag$arrivals) {
			// Constructor for arrivals
			{
				arrivals = new int[((((avail.length - 1) - 0) / 1) + 1)];
			}
		}
		
		// Constructor for weekly_rates
		{
			if((0 < avail.length)) {
				for(int t = 0; t < avail.length; t += 1)
					weekly_rates[((t - 0) / 1)] = new double[(avail[0].length + 1)];
			}
			weekly_rates = new double[((((avail.length - 1) - 0) / 1) + 1)][];
		}
		
		// Constructor for weekly_ut
		{
			if((0 < avail.length)) {
				for(int t = 0; t < avail.length; t += 1)
					weekly_ut[((t - 0) / 1)] = new double[(avail[0].length + 1)];
			}
			weekly_ut = new double[((((avail.length - 1) - 0) / 1) + 1)][];
		}
		
		// If weekly_sales has not been set already allocate space.
		if(!setFlag$weekly_sales) {
			// Constructor for weekly_sales
			{
				if((0 < avail.length)) {
					for(int t = 0; t < avail.length; t += 1)
						weekly_sales[((t - 0) / 1)] = new int[(avail[0].length + 1)];
				}
				weekly_sales = new int[((((avail.length - 1) - 0) / 1) + 1)][];
			}
		}
		
		// Constructor for logProbability$sample34
		{
			logProbability$sample34 = new double[((((avail[0].length - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample125
		{
			logProbability$sample125 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if((0 < numTimeSteps)) {
			for(int var27 = 0; var27 < avail[0].length; var27 += 1) {
				if(!fixedFlag$sample34)
					ut[var27] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
			}
			for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
				if(!fixedFlag$sample34)
					exped[j$var33] = Math.exp(ut[j$var33]);
			}
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$8 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k$var44 = reduceVar$sum$8;
				
				// Set the right hand term to a value from the array exped
				double l$var45 = exped[cv$reduction50Index];
				
				// Execute the reduction function, saving the result into the return value.
				if(!fixedFlag$sample34)
					// Copy the result of the reduction into the variable returned by the reduction.
					reduceVar$sum$8 = (k$var44 + l$var45);
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				if(!fixedFlag$sample34)
					expedNorm[j$var50] = (exped[j$var50] / (r * reduceVar$sum$8));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(!fixedFlag$sample88)
					lambda[((t - 0) / 1)] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
				
				// Reduction of array null
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				int reduceVar$numSales$5 = 0;
				
				// For each index in the array to be reduced
				for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1) {
					// Set the left hand term of the reduction function to the return variable value.
					int k$var69 = reduceVar$numSales$5;
					
					// Set the right hand term to a value from the array var64
					int l$var70 = ObsSales[t][cv$reduction78Index];
					
					// Execute the reduction function, saving the result into the return value.
					if(!fixedFlag$sample90)
						// Copy the result of the reduction into the variable returned by the reduction.
						reduceVar$numSales$5 = (k$var69 + l$var70);
				}
				if(!fixedFlag$sample90)
					arrivals[((t - 0) / 1)] = (reduceVar$numSales$5 + DistributionSampling.samplePoisson(RNG$, lambda[((t - 0) / 1)]));
				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
					if(avail[t][j$var86]) {
						if(!fixedFlag$sample34)
							weekly_ut[((t - 0) / 1)][j$var86] = expedNorm[j$var86];
					} else
						weekly_ut[((t - 0) / 1)][j$var86] = 0.0;
				}
				weekly_ut[((t - 0) / 1)][avail[0].length] = 1.0;
				
				// Reduction of array weekly_ut
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$denom$10 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction111Index = 0; cv$reduction111Index < (avail[0].length + 1); cv$reduction111Index += 1) {
					// Set the left hand term of the reduction function to the return variable value.
					double k$var99 = reduceVar$denom$10;
					
					// Set the right hand term to a value from the array weekly_ut
					double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction111Index];
					
					// Execute the reduction function, saving the result into the return value.
					if(!fixedFlag$sample34)
						// Copy the result of the reduction into the variable returned by the reduction.
						reduceVar$denom$10 = (k$var99 + l$var100);
				}
				for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
					if(!fixedFlag$sample34)
						weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$10);
				}
				if(!fixedFlag$sample125)
					DistributionSampling.sampleMultinomial(RNG$, weekly_rates[((t - 0) / 1)], arrivals[((t - 0) / 1)], weekly_sales[((t - 0) / 1)]);
				int[] observed_weekly_sales = sales[t];
				for(int j$var116 = 0; j$var116 < avail[0].length; j$var116 += 1) {
					if(!fixedFlag$sample125)
						observed_weekly_sales[j$var116] = weekly_sales[((t - 0) / 1)][j$var116];
				}
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if((0 < numTimeSteps)) {
			for(int var27 = 0; var27 < avail[0].length; var27 += 1) {
				if(!fixedFlag$sample34)
					ut[var27] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
			}
			for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
				if(!fixedFlag$sample34)
					exped[j$var33] = Math.exp(ut[j$var33]);
			}
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$10 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k$var44 = reduceVar$sum$10;
				
				// Set the right hand term to a value from the array exped
				double l$var45 = exped[cv$reduction50Index];
				
				// Execute the reduction function, saving the result into the return value.
				if(!fixedFlag$sample34)
					// Copy the result of the reduction into the variable returned by the reduction.
					reduceVar$sum$10 = (k$var44 + l$var45);
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				if(!fixedFlag$sample34)
					expedNorm[j$var50] = (exped[j$var50] / (r * reduceVar$sum$10));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(!fixedFlag$sample88)
					lambda[((t - 0) / 1)] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
				
				// Reduction of array null
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				int reduceVar$numSales$7 = 0;
				
				// For each index in the array to be reduced
				for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1) {
					// Set the left hand term of the reduction function to the return variable value.
					int k$var69 = reduceVar$numSales$7;
					
					// Set the right hand term to a value from the array var64
					int l$var70 = ObsSales[t][cv$reduction78Index];
					
					// Execute the reduction function, saving the result into the return value.
					if(!fixedFlag$sample90)
						// Copy the result of the reduction into the variable returned by the reduction.
						reduceVar$numSales$7 = (k$var69 + l$var70);
				}
				if(!fixedFlag$sample90)
					arrivals[((t - 0) / 1)] = (reduceVar$numSales$7 + DistributionSampling.samplePoisson(RNG$, lambda[((t - 0) / 1)]));
				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
					if(avail[t][j$var86]) {
						if(!fixedFlag$sample34)
							weekly_ut[((t - 0) / 1)][j$var86] = expedNorm[j$var86];
					} else
						weekly_ut[((t - 0) / 1)][j$var86] = 0.0;
				}
				weekly_ut[((t - 0) / 1)][avail[0].length] = 1.0;
				
				// Reduction of array weekly_ut
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$denom$12 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction111Index = 0; cv$reduction111Index < (avail[0].length + 1); cv$reduction111Index += 1) {
					// Set the left hand term of the reduction function to the return variable value.
					double k$var99 = reduceVar$denom$12;
					
					// Set the right hand term to a value from the array weekly_ut
					double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction111Index];
					
					// Execute the reduction function, saving the result into the return value.
					if(!fixedFlag$sample34)
						// Copy the result of the reduction into the variable returned by the reduction.
						reduceVar$denom$12 = (k$var99 + l$var100);
				}
				for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
					if(!fixedFlag$sample34)
						weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$12);
				}
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if((0 < numTimeSteps)) {
			for(int var27 = 0; var27 < avail[0].length; var27 += 1) {
				if(!fixedFlag$sample34)
					ut[var27] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
			}
			for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
				if(!fixedFlag$sample34)
					exped[j$var33] = Math.exp(ut[j$var33]);
			}
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$9 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k$var44 = reduceVar$sum$9;
				
				// Set the right hand term to a value from the array exped
				double l$var45 = exped[cv$reduction50Index];
				
				// Execute the reduction function, saving the result into the return value.
				if(!fixedFlag$sample34)
					// Copy the result of the reduction into the variable returned by the reduction.
					reduceVar$sum$9 = (k$var44 + l$var45);
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				if(!fixedFlag$sample34)
					expedNorm[j$var50] = (exped[j$var50] / (r * reduceVar$sum$9));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(!fixedFlag$sample88)
					lambda[((t - 0) / 1)] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
				
				// Reduction of array null
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				int reduceVar$numSales$6 = 0;
				
				// For each index in the array to be reduced
				for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1) {
					// Set the left hand term of the reduction function to the return variable value.
					int k$var69 = reduceVar$numSales$6;
					
					// Set the right hand term to a value from the array var64
					int l$var70 = ObsSales[t][cv$reduction78Index];
					
					// Execute the reduction function, saving the result into the return value.
					if(!fixedFlag$sample90)
						// Copy the result of the reduction into the variable returned by the reduction.
						reduceVar$numSales$6 = (k$var69 + l$var70);
				}
				if(!fixedFlag$sample90)
					arrivals[((t - 0) / 1)] = (reduceVar$numSales$6 + DistributionSampling.samplePoisson(RNG$, lambda[((t - 0) / 1)]));
				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
					if(avail[t][j$var86]) {
						if(!fixedFlag$sample34)
							weekly_ut[((t - 0) / 1)][j$var86] = expedNorm[j$var86];
					} else
						weekly_ut[((t - 0) / 1)][j$var86] = 0.0;
				}
				weekly_ut[((t - 0) / 1)][avail[0].length] = 1.0;
				
				// Reduction of array weekly_ut
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$denom$11 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction111Index = 0; cv$reduction111Index < (avail[0].length + 1); cv$reduction111Index += 1) {
					// Set the left hand term of the reduction function to the return variable value.
					double k$var99 = reduceVar$denom$11;
					
					// Set the right hand term to a value from the array weekly_ut
					double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction111Index];
					
					// Execute the reduction function, saving the result into the return value.
					if(!fixedFlag$sample34)
						// Copy the result of the reduction into the variable returned by the reduction.
						reduceVar$denom$11 = (k$var99 + l$var100);
				}
				for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
					if(!fixedFlag$sample34)
						weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$11);
				}
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if((0 < numTimeSteps)) {
				for(int var27 = 0; var27 < avail[0].length; var27 += 1) {
					if(!fixedFlag$sample34)
						sample34(var27);
				}
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(!fixedFlag$sample88)
						sample88(t);
					if(!fixedFlag$sample90)
						sample90(t);
				}
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			if((0 < numTimeSteps)) {
				for(int t = (numTimeSteps - ((((numTimeSteps - 1) - 0) % 1) + 1)); t >= ((0 - 1) + 1); t -= 1) {
					if(!fixedFlag$sample90)
						sample90(t);
					if(!fixedFlag$sample88)
						sample88(t);
				}
				for(int var27 = (avail[0].length - ((((avail[0].length - 1) - 0) % 1) + 1)); var27 >= ((0 - 1) + 1); var27 -= 1) {
					if(!fixedFlag$sample34)
						sample34(var27);
				}
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		r = 0.3;
		numTimeSteps = avail.length;
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
		logProbability$exped = 0.0;
		logProbability$expedNorm = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$ut = 0.0;
		logProbability$weekly_rates = 0.0;
		if(!fixedProbFlag$sample34) {
			if((0 < numTimeSteps)) {
				for(int var27 = 0; var27 < avail[0].length; var27 += 1)
					logProbability$sample34[((var27 - 0) / 1)] = 0.0;
			}
		}
		logProbability$var77 = 0.0;
		if(!fixedProbFlag$sample88)
			logProbability$lambda = 0.0;
		logProbability$var79 = 0.0;
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample90)
			logProbability$var80 = 0.0;
		logProbability$var111 = 0.0;
		logProbability$sales = 0.0;
		logProbability$weekly_sales = 0.0;
		if(!fixedProbFlag$sample125) {
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample125[((t - 0) / 1)] = 0.0;
			}
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
		if(fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(fixedFlag$sample88)
			logProbabilityValue$sample88();
		if(fixedFlag$sample90)
			logProbabilityValue$sample90();
		logProbabilityValue$sample125();
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
		logProbabilityValue$sample34();
		logProbabilityValue$sample88();
		logProbabilityValue$sample90();
		logProbabilityValue$sample125();
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
		logProbabilityValue$sample34();
		logProbabilityValue$sample88();
		logProbabilityValue$sample90();
		logProbabilityValue$sample125();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if((0 < numTimeSteps)) {
			for(int var27 = 0; var27 < avail[0].length; var27 += 1) {
				if(!fixedFlag$sample34)
					ut[var27] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
			}
			for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
				if(!fixedFlag$sample34)
					exped[j$var33] = Math.exp(ut[j$var33]);
			}
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$11 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k$var44 = reduceVar$sum$11;
				
				// Set the right hand term to a value from the array exped
				double l$var45 = exped[cv$reduction50Index];
				
				// Execute the reduction function, saving the result into the return value.
				if(!fixedFlag$sample34)
					// Copy the result of the reduction into the variable returned by the reduction.
					reduceVar$sum$11 = (k$var44 + l$var45);
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				if(!fixedFlag$sample34)
					expedNorm[j$var50] = (exped[j$var50] / (r * reduceVar$sum$11));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(!fixedFlag$sample88)
					lambda[((t - 0) / 1)] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
				
				// Reduction of array null
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				int reduceVar$numSales$8 = 0;
				
				// For each index in the array to be reduced
				for(int cv$reduction78Index = 0; cv$reduction78Index < ObsSales[t].length; cv$reduction78Index += 1) {
					// Set the left hand term of the reduction function to the return variable value.
					int k$var69 = reduceVar$numSales$8;
					
					// Set the right hand term to a value from the array var64
					int l$var70 = ObsSales[t][cv$reduction78Index];
					
					// Execute the reduction function, saving the result into the return value.
					if(!fixedFlag$sample90)
						// Copy the result of the reduction into the variable returned by the reduction.
						reduceVar$numSales$8 = (k$var69 + l$var70);
				}
				if(!fixedFlag$sample90)
					arrivals[((t - 0) / 1)] = (reduceVar$numSales$8 + DistributionSampling.samplePoisson(RNG$, lambda[((t - 0) / 1)]));
				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
					if(avail[t][j$var86]) {
						if(!fixedFlag$sample34)
							weekly_ut[((t - 0) / 1)][j$var86] = expedNorm[j$var86];
					} else
						weekly_ut[((t - 0) / 1)][j$var86] = 0.0;
				}
				weekly_ut[((t - 0) / 1)][avail[0].length] = 1.0;
				
				// Reduction of array weekly_ut
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$denom$13 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction111Index = 0; cv$reduction111Index < (avail[0].length + 1); cv$reduction111Index += 1) {
					// Set the left hand term of the reduction function to the return variable value.
					double k$var99 = reduceVar$denom$13;
					
					// Set the right hand term to a value from the array weekly_ut
					double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction111Index];
					
					// Execute the reduction function, saving the result into the return value.
					if(!fixedFlag$sample34)
						// Copy the result of the reduction into the variable returned by the reduction.
						reduceVar$denom$13 = (k$var99 + l$var100);
				}
				for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
					if(!fixedFlag$sample34)
						weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$13);
				}
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
		if((0 < numTimeSteps)) {
			{
				// Deep copy between arrays
				int[][] cv$source1 = ObsSales;
				int[][] cv$target1 = sales;
				int cv$length1 = cv$target1.length;
				for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
					int[] cv$source2 = cv$source1[cv$index1];
					int[] cv$target2 = cv$target1[cv$index1];
					int cv$length2 = cv$target2.length;
					for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
						cv$target2[cv$index2] = cv$source2[cv$index2];
				}
			}
			for(int t = (numTimeSteps - ((((numTimeSteps - 1) - 0) % 1) + 1)); t >= ((0 - 1) + 1); t -= 1) {
				int[] observed_weekly_sales;
				observed_weekly_sales = sales[t];
				for(int j$var116 = (avail[0].length - ((((avail[0].length - 1) - 0) % 1) + 1)); j$var116 >= ((0 - 1) + 1); j$var116 -= 1)
					weekly_sales[((t - 0) / 1)][j$var116] = observed_weekly_sales[j$var116];
			}
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		if((0 < numTimeSteps)) {
			for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
				if(true)
					exped[j$var33] = Math.exp(ut[j$var33]);
			}
			
			// Reduction of array exped
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$sum$12 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k$var44 = reduceVar$sum$12;
				
				// Set the right hand term to a value from the array exped
				double l$var45 = exped[cv$reduction50Index];
				
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$12 = (k$var44 + l$var45);
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				if(true)
					expedNorm[j$var50] = (exped[j$var50] / (r * reduceVar$sum$12));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
					if(avail[t][j$var86]) {
						if(true)
							weekly_ut[((t - 0) / 1)][j$var86] = expedNorm[j$var86];
					}
				}
				
				// Reduction of array weekly_ut
				// 
				// A generated name to prevent name collisions if the reduction is implemented more
				// than once in inference and probability code. Initialize the variable to the unit
				// value
				double reduceVar$denom$14 = 0.0;
				
				// For each index in the array to be reduced
				for(int cv$reduction111Index = 0; cv$reduction111Index < (avail[0].length + 1); cv$reduction111Index += 1) {
					// Set the left hand term of the reduction function to the return variable value.
					double k$var99 = reduceVar$denom$14;
					
					// Set the right hand term to a value from the array weekly_ut
					double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction111Index];
					
					// Execute the reduction function, saving the result into the return value.
					// 
					// Copy the result of the reduction into the variable returned by the reduction.
					reduceVar$denom$14 = (k$var99 + l$var100);
				}
				for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
					if(true)
						weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$14);
				}
				if(setFlag$weekly_sales) {
					int[] observed_weekly_sales = sales[t];
					for(int j$var116 = 0; j$var116 < avail[0].length; j$var116 += 1)
						observed_weekly_sales[j$var116] = weekly_sales[((t - 0) / 1)][j$var116];
				}
			}
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n/*\n * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n */\npackage org.sandwood.compiler.tests.parser;\n\nmodel Vulcano2012basicDG(int[][] ObsSales, boolean[][] avail) {\n   // avail is the availability matrix, numTimeSteps-by-numProducts\n   // r is the normalization constant here, number between 0 and 1. \"Relative appeal of the outside option\"\n   double r = 0.3;\n    \n   int numTimeSteps = avail.length;\n   if(numTimeSteps > 0) {\n      int numProducts = avail[0].length;\n\n      // draw utilities\n      double[] ut = gaussian(0, 1).sample(numProducts);\n\n      //exponentiate right here (in the non-basic models move to the for loop)\n      double[] exped = new double[numProducts];\n      for(int j : [0..numProducts))\n         exped[j] = exp(ut[j]);\n\n      //Choices includes the choice to not buy anything.\n      int numChoices = numProducts + 1;\n\n      //now normalize\n      double[] expedNorm = new double[numProducts];\n      double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n      for(int j : [0..numProducts))\n         expedNorm[j] = exped[j]/(r*sum);\n\n      int[][] sales = new int[numTimeSteps][numProducts];\n\n      for (int t:[0..numTimeSteps)){\n         // Calculate the number of purchases made.\n         int numSales = reduce(ObsSales[t], 0, (k, l) -> { return k + l; });\n\n         // prior for the distribution of lambda for arrivals. These can be \n         // supplied as a vector if RGBU has some estimates, or just use some priors.\n         public double lambda = gamma(10,10).sample();\n         public int arrivals = numSales + poisson(lambda).sample();\n\n         // for each period t calculate choice probabilities and sales\n         double[] weekly_rates = new double[numChoices];\n         double[] weekly_ut = new double[numChoices];\n\n         for(int j : [0..numProducts)) {\n            if(avail[t][j])\n               weekly_ut[j] = expedNorm[j];\n            else\n               weekly_ut[j] = 0.0;\n         }\n         // Moved v_0 to the end of the array to keep indexing consistent everywhere else in \n         // the model and delayed the assignment of the value 1 to here. None of this is a \n         // sandwood requirement, I just thought it made the model eaiser to follow.\n         weekly_ut[numProducts] = 1.0;\n\n         double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n\n         for(int j : [0..numProducts]) \n            weekly_rates[j] = weekly_ut[j]/denom ;\n\n         int[] weekly_sales = multinomial(weekly_rates, arrivals).sample();\n\n         //getting rid of the no purchase observation (last one in the vector of weekly_sales)\n         int[] observed_weekly_sales = sales[t];\n         for (int j : [0..numProducts))\n            observed_weekly_sales[j] = weekly_sales[j] ;\n      }\n      // assert that generated sales match observed sales\n      sales.observe(ObsSales);\n   }\n}";
	}
}