package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.ParallelMK4$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.ParallelMK4.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class ParallelMK4$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {}
	}


	public ParallelMK4$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample61
	private final void drawValueSample61(int i, int j) {
		double[] var55 = state.indirection1[i];
		var55[j] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
		
		// Guards to ensure that indirection2 is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 61 and consumer double[][] 87.
		{
			{
				for(int k = 0; k < state.length$observed; k += 1) {
					if((i == k)) {
						for(int l = 0; l < 10; l += 1) {
							if((j == l)) {
								{
									double[] var83 = state.indirection2[k];
									var83[l] = state.indirection1[k][l];
								}
							}
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 61 drawn from Uniform 58. Inference was performed using Metropolis-Hastings.
	private final void inferSample61(int i, int j) {
		if(true) {
			state.constrainedFlag$sample61[((i - 0) / 1)][((j - 0) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// Metropolis-Hastings
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// The original value of the sample
			double cv$originalValue = state.indirection1[i][j];
			
			// The probability of the random variable generating the originally sampled value
			double cv$originalProbability = 0.0;
			
			// Calculate a proposed variance.
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			
			// Ensure the variance is at least 0.01
			if((cv$var < 0.01))
				cv$var = 0.01;
			
			// The proposed new value for the sample
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			
			// The probability of the random variable generating the new sample value.
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample61[((i - 0) / 1)][((j - 0) / 1)] || (cv$valuePos == 0))) {
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
						double var59 = cv$proposedValue;
						
						// Guards to ensure that indirection1 is only updated when there is a valid path.
						{
							{
								{
									double[] var55 = state.indirection1[i];
									var55[j] = cv$currentValue;
								}
							}
						}
						
						// Guards to ensure that indirection2 is only updated when there is a valid path.
						// 
						// Looking for a path between Sample 61 and consumer double[][] 87.
						{
							{
								for(int k = 0; k < state.length$observed; k += 1) {
									if((i == k)) {
										for(int l = 0; l < 10; l += 1) {
											if((j == l)) {
												{
													double[] var83 = state.indirection2[k];
													var83[l] = state.indirection1[k][l];
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
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < 1.0))?(-Math.log((1.0 - 0.0))):Double.NEGATIVE_INFINITY));
						
						// Processing random variable 100.
						{
							// Looking for a path between Sample 61 and consumer Categorical 100.
							{
								{
									double traceTempVariable$var85$3_1 = cv$currentValue;
									for(int k = 0; k < state.length$observed; k += 1) {
										if((i == k)) {
											for(int l = 0; l < 10; l += 1) {
												if((j == l)) {
													for(int m = 0; m < state.length$observed; m += 1) {
														if((k == m)) {
															// Processing sample task 103 of consumer random variable null.
															{
																{
																	// Flag recording if this sample task of the consuming random variable is constrained.
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		// Mark that the sample has observed constrained data.
																		state.constrainedFlag$sample61[((i - 0) / 1)][((j - 0) / 1)] = true;
																		
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
																							double[] var99 = state.indirection2[m];
																							
																							// Record the probability of sample task 103 generating output with current configuration.
																							if(((Math.log(1.0) + ((((((0.0 <= state.generated[m]) && (state.generated[m] < 10)) && (0 < 10)) && (0.0 <= var99[state.generated[m]])) && (var99[state.generated[m]] <= 1.0))?Math.log(var99[state.generated[m]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.generated[m]) && (state.generated[m] < 10)) && (0 < 10)) && (0.0 <= var99[state.generated[m]])) && (var99[state.generated[m]] <= 1.0))?Math.log(var99[state.generated[m]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.generated[m]) && (state.generated[m] < 10)) && (0 < 10)) && (0.0 <= var99[state.generated[m]])) && (var99[state.generated[m]] <= 1.0))?Math.log(var99[state.generated[m]]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.generated[m]) && (state.generated[m] < 10)) && (0 < 10)) && (0.0 <= var99[state.generated[m]])) && (var99[state.generated[m]] <= 1.0))?Math.log(var99[state.generated[m]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.generated[m]) && (state.generated[m] < 10)) && (0 < 10)) && (0.0 <= var99[state.generated[m]])) && (var99[state.generated[m]] <= 1.0))?Math.log(var99[state.generated[m]]):Double.NEGATIVE_INFINITY)));
																							}
																							
																							// Recorded the probability of reaching sample task 103 with the current configuration.
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
							double var59 = cv$originalValue;
							
							// Guards to ensure that indirection1 is only updated when there is a valid path.
							{
								{
									{
										double[] var55 = state.indirection1[i];
										var55[j] = var59;
									}
								}
							}
							
							// Guards to ensure that indirection2 is only updated when there is a valid path.
							// 
							// Looking for a path between Sample 61 and consumer double[][] 87.
							{
								{
									for(int k = 0; k < state.length$observed; k += 1) {
										if((i == k)) {
											for(int l = 0; l < 10; l += 1) {
												if((j == l)) {
													{
														double[] var83 = state.indirection2[k];
														var83[l] = state.indirection1[k][l];
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

	// Calculate the probability of the samples represented by sample103 using sampled
	// values.
	private final void logProbabilityValue$sample103() {
		// Determine if we need to calculate the values for sample task 103 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample103) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int m = 0; m < state.length$observed; m += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = state.generated[m];
						{
							{
								double[] var99 = state.indirection2[m];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < 10)) && (0 < 10)) && (0.0 <= var99[cv$sampleValue])) && (var99[cv$sampleValue] <= 1.0))?Math.log(var99[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
				state.logProbability$sample103[((m - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			state.logProbability$generated = (state.logProbability$generated + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample103 = state.fixedFlag$sample61;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int m = 0; m < state.length$observed; m += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample103[((m - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			
			// Update the variable probability
			state.logProbability$generated = (state.logProbability$generated + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample61 using sampled
	// values.
	private final void logProbabilityValue$sample61() {
		// Determine if we need to calculate the values for sample task 61 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample61) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i = 0; i < state.length$observed; i += 1) {
				for(int j = 0; j < 10; j += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						{
							// The sample value to calculate the probability of generating
							double cv$sampleValue = state.indirection1[i][j];
							{
								{
									double var56 = 0.0;
									double var57 = 1.0;
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + (((var56 <= cv$sampleValue) && (cv$sampleValue < var57))?(-Math.log((var57 - var56))):Double.NEGATIVE_INFINITY));
									
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
					state.logProbability$sample61[((i - 0) / 1)][((j - 0) / 1)] = cv$sampleProbability;
					
					// Guard to ensure that indirection2 is only updated once for this probability.
					boolean cv$guard$indirection2 = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					// 
					// Looking for a path between Sample 61 and consumer double[][] 87.
					{
						{
							for(int k = 0; k < state.length$observed; k += 1) {
								if((i == k)) {
									for(int l = 0; l < 10; l += 1) {
										if((j == l)) {
											// If the probability of the variable has not already been updated
											if(!cv$guard$indirection2) {
												// Set the guard so the update is only applied once.
												cv$guard$indirection2 = true;
												
												// Update the variable probability
												state.logProbability$indirection2 = (state.logProbability$indirection2 + cv$sampleProbability);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Update the variable probability
			state.logProbability$indirection1 = (state.logProbability$indirection1 + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample61)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample61 = state.fixedFlag$sample61;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i = 0; i < state.length$observed; i += 1) {
				for(int j = 0; j < 10; j += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample61[((i - 0) / 1)][((j - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					
					// Guard to ensure that indirection2 is only updated once for this probability.
					boolean cv$guard$indirection2 = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					// 
					// Looking for a path between Sample 61 and consumer double[][] 87.
					{
						{
							for(int k = 0; k < state.length$observed; k += 1) {
								if((i == k)) {
									for(int l = 0; l < 10; l += 1) {
										if((j == l)) {
											// If the probability of the variable has not already been updated
											if(!cv$guard$indirection2) {
												// Set the guard so the update is only applied once.
												cv$guard$indirection2 = true;
												
												// Update the variable probability
												state.logProbability$indirection2 = (state.logProbability$indirection2 + cv$sampleValue);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Update the variable probability
			state.logProbability$indirection1 = (state.logProbability$indirection1 + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample61)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		for(int i = 0; i < state.length$observed; i += 1) {
			double[] var55 = state.indirection1[i];
			for(int j = 0; j < 10; j += 1) {
				if(!state.fixedFlag$sample61)
					var55[j] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			}
		}
		for(int k = 0; k < state.length$observed; k += 1) {
			double[] var83 = state.indirection2[k];
			for(int l = 0; l < 10; l += 1) {
				if(!state.fixedFlag$sample61)
					var83[l] = state.indirection1[k][l];
			}
		}
		for(int m = 0; m < state.length$observed; m += 1)
			state.generated[m] = DistributionSampling.sampleCategorical(state.RNG$, state.indirection2[m], 10);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int i = 0; i < state.length$observed; i += 1) {
			double[] var55 = state.indirection1[i];
			for(int j = 0; j < 10; j += 1) {
				if(!state.fixedFlag$sample61)
					var55[j] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			}
		}
		for(int k = 0; k < state.length$observed; k += 1) {
			double[] var83 = state.indirection2[k];
			for(int l = 0; l < 10; l += 1)
				var83[l] = state.indirection1[k][l];
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		for(int i = 0; i < state.length$observed; i += 1) {
			double[] var55 = state.indirection1[i];
			for(int j = 0; j < 10; j += 1) {
				if(!state.fixedFlag$sample61)
					var55[j] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			}
		}
		for(int k = 0; k < state.length$observed; k += 1) {
			double[] var83 = state.indirection2[k];
			for(int l = 0; l < 10; l += 1)
				var83[l] = state.indirection1[k][l];
		}
		for(int m = 0; m < state.length$observed; m += 1)
			state.generated[m] = DistributionSampling.sampleCategorical(state.RNG$, state.indirection2[m], 10);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int i = 0; i < state.length$observed; i += 1) {
			double[] var55 = state.indirection1[i];
			for(int j = 0; j < 10; j += 1) {
				if(!state.fixedFlag$sample61)
					var55[j] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			}
		}
		for(int k = 0; k < state.length$observed; k += 1) {
			double[] var83 = state.indirection2[k];
			for(int l = 0; l < 10; l += 1) {
				if(!state.fixedFlag$sample61)
					var83[l] = state.indirection1[k][l];
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		for(int i = 0; i < state.length$observed; i += 1) {
			double[] var55 = state.indirection1[i];
			for(int j = 0; j < 10; j += 1) {
				if(!state.fixedFlag$sample61)
					var55[j] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			}
		}
		for(int k = 0; k < state.length$observed; k += 1) {
			double[] var83 = state.indirection2[k];
			for(int l = 0; l < 10; l += 1)
				var83[l] = state.indirection1[k][l];
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			for(int i = 0; i < state.length$observed; i += 1) {
				for(int j = 0; j < 10; j += 1) {
					if(!state.fixedFlag$sample61)
						inferSample61(i, j);
				}
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i = (state.length$observed - ((((state.length$observed - 1) - 0) % 1) + 1)); i >= ((0 - 1) + 1); i -= 1) {
				for(int j = (10 - ((((10 - 1) - 0) % 1) + 1)); j >= ((0 - 1) + 1); j -= 1) {
					if(!state.fixedFlag$sample61)
						inferSample61(i, j);
				}
			}
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int i = 0; i < state.length$observed; i += 1) {
			for(int j = 0; j < 10; j += 1) {
				if(!state.constrainedFlag$sample61[((i - 0) / 1)][((j - 0) / 1)])
					drawValueSample61(i, j);
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
		state.logProbability$indirection1 = 0.0;
		state.logProbability$indirection2 = 0.0;
		if(!state.fixedProbFlag$sample61) {
			for(int i = 0; i < state.length$observed; i += 1) {
				for(int j = 0; j < 10; j += 1)
					state.logProbability$sample61[((i - 0) / 1)][((j - 0) / 1)] = Double.NaN;
			}
		}
		state.logProbability$generated = 0.0;
		if(!state.fixedProbFlag$sample103) {
			for(int m = 0; m < state.length$observed; m += 1)
				state.logProbability$sample103[((m - 0) / 1)] = Double.NaN;
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		// Set all the values in the array
		for(int index$constrainedFlag$sample61$1 = 0; index$constrainedFlag$sample61$1 < state.constrainedFlag$sample61.length; index$constrainedFlag$sample61$1 += 1) {
			boolean[] cv$constrainedFlag$sample61$1 = state.constrainedFlag$sample61[index$constrainedFlag$sample61$1];
			for(int index$constrainedFlag$sample61$2 = 0; index$constrainedFlag$sample61$2 < cv$constrainedFlag$sample61$1.length; index$constrainedFlag$sample61$2 += 1)
				cv$constrainedFlag$sample61$1[index$constrainedFlag$sample61$2] = true;
		}
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample61)
			logProbabilityValue$sample61();
		logProbabilityValue$sample103();
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
		logProbabilityValue$sample61();
		logProbabilityValue$sample103();
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
		logProbabilityValue$sample61();
		logProbabilityValue$sample103();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Deep copy between arrays
		int[] cv$source1 = state.observed;
		int[] cv$target1 = state.generated;
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
		for(int k = 0; k < state.length$observed; k += 1) {
			double[] var83 = state.indirection2[k];
			for(int l = 0; l < 10; l += 1)
				var83[l] = state.indirection1[k][l];
		}
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model ParallelMK4(int[] observed) {\n"
		     + "    int[] generated = new int[observed.length];\n"
		     + "    double[][] indirection1 = new double[observed.length][10];\n"
		     + "    double[][] indirection2 = new double[observed.length][10];\n"
		     + "\n"
		     + "    for(int i=0; i<observed.length; i++) {\n"
		     + "        for(int j=0; j<10; j++) {\n"
		     + "            indirection1[i][j] = uniform(0.0, 1.0).sample();\n"
		     + "        }\n"
		     + "    }\n"
		     + "    \n"
		     + "    for(int k=0; k<observed.length; k++) {\n"
		     + "        for(int l=0; l<10; l++) {\n"
		     + "            indirection2[k][l] = indirection1[k][l];\n"
		     + "        }\n"
		     + "    }\n"
		     + "    \n"
		     + "    for(int m=0; m<observed.length; m++) {\n"
		     + "        generated[m] = categorical(indirection2[m]).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    generated.observe(observed);\n"
		     + "}";
	}
}