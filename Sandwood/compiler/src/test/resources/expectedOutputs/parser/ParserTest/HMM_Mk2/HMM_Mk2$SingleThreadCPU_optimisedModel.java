package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMM_Mk2$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMM_Mk2$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] bias;
	private double[] cv$var25$countGlobal;
	private double[] cv$var32$countGlobal;
	private double[] cv$var45$countGlobal;
	private double[] cv$var47$stateProbabilityGlobal;
	private double[] cv$var55$stateProbabilityGlobal;
	private double[] cv$var74$stateProbabilityGlobal;
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

	public HMM_Mk2$SingleThreadCPU(ExecutionTarget target) {
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
		// Set bias with flag to mark that it has been set so another array doesn't need to
		// be constructed
		bias = cv$value;
		setFlag$bias = true;
	}

	// Getter for events.
	@Override
	public final int[][] get$events() {
		return events;
	}

	// Setter for events.
	@Override
	public final void set$events(int[][] cv$value) {
		// Set events with flag to mark that it has been set so another array doesn't need
		// to be constructed
		events = cv$value;
		setFlag$events = true;
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
		// 
		// Substituted "fixedFlag$sample26" with its value "cv$value".
		fixedProbFlag$sample26 = (cv$value && fixedProbFlag$sample26);
		
		// Should the probability of sample 61 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample26" with its value "cv$value".
		fixedProbFlag$sample61 = (cv$value && fixedProbFlag$sample61);
		
		// Should the probability of sample 81 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample26" with its value "cv$value".
		fixedProbFlag$sample81 = (cv$value && fixedProbFlag$sample81);
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
		// 
		// Substituted "fixedFlag$sample34" with its value "cv$value".
		fixedProbFlag$sample34 = (cv$value && fixedProbFlag$sample34);
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample34" with its value "cv$value".
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
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
		// 
		// Substituted "fixedFlag$sample51" with its value "cv$value".
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
		
		// Should the probability of sample 53 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample51" with its value "cv$value".
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
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
		// 
		// Substituted "fixedFlag$sample53" with its value "cv$value".
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
		
		// Should the probability of sample 61 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample53" with its value "cv$value".
		fixedProbFlag$sample61 = (cv$value && fixedProbFlag$sample61);
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
		// 
		// Substituted "fixedFlag$sample61" with its value "cv$value".
		fixedProbFlag$sample61 = (cv$value && fixedProbFlag$sample61);
		
		// Should the probability of sample 81 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample61" with its value "cv$value".
		fixedProbFlag$sample81 = (cv$value && fixedProbFlag$sample81);
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample61" with its value "cv$value".
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
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
		// 
		// Substituted "fixedFlag$sample81" with its value "cv$value".
		fixedProbFlag$sample81 = (cv$value && fixedProbFlag$sample81);
		
		// Should the probability of sample 103 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample81" with its value "cv$value".
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
		initialState = cv$value;
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
		// Set m with flag to mark that it has been set so another array doesn't need to be
		// constructed
		m = cv$value;
		setFlag$m = true;
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
		// Set st with flag to mark that it has been set so another array doesn't need to
		// be constructed
		st = cv$value;
		setFlag$st = true;
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
		// Set weights with flag to mark that it has been set so another array doesn't need
		// to be constructed
		weights = cv$value;
		setFlag$weights = true;
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
					// The sample value to calculate the probability of generating
					int cv$sampleValue = (events[i$var80][j$var88] - 1);
					double[] var92 = bias[st[i$var80][j$var88]];
					
					// Variable declaration of cv$distributionAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$distributionAccumulator moved.
					// Declaration comment was:
					// An accumulator for log probabilities.
					// 
					// Store the value of the function call, so the function call is only made once.
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
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var92.length))?Math.log(var92[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					// 
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					logProbability$var93[i$var80][(j$var88 - 1)] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample103[i$var80][(j$var88 - 1)] = cv$distributionAccumulator;
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
					// Variable declaration of cv$rvAccumulator moved.
					double cv$rvAccumulator = logProbability$sample103[i$var80][(j$var88 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var93[i$var80][(j$var88 - 1)] = cv$rvAccumulator;
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
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var24 = 0; var24 < noStates; var24 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var24], v));
			logProbability$var20 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var25 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample26)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample26 = fixedFlag$sample26;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var20 = logProbability$var25;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var25);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var25);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample26)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var25);
		}
	}

	// Calculate the probability of the samples represented by sample34 using sampled
	// values.
	private final void logProbabilityValue$sample34() {
		// Determine if we need to calculate the values for sample task 34 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample34) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var31 = 0; var31 < noStates; var31 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(bias[var31], v2));
			logProbability$var27 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var32 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample34)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample34 = fixedFlag$sample34;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var27 = logProbability$var32;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$bias = (logProbability$bias + logProbability$var32);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var32);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample34)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var32);
		}
	}

	// Calculate the probability of the samples represented by sample51 using sampled
	// values.
	private final void logProbabilityValue$sample51() {
		// Determine if we need to calculate the values for sample task 51 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample51) {
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
			logProbability$var44 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample51)
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
			fixedProbFlag$sample51 = fixedFlag$sample51;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var44 = logProbability$weights;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$weights);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample51)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$weights);
		}
	}

	// Calculate the probability of the samples represented by sample53 using sampled
	// values.
	private final void logProbabilityValue$sample53() {
		// Determine if we need to calculate the values for sample task 53 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample53) {
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
			logProbability$var46 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample53)
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
			fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedFlag$sample51);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var46 = logProbability$initialState;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$initialState);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample53)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialState);
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
				// The sample value to calculate the probability of generating
				int cv$sampleValue = st[i$var50][0];
				double[] var53 = m[initialState];
				
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
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
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var53.length))?Math.log(var53[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$var54[i$var50] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample61[i$var50] = cv$distributionAccumulator;
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
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample61[i$var50];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var54[i$var50] = cv$rvAccumulator;
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
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[i$var60][j$var66];
					double[] var72 = m[st[i$var60][(j$var66 - 1)]];
					
					// Variable declaration of cv$distributionAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$distributionAccumulator moved.
					// Declaration comment was:
					// An accumulator for log probabilities.
					// 
					// Store the value of the function call, so the function call is only made once.
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
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var72.length))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					// 
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					logProbability$var73[i$var60][(j$var66 - 1)] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample81[i$var60][(j$var66 - 1)] = cv$distributionAccumulator;
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
					// Variable declaration of cv$rvAccumulator moved.
					double cv$rvAccumulator = logProbability$sample81[i$var60][(j$var66 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var73[i$var60][(j$var66 - 1)] = cv$rvAccumulator;
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
	private final void sample26(int var24) {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var25$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var24 == initialState)) {
			// Looking for a path between Sample 26 and consumer Categorical 54.
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1)
				// Processing sample task 61 of consumer random variable null.
				// 
				// Increment the sample counter with the value sampled by sample task 61 of random
				// variable var54
				// 
				// A local reference to the scratch space.
				cv$var25$countGlobal[st[i$var50][0]] = (cv$var25$countGlobal[st[i$var50][0]] + 1.0);
		}
		
		// Processing random variable 73.
		// 
		// Looking for a path between Sample 26 and consumer Categorical 73.
		for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
			for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
				if((var24 == st[i$var60][(j$var66 - 1)]))
					// Processing sample task 81 of consumer random variable null.
					// 
					// Increment the sample counter with the value sampled by sample task 81 of random
					// variable var73
					// 
					// A local reference to the scratch space.
					cv$var25$countGlobal[st[i$var60][j$var66]] = (cv$var25$countGlobal[st[i$var60][j$var66]] + 1.0);
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var25$countGlobal, m[var24]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 34 drawn from Dirichlet 27. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample34(int var31) {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noEvents; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var32$countGlobal[cv$loopIndex] = 0.0;
		
		// Processing random variable 93.
		// 
		// Looking for a path between Sample 34 and consumer Categorical 93.
		for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
			for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
				if((var31 == st[i$var80][j$var88]))
					// Processing sample task 103 of consumer random variable null.
					// 
					// Increment the sample counter with the value sampled by sample task 103 of random
					// variable var93
					// 
					// A local reference to the scratch space.
					cv$var32$countGlobal[(events[i$var80][j$var88] - 1)] = (cv$var32$countGlobal[(events[i$var80][j$var88] - 1)] + 1.0);
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v2, cv$var32$countGlobal, bias[var31]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 51 drawn from Dirichlet 44. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample51() {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var45$countGlobal[cv$loopIndex] = 0.0;
		
		// Processing random variable 46.
		// 
		// Processing sample task 53 of consumer random variable null.
		// 
		// Increment the sample counter with the value sampled by sample task 53 of random
		// variable var46
		// 
		// A local reference to the scratch space.
		cv$var45$countGlobal[initialState] = (cv$var45$countGlobal[initialState] + 1.0);
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var45$countGlobal, weights);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 53 drawn from Categorical 46. Inference was performed using variable
	// marginalization.
	private final void sample53() {
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			initialState = cv$valuePos;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$weights" with its value "weights".
			double cv$accumulatedProbabilities = ((cv$valuePos < weights.length)?Math.log(weights[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Processing random variable 54.
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
				// Variable declaration of cv$temp$1$var53 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				double[] cv$temp$1$var53 = m[cv$valuePos];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 61 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 61 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= st[i$var50][0]) && (st[i$var50][0] < cv$temp$1$var53.length))?Math.log(cv$temp$1$var53[st[i$var50][0]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var47$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
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
		double cv$lseMax = cv$var47$stateProbabilityGlobal[0];
		
		// Find max value.
		// 
		// Get a local reference to the scratch space.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var47$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var47$stateProbabilityGlobal[cv$lseIndex];
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
			// 
			// Get a local reference to the scratch space.
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var47$stateProbabilityGlobal.length; cv$lseIndex += 1)
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var47$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var47$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var47$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$var47$stateProbabilityGlobal.length);
		} else {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var47$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var47$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var47$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Write out the new value of the sample.
		// 
		// Get a local reference to the scratch space.
		initialState = DistributionSampling.sampleCategorical(RNG$, cv$var47$stateProbabilityGlobal);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 61 drawn from Categorical 54. Inference was performed using variable
	// marginalization.
	private final void sample61(int i$var50) {
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			// Value of the variable at this index
			st[i$var50][0] = cv$valuePos;
			
			// Variable declaration of cv$temp$0$var53 moved.
			// 
			// Constructing a random variable input for use later.
			double[] cv$temp$0$var53 = m[initialState];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var53.length)?Math.log(cv$temp$0$var53[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Substituted "j$var66" with its value "1".
			if((1 < length$eventsMeasured[i$var50])) {
				// Processing sample task 81 of consumer random variable null.
				// Variable declaration of cv$temp$1$var72 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 73.
				// 
				// Looking for a path between Sample 61 and consumer Categorical 73.
				// 
				// Value of the variable at this index
				double[] cv$temp$1$var72 = m[cv$valuePos];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 81 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var60" with its value "i$var50".
				cv$accumulatedProbabilities = ((((0.0 <= st[i$var50][1]) && (st[i$var50][1] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[i$var50][1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var55$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
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
		double cv$lseMax = cv$var55$stateProbabilityGlobal[0];
		
		// Find max value.
		// 
		// Get a local reference to the scratch space.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var55$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var55$stateProbabilityGlobal[cv$lseIndex];
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
			// 
			// Get a local reference to the scratch space.
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var55$stateProbabilityGlobal.length; cv$lseIndex += 1)
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var55$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var55$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var55$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$var55$stateProbabilityGlobal.length);
		} else {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var55$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var55$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var55$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Get a local reference to the scratch space.
		st[i$var50][0] = DistributionSampling.sampleCategorical(RNG$, cv$var55$stateProbabilityGlobal);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 81 drawn from Categorical 73. Inference was performed using variable
	// marginalization.
	private final void sample81(int i$var60, int j$var66) {
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			// Value of the variable at this index
			st[i$var60][j$var66] = cv$valuePos;
			
			// Variable declaration of cv$temp$0$var72 moved.
			// 
			// Constructing a random variable input for use later.
			double[] cv$temp$0$var72 = m[st[i$var60][(j$var66 - 1)]];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var72.length)?Math.log(cv$temp$0$var72[cv$valuePos]):Double.NEGATIVE_INFINITY);
			int index$j$1_3 = (j$var66 + 1);
			if((index$j$1_3 < length$eventsMeasured[i$var60])) {
				// Processing sample task 81 of consumer random variable null.
				// Variable declaration of cv$temp$1$var72 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 73.
				// 
				// Looking for a path between Sample 81 and consumer Categorical 73.
				// 
				// Value of the variable at this index
				double[] cv$temp$1$var72 = m[cv$valuePos];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 81 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "index$i$1_2" with its value "i$var60".
				cv$accumulatedProbabilities = ((((0.0 <= st[i$var60][index$j$1_3]) && (st[i$var60][index$j$1_3] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[i$var60][index$j$1_3]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Variable declaration of cv$temp$2$var92 moved.
			// 
			// Constructing a random variable input for use later.
			// 
			// Processing random variable 93.
			// 
			// Looking for a path between Sample 81 and consumer Categorical 93.
			// 
			// Value of the variable at this index
			double[] cv$temp$2$var92 = bias[cv$valuePos];
			
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
			// 
			// Substituted "i$var80" with its value "i$var60".
			cv$accumulatedProbabilities = ((((1.0 <= events[i$var60][j$var66]) && (events[i$var60][j$var66] < (cv$temp$2$var92.length + 1)))?Math.log(cv$temp$2$var92[(events[i$var60][j$var66] - 1)]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var74$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
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
		double cv$lseMax = cv$var74$stateProbabilityGlobal[0];
		
		// Find max value.
		// 
		// Get a local reference to the scratch space.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var74$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var74$stateProbabilityGlobal[cv$lseIndex];
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
			// 
			// Get a local reference to the scratch space.
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var74$stateProbabilityGlobal.length; cv$lseIndex += 1)
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var74$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var74$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var74$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$var74$stateProbabilityGlobal.length);
		} else {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var74$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var74$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var74$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Get a local reference to the scratch space.
		st[i$var60][j$var66] = DistributionSampling.sampleCategorical(RNG$, cv$var74$stateProbabilityGlobal);
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
			if((0 < noStates))
				cv$max = noStates;
			
			// Allocation of cv$var25$countGlobal for single threaded execution
			cv$var25$countGlobal = new double[cv$max];
		}
		
		// Constructor for cv$var32$countGlobal
		// 
		// Calculate the longest array this random variable could produce and allocate an
		// array large enough to handle this.
		int cv$max = 0;
		if((0 < noStates))
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			cv$max = Math.max(0, noEvents);
		
		// Allocation of cv$var32$countGlobal for single threaded execution
		cv$var32$countGlobal = new double[cv$max];
		
		// Allocation of cv$var45$countGlobal for single threaded execution
		// 
		// Calculate the longest array this random variable could produce and allocate an
		// array large enough to handle this.
		cv$var45$countGlobal = new double[Math.max(0, noStates)];
		
		// Constructor for cv$var47$stateProbabilityGlobal
		// 
		// Allocation of cv$var47$stateProbabilityGlobal for single threaded execution
		cv$var47$stateProbabilityGlobal = new double[noStates];
		
		// Constructor for cv$var55$stateProbabilityGlobal
		// 
		// Allocation of cv$var55$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 59. Initially set to the value
		// of putTask 27.
		cv$var55$stateProbabilityGlobal = new double[noStates];
		
		// Allocation of cv$var74$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 79. Initially set to the value
		// of putTask 27.
		cv$var74$stateProbabilityGlobal = new double[noStates];
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
			for(int var24 = 0; var24 < noStates; var24 += 1)
				m[var24] = new double[noStates];
		}
		
		// If bias has not been set already allocate space.
		if(!setFlag$bias) {
			// Constructor for bias
			bias = new double[noStates][];
			for(int var31 = 0; var31 < noStates; var31 += 1)
				bias[var31] = new double[noEvents];
		}
		
		// If st has not been set already allocate space.
		if(!setFlag$st) {
			// Constructor for st
			st = new int[length$eventsMeasured.length][];
			for(int i$var38 = 0; i$var38 < length$eventsMeasured.length; i$var38 += 1)
				st[i$var38] = new int[length$eventsMeasured[i$var38]];
		}
		
		// If weights has not been set already allocate space.
		if(!setFlag$weights)
			// Constructor for weights
			weights = new double[noStates];
		
		// If events has not been set already allocate space.
		if(!setFlag$events) {
			// Constructor for events
			events = new int[length$eventsMeasured.length][];
			for(int i$var80 = 0; i$var80 < length$eventsMeasured.length; i$var80 += 1)
				events[i$var80] = new int[length$eventsMeasured[i$var80]];
		}
		
		// Constructor for logProbability$var54
		logProbability$var54 = new double[length$eventsMeasured.length];
		
		// Constructor for logProbability$sample61
		logProbability$sample61 = new double[length$eventsMeasured.length];
		
		// Constructor for logProbability$var73
		logProbability$var73 = new double[length$eventsMeasured.length][];
		for(int i$var60 = 0; i$var60 < length$eventsMeasured.length; i$var60 += 1)
			logProbability$var73[i$var60] = new double[(length$eventsMeasured[i$var60] - 1)];
		
		// Constructor for logProbability$sample81
		logProbability$sample81 = new double[length$eventsMeasured.length][];
		for(int i$var60 = 0; i$var60 < length$eventsMeasured.length; i$var60 += 1)
			logProbability$sample81[i$var60] = new double[(length$eventsMeasured[i$var60] - 1)];
		
		// Constructor for logProbability$var93
		logProbability$var93 = new double[length$eventsMeasured.length][];
		for(int i$var80 = 0; i$var80 < length$eventsMeasured.length; i$var80 += 1)
			logProbability$var93[i$var80] = new double[(length$eventsMeasured[i$var80] - 1)];
		
		// Constructor for logProbability$sample103
		logProbability$sample103 = new double[length$eventsMeasured.length][];
		for(int i$var80 = 0; i$var80 < length$eventsMeasured.length; i$var80 += 1)
			logProbability$sample103[i$var80] = new double[(length$eventsMeasured[i$var80] - 1)];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample26) {
			for(int var24 = 0; var24 < noStates; var24 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var24]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample34) {
			for(int var31 = 0; var31 < noStates; var31 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v2, bias[var31]);
		}
		if(!fixedFlag$sample51)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample53)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample61) {
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1)
				st[i$var50][0] = DistributionSampling.sampleCategorical(RNG$, m[initialState]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample81) {
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				int[] var67 = st[i$var60];
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
					var67[j$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var60][(j$var66 - 1)]]);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample103) {
			for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
				int[] var89 = events[i$var80];
				for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1)
					var89[j$var88] = (DistributionSampling.sampleCategorical(RNG$, bias[st[i$var80][j$var88]]) + 1);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample26) {
			for(int var24 = 0; var24 < noStates; var24 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var24]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample34) {
			for(int var31 = 0; var31 < noStates; var31 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v2, bias[var31]);
		}
		if(!fixedFlag$sample51)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample53)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample61) {
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1)
				st[i$var50][0] = DistributionSampling.sampleCategorical(RNG$, m[initialState]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample81) {
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				int[] var67 = st[i$var60];
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
					var67[j$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var60][(j$var66 - 1)]]);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample26) {
			for(int var24 = 0; var24 < noStates; var24 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var24]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample34) {
			for(int var31 = 0; var31 < noStates; var31 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v2, bias[var31]);
		}
		if(!fixedFlag$sample51)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample53)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample61) {
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1)
				st[i$var50][0] = DistributionSampling.sampleCategorical(RNG$, m[initialState]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample81) {
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				int[] var67 = st[i$var60];
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
					var67[j$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var60][(j$var66 - 1)]]);
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample26) {
				for(int var24 = 0; var24 < noStates; var24 += 1)
					sample26(var24);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample34) {
				for(int var31 = 0; var31 < noStates; var31 += 1)
					sample34(var31);
			}
			if(!fixedFlag$sample51)
				sample51();
			if(!fixedFlag$sample53)
				sample53();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample61) {
				for(int i$var50 = 0; i$var50 < samples; i$var50 += 1)
					sample61(i$var50);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample81) {
				for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
					for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
						sample81(i$var60, j$var66);
				}
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample81) {
				for(int i$var60 = (samples - 1); i$var60 >= 0; i$var60 -= 1) {
					for(int j$var66 = (length$eventsMeasured[i$var60] - 1); j$var66 >= 1; j$var66 -= 1)
						sample81(i$var60, j$var66);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample61) {
				for(int i$var50 = (samples - 1); i$var50 >= 0; i$var50 -= 1)
					sample61(i$var50);
			}
			if(!fixedFlag$sample53)
				sample53();
			if(!fixedFlag$sample51)
				sample51();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample34) {
				for(int var31 = (noStates - 1); var31 >= 0; var31 -= 1)
					sample34(var31);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample26) {
				for(int var24 = (noStates - 1); var24 >= 0; var24 -= 1)
					sample26(var24);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		for(int var11 = 0; var11 < noStates; var11 += 1)
			v[var11] = 0.1;
		for(int var17 = 0; var17 < noEvents; var17 += 1)
			v2[var17] = 0.1;
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
			logProbability$var54[i$var50] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample61) {
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1)
				logProbability$sample61[i$var50] = 0.0;
		}
		for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
			for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
				logProbability$var73[i$var60][(j$var66 - 1)] = 0.0;
		}
		if(!fixedProbFlag$sample81) {
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
					logProbability$sample81[i$var60][(j$var66 - 1)] = 0.0;
			}
		}
		for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
			for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1)
				logProbability$var93[i$var80][(j$var88 - 1)] = 0.0;
		}
		logProbability$events = 0.0;
		if(!fixedProbFlag$sample103) {
			for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
				for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1)
					logProbability$sample103[i$var80][(j$var88 - 1)] = 0.0;
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
		if(fixedFlag$sample61)
			logProbabilityValue$sample61();
		if(fixedFlag$sample81)
			logProbabilityValue$sample81();
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
		logProbabilityValue$sample61();
		logProbabilityValue$sample81();
		logProbabilityValue$sample103();
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
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample26) {
			for(int var24 = 0; var24 < noStates; var24 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var24]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample34) {
			for(int var31 = 0; var31 < noStates; var31 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v2, bias[var31]);
		}
		if(!fixedFlag$sample51)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample53)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample61) {
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1)
				st[i$var50][0] = DistributionSampling.sampleCategorical(RNG$, m[initialState]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample81) {
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				int[] var67 = st[i$var60];
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
					var67[j$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var60][(j$var66 - 1)]]);
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMM_Mk2(int[][] eventsMeasured, int noStates, int noEvents) {\n        \n        // Construct arrays describing the probability of a move from 1 state to another.\n        double[] v = new double[noStates] <~ 0.1;\n        double[] v2 = new double[noEvents] <~ 0.1;\n        double[][] m = dirichlet(v).sample(noStates);\n        \n        // Construct the bias for each webpage.\n        double[][] bias = dirichlet(v2).sample(noStates);\n\n        // Determine how many samples the model will need to produce.\n        int samples = eventsMeasured.length;\n        \n        // Allocate space for the state, i.e. which webpage we are going to trigger an event on.\n        int[][] st = new int[samples][];\n        for(int i:[0 .. samples)){\n            int streamLength = eventsMeasured[i].length;\n            st[i] = new int[streamLength];\n        }\n\n        // Set the initial state by sampling from a categorical with learnt weightings.\n        double[] weights = dirichlet(v).sample();\n        int initialState = categorical(weights).sample();\n        for(int i:[0..samples)) {\n            st[i][0] = categorical(m[initialState]).sample();\n        }\n\n        //Determine the remaining states based on the previous state.\n        for(int i:[0 .. samples)){\n            int streamLength = eventsMeasured[i].length;\n            for(int j:[1..streamLength)){\n                st[i][j] = categorical(m[st[i][j-1]]).sample();\n            }\n        }\n            \n        //Generate each event.\n        int[][] events = new int[samples][];\n        for(int i:[0 .. samples)) {\n            int streamLength = eventsMeasured[i].length;\n            events[i] = new int[streamLength];\n            for(int j:[1..streamLength)){\n                events[i][j] = categorical(bias[st[i][j]]).sample() + 1;\n            }\n        }\n\n        //Tie the values of the flips to the values we have measured.\n        events.observe(eventsMeasured);\n}\n";
	}
}