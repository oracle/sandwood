package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK12$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK12.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK12$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK12$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample16
	private final void drawValueSample16() {
		state.var14 = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		
		// Guards to ensure that bias is only updated when there is a valid path.
		{
			{
				if(state.guard1) {
					{
						state.bias = state.var14;
					}
				}
			}
		}
	}

	// Pick a value from the distribution for the unconditioned variable from sample28
	private final void drawValueSample28() {
		state.var26 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 2);
		
		// Guards to ensure that bias is only updated when there is a valid path.
		{
			{
				if((state.guard2 <= 2)) {
					if(!state.guard1) {
						{
							double var34 = state.var26;
							state.bias = var34;
						}
					}
				}
			}
		}
	}

	// Pick a value from the distribution for the unconditioned variable from sample35
	private final void drawValueSample35() {
		state.var33 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 3);
		
		// Guards to ensure that bias is only updated when there is a valid path.
		{
			{
				if(!(state.guard2 <= 2)) {
					if(!state.guard1) {
						{
							double var34 = state.var33;
							state.bias = var34;
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 16 drawn from Beta 13. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void inferSample16() {
		if(true) {
			state.constrainedFlag$sample16 = false;
			
			// Local variable to record the number of true samples.
			int cv$sum = 0;
			
			// Local variable to record the number of samples.
			int cv$count = 0;
			{
				// Processing random variable 36.
				{
					{
						{
							if(state.guard1) {
								// Processing sample task 52 of consumer random variable bernoulli.
								{
									{
										for(int var47 = 0; var47 < state.samples; var47 += 1) {
											// Flag recording if this sample task of the consuming random variable is constrained.
											boolean cv$sampleConstrained = true;
											if(cv$sampleConstrained) {
												// Mark that the sample has observed constrained data.
												state.constrainedFlag$sample16 = true;
												{
													{
														{
															{
																{
																	// Include the value sampled by task 52 from random variable bernoulli.
																	// Increment the number of samples.
																	cv$count = (cv$count + 1);
																	
																	// If the sample value was positive increase the count
																	if(state.flips[var47])
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
			if(state.constrainedFlag$sample16) {
				// Write out the new value of the sample.
				state.var14 = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
				
				// Guards to ensure that bias is only updated when there is a valid path.
				{
					{
						if(state.guard1) {
							{
								state.bias = state.var14;
							}
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 28 drawn from Beta 23. Inference was performed using Metropolis-Hastings.
	private final void inferSample28() {
		if(true) {
			state.constrainedFlag$sample28 = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// Metropolis-Hastings
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// The original value of the sample
			double cv$originalValue = (state.var26 * 2);
			
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
				if((state.constrainedFlag$sample28 || (cv$valuePos == 0))) {
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
						
						// Write out the new sample value.
						state.var26 = (cv$currentValue / 2);
						
						// Guards to ensure that bias is only updated when there is a valid path.
						{
							{
								if((state.guard2 <= 2)) {
									if(!state.guard1) {
										{
											double var34 = state.var26;
											state.bias = var34;
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
						double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, 1.0, 1.0));
						
						// Processing random variable 36.
						{
							{
								{
									if((state.guard2 <= 2)) {
										double traceTempVariable$var34$2_1 = state.var26;
										if(!state.guard1) {
											double traceTempVariable$bias$2_2 = traceTempVariable$var34$2_1;
											
											// Processing sample task 52 of consumer random variable bernoulli.
											{
												{
													for(int var47 = 0; var47 < state.samples; var47 += 1) {
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = true;
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															state.constrainedFlag$sample28 = true;
															
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
																				// Record the probability of sample task 52 generating output with current configuration.
																				if(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$2_2) && (traceTempVariable$bias$2_2 <= 1.0))?Math.log((state.flips[var47]?traceTempVariable$bias$2_2:(1.0 - traceTempVariable$bias$2_2))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$2_2) && (traceTempVariable$bias$2_2 <= 1.0))?Math.log((state.flips[var47]?traceTempVariable$bias$2_2:(1.0 - traceTempVariable$bias$2_2))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$2_2) && (traceTempVariable$bias$2_2 <= 1.0))?Math.log((state.flips[var47]?traceTempVariable$bias$2_2:(1.0 - traceTempVariable$bias$2_2))):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$2_2) && (traceTempVariable$bias$2_2 <= 1.0))?Math.log((state.flips[var47]?traceTempVariable$bias$2_2:(1.0 - traceTempVariable$bias$2_2))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$2_2) && (traceTempVariable$bias$2_2 <= 1.0))?Math.log((state.flips[var47]?traceTempVariable$bias$2_2:(1.0 - traceTempVariable$bias$2_2))):Double.NEGATIVE_INFINITY)));
																				}
																				
																				// Recorded the probability of reaching sample task 52 with the current configuration.
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
							
							// Write out the new sample value.
							state.var26 = (var24 / 2);
							
							// Guards to ensure that bias is only updated when there is a valid path.
							{
								{
									if((state.guard2 <= 2)) {
										if(!state.guard1) {
											{
												double var34 = state.var26;
												state.bias = var34;
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

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 35 drawn from Beta 30. Inference was performed using Metropolis-Hastings.
	private final void inferSample35() {
		if(true) {
			state.constrainedFlag$sample35 = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// Metropolis-Hastings
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// The original value of the sample
			double cv$originalValue = (state.var33 * 3);
			
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
				if((state.constrainedFlag$sample35 || (cv$valuePos == 0))) {
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
						double var31 = cv$proposedValue;
						
						// Write out the new sample value.
						state.var33 = (cv$currentValue / 3);
						
						// Guards to ensure that bias is only updated when there is a valid path.
						{
							{
								if(!(state.guard2 <= 2)) {
									if(!state.guard1) {
										{
											double var34 = state.var33;
											state.bias = var34;
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
						double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, 1.0, 1.0));
						
						// Processing random variable 36.
						{
							{
								{
									if(!(state.guard2 <= 2)) {
										double traceTempVariable$var34$2_1 = state.var33;
										if(!state.guard1) {
											double traceTempVariable$bias$2_2 = traceTempVariable$var34$2_1;
											
											// Processing sample task 52 of consumer random variable bernoulli.
											{
												{
													for(int var47 = 0; var47 < state.samples; var47 += 1) {
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = true;
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															state.constrainedFlag$sample35 = true;
															
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
																				// Record the probability of sample task 52 generating output with current configuration.
																				if(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$2_2) && (traceTempVariable$bias$2_2 <= 1.0))?Math.log((state.flips[var47]?traceTempVariable$bias$2_2:(1.0 - traceTempVariable$bias$2_2))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$2_2) && (traceTempVariable$bias$2_2 <= 1.0))?Math.log((state.flips[var47]?traceTempVariable$bias$2_2:(1.0 - traceTempVariable$bias$2_2))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$2_2) && (traceTempVariable$bias$2_2 <= 1.0))?Math.log((state.flips[var47]?traceTempVariable$bias$2_2:(1.0 - traceTempVariable$bias$2_2))):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$2_2) && (traceTempVariable$bias$2_2 <= 1.0))?Math.log((state.flips[var47]?traceTempVariable$bias$2_2:(1.0 - traceTempVariable$bias$2_2))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$2_2) && (traceTempVariable$bias$2_2 <= 1.0))?Math.log((state.flips[var47]?traceTempVariable$bias$2_2:(1.0 - traceTempVariable$bias$2_2))):Double.NEGATIVE_INFINITY)));
																				}
																				
																				// Recorded the probability of reaching sample task 52 with the current configuration.
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
							double var31 = cv$originalValue;
							
							// Write out the new sample value.
							state.var33 = (var31 / 3);
							
							// Guards to ensure that bias is only updated when there is a valid path.
							{
								{
									if(!(state.guard2 <= 2)) {
										if(!state.guard1) {
											{
												double var34 = state.var33;
												state.bias = var34;
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

	// Calculate the probability of the samples represented by sample16 using sampled
	// values.
	private final void logProbabilityValue$sample16() {
		// Determine if we need to calculate the values for sample task 16 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample16) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if(state.guard1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = state.var14;
						{
							{
								double var10 = 1.0;
								double var12 = 1.0;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var10, var12));
								
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
				state.logProbability$sample16 = cv$sampleProbability;
			}
			
			// Guard to ensure that bias is only updated once for this probability.
			boolean cv$guard$bias = false;
			
			// Update the variable probability
			state.logProbability$var14 = (state.logProbability$var14 + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				{
					if(state.guard1) {
						// If the probability of the variable has not already been updated
						if(!cv$guard$bias) {
							// Set the guard so the update is only applied once.
							cv$guard$bias = true;
							
							// Update the variable probability
							state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
						}
					}
				}
			}
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample16)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample16 = state.fixedFlag$sample16;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if(state.guard1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample16;
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			
			// Guard to ensure that bias is only updated once for this probability.
			boolean cv$guard$bias = false;
			
			// Update the variable probability
			state.logProbability$var14 = (state.logProbability$var14 + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				{
					if(state.guard1) {
						// If the probability of the variable has not already been updated
						if(!cv$guard$bias) {
							// Set the guard so the update is only applied once.
							cv$guard$bias = true;
							
							// Update the variable probability
							state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
						}
					}
				}
			}
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample16)
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if(!state.guard1) {
				if((state.guard2 <= 2)) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						{
							// The sample value to calculate the probability of generating
							double cv$sampleValue = (state.var26 * 2);
							{
								{
									double var20 = 1.0;
									double var22 = 1.0;
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var20, var22));
									
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
					state.logProbability$sample28 = cv$sampleProbability;
				}
			}
			
			// Guard to ensure that bias is only updated once for this probability.
			boolean cv$guard$bias = false;
			
			// Update the variable probability
			state.logProbability$var26 = (state.logProbability$var26 + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				{
					if((state.guard2 <= 2)) {
						if(!state.guard1) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$bias) {
								// Set the guard so the update is only applied once.
								cv$guard$bias = true;
								
								// Update the variable probability
								state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if(!state.guard1) {
				if((state.guard2 <= 2)) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample28;
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			
			// Guard to ensure that bias is only updated once for this probability.
			boolean cv$guard$bias = false;
			
			// Update the variable probability
			state.logProbability$var26 = (state.logProbability$var26 + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				{
					if((state.guard2 <= 2)) {
						if(!state.guard1) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$bias) {
								// Set the guard so the update is only applied once.
								cv$guard$bias = true;
								
								// Update the variable probability
								state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample35 using sampled
	// values.
	private final void logProbabilityValue$sample35() {
		// Determine if we need to calculate the values for sample task 35 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample35) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if(!state.guard1) {
				if(!(state.guard2 <= 2)) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						{
							// The sample value to calculate the probability of generating
							double cv$sampleValue = (state.var33 * 3);
							{
								{
									double var27 = 1.0;
									double var29 = 1.0;
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var27, var29));
									
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
					state.logProbability$sample35 = cv$sampleProbability;
				}
			}
			
			// Guard to ensure that bias is only updated once for this probability.
			boolean cv$guard$bias = false;
			
			// Update the variable probability
			state.logProbability$var33 = (state.logProbability$var33 + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				{
					if(!(state.guard2 <= 2)) {
						if(!state.guard1) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$bias) {
								// Set the guard so the update is only applied once.
								cv$guard$bias = true;
								
								// Update the variable probability
								state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample35)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample35 = state.fixedFlag$sample35;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if(!state.guard1) {
				if(!(state.guard2 <= 2)) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample35;
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Record that the sample was reached.
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			
			// Guard to ensure that bias is only updated once for this probability.
			boolean cv$guard$bias = false;
			
			// Update the variable probability
			state.logProbability$var33 = (state.logProbability$var33 + cv$accumulator);
			
			// Add probability to constructed variables from the combined probability
			{
				{
					if(!(state.guard2 <= 2)) {
						if(!state.guard1) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$bias) {
								// Set the guard so the update is only applied once.
								cv$guard$bias = true;
								
								// Update the variable probability
								state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample35)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample52 using sampled
	// values.
	private final void logProbabilityValue$sample52() {
		// Determine if we need to calculate the values for sample task 52 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample52) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var47 = 0; var47 < state.samples; var47 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = state.flips[var47];
						{
							{
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= state.bias) && (state.bias <= 1.0))?Math.log((cv$sampleValue?state.bias:(1.0 - state.bias))):Double.NEGATIVE_INFINITY));
								
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
			state.logProbability$bernoulli = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			state.logProbability$var48 = cv$sampleAccumulator;
			
			// Update the variable probability
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample52 = ((state.fixedFlag$sample16 && state.fixedFlag$sample28) && state.fixedFlag$sample35);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var47 = 0; var47 < state.samples; var47 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var48;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$bernoulli = cv$rvAccumulator;
			
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
		if(state.guard1) {
			if(!state.fixedFlag$sample16)
				state.var14 = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			if(!state.fixedFlag$sample16)
				state.bias = state.var14;
		} else {
			if((state.guard2 <= 2)) {
				if(!state.fixedFlag$sample28)
					state.var26 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 2);
				if(!state.fixedFlag$sample28)
					state.bias = state.var26;
			} else {
				if(!state.fixedFlag$sample35)
					state.var33 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 3);
				if(!state.fixedFlag$sample35)
					state.bias = state.var33;
			}
		}
		for(int var47 = 0; var47 < state.samples; var47 += 1)
			state.flips[var47] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(state.guard1) {
			if(!state.fixedFlag$sample16)
				state.var14 = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			if(!state.fixedFlag$sample16)
				state.bias = state.var14;
		} else {
			if((state.guard2 <= 2)) {
				if(!state.fixedFlag$sample28)
					state.var26 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 2);
				if(!state.fixedFlag$sample28)
					state.bias = state.var26;
			} else {
				if(!state.fixedFlag$sample35)
					state.var33 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 3);
				if(!state.fixedFlag$sample35)
					state.bias = state.var33;
			}
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(state.guard1) {
			if(!state.fixedFlag$sample16)
				state.var14 = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			if(!state.fixedFlag$sample16)
				state.bias = state.var14;
		} else {
			if((state.guard2 <= 2)) {
				if(!state.fixedFlag$sample28)
					state.var26 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 2);
				if(!state.fixedFlag$sample28)
					state.bias = state.var26;
			} else {
				if(!state.fixedFlag$sample35)
					state.var33 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 3);
				if(!state.fixedFlag$sample35)
					state.bias = state.var33;
			}
		}
		for(int var47 = 0; var47 < state.samples; var47 += 1)
			state.flips[var47] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(state.guard1) {
			if(!state.fixedFlag$sample16)
				state.var14 = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			if(!state.fixedFlag$sample16)
				state.bias = state.var14;
		} else {
			if((state.guard2 <= 2)) {
				if(!state.fixedFlag$sample28)
					state.var26 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 2);
				if(!state.fixedFlag$sample28)
					state.bias = state.var26;
			} else {
				if(!state.fixedFlag$sample35)
					state.var33 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 3);
				if(!state.fixedFlag$sample35)
					state.bias = state.var33;
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(state.guard1) {
			if(!state.fixedFlag$sample16)
				state.var14 = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			if(!state.fixedFlag$sample16)
				state.bias = state.var14;
		} else {
			if((state.guard2 <= 2)) {
				if(!state.fixedFlag$sample28)
					state.var26 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 2);
				if(!state.fixedFlag$sample28)
					state.bias = state.var26;
			} else {
				if(!state.fixedFlag$sample35)
					state.var33 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 3);
				if(!state.fixedFlag$sample35)
					state.bias = state.var33;
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			if(state.guard1) {
				if(!state.fixedFlag$sample16)
					inferSample16();
			} else {
				if((state.guard2 <= 2)) {
					if(!state.fixedFlag$sample28)
						inferSample28();
				} else {
					if(!state.fixedFlag$sample35)
						inferSample35();
				}
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			if(state.guard1) {
				if(!state.fixedFlag$sample16)
					inferSample16();
			} else {
				if((state.guard2 <= 2)) {
					if(!state.fixedFlag$sample28)
						inferSample28();
				} else {
					if(!state.fixedFlag$sample35)
						inferSample35();
				}
			}
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(state.guard1) {
			if(!state.constrainedFlag$sample16)
				drawValueSample16();
		} else {
			if((state.guard2 <= 2)) {
				if(!state.constrainedFlag$sample28)
					drawValueSample28();
			} else {
				if(!state.constrainedFlag$sample35)
					drawValueSample35();
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
		state.logProbability$var14 = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample16)
			state.logProbability$sample16 = Double.NaN;
		state.logProbability$var26 = 0.0;
		if(!state.fixedProbFlag$sample28)
			state.logProbability$sample28 = Double.NaN;
		state.logProbability$var33 = 0.0;
		if(!state.fixedProbFlag$sample35)
			state.logProbability$sample35 = Double.NaN;
		state.logProbability$bernoulli = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample52)
			state.logProbability$var48 = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.samples = state.length$flipsMeasured;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample16)
			logProbabilityValue$sample16();
		if(state.fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(state.fixedFlag$sample35)
			logProbabilityValue$sample35();
		logProbabilityValue$sample52();
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
		logProbabilityValue$sample16();
		logProbabilityValue$sample28();
		logProbabilityValue$sample35();
		logProbabilityValue$sample52();
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
		logProbabilityValue$sample16();
		logProbabilityValue$sample28();
		logProbabilityValue$sample35();
		logProbabilityValue$sample52();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		for(int i = (state.samples - ((((state.samples - 1) - 0) % 1) + 1)); i >= ((0 - 1) + 1); i -= 1)
			state.flips[i] = state.flipsMeasured[i];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		if(state.guard1) {
			if(state.fixedFlag$sample16)
				state.bias = state.var14;
		} else {
			if((state.guard2 <= 2)) {
				if(state.fixedFlag$sample28)
					state.bias = state.var26;
			} else {
				if(state.fixedFlag$sample35)
					state.bias = state.var33;
			}
		}
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
		     + "public model Flip1CoinMK12(boolean[] flipsMeasured, boolean guard1, int guard2) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    double bias;\n"
		     + "    if(guard1)\n"
		     + "      bias = beta(1.0, 1).sample();\n"
		     + "    else { \n"
		     + "        if(guard2 <= 2) {\n"
		     + "            bias = beta(1.0, 1).sample()/2;\n"
		     + "        } else\n"
		     + "            bias = beta(1.0, 1).sample()/3;\n"
		     + "    }\n"
		     + "        \n"
		     + "    Bernoulli bernoulli = bernoulli(bias);\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "\n"
		     + "    for(int i:[0..samples))\n"
		     + "        flips[i].observe(flipsMeasured[i]);\n"
		     + "}\n"
		     + "";
	}
}