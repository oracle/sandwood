package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray4$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements RaggedArray4$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] a;
	private double[] b;
	private double[] cv$var45$stateProbabilityGlobal;
	private double[] cv$var48$countGlobal;
	private double[] d;
	private boolean fixedFlag$sample47 = false;
	private boolean fixedFlag$sample50 = false;
	private boolean fixedProbFlag$sample47 = false;
	private boolean fixedProbFlag$sample50 = false;
	private boolean fixedProbFlag$sample64 = false;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$d;
	private double logProbability$obs;
	private double logProbability$var44;
	private double logProbability$var47;
	private double logProbability$var49;
	private double logProbability$var62;
	private double logProbability$y;
	private int[] obs;
	private int[] obs_measured;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray4$MultiThreadCPU(ExecutionTarget target) {
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
		
		// Unset the fixed probability flag for sample 64 as it depends on d.
		fixedProbFlag$sample64 = false;
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
		
		// Should the probability of sample 64 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample50" with its value "cv$value".
		fixedProbFlag$sample64 = (cv$value && fixedProbFlag$sample64);
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
	public final int[] get$obs() {
		return obs;
	}

	// Getter for obs_measured.
	@Override
	public final int[] get$obs_measured() {
		return obs_measured;
	}

	// Setter for obs_measured.
	@Override
	public final void set$obs_measured(int[] cv$value) {
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
			int lengthCV$a$48_15 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$48_15 = 3;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$48_15 = 2;
			
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(d, a[y], lengthCV$a$48_15);
			
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

	// Calculate the probability of the samples represented by sample64 using sampled
	// values.
	private final void logProbabilityValue$sample64() {
		// Determine if we need to calculate the values for sample task 64 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample64) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var61 = 0; var61 < length$obs_measured; var61 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = obs[var61];
				
				// Allocate a local variable to hold the length of the array.
				int lengthCV$a$48_16 = -1;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == y))
					lengthCV$a$48_16 = 3;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == y))
					lengthCV$a$48_16 = 2;
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$a$48_16))?Math.log(d[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			logProbability$var49 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var62 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample64 = fixedFlag$sample50;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var49 = logProbability$var62;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$obs = (logProbability$obs + logProbability$var62);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var62);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var62);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 47 drawn from Categorical 44. Inference was performed using variable
	// marginalization.
	private final void sample47() {
		// Write out the new value of the sample.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "0".
		y = 0;
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
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
		// cv$temp$3$$var218's comment
		// 
		// $var218's comment
		// Constructing a random variable input for use later.
		// 
		// Processing random variable 47.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "0".
		cv$var45$stateProbabilityGlobal[0] = (DistributionSampling.logProbabilityDirichlet(d, a[0], 2) + Math.log(b[0]));
		
		// Write out the new value of the sample.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		y = 1;
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
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
		// cv$temp$3$$var218's comment
		// 
		// $var218's comment
		// Constructing a random variable input for use later.
		// 
		// Processing random variable 47.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		cv$var45$stateProbabilityGlobal[1] = (DistributionSampling.logProbabilityDirichlet(d, a[1], 3) + Math.log(b[1]));
		
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
	// by sample task 50 drawn from Dirichlet 47. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample50() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$48_13 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == y))
			lengthCV$a$48_13 = 3;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == y))
			lengthCV$a$48_13 = 2;
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < lengthCV$a$48_13; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var48$countGlobal[cv$loopIndex] = 0.0;
		
		// Processing random variable 49.
		// 
		// Processing sample task 64 of consumer random variable null.
		for(int var61 = 0; var61 < length$obs_measured; var61 += 1)
			// Increment the sample counter with the value sampled by sample task 64 of random
			// variable var49
			// 
			// A local reference to the scratch space.
			cv$var48$countGlobal[obs[var61]] = (cv$var48$countGlobal[obs[var61]] + 1.0);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$48_14 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == y))
			lengthCV$a$48_14 = 3;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == y))
			lengthCV$a$48_14 = 2;
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, a[y], cv$var48$countGlobal, d, lengthCV$a$48_14);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var45$stateProbabilityGlobal
		// 
		// Allocation of cv$var45$stateProbabilityGlobal for single threaded execution
		cv$var45$stateProbabilityGlobal = new double[2];
		
		// Allocation of cv$var48$countGlobal for single threaded execution
		// 
		// Test if the input to putTask 34 is larger than the current values.
		cv$var48$countGlobal = new double[3];
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
			int lengthCV$a$48_11 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$48_11 = 3;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$48_11 = 2;
			d = new double[lengthCV$a$48_11];
		}
		
		// Constructor for obs
		obs = new int[length$obs_measured];
		
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
			int lengthCV$a$48_17 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$48_17 = 3;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$48_17 = 2;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$48_17, d);
		}
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$48_18 = -1;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == y))
			lengthCV$a$48_18 = 3;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 == y))
			lengthCV$a$48_18 = 2;
		
		// Alternative name for lengthCV$a$48_18 to make it effectively final.
		int lengthCV$a$48_18$1 = lengthCV$a$48_18;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, length$obs_measured, 1,
			(int forStart$var61, int forEnd$var61, int threadID$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var61 = forStart$var61; var61 < forEnd$var61; var61 += 1)
						obs[var61] = DistributionSampling.sampleCategorical(RNG$1, d, lengthCV$a$48_18$1);
			}
		);
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
			int lengthCV$a$48_20 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$48_20 = 3;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$48_20 = 2;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$48_20, d);
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
			int lengthCV$a$48_19 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$48_19 = 3;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$48_19 = 2;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$48_19, d);
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
		logProbability$var49 = 0.0;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample64)
			logProbability$var62 = 0.0;
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
		logProbabilityValue$sample64();
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
		logProbabilityValue$sample64();
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
		logProbabilityValue$sample64();
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
			int lengthCV$a$48_21 = -1;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == y))
				lengthCV$a$48_21 = 3;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 == y))
				lengthCV$a$48_21 = 2;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$48_21, d);
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
		     + "public model RaggedArray4(int[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    double[] b = { 0.35, 0.65 };\n"
		     + "    int y = categorical(b).sample();\n"
		     + "    double[] d = dirichlet(a[y]).sample();\n"
		     + "    int[] obs = categorical(d).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}