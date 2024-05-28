package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMM_Mk2Dist$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMM_Mk2Dist$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] bias;
	private double[] cv$distributionAccumulator$var128;
	private double[] cv$distributionAccumulator$var96;
	private double[] cv$var129$stateProbabilityGlobal;
	private double[] cv$var46$countGlobal;
	private double[] cv$var60$countGlobal;
	private double[] cv$var80$countGlobal;
	private double[] cv$var82$stateProbabilityGlobal;
	private double[] cv$var97$stateProbabilityGlobal;
	private double[][] distribution$sample103;
	private double[][][] distribution$sample136;
	private int[][] events;
	private int[][] eventsMeasured;
	private boolean fixedFlag$sample103 = false;
	private boolean fixedFlag$sample136 = false;
	private boolean fixedFlag$sample171 = false;
	private boolean fixedFlag$sample47 = false;
	private boolean fixedFlag$sample62 = false;
	private boolean fixedFlag$sample86 = false;
	private boolean fixedFlag$sample88 = false;
	private boolean fixedProbFlag$sample103 = false;
	private boolean fixedProbFlag$sample136 = false;
	private boolean fixedProbFlag$sample171 = false;
	private boolean fixedProbFlag$sample47 = false;
	private boolean fixedProbFlag$sample62 = false;
	private boolean fixedProbFlag$sample86 = false;
	private boolean fixedProbFlag$sample88 = false;
	private int initialState;
	private int[] length$eventsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$events;
	private double logProbability$initialState;
	private double logProbability$m;
	private double logProbability$st;
	private double logProbability$var128;
	private double logProbability$var129;
	private double logProbability$var161;
	private double logProbability$var162;
	private double logProbability$var34;
	private double logProbability$var46;
	private double logProbability$var48;
	private double logProbability$var60;
	private double logProbability$var79;
	private double logProbability$var81;
	private double logProbability$var96;
	private double logProbability$var97;
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

	public HMM_Mk2Dist$SingleThreadCPU(ExecutionTarget target) {
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
		
		// Unset the fixed probability flag for sample 62 as it depends on bias.
		fixedProbFlag$sample62 = false;
		
		// Unset the fixed probability flag for sample 171 as it depends on bias.
		fixedProbFlag$sample171 = false;
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
		
		// Unset the fixed probability flag for sample 171 as it depends on events.
		fixedProbFlag$sample171 = false;
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
		
		// Should the probability of sample 136 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample136 = (fixedFlag$sample103 && fixedProbFlag$sample136);
		
		// Should the probability of sample 171 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample171 = (fixedFlag$sample103 && fixedProbFlag$sample171);
	}

	// Getter for fixedFlag$sample136.
	@Override
	public final boolean get$fixedFlag$sample136() {
		return fixedFlag$sample136;
	}

	// Setter for fixedFlag$sample136.
	@Override
	public final void set$fixedFlag$sample136(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample136 including if probabilities
		// need to be updated.
		fixedFlag$sample136 = cv$value;
		
		// Should the probability of sample 136 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample136 = (fixedFlag$sample136 && fixedProbFlag$sample136);
		
		// Should the probability of sample 171 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample171 = (fixedFlag$sample136 && fixedProbFlag$sample171);
	}

	// Getter for fixedFlag$sample171.
	@Override
	public final boolean get$fixedFlag$sample171() {
		return fixedFlag$sample171;
	}

	// Setter for fixedFlag$sample171.
	@Override
	public final void set$fixedFlag$sample171(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample171 including if probabilities
		// need to be updated.
		fixedFlag$sample171 = cv$value;
		
		// Should the probability of sample 171 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample171 = (fixedFlag$sample171 && fixedProbFlag$sample171);
	}

	// Getter for fixedFlag$sample47.
	@Override
	public final boolean get$fixedFlag$sample47() {
		return fixedFlag$sample47;
	}

	// Setter for fixedFlag$sample47.
	@Override
	public final void set$fixedFlag$sample47(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample47 including if probabilities
		// need to be updated.
		fixedFlag$sample47 = cv$value;
		
		// Should the probability of sample 47 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample47 = (fixedFlag$sample47 && fixedProbFlag$sample47);
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample103 = (fixedFlag$sample47 && fixedProbFlag$sample103);
		
		// Should the probability of sample 136 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample136 = (fixedFlag$sample47 && fixedProbFlag$sample136);
	}

	// Getter for fixedFlag$sample62.
	@Override
	public final boolean get$fixedFlag$sample62() {
		return fixedFlag$sample62;
	}

	// Setter for fixedFlag$sample62.
	@Override
	public final void set$fixedFlag$sample62(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample62 including if probabilities
		// need to be updated.
		fixedFlag$sample62 = cv$value;
		
		// Should the probability of sample 62 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample62 = (fixedFlag$sample62 && fixedProbFlag$sample62);
		
		// Should the probability of sample 171 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample171 = (fixedFlag$sample62 && fixedProbFlag$sample171);
	}

	// Getter for fixedFlag$sample86.
	@Override
	public final boolean get$fixedFlag$sample86() {
		return fixedFlag$sample86;
	}

	// Setter for fixedFlag$sample86.
	@Override
	public final void set$fixedFlag$sample86(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample86 including if probabilities
		// need to be updated.
		fixedFlag$sample86 = cv$value;
		
		// Should the probability of sample 86 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample86 = (fixedFlag$sample86 && fixedProbFlag$sample86);
		
		// Should the probability of sample 88 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample88 = (fixedFlag$sample86 && fixedProbFlag$sample88);
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
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample103 = (fixedFlag$sample88 && fixedProbFlag$sample103);
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
		
		// Unset the fixed probability flag for sample 88 as it depends on initialState.
		fixedProbFlag$sample88 = false;
		
		// Unset the fixed probability flag for sample 103 as it depends on initialState.
		fixedProbFlag$sample103 = false;
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
		
		// Unset the fixed probability flag for sample 47 as it depends on m.
		fixedProbFlag$sample47 = false;
		
		// Unset the fixed probability flag for sample 103 as it depends on m.
		fixedProbFlag$sample103 = false;
		
		// Unset the fixed probability flag for sample 136 as it depends on m.
		fixedProbFlag$sample136 = false;
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
		
		// Unset the fixed probability flag for sample 103 as it depends on st.
		fixedProbFlag$sample103 = false;
		
		// Unset the fixed probability flag for sample 136 as it depends on st.
		fixedProbFlag$sample136 = false;
		
		// Unset the fixed probability flag for sample 171 as it depends on st.
		fixedProbFlag$sample171 = false;
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
		
		// Unset the fixed probability flag for sample 86 as it depends on weights.
		fixedProbFlag$sample86 = false;
		
		// Unset the fixed probability flag for sample 88 as it depends on weights.
		fixedProbFlag$sample88 = false;
	}

	// Calculate the probability of the samples represented by sample103 using probability
	// distributions.
	private final void logProbabilityDistribution$sample103() {
		// Determine if we need to calculate the values for sample task 103 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample103) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample103) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Copy of index so that its values can be safely substituted
					int index$i$1 = i$var92;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[i$var92][0];
						{
							{
								double[] var95 = m[initialState];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var95.length))?Math.log(var95[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
				logProbability$var96 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				logProbability$var97 = cv$accumulator;
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(fixedFlag$sample103)
					// Update the variable probability
					logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample103)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample103 = ((fixedFlag$sample103 && fixedFlag$sample47) && fixedFlag$sample88);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var97;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var96 = cv$rvAccumulator;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample103)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample103)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample136 using probability
	// distributions.
	private final void logProbabilityDistribution$sample136() {
		// Determine if we need to calculate the values for sample task 136 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample136) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample136) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
					for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// Look for paths between the variable and the sample task 136 including any distribution
						// values.
						// 
						// Copy of index so that its values can be safely substituted
						int index$j$1 = j$var121;
						
						// Copy of index so that its values can be safely substituted
						int index$i$2 = i$var109;
						{
							// The sample value to calculate the probability of generating
							int cv$sampleValue = st[i$var109][j$var121];
							
							// Enumerating the possible arguments for Categorical 128.
							if(fixedFlag$sample103) {
								for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
									if((i$var92 == i$var109)) {
										if((0 == (j$var121 - 1))) {
											for(int var45 = 0; var45 < noStates; var45 += 1) {
												if((var45 == st[i$var109][(j$var121 - 1)])) {
													{
														double[] var127 = m[st[i$var109][(j$var121 - 1)]];
														
														// Store the value of the function call, so the function call is only made once.
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var127.length))?Math.log(var127[cv$sampleValue]):Double.NEGATIVE_INFINITY));
														
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
								for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 96.
										for(int index$sample103$6 = 0; index$sample103$6 < noStates; index$sample103$6 += 1) {
											int distributionTempVariable$var97$8 = index$sample103$6;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample103Value7 = (1.0 * distribution$sample103[((i$var92 - 0) / 1)][index$sample103$6]);
											int traceTempVariable$var126$9_1 = distributionTempVariable$var97$8;
											if((i$var92 == i$var109)) {
												if((0 == (j$var121 - 1))) {
													for(int var45 = 0; var45 < noStates; var45 += 1) {
														if((var45 == traceTempVariable$var126$9_1)) {
															{
																double[] var127 = m[traceTempVariable$var126$9_1];
																
																// Store the value of the function call, so the function call is only made once.
																double cv$weightedProbability = (Math.log(cv$probabilitySample103Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var127.length))?Math.log(var127[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																
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
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample103Value7);
															}
														}
													}
												}
											}
										}
									}
								}
							}
							
							// Enumerating the possible arguments for Categorical 128.
							if((index$i$2 == i$var109)) {
								if((index$j$1 == (j$var121 - 1))) {
									for(int var45 = 0; var45 < noStates; var45 += 1) {
										if((var45 == st[i$var109][(j$var121 - 1)])) {
											{
												double[] var127 = m[st[i$var109][(j$var121 - 1)]];
												
												// Store the value of the function call, so the function call is only made once.
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var127.length))?Math.log(var127[cv$sampleValue]):Double.NEGATIVE_INFINITY));
												
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
							if(fixedFlag$sample136) {
								for(int index$i$13_1 = 0; index$i$13_1 < samples; index$i$13_1 += 1) {
									for(int index$j$13_2 = 1; index$j$13_2 < length$eventsMeasured[index$i$13_1]; index$j$13_2 += 1) {
										if((index$i$13_1 == i$var109)) {
											if((index$j$13_2 == (j$var121 - 1))) {
												for(int var45 = 0; var45 < noStates; var45 += 1) {
													if((var45 == st[i$var109][(j$var121 - 1)])) {
														{
															double[] var127 = m[st[i$var109][(j$var121 - 1)]];
															
															// Store the value of the function call, so the function call is only made once.
															double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var127.length))?Math.log(var127[cv$sampleValue]):Double.NEGATIVE_INFINITY));
															
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
										if(!((index$j$15 == index$j$1) && (index$i$14 == index$i$2))) {
											// Enumerating the possible outputs of Categorical 128.
											for(int index$sample136$16 = 0; index$sample136$16 < noStates; index$sample136$16 += 1) {
												int distributionTempVariable$var129$18 = index$sample136$16;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample136Value17 = (1.0 * distribution$sample136[((index$i$14 - 0) / 1)][((index$j$15 - 1) / 1)][index$sample136$16]);
												int traceTempVariable$var126$19_1 = distributionTempVariable$var129$18;
												if((index$i$14 == i$var109)) {
													if((index$j$15 == (j$var121 - 1))) {
														for(int var45 = 0; var45 < noStates; var45 += 1) {
															if((var45 == traceTempVariable$var126$19_1)) {
																{
																	double[] var127 = m[traceTempVariable$var126$19_1];
																	
																	// Store the value of the function call, so the function call is only made once.
																	double cv$weightedProbability = (Math.log(cv$probabilitySample136Value17) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var127.length))?Math.log(var127[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																	
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
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample136Value17);
																}
															}
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
					}
				}
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var128 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				logProbability$var129 = cv$accumulator;
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(fixedFlag$sample136)
					// Update the variable probability
					logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample136)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample136 = ((fixedFlag$sample136 && fixedFlag$sample47) && fixedFlag$sample103);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var129;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var128 = cv$rvAccumulator;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample136)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample136)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample171 using probability
	// distributions.
	private final void logProbabilityDistribution$sample171() {
		// Determine if we need to calculate the values for sample task 171 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample171) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
				for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Look for paths between the variable and the sample task 171 including any distribution
					// values.
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = (events[i$var142][j$var156] - 1);
						
						// Enumerating the possible arguments for Categorical 161.
						if(fixedFlag$sample103) {
							for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
								if((i$var92 == i$var142)) {
									if((0 == j$var156)) {
										for(int var59 = 0; var59 < noStates; var59 += 1) {
											if((var59 == st[i$var142][j$var156])) {
												{
													double[] var160 = bias[st[i$var142][j$var156]];
													
													// Store the value of the function call, so the function call is only made once.
													double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var160.length))?Math.log(var160[cv$sampleValue]):Double.NEGATIVE_INFINITY));
													
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
							for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
								if(true) {
									// Enumerating the possible outputs of Categorical 96.
									for(int index$sample103$4 = 0; index$sample103$4 < noStates; index$sample103$4 += 1) {
										int distributionTempVariable$var97$6 = index$sample103$4;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample103Value5 = (1.0 * distribution$sample103[((i$var92 - 0) / 1)][index$sample103$4]);
										int traceTempVariable$var159$7_1 = distributionTempVariable$var97$6;
										if((i$var92 == i$var142)) {
											if((0 == j$var156)) {
												for(int var59 = 0; var59 < noStates; var59 += 1) {
													if((var59 == traceTempVariable$var159$7_1)) {
														{
															double[] var160 = bias[traceTempVariable$var159$7_1];
															
															// Store the value of the function call, so the function call is only made once.
															double cv$weightedProbability = (Math.log(cv$probabilitySample103Value5) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var160.length))?Math.log(var160[cv$sampleValue]):Double.NEGATIVE_INFINITY));
															
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
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample103Value5);
														}
													}
												}
											}
										}
									}
								}
							}
						}
						
						// Enumerating the possible arguments for Categorical 161.
						if(fixedFlag$sample136) {
							for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
								for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
									if((i$var109 == i$var142)) {
										if((j$var121 == j$var156)) {
											for(int var59 = 0; var59 < noStates; var59 += 1) {
												if((var59 == st[i$var142][j$var156])) {
													{
														double[] var160 = bias[st[i$var142][j$var156]];
														
														// Store the value of the function call, so the function call is only made once.
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var160.length))?Math.log(var160[cv$sampleValue]):Double.NEGATIVE_INFINITY));
														
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
							for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
								for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 128.
										for(int index$sample136$13 = 0; index$sample136$13 < noStates; index$sample136$13 += 1) {
											int distributionTempVariable$var129$15 = index$sample136$13;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample136Value14 = (1.0 * distribution$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)][index$sample136$13]);
											int traceTempVariable$var159$16_1 = distributionTempVariable$var129$15;
											if((i$var109 == i$var142)) {
												if((j$var121 == j$var156)) {
													for(int var59 = 0; var59 < noStates; var59 += 1) {
														if((var59 == traceTempVariable$var159$16_1)) {
															{
																double[] var160 = bias[traceTempVariable$var159$16_1];
																
																// Store the value of the function call, so the function call is only made once.
																double cv$weightedProbability = (Math.log(cv$probabilitySample136Value14) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var160.length))?Math.log(var160[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																
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
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample136Value14);
															}
														}
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
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var161 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var162 = cv$accumulator;
			
			// Update the variable probability
			logProbability$events = (logProbability$events + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample171 = (((fixedFlag$sample171 && fixedFlag$sample62) && fixedFlag$sample103) && fixedFlag$sample136);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var162;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var161 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$events = (logProbability$events + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
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
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Copy of index so that its values can be safely substituted
				int index$i$1 = i$var92;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[i$var92][0];
					{
						{
							double[] var95 = m[initialState];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var95.length))?Math.log(var95[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			logProbability$var96 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var97 = cv$accumulator;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample103)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample103 = ((fixedFlag$sample103 && fixedFlag$sample47) && fixedFlag$sample88);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var97;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var96 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample103)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample136 using sampled
	// values.
	private final void logProbabilityValue$sample136() {
		// Determine if we need to calculate the values for sample task 136 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample136) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Copy of index so that its values can be safely substituted
					int index$j$1 = j$var121;
					
					// Copy of index so that its values can be safely substituted
					int index$i$2 = i$var109;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[i$var109][j$var121];
						{
							{
								double[] var127 = m[st[i$var109][(j$var121 - 1)]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var127.length))?Math.log(var127[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
			logProbability$var128 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var129 = cv$accumulator;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample136)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample136 = ((fixedFlag$sample136 && fixedFlag$sample47) && fixedFlag$sample103);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var129;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var128 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample136)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample171 using sampled
	// values.
	private final void logProbabilityValue$sample171() {
		// Determine if we need to calculate the values for sample task 171 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample171) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
				for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = (events[i$var142][j$var156] - 1);
						{
							{
								double[] var160 = bias[st[i$var142][j$var156]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var160.length))?Math.log(var160[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
			logProbability$var161 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var162 = cv$accumulator;
			
			// Update the variable probability
			logProbability$events = (logProbability$events + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample171 = (((fixedFlag$sample171 && fixedFlag$sample62) && fixedFlag$sample103) && fixedFlag$sample136);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var162;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var161 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$events = (logProbability$events + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample47 using sampled
	// values.
	private final void logProbabilityValue$sample47() {
		// Determine if we need to calculate the values for sample task 47 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample47) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var45 = 0; var45 < noStates; var45 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double[] cv$sampleValue = m[var45];
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
			logProbability$var34 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var46 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample47 = fixedFlag$sample47;
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
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample62 using sampled
	// values.
	private final void logProbabilityValue$sample62() {
		// Determine if we need to calculate the values for sample task 62 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample62) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var59 = 0; var59 < noStates; var59 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double[] cv$sampleValue = bias[var59];
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
			logProbability$var48 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var60 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample62)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample62 = fixedFlag$sample62;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var60;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var48 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$bias = (logProbability$bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample62)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample86 using sampled
	// values.
	private final void logProbabilityValue$sample86() {
		// Determine if we need to calculate the values for sample task 86 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample86) {
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
			logProbability$var79 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$weights = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample86)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample86 = fixedFlag$sample86;
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
			logProbability$var79 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample86)
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
			logProbability$var81 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$initialState = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample88 = (fixedFlag$sample88 && fixedFlag$sample86);
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
			logProbability$var81 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 103 drawn from Categorical 96. Inference was performed using variable
	// marginalization.
	private final void sample103(int i$var92) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		
		// Copy of index so that its values can be safely substituted
		int index$i$1 = i$var92;
		{
			// variable marginalization
			cv$noStates = Math.max(cv$noStates, noStates);
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var97$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Copy of index so that its values can be safely substituted
			int index$i$2 = i$var92;
			
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
				double[] cv$temp$0$var95;
				{
					// Constructing a random variable input for use later.
					double[] var95 = m[initialState];
					cv$temp$0$var95 = var95;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var95.length))?Math.log(cv$temp$0$var95[cv$currentValue]):Double.NEGATIVE_INFINITY));
				
				// Processing random variable 128.
				{
					// Looking for a path between Sample 103 and consumer Categorical 128.
					{
						int traceTempVariable$var126$3_1 = cv$currentValue;
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((i$var92 == i$var109)) {
								for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
									if((0 == (j$var121 - 1))) {
										if(fixedFlag$sample136) {
											// Processing sample task 136 of consumer random variable null.
											{
												// Copy of index so that its values can be safely substituted
												int index$j$5 = j$var121;
												
												// Copy of index so that its values can be safely substituted
												int index$i$6 = i$var109;
												
												// Set an accumulator to sum the probabilities for each possible configuration of
												// inputs.
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												
												// Set an accumulator to record the consumer distributions not seen. Initially set
												// to 1 as seen values will be deducted from this value.
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													// Enumerating the possible arguments for the variable Categorical 128 which is consuming
													// the output of Sample task 103.
													for(int var45 = 0; var45 < noStates; var45 += 1) {
														if((var45 == traceTempVariable$var126$3_1)) {
															{
																{
																	double[] cv$temp$1$var127;
																	{
																		// Constructing a random variable input for use later.
																		double[] var127 = m[traceTempVariable$var126$3_1];
																		cv$temp$1$var127 = var127;
																	}
																	
																	// Record the probability of sample task 136 generating output with current configuration.
																	if(((Math.log(1.0) + (((0.0 <= st[i$var109][j$var121]) && (st[i$var109][j$var121] < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[st[i$var109][j$var121]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var109][j$var121]) && (st[i$var109][j$var121] < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[st[i$var109][j$var121]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var109][j$var121]) && (st[i$var109][j$var121] < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[st[i$var109][j$var121]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var109][j$var121]) && (st[i$var109][j$var121] < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[st[i$var109][j$var121]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var109][j$var121]) && (st[i$var109][j$var121] < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[st[i$var109][j$var121]]):Double.NEGATIVE_INFINITY)));
																	}
																	
																	// Recorded the probability of reaching sample task 136 with the current configuration.
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
				
				// Processing random variable 161.
				{
					// Looking for a path between Sample 103 and consumer Categorical 161.
					{
						int traceTempVariable$var159$9_1 = cv$currentValue;
						for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
							if((i$var92 == i$var142)) {
								for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
									if((0 == j$var156)) {
										// Processing sample task 171 of consumer random variable null.
										{
											// Set an accumulator to sum the probabilities for each possible configuration of
											// inputs.
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											
											// Set an accumulator to record the consumer distributions not seen. Initially set
											// to 1 as seen values will be deducted from this value.
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												// Enumerating the possible arguments for the variable Categorical 161 which is consuming
												// the output of Sample task 103.
												for(int var59 = 0; var59 < noStates; var59 += 1) {
													if((var59 == traceTempVariable$var159$9_1)) {
														{
															{
																double[] cv$temp$2$var160;
																{
																	// Constructing a random variable input for use later.
																	double[] var160 = bias[traceTempVariable$var159$9_1];
																	cv$temp$2$var160 = var160;
																}
																
																// Record the probability of sample task 171 generating output with current configuration.
																if(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$2$var160.length))?Math.log(cv$temp$2$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$2$var160.length))?Math.log(cv$temp$2$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$2$var160.length))?Math.log(cv$temp$2$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$2$var160.length))?Math.log(cv$temp$2$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$2$var160.length))?Math.log(cv$temp$2$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)));
																}
																
																// Recorded the probability of reaching sample task 171 with the current configuration.
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
			
			// Processing random variable 128.
			{
				// Looking for a path between Sample 103 and consumer Categorical 128.
				{
					int traceTempVariable$var126$13_1 = cv$currentValue;
					for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
						if((i$var92 == i$var109)) {
							for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
								if((0 == (j$var121 - 1))) {
									if(!fixedFlag$sample136) {
										// Processing sample task 136 of consumer random variable null.
										{
											// Copy of index so that its values can be safely substituted
											int index$j$15 = j$var121;
											
											// Copy of index so that its values can be safely substituted
											int index$i$16 = i$var109;
											
											// A local array to hold the accumulated distributions of the sample tasks for each
											// configuration of distributions.
											double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var128;
											
											// Zero all the elements in the distribution accumulator
											for(int cv$i = 0; cv$i < noStates; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											
											// Zero an accumulator to track the probabilities reached.
											double cv$reachedDistributionProbability = 0.0;
											
											// Enumerating the possible arguments for the variable Categorical 128 which is consuming
											// the output of Sample task 103.
											for(int var45 = 0; var45 < noStates; var45 += 1) {
												if((var45 == traceTempVariable$var126$13_1)) {
													{
														// Declare and zero an accumulator for tracking the reached source probability space.
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															// Add the probability of this argument configuration.
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														double[] cv$temp$3$var127;
														{
															// Constructing a random variable input for use later.
															double[] var127 = m[traceTempVariable$var126$13_1];
															cv$temp$3$var127 = var127;
														}
														
														// The probability of reaching the consumer with this set of consumer arguments
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
														
														// Record the reached distribution.
														cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
														
														// Add the current distribution to the distribution accumulator.
														DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$3$var127);
													}
												}
											}
											
											// A local copy of the samples' distribution.
											double[] cv$sampleDistribution = distribution$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)];
											
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
		double[] cv$localProbability = distribution$sample103[((i$var92 - 0) / 1)];
		
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
	// by sample task 136 drawn from Categorical 128. Inference was performed using variable
	// marginalization.
	private final void sample136(int i$var109, int j$var121) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		
		// Exploring all the possible state counts for random variable 128.
		// 
		// Copy of index so that its values can be safely substituted
		int index$j$1 = j$var121;
		
		// Copy of index so that its values can be safely substituted
		int index$i$2 = i$var109;
		
		// Enumerating the possible arguments for Categorical 128.
		if(fixedFlag$sample103) {
			for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
				if((i$var92 == i$var109)) {
					if((0 == (j$var121 - 1))) {
						for(int var45 = 0; var45 < noStates; var45 += 1) {
							if((var45 == st[i$var109][(j$var121 - 1)]))
								// variable marginalization
								cv$noStates = Math.max(cv$noStates, noStates);
						}
					}
				}
			}
		} else {
			for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
				if(true) {
					// Enumerating the possible outputs of Categorical 96.
					for(int index$sample103$5 = 0; index$sample103$5 < noStates; index$sample103$5 += 1) {
						int distributionTempVariable$var97$7 = index$sample103$5;
						
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample103Value6 = (1.0 * distribution$sample103[((i$var92 - 0) / 1)][index$sample103$5]);
						int traceTempVariable$var126$8_1 = distributionTempVariable$var97$7;
						if((i$var92 == i$var109)) {
							if((0 == (j$var121 - 1))) {
								for(int var45 = 0; var45 < noStates; var45 += 1) {
									if((var45 == traceTempVariable$var126$8_1))
										// variable marginalization
										cv$noStates = Math.max(cv$noStates, noStates);
								}
							}
						}
					}
				}
			}
		}
		
		// Enumerating the possible arguments for Categorical 128.
		if((index$i$2 == i$var109)) {
			if((index$j$1 == (j$var121 - 1))) {
				for(int var45 = 0; var45 < noStates; var45 += 1) {
					if((var45 == st[i$var109][(j$var121 - 1)]))
						// variable marginalization
						cv$noStates = Math.max(cv$noStates, noStates);
				}
			}
		}
		if(fixedFlag$sample136) {
			for(int index$i$12_1 = 0; index$i$12_1 < samples; index$i$12_1 += 1) {
				for(int index$j$12_2 = 1; index$j$12_2 < length$eventsMeasured[index$i$12_1]; index$j$12_2 += 1) {
					if((index$i$12_1 == i$var109)) {
						if((index$j$12_2 == (j$var121 - 1))) {
							for(int var45 = 0; var45 < noStates; var45 += 1) {
								if((var45 == st[i$var109][(j$var121 - 1)]))
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
					if(!((index$j$14 == index$j$1) && (index$i$13 == index$i$2))) {
						// Enumerating the possible outputs of Categorical 128.
						for(int index$sample136$15 = 0; index$sample136$15 < noStates; index$sample136$15 += 1) {
							int distributionTempVariable$var129$17 = index$sample136$15;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample136Value16 = (1.0 * distribution$sample136[((index$i$13 - 0) / 1)][((index$j$14 - 1) / 1)][index$sample136$15]);
							int traceTempVariable$var126$18_1 = distributionTempVariable$var129$17;
							if((index$i$13 == i$var109)) {
								if((index$j$14 == (j$var121 - 1))) {
									for(int var45 = 0; var45 < noStates; var45 += 1) {
										if((var45 == traceTempVariable$var126$18_1))
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
		double[] cv$stateProbabilityLocal = cv$var129$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 128 creating
			// sample task 136.
			// Copy of index so that its values can be safely substituted
			int index$j$22 = j$var121;
			
			// Copy of index so that its values can be safely substituted
			int index$i$23 = i$var109;
			
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
			
			// Enumerating the possible arguments for Categorical 128.
			if(fixedFlag$sample103) {
				for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
					if((i$var92 == i$var109)) {
						if((0 == (j$var121 - 1))) {
							for(int var45 = 0; var45 < noStates; var45 += 1) {
								if((var45 == st[i$var109][(j$var121 - 1)])) {
									// Record the reached probability density.
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
									double[] cv$temp$0$var127;
									{
										// Constructing a random variable input for use later.
										double[] var127 = m[st[i$var109][(j$var121 - 1)]];
										cv$temp$0$var127 = var127;
									}
									
									// An accumulator to allow the value for each distribution to be constructed before
									// it is added to the index probabilities.
									double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var127.length))?Math.log(cv$temp$0$var127[cv$currentValue]):Double.NEGATIVE_INFINITY));
									
									// Processing random variable 128.
									{
										// Looking for a path between Sample 136 and consumer Categorical 128.
										{
											int traceTempVariable$var126$41_1 = cv$currentValue;
										}
									}
									
									// Processing random variable 161.
									{
										// Looking for a path between Sample 136 and consumer Categorical 161.
										{
											int traceTempVariable$var159$45_1 = cv$currentValue;
											for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
												if((i$var109 == i$var142)) {
													for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
														if((j$var121 == j$var156)) {
															// Processing sample task 171 of consumer random variable null.
															{
																// Set an accumulator to sum the probabilities for each possible configuration of
																// inputs.
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																
																// Set an accumulator to record the consumer distributions not seen. Initially set
																// to 1 as seen values will be deducted from this value.
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	// Enumerating the possible arguments for the variable Categorical 161 which is consuming
																	// the output of Sample task 136.
																	for(int var59 = 0; var59 < noStates; var59 += 1) {
																		if((var59 == traceTempVariable$var159$45_1)) {
																			{
																				{
																					double[] cv$temp$4$var160;
																					{
																						// Constructing a random variable input for use later.
																						double[] var160 = bias[traceTempVariable$var159$45_1];
																						cv$temp$4$var160 = var160;
																					}
																					
																					// Record the probability of sample task 171 generating output with current configuration.
																					if(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$4$var160.length))?Math.log(cv$temp$4$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$4$var160.length))?Math.log(cv$temp$4$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						// If the second value is -infinity.
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$4$var160.length))?Math.log(cv$temp$4$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$4$var160.length))?Math.log(cv$temp$4$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$4$var160.length))?Math.log(cv$temp$4$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)));
																					}
																					
																					// Recorded the probability of reaching sample task 171 with the current configuration.
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
				for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
					if(true) {
						// Enumerating the possible outputs of Categorical 96.
						for(int index$sample103$26 = 0; index$sample103$26 < noStates; index$sample103$26 += 1) {
							int distributionTempVariable$var97$28 = index$sample103$26;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample103Value27 = (1.0 * distribution$sample103[((i$var92 - 0) / 1)][index$sample103$26]);
							int traceTempVariable$var126$29_1 = distributionTempVariable$var97$28;
							if((i$var92 == i$var109)) {
								if((0 == (j$var121 - 1))) {
									for(int var45 = 0; var45 < noStates; var45 += 1) {
										if((var45 == traceTempVariable$var126$29_1)) {
											// Record the reached probability density.
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample103Value27);
											double[] cv$temp$1$var127;
											{
												// Constructing a random variable input for use later.
												double[] var127 = m[traceTempVariable$var126$29_1];
												cv$temp$1$var127 = var127;
											}
											
											// An accumulator to allow the value for each distribution to be constructed before
											// it is added to the index probabilities.
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample103Value27) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[cv$currentValue]):Double.NEGATIVE_INFINITY));
											
											// Processing random variable 128.
											{
												// Looking for a path between Sample 136 and consumer Categorical 128.
												{
													int traceTempVariable$var126$42_1 = cv$currentValue;
												}
											}
											
											// Processing random variable 161.
											{
												// Looking for a path between Sample 136 and consumer Categorical 161.
												{
													int traceTempVariable$var159$46_1 = cv$currentValue;
													for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
														if((i$var109 == i$var142)) {
															for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
																if((j$var121 == j$var156)) {
																	// Processing sample task 171 of consumer random variable null.
																	{
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Categorical 161 which is consuming
																			// the output of Sample task 136.
																			for(int var59 = 0; var59 < noStates; var59 += 1) {
																				if((var59 == traceTempVariable$var159$46_1)) {
																					{
																						{
																							double[] cv$temp$5$var160;
																							{
																								// Constructing a random variable input for use later.
																								double[] var160 = bias[traceTempVariable$var159$46_1];
																								cv$temp$5$var160 = var160;
																							}
																							
																							// Record the probability of sample task 171 generating output with current configuration.
																							if(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$5$var160.length))?Math.log(cv$temp$5$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$5$var160.length))?Math.log(cv$temp$5$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$5$var160.length))?Math.log(cv$temp$5$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$5$var160.length))?Math.log(cv$temp$5$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$5$var160.length))?Math.log(cv$temp$5$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)));
																							}
																							
																							// Recorded the probability of reaching sample task 171 with the current configuration.
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
			
			// Enumerating the possible arguments for Categorical 128.
			int traceTempVariable$var126$32_1 = cv$currentValue;
			if((index$i$23 == i$var109)) {
				if((index$j$22 == (j$var121 - 1))) {
					for(int var45 = 0; var45 < noStates; var45 += 1) {
						if((var45 == traceTempVariable$var126$32_1)) {
							// Record the reached probability density.
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$2$var127;
							{
								// Constructing a random variable input for use later.
								double[] var127 = m[traceTempVariable$var126$32_1];
								cv$temp$2$var127 = var127;
							}
							
							// An accumulator to allow the value for each distribution to be constructed before
							// it is added to the index probabilities.
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var127.length))?Math.log(cv$temp$2$var127[cv$currentValue]):Double.NEGATIVE_INFINITY));
							
							// Processing random variable 128.
							{
								// Looking for a path between Sample 136 and consumer Categorical 128.
								{
									int traceTempVariable$var126$43_1 = cv$currentValue;
								}
							}
							
							// Processing random variable 161.
							{
								// Looking for a path between Sample 136 and consumer Categorical 161.
								{
									int traceTempVariable$var159$47_1 = cv$currentValue;
									for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
										if((i$var109 == i$var142)) {
											for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
												if((j$var121 == j$var156)) {
													// Processing sample task 171 of consumer random variable null.
													{
														// Set an accumulator to sum the probabilities for each possible configuration of
														// inputs.
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														
														// Set an accumulator to record the consumer distributions not seen. Initially set
														// to 1 as seen values will be deducted from this value.
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															// Enumerating the possible arguments for the variable Categorical 161 which is consuming
															// the output of Sample task 136.
															for(int var59 = 0; var59 < noStates; var59 += 1) {
																if((var59 == traceTempVariable$var159$47_1)) {
																	{
																		{
																			double[] cv$temp$6$var160;
																			{
																				// Constructing a random variable input for use later.
																				double[] var160 = bias[traceTempVariable$var159$47_1];
																				cv$temp$6$var160 = var160;
																			}
																			
																			// Record the probability of sample task 171 generating output with current configuration.
																			if(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$6$var160.length))?Math.log(cv$temp$6$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$6$var160.length))?Math.log(cv$temp$6$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$6$var160.length))?Math.log(cv$temp$6$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$6$var160.length))?Math.log(cv$temp$6$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$6$var160.length))?Math.log(cv$temp$6$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)));
																			}
																			
																			// Recorded the probability of reaching sample task 171 with the current configuration.
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
					if(!((index$j$34 == index$j$22) && (index$i$33 == index$i$23))) {
						// Enumerating the possible outputs of Categorical 128.
						for(int index$sample136$35 = 0; index$sample136$35 < noStates; index$sample136$35 += 1) {
							int distributionTempVariable$var129$37 = index$sample136$35;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample136Value36 = (1.0 * distribution$sample136[((index$i$33 - 0) / 1)][((index$j$34 - 1) / 1)][index$sample136$35]);
							int traceTempVariable$var126$38_1 = distributionTempVariable$var129$37;
							if((index$i$33 == i$var109)) {
								if((index$j$34 == (j$var121 - 1))) {
									for(int var45 = 0; var45 < noStates; var45 += 1) {
										if((var45 == traceTempVariable$var126$38_1)) {
											// Record the reached probability density.
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample136Value36);
											double[] cv$temp$3$var127;
											{
												// Constructing a random variable input for use later.
												double[] var127 = m[traceTempVariable$var126$38_1];
												cv$temp$3$var127 = var127;
											}
											
											// An accumulator to allow the value for each distribution to be constructed before
											// it is added to the index probabilities.
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample136Value36) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$var127.length))?Math.log(cv$temp$3$var127[cv$currentValue]):Double.NEGATIVE_INFINITY));
											
											// Processing random variable 128.
											{
												// Looking for a path between Sample 136 and consumer Categorical 128.
												{
													int traceTempVariable$var126$44_1 = cv$currentValue;
												}
											}
											
											// Processing random variable 161.
											{
												// Looking for a path between Sample 136 and consumer Categorical 161.
												{
													int traceTempVariable$var159$48_1 = cv$currentValue;
													for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
														if((i$var109 == i$var142)) {
															for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
																if((j$var121 == j$var156)) {
																	// Processing sample task 171 of consumer random variable null.
																	{
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Categorical 161 which is consuming
																			// the output of Sample task 136.
																			for(int var59 = 0; var59 < noStates; var59 += 1) {
																				if((var59 == traceTempVariable$var159$48_1)) {
																					{
																						{
																							double[] cv$temp$7$var160;
																							{
																								// Constructing a random variable input for use later.
																								double[] var160 = bias[traceTempVariable$var159$48_1];
																								cv$temp$7$var160 = var160;
																							}
																							
																							// Record the probability of sample task 171 generating output with current configuration.
																							if(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$7$var160.length))?Math.log(cv$temp$7$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$7$var160.length))?Math.log(cv$temp$7$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$7$var160.length))?Math.log(cv$temp$7$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$7$var160.length))?Math.log(cv$temp$7$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$7$var160.length))?Math.log(cv$temp$7$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)));
																							}
																							
																							// Recorded the probability of reaching sample task 171 with the current configuration.
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
			
			// Processing random variable 128.
			{
				// Looking for a path between Sample 136 and consumer Categorical 128.
				{
					int traceTempVariable$var126$61_1 = cv$currentValue;
					for(int index$i$61_2 = 0; index$i$61_2 < samples; index$i$61_2 += 1) {
						if((i$var109 == index$i$61_2)) {
							for(int index$j$61_3 = 1; index$j$61_3 < length$eventsMeasured[index$i$61_2]; index$j$61_3 += 1) {
								if((j$var121 == (index$j$61_3 - 1))) {
									// Processing sample task 136 of consumer random variable null.
									{
										// Copy of index so that its values can be safely substituted
										int index$j$63 = index$j$61_3;
										
										// Copy of index so that its values can be safely substituted
										int index$i$64 = index$i$61_2;
										
										// A local array to hold the accumulated distributions of the sample tasks for each
										// configuration of distributions.
										double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var128;
										
										// Zero all the elements in the distribution accumulator
										for(int cv$i = 0; cv$i < noStates; cv$i += 1)
											cv$accumulatedConsumerDistributions[cv$i] = 0.0;
										
										// Zero an accumulator to track the probabilities reached.
										double cv$reachedDistributionProbability = 0.0;
										
										// Enumerating the possible arguments for the variable Categorical 128 which is consuming
										// the output of Sample task 136.
										for(int var45 = 0; var45 < noStates; var45 += 1) {
											if((var45 == traceTempVariable$var126$61_1)) {
												{
													// Declare and zero an accumulator for tracking the reached source probability space.
													double scopeVariable$reachedSourceProbability = 0.0;
													
													// Enumerating the possible arguments for Categorical 128.
													if(fixedFlag$sample103) {
														for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
															if((i$var92 == i$var109)) {
																if((0 == (j$var121 - 1))) {
																	for(int index$var45$72_1 = 0; index$var45$72_1 < noStates; index$var45$72_1 += 1) {
																		if((index$var45$72_1 == st[i$var109][(j$var121 - 1)]))
																			// Add the probability of this argument configuration.
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																}
															}
														}
													} else {
														for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
															if(true) {
																// Enumerating the possible outputs of Categorical 96.
																for(int index$sample103$68 = 0; index$sample103$68 < noStates; index$sample103$68 += 1) {
																	int distributionTempVariable$var97$70 = index$sample103$68;
																	
																	// Update the probability of sampling this value from the distribution value.
																	double cv$probabilitySample103Value69 = (1.0 * distribution$sample103[((i$var92 - 0) / 1)][index$sample103$68]);
																	int traceTempVariable$var126$71_1 = distributionTempVariable$var97$70;
																	if((i$var92 == i$var109)) {
																		if((0 == (j$var121 - 1))) {
																			for(int index$var45$73_1 = 0; index$var45$73_1 < noStates; index$var45$73_1 += 1) {
																				if((index$var45$73_1 == traceTempVariable$var126$71_1))
																					// Add the probability of this argument configuration.
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample103Value69);
																			}
																		}
																	}
																}
															}
														}
													}
													
													// Enumerating the possible arguments for Categorical 128.
													int traceTempVariable$var126$74_1 = cv$currentValue;
													if((index$i$23 == i$var109)) {
														if((index$j$22 == (j$var121 - 1))) {
															for(int index$var45$81_1 = 0; index$var45$81_1 < noStates; index$var45$81_1 += 1) {
																if((index$var45$81_1 == traceTempVariable$var126$74_1))
																	// Add the probability of this argument configuration.
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
														}
													}
													for(int index$i$75 = 0; index$i$75 < samples; index$i$75 += 1) {
														for(int index$j$76 = 1; index$j$76 < length$eventsMeasured[index$i$75]; index$j$76 += 1) {
															if((!((index$j$76 == index$j$22) && (index$i$75 == index$i$23)) && !((index$j$76 == index$j$63) && (index$i$75 == index$i$64)))) {
																// Enumerating the possible outputs of Categorical 128.
																for(int index$sample136$77 = 0; index$sample136$77 < noStates; index$sample136$77 += 1) {
																	int distributionTempVariable$var129$79 = index$sample136$77;
																	
																	// Update the probability of sampling this value from the distribution value.
																	double cv$probabilitySample136Value78 = (1.0 * distribution$sample136[((index$i$75 - 0) / 1)][((index$j$76 - 1) / 1)][index$sample136$77]);
																	int traceTempVariable$var126$80_1 = distributionTempVariable$var129$79;
																	if((index$i$75 == i$var109)) {
																		if((index$j$76 == (j$var121 - 1))) {
																			for(int index$var45$82_1 = 0; index$var45$82_1 < noStates; index$var45$82_1 += 1) {
																				if((index$var45$82_1 == traceTempVariable$var126$80_1))
																					// Add the probability of this argument configuration.
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample136Value78);
																			}
																		}
																	}
																}
															}
														}
													}
													double[] cv$temp$8$var127;
													{
														// Constructing a random variable input for use later.
														double[] var127 = m[traceTempVariable$var126$61_1];
														cv$temp$8$var127 = var127;
													}
													
													// The probability of reaching the consumer with this set of consumer arguments
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													
													// Record the reached distribution.
													cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
													
													// Add the current distribution to the distribution accumulator.
													DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$8$var127);
												}
											}
										}
										
										// A local copy of the samples' distribution.
										double[] cv$sampleDistribution = distribution$sample136[((index$i$61_2 - 0) / 1)][((index$j$61_3 - 1) / 1)];
										
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
		double[] cv$localProbability = distribution$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)];
		
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
	// by sample task 47 drawn from Dirichlet 34. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample47(int var45) {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = m[var45];
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var46$countGlobal;
		
		// Get the length of the array
		int cv$arrayLength = noStates;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 96.
			{
				// Looking for a path between Sample 47 and consumer Categorical 96.
				{
					if((var45 == initialState)) {
						for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
							if(fixedFlag$sample103) {
								// Processing sample task 103 of consumer random variable null.
								{
									// Copy of index so that its values can be safely substituted
									int index$i$3 = i$var92;
									{
										{
											{
												{
													// Increment the sample counter with the value sampled by sample task 103 of random
													// variable var96
													cv$countLocal[st[i$var92][0]] = (cv$countLocal[st[i$var92][0]] + 1.0);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Processing random variable 128.
			{
				// Looking for a path between Sample 47 and consumer Categorical 128.
				{
					for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
						for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
							if(fixedFlag$sample103) {
								for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
									if((i$var92 == i$var109)) {
										if((0 == (j$var121 - 1))) {
											if((var45 == st[i$var109][(j$var121 - 1)])) {
												if(fixedFlag$sample136) {
													// Processing sample task 136 of consumer random variable null.
													{
														// Copy of index so that its values can be safely substituted
														int index$j$27 = j$var121;
														
														// Copy of index so that its values can be safely substituted
														int index$i$28 = i$var109;
														{
															{
																{
																	{
																		// Increment the sample counter with the value sampled by sample task 136 of random
																		// variable var128
																		cv$countLocal[st[i$var109][j$var121]] = (cv$countLocal[st[i$var109][j$var121]] + 1.0);
																	}
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
								for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 96.
										for(int index$sample103$9 = 0; index$sample103$9 < noStates; index$sample103$9 += 1) {
											int distributionTempVariable$var97$11 = index$sample103$9;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample103Value10 = (1.0 * distribution$sample103[((i$var92 - 0) / 1)][index$sample103$9]);
											int traceTempVariable$var126$12_1 = distributionTempVariable$var97$11;
											if((i$var92 == i$var109)) {
												if((0 == (j$var121 - 1))) {
													if((var45 == traceTempVariable$var126$12_1)) {
														if(fixedFlag$sample136) {
															// Processing sample task 136 of consumer random variable null.
															{
																// Copy of index so that its values can be safely substituted
																int index$j$30 = j$var121;
																
																// Copy of index so that its values can be safely substituted
																int index$i$31 = i$var109;
																{
																	{
																		{
																			{
																				// Increment the sample counter with the value sampled by sample task 136 of random
																				// variable var128
																				cv$countLocal[st[i$var109][j$var121]] = (cv$countLocal[st[i$var109][j$var121]] + cv$probabilitySample103Value10);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
						for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
							if(fixedFlag$sample136) {
								for(int index$i$17_1 = 0; index$i$17_1 < samples; index$i$17_1 += 1) {
									for(int index$j$17_2 = 1; index$j$17_2 < length$eventsMeasured[index$i$17_1]; index$j$17_2 += 1) {
										if((index$i$17_1 == i$var109)) {
											if((index$j$17_2 == (j$var121 - 1))) {
												if((var45 == st[i$var109][(j$var121 - 1)])) {
													if(fixedFlag$sample136) {
														// Processing sample task 136 of consumer random variable null.
														{
															// Copy of index so that its values can be safely substituted
															int index$j$33 = j$var121;
															
															// Copy of index so that its values can be safely substituted
															int index$i$34 = i$var109;
															{
																{
																	{
																		{
																			// Increment the sample counter with the value sampled by sample task 136 of random
																			// variable var128
																			cv$countLocal[st[i$var109][j$var121]] = (cv$countLocal[st[i$var109][j$var121]] + 1.0);
																		}
																	}
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
											// Enumerating the possible outputs of Categorical 128.
											for(int index$sample136$20 = 0; index$sample136$20 < noStates; index$sample136$20 += 1) {
												int distributionTempVariable$var129$22 = index$sample136$20;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample136Value21 = (1.0 * distribution$sample136[((index$i$18 - 0) / 1)][((index$j$19 - 1) / 1)][index$sample136$20]);
												int traceTempVariable$var126$23_1 = distributionTempVariable$var129$22;
												if((index$i$18 == i$var109)) {
													if((index$j$19 == (j$var121 - 1))) {
														if((var45 == traceTempVariable$var126$23_1)) {
															if(fixedFlag$sample136) {
																// Processing sample task 136 of consumer random variable null.
																{
																	// Copy of index so that its values can be safely substituted
																	int index$j$36 = j$var121;
																	
																	// Copy of index so that its values can be safely substituted
																	int index$i$37 = i$var109;
																	{
																		{
																			{
																				{
																					// Increment the sample counter with the value sampled by sample task 136 of random
																					// variable var128
																					cv$countLocal[st[i$var109][j$var121]] = (cv$countLocal[st[i$var109][j$var121]] + cv$probabilitySample136Value21);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Processing random variable 96.
		{
			// Looking for a path between Sample 47 and consumer Categorical 96.
			{
				if((var45 == initialState)) {
					for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
						if(!fixedFlag$sample103) {
							// Processing sample task 103 of consumer random variable null.
							{
								// Copy of index so that its values can be safely substituted
								int index$i$44 = i$var92;
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
											cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample103[((i$var92 - 0) / 1)][cv$loopIndex] * cv$distributionProbability));
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Processing random variable 128.
		{
			// Looking for a path between Sample 47 and consumer Categorical 128.
			{
				for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
					for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
						if(fixedFlag$sample103) {
							for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
								if((i$var92 == i$var109)) {
									if((0 == (j$var121 - 1))) {
										if((var45 == st[i$var109][(j$var121 - 1)])) {
											if(!fixedFlag$sample136) {
												// Processing sample task 136 of consumer random variable null.
												{
													// Copy of index so that its values can be safely substituted
													int index$j$67 = j$var121;
													
													// Copy of index so that its values can be safely substituted
													int index$i$68 = i$var109;
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
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
														}
													}
												}
											}
										}
									}
								}
							}
						} else {
							for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
								if(true) {
									// Enumerating the possible outputs of Categorical 96.
									for(int index$sample103$49 = 0; index$sample103$49 < noStates; index$sample103$49 += 1) {
										int distributionTempVariable$var97$51 = index$sample103$49;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample103Value50 = (1.0 * distribution$sample103[((i$var92 - 0) / 1)][index$sample103$49]);
										int traceTempVariable$var126$52_1 = distributionTempVariable$var97$51;
										if((i$var92 == i$var109)) {
											if((0 == (j$var121 - 1))) {
												if((var45 == traceTempVariable$var126$52_1)) {
													if(!fixedFlag$sample136) {
														// Processing sample task 136 of consumer random variable null.
														{
															// Copy of index so that its values can be safely substituted
															int index$j$70 = j$var121;
															
															// Copy of index so that its values can be safely substituted
															int index$i$71 = i$var109;
															{
																{
																	// Declare and zero an accumulator for tracking the reached source probability space.
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		// Add the probability of this argument configuration.
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																	
																	// The probability of reaching the consumer with this set of consumer arguments
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample103Value50);
																	
																	// Merge the distribution probabilities into the count
																	for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
					for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
						if(fixedFlag$sample136) {
							for(int index$i$57_1 = 0; index$i$57_1 < samples; index$i$57_1 += 1) {
								for(int index$j$57_2 = 1; index$j$57_2 < length$eventsMeasured[index$i$57_1]; index$j$57_2 += 1) {
									if((index$i$57_1 == i$var109)) {
										if((index$j$57_2 == (j$var121 - 1))) {
											if((var45 == st[i$var109][(j$var121 - 1)])) {
												if(!fixedFlag$sample136) {
													// Processing sample task 136 of consumer random variable null.
													{
														// Copy of index so that its values can be safely substituted
														int index$j$73 = j$var121;
														
														// Copy of index so that its values can be safely substituted
														int index$i$74 = i$var109;
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
																	cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
										// Enumerating the possible outputs of Categorical 128.
										for(int index$sample136$60 = 0; index$sample136$60 < noStates; index$sample136$60 += 1) {
											int distributionTempVariable$var129$62 = index$sample136$60;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample136Value61 = (1.0 * distribution$sample136[((index$i$58 - 0) / 1)][((index$j$59 - 1) / 1)][index$sample136$60]);
											int traceTempVariable$var126$63_1 = distributionTempVariable$var129$62;
											if((index$i$58 == i$var109)) {
												if((index$j$59 == (j$var121 - 1))) {
													if((var45 == traceTempVariable$var126$63_1)) {
														if(!fixedFlag$sample136) {
															// Processing sample task 136 of consumer random variable null.
															{
																// Copy of index so that its values can be safely substituted
																int index$j$76 = j$var121;
																
																// Copy of index so that its values can be safely substituted
																int index$i$77 = i$var109;
																{
																	{
																		// Declare and zero an accumulator for tracking the reached source probability space.
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			// Add the probability of this argument configuration.
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		
																		// The probability of reaching the consumer with this set of consumer arguments
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample136Value61);
																		
																		// Merge the distribution probabilities into the count
																		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																			cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																	}
																}
															}
														}
													}
												}
											}
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
	// by sample task 62 drawn from Dirichlet 48. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample62(int var59) {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = bias[var59];
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var60$countGlobal;
		
		// Get the length of the array
		int cv$arrayLength = noEvents;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 161.
			{
				// Looking for a path between Sample 62 and consumer Categorical 161.
				{
					for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
						for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
							if(fixedFlag$sample103) {
								for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
									if((i$var92 == i$var142)) {
										if((0 == j$var156)) {
											if((var59 == st[i$var142][j$var156])) {
												// Processing sample task 171 of consumer random variable null.
												{
													{
														{
															{
																{
																	// Increment the sample counter with the value sampled by sample task 171 of random
																	// variable var161
																	cv$countLocal[(events[i$var142][j$var156] - 1)] = (cv$countLocal[(events[i$var142][j$var156] - 1)] + 1.0);
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
								for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 96.
										for(int index$sample103$5 = 0; index$sample103$5 < noStates; index$sample103$5 += 1) {
											int distributionTempVariable$var97$7 = index$sample103$5;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample103Value6 = (1.0 * distribution$sample103[((i$var92 - 0) / 1)][index$sample103$5]);
											int traceTempVariable$var159$8_1 = distributionTempVariable$var97$7;
											if((i$var92 == i$var142)) {
												if((0 == j$var156)) {
													if((var59 == traceTempVariable$var159$8_1)) {
														// Processing sample task 171 of consumer random variable null.
														{
															{
																{
																	{
																		{
																			// Increment the sample counter with the value sampled by sample task 171 of random
																			// variable var161
																			cv$countLocal[(events[i$var142][j$var156] - 1)] = (cv$countLocal[(events[i$var142][j$var156] - 1)] + cv$probabilitySample103Value6);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
						for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
							if(fixedFlag$sample136) {
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
										if((i$var109 == i$var142)) {
											if((j$var121 == j$var156)) {
												if((var59 == st[i$var142][j$var156])) {
													// Processing sample task 171 of consumer random variable null.
													{
														{
															{
																{
																	{
																		// Increment the sample counter with the value sampled by sample task 171 of random
																		// variable var161
																		cv$countLocal[(events[i$var142][j$var156] - 1)] = (cv$countLocal[(events[i$var142][j$var156] - 1)] + 1.0);
																	}
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
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 128.
											for(int index$sample136$16 = 0; index$sample136$16 < noStates; index$sample136$16 += 1) {
												int distributionTempVariable$var129$18 = index$sample136$16;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample136Value17 = (1.0 * distribution$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)][index$sample136$16]);
												int traceTempVariable$var159$19_1 = distributionTempVariable$var129$18;
												if((i$var109 == i$var142)) {
													if((j$var121 == j$var156)) {
														if((var59 == traceTempVariable$var159$19_1)) {
															// Processing sample task 171 of consumer random variable null.
															{
																{
																	{
																		{
																			{
																				// Increment the sample counter with the value sampled by sample task 171 of random
																				// variable var161
																				cv$countLocal[(events[i$var142][j$var156] - 1)] = (cv$countLocal[(events[i$var142][j$var156] - 1)] + cv$probabilitySample136Value17);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
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
	// by sample task 86 drawn from Dirichlet 79. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample86() {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = weights;
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var80$countGlobal;
		
		// Get the length of the array
		int cv$arrayLength = noStates;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 81.
			{
				{
					// Processing sample task 88 of consumer random variable null.
					{
						{
							{
								{
									{
										// Increment the sample counter with the value sampled by sample task 88 of random
										// variable var81
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
	// by sample task 88 drawn from Categorical 81. Inference was performed using variable
	// marginalization.
	private final void sample88() {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		{
			// variable marginalization
			cv$noStates = Math.max(cv$noStates, noStates);
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var82$stateProbabilityGlobal;
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
				
				// Processing random variable 96.
				{
					{
						for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
							int traceTempVariable$initialState$1_2 = cv$currentValue;
							if(fixedFlag$sample103) {
								// Processing sample task 103 of consumer random variable null.
								{
									// Copy of index so that its values can be safely substituted
									int index$i$3 = i$var92;
									
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
													double[] cv$temp$1$var95;
													{
														// Constructing a random variable input for use later.
														double[] var95 = m[traceTempVariable$initialState$1_2];
														cv$temp$1$var95 = var95;
													}
													
													// Record the probability of sample task 103 generating output with current configuration.
													if(((Math.log(1.0) + (((0.0 <= st[i$var92][0]) && (st[i$var92][0] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[st[i$var92][0]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var92][0]) && (st[i$var92][0] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[st[i$var92][0]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var92][0]) && (st[i$var92][0] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[st[i$var92][0]]):Double.NEGATIVE_INFINITY));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var92][0]) && (st[i$var92][0] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[st[i$var92][0]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var92][0]) && (st[i$var92][0] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[st[i$var92][0]]):Double.NEGATIVE_INFINITY)));
													}
													
													// Recorded the probability of reaching sample task 103 with the current configuration.
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
			
			// Processing random variable 96.
			{
				{
					for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
						int traceTempVariable$initialState$5_2 = cv$currentValue;
						if(!fixedFlag$sample103) {
							// Processing sample task 103 of consumer random variable null.
							{
								// Copy of index so that its values can be safely substituted
								int index$i$7 = i$var92;
								
								// A local array to hold the accumulated distributions of the sample tasks for each
								// configuration of distributions.
								double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var96;
								
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
										double[] cv$temp$2$var95;
										{
											// Constructing a random variable input for use later.
											double[] var95 = m[traceTempVariable$initialState$5_2];
											cv$temp$2$var95 = var95;
										}
										
										// The probability of reaching the consumer with this set of consumer arguments
										double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
										
										// Record the reached distribution.
										cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
										
										// Add the current distribution to the distribution accumulator.
										DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$2$var95);
									}
								}
								
								// A local copy of the samples' distribution.
								double[] cv$sampleDistribution = distribution$sample103[((i$var92 - 0) / 1)];
								
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

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var46$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			for(int var45 = 0; var45 < noStates; var45 += 1)
				cv$max = Math.max(cv$max, noStates);
			
			// Allocation of cv$var46$countGlobal for single threaded execution
			cv$var46$countGlobal = new double[cv$max];
		}
		
		// Constructor for cv$var60$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			for(int var59 = 0; var59 < noStates; var59 += 1)
				cv$max = Math.max(cv$max, noEvents);
			
			// Allocation of cv$var60$countGlobal for single threaded execution
			cv$var60$countGlobal = new double[cv$max];
		}
		
		// Constructor for cv$var80$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			cv$max = Math.max(cv$max, noStates);
			
			// Allocation of cv$var80$countGlobal for single threaded execution
			cv$var80$countGlobal = new double[cv$max];
		}
		
		// Constructor for cv$distributionAccumulator$var96
		{
			// Variable to record the maximum value of Task Get 101. Initially set to the value
			// of putTask 48.
			int cv$var47$max = noStates;
			
			// Allocation of cv$distributionAccumulator$var96 for single threaded execution
			cv$distributionAccumulator$var96 = new double[cv$var47$max];
		}
		
		// Constructor for cv$var82$stateProbabilityGlobal
		{
			// Allocation of cv$var82$stateProbabilityGlobal for single threaded execution
			cv$var82$stateProbabilityGlobal = new double[noStates];
		}
		
		// Constructor for cv$distributionAccumulator$var128
		{
			// Variable to record the maximum value of Task Get 134. Initially set to the value
			// of putTask 48.
			int cv$var47$max = noStates;
			
			// Allocation of cv$distributionAccumulator$var128 for single threaded execution
			cv$distributionAccumulator$var128 = new double[cv$var47$max];
		}
		
		// Constructor for cv$var97$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 101. Initially set to the value
			// of putTask 48.
			int cv$var47$max = noStates;
			
			// Allocation of cv$var97$stateProbabilityGlobal for single threaded execution
			cv$var97$stateProbabilityGlobal = new double[cv$var47$max];
		}
		
		// Constructor for cv$var129$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 134. Initially set to the value
			// of putTask 48.
			int cv$var47$max = noStates;
			
			// Allocation of cv$var129$stateProbabilityGlobal for single threaded execution
			cv$var129$stateProbabilityGlobal = new double[cv$var47$max];
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
				for(int var45 = 0; var45 < noStates; var45 += 1)
					m[var45] = new double[noStates];
			}
		}
		
		// If bias has not been set already allocate space.
		if(!setFlag$bias) {
			// Constructor for bias
			{
				bias = new double[noStates][];
				for(int var59 = 0; var59 < noStates; var59 += 1)
					bias[var59] = new double[noEvents];
			}
		}
		
		// If st has not been set already allocate space.
		if(!setFlag$st) {
			// Constructor for st
			{
				st = new int[length$eventsMeasured.length][];
				for(int i$var73 = 0; i$var73 < length$eventsMeasured.length; i$var73 += 1)
					st[i$var73] = new int[length$eventsMeasured[i$var73]];
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
				for(int i$var142 = 0; i$var142 < length$eventsMeasured.length; i$var142 += 1)
					events[i$var142] = new int[length$eventsMeasured[i$var142]];
			}
		}
		
		// Constructor for distribution$sample103
		{
			distribution$sample103 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var92 = 0; i$var92 < length$eventsMeasured.length; i$var92 += 1)
				distribution$sample103[((i$var92 - 0) / 1)] = new double[noStates];
		}
		
		// Constructor for distribution$sample136
		{
			distribution$sample136 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][][];
			for(int i$var109 = 0; i$var109 < length$eventsMeasured.length; i$var109 += 1) {
				double[][] subarray$0 = new double[((((length$eventsMeasured[i$var109] - 1) - 1) / 1) + 1)][];
				distribution$sample136[((i$var109 - 0) / 1)] = subarray$0;
				for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1)
					subarray$0[((j$var121 - 1) / 1)] = new double[noStates];
			}
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		for(int var45 = 0; var45 < noStates; var45 += 1) {
			double[] var46 = m[var45];
			if(!fixedFlag$sample47)
				DistributionSampling.sampleDirichlet(RNG$, v, var46);
		}
		for(int var59 = 0; var59 < noStates; var59 += 1) {
			double[] var60 = bias[var59];
			if(!fixedFlag$sample62)
				DistributionSampling.sampleDirichlet(RNG$, v2, var60);
		}
		if(!fixedFlag$sample86)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample88)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
			int[] var93 = st[i$var92];
			if(!fixedFlag$sample103)
				var93[0] = DistributionSampling.sampleCategorical(RNG$, m[initialState]);
		}
		for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
			int[] var122 = st[i$var109];
			for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
				if(!fixedFlag$sample136)
					var122[j$var121] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var109][(j$var121 - 1)]]);
			}
		}
		for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
			int[] var157 = events[i$var142];
			for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
				if(!fixedFlag$sample171)
					var157[j$var156] = (DistributionSampling.sampleCategorical(RNG$, bias[st[i$var142][j$var156]]) + 1);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var45 = 0; var45 < noStates; var45 += 1) {
			double[] var46 = m[var45];
			if(!fixedFlag$sample47)
				DistributionSampling.sampleDirichlet(RNG$, v, var46);
		}
		for(int var59 = 0; var59 < noStates; var59 += 1) {
			double[] var60 = bias[var59];
			if(!fixedFlag$sample62)
				DistributionSampling.sampleDirichlet(RNG$, v2, var60);
		}
		if(!fixedFlag$sample86)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample88)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
			// Create local copy of variable probabilities.
			double[] cv$distribution$sample103 = distribution$sample103[((i$var92 - 0) / 1)];
			double[] var95 = m[initialState];
			for(int index$var96 = 0; index$var96 < noStates; index$var96 += 1) {
				// Probability for this value
				double cv$value = (((0.0 <= index$var96) && (index$var96 < var95.length))?var95[index$var96]:0.0);
				if(!fixedFlag$sample103)
					// Save the probability of each value
					cv$distribution$sample103[index$var96] = cv$value;
			}
		}
		for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
			for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
				// Create local copy of variable probabilities.
				double[] cv$distribution$sample136 = distribution$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)];
				for(int index$var128 = 0; index$var128 < noStates; index$var128 += 1) {
					if(!fixedFlag$sample136)
						// Zero the probability of each value
						cv$distribution$sample136[index$var128] = 0.0;
				}
				
				// Iterate through possible values for var128's arguments.
				// 
				// Enumerating the possible arguments for Categorical 128.
				if(fixedFlag$sample103) {
					for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
						if((i$var92 == i$var109)) {
							if((0 == (j$var121 - 1))) {
								for(int var45 = 0; var45 < noStates; var45 += 1) {
									if((var45 == st[i$var109][(j$var121 - 1)])) {
										{
											if(!fixedFlag$sample136) {
												double[] var127 = m[st[i$var109][(j$var121 - 1)]];
												for(int index$var128 = 0; index$var128 < noStates; index$var128 += 1)
													// Save the probability of each value
													cv$distribution$sample136[index$var128] = (cv$distribution$sample136[index$var128] + (1.0 * (((0.0 <= index$var128) && (index$var128 < var127.length))?var127[index$var128]:0.0)));
											}
										}
									}
								}
							}
						}
					}
				} else {
					for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
						if(true) {
							// Enumerating the possible outputs of Categorical 96.
							for(int index$sample103$3 = 0; index$sample103$3 < noStates; index$sample103$3 += 1) {
								int distributionTempVariable$var97$5 = index$sample103$3;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample103Value4 = (1.0 * distribution$sample103[((i$var92 - 0) / 1)][index$sample103$3]);
								int traceTempVariable$var126$6_1 = distributionTempVariable$var97$5;
								if((i$var92 == i$var109)) {
									if((0 == (j$var121 - 1))) {
										for(int var45 = 0; var45 < noStates; var45 += 1) {
											if((var45 == traceTempVariable$var126$6_1)) {
												{
													if(!fixedFlag$sample136) {
														double[] var127 = m[traceTempVariable$var126$6_1];
														for(int index$var128 = 0; index$var128 < noStates; index$var128 += 1)
															// Save the probability of each value
															cv$distribution$sample136[index$var128] = (cv$distribution$sample136[index$var128] + (cv$probabilitySample103Value4 * (((0.0 <= index$var128) && (index$var128 < var127.length))?var127[index$var128]:0.0)));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				
				// Enumerating the possible arguments for Categorical 128.
				if(fixedFlag$sample136) {
					for(int index$i$9_1 = 0; index$i$9_1 < samples; index$i$9_1 += 1) {
						for(int index$j$9_2 = 1; index$j$9_2 < length$eventsMeasured[index$i$9_1]; index$j$9_2 += 1) {
							if((index$i$9_1 == i$var109)) {
								if((index$j$9_2 == (j$var121 - 1))) {
									for(int var45 = 0; var45 < noStates; var45 += 1) {
										if((var45 == st[i$var109][(j$var121 - 1)])) {
											{
												if(!fixedFlag$sample136) {
													double[] var127 = m[st[i$var109][(j$var121 - 1)]];
													for(int index$var128 = 0; index$var128 < noStates; index$var128 += 1)
														// Save the probability of each value
														cv$distribution$sample136[index$var128] = (cv$distribution$sample136[index$var128] + (1.0 * (((0.0 <= index$var128) && (index$var128 < var127.length))?var127[index$var128]:0.0)));
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
								// Enumerating the possible outputs of Categorical 128.
								for(int index$sample136$12 = 0; index$sample136$12 < noStates; index$sample136$12 += 1) {
									int distributionTempVariable$var129$14 = index$sample136$12;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample136Value13 = (1.0 * distribution$sample136[((index$i$10 - 0) / 1)][((index$j$11 - 1) / 1)][index$sample136$12]);
									int traceTempVariable$var126$15_1 = distributionTempVariable$var129$14;
									if((index$i$10 == i$var109)) {
										if((index$j$11 == (j$var121 - 1))) {
											for(int var45 = 0; var45 < noStates; var45 += 1) {
												if((var45 == traceTempVariable$var126$15_1)) {
													{
														if(!fixedFlag$sample136) {
															double[] var127 = m[traceTempVariable$var126$15_1];
															for(int index$var128 = 0; index$var128 < noStates; index$var128 += 1)
																// Save the probability of each value
																cv$distribution$sample136[index$var128] = (cv$distribution$sample136[index$var128] + (cv$probabilitySample136Value13 * (((0.0 <= index$var128) && (index$var128 < var127.length))?var127[index$var128]:0.0)));
														}
													}
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
				double cv$var128$sum = 0.0;
				for(int index$var128 = 0; index$var128 < noStates; index$var128 += 1) {
					if(!fixedFlag$sample136)
						// sum the probability of each value
						cv$var128$sum = (cv$var128$sum + cv$distribution$sample136[index$var128]);
				}
				for(int index$var128 = 0; index$var128 < noStates; index$var128 += 1) {
					if(!fixedFlag$sample136)
						// Normalise the probability of each value
						cv$distribution$sample136[index$var128] = (cv$distribution$sample136[index$var128] / cv$var128$sum);
				}
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var45 = 0; var45 < noStates; var45 += 1) {
			double[] var46 = m[var45];
			if(!fixedFlag$sample47)
				DistributionSampling.sampleDirichlet(RNG$, v, var46);
		}
		for(int var59 = 0; var59 < noStates; var59 += 1) {
			double[] var60 = bias[var59];
			if(!fixedFlag$sample62)
				DistributionSampling.sampleDirichlet(RNG$, v2, var60);
		}
		if(!fixedFlag$sample86)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample88)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
			int[] var93 = st[i$var92];
			if(!fixedFlag$sample103)
				var93[0] = DistributionSampling.sampleCategorical(RNG$, m[initialState]);
		}
		for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
			int[] var122 = st[i$var109];
			for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
				if(!fixedFlag$sample136)
					var122[j$var121] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var109][(j$var121 - 1)]]);
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			for(int var45 = 0; var45 < noStates; var45 += 1) {
				if(!fixedFlag$sample47)
					sample47(var45);
			}
			for(int var59 = 0; var59 < noStates; var59 += 1) {
				if(!fixedFlag$sample62)
					sample62(var59);
			}
			if(!fixedFlag$sample86)
				sample86();
			if(!fixedFlag$sample88)
				sample88();
			for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
				if(!fixedFlag$sample103)
					sample103(i$var92);
			}
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
					if(!fixedFlag$sample136)
						sample136(i$var109, j$var121);
				}
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i$var109 = (samples - ((((samples - 1) - 0) % 1) + 1)); i$var109 >= ((0 - 1) + 1); i$var109 -= 1) {
				for(int j$var121 = (length$eventsMeasured[i$var109] - ((((length$eventsMeasured[i$var109] - 1) - 1) % 1) + 1)); j$var121 >= ((1 - 1) + 1); j$var121 -= 1) {
					if(!fixedFlag$sample136)
						sample136(i$var109, j$var121);
				}
			}
			for(int i$var92 = (samples - ((((samples - 1) - 0) % 1) + 1)); i$var92 >= ((0 - 1) + 1); i$var92 -= 1) {
				if(!fixedFlag$sample103)
					sample103(i$var92);
			}
			if(!fixedFlag$sample88)
				sample88();
			if(!fixedFlag$sample86)
				sample86();
			for(int var59 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var59 >= ((0 - 1) + 1); var59 -= 1) {
				if(!fixedFlag$sample62)
					sample62(var59);
			}
			for(int var45 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var45 >= ((0 - 1) + 1); var45 -= 1) {
				if(!fixedFlag$sample47)
					sample47(var45);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		for(int var18 = 0; var18 < noStates; var18 += 1)
			v[var18] = 0.1;
		for(int var31 = 0; var31 < noEvents; var31 += 1)
			v2[var31] = 0.1;
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
		logProbability$var34 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample47)
			logProbability$var46 = 0.0;
		logProbability$var48 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample62)
			logProbability$var60 = 0.0;
		logProbability$var79 = 0.0;
		if(!fixedProbFlag$sample86)
			logProbability$weights = 0.0;
		logProbability$var81 = 0.0;
		if(!fixedProbFlag$sample88)
			logProbability$initialState = 0.0;
		logProbability$var96 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample103)
			logProbability$var97 = 0.0;
		logProbability$var128 = 0.0;
		if(!fixedProbFlag$sample136)
			logProbability$var129 = 0.0;
		logProbability$var161 = 0.0;
		logProbability$events = 0.0;
		if(!fixedProbFlag$sample171)
			logProbability$var162 = 0.0;
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
		if(fixedFlag$sample47)
			logProbabilityValue$sample47();
		if(fixedFlag$sample62)
			logProbabilityValue$sample62();
		if(fixedFlag$sample86)
			logProbabilityValue$sample86();
		if(fixedFlag$sample88)
			logProbabilityValue$sample88();
		logProbabilityValue$sample171();
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
		logProbabilityValue$sample47();
		logProbabilityValue$sample62();
		logProbabilityValue$sample86();
		logProbabilityValue$sample88();
		logProbabilityDistribution$sample103();
		logProbabilityDistribution$sample136();
		logProbabilityDistribution$sample171();
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
		logProbabilityValue$sample47();
		logProbabilityValue$sample62();
		logProbabilityValue$sample86();
		logProbabilityValue$sample88();
		logProbabilityValue$sample103();
		logProbabilityValue$sample136();
		logProbabilityValue$sample171();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		for(int var45 = 0; var45 < noStates; var45 += 1) {
			double[] var46 = m[var45];
			if(!fixedFlag$sample47)
				DistributionSampling.sampleDirichlet(RNG$, v, var46);
		}
		for(int var59 = 0; var59 < noStates; var59 += 1) {
			double[] var60 = bias[var59];
			if(!fixedFlag$sample62)
				DistributionSampling.sampleDirichlet(RNG$, v2, var60);
		}
		if(!fixedFlag$sample86)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample88)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
			int[] var93 = st[i$var92];
			if(!fixedFlag$sample103)
				var93[0] = DistributionSampling.sampleCategorical(RNG$, m[initialState]);
		}
		for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
			int[] var122 = st[i$var109];
			for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
				if(!fixedFlag$sample136)
					var122[j$var121] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var109][(j$var121 - 1)]]);
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