package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.LinearRegressionTest$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.LinearRegressionTest.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class LinearRegressionTest$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {}
	}


	public LinearRegressionTest$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample24
	private final void drawValueSample24(int var23) {
		state.weights[var23] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		
		// Guards to ensure that phi is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 24 and consumer double[] 60.
		{
			{
				for(int j$var55 = 0; j$var55 < state.k; j$var55 += 1) {
					if((var23 == j$var55)) {
						for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1)
							state.phi[((i$var45 - 0) / 1)][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
					}
				}
			}
		}
	}

	// Pick a value from the distribution for the unconditioned variable from sample31
	private final void drawValueSample31() {
		state.bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample35
	private final void drawValueSample35() {
		state.tau = DistributionSampling.sampleInverseGamma(state.RNG$, 3.0, 1.0);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 24 drawn from Gaussian 12. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void inferSample24(int var23) {
		if(true) {
			state.constrainedFlag$sample24[((var23 - 0) / 1)] = false;
			
			// State to record the weighting of each sample that is consumed. This is the:
			// sum of the sample denominator*(the sample value - the sample nominator).
			double cv$sum = 0.0;
			
			// State for storing the sum of the squares of the sample denominators.
			double cv$denominatorSquareSum = 0.0;
			
			// Flag to record if we have a value for Sigma.
			boolean cv$sigmaNotFound = true;
			
			// State for the value of sigma once we find it.
			double cv$sigmaValue = 1.0;
			{
				// Processing random variable 72.
				{
					// Looking for a path between Sample 24 and consumer Gaussian 72.
					{
						{
							for(int j$var55 = 0; j$var55 < state.k; j$var55 += 1) {
								if((var23 == j$var55)) {
									for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
										if(((0 <= j$var55) && (j$var55 < state.k))) {
											// Processing sample task 74 of consumer random variable null.
											{
												{
													// Flag recording if this sample task of the consuming random variable is constrained.
													boolean cv$sampleConstrained = true;
													if(cv$sampleConstrained) {
														// Mark that the sample has observed constrained data.
														state.constrainedFlag$sample24[((var23 - 0) / 1)] = true;
														{
															{
																{
																	{
																		{
																			// State for tracking the changes that happen to the sampled value between it being
																			// consumed and it being produced.
																			double cv$denominator = 1.0;
																			double cv$numerator = 0.0;
																			cv$numerator = (cv$numerator * state.x[i$var45][j$var55]);
																			cv$denominator = (cv$denominator * state.x[i$var45][j$var55]);
																			if((0 < state.k)) {
																				// Reduction of array phi
																				// 
																				// A generated name to prevent name collisions if the reduction is implemented more
																				// than once in inference and probability code. Initialize the variable to the unit
																				// value
																				double reduceVar$var70$0 = 0.0;
																				
																				// Reduce for every value except a masked value which will be skipped.
																				for(int cv$reduction162Index = 0; cv$reduction162Index < j$var55; cv$reduction162Index += 1) {
																					// Set the left hand term of the reduction function to the return variable value.
																					double i$var67 = reduceVar$var70$0;
																					
																					// Set the right hand term to a value from the array phi
																					double j$var68 = state.phi[((i$var45 - 0) / 1)][cv$reduction162Index];
																					
																					// Execute the reduction function, saving the result into the return value.
																					// 
																					// Copy the result of the reduction into the variable returned by the reduction.
																					reduceVar$var70$0 = (i$var67 + j$var68);
																				}
																				for(int cv$reduction162Index = (j$var55 + 1); cv$reduction162Index < state.k; cv$reduction162Index += 1) {
																					// Set the left hand term of the reduction function to the return variable value.
																					double i$var67 = reduceVar$var70$0;
																					
																					// Set the right hand term to a value from the array phi
																					double j$var68 = state.phi[((i$var45 - 0) / 1)][cv$reduction162Index];
																					
																					// Execute the reduction function, saving the result into the return value.
																					// 
																					// Copy the result of the reduction into the variable returned by the reduction.
																					reduceVar$var70$0 = (i$var67 + j$var68);
																				}
																				double cv$reduced65 = reduceVar$var70$0;
																				cv$numerator = (cv$numerator + cv$reduced65);
																			}
																			cv$numerator = (cv$numerator + state.bias);
																			
																			// Record the value of a sample generated by a consuming sample 74 of random variable
																			// var72.
																			// 
																			// Add the denominator squared to the sample denominator
																			cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
																			
																			// Add the weighting of the sample to the sum.
																			cv$sum = (cv$sum + (cv$denominator * (state.y[i$var45] - cv$numerator)));
																			
																			// If we have not got the value of sigma yet record it and set a flag so it is not
																			// recorded again.
																			if(cv$sigmaNotFound) {
																				cv$sigmaValue = state.tau;
																				cv$sigmaNotFound = false;
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
			if(state.constrainedFlag$sample24[((var23 - 0) / 1)]) {
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				double var24 = Conjugates.sampleConjugateGaussianGaussian(state.RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
				
				// Guards to ensure that weights is only updated when there is a valid path.
				{
					{
						{
							state.weights[var23] = var24;
						}
					}
				}
				
				// Guards to ensure that phi is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 24 and consumer double[] 60.
				{
					{
						for(int j$var55 = 0; j$var55 < state.k; j$var55 += 1) {
							if((var23 == j$var55)) {
								for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1)
									state.phi[((i$var45 - 0) / 1)][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
							}
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 31 drawn from Gaussian 30. Inference was performed using a Gaussian
	// to Gaussian conjugate prior.
	private final void inferSample31() {
		if(true) {
			state.constrainedFlag$sample31 = false;
			
			// State to record the weighting of each sample that is consumed. This is the:
			// sum of the sample denominator*(the sample value - the sample nominator).
			double cv$sum = 0.0;
			
			// State for storing the sum of the squares of the sample denominators.
			double cv$denominatorSquareSum = 0.0;
			
			// Flag to record if we have a value for Sigma.
			boolean cv$sigmaNotFound = true;
			
			// State for the value of sigma once we find it.
			double cv$sigmaValue = 1.0;
			{
				// Processing random variable 72.
				{
					{
						{
							for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
								// Flag recording if this sample task of the consuming random variable is constrained.
								boolean cv$sampleConstrained = true;
								if(cv$sampleConstrained) {
									// Mark that the sample has observed constrained data.
									state.constrainedFlag$sample31 = true;
									{
										{
											{
												{
													{
														// State for tracking the changes that happen to the sampled value between it being
														// consumed and it being produced.
														double cv$denominator = 1.0;
														double cv$numerator = 0.0;
														
														// Reduction of array phi
														// 
														// A generated name to prevent name collisions if the reduction is implemented more
														// than once in inference and probability code. Initialize the variable to the unit
														// value
														double reduceVar$var70$1 = 0.0;
														
														// For each index in the array to be reduced
														for(int cv$reduction65Index = 0; cv$reduction65Index < state.k; cv$reduction65Index += 1) {
															// Set the left hand term of the reduction function to the return variable value.
															double i$var67 = reduceVar$var70$1;
															
															// Set the right hand term to a value from the array phi
															double j$var68 = state.phi[((i$var45 - 0) / 1)][cv$reduction65Index];
															
															// Execute the reduction function, saving the result into the return value.
															// 
															// Copy the result of the reduction into the variable returned by the reduction.
															reduceVar$var70$1 = (i$var67 + j$var68);
														}
														cv$numerator = (reduceVar$var70$1 + cv$numerator);
														
														// Record the value of a sample generated by a consuming sample 74 of random variable
														// var72.
														// 
														// Add the denominator squared to the sample denominator
														cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
														
														// Add the weighting of the sample to the sum.
														cv$sum = (cv$sum + (cv$denominator * (state.y[i$var45] - cv$numerator)));
														
														// If we have not got the value of sigma yet record it and set a flag so it is not
														// recorded again.
														if(cv$sigmaNotFound) {
															cv$sigmaValue = state.tau;
															cv$sigmaNotFound = false;
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
			if(state.constrainedFlag$sample31)
				// Write out the new value of the sample.
				state.bias = Conjugates.sampleConjugateGaussianGaussian(state.RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 35 drawn from InverseGamma 34. Inference was performed using a Inverse
	// Gamma to Gaussian conjugate prior.
	private final void inferSample35() {
		if(true) {
			state.constrainedFlag$sample35 = false;
			
			// Variable to track the sum of the difference between the samples and the random
			// variables mean squared.
			double cv$sum = 0.0;
			
			// Variable to record the number of samples from consuming random variables.
			int cv$count = 0;
			{
				// Processing random variable 72.
				{
					{
						{
							for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
								// Flag recording if this sample task of the consuming random variable is constrained.
								boolean cv$sampleConstrained = true;
								if(cv$sampleConstrained) {
									// Mark that the sample has observed constrained data.
									state.constrainedFlag$sample35 = true;
									{
										{
											{
												{
													{
														// Reduction of array phi
														// 
														// A generated name to prevent name collisions if the reduction is implemented more
														// than once in inference and probability code. Initialize the variable to the unit
														// value
														double reduceVar$var70$2 = 0.0;
														
														// For each index in the array to be reduced
														for(int cv$reduction65Index = 0; cv$reduction65Index < state.k; cv$reduction65Index += 1) {
															// Set the left hand term of the reduction function to the return variable value.
															double i$var67 = reduceVar$var70$2;
															
															// Set the right hand term to a value from the array phi
															double j$var68 = state.phi[((i$var45 - 0) / 1)][cv$reduction65Index];
															
															// Execute the reduction function, saving the result into the return value.
															// 
															// Copy the result of the reduction into the variable returned by the reduction.
															reduceVar$var70$2 = (i$var67 + j$var68);
														}
														
														// The mean parameter for Gaussian var72.
														double cv$var72$mu = (reduceVar$var70$2 + state.bias);
														
														// Consume sample task 74 from random variable var72.
														// 
														// The difference between the mean parameter and the value sampled from the Gaussian.
														double cv$var72$diff = (cv$var72$mu - state.y[i$var45]);
														
														// Include this sample by adding the square of the difference to the sum.
														cv$sum = (cv$sum + (cv$var72$diff * cv$var72$diff));
														
														// Increment the number of samples in the calculation.
														cv$count = (cv$count + 1);
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
			if(state.constrainedFlag$sample35)
				// Write out the new value of the sample.
				state.tau = Conjugates.sampleConjugateInverseGammaGaussian(state.RNG$, 3.0, 1.0, cv$sum, cv$count);
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
			for(int var23 = 0; var23 < state.k; var23 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = state.weights[var23];
						{
							{
								double var10 = 0.0;
								double var11 = 10.0;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var11)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var10) / Math.sqrt(var11))) - (0.5 * Math.log(var11))):Double.NEGATIVE_INFINITY));
								
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
				
				// Store the sample task probability
				state.logProbability$sample24[((var23 - 0) / 1)] = cv$sampleProbability;
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			
			// Update the variable probability
			state.logProbability$weights = (state.logProbability$weights + cv$accumulator);
			
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
			for(int var23 = 0; var23 < state.k; var23 += 1) {
				double cv$sampleValue = state.logProbability$sample24[((var23 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$weights = (state.logProbability$weights + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample24)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample31 using sampled
	// values.
	private final void logProbabilityValue$sample31() {
		// Determine if we need to calculate the values for sample task 31 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample31) {
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
					double cv$sampleValue = state.bias;
					{
						{
							double var28 = 0.0;
							double var29 = 10.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var29)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var28) / Math.sqrt(var29))) - (0.5 * Math.log(var29))):Double.NEGATIVE_INFINITY));
							
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
			state.logProbability$bias = cv$sampleProbability;
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample31)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample31 = state.fixedFlag$sample31;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$bias;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample31)
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
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = state.tau;
					{
						{
							double var32 = 3.0;
							double var33 = 1.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var32, var33));
							
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
			state.logProbability$tau = cv$sampleProbability;
			
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
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$tau;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample35)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample74 using sampled
	// values.
	private final void logProbabilityValue$sample74() {
		// Determine if we need to calculate the values for sample task 74 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample74) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = state.y[i$var45];
						{
							{
								// Reduction of array phi
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								double reduceVar$var70$3 = 0.0;
								
								// For each index in the array to be reduced
								for(int cv$reduction65Index = 0; cv$reduction65Index < state.k; cv$reduction65Index += 1) {
									// Set the left hand term of the reduction function to the return variable value.
									double i$var67 = reduceVar$var70$3;
									
									// Set the right hand term to a value from the array phi
									double j$var68 = state.phi[((i$var45 - 0) / 1)][cv$reduction65Index];
									
									// Execute the reduction function, saving the result into the return value.
									// 
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$var70$3 = (i$var67 + j$var68);
								}
								double var71 = (reduceVar$var70$3 + state.bias);
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < state.tau)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var71) / Math.sqrt(state.tau))) - (0.5 * Math.log(state.tau))):Double.NEGATIVE_INFINITY));
								
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
				state.logProbability$sample74[((i$var45 - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			state.logProbability$y = (state.logProbability$y + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample74 = ((state.fixedFlag$sample24 && state.fixedFlag$sample31) && state.fixedFlag$sample35);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample74[((i$var45 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			
			// Update the variable probability
			state.logProbability$y = (state.logProbability$y + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		for(int var23 = 0; var23 < state.k; var23 += 1) {
			if(!state.fixedFlag$sample24)
				state.weights[var23] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		}
		if(!state.fixedFlag$sample31)
			state.bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample35)
			state.tau = DistributionSampling.sampleInverseGamma(state.RNG$, 3.0, 1.0);
		for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
			for(int j$var55 = 0; j$var55 < state.k; j$var55 += 1) {
				if(!state.fixedFlag$sample24)
					state.phi[((i$var45 - 0) / 1)][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
			}
			
			// Reduction of array phi
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$var70$4 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction65Index = 0; cv$reduction65Index < state.k; cv$reduction65Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double i$var67 = reduceVar$var70$4;
				
				// Set the right hand term to a value from the array phi
				double j$var68 = state.phi[((i$var45 - 0) / 1)][cv$reduction65Index];
				
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$var70$4 = (i$var67 + j$var68);
			}
			state.y[i$var45] = ((Math.sqrt(state.tau) * DistributionSampling.sampleGaussian(state.RNG$)) + (reduceVar$var70$4 + state.bias));
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int var23 = 0; var23 < state.k; var23 += 1) {
			if(!state.fixedFlag$sample24)
				state.weights[var23] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		}
		if(!state.fixedFlag$sample31)
			state.bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample35)
			state.tau = DistributionSampling.sampleInverseGamma(state.RNG$, 3.0, 1.0);
		for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
			for(int j$var55 = 0; j$var55 < state.k; j$var55 += 1)
				state.phi[((i$var45 - 0) / 1)][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		for(int var23 = 0; var23 < state.k; var23 += 1) {
			if(!state.fixedFlag$sample24)
				state.weights[var23] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		}
		if(!state.fixedFlag$sample31)
			state.bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample35)
			state.tau = DistributionSampling.sampleInverseGamma(state.RNG$, 3.0, 1.0);
		for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
			for(int j$var55 = 0; j$var55 < state.k; j$var55 += 1)
				state.phi[((i$var45 - 0) / 1)][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
			
			// Reduction of array phi
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$var70$5 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction65Index = 0; cv$reduction65Index < state.k; cv$reduction65Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double i$var67 = reduceVar$var70$5;
				
				// Set the right hand term to a value from the array phi
				double j$var68 = state.phi[((i$var45 - 0) / 1)][cv$reduction65Index];
				
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$var70$5 = (i$var67 + j$var68);
			}
			state.y[i$var45] = ((Math.sqrt(state.tau) * DistributionSampling.sampleGaussian(state.RNG$)) + (reduceVar$var70$5 + state.bias));
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var23 = 0; var23 < state.k; var23 += 1) {
			if(!state.fixedFlag$sample24)
				state.weights[var23] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		}
		if(!state.fixedFlag$sample31)
			state.bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample35)
			state.tau = DistributionSampling.sampleInverseGamma(state.RNG$, 3.0, 1.0);
		for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
			for(int j$var55 = 0; j$var55 < state.k; j$var55 += 1) {
				if(!state.fixedFlag$sample24)
					state.phi[((i$var45 - 0) / 1)][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		for(int var23 = 0; var23 < state.k; var23 += 1) {
			if(!state.fixedFlag$sample24)
				state.weights[var23] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		}
		if(!state.fixedFlag$sample31)
			state.bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample35)
			state.tau = DistributionSampling.sampleInverseGamma(state.RNG$, 3.0, 1.0);
		for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
			for(int j$var55 = 0; j$var55 < state.k; j$var55 += 1)
				state.phi[((i$var45 - 0) / 1)][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			for(int var23 = 0; var23 < state.k; var23 += 1) {
				if(!state.fixedFlag$sample24)
					inferSample24(var23);
			}
			if(!state.fixedFlag$sample31)
				inferSample31();
			if(!state.fixedFlag$sample35)
				inferSample35();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!state.fixedFlag$sample35)
				inferSample35();
			if(!state.fixedFlag$sample31)
				inferSample31();
			for(int var23 = (state.k - ((((state.k - 1) - 0) % 1) + 1)); var23 >= ((0 - 1) + 1); var23 -= 1) {
				if(!state.fixedFlag$sample24)
					inferSample24(var23);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int var23 = 0; var23 < state.k; var23 += 1) {
			if(!state.constrainedFlag$sample24[((var23 - 0) / 1)])
				drawValueSample24(var23);
		}
		if(!state.constrainedFlag$sample31)
			drawValueSample31();
		if(!state.constrainedFlag$sample35)
			drawValueSample35();
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
		state.logProbability$weights = 0.0;
		if(!state.fixedProbFlag$sample24) {
			for(int var23 = 0; var23 < state.k; var23 += 1)
				state.logProbability$sample24[((var23 - 0) / 1)] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample31)
			state.logProbability$bias = Double.NaN;
		if(!state.fixedProbFlag$sample35)
			state.logProbability$tau = Double.NaN;
		state.logProbability$y = 0.0;
		if(!state.fixedProbFlag$sample74) {
			for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1)
				state.logProbability$sample74[((i$var45 - 0) / 1)] = Double.NaN;
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.n = state.x.length;
		state.k = state.x[0].length;
		
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
		if(state.fixedFlag$sample31)
			logProbabilityValue$sample31();
		if(state.fixedFlag$sample35)
			logProbabilityValue$sample35();
		logProbabilityValue$sample74();
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
		logProbabilityValue$sample31();
		logProbabilityValue$sample35();
		logProbabilityValue$sample74();
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
		logProbabilityValue$sample31();
		logProbabilityValue$sample35();
		logProbabilityValue$sample74();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Deep copy between arrays
		double[] cv$source1 = state.yMeasured;
		double[] cv$target1 = state.y;
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
		for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
			for(int j$var55 = 0; j$var55 < state.k; j$var55 += 1)
				state.phi[((i$var45 - 0) / 1)][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
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
		     + "model LinearRegressionTest(double[][] x, double[] yMeasured) {\n"
		     + "\n"
		     + "        int n = x.length;\n"
		     + "        int k = x[0].length;\n"
		     + "\n"
		     + "        double[] y = new double[n];\n"
		     + "\n"
		     + "        double[] weights = gaussian(0,10).sample(k);\n"
		     + "        double bias = gaussian(0,10).sample();\n"
		     + "        double tau = inverseGamma(3.0,1.0).sample();\n"
		     + "\n"
		     + "        for(int i:[0..n)) {\n"
		     + "            double[] phi = new double[k];\n"
		     + "            for(int j:[0..k,1))\n"
		     + "                phi[j] = weights[j] * x[i][j];\n"
		     + "            \n"
		     + "            y[i] = gaussian(sum(phi) + bias, tau).sample();\n"
		     + "        }\n"
		     + "        \n"
		     + "        y.observe(yMeasured);\n"
		     + "\n"
		     + "    private double sum(double[] a) {\n"
		     + "        return reduce(a, 0, (i,j) -> {\n"
		     + "            return i + j;\n"
		     + "        });\n"
		     + "    }\n"
		     + "}\n"
		     + "";
	}
}