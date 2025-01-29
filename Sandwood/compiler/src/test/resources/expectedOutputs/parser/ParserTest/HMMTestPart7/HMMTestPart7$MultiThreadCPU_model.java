package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart7$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart7$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private double[] cv$distributionAccumulator$var42;
	private double[][] cv$var16$countGlobal;
	private double[] cv$var33$stateProbabilityGlobal;
	private double[] cv$var43$stateProbabilityGlobal;
	private double[] distribution$sample36;
	private double[][] distribution$sample46;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample27 = false;
	private boolean fixedFlag$sample36 = false;
	private boolean fixedFlag$sample46 = false;
	private boolean fixedFlag$sample55 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample27 = false;
	private boolean fixedProbFlag$sample36 = false;
	private boolean fixedProbFlag$sample46 = false;
	private boolean fixedProbFlag$sample55 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample46;
	private double[] logProbability$sample55;
	private double logProbability$st;
	private double logProbability$var11;
	private double logProbability$var16;
	private double logProbability$var20;
	private double logProbability$var25;
	private double logProbability$var32;
	private double logProbability$var33;
	private double[] logProbability$var42;
	private double[] logProbability$var51;
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
		// Set bias with flag to mark that it has been set so another array doesn't need to
		// be constructed
		bias = cv$value;
		setFlag$bias = true;
		
		// Unset the fixed probability flag for sample 27 as it depends on bias.
		fixedProbFlag$sample27 = false;
		
		// Unset the fixed probability flag for sample 55 as it depends on bias.
		fixedProbFlag$sample55 = false;
	}

	// Getter for fixedFlag$sample17.
	@Override
	public final boolean get$fixedFlag$sample17() {
		return fixedFlag$sample17;
	}

	// Setter for fixedFlag$sample17.
	@Override
	public final void set$fixedFlag$sample17(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample17 including if probabilities
		// need to be updated.
		fixedFlag$sample17 = cv$value;
		
		// Should the probability of sample 17 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample17 = (fixedFlag$sample17 && fixedProbFlag$sample17);
		
		// Should the probability of sample 36 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample36 = (fixedFlag$sample17 && fixedProbFlag$sample36);
		
		// Should the probability of sample 46 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample46 = (fixedFlag$sample17 && fixedProbFlag$sample46);
	}

	// Getter for fixedFlag$sample27.
	@Override
	public final boolean get$fixedFlag$sample27() {
		return fixedFlag$sample27;
	}

	// Setter for fixedFlag$sample27.
	@Override
	public final void set$fixedFlag$sample27(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample27 including if probabilities
		// need to be updated.
		fixedFlag$sample27 = cv$value;
		
		// Should the probability of sample 27 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample27 = (fixedFlag$sample27 && fixedProbFlag$sample27);
		
		// Should the probability of sample 55 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample55 = (fixedFlag$sample27 && fixedProbFlag$sample55);
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
		
		// Should the probability of sample 46 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample46 = (fixedFlag$sample36 && fixedProbFlag$sample46);
		
		// Should the probability of sample 55 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample55 = (fixedFlag$sample36 && fixedProbFlag$sample55);
	}

	// Getter for fixedFlag$sample46.
	@Override
	public final boolean get$fixedFlag$sample46() {
		return fixedFlag$sample46;
	}

	// Setter for fixedFlag$sample46.
	@Override
	public final void set$fixedFlag$sample46(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample46 including if probabilities
		// need to be updated.
		fixedFlag$sample46 = cv$value;
		
		// Should the probability of sample 46 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample46 = (fixedFlag$sample46 && fixedProbFlag$sample46);
		
		// Should the probability of sample 55 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample55 = (fixedFlag$sample46 && fixedProbFlag$sample55);
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
		
		// Unset the fixed probability flag for sample 55 as it depends on flips.
		fixedProbFlag$sample55 = false;
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
		
		// Unset the fixed probability flag for sample 17 as it depends on m.
		fixedProbFlag$sample17 = false;
		
		// Unset the fixed probability flag for sample 36 as it depends on m.
		fixedProbFlag$sample36 = false;
		
		// Unset the fixed probability flag for sample 46 as it depends on m.
		fixedProbFlag$sample46 = false;
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
		
		// Unset the fixed probability flag for sample 36 as it depends on st.
		fixedProbFlag$sample36 = false;
		
		// Unset the fixed probability flag for sample 46 as it depends on st.
		fixedProbFlag$sample46 = false;
		
		// Unset the fixed probability flag for sample 55 as it depends on st.
		fixedProbFlag$sample55 = false;
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
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[0];
					{
						{
							double[] var31 = m[0];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var31.length))?Math.log(var31[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
				logProbability$var32 = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$var33 = cv$sampleProbability;
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(fixedFlag$sample36)
					// Update the variable probability
					logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample36)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample36 = (fixedFlag$sample36 && fixedFlag$sample17);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var33;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var32 = cv$rvAccumulator;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample36)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample46 using probability
	// distributions.
	private final void logProbabilityDistribution$sample46() {
		// Determine if we need to calculate the values for sample task 46 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample46) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample46) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Look for paths between the variable and the sample task 46 including any distribution
					// values.
					// 
					// Copy of index so that its values can be safely substituted
					int index$i$1 = i$var37;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[i$var37];
						
						// Enumerating the possible arguments for Categorical 42.
						if(fixedFlag$sample36) {
							if((0 == (i$var37 - 1))) {
								for(int var15 = 0; var15 < states; var15 += 1) {
									if((var15 == st[(i$var37 - 1)])) {
										{
											double[] var41 = m[st[(i$var37 - 1)]];
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var41.length))?Math.log(var41[cv$sampleValue]):Double.NEGATIVE_INFINITY));
											
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
								// Enumerating the possible outputs of Categorical 32.
								for(int index$sample36$4 = 0; index$sample36$4 < states; index$sample36$4 += 1) {
									int distributionTempVariable$var33$6 = index$sample36$4;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample36Value5 = (1.0 * distribution$sample36[index$sample36$4]);
									int traceTempVariable$var40$7_1 = distributionTempVariable$var33$6;
									if((0 == (i$var37 - 1))) {
										for(int var15 = 0; var15 < states; var15 += 1) {
											if((var15 == traceTempVariable$var40$7_1)) {
												{
													double[] var41 = m[traceTempVariable$var40$7_1];
													
													// Store the value of the function call, so the function call is only made once.
													double cv$weightedProbability = (Math.log(cv$probabilitySample36Value5) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var41.length))?Math.log(var41[cv$sampleValue]):Double.NEGATIVE_INFINITY));
													
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
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample36Value5);
												}
											}
										}
									}
								}
							}
						}
						
						// Enumerating the possible arguments for Categorical 42.
						int traceTempVariable$var40$10_1 = DistributionSampling.sampleCategorical(RNG$, m[st[(index$i$1 - 1)]]);
						if((index$i$1 == (i$var37 - 1))) {
							for(int var15 = 0; var15 < states; var15 += 1) {
								if((var15 == traceTempVariable$var40$10_1)) {
									{
										double[] var41 = m[traceTempVariable$var40$10_1];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var41.length))?Math.log(var41[cv$sampleValue]):Double.NEGATIVE_INFINITY));
										
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
						if(fixedFlag$sample46) {
							for(int index$i$11_1 = 1; index$i$11_1 < samples; index$i$11_1 += 1) {
								if((index$i$11_1 == (i$var37 - 1))) {
									for(int var15 = 0; var15 < states; var15 += 1) {
										if((var15 == st[(i$var37 - 1)])) {
											{
												double[] var41 = m[st[(i$var37 - 1)]];
												
												// Store the value of the function call, so the function call is only made once.
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var41.length))?Math.log(var41[cv$sampleValue]):Double.NEGATIVE_INFINITY));
												
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
									// Enumerating the possible outputs of Categorical 42.
									for(int index$sample46$13 = 0; index$sample46$13 < states; index$sample46$13 += 1) {
										int distributionTempVariable$var43$15 = index$sample46$13;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample46Value14 = (1.0 * distribution$sample46[((index$i$12 - 1) / 1)][index$sample46$13]);
										int traceTempVariable$var40$16_1 = distributionTempVariable$var43$15;
										if((index$i$12 == (i$var37 - 1))) {
											for(int var15 = 0; var15 < states; var15 += 1) {
												if((var15 == traceTempVariable$var40$16_1)) {
													{
														double[] var41 = m[traceTempVariable$var40$16_1];
														
														// Store the value of the function call, so the function call is only made once.
														double cv$weightedProbability = (Math.log(cv$probabilitySample46Value14) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var41.length))?Math.log(var41[cv$sampleValue]):Double.NEGATIVE_INFINITY));
														
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
														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample46Value14);
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
					logProbability$var42[((i$var37 - 1) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample46[((i$var37 - 1) / 1)] = cv$sampleProbability;
				}
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(fixedFlag$sample46)
					// Update the variable probability
					logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample46)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample46 = ((fixedFlag$sample46 && fixedFlag$sample17) && fixedFlag$sample36);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample46[((i$var37 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var42[((i$var37 - 1) / 1)] = cv$rvAccumulator;
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample46)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample55 using probability
	// distributions.
	private final void logProbabilityDistribution$sample55() {
		// Determine if we need to calculate the values for sample task 55 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample55) {
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
				
				// Look for paths between the variable and the sample task 55 including any distribution
				// values.
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = flips[j];
					
					// Enumerating the possible arguments for Bernoulli 51.
					if(fixedFlag$sample36) {
						if((0 == j)) {
							for(int var24 = 0; var24 < states; var24 += 1) {
								if((var24 == st[j])) {
									{
										double var50 = bias[st[j]];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
										
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
							// Enumerating the possible outputs of Categorical 32.
							for(int index$sample36$3 = 0; index$sample36$3 < states; index$sample36$3 += 1) {
								int distributionTempVariable$var33$5 = index$sample36$3;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample36Value4 = (1.0 * distribution$sample36[index$sample36$3]);
								int traceTempVariable$var49$6_1 = distributionTempVariable$var33$5;
								if((0 == j)) {
									for(int var24 = 0; var24 < states; var24 += 1) {
										if((var24 == traceTempVariable$var49$6_1)) {
											{
												double var50 = bias[traceTempVariable$var49$6_1];
												
												// Store the value of the function call, so the function call is only made once.
												double cv$weightedProbability = (Math.log(cv$probabilitySample36Value4) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
												
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
												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample36Value4);
											}
										}
									}
								}
							}
						}
					}
					
					// Enumerating the possible arguments for Bernoulli 51.
					if(fixedFlag$sample46) {
						for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
							if((i$var37 == j)) {
								for(int var24 = 0; var24 < states; var24 += 1) {
									if((var24 == st[j])) {
										{
											double var50 = bias[st[j]];
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
											
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
						for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
							if(true) {
								// Enumerating the possible outputs of Categorical 42.
								for(int index$sample46$11 = 0; index$sample46$11 < states; index$sample46$11 += 1) {
									int distributionTempVariable$var43$13 = index$sample46$11;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample46Value12 = (1.0 * distribution$sample46[((i$var37 - 1) / 1)][index$sample46$11]);
									int traceTempVariable$var49$14_1 = distributionTempVariable$var43$13;
									if((i$var37 == j)) {
										for(int var24 = 0; var24 < states; var24 += 1) {
											if((var24 == traceTempVariable$var49$14_1)) {
												{
													double var50 = bias[traceTempVariable$var49$14_1];
													
													// Store the value of the function call, so the function call is only made once.
													double cv$weightedProbability = (Math.log(cv$probabilitySample46Value12) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
													
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
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample46Value12);
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
				logProbability$var51[((j - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample55[((j - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample55 = (((fixedFlag$sample55 && fixedFlag$sample27) && fixedFlag$sample36) && fixedFlag$sample46);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample55[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var51[((j - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample17 using sampled
	// values.
	private final void logProbabilityValue$sample17() {
		// Determine if we need to calculate the values for sample task 17 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample17) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var15 = 0; var15 < states; var15 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double[] cv$sampleValue = m[var15];
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
			logProbability$var11 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var16 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample17 = fixedFlag$sample17;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var16;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var11 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample27 using sampled
	// values.
	private final void logProbabilityValue$sample27() {
		// Determine if we need to calculate the values for sample task 27 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample27) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var24 = 0; var24 < states; var24 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = bias[var24];
					{
						{
							double var18 = 1.0;
							double var19 = 1.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var18, var19));
							
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
			logProbability$var20 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var25 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample27 = fixedFlag$sample27;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var25;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var20 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample27)
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
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				// The sample value to calculate the probability of generating
				int cv$sampleValue = st[0];
				{
					{
						double[] var31 = m[0];
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var31.length))?Math.log(var31[cv$sampleValue]):Double.NEGATIVE_INFINITY));
						
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
			logProbability$var32 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$var33 = cv$sampleProbability;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample36 = (fixedFlag$sample36 && fixedFlag$sample17);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var33;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var32 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample46 using sampled
	// values.
	private final void logProbabilityValue$sample46() {
		// Determine if we need to calculate the values for sample task 46 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample46) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Copy of index so that its values can be safely substituted
				int index$i$1 = i$var37;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[i$var37];
					{
						{
							double[] var41 = m[st[(i$var37 - 1)]];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var41.length))?Math.log(var41[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
				logProbability$var42[((i$var37 - 1) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample46[((i$var37 - 1) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample46 = ((fixedFlag$sample46 && fixedFlag$sample17) && fixedFlag$sample36);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample46[((i$var37 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var42[((i$var37 - 1) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample46)
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
							double var50 = bias[st[j]];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
							
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
				logProbability$var51[((j - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample55[((j - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample55 = (((fixedFlag$sample55 && fixedFlag$sample27) && fixedFlag$sample36) && fixedFlag$sample46);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample55[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var51[((j - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 17 drawn from Dirichlet 11. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample17(int var15, int threadID$cv$var15, Rng RNG$) {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = m[var15];
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var16$countGlobal[threadID$cv$var15];
		
		// Get the length of the array
		int cv$arrayLength = states;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 32.
			{
				// Looking for a path between Sample 17 and consumer Categorical 32.
				{
					if((var15 == 0)) {
						if(fixedFlag$sample36) {
							// Processing sample task 36 of consumer random variable null.
							{
								{
									{
										{
											{
												// Increment the sample counter with the value sampled by sample task 36 of random
												// variable var32
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
			
			// Processing random variable 42.
			{
				// Looking for a path between Sample 17 and consumer Categorical 42.
				{
					for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
						if(fixedFlag$sample36) {
							if((0 == (i$var37 - 1))) {
								if((var15 == st[(i$var37 - 1)])) {
									if(fixedFlag$sample46) {
										// Processing sample task 46 of consumer random variable null.
										{
											// Copy of index so that its values can be safely substituted
											int index$i$22 = i$var37;
											{
												{
													{
														{
															// Increment the sample counter with the value sampled by sample task 46 of random
															// variable var42
															cv$countLocal[st[i$var37]] = (cv$countLocal[st[i$var37]] + 1.0);
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
								// Enumerating the possible outputs of Categorical 32.
								for(int index$sample36$6 = 0; index$sample36$6 < states; index$sample36$6 += 1) {
									int distributionTempVariable$var33$8 = index$sample36$6;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample36Value7 = (1.0 * distribution$sample36[index$sample36$6]);
									int traceTempVariable$var40$9_1 = distributionTempVariable$var33$8;
									if((0 == (i$var37 - 1))) {
										if((var15 == traceTempVariable$var40$9_1)) {
											if(fixedFlag$sample46) {
												// Processing sample task 46 of consumer random variable null.
												{
													// Copy of index so that its values can be safely substituted
													int index$i$24 = i$var37;
													{
														{
															{
																{
																	// Increment the sample counter with the value sampled by sample task 46 of random
																	// variable var42
																	cv$countLocal[st[i$var37]] = (cv$countLocal[st[i$var37]] + cv$probabilitySample36Value7);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
						if(fixedFlag$sample46) {
							for(int index$i$13_1 = 1; index$i$13_1 < samples; index$i$13_1 += 1) {
								if((index$i$13_1 == (i$var37 - 1))) {
									if((var15 == st[(i$var37 - 1)])) {
										if(fixedFlag$sample46) {
											// Processing sample task 46 of consumer random variable null.
											{
												// Copy of index so that its values can be safely substituted
												int index$i$26 = i$var37;
												{
													{
														{
															{
																// Increment the sample counter with the value sampled by sample task 46 of random
																// variable var42
																cv$countLocal[st[i$var37]] = (cv$countLocal[st[i$var37]] + 1.0);
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
									// Enumerating the possible outputs of Categorical 42.
									for(int index$sample46$15 = 0; index$sample46$15 < states; index$sample46$15 += 1) {
										int distributionTempVariable$var43$17 = index$sample46$15;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample46Value16 = (1.0 * distribution$sample46[((index$i$14 - 1) / 1)][index$sample46$15]);
										int traceTempVariable$var40$18_1 = distributionTempVariable$var43$17;
										if((index$i$14 == (i$var37 - 1))) {
											if((var15 == traceTempVariable$var40$18_1)) {
												if(fixedFlag$sample46) {
													// Processing sample task 46 of consumer random variable null.
													{
														// Copy of index so that its values can be safely substituted
														int index$i$28 = i$var37;
														{
															{
																{
																	{
																		// Increment the sample counter with the value sampled by sample task 46 of random
																		// variable var42
																		cv$countLocal[st[i$var37]] = (cv$countLocal[st[i$var37]] + cv$probabilitySample46Value16);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Processing random variable 32.
		{
			// Looking for a path between Sample 17 and consumer Categorical 32.
			{
				if((var15 == 0)) {
					if(!fixedFlag$sample36) {
						// Processing sample task 36 of consumer random variable null.
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
										cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample36[cv$loopIndex] * cv$distributionProbability));
								}
							}
						}
					}
				}
			}
		}
		
		// Processing random variable 42.
		{
			// Looking for a path between Sample 17 and consumer Categorical 42.
			{
				for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
					if(fixedFlag$sample36) {
						if((0 == (i$var37 - 1))) {
							if((var15 == st[(i$var37 - 1)])) {
								if(!fixedFlag$sample46) {
									// Processing sample task 46 of consumer random variable null.
									{
										// Copy of index so that its values can be safely substituted
										int index$i$53 = i$var37;
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
													cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample46[((i$var37 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
											}
										}
									}
								}
							}
						}
					} else {
						if(true) {
							// Enumerating the possible outputs of Categorical 32.
							for(int index$sample36$37 = 0; index$sample36$37 < states; index$sample36$37 += 1) {
								int distributionTempVariable$var33$39 = index$sample36$37;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample36Value38 = (1.0 * distribution$sample36[index$sample36$37]);
								int traceTempVariable$var40$40_1 = distributionTempVariable$var33$39;
								if((0 == (i$var37 - 1))) {
									if((var15 == traceTempVariable$var40$40_1)) {
										if(!fixedFlag$sample46) {
											// Processing sample task 46 of consumer random variable null.
											{
												// Copy of index so that its values can be safely substituted
												int index$i$55 = i$var37;
												{
													{
														// Declare and zero an accumulator for tracking the reached source probability space.
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															// Add the probability of this argument configuration.
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														
														// The probability of reaching the consumer with this set of consumer arguments
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample36Value38);
														
														// Merge the distribution probabilities into the count
														for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
															cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample46[((i$var37 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
					if(fixedFlag$sample46) {
						for(int index$i$44_1 = 1; index$i$44_1 < samples; index$i$44_1 += 1) {
							if((index$i$44_1 == (i$var37 - 1))) {
								if((var15 == st[(i$var37 - 1)])) {
									if(!fixedFlag$sample46) {
										// Processing sample task 46 of consumer random variable null.
										{
											// Copy of index so that its values can be safely substituted
											int index$i$57 = i$var37;
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
														cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample46[((i$var37 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
								// Enumerating the possible outputs of Categorical 42.
								for(int index$sample46$46 = 0; index$sample46$46 < states; index$sample46$46 += 1) {
									int distributionTempVariable$var43$48 = index$sample46$46;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample46Value47 = (1.0 * distribution$sample46[((index$i$45 - 1) / 1)][index$sample46$46]);
									int traceTempVariable$var40$49_1 = distributionTempVariable$var43$48;
									if((index$i$45 == (i$var37 - 1))) {
										if((var15 == traceTempVariable$var40$49_1)) {
											if(!fixedFlag$sample46) {
												// Processing sample task 46 of consumer random variable null.
												{
													// Copy of index so that its values can be safely substituted
													int index$i$59 = i$var37;
													{
														{
															// Declare and zero an accumulator for tracking the reached source probability space.
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																// Add the probability of this argument configuration.
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															
															// The probability of reaching the consumer with this set of consumer arguments
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample46Value47);
															
															// Merge the distribution probabilities into the count
															for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample46[((i$var37 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
														}
													}
												}
											}
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
	// by sample task 27 drawn from Beta 20. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample27(int var24, int threadID$cv$var24, Rng RNG$) {
		// Local variable to record the number of true samples.
		double cv$sum = 0.0;
		
		// Local variable to record the number of samples.
		double cv$count = 0.0;
		{
			// Processing random variable 51.
			{
				// Looking for a path between Sample 27 and consumer Bernoulli 51.
				{
					for(int j = 0; j < samples; j += 1) {
						if(fixedFlag$sample36) {
							if((0 == j)) {
								if((var24 == st[j])) {
									// Processing sample task 55 of consumer random variable null.
									{
										{
											{
												{
													{
														// Include the value sampled by task 55 from random variable var51.
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
								// Enumerating the possible outputs of Categorical 32.
								for(int index$sample36$3 = 0; index$sample36$3 < states; index$sample36$3 += 1) {
									int distributionTempVariable$var33$5 = index$sample36$3;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample36Value4 = (1.0 * distribution$sample36[index$sample36$3]);
									int traceTempVariable$var49$6_1 = distributionTempVariable$var33$5;
									if((0 == j)) {
										if((var24 == traceTempVariable$var49$6_1)) {
											// Processing sample task 55 of consumer random variable null.
											{
												{
													{
														{
															{
																// Include the value sampled by task 55 from random variable var51.
																// Increment the number of samples.
																cv$count = (cv$count + cv$probabilitySample36Value4);
																
																// If the sample value was positive increase the count
																if(flips[j])
																	cv$sum = (cv$sum + cv$probabilitySample36Value4);
															}
														}
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
						if(fixedFlag$sample46) {
							for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
								if((i$var37 == j)) {
									if((var24 == st[j])) {
										// Processing sample task 55 of consumer random variable null.
										{
											{
												{
													{
														{
															// Include the value sampled by task 55 from random variable var51.
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
							for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
								if(true) {
									// Enumerating the possible outputs of Categorical 42.
									for(int index$sample46$12 = 0; index$sample46$12 < states; index$sample46$12 += 1) {
										int distributionTempVariable$var43$14 = index$sample46$12;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample46Value13 = (1.0 * distribution$sample46[((i$var37 - 1) / 1)][index$sample46$12]);
										int traceTempVariable$var49$15_1 = distributionTempVariable$var43$14;
										if((i$var37 == j)) {
											if((var24 == traceTempVariable$var49$15_1)) {
												// Processing sample task 55 of consumer random variable null.
												{
													{
														{
															{
																{
																	// Include the value sampled by task 55 from random variable var51.
																	// Increment the number of samples.
																	cv$count = (cv$count + cv$probabilitySample46Value13);
																	
																	// If the sample value was positive increase the count
																	if(flips[j])
																		cv$sum = (cv$sum + cv$probabilitySample46Value13);
																}
															}
														}
													}
												}
											}
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
		double var25 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[var24] = var25;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 36 drawn from Categorical 32. Inference was performed using variable
	// marginalization.
	private final void sample36() {
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var33$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < states; cv$valuePos += 1) {
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
				double[] cv$temp$0$var31;
				{
					// Constructing a random variable input for use later.
					double[] var31 = m[0];
					cv$temp$0$var31 = var31;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var31.length))?Math.log(cv$temp$0$var31[cv$currentValue]):Double.NEGATIVE_INFINITY));
				
				// Processing random variable 42.
				{
					// Looking for a path between Sample 36 and consumer Categorical 42.
					{
						int traceTempVariable$var40$1_1 = cv$currentValue;
						for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
							if((0 == (i$var37 - 1))) {
								if(fixedFlag$sample46) {
									// Processing sample task 46 of consumer random variable null.
									{
										// Copy of index so that its values can be safely substituted
										int index$i$3 = i$var37;
										
										// Set an accumulator to sum the probabilities for each possible configuration of
										// inputs.
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											// Enumerating the possible arguments for the variable Categorical 42 which is consuming
											// the output of Sample task 36.
											for(int var15 = 0; var15 < states; var15 += 1) {
												if((var15 == traceTempVariable$var40$1_1)) {
													{
														{
															double[] cv$temp$1$var41;
															{
																// Constructing a random variable input for use later.
																double[] var41 = m[traceTempVariable$var40$1_1];
																cv$temp$1$var41 = var41;
															}
															
															// Record the probability of sample task 46 generating output with current configuration.
															if(((Math.log(1.0) + (((0.0 <= st[i$var37]) && (st[i$var37] < cv$temp$1$var41.length))?Math.log(cv$temp$1$var41[st[i$var37]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var37]) && (st[i$var37] < cv$temp$1$var41.length))?Math.log(cv$temp$1$var41[st[i$var37]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																// If the second value is -infinity.
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var37]) && (st[i$var37] < cv$temp$1$var41.length))?Math.log(cv$temp$1$var41[st[i$var37]]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var37]) && (st[i$var37] < cv$temp$1$var41.length))?Math.log(cv$temp$1$var41[st[i$var37]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var37]) && (st[i$var37] < cv$temp$1$var41.length))?Math.log(cv$temp$1$var41[st[i$var37]]):Double.NEGATIVE_INFINITY)));
															}
															
															// Recorded the probability of reaching sample task 46 with the current configuration.
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
				
				// Processing random variable 51.
				{
					// Looking for a path between Sample 36 and consumer Bernoulli 51.
					{
						int traceTempVariable$var49$6_1 = cv$currentValue;
						for(int j = 0; j < samples; j += 1) {
							if((0 == j)) {
								// Processing sample task 55 of consumer random variable null.
								{
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										// Enumerating the possible arguments for the variable Bernoulli 51 which is consuming
										// the output of Sample task 36.
										for(int var24 = 0; var24 < states; var24 += 1) {
											if((var24 == traceTempVariable$var49$6_1)) {
												{
													{
														double cv$temp$2$var50;
														{
															// Constructing a random variable input for use later.
															double var50 = bias[traceTempVariable$var49$6_1];
															cv$temp$2$var50 = var50;
														}
														
														// Record the probability of sample task 55 generating output with current configuration.
														if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var50)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															// If the second value is -infinity.
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var50));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var50)));
														}
														
														// Recorded the probability of reaching sample task 55 with the current configuration.
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
			
			// Processing random variable 42.
			{
				// Looking for a path between Sample 36 and consumer Categorical 42.
				{
					int traceTempVariable$var40$10_1 = cv$currentValue;
					for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
						if((0 == (i$var37 - 1))) {
							if(!fixedFlag$sample46) {
								// Processing sample task 46 of consumer random variable null.
								{
									// Copy of index so that its values can be safely substituted
									int index$i$12 = i$var37;
									
									// A local array to hold the accumulated distributions of the sample tasks for each
									// configuration of distributions.
									double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var42;
									
									// Zero all the elements in the distribution accumulator
									for(int cv$i = 0; cv$i < states; cv$i += 1)
										cv$accumulatedConsumerDistributions[cv$i] = 0.0;
									
									// Zero an accumulator to track the probabilities reached.
									double cv$reachedDistributionProbability = 0.0;
									
									// Enumerating the possible arguments for the variable Categorical 42 which is consuming
									// the output of Sample task 36.
									for(int var15 = 0; var15 < states; var15 += 1) {
										if((var15 == traceTempVariable$var40$10_1)) {
											{
												// Declare and zero an accumulator for tracking the reached source probability space.
												double scopeVariable$reachedSourceProbability = 0.0;
												{
													// Add the probability of this argument configuration.
													scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
												double[] cv$temp$3$var41;
												{
													// Constructing a random variable input for use later.
													double[] var41 = m[traceTempVariable$var40$10_1];
													cv$temp$3$var41 = var41;
												}
												
												// The probability of reaching the consumer with this set of consumer arguments
												double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
												
												// Record the reached distribution.
												cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
												
												// Add the current distribution to the distribution accumulator.
												DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$3$var41);
											}
										}
									}
									
									// A local copy of the samples' distribution.
									double[] cv$sampleDistribution = distribution$sample46[((i$var37 - 1) / 1)];
									
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
		double[] cv$localProbability = distribution$sample36;
		
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

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 46 drawn from Categorical 42. Inference was performed using variable
	// marginalization.
	private final void sample46(int i$var37) {
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var43$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < states; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 42 creating
			// sample task 46.
			// Copy of index so that its values can be safely substituted
			int index$i$1 = i$var37;
			
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
			
			// Enumerating the possible arguments for Categorical 42.
			if(fixedFlag$sample36) {
				if((0 == (i$var37 - 1))) {
					for(int var15 = 0; var15 < states; var15 += 1) {
						if((var15 == st[(i$var37 - 1)])) {
							// Record the reached probability density.
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$0$var41;
							{
								// Constructing a random variable input for use later.
								double[] var41 = m[st[(i$var37 - 1)]];
								cv$temp$0$var41 = var41;
							}
							
							// An accumulator to allow the value for each distribution to be constructed before
							// it is added to the index probabilities.
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var41.length))?Math.log(cv$temp$0$var41[cv$currentValue]):Double.NEGATIVE_INFINITY));
							
							// Processing random variable 42.
							{
								// Looking for a path between Sample 46 and consumer Categorical 42.
								{
									int traceTempVariable$var40$17_1 = cv$currentValue;
								}
							}
							
							// Processing random variable 51.
							{
								// Looking for a path between Sample 46 and consumer Bernoulli 51.
								{
									int traceTempVariable$var49$21_1 = cv$currentValue;
									for(int j = 0; j < samples; j += 1) {
										if((i$var37 == j)) {
											// Processing sample task 55 of consumer random variable null.
											{
												// Set an accumulator to sum the probabilities for each possible configuration of
												// inputs.
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												
												// Set an accumulator to record the consumer distributions not seen. Initially set
												// to 1 as seen values will be deducted from this value.
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													// Enumerating the possible arguments for the variable Bernoulli 51 which is consuming
													// the output of Sample task 46.
													for(int var24 = 0; var24 < states; var24 += 1) {
														if((var24 == traceTempVariable$var49$21_1)) {
															{
																{
																	double cv$temp$4$var50;
																	{
																		// Constructing a random variable input for use later.
																		double var50 = bias[traceTempVariable$var49$21_1];
																		cv$temp$4$var50 = var50;
																	}
																	
																	// Record the probability of sample task 55 generating output with current configuration.
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var50)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var50));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var50)));
																	}
																	
																	// Recorded the probability of reaching sample task 55 with the current configuration.
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
					// Enumerating the possible outputs of Categorical 32.
					for(int index$sample36$3 = 0; index$sample36$3 < states; index$sample36$3 += 1) {
						int distributionTempVariable$var33$5 = index$sample36$3;
						
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample36Value4 = (1.0 * distribution$sample36[index$sample36$3]);
						int traceTempVariable$var40$6_1 = distributionTempVariable$var33$5;
						if((0 == (i$var37 - 1))) {
							for(int var15 = 0; var15 < states; var15 += 1) {
								if((var15 == traceTempVariable$var40$6_1)) {
									// Record the reached probability density.
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample36Value4);
									double[] cv$temp$1$var41;
									{
										// Constructing a random variable input for use later.
										double[] var41 = m[traceTempVariable$var40$6_1];
										cv$temp$1$var41 = var41;
									}
									
									// An accumulator to allow the value for each distribution to be constructed before
									// it is added to the index probabilities.
									double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample36Value4) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var41.length))?Math.log(cv$temp$1$var41[cv$currentValue]):Double.NEGATIVE_INFINITY));
									
									// Processing random variable 42.
									{
										// Looking for a path between Sample 46 and consumer Categorical 42.
										{
											int traceTempVariable$var40$18_1 = cv$currentValue;
										}
									}
									
									// Processing random variable 51.
									{
										// Looking for a path between Sample 46 and consumer Bernoulli 51.
										{
											int traceTempVariable$var49$22_1 = cv$currentValue;
											for(int j = 0; j < samples; j += 1) {
												if((i$var37 == j)) {
													// Processing sample task 55 of consumer random variable null.
													{
														// Set an accumulator to sum the probabilities for each possible configuration of
														// inputs.
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														
														// Set an accumulator to record the consumer distributions not seen. Initially set
														// to 1 as seen values will be deducted from this value.
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															// Enumerating the possible arguments for the variable Bernoulli 51 which is consuming
															// the output of Sample task 46.
															for(int var24 = 0; var24 < states; var24 += 1) {
																if((var24 == traceTempVariable$var49$22_1)) {
																	{
																		{
																			double cv$temp$5$var50;
																			{
																				// Constructing a random variable input for use later.
																				double var50 = bias[traceTempVariable$var49$22_1];
																				cv$temp$5$var50 = var50;
																			}
																			
																			// Record the probability of sample task 55 generating output with current configuration.
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var50)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var50));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var50)));
																			}
																			
																			// Recorded the probability of reaching sample task 55 with the current configuration.
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
			
			// Enumerating the possible arguments for Categorical 42.
			int traceTempVariable$var40$9_1 = cv$currentValue;
			if((index$i$1 == (i$var37 - 1))) {
				for(int var15 = 0; var15 < states; var15 += 1) {
					if((var15 == traceTempVariable$var40$9_1)) {
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double[] cv$temp$2$var41;
						{
							// Constructing a random variable input for use later.
							double[] var41 = m[traceTempVariable$var40$9_1];
							cv$temp$2$var41 = var41;
						}
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var41.length))?Math.log(cv$temp$2$var41[cv$currentValue]):Double.NEGATIVE_INFINITY));
						
						// Processing random variable 42.
						{
							// Looking for a path between Sample 46 and consumer Categorical 42.
							{
								int traceTempVariable$var40$19_1 = cv$currentValue;
							}
						}
						
						// Processing random variable 51.
						{
							// Looking for a path between Sample 46 and consumer Bernoulli 51.
							{
								int traceTempVariable$var49$23_1 = cv$currentValue;
								for(int j = 0; j < samples; j += 1) {
									if((i$var37 == j)) {
										// Processing sample task 55 of consumer random variable null.
										{
											// Set an accumulator to sum the probabilities for each possible configuration of
											// inputs.
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											
											// Set an accumulator to record the consumer distributions not seen. Initially set
											// to 1 as seen values will be deducted from this value.
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												// Enumerating the possible arguments for the variable Bernoulli 51 which is consuming
												// the output of Sample task 46.
												for(int var24 = 0; var24 < states; var24 += 1) {
													if((var24 == traceTempVariable$var49$23_1)) {
														{
															{
																double cv$temp$6$var50;
																{
																	// Constructing a random variable input for use later.
																	double var50 = bias[traceTempVariable$var49$23_1];
																	cv$temp$6$var50 = var50;
																}
																
																// Record the probability of sample task 55 generating output with current configuration.
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var50)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var50));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var50)));
																}
																
																// Recorded the probability of reaching sample task 55 with the current configuration.
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
			for(int index$i$10 = 1; index$i$10 < samples; index$i$10 += 1) {
				if(!(index$i$10 == index$i$1)) {
					// Enumerating the possible outputs of Categorical 42.
					for(int index$sample46$11 = 0; index$sample46$11 < states; index$sample46$11 += 1) {
						int distributionTempVariable$var43$13 = index$sample46$11;
						
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample46Value12 = (1.0 * distribution$sample46[((index$i$10 - 1) / 1)][index$sample46$11]);
						int traceTempVariable$var40$14_1 = distributionTempVariable$var43$13;
						if((index$i$10 == (i$var37 - 1))) {
							for(int var15 = 0; var15 < states; var15 += 1) {
								if((var15 == traceTempVariable$var40$14_1)) {
									// Record the reached probability density.
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample46Value12);
									double[] cv$temp$3$var41;
									{
										// Constructing a random variable input for use later.
										double[] var41 = m[traceTempVariable$var40$14_1];
										cv$temp$3$var41 = var41;
									}
									
									// An accumulator to allow the value for each distribution to be constructed before
									// it is added to the index probabilities.
									double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample46Value12) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$var41.length))?Math.log(cv$temp$3$var41[cv$currentValue]):Double.NEGATIVE_INFINITY));
									
									// Processing random variable 42.
									{
										// Looking for a path between Sample 46 and consumer Categorical 42.
										{
											int traceTempVariable$var40$20_1 = cv$currentValue;
										}
									}
									
									// Processing random variable 51.
									{
										// Looking for a path between Sample 46 and consumer Bernoulli 51.
										{
											int traceTempVariable$var49$24_1 = cv$currentValue;
											for(int j = 0; j < samples; j += 1) {
												if((i$var37 == j)) {
													// Processing sample task 55 of consumer random variable null.
													{
														// Set an accumulator to sum the probabilities for each possible configuration of
														// inputs.
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														
														// Set an accumulator to record the consumer distributions not seen. Initially set
														// to 1 as seen values will be deducted from this value.
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															// Enumerating the possible arguments for the variable Bernoulli 51 which is consuming
															// the output of Sample task 46.
															for(int var24 = 0; var24 < states; var24 += 1) {
																if((var24 == traceTempVariable$var49$24_1)) {
																	{
																		{
																			double cv$temp$7$var50;
																			{
																				// Constructing a random variable input for use later.
																				double var50 = bias[traceTempVariable$var49$24_1];
																				cv$temp$7$var50 = var50;
																			}
																			
																			// Record the probability of sample task 55 generating output with current configuration.
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var50)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var50));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var50)));
																			}
																			
																			// Recorded the probability of reaching sample task 55 with the current configuration.
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
			
			// Processing random variable 42.
			{
				// Looking for a path between Sample 46 and consumer Categorical 42.
				{
					int traceTempVariable$var40$37_1 = cv$currentValue;
					for(int index$i$37_2 = 1; index$i$37_2 < samples; index$i$37_2 += 1) {
						if((i$var37 == (index$i$37_2 - 1))) {
							// Processing sample task 46 of consumer random variable null.
							{
								// Copy of index so that its values can be safely substituted
								int index$i$39 = index$i$37_2;
								
								// A local array to hold the accumulated distributions of the sample tasks for each
								// configuration of distributions.
								double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var42;
								
								// Zero all the elements in the distribution accumulator
								for(int cv$i = 0; cv$i < states; cv$i += 1)
									cv$accumulatedConsumerDistributions[cv$i] = 0.0;
								
								// Zero an accumulator to track the probabilities reached.
								double cv$reachedDistributionProbability = 0.0;
								
								// Enumerating the possible arguments for the variable Categorical 42 which is consuming
								// the output of Sample task 46.
								for(int var15 = 0; var15 < states; var15 += 1) {
									if((var15 == traceTempVariable$var40$37_1)) {
										{
											// Declare and zero an accumulator for tracking the reached source probability space.
											double scopeVariable$reachedSourceProbability = 0.0;
											
											// Enumerating the possible arguments for Categorical 42.
											if(fixedFlag$sample36) {
												if((0 == (i$var37 - 1))) {
													for(int index$var15$46_1 = 0; index$var15$46_1 < states; index$var15$46_1 += 1) {
														if((index$var15$46_1 == st[(i$var37 - 1)]))
															// Add the probability of this argument configuration.
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
													}
												}
											} else {
												if(true) {
													// Enumerating the possible outputs of Categorical 32.
													for(int index$sample36$42 = 0; index$sample36$42 < states; index$sample36$42 += 1) {
														int distributionTempVariable$var33$44 = index$sample36$42;
														
														// Update the probability of sampling this value from the distribution value.
														double cv$probabilitySample36Value43 = (1.0 * distribution$sample36[index$sample36$42]);
														int traceTempVariable$var40$45_1 = distributionTempVariable$var33$44;
														if((0 == (i$var37 - 1))) {
															for(int index$var15$47_1 = 0; index$var15$47_1 < states; index$var15$47_1 += 1) {
																if((index$var15$47_1 == traceTempVariable$var40$45_1))
																	// Add the probability of this argument configuration.
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample36Value43);
															}
														}
													}
												}
											}
											
											// Enumerating the possible arguments for Categorical 42.
											int traceTempVariable$var40$48_1 = cv$currentValue;
											if((index$i$1 == (i$var37 - 1))) {
												for(int index$var15$54_1 = 0; index$var15$54_1 < states; index$var15$54_1 += 1) {
													if((index$var15$54_1 == traceTempVariable$var40$48_1))
														// Add the probability of this argument configuration.
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
											}
											for(int index$i$49 = 1; index$i$49 < samples; index$i$49 += 1) {
												if((!(index$i$49 == index$i$1) && !(index$i$49 == index$i$39))) {
													// Enumerating the possible outputs of Categorical 42.
													for(int index$sample46$50 = 0; index$sample46$50 < states; index$sample46$50 += 1) {
														int distributionTempVariable$var43$52 = index$sample46$50;
														
														// Update the probability of sampling this value from the distribution value.
														double cv$probabilitySample46Value51 = (1.0 * distribution$sample46[((index$i$49 - 1) / 1)][index$sample46$50]);
														int traceTempVariable$var40$53_1 = distributionTempVariable$var43$52;
														if((index$i$49 == (i$var37 - 1))) {
															for(int index$var15$55_1 = 0; index$var15$55_1 < states; index$var15$55_1 += 1) {
																if((index$var15$55_1 == traceTempVariable$var40$53_1))
																	// Add the probability of this argument configuration.
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample46Value51);
															}
														}
													}
												}
											}
											double[] cv$temp$8$var41;
											{
												// Constructing a random variable input for use later.
												double[] var41 = m[traceTempVariable$var40$37_1];
												cv$temp$8$var41 = var41;
											}
											
											// The probability of reaching the consumer with this set of consumer arguments
											double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
											
											// Record the reached distribution.
											cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
											
											// Add the current distribution to the distribution accumulator.
											DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$8$var41);
										}
									}
								}
								
								// A local copy of the samples' distribution.
								double[] cv$sampleDistribution = distribution$sample46[((index$i$37_2 - 1) / 1)];
								
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
		double[] cv$localProbability = distribution$sample46[((i$var37 - 1) / 1)];
		
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
		// Constructor for cv$var16$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			for(int var15 = 0; var15 < 5; var15 += 1)
				cv$max = Math.max(cv$max, 5);
			
			// Allocation of cv$var16$countGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var16$countGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var16$countGlobal[cv$index] = new double[cv$max];
			}
		}
		
		// Constructor for cv$distributionAccumulator$var42
		{
			// Variable to record the maximum value of Task Get 44. Initially set to the value
			// of putTask 18.
			int cv$var17$max = 5;
			
			// Allocation of cv$distributionAccumulator$var42 for single threaded execution
			cv$distributionAccumulator$var42 = new double[cv$var17$max];
		}
		
		// Constructor for cv$var33$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 34. Initially set to the value
			// of putTask 18.
			int cv$var17$max = 5;
			
			// Allocation of cv$var33$stateProbabilityGlobal for single threaded execution
			cv$var33$stateProbabilityGlobal = new double[cv$var17$max];
		}
		
		// Constructor for cv$var43$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 44. Initially set to the value
			// of putTask 18.
			int cv$var17$max = 5;
			
			// Allocation of cv$var43$stateProbabilityGlobal for single threaded execution
			cv$var43$stateProbabilityGlobal = new double[cv$var17$max];
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
				for(int var15 = 0; var15 < 5; var15 += 1)
					m[var15] = new double[5];
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
		
		// Constructor for distribution$sample36
		{
			distribution$sample36 = new double[5];
		}
		
		// Constructor for distribution$sample46
		{
			distribution$sample46 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)][];
			for(int i$var37 = 1; i$var37 < length$flipsMeasured; i$var37 += 1)
				distribution$sample46[((i$var37 - 1) / 1)] = new double[5];
		}
		
		// Constructor for logProbability$var42
		{
			logProbability$var42 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample46
		{
			logProbability$sample46 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)];
		}
		
		// Constructor for logProbability$var51
		{
			logProbability$var51 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample55
		{
			logProbability$sample55 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1) {
						double[] var16 = m[var15];
						if(!fixedFlag$sample17)
							DistributionSampling.sampleDirichlet(RNG$1, v, var16);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
						if(!fixedFlag$sample27)
							bias[var24] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample36)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
			if(!fixedFlag$sample46)
				st[i$var37] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var37 - 1)]]);
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j = forStart$j; j < forEnd$j; j += 1) {
						if(!fixedFlag$sample55)
							flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[st[j]]);
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
			(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1) {
						double[] var16 = m[var15];
						if(!fixedFlag$sample17)
							DistributionSampling.sampleDirichlet(RNG$1, v, var16);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
						if(!fixedFlag$sample27)
							bias[var24] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		// Create local copy of variable probabilities.
		double[] cv$distribution$sample36 = distribution$sample36;
		double[] var31 = m[0];
		for(int index$var32 = 0; index$var32 < states; index$var32 += 1) {
			// Probability for this value
			double cv$value = (((0.0 <= index$var32) && (index$var32 < var31.length))?var31[index$var32]:0.0);
			if(!fixedFlag$sample36)
				// Save the probability of each value
				cv$distribution$sample36[index$var32] = cv$value;
		}
		for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
			// Create local copy of variable probabilities.
			double[] cv$distribution$sample46 = distribution$sample46[((i$var37 - 1) / 1)];
			for(int index$var42 = 0; index$var42 < states; index$var42 += 1) {
				if(!fixedFlag$sample46)
					// Zero the probability of each value
					cv$distribution$sample46[index$var42] = 0.0;
			}
			
			// Iterate through possible values for var42's arguments.
			// 
			// Enumerating the possible arguments for Categorical 42.
			if(fixedFlag$sample36) {
				if((0 == (i$var37 - 1))) {
					for(int var15 = 0; var15 < states; var15 += 1) {
						if((var15 == st[(i$var37 - 1)])) {
							{
								if(!fixedFlag$sample46) {
									double[] var41 = m[st[(i$var37 - 1)]];
									for(int index$var42 = 0; index$var42 < states; index$var42 += 1)
										// Save the probability of each value
										cv$distribution$sample46[index$var42] = (cv$distribution$sample46[index$var42] + (1.0 * (((0.0 <= index$var42) && (index$var42 < var41.length))?var41[index$var42]:0.0)));
								}
							}
						}
					}
				}
			} else {
				if(true) {
					// Enumerating the possible outputs of Categorical 32.
					for(int index$sample36$2 = 0; index$sample36$2 < states; index$sample36$2 += 1) {
						int distributionTempVariable$var33$4 = index$sample36$2;
						
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample36Value3 = (1.0 * distribution$sample36[index$sample36$2]);
						int traceTempVariable$var40$5_1 = distributionTempVariable$var33$4;
						if((0 == (i$var37 - 1))) {
							for(int var15 = 0; var15 < states; var15 += 1) {
								if((var15 == traceTempVariable$var40$5_1)) {
									{
										if(!fixedFlag$sample46) {
											double[] var41 = m[traceTempVariable$var40$5_1];
											for(int index$var42 = 0; index$var42 < states; index$var42 += 1)
												// Save the probability of each value
												cv$distribution$sample46[index$var42] = (cv$distribution$sample46[index$var42] + (cv$probabilitySample36Value3 * (((0.0 <= index$var42) && (index$var42 < var41.length))?var41[index$var42]:0.0)));
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Enumerating the possible arguments for Categorical 42.
			if(fixedFlag$sample46) {
				for(int index$i$8_1 = 1; index$i$8_1 < samples; index$i$8_1 += 1) {
					if((index$i$8_1 == (i$var37 - 1))) {
						for(int var15 = 0; var15 < states; var15 += 1) {
							if((var15 == st[(i$var37 - 1)])) {
								{
									if(!fixedFlag$sample46) {
										double[] var41 = m[st[(i$var37 - 1)]];
										for(int index$var42 = 0; index$var42 < states; index$var42 += 1)
											// Save the probability of each value
											cv$distribution$sample46[index$var42] = (cv$distribution$sample46[index$var42] + (1.0 * (((0.0 <= index$var42) && (index$var42 < var41.length))?var41[index$var42]:0.0)));
									}
								}
							}
						}
					}
				}
			} else {
				for(int index$i$9 = 1; index$i$9 < samples; index$i$9 += 1) {
					if(true) {
						// Enumerating the possible outputs of Categorical 42.
						for(int index$sample46$10 = 0; index$sample46$10 < states; index$sample46$10 += 1) {
							int distributionTempVariable$var43$12 = index$sample46$10;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample46Value11 = (1.0 * distribution$sample46[((index$i$9 - 1) / 1)][index$sample46$10]);
							int traceTempVariable$var40$13_1 = distributionTempVariable$var43$12;
							if((index$i$9 == (i$var37 - 1))) {
								for(int var15 = 0; var15 < states; var15 += 1) {
									if((var15 == traceTempVariable$var40$13_1)) {
										{
											if(!fixedFlag$sample46) {
												double[] var41 = m[traceTempVariable$var40$13_1];
												for(int index$var42 = 0; index$var42 < states; index$var42 += 1)
													// Save the probability of each value
													cv$distribution$sample46[index$var42] = (cv$distribution$sample46[index$var42] + (cv$probabilitySample46Value11 * (((0.0 <= index$var42) && (index$var42 < var41.length))?var41[index$var42]:0.0)));
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
			double cv$var42$sum = 0.0;
			for(int index$var42 = 0; index$var42 < states; index$var42 += 1) {
				if(!fixedFlag$sample46)
					// sum the probability of each value
					cv$var42$sum = (cv$var42$sum + cv$distribution$sample46[index$var42]);
			}
			for(int index$var42 = 0; index$var42 < states; index$var42 += 1) {
				if(!fixedFlag$sample46)
					// Normalise the probability of each value
					cv$distribution$sample46[index$var42] = (cv$distribution$sample46[index$var42] / cv$var42$sum);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1) {
						double[] var16 = m[var15];
						if(!fixedFlag$sample17)
							DistributionSampling.sampleDirichlet(RNG$1, v, var16);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
						if(!fixedFlag$sample27)
							bias[var24] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample36)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
			if(!fixedFlag$sample46)
				st[i$var37] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var37 - 1)]]);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1) {
							if(!fixedFlag$sample17)
								sample17(var15, threadID$var15, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
							if(!fixedFlag$sample27)
								sample27(var24, threadID$var24, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample36)
				sample36();
			for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
				if(!fixedFlag$sample46)
					sample46(i$var37);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i$var37 = (samples - ((((samples - 1) - 1) % 1) + 1)); i$var37 >= ((1 - 1) + 1); i$var37 -= 1) {
				if(!fixedFlag$sample46)
					sample46(i$var37);
			}
			if(!fixedFlag$sample36)
				sample36();
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
							if(!fixedFlag$sample27)
								sample27(var24, threadID$var24, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1) {
							if(!fixedFlag$sample17)
								sample17(var15, threadID$var15, RNG$1);
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
			(int forStart$i$var8, int forEnd$i$var8, int threadID$i$var8, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var8 = forStart$i$var8; i$var8 < forEnd$i$var8; i$var8 += 1)
						v[i$var8] = 0.1;
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
		logProbability$var11 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$var16 = 0.0;
		logProbability$var20 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample27)
			logProbability$var25 = 0.0;
		logProbability$var32 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample36)
			logProbability$var33 = 0.0;
		for(int i$var37 = 1; i$var37 < samples; i$var37 += 1)
			logProbability$var42[((i$var37 - 1) / 1)] = 0.0;
		if(!fixedProbFlag$sample46) {
			for(int i$var37 = 1; i$var37 < samples; i$var37 += 1)
				logProbability$sample46[((i$var37 - 1) / 1)] = 0.0;
		}
		for(int j = 0; j < samples; j += 1)
			logProbability$var51[((j - 0) / 1)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample55) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample55[((j - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample17)
			logProbabilityValue$sample17();
		if(fixedFlag$sample27)
			logProbabilityValue$sample27();
		logProbabilityValue$sample55();
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
		logProbabilityValue$sample17();
		logProbabilityValue$sample27();
		logProbabilityDistribution$sample36();
		logProbabilityDistribution$sample46();
		logProbabilityDistribution$sample55();
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
		logProbabilityValue$sample17();
		logProbabilityValue$sample27();
		logProbabilityValue$sample36();
		logProbabilityValue$sample46();
		logProbabilityValue$sample55();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1) {
						double[] var16 = m[var15];
						if(!fixedFlag$sample17)
							DistributionSampling.sampleDirichlet(RNG$1, v, var16);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
						if(!fixedFlag$sample27)
							bias[var24] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample36)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
			if(!fixedFlag$sample46)
				st[i$var37] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var37 - 1)]]);
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart7(boolean[] flipsMeasured) {\n        int states = 5;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n        \n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        int[] st = new int[samples];\n\n        st[0] = categorical(m[0]).sampleDistribution();\n\n        for(int i:[1..samples))\n            st[i] = categorical(m[st[i-1]]).sampleDistribution();\n            \n        boolean[] flips = new boolean[samples];\n            \n        for(int j:[0..samples))\n            flips[j] = bernoulli(bias[st[j]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
	}
}