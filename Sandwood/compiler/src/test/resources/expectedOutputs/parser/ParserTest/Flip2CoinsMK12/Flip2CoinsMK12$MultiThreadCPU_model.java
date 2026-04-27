package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip2CoinsMK12$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip2CoinsMK12.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip2CoinsMK12$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {}
	}


	public Flip2CoinsMK12$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample10
	private final void drawValueSample10() {
		state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample23
	private final void drawValueSample23(int i$var22, int threadID$cv$i$var22, Rng RNG$) {
		state.bias[i$var22] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 10 drawn from beta. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void inferSample10() {
		if(true) {
			state.constrainedFlag$sample10 = false;
			
			// Local variable to record the number of true samples.
			int cv$sum = 0;
			
			// Local variable to record the number of samples.
			int cv$count = 0;
			{
				// Processing random variable 39.
				{
					// Looking for a path between Sample 10 and consumer Bernoulli 39.
					{
						{
							for(int j = 0; j < 1; j += 1) {
								if((0 == j)) {
									// Processing sample task 50 of consumer random variable bernoulli1.
									{
										{
											for(int var49 = 0; var49 < state.length$flipsMeasured[j]; var49 += 1) {
												// Flag recording if this sample task of the consuming random variable is constrained.
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													// Mark that the sample has observed constrained data.
													state.constrainedFlag$sample10 = true;
													{
														{
															{
																{
																	{
																		// Include the value sampled by task 50 from random variable bernoulli1.
																		// Increment the number of samples.
																		cv$count = (cv$count + 1);
																		
																		// If the sample value was positive increase the count
																		if(state.flips[j][var49])
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
				
				// Processing random variable 66.
				{
					// Looking for a path between Sample 10 and consumer Bernoulli 66.
					{
						{
							for(int k = 1; k < state.coins; k += 1) {
								if((0 == k)) {
									// Processing sample task 78 of consumer random variable bernoulli2.
									{
										{
											for(int var76 = 0; var76 < state.length$flipsMeasured[k]; var76 += 1) {
												// Flag recording if this sample task of the consuming random variable is constrained.
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													// Mark that the sample has observed constrained data.
													state.constrainedFlag$sample10 = true;
													{
														{
															{
																{
																	{
																		// Include the value sampled by task 78 from random variable bernoulli2.
																		// Increment the number of samples.
																		cv$count = (cv$count + 1);
																		
																		// If the sample value was positive increase the count
																		if(state.flips[k][var76])
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
			}
			if(state.constrainedFlag$sample10) {
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				double var10 = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
				
				// Guards to ensure that bias is only updated when there is a valid path.
				{
					{
						{
							state.bias[0] = var10;
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 23 drawn from beta. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void inferSample23(int i$var22, int threadID$cv$i$var22, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample23[((i$var22 - 1) / 1)] = false;
			
			// Local variable to record the number of true samples.
			int cv$sum = 0;
			
			// Local variable to record the number of samples.
			int cv$count = 0;
			{
				// Processing random variable 39.
				{
					// Looking for a path between Sample 23 and consumer Bernoulli 39.
					{
						{
							for(int j = 0; j < 1; j += 1) {
								if((i$var22 == j)) {
									// Processing sample task 50 of consumer random variable bernoulli1.
									{
										{
											for(int var49 = 0; var49 < state.length$flipsMeasured[j]; var49 += 1) {
												// Flag recording if this sample task of the consuming random variable is constrained.
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													// Mark that the sample has observed constrained data.
													state.constrainedFlag$sample23[((i$var22 - 1) / 1)] = true;
													{
														{
															{
																{
																	{
																		// Include the value sampled by task 50 from random variable bernoulli1.
																		// Increment the number of samples.
																		cv$count = (cv$count + 1);
																		
																		// If the sample value was positive increase the count
																		if(state.flips[j][var49])
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
				
				// Processing random variable 66.
				{
					// Looking for a path between Sample 23 and consumer Bernoulli 66.
					{
						{
							for(int k = 1; k < state.coins; k += 1) {
								if((i$var22 == k)) {
									// Processing sample task 78 of consumer random variable bernoulli2.
									{
										{
											for(int var76 = 0; var76 < state.length$flipsMeasured[k]; var76 += 1) {
												// Flag recording if this sample task of the consuming random variable is constrained.
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													// Mark that the sample has observed constrained data.
													state.constrainedFlag$sample23[((i$var22 - 1) / 1)] = true;
													{
														{
															{
																{
																	{
																		// Include the value sampled by task 78 from random variable bernoulli2.
																		// Increment the number of samples.
																		cv$count = (cv$count + 1);
																		
																		// If the sample value was positive increase the count
																		if(state.flips[k][var76])
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
			}
			if(state.constrainedFlag$sample23[((i$var22 - 1) / 1)]) {
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				double var23 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
				
				// Guards to ensure that bias is only updated when there is a valid path.
				{
					{
						{
							state.bias[i$var22] = var23;
						}
					}
				}
			}
		}
	}

	// Calculate the probability of the samples represented by sample10 using sampled
	// values.
	private final void logProbabilityValue$sample10() {
		// Determine if we need to calculate the values for sample task 10 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample10) {
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
					double cv$sampleValue = state.bias[0];
					{
						{
							double var6 = 1.0;
							double var7 = 1.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var6, var7));
							
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
			state.logProbability$beta = (state.logProbability$beta + cv$sampleAccumulator);
			
			// Store the sample task probability
			state.logProbability$var10 = cv$sampleProbability;
			
			// Update the variable probability
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample10)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample10 = state.fixedFlag$sample10;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$var10;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$beta = (state.logProbability$beta + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample10)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample23 using sampled
	// values.
	private final void logProbabilityValue$sample23() {
		// Determine if we need to calculate the values for sample task 23 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample23) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var22 = 1; i$var22 < state.coins; i$var22 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = state.bias[i$var22];
						{
							{
								double var6 = 1.0;
								double var7 = 1.0;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var6, var7));
								
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
			state.logProbability$beta = (state.logProbability$beta + cv$sampleAccumulator);
			
			// Store the random variable instance probability
			state.logProbability$var23 = cv$sampleAccumulator;
			
			// Update the variable probability
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample23)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample23 = state.fixedFlag$sample23;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var22 = 1; i$var22 < state.coins; i$var22 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var23;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$beta = (state.logProbability$beta + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample23)
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j = 0; j < 1; j += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int var49 = 0; var49 < state.length$flipsMeasured[j]; var49 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						{
							// The sample value to calculate the probability of generating
							boolean cv$sampleValue = state.flips[j][var49];
							{
								{
									double var38 = state.bias[j];
									
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
				state.logProbability$bernoulli1[((j - 0) / 1)] = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				state.logProbability$sample50[((j - 0) / 1)] = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample50 = (state.fixedFlag$sample10 && state.fixedFlag$sample23);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j = 0; j < 1; j += 1) {
				double cv$rvAccumulator = 0.0;
				for(int var49 = 0; var49 < state.length$flipsMeasured[j]; var49 += 1)
					// Record that the sample was reached.
					cv$sampleReached = true;
				double cv$sampleValue = state.logProbability$sample50[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				state.logProbability$bernoulli1[((j - 0) / 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int k = 1; k < state.coins; k += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int var76 = 0; var76 < state.length$flipsMeasured[k]; var76 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						{
							// The sample value to calculate the probability of generating
							boolean cv$sampleValue = state.flips[k][var76];
							{
								{
									double var65 = state.bias[k];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var65) && (var65 <= 1.0))?Math.log((cv$sampleValue?var65:(1.0 - var65))):Double.NEGATIVE_INFINITY));
									
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
				state.logProbability$bernoulli2[((k - 1) / 1)] = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				state.logProbability$sample78[((k - 1) / 1)] = cv$sampleAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample78 = (state.fixedFlag$sample10 && state.fixedFlag$sample23);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int k = 1; k < state.coins; k += 1) {
				double cv$rvAccumulator = 0.0;
				for(int var76 = 0; var76 < state.length$flipsMeasured[k]; var76 += 1)
					// Record that the sample was reached.
					cv$sampleReached = true;
				double cv$sampleValue = state.logProbability$sample78[((k - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				state.logProbability$bernoulli2[((k - 1) / 1)] = cv$rvAccumulator;
			}
			
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
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 1, state.coins, 1,
			(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1) {
						if(!state.fixedFlag$sample23)
							state.bias[i$var22] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, 1, 1,
			(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
						int j = index$j;
						int threadID$j = threadID$index$j;
						boolean[] var40 = state.flips[j];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.length$flipsMeasured[j], 1,
							(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1)
										var40[var49] = DistributionSampling.sampleBernoulli(RNG$2, state.bias[j]);
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 1, state.coins, 1,
			(int forStart$index$k, int forEnd$index$k, int threadID$index$k, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$k = forStart$index$k; index$k < forEnd$index$k; index$k += 1) {
						int k = index$k;
						int threadID$k = threadID$index$k;
						boolean[] var67 = state.flips[k];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.length$flipsMeasured[k], 1,
							(int forStart$var76, int forEnd$var76, int threadID$var76, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var76 = forStart$var76; var76 < forEnd$var76; var76 += 1)
										var67[var76] = DistributionSampling.sampleBernoulli(RNG$2, state.bias[k]);
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
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 1, state.coins, 1,
			(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1) {
						if(!state.fixedFlag$sample23)
							state.bias[i$var22] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 1, state.coins, 1,
			(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1) {
						if(!state.fixedFlag$sample23)
							state.bias[i$var22] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, 1, 1,
			(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
						int j = index$j;
						int threadID$j = threadID$index$j;
						boolean[] var40 = state.flips[j];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.length$flipsMeasured[j], 1,
							(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1)
										var40[var49] = DistributionSampling.sampleBernoulli(RNG$2, state.bias[j]);
							}
						);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 1, state.coins, 1,
			(int forStart$index$k, int forEnd$index$k, int threadID$index$k, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$k = forStart$index$k; index$k < forEnd$index$k; index$k += 1) {
						int k = index$k;
						int threadID$k = threadID$index$k;
						boolean[] var67 = state.flips[k];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, state.length$flipsMeasured[k], 1,
							(int forStart$var76, int forEnd$var76, int threadID$var76, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int var76 = forStart$var76; var76 < forEnd$var76; var76 += 1)
										var67[var76] = DistributionSampling.sampleBernoulli(RNG$2, state.bias[k]);
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
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 1, state.coins, 1,
			(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1) {
						if(!state.fixedFlag$sample23)
							state.bias[i$var22] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 1, state.coins, 1,
			(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1) {
						if(!state.fixedFlag$sample23)
							state.bias[i$var22] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample10)
				inferSample10();
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 1, state.coins, 1,
				(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1) {
							if(!state.fixedFlag$sample23)
								inferSample23(i$var22, threadID$i$var22, RNG$1);
						}
				}
			);
		}
		// Infer the samples in reverse chronological order.
		else {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 1, state.coins, 1,
				(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1) {
							if(!state.fixedFlag$sample23)
								inferSample23(i$var22, threadID$i$var22, RNG$1);
						}
				}
			);
			if(!state.fixedFlag$sample10)
				inferSample10();
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample10)
			drawValueSample10();
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 1, state.coins, 1,
			(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1) {
						if(!state.constrainedFlag$sample23[((i$var22 - 1) / 1)])
							drawValueSample23(i$var22, threadID$i$var22, RNG$1);
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
		state.logProbability$beta = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample10)
			state.logProbability$var10 = Double.NaN;
		if(!state.fixedProbFlag$sample23)
			state.logProbability$var23 = Double.NaN;
		for(int j = 0; j < 1; j += 1)
			state.logProbability$bernoulli1[((j - 0) / 1)] = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample50) {
			for(int j = 0; j < 1; j += 1)
				state.logProbability$sample50[((j - 0) / 1)] = Double.NaN;
		}
		for(int k = 1; k < state.coins; k += 1)
			state.logProbability$bernoulli2[((k - 1) / 1)] = Double.NaN;
		if(!state.fixedProbFlag$sample78) {
			for(int k = 1; k < state.coins; k += 1)
				state.logProbability$sample78[((k - 1) / 1)] = Double.NaN;
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.coins = state.length$flipsMeasured.length;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample23$1 = 0; index$constrainedFlag$sample23$1 < state.constrainedFlag$sample23.length; index$constrainedFlag$sample23$1 += 1)
			state.constrainedFlag$sample23[index$constrainedFlag$sample23$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample10)
			logProbabilityValue$sample10();
		if(state.fixedFlag$sample23)
			logProbabilityValue$sample23();
		logProbabilityValue$sample50();
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
		logProbabilityValue$sample10();
		logProbabilityValue$sample23();
		logProbabilityValue$sample50();
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
		logProbabilityValue$sample10();
		logProbabilityValue$sample23();
		logProbabilityValue$sample50();
		logProbabilityValue$sample78();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Setting intermediate variables that are used to constrain other variables.
		{
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.length$flipsMeasured.length, 1,
				(int forStart$index$l, int forEnd$index$l, int threadID$index$l, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$l = forStart$index$l; index$l < forEnd$index$l; index$l += 1) {
							int l = index$l;
							int threadID$l = threadID$index$l;
							boolean[] target = state.intermediateFlips[l];
							
							//  Outer loop for dispatching multiple batches of iterations to execute in parallel
							parallelFor(RNG$1, 0, state.length$flipsMeasured[l], 1,
								(int forStart$m$var102, int forEnd$m$var102, int threadID$m$var102, org.sandwood.random.internal.Rng RNG$2) -> { 
									
										// Inner loop for running batches of iterations, each batch has its own random number
										// generator.
										for(int m$var102 = forStart$m$var102; m$var102 < forEnd$m$var102; m$var102 += 1)
											target[m$var102] = state.flipsMeasured[l][m$var102];
								}
							);
						}
				}
			);
		}
		
		// Propagating values back from observations into the models intermediate variables.
		{
			for(int i$var115 = (state.coins - ((((state.coins - 1) - 0) % 1) + 1)); i$var115 >= ((0 - 1) + 1); i$var115 -= 1) {
				// Deep copy between arrays
				boolean[] cv$source1 = state.intermediateFlips[(state.coins - (i$var115 + 1))];
				boolean[] cv$target1 = state.flips[i$var115];
				int cv$length1 = cv$target1.length;
				for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
					cv$target1[cv$index1] = cv$source1[cv$index1];
			}
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
		     + "public model Flip2CoinsMK12(boolean[][] flipsMeasured) {\n"
		     + "    int coins = flipsMeasured.length;\n"
		     + "         \n"
		     + "    boolean[][] flips = new boolean[coins][];\n"
		     + "    boolean[][] intermediateFlips = new boolean[coins][];\n"
		     + "    double [] bias = new double[coins];\n"
		     + "        \n"
		     + "    Beta beta = beta(1.0, 1.0);\n"
		     + "        \n"
		     + "    bias[0] = beta.sample();\n"
		     + "        \n"
		     + "    for(int i:[1..coins))\n"
		     + "        bias[i] = beta.sample();\n"
		     + "    \n"
		     + "    for(int j:[0..1)) {\n"
		     + "        int samples = flipsMeasured[j].length;\n"
		     + "        Bernoulli bernoulli1 = bernoulli(bias[j]);\n"
		     + "        flips[j] = bernoulli1.sample(samples);\n"
		     + "    }\n"
		     + "                \n"
		     + "    for(int k:[1..coins)) {\n"
		     + "        int samples = flipsMeasured[k].length;\n"
		     + "        Bernoulli bernoulli2 = bernoulli(bias[k]);\n"
		     + "        flips[k] = bernoulli2.sample(samples);\n"
		     + "    }\n"
		     + "        \n"
		     + "    for(int l:[0..coins)) {\n"
		     + "        boolean[] source = flipsMeasured[l];\n"
		     + "        int noFlips = source.length;\n"
		     + "        boolean[] target = new boolean[noFlips];\n"
		     + "        intermediateFlips[l] = target;\n"
		     + "        \n"
		     + "        for(int m:[0..noFlips))\n"
		     + "            target[m] = source[m];\n"
		     + "    }\n"
		     + "        \n"
		     + "    for(int i:[0..coins)) {\n"
		     + "        boolean[] f = flips[i];\n"
		     + "        boolean[] m = intermediateFlips[coins - (i+1)];\n"
		     + "        f.observe(m);\n"
		     + "    }\n"
		     + "}";
	}
}