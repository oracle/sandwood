package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart7$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart7$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private double[] cv$distributionAccumulator$var69;
	private double[][] cv$var28$countGlobal;
	private double[] cv$var52$stateProbabilityGlobal;
	private double[] cv$var70$stateProbabilityGlobal;
	private double[] distribution$sample53;
	private double[][] distribution$sample71;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedFlag$sample71 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean fixedProbFlag$sample71 = false;
	private boolean fixedProbFlag$sample87 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample71;
	private double[] logProbability$sample87;
	private double logProbability$st;
	private double logProbability$var16;
	private double logProbability$var28;
	private double logProbability$var32;
	private double logProbability$var44;
	private double logProbability$var51;
	private double logProbability$var52;
	private double[] logProbability$var69;
	private double[] logProbability$var85;
	private double[][] m;
	private int samples;
	private int[] st;
	private int states;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart7$MultiThreadCPU(ExecutionTarget target) {
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
		
		// Unset the fixed probability flag for sample 45 as it depends on bias.
		fixedProbFlag$sample45 = false;
		
		// Unset the fixed probability flag for sample 87 as it depends on bias.
		fixedProbFlag$sample87 = false;
	}

	// Getter for distribution$sample53.
	@Override
	public final double[] get$distribution$sample53() {
		return distribution$sample53;
	}

	// Setter for distribution$sample53.
	@Override
	public final void set$distribution$sample53(double[] cv$value) {
		// Set distribution$sample53
		distribution$sample53 = cv$value;
	}

	// Getter for distribution$sample71.
	@Override
	public final double[][] get$distribution$sample71() {
		return distribution$sample71;
	}

	// Setter for distribution$sample71.
	@Override
	public final void set$distribution$sample71(double[][] cv$value) {
		// Set distribution$sample71
		distribution$sample71 = cv$value;
	}

	// Getter for fixedFlag$sample28.
	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	// Setter for fixedFlag$sample28.
	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample28 including if probabilities
		// need to be updated.
		fixedFlag$sample28 = cv$value;
		
		// Should the probability of sample 28 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample28 = (fixedFlag$sample28 && fixedProbFlag$sample28);
		
		// Should the probability of sample 53 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample53 = (fixedFlag$sample28 && fixedProbFlag$sample53);
		
		// Should the probability of sample 71 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample71 = (fixedFlag$sample28 && fixedProbFlag$sample71);
	}

	// Getter for fixedFlag$sample45.
	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	// Setter for fixedFlag$sample45.
	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample45 including if probabilities
		// need to be updated.
		fixedFlag$sample45 = cv$value;
		
		// Should the probability of sample 45 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedProbFlag$sample45);
		
		// Should the probability of sample 87 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample87 = (fixedFlag$sample45 && fixedProbFlag$sample87);
	}

	// Getter for fixedFlag$sample53.
	@Override
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	// Setter for fixedFlag$sample53.
	@Override
	public final void set$fixedFlag$sample53(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample53 including if probabilities
		// need to be updated.
		fixedFlag$sample53 = cv$value;
		
		// Should the probability of sample 53 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedProbFlag$sample53);
		
		// Should the probability of sample 71 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample71 = (fixedFlag$sample53 && fixedProbFlag$sample71);
		
		// Should the probability of sample 87 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample87 = (fixedFlag$sample53 && fixedProbFlag$sample87);
	}

	// Getter for fixedFlag$sample71.
	@Override
	public final boolean get$fixedFlag$sample71() {
		return fixedFlag$sample71;
	}

	// Setter for fixedFlag$sample71.
	@Override
	public final void set$fixedFlag$sample71(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample71 including if probabilities
		// need to be updated.
		fixedFlag$sample71 = cv$value;
		
		// Should the probability of sample 71 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample71 = (fixedFlag$sample71 && fixedProbFlag$sample71);
		
		// Should the probability of sample 87 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample87 = (fixedFlag$sample71 && fixedProbFlag$sample87);
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

	// Getter for logProbability$m.
	@Override
	public final double get$logProbability$m() {
		return logProbability$m;
	}

	// Getter for logProbability$st.
	@Override
	public final double get$logProbability$st() {
		return logProbability$st;
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
		
		// Unset the fixed probability flag for sample 28 as it depends on m.
		fixedProbFlag$sample28 = false;
		
		// Unset the fixed probability flag for sample 53 as it depends on m.
		fixedProbFlag$sample53 = false;
		
		// Unset the fixed probability flag for sample 71 as it depends on m.
		fixedProbFlag$sample71 = false;
	}

	// Getter for samples.
	@Override
	public final int get$samples() {
		return samples;
	}

	// Getter for st.
	@Override
	public final int[] get$st() {
		return st;
	}

	// Setter for st.
	@Override
	public final void set$st(int[] cv$value) {
		// Set flags for all the side effects of st including if probabilities need to be
		// updated.
		// Set st
		st = cv$value;
		
		// Unset the fixed probability flag for sample 53 as it depends on st.
		fixedProbFlag$sample53 = false;
		
		// Unset the fixed probability flag for sample 71 as it depends on st.
		fixedProbFlag$sample71 = false;
		
		// Unset the fixed probability flag for sample 87 as it depends on st.
		fixedProbFlag$sample87 = false;
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

	// Calculate the probability of the samples represented by sample53 using probability
	// distributions.
	private final void logProbabilityDistribution$sample53() {
		// Determine if we need to calculate the values for sample task 53 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample53) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample53) {
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
					int cv$sampleValue = st[0];
					{
						{
							double[] var50 = m[0];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var50[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
				logProbability$var51 = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$var52 = cv$sampleProbability;
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(fixedFlag$sample53)
					// Update the variable probability
					logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample53)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedFlag$sample28);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var52;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var51 = cv$rvAccumulator;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample53)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample71 using probability
	// distributions.
	private final void logProbabilityDistribution$sample71() {
		// Determine if we need to calculate the values for sample task 71 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample71) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample71) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Look for paths between the variable and the sample task 71 including any distribution
					// values.
					// 
					// Copy of index so that its values can be safely substituted
					int index$i$1 = i$var64;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[i$var64];
						
						// Enumerating the possible arguments for Categorical 69.
						if(fixedFlag$sample53) {
							if((0 == (i$var64 - 1))) {
								for(int var27 = 0; var27 < states; var27 += 1) {
									if((var27 == st[(i$var64 - 1)])) {
										{
											double[] var68 = m[st[(i$var64 - 1)]];
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
											
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
						} else {
							if(true) {
								// Enumerating the possible outputs of Categorical 51.
								for(int index$sample53$4 = 0; index$sample53$4 < states; index$sample53$4 += 1) {
									int distributionTempVariable$var52$6 = index$sample53$4;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample53Value5 = (1.0 * distribution$sample53[index$sample53$4]);
									if((0 == (i$var64 - 1))) {
										for(int var27 = 0; var27 < states; var27 += 1) {
											if((var27 == st[(i$var64 - 1)])) {
												{
													double[] var68 = m[st[(i$var64 - 1)]];
													
													// Store the value of the function call, so the function call is only made once.
													double cv$weightedProbability = (Math.log(cv$probabilitySample53Value5) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
													
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
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample53Value5);
												}
											}
										}
									}
								}
							}
						}
						
						// Enumerating the possible arguments for Categorical 69.
						if((index$i$1 == (i$var64 - 1))) {
							for(int var27 = 0; var27 < states; var27 += 1) {
								if((var27 == st[(i$var64 - 1)])) {
									{
										double[] var68 = m[st[(i$var64 - 1)]];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
										
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
						if(fixedFlag$sample71) {
							for(int index$i$11_1 = 1; index$i$11_1 < samples; index$i$11_1 += 1) {
								if((index$i$11_1 == (i$var64 - 1))) {
									for(int var27 = 0; var27 < states; var27 += 1) {
										if((var27 == st[(i$var64 - 1)])) {
											{
												double[] var68 = m[st[(i$var64 - 1)]];
												
												// Store the value of the function call, so the function call is only made once.
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
												
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
						} else {
							for(int index$i$12 = 1; index$i$12 < samples; index$i$12 += 1) {
								if(!(index$i$12 == index$i$1)) {
									// Enumerating the possible outputs of Categorical 69.
									for(int index$sample71$13 = 0; index$sample71$13 < states; index$sample71$13 += 1) {
										int distributionTempVariable$var70$15 = index$sample71$13;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample71Value14 = (1.0 * distribution$sample71[((index$i$12 - 1) / 1)][index$sample71$13]);
										if((index$i$12 == (i$var64 - 1))) {
											for(int var27 = 0; var27 < states; var27 += 1) {
												if((var27 == st[(i$var64 - 1)])) {
													{
														double[] var68 = m[st[(i$var64 - 1)]];
														
														// Store the value of the function call, so the function call is only made once.
														double cv$weightedProbability = (Math.log(cv$probabilitySample71Value14) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
														
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
														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value14);
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
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$var69[((i$var64 - 1) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample71[((i$var64 - 1) / 1)] = cv$sampleProbability;
				}
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(fixedFlag$sample71)
					// Update the variable probability
					logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample71)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample71 = ((fixedFlag$sample71 && fixedFlag$sample28) && fixedFlag$sample53);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample71[((i$var64 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var69[((i$var64 - 1) / 1)] = cv$rvAccumulator;
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample71)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample87 using probability
	// distributions.
	private final void logProbabilityDistribution$sample87() {
		// Determine if we need to calculate the values for sample task 87 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample87) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 87 including any distribution
				// values.
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flips[j];
					
					// Enumerating the possible arguments for Bernoulli 85.
					if(fixedFlag$sample53) {
						if((0 == j)) {
							for(int var43 = 0; var43 < states; var43 += 1) {
								if((var43 == st[j])) {
									{
										double var84 = bias[st[j]];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var84));
										
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
					} else {
						if(true) {
							// Enumerating the possible outputs of Categorical 51.
							for(int index$sample53$3 = 0; index$sample53$3 < states; index$sample53$3 += 1) {
								int distributionTempVariable$var52$5 = index$sample53$3;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample53Value4 = (1.0 * distribution$sample53[index$sample53$3]);
								if((0 == j)) {
									for(int var43 = 0; var43 < states; var43 += 1) {
										if((var43 == st[j])) {
											{
												double var84 = bias[st[j]];
												
												// Store the value of the function call, so the function call is only made once.
												double cv$weightedProbability = (Math.log(cv$probabilitySample53Value4) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var84));
												
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
												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample53Value4);
											}
										}
									}
								}
							}
						}
					}
					
					// Enumerating the possible arguments for Bernoulli 85.
					if(fixedFlag$sample71) {
						for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
							if((i$var64 == j)) {
								for(int var43 = 0; var43 < states; var43 += 1) {
									if((var43 == st[j])) {
										{
											double var84 = bias[st[j]];
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var84));
											
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
					} else {
						for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
							if(true) {
								// Enumerating the possible outputs of Categorical 69.
								for(int index$sample71$11 = 0; index$sample71$11 < states; index$sample71$11 += 1) {
									int distributionTempVariable$var70$13 = index$sample71$11;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample71Value12 = (1.0 * distribution$sample71[((i$var64 - 1) / 1)][index$sample71$11]);
									if((i$var64 == j)) {
										for(int var43 = 0; var43 < states; var43 += 1) {
											if((var43 == st[j])) {
												{
													double var84 = bias[st[j]];
													
													// Store the value of the function call, so the function call is only made once.
													double cv$weightedProbability = (Math.log(cv$probabilitySample71Value12) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var84));
													
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
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value12);
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
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var85[((j - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample87[((j - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample87 = ((fixedFlag$sample45 && fixedFlag$sample53) && fixedFlag$sample71);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample87[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var85[((j - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample28 using sampled
	// values.
	private final void logProbabilityValue$sample28() {
		// Determine if we need to calculate the values for sample task 28 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample28) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var27 = 0; var27 < states; var27 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double[] cv$sampleValue = m[var27];
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
			logProbability$var16 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var28 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample28 = fixedFlag$sample28;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var28;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var16 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample45 using sampled
	// values.
	private final void logProbabilityValue$sample45() {
		// Determine if we need to calculate the values for sample task 45 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample45) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var43 = 0; var43 < states; var43 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = bias[var43];
					{
						{
							double var30 = 1.0;
							double var31 = 1.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var30, var31));
							
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
			logProbability$var32 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var44 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample45 = fixedFlag$sample45;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var44;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var32 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample53 using sampled
	// values.
	private final void logProbabilityValue$sample53() {
		// Determine if we need to calculate the values for sample task 53 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample53) {
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
				int cv$sampleValue = st[0];
				{
					{
						double[] var50 = m[0];
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var50[cv$sampleValue]):Double.NEGATIVE_INFINITY));
						
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
			logProbability$var51 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$var52 = cv$sampleProbability;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedFlag$sample28);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var52;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var51 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample71 using sampled
	// values.
	private final void logProbabilityValue$sample71() {
		// Determine if we need to calculate the values for sample task 71 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample71) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Copy of index so that its values can be safely substituted
				int index$i$1 = i$var64;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[i$var64];
					{
						{
							double[] var68 = m[st[(i$var64 - 1)]];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
				logProbability$var69[((i$var64 - 1) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample71[((i$var64 - 1) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample71 = ((fixedFlag$sample71 && fixedFlag$sample28) && fixedFlag$sample53);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample71[((i$var64 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var69[((i$var64 - 1) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample87 using sampled
	// values.
	private final void logProbabilityValue$sample87() {
		// Determine if we need to calculate the values for sample task 87 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample87) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flips[j];
					{
						{
							double var84 = bias[st[j]];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var84));
							
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
				logProbability$var85[((j - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample87[((j - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample87 = ((fixedFlag$sample45 && fixedFlag$sample53) && fixedFlag$sample71);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample87[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var85[((j - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 28 drawn from Dirichlet 16. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample28(int var27, int threadID$cv$var27, Rng RNG$) {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = m[var27];
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var28$countGlobal[threadID$cv$var27];
		
		// Get the length of the array
		int cv$arrayLength = states;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 51.
			{
				// Looking for a path between Sample 28 and consumer Categorical 51.
				{
					if((var27 == 0)) {
						if(fixedFlag$sample53) {
							// Processing sample task 53 of consumer random variable null.
							{
								{
									{
										{
											{
												// Increment the sample counter with the value sampled by sample task 53 of random
												// variable var51
												cv$countLocal[st[0]] = (cv$countLocal[st[0]] + 1.0);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Processing random variable 69.
			{
				// Looking for a path between Sample 28 and consumer Categorical 69.
				{
					for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
						if(fixedFlag$sample53) {
							if((0 == (i$var64 - 1))) {
								if((var27 == st[(i$var64 - 1)])) {
									if(fixedFlag$sample71) {
										// Processing sample task 71 of consumer random variable null.
										{
											// Copy of index so that its values can be safely substituted
											int index$i$22 = i$var64;
											{
												{
													{
														{
															// Increment the sample counter with the value sampled by sample task 71 of random
															// variable var69
															cv$countLocal[st[i$var64]] = (cv$countLocal[st[i$var64]] + 1.0);
														}
													}
												}
											}
										}
									}
								}
							}
						} else {
							if(true) {
								// Enumerating the possible outputs of Categorical 51.
								for(int index$sample53$6 = 0; index$sample53$6 < states; index$sample53$6 += 1) {
									int distributionTempVariable$var52$8 = index$sample53$6;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample53Value7 = (1.0 * distribution$sample53[index$sample53$6]);
									if((0 == (i$var64 - 1))) {
										if((var27 == st[(i$var64 - 1)])) {
											if(fixedFlag$sample71) {
												// Processing sample task 71 of consumer random variable null.
												{
													// Copy of index so that its values can be safely substituted
													int index$i$24 = i$var64;
													{
														{
															{
																{
																	// Increment the sample counter with the value sampled by sample task 71 of random
																	// variable var69
																	cv$countLocal[st[i$var64]] = (cv$countLocal[st[i$var64]] + cv$probabilitySample53Value7);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
						if(fixedFlag$sample71) {
							for(int index$i$13_1 = 1; index$i$13_1 < samples; index$i$13_1 += 1) {
								if((index$i$13_1 == (i$var64 - 1))) {
									if((var27 == st[(i$var64 - 1)])) {
										if(fixedFlag$sample71) {
											// Processing sample task 71 of consumer random variable null.
											{
												// Copy of index so that its values can be safely substituted
												int index$i$26 = i$var64;
												{
													{
														{
															{
																// Increment the sample counter with the value sampled by sample task 71 of random
																// variable var69
																cv$countLocal[st[i$var64]] = (cv$countLocal[st[i$var64]] + 1.0);
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
							for(int index$i$14 = 1; index$i$14 < samples; index$i$14 += 1) {
								if(true) {
									// Enumerating the possible outputs of Categorical 69.
									for(int index$sample71$15 = 0; index$sample71$15 < states; index$sample71$15 += 1) {
										int distributionTempVariable$var70$17 = index$sample71$15;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample71Value16 = (1.0 * distribution$sample71[((index$i$14 - 1) / 1)][index$sample71$15]);
										if((index$i$14 == (i$var64 - 1))) {
											if((var27 == st[(i$var64 - 1)])) {
												if(fixedFlag$sample71) {
													// Processing sample task 71 of consumer random variable null.
													{
														// Copy of index so that its values can be safely substituted
														int index$i$28 = i$var64;
														{
															{
																{
																	{
																		// Increment the sample counter with the value sampled by sample task 71 of random
																		// variable var69
																		cv$countLocal[st[i$var64]] = (cv$countLocal[st[i$var64]] + cv$probabilitySample71Value16);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Processing random variable 51.
		{
			// Looking for a path between Sample 28 and consumer Categorical 51.
			{
				if((var27 == 0)) {
					if(!fixedFlag$sample53) {
						// Processing sample task 53 of consumer random variable null.
						{
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
										cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample53[cv$loopIndex] * cv$distributionProbability));
								}
							}
						}
					}
				}
			}
		}
		
		// Processing random variable 69.
		{
			// Looking for a path between Sample 28 and consumer Categorical 69.
			{
				for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
					if(fixedFlag$sample53) {
						if((0 == (i$var64 - 1))) {
							if((var27 == st[(i$var64 - 1)])) {
								if(!fixedFlag$sample71) {
									// Processing sample task 71 of consumer random variable null.
									{
										// Copy of index so that its values can be safely substituted
										int index$i$53 = i$var64;
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
													cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample71[((i$var64 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
											}
										}
									}
								}
							}
						}
					} else {
						if(true) {
							// Enumerating the possible outputs of Categorical 51.
							for(int index$sample53$37 = 0; index$sample53$37 < states; index$sample53$37 += 1) {
								int distributionTempVariable$var52$39 = index$sample53$37;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample53Value38 = (1.0 * distribution$sample53[index$sample53$37]);
								if((0 == (i$var64 - 1))) {
									if((var27 == st[(i$var64 - 1)])) {
										if(!fixedFlag$sample71) {
											// Processing sample task 71 of consumer random variable null.
											{
												// Copy of index so that its values can be safely substituted
												int index$i$55 = i$var64;
												{
													{
														// Declare and zero an accumulator for tracking the reached source probability space.
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															// Add the probability of this argument configuration.
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														
														// The probability of reaching the consumer with this set of consumer arguments
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample53Value38);
														
														// Merge the distribution probabilities into the count
														for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
															cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample71[((i$var64 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
					if(fixedFlag$sample71) {
						for(int index$i$44_1 = 1; index$i$44_1 < samples; index$i$44_1 += 1) {
							if((index$i$44_1 == (i$var64 - 1))) {
								if((var27 == st[(i$var64 - 1)])) {
									if(!fixedFlag$sample71) {
										// Processing sample task 71 of consumer random variable null.
										{
											// Copy of index so that its values can be safely substituted
											int index$i$57 = i$var64;
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
														cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample71[((i$var64 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
												}
											}
										}
									}
								}
							}
						}
					} else {
						for(int index$i$45 = 1; index$i$45 < samples; index$i$45 += 1) {
							if(true) {
								// Enumerating the possible outputs of Categorical 69.
								for(int index$sample71$46 = 0; index$sample71$46 < states; index$sample71$46 += 1) {
									int distributionTempVariable$var70$48 = index$sample71$46;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample71Value47 = (1.0 * distribution$sample71[((index$i$45 - 1) / 1)][index$sample71$46]);
									if((index$i$45 == (i$var64 - 1))) {
										if((var27 == st[(i$var64 - 1)])) {
											if(!fixedFlag$sample71) {
												// Processing sample task 71 of consumer random variable null.
												{
													// Copy of index so that its values can be safely substituted
													int index$i$59 = i$var64;
													{
														{
															// Declare and zero an accumulator for tracking the reached source probability space.
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																// Add the probability of this argument configuration.
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															
															// The probability of reaching the consumer with this set of consumer arguments
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample71Value47);
															
															// Merge the distribution probabilities into the count
															for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample71[((i$var64 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
														}
													}
												}
											}
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

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 45 drawn from Beta 32. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample45(int var43, int threadID$cv$var43, Rng RNG$) {
		// Local variable to record the number of true samples.
		double cv$sum = 0.0;
		
		// Local variable to record the number of samples.
		double cv$count = 0.0;
		{
			// Processing random variable 85.
			{
				// Looking for a path between Sample 45 and consumer Bernoulli 85.
				{
					for(int j = 0; j < samples; j += 1) {
						if(fixedFlag$sample53) {
							if((0 == j)) {
								if((var43 == st[j])) {
									// Processing sample task 87 of consumer random variable null.
									{
										{
											{
												{
													{
														// Include the value sampled by task 87 from random variable var85.
														// Increment the number of samples.
														cv$count = (cv$count + 1.0);
														
														// If the sample value was positive increase the count
														if(flips[j])
															cv$sum = (cv$sum + 1.0);
													}
												}
											}
										}
									}
								}
							}
						} else {
							if(true) {
								// Enumerating the possible outputs of Categorical 51.
								for(int index$sample53$3 = 0; index$sample53$3 < states; index$sample53$3 += 1) {
									int distributionTempVariable$var52$5 = index$sample53$3;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample53Value4 = (1.0 * distribution$sample53[index$sample53$3]);
									if((0 == j)) {
										if((var43 == st[j])) {
											// Processing sample task 87 of consumer random variable null.
											{
												{
													{
														{
															{
																// Include the value sampled by task 87 from random variable var85.
																// Increment the number of samples.
																cv$count = (cv$count + cv$probabilitySample53Value4);
																
																// If the sample value was positive increase the count
																if(flips[j])
																	cv$sum = (cv$sum + cv$probabilitySample53Value4);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int j = 0; j < samples; j += 1) {
						if(fixedFlag$sample71) {
							for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
								if((i$var64 == j)) {
									if((var43 == st[j])) {
										// Processing sample task 87 of consumer random variable null.
										{
											{
												{
													{
														{
															// Include the value sampled by task 87 from random variable var85.
															// Increment the number of samples.
															cv$count = (cv$count + 1.0);
															
															// If the sample value was positive increase the count
															if(flips[j])
																cv$sum = (cv$sum + 1.0);
														}
													}
												}
											}
										}
									}
								}
							}
						} else {
							for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
								if(true) {
									// Enumerating the possible outputs of Categorical 69.
									for(int index$sample71$12 = 0; index$sample71$12 < states; index$sample71$12 += 1) {
										int distributionTempVariable$var70$14 = index$sample71$12;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample71Value13 = (1.0 * distribution$sample71[((i$var64 - 1) / 1)][index$sample71$12]);
										if((i$var64 == j)) {
											if((var43 == st[j])) {
												// Processing sample task 87 of consumer random variable null.
												{
													{
														{
															{
																{
																	// Include the value sampled by task 87 from random variable var85.
																	// Increment the number of samples.
																	cv$count = (cv$count + cv$probabilitySample71Value13);
																	
																	// If the sample value was positive increase the count
																	if(flips[j])
																		cv$sum = (cv$sum + cv$probabilitySample71Value13);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		double var44 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		
		// Guards to ensure that bias is only updated when there is a valid path.
		{
			{
				bias[var43] = var44;
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 53 drawn from Categorical 51. Inference was performed using variable
	// marginalization.
	private final void sample53() {
		// Calculate the number of states to evaluate.
		int cv$numNumStates = 0;
		{
			// variable marginalization
			cv$numNumStates = Math.max(cv$numNumStates, states);
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var52$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
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
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var50;
				{
					// Constructing a random variable input for use later.
					double[] var50 = m[0];
					cv$temp$0$var50 = var50;
				}
				int cv$temp$1$$var820;
				{
					// Constructing a random variable input for use later.
					int $var820 = states;
					cv$temp$1$$var820 = $var820;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var820))?Math.log(cv$temp$0$var50[cv$currentValue]):Double.NEGATIVE_INFINITY));
				
				// Processing random variable 69.
				{
					// Looking for a path between Sample 53 and consumer Categorical 69.
					{
						int traceTempVariable$var67$1_1 = cv$currentValue;
						for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
							if((0 == (i$var64 - 1))) {
								if(fixedFlag$sample71) {
									// Processing sample task 71 of consumer random variable null.
									{
										// Copy of index so that its values can be safely substituted
										int index$i$3 = i$var64;
										
										// Set an accumulator to sum the probabilities for each possible configuration of
										// inputs.
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											// Enumerating the possible arguments for the variable Categorical 69 which is consuming
											// the output of Sample task 53.
											for(int var27 = 0; var27 < states; var27 += 1) {
												if((var27 == st[(i$var64 - 1)])) {
													{
														{
															double[] cv$temp$2$var68;
															{
																// Constructing a random variable input for use later.
																double[] var68 = m[traceTempVariable$var67$1_1];
																cv$temp$2$var68 = var68;
															}
															int cv$temp$3$$var831;
															{
																// Constructing a random variable input for use later.
																int $var831 = states;
																cv$temp$3$$var831 = $var831;
															}
															
															// Record the probability of sample task 71 generating output with current configuration.
															if(((Math.log(1.0) + (((0.0 <= st[i$var64]) && (st[i$var64] < cv$temp$3$$var831))?Math.log(cv$temp$2$var68[st[i$var64]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var64]) && (st[i$var64] < cv$temp$3$$var831))?Math.log(cv$temp$2$var68[st[i$var64]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																// If the second value is -infinity.
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var64]) && (st[i$var64] < cv$temp$3$$var831))?Math.log(cv$temp$2$var68[st[i$var64]]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var64]) && (st[i$var64] < cv$temp$3$$var831))?Math.log(cv$temp$2$var68[st[i$var64]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var64]) && (st[i$var64] < cv$temp$3$$var831))?Math.log(cv$temp$2$var68[st[i$var64]]):Double.NEGATIVE_INFINITY)));
															}
															
															// Recorded the probability of reaching sample task 71 with the current configuration.
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
				
				// Processing random variable 85.
				{
					// Looking for a path between Sample 53 and consumer Bernoulli 85.
					{
						int traceTempVariable$var83$6_1 = cv$currentValue;
						for(int j = 0; j < samples; j += 1) {
							if((0 == j)) {
								// Processing sample task 87 of consumer random variable null.
								{
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
										// the output of Sample task 53.
										for(int var43 = 0; var43 < states; var43 += 1) {
											if((var43 == st[j])) {
												{
													{
														double cv$temp$4$var84;
														{
															// Constructing a random variable input for use later.
															double var84 = bias[traceTempVariable$var83$6_1];
															cv$temp$4$var84 = var84;
														}
														
														// Record the probability of sample task 87 generating output with current configuration.
														if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var84)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															// If the second value is -infinity.
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var84));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var84)));
														}
														
														// Recorded the probability of reaching sample task 87 with the current configuration.
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
			
			// Processing random variable 69.
			{
				// Looking for a path between Sample 53 and consumer Categorical 69.
				{
					int traceTempVariable$var67$10_1 = cv$currentValue;
					for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
						if((0 == (i$var64 - 1))) {
							if(!fixedFlag$sample71) {
								// Processing sample task 71 of consumer random variable null.
								{
									// Copy of index so that its values can be safely substituted
									int index$i$12 = i$var64;
									
									// A local array to hold the accumulated distributions of the sample tasks for each
									// configuration of distributions.
									double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var69;
									
									// Zero all the elements in the distribution accumulator
									for(int cv$i = 0; cv$i < states; cv$i += 1)
										cv$accumulatedConsumerDistributions[cv$i] = 0.0;
									
									// Zero an accumulator to track the probabilities reached.
									double cv$reachedDistributionProbability = 0.0;
									
									// Enumerating the possible arguments for the variable Categorical 69 which is consuming
									// the output of Sample task 53.
									for(int var27 = 0; var27 < states; var27 += 1) {
										if((var27 == st[(i$var64 - 1)])) {
											{
												// Declare and zero an accumulator for tracking the reached source probability space.
												double scopeVariable$reachedSourceProbability = 0.0;
												{
													// Add the probability of this argument configuration.
													scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
												double[] cv$temp$5$var68;
												{
													// Constructing a random variable input for use later.
													double[] var68 = m[traceTempVariable$var67$10_1];
													cv$temp$5$var68 = var68;
												}
												int cv$temp$6$$var851;
												{
													// Constructing a random variable input for use later.
													int $var851 = states;
													cv$temp$6$$var851 = $var851;
												}
												
												// The probability of reaching the consumer with this set of consumer arguments
												double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
												
												// Record the reached distribution.
												cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
												
												// Add the current distribution to the distribution accumulator.
												DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$5$var68, cv$temp$6$$var851);
											}
										}
									}
									
									// A local copy of the samples' distribution.
									double[] cv$sampleDistribution = distribution$sample71[((i$var64 - 1) / 1)];
									
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
			
			// Save the calculated index value into the array of index value probabilities
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample53;
		
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

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 71 drawn from Categorical 69. Inference was performed using variable
	// marginalization.
	private final void sample71(int i$var64) {
		// Calculate the number of states to evaluate.
		int cv$numNumStates = 0;
		
		// Exploring all the possible state counts for random variable 69.
		// 
		// Copy of index so that its values can be safely substituted
		int index$i$1 = i$var64;
		
		// Enumerating the possible arguments for Categorical 69.
		if(fixedFlag$sample53) {
			if((0 == (i$var64 - 1))) {
				for(int var27 = 0; var27 < states; var27 += 1) {
					if((var27 == st[(i$var64 - 1)]))
						// variable marginalization
						cv$numNumStates = Math.max(cv$numNumStates, states);
				}
			}
		} else {
			if(true) {
				// Enumerating the possible outputs of Categorical 51.
				for(int index$sample53$3 = 0; index$sample53$3 < states; index$sample53$3 += 1) {
					int distributionTempVariable$var52$5 = index$sample53$3;
					
					// Update the probability of sampling this value from the distribution value.
					double cv$probabilitySample53Value4 = (1.0 * distribution$sample53[index$sample53$3]);
					if((0 == (i$var64 - 1))) {
						for(int var27 = 0; var27 < states; var27 += 1) {
							if((var27 == st[(i$var64 - 1)]))
								// variable marginalization
								cv$numNumStates = Math.max(cv$numNumStates, states);
						}
					}
				}
			}
		}
		
		// Enumerating the possible arguments for Categorical 69.
		if((index$i$1 == (i$var64 - 1))) {
			for(int var27 = 0; var27 < states; var27 += 1) {
				if((var27 == st[(i$var64 - 1)]))
					// variable marginalization
					cv$numNumStates = Math.max(cv$numNumStates, states);
			}
		}
		if(fixedFlag$sample71) {
			for(int index$i$10_1 = 1; index$i$10_1 < samples; index$i$10_1 += 1) {
				if((index$i$10_1 == (i$var64 - 1))) {
					for(int var27 = 0; var27 < states; var27 += 1) {
						if((var27 == st[(i$var64 - 1)]))
							// variable marginalization
							cv$numNumStates = Math.max(cv$numNumStates, states);
					}
				}
			}
		} else {
			for(int index$i$11 = 1; index$i$11 < samples; index$i$11 += 1) {
				if(!(index$i$11 == index$i$1)) {
					// Enumerating the possible outputs of Categorical 69.
					for(int index$sample71$12 = 0; index$sample71$12 < states; index$sample71$12 += 1) {
						int distributionTempVariable$var70$14 = index$sample71$12;
						
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample71Value13 = (1.0 * distribution$sample71[((index$i$11 - 1) / 1)][index$sample71$12]);
						if((index$i$11 == (i$var64 - 1))) {
							for(int var27 = 0; var27 < states; var27 += 1) {
								if((var27 == st[(i$var64 - 1)]))
									// variable marginalization
									cv$numNumStates = Math.max(cv$numNumStates, states);
							}
						}
					}
				}
			}
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var70$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 69 creating
			// sample task 71.
			// Copy of index so that its values can be safely substituted
			int index$i$19 = i$var64;
			
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
			
			// Enumerating the possible arguments for Categorical 69.
			if(fixedFlag$sample53) {
				if((0 == (i$var64 - 1))) {
					for(int var27 = 0; var27 < states; var27 += 1) {
						if((var27 == st[(i$var64 - 1)])) {
							// Record the reached probability density.
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$0$var68;
							{
								// Constructing a random variable input for use later.
								double[] var68 = m[st[(i$var64 - 1)]];
								cv$temp$0$var68 = var68;
							}
							int cv$temp$1$$var910;
							{
								// Constructing a random variable input for use later.
								int $var910 = states;
								cv$temp$1$$var910 = $var910;
							}
							
							// An accumulator to allow the value for each distribution to be constructed before
							// it is added to the index probabilities.
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var910))?Math.log(cv$temp$0$var68[cv$currentValue]):Double.NEGATIVE_INFINITY));
							
							// Processing random variable 69.
							{
								// Looking for a path between Sample 71 and consumer Categorical 69.
								{
									int traceTempVariable$var67$35_1 = cv$currentValue;
								}
							}
							
							// Processing random variable 85.
							{
								// Looking for a path between Sample 71 and consumer Bernoulli 85.
								{
									int traceTempVariable$var83$39_1 = cv$currentValue;
									for(int j = 0; j < samples; j += 1) {
										if((i$var64 == j)) {
											// Processing sample task 87 of consumer random variable null.
											{
												// Set an accumulator to sum the probabilities for each possible configuration of
												// inputs.
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												
												// Set an accumulator to record the consumer distributions not seen. Initially set
												// to 1 as seen values will be deducted from this value.
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
													// the output of Sample task 71.
													for(int var43 = 0; var43 < states; var43 += 1) {
														if((var43 == st[j])) {
															{
																{
																	double cv$temp$8$var84;
																	{
																		// Constructing a random variable input for use later.
																		double var84 = bias[traceTempVariable$var83$39_1];
																		cv$temp$8$var84 = var84;
																	}
																	
																	// Record the probability of sample task 87 generating output with current configuration.
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$8$var84)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$8$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$8$var84));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$8$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$8$var84)));
																	}
																	
																	// Recorded the probability of reaching sample task 87 with the current configuration.
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
			} else {
				if(true) {
					// Enumerating the possible outputs of Categorical 51.
					for(int index$sample53$21 = 0; index$sample53$21 < states; index$sample53$21 += 1) {
						int distributionTempVariable$var52$23 = index$sample53$21;
						
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample53Value22 = (1.0 * distribution$sample53[index$sample53$21]);
						if((0 == (i$var64 - 1))) {
							for(int var27 = 0; var27 < states; var27 += 1) {
								if((var27 == st[(i$var64 - 1)])) {
									// Record the reached probability density.
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample53Value22);
									double[] cv$temp$2$var68;
									{
										// Constructing a random variable input for use later.
										double[] var68 = m[st[(i$var64 - 1)]];
										cv$temp$2$var68 = var68;
									}
									int cv$temp$3$$var911;
									{
										// Constructing a random variable input for use later.
										int $var911 = states;
										cv$temp$3$$var911 = $var911;
									}
									
									// An accumulator to allow the value for each distribution to be constructed before
									// it is added to the index probabilities.
									double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample53Value22) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$$var911))?Math.log(cv$temp$2$var68[cv$currentValue]):Double.NEGATIVE_INFINITY));
									
									// Processing random variable 69.
									{
										// Looking for a path between Sample 71 and consumer Categorical 69.
										{
											int traceTempVariable$var67$36_1 = cv$currentValue;
										}
									}
									
									// Processing random variable 85.
									{
										// Looking for a path between Sample 71 and consumer Bernoulli 85.
										{
											int traceTempVariable$var83$40_1 = cv$currentValue;
											for(int j = 0; j < samples; j += 1) {
												if((i$var64 == j)) {
													// Processing sample task 87 of consumer random variable null.
													{
														// Set an accumulator to sum the probabilities for each possible configuration of
														// inputs.
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														
														// Set an accumulator to record the consumer distributions not seen. Initially set
														// to 1 as seen values will be deducted from this value.
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
															// the output of Sample task 71.
															for(int var43 = 0; var43 < states; var43 += 1) {
																if((var43 == st[j])) {
																	{
																		{
																			double cv$temp$9$var84;
																			{
																				// Constructing a random variable input for use later.
																				double var84 = bias[traceTempVariable$var83$40_1];
																				cv$temp$9$var84 = var84;
																			}
																			
																			// Record the probability of sample task 87 generating output with current configuration.
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$9$var84)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$9$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$9$var84));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$9$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$9$var84)));
																			}
																			
																			// Recorded the probability of reaching sample task 87 with the current configuration.
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
			
			// Enumerating the possible arguments for Categorical 69.
			int traceTempVariable$var67$27_1 = cv$currentValue;
			if((index$i$19 == (i$var64 - 1))) {
				for(int var27 = 0; var27 < states; var27 += 1) {
					if((var27 == st[(i$var64 - 1)])) {
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double[] cv$temp$4$var68;
						{
							// Constructing a random variable input for use later.
							double[] var68 = m[traceTempVariable$var67$27_1];
							cv$temp$4$var68 = var68;
						}
						int cv$temp$5$$var912;
						{
							// Constructing a random variable input for use later.
							int $var912 = states;
							cv$temp$5$$var912 = $var912;
						}
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$5$$var912))?Math.log(cv$temp$4$var68[cv$currentValue]):Double.NEGATIVE_INFINITY));
						
						// Processing random variable 69.
						{
							// Looking for a path between Sample 71 and consumer Categorical 69.
							{
								int traceTempVariable$var67$37_1 = cv$currentValue;
							}
						}
						
						// Processing random variable 85.
						{
							// Looking for a path between Sample 71 and consumer Bernoulli 85.
							{
								int traceTempVariable$var83$41_1 = cv$currentValue;
								for(int j = 0; j < samples; j += 1) {
									if((i$var64 == j)) {
										// Processing sample task 87 of consumer random variable null.
										{
											// Set an accumulator to sum the probabilities for each possible configuration of
											// inputs.
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											
											// Set an accumulator to record the consumer distributions not seen. Initially set
											// to 1 as seen values will be deducted from this value.
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
												// the output of Sample task 71.
												for(int var43 = 0; var43 < states; var43 += 1) {
													if((var43 == st[j])) {
														{
															{
																double cv$temp$10$var84;
																{
																	// Constructing a random variable input for use later.
																	double var84 = bias[traceTempVariable$var83$41_1];
																	cv$temp$10$var84 = var84;
																}
																
																// Record the probability of sample task 87 generating output with current configuration.
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$10$var84)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$10$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$10$var84));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$10$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$10$var84)));
																}
																
																// Recorded the probability of reaching sample task 87 with the current configuration.
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
			for(int index$i$28 = 1; index$i$28 < samples; index$i$28 += 1) {
				if(!(index$i$28 == index$i$19)) {
					// Enumerating the possible outputs of Categorical 69.
					for(int index$sample71$29 = 0; index$sample71$29 < states; index$sample71$29 += 1) {
						int distributionTempVariable$var70$31 = index$sample71$29;
						
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample71Value30 = (1.0 * distribution$sample71[((index$i$28 - 1) / 1)][index$sample71$29]);
						int traceTempVariable$var67$32_1 = cv$currentValue;
						if((index$i$28 == (i$var64 - 1))) {
							for(int var27 = 0; var27 < states; var27 += 1) {
								if((var27 == st[(i$var64 - 1)])) {
									// Record the reached probability density.
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample71Value30);
									double[] cv$temp$6$var68;
									{
										// Constructing a random variable input for use later.
										double[] var68 = m[traceTempVariable$var67$32_1];
										cv$temp$6$var68 = var68;
									}
									int cv$temp$7$$var913;
									{
										// Constructing a random variable input for use later.
										int $var913 = states;
										cv$temp$7$$var913 = $var913;
									}
									
									// An accumulator to allow the value for each distribution to be constructed before
									// it is added to the index probabilities.
									double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample71Value30) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$7$$var913))?Math.log(cv$temp$6$var68[cv$currentValue]):Double.NEGATIVE_INFINITY));
									
									// Processing random variable 69.
									{
										// Looking for a path between Sample 71 and consumer Categorical 69.
										{
											int traceTempVariable$var67$38_1 = distributionTempVariable$var70$31;
										}
									}
									
									// Processing random variable 85.
									{
										// Looking for a path between Sample 71 and consumer Bernoulli 85.
										{
											int traceTempVariable$var83$42_1 = distributionTempVariable$var70$31;
											for(int j = 0; j < samples; j += 1) {
												if((i$var64 == j)) {
													// Processing sample task 87 of consumer random variable null.
													{
														// Set an accumulator to sum the probabilities for each possible configuration of
														// inputs.
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														
														// Set an accumulator to record the consumer distributions not seen. Initially set
														// to 1 as seen values will be deducted from this value.
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
															// the output of Sample task 71.
															for(int var43 = 0; var43 < states; var43 += 1) {
																if((var43 == st[j])) {
																	{
																		{
																			double cv$temp$11$var84;
																			{
																				// Constructing a random variable input for use later.
																				double var84 = bias[traceTempVariable$var83$42_1];
																				cv$temp$11$var84 = var84;
																			}
																			
																			// Record the probability of sample task 87 generating output with current configuration.
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$11$var84)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$11$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$11$var84));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$11$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$11$var84)));
																			}
																			
																			// Recorded the probability of reaching sample task 87 with the current configuration.
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
			
			// Processing random variable 69.
			{
				// Looking for a path between Sample 71 and consumer Categorical 69.
				{
					int traceTempVariable$var67$55_1 = cv$currentValue;
					for(int index$i$55_2 = 1; index$i$55_2 < samples; index$i$55_2 += 1) {
						if((i$var64 == (index$i$55_2 - 1))) {
							// Processing sample task 71 of consumer random variable null.
							{
								// Copy of index so that its values can be safely substituted
								int index$i$57 = index$i$55_2;
								
								// A local array to hold the accumulated distributions of the sample tasks for each
								// configuration of distributions.
								double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var69;
								
								// Zero all the elements in the distribution accumulator
								for(int cv$i = 0; cv$i < states; cv$i += 1)
									cv$accumulatedConsumerDistributions[cv$i] = 0.0;
								
								// Zero an accumulator to track the probabilities reached.
								double cv$reachedDistributionProbability = 0.0;
								
								// Enumerating the possible arguments for the variable Categorical 69 which is consuming
								// the output of Sample task 71.
								for(int var27 = 0; var27 < states; var27 += 1) {
									if((var27 == st[(index$i$55_2 - 1)])) {
										{
											// Declare and zero an accumulator for tracking the reached source probability space.
											double scopeVariable$reachedSourceProbability = 0.0;
											
											// Enumerating the possible arguments for Categorical 69.
											if(fixedFlag$sample53) {
												if((0 == (i$var64 - 1))) {
													for(int index$var27$64_1 = 0; index$var27$64_1 < states; index$var27$64_1 += 1) {
														if((index$var27$64_1 == st[(i$var64 - 1)]))
															// Add the probability of this argument configuration.
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
													}
												}
											} else {
												if(true) {
													// Enumerating the possible outputs of Categorical 51.
													for(int index$sample53$60 = 0; index$sample53$60 < states; index$sample53$60 += 1) {
														int distributionTempVariable$var52$62 = index$sample53$60;
														
														// Update the probability of sampling this value from the distribution value.
														double cv$probabilitySample53Value61 = (1.0 * distribution$sample53[index$sample53$60]);
														if((0 == (i$var64 - 1))) {
															for(int index$var27$65_1 = 0; index$var27$65_1 < states; index$var27$65_1 += 1) {
																if((index$var27$65_1 == st[(i$var64 - 1)]))
																	// Add the probability of this argument configuration.
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample53Value61);
															}
														}
													}
												}
											}
											
											// Enumerating the possible arguments for Categorical 69.
											int traceTempVariable$var67$66_1 = cv$currentValue;
											if((index$i$19 == (i$var64 - 1))) {
												for(int index$var27$72_1 = 0; index$var27$72_1 < states; index$var27$72_1 += 1) {
													if((index$var27$72_1 == st[(i$var64 - 1)]))
														// Add the probability of this argument configuration.
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
											}
											for(int index$i$67 = 1; index$i$67 < samples; index$i$67 += 1) {
												if((!(index$i$67 == index$i$19) && !(index$i$67 == index$i$57))) {
													// Enumerating the possible outputs of Categorical 69.
													for(int index$sample71$68 = 0; index$sample71$68 < states; index$sample71$68 += 1) {
														int distributionTempVariable$var70$70 = index$sample71$68;
														
														// Update the probability of sampling this value from the distribution value.
														double cv$probabilitySample71Value69 = (1.0 * distribution$sample71[((index$i$67 - 1) / 1)][index$sample71$68]);
														int traceTempVariable$var67$71_1 = cv$currentValue;
														if((index$i$67 == (i$var64 - 1))) {
															for(int index$var27$73_1 = 0; index$var27$73_1 < states; index$var27$73_1 += 1) {
																if((index$var27$73_1 == st[(i$var64 - 1)]))
																	// Add the probability of this argument configuration.
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample71Value69);
															}
														}
													}
												}
											}
											double[] cv$temp$12$var68;
											{
												// Constructing a random variable input for use later.
												double[] var68 = m[traceTempVariable$var67$55_1];
												cv$temp$12$var68 = var68;
											}
											int cv$temp$13$$var962;
											{
												// Constructing a random variable input for use later.
												int $var962 = states;
												cv$temp$13$$var962 = $var962;
											}
											
											// The probability of reaching the consumer with this set of consumer arguments
											double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
											
											// Record the reached distribution.
											cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
											
											// Add the current distribution to the distribution accumulator.
											DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$12$var68, cv$temp$13$$var962);
										}
									}
								}
								
								// A local copy of the samples' distribution.
								double[] cv$sampleDistribution = distribution$sample71[((index$i$55_2 - 1) / 1)];
								
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
			
			// Save the calculated index value into the array of index value probabilities
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample71[((i$var64 - 1) / 1)];
		
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

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var28$countGlobal
		{
			// Allocation of cv$var28$countGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var28$countGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var28$countGlobal[cv$index] = new double[5];
			}
		}
		
		// Constructor for cv$distributionAccumulator$var69
		{
			// Variable to record the maximum value of Task Get 69. Initially set to the value
			// of putTask 29.
			int cv$var29$max = 5;
			
			// Allocation of cv$distributionAccumulator$var69 for single threaded execution
			cv$distributionAccumulator$var69 = new double[cv$var29$max];
		}
		
		// Constructor for cv$var52$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 51. Initially set to the value
			// of putTask 29.
			int cv$var29$max = 5;
			
			// Allocation of cv$var52$stateProbabilityGlobal for single threaded execution
			cv$var52$stateProbabilityGlobal = new double[cv$var29$max];
		}
		
		// Constructor for cv$var70$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 69. Initially set to the value
			// of putTask 29.
			int cv$var29$max = 5;
			
			// Allocation of cv$var70$stateProbabilityGlobal for single threaded execution
			cv$var70$stateProbabilityGlobal = new double[cv$var29$max];
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
		if(!fixedFlag$sample28) {
			// Constructor for m
			{
				m = new double[5][];
				for(int var27 = 0; var27 < 5; var27 += 1)
					m[var27] = new double[5];
			}
		}
		
		// If bias has not been set already allocate space.
		if(!fixedFlag$sample45) {
			// Constructor for bias
			{
				bias = new double[5];
			}
		}
		
		// If st has not been set already allocate space.
		if((!fixedFlag$sample53 || !fixedFlag$sample71)) {
			// Constructor for st
			{
				st = new int[length$flipsMeasured];
			}
		}
		
		// Constructor for flips
		{
			flips = new boolean[length$flipsMeasured];
		}
		
		// Constructor for distribution$sample53
		{
			distribution$sample53 = new double[5];
		}
		
		// Constructor for distribution$sample71
		{
			distribution$sample71 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)][];
			for(int i$var64 = 1; i$var64 < length$flipsMeasured; i$var64 += 1)
				distribution$sample71[((i$var64 - 1) / 1)] = new double[5];
		}
		
		// Constructor for logProbability$var69
		{
			logProbability$var69 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample71
		{
			logProbability$sample71 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)];
		}
		
		// Constructor for logProbability$var85
		{
			logProbability$var85 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample87
		{
			logProbability$sample87 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = m[var27];
						if(!fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, v, states, var28);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!fixedFlag$sample45)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
			if(!fixedFlag$sample71)
				st[i$var64] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], states);
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j = forStart$j; j < forEnd$j; j += 1)
						flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[st[j]]);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = m[var27];
						if(!fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, v, states, var28);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!fixedFlag$sample45)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		// Create local copy of variable probabilities.
		double[] cv$distribution$sample53 = distribution$sample53;
		double[] var50 = m[0];
		for(int index$var51 = 0; index$var51 < states; index$var51 += 1) {
			// Probability for this value
			double cv$value = (((0.0 <= index$var51) && (index$var51 < states))?var50[index$var51]:0.0);
			if(!fixedFlag$sample53)
				// Save the probability of each value
				cv$distribution$sample53[index$var51] = cv$value;
		}
		for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
			// Create local copy of variable probabilities.
			double[] cv$distribution$sample71 = distribution$sample71[((i$var64 - 1) / 1)];
			for(int index$var69 = 0; index$var69 < states; index$var69 += 1) {
				if(!fixedFlag$sample71)
					// Zero the probability of each value
					cv$distribution$sample71[index$var69] = 0.0;
			}
			
			// Iterate through possible values for var69's arguments.
			// 
			// Enumerating the possible arguments for Categorical 69.
			if(fixedFlag$sample53) {
				if((0 == (i$var64 - 1))) {
					for(int var27 = 0; var27 < states; var27 += 1) {
						if((var27 == st[(i$var64 - 1)])) {
							{
								if(!fixedFlag$sample71) {
									double[] var68 = m[st[(i$var64 - 1)]];
									for(int index$var69 = 0; index$var69 < states; index$var69 += 1)
										// Save the probability of each value
										cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (1.0 * (((0.0 <= index$var69) && (index$var69 < states))?var68[index$var69]:0.0)));
								}
							}
						}
					}
				}
			} else {
				if(true) {
					// Enumerating the possible outputs of Categorical 51.
					for(int index$sample53$2 = 0; index$sample53$2 < states; index$sample53$2 += 1) {
						int distributionTempVariable$var52$4 = index$sample53$2;
						
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample53Value3 = (1.0 * distribution$sample53[index$sample53$2]);
						if((0 == (i$var64 - 1))) {
							for(int var27 = 0; var27 < states; var27 += 1) {
								if((var27 == st[(i$var64 - 1)])) {
									{
										if(!fixedFlag$sample71) {
											double[] var68 = m[st[(i$var64 - 1)]];
											for(int index$var69 = 0; index$var69 < states; index$var69 += 1)
												// Save the probability of each value
												cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (cv$probabilitySample53Value3 * (((0.0 <= index$var69) && (index$var69 < states))?var68[index$var69]:0.0)));
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Enumerating the possible arguments for Categorical 69.
			if(fixedFlag$sample71) {
				for(int index$i$8_1 = 1; index$i$8_1 < samples; index$i$8_1 += 1) {
					if((index$i$8_1 == (i$var64 - 1))) {
						for(int var27 = 0; var27 < states; var27 += 1) {
							if((var27 == st[(i$var64 - 1)])) {
								{
									if(!fixedFlag$sample71) {
										double[] var68 = m[st[(i$var64 - 1)]];
										for(int index$var69 = 0; index$var69 < states; index$var69 += 1)
											// Save the probability of each value
											cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (1.0 * (((0.0 <= index$var69) && (index$var69 < states))?var68[index$var69]:0.0)));
									}
								}
							}
						}
					}
				}
			} else {
				for(int index$i$9 = 1; index$i$9 < samples; index$i$9 += 1) {
					if(true) {
						// Enumerating the possible outputs of Categorical 69.
						for(int index$sample71$10 = 0; index$sample71$10 < states; index$sample71$10 += 1) {
							int distributionTempVariable$var70$12 = index$sample71$10;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample71Value11 = (1.0 * distribution$sample71[((index$i$9 - 1) / 1)][index$sample71$10]);
							if((index$i$9 == (i$var64 - 1))) {
								for(int var27 = 0; var27 < states; var27 += 1) {
									if((var27 == st[(i$var64 - 1)])) {
										{
											if(!fixedFlag$sample71) {
												double[] var68 = m[st[(i$var64 - 1)]];
												for(int index$var69 = 0; index$var69 < states; index$var69 += 1)
													// Save the probability of each value
													cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (cv$probabilitySample71Value11 * (((0.0 <= index$var69) && (index$var69 < states))?var68[index$var69]:0.0)));
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
			double cv$var69$sum = 0.0;
			for(int index$var69 = 0; index$var69 < states; index$var69 += 1) {
				if(!fixedFlag$sample71)
					// sum the probability of each value
					cv$var69$sum = (cv$var69$sum + cv$distribution$sample71[index$var69]);
			}
			for(int index$var69 = 0; index$var69 < states; index$var69 += 1) {
				if(!fixedFlag$sample71)
					// Normalise the probability of each value
					cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] / cv$var69$sum);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = m[var27];
						if(!fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, v, states, var28);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!fixedFlag$sample45)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
			if(!fixedFlag$sample71)
				st[i$var64] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], states);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
							if(!fixedFlag$sample28)
								sample28(var27, threadID$var27, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
							if(!fixedFlag$sample45)
								sample45(var43, threadID$var43, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample53)
				sample53();
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
				if(!fixedFlag$sample71)
					sample71(i$var64);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i$var64 = (samples - ((((samples - 1) - 1) % 1) + 1)); i$var64 >= ((1 - 1) + 1); i$var64 -= 1) {
				if(!fixedFlag$sample71)
					sample71(i$var64);
			}
			if(!fixedFlag$sample53)
				sample53();
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
							if(!fixedFlag$sample45)
								sample45(var43, threadID$var43, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
							if(!fixedFlag$sample28)
								sample28(var27, threadID$var27, RNG$1);
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
			(int forStart$i$var13, int forEnd$i$var13, int threadID$i$var13, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var13 = forStart$i$var13; i$var13 < forEnd$i$var13; i$var13 += 1)
						v[i$var13] = 0.1;
			}
		);
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
		logProbability$var16 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var28 = 0.0;
		logProbability$var32 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$var44 = 0.0;
		logProbability$var51 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$var52 = 0.0;
		for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
			logProbability$var69[((i$var64 - 1) / 1)] = 0.0;
		if(!fixedProbFlag$sample71) {
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
				logProbability$sample71[((i$var64 - 1) / 1)] = 0.0;
		}
		for(int j = 0; j < samples; j += 1)
			logProbability$var85[((j - 0) / 1)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample87) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample87[((j - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		logProbabilityValue$sample87();
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
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityDistribution$sample53();
		logProbabilityDistribution$sample71();
		logProbabilityDistribution$sample87();
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
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample71();
		logProbabilityValue$sample87();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = m[var27];
						if(!fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, v, states, var28);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!fixedFlag$sample45)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
			if(!fixedFlag$sample71)
				st[i$var64] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], states);
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
	public final void setIntermediates() {}

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
		     + "model HMMTestPart7(boolean[] flipsMeasured) {\n"
		     + "        int states = 5;\n"
		     + "\n"
		     + "        double[] v = new double[states];\n"
		     + "        for(int i:[0..states))\n"
		     + "            v[i] = 0.1;\n"
		     + "        \n"
		     + "        double[][] m = dirichlet(v).sample(states);\n"
		     + "        double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "        int samples = flipsMeasured.length;\n"
		     + "        int[] st = new int[samples];\n"
		     + "\n"
		     + "        st[0] = categorical(m[0]).sampleDistribution();\n"
		     + "\n"
		     + "        for(int i:[1..samples))\n"
		     + "            st[i] = categorical(m[st[i-1]]).sampleDistribution();\n"
		     + "            \n"
		     + "        boolean[] flips = new boolean[samples];\n"
		     + "            \n"
		     + "        for(int j:[0..samples))\n"
		     + "            flips[j] = bernoulli(bias[st[j]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}