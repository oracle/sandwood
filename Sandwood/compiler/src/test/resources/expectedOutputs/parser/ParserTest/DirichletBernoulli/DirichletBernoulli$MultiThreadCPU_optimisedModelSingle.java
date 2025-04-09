package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DirichletBernoulli$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DirichletBernoulli$CoreInterface {
	
	// Declare the variables for the model.
	private boolean fixedFlag$sample13 = false;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedProbFlag$sample13 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample34 = false;
	private int length;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b1;
	private double logProbability$b2;
	private double logProbability$output;
	private double logProbability$prior;
	private double logProbability$var11;
	private double logProbability$var26;
	private double logProbability$var32;
	private boolean[] observed;
	private boolean[] output;
	private double[] prior;
	private boolean setFlag$output = false;
	private boolean setFlag$prior = false;
	private boolean system$gibbsForward = true;
	private double[] v;

	public DirichletBernoulli$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for fixedFlag$sample13.
	@Override
	public final boolean get$fixedFlag$sample13() {
		return fixedFlag$sample13;
	}

	// Setter for fixedFlag$sample13.
	@Override
	public final void set$fixedFlag$sample13(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample13 including if probabilities
		// need to be updated.
		fixedFlag$sample13 = cv$value;
		
		// Should the probability of sample 13 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample13" with its value "cv$value".
		fixedProbFlag$sample13 = (cv$value && fixedProbFlag$sample13);
		
		// Should the probability of sample 28 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample13" with its value "cv$value".
		fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
		
		// Should the probability of sample 34 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample13" with its value "cv$value".
		fixedProbFlag$sample34 = (cv$value && fixedProbFlag$sample34);
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
	}

	// Getter for fixedFlag$sample34.
	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	// Setter for fixedFlag$sample34.
	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample34 including if probabilities
		// need to be updated.
		fixedFlag$sample34 = cv$value;
		
		// Should the probability of sample 34 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample34" with its value "cv$value".
		fixedProbFlag$sample34 = (cv$value && fixedProbFlag$sample34);
	}

	// Getter for length.
	@Override
	public final int get$length() {
		return length;
	}

	// Getter for length$observed.
	@Override
	public final int get$length$observed() {
		return length$observed;
	}

	// Setter for length$observed.
	@Override
	public final void set$length$observed(int cv$value) {
		length$observed = cv$value;
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

	// Getter for logProbability$b1.
	@Override
	public final double get$logProbability$b1() {
		return logProbability$b1;
	}

	// Getter for logProbability$b2.
	@Override
	public final double get$logProbability$b2() {
		return logProbability$b2;
	}

	// Getter for logProbability$output.
	@Override
	public final double get$logProbability$output() {
		return logProbability$output;
	}

	// Getter for logProbability$prior.
	@Override
	public final double get$logProbability$prior() {
		return logProbability$prior;
	}

	// Getter for observed.
	@Override
	public final boolean[] get$observed() {
		return observed;
	}

	// Setter for observed.
	@Override
	public final void set$observed(boolean[] cv$value) {
		// Set observed with flag to mark that it has been set so another array doesn't need
		// to be constructed
		observed = cv$value;
	}

	// Getter for output.
	@Override
	public final boolean[] get$output() {
		return output;
	}

	// Setter for output.
	@Override
	public final void set$output(boolean[] cv$value) {
		// Set flags for all the side effects of output including if probabilities need to
		// be updated.
		// Set output with flag to mark that it has been set so another array doesn't need
		// to be constructed
		output = cv$value;
		setFlag$output = true;
		
		// Unset the fixed probability flag for sample 28 as it depends on output.
		fixedProbFlag$sample28 = false;
		
		// Unset the fixed probability flag for sample 34 as it depends on output.
		fixedProbFlag$sample34 = false;
	}

	// Getter for prior.
	@Override
	public final double[] get$prior() {
		return prior;
	}

	// Setter for prior.
	@Override
	public final void set$prior(double[] cv$value) {
		// Set flags for all the side effects of prior including if probabilities need to
		// be updated.
		// Set prior with flag to mark that it has been set so another array doesn't need
		// to be constructed
		prior = cv$value;
		setFlag$prior = true;
		
		// Unset the fixed probability flag for sample 13 as it depends on prior.
		fixedProbFlag$sample13 = false;
		
		// Unset the fixed probability flag for sample 28 as it depends on prior.
		fixedProbFlag$sample28 = false;
		
		// Unset the fixed probability flag for sample 34 as it depends on prior.
		fixedProbFlag$sample34 = false;
	}

	// Getter for v.
	@Override
	public final double[] get$v() {
		return v;
	}

	// Calculate the probability of the samples represented by sample13 using sampled
	// values.
	private final void logProbabilityValue$sample13() {
		// Determine if we need to calculate the values for sample task 13 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample13) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(prior, v);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var11 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$prior = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample13)
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
			fixedProbFlag$sample13 = fixedFlag$sample13;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var11 = logProbability$prior;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$prior);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample13)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$prior);
		}
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
			for(int i$var25 = 0; i$var25 < (length / 2); i$var25 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(output[i$var25], prior[0]));
			logProbability$b1 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var26 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$output = (logProbability$output + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample28 = (fixedFlag$sample28 && fixedFlag$sample13);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$b1 = logProbability$var26;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$output = (logProbability$output + logProbability$var26);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var26);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var26);
		}
	}

	// Calculate the probability of the samples represented by sample34 using sampled
	// values.
	private final void logProbabilityValue$sample34() {
		// Determine if we need to calculate the values for sample task 34 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample34) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var31 = (length / 2); i$var31 < length; i$var31 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(output[i$var31], prior[1]));
			logProbability$b2 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var32 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$output = (logProbability$output + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample34 = (fixedFlag$sample34 && fixedFlag$sample13);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$b2 = logProbability$var32;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$output = (logProbability$output + logProbability$var32);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var32);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var32);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 13 drawn from Dirichlet 11. Inference was performed using Metropolis-Hastings.
	private final void sample13() {
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the probability of the random variable generating the original sampled
		// value.
		double cv$originalProbability;
		
		// A reference local to the function for the sample variable.
		int cv$arrayLength = prior.length;
		
		// Pick a value in the array to adjust.
		int cv$indexToChange = (int)((double)cv$arrayLength * DistributionSampling.sampleUniform(RNG$));
		
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
			cv$proposedDifference = prior[cv$indexToChange];
		else {
			// Calculate the maximum magnitude of the proposed index change.
			// Initially set the maximum to the amount that the value we are changing could increase
			// without exceeding 1
			// 
			// A reference local to the function for the sample variable.
			cv$proposedDifference = (1.0 - prior[cv$indexToChange]);
			
			// For the array values up to the index we are going to change calculate the maximum
			// move possible.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1) {
				// Calculate the maximum change value that the value at array index cv$loopIndex could
				// support. Based on moving all other values by an equal amount.
				// 
				// A reference local to the function for the sample variable.
				double cv$temp = (prior[cv$loopIndex] * (cv$arrayLength - 1));
				
				// If the maximum move is less than the proposed move update the move size.
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
			
			// For the array values after the index we are going to change calculate the maximum
			// move possible.
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1) {
				// Calculate the maximum change value that the value at array index cv$loopIndex could
				// support. Based on moving all other values by an equal amount.
				// 
				// A reference local to the function for the sample variable.
				double cv$temp = (prior[cv$loopIndex] * (cv$arrayLength - 1));
				
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
		double cv$rebalanceValue = (cv$proposedDifference / (cv$arrayLength - 1));
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$v" with its value "v".
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(prior, v);
			
			// Processing random variable 15.
			// 
			// Processing sample task 28 of consumer random variable b1.
			for(int i$var25 = 0; i$var25 < (length / 2); i$var25 += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 28 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$1$var14's comment
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var25], prior[0]) + cv$accumulatedProbabilities);
			
			// Processing random variable 18.
			// 
			// Processing sample task 34 of consumer random variable b2.
			for(int i$var31 = (length / 2); i$var31 < length; i$var31 += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 34 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$2$var17's comment
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var31], prior[1]) + cv$accumulatedProbabilities);
			
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
			prior[cv$loopIndex] = (prior[cv$loopIndex] - cv$rebalanceValue);
		
		// Update the selected index.
		// 
		// A reference local to the function for the sample variable.
		prior[cv$indexToChange] = (prior[cv$indexToChange] + cv$proposedDifference);
		
		// Update all the indexes after the index we selected.
		for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			// A reference local to the function for the sample variable.
			prior[cv$loopIndex] = (prior[cv$loopIndex] - cv$rebalanceValue);
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$v" with its value "v".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(prior, v);
		
		// Processing random variable 15.
		// 
		// Processing sample task 28 of consumer random variable b1.
		for(int i$var25 = 0; i$var25 < (length / 2); i$var25 += 1)
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 28 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// cv$temp$1$var14's comment
			// Constructing a random variable input for use later.
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var25], prior[0]) + cv$accumulatedProbabilities);
		
		// Processing random variable 18.
		// 
		// Processing sample task 34 of consumer random variable b2.
		for(int i$var31 = (length / 2); i$var31 < length; i$var31 += 1)
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 34 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// cv$temp$2$var17's comment
			// Constructing a random variable input for use later.
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var31], prior[1]) + cv$accumulatedProbabilities);
		
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
				prior[cv$loopIndex] = (prior[cv$loopIndex] + cv$rebalanceValue);
			
			// Update the selected index.
			// 
			// A reference local to the function for the sample variable.
			prior[cv$indexToChange] = (prior[cv$indexToChange] - cv$proposedDifference);
			
			// Update all the indexes after the index we selected.
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				// A reference local to the function for the sample variable.
				prior[cv$loopIndex] = (prior[cv$loopIndex] + cv$rebalanceValue);
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for v
		v = new double[2];
		
		// If prior has not been set already allocate space.
		if(!setFlag$prior)
			// Constructor for prior
			prior = new double[2];
		
		// If output has not been set already allocate space.
		if(!setFlag$output)
			// Constructor for output
			output = new boolean[length$observed];
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample13)
			DistributionSampling.sampleDirichlet(RNG$, v, prior);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample28)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, (length / 2), 1,
				(int forStart$i$var25, int forEnd$i$var25, int threadID$i$var25, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var25 = forStart$i$var25; i$var25 < forEnd$i$var25; i$var25 += 1)
							output[i$var25] = DistributionSampling.sampleBernoulli(RNG$1, prior[0]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample34)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, (length / 2), length, 1,
				(int forStart$i$var31, int forEnd$i$var31, int threadID$i$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var31 = forStart$i$var31; i$var31 < forEnd$i$var31; i$var31 += 1)
							output[i$var31] = DistributionSampling.sampleBernoulli(RNG$1, prior[1]);
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample13)
			DistributionSampling.sampleDirichlet(RNG$, v, prior);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample13)
			DistributionSampling.sampleDirichlet(RNG$, v, prior);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample13)
			sample13();
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$var8, int forEnd$var8, int threadID$var8, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var8 = forStart$var8; var8 < forEnd$var8; var8 += 1)
						v[var8] = 0.1;
			}
		);
		length = length$observed;
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
		if(!fixedProbFlag$sample13)
			logProbability$prior = 0.0;
		logProbability$b1 = 0.0;
		logProbability$output = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var26 = 0.0;
		logProbability$b2 = 0.0;
		if(!fixedProbFlag$sample34)
			logProbability$var32 = 0.0;
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
		if(fixedFlag$sample13)
			logProbabilityValue$sample13();
		logProbabilityValue$sample28();
		logProbabilityValue$sample34();
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
		logProbabilityValue$sample13();
		logProbabilityValue$sample28();
		logProbabilityValue$sample34();
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
		logProbabilityValue$sample13();
		logProbabilityValue$sample28();
		logProbabilityValue$sample34();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample13)
			DistributionSampling.sampleDirichlet(RNG$, v, prior);
		
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
		int cv$length1 = output.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			output[cv$index1] = observed[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model DirichletBernoulli(boolean[] observed) {\n    double[] v = new double[2] <~ 0.1;\n    double[] prior = dirichlet(v).sample();\n    Bernoulli b1 = new Bernoulli(prior[0]);\n    Bernoulli b2 = new Bernoulli(prior[1]);\n    int length = observed.length;\n    boolean[] output = new boolean[length];\n    for(int i=0; i<length/2; i++)\n        output[i] = b1.sample();\n    for(int i=length/2; i<length; i++)\n        output[i] = b2.sample();\n    output.observe(observed);\n}\n";
	}
}