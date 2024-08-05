package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart6$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMTestPart6$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private double[] calculationVariable$var16$CountGlobal;
	private double[] calculationVariable$var33$StateProbabilityGlobal;
	private double[] calculationVariable$var50$StateProbabilityGlobal;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample26 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample52 = false;
	private boolean fixedFlag$sample70 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample52 = false;
	private boolean fixedProbFlag$sample70 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample52;
	private double[] logProbability$sample70;
	private double logProbability$st;
	private double logProbability$var11;
	private double logProbability$var16;
	private double logProbability$var20;
	private double logProbability$var25;
	private double logProbability$var32;
	private double logProbability$var33;
	private double[] logProbability$var49;
	private double[] logProbability$var67;
	private double[][] m;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart6$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for bias.
	@Override
	public final double[] get$bias() {
		return bias;
	}

	// Setter for bias.
	@Override
	public final void set$bias(double[] calculationVariable$value) {
		// Set bias with flag to mark that it has been set so another array doesn't need to
		// be constructed
		bias = calculationVariable$value;
		setFlag$bias = true;
	}

	// Getter for fixedFlag$sample17.
	@Override
	public final boolean get$fixedFlag$sample17() {
		return fixedFlag$sample17;
	}

	// Setter for fixedFlag$sample17.
	@Override
	public final void set$fixedFlag$sample17(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample17 including if probabilities
		// need to be updated.
		fixedFlag$sample17 = calculationVariable$value;
		
		// Should the probability of sample 17 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample17" with its value "calculationVariable$value".
		fixedProbFlag$sample17 = (calculationVariable$value && fixedProbFlag$sample17);
		
		// Should the probability of sample 35 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample17" with its value "calculationVariable$value".
		fixedProbFlag$sample35 = (calculationVariable$value && fixedProbFlag$sample35);
		
		// Should the probability of sample 52 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample17" with its value "calculationVariable$value".
		fixedProbFlag$sample52 = (calculationVariable$value && fixedProbFlag$sample52);
	}

	// Getter for fixedFlag$sample26.
	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	// Setter for fixedFlag$sample26.
	@Override
	public final void set$fixedFlag$sample26(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample26 including if probabilities
		// need to be updated.
		fixedFlag$sample26 = calculationVariable$value;
		
		// Should the probability of sample 26 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample26" with its value "calculationVariable$value".
		fixedProbFlag$sample26 = (calculationVariable$value && fixedProbFlag$sample26);
		
		// Should the probability of sample 70 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample26" with its value "calculationVariable$value".
		fixedProbFlag$sample70 = (calculationVariable$value && fixedProbFlag$sample70);
	}

	// Getter for fixedFlag$sample35.
	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	// Setter for fixedFlag$sample35.
	@Override
	public final void set$fixedFlag$sample35(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample35 including if probabilities
		// need to be updated.
		fixedFlag$sample35 = calculationVariable$value;
		
		// Should the probability of sample 35 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample35" with its value "calculationVariable$value".
		fixedProbFlag$sample35 = (calculationVariable$value && fixedProbFlag$sample35);
		
		// Should the probability of sample 52 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample35" with its value "calculationVariable$value".
		fixedProbFlag$sample52 = (calculationVariable$value && fixedProbFlag$sample52);
		
		// Should the probability of sample 70 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample35" with its value "calculationVariable$value".
		fixedProbFlag$sample70 = (calculationVariable$value && fixedProbFlag$sample70);
	}

	// Getter for fixedFlag$sample52.
	@Override
	public final boolean get$fixedFlag$sample52() {
		return fixedFlag$sample52;
	}

	// Setter for fixedFlag$sample52.
	@Override
	public final void set$fixedFlag$sample52(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample52 including if probabilities
		// need to be updated.
		fixedFlag$sample52 = calculationVariable$value;
		
		// Should the probability of sample 52 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample52" with its value "calculationVariable$value".
		fixedProbFlag$sample52 = (calculationVariable$value && fixedProbFlag$sample52);
		
		// Should the probability of sample 70 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample52" with its value "calculationVariable$value".
		fixedProbFlag$sample70 = (calculationVariable$value && fixedProbFlag$sample70);
	}

	// Getter for fixedFlag$sample70.
	@Override
	public final boolean get$fixedFlag$sample70() {
		return fixedFlag$sample70;
	}

	// Setter for fixedFlag$sample70.
	@Override
	public final void set$fixedFlag$sample70(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample70 including if probabilities
		// need to be updated.
		fixedFlag$sample70 = calculationVariable$value;
		
		// Should the probability of sample 70 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample70" with its value "calculationVariable$value".
		fixedProbFlag$sample70 = (calculationVariable$value && fixedProbFlag$sample70);
	}

	// Getter for flips.
	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	// Setter for flips.
	@Override
	public final void set$flips(boolean[] calculationVariable$value) {
		// Set flips with flag to mark that it has been set so another array doesn't need
		// to be constructed
		flips = calculationVariable$value;
		setFlag$flips = true;
	}

	// Getter for flipsMeasured.
	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	// Setter for flipsMeasured.
	@Override
	public final void set$flipsMeasured(boolean[] calculationVariable$value) {
		// Set flipsMeasured with flag to mark that it has been set so another array doesn't
		// need to be constructed
		flipsMeasured = calculationVariable$value;
	}

	// Getter for length$flipsMeasured.
	@Override
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	// Setter for length$flipsMeasured.
	@Override
	public final void set$length$flipsMeasured(int calculationVariable$value) {
		length$flipsMeasured = calculationVariable$value;
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
	public final void set$m(double[][] calculationVariable$value) {
		// Set m with flag to mark that it has been set so another array doesn't need to be
		// constructed
		m = calculationVariable$value;
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
	public final void set$st(int[] calculationVariable$value) {
		// Set st with flag to mark that it has been set so another array doesn't need to
		// be constructed
		st = calculationVariable$value;
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

	// Calculate the probability of the samples represented by sample17 using sampled
	// values.
	private final void logProbabilityValue$sample17() {
		// Determine if we need to calculate the values for sample task 17 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample17) {
			// Generating probabilities for sample task
			// Variable declaration of calculationVariable$sampleAccumulator moved.
			// Declaration comment was:
			// Variable declaration of calculationVariable$sampleAccumulator moved.
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
			double calculationVariable$sampleAccumulator = (DistributionSampling.logProbabilityDirichlet(m[0], v) + DistributionSampling.logProbabilityDirichlet(m[1], v));
			logProbability$var11 = calculationVariable$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var16 = calculationVariable$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$m = (logProbability$m + calculationVariable$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + calculationVariable$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample17)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$sampleAccumulator);
			
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
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var16);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var16);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample17)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var16);
		}
	}

	// Calculate the probability of the samples represented by sample26 using sampled
	// values.
	private final void logProbabilityValue$sample26() {
		// Determine if we need to calculate the values for sample task 26 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample26) {
			// Generating probabilities for sample task
			// Variable declaration of calculationVariable$sampleAccumulator moved.
			// Declaration comment was:
			// Variable declaration of calculationVariable$sampleAccumulator moved.
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
			double calculationVariable$sampleAccumulator = (DistributionSampling.logProbabilityBeta(bias[0], 1.0, 1.0) + DistributionSampling.logProbabilityBeta(bias[1], 1.0, 1.0));
			logProbability$var20 = calculationVariable$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var25 = calculationVariable$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$bias = (logProbability$bias + calculationVariable$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + calculationVariable$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample26)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$sampleAccumulator);
			
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
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$bias = (logProbability$bias + logProbability$var25);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var25);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample26)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var25);
		}
	}

	// Calculate the probability of the samples represented by sample35 using sampled
	// values.
	private final void logProbabilityValue$sample35() {
		// Determine if we need to calculate the values for sample task 35 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample35) {
			// Generating probabilities for sample task
			// Variable declaration of calculationVariable$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of calculationVariable$distributionAccumulator moved.
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
			// Variable declaration of calculationVariable$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			double calculationVariable$distributionAccumulator = DistributionSampling.logProbabilityCategorical(st[0], m[0]);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var32 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var33 = calculationVariable$distributionAccumulator;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
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
			logProbability$st = (logProbability$st + calculationVariable$distributionAccumulator);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
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
			logProbability$$model = (logProbability$$model + calculationVariable$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample35)
				// Variable declaration of calculationVariable$accumulator moved.
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
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedFlag$sample17);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var32 = logProbability$var33;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var33);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var33);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample35)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var33);
		}
	}

	// Calculate the probability of the samples represented by sample52 using sampled
	// values.
	private final void logProbabilityValue$sample52() {
		// Determine if we need to calculate the values for sample task 52 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample52) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double calculationVariable$accumulator = 0.0;
			for(int i$var39=4; i$var39<(samples + 3); i$var39+=1) {
				// Variable declaration of calculationVariable$distributionAccumulator moved.
				// Declaration comment was:
				// Variable declaration of calculationVariable$distributionAccumulator moved.
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
				// Variable declaration of calculationVariable$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				double calculationVariable$distributionAccumulator = DistributionSampling.logProbabilityCategorical(st[(i$var39 - 3)], m[(1 - st[(i$var39 - 4)])]);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$distributionAccumulator);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$var49[(i$var39 - 4)] = calculationVariable$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample52[(i$var39 - 4)] = calculationVariable$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + calculationVariable$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample52 = ((fixedFlag$sample52 && fixedFlag$sample17) && fixedFlag$sample35);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			for(int i$var39=4; i$var39<(samples + 3); i$var39+=1) {
				// Variable declaration of calculationVariable$RVaccumulator moved.
				double calculationVariable$RVaccumulator = logProbability$sample52[(i$var39 - 4)];
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
				logProbability$var49[(i$var39 - 4)] = calculationVariable$RVaccumulator;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + calculationVariable$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample70 using sampled
	// values.
	private final void logProbabilityValue$sample70() {
		// Determine if we need to calculate the values for sample task 70 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample70) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double calculationVariable$accumulator = 0.0;
			for(int j=5; j<(samples + 5); j+=1) {
				// Variable declaration of calculationVariable$distributionAccumulator moved.
				// Declaration comment was:
				// Variable declaration of calculationVariable$distributionAccumulator moved.
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
				// Variable declaration of calculationVariable$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				double calculationVariable$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flipsMeasured[(j - 5)], bias[(1 - st[(j - 5)])]);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$distributionAccumulator);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$var67[(j - 5)] = calculationVariable$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample70[(j - 5)] = calculationVariable$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + calculationVariable$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample70 = (((fixedFlag$sample70 && fixedFlag$sample26) && fixedFlag$sample35) && fixedFlag$sample52);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double calculationVariable$accumulator = 0.0;
			for(int j=5; j<(samples + 5); j+=1) {
				// Variable declaration of calculationVariable$RVaccumulator moved.
				double calculationVariable$RVaccumulator = logProbability$sample70[(j - 5)];
				calculationVariable$accumulator = (calculationVariable$accumulator + calculationVariable$RVaccumulator);
				logProbability$var67[(j - 5)] = calculationVariable$RVaccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + calculationVariable$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + calculationVariable$accumulator);
			logProbability$$evidence = (logProbability$$evidence + calculationVariable$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 17 drawn from Dirichlet 11. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample17(int var15) {
		// A local reference to the scratch space.
		calculationVariable$var16$CountGlobal[0] = 0.0;
		
		// A local reference to the scratch space.
		calculationVariable$var16$CountGlobal[1] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var15 == 0))
			// Looking for a path between Sample 17 and consumer Categorical 32.
			// 
			// Processing sample task 35 of consumer random variable null.
			// 
			// Increment the sample counter with the value sampled by sample task 35 of random
			// variable var32
			// 
			// A local reference to the scratch space.
			calculationVariable$var16$CountGlobal[st[0]] = (calculationVariable$var16$CountGlobal[st[0]] + 1.0);
		
		// Processing random variable 49.
		// 
		// Looking for a path between Sample 17 and consumer Categorical 49.
		for(int i$var39=4; i$var39<(samples + 3); i$var39+=1) {
			if((var15 == (1 - st[(i$var39 - 4)])))
				// Processing sample task 52 of consumer random variable null.
				// 
				// Increment the sample counter with the value sampled by sample task 52 of random
				// variable var49
				// 
				// A local reference to the scratch space.
				calculationVariable$var16$CountGlobal[st[(i$var39 - 3)]] = (calculationVariable$var16$CountGlobal[st[(i$var39 - 3)]] + 1.0);
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into calculationVariable$TargetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, calculationVariable$var16$CountGlobal, m[var15]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 26 drawn from Beta 20. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample26(int var24) {
		// Local variable to record the number of true samples.
		int calculationVariable$Sum = 0;
		
		// Local variable to record the number of samples.
		int calculationVariable$Count = 0;
		
		// Processing random variable 67.
		// 
		// Looking for a path between Sample 26 and consumer Bernoulli 67.
		for(int j=5; j<(samples + 5); j+=1) {
			if((var24 == (1 - st[(j - 5)]))) {
				// Processing sample task 70 of consumer random variable null.
				// 
				// Include the value sampled by task 70 from random variable var67.
				// Increment the number of samples.
				calculationVariable$Count = (calculationVariable$Count + 1);
				
				// If the sample value was positive increase the count
				if(flipsMeasured[(j - 5)])
					calculationVariable$Sum = (calculationVariable$Sum + 1);
			}
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		bias[var24] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, calculationVariable$Sum, calculationVariable$Count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 35 drawn from Categorical 32. Inference was performed using variable
	// marginalization.
	private final void sample35() {
		{
			// Variable declaration of calculationVariable$CurrentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "calculationVariable$valuePos" with its value "0".
			st[0] = 0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// calculationVariable$temp$0$var31's comment
			// Constructing a random variable input for use later.
			double calculationVariable$AccumulatedProbabilities = DistributionSampling.logProbabilityCategorical(0, m[0]);
			
			// Substituted "i$var39" with its value "4".
			if((1 < samples))
				// Processing sample task 52 of consumer random variable null.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 52 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of calculationVariable$AccumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var39" with its value "4".
				// 
				// Substituted "calculationVariable$temp$1$var48" with its value "var48".
				// 
				// Constructing a random variable input for use later.
				// 
				// Variable declaration of calculationVariable$CurrentValue moved.
				// Declaration comment was:
				// The value currently being tested
				// 
				// Value of the variable at this index
				// 
				// Substituted "calculationVariable$valuePos" with its value "0".
				calculationVariable$AccumulatedProbabilities = (DistributionSampling.logProbabilityCategorical(st[1], m[1]) + calculationVariable$AccumulatedProbabilities);
			
			// Substituted "j" with its value "5".
			if((0 < samples))
				// Processing sample task 70 of consumer random variable null.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 70 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of calculationVariable$AccumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "j" with its value "5".
				// 
				// Substituted "calculationVariable$temp$2$var66" with its value "var66".
				// 
				// Constructing a random variable input for use later.
				// 
				// Variable declaration of calculationVariable$CurrentValue moved.
				// Declaration comment was:
				// The value currently being tested
				// 
				// Value of the variable at this index
				// 
				// Substituted "calculationVariable$valuePos" with its value "0".
				calculationVariable$AccumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flipsMeasured[0], bias[1]) + calculationVariable$AccumulatedProbabilities);
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			calculationVariable$var33$StateProbabilityGlobal[0] = calculationVariable$AccumulatedProbabilities;
		}
		
		// Variable declaration of calculationVariable$CurrentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "calculationVariable$valuePos" with its value "1".
		st[0] = 1;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// calculationVariable$temp$0$var31's comment
		// Constructing a random variable input for use later.
		double calculationVariable$AccumulatedProbabilities = DistributionSampling.logProbabilityCategorical(1, m[0]);
		
		// Substituted "i$var39" with its value "4".
		if((1 < samples))
			// Processing sample task 52 of consumer random variable null.
			// 
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 52 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of calculationVariable$AccumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "i$var39" with its value "4".
			// 
			// Substituted "calculationVariable$temp$1$var48" with its value "var48".
			// 
			// Constructing a random variable input for use later.
			// 
			// Variable declaration of calculationVariable$CurrentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "calculationVariable$valuePos" with its value "1".
			calculationVariable$AccumulatedProbabilities = (DistributionSampling.logProbabilityCategorical(st[1], m[0]) + calculationVariable$AccumulatedProbabilities);
		
		// Substituted "j" with its value "5".
		if((0 < samples))
			// Processing sample task 70 of consumer random variable null.
			// 
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 70 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of calculationVariable$AccumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "j" with its value "5".
			// 
			// Substituted "calculationVariable$temp$2$var66" with its value "var66".
			// 
			// Constructing a random variable input for use later.
			// 
			// Variable declaration of calculationVariable$CurrentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "calculationVariable$valuePos" with its value "1".
			calculationVariable$AccumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flipsMeasured[0], bias[0]) + calculationVariable$AccumulatedProbabilities);
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		calculationVariable$var33$StateProbabilityGlobal[1] = calculationVariable$AccumulatedProbabilities;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double calculationVariable$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double calculationVariable$lseMax = calculationVariable$var33$StateProbabilityGlobal[0];
		
		// Find max value.
		// 
		// Get a local reference to the scratch space.
		for(int calculationVariable$lseIndex=1; calculationVariable$lseIndex<calculationVariable$var33$StateProbabilityGlobal.length; calculationVariable$lseIndex+=1) {
			// Get a local reference to the scratch space.
			double calculationVariable$lseElementValue = calculationVariable$var33$StateProbabilityGlobal[calculationVariable$lseIndex];
			if((calculationVariable$lseMax < calculationVariable$lseElementValue))
				calculationVariable$lseMax = calculationVariable$lseElementValue;
		}
		
		// If the maximum value is -infinity return -infinity.
		if((calculationVariable$lseMax == Double.NEGATIVE_INFINITY))
			calculationVariable$logSum = Double.NEGATIVE_INFINITY;
		
		// Sum the values in the array.
		else {
			// Initialise the sum of the array elements
			double calculationVariable$lseSum = 0.0;
			
			// Offset values, move to normal space, and sum.
			// 
			// Get a local reference to the scratch space.
			for(int calculationVariable$lseIndex=0; calculationVariable$lseIndex<calculationVariable$var33$StateProbabilityGlobal.length; calculationVariable$lseIndex+=1)
				// Get a local reference to the scratch space.
				calculationVariable$lseSum = (calculationVariable$lseSum + Math.exp((calculationVariable$var33$StateProbabilityGlobal[calculationVariable$lseIndex] - calculationVariable$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			calculationVariable$logSum = (Math.log(calculationVariable$lseSum) + calculationVariable$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((calculationVariable$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int calculationVariable$indexName=0; calculationVariable$indexName<calculationVariable$var33$StateProbabilityGlobal.length; calculationVariable$indexName+=1)
				// Get a local reference to the scratch space.
				calculationVariable$var33$StateProbabilityGlobal[calculationVariable$indexName] = (1.0 / calculationVariable$var33$StateProbabilityGlobal.length);
		} else {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int calculationVariable$indexName=0; calculationVariable$indexName<calculationVariable$var33$StateProbabilityGlobal.length; calculationVariable$indexName+=1)
				// Get a local reference to the scratch space.
				calculationVariable$var33$StateProbabilityGlobal[calculationVariable$indexName] = Math.exp((calculationVariable$var33$StateProbabilityGlobal[calculationVariable$indexName] - calculationVariable$logSum));
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Get a local reference to the scratch space.
		st[0] = DistributionSampling.sampleCategorical(RNG$, calculationVariable$var33$StateProbabilityGlobal);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 52 drawn from Categorical 49. Inference was performed using variable
	// marginalization.
	private final void sample52(int i$var39) {
		{
			// Variable declaration of calculationVariable$CurrentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "calculationVariable$valuePos" with its value "0".
			st[(i$var39 - 3)] = 0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// calculationVariable$temp$0$var48's comment
			// Constructing a random variable input for use later.
			double calculationVariable$AccumulatedProbabilities = DistributionSampling.logProbabilityCategorical(0, m[(1 - st[(i$var39 - 4)])]);
			int index$i$1_2 = (i$var39 + 1);
			if((index$i$1_2 < (samples + 3)))
				// Processing sample task 52 of consumer random variable null.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 52 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of calculationVariable$AccumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "calculationVariable$temp$1$var48" with its value "var48".
				// 
				// Constructing a random variable input for use later.
				// 
				// Variable declaration of calculationVariable$CurrentValue moved.
				// Declaration comment was:
				// The value currently being tested
				// 
				// Value of the variable at this index
				// 
				// Substituted "calculationVariable$valuePos" with its value "0".
				calculationVariable$AccumulatedProbabilities = (DistributionSampling.logProbabilityCategorical(st[(index$i$1_2 - 3)], m[1]) + calculationVariable$AccumulatedProbabilities);
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 70 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of calculationVariable$AccumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Processing sample task 70 of consumer random variable null.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "calculationVariable$temp$2$var66" with its value "var66".
			// 
			// Constructing a random variable input for use later.
			// 
			// Variable declaration of calculationVariable$CurrentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "calculationVariable$valuePos" with its value "0".
			calculationVariable$AccumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flipsMeasured[(i$var39 - 3)], bias[1]) + calculationVariable$AccumulatedProbabilities);
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			calculationVariable$var50$StateProbabilityGlobal[0] = calculationVariable$AccumulatedProbabilities;
		}
		
		// Variable declaration of calculationVariable$CurrentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "calculationVariable$valuePos" with its value "1".
		st[(i$var39 - 3)] = 1;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// calculationVariable$temp$0$var48's comment
		// Constructing a random variable input for use later.
		double calculationVariable$AccumulatedProbabilities = DistributionSampling.logProbabilityCategorical(1, m[(1 - st[(i$var39 - 4)])]);
		int index$i$1_2 = (i$var39 + 1);
		if((index$i$1_2 < (samples + 3)))
			// Processing sample task 52 of consumer random variable null.
			// 
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 52 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of calculationVariable$AccumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "calculationVariable$temp$1$var48" with its value "var48".
			// 
			// Constructing a random variable input for use later.
			// 
			// Variable declaration of calculationVariable$CurrentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
			// Substituted "calculationVariable$valuePos" with its value "1".
			calculationVariable$AccumulatedProbabilities = (DistributionSampling.logProbabilityCategorical(st[(index$i$1_2 - 3)], m[0]) + calculationVariable$AccumulatedProbabilities);
		
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 70 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// Variable declaration of calculationVariable$AccumulatedConsumerProbabilities moved.
		// Declaration comment was:
		// Processing sample task 70 of consumer random variable null.
		// 
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		// 
		// Substituted "calculationVariable$temp$2$var66" with its value "var66".
		// 
		// Constructing a random variable input for use later.
		// 
		// Variable declaration of calculationVariable$CurrentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "calculationVariable$valuePos" with its value "1".
		calculationVariable$AccumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flipsMeasured[(i$var39 - 3)], bias[0]) + calculationVariable$AccumulatedProbabilities);
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		calculationVariable$var50$StateProbabilityGlobal[1] = calculationVariable$AccumulatedProbabilities;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double calculationVariable$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double calculationVariable$lseMax = calculationVariable$var50$StateProbabilityGlobal[0];
		
		// Find max value.
		// 
		// Get a local reference to the scratch space.
		for(int calculationVariable$lseIndex=1; calculationVariable$lseIndex<calculationVariable$var50$StateProbabilityGlobal.length; calculationVariable$lseIndex+=1) {
			// Get a local reference to the scratch space.
			double calculationVariable$lseElementValue = calculationVariable$var50$StateProbabilityGlobal[calculationVariable$lseIndex];
			if((calculationVariable$lseMax < calculationVariable$lseElementValue))
				calculationVariable$lseMax = calculationVariable$lseElementValue;
		}
		
		// If the maximum value is -infinity return -infinity.
		if((calculationVariable$lseMax == Double.NEGATIVE_INFINITY))
			calculationVariable$logSum = Double.NEGATIVE_INFINITY;
		
		// Sum the values in the array.
		else {
			// Initialise the sum of the array elements
			double calculationVariable$lseSum = 0.0;
			
			// Offset values, move to normal space, and sum.
			// 
			// Get a local reference to the scratch space.
			for(int calculationVariable$lseIndex=0; calculationVariable$lseIndex<calculationVariable$var50$StateProbabilityGlobal.length; calculationVariable$lseIndex+=1)
				// Get a local reference to the scratch space.
				calculationVariable$lseSum = (calculationVariable$lseSum + Math.exp((calculationVariable$var50$StateProbabilityGlobal[calculationVariable$lseIndex] - calculationVariable$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			calculationVariable$logSum = (Math.log(calculationVariable$lseSum) + calculationVariable$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((calculationVariable$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int calculationVariable$indexName=0; calculationVariable$indexName<calculationVariable$var50$StateProbabilityGlobal.length; calculationVariable$indexName+=1)
				// Get a local reference to the scratch space.
				calculationVariable$var50$StateProbabilityGlobal[calculationVariable$indexName] = (1.0 / calculationVariable$var50$StateProbabilityGlobal.length);
		} else {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int calculationVariable$indexName=0; calculationVariable$indexName<calculationVariable$var50$StateProbabilityGlobal.length; calculationVariable$indexName+=1)
				// Get a local reference to the scratch space.
				calculationVariable$var50$StateProbabilityGlobal[calculationVariable$indexName] = Math.exp((calculationVariable$var50$StateProbabilityGlobal[calculationVariable$indexName] - calculationVariable$logSum));
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Get a local reference to the scratch space.
		st[(i$var39 - 3)] = DistributionSampling.sampleCategorical(RNG$, calculationVariable$var50$StateProbabilityGlobal);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Allocation of calculationVariable$var16$CountGlobal for single threaded execution
		// 
		// Unrolled loop
		// 
		// Calculate the longest array this random variable could produce and allocate an
		// array large enough to handle this.
		calculationVariable$var16$CountGlobal = new double[2];
		
		// Constructor for calculationVariable$var33$StateProbabilityGlobal
		// 
		// Allocation of calculationVariable$var33$StateProbabilityGlobal for single threaded
		// execution
		// 
		// Variable to record the maximum value of Task Get 33. Initially set to the value
		// of putTask 18.
		calculationVariable$var33$StateProbabilityGlobal = new double[2];
		
		// Allocation of calculationVariable$var50$StateProbabilityGlobal for single threaded
		// execution
		// 
		// Variable to record the maximum value of Task Get 50. Initially set to the value
		// of putTask 18.
		calculationVariable$var50$StateProbabilityGlobal = new double[2];
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
		
		// If flips has not been set already allocate space.
		if(!setFlag$flips)
			// Constructor for flips
			flips = new boolean[length$flipsMeasured];
		
		// Constructor for logProbability$var49
		logProbability$var49 = new double[(length$flipsMeasured - 1)];
		
		// Constructor for logProbability$sample52
		logProbability$sample52 = new double[(length$flipsMeasured - 1)];
		
		// Constructor for logProbability$var67
		logProbability$var67 = new double[length$flipsMeasured];
		
		// Constructor for logProbability$sample70
		logProbability$sample70 = new double[length$flipsMeasured];
		
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
		if(!fixedFlag$sample26) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample35)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample52) {
			for(int i$var39=4; i$var39<(samples + 3); i$var39+=1)
				st[(i$var39 - 3)] = DistributionSampling.sampleCategorical(RNG$, m[(1 - st[(i$var39 - 4)])]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample70) {
			for(int j=5; j<(samples + 5); j+=1)
				flips[(j - 5)] = DistributionSampling.sampleBernoulli(RNG$, bias[(1 - st[(j - 5)])]);
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
		if(!fixedFlag$sample26) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample35)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample52) {
			for(int i$var39=4; i$var39<(samples + 3); i$var39+=1)
				st[(i$var39 - 3)] = DistributionSampling.sampleCategorical(RNG$, m[(1 - st[(i$var39 - 4)])]);
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
		if(!fixedFlag$sample26) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample35)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample52) {
			for(int i$var39=4; i$var39<(samples + 3); i$var39+=1)
				st[(i$var39 - 3)] = DistributionSampling.sampleCategorical(RNG$, m[(1 - st[(i$var39 - 4)])]);
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
			if(!fixedFlag$sample26) {
				sample26(0);
				sample26(1);
			}
			if(!fixedFlag$sample35)
				sample35();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample52) {
				for(int i$var39=4; i$var39<(samples + 3); i$var39+=1)
					sample52(i$var39);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample52) {
				for(int i$var39=(samples + 2); i$var39>=4; i$var39-=1)
					sample52(i$var39);
			}
			if(!fixedFlag$sample35)
				sample35();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample26) {
				sample26(1);
				sample26(0);
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
		if(!fixedProbFlag$sample26)
			logProbability$var25 = 0.0;
		logProbability$var32 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$var33 = 0.0;
		for(int i$var39=4; i$var39<(samples + 3); i$var39+=1)
			logProbability$var49[(i$var39 - 4)] = 0.0;
		if(!fixedProbFlag$sample52) {
			for(int i$var39=4; i$var39<(samples + 3); i$var39+=1)
				logProbability$sample52[(i$var39 - 4)] = 0.0;
		}
		for(int j=5; j<(samples + 5); j+=1)
			logProbability$var67[(j - 5)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample70) {
			for(int j=5; j<(samples + 5); j+=1)
				logProbability$sample70[(j - 5)] = 0.0;
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
		if(fixedFlag$sample26)
			logProbabilityValue$sample26();
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		if(fixedFlag$sample52)
			logProbabilityValue$sample52();
		logProbabilityValue$sample70();
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
		logProbabilityValue$sample26();
		logProbabilityValue$sample35();
		logProbabilityValue$sample52();
		logProbabilityValue$sample70();
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
		logProbabilityValue$sample26();
		logProbabilityValue$sample35();
		logProbabilityValue$sample52();
		logProbabilityValue$sample70();
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
		if(!fixedFlag$sample26) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample35)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample52) {
			for(int i$var39=4; i$var39<(samples + 3); i$var39+=1)
				st[(i$var39 - 3)] = DistributionSampling.sampleCategorical(RNG$, m[(1 - st[(i$var39 - 4)])]);
		}
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart6(boolean[] flipsMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n        \n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        int[] st = new int[samples];\n\n        st[0] = categorical(m[0]).sample();\n\n        for(int i:[4..samples + 3))\n            st[i-3] = categorical(m[(states - 1) - st[i-4]]).sample();\n            \n        boolean[] flips = new boolean[samples];\n            \n        for(int j:[5..samples+5))\n            flips[j-5] = bernoulli(bias[(states - 1) - st[j-5]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
	}
}