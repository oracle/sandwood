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
		// 
		// Substituted "fixedFlag$sample103" with its value "cv$value".
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
		
		// Should the probability of sample 136 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample103" with its value "cv$value".
		fixedProbFlag$sample136 = (cv$value && fixedProbFlag$sample136);
		
		// Should the probability of sample 171 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample103" with its value "cv$value".
		fixedProbFlag$sample171 = (cv$value && fixedProbFlag$sample171);
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
		// 
		// Substituted "fixedFlag$sample136" with its value "cv$value".
		fixedProbFlag$sample136 = (cv$value && fixedProbFlag$sample136);
		
		// Should the probability of sample 171 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample136" with its value "cv$value".
		fixedProbFlag$sample171 = (cv$value && fixedProbFlag$sample171);
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
		// 
		// Substituted "fixedFlag$sample171" with its value "cv$value".
		fixedProbFlag$sample171 = (cv$value && fixedProbFlag$sample171);
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
		// 
		// Substituted "fixedFlag$sample47" with its value "cv$value".
		fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample47" with its value "cv$value".
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
		
		// Should the probability of sample 136 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample47" with its value "cv$value".
		fixedProbFlag$sample136 = (cv$value && fixedProbFlag$sample136);
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
		// 
		// Substituted "fixedFlag$sample62" with its value "cv$value".
		fixedProbFlag$sample62 = (cv$value && fixedProbFlag$sample62);
		
		// Should the probability of sample 171 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample62" with its value "cv$value".
		fixedProbFlag$sample171 = (cv$value && fixedProbFlag$sample171);
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
		// 
		// Substituted "fixedFlag$sample86" with its value "cv$value".
		fixedProbFlag$sample86 = (cv$value && fixedProbFlag$sample86);
		
		// Should the probability of sample 88 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample86" with its value "cv$value".
		fixedProbFlag$sample88 = (cv$value && fixedProbFlag$sample88);
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
		// 
		// Substituted "fixedFlag$sample88" with its value "cv$value".
		fixedProbFlag$sample88 = (cv$value && fixedProbFlag$sample88);
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample88" with its value "cv$value".
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
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
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[i$var92][0];
					double[] var95 = m[initialState];
					
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					// 
					// Variable declaration of cv$distributionAccumulator moved.
					// Declaration comment was:
					// An accumulator for log probabilities.
					// 
					// Store the value of the function call, so the function call is only made once.
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var95.length))?Math.log(var95[cv$sampleValue]):Double.NEGATIVE_INFINITY));
				}
				logProbability$var96 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var97 = cv$sampleAccumulator;
				
				// Update the variable probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$st = (logProbability$st + cv$sampleAccumulator);
				
				// Add probability to model
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample103" with its value "true".
				fixedProbFlag$sample103 = (fixedFlag$sample47 && fixedFlag$sample88);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var96 = logProbability$var97;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample103)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$st = (logProbability$st + logProbability$var97);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var97);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample103)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var97);
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
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
					for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[i$var109][j$var121];
						
						// Enumerating the possible arguments for Categorical 128.
						if((1 == j$var121)) {
							// Enumerating the possible arguments for Categorical 128.
							if(fixedFlag$sample103) {
								int var45 = st[i$var109][0];
								
								// Substituted "j$var121" with its value "1".
								if(((0 <= var45) && (var45 < noStates))) {
									// Substituted "j$var121" with its value "1".
									double[] var127 = m[st[i$var109][0]];
									
									// Store the value of the function call, so the function call is only made once.
									cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var127.length))?Math.log(var127[cv$sampleValue]):Double.NEGATIVE_INFINITY);
									
									// Add the probability of this distribution configuration to the accumulator.
									// 
									// An accumulator for the distributed probability space covered.
									cv$probabilityReached = 1.0;
								}
							} else {
								// Enumerating the possible outputs of Categorical 96.
								for(int index$sample103$6 = 0; index$sample103$6 < noStates; index$sample103$6 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "i$var92" with its value "i$var109".
									double cv$probabilitySample103Value7 = distribution$sample103[i$var109][index$sample103$6];
									double[] var127 = m[index$sample103$6];
									
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
						
						// Substituted "index$i$13_1" with its value "i$var109".
						// 
						// Substituted "index$j$13_2" with its value "(j$var121 - 1)".
						if((2 <= j$var121)) {
							int var45 = st[i$var109][(j$var121 - 1)];
							if(((0 <= var45) && (var45 < noStates))) {
								double[] var127 = m[st[i$var109][(j$var121 - 1)]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var127.length))?Math.log(var127[cv$sampleValue]):Double.NEGATIVE_INFINITY);
								
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
						if((cv$probabilityReached == 0.0))
							// Return negative infinity if no distribution probability space is reached.
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							// Scale the probability relative to the observed distribution space.
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						
						// Add the probability of this sample task to the sample task accumulator.
						cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
					}
				}
				logProbability$var128 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var129 = cv$sampleAccumulator;
				
				// Update the variable probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$st = (logProbability$st + cv$sampleAccumulator);
				
				// Add probability to model
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample136" with its value "true".
				fixedProbFlag$sample136 = (fixedFlag$sample47 && fixedFlag$sample103);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var128 = logProbability$var129;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample136)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$st = (logProbability$st + logProbability$var129);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var129);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample136)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var129);
		}
	}

	// Calculate the probability of the samples represented by sample171 using probability
	// distributions.
	private final void logProbabilityDistribution$sample171() {
		// Determine if we need to calculate the values for sample task 171 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample171) {
			// Generating probabilities for sample task
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
					// 
					// The sample value to calculate the probability of generating
					int cv$sampleValue = (events[i$var142][j$var156] - 1);
					
					// Enumerating the possible arguments for Categorical 161.
					if(fixedFlag$sample136) {
						int var59 = st[i$var142][j$var156];
						if(((0 <= var59) && (var59 < noStates))) {
							double[] var160 = bias[st[i$var142][j$var156]];
							
							// Store the value of the function call, so the function call is only made once.
							cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var160.length))?Math.log(var160[cv$sampleValue]):Double.NEGATIVE_INFINITY);
							
							// Add the probability of this distribution configuration to the accumulator.
							// 
							// An accumulator for the distributed probability space covered.
							cv$probabilityReached = 1.0;
						}
					} else {
						// Enumerating the possible outputs of Categorical 128.
						for(int index$sample136$13 = 0; index$sample136$13 < noStates; index$sample136$13 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "i$var109" with its value "i$var142".
							double cv$probabilitySample136Value14 = distribution$sample136[i$var142][(j$var156 - 1)][index$sample136$13];
							double[] var160 = bias[index$sample136$13];
							
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
					if((cv$probabilityReached == 0.0))
						// Return negative infinity if no distribution probability space is reached.
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						// Scale the probability relative to the observed distribution space.
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				}
			}
			logProbability$var161 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var162 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$events = (logProbability$events + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample171 = (((fixedFlag$sample171 && fixedFlag$sample62) && fixedFlag$sample103) && fixedFlag$sample136);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var161 = logProbability$var162;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$events = (logProbability$events + logProbability$var162);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var162);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var162);
		}
	}

	// Calculate the probability of the samples represented by sample103 using sampled
	// values.
	private final void logProbabilityValue$sample103() {
		// Determine if we need to calculate the values for sample task 103 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample103) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = st[i$var92][0];
				double[] var95 = m[initialState];
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var95.length))?Math.log(var95[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			logProbability$var96 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var97 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$st = (logProbability$st + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample103)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample103 = ((fixedFlag$sample103 && fixedFlag$sample47) && fixedFlag$sample88);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var96 = logProbability$var97;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var97);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var97);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample103)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var97);
		}
	}

	// Calculate the probability of the samples represented by sample136 using sampled
	// values.
	private final void logProbabilityValue$sample136() {
		// Determine if we need to calculate the values for sample task 136 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample136) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[i$var109][j$var121];
					double[] var127 = m[st[i$var109][(j$var121 - 1)]];
					
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					// 
					// Variable declaration of cv$distributionAccumulator moved.
					// Declaration comment was:
					// An accumulator for log probabilities.
					// 
					// Store the value of the function call, so the function call is only made once.
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var127.length))?Math.log(var127[cv$sampleValue]):Double.NEGATIVE_INFINITY));
				}
			}
			logProbability$var128 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var129 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$st = (logProbability$st + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample136)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample136 = ((fixedFlag$sample136 && fixedFlag$sample47) && fixedFlag$sample103);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var128 = logProbability$var129;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var129);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var129);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample136)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var129);
		}
	}

	// Calculate the probability of the samples represented by sample171 using sampled
	// values.
	private final void logProbabilityValue$sample171() {
		// Determine if we need to calculate the values for sample task 171 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample171) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
				for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = (events[i$var142][j$var156] - 1);
					double[] var160 = bias[st[i$var142][j$var156]];
					
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					// 
					// Variable declaration of cv$distributionAccumulator moved.
					// Declaration comment was:
					// An accumulator for log probabilities.
					// 
					// Store the value of the function call, so the function call is only made once.
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var160.length))?Math.log(var160[cv$sampleValue]):Double.NEGATIVE_INFINITY));
				}
			}
			logProbability$var161 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var162 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$events = (logProbability$events + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample171 = (((fixedFlag$sample171 && fixedFlag$sample62) && fixedFlag$sample103) && fixedFlag$sample136);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var161 = logProbability$var162;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$events = (logProbability$events + logProbability$var162);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var162);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var162);
		}
	}

	// Calculate the probability of the samples represented by sample47 using sampled
	// values.
	private final void logProbabilityValue$sample47() {
		// Determine if we need to calculate the values for sample task 47 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample47) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var45 = 0; var45 < noStates; var45 += 1)
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var45], v));
			logProbability$var34 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var46 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample47)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample47 = fixedFlag$sample47;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var34 = logProbability$var46;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var46);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var46);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample47)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var46);
		}
	}

	// Calculate the probability of the samples represented by sample62 using sampled
	// values.
	private final void logProbabilityValue$sample62() {
		// Determine if we need to calculate the values for sample task 62 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample62) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var59 = 0; var59 < noStates; var59 += 1)
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(bias[var59], v2));
			logProbability$var48 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var60 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample62)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample62 = fixedFlag$sample62;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var48 = logProbability$var60;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$bias = (logProbability$bias + logProbability$var60);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var60);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample62)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var60);
		}
	}

	// Calculate the probability of the samples represented by sample86 using sampled
	// values.
	private final void logProbabilityValue$sample86() {
		// Determine if we need to calculate the values for sample task 86 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample86) {
			// Generating probabilities for sample task
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(weights, v);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var79 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$weights = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample86)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample86 = fixedFlag$sample86;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var79 = logProbability$weights;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$weights);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample86)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$weights);
		}
	}

	// Calculate the probability of the samples represented by sample88 using sampled
	// values.
	private final void logProbabilityValue$sample88() {
		// Determine if we need to calculate the values for sample task 88 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample88) {
			// Generating probabilities for sample task
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = (((0.0 <= initialState) && (initialState < weights.length))?Math.log(weights[initialState]):Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var81 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$initialState = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample88)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample88 = (fixedFlag$sample88 && fixedFlag$sample86);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var81 = logProbability$initialState;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$initialState);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample88)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialState);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 103 drawn from Categorical 96. Inference was performed using variable
	// marginalization.
	private final void sample103(int i$var92) {
		// Variable declaration of cv$noStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
		// cv$noStates's comment
		// Calculate the number of states to evaluate.
		int cv$noStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Variable declaration of cv$temp$0$var95 moved.
			// 
			// Constructing a random variable input for use later.
			double[] cv$temp$0$var95 = m[initialState];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var95.length)?Math.log(cv$temp$0$var95[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 < length$eventsMeasured[i$var92])) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(fixedFlag$sample136) {
					// Looking for a path between Sample 103 and consumer Categorical 128.
					// Processing sample task 136 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Value of the variable at this index
					if((cv$valuePos < noStates)) {
						// Variable declaration of cv$temp$1$var127 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double[] cv$temp$1$var127 = m[cv$valuePos];
						
						// Substituted "i$var109" with its value "i$var92".
						cv$accumulatedConsumerProbabilities = (((0.0 <= st[i$var92][1]) && (st[i$var92][1] < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[st[i$var92][1]]):Double.NEGATIVE_INFINITY);
						
						// Recorded the probability of reaching sample task 136 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					// Looking for a path between Sample 103 and consumer Categorical 128.
					// Processing sample task 136 of consumer random variable null.
					// 
					// Zero all the elements in the distribution accumulator
					for(int cv$i = 0; cv$i < noStates; cv$i += 1)
						// A local array to hold the accumulated distributions of the sample tasks for each
						// configuration of distributions.
						cv$distributionAccumulator$var128[cv$i] = 0.0;
					
					// Zero an accumulator to track the probabilities reached.
					double cv$reachedDistributionProbability = 0.0;
					
					// Value of the variable at this index
					if((cv$valuePos < noStates)) {
						// Record the reached distribution.
						// 
						// The probability of reaching the consumer with this set of consumer arguments
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						cv$reachedDistributionProbability = 1.0;
						
						// Add the current distribution to the distribution accumulator.
						// 
						// The probability of reaching the consumer with this set of consumer arguments
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						// 
						// cv$temp$3$var127's comment
						// Variable declaration of cv$temp$3$var127 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var128, 1.0, m[cv$valuePos]);
					}
					
					// A local copy of the samples' distribution.
					// 
					// Substituted "i$var109" with its value "i$var92".
					double[] cv$sampleDistribution = distribution$sample136[i$var92][0];
					
					// The overlap of the distributions so far.
					double cv$overlap = 0.0;
					
					// Calculate the overlap for each element in the distribution
					for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
						// Normalise the values in the calculated distribution
						// 
						// A local array to hold the accumulated distributions of the sample tasks for each
						// configuration of distributions.
						double cv$normalisedDistValue = (cv$distributionAccumulator$var128[cv$i] / cv$reachedDistributionProbability);
						
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
					// 
					// Initialize a log space accumulator to take the product of all the distribution
					// probabilities.
					cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var97$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample103[i$var92];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var97$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var97$stateProbabilityGlobal[cv$lseIndex];
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
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var97$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$localProbability[cv$indexName] = Math.exp((cv$var97$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var97$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 136 drawn from Categorical 128. Inference was performed using variable
	// marginalization.
	private final void sample136(int i$var109, int j$var121) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		
		// Enumerating the possible arguments for Categorical 128.
		if((1 == j$var121)) {
			// Enumerating the possible arguments for Categorical 128.
			if(fixedFlag$sample103) {
				int var45 = st[i$var109][0];
				
				// Substituted "j$var121" with its value "1".
				if(((0 <= var45) && (var45 < noStates)))
					// variable marginalization
					// 
					// cv$noStates's comment
					// Calculate the number of states to evaluate.
					cv$noStates = Math.max(0, noStates);
			} else {
				// Enumerating the possible outputs of Categorical 96.
				if((0 < noStates))
					// variable marginalization
					cv$noStates = noStates;
			}
		}
		if(fixedFlag$sample136) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((2 <= j$var121)) {
				int var45 = st[i$var109][(j$var121 - 1)];
				if(((0 <= var45) && (var45 < noStates)))
					// variable marginalization
					cv$noStates = Math.max(cv$noStates, noStates);
			}
		} else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < noStates)) {
				int index$j$14 = (j$var121 - 1);
				
				// index$i$2's comment
				// Copy of index so that its values can be safely substituted
				// 
				// Substituted "index$i$13" with its value "i$var109".
				// 
				// Substituted "index$j$14" with its value "(j$var121 - 1)".
				// 
				// Substituted "index$j$14" with its value "(j$var121 - 1)".
				// 
				// Substituted "index$j$14" with its value "(j$var121 - 1)".
				if(((1 <= index$j$14) && !(index$j$14 == j$var121)))
					// variable marginalization
					cv$noStates = Math.max(cv$noStates, noStates);
			}
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 128 creating
			// sample task 136.
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Enumerating the possible arguments for Categorical 128.
			if((1 == j$var121)) {
				// Enumerating the possible arguments for Categorical 128.
				if(fixedFlag$sample103) {
					int var45 = st[i$var109][0];
					
					// Substituted "j$var121" with its value "1".
					if(((0 <= var45) && (var45 < noStates))) {
						// Record the reached probability density.
						// 
						// Initialize a counter to track the reached distributions.
						cv$reachedDistributionSourceRV = 1.0;
						
						// Variable declaration of cv$temp$0$var127 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var121" with its value "1".
						double[] cv$temp$0$var127 = m[st[i$var109][0]];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var127.length)?Math.log(cv$temp$0$var127[cv$valuePos]):Double.NEGATIVE_INFINITY);
						
						// Substituted "j$var121" with its value "1".
						// 
						// Substituted "j$var156" with its value "1".
						if((1 < length$eventsMeasured[i$var109])) {
							// Processing sample task 171 of consumer random variable null.
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Processing random variable 161.
							// 
							// Looking for a path between Sample 136 and consumer Categorical 161.
							// 
							// Value of the variable at this index
							if((cv$valuePos < noStates)) {
								// Variable declaration of cv$temp$4$var160 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Processing random variable 161.
								// 
								// Looking for a path between Sample 136 and consumer Categorical 161.
								// 
								// Value of the variable at this index
								double[] cv$temp$4$var160 = bias[cv$valuePos];
								
								// Substituted "i$var142" with its value "i$var109".
								// 
								// Substituted "j$var156" with its value "1".
								cv$accumulatedConsumerProbabilities = (((1.0 <= events[i$var109][1]) && (events[i$var109][1] < (cv$temp$4$var160.length + 1)))?Math.log(cv$temp$4$var160[(events[i$var109][1] - 1)]):Double.NEGATIVE_INFINITY);
								
								// Recorded the probability of reaching sample task 171 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					// Enumerating the possible outputs of Categorical 96.
					for(int index$sample103$26 = 0; index$sample103$26 < noStates; index$sample103$26 += 1) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "i$var92" with its value "i$var109".
						double cv$probabilitySample103Value27 = distribution$sample103[i$var109][index$sample103$26];
						
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample103Value27);
						
						// Variable declaration of cv$temp$1$var127 moved.
						// 
						// Constructing a random variable input for use later.
						double[] cv$temp$1$var127 = m[index$sample103$26];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample103Value27) + ((cv$valuePos < cv$temp$1$var127.length)?Math.log(cv$temp$1$var127[cv$valuePos]):Double.NEGATIVE_INFINITY));
						
						// Substituted "j$var121" with its value "1".
						// 
						// Substituted "j$var156" with its value "1".
						if((1 < length$eventsMeasured[i$var109])) {
							// Processing sample task 171 of consumer random variable null.
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Processing random variable 161.
							// 
							// Looking for a path between Sample 136 and consumer Categorical 161.
							// 
							// Value of the variable at this index
							if((cv$valuePos < noStates)) {
								// Variable declaration of cv$temp$5$var160 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Processing random variable 161.
								// 
								// Looking for a path between Sample 136 and consumer Categorical 161.
								// 
								// Value of the variable at this index
								double[] cv$temp$5$var160 = bias[cv$valuePos];
								
								// Substituted "i$var142" with its value "i$var109".
								// 
								// Substituted "j$var156" with its value "1".
								cv$accumulatedConsumerProbabilities = (((1.0 <= events[i$var109][1]) && (events[i$var109][1] < (cv$temp$5$var160.length + 1)))?Math.log(cv$temp$5$var160[(events[i$var109][1] - 1)]):Double.NEGATIVE_INFINITY);
								
								// Recorded the probability of reaching sample task 171 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
			int index$j$34 = (j$var121 - 1);
			
			// index$i$23's comment
			// Copy of index so that its values can be safely substituted
			// 
			// Substituted "index$i$33" with its value "i$var109".
			// 
			// Substituted "index$j$34" with its value "(j$var121 - 1)".
			// 
			// Substituted "index$j$34" with its value "(j$var121 - 1)".
			// 
			// Substituted "index$j$34" with its value "(j$var121 - 1)".
			if(((1 <= index$j$34) && !(index$j$34 == j$var121))) {
				// Enumerating the possible outputs of Categorical 128.
				for(int index$sample136$35 = 0; index$sample136$35 < noStates; index$sample136$35 += 1) {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$i$33" with its value "i$var109".
					double cv$probabilitySample136Value36 = distribution$sample136[i$var109][(index$j$34 - 1)][index$sample136$35];
					
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample136Value36);
					
					// Variable declaration of cv$temp$3$var127 moved.
					// 
					// Constructing a random variable input for use later.
					double[] cv$temp$3$var127 = m[index$sample136$35];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample136Value36) + ((cv$valuePos < cv$temp$3$var127.length)?Math.log(cv$temp$3$var127[cv$valuePos]):Double.NEGATIVE_INFINITY));
					
					// Processing sample task 171 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Processing random variable 161.
					// 
					// Looking for a path between Sample 136 and consumer Categorical 161.
					// 
					// Value of the variable at this index
					if((cv$valuePos < noStates)) {
						// Variable declaration of cv$temp$7$var160 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Processing random variable 161.
						// 
						// Looking for a path between Sample 136 and consumer Categorical 161.
						// 
						// Value of the variable at this index
						double[] cv$temp$7$var160 = bias[cv$valuePos];
						
						// Substituted "i$var142" with its value "i$var109".
						cv$accumulatedConsumerProbabilities = (((1.0 <= events[i$var109][j$var121]) && (events[i$var109][j$var121] < (cv$temp$7$var160.length + 1)))?Math.log(cv$temp$7$var160[(events[i$var109][j$var121] - 1)]):Double.NEGATIVE_INFINITY);
						
						// Recorded the probability of reaching sample task 171 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
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
			int index$j$61_3 = (j$var121 + 1);
			if((index$j$61_3 < length$eventsMeasured[i$var109])) {
				// Processing sample task 136 of consumer random variable null.
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					cv$distributionAccumulator$var128[cv$i] = 0.0;
				
				// Zero an accumulator to track the probabilities reached.
				double cv$reachedDistributionProbability = 0.0;
				
				// Processing random variable 128.
				// 
				// Looking for a path between Sample 136 and consumer Categorical 128.
				// 
				// Value of the variable at this index
				if((cv$valuePos < noStates)) {
					// Declare and zero an accumulator for tracking the reached source probability space.
					double scopeVariable$reachedSourceProbability = 0.0;
					
					// Enumerating the possible arguments for Categorical 128.
					if((1 == j$var121)) {
						// Enumerating the possible arguments for Categorical 128.
						if(fixedFlag$sample103) {
							int index$var45$72_1 = st[i$var109][0];
							
							// Substituted "j$var121" with its value "1".
							if(((0 <= index$var45$72_1) && (index$var45$72_1 < noStates)))
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							// Enumerating the possible outputs of Categorical 96.
							for(int index$sample103$68 = 0; index$sample103$68 < noStates; index$sample103$68 += 1)
								// Add the probability of this argument configuration.
								// 
								// cv$probabilitySample103Value69's comment
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "i$var92" with its value "i$var109".
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample103[i$var109][index$sample103$68]);
						}
					}
					int index$j$76 = (j$var121 - 1);
					
					// index$j$63's comment
					// Copy of index so that its values can be safely substituted
					// 
					// index$i$64's comment
					// Copy of index so that its values can be safely substituted
					// 
					// Substituted "index$i$61_2" with its value "i$var109".
					// 
					// Substituted "index$j$61_3" with its value "(j$var121 + 1)".
					// 
					// Substituted "index$j$61_3" with its value "(j$var121 + 1)".
					// 
					// Substituted "index$j$61_3" with its value "(j$var121 + 1)".
					if((((1 <= index$j$76) && !(index$j$76 == j$var121)) && !(index$j$76 == index$j$61_3))) {
						// Enumerating the possible outputs of Categorical 128.
						for(int index$sample136$77 = 0; index$sample136$77 < noStates; index$sample136$77 += 1)
							// Add the probability of this argument configuration.
							// 
							// cv$probabilitySample136Value78's comment
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$i$75" with its value "i$var109".
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample136[i$var109][(index$j$76 - 1)][index$sample136$77]);
					}
					
					// Record the reached distribution.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Zero an accumulator to track the probabilities reached.
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					
					// Add the current distribution to the distribution accumulator.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// cv$temp$8$var127's comment
					// Variable declaration of cv$temp$8$var127 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Processing random variable 128.
					// 
					// Looking for a path between Sample 136 and consumer Categorical 128.
					// 
					// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var128, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				}
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "index$i$61_2" with its value "i$var109".
				double[] cv$sampleDistribution = distribution$sample136[i$var109][(index$j$61_3 - 1)];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					// 
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					double cv$normalisedDistValue = (cv$distributionAccumulator$var128[cv$i] / cv$reachedDistributionProbability);
					
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
				// 
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			cv$var129$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample136[i$var109][(j$var121 - 1)];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var129$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var129$stateProbabilityGlobal[cv$lseIndex];
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
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var129$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$localProbability[cv$indexName] = Math.exp((cv$var129$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var129$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 47 drawn from Dirichlet 34. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample47(int var45) {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var46$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(((var45 == initialState) && fixedFlag$sample103)) {
			// Looking for a path between Sample 47 and consumer Categorical 96.
			for(int i$var92 = 0; i$var92 < samples; i$var92 += 1)
				// Processing sample task 103 of consumer random variable null.
				// 
				// Increment the sample counter with the value sampled by sample task 103 of random
				// variable var96
				// 
				// A local reference to the scratch space.
				cv$var46$countGlobal[st[i$var92][0]] = (cv$var46$countGlobal[st[i$var92][0]] + 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample136) {
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 < length$eventsMeasured[i$var109])) {
					if(fixedFlag$sample103) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var45 == st[i$var109][0]))
							// Increment the sample counter with the value sampled by sample task 136 of random
							// variable var128
							// 
							// A local reference to the scratch space.
							cv$var46$countGlobal[st[i$var109][1]] = (cv$var46$countGlobal[st[i$var109][1]] + 1.0);
					} else
						// Processing sample task 136 of consumer random variable null.
						// 
						// Increment the sample counter with the value sampled by sample task 136 of random
						// variable var128
						// 
						// A local reference to the scratch space.
						// 
						// Substituted "index$sample103$9" with its value "var45".
						cv$var46$countGlobal[st[i$var109][1]] = (cv$var46$countGlobal[st[i$var109][1]] + distribution$sample103[i$var109][var45]);
				}
			}
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				for(int j$var121 = 2; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
					if((var45 == st[i$var109][(j$var121 - 1)]))
						// Processing sample task 136 of consumer random variable null.
						// 
						// Increment the sample counter with the value sampled by sample task 136 of random
						// variable var128
						// 
						// A local reference to the scratch space.
						cv$var46$countGlobal[st[i$var109][j$var121]] = (cv$var46$countGlobal[st[i$var109][j$var121]] + 1.0);
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(((var45 == initialState) && !fixedFlag$sample103)) {
			// Looking for a path between Sample 47 and consumer Categorical 96.
			for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
				// Processing sample task 103 of consumer random variable null.
				// 
				// Merge the distribution probabilities into the count
				// 
				// Get the length of the array
				for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
					// A local reference to the scratch space.
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					cv$var46$countGlobal[cv$loopIndex] = (cv$var46$countGlobal[cv$loopIndex] + distribution$sample103[i$var92][cv$loopIndex]);
			}
		}
		
		// Processing random variable 128.
		// 
		// Looking for a path between Sample 47 and consumer Categorical 128.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample136) {
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 < length$eventsMeasured[i$var109])) {
					if(fixedFlag$sample103) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var45 == st[i$var109][0])) {
							// Merge the distribution probabilities into the count
							// 
							// Get the length of the array
							for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
								// A local reference to the scratch space.
								// 
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								cv$var46$countGlobal[cv$loopIndex] = (cv$var46$countGlobal[cv$loopIndex] + distribution$sample136[i$var109][0][cv$loopIndex]);
						}
					} else {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
						// Substituted "i$var92" with its value "i$var109".
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						// 
						// Substituted "index$sample103$49" with its value "var45".
						double cv$distributionProbability = distribution$sample103[i$var109][var45];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							// A local reference to the scratch space.
							cv$var46$countGlobal[cv$loopIndex] = (cv$var46$countGlobal[cv$loopIndex] + (distribution$sample136[i$var109][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
					int index$j$59 = (j$var121 - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Substituted "index$i$58" with its value "i$var109".
					if((1 <= index$j$59)) {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
						// Substituted "index$i$58" with its value "i$var109".
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						// 
						// Substituted "index$sample136$60" with its value "var45".
						double cv$distributionProbability = distribution$sample136[i$var109][(index$j$59 - 1)][var45];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							// A local reference to the scratch space.
							cv$var46$countGlobal[cv$loopIndex] = (cv$var46$countGlobal[cv$loopIndex] + (distribution$sample136[i$var109][(j$var121 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var46$countGlobal, m[var45]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 62 drawn from Dirichlet 48. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample62(int var59) {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noEvents; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var60$countGlobal[cv$loopIndex] = 0.0;
		
		// Processing random variable 161.
		// 
		// Looking for a path between Sample 62 and consumer Categorical 161.
		for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
			for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
				if(fixedFlag$sample136) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var59 == st[i$var142][j$var156]))
						// Processing sample task 171 of consumer random variable null.
						// 
						// Increment the sample counter with the value sampled by sample task 171 of random
						// variable var161
						// 
						// A local reference to the scratch space.
						cv$var60$countGlobal[(events[i$var142][j$var156] - 1)] = (cv$var60$countGlobal[(events[i$var142][j$var156] - 1)] + 1.0);
				} else
					// Processing sample task 171 of consumer random variable null.
					// 
					// Increment the sample counter with the value sampled by sample task 171 of random
					// variable var161
					// 
					// A local reference to the scratch space.
					// 
					// Substituted "index$sample136$16" with its value "var59".
					cv$var60$countGlobal[(events[i$var142][j$var156] - 1)] = (cv$var60$countGlobal[(events[i$var142][j$var156] - 1)] + distribution$sample136[i$var142][(j$var156 - 1)][var59]);
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v2, cv$var60$countGlobal, bias[var59]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 86 drawn from Dirichlet 79. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample86() {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var80$countGlobal[cv$loopIndex] = 0.0;
		
		// Processing random variable 81.
		// 
		// Processing sample task 88 of consumer random variable null.
		// 
		// Increment the sample counter with the value sampled by sample task 88 of random
		// variable var81
		// 
		// A local reference to the scratch space.
		cv$var80$countGlobal[initialState] = (cv$var80$countGlobal[initialState] + 1.0);
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var80$countGlobal, weights);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 88 drawn from Categorical 81. Inference was performed using variable
	// marginalization.
	private final void sample88() {
		// Variable declaration of cv$noStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
		// cv$noStates's comment
		// Calculate the number of states to evaluate.
		int cv$noStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			initialState = cv$valuePos;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$weights" with its value "weights".
			double cv$accumulatedProbabilities = ((cv$valuePos < weights.length)?Math.log(weights[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(fixedFlag$sample103) {
				for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
					// Variable declaration of cv$temp$1$var95 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					double[] cv$temp$1$var95 = m[cv$valuePos];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 103 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 103 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((((0.0 <= st[i$var92][0]) && (st[i$var92][0] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[st[i$var92][0]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
					// Zero all the elements in the distribution accumulator
					for(int cv$i = 0; cv$i < noStates; cv$i += 1)
						// A local array to hold the accumulated distributions of the sample tasks for each
						// configuration of distributions.
						cv$distributionAccumulator$var96[cv$i] = 0.0;
					
					// Add the current distribution to the distribution accumulator.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					// 
					// cv$temp$2$var95's comment
					// Variable declaration of cv$temp$2$var95 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var96, 1.0, m[cv$valuePos]);
					
					// A local copy of the samples' distribution.
					double[] cv$sampleDistribution = distribution$sample103[i$var92];
					
					// The overlap of the distributions so far.
					double cv$overlap = 0.0;
					
					// Calculate the overlap for each element in the distribution
					for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
						// Normalise the values in the calculated distribution
						// 
						// A local array to hold the accumulated distributions of the sample tasks for each
						// configuration of distributions.
						// 
						// Variable declaration of cv$reachedDistributionProbability moved.
						// Declaration comment was:
						// Zero an accumulator to track the probabilities reached.
						// 
						// Record the reached distribution.
						// 
						// The probability of reaching the consumer with this set of consumer arguments
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						double cv$normalisedDistValue = cv$distributionAccumulator$var96[cv$i];
						
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
					// 
					// Variable declaration of cv$reachedDistributionProbability moved.
					// Declaration comment was:
					// Zero an accumulator to track the probabilities reached.
					// 
					// Record the reached distribution.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					cv$accumulatedDistributionProbabilities = (cv$accumulatedDistributionProbabilities + Math.log(cv$overlap));
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var82$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var82$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var82$stateProbabilityGlobal[cv$lseIndex];
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
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var82$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var82$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var82$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var82$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var82$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var82$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the new value of the sample.
		// 
		// Get a local reference to the scratch space.
		initialState = DistributionSampling.sampleCategorical(RNG$, cv$var82$stateProbabilityGlobal);
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
			if((0 < noStates))
				cv$max = noStates;
			
			// Allocation of cv$var46$countGlobal for single threaded execution
			cv$var46$countGlobal = new double[cv$max];
		}
		
		// Constructor for cv$var60$countGlobal
		// 
		// Calculate the longest array this random variable could produce and allocate an
		// array large enough to handle this.
		int cv$max = 0;
		if((0 < noStates))
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			cv$max = Math.max(0, noEvents);
		
		// Allocation of cv$var60$countGlobal for single threaded execution
		cv$var60$countGlobal = new double[cv$max];
		
		// Allocation of cv$var80$countGlobal for single threaded execution
		// 
		// Calculate the longest array this random variable could produce and allocate an
		// array large enough to handle this.
		cv$var80$countGlobal = new double[Math.max(0, noStates)];
		
		// Constructor for cv$distributionAccumulator$var96
		// 
		// Allocation of cv$distributionAccumulator$var96 for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 101. Initially set to the value
		// of putTask 48.
		cv$distributionAccumulator$var96 = new double[noStates];
		
		// Constructor for cv$var82$stateProbabilityGlobal
		// 
		// Allocation of cv$var82$stateProbabilityGlobal for single threaded execution
		cv$var82$stateProbabilityGlobal = new double[noStates];
		
		// Constructor for cv$distributionAccumulator$var128
		// 
		// Allocation of cv$distributionAccumulator$var128 for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 134. Initially set to the value
		// of putTask 48.
		cv$distributionAccumulator$var128 = new double[noStates];
		
		// Constructor for cv$var97$stateProbabilityGlobal
		// 
		// Allocation of cv$var97$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 101. Initially set to the value
		// of putTask 48.
		cv$var97$stateProbabilityGlobal = new double[noStates];
		
		// Allocation of cv$var129$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 134. Initially set to the value
		// of putTask 48.
		cv$var129$stateProbabilityGlobal = new double[noStates];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for v
		v = new double[noStates];
		
		// Constructor for v2
		v2 = new double[noEvents];
		
		// If m has not been set already allocate space.
		if(!setFlag$m) {
			// Constructor for m
			m = new double[noStates][];
			for(int var45 = 0; var45 < noStates; var45 += 1)
				m[var45] = new double[noStates];
		}
		
		// If bias has not been set already allocate space.
		if(!setFlag$bias) {
			// Constructor for bias
			bias = new double[noStates][];
			for(int var59 = 0; var59 < noStates; var59 += 1)
				bias[var59] = new double[noEvents];
		}
		
		// If st has not been set already allocate space.
		if(!setFlag$st) {
			// Constructor for st
			st = new int[length$eventsMeasured.length][];
			for(int i$var73 = 0; i$var73 < length$eventsMeasured.length; i$var73 += 1)
				st[i$var73] = new int[length$eventsMeasured[i$var73]];
		}
		
		// If weights has not been set already allocate space.
		if(!setFlag$weights)
			// Constructor for weights
			weights = new double[noStates];
		
		// If events has not been set already allocate space.
		if(!setFlag$events) {
			// Constructor for events
			events = new int[length$eventsMeasured.length][];
			for(int i$var142 = 0; i$var142 < length$eventsMeasured.length; i$var142 += 1)
				events[i$var142] = new int[length$eventsMeasured[i$var142]];
		}
		
		// Constructor for distribution$sample103
		distribution$sample103 = new double[length$eventsMeasured.length][];
		for(int i$var92 = 0; i$var92 < length$eventsMeasured.length; i$var92 += 1)
			distribution$sample103[i$var92] = new double[noStates];
		
		// Constructor for distribution$sample136
		distribution$sample136 = new double[length$eventsMeasured.length][][];
		for(int i$var109 = 0; i$var109 < length$eventsMeasured.length; i$var109 += 1) {
			double[][] subarray$0 = new double[(length$eventsMeasured[i$var109] - 1)][];
			distribution$sample136[i$var109] = subarray$0;
			for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1)
				subarray$0[(j$var121 - 1)] = new double[noStates];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample47) {
			for(int var45 = 0; var45 < noStates; var45 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var45]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample62) {
			for(int var59 = 0; var59 < noStates; var59 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v2, bias[var59]);
		}
		if(!fixedFlag$sample86)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample88)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample103) {
			for(int i$var92 = 0; i$var92 < samples; i$var92 += 1)
				st[i$var92][0] = DistributionSampling.sampleCategorical(RNG$, m[initialState]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample136) {
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				int[] var122 = st[i$var109];
				for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1)
					var122[j$var121] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var109][(j$var121 - 1)]]);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample171) {
			for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
				int[] var157 = events[i$var142];
				for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1)
					var157[j$var156] = (DistributionSampling.sampleCategorical(RNG$, bias[st[i$var142][j$var156]]) + 1);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample47) {
			for(int var45 = 0; var45 < noStates; var45 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var45]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample62) {
			for(int var59 = 0; var59 < noStates; var59 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v2, bias[var59]);
		}
		if(!fixedFlag$sample86)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample88)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample103) {
			for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
				// Create local copy of variable probabilities.
				double[] cv$distribution$sample103 = distribution$sample103[i$var92];
				double[] var95 = m[initialState];
				for(int index$var96 = 0; index$var96 < noStates; index$var96 += 1)
					// Save the probability of each value
					// 
					// Probability for this value
					cv$distribution$sample103[index$var96] = ((index$var96 < var95.length)?var95[index$var96]:0.0);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample136) {
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
					// Create local copy of variable probabilities.
					double[] cv$distribution$sample136 = distribution$sample136[i$var109][(j$var121 - 1)];
					for(int index$var128 = 0; index$var128 < noStates; index$var128 += 1)
						// Zero the probability of each value
						cv$distribution$sample136[index$var128] = 0.0;
					
					// Iterate through possible values for var128's arguments.
					// 
					// Enumerating the possible arguments for Categorical 128.
					if((1 == j$var121)) {
						// Iterate through possible values for var128's arguments.
						// 
						// Enumerating the possible arguments for Categorical 128.
						if(fixedFlag$sample103) {
							int var45 = st[i$var109][0];
							
							// Substituted "j$var121" with its value "1".
							if(((0 <= var45) && (var45 < noStates))) {
								// Substituted "j$var121" with its value "1".
								double[] var127 = m[st[i$var109][0]];
								for(int index$var128 = 0; index$var128 < noStates; index$var128 += 1)
									// Save the probability of each value
									cv$distribution$sample136[index$var128] = (cv$distribution$sample136[index$var128] + ((index$var128 < var127.length)?var127[index$var128]:0.0));
							}
						} else {
							// Enumerating the possible outputs of Categorical 96.
							for(int index$sample103$3 = 0; index$sample103$3 < noStates; index$sample103$3 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "i$var92" with its value "i$var109".
								double cv$probabilitySample103Value4 = distribution$sample103[i$var109][index$sample103$3];
								double[] var127 = m[index$sample103$3];
								for(int index$var128 = 0; index$var128 < noStates; index$var128 += 1)
									// Save the probability of each value
									cv$distribution$sample136[index$var128] = (cv$distribution$sample136[index$var128] + (cv$probabilitySample103Value4 * ((index$var128 < var127.length)?var127[index$var128]:0.0)));
							}
						}
					}
					int index$j$11 = (j$var121 - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Substituted "index$i$10" with its value "i$var109".
					if((1 <= index$j$11)) {
						// Enumerating the possible outputs of Categorical 128.
						for(int index$sample136$12 = 0; index$sample136$12 < noStates; index$sample136$12 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$i$10" with its value "i$var109".
							double cv$probabilitySample136Value13 = distribution$sample136[i$var109][(index$j$11 - 1)][index$sample136$12];
							double[] var127 = m[index$sample136$12];
							for(int index$var128 = 0; index$var128 < noStates; index$var128 += 1)
								// Save the probability of each value
								cv$distribution$sample136[index$var128] = (cv$distribution$sample136[index$var128] + (cv$probabilitySample136Value13 * ((index$var128 < var127.length)?var127[index$var128]:0.0)));
						}
					}
					
					// Sum the values in the array
					double cv$var128$sum = 0.0;
					for(int index$var128 = 0; index$var128 < noStates; index$var128 += 1)
						// sum the probability of each value
						cv$var128$sum = (cv$var128$sum + cv$distribution$sample136[index$var128]);
					for(int index$var128 = 0; index$var128 < noStates; index$var128 += 1)
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
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample47) {
			for(int var45 = 0; var45 < noStates; var45 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var45]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample62) {
			for(int var59 = 0; var59 < noStates; var59 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v2, bias[var59]);
		}
		if(!fixedFlag$sample86)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample88)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample103) {
			for(int i$var92 = 0; i$var92 < samples; i$var92 += 1)
				st[i$var92][0] = DistributionSampling.sampleCategorical(RNG$, m[initialState]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample136) {
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				int[] var122 = st[i$var109];
				for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1)
					var122[j$var121] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var109][(j$var121 - 1)]]);
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample47) {
				for(int var45 = 0; var45 < noStates; var45 += 1)
					sample47(var45);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample62) {
				for(int var59 = 0; var59 < noStates; var59 += 1)
					sample62(var59);
			}
			if(!fixedFlag$sample86)
				sample86();
			if(!fixedFlag$sample88)
				sample88();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample103) {
				for(int i$var92 = 0; i$var92 < samples; i$var92 += 1)
					sample103(i$var92);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample136) {
				for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
					for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1)
						sample136(i$var109, j$var121);
				}
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample136) {
				for(int i$var109 = (samples - 1); i$var109 >= 0; i$var109 -= 1) {
					for(int j$var121 = (length$eventsMeasured[i$var109] - 1); j$var121 >= 1; j$var121 -= 1)
						sample136(i$var109, j$var121);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample103) {
				for(int i$var92 = (samples - 1); i$var92 >= 0; i$var92 -= 1)
					sample103(i$var92);
			}
			if(!fixedFlag$sample88)
				sample88();
			if(!fixedFlag$sample86)
				sample86();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample62) {
				for(int var59 = (noStates - 1); var59 >= 0; var59 -= 1)
					sample62(var59);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample47) {
				for(int var45 = (noStates - 1); var45 >= 0; var45 -= 1)
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
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample47) {
			for(int var45 = 0; var45 < noStates; var45 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var45]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample62) {
			for(int var59 = 0; var59 < noStates; var59 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v2, bias[var59]);
		}
		if(!fixedFlag$sample86)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample88)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample103) {
			for(int i$var92 = 0; i$var92 < samples; i$var92 += 1)
				st[i$var92][0] = DistributionSampling.sampleCategorical(RNG$, m[initialState]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample136) {
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				int[] var122 = st[i$var109];
				for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1)
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
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = events.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = eventsMeasured[cv$index1];
			int[] cv$target2 = events[cv$index1];
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