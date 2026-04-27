package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMM_Paper$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMM_Paper.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMM_Paper$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[][] cv$var28$countGlobal;
		double[] cv$var31$countGlobal;
		double[] cv$var52$stateProbabilityGlobal;
		double[] cv$var70$stateProbabilityGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for cv$var28$countGlobal
			// 
			// Allocation of cv$var28$countGlobal for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var28$countGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var28$countGlobal[cv$index] = new double[state.nCoins];
			
			// Constructor for cv$var31$countGlobal
			// 
			// Allocation of cv$var31$countGlobal for single threaded execution
			cv$var31$countGlobal = new double[state.nCoins];
			
			// Constructor for cv$var52$stateProbabilityGlobal
			// 
			// Allocation of cv$var52$stateProbabilityGlobal for single threaded execution
			cv$var52$stateProbabilityGlobal = new double[state.nCoins];
			
			// Allocation of cv$var70$stateProbabilityGlobal for single threaded execution
			// 
			// Variable to record the maximum value of Task Get 69. Initially set to the value
			// of putTask 29.
			cv$var70$stateProbabilityGlobal = new double[state.nCoins];
		}
	}


	public HMM_Paper$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample28
	private final void drawValueSample28(int var27, int threadID$cv$var27, Rng RNG$) {
		DistributionSampling.sampleDirichlet(RNG$, state.v, state.nCoins, state.m[var27]);
	}

	// Pick a value from the distribution for the unconditioned variable from sample32
	private final void drawValueSample32() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.nCoins, state.initialCoin);
	}

	// Pick a value from the distribution for the unconditioned variable from sample47
	private final void drawValueSample47(int var45, int threadID$cv$var45, Rng RNG$) {
		state.bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample53
	private final void drawValueSample53() {
		state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialCoin, state.nCoins);
	}

	// Pick a value from the distribution for the unconditioned variable from sample71
	private final void drawValueSample71(int i) {
		state.st[i] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i - 1)]], state.nCoins);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 28 drawn from Dirichlet 16. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample28(int var27, int threadID$cv$var27, Rng RNG$) {
		state.constrainedFlag$sample28[var27] = false;
		
		// A local reference to the scratch space.
		double[] cv$countLocal = scratch.cv$var28$countGlobal[threadID$cv$var27];
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < state.nCoins; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		
		// Processing random variable 69.
		// 
		// Looking for a path between Sample 28 and consumer Categorical 69.
		for(int i = 1; i < state.nFlips; i += 1) {
			if(((var27 == state.st[(i - 1)]) && (state.fixedFlag$sample71 || state.constrainedFlag$sample71[(i - 1)]))) {
				// Processing sample task 71 of consumer random variable null.
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample28[var27] = true;
				
				// Increment the sample counter with the value sampled by sample task 71 of random
				// variable var69
				cv$countLocal[state.st[i]] = (cv$countLocal[state.st[i]] + 1.0);
			}
		}
		if(state.constrainedFlag$sample28[var27])
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
			// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(RNG$, state.v, cv$countLocal, state.m[var27], state.nCoins);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 32 drawn from Dirichlet 30. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample32() {
		state.constrainedFlag$sample32 = false;
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < state.nCoins; cv$loopIndex += 1)
			// A local reference to the scratch space.
			scratch.cv$var31$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((state.fixedFlag$sample53 || state.constrainedFlag$sample53)) {
			// Processing random variable 51.
			// 
			// Processing sample task 53 of consumer random variable null.
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample32 = true;
			
			// Increment the sample counter with the value sampled by sample task 53 of random
			// variable var51
			// 
									// A local reference to the scratch space.
			scratch.cv$var31$countGlobal[state.st[0]] = (scratch.cv$var31$countGlobal[state.st[0]] + 1.0);
		}
		if(state.constrainedFlag$sample32)
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
									// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, scratch.cv$var31$countGlobal, state.initialCoin, state.nCoins);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 47 drawn from Beta 34. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void inferSample47(int var45, int threadID$cv$var45, Rng RNG$) {
		state.constrainedFlag$sample47[var45] = false;
		
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		
		// Processing random variable 85.
		// 
		// Looking for a path between Sample 47 and consumer Bernoulli 85.
		for(int j = 0; j < state.nFlips; j += 1) {
			if((var45 == state.st[j])) {
				// Processing sample task 87 of consumer random variable null.
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample47[var45] = true;
				
				// Include the value sampled by task 87 from random variable var85.
				// 
				// Increment the number of samples.
				cv$count = (cv$count + 1);
				
				// If the sample value was positive increase the count
				if(state.flips[j])
					cv$sum = (cv$sum + 1);
			}
		}
		if(state.constrainedFlag$sample47[var45])
			// Guards to ensure that bias is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			state.bias[var45] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 53 drawn from Categorical 51. Inference was performed using variable
	// marginalization.
	private final void inferSample53() {
		state.constrainedFlag$sample53 = false;
		
		// Variable declaration of cv$numStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
				// cv$numStates's comment
		// Calculate the number of states to evaluate.
		int cv$numStates = Math.max(0, state.nCoins);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Guards to ensure that st is only updated when there is a valid path.
			// 
			// Value of the variable at this index
			state.st[0] = cv$valuePos;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			double cv$accumulatedProbabilities = (((((cv$valuePos < state.nCoins) && (0 < state.nCoins)) && (0.0 <= state.initialCoin[cv$valuePos])) && (state.initialCoin[cv$valuePos] <= 1.0))?Math.log(state.initialCoin[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Substituted "i" with its value "1".
			if(((1 < state.nFlips) && (state.fixedFlag$sample71 || state.constrainedFlag$sample71[0]))) {
				// Processing sample task 71 of consumer random variable null.
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample53 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 69.
				// 
				// Looking for a path between Sample 53 and consumer Categorical 69.
				// 
				// Value of the variable at this index
				double[] var68 = state.m[cv$valuePos];
				
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
				cv$accumulatedProbabilities = (((((((0.0 <= state.st[1]) && (state.st[1] < state.nCoins)) && (0 < state.nCoins)) && (0.0 <= var68[state.st[1]])) && (var68[state.st[1]] <= 1.0))?Math.log(var68[state.st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Substituted "j" with its value "0".
			if((0 < state.nFlips)) {
				// Processing sample task 87 of consumer random variable null.
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample53 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Looking for a path between Sample 53 and consumer Bernoulli 85.
				// 
				// Value of the variable at this index
				double var84 = state.bias[cv$valuePos];
				
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
				cv$accumulatedProbabilities = ((((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[0]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var52$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample53) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var52$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var52$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else {
				// Initialize the sum of the array elements
				double cv$lseSum = 0.0;
				
				// Offset values, move to normal space, and sum.
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					// Get a local reference to the scratch space.
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var52$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Get a local reference to the scratch space.
					scratch.cv$var52$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
															// Get a local reference to the scratch space.
					scratch.cv$var52$stateProbabilityGlobal[cv$indexName] = Math.exp((scratch.cv$var52$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var52$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var52$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Guards to ensure that st is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			// 
			// Get a local reference to the scratch space.
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var52$stateProbabilityGlobal, cv$numStates);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 71 drawn from Categorical 69. Inference was performed using variable
	// marginalization.
	private final void inferSample71(int i) {
		state.constrainedFlag$sample71[(i - 1)] = false;
		
		// Variable declaration of cv$numStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
				// cv$numStates's comment
		// Calculate the number of states to evaluate.
		int cv$numStates = Math.max(0, state.nCoins);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Guards to ensure that st is only updated when there is a valid path.
			// 
			// Value of the variable at this index
			state.st[i] = cv$valuePos;
			
			// Constructing a random variable input for use later.
			double[] var68 = state.m[state.st[(i - 1)]];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			double cv$accumulatedProbabilities = (((((cv$valuePos < state.nCoins) && (0 < state.nCoins)) && (0.0 <= var68[cv$valuePos])) && (var68[cv$valuePos] <= 1.0))?Math.log(var68[cv$valuePos]):Double.NEGATIVE_INFINITY);
			int index$i$2_2 = (i + 1);
			if(((index$i$2_2 < state.nFlips) && (state.fixedFlag$sample71 || state.constrainedFlag$sample71[(index$i$2_2 - 1)]))) {
				// Processing sample task 71 of consumer random variable null.
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 69.
				// 
				// Looking for a path between Sample 71 and consumer Categorical 69.
				// 
				// Value of the variable at this index
				double[] sc$var68$1 = state.m[cv$valuePos];
				
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
				cv$accumulatedProbabilities = (((((((0.0 <= state.st[index$i$2_2]) && (state.st[index$i$2_2] < state.nCoins)) && (0 < state.nCoins)) && (0.0 <= sc$var68$1[state.st[index$i$2_2]])) && (sc$var68$1[state.st[index$i$2_2]] <= 1.0))?Math.log(sc$var68$1[state.st[index$i$2_2]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample71[(i - 1)] = true;
			
			// Constructing a random variable input for use later.
			// 
			// Looking for a path between Sample 71 and consumer Bernoulli 85.
			// 
			// Value of the variable at this index
			double var84 = state.bias[cv$valuePos];
			
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
			// Substituted "j" with its value "i".
			cv$accumulatedProbabilities = ((((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[i]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var70$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample71[(i - 1)]) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var70$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var70$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else {
				// Initialize the sum of the array elements
				double cv$lseSum = 0.0;
				
				// Offset values, move to normal space, and sum.
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					// Get a local reference to the scratch space.
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var70$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Get a local reference to the scratch space.
					scratch.cv$var70$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
															// Get a local reference to the scratch space.
					scratch.cv$var70$stateProbabilityGlobal[cv$indexName] = Math.exp((scratch.cv$var70$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var70$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var70$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Guards to ensure that st is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			// 
			// Get a local reference to the scratch space.
			state.st[i] = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var70$stateProbabilityGlobal, cv$numStates);
		}
	}

	// Calculate the probability of the samples represented by sample28 using sampled
	// values.
	private final void logProbabilityValue$sample28() {
		// Determine if we need to calculate the values for sample task 28 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample28) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var27 = 0; var27 < state.nCoins; var27 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(state.m[var27], state.v, state.nCoins));
			
			// Store the random variable instance probability
			state.logProbability$var28 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$m = (state.logProbability$m + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample28)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample28 = state.fixedFlag$sample28;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$m = (state.logProbability$m + state.logProbability$var28);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var28);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample28)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var28);
		}
	}

	// Calculate the probability of the samples represented by sample32 using sampled
	// values.
	private final void logProbabilityValue$sample32() {
		// Determine if we need to calculate the values for sample task 32 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample32) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(state.initialCoin, state.v, state.nCoins);
			
			// Store the sample task probability
			state.logProbability$initialCoin = cv$distributionAccumulator;
			
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
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample32)
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
			state.fixedProbFlag$sample32 = state.fixedFlag$sample32;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$initialCoin);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample32)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$initialCoin);
		}
	}

	// Calculate the probability of the samples represented by sample47 using sampled
	// values.
	private final void logProbabilityValue$sample47() {
		// Determine if we need to calculate the values for sample task 47 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample47) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var45 = 0; var45 < state.nCoins; var45 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(state.bias[var45], 1.0, 1.0));
			
			// Store the random variable instance probability
			state.logProbability$var46 = cv$sampleAccumulator;
			
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
			if(state.fixedFlag$sample47)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample47 = state.fixedFlag$sample47;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$var46);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var46);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample47)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var46);
		}
	}

	// Calculate the probability of the samples represented by sample53 using sampled
	// values.
	private final void logProbabilityValue$sample53() {
		// Determine if we need to calculate the values for sample task 53 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample53) {
			// Generating probabilities for sample task
			// The sample value to calculate the probability of generating
			int cv$sampleValue = state.st[0];
			
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
			double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.nCoins)) && (0 < state.nCoins)) && (0.0 <= state.initialCoin[cv$sampleValue])) && (state.initialCoin[cv$sampleValue] <= 1.0))?Math.log(state.initialCoin[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$var52 = cv$distributionAccumulator;
			
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
			state.logProbability$st = (state.logProbability$st + cv$distributionAccumulator);
			
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
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample53)
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
			state.fixedProbFlag$sample53 = (state.fixedFlag$sample53 && state.fixedFlag$sample32);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$st = (state.logProbability$st + state.logProbability$var52);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var52);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample53)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var52);
		}
	}

	// Calculate the probability of the samples represented by sample71 using sampled
	// values.
	private final void logProbabilityValue$sample71() {
		// Determine if we need to calculate the values for sample task 71 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample71) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i = 1; i < state.nFlips; i += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = state.st[i];
				double[] var68 = state.m[state.st[(i - 1)]];
				
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
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.nCoins)) && (0 < state.nCoins)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample71[(i - 1)] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample71)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample71 = ((state.fixedFlag$sample71 && state.fixedFlag$sample28) && state.fixedFlag$sample53);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 1; i < state.nFlips; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample71[(i - 1)]);
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample71)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample87 using sampled
	// values.
	private final void logProbabilityValue$sample87() {
		// Determine if we need to calculate the values for sample task 87 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample87) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.nFlips; j += 1) {
				double var84 = state.bias[state.st[j]];
				
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
				double cv$distributionAccumulator = (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample87[j] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample87 = ((state.fixedFlag$sample47 && state.fixedFlag$sample53) && state.fixedFlag$sample71);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.nFlips; j += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample87[j]);
			
			// Update the variable probability
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample28)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.nCoins, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.nCoins, state.m[var27]);
				}
			);

		if(!state.fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.nCoins, state.initialCoin);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample47)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.nCoins, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
							state.bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialCoin, state.nCoins);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample71) {
			for(int i = 1; i < state.nFlips; i += 1)
				state.st[i] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i - 1)]], state.nCoins);
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.nFlips, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j = forStart$j; j < forEnd$j; j += 1)
						state.flips[j] = DistributionSampling.sampleBernoulli(RNG$1, state.bias[state.st[j]]);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample28)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.nCoins, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.nCoins, state.m[var27]);
				}
			);

		if(!state.fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.nCoins, state.initialCoin);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample47)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.nCoins, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
							state.bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialCoin, state.nCoins);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample71) {
			for(int i = 1; i < state.nFlips; i += 1)
				state.st[i] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i - 1)]], state.nCoins);
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample28)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.nCoins, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.nCoins, state.m[var27]);
				}
			);

		if(!state.fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.nCoins, state.initialCoin);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample47)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.nCoins, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
							state.bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialCoin, state.nCoins);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample71) {
			for(int i = 1; i < state.nFlips; i += 1)
				state.st[i] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i - 1)]], state.nCoins);
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.nFlips, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j = forStart$j; j < forEnd$j; j += 1)
						state.flips[j] = DistributionSampling.sampleBernoulli(RNG$1, state.bias[state.st[j]]);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample28)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.nCoins, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.nCoins, state.m[var27]);
				}
			);

		if(!state.fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.nCoins, state.initialCoin);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample47)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.nCoins, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
							state.bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialCoin, state.nCoins);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample71) {
			for(int i = 1; i < state.nFlips; i += 1)
				state.st[i] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i - 1)]], state.nCoins);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample28)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.nCoins, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.nCoins, state.m[var27]);
				}
			);

		if(!state.fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.nCoins, state.initialCoin);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample47)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.nCoins, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
							state.bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialCoin, state.nCoins);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample71) {
			for(int i = 1; i < state.nFlips; i += 1)
				state.st[i] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i - 1)]], state.nCoins);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample28)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, state.nCoins, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								inferSample28(var27, threadID$var27, RNG$1);
					}
				);

			if(!state.fixedFlag$sample32)
				inferSample32();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample47)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, state.nCoins, 1,
					(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
								inferSample47(var45, threadID$var45, RNG$1);
					}
				);

			if(!state.fixedFlag$sample53)
				inferSample53();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample71) {
				for(int i = 1; i < state.nFlips; i += 1)
					inferSample71(i);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample71) {
				for(int i = (state.nFlips - 1); i >= 1; i -= 1)
					inferSample71(i);
			}
			if(!state.fixedFlag$sample53)
				inferSample53();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample47)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, state.nCoins, 1,
					(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
								inferSample47(var45, threadID$var45, RNG$1);
					}
				);

			if(!state.fixedFlag$sample32)
				inferSample32();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample28)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, state.nCoins, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								inferSample28(var27, threadID$var27, RNG$1);
					}
				);

		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.nCoins, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						if(!state.constrainedFlag$sample28[var27])
							drawValueSample28(var27, threadID$var27, RNG$1);
					}
			}
		);
		if(!state.constrainedFlag$sample32)
			drawValueSample32();
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.nCoins, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!state.constrainedFlag$sample47[var45])
							drawValueSample47(var45, threadID$var45, RNG$1);
					}
			}
		);
		if(!state.constrainedFlag$sample53)
			drawValueSample53();
		for(int i = 1; i < state.nFlips; i += 1) {
			if(!state.constrainedFlag$sample71[(i - 1)])
				drawValueSample71(i);
		}
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
		state.logProbability$m = 0.0;
		if(!state.fixedProbFlag$sample28)
			state.logProbability$var28 = Double.NaN;
		if(!state.fixedProbFlag$sample32)
			state.logProbability$initialCoin = Double.NaN;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample47)
			state.logProbability$var46 = Double.NaN;
		state.logProbability$st = 0.0;
		if(!state.fixedProbFlag$sample53)
			state.logProbability$var52 = Double.NaN;
		if(!state.fixedProbFlag$sample71) {
			for(int i = 1; i < state.nFlips; i += 1)
				state.logProbability$sample71[(i - 1)] = Double.NaN;
		}
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample87) {
			for(int j = 0; j < state.nFlips; j += 1)
				state.logProbability$sample87[j] = Double.NaN;
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		for(int var13 = 0; var13 < state.nCoins; var13 += 1)
			state.v[var13] = 0.1;
		state.nFlips = state.length$measured;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample47$1 = 0; index$constrainedFlag$sample47$1 < state.constrainedFlag$sample47.length; index$constrainedFlag$sample47$1 += 1)
			state.constrainedFlag$sample47[index$constrainedFlag$sample47$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < state.constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
			state.constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample71$1 = 0; index$constrainedFlag$sample71$1 < state.constrainedFlag$sample71.length; index$constrainedFlag$sample71$1 += 1)
			state.constrainedFlag$sample71[index$constrainedFlag$sample71$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(state.fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(state.fixedFlag$sample47)
			logProbabilityValue$sample47();
		if(state.fixedFlag$sample53)
			logProbabilityValue$sample53();
		if(state.fixedFlag$sample71)
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

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = state.flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.flips[cv$index1] = state.measured[cv$index1];
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