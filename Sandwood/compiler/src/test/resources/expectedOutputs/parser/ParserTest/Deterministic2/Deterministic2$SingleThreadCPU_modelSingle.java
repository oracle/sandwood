package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Deterministic2$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Deterministic2$CoreInterface {
	
	// Declare the variables for the model.
	private int[] a;
	private int[] b;
	private double[] cv$distributionAccumulator$var53;
	private double[] cv$var29$countGlobal;
	private double[] cv$var54$stateProbabilityGlobal;
	private double[][] distribution$sample55;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample55 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample55 = false;
	private boolean fixedProbFlag$sample75 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$a;
	private double logProbability$b;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample55;
	private double logProbability$var17;
	private double logProbability$var29;
	private double logProbability$var53;
	private double logProbability$var73;
	private double logProbability$var74;
	private double[][] m;
	private int n;
	private int states;
	private boolean system$gibbsForward = true;
	private double[] v;

	public Deterministic2$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for a.
	@Override
	public final int[] get$a() {
		return a;
	}

	// Setter for a.
	@Override
	public final void set$a(int[] cv$value) {
		// Set flags for all the side effects of a including if probabilities need to be updated.
		// Set a
		a = cv$value;
		
		// Unset the fixed probability flag for sample 55 as it depends on a.
		fixedProbFlag$sample55 = false;
		
		// Unset the fixed probability flag for sample 75 as it depends on a.
		fixedProbFlag$sample75 = false;
	}

	// Getter for b.
	@Override
	public final int[] get$b() {
		return b;
	}

	// Getter for distribution$sample55.
	@Override
	public final double[][] get$distribution$sample55() {
		return distribution$sample55;
	}

	// Setter for distribution$sample55.
	@Override
	public final void set$distribution$sample55(double[][] cv$value) {
		// Set distribution$sample55
		distribution$sample55 = cv$value;
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
		
		// Should the probability of sample 55 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample55 = (fixedFlag$sample29 && fixedProbFlag$sample55);
	}

	// Getter for fixedFlag$sample55.
	@Override
	public final boolean get$fixedFlag$sample55() {
		return fixedFlag$sample55;
	}

	// Setter for fixedFlag$sample55.
	@Override
	public final void set$fixedFlag$sample55(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample55 including if probabilities
		// need to be updated.
		fixedFlag$sample55 = cv$value;
		
		// Should the probability of sample 55 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample55 = (fixedFlag$sample55 && fixedProbFlag$sample55);
		
		// Should the probability of sample 75 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample75 = (fixedFlag$sample55 && fixedProbFlag$sample75);
	}

	// Getter for flips.
	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	// Getter for flipsMeasured.
	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	// Setter for flipsMeasured.
	@Override
	public final void set$flipsMeasured(boolean[] cv$value) {
		// Set flipsMeasured
		flipsMeasured = cv$value;
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

	// Getter for logProbability$a.
	@Override
	public final double get$logProbability$a() {
		return logProbability$a;
	}

	// Getter for logProbability$b.
	@Override
	public final double get$logProbability$b() {
		return logProbability$b;
	}

	// Getter for logProbability$flips.
	@Override
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	// Getter for logProbability$m.
	@Override
	public final double get$logProbability$m() {
		return logProbability$m;
	}

	// Getter for m.
	@Override
	public final double[][] get$m() {
		return m;
	}

	// Setter for m.
	@Override
	public final void set$m(double[][] cv$value) {
		// Set flags for all the side effects of m including if probabilities need to be updated.
		// Set m
		m = cv$value;
		
		// Unset the fixed probability flag for sample 29 as it depends on m.
		fixedProbFlag$sample29 = false;
		
		// Unset the fixed probability flag for sample 55 as it depends on m.
		fixedProbFlag$sample55 = false;
	}

	// Getter for n.
	@Override
	public final int get$n() {
		return n;
	}

	// Setter for n.
	@Override
	public final void set$n(int cv$value) {
		n = cv$value;
	}

	// Getter for states.
	@Override
	public final int get$states() {
		return states;
	}

	// Getter for v.
	@Override
	public final double[] get$v() {
		return v;
	}

	// Calculate the probability of the samples represented by sample55 using probability
	// distributions.
	private final void logProbabilityDistribution$sample55() {
		// Determine if we need to calculate the values for sample task 55 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample55) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample55) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Look for paths between the variable and the sample task 55 including any distribution
					// values.
					// 
					// Copy of index so that its values can be safely substituted
					int index$i$1 = i$var46;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = a[i$var46];
						
						// Enumerating the possible arguments for Categorical 53.
						int traceTempVariable$var49$3_1 = 0;
						for(int index$i$3_2 = 1; index$i$3_2 < n; index$i$3_2 += 1) {
							if((0 == (index$i$3_2 - 1))) {
								int traceTempVariable$var51$3_3 = traceTempVariable$var49$3_1;
								if((index$i$3_2 == i$var46)) {
									for(int var28 = 0; var28 < states; var28 += 1) {
										if((var28 == b[i$var46])) {
											{
												double[] var52 = m[traceTempVariable$var51$3_3];
												
												// Store the value of the function call, so the function call is only made once.
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY));
												
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
							}
						}
						
						// Enumerating the possible arguments for Categorical 53.
						for(int index$i$5_1 = 1; index$i$5_1 < n; index$i$5_1 += 1) {
							if((index$i$1 == (index$i$5_1 - 1))) {
								int traceTempVariable$var51$5_2 = a[(index$i$5_1 - 1)];
								if((index$i$5_1 == i$var46)) {
									for(int var28 = 0; var28 < states; var28 += 1) {
										if((var28 == b[i$var46])) {
											{
												double[] var52 = m[traceTempVariable$var51$5_2];
												
												// Store the value of the function call, so the function call is only made once.
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY));
												
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
							}
						}
						if(fixedFlag$sample55) {
							for(int index$i$6_1 = 1; index$i$6_1 < n; index$i$6_1 += 1) {
								for(int index$i$6_2 = 1; index$i$6_2 < n; index$i$6_2 += 1) {
									if((index$i$6_1 == (index$i$6_2 - 1))) {
										int traceTempVariable$var51$6_3 = a[(index$i$6_2 - 1)];
										if((index$i$6_2 == i$var46)) {
											for(int var28 = 0; var28 < states; var28 += 1) {
												if((var28 == b[i$var46])) {
													{
														double[] var52 = m[traceTempVariable$var51$6_3];
														
														// Store the value of the function call, so the function call is only made once.
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY));
														
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
									}
								}
							}
						} else {
							for(int index$i$7 = 1; index$i$7 < n; index$i$7 += 1) {
								if(!(index$i$7 == index$i$1)) {
									// Enumerating the possible outputs of Categorical 53.
									for(int index$sample55$8 = 0; index$sample55$8 < states; index$sample55$8 += 1) {
										int distributionTempVariable$var54$10 = index$sample55$8;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample55Value9 = (1.0 * distribution$sample55[((index$i$7 - 1) / 1)][index$sample55$8]);
										for(int index$i$11_1 = 1; index$i$11_1 < n; index$i$11_1 += 1) {
											if((index$i$7 == (index$i$11_1 - 1))) {
												int traceTempVariable$var51$11_2 = a[(index$i$11_1 - 1)];
												if((index$i$11_1 == i$var46)) {
													for(int var28 = 0; var28 < states; var28 += 1) {
														if((var28 == b[i$var46])) {
															{
																double[] var52 = m[traceTempVariable$var51$11_2];
																
																// Store the value of the function call, so the function call is only made once.
																double cv$weightedProbability = (Math.log(cv$probabilitySample55Value9) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																
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
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample55Value9);
															}
														}
													}
												}
											}
										}
									}
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
					
					// Store the sample task probability
					logProbability$sample55[((i$var46 - 1) / 1)] = cv$sampleProbability;
					
					// Guard to ensure that b is only updated once for this probability.
					boolean cv$guard$b = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					// 
					// Copy of index so that its values can be safely substituted
					int index$i$15 = i$var46;
					
					// Looking for a path between Sample 55 and consumer int[] 50.
					{
						for(int index$i$16_1 = 1; index$i$16_1 < n; index$i$16_1 += 1) {
							if((i$var46 == (index$i$16_1 - 1))) {
								// Make sure all the inputs have been fixed so the variable is not a distribution.
								if(fixedFlag$sample55) {
									// If the probability of the variable has not already been updated
									if(!cv$guard$b) {
										// Set the guard so the update is only applied once.
										cv$guard$b = true;
										
										// Update the variable probability
										logProbability$b = (logProbability$b + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var53 = cv$sampleAccumulator;
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(fixedFlag$sample55)
					// Update the variable probability
					logProbability$a = (logProbability$a + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample55)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample55 = (fixedFlag$sample55 && fixedFlag$sample29);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				double cv$sampleValue = logProbability$sample55[((i$var46 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Guard to ensure that b is only updated once for this probability.
				boolean cv$guard$b = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Copy of index so that its values can be safely substituted
				int index$i$17 = i$var46;
				
				// Looking for a path between Sample 55 and consumer int[] 50.
				{
					for(int index$i$18_1 = 1; index$i$18_1 < n; index$i$18_1 += 1) {
						if((i$var46 == (index$i$18_1 - 1))) {
							// Make sure all the inputs have been fixed so the variable is not a distribution.
							if(fixedFlag$sample55) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$b) {
									// Set the guard so the update is only applied once.
									cv$guard$b = true;
									
									// Update the variable probability
									logProbability$b = (logProbability$b + cv$sampleValue);
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var53 = cv$rvAccumulator;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample55)
				// Update the variable probability
				logProbability$a = (logProbability$a + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample75 using probability
	// distributions.
	private final void logProbabilityDistribution$sample75() {
		// Determine if we need to calculate the values for sample task 75 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample75) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 75 including any distribution
				// values.
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flips[j];
					
					// Enumerating the possible arguments for Bernoulli 73.
					int traceTempVariable$var70$2_1 = 0;
					if((0 == (j + 1))) {
						{
							double var72 = (double)(1 / traceTempVariable$var70$2_1);
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var72:(1.0 - var72))));
							
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
					
					// Enumerating the possible arguments for Bernoulli 73.
					if(fixedFlag$sample55) {
						for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
							if((i$var46 == (j + 1))) {
								{
									double var72 = (double)(1 / a[(j + 1)]);
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var72:(1.0 - var72))));
									
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
					} else {
						for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
							if(true) {
								// Enumerating the possible outputs of Categorical 53.
								for(int index$sample55$5 = 0; index$sample55$5 < states; index$sample55$5 += 1) {
									int distributionTempVariable$var54$7 = index$sample55$5;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample55Value6 = (1.0 * distribution$sample55[((i$var46 - 1) / 1)][index$sample55$5]);
									if((i$var46 == (j + 1))) {
										{
											double var72 = (double)(1 / a[(j + 1)]);
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(cv$probabilitySample55Value6) + Math.log((cv$sampleValue?var72:(1.0 - var72))));
											
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
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample55Value6);
										}
									}
								}
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
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var73 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var74 = cv$accumulator;
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample75 = fixedFlag$sample55;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var74;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var73 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
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
			for(int var28 = 0; var28 < states; var28 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double[] cv$sampleValue = m[var28];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, states));
							
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
			logProbability$var17 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var29 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample29 = fixedFlag$sample29;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var29;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var17 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample55 using sampled
	// values.
	private final void logProbabilityValue$sample55() {
		// Determine if we need to calculate the values for sample task 55 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample55) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Copy of index so that its values can be safely substituted
				int index$i$1 = i$var46;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = a[i$var46];
					{
						{
							double[] var52 = m[b[i$var46]];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
				logProbability$sample55[((i$var46 - 1) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that b is only updated once for this probability.
				boolean cv$guard$b = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Copy of index so that its values can be safely substituted
				int index$i$3 = i$var46;
				
				// Looking for a path between Sample 55 and consumer int[] 50.
				{
					for(int index$i$4_1 = 1; index$i$4_1 < n; index$i$4_1 += 1) {
						if((i$var46 == (index$i$4_1 - 1))) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$b) {
								// Set the guard so the update is only applied once.
								cv$guard$b = true;
								
								// Update the variable probability
								logProbability$b = (logProbability$b + cv$sampleProbability);
							}
						}
					}
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var53 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$a = (logProbability$a + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample55 = (fixedFlag$sample55 && fixedFlag$sample29);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				double cv$sampleValue = logProbability$sample55[((i$var46 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Guard to ensure that b is only updated once for this probability.
				boolean cv$guard$b = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Copy of index so that its values can be safely substituted
				int index$i$5 = i$var46;
				
				// Looking for a path between Sample 55 and consumer int[] 50.
				{
					for(int index$i$6_1 = 1; index$i$6_1 < n; index$i$6_1 += 1) {
						if((i$var46 == (index$i$6_1 - 1))) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$b) {
								// Set the guard so the update is only applied once.
								cv$guard$b = true;
								
								// Update the variable probability
								logProbability$b = (logProbability$b + cv$sampleValue);
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var53 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$a = (logProbability$a + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample75 using sampled
	// values.
	private final void logProbabilityValue$sample75() {
		// Determine if we need to calculate the values for sample task 75 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample75) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flips[j];
					{
						{
							double var72 = (double)(1 / a[(j + 1)]);
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var72:(1.0 - var72))));
							
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
			logProbability$var73 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var74 = cv$accumulator;
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample75 = fixedFlag$sample55;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var74;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var73 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 29 drawn from Dirichlet 17. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample29(int var28) {
		if(true) {
			// A reference local to the function for the sample variable.
			double[] cv$targetLocal = m[var28];
			
			// A local reference to the scratch space.
			double[] cv$countLocal = cv$var29$countGlobal;
			
			// Get the length of the array
			int cv$arrayLength = states;
			
			// Initialize the array values to 0.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				// Processing random variable 53.
				{
					// Looking for a path between Sample 29 and consumer Categorical 53.
					{
						for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
							int traceTempVariable$var49$2_1 = 0;
							for(int index$i$2_2 = 1; index$i$2_2 < n; index$i$2_2 += 1) {
								if((0 == (index$i$2_2 - 1))) {
									int traceTempVariable$var51$2_3 = traceTempVariable$var49$2_1;
									if((index$i$2_2 == i$var46)) {
										if((var28 == b[i$var46])) {
											if(fixedFlag$sample55) {
												// Processing sample task 55 of consumer random variable null.
												{
													// Copy of index so that its values can be safely substituted
													int index$i$14 = i$var46;
													{
														{
															{
																{
																	// Increment the sample counter with the value sampled by sample task 55 of random
																	// variable var53
																	cv$countLocal[a[i$var46]] = (cv$countLocal[a[i$var46]] + 1.0);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
							if(fixedFlag$sample55) {
								for(int index$i$5_1 = 1; index$i$5_1 < n; index$i$5_1 += 1) {
									for(int index$i$5_2 = 1; index$i$5_2 < n; index$i$5_2 += 1) {
										if((index$i$5_1 == (index$i$5_2 - 1))) {
											int traceTempVariable$var51$5_3 = a[(index$i$5_2 - 1)];
											if((index$i$5_2 == i$var46)) {
												if((var28 == b[i$var46])) {
													if(fixedFlag$sample55) {
														// Processing sample task 55 of consumer random variable null.
														{
															// Copy of index so that its values can be safely substituted
															int index$i$16 = i$var46;
															{
																{
																	{
																		{
																			// Increment the sample counter with the value sampled by sample task 55 of random
																			// variable var53
																			cv$countLocal[a[i$var46]] = (cv$countLocal[a[i$var46]] + 1.0);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							} else {
								for(int index$i$6 = 1; index$i$6 < n; index$i$6 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 53.
										for(int index$sample55$7 = 0; index$sample55$7 < states; index$sample55$7 += 1) {
											int distributionTempVariable$var54$9 = index$sample55$7;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample55Value8 = (1.0 * distribution$sample55[((index$i$6 - 1) / 1)][index$sample55$7]);
											for(int index$i$10_1 = 1; index$i$10_1 < n; index$i$10_1 += 1) {
												if((index$i$6 == (index$i$10_1 - 1))) {
													int traceTempVariable$var51$10_2 = a[(index$i$10_1 - 1)];
													if((index$i$10_1 == i$var46)) {
														if((var28 == b[i$var46])) {
															if(fixedFlag$sample55) {
																// Processing sample task 55 of consumer random variable null.
																{
																	// Copy of index so that its values can be safely substituted
																	int index$i$18 = i$var46;
																	{
																		{
																			{
																				{
																					// Increment the sample counter with the value sampled by sample task 55 of random
																					// variable var53
																					cv$countLocal[a[i$var46]] = (cv$countLocal[a[i$var46]] + cv$probabilitySample55Value8);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Processing random variable 53.
			{
				// Looking for a path between Sample 29 and consumer Categorical 53.
				{
					for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
						int traceTempVariable$var49$23_1 = 0;
						for(int index$i$23_2 = 1; index$i$23_2 < n; index$i$23_2 += 1) {
							if((0 == (index$i$23_2 - 1))) {
								int traceTempVariable$var51$23_3 = traceTempVariable$var49$23_1;
								if((index$i$23_2 == i$var46)) {
									if((var28 == b[i$var46])) {
										if(!fixedFlag$sample55) {
											// Processing sample task 55 of consumer random variable null.
											{
												// Copy of index so that its values can be safely substituted
												int index$i$35 = i$var46;
												{
													{
														// Declare and zero an accumulator for tracking the reached source probability space.
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															// Add the probability of this argument configuration.
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														
														// The probability of reaching the consumer with this set of consumer arguments
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
														
														// Merge the distribution probabilities into the count
														for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
															cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample55[((i$var46 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
						if(fixedFlag$sample55) {
							for(int index$i$26_1 = 1; index$i$26_1 < n; index$i$26_1 += 1) {
								for(int index$i$26_2 = 1; index$i$26_2 < n; index$i$26_2 += 1) {
									if((index$i$26_1 == (index$i$26_2 - 1))) {
										int traceTempVariable$var51$26_3 = a[(index$i$26_2 - 1)];
										if((index$i$26_2 == i$var46)) {
											if((var28 == b[i$var46])) {
												if(!fixedFlag$sample55) {
													// Processing sample task 55 of consumer random variable null.
													{
														// Copy of index so that its values can be safely substituted
														int index$i$37 = i$var46;
														{
															{
																// Declare and zero an accumulator for tracking the reached source probability space.
																double scopeVariable$reachedSourceProbability = 0.0;
																{
																	// Add the probability of this argument configuration.
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
																
																// The probability of reaching the consumer with this set of consumer arguments
																double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																
																// Merge the distribution probabilities into the count
																for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																	cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample55[((i$var46 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
															}
														}
													}
												}
											}
										}
									}
								}
							}
						} else {
							for(int index$i$27 = 1; index$i$27 < n; index$i$27 += 1) {
								if(true) {
									// Enumerating the possible outputs of Categorical 53.
									for(int index$sample55$28 = 0; index$sample55$28 < states; index$sample55$28 += 1) {
										int distributionTempVariable$var54$30 = index$sample55$28;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample55Value29 = (1.0 * distribution$sample55[((index$i$27 - 1) / 1)][index$sample55$28]);
										for(int index$i$31_1 = 1; index$i$31_1 < n; index$i$31_1 += 1) {
											if((index$i$27 == (index$i$31_1 - 1))) {
												int traceTempVariable$var51$31_2 = a[(index$i$31_1 - 1)];
												if((index$i$31_1 == i$var46)) {
													if((var28 == b[i$var46])) {
														if(!fixedFlag$sample55) {
															// Processing sample task 55 of consumer random variable null.
															{
																// Copy of index so that its values can be safely substituted
																int index$i$39 = i$var46;
																{
																	{
																		// Declare and zero an accumulator for tracking the reached source probability space.
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			// Add the probability of this argument configuration.
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		
																		// The probability of reaching the consumer with this set of consumer arguments
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample55Value29);
																		
																		// Merge the distribution probabilities into the count
																		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																			cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample55[((i$var46 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, states);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 55 drawn from Categorical 53. Inference was performed using variable
	// marginalization.
	private final void sample55(int i$var46) {
		if(true) {
			// Calculate the number of states to evaluate.
			int cv$numNumStates = 0;
			
			// Exploring all the possible state counts for random variable 53.
			// 
			// Copy of index so that its values can be safely substituted
			int index$i$1 = i$var46;
			
			// Enumerating the possible arguments for Categorical 53.
			int traceTempVariable$var49$2_1 = 0;
			for(int index$i$2_2 = 1; index$i$2_2 < n; index$i$2_2 += 1) {
				if((0 == (index$i$2_2 - 1))) {
					int traceTempVariable$var51$2_3 = traceTempVariable$var49$2_1;
					if((index$i$2_2 == i$var46)) {
						for(int var28 = 0; var28 < states; var28 += 1) {
							if((var28 == b[i$var46]))
								// variable marginalization
								cv$numNumStates = Math.max(cv$numNumStates, states);
						}
					}
				}
			}
			
			// Enumerating the possible arguments for Categorical 53.
			for(int index$i$4_1 = 1; index$i$4_1 < n; index$i$4_1 += 1) {
				if((index$i$1 == (index$i$4_1 - 1))) {
					int traceTempVariable$var51$4_2 = a[(index$i$4_1 - 1)];
					if((index$i$4_1 == i$var46)) {
						for(int var28 = 0; var28 < states; var28 += 1) {
							if((var28 == b[i$var46]))
								// variable marginalization
								cv$numNumStates = Math.max(cv$numNumStates, states);
						}
					}
				}
			}
			if(fixedFlag$sample55) {
				for(int index$i$5_1 = 1; index$i$5_1 < n; index$i$5_1 += 1) {
					for(int index$i$5_2 = 1; index$i$5_2 < n; index$i$5_2 += 1) {
						if((index$i$5_1 == (index$i$5_2 - 1))) {
							int traceTempVariable$var51$5_3 = a[(index$i$5_2 - 1)];
							if((index$i$5_2 == i$var46)) {
								for(int var28 = 0; var28 < states; var28 += 1) {
									if((var28 == b[i$var46]))
										// variable marginalization
										cv$numNumStates = Math.max(cv$numNumStates, states);
								}
							}
						}
					}
				}
			} else {
				for(int index$i$6 = 1; index$i$6 < n; index$i$6 += 1) {
					if(!(index$i$6 == index$i$1)) {
						// Enumerating the possible outputs of Categorical 53.
						for(int index$sample55$7 = 0; index$sample55$7 < states; index$sample55$7 += 1) {
							int distributionTempVariable$var54$9 = index$sample55$7;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample55Value8 = (1.0 * distribution$sample55[((index$i$6 - 1) / 1)][index$sample55$7]);
							for(int index$i$10_1 = 1; index$i$10_1 < n; index$i$10_1 += 1) {
								if((index$i$6 == (index$i$10_1 - 1))) {
									int traceTempVariable$var51$10_2 = a[(index$i$10_1 - 1)];
									if((index$i$10_1 == i$var46)) {
										for(int var28 = 0; var28 < states; var28 += 1) {
											if((var28 == b[i$var46]))
												// variable marginalization
												cv$numNumStates = Math.max(cv$numNumStates, states);
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var54$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				// Exploring all the possible distribution values for random variable 53 creating
				// sample task 55.
				// Copy of index so that its values can be safely substituted
				int index$i$14 = i$var46;
				
				// Initialize the summed probabilities to 0.
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				
				// Initialize a counter to track the reached distributions.
				double cv$reachedDistributionSourceRV = 0.0;
				
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				double cv$accumulatedDistributionProbabilities = 0.0;
				
				// The value currently being tested
				int cv$currentValue;
				
				// Value of the variable at this index
				cv$currentValue = cv$valuePos;
				
				// Enumerating the possible arguments for Categorical 53.
				int traceTempVariable$var49$15_1 = 0;
				for(int index$i$15_2 = 1; index$i$15_2 < n; index$i$15_2 += 1) {
					if((0 == (index$i$15_2 - 1))) {
						int traceTempVariable$var51$15_3 = traceTempVariable$var49$15_1;
						if((index$i$15_2 == i$var46)) {
							for(int var28 = 0; var28 < states; var28 += 1) {
								if((var28 == b[i$var46])) {
									// Record the reached probability density.
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
									double[] cv$temp$0$var52;
									{
										// Constructing a random variable input for use later.
										double[] var52 = m[traceTempVariable$var51$15_3];
										cv$temp$0$var52 = var52;
									}
									int cv$temp$1$$var320;
									{
										// Constructing a random variable input for use later.
										int $var320 = states;
										cv$temp$1$$var320 = $var320;
									}
									
									// An accumulator to allow the value for each distribution to be constructed before
									// it is added to the index probabilities.
									double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var320))?Math.log(cv$temp$0$var52[cv$currentValue]):Double.NEGATIVE_INFINITY));
									
									// Processing random variable 53.
									{
										// Looking for a path between Sample 55 and consumer Categorical 53.
										{
											int traceTempVariable$var49$25_1 = cv$currentValue;
											for(int index$i$25_2 = 1; index$i$25_2 < n; index$i$25_2 += 1) {
												if((i$var46 == (index$i$25_2 - 1))) {
													int traceTempVariable$var51$25_3 = traceTempVariable$var49$25_1;
												}
											}
										}
									}
									
									// Processing random variable 73.
									{
										// Looking for a path between Sample 55 and consumer Bernoulli 73.
										{
											int traceTempVariable$var70$28_1 = cv$currentValue;
											for(int j = 0; j < n; j += 1) {
												if((i$var46 == (j + 1))) {
													// Processing sample task 75 of consumer random variable null.
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
																		double cv$temp$6$var72;
																		{
																			// Constructing a random variable input for use later.
																			double var72 = (double)(1 / traceTempVariable$var70$28_1);
																			cv$temp$6$var72 = var72;
																		}
																		
																		// Record the probability of sample task 75 generating output with current configuration.
																		if(((Math.log(1.0) + Math.log((flips[j]?cv$temp$6$var72:(1.0 - cv$temp$6$var72)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((flips[j]?cv$temp$6$var72:(1.0 - cv$temp$6$var72)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((flips[j]?cv$temp$6$var72:(1.0 - cv$temp$6$var72))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((flips[j]?cv$temp$6$var72:(1.0 - cv$temp$6$var72)))))) + 1)) + (Math.log(1.0) + Math.log((flips[j]?cv$temp$6$var72:(1.0 - cv$temp$6$var72)))));
																		}
																		
																		// Recorded the probability of reaching sample task 75 with the current configuration.
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
							}
						}
					}
				}
				
				// Enumerating the possible arguments for Categorical 53.
				int traceTempVariable$var49$17_1 = cv$currentValue;
				for(int index$i$17_2 = 1; index$i$17_2 < n; index$i$17_2 += 1) {
					if((index$i$14 == (index$i$17_2 - 1))) {
						int traceTempVariable$var51$17_3 = traceTempVariable$var49$17_1;
						if((index$i$17_2 == i$var46)) {
							for(int var28 = 0; var28 < states; var28 += 1) {
								if((var28 == b[i$var46])) {
									// Record the reached probability density.
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
									double[] cv$temp$2$var52;
									{
										// Constructing a random variable input for use later.
										double[] var52 = m[traceTempVariable$var51$17_3];
										cv$temp$2$var52 = var52;
									}
									int cv$temp$3$$var321;
									{
										// Constructing a random variable input for use later.
										int $var321 = states;
										cv$temp$3$$var321 = $var321;
									}
									
									// An accumulator to allow the value for each distribution to be constructed before
									// it is added to the index probabilities.
									double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$$var321))?Math.log(cv$temp$2$var52[cv$currentValue]):Double.NEGATIVE_INFINITY));
									
									// Processing random variable 53.
									{
										// Looking for a path between Sample 55 and consumer Categorical 53.
										{
											int traceTempVariable$var49$26_1 = cv$currentValue;
											for(int index$i$26_2 = 1; index$i$26_2 < n; index$i$26_2 += 1) {
												if((i$var46 == (index$i$26_2 - 1))) {
													int traceTempVariable$var51$26_3 = traceTempVariable$var49$26_1;
												}
											}
										}
									}
									
									// Processing random variable 73.
									{
										// Looking for a path between Sample 55 and consumer Bernoulli 73.
										{
											int traceTempVariable$var70$29_1 = cv$currentValue;
											for(int j = 0; j < n; j += 1) {
												if((i$var46 == (j + 1))) {
													// Processing sample task 75 of consumer random variable null.
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
																		double cv$temp$7$var72;
																		{
																			// Constructing a random variable input for use later.
																			double var72 = (double)(1 / traceTempVariable$var70$29_1);
																			cv$temp$7$var72 = var72;
																		}
																		
																		// Record the probability of sample task 75 generating output with current configuration.
																		if(((Math.log(1.0) + Math.log((flips[j]?cv$temp$7$var72:(1.0 - cv$temp$7$var72)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((flips[j]?cv$temp$7$var72:(1.0 - cv$temp$7$var72)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((flips[j]?cv$temp$7$var72:(1.0 - cv$temp$7$var72))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((flips[j]?cv$temp$7$var72:(1.0 - cv$temp$7$var72)))))) + 1)) + (Math.log(1.0) + Math.log((flips[j]?cv$temp$7$var72:(1.0 - cv$temp$7$var72)))));
																		}
																		
																		// Recorded the probability of reaching sample task 75 with the current configuration.
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
							}
						}
					}
				}
				for(int index$i$18 = 1; index$i$18 < n; index$i$18 += 1) {
					if(!(index$i$18 == index$i$14)) {
						// Enumerating the possible outputs of Categorical 53.
						for(int index$sample55$19 = 0; index$sample55$19 < states; index$sample55$19 += 1) {
							int distributionTempVariable$var54$21 = index$sample55$19;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample55Value20 = (1.0 * distribution$sample55[((index$i$18 - 1) / 1)][index$sample55$19]);
							int traceTempVariable$var49$22_1 = cv$currentValue;
							for(int index$i$22_2 = 1; index$i$22_2 < n; index$i$22_2 += 1) {
								if((index$i$18 == (index$i$22_2 - 1))) {
									int traceTempVariable$var51$22_3 = traceTempVariable$var49$22_1;
									if((index$i$22_2 == i$var46)) {
										for(int var28 = 0; var28 < states; var28 += 1) {
											if((var28 == b[i$var46])) {
												// Record the reached probability density.
												cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample55Value20);
												double[] cv$temp$4$var52;
												{
													// Constructing a random variable input for use later.
													double[] var52 = m[traceTempVariable$var51$22_3];
													cv$temp$4$var52 = var52;
												}
												int cv$temp$5$$var322;
												{
													// Constructing a random variable input for use later.
													int $var322 = states;
													cv$temp$5$$var322 = $var322;
												}
												
												// An accumulator to allow the value for each distribution to be constructed before
												// it is added to the index probabilities.
												double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample55Value20) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$5$$var322))?Math.log(cv$temp$4$var52[cv$currentValue]):Double.NEGATIVE_INFINITY));
												
												// Processing random variable 53.
												{
													// Looking for a path between Sample 55 and consumer Categorical 53.
													{
														int traceTempVariable$var49$27_1 = distributionTempVariable$var54$21;
														for(int index$i$27_2 = 1; index$i$27_2 < n; index$i$27_2 += 1) {
															if((i$var46 == (index$i$27_2 - 1))) {
																int traceTempVariable$var51$27_3 = traceTempVariable$var49$27_1;
															}
														}
													}
												}
												
												// Processing random variable 73.
												{
													// Looking for a path between Sample 55 and consumer Bernoulli 73.
													{
														int traceTempVariable$var70$30_1 = distributionTempVariable$var54$21;
														for(int j = 0; j < n; j += 1) {
															if((i$var46 == (j + 1))) {
																// Processing sample task 75 of consumer random variable null.
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
																					double cv$temp$8$var72;
																					{
																						// Constructing a random variable input for use later.
																						double var72 = (double)(1 / traceTempVariable$var70$30_1);
																						cv$temp$8$var72 = var72;
																					}
																					
																					// Record the probability of sample task 75 generating output with current configuration.
																					if(((Math.log(1.0) + Math.log((flips[j]?cv$temp$8$var72:(1.0 - cv$temp$8$var72)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((flips[j]?cv$temp$8$var72:(1.0 - cv$temp$8$var72)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						// If the second value is -infinity.
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((flips[j]?cv$temp$8$var72:(1.0 - cv$temp$8$var72))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((flips[j]?cv$temp$8$var72:(1.0 - cv$temp$8$var72)))))) + 1)) + (Math.log(1.0) + Math.log((flips[j]?cv$temp$8$var72:(1.0 - cv$temp$8$var72)))));
																					}
																					
																					// Recorded the probability of reaching sample task 75 with the current configuration.
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
										}
									}
								}
							}
						}
					}
				}
				
				// Processing random variable 53.
				{
					// Looking for a path between Sample 55 and consumer Categorical 53.
					{
						int traceTempVariable$var49$37_1 = cv$currentValue;
						for(int index$i$37_2 = 1; index$i$37_2 < n; index$i$37_2 += 1) {
							if((i$var46 == (index$i$37_2 - 1))) {
								int traceTempVariable$var51$37_3 = traceTempVariable$var49$37_1;
								for(int index$i$37_4 = 1; index$i$37_4 < n; index$i$37_4 += 1) {
									if((index$i$37_2 == index$i$37_4)) {
										// Processing sample task 55 of consumer random variable null.
										{
											// Copy of index so that its values can be safely substituted
											int index$i$39 = index$i$37_4;
											
											// A local array to hold the accumulated distributions of the sample tasks for each
											// configuration of distributions.
											double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var53;
											
											// Zero all the elements in the distribution accumulator
											for(int cv$i = 0; cv$i < states; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											
											// Zero an accumulator to track the probabilities reached.
											double cv$reachedDistributionProbability = 0.0;
											
											// Enumerating the possible arguments for the variable Categorical 53 which is consuming
											// the output of Sample task 55.
											for(int var28 = 0; var28 < states; var28 += 1) {
												if((var28 == b[index$i$37_4])) {
													{
														// Declare and zero an accumulator for tracking the reached source probability space.
														double scopeVariable$reachedSourceProbability = 0.0;
														
														// Enumerating the possible arguments for Categorical 53.
														int traceTempVariable$var49$41_1 = 0;
														for(int index$i$41_2 = 1; index$i$41_2 < n; index$i$41_2 += 1) {
															if((0 == (index$i$41_2 - 1))) {
																int traceTempVariable$var51$41_3 = traceTempVariable$var49$41_1;
																if((index$i$41_2 == i$var46)) {
																	for(int index$var28$42_1 = 0; index$var28$42_1 < states; index$var28$42_1 += 1) {
																		if((index$var28$42_1 == b[i$var46]))
																			// Add the probability of this argument configuration.
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																}
															}
														}
														
														// Enumerating the possible arguments for Categorical 53.
														int traceTempVariable$var49$43_1 = cv$currentValue;
														for(int index$i$43_2 = 1; index$i$43_2 < n; index$i$43_2 += 1) {
															if((index$i$14 == (index$i$43_2 - 1))) {
																int traceTempVariable$var51$43_3 = traceTempVariable$var49$43_1;
																if((index$i$43_2 == i$var46)) {
																	for(int index$var28$49_1 = 0; index$var28$49_1 < states; index$var28$49_1 += 1) {
																		if((index$var28$49_1 == b[i$var46]))
																			// Add the probability of this argument configuration.
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																}
															}
														}
														for(int index$i$44 = 1; index$i$44 < n; index$i$44 += 1) {
															if((!(index$i$44 == index$i$14) && !(index$i$44 == index$i$39))) {
																// Enumerating the possible outputs of Categorical 53.
																for(int index$sample55$45 = 0; index$sample55$45 < states; index$sample55$45 += 1) {
																	int distributionTempVariable$var54$47 = index$sample55$45;
																	
																	// Update the probability of sampling this value from the distribution value.
																	double cv$probabilitySample55Value46 = (1.0 * distribution$sample55[((index$i$44 - 1) / 1)][index$sample55$45]);
																	int traceTempVariable$var49$48_1 = cv$currentValue;
																	for(int index$i$48_2 = 1; index$i$48_2 < n; index$i$48_2 += 1) {
																		if((index$i$44 == (index$i$48_2 - 1))) {
																			int traceTempVariable$var51$48_3 = traceTempVariable$var49$48_1;
																			if((index$i$48_2 == i$var46)) {
																				for(int index$var28$50_1 = 0; index$var28$50_1 < states; index$var28$50_1 += 1) {
																					if((index$var28$50_1 == b[i$var46]))
																						// Add the probability of this argument configuration.
																						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample55Value46);
																				}
																			}
																		}
																	}
																}
															}
														}
														double[] cv$temp$9$var52;
														{
															// Constructing a random variable input for use later.
															double[] var52 = m[traceTempVariable$var51$37_3];
															cv$temp$9$var52 = var52;
														}
														int cv$temp$10$$var372;
														{
															// Constructing a random variable input for use later.
															int $var372 = states;
															cv$temp$10$$var372 = $var372;
														}
														
														// The probability of reaching the consumer with this set of consumer arguments
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
														
														// Record the reached distribution.
														cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
														
														// Add the current distribution to the distribution accumulator.
														DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$9$var52, cv$temp$10$$var372);
													}
												}
											}
											
											// A local copy of the samples' distribution.
											double[] cv$sampleDistribution = distribution$sample55[((index$i$37_4 - 1) / 1)];
											
											// The overlap of the distributions so far.
											double cv$overlap = 0.0;
											
											// Calculate the overlap for each element in the distribution
											for(int cv$i = 0; cv$i < states; cv$i += 1) {
												// Normalise the values in the calculated distribution
												double cv$normalisedDistValue = (cv$accumulatedConsumerDistributions[cv$i] / cv$reachedDistributionProbability);
												
												// Corresponding value from the sample distribution
												double cv$sampleDistValue = cv$sampleDistribution[cv$i];
												
												// Calculate the overlap and store the result
												if((cv$sampleDistValue < cv$normalisedDistValue))
													cv$overlap = (cv$overlap + cv$sampleDistValue);
												
												// Calculate the overlap and store the result
												else
													cv$overlap = (cv$overlap + cv$normalisedDistValue);
											}
											
											// Scale and add the result to the combined results so far. A min is taken over the
											// reached distributions so that rounding cannot result in a value greater than one
											// as for a small probability this could give a negative value
											cv$accumulatedDistributionProbabilities = (cv$accumulatedDistributionProbabilities + Math.log(((cv$overlap * cv$reachedDistributionProbability) + (1.0 - Math.min(cv$reachedDistributionProbability, 1.0)))));
										}
									}
								}
							}
						}
					}
				}
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			double[] cv$localProbability = distribution$sample55[((i$var46 - 1) / 1)];
			
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
					cv$localProbability[cv$indexName] = (1.0 / cv$numNumStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var29$countGlobal
		{
			// Allocation of cv$var29$countGlobal for single threaded execution
			cv$var29$countGlobal = new double[5];
		}
		
		// Constructor for cv$distributionAccumulator$var53
		{
			// Variable to record the maximum value of Task Get 53. Initially set to the value
			// of putTask 30.
			int cv$var30$max = 5;
			
			// Allocation of cv$distributionAccumulator$var53 for single threaded execution
			cv$distributionAccumulator$var53 = new double[cv$var30$max];
		}
		
		// Constructor for cv$var54$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 53. Initially set to the value
			// of putTask 30.
			int cv$var30$max = 5;
			
			// Allocation of cv$var54$stateProbabilityGlobal for single threaded execution
			cv$var54$stateProbabilityGlobal = new double[cv$var30$max];
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for v
		{
			v = new double[5];
		}
		
		// If m has not been set already allocate space.
		if(!fixedFlag$sample29) {
			// Constructor for m
			{
				m = new double[5][];
				for(int var28 = 0; var28 < 5; var28 += 1)
					m[var28] = new double[5];
			}
		}
		
		// If a has not been set already allocate space.
		if(!fixedFlag$sample55) {
			// Constructor for a
			{
				a = new int[n];
			}
		}
		
		// Constructor for b
		{
			b = new int[n];
		}
		
		// Constructor for flips
		{
			flips = new boolean[n];
		}
		
		// Constructor for distribution$sample55
		{
			distribution$sample55 = new double[((((n - 1) - 1) / 1) + 1)][];
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
				distribution$sample55[((i$var46 - 1) / 1)] = new double[5];
		}
		
		// Constructor for logProbability$sample55
		{
			logProbability$sample55 = new double[((((n - 1) - 1) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		for(int var28 = 0; var28 < states; var28 += 1) {
			double[] var29 = m[var28];
			if(!fixedFlag$sample29)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var29);
		}
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			if(!fixedFlag$sample55)
				b[i$var46] = a[(i$var46 - 1)];
			if(!fixedFlag$sample55)
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], states);
		}
		for(int j = 0; j < n; j += 1)
			flips[j] = DistributionSampling.sampleBernoulli(RNG$, (1 / a[(j + 1)]));
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var28 = 0; var28 < states; var28 += 1) {
			double[] var29 = m[var28];
			if(!fixedFlag$sample29)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var29);
		}
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			// Create local copy of variable probabilities.
			double[] cv$distribution$sample55 = distribution$sample55[((i$var46 - 1) / 1)];
			for(int index$var53 = 0; index$var53 < states; index$var53 += 1) {
				if(!fixedFlag$sample55)
					// Zero the probability of each value
					cv$distribution$sample55[index$var53] = 0.0;
			}
			
			// Iterate through possible values for var53's arguments.
			// 
			// Enumerating the possible arguments for Categorical 53.
			int traceTempVariable$var49$1_1 = 0;
			for(int index$i$1_2 = 1; index$i$1_2 < n; index$i$1_2 += 1) {
				if((0 == (index$i$1_2 - 1))) {
					int traceTempVariable$var51$1_3 = traceTempVariable$var49$1_1;
					if((index$i$1_2 == i$var46)) {
						for(int var28 = 0; var28 < states; var28 += 1) {
							if((var28 == b[i$var46])) {
								{
									if(!fixedFlag$sample55) {
										double[] var52 = m[traceTempVariable$var51$1_3];
										for(int index$var53 = 0; index$var53 < states; index$var53 += 1)
											// Save the probability of each value
											cv$distribution$sample55[index$var53] = (cv$distribution$sample55[index$var53] + (1.0 * (((0.0 <= index$var53) && (index$var53 < states))?var52[index$var53]:0.0)));
									}
								}
							}
						}
					}
				}
			}
			
			// Enumerating the possible arguments for Categorical 53.
			if(fixedFlag$sample55) {
				for(int index$i$3_1 = 1; index$i$3_1 < n; index$i$3_1 += 1) {
					for(int index$i$3_2 = 1; index$i$3_2 < n; index$i$3_2 += 1) {
						if((index$i$3_1 == (index$i$3_2 - 1))) {
							int traceTempVariable$var51$3_3 = a[(index$i$3_2 - 1)];
							if((index$i$3_2 == i$var46)) {
								for(int var28 = 0; var28 < states; var28 += 1) {
									if((var28 == b[i$var46])) {
										{
											if(!fixedFlag$sample55) {
												double[] var52 = m[traceTempVariable$var51$3_3];
												for(int index$var53 = 0; index$var53 < states; index$var53 += 1)
													// Save the probability of each value
													cv$distribution$sample55[index$var53] = (cv$distribution$sample55[index$var53] + (1.0 * (((0.0 <= index$var53) && (index$var53 < states))?var52[index$var53]:0.0)));
											}
										}
									}
								}
							}
						}
					}
				}
			} else {
				for(int index$i$4 = 1; index$i$4 < n; index$i$4 += 1) {
					if(true) {
						// Enumerating the possible outputs of Categorical 53.
						for(int index$sample55$5 = 0; index$sample55$5 < states; index$sample55$5 += 1) {
							int distributionTempVariable$var54$7 = index$sample55$5;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample55Value6 = (1.0 * distribution$sample55[((index$i$4 - 1) / 1)][index$sample55$5]);
							for(int index$i$8_1 = 1; index$i$8_1 < n; index$i$8_1 += 1) {
								if((index$i$4 == (index$i$8_1 - 1))) {
									int traceTempVariable$var51$8_2 = a[(index$i$8_1 - 1)];
									if((index$i$8_1 == i$var46)) {
										for(int var28 = 0; var28 < states; var28 += 1) {
											if((var28 == b[i$var46])) {
												{
													if(!fixedFlag$sample55) {
														double[] var52 = m[traceTempVariable$var51$8_2];
														for(int index$var53 = 0; index$var53 < states; index$var53 += 1)
															// Save the probability of each value
															cv$distribution$sample55[index$var53] = (cv$distribution$sample55[index$var53] + (cv$probabilitySample55Value6 * (((0.0 <= index$var53) && (index$var53 < states))?var52[index$var53]:0.0)));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Sum the values in the array
			double cv$var53$sum = 0.0;
			for(int index$var53 = 0; index$var53 < states; index$var53 += 1) {
				if(!fixedFlag$sample55)
					// sum the probability of each value
					cv$var53$sum = (cv$var53$sum + cv$distribution$sample55[index$var53]);
			}
			for(int index$var53 = 0; index$var53 < states; index$var53 += 1) {
				if(!fixedFlag$sample55)
					// Normalise the probability of each value
					cv$distribution$sample55[index$var53] = (cv$distribution$sample55[index$var53] / cv$var53$sum);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var28 = 0; var28 < states; var28 += 1) {
			double[] var29 = m[var28];
			if(!fixedFlag$sample29)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var29);
		}
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			if(!fixedFlag$sample55)
				b[i$var46] = a[(i$var46 - 1)];
			if(!fixedFlag$sample55)
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], states);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			for(int var28 = 0; var28 < states; var28 += 1) {
				if(!fixedFlag$sample29)
					sample29(var28);
			}
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				if(!fixedFlag$sample55)
					sample55(i$var46);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i$var46 = (n - ((((n - 1) - 1) % 1) + 1)); i$var46 >= ((1 - 1) + 1); i$var46 -= 1) {
				if(!fixedFlag$sample55)
					sample55(i$var46);
			}
			for(int var28 = (states - ((((states - 1) - 0) % 1) + 1)); var28 >= ((0 - 1) + 1); var28 -= 1) {
				if(!fixedFlag$sample29)
					sample29(var28);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		states = 5;
		for(int i$var14 = 0; i$var14 < 5; i$var14 += 1)
			v[i$var14] = 0.1;
		a[0] = 0;
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
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$var29 = 0.0;
		logProbability$var53 = 0.0;
		logProbability$b = 0.0;
		logProbability$a = 0.0;
		if(!fixedProbFlag$sample55) {
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
				logProbability$sample55[((i$var46 - 1) / 1)] = 0.0;
		}
		logProbability$var73 = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample75)
			logProbability$var74 = 0.0;
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
		if(fixedFlag$sample29)
			logProbabilityValue$sample29();
		logProbabilityValue$sample75();
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
		logProbabilityValue$sample29();
		logProbabilityDistribution$sample55();
		logProbabilityDistribution$sample75();
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
		logProbabilityValue$sample29();
		logProbabilityValue$sample55();
		logProbabilityValue$sample75();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		for(int var28 = 0; var28 < states; var28 += 1) {
			double[] var29 = m[var28];
			if(!fixedFlag$sample29)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var29);
		}
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			if(!fixedFlag$sample55)
				b[i$var46] = a[(i$var46 - 1)];
			if(!fixedFlag$sample55)
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], states);
		}
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Deep copy between arrays
		boolean[] cv$source1 = flipsMeasured;
		boolean[] cv$target1 = flips;
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
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			if(fixedFlag$sample55)
				b[i$var46] = a[(i$var46 - 1)];
		}
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
		     + "/**\n"
		     + " * A model for the fairness work.\n"
		     + " */\n"
		     + "public model Deterministic2(int n, boolean[] flipsMeasured) {\n"
		     + "    int states = 5;\n"
		     + "\n"
		     + "    double[] v = new double[states];\n"
		     + "    for(int i:[0..states))\n"
		     + "        v[i] = 0.1;\n"
		     + "    \n"
		     + "    double[][] m = dirichlet(v).sample(states);\n"
		     + "\n"
		     + "    int[] a = new int[n];\n"
		     + "    int[] b = new int[n];\n"
		     + "    a[0] = 0;\n"
		     + "    for(int i=1; i<n; i++) {\n"
		     + "        b[i] = a[i-1];\n"
		     + "        a[i] = categorical(m[b[i]]).sampleDistribution();\n"
		     + "    }\n"
		     + "    \n"
		     + "    boolean[] flips = new boolean[n];\n"
		     + "            \n"
		     + "    for(int j:[0..n))\n"
		     + "        flips[j] = bernoulli(1/a[j+1]).sample();\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}";
	}
}