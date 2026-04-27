package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.RaggedArray5$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.RaggedArray5.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class RaggedArray5$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {}
	}


	public RaggedArray5$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample39
	private final void drawValueSample39() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$37_13 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 17 and consumer double[] 35.
		{
			{
				if((0 == state.y))
					lengthCV$a$37_13 = 2;
			}
		}
		
		// Looking for a path between Put 35 and consumer double[] 35.
		{
			{
				if((1 == state.y))
					lengthCV$a$37_13 = 3;
			}
		}
		DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_13, state.d);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 39 drawn from Dirichlet 36. Inference was performed using Metropolis-Hastings.
	private final void inferSample39() {
		if(true) {
			state.constrainedFlag$sample39 = false;
			
			// A reference local to the function for the sample variable.
			double[] cv$targetLocal = state.d;
			
			// Calculate the probability of the random variable generating the original sampled
			// value.
			double cv$originalProbability = 0.0;
			
			// The probability of the random variable generating the new sample value.
			double cv$proposedProbability = 0.0;
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$37_11 = -1;
			
			// calculate array length.
			// 
			// Looking for a path between Put 17 and consumer double[] 35.
			{
				{
					if((0 == state.y))
						lengthCV$a$37_11 = 2;
				}
			}
			
			// Looking for a path between Put 35 and consumer double[] 35.
			{
				{
					if((1 == state.y))
						lengthCV$a$37_11 = 3;
				}
			}
			int cv$arrayLength = lengthCV$a$37_11;
			
			// Pick a value in the array to adjust.
			int cv$indexToChange = (int)(0.0 + ((cv$arrayLength - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			
			// Pick how much the value should be moved by. Initially this value is proposed as
			// a ratio of the current magnitude of the value, we will check to make sure the adjustment
			// will not make this value too large or other values too small and adjust if required
			// before it is applied.
			double cv$movementRatio = ((DistributionSampling.sampleBeta(state.RNG$, 5, 5) * 1.9999) - 1);
			
			// Calculate how much we are going to move the array index cv$indexToChange the by.
			// 
			// Allocate space for the proposed change to be stored as an absolute value
			double cv$proposedDifference;
			
			// Test if we are increasing or decreasing the value at the index. For each case calculate
			// the maximum valid adjustment.
			if((cv$movementRatio < 0))
				// The maximum reduction of the array at the index without going below 0 is the value
				// of the array at that index.
				cv$proposedDifference = cv$targetLocal[cv$indexToChange];
			else {
				// Calculate the maximum magnitude of the proposed index change.
				// Initially set the maximum to the amount that the value we are changing could increase
				// without exceeding 1
				cv$proposedDifference = (1.0 - cv$targetLocal[cv$indexToChange]);
				
				// For the array values up to the index we are going to change calculate the maximum
				// move possible.
				for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1) {
					// Calculate the maximum change value that the value at array index cv$loopIndex could
					// support. Based on moving all other values by an equal amount.
					double cv$temp = (cv$targetLocal[cv$loopIndex] * (cv$arrayLength - 1));
					
					// If the maximum move is less than the proposed move update the move size.
					if((cv$temp < cv$proposedDifference))
						cv$proposedDifference = cv$temp;
				}
				
				// For the array values after the index we are going to change calculate the maximum
				// move possible.
				for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1) {
					// Calculate the maximum change value that the value at array index cv$loopIndex could
					// support. Based on moving all other values by an equal amount.
					double cv$temp = (cv$targetLocal[cv$loopIndex] * (cv$arrayLength - 1));
					
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
			for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
				if((state.constrainedFlag$sample39 || (cv$valuePos == 0))) {
					// Initialize the summed probabilities to 0 in log space.
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					
					// Initialize a counter to track the reached distributions.
					double cv$reachedDistributionSourceRV = 0.0;
					
					// Initialize an accumulator to take the product of all the distribution probabilities
					// in log space.
					double cv$accumulatedDistributionProbabilities = 0.0;
					if((cv$valuePos == 1)) {
						// Update Sample and intermediate values
						{
							// Update the sample value
							// Update all the indexes up to the index selected.
							for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
								cv$targetLocal[cv$loopIndex] = (cv$targetLocal[cv$loopIndex] - cv$rebalanceValue);
							
							// Update the selected index.
							cv$targetLocal[cv$indexToChange] = (cv$targetLocal[cv$indexToChange] + cv$proposedDifference);
							
							// Update all the indexes after the index we selected.
							for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
								cv$targetLocal[cv$loopIndex] = (cv$targetLocal[cv$loopIndex] - cv$rebalanceValue);
						}
					}
					{
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						
						// Constructing a random variable input for use later.
						double[] var35 = state.a[state.y];
						
						// Allocate a local variable to hold the length of the array.
						int lengthCV$a$37_12 = -1;
						
						// calculate array length.
						// 
						// Looking for a path between Put 17 and consumer double[] 35.
						{
							{
								if((0 == state.y))
									lengthCV$a$37_12 = 2;
							}
						}
						
						// Looking for a path between Put 35 and consumer double[] 35.
						{
							{
								if((1 == state.y))
									lengthCV$a$37_12 = 3;
							}
						}
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$targetLocal, var35, lengthCV$a$37_12));
						
						// Processing random variable 39.
						{
							{
								{
									// Processing sample task 54 of consumer random variable null.
									{
										{
											for(int var51 = 0; var51 < state.length$obs_measured; var51 += 1) {
												// Flag recording if this sample task of the consuming random variable is constrained.
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													// Mark that the sample has observed constrained data.
													state.constrainedFlag$sample39 = true;
													
													// Set an accumulator to sum the probabilities for each possible configuration of
													// inputs.
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													
													// Set an accumulator to record the consumer distributions not seen. Initially set
													// to 1 as seen values will be deducted from this value.
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	{
																		// Constructing a random variable input for use later.
																		double var38 = state.d[state.y];
																		
																		// Record the probability of sample task 54 generating output with current configuration.
																		if(((Math.log(1.0) + (((0.0 <= var38) && (var38 <= 1.0))?Math.log((state.obs[var51]?var38:(1.0 - var38))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var38) && (var38 <= 1.0))?Math.log((state.obs[var51]?var38:(1.0 - var38))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var38) && (var38 <= 1.0))?Math.log((state.obs[var51]?var38:(1.0 - var38))):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var38) && (var38 <= 1.0))?Math.log((state.obs[var51]?var38:(1.0 - var38))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var38) && (var38 <= 1.0))?Math.log((state.obs[var51]?var38:(1.0 - var38))):Double.NEGATIVE_INFINITY)));
																		}
																		
																		// Recorded the probability of reaching sample task 54 with the current configuration.
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													}
													
													// A check to ensure rounding of floating point values can never result in a negative
													// value.
													cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
													
													// Multiply (log space add) in the probability of the sample task to the overall probability
													// for this configuration of the source random variable.
													if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
														else
															cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
													}
												}
											}
										}
									}
								}
							}
						}
						
						// Add the values for the source and any standard consumers for this configuration
						// of arguments to the source.
						if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
						else {
							// If the second value is -infinity.
							if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
								cv$stateProbabilityValue = cv$accumulatedProbabilities;
							else
								cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
						}
					}
					
					// Save the probability of the original value.
					if((cv$valuePos == 0))
						cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
					
					// Save the probability of the proposed value.
					else
						cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
					
					// Ratio of the probability of proposed and original sample values
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					
					// Test if the probability of the sample is sufficient to keep the value. This needs
					// to be less than or equal as otherwise if the proposed value is not possible and
					// the random value is 0 an impossible value will be accepted.
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$))))) || Double.isNaN(cv$ratio))) {
							// If it is not revert the sample value and intermediates to their original values.
							// 
							// Set the sample value
							// 
							// Calculate the new sample value
							// 
							// Update the sample value
							// Update all the indexes up to the index selected.
							for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
								cv$targetLocal[cv$loopIndex] = (cv$targetLocal[cv$loopIndex] + cv$rebalanceValue);
							
							// Update the selected index.
							cv$targetLocal[cv$indexToChange] = (cv$targetLocal[cv$indexToChange] - cv$proposedDifference);
							
							// Update all the indexes after the index we selected.
							for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
								cv$targetLocal[cv$loopIndex] = (cv$targetLocal[cv$loopIndex] + cv$rebalanceValue);
						}
					}
				}
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
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, var35, lengthCV$a$37_14));
							
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

	// Calculate the probability of the samples represented by sample54 using sampled
	// values.
	private final void logProbabilityValue$sample54() {
		// Determine if we need to calculate the values for sample task 54 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample54) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var51 = 0; var51 < state.length$obs_measured; var51 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = state.obs[var51];
						{
							{
								double var38 = state.d[state.y];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var38) && (var38 <= 1.0))?Math.log((cv$sampleValue?var38:(1.0 - var38))):Double.NEGATIVE_INFINITY));
								
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
				state.logProbability$var52 = cv$sampleAccumulator;
			
			// Update the variable probability
			state.logProbability$obs = (state.logProbability$obs + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample54 = state.fixedFlag$sample39;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var51 = 0; var51 < state.length$obs_measured; var51 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var52;
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
		int lengthCV$a$37_15 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 17 and consumer double[] 35.
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample39)
						lengthCV$a$37_15 = 2;
				}
			}
		}
		
		// Looking for a path between Put 35 and consumer double[] 35.
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample39)
						lengthCV$a$37_15 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample39)
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_15, state.d);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.length$obs_measured, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1)
						state.obs[var51] = DistributionSampling.sampleBernoulli(RNG$1, state.d[state.y]);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
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
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$37_16 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 17 and consumer double[] 35.
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample39)
						lengthCV$a$37_16 = 2;
				}
			}
		}
		
		// Looking for a path between Put 35 and consumer double[] 35.
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample39)
						lengthCV$a$37_16 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample39)
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_16, state.d);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.length$obs_measured, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1)
						state.obs[var51] = DistributionSampling.sampleBernoulli(RNG$1, state.d[state.y]);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$37_17 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 17 and consumer double[] 35.
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample39)
						lengthCV$a$37_17 = 2;
				}
			}
		}
		
		// Looking for a path between Put 35 and consumer double[] 35.
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample39)
						lengthCV$a$37_17 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample39)
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_17, state.d);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$37_18 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 17 and consumer double[] 35.
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample39)
						lengthCV$a$37_18 = 2;
				}
			}
		}
		
		// Looking for a path between Put 35 and consumer double[] 35.
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample39)
						lengthCV$a$37_18 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample39)
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_18, state.d);
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
		if(!state.fixedProbFlag$sample54)
			state.logProbability$var52 = Double.NaN;
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
		logProbabilityValue$sample54();
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
		logProbabilityValue$sample54();
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
		logProbabilityValue$sample54();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Deep copy between arrays
		boolean[] cv$source1 = state.obs_measured;
		boolean[] cv$target1 = state.obs;
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
		     + "public model RaggedArray5(int y, boolean[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    \n"
		     + "    double[] d = dirichlet(a[y]).sample();\n"
		     + "    boolean[] obs = bernoulli(d[y]).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}