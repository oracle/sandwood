package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart4b$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart4b$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private double[][] cv$var123$stateProbabilityGlobal;
	private double[][] cv$var32$countGlobal;
	private double[] cv$var83$stateProbabilityGlobal;
	private boolean fixedFlag$sample128 = false;
	private boolean fixedFlag$sample195 = false;
	private boolean fixedFlag$sample33 = false;
	private boolean fixedFlag$sample50 = false;
	private boolean fixedFlag$sample88 = false;
	private boolean fixedProbFlag$sample128 = false;
	private boolean fixedProbFlag$sample195 = false;
	private boolean fixedProbFlag$sample33 = false;
	private boolean fixedProbFlag$sample50 = false;
	private boolean fixedProbFlag$sample88 = false;
	private boolean[][][] flips;
	private boolean[][][] flipsMeasured;
	private int[][] length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double logProbability$st;
	private double logProbability$var122;
	private double logProbability$var123;
	private double logProbability$var187;
	private double logProbability$var188;
	private double logProbability$var20;
	private double logProbability$var32;
	private double logProbability$var36;
	private double logProbability$var48;
	private double logProbability$var82;
	private double logProbability$var83;
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
		// Set flags for all the side effects of bias including if probabilities need to be
		// updated.
		// Set bias with flag to mark that it has been set so another array doesn't need to
		// be constructed
		bias = cv$value;
		setFlag$bias = true;
		
		// Unset the fixed probability flag for sample 50 as it depends on bias.
		fixedProbFlag$sample50 = false;
		
		// Unset the fixed probability flag for sample 195 as it depends on bias.
		fixedProbFlag$sample195 = false;
	}

	// Getter for fixedFlag$sample128.
	@Override
	public final boolean get$fixedFlag$sample128() {
		return fixedFlag$sample128;
	}

	// Setter for fixedFlag$sample128.
	@Override
	public final void set$fixedFlag$sample128(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample128 including if probabilities
		// need to be updated.
		fixedFlag$sample128 = cv$value;
		
		// Should the probability of sample 128 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample128" with its value "cv$value".
		fixedProbFlag$sample128 = (cv$value && fixedProbFlag$sample128);
		
		// Should the probability of sample 195 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample128" with its value "cv$value".
		fixedProbFlag$sample195 = (cv$value && fixedProbFlag$sample195);
	}

	// Getter for fixedFlag$sample195.
	@Override
	public final boolean get$fixedFlag$sample195() {
		return fixedFlag$sample195;
	}

	// Setter for fixedFlag$sample195.
	@Override
	public final void set$fixedFlag$sample195(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample195 including if probabilities
		// need to be updated.
		fixedFlag$sample195 = cv$value;
		
		// Should the probability of sample 195 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample195" with its value "cv$value".
		fixedProbFlag$sample195 = (cv$value && fixedProbFlag$sample195);
	}

	// Getter for fixedFlag$sample33.
	@Override
	public final boolean get$fixedFlag$sample33() {
		return fixedFlag$sample33;
	}

	// Setter for fixedFlag$sample33.
	@Override
	public final void set$fixedFlag$sample33(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample33 including if probabilities
		// need to be updated.
		fixedFlag$sample33 = cv$value;
		
		// Should the probability of sample 33 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample33" with its value "cv$value".
		fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
		
		// Should the probability of sample 88 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample33" with its value "cv$value".
		fixedProbFlag$sample88 = (cv$value && fixedProbFlag$sample88);
		
		// Should the probability of sample 128 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample33" with its value "cv$value".
		fixedProbFlag$sample128 = (cv$value && fixedProbFlag$sample128);
	}

	// Getter for fixedFlag$sample50.
	@Override
	public final boolean get$fixedFlag$sample50() {
		return fixedFlag$sample50;
	}

	// Setter for fixedFlag$sample50.
	@Override
	public final void set$fixedFlag$sample50(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample50 including if probabilities
		// need to be updated.
		fixedFlag$sample50 = cv$value;
		
		// Should the probability of sample 50 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample50" with its value "cv$value".
		fixedProbFlag$sample50 = (cv$value && fixedProbFlag$sample50);
		
		// Should the probability of sample 195 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample50" with its value "cv$value".
		fixedProbFlag$sample195 = (cv$value && fixedProbFlag$sample195);
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
		
		// Should the probability of sample 195 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample88" with its value "cv$value".
		fixedProbFlag$sample195 = (cv$value && fixedProbFlag$sample195);
	}

	// Getter for flips.
	@Override
	public final boolean[][][] get$flips() {
		return flips;
	}

	// Setter for flips.
	@Override
	public final void set$flips(boolean[][][] cv$value) {
		// Set flags for all the side effects of flips including if probabilities need to
		// be updated.
		// Set flips with flag to mark that it has been set so another array doesn't need
		// to be constructed
		flips = cv$value;
		setFlag$flips = true;
		
		// Unset the fixed probability flag for sample 195 as it depends on flips.
		fixedProbFlag$sample195 = false;
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
		// Set flags for all the side effects of m including if probabilities need to be updated.
		// Set m with flag to mark that it has been set so another array doesn't need to be
		// constructed
		m = cv$value;
		setFlag$m = true;
		
		// Unset the fixed probability flag for sample 33 as it depends on m.
		fixedProbFlag$sample33 = false;
		
		// Unset the fixed probability flag for sample 88 as it depends on m.
		fixedProbFlag$sample88 = false;
		
		// Unset the fixed probability flag for sample 128 as it depends on m.
		fixedProbFlag$sample128 = false;
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
		// Set flags for all the side effects of st including if probabilities need to be
		// updated.
		// Set st with flag to mark that it has been set so another array doesn't need to
		// be constructed
		st = cv$value;
		setFlag$st = true;
		
		// Unset the fixed probability flag for sample 88 as it depends on st.
		fixedProbFlag$sample88 = false;
		
		// Unset the fixed probability flag for sample 128 as it depends on st.
		fixedProbFlag$sample128 = false;
		
		// Unset the fixed probability flag for sample 195 as it depends on st.
		fixedProbFlag$sample195 = false;
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

	// Calculate the probability of the samples represented by sample128 using sampled
	// values.
	private final void logProbabilityValue$sample128() {
		// Determine if we need to calculate the values for sample task 128 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample128) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var99 = 1; i$var99 < samples; i$var99 += 1) {
				for(int j$var108 = 0; j$var108 < samples; j$var108 += 1) {
					for(int k = 0; k < samples; k += 1) {
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[i$var99][j$var108][k];
						double[] var121 = m[0];
						
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
						cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var121.length))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
					}
				}
			}
			logProbability$var122 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var123 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample128)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample128 = (fixedFlag$sample128 && fixedFlag$sample33);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
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
			if(fixedFlag$sample128)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var123);
		}
	}

	// Calculate the probability of the samples represented by sample195 using sampled
	// values.
	private final void logProbabilityValue$sample195() {
		// Determine if we need to calculate the values for sample task 195 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample195) {
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
			logProbability$var187 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var188 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample195 = (((fixedFlag$sample195 && fixedFlag$sample50) && fixedFlag$sample88) && fixedFlag$sample128);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var187 = logProbability$var188;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$flips = (logProbability$flips + logProbability$var188);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var188);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var188);
		}
	}

	// Calculate the probability of the samples represented by sample33 using sampled
	// values.
	private final void logProbabilityValue$sample33() {
		// Determine if we need to calculate the values for sample task 33 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample33) {
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
			logProbability$var20 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var32 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample33)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample33 = fixedFlag$sample33;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var20 = logProbability$var32;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var32);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var32);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample33)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var32);
		}
	}

	// Calculate the probability of the samples represented by sample50 using sampled
	// values.
	private final void logProbabilityValue$sample50() {
		// Determine if we need to calculate the values for sample task 50 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample50) {
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
			logProbability$var36 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var48 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample50)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample50 = fixedFlag$sample50;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var36 = logProbability$var48;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$bias = (logProbability$bias + logProbability$var48);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var48);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample50)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var48);
		}
	}

	// Calculate the probability of the samples represented by sample88 using sampled
	// values.
	private final void logProbabilityValue$sample88() {
		// Determine if we need to calculate the values for sample task 88 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample88) {
			// Generating probabilities for sample task
			// The sample value to calculate the probability of generating
			int cv$sampleValue = st[0][0][0];
			double[] var81 = m[0];
			
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
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var81.length))?Math.log(var81[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var82 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var83 = cv$distributionAccumulator;
			
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
			fixedProbFlag$sample88 = (fixedFlag$sample88 && fixedFlag$sample33);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var82 = logProbability$var83;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var83);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var83);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample88)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var83);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 128 drawn from Categorical 122. Inference was performed using variable
	// marginalization.
	private final void sample128(int i$var99, int j$var108, int k, int threadID$cv$k, Rng RNG$) {
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var123$stateProbabilityGlobal[threadID$cv$k];
		
		// Unrolled loop
		{
			// Variable declaration of cv$temp$0$var121 moved.
			// 
			// Constructing a random variable input for use later.
			double[] cv$temp$0$var121 = m[0];
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			// 
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 195 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			cv$stateProbabilityLocal[0] = (DistributionSampling.logProbabilityBernoulli(flips[j$var108][k][i$var99], bias[0]) + ((0 < cv$temp$0$var121.length)?Math.log(cv$temp$0$var121[0]):Double.NEGATIVE_INFINITY));
		}
		
		// Variable declaration of cv$temp$0$var121 moved.
		// 
		// Constructing a random variable input for use later.
		double[] cv$temp$0$var121 = m[0];
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 195 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		cv$stateProbabilityLocal[1] = (DistributionSampling.logProbabilityBernoulli(flips[j$var108][k][i$var99], bias[1]) + ((1 < cv$temp$0$var121.length)?Math.log(cv$temp$0$var121[1]):Double.NEGATIVE_INFINITY));
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		double cv$lseMax = cv$stateProbabilityLocal[0];
		
		// Unrolled loop
		double cv$lseElementValue = cv$stateProbabilityLocal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		
		// If the maximum value is -infinity return -infinity.
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		
		// Sum the values in the array.
		else
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			// 
			// Initialise the sum of the array elements
			cv$logSum = (Math.log((Math.exp((cv$stateProbabilityLocal[0] - cv$lseMax)) + Math.exp((cv$stateProbabilityLocal[1] - cv$lseMax)))) + cv$lseMax);
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Unrolled loop
			// cv$noStates's comment
			// variable marginalization
			cv$stateProbabilityLocal[0] = 0.5;
			
			// cv$noStates's comment
			// variable marginalization
			cv$stateProbabilityLocal[1] = 0.5;
		} else {
			// Unrolled loop
			cv$stateProbabilityLocal[0] = Math.exp((cv$stateProbabilityLocal[0] - cv$logSum));
			cv$stateProbabilityLocal[1] = Math.exp((cv$stateProbabilityLocal[1] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// cv$noStates's comment
		// variable marginalization
		for(int cv$indexName = 2; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		st[i$var99][j$var108][k] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 33 drawn from Dirichlet 20. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample33(int var31, int threadID$cv$var31, Rng RNG$) {
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var32$countGlobal[threadID$cv$var31];
		cv$countLocal[0] = 0.0;
		cv$countLocal[1] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var31 == 0)) {
			// Processing random variable 82.
			// 
			// Looking for a path between Sample 33 and consumer Categorical 82.
			// 
			// Processing sample task 88 of consumer random variable null.
			// 
			// Increment the sample counter with the value sampled by sample task 88 of random
			// variable var82
			cv$countLocal[st[0][0][0]] = (cv$countLocal[st[0][0][0]] + 1.0);
			
			// Processing random variable 122.
			// 
			// Looking for a path between Sample 33 and consumer Categorical 122.
			for(int i$var99 = 1; i$var99 < samples; i$var99 += 1) {
				for(int j$var108 = 0; j$var108 < samples; j$var108 += 1) {
					for(int k = 0; k < samples; k += 1)
						// Processing sample task 128 of consumer random variable null.
						// 
						// Increment the sample counter with the value sampled by sample task 128 of random
						// variable var122
						cv$countLocal[st[i$var99][j$var108][k]] = (cv$countLocal[st[i$var99][j$var108][k]] + 1.0);
				}
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var31]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 50 drawn from Beta 36. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample50(int var47, int threadID$cv$var47, Rng RNG$) {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		
		// Processing random variable 187.
		// 
		// Looking for a path between Sample 50 and consumer Bernoulli 187.
		for(int l = 0; l < samples; l += 1) {
			for(int p = 0; p < samples; p += 1) {
				for(int n = 0; n < samples; n += 1) {
					if((var47 == st[p][l][n])) {
						// Processing sample task 195 of consumer random variable null.
						// 
						// Include the value sampled by task 195 from random variable var187.
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
		bias[var47] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 88 drawn from Categorical 82. Inference was performed using variable
	// marginalization.
	private final void sample88() {
		// Unrolled loop
		{
			// Variable declaration of cv$temp$0$var81 moved.
			// 
			// Constructing a random variable input for use later.
			double[] cv$temp$0$var81 = m[0];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = ((0 < cv$temp$0$var81.length)?Math.log(cv$temp$0$var81[0]):Double.NEGATIVE_INFINITY);
			
			// Substituted "p" with its value "0".
			if((0 < samples))
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 195 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 195 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "l" with its value "0".
				// 
				// cv$temp$1$var186's comment
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 187.
				// 
				// Looking for a path between Sample 88 and consumer Bernoulli 187.
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
			cv$var83$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Variable declaration of cv$temp$0$var81 moved.
		// 
		// Constructing a random variable input for use later.
		double[] cv$temp$0$var81 = m[0];
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = ((1 < cv$temp$0$var81.length)?Math.log(cv$temp$0$var81[1]):Double.NEGATIVE_INFINITY);
		
		// Substituted "p" with its value "0".
		if((0 < samples))
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 195 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Processing sample task 195 of consumer random variable null.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "l" with its value "0".
			// 
			// cv$temp$1$var186's comment
			// Constructing a random variable input for use later.
			// 
			// Processing random variable 187.
			// 
			// Looking for a path between Sample 88 and consumer Bernoulli 187.
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
		cv$var83$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var83$stateProbabilityGlobal[0];
		
		// Unrolled loop
		// 
		// Get a local reference to the scratch space.
		double cv$lseElementValue = cv$var83$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		
		// If the maximum value is -infinity return -infinity.
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		
		// Sum the values in the array.
		else
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			// 
			// Get a local reference to the scratch space.
			// 
			// Get a local reference to the scratch space.
			// 
			// Initialise the sum of the array elements
			cv$logSum = (Math.log((Math.exp((cv$var83$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var83$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Unrolled loop
			// Get a local reference to the scratch space.
			cv$var83$stateProbabilityGlobal[0] = 0.5;
			
			// Get a local reference to the scratch space.
			cv$var83$stateProbabilityGlobal[1] = 0.5;
		} else {
			// Unrolled loop
			// Get a local reference to the scratch space.
			cv$var83$stateProbabilityGlobal[0] = Math.exp((cv$var83$stateProbabilityGlobal[0] - cv$logSum));
			
			// Get a local reference to the scratch space.
			cv$var83$stateProbabilityGlobal[1] = Math.exp((cv$var83$stateProbabilityGlobal[1] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = 2; cv$indexName < cv$var83$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var83$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Get a local reference to the scratch space.
		st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, cv$var83$stateProbabilityGlobal);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var32$countGlobal
		{
			// Allocation of cv$var32$countGlobal for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var32$countGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var32$countGlobal[cv$index] = new double[2];
		}
		
		// Constructor for cv$var83$stateProbabilityGlobal
		// 
		// Allocation of cv$var83$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 86. Initially set to the value
		// of putTask 34.
		cv$var83$stateProbabilityGlobal = new double[2];
		
		// Allocation of cv$var123$stateProbabilityGlobal for multithreaded execution
		// 
		// Get the thread count.
		int cv$threadCount = threadCount();
		
		// Allocate an array to hold a copy per thread
		cv$var123$stateProbabilityGlobal = new double[cv$threadCount][];
		
		// Populate the array with a copy per thread
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			// Variable to record the maximum value of Task Get 126. Initially set to the value
			// of putTask 34.
			cv$var123$stateProbabilityGlobal[cv$index] = new double[2];
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
			for(int var61 = 0; var61 < length$flipsMeasured.length; var61 += 1) {
				int[][] subarray$0 = new int[length$flipsMeasured.length][];
				st[var61] = subarray$0;
				for(int var71 = 0; var71 < length$flipsMeasured.length; var71 += 1)
					subarray$0[var71] = new int[length$flipsMeasured.length];
			}
		}
		
		// If flips has not been set already allocate space.
		if(!setFlag$flips) {
			// Constructor for flips
			flips = new boolean[length$flipsMeasured.length][][];
			for(int i$var137 = 0; i$var137 < length$flipsMeasured.length; i$var137 += 1) {
				boolean[][] subarray$0 = new boolean[length$flipsMeasured.length][];
				flips[i$var137] = subarray$0;
				for(int j$var148 = 0; j$var148 < length$flipsMeasured.length; j$var148 += 1)
					subarray$0[j$var148] = new boolean[length$flipsMeasured.length];
			}
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample33)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var31]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample50)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var47, int forEnd$var47, int threadID$var47, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var47 = forStart$var47; var47 < forEnd$var47; var47 += 1)
							bias[var47] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample88)
			st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample128)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, samples, 1,
				(int forStart$i$var99, int forEnd$i$var99, int threadID$i$var99, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var99 = forStart$i$var99; i$var99 < forEnd$i$var99; i$var99 += 1) {
							int[][] var118 = st[i$var99];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, samples, 1,
								(int forStart$index$j$var108, int forEnd$index$j$var108, int threadID$index$j$var108, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int index$j$var108 = forStart$index$j$var108; index$j$var108 < forEnd$index$j$var108; index$j$var108 += 1) {
											int j$var108 = index$j$var108;
											int threadID$j$var108 = threadID$index$j$var108;
											
											//  Outer loop for dispatching multiple batches of iterations to execute in parallel
											parallelFor(RNG$2, 0, samples, 1,
												(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
													
														// Inner loop for running batches of iterations, each batch has its own random number
														// generator.
														for(int k = forStart$k; k < forEnd$k; k += 1)
															var118[j$var108][k] = DistributionSampling.sampleCategorical(RNG$3, m[0]);
												}
											);
										}
								}
							);
						}
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample195)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$index$l, int forEnd$index$l, int threadID$index$l, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$l = forStart$index$l; index$l < forEnd$index$l; index$l += 1) {
							int l = index$l;
							int threadID$l = threadID$index$l;
							boolean[][] var181 = flips[l];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, samples, 1,
								(int forStart$index$p, int forEnd$index$p, int threadID$index$p, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int index$p = forStart$index$p; index$p < forEnd$index$p; index$p += 1) {
											int p = index$p;
											int threadID$p = threadID$index$p;
											
											//  Outer loop for dispatching multiple batches of iterations to execute in parallel
											parallelFor(RNG$2, 0, samples, 1,
												(int forStart$n, int forEnd$n, int threadID$n, org.sandwood.random.internal.Rng RNG$3) -> { 
													
														// Inner loop for running batches of iterations, each batch has its own random number
														// generator.
														for(int n = forStart$n; n < forEnd$n; n += 1)
															var181[n][p] = DistributionSampling.sampleBernoulli(RNG$3, bias[st[p][l][n]]);
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
		if(!fixedFlag$sample33)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var31]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample50)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var47, int forEnd$var47, int threadID$var47, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var47 = forStart$var47; var47 < forEnd$var47; var47 += 1)
							bias[var47] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample88)
			st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample128)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, samples, 1,
				(int forStart$i$var99, int forEnd$i$var99, int threadID$i$var99, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var99 = forStart$i$var99; i$var99 < forEnd$i$var99; i$var99 += 1) {
							int[][] var118 = st[i$var99];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, samples, 1,
								(int forStart$index$j$var108, int forEnd$index$j$var108, int threadID$index$j$var108, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int index$j$var108 = forStart$index$j$var108; index$j$var108 < forEnd$index$j$var108; index$j$var108 += 1) {
											int j$var108 = index$j$var108;
											int threadID$j$var108 = threadID$index$j$var108;
											
											//  Outer loop for dispatching multiple batches of iterations to execute in parallel
											parallelFor(RNG$2, 0, samples, 1,
												(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
													
														// Inner loop for running batches of iterations, each batch has its own random number
														// generator.
														for(int k = forStart$k; k < forEnd$k; k += 1)
															var118[j$var108][k] = DistributionSampling.sampleCategorical(RNG$3, m[0]);
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
		if(!fixedFlag$sample33)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var31]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample50)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var47, int forEnd$var47, int threadID$var47, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var47 = forStart$var47; var47 < forEnd$var47; var47 += 1)
							bias[var47] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample88)
			st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample128)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, samples, 1,
				(int forStart$i$var99, int forEnd$i$var99, int threadID$i$var99, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var99 = forStart$i$var99; i$var99 < forEnd$i$var99; i$var99 += 1) {
							int[][] var118 = st[i$var99];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, samples, 1,
								(int forStart$index$j$var108, int forEnd$index$j$var108, int threadID$index$j$var108, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int index$j$var108 = forStart$index$j$var108; index$j$var108 < forEnd$index$j$var108; index$j$var108 += 1) {
											int j$var108 = index$j$var108;
											int threadID$j$var108 = threadID$index$j$var108;
											
											//  Outer loop for dispatching multiple batches of iterations to execute in parallel
											parallelFor(RNG$2, 0, samples, 1,
												(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
													
														// Inner loop for running batches of iterations, each batch has its own random number
														// generator.
														for(int k = forStart$k; k < forEnd$k; k += 1)
															var118[j$var108][k] = DistributionSampling.sampleCategorical(RNG$3, m[0]);
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
			if(!fixedFlag$sample33)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
								sample33(var31, threadID$var31, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample50)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var47, int forEnd$var47, int threadID$var47, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var47 = forStart$var47; var47 < forEnd$var47; var47 += 1)
								sample50(var47, threadID$var47, RNG$1);
					}
				);

			if(!fixedFlag$sample88)
				sample88();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample128)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 1, samples, 1,
					(int forStart$index$i$var99, int forEnd$index$i$var99, int threadID$index$i$var99, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int index$i$var99 = forStart$index$i$var99; index$i$var99 < forEnd$index$i$var99; index$i$var99 += 1) {
								int i$var99 = index$i$var99;
								int threadID$i$var99 = threadID$index$i$var99;
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, samples, 1,
									(int forStart$index$j$var108, int forEnd$index$j$var108, int threadID$index$j$var108, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int index$j$var108 = forStart$index$j$var108; index$j$var108 < forEnd$index$j$var108; index$j$var108 += 1) {
												int j$var108 = index$j$var108;
												int threadID$j$var108 = threadID$index$j$var108;
												
												//  Outer loop for dispatching multiple batches of iterations to execute in parallel
												parallelFor(RNG$2, 0, samples, 1,
													(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
														
															// Inner loop for running batches of iterations, each batch has its own random number
															// generator.
															for(int k = forStart$k; k < forEnd$k; k += 1)
																sample128(i$var99, j$var108, k, threadID$k, RNG$3);
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
			if(!fixedFlag$sample128)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 1, samples, 1,
					(int forStart$index$i$var99, int forEnd$index$i$var99, int threadID$index$i$var99, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int index$i$var99 = forStart$index$i$var99; index$i$var99 < forEnd$index$i$var99; index$i$var99 += 1) {
								int i$var99 = index$i$var99;
								int threadID$i$var99 = threadID$index$i$var99;
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, samples, 1,
									(int forStart$index$j$var108, int forEnd$index$j$var108, int threadID$index$j$var108, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int index$j$var108 = forStart$index$j$var108; index$j$var108 < forEnd$index$j$var108; index$j$var108 += 1) {
												int j$var108 = index$j$var108;
												int threadID$j$var108 = threadID$index$j$var108;
												
												//  Outer loop for dispatching multiple batches of iterations to execute in parallel
												parallelFor(RNG$2, 0, samples, 1,
													(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
														
															// Inner loop for running batches of iterations, each batch has its own random number
															// generator.
															for(int k = forStart$k; k < forEnd$k; k += 1)
																sample128(i$var99, j$var108, k, threadID$k, RNG$3);
													}
												);
											}
									}
								);
							}
					}
				);

			if(!fixedFlag$sample88)
				sample88();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample50)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var47, int forEnd$var47, int threadID$var47, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var47 = forStart$var47; var47 < forEnd$var47; var47 += 1)
								sample50(var47, threadID$var47, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample33)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
								sample33(var31, threadID$var31, RNG$1);
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
			(int forStart$i$var17, int forEnd$i$var17, int threadID$i$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var17 = forStart$i$var17; i$var17 < forEnd$i$var17; i$var17 += 1)
						v[i$var17] = 0.1;
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
		logProbability$var20 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample33)
			logProbability$var32 = 0.0;
		logProbability$var36 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample50)
			logProbability$var48 = 0.0;
		logProbability$var82 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample88)
			logProbability$var83 = 0.0;
		logProbability$var122 = 0.0;
		if(!fixedProbFlag$sample128)
			logProbability$var123 = 0.0;
		logProbability$var187 = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample195)
			logProbability$var188 = 0.0;
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
		if(fixedFlag$sample33)
			logProbabilityValue$sample33();
		if(fixedFlag$sample50)
			logProbabilityValue$sample50();
		if(fixedFlag$sample88)
			logProbabilityValue$sample88();
		if(fixedFlag$sample128)
			logProbabilityValue$sample128();
		logProbabilityValue$sample195();
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
		logProbabilityValue$sample33();
		logProbabilityValue$sample50();
		logProbabilityValue$sample88();
		logProbabilityValue$sample128();
		logProbabilityValue$sample195();
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
		logProbabilityValue$sample33();
		logProbabilityValue$sample50();
		logProbabilityValue$sample88();
		logProbabilityValue$sample128();
		logProbabilityValue$sample195();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample33)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var31]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample50)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var47, int forEnd$var47, int threadID$var47, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var47 = forStart$var47; var47 < forEnd$var47; var47 += 1)
							bias[var47] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample88)
			st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample128)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, samples, 1,
				(int forStart$i$var99, int forEnd$i$var99, int threadID$i$var99, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var99 = forStart$i$var99; i$var99 < forEnd$i$var99; i$var99 += 1) {
							int[][] var118 = st[i$var99];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, samples, 1,
								(int forStart$index$j$var108, int forEnd$index$j$var108, int threadID$index$j$var108, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int index$j$var108 = forStart$index$j$var108; index$j$var108 < forEnd$index$j$var108; index$j$var108 += 1) {
											int j$var108 = index$j$var108;
											int threadID$j$var108 = threadID$index$j$var108;
											
											//  Outer loop for dispatching multiple batches of iterations to execute in parallel
											parallelFor(RNG$2, 0, samples, 1,
												(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
													
														// Inner loop for running batches of iterations, each batch has its own random number
														// generator.
														for(int k = forStart$k; k < forEnd$k; k += 1)
															var118[j$var108][k] = DistributionSampling.sampleCategorical(RNG$3, m[0]);
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
		     + "model HMMTestPart4b(boolean[][][] flipsMeasured) {\n"
		     + "        int states = 2;\n"
		     + "\n"
		     + "        double[] v = new double[states];\n"
		     + "        for(int i:[0..states))\n"
		     + "            v[i] = 0.1;\n"
		     + "        \n"
		     + "        double[][] m = dirichlet(v).sample(states);\n"
		     + "        double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "        int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "        int[][][] st = new int[samples][samples][samples];\n"
		     + "\n"
		     + "        st[0][0][0] = categorical(m[0]).sample();\n"
		     + "\n"
		     + "        for(int i:[1..samples))\n"
		     + "            for(int j:[0..samples))\n"
		     + "                for(int k:[0..samples))\n"
		     + "                    st[i][j][k] = categorical(m[0]).sample();\n"
		     + "            \n"
		     + "        boolean[][][] flips = new boolean[samples][][];\n"
		     + "        for(int i:[0..samples)) {\n"
		     + "            flips[i] = new boolean[samples][];\n"
		     + "            for(int j:[0..samples))\n"
		     + "                flips[i][j] = new boolean[samples];\n"
		     + "        }\n"
		     + "            \n"
		     + "        for(int l:[0..samples))\n"
		     + "            for(int p:[0..samples))\n"
		     + "                for(int n:[0..samples))\n"
		     + "                    flips[l][n][p] = bernoulli(bias[st[p][l][n]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}