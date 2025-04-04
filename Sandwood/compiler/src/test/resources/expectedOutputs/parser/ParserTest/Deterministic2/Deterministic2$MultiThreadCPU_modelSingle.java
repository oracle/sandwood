package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Deterministic2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Deterministic2$CoreInterface {
	
	// Declare the variables for the model.
	private int[] a;
	private int[] b;
	private double[] cv$distributionAccumulator$var33;
	private double[][] cv$var17$countGlobal;
	private double[] cv$var34$stateProbabilityGlobal;
	private double[][] distribution$sample36;
	private boolean fixedFlag$sample18 = false;
	private boolean fixedFlag$sample36 = false;
	private boolean fixedFlag$sample49 = false;
	private boolean fixedProbFlag$sample18 = false;
	private boolean fixedProbFlag$sample36 = false;
	private boolean fixedProbFlag$sample49 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$a;
	private double logProbability$b;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample36;
	private double logProbability$var12;
	private double logProbability$var17;
	private double logProbability$var33;
	private double logProbability$var46;
	private double logProbability$var47;
	private double[][] m;
	private int n;
	private boolean setFlag$a = false;
	private boolean setFlag$b = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private int states;
	private boolean system$gibbsForward = true;
	private double[] v;

	public Deterministic2$MultiThreadCPU(ExecutionTarget target) {
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
		// Set a with flag to mark that it has been set so another array doesn't need to be
		// constructed
		a = cv$value;
		setFlag$a = true;
	}

	// Getter for b.
	@Override
	public final int[] get$b() {
		return b;
	}

	// Setter for b.
	@Override
	public final void set$b(int[] cv$value) {
		// Set b with flag to mark that it has been set so another array doesn't need to be
		// constructed
		b = cv$value;
		setFlag$b = true;
	}

	// Getter for fixedFlag$sample18.
	@Override
	public final boolean get$fixedFlag$sample18() {
		return fixedFlag$sample18;
	}

	// Setter for fixedFlag$sample18.
	@Override
	public final void set$fixedFlag$sample18(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample18 including if probabilities
		// need to be updated.
		fixedFlag$sample18 = cv$value;
		
		// Should the probability of sample 18 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample18 = (fixedFlag$sample18 && fixedProbFlag$sample18);
		
		// Should the probability of sample 36 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample36 = (fixedFlag$sample18 && fixedProbFlag$sample36);
	}

	// Getter for fixedFlag$sample36.
	@Override
	public final boolean get$fixedFlag$sample36() {
		return fixedFlag$sample36;
	}

	// Setter for fixedFlag$sample36.
	@Override
	public final void set$fixedFlag$sample36(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample36 including if probabilities
		// need to be updated.
		fixedFlag$sample36 = cv$value;
		
		// Should the probability of sample 36 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample36 = (fixedFlag$sample36 && fixedProbFlag$sample36);
		
		// Should the probability of sample 49 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample49 = (fixedFlag$sample36 && fixedProbFlag$sample49);
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
		// Set m with flag to mark that it has been set so another array doesn't need to be
		// constructed
		m = cv$value;
		setFlag$m = true;
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

	// Calculate the probability of the samples represented by sample36 using probability
	// distributions.
	private final void logProbabilityDistribution$sample36() {
		// Determine if we need to calculate the values for sample task 36 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample36) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample36) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Look for paths between the variable and the sample task 36 including any distribution
					// values.
					// 
					// Copy of index so that its values can be safely substituted
					int index$i$1 = i$var26;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = a[i$var26];
						
						// Enumerating the possible arguments for Categorical 33.
						for(int index$i$3_1 = 1; index$i$3_1 < n; index$i$3_1 += 1) {
							if((0 == (index$i$3_1 - 1))) {
								if((index$i$3_1 == i$var26)) {
									for(int var16 = 0; var16 < states; var16 += 1) {
										if((var16 == b[i$var26])) {
											{
												double[] var32 = m[b[i$var26]];
												
												// Store the value of the function call, so the function call is only made once.
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var32.length))?Math.log(var32[cv$sampleValue]):Double.NEGATIVE_INFINITY));
												
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
						
						// Enumerating the possible arguments for Categorical 33.
						int traceTempVariable$var29$5_1 = DistributionSampling.sampleCategorical(RNG$, m[b[index$i$1]]);
						for(int index$i$5_2 = 1; index$i$5_2 < n; index$i$5_2 += 1) {
							if((index$i$1 == (index$i$5_2 - 1))) {
								int traceTempVariable$var31$5_3 = traceTempVariable$var29$5_1;
								if((index$i$5_2 == i$var26)) {
									for(int var16 = 0; var16 < states; var16 += 1) {
										if((var16 == traceTempVariable$var31$5_3)) {
											{
												double[] var32 = m[traceTempVariable$var31$5_3];
												
												// Store the value of the function call, so the function call is only made once.
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var32.length))?Math.log(var32[cv$sampleValue]):Double.NEGATIVE_INFINITY));
												
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
						if(fixedFlag$sample36) {
							for(int index$i$6_1 = 1; index$i$6_1 < n; index$i$6_1 += 1) {
								for(int index$i$6_2 = 1; index$i$6_2 < n; index$i$6_2 += 1) {
									if((index$i$6_1 == (index$i$6_2 - 1))) {
										if((index$i$6_2 == i$var26)) {
											for(int var16 = 0; var16 < states; var16 += 1) {
												if((var16 == b[i$var26])) {
													{
														double[] var32 = m[b[i$var26]];
														
														// Store the value of the function call, so the function call is only made once.
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var32.length))?Math.log(var32[cv$sampleValue]):Double.NEGATIVE_INFINITY));
														
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
									// Enumerating the possible outputs of Categorical 33.
									for(int index$sample36$8 = 0; index$sample36$8 < states; index$sample36$8 += 1) {
										int distributionTempVariable$var34$10 = index$sample36$8;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample36Value9 = (1.0 * distribution$sample36[((index$i$7 - 1) / 1)][index$sample36$8]);
										int traceTempVariable$var29$11_1 = distributionTempVariable$var34$10;
										for(int index$i$11_2 = 1; index$i$11_2 < n; index$i$11_2 += 1) {
											if((index$i$7 == (index$i$11_2 - 1))) {
												int traceTempVariable$var31$11_3 = traceTempVariable$var29$11_1;
												if((index$i$11_2 == i$var26)) {
													for(int var16 = 0; var16 < states; var16 += 1) {
														if((var16 == traceTempVariable$var31$11_3)) {
															{
																double[] var32 = m[traceTempVariable$var31$11_3];
																
																// Store the value of the function call, so the function call is only made once.
																double cv$weightedProbability = (Math.log(cv$probabilitySample36Value9) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var32.length))?Math.log(var32[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																
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
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample36Value9);
															}
														}
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
					logProbability$sample36[((i$var26 - 1) / 1)] = cv$sampleProbability;
					
					// Guard to ensure that b is only updated once for this probability.
					boolean cv$guard$b = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					// 
					// Copy of index so that its values can be safely substituted
					int index$i$15 = i$var26;
					
					// Looking for a path between Sample 36 and consumer int[] 30.
					{
						for(int index$i$16_1 = 1; index$i$16_1 < n; index$i$16_1 += 1) {
							if((i$var26 == (index$i$16_1 - 1))) {
								// Make sure all the inputs have been fixed so the variable is not a distribution.
								if(fixedFlag$sample36) {
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
				logProbability$var33 = cv$sampleAccumulator;
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(fixedFlag$sample36)
					// Update the variable probability
					logProbability$a = (logProbability$a + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample36)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample36 = (fixedFlag$sample36 && fixedFlag$sample18);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				double cv$sampleValue = logProbability$sample36[((i$var26 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Guard to ensure that b is only updated once for this probability.
				boolean cv$guard$b = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Copy of index so that its values can be safely substituted
				int index$i$17 = i$var26;
				
				// Looking for a path between Sample 36 and consumer int[] 30.
				{
					for(int index$i$18_1 = 1; index$i$18_1 < n; index$i$18_1 += 1) {
						if((i$var26 == (index$i$18_1 - 1))) {
							// Make sure all the inputs have been fixed so the variable is not a distribution.
							if(fixedFlag$sample36) {
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
			logProbability$var33 = cv$rvAccumulator;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample36)
				// Update the variable probability
				logProbability$a = (logProbability$a + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample49 using probability
	// distributions.
	private final void logProbabilityDistribution$sample49() {
		// Determine if we need to calculate the values for sample task 49 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample49) {
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
				
				// Look for paths between the variable and the sample task 49 including any distribution
				// values.
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flips[j];
					
					// Enumerating the possible arguments for Bernoulli 46.
					if((0 == (j + 1))) {
						{
							double var45 = (double)(1 / a[(j + 1)]);
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var45));
							
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
					
					// Enumerating the possible arguments for Bernoulli 46.
					if(fixedFlag$sample36) {
						for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
							if((i$var26 == (j + 1))) {
								{
									double var45 = (double)(1 / a[(j + 1)]);
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var45));
									
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
						for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
							if(true) {
								// Enumerating the possible outputs of Categorical 33.
								for(int index$sample36$5 = 0; index$sample36$5 < states; index$sample36$5 += 1) {
									int distributionTempVariable$var34$7 = index$sample36$5;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample36Value6 = (1.0 * distribution$sample36[((i$var26 - 1) / 1)][index$sample36$5]);
									int traceTempVariable$var43$8_1 = distributionTempVariable$var34$7;
									if((i$var26 == (j + 1))) {
										{
											double var45 = (double)(1 / traceTempVariable$var43$8_1);
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(cv$probabilitySample36Value6) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var45));
											
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
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample36Value6);
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
			logProbability$var46 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var47 = cv$accumulator;
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample49 = (fixedFlag$sample49 && fixedFlag$sample36);
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
			logProbability$var46 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample18 using sampled
	// values.
	private final void logProbabilityValue$sample18() {
		// Determine if we need to calculate the values for sample task 18 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample18) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var16 = 0; var16 < states; var16 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double[] cv$sampleValue = m[var16];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v));
							
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
			logProbability$var12 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var17 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample18 = fixedFlag$sample18;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var17;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var12 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample36 using sampled
	// values.
	private final void logProbabilityValue$sample36() {
		// Determine if we need to calculate the values for sample task 36 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample36) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Copy of index so that its values can be safely substituted
				int index$i$1 = i$var26;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = a[i$var26];
					{
						{
							double[] var32 = m[b[i$var26]];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var32.length))?Math.log(var32[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
				logProbability$sample36[((i$var26 - 1) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that b is only updated once for this probability.
				boolean cv$guard$b = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Copy of index so that its values can be safely substituted
				int index$i$3 = i$var26;
				
				// Looking for a path between Sample 36 and consumer int[] 30.
				{
					for(int index$i$4_1 = 1; index$i$4_1 < n; index$i$4_1 += 1) {
						if((i$var26 == (index$i$4_1 - 1))) {
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
			logProbability$var33 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$a = (logProbability$a + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample36 = (fixedFlag$sample36 && fixedFlag$sample18);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				double cv$sampleValue = logProbability$sample36[((i$var26 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Guard to ensure that b is only updated once for this probability.
				boolean cv$guard$b = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Copy of index so that its values can be safely substituted
				int index$i$5 = i$var26;
				
				// Looking for a path between Sample 36 and consumer int[] 30.
				{
					for(int index$i$6_1 = 1; index$i$6_1 < n; index$i$6_1 += 1) {
						if((i$var26 == (index$i$6_1 - 1))) {
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
			logProbability$var33 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$a = (logProbability$a + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample36)
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
							double var45 = (double)(1 / a[(j + 1)]);
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var45));
							
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
			logProbability$var46 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var47 = cv$accumulator;
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample49 = (fixedFlag$sample49 && fixedFlag$sample36);
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
			logProbability$var46 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 18 drawn from Dirichlet 12. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample18(int var16, int threadID$cv$var16, Rng RNG$) {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = m[var16];
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var17$countGlobal[threadID$cv$var16];
		
		// Get the length of the array
		int cv$arrayLength = states;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 33.
			{
				// Looking for a path between Sample 18 and consumer Categorical 33.
				{
					for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
						for(int index$i$2_1 = 1; index$i$2_1 < n; index$i$2_1 += 1) {
							if((0 == (index$i$2_1 - 1))) {
								if((index$i$2_1 == i$var26)) {
									if((var16 == b[i$var26])) {
										if(fixedFlag$sample36) {
											// Processing sample task 36 of consumer random variable null.
											{
												// Copy of index so that its values can be safely substituted
												int index$i$14 = i$var26;
												{
													{
														{
															{
																// Increment the sample counter with the value sampled by sample task 36 of random
																// variable var33
																cv$countLocal[a[i$var26]] = (cv$countLocal[a[i$var26]] + 1.0);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
						if(fixedFlag$sample36) {
							for(int index$i$5_1 = 1; index$i$5_1 < n; index$i$5_1 += 1) {
								for(int index$i$5_2 = 1; index$i$5_2 < n; index$i$5_2 += 1) {
									if((index$i$5_1 == (index$i$5_2 - 1))) {
										if((index$i$5_2 == i$var26)) {
											if((var16 == b[i$var26])) {
												if(fixedFlag$sample36) {
													// Processing sample task 36 of consumer random variable null.
													{
														// Copy of index so that its values can be safely substituted
														int index$i$16 = i$var26;
														{
															{
																{
																	{
																		// Increment the sample counter with the value sampled by sample task 36 of random
																		// variable var33
																		cv$countLocal[a[i$var26]] = (cv$countLocal[a[i$var26]] + 1.0);
																	}
																}
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
									// Enumerating the possible outputs of Categorical 33.
									for(int index$sample36$7 = 0; index$sample36$7 < states; index$sample36$7 += 1) {
										int distributionTempVariable$var34$9 = index$sample36$7;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample36Value8 = (1.0 * distribution$sample36[((index$i$6 - 1) / 1)][index$sample36$7]);
										int traceTempVariable$var29$10_1 = distributionTempVariable$var34$9;
										for(int index$i$10_2 = 1; index$i$10_2 < n; index$i$10_2 += 1) {
											if((index$i$6 == (index$i$10_2 - 1))) {
												int traceTempVariable$var31$10_3 = traceTempVariable$var29$10_1;
												if((index$i$10_2 == i$var26)) {
													if((var16 == traceTempVariable$var31$10_3)) {
														if(fixedFlag$sample36) {
															// Processing sample task 36 of consumer random variable null.
															{
																// Copy of index so that its values can be safely substituted
																int index$i$18 = i$var26;
																{
																	{
																		{
																			{
																				// Increment the sample counter with the value sampled by sample task 36 of random
																				// variable var33
																				cv$countLocal[a[i$var26]] = (cv$countLocal[a[i$var26]] + cv$probabilitySample36Value8);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Processing random variable 33.
		{
			// Looking for a path between Sample 18 and consumer Categorical 33.
			{
				for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
					for(int index$i$23_1 = 1; index$i$23_1 < n; index$i$23_1 += 1) {
						if((0 == (index$i$23_1 - 1))) {
							if((index$i$23_1 == i$var26)) {
								if((var16 == b[i$var26])) {
									if(!fixedFlag$sample36) {
										// Processing sample task 36 of consumer random variable null.
										{
											// Copy of index so that its values can be safely substituted
											int index$i$35 = i$var26;
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
														cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample36[((i$var26 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
												}
											}
										}
									}
								}
							}
						}
					}
				}
				for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
					if(fixedFlag$sample36) {
						for(int index$i$26_1 = 1; index$i$26_1 < n; index$i$26_1 += 1) {
							for(int index$i$26_2 = 1; index$i$26_2 < n; index$i$26_2 += 1) {
								if((index$i$26_1 == (index$i$26_2 - 1))) {
									if((index$i$26_2 == i$var26)) {
										if((var16 == b[i$var26])) {
											if(!fixedFlag$sample36) {
												// Processing sample task 36 of consumer random variable null.
												{
													// Copy of index so that its values can be safely substituted
													int index$i$37 = i$var26;
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
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample36[((i$var26 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
								// Enumerating the possible outputs of Categorical 33.
								for(int index$sample36$28 = 0; index$sample36$28 < states; index$sample36$28 += 1) {
									int distributionTempVariable$var34$30 = index$sample36$28;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample36Value29 = (1.0 * distribution$sample36[((index$i$27 - 1) / 1)][index$sample36$28]);
									int traceTempVariable$var29$31_1 = distributionTempVariable$var34$30;
									for(int index$i$31_2 = 1; index$i$31_2 < n; index$i$31_2 += 1) {
										if((index$i$27 == (index$i$31_2 - 1))) {
											int traceTempVariable$var31$31_3 = traceTempVariable$var29$31_1;
											if((index$i$31_2 == i$var26)) {
												if((var16 == traceTempVariable$var31$31_3)) {
													if(!fixedFlag$sample36) {
														// Processing sample task 36 of consumer random variable null.
														{
															// Copy of index so that its values can be safely substituted
															int index$i$39 = i$var26;
															{
																{
																	// Declare and zero an accumulator for tracking the reached source probability space.
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		// Add the probability of this argument configuration.
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																	
																	// The probability of reaching the consumer with this set of consumer arguments
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample36Value29);
																	
																	// Merge the distribution probabilities into the count
																	for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample36[((i$var26 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																}
															}
														}
													}
												}
											}
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
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 36 drawn from Categorical 33. Inference was performed using variable
	// marginalization.
	private final void sample36(int i$var26) {
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var34$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < states; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 33 creating
			// sample task 36.
			// Copy of index so that its values can be safely substituted
			int index$i$1 = i$var26;
			
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
			
			// Enumerating the possible arguments for Categorical 33.
			for(int index$i$2_1 = 1; index$i$2_1 < n; index$i$2_1 += 1) {
				if((0 == (index$i$2_1 - 1))) {
					if((index$i$2_1 == i$var26)) {
						for(int var16 = 0; var16 < states; var16 += 1) {
							if((var16 == b[i$var26])) {
								// Record the reached probability density.
								cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
								double[] cv$temp$0$var32;
								{
									// Constructing a random variable input for use later.
									double[] var32 = m[b[i$var26]];
									cv$temp$0$var32 = var32;
								}
								
								// An accumulator to allow the value for each distribution to be constructed before
								// it is added to the index probabilities.
								double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var32.length))?Math.log(cv$temp$0$var32[cv$currentValue]):Double.NEGATIVE_INFINITY));
								
								// Processing random variable 33.
								{
									// Looking for a path between Sample 36 and consumer Categorical 33.
									{
										int traceTempVariable$var29$12_1 = cv$currentValue;
										for(int index$i$12_2 = 1; index$i$12_2 < n; index$i$12_2 += 1) {
											if((i$var26 == (index$i$12_2 - 1))) {
												int traceTempVariable$var31$12_3 = traceTempVariable$var29$12_1;
											}
										}
									}
								}
								
								// Processing random variable 46.
								{
									// Looking for a path between Sample 36 and consumer Bernoulli 46.
									{
										int traceTempVariable$var43$15_1 = cv$currentValue;
										for(int j = 0; j < n; j += 1) {
											if((i$var26 == (j + 1))) {
												// Processing sample task 49 of consumer random variable null.
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
																	double cv$temp$3$var45;
																	{
																		// Constructing a random variable input for use later.
																		double var45 = (double)(1 / traceTempVariable$var43$15_1);
																		cv$temp$3$var45 = var45;
																	}
																	
																	// Record the probability of sample task 49 generating output with current configuration.
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var45)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var45)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var45));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var45)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var45)));
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
			
			// Enumerating the possible arguments for Categorical 33.
			int traceTempVariable$var29$4_1 = cv$currentValue;
			for(int index$i$4_2 = 1; index$i$4_2 < n; index$i$4_2 += 1) {
				if((index$i$1 == (index$i$4_2 - 1))) {
					int traceTempVariable$var31$4_3 = traceTempVariable$var29$4_1;
					if((index$i$4_2 == i$var26)) {
						for(int var16 = 0; var16 < states; var16 += 1) {
							if((var16 == traceTempVariable$var31$4_3)) {
								// Record the reached probability density.
								cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
								double[] cv$temp$1$var32;
								{
									// Constructing a random variable input for use later.
									double[] var32 = m[traceTempVariable$var31$4_3];
									cv$temp$1$var32 = var32;
								}
								
								// An accumulator to allow the value for each distribution to be constructed before
								// it is added to the index probabilities.
								double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var32.length))?Math.log(cv$temp$1$var32[cv$currentValue]):Double.NEGATIVE_INFINITY));
								
								// Processing random variable 33.
								{
									// Looking for a path between Sample 36 and consumer Categorical 33.
									{
										int traceTempVariable$var29$13_1 = cv$currentValue;
										for(int index$i$13_2 = 1; index$i$13_2 < n; index$i$13_2 += 1) {
											if((i$var26 == (index$i$13_2 - 1))) {
												int traceTempVariable$var31$13_3 = traceTempVariable$var29$13_1;
											}
										}
									}
								}
								
								// Processing random variable 46.
								{
									// Looking for a path between Sample 36 and consumer Bernoulli 46.
									{
										int traceTempVariable$var43$16_1 = cv$currentValue;
										for(int j = 0; j < n; j += 1) {
											if((i$var26 == (j + 1))) {
												// Processing sample task 49 of consumer random variable null.
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
																	double cv$temp$4$var45;
																	{
																		// Constructing a random variable input for use later.
																		double var45 = (double)(1 / traceTempVariable$var43$16_1);
																		cv$temp$4$var45 = var45;
																	}
																	
																	// Record the probability of sample task 49 generating output with current configuration.
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var45)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var45)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var45));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var45)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var45)));
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
			for(int index$i$5 = 1; index$i$5 < n; index$i$5 += 1) {
				if(!(index$i$5 == index$i$1)) {
					// Enumerating the possible outputs of Categorical 33.
					for(int index$sample36$6 = 0; index$sample36$6 < states; index$sample36$6 += 1) {
						int distributionTempVariable$var34$8 = index$sample36$6;
						
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample36Value7 = (1.0 * distribution$sample36[((index$i$5 - 1) / 1)][index$sample36$6]);
						int traceTempVariable$var29$9_1 = distributionTempVariable$var34$8;
						for(int index$i$9_2 = 1; index$i$9_2 < n; index$i$9_2 += 1) {
							if((index$i$5 == (index$i$9_2 - 1))) {
								int traceTempVariable$var31$9_3 = traceTempVariable$var29$9_1;
								if((index$i$9_2 == i$var26)) {
									for(int var16 = 0; var16 < states; var16 += 1) {
										if((var16 == traceTempVariable$var31$9_3)) {
											// Record the reached probability density.
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample36Value7);
											double[] cv$temp$2$var32;
											{
												// Constructing a random variable input for use later.
												double[] var32 = m[traceTempVariable$var31$9_3];
												cv$temp$2$var32 = var32;
											}
											
											// An accumulator to allow the value for each distribution to be constructed before
											// it is added to the index probabilities.
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample36Value7) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var32.length))?Math.log(cv$temp$2$var32[cv$currentValue]):Double.NEGATIVE_INFINITY));
											
											// Processing random variable 33.
											{
												// Looking for a path between Sample 36 and consumer Categorical 33.
												{
													int traceTempVariable$var29$14_1 = cv$currentValue;
													for(int index$i$14_2 = 1; index$i$14_2 < n; index$i$14_2 += 1) {
														if((i$var26 == (index$i$14_2 - 1))) {
															int traceTempVariable$var31$14_3 = traceTempVariable$var29$14_1;
														}
													}
												}
											}
											
											// Processing random variable 46.
											{
												// Looking for a path between Sample 36 and consumer Bernoulli 46.
												{
													int traceTempVariable$var43$17_1 = cv$currentValue;
													for(int j = 0; j < n; j += 1) {
														if((i$var26 == (j + 1))) {
															// Processing sample task 49 of consumer random variable null.
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
																				double cv$temp$5$var45;
																				{
																					// Constructing a random variable input for use later.
																					double var45 = (double)(1 / traceTempVariable$var43$17_1);
																					cv$temp$5$var45 = var45;
																				}
																				
																				// Record the probability of sample task 49 generating output with current configuration.
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var45)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var45)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var45));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var45)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var45)));
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
			
			// Processing random variable 33.
			{
				// Looking for a path between Sample 36 and consumer Categorical 33.
				{
					int traceTempVariable$var29$24_1 = cv$currentValue;
					for(int index$i$24_2 = 1; index$i$24_2 < n; index$i$24_2 += 1) {
						if((i$var26 == (index$i$24_2 - 1))) {
							int traceTempVariable$var31$24_3 = traceTempVariable$var29$24_1;
							for(int index$i$24_4 = 1; index$i$24_4 < n; index$i$24_4 += 1) {
								if((index$i$24_2 == index$i$24_4)) {
									// Processing sample task 36 of consumer random variable null.
									{
										// Copy of index so that its values can be safely substituted
										int index$i$26 = index$i$24_4;
										
										// A local array to hold the accumulated distributions of the sample tasks for each
										// configuration of distributions.
										double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var33;
										
										// Zero all the elements in the distribution accumulator
										for(int cv$i = 0; cv$i < states; cv$i += 1)
											cv$accumulatedConsumerDistributions[cv$i] = 0.0;
										
										// Zero an accumulator to track the probabilities reached.
										double cv$reachedDistributionProbability = 0.0;
										
										// Enumerating the possible arguments for the variable Categorical 33 which is consuming
										// the output of Sample task 36.
										for(int var16 = 0; var16 < states; var16 += 1) {
											if((var16 == traceTempVariable$var31$24_3)) {
												{
													// Declare and zero an accumulator for tracking the reached source probability space.
													double scopeVariable$reachedSourceProbability = 0.0;
													
													// Enumerating the possible arguments for Categorical 33.
													for(int index$i$28_1 = 1; index$i$28_1 < n; index$i$28_1 += 1) {
														if((0 == (index$i$28_1 - 1))) {
															if((index$i$28_1 == i$var26)) {
																for(int index$var16$29_1 = 0; index$var16$29_1 < states; index$var16$29_1 += 1) {
																	if((index$var16$29_1 == b[i$var26]))
																		// Add the probability of this argument configuration.
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
															}
														}
													}
													
													// Enumerating the possible arguments for Categorical 33.
													int traceTempVariable$var29$30_1 = cv$currentValue;
													for(int index$i$30_2 = 1; index$i$30_2 < n; index$i$30_2 += 1) {
														if((index$i$1 == (index$i$30_2 - 1))) {
															int traceTempVariable$var31$30_3 = traceTempVariable$var29$30_1;
															if((index$i$30_2 == i$var26)) {
																for(int index$var16$36_1 = 0; index$var16$36_1 < states; index$var16$36_1 += 1) {
																	if((index$var16$36_1 == traceTempVariable$var31$30_3))
																		// Add the probability of this argument configuration.
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
															}
														}
													}
													for(int index$i$31 = 1; index$i$31 < n; index$i$31 += 1) {
														if((!(index$i$31 == index$i$1) && !(index$i$31 == index$i$26))) {
															// Enumerating the possible outputs of Categorical 33.
															for(int index$sample36$32 = 0; index$sample36$32 < states; index$sample36$32 += 1) {
																int distributionTempVariable$var34$34 = index$sample36$32;
																
																// Update the probability of sampling this value from the distribution value.
																double cv$probabilitySample36Value33 = (1.0 * distribution$sample36[((index$i$31 - 1) / 1)][index$sample36$32]);
																int traceTempVariable$var29$35_1 = distributionTempVariable$var34$34;
																for(int index$i$35_2 = 1; index$i$35_2 < n; index$i$35_2 += 1) {
																	if((index$i$31 == (index$i$35_2 - 1))) {
																		int traceTempVariable$var31$35_3 = traceTempVariable$var29$35_1;
																		if((index$i$35_2 == i$var26)) {
																			for(int index$var16$37_1 = 0; index$var16$37_1 < states; index$var16$37_1 += 1) {
																				if((index$var16$37_1 == traceTempVariable$var31$35_3))
																					// Add the probability of this argument configuration.
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample36Value33);
																			}
																		}
																	}
																}
															}
														}
													}
													double[] cv$temp$6$var32;
													{
														// Constructing a random variable input for use later.
														double[] var32 = m[traceTempVariable$var31$24_3];
														cv$temp$6$var32 = var32;
													}
													
													// The probability of reaching the consumer with this set of consumer arguments
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													
													// Record the reached distribution.
													cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
													
													// Add the current distribution to the distribution accumulator.
													DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$6$var32);
												}
											}
										}
										
										// A local copy of the samples' distribution.
										double[] cv$sampleDistribution = distribution$sample36[((index$i$24_4 - 1) / 1)];
										
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
		double[] cv$localProbability = distribution$sample36[((i$var26 - 1) / 1)];
		
		// The sum of all the probabilities in log space
		double cv$logSum = 0.0;
		
		// Sum all the values
		{
			// Initialise the max to the first element.
			double cv$lseMax = cv$stateProbabilityLocal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1) {
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
				for(int cv$lseIndex = 0; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$stateProbabilityLocal.length);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var17$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			for(int var16 = 0; var16 < 5; var16 += 1)
				cv$max = Math.max(cv$max, 5);
			
			// Allocation of cv$var17$countGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var17$countGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var17$countGlobal[cv$index] = new double[cv$max];
			}
		}
		
		// Constructor for cv$distributionAccumulator$var33
		{
			// Variable to record the maximum value of Task Get 34. Initially set to the value
			// of putTask 19.
			int cv$var18$max = 5;
			
			// Allocation of cv$distributionAccumulator$var33 for single threaded execution
			cv$distributionAccumulator$var33 = new double[cv$var18$max];
		}
		
		// Constructor for cv$var34$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 34. Initially set to the value
			// of putTask 19.
			int cv$var18$max = 5;
			
			// Allocation of cv$var34$stateProbabilityGlobal for single threaded execution
			cv$var34$stateProbabilityGlobal = new double[cv$var18$max];
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
		if(!setFlag$m) {
			// Constructor for m
			{
				m = new double[5][];
				for(int var16 = 0; var16 < 5; var16 += 1)
					m[var16] = new double[5];
			}
		}
		
		// If a has not been set already allocate space.
		if(!setFlag$a) {
			// Constructor for a
			{
				a = new int[n];
			}
		}
		
		// If b has not been set already allocate space.
		if(!setFlag$b) {
			// Constructor for b
			{
				b = new int[n];
			}
		}
		
		// If flips has not been set already allocate space.
		if(!setFlag$flips) {
			// Constructor for flips
			{
				flips = new boolean[n];
			}
		}
		
		// Constructor for distribution$sample36
		{
			distribution$sample36 = new double[((((n - 1) - 1) / 1) + 1)][];
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1)
				distribution$sample36[((i$var26 - 1) / 1)] = new double[5];
		}
		
		// Constructor for logProbability$sample36
		{
			logProbability$sample36 = new double[((((n - 1) - 1) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1) {
						double[] var17 = m[var16];
						if(!fixedFlag$sample18)
							DistributionSampling.sampleDirichlet(RNG$1, v, var17);
					}
			}
		);
		for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
			if(!fixedFlag$sample36)
				b[i$var26] = a[(i$var26 - 1)];
			if(!fixedFlag$sample36)
				a[i$var26] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var26]]);
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, n, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j = forStart$j; j < forEnd$j; j += 1) {
						if(!fixedFlag$sample49)
							flips[j] = DistributionSampling.sampleBernoulli(RNG$1, (1 / a[(j + 1)]));
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1) {
						double[] var17 = m[var16];
						if(!fixedFlag$sample18)
							DistributionSampling.sampleDirichlet(RNG$1, v, var17);
					}
			}
		);
		for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
			// Create local copy of variable probabilities.
			double[] cv$distribution$sample36 = distribution$sample36[((i$var26 - 1) / 1)];
			for(int index$var33 = 0; index$var33 < states; index$var33 += 1) {
				if(!fixedFlag$sample36)
					// Zero the probability of each value
					cv$distribution$sample36[index$var33] = 0.0;
			}
			
			// Iterate through possible values for var33's arguments.
			// 
			// Enumerating the possible arguments for Categorical 33.
			for(int index$i$1_1 = 1; index$i$1_1 < n; index$i$1_1 += 1) {
				if((0 == (index$i$1_1 - 1))) {
					if((index$i$1_1 == i$var26)) {
						for(int var16 = 0; var16 < states; var16 += 1) {
							if((var16 == b[i$var26])) {
								{
									if(!fixedFlag$sample36) {
										double[] var32 = m[b[i$var26]];
										for(int index$var33 = 0; index$var33 < states; index$var33 += 1)
											// Save the probability of each value
											cv$distribution$sample36[index$var33] = (cv$distribution$sample36[index$var33] + (1.0 * (((0.0 <= index$var33) && (index$var33 < var32.length))?var32[index$var33]:0.0)));
									}
								}
							}
						}
					}
				}
			}
			
			// Enumerating the possible arguments for Categorical 33.
			if(fixedFlag$sample36) {
				for(int index$i$3_1 = 1; index$i$3_1 < n; index$i$3_1 += 1) {
					for(int index$i$3_2 = 1; index$i$3_2 < n; index$i$3_2 += 1) {
						if((index$i$3_1 == (index$i$3_2 - 1))) {
							if((index$i$3_2 == i$var26)) {
								for(int var16 = 0; var16 < states; var16 += 1) {
									if((var16 == b[i$var26])) {
										{
											if(!fixedFlag$sample36) {
												double[] var32 = m[b[i$var26]];
												for(int index$var33 = 0; index$var33 < states; index$var33 += 1)
													// Save the probability of each value
													cv$distribution$sample36[index$var33] = (cv$distribution$sample36[index$var33] + (1.0 * (((0.0 <= index$var33) && (index$var33 < var32.length))?var32[index$var33]:0.0)));
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
						// Enumerating the possible outputs of Categorical 33.
						for(int index$sample36$5 = 0; index$sample36$5 < states; index$sample36$5 += 1) {
							int distributionTempVariable$var34$7 = index$sample36$5;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample36Value6 = (1.0 * distribution$sample36[((index$i$4 - 1) / 1)][index$sample36$5]);
							int traceTempVariable$var29$8_1 = distributionTempVariable$var34$7;
							for(int index$i$8_2 = 1; index$i$8_2 < n; index$i$8_2 += 1) {
								if((index$i$4 == (index$i$8_2 - 1))) {
									int traceTempVariable$var31$8_3 = traceTempVariable$var29$8_1;
									if((index$i$8_2 == i$var26)) {
										for(int var16 = 0; var16 < states; var16 += 1) {
											if((var16 == traceTempVariable$var31$8_3)) {
												{
													if(!fixedFlag$sample36) {
														double[] var32 = m[traceTempVariable$var31$8_3];
														for(int index$var33 = 0; index$var33 < states; index$var33 += 1)
															// Save the probability of each value
															cv$distribution$sample36[index$var33] = (cv$distribution$sample36[index$var33] + (cv$probabilitySample36Value6 * (((0.0 <= index$var33) && (index$var33 < var32.length))?var32[index$var33]:0.0)));
													}
												}
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
			double cv$var33$sum = 0.0;
			for(int index$var33 = 0; index$var33 < states; index$var33 += 1) {
				if(!fixedFlag$sample36)
					// sum the probability of each value
					cv$var33$sum = (cv$var33$sum + cv$distribution$sample36[index$var33]);
			}
			for(int index$var33 = 0; index$var33 < states; index$var33 += 1) {
				if(!fixedFlag$sample36)
					// Normalise the probability of each value
					cv$distribution$sample36[index$var33] = (cv$distribution$sample36[index$var33] / cv$var33$sum);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1) {
						double[] var17 = m[var16];
						if(!fixedFlag$sample18)
							DistributionSampling.sampleDirichlet(RNG$1, v, var17);
					}
			}
		);
		for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
			if(!fixedFlag$sample36)
				b[i$var26] = a[(i$var26 - 1)];
			if(!fixedFlag$sample36)
				a[i$var26] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var26]]);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1) {
							if(!fixedFlag$sample18)
								sample18(var16, threadID$var16, RNG$1);
						}
				}
			);
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				if(!fixedFlag$sample36)
					sample36(i$var26);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i$var26 = (n - ((((n - 1) - 1) % 1) + 1)); i$var26 >= ((1 - 1) + 1); i$var26 -= 1) {
				if(!fixedFlag$sample36)
					sample36(i$var26);
			}
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1) {
							if(!fixedFlag$sample18)
								sample18(var16, threadID$var16, RNG$1);
						}
				}
			);
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		states = 5;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var9, int forEnd$i$var9, int threadID$i$var9, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var9 = forStart$i$var9; i$var9 < forEnd$i$var9; i$var9 += 1)
						v[i$var9] = 0.1;
			}
		);
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
		logProbability$var12 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample18)
			logProbability$var17 = 0.0;
		logProbability$var33 = 0.0;
		logProbability$a = 0.0;
		logProbability$b = 0.0;
		if(!fixedProbFlag$sample36) {
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1)
				logProbability$sample36[((i$var26 - 1) / 1)] = 0.0;
		}
		logProbability$var46 = 0.0;
		logProbability$flips = 0.0;
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
		if(fixedFlag$sample18)
			logProbabilityValue$sample18();
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
		logProbabilityValue$sample18();
		logProbabilityDistribution$sample36();
		logProbabilityDistribution$sample49();
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
		logProbabilityValue$sample18();
		logProbabilityValue$sample36();
		logProbabilityValue$sample49();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1) {
						double[] var17 = m[var16];
						if(!fixedFlag$sample18)
							DistributionSampling.sampleDirichlet(RNG$1, v, var17);
					}
			}
		);
		for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
			if(!fixedFlag$sample36)
				b[i$var26] = a[(i$var26 - 1)];
			if(!fixedFlag$sample36)
				a[i$var26] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var26]]);
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
		boolean[] cv$source1 = flipsMeasured;
		boolean[] cv$target1 = flips;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
			if(setFlag$a)
				b[i$var26] = a[(i$var26 - 1)];
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\n/**\n * A model for the fairness work.\n */\npublic model Deterministic2(int n, boolean[] flipsMeasured) {\n    int states = 5;\n\n    double[] v = new double[states];\n    for(int i:[0..states))\n        v[i] = 0.1;\n    \n    double[][] m = dirichlet(v).sample(states);\n\n    int[] a = new int[n];\n    int[] b = new int[n];\n    a[0] = 0;\n    for(int i=1; i<n; i++) {\n        b[i] = a[i-1];\n        a[i] = categorical(m[b[i]]).sampleDistribution();\n    }\n    \n    boolean[] flips = new boolean[n];\n            \n    for(int j:[0..n))\n        flips[j] = bernoulli(1/a[j+1]).sample();\n        flips.observe(flipsMeasured);\n}";
	}
}