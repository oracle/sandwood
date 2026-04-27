package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Deterministic$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Deterministic.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Deterministic$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[][] cv$var29$countGlobal;
		double[] cv$var54$stateProbabilityGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for cv$var29$countGlobal
			// 
			// Allocation of cv$var29$countGlobal for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var29$countGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var29$countGlobal[cv$index] = new double[5];
			
			// Allocation of cv$var54$stateProbabilityGlobal for single threaded execution
			// 
			// Variable to record the maximum value of Task Get 53. Initially set to the value
			// of putTask 30.
			cv$var54$stateProbabilityGlobal = new double[5];
		}
	}


	public Deterministic$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample29
	private final void drawValueSample29(int var28, int threadID$cv$var28, Rng RNG$) {
		DistributionSampling.sampleDirichlet(RNG$, state.v, 5, state.m[var28]);
	}

	// Pick a value from the distribution for the unconditioned variable from sample55
	private final void drawValueSample55(int i$var46) {
		state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
		
		// Guards to ensure that b is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 55 and consumer int[] 50.
		int index$i$1_1 = (i$var46 + 1);
		if((index$i$1_1 < state.n))
			state.b[index$i$1_1] = state.a[(index$i$1_1 - 1)];
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 29 drawn from Dirichlet 17. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample29(int var28, int threadID$cv$var28, Rng RNG$) {
		state.constrainedFlag$sample29[var28] = false;
		
		// A local reference to the scratch space.
		double[] cv$countLocal = scratch.cv$var29$countGlobal[threadID$cv$var28];
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		
		// Processing random variable 53.
		// 
		// Looking for a path between Sample 29 and consumer Categorical 53.
		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			if(((var28 == state.b[i$var46]) && (state.fixedFlag$sample55 || state.constrainedFlag$sample55[(i$var46 - 1)]))) {
				// Processing sample task 55 of consumer random variable null.
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample29[var28] = true;
				
				// Increment the sample counter with the value sampled by sample task 55 of random
				// variable var53
				cv$countLocal[state.a[i$var46]] = (cv$countLocal[state.a[i$var46]] + 1.0);
			}
		}
		if(state.constrainedFlag$sample29[var28])
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
			// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(RNG$, state.v, cv$countLocal, state.m[var28], 5);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 55 drawn from Categorical 53. Inference was performed using variable
	// marginalization.
	private final void inferSample55(int i$var46) {
		state.constrainedFlag$sample55[(i$var46 - 1)] = false;
		
				// cv$numStates's comment
		// variable marginalization
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			// Guards to ensure that a is only updated when there is a valid path.
			// 
			// Value of the variable at this index
			state.a[i$var46] = cv$valuePos;
			
			// Guards to ensure that b is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 55 and consumer int[] 50.
			int index$i$2_1 = (i$var46 + 1);
			if((index$i$2_1 < state.n))
				state.b[index$i$2_1] = state.a[(index$i$2_1 - 1)];
			
			// Constructing a random variable input for use later.
			double[] var52 = state.m[state.b[i$var46]];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			double cv$accumulatedProbabilities = (((0.0 <= var52[cv$valuePos]) && (var52[cv$valuePos] <= 1.0))?Math.log(var52[cv$valuePos]):Double.NEGATIVE_INFINITY);
			int index$i$3_2 = (i$var46 + 1);
			if(((index$i$3_2 < state.n) && (state.fixedFlag$sample55 || state.constrainedFlag$sample55[(index$i$3_2 - 1)]))) {
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 53.
				// 
				// Looking for a path between Sample 55 and consumer Categorical 53.
				// 
				// Value of the variable at this index
				double[] sc$var52$1 = state.m[cv$valuePos];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 55 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "index$i$3_4" with its value "index$i$3_2".
				cv$accumulatedProbabilities = ((((((0.0 <= state.a[index$i$3_2]) && (state.a[index$i$3_2] < 5)) && (0.0 <= sc$var52$1[state.a[index$i$3_2]])) && (sc$var52$1[state.a[index$i$3_2]] <= 1.0))?Math.log(sc$var52$1[state.a[index$i$3_2]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Mark that the sample has observed constrained data.
			state.constrainedFlag$sample55[(i$var46 - 1)] = true;
			
			// Constructing a random variable input for use later.
			// 
									// Looking for a path between Sample 55 and consumer Bernoulli 73.
			// 
			// Value of the variable at this index
			double var72 = (double)(1 / cv$valuePos);
			
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 75 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// Substituted "j" with its value "(i$var46 - 1)".
			cv$accumulatedProbabilities = ((((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[(i$var46 - 1)]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var54$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample55[(i$var46 - 1)]) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var54$stateProbabilityGlobal[0];
			
			// Unrolled loop
			{
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var54$stateProbabilityGlobal[1];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			{
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var54$stateProbabilityGlobal[2];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			{
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var54$stateProbabilityGlobal[3];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			
			// Get a local reference to the scratch space.
			double cv$lseElementValue = scratch.cv$var54$stateProbabilityGlobal[4];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else {
				// Initialize the sum of the array elements
				double cv$lseSum = 0.0;
				
				// Offset values, move to normal space, and sum.
				// 
								// cv$numStates's comment
				// variable marginalization
				for(int cv$lseIndex = 0; cv$lseIndex < 5; cv$lseIndex += 1)
					// Get a local reference to the scratch space.
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var54$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				// 
								// cv$numStates's comment
				// variable marginalization
				for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
															// Get a local reference to the scratch space.
					scratch.cv$var54$stateProbabilityGlobal[cv$indexName] = 0.2;
			} else {
				// Normalize log space values and move to normal space
				// 
								// cv$numStates's comment
				// variable marginalization
				for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
															// Get a local reference to the scratch space.
					scratch.cv$var54$stateProbabilityGlobal[cv$indexName] = Math.exp((scratch.cv$var54$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
									// Get a local reference to the scratch space.
			for(int cv$indexName = 5; cv$indexName < scratch.cv$var54$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var54$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Guards to ensure that a is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var54$stateProbabilityGlobal, 5);
			
			// Guards to ensure that b is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 55 and consumer int[] 50.
			int index$i$10_1 = (i$var46 + 1);
			if((index$i$10_1 < state.n))
				state.b[index$i$10_1] = state.a[(index$i$10_1 - 1)];
		}
	}

	// Calculate the probability of the samples represented by sample29 using sampled
	// values.
	private final void logProbabilityValue$sample29() {
		// Determine if we need to calculate the values for sample task 29 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample29) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var28 = 0; var28 < 5; var28 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(state.m[var28], state.v, 5));
			
			// Store the random variable instance probability
			state.logProbability$var29 = cv$sampleAccumulator;
			
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
			if(state.fixedFlag$sample29)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample29 = state.fixedFlag$sample29;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$m = (state.logProbability$m + state.logProbability$var29);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var29);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample29)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var29);
		}
	}

	// Calculate the probability of the samples represented by sample55 using sampled
	// values.
	private final void logProbabilityValue$sample55() {
		// Determine if we need to calculate the values for sample task 55 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample55) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = state.a[i$var46];
				double[] var52 = state.m[state.b[i$var46]];
				
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
				double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var52[cv$sampleValue])) && (var52[cv$sampleValue] <= 1.0))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample55[(i$var46 - 1)] = cv$distributionAccumulator;
				
												// Guard to ensure that b is only updated once for this probability.
				if((i$var46 < (state.n - 1)))
					// Update the variable probability
					state.logProbability$b = (state.logProbability$b + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			state.logProbability$a = (state.logProbability$a + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample55)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample55 = (state.fixedFlag$sample55 && state.fixedFlag$sample29);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				double cv$sampleValue = state.logProbability$sample55[(i$var46 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				
												// Guard to ensure that b is only updated once for this probability.
				if((i$var46 < (state.n - 1)))
					// Update the variable probability
					state.logProbability$b = (state.logProbability$b + cv$sampleValue);
			}
			
			// Update the variable probability
			state.logProbability$a = (state.logProbability$a + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample55)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample75 using sampled
	// values.
	private final void logProbabilityValue$sample75() {
		// Determine if we need to calculate the values for sample task 75 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample75) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.n; j += 1) {
				double var72 = (double)(1 / state.a[(j + 1)]);
				
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
				double cv$distributionAccumulator = (((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample75[j] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample75 = state.fixedFlag$sample55;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.n; j += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample75[j]);
			
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
		if(!state.fixedFlag$sample29)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var28]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				state.b[i$var46] = state.a[(i$var46 - 1)];
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
			}
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j = forStart$j; j < forEnd$j; j += 1)
						state.flips[j] = DistributionSampling.sampleBernoulli(RNG$1, (1 / state.a[(j + 1)]));
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample29)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var28]);
				}
			);

		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			state.b[i$var46] = state.a[(i$var46 - 1)];
			if(!state.fixedFlag$sample55)
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample29)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var28]);
				}
			);

		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			state.b[i$var46] = state.a[(i$var46 - 1)];
			if(!state.fixedFlag$sample55)
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j = forStart$j; j < forEnd$j; j += 1)
						state.flips[j] = DistributionSampling.sampleBernoulli(RNG$1, (1 / state.a[(j + 1)]));
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample29)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var28]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				state.b[i$var46] = state.a[(i$var46 - 1)];
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample29)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var28]);
				}
			);

		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			state.b[i$var46] = state.a[(i$var46 - 1)];
			if(!state.fixedFlag$sample55)
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample29)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, 5, 1,
					(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
								inferSample29(var28, threadID$var28, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample55) {
				for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1)
					inferSample55(i$var46);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample55) {
				for(int i$var46 = (state.n - 1); i$var46 >= 1; i$var46 -= 1)
					inferSample55(i$var46);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample29)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(state.RNG$, 0, 5, 1,
					(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
								inferSample29(var28, threadID$var28, RNG$1);
					}
				);

		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1) {
						if(!state.constrainedFlag$sample29[var28])
							drawValueSample29(var28, threadID$var28, RNG$1);
					}
			}
		);
		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			if(!state.constrainedFlag$sample55[(i$var46 - 1)])
				drawValueSample55(i$var46);
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
		if(!state.fixedProbFlag$sample29)
			state.logProbability$var29 = Double.NaN;
		state.logProbability$a = 0.0;
		state.logProbability$b = 0.0;
		if(!state.fixedProbFlag$sample55) {
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1)
				state.logProbability$sample55[(i$var46 - 1)] = Double.NaN;
		}
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample75) {
			for(int j = 0; j < state.n; j += 1)
				state.logProbability$sample75[j] = Double.NaN;
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		for(int i$var14 = 0; i$var14 < 5; i$var14 += 1)
			state.v[i$var14] = 0.1;
		state.a[0] = 0;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample29$1 = 0; index$constrainedFlag$sample29$1 < state.constrainedFlag$sample29.length; index$constrainedFlag$sample29$1 += 1)
			state.constrainedFlag$sample29[index$constrainedFlag$sample29$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample55$1 = 0; index$constrainedFlag$sample55$1 < state.constrainedFlag$sample55.length; index$constrainedFlag$sample55$1 += 1)
			state.constrainedFlag$sample55[index$constrainedFlag$sample55$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample29)
			logProbabilityValue$sample29();
		if(state.fixedFlag$sample55)
			logProbabilityValue$sample55();
		logProbabilityValue$sample75();
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
		logProbabilityValue$sample29();
		logProbabilityValue$sample55();
		logProbabilityValue$sample75();
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
		logProbabilityValue$sample29();
		logProbabilityValue$sample55();
		logProbabilityValue$sample75();
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
	public final void setIntermediates() {
		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1)
			state.b[i$var46] = state.a[(i$var46 - 1)];
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
		     + "/**\n"
		     + " * A model for the fairness work.\n"
		     + " */\n"
		     + "public model Deterministic(int n, boolean[] flipsMeasured) {\n"
		     + "    int states = 5;\n"
		     + "\n"
		     + "    double[] v = new double[states];\n"
		     + "    for(int i:[0..states))\n"
		     + "        v[i] = 0.1;\n"
		     + "    \n"
		     + "    double[][] m = dirichlet(v).sample(states);\n"
		     + "\n"
		     + "    int[] a = new int[n];\n"
		     + "    int[] b = new int[n];\n"
		     + "    a[0] = 0;\n"
		     + "    for(int i=1; i<n; i++) {\n"
		     + "        b[i] = a[i-1];\n"
		     + "        a[i] = categorical(m[b[i]]).sample();\n"
		     + "    }\n"
		     + "    \n"
		     + "    boolean[] flips = new boolean[n];\n"
		     + "            \n"
		     + "    for(int j:[0..n))\n"
		     + "        flips[j] = bernoulli(1/a[j+1]).sample();\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}";
	}
}