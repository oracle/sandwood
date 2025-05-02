package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMM_Mk2$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMM_Mk2$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] bias;
	private double[] cv$var123$stateProbabilityGlobal;
	private double[] cv$var42$countGlobal;
	private double[] cv$var56$countGlobal;
	private double[] cv$var75$countGlobal;
	private double[] cv$var77$stateProbabilityGlobal;
	private double[] cv$var92$stateProbabilityGlobal;
	private int[][] events;
	private int[][] eventsMeasured;
	private boolean fixedFlag$sample126 = false;
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
	private double logProbability$st;
	private double logProbability$var122;
	private double logProbability$var123;
	private double logProbability$var154;
	private double logProbability$var155;
	private double logProbability$var30;
	private double logProbability$var42;
	private double logProbability$var44;
	private double logProbability$var56;
	private double logProbability$var74;
	private double logProbability$var76;
	private double logProbability$var91;
	private double logProbability$var92;
	private double logProbability$weights;
	private double[][] m;
	private int noEvents;
	private int noStates;
	private int samples;
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
		// Set flags for all the side effects of bias including if probabilities need to be
		// updated.
		// Set bias
		bias = cv$value;
		
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

	// Getter for eventsMeasured.
	@Override
	public final int[][] get$eventsMeasured() {
		return eventsMeasured;
	}

	// Setter for eventsMeasured.
	@Override
	public final void set$eventsMeasured(int[][] cv$value) {
		// Set eventsMeasured
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
		// 
		// Substituted "fixedFlag$sample126" with its value "cv$value".
		fixedProbFlag$sample126 = (cv$value && fixedProbFlag$sample126);
		
		// Should the probability of sample 159 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample126" with its value "cv$value".
		fixedProbFlag$sample159 = (cv$value && fixedProbFlag$sample159);
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
		// 
		// Substituted "fixedFlag$sample42" with its value "cv$value".
		fixedProbFlag$sample42 = (cv$value && fixedProbFlag$sample42);
		
		// Should the probability of sample 95 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample42" with its value "cv$value".
		fixedProbFlag$sample95 = (cv$value && fixedProbFlag$sample95);
		
		// Should the probability of sample 126 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample42" with its value "cv$value".
		fixedProbFlag$sample126 = (cv$value && fixedProbFlag$sample126);
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
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		
		// Should the probability of sample 159 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample159 = (cv$value && fixedProbFlag$sample159);
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
		// 
		// Substituted "fixedFlag$sample78" with its value "cv$value".
		fixedProbFlag$sample78 = (cv$value && fixedProbFlag$sample78);
		
		// Should the probability of sample 80 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample78" with its value "cv$value".
		fixedProbFlag$sample80 = (cv$value && fixedProbFlag$sample80);
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
		// 
		// Substituted "fixedFlag$sample80" with its value "cv$value".
		fixedProbFlag$sample80 = (cv$value && fixedProbFlag$sample80);
		
		// Should the probability of sample 95 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample80" with its value "cv$value".
		fixedProbFlag$sample95 = (cv$value && fixedProbFlag$sample95);
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
		// 
		// Substituted "fixedFlag$sample95" with its value "cv$value".
		fixedProbFlag$sample95 = (cv$value && fixedProbFlag$sample95);
		
		// Should the probability of sample 126 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample95" with its value "cv$value".
		fixedProbFlag$sample126 = (cv$value && fixedProbFlag$sample126);
		
		// Should the probability of sample 159 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample95" with its value "cv$value".
		fixedProbFlag$sample159 = (cv$value && fixedProbFlag$sample159);
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
		// Set length$eventsMeasured
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
		// Set m
		m = cv$value;
		
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
		// Set st
		st = cv$value;
		
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
		// Set weights
		weights = cv$value;
		
		// Unset the fixed probability flag for sample 78 as it depends on weights.
		fixedProbFlag$sample78 = false;
		
		// Unset the fixed probability flag for sample 80 as it depends on weights.
		fixedProbFlag$sample80 = false;
	}

	// Calculate the probability of the samples represented by sample126 using sampled
	// values.
	private final void logProbabilityValue$sample126() {
		// Determine if we need to calculate the values for sample task 126 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample126) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[i$var104][j$var115];
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					
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
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[st[i$var104][(j$var115 - 1)]][cv$sampleValue]):Double.NEGATIVE_INFINITY));
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(cv$sampleReached) {
				logProbability$var122 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var123 = cv$sampleAccumulator;
			}
			
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
			if(fixedFlag$sample126)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample126 = ((fixedFlag$sample126 && fixedFlag$sample42) && fixedFlag$sample95);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				if((1 < length$eventsMeasured[i$var104]))
					// Record that the sample was reached.
					cv$sampleReached = true;
			}
			if(cv$sampleReached)
				logProbability$var122 = logProbability$var123;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var123);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var123);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample126)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var123);
		}
	}

	// Calculate the probability of the samples represented by sample159 using sampled
	// values.
	private final void logProbabilityValue$sample159() {
		// Determine if we need to calculate the values for sample task 159 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample159) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = (events[i$var136][j$var149] - 1);
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					
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
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents))?Math.log(bias[st[i$var136][j$var149]][cv$sampleValue]):Double.NEGATIVE_INFINITY));
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(cv$sampleReached) {
				logProbability$var154 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var155 = cv$sampleAccumulator;
			}
			
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
			fixedProbFlag$sample159 = ((fixedFlag$sample57 && fixedFlag$sample95) && fixedFlag$sample126);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				if((1 < length$eventsMeasured[i$var136]))
					// Record that the sample was reached.
					cv$sampleReached = true;
			}
			if(cv$sampleReached)
				logProbability$var154 = logProbability$var155;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$events = (logProbability$events + logProbability$var155);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var155);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var155);
		}
	}

	// Calculate the probability of the samples represented by sample42 using sampled
	// values.
	private final void logProbabilityValue$sample42() {
		// Determine if we need to calculate the values for sample task 42 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample42) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var41 = 0; var41 < noStates; var41 += 1) {
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var41], v, noStates));
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(cv$sampleReached) {
				logProbability$var30 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				logProbability$var42 = cv$sampleAccumulator;
			}
			
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
			if(fixedFlag$sample42)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample42 = fixedFlag$sample42;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if((0 < noStates))
				// Record that the sample was reached.
				cv$sampleReached = true;
			if(cv$sampleReached)
				logProbability$var30 = logProbability$var42;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var42);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var42);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample42)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var42);
		}
	}

	// Calculate the probability of the samples represented by sample57 using sampled
	// values.
	private final void logProbabilityValue$sample57() {
		// Determine if we need to calculate the values for sample task 57 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample57) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var55 = 0; var55 < noStates; var55 += 1) {
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(bias[var55], v2, noEvents));
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(cv$sampleReached) {
				logProbability$var44 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				logProbability$var56 = cv$sampleAccumulator;
			}
			
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
			if(fixedFlag$sample57)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample57 = fixedFlag$sample57;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if((0 < noStates))
				// Record that the sample was reached.
				cv$sampleReached = true;
			if(cv$sampleReached)
				logProbability$var44 = logProbability$var56;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$bias = (logProbability$bias + logProbability$var56);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var56);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample57)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var56);
		}
	}

	// Calculate the probability of the samples represented by sample78 using sampled
	// values.
	private final void logProbabilityValue$sample78() {
		// Determine if we need to calculate the values for sample task 78 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample78) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(weights, v, noStates);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var74 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample78)
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
			fixedProbFlag$sample78 = fixedFlag$sample78;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var74 = logProbability$weights;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$weights);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample78)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$weights);
		}
	}

	// Calculate the probability of the samples represented by sample80 using sampled
	// values.
	private final void logProbabilityValue$sample80() {
		// Determine if we need to calculate the values for sample task 80 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample80) {
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
			double cv$distributionAccumulator = (((0.0 <= initialState) && (initialState < noStates))?Math.log(weights[initialState]):Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var76 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample80)
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
			fixedProbFlag$sample80 = (fixedFlag$sample80 && fixedFlag$sample78);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var76 = logProbability$initialState;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$initialState);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample80)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialState);
		}
	}

	// Calculate the probability of the samples represented by sample95 using sampled
	// values.
	private final void logProbabilityValue$sample95() {
		// Determine if we need to calculate the values for sample task 95 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample95) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = st[i$var87][0];
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[initialState][cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(cv$sampleReached) {
				logProbability$var91 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var92 = cv$sampleAccumulator;
			}
			
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
			if(fixedFlag$sample95)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample95 = ((fixedFlag$sample95 && fixedFlag$sample42) && fixedFlag$sample80);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if((0 < samples))
				// Record that the sample was reached.
				cv$sampleReached = true;
			if(cv$sampleReached)
				logProbability$var91 = logProbability$var92;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var92);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var92);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample95)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var92);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 126 drawn from Categorical 122. Inference was performed using variable
	// marginalization.
	private final void sample126(int i$var104, int j$var115) {
		// Variable declaration of cv$numNumStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
		// cv$numNumStates's comment
		// Calculate the number of states to evaluate.
		int cv$numNumStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			// Value of the variable at this index
			st[i$var104][j$var115] = cv$valuePos;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			// 
			// cv$temp$0$var121's comment
			// Constructing a random variable input for use later.
			double cv$accumulatedProbabilities = ((cv$valuePos < noStates)?Math.log(m[st[i$var104][(j$var115 - 1)]][cv$valuePos]):Double.NEGATIVE_INFINITY);
			int index$j$2_3 = (j$var115 + 1);
			if((index$j$2_3 < length$eventsMeasured[i$var104]))
				// Processing sample task 126 of consumer random variable null.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 126 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "index$i$2_2" with its value "i$var104".
				// 
				// cv$temp$3$$var357's comment
				// 
				// $var357's comment
				// Constructing a random variable input for use later.
				// 
				// cv$temp$2$var121's comment
				// Variable declaration of cv$temp$2$var121 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 122.
				// 
				// Looking for a path between Sample 126 and consumer Categorical 122.
				// 
				// Value of the variable at this index
				cv$accumulatedProbabilities = ((((0.0 <= st[i$var104][index$j$2_3]) && (st[i$var104][index$j$2_3] < noStates))?Math.log(m[cv$valuePos][st[i$var104][index$j$2_3]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 159 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Processing sample task 159 of consumer random variable null.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "i$var136" with its value "i$var104".
			// 
			// cv$temp$5$$var367's comment
			// 
			// $var367's comment
			// Constructing a random variable input for use later.
			// 
			// cv$temp$4$var153's comment
			// Variable declaration of cv$temp$4$var153 moved.
			// 
			// Constructing a random variable input for use later.
			// 
			// Processing random variable 154.
			// 
			// Looking for a path between Sample 126 and consumer Categorical 154.
			// 
			// Value of the variable at this index
			cv$accumulatedProbabilities = ((((1.0 <= events[i$var104][j$var115]) && (events[i$var104][j$var115] < (noEvents + 1)))?Math.log(bias[cv$valuePos][(events[i$var104][j$var115] - 1)]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var123$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
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
		double cv$lseMax = cv$var123$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var123$stateProbabilityGlobal[cv$lseIndex];
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
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var123$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var123$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var123$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var123$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var123$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var123$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Get a local reference to the scratch space.
		st[i$var104][j$var115] = DistributionSampling.sampleCategorical(RNG$, cv$var123$stateProbabilityGlobal, cv$numNumStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 42 drawn from Dirichlet 30. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample42(int var41) {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var42$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var41 == initialState)) {
			// Looking for a path between Sample 42 and consumer Categorical 91.
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1)
				// Processing sample task 95 of consumer random variable null.
				// 
				// Increment the sample counter with the value sampled by sample task 95 of random
				// variable var91
				// 
				// A local reference to the scratch space.
				cv$var42$countGlobal[st[i$var87][0]] = (cv$var42$countGlobal[st[i$var87][0]] + 1.0);
		}
		
		// Processing random variable 122.
		// 
		// Looking for a path between Sample 42 and consumer Categorical 122.
		for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
			for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
				if((var41 == st[i$var104][(j$var115 - 1)]))
					// Processing sample task 126 of consumer random variable null.
					// 
					// Increment the sample counter with the value sampled by sample task 126 of random
					// variable var122
					// 
					// A local reference to the scratch space.
					cv$var42$countGlobal[st[i$var104][j$var115]] = (cv$var42$countGlobal[st[i$var104][j$var115]] + 1.0);
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var42$countGlobal, m[var41], noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 57 drawn from Dirichlet 44. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample57(int var55) {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noEvents; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var56$countGlobal[cv$loopIndex] = 0.0;
		
		// Processing random variable 154.
		// 
		// Looking for a path between Sample 57 and consumer Categorical 154.
		for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
			for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
				if((var55 == st[i$var136][j$var149]))
					// Processing sample task 159 of consumer random variable null.
					// 
					// Increment the sample counter with the value sampled by sample task 159 of random
					// variable var154
					// 
					// A local reference to the scratch space.
					cv$var56$countGlobal[(events[i$var136][j$var149] - 1)] = (cv$var56$countGlobal[(events[i$var136][j$var149] - 1)] + 1.0);
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v2, cv$var56$countGlobal, bias[var55], noEvents);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 78 drawn from Dirichlet 74. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample78() {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var75$countGlobal[cv$loopIndex] = 0.0;
		
		// Processing random variable 76.
		// 
		// Processing sample task 80 of consumer random variable null.
		// 
		// Increment the sample counter with the value sampled by sample task 80 of random
		// variable var76
		// 
		// A local reference to the scratch space.
		cv$var75$countGlobal[initialState] = (cv$var75$countGlobal[initialState] + 1.0);
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var75$countGlobal, weights, noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 80 drawn from Categorical 76. Inference was performed using variable
	// marginalization.
	private final void sample80() {
		// Variable declaration of cv$numNumStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
		// cv$numNumStates's comment
		// Calculate the number of states to evaluate.
		int cv$numNumStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			initialState = cv$valuePos;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$weights" with its value "weights".
			// 
			// cv$temp$1$$var290's comment
			// 
			// $var290's comment
			// Constructing a random variable input for use later.
			double cv$accumulatedProbabilities = ((cv$valuePos < noStates)?Math.log(weights[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Processing random variable 91.
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 95 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 95 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$3$$var299's comment
				// 
				// $var299's comment
				// Constructing a random variable input for use later.
				// 
				// cv$temp$2$var90's comment
				// Variable declaration of cv$temp$2$var90 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				cv$accumulatedProbabilities = ((((0.0 <= st[i$var87][0]) && (st[i$var87][0] < noStates))?Math.log(m[cv$valuePos][st[i$var87][0]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var77$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
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
		double cv$lseMax = cv$var77$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var77$stateProbabilityGlobal[cv$lseIndex];
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
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var77$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var77$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var77$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var77$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var77$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var77$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the new value of the sample.
		// 
		// Get a local reference to the scratch space.
		initialState = DistributionSampling.sampleCategorical(RNG$, cv$var77$stateProbabilityGlobal, cv$numNumStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 95 drawn from Categorical 91. Inference was performed using variable
	// marginalization.
	private final void sample95(int i$var87) {
		// Variable declaration of cv$numNumStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
		// cv$numNumStates's comment
		// Calculate the number of states to evaluate.
		int cv$numNumStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			// Value of the variable at this index
			st[i$var87][0] = cv$valuePos;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			// 
			// cv$temp$0$var90's comment
			// Constructing a random variable input for use later.
			double cv$accumulatedProbabilities = ((cv$valuePos < noStates)?Math.log(m[initialState][cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Substituted "j$var115" with its value "1".
			if((1 < length$eventsMeasured[i$var87]))
				// Processing sample task 126 of consumer random variable null.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 126 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var104" with its value "i$var87".
				// 
				// cv$temp$3$$var323's comment
				// 
				// $var323's comment
				// Constructing a random variable input for use later.
				// 
				// cv$temp$2$var121's comment
				// Variable declaration of cv$temp$2$var121 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 122.
				// 
				// Looking for a path between Sample 95 and consumer Categorical 122.
				// 
				// Value of the variable at this index
				cv$accumulatedProbabilities = ((((0.0 <= st[i$var87][1]) && (st[i$var87][1] < noStates))?Math.log(m[cv$valuePos][st[i$var87][1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var92$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
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
		double cv$lseMax = cv$var92$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var92$stateProbabilityGlobal[cv$lseIndex];
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
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var92$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var92$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var92$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var92$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var92$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var92$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Get a local reference to the scratch space.
		st[i$var87][0] = DistributionSampling.sampleCategorical(RNG$, cv$var92$stateProbabilityGlobal, cv$numNumStates);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var42$countGlobal
		// 
		// Allocation of cv$var42$countGlobal for single threaded execution
		cv$var42$countGlobal = new double[noStates];
		
		// Constructor for cv$var56$countGlobal
		// 
		// Allocation of cv$var56$countGlobal for single threaded execution
		cv$var56$countGlobal = new double[noEvents];
		
		// Constructor for cv$var75$countGlobal
		// 
		// Allocation of cv$var75$countGlobal for single threaded execution
		cv$var75$countGlobal = new double[noStates];
		
		// Constructor for cv$var77$stateProbabilityGlobal
		// 
		// Allocation of cv$var77$stateProbabilityGlobal for single threaded execution
		cv$var77$stateProbabilityGlobal = new double[noStates];
		
		// Constructor for cv$var92$stateProbabilityGlobal
		// 
		// Allocation of cv$var92$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 93. Initially set to the value
		// of putTask 43.
		cv$var92$stateProbabilityGlobal = new double[noStates];
		
		// Allocation of cv$var123$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 124. Initially set to the value
		// of putTask 43.
		cv$var123$stateProbabilityGlobal = new double[noStates];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for v
		v = new double[noStates];
		
		// Constructor for v2
		v2 = new double[noEvents];
		
		// If m has not been set already allocate space.
		if(!fixedFlag$sample42) {
			// Constructor for m
			m = new double[noStates][];
			for(int var41 = 0; var41 < noStates; var41 += 1)
				m[var41] = new double[noStates];
		}
		
		// If bias has not been set already allocate space.
		if(!fixedFlag$sample57) {
			// Constructor for bias
			bias = new double[noStates][];
			for(int var55 = 0; var55 < noStates; var55 += 1)
				bias[var55] = new double[noEvents];
		}
		
		// If st has not been set already allocate space.
		if((!fixedFlag$sample95 || !fixedFlag$sample126)) {
			// Constructor for st
			st = new int[length$eventsMeasured.length][];
			for(int i$var69 = 0; i$var69 < length$eventsMeasured.length; i$var69 += 1)
				st[i$var69] = new int[length$eventsMeasured[i$var69]];
		}
		
		// If weights has not been set already allocate space.
		if(!fixedFlag$sample78)
			// Constructor for weights
			weights = new double[noStates];
		
		// Constructor for events
		events = new int[length$eventsMeasured.length][];
		for(int i$var136 = 0; i$var136 < length$eventsMeasured.length; i$var136 += 1)
			events[i$var136] = new int[length$eventsMeasured[i$var136]];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample42) {
			for(int var41 = 0; var41 < noStates; var41 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var41]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample57) {
			for(int var55 = 0; var55 < noStates; var55 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v2, noEvents, bias[var55]);
		}
		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample95) {
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1)
				st[i$var87][0] = DistributionSampling.sampleCategorical(RNG$, m[initialState], noStates);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample126) {
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				int[] var116 = st[i$var104];
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
					var116[j$var115] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var104][(j$var115 - 1)]], noStates);
			}
		}
		for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
			int[] var150 = events[i$var136];
			for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1)
				var150[j$var149] = (DistributionSampling.sampleCategorical(RNG$, bias[st[i$var136][j$var149]], noEvents) + 1);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample42) {
			for(int var41 = 0; var41 < noStates; var41 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var41]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample57) {
			for(int var55 = 0; var55 < noStates; var55 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v2, noEvents, bias[var55]);
		}
		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample95) {
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1)
				st[i$var87][0] = DistributionSampling.sampleCategorical(RNG$, m[initialState], noStates);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample126) {
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				int[] var116 = st[i$var104];
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
					var116[j$var115] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var104][(j$var115 - 1)]], noStates);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample42) {
			for(int var41 = 0; var41 < noStates; var41 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var41]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample57) {
			for(int var55 = 0; var55 < noStates; var55 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v2, noEvents, bias[var55]);
		}
		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample95) {
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1)
				st[i$var87][0] = DistributionSampling.sampleCategorical(RNG$, m[initialState], noStates);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample126) {
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				int[] var116 = st[i$var104];
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
					var116[j$var115] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var104][(j$var115 - 1)]], noStates);
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample42) {
				for(int var41 = 0; var41 < noStates; var41 += 1)
					sample42(var41);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample57) {
				for(int var55 = 0; var55 < noStates; var55 += 1)
					sample57(var55);
			}
			if(!fixedFlag$sample78)
				sample78();
			if(!fixedFlag$sample80)
				sample80();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample95) {
				for(int i$var87 = 0; i$var87 < samples; i$var87 += 1)
					sample95(i$var87);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample126) {
				for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
					for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
						sample126(i$var104, j$var115);
				}
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample126) {
				for(int i$var104 = (samples - 1); i$var104 >= 0; i$var104 -= 1) {
					for(int j$var115 = (length$eventsMeasured[i$var104] - 1); j$var115 >= 1; j$var115 -= 1)
						sample126(i$var104, j$var115);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample95) {
				for(int i$var87 = (samples - 1); i$var87 >= 0; i$var87 -= 1)
					sample95(i$var87);
			}
			if(!fixedFlag$sample80)
				sample80();
			if(!fixedFlag$sample78)
				sample78();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample57) {
				for(int var55 = (noStates - 1); var55 >= 0; var55 -= 1)
					sample57(var55);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample42) {
				for(int var41 = (noStates - 1); var41 >= 0; var41 -= 1)
					sample42(var41);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		for(int var14 = 0; var14 < noStates; var14 += 1)
			v[var14] = 0.1;
		for(int var27 = 0; var27 < noEvents; var27 += 1)
			v2[var27] = 0.1;
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
		logProbability$var30 = Double.NaN;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample42)
			logProbability$var42 = Double.NaN;
		logProbability$var44 = Double.NaN;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample57)
			logProbability$var56 = Double.NaN;
		logProbability$var74 = 0.0;
		if(!fixedProbFlag$sample78)
			logProbability$weights = Double.NaN;
		logProbability$var76 = 0.0;
		if(!fixedProbFlag$sample80)
			logProbability$initialState = Double.NaN;
		logProbability$var91 = Double.NaN;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample95)
			logProbability$var92 = Double.NaN;
		logProbability$var122 = Double.NaN;
		if(!fixedProbFlag$sample126)
			logProbability$var123 = Double.NaN;
		logProbability$var154 = Double.NaN;
		logProbability$events = 0.0;
		if(!fixedProbFlag$sample159)
			logProbability$var155 = Double.NaN;
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
		if(fixedFlag$sample95)
			logProbabilityValue$sample95();
		if(fixedFlag$sample126)
			logProbabilityValue$sample126();
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
		logProbabilityValue$sample95();
		logProbabilityValue$sample126();
		logProbabilityValue$sample159();
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
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample42) {
			for(int var41 = 0; var41 < noStates; var41 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var41]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample57) {
			for(int var55 = 0; var55 < noStates; var55 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v2, noEvents, bias[var55]);
		}
		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample95) {
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1)
				st[i$var87][0] = DistributionSampling.sampleCategorical(RNG$, m[initialState], noStates);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample126) {
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				int[] var116 = st[i$var104];
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
					var116[j$var115] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var104][(j$var115 - 1)]], noStates);
			}
		}
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
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
		     + "model HMM_Mk2(int[][] eventsMeasured, int noStates, int noEvents) {\n"
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
		     + "            st[i][0] = categorical(m[initialState]).sample();\n"
		     + "        }\n"
		     + "\n"
		     + "        //Determine the remaining states based on the previous state.\n"
		     + "        for(int i:[0 .. samples)){\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            for(int j:[1..streamLength)){\n"
		     + "                st[i][j] = categorical(m[st[i][j-1]]).sample();\n"
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