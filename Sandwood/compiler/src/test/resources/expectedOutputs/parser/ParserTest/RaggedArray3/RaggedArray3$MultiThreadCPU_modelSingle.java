package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.RaggedArray3$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.RaggedArray3.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class RaggedArray3$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$var37$countGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Variable to record the maximum value of Task Get 37. Initially set to the value
			// of putTask 17.
			int cv$var34$max = 2;
			
			// Test if the input to putTask 35 is larger than the current values.
			cv$var34$max = Math.max(cv$var34$max, 3);
			
			// Allocation of cv$var37$countGlobal for single threaded execution
			cv$var37$countGlobal = new double[cv$var34$max];
		}
	}


	public RaggedArray3$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample39
	private final void drawValueSample39() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$37_16 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 17 and consumer double[] 35.
		{
			{
				if((0 == state.y))
					lengthCV$a$37_16 = 2;
			}
		}
		
		// Looking for a path between Put 35 and consumer double[] 35.
		{
			{
				if((1 == state.y))
					lengthCV$a$37_16 = 3;
			}
		}
		DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_16, state.d);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 39 drawn from Dirichlet 36. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample39() {
		if(true) {
			state.constrainedFlag$sample39 = false;
			
			// A reference local to the function for the sample variable.
			double[] cv$targetLocal = state.d;
			
			// A local reference to the scratch space.
			double[] cv$countLocal = scratch.cv$var37$countGlobal;
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$37_14 = -1;
			
			// calculate array length.
			// 
			// Looking for a path between Put 17 and consumer double[] 35.
			{
				{
					if((0 == state.y))
						lengthCV$a$37_14 = 2;
				}
			}
			
			// Looking for a path between Put 35 and consumer double[] 35.
			{
				{
					if((1 == state.y))
						lengthCV$a$37_14 = 3;
				}
			}
			
			// Get the length of the array
			int cv$arrayLength = lengthCV$a$37_14;
			
			// Initialize the array values to 0.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				// Processing random variable 38.
				{
					{
						{
							// Processing sample task 53 of consumer random variable null.
							{
								{
									for(int var50 = 0; var50 < state.length$obs_measured; var50 += 1) {
										// Flag recording if this sample task of the consuming random variable is constrained.
										boolean cv$sampleConstrained = true;
										if(cv$sampleConstrained) {
											// Mark that the sample has observed constrained data.
											state.constrainedFlag$sample39 = true;
											{
												{
													{
														{
															{
																// Increment the sample counter with the value sampled by sample task 53 of random
																// variable var38
																cv$countLocal[state.obs[var50]] = (cv$countLocal[state.obs[var50]] + 1.0);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample39) {
				// Allocate a local variable to hold the length of the array.
				int lengthCV$a$37_15 = -1;
				
				// calculate array length.
				// 
				// Looking for a path between Put 17 and consumer double[] 35.
				{
					{
						if((0 == state.y))
							lengthCV$a$37_15 = 2;
					}
				}
				
				// Looking for a path between Put 35 and consumer double[] 35.
				{
					{
						if((1 == state.y))
							lengthCV$a$37_15 = 3;
					}
				}
				
				// Calculate the new sample value
				// 
				// Calculate a new sample value and write it into cv$targetLocal.
				Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.a[state.y], cv$countLocal, cv$targetLocal, lengthCV$a$37_15);
			}
		}
	}

	// Calculate the probability of the samples represented by sample39 using sampled
	// values.
	private final void logProbabilityValue$sample39() {
		// Determine if we need to calculate the values for sample task 39 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample39) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				{
					// The sample value to calculate the probability of generating
					double[] cv$sampleValue = state.d;
					{
						{
							double[] var35 = state.a[state.y];
							
							// Allocate a local variable to hold the length of the array.
							int lengthCV$a$37_17 = -1;
							
							// calculate array length.
							// 
							// Looking for a path between Put 17 and consumer double[] 35.
							{
								{
									if((0 == state.y))
										lengthCV$a$37_17 = 2;
								}
							}
							
							// Looking for a path between Put 35 and consumer double[] 35.
							{
								{
									if((1 == state.y))
										lengthCV$a$37_17 = 3;
								}
							}
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, var35, lengthCV$a$37_17));
							
							// Add the probability of this sample task to the distribution accumulator.
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								// If the second value is -infinity.
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							
							// Add the probability of this distribution configuration to the accumulator.
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				// Return negative infinity if no distribution probability space is reached.
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				// Scale the probability relative to the observed distribution space.
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			
			// Add the probability of this sample task to the sample task accumulator.
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			
			// Store the sample task probability
			state.logProbability$d = cv$sampleProbability;
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample39)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample39 = state.fixedFlag$sample39;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$d;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample39)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample53 using sampled
	// values.
	private final void logProbabilityValue$sample53() {
		// Determine if we need to calculate the values for sample task 53 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample53) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var50 = 0; var50 < state.length$obs_measured; var50 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = state.obs[var50];
						{
							{
								// Allocate a local variable to hold the length of the array.
								int lengthCV$a$37_18 = -1;
								
								// calculate array length.
								// 
								// Looking for a path between Put 17 and consumer double[] 35.
								{
									{
										if((0 == state.y))
											lengthCV$a$37_18 = 2;
									}
								}
								
								// Looking for a path between Put 35 and consumer double[] 35.
								{
									{
										if((1 == state.y))
											lengthCV$a$37_18 = 3;
									}
								}
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$a$37_18)) && (0 < lengthCV$a$37_18)) && (0.0 <= state.d[cv$sampleValue])) && (state.d[cv$sampleValue] <= 1.0))?Math.log(state.d[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
								// Add the probability of this sample task to the distribution accumulator.
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									// If the second value is -infinity.
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								
								// Add the probability of this distribution configuration to the accumulator.
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				state.logProbability$var51 = cv$sampleAccumulator;
			
			// Update the variable probability
			state.logProbability$obs = (state.logProbability$obs + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample53 = state.fixedFlag$sample39;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var50 = 0; var50 < state.length$obs_measured; var50 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var51;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$obs = (state.logProbability$obs + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$37_19 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 17 and consumer double[] 35.
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample39)
						lengthCV$a$37_19 = 2;
				}
			}
		}
		
		// Looking for a path between Put 35 and consumer double[] 35.
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample39)
						lengthCV$a$37_19 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample39)
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_19, state.d);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$37_20 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 17 and consumer double[] 35.
		{
			{
				if((0 == state.y))
					lengthCV$a$37_20 = 2;
			}
		}
		
		// Looking for a path between Put 35 and consumer double[] 35.
		{
			{
				if((1 == state.y))
					lengthCV$a$37_20 = 3;
			}
		}
		
		// Alternative name for lengthCV$a$37_20 to make it effectively final.
		int lengthCV$a$37_20$1 = lengthCV$a$37_20;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.length$obs_measured, 1,
			(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1)
						state.obs[var50] = DistributionSampling.sampleCategorical(RNG$1, state.d, lengthCV$a$37_20$1);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$37_25 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 17 and consumer double[] 35.
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample39)
						lengthCV$a$37_25 = 2;
				}
			}
		}
		
		// Looking for a path between Put 35 and consumer double[] 35.
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample39)
						lengthCV$a$37_25 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample39)
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_25, state.d);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$37_21 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 17 and consumer double[] 35.
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample39)
						lengthCV$a$37_21 = 2;
				}
			}
		}
		
		// Looking for a path between Put 35 and consumer double[] 35.
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample39)
						lengthCV$a$37_21 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample39)
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_21, state.d);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$37_22 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 17 and consumer double[] 35.
		{
			{
				if((0 == state.y))
					lengthCV$a$37_22 = 2;
			}
		}
		
		// Looking for a path between Put 35 and consumer double[] 35.
		{
			{
				if((1 == state.y))
					lengthCV$a$37_22 = 3;
			}
		}
		
		// Alternative name for lengthCV$a$37_22 to make it effectively final.
		int lengthCV$a$37_22$1 = lengthCV$a$37_22;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.length$obs_measured, 1,
			(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1)
						state.obs[var50] = DistributionSampling.sampleCategorical(RNG$1, state.d, lengthCV$a$37_22$1);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$37_23 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 17 and consumer double[] 35.
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample39)
						lengthCV$a$37_23 = 2;
				}
			}
		}
		
		// Looking for a path between Put 35 and consumer double[] 35.
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample39)
						lengthCV$a$37_23 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample39)
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_23, state.d);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$37_24 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 17 and consumer double[] 35.
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample39)
						lengthCV$a$37_24 = 2;
				}
			}
		}
		
		// Looking for a path between Put 35 and consumer double[] 35.
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample39)
						lengthCV$a$37_24 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample39)
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_24, state.d);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample39)
				inferSample39();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!state.fixedFlag$sample39)
				inferSample39();
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample39)
			drawValueSample39();
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
		if(!state.fixedProbFlag$sample39)
			state.logProbability$d = Double.NaN;
		state.logProbability$obs = 0.0;
		if(!state.fixedProbFlag$sample53)
			state.logProbability$var51 = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		double[] var6 = state.a[0];
		var6[0] = 0.4;
		var6[1] = 0.6;
		double[] var19 = state.a[1];
		var19[0] = 0.2;
		var19[1] = 0.3;
		var19[2] = 0.5;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample39)
			logProbabilityValue$sample39();
		logProbabilityValue$sample53();
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
		logProbabilityValue$sample39();
		logProbabilityValue$sample53();
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
		logProbabilityValue$sample39();
		logProbabilityValue$sample53();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Deep copy between arrays
		int[] cv$source1 = state.obs_measured;
		int[] cv$target1 = state.obs;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
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
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model RaggedArray3(int y, int[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    \n"
		     + "    double[] d = dirichlet(a[y]).sample();\n"
		     + "    int[] obs = categorical(d).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}