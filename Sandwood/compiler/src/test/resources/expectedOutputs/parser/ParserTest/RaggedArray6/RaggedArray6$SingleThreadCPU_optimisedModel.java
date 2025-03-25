package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray6$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements RaggedArray6$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] a;
	private double[] b;
	private double[] cv$var45$stateProbabilityGlobal;
	private double[] d;
	private boolean fixedFlag$sample47 = false;
	private boolean fixedFlag$sample50 = false;
	private boolean fixedProbFlag$sample47 = false;
	private boolean fixedProbFlag$sample50 = false;
	private boolean fixedProbFlag$sample65 = false;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$d;
	private double logProbability$obs;
	private double logProbability$var44;
	private double logProbability$var47;
	private double logProbability$var50;
	private double logProbability$var63;
	private double logProbability$y;
	private boolean[] obs;
	private boolean[] obs_measured;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray6$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for a.
	@Override
	public final double[][] get$a() {
		return a;
	}

	// Getter for b.
	@Override
	public final double[] get$b() {
		return b;
	}

	// Getter for d.
	@Override
	public final double[] get$d() {
		return d;
	}

	// Setter for d.
	@Override
	public final void set$d(double[] cv$value) {
		// Set flags for all the side effects of d including if probabilities need to be updated.
		// Set d
		d = cv$value;
		
		// Unset the fixed probability flag for sample 50 as it depends on d.
		fixedProbFlag$sample50 = false;
		
		// Unset the fixed probability flag for sample 65 as it depends on d.
		fixedProbFlag$sample65 = false;
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
		
		// Should the probability of sample 50 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample47" with its value "cv$value".
		fixedProbFlag$sample50 = (cv$value && fixedProbFlag$sample50);
		
		// Should the probability of sample 65 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample47" with its value "cv$value".
		fixedProbFlag$sample65 = (cv$value && fixedProbFlag$sample65);
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
		
		// Should the probability of sample 65 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample50" with its value "cv$value".
		fixedProbFlag$sample65 = (cv$value && fixedProbFlag$sample65);
	}

	// Getter for length$obs_measured.
	@Override
	public final int get$length$obs_measured() {
		return length$obs_measured;
	}

	// Setter for length$obs_measured.
	@Override
	public final void set$length$obs_measured(int cv$value) {
		length$obs_measured = cv$value;
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

	// Getter for logProbability$d.
	@Override
	public final double get$logProbability$d() {
		return logProbability$d;
	}

	// Getter for logProbability$obs.
	@Override
	public final double get$logProbability$obs() {
		return logProbability$obs;
	}

	// Getter for logProbability$y.
	@Override
	public final double get$logProbability$y() {
		return logProbability$y;
	}

	// Getter for obs.
	@Override
	public final boolean[] get$obs() {
		return obs;
	}

	// Getter for obs_measured.
	@Override
	public final boolean[] get$obs_measured() {
		return obs_measured;
	}

	// Setter for obs_measured.
	@Override
	public final void set$obs_measured(boolean[] cv$value) {
		// Set obs_measured
		obs_measured = cv$value;
	}

	// Getter for y.
	@Override
	public final int get$y() {
		return y;
	}

	// Setter for y.
	@Override
	public final void set$y(int cv$value) {
		// Set flags for all the side effects of y including if probabilities need to be updated.
		y = cv$value;
		
		// Unset the fixed probability flag for sample 47 as it depends on y.
		fixedProbFlag$sample47 = false;
		
		// Unset the fixed probability flag for sample 50 as it depends on y.
		fixedProbFlag$sample50 = false;
		
		// Unset the fixed probability flag for sample 65 as it depends on y.
		fixedProbFlag$sample65 = false;
	}

	// Calculate the probability of the samples represented by sample47 using sampled
	// values.
	private final void logProbabilityValue$sample47() {
		// Determine if we need to calculate the values for sample task 47 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample47) {
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
			double cv$distributionAccumulator = (((0.0 <= y) && (y < 2))?Math.log(b[y]):Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var44 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$y = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample47)
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
			fixedProbFlag$sample47 = fixedFlag$sample47;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var44 = logProbability$y;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$y);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample47)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$y);
		}
	}

	// Calculate the probability of the samples represented by sample50 using sampled
	// values.
	private final void logProbabilityValue$sample50() {
		// Determine if we need to calculate the values for sample task 50 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample50) {
			// Generating probabilities for sample task
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$48_4 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$48_4 = 3;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$48_4 = 2;
			
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(d, a[y], lengthCV$a$48_4);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var47 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$d = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample50)
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
			fixedProbFlag$sample50 = (fixedFlag$sample50 && fixedFlag$sample47);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var47 = logProbability$d;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$d);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample50)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$d);
		}
	}

	// Calculate the probability of the samples represented by sample65 using sampled
	// values.
	private final void logProbabilityValue$sample65() {
		// Determine if we need to calculate the values for sample task 65 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample65) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var62 = 0; var62 < length$obs_measured; var62 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(obs[var62], d[y]));
			logProbability$var50 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var63 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$obs = (logProbability$obs + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample65 = (fixedFlag$sample47 && fixedFlag$sample50);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var50 = logProbability$var63;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$obs = (logProbability$obs + logProbability$var63);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var63);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var63);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 47 drawn from Categorical 44. Inference was performed using variable
	// marginalization.
	private final void sample47() {
		{
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			y = 0;
			
			// Variable declaration of cv$accumulatedProbabilities moved.
			// Declaration comment was:
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$b" with its value "b".
			// 
			// Substituted "cv$valuePos" with its value "0".
			// 
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 50 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$b" with its value "b".
			// 
			// Substituted "cv$valuePos" with its value "0".
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Processing sample task 50 of consumer random variable null.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// cv$temp$3$$var137's comment
			// 
			// $var137's comment
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityDirichlet(d, a[0], 2) + Math.log(b[0]));
			
			// Processing sample task 65 of consumer random variable null.
			for(int var62 = 0; var62 < length$obs_measured; var62 += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 65 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$4$var49's comment
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 50.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var62], d[0]) + cv$accumulatedProbabilities);
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var45$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		y = 1;
		
		// Variable declaration of cv$accumulatedProbabilities moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$b" with its value "b".
		// 
		// Substituted "cv$valuePos" with its value "1".
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 50 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$b" with its value "b".
		// 
		// Substituted "cv$valuePos" with its value "1".
		// 
		// Variable declaration of cv$accumulatedConsumerProbabilities moved.
		// Declaration comment was:
		// Processing sample task 50 of consumer random variable null.
		// 
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		// 
		// cv$temp$3$$var137's comment
		// 
		// $var137's comment
		// Constructing a random variable input for use later.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityDirichlet(d, a[1], 3) + Math.log(b[1]));
		
		// Processing sample task 65 of consumer random variable null.
		for(int var62 = 0; var62 < length$obs_measured; var62 += 1)
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 65 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// cv$temp$4$var49's comment
			// Constructing a random variable input for use later.
			// 
			// Processing random variable 50.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var62], d[1]) + cv$accumulatedProbabilities);
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var45$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var45$stateProbabilityGlobal[0];
		
		// Unrolled loop
		// 
		// Get a local reference to the scratch space.
		double cv$lseElementValue = cv$var45$stateProbabilityGlobal[1];
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
			cv$logSum = (Math.log((Math.exp((cv$var45$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var45$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Unrolled loop
			// Get a local reference to the scratch space.
			cv$var45$stateProbabilityGlobal[0] = 0.5;
			
			// Get a local reference to the scratch space.
			cv$var45$stateProbabilityGlobal[1] = 0.5;
		} else {
			// Unrolled loop
			// Get a local reference to the scratch space.
			cv$var45$stateProbabilityGlobal[0] = Math.exp((cv$var45$stateProbabilityGlobal[0] - cv$logSum));
			
			// Get a local reference to the scratch space.
			cv$var45$stateProbabilityGlobal[1] = Math.exp((cv$var45$stateProbabilityGlobal[1] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = 2; cv$indexName < cv$var45$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var45$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the new value of the sample.
		// 
		// cv$numNumStates's comment
		// variable marginalization
		y = DistributionSampling.sampleCategorical(RNG$, cv$var45$stateProbabilityGlobal, 2);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 50 drawn from Dirichlet 47. Inference was performed using Metropolis-Hastings.
	private final void sample50() {
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the probability of the random variable generating the original sampled
		// value.
		double cv$originalProbability;
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$48_2 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == y))
			lengthCV$a$48_2 = 3;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == y))
			lengthCV$a$48_2 = 2;
		
		// Pick a value in the array to adjust.
		int cv$indexToChange = (int)((double)lengthCV$a$48_2 * DistributionSampling.sampleUniform(RNG$));
		
		// Pick how much the value should be moved by. Initially this value is proposed as
		// a ratio of the current magnitude of the value, we will check to make sure the adjustment
		// will not make this value too large or other values too small and adjust if required
		// before it is applied.
		double cv$movementRatio = ((DistributionSampling.sampleBeta(RNG$, 5, 5) * 1.9999) - 1);
		
		// Calculate how much we are going to move the array index cv$indexToChange the by.
		// 
		// Allocate space for the proposed change to be stored as an absolute value
		double cv$proposedDifference;
		
		// Test if we are increasing or decreasing the value at the index. For each case calculate
		// the maximum valid adjustment.
		if((cv$movementRatio < 0))
			// The maximum reduction of the array at the index without going below 0 is the value
			// of the array at that index.
			// 
			// A reference local to the function for the sample variable.
			cv$proposedDifference = d[cv$indexToChange];
		else {
			// Calculate the maximum magnitude of the proposed index change.
			// Initially set the maximum to the amount that the value we are changing could increase
			// without exceeding 1
			// 
			// A reference local to the function for the sample variable.
			cv$proposedDifference = (1.0 - d[cv$indexToChange]);
			
			// For the array values up to the index we are going to change calculate the maximum
			// move possible.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1) {
				// Calculate the maximum change value that the value at array index cv$loopIndex could
				// support. Based on moving all other values by an equal amount.
				// 
				// A reference local to the function for the sample variable.
				double cv$temp = (d[cv$loopIndex] * (lengthCV$a$48_2 - 1));
				
				// If the maximum move is less than the proposed move update the move size.
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
			
			// For the array values after the index we are going to change calculate the maximum
			// move possible.
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < lengthCV$a$48_2; cv$loopIndex += 1) {
				// Calculate the maximum change value that the value at array index cv$loopIndex could
				// support. Based on moving all other values by an equal amount.
				// 
				// A reference local to the function for the sample variable.
				double cv$temp = (d[cv$loopIndex] * (lengthCV$a$48_2 - 1));
				
				// If this is less than the proposed increase, change the proposed increase to this
				// value.
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
		}
		
		// Multiply the maximum adjustment by the adjustment ratio to get the actual adjustment
		// we are going to make.
		cv$proposedDifference = (cv$movementRatio * cv$proposedDifference);
		
		// Calculate how much each of the other indexes needs to be adjusted by in order to
		// maintain that the sum of the indexes is 1.
		double cv$rebalanceValue = (cv$proposedDifference / (lengthCV$a$48_2 - 1));
		{
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$48_3 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$48_3 = 3;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$48_3 = 2;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// A reference local to the function for the sample variable.
			// 
			// cv$temp$1$$var157's comment
			// 
			// $var157's comment
			// Constructing a random variable input for use later.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(d, a[y], lengthCV$a$48_3);
			
			// Processing random variable 50.
			// 
			// Processing sample task 65 of consumer random variable null.
			for(int var62 = 0; var62 < length$obs_measured; var62 += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 65 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$2$var49's comment
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var62], d[y]) + cv$accumulatedProbabilities);
			
			// Initialize an accumulator to take the product of all the distribution probabilities
			// in log space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Update Sample and intermediate values
		// 
		// Update the sample value
		// 
		// Update all the indexes up to the index selected.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
			// A reference local to the function for the sample variable.
			d[cv$loopIndex] = (d[cv$loopIndex] - cv$rebalanceValue);
		
		// Update the selected index.
		// 
		// A reference local to the function for the sample variable.
		d[cv$indexToChange] = (d[cv$indexToChange] + cv$proposedDifference);
		
		// Update all the indexes after the index we selected.
		for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < lengthCV$a$48_2; cv$loopIndex += 1)
			// A reference local to the function for the sample variable.
			d[cv$loopIndex] = (d[cv$loopIndex] - cv$rebalanceValue);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$48_3 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == y))
			lengthCV$a$48_3 = 3;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == y))
			lengthCV$a$48_3 = 2;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// A reference local to the function for the sample variable.
		// 
		// cv$temp$1$$var157's comment
		// 
		// $var157's comment
		// Constructing a random variable input for use later.
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(d, a[y], lengthCV$a$48_3);
		
		// Processing random variable 50.
		// 
		// Processing sample task 65 of consumer random variable null.
		for(int var62 = 0; var62 < length$obs_measured; var62 += 1)
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 65 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// cv$temp$2$var49's comment
			// Constructing a random variable input for use later.
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var62], d[y]) + cv$accumulatedProbabilities);
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// Variable declaration of cv$proposedProbability moved.
		// Declaration comment was:
		// The probability of the random variable generating the new sample value.
		// 
		// Initialize an accumulator to take the product of all the distribution probabilities
		// in log space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		if(((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$)))) {
			// If it is not revert the sample value and intermediates to their original values.
			// 
			// Set the sample value
			// 
			// Calculate the new sample value
			// 
			// Update the sample value
			// Update all the indexes up to the index selected.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
				// A reference local to the function for the sample variable.
				d[cv$loopIndex] = (d[cv$loopIndex] + cv$rebalanceValue);
			
			// Update the selected index.
			// 
			// A reference local to the function for the sample variable.
			d[cv$indexToChange] = (d[cv$indexToChange] - cv$proposedDifference);
			
			// Update all the indexes after the index we selected.
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < lengthCV$a$48_2; cv$loopIndex += 1)
				// A reference local to the function for the sample variable.
				d[cv$loopIndex] = (d[cv$loopIndex] + cv$rebalanceValue);
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Constructor for cv$var45$stateProbabilityGlobal
		// 
		// Allocate scratch space.
		// 
		// Allocation of cv$var45$stateProbabilityGlobal for single threaded execution
		cv$var45$stateProbabilityGlobal = new double[2];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for a
		a = new double[2][];
		a[0] = new double[2];
		a[1] = new double[3];
		
		// Constructor for b
		b = new double[2];
		
		// If d has not been set already allocate space.
		if(!fixedFlag$sample50) {
			// Constructor for d
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$48_0 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$48_0 = 3;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$48_0 = 2;
			d = new double[lengthCV$a$48_0];
		}
		
		// Constructor for obs
		obs = new boolean[length$obs_measured];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample47)
			y = DistributionSampling.sampleCategorical(RNG$, b, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample50) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$48_5 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$48_5 = 3;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$48_5 = 2;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$48_5, d);
		}
		for(int var62 = 0; var62 < length$obs_measured; var62 += 1)
			obs[var62] = DistributionSampling.sampleBernoulli(RNG$, d[y]);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample47)
			y = DistributionSampling.sampleCategorical(RNG$, b, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample50) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$48_7 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$48_7 = 3;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$48_7 = 2;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$48_7, d);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample47)
			y = DistributionSampling.sampleCategorical(RNG$, b, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample50) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$48_6 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$48_6 = 3;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$48_6 = 2;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$48_6, d);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample47)
				sample47();
			if(!fixedFlag$sample50)
				sample50();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!fixedFlag$sample50)
				sample50();
			if(!fixedFlag$sample47)
				sample47();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		double[] var5 = a[0];
		var5[0] = 0.4;
		var5[1] = 0.6;
		double[] var18 = a[1];
		var18[0] = 0.2;
		var18[1] = 0.3;
		var18[2] = 0.5;
		b[0] = 0.35;
		b[1] = 0.65;
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
		logProbability$var44 = 0.0;
		if(!fixedProbFlag$sample47)
			logProbability$y = 0.0;
		logProbability$var47 = 0.0;
		if(!fixedProbFlag$sample50)
			logProbability$d = 0.0;
		logProbability$var50 = 0.0;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample65)
			logProbability$var63 = 0.0;
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
		if(fixedFlag$sample47)
			logProbabilityValue$sample47();
		if(fixedFlag$sample50)
			logProbabilityValue$sample50();
		logProbabilityValue$sample65();
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
		logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		logProbabilityValue$sample65();
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
		logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		logProbabilityValue$sample65();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample47)
			y = DistributionSampling.sampleCategorical(RNG$, b, 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample50) {
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$48_8 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$48_8 = 3;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$48_8 = 2;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$48_8, d);
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
		int cv$length1 = obs.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			obs[cv$index1] = obs_measured[cv$index1];
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
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + " package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model RaggedArray6(boolean[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    double[] b = { 0.35, 0.65 };\n"
		     + "    int y = categorical(b).sample();\n"
		     + "    double[] d = dirichlet(a[y]).sample();\n"
		     + "    boolean[] obs = bernoulli(d[y]).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}