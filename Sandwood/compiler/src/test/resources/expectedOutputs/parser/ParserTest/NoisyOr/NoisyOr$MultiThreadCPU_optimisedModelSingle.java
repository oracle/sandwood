package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.NoisyOr$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.NoisyOr.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class NoisyOr$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$var12$stateProbabilityGlobal;
		double[] cv$var15$stateProbabilityGlobal;
		double[] cv$var18$stateProbabilityGlobal;
		double[][] cv$var225$stateProbabilityGlobal;
		double[][] cv$var238$stateProbabilityGlobal;
		double[][] cv$var251$stateProbabilityGlobal;
		double[][] cv$var264$stateProbabilityGlobal;
		double[][] cv$var277$stateProbabilityGlobal;
		double[][] cv$var290$stateProbabilityGlobal;
		double[] cv$var3$stateProbabilityGlobal;
		double[] cv$var6$stateProbabilityGlobal;
		double[] cv$var9$stateProbabilityGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for cv$var3$stateProbabilityGlobal
			// 
			// Allocation of cv$var3$stateProbabilityGlobal for single threaded execution
			cv$var3$stateProbabilityGlobal = new double[2];
			
			// Constructor for cv$var6$stateProbabilityGlobal
			// 
			// Allocation of cv$var6$stateProbabilityGlobal for single threaded execution
			cv$var6$stateProbabilityGlobal = new double[2];
			
			// Constructor for cv$var9$stateProbabilityGlobal
			// 
			// Allocation of cv$var9$stateProbabilityGlobal for single threaded execution
			cv$var9$stateProbabilityGlobal = new double[2];
			
			// Constructor for cv$var12$stateProbabilityGlobal
			// 
			// Allocation of cv$var12$stateProbabilityGlobal for single threaded execution
			cv$var12$stateProbabilityGlobal = new double[2];
			
			// Constructor for cv$var15$stateProbabilityGlobal
			// 
			// Allocation of cv$var15$stateProbabilityGlobal for single threaded execution
			cv$var15$stateProbabilityGlobal = new double[2];
			
			// Constructor for cv$var18$stateProbabilityGlobal
			// 
			// Allocation of cv$var18$stateProbabilityGlobal for single threaded execution
			cv$var18$stateProbabilityGlobal = new double[2];
			
			// Constructor for cv$var225$stateProbabilityGlobal
			{
				// Allocation of cv$var225$stateProbabilityGlobal for multithreaded execution
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var225$stateProbabilityGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var225$stateProbabilityGlobal[cv$index] = new double[2];
			}
			
			// Constructor for cv$var238$stateProbabilityGlobal
			{
				// Allocation of cv$var238$stateProbabilityGlobal for multithreaded execution
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var238$stateProbabilityGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var238$stateProbabilityGlobal[cv$index] = new double[2];
			}
			
			// Constructor for cv$var251$stateProbabilityGlobal
			{
				// Allocation of cv$var251$stateProbabilityGlobal for multithreaded execution
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var251$stateProbabilityGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var251$stateProbabilityGlobal[cv$index] = new double[2];
			}
			
			// Constructor for cv$var264$stateProbabilityGlobal
			{
				// Allocation of cv$var264$stateProbabilityGlobal for multithreaded execution
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var264$stateProbabilityGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var264$stateProbabilityGlobal[cv$index] = new double[2];
			}
			
			// Constructor for cv$var277$stateProbabilityGlobal
			{
				// Allocation of cv$var277$stateProbabilityGlobal for multithreaded execution
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var277$stateProbabilityGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var277$stateProbabilityGlobal[cv$index] = new double[2];
			}
			
			// Constructor for cv$var290$stateProbabilityGlobal
			// 
			// Allocation of cv$var290$stateProbabilityGlobal for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var290$stateProbabilityGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var290$stateProbabilityGlobal[cv$index] = new double[2];
		}
	}


	public NoisyOr$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample12
	private final void drawValueSample12() {
		state.flag4 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
	}

	// Pick a value from the distribution for the unconditioned variable from sample15
	private final void drawValueSample15() {
		state.flag5 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
	}

	// Pick a value from the distribution for the unconditioned variable from sample18
	private final void drawValueSample18() {
		state.flag6 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
	}

	// Pick a value from the distribution for the unconditioned variable from sample233
	private final void drawValueSample233(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var223;
		if(state.flag1)
			var223 = state.p[0][i$var211];
		else
			var223 = 0.0;
		state.issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$, var223);
		
		// Guards to ensure that noisyOr is only updated when there is a valid path.
		// 
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var300$36 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// y$var298's comment
			// Set the right hand term to a value from the array issues
			reduceVar$var300$36 = (reduceVar$var300$36 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$36;
	}

	// Pick a value from the distribution for the unconditioned variable from sample248
	private final void drawValueSample248(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var236;
		if(state.flag2)
			var236 = state.p[1][i$var211];
		else
			var236 = 0.0;
		state.issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$, var236);
		
		// Guards to ensure that noisyOr is only updated when there is a valid path.
		// 
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var300$37 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// y$var298's comment
			// Set the right hand term to a value from the array issues
			reduceVar$var300$37 = (reduceVar$var300$37 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$37;
	}

	// Pick a value from the distribution for the unconditioned variable from sample263
	private final void drawValueSample263(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var249;
		if(state.flag3)
			var249 = state.p[2][i$var211];
		else
			var249 = 0.0;
		state.issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$, var249);
		
		// Guards to ensure that noisyOr is only updated when there is a valid path.
		// 
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var300$38 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// y$var298's comment
			// Set the right hand term to a value from the array issues
			reduceVar$var300$38 = (reduceVar$var300$38 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$38;
	}

	// Pick a value from the distribution for the unconditioned variable from sample278
	private final void drawValueSample278(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var262;
		if(state.flag4)
			var262 = state.p[3][i$var211];
		else
			var262 = 0.0;
		state.issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$, var262);
		
		// Guards to ensure that noisyOr is only updated when there is a valid path.
		// 
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var300$39 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// y$var298's comment
			// Set the right hand term to a value from the array issues
			reduceVar$var300$39 = (reduceVar$var300$39 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$39;
	}

	// Pick a value from the distribution for the unconditioned variable from sample293
	private final void drawValueSample293(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var275;
		if(state.flag5)
			var275 = state.p[4][i$var211];
		else
			var275 = 0.0;
		state.issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$, var275);
		
		// Guards to ensure that noisyOr is only updated when there is a valid path.
		// 
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var300$40 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// y$var298's comment
			// Set the right hand term to a value from the array issues
			reduceVar$var300$40 = (reduceVar$var300$40 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$40;
	}

	// Pick a value from the distribution for the unconditioned variable from sample3
	private final void drawValueSample3() {
		state.flag1 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
	}

	// Pick a value from the distribution for the unconditioned variable from sample308
	private final void drawValueSample308(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		double var288;
		if(state.flag6)
			var288 = state.p[5][i$var211];
		else
			var288 = 0.0;
		state.issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$, var288);
		
		// Guards to ensure that noisyOr is only updated when there is a valid path.
		// 
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var300$41 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// y$var298's comment
			// Set the right hand term to a value from the array issues
			reduceVar$var300$41 = (reduceVar$var300$41 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$41;
	}

	// Pick a value from the distribution for the unconditioned variable from sample430
	private final void drawValueSample430(int i$var381, int j, int threadID$cv$j, Rng RNG$) {
		double var402;
		if(state.noisyOr[j])
			var402 = state.p13[j][i$var381];
		else
			var402 = 0.0;
		state.issues$var383[i$var381][j] = DistributionSampling.sampleBernoulli(RNG$, var402);
		
		// Guards to ensure that n13State is only updated when there is a valid path.
		// 
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var414$7 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// y$var412's comment
			// Set the right hand term to a value from the array issues
			reduceVar$var414$7 = (reduceVar$var414$7 || state.issues$var383[i$var381][cv$reduction435Index]);
		state.n13State[i$var381] = reduceVar$var414$7;
	}

	// Pick a value from the distribution for the unconditioned variable from sample6
	private final void drawValueSample6() {
		state.flag2 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
	}

	// Pick a value from the distribution for the unconditioned variable from sample9
	private final void drawValueSample9() {
		state.flag3 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 12 drawn from Bernoulli 11. Inference was performed using variable
	// marginalization.
	private final void inferSample12() {
		state.constrainedFlag$sample12 = false;
		{
			// Write out the new value of the sample.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			state.flag4 = false;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = -0.01005033585350145;
			
			// Processing conditional point276.
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((state.fixedFlag$sample278 || state.constrainedFlag$sample278[i$var211])) {
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample12 = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 278 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = (Math.log((state.issues$var213[i$var211][3]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var12$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.flag4 = true;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = -4.605170185988091;
		
		// Processing conditional point276.
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((state.fixedFlag$sample278 || state.constrainedFlag$sample278[i$var211])) {
				double traceTempVariable$var262$2_1 = state.p[3][i$var211];
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample12 = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 278 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var262$2_1) && (traceTempVariable$var262$2_1 <= 1.0))?Math.log((state.issues$var213[i$var211][3]?traceTempVariable$var262$2_1:(1.0 - traceTempVariable$var262$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		scratch.cv$var12$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample12) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var12$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = scratch.cv$var12$stateProbabilityGlobal[1];
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
				cv$logSum = (Math.log((Math.exp((scratch.cv$var12$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var12$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var12$stateProbabilityGlobal[0] = 0.5;
				
												// Get a local reference to the scratch space.
				scratch.cv$var12$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var12$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var12$stateProbabilityGlobal[0] - cv$logSum));
				
												// Get a local reference to the scratch space.
				scratch.cv$var12$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var12$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
									// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var12$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var12$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.flag4 = (DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var12$stateProbabilityGlobal, 2) == 1);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 15 drawn from Bernoulli 14. Inference was performed using variable
	// marginalization.
	private final void inferSample15() {
		state.constrainedFlag$sample15 = false;
		{
			// Write out the new value of the sample.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			state.flag5 = false;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = -0.01005033585350145;
			
			// Processing conditional point291.
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((state.fixedFlag$sample293 || state.constrainedFlag$sample293[i$var211])) {
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample15 = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 293 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = (Math.log((state.issues$var213[i$var211][4]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var15$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.flag5 = true;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = -4.605170185988091;
		
		// Processing conditional point291.
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((state.fixedFlag$sample293 || state.constrainedFlag$sample293[i$var211])) {
				double traceTempVariable$var275$2_1 = state.p[4][i$var211];
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample15 = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 293 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var275$2_1) && (traceTempVariable$var275$2_1 <= 1.0))?Math.log((state.issues$var213[i$var211][4]?traceTempVariable$var275$2_1:(1.0 - traceTempVariable$var275$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		scratch.cv$var15$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample15) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var15$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = scratch.cv$var15$stateProbabilityGlobal[1];
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
				cv$logSum = (Math.log((Math.exp((scratch.cv$var15$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var15$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var15$stateProbabilityGlobal[0] = 0.5;
				
												// Get a local reference to the scratch space.
				scratch.cv$var15$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var15$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var15$stateProbabilityGlobal[0] - cv$logSum));
				
												// Get a local reference to the scratch space.
				scratch.cv$var15$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var15$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
									// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var15$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var15$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.flag5 = (DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var15$stateProbabilityGlobal, 2) == 1);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 18 drawn from Bernoulli 17. Inference was performed using variable
	// marginalization.
	private final void inferSample18() {
		state.constrainedFlag$sample18 = false;
		{
			// Write out the new value of the sample.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			state.flag6 = false;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = -0.01005033585350145;
			
			// Processing conditional point306.
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((state.fixedFlag$sample308 || state.constrainedFlag$sample308[i$var211])) {
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample18 = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 308 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = (Math.log((state.issues$var213[i$var211][5]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var18$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.flag6 = true;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = -4.605170185988091;
		
		// Processing conditional point306.
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((state.fixedFlag$sample308 || state.constrainedFlag$sample308[i$var211])) {
				double traceTempVariable$var288$2_1 = state.p[5][i$var211];
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample18 = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 308 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var288$2_1) && (traceTempVariable$var288$2_1 <= 1.0))?Math.log((state.issues$var213[i$var211][5]?traceTempVariable$var288$2_1:(1.0 - traceTempVariable$var288$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		scratch.cv$var18$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample18) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var18$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = scratch.cv$var18$stateProbabilityGlobal[1];
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
				cv$logSum = (Math.log((Math.exp((scratch.cv$var18$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var18$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var18$stateProbabilityGlobal[0] = 0.5;
				
												// Get a local reference to the scratch space.
				scratch.cv$var18$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var18$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var18$stateProbabilityGlobal[0] - cv$logSum));
				
												// Get a local reference to the scratch space.
				scratch.cv$var18$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var18$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
									// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var18$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var18$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.flag6 = (DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var18$stateProbabilityGlobal, 2) == 1);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 233 drawn from Bernoulli 224. Inference was performed using variable
	// marginalization.
	private final void inferSample233(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		state.constrainedFlag$sample233[i$var211] = false;
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = scratch.cv$var225$stateProbabilityGlobal[threadID$cv$i$var211];
		{
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			state.issues$var213[i$var211][0] = false;
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$24 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$24 = (reduceVar$var300$24 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$24;
			double var223;
			if(state.flag1)
				var223 = state.p[0][i$var211];
			else
				var223 = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= var223) && (var223 <= 1.0))?Math.log((1.0 - var223)):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.fixedFlag$sample430) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(state.noisyOr[i$var211]) {
					{
																		// Substituted "j" with its value "i$var211".
						double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 430 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
																		// Substituted "i$var381" with its value "0".
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
															// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample233[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample233[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$stateProbabilityLocal[0] = cv$accumulatedProbabilities;
		}
		
		// Guards to ensure that issues$var213 is only updated when there is a valid path.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.issues$var213[i$var211][0] = true;
		
		// Guards to ensure that noisyOr is only updated when there is a valid path.
		// 
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var300$24 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// y$var298's comment
			// Set the right hand term to a value from the array issues
			reduceVar$var300$24 = (reduceVar$var300$24 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$24;
		double var223;
		if(state.flag1)
			var223 = state.p[0][i$var211];
		else
			var223 = 0.0;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= var223) && (var223 <= 1.0))?Math.log(var223):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.fixedFlag$sample430) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.noisyOr[i$var211]) {
				{
															// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				
												// Substituted "j" with its value "i$var211".
				double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample233[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "i$var381" with its value "0".
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample233[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$stateProbabilityLocal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample233[i$var211]) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
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
				// Initialise the sum of the array elements
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
			
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.issues$var213[i$var211][0] = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2) == 1);
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$25 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$25 = (reduceVar$var300$25 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$25;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 248 drawn from Bernoulli 237. Inference was performed using variable
	// marginalization.
	private final void inferSample248(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		state.constrainedFlag$sample248[i$var211] = false;
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = scratch.cv$var238$stateProbabilityGlobal[threadID$cv$i$var211];
		{
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			state.issues$var213[i$var211][1] = false;
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$26 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$26 = (reduceVar$var300$26 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$26;
			double var236;
			if(state.flag2)
				var236 = state.p[1][i$var211];
			else
				var236 = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= var236) && (var236 <= 1.0))?Math.log((1.0 - var236)):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.fixedFlag$sample430) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(state.noisyOr[i$var211]) {
					{
																		// Substituted "j" with its value "i$var211".
						double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 430 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
																		// Substituted "i$var381" with its value "0".
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
															// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample248[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample248[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$stateProbabilityLocal[0] = cv$accumulatedProbabilities;
		}
		
		// Guards to ensure that issues$var213 is only updated when there is a valid path.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.issues$var213[i$var211][1] = true;
		
		// Guards to ensure that noisyOr is only updated when there is a valid path.
		// 
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var300$26 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// y$var298's comment
			// Set the right hand term to a value from the array issues
			reduceVar$var300$26 = (reduceVar$var300$26 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$26;
		double var236;
		if(state.flag2)
			var236 = state.p[1][i$var211];
		else
			var236 = 0.0;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= var236) && (var236 <= 1.0))?Math.log(var236):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.fixedFlag$sample430) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.noisyOr[i$var211]) {
				{
															// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				
												// Substituted "j" with its value "i$var211".
				double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample248[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "i$var381" with its value "0".
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample248[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$stateProbabilityLocal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample248[i$var211]) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
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
				// Initialise the sum of the array elements
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
			
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.issues$var213[i$var211][1] = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2) == 1);
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$27 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$27 = (reduceVar$var300$27 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$27;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 263 drawn from Bernoulli 250. Inference was performed using variable
	// marginalization.
	private final void inferSample263(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		state.constrainedFlag$sample263[i$var211] = false;
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = scratch.cv$var251$stateProbabilityGlobal[threadID$cv$i$var211];
		{
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			state.issues$var213[i$var211][2] = false;
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$28 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$28 = (reduceVar$var300$28 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$28;
			double var249;
			if(state.flag3)
				var249 = state.p[2][i$var211];
			else
				var249 = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= var249) && (var249 <= 1.0))?Math.log((1.0 - var249)):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.fixedFlag$sample430) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(state.noisyOr[i$var211]) {
					{
																		// Substituted "j" with its value "i$var211".
						double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 430 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
																		// Substituted "i$var381" with its value "0".
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
															// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample263[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample263[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$stateProbabilityLocal[0] = cv$accumulatedProbabilities;
		}
		
		// Guards to ensure that issues$var213 is only updated when there is a valid path.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.issues$var213[i$var211][2] = true;
		
		// Guards to ensure that noisyOr is only updated when there is a valid path.
		// 
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var300$28 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// y$var298's comment
			// Set the right hand term to a value from the array issues
			reduceVar$var300$28 = (reduceVar$var300$28 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$28;
		double var249;
		if(state.flag3)
			var249 = state.p[2][i$var211];
		else
			var249 = 0.0;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= var249) && (var249 <= 1.0))?Math.log(var249):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.fixedFlag$sample430) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.noisyOr[i$var211]) {
				{
															// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				
												// Substituted "j" with its value "i$var211".
				double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample263[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "i$var381" with its value "0".
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample263[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$stateProbabilityLocal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample263[i$var211]) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
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
				// Initialise the sum of the array elements
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
			
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.issues$var213[i$var211][2] = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2) == 1);
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$29 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$29 = (reduceVar$var300$29 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$29;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 278 drawn from Bernoulli 263. Inference was performed using variable
	// marginalization.
	private final void inferSample278(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		state.constrainedFlag$sample278[i$var211] = false;
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = scratch.cv$var264$stateProbabilityGlobal[threadID$cv$i$var211];
		{
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			state.issues$var213[i$var211][3] = false;
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$30 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$30 = (reduceVar$var300$30 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$30;
			double var262;
			if(state.flag4)
				var262 = state.p[3][i$var211];
			else
				var262 = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= var262) && (var262 <= 1.0))?Math.log((1.0 - var262)):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.fixedFlag$sample430) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(state.noisyOr[i$var211]) {
					{
																		// Substituted "j" with its value "i$var211".
						double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 430 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
																		// Substituted "i$var381" with its value "0".
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
															// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample278[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample278[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$stateProbabilityLocal[0] = cv$accumulatedProbabilities;
		}
		
		// Guards to ensure that issues$var213 is only updated when there is a valid path.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.issues$var213[i$var211][3] = true;
		
		// Guards to ensure that noisyOr is only updated when there is a valid path.
		// 
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var300$30 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// y$var298's comment
			// Set the right hand term to a value from the array issues
			reduceVar$var300$30 = (reduceVar$var300$30 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$30;
		double var262;
		if(state.flag4)
			var262 = state.p[3][i$var211];
		else
			var262 = 0.0;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= var262) && (var262 <= 1.0))?Math.log(var262):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.fixedFlag$sample430) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.noisyOr[i$var211]) {
				{
															// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				
												// Substituted "j" with its value "i$var211".
				double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample278[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "i$var381" with its value "0".
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample278[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$stateProbabilityLocal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample278[i$var211]) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
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
				// Initialise the sum of the array elements
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
			
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.issues$var213[i$var211][3] = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2) == 1);
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$31 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$31 = (reduceVar$var300$31 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$31;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 293 drawn from Bernoulli 276. Inference was performed using variable
	// marginalization.
	private final void inferSample293(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		state.constrainedFlag$sample293[i$var211] = false;
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = scratch.cv$var277$stateProbabilityGlobal[threadID$cv$i$var211];
		{
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			state.issues$var213[i$var211][4] = false;
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$32 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$32 = (reduceVar$var300$32 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$32;
			double var275;
			if(state.flag5)
				var275 = state.p[4][i$var211];
			else
				var275 = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= var275) && (var275 <= 1.0))?Math.log((1.0 - var275)):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.fixedFlag$sample430) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(state.noisyOr[i$var211]) {
					{
																		// Substituted "j" with its value "i$var211".
						double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 430 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
																		// Substituted "i$var381" with its value "0".
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
															// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample293[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample293[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$stateProbabilityLocal[0] = cv$accumulatedProbabilities;
		}
		
		// Guards to ensure that issues$var213 is only updated when there is a valid path.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.issues$var213[i$var211][4] = true;
		
		// Guards to ensure that noisyOr is only updated when there is a valid path.
		// 
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var300$32 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// y$var298's comment
			// Set the right hand term to a value from the array issues
			reduceVar$var300$32 = (reduceVar$var300$32 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$32;
		double var275;
		if(state.flag5)
			var275 = state.p[4][i$var211];
		else
			var275 = 0.0;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= var275) && (var275 <= 1.0))?Math.log(var275):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.fixedFlag$sample430) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.noisyOr[i$var211]) {
				{
															// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				
												// Substituted "j" with its value "i$var211".
				double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample293[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "i$var381" with its value "0".
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample293[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$stateProbabilityLocal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample293[i$var211]) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
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
				// Initialise the sum of the array elements
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
			
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.issues$var213[i$var211][4] = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2) == 1);
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$33 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$33 = (reduceVar$var300$33 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$33;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 3 drawn from Bernoulli 2. Inference was performed using variable
	// marginalization.
	private final void inferSample3() {
		state.constrainedFlag$sample3 = false;
		{
			// Write out the new value of the sample.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			state.flag1 = false;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = -0.01005033585350145;
			
			// Processing conditional point231.
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((state.fixedFlag$sample233 || state.constrainedFlag$sample233[i$var211])) {
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample3 = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 233 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = (Math.log((state.issues$var213[i$var211][0]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var3$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.flag1 = true;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = -4.605170185988091;
		
		// Processing conditional point231.
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((state.fixedFlag$sample233 || state.constrainedFlag$sample233[i$var211])) {
				double traceTempVariable$var223$2_1 = state.p[0][i$var211];
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample3 = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 233 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var223$2_1) && (traceTempVariable$var223$2_1 <= 1.0))?Math.log((state.issues$var213[i$var211][0]?traceTempVariable$var223$2_1:(1.0 - traceTempVariable$var223$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		scratch.cv$var3$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample3) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var3$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = scratch.cv$var3$stateProbabilityGlobal[1];
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
				cv$logSum = (Math.log((Math.exp((scratch.cv$var3$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var3$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var3$stateProbabilityGlobal[0] = 0.5;
				
												// Get a local reference to the scratch space.
				scratch.cv$var3$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var3$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var3$stateProbabilityGlobal[0] - cv$logSum));
				
												// Get a local reference to the scratch space.
				scratch.cv$var3$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var3$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
									// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var3$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var3$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.flag1 = (DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var3$stateProbabilityGlobal, 2) == 1);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 308 drawn from Bernoulli 289. Inference was performed using variable
	// marginalization.
	private final void inferSample308(int i$var211, int threadID$cv$i$var211, Rng RNG$) {
		state.constrainedFlag$sample308[i$var211] = false;
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = scratch.cv$var290$stateProbabilityGlobal[threadID$cv$i$var211];
		{
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			state.issues$var213[i$var211][5] = false;
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$34 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$34 = (reduceVar$var300$34 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$34;
			double var288;
			if(state.flag6)
				var288 = state.p[5][i$var211];
			else
				var288 = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (((0.0 <= var288) && (var288 <= 1.0))?Math.log((1.0 - var288)):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.fixedFlag$sample430) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(state.noisyOr[i$var211]) {
					{
																		// Substituted "j" with its value "i$var211".
						double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 430 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
																		// Substituted "i$var381" with its value "0".
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					
															// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample308[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				// Constraints moved from conditionals in inner loops/scopes/etc.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				else {
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample308[i$var211] = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "1".
					cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$stateProbabilityLocal[0] = cv$accumulatedProbabilities;
		}
		
		// Guards to ensure that issues$var213 is only updated when there is a valid path.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.issues$var213[i$var211][5] = true;
		
		// Guards to ensure that noisyOr is only updated when there is a valid path.
		// 
		// Reduction of array issues
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		boolean reduceVar$var300$34 = false;
		
		// For each index in the array to be reduced
		for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			// 
												// y$var298's comment
			// Set the right hand term to a value from the array issues
			reduceVar$var300$34 = (reduceVar$var300$34 || state.issues$var213[i$var211][cv$reduction313Index]);
		state.noisyOr[i$var211] = reduceVar$var300$34;
		double var288;
		if(state.flag6)
			var288 = state.p[5][i$var211];
		else
			var288 = 0.0;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (((0.0 <= var288) && (var288 <= 1.0))?Math.log(var288):Double.NEGATIVE_INFINITY);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(state.fixedFlag$sample430) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(state.noisyOr[i$var211]) {
				{
															// Substituted "j" with its value "i$var211".
					double traceTempVariable$var402$4_1 = state.p13[i$var211][0];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 430 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
															// Substituted "i$var381" with its value "0".
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[0][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				
												// Substituted "j" with its value "i$var211".
				double traceTempVariable$var402$4_1 = state.p13[i$var211][1];
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample308[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[1][i$var211]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			// Constraints moved from conditionals in inner loops/scopes/etc.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "i$var381" with its value "0".
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[0][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample308[i$var211] = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 430 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
												// Substituted "i$var381" with its value "1".
				cv$accumulatedProbabilities = (Math.log((state.issues$var383[1][i$var211]?0.0:1.0)) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$stateProbabilityLocal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample308[i$var211]) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
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
				// Initialise the sum of the array elements
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
			
			// Guards to ensure that issues$var213 is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.issues$var213[i$var211][5] = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2) == 1);
			
			// Guards to ensure that noisyOr is only updated when there is a valid path.
			// 
			// Reduction of array issues
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			boolean reduceVar$var300$35 = false;
			
			// For each index in the array to be reduced
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				// 
																// y$var298's comment
				// Set the right hand term to a value from the array issues
				reduceVar$var300$35 = (reduceVar$var300$35 || state.issues$var213[i$var211][cv$reduction313Index]);
			state.noisyOr[i$var211] = reduceVar$var300$35;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 6 drawn from Bernoulli 5. Inference was performed using variable
	// marginalization.
	private final void inferSample6() {
		state.constrainedFlag$sample6 = false;
		{
			// Write out the new value of the sample.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			state.flag2 = false;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = -0.01005033585350145;
			
			// Processing conditional point246.
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((state.fixedFlag$sample248 || state.constrainedFlag$sample248[i$var211])) {
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample6 = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 248 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = (Math.log((state.issues$var213[i$var211][1]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var6$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.flag2 = true;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = -4.605170185988091;
		
		// Processing conditional point246.
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((state.fixedFlag$sample248 || state.constrainedFlag$sample248[i$var211])) {
				double traceTempVariable$var236$2_1 = state.p[1][i$var211];
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample6 = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 248 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var236$2_1) && (traceTempVariable$var236$2_1 <= 1.0))?Math.log((state.issues$var213[i$var211][1]?traceTempVariable$var236$2_1:(1.0 - traceTempVariable$var236$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		scratch.cv$var6$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample6) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var6$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = scratch.cv$var6$stateProbabilityGlobal[1];
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
				cv$logSum = (Math.log((Math.exp((scratch.cv$var6$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var6$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var6$stateProbabilityGlobal[0] = 0.5;
				
												// Get a local reference to the scratch space.
				scratch.cv$var6$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var6$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var6$stateProbabilityGlobal[0] - cv$logSum));
				
												// Get a local reference to the scratch space.
				scratch.cv$var6$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var6$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
									// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var6$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var6$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.flag2 = (DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var6$stateProbabilityGlobal, 2) == 1);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 9 drawn from Bernoulli 8. Inference was performed using variable
	// marginalization.
	private final void inferSample9() {
		state.constrainedFlag$sample9 = false;
		{
			// Write out the new value of the sample.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			state.flag3 = false;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Variable declaration of cv$currentValue moved.
			// Declaration comment was:
			// The value currently being tested
			// 
			// Value of the variable at this index
			// 
									// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = -0.01005033585350145;
			
			// Processing conditional point261.
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((state.fixedFlag$sample263 || state.constrainedFlag$sample263[i$var211])) {
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample9 = true;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 263 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = (Math.log((state.issues$var213[i$var211][2]?0.0:1.0)) + cv$accumulatedProbabilities);
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
									// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var9$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Write out the new value of the sample.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		state.flag3 = true;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Variable declaration of cv$currentValue moved.
		// Declaration comment was:
		// The value currently being tested
		// 
		// Value of the variable at this index
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = -4.605170185988091;
		
		// Processing conditional point261.
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((state.fixedFlag$sample263 || state.constrainedFlag$sample263[i$var211])) {
				double traceTempVariable$var249$2_1 = state.p[2][i$var211];
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample9 = true;
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 263 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var249$2_1) && (traceTempVariable$var249$2_1 <= 1.0))?Math.log((state.issues$var213[i$var211][2]?traceTempVariable$var249$2_1:(1.0 - traceTempVariable$var249$2_1))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		
		// Save the calculated index value into the array of index value probabilities
		// 
						// Get a local reference to the scratch space.
		// 
						// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		scratch.cv$var9$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample9) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialise the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var9$stateProbabilityGlobal[0];
			
			// Unrolled loop
			// 
			// Get a local reference to the scratch space.
			double cv$lseElementValue = scratch.cv$var9$stateProbabilityGlobal[1];
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
				cv$logSum = (Math.log((Math.exp((scratch.cv$var9$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var9$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var9$stateProbabilityGlobal[0] = 0.5;
				
												// Get a local reference to the scratch space.
				scratch.cv$var9$stateProbabilityGlobal[1] = 0.5;
			} else {
				// Unrolled loop
												// Get a local reference to the scratch space.
				scratch.cv$var9$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var9$stateProbabilityGlobal[0] - cv$logSum));
				
												// Get a local reference to the scratch space.
				scratch.cv$var9$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var9$stateProbabilityGlobal[1] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
									// Get a local reference to the scratch space.
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var9$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				scratch.cv$var9$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
												// cv$numStates's comment
			// variable marginalization
			state.flag3 = (DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var9$stateProbabilityGlobal, 2) == 1);
		}
	}

	// Calculate the probability of the samples represented by sample12 using sampled
	// values.
	private final void logProbabilityValue$sample12() {
		// Determine if we need to calculate the values for sample task 12 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample12) {
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
			double cv$distributionAccumulator = Math.log((state.flag4?0.01:0.99));
			
			// Store the sample task probability
			state.logProbability$flag4 = cv$distributionAccumulator;
			
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
			if(state.fixedFlag$sample12)
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
			state.fixedProbFlag$sample12 = state.fixedFlag$sample12;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$flag4);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample12)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$flag4);
		}
	}

	// Calculate the probability of the samples represented by sample15 using sampled
	// values.
	private final void logProbabilityValue$sample15() {
		// Determine if we need to calculate the values for sample task 15 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample15) {
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
			double cv$distributionAccumulator = Math.log((state.flag5?0.01:0.99));
			
			// Store the sample task probability
			state.logProbability$flag5 = cv$distributionAccumulator;
			
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
			if(state.fixedFlag$sample15)
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
			state.fixedProbFlag$sample15 = state.fixedFlag$sample15;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$flag5);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample15)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$flag5);
		}
	}

	// Calculate the probability of the samples represented by sample18 using sampled
	// values.
	private final void logProbabilityValue$sample18() {
		// Determine if we need to calculate the values for sample task 18 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample18) {
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
			double cv$distributionAccumulator = Math.log((state.flag6?0.01:0.99));
			
			// Store the sample task probability
			state.logProbability$flag6 = cv$distributionAccumulator;
			
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
			if(state.fixedFlag$sample18)
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
			state.fixedProbFlag$sample18 = state.fixedFlag$sample18;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$flag6);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample18)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$flag6);
		}
	}

	// Calculate the probability of the samples represented by sample233 using sampled
	// values.
	private final void logProbabilityValue$sample233() {
		// Determine if we need to calculate the values for sample task 233 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample233) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var223;
				if(state.flag1)
					var223 = state.p[0][i$var211];
				else
					var223 = 0.0;
				
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
				double cv$distributionAccumulator = (((0.0 <= var223) && (var223 <= 1.0))?Math.log((state.issues$var213[i$var211][0]?var223:(1.0 - var223))):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample233[i$var211] = cv$distributionAccumulator;
				
				// Update the variable probability
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample233)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample233 = (state.fixedFlag$sample233 && state.fixedFlag$sample3);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = state.logProbability$sample233[i$var211];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Update the variable probability
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleValue);
			}
			
			// Update the variable probability
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample233)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample248 using sampled
	// values.
	private final void logProbabilityValue$sample248() {
		// Determine if we need to calculate the values for sample task 248 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample248) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var236;
				if(state.flag2)
					var236 = state.p[1][i$var211];
				else
					var236 = 0.0;
				
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
				double cv$distributionAccumulator = (((0.0 <= var236) && (var236 <= 1.0))?Math.log((state.issues$var213[i$var211][1]?var236:(1.0 - var236))):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample248[i$var211] = cv$distributionAccumulator;
				
				// Update the variable probability
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample248)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample248 = (state.fixedFlag$sample248 && state.fixedFlag$sample6);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = state.logProbability$sample248[i$var211];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Update the variable probability
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleValue);
			}
			
			// Update the variable probability
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample248)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample263 using sampled
	// values.
	private final void logProbabilityValue$sample263() {
		// Determine if we need to calculate the values for sample task 263 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample263) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var249;
				if(state.flag3)
					var249 = state.p[2][i$var211];
				else
					var249 = 0.0;
				
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
				double cv$distributionAccumulator = (((0.0 <= var249) && (var249 <= 1.0))?Math.log((state.issues$var213[i$var211][2]?var249:(1.0 - var249))):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample263[i$var211] = cv$distributionAccumulator;
				
				// Update the variable probability
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample263)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample263 = (state.fixedFlag$sample263 && state.fixedFlag$sample9);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = state.logProbability$sample263[i$var211];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Update the variable probability
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleValue);
			}
			
			// Update the variable probability
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample263)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample278 using sampled
	// values.
	private final void logProbabilityValue$sample278() {
		// Determine if we need to calculate the values for sample task 278 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample278) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var262;
				if(state.flag4)
					var262 = state.p[3][i$var211];
				else
					var262 = 0.0;
				
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
				double cv$distributionAccumulator = (((0.0 <= var262) && (var262 <= 1.0))?Math.log((state.issues$var213[i$var211][3]?var262:(1.0 - var262))):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample278[i$var211] = cv$distributionAccumulator;
				
				// Update the variable probability
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample278)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample278 = (state.fixedFlag$sample278 && state.fixedFlag$sample12);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = state.logProbability$sample278[i$var211];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Update the variable probability
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleValue);
			}
			
			// Update the variable probability
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample278)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample293 using sampled
	// values.
	private final void logProbabilityValue$sample293() {
		// Determine if we need to calculate the values for sample task 293 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample293) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var275;
				if(state.flag5)
					var275 = state.p[4][i$var211];
				else
					var275 = 0.0;
				
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
				double cv$distributionAccumulator = (((0.0 <= var275) && (var275 <= 1.0))?Math.log((state.issues$var213[i$var211][4]?var275:(1.0 - var275))):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample293[i$var211] = cv$distributionAccumulator;
				
				// Update the variable probability
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample293)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample293 = (state.fixedFlag$sample293 && state.fixedFlag$sample15);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = state.logProbability$sample293[i$var211];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Update the variable probability
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleValue);
			}
			
			// Update the variable probability
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample293)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample3 using sampled values.
	private final void logProbabilityValue$sample3() {
		// Determine if we need to calculate the values for sample task 3 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample3) {
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
			double cv$distributionAccumulator = Math.log((state.flag1?0.01:0.99));
			
			// Store the sample task probability
			state.logProbability$flag1 = cv$distributionAccumulator;
			
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
			if(state.fixedFlag$sample3)
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
			state.fixedProbFlag$sample3 = state.fixedFlag$sample3;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$flag1);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample3)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$flag1);
		}
	}

	// Calculate the probability of the samples represented by sample308 using sampled
	// values.
	private final void logProbabilityValue$sample308() {
		// Determine if we need to calculate the values for sample task 308 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample308) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double var288;
				if(state.flag6)
					var288 = state.p[5][i$var211];
				else
					var288 = 0.0;
				
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
				double cv$distributionAccumulator = (((0.0 <= var288) && (var288 <= 1.0))?Math.log((state.issues$var213[i$var211][5]?var288:(1.0 - var288))):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample308[i$var211] = cv$distributionAccumulator;
				
				// Update the variable probability
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample308)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample308 = (state.fixedFlag$sample308 && state.fixedFlag$sample18);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleValue = state.logProbability$sample308[i$var211];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Update the variable probability
				state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleValue);
			}
			
			// Update the variable probability
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample308)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample430 using sampled
	// values.
	private final void logProbabilityValue$sample430() {
		// Determine if we need to calculate the values for sample task 430 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample430) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int j = 0; j < 5; j += 1) {
				double var402;
				if(state.noisyOr[j])
					// Substituted "i$var381" with its value "0".
					var402 = state.p13[j][0];
				else
					var402 = 0.0;
				
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				// 
				// Substituted "i$var381" with its value "0".
				double cv$weightedProbability = (((0.0 <= var402) && (var402 <= 1.0))?Math.log((state.issues$var383[0][j]?var402:(1.0 - var402))):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$weightedProbability);
				
				// Store the sample task probability
				// 
				// Substituted "i$var381" with its value "0".
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				state.logProbability$sample430[0][j] = cv$weightedProbability;
				
				// Update the variable probability
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				state.logProbability$n13State = (state.logProbability$n13State + cv$weightedProbability);
			}
			for(int j = 0; j < 5; j += 1) {
				double var402;
				if(state.noisyOr[j])
					// Substituted "i$var381" with its value "1".
					var402 = state.p13[j][1];
				else
					var402 = 0.0;
				
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				// 
				// Substituted "i$var381" with its value "1".
				double cv$weightedProbability = (((0.0 <= var402) && (var402 <= 1.0))?Math.log((state.issues$var383[1][j]?var402:(1.0 - var402))):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$weightedProbability);
				
				// Store the sample task probability
				// 
				// Substituted "i$var381" with its value "1".
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				state.logProbability$sample430[1][j] = cv$weightedProbability;
				
				// Update the variable probability
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				state.logProbability$n13State = (state.logProbability$n13State + cv$weightedProbability);
			}
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$issues$var383 = (state.logProbability$issues$var383 + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample430)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample430 = ((((((state.fixedFlag$sample430 && state.fixedFlag$sample233) && state.fixedFlag$sample248) && state.fixedFlag$sample263) && state.fixedFlag$sample278) && state.fixedFlag$sample293) && state.fixedFlag$sample308);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int j = 0; j < 5; j += 1) {
				// Substituted "i$var381" with its value "0".
				double cv$sampleValue = state.logProbability$sample430[0][j];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Update the variable probability
				state.logProbability$n13State = (state.logProbability$n13State + cv$sampleValue);
			}
			for(int j = 0; j < 5; j += 1) {
				// Substituted "i$var381" with its value "1".
				double cv$sampleValue = state.logProbability$sample430[1][j];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Update the variable probability
				state.logProbability$n13State = (state.logProbability$n13State + cv$sampleValue);
			}
			
			// Update the variable probability
			state.logProbability$issues$var383 = (state.logProbability$issues$var383 + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample430)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample6 using sampled values.
	private final void logProbabilityValue$sample6() {
		// Determine if we need to calculate the values for sample task 6 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample6) {
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
			double cv$distributionAccumulator = Math.log((state.flag2?0.01:0.99));
			
			// Store the sample task probability
			state.logProbability$flag2 = cv$distributionAccumulator;
			
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
			if(state.fixedFlag$sample6)
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
			state.fixedProbFlag$sample6 = state.fixedFlag$sample6;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$flag2);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample6)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$flag2);
		}
	}

	// Calculate the probability of the samples represented by sample9 using sampled values.
	private final void logProbabilityValue$sample9() {
		// Determine if we need to calculate the values for sample task 9 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample9) {
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
			double cv$distributionAccumulator = Math.log((state.flag3?0.01:0.99));
			
			// Store the sample task probability
			state.logProbability$flag3 = cv$distributionAccumulator;
			
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
			if(state.fixedFlag$sample9)
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
			state.fixedProbFlag$sample9 = state.fixedFlag$sample9;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$flag3);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample9)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$flag3);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample3)
			state.flag1 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample6)
			state.flag2 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample9)
			state.flag3 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample12)
			state.flag4 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample15)
			state.flag5 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample18)
			state.flag6 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample233) {
							// This value is not used before it is set again, so removing the value declaration.
							double var223;
							if(state.flag1)
								var223 = state.p[0][i$var211];
							else
								var223 = 0.0;
							state.issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample248) {
							// This value is not used before it is set again, so removing the value declaration.
							double var236;
							if(state.flag2)
								var236 = state.p[1][i$var211];
							else
								var236 = 0.0;
							state.issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample263) {
							// This value is not used before it is set again, so removing the value declaration.
							double var249;
							if(state.flag3)
								var249 = state.p[2][i$var211];
							else
								var249 = 0.0;
							state.issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample278) {
							// This value is not used before it is set again, so removing the value declaration.
							double var262;
							if(state.flag4)
								var262 = state.p[3][i$var211];
							else
								var262 = 0.0;
							state.issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample293) {
							// This value is not used before it is set again, so removing the value declaration.
							double var275;
							if(state.flag5)
								var275 = state.p[4][i$var211];
							else
								var275 = 0.0;
							state.issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample308) {
							// This value is not used before it is set again, so removing the value declaration.
							double var288;
							if(state.flag6)
								var288 = state.p[5][i$var211];
							else
								var288 = 0.0;
							state.issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((((((!state.fixedFlag$sample233 || !state.fixedFlag$sample248) || !state.fixedFlag$sample263) || !state.fixedFlag$sample278) || !state.fixedFlag$sample293) || !state.fixedFlag$sample308)) {
							// Reduction of array issues
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							boolean reduceVar$var300$42 = false;
							
							// For each index in the array to be reduced
							for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
																																// y$var298's comment
								// Set the right hand term to a value from the array issues
								reduceVar$var300$42 = (reduceVar$var300$42 || state.issues$var213[i$var211][cv$reduction313Index]);
							state.noisyOr[i$var211] = reduceVar$var300$42;
						}
					}
			}
		);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample430)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
							int i$var381 = index$i$var381;
							int threadID$i$var381 = threadID$index$i$var381;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, 5, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j = forStart$j; j < forEnd$j; j += 1) {
											// This value is not used before it is set again, so removing the value declaration.
											double var402;
											if(state.noisyOr[j])
												var402 = state.p13[j][i$var381];
											else
												var402 = 0.0;
											state.issues$var383[i$var381][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
										}
								}
							);
							
							// Reduction of array issues
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							boolean reduceVar$var414$8 = false;
							
							// For each index in the array to be reduced
							for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
																																// y$var412's comment
								// Set the right hand term to a value from the array issues
								reduceVar$var414$8 = (reduceVar$var414$8 || state.issues$var383[i$var381][cv$reduction435Index]);
							state.n13State[i$var381] = reduceVar$var414$8;
						}
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample3)
			state.flag1 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample6)
			state.flag2 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample9)
			state.flag3 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample12)
			state.flag4 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample15)
			state.flag5 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample18)
			state.flag6 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample233) {
							// This value is not used before it is set again, so removing the value declaration.
							double var223;
							if(state.flag1)
								var223 = state.p[0][i$var211];
							else
								var223 = 0.0;
							state.issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample248) {
							// This value is not used before it is set again, so removing the value declaration.
							double var236;
							if(state.flag2)
								var236 = state.p[1][i$var211];
							else
								var236 = 0.0;
							state.issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample263) {
							// This value is not used before it is set again, so removing the value declaration.
							double var249;
							if(state.flag3)
								var249 = state.p[2][i$var211];
							else
								var249 = 0.0;
							state.issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample278) {
							// This value is not used before it is set again, so removing the value declaration.
							double var262;
							if(state.flag4)
								var262 = state.p[3][i$var211];
							else
								var262 = 0.0;
							state.issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample293) {
							// This value is not used before it is set again, so removing the value declaration.
							double var275;
							if(state.flag5)
								var275 = state.p[4][i$var211];
							else
								var275 = 0.0;
							state.issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample308) {
							// This value is not used before it is set again, so removing the value declaration.
							double var288;
							if(state.flag6)
								var288 = state.p[5][i$var211];
							else
								var288 = 0.0;
							state.issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						}
						
						// Reduction of array issues
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						boolean reduceVar$var300$46 = false;
						
						// For each index in the array to be reduced
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
																												// y$var298's comment
							// Set the right hand term to a value from the array issues
							reduceVar$var300$46 = (reduceVar$var300$46 || state.issues$var213[i$var211][cv$reduction313Index]);
						state.noisyOr[i$var211] = reduceVar$var300$46;
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, 2, 1,
			(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
						int i$var381 = index$i$var381;
						int threadID$i$var381 = threadID$index$i$var381;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample430)
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, 5, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j = forStart$j; j < forEnd$j; j += 1) {
											// This value is not used before it is set again, so removing the value declaration.
											double var402;
											if(state.noisyOr[j])
												var402 = state.p13[j][i$var381];
											else
												var402 = 0.0;
											state.issues$var383[i$var381][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
										}
								}
							);

						
						// Reduction of array issues
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						boolean reduceVar$var414$12 = false;
						
						// For each index in the array to be reduced
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
																												// y$var412's comment
							// Set the right hand term to a value from the array issues
							reduceVar$var414$12 = (reduceVar$var414$12 || state.issues$var383[i$var381][cv$reduction435Index]);
						state.n13State[i$var381] = reduceVar$var414$12;
					}
			}
		);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample3)
			state.flag1 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample6)
			state.flag2 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample9)
			state.flag3 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample12)
			state.flag4 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample15)
			state.flag5 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample18)
			state.flag6 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample233) {
							// This value is not used before it is set again, so removing the value declaration.
							double var223;
							if(state.flag1)
								var223 = state.p[0][i$var211];
							else
								var223 = 0.0;
							state.issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample248) {
							// This value is not used before it is set again, so removing the value declaration.
							double var236;
							if(state.flag2)
								var236 = state.p[1][i$var211];
							else
								var236 = 0.0;
							state.issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample263) {
							// This value is not used before it is set again, so removing the value declaration.
							double var249;
							if(state.flag3)
								var249 = state.p[2][i$var211];
							else
								var249 = 0.0;
							state.issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample278) {
							// This value is not used before it is set again, so removing the value declaration.
							double var262;
							if(state.flag4)
								var262 = state.p[3][i$var211];
							else
								var262 = 0.0;
							state.issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample293) {
							// This value is not used before it is set again, so removing the value declaration.
							double var275;
							if(state.flag5)
								var275 = state.p[4][i$var211];
							else
								var275 = 0.0;
							state.issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample308) {
							// This value is not used before it is set again, so removing the value declaration.
							double var288;
							if(state.flag6)
								var288 = state.p[5][i$var211];
							else
								var288 = 0.0;
							state.issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						}
						
						// Reduction of array issues
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						boolean reduceVar$var300$43 = false;
						
						// For each index in the array to be reduced
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
																												// y$var298's comment
							// Set the right hand term to a value from the array issues
							reduceVar$var300$43 = (reduceVar$var300$43 || state.issues$var213[i$var211][cv$reduction313Index]);
						state.noisyOr[i$var211] = reduceVar$var300$43;
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, 2, 1,
			(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
						int i$var381 = index$i$var381;
						int threadID$i$var381 = threadID$index$i$var381;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample430)
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, 5, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j = forStart$j; j < forEnd$j; j += 1) {
											// This value is not used before it is set again, so removing the value declaration.
											double var402;
											if(state.noisyOr[j])
												var402 = state.p13[j][i$var381];
											else
												var402 = 0.0;
											state.issues$var383[i$var381][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
										}
								}
							);

						
						// Reduction of array issues
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						boolean reduceVar$var414$9 = false;
						
						// For each index in the array to be reduced
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
																												// y$var412's comment
							// Set the right hand term to a value from the array issues
							reduceVar$var414$9 = (reduceVar$var414$9 || state.issues$var383[i$var381][cv$reduction435Index]);
						state.n13State[i$var381] = reduceVar$var414$9;
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample3)
			state.flag1 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample6)
			state.flag2 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample9)
			state.flag3 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample12)
			state.flag4 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample15)
			state.flag5 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample18)
			state.flag6 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample233) {
							// This value is not used before it is set again, so removing the value declaration.
							double var223;
							if(state.flag1)
								var223 = state.p[0][i$var211];
							else
								var223 = 0.0;
							state.issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample248) {
							// This value is not used before it is set again, so removing the value declaration.
							double var236;
							if(state.flag2)
								var236 = state.p[1][i$var211];
							else
								var236 = 0.0;
							state.issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample263) {
							// This value is not used before it is set again, so removing the value declaration.
							double var249;
							if(state.flag3)
								var249 = state.p[2][i$var211];
							else
								var249 = 0.0;
							state.issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample278) {
							// This value is not used before it is set again, so removing the value declaration.
							double var262;
							if(state.flag4)
								var262 = state.p[3][i$var211];
							else
								var262 = 0.0;
							state.issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample293) {
							// This value is not used before it is set again, so removing the value declaration.
							double var275;
							if(state.flag5)
								var275 = state.p[4][i$var211];
							else
								var275 = 0.0;
							state.issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample308) {
							// This value is not used before it is set again, so removing the value declaration.
							double var288;
							if(state.flag6)
								var288 = state.p[5][i$var211];
							else
								var288 = 0.0;
							state.issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((((((!state.fixedFlag$sample233 || !state.fixedFlag$sample248) || !state.fixedFlag$sample263) || !state.fixedFlag$sample278) || !state.fixedFlag$sample293) || !state.fixedFlag$sample308)) {
							// Reduction of array issues
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							boolean reduceVar$var300$44 = false;
							
							// For each index in the array to be reduced
							for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
																																// y$var298's comment
								// Set the right hand term to a value from the array issues
								reduceVar$var300$44 = (reduceVar$var300$44 || state.issues$var213[i$var211][cv$reduction313Index]);
							state.noisyOr[i$var211] = reduceVar$var300$44;
						}
					}
			}
		);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample430)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
							int i$var381 = index$i$var381;
							int threadID$i$var381 = threadID$index$i$var381;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, 5, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j = forStart$j; j < forEnd$j; j += 1) {
											// This value is not used before it is set again, so removing the value declaration.
											double var402;
											if(state.noisyOr[j])
												var402 = state.p13[j][i$var381];
											else
												var402 = 0.0;
											state.issues$var383[i$var381][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
										}
								}
							);
							
							// Reduction of array issues
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							boolean reduceVar$var414$10 = false;
							
							// For each index in the array to be reduced
							for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
								// Copy the result of the reduction into the variable returned by the reduction.
								// 
																																// y$var412's comment
								// Set the right hand term to a value from the array issues
								reduceVar$var414$10 = (reduceVar$var414$10 || state.issues$var383[i$var381][cv$reduction435Index]);
							state.n13State[i$var381] = reduceVar$var414$10;
						}
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample3)
			state.flag1 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample6)
			state.flag2 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample9)
			state.flag3 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample12)
			state.flag4 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample15)
			state.flag5 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		if(!state.fixedFlag$sample18)
			state.flag6 = DistributionSampling.sampleBernoulli(state.RNG$, 0.01);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample233) {
							// This value is not used before it is set again, so removing the value declaration.
							double var223;
							if(state.flag1)
								var223 = state.p[0][i$var211];
							else
								var223 = 0.0;
							state.issues$var213[i$var211][0] = DistributionSampling.sampleBernoulli(RNG$1, var223);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample248) {
							// This value is not used before it is set again, so removing the value declaration.
							double var236;
							if(state.flag2)
								var236 = state.p[1][i$var211];
							else
								var236 = 0.0;
							state.issues$var213[i$var211][1] = DistributionSampling.sampleBernoulli(RNG$1, var236);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample263) {
							// This value is not used before it is set again, so removing the value declaration.
							double var249;
							if(state.flag3)
								var249 = state.p[2][i$var211];
							else
								var249 = 0.0;
							state.issues$var213[i$var211][2] = DistributionSampling.sampleBernoulli(RNG$1, var249);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample278) {
							// This value is not used before it is set again, so removing the value declaration.
							double var262;
							if(state.flag4)
								var262 = state.p[3][i$var211];
							else
								var262 = 0.0;
							state.issues$var213[i$var211][3] = DistributionSampling.sampleBernoulli(RNG$1, var262);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample293) {
							// This value is not used before it is set again, so removing the value declaration.
							double var275;
							if(state.flag5)
								var275 = state.p[4][i$var211];
							else
								var275 = 0.0;
							state.issues$var213[i$var211][4] = DistributionSampling.sampleBernoulli(RNG$1, var275);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample308) {
							// This value is not used before it is set again, so removing the value declaration.
							double var288;
							if(state.flag6)
								var288 = state.p[5][i$var211];
							else
								var288 = 0.0;
							state.issues$var213[i$var211][5] = DistributionSampling.sampleBernoulli(RNG$1, var288);
						}
						
						// Reduction of array issues
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						boolean reduceVar$var300$45 = false;
						
						// For each index in the array to be reduced
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
																												// y$var298's comment
							// Set the right hand term to a value from the array issues
							reduceVar$var300$45 = (reduceVar$var300$45 || state.issues$var213[i$var211][cv$reduction313Index]);
						state.noisyOr[i$var211] = reduceVar$var300$45;
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, 2, 1,
			(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
						int i$var381 = index$i$var381;
						int threadID$i$var381 = threadID$index$i$var381;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!state.fixedFlag$sample430)
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, 5, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j = forStart$j; j < forEnd$j; j += 1) {
											// This value is not used before it is set again, so removing the value declaration.
											double var402;
											if(state.noisyOr[j])
												var402 = state.p13[j][i$var381];
											else
												var402 = 0.0;
											state.issues$var383[i$var381][j] = DistributionSampling.sampleBernoulli(RNG$2, var402);
										}
								}
							);

						
						// Reduction of array issues
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						boolean reduceVar$var414$11 = false;
						
						// For each index in the array to be reduced
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
																												// y$var412's comment
							// Set the right hand term to a value from the array issues
							reduceVar$var414$11 = (reduceVar$var414$11 || state.issues$var383[i$var381][cv$reduction435Index]);
						state.n13State[i$var381] = reduceVar$var414$11;
					}
			}
		);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample3)
				inferSample3();
			if(!state.fixedFlag$sample6)
				inferSample6();
			if(!state.fixedFlag$sample9)
				inferSample9();
			if(!state.fixedFlag$sample12)
				inferSample12();
			if(!state.fixedFlag$sample15)
				inferSample15();
			if(!state.fixedFlag$sample18)
				inferSample18();
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
							if(!state.fixedFlag$sample233)
								inferSample233(i$var211, threadID$i$var211, RNG$1);
							if(!state.fixedFlag$sample248)
								inferSample248(i$var211, threadID$i$var211, RNG$1);
							if(!state.fixedFlag$sample263)
								inferSample263(i$var211, threadID$i$var211, RNG$1);
							if(!state.fixedFlag$sample278)
								inferSample278(i$var211, threadID$i$var211, RNG$1);
							if(!state.fixedFlag$sample293)
								inferSample293(i$var211, threadID$i$var211, RNG$1);
							if(!state.fixedFlag$sample308)
								inferSample308(i$var211, threadID$i$var211, RNG$1);
						}
				}
			);
		}
		// Infer the samples in reverse chronological order.
		else {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
							if(!state.fixedFlag$sample308)
								inferSample308(i$var211, threadID$i$var211, RNG$1);
							if(!state.fixedFlag$sample293)
								inferSample293(i$var211, threadID$i$var211, RNG$1);
							if(!state.fixedFlag$sample278)
								inferSample278(i$var211, threadID$i$var211, RNG$1);
							if(!state.fixedFlag$sample263)
								inferSample263(i$var211, threadID$i$var211, RNG$1);
							if(!state.fixedFlag$sample248)
								inferSample248(i$var211, threadID$i$var211, RNG$1);
							if(!state.fixedFlag$sample233)
								inferSample233(i$var211, threadID$i$var211, RNG$1);
						}
				}
			);
			if(!state.fixedFlag$sample18)
				inferSample18();
			if(!state.fixedFlag$sample15)
				inferSample15();
			if(!state.fixedFlag$sample12)
				inferSample12();
			if(!state.fixedFlag$sample9)
				inferSample9();
			if(!state.fixedFlag$sample6)
				inferSample6();
			if(!state.fixedFlag$sample3)
				inferSample3();
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample3)
			drawValueSample3();
		if(!state.constrainedFlag$sample6)
			drawValueSample6();
		if(!state.constrainedFlag$sample9)
			drawValueSample9();
		if(!state.constrainedFlag$sample12)
			drawValueSample12();
		if(!state.constrainedFlag$sample15)
			drawValueSample15();
		if(!state.constrainedFlag$sample18)
			drawValueSample18();
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						if(!state.constrainedFlag$sample233[i$var211])
							drawValueSample233(i$var211, threadID$i$var211, RNG$1);
						if(!state.constrainedFlag$sample248[i$var211])
							drawValueSample248(i$var211, threadID$i$var211, RNG$1);
						if(!state.constrainedFlag$sample263[i$var211])
							drawValueSample263(i$var211, threadID$i$var211, RNG$1);
						if(!state.constrainedFlag$sample278[i$var211])
							drawValueSample278(i$var211, threadID$i$var211, RNG$1);
						if(!state.constrainedFlag$sample293[i$var211])
							drawValueSample293(i$var211, threadID$i$var211, RNG$1);
						if(!state.constrainedFlag$sample308[i$var211])
							drawValueSample308(i$var211, threadID$i$var211, RNG$1);
					}
			}
		);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample430)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$index$i$var381, int forEnd$index$i$var381, int threadID$index$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$i$var381 = forStart$index$i$var381; index$i$var381 < forEnd$index$i$var381; index$i$var381 += 1) {
							int i$var381 = index$i$var381;
							int threadID$i$var381 = threadID$index$i$var381;
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, 5, 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int j = forStart$j; j < forEnd$j; j += 1)
											drawValueSample430(i$var381, j, threadID$j, RNG$2);
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
		if(!state.fixedProbFlag$sample3)
			state.logProbability$flag1 = Double.NaN;
		if(!state.fixedProbFlag$sample6)
			state.logProbability$flag2 = Double.NaN;
		if(!state.fixedProbFlag$sample9)
			state.logProbability$flag3 = Double.NaN;
		if(!state.fixedProbFlag$sample12)
			state.logProbability$flag4 = Double.NaN;
		if(!state.fixedProbFlag$sample15)
			state.logProbability$flag5 = Double.NaN;
		if(!state.fixedProbFlag$sample18)
			state.logProbability$flag6 = Double.NaN;
		state.logProbability$issues$var213 = 0.0;
		state.logProbability$noisyOr = 0.0;
		if(!state.fixedProbFlag$sample233) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				state.logProbability$sample233[i$var211] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample248) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				state.logProbability$sample248[i$var211] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample263) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				state.logProbability$sample263[i$var211] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample278) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				state.logProbability$sample278[i$var211] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample293) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				state.logProbability$sample293[i$var211] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample308) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				state.logProbability$sample308[i$var211] = Double.NaN;
		}
		state.logProbability$issues$var383 = 0.0;
		state.logProbability$n13State = 0.0;
		if(!state.fixedProbFlag$sample430) {
			// Unrolled loop
			for(int j = 0; j < 5; j += 1)
				// Substituted "i$var381" with its value "0".
				state.logProbability$sample430[0][j] = Double.NaN;
			for(int j = 0; j < 5; j += 1)
				// Substituted "i$var381" with its value "1".
				state.logProbability$sample430[1][j] = Double.NaN;
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		double[] var23 = state.p[0];
		var23[0] = 0.0;
		var23[1] = 1.0;
		var23[2] = 0.0;
		var23[3] = 0.0;
		var23[4] = 0.0;
		double[] var53 = state.p[1];
		var53[0] = 0.5;
		var53[1] = 0.5;
		var53[2] = 0.0;
		var53[3] = 0.0;
		var53[4] = 0.0;
		double[] var81 = state.p[2];
		var81[0] = 0.0;
		var81[1] = 0.0;
		var81[2] = 0.0;
		var81[3] = 1.0;
		var81[4] = 0.0;
		double[] var111 = state.p[3];
		var111[0] = 0.0;
		var111[1] = 0.0;
		var111[2] = 0.0;
		var111[3] = 1.0;
		var111[4] = 0.0;
		double[] var141 = state.p[4];
		var141[0] = 0.0;
		var141[1] = 0.0;
		var141[2] = 1.0;
		var141[3] = 0.0;
		var141[4] = 0.0;
		double[] var171 = state.p[5];
		var171[0] = 0.0;
		var171[1] = 0.0;
		var171[2] = 1.0;
		var171[3] = 0.0;
		var171[4] = 0.0;
		double[] var306 = state.p13[0];
		var306[0] = 0.1;
		var306[1] = 0.9;
		double[] var319 = state.p13[1];
		var319[0] = 1.0;
		var319[1] = 0.0;
		double[] var332 = state.p13[2];
		var332[0] = 0.5;
		var332[1] = 0.5;
		double[] var345 = state.p13[3];
		var345[0] = 0.5;
		var345[1] = 0.5;
		double[] var358 = state.p13[4];
		var358[0] = 0.0;
		var358[1] = 1.0;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample233$1 = 0; index$constrainedFlag$sample233$1 < state.constrainedFlag$sample233.length; index$constrainedFlag$sample233$1 += 1)
			state.constrainedFlag$sample233[index$constrainedFlag$sample233$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample248$1 = 0; index$constrainedFlag$sample248$1 < state.constrainedFlag$sample248.length; index$constrainedFlag$sample248$1 += 1)
			state.constrainedFlag$sample248[index$constrainedFlag$sample248$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample263$1 = 0; index$constrainedFlag$sample263$1 < state.constrainedFlag$sample263.length; index$constrainedFlag$sample263$1 += 1)
			state.constrainedFlag$sample263[index$constrainedFlag$sample263$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample278$1 = 0; index$constrainedFlag$sample278$1 < state.constrainedFlag$sample278.length; index$constrainedFlag$sample278$1 += 1)
			state.constrainedFlag$sample278[index$constrainedFlag$sample278$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample293$1 = 0; index$constrainedFlag$sample293$1 < state.constrainedFlag$sample293.length; index$constrainedFlag$sample293$1 += 1)
			state.constrainedFlag$sample293[index$constrainedFlag$sample293$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample308$1 = 0; index$constrainedFlag$sample308$1 < state.constrainedFlag$sample308.length; index$constrainedFlag$sample308$1 += 1)
			state.constrainedFlag$sample308[index$constrainedFlag$sample308$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample3)
			logProbabilityValue$sample3();
		if(state.fixedFlag$sample6)
			logProbabilityValue$sample6();
		if(state.fixedFlag$sample9)
			logProbabilityValue$sample9();
		if(state.fixedFlag$sample12)
			logProbabilityValue$sample12();
		if(state.fixedFlag$sample15)
			logProbabilityValue$sample15();
		if(state.fixedFlag$sample18)
			logProbabilityValue$sample18();
		if(state.fixedFlag$sample233)
			logProbabilityValue$sample233();
		if(state.fixedFlag$sample248)
			logProbabilityValue$sample248();
		if(state.fixedFlag$sample263)
			logProbabilityValue$sample263();
		if(state.fixedFlag$sample278)
			logProbabilityValue$sample278();
		if(state.fixedFlag$sample293)
			logProbabilityValue$sample293();
		if(state.fixedFlag$sample308)
			logProbabilityValue$sample308();
		if(state.fixedFlag$sample430)
			logProbabilityValue$sample430();
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
		logProbabilityValue$sample3();
		logProbabilityValue$sample6();
		logProbabilityValue$sample9();
		logProbabilityValue$sample12();
		logProbabilityValue$sample15();
		logProbabilityValue$sample18();
		logProbabilityValue$sample233();
		logProbabilityValue$sample248();
		logProbabilityValue$sample263();
		logProbabilityValue$sample278();
		logProbabilityValue$sample293();
		logProbabilityValue$sample308();
		logProbabilityValue$sample430();
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
		logProbabilityValue$sample3();
		logProbabilityValue$sample6();
		logProbabilityValue$sample9();
		logProbabilityValue$sample12();
		logProbabilityValue$sample15();
		logProbabilityValue$sample18();
		logProbabilityValue$sample233();
		logProbabilityValue$sample248();
		logProbabilityValue$sample263();
		logProbabilityValue$sample278();
		logProbabilityValue$sample293();
		logProbabilityValue$sample308();
		logProbabilityValue$sample430();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$i$var211, int forEnd$i$var211, int threadID$i$var211, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var211 = forStart$i$var211; i$var211 < forEnd$i$var211; i$var211 += 1) {
						// Reduction of array issues
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						boolean reduceVar$var300$47 = false;
						
						// For each index in the array to be reduced
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
																												// y$var298's comment
							// Set the right hand term to a value from the array issues
							reduceVar$var300$47 = (reduceVar$var300$47 || state.issues$var213[i$var211][cv$reduction313Index]);
						state.noisyOr[i$var211] = reduceVar$var300$47;
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, 2, 1,
			(int forStart$i$var381, int forEnd$i$var381, int threadID$i$var381, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var381 = forStart$i$var381; i$var381 < forEnd$i$var381; i$var381 += 1) {
						// Reduction of array issues
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						boolean reduceVar$var414$13 = false;
						
						// For each index in the array to be reduced
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1)
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							// 
																												// y$var412's comment
							// Set the right hand term to a value from the array issues
							reduceVar$var414$13 = (reduceVar$var414$13 || state.issues$var383[i$var381][cv$reduction435Index]);
						state.n13State[i$var381] = reduceVar$var414$13;
					}
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2026, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model NoisyOr() {\n"
		     + "\n"
		     + "\n"
		     + "    // 1)\n"
		     + "    double prior1 = 0.01;\n"
		     + "    boolean flag1 = bernoulli(prior1).sample();\n"
		     + "    \n"
		     + "    // 2)\n"
		     + "    double prior2 = 0.01;\n"
		     + "    boolean flag2 = bernoulli(prior2).sample();\n"
		     + "    \n"
		     + "    // 3)\n"
		     + "    double prior3 = 0.01;\n"
		     + "    boolean flag3 = bernoulli(prior3).sample();\n"
		     + "    \n"
		     + "    // 4)\n"
		     + "    double prior4 = 0.01;\n"
		     + "    boolean flag4 = bernoulli(prior4).sample();\n"
		     + "    \n"
		     + "    // 5)\n"
		     + "    double prior5 = 0.01;\n"
		     + "    boolean flag5 = bernoulli(prior5).sample();\n"
		     + "    \n"
		     + "    // 6)\n"
		     + "    double prior6 = 0.01;\n"
		     + "    boolean flag6 = bernoulli(prior6).sample();\n"
		     + "    \n"
		     + "    // Start n12\n"
		     + "    double[][] p = new double[6][];\n"
		     + "    p[0] = new double[] {0,1,0,0,0};\n"
		     + "    p[1] = new double[] {0.5,0.5,0,0,0};\n"
		     + "    p[2] = new double[] {0,0,0,1,0};\n"
		     + "    p[3] = new double[] {0,0,0,1,0};\n"
		     + "    p[4] = new double[] {0,0,1,0,0};\n"
		     + "    p[5] = new double[] {0,0,1,0,0};\n"
		     + "    \n"
		     + "    boolean[] noisyOr = new boolean[5];\n"
		     + "    \n"
		     + "    for(int i=0; i<5; i++) {\n"
		     + "        boolean[] issues = new boolean[6];\n"
		     + "        issues[0] = bernoulli(flag1?p[0][i]:0).sample();\n"
		     + "        issues[1] = bernoulli(flag2?p[1][i]:0).sample();\n"
		     + "        issues[2] = bernoulli(flag3?p[2][i]:0).sample();\n"
		     + "        issues[3] = bernoulli(flag4?p[3][i]:0).sample();\n"
		     + "        issues[4] = bernoulli(flag5?p[4][i]:0).sample();\n"
		     + "        issues[5] = bernoulli(flag6?p[5][i]:0).sample();\n"
		     + "        \n"
		     + "        noisyOr[i] = reduce(issues, false, (x, y) -> {\n"
		     + "            return x || y;\n"
		     + "        });\n"
		     + "    }\n"
		     + "    \n"
		     + "    // Starting n13\n"
		     + "    double[][] p13 = new double[5][];\n"
		     + "    p13[0] = new double[] {0.1, 0.9};\n"
		     + "    p13[1] = new double[] {1.0, 0.0};\n"
		     + "    p13[2] = new double[] {0.5, 0.5};\n"
		     + "    p13[3] = new double[] {0.5, 0.5};\n"
		     + "    p13[4] = new double[] {0.0, 1.0};\n"
		     + "    \n"
		     + "    boolean[] n13State = new boolean[2];\n"
		     + "    \n"
		     + "    for(int i=0; i<2; i++) {\n"
		     + "        boolean[] issues = new boolean[5];\n"
		     + "        for(int j=0; j<5; j++)\n"
		     + "            issues[j] = bernoulli(noisyOr[j]?p13[j][i]:0).sample();\n"
		     + "        \n"
		     + "        n13State[i] = reduce(issues, false, (x, y) -> {\n"
		     + "            return x || y;\n"
		     + "        });\n"
		     + "    }\n"
		     + "}";
	}
}