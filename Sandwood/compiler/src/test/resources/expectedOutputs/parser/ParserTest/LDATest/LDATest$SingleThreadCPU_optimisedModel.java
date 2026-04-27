package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.LDATest$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.LDATest.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class LDATest$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$var42$countGlobal;
		double[] cv$var57$countGlobal;
		double[] cv$var88$stateProbabilityGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for cv$var42$countGlobal
			// 
			// Allocation of cv$var42$countGlobal for single threaded execution
			cv$var42$countGlobal = new double[state.vocabSize];
			
			// Constructor for cv$var57$countGlobal
			// 
			// Allocation of cv$var57$countGlobal for single threaded execution
			cv$var57$countGlobal = new double[state.noTopics];
			
			// Allocation of cv$var88$stateProbabilityGlobal for single threaded execution
			// 
			// Variable to record the maximum value of Task Get 88. Initially set to the value
			// of putTask 59.
			cv$var88$stateProbabilityGlobal = new double[state.noTopics];
		}
	}


	public LDATest$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample42
	private final void drawValueSample42(int var41) {
		DistributionSampling.sampleDirichlet(state.RNG$, state.beta, state.vocabSize, state.phi[var41]);
	}

	// Pick a value from the distribution for the unconditioned variable from sample58
	private final void drawValueSample58(int var56) {
		DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.noTopics, state.theta[var56]);
	}

	// Pick a value from the distribution for the unconditioned variable from sample90
	private final void drawValueSample90(int i$var71, int j) {
		state.z[i$var71][j] = DistributionSampling.sampleCategorical(state.RNG$, state.theta[i$var71], state.noTopics);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 42 drawn from Dirichlet 30. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample42(int var41) {
		state.constrainedFlag$sample42[var41] = false;
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < state.vocabSize; cv$loopIndex += 1)
			// A local reference to the scratch space.
			scratch.cv$var42$countGlobal[cv$loopIndex] = 0.0;
		
		// Processing random variable 90.
		// 
		// Looking for a path between Sample 42 and consumer Categorical 90.
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1) {
				if((var41 == state.z[i$var71][j])) {
					// Processing sample task 93 of consumer random variable null.
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample42[var41] = true;
					
					// Increment the sample counter with the value sampled by sample task 93 of random
					// variable var90
					// 
															// A local reference to the scratch space.
					scratch.cv$var42$countGlobal[state.w[i$var71][j]] = (scratch.cv$var42$countGlobal[state.w[i$var71][j]] + 1.0);
				}
			}
		}
		if(state.constrainedFlag$sample42[var41])
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
									// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.beta, scratch.cv$var42$countGlobal, state.phi[var41], state.vocabSize);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 58 drawn from Dirichlet 44. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample58(int var56) {
		state.constrainedFlag$sample58[var56] = false;
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < state.noTopics; cv$loopIndex += 1)
			// A local reference to the scratch space.
			scratch.cv$var57$countGlobal[cv$loopIndex] = 0.0;
		
		// Substituted "i$var71" with its value "var56".
		for(int j = 0; j < state.length$documents[var56]; j += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.constrainedFlag$sample90[var56][j]) {
				// Processing sample task 90 of consumer random variable null.
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample58[var56] = true;
				
				// Increment the sample counter with the value sampled by sample task 90 of random
				// variable var87
				// 
												// A local reference to the scratch space.
				scratch.cv$var57$countGlobal[state.z[var56][j]] = (scratch.cv$var57$countGlobal[state.z[var56][j]] + 1.0);
			}
		}
		if(state.constrainedFlag$sample58[var56])
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
									// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.alpha, scratch.cv$var57$countGlobal, state.theta[var56], state.noTopics);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 90 drawn from Categorical 87. Inference was performed using variable
	// marginalization.
	private final void inferSample90(int i$var71, int j) {
		state.constrainedFlag$sample90[i$var71][j] = false;
		
		// Variable declaration of cv$numStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
				// cv$numStates's comment
		// Calculate the number of states to evaluate.
		int cv$numStates = Math.max(0, state.noTopics);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			state.z[i$var71][j] = cv$valuePos;
			
			// Constructing a random variable input for use later.
			double[] var86 = state.theta[i$var71];
			
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample90[i$var71][j] = true;
			
			// Constructing a random variable input for use later.
			// 
			// Value of the variable at this index
			double[] var89 = state.phi[cv$valuePos];
			
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
			// Recorded the probability of reaching sample task 93 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
									// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			scratch.cv$var88$stateProbabilityGlobal[cv$valuePos] = (((((((0.0 <= state.w[i$var71][j]) && (state.w[i$var71][j] < state.vocabSize)) && (0 < state.vocabSize)) && (0.0 <= var89[state.w[i$var71][j]])) && (var89[state.w[i$var71][j]] <= 1.0))?Math.log(var89[state.w[i$var71][j]]):Double.NEGATIVE_INFINITY) + (((((cv$valuePos < state.noTopics) && (0 < state.noTopics)) && (0.0 <= var86[cv$valuePos])) && (var86[cv$valuePos] <= 1.0))?Math.log(var86[cv$valuePos]):Double.NEGATIVE_INFINITY));
		}
		if(state.constrainedFlag$sample90[i$var71][j]) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var88$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var88$stateProbabilityGlobal[cv$lseIndex];
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
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					// Get a local reference to the scratch space.
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var88$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
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
					scratch.cv$var88$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
															// Get a local reference to the scratch space.
					scratch.cv$var88$stateProbabilityGlobal[cv$indexName] = Math.exp((scratch.cv$var88$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var88$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var88$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
			// Get a local reference to the scratch space.
			state.z[i$var71][j] = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var88$stateProbabilityGlobal, cv$numStates);
		}
	}

	// Calculate the probability of the samples represented by sample42 using sampled
	// values.
	private final void logProbabilityValue$sample42() {
		// Determine if we need to calculate the values for sample task 42 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample42) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var41 = 0; var41 < state.noTopics; var41 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(state.phi[var41], state.beta, state.vocabSize));
			
			// Store the random variable instance probability
			state.logProbability$var42 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$phi = (state.logProbability$phi + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample42)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample42 = state.fixedFlag$sample42;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$phi = (state.logProbability$phi + state.logProbability$var42);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var42);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample42)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var42);
		}
	}

	// Calculate the probability of the samples represented by sample58 using sampled
	// values.
	private final void logProbabilityValue$sample58() {
		// Determine if we need to calculate the values for sample task 58 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample58) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var56 = 0; var56 < state.length$documents.length; var56 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(state.theta[var56], state.alpha, state.noTopics));
			
			// Store the random variable instance probability
			state.logProbability$var57 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$theta = (state.logProbability$theta + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample58)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample58 = state.fixedFlag$sample58;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$theta = (state.logProbability$theta + state.logProbability$var57);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var57);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample58)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var57);
		}
	}

	// Calculate the probability of the samples represented by sample90 using sampled
	// values.
	private final void logProbabilityValue$sample90() {
		// Generating probabilities for sample task
		// Accumulator for probabilities of instances of the random variable
		double cv$accumulator = 0.0;
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = state.z[i$var71][j];
				double[] var86 = state.theta[i$var71];
				
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
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noTopics)) && (0 < state.noTopics)) && (0.0 <= var86[cv$sampleValue])) && (var86[cv$sampleValue] <= 1.0))?Math.log(var86[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample90[i$var71][j] = cv$distributionAccumulator;
			}
		}
		
		// Add probability to model
		state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
	}

	// Calculate the probability of the samples represented by sample93 using sampled
	// values.
	private final void logProbabilityValue$sample93() {
		// Generating probabilities for sample task
		// Accumulator for probabilities of instances of the random variable
		double cv$accumulator = 0.0;
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = state.w[i$var71][j];
				double[] var89 = state.phi[state.z[i$var71][j]];
				
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
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.vocabSize)) && (0 < state.vocabSize)) && (0.0 <= var89[cv$sampleValue])) && (var89[cv$sampleValue] <= 1.0))?Math.log(var89[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample93[i$var71][j] = cv$distributionAccumulator;
			}
		}
		
		// Update the variable probability
		state.logProbability$w = (state.logProbability$w + cv$accumulator);
		
		// Add probability to model
		state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
		state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample42) {
			for(int var41 = 0; var41 < state.noTopics; var41 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.beta, state.vocabSize, state.phi[var41]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample58) {
			for(int var56 = 0; var56 < state.length$documents.length; var56 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.noTopics, state.theta[var56]);
		}
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			int[] t = state.w[i$var71];
			for(int j = 0; j < state.length$documents[i$var71]; j += 1) {
				state.z[i$var71][j] = DistributionSampling.sampleCategorical(state.RNG$, state.theta[i$var71], state.noTopics);
				t[j] = DistributionSampling.sampleCategorical(state.RNG$, state.phi[state.z[i$var71][j]], state.vocabSize);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample42) {
			for(int var41 = 0; var41 < state.noTopics; var41 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.beta, state.vocabSize, state.phi[var41]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample58) {
			for(int var56 = 0; var56 < state.length$documents.length; var56 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.noTopics, state.theta[var56]);
		}
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1)
				state.z[i$var71][j] = DistributionSampling.sampleCategorical(state.RNG$, state.theta[i$var71], state.noTopics);
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample42) {
			for(int var41 = 0; var41 < state.noTopics; var41 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.beta, state.vocabSize, state.phi[var41]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample58) {
			for(int var56 = 0; var56 < state.length$documents.length; var56 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.noTopics, state.theta[var56]);
		}
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			int[] t = state.w[i$var71];
			for(int j = 0; j < state.length$documents[i$var71]; j += 1) {
				state.z[i$var71][j] = DistributionSampling.sampleCategorical(state.RNG$, state.theta[i$var71], state.noTopics);
				t[j] = DistributionSampling.sampleCategorical(state.RNG$, state.phi[state.z[i$var71][j]], state.vocabSize);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample42) {
			for(int var41 = 0; var41 < state.noTopics; var41 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.beta, state.vocabSize, state.phi[var41]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample58) {
			for(int var56 = 0; var56 < state.length$documents.length; var56 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.noTopics, state.theta[var56]);
		}
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1)
				state.z[i$var71][j] = DistributionSampling.sampleCategorical(state.RNG$, state.theta[i$var71], state.noTopics);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample42) {
			for(int var41 = 0; var41 < state.noTopics; var41 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.beta, state.vocabSize, state.phi[var41]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample58) {
			for(int var56 = 0; var56 < state.length$documents.length; var56 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.noTopics, state.theta[var56]);
		}
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1)
				state.z[i$var71][j] = DistributionSampling.sampleCategorical(state.RNG$, state.theta[i$var71], state.noTopics);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample42) {
				for(int var41 = 0; var41 < state.noTopics; var41 += 1)
					inferSample42(var41);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample58) {
				for(int var56 = 0; var56 < state.length$documents.length; var56 += 1)
					inferSample58(var56);
			}
			for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
				for(int j = 0; j < state.length$documents[i$var71]; j += 1)
					inferSample90(i$var71, j);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i$var71 = (state.length$documents.length - 1); i$var71 >= 0; i$var71 -= 1) {
				for(int j = (state.length$documents[i$var71] - 1); j >= 0; j -= 1)
					inferSample90(i$var71, j);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample58) {
				for(int var56 = (state.length$documents.length - 1); var56 >= 0; var56 -= 1)
					inferSample58(var56);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample42) {
				for(int var41 = (state.noTopics - 1); var41 >= 0; var41 -= 1)
					inferSample42(var41);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int var41 = 0; var41 < state.noTopics; var41 += 1) {
			if(!state.constrainedFlag$sample42[var41])
				drawValueSample42(var41);
		}
		for(int var56 = 0; var56 < state.length$documents.length; var56 += 1) {
			if(!state.constrainedFlag$sample58[var56])
				drawValueSample58(var56);
		}
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1) {
				if(!state.constrainedFlag$sample90[i$var71][j])
					drawValueSample90(i$var71, j);
			}
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
		state.logProbability$phi = 0.0;
		if(!state.fixedProbFlag$sample42)
			state.logProbability$var42 = Double.NaN;
		state.logProbability$theta = 0.0;
		if(!state.fixedProbFlag$sample58)
			state.logProbability$var57 = Double.NaN;
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1)
				state.logProbability$sample90[i$var71][j] = Double.NaN;
		}
		state.logProbability$w = 0.0;
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1)
				state.logProbability$sample93[i$var71][j] = Double.NaN;
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		for(int i$var14 = 0; i$var14 < state.noTopics; i$var14 += 1)
			state.alpha[i$var14] = 0.1;
		for(int i$var27 = 0; i$var27 < state.vocabSize; i$var27 += 1)
			state.beta[i$var27] = 0.1;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample90$1 = 0; index$constrainedFlag$sample90$1 < state.constrainedFlag$sample90.length; index$constrainedFlag$sample90$1 += 1) {
			boolean[] cv$constrainedFlag$sample90$1 = state.constrainedFlag$sample90[index$constrainedFlag$sample90$1];
			for(int index$constrainedFlag$sample90$2 = 0; index$constrainedFlag$sample90$2 < cv$constrainedFlag$sample90$1.length; index$constrainedFlag$sample90$2 += 1)
				cv$constrainedFlag$sample90$1[index$constrainedFlag$sample90$2] = true;
		}
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample42$1 = 0; index$constrainedFlag$sample42$1 < state.constrainedFlag$sample42.length; index$constrainedFlag$sample42$1 += 1)
			state.constrainedFlag$sample42[index$constrainedFlag$sample42$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample58$1 = 0; index$constrainedFlag$sample58$1 < state.constrainedFlag$sample58.length; index$constrainedFlag$sample58$1 += 1)
			state.constrainedFlag$sample58[index$constrainedFlag$sample58$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample42)
			logProbabilityValue$sample42();
		if(state.fixedFlag$sample58)
			logProbabilityValue$sample58();
		logProbabilityValue$sample93();
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
		logProbabilityValue$sample42();
		logProbabilityValue$sample58();
		logProbabilityValue$sample90();
		logProbabilityValue$sample93();
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
		logProbabilityValue$sample42();
		logProbabilityValue$sample58();
		logProbabilityValue$sample90();
		logProbabilityValue$sample93();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = state.w.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = state.documents[cv$index1];
			int[] cv$target2 = state.w[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
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
		     + "model LDATest(int noTopics, int vocabSize, int[][] documents) {\n"
		     + "\n"
		     + "    double[] alpha = new double[noTopics];\n"
		     + "    for(int i:[0..noTopics))\n"
		     + "        alpha[i] = 0.1;\n"
		     + "\n"
		     + "    double[] beta = new double[vocabSize];\n"
		     + "    for(int i:[0..vocabSize))\n"
		     + "        beta[i] = 0.1;\n"
		     + "\n"
		     + "    double[][] phi = dirichlet(beta).sample(noTopics);\n"
		     + "    double[][] theta = dirichlet(alpha).sample(documents.length);\n"
		     + "    int[][] w = new int[documents.length][];\n"
		     + "\n"
		     + "    for(int i:[0..documents.length)) {\n"
		     + "        int[] t = new int[documents[i].length];\n"
		     + "        for(int j:[0..documents[i].length)) {\n"
		     + "            int z = categorical(theta[i]).sample();\n"
		     + "            t[j] = categorical(phi[z]).sample();\n"
		     + "        }\n"
		     + "        w[i] = t;\n"
		     + "    }\n"
		     + "\n"
		     + "    w.observe(documents);\n"
		     + "}\n"
		     + "";
	}
}