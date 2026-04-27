package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMTestPart3d$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMTestPart3d.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMTestPart3d$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$var28$countGlobal;
		double[] cv$var53$stateProbabilityGlobal;
		double[] cv$var78$stateProbabilityGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for cv$var28$countGlobal
			{
				// Allocation of cv$var28$countGlobal for single threaded execution
				cv$var28$countGlobal = new double[2];
			}
			
			// Constructor for cv$var53$stateProbabilityGlobal
			{
				// Variable to record the maximum value of Task Get 52. Initially set to the value
				// of putTask 29.
				int cv$var29$max = 2;
				
				// Allocation of cv$var53$stateProbabilityGlobal for single threaded execution
				cv$var53$stateProbabilityGlobal = new double[cv$var29$max];
			}
			
			// Constructor for cv$var78$stateProbabilityGlobal
			{
				// Variable to record the maximum value of Task Get 77. Initially set to the value
				// of putTask 29.
				int cv$var29$max = 2;
				
				// Allocation of cv$var78$stateProbabilityGlobal for single threaded execution
				cv$var78$stateProbabilityGlobal = new double[cv$var29$max];
			}
		}
	}


	public HMMTestPart3d$SingleThreadCPU(State state, ExecutionTarget target) {
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

	// Pick a value from the distribution for the unconditioned variable from sample54
	private final void drawValueSample54() {
		state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
		
		// Guards to ensure that st2 is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 54 and consumer int[] 61.
		{
			{
				if((0 == 0)) {
					{
						state.st2[0] = (state.samples - state.st[0]);
					}
				}
			}
		}
		
		// Guards to ensure that st2 is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 54 and consumer int[] 102.
		{
			{
				for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
					if((0 == (state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71))) {
						{
							state.st2[(state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)] = (state.samples - state.st[(state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)]);
						}
					}
				}
			}
		}
	}

	// Pick a value from the distribution for the unconditioned variable from sample79
	private final void drawValueSample79(int i$var71) {
		state.st[i$var71] = DistributionSampling.sampleCategorical(state.RNG$, state.m[(state.samples - state.st2[(i$var71 - 1)])], state.states);
		
		// Guards to ensure that st2 is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 79 and consumer int[] 102.
		{
			{
				for(int index$i$1_1 = 1; index$i$1_1 < state.samples; index$i$1_1 += 1) {
					if((i$var71 == (state.indirection[((index$i$1_1 - 1) / 1)][index$i$1_1] / index$i$1_1))) {
						{
							state.st2[(state.indirection[((index$i$1_1 - 1) / 1)][index$i$1_1] / index$i$1_1)] = (state.samples - state.st[(state.indirection[((index$i$1_1 - 1) / 1)][index$i$1_1] / index$i$1_1)]);
						}
					}
				}
			}
		}
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
				// Processing random variable 52.
				{
					// Looking for a path between Sample 28 and consumer Categorical 52.
					{
						{
							if((var27 == 0)) {
								// Processing sample task 54 of consumer random variable null.
								{
									{
										// Flag recording if this sample task of the consuming random variable is constrained.
										boolean cv$sampleConstrained = (state.fixedFlag$sample54 || state.constrainedFlag$sample54);
										if(cv$sampleConstrained) {
											// Mark that the sample has observed constrained data.
											state.constrainedFlag$sample28[((var27 - 0) / 1)] = true;
											{
												{
													{
														{
															{
																// Increment the sample counter with the value sampled by sample task 54 of random
																// variable var52
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
				
				// Processing random variable 77.
				{
					// Looking for a path between Sample 28 and consumer Categorical 77.
					{
						{
							for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
								if((var27 == (state.samples - state.st2[(i$var71 - 1)]))) {
									// Processing sample task 79 of consumer random variable null.
									{
										{
											// Flag recording if this sample task of the consuming random variable is constrained.
											boolean cv$sampleConstrained = (state.fixedFlag$sample79 || state.constrainedFlag$sample79[((i$var71 - 1) / 1)]);
											if(cv$sampleConstrained) {
												// Mark that the sample has observed constrained data.
												state.constrainedFlag$sample28[((var27 - 0) / 1)] = true;
												{
													{
														{
															{
																{
																	// Increment the sample counter with the value sampled by sample task 79 of random
																	// variable var77
																	cv$countLocal[state.st[i$var71]] = (cv$countLocal[state.st[i$var71]] + 1.0);
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
			int cv$sum = 0;
			
			// Local variable to record the number of samples.
			int cv$count = 0;
			{
				// Processing random variable 117.
				{
					// Looking for a path between Sample 45 and consumer Bernoulli 117.
					{
						{
							for(int j = 0; j < state.samples; j += 1) {
								if((var43 == (state.samples - state.st2[j]))) {
									// Processing sample task 119 of consumer random variable null.
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
																	// Include the value sampled by task 119 from random variable var117.
																	// Increment the number of samples.
																	cv$count = (cv$count + 1);
																	
																	// If the sample value was positive increase the count
																	if(state.flips[j])
																		cv$sum = (cv$sum + 1);
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
	// by sample task 54 drawn from Categorical 52. Inference was performed using variable
	// marginalization.
	private final void inferSample54() {
		if(true) {
			state.constrainedFlag$sample54 = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, state.states);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = scratch.cv$var53$stateProbabilityGlobal;
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
				int var53 = cv$currentValue;
				
				// Guards to ensure that st is only updated when there is a valid path.
				{
					{
						{
							state.st[0] = cv$currentValue;
						}
					}
				}
				
				// Guards to ensure that st2 is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 54 and consumer int[] 61.
				{
					{
						if((0 == 0)) {
							{
								state.st2[0] = (state.samples - state.st[0]);
							}
						}
					}
				}
				
				// Guards to ensure that st2 is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 54 and consumer int[] 102.
				{
					{
						for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
							if((0 == (state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71))) {
								{
									state.st2[(state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)] = (state.samples - state.st[(state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)]);
								}
							}
						}
					}
				}
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// Constructing a random variable input for use later.
					double[] var51 = state.m[0];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.states)) && (0 < state.states)) && (0.0 <= var51[cv$currentValue])) && (var51[cv$currentValue] <= 1.0))?Math.log(var51[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 77.
					{
						// Looking for a path between Sample 54 and consumer Categorical 77.
						{
							{
								int traceTempVariable$var58$4_1 = cv$currentValue;
								if((0 == 0)) {
									int traceTempVariable$var74$4_2 = (state.samples - traceTempVariable$var58$4_1);
									for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
										if((0 == (i$var71 - 1))) {
											// Processing sample task 79 of consumer random variable null.
											{
												{
													// Flag recording if this sample task of the consuming random variable is constrained.
													boolean cv$sampleConstrained = (state.fixedFlag$sample79 || state.constrainedFlag$sample79[((i$var71 - 1) / 1)]);
													if(cv$sampleConstrained) {
														// Mark that the sample has observed constrained data.
														state.constrainedFlag$sample54 = true;
														
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
																			double[] var76 = state.m[(state.samples - traceTempVariable$var74$4_2)];
																			
																			// Record the probability of sample task 79 generating output with current configuration.
																			if(((Math.log(1.0) + ((((((0.0 <= state.st[i$var71]) && (state.st[i$var71] < state.states)) && (0 < state.states)) && (0.0 <= var76[state.st[i$var71]])) && (var76[state.st[i$var71]] <= 1.0))?Math.log(var76[state.st[i$var71]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.st[i$var71]) && (state.st[i$var71] < state.states)) && (0 < state.states)) && (0.0 <= var76[state.st[i$var71]])) && (var76[state.st[i$var71]] <= 1.0))?Math.log(var76[state.st[i$var71]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.st[i$var71]) && (state.st[i$var71] < state.states)) && (0 < state.states)) && (0.0 <= var76[state.st[i$var71]])) && (var76[state.st[i$var71]] <= 1.0))?Math.log(var76[state.st[i$var71]]):Double.NEGATIVE_INFINITY));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.st[i$var71]) && (state.st[i$var71] < state.states)) && (0 < state.states)) && (0.0 <= var76[state.st[i$var71]])) && (var76[state.st[i$var71]] <= 1.0))?Math.log(var76[state.st[i$var71]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.st[i$var71]) && (state.st[i$var71] < state.states)) && (0 < state.states)) && (0.0 <= var76[state.st[i$var71]])) && (var76[state.st[i$var71]] <= 1.0))?Math.log(var76[state.st[i$var71]]):Double.NEGATIVE_INFINITY)));
																			}
																			
																			// Recorded the probability of reaching sample task 79 with the current configuration.
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
							{
								int traceTempVariable$var100$5_1 = cv$currentValue;
								for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
									if((0 == (state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71))) {
										int traceTempVariable$var74$5_3 = (state.samples - traceTempVariable$var100$5_1);
										for(int index$i$5_4 = 1; index$i$5_4 < state.samples; index$i$5_4 += 1) {
											if(((state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71) == (index$i$5_4 - 1))) {
												// Processing sample task 79 of consumer random variable null.
												{
													{
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = (state.fixedFlag$sample79 || state.constrainedFlag$sample79[((index$i$5_4 - 1) / 1)]);
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															state.constrainedFlag$sample54 = true;
															
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
																				double[] var76 = state.m[(state.samples - traceTempVariable$var74$5_3)];
																				
																				// Record the probability of sample task 79 generating output with current configuration.
																				if(((Math.log(1.0) + ((((((0.0 <= state.st[index$i$5_4]) && (state.st[index$i$5_4] < state.states)) && (0 < state.states)) && (0.0 <= var76[state.st[index$i$5_4]])) && (var76[state.st[index$i$5_4]] <= 1.0))?Math.log(var76[state.st[index$i$5_4]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.st[index$i$5_4]) && (state.st[index$i$5_4] < state.states)) && (0 < state.states)) && (0.0 <= var76[state.st[index$i$5_4]])) && (var76[state.st[index$i$5_4]] <= 1.0))?Math.log(var76[state.st[index$i$5_4]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.st[index$i$5_4]) && (state.st[index$i$5_4] < state.states)) && (0 < state.states)) && (0.0 <= var76[state.st[index$i$5_4]])) && (var76[state.st[index$i$5_4]] <= 1.0))?Math.log(var76[state.st[index$i$5_4]]):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.st[index$i$5_4]) && (state.st[index$i$5_4] < state.states)) && (0 < state.states)) && (0.0 <= var76[state.st[index$i$5_4]])) && (var76[state.st[index$i$5_4]] <= 1.0))?Math.log(var76[state.st[index$i$5_4]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.st[index$i$5_4]) && (state.st[index$i$5_4] < state.states)) && (0 < state.states)) && (0.0 <= var76[state.st[index$i$5_4]])) && (var76[state.st[index$i$5_4]] <= 1.0))?Math.log(var76[state.st[index$i$5_4]]):Double.NEGATIVE_INFINITY)));
																				}
																				
																				// Recorded the probability of reaching sample task 79 with the current configuration.
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
					
					// Processing random variable 117.
					{
						// Looking for a path between Sample 54 and consumer Bernoulli 117.
						{
							{
								int traceTempVariable$var58$10_1 = cv$currentValue;
								if((0 == 0)) {
									int traceTempVariable$var114$10_2 = (state.samples - traceTempVariable$var58$10_1);
									for(int j = 0; j < state.samples; j += 1) {
										if((0 == j)) {
											// Processing sample task 119 of consumer random variable null.
											{
												{
													// Flag recording if this sample task of the consuming random variable is constrained.
													boolean cv$sampleConstrained = true;
													if(cv$sampleConstrained) {
														// Mark that the sample has observed constrained data.
														state.constrainedFlag$sample54 = true;
														
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
																			double var116 = state.bias[(state.samples - traceTempVariable$var114$10_2)];
																			
																			// Record the probability of sample task 119 generating output with current configuration.
																			if(((Math.log(1.0) + (((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[j]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[j]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[j]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[j]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[j]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY)));
																			}
																			
																			// Recorded the probability of reaching sample task 119 with the current configuration.
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
							{
								int traceTempVariable$var100$11_1 = cv$currentValue;
								for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
									if((0 == (state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71))) {
										int traceTempVariable$var114$11_3 = (state.samples - traceTempVariable$var100$11_1);
										for(int j = 0; j < state.samples; j += 1) {
											if(((state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71) == j)) {
												// Processing sample task 119 of consumer random variable null.
												{
													{
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = true;
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															state.constrainedFlag$sample54 = true;
															
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
																				double var116 = state.bias[(state.samples - traceTempVariable$var114$11_3)];
																				
																				// Record the probability of sample task 119 generating output with current configuration.
																				if(((Math.log(1.0) + (((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[j]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[j]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[j]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[j]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[j]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY)));
																				}
																				
																				// Recorded the probability of reaching sample task 119 with the current configuration.
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
			if(state.constrainedFlag$sample54) {
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
				
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				int var53 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
				
				// Guards to ensure that st is only updated when there is a valid path.
				{
					{
						{
							state.st[0] = var53;
						}
					}
				}
				
				// Guards to ensure that st2 is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 54 and consumer int[] 61.
				{
					{
						if((0 == 0)) {
							{
								state.st2[0] = (state.samples - state.st[0]);
							}
						}
					}
				}
				
				// Guards to ensure that st2 is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 54 and consumer int[] 102.
				{
					{
						for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
							if((0 == (state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71))) {
								{
									state.st2[(state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)] = (state.samples - state.st[(state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)]);
								}
							}
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 79 drawn from Categorical 77. Inference was performed using variable
	// marginalization.
	private final void inferSample79(int i$var71) {
		if(true) {
			state.constrainedFlag$sample79[((i$var71 - 1) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, state.states);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = scratch.cv$var78$stateProbabilityGlobal;
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
				int var78 = cv$currentValue;
				
				// Guards to ensure that st is only updated when there is a valid path.
				{
					{
						{
							state.st[i$var71] = cv$currentValue;
						}
					}
				}
				
				// Guards to ensure that st2 is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 79 and consumer int[] 102.
				{
					{
						for(int index$i$2_1 = 1; index$i$2_1 < state.samples; index$i$2_1 += 1) {
							if((i$var71 == (state.indirection[((index$i$2_1 - 1) / 1)][index$i$2_1] / index$i$2_1))) {
								{
									state.st2[(state.indirection[((index$i$2_1 - 1) / 1)][index$i$2_1] / index$i$2_1)] = (state.samples - state.st[(state.indirection[((index$i$2_1 - 1) / 1)][index$i$2_1] / index$i$2_1)]);
								}
							}
						}
					}
				}
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// Constructing a random variable input for use later.
					double[] var76 = state.m[(state.samples - state.st2[(i$var71 - 1)])];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.states)) && (0 < state.states)) && (0.0 <= var76[cv$currentValue])) && (var76[cv$currentValue] <= 1.0))?Math.log(var76[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 77.
					{
						// Looking for a path between Sample 79 and consumer Categorical 77.
						{
							{
								int traceTempVariable$var100$3_1 = cv$currentValue;
								for(int index$i$3_2 = 1; index$i$3_2 < state.samples; index$i$3_2 += 1) {
									if((i$var71 == (state.indirection[((index$i$3_2 - 1) / 1)][index$i$3_2] / index$i$3_2))) {
										int traceTempVariable$var74$3_3 = (state.samples - traceTempVariable$var100$3_1);
										for(int index$i$3_4 = 1; index$i$3_4 < state.samples; index$i$3_4 += 1) {
											if(((state.indirection[((index$i$3_2 - 1) / 1)][index$i$3_2] / index$i$3_2) == (index$i$3_4 - 1))) {
												// Processing sample task 79 of consumer random variable null.
												{
													{
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = (state.fixedFlag$sample79 || state.constrainedFlag$sample79[((index$i$3_4 - 1) / 1)]);
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															state.constrainedFlag$sample79[((i$var71 - 1) / 1)] = true;
															
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
																				double[] sc$var76$1 = state.m[(state.samples - traceTempVariable$var74$3_3)];
																				
																				// Record the probability of sample task 79 generating output with current configuration.
																				if(((Math.log(1.0) + ((((((0.0 <= state.st[index$i$3_4]) && (state.st[index$i$3_4] < state.states)) && (0 < state.states)) && (0.0 <= sc$var76$1[state.st[index$i$3_4]])) && (sc$var76$1[state.st[index$i$3_4]] <= 1.0))?Math.log(sc$var76$1[state.st[index$i$3_4]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.st[index$i$3_4]) && (state.st[index$i$3_4] < state.states)) && (0 < state.states)) && (0.0 <= sc$var76$1[state.st[index$i$3_4]])) && (sc$var76$1[state.st[index$i$3_4]] <= 1.0))?Math.log(sc$var76$1[state.st[index$i$3_4]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.st[index$i$3_4]) && (state.st[index$i$3_4] < state.states)) && (0 < state.states)) && (0.0 <= sc$var76$1[state.st[index$i$3_4]])) && (sc$var76$1[state.st[index$i$3_4]] <= 1.0))?Math.log(sc$var76$1[state.st[index$i$3_4]]):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.st[index$i$3_4]) && (state.st[index$i$3_4] < state.states)) && (0 < state.states)) && (0.0 <= sc$var76$1[state.st[index$i$3_4]])) && (sc$var76$1[state.st[index$i$3_4]] <= 1.0))?Math.log(sc$var76$1[state.st[index$i$3_4]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.st[index$i$3_4]) && (state.st[index$i$3_4] < state.states)) && (0 < state.states)) && (0.0 <= sc$var76$1[state.st[index$i$3_4]])) && (sc$var76$1[state.st[index$i$3_4]] <= 1.0))?Math.log(sc$var76$1[state.st[index$i$3_4]]):Double.NEGATIVE_INFINITY)));
																				}
																				
																				// Recorded the probability of reaching sample task 79 with the current configuration.
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
					
					// Processing random variable 117.
					{
						// Looking for a path between Sample 79 and consumer Bernoulli 117.
						{
							{
								int traceTempVariable$var100$6_1 = cv$currentValue;
								for(int index$i$6_2 = 1; index$i$6_2 < state.samples; index$i$6_2 += 1) {
									if((i$var71 == (state.indirection[((index$i$6_2 - 1) / 1)][index$i$6_2] / index$i$6_2))) {
										int traceTempVariable$var114$6_3 = (state.samples - traceTempVariable$var100$6_1);
										for(int j = 0; j < state.samples; j += 1) {
											if(((state.indirection[((index$i$6_2 - 1) / 1)][index$i$6_2] / index$i$6_2) == j)) {
												// Processing sample task 119 of consumer random variable null.
												{
													{
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = true;
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															state.constrainedFlag$sample79[((i$var71 - 1) / 1)] = true;
															
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
																				double var116 = state.bias[(state.samples - traceTempVariable$var114$6_3)];
																				
																				// Record the probability of sample task 119 generating output with current configuration.
																				if(((Math.log(1.0) + (((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[j]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[j]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[j]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[j]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[j]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY)));
																				}
																				
																				// Recorded the probability of reaching sample task 119 with the current configuration.
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
			if(state.constrainedFlag$sample79[((i$var71 - 1) / 1)]) {
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
				
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				int var78 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
				
				// Guards to ensure that st is only updated when there is a valid path.
				{
					{
						{
							state.st[i$var71] = var78;
						}
					}
				}
				
				// Guards to ensure that st2 is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 79 and consumer int[] 102.
				{
					{
						for(int index$i$10_1 = 1; index$i$10_1 < state.samples; index$i$10_1 += 1) {
							if((i$var71 == (state.indirection[((index$i$10_1 - 1) / 1)][index$i$10_1] / index$i$10_1))) {
								{
									state.st2[(state.indirection[((index$i$10_1 - 1) / 1)][index$i$10_1] / index$i$10_1)] = (state.samples - state.st[(state.indirection[((index$i$10_1 - 1) / 1)][index$i$10_1] / index$i$10_1)]);
								}
							}
						}
					}
				}
			}
		}
	}

	// Calculate the probability of the samples represented by sample119 using sampled
	// values.
	private final void logProbabilityValue$sample119() {
		// Determine if we need to calculate the values for sample task 119 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample119) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.samples; j += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
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
								double var116 = state.bias[(state.samples - state.st2[j])];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var116) && (var116 <= 1.0))?Math.log((cv$sampleValue?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY));
								
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
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample119[((j - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample119 = ((state.fixedFlag$sample45 && state.fixedFlag$sample54) && state.fixedFlag$sample79);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.samples; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample119[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			
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
							double[] var51 = state.m[0];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var51[cv$sampleValue])) && (var51[cv$sampleValue] <= 1.0))?Math.log(var51[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			state.logProbability$var53 = cv$sampleProbability;
			
			// Guard to ensure that st2 is only updated once for this probability.
			boolean cv$guard$st2 = false;
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			// 
			// Looking for a path between Sample 54 and consumer int[] 61.
			{
				{
					if((0 == 0)) {
						// If the probability of the variable has not already been updated
						if(!cv$guard$st2) {
							// Set the guard so the update is only applied once.
							cv$guard$st2 = true;
							
							// Update the variable probability
							state.logProbability$st2 = (state.logProbability$st2 + cv$accumulator);
						}
					}
				}
			}
			
			// Looking for a path between Sample 54 and consumer int[] 102.
			{
				{
					for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
						if((0 == (state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71))) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$st2) {
								// Set the guard so the update is only applied once.
								cv$guard$st2 = true;
								
								// Update the variable probability
								state.logProbability$st2 = (state.logProbability$st2 + cv$accumulator);
							}
						}
					}
				}
			}
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample54)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample54 = (state.fixedFlag$sample54 && state.fixedFlag$sample28);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$var53;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Guard to ensure that st2 is only updated once for this probability.
			boolean cv$guard$st2 = false;
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			// 
			// Looking for a path between Sample 54 and consumer int[] 61.
			{
				{
					if((0 == 0)) {
						// If the probability of the variable has not already been updated
						if(!cv$guard$st2) {
							// Set the guard so the update is only applied once.
							cv$guard$st2 = true;
							
							// Update the variable probability
							state.logProbability$st2 = (state.logProbability$st2 + cv$accumulator);
						}
					}
				}
			}
			
			// Looking for a path between Sample 54 and consumer int[] 102.
			{
				{
					for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
						if((0 == (state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71))) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$st2) {
								// Set the guard so the update is only applied once.
								cv$guard$st2 = true;
								
								// Update the variable probability
								state.logProbability$st2 = (state.logProbability$st2 + cv$accumulator);
							}
						}
					}
				}
			}
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample54)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample79 using sampled
	// values.
	private final void logProbabilityValue$sample79() {
		// Determine if we need to calculate the values for sample task 79 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample79) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = state.st[i$var71];
						{
							{
								double[] var76 = state.m[(state.samples - state.st2[(i$var71 - 1)])];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var76[cv$sampleValue])) && (var76[cv$sampleValue] <= 1.0))?Math.log(var76[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample79[((i$var71 - 1) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that st2 is only updated once for this probability.
				boolean cv$guard$st2 = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 79 and consumer int[] 102.
				{
					{
						for(int index$i$2_1 = 1; index$i$2_1 < state.samples; index$i$2_1 += 1) {
							if((i$var71 == (state.indirection[((index$i$2_1 - 1) / 1)][index$i$2_1] / index$i$2_1))) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$st2) {
									// Set the guard so the update is only applied once.
									cv$guard$st2 = true;
									
									// Update the variable probability
									state.logProbability$st2 = (state.logProbability$st2 + cv$sampleProbability);
								}
							}
						}
					}
				}
			}
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample79)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample79 = ((state.fixedFlag$sample79 && state.fixedFlag$sample28) && state.fixedFlag$sample54);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample79[((i$var71 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				
				// Guard to ensure that st2 is only updated once for this probability.
				boolean cv$guard$st2 = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 79 and consumer int[] 102.
				{
					{
						for(int index$i$3_1 = 1; index$i$3_1 < state.samples; index$i$3_1 += 1) {
							if((i$var71 == (state.indirection[((index$i$3_1 - 1) / 1)][index$i$3_1] / index$i$3_1))) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$st2) {
									// Set the guard so the update is only applied once.
									cv$guard$st2 = true;
									
									// Update the variable probability
									state.logProbability$st2 = (state.logProbability$st2 + cv$sampleValue);
								}
							}
						}
					}
				}
			}
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample79)
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
		if(!state.fixedFlag$sample54)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
		if(!state.fixedFlag$sample54)
			state.st2[0] = (state.samples - state.st[0]);
		for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
			if(!state.fixedFlag$sample79)
				state.st[i$var71] = DistributionSampling.sampleCategorical(state.RNG$, state.m[(state.samples - state.st2[(i$var71 - 1)])], state.states);
			if(!(state.fixedFlag$sample54 && state.fixedFlag$sample79))
				state.st2[(state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)] = (state.samples - state.st[(state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)]);
		}
		for(int j = 0; j < state.samples; j += 1)
			state.flips[j] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[(state.samples - state.st2[j])]);
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
		if(!state.fixedFlag$sample54)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
		state.st2[0] = (state.samples - state.st[0]);
		for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
			if(!state.fixedFlag$sample79)
				state.st[i$var71] = DistributionSampling.sampleCategorical(state.RNG$, state.m[(state.samples - state.st2[(i$var71 - 1)])], state.states);
			state.st2[(state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)] = (state.samples - state.st[(state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)]);
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
		if(!state.fixedFlag$sample54)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
		state.st2[0] = (state.samples - state.st[0]);
		for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
			if(!state.fixedFlag$sample79)
				state.st[i$var71] = DistributionSampling.sampleCategorical(state.RNG$, state.m[(state.samples - state.st2[(i$var71 - 1)])], state.states);
			state.st2[(state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)] = (state.samples - state.st[(state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)]);
		}
		for(int j = 0; j < state.samples; j += 1)
			state.flips[j] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[(state.samples - state.st2[j])]);
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
		if(!state.fixedFlag$sample54)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
		if(!state.fixedFlag$sample54)
			state.st2[0] = (state.samples - state.st[0]);
		for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
			if(!state.fixedFlag$sample79)
				state.st[i$var71] = DistributionSampling.sampleCategorical(state.RNG$, state.m[(state.samples - state.st2[(i$var71 - 1)])], state.states);
			if(!(state.fixedFlag$sample54 && state.fixedFlag$sample79))
				state.st2[(state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)] = (state.samples - state.st[(state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)]);
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
		if(!state.fixedFlag$sample54)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
		state.st2[0] = (state.samples - state.st[0]);
		for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
			if(!state.fixedFlag$sample79)
				state.st[i$var71] = DistributionSampling.sampleCategorical(state.RNG$, state.m[(state.samples - state.st2[(i$var71 - 1)])], state.states);
			state.st2[(state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)] = (state.samples - state.st[(state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)]);
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
			if(!state.fixedFlag$sample54)
				inferSample54();
			for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
				if(!state.fixedFlag$sample79)
					inferSample79(i$var71);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i$var71 = (state.samples - ((((state.samples - 1) - 1) % 1) + 1)); i$var71 >= ((1 - 1) + 1); i$var71 -= 1) {
				if(!state.fixedFlag$sample79)
					inferSample79(i$var71);
			}
			if(!state.fixedFlag$sample54)
				inferSample54();
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
		if(!state.constrainedFlag$sample54)
			drawValueSample54();
		for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
			if(!state.constrainedFlag$sample79[((i$var71 - 1) / 1)])
				drawValueSample79(i$var71);
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
		state.logProbability$st2 = 0.0;
		if(!state.fixedProbFlag$sample54)
			state.logProbability$var53 = Double.NaN;
		if(!state.fixedProbFlag$sample79) {
			for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1)
				state.logProbability$sample79[((i$var71 - 1) / 1)] = Double.NaN;
		}
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample119) {
			for(int j = 0; j < state.samples; j += 1)
				state.logProbability$sample119[((j - 0) / 1)] = Double.NaN;
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.states = 2;
		for(int i$var13 = 0; i$var13 < 2; i$var13 += 1)
			state.v[i$var13] = 0.1;
		state.samples = state.length$flipsMeasured;
		for(int i$var71 = 1; i$var71 < state.length$flipsMeasured; i$var71 += 1) {
			for(int k = 0; k < (i$var71 + 1); k += 1)
				state.indirection[((i$var71 - 1) / 1)][k] = (k * i$var71);
		}
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample79$1 = 0; index$constrainedFlag$sample79$1 < state.constrainedFlag$sample79.length; index$constrainedFlag$sample79$1 += 1)
			state.constrainedFlag$sample79[index$constrainedFlag$sample79$1] = true;
		
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
		if(state.fixedFlag$sample54)
			logProbabilityValue$sample54();
		if(state.fixedFlag$sample79)
			logProbabilityValue$sample79();
		logProbabilityValue$sample119();
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
		logProbabilityValue$sample54();
		logProbabilityValue$sample79();
		logProbabilityValue$sample119();
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
		logProbabilityValue$sample54();
		logProbabilityValue$sample79();
		logProbabilityValue$sample119();
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
	public final void setIntermediates() {
		state.st2[0] = (state.samples - state.st[0]);
		for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1)
			state.st2[(state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)] = (state.samples - state.st[(state.indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)]);
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
		     + "model HMMTestPart3d(boolean[] flipsMeasured) {\n"
		     + "        int states = 2;\n"
		     + "\n"
		     + "        double[] v = new double[states];\n"
		     + "        for(int i:[0..states))\n"
		     + "            v[i] = 0.1;\n"
		     + "\n"
		     + "        double[][] m = dirichlet(v).sample(states);\n"
		     + "        double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "        int samples = flipsMeasured.length;\n"
		     + "        int[] st = new int[samples];\n"
		     + "        int[] st2 = new int[samples];\n"
		     + "\n"
		     + "        st[0] = categorical(m[0]).sample();\n"
		     + "        st2[0] = samples - st[0];\n"
		     + "\n"
		     + "        for(int i:[1..samples)) {\n"
		     + "            st[i] = categorical(m[samples - st2[i-1]]).sample();\n"
		     + "            \n"
		     + "            int[] indirection = new int[i+1];\n"
		     + "            for(int k:[0..i])\n"
		     + "                indirection[k] = k*i; \n"
		     + "                \n"
		     + "            st2[indirection[i]/i] = samples - st[indirection[i]/i];\n"
		     + "        }\n"
		     + "            \n"
		     + "        boolean[] flips = new boolean[samples];\n"
		     + "            \n"
		     + "        for(int j:[0..samples))\n"
		     + "            flips[j] = bernoulli(bias[samples - st2[j]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}