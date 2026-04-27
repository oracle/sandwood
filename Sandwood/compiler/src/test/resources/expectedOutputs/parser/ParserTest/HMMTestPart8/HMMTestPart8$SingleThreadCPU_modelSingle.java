package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMTestPart8$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMTestPart8.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMTestPart8$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$distributionAccumulator$var69;
		double[] cv$var28$countGlobal;
		double[] cv$var52$stateProbabilityGlobal;
		double[] cv$var70$stateProbabilityGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for cv$var28$countGlobal
			{
				// Allocation of cv$var28$countGlobal for single threaded execution
				cv$var28$countGlobal = new double[5];
			}
			
			// Constructor for cv$distributionAccumulator$var69
			{
				// Variable to record the maximum value of Task Get 69. Initially set to the value
				// of putTask 29.
				int cv$var29$max = 5;
				
				// Allocation of cv$distributionAccumulator$var69 for single threaded execution
				cv$distributionAccumulator$var69 = new double[cv$var29$max];
			}
			
			// Constructor for cv$var52$stateProbabilityGlobal
			{
				// Variable to record the maximum value of Task Get 51. Initially set to the value
				// of putTask 29.
				int cv$var29$max = 5;
				
				// Allocation of cv$var52$stateProbabilityGlobal for single threaded execution
				cv$var52$stateProbabilityGlobal = new double[cv$var29$max];
			}
			
			// Constructor for cv$var70$stateProbabilityGlobal
			{
				// Variable to record the maximum value of Task Get 69. Initially set to the value
				// of putTask 29.
				int cv$var29$max = 5;
				
				// Allocation of cv$var70$stateProbabilityGlobal for single threaded execution
				cv$var70$stateProbabilityGlobal = new double[cv$var29$max];
			}
		}
	}


	public HMMTestPart8$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample28
	private final void drawValueSample28(int var27) {
		double[] var28 = state.m[var27];
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.states, var28);
	}

	// Pick a value from the distribution for the unconditioned variable from sample45
	private final void drawValueSample45(int var43) {
		state.bias[var43] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample53
	private final void drawValueSample53() {
		state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
	}

	// Pick a value from the distribution for the unconditioned variable from sample71
	private final void drawValueSample71(int i$var64) {
		// Copy of index so that its values can be safely substituted
		int index$i$1 = i$var64;
		state.st[i$var64] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var64 - 1)]], state.states);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 28 drawn from Dirichlet 16. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample28(int var27) {
		if(true) {
			state.constrainedFlag$sample28[((var27 - 0) / 1)] = false;
			
			// A reference local to the function for the sample variable.
			double[] cv$targetLocal = state.m[var27];
			
			// A local reference to the scratch space.
			double[] cv$countLocal = scratch.cv$var28$countGlobal;
			
			// Get the length of the array
			int cv$arrayLength = state.states;
			
			// Initialize the array values to 0.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				// Processing random variable 51.
				{
					// Looking for a path between Sample 28 and consumer Categorical 51.
					{
						{
							if((var27 == 0)) {
								// Processing sample task 53 of consumer random variable null.
								{
									{
										// Flag recording if this sample task of the consuming random variable is constrained.
										boolean cv$sampleConstrained = (state.fixedFlag$sample53 || state.constrainedFlag$sample53);
										if(cv$sampleConstrained) {
											// Mark that the sample has observed constrained data.
											state.constrainedFlag$sample28[((var27 - 0) / 1)] = true;
											{
												{
													{
														{
															{
																// Increment the sample counter with the value sampled by sample task 53 of random
																// variable var51
																cv$countLocal[state.st[0]] = (cv$countLocal[state.st[0]] + 1.0);
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
				
				// Processing random variable 69.
				{
					// Looking for a path between Sample 28 and consumer Categorical 69.
					{
						for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
							if((0 == (i$var64 - 1))) {
								{
									if((var27 == state.st[(i$var64 - 1)])) {
										if(state.fixedFlag$sample71) {
											// Processing sample task 71 of consumer random variable null.
											{
												{
													// Copy of index so that its values can be safely substituted
													int index$i$17 = i$var64;
													
													// Flag recording if this sample task of the consuming random variable is constrained.
													boolean cv$sampleConstrained = (state.fixedFlag$sample71 || state.constrainedFlag$sample71[((i$var64 - 1) / 1)]);
													if(cv$sampleConstrained) {
														// Mark that the sample has observed constrained data.
														state.constrainedFlag$sample28[((var27 - 0) / 1)] = true;
														{
															{
																{
																	{
																		{
																			// Increment the sample counter with the value sampled by sample task 71 of random
																			// variable var69
																			cv$countLocal[state.st[i$var64]] = (cv$countLocal[state.st[i$var64]] + 1.0);
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
						for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
							if(state.fixedFlag$sample71) {
								{
									for(int index$i$8_1 = 1; index$i$8_1 < state.samples; index$i$8_1 += 1) {
										if((index$i$8_1 == (i$var64 - 1))) {
											{
												if((var27 == state.st[(i$var64 - 1)])) {
													if(state.fixedFlag$sample71) {
														// Processing sample task 71 of consumer random variable null.
														{
															{
																// Copy of index so that its values can be safely substituted
																int index$i$19 = i$var64;
																
																// Flag recording if this sample task of the consuming random variable is constrained.
																boolean cv$sampleConstrained = (state.fixedFlag$sample71 || state.constrainedFlag$sample71[((i$var64 - 1) / 1)]);
																if(cv$sampleConstrained) {
																	// Mark that the sample has observed constrained data.
																	state.constrainedFlag$sample28[((var27 - 0) / 1)] = true;
																	{
																		{
																			{
																				{
																					{
																						// Increment the sample counter with the value sampled by sample task 71 of random
																						// variable var69
																						cv$countLocal[state.st[i$var64]] = (cv$countLocal[state.st[i$var64]] + 1.0);
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
							} else {
								for(int index$i$9 = 1; index$i$9 < state.samples; index$i$9 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 69.
										for(int index$sample71$10 = 0; index$sample71$10 < state.states; index$sample71$10 += 1) {
											int distributionTempVariable$var70$12 = index$sample71$10;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample71Value11 = (1.0 * state.distribution$sample71[((index$i$9 - 1) / 1)][index$sample71$10]);
											{
												int traceTempVariable$var67$13_1 = distributionTempVariable$var70$12;
												if((index$i$9 == (i$var64 - 1))) {
													{
														if((var27 == traceTempVariable$var67$13_1)) {
															if(state.fixedFlag$sample71) {
																// Processing sample task 71 of consumer random variable null.
																{
																	{
																		// Copy of index so that its values can be safely substituted
																		int index$i$21 = i$var64;
																		
																		// Flag recording if this sample task of the consuming random variable is constrained.
																		boolean cv$sampleConstrained = (state.fixedFlag$sample71 || state.constrainedFlag$sample71[((i$var64 - 1) / 1)]);
																		if(cv$sampleConstrained) {
																			// Mark that the sample has observed constrained data.
																			state.constrainedFlag$sample28[((var27 - 0) / 1)] = true;
																			{
																				{
																					{
																						{
																							{
																								// Increment the sample counter with the value sampled by sample task 71 of random
																								// variable var69
																								cv$countLocal[state.st[i$var64]] = (cv$countLocal[state.st[i$var64]] + cv$probabilitySample71Value11);
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
									}
								}
							}
						}
					}
				}
			}
			
			// Processing random variable 69.
			{
				// Looking for a path between Sample 28 and consumer Categorical 69.
				{
					for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
						if((0 == (i$var64 - 1))) {
							{
								if((var27 == state.st[(i$var64 - 1)])) {
									if(!state.fixedFlag$sample71) {
										// Processing sample task 71 of consumer random variable null.
										{
											{
												// Copy of index so that its values can be safely substituted
												int index$i$38 = i$var64;
												{
													{
														// Declare and zero an accumulator for tracking the reached source probability space.
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															// Add the probability of this argument configuration.
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														
														// The probability of reaching the consumer with this set of consumer arguments
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
														
														// Merge the distribution probabilities into the count
														for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
															cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample71[((i$var64 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
						if(state.fixedFlag$sample71) {
							{
								for(int index$i$29_1 = 1; index$i$29_1 < state.samples; index$i$29_1 += 1) {
									if((index$i$29_1 == (i$var64 - 1))) {
										{
											if((var27 == state.st[(i$var64 - 1)])) {
												if(!state.fixedFlag$sample71) {
													// Processing sample task 71 of consumer random variable null.
													{
														{
															// Copy of index so that its values can be safely substituted
															int index$i$40 = i$var64;
															{
																{
																	// Declare and zero an accumulator for tracking the reached source probability space.
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		// Add the probability of this argument configuration.
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																	
																	// The probability of reaching the consumer with this set of consumer arguments
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																	
																	// Merge the distribution probabilities into the count
																	for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample71[((i$var64 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
						} else {
							for(int index$i$30 = 1; index$i$30 < state.samples; index$i$30 += 1) {
								if(true) {
									// Enumerating the possible outputs of Categorical 69.
									for(int index$sample71$31 = 0; index$sample71$31 < state.states; index$sample71$31 += 1) {
										int distributionTempVariable$var70$33 = index$sample71$31;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample71Value32 = (1.0 * state.distribution$sample71[((index$i$30 - 1) / 1)][index$sample71$31]);
										{
											int traceTempVariable$var67$34_1 = distributionTempVariable$var70$33;
											if((index$i$30 == (i$var64 - 1))) {
												{
													if((var27 == traceTempVariable$var67$34_1)) {
														if(!state.fixedFlag$sample71) {
															// Processing sample task 71 of consumer random variable null.
															{
																{
																	// Copy of index so that its values can be safely substituted
																	int index$i$42 = i$var64;
																	{
																		{
																			// Declare and zero an accumulator for tracking the reached source probability space.
																			double scopeVariable$reachedSourceProbability = 0.0;
																			{
																				// Add the probability of this argument configuration.
																				scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																			}
																			
																			// The probability of reaching the consumer with this set of consumer arguments
																			double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample71Value32);
																			
																			// Merge the distribution probabilities into the count
																			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																				cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample71[((i$var64 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
				}
			}
			if(state.constrainedFlag$sample28[((var27 - 0) / 1)])
				// Calculate the new sample value
				// 
				// Calculate a new sample value and write it into cv$targetLocal.
				Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, cv$countLocal, cv$targetLocal, state.states);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 45 drawn from Beta 32. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void inferSample45(int var43) {
		if(true) {
			state.constrainedFlag$sample45[((var43 - 0) / 1)] = false;
			
			// Local variable to record the number of true samples.
			double cv$sum = 0.0;
			
			// Local variable to record the number of samples.
			double cv$count = 0.0;
			{
				// Processing random variable 85.
				{
					// Looking for a path between Sample 45 and consumer Bernoulli 85.
					{
						for(int j = 0; j < state.samples; j += 1) {
							if((0 == j)) {
								{
									if((var43 == state.st[j])) {
										// Processing sample task 87 of consumer random variable null.
										{
											{
												// Flag recording if this sample task of the consuming random variable is constrained.
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													// Mark that the sample has observed constrained data.
													state.constrainedFlag$sample45[((var43 - 0) / 1)] = true;
													{
														{
															{
																{
																	{
																		// Include the value sampled by task 87 from random variable var85.
																		// Increment the number of samples.
																		cv$count = (cv$count + 1.0);
																		
																		// If the sample value was positive increase the count
																		if(state.flips[j])
																			cv$sum = (cv$sum + 1.0);
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
						for(int j = 0; j < state.samples; j += 1) {
							if(state.fixedFlag$sample71) {
								{
									for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
										if((i$var64 == j)) {
											{
												if((var43 == state.st[j])) {
													// Processing sample task 87 of consumer random variable null.
													{
														{
															// Flag recording if this sample task of the consuming random variable is constrained.
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																// Mark that the sample has observed constrained data.
																state.constrainedFlag$sample45[((var43 - 0) / 1)] = true;
																{
																	{
																		{
																			{
																				{
																					// Include the value sampled by task 87 from random variable var85.
																					// Increment the number of samples.
																					cv$count = (cv$count + 1.0);
																					
																					// If the sample value was positive increase the count
																					if(state.flips[j])
																						cv$sum = (cv$sum + 1.0);
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
							} else {
								for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 69.
										for(int index$sample71$7 = 0; index$sample71$7 < state.states; index$sample71$7 += 1) {
											int distributionTempVariable$var70$9 = index$sample71$7;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample71Value8 = (1.0 * state.distribution$sample71[((i$var64 - 1) / 1)][index$sample71$7]);
											{
												int traceTempVariable$var83$10_1 = distributionTempVariable$var70$9;
												if((i$var64 == j)) {
													{
														if((var43 == traceTempVariable$var83$10_1)) {
															// Processing sample task 87 of consumer random variable null.
															{
																{
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		state.constrainedFlag$sample45[((var43 - 0) / 1)] = true;
																		{
																			{
																				{
																					{
																						{
																							// Include the value sampled by task 87 from random variable var85.
																							// Increment the number of samples.
																							cv$count = (cv$count + cv$probabilitySample71Value8);
																							
																							// If the sample value was positive increase the count
																							if(state.flips[j])
																								cv$sum = (cv$sum + cv$probabilitySample71Value8);
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
								}
							}
						}
					}
				}
			}
			if(state.constrainedFlag$sample45[((var43 - 0) / 1)]) {
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				double var44 = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
				
				// Guards to ensure that bias is only updated when there is a valid path.
				{
					{
						{
							state.bias[var43] = var44;
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 53 drawn from Categorical 51. Inference was performed using variable
	// marginalization.
	private final void inferSample53() {
		if(true) {
			state.constrainedFlag$sample53 = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, state.states);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = scratch.cv$var52$stateProbabilityGlobal;
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
				
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				int var52 = cv$currentValue;
				
				// Guards to ensure that st is only updated when there is a valid path.
				{
					{
						{
							state.st[0] = cv$currentValue;
						}
					}
				}
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// Constructing a random variable input for use later.
					double[] var50 = state.m[0];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.states)) && (0 < state.states)) && (0.0 <= var50[cv$currentValue])) && (var50[cv$currentValue] <= 1.0))?Math.log(var50[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 69.
					{
						// Looking for a path between Sample 53 and consumer Categorical 69.
						{
							{
								int traceTempVariable$var67$2_1 = cv$currentValue;
								for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
									if((0 == (i$var64 - 1))) {
										if(state.fixedFlag$sample71) {
											// Processing sample task 71 of consumer random variable null.
											{
												{
													// Copy of index so that its values can be safely substituted
													int index$i$4 = i$var64;
													
													// Flag recording if this sample task of the consuming random variable is constrained.
													boolean cv$sampleConstrained = (state.fixedFlag$sample71 || state.constrainedFlag$sample71[((i$var64 - 1) / 1)]);
													if(cv$sampleConstrained) {
														// Mark that the sample has observed constrained data.
														state.constrainedFlag$sample53 = true;
														
														// Set an accumulator to sum the probabilities for each possible configuration of
														// inputs.
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														
														// Set an accumulator to record the consumer distributions not seen. Initially set
														// to 1 as seen values will be deducted from this value.
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															// Enumerating the possible arguments for the variable Categorical 69 which is consuming
															// the output of Sample task 53.
															{
																for(int var27 = 0; var27 < state.states; var27 += 1) {
																	if((var27 == traceTempVariable$var67$2_1)) {
																		{
																			{
																				{
																					// Constructing a random variable input for use later.
																					double[] var68 = state.m[traceTempVariable$var67$2_1];
																					
																					// Record the probability of sample task 71 generating output with current configuration.
																					if(((Math.log(1.0) + ((((((0.0 <= state.st[i$var64]) && (state.st[i$var64] < state.states)) && (0 < state.states)) && (0.0 <= var68[state.st[i$var64]])) && (var68[state.st[i$var64]] <= 1.0))?Math.log(var68[state.st[i$var64]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.st[i$var64]) && (state.st[i$var64] < state.states)) && (0 < state.states)) && (0.0 <= var68[state.st[i$var64]])) && (var68[state.st[i$var64]] <= 1.0))?Math.log(var68[state.st[i$var64]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						// If the second value is -infinity.
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.st[i$var64]) && (state.st[i$var64] < state.states)) && (0 < state.states)) && (0.0 <= var68[state.st[i$var64]])) && (var68[state.st[i$var64]] <= 1.0))?Math.log(var68[state.st[i$var64]]):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.st[i$var64]) && (state.st[i$var64] < state.states)) && (0 < state.states)) && (0.0 <= var68[state.st[i$var64]])) && (var68[state.st[i$var64]] <= 1.0))?Math.log(var68[state.st[i$var64]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.st[i$var64]) && (state.st[i$var64] < state.states)) && (0 < state.states)) && (0.0 <= var68[state.st[i$var64]])) && (var68[state.st[i$var64]] <= 1.0))?Math.log(var68[state.st[i$var64]]):Double.NEGATIVE_INFINITY)));
																					}
																					
																					// Recorded the probability of reaching sample task 71 with the current configuration.
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
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
					
					// Processing random variable 85.
					{
						// Looking for a path between Sample 53 and consumer Bernoulli 85.
						{
							{
								int traceTempVariable$var83$7_1 = cv$currentValue;
								for(int j = 0; j < state.samples; j += 1) {
									if((0 == j)) {
										// Processing sample task 87 of consumer random variable null.
										{
											{
												// Flag recording if this sample task of the consuming random variable is constrained.
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													// Mark that the sample has observed constrained data.
													state.constrainedFlag$sample53 = true;
													
													// Set an accumulator to sum the probabilities for each possible configuration of
													// inputs.
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													
													// Set an accumulator to record the consumer distributions not seen. Initially set
													// to 1 as seen values will be deducted from this value.
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
														// the output of Sample task 53.
														{
															for(int var43 = 0; var43 < state.states; var43 += 1) {
																if((var43 == traceTempVariable$var83$7_1)) {
																	{
																		{
																			{
																				// Constructing a random variable input for use later.
																				double var84 = state.bias[traceTempVariable$var83$7_1];
																				
																				// Record the probability of sample task 87 generating output with current configuration.
																				if(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)));
																				}
																				
																				// Recorded the probability of reaching sample task 87 with the current configuration.
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
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
				
				// Processing random variable 69.
				{
					// Looking for a path between Sample 53 and consumer Categorical 69.
					{
						{
							int traceTempVariable$var67$11_1 = cv$currentValue;
							for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
								if((0 == (i$var64 - 1))) {
									if(!state.fixedFlag$sample71) {
										// Processing sample task 71 of consumer random variable null.
										{
											{
												// Copy of index so that its values can be safely substituted
												int index$i$13 = i$var64;
												
												// A local array to hold the accumulated distributions of the sample tasks for each
												// configuration of distributions.
												double[] cv$accumulatedConsumerDistributions = scratch.cv$distributionAccumulator$var69;
												
												// Zero all the elements in the distribution accumulator
												for(int cv$i = 0; cv$i < state.states; cv$i += 1)
													cv$accumulatedConsumerDistributions[cv$i] = 0.0;
												
												// Zero an accumulator to track the probabilities reached.
												double cv$reachedDistributionProbability = 0.0;
												
												// Enumerating the possible arguments for the variable Categorical 69 which is consuming
												// the output of Sample task 53.
												{
													for(int var27 = 0; var27 < state.states; var27 += 1) {
														if((var27 == traceTempVariable$var67$11_1)) {
															{
																// Declare and zero an accumulator for tracking the reached source probability space.
																double scopeVariable$reachedSourceProbability = 0.0;
																{
																	// Add the probability of this argument configuration.
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
																
																// Constructing a random variable input for use later.
																double[] var68 = state.m[traceTempVariable$var67$11_1];
																
																// The probability of reaching the consumer with this set of consumer arguments
																double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																
																// Record the reached distribution.
																cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
																
																// Add the current distribution to the distribution accumulator.
																DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, var68, state.states);
															}
														}
													}
												}
												
												// A local copy of the samples' distribution.
												double[] cv$sampleDistribution = state.distribution$sample71[((i$var64 - 1) / 1)];
												
												// The overlap of the distributions so far.
												double cv$overlap = 0.0;
												
												// Calculate the overlap for each element in the distribution
												for(int cv$i = 0; cv$i < state.states; cv$i += 1) {
													// Normalise the values in the calculated distribution
													double cv$normalisedDistValue = (cv$accumulatedConsumerDistributions[cv$i] / cv$reachedDistributionProbability);
													
													// Corresponding value from the sample distribution
													double cv$sampleDistValue = cv$sampleDistribution[cv$i];
													
													// Calculate the overlap and store the result
													if((cv$sampleDistValue < cv$normalisedDistValue))
														cv$overlap = (cv$overlap + cv$sampleDistValue);
													
													// Calculate the overlap and store the result
													else
														cv$overlap = (cv$overlap + cv$normalisedDistValue);
												}
												
												// Scale and add the result to the combined results so far. A min is taken over the
												// reached distributions so that rounding cannot result in a value greater than one
												// as for a small probability this could give a negative value
												cv$accumulatedDistributionProbabilities = (cv$accumulatedDistributionProbabilities + Math.log(((cv$overlap * cv$reachedDistributionProbability) + (1.0 - Math.min(cv$reachedDistributionProbability, 1.0)))));
											}
										}
									}
								}
							}
						}
					}
				}
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample53) {
				// The sum of all the probabilities in log space
				double cv$logSum = 0.0;
				
				// Sum all the values
				{
					// Initialise the max to the first element.
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
						// Initialise the sum of the array elements
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
				
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				int var52 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
				
				// Guards to ensure that st is only updated when there is a valid path.
				{
					{
						{
							state.st[0] = var52;
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 71 drawn from Categorical 69. Inference was performed using variable
	// marginalization.
	private final void inferSample71(int i$var64) {
		// Copy of index so that its values can be safely substituted
		int index$i$1 = i$var64;
		if(true) {
			state.constrainedFlag$sample71[((i$var64 - 1) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			
			// Exploring all the possible state counts for random variable 69.
			// 
			// Enumerating the possible arguments for Categorical 69.
			{
				if((0 == (i$var64 - 1))) {
					{
						for(int var27 = 0; var27 < state.states; var27 += 1) {
							if((var27 == state.st[(i$var64 - 1)]))
								// variable marginalization
								cv$numStates = Math.max(cv$numStates, state.states);
						}
					}
				}
			}
			
			// Enumerating the possible arguments for Categorical 69.
			{
				if((index$i$1 == (i$var64 - 1))) {
					{
						for(int var27 = 0; var27 < state.states; var27 += 1) {
							if((var27 == state.st[(i$var64 - 1)]))
								// variable marginalization
								cv$numStates = Math.max(cv$numStates, state.states);
						}
					}
				}
			}
			for(int index$i$5 = 1; index$i$5 < state.samples; index$i$5 += 1) {
				if(!(index$i$5 == index$i$1)) {
					// Enumerating the possible outputs of Categorical 69.
					for(int index$sample71$6 = 0; index$sample71$6 < state.states; index$sample71$6 += 1) {
						int distributionTempVariable$var70$8 = index$sample71$6;
						
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample71Value7 = (1.0 * state.distribution$sample71[((index$i$5 - 1) / 1)][index$sample71$6]);
						{
							int traceTempVariable$var67$9_1 = distributionTempVariable$var70$8;
							if((index$i$5 == (i$var64 - 1))) {
								{
									for(int var27 = 0; var27 < state.states; var27 += 1) {
										if((var27 == traceTempVariable$var67$9_1))
											// variable marginalization
											cv$numStates = Math.max(cv$numStates, state.states);
									}
								}
							}
						}
					}
				}
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = scratch.cv$var70$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				// Exploring all the possible distribution values for random variable 69 creating
				// sample task 71.
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
				
				// Enumerating the possible arguments for Categorical 69.
				{
					if((0 == (i$var64 - 1))) {
						{
							for(int var27 = 0; var27 < state.states; var27 += 1) {
								if((var27 == state.st[(i$var64 - 1)])) {
									// Record the reached probability density.
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
									
									// Constructing a random variable input for use later.
									double[] var68 = state.m[state.st[(i$var64 - 1)]];
									
									// An accumulator to allow the value for each distribution to be constructed before
									// it is added to the index probabilities.
									double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.states)) && (0 < state.states)) && (0.0 <= var68[cv$currentValue])) && (var68[cv$currentValue] <= 1.0))?Math.log(var68[cv$currentValue]):Double.NEGATIVE_INFINITY));
									
									// Processing random variable 69.
									{
										// Looking for a path between Sample 71 and consumer Categorical 69.
										{
											{
												int traceTempVariable$var67$22_1 = cv$currentValue;
											}
										}
									}
									
									// Processing random variable 85.
									{
										// Looking for a path between Sample 71 and consumer Bernoulli 85.
										{
											{
												int traceTempVariable$var83$25_1 = cv$currentValue;
												for(int j = 0; j < state.samples; j += 1) {
													if((i$var64 == j)) {
														// Processing sample task 87 of consumer random variable null.
														{
															{
																// Flag recording if this sample task of the consuming random variable is constrained.
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	// Mark that the sample has observed constrained data.
																	state.constrainedFlag$sample71[((i$var64 - 1) / 1)] = true;
																	
																	// Set an accumulator to sum the probabilities for each possible configuration of
																	// inputs.
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	
																	// Set an accumulator to record the consumer distributions not seen. Initially set
																	// to 1 as seen values will be deducted from this value.
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
																		// the output of Sample task 71.
																		{
																			for(int var43 = 0; var43 < state.states; var43 += 1) {
																				if((var43 == traceTempVariable$var83$25_1)) {
																					{
																						{
																							{
																								// Constructing a random variable input for use later.
																								double var84 = state.bias[traceTempVariable$var83$25_1];
																								
																								// Record the probability of sample task 87 generating output with current configuration.
																								if(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 87 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
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
							}
						}
					}
				}
				
				// Enumerating the possible arguments for Categorical 69.
				{
					int traceTempVariable$var67$14_1 = cv$currentValue;
					if((index$i$1 == (i$var64 - 1))) {
						{
							for(int var27 = 0; var27 < state.states; var27 += 1) {
								if((var27 == traceTempVariable$var67$14_1)) {
									// Record the reached probability density.
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
									
									// Constructing a random variable input for use later.
									double[] var68 = state.m[traceTempVariable$var67$14_1];
									
									// An accumulator to allow the value for each distribution to be constructed before
									// it is added to the index probabilities.
									double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.states)) && (0 < state.states)) && (0.0 <= var68[cv$currentValue])) && (var68[cv$currentValue] <= 1.0))?Math.log(var68[cv$currentValue]):Double.NEGATIVE_INFINITY));
									
									// Processing random variable 69.
									{
										// Looking for a path between Sample 71 and consumer Categorical 69.
										{
											{
												int traceTempVariable$var67$23_1 = cv$currentValue;
											}
										}
									}
									
									// Processing random variable 85.
									{
										// Looking for a path between Sample 71 and consumer Bernoulli 85.
										{
											{
												int traceTempVariable$var83$26_1 = cv$currentValue;
												for(int j = 0; j < state.samples; j += 1) {
													if((i$var64 == j)) {
														// Processing sample task 87 of consumer random variable null.
														{
															{
																// Flag recording if this sample task of the consuming random variable is constrained.
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	// Mark that the sample has observed constrained data.
																	state.constrainedFlag$sample71[((i$var64 - 1) / 1)] = true;
																	
																	// Set an accumulator to sum the probabilities for each possible configuration of
																	// inputs.
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	
																	// Set an accumulator to record the consumer distributions not seen. Initially set
																	// to 1 as seen values will be deducted from this value.
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
																		// the output of Sample task 71.
																		{
																			for(int var43 = 0; var43 < state.states; var43 += 1) {
																				if((var43 == traceTempVariable$var83$26_1)) {
																					{
																						{
																							{
																								// Constructing a random variable input for use later.
																								double var84 = state.bias[traceTempVariable$var83$26_1];
																								
																								// Record the probability of sample task 87 generating output with current configuration.
																								if(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)));
																								}
																								
																								// Recorded the probability of reaching sample task 87 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
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
							}
						}
					}
				}
				for(int index$i$15 = 1; index$i$15 < state.samples; index$i$15 += 1) {
					if(!(index$i$15 == index$i$1)) {
						// Enumerating the possible outputs of Categorical 69.
						for(int index$sample71$16 = 0; index$sample71$16 < state.states; index$sample71$16 += 1) {
							int distributionTempVariable$var70$18 = index$sample71$16;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample71Value17 = (1.0 * state.distribution$sample71[((index$i$15 - 1) / 1)][index$sample71$16]);
							{
								int traceTempVariable$var67$19_1 = distributionTempVariable$var70$18;
								if((index$i$15 == (i$var64 - 1))) {
									{
										for(int var27 = 0; var27 < state.states; var27 += 1) {
											if((var27 == traceTempVariable$var67$19_1)) {
												// Record the reached probability density.
												cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample71Value17);
												
												// Constructing a random variable input for use later.
												double[] var68 = state.m[traceTempVariable$var67$19_1];
												
												// An accumulator to allow the value for each distribution to be constructed before
												// it is added to the index probabilities.
												double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample71Value17) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.states)) && (0 < state.states)) && (0.0 <= var68[cv$currentValue])) && (var68[cv$currentValue] <= 1.0))?Math.log(var68[cv$currentValue]):Double.NEGATIVE_INFINITY));
												
												// Processing random variable 69.
												{
													// Looking for a path between Sample 71 and consumer Categorical 69.
													{
														{
															int traceTempVariable$var67$24_1 = distributionTempVariable$var70$18;
														}
													}
												}
												
												// Processing random variable 85.
												{
													// Looking for a path between Sample 71 and consumer Bernoulli 85.
													{
														{
															int traceTempVariable$var83$27_1 = distributionTempVariable$var70$18;
															for(int j = 0; j < state.samples; j += 1) {
																if((i$var64 == j)) {
																	// Processing sample task 87 of consumer random variable null.
																	{
																		{
																			// Flag recording if this sample task of the consuming random variable is constrained.
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				// Mark that the sample has observed constrained data.
																				state.constrainedFlag$sample71[((i$var64 - 1) / 1)] = true;
																				
																				// Set an accumulator to sum the probabilities for each possible configuration of
																				// inputs.
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				
																				// Set an accumulator to record the consumer distributions not seen. Initially set
																				// to 1 as seen values will be deducted from this value.
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
																					// the output of Sample task 71.
																					{
																						for(int var43 = 0; var43 < state.states; var43 += 1) {
																							if((var43 == traceTempVariable$var83$27_1)) {
																								{
																									{
																										{
																											// Constructing a random variable input for use later.
																											double var84 = state.bias[traceTempVariable$var83$27_1];
																											
																											// Record the probability of sample task 87 generating output with current configuration.
																											if(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												// If the second value is -infinity.
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)));
																											}
																											
																											// Recorded the probability of reaching sample task 87 with the current configuration.
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																										}
																									}
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
										}
									}
								}
							}
						}
					}
				}
				
				// Processing random variable 69.
				{
					// Looking for a path between Sample 71 and consumer Categorical 69.
					{
						{
							int traceTempVariable$var67$37_1 = cv$currentValue;
							for(int index$i$37_2 = 1; index$i$37_2 < state.samples; index$i$37_2 += 1) {
								if((i$var64 == (index$i$37_2 - 1))) {
									// Processing sample task 71 of consumer random variable null.
									{
										{
											// Copy of index so that its values can be safely substituted
											int index$i$39 = index$i$37_2;
											
											// A local array to hold the accumulated distributions of the sample tasks for each
											// configuration of distributions.
											double[] cv$accumulatedConsumerDistributions = scratch.cv$distributionAccumulator$var69;
											
											// Zero all the elements in the distribution accumulator
											for(int cv$i = 0; cv$i < state.states; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											
											// Zero an accumulator to track the probabilities reached.
											double cv$reachedDistributionProbability = 0.0;
											
											// Enumerating the possible arguments for the variable Categorical 69 which is consuming
											// the output of Sample task 71.
											{
												for(int var27 = 0; var27 < state.states; var27 += 1) {
													if((var27 == traceTempVariable$var67$37_1)) {
														{
															// Declare and zero an accumulator for tracking the reached source probability space.
															double scopeVariable$reachedSourceProbability = 0.0;
															
															// Enumerating the possible arguments for Categorical 69.
															{
																if((0 == (i$var64 - 1))) {
																	{
																		for(int index$var27$42_1 = 0; index$var27$42_1 < state.states; index$var27$42_1 += 1) {
																			if((index$var27$42_1 == state.st[(i$var64 - 1)]))
																				// Add the probability of this argument configuration.
																				scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																	}
																}
															}
															
															// Enumerating the possible arguments for Categorical 69.
															{
																int traceTempVariable$var67$43_1 = cv$currentValue;
																if((index$i$1 == (i$var64 - 1))) {
																	{
																		for(int index$var27$49_1 = 0; index$var27$49_1 < state.states; index$var27$49_1 += 1) {
																			if((index$var27$49_1 == traceTempVariable$var67$43_1))
																				// Add the probability of this argument configuration.
																				scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																	}
																}
															}
															for(int index$i$44 = 1; index$i$44 < state.samples; index$i$44 += 1) {
																if((!(index$i$44 == index$i$1) && !(index$i$44 == index$i$39))) {
																	// Enumerating the possible outputs of Categorical 69.
																	for(int index$sample71$45 = 0; index$sample71$45 < state.states; index$sample71$45 += 1) {
																		int distributionTempVariable$var70$47 = index$sample71$45;
																		
																		// Update the probability of sampling this value from the distribution value.
																		double cv$probabilitySample71Value46 = (1.0 * state.distribution$sample71[((index$i$44 - 1) / 1)][index$sample71$45]);
																		{
																			int traceTempVariable$var67$48_1 = distributionTempVariable$var70$47;
																			if((index$i$44 == (i$var64 - 1))) {
																				{
																					for(int index$var27$50_1 = 0; index$var27$50_1 < state.states; index$var27$50_1 += 1) {
																						if((index$var27$50_1 == traceTempVariable$var67$48_1))
																							// Add the probability of this argument configuration.
																							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample71Value46);
																					}
																				}
																			}
																		}
																	}
																}
															}
															
															// Constructing a random variable input for use later.
															double[] var68 = state.m[traceTempVariable$var67$37_1];
															
															// The probability of reaching the consumer with this set of consumer arguments
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
															
															// Record the reached distribution.
															cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
															
															// Add the current distribution to the distribution accumulator.
															DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, var68, state.states);
														}
													}
												}
											}
											
											// A local copy of the samples' distribution.
											double[] cv$sampleDistribution = state.distribution$sample71[((index$i$37_2 - 1) / 1)];
											
											// The overlap of the distributions so far.
											double cv$overlap = 0.0;
											
											// Calculate the overlap for each element in the distribution
											for(int cv$i = 0; cv$i < state.states; cv$i += 1) {
												// Normalise the values in the calculated distribution
												double cv$normalisedDistValue = (cv$accumulatedConsumerDistributions[cv$i] / cv$reachedDistributionProbability);
												
												// Corresponding value from the sample distribution
												double cv$sampleDistValue = cv$sampleDistribution[cv$i];
												
												// Calculate the overlap and store the result
												if((cv$sampleDistValue < cv$normalisedDistValue))
													cv$overlap = (cv$overlap + cv$sampleDistValue);
												
												// Calculate the overlap and store the result
												else
													cv$overlap = (cv$overlap + cv$normalisedDistValue);
											}
											
											// Scale and add the result to the combined results so far. A min is taken over the
											// reached distributions so that rounding cannot result in a value greater than one
											// as for a small probability this could give a negative value
											cv$accumulatedDistributionProbabilities = (cv$accumulatedDistributionProbabilities + Math.log(((cv$overlap * cv$reachedDistributionProbability) + (1.0 - Math.min(cv$reachedDistributionProbability, 1.0)))));
										}
									}
								}
							}
						}
					}
				}
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample71[((i$var64 - 1) / 1)]) {
				// Set the calculated probabilities to be the distribution values, and normalize
				// Local copy of the probability array
				double[] cv$localProbability = state.distribution$sample71[((i$var64 - 1) / 1)];
				
				// The sum of all the probabilities in log space
				double cv$logSum = 0.0;
				
				// Sum all the values
				{
					// Initialise the max to the first element.
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
						// Initialise the sum of the array elements
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
						cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
				} else {
					// Normalize log space values and move to normal space
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				
				// Set array values that are not computed for the input to negative infinity.
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
			}
		}
	}

	// Calculate the probability of the samples represented by sample71 using probability
	// distributions.
	private final void logProbabilityDistribution$sample71() {
		// Determine if we need to calculate the values for sample task 71 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample71) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(state.fixedFlag$sample71) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// A guard to check if the sample value is ever reached.
				boolean cv$sampleReached = false;
				for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Look for paths between the variable and the sample task 71 including any distribution
					// values.
					// 
					// Copy of index so that its values can be safely substituted
					int index$i$1 = i$var64;
					{
						{
							// The sample value to calculate the probability of generating
							int cv$sampleValue = state.st[i$var64];
							
							// Enumerating the possible arguments for Categorical 69.
							{
								if((0 == (i$var64 - 1))) {
									{
										for(int var27 = 0; var27 < state.states; var27 += 1) {
											if((var27 == state.st[(i$var64 - 1)])) {
												{
													double[] var68 = state.m[state.st[(i$var64 - 1)]];
													
													// Store the value of the function call, so the function call is only made once.
													double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
													
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
								}
							}
							
							// Enumerating the possible arguments for Categorical 69.
							{
								if((index$i$1 == (i$var64 - 1))) {
									{
										for(int var27 = 0; var27 < state.states; var27 += 1) {
											if((var27 == state.st[(i$var64 - 1)])) {
												{
													double[] var68 = state.m[state.st[(i$var64 - 1)]];
													
													// Store the value of the function call, so the function call is only made once.
													double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
													
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
								}
							}
							if(state.fixedFlag$sample71) {
								{
									for(int index$i$6_1 = 1; index$i$6_1 < state.samples; index$i$6_1 += 1) {
										if((index$i$6_1 == (i$var64 - 1))) {
											{
												for(int var27 = 0; var27 < state.states; var27 += 1) {
													if((var27 == state.st[(i$var64 - 1)])) {
														{
															double[] var68 = state.m[state.st[(i$var64 - 1)]];
															
															// Store the value of the function call, so the function call is only made once.
															double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
															
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
										}
									}
								}
							} else {
								for(int index$i$7 = 1; index$i$7 < state.samples; index$i$7 += 1) {
									if(!(index$i$7 == index$i$1)) {
										// Enumerating the possible outputs of Categorical 69.
										for(int index$sample71$8 = 0; index$sample71$8 < state.states; index$sample71$8 += 1) {
											int distributionTempVariable$var70$10 = index$sample71$8;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample71Value9 = (1.0 * state.distribution$sample71[((index$i$7 - 1) / 1)][index$sample71$8]);
											{
												int traceTempVariable$var67$11_1 = distributionTempVariable$var70$10;
												if((index$i$7 == (i$var64 - 1))) {
													{
														for(int var27 = 0; var27 < state.states; var27 += 1) {
															if((var27 == traceTempVariable$var67$11_1)) {
																{
																	double[] var68 = state.m[traceTempVariable$var67$11_1];
																	
																	// Store the value of the function call, so the function call is only made once.
																	double cv$weightedProbability = (Math.log(cv$probabilitySample71Value9) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																	
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
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value9);
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
					state.logProbability$var70 = cv$accumulator;
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(state.fixedFlag$sample71)
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
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var70;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(state.fixedFlag$sample71)
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

	// Calculate the probability of the samples represented by sample87 using probability
	// distributions.
	private final void logProbabilityDistribution$sample87() {
		// Determine if we need to calculate the values for sample task 87 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample87) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.samples; j += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 87 including any distribution
				// values.
				{
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = state.flips[j];
						
						// Enumerating the possible arguments for Bernoulli 85.
						{
							if((0 == j)) {
								{
									for(int var43 = 0; var43 < state.states; var43 += 1) {
										if((var43 == state.st[j])) {
											{
												double var84 = state.bias[state.st[j]];
												
												// Store the value of the function call, so the function call is only made once.
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
												
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
							}
						}
						
						// Enumerating the possible arguments for Bernoulli 85.
						if(state.fixedFlag$sample71) {
							{
								for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
									if((i$var64 == j)) {
										{
											for(int var43 = 0; var43 < state.states; var43 += 1) {
												if((var43 == state.st[j])) {
													{
														double var84 = state.bias[state.st[j]];
														
														// Store the value of the function call, so the function call is only made once.
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
														
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
									}
								}
							}
						} else {
							for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
								if(true) {
									// Enumerating the possible outputs of Categorical 69.
									for(int index$sample71$6 = 0; index$sample71$6 < state.states; index$sample71$6 += 1) {
										int distributionTempVariable$var70$8 = index$sample71$6;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample71Value7 = (1.0 * state.distribution$sample71[((i$var64 - 1) / 1)][index$sample71$6]);
										{
											int traceTempVariable$var83$9_1 = distributionTempVariable$var70$8;
											if((i$var64 == j)) {
												{
													for(int var43 = 0; var43 < state.states; var43 += 1) {
														if((var43 == traceTempVariable$var83$9_1)) {
															{
																double var84 = state.bias[traceTempVariable$var83$9_1];
																
																// Store the value of the function call, so the function call is only made once.
																double cv$weightedProbability = (Math.log(cv$probabilitySample71Value7) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
																
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
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value7);
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
				state.logProbability$var86 = cv$accumulator;
			
			// Update the variable probability
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample87 = ((state.fixedFlag$sample45 && state.fixedFlag$sample53) && state.fixedFlag$sample71);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.samples; j += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var86;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
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
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var27 = 0; var27 < state.states; var27 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						double[] cv$sampleValue = state.m[var27];
						{
							{
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, state.v, state.states));
								
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
				state.logProbability$var28 = cv$sampleAccumulator;
			
			// Update the variable probability
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample28 = state.fixedFlag$sample28;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var27 = 0; var27 < state.states; var27 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var28;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample45 using sampled
	// values.
	private final void logProbabilityValue$sample45() {
		// Determine if we need to calculate the values for sample task 45 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample45) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var43 = 0; var43 < state.states; var43 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = state.bias[var43];
						{
							{
								double var30 = 1.0;
								double var31 = 1.0;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var30, var31));
								
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
				state.logProbability$var44 = cv$sampleAccumulator;
			
			// Update the variable probability
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample45)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample45 = state.fixedFlag$sample45;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var43 = 0; var43 < state.states; var43 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var44;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample45)
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
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = state.st[0];
					{
						{
							double[] var50 = state.m[0];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var50[cv$sampleValue])) && (var50[cv$sampleValue] <= 1.0))?Math.log(var50[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			state.logProbability$var52 = cv$sampleProbability;
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample53)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample53 = (state.fixedFlag$sample53 && state.fixedFlag$sample28);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$var52;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample53)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
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
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Copy of index so that its values can be safely substituted
				int index$i$1 = i$var64;
				{
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = state.st[i$var64];
						{
							{
								double[] var68 = state.m[state.st[(i$var64 - 1)]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
				state.logProbability$var70 = cv$accumulator;
			
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
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var70;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
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
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.samples; j += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = state.flips[j];
						{
							{
								double var84 = state.bias[state.st[j]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
								
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
				state.logProbability$var86 = cv$accumulator;
			
			// Update the variable probability
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample87 = ((state.fixedFlag$sample45 && state.fixedFlag$sample53) && state.fixedFlag$sample71);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.samples; j += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var86;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
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
		for(int var27 = 0; var27 < state.states; var27 += 1) {
			double[] var28 = state.m[var27];
			if(!state.fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.states, var28);
		}
		for(int var43 = 0; var43 < state.states; var43 += 1) {
			if(!state.fixedFlag$sample45)
				state.bias[var43] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
		for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
			if(!state.fixedFlag$sample71)
				state.st[i$var64] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var64 - 1)]], state.states);
		}
		for(int j = 0; j < state.samples; j += 1)
			state.flips[j] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[state.st[j]]);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int var27 = 0; var27 < state.states; var27 += 1) {
			double[] var28 = state.m[var27];
			if(!state.fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.states, var28);
		}
		for(int var43 = 0; var43 < state.states; var43 += 1) {
			if(!state.fixedFlag$sample45)
				state.bias[var43] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
		for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
			// Create local copy of variable probabilities.
			double[] cv$distribution$sample71 = state.distribution$sample71[((i$var64 - 1) / 1)];
			for(int index$var69 = 0; index$var69 < state.states; index$var69 += 1) {
				if(!state.fixedFlag$sample71)
					// Zero the probability of each value
					cv$distribution$sample71[index$var69] = 0.0;
			}
			
			// Iterate through possible values for var69's arguments.
			// 
			// Enumerating the possible arguments for Categorical 69.
			{
				if((0 == (i$var64 - 1))) {
					{
						for(int var27 = 0; var27 < state.states; var27 += 1) {
							if((var27 == state.st[(i$var64 - 1)])) {
								{
									double[] var68 = state.m[state.st[(i$var64 - 1)]];
									for(int index$var69 = 0; index$var69 < state.states; index$var69 += 1) {
										if(!state.fixedFlag$sample71)
											// Save the probability of each value
											cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (1.0 * ((((((0.0 <= index$var69) && (index$var69 < state.states)) && (0 < state.states)) && (0.0 <= var68[index$var69])) && (var68[index$var69] <= 1.0))?var68[index$var69]:0.0)));
									}
								}
							}
						}
					}
				}
			}
			
			// Enumerating the possible arguments for Categorical 69.
			if(state.fixedFlag$sample71) {
				{
					for(int index$i$3_1 = 1; index$i$3_1 < state.samples; index$i$3_1 += 1) {
						if((index$i$3_1 == (i$var64 - 1))) {
							{
								for(int var27 = 0; var27 < state.states; var27 += 1) {
									if((var27 == state.st[(i$var64 - 1)])) {
										{
											double[] var68 = state.m[state.st[(i$var64 - 1)]];
											for(int index$var69 = 0; index$var69 < state.states; index$var69 += 1) {
												if(!state.fixedFlag$sample71)
													// Save the probability of each value
													cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (1.0 * ((((((0.0 <= index$var69) && (index$var69 < state.states)) && (0 < state.states)) && (0.0 <= var68[index$var69])) && (var68[index$var69] <= 1.0))?var68[index$var69]:0.0)));
											}
										}
									}
								}
							}
						}
					}
				}
			} else {
				for(int index$i$4 = 1; index$i$4 < state.samples; index$i$4 += 1) {
					if(true) {
						// Enumerating the possible outputs of Categorical 69.
						for(int index$sample71$5 = 0; index$sample71$5 < state.states; index$sample71$5 += 1) {
							int distributionTempVariable$var70$7 = index$sample71$5;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample71Value6 = (1.0 * state.distribution$sample71[((index$i$4 - 1) / 1)][index$sample71$5]);
							{
								int traceTempVariable$var67$8_1 = distributionTempVariable$var70$7;
								if((index$i$4 == (i$var64 - 1))) {
									{
										for(int var27 = 0; var27 < state.states; var27 += 1) {
											if((var27 == traceTempVariable$var67$8_1)) {
												{
													double[] var68 = state.m[traceTempVariable$var67$8_1];
													for(int index$var69 = 0; index$var69 < state.states; index$var69 += 1) {
														if(!state.fixedFlag$sample71)
															// Save the probability of each value
															cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (cv$probabilitySample71Value6 * ((((((0.0 <= index$var69) && (index$var69 < state.states)) && (0 < state.states)) && (0.0 <= var68[index$var69])) && (var68[index$var69] <= 1.0))?var68[index$var69]:0.0)));
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
			
			// Sum the values in the array
			double cv$var69$sum = 0.0;
			for(int index$var69 = 0; index$var69 < state.states; index$var69 += 1) {
				if(!state.fixedFlag$sample71)
					// sum the probability of each value
					cv$var69$sum = (cv$var69$sum + cv$distribution$sample71[index$var69]);
			}
			for(int index$var69 = 0; index$var69 < state.states; index$var69 += 1) {
				if(!state.fixedFlag$sample71)
					// Normalise the probability of each value
					cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] / cv$var69$sum);
			}
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		for(int var27 = 0; var27 < state.states; var27 += 1) {
			double[] var28 = state.m[var27];
			if(!state.fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.states, var28);
		}
		for(int var43 = 0; var43 < state.states; var43 += 1) {
			if(!state.fixedFlag$sample45)
				state.bias[var43] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
		for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
			if(!state.fixedFlag$sample71)
				state.st[i$var64] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var64 - 1)]], state.states);
		}
		for(int j = 0; j < state.samples; j += 1)
			state.flips[j] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[state.st[j]]);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var27 = 0; var27 < state.states; var27 += 1) {
			double[] var28 = state.m[var27];
			if(!state.fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.states, var28);
		}
		for(int var43 = 0; var43 < state.states; var43 += 1) {
			if(!state.fixedFlag$sample45)
				state.bias[var43] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
		for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
			if(!state.fixedFlag$sample71)
				state.st[i$var64] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var64 - 1)]], state.states);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		for(int var27 = 0; var27 < state.states; var27 += 1) {
			double[] var28 = state.m[var27];
			if(!state.fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.states, var28);
		}
		for(int var43 = 0; var43 < state.states; var43 += 1) {
			if(!state.fixedFlag$sample45)
				state.bias[var43] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
		for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
			if(!state.fixedFlag$sample71)
				state.st[i$var64] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var64 - 1)]], state.states);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			for(int var27 = 0; var27 < state.states; var27 += 1) {
				if(!state.fixedFlag$sample28)
					inferSample28(var27);
			}
			for(int var43 = 0; var43 < state.states; var43 += 1) {
				if(!state.fixedFlag$sample45)
					inferSample45(var43);
			}
			if(!state.fixedFlag$sample53)
				inferSample53();
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
				if(!state.fixedFlag$sample71)
					inferSample71(i$var64);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i$var64 = (state.samples - ((((state.samples - 1) - 1) % 1) + 1)); i$var64 >= ((1 - 1) + 1); i$var64 -= 1) {
				if(!state.fixedFlag$sample71)
					inferSample71(i$var64);
			}
			if(!state.fixedFlag$sample53)
				inferSample53();
			for(int var43 = (state.states - ((((state.states - 1) - 0) % 1) + 1)); var43 >= ((0 - 1) + 1); var43 -= 1) {
				if(!state.fixedFlag$sample45)
					inferSample45(var43);
			}
			for(int var27 = (state.states - ((((state.states - 1) - 0) % 1) + 1)); var27 >= ((0 - 1) + 1); var27 -= 1) {
				if(!state.fixedFlag$sample28)
					inferSample28(var27);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int var27 = 0; var27 < state.states; var27 += 1) {
			if(!state.constrainedFlag$sample28[((var27 - 0) / 1)])
				drawValueSample28(var27);
		}
		for(int var43 = 0; var43 < state.states; var43 += 1) {
			if(!state.constrainedFlag$sample45[((var43 - 0) / 1)])
				drawValueSample45(var43);
		}
		if(!state.constrainedFlag$sample53)
			drawValueSample53();
		for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
			if(!state.constrainedFlag$sample71[((i$var64 - 1) / 1)])
				drawValueSample71(i$var64);
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
		if(!state.fixedProbFlag$sample71)
			state.logProbability$var70 = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample87)
			state.logProbability$var86 = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.states = 5;
		for(int i$var13 = 0; i$var13 < 5; i$var13 += 1)
			state.v[i$var13] = 0.1;
		state.samples = state.length$flipsMeasured;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < state.constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
			state.constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
		
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
		if(state.fixedFlag$sample45)
			logProbabilityValue$sample45();
		if(state.fixedFlag$sample53)
			logProbabilityValue$sample53();
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
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityDistribution$sample71();
		logProbabilityDistribution$sample87();
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
		logProbabilityValue$sample71();
		logProbabilityValue$sample87();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Deep copy between arrays
		boolean[] cv$source1 = state.flipsMeasured;
		boolean[] cv$target1 = state.flips;
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
		     + " * Copyright (c) 2019-2023, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model HMMTestPart8(boolean[] flipsMeasured) {\n"
		     + "        int states = 5;\n"
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
		     + "        for(int i:[1..samples))\n"
		     + "            st[i] = categorical(m[st[i-1]]).sampleDistribution();\n"
		     + "            \n"
		     + "        boolean[] flips = new boolean[samples];\n"
		     + "            \n"
		     + "        for(int j:[0..samples))\n"
		     + "            flips[j] = bernoulli(bias[st[j]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}