package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMTestPart6$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMTestPart6.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMTestPart6$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$var28$countGlobal;
		double[] cv$var52$stateProbabilityGlobal;
		double[] cv$var77$stateProbabilityGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for cv$var28$countGlobal
			// 
			// Allocation of cv$var28$countGlobal for single threaded execution
			cv$var28$countGlobal = new double[2];
			
			// Constructor for cv$var52$stateProbabilityGlobal
			// 
			// Allocation of cv$var52$stateProbabilityGlobal for single threaded execution
			// 
			// Variable to record the maximum value of Task Get 51. Initially set to the value
			// of putTask 29.
			cv$var52$stateProbabilityGlobal = new double[2];
			
			// Allocation of cv$var77$stateProbabilityGlobal for single threaded execution
			// 
			// Variable to record the maximum value of Task Get 76. Initially set to the value
			// of putTask 29.
			cv$var77$stateProbabilityGlobal = new double[2];
		}
	}


	public HMMTestPart6$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample28
	private final void drawValueSample28(int var27) {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[var27]);
	}

	// Pick a value from the distribution for the unconditioned variable from sample45
	private final void drawValueSample45(int var43) {
		state.bias[var43] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample53
	private final void drawValueSample53() {
		state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
	}

	// Pick a value from the distribution for the unconditioned variable from sample78
	private final void drawValueSample78(int i$var66) {
		state.st[(i$var66 - 3)] = DistributionSampling.sampleCategorical(state.RNG$, state.m[(1 - state.st[(i$var66 - 4)])], 2);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 28 drawn from Dirichlet 16. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample28(int var27) {
		state.constrainedFlag$sample28[var27] = false;
		
		// A local reference to the scratch space.
		scratch.cv$var28$countGlobal[0] = 0.0;
		
		// A local reference to the scratch space.
		scratch.cv$var28$countGlobal[1] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(((var27 == 0) && (state.fixedFlag$sample53 || state.constrainedFlag$sample53))) {
			// Looking for a path between Sample 28 and consumer Categorical 51.
			// 
			// Processing sample task 53 of consumer random variable null.
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample28[0] = true;
			
			// Increment the sample counter with the value sampled by sample task 53 of random
			// variable var51
			// 
									// A local reference to the scratch space.
			scratch.cv$var28$countGlobal[state.st[0]] = (scratch.cv$var28$countGlobal[state.st[0]] + 1.0);
		}
		
		// Processing random variable 76.
		// 
		// Looking for a path between Sample 28 and consumer Categorical 76.
		for(int i$var66 = 4; i$var66 < (state.samples + 3); i$var66 += 1) {
			if(((var27 == (1 - state.st[(i$var66 - 4)])) && (state.fixedFlag$sample78 || state.constrainedFlag$sample78[(i$var66 - 4)]))) {
				// Processing sample task 78 of consumer random variable null.
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample28[var27] = true;
				
				// Increment the sample counter with the value sampled by sample task 78 of random
				// variable var76
				// 
												// A local reference to the scratch space.
				scratch.cv$var28$countGlobal[state.st[(i$var66 - 3)]] = (scratch.cv$var28$countGlobal[state.st[(i$var66 - 3)]] + 1.0);
			}
		}
		if(state.constrainedFlag$sample28[var27])
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
									// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, scratch.cv$var28$countGlobal, state.m[var27], 2);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 45 drawn from Beta 32. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void inferSample45(int var43) {
		state.constrainedFlag$sample45[var43] = false;
		
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		
		// Processing random variable 101.
		// 
		// Looking for a path between Sample 45 and consumer Bernoulli 101.
		for(int j = 5; j < (state.samples + 5); j += 1) {
			if((var43 == (1 - state.st[(j - 5)]))) {
				// Processing sample task 103 of consumer random variable null.
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample45[var43] = true;
				
				// Include the value sampled by task 103 from random variable var101.
				// 
				// Increment the number of samples.
				cv$count = (cv$count + 1);
				
				// If the sample value was positive increase the count
				if(state.flips[(j - 5)])
					cv$sum = (cv$sum + 1);
			}
		}
		if(state.constrainedFlag$sample45[var43])
			// Guards to ensure that bias is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			state.bias[var43] = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 53 drawn from Categorical 51. Inference was performed using variable
	// marginalization.
	private final void inferSample53() {
		state.constrainedFlag$sample53 = false;
		
		// Unrolled loop
		{
			// Guards to ensure that st is only updated when there is a valid path.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			state.st[0] = 0;
			
			// Constructing a random variable input for use later.
			double[] var50 = state.m[0];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= var50[0]) && (var50[0] <= 1.0))?Math.log(var50[0]):Double.NEGATIVE_INFINITY);
			
			// Substituted "i$var66" with its value "4".
			if(((1 < state.samples) && (state.fixedFlag$sample78 || state.constrainedFlag$sample78[0]))) {
				// Processing sample task 78 of consumer random variable null.
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample53 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 76.
				// 
				// Looking for a path between Sample 53 and consumer Categorical 76.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double[] var75 = state.m[1];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 78 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "i$var66" with its value "4".
				cv$accumulatedProbabilities = ((((((0.0 <= state.st[1]) && (state.st[1] < 2)) && (0.0 <= var75[state.st[1]])) && (var75[state.st[1]] <= 1.0))?Math.log(var75[state.st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Substituted "j" with its value "5".
			if((0 < state.samples)) {
				// Processing sample task 103 of consumer random variable null.
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample53 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Looking for a path between Sample 53 and consumer Bernoulli 101.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double var100 = state.bias[1];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 103 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "j" with its value "5".
				cv$accumulatedProbabilities = ((((0.0 <= var100) && (var100 <= 1.0))?Math.log((state.flips[0]?var100:(1.0 - var100))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var52$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Guards to ensure that st is only updated when there is a valid path.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.st[0] = 1;
		
		// Constructing a random variable input for use later.
		double[] var50 = state.m[0];
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
						// Value of the variable at this index
		// 
						// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= var50[1]) && (var50[1] <= 1.0))?Math.log(var50[1]):Double.NEGATIVE_INFINITY);
		
		// Substituted "i$var66" with its value "4".
		if(((1 < state.samples) && (state.fixedFlag$sample78 || state.constrainedFlag$sample78[0]))) {
			// Processing sample task 78 of consumer random variable null.
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample53 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Processing random variable 76.
			// 
			// Looking for a path between Sample 53 and consumer Categorical 76.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double[] var75 = state.m[0];
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 78 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
									// Substituted "i$var66" with its value "4".
			cv$accumulatedProbabilities = ((((((0.0 <= state.st[1]) && (state.st[1] < 2)) && (0.0 <= var75[state.st[1]])) && (var75[state.st[1]] <= 1.0))?Math.log(var75[state.st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Substituted "j" with its value "5".
		if((0 < state.samples)) {
			// Processing sample task 103 of consumer random variable null.
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample53 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Looking for a path between Sample 53 and consumer Bernoulli 101.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double var100 = state.bias[0];
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 103 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "j" with its value "5".
			cv$accumulatedProbabilities = ((((0.0 <= var100) && (var100 <= 1.0))?Math.log((state.flips[0]?var100:(1.0 - var100))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		scratch.cv$var52$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
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
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = scratch.cv$var52$stateProbabilityGlobal[1];
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
				cv$logSum = (Math.log((Math.exp((scratch.cv$var52$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var52$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var52$stateProbabilityGlobal[0] = 0.5;
				
												// Get a local reference to the scratch space.
				scratch.cv$var52$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var52$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var52$stateProbabilityGlobal[0] - cv$logSum));
				
												// Get a local reference to the scratch space.
				scratch.cv$var52$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var52$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
									// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var52$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var52$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Guards to ensure that st is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var52$stateProbabilityGlobal, 2);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 78 drawn from Categorical 76. Inference was performed using variable
	// marginalization.
	private final void inferSample78(int i$var66) {
		state.constrainedFlag$sample78[(i$var66 - 4)] = false;
		
		// Unrolled loop
		{
			// Guards to ensure that st is only updated when there is a valid path.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			state.st[(i$var66 - 3)] = 0;
			
			// Constructing a random variable input for use later.
			double[] var75 = state.m[(1 - state.st[(i$var66 - 4)])];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= var75[0]) && (var75[0] <= 1.0))?Math.log(var75[0]):Double.NEGATIVE_INFINITY);
			int index$i$2_2 = (i$var66 + 1);
			if(((index$i$2_2 < (state.samples + 3)) && (state.fixedFlag$sample78 || state.constrainedFlag$sample78[(index$i$2_2 - 4)]))) {
				// Processing sample task 78 of consumer random variable null.
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 76.
				// 
				// Looking for a path between Sample 78 and consumer Categorical 76.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double[] sc$var75$1 = state.m[1];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 78 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((((0.0 <= state.st[(index$i$2_2 - 3)]) && (state.st[(index$i$2_2 - 3)] < 2)) && (0.0 <= sc$var75$1[state.st[(index$i$2_2 - 3)]])) && (sc$var75$1[state.st[(index$i$2_2 - 3)]] <= 1.0))?Math.log(sc$var75$1[state.st[(index$i$2_2 - 3)]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample78[(i$var66 - 4)] = true;
			
			// Constructing a random variable input for use later.
			// 
			// Looking for a path between Sample 78 and consumer Bernoulli 101.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double var100 = state.bias[1];
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 103 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "j" with its value "(i$var66 + 2)".
			cv$accumulatedProbabilities = ((((0.0 <= var100) && (var100 <= 1.0))?Math.log((state.flips[(i$var66 - 3)]?var100:(1.0 - var100))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var77$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Guards to ensure that st is only updated when there is a valid path.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.st[(i$var66 - 3)] = 1;
		
		// Constructing a random variable input for use later.
		double[] var75 = state.m[(1 - state.st[(i$var66 - 4)])];
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
						// Value of the variable at this index
		// 
						// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= var75[1]) && (var75[1] <= 1.0))?Math.log(var75[1]):Double.NEGATIVE_INFINITY);
		int index$i$2_2 = (i$var66 + 1);
		if(((index$i$2_2 < (state.samples + 3)) && (state.fixedFlag$sample78 || state.constrainedFlag$sample78[(index$i$2_2 - 4)]))) {
			// Processing sample task 78 of consumer random variable null.
			// Constructing a random variable input for use later.
			// 
			// Processing random variable 76.
			// 
			// Looking for a path between Sample 78 and consumer Categorical 76.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double[] sc$var75$1 = state.m[0];
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 78 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			cv$accumulatedProbabilities = ((((((0.0 <= state.st[(index$i$2_2 - 3)]) && (state.st[(index$i$2_2 - 3)] < 2)) && (0.0 <= sc$var75$1[state.st[(index$i$2_2 - 3)]])) && (sc$var75$1[state.st[(index$i$2_2 - 3)]] <= 1.0))?Math.log(sc$var75$1[state.st[(index$i$2_2 - 3)]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Mark that the sample has observed constrained data.
		state.constrainedFlag$sample78[(i$var66 - 4)] = true;
		
		// Constructing a random variable input for use later.
		// 
		// Looking for a path between Sample 78 and consumer Bernoulli 101.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double var100 = state.bias[0];
		
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 103 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// Variable declaration of cv$accumulatedConsumerProbabilities moved.
		// Declaration comment was:
		// Set an accumulator to sum the probabilities for each possible configuration of
		// inputs.
		// 
		// Substituted "j" with its value "(i$var66 + 2)".
		cv$accumulatedProbabilities = ((((0.0 <= var100) && (var100 <= 1.0))?Math.log((state.flips[(i$var66 - 3)]?var100:(1.0 - var100))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		scratch.cv$var77$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample78[(i$var66 - 4)]) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var77$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = scratch.cv$var77$stateProbabilityGlobal[1];
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
				cv$logSum = (Math.log((Math.exp((scratch.cv$var77$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var77$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var77$stateProbabilityGlobal[0] = 0.5;
				
												// Get a local reference to the scratch space.
				scratch.cv$var77$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var77$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var77$stateProbabilityGlobal[0] - cv$logSum));
				
												// Get a local reference to the scratch space.
				scratch.cv$var77$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var77$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
									// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var77$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var77$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Guards to ensure that st is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.st[(i$var66 - 3)] = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var77$stateProbabilityGlobal, 2);
		}
	}

	// Calculate the probability of the samples represented by sample103 using sampled
	// values.
	private final void logProbabilityValue$sample103() {
		// Determine if we need to calculate the values for sample task 103 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample103) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 5; j < (state.samples + 5); j += 1) {
				double var100 = state.bias[(1 - state.st[(j - 5)])];
				
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
				double cv$distributionAccumulator = (((0.0 <= var100) && (var100 <= 1.0))?Math.log((state.flips[(j - 5)]?var100:(1.0 - var100))):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample103[(j - 5)] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample103 = ((state.fixedFlag$sample45 && state.fixedFlag$sample53) && state.fixedFlag$sample78);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 5; j < (state.samples + 5); j += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample103[(j - 5)]);
			
			// Update the variable probability
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample28 using sampled
	// values.
	private final void logProbabilityValue$sample28() {
		// Determine if we need to calculate the values for sample task 28 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample28) {
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
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityDirichlet(state.m[0], state.v, 2) + DistributionSampling.logProbabilityDirichlet(state.m[1], state.v, 2));
			
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

	// Calculate the probability of the samples represented by sample45 using sampled
	// values.
	private final void logProbabilityValue$sample45() {
		// Determine if we need to calculate the values for sample task 45 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample45) {
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
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityBeta(state.bias[0], 1.0, 1.0) + DistributionSampling.logProbabilityBeta(state.bias[1], 1.0, 1.0));
			
			// Store the random variable instance probability
			state.logProbability$var44 = cv$sampleAccumulator;
			
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
			if(state.fixedFlag$sample45)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample45 = state.fixedFlag$sample45;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$var44);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var44);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample45)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var44);
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
			double[] var50 = state.m[0];
			
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
			double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0.0 <= var50[cv$sampleValue])) && (var50[cv$sampleValue] <= 1.0))?Math.log(var50[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			
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
			state.fixedProbFlag$sample53 = (state.fixedFlag$sample53 && state.fixedFlag$sample28);
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

	// Calculate the probability of the samples represented by sample78 using sampled
	// values.
	private final void logProbabilityValue$sample78() {
		// Determine if we need to calculate the values for sample task 78 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample78) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var66 = 4; i$var66 < (state.samples + 3); i$var66 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = state.st[(i$var66 - 3)];
				double[] var75 = state.m[(1 - state.st[(i$var66 - 4)])];
				
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
				double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0.0 <= var75[cv$sampleValue])) && (var75[cv$sampleValue] <= 1.0))?Math.log(var75[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample78[(i$var66 - 4)] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample78)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample78 = ((state.fixedFlag$sample78 && state.fixedFlag$sample28) && state.fixedFlag$sample53);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var66 = 4; i$var66 < (state.samples + 3); i$var66 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample78[(i$var66 - 4)]);
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample78)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[0]);
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[1]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample45) {
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			state.bias[1] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample78) {
			for(int i$var66 = 4; i$var66 < (state.samples + 3); i$var66 += 1)
				state.st[(i$var66 - 3)] = DistributionSampling.sampleCategorical(state.RNG$, state.m[(1 - state.st[(i$var66 - 4)])], 2);
		}
		for(int j = 5; j < (state.samples + 5); j += 1)
			state.flips[(j - 5)] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[(1 - state.st[(j - 5)])]);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[0]);
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[1]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample45) {
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			state.bias[1] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample78) {
			for(int i$var66 = 4; i$var66 < (state.samples + 3); i$var66 += 1)
				state.st[(i$var66 - 3)] = DistributionSampling.sampleCategorical(state.RNG$, state.m[(1 - state.st[(i$var66 - 4)])], 2);
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[0]);
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[1]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample45) {
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			state.bias[1] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample78) {
			for(int i$var66 = 4; i$var66 < (state.samples + 3); i$var66 += 1)
				state.st[(i$var66 - 3)] = DistributionSampling.sampleCategorical(state.RNG$, state.m[(1 - state.st[(i$var66 - 4)])], 2);
		}
		for(int j = 5; j < (state.samples + 5); j += 1)
			state.flips[(j - 5)] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[(1 - state.st[(j - 5)])]);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[0]);
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[1]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample45) {
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			state.bias[1] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample78) {
			for(int i$var66 = 4; i$var66 < (state.samples + 3); i$var66 += 1)
				state.st[(i$var66 - 3)] = DistributionSampling.sampleCategorical(state.RNG$, state.m[(1 - state.st[(i$var66 - 4)])], 2);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[0]);
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[1]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample45) {
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			state.bias[1] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample78) {
			for(int i$var66 = 4; i$var66 < (state.samples + 3); i$var66 += 1)
				state.st[(i$var66 - 3)] = DistributionSampling.sampleCategorical(state.RNG$, state.m[(1 - state.st[(i$var66 - 4)])], 2);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample28) {
				inferSample28(0);
				inferSample28(1);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample45) {
				inferSample45(0);
				inferSample45(1);
			}
			if(!state.fixedFlag$sample53)
				inferSample53();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample78) {
				for(int i$var66 = 4; i$var66 < (state.samples + 3); i$var66 += 1)
					inferSample78(i$var66);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample78) {
				for(int i$var66 = (state.samples + 2); i$var66 >= 4; i$var66 -= 1)
					inferSample78(i$var66);
			}
			if(!state.fixedFlag$sample53)
				inferSample53();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample45) {
				inferSample45(1);
				inferSample45(0);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample28) {
				inferSample28(1);
				inferSample28(0);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.constrainedFlag$sample28[0])
			drawValueSample28(0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.constrainedFlag$sample28[1])
			drawValueSample28(1);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.constrainedFlag$sample45[0])
			drawValueSample45(0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.constrainedFlag$sample45[1])
			drawValueSample45(1);
		if(!state.constrainedFlag$sample53)
			drawValueSample53();
		for(int i$var66 = 4; i$var66 < (state.samples + 3); i$var66 += 1) {
			if(!state.constrainedFlag$sample78[(i$var66 - 4)])
				drawValueSample78(i$var66);
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
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample45)
			state.logProbability$var44 = Double.NaN;
		state.logProbability$st = 0.0;
		if(!state.fixedProbFlag$sample53)
			state.logProbability$var52 = Double.NaN;
		if(!state.fixedProbFlag$sample78) {
			for(int i$var66 = 4; i$var66 < (state.samples + 3); i$var66 += 1)
				state.logProbability$sample78[(i$var66 - 4)] = Double.NaN;
		}
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample103) {
			for(int j = 5; j < (state.samples + 5); j += 1)
				state.logProbability$sample103[(j - 5)] = Double.NaN;
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		// Substituted "i$var13" with its value "0".
		state.v[0] = 0.1;
		
		// Substituted "i$var13" with its value "1".
		state.v[1] = 0.1;
		state.samples = state.length$flipsMeasured;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample78$1 = 0; index$constrainedFlag$sample78$1 < state.constrainedFlag$sample78.length; index$constrainedFlag$sample78$1 += 1)
			state.constrainedFlag$sample78[index$constrainedFlag$sample78$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < state.constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
			state.constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < state.constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
			state.constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(state.fixedFlag$sample45)
			logProbabilityValue$sample45();
		if(state.fixedFlag$sample53)
			logProbabilityValue$sample53();
		if(state.fixedFlag$sample78)
			logProbabilityValue$sample78();
		logProbabilityValue$sample103();
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
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample78();
		logProbabilityValue$sample103();
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
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample78();
		logProbabilityValue$sample103();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = state.flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.flips[cv$index1] = state.flipsMeasured[cv$index1];
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
		     + "model HMMTestPart6(boolean[] flipsMeasured) {\n"
		     + "        int states = 2;\n"
		     + "\n"
		     + "        double[] v = new double[states];\n"
		     + "        for(int i:[0..states))\n"
		     + "            v[i] = 0.1;\n"
		     + "        \n"
		     + "        double[][] m = dirichlet(v).sample(states);\n"
		     + "        double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "        int samples = flipsMeasured.length;\n"
		     + "        int[] st = new int[samples];\n"
		     + "\n"
		     + "        st[0] = categorical(m[0]).sample();\n"
		     + "\n"
		     + "        for(int i:[4..samples + 3))\n"
		     + "            st[i-3] = categorical(m[(states - 1) - st[i-4]]).sample();\n"
		     + "            \n"
		     + "        boolean[] flips = new boolean[samples];\n"
		     + "            \n"
		     + "        for(int j:[5..samples+5))\n"
		     + "            flips[j-5] = bernoulli(bias[(states - 1) - st[j-5]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}