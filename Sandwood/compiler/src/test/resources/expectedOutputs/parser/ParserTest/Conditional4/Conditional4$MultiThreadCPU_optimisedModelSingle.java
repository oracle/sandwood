package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Conditional4$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Conditional4.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Conditional4$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$var4$stateProbabilityGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// 
			// Constructor for cv$var4$stateProbabilityGlobal
			// 
			// Allocation of cv$var4$stateProbabilityGlobal for single threaded execution
			cv$var4$stateProbabilityGlobal = new double[2];
		}
	}


	public Conditional4$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample21
	private final void drawValueSample21() {
		state.var19 = (DistributionSampling.sampleUniform(state.RNG$) * 0.5);
		
		// Guards to ensure that bias is only updated when there is a valid path.
		state.bias[0] = state.var19;
	}

	// Pick a value from the distribution for the unconditioned variable from sample4
	private final void drawValueSample4() {
		state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.guard)
			state.bias[0] = 0.5;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else
			state.bias[0] = state.var19;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 21 drawn from Uniform 18. Inference was performed using Metropolis-Hastings.
	private final void inferSample21() {
		state.constrainedFlag$sample21 = false;
		
		// The original value of the sample
		double cv$originalValue = state.var19;
		
		// Calculate a proposed variance.
		// 
						// The original value of the sample
		double cv$var = (((state.var19 < 0)?(-state.var19):state.var19) * 40.0);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.01))
			cv$var = 0.01;
		
		// The proposed new value for the sample
		// 
		// The original value of the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + state.var19);
		
		// Mark that the sample has observed constrained data.
		state.constrainedFlag$sample21 = true;
		
		// Variable declaration of cv$originalProbability moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 27 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
						// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
						// Set the current value to the current state of the tree.
		// 
		// The original value of the sample
		double cv$originalProbability = (DistributionSampling.logProbabilityBeta(state.value, state.var19, 1.0) + (((0.0 <= state.var19) && (state.var19 < 0.5))?0.6931471805599453:Double.NEGATIVE_INFINITY));
		
		// Update Sample and intermediate values
		// 
		// Write out the new value of the sample.
		state.var19 = cv$proposedValue;
		
		// Guards to ensure that bias is only updated when there is a valid path.
		state.bias[0] = cv$proposedValue;
		
		// Mark that the sample has observed constrained data.
		state.constrainedFlag$sample21 = true;
		
		// The probability ration for the proposed value and the current value.
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 27 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
						// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		double cv$ratio = ((DistributionSampling.logProbabilityBeta(state.value, cv$proposedValue, 1.0) + (((0.0 <= cv$proposedValue) && (cv$proposedValue < 0.5))?0.6931471805599453:Double.NEGATIVE_INFINITY)) - cv$originalProbability);
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
			// If it is not revert the changes.
			// 
			// Set the sample value
			// Write out the new value of the sample.
			state.var19 = cv$originalValue;
			
			// Guards to ensure that bias is only updated when there is a valid path.
			// 
			// Write out the new value of the sample.
			state.bias[0] = cv$originalValue;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 4 drawn from bernoulli. Inference was performed using variable marginalization.
	private final void inferSample4() {
		state.constrainedFlag$sample4 = false;
		
		// Write out the new value of the sample.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
						// Substituted "cv$valuePos" with its value "0".
		state.guard = false;
		state.bias[0] = state.var19;
		
		// Processing sample task 27 of consumer random variable null.
		// 
		// Mark that the sample has observed constrained data.
		state.constrainedFlag$sample4 = true;
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 21 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// Variable declaration of cv$accumulatedConsumerProbabilities moved.
		// Declaration comment was:
		// Processing sample task 21 of consumer random variable null.
		// 
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 27 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// Variable declaration of cv$accumulatedConsumerProbabilities moved.
		// Declaration comment was:
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		// 
		// Constructing a random variable input for use later.
		scratch.cv$var4$stateProbabilityGlobal[0] = (((((0.0 <= state.var19) && (state.var19 < 0.5))?0.6931471805599453:Double.NEGATIVE_INFINITY) + DistributionSampling.logProbabilityBeta(state.value, state.bias[0], 1.0)) - 0.6931471805599453);
		
		// Write out the new value of the sample.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.guard = true;
		
		// Looking for a path between Put 16 and consumer Beta 24.
		// 
		// Mark that the sample has observed constrained data.
		state.constrainedFlag$sample4 = true;
		
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
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 27 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// Variable declaration of cv$accumulatedConsumerProbabilities moved.
		// Declaration comment was:
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		scratch.cv$var4$stateProbabilityGlobal[1] = (DistributionSampling.logProbabilityBeta(state.value, 0.5, 1.0) - 0.6931471805599453);
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialize the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = scratch.cv$var4$stateProbabilityGlobal[0];
		
		// Unrolled loop
		// 
		// Get a local reference to the scratch space.
		double cv$lseElementValue = scratch.cv$var4$stateProbabilityGlobal[1];
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
			// Initialize the sum of the array elements
			cv$logSum = (Math.log((Math.exp((scratch.cv$var4$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var4$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Unrolled loop
									// Get a local reference to the scratch space.
			scratch.cv$var4$stateProbabilityGlobal[0] = 0.5;
			
									// Get a local reference to the scratch space.
			scratch.cv$var4$stateProbabilityGlobal[1] = 0.5;
		} else {
			// Unrolled loop
									// Get a local reference to the scratch space.
			scratch.cv$var4$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var4$stateProbabilityGlobal[0] - cv$logSum));
			
									// Get a local reference to the scratch space.
			scratch.cv$var4$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var4$stateProbabilityGlobal[1] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
						// Get a local reference to the scratch space.
		for(int cv$indexName = 2; cv$indexName < scratch.cv$var4$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			scratch.cv$var4$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the new value of the sample.
		// 
								// cv$numStates's comment
		// variable marginalization
		state.guard = (DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var4$stateProbabilityGlobal, 2) == 1);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.guard)
			state.bias[0] = 0.5;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else
			state.bias[0] = state.var19;
	}

	// Calculate the probability of the samples represented by sample21 using sampled
	// values.
	private final void logProbabilityValue$sample21() {
		// Determine if we need to calculate the values for sample task 21 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample21) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if(!state.guard) {
				// Record that the sample was reached.
				cv$sampleReached = true;
				
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
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
												// The sample value to calculate the probability of generating
				cv$sampleAccumulator = (((0.0 <= state.var19) && (state.var19 < 0.5))?0.6931471805599453:Double.NEGATIVE_INFINITY);
			}
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$var19 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$bias = (state.logProbability$bias + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample21)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample21 = (state.fixedFlag$sample21 && state.fixedFlag$sample4);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$var19);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var19);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample21)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var19);
		}
	}

	// Calculate the probability of the samples represented by sample27 using sampled
	// values.
	private final void logProbabilityValue$sample27() {
		// Determine if we need to calculate the values for sample task 27 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample27) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.value, state.bias[0], 1.0);
			
			// Store the sample task probability
			state.logProbability$value = cv$distributionAccumulator;
			
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
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			
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
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample27 = (state.fixedFlag$sample4 && state.fixedFlag$sample21);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$value);
			
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$value);
		}
	}

	// Calculate the probability of the samples represented by sample4 using sampled values.
	private final void logProbabilityValue$sample4() {
		// Determine if we need to calculate the values for sample task 4 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample4) {
			// Generating probabilities for sample task
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
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// The sample value to calculate the probability of generating
			state.logProbability$bernoulli = -0.6931471805599453;
			
			// Store the sample task probability
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
			state.logProbability$guard = -0.6931471805599453;
			
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
			state.logProbability$$model = (state.logProbability$$model - 0.6931471805599453);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample4)
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
				state.logProbability$$evidence = (state.logProbability$$evidence - 0.6931471805599453);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample4 = state.fixedFlag$sample4;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			state.logProbability$bernoulli = state.logProbability$guard;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$guard);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample4)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$guard);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(state.guard)
			state.bias[0] = 0.5;
		else {
			if(!state.fixedFlag$sample21)
				state.var19 = (DistributionSampling.sampleUniform(state.RNG$) * 0.5);
			if((!state.fixedFlag$sample4 || !state.fixedFlag$sample21))
				state.bias[0] = state.var19;
		}
		state.value = DistributionSampling.sampleBeta(state.RNG$, state.bias[0], 1.0);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(state.guard)
			state.bias[0] = 0.5;
		else {
			if(!state.fixedFlag$sample21)
				state.var19 = (DistributionSampling.sampleUniform(state.RNG$) * 0.5);
			state.bias[0] = state.var19;
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(state.guard)
			state.bias[0] = 0.5;
		else {
			if(!state.fixedFlag$sample21)
				state.var19 = (DistributionSampling.sampleUniform(state.RNG$) * 0.5);
			state.bias[0] = state.var19;
		}
		state.value = DistributionSampling.sampleBeta(state.RNG$, state.bias[0], 1.0);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(state.guard)
			state.bias[0] = 0.5;
		else {
			if(!state.fixedFlag$sample21)
				state.var19 = (DistributionSampling.sampleUniform(state.RNG$) * 0.5);
			if((!state.fixedFlag$sample4 || !state.fixedFlag$sample21))
				state.bias[0] = state.var19;
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(state.guard)
			state.bias[0] = 0.5;
		else {
			if(!state.fixedFlag$sample21)
				state.var19 = (DistributionSampling.sampleUniform(state.RNG$) * 0.5);
			state.bias[0] = state.var19;
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample4)
				inferSample4();
			if((!state.guard && !state.fixedFlag$sample21))
				inferSample21();
		}
		// Infer the samples in reverse chronological order.
		else {
			if((!state.guard && !state.fixedFlag$sample21))
				inferSample21();
			if(!state.fixedFlag$sample4)
				inferSample4();
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample4)
			drawValueSample4();
		if((!state.guard && !state.constrainedFlag$sample21))
			drawValueSample21();
	}

	// A method to initialize all the probabilities in the model to 0/Log(1) ready for
	// the current probabilities to be calculated by calculating the probability of each
	// sample task, and its effect on the rest of the model.
	private final void initializeLogProbabilityFields() {
		// Set the probabilities of the random variable, and the model as a whole to ready
		// them to be reconstructed by the probability calls for each sample. Sample probabilities
		// are only reset for samples that are not fixed at a value that has already been
		// calculated.
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$bernoulli = 0.0;
		if(!state.fixedProbFlag$sample4)
			state.logProbability$guard = Double.NaN;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample21)
			state.logProbability$var19 = Double.NaN;
		if(!state.fixedProbFlag$sample27)
			state.logProbability$value = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample4)
			logProbabilityValue$sample4();
		if(state.fixedFlag$sample21)
			logProbabilityValue$sample21();
		logProbabilityValue$sample27();
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
		logProbabilityValue$sample4();
		logProbabilityValue$sample21();
		logProbabilityValue$sample27();
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
		logProbabilityValue$sample4();
		logProbabilityValue$sample21();
		logProbabilityValue$sample27();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		state.value = state.observedValue;
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		if(state.guard)
			state.bias[0] = 0.5;
		else
			state.bias[0] = state.var19;
	}

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
		     + "public model Conditional4(double observedValue)  {\n"
		     + "        \n"
		     + "    //Construct a bernoulli\n"
		     + "    Bernoulli bernoulli = bernoulli(0.5);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    boolean guard = bernoulli.sample();\n"
		     + "    \n"
		     + "    double[] bias = new double[1];\n"
		     + "        \n"
		     + "    if(guard)\n"
		     + "        bias[0] = 0.5;\n"
		     + "    else\n"
		     + "        bias[0] = uniform(0.0, 0.5).sample();\n"
		     + "    \n"
		     + "    double value = beta(bias[0],1.0).sample();\n"
		     + "    \n"
		     + "    //Link the sampled value to the observed value\n"
		     + "    value.observe(observedValue);\n"
		     + "}";
	}
}