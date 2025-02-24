package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMM_Paper$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMM_Paper$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private double[] cv$var28$countGlobal;
	private double[] cv$var31$countGlobal;
	private double[] cv$var52$stateProbabilityGlobal;
	private double[] cv$var70$stateProbabilityGlobal;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedFlag$sample47 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedFlag$sample71 = false;
	private boolean fixedFlag$sample87 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean fixedProbFlag$sample47 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean fixedProbFlag$sample71 = false;
	private boolean fixedProbFlag$sample87 = false;
	private boolean[] flips;
	private double[] initialCoin;
	private int length$measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$initialCoin;
	private double logProbability$m;
	private double logProbability$st;
	private double logProbability$var16;
	private double logProbability$var28;
	private double logProbability$var30;
	private double logProbability$var34;
	private double logProbability$var46;
	private double logProbability$var51;
	private double logProbability$var52;
	private double logProbability$var69;
	private double logProbability$var70;
	private double logProbability$var85;
	private double logProbability$var86;
	private double[][] m;
	private boolean[] measured;
	private int nCoins;
	private int nFlips;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$initialCoin = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMM_Paper$SingleThreadCPU(ExecutionTarget target) {
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
		
		// Unset the fixed probability flag for sample 47 as it depends on bias.
		fixedProbFlag$sample47 = false;
		
		// Unset the fixed probability flag for sample 87 as it depends on bias.
		fixedProbFlag$sample87 = false;
	}

	// Getter for fixedFlag$sample28.
	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	// Setter for fixedFlag$sample28.
	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample28 including if probabilities
		// need to be updated.
		fixedFlag$sample28 = cv$value;
		
		// Should the probability of sample 28 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample28" with its value "cv$value".
		fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
		
		// Should the probability of sample 71 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample28" with its value "cv$value".
		fixedProbFlag$sample71 = (cv$value && fixedProbFlag$sample71);
	}

	// Getter for fixedFlag$sample32.
	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	// Setter for fixedFlag$sample32.
	@Override
	public final void set$fixedFlag$sample32(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample32 including if probabilities
		// need to be updated.
		fixedFlag$sample32 = cv$value;
		
		// Should the probability of sample 32 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample32" with its value "cv$value".
		fixedProbFlag$sample32 = (cv$value && fixedProbFlag$sample32);
		
		// Should the probability of sample 53 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample32" with its value "cv$value".
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
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
		
		// Should the probability of sample 87 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample47" with its value "cv$value".
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
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
		
		// Should the probability of sample 71 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample53" with its value "cv$value".
		fixedProbFlag$sample71 = (cv$value && fixedProbFlag$sample71);
		
		// Should the probability of sample 87 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample53" with its value "cv$value".
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
	}

	// Getter for fixedFlag$sample71.
	@Override
	public final boolean get$fixedFlag$sample71() {
		return fixedFlag$sample71;
	}

	// Setter for fixedFlag$sample71.
	@Override
	public final void set$fixedFlag$sample71(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample71 including if probabilities
		// need to be updated.
		fixedFlag$sample71 = cv$value;
		
		// Should the probability of sample 71 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample71" with its value "cv$value".
		fixedProbFlag$sample71 = (cv$value && fixedProbFlag$sample71);
		
		// Should the probability of sample 87 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample71" with its value "cv$value".
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
	}

	// Getter for fixedFlag$sample87.
	@Override
	public final boolean get$fixedFlag$sample87() {
		return fixedFlag$sample87;
	}

	// Setter for fixedFlag$sample87.
	@Override
	public final void set$fixedFlag$sample87(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample87 including if probabilities
		// need to be updated.
		fixedFlag$sample87 = cv$value;
		
		// Should the probability of sample 87 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample87" with its value "cv$value".
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
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
		
		// Unset the fixed probability flag for sample 87 as it depends on flips.
		fixedProbFlag$sample87 = false;
	}

	// Getter for initialCoin.
	@Override
	public final double[] get$initialCoin() {
		return initialCoin;
	}

	// Setter for initialCoin.
	@Override
	public final void set$initialCoin(double[] cv$value) {
		// Set flags for all the side effects of initialCoin including if probabilities need
		// to be updated.
		// Set initialCoin with flag to mark that it has been set so another array doesn't
		// need to be constructed
		initialCoin = cv$value;
		setFlag$initialCoin = true;
		
		// Unset the fixed probability flag for sample 32 as it depends on initialCoin.
		fixedProbFlag$sample32 = false;
		
		// Unset the fixed probability flag for sample 53 as it depends on initialCoin.
		fixedProbFlag$sample53 = false;
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

	// Getter for logProbability$initialCoin.
	@Override
	public final double get$logProbability$initialCoin() {
		return logProbability$initialCoin;
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
		
		// Unset the fixed probability flag for sample 28 as it depends on m.
		fixedProbFlag$sample28 = false;
		
		// Unset the fixed probability flag for sample 71 as it depends on m.
		fixedProbFlag$sample71 = false;
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

	// Getter for nCoins.
	@Override
	public final int get$nCoins() {
		return nCoins;
	}

	// Setter for nCoins.
	@Override
	public final void set$nCoins(int cv$value) {
		nCoins = cv$value;
	}

	// Getter for nFlips.
	@Override
	public final int get$nFlips() {
		return nFlips;
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
		
		// Unset the fixed probability flag for sample 53 as it depends on st.
		fixedProbFlag$sample53 = false;
		
		// Unset the fixed probability flag for sample 71 as it depends on st.
		fixedProbFlag$sample71 = false;
		
		// Unset the fixed probability flag for sample 87 as it depends on st.
		fixedProbFlag$sample87 = false;
	}

	// Getter for v.
	@Override
	public final double[] get$v() {
		return v;
	}

	// Calculate the probability of the samples represented by sample28 using sampled
	// values.
	private final void logProbabilityValue$sample28() {
		// Determine if we need to calculate the values for sample task 28 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample28) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var27 = 0; var27 < nCoins; var27 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var27], v, nCoins));
			logProbability$var16 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var28 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample28)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample28 = fixedFlag$sample28;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var16 = logProbability$var28;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var28);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var28);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample28)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var28);
		}
	}

	// Calculate the probability of the samples represented by sample32 using sampled
	// values.
	private final void logProbabilityValue$sample32() {
		// Determine if we need to calculate the values for sample task 32 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample32) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(initialCoin, v, nCoins);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var30 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$initialCoin = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample32)
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
			fixedProbFlag$sample32 = fixedFlag$sample32;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var30 = logProbability$initialCoin;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$initialCoin);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample32)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialCoin);
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
			for(int var45 = 0; var45 < nCoins; var45 += 1)
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
			logProbability$bias = (logProbability$bias + logProbability$var46);
			
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

	// Calculate the probability of the samples represented by sample53 using sampled
	// values.
	private final void logProbabilityValue$sample53() {
		// Determine if we need to calculate the values for sample task 53 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample53) {
			// Generating probabilities for sample task
			// The sample value to calculate the probability of generating
			int cv$sampleValue = st[0];
			
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
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < nCoins))?Math.log(initialCoin[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var51 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var52 = cv$distributionAccumulator;
			
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
			fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedFlag$sample32);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var51 = logProbability$var52;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var52);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var52);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample53)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var52);
		}
	}

	// Calculate the probability of the samples represented by sample71 using sampled
	// values.
	private final void logProbabilityValue$sample71() {
		// Determine if we need to calculate the values for sample task 71 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample71) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i = 1; i < nFlips; i += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = st[i];
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < nCoins))?Math.log(m[st[(i - 1)]][cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			logProbability$var69 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var70 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample71)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample71 = ((fixedFlag$sample71 && fixedFlag$sample28) && fixedFlag$sample53);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var69 = logProbability$var70;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var70);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var70);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample71)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var70);
		}
	}

	// Calculate the probability of the samples represented by sample87 using sampled
	// values.
	private final void logProbabilityValue$sample87() {
		// Determine if we need to calculate the values for sample task 87 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample87) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int j = 0; j < nFlips; j += 1)
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
			logProbability$var85 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var86 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample87 = (((fixedFlag$sample87 && fixedFlag$sample47) && fixedFlag$sample53) && fixedFlag$sample71);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var85 = logProbability$var86;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$flips = (logProbability$flips + logProbability$var86);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var86);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var86);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 28 drawn from Dirichlet 16. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample28(int var27) {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < nCoins; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var28$countGlobal[cv$loopIndex] = 0.0;
		
		// Processing random variable 69.
		// 
		// Looking for a path between Sample 28 and consumer Categorical 69.
		for(int i = 1; i < nFlips; i += 1) {
			if((var27 == st[(i - 1)]))
				// Processing sample task 71 of consumer random variable null.
				// 
				// Increment the sample counter with the value sampled by sample task 71 of random
				// variable var69
				// 
				// A local reference to the scratch space.
				cv$var28$countGlobal[st[i]] = (cv$var28$countGlobal[st[i]] + 1.0);
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var28$countGlobal, m[var27], nCoins);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 32 drawn from Dirichlet 30. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample32() {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < nCoins; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var31$countGlobal[cv$loopIndex] = 0.0;
		
		// Processing random variable 51.
		// 
		// Processing sample task 53 of consumer random variable null.
		// 
		// Increment the sample counter with the value sampled by sample task 53 of random
		// variable var51
		// 
		// A local reference to the scratch space.
		cv$var31$countGlobal[st[0]] = (cv$var31$countGlobal[st[0]] + 1.0);
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var31$countGlobal, initialCoin, nCoins);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 47 drawn from Beta 34. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample47(int var45) {
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		
		// Processing random variable 85.
		// 
		// Looking for a path between Sample 47 and consumer Bernoulli 85.
		for(int j = 0; j < nFlips; j += 1) {
			if((var45 == st[j])) {
				// Processing sample task 87 of consumer random variable null.
				// 
				// Include the value sampled by task 87 from random variable var85.
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
	// by sample task 53 drawn from Categorical 51. Inference was performed using variable
	// marginalization.
	private final void sample53() {
		// Variable declaration of cv$numNumStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
		// cv$numNumStates's comment
		// Calculate the number of states to evaluate.
		int cv$numNumStates = Math.max(0, nCoins);
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			// Value of the variable at this index
			st[0] = cv$valuePos;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$initialCoin" with its value "initialCoin".
			// 
			// cv$temp$1$$var186's comment
			// 
			// $var186's comment
			// Constructing a random variable input for use later.
			double cv$accumulatedProbabilities = ((cv$valuePos < nCoins)?Math.log(initialCoin[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Substituted "i" with its value "1".
			if((1 < nFlips))
				// Processing sample task 71 of consumer random variable null.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 71 with the current configuration.
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
				// 
				// cv$temp$3$$var195's comment
				// 
				// $var195's comment
				// Constructing a random variable input for use later.
				// 
				// cv$temp$2$var68's comment
				// Variable declaration of cv$temp$2$var68 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 69.
				// 
				// Looking for a path between Sample 53 and consumer Categorical 69.
				// 
				// Value of the variable at this index
				cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < nCoins))?Math.log(m[cv$valuePos][st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			
			// Substituted "j" with its value "0".
			if((0 < nFlips))
				// Processing sample task 87 of consumer random variable null.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 87 with the current configuration.
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
				// cv$temp$4$var84's comment
				// Variable declaration of cv$temp$4$var84 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 85.
				// 
				// Looking for a path between Sample 53 and consumer Bernoulli 85.
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
			cv$var52$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
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
		double cv$lseMax = cv$var52$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var52$stateProbabilityGlobal[cv$lseIndex];
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
				cv$lseSum = (cv$lseSum + Math.exp((cv$var52$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
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
				cv$var52$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var52$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var52$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var52$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var52$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Get a local reference to the scratch space.
		st[0] = DistributionSampling.sampleCategorical(RNG$, cv$var52$stateProbabilityGlobal, cv$numNumStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 71 drawn from Categorical 69. Inference was performed using variable
	// marginalization.
	private final void sample71(int i) {
		// Variable declaration of cv$numNumStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
		// cv$numNumStates's comment
		// Calculate the number of states to evaluate.
		int cv$numNumStates = Math.max(0, nCoins);
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			// Value of the variable at this index
			st[i] = cv$valuePos;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			// 
			// cv$temp$0$var68's comment
			// Constructing a random variable input for use later.
			double cv$accumulatedProbabilities = ((cv$valuePos < nCoins)?Math.log(m[st[(i - 1)]][cv$valuePos]):Double.NEGATIVE_INFINITY);
			int index$i$1_2 = (i + 1);
			if((index$i$1_2 < nFlips))
				// Processing sample task 71 of consumer random variable null.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 71 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$3$$var226's comment
				// 
				// $var226's comment
				// Constructing a random variable input for use later.
				// 
				// cv$temp$2$var68's comment
				// Variable declaration of cv$temp$2$var68 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 69.
				// 
				// Looking for a path between Sample 71 and consumer Categorical 69.
				// 
				// Value of the variable at this index
				cv$accumulatedProbabilities = ((((0.0 <= st[index$i$1_2]) && (st[index$i$1_2] < nCoins))?Math.log(m[cv$valuePos][st[index$i$1_2]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 87 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Processing sample task 87 of consumer random variable null.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "j" with its value "i".
			// 
			// cv$temp$4$var84's comment
			// Variable declaration of cv$temp$4$var84 moved.
			// 
			// Constructing a random variable input for use later.
			// 
			// Processing random variable 85.
			// 
			// Looking for a path between Sample 71 and consumer Bernoulli 85.
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
			cv$var70$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
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
		double cv$lseMax = cv$var70$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var70$stateProbabilityGlobal[cv$lseIndex];
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
				cv$lseSum = (cv$lseSum + Math.exp((cv$var70$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
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
				cv$var70$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var70$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var70$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var70$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var70$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		// 
		// Get a local reference to the scratch space.
		st[i] = DistributionSampling.sampleCategorical(RNG$, cv$var70$stateProbabilityGlobal, cv$numNumStates);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var28$countGlobal
		// 
		// Allocation of cv$var28$countGlobal for single threaded execution
		cv$var28$countGlobal = new double[nCoins];
		
		// Constructor for cv$var31$countGlobal
		// 
		// Allocation of cv$var31$countGlobal for single threaded execution
		cv$var31$countGlobal = new double[nCoins];
		
		// Constructor for cv$var52$stateProbabilityGlobal
		// 
		// Allocation of cv$var52$stateProbabilityGlobal for single threaded execution
		cv$var52$stateProbabilityGlobal = new double[nCoins];
		
		// Allocation of cv$var70$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 69. Initially set to the value
		// of putTask 29.
		cv$var70$stateProbabilityGlobal = new double[nCoins];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for v
		v = new double[nCoins];
		
		// If m has not been set already allocate space.
		if(!setFlag$m) {
			// Constructor for m
			m = new double[nCoins][];
			for(int var27 = 0; var27 < nCoins; var27 += 1)
				m[var27] = new double[nCoins];
		}
		
		// If initialCoin has not been set already allocate space.
		if(!setFlag$initialCoin)
			// Constructor for initialCoin
			initialCoin = new double[nCoins];
		
		// If bias has not been set already allocate space.
		if(!setFlag$bias)
			// Constructor for bias
			bias = new double[nCoins];
		
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
		if(!fixedFlag$sample28) {
			for(int var27 = 0; var27 < nCoins; var27 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, nCoins, m[var27]);
		}
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, nCoins, initialCoin);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample47) {
			for(int var45 = 0; var45 < nCoins; var45 += 1)
				bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialCoin, nCoins);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample71) {
			for(int i = 1; i < nFlips; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]], nCoins);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample87) {
			for(int j = 0; j < nFlips; j += 1)
				flips[j] = DistributionSampling.sampleBernoulli(RNG$, bias[st[j]]);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample28) {
			for(int var27 = 0; var27 < nCoins; var27 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, nCoins, m[var27]);
		}
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, nCoins, initialCoin);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample47) {
			for(int var45 = 0; var45 < nCoins; var45 += 1)
				bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialCoin, nCoins);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample71) {
			for(int i = 1; i < nFlips; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]], nCoins);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample28) {
			for(int var27 = 0; var27 < nCoins; var27 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, nCoins, m[var27]);
		}
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, nCoins, initialCoin);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample47) {
			for(int var45 = 0; var45 < nCoins; var45 += 1)
				bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialCoin, nCoins);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample71) {
			for(int i = 1; i < nFlips; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]], nCoins);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample28) {
				for(int var27 = 0; var27 < nCoins; var27 += 1)
					sample28(var27);
			}
			if(!fixedFlag$sample32)
				sample32();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample47) {
				for(int var45 = 0; var45 < nCoins; var45 += 1)
					sample47(var45);
			}
			if(!fixedFlag$sample53)
				sample53();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample71) {
				for(int i = 1; i < nFlips; i += 1)
					sample71(i);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample71) {
				for(int i = (nFlips - 1); i >= 1; i -= 1)
					sample71(i);
			}
			if(!fixedFlag$sample53)
				sample53();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample47) {
				for(int var45 = (nCoins - 1); var45 >= 0; var45 -= 1)
					sample47(var45);
			}
			if(!fixedFlag$sample32)
				sample32();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample28) {
				for(int var27 = (nCoins - 1); var27 >= 0; var27 -= 1)
					sample28(var27);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		for(int var13 = 0; var13 < nCoins; var13 += 1)
			v[var13] = 0.1;
		nFlips = length$measured;
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
		logProbability$var16 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var28 = 0.0;
		logProbability$var30 = 0.0;
		if(!fixedProbFlag$sample32)
			logProbability$initialCoin = 0.0;
		logProbability$var34 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample47)
			logProbability$var46 = 0.0;
		logProbability$var51 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$var52 = 0.0;
		logProbability$var69 = 0.0;
		if(!fixedProbFlag$sample71)
			logProbability$var70 = 0.0;
		logProbability$var85 = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample87)
			logProbability$var86 = 0.0;
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
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(fixedFlag$sample47)
			logProbabilityValue$sample47();
		if(fixedFlag$sample53)
			logProbabilityValue$sample53();
		if(fixedFlag$sample71)
			logProbabilityValue$sample71();
		logProbabilityValue$sample87();
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
		logProbabilityValue$sample28();
		logProbabilityValue$sample32();
		logProbabilityValue$sample47();
		logProbabilityValue$sample53();
		logProbabilityValue$sample71();
		logProbabilityValue$sample87();
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
		logProbabilityValue$sample28();
		logProbabilityValue$sample32();
		logProbabilityValue$sample47();
		logProbabilityValue$sample53();
		logProbabilityValue$sample71();
		logProbabilityValue$sample87();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample28) {
			for(int var27 = 0; var27 < nCoins; var27 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, nCoins, m[var27]);
		}
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, nCoins, initialCoin);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample47) {
			for(int var45 = 0; var45 < nCoins; var45 += 1)
				bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialCoin, nCoins);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample71) {
			for(int i = 1; i < nFlips; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]], nCoins);
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
		     + "model HMM_Paper(boolean[] measured, int nCoins) {\n"
		     + "  //Construct a transistion matrix m.\n"
		     + "  double[] v = new double[nCoins] <~ 0.1;\n"
		     + "  double[][] m = dirichlet(v).sample(nCoins);\n"
		     + "  \n"
		     + "  //Construct weighting for which coin to start at.\n"
		     + "  double[] initialCoin = dirichlet(v).sample;\n"
		     + "    \n"
		     + "  //Construct biases for each coin    \n"
		     + "  double[] bias = beta(1.0, 1.0).sample(nCoins);\n"
		     + "\n"
		     + "  //Allocate space to record which coin is flipped\n"
		     + "  int nFlips = measured.length;\n"
		     + "  int[] st = new int[nFlips];\n"
		     + "\n"
		     + "  //Calculate the movements between coins        \n"
		     + "  st[0] = categorical(initialCoin).sample();\n"
		     + "  for (int i: [1..nFlips) )\n"
		     + "    st[i] = categorical(m[st[i - 1]]).sample();\n"
		     + "\n"
		     + "  //Flip the coins\n"
		     + "  boolean[] flips = new boolean[nFlips];\n"
		     + "  for (int j: [0..nFlips) )\n"
		     + "    flips[j] = bernoulli(bias[st[j]]).sample();\n"
		     + "    \n"
		     + "  //Assert that the flips match the measured data.\n"
		     + "  flips.observe(measured);\n"
		     + "}\n"
		     + "";
	}
}