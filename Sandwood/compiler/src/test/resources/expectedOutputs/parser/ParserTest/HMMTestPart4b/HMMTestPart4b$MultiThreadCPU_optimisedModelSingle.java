package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMTestPart4b$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMTestPart4b.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMTestPart4b$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[][] cv$var119$stateProbabilityGlobal;
		double[][] cv$var28$countGlobal;
		double[] cv$var79$stateProbabilityGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for cv$var28$countGlobal
			{
				// Allocation of cv$var28$countGlobal for multithreaded execution
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var28$countGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var28$countGlobal[cv$index] = new double[2];
			}
			
			// Constructor for cv$var79$stateProbabilityGlobal
			// 
			// Allocation of cv$var79$stateProbabilityGlobal for single threaded execution
			// 
			// Variable to record the maximum value of Task Get 80. Initially set to the value
			// of putTask 29.
			cv$var79$stateProbabilityGlobal = new double[2];
			
			// Allocation of cv$var119$stateProbabilityGlobal for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var119$stateProbabilityGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				// Variable to record the maximum value of Task Get 120. Initially set to the value
				// of putTask 29.
				cv$var119$stateProbabilityGlobal[cv$index] = new double[2];
		}
	}


	public HMMTestPart4b$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample122
	private final void drawValueSample122(int i$var95, int j$var104, int k, int threadID$cv$k, Rng RNG$) {
		state.st[i$var95][j$var104][k] = DistributionSampling.sampleCategorical(RNG$, state.m[0], 2);
	}

	// Pick a value from the distribution for the unconditioned variable from sample28
	private final void drawValueSample28(int var27, int threadID$cv$var27, Rng RNG$) {
		DistributionSampling.sampleDirichlet(RNG$, state.v, 2, state.m[var27]);
	}

	// Pick a value from the distribution for the unconditioned variable from sample45
	private final void drawValueSample45(int var43, int threadID$cv$var43, Rng RNG$) {
		state.bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample82
	private final void drawValueSample82() {
		state.st[0][0][0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 122 drawn from Categorical 118. Inference was performed using variable
	// marginalization.
	private final void inferSample122(int i$var95, int j$var104, int k, int threadID$cv$k, Rng RNG$) {
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = scratch.cv$var119$stateProbabilityGlobal[threadID$cv$k];
		
		// Unrolled loop
		{
			// Constructing a random variable input for use later.
			double[] var117 = state.m[0];
			
			// Constructing a random variable input for use later.
			// 
			// Looking for a path between Sample 122 and consumer Bernoulli 183.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "0".
			double var182 = state.bias[0];
			
			// Save the calculated index value into the array of index value probabilities
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
			// Recorded the probability of reaching sample task 189 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
									// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			cv$stateProbabilityLocal[0] = ((((0.0 <= var182) && (var182 <= 1.0))?Math.log((state.flips[j$var104][k][i$var95]?var182:(1.0 - var182))):Double.NEGATIVE_INFINITY) + (((0.0 <= var117[0]) && (var117[0] <= 1.0))?Math.log(var117[0]):Double.NEGATIVE_INFINITY));
		}
		
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.st[i$var95][j$var104][k] = 1;
		
		// Constructing a random variable input for use later.
		double[] var117 = state.m[0];
		
		// Mark that the sample has observed constrained data.
		state.constrainedFlag$sample122[(i$var95 - 1)][j$var104][k] = true;
		
		// Constructing a random variable input for use later.
		// 
		// Looking for a path between Sample 122 and consumer Bernoulli 183.
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double var182 = state.bias[1];
		
		// Save the calculated index value into the array of index value probabilities
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
		// Recorded the probability of reaching sample task 189 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
						// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
						// Value of the variable at this index
		// 
						// Substituted "cv$valuePos" with its value "1".
		cv$stateProbabilityLocal[1] = ((((0.0 <= var182) && (var182 <= 1.0))?Math.log((state.flips[j$var104][k][i$var95]?var182:(1.0 - var182))):Double.NEGATIVE_INFINITY) + (((0.0 <= var117[1]) && (var117[1] <= 1.0))?Math.log(var117[1]):Double.NEGATIVE_INFINITY));
		if(state.constrainedFlag$sample122[(i$var95 - 1)][j$var104][k]) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			double cv$lseMax = cv$stateProbabilityLocal[0];
			
			// Unrolled loop
			double cv$lseElementValue = cv$stateProbabilityLocal[1];
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
				// Initialize the sum of the array elements
				cv$logSum = (Math.log((Math.exp((cv$stateProbabilityLocal[0] - cv$lseMax)) + Math.exp((cv$stateProbabilityLocal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
								// cv$numStates's comment
				// variable marginalization
				cv$stateProbabilityLocal[0] = 0.5;
				
								// cv$numStates's comment
				// variable marginalization
				cv$stateProbabilityLocal[1] = 0.5;
			} else {
				// Unrolled loop
				cv$stateProbabilityLocal[0] = Math.exp((cv$stateProbabilityLocal[0] - cv$logSum));
				cv$stateProbabilityLocal[1] = Math.exp((cv$stateProbabilityLocal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
						// cv$numStates's comment
			// variable marginalization
			for(int cv$indexName = 2; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			// 
						// cv$numStates's comment
			// variable marginalization
			state.st[i$var95][j$var104][k] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 28 drawn from Dirichlet 16. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample28(int var27, int threadID$cv$var27, Rng RNG$) {
		state.constrainedFlag$sample28[var27] = false;
		
		// A local reference to the scratch space.
		double[] cv$countLocal = scratch.cv$var28$countGlobal[threadID$cv$var27];
		cv$countLocal[0] = 0.0;
		cv$countLocal[1] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((var27 == 0)) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((state.fixedFlag$sample82 || state.constrainedFlag$sample82)) {
				// Looking for a path between Sample 28 and consumer Categorical 78.
				// 
				// Processing sample task 82 of consumer random variable null.
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample28[0] = true;
				
				// Increment the sample counter with the value sampled by sample task 82 of random
				// variable var78
				cv$countLocal[state.st[0][0][0]] = (cv$countLocal[state.st[0][0][0]] + 1.0);
			}
			
			// Processing random variable 118.
			// 
			// Looking for a path between Sample 28 and consumer Categorical 118.
			for(int i$var95 = 1; i$var95 < state.samples; i$var95 += 1) {
				for(int j$var104 = 0; j$var104 < state.samples; j$var104 += 1) {
					for(int k = 0; k < state.samples; k += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((state.fixedFlag$sample122 || state.constrainedFlag$sample122[(i$var95 - 1)][j$var104][k])) {
							// Mark that the sample has observed constrained data.
							state.constrainedFlag$sample28[0] = true;
							
							// Increment the sample counter with the value sampled by sample task 122 of random
							// variable var118
							cv$countLocal[state.st[i$var95][j$var104][k]] = (cv$countLocal[state.st[i$var95][j$var104][k]] + 1.0);
						}
					}
				}
			}
		}
		if(state.constrainedFlag$sample28[var27])
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
			// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(RNG$, state.v, cv$countLocal, state.m[var27], 2);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 45 drawn from Beta 32. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void inferSample45(int var43, int threadID$cv$var43, Rng RNG$) {
		state.constrainedFlag$sample45[var43] = false;
		
		// Local variable to record the number of true samples.
		int cv$sum = 0;
		
		// Local variable to record the number of samples.
		int cv$count = 0;
		
		// Processing random variable 183.
		// 
		// Looking for a path between Sample 45 and consumer Bernoulli 183.
		for(int l = 0; l < state.samples; l += 1) {
			for(int p = 0; p < state.samples; p += 1) {
				for(int n = 0; n < state.samples; n += 1) {
					if((var43 == state.st[p][l][n])) {
						// Processing sample task 189 of consumer random variable null.
						// Mark that the sample has observed constrained data.
						state.constrainedFlag$sample45[var43] = true;
						
						// Include the value sampled by task 189 from random variable var183.
						// 
						// Increment the number of samples.
						cv$count = (cv$count + 1);
						
						// If the sample value was positive increase the count
						if(state.flips[l][n][p])
							cv$sum = (cv$sum + 1);
					}
				}
			}
		}
		if(state.constrainedFlag$sample45[var43])
			// Guards to ensure that bias is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			state.bias[var43] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 82 drawn from Categorical 78. Inference was performed using variable
	// marginalization.
	private final void inferSample82() {
		state.constrainedFlag$sample82 = false;
		
		// Unrolled loop
		{
			// Constructing a random variable input for use later.
			double[] var77 = state.m[0];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= var77[0]) && (var77[0] <= 1.0))?Math.log(var77[0]):Double.NEGATIVE_INFINITY);
			
			// Substituted "p" with its value "0".
			if((0 < state.samples)) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample82 = true;
				
				// Constructing a random variable input for use later.
				// 
				// Looking for a path between Sample 82 and consumer Bernoulli 183.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				double var182 = state.bias[0];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 189 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "l" with its value "0".
				cv$accumulatedProbabilities = ((((0.0 <= var182) && (var182 <= 1.0))?Math.log((state.flips[0][0][0]?var182:(1.0 - var182))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var79$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.st[0][0][0] = 1;
		
		// Constructing a random variable input for use later.
		double[] var77 = state.m[0];
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
						// Value of the variable at this index
		// 
						// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= var77[1]) && (var77[1] <= 1.0))?Math.log(var77[1]):Double.NEGATIVE_INFINITY);
		
		// Substituted "p" with its value "0".
		if((0 < state.samples)) {
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample82 = true;
			
			// Constructing a random variable input for use later.
			// 
			// Looking for a path between Sample 82 and consumer Bernoulli 183.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			double var182 = state.bias[1];
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 189 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
									// Substituted "l" with its value "0".
			cv$accumulatedProbabilities = ((((0.0 <= var182) && (var182 <= 1.0))?Math.log((state.flips[0][0][0]?var182:(1.0 - var182))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		scratch.cv$var79$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample82) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var79$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = scratch.cv$var79$stateProbabilityGlobal[1];
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
				cv$logSum = (Math.log((Math.exp((scratch.cv$var79$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var79$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var79$stateProbabilityGlobal[0] = 0.5;
				
												// Get a local reference to the scratch space.
				scratch.cv$var79$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var79$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var79$stateProbabilityGlobal[0] - cv$logSum));
				
												// Get a local reference to the scratch space.
				scratch.cv$var79$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var79$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
									// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var79$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var79$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.st[0][0][0] = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var79$stateProbabilityGlobal, 2);
		}
	}

	// Calculate the probability of the samples represented by sample122 using sampled
	// values.
	private final void logProbabilityValue$sample122() {
		// Determine if we need to calculate the values for sample task 122 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample122) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var95 = 1; i$var95 < state.samples; i$var95 += 1) {
				for(int j$var104 = 0; j$var104 < state.samples; j$var104 += 1) {
					for(int k = 0; k < state.samples; k += 1) {
						// The sample value to calculate the probability of generating
						int cv$sampleValue = state.st[i$var95][j$var104][k];
						double[] var117 = state.m[0];
						
						// Record that the sample was reached.
						cv$sampleReached = true;
						
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
						cv$sampleAccumulator = (cv$sampleAccumulator + (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0.0 <= var117[cv$sampleValue])) && (var117[cv$sampleValue] <= 1.0))?Math.log(var117[cv$sampleValue]):Double.NEGATIVE_INFINITY));
					}
				}
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
				state.logProbability$var119 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$st = (state.logProbability$st + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample122)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample122 = (state.fixedFlag$sample122 && state.fixedFlag$sample28);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$st = (state.logProbability$st + state.logProbability$var119);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var119);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample122)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var119);
		}
	}

	// Calculate the probability of the samples represented by sample189 using sampled
	// values.
	private final void logProbabilityValue$sample189() {
		// Determine if we need to calculate the values for sample task 189 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample189) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int l = 0; l < state.samples; l += 1) {
				for(int p = 0; p < state.samples; p += 1) {
					for(int n = 0; n < state.samples; n += 1) {
						double var182 = state.bias[state.st[p][l][n]];
						
						// Record that the sample was reached.
						cv$sampleReached = true;
						
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
						cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var182) && (var182 <= 1.0))?Math.log((state.flips[l][n][p]?var182:(1.0 - var182))):Double.NEGATIVE_INFINITY));
					}
				}
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
				state.logProbability$var184 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$flips = (state.logProbability$flips + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample189 = ((state.fixedFlag$sample45 && state.fixedFlag$sample82) && state.fixedFlag$sample122);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$flips = (state.logProbability$flips + state.logProbability$var184);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var184);
			
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var184);
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

	// Calculate the probability of the samples represented by sample82 using sampled
	// values.
	private final void logProbabilityValue$sample82() {
		// Determine if we need to calculate the values for sample task 82 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample82) {
			// Generating probabilities for sample task
			// The sample value to calculate the probability of generating
			int cv$sampleValue = state.st[0][0][0];
			double[] var77 = state.m[0];
			
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
			double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0.0 <= var77[cv$sampleValue])) && (var77[cv$sampleValue] <= 1.0))?Math.log(var77[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$var79 = cv$distributionAccumulator;
			
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
			if(state.fixedFlag$sample82)
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
			state.fixedProbFlag$sample82 = (state.fixedFlag$sample82 && state.fixedFlag$sample28);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$st = (state.logProbability$st + state.logProbability$var79);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var79);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample82)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var79);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample28)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 2, state.m[var27]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample45)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample82)
			state.st[0][0][0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample122)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 1, state.samples, 1,
				(int forStart$i$var95, int forEnd$i$var95, int threadID$i$var95, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var95 = forStart$i$var95; i$var95 < forEnd$i$var95; i$var95 += 1) {
							int[][] var114 = state.st[i$var95];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.samples, 1,
								(int forStart$index$j$var104, int forEnd$index$j$var104, int threadID$index$j$var104, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int index$j$var104 = forStart$index$j$var104; index$j$var104 < forEnd$index$j$var104; index$j$var104 += 1) {
											int j$var104 = index$j$var104;
											int threadID$j$var104 = threadID$index$j$var104;
											
											//  Outer loop for dispatching multiple batches of iterations to execute in parallel
											parallelFor(RNG$2, 0, state.samples, 1,
												(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
													
														// Inner loop for running batches of iterations, each batch has its own random number
														// generator.
														for(int k = forStart$k; k < forEnd$k; k += 1)
															var114[j$var104][k] = DistributionSampling.sampleCategorical(RNG$3, state.m[0], 2);
												}
											);
										}
								}
							);
						}
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$index$l, int forEnd$index$l, int threadID$index$l, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$l = forStart$index$l; index$l < forEnd$index$l; index$l += 1) {
						int l = index$l;
						int threadID$l = threadID$index$l;
						boolean[][] var177 = state.flips[l];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.samples, 1,
							(int forStart$index$p, int forEnd$index$p, int threadID$index$p, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int index$p = forStart$index$p; index$p < forEnd$index$p; index$p += 1) {
										int p = index$p;
										int threadID$p = threadID$index$p;
										
										//  Outer loop for dispatching multiple batches of iterations to execute in parallel
										parallelFor(RNG$2, 0, state.samples, 1,
											(int forStart$n, int forEnd$n, int threadID$n, org.sandwood.random.internal.Rng RNG$3) -> { 
												
													// Inner loop for running batches of iterations, each batch has its own random number
													// generator.
													for(int n = forStart$n; n < forEnd$n; n += 1)
														var177[n][p] = DistributionSampling.sampleBernoulli(RNG$3, state.bias[state.st[p][l][n]]);
											}
										);
									}
							}
						);
					}
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
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 2, state.m[var27]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample45)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample82)
			state.st[0][0][0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample122)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 1, state.samples, 1,
				(int forStart$i$var95, int forEnd$i$var95, int threadID$i$var95, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var95 = forStart$i$var95; i$var95 < forEnd$i$var95; i$var95 += 1) {
							int[][] var114 = state.st[i$var95];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.samples, 1,
								(int forStart$index$j$var104, int forEnd$index$j$var104, int threadID$index$j$var104, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int index$j$var104 = forStart$index$j$var104; index$j$var104 < forEnd$index$j$var104; index$j$var104 += 1) {
											int j$var104 = index$j$var104;
											int threadID$j$var104 = threadID$index$j$var104;
											
											//  Outer loop for dispatching multiple batches of iterations to execute in parallel
											parallelFor(RNG$2, 0, state.samples, 1,
												(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
													
														// Inner loop for running batches of iterations, each batch has its own random number
														// generator.
														for(int k = forStart$k; k < forEnd$k; k += 1)
															var114[j$var104][k] = DistributionSampling.sampleCategorical(RNG$3, state.m[0], 2);
												}
											);
										}
								}
							);
						}
				}
			);

	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample28)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 2, state.m[var27]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample45)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample82)
			state.st[0][0][0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample122)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 1, state.samples, 1,
				(int forStart$i$var95, int forEnd$i$var95, int threadID$i$var95, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var95 = forStart$i$var95; i$var95 < forEnd$i$var95; i$var95 += 1) {
							int[][] var114 = state.st[i$var95];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.samples, 1,
								(int forStart$index$j$var104, int forEnd$index$j$var104, int threadID$index$j$var104, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int index$j$var104 = forStart$index$j$var104; index$j$var104 < forEnd$index$j$var104; index$j$var104 += 1) {
											int j$var104 = index$j$var104;
											int threadID$j$var104 = threadID$index$j$var104;
											
											//  Outer loop for dispatching multiple batches of iterations to execute in parallel
											parallelFor(RNG$2, 0, state.samples, 1,
												(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
													
														// Inner loop for running batches of iterations, each batch has its own random number
														// generator.
														for(int k = forStart$k; k < forEnd$k; k += 1)
															var114[j$var104][k] = DistributionSampling.sampleCategorical(RNG$3, state.m[0], 2);
												}
											);
										}
								}
							);
						}
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$index$l, int forEnd$index$l, int threadID$index$l, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$l = forStart$index$l; index$l < forEnd$index$l; index$l += 1) {
						int l = index$l;
						int threadID$l = threadID$index$l;
						boolean[][] var177 = state.flips[l];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.samples, 1,
							(int forStart$index$p, int forEnd$index$p, int threadID$index$p, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int index$p = forStart$index$p; index$p < forEnd$index$p; index$p += 1) {
										int p = index$p;
										int threadID$p = threadID$index$p;
										
										//  Outer loop for dispatching multiple batches of iterations to execute in parallel
										parallelFor(RNG$2, 0, state.samples, 1,
											(int forStart$n, int forEnd$n, int threadID$n, org.sandwood.random.internal.Rng RNG$3) -> { 
												
													// Inner loop for running batches of iterations, each batch has its own random number
													// generator.
													for(int n = forStart$n; n < forEnd$n; n += 1)
														var177[n][p] = DistributionSampling.sampleBernoulli(RNG$3, state.bias[state.st[p][l][n]]);
											}
										);
									}
							}
						);
					}
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
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 2, state.m[var27]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample45)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample82)
			state.st[0][0][0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample122)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 1, state.samples, 1,
				(int forStart$i$var95, int forEnd$i$var95, int threadID$i$var95, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var95 = forStart$i$var95; i$var95 < forEnd$i$var95; i$var95 += 1) {
							int[][] var114 = state.st[i$var95];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.samples, 1,
								(int forStart$index$j$var104, int forEnd$index$j$var104, int threadID$index$j$var104, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int index$j$var104 = forStart$index$j$var104; index$j$var104 < forEnd$index$j$var104; index$j$var104 += 1) {
											int j$var104 = index$j$var104;
											int threadID$j$var104 = threadID$index$j$var104;
											
											//  Outer loop for dispatching multiple batches of iterations to execute in parallel
											parallelFor(RNG$2, 0, state.samples, 1,
												(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
													
														// Inner loop for running batches of iterations, each batch has its own random number
														// generator.
														for(int k = forStart$k; k < forEnd$k; k += 1)
															var114[j$var104][k] = DistributionSampling.sampleCategorical(RNG$3, state.m[0], 2);
												}
											);
										}
								}
							);
						}
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample28)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 2, state.m[var27]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample45)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample82)
			state.st[0][0][0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample122)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 1, state.samples, 1,
				(int forStart$i$var95, int forEnd$i$var95, int threadID$i$var95, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var95 = forStart$i$var95; i$var95 < forEnd$i$var95; i$var95 += 1) {
							int[][] var114 = state.st[i$var95];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.samples, 1,
								(int forStart$index$j$var104, int forEnd$index$j$var104, int threadID$index$j$var104, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int index$j$var104 = forStart$index$j$var104; index$j$var104 < forEnd$index$j$var104; index$j$var104 += 1) {
											int j$var104 = index$j$var104;
											int threadID$j$var104 = threadID$index$j$var104;
											
											//  Outer loop for dispatching multiple batches of iterations to execute in parallel
											parallelFor(RNG$2, 0, state.samples, 1,
												(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
													
														// Inner loop for running batches of iterations, each batch has its own random number
														// generator.
														for(int k = forStart$k; k < forEnd$k; k += 1)
															var114[j$var104][k] = DistributionSampling.sampleCategorical(RNG$3, state.m[0], 2);
												}
											);
										}
								}
							);
						}
				}
			);

	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample28)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, 2, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								inferSample28(var27, threadID$var27, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample45)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, 2, 1,
					(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
								inferSample45(var43, threadID$var43, RNG$1);
					}
				);

			if(!state.fixedFlag$sample82)
				inferSample82();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample122)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 1, state.samples, 1,
					(int forStart$index$i$var95, int forEnd$index$i$var95, int threadID$index$i$var95, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int index$i$var95 = forStart$index$i$var95; index$i$var95 < forEnd$index$i$var95; index$i$var95 += 1) {
								int i$var95 = index$i$var95;
								int threadID$i$var95 = threadID$index$i$var95;
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, state.samples, 1,
									(int forStart$index$j$var104, int forEnd$index$j$var104, int threadID$index$j$var104, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int index$j$var104 = forStart$index$j$var104; index$j$var104 < forEnd$index$j$var104; index$j$var104 += 1) {
												int j$var104 = index$j$var104;
												int threadID$j$var104 = threadID$index$j$var104;
												
												//  Outer loop for dispatching multiple batches of iterations to execute in parallel
												parallelFor(RNG$2, 0, state.samples, 1,
													(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
														
															// Inner loop for running batches of iterations, each batch has its own random number
															// generator.
															for(int k = forStart$k; k < forEnd$k; k += 1)
																inferSample122(i$var95, j$var104, k, threadID$k, RNG$3);
													}
												);
											}
									}
								);
							}
					}
				);

		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample122)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 1, state.samples, 1,
					(int forStart$index$i$var95, int forEnd$index$i$var95, int threadID$index$i$var95, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int index$i$var95 = forStart$index$i$var95; index$i$var95 < forEnd$index$i$var95; index$i$var95 += 1) {
								int i$var95 = index$i$var95;
								int threadID$i$var95 = threadID$index$i$var95;
								
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, state.samples, 1,
									(int forStart$index$j$var104, int forEnd$index$j$var104, int threadID$index$j$var104, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int index$j$var104 = forStart$index$j$var104; index$j$var104 < forEnd$index$j$var104; index$j$var104 += 1) {
												int j$var104 = index$j$var104;
												int threadID$j$var104 = threadID$index$j$var104;
												
												//  Outer loop for dispatching multiple batches of iterations to execute in parallel
												parallelFor(RNG$2, 0, state.samples, 1,
													(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
														
															// Inner loop for running batches of iterations, each batch has its own random number
															// generator.
															for(int k = forStart$k; k < forEnd$k; k += 1)
																inferSample122(i$var95, j$var104, k, threadID$k, RNG$3);
													}
												);
											}
									}
								);
							}
					}
				);

			if(!state.fixedFlag$sample82)
				inferSample82();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample45)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, 2, 1,
					(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
								inferSample45(var43, threadID$var43, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample28)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, 2, 1,
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
		parallelFor(state.RNG$, 0, 2, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						if(!state.constrainedFlag$sample28[var27])
							drawValueSample28(var27, threadID$var27, RNG$1);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, 2, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!state.constrainedFlag$sample45[var43])
							drawValueSample45(var43, threadID$var43, RNG$1);
					}
			}
		);
		if(!state.constrainedFlag$sample82)
			drawValueSample82();
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 1, state.samples, 1,
			(int forStart$index$i$var95, int forEnd$index$i$var95, int threadID$index$i$var95, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var95 = forStart$index$i$var95; index$i$var95 < forEnd$index$i$var95; index$i$var95 += 1) {
						int i$var95 = index$i$var95;
						int threadID$i$var95 = threadID$index$i$var95;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.samples, 1,
							(int forStart$index$j$var104, int forEnd$index$j$var104, int threadID$index$j$var104, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int index$j$var104 = forStart$index$j$var104; index$j$var104 < forEnd$index$j$var104; index$j$var104 += 1) {
										int j$var104 = index$j$var104;
										int threadID$j$var104 = threadID$index$j$var104;
										
										//  Outer loop for dispatching multiple batches of iterations to execute in parallel
										parallelFor(RNG$2, 0, state.samples, 1,
											(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
												
													// Inner loop for running batches of iterations, each batch has its own random number
													// generator.
													for(int k = forStart$k; k < forEnd$k; k += 1) {
														if(!state.constrainedFlag$sample122[(i$var95 - 1)][j$var104][k])
															drawValueSample122(i$var95, j$var104, k, threadID$k, RNG$3);
													}
											}
										);
									}
							}
						);
					}
			}
		);
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
		if(!state.fixedProbFlag$sample82)
			state.logProbability$var79 = Double.NaN;
		if(!state.fixedProbFlag$sample122)
			state.logProbability$var119 = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample189)
			state.logProbability$var184 = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		// Substituted "i$var13" with its value "0".
		state.v[0] = 0.1;
		
		// Substituted "i$var13" with its value "1".
		state.v[1] = 0.1;
		state.samples = state.length$flipsMeasured.length;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < state.constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
			state.constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < state.constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
			state.constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample122$1 = 0; index$constrainedFlag$sample122$1 < state.constrainedFlag$sample122.length; index$constrainedFlag$sample122$1 += 1) {
			boolean[][] cv$constrainedFlag$sample122$1 = state.constrainedFlag$sample122[index$constrainedFlag$sample122$1];
			for(int index$constrainedFlag$sample122$2 = 0; index$constrainedFlag$sample122$2 < cv$constrainedFlag$sample122$1.length; index$constrainedFlag$sample122$2 += 1) {
				boolean[] cv$constrainedFlag$sample122$2 = cv$constrainedFlag$sample122$1[index$constrainedFlag$sample122$2];
				for(int index$constrainedFlag$sample122$3 = 0; index$constrainedFlag$sample122$3 < cv$constrainedFlag$sample122$2.length; index$constrainedFlag$sample122$3 += 1)
					cv$constrainedFlag$sample122$2[index$constrainedFlag$sample122$3] = true;
			}
		}
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
		if(state.fixedFlag$sample82)
			logProbabilityValue$sample82();
		if(state.fixedFlag$sample122)
			logProbabilityValue$sample122();
		logProbabilityValue$sample189();
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
		logProbabilityValue$sample82();
		logProbabilityValue$sample122();
		logProbabilityValue$sample189();
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
		logProbabilityValue$sample82();
		logProbabilityValue$sample122();
		logProbabilityValue$sample189();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = state.flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[][] cv$source2 = state.flipsMeasured[cv$index1];
			boolean[][] cv$target2 = state.flips[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1) {
				boolean[] cv$source3 = cv$source2[cv$index2];
				boolean[] cv$target3 = cv$target2[cv$index2];
				int cv$length3 = cv$target3.length;
				for(int cv$index3 = 0; cv$index3 < cv$length3; cv$index3 += 1)
					cv$target3[cv$index3] = cv$source3[cv$index3];
			}
		}
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
		     + "model HMMTestPart4b(boolean[][][] flipsMeasured) {\n"
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
		     + "        \n"
		     + "        int[][][] st = new int[samples][samples][samples];\n"
		     + "\n"
		     + "        st[0][0][0] = categorical(m[0]).sample();\n"
		     + "\n"
		     + "        for(int i:[1..samples))\n"
		     + "            for(int j:[0..samples))\n"
		     + "                for(int k:[0..samples))\n"
		     + "                    st[i][j][k] = categorical(m[0]).sample();\n"
		     + "            \n"
		     + "        boolean[][][] flips = new boolean[samples][][];\n"
		     + "        for(int i:[0..samples)) {\n"
		     + "            flips[i] = new boolean[samples][];\n"
		     + "            for(int j:[0..samples))\n"
		     + "                flips[i][j] = new boolean[samples];\n"
		     + "        }\n"
		     + "            \n"
		     + "        for(int l:[0..samples))\n"
		     + "            for(int p:[0..samples))\n"
		     + "                for(int n:[0..samples))\n"
		     + "                    flips[l][n][p] = bernoulli(bias[st[p][l][n]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}