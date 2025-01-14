package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart4b$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart4b$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private double[][] cv$var18$countGlobal;
	private double[] cv$var49$stateProbabilityGlobal;
	private double[][] cv$var67$stateProbabilityGlobal;
	private boolean fixedFlag$sample107 = false;
	private boolean fixedFlag$sample19 = false;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample54 = false;
	private boolean fixedFlag$sample72 = false;
	private boolean fixedProbFlag$sample107 = false;
	private boolean fixedProbFlag$sample19 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample54 = false;
	private boolean fixedProbFlag$sample72 = false;
	private boolean[][][] flips;
	private boolean[][][] flipsMeasured;
	private int[][] length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double logProbability$st;
	private double logProbability$var100;
	private double logProbability$var13;
	private double logProbability$var18;
	private double logProbability$var22;
	private double logProbability$var27;
	private double logProbability$var48;
	private double logProbability$var49;
	private double logProbability$var66;
	private double logProbability$var67;
	private double logProbability$var99;
	private double[][] m;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[][][] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart4b$MultiThreadCPU(ExecutionTarget target) {
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
		// Set bias with flag to mark that it has been set so another array doesn't need to
		// be constructed
		bias = cv$value;
		setFlag$bias = true;
	}

	// Getter for fixedFlag$sample107.
	@Override
	public final boolean get$fixedFlag$sample107() {
		return fixedFlag$sample107;
	}

	// Setter for fixedFlag$sample107.
	@Override
	public final void set$fixedFlag$sample107(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample107 including if probabilities
		// need to be updated.
		fixedFlag$sample107 = cv$value;
		
		// Should the probability of sample 107 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample107" with its value "cv$value".
		fixedProbFlag$sample107 = (cv$value && fixedProbFlag$sample107);
	}

	// Getter for fixedFlag$sample19.
	@Override
	public final boolean get$fixedFlag$sample19() {
		return fixedFlag$sample19;
	}

	// Setter for fixedFlag$sample19.
	@Override
	public final void set$fixedFlag$sample19(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample19 including if probabilities
		// need to be updated.
		fixedFlag$sample19 = cv$value;
		
		// Should the probability of sample 19 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample19" with its value "cv$value".
		fixedProbFlag$sample19 = (cv$value && fixedProbFlag$sample19);
		
		// Should the probability of sample 54 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample19" with its value "cv$value".
		fixedProbFlag$sample54 = (cv$value && fixedProbFlag$sample54);
		
		// Should the probability of sample 72 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample19" with its value "cv$value".
		fixedProbFlag$sample72 = (cv$value && fixedProbFlag$sample72);
	}

	// Getter for fixedFlag$sample29.
	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	// Setter for fixedFlag$sample29.
	@Override
	public final void set$fixedFlag$sample29(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample29 including if probabilities
		// need to be updated.
		fixedFlag$sample29 = cv$value;
		
		// Should the probability of sample 29 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample29" with its value "cv$value".
		fixedProbFlag$sample29 = (cv$value && fixedProbFlag$sample29);
		
		// Should the probability of sample 107 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample29" with its value "cv$value".
		fixedProbFlag$sample107 = (cv$value && fixedProbFlag$sample107);
	}

	// Getter for fixedFlag$sample54.
	@Override
	public final boolean get$fixedFlag$sample54() {
		return fixedFlag$sample54;
	}

	// Setter for fixedFlag$sample54.
	@Override
	public final void set$fixedFlag$sample54(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample54 including if probabilities
		// need to be updated.
		fixedFlag$sample54 = cv$value;
		
		// Should the probability of sample 54 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample54" with its value "cv$value".
		fixedProbFlag$sample54 = (cv$value && fixedProbFlag$sample54);
		
		// Should the probability of sample 107 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample54" with its value "cv$value".
		fixedProbFlag$sample107 = (cv$value && fixedProbFlag$sample107);
	}

	// Getter for fixedFlag$sample72.
	@Override
	public final boolean get$fixedFlag$sample72() {
		return fixedFlag$sample72;
	}

	// Setter for fixedFlag$sample72.
	@Override
	public final void set$fixedFlag$sample72(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample72 including if probabilities
		// need to be updated.
		fixedFlag$sample72 = cv$value;
		
		// Should the probability of sample 72 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample72" with its value "cv$value".
		fixedProbFlag$sample72 = (cv$value && fixedProbFlag$sample72);
		
		// Should the probability of sample 107 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample72" with its value "cv$value".
		fixedProbFlag$sample107 = (cv$value && fixedProbFlag$sample107);
	}

	// Getter for flips.
	@Override
	public final boolean[][][] get$flips() {
		return flips;
	}

	// Setter for flips.
	@Override
	public final void set$flips(boolean[][][] cv$value) {
		// Set flips with flag to mark that it has been set so another array doesn't need
		// to be constructed
		flips = cv$value;
		setFlag$flips = true;
	}

	// Getter for flipsMeasured.
	@Override
	public final boolean[][][] get$flipsMeasured() {
		return flipsMeasured;
	}

	// Setter for flipsMeasured.
	@Override
	public final void set$flipsMeasured(boolean[][][] cv$value) {
		// Set flipsMeasured with flag to mark that it has been set so another array doesn't
		// need to be constructed
		flipsMeasured = cv$value;
	}

	// Getter for length$flipsMeasured.
	@Override
	public final int[][] get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	// Setter for length$flipsMeasured.
	@Override
	public final void set$length$flipsMeasured(int[][] cv$value) {
		// Set length$flipsMeasured with flag to mark that it has been set so another array
		// doesn't need to be constructed
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
		// Set m with flag to mark that it has been set so another array doesn't need to be
		// constructed
		m = cv$value;
		setFlag$m = true;
	}

	// Getter for samples.
	@Override
	public final int get$samples() {
		return samples;
	}

	// Getter for st.
	@Override
	public final int[][][] get$st() {
		return st;
	}

	// Setter for st.
	@Override
	public final void set$st(int[][][] cv$value) {
		// Set st with flag to mark that it has been set so another array doesn't need to
		// be constructed
		st = cv$value;
		setFlag$st = true;
	}

	// Getter for states.
	@Override
	public final int get$states() {
		return 2;
	}

	// Getter for v.
	@Override
	public final double[] get$v() {
		return v;
	}

	// Calculate the probability of the samples represented by sample107 using sampled
	// values.
	private final void logProbabilityValue$sample107() {
		// Determine if we need to calculate the values for sample task 107 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample107) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int l = 0; l < samples; l += 1) {
				for(int p = 0; p < samples; p += 1) {
					for(int n = 0; n < samples; n += 1)
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
						cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], bias[st[p][l][n]]));
				}
			}
			logProbability$var99 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var100 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample107 = (((fixedFlag$sample107 && fixedFlag$sample29) && fixedFlag$sample54) && fixedFlag$sample72);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var99 = logProbability$var100;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$flips = (logProbability$flips + logProbability$var100);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var100);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var100);
		}
	}

	// Calculate the probability of the samples represented by sample19 using sampled
	// values.
	private final void logProbabilityValue$sample19() {
		// Determine if we need to calculate the values for sample task 19 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample19) {
			// Generating probabilities for sample task
			// Variable declaration of cv$sampleAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$sampleAccumulator moved.
			// Declaration comment was:
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityDirichlet(m[0], v) + DistributionSampling.logProbabilityDirichlet(m[1], v));
			logProbability$var13 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var18 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample19)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample19 = fixedFlag$sample19;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var13 = logProbability$var18;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var18);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var18);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample19)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var18);
		}
	}

	// Calculate the probability of the samples represented by sample29 using sampled
	// values.
	private final void logProbabilityValue$sample29() {
		// Determine if we need to calculate the values for sample task 29 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample29) {
			// Generating probabilities for sample task
			// Variable declaration of cv$sampleAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$sampleAccumulator moved.
			// Declaration comment was:
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityBeta(bias[0], 1.0, 1.0) + DistributionSampling.logProbabilityBeta(bias[1], 1.0, 1.0));
			logProbability$var22 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var27 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample29)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample29 = fixedFlag$sample29;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var22 = logProbability$var27;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$bias = (logProbability$bias + logProbability$var27);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var27);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample29)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var27);
		}
	}

	// Calculate the probability of the samples represented by sample54 using sampled
	// values.
	private final void logProbabilityValue$sample54() {
		// Determine if we need to calculate the values for sample task 54 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample54) {
			// Generating probabilities for sample task
			// The sample value to calculate the probability of generating
			int cv$sampleValue = st[0][0][0];
			double[] var47 = m[0];
			
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
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var47.length))?Math.log(var47[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var48 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var49 = cv$distributionAccumulator;
			
			// Update the variable probability
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
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			
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
			if(fixedFlag$sample54)
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
			fixedProbFlag$sample54 = (fixedFlag$sample54 && fixedFlag$sample19);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var48 = logProbability$var49;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var49);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var49);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample54)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var49);
		}
	}

	// Calculate the probability of the samples represented by sample72 using sampled
	// values.
	private final void logProbabilityValue$sample72() {
		// Determine if we need to calculate the values for sample task 72 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample72) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var55 = 1; i$var55 < samples; i$var55 += 1) {
				for(int j$var58 = 0; j$var58 < samples; j$var58 += 1) {
					for(int k = 0; k < samples; k += 1) {
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[i$var55][j$var58][k];
						double[] var65 = m[0];
						
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
						cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var65.length))?Math.log(var65[cv$sampleValue]):Double.NEGATIVE_INFINITY));
					}
				}
			}
			logProbability$var66 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var67 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample72)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample72 = (fixedFlag$sample72 && fixedFlag$sample19);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var66 = logProbability$var67;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var67);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var67);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample72)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var67);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 19 drawn from Dirichlet 13. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample19(int var17, int threadID$cv$var17, Rng RNG$) {
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var18$countGlobal[threadID$cv$var17];
		cv$countLocal[0] = 0.0;
		cv$countLocal[1] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var17 == 0)) {
			// Processing random variable 48.
			// 
			// Looking for a path between Sample 19 and consumer Categorical 48.
			// 
			// Processing sample task 54 of consumer random variable null.
			// 
			// Increment the sample counter with the value sampled by sample task 54 of random
			// variable var48
			cv$countLocal[st[0][0][0]] = (cv$countLocal[st[0][0][0]] + 1.0);
			
			// Processing random variable 66.
			// 
			// Looking for a path between Sample 19 and consumer Categorical 66.
			for(int i$var55 = 1; i$var55 < samples; i$var55 += 1) {
				for(int j$var58 = 0; j$var58 < samples; j$var58 += 1) {
					for(int k = 0; k < samples; k += 1)
						// Processing sample task 72 of consumer random variable null.
						// 
						// Increment the sample counter with the value sampled by sample task 72 of random
						// variable var66
						cv$countLocal[st[i$var55][j$var58][k]] = (cv$countLocal[st[i$var55][j$var58][k]] + 1.0);
				}
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var17]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 29 drawn from Beta 22. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample29(int var26, int threadID$cv$var26, Rng RNG$) {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		
		// Processing random variable 99.
		// 
		// Looking for a path between Sample 29 and consumer Bernoulli 99.
		for(int l = 0; l < samples; l += 1) {
			for(int p = 0; p < samples; p += 1) {
				for(int n = 0; n < samples; n += 1) {
					if((var26 == st[p][l][n])) {
						// Processing sample task 107 of consumer random variable null.
						// 
						// Include the value sampled by task 107 from random variable var99.
						// Increment the number of samples.
						cv$count = (cv$count + 1);
						
						// If the sample value was positive increase the count
						if(flips[l][n][p])
							cv$sum = (cv$sum + 1);
					}
				}
			}
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		bias[var26] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 54 drawn from Categorical 48. Inference was performed using variable
	// marginalization.
	private final void sample54() {
		// Unrolled loop
		{
			// Variable declaration of cv$temp$0$var47 moved.
			// 
			// Constructing a random variable input for use later.
			double[] cv$temp$0$var47 = m[0];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = ((0 < cv$temp$0$var47.length)?Math.log(cv$temp$0$var47[0]):Double.NEGATIVE_INFINITY);
			
			// Substituted "p" with its value "0".
			if((0 < samples))
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 107 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 107 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "l" with its value "0".
				// 
				// Substituted "cv$temp$1$var98" with its value "var98".
				// 
				// Constructing a random variable input for use later.
				// 
				// Variable declaration of cv$currentValue moved.
				// Declaration comment was:
				// The value currently being tested
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0][0][0], bias[0]) + cv$accumulatedProbabilities);
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var49$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Variable declaration of cv$temp$0$var47 moved.
		// 
		// Constructing a random variable input for use later.
		double[] cv$temp$0$var47 = m[0];
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = ((1 < cv$temp$0$var47.length)?Math.log(cv$temp$0$var47[1]):Double.NEGATIVE_INFINITY);
		
		// Substituted "p" with its value "0".
		if((0 < samples))
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 107 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Processing sample task 107 of consumer random variable null.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "l" with its value "0".
			// 
			// Substituted "cv$temp$1$var98" with its value "var98".
			// 
			// Constructing a random variable input for use later.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0][0][0], bias[1]) + cv$accumulatedProbabilities);
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var49$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var49$stateProbabilityGlobal[0];
		
		// Find max value.
		// 
		// Get a local reference to the scratch space.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var49$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var49$stateProbabilityGlobal[cv$lseIndex];
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
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var49$stateProbabilityGlobal.length; cv$lseIndex += 1)
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var49$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
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
			for(int cv$indexName = 0; cv$indexName < cv$var49$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var49$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$var49$stateProbabilityGlobal.length);
		} else {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var49$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var49$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var49$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Get a local reference to the scratch space.
		st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, cv$var49$stateProbabilityGlobal);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 72 drawn from Categorical 66. Inference was performed using variable
	// marginalization.
	private final void sample72(int i$var55, int j$var58, int k, int threadID$cv$k, Rng RNG$) {
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var67$stateProbabilityGlobal[threadID$cv$k];
		
		// Unrolled loop
		{
			// Variable declaration of cv$temp$0$var65 moved.
			// 
			// Constructing a random variable input for use later.
			double[] cv$temp$0$var65 = m[0];
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			// 
			// Variable declaration of cv$accumulatedProbabilities moved.
			// Declaration comment was:
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			// 
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 107 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Processing sample task 107 of consumer random variable null.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "l" with its value "j$var58".
			// 
			// Substituted "cv$temp$1$var98" with its value "var98".
			// 
			// Constructing a random variable input for use later.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			cv$stateProbabilityLocal[0] = (DistributionSampling.logProbabilityBernoulli(flips[j$var58][k][i$var55], bias[0]) + ((0 < cv$temp$0$var65.length)?Math.log(cv$temp$0$var65[0]):Double.NEGATIVE_INFINITY));
		}
		
		// Variable declaration of cv$temp$0$var65 moved.
		// 
		// Constructing a random variable input for use later.
		double[] cv$temp$0$var65 = m[0];
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		// 
		// Variable declaration of cv$accumulatedProbabilities moved.
		// Declaration comment was:
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 107 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		// 
		// Variable declaration of cv$accumulatedConsumerProbabilities moved.
		// Declaration comment was:
		// Processing sample task 107 of consumer random variable null.
		// 
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		// 
		// Substituted "l" with its value "j$var58".
		// 
		// Substituted "cv$temp$1$var98" with its value "var98".
		// 
		// Constructing a random variable input for use later.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		cv$stateProbabilityLocal[1] = (DistributionSampling.logProbabilityBernoulli(flips[j$var58][k][i$var55], bias[1]) + ((1 < cv$temp$0$var65.length)?Math.log(cv$temp$0$var65[1]):Double.NEGATIVE_INFINITY));
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
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
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$stateProbabilityLocal.length);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		st[i$var55][j$var58][k] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var18$countGlobal
		{
			// Allocation of cv$var18$countGlobal for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var18$countGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var18$countGlobal[cv$index] = new double[2];
		}
		
		// Constructor for cv$var49$stateProbabilityGlobal
		// 
		// Allocation of cv$var49$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 52. Initially set to the value
		// of putTask 20.
		cv$var49$stateProbabilityGlobal = new double[2];
		
		// Allocation of cv$var67$stateProbabilityGlobal for multithreaded execution
		// 
		// Get the thread count.
		int cv$threadCount = threadCount();
		
		// Allocate an array to hold a copy per thread
		cv$var67$stateProbabilityGlobal = new double[cv$threadCount][];
		
		// Populate the array with a copy per thread
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			// Variable to record the maximum value of Task Get 70. Initially set to the value
			// of putTask 20.
			cv$var67$stateProbabilityGlobal[cv$index] = new double[2];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for v
		v = new double[2];
		
		// If m has not been set already allocate space.
		if(!setFlag$m) {
			// Constructor for m
			m = new double[2][];
			m[0] = new double[2];
			m[1] = new double[2];
		}
		
		// If bias has not been set already allocate space.
		if(!setFlag$bias)
			// Constructor for bias
			bias = new double[2];
		
		// If st has not been set already allocate space.
		if(!setFlag$st) {
			// Constructor for st
			st = new int[length$flipsMeasured.length][][];
			for(int var33 = 0; var33 < length$flipsMeasured.length; var33 += 1) {
				int[][] subarray$0 = new int[length$flipsMeasured.length][];
				st[var33] = subarray$0;
				for(int var37 = 0; var37 < length$flipsMeasured.length; var37 += 1)
					subarray$0[var37] = new int[length$flipsMeasured.length];
			}
		}
		
		// If flips has not been set already allocate space.
		if(!setFlag$flips) {
			// Constructor for flips
			flips = new boolean[length$flipsMeasured.length][][];
			for(int i$var74 = 0; i$var74 < length$flipsMeasured.length; i$var74 += 1) {
				boolean[][] subarray$0 = new boolean[length$flipsMeasured.length][];
				flips[i$var74] = subarray$0;
				for(int j$var79 = 0; j$var79 < length$flipsMeasured.length; j$var79 += 1)
					subarray$0[j$var79] = new boolean[length$flipsMeasured.length];
			}
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample19)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var17]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample29)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample54)
			st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample72)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, samples, 1,
				(int forStart$i$var55, int forEnd$i$var55, int threadID$i$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var55 = forStart$i$var55; i$var55 < forEnd$i$var55; i$var55 += 1) {
							int[][] var62 = st[i$var55];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, samples, 1,
								(int forStart$index$j$var58, int forEnd$index$j$var58, int threadID$index$j$var58, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int index$j$var58 = forStart$index$j$var58; index$j$var58 < forEnd$index$j$var58; index$j$var58 += 1) {
											int j$var58 = index$j$var58;
											
											//  Outer loop for dispatching multiple batches of iterations to execute in parallel
											parallelFor(RNG$2, 0, samples, 1,
												(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
													
														// Inner loop for running batches of iterations, each batch has its own random number
														// generator.
														for(int k = forStart$k; k < forEnd$k; k += 1)
															var62[j$var58][k] = DistributionSampling.sampleCategorical(RNG$3, m[0]);
												}
											);
										}
								}
							);
						}
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample107)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$index$l, int forEnd$index$l, int threadID$index$l, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$l = forStart$index$l; index$l < forEnd$index$l; index$l += 1) {
							int l = index$l;
							boolean[][] var93 = flips[l];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, samples, 1,
								(int forStart$index$p, int forEnd$index$p, int threadID$index$p, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int index$p = forStart$index$p; index$p < forEnd$index$p; index$p += 1) {
											int p = index$p;
											
											//  Outer loop for dispatching multiple batches of iterations to execute in parallel
											parallelFor(RNG$2, 0, samples, 1,
												(int forStart$n, int forEnd$n, int threadID$n, org.sandwood.random.internal.Rng RNG$3) -> { 
													
														// Inner loop for running batches of iterations, each batch has its own random number
														// generator.
														for(int n = forStart$n; n < forEnd$n; n += 1)
															var93[n][p] = DistributionSampling.sampleBernoulli(RNG$3, bias[st[p][l][n]]);
												}
											);
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
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample19)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var17]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample29)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample54)
			st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample72)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, samples, 1,
				(int forStart$i$var55, int forEnd$i$var55, int threadID$i$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var55 = forStart$i$var55; i$var55 < forEnd$i$var55; i$var55 += 1) {
							int[][] var62 = st[i$var55];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, samples, 1,
								(int forStart$index$j$var58, int forEnd$index$j$var58, int threadID$index$j$var58, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int index$j$var58 = forStart$index$j$var58; index$j$var58 < forEnd$index$j$var58; index$j$var58 += 1) {
											int j$var58 = index$j$var58;
											
											//  Outer loop for dispatching multiple batches of iterations to execute in parallel
											parallelFor(RNG$2, 0, samples, 1,
												(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
													
														// Inner loop for running batches of iterations, each batch has its own random number
														// generator.
														for(int k = forStart$k; k < forEnd$k; k += 1)
															var62[j$var58][k] = DistributionSampling.sampleCategorical(RNG$3, m[0]);
												}
											);
										}
								}
							);
						}
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample19)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var17]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample29)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample54)
			st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample72)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, samples, 1,
				(int forStart$i$var55, int forEnd$i$var55, int threadID$i$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var55 = forStart$i$var55; i$var55 < forEnd$i$var55; i$var55 += 1) {
							int[][] var62 = st[i$var55];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, samples, 1,
								(int forStart$index$j$var58, int forEnd$index$j$var58, int threadID$index$j$var58, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int index$j$var58 = forStart$index$j$var58; index$j$var58 < forEnd$index$j$var58; index$j$var58 += 1) {
											int j$var58 = index$j$var58;
											
											//  Outer loop for dispatching multiple batches of iterations to execute in parallel
											parallelFor(RNG$2, 0, samples, 1,
												(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
													
														// Inner loop for running batches of iterations, each batch has its own random number
														// generator.
														for(int k = forStart$k; k < forEnd$k; k += 1)
															var62[j$var58][k] = DistributionSampling.sampleCategorical(RNG$3, m[0]);
												}
											);
										}
								}
							);
						}
				}
			);

	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample19)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
								sample19(var17, threadID$var17, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample29)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
								sample29(var26, threadID$var26, RNG$1);
					}
				);

			if(!fixedFlag$sample54)
				sample54();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample72)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 1, samples, 1,
					(int forStart$index$i$var55, int forEnd$index$i$var55, int threadID$index$i$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int index$i$var55 = forStart$index$i$var55; index$i$var55 < forEnd$index$i$var55; index$i$var55 += 1) {
								int i$var55 = index$i$var55;
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, samples, 1,
									(int forStart$index$j$var58, int forEnd$index$j$var58, int threadID$index$j$var58, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int index$j$var58 = forStart$index$j$var58; index$j$var58 < forEnd$index$j$var58; index$j$var58 += 1) {
												int j$var58 = index$j$var58;
												
												//  Outer loop for dispatching multiple batches of iterations to execute in parallel
												parallelFor(RNG$2, 0, samples, 1,
													(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
														
															// Inner loop for running batches of iterations, each batch has its own random number
															// generator.
															for(int k = forStart$k; k < forEnd$k; k += 1)
																sample72(i$var55, j$var58, k, threadID$k, RNG$3);
													}
												);
											}
									}
								);
							}
					}
				);

		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample72)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 1, samples, 1,
					(int forStart$index$i$var55, int forEnd$index$i$var55, int threadID$index$i$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int index$i$var55 = forStart$index$i$var55; index$i$var55 < forEnd$index$i$var55; index$i$var55 += 1) {
								int i$var55 = index$i$var55;
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, samples, 1,
									(int forStart$index$j$var58, int forEnd$index$j$var58, int threadID$index$j$var58, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int index$j$var58 = forStart$index$j$var58; index$j$var58 < forEnd$index$j$var58; index$j$var58 += 1) {
												int j$var58 = index$j$var58;
												
												//  Outer loop for dispatching multiple batches of iterations to execute in parallel
												parallelFor(RNG$2, 0, samples, 1,
													(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
														
															// Inner loop for running batches of iterations, each batch has its own random number
															// generator.
															for(int k = forStart$k; k < forEnd$k; k += 1)
																sample72(i$var55, j$var58, k, threadID$k, RNG$3);
													}
												);
											}
									}
								);
							}
					}
				);

			if(!fixedFlag$sample54)
				sample54();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample29)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
								sample29(var26, threadID$var26, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample19)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
								sample19(var17, threadID$var17, RNG$1);
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
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$i$var10, int forEnd$i$var10, int threadID$i$var10, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var10 = forStart$i$var10; i$var10 < forEnd$i$var10; i$var10 += 1)
						v[i$var10] = 0.1;
			}
		);
		samples = length$flipsMeasured.length;
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
		logProbability$var13 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample19)
			logProbability$var18 = 0.0;
		logProbability$var22 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$var27 = 0.0;
		logProbability$var48 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample54)
			logProbability$var49 = 0.0;
		logProbability$var66 = 0.0;
		if(!fixedProbFlag$sample72)
			logProbability$var67 = 0.0;
		logProbability$var99 = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample107)
			logProbability$var100 = 0.0;
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
		if(fixedFlag$sample19)
			logProbabilityValue$sample19();
		if(fixedFlag$sample29)
			logProbabilityValue$sample29();
		if(fixedFlag$sample54)
			logProbabilityValue$sample54();
		if(fixedFlag$sample72)
			logProbabilityValue$sample72();
		logProbabilityValue$sample107();
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
		logProbabilityValue$sample19();
		logProbabilityValue$sample29();
		logProbabilityValue$sample54();
		logProbabilityValue$sample72();
		logProbabilityValue$sample107();
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
		logProbabilityValue$sample19();
		logProbabilityValue$sample29();
		logProbabilityValue$sample54();
		logProbabilityValue$sample72();
		logProbabilityValue$sample107();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample19)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var17]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample29)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample54)
			st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample72)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, samples, 1,
				(int forStart$i$var55, int forEnd$i$var55, int threadID$i$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var55 = forStart$i$var55; i$var55 < forEnd$i$var55; i$var55 += 1) {
							int[][] var62 = st[i$var55];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, samples, 1,
								(int forStart$index$j$var58, int forEnd$index$j$var58, int threadID$index$j$var58, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int index$j$var58 = forStart$index$j$var58; index$j$var58 < forEnd$index$j$var58; index$j$var58 += 1) {
											int j$var58 = index$j$var58;
											
											//  Outer loop for dispatching multiple batches of iterations to execute in parallel
											parallelFor(RNG$2, 0, samples, 1,
												(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
													
														// Inner loop for running batches of iterations, each batch has its own random number
														// generator.
														for(int k = forStart$k; k < forEnd$k; k += 1)
															var62[j$var58][k] = DistributionSampling.sampleCategorical(RNG$3, m[0]);
												}
											);
										}
								}
							);
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
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[][] cv$source2 = flipsMeasured[cv$index1];
			boolean[][] cv$target2 = flips[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1) {
				boolean[] cv$source3 = cv$source2[cv$index2];
				boolean[] cv$target3 = cv$target2[cv$index2];
				int cv$length3 = cv$target3.length;
				for(int cv$index3 = 0; cv$index3 < cv$length3; cv$index3 += 1)
					cv$target3[cv$index3] = cv$source3[cv$index3];
			}
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart4b(boolean[][][] flipsMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n        \n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        \n        int[][][] st = new int[samples][samples][samples];\n\n        st[0][0][0] = categorical(m[0]).sample();\n\n        for(int i:[1..samples))\n            for(int j:[0..samples))\n                for(int k:[0..samples))\n                    st[i][j][k] = categorical(m[0]).sample();\n            \n        boolean[][][] flips = new boolean[samples][][];\n        for(int i:[0..samples)) {\n            flips[i] = new boolean[samples][];\n            for(int j:[0..samples))\n                flips[i][j] = new boolean[samples];\n        }\n            \n        for(int l:[0..samples))\n            for(int p:[0..samples))\n                for(int n:[0..samples))\n                    flips[l][n][p] = bernoulli(bias[st[p][l][n]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
	}
}