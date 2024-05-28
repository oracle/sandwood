package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart3d$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart3d$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private double[][] cv$var30$countGlobal;
	private double[] cv$var55$stateProbabilityGlobal;
	private double[] cv$var80$stateProbabilityGlobal;
	private boolean fixedFlag$sample123 = false;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample48 = false;
	private boolean fixedFlag$sample58 = false;
	private boolean fixedFlag$sample83 = false;
	private boolean fixedProbFlag$sample123 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample48 = false;
	private boolean fixedProbFlag$sample58 = false;
	private boolean fixedProbFlag$sample83 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int[][] indirection;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample123;
	private double[] logProbability$sample83;
	private double logProbability$st;
	private double logProbability$st2;
	private double[] logProbability$var119;
	private double logProbability$var18;
	private double logProbability$var30;
	private double logProbability$var34;
	private double logProbability$var46;
	private double logProbability$var54;
	private double logProbability$var55;
	private double[] logProbability$var79;
	private double[][] m;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[] st;
	private int[] st2;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart3d$MultiThreadCPU(ExecutionTarget target) {
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
		
		// Unset the fixed probability flag for sample 123 as it depends on bias.
		fixedProbFlag$sample123 = false;
	}

	// Getter for fixedFlag$sample123.
	@Override
	public final boolean get$fixedFlag$sample123() {
		return fixedFlag$sample123;
	}

	// Setter for fixedFlag$sample123.
	@Override
	public final void set$fixedFlag$sample123(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample123 including if probabilities
		// need to be updated.
		fixedFlag$sample123 = cv$value;
		
		// Should the probability of sample 123 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample123" with its value "cv$value".
		fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
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
		// 
		// Substituted "fixedFlag$sample31" with its value "cv$value".
		fixedProbFlag$sample31 = (cv$value && fixedProbFlag$sample31);
		
		// Should the probability of sample 58 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample31" with its value "cv$value".
		fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
		
		// Should the probability of sample 83 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample31" with its value "cv$value".
		fixedProbFlag$sample83 = (cv$value && fixedProbFlag$sample83);
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
		// 
		// Substituted "fixedFlag$sample48" with its value "cv$value".
		fixedProbFlag$sample48 = (cv$value && fixedProbFlag$sample48);
		
		// Should the probability of sample 123 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample48" with its value "cv$value".
		fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
	}

	// Getter for fixedFlag$sample58.
	@Override
	public final boolean get$fixedFlag$sample58() {
		return fixedFlag$sample58;
	}

	// Setter for fixedFlag$sample58.
	@Override
	public final void set$fixedFlag$sample58(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample58 including if probabilities
		// need to be updated.
		fixedFlag$sample58 = cv$value;
		
		// Should the probability of sample 58 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample58" with its value "cv$value".
		fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
		
		// Should the probability of sample 83 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample58" with its value "cv$value".
		fixedProbFlag$sample83 = (cv$value && fixedProbFlag$sample83);
		
		// Should the probability of sample 123 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample58" with its value "cv$value".
		fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
	}

	// Getter for fixedFlag$sample83.
	@Override
	public final boolean get$fixedFlag$sample83() {
		return fixedFlag$sample83;
	}

	// Setter for fixedFlag$sample83.
	@Override
	public final void set$fixedFlag$sample83(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample83 including if probabilities
		// need to be updated.
		fixedFlag$sample83 = cv$value;
		
		// Should the probability of sample 83 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample83" with its value "cv$value".
		fixedProbFlag$sample83 = (cv$value && fixedProbFlag$sample83);
		
		// Should the probability of sample 123 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample83" with its value "cv$value".
		fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
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
		
		// Unset the fixed probability flag for sample 123 as it depends on flips.
		fixedProbFlag$sample123 = false;
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

	// Getter for logProbability$st2.
	@Override
	public final double get$logProbability$st2() {
		return logProbability$st2;
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
		
		// Unset the fixed probability flag for sample 58 as it depends on m.
		fixedProbFlag$sample58 = false;
		
		// Unset the fixed probability flag for sample 83 as it depends on m.
		fixedProbFlag$sample83 = false;
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
		
		// Unset the fixed probability flag for sample 58 as it depends on st.
		fixedProbFlag$sample58 = false;
		
		// Unset the fixed probability flag for sample 83 as it depends on st.
		fixedProbFlag$sample83 = false;
		
		// Unset the fixed probability flag for sample 123 as it depends on st.
		fixedProbFlag$sample123 = false;
	}

	// Getter for st2.
	@Override
	public final int[] get$st2() {
		return st2;
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

	// Calculate the probability of the samples represented by sample123 using sampled
	// values.
	private final void logProbabilityValue$sample123() {
		// Determine if we need to calculate the values for sample task 123 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample123) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[j], bias[(samples - st2[j])]);
				
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
				logProbability$var119[j] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample123[j] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample123 = (((fixedFlag$sample123 && fixedFlag$sample48) && fixedFlag$sample58) && fixedFlag$sample83);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample123[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var119[j] = cv$rvAccumulator;
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
			logProbability$var18 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var30 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample31)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample31 = fixedFlag$sample31;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var18 = logProbability$var30;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var30);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var30);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample31)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var30);
		}
	}

	// Calculate the probability of the samples represented by sample48 using sampled
	// values.
	private final void logProbabilityValue$sample48() {
		// Determine if we need to calculate the values for sample task 48 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample48) {
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
			logProbability$var34 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var46 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample48)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample48 = fixedFlag$sample48;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var34 = logProbability$var46;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$bias = (logProbability$bias + logProbability$var46);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var46);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample48)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var46);
		}
	}

	// Calculate the probability of the samples represented by sample58 using sampled
	// values.
	private final void logProbabilityValue$sample58() {
		// Determine if we need to calculate the values for sample task 58 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample58) {
			// Generating probabilities for sample task
			// The sample value to calculate the probability of generating
			int cv$sampleValue = st[0];
			double[] var53 = m[0];
			
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
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var54 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var55 = cv$distributionAccumulator;
			
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
			
			// Variable declaration of cv$guard$st2 moved.
			// Declaration comment was:
			// Guard to ensure that st2 is only updated once for this probability.
			// 
			// Set the guard so the update is only applied once.
			boolean cv$guard$st2 = true;
			
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
			logProbability$st2 = (logProbability$st2 + cv$distributionAccumulator);
			
			// Looking for a path between Sample 58 and consumer int[] 104.
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
				if(((0 == (indirection[(i$var73 - 1)][i$var73] / i$var73)) && !cv$guard$st2)) {
					// Set the guard so the update is only applied once.
					cv$guard$st2 = true;
					
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
					logProbability$st2 = (logProbability$st2 + cv$distributionAccumulator);
				}
			}
			
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
			if(fixedFlag$sample58)
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
			fixedProbFlag$sample58 = (fixedFlag$sample58 && fixedFlag$sample31);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var54 = logProbability$var55;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var55);
			
			// Variable declaration of cv$guard$st2 moved.
			// Declaration comment was:
			// Guard to ensure that st2 is only updated once for this probability.
			// 
			// Set the guard so the update is only applied once.
			boolean cv$guard$st2 = true;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st2 = (logProbability$st2 + logProbability$var55);
			
			// Looking for a path between Sample 58 and consumer int[] 104.
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
				if(((0 == (indirection[(i$var73 - 1)][i$var73] / i$var73)) && !cv$guard$st2)) {
					// Set the guard so the update is only applied once.
					cv$guard$st2 = true;
					
					// Update the variable probability
					// 
					// Variable declaration of cv$accumulator moved.
					logProbability$st2 = (logProbability$st2 + logProbability$var55);
				}
			}
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var55);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample58)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var55);
		}
	}

	// Calculate the probability of the samples represented by sample83 using sampled
	// values.
	private final void logProbabilityValue$sample83() {
		// Determine if we need to calculate the values for sample task 83 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample83) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = st[i$var73];
				double[] var78 = m[(samples - st2[(i$var73 - 1)])];
				
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
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var78.length))?Math.log(var78[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
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
				logProbability$var79[(i$var73 - 1)] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample83[(i$var73 - 1)] = cv$distributionAccumulator;
				
				// Guard to ensure that st2 is only updated once for this probability.
				boolean cv$guard$st2 = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 83 and consumer int[] 104.
				for(int index$i$2_1 = 1; index$i$2_1 < samples; index$i$2_1 += 1) {
					if(((i$var73 == (indirection[(index$i$2_1 - 1)][index$i$2_1] / index$i$2_1)) && !cv$guard$st2)) {
						// Set the guard so the update is only applied once.
						cv$guard$st2 = true;
						
						// Update the variable probability
						logProbability$st2 = (logProbability$st2 + cv$distributionAccumulator);
					}
				}
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample83)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample83 = ((fixedFlag$sample83 && fixedFlag$sample31) && fixedFlag$sample58);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
				double cv$sampleValue = logProbability$sample83[(i$var73 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var79[(i$var73 - 1)] = cv$sampleValue;
				
				// Guard to ensure that st2 is only updated once for this probability.
				boolean cv$guard$st2 = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 83 and consumer int[] 104.
				for(int index$i$3_1 = 1; index$i$3_1 < samples; index$i$3_1 += 1) {
					if(((i$var73 == (indirection[(index$i$3_1 - 1)][index$i$3_1] / index$i$3_1)) && !cv$guard$st2)) {
						// Set the guard so the update is only applied once.
						cv$guard$st2 = true;
						
						// Update the variable probability
						logProbability$st2 = (logProbability$st2 + cv$sampleValue);
					}
				}
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample83)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 31 drawn from Dirichlet 18. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample31(int var29, int threadID$cv$var29, Rng RNG$) {
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var30$countGlobal[threadID$cv$var29];
		cv$countLocal[0] = 0.0;
		cv$countLocal[1] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var29 == 0))
			// Looking for a path between Sample 31 and consumer Categorical 54.
			// 
			// Processing sample task 58 of consumer random variable null.
			// 
			// Increment the sample counter with the value sampled by sample task 58 of random
			// variable var54
			cv$countLocal[st[0]] = (cv$countLocal[st[0]] + 1.0);
		
		// Processing random variable 79.
		// 
		// Looking for a path between Sample 31 and consumer Categorical 79.
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if((var29 == (samples - st2[(i$var73 - 1)])))
				// Processing sample task 83 of consumer random variable null.
				// 
				// Increment the sample counter with the value sampled by sample task 83 of random
				// variable var79
				cv$countLocal[st[i$var73]] = (cv$countLocal[st[i$var73]] + 1.0);
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var29]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 48 drawn from Beta 34. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample48(int var45, int threadID$cv$var45, Rng RNG$) {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		
		// Processing random variable 119.
		// 
		// Looking for a path between Sample 48 and consumer Bernoulli 119.
		for(int j = 0; j < samples; j += 1) {
			if((var45 == (samples - st2[j]))) {
				// Processing sample task 123 of consumer random variable null.
				// 
				// Include the value sampled by task 123 from random variable var119.
				// Increment the number of samples.
				cv$count = (cv$count + 1);
				
				// If the sample value was positive increase the count
				if(flips[j])
					cv$sum = (cv$sum + 1);
			}
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		bias[var45] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 58 drawn from Categorical 54. Inference was performed using variable
	// marginalization.
	private final void sample58() {
		// Unrolled loop
		{
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			st[0] = 0;
			
			// Guards to ensure that st2 is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 58 and consumer int[] 104.
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
				if((0 == (indirection[(i$var73 - 1)][i$var73] / i$var73)))
					st2[(indirection[(i$var73 - 1)][i$var73] / i$var73)] = (samples - st[(indirection[(i$var73 - 1)][i$var73] / i$var73)]);
			}
			
			// Variable declaration of cv$temp$0$var53 moved.
			// 
			// Constructing a random variable input for use later.
			double[] cv$temp$0$var53 = m[0];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = ((0 < cv$temp$0$var53.length)?Math.log(cv$temp$0$var53[0]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 < samples)) {
				// Processing sample task 83 of consumer random variable null.
				// 
				// Variable declaration of cv$temp$1$var78 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 79.
				// 
				// Looking for a path between Sample 58 and consumer Categorical 79.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double[] cv$temp$1$var78 = m[0];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 83 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var73" with its value "1".
				cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < cv$temp$1$var78.length))?Math.log(cv$temp$1$var78[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
				if((0 == (indirection[(i$var73 - 1)][i$var73] / i$var73))) {
					int index$i$4_4 = ((indirection[(i$var73 - 1)][i$var73] / i$var73) + 1);
					if(((1 <= index$i$4_4) && (index$i$4_4 < samples))) {
						// Processing sample task 83 of consumer random variable null.
						// Variable declaration of cv$temp$2$var78 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						double[] cv$temp$2$var78 = m[0];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 83 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						cv$accumulatedProbabilities = ((((0.0 <= st[index$i$4_4]) && (st[index$i$4_4] < cv$temp$2$var78.length))?Math.log(cv$temp$2$var78[st[index$i$4_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples))
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 123 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "j" with its value "0".
				// 
				// cv$temp$3$var118's comment
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 119.
				// 
				// Looking for a path between Sample 58 and consumer Bernoulli 119.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0], bias[0]) + cv$accumulatedProbabilities);
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
				if((0 == (indirection[(i$var73 - 1)][i$var73] / i$var73))) {
					int j = (indirection[(i$var73 - 1)][i$var73] / i$var73);
					if(((0 <= j) && (j < samples)))
						// Processing sample task 123 of consumer random variable null.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 123 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// cv$temp$4$var118's comment
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[j], bias[0]) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var55$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		st[0] = 1;
		
		// Guards to ensure that st2 is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 58 and consumer int[] 104.
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if((0 == (indirection[(i$var73 - 1)][i$var73] / i$var73)))
				st2[(indirection[(i$var73 - 1)][i$var73] / i$var73)] = (samples - st[(indirection[(i$var73 - 1)][i$var73] / i$var73)]);
		}
		
		// Variable declaration of cv$temp$0$var53 moved.
		// 
		// Constructing a random variable input for use later.
		double[] cv$temp$0$var53 = m[0];
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = ((1 < cv$temp$0$var53.length)?Math.log(cv$temp$0$var53[1]):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 < samples)) {
			// Processing sample task 83 of consumer random variable null.
			// 
			// Variable declaration of cv$temp$1$var78 moved.
			// 
			// Constructing a random variable input for use later.
			// 
			// Processing random variable 79.
			// 
			// Looking for a path between Sample 58 and consumer Categorical 79.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double[] cv$temp$1$var78 = m[1];
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 83 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "i$var73" with its value "1".
			cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < cv$temp$1$var78.length))?Math.log(cv$temp$1$var78[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if((0 == (indirection[(i$var73 - 1)][i$var73] / i$var73))) {
				int index$i$4_4 = ((indirection[(i$var73 - 1)][i$var73] / i$var73) + 1);
				if(((1 <= index$i$4_4) && (index$i$4_4 < samples))) {
					// Processing sample task 83 of consumer random variable null.
					// Variable declaration of cv$temp$2$var78 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					double[] cv$temp$2$var78 = m[1];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 83 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((((0.0 <= st[index$i$4_4]) && (st[index$i$4_4] < cv$temp$2$var78.length))?Math.log(cv$temp$2$var78[st[index$i$4_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < samples))
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 123 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "j" with its value "0".
			// 
			// cv$temp$3$var118's comment
			// Constructing a random variable input for use later.
			// 
			// Processing random variable 119.
			// 
			// Looking for a path between Sample 58 and consumer Bernoulli 119.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0], bias[1]) + cv$accumulatedProbabilities);
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if((0 == (indirection[(i$var73 - 1)][i$var73] / i$var73))) {
				int j = (indirection[(i$var73 - 1)][i$var73] / i$var73);
				if(((0 <= j) && (j < samples)))
					// Processing sample task 123 of consumer random variable null.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 123 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$4$var118's comment
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[j], bias[1]) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var55$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		
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
		
		// Unrolled loop
		// 
		// Get a local reference to the scratch space.
		double cv$lseElementValue = cv$var55$stateProbabilityGlobal[1];
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
			cv$logSum = (Math.log((Math.exp((cv$var55$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var55$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Unrolled loop
			// Get a local reference to the scratch space.
			cv$var55$stateProbabilityGlobal[0] = 0.5;
			
			// Get a local reference to the scratch space.
			cv$var55$stateProbabilityGlobal[1] = 0.5;
		} else {
			// Unrolled loop
			// Get a local reference to the scratch space.
			cv$var55$stateProbabilityGlobal[0] = Math.exp((cv$var55$stateProbabilityGlobal[0] - cv$logSum));
			
			// Get a local reference to the scratch space.
			cv$var55$stateProbabilityGlobal[1] = Math.exp((cv$var55$stateProbabilityGlobal[1] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = 2; cv$indexName < cv$var55$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var55$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Get a local reference to the scratch space.
		st[0] = DistributionSampling.sampleCategorical(RNG$, cv$var55$stateProbabilityGlobal);
		
		// Guards to ensure that st2 is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 58 and consumer int[] 63.
		st2[0] = (samples - st[0]);
		
		// Guards to ensure that st2 is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 58 and consumer int[] 104.
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if((0 == (indirection[(i$var73 - 1)][i$var73] / i$var73)))
				st2[(indirection[(i$var73 - 1)][i$var73] / i$var73)] = (samples - st[(indirection[(i$var73 - 1)][i$var73] / i$var73)]);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 83 drawn from Categorical 79. Inference was performed using variable
	// marginalization.
	private final void sample83(int i$var73) {
		// Unrolled loop
		{
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			st[i$var73] = 0;
			
			// Guards to ensure that st2 is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 83 and consumer int[] 104.
			for(int index$i$1_1 = 1; index$i$1_1 < samples; index$i$1_1 += 1) {
				if((i$var73 == (indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)))
					st2[(indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)] = (samples - st[(indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)]);
			}
			
			// Variable declaration of cv$temp$0$var78 moved.
			// 
			// Constructing a random variable input for use later.
			double[] cv$temp$0$var78 = m[(samples - st2[(i$var73 - 1)])];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = ((0 < cv$temp$0$var78.length)?Math.log(cv$temp$0$var78[0]):Double.NEGATIVE_INFINITY);
			for(int index$i$2_2 = 1; index$i$2_2 < samples; index$i$2_2 += 1) {
				if((i$var73 == (indirection[(index$i$2_2 - 1)][index$i$2_2] / index$i$2_2))) {
					int index$i$2_4 = ((indirection[(index$i$2_2 - 1)][index$i$2_2] / index$i$2_2) + 1);
					if(((1 <= index$i$2_4) && (index$i$2_4 < samples))) {
						// Processing sample task 83 of consumer random variable null.
						// Variable declaration of cv$temp$1$var78 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Processing random variable 79.
						// 
						// Looking for a path between Sample 83 and consumer Categorical 79.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						double[] cv$temp$1$var78 = m[0];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 83 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						cv$accumulatedProbabilities = ((((0.0 <= st[index$i$2_4]) && (st[index$i$2_4] < cv$temp$1$var78.length))?Math.log(cv$temp$1$var78[st[index$i$2_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int index$i$5_2 = 1; index$i$5_2 < samples; index$i$5_2 += 1) {
				if((i$var73 == (indirection[(index$i$5_2 - 1)][index$i$5_2] / index$i$5_2))) {
					int j = (indirection[(index$i$5_2 - 1)][index$i$5_2] / index$i$5_2);
					if(((0 <= j) && (j < samples)))
						// Processing sample task 123 of consumer random variable null.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 123 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// cv$temp$2$var118's comment
						// Constructing a random variable input for use later.
						// 
						// Processing random variable 119.
						// 
						// Looking for a path between Sample 83 and consumer Bernoulli 119.
						// 
						// Value of the variable at this index
						// 
						// Substituted "cv$valuePos" with its value "0".
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[j], bias[0]) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var80$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		st[i$var73] = 1;
		
		// Guards to ensure that st2 is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 83 and consumer int[] 104.
		for(int index$i$1_1 = 1; index$i$1_1 < samples; index$i$1_1 += 1) {
			if((i$var73 == (indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)))
				st2[(indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)] = (samples - st[(indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)]);
		}
		
		// Variable declaration of cv$temp$0$var78 moved.
		// 
		// Constructing a random variable input for use later.
		double[] cv$temp$0$var78 = m[(samples - st2[(i$var73 - 1)])];
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = ((1 < cv$temp$0$var78.length)?Math.log(cv$temp$0$var78[1]):Double.NEGATIVE_INFINITY);
		for(int index$i$2_2 = 1; index$i$2_2 < samples; index$i$2_2 += 1) {
			if((i$var73 == (indirection[(index$i$2_2 - 1)][index$i$2_2] / index$i$2_2))) {
				int index$i$2_4 = ((indirection[(index$i$2_2 - 1)][index$i$2_2] / index$i$2_2) + 1);
				if(((1 <= index$i$2_4) && (index$i$2_4 < samples))) {
					// Processing sample task 83 of consumer random variable null.
					// Variable declaration of cv$temp$1$var78 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Processing random variable 79.
					// 
					// Looking for a path between Sample 83 and consumer Categorical 79.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					double[] cv$temp$1$var78 = m[1];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 83 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((((0.0 <= st[index$i$2_4]) && (st[index$i$2_4] < cv$temp$1$var78.length))?Math.log(cv$temp$1$var78[st[index$i$2_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int index$i$5_2 = 1; index$i$5_2 < samples; index$i$5_2 += 1) {
			if((i$var73 == (indirection[(index$i$5_2 - 1)][index$i$5_2] / index$i$5_2))) {
				int j = (indirection[(index$i$5_2 - 1)][index$i$5_2] / index$i$5_2);
				if(((0 <= j) && (j < samples)))
					// Processing sample task 123 of consumer random variable null.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 123 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$2$var118's comment
					// Constructing a random variable input for use later.
					// 
					// Processing random variable 119.
					// 
					// Looking for a path between Sample 83 and consumer Bernoulli 119.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$valuePos" with its value "1".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[j], bias[1]) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var80$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var80$stateProbabilityGlobal[0];
		
		// Unrolled loop
		// 
		// Get a local reference to the scratch space.
		double cv$lseElementValue = cv$var80$stateProbabilityGlobal[1];
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
			cv$logSum = (Math.log((Math.exp((cv$var80$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var80$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Unrolled loop
			// Get a local reference to the scratch space.
			cv$var80$stateProbabilityGlobal[0] = 0.5;
			
			// Get a local reference to the scratch space.
			cv$var80$stateProbabilityGlobal[1] = 0.5;
		} else {
			// Unrolled loop
			// Get a local reference to the scratch space.
			cv$var80$stateProbabilityGlobal[0] = Math.exp((cv$var80$stateProbabilityGlobal[0] - cv$logSum));
			
			// Get a local reference to the scratch space.
			cv$var80$stateProbabilityGlobal[1] = Math.exp((cv$var80$stateProbabilityGlobal[1] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = 2; cv$indexName < cv$var80$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var80$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Get a local reference to the scratch space.
		st[i$var73] = DistributionSampling.sampleCategorical(RNG$, cv$var80$stateProbabilityGlobal);
		
		// Guards to ensure that st2 is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 83 and consumer int[] 104.
		for(int index$i$8_1 = 1; index$i$8_1 < samples; index$i$8_1 += 1) {
			if((i$var73 == (indirection[(index$i$8_1 - 1)][index$i$8_1] / index$i$8_1)))
				st2[(indirection[(index$i$8_1 - 1)][index$i$8_1] / index$i$8_1)] = (samples - st[(indirection[(index$i$8_1 - 1)][index$i$8_1] / index$i$8_1)]);
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Allocation of cv$var30$countGlobal for multithreaded execution
		// 
		// Get the thread count.
		int cv$threadCount = threadCount();
		
		// Allocate an array to hold a copy per thread
		cv$var30$countGlobal = new double[cv$threadCount][];
		
		// Populate the array with a copy per thread
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var30$countGlobal[cv$index] = new double[2];
		
		// Constructor for cv$var55$stateProbabilityGlobal
		// 
		// Allocation of cv$var55$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 56. Initially set to the value
		// of putTask 32.
		cv$var55$stateProbabilityGlobal = new double[2];
		
		// Allocation of cv$var80$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 81. Initially set to the value
		// of putTask 32.
		cv$var80$stateProbabilityGlobal = new double[2];
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
		if(!setFlag$st)
			// Constructor for st
			st = new int[length$flipsMeasured];
		
		// Constructor for st2
		st2 = new int[length$flipsMeasured];
		
		// Constructor for indirection
		indirection = new int[(length$flipsMeasured - 1)][];
		for(int i$var73 = 1; i$var73 < length$flipsMeasured; i$var73 += 1)
			indirection[(i$var73 - 1)] = new int[(i$var73 + 1)];
		
		// If flips has not been set already allocate space.
		if(!setFlag$flips)
			// Constructor for flips
			flips = new boolean[length$flipsMeasured];
		
		// Constructor for logProbability$var79
		logProbability$var79 = new double[(length$flipsMeasured - 1)];
		
		// Constructor for logProbability$sample83
		logProbability$sample83 = new double[(length$flipsMeasured - 1)];
		
		// Constructor for logProbability$var119
		logProbability$var119 = new double[length$flipsMeasured];
		
		// Constructor for logProbability$sample123
		logProbability$sample123 = new double[length$flipsMeasured];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample31)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var29]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample48)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample58) {
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
			st2[0] = (samples - st[0]);
		}
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if(!fixedFlag$sample83)
				st[i$var73] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var73 - 1)])]);
			if((!fixedFlag$sample58 || !fixedFlag$sample83))
				st2[(indirection[(i$var73 - 1)][i$var73] / i$var73)] = (samples - st[(indirection[(i$var73 - 1)][i$var73] / i$var73)]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample123)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j = forStart$j; j < forEnd$j; j += 1)
							flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[(samples - st2[j])]);
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample31)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var29]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample48)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample58) {
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
			st2[0] = (samples - st[0]);
		}
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if(!fixedFlag$sample83)
				st[i$var73] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var73 - 1)])]);
			if((!fixedFlag$sample58 || !fixedFlag$sample83))
				st2[(indirection[(i$var73 - 1)][i$var73] / i$var73)] = (samples - st[(indirection[(i$var73 - 1)][i$var73] / i$var73)]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample31)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var29]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample48)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample58) {
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
			st2[0] = (samples - st[0]);
		}
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if(!fixedFlag$sample83)
				st[i$var73] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var73 - 1)])]);
			if((!fixedFlag$sample58 || !fixedFlag$sample83))
				st2[(indirection[(i$var73 - 1)][i$var73] / i$var73)] = (samples - st[(indirection[(i$var73 - 1)][i$var73] / i$var73)]);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample31)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
								sample31(var29, threadID$var29, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample48)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
								sample48(var45, threadID$var45, RNG$1);
					}
				);

			if(!fixedFlag$sample58)
				sample58();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample83) {
				for(int i$var73 = 1; i$var73 < samples; i$var73 += 1)
					sample83(i$var73);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample83) {
				for(int i$var73 = (samples - 1); i$var73 >= 1; i$var73 -= 1)
					sample83(i$var73);
			}
			if(!fixedFlag$sample58)
				sample58();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample48)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
								sample48(var45, threadID$var45, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample31)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
								sample31(var29, threadID$var29, RNG$1);
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
			(int forStart$i$var15, int forEnd$i$var15, int threadID$i$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var15 = forStart$i$var15; i$var15 < forEnd$i$var15; i$var15 += 1)
						v[i$var15] = 0.1;
			}
		);
		samples = length$flipsMeasured;
		for(int i$var73 = 1; i$var73 < length$flipsMeasured; i$var73 += 1) {
			// Alternative name for i$var73 to make it effectively final.
			int i$var73$1 = i$var73;
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, (i$var73$1 + 1), 1,
				(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int k = forStart$k; k < forEnd$k; k += 1)
							indirection[(i$var73$1 - 1)][k] = (k * i$var73$1);
				}
			);
		}
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
		logProbability$var54 = 0.0;
		logProbability$st2 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample58)
			logProbability$var55 = 0.0;
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1)
			logProbability$var79[(i$var73 - 1)] = 0.0;
		if(!fixedProbFlag$sample83) {
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1)
				logProbability$sample83[(i$var73 - 1)] = 0.0;
		}
		for(int j = 0; j < samples; j += 1)
			logProbability$var119[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample123) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample123[j] = 0.0;
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
		if(fixedFlag$sample58)
			logProbabilityValue$sample58();
		if(fixedFlag$sample83)
			logProbabilityValue$sample83();
		logProbabilityValue$sample123();
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
		logProbabilityValue$sample58();
		logProbabilityValue$sample83();
		logProbabilityValue$sample123();
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
		logProbabilityValue$sample58();
		logProbabilityValue$sample83();
		logProbabilityValue$sample123();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample31)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var29]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample48)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample58) {
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
			st2[0] = (samples - st[0]);
		}
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if(!fixedFlag$sample83)
				st[i$var73] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var73 - 1)])]);
			if((!fixedFlag$sample58 || !fixedFlag$sample83))
				st2[(indirection[(i$var73 - 1)][i$var73] / i$var73)] = (samples - st[(indirection[(i$var73 - 1)][i$var73] / i$var73)]);
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
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = flipsMeasured[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(setFlag$st) {
			st2[0] = (samples - st[0]);
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1)
				st2[(indirection[(i$var73 - 1)][i$var73] / i$var73)] = (samples - st[(indirection[(i$var73 - 1)][i$var73] / i$var73)]);
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart3d(boolean[] flipsMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n\n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        int[] st = new int[samples];\n        int[] st2 = new int[samples];\n\n        st[0] = categorical(m[0]).sample();\n        st2[0] = samples - st[0];\n\n        for(int i:[1..samples)) {\n            st[i] = categorical(m[samples - st2[i-1]]).sample();\n            \n            int[] indirection = new int[i+1];\n            for(int k:[0..i])\n                indirection[k] = k*i; \n                \n            st2[indirection[i]/i] = samples - st[indirection[i]/i];\n        }\n            \n        boolean[] flips = new boolean[samples];\n            \n        for(int j:[0..samples))\n            flips[j] = bernoulli(bias[samples - st2[j]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
	}
}