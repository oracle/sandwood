package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart1$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMTestPart1$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private double[] calculationVariable$var14$CountGlobal;
	private double[] calculationVariable$var28$StateProbabilityGlobal;
	private boolean fixedFlag$sample14 = false;
	private boolean fixedFlag$sample23 = false;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedProbFlag$sample14 = false;
	private boolean fixedProbFlag$sample23 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean flip;
	private boolean flipMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flip;
	private double logProbability$m;
	private double logProbability$st;
	private double logProbability$var14;
	private double logProbability$var18;
	private double logProbability$var23;
	private double logProbability$var27;
	private double logProbability$var30;
	private double logProbability$var9;
	private double[][] m;
	private boolean setFlag$bias = false;
	private boolean setFlag$m = false;
	private int st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart1$SingleThreadCPU(ExecutionTarget target) {
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

	// Getter for fixedFlag$sample14.
	@Override
	public final boolean get$fixedFlag$sample14() {
		return fixedFlag$sample14;
	}

	// Setter for fixedFlag$sample14.
	@Override
	public final void set$fixedFlag$sample14(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample14 including if probabilities
		// need to be updated.
		fixedFlag$sample14 = calculationVariable$value;
		
		// Should the probability of sample 14 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample14" with its value "calculationVariable$value".
		fixedProbFlag$sample14 = (calculationVariable$value && fixedProbFlag$sample14);
		
		// Should the probability of sample 28 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample14" with its value "calculationVariable$value".
		fixedProbFlag$sample28 = (calculationVariable$value && fixedProbFlag$sample28);
	}

	// Getter for fixedFlag$sample23.
	@Override
	public final boolean get$fixedFlag$sample23() {
		return fixedFlag$sample23;
	}

	// Setter for fixedFlag$sample23.
	@Override
	public final void set$fixedFlag$sample23(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample23 including if probabilities
		// need to be updated.
		fixedFlag$sample23 = calculationVariable$value;
		
		// Should the probability of sample 23 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample23" with its value "calculationVariable$value".
		fixedProbFlag$sample23 = (calculationVariable$value && fixedProbFlag$sample23);
		
		// Should the probability of sample 31 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample23" with its value "calculationVariable$value".
		fixedProbFlag$sample31 = (calculationVariable$value && fixedProbFlag$sample31);
	}

	// Getter for fixedFlag$sample28.
	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	// Setter for fixedFlag$sample28.
	@Override
	public final void set$fixedFlag$sample28(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample28 including if probabilities
		// need to be updated.
		fixedFlag$sample28 = calculationVariable$value;
		
		// Should the probability of sample 28 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample28" with its value "calculationVariable$value".
		fixedProbFlag$sample28 = (calculationVariable$value && fixedProbFlag$sample28);
		
		// Should the probability of sample 31 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample28" with its value "calculationVariable$value".
		fixedProbFlag$sample31 = (calculationVariable$value && fixedProbFlag$sample31);
	}

	// Getter for fixedFlag$sample31.
	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	// Setter for fixedFlag$sample31.
	@Override
	public final void set$fixedFlag$sample31(boolean calculationVariable$value) {
		// Set flags for all the side effects of fixedFlag$sample31 including if probabilities
		// need to be updated.
		fixedFlag$sample31 = calculationVariable$value;
		
		// Should the probability of sample 31 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample31" with its value "calculationVariable$value".
		fixedProbFlag$sample31 = (calculationVariable$value && fixedProbFlag$sample31);
	}

	// Getter for flip.
	@Override
	public final boolean get$flip() {
		return flip;
	}

	// Setter for flip.
	@Override
	public final void set$flip(boolean calculationVariable$value) {
		flip = calculationVariable$value;
	}

	// Getter for flipMeasured.
	@Override
	public final boolean get$flipMeasured() {
		return flipMeasured;
	}

	// Setter for flipMeasured.
	@Override
	public final void set$flipMeasured(boolean calculationVariable$value) {
		flipMeasured = calculationVariable$value;
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

	// Getter for logProbability$flip.
	@Override
	public final double get$logProbability$flip() {
		return logProbability$flip;
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

	// Getter for st.
	@Override
	public final int get$st() {
		return st;
	}

	// Setter for st.
	@Override
	public final void set$st(int calculationVariable$value) {
		st = calculationVariable$value;
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

	// Calculate the probability of the samples represented by sample14 using sampled
	// values.
	private final void logProbabilityValue$sample14() {
		// Determine if we need to calculate the values for sample task 14 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample14) {
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
			logProbability$var9 = calculationVariable$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var14 = calculationVariable$sampleAccumulator;
			
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
			if(fixedFlag$sample14)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample14 = fixedFlag$sample14;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var9 = logProbability$var14;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var14);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var14);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample14)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var14);
		}
	}

	// Calculate the probability of the samples represented by sample23 using sampled
	// values.
	private final void logProbabilityValue$sample23() {
		// Determine if we need to calculate the values for sample task 23 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample23) {
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
			logProbability$var18 = calculationVariable$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var23 = calculationVariable$sampleAccumulator;
			
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
			if(fixedFlag$sample23)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + calculationVariable$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample23 = fixedFlag$sample23;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var18 = logProbability$var23;
			
			// Update the variable probability
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$bias = (logProbability$bias + logProbability$var23);
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var23);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample23)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var23);
		}
	}

	// Calculate the probability of the samples represented by sample28 using sampled
	// values.
	private final void logProbabilityValue$sample28() {
		// Determine if we need to calculate the values for sample task 28 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample28) {
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
			double calculationVariable$distributionAccumulator = DistributionSampling.logProbabilityCategorical(st, m[0]);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var27 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$st = calculationVariable$distributionAccumulator;
			
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
			if(fixedFlag$sample28)
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
			fixedProbFlag$sample28 = (fixedFlag$sample28 && fixedFlag$sample14);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var27 = logProbability$st;
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$st);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample28)
				// Variable declaration of calculationVariable$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$st);
		}
	}

	// Calculate the probability of the samples represented by sample31 using sampled
	// values.
	private final void logProbabilityValue$sample31() {
		// Determine if we need to calculate the values for sample task 31 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample31) {
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
			double calculationVariable$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flipMeasured, bias[st]);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var30 = calculationVariable$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$flip = calculationVariable$distributionAccumulator;
			
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
			fixedProbFlag$sample31 = ((fixedFlag$sample31 && fixedFlag$sample23) && fixedFlag$sample28);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var30 = logProbability$flip;
			
			// Add probability to model
			// 
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$flip);
			
			// Variable declaration of calculationVariable$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$flip);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 14 drawn from Dirichlet 9. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample14(int var13) {
		// A local reference to the scratch space.
		calculationVariable$var14$CountGlobal[0] = 0.0;
		
		// A local reference to the scratch space.
		calculationVariable$var14$CountGlobal[1] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var13 == 0))
			// Processing random variable 27.
			// 
			// Looking for a path between Sample 14 and consumer Categorical 27.
			// 
			// Processing sample task 28 of consumer random variable null.
			// 
			// Increment the sample counter with the value sampled by sample task 28 of random
			// variable var27
			// 
			// A local reference to the scratch space.
			calculationVariable$var14$CountGlobal[st] = (calculationVariable$var14$CountGlobal[st] + 1.0);
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into calculationVariable$TargetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, calculationVariable$var14$CountGlobal, m[var13]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 23 drawn from Beta 18. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample23(int var22) {
		// Local variable to record the number of true samples.
		int calculationVariable$Sum = 0;
		
		// Local variable to record the number of samples.
		int calculationVariable$Count = 0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var22 == st)) {
			// Processing random variable 30.
			// 
			// Looking for a path between Sample 23 and consumer Bernoulli 30.
			// 
			// Processing sample task 31 of consumer random variable null.
			// 
			// Include the value sampled by task 31 from random variable var30.
			// Increment the number of samples.
			// 
			// Local variable to record the number of samples.
			calculationVariable$Count = 1;
			
			// If the sample value was positive increase the count
			if(flipMeasured)
				// Local variable to record the number of true samples.
				calculationVariable$Sum = 1;
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		bias[var22] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, calculationVariable$Sum, calculationVariable$Count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 28 drawn from Categorical 27. Inference was performed using variable
	// marginalization.
	private final void sample28() {
		// Write out the new value of the sample.
		// 
		// Variable declaration of calculationVariable$CurrentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "calculationVariable$valuePos" with its value "0".
		st = 0;
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		// 
		// Variable declaration of calculationVariable$AccumulatedProbabilities moved.
		// Declaration comment was:
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// calculationVariable$temp$0$var26's comment
		// Constructing a random variable input for use later.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 31 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// calculationVariable$temp$0$var26's comment
		// Constructing a random variable input for use later.
		// 
		// Variable declaration of calculationVariable$AccumulatedConsumerProbabilities moved.
		// Declaration comment was:
		// Processing sample task 31 of consumer random variable null.
		// 
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		// 
		// Substituted "calculationVariable$temp$1$var29" with its value "var29".
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
		calculationVariable$var28$StateProbabilityGlobal[0] = (DistributionSampling.logProbabilityBernoulli(flipMeasured, bias[0]) + DistributionSampling.logProbabilityCategorical(0, m[0]));
		
		// Write out the new value of the sample.
		// 
		// Variable declaration of calculationVariable$CurrentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "calculationVariable$valuePos" with its value "1".
		st = 1;
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		// 
		// Variable declaration of calculationVariable$AccumulatedProbabilities moved.
		// Declaration comment was:
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// calculationVariable$temp$0$var26's comment
		// Constructing a random variable input for use later.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 31 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// calculationVariable$temp$0$var26's comment
		// Constructing a random variable input for use later.
		// 
		// Variable declaration of calculationVariable$AccumulatedConsumerProbabilities moved.
		// Declaration comment was:
		// Processing sample task 31 of consumer random variable null.
		// 
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		// 
		// Substituted "calculationVariable$temp$1$var29" with its value "var29".
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
		calculationVariable$var28$StateProbabilityGlobal[1] = (DistributionSampling.logProbabilityBernoulli(flipMeasured, bias[1]) + DistributionSampling.logProbabilityCategorical(1, m[0]));
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double calculationVariable$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double calculationVariable$lseMax = calculationVariable$var28$StateProbabilityGlobal[0];
		
		// Find max value.
		// 
		// Get a local reference to the scratch space.
		for(int calculationVariable$lseIndex=1; calculationVariable$lseIndex<calculationVariable$var28$StateProbabilityGlobal.length; calculationVariable$lseIndex+=1) {
			// Get a local reference to the scratch space.
			double calculationVariable$lseElementValue = calculationVariable$var28$StateProbabilityGlobal[calculationVariable$lseIndex];
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
			for(int calculationVariable$lseIndex=0; calculationVariable$lseIndex<calculationVariable$var28$StateProbabilityGlobal.length; calculationVariable$lseIndex+=1)
				// Get a local reference to the scratch space.
				calculationVariable$lseSum = (calculationVariable$lseSum + Math.exp((calculationVariable$var28$StateProbabilityGlobal[calculationVariable$lseIndex] - calculationVariable$lseMax)));
			
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
			for(int calculationVariable$indexName=0; calculationVariable$indexName<calculationVariable$var28$StateProbabilityGlobal.length; calculationVariable$indexName+=1)
				// Get a local reference to the scratch space.
				calculationVariable$var28$StateProbabilityGlobal[calculationVariable$indexName] = (1.0 / calculationVariable$var28$StateProbabilityGlobal.length);
		} else {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int calculationVariable$indexName=0; calculationVariable$indexName<calculationVariable$var28$StateProbabilityGlobal.length; calculationVariable$indexName+=1)
				// Get a local reference to the scratch space.
				calculationVariable$var28$StateProbabilityGlobal[calculationVariable$indexName] = Math.exp((calculationVariable$var28$StateProbabilityGlobal[calculationVariable$indexName] - calculationVariable$logSum));
		}
		
		// Write out the new value of the sample.
		// 
		// Get a local reference to the scratch space.
		st = DistributionSampling.sampleCategorical(RNG$, calculationVariable$var28$StateProbabilityGlobal);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Allocation of calculationVariable$var14$CountGlobal for single threaded execution
		// 
		// Unrolled loop
		// 
		// Calculate the longest array this random variable could produce and allocate an
		// array large enough to handle this.
		calculationVariable$var14$CountGlobal = new double[2];
		
		// Allocation of calculationVariable$var28$StateProbabilityGlobal for single threaded
		// execution
		// 
		// Variable to record the maximum value of Task Get 26. Initially set to the value
		// of putTask 15.
		calculationVariable$var28$StateProbabilityGlobal = new double[2];
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
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample14) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample23) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample28)
			st = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample31)
			flip = DistributionSampling.sampleBernoulli(RNG$, bias[st]);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample14) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample23) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample28)
			st = DistributionSampling.sampleCategorical(RNG$, m[0]);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample14) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample23) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample28)
			st = DistributionSampling.sampleCategorical(RNG$, m[0]);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample14) {
				sample14(0);
				sample14(1);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample23) {
				sample23(0);
				sample23(1);
			}
			if(!fixedFlag$sample28)
				sample28();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!fixedFlag$sample28)
				sample28();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample23) {
				sample23(1);
				sample23(0);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample14) {
				sample14(1);
				sample14(0);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		// Substituted "i" with its value "0".
		v[0] = 0.1;
		
		// Substituted "i" with its value "1".
		v[1] = 0.1;
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
		logProbability$var9 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample14)
			logProbability$var14 = 0.0;
		logProbability$var18 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample23)
			logProbability$var23 = 0.0;
		logProbability$var27 = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$st = 0.0;
		logProbability$var30 = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$flip = 0.0;
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
		if(fixedFlag$sample14)
			logProbabilityValue$sample14();
		if(fixedFlag$sample23)
			logProbabilityValue$sample23();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		logProbabilityValue$sample31();
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
		logProbabilityValue$sample14();
		logProbabilityValue$sample23();
		logProbabilityValue$sample28();
		logProbabilityValue$sample31();
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
		logProbabilityValue$sample14();
		logProbabilityValue$sample23();
		logProbabilityValue$sample28();
		logProbabilityValue$sample31();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample14) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample23) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample28)
			st = DistributionSampling.sampleCategorical(RNG$, m[0]);
		
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart1(boolean flipMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n        \n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int st = categorical(m[0]).sample();\n        boolean flip = bernoulli(bias[st]).sample();\n\n        flip.observe(flipMeasured);\n}\n";
	}
}