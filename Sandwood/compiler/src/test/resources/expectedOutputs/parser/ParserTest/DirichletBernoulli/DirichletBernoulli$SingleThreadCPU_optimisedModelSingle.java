package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DirichletBernoulli$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements DirichletBernoulli$CoreInterface {
	
	// Declare the variables for the model.
	private boolean fixedFlag$sample17 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample38 = false;
	private boolean fixedProbFlag$sample51 = false;
	private int length;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b1;
	private double logProbability$b2;
	private double logProbability$output;
	private double logProbability$prior;
	private double logProbability$var16;
	private double logProbability$var38;
	private double logProbability$var51;
	private boolean[] observed;
	private boolean[] output;
	private double[] prior;
	private boolean system$gibbsForward = true;
	private double[] v;

	public DirichletBernoulli$SingleThreadCPU(ExecutionTarget target) {
		super(target);
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
		
		// Should the probability of sample 38 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample17" with its value "cv$value".
		fixedProbFlag$sample38 = (cv$value && fixedProbFlag$sample38);
		
		// Should the probability of sample 51 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample17" with its value "cv$value".
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
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
		// Set observed
		observed = cv$value;
	}

	// Getter for output.
	@Override
	public final boolean[] get$output() {
		return output;
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
		// Set prior
		prior = cv$value;
		
		// Unset the fixed probability flag for sample 17 as it depends on prior.
		fixedProbFlag$sample17 = false;
		
		// Unset the fixed probability flag for sample 38 as it depends on prior.
		fixedProbFlag$sample38 = false;
		
		// Unset the fixed probability flag for sample 51 as it depends on prior.
		fixedProbFlag$sample51 = false;
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(prior, v, 2);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var16 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample17)
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
			fixedProbFlag$sample17 = fixedFlag$sample17;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var16 = logProbability$prior;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$prior);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample17)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$prior);
		}
	}

	// Calculate the probability of the samples represented by sample38 using sampled
	// values.
	private final void logProbabilityValue$sample38() {
		// Determine if we need to calculate the values for sample task 38 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample38) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var37 = 0; i$var37 < (length / 2); i$var37 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(output[i$var37], prior[0]));
			logProbability$b1 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var38 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample38 = fixedFlag$sample17;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$b1 = logProbability$var38;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$output = (logProbability$output + logProbability$var38);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var38);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var38);
		}
	}

	// Calculate the probability of the samples represented by sample51 using sampled
	// values.
	private final void logProbabilityValue$sample51() {
		// Determine if we need to calculate the values for sample task 51 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample51) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var50 = (length / 2); i$var50 < length; i$var50 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(output[i$var50], prior[1]));
			logProbability$b2 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var51 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample51 = fixedFlag$sample17;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$b2 = logProbability$var51;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$output = (logProbability$output + logProbability$var51);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var51);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var51);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 17 drawn from Dirichlet 16. Inference was performed using Metropolis-Hastings.
	private final void sample17() {
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the probability of the random variable generating the original sampled
		// value.
		double cv$originalProbability;
		
		// Pick a value in the array to adjust.
		int cv$indexToChange = (int)(DistributionSampling.sampleUniform(RNG$) * 2.0);
		
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
				double cv$temp = prior[cv$loopIndex];
				
				// If the maximum move is less than the proposed move update the move size.
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
			
			// For the array values after the index we are going to change calculate the maximum
			// move possible.
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < 2; cv$loopIndex += 1) {
				// Calculate the maximum change value that the value at array index cv$loopIndex could
				// support. Based on moving all other values by an equal amount.
				// 
				// A reference local to the function for the sample variable.
				double cv$temp = prior[cv$loopIndex];
				
				// If this is less than the proposed increase, change the proposed increase to this
				// value.
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
		}
		
		// Multiply the maximum adjustment by the adjustment ratio to get the actual adjustment
		// we are going to make.
		cv$proposedDifference = (cv$movementRatio * cv$proposedDifference);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$$var98" with its value "2".
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(prior, v, 2);
			
			// Processing random variable 20.
			// 
			// Processing sample task 38 of consumer random variable b1.
			for(int i$var37 = 0; i$var37 < (length / 2); i$var37 += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 38 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$2$var19's comment
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var37], prior[0]) + cv$accumulatedProbabilities);
			
			// Processing random variable 23.
			// 
			// Processing sample task 51 of consumer random variable b2.
			for(int i$var50 = (length / 2); i$var50 < length; i$var50 += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 51 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$3$var22's comment
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var50], prior[1]) + cv$accumulatedProbabilities);
			
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
			// 
			// Calculate how much each of the other indexes needs to be adjusted by in order to
			// maintain that the sum of the indexes is 1.
			prior[cv$loopIndex] = (prior[cv$loopIndex] - cv$proposedDifference);
		
		// Update the selected index.
		// 
		// A reference local to the function for the sample variable.
		prior[cv$indexToChange] = (prior[cv$indexToChange] + cv$proposedDifference);
		
		// Update all the indexes after the index we selected.
		for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < 2; cv$loopIndex += 1)
			// A reference local to the function for the sample variable.
			// 
			// Calculate how much each of the other indexes needs to be adjusted by in order to
			// maintain that the sum of the indexes is 1.
			prior[cv$loopIndex] = (prior[cv$loopIndex] - cv$proposedDifference);
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$$var98" with its value "2".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(prior, v, 2);
		
		// Processing random variable 20.
		// 
		// Processing sample task 38 of consumer random variable b1.
		for(int i$var37 = 0; i$var37 < (length / 2); i$var37 += 1)
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 38 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// cv$temp$2$var19's comment
			// Constructing a random variable input for use later.
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var37], prior[0]) + cv$accumulatedProbabilities);
		
		// Processing random variable 23.
		// 
		// Processing sample task 51 of consumer random variable b2.
		for(int i$var50 = (length / 2); i$var50 < length; i$var50 += 1)
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 51 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// cv$temp$3$var22's comment
			// Constructing a random variable input for use later.
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var50], prior[1]) + cv$accumulatedProbabilities);
		
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
				// 
				// Calculate how much each of the other indexes needs to be adjusted by in order to
				// maintain that the sum of the indexes is 1.
				prior[cv$loopIndex] = (prior[cv$loopIndex] + cv$proposedDifference);
			
			// Update the selected index.
			// 
			// A reference local to the function for the sample variable.
			prior[cv$indexToChange] = (prior[cv$indexToChange] - cv$proposedDifference);
			
			// Update all the indexes after the index we selected.
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < 2; cv$loopIndex += 1)
				// A reference local to the function for the sample variable.
				// 
				// Calculate how much each of the other indexes needs to be adjusted by in order to
				// maintain that the sum of the indexes is 1.
				prior[cv$loopIndex] = (prior[cv$loopIndex] + cv$proposedDifference);
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
		if(!fixedFlag$sample17)
			// Constructor for prior
			prior = new double[2];
		
		// Constructor for output
		output = new boolean[length$observed];
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, v, 2, prior);
		for(int i$var37 = 0; i$var37 < (length / 2); i$var37 += 1)
			output[i$var37] = DistributionSampling.sampleBernoulli(RNG$, prior[0]);
		for(int i$var50 = (length / 2); i$var50 < length; i$var50 += 1)
			output[i$var50] = DistributionSampling.sampleBernoulli(RNG$, prior[1]);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, v, 2, prior);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, v, 2, prior);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample17)
			sample17();
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		v[0] = 0.1;
		v[1] = 0.1;
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
		logProbability$var16 = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$prior = 0.0;
		logProbability$b1 = 0.0;
		logProbability$output = 0.0;
		if(!fixedProbFlag$sample38)
			logProbability$var38 = 0.0;
		logProbability$b2 = 0.0;
		if(!fixedProbFlag$sample51)
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
		logProbabilityValue$sample38();
		logProbabilityValue$sample51();
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
		logProbabilityValue$sample38();
		logProbabilityValue$sample51();
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
		logProbabilityValue$sample38();
		logProbabilityValue$sample51();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, v, 2, prior);
		
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
		int cv$length1 = output.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			output[cv$index1] = observed[cv$index1];
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
		     + " * Copyright (c) 2019-2023, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model DirichletBernoulli(boolean[] observed) {\n"
		     + "    double[] v = new double[2] <~ 0.1;\n"
		     + "    double[] prior = dirichlet(v).sample();\n"
		     + "    Bernoulli b1 = new Bernoulli(prior[0]);\n"
		     + "    Bernoulli b2 = new Bernoulli(prior[1]);\n"
		     + "    int length = observed.length;\n"
		     + "    boolean[] output = new boolean[length];\n"
		     + "    for(int i=0; i<length/2; i++)\n"
		     + "        output[i] = b1.sample();\n"
		     + "    for(int i=length/2; i<length; i++)\n"
		     + "        output[i] = b2.sample();\n"
		     + "    output.observe(observed);\n"
		     + "}\n"
		     + "";
	}
}