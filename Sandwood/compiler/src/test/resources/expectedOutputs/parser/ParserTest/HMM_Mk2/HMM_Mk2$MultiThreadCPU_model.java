package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMM_Mk2$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMM_Mk2.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMM_Mk2$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[][] cv$var123$stateProbabilityGlobal;
		double[][] cv$var42$countGlobal;
		double[][] cv$var56$countGlobal;
		double[] cv$var75$countGlobal;
		double[] cv$var77$stateProbabilityGlobal;
		double[][] cv$var92$stateProbabilityGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for cv$var42$countGlobal
			{
				// Allocation of cv$var42$countGlobal for multithreaded execution
				{
					// Get the thread count.
					int cv$threadCount = threadCount();
					
					// Allocate an array to hold a copy per thread
					cv$var42$countGlobal = new double[cv$threadCount][];
					
					// Populate the array with a copy per thread
					for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
						cv$var42$countGlobal[cv$index] = new double[state.noStates];
				}
			}
			
			// Constructor for cv$var56$countGlobal
			{
				// Allocation of cv$var56$countGlobal for multithreaded execution
				{
					// Get the thread count.
					int cv$threadCount = threadCount();
					
					// Allocate an array to hold a copy per thread
					cv$var56$countGlobal = new double[cv$threadCount][];
					
					// Populate the array with a copy per thread
					for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
						cv$var56$countGlobal[cv$index] = new double[state.noEvents];
				}
			}
			
			// Constructor for cv$var75$countGlobal
			{
				// Allocation of cv$var75$countGlobal for single threaded execution
				cv$var75$countGlobal = new double[state.noStates];
			}
			
			// Constructor for cv$var77$stateProbabilityGlobal
			{
				// Allocation of cv$var77$stateProbabilityGlobal for single threaded execution
				cv$var77$stateProbabilityGlobal = new double[state.noStates];
			}
			
			// Constructor for cv$var92$stateProbabilityGlobal
			{
				// Variable to record the maximum value of Task Get 93. Initially set to the value
				// of putTask 43.
				int cv$var43$max = state.noStates;
				
				// Allocation of cv$var92$stateProbabilityGlobal for multithreaded execution
				{
					// Get the thread count.
					int cv$threadCount = threadCount();
					
					// Allocate an array to hold a copy per thread
					cv$var92$stateProbabilityGlobal = new double[cv$threadCount][];
					
					// Populate the array with a copy per thread
					for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
						cv$var92$stateProbabilityGlobal[cv$index] = new double[cv$var43$max];
				}
			}
			
			// Constructor for cv$var123$stateProbabilityGlobal
			{
				// Variable to record the maximum value of Task Get 124. Initially set to the value
				// of putTask 43.
				int cv$var43$max = state.noStates;
				
				// Allocation of cv$var123$stateProbabilityGlobal for multithreaded execution
				{
					// Get the thread count.
					int cv$threadCount = threadCount();
					
					// Allocate an array to hold a copy per thread
					cv$var123$stateProbabilityGlobal = new double[cv$threadCount][];
					
					// Populate the array with a copy per thread
					for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
						cv$var123$stateProbabilityGlobal[cv$index] = new double[cv$var43$max];
				}
			}
		}
	}


	public HMM_Mk2$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample126
	private final void drawValueSample126(int i$var104, int j$var115, int threadID$cv$i$var104, Rng RNG$) {
		int[] var116 = state.st[i$var104];
		var116[j$var115] = DistributionSampling.sampleCategorical(RNG$, state.m[state.st[i$var104][(j$var115 - 1)]], state.noStates);
	}

	// Pick a value from the distribution for the unconditioned variable from sample42
	private final void drawValueSample42(int var41, int threadID$cv$var41, Rng RNG$) {
		double[] var42 = state.m[var41];
		DistributionSampling.sampleDirichlet(RNG$, state.v, state.noStates, var42);
	}

	// Pick a value from the distribution for the unconditioned variable from sample57
	private final void drawValueSample57(int var55, int threadID$cv$var55, Rng RNG$) {
		double[] var56 = state.bias[var55];
		DistributionSampling.sampleDirichlet(RNG$, state.v2, state.noEvents, var56);
	}

	// Pick a value from the distribution for the unconditioned variable from sample78
	private final void drawValueSample78() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.weights);
	}

	// Pick a value from the distribution for the unconditioned variable from sample80
	private final void drawValueSample80() {
		state.initialState = DistributionSampling.sampleCategorical(state.RNG$, state.weights, state.noStates);
	}

	// Pick a value from the distribution for the unconditioned variable from sample95
	private final void drawValueSample95(int i$var87, int threadID$cv$i$var87, Rng RNG$) {
		int[] var88 = state.st[i$var87];
		var88[0] = DistributionSampling.sampleCategorical(RNG$, state.m[state.initialState], state.noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 126 drawn from Categorical 122. Inference was performed using variable
	// marginalization.
	private final void inferSample126(int i$var104, int j$var115, int threadID$cv$i$var104, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, state.noStates);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = scratch.cv$var123$stateProbabilityGlobal[threadID$cv$i$var104];
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
				int var123 = cv$currentValue;
				
				// Guards to ensure that st is only updated when there is a valid path.
				{
					{
						{
							int[] var116 = state.st[i$var104];
							var116[j$var115] = cv$currentValue;
						}
					}
				}
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// Constructing a random variable input for use later.
					double[] var121 = state.m[state.st[i$var104][(j$var115 - 1)]];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var121[cv$currentValue])) && (var121[cv$currentValue] <= 1.0))?Math.log(var121[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 122.
					{
						// Looking for a path between Sample 126 and consumer Categorical 122.
						{
							{
								int traceTempVariable$var120$2_1 = cv$currentValue;
								for(int index$i$2_2 = 0; index$i$2_2 < state.samples; index$i$2_2 += 1) {
									if((i$var104 == index$i$2_2)) {
										for(int index$j$2_3 = 1; index$j$2_3 < state.length$eventsMeasured[index$i$2_2]; index$j$2_3 += 1) {
											if((j$var115 == (index$j$2_3 - 1))) {
												// Processing sample task 126 of consumer random variable null.
												{
													{
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = (state.fixedFlag$sample126 || state.constrainedFlag$sample126[((index$i$2_2 - 0) / 1)][((index$j$2_3 - 1) / 1)]);
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															state.constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = true;
															
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
																				double[] sc$var121$1 = state.m[traceTempVariable$var120$2_1];
																				
																				// Record the probability of sample task 126 generating output with current configuration.
																				if(((Math.log(1.0) + ((((((0.0 <= state.st[index$i$2_2][index$j$2_3]) && (state.st[index$i$2_2][index$j$2_3] < state.noStates)) && (0 < state.noStates)) && (0.0 <= sc$var121$1[state.st[index$i$2_2][index$j$2_3]])) && (sc$var121$1[state.st[index$i$2_2][index$j$2_3]] <= 1.0))?Math.log(sc$var121$1[state.st[index$i$2_2][index$j$2_3]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.st[index$i$2_2][index$j$2_3]) && (state.st[index$i$2_2][index$j$2_3] < state.noStates)) && (0 < state.noStates)) && (0.0 <= sc$var121$1[state.st[index$i$2_2][index$j$2_3]])) && (sc$var121$1[state.st[index$i$2_2][index$j$2_3]] <= 1.0))?Math.log(sc$var121$1[state.st[index$i$2_2][index$j$2_3]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.st[index$i$2_2][index$j$2_3]) && (state.st[index$i$2_2][index$j$2_3] < state.noStates)) && (0 < state.noStates)) && (0.0 <= sc$var121$1[state.st[index$i$2_2][index$j$2_3]])) && (sc$var121$1[state.st[index$i$2_2][index$j$2_3]] <= 1.0))?Math.log(sc$var121$1[state.st[index$i$2_2][index$j$2_3]]):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.st[index$i$2_2][index$j$2_3]) && (state.st[index$i$2_2][index$j$2_3] < state.noStates)) && (0 < state.noStates)) && (0.0 <= sc$var121$1[state.st[index$i$2_2][index$j$2_3]])) && (sc$var121$1[state.st[index$i$2_2][index$j$2_3]] <= 1.0))?Math.log(sc$var121$1[state.st[index$i$2_2][index$j$2_3]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.st[index$i$2_2][index$j$2_3]) && (state.st[index$i$2_2][index$j$2_3] < state.noStates)) && (0 < state.noStates)) && (0.0 <= sc$var121$1[state.st[index$i$2_2][index$j$2_3]])) && (sc$var121$1[state.st[index$i$2_2][index$j$2_3]] <= 1.0))?Math.log(sc$var121$1[state.st[index$i$2_2][index$j$2_3]]):Double.NEGATIVE_INFINITY)));
																				}
																				
																				// Recorded the probability of reaching sample task 126 with the current configuration.
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
					
					// Processing random variable 154.
					{
						// Looking for a path between Sample 126 and consumer Categorical 154.
						{
							{
								int traceTempVariable$var152$5_1 = cv$currentValue;
								for(int i$var136 = 0; i$var136 < state.samples; i$var136 += 1) {
									if((i$var104 == i$var136)) {
										for(int j$var149 = 1; j$var149 < state.length$eventsMeasured[i$var136]; j$var149 += 1) {
											if((j$var115 == j$var149)) {
												// Processing sample task 159 of consumer random variable null.
												{
													{
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = true;
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															state.constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = true;
															
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
																				double[] var153 = state.bias[traceTempVariable$var152$5_1];
																				
																				// Record the probability of sample task 159 generating output with current configuration.
																				if(((Math.log(1.0) + ((((((0.0 <= (state.events[i$var136][j$var149] - 1)) && ((state.events[i$var136][j$var149] - 1) < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var136][j$var149] - 1)])) && (var153[(state.events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= (state.events[i$var136][j$var149] - 1)) && ((state.events[i$var136][j$var149] - 1) < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var136][j$var149] - 1)])) && (var153[(state.events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= (state.events[i$var136][j$var149] - 1)) && ((state.events[i$var136][j$var149] - 1) < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var136][j$var149] - 1)])) && (var153[(state.events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= (state.events[i$var136][j$var149] - 1)) && ((state.events[i$var136][j$var149] - 1) < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var136][j$var149] - 1)])) && (var153[(state.events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= (state.events[i$var136][j$var149] - 1)) && ((state.events[i$var136][j$var149] - 1) < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var136][j$var149] - 1)])) && (var153[(state.events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)));
																				}
																				
																				// Recorded the probability of reaching sample task 159 with the current configuration.
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
			if(state.constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)]) {
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
				int var123 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates);
				
				// Guards to ensure that st is only updated when there is a valid path.
				{
					{
						{
							int[] var116 = state.st[i$var104];
							var116[j$var115] = var123;
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 42 drawn from Dirichlet 30. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample42(int var41, int threadID$cv$var41, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample42[((var41 - 0) / 1)] = false;
			
			// A reference local to the function for the sample variable.
			double[] cv$targetLocal = state.m[var41];
			
			// A local reference to the scratch space.
			double[] cv$countLocal = scratch.cv$var42$countGlobal[threadID$cv$var41];
			
			// Get the length of the array
			int cv$arrayLength = state.noStates;
			
			// Initialize the array values to 0.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				// Processing random variable 91.
				{
					// Looking for a path between Sample 42 and consumer Categorical 91.
					{
						{
							if((var41 == state.initialState)) {
								for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1) {
									// Flag recording if this sample task of the consuming random variable is constrained.
									boolean cv$sampleConstrained = (state.fixedFlag$sample95 || state.constrainedFlag$sample95[((i$var87 - 0) / 1)]);
									if(cv$sampleConstrained) {
										// Mark that the sample has observed constrained data.
										state.constrainedFlag$sample42[((var41 - 0) / 1)] = true;
										{
											{
												{
													{
														{
															// Increment the sample counter with the value sampled by sample task 95 of random
															// variable var91
															cv$countLocal[state.st[i$var87][0]] = (cv$countLocal[state.st[i$var87][0]] + 1.0);
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
				
				// Processing random variable 122.
				{
					// Looking for a path between Sample 42 and consumer Categorical 122.
					{
						{
							for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
								for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
									if((var41 == state.st[i$var104][(j$var115 - 1)])) {
										// Processing sample task 126 of consumer random variable null.
										{
											{
												// Flag recording if this sample task of the consuming random variable is constrained.
												boolean cv$sampleConstrained = (state.fixedFlag$sample126 || state.constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)]);
												if(cv$sampleConstrained) {
													// Mark that the sample has observed constrained data.
													state.constrainedFlag$sample42[((var41 - 0) / 1)] = true;
													{
														{
															{
																{
																	{
																		// Increment the sample counter with the value sampled by sample task 126 of random
																		// variable var122
																		cv$countLocal[state.st[i$var104][j$var115]] = (cv$countLocal[state.st[i$var104][j$var115]] + 1.0);
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
			if(state.constrainedFlag$sample42[((var41 - 0) / 1)])
				// Calculate the new sample value
				// 
				// Calculate a new sample value and write it into cv$targetLocal.
				Conjugates.sampleConjugateDirichletCategorical(RNG$, state.v, cv$countLocal, cv$targetLocal, state.noStates);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 57 drawn from Dirichlet 44. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample57(int var55, int threadID$cv$var55, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample57[((var55 - 0) / 1)] = false;
			
			// A reference local to the function for the sample variable.
			double[] cv$targetLocal = state.bias[var55];
			
			// A local reference to the scratch space.
			double[] cv$countLocal = scratch.cv$var56$countGlobal[threadID$cv$var55];
			
			// Get the length of the array
			int cv$arrayLength = state.noEvents;
			
			// Initialize the array values to 0.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				// Processing random variable 154.
				{
					// Looking for a path between Sample 57 and consumer Categorical 154.
					{
						{
							for(int i$var136 = 0; i$var136 < state.samples; i$var136 += 1) {
								for(int j$var149 = 1; j$var149 < state.length$eventsMeasured[i$var136]; j$var149 += 1) {
									if((var55 == state.st[i$var136][j$var149])) {
										// Processing sample task 159 of consumer random variable null.
										{
											{
												// Flag recording if this sample task of the consuming random variable is constrained.
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													// Mark that the sample has observed constrained data.
													state.constrainedFlag$sample57[((var55 - 0) / 1)] = true;
													{
														{
															{
																{
																	{
																		// Increment the sample counter with the value sampled by sample task 159 of random
																		// variable var154
																		cv$countLocal[(state.events[i$var136][j$var149] - 1)] = (cv$countLocal[(state.events[i$var136][j$var149] - 1)] + 1.0);
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
			if(state.constrainedFlag$sample57[((var55 - 0) / 1)])
				// Calculate the new sample value
				// 
				// Calculate a new sample value and write it into cv$targetLocal.
				Conjugates.sampleConjugateDirichletCategorical(RNG$, state.v2, cv$countLocal, cv$targetLocal, state.noEvents);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 78 drawn from Dirichlet 74. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample78() {
		if(true) {
			state.constrainedFlag$sample78 = false;
			
			// A reference local to the function for the sample variable.
			double[] cv$targetLocal = state.weights;
			
			// A local reference to the scratch space.
			double[] cv$countLocal = scratch.cv$var75$countGlobal;
			
			// Get the length of the array
			int cv$arrayLength = state.noStates;
			
			// Initialize the array values to 0.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				// Processing random variable 76.
				{
					{
						{
							// Processing sample task 80 of consumer random variable null.
							{
								{
									// Flag recording if this sample task of the consuming random variable is constrained.
									boolean cv$sampleConstrained = (state.fixedFlag$sample80 || state.constrainedFlag$sample80);
									if(cv$sampleConstrained) {
										// Mark that the sample has observed constrained data.
										state.constrainedFlag$sample78 = true;
										{
											{
												{
													{
														{
															// Increment the sample counter with the value sampled by sample task 80 of random
															// variable var76
															cv$countLocal[state.initialState] = (cv$countLocal[state.initialState] + 1.0);
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
			if(state.constrainedFlag$sample78)
				// Calculate the new sample value
				// 
				// Calculate a new sample value and write it into cv$targetLocal.
				Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, cv$countLocal, cv$targetLocal, state.noStates);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 80 drawn from Categorical 76. Inference was performed using variable
	// marginalization.
	private final void inferSample80() {
		if(true) {
			state.constrainedFlag$sample80 = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, state.noStates);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = scratch.cv$var77$stateProbabilityGlobal;
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
				state.initialState = cv$currentValue;
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.weights[cv$currentValue])) && (state.weights[cv$currentValue] <= 1.0))?Math.log(state.weights[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 91.
					{
						{
							{
								for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1) {
									int traceTempVariable$initialState$1_2 = cv$currentValue;
									
									// Processing sample task 95 of consumer random variable null.
									{
										{
											// Flag recording if this sample task of the consuming random variable is constrained.
											boolean cv$sampleConstrained = (state.fixedFlag$sample95 || state.constrainedFlag$sample95[((i$var87 - 0) / 1)]);
											if(cv$sampleConstrained) {
												// Mark that the sample has observed constrained data.
												state.constrainedFlag$sample80 = true;
												
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
																	double[] var90 = state.m[traceTempVariable$initialState$1_2];
																	
																	// Record the probability of sample task 95 generating output with current configuration.
																	if(((Math.log(1.0) + ((((((0.0 <= state.st[i$var87][0]) && (state.st[i$var87][0] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var90[state.st[i$var87][0]])) && (var90[state.st[i$var87][0]] <= 1.0))?Math.log(var90[state.st[i$var87][0]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.st[i$var87][0]) && (state.st[i$var87][0] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var90[state.st[i$var87][0]])) && (var90[state.st[i$var87][0]] <= 1.0))?Math.log(var90[state.st[i$var87][0]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.st[i$var87][0]) && (state.st[i$var87][0] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var90[state.st[i$var87][0]])) && (var90[state.st[i$var87][0]] <= 1.0))?Math.log(var90[state.st[i$var87][0]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.st[i$var87][0]) && (state.st[i$var87][0] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var90[state.st[i$var87][0]])) && (var90[state.st[i$var87][0]] <= 1.0))?Math.log(var90[state.st[i$var87][0]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.st[i$var87][0]) && (state.st[i$var87][0] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var90[state.st[i$var87][0]])) && (var90[state.st[i$var87][0]] <= 1.0))?Math.log(var90[state.st[i$var87][0]]):Double.NEGATIVE_INFINITY)));
																	}
																	
																	// Recorded the probability of reaching sample task 95 with the current configuration.
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
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample80) {
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
				
				// Write out the new value of the sample.
				state.initialState = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 95 drawn from Categorical 91. Inference was performed using variable
	// marginalization.
	private final void inferSample95(int i$var87, int threadID$cv$i$var87, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample95[((i$var87 - 0) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, state.noStates);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = scratch.cv$var92$stateProbabilityGlobal[threadID$cv$i$var87];
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
				int var92 = cv$currentValue;
				
				// Guards to ensure that st is only updated when there is a valid path.
				{
					{
						{
							int[] var88 = state.st[i$var87];
							var88[0] = cv$currentValue;
						}
					}
				}
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// Constructing a random variable input for use later.
					double[] var90 = state.m[state.initialState];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var90[cv$currentValue])) && (var90[cv$currentValue] <= 1.0))?Math.log(var90[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 122.
					{
						// Looking for a path between Sample 95 and consumer Categorical 122.
						{
							{
								int traceTempVariable$var120$2_1 = cv$currentValue;
								for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
									if((i$var87 == i$var104)) {
										for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
											if((0 == (j$var115 - 1))) {
												// Processing sample task 126 of consumer random variable null.
												{
													{
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = (state.fixedFlag$sample126 || state.constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)]);
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															state.constrainedFlag$sample95[((i$var87 - 0) / 1)] = true;
															
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
																				double[] var121 = state.m[traceTempVariable$var120$2_1];
																				
																				// Record the probability of sample task 126 generating output with current configuration.
																				if(((Math.log(1.0) + ((((((0.0 <= state.st[i$var104][j$var115]) && (state.st[i$var104][j$var115] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var121[state.st[i$var104][j$var115]])) && (var121[state.st[i$var104][j$var115]] <= 1.0))?Math.log(var121[state.st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.st[i$var104][j$var115]) && (state.st[i$var104][j$var115] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var121[state.st[i$var104][j$var115]])) && (var121[state.st[i$var104][j$var115]] <= 1.0))?Math.log(var121[state.st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.st[i$var104][j$var115]) && (state.st[i$var104][j$var115] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var121[state.st[i$var104][j$var115]])) && (var121[state.st[i$var104][j$var115]] <= 1.0))?Math.log(var121[state.st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.st[i$var104][j$var115]) && (state.st[i$var104][j$var115] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var121[state.st[i$var104][j$var115]])) && (var121[state.st[i$var104][j$var115]] <= 1.0))?Math.log(var121[state.st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.st[i$var104][j$var115]) && (state.st[i$var104][j$var115] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var121[state.st[i$var104][j$var115]])) && (var121[state.st[i$var104][j$var115]] <= 1.0))?Math.log(var121[state.st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)));
																				}
																				
																				// Recorded the probability of reaching sample task 126 with the current configuration.
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
					
					// Processing random variable 154.
					{
						// Looking for a path between Sample 95 and consumer Categorical 154.
						{
							{
								int traceTempVariable$var152$5_1 = cv$currentValue;
								for(int i$var136 = 0; i$var136 < state.samples; i$var136 += 1) {
									if((i$var87 == i$var136)) {
										for(int j$var149 = 1; j$var149 < state.length$eventsMeasured[i$var136]; j$var149 += 1) {
											if((0 == j$var149)) {
												// Processing sample task 159 of consumer random variable null.
												{
													{
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = true;
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															state.constrainedFlag$sample95[((i$var87 - 0) / 1)] = true;
															
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
																				double[] var153 = state.bias[traceTempVariable$var152$5_1];
																				
																				// Record the probability of sample task 159 generating output with current configuration.
																				if(((Math.log(1.0) + ((((((0.0 <= (state.events[i$var136][j$var149] - 1)) && ((state.events[i$var136][j$var149] - 1) < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var136][j$var149] - 1)])) && (var153[(state.events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= (state.events[i$var136][j$var149] - 1)) && ((state.events[i$var136][j$var149] - 1) < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var136][j$var149] - 1)])) && (var153[(state.events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= (state.events[i$var136][j$var149] - 1)) && ((state.events[i$var136][j$var149] - 1) < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var136][j$var149] - 1)])) && (var153[(state.events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= (state.events[i$var136][j$var149] - 1)) && ((state.events[i$var136][j$var149] - 1) < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var136][j$var149] - 1)])) && (var153[(state.events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= (state.events[i$var136][j$var149] - 1)) && ((state.events[i$var136][j$var149] - 1) < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var136][j$var149] - 1)])) && (var153[(state.events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)));
																				}
																				
																				// Recorded the probability of reaching sample task 159 with the current configuration.
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
			if(state.constrainedFlag$sample95[((i$var87 - 0) / 1)]) {
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
				int var92 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates);
				
				// Guards to ensure that st is only updated when there is a valid path.
				{
					{
						{
							int[] var88 = state.st[i$var87];
							var88[0] = var92;
						}
					}
				}
			}
		}
	}

	// Calculate the probability of the samples represented by sample126 using sampled
	// values.
	private final void logProbabilityValue$sample126() {
		// Determine if we need to calculate the values for sample task 126 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample126) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						{
							// The sample value to calculate the probability of generating
							int cv$sampleValue = state.st[i$var104][j$var115];
							{
								{
									double[] var121 = state.m[state.st[i$var104][(j$var115 - 1)]];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var121[cv$sampleValue])) && (var121[cv$sampleValue] <= 1.0))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
									
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
					state.logProbability$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = cv$sampleProbability;
				}
			}
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample126)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample126 = ((state.fixedFlag$sample126 && state.fixedFlag$sample42) && state.fixedFlag$sample95);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample126)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample159 using sampled
	// values.
	private final void logProbabilityValue$sample159() {
		// Determine if we need to calculate the values for sample task 159 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample159) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var136 = 0; i$var136 < state.samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < state.length$eventsMeasured[i$var136]; j$var149 += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						{
							// The sample value to calculate the probability of generating
							int cv$sampleValue = (state.events[i$var136][j$var149] - 1);
							{
								{
									double[] var153 = state.bias[state.st[i$var136][j$var149]];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[cv$sampleValue])) && (var153[cv$sampleValue] <= 1.0))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY));
									
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
					state.logProbability$sample159[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = cv$sampleProbability;
				}
			}
			
			// Update the variable probability
			state.logProbability$events = (state.logProbability$events + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample159 = ((state.fixedFlag$sample57 && state.fixedFlag$sample95) && state.fixedFlag$sample126);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var136 = 0; i$var136 < state.samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < state.length$eventsMeasured[i$var136]; j$var149 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample159[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			
			// Update the variable probability
			state.logProbability$events = (state.logProbability$events + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample42 using sampled
	// values.
	private final void logProbabilityValue$sample42() {
		// Determine if we need to calculate the values for sample task 42 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample42) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var41 = 0; var41 < state.noStates; var41 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						double[] cv$sampleValue = state.m[var41];
						{
							{
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, state.v, state.noStates));
								
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
			state.logProbability$var42 = cv$sampleAccumulator;
			
			// Update the variable probability
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample42)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample42 = state.fixedFlag$sample42;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var41 = 0; var41 < state.noStates; var41 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var42;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample42)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample57 using sampled
	// values.
	private final void logProbabilityValue$sample57() {
		// Determine if we need to calculate the values for sample task 57 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample57) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var55 = 0; var55 < state.noStates; var55 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						double[] cv$sampleValue = state.bias[var55];
						{
							{
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, state.v2, state.noEvents));
								
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
			state.logProbability$var56 = cv$sampleAccumulator;
			
			// Update the variable probability
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample57 = state.fixedFlag$sample57;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var55 = 0; var55 < state.noStates; var55 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var56;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample57)
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
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				{
					// The sample value to calculate the probability of generating
					double[] cv$sampleValue = state.weights;
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, state.v, state.noStates));
							
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
			state.logProbability$weights = cv$sampleProbability;
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample78)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample78 = state.fixedFlag$sample78;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$weights;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample78)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample80 using sampled
	// values.
	private final void logProbabilityValue$sample80() {
		// Determine if we need to calculate the values for sample task 80 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample80) {
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
					int cv$sampleValue = state.initialState;
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.weights[cv$sampleValue])) && (state.weights[cv$sampleValue] <= 1.0))?Math.log(state.weights[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			state.logProbability$initialState = cv$sampleProbability;
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample80)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample80 = (state.fixedFlag$sample80 && state.fixedFlag$sample78);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$initialState;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample80)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample95 using sampled
	// values.
	private final void logProbabilityValue$sample95() {
		// Determine if we need to calculate the values for sample task 95 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample95) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = state.st[i$var87][0];
						{
							{
								double[] var90 = state.m[state.initialState];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var90[cv$sampleValue])) && (var90[cv$sampleValue] <= 1.0))?Math.log(var90[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
				state.logProbability$sample95[((i$var87 - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample95)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample95 = ((state.fixedFlag$sample95 && state.fixedFlag$sample42) && state.fixedFlag$sample80);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample95[((i$var87 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample95)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
						double[] var42 = state.m[var41];
						if(!state.fixedFlag$sample42)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var42);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
						double[] var56 = state.bias[var55];
						if(!state.fixedFlag$sample57)
							DistributionSampling.sampleDirichlet(RNG$1, state.v2, state.noEvents, var56);
					}
			}
		);
		if(!state.fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.weights);
		if(!state.fixedFlag$sample80)
			state.initialState = DistributionSampling.sampleCategorical(state.RNG$, state.weights, state.noStates);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
						int[] var88 = state.st[i$var87];
						if(!state.fixedFlag$sample95)
							var88[0] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.initialState], state.noStates);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
						int[] var116 = state.st[i$var104];
						for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
							if(!state.fixedFlag$sample126)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.st[i$var104][(j$var115 - 1)]], state.noStates);
						}
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$index$i$var136, int forEnd$index$i$var136, int threadID$index$i$var136, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var136 = forStart$index$i$var136; index$i$var136 < forEnd$index$i$var136; index$i$var136 += 1) {
						int i$var136 = index$i$var136;
						int threadID$i$var136 = threadID$index$i$var136;
						int[] var150 = state.events[i$var136];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 1, state.length$eventsMeasured[i$var136], 1,
							(int forStart$j$var149, int forEnd$j$var149, int threadID$j$var149, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var149 = forStart$j$var149; j$var149 < forEnd$j$var149; j$var149 += 1)
										var150[j$var149] = (DistributionSampling.sampleCategorical(RNG$2, state.bias[state.st[i$var136][j$var149]], state.noEvents) + 1);
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
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
						double[] var42 = state.m[var41];
						if(!state.fixedFlag$sample42)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var42);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
						double[] var56 = state.bias[var55];
						if(!state.fixedFlag$sample57)
							DistributionSampling.sampleDirichlet(RNG$1, state.v2, state.noEvents, var56);
					}
			}
		);
		if(!state.fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.weights);
		if(!state.fixedFlag$sample80)
			state.initialState = DistributionSampling.sampleCategorical(state.RNG$, state.weights, state.noStates);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
						int[] var88 = state.st[i$var87];
						if(!state.fixedFlag$sample95)
							var88[0] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.initialState], state.noStates);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
						int[] var116 = state.st[i$var104];
						for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
							if(!state.fixedFlag$sample126)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.st[i$var104][(j$var115 - 1)]], state.noStates);
						}
					}
			}
		);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
						double[] var42 = state.m[var41];
						if(!state.fixedFlag$sample42)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var42);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
						double[] var56 = state.bias[var55];
						if(!state.fixedFlag$sample57)
							DistributionSampling.sampleDirichlet(RNG$1, state.v2, state.noEvents, var56);
					}
			}
		);
		if(!state.fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.weights);
		if(!state.fixedFlag$sample80)
			state.initialState = DistributionSampling.sampleCategorical(state.RNG$, state.weights, state.noStates);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
						int[] var88 = state.st[i$var87];
						if(!state.fixedFlag$sample95)
							var88[0] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.initialState], state.noStates);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
						int[] var116 = state.st[i$var104];
						for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
							if(!state.fixedFlag$sample126)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.st[i$var104][(j$var115 - 1)]], state.noStates);
						}
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$index$i$var136, int forEnd$index$i$var136, int threadID$index$i$var136, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var136 = forStart$index$i$var136; index$i$var136 < forEnd$index$i$var136; index$i$var136 += 1) {
						int i$var136 = index$i$var136;
						int threadID$i$var136 = threadID$index$i$var136;
						int[] var150 = state.events[i$var136];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 1, state.length$eventsMeasured[i$var136], 1,
							(int forStart$j$var149, int forEnd$j$var149, int threadID$j$var149, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var149 = forStart$j$var149; j$var149 < forEnd$j$var149; j$var149 += 1)
										var150[j$var149] = (DistributionSampling.sampleCategorical(RNG$2, state.bias[state.st[i$var136][j$var149]], state.noEvents) + 1);
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
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
						double[] var42 = state.m[var41];
						if(!state.fixedFlag$sample42)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var42);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
						double[] var56 = state.bias[var55];
						if(!state.fixedFlag$sample57)
							DistributionSampling.sampleDirichlet(RNG$1, state.v2, state.noEvents, var56);
					}
			}
		);
		if(!state.fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.weights);
		if(!state.fixedFlag$sample80)
			state.initialState = DistributionSampling.sampleCategorical(state.RNG$, state.weights, state.noStates);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
						int[] var88 = state.st[i$var87];
						if(!state.fixedFlag$sample95)
							var88[0] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.initialState], state.noStates);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
						int[] var116 = state.st[i$var104];
						for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
							if(!state.fixedFlag$sample126)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.st[i$var104][(j$var115 - 1)]], state.noStates);
						}
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
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
						double[] var42 = state.m[var41];
						if(!state.fixedFlag$sample42)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var42);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
						double[] var56 = state.bias[var55];
						if(!state.fixedFlag$sample57)
							DistributionSampling.sampleDirichlet(RNG$1, state.v2, state.noEvents, var56);
					}
			}
		);
		if(!state.fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.weights);
		if(!state.fixedFlag$sample80)
			state.initialState = DistributionSampling.sampleCategorical(state.RNG$, state.weights, state.noStates);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
						int[] var88 = state.st[i$var87];
						if(!state.fixedFlag$sample95)
							var88[0] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.initialState], state.noStates);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
						int[] var116 = state.st[i$var104];
						for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
							if(!state.fixedFlag$sample126)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.st[i$var104][(j$var115 - 1)]], state.noStates);
						}
					}
			}
		);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
							if(!state.fixedFlag$sample42)
								inferSample42(var41, threadID$var41, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
							if(!state.fixedFlag$sample57)
								inferSample57(var55, threadID$var55, RNG$1);
						}
				}
			);
			if(!state.fixedFlag$sample78)
				inferSample78();
			if(!state.fixedFlag$sample80)
				inferSample80();
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.samples, 1,
				(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
							if(!state.fixedFlag$sample95)
								inferSample95(i$var87, threadID$i$var87, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.samples, 1,
				(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
							for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
								if(!state.fixedFlag$sample126)
									inferSample126(i$var104, j$var115, threadID$i$var104, RNG$1);
							}
						}
				}
			);
		}
		// Infer the samples in reverse chronological order.
		else {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.samples, 1,
				(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
							for(int j$var115 = (state.length$eventsMeasured[i$var104] - ((((state.length$eventsMeasured[i$var104] - 1) - 1) % 1) + 1)); j$var115 >= ((1 - 1) + 1); j$var115 -= 1) {
								if(!state.fixedFlag$sample126)
									inferSample126(i$var104, j$var115, threadID$i$var104, RNG$1);
							}
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.samples, 1,
				(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
							if(!state.fixedFlag$sample95)
								inferSample95(i$var87, threadID$i$var87, RNG$1);
						}
				}
			);
			if(!state.fixedFlag$sample80)
				inferSample80();
			if(!state.fixedFlag$sample78)
				inferSample78();
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
							if(!state.fixedFlag$sample57)
								inferSample57(var55, threadID$var55, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
							if(!state.fixedFlag$sample42)
								inferSample42(var41, threadID$var41, RNG$1);
						}
				}
			);
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
						if(!state.constrainedFlag$sample42[((var41 - 0) / 1)])
							drawValueSample42(var41, threadID$var41, RNG$1);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
						if(!state.constrainedFlag$sample57[((var55 - 0) / 1)])
							drawValueSample57(var55, threadID$var55, RNG$1);
					}
			}
		);
		if(!state.constrainedFlag$sample78)
			drawValueSample78();
		if(!state.constrainedFlag$sample80)
			drawValueSample80();
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
						if(!state.constrainedFlag$sample95[((i$var87 - 0) / 1)])
							drawValueSample95(i$var87, threadID$i$var87, RNG$1);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
						for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
							if(!state.constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)])
								drawValueSample126(i$var104, j$var115, threadID$i$var104, RNG$1);
						}
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
		if(!state.fixedProbFlag$sample42)
			state.logProbability$var42 = Double.NaN;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample57)
			state.logProbability$var56 = Double.NaN;
		if(!state.fixedProbFlag$sample78)
			state.logProbability$weights = Double.NaN;
		if(!state.fixedProbFlag$sample80)
			state.logProbability$initialState = Double.NaN;
		state.logProbability$st = 0.0;
		if(!state.fixedProbFlag$sample95) {
			for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1)
				state.logProbability$sample95[((i$var87 - 0) / 1)] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample126) {
			for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1)
					state.logProbability$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = Double.NaN;
			}
		}
		state.logProbability$events = 0.0;
		if(!state.fixedProbFlag$sample159) {
			for(int i$var136 = 0; i$var136 < state.samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < state.length$eventsMeasured[i$var136]; j$var149 += 1)
					state.logProbability$sample159[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = Double.NaN;
			}
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		for(int var14 = 0; var14 < state.noStates; var14 += 1)
			state.v[var14] = 0.1;
		for(int var27 = 0; var27 < state.noEvents; var27 += 1)
			state.v2[var27] = 0.1;
		state.samples = state.length$eventsMeasured.length;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample95$1 = 0; index$constrainedFlag$sample95$1 < state.constrainedFlag$sample95.length; index$constrainedFlag$sample95$1 += 1)
			state.constrainedFlag$sample95[index$constrainedFlag$sample95$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample126$1 = 0; index$constrainedFlag$sample126$1 < state.constrainedFlag$sample126.length; index$constrainedFlag$sample126$1 += 1) {
			boolean[] cv$constrainedFlag$sample126$1 = state.constrainedFlag$sample126[index$constrainedFlag$sample126$1];
			for(int index$constrainedFlag$sample126$2 = 0; index$constrainedFlag$sample126$2 < cv$constrainedFlag$sample126$1.length; index$constrainedFlag$sample126$2 += 1)
				cv$constrainedFlag$sample126$1[index$constrainedFlag$sample126$2] = true;
		}
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample42$1 = 0; index$constrainedFlag$sample42$1 < state.constrainedFlag$sample42.length; index$constrainedFlag$sample42$1 += 1)
			state.constrainedFlag$sample42[index$constrainedFlag$sample42$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample57$1 = 0; index$constrainedFlag$sample57$1 < state.constrainedFlag$sample57.length; index$constrainedFlag$sample57$1 += 1)
			state.constrainedFlag$sample57[index$constrainedFlag$sample57$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample42)
			logProbabilityValue$sample42();
		if(state.fixedFlag$sample57)
			logProbabilityValue$sample57();
		if(state.fixedFlag$sample78)
			logProbabilityValue$sample78();
		if(state.fixedFlag$sample80)
			logProbabilityValue$sample80();
		if(state.fixedFlag$sample95)
			logProbabilityValue$sample95();
		if(state.fixedFlag$sample126)
			logProbabilityValue$sample126();
		logProbabilityValue$sample159();
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
		logProbabilityValue$sample57();
		logProbabilityValue$sample78();
		logProbabilityValue$sample80();
		logProbabilityValue$sample95();
		logProbabilityValue$sample126();
		logProbabilityValue$sample159();
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
		logProbabilityValue$sample57();
		logProbabilityValue$sample78();
		logProbabilityValue$sample80();
		logProbabilityValue$sample95();
		logProbabilityValue$sample126();
		logProbabilityValue$sample159();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Deep copy between arrays
		int[][] cv$source1 = state.eventsMeasured;
		int[][] cv$target1 = state.events;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = cv$source1[cv$index1];
			int[] cv$target2 = cv$target1[cv$index1];
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
		     + "model HMM_Mk2(int[][] eventsMeasured, int noStates, int noEvents) {\n"
		     + "        \n"
		     + "        // Construct arrays describing the probability of a move from 1 state to another.\n"
		     + "        double[] v = new double[noStates] <~ 0.1;\n"
		     + "        double[] v2 = new double[noEvents] <~ 0.1;\n"
		     + "        double[][] m = dirichlet(v).sample(noStates);\n"
		     + "        \n"
		     + "        // Construct the bias for each webpage.\n"
		     + "        double[][] bias = dirichlet(v2).sample(noStates);\n"
		     + "\n"
		     + "        // Determine how many samples the model will need to produce.\n"
		     + "        int samples = eventsMeasured.length;\n"
		     + "        \n"
		     + "        // Allocate space for the state, i.e. which webpage we are going to trigger an event on.\n"
		     + "        int[][] st = new int[samples][];\n"
		     + "        for(int i:[0 .. samples)){\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            st[i] = new int[streamLength];\n"
		     + "        }\n"
		     + "\n"
		     + "        // Set the initial state by sampling from a categorical with learnt weightings.\n"
		     + "        double[] weights = dirichlet(v).sample();\n"
		     + "        int initialState = categorical(weights).sample();\n"
		     + "        for(int i:[0..samples)) {\n"
		     + "            st[i][0] = categorical(m[initialState]).sample();\n"
		     + "        }\n"
		     + "\n"
		     + "        //Determine the remaining states based on the previous state.\n"
		     + "        for(int i:[0 .. samples)){\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            for(int j:[1..streamLength)){\n"
		     + "                st[i][j] = categorical(m[st[i][j-1]]).sample();\n"
		     + "            }\n"
		     + "        }\n"
		     + "            \n"
		     + "        //Generate each event.\n"
		     + "        int[][] events = new int[samples][];\n"
		     + "        for(int i:[0 .. samples)) {\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            events[i] = new int[streamLength];\n"
		     + "            for(int j:[1..streamLength)){\n"
		     + "                events[i][j] = categorical(bias[st[i][j]]).sample() + 1;\n"
		     + "            }\n"
		     + "        }\n"
		     + "\n"
		     + "        //Tie the values of the flips to the values we have measured.\n"
		     + "        events.observe(eventsMeasured);\n"
		     + "}\n"
		     + "";
	}
}