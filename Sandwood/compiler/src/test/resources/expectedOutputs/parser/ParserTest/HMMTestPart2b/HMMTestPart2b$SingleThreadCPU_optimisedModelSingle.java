package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart2b$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMTestPart2b$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private double[] cv$var16$countGlobal;
	private double[] cv$var43$stateProbabilityGlobal;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample27 = false;
	private boolean fixedFlag$sample46 = false;
	private boolean fixedFlag$sample54 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample27 = false;
	private boolean fixedProbFlag$sample46 = false;
	private boolean fixedProbFlag$sample54 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double logProbability$st;
	private double logProbability$var11;
	private double logProbability$var16;
	private double logProbability$var20;
	private double logProbability$var25;
	private double logProbability$var42;
	private double logProbability$var43;
	private double logProbability$var50;
	private double logProbability$var51;
	private double[][] m;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart2b$SingleThreadCPU(ExecutionTarget target) {
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
		
		// Unset the fixed probability flag for sample 27 as it depends on bias.
		fixedProbFlag$sample27 = false;
		
		// Unset the fixed probability flag for sample 54 as it depends on bias.
		fixedProbFlag$sample54 = false;
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
		
		// Should the probability of sample 46 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample17" with its value "cv$value".
		fixedProbFlag$sample46 = (cv$value && fixedProbFlag$sample46);
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
		
		// Should the probability of sample 54 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample27" with its value "cv$value".
		fixedProbFlag$sample54 = (cv$value && fixedProbFlag$sample54);
	}

	// Getter for fixedFlag$sample46.
	@Override
	public final boolean get$fixedFlag$sample46() {
		return fixedFlag$sample46;
	}

	// Setter for fixedFlag$sample46.
	@Override
	public final void set$fixedFlag$sample46(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample46 including if probabilities
		// need to be updated.
		fixedFlag$sample46 = cv$value;
		
		// Should the probability of sample 46 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample46" with its value "cv$value".
		fixedProbFlag$sample46 = (cv$value && fixedProbFlag$sample46);
		
		// Should the probability of sample 54 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample46" with its value "cv$value".
		fixedProbFlag$sample54 = (cv$value && fixedProbFlag$sample54);
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
		
		// Unset the fixed probability flag for sample 54 as it depends on flips.
		fixedProbFlag$sample54 = false;
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
		
		// Unset the fixed probability flag for sample 17 as it depends on m.
		fixedProbFlag$sample17 = false;
		
		// Unset the fixed probability flag for sample 46 as it depends on m.
		fixedProbFlag$sample46 = false;
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
		
		// Unset the fixed probability flag for sample 46 as it depends on st.
		fixedProbFlag$sample46 = false;
		
		// Unset the fixed probability flag for sample 54 as it depends on st.
		fixedProbFlag$sample54 = false;
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

	// Calculate the probability of the samples represented by sample46 using sampled
	// values.
	private final void logProbabilityValue$sample46() {
		// Determine if we need to calculate the values for sample task 46 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample46) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var37 = 0; i$var37 < (samples - 1); i$var37 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = st[(i$var37 + 1)];
				double[] var41 = m[st[i$var37]];
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var41.length))?Math.log(var41[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			logProbability$var42 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var43 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample46)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample46 = (fixedFlag$sample46 && fixedFlag$sample17);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var42 = logProbability$var43;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var43);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var43);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample46)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var43);
		}
	}

	// Calculate the probability of the samples represented by sample54 using sampled
	// values.
	private final void logProbabilityValue$sample54() {
		// Determine if we need to calculate the values for sample task 54 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample54) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int j = 1; j < samples; j += 1)
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
			logProbability$var50 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var51 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample54 = ((fixedFlag$sample54 && fixedFlag$sample27) && fixedFlag$sample46);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var50 = logProbability$var51;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$flips = (logProbability$flips + logProbability$var51);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var51);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var51);
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
		
		// Processing random variable 42.
		// 
		// Looking for a path between Sample 17 and consumer Categorical 42.
		for(int i$var37 = 0; i$var37 < (samples - 1); i$var37 += 1) {
			if((var15 == st[i$var37]))
				// Processing sample task 46 of consumer random variable null.
				// 
				// Increment the sample counter with the value sampled by sample task 46 of random
				// variable var42
				// 
				// A local reference to the scratch space.
				cv$var16$countGlobal[st[(i$var37 + 1)]] = (cv$var16$countGlobal[st[(i$var37 + 1)]] + 1.0);
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
		
		// Processing random variable 50.
		// 
		// Looking for a path between Sample 27 and consumer Bernoulli 50.
		for(int j = 1; j < samples; j += 1) {
			if((var24 == st[j])) {
				// Processing sample task 54 of consumer random variable null.
				// 
				// Include the value sampled by task 54 from random variable var50.
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
	// by sample task 46 drawn from Categorical 42. Inference was performed using variable
	// marginalization.
	private final void sample46(int i$var37) {
		// Unrolled loop
		{
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			st[(i$var37 + 1)] = 0;
			
			// Variable declaration of cv$temp$0$var41 moved.
			// 
			// Constructing a random variable input for use later.
			double[] cv$temp$0$var41 = m[st[i$var37]];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = ((0 < cv$temp$0$var41.length)?Math.log(cv$temp$0$var41[0]):Double.NEGATIVE_INFINITY);
			int index$i$1_2 = (i$var37 + 1);
			if((index$i$1_2 < (samples - 1))) {
				// Processing sample task 46 of consumer random variable null.
				// Variable declaration of cv$temp$1$var41 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 42.
				// 
				// Looking for a path between Sample 46 and consumer Categorical 42.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double[] cv$temp$1$var41 = m[0];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 46 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= st[(index$i$1_2 + 1)]) && (st[(index$i$1_2 + 1)] < cv$temp$1$var41.length))?Math.log(cv$temp$1$var41[st[(index$i$1_2 + 1)]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 54 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Processing sample task 54 of consumer random variable null.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// cv$temp$2$var49's comment
			// Constructing a random variable input for use later.
			// 
			// Processing random variable 50.
			// 
			// Looking for a path between Sample 46 and consumer Bernoulli 50.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[(i$var37 + 1)], bias[0]) + cv$accumulatedProbabilities);
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var43$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		st[(i$var37 + 1)] = 1;
		
		// Variable declaration of cv$temp$0$var41 moved.
		// 
		// Constructing a random variable input for use later.
		double[] cv$temp$0$var41 = m[st[i$var37]];
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = ((1 < cv$temp$0$var41.length)?Math.log(cv$temp$0$var41[1]):Double.NEGATIVE_INFINITY);
		int index$i$1_2 = (i$var37 + 1);
		if((index$i$1_2 < (samples - 1))) {
			// Processing sample task 46 of consumer random variable null.
			// Variable declaration of cv$temp$1$var41 moved.
			// 
			// Constructing a random variable input for use later.
			// 
			// Processing random variable 42.
			// 
			// Looking for a path between Sample 46 and consumer Categorical 42.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double[] cv$temp$1$var41 = m[1];
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 46 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = ((((0.0 <= st[(index$i$1_2 + 1)]) && (st[(index$i$1_2 + 1)] < cv$temp$1$var41.length))?Math.log(cv$temp$1$var41[st[(index$i$1_2 + 1)]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 54 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// Variable declaration of cv$accumulatedConsumerProbabilities moved.
		// Declaration comment was:
		// Processing sample task 54 of consumer random variable null.
		// 
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		// 
		// cv$temp$2$var49's comment
		// Constructing a random variable input for use later.
		// 
		// Processing random variable 50.
		// 
		// Looking for a path between Sample 46 and consumer Bernoulli 50.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[(i$var37 + 1)], bias[1]) + cv$accumulatedProbabilities);
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var43$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var43$stateProbabilityGlobal[0];
		
		// Unrolled loop
		// 
		// Get a local reference to the scratch space.
		double cv$lseElementValue = cv$var43$stateProbabilityGlobal[1];
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
			cv$logSum = (Math.log((Math.exp((cv$var43$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var43$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Unrolled loop
			// Get a local reference to the scratch space.
			cv$var43$stateProbabilityGlobal[0] = 0.5;
			
			// Get a local reference to the scratch space.
			cv$var43$stateProbabilityGlobal[1] = 0.5;
		} else {
			// Unrolled loop
			// Get a local reference to the scratch space.
			cv$var43$stateProbabilityGlobal[0] = Math.exp((cv$var43$stateProbabilityGlobal[0] - cv$logSum));
			
			// Get a local reference to the scratch space.
			cv$var43$stateProbabilityGlobal[1] = Math.exp((cv$var43$stateProbabilityGlobal[1] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = 2; cv$indexName < cv$var43$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var43$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Get a local reference to the scratch space.
		st[(i$var37 + 1)] = DistributionSampling.sampleCategorical(RNG$, cv$var43$stateProbabilityGlobal);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Allocation of cv$var16$countGlobal for single threaded execution
		cv$var16$countGlobal = new double[2];
		
		// Allocation of cv$var43$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 44. Initially set to the value
		// of putTask 18.
		cv$var43$stateProbabilityGlobal = new double[2];
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
		if(!fixedFlag$sample46) {
			for(int i$var37 = 0; i$var37 < (samples - 1); i$var37 += 1)
				st[(i$var37 + 1)] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var37]]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample54) {
			for(int j = 1; j < samples; j += 1)
				flips[j] = DistributionSampling.sampleBernoulli(RNG$, bias[st[j]]);
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
		if(!fixedFlag$sample46) {
			for(int i$var37 = 0; i$var37 < (samples - 1); i$var37 += 1)
				st[(i$var37 + 1)] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var37]]);
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
		if(!fixedFlag$sample46) {
			for(int i$var37 = 0; i$var37 < (samples - 1); i$var37 += 1)
				st[(i$var37 + 1)] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var37]]);
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
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample46) {
				for(int i$var37 = 0; i$var37 < (samples - 1); i$var37 += 1)
					sample46(i$var37);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample46) {
				for(int i$var37 = (samples - 2); i$var37 >= 0; i$var37 -= 1)
					sample46(i$var37);
			}
			
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
		st[0] = 0;
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
		logProbability$var42 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample46)
			logProbability$var43 = 0.0;
		logProbability$var50 = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample54)
			logProbability$var51 = 0.0;
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
		if(fixedFlag$sample46)
			logProbabilityValue$sample46();
		logProbabilityValue$sample54();
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
		logProbabilityValue$sample46();
		logProbabilityValue$sample54();
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
		logProbabilityValue$sample46();
		logProbabilityValue$sample54();
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
		if(!fixedFlag$sample46) {
			for(int i$var37 = 0; i$var37 < (samples - 1); i$var37 += 1)
				st[(i$var37 + 1)] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var37]]);
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
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart2b(boolean[] flipsMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n        \n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        int[] st = new int[samples];\n        st[0] = 0;\n        \n        boolean[] flips = new boolean[samples];\n\n        for(int i:[0..samples-1))\n            st[i+1] = categorical(m[st[i]]).sample();\n            \n        for(int j:[1..samples))\n            flips[j] = bernoulli(bias[st[j]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
	}
}