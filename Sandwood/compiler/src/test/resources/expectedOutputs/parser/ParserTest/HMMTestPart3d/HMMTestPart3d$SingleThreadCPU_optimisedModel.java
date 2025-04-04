package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart3d$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMTestPart3d$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private double[] cv$var16$countGlobal;
	private double[] cv$var34$stateProbabilityGlobal;
	private double[] cv$var50$stateProbabilityGlobal;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample27 = false;
	private boolean fixedFlag$sample37 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedFlag$sample80 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample27 = false;
	private boolean fixedProbFlag$sample37 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean fixedProbFlag$sample80 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int[][] indirection;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample53;
	private double[] logProbability$sample80;
	private double logProbability$st;
	private double logProbability$st2;
	private double logProbability$var11;
	private double logProbability$var16;
	private double logProbability$var20;
	private double logProbability$var25;
	private double logProbability$var33;
	private double logProbability$var34;
	private double[] logProbability$var49;
	private double[] logProbability$var76;
	private double[][] m;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private boolean setFlag$st2 = false;
	private int[] st;
	private int[] st2;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart3d$SingleThreadCPU(ExecutionTarget target) {
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
		// 
		// Substituted "fixedFlag$sample17" with its value "cv$value".
		fixedProbFlag$sample17 = (cv$value && fixedProbFlag$sample17);
		
		// Should the probability of sample 37 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample17" with its value "cv$value".
		fixedProbFlag$sample37 = (cv$value && fixedProbFlag$sample37);
		
		// Should the probability of sample 53 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample17" with its value "cv$value".
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
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
		// 
		// Substituted "fixedFlag$sample27" with its value "cv$value".
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
		
		// Should the probability of sample 80 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample27" with its value "cv$value".
		fixedProbFlag$sample80 = (cv$value && fixedProbFlag$sample80);
	}

	// Getter for fixedFlag$sample37.
	@Override
	public final boolean get$fixedFlag$sample37() {
		return fixedFlag$sample37;
	}

	// Setter for fixedFlag$sample37.
	@Override
	public final void set$fixedFlag$sample37(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample37 including if probabilities
		// need to be updated.
		fixedFlag$sample37 = cv$value;
		
		// Should the probability of sample 37 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample37" with its value "cv$value".
		fixedProbFlag$sample37 = (cv$value && fixedProbFlag$sample37);
		
		// Should the probability of sample 53 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample37" with its value "cv$value".
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
		
		// Should the probability of sample 80 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample37" with its value "cv$value".
		fixedProbFlag$sample80 = (cv$value && fixedProbFlag$sample80);
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
		
		// Should the probability of sample 80 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample53" with its value "cv$value".
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
	}

	// Getter for flips.
	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	// Setter for flips.
	@Override
	public final void set$flips(boolean[] cv$value) {
		// Set flips with flag to mark that it has been set so another array doesn't need
		// to be constructed
		flips = cv$value;
		setFlag$flips = true;
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
	public final int[] get$st() {
		return st;
	}

	// Setter for st.
	@Override
	public final void set$st(int[] cv$value) {
		// Set st with flag to mark that it has been set so another array doesn't need to
		// be constructed
		st = cv$value;
		setFlag$st = true;
	}

	// Getter for st2.
	@Override
	public final int[] get$st2() {
		return st2;
	}

	// Setter for st2.
	@Override
	public final void set$st2(int[] cv$value) {
		// Set st2 with flag to mark that it has been set so another array doesn't need to
		// be constructed
		st2 = cv$value;
		setFlag$st2 = true;
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

	// Calculate the probability of the samples represented by sample17 using sampled
	// values.
	private final void logProbabilityValue$sample17() {
		// Determine if we need to calculate the values for sample task 17 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample17) {
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
			logProbability$var11 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var16 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample17)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample17 = fixedFlag$sample17;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var11 = logProbability$var16;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var16);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var16);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample17)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var16);
		}
	}

	// Calculate the probability of the samples represented by sample27 using sampled
	// values.
	private final void logProbabilityValue$sample27() {
		// Determine if we need to calculate the values for sample task 27 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample27) {
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
			logProbability$var20 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var25 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample27)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample27 = fixedFlag$sample27;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var20 = logProbability$var25;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$bias = (logProbability$bias + logProbability$var25);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var25);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample27)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var25);
		}
	}

	// Calculate the probability of the samples represented by sample37 using sampled
	// values.
	private final void logProbabilityValue$sample37() {
		// Determine if we need to calculate the values for sample task 37 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample37) {
			// Generating probabilities for sample task
			// The sample value to calculate the probability of generating
			int cv$sampleValue = st[0];
			double[] var32 = m[0];
			
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
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var32.length))?Math.log(var32[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var33 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var34 = cv$distributionAccumulator;
			
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
			
			// Looking for a path between Sample 37 and consumer int[] 68.
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
				if(((0 == (indirection[(i$var43 - 1)][i$var43] / i$var43)) && !cv$guard$st2)) {
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
			if(fixedFlag$sample37)
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
			fixedProbFlag$sample37 = (fixedFlag$sample37 && fixedFlag$sample17);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var33 = logProbability$var34;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var34);
			
			// Variable declaration of cv$guard$st2 moved.
			// Declaration comment was:
			// Guard to ensure that st2 is only updated once for this probability.
			// 
			// Set the guard so the update is only applied once.
			boolean cv$guard$st2 = true;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st2 = (logProbability$st2 + logProbability$var34);
			
			// Looking for a path between Sample 37 and consumer int[] 68.
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
				if(((0 == (indirection[(i$var43 - 1)][i$var43] / i$var43)) && !cv$guard$st2)) {
					// Set the guard so the update is only applied once.
					cv$guard$st2 = true;
					
					// Update the variable probability
					// 
					// Variable declaration of cv$accumulator moved.
					logProbability$st2 = (logProbability$st2 + logProbability$var34);
				}
			}
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var34);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample37)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var34);
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
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = st[i$var43];
				double[] var48 = m[(samples - st2[(i$var43 - 1)])];
				
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
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var48.length))?Math.log(var48[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
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
				logProbability$var49[(i$var43 - 1)] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample53[(i$var43 - 1)] = cv$distributionAccumulator;
				
				// Guard to ensure that st2 is only updated once for this probability.
				boolean cv$guard$st2 = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 53 and consumer int[] 68.
				for(int index$i$2_1 = 1; index$i$2_1 < samples; index$i$2_1 += 1) {
					if(((i$var43 == (indirection[(index$i$2_1 - 1)][index$i$2_1] / index$i$2_1)) && !cv$guard$st2)) {
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
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample53 = ((fixedFlag$sample53 && fixedFlag$sample17) && fixedFlag$sample37);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
				double cv$sampleValue = logProbability$sample53[(i$var43 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var49[(i$var43 - 1)] = cv$sampleValue;
				
				// Guard to ensure that st2 is only updated once for this probability.
				boolean cv$guard$st2 = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 53 and consumer int[] 68.
				for(int index$i$3_1 = 1; index$i$3_1 < samples; index$i$3_1 += 1) {
					if(((i$var43 == (indirection[(index$i$3_1 - 1)][index$i$3_1] / index$i$3_1)) && !cv$guard$st2)) {
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
			if(fixedFlag$sample53)
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
				logProbability$var76[j] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample80[j] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample80 = (((fixedFlag$sample80 && fixedFlag$sample27) && fixedFlag$sample37) && fixedFlag$sample53);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample80[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var76[j] = cv$rvAccumulator;
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
	private final void sample17(int var15) {
		// A local reference to the scratch space.
		cv$var16$countGlobal[0] = 0.0;
		
		// A local reference to the scratch space.
		cv$var16$countGlobal[1] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var15 == 0))
			// Looking for a path between Sample 17 and consumer Categorical 33.
			// 
			// Processing sample task 37 of consumer random variable null.
			// 
			// Increment the sample counter with the value sampled by sample task 37 of random
			// variable var33
			// 
			// A local reference to the scratch space.
			cv$var16$countGlobal[st[0]] = (cv$var16$countGlobal[st[0]] + 1.0);
		
		// Processing random variable 49.
		// 
		// Looking for a path between Sample 17 and consumer Categorical 49.
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if((var15 == (samples - st2[(i$var43 - 1)])))
				// Processing sample task 53 of consumer random variable null.
				// 
				// Increment the sample counter with the value sampled by sample task 53 of random
				// variable var49
				// 
				// A local reference to the scratch space.
				cv$var16$countGlobal[st[i$var43]] = (cv$var16$countGlobal[st[i$var43]] + 1.0);
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var16$countGlobal, m[var15]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 27 drawn from Beta 20. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample27(int var24) {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		
		// Processing random variable 76.
		// 
		// Looking for a path between Sample 27 and consumer Bernoulli 76.
		for(int j = 0; j < samples; j += 1) {
			if((var24 == (samples - st2[j]))) {
				// Processing sample task 80 of consumer random variable null.
				// 
				// Include the value sampled by task 80 from random variable var76.
				// Increment the number of samples.
				cv$count = (cv$count + 1);
				
				// If the sample value was positive increase the count
				if(flips[j])
					cv$sum = (cv$sum + 1);
			}
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		bias[var24] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 37 drawn from Categorical 33. Inference was performed using variable
	// marginalization.
	private final void sample37() {
		{
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			st[0] = 0;
			
			// Guards to ensure that st2 is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 37 and consumer int[] 68.
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
				if((0 == (indirection[(i$var43 - 1)][i$var43] / i$var43)))
					st2[(indirection[(i$var43 - 1)][i$var43] / i$var43)] = (samples - st[(indirection[(i$var43 - 1)][i$var43] / i$var43)]);
			}
			
			// Variable declaration of cv$temp$0$var32 moved.
			// 
			// Constructing a random variable input for use later.
			double[] cv$temp$0$var32 = m[0];
			
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
			double cv$accumulatedProbabilities = ((0 < cv$temp$0$var32.length)?Math.log(cv$temp$0$var32[0]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 < samples)) {
				// Processing sample task 53 of consumer random variable null.
				// 
				// Variable declaration of cv$temp$1$var48 moved.
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
				double[] cv$temp$1$var48 = m[0];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 53 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var43" with its value "1".
				cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < cv$temp$1$var48.length))?Math.log(cv$temp$1$var48[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
				if((0 == (indirection[(i$var43 - 1)][i$var43] / i$var43))) {
					int index$i$4_4 = ((indirection[(i$var43 - 1)][i$var43] / i$var43) + 1);
					if(((1 <= index$i$4_4) && (index$i$4_4 < samples))) {
						// Processing sample task 53 of consumer random variable null.
						// Variable declaration of cv$temp$2$var48 moved.
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
						double[] cv$temp$2$var48 = m[0];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 53 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						cv$accumulatedProbabilities = ((((0.0 <= st[index$i$4_4]) && (st[index$i$4_4] < cv$temp$2$var48.length))?Math.log(cv$temp$2$var48[st[index$i$4_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < samples))
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 80 with the current configuration.
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
				// cv$temp$3$var75's comment
				// Constructing a random variable input for use later.
				// 
				// Variable declaration of cv$currentValue moved.
				// Declaration comment was:
				// The value currently being tested
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0], bias[0]) + cv$accumulatedProbabilities);
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
				if((0 == (indirection[(i$var43 - 1)][i$var43] / i$var43))) {
					int j = (indirection[(i$var43 - 1)][i$var43] / i$var43);
					if(((0 <= j) && (j < samples)))
						// Processing sample task 80 of consumer random variable null.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 80 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// cv$temp$4$var75's comment
						// Constructing a random variable input for use later.
						// 
						// Variable declaration of cv$currentValue moved.
						// Declaration comment was:
						// The value currently being tested
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
			cv$var34$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		st[0] = 1;
		
		// Guards to ensure that st2 is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 37 and consumer int[] 68.
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if((0 == (indirection[(i$var43 - 1)][i$var43] / i$var43)))
				st2[(indirection[(i$var43 - 1)][i$var43] / i$var43)] = (samples - st[(indirection[(i$var43 - 1)][i$var43] / i$var43)]);
		}
		
		// Variable declaration of cv$temp$0$var32 moved.
		// 
		// Constructing a random variable input for use later.
		double[] cv$temp$0$var32 = m[0];
		
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
		double cv$accumulatedProbabilities = ((1 < cv$temp$0$var32.length)?Math.log(cv$temp$0$var32[1]):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 < samples)) {
			// Processing sample task 53 of consumer random variable null.
			// 
			// Variable declaration of cv$temp$1$var48 moved.
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
			double[] cv$temp$1$var48 = m[1];
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 53 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "i$var43" with its value "1".
			cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < cv$temp$1$var48.length))?Math.log(cv$temp$1$var48[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if((0 == (indirection[(i$var43 - 1)][i$var43] / i$var43))) {
				int index$i$4_4 = ((indirection[(i$var43 - 1)][i$var43] / i$var43) + 1);
				if(((1 <= index$i$4_4) && (index$i$4_4 < samples))) {
					// Processing sample task 53 of consumer random variable null.
					// Variable declaration of cv$temp$2$var48 moved.
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
					double[] cv$temp$2$var48 = m[1];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 53 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((((0.0 <= st[index$i$4_4]) && (st[index$i$4_4] < cv$temp$2$var48.length))?Math.log(cv$temp$2$var48[st[index$i$4_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < samples))
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 80 with the current configuration.
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
			// cv$temp$3$var75's comment
			// Constructing a random variable input for use later.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0], bias[1]) + cv$accumulatedProbabilities);
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if((0 == (indirection[(i$var43 - 1)][i$var43] / i$var43))) {
				int j = (indirection[(i$var43 - 1)][i$var43] / i$var43);
				if(((0 <= j) && (j < samples)))
					// Processing sample task 80 of consumer random variable null.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 80 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$4$var75's comment
					// Constructing a random variable input for use later.
					// 
					// Variable declaration of cv$currentValue moved.
					// Declaration comment was:
					// The value currently being tested
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
		cv$var34$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var34$stateProbabilityGlobal[0];
		
		// Find max value.
		// 
		// Get a local reference to the scratch space.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var34$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var34$stateProbabilityGlobal[cv$lseIndex];
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
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var34$stateProbabilityGlobal.length; cv$lseIndex += 1)
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var34$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
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
			for(int cv$indexName = 0; cv$indexName < cv$var34$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var34$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$var34$stateProbabilityGlobal.length);
		} else {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var34$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var34$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var34$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Get a local reference to the scratch space.
		st[0] = DistributionSampling.sampleCategorical(RNG$, cv$var34$stateProbabilityGlobal);
		
		// Guards to ensure that st2 is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 37 and consumer int[] 40.
		st2[0] = (samples - st[0]);
		
		// Guards to ensure that st2 is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 37 and consumer int[] 68.
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if((0 == (indirection[(i$var43 - 1)][i$var43] / i$var43)))
				st2[(indirection[(i$var43 - 1)][i$var43] / i$var43)] = (samples - st[(indirection[(i$var43 - 1)][i$var43] / i$var43)]);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 53 drawn from Categorical 49. Inference was performed using variable
	// marginalization.
	private final void sample53(int i$var43) {
		{
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			st[i$var43] = 0;
			
			// Guards to ensure that st2 is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 53 and consumer int[] 68.
			for(int index$i$1_1 = 1; index$i$1_1 < samples; index$i$1_1 += 1) {
				if((i$var43 == (indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)))
					st2[(indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)] = (samples - st[(indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)]);
			}
			
			// Variable declaration of cv$temp$0$var48 moved.
			// 
			// Constructing a random variable input for use later.
			double[] cv$temp$0$var48 = m[(samples - st2[(i$var43 - 1)])];
			
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
			double cv$accumulatedProbabilities = ((0 < cv$temp$0$var48.length)?Math.log(cv$temp$0$var48[0]):Double.NEGATIVE_INFINITY);
			for(int index$i$2_2 = 1; index$i$2_2 < samples; index$i$2_2 += 1) {
				if((i$var43 == (indirection[(index$i$2_2 - 1)][index$i$2_2] / index$i$2_2))) {
					int index$i$2_4 = ((indirection[(index$i$2_2 - 1)][index$i$2_2] / index$i$2_2) + 1);
					if(((1 <= index$i$2_4) && (index$i$2_4 < samples))) {
						// Processing sample task 53 of consumer random variable null.
						// Variable declaration of cv$temp$1$var48 moved.
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
						double[] cv$temp$1$var48 = m[0];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 53 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						cv$accumulatedProbabilities = ((((0.0 <= st[index$i$2_4]) && (st[index$i$2_4] < cv$temp$1$var48.length))?Math.log(cv$temp$1$var48[st[index$i$2_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int index$i$5_2 = 1; index$i$5_2 < samples; index$i$5_2 += 1) {
				if((i$var43 == (indirection[(index$i$5_2 - 1)][index$i$5_2] / index$i$5_2))) {
					int j = (indirection[(index$i$5_2 - 1)][index$i$5_2] / index$i$5_2);
					if(((0 <= j) && (j < samples)))
						// Processing sample task 80 of consumer random variable null.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 80 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// cv$temp$2$var75's comment
						// Constructing a random variable input for use later.
						// 
						// Variable declaration of cv$currentValue moved.
						// Declaration comment was:
						// The value currently being tested
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
			cv$var50$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		st[i$var43] = 1;
		
		// Guards to ensure that st2 is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 53 and consumer int[] 68.
		for(int index$i$1_1 = 1; index$i$1_1 < samples; index$i$1_1 += 1) {
			if((i$var43 == (indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)))
				st2[(indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)] = (samples - st[(indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)]);
		}
		
		// Variable declaration of cv$temp$0$var48 moved.
		// 
		// Constructing a random variable input for use later.
		double[] cv$temp$0$var48 = m[(samples - st2[(i$var43 - 1)])];
		
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
		double cv$accumulatedProbabilities = ((1 < cv$temp$0$var48.length)?Math.log(cv$temp$0$var48[1]):Double.NEGATIVE_INFINITY);
		for(int index$i$2_2 = 1; index$i$2_2 < samples; index$i$2_2 += 1) {
			if((i$var43 == (indirection[(index$i$2_2 - 1)][index$i$2_2] / index$i$2_2))) {
				int index$i$2_4 = ((indirection[(index$i$2_2 - 1)][index$i$2_2] / index$i$2_2) + 1);
				if(((1 <= index$i$2_4) && (index$i$2_4 < samples))) {
					// Processing sample task 53 of consumer random variable null.
					// Variable declaration of cv$temp$1$var48 moved.
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
					double[] cv$temp$1$var48 = m[1];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 53 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = ((((0.0 <= st[index$i$2_4]) && (st[index$i$2_4] < cv$temp$1$var48.length))?Math.log(cv$temp$1$var48[st[index$i$2_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int index$i$5_2 = 1; index$i$5_2 < samples; index$i$5_2 += 1) {
			if((i$var43 == (indirection[(index$i$5_2 - 1)][index$i$5_2] / index$i$5_2))) {
				int j = (indirection[(index$i$5_2 - 1)][index$i$5_2] / index$i$5_2);
				if(((0 <= j) && (j < samples)))
					// Processing sample task 80 of consumer random variable null.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 80 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$2$var75's comment
					// Constructing a random variable input for use later.
					// 
					// Variable declaration of cv$currentValue moved.
					// Declaration comment was:
					// The value currently being tested
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
		cv$var50$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var50$stateProbabilityGlobal[0];
		
		// Find max value.
		// 
		// Get a local reference to the scratch space.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var50$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var50$stateProbabilityGlobal[cv$lseIndex];
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
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var50$stateProbabilityGlobal.length; cv$lseIndex += 1)
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var50$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
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
			for(int cv$indexName = 0; cv$indexName < cv$var50$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var50$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$var50$stateProbabilityGlobal.length);
		} else {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var50$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var50$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var50$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Get a local reference to the scratch space.
		st[i$var43] = DistributionSampling.sampleCategorical(RNG$, cv$var50$stateProbabilityGlobal);
		
		// Guards to ensure that st2 is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 53 and consumer int[] 68.
		for(int index$i$8_1 = 1; index$i$8_1 < samples; index$i$8_1 += 1) {
			if((i$var43 == (indirection[(index$i$8_1 - 1)][index$i$8_1] / index$i$8_1)))
				st2[(indirection[(index$i$8_1 - 1)][index$i$8_1] / index$i$8_1)] = (samples - st[(indirection[(index$i$8_1 - 1)][index$i$8_1] / index$i$8_1)]);
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Allocation of cv$var16$countGlobal for single threaded execution
		cv$var16$countGlobal = new double[2];
		
		// Constructor for cv$var34$stateProbabilityGlobal
		// 
		// Allocation of cv$var34$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 35. Initially set to the value
		// of putTask 18.
		cv$var34$stateProbabilityGlobal = new double[2];
		
		// Allocation of cv$var50$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 51. Initially set to the value
		// of putTask 18.
		cv$var50$stateProbabilityGlobal = new double[2];
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
		
		// If st2 has not been set already allocate space.
		if(!setFlag$st2)
			// Constructor for st2
			st2 = new int[length$flipsMeasured];
		
		// Constructor for indirection
		indirection = new int[(length$flipsMeasured - 1)][];
		for(int i$var43 = 1; i$var43 < length$flipsMeasured; i$var43 += 1)
			indirection[(i$var43 - 1)] = new int[(i$var43 + 1)];
		
		// If flips has not been set already allocate space.
		if(!setFlag$flips)
			// Constructor for flips
			flips = new boolean[length$flipsMeasured];
		
		// Constructor for logProbability$var49
		logProbability$var49 = new double[(length$flipsMeasured - 1)];
		
		// Constructor for logProbability$sample53
		logProbability$sample53 = new double[(length$flipsMeasured - 1)];
		
		// Constructor for logProbability$var76
		logProbability$var76 = new double[length$flipsMeasured];
		
		// Constructor for logProbability$sample80
		logProbability$sample80 = new double[length$flipsMeasured];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample17) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample27) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample37) {
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
			st2[0] = (samples - st[0]);
		}
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if(!fixedFlag$sample53)
				st[i$var43] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var43 - 1)])]);
			if((!fixedFlag$sample37 || !fixedFlag$sample53))
				st2[(indirection[(i$var43 - 1)][i$var43] / i$var43)] = (samples - st[(indirection[(i$var43 - 1)][i$var43] / i$var43)]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample80) {
			for(int j = 0; j < samples; j += 1)
				flips[j] = DistributionSampling.sampleBernoulli(RNG$, bias[(samples - st2[j])]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample17) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample27) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample37) {
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
			st2[0] = (samples - st[0]);
		}
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if(!fixedFlag$sample53)
				st[i$var43] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var43 - 1)])]);
			if((!fixedFlag$sample37 || !fixedFlag$sample53))
				st2[(indirection[(i$var43 - 1)][i$var43] / i$var43)] = (samples - st[(indirection[(i$var43 - 1)][i$var43] / i$var43)]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample17) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample27) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample37) {
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
			st2[0] = (samples - st[0]);
		}
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if(!fixedFlag$sample53)
				st[i$var43] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var43 - 1)])]);
			if((!fixedFlag$sample37 || !fixedFlag$sample53))
				st2[(indirection[(i$var43 - 1)][i$var43] / i$var43)] = (samples - st[(indirection[(i$var43 - 1)][i$var43] / i$var43)]);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample17) {
				sample17(0);
				sample17(1);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample27) {
				sample27(0);
				sample27(1);
			}
			if(!fixedFlag$sample37)
				sample37();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample53) {
				for(int i$var43 = 1; i$var43 < samples; i$var43 += 1)
					sample53(i$var43);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample53) {
				for(int i$var43 = (samples - 1); i$var43 >= 1; i$var43 -= 1)
					sample53(i$var43);
			}
			if(!fixedFlag$sample37)
				sample37();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample27) {
				sample27(1);
				sample27(0);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample17) {
				sample17(1);
				sample17(0);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		// Substituted "i$var8" with its value "0".
		v[0] = 0.1;
		
		// Substituted "i$var8" with its value "1".
		v[1] = 0.1;
		samples = length$flipsMeasured;
		for(int i$var43 = 1; i$var43 < length$flipsMeasured; i$var43 += 1) {
			for(int k = 0; k <= i$var43; k += 1)
				indirection[(i$var43 - 1)][k] = (k * i$var43);
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
		logProbability$var11 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$var16 = 0.0;
		logProbability$var20 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample27)
			logProbability$var25 = 0.0;
		logProbability$var33 = 0.0;
		logProbability$st = 0.0;
		logProbability$st2 = 0.0;
		if(!fixedProbFlag$sample37)
			logProbability$var34 = 0.0;
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1)
			logProbability$var49[(i$var43 - 1)] = 0.0;
		if(!fixedProbFlag$sample53) {
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1)
				logProbability$sample53[(i$var43 - 1)] = 0.0;
		}
		for(int j = 0; j < samples; j += 1)
			logProbability$var76[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample80) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample80[j] = 0.0;
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
		if(fixedFlag$sample37)
			logProbabilityValue$sample37();
		if(fixedFlag$sample53)
			logProbabilityValue$sample53();
		logProbabilityValue$sample80();
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
		logProbabilityValue$sample37();
		logProbabilityValue$sample53();
		logProbabilityValue$sample80();
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
		logProbabilityValue$sample37();
		logProbabilityValue$sample53();
		logProbabilityValue$sample80();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample17) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample27) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample37) {
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
			st2[0] = (samples - st[0]);
		}
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if(!fixedFlag$sample53)
				st[i$var43] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var43 - 1)])]);
			if((!fixedFlag$sample37 || !fixedFlag$sample53))
				st2[(indirection[(i$var43 - 1)][i$var43] / i$var43)] = (samples - st[(indirection[(i$var43 - 1)][i$var43] / i$var43)]);
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
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1)
				st2[(indirection[(i$var43 - 1)][i$var43] / i$var43)] = (samples - st[(indirection[(i$var43 - 1)][i$var43] / i$var43)]);
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart3d(boolean[] flipsMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n\n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        int[] st = new int[samples];\n        int[] st2 = new int[samples];\n\n        st[0] = categorical(m[0]).sample();\n        st2[0] = samples - st[0];\n\n        for(int i:[1..samples)) {\n            st[i] = categorical(m[samples - st2[i-1]]).sample();\n            \n            int[] indirection = new int[i+1];\n            for(int k:[0..i])\n                indirection[k] = k*i; \n                \n            st2[indirection[i]/i] = samples - st[indirection[i]/i];\n        }\n            \n        boolean[] flips = new boolean[samples];\n            \n        for(int j:[0..samples))\n            flips[j] = bernoulli(bias[samples - st2[j]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
	}
}