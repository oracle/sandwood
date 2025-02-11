package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMM_Mk2Dist$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMM_Mk2Dist$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] bias;
	private double[] cv$distributionAccumulator$var54;
	private double[][] cv$distributionAccumulator$var73;
	private double[][] cv$var25$countGlobal;
	private double[][] cv$var32$countGlobal;
	private double[] cv$var45$countGlobal;
	private double[] cv$var47$stateProbabilityGlobal;
	private double[][] cv$var55$stateProbabilityGlobal;
	private double[][] cv$var74$stateProbabilityGlobal;
	private double[][] distribution$sample61;
	private double[][][] distribution$sample81;
	private int[][] events;
	private int[][] eventsMeasured;
	private boolean fixedFlag$sample103 = false;
	private boolean fixedFlag$sample26 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample51 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedFlag$sample61 = false;
	private boolean fixedFlag$sample81 = false;
	private boolean fixedProbFlag$sample103 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample51 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean fixedProbFlag$sample61 = false;
	private boolean fixedProbFlag$sample81 = false;
	private int initialState;
	private int[] length$eventsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$events;
	private double logProbability$initialState;
	private double logProbability$m;
	private double[][] logProbability$sample103;
	private double[] logProbability$sample61;
	private double[][] logProbability$sample81;
	private double logProbability$st;
	private double logProbability$var20;
	private double logProbability$var25;
	private double logProbability$var27;
	private double logProbability$var32;
	private double logProbability$var44;
	private double logProbability$var46;
	private double[] logProbability$var54;
	private double[][] logProbability$var73;
	private double[][] logProbability$var93;
	private double logProbability$weights;
	private double[][] m;
	private int noEvents;
	private int noStates;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$events = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private boolean setFlag$weights = false;
	private int[][] st;
	private boolean system$gibbsForward = true;
	private double[] v;
	private double[] v2;
	private double[] weights;

	public HMM_Mk2Dist$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for bias.
	@Override
	public final double[][] get$bias() {
		return bias;
	}

	// Setter for bias.
	@Override
	public final void set$bias(double[][] cv$value) {
		// Set flags for all the side effects of bias including if probabilities need to be
		// updated.
		// Set bias with flag to mark that it has been set so another array doesn't need to
		// be constructed
		bias = cv$value;
		setFlag$bias = true;
		
		// Unset the fixed probability flag for sample 34 as it depends on bias.
		fixedProbFlag$sample34 = false;
		
		// Unset the fixed probability flag for sample 103 as it depends on bias.
		fixedProbFlag$sample103 = false;
	}

	// Getter for events.
	@Override
	public final int[][] get$events() {
		return events;
	}

	// Setter for events.
	@Override
	public final void set$events(int[][] cv$value) {
		// Set flags for all the side effects of events including if probabilities need to
		// be updated.
		// Set events with flag to mark that it has been set so another array doesn't need
		// to be constructed
		events = cv$value;
		setFlag$events = true;
		
		// Unset the fixed probability flag for sample 103 as it depends on events.
		fixedProbFlag$sample103 = false;
	}

	// Getter for eventsMeasured.
	@Override
	public final int[][] get$eventsMeasured() {
		return eventsMeasured;
	}

	// Setter for eventsMeasured.
	@Override
	public final void set$eventsMeasured(int[][] cv$value) {
		// Set eventsMeasured with flag to mark that it has been set so another array doesn't
		// need to be constructed
		eventsMeasured = cv$value;
	}

	// Getter for fixedFlag$sample103.
	@Override
	public final boolean get$fixedFlag$sample103() {
		return fixedFlag$sample103;
	}

	// Setter for fixedFlag$sample103.
	@Override
	public final void set$fixedFlag$sample103(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample103 including if probabilities
		// need to be updated.
		fixedFlag$sample103 = cv$value;
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample103 = (fixedFlag$sample103 && fixedProbFlag$sample103);
	}

	// Getter for fixedFlag$sample26.
	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	// Setter for fixedFlag$sample26.
	@Override
	public final void set$fixedFlag$sample26(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample26 including if probabilities
		// need to be updated.
		fixedFlag$sample26 = cv$value;
		
		// Should the probability of sample 26 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample26 = (fixedFlag$sample26 && fixedProbFlag$sample26);
		
		// Should the probability of sample 61 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample61 = (fixedFlag$sample26 && fixedProbFlag$sample61);
		
		// Should the probability of sample 81 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample81 = (fixedFlag$sample26 && fixedProbFlag$sample81);
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
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample103 = (fixedFlag$sample34 && fixedProbFlag$sample103);
	}

	// Getter for fixedFlag$sample51.
	@Override
	public final boolean get$fixedFlag$sample51() {
		return fixedFlag$sample51;
	}

	// Setter for fixedFlag$sample51.
	@Override
	public final void set$fixedFlag$sample51(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample51 including if probabilities
		// need to be updated.
		fixedFlag$sample51 = cv$value;
		
		// Should the probability of sample 51 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample51 = (fixedFlag$sample51 && fixedProbFlag$sample51);
		
		// Should the probability of sample 53 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample53 = (fixedFlag$sample51 && fixedProbFlag$sample53);
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
		
		// Should the probability of sample 61 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample61 = (fixedFlag$sample53 && fixedProbFlag$sample61);
	}

	// Getter for fixedFlag$sample61.
	@Override
	public final boolean get$fixedFlag$sample61() {
		return fixedFlag$sample61;
	}

	// Setter for fixedFlag$sample61.
	@Override
	public final void set$fixedFlag$sample61(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample61 including if probabilities
		// need to be updated.
		fixedFlag$sample61 = cv$value;
		
		// Should the probability of sample 61 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample61 = (fixedFlag$sample61 && fixedProbFlag$sample61);
		
		// Should the probability of sample 81 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample81 = (fixedFlag$sample61 && fixedProbFlag$sample81);
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample103 = (fixedFlag$sample61 && fixedProbFlag$sample103);
	}

	// Getter for fixedFlag$sample81.
	@Override
	public final boolean get$fixedFlag$sample81() {
		return fixedFlag$sample81;
	}

	// Setter for fixedFlag$sample81.
	@Override
	public final void set$fixedFlag$sample81(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample81 including if probabilities
		// need to be updated.
		fixedFlag$sample81 = cv$value;
		
		// Should the probability of sample 81 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample81 = (fixedFlag$sample81 && fixedProbFlag$sample81);
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample103 = (fixedFlag$sample81 && fixedProbFlag$sample103);
	}

	// Getter for initialState.
	@Override
	public final int get$initialState() {
		return initialState;
	}

	// Setter for initialState.
	@Override
	public final void set$initialState(int cv$value) {
		// Set flags for all the side effects of initialState including if probabilities need
		// to be updated.
		initialState = cv$value;
		
		// Unset the fixed probability flag for sample 53 as it depends on initialState.
		fixedProbFlag$sample53 = false;
		
		// Unset the fixed probability flag for sample 61 as it depends on initialState.
		fixedProbFlag$sample61 = false;
	}

	// Getter for length$eventsMeasured.
	@Override
	public final int[] get$length$eventsMeasured() {
		return length$eventsMeasured;
	}

	// Setter for length$eventsMeasured.
	@Override
	public final void set$length$eventsMeasured(int[] cv$value) {
		// Set length$eventsMeasured with flag to mark that it has been set so another array
		// doesn't need to be constructed
		length$eventsMeasured = cv$value;
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

	// Getter for logProbability$events.
	@Override
	public final double get$logProbability$events() {
		return logProbability$events;
	}

	// Getter for logProbability$initialState.
	@Override
	public final double get$logProbability$initialState() {
		return logProbability$initialState;
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

	// Getter for logProbability$weights.
	@Override
	public final double get$logProbability$weights() {
		return logProbability$weights;
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
		
		// Unset the fixed probability flag for sample 26 as it depends on m.
		fixedProbFlag$sample26 = false;
		
		// Unset the fixed probability flag for sample 61 as it depends on m.
		fixedProbFlag$sample61 = false;
		
		// Unset the fixed probability flag for sample 81 as it depends on m.
		fixedProbFlag$sample81 = false;
	}

	// Getter for noEvents.
	@Override
	public final int get$noEvents() {
		return noEvents;
	}

	// Setter for noEvents.
	@Override
	public final void set$noEvents(int cv$value) {
		noEvents = cv$value;
	}

	// Getter for noStates.
	@Override
	public final int get$noStates() {
		return noStates;
	}

	// Setter for noStates.
	@Override
	public final void set$noStates(int cv$value) {
		noStates = cv$value;
	}

	// Getter for samples.
	@Override
	public final int get$samples() {
		return samples;
	}

	// Getter for st.
	@Override
	public final int[][] get$st() {
		return st;
	}

	// Setter for st.
	@Override
	public final void set$st(int[][] cv$value) {
		// Set flags for all the side effects of st including if probabilities need to be
		// updated.
		// Set st with flag to mark that it has been set so another array doesn't need to
		// be constructed
		st = cv$value;
		setFlag$st = true;
		
		// Unset the fixed probability flag for sample 61 as it depends on st.
		fixedProbFlag$sample61 = false;
		
		// Unset the fixed probability flag for sample 81 as it depends on st.
		fixedProbFlag$sample81 = false;
		
		// Unset the fixed probability flag for sample 103 as it depends on st.
		fixedProbFlag$sample103 = false;
	}

	// Getter for v.
	@Override
	public final double[] get$v() {
		return v;
	}

	// Getter for v2.
	@Override
	public final double[] get$v2() {
		return v2;
	}

	// Getter for weights.
	@Override
	public final double[] get$weights() {
		return weights;
	}

	// Setter for weights.
	@Override
	public final void set$weights(double[] cv$value) {
		// Set flags for all the side effects of weights including if probabilities need to
		// be updated.
		// Set weights with flag to mark that it has been set so another array doesn't need
		// to be constructed
		weights = cv$value;
		setFlag$weights = true;
		
		// Unset the fixed probability flag for sample 51 as it depends on weights.
		fixedProbFlag$sample51 = false;
		
		// Unset the fixed probability flag for sample 53 as it depends on weights.
		fixedProbFlag$sample53 = false;
	}

	// Calculate the probability of the samples represented by sample103 using probability
	// distributions.
	private final void logProbabilityDistribution$sample103() {
		// Determine if we need to calculate the values for sample task 103 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample103) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
				for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Look for paths between the variable and the sample task 103 including any distribution
					// values.
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = (events[i$var80][j$var88] - 1);
						
						// Enumerating the possible arguments for Categorical 93.
						if(fixedFlag$sample61) {
							for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
								if((i$var50 == i$var80)) {
									if((0 == j$var88)) {
										for(int var31 = 0; var31 < noStates; var31 += 1) {
											if((var31 == st[i$var80][j$var88])) {
												{
													double[] var92 = bias[st[i$var80][j$var88]];
													
													// Store the value of the function call, so the function call is only made once.
													double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var92.length))?Math.log(var92[cv$sampleValue]):Double.NEGATIVE_INFINITY));
													
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
						} else {
							for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
								if(true) {
									// Enumerating the possible outputs of Categorical 54.
									for(int index$sample61$4 = 0; index$sample61$4 < noStates; index$sample61$4 += 1) {
										int distributionTempVariable$var55$6 = index$sample61$4;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample61Value5 = (1.0 * distribution$sample61[((i$var50 - 0) / 1)][index$sample61$4]);
										int traceTempVariable$var91$7_1 = distributionTempVariable$var55$6;
										if((i$var50 == i$var80)) {
											if((0 == j$var88)) {
												for(int var31 = 0; var31 < noStates; var31 += 1) {
													if((var31 == traceTempVariable$var91$7_1)) {
														{
															double[] var92 = bias[traceTempVariable$var91$7_1];
															
															// Store the value of the function call, so the function call is only made once.
															double cv$weightedProbability = (Math.log(cv$probabilitySample61Value5) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var92.length))?Math.log(var92[cv$sampleValue]):Double.NEGATIVE_INFINITY));
															
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
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample61Value5);
														}
													}
												}
											}
										}
									}
								}
							}
						}
						
						// Enumerating the possible arguments for Categorical 93.
						if(fixedFlag$sample81) {
							for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
								for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
									if((i$var60 == i$var80)) {
										if((j$var66 == j$var88)) {
											for(int var31 = 0; var31 < noStates; var31 += 1) {
												if((var31 == st[i$var80][j$var88])) {
													{
														double[] var92 = bias[st[i$var80][j$var88]];
														
														// Store the value of the function call, so the function call is only made once.
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var92.length))?Math.log(var92[cv$sampleValue]):Double.NEGATIVE_INFINITY));
														
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
							for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
								for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 73.
										for(int index$sample81$13 = 0; index$sample81$13 < noStates; index$sample81$13 += 1) {
											int distributionTempVariable$var74$15 = index$sample81$13;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample81Value14 = (1.0 * distribution$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)][index$sample81$13]);
											int traceTempVariable$var91$16_1 = distributionTempVariable$var74$15;
											if((i$var60 == i$var80)) {
												if((j$var66 == j$var88)) {
													for(int var31 = 0; var31 < noStates; var31 += 1) {
														if((var31 == traceTempVariable$var91$16_1)) {
															{
																double[] var92 = bias[traceTempVariable$var91$16_1];
																
																// Store the value of the function call, so the function call is only made once.
																double cv$weightedProbability = (Math.log(cv$probabilitySample81Value14) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var92.length))?Math.log(var92[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																
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
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample81Value14);
															}
														}
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
					logProbability$var93[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample103[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)] = cv$sampleProbability;
				}
			}
			
			// Update the variable probability
			logProbability$events = (logProbability$events + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample103 = (((fixedFlag$sample103 && fixedFlag$sample34) && fixedFlag$sample61) && fixedFlag$sample81);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
				for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample103[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var93[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$events = (logProbability$events + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample61 using probability
	// distributions.
	private final void logProbabilityDistribution$sample61() {
		// Determine if we need to calculate the values for sample task 61 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample61) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample61) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Copy of index so that its values can be safely substituted
					int index$i$1 = i$var50;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[i$var50][0];
						{
							{
								double[] var53 = m[initialState];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var53.length))?Math.log(var53[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
					logProbability$var54[((i$var50 - 0) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample61[((i$var50 - 0) / 1)] = cv$sampleProbability;
				}
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(fixedFlag$sample61)
					// Update the variable probability
					logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample61)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample61 = ((fixedFlag$sample61 && fixedFlag$sample26) && fixedFlag$sample53);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample61[((i$var50 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var54[((i$var50 - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample61)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample81 using probability
	// distributions.
	private final void logProbabilityDistribution$sample81() {
		// Determine if we need to calculate the values for sample task 81 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample81) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample81) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
					for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
						// Accumulator for sample probabilities for a specific instance of the random variable.
						double cv$sampleAccumulator = 0.0;
						
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// Look for paths between the variable and the sample task 81 including any distribution
						// values.
						// 
						// Copy of index so that its values can be safely substituted
						int index$j$1 = j$var66;
						
						// Copy of index so that its values can be safely substituted
						int index$i$2 = i$var60;
						{
							// The sample value to calculate the probability of generating
							int cv$sampleValue = st[i$var60][j$var66];
							
							// Enumerating the possible arguments for Categorical 73.
							if(fixedFlag$sample61) {
								for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
									if((i$var50 == i$var60)) {
										if((0 == (j$var66 - 1))) {
											for(int var24 = 0; var24 < noStates; var24 += 1) {
												if((var24 == st[i$var60][(j$var66 - 1)])) {
													{
														double[] var72 = m[st[i$var60][(j$var66 - 1)]];
														
														// Store the value of the function call, so the function call is only made once.
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var72.length))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
														
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
							} else {
								for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 54.
										for(int index$sample61$6 = 0; index$sample61$6 < noStates; index$sample61$6 += 1) {
											int distributionTempVariable$var55$8 = index$sample61$6;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample61Value7 = (1.0 * distribution$sample61[((i$var50 - 0) / 1)][index$sample61$6]);
											int traceTempVariable$var71$9_1 = distributionTempVariable$var55$8;
											if((i$var50 == i$var60)) {
												if((0 == (j$var66 - 1))) {
													for(int var24 = 0; var24 < noStates; var24 += 1) {
														if((var24 == traceTempVariable$var71$9_1)) {
															{
																double[] var72 = m[traceTempVariable$var71$9_1];
																
																// Store the value of the function call, so the function call is only made once.
																double cv$weightedProbability = (Math.log(cv$probabilitySample61Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var72.length))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																
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
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample61Value7);
															}
														}
													}
												}
											}
										}
									}
								}
							}
							
							// Enumerating the possible arguments for Categorical 73.
							if((index$i$2 == i$var60)) {
								if((index$j$1 == (j$var66 - 1))) {
									for(int var24 = 0; var24 < noStates; var24 += 1) {
										if((var24 == st[i$var60][(j$var66 - 1)])) {
											{
												double[] var72 = m[st[i$var60][(j$var66 - 1)]];
												
												// Store the value of the function call, so the function call is only made once.
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var72.length))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
												
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
							if(fixedFlag$sample81) {
								for(int index$i$13_1 = 0; index$i$13_1 < samples; index$i$13_1 += 1) {
									for(int index$j$13_2 = 1; index$j$13_2 < length$eventsMeasured[index$i$13_1]; index$j$13_2 += 1) {
										if((index$i$13_1 == i$var60)) {
											if((index$j$13_2 == (j$var66 - 1))) {
												for(int var24 = 0; var24 < noStates; var24 += 1) {
													if((var24 == st[i$var60][(j$var66 - 1)])) {
														{
															double[] var72 = m[st[i$var60][(j$var66 - 1)]];
															
															// Store the value of the function call, so the function call is only made once.
															double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var72.length))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
															
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
								for(int index$i$14 = 0; index$i$14 < samples; index$i$14 += 1) {
									for(int index$j$15 = 1; index$j$15 < length$eventsMeasured[index$i$14]; index$j$15 += 1) {
										if(!((index$i$14 == index$i$2) && (index$j$15 == index$j$1))) {
											// Enumerating the possible outputs of Categorical 73.
											for(int index$sample81$16 = 0; index$sample81$16 < noStates; index$sample81$16 += 1) {
												int distributionTempVariable$var74$18 = index$sample81$16;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample81Value17 = (1.0 * distribution$sample81[((index$i$14 - 0) / 1)][((index$j$15 - 1) / 1)][index$sample81$16]);
												int traceTempVariable$var71$19_1 = distributionTempVariable$var74$18;
												if((index$i$14 == i$var60)) {
													if((index$j$15 == (j$var66 - 1))) {
														for(int var24 = 0; var24 < noStates; var24 += 1) {
															if((var24 == traceTempVariable$var71$19_1)) {
																{
																	double[] var72 = m[traceTempVariable$var71$19_1];
																	
																	// Store the value of the function call, so the function call is only made once.
																	double cv$weightedProbability = (Math.log(cv$probabilitySample81Value17) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var72.length))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																	
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
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample81Value17);
																}
															}
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
						logProbability$var73[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)] = cv$sampleAccumulator;
						
						// Store the sample task probability
						logProbability$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)] = cv$sampleProbability;
					}
				}
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(fixedFlag$sample81)
					// Update the variable probability
					logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample81)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample81 = ((fixedFlag$sample81 && fixedFlag$sample26) && fixedFlag$sample61);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var73[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample81)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample81)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample103 using sampled
	// values.
	private final void logProbabilityValue$sample103() {
		// Determine if we need to calculate the values for sample task 103 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample103) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
				for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = (events[i$var80][j$var88] - 1);
						{
							{
								double[] var92 = bias[st[i$var80][j$var88]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var92.length))?Math.log(var92[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
					logProbability$var93[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample103[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)] = cv$sampleProbability;
				}
			}
			
			// Update the variable probability
			logProbability$events = (logProbability$events + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample103 = (((fixedFlag$sample103 && fixedFlag$sample34) && fixedFlag$sample61) && fixedFlag$sample81);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
				for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample103[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var93[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$events = (logProbability$events + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample26 using sampled
	// values.
	private final void logProbabilityValue$sample26() {
		// Determine if we need to calculate the values for sample task 26 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample26) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var24 = 0; var24 < noStates; var24 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double[] cv$sampleValue = m[var24];
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
			logProbability$var20 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var25 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample26 = fixedFlag$sample26;
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
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample26)
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
			for(int var31 = 0; var31 < noStates; var31 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double[] cv$sampleValue = bias[var31];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v2));
							
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
			logProbability$var27 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var32 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
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
			double cv$sampleValue = logProbability$var32;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var27 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample51 using sampled
	// values.
	private final void logProbabilityValue$sample51() {
		// Determine if we need to calculate the values for sample task 51 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample51) {
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
				double[] cv$sampleValue = weights;
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
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var44 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$weights = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample51 = fixedFlag$sample51;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$weights;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var44 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample51)
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
				int cv$sampleValue = initialState;
				{
					{
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weights.length))?Math.log(weights[cv$sampleValue]):Double.NEGATIVE_INFINITY));
						
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
			logProbability$var46 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$initialState = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedFlag$sample51);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$initialState;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var46 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample61 using sampled
	// values.
	private final void logProbabilityValue$sample61() {
		// Determine if we need to calculate the values for sample task 61 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample61) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Copy of index so that its values can be safely substituted
				int index$i$1 = i$var50;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[i$var50][0];
					{
						{
							double[] var53 = m[initialState];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var53.length))?Math.log(var53[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
				logProbability$var54[((i$var50 - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample61[((i$var50 - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample61 = ((fixedFlag$sample61 && fixedFlag$sample26) && fixedFlag$sample53);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample61[((i$var50 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var54[((i$var50 - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample81 using sampled
	// values.
	private final void logProbabilityValue$sample81() {
		// Determine if we need to calculate the values for sample task 81 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample81) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Copy of index so that its values can be safely substituted
					int index$j$1 = j$var66;
					
					// Copy of index so that its values can be safely substituted
					int index$i$2 = i$var60;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[i$var60][j$var66];
						{
							{
								double[] var72 = m[st[i$var60][(j$var66 - 1)]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var72.length))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
					logProbability$var73[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)] = cv$sampleProbability;
				}
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample81)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample81 = ((fixedFlag$sample81 && fixedFlag$sample26) && fixedFlag$sample61);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var73[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample81)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 26 drawn from Dirichlet 20. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample26(int var24, int threadID$cv$var24, Rng RNG$) {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = m[var24];
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var25$countGlobal[threadID$cv$var24];
		
		// Get the length of the array
		int cv$arrayLength = noStates;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 54.
			{
				// Looking for a path between Sample 26 and consumer Categorical 54.
				{
					if((var24 == initialState)) {
						for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
							if(fixedFlag$sample61) {
								// Processing sample task 61 of consumer random variable null.
								{
									// Copy of index so that its values can be safely substituted
									int index$i$3 = i$var50;
									{
										{
											{
												{
													// Increment the sample counter with the value sampled by sample task 61 of random
													// variable var54
													cv$countLocal[st[i$var50][0]] = (cv$countLocal[st[i$var50][0]] + 1.0);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Processing random variable 73.
			{
				// Looking for a path between Sample 26 and consumer Categorical 73.
				{
					for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
						for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
							if(fixedFlag$sample61) {
								for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
									if((i$var50 == i$var60)) {
										if((0 == (j$var66 - 1))) {
											if((var24 == st[i$var60][(j$var66 - 1)])) {
												if(fixedFlag$sample81) {
													// Processing sample task 81 of consumer random variable null.
													{
														// Copy of index so that its values can be safely substituted
														int index$j$27 = j$var66;
														
														// Copy of index so that its values can be safely substituted
														int index$i$28 = i$var60;
														{
															{
																{
																	{
																		// Increment the sample counter with the value sampled by sample task 81 of random
																		// variable var73
																		cv$countLocal[st[i$var60][j$var66]] = (cv$countLocal[st[i$var60][j$var66]] + 1.0);
																	}
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
								for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 54.
										for(int index$sample61$9 = 0; index$sample61$9 < noStates; index$sample61$9 += 1) {
											int distributionTempVariable$var55$11 = index$sample61$9;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample61Value10 = (1.0 * distribution$sample61[((i$var50 - 0) / 1)][index$sample61$9]);
											int traceTempVariable$var71$12_1 = distributionTempVariable$var55$11;
											if((i$var50 == i$var60)) {
												if((0 == (j$var66 - 1))) {
													if((var24 == traceTempVariable$var71$12_1)) {
														if(fixedFlag$sample81) {
															// Processing sample task 81 of consumer random variable null.
															{
																// Copy of index so that its values can be safely substituted
																int index$j$30 = j$var66;
																
																// Copy of index so that its values can be safely substituted
																int index$i$31 = i$var60;
																{
																	{
																		{
																			{
																				// Increment the sample counter with the value sampled by sample task 81 of random
																				// variable var73
																				cv$countLocal[st[i$var60][j$var66]] = (cv$countLocal[st[i$var60][j$var66]] + cv$probabilitySample61Value10);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
						for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
							if(fixedFlag$sample81) {
								for(int index$i$17_1 = 0; index$i$17_1 < samples; index$i$17_1 += 1) {
									for(int index$j$17_2 = 1; index$j$17_2 < length$eventsMeasured[index$i$17_1]; index$j$17_2 += 1) {
										if((index$i$17_1 == i$var60)) {
											if((index$j$17_2 == (j$var66 - 1))) {
												if((var24 == st[i$var60][(j$var66 - 1)])) {
													if(fixedFlag$sample81) {
														// Processing sample task 81 of consumer random variable null.
														{
															// Copy of index so that its values can be safely substituted
															int index$j$33 = j$var66;
															
															// Copy of index so that its values can be safely substituted
															int index$i$34 = i$var60;
															{
																{
																	{
																		{
																			// Increment the sample counter with the value sampled by sample task 81 of random
																			// variable var73
																			cv$countLocal[st[i$var60][j$var66]] = (cv$countLocal[st[i$var60][j$var66]] + 1.0);
																		}
																	}
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
								for(int index$i$18 = 0; index$i$18 < samples; index$i$18 += 1) {
									for(int index$j$19 = 1; index$j$19 < length$eventsMeasured[index$i$18]; index$j$19 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 73.
											for(int index$sample81$20 = 0; index$sample81$20 < noStates; index$sample81$20 += 1) {
												int distributionTempVariable$var74$22 = index$sample81$20;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample81Value21 = (1.0 * distribution$sample81[((index$i$18 - 0) / 1)][((index$j$19 - 1) / 1)][index$sample81$20]);
												int traceTempVariable$var71$23_1 = distributionTempVariable$var74$22;
												if((index$i$18 == i$var60)) {
													if((index$j$19 == (j$var66 - 1))) {
														if((var24 == traceTempVariable$var71$23_1)) {
															if(fixedFlag$sample81) {
																// Processing sample task 81 of consumer random variable null.
																{
																	// Copy of index so that its values can be safely substituted
																	int index$j$36 = j$var66;
																	
																	// Copy of index so that its values can be safely substituted
																	int index$i$37 = i$var60;
																	{
																		{
																			{
																				{
																					// Increment the sample counter with the value sampled by sample task 81 of random
																					// variable var73
																					cv$countLocal[st[i$var60][j$var66]] = (cv$countLocal[st[i$var60][j$var66]] + cv$probabilitySample81Value21);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Processing random variable 54.
		{
			// Looking for a path between Sample 26 and consumer Categorical 54.
			{
				if((var24 == initialState)) {
					for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
						if(!fixedFlag$sample61) {
							// Processing sample task 61 of consumer random variable null.
							{
								// Copy of index so that its values can be safely substituted
								int index$i$44 = i$var50;
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
											cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample61[((i$var50 - 0) / 1)][cv$loopIndex] * cv$distributionProbability));
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Processing random variable 73.
		{
			// Looking for a path between Sample 26 and consumer Categorical 73.
			{
				for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
					for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
						if(fixedFlag$sample61) {
							for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
								if((i$var50 == i$var60)) {
									if((0 == (j$var66 - 1))) {
										if((var24 == st[i$var60][(j$var66 - 1)])) {
											if(!fixedFlag$sample81) {
												// Processing sample task 81 of consumer random variable null.
												{
													// Copy of index so that its values can be safely substituted
													int index$j$67 = j$var66;
													
													// Copy of index so that its values can be safely substituted
													int index$i$68 = i$var60;
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
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
														}
													}
												}
											}
										}
									}
								}
							}
						} else {
							for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
								if(true) {
									// Enumerating the possible outputs of Categorical 54.
									for(int index$sample61$49 = 0; index$sample61$49 < noStates; index$sample61$49 += 1) {
										int distributionTempVariable$var55$51 = index$sample61$49;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample61Value50 = (1.0 * distribution$sample61[((i$var50 - 0) / 1)][index$sample61$49]);
										int traceTempVariable$var71$52_1 = distributionTempVariable$var55$51;
										if((i$var50 == i$var60)) {
											if((0 == (j$var66 - 1))) {
												if((var24 == traceTempVariable$var71$52_1)) {
													if(!fixedFlag$sample81) {
														// Processing sample task 81 of consumer random variable null.
														{
															// Copy of index so that its values can be safely substituted
															int index$j$70 = j$var66;
															
															// Copy of index so that its values can be safely substituted
															int index$i$71 = i$var60;
															{
																{
																	// Declare and zero an accumulator for tracking the reached source probability space.
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		// Add the probability of this argument configuration.
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																	
																	// The probability of reaching the consumer with this set of consumer arguments
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample61Value50);
																	
																	// Merge the distribution probabilities into the count
																	for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
					for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
						if(fixedFlag$sample81) {
							for(int index$i$57_1 = 0; index$i$57_1 < samples; index$i$57_1 += 1) {
								for(int index$j$57_2 = 1; index$j$57_2 < length$eventsMeasured[index$i$57_1]; index$j$57_2 += 1) {
									if((index$i$57_1 == i$var60)) {
										if((index$j$57_2 == (j$var66 - 1))) {
											if((var24 == st[i$var60][(j$var66 - 1)])) {
												if(!fixedFlag$sample81) {
													// Processing sample task 81 of consumer random variable null.
													{
														// Copy of index so that its values can be safely substituted
														int index$j$73 = j$var66;
														
														// Copy of index so that its values can be safely substituted
														int index$i$74 = i$var60;
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
																	cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
							for(int index$i$58 = 0; index$i$58 < samples; index$i$58 += 1) {
								for(int index$j$59 = 1; index$j$59 < length$eventsMeasured[index$i$58]; index$j$59 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 73.
										for(int index$sample81$60 = 0; index$sample81$60 < noStates; index$sample81$60 += 1) {
											int distributionTempVariable$var74$62 = index$sample81$60;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample81Value61 = (1.0 * distribution$sample81[((index$i$58 - 0) / 1)][((index$j$59 - 1) / 1)][index$sample81$60]);
											int traceTempVariable$var71$63_1 = distributionTempVariable$var74$62;
											if((index$i$58 == i$var60)) {
												if((index$j$59 == (j$var66 - 1))) {
													if((var24 == traceTempVariable$var71$63_1)) {
														if(!fixedFlag$sample81) {
															// Processing sample task 81 of consumer random variable null.
															{
																// Copy of index so that its values can be safely substituted
																int index$j$76 = j$var66;
																
																// Copy of index so that its values can be safely substituted
																int index$i$77 = i$var60;
																{
																	{
																		// Declare and zero an accumulator for tracking the reached source probability space.
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			// Add the probability of this argument configuration.
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		
																		// The probability of reaching the consumer with this set of consumer arguments
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample81Value61);
																		
																		// Merge the distribution probabilities into the count
																		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																			cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																	}
																}
															}
														}
													}
												}
											}
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
	// by sample task 34 drawn from Dirichlet 27. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample34(int var31, int threadID$cv$var31, Rng RNG$) {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = bias[var31];
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var32$countGlobal[threadID$cv$var31];
		
		// Get the length of the array
		int cv$arrayLength = noEvents;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 93.
			{
				// Looking for a path between Sample 34 and consumer Categorical 93.
				{
					for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
						for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
							if(fixedFlag$sample61) {
								for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
									if((i$var50 == i$var80)) {
										if((0 == j$var88)) {
											if((var31 == st[i$var80][j$var88])) {
												// Processing sample task 103 of consumer random variable null.
												{
													{
														{
															{
																{
																	// Increment the sample counter with the value sampled by sample task 103 of random
																	// variable var93
																	cv$countLocal[(events[i$var80][j$var88] - 1)] = (cv$countLocal[(events[i$var80][j$var88] - 1)] + 1.0);
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
								for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 54.
										for(int index$sample61$5 = 0; index$sample61$5 < noStates; index$sample61$5 += 1) {
											int distributionTempVariable$var55$7 = index$sample61$5;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample61Value6 = (1.0 * distribution$sample61[((i$var50 - 0) / 1)][index$sample61$5]);
											int traceTempVariable$var91$8_1 = distributionTempVariable$var55$7;
											if((i$var50 == i$var80)) {
												if((0 == j$var88)) {
													if((var31 == traceTempVariable$var91$8_1)) {
														// Processing sample task 103 of consumer random variable null.
														{
															{
																{
																	{
																		{
																			// Increment the sample counter with the value sampled by sample task 103 of random
																			// variable var93
																			cv$countLocal[(events[i$var80][j$var88] - 1)] = (cv$countLocal[(events[i$var80][j$var88] - 1)] + cv$probabilitySample61Value6);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
						for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
							if(fixedFlag$sample81) {
								for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
									for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
										if((i$var60 == i$var80)) {
											if((j$var66 == j$var88)) {
												if((var31 == st[i$var80][j$var88])) {
													// Processing sample task 103 of consumer random variable null.
													{
														{
															{
																{
																	{
																		// Increment the sample counter with the value sampled by sample task 103 of random
																		// variable var93
																		cv$countLocal[(events[i$var80][j$var88] - 1)] = (cv$countLocal[(events[i$var80][j$var88] - 1)] + 1.0);
																	}
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
								for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
									for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 73.
											for(int index$sample81$16 = 0; index$sample81$16 < noStates; index$sample81$16 += 1) {
												int distributionTempVariable$var74$18 = index$sample81$16;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample81Value17 = (1.0 * distribution$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)][index$sample81$16]);
												int traceTempVariable$var91$19_1 = distributionTempVariable$var74$18;
												if((i$var60 == i$var80)) {
													if((j$var66 == j$var88)) {
														if((var31 == traceTempVariable$var91$19_1)) {
															// Processing sample task 103 of consumer random variable null.
															{
																{
																	{
																		{
																			{
																				// Increment the sample counter with the value sampled by sample task 103 of random
																				// variable var93
																				cv$countLocal[(events[i$var80][j$var88] - 1)] = (cv$countLocal[(events[i$var80][j$var88] - 1)] + cv$probabilitySample81Value17);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
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
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v2, cv$countLocal, cv$targetLocal);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 51 drawn from Dirichlet 44. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample51() {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = weights;
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var45$countGlobal;
		
		// Get the length of the array
		int cv$arrayLength = noStates;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 46.
			{
				{
					// Processing sample task 53 of consumer random variable null.
					{
						{
							{
								{
									{
										// Increment the sample counter with the value sampled by sample task 53 of random
										// variable var46
										cv$countLocal[initialState] = (cv$countLocal[initialState] + 1.0);
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
	// by sample task 53 drawn from Categorical 46. Inference was performed using variable
	// marginalization.
	private final void sample53() {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		{
			// variable marginalization
			cv$noStates = Math.max(cv$noStates, noStates);
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var47$stateProbabilityGlobal;
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
			
			// Write out the new value of the sample.
			initialState = cv$currentValue;
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$weights;
				{
					cv$temp$0$weights = weights;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$weights.length))?Math.log(cv$temp$0$weights[cv$currentValue]):Double.NEGATIVE_INFINITY));
				
				// Processing random variable 54.
				{
					{
						for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
							int traceTempVariable$initialState$1_2 = cv$currentValue;
							if(fixedFlag$sample61) {
								// Processing sample task 61 of consumer random variable null.
								{
									// Copy of index so that its values can be safely substituted
									int index$i$3 = i$var50;
									
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
													double[] cv$temp$1$var53;
													{
														// Constructing a random variable input for use later.
														double[] var53 = m[traceTempVariable$initialState$1_2];
														cv$temp$1$var53 = var53;
													}
													
													// Record the probability of sample task 61 generating output with current configuration.
													if(((Math.log(1.0) + (((0.0 <= st[i$var50][0]) && (st[i$var50][0] < cv$temp$1$var53.length))?Math.log(cv$temp$1$var53[st[i$var50][0]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var50][0]) && (st[i$var50][0] < cv$temp$1$var53.length))?Math.log(cv$temp$1$var53[st[i$var50][0]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var50][0]) && (st[i$var50][0] < cv$temp$1$var53.length))?Math.log(cv$temp$1$var53[st[i$var50][0]]):Double.NEGATIVE_INFINITY));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var50][0]) && (st[i$var50][0] < cv$temp$1$var53.length))?Math.log(cv$temp$1$var53[st[i$var50][0]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var50][0]) && (st[i$var50][0] < cv$temp$1$var53.length))?Math.log(cv$temp$1$var53[st[i$var50][0]]):Double.NEGATIVE_INFINITY)));
													}
													
													// Recorded the probability of reaching sample task 61 with the current configuration.
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
			
			// Processing random variable 54.
			{
				{
					for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
						int traceTempVariable$initialState$5_2 = cv$currentValue;
						if(!fixedFlag$sample61) {
							// Processing sample task 61 of consumer random variable null.
							{
								// Copy of index so that its values can be safely substituted
								int index$i$7 = i$var50;
								
								// A local array to hold the accumulated distributions of the sample tasks for each
								// configuration of distributions.
								double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var54;
								
								// Zero all the elements in the distribution accumulator
								for(int cv$i = 0; cv$i < noStates; cv$i += 1)
									cv$accumulatedConsumerDistributions[cv$i] = 0.0;
								
								// Zero an accumulator to track the probabilities reached.
								double cv$reachedDistributionProbability = 0.0;
								{
									{
										// Declare and zero an accumulator for tracking the reached source probability space.
										double scopeVariable$reachedSourceProbability = 0.0;
										{
											// Add the probability of this argument configuration.
											scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
										}
										double[] cv$temp$2$var53;
										{
											// Constructing a random variable input for use later.
											double[] var53 = m[traceTempVariable$initialState$5_2];
											cv$temp$2$var53 = var53;
										}
										
										// The probability of reaching the consumer with this set of consumer arguments
										double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
										
										// Record the reached distribution.
										cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
										
										// Add the current distribution to the distribution accumulator.
										DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$2$var53);
									}
								}
								
								// A local copy of the samples' distribution.
								double[] cv$sampleDistribution = distribution$sample61[((i$var50 - 0) / 1)];
								
								// The overlap of the distributions so far.
								double cv$overlap = 0.0;
								
								// Calculate the overlap for each element in the distribution
								for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
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
		
		// Write out the new value of the sample.
		initialState = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 61 drawn from Categorical 54. Inference was performed using variable
	// marginalization.
	private final void sample61(int i$var50, int threadID$cv$i$var50, Rng RNG$) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		
		// Copy of index so that its values can be safely substituted
		int index$i$1 = i$var50;
		{
			// variable marginalization
			cv$noStates = Math.max(cv$noStates, noStates);
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var55$stateProbabilityGlobal[threadID$cv$i$var50];
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Copy of index so that its values can be safely substituted
			int index$i$2 = i$var50;
			
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
				double[] cv$temp$0$var53;
				{
					// Constructing a random variable input for use later.
					double[] var53 = m[initialState];
					cv$temp$0$var53 = var53;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var53.length))?Math.log(cv$temp$0$var53[cv$currentValue]):Double.NEGATIVE_INFINITY));
				
				// Processing random variable 73.
				{
					// Looking for a path between Sample 61 and consumer Categorical 73.
					{
						int traceTempVariable$var71$3_1 = cv$currentValue;
						for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
							if((i$var50 == i$var60)) {
								for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
									if((0 == (j$var66 - 1))) {
										if(fixedFlag$sample81) {
											// Processing sample task 81 of consumer random variable null.
											{
												// Copy of index so that its values can be safely substituted
												int index$j$5 = j$var66;
												
												// Copy of index so that its values can be safely substituted
												int index$i$6 = i$var60;
												
												// Set an accumulator to sum the probabilities for each possible configuration of
												// inputs.
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												
												// Set an accumulator to record the consumer distributions not seen. Initially set
												// to 1 as seen values will be deducted from this value.
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													// Enumerating the possible arguments for the variable Categorical 73 which is consuming
													// the output of Sample task 61.
													for(int var24 = 0; var24 < noStates; var24 += 1) {
														if((var24 == traceTempVariable$var71$3_1)) {
															{
																{
																	double[] cv$temp$1$var72;
																	{
																		// Constructing a random variable input for use later.
																		double[] var72 = m[traceTempVariable$var71$3_1];
																		cv$temp$1$var72 = var72;
																	}
																	
																	// Record the probability of sample task 81 generating output with current configuration.
																	if(((Math.log(1.0) + (((0.0 <= st[i$var60][j$var66]) && (st[i$var60][j$var66] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[i$var60][j$var66]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var60][j$var66]) && (st[i$var60][j$var66] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[i$var60][j$var66]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var60][j$var66]) && (st[i$var60][j$var66] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[i$var60][j$var66]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var60][j$var66]) && (st[i$var60][j$var66] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[i$var60][j$var66]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var60][j$var66]) && (st[i$var60][j$var66] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[i$var60][j$var66]]):Double.NEGATIVE_INFINITY)));
																	}
																	
																	// Recorded the probability of reaching sample task 81 with the current configuration.
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
				
				// Processing random variable 93.
				{
					// Looking for a path between Sample 61 and consumer Categorical 93.
					{
						int traceTempVariable$var91$9_1 = cv$currentValue;
						for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
							if((i$var50 == i$var80)) {
								for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
									if((0 == j$var88)) {
										// Processing sample task 103 of consumer random variable null.
										{
											// Set an accumulator to sum the probabilities for each possible configuration of
											// inputs.
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											
											// Set an accumulator to record the consumer distributions not seen. Initially set
											// to 1 as seen values will be deducted from this value.
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												// Enumerating the possible arguments for the variable Categorical 93 which is consuming
												// the output of Sample task 61.
												for(int var31 = 0; var31 < noStates; var31 += 1) {
													if((var31 == traceTempVariable$var91$9_1)) {
														{
															{
																double[] cv$temp$2$var92;
																{
																	// Constructing a random variable input for use later.
																	double[] var92 = bias[traceTempVariable$var91$9_1];
																	cv$temp$2$var92 = var92;
																}
																
																// Record the probability of sample task 103 generating output with current configuration.
																if(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$2$var92.length))?Math.log(cv$temp$2$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$2$var92.length))?Math.log(cv$temp$2$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$2$var92.length))?Math.log(cv$temp$2$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$2$var92.length))?Math.log(cv$temp$2$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$2$var92.length))?Math.log(cv$temp$2$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)));
																}
																
																// Recorded the probability of reaching sample task 103 with the current configuration.
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
			
			// Processing random variable 73.
			{
				// Looking for a path between Sample 61 and consumer Categorical 73.
				{
					int traceTempVariable$var71$13_1 = cv$currentValue;
					for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
						if((i$var50 == i$var60)) {
							for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
								if((0 == (j$var66 - 1))) {
									if(!fixedFlag$sample81) {
										// Processing sample task 81 of consumer random variable null.
										{
											// Copy of index so that its values can be safely substituted
											int index$j$15 = j$var66;
											
											// Copy of index so that its values can be safely substituted
											int index$i$16 = i$var60;
											
											// A local array to hold the accumulated distributions of the sample tasks for each
											// configuration of distributions.
											double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var73[threadID$cv$i$var50];
											
											// Zero all the elements in the distribution accumulator
											for(int cv$i = 0; cv$i < noStates; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											
											// Zero an accumulator to track the probabilities reached.
											double cv$reachedDistributionProbability = 0.0;
											
											// Enumerating the possible arguments for the variable Categorical 73 which is consuming
											// the output of Sample task 61.
											for(int var24 = 0; var24 < noStates; var24 += 1) {
												if((var24 == traceTempVariable$var71$13_1)) {
													{
														// Declare and zero an accumulator for tracking the reached source probability space.
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															// Add the probability of this argument configuration.
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														double[] cv$temp$3$var72;
														{
															// Constructing a random variable input for use later.
															double[] var72 = m[traceTempVariable$var71$13_1];
															cv$temp$3$var72 = var72;
														}
														
														// The probability of reaching the consumer with this set of consumer arguments
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
														
														// Record the reached distribution.
														cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
														
														// Add the current distribution to the distribution accumulator.
														DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$3$var72);
													}
												}
											}
											
											// A local copy of the samples' distribution.
											double[] cv$sampleDistribution = distribution$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)];
											
											// The overlap of the distributions so far.
											double cv$overlap = 0.0;
											
											// Calculate the overlap for each element in the distribution
											for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
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
			}
			
			// Save the calculated index value into the array of index value probabilities
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample61[((i$var50 - 0) / 1)];
		
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

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 81 drawn from Categorical 73. Inference was performed using variable
	// marginalization.
	private final void sample81(int i$var60, int j$var66, int threadID$cv$i$var60, Rng RNG$) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		
		// Exploring all the possible state counts for random variable 73.
		// 
		// Copy of index so that its values can be safely substituted
		int index$j$1 = j$var66;
		
		// Copy of index so that its values can be safely substituted
		int index$i$2 = i$var60;
		
		// Enumerating the possible arguments for Categorical 73.
		if(fixedFlag$sample61) {
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
				if((i$var50 == i$var60)) {
					if((0 == (j$var66 - 1))) {
						for(int var24 = 0; var24 < noStates; var24 += 1) {
							if((var24 == st[i$var60][(j$var66 - 1)]))
								// variable marginalization
								cv$noStates = Math.max(cv$noStates, noStates);
						}
					}
				}
			}
		} else {
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
				if(true) {
					// Enumerating the possible outputs of Categorical 54.
					for(int index$sample61$5 = 0; index$sample61$5 < noStates; index$sample61$5 += 1) {
						int distributionTempVariable$var55$7 = index$sample61$5;
						
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample61Value6 = (1.0 * distribution$sample61[((i$var50 - 0) / 1)][index$sample61$5]);
						int traceTempVariable$var71$8_1 = distributionTempVariable$var55$7;
						if((i$var50 == i$var60)) {
							if((0 == (j$var66 - 1))) {
								for(int var24 = 0; var24 < noStates; var24 += 1) {
									if((var24 == traceTempVariable$var71$8_1))
										// variable marginalization
										cv$noStates = Math.max(cv$noStates, noStates);
								}
							}
						}
					}
				}
			}
		}
		
		// Enumerating the possible arguments for Categorical 73.
		if((index$i$2 == i$var60)) {
			if((index$j$1 == (j$var66 - 1))) {
				for(int var24 = 0; var24 < noStates; var24 += 1) {
					if((var24 == st[i$var60][(j$var66 - 1)]))
						// variable marginalization
						cv$noStates = Math.max(cv$noStates, noStates);
				}
			}
		}
		if(fixedFlag$sample81) {
			for(int index$i$12_1 = 0; index$i$12_1 < samples; index$i$12_1 += 1) {
				for(int index$j$12_2 = 1; index$j$12_2 < length$eventsMeasured[index$i$12_1]; index$j$12_2 += 1) {
					if((index$i$12_1 == i$var60)) {
						if((index$j$12_2 == (j$var66 - 1))) {
							for(int var24 = 0; var24 < noStates; var24 += 1) {
								if((var24 == st[i$var60][(j$var66 - 1)]))
									// variable marginalization
									cv$noStates = Math.max(cv$noStates, noStates);
							}
						}
					}
				}
			}
		} else {
			for(int index$i$13 = 0; index$i$13 < samples; index$i$13 += 1) {
				for(int index$j$14 = 1; index$j$14 < length$eventsMeasured[index$i$13]; index$j$14 += 1) {
					if(!((index$i$13 == index$i$2) && (index$j$14 == index$j$1))) {
						// Enumerating the possible outputs of Categorical 73.
						for(int index$sample81$15 = 0; index$sample81$15 < noStates; index$sample81$15 += 1) {
							int distributionTempVariable$var74$17 = index$sample81$15;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample81Value16 = (1.0 * distribution$sample81[((index$i$13 - 0) / 1)][((index$j$14 - 1) / 1)][index$sample81$15]);
							int traceTempVariable$var71$18_1 = distributionTempVariable$var74$17;
							if((index$i$13 == i$var60)) {
								if((index$j$14 == (j$var66 - 1))) {
									for(int var24 = 0; var24 < noStates; var24 += 1) {
										if((var24 == traceTempVariable$var71$18_1))
											// variable marginalization
											cv$noStates = Math.max(cv$noStates, noStates);
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var74$stateProbabilityGlobal[threadID$cv$i$var60];
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 73 creating
			// sample task 81.
			// Copy of index so that its values can be safely substituted
			int index$j$22 = j$var66;
			
			// Copy of index so that its values can be safely substituted
			int index$i$23 = i$var60;
			
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
			
			// Enumerating the possible arguments for Categorical 73.
			if(fixedFlag$sample61) {
				for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
					if((i$var50 == i$var60)) {
						if((0 == (j$var66 - 1))) {
							for(int var24 = 0; var24 < noStates; var24 += 1) {
								if((var24 == st[i$var60][(j$var66 - 1)])) {
									// Record the reached probability density.
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
									double[] cv$temp$0$var72;
									{
										// Constructing a random variable input for use later.
										double[] var72 = m[st[i$var60][(j$var66 - 1)]];
										cv$temp$0$var72 = var72;
									}
									
									// An accumulator to allow the value for each distribution to be constructed before
									// it is added to the index probabilities.
									double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var72.length))?Math.log(cv$temp$0$var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
									
									// Processing random variable 73.
									{
										// Looking for a path between Sample 81 and consumer Categorical 73.
										{
											int traceTempVariable$var71$41_1 = cv$currentValue;
										}
									}
									
									// Processing random variable 93.
									{
										// Looking for a path between Sample 81 and consumer Categorical 93.
										{
											int traceTempVariable$var91$45_1 = cv$currentValue;
											for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
												if((i$var60 == i$var80)) {
													for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
														if((j$var66 == j$var88)) {
															// Processing sample task 103 of consumer random variable null.
															{
																// Set an accumulator to sum the probabilities for each possible configuration of
																// inputs.
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																
																// Set an accumulator to record the consumer distributions not seen. Initially set
																// to 1 as seen values will be deducted from this value.
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	// Enumerating the possible arguments for the variable Categorical 93 which is consuming
																	// the output of Sample task 81.
																	for(int var31 = 0; var31 < noStates; var31 += 1) {
																		if((var31 == traceTempVariable$var91$45_1)) {
																			{
																				{
																					double[] cv$temp$4$var92;
																					{
																						// Constructing a random variable input for use later.
																						double[] var92 = bias[traceTempVariable$var91$45_1];
																						cv$temp$4$var92 = var92;
																					}
																					
																					// Record the probability of sample task 103 generating output with current configuration.
																					if(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$4$var92.length))?Math.log(cv$temp$4$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$4$var92.length))?Math.log(cv$temp$4$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						// If the second value is -infinity.
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$4$var92.length))?Math.log(cv$temp$4$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$4$var92.length))?Math.log(cv$temp$4$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$4$var92.length))?Math.log(cv$temp$4$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)));
																					}
																					
																					// Recorded the probability of reaching sample task 103 with the current configuration.
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
			} else {
				for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
					if(true) {
						// Enumerating the possible outputs of Categorical 54.
						for(int index$sample61$26 = 0; index$sample61$26 < noStates; index$sample61$26 += 1) {
							int distributionTempVariable$var55$28 = index$sample61$26;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample61Value27 = (1.0 * distribution$sample61[((i$var50 - 0) / 1)][index$sample61$26]);
							int traceTempVariable$var71$29_1 = distributionTempVariable$var55$28;
							if((i$var50 == i$var60)) {
								if((0 == (j$var66 - 1))) {
									for(int var24 = 0; var24 < noStates; var24 += 1) {
										if((var24 == traceTempVariable$var71$29_1)) {
											// Record the reached probability density.
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample61Value27);
											double[] cv$temp$1$var72;
											{
												// Constructing a random variable input for use later.
												double[] var72 = m[traceTempVariable$var71$29_1];
												cv$temp$1$var72 = var72;
											}
											
											// An accumulator to allow the value for each distribution to be constructed before
											// it is added to the index probabilities.
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample61Value27) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
											
											// Processing random variable 73.
											{
												// Looking for a path between Sample 81 and consumer Categorical 73.
												{
													int traceTempVariable$var71$42_1 = cv$currentValue;
												}
											}
											
											// Processing random variable 93.
											{
												// Looking for a path between Sample 81 and consumer Categorical 93.
												{
													int traceTempVariable$var91$46_1 = cv$currentValue;
													for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
														if((i$var60 == i$var80)) {
															for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
																if((j$var66 == j$var88)) {
																	// Processing sample task 103 of consumer random variable null.
																	{
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Categorical 93 which is consuming
																			// the output of Sample task 81.
																			for(int var31 = 0; var31 < noStates; var31 += 1) {
																				if((var31 == traceTempVariable$var91$46_1)) {
																					{
																						{
																							double[] cv$temp$5$var92;
																							{
																								// Constructing a random variable input for use later.
																								double[] var92 = bias[traceTempVariable$var91$46_1];
																								cv$temp$5$var92 = var92;
																							}
																							
																							// Record the probability of sample task 103 generating output with current configuration.
																							if(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$5$var92.length))?Math.log(cv$temp$5$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$5$var92.length))?Math.log(cv$temp$5$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$5$var92.length))?Math.log(cv$temp$5$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$5$var92.length))?Math.log(cv$temp$5$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$5$var92.length))?Math.log(cv$temp$5$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)));
																							}
																							
																							// Recorded the probability of reaching sample task 103 with the current configuration.
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
			
			// Enumerating the possible arguments for Categorical 73.
			int traceTempVariable$var71$32_1 = cv$currentValue;
			if((index$i$23 == i$var60)) {
				if((index$j$22 == (j$var66 - 1))) {
					for(int var24 = 0; var24 < noStates; var24 += 1) {
						if((var24 == traceTempVariable$var71$32_1)) {
							// Record the reached probability density.
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$2$var72;
							{
								// Constructing a random variable input for use later.
								double[] var72 = m[traceTempVariable$var71$32_1];
								cv$temp$2$var72 = var72;
							}
							
							// An accumulator to allow the value for each distribution to be constructed before
							// it is added to the index probabilities.
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var72.length))?Math.log(cv$temp$2$var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
							
							// Processing random variable 73.
							{
								// Looking for a path between Sample 81 and consumer Categorical 73.
								{
									int traceTempVariable$var71$43_1 = cv$currentValue;
								}
							}
							
							// Processing random variable 93.
							{
								// Looking for a path between Sample 81 and consumer Categorical 93.
								{
									int traceTempVariable$var91$47_1 = cv$currentValue;
									for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
										if((i$var60 == i$var80)) {
											for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
												if((j$var66 == j$var88)) {
													// Processing sample task 103 of consumer random variable null.
													{
														// Set an accumulator to sum the probabilities for each possible configuration of
														// inputs.
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														
														// Set an accumulator to record the consumer distributions not seen. Initially set
														// to 1 as seen values will be deducted from this value.
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															// Enumerating the possible arguments for the variable Categorical 93 which is consuming
															// the output of Sample task 81.
															for(int var31 = 0; var31 < noStates; var31 += 1) {
																if((var31 == traceTempVariable$var91$47_1)) {
																	{
																		{
																			double[] cv$temp$6$var92;
																			{
																				// Constructing a random variable input for use later.
																				double[] var92 = bias[traceTempVariable$var91$47_1];
																				cv$temp$6$var92 = var92;
																			}
																			
																			// Record the probability of sample task 103 generating output with current configuration.
																			if(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$6$var92.length))?Math.log(cv$temp$6$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$6$var92.length))?Math.log(cv$temp$6$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$6$var92.length))?Math.log(cv$temp$6$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$6$var92.length))?Math.log(cv$temp$6$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$6$var92.length))?Math.log(cv$temp$6$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)));
																			}
																			
																			// Recorded the probability of reaching sample task 103 with the current configuration.
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
			for(int index$i$33 = 0; index$i$33 < samples; index$i$33 += 1) {
				for(int index$j$34 = 1; index$j$34 < length$eventsMeasured[index$i$33]; index$j$34 += 1) {
					if(!((index$i$33 == index$i$23) && (index$j$34 == index$j$22))) {
						// Enumerating the possible outputs of Categorical 73.
						for(int index$sample81$35 = 0; index$sample81$35 < noStates; index$sample81$35 += 1) {
							int distributionTempVariable$var74$37 = index$sample81$35;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample81Value36 = (1.0 * distribution$sample81[((index$i$33 - 0) / 1)][((index$j$34 - 1) / 1)][index$sample81$35]);
							int traceTempVariable$var71$38_1 = distributionTempVariable$var74$37;
							if((index$i$33 == i$var60)) {
								if((index$j$34 == (j$var66 - 1))) {
									for(int var24 = 0; var24 < noStates; var24 += 1) {
										if((var24 == traceTempVariable$var71$38_1)) {
											// Record the reached probability density.
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample81Value36);
											double[] cv$temp$3$var72;
											{
												// Constructing a random variable input for use later.
												double[] var72 = m[traceTempVariable$var71$38_1];
												cv$temp$3$var72 = var72;
											}
											
											// An accumulator to allow the value for each distribution to be constructed before
											// it is added to the index probabilities.
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample81Value36) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$var72.length))?Math.log(cv$temp$3$var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
											
											// Processing random variable 73.
											{
												// Looking for a path between Sample 81 and consumer Categorical 73.
												{
													int traceTempVariable$var71$44_1 = cv$currentValue;
												}
											}
											
											// Processing random variable 93.
											{
												// Looking for a path between Sample 81 and consumer Categorical 93.
												{
													int traceTempVariable$var91$48_1 = cv$currentValue;
													for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
														if((i$var60 == i$var80)) {
															for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
																if((j$var66 == j$var88)) {
																	// Processing sample task 103 of consumer random variable null.
																	{
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Categorical 93 which is consuming
																			// the output of Sample task 81.
																			for(int var31 = 0; var31 < noStates; var31 += 1) {
																				if((var31 == traceTempVariable$var91$48_1)) {
																					{
																						{
																							double[] cv$temp$7$var92;
																							{
																								// Constructing a random variable input for use later.
																								double[] var92 = bias[traceTempVariable$var91$48_1];
																								cv$temp$7$var92 = var92;
																							}
																							
																							// Record the probability of sample task 103 generating output with current configuration.
																							if(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$7$var92.length))?Math.log(cv$temp$7$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$7$var92.length))?Math.log(cv$temp$7$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$7$var92.length))?Math.log(cv$temp$7$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$7$var92.length))?Math.log(cv$temp$7$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$7$var92.length))?Math.log(cv$temp$7$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)));
																							}
																							
																							// Recorded the probability of reaching sample task 103 with the current configuration.
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
			
			// Processing random variable 73.
			{
				// Looking for a path between Sample 81 and consumer Categorical 73.
				{
					int traceTempVariable$var71$61_1 = cv$currentValue;
					for(int index$i$61_2 = 0; index$i$61_2 < samples; index$i$61_2 += 1) {
						if((i$var60 == index$i$61_2)) {
							for(int index$j$61_3 = 1; index$j$61_3 < length$eventsMeasured[index$i$61_2]; index$j$61_3 += 1) {
								if((j$var66 == (index$j$61_3 - 1))) {
									// Processing sample task 81 of consumer random variable null.
									{
										// Copy of index so that its values can be safely substituted
										int index$j$63 = index$j$61_3;
										
										// Copy of index so that its values can be safely substituted
										int index$i$64 = index$i$61_2;
										
										// A local array to hold the accumulated distributions of the sample tasks for each
										// configuration of distributions.
										double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var73[threadID$cv$i$var60];
										
										// Zero all the elements in the distribution accumulator
										for(int cv$i = 0; cv$i < noStates; cv$i += 1)
											cv$accumulatedConsumerDistributions[cv$i] = 0.0;
										
										// Zero an accumulator to track the probabilities reached.
										double cv$reachedDistributionProbability = 0.0;
										
										// Enumerating the possible arguments for the variable Categorical 73 which is consuming
										// the output of Sample task 81.
										for(int var24 = 0; var24 < noStates; var24 += 1) {
											if((var24 == traceTempVariable$var71$61_1)) {
												{
													// Declare and zero an accumulator for tracking the reached source probability space.
													double scopeVariable$reachedSourceProbability = 0.0;
													
													// Enumerating the possible arguments for Categorical 73.
													if(fixedFlag$sample61) {
														for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
															if((i$var50 == i$var60)) {
																if((0 == (j$var66 - 1))) {
																	for(int index$var24$72_1 = 0; index$var24$72_1 < noStates; index$var24$72_1 += 1) {
																		if((index$var24$72_1 == st[i$var60][(j$var66 - 1)]))
																			// Add the probability of this argument configuration.
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																}
															}
														}
													} else {
														for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
															if(true) {
																// Enumerating the possible outputs of Categorical 54.
																for(int index$sample61$68 = 0; index$sample61$68 < noStates; index$sample61$68 += 1) {
																	int distributionTempVariable$var55$70 = index$sample61$68;
																	
																	// Update the probability of sampling this value from the distribution value.
																	double cv$probabilitySample61Value69 = (1.0 * distribution$sample61[((i$var50 - 0) / 1)][index$sample61$68]);
																	int traceTempVariable$var71$71_1 = distributionTempVariable$var55$70;
																	if((i$var50 == i$var60)) {
																		if((0 == (j$var66 - 1))) {
																			for(int index$var24$73_1 = 0; index$var24$73_1 < noStates; index$var24$73_1 += 1) {
																				if((index$var24$73_1 == traceTempVariable$var71$71_1))
																					// Add the probability of this argument configuration.
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample61Value69);
																			}
																		}
																	}
																}
															}
														}
													}
													
													// Enumerating the possible arguments for Categorical 73.
													int traceTempVariable$var71$74_1 = cv$currentValue;
													if((index$i$23 == i$var60)) {
														if((index$j$22 == (j$var66 - 1))) {
															for(int index$var24$81_1 = 0; index$var24$81_1 < noStates; index$var24$81_1 += 1) {
																if((index$var24$81_1 == traceTempVariable$var71$74_1))
																	// Add the probability of this argument configuration.
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
														}
													}
													for(int index$i$75 = 0; index$i$75 < samples; index$i$75 += 1) {
														for(int index$j$76 = 1; index$j$76 < length$eventsMeasured[index$i$75]; index$j$76 += 1) {
															if((!((index$i$75 == index$i$23) && (index$j$76 == index$j$22)) && !((index$i$75 == index$i$64) && (index$j$76 == index$j$63)))) {
																// Enumerating the possible outputs of Categorical 73.
																for(int index$sample81$77 = 0; index$sample81$77 < noStates; index$sample81$77 += 1) {
																	int distributionTempVariable$var74$79 = index$sample81$77;
																	
																	// Update the probability of sampling this value from the distribution value.
																	double cv$probabilitySample81Value78 = (1.0 * distribution$sample81[((index$i$75 - 0) / 1)][((index$j$76 - 1) / 1)][index$sample81$77]);
																	int traceTempVariable$var71$80_1 = distributionTempVariable$var74$79;
																	if((index$i$75 == i$var60)) {
																		if((index$j$76 == (j$var66 - 1))) {
																			for(int index$var24$82_1 = 0; index$var24$82_1 < noStates; index$var24$82_1 += 1) {
																				if((index$var24$82_1 == traceTempVariable$var71$80_1))
																					// Add the probability of this argument configuration.
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample81Value78);
																			}
																		}
																	}
																}
															}
														}
													}
													double[] cv$temp$8$var72;
													{
														// Constructing a random variable input for use later.
														double[] var72 = m[traceTempVariable$var71$61_1];
														cv$temp$8$var72 = var72;
													}
													
													// The probability of reaching the consumer with this set of consumer arguments
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													
													// Record the reached distribution.
													cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
													
													// Add the current distribution to the distribution accumulator.
													DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$8$var72);
												}
											}
										}
										
										// A local copy of the samples' distribution.
										double[] cv$sampleDistribution = distribution$sample81[((index$i$61_2 - 0) / 1)][((index$j$61_3 - 1) / 1)];
										
										// The overlap of the distributions so far.
										double cv$overlap = 0.0;
										
										// Calculate the overlap for each element in the distribution
										for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
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
		double[] cv$localProbability = distribution$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)];
		
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
		// Constructor for cv$var25$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			for(int var24 = 0; var24 < noStates; var24 += 1)
				cv$max = Math.max(cv$max, noStates);
			
			// Allocation of cv$var25$countGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var25$countGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var25$countGlobal[cv$index] = new double[cv$max];
			}
		}
		
		// Constructor for cv$var32$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			for(int var31 = 0; var31 < noStates; var31 += 1)
				cv$max = Math.max(cv$max, noEvents);
			
			// Allocation of cv$var32$countGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var32$countGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var32$countGlobal[cv$index] = new double[cv$max];
			}
		}
		
		// Constructor for cv$var45$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			cv$max = Math.max(cv$max, noStates);
			
			// Allocation of cv$var45$countGlobal for single threaded execution
			cv$var45$countGlobal = new double[cv$max];
		}
		
		// Constructor for cv$distributionAccumulator$var54
		{
			// Variable to record the maximum value of Task Get 59. Initially set to the value
			// of putTask 27.
			int cv$var26$max = noStates;
			
			// Allocation of cv$distributionAccumulator$var54 for single threaded execution
			cv$distributionAccumulator$var54 = new double[cv$var26$max];
		}
		
		// Constructor for cv$var47$stateProbabilityGlobal
		{
			// Allocation of cv$var47$stateProbabilityGlobal for single threaded execution
			cv$var47$stateProbabilityGlobal = new double[noStates];
		}
		
		// Constructor for cv$distributionAccumulator$var73
		{
			// Variable to record the maximum value of Task Get 79. Initially set to the value
			// of putTask 27.
			int cv$var26$max = noStates;
			
			// Allocation of cv$distributionAccumulator$var73 for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$distributionAccumulator$var73 = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$distributionAccumulator$var73[cv$index] = new double[cv$var26$max];
			}
		}
		
		// Constructor for cv$var55$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 59. Initially set to the value
			// of putTask 27.
			int cv$var26$max = noStates;
			
			// Allocation of cv$var55$stateProbabilityGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var55$stateProbabilityGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var55$stateProbabilityGlobal[cv$index] = new double[cv$var26$max];
			}
		}
		
		// Constructor for cv$var74$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 79. Initially set to the value
			// of putTask 27.
			int cv$var26$max = noStates;
			
			// Allocation of cv$var74$stateProbabilityGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var74$stateProbabilityGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var74$stateProbabilityGlobal[cv$index] = new double[cv$var26$max];
			}
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for v
		{
			v = new double[noStates];
		}
		
		// Constructor for v2
		{
			v2 = new double[noEvents];
		}
		
		// If m has not been set already allocate space.
		if(!setFlag$m) {
			// Constructor for m
			{
				m = new double[noStates][];
				for(int var24 = 0; var24 < noStates; var24 += 1)
					m[var24] = new double[noStates];
			}
		}
		
		// If bias has not been set already allocate space.
		if(!setFlag$bias) {
			// Constructor for bias
			{
				bias = new double[noStates][];
				for(int var31 = 0; var31 < noStates; var31 += 1)
					bias[var31] = new double[noEvents];
			}
		}
		
		// If st has not been set already allocate space.
		if(!setFlag$st) {
			// Constructor for st
			{
				st = new int[length$eventsMeasured.length][];
				for(int i$var38 = 0; i$var38 < length$eventsMeasured.length; i$var38 += 1)
					st[i$var38] = new int[length$eventsMeasured[i$var38]];
			}
		}
		
		// If weights has not been set already allocate space.
		if(!setFlag$weights) {
			// Constructor for weights
			{
				weights = new double[noStates];
			}
		}
		
		// If events has not been set already allocate space.
		if(!setFlag$events) {
			// Constructor for events
			{
				events = new int[length$eventsMeasured.length][];
				for(int i$var80 = 0; i$var80 < length$eventsMeasured.length; i$var80 += 1)
					events[i$var80] = new int[length$eventsMeasured[i$var80]];
			}
		}
		
		// Constructor for distribution$sample81
		{
			distribution$sample81 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][][];
			for(int i$var60 = 0; i$var60 < length$eventsMeasured.length; i$var60 += 1) {
				double[][] subarray$0 = new double[((((length$eventsMeasured[i$var60] - 1) - 1) / 1) + 1)][];
				distribution$sample81[((i$var60 - 0) / 1)] = subarray$0;
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
					subarray$0[((j$var66 - 1) / 1)] = new double[noStates];
			}
		}
		
		// Constructor for distribution$sample61
		{
			distribution$sample61 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var50 = 0; i$var50 < length$eventsMeasured.length; i$var50 += 1)
				distribution$sample61[((i$var50 - 0) / 1)] = new double[noStates];
		}
		
		// Constructor for logProbability$var54
		{
			logProbability$var54 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample61
		{
			logProbability$sample61 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$var73
		{
			logProbability$var73 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var60 = 0; i$var60 < length$eventsMeasured.length; i$var60 += 1)
				logProbability$var73[((i$var60 - 0) / 1)] = new double[((((length$eventsMeasured[i$var60] - 1) - 1) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample81
		{
			logProbability$sample81 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var60 = 0; i$var60 < length$eventsMeasured.length; i$var60 += 1)
				logProbability$sample81[((i$var60 - 0) / 1)] = new double[((((length$eventsMeasured[i$var60] - 1) - 1) / 1) + 1)];
		}
		
		// Constructor for logProbability$var93
		{
			logProbability$var93 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var80 = 0; i$var80 < length$eventsMeasured.length; i$var80 += 1)
				logProbability$var93[((i$var80 - 0) / 1)] = new double[((((length$eventsMeasured[i$var80] - 1) - 1) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample103
		{
			logProbability$sample103 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var80 = 0; i$var80 < length$eventsMeasured.length; i$var80 += 1)
				logProbability$sample103[((i$var80 - 0) / 1)] = new double[((((length$eventsMeasured[i$var80] - 1) - 1) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
						double[] var25 = m[var24];
						if(!fixedFlag$sample26)
							DistributionSampling.sampleDirichlet(RNG$1, v, var25);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1) {
						double[] var32 = bias[var31];
						if(!fixedFlag$sample34)
							DistributionSampling.sampleDirichlet(RNG$1, v2, var32);
					}
			}
		);
		if(!fixedFlag$sample51)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample53)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var50, int forEnd$i$var50, int threadID$i$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var50 = forStart$i$var50; i$var50 < forEnd$i$var50; i$var50 += 1) {
						int[] var51 = st[i$var50];
						if(!fixedFlag$sample61)
							var51[0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState]);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var60, int forEnd$i$var60, int threadID$i$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var60 = forStart$i$var60; i$var60 < forEnd$i$var60; i$var60 += 1) {
						int[] var67 = st[i$var60];
						for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
							if(!fixedFlag$sample81)
								var67[j$var66] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var60][(j$var66 - 1)]]);
						}
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$index$i$var80, int forEnd$index$i$var80, int threadID$index$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var80 = forStart$index$i$var80; index$i$var80 < forEnd$index$i$var80; index$i$var80 += 1) {
						int i$var80 = index$i$var80;
						int[] var89 = events[i$var80];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 1, length$eventsMeasured[i$var80], 1,
							(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1) {
										if(!fixedFlag$sample103)
											var89[j$var88] = (DistributionSampling.sampleCategorical(RNG$2, bias[st[i$var80][j$var88]]) + 1);
									}
							}
						);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
						double[] var25 = m[var24];
						if(!fixedFlag$sample26)
							DistributionSampling.sampleDirichlet(RNG$1, v, var25);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1) {
						double[] var32 = bias[var31];
						if(!fixedFlag$sample34)
							DistributionSampling.sampleDirichlet(RNG$1, v2, var32);
					}
			}
		);
		if(!fixedFlag$sample51)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample53)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var50, int forEnd$i$var50, int threadID$i$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var50 = forStart$i$var50; i$var50 < forEnd$i$var50; i$var50 += 1) {
						// Create local copy of variable probabilities.
						double[] cv$distribution$sample61 = distribution$sample61[((i$var50 - 0) / 1)];
						double[] var53 = m[initialState];
						for(int index$var54 = 0; index$var54 < noStates; index$var54 += 1) {
							// Probability for this value
							double cv$value = (((0.0 <= index$var54) && (index$var54 < var53.length))?var53[index$var54]:0.0);
							if(!fixedFlag$sample61)
								// Save the probability of each value
								cv$distribution$sample61[index$var54] = cv$value;
						}
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var60, int forEnd$i$var60, int threadID$i$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var60 = forStart$i$var60; i$var60 < forEnd$i$var60; i$var60 += 1) {
						for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
							// Create local copy of variable probabilities.
							double[] cv$distribution$sample81 = distribution$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)];
							for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1) {
								if(!fixedFlag$sample81)
									// Zero the probability of each value
									cv$distribution$sample81[index$var73] = 0.0;
							}
							
							// Iterate through possible values for var73's arguments.
							// 
							// Enumerating the possible arguments for Categorical 73.
							if(fixedFlag$sample61) {
								for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
									if((i$var50 == i$var60)) {
										if((0 == (j$var66 - 1))) {
											for(int var24 = 0; var24 < noStates; var24 += 1) {
												if((var24 == st[i$var60][(j$var66 - 1)])) {
													{
														if(!fixedFlag$sample81) {
															double[] var72 = m[st[i$var60][(j$var66 - 1)]];
															for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
																// Save the probability of each value
																cv$distribution$sample81[index$var73] = (cv$distribution$sample81[index$var73] + (1.0 * (((0.0 <= index$var73) && (index$var73 < var72.length))?var72[index$var73]:0.0)));
														}
													}
												}
											}
										}
									}
								}
							} else {
								for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 54.
										for(int index$sample61$3 = 0; index$sample61$3 < noStates; index$sample61$3 += 1) {
											int distributionTempVariable$var55$5 = index$sample61$3;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample61Value4 = (1.0 * distribution$sample61[((i$var50 - 0) / 1)][index$sample61$3]);
											int traceTempVariable$var71$6_1 = distributionTempVariable$var55$5;
											if((i$var50 == i$var60)) {
												if((0 == (j$var66 - 1))) {
													for(int var24 = 0; var24 < noStates; var24 += 1) {
														if((var24 == traceTempVariable$var71$6_1)) {
															{
																if(!fixedFlag$sample81) {
																	double[] var72 = m[traceTempVariable$var71$6_1];
																	for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
																		// Save the probability of each value
																		cv$distribution$sample81[index$var73] = (cv$distribution$sample81[index$var73] + (cv$probabilitySample61Value4 * (((0.0 <= index$var73) && (index$var73 < var72.length))?var72[index$var73]:0.0)));
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							
							// Enumerating the possible arguments for Categorical 73.
							if(fixedFlag$sample81) {
								for(int index$i$9_1 = 0; index$i$9_1 < samples; index$i$9_1 += 1) {
									for(int index$j$9_2 = 1; index$j$9_2 < length$eventsMeasured[index$i$9_1]; index$j$9_2 += 1) {
										if((index$i$9_1 == i$var60)) {
											if((index$j$9_2 == (j$var66 - 1))) {
												for(int var24 = 0; var24 < noStates; var24 += 1) {
													if((var24 == st[i$var60][(j$var66 - 1)])) {
														{
															if(!fixedFlag$sample81) {
																double[] var72 = m[st[i$var60][(j$var66 - 1)]];
																for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
																	// Save the probability of each value
																	cv$distribution$sample81[index$var73] = (cv$distribution$sample81[index$var73] + (1.0 * (((0.0 <= index$var73) && (index$var73 < var72.length))?var72[index$var73]:0.0)));
															}
														}
													}
												}
											}
										}
									}
								}
							} else {
								for(int index$i$10 = 0; index$i$10 < samples; index$i$10 += 1) {
									for(int index$j$11 = 1; index$j$11 < length$eventsMeasured[index$i$10]; index$j$11 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 73.
											for(int index$sample81$12 = 0; index$sample81$12 < noStates; index$sample81$12 += 1) {
												int distributionTempVariable$var74$14 = index$sample81$12;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample81Value13 = (1.0 * distribution$sample81[((index$i$10 - 0) / 1)][((index$j$11 - 1) / 1)][index$sample81$12]);
												int traceTempVariable$var71$15_1 = distributionTempVariable$var74$14;
												if((index$i$10 == i$var60)) {
													if((index$j$11 == (j$var66 - 1))) {
														for(int var24 = 0; var24 < noStates; var24 += 1) {
															if((var24 == traceTempVariable$var71$15_1)) {
																{
																	if(!fixedFlag$sample81) {
																		double[] var72 = m[traceTempVariable$var71$15_1];
																		for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
																			// Save the probability of each value
																			cv$distribution$sample81[index$var73] = (cv$distribution$sample81[index$var73] + (cv$probabilitySample81Value13 * (((0.0 <= index$var73) && (index$var73 < var72.length))?var72[index$var73]:0.0)));
																	}
																}
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
							double cv$var73$sum = 0.0;
							for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1) {
								if(!fixedFlag$sample81)
									// sum the probability of each value
									cv$var73$sum = (cv$var73$sum + cv$distribution$sample81[index$var73]);
							}
							for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1) {
								if(!fixedFlag$sample81)
									// Normalise the probability of each value
									cv$distribution$sample81[index$var73] = (cv$distribution$sample81[index$var73] / cv$var73$sum);
							}
						}
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
						double[] var25 = m[var24];
						if(!fixedFlag$sample26)
							DistributionSampling.sampleDirichlet(RNG$1, v, var25);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1) {
						double[] var32 = bias[var31];
						if(!fixedFlag$sample34)
							DistributionSampling.sampleDirichlet(RNG$1, v2, var32);
					}
			}
		);
		if(!fixedFlag$sample51)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample53)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var50, int forEnd$i$var50, int threadID$i$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var50 = forStart$i$var50; i$var50 < forEnd$i$var50; i$var50 += 1) {
						int[] var51 = st[i$var50];
						if(!fixedFlag$sample61)
							var51[0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState]);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var60, int forEnd$i$var60, int threadID$i$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var60 = forStart$i$var60; i$var60 < forEnd$i$var60; i$var60 += 1) {
						int[] var67 = st[i$var60];
						for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
							if(!fixedFlag$sample81)
								var67[j$var66] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var60][(j$var66 - 1)]]);
						}
					}
			}
		);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
							if(!fixedFlag$sample26)
								sample26(var24, threadID$var24, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1) {
							if(!fixedFlag$sample34)
								sample34(var31, threadID$var31, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample51)
				sample51();
			if(!fixedFlag$sample53)
				sample53();
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var50, int forEnd$i$var50, int threadID$i$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var50 = forStart$i$var50; i$var50 < forEnd$i$var50; i$var50 += 1) {
							if(!fixedFlag$sample61)
								sample61(i$var50, threadID$i$var50, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var60, int forEnd$i$var60, int threadID$i$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var60 = forStart$i$var60; i$var60 < forEnd$i$var60; i$var60 += 1) {
							for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
								if(!fixedFlag$sample81)
									sample81(i$var60, j$var66, threadID$i$var60, RNG$1);
							}
						}
				}
			);
		}
		// Infer the samples in reverse chronological order.
		else {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var60, int forEnd$i$var60, int threadID$i$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var60 = forStart$i$var60; i$var60 < forEnd$i$var60; i$var60 += 1) {
							for(int j$var66 = (length$eventsMeasured[i$var60] - ((((length$eventsMeasured[i$var60] - 1) - 1) % 1) + 1)); j$var66 >= ((1 - 1) + 1); j$var66 -= 1) {
								if(!fixedFlag$sample81)
									sample81(i$var60, j$var66, threadID$i$var60, RNG$1);
							}
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var50, int forEnd$i$var50, int threadID$i$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var50 = forStart$i$var50; i$var50 < forEnd$i$var50; i$var50 += 1) {
							if(!fixedFlag$sample61)
								sample61(i$var50, threadID$i$var50, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample53)
				sample53();
			if(!fixedFlag$sample51)
				sample51();
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1) {
							if(!fixedFlag$sample34)
								sample34(var31, threadID$var31, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
							if(!fixedFlag$sample26)
								sample26(var24, threadID$var24, RNG$1);
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
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var11, int forEnd$var11, int threadID$var11, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var11 = forStart$var11; var11 < forEnd$var11; var11 += 1)
						v[var11] = 0.1;
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noEvents, 1,
			(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
						v2[var17] = 0.1;
			}
		);
		samples = length$eventsMeasured.length;
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
		logProbability$var20 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample26)
			logProbability$var25 = 0.0;
		logProbability$var27 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample34)
			logProbability$var32 = 0.0;
		logProbability$var44 = 0.0;
		if(!fixedProbFlag$sample51)
			logProbability$weights = 0.0;
		logProbability$var46 = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$initialState = 0.0;
		for(int i$var50 = 0; i$var50 < samples; i$var50 += 1)
			logProbability$var54[((i$var50 - 0) / 1)] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample61) {
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1)
				logProbability$sample61[((i$var50 - 0) / 1)] = 0.0;
		}
		for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
			for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
				logProbability$var73[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)] = 0.0;
		}
		if(!fixedProbFlag$sample81) {
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
					logProbability$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)] = 0.0;
			}
		}
		for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
			for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1)
				logProbability$var93[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)] = 0.0;
		}
		logProbability$events = 0.0;
		if(!fixedProbFlag$sample103) {
			for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
				for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1)
					logProbability$sample103[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)] = 0.0;
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
		if(fixedFlag$sample26)
			logProbabilityValue$sample26();
		if(fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(fixedFlag$sample51)
			logProbabilityValue$sample51();
		if(fixedFlag$sample53)
			logProbabilityValue$sample53();
		logProbabilityValue$sample103();
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
		logProbabilityValue$sample26();
		logProbabilityValue$sample34();
		logProbabilityValue$sample51();
		logProbabilityValue$sample53();
		logProbabilityDistribution$sample61();
		logProbabilityDistribution$sample81();
		logProbabilityDistribution$sample103();
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
		logProbabilityValue$sample26();
		logProbabilityValue$sample34();
		logProbabilityValue$sample51();
		logProbabilityValue$sample53();
		logProbabilityValue$sample61();
		logProbabilityValue$sample81();
		logProbabilityValue$sample103();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
						double[] var25 = m[var24];
						if(!fixedFlag$sample26)
							DistributionSampling.sampleDirichlet(RNG$1, v, var25);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1) {
						double[] var32 = bias[var31];
						if(!fixedFlag$sample34)
							DistributionSampling.sampleDirichlet(RNG$1, v2, var32);
					}
			}
		);
		if(!fixedFlag$sample51)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample53)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var50, int forEnd$i$var50, int threadID$i$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var50 = forStart$i$var50; i$var50 < forEnd$i$var50; i$var50 += 1) {
						int[] var51 = st[i$var50];
						if(!fixedFlag$sample61)
							var51[0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState]);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var60, int forEnd$i$var60, int threadID$i$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var60 = forStart$i$var60; i$var60 < forEnd$i$var60; i$var60 += 1) {
						int[] var67 = st[i$var60];
						for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
							if(!fixedFlag$sample81)
								var67[j$var66] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var60][(j$var66 - 1)]]);
						}
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
		int[][] cv$source1 = eventsMeasured;
		int[][] cv$target1 = events;
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
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMM_Mk2Dist(int[][] eventsMeasured, int noStates, int noEvents) {\n        \n        // Construct arrays describing the probability of a move from 1 state to another.\n        double[] v = new double[noStates] <~ 0.1;\n        double[] v2 = new double[noEvents] <~ 0.1;\n        double[][] m = dirichlet(v).sample(noStates);\n        \n        // Construct the bias for each webpage.\n        double[][] bias = dirichlet(v2).sample(noStates);\n\n        // Determine how many samples the model will need to produce.\n        int samples = eventsMeasured.length;\n        \n        // Allocate space for the state, i.e. which webpage we are going to trigger an event on.\n        int[][] st = new int[samples][];\n        for(int i:[0 .. samples)){\n            int streamLength = eventsMeasured[i].length;\n            st[i] = new int[streamLength];\n        }\n\n        // Set the initial state by sampling from a categorical with learnt weightings.\n        double[] weights = dirichlet(v).sample();\n        int initialState = categorical(weights).sample();\n        for(int i:[0..samples)) {\n            st[i][0] = categorical(m[initialState]).sampleDistribution();\n        }\n\n        //Determine the remaining states based on the previous state.\n        for(int i:[0 .. samples)){\n            int streamLength = eventsMeasured[i].length;\n            for(int j:[1..streamLength)){\n                st[i][j] = categorical(m[st[i][j-1]]).sampleDistribution();\n            }\n        }\n            \n        //Generate each event.\n        int[][] events = new int[samples][];\n        for(int i:[0 .. samples)) {\n            int streamLength = eventsMeasured[i].length;\n            events[i] = new int[streamLength];\n            for(int j:[1..streamLength)){\n                events[i][j] = categorical(bias[st[i][j]]).sample() + 1;\n            }\n        }\n\n        //Tie the values of the flips to the values we have measured.\n        events.observe(eventsMeasured);\n}\n";
	}
}