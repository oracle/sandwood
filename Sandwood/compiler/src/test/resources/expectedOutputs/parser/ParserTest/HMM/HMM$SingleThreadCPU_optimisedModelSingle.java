package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMM$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMM$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private double[] cv$var30$countGlobal;
	private double[] cv$var54$stateProbabilityGlobal;
	private double[] cv$var72$stateProbabilityGlobal;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample48 = false;
	private boolean fixedFlag$sample57 = false;
	private boolean fixedFlag$sample75 = false;
	private boolean fixedFlag$sample91 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample48 = false;
	private boolean fixedProbFlag$sample57 = false;
	private boolean fixedProbFlag$sample75 = false;
	private boolean fixedProbFlag$sample91 = false;
	private boolean[] flips;
	private int length$measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double logProbability$st;
	private double logProbability$var18;
	private double logProbability$var30;
	private double logProbability$var34;
	private double logProbability$var46;
	private double logProbability$var53;
	private double logProbability$var54;
	private double logProbability$var71;
	private double logProbability$var72;
	private double logProbability$var87;
	private double logProbability$var88;
	private double[][] m;
	private boolean[] measured;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[] st;
	private int states;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMM$SingleThreadCPU(ExecutionTarget target) {
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
		
		// Unset the fixed probability flag for sample 91 as it depends on bias.
		fixedProbFlag$sample91 = false;
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
		
		// Should the probability of sample 57 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample31" with its value "cv$value".
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		
		// Should the probability of sample 75 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample31" with its value "cv$value".
		fixedProbFlag$sample75 = (cv$value && fixedProbFlag$sample75);
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
		
		// Should the probability of sample 91 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample48" with its value "cv$value".
		fixedProbFlag$sample91 = (cv$value && fixedProbFlag$sample91);
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
		
		// Should the probability of sample 75 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample75 = (cv$value && fixedProbFlag$sample75);
		
		// Should the probability of sample 91 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample91 = (cv$value && fixedProbFlag$sample91);
	}

	// Getter for fixedFlag$sample75.
	@Override
	public final boolean get$fixedFlag$sample75() {
		return fixedFlag$sample75;
	}

	// Setter for fixedFlag$sample75.
	@Override
	public final void set$fixedFlag$sample75(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample75 including if probabilities
		// need to be updated.
		fixedFlag$sample75 = cv$value;
		
		// Should the probability of sample 75 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample75" with its value "cv$value".
		fixedProbFlag$sample75 = (cv$value && fixedProbFlag$sample75);
		
		// Should the probability of sample 91 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample75" with its value "cv$value".
		fixedProbFlag$sample91 = (cv$value && fixedProbFlag$sample91);
	}

	// Getter for fixedFlag$sample91.
	@Override
	public final boolean get$fixedFlag$sample91() {
		return fixedFlag$sample91;
	}

	// Setter for fixedFlag$sample91.
	@Override
	public final void set$fixedFlag$sample91(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample91 including if probabilities
		// need to be updated.
		fixedFlag$sample91 = cv$value;
		
		// Should the probability of sample 91 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample91" with its value "cv$value".
		fixedProbFlag$sample91 = (cv$value && fixedProbFlag$sample91);
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
		
		// Unset the fixed probability flag for sample 91 as it depends on flips.
		fixedProbFlag$sample91 = false;
	}

	// Getter for length$measured.
	@Override
	public final int get$length$measured() {
		return length$measured;
	}

	// Setter for length$measured.
	@Override
	public final void set$length$measured(int cv$value) {
		length$measured = cv$value;
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
		
		// Unset the fixed probability flag for sample 31 as it depends on m.
		fixedProbFlag$sample31 = false;
		
		// Unset the fixed probability flag for sample 57 as it depends on m.
		fixedProbFlag$sample57 = false;
		
		// Unset the fixed probability flag for sample 75 as it depends on m.
		fixedProbFlag$sample75 = false;
	}

	// Getter for measured.
	@Override
	public final boolean[] get$measured() {
		return measured;
	}

	// Setter for measured.
	@Override
	public final void set$measured(boolean[] cv$value) {
		// Set measured with flag to mark that it has been set so another array doesn't need
		// to be constructed
		measured = cv$value;
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
		
		// Unset the fixed probability flag for sample 57 as it depends on st.
		fixedProbFlag$sample57 = false;
		
		// Unset the fixed probability flag for sample 75 as it depends on st.
		fixedProbFlag$sample75 = false;
		
		// Unset the fixed probability flag for sample 91 as it depends on st.
		fixedProbFlag$sample91 = false;
	}

	// Getter for states.
	@Override
	public final int get$states() {
		return states;
	}

	// Setter for states.
	@Override
	public final void set$states(int cv$value) {
		states = cv$value;
	}

	// Getter for v.
	@Override
	public final double[] get$v() {
		return v;
	}

	// Calculate the probability of the samples represented by sample31 using sampled
	// values.
	private final void logProbabilityValue$sample31() {
		// Determine if we need to calculate the values for sample task 31 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample31) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var29 = 0; var29 < states; var29 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var29], v));
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
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var45 = 0; var45 < states; var45 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(bias[var45], 1.0, 1.0));
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

	// Calculate the probability of the samples represented by sample57 using sampled
	// values.
	private final void logProbabilityValue$sample57() {
		// Determine if we need to calculate the values for sample task 57 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample57) {
			// Generating probabilities for sample task
			// The sample value to calculate the probability of generating
			int cv$sampleValue = st[0];
			double[] var52 = m[0];
			
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
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var52.length))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var53 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var54 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample57)
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
			fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedFlag$sample31);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var53 = logProbability$var54;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var54);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var54);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample57)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var54);
		}
	}

	// Calculate the probability of the samples represented by sample75 using sampled
	// values.
	private final void logProbabilityValue$sample75() {
		// Determine if we need to calculate the values for sample task 75 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample75) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i = 1; i < samples; i += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = st[i];
				double[] var70 = m[st[(i - 1)]];
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var70.length))?Math.log(var70[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			logProbability$var71 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var72 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample75)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample75 = ((fixedFlag$sample75 && fixedFlag$sample31) && fixedFlag$sample57);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var71 = logProbability$var72;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var72);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var72);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample75)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var72);
		}
	}

	// Calculate the probability of the samples represented by sample91 using sampled
	// values.
	private final void logProbabilityValue$sample91() {
		// Determine if we need to calculate the values for sample task 91 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample91) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int j = 0; j < samples; j += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[j], bias[st[j]]));
			logProbability$var87 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var88 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample91 = (((fixedFlag$sample91 && fixedFlag$sample48) && fixedFlag$sample57) && fixedFlag$sample75);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var87 = logProbability$var88;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$flips = (logProbability$flips + logProbability$var88);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var88);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var88);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 31 drawn from Dirichlet 18. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample31(int var29) {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < states; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var30$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var29 == 0))
			// Looking for a path between Sample 31 and consumer Categorical 53.
			// 
			// Processing sample task 57 of consumer random variable null.
			// 
			// Increment the sample counter with the value sampled by sample task 57 of random
			// variable var53
			// 
			// A local reference to the scratch space.
			cv$var30$countGlobal[st[0]] = (cv$var30$countGlobal[st[0]] + 1.0);
		
		// Processing random variable 71.
		// 
		// Looking for a path between Sample 31 and consumer Categorical 71.
		for(int i = 1; i < samples; i += 1) {
			if((var29 == st[(i - 1)]))
				// Processing sample task 75 of consumer random variable null.
				// 
				// Increment the sample counter with the value sampled by sample task 75 of random
				// variable var71
				// 
				// A local reference to the scratch space.
				cv$var30$countGlobal[st[i]] = (cv$var30$countGlobal[st[i]] + 1.0);
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var30$countGlobal, m[var29]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 48 drawn from Beta 34. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample48(int var45) {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		
		// Processing random variable 87.
		// 
		// Looking for a path between Sample 48 and consumer Bernoulli 87.
		for(int j = 0; j < samples; j += 1) {
			if((var45 == st[j])) {
				// Processing sample task 91 of consumer random variable null.
				// 
				// Include the value sampled by task 91 from random variable var87.
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
	// by sample task 57 drawn from Categorical 53. Inference was performed using variable
	// marginalization.
	private final void sample57() {
		// Variable declaration of cv$noStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
		// cv$noStates's comment
		// Calculate the number of states to evaluate.
		int cv$noStates = Math.max(0, states);
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Value of the variable at this index
			st[0] = cv$valuePos;
			
			// Variable declaration of cv$temp$0$var52 moved.
			// 
			// Constructing a random variable input for use later.
			double[] cv$temp$0$var52 = m[0];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var52.length)?Math.log(cv$temp$0$var52[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Substituted "i" with its value "1".
			if((1 < samples)) {
				// Processing sample task 75 of consumer random variable null.
				// Variable declaration of cv$temp$1$var70 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 71.
				// 
				// Looking for a path between Sample 57 and consumer Categorical 71.
				// 
				// Value of the variable at this index
				double[] cv$temp$1$var70 = m[cv$valuePos];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 75 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i" with its value "1".
				cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < cv$temp$1$var70.length))?Math.log(cv$temp$1$var70[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Substituted "j" with its value "0".
			if((0 < samples))
				// Processing sample task 91 of consumer random variable null.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 91 with the current configuration.
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
				// cv$temp$2$var86's comment
				// Variable declaration of cv$temp$2$var86 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 87.
				// 
				// Looking for a path between Sample 57 and consumer Bernoulli 87.
				// 
				// Value of the variable at this index
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0], bias[cv$valuePos]) + cv$accumulatedProbabilities);
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var54$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
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
		double cv$lseMax = cv$var54$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var54$stateProbabilityGlobal[cv$lseIndex];
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
				cv$lseSum = (cv$lseSum + Math.exp((cv$var54$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
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
				cv$var54$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var54$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var54$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var54$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var54$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Get a local reference to the scratch space.
		st[0] = DistributionSampling.sampleCategorical(RNG$, cv$var54$stateProbabilityGlobal);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 75 drawn from Categorical 71. Inference was performed using variable
	// marginalization.
	private final void sample75(int i) {
		// Variable declaration of cv$noStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
		// cv$noStates's comment
		// Calculate the number of states to evaluate.
		int cv$noStates = Math.max(0, states);
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Value of the variable at this index
			st[i] = cv$valuePos;
			
			// Variable declaration of cv$temp$0$var70 moved.
			// 
			// Constructing a random variable input for use later.
			double[] cv$temp$0$var70 = m[st[(i - 1)]];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var70.length)?Math.log(cv$temp$0$var70[cv$valuePos]):Double.NEGATIVE_INFINITY);
			int index$i$1_2 = (i + 1);
			if((index$i$1_2 < samples)) {
				// Processing sample task 75 of consumer random variable null.
				// Variable declaration of cv$temp$1$var70 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 71.
				// 
				// Looking for a path between Sample 75 and consumer Categorical 71.
				// 
				// Value of the variable at this index
				double[] cv$temp$1$var70 = m[cv$valuePos];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 75 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= st[index$i$1_2]) && (st[index$i$1_2] < cv$temp$1$var70.length))?Math.log(cv$temp$1$var70[st[index$i$1_2]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 91 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Processing sample task 91 of consumer random variable null.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "j" with its value "i".
			// 
			// cv$temp$2$var86's comment
			// Variable declaration of cv$temp$2$var86 moved.
			// 
			// Constructing a random variable input for use later.
			// 
			// Processing random variable 87.
			// 
			// Looking for a path between Sample 75 and consumer Bernoulli 87.
			// 
			// Value of the variable at this index
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[i], bias[cv$valuePos]) + cv$accumulatedProbabilities);
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var72$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
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
		double cv$lseMax = cv$var72$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var72$stateProbabilityGlobal[cv$lseIndex];
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
				cv$lseSum = (cv$lseSum + Math.exp((cv$var72$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
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
				cv$var72$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var72$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var72$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var72$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var72$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Get a local reference to the scratch space.
		st[i] = DistributionSampling.sampleCategorical(RNG$, cv$var72$stateProbabilityGlobal);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var30$countGlobal
		// 
		// Calculate the longest array this random variable could produce and allocate an
		// array large enough to handle this.
		int cv$max = 0;
		if((0 < states))
			cv$max = states;
		
		// Allocation of cv$var30$countGlobal for single threaded execution
		cv$var30$countGlobal = new double[cv$max];
		
		// Constructor for cv$var54$stateProbabilityGlobal
		// 
		// Allocation of cv$var54$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 55. Initially set to the value
		// of putTask 32.
		cv$var54$stateProbabilityGlobal = new double[states];
		
		// Allocation of cv$var72$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 73. Initially set to the value
		// of putTask 32.
		cv$var72$stateProbabilityGlobal = new double[states];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for v
		v = new double[states];
		
		// If m has not been set already allocate space.
		if(!setFlag$m) {
			// Constructor for m
			m = new double[states][];
			for(int var29 = 0; var29 < states; var29 += 1)
				m[var29] = new double[states];
		}
		
		// If bias has not been set already allocate space.
		if(!setFlag$bias)
			// Constructor for bias
			bias = new double[states];
		
		// If st has not been set already allocate space.
		if(!setFlag$st)
			// Constructor for st
			st = new int[length$measured];
		
		// If flips has not been set already allocate space.
		if(!setFlag$flips)
			// Constructor for flips
			flips = new boolean[length$measured];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample31) {
			for(int var29 = 0; var29 < states; var29 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var29]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample48) {
			for(int var45 = 0; var45 < states; var45 += 1)
				bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample57)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample75) {
			for(int i = 1; i < samples; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample91) {
			for(int j = 0; j < samples; j += 1)
				flips[j] = DistributionSampling.sampleBernoulli(RNG$, bias[st[j]]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample31) {
			for(int var29 = 0; var29 < states; var29 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var29]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample48) {
			for(int var45 = 0; var45 < states; var45 += 1)
				bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample57)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample75) {
			for(int i = 1; i < samples; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample31) {
			for(int var29 = 0; var29 < states; var29 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var29]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample48) {
			for(int var45 = 0; var45 < states; var45 += 1)
				bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample57)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample75) {
			for(int i = 1; i < samples; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]]);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample31) {
				for(int var29 = 0; var29 < states; var29 += 1)
					sample31(var29);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample48) {
				for(int var45 = 0; var45 < states; var45 += 1)
					sample48(var45);
			}
			if(!fixedFlag$sample57)
				sample57();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample75) {
				for(int i = 1; i < samples; i += 1)
					sample75(i);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample75) {
				for(int i = (samples - 1); i >= 1; i -= 1)
					sample75(i);
			}
			if(!fixedFlag$sample57)
				sample57();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample48) {
				for(int var45 = (states - 1); var45 >= 0; var45 -= 1)
					sample48(var45);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample31) {
				for(int var29 = (states - 1); var29 >= 0; var29 -= 1)
					sample31(var29);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		for(int var15 = 0; var15 < states; var15 += 1)
			v[var15] = 0.1;
		samples = length$measured;
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
		logProbability$var53 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample57)
			logProbability$var54 = 0.0;
		logProbability$var71 = 0.0;
		if(!fixedProbFlag$sample75)
			logProbability$var72 = 0.0;
		logProbability$var87 = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample91)
			logProbability$var88 = 0.0;
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
		if(fixedFlag$sample57)
			logProbabilityValue$sample57();
		if(fixedFlag$sample75)
			logProbabilityValue$sample75();
		logProbabilityValue$sample91();
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
		logProbabilityValue$sample57();
		logProbabilityValue$sample75();
		logProbabilityValue$sample91();
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
		logProbabilityValue$sample57();
		logProbabilityValue$sample75();
		logProbabilityValue$sample91();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample31) {
			for(int var29 = 0; var29 < states; var29 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var29]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample48) {
			for(int var45 = 0; var45 < states; var45 += 1)
				bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample57)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample75) {
			for(int i = 1; i < samples; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]]);
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
			flips[cv$index1] = measured[cv$index1];
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
		     + "model HMM(boolean[] measured, int states) {\n"
		     + "\n"
		     + "  double[] v = new double[states] <~ 0.1;\n"
		     + "  double[][] m = dirichlet(v).sample(states);\n"
		     + "    \n"
		     + "  double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "  int samples = measured.length;\n"
		     + "  int[] st = new int[samples];\n"
		     + "        \n"
		     + "  st[0] = categorical(m[0]).sample();\n"
		     + " \n"
		     + "  for(int i:[1..samples))\n"
		     + "    st[i] = categorical(m[st[i - 1]]).sample();\n"
		     + "\n"
		     + "  boolean[] flips = new boolean[samples];\n"
		     + "  for(int j:[0..samples))\n"
		     + "    flips[j] = bernoulli(bias[st[j]]).sample();\n"
		     + "\n"
		     + "  flips.observe(measured);\n"
		     + "}";
	}
}