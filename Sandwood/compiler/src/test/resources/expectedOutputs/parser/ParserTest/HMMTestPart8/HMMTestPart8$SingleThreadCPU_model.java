package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart8$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMTestPart8$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private double[] cv$distributionAccumulator$var71;
	private double[] cv$var30$countGlobal;
	private double[] cv$var54$stateProbabilityGlobal;
	private double[] cv$var72$stateProbabilityGlobal;
	private double[][] distribution$sample75;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample48 = false;
	private boolean fixedFlag$sample57 = false;
	private boolean fixedFlag$sample75 = false;
	private boolean fixedFlag$sample91 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample48 = false;
	private boolean fixedProbFlag$sample57 = false;
	private boolean fixedProbFlag$sample75 = false;
	private boolean fixedProbFlag$sample91 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample75;
	private double[] logProbability$sample91;
	private double logProbability$st;
	private double logProbability$var18;
	private double logProbability$var30;
	private double logProbability$var34;
	private double logProbability$var46;
	private double logProbability$var53;
	private double logProbability$var54;
	private double[] logProbability$var71;
	private double[] logProbability$var87;
	private double[][] m;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[] st;
	private int states;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart8$SingleThreadCPU(ExecutionTarget target) {
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
		// Set bias with flag to mark that it has been set so another array doesn't need to
		// be constructed
		bias = cv$value;
		setFlag$bias = true;
		
		// Unset the fixed probability flag for sample 48 as it depends on bias.
		fixedProbFlag$sample48 = false;
		
		// Unset the fixed probability flag for sample 91 as it depends on bias.
		fixedProbFlag$sample91 = false;
	}

	// Getter for fixedFlag$sample31.
	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	// Setter for fixedFlag$sample31.
	@Override
	public final void set$fixedFlag$sample31(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample31 including if probabilities
		// need to be updated.
		fixedFlag$sample31 = cv$value;
		
		// Should the probability of sample 31 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample31 = (fixedFlag$sample31 && fixedProbFlag$sample31);
		
		// Should the probability of sample 57 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample57 = (fixedFlag$sample31 && fixedProbFlag$sample57);
		
		// Should the probability of sample 75 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample75 = (fixedFlag$sample31 && fixedProbFlag$sample75);
	}

	// Getter for fixedFlag$sample48.
	@Override
	public final boolean get$fixedFlag$sample48() {
		return fixedFlag$sample48;
	}

	// Setter for fixedFlag$sample48.
	@Override
	public final void set$fixedFlag$sample48(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample48 including if probabilities
		// need to be updated.
		fixedFlag$sample48 = cv$value;
		
		// Should the probability of sample 48 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample48 = (fixedFlag$sample48 && fixedProbFlag$sample48);
		
		// Should the probability of sample 91 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample91 = (fixedFlag$sample48 && fixedProbFlag$sample91);
	}

	// Getter for fixedFlag$sample57.
	@Override
	public final boolean get$fixedFlag$sample57() {
		return fixedFlag$sample57;
	}

	// Setter for fixedFlag$sample57.
	@Override
	public final void set$fixedFlag$sample57(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample57 including if probabilities
		// need to be updated.
		fixedFlag$sample57 = cv$value;
		
		// Should the probability of sample 57 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedProbFlag$sample57);
		
		// Should the probability of sample 75 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample75 = (fixedFlag$sample57 && fixedProbFlag$sample75);
		
		// Should the probability of sample 91 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample91 = (fixedFlag$sample57 && fixedProbFlag$sample91);
	}

	// Getter for fixedFlag$sample75.
	@Override
	public final boolean get$fixedFlag$sample75() {
		return fixedFlag$sample75;
	}

	// Setter for fixedFlag$sample75.
	@Override
	public final void set$fixedFlag$sample75(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample75 including if probabilities
		// need to be updated.
		fixedFlag$sample75 = cv$value;
		
		// Should the probability of sample 75 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample75 = (fixedFlag$sample75 && fixedProbFlag$sample75);
		
		// Should the probability of sample 91 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample91 = (fixedFlag$sample75 && fixedProbFlag$sample91);
	}

	// Getter for fixedFlag$sample91.
	@Override
	public final boolean get$fixedFlag$sample91() {
		return fixedFlag$sample91;
	}

	// Setter for fixedFlag$sample91.
	@Override
	public final void set$fixedFlag$sample91(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample91 including if probabilities
		// need to be updated.
		fixedFlag$sample91 = cv$value;
		
		// Should the probability of sample 91 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample91 = (fixedFlag$sample91 && fixedProbFlag$sample91);
	}

	// Getter for flips.
	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	// Setter for flips.
	@Override
	public final void set$flips(boolean[] cv$value) {
		// Set flags for all the side effects of flips including if probabilities need to
		// be updated.
		// Set flips with flag to mark that it has been set so another array doesn't need
		// to be constructed
		flips = cv$value;
		setFlag$flips = true;
		
		// Unset the fixed probability flag for sample 91 as it depends on flips.
		fixedProbFlag$sample91 = false;
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
		// Set m with flag to mark that it has been set so another array doesn't need to be
		// constructed
		m = cv$value;
		setFlag$m = true;
		
		// Unset the fixed probability flag for sample 31 as it depends on m.
		fixedProbFlag$sample31 = false;
		
		// Unset the fixed probability flag for sample 57 as it depends on m.
		fixedProbFlag$sample57 = false;
		
		// Unset the fixed probability flag for sample 75 as it depends on m.
		fixedProbFlag$sample75 = false;
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
		// Set st with flag to mark that it has been set so another array doesn't need to
		// be constructed
		st = cv$value;
		setFlag$st = true;
		
		// Unset the fixed probability flag for sample 57 as it depends on st.
		fixedProbFlag$sample57 = false;
		
		// Unset the fixed probability flag for sample 75 as it depends on st.
		fixedProbFlag$sample75 = false;
		
		// Unset the fixed probability flag for sample 91 as it depends on st.
		fixedProbFlag$sample91 = false;
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

	// Calculate the probability of the samples represented by sample75 using probability
	// distributions.
	private final void logProbabilityDistribution$sample75() {
		// Determine if we need to calculate the values for sample task 75 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample75) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample75) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Look for paths between the variable and the sample task 75 including any distribution
					// values.
					// 
					// Copy of index so that its values can be safely substituted
					int index$i$1 = i$var66;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[i$var66];
						
						// Enumerating the possible arguments for Categorical 71.
						if((0 == (i$var66 - 1))) {
							for(int var29 = 0; var29 < states; var29 += 1) {
								if((var29 == st[(i$var66 - 1)])) {
									{
										double[] var70 = m[st[(i$var66 - 1)]];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var70.length))?Math.log(var70[cv$sampleValue]):Double.NEGATIVE_INFINITY));
										
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
						
						// Enumerating the possible arguments for Categorical 71.
						if((index$i$1 == (i$var66 - 1))) {
							for(int var29 = 0; var29 < states; var29 += 1) {
								if((var29 == st[(i$var66 - 1)])) {
									{
										double[] var70 = m[st[(i$var66 - 1)]];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var70.length))?Math.log(var70[cv$sampleValue]):Double.NEGATIVE_INFINITY));
										
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
						if(fixedFlag$sample75) {
							for(int index$i$6_1 = 1; index$i$6_1 < samples; index$i$6_1 += 1) {
								if((index$i$6_1 == (i$var66 - 1))) {
									for(int var29 = 0; var29 < states; var29 += 1) {
										if((var29 == st[(i$var66 - 1)])) {
											{
												double[] var70 = m[st[(i$var66 - 1)]];
												
												// Store the value of the function call, so the function call is only made once.
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var70.length))?Math.log(var70[cv$sampleValue]):Double.NEGATIVE_INFINITY));
												
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
							for(int index$i$7 = 1; index$i$7 < samples; index$i$7 += 1) {
								if(!(index$i$7 == index$i$1)) {
									// Enumerating the possible outputs of Categorical 71.
									for(int index$sample75$8 = 0; index$sample75$8 < states; index$sample75$8 += 1) {
										int distributionTempVariable$var72$10 = index$sample75$8;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample75Value9 = (1.0 * distribution$sample75[((index$i$7 - 1) / 1)][index$sample75$8]);
										int traceTempVariable$var69$11_1 = distributionTempVariable$var72$10;
										if((index$i$7 == (i$var66 - 1))) {
											for(int var29 = 0; var29 < states; var29 += 1) {
												if((var29 == traceTempVariable$var69$11_1)) {
													{
														double[] var70 = m[traceTempVariable$var69$11_1];
														
														// Store the value of the function call, so the function call is only made once.
														double cv$weightedProbability = (Math.log(cv$probabilitySample75Value9) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var70.length))?Math.log(var70[cv$sampleValue]):Double.NEGATIVE_INFINITY));
														
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
														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample75Value9);
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
					logProbability$var71[((i$var66 - 1) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample75[((i$var66 - 1) / 1)] = cv$sampleProbability;
				}
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(fixedFlag$sample75)
					// Update the variable probability
					logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample75)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample75 = ((fixedFlag$sample75 && fixedFlag$sample31) && fixedFlag$sample57);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample75[((i$var66 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var71[((i$var66 - 1) / 1)] = cv$rvAccumulator;
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample75)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample75)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample91 using probability
	// distributions.
	private final void logProbabilityDistribution$sample91() {
		// Determine if we need to calculate the values for sample task 91 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample91) {
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
				
				// Look for paths between the variable and the sample task 91 including any distribution
				// values.
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flips[j];
					
					// Enumerating the possible arguments for Bernoulli 87.
					if((0 == j)) {
						for(int var45 = 0; var45 < states; var45 += 1) {
							if((var45 == st[j])) {
								{
									double var86 = bias[st[j]];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var86));
									
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
					
					// Enumerating the possible arguments for Bernoulli 87.
					if(fixedFlag$sample75) {
						for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
							if((i$var66 == j)) {
								for(int var45 = 0; var45 < states; var45 += 1) {
									if((var45 == st[j])) {
										{
											double var86 = bias[st[j]];
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var86));
											
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
						for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
							if(true) {
								// Enumerating the possible outputs of Categorical 71.
								for(int index$sample75$6 = 0; index$sample75$6 < states; index$sample75$6 += 1) {
									int distributionTempVariable$var72$8 = index$sample75$6;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample75Value7 = (1.0 * distribution$sample75[((i$var66 - 1) / 1)][index$sample75$6]);
									int traceTempVariable$var85$9_1 = distributionTempVariable$var72$8;
									if((i$var66 == j)) {
										for(int var45 = 0; var45 < states; var45 += 1) {
											if((var45 == traceTempVariable$var85$9_1)) {
												{
													double var86 = bias[traceTempVariable$var85$9_1];
													
													// Store the value of the function call, so the function call is only made once.
													double cv$weightedProbability = (Math.log(cv$probabilitySample75Value7) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var86));
													
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
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample75Value7);
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
				logProbability$var87[((j - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample91[((j - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample91 = (((fixedFlag$sample91 && fixedFlag$sample48) && fixedFlag$sample57) && fixedFlag$sample75);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample91[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var87[((j - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample31 using sampled
	// values.
	private final void logProbabilityValue$sample31() {
		// Determine if we need to calculate the values for sample task 31 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample31) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var29 = 0; var29 < states; var29 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double[] cv$sampleValue = m[var29];
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
			logProbability$var18 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var30 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample31 = fixedFlag$sample31;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var30;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var18 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample48 using sampled
	// values.
	private final void logProbabilityValue$sample48() {
		// Determine if we need to calculate the values for sample task 48 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample48) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var45 = 0; var45 < states; var45 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = bias[var45];
					{
						{
							double var32 = 1.0;
							double var33 = 1.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var32, var33));
							
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
			logProbability$var34 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var46 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample48)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample48 = fixedFlag$sample48;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var46;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var34 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample48)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample57 using sampled
	// values.
	private final void logProbabilityValue$sample57() {
		// Determine if we need to calculate the values for sample task 57 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample57) {
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
						double[] var52 = m[0];
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var52.length))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY));
						
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
			logProbability$var53 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$var54 = cv$sampleProbability;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedFlag$sample31);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var54;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var53 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample57)
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
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Copy of index so that its values can be safely substituted
				int index$i$1 = i$var66;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[i$var66];
					{
						{
							double[] var70 = m[st[(i$var66 - 1)]];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var70.length))?Math.log(var70[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
				logProbability$var71[((i$var66 - 1) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample75[((i$var66 - 1) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample75)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample75 = ((fixedFlag$sample75 && fixedFlag$sample31) && fixedFlag$sample57);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample75[((i$var66 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var71[((i$var66 - 1) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample75)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample91 using sampled
	// values.
	private final void logProbabilityValue$sample91() {
		// Determine if we need to calculate the values for sample task 91 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample91) {
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
							double var86 = bias[st[j]];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var86));
							
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
				logProbability$var87[((j - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample91[((j - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample91 = (((fixedFlag$sample91 && fixedFlag$sample48) && fixedFlag$sample57) && fixedFlag$sample75);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample91[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var87[((j - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 31 drawn from Dirichlet 18. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample31(int var29) {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = m[var29];
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var30$countGlobal;
		
		// Get the length of the array
		int cv$arrayLength = states;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 53.
			{
				// Looking for a path between Sample 31 and consumer Categorical 53.
				{
					if((var29 == 0)) {
						// Processing sample task 57 of consumer random variable null.
						{
							{
								{
									{
										{
											// Increment the sample counter with the value sampled by sample task 57 of random
											// variable var53
											cv$countLocal[st[0]] = (cv$countLocal[st[0]] + 1.0);
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Processing random variable 71.
			{
				// Looking for a path between Sample 31 and consumer Categorical 71.
				{
					for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
						if((0 == (i$var66 - 1))) {
							if((var29 == st[(i$var66 - 1)])) {
								if(fixedFlag$sample75) {
									// Processing sample task 75 of consumer random variable null.
									{
										// Copy of index so that its values can be safely substituted
										int index$i$17 = i$var66;
										{
											{
												{
													{
														// Increment the sample counter with the value sampled by sample task 75 of random
														// variable var71
														cv$countLocal[st[i$var66]] = (cv$countLocal[st[i$var66]] + 1.0);
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
						if(fixedFlag$sample75) {
							for(int index$i$8_1 = 1; index$i$8_1 < samples; index$i$8_1 += 1) {
								if((index$i$8_1 == (i$var66 - 1))) {
									if((var29 == st[(i$var66 - 1)])) {
										if(fixedFlag$sample75) {
											// Processing sample task 75 of consumer random variable null.
											{
												// Copy of index so that its values can be safely substituted
												int index$i$19 = i$var66;
												{
													{
														{
															{
																// Increment the sample counter with the value sampled by sample task 75 of random
																// variable var71
																cv$countLocal[st[i$var66]] = (cv$countLocal[st[i$var66]] + 1.0);
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
							for(int index$i$9 = 1; index$i$9 < samples; index$i$9 += 1) {
								if(true) {
									// Enumerating the possible outputs of Categorical 71.
									for(int index$sample75$10 = 0; index$sample75$10 < states; index$sample75$10 += 1) {
										int distributionTempVariable$var72$12 = index$sample75$10;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample75Value11 = (1.0 * distribution$sample75[((index$i$9 - 1) / 1)][index$sample75$10]);
										int traceTempVariable$var69$13_1 = distributionTempVariable$var72$12;
										if((index$i$9 == (i$var66 - 1))) {
											if((var29 == traceTempVariable$var69$13_1)) {
												if(fixedFlag$sample75) {
													// Processing sample task 75 of consumer random variable null.
													{
														// Copy of index so that its values can be safely substituted
														int index$i$21 = i$var66;
														{
															{
																{
																	{
																		// Increment the sample counter with the value sampled by sample task 75 of random
																		// variable var71
																		cv$countLocal[st[i$var66]] = (cv$countLocal[st[i$var66]] + cv$probabilitySample75Value11);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Processing random variable 71.
		{
			// Looking for a path between Sample 31 and consumer Categorical 71.
			{
				for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
					if((0 == (i$var66 - 1))) {
						if((var29 == st[(i$var66 - 1)])) {
							if(!fixedFlag$sample75) {
								// Processing sample task 75 of consumer random variable null.
								{
									// Copy of index so that its values can be safely substituted
									int index$i$38 = i$var66;
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
												cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample75[((i$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
										}
									}
								}
							}
						}
					}
				}
				for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
					if(fixedFlag$sample75) {
						for(int index$i$29_1 = 1; index$i$29_1 < samples; index$i$29_1 += 1) {
							if((index$i$29_1 == (i$var66 - 1))) {
								if((var29 == st[(i$var66 - 1)])) {
									if(!fixedFlag$sample75) {
										// Processing sample task 75 of consumer random variable null.
										{
											// Copy of index so that its values can be safely substituted
											int index$i$40 = i$var66;
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
														cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample75[((i$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
												}
											}
										}
									}
								}
							}
						}
					} else {
						for(int index$i$30 = 1; index$i$30 < samples; index$i$30 += 1) {
							if(true) {
								// Enumerating the possible outputs of Categorical 71.
								for(int index$sample75$31 = 0; index$sample75$31 < states; index$sample75$31 += 1) {
									int distributionTempVariable$var72$33 = index$sample75$31;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample75Value32 = (1.0 * distribution$sample75[((index$i$30 - 1) / 1)][index$sample75$31]);
									int traceTempVariable$var69$34_1 = distributionTempVariable$var72$33;
									if((index$i$30 == (i$var66 - 1))) {
										if((var29 == traceTempVariable$var69$34_1)) {
											if(!fixedFlag$sample75) {
												// Processing sample task 75 of consumer random variable null.
												{
													// Copy of index so that its values can be safely substituted
													int index$i$42 = i$var66;
													{
														{
															// Declare and zero an accumulator for tracking the reached source probability space.
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																// Add the probability of this argument configuration.
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															
															// The probability of reaching the consumer with this set of consumer arguments
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample75Value32);
															
															// Merge the distribution probabilities into the count
															for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample75[((i$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
														}
													}
												}
											}
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
	// by sample task 48 drawn from Beta 34. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample48(int var45) {
		// Local variable to record the number of true samples.
		double cv$sum = 0.0;
		
		// Local variable to record the number of samples.
		double cv$count = 0.0;
		{
			// Processing random variable 87.
			{
				// Looking for a path between Sample 48 and consumer Bernoulli 87.
				{
					for(int j = 0; j < samples; j += 1) {
						if((0 == j)) {
							if((var45 == st[j])) {
								// Processing sample task 91 of consumer random variable null.
								{
									{
										{
											{
												{
													// Include the value sampled by task 91 from random variable var87.
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
					for(int j = 0; j < samples; j += 1) {
						if(fixedFlag$sample75) {
							for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
								if((i$var66 == j)) {
									if((var45 == st[j])) {
										// Processing sample task 91 of consumer random variable null.
										{
											{
												{
													{
														{
															// Include the value sampled by task 91 from random variable var87.
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
							for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
								if(true) {
									// Enumerating the possible outputs of Categorical 71.
									for(int index$sample75$7 = 0; index$sample75$7 < states; index$sample75$7 += 1) {
										int distributionTempVariable$var72$9 = index$sample75$7;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample75Value8 = (1.0 * distribution$sample75[((i$var66 - 1) / 1)][index$sample75$7]);
										int traceTempVariable$var85$10_1 = distributionTempVariable$var72$9;
										if((i$var66 == j)) {
											if((var45 == traceTempVariable$var85$10_1)) {
												// Processing sample task 91 of consumer random variable null.
												{
													{
														{
															{
																{
																	// Include the value sampled by task 91 from random variable var87.
																	// Increment the number of samples.
																	cv$count = (cv$count + cv$probabilitySample75Value8);
																	
																	// If the sample value was positive increase the count
																	if(flips[j])
																		cv$sum = (cv$sum + cv$probabilitySample75Value8);
																}
															}
														}
													}
												}
											}
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
		double var46 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[var45] = var46;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 57 drawn from Categorical 53. Inference was performed using variable
	// marginalization.
	private final void sample57() {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		{
			// variable marginalization
			cv$noStates = Math.max(cv$noStates, states);
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var54$stateProbabilityGlobal;
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
			
			// Value of the variable at this index
			cv$currentValue = cv$valuePos;
			
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			int var54 = cv$currentValue;
			st[0] = cv$currentValue;
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var52;
				{
					// Constructing a random variable input for use later.
					double[] var52 = m[0];
					cv$temp$0$var52 = var52;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var52.length))?Math.log(cv$temp$0$var52[cv$currentValue]):Double.NEGATIVE_INFINITY));
				
				// Processing random variable 71.
				{
					// Looking for a path between Sample 57 and consumer Categorical 71.
					{
						int traceTempVariable$var69$1_1 = cv$currentValue;
						for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
							if((0 == (i$var66 - 1))) {
								if(fixedFlag$sample75) {
									// Processing sample task 75 of consumer random variable null.
									{
										// Copy of index so that its values can be safely substituted
										int index$i$3 = i$var66;
										
										// Set an accumulator to sum the probabilities for each possible configuration of
										// inputs.
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											// Enumerating the possible arguments for the variable Categorical 71 which is consuming
											// the output of Sample task 57.
											for(int var29 = 0; var29 < states; var29 += 1) {
												if((var29 == traceTempVariable$var69$1_1)) {
													{
														{
															double[] cv$temp$1$var70;
															{
																// Constructing a random variable input for use later.
																double[] var70 = m[traceTempVariable$var69$1_1];
																cv$temp$1$var70 = var70;
															}
															
															// Record the probability of sample task 75 generating output with current configuration.
															if(((Math.log(1.0) + (((0.0 <= st[i$var66]) && (st[i$var66] < cv$temp$1$var70.length))?Math.log(cv$temp$1$var70[st[i$var66]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var66]) && (st[i$var66] < cv$temp$1$var70.length))?Math.log(cv$temp$1$var70[st[i$var66]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																// If the second value is -infinity.
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var66]) && (st[i$var66] < cv$temp$1$var70.length))?Math.log(cv$temp$1$var70[st[i$var66]]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var66]) && (st[i$var66] < cv$temp$1$var70.length))?Math.log(cv$temp$1$var70[st[i$var66]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var66]) && (st[i$var66] < cv$temp$1$var70.length))?Math.log(cv$temp$1$var70[st[i$var66]]):Double.NEGATIVE_INFINITY)));
															}
															
															// Recorded the probability of reaching sample task 75 with the current configuration.
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
				
				// Processing random variable 87.
				{
					// Looking for a path between Sample 57 and consumer Bernoulli 87.
					{
						int traceTempVariable$var85$6_1 = cv$currentValue;
						for(int j = 0; j < samples; j += 1) {
							if((0 == j)) {
								// Processing sample task 91 of consumer random variable null.
								{
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										// Enumerating the possible arguments for the variable Bernoulli 87 which is consuming
										// the output of Sample task 57.
										for(int var45 = 0; var45 < states; var45 += 1) {
											if((var45 == traceTempVariable$var85$6_1)) {
												{
													{
														double cv$temp$2$var86;
														{
															// Constructing a random variable input for use later.
															double var86 = bias[traceTempVariable$var85$6_1];
															cv$temp$2$var86 = var86;
														}
														
														// Record the probability of sample task 91 generating output with current configuration.
														if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var86)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var86)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															// If the second value is -infinity.
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var86));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var86)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var86)));
														}
														
														// Recorded the probability of reaching sample task 91 with the current configuration.
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
			
			// Processing random variable 71.
			{
				// Looking for a path between Sample 57 and consumer Categorical 71.
				{
					int traceTempVariable$var69$10_1 = cv$currentValue;
					for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
						if((0 == (i$var66 - 1))) {
							if(!fixedFlag$sample75) {
								// Processing sample task 75 of consumer random variable null.
								{
									// Copy of index so that its values can be safely substituted
									int index$i$12 = i$var66;
									
									// A local array to hold the accumulated distributions of the sample tasks for each
									// configuration of distributions.
									double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var71;
									
									// Zero all the elements in the distribution accumulator
									for(int cv$i = 0; cv$i < states; cv$i += 1)
										cv$accumulatedConsumerDistributions[cv$i] = 0.0;
									
									// Zero an accumulator to track the probabilities reached.
									double cv$reachedDistributionProbability = 0.0;
									
									// Enumerating the possible arguments for the variable Categorical 71 which is consuming
									// the output of Sample task 57.
									for(int var29 = 0; var29 < states; var29 += 1) {
										if((var29 == traceTempVariable$var69$10_1)) {
											{
												// Declare and zero an accumulator for tracking the reached source probability space.
												double scopeVariable$reachedSourceProbability = 0.0;
												{
													// Add the probability of this argument configuration.
													scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
												double[] cv$temp$3$var70;
												{
													// Constructing a random variable input for use later.
													double[] var70 = m[traceTempVariable$var69$10_1];
													cv$temp$3$var70 = var70;
												}
												
												// The probability of reaching the consumer with this set of consumer arguments
												double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
												
												// Record the reached distribution.
												cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
												
												// Add the current distribution to the distribution accumulator.
												DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$3$var70);
											}
										}
									}
									
									// A local copy of the samples' distribution.
									double[] cv$sampleDistribution = distribution$sample75[((i$var66 - 1) / 1)];
									
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
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		int var54 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
		st[0] = var54;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 75 drawn from Categorical 71. Inference was performed using variable
	// marginalization.
	private final void sample75(int i$var66) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		
		// Exploring all the possible state counts for random variable 71.
		// 
		// Copy of index so that its values can be safely substituted
		int index$i$1 = i$var66;
		
		// Enumerating the possible arguments for Categorical 71.
		if((0 == (i$var66 - 1))) {
			for(int var29 = 0; var29 < states; var29 += 1) {
				if((var29 == st[(i$var66 - 1)]))
					// variable marginalization
					cv$noStates = Math.max(cv$noStates, states);
			}
		}
		
		// Enumerating the possible arguments for Categorical 71.
		if((index$i$1 == (i$var66 - 1))) {
			for(int var29 = 0; var29 < states; var29 += 1) {
				if((var29 == st[(i$var66 - 1)]))
					// variable marginalization
					cv$noStates = Math.max(cv$noStates, states);
			}
		}
		if(fixedFlag$sample75) {
			for(int index$i$5_1 = 1; index$i$5_1 < samples; index$i$5_1 += 1) {
				if((index$i$5_1 == (i$var66 - 1))) {
					for(int var29 = 0; var29 < states; var29 += 1) {
						if((var29 == st[(i$var66 - 1)]))
							// variable marginalization
							cv$noStates = Math.max(cv$noStates, states);
					}
				}
			}
		} else {
			for(int index$i$6 = 1; index$i$6 < samples; index$i$6 += 1) {
				if(!(index$i$6 == index$i$1)) {
					// Enumerating the possible outputs of Categorical 71.
					for(int index$sample75$7 = 0; index$sample75$7 < states; index$sample75$7 += 1) {
						int distributionTempVariable$var72$9 = index$sample75$7;
						
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample75Value8 = (1.0 * distribution$sample75[((index$i$6 - 1) / 1)][index$sample75$7]);
						int traceTempVariable$var69$10_1 = distributionTempVariable$var72$9;
						if((index$i$6 == (i$var66 - 1))) {
							for(int var29 = 0; var29 < states; var29 += 1) {
								if((var29 == traceTempVariable$var69$10_1))
									// variable marginalization
									cv$noStates = Math.max(cv$noStates, states);
							}
						}
					}
				}
			}
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var72$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 71 creating
			// sample task 75.
			// Copy of index so that its values can be safely substituted
			int index$i$14 = i$var66;
			
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
			
			// Enumerating the possible arguments for Categorical 71.
			if((0 == (i$var66 - 1))) {
				for(int var29 = 0; var29 < states; var29 += 1) {
					if((var29 == st[(i$var66 - 1)])) {
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double[] cv$temp$0$var70;
						{
							// Constructing a random variable input for use later.
							double[] var70 = m[st[(i$var66 - 1)]];
							cv$temp$0$var70 = var70;
						}
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var70.length))?Math.log(cv$temp$0$var70[cv$currentValue]):Double.NEGATIVE_INFINITY));
						
						// Processing random variable 71.
						{
							// Looking for a path between Sample 75 and consumer Categorical 71.
							{
								int traceTempVariable$var69$25_1 = cv$currentValue;
							}
						}
						
						// Processing random variable 87.
						{
							// Looking for a path between Sample 75 and consumer Bernoulli 87.
							{
								int traceTempVariable$var85$28_1 = cv$currentValue;
								for(int j = 0; j < samples; j += 1) {
									if((i$var66 == j)) {
										// Processing sample task 91 of consumer random variable null.
										{
											// Set an accumulator to sum the probabilities for each possible configuration of
											// inputs.
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											
											// Set an accumulator to record the consumer distributions not seen. Initially set
											// to 1 as seen values will be deducted from this value.
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												// Enumerating the possible arguments for the variable Bernoulli 87 which is consuming
												// the output of Sample task 75.
												for(int var45 = 0; var45 < states; var45 += 1) {
													if((var45 == traceTempVariable$var85$28_1)) {
														{
															{
																double cv$temp$3$var86;
																{
																	// Constructing a random variable input for use later.
																	double var86 = bias[traceTempVariable$var85$28_1];
																	cv$temp$3$var86 = var86;
																}
																
																// Record the probability of sample task 91 generating output with current configuration.
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var86)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var86)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var86));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var86)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var86)));
																}
																
																// Recorded the probability of reaching sample task 91 with the current configuration.
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
			
			// Enumerating the possible arguments for Categorical 71.
			int traceTempVariable$var69$17_1 = cv$currentValue;
			if((index$i$14 == (i$var66 - 1))) {
				for(int var29 = 0; var29 < states; var29 += 1) {
					if((var29 == traceTempVariable$var69$17_1)) {
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double[] cv$temp$1$var70;
						{
							// Constructing a random variable input for use later.
							double[] var70 = m[traceTempVariable$var69$17_1];
							cv$temp$1$var70 = var70;
						}
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var70.length))?Math.log(cv$temp$1$var70[cv$currentValue]):Double.NEGATIVE_INFINITY));
						
						// Processing random variable 71.
						{
							// Looking for a path between Sample 75 and consumer Categorical 71.
							{
								int traceTempVariable$var69$26_1 = cv$currentValue;
							}
						}
						
						// Processing random variable 87.
						{
							// Looking for a path between Sample 75 and consumer Bernoulli 87.
							{
								int traceTempVariable$var85$29_1 = cv$currentValue;
								for(int j = 0; j < samples; j += 1) {
									if((i$var66 == j)) {
										// Processing sample task 91 of consumer random variable null.
										{
											// Set an accumulator to sum the probabilities for each possible configuration of
											// inputs.
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											
											// Set an accumulator to record the consumer distributions not seen. Initially set
											// to 1 as seen values will be deducted from this value.
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												// Enumerating the possible arguments for the variable Bernoulli 87 which is consuming
												// the output of Sample task 75.
												for(int var45 = 0; var45 < states; var45 += 1) {
													if((var45 == traceTempVariable$var85$29_1)) {
														{
															{
																double cv$temp$4$var86;
																{
																	// Constructing a random variable input for use later.
																	double var86 = bias[traceTempVariable$var85$29_1];
																	cv$temp$4$var86 = var86;
																}
																
																// Record the probability of sample task 91 generating output with current configuration.
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var86)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var86)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var86));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var86)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var86)));
																}
																
																// Recorded the probability of reaching sample task 91 with the current configuration.
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
			for(int index$i$18 = 1; index$i$18 < samples; index$i$18 += 1) {
				if(!(index$i$18 == index$i$14)) {
					// Enumerating the possible outputs of Categorical 71.
					for(int index$sample75$19 = 0; index$sample75$19 < states; index$sample75$19 += 1) {
						int distributionTempVariable$var72$21 = index$sample75$19;
						
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample75Value20 = (1.0 * distribution$sample75[((index$i$18 - 1) / 1)][index$sample75$19]);
						int traceTempVariable$var69$22_1 = distributionTempVariable$var72$21;
						if((index$i$18 == (i$var66 - 1))) {
							for(int var29 = 0; var29 < states; var29 += 1) {
								if((var29 == traceTempVariable$var69$22_1)) {
									// Record the reached probability density.
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample75Value20);
									double[] cv$temp$2$var70;
									{
										// Constructing a random variable input for use later.
										double[] var70 = m[traceTempVariable$var69$22_1];
										cv$temp$2$var70 = var70;
									}
									
									// An accumulator to allow the value for each distribution to be constructed before
									// it is added to the index probabilities.
									double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample75Value20) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var70.length))?Math.log(cv$temp$2$var70[cv$currentValue]):Double.NEGATIVE_INFINITY));
									
									// Processing random variable 71.
									{
										// Looking for a path between Sample 75 and consumer Categorical 71.
										{
											int traceTempVariable$var69$27_1 = cv$currentValue;
										}
									}
									
									// Processing random variable 87.
									{
										// Looking for a path between Sample 75 and consumer Bernoulli 87.
										{
											int traceTempVariable$var85$30_1 = cv$currentValue;
											for(int j = 0; j < samples; j += 1) {
												if((i$var66 == j)) {
													// Processing sample task 91 of consumer random variable null.
													{
														// Set an accumulator to sum the probabilities for each possible configuration of
														// inputs.
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														
														// Set an accumulator to record the consumer distributions not seen. Initially set
														// to 1 as seen values will be deducted from this value.
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															// Enumerating the possible arguments for the variable Bernoulli 87 which is consuming
															// the output of Sample task 75.
															for(int var45 = 0; var45 < states; var45 += 1) {
																if((var45 == traceTempVariable$var85$30_1)) {
																	{
																		{
																			double cv$temp$5$var86;
																			{
																				// Constructing a random variable input for use later.
																				double var86 = bias[traceTempVariable$var85$30_1];
																				cv$temp$5$var86 = var86;
																			}
																			
																			// Record the probability of sample task 91 generating output with current configuration.
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var86)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var86)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var86));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var86)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var86)));
																			}
																			
																			// Recorded the probability of reaching sample task 91 with the current configuration.
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
			
			// Processing random variable 71.
			{
				// Looking for a path between Sample 75 and consumer Categorical 71.
				{
					int traceTempVariable$var69$40_1 = cv$currentValue;
					for(int index$i$40_2 = 1; index$i$40_2 < samples; index$i$40_2 += 1) {
						if((i$var66 == (index$i$40_2 - 1))) {
							// Processing sample task 75 of consumer random variable null.
							{
								// Copy of index so that its values can be safely substituted
								int index$i$42 = index$i$40_2;
								
								// A local array to hold the accumulated distributions of the sample tasks for each
								// configuration of distributions.
								double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var71;
								
								// Zero all the elements in the distribution accumulator
								for(int cv$i = 0; cv$i < states; cv$i += 1)
									cv$accumulatedConsumerDistributions[cv$i] = 0.0;
								
								// Zero an accumulator to track the probabilities reached.
								double cv$reachedDistributionProbability = 0.0;
								
								// Enumerating the possible arguments for the variable Categorical 71 which is consuming
								// the output of Sample task 75.
								for(int var29 = 0; var29 < states; var29 += 1) {
									if((var29 == traceTempVariable$var69$40_1)) {
										{
											// Declare and zero an accumulator for tracking the reached source probability space.
											double scopeVariable$reachedSourceProbability = 0.0;
											
											// Enumerating the possible arguments for Categorical 71.
											if((0 == (i$var66 - 1))) {
												for(int index$var29$45_1 = 0; index$var29$45_1 < states; index$var29$45_1 += 1) {
													if((index$var29$45_1 == st[(i$var66 - 1)]))
														// Add the probability of this argument configuration.
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
											}
											
											// Enumerating the possible arguments for Categorical 71.
											int traceTempVariable$var69$46_1 = cv$currentValue;
											if((index$i$14 == (i$var66 - 1))) {
												for(int index$var29$52_1 = 0; index$var29$52_1 < states; index$var29$52_1 += 1) {
													if((index$var29$52_1 == traceTempVariable$var69$46_1))
														// Add the probability of this argument configuration.
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
											}
											for(int index$i$47 = 1; index$i$47 < samples; index$i$47 += 1) {
												if((!(index$i$47 == index$i$14) && !(index$i$47 == index$i$42))) {
													// Enumerating the possible outputs of Categorical 71.
													for(int index$sample75$48 = 0; index$sample75$48 < states; index$sample75$48 += 1) {
														int distributionTempVariable$var72$50 = index$sample75$48;
														
														// Update the probability of sampling this value from the distribution value.
														double cv$probabilitySample75Value49 = (1.0 * distribution$sample75[((index$i$47 - 1) / 1)][index$sample75$48]);
														int traceTempVariable$var69$51_1 = distributionTempVariable$var72$50;
														if((index$i$47 == (i$var66 - 1))) {
															for(int index$var29$53_1 = 0; index$var29$53_1 < states; index$var29$53_1 += 1) {
																if((index$var29$53_1 == traceTempVariable$var69$51_1))
																	// Add the probability of this argument configuration.
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample75Value49);
															}
														}
													}
												}
											}
											double[] cv$temp$6$var70;
											{
												// Constructing a random variable input for use later.
												double[] var70 = m[traceTempVariable$var69$40_1];
												cv$temp$6$var70 = var70;
											}
											
											// The probability of reaching the consumer with this set of consumer arguments
											double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
											
											// Record the reached distribution.
											cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
											
											// Add the current distribution to the distribution accumulator.
											DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$6$var70);
										}
									}
								}
								
								// A local copy of the samples' distribution.
								double[] cv$sampleDistribution = distribution$sample75[((index$i$40_2 - 1) / 1)];
								
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
		double[] cv$localProbability = distribution$sample75[((i$var66 - 1) / 1)];
		
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
				cv$localProbability[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var30$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			for(int var29 = 0; var29 < 5; var29 += 1)
				cv$max = Math.max(cv$max, 5);
			
			// Allocation of cv$var30$countGlobal for single threaded execution
			cv$var30$countGlobal = new double[cv$max];
		}
		
		// Constructor for cv$distributionAccumulator$var71
		{
			// Variable to record the maximum value of Task Get 73. Initially set to the value
			// of putTask 32.
			int cv$var31$max = 5;
			
			// Allocation of cv$distributionAccumulator$var71 for single threaded execution
			cv$distributionAccumulator$var71 = new double[cv$var31$max];
		}
		
		// Constructor for cv$var54$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 55. Initially set to the value
			// of putTask 32.
			int cv$var31$max = 5;
			
			// Allocation of cv$var54$stateProbabilityGlobal for single threaded execution
			cv$var54$stateProbabilityGlobal = new double[cv$var31$max];
		}
		
		// Constructor for cv$var72$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 73. Initially set to the value
			// of putTask 32.
			int cv$var31$max = 5;
			
			// Allocation of cv$var72$stateProbabilityGlobal for single threaded execution
			cv$var72$stateProbabilityGlobal = new double[cv$var31$max];
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
				for(int var29 = 0; var29 < 5; var29 += 1)
					m[var29] = new double[5];
			}
		}
		
		// If bias has not been set already allocate space.
		if(!setFlag$bias) {
			// Constructor for bias
			{
				bias = new double[5];
			}
		}
		
		// If st has not been set already allocate space.
		if(!setFlag$st) {
			// Constructor for st
			{
				st = new int[length$flipsMeasured];
			}
		}
		
		// If flips has not been set already allocate space.
		if(!setFlag$flips) {
			// Constructor for flips
			{
				flips = new boolean[length$flipsMeasured];
			}
		}
		
		// Constructor for distribution$sample75
		{
			distribution$sample75 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)][];
			for(int i$var66 = 1; i$var66 < length$flipsMeasured; i$var66 += 1)
				distribution$sample75[((i$var66 - 1) / 1)] = new double[5];
		}
		
		// Constructor for logProbability$var71
		{
			logProbability$var71 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample75
		{
			logProbability$sample75 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)];
		}
		
		// Constructor for logProbability$var87
		{
			logProbability$var87 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample91
		{
			logProbability$sample91 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		for(int var29 = 0; var29 < states; var29 += 1) {
			double[] var30 = m[var29];
			if(!fixedFlag$sample31)
				DistributionSampling.sampleDirichlet(RNG$, v, var30);
		}
		for(int var45 = 0; var45 < states; var45 += 1) {
			if(!fixedFlag$sample48)
				bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample57)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
			if(!fixedFlag$sample75)
				st[i$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var66 - 1)]]);
		}
		for(int j = 0; j < samples; j += 1) {
			if(!fixedFlag$sample91)
				flips[j] = DistributionSampling.sampleBernoulli(RNG$, bias[st[j]]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var29 = 0; var29 < states; var29 += 1) {
			double[] var30 = m[var29];
			if(!fixedFlag$sample31)
				DistributionSampling.sampleDirichlet(RNG$, v, var30);
		}
		for(int var45 = 0; var45 < states; var45 += 1) {
			if(!fixedFlag$sample48)
				bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample57)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
			// Create local copy of variable probabilities.
			double[] cv$distribution$sample75 = distribution$sample75[((i$var66 - 1) / 1)];
			for(int index$var71 = 0; index$var71 < states; index$var71 += 1) {
				if(!fixedFlag$sample75)
					// Zero the probability of each value
					cv$distribution$sample75[index$var71] = 0.0;
			}
			
			// Iterate through possible values for var71's arguments.
			// 
			// Enumerating the possible arguments for Categorical 71.
			if((0 == (i$var66 - 1))) {
				for(int var29 = 0; var29 < states; var29 += 1) {
					if((var29 == st[(i$var66 - 1)])) {
						{
							if(!fixedFlag$sample75) {
								double[] var70 = m[st[(i$var66 - 1)]];
								for(int index$var71 = 0; index$var71 < states; index$var71 += 1)
									// Save the probability of each value
									cv$distribution$sample75[index$var71] = (cv$distribution$sample75[index$var71] + (1.0 * (((0.0 <= index$var71) && (index$var71 < var70.length))?var70[index$var71]:0.0)));
							}
						}
					}
				}
			}
			
			// Enumerating the possible arguments for Categorical 71.
			if(fixedFlag$sample75) {
				for(int index$i$3_1 = 1; index$i$3_1 < samples; index$i$3_1 += 1) {
					if((index$i$3_1 == (i$var66 - 1))) {
						for(int var29 = 0; var29 < states; var29 += 1) {
							if((var29 == st[(i$var66 - 1)])) {
								{
									if(!fixedFlag$sample75) {
										double[] var70 = m[st[(i$var66 - 1)]];
										for(int index$var71 = 0; index$var71 < states; index$var71 += 1)
											// Save the probability of each value
											cv$distribution$sample75[index$var71] = (cv$distribution$sample75[index$var71] + (1.0 * (((0.0 <= index$var71) && (index$var71 < var70.length))?var70[index$var71]:0.0)));
									}
								}
							}
						}
					}
				}
			} else {
				for(int index$i$4 = 1; index$i$4 < samples; index$i$4 += 1) {
					if(true) {
						// Enumerating the possible outputs of Categorical 71.
						for(int index$sample75$5 = 0; index$sample75$5 < states; index$sample75$5 += 1) {
							int distributionTempVariable$var72$7 = index$sample75$5;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample75Value6 = (1.0 * distribution$sample75[((index$i$4 - 1) / 1)][index$sample75$5]);
							int traceTempVariable$var69$8_1 = distributionTempVariable$var72$7;
							if((index$i$4 == (i$var66 - 1))) {
								for(int var29 = 0; var29 < states; var29 += 1) {
									if((var29 == traceTempVariable$var69$8_1)) {
										{
											if(!fixedFlag$sample75) {
												double[] var70 = m[traceTempVariable$var69$8_1];
												for(int index$var71 = 0; index$var71 < states; index$var71 += 1)
													// Save the probability of each value
													cv$distribution$sample75[index$var71] = (cv$distribution$sample75[index$var71] + (cv$probabilitySample75Value6 * (((0.0 <= index$var71) && (index$var71 < var70.length))?var70[index$var71]:0.0)));
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
			double cv$var71$sum = 0.0;
			for(int index$var71 = 0; index$var71 < states; index$var71 += 1) {
				if(!fixedFlag$sample75)
					// sum the probability of each value
					cv$var71$sum = (cv$var71$sum + cv$distribution$sample75[index$var71]);
			}
			for(int index$var71 = 0; index$var71 < states; index$var71 += 1) {
				if(!fixedFlag$sample75)
					// Normalise the probability of each value
					cv$distribution$sample75[index$var71] = (cv$distribution$sample75[index$var71] / cv$var71$sum);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var29 = 0; var29 < states; var29 += 1) {
			double[] var30 = m[var29];
			if(!fixedFlag$sample31)
				DistributionSampling.sampleDirichlet(RNG$, v, var30);
		}
		for(int var45 = 0; var45 < states; var45 += 1) {
			if(!fixedFlag$sample48)
				bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample57)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
			if(!fixedFlag$sample75)
				st[i$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var66 - 1)]]);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			for(int var29 = 0; var29 < states; var29 += 1) {
				if(!fixedFlag$sample31)
					sample31(var29);
			}
			for(int var45 = 0; var45 < states; var45 += 1) {
				if(!fixedFlag$sample48)
					sample48(var45);
			}
			if(!fixedFlag$sample57)
				sample57();
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
				if(!fixedFlag$sample75)
					sample75(i$var66);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i$var66 = (samples - ((((samples - 1) - 1) % 1) + 1)); i$var66 >= ((1 - 1) + 1); i$var66 -= 1) {
				if(!fixedFlag$sample75)
					sample75(i$var66);
			}
			if(!fixedFlag$sample57)
				sample57();
			for(int var45 = (states - ((((states - 1) - 0) % 1) + 1)); var45 >= ((0 - 1) + 1); var45 -= 1) {
				if(!fixedFlag$sample48)
					sample48(var45);
			}
			for(int var29 = (states - ((((states - 1) - 0) % 1) + 1)); var29 >= ((0 - 1) + 1); var29 -= 1) {
				if(!fixedFlag$sample31)
					sample31(var29);
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
		for(int i$var15 = 0; i$var15 < 5; i$var15 += 1)
			v[i$var15] = 0.1;
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
		logProbability$var18 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$var30 = 0.0;
		logProbability$var34 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample48)
			logProbability$var46 = 0.0;
		logProbability$var53 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample57)
			logProbability$var54 = 0.0;
		for(int i$var66 = 1; i$var66 < samples; i$var66 += 1)
			logProbability$var71[((i$var66 - 1) / 1)] = 0.0;
		if(!fixedProbFlag$sample75) {
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1)
				logProbability$sample75[((i$var66 - 1) / 1)] = 0.0;
		}
		for(int j = 0; j < samples; j += 1)
			logProbability$var87[((j - 0) / 1)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample91) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample91[((j - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample31)
			logProbabilityValue$sample31();
		if(fixedFlag$sample48)
			logProbabilityValue$sample48();
		if(fixedFlag$sample57)
			logProbabilityValue$sample57();
		logProbabilityValue$sample91();
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
		logProbabilityValue$sample31();
		logProbabilityValue$sample48();
		logProbabilityValue$sample57();
		logProbabilityDistribution$sample75();
		logProbabilityDistribution$sample91();
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
		logProbabilityValue$sample31();
		logProbabilityValue$sample48();
		logProbabilityValue$sample57();
		logProbabilityValue$sample75();
		logProbabilityValue$sample91();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		for(int var29 = 0; var29 < states; var29 += 1) {
			double[] var30 = m[var29];
			if(!fixedFlag$sample31)
				DistributionSampling.sampleDirichlet(RNG$, v, var30);
		}
		for(int var45 = 0; var45 < states; var45 += 1) {
			if(!fixedFlag$sample48)
				bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample57)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
			if(!fixedFlag$sample75)
				st[i$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var66 - 1)]]);
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
		     + "model HMMTestPart8(boolean[] flipsMeasured) {\n"
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
		     + "        st[0] = categorical(m[0]).sample();\n"
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