package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMM_Mk2Dist$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMM_Mk2Dist$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] bias;
	private double[][] cv$distributionAccumulator$var122;
	private double[] cv$distributionAccumulator$var91;
	private double[][] cv$var123$stateProbabilityGlobal;
	private double[][] cv$var42$countGlobal;
	private double[][] cv$var56$countGlobal;
	private double[] cv$var75$countGlobal;
	private double[] cv$var77$stateProbabilityGlobal;
	private double[][] cv$var92$stateProbabilityGlobal;
	private double[][][] distribution$sample126;
	private double[][] distribution$sample95;
	private int[][] events;
	private int[][] eventsMeasured;
	private boolean fixedFlag$sample126 = false;
	private boolean fixedFlag$sample159 = false;
	private boolean fixedFlag$sample42 = false;
	private boolean fixedFlag$sample57 = false;
	private boolean fixedFlag$sample78 = false;
	private boolean fixedFlag$sample80 = false;
	private boolean fixedFlag$sample95 = false;
	private boolean fixedProbFlag$sample126 = false;
	private boolean fixedProbFlag$sample159 = false;
	private boolean fixedProbFlag$sample42 = false;
	private boolean fixedProbFlag$sample57 = false;
	private boolean fixedProbFlag$sample78 = false;
	private boolean fixedProbFlag$sample80 = false;
	private boolean fixedProbFlag$sample95 = false;
	private int initialState;
	private int[] length$eventsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$events;
	private double logProbability$initialState;
	private double logProbability$m;
	private double[][] logProbability$sample126;
	private double[][] logProbability$sample159;
	private double[] logProbability$sample95;
	private double logProbability$st;
	private double[][] logProbability$var122;
	private double[][] logProbability$var154;
	private double logProbability$var30;
	private double logProbability$var42;
	private double logProbability$var44;
	private double logProbability$var56;
	private double logProbability$var74;
	private double logProbability$var76;
	private double[] logProbability$var91;
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
		
		// Unset the fixed probability flag for sample 57 as it depends on bias.
		fixedProbFlag$sample57 = false;
		
		// Unset the fixed probability flag for sample 159 as it depends on bias.
		fixedProbFlag$sample159 = false;
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
		
		// Unset the fixed probability flag for sample 159 as it depends on events.
		fixedProbFlag$sample159 = false;
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

	// Getter for fixedFlag$sample126.
	@Override
	public final boolean get$fixedFlag$sample126() {
		return fixedFlag$sample126;
	}

	// Setter for fixedFlag$sample126.
	@Override
	public final void set$fixedFlag$sample126(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample126 including if probabilities
		// need to be updated.
		fixedFlag$sample126 = cv$value;
		
		// Should the probability of sample 126 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample126 = (fixedFlag$sample126 && fixedProbFlag$sample126);
		
		// Should the probability of sample 159 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample159 = (fixedFlag$sample126 && fixedProbFlag$sample159);
	}

	// Getter for fixedFlag$sample159.
	@Override
	public final boolean get$fixedFlag$sample159() {
		return fixedFlag$sample159;
	}

	// Setter for fixedFlag$sample159.
	@Override
	public final void set$fixedFlag$sample159(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample159 including if probabilities
		// need to be updated.
		fixedFlag$sample159 = cv$value;
		
		// Should the probability of sample 159 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample159 = (fixedFlag$sample159 && fixedProbFlag$sample159);
	}

	// Getter for fixedFlag$sample42.
	@Override
	public final boolean get$fixedFlag$sample42() {
		return fixedFlag$sample42;
	}

	// Setter for fixedFlag$sample42.
	@Override
	public final void set$fixedFlag$sample42(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample42 including if probabilities
		// need to be updated.
		fixedFlag$sample42 = cv$value;
		
		// Should the probability of sample 42 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample42 = (fixedFlag$sample42 && fixedProbFlag$sample42);
		
		// Should the probability of sample 95 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample95 = (fixedFlag$sample42 && fixedProbFlag$sample95);
		
		// Should the probability of sample 126 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample126 = (fixedFlag$sample42 && fixedProbFlag$sample126);
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
		
		// Should the probability of sample 159 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample159 = (fixedFlag$sample57 && fixedProbFlag$sample159);
	}

	// Getter for fixedFlag$sample78.
	@Override
	public final boolean get$fixedFlag$sample78() {
		return fixedFlag$sample78;
	}

	// Setter for fixedFlag$sample78.
	@Override
	public final void set$fixedFlag$sample78(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample78 including if probabilities
		// need to be updated.
		fixedFlag$sample78 = cv$value;
		
		// Should the probability of sample 78 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample78 = (fixedFlag$sample78 && fixedProbFlag$sample78);
		
		// Should the probability of sample 80 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample80 = (fixedFlag$sample78 && fixedProbFlag$sample80);
	}

	// Getter for fixedFlag$sample80.
	@Override
	public final boolean get$fixedFlag$sample80() {
		return fixedFlag$sample80;
	}

	// Setter for fixedFlag$sample80.
	@Override
	public final void set$fixedFlag$sample80(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample80 including if probabilities
		// need to be updated.
		fixedFlag$sample80 = cv$value;
		
		// Should the probability of sample 80 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample80 = (fixedFlag$sample80 && fixedProbFlag$sample80);
		
		// Should the probability of sample 95 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample95 = (fixedFlag$sample80 && fixedProbFlag$sample95);
	}

	// Getter for fixedFlag$sample95.
	@Override
	public final boolean get$fixedFlag$sample95() {
		return fixedFlag$sample95;
	}

	// Setter for fixedFlag$sample95.
	@Override
	public final void set$fixedFlag$sample95(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample95 including if probabilities
		// need to be updated.
		fixedFlag$sample95 = cv$value;
		
		// Should the probability of sample 95 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample95 = (fixedFlag$sample95 && fixedProbFlag$sample95);
		
		// Should the probability of sample 126 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample126 = (fixedFlag$sample95 && fixedProbFlag$sample126);
		
		// Should the probability of sample 159 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample159 = (fixedFlag$sample95 && fixedProbFlag$sample159);
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
		
		// Unset the fixed probability flag for sample 80 as it depends on initialState.
		fixedProbFlag$sample80 = false;
		
		// Unset the fixed probability flag for sample 95 as it depends on initialState.
		fixedProbFlag$sample95 = false;
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
		
		// Unset the fixed probability flag for sample 42 as it depends on m.
		fixedProbFlag$sample42 = false;
		
		// Unset the fixed probability flag for sample 95 as it depends on m.
		fixedProbFlag$sample95 = false;
		
		// Unset the fixed probability flag for sample 126 as it depends on m.
		fixedProbFlag$sample126 = false;
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
		
		// Unset the fixed probability flag for sample 95 as it depends on st.
		fixedProbFlag$sample95 = false;
		
		// Unset the fixed probability flag for sample 126 as it depends on st.
		fixedProbFlag$sample126 = false;
		
		// Unset the fixed probability flag for sample 159 as it depends on st.
		fixedProbFlag$sample159 = false;
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
		
		// Unset the fixed probability flag for sample 78 as it depends on weights.
		fixedProbFlag$sample78 = false;
		
		// Unset the fixed probability flag for sample 80 as it depends on weights.
		fixedProbFlag$sample80 = false;
	}

	// Calculate the probability of the samples represented by sample126 using probability
	// distributions.
	private final void logProbabilityDistribution$sample126() {
		// Determine if we need to calculate the values for sample task 126 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample126) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample126) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
					for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
						// Accumulator for sample probabilities for a specific instance of the random variable.
						double cv$sampleAccumulator = 0.0;
						
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// Look for paths between the variable and the sample task 126 including any distribution
						// values.
						// 
						// Copy of index so that its values can be safely substituted
						int index$j$1 = j$var115;
						
						// Copy of index so that its values can be safely substituted
						int index$i$2 = i$var104;
						{
							// The sample value to calculate the probability of generating
							int cv$sampleValue = st[i$var104][j$var115];
							
							// Enumerating the possible arguments for Categorical 122.
							if(fixedFlag$sample95) {
								for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
									if((i$var87 == i$var104)) {
										if((0 == (j$var115 - 1))) {
											for(int var41 = 0; var41 < noStates; var41 += 1) {
												if((var41 == st[i$var104][(j$var115 - 1)])) {
													{
														double[] var121 = m[st[i$var104][(j$var115 - 1)]];
														
														// Store the value of the function call, so the function call is only made once.
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
														
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
								for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 91.
										for(int index$sample95$6 = 0; index$sample95$6 < noStates; index$sample95$6 += 1) {
											int distributionTempVariable$var92$8 = index$sample95$6;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample95Value7 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$6]);
											if((i$var87 == i$var104)) {
												if((0 == (j$var115 - 1))) {
													for(int var41 = 0; var41 < noStates; var41 += 1) {
														if((var41 == st[i$var104][(j$var115 - 1)])) {
															{
																double[] var121 = m[st[i$var104][(j$var115 - 1)]];
																
																// Store the value of the function call, so the function call is only made once.
																double cv$weightedProbability = (Math.log(cv$probabilitySample95Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																
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
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample95Value7);
															}
														}
													}
												}
											}
										}
									}
								}
							}
							
							// Enumerating the possible arguments for Categorical 122.
							if((index$i$2 == i$var104)) {
								if((index$j$1 == (j$var115 - 1))) {
									for(int var41 = 0; var41 < noStates; var41 += 1) {
										if((var41 == st[i$var104][(j$var115 - 1)])) {
											{
												double[] var121 = m[st[i$var104][(j$var115 - 1)]];
												
												// Store the value of the function call, so the function call is only made once.
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
												
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
							if(fixedFlag$sample126) {
								for(int index$i$13_1 = 0; index$i$13_1 < samples; index$i$13_1 += 1) {
									for(int index$j$13_2 = 1; index$j$13_2 < length$eventsMeasured[index$i$13_1]; index$j$13_2 += 1) {
										if((index$i$13_1 == i$var104)) {
											if((index$j$13_2 == (j$var115 - 1))) {
												for(int var41 = 0; var41 < noStates; var41 += 1) {
													if((var41 == st[i$var104][(j$var115 - 1)])) {
														{
															double[] var121 = m[st[i$var104][(j$var115 - 1)]];
															
															// Store the value of the function call, so the function call is only made once.
															double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
															
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
											// Enumerating the possible outputs of Categorical 122.
											for(int index$sample126$16 = 0; index$sample126$16 < noStates; index$sample126$16 += 1) {
												int distributionTempVariable$var123$18 = index$sample126$16;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample126Value17 = (1.0 * distribution$sample126[((index$i$14 - 0) / 1)][((index$j$15 - 1) / 1)][index$sample126$16]);
												if((index$i$14 == i$var104)) {
													if((index$j$15 == (j$var115 - 1))) {
														for(int var41 = 0; var41 < noStates; var41 += 1) {
															if((var41 == st[i$var104][(j$var115 - 1)])) {
																{
																	double[] var121 = m[st[i$var104][(j$var115 - 1)]];
																	
																	// Store the value of the function call, so the function call is only made once.
																	double cv$weightedProbability = (Math.log(cv$probabilitySample126Value17) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																	
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
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample126Value17);
																}
															}
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
						logProbability$var122[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = cv$sampleAccumulator;
						
						// Store the sample task probability
						logProbability$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = cv$sampleProbability;
					}
				}
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(fixedFlag$sample126)
					// Update the variable probability
					logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample126)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample126 = ((fixedFlag$sample126 && fixedFlag$sample42) && fixedFlag$sample95);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var122[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample126)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample126)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample159 using probability
	// distributions.
	private final void logProbabilityDistribution$sample159() {
		// Determine if we need to calculate the values for sample task 159 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample159) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Look for paths between the variable and the sample task 159 including any distribution
					// values.
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = (events[i$var136][j$var149] - 1);
						
						// Enumerating the possible arguments for Categorical 154.
						if(fixedFlag$sample95) {
							for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
								if((i$var87 == i$var136)) {
									if((0 == j$var149)) {
										for(int var55 = 0; var55 < noStates; var55 += 1) {
											if((var55 == st[i$var136][j$var149])) {
												{
													double[] var153 = bias[st[i$var136][j$var149]];
													
													// Store the value of the function call, so the function call is only made once.
													double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY));
													
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
							for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
								if(true) {
									// Enumerating the possible outputs of Categorical 91.
									for(int index$sample95$4 = 0; index$sample95$4 < noStates; index$sample95$4 += 1) {
										int distributionTempVariable$var92$6 = index$sample95$4;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample95Value5 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$4]);
										if((i$var87 == i$var136)) {
											if((0 == j$var149)) {
												for(int var55 = 0; var55 < noStates; var55 += 1) {
													if((var55 == st[i$var136][j$var149])) {
														{
															double[] var153 = bias[st[i$var136][j$var149]];
															
															// Store the value of the function call, so the function call is only made once.
															double cv$weightedProbability = (Math.log(cv$probabilitySample95Value5) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY));
															
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
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample95Value5);
														}
													}
												}
											}
										}
									}
								}
							}
						}
						
						// Enumerating the possible arguments for Categorical 154.
						if(fixedFlag$sample126) {
							for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
								for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
									if((i$var104 == i$var136)) {
										if((j$var115 == j$var149)) {
											for(int var55 = 0; var55 < noStates; var55 += 1) {
												if((var55 == st[i$var136][j$var149])) {
													{
														double[] var153 = bias[st[i$var136][j$var149]];
														
														// Store the value of the function call, so the function call is only made once.
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY));
														
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
							for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
								for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 122.
										for(int index$sample126$13 = 0; index$sample126$13 < noStates; index$sample126$13 += 1) {
											int distributionTempVariable$var123$15 = index$sample126$13;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample126Value14 = (1.0 * distribution$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)][index$sample126$13]);
											if((i$var104 == i$var136)) {
												if((j$var115 == j$var149)) {
													for(int var55 = 0; var55 < noStates; var55 += 1) {
														if((var55 == st[i$var136][j$var149])) {
															{
																double[] var153 = bias[st[i$var136][j$var149]];
																
																// Store the value of the function call, so the function call is only made once.
																double cv$weightedProbability = (Math.log(cv$probabilitySample126Value14) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																
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
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample126Value14);
															}
														}
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
					logProbability$var154[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample159[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = cv$sampleProbability;
				}
			}
			
			// Update the variable probability
			logProbability$events = (logProbability$events + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample159 = (((fixedFlag$sample159 && fixedFlag$sample57) && fixedFlag$sample95) && fixedFlag$sample126);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample159[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var154[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$events = (logProbability$events + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample95 using probability
	// distributions.
	private final void logProbabilityDistribution$sample95() {
		// Determine if we need to calculate the values for sample task 95 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample95) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample95) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Copy of index so that its values can be safely substituted
					int index$i$1 = i$var87;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[i$var87][0];
						{
							{
								double[] var90 = m[initialState];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var90[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
					logProbability$var91[((i$var87 - 0) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample95[((i$var87 - 0) / 1)] = cv$sampleProbability;
				}
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(fixedFlag$sample95)
					// Update the variable probability
					logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample95)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample95 = ((fixedFlag$sample95 && fixedFlag$sample42) && fixedFlag$sample80);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample95[((i$var87 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var91[((i$var87 - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample95)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample126 using sampled
	// values.
	private final void logProbabilityValue$sample126() {
		// Determine if we need to calculate the values for sample task 126 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample126) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Copy of index so that its values can be safely substituted
					int index$j$1 = j$var115;
					
					// Copy of index so that its values can be safely substituted
					int index$i$2 = i$var104;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[i$var104][j$var115];
						{
							{
								double[] var121 = m[st[i$var104][(j$var115 - 1)]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
					logProbability$var122[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = cv$sampleProbability;
				}
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample126)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample126 = ((fixedFlag$sample126 && fixedFlag$sample42) && fixedFlag$sample95);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var122[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample126)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample159 using sampled
	// values.
	private final void logProbabilityValue$sample159() {
		// Determine if we need to calculate the values for sample task 159 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample159) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = (events[i$var136][j$var149] - 1);
						{
							{
								double[] var153 = bias[st[i$var136][j$var149]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
					logProbability$var154[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample159[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = cv$sampleProbability;
				}
			}
			
			// Update the variable probability
			logProbability$events = (logProbability$events + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample159 = (((fixedFlag$sample159 && fixedFlag$sample57) && fixedFlag$sample95) && fixedFlag$sample126);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample159[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var154[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$events = (logProbability$events + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample42 using sampled
	// values.
	private final void logProbabilityValue$sample42() {
		// Determine if we need to calculate the values for sample task 42 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample42) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var41 = 0; var41 < noStates; var41 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double[] cv$sampleValue = m[var41];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, noStates));
							
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
			logProbability$var30 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var42 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample42 = fixedFlag$sample42;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var42;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var30 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample42)
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
			for(int var55 = 0; var55 < noStates; var55 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double[] cv$sampleValue = bias[var55];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v2, noEvents));
							
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
			logProbability$var44 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var56 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample57 = fixedFlag$sample57;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var56;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var44 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample57)
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
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, noStates));
						
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
			logProbability$var74 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$weights = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample78)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample78 = fixedFlag$sample78;
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
			logProbability$var74 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample78)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample80 using sampled
	// values.
	private final void logProbabilityValue$sample80() {
		// Determine if we need to calculate the values for sample task 80 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample80) {
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
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(weights[cv$sampleValue]):Double.NEGATIVE_INFINITY));
						
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
			logProbability$var76 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$initialState = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample80)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample80 = (fixedFlag$sample80 && fixedFlag$sample78);
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
			logProbability$var76 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample80)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample95 using sampled
	// values.
	private final void logProbabilityValue$sample95() {
		// Determine if we need to calculate the values for sample task 95 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample95) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Copy of index so that its values can be safely substituted
				int index$i$1 = i$var87;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[i$var87][0];
					{
						{
							double[] var90 = m[initialState];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var90[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
				logProbability$var91[((i$var87 - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$sample95[((i$var87 - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample95 = ((fixedFlag$sample95 && fixedFlag$sample42) && fixedFlag$sample80);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample95[((i$var87 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var91[((i$var87 - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 126 drawn from Categorical 122. Inference was performed using variable
	// marginalization.
	private final void sample126(int i$var104, int j$var115, int threadID$cv$i$var104, Rng RNG$) {
		// Calculate the number of states to evaluate.
		int cv$numNumStates = 0;
		
		// Exploring all the possible state counts for random variable 122.
		// 
		// Copy of index so that its values can be safely substituted
		int index$j$1 = j$var115;
		
		// Copy of index so that its values can be safely substituted
		int index$i$2 = i$var104;
		
		// Enumerating the possible arguments for Categorical 122.
		if(fixedFlag$sample95) {
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
				if((i$var87 == i$var104)) {
					if((0 == (j$var115 - 1))) {
						for(int var41 = 0; var41 < noStates; var41 += 1) {
							if((var41 == st[i$var104][(j$var115 - 1)]))
								// variable marginalization
								cv$numNumStates = Math.max(cv$numNumStates, noStates);
						}
					}
				}
			}
		} else {
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
				if(true) {
					// Enumerating the possible outputs of Categorical 91.
					for(int index$sample95$5 = 0; index$sample95$5 < noStates; index$sample95$5 += 1) {
						int distributionTempVariable$var92$7 = index$sample95$5;
						
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample95Value6 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$5]);
						if((i$var87 == i$var104)) {
							if((0 == (j$var115 - 1))) {
								for(int var41 = 0; var41 < noStates; var41 += 1) {
									if((var41 == st[i$var104][(j$var115 - 1)]))
										// variable marginalization
										cv$numNumStates = Math.max(cv$numNumStates, noStates);
								}
							}
						}
					}
				}
			}
		}
		
		// Enumerating the possible arguments for Categorical 122.
		if((index$i$2 == i$var104)) {
			if((index$j$1 == (j$var115 - 1))) {
				for(int var41 = 0; var41 < noStates; var41 += 1) {
					if((var41 == st[i$var104][(j$var115 - 1)]))
						// variable marginalization
						cv$numNumStates = Math.max(cv$numNumStates, noStates);
				}
			}
		}
		if(fixedFlag$sample126) {
			for(int index$i$12_1 = 0; index$i$12_1 < samples; index$i$12_1 += 1) {
				for(int index$j$12_2 = 1; index$j$12_2 < length$eventsMeasured[index$i$12_1]; index$j$12_2 += 1) {
					if((index$i$12_1 == i$var104)) {
						if((index$j$12_2 == (j$var115 - 1))) {
							for(int var41 = 0; var41 < noStates; var41 += 1) {
								if((var41 == st[i$var104][(j$var115 - 1)]))
									// variable marginalization
									cv$numNumStates = Math.max(cv$numNumStates, noStates);
							}
						}
					}
				}
			}
		} else {
			for(int index$i$13 = 0; index$i$13 < samples; index$i$13 += 1) {
				for(int index$j$14 = 1; index$j$14 < length$eventsMeasured[index$i$13]; index$j$14 += 1) {
					if(!((index$i$13 == index$i$2) && (index$j$14 == index$j$1))) {
						// Enumerating the possible outputs of Categorical 122.
						for(int index$sample126$15 = 0; index$sample126$15 < noStates; index$sample126$15 += 1) {
							int distributionTempVariable$var123$17 = index$sample126$15;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample126Value16 = (1.0 * distribution$sample126[((index$i$13 - 0) / 1)][((index$j$14 - 1) / 1)][index$sample126$15]);
							if((index$i$13 == i$var104)) {
								if((index$j$14 == (j$var115 - 1))) {
									for(int var41 = 0; var41 < noStates; var41 += 1) {
										if((var41 == st[i$var104][(j$var115 - 1)]))
											// variable marginalization
											cv$numNumStates = Math.max(cv$numNumStates, noStates);
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var123$stateProbabilityGlobal[threadID$cv$i$var104];
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 122 creating
			// sample task 126.
			// Copy of index so that its values can be safely substituted
			int index$j$22 = j$var115;
			
			// Copy of index so that its values can be safely substituted
			int index$i$23 = i$var104;
			
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
			
			// Enumerating the possible arguments for Categorical 122.
			if(fixedFlag$sample95) {
				for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
					if((i$var87 == i$var104)) {
						if((0 == (j$var115 - 1))) {
							for(int var41 = 0; var41 < noStates; var41 += 1) {
								if((var41 == st[i$var104][(j$var115 - 1)])) {
									// Record the reached probability density.
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
									double[] cv$temp$0$var121;
									{
										// Constructing a random variable input for use later.
										double[] var121 = m[st[i$var104][(j$var115 - 1)]];
										cv$temp$0$var121 = var121;
									}
									int cv$temp$1$$var1513;
									{
										// Constructing a random variable input for use later.
										int $var1513 = noStates;
										cv$temp$1$$var1513 = $var1513;
									}
									
									// An accumulator to allow the value for each distribution to be constructed before
									// it is added to the index probabilities.
									double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var1513))?Math.log(cv$temp$0$var121[cv$currentValue]):Double.NEGATIVE_INFINITY));
									
									// Processing random variable 122.
									{
										// Looking for a path between Sample 126 and consumer Categorical 122.
										{
											int traceTempVariable$var120$41_1 = cv$currentValue;
										}
									}
									
									// Processing random variable 154.
									{
										// Looking for a path between Sample 126 and consumer Categorical 154.
										{
											int traceTempVariable$var152$45_1 = cv$currentValue;
											for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
												if((i$var104 == i$var136)) {
													for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
														if((j$var115 == j$var149)) {
															// Processing sample task 159 of consumer random variable null.
															{
																// Set an accumulator to sum the probabilities for each possible configuration of
																// inputs.
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																
																// Set an accumulator to record the consumer distributions not seen. Initially set
																// to 1 as seen values will be deducted from this value.
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	// Enumerating the possible arguments for the variable Categorical 154 which is consuming
																	// the output of Sample task 126.
																	for(int var55 = 0; var55 < noStates; var55 += 1) {
																		if((var55 == st[i$var136][j$var149])) {
																			{
																				{
																					double[] cv$temp$8$var153;
																					{
																						// Constructing a random variable input for use later.
																						double[] var153 = bias[traceTempVariable$var152$45_1];
																						cv$temp$8$var153 = var153;
																					}
																					int cv$temp$9$$var1545;
																					{
																						// Constructing a random variable input for use later.
																						int $var1545 = noEvents;
																						cv$temp$9$$var1545 = $var1545;
																					}
																					
																					// Record the probability of sample task 159 generating output with current configuration.
																					if(((Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$9$$var1545))?Math.log(cv$temp$8$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$9$$var1545))?Math.log(cv$temp$8$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						// If the second value is -infinity.
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$9$$var1545))?Math.log(cv$temp$8$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$9$$var1545))?Math.log(cv$temp$8$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$9$$var1545))?Math.log(cv$temp$8$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)));
																					}
																					
																					// Recorded the probability of reaching sample task 159 with the current configuration.
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
				for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
					if(true) {
						// Enumerating the possible outputs of Categorical 91.
						for(int index$sample95$26 = 0; index$sample95$26 < noStates; index$sample95$26 += 1) {
							int distributionTempVariable$var92$28 = index$sample95$26;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample95Value27 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$26]);
							if((i$var87 == i$var104)) {
								if((0 == (j$var115 - 1))) {
									for(int var41 = 0; var41 < noStates; var41 += 1) {
										if((var41 == st[i$var104][(j$var115 - 1)])) {
											// Record the reached probability density.
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample95Value27);
											double[] cv$temp$2$var121;
											{
												// Constructing a random variable input for use later.
												double[] var121 = m[st[i$var104][(j$var115 - 1)]];
												cv$temp$2$var121 = var121;
											}
											int cv$temp$3$$var1514;
											{
												// Constructing a random variable input for use later.
												int $var1514 = noStates;
												cv$temp$3$$var1514 = $var1514;
											}
											
											// An accumulator to allow the value for each distribution to be constructed before
											// it is added to the index probabilities.
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample95Value27) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$$var1514))?Math.log(cv$temp$2$var121[cv$currentValue]):Double.NEGATIVE_INFINITY));
											
											// Processing random variable 122.
											{
												// Looking for a path between Sample 126 and consumer Categorical 122.
												{
													int traceTempVariable$var120$42_1 = cv$currentValue;
												}
											}
											
											// Processing random variable 154.
											{
												// Looking for a path between Sample 126 and consumer Categorical 154.
												{
													int traceTempVariable$var152$46_1 = cv$currentValue;
													for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
														if((i$var104 == i$var136)) {
															for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
																if((j$var115 == j$var149)) {
																	// Processing sample task 159 of consumer random variable null.
																	{
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Categorical 154 which is consuming
																			// the output of Sample task 126.
																			for(int var55 = 0; var55 < noStates; var55 += 1) {
																				if((var55 == st[i$var136][j$var149])) {
																					{
																						{
																							double[] cv$temp$10$var153;
																							{
																								// Constructing a random variable input for use later.
																								double[] var153 = bias[traceTempVariable$var152$46_1];
																								cv$temp$10$var153 = var153;
																							}
																							int cv$temp$11$$var1546;
																							{
																								// Constructing a random variable input for use later.
																								int $var1546 = noEvents;
																								cv$temp$11$$var1546 = $var1546;
																							}
																							
																							// Record the probability of sample task 159 generating output with current configuration.
																							if(((Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$11$$var1546))?Math.log(cv$temp$10$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$11$$var1546))?Math.log(cv$temp$10$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$11$$var1546))?Math.log(cv$temp$10$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$11$$var1546))?Math.log(cv$temp$10$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$11$$var1546))?Math.log(cv$temp$10$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)));
																							}
																							
																							// Recorded the probability of reaching sample task 159 with the current configuration.
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
			
			// Enumerating the possible arguments for Categorical 122.
			int traceTempVariable$var120$32_1 = cv$currentValue;
			if((index$i$23 == i$var104)) {
				if((index$j$22 == (j$var115 - 1))) {
					for(int var41 = 0; var41 < noStates; var41 += 1) {
						if((var41 == st[i$var104][(j$var115 - 1)])) {
							// Record the reached probability density.
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$4$var121;
							{
								// Constructing a random variable input for use later.
								double[] var121 = m[traceTempVariable$var120$32_1];
								cv$temp$4$var121 = var121;
							}
							int cv$temp$5$$var1515;
							{
								// Constructing a random variable input for use later.
								int $var1515 = noStates;
								cv$temp$5$$var1515 = $var1515;
							}
							
							// An accumulator to allow the value for each distribution to be constructed before
							// it is added to the index probabilities.
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$5$$var1515))?Math.log(cv$temp$4$var121[cv$currentValue]):Double.NEGATIVE_INFINITY));
							
							// Processing random variable 122.
							{
								// Looking for a path between Sample 126 and consumer Categorical 122.
								{
									int traceTempVariable$var120$43_1 = cv$currentValue;
								}
							}
							
							// Processing random variable 154.
							{
								// Looking for a path between Sample 126 and consumer Categorical 154.
								{
									int traceTempVariable$var152$47_1 = cv$currentValue;
									for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
										if((i$var104 == i$var136)) {
											for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
												if((j$var115 == j$var149)) {
													// Processing sample task 159 of consumer random variable null.
													{
														// Set an accumulator to sum the probabilities for each possible configuration of
														// inputs.
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														
														// Set an accumulator to record the consumer distributions not seen. Initially set
														// to 1 as seen values will be deducted from this value.
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															// Enumerating the possible arguments for the variable Categorical 154 which is consuming
															// the output of Sample task 126.
															for(int var55 = 0; var55 < noStates; var55 += 1) {
																if((var55 == st[i$var136][j$var149])) {
																	{
																		{
																			double[] cv$temp$12$var153;
																			{
																				// Constructing a random variable input for use later.
																				double[] var153 = bias[traceTempVariable$var152$47_1];
																				cv$temp$12$var153 = var153;
																			}
																			int cv$temp$13$$var1547;
																			{
																				// Constructing a random variable input for use later.
																				int $var1547 = noEvents;
																				cv$temp$13$$var1547 = $var1547;
																			}
																			
																			// Record the probability of sample task 159 generating output with current configuration.
																			if(((Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$13$$var1547))?Math.log(cv$temp$12$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$13$$var1547))?Math.log(cv$temp$12$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$13$$var1547))?Math.log(cv$temp$12$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$13$$var1547))?Math.log(cv$temp$12$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$13$$var1547))?Math.log(cv$temp$12$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)));
																			}
																			
																			// Recorded the probability of reaching sample task 159 with the current configuration.
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
						// Enumerating the possible outputs of Categorical 122.
						for(int index$sample126$35 = 0; index$sample126$35 < noStates; index$sample126$35 += 1) {
							int distributionTempVariable$var123$37 = index$sample126$35;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample126Value36 = (1.0 * distribution$sample126[((index$i$33 - 0) / 1)][((index$j$34 - 1) / 1)][index$sample126$35]);
							int traceTempVariable$var120$38_1 = cv$currentValue;
							if((index$i$33 == i$var104)) {
								if((index$j$34 == (j$var115 - 1))) {
									for(int var41 = 0; var41 < noStates; var41 += 1) {
										if((var41 == st[i$var104][(j$var115 - 1)])) {
											// Record the reached probability density.
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample126Value36);
											double[] cv$temp$6$var121;
											{
												// Constructing a random variable input for use later.
												double[] var121 = m[traceTempVariable$var120$38_1];
												cv$temp$6$var121 = var121;
											}
											int cv$temp$7$$var1516;
											{
												// Constructing a random variable input for use later.
												int $var1516 = noStates;
												cv$temp$7$$var1516 = $var1516;
											}
											
											// An accumulator to allow the value for each distribution to be constructed before
											// it is added to the index probabilities.
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample126Value36) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$7$$var1516))?Math.log(cv$temp$6$var121[cv$currentValue]):Double.NEGATIVE_INFINITY));
											
											// Processing random variable 122.
											{
												// Looking for a path between Sample 126 and consumer Categorical 122.
												{
													int traceTempVariable$var120$44_1 = distributionTempVariable$var123$37;
												}
											}
											
											// Processing random variable 154.
											{
												// Looking for a path between Sample 126 and consumer Categorical 154.
												{
													int traceTempVariable$var152$48_1 = distributionTempVariable$var123$37;
													for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
														if((i$var104 == i$var136)) {
															for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
																if((j$var115 == j$var149)) {
																	// Processing sample task 159 of consumer random variable null.
																	{
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Categorical 154 which is consuming
																			// the output of Sample task 126.
																			for(int var55 = 0; var55 < noStates; var55 += 1) {
																				if((var55 == st[i$var136][j$var149])) {
																					{
																						{
																							double[] cv$temp$14$var153;
																							{
																								// Constructing a random variable input for use later.
																								double[] var153 = bias[traceTempVariable$var152$48_1];
																								cv$temp$14$var153 = var153;
																							}
																							int cv$temp$15$$var1548;
																							{
																								// Constructing a random variable input for use later.
																								int $var1548 = noEvents;
																								cv$temp$15$$var1548 = $var1548;
																							}
																							
																							// Record the probability of sample task 159 generating output with current configuration.
																							if(((Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$15$$var1548))?Math.log(cv$temp$14$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$15$$var1548))?Math.log(cv$temp$14$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$15$$var1548))?Math.log(cv$temp$14$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$15$$var1548))?Math.log(cv$temp$14$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$15$$var1548))?Math.log(cv$temp$14$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)));
																							}
																							
																							// Recorded the probability of reaching sample task 159 with the current configuration.
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
			
			// Processing random variable 122.
			{
				// Looking for a path between Sample 126 and consumer Categorical 122.
				{
					int traceTempVariable$var120$61_1 = cv$currentValue;
					for(int index$i$61_2 = 0; index$i$61_2 < samples; index$i$61_2 += 1) {
						if((i$var104 == index$i$61_2)) {
							for(int index$j$61_3 = 1; index$j$61_3 < length$eventsMeasured[index$i$61_2]; index$j$61_3 += 1) {
								if((j$var115 == (index$j$61_3 - 1))) {
									// Processing sample task 126 of consumer random variable null.
									{
										// Copy of index so that its values can be safely substituted
										int index$j$63 = index$j$61_3;
										
										// Copy of index so that its values can be safely substituted
										int index$i$64 = index$i$61_2;
										
										// A local array to hold the accumulated distributions of the sample tasks for each
										// configuration of distributions.
										double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var122[threadID$cv$i$var104];
										
										// Zero all the elements in the distribution accumulator
										for(int cv$i = 0; cv$i < noStates; cv$i += 1)
											cv$accumulatedConsumerDistributions[cv$i] = 0.0;
										
										// Zero an accumulator to track the probabilities reached.
										double cv$reachedDistributionProbability = 0.0;
										
										// Enumerating the possible arguments for the variable Categorical 122 which is consuming
										// the output of Sample task 126.
										for(int var41 = 0; var41 < noStates; var41 += 1) {
											if((var41 == st[index$i$61_2][(index$j$61_3 - 1)])) {
												{
													// Declare and zero an accumulator for tracking the reached source probability space.
													double scopeVariable$reachedSourceProbability = 0.0;
													
													// Enumerating the possible arguments for Categorical 122.
													if(fixedFlag$sample95) {
														for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
															if((i$var87 == i$var104)) {
																if((0 == (j$var115 - 1))) {
																	for(int index$var41$72_1 = 0; index$var41$72_1 < noStates; index$var41$72_1 += 1) {
																		if((index$var41$72_1 == st[i$var104][(j$var115 - 1)]))
																			// Add the probability of this argument configuration.
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																}
															}
														}
													} else {
														for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
															if(true) {
																// Enumerating the possible outputs of Categorical 91.
																for(int index$sample95$68 = 0; index$sample95$68 < noStates; index$sample95$68 += 1) {
																	int distributionTempVariable$var92$70 = index$sample95$68;
																	
																	// Update the probability of sampling this value from the distribution value.
																	double cv$probabilitySample95Value69 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$68]);
																	if((i$var87 == i$var104)) {
																		if((0 == (j$var115 - 1))) {
																			for(int index$var41$73_1 = 0; index$var41$73_1 < noStates; index$var41$73_1 += 1) {
																				if((index$var41$73_1 == st[i$var104][(j$var115 - 1)]))
																					// Add the probability of this argument configuration.
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample95Value69);
																			}
																		}
																	}
																}
															}
														}
													}
													
													// Enumerating the possible arguments for Categorical 122.
													int traceTempVariable$var120$74_1 = cv$currentValue;
													if((index$i$23 == i$var104)) {
														if((index$j$22 == (j$var115 - 1))) {
															for(int index$var41$81_1 = 0; index$var41$81_1 < noStates; index$var41$81_1 += 1) {
																if((index$var41$81_1 == st[i$var104][(j$var115 - 1)]))
																	// Add the probability of this argument configuration.
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
														}
													}
													for(int index$i$75 = 0; index$i$75 < samples; index$i$75 += 1) {
														for(int index$j$76 = 1; index$j$76 < length$eventsMeasured[index$i$75]; index$j$76 += 1) {
															if((!((index$i$75 == index$i$23) && (index$j$76 == index$j$22)) && !((index$i$75 == index$i$64) && (index$j$76 == index$j$63)))) {
																// Enumerating the possible outputs of Categorical 122.
																for(int index$sample126$77 = 0; index$sample126$77 < noStates; index$sample126$77 += 1) {
																	int distributionTempVariable$var123$79 = index$sample126$77;
																	
																	// Update the probability of sampling this value from the distribution value.
																	double cv$probabilitySample126Value78 = (1.0 * distribution$sample126[((index$i$75 - 0) / 1)][((index$j$76 - 1) / 1)][index$sample126$77]);
																	int traceTempVariable$var120$80_1 = cv$currentValue;
																	if((index$i$75 == i$var104)) {
																		if((index$j$76 == (j$var115 - 1))) {
																			for(int index$var41$82_1 = 0; index$var41$82_1 < noStates; index$var41$82_1 += 1) {
																				if((index$var41$82_1 == st[i$var104][(j$var115 - 1)]))
																					// Add the probability of this argument configuration.
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample126Value78);
																			}
																		}
																	}
																}
															}
														}
													}
													double[] cv$temp$16$var121;
													{
														// Constructing a random variable input for use later.
														double[] var121 = m[traceTempVariable$var120$61_1];
														cv$temp$16$var121 = var121;
													}
													int cv$temp$17$$var1586;
													{
														// Constructing a random variable input for use later.
														int $var1586 = noStates;
														cv$temp$17$$var1586 = $var1586;
													}
													
													// The probability of reaching the consumer with this set of consumer arguments
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													
													// Record the reached distribution.
													cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
													
													// Add the current distribution to the distribution accumulator.
													DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$16$var121, cv$temp$17$$var1586);
												}
											}
										}
										
										// A local copy of the samples' distribution.
										double[] cv$sampleDistribution = distribution$sample126[((index$i$61_2 - 0) / 1)][((index$j$61_3 - 1) / 1)];
										
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
		double[] cv$localProbability = distribution$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)];
		
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
	// by sample task 42 drawn from Dirichlet 30. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample42(int var41, int threadID$cv$var41, Rng RNG$) {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = m[var41];
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var42$countGlobal[threadID$cv$var41];
		
		// Get the length of the array
		int cv$arrayLength = noStates;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 91.
			{
				// Looking for a path between Sample 42 and consumer Categorical 91.
				{
					if((var41 == initialState)) {
						for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
							if(fixedFlag$sample95) {
								// Processing sample task 95 of consumer random variable null.
								{
									// Copy of index so that its values can be safely substituted
									int index$i$3 = i$var87;
									{
										{
											{
												{
													// Increment the sample counter with the value sampled by sample task 95 of random
													// variable var91
													cv$countLocal[st[i$var87][0]] = (cv$countLocal[st[i$var87][0]] + 1.0);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Processing random variable 122.
			{
				// Looking for a path between Sample 42 and consumer Categorical 122.
				{
					for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
						for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
							if(fixedFlag$sample95) {
								for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
									if((i$var87 == i$var104)) {
										if((0 == (j$var115 - 1))) {
											if((var41 == st[i$var104][(j$var115 - 1)])) {
												if(fixedFlag$sample126) {
													// Processing sample task 126 of consumer random variable null.
													{
														// Copy of index so that its values can be safely substituted
														int index$j$27 = j$var115;
														
														// Copy of index so that its values can be safely substituted
														int index$i$28 = i$var104;
														{
															{
																{
																	{
																		// Increment the sample counter with the value sampled by sample task 126 of random
																		// variable var122
																		cv$countLocal[st[i$var104][j$var115]] = (cv$countLocal[st[i$var104][j$var115]] + 1.0);
																	}
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
								for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 91.
										for(int index$sample95$9 = 0; index$sample95$9 < noStates; index$sample95$9 += 1) {
											int distributionTempVariable$var92$11 = index$sample95$9;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample95Value10 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$9]);
											if((i$var87 == i$var104)) {
												if((0 == (j$var115 - 1))) {
													if((var41 == st[i$var104][(j$var115 - 1)])) {
														if(fixedFlag$sample126) {
															// Processing sample task 126 of consumer random variable null.
															{
																// Copy of index so that its values can be safely substituted
																int index$j$30 = j$var115;
																
																// Copy of index so that its values can be safely substituted
																int index$i$31 = i$var104;
																{
																	{
																		{
																			{
																				// Increment the sample counter with the value sampled by sample task 126 of random
																				// variable var122
																				cv$countLocal[st[i$var104][j$var115]] = (cv$countLocal[st[i$var104][j$var115]] + cv$probabilitySample95Value10);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
						for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
							if(fixedFlag$sample126) {
								for(int index$i$17_1 = 0; index$i$17_1 < samples; index$i$17_1 += 1) {
									for(int index$j$17_2 = 1; index$j$17_2 < length$eventsMeasured[index$i$17_1]; index$j$17_2 += 1) {
										if((index$i$17_1 == i$var104)) {
											if((index$j$17_2 == (j$var115 - 1))) {
												if((var41 == st[i$var104][(j$var115 - 1)])) {
													if(fixedFlag$sample126) {
														// Processing sample task 126 of consumer random variable null.
														{
															// Copy of index so that its values can be safely substituted
															int index$j$33 = j$var115;
															
															// Copy of index so that its values can be safely substituted
															int index$i$34 = i$var104;
															{
																{
																	{
																		{
																			// Increment the sample counter with the value sampled by sample task 126 of random
																			// variable var122
																			cv$countLocal[st[i$var104][j$var115]] = (cv$countLocal[st[i$var104][j$var115]] + 1.0);
																		}
																	}
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
											// Enumerating the possible outputs of Categorical 122.
											for(int index$sample126$20 = 0; index$sample126$20 < noStates; index$sample126$20 += 1) {
												int distributionTempVariable$var123$22 = index$sample126$20;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample126Value21 = (1.0 * distribution$sample126[((index$i$18 - 0) / 1)][((index$j$19 - 1) / 1)][index$sample126$20]);
												if((index$i$18 == i$var104)) {
													if((index$j$19 == (j$var115 - 1))) {
														if((var41 == st[i$var104][(j$var115 - 1)])) {
															if(fixedFlag$sample126) {
																// Processing sample task 126 of consumer random variable null.
																{
																	// Copy of index so that its values can be safely substituted
																	int index$j$36 = j$var115;
																	
																	// Copy of index so that its values can be safely substituted
																	int index$i$37 = i$var104;
																	{
																		{
																			{
																				{
																					// Increment the sample counter with the value sampled by sample task 126 of random
																					// variable var122
																					cv$countLocal[st[i$var104][j$var115]] = (cv$countLocal[st[i$var104][j$var115]] + cv$probabilitySample126Value21);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Processing random variable 91.
		{
			// Looking for a path between Sample 42 and consumer Categorical 91.
			{
				if((var41 == initialState)) {
					for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
						if(!fixedFlag$sample95) {
							// Processing sample task 95 of consumer random variable null.
							{
								// Copy of index so that its values can be safely substituted
								int index$i$44 = i$var87;
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
											cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample95[((i$var87 - 0) / 1)][cv$loopIndex] * cv$distributionProbability));
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Processing random variable 122.
		{
			// Looking for a path between Sample 42 and consumer Categorical 122.
			{
				for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
					for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
						if(fixedFlag$sample95) {
							for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
								if((i$var87 == i$var104)) {
									if((0 == (j$var115 - 1))) {
										if((var41 == st[i$var104][(j$var115 - 1)])) {
											if(!fixedFlag$sample126) {
												// Processing sample task 126 of consumer random variable null.
												{
													// Copy of index so that its values can be safely substituted
													int index$j$67 = j$var115;
													
													// Copy of index so that its values can be safely substituted
													int index$i$68 = i$var104;
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
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
														}
													}
												}
											}
										}
									}
								}
							}
						} else {
							for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
								if(true) {
									// Enumerating the possible outputs of Categorical 91.
									for(int index$sample95$49 = 0; index$sample95$49 < noStates; index$sample95$49 += 1) {
										int distributionTempVariable$var92$51 = index$sample95$49;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample95Value50 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$49]);
										if((i$var87 == i$var104)) {
											if((0 == (j$var115 - 1))) {
												if((var41 == st[i$var104][(j$var115 - 1)])) {
													if(!fixedFlag$sample126) {
														// Processing sample task 126 of consumer random variable null.
														{
															// Copy of index so that its values can be safely substituted
															int index$j$70 = j$var115;
															
															// Copy of index so that its values can be safely substituted
															int index$i$71 = i$var104;
															{
																{
																	// Declare and zero an accumulator for tracking the reached source probability space.
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		// Add the probability of this argument configuration.
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																	
																	// The probability of reaching the consumer with this set of consumer arguments
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample95Value50);
																	
																	// Merge the distribution probabilities into the count
																	for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
					for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
						if(fixedFlag$sample126) {
							for(int index$i$57_1 = 0; index$i$57_1 < samples; index$i$57_1 += 1) {
								for(int index$j$57_2 = 1; index$j$57_2 < length$eventsMeasured[index$i$57_1]; index$j$57_2 += 1) {
									if((index$i$57_1 == i$var104)) {
										if((index$j$57_2 == (j$var115 - 1))) {
											if((var41 == st[i$var104][(j$var115 - 1)])) {
												if(!fixedFlag$sample126) {
													// Processing sample task 126 of consumer random variable null.
													{
														// Copy of index so that its values can be safely substituted
														int index$j$73 = j$var115;
														
														// Copy of index so that its values can be safely substituted
														int index$i$74 = i$var104;
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
																	cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
										// Enumerating the possible outputs of Categorical 122.
										for(int index$sample126$60 = 0; index$sample126$60 < noStates; index$sample126$60 += 1) {
											int distributionTempVariable$var123$62 = index$sample126$60;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample126Value61 = (1.0 * distribution$sample126[((index$i$58 - 0) / 1)][((index$j$59 - 1) / 1)][index$sample126$60]);
											if((index$i$58 == i$var104)) {
												if((index$j$59 == (j$var115 - 1))) {
													if((var41 == st[i$var104][(j$var115 - 1)])) {
														if(!fixedFlag$sample126) {
															// Processing sample task 126 of consumer random variable null.
															{
																// Copy of index so that its values can be safely substituted
																int index$j$76 = j$var115;
																
																// Copy of index so that its values can be safely substituted
																int index$i$77 = i$var104;
																{
																	{
																		// Declare and zero an accumulator for tracking the reached source probability space.
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			// Add the probability of this argument configuration.
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		
																		// The probability of reaching the consumer with this set of consumer arguments
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample126Value61);
																		
																		// Merge the distribution probabilities into the count
																		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																			cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																	}
																}
															}
														}
													}
												}
											}
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
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 57 drawn from Dirichlet 44. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample57(int var55, int threadID$cv$var55, Rng RNG$) {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = bias[var55];
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var56$countGlobal[threadID$cv$var55];
		
		// Get the length of the array
		int cv$arrayLength = noEvents;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 154.
			{
				// Looking for a path between Sample 57 and consumer Categorical 154.
				{
					for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
						for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
							if(fixedFlag$sample95) {
								for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
									if((i$var87 == i$var136)) {
										if((0 == j$var149)) {
											if((var55 == st[i$var136][j$var149])) {
												// Processing sample task 159 of consumer random variable null.
												{
													{
														{
															{
																{
																	// Increment the sample counter with the value sampled by sample task 159 of random
																	// variable var154
																	cv$countLocal[(events[i$var136][j$var149] - 1)] = (cv$countLocal[(events[i$var136][j$var149] - 1)] + 1.0);
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
								for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 91.
										for(int index$sample95$5 = 0; index$sample95$5 < noStates; index$sample95$5 += 1) {
											int distributionTempVariable$var92$7 = index$sample95$5;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample95Value6 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$5]);
											if((i$var87 == i$var136)) {
												if((0 == j$var149)) {
													if((var55 == st[i$var136][j$var149])) {
														// Processing sample task 159 of consumer random variable null.
														{
															{
																{
																	{
																		{
																			// Increment the sample counter with the value sampled by sample task 159 of random
																			// variable var154
																			cv$countLocal[(events[i$var136][j$var149] - 1)] = (cv$countLocal[(events[i$var136][j$var149] - 1)] + cv$probabilitySample95Value6);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
						for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
							if(fixedFlag$sample126) {
								for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
									for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
										if((i$var104 == i$var136)) {
											if((j$var115 == j$var149)) {
												if((var55 == st[i$var136][j$var149])) {
													// Processing sample task 159 of consumer random variable null.
													{
														{
															{
																{
																	{
																		// Increment the sample counter with the value sampled by sample task 159 of random
																		// variable var154
																		cv$countLocal[(events[i$var136][j$var149] - 1)] = (cv$countLocal[(events[i$var136][j$var149] - 1)] + 1.0);
																	}
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
								for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
									for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 122.
											for(int index$sample126$16 = 0; index$sample126$16 < noStates; index$sample126$16 += 1) {
												int distributionTempVariable$var123$18 = index$sample126$16;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample126Value17 = (1.0 * distribution$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)][index$sample126$16]);
												if((i$var104 == i$var136)) {
													if((j$var115 == j$var149)) {
														if((var55 == st[i$var136][j$var149])) {
															// Processing sample task 159 of consumer random variable null.
															{
																{
																	{
																		{
																			{
																				// Increment the sample counter with the value sampled by sample task 159 of random
																				// variable var154
																				cv$countLocal[(events[i$var136][j$var149] - 1)] = (cv$countLocal[(events[i$var136][j$var149] - 1)] + cv$probabilitySample126Value17);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
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
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v2, cv$countLocal, cv$targetLocal, noEvents);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 78 drawn from Dirichlet 74. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample78() {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = weights;
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var75$countGlobal;
		
		// Get the length of the array
		int cv$arrayLength = noStates;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 76.
			{
				{
					// Processing sample task 80 of consumer random variable null.
					{
						{
							{
								{
									{
										// Increment the sample counter with the value sampled by sample task 80 of random
										// variable var76
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
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 80 drawn from Categorical 76. Inference was performed using variable
	// marginalization.
	private final void sample80() {
		// Calculate the number of states to evaluate.
		int cv$numNumStates = 0;
		{
			// variable marginalization
			cv$numNumStates = Math.max(cv$numNumStates, noStates);
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var77$stateProbabilityGlobal;
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
			
			// Write out the new value of the sample.
			initialState = cv$currentValue;
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$weights;
				{
					cv$temp$0$weights = weights;
				}
				int cv$temp$1$$var1350;
				{
					// Constructing a random variable input for use later.
					int $var1350 = noStates;
					cv$temp$1$$var1350 = $var1350;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var1350))?Math.log(cv$temp$0$weights[cv$currentValue]):Double.NEGATIVE_INFINITY));
				
				// Processing random variable 91.
				{
					{
						for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
							int traceTempVariable$initialState$1_2 = cv$currentValue;
							if(fixedFlag$sample95) {
								// Processing sample task 95 of consumer random variable null.
								{
									// Copy of index so that its values can be safely substituted
									int index$i$3 = i$var87;
									
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
													double[] cv$temp$2$var90;
													{
														// Constructing a random variable input for use later.
														double[] var90 = m[traceTempVariable$initialState$1_2];
														cv$temp$2$var90 = var90;
													}
													int cv$temp$3$$var1360;
													{
														// Constructing a random variable input for use later.
														int $var1360 = noStates;
														cv$temp$3$$var1360 = $var1360;
													}
													
													// Record the probability of sample task 95 generating output with current configuration.
													if(((Math.log(1.0) + (((0.0 <= st[i$var87][0]) && (st[i$var87][0] < cv$temp$3$$var1360))?Math.log(cv$temp$2$var90[st[i$var87][0]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var87][0]) && (st[i$var87][0] < cv$temp$3$$var1360))?Math.log(cv$temp$2$var90[st[i$var87][0]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var87][0]) && (st[i$var87][0] < cv$temp$3$$var1360))?Math.log(cv$temp$2$var90[st[i$var87][0]]):Double.NEGATIVE_INFINITY));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var87][0]) && (st[i$var87][0] < cv$temp$3$$var1360))?Math.log(cv$temp$2$var90[st[i$var87][0]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var87][0]) && (st[i$var87][0] < cv$temp$3$$var1360))?Math.log(cv$temp$2$var90[st[i$var87][0]]):Double.NEGATIVE_INFINITY)));
													}
													
													// Recorded the probability of reaching sample task 95 with the current configuration.
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
			
			// Processing random variable 91.
			{
				{
					for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
						int traceTempVariable$initialState$5_2 = cv$currentValue;
						if(!fixedFlag$sample95) {
							// Processing sample task 95 of consumer random variable null.
							{
								// Copy of index so that its values can be safely substituted
								int index$i$7 = i$var87;
								
								// A local array to hold the accumulated distributions of the sample tasks for each
								// configuration of distributions.
								double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var91;
								
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
										double[] cv$temp$4$var90;
										{
											// Constructing a random variable input for use later.
											double[] var90 = m[traceTempVariable$initialState$5_2];
											cv$temp$4$var90 = var90;
										}
										int cv$temp$5$$var1371;
										{
											// Constructing a random variable input for use later.
											int $var1371 = noStates;
											cv$temp$5$$var1371 = $var1371;
										}
										
										// The probability of reaching the consumer with this set of consumer arguments
										double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
										
										// Record the reached distribution.
										cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
										
										// Add the current distribution to the distribution accumulator.
										DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$4$var90, cv$temp$5$$var1371);
									}
								}
								
								// A local copy of the samples' distribution.
								double[] cv$sampleDistribution = distribution$sample95[((i$var87 - 0) / 1)];
								
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
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the new value of the sample.
		initialState = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 95 drawn from Categorical 91. Inference was performed using variable
	// marginalization.
	private final void sample95(int i$var87, int threadID$cv$i$var87, Rng RNG$) {
		// Calculate the number of states to evaluate.
		int cv$numNumStates = 0;
		
		// Copy of index so that its values can be safely substituted
		int index$i$1 = i$var87;
		{
			// variable marginalization
			cv$numNumStates = Math.max(cv$numNumStates, noStates);
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var92$stateProbabilityGlobal[threadID$cv$i$var87];
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			// Copy of index so that its values can be safely substituted
			int index$i$2 = i$var87;
			
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
				double[] cv$temp$0$var90;
				{
					// Constructing a random variable input for use later.
					double[] var90 = m[initialState];
					cv$temp$0$var90 = var90;
				}
				int cv$temp$1$$var1390;
				{
					// Constructing a random variable input for use later.
					int $var1390 = noStates;
					cv$temp$1$$var1390 = $var1390;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var1390))?Math.log(cv$temp$0$var90[cv$currentValue]):Double.NEGATIVE_INFINITY));
				
				// Processing random variable 122.
				{
					// Looking for a path between Sample 95 and consumer Categorical 122.
					{
						int traceTempVariable$var120$3_1 = cv$currentValue;
						for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
							if((i$var87 == i$var104)) {
								for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
									if((0 == (j$var115 - 1))) {
										if(fixedFlag$sample126) {
											// Processing sample task 126 of consumer random variable null.
											{
												// Copy of index so that its values can be safely substituted
												int index$j$5 = j$var115;
												
												// Copy of index so that its values can be safely substituted
												int index$i$6 = i$var104;
												
												// Set an accumulator to sum the probabilities for each possible configuration of
												// inputs.
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												
												// Set an accumulator to record the consumer distributions not seen. Initially set
												// to 1 as seen values will be deducted from this value.
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													// Enumerating the possible arguments for the variable Categorical 122 which is consuming
													// the output of Sample task 95.
													for(int var41 = 0; var41 < noStates; var41 += 1) {
														if((var41 == st[i$var104][(j$var115 - 1)])) {
															{
																{
																	double[] cv$temp$2$var121;
																	{
																		// Constructing a random variable input for use later.
																		double[] var121 = m[traceTempVariable$var120$3_1];
																		cv$temp$2$var121 = var121;
																	}
																	int cv$temp$3$$var1403;
																	{
																		// Constructing a random variable input for use later.
																		int $var1403 = noStates;
																		cv$temp$3$$var1403 = $var1403;
																	}
																	
																	// Record the probability of sample task 126 generating output with current configuration.
																	if(((Math.log(1.0) + (((0.0 <= st[i$var104][j$var115]) && (st[i$var104][j$var115] < cv$temp$3$$var1403))?Math.log(cv$temp$2$var121[st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var104][j$var115]) && (st[i$var104][j$var115] < cv$temp$3$$var1403))?Math.log(cv$temp$2$var121[st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var104][j$var115]) && (st[i$var104][j$var115] < cv$temp$3$$var1403))?Math.log(cv$temp$2$var121[st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var104][j$var115]) && (st[i$var104][j$var115] < cv$temp$3$$var1403))?Math.log(cv$temp$2$var121[st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var104][j$var115]) && (st[i$var104][j$var115] < cv$temp$3$$var1403))?Math.log(cv$temp$2$var121[st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)));
																	}
																	
																	// Recorded the probability of reaching sample task 126 with the current configuration.
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
				
				// Processing random variable 154.
				{
					// Looking for a path between Sample 95 and consumer Categorical 154.
					{
						int traceTempVariable$var152$9_1 = cv$currentValue;
						for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
							if((i$var87 == i$var136)) {
								for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
									if((0 == j$var149)) {
										// Processing sample task 159 of consumer random variable null.
										{
											// Set an accumulator to sum the probabilities for each possible configuration of
											// inputs.
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											
											// Set an accumulator to record the consumer distributions not seen. Initially set
											// to 1 as seen values will be deducted from this value.
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												// Enumerating the possible arguments for the variable Categorical 154 which is consuming
												// the output of Sample task 95.
												for(int var55 = 0; var55 < noStates; var55 += 1) {
													if((var55 == st[i$var136][j$var149])) {
														{
															{
																double[] cv$temp$4$var153;
																{
																	// Constructing a random variable input for use later.
																	double[] var153 = bias[traceTempVariable$var152$9_1];
																	cv$temp$4$var153 = var153;
																}
																int cv$temp$5$$var1414;
																{
																	// Constructing a random variable input for use later.
																	int $var1414 = noEvents;
																	cv$temp$5$$var1414 = $var1414;
																}
																
																// Record the probability of sample task 159 generating output with current configuration.
																if(((Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$5$$var1414))?Math.log(cv$temp$4$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$5$$var1414))?Math.log(cv$temp$4$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$5$$var1414))?Math.log(cv$temp$4$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$5$$var1414))?Math.log(cv$temp$4$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$5$$var1414))?Math.log(cv$temp$4$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)));
																}
																
																// Recorded the probability of reaching sample task 159 with the current configuration.
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
			
			// Processing random variable 122.
			{
				// Looking for a path between Sample 95 and consumer Categorical 122.
				{
					int traceTempVariable$var120$13_1 = cv$currentValue;
					for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
						if((i$var87 == i$var104)) {
							for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
								if((0 == (j$var115 - 1))) {
									if(!fixedFlag$sample126) {
										// Processing sample task 126 of consumer random variable null.
										{
											// Copy of index so that its values can be safely substituted
											int index$j$15 = j$var115;
											
											// Copy of index so that its values can be safely substituted
											int index$i$16 = i$var104;
											
											// A local array to hold the accumulated distributions of the sample tasks for each
											// configuration of distributions.
											double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var122[threadID$cv$i$var87];
											
											// Zero all the elements in the distribution accumulator
											for(int cv$i = 0; cv$i < noStates; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											
											// Zero an accumulator to track the probabilities reached.
											double cv$reachedDistributionProbability = 0.0;
											
											// Enumerating the possible arguments for the variable Categorical 122 which is consuming
											// the output of Sample task 95.
											for(int var41 = 0; var41 < noStates; var41 += 1) {
												if((var41 == st[i$var104][(j$var115 - 1)])) {
													{
														// Declare and zero an accumulator for tracking the reached source probability space.
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															// Add the probability of this argument configuration.
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														double[] cv$temp$6$var121;
														{
															// Constructing a random variable input for use later.
															double[] var121 = m[traceTempVariable$var120$13_1];
															cv$temp$6$var121 = var121;
														}
														int cv$temp$7$$var1427;
														{
															// Constructing a random variable input for use later.
															int $var1427 = noStates;
															cv$temp$7$$var1427 = $var1427;
														}
														
														// The probability of reaching the consumer with this set of consumer arguments
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
														
														// Record the reached distribution.
														cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
														
														// Add the current distribution to the distribution accumulator.
														DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$6$var121, cv$temp$7$$var1427);
													}
												}
											}
											
											// A local copy of the samples' distribution.
											double[] cv$sampleDistribution = distribution$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)];
											
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
		double[] cv$localProbability = distribution$sample95[((i$var87 - 0) / 1)];
		
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
		// Constructor for cv$var42$countGlobal
		{
			// Allocation of cv$var42$countGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var42$countGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var42$countGlobal[cv$index] = new double[noStates];
			}
		}
		
		// Constructor for cv$var56$countGlobal
		{
			// Allocation of cv$var56$countGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var56$countGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var56$countGlobal[cv$index] = new double[noEvents];
			}
		}
		
		// Constructor for cv$var75$countGlobal
		{
			// Allocation of cv$var75$countGlobal for single threaded execution
			cv$var75$countGlobal = new double[noStates];
		}
		
		// Constructor for cv$distributionAccumulator$var91
		{
			// Variable to record the maximum value of Task Get 93. Initially set to the value
			// of putTask 43.
			int cv$var43$max = noStates;
			
			// Allocation of cv$distributionAccumulator$var91 for single threaded execution
			cv$distributionAccumulator$var91 = new double[cv$var43$max];
		}
		
		// Constructor for cv$var77$stateProbabilityGlobal
		{
			// Allocation of cv$var77$stateProbabilityGlobal for single threaded execution
			cv$var77$stateProbabilityGlobal = new double[noStates];
		}
		
		// Constructor for cv$distributionAccumulator$var122
		{
			// Variable to record the maximum value of Task Get 124. Initially set to the value
			// of putTask 43.
			int cv$var43$max = noStates;
			
			// Allocation of cv$distributionAccumulator$var122 for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$distributionAccumulator$var122 = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$distributionAccumulator$var122[cv$index] = new double[cv$var43$max];
			}
		}
		
		// Constructor for cv$var92$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 93. Initially set to the value
			// of putTask 43.
			int cv$var43$max = noStates;
			
			// Allocation of cv$var92$stateProbabilityGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var92$stateProbabilityGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var92$stateProbabilityGlobal[cv$index] = new double[cv$var43$max];
			}
		}
		
		// Constructor for cv$var123$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 124. Initially set to the value
			// of putTask 43.
			int cv$var43$max = noStates;
			
			// Allocation of cv$var123$stateProbabilityGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var123$stateProbabilityGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var123$stateProbabilityGlobal[cv$index] = new double[cv$var43$max];
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
				for(int var41 = 0; var41 < noStates; var41 += 1)
					m[var41] = new double[noStates];
			}
		}
		
		// If bias has not been set already allocate space.
		if(!setFlag$bias) {
			// Constructor for bias
			{
				bias = new double[noStates][];
				for(int var55 = 0; var55 < noStates; var55 += 1)
					bias[var55] = new double[noEvents];
			}
		}
		
		// If st has not been set already allocate space.
		if(!setFlag$st) {
			// Constructor for st
			{
				st = new int[length$eventsMeasured.length][];
				for(int i$var69 = 0; i$var69 < length$eventsMeasured.length; i$var69 += 1)
					st[i$var69] = new int[length$eventsMeasured[i$var69]];
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
				for(int i$var136 = 0; i$var136 < length$eventsMeasured.length; i$var136 += 1)
					events[i$var136] = new int[length$eventsMeasured[i$var136]];
			}
		}
		
		// Constructor for distribution$sample126
		{
			distribution$sample126 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][][];
			for(int i$var104 = 0; i$var104 < length$eventsMeasured.length; i$var104 += 1) {
				double[][] subarray$0 = new double[((((length$eventsMeasured[i$var104] - 1) - 1) / 1) + 1)][];
				distribution$sample126[((i$var104 - 0) / 1)] = subarray$0;
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
					subarray$0[((j$var115 - 1) / 1)] = new double[noStates];
			}
		}
		
		// Constructor for distribution$sample95
		{
			distribution$sample95 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var87 = 0; i$var87 < length$eventsMeasured.length; i$var87 += 1)
				distribution$sample95[((i$var87 - 0) / 1)] = new double[noStates];
		}
		
		// Constructor for logProbability$var91
		{
			logProbability$var91 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample95
		{
			logProbability$sample95 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$var122
		{
			logProbability$var122 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var104 = 0; i$var104 < length$eventsMeasured.length; i$var104 += 1)
				logProbability$var122[((i$var104 - 0) / 1)] = new double[((((length$eventsMeasured[i$var104] - 1) - 1) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample126
		{
			logProbability$sample126 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var104 = 0; i$var104 < length$eventsMeasured.length; i$var104 += 1)
				logProbability$sample126[((i$var104 - 0) / 1)] = new double[((((length$eventsMeasured[i$var104] - 1) - 1) / 1) + 1)];
		}
		
		// Constructor for logProbability$var154
		{
			logProbability$var154 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var136 = 0; i$var136 < length$eventsMeasured.length; i$var136 += 1)
				logProbability$var154[((i$var136 - 0) / 1)] = new double[((((length$eventsMeasured[i$var136] - 1) - 1) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample159
		{
			logProbability$sample159 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var136 = 0; i$var136 < length$eventsMeasured.length; i$var136 += 1)
				logProbability$sample159[((i$var136 - 0) / 1)] = new double[((((length$eventsMeasured[i$var136] - 1) - 1) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
						double[] var42 = m[var41];
						if(!fixedFlag$sample42)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var42);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
						double[] var56 = bias[var55];
						if(!fixedFlag$sample57)
							DistributionSampling.sampleDirichlet(RNG$1, v2, noEvents, var56);
					}
			}
		);
		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
						int[] var88 = st[i$var87];
						if(!fixedFlag$sample95)
							var88[0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState], noStates);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
						int[] var116 = st[i$var104];
						for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
							if(!fixedFlag$sample126)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var104][(j$var115 - 1)]], noStates);
						}
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$index$i$var136, int forEnd$index$i$var136, int threadID$index$i$var136, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var136 = forStart$index$i$var136; index$i$var136 < forEnd$index$i$var136; index$i$var136 += 1) {
						int i$var136 = index$i$var136;
						int threadID$i$var136 = threadID$index$i$var136;
						int[] var150 = events[i$var136];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 1, length$eventsMeasured[i$var136], 1,
							(int forStart$j$var149, int forEnd$j$var149, int threadID$j$var149, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var149 = forStart$j$var149; j$var149 < forEnd$j$var149; j$var149 += 1) {
										if(!fixedFlag$sample159)
											var150[j$var149] = (DistributionSampling.sampleCategorical(RNG$2, bias[st[i$var136][j$var149]], noEvents) + 1);
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
			(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
						double[] var42 = m[var41];
						if(!fixedFlag$sample42)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var42);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
						double[] var56 = bias[var55];
						if(!fixedFlag$sample57)
							DistributionSampling.sampleDirichlet(RNG$1, v2, noEvents, var56);
					}
			}
		);
		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
						// Create local copy of variable probabilities.
						double[] cv$distribution$sample95 = distribution$sample95[((i$var87 - 0) / 1)];
						double[] var90 = m[initialState];
						for(int index$var91 = 0; index$var91 < noStates; index$var91 += 1) {
							// Probability for this value
							double cv$value = (((0.0 <= index$var91) && (index$var91 < noStates))?var90[index$var91]:0.0);
							if(!fixedFlag$sample95)
								// Save the probability of each value
								cv$distribution$sample95[index$var91] = cv$value;
						}
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
						for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
							// Create local copy of variable probabilities.
							double[] cv$distribution$sample126 = distribution$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)];
							for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1) {
								if(!fixedFlag$sample126)
									// Zero the probability of each value
									cv$distribution$sample126[index$var122] = 0.0;
							}
							
							// Iterate through possible values for var122's arguments.
							// 
							// Enumerating the possible arguments for Categorical 122.
							if(fixedFlag$sample95) {
								for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
									if((i$var87 == i$var104)) {
										if((0 == (j$var115 - 1))) {
											for(int var41 = 0; var41 < noStates; var41 += 1) {
												if((var41 == st[i$var104][(j$var115 - 1)])) {
													{
														if(!fixedFlag$sample126) {
															double[] var121 = m[st[i$var104][(j$var115 - 1)]];
															for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1)
																// Save the probability of each value
																cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] + (1.0 * (((0.0 <= index$var122) && (index$var122 < noStates))?var121[index$var122]:0.0)));
														}
													}
												}
											}
										}
									}
								}
							} else {
								for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 91.
										for(int index$sample95$3 = 0; index$sample95$3 < noStates; index$sample95$3 += 1) {
											int distributionTempVariable$var92$5 = index$sample95$3;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample95Value4 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$3]);
											if((i$var87 == i$var104)) {
												if((0 == (j$var115 - 1))) {
													for(int var41 = 0; var41 < noStates; var41 += 1) {
														if((var41 == st[i$var104][(j$var115 - 1)])) {
															{
																if(!fixedFlag$sample126) {
																	double[] var121 = m[st[i$var104][(j$var115 - 1)]];
																	for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1)
																		// Save the probability of each value
																		cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] + (cv$probabilitySample95Value4 * (((0.0 <= index$var122) && (index$var122 < noStates))?var121[index$var122]:0.0)));
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							
							// Enumerating the possible arguments for Categorical 122.
							if(fixedFlag$sample126) {
								for(int index$i$9_1 = 0; index$i$9_1 < samples; index$i$9_1 += 1) {
									for(int index$j$9_2 = 1; index$j$9_2 < length$eventsMeasured[index$i$9_1]; index$j$9_2 += 1) {
										if((index$i$9_1 == i$var104)) {
											if((index$j$9_2 == (j$var115 - 1))) {
												for(int var41 = 0; var41 < noStates; var41 += 1) {
													if((var41 == st[i$var104][(j$var115 - 1)])) {
														{
															if(!fixedFlag$sample126) {
																double[] var121 = m[st[i$var104][(j$var115 - 1)]];
																for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1)
																	// Save the probability of each value
																	cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] + (1.0 * (((0.0 <= index$var122) && (index$var122 < noStates))?var121[index$var122]:0.0)));
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
											// Enumerating the possible outputs of Categorical 122.
											for(int index$sample126$12 = 0; index$sample126$12 < noStates; index$sample126$12 += 1) {
												int distributionTempVariable$var123$14 = index$sample126$12;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample126Value13 = (1.0 * distribution$sample126[((index$i$10 - 0) / 1)][((index$j$11 - 1) / 1)][index$sample126$12]);
												if((index$i$10 == i$var104)) {
													if((index$j$11 == (j$var115 - 1))) {
														for(int var41 = 0; var41 < noStates; var41 += 1) {
															if((var41 == st[i$var104][(j$var115 - 1)])) {
																{
																	if(!fixedFlag$sample126) {
																		double[] var121 = m[st[i$var104][(j$var115 - 1)]];
																		for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1)
																			// Save the probability of each value
																			cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] + (cv$probabilitySample126Value13 * (((0.0 <= index$var122) && (index$var122 < noStates))?var121[index$var122]:0.0)));
																	}
																}
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
							double cv$var122$sum = 0.0;
							for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1) {
								if(!fixedFlag$sample126)
									// sum the probability of each value
									cv$var122$sum = (cv$var122$sum + cv$distribution$sample126[index$var122]);
							}
							for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1) {
								if(!fixedFlag$sample126)
									// Normalise the probability of each value
									cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] / cv$var122$sum);
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
			(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
						double[] var42 = m[var41];
						if(!fixedFlag$sample42)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var42);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
						double[] var56 = bias[var55];
						if(!fixedFlag$sample57)
							DistributionSampling.sampleDirichlet(RNG$1, v2, noEvents, var56);
					}
			}
		);
		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
						int[] var88 = st[i$var87];
						if(!fixedFlag$sample95)
							var88[0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState], noStates);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
						int[] var116 = st[i$var104];
						for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
							if(!fixedFlag$sample126)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var104][(j$var115 - 1)]], noStates);
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
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
							if(!fixedFlag$sample42)
								sample42(var41, threadID$var41, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
							if(!fixedFlag$sample57)
								sample57(var55, threadID$var55, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample78)
				sample78();
			if(!fixedFlag$sample80)
				sample80();
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
							if(!fixedFlag$sample95)
								sample95(i$var87, threadID$i$var87, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
							for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
								if(!fixedFlag$sample126)
									sample126(i$var104, j$var115, threadID$i$var104, RNG$1);
							}
						}
				}
			);
		}
		// Infer the samples in reverse chronological order.
		else {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
							for(int j$var115 = (length$eventsMeasured[i$var104] - ((((length$eventsMeasured[i$var104] - 1) - 1) % 1) + 1)); j$var115 >= ((1 - 1) + 1); j$var115 -= 1) {
								if(!fixedFlag$sample126)
									sample126(i$var104, j$var115, threadID$i$var104, RNG$1);
							}
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
							if(!fixedFlag$sample95)
								sample95(i$var87, threadID$i$var87, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample80)
				sample80();
			if(!fixedFlag$sample78)
				sample78();
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
							if(!fixedFlag$sample57)
								sample57(var55, threadID$var55, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
							if(!fixedFlag$sample42)
								sample42(var41, threadID$var41, RNG$1);
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
			(int forStart$var14, int forEnd$var14, int threadID$var14, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var14 = forStart$var14; var14 < forEnd$var14; var14 += 1)
						v[var14] = 0.1;
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noEvents, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
						v2[var27] = 0.1;
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
		logProbability$var30 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample42)
			logProbability$var42 = 0.0;
		logProbability$var44 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample57)
			logProbability$var56 = 0.0;
		logProbability$var74 = 0.0;
		if(!fixedProbFlag$sample78)
			logProbability$weights = 0.0;
		logProbability$var76 = 0.0;
		if(!fixedProbFlag$sample80)
			logProbability$initialState = 0.0;
		for(int i$var87 = 0; i$var87 < samples; i$var87 += 1)
			logProbability$var91[((i$var87 - 0) / 1)] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample95) {
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1)
				logProbability$sample95[((i$var87 - 0) / 1)] = 0.0;
		}
		for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
			for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
				logProbability$var122[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = 0.0;
		}
		if(!fixedProbFlag$sample126) {
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
					logProbability$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = 0.0;
			}
		}
		for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
			for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1)
				logProbability$var154[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = 0.0;
		}
		logProbability$events = 0.0;
		if(!fixedProbFlag$sample159) {
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1)
					logProbability$sample159[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = 0.0;
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
		if(fixedFlag$sample42)
			logProbabilityValue$sample42();
		if(fixedFlag$sample57)
			logProbabilityValue$sample57();
		if(fixedFlag$sample78)
			logProbabilityValue$sample78();
		if(fixedFlag$sample80)
			logProbabilityValue$sample80();
		logProbabilityValue$sample159();
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
		logProbabilityValue$sample42();
		logProbabilityValue$sample57();
		logProbabilityValue$sample78();
		logProbabilityValue$sample80();
		logProbabilityDistribution$sample95();
		logProbabilityDistribution$sample126();
		logProbabilityDistribution$sample159();
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
		logProbabilityValue$sample42();
		logProbabilityValue$sample57();
		logProbabilityValue$sample78();
		logProbabilityValue$sample80();
		logProbabilityValue$sample95();
		logProbabilityValue$sample126();
		logProbabilityValue$sample159();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
						double[] var42 = m[var41];
						if(!fixedFlag$sample42)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var42);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
						double[] var56 = bias[var55];
						if(!fixedFlag$sample57)
							DistributionSampling.sampleDirichlet(RNG$1, v2, noEvents, var56);
					}
			}
		);
		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
						int[] var88 = st[i$var87];
						if(!fixedFlag$sample95)
							var88[0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState], noStates);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
						int[] var116 = st[i$var104];
						for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
							if(!fixedFlag$sample126)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var104][(j$var115 - 1)]], noStates);
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
		     + "model HMM_Mk2Dist(int[][] eventsMeasured, int noStates, int noEvents) {\n"
		     + "        \n"
		     + "        // Construct arrays describing the probability of a move from 1 state to another.\n"
		     + "        double[] v = new double[noStates] <~ 0.1;\n"
		     + "        double[] v2 = new double[noEvents] <~ 0.1;\n"
		     + "        double[][] m = dirichlet(v).sample(noStates);\n"
		     + "        \n"
		     + "        // Construct the bias for each webpage.\n"
		     + "        double[][] bias = dirichlet(v2).sample(noStates);\n"
		     + "\n"
		     + "        // Determine how many samples the model will need to produce.\n"
		     + "        int samples = eventsMeasured.length;\n"
		     + "        \n"
		     + "        // Allocate space for the state, i.e. which webpage we are going to trigger an event on.\n"
		     + "        int[][] st = new int[samples][];\n"
		     + "        for(int i:[0 .. samples)){\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            st[i] = new int[streamLength];\n"
		     + "        }\n"
		     + "\n"
		     + "        // Set the initial state by sampling from a categorical with learnt weightings.\n"
		     + "        double[] weights = dirichlet(v).sample();\n"
		     + "        int initialState = categorical(weights).sample();\n"
		     + "        for(int i:[0..samples)) {\n"
		     + "            st[i][0] = categorical(m[initialState]).sampleDistribution();\n"
		     + "        }\n"
		     + "\n"
		     + "        //Determine the remaining states based on the previous state.\n"
		     + "        for(int i:[0 .. samples)){\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            for(int j:[1..streamLength)){\n"
		     + "                st[i][j] = categorical(m[st[i][j-1]]).sampleDistribution();\n"
		     + "            }\n"
		     + "        }\n"
		     + "            \n"
		     + "        //Generate each event.\n"
		     + "        int[][] events = new int[samples][];\n"
		     + "        for(int i:[0 .. samples)) {\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            events[i] = new int[streamLength];\n"
		     + "            for(int j:[1..streamLength)){\n"
		     + "                events[i][j] = categorical(bias[st[i][j]]).sample() + 1;\n"
		     + "            }\n"
		     + "        }\n"
		     + "\n"
		     + "        //Tie the values of the flips to the values we have measured.\n"
		     + "        events.observe(eventsMeasured);\n"
		     + "}\n"
		     + "";
	}
}