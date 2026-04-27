package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.DiscreteChoice$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.DiscreteChoice.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DiscreteChoice$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		boolean[] guard$sample24put65$global;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Calculate the largest index of i that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_i$var61 = 0;
			cv$max_i$var61 = Math.max(cv$max_i$var61, ((state.noProducts - 0) / 1));
			
			// Allocation of guard$sample24put65$global for single threaded execution
			guard$sample24put65$global = new boolean[cv$max_i$var61];
		}
	}


	public DiscreteChoice$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample24
	private final void drawValueSample24(int i$var18) {
		state.ut[i$var18] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		
		// Guards to ensure that exped is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 24 and consumer double[] 39.
		{
			{
				for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						{
							state.exped[i$var36] = Math.exp(state.ut[i$var36]);
						}
					}
				}
			}
		}
		
		// Guards to ensure that sum is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 24 and consumer double 50.
		{
			{
				for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
							{
								// Reduction of array exped
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								double reduceVar$sum$13 = 0.0;
								
								// For each index in the array to be reduced
								for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1) {
									// Set the left hand term of the reduction function to the return variable value.
									double i$var47 = reduceVar$sum$13;
									
									// Set the right hand term to a value from the array exped
									double j = state.exped[cv$reduction44Index];
									
									// Execute the reduction function, saving the result into the return value.
									// 
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$sum$13 = (i$var47 + j);
								}
								
								// Write out the new sample value.
								state.sum = reduceVar$sum$13;
							}
						}
					}
				}
			}
		}
		
		// Guards to ensure that prob is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 24 and consumer double[] 64.
		{
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			boolean[] guard$sample24put65 = scratch.guard$sample24put65$global;
			{
				for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
							for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
								// Set the flags to false
								guard$sample24put65[((i$var61 - 0) / 1)] = false;
						}
					}
				}
			}
			{
				for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
							if((i$var36 == i$var61))
								// Set the flags to false
								guard$sample24put65[((i$var61 - 0) / 1)] = false;
						}
					}
				}
			}
			{
				for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
							for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
								if(!guard$sample24put65[((i$var61 - 0) / 1)]) {
									// The body will execute, so should not be executed again
									guard$sample24put65[((i$var61 - 0) / 1)] = true;
									{
										state.prob[i$var61] = (state.exped[i$var61] / state.sum);
									}
								}
							}
						}
					}
				}
			}
			{
				for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
							if((i$var36 == i$var61)) {
								if(!guard$sample24put65[((i$var61 - 0) / 1)]) {
									// The body will execute, so should not be executed again
									guard$sample24put65[((i$var61 - 0) / 1)] = true;
									{
										state.prob[i$var61] = (state.exped[i$var61] / state.sum);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 24 drawn from Gaussian 23. Inference was performed using Metropolis-Hastings.
	private final void inferSample24(int i$var18) {
		if(true) {
			state.constrainedFlag$sample24[((i$var18 - 1) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// Metropolis-Hastings
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// The original value of the sample
			double cv$originalValue = state.ut[i$var18];
			
			// The probability of the random variable generating the originally sampled value
			double cv$originalProbability = 0.0;
			
			// Calculate a proposed variance.
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			
			// Ensure the variance is at least 0.01
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			
			// The proposed new value for the sample
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			
			// The probability of the random variable generating the new sample value.
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample24[((i$var18 - 1) / 1)] || (cv$valuePos == 0))) {
					// Initialize the summed probabilities to 0.
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					
					// Initialize a counter to track the reached distributions.
					double cv$reachedDistributionSourceRV = 0.0;
					
					// Initialize a log space accumulator to take the product of all the distribution
					// probabilities.
					double cv$accumulatedDistributionProbabilities = 0.0;
					
					// The value currently being tested
					double cv$currentValue;
					if((cv$valuePos == 0))
						// Set the current value to the current state of the tree.
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						
						// Update Sample and intermediate values
						// 
						// Write out the value of the sample to a temporary variable prior to updating the
						// intermediate variables.
						double var24 = cv$proposedValue;
						
						// Guards to ensure that ut is only updated when there is a valid path.
						{
							{
								{
									state.ut[i$var18] = cv$currentValue;
								}
							}
						}
						
						// Guards to ensure that exped is only updated when there is a valid path.
						// 
						// Looking for a path between Sample 24 and consumer double[] 39.
						{
							{
								for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
									if((i$var18 == i$var36)) {
										{
											state.exped[i$var36] = Math.exp(state.ut[i$var36]);
										}
									}
								}
							}
						}
						
						// Guards to ensure that sum is only updated when there is a valid path.
						// 
						// Looking for a path between Sample 24 and consumer double 50.
						{
							{
								for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
									if((i$var18 == i$var36)) {
										if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
											{
												// Reduction of array exped
												// 
												// A generated name to prevent name collisions if the reduction is implemented more
												// than once in inference and probability code. Initialize the variable to the unit
												// value
												double reduceVar$sum$10 = 0.0;
												
												// For each index in the array to be reduced
												for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1) {
													// Set the left hand term of the reduction function to the return variable value.
													double i$var47 = reduceVar$sum$10;
													
													// Set the right hand term to a value from the array exped
													double j = state.exped[cv$reduction44Index];
													
													// Execute the reduction function, saving the result into the return value.
													// 
													// Copy the result of the reduction into the variable returned by the reduction.
													reduceVar$sum$10 = (i$var47 + j);
												}
												
												// Write out the new sample value.
												state.sum = reduceVar$sum$10;
											}
										}
									}
								}
							}
						}
						
						// Guards to ensure that prob is only updated when there is a valid path.
						// 
						// Looking for a path between Sample 24 and consumer double[] 64.
						{
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							boolean[] guard$sample24put65 = scratch.guard$sample24put65$global;
							{
								for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
									if((i$var18 == i$var36)) {
										if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
											for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
												// Set the flags to false
												guard$sample24put65[((i$var61 - 0) / 1)] = false;
										}
									}
								}
							}
							{
								for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
									if((i$var18 == i$var36)) {
										for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
											if((i$var36 == i$var61))
												// Set the flags to false
												guard$sample24put65[((i$var61 - 0) / 1)] = false;
										}
									}
								}
							}
							{
								for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
									if((i$var18 == i$var36)) {
										if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
											for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
												if(!guard$sample24put65[((i$var61 - 0) / 1)]) {
													// The body will execute, so should not be executed again
													guard$sample24put65[((i$var61 - 0) / 1)] = true;
													{
														state.prob[i$var61] = (state.exped[i$var61] / state.sum);
													}
												}
											}
										}
									}
								}
							}
							{
								for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
									if((i$var18 == i$var36)) {
										for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
											if((i$var36 == i$var61)) {
												if(!guard$sample24put65[((i$var61 - 0) / 1)]) {
													// The body will execute, so should not be executed again
													guard$sample24put65[((i$var61 - 0) / 1)] = true;
													{
														state.prob[i$var61] = (state.exped[i$var61] / state.sum);
													}
												}
											}
										}
									}
								}
							}
						}
					}
					{
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						double cv$accumulatedProbabilities = (Math.log(1.0) + ((0.0 < 10.0)?(DistributionSampling.logProbabilityGaussian(((cv$currentValue - 0.0) / Math.sqrt(10.0))) - (0.5 * Math.log(10.0))):Double.NEGATIVE_INFINITY));
						
						// Processing random variable 65.
						{
							// Looking for a path between Sample 24 and consumer Categorical 65.
							{
								// Guard to check that at most one copy of the code is executed for a given set of
								// loop iterations.
								boolean guard$sample24categorical66 = false;
								{
									double traceTempVariable$var37$8_1 = cv$currentValue;
									for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
										if((i$var18 == i$var36)) {
											double traceTempVariable$i$8_3 = Math.exp(traceTempVariable$var37$8_1);
											if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
												if((0 < state.noProducts)) {
													// Reduction of array exped
													// 
													// A generated name to prevent name collisions if the reduction is implemented more
													// than once in inference and probability code. Initialize the variable to the unit
													// value
													double reduceVar$sum$11 = 0.0;
													
													// Reduce for every value except a masked value which will be skipped.
													for(int cv$reduction758Index = 0; cv$reduction758Index < i$var36; cv$reduction758Index += 1) {
														// Set the left hand term of the reduction function to the return variable value.
														double i$var47 = reduceVar$sum$11;
														
														// Set the right hand term to a value from the array exped
														double j = state.exped[cv$reduction758Index];
														
														// Execute the reduction function, saving the result into the return value.
														// 
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$sum$11 = (i$var47 + j);
													}
													for(int cv$reduction758Index = (i$var36 + 1); cv$reduction758Index < state.noProducts; cv$reduction758Index += 1) {
														// Set the left hand term of the reduction function to the return variable value.
														double i$var47 = reduceVar$sum$11;
														
														// Set the right hand term to a value from the array exped
														double j = state.exped[cv$reduction758Index];
														
														// Execute the reduction function, saving the result into the return value.
														// 
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$sum$11 = (i$var47 + j);
													}
													double cv$reduced44 = reduceVar$sum$11;
													
													// Copy the result of the reduction into the variable returned by the reduction.
													reduceVar$sum$11 = (traceTempVariable$i$8_3 + cv$reduced44);
													double traceTempVariable$sum$8_4 = reduceVar$sum$11;
													double traceTempVariable$sum$8_5 = traceTempVariable$sum$8_4;
													if(!guard$sample24categorical66) {
														// The body will execute, so should not be executed again
														guard$sample24categorical66 = true;
														
														// Processing sample task 78 of consumer random variable null.
														{
															{
																for(int var76 = 0; var76 < state.noObs; var76 += 1) {
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		state.constrainedFlag$sample24[((i$var18 - 1) / 1)] = true;
																		
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
																							// Record the probability of sample task 78 generating output with current configuration.
																							if(((Math.log(1.0) + ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY)));
																							}
																							
																							// Recorded the probability of reaching sample task 78 with the current configuration.
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
										}
									}
								}
								{
									double traceTempVariable$var37$9_1 = cv$currentValue;
									for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
										if((i$var18 == i$var36)) {
											double traceTempVariable$var62$9_3 = Math.exp(traceTempVariable$var37$9_1);
											for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
												if((i$var36 == i$var61)) {
													if(!guard$sample24categorical66) {
														// The body will execute, so should not be executed again
														guard$sample24categorical66 = true;
														
														// Processing sample task 78 of consumer random variable null.
														{
															{
																for(int var76 = 0; var76 < state.noObs; var76 += 1) {
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		state.constrainedFlag$sample24[((i$var18 - 1) / 1)] = true;
																		
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
																							// Record the probability of sample task 78 generating output with current configuration.
																							if(((Math.log(1.0) + ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY)));
																							}
																							
																							// Recorded the probability of reaching sample task 78 with the current configuration.
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
					
					// The probability ration for the proposed value and the current value.
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					
					// Test if the probability of the sample is sufficient to keep the value. This needs
					// to be less than or equal as otherwise if the proposed value is not possible and
					// the random value is 0 an impossible value will be accepted.
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$))))) || Double.isNaN(cv$ratio))) {
							// If it is not revert the changes.
							// 
							// Set the sample value
							// Write out the value of the sample to a temporary variable prior to updating the
							// intermediate variables.
							double var24 = cv$originalValue;
							
							// Guards to ensure that ut is only updated when there is a valid path.
							{
								{
									{
										state.ut[i$var18] = var24;
									}
								}
							}
							
							// Guards to ensure that exped is only updated when there is a valid path.
							// 
							// Looking for a path between Sample 24 and consumer double[] 39.
							{
								{
									for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
										if((i$var18 == i$var36)) {
											{
												state.exped[i$var36] = Math.exp(state.ut[i$var36]);
											}
										}
									}
								}
							}
							
							// Guards to ensure that sum is only updated when there is a valid path.
							// 
							// Looking for a path between Sample 24 and consumer double 50.
							{
								{
									for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
										if((i$var18 == i$var36)) {
											if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
												{
													// Reduction of array exped
													// 
													// A generated name to prevent name collisions if the reduction is implemented more
													// than once in inference and probability code. Initialize the variable to the unit
													// value
													double reduceVar$sum$12 = 0.0;
													
													// For each index in the array to be reduced
													for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1) {
														// Set the left hand term of the reduction function to the return variable value.
														double i$var47 = reduceVar$sum$12;
														
														// Set the right hand term to a value from the array exped
														double j = state.exped[cv$reduction44Index];
														
														// Execute the reduction function, saving the result into the return value.
														// 
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$sum$12 = (i$var47 + j);
													}
													
													// Write out the new sample value.
													state.sum = reduceVar$sum$12;
												}
											}
										}
									}
								}
							}
							
							// Guards to ensure that prob is only updated when there is a valid path.
							// 
							// Looking for a path between Sample 24 and consumer double[] 64.
							{
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								boolean[] guard$sample24put65 = scratch.guard$sample24put65$global;
								{
									for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
										if((i$var18 == i$var36)) {
											if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
												for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
													// Set the flags to false
													guard$sample24put65[((i$var61 - 0) / 1)] = false;
											}
										}
									}
								}
								{
									for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
										if((i$var18 == i$var36)) {
											for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
												if((i$var36 == i$var61))
													// Set the flags to false
													guard$sample24put65[((i$var61 - 0) / 1)] = false;
											}
										}
									}
								}
								{
									for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
										if((i$var18 == i$var36)) {
											if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
												for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
													if(!guard$sample24put65[((i$var61 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample24put65[((i$var61 - 0) / 1)] = true;
														{
															state.prob[i$var61] = (state.exped[i$var61] / state.sum);
														}
													}
												}
											}
										}
									}
								}
								{
									for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
										if((i$var18 == i$var36)) {
											for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
												if((i$var36 == i$var61)) {
													if(!guard$sample24put65[((i$var61 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample24put65[((i$var61 - 0) / 1)] = true;
														{
															state.prob[i$var61] = (state.exped[i$var61] / state.sum);
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
	}

	// Calculate the probability of the samples represented by sample24 using sampled
	// values.
	private final void logProbabilityValue$sample24() {
		// Determine if we need to calculate the values for sample task 24 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample24) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = state.ut[i$var18];
						{
							{
								double var21 = 0.0;
								double var22 = 10.0;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var22)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var21) / Math.sqrt(var22))) - (0.5 * Math.log(var22))):Double.NEGATIVE_INFINITY));
								
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
				
				// Only update the sample if it was reached, otherwise the NaN will be
				// erroneously over written.
				if(cv$sampleReached)
					// Store the sample task probability
					state.logProbability$sample24[((i$var18 - 1) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that exped is only updated once for this probability.
				boolean cv$guard$exped = false;
				
				// Guard to ensure that sum is only updated once for this probability.
				boolean cv$guard$sum = false;
				
				// Guard to ensure that prob is only updated once for this probability.
				boolean cv$guard$prob = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 24 and consumer double[] 39.
				{
					{
						for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$exped) {
									// Set the guard so the update is only applied once.
									cv$guard$exped = true;
									
									// Update the variable probability
									state.logProbability$exped = (state.logProbability$exped + cv$sampleProbability);
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 24 and consumer double 50.
				{
					{
						for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
									// If the probability of the variable has not already been updated
									if(!cv$guard$sum) {
										// Set the guard so the update is only applied once.
										cv$guard$sum = true;
										
										// Update the variable probability
										state.logProbability$sum = (state.logProbability$sum + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 24 and consumer double[] 64.
				{
					{
						for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
									for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$prob) {
											// Set the guard so the update is only applied once.
											cv$guard$prob = true;
											
											// Update the variable probability
											state.logProbability$prob = (state.logProbability$prob + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
					{
						for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
									if((i$var36 == i$var61)) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$prob) {
											// Set the guard so the update is only applied once.
											cv$guard$prob = true;
											
											// Update the variable probability
											state.logProbability$prob = (state.logProbability$prob + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			
			// Update the variable probability
			state.logProbability$ut = (state.logProbability$ut + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample24)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample24 = state.fixedFlag$sample24;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1) {
				double cv$sampleValue = state.logProbability$sample24[((i$var18 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Guard to ensure that exped is only updated once for this probability.
				boolean cv$guard$exped = false;
				
				// Guard to ensure that sum is only updated once for this probability.
				boolean cv$guard$sum = false;
				
				// Guard to ensure that prob is only updated once for this probability.
				boolean cv$guard$prob = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 24 and consumer double[] 39.
				{
					{
						for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$exped) {
									// Set the guard so the update is only applied once.
									cv$guard$exped = true;
									
									// Update the variable probability
									state.logProbability$exped = (state.logProbability$exped + cv$sampleValue);
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 24 and consumer double 50.
				{
					{
						for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
									// If the probability of the variable has not already been updated
									if(!cv$guard$sum) {
										// Set the guard so the update is only applied once.
										cv$guard$sum = true;
										
										// Update the variable probability
										state.logProbability$sum = (state.logProbability$sum + cv$sampleValue);
									}
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 24 and consumer double[] 64.
				{
					{
						for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								if(((0 <= i$var36) && (i$var36 < state.noProducts))) {
									for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$prob) {
											// Set the guard so the update is only applied once.
											cv$guard$prob = true;
											
											// Update the variable probability
											state.logProbability$prob = (state.logProbability$prob + cv$sampleValue);
										}
									}
								}
							}
						}
					}
					{
						for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
									if((i$var36 == i$var61)) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$prob) {
											// Set the guard so the update is only applied once.
											cv$guard$prob = true;
											
											// Update the variable probability
											state.logProbability$prob = (state.logProbability$prob + cv$sampleValue);
										}
									}
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$ut = (state.logProbability$ut + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample24)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
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
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var76 = 0; var76 < state.noObs; var76 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = state.choices[var76];
						{
							{
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[cv$sampleValue])) && (state.prob[cv$sampleValue] <= 1.0))?Math.log(state.prob[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
				state.logProbability$var77 = cv$sampleAccumulator;
			
			// Update the variable probability
			state.logProbability$choices = (state.logProbability$choices + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample78 = state.fixedFlag$sample24;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var76 = 0; var76 < state.noObs; var76 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var77;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$choices = (state.logProbability$choices + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 1, state.noProducts, 1,
			(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1) {
						if(!state.fixedFlag$sample24)
							state.ut[i$var18] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1) {
						if(!state.fixedFlag$sample24)
							state.exped[i$var36] = Math.exp(state.ut[i$var36]);
					}
			}
		);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$14 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double i$var47 = reduceVar$sum$14;
			
			// Set the right hand term to a value from the array exped
			double j = state.exped[cv$reduction44Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!state.fixedFlag$sample24)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$14 = (i$var47 + j);
		}
		if(!state.fixedFlag$sample24)
			state.sum = reduceVar$sum$14;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1) {
						if(!state.fixedFlag$sample24)
							state.prob[i$var61] = (state.exped[i$var61] / state.sum);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$var76, int forEnd$var76, int threadID$var76, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var76 = forStart$var76; var76 < forEnd$var76; var76 += 1)
						state.choices[var76] = DistributionSampling.sampleCategorical(RNG$1, state.prob, state.noProducts);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 1, state.noProducts, 1,
			(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1) {
						if(!state.fixedFlag$sample24)
							state.ut[i$var18] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1)
						state.exped[i$var36] = Math.exp(state.ut[i$var36]);
			}
		);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$18 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double i$var47 = reduceVar$sum$18;
			
			// Set the right hand term to a value from the array exped
			double j = state.exped[cv$reduction44Index];
			
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			reduceVar$sum$18 = (i$var47 + j);
		}
		state.sum = reduceVar$sum$18;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1)
						state.prob[i$var61] = (state.exped[i$var61] / state.sum);
			}
		);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 1, state.noProducts, 1,
			(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1) {
						if(!state.fixedFlag$sample24)
							state.ut[i$var18] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1)
						state.exped[i$var36] = Math.exp(state.ut[i$var36]);
			}
		);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$15 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double i$var47 = reduceVar$sum$15;
			
			// Set the right hand term to a value from the array exped
			double j = state.exped[cv$reduction44Index];
			
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			reduceVar$sum$15 = (i$var47 + j);
		}
		state.sum = reduceVar$sum$15;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1)
						state.prob[i$var61] = (state.exped[i$var61] / state.sum);
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$var76, int forEnd$var76, int threadID$var76, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var76 = forStart$var76; var76 < forEnd$var76; var76 += 1)
						state.choices[var76] = DistributionSampling.sampleCategorical(RNG$1, state.prob, state.noProducts);
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 1, state.noProducts, 1,
			(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1) {
						if(!state.fixedFlag$sample24)
							state.ut[i$var18] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1) {
						if(!state.fixedFlag$sample24)
							state.exped[i$var36] = Math.exp(state.ut[i$var36]);
					}
			}
		);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$16 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double i$var47 = reduceVar$sum$16;
			
			// Set the right hand term to a value from the array exped
			double j = state.exped[cv$reduction44Index];
			
			// Execute the reduction function, saving the result into the return value.
			if(!state.fixedFlag$sample24)
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$sum$16 = (i$var47 + j);
		}
		if(!state.fixedFlag$sample24)
			state.sum = reduceVar$sum$16;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1) {
						if(!state.fixedFlag$sample24)
							state.prob[i$var61] = (state.exped[i$var61] / state.sum);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 1, state.noProducts, 1,
			(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1) {
						if(!state.fixedFlag$sample24)
							state.ut[i$var18] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1)
						state.exped[i$var36] = Math.exp(state.ut[i$var36]);
			}
		);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$17 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double i$var47 = reduceVar$sum$17;
			
			// Set the right hand term to a value from the array exped
			double j = state.exped[cv$reduction44Index];
			
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			reduceVar$sum$17 = (i$var47 + j);
		}
		state.sum = reduceVar$sum$17;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1)
						state.prob[i$var61] = (state.exped[i$var61] / state.sum);
			}
		);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1) {
				if(!state.fixedFlag$sample24)
					inferSample24(i$var18);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i$var18 = (state.noProducts - ((((state.noProducts - 1) - 1) % 1) + 1)); i$var18 >= ((1 - 1) + 1); i$var18 -= 1) {
				if(!state.fixedFlag$sample24)
					inferSample24(i$var18);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1) {
			if(!state.constrainedFlag$sample24[((i$var18 - 1) / 1)])
				drawValueSample24(i$var18);
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
		state.logProbability$ut = 0.0;
		state.logProbability$exped = 0.0;
		state.logProbability$sum = 0.0;
		state.logProbability$prob = 0.0;
		if(!state.fixedProbFlag$sample24) {
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1)
				state.logProbability$sample24[((i$var18 - 1) / 1)] = Double.NaN;
		}
		state.logProbability$choices = 0.0;
		if(!state.fixedProbFlag$sample78)
			state.logProbability$var77 = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.ut[0] = 0.0;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample24$1 = 0; index$constrainedFlag$sample24$1 < state.constrainedFlag$sample24.length; index$constrainedFlag$sample24$1 += 1)
			state.constrainedFlag$sample24[index$constrainedFlag$sample24$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample24)
			logProbabilityValue$sample24();
		logProbabilityValue$sample78();
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
		logProbabilityValue$sample24();
		logProbabilityValue$sample78();
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
		logProbabilityValue$sample24();
		logProbabilityValue$sample78();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Deep copy between arrays
		int[] cv$source1 = state.ObsChoices;
		int[] cv$target1 = state.choices;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1)
						state.exped[i$var36] = Math.exp(state.ut[i$var36]);
			}
		);
		
		// Reduction of array exped
		// 
		// A generated name to prevent name collisions if the reduction is implemented more
		// than once in inference and probability code. Initialize the variable to the unit
		// value
		double reduceVar$sum$19 = 0.0;
		
		// For each index in the array to be reduced
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1) {
			// Set the left hand term of the reduction function to the return variable value.
			double i$var47 = reduceVar$sum$19;
			
			// Set the right hand term to a value from the array exped
			double j = state.exped[cv$reduction44Index];
			
			// Execute the reduction function, saving the result into the return value.
			// 
			// Copy the result of the reduction into the variable returned by the reduction.
			reduceVar$sum$19 = (i$var47 + j);
		}
		state.sum = reduceVar$sum$19;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1)
						state.prob[i$var61] = (state.exped[i$var61] / state.sum);
			}
		);
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
		     + "public model DiscreteChoice(int noProducts, int noObs, int[] ObsChoices) {\n"
		     + "    // we just need an uninformative prior for utility intercepts\n"
		     + "\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = new double[noProducts];\n"
		     + "    ut[0] = 0.0;\n"
		     + "    for(int i=1; i<noProducts; i++) {\n"
		     + "        ut[i]= gaussian(0, 10).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    // calculate choice probabilities\n"
		     + "    double[] exped = new double[noProducts];\n"
		     + "    for(int i : [0..noProducts)) {\n"
		     + "        exped[i] = exp(ut[i]);\n"
		     + "    }\n"
		     + "    double sum = reduce(exped, 0, (i, j) -> { return i + j; });\n"
		     + "    double[] prob = new double[noProducts];\n"
		     + "    for (int i : [0..noProducts)) {\n"
		     + "        prob[i] = exped[i] / sum;\n"
		     + "    }\n"
		     + "    // draw consumer choices according to the calculated probabilities\n"
		     + "    int[] choices = categorical(prob).sample(noObs);\n"
		     + "\n"
		     + "    // assert generated choices match observed choices\n"
		     + "    choices.observe(ObsChoices);\n"
		     + "}";
	}
}