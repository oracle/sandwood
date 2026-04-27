package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.RaggedArray4$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.RaggedArray4.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class RaggedArray4$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$var45$stateProbabilityGlobal;
		double[] cv$var48$countGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for cv$var45$stateProbabilityGlobal
			{
				// Allocation of cv$var45$stateProbabilityGlobal for single threaded execution
				cv$var45$stateProbabilityGlobal = new double[2];
			}
			
			// Constructor for cv$var48$countGlobal
			{
				// Variable to record the maximum value of Task Get 48. Initially set to the value
				// of putTask 16.
				int cv$var33$max = 2;
				
				// Test if the input to putTask 34 is larger than the current values.
				cv$var33$max = Math.max(cv$var33$max, 3);
				
				// Allocation of cv$var48$countGlobal for single threaded execution
				cv$var48$countGlobal = new double[cv$var33$max];
			}
		}
	}


	public RaggedArray4$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample47
	private final void drawValueSample47() {
		state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
	}

	// Pick a value from the distribution for the unconditioned variable from sample50
	private final void drawValueSample50() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$48_18 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 16 and consumer double[] 46.
		{
			{
				if((0 == state.y))
					lengthCV$a$48_18 = 2;
			}
		}
		
		// Looking for a path between Put 34 and consumer double[] 46.
		{
			{
				if((1 == state.y))
					lengthCV$a$48_18 = 3;
			}
		}
		DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_18, state.d);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 47 drawn from Categorical 44. Inference was performed using variable
	// marginalization.
	private final void inferSample47() {
		if(true) {
			state.constrainedFlag$sample47 = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = scratch.cv$var45$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				// Initialize the summed probabilities to 0.
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				
				// Initialize a counter to track the reached distributions.
				double cv$reachedDistributionSourceRV = 0.0;
				
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				double cv$accumulatedDistributionProbabilities = 0.0;
				
				// The value currently being tested
				int cv$currentValue;
				
				// Value of the variable at this index
				cv$currentValue = cv$valuePos;
				
				// Write out the new value of the sample.
				state.y = cv$currentValue;
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < 2)) && (0 < 2)) && (0.0 <= state.b[cv$currentValue])) && (state.b[cv$currentValue] <= 1.0))?Math.log(state.b[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 47.
					{
						{
							{
								int traceTempVariable$y$3_1 = cv$currentValue;
								
								// Processing sample task 50 of consumer random variable null.
								{
									{
										// Flag recording if this sample task of the consuming random variable is constrained.
										boolean cv$sampleConstrained = (state.fixedFlag$sample50 || state.constrainedFlag$sample50);
										if(cv$sampleConstrained) {
											// Mark that the sample has observed constrained data.
											state.constrainedFlag$sample47 = true;
											
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
																double[] var46 = state.a[traceTempVariable$y$3_1];
																
																// Allocate a local variable to hold the length of the array.
																int lengthCV$a$48_15 = -1;
																
																// calculate array length.
																// 
																// Looking for a path between Put 16 and consumer double[] 46.
																{
																	{
																		if((0 == traceTempVariable$y$3_1))
																			lengthCV$a$48_15 = 2;
																	}
																}
																
																// Looking for a path between Put 34 and consumer double[] 46.
																{
																	{
																		if((1 == traceTempVariable$y$3_1))
																			lengthCV$a$48_15 = 3;
																	}
																}
																
																// Record the probability of sample task 50 generating output with current configuration.
																if(((Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(state.d, var46, lengthCV$a$48_15)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(state.d, var46, lengthCV$a$48_15)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(state.d, var46, lengthCV$a$48_15));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(state.d, var46, lengthCV$a$48_15)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(state.d, var46, lengthCV$a$48_15)));
																}
																
																// Recorded the probability of reaching sample task 50 with the current configuration.
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
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample47) {
				// The sum of all the probabilities in log space
				double cv$logSum = 0.0;
				
				// Sum all the values
				{
					// Initialize the max to the first element.
					double cv$lseMax = cv$stateProbabilityLocal[0];
					
					// Find max value.
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
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
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						
						// Increment the value of the target, moving the value back into log space.
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				
				// If all the sum is zero, just share the probability evenly.
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				
				// Set array values that are not computed for the input to negative infinity.
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				
				// Write out the new value of the sample.
				state.y = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 50 drawn from Dirichlet 47. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample50() {
		if(true) {
			state.constrainedFlag$sample50 = false;
			
			// A reference local to the function for the sample variable.
			double[] cv$targetLocal = state.d;
			
			// A local reference to the scratch space.
			double[] cv$countLocal = scratch.cv$var48$countGlobal;
			
			// Allocate a local variable to hold the length of the array.
			int lengthCV$a$48_16 = -1;
			
			// calculate array length.
			// 
			// Looking for a path between Put 16 and consumer double[] 46.
			{
				{
					if((0 == state.y))
						lengthCV$a$48_16 = 2;
				}
			}
			
			// Looking for a path between Put 34 and consumer double[] 46.
			{
				{
					if((1 == state.y))
						lengthCV$a$48_16 = 3;
				}
			}
			
			// Get the length of the array
			int cv$arrayLength = lengthCV$a$48_16;
			
			// Initialize the array values to 0.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				// Processing random variable 49.
				{
					{
						{
							// Processing sample task 64 of consumer random variable null.
							{
								{
									for(int var61 = 0; var61 < state.length$obs_measured; var61 += 1) {
										// Flag recording if this sample task of the consuming random variable is constrained.
										boolean cv$sampleConstrained = true;
										if(cv$sampleConstrained) {
											// Mark that the sample has observed constrained data.
											state.constrainedFlag$sample50 = true;
											{
												{
													{
														{
															{
																// Increment the sample counter with the value sampled by sample task 64 of random
																// variable var49
																cv$countLocal[state.obs[var61]] = (cv$countLocal[state.obs[var61]] + 1.0);
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
			if(state.constrainedFlag$sample50) {
				// Allocate a local variable to hold the length of the array.
				int lengthCV$a$48_17 = -1;
				
				// calculate array length.
				// 
				// Looking for a path between Put 16 and consumer double[] 46.
				{
					{
						if((0 == state.y))
							lengthCV$a$48_17 = 2;
					}
				}
				
				// Looking for a path between Put 34 and consumer double[] 46.
				{
					{
						if((1 == state.y))
							lengthCV$a$48_17 = 3;
					}
				}
				
				// Calculate the new sample value
				// 
				// Calculate a new sample value and write it into cv$targetLocal.
				Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.a[state.y], cv$countLocal, cv$targetLocal, lengthCV$a$48_17);
			}
		}
	}

	// Calculate the probability of the samples represented by sample47 using sampled
	// values.
	private final void logProbabilityValue$sample47() {
		// Determine if we need to calculate the values for sample task 47 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample47) {
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
					int cv$sampleValue = state.y;
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0 < 2)) && (0.0 <= state.b[cv$sampleValue])) && (state.b[cv$sampleValue] <= 1.0))?Math.log(state.b[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			state.logProbability$y = cv$sampleProbability;
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample47)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample47 = state.fixedFlag$sample47;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$y;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample47)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample50 using sampled
	// values.
	private final void logProbabilityValue$sample50() {
		// Determine if we need to calculate the values for sample task 50 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample50) {
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
							double[] var46 = state.a[state.y];
							
							// Allocate a local variable to hold the length of the array.
							int lengthCV$a$48_19 = -1;
							
							// calculate array length.
							// 
							// Looking for a path between Put 16 and consumer double[] 46.
							{
								{
									if((0 == state.y))
										lengthCV$a$48_19 = 2;
								}
							}
							
							// Looking for a path between Put 34 and consumer double[] 46.
							{
								{
									if((1 == state.y))
										lengthCV$a$48_19 = 3;
								}
							}
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, var46, lengthCV$a$48_19));
							
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
			if(state.fixedFlag$sample50)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample50 = (state.fixedFlag$sample50 && state.fixedFlag$sample47);
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
			if(state.fixedFlag$sample50)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample64 using sampled
	// values.
	private final void logProbabilityValue$sample64() {
		// Determine if we need to calculate the values for sample task 64 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample64) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var61 = 0; var61 < state.length$obs_measured; var61 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = state.obs[var61];
						{
							{
								// Allocate a local variable to hold the length of the array.
								int lengthCV$a$48_20 = -1;
								
								// calculate array length.
								// 
								// Looking for a path between Put 16 and consumer double[] 46.
								{
									{
										if((0 == state.y))
											lengthCV$a$48_20 = 2;
									}
								}
								
								// Looking for a path between Put 34 and consumer double[] 46.
								{
									{
										if((1 == state.y))
											lengthCV$a$48_20 = 3;
									}
								}
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$a$48_20)) && (0 < lengthCV$a$48_20)) && (0.0 <= state.d[cv$sampleValue])) && (state.d[cv$sampleValue] <= 1.0))?Math.log(state.d[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
				state.logProbability$var62 = cv$sampleAccumulator;
			
			// Update the variable probability
			state.logProbability$obs = (state.logProbability$obs + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample64 = state.fixedFlag$sample50;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var61 = 0; var61 < state.length$obs_measured; var61 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var62;
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
		if(!state.fixedFlag$sample47)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$48_21 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 16 and consumer double[] 46.
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample50)
						lengthCV$a$48_21 = 2;
				}
			}
		}
		
		// Looking for a path between Put 34 and consumer double[] 46.
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample50)
						lengthCV$a$48_21 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample50)
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_21, state.d);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$48_22 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 16 and consumer double[] 46.
		{
			{
				if((0 == state.y))
					lengthCV$a$48_22 = 2;
			}
		}
		
		// Looking for a path between Put 34 and consumer double[] 46.
		{
			{
				if((1 == state.y))
					lengthCV$a$48_22 = 3;
			}
		}
		
		// Alternative name for lengthCV$a$48_22 to make it effectively final.
		int lengthCV$a$48_22$1 = lengthCV$a$48_22;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.length$obs_measured, 1,
			(int forStart$var61, int forEnd$var61, int threadID$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var61 = forStart$var61; var61 < forEnd$var61; var61 += 1)
						state.obs[var61] = DistributionSampling.sampleCategorical(RNG$1, state.d, lengthCV$a$48_22$1);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample47)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$48_27 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 16 and consumer double[] 46.
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample50)
						lengthCV$a$48_27 = 2;
				}
			}
		}
		
		// Looking for a path between Put 34 and consumer double[] 46.
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample50)
						lengthCV$a$48_27 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample50)
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_27, state.d);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample47)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$48_23 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 16 and consumer double[] 46.
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample50)
						lengthCV$a$48_23 = 2;
				}
			}
		}
		
		// Looking for a path between Put 34 and consumer double[] 46.
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample50)
						lengthCV$a$48_23 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample50)
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_23, state.d);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$48_24 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 16 and consumer double[] 46.
		{
			{
				if((0 == state.y))
					lengthCV$a$48_24 = 2;
			}
		}
		
		// Looking for a path between Put 34 and consumer double[] 46.
		{
			{
				if((1 == state.y))
					lengthCV$a$48_24 = 3;
			}
		}
		
		// Alternative name for lengthCV$a$48_24 to make it effectively final.
		int lengthCV$a$48_24$1 = lengthCV$a$48_24;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.length$obs_measured, 1,
			(int forStart$var61, int forEnd$var61, int threadID$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var61 = forStart$var61; var61 < forEnd$var61; var61 += 1)
						state.obs[var61] = DistributionSampling.sampleCategorical(RNG$1, state.d, lengthCV$a$48_24$1);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample47)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$48_25 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 16 and consumer double[] 46.
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample50)
						lengthCV$a$48_25 = 2;
				}
			}
		}
		
		// Looking for a path between Put 34 and consumer double[] 46.
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample50)
						lengthCV$a$48_25 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample50)
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_25, state.d);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample47)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$48_26 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 16 and consumer double[] 46.
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample50)
						lengthCV$a$48_26 = 2;
				}
			}
		}
		
		// Looking for a path between Put 34 and consumer double[] 46.
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample50)
						lengthCV$a$48_26 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample50)
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_26, state.d);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample47)
				inferSample47();
			if(!state.fixedFlag$sample50)
				inferSample50();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!state.fixedFlag$sample50)
				inferSample50();
			if(!state.fixedFlag$sample47)
				inferSample47();
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample47)
			drawValueSample47();
		if(!state.constrainedFlag$sample50)
			drawValueSample50();
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
		if(!state.fixedProbFlag$sample47)
			state.logProbability$y = Double.NaN;
		if(!state.fixedProbFlag$sample50)
			state.logProbability$d = Double.NaN;
		state.logProbability$obs = 0.0;
		if(!state.fixedProbFlag$sample64)
			state.logProbability$var62 = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		double[] var5 = state.a[0];
		var5[0] = 0.4;
		var5[1] = 0.6;
		double[] var18 = state.a[1];
		var18[0] = 0.2;
		var18[1] = 0.3;
		var18[2] = 0.5;
		state.b[0] = 0.35;
		state.b[1] = 0.65;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample47)
			logProbabilityValue$sample47();
		if(state.fixedFlag$sample50)
			logProbabilityValue$sample50();
		logProbabilityValue$sample64();
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
		logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		logProbabilityValue$sample64();
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
		logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		logProbabilityValue$sample64();
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
		     + " \n"
		     + " package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model RaggedArray4(int[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    double[] b = { 0.35, 0.65 };\n"
		     + "    int y = categorical(b).sample();\n"
		     + "    double[] d = dirichlet(a[y]).sample();\n"
		     + "    int[] obs = categorical(d).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}