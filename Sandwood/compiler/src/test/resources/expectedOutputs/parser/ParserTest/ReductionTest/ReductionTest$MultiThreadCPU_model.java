package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.ReductionTest$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.ReductionTest.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class ReductionTest$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[][] cv$var30$countGlobal;
		double[] cv$var61$stateProbabilityGlobal;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for cv$var30$countGlobal
			{
				// Allocation of cv$var30$countGlobal for multithreaded execution
				{
					// Get the thread count.
					int cv$threadCount = threadCount();
					
					// Allocate an array to hold a copy per thread
					cv$var30$countGlobal = new double[cv$threadCount][];
					
					// Populate the array with a copy per thread
					for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
						cv$var30$countGlobal[cv$index] = new double[((0.0 <= state.flipsMeasured.length)?((0.0 <= state.noCats)?(state.flipsMeasured.length / state.noCats):((state.noCats < 0.0)?(state.flipsMeasured.length / state.noCats):state.flipsMeasured.length)):((state.flipsMeasured.length < 0.0)?((0.0 <= state.noCats)?(state.flipsMeasured.length / state.noCats):((state.noCats < 0.0)?(state.flipsMeasured.length / state.noCats):(-state.flipsMeasured.length))):((0.0 <= state.noCats)?(state.flipsMeasured.length / state.noCats):((state.noCats < 0.0)?(state.flipsMeasured.length / state.noCats):Math.max(state.flipsMeasured.length, (-state.flipsMeasured.length))))))];
				}
			}
			
			// Constructor for cv$var61$stateProbabilityGlobal
			{
				// Variable to record the maximum value of Task Get 60. Initially set to the value
				// of putTask 31.
				int cv$var31$max = ((0.0 <= state.flipsMeasured.length)?((0.0 <= state.noCats)?(state.flipsMeasured.length / state.noCats):((state.noCats < 0.0)?(state.flipsMeasured.length / state.noCats):state.flipsMeasured.length)):((state.flipsMeasured.length < 0.0)?((0.0 <= state.noCats)?(state.flipsMeasured.length / state.noCats):((state.noCats < 0.0)?(state.flipsMeasured.length / state.noCats):(-state.flipsMeasured.length))):((0.0 <= state.noCats)?(state.flipsMeasured.length / state.noCats):((state.noCats < 0.0)?(state.flipsMeasured.length / state.noCats):Math.max(state.flipsMeasured.length, (-state.flipsMeasured.length))))));
				
				// Allocation of cv$var61$stateProbabilityGlobal for single threaded execution
				cv$var61$stateProbabilityGlobal = new double[cv$var31$max];
			}
		}
	}


	public ReductionTest$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample30
	private final void drawValueSample30(int var29, int threadID$cv$var29, Rng RNG$) {
		double[] var30 = state.m[var29];
		DistributionSampling.sampleDirichlet(RNG$, state.v, state.noStates, var30);
	}

	// Pick a value from the distribution for the unconditioned variable from sample47
	private final void drawValueSample47(int var45, int threadID$cv$var45, Rng RNG$) {
		state.bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	// Pick a value from the distribution for the unconditioned variable from sample62
	private final void drawValueSample62(int i$var58) {
		state.st[i$var58] = DistributionSampling.sampleCategorical(state.RNG$, state.m[i$var58], state.noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 30 drawn from Dirichlet 18. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample30(int var29, int threadID$cv$var29, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample30[((var29 - 0) / 1)] = false;
			
			// A reference local to the function for the sample variable.
			double[] cv$targetLocal = state.m[var29];
			
			// A local reference to the scratch space.
			double[] cv$countLocal = scratch.cv$var30$countGlobal[threadID$cv$var29];
			
			// Get the length of the array
			int cv$arrayLength = state.noStates;
			
			// Initialize the array values to 0.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				// Processing random variable 60.
				{
					// Looking for a path between Sample 30 and consumer Categorical 60.
					{
						{
							for(int i$var58 = 0; i$var58 < state.noCats; i$var58 += 1) {
								if((var29 == i$var58)) {
									// Processing sample task 62 of consumer random variable null.
									{
										{
											// Flag recording if this sample task of the consuming random variable is constrained.
											boolean cv$sampleConstrained = (state.fixedFlag$sample62 || state.constrainedFlag$sample62[((i$var58 - 0) / 1)]);
											if(cv$sampleConstrained) {
												// Mark that the sample has observed constrained data.
												state.constrainedFlag$sample30[((var29 - 0) / 1)] = true;
												{
													{
														{
															{
																{
																	// Increment the sample counter with the value sampled by sample task 62 of random
																	// variable var60
																	cv$countLocal[state.st[i$var58]] = (cv$countLocal[state.st[i$var58]] + 1.0);
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
			if(state.constrainedFlag$sample30[((var29 - 0) / 1)])
				// Calculate the new sample value
				// 
				// Calculate a new sample value and write it into cv$targetLocal.
				Conjugates.sampleConjugateDirichletCategorical(RNG$, state.v, cv$countLocal, cv$targetLocal, state.noStates);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 47 drawn from Beta 34. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void inferSample47(int var45, int threadID$cv$var45, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample47[((var45 - 0) / 1)] = false;
			
			// Local variable to record the number of true samples.
			int cv$sum = 0;
			
			// Local variable to record the number of samples.
			int cv$count = 0;
			{
				// Processing random variable 84.
				{
					// Looking for a path between Sample 47 and consumer Bernoulli 84.
					{
						{
							// Reduction of array st
							// 
							// A generated name to prevent name collisions if the reduction is implemented more
							// than once in inference and probability code. Initialize the variable to the unit
							// value
							int reduceVar$var82$5 = 0;
							
							// For each index in the array to be reduced
							for(int cv$reduction78Index = 0; cv$reduction78Index < state.noCats; cv$reduction78Index += 1) {
								// Set the left hand term of the reduction function to the return variable value.
								int i$var79 = reduceVar$var82$5;
								
								// Set the right hand term to a value from the array st
								int j$var80 = state.st[cv$reduction78Index];
								
								// Execute the reduction function, saving the result into the return value.
								// 
								// Copy the result of the reduction into the variable returned by the reduction.
								reduceVar$var82$5 = (i$var79 + j$var80);
							}
							if((var45 == reduceVar$var82$5)) {
								for(int j$var73 = 0; j$var73 < state.noFlips; j$var73 += 1) {
									// Flag recording if this sample task of the consuming random variable is constrained.
									boolean cv$sampleConstrained = true;
									if(cv$sampleConstrained) {
										// Mark that the sample has observed constrained data.
										state.constrainedFlag$sample47[((var45 - 0) / 1)] = true;
										{
											{
												{
													{
														{
															// Include the value sampled by task 87 from random variable var84.
															// Increment the number of samples.
															cv$count = (cv$count + 1);
															
															// If the sample value was positive increase the count
															if(state.flips[j$var73])
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
			if(state.constrainedFlag$sample47[((var45 - 0) / 1)]) {
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				double var46 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
				
				// Guards to ensure that bias is only updated when there is a valid path.
				{
					{
						{
							state.bias[var45] = var46;
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 62 drawn from Categorical 60. Inference was performed using variable
	// marginalization.
	private final void inferSample62(int i$var58) {
		if(true) {
			state.constrainedFlag$sample62[((i$var58 - 0) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, state.noStates);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = scratch.cv$var61$stateProbabilityGlobal;
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
				int var61 = cv$currentValue;
				
				// Guards to ensure that st is only updated when there is a valid path.
				{
					{
						{
							state.st[i$var58] = cv$currentValue;
						}
					}
				}
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// Constructing a random variable input for use later.
					double[] var59 = state.m[i$var58];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var59[cv$currentValue])) && (var59[cv$currentValue] <= 1.0))?Math.log(var59[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 84.
					{
						{
							{
								int traceTempVariable$i$2_1 = cv$currentValue;
								if(((0 <= i$var58) && (i$var58 < state.noCats))) {
									if((0 < state.noCats)) {
										// Reduction of array st
										// 
										// A generated name to prevent name collisions if the reduction is implemented more
										// than once in inference and probability code. Initialize the variable to the unit
										// value
										int reduceVar$var82$6 = 0;
										
										// Reduce for every value except a masked value which will be skipped.
										for(int cv$reduction543Index = 0; cv$reduction543Index < i$var58; cv$reduction543Index += 1) {
											// Set the left hand term of the reduction function to the return variable value.
											int i$var79 = reduceVar$var82$6;
											
											// Set the right hand term to a value from the array st
											int j$var80 = state.st[cv$reduction543Index];
											
											// Execute the reduction function, saving the result into the return value.
											// 
											// Copy the result of the reduction into the variable returned by the reduction.
											reduceVar$var82$6 = (i$var79 + j$var80);
										}
										for(int cv$reduction543Index = (i$var58 + 1); cv$reduction543Index < state.noCats; cv$reduction543Index += 1) {
											// Set the left hand term of the reduction function to the return variable value.
											int i$var79 = reduceVar$var82$6;
											
											// Set the right hand term to a value from the array st
											int j$var80 = state.st[cv$reduction543Index];
											
											// Execute the reduction function, saving the result into the return value.
											// 
											// Copy the result of the reduction into the variable returned by the reduction.
											reduceVar$var82$6 = (i$var79 + j$var80);
										}
										int cv$reduced78 = reduceVar$var82$6;
										
										// Copy the result of the reduction into the variable returned by the reduction.
										reduceVar$var82$6 = (traceTempVariable$i$2_1 + cv$reduced78);
										int traceTempVariable$var82$2_2 = reduceVar$var82$6;
										for(int j$var73 = 0; j$var73 < state.noFlips; j$var73 += 1) {
											// Flag recording if this sample task of the consuming random variable is constrained.
											boolean cv$sampleConstrained = true;
											if(cv$sampleConstrained) {
												// Mark that the sample has observed constrained data.
												state.constrainedFlag$sample62[((i$var58 - 0) / 1)] = true;
												
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
																	double var83 = state.bias[traceTempVariable$var82$2_2];
																	
																	// Record the probability of sample task 87 generating output with current configuration.
																	if(((Math.log(1.0) + (((0.0 <= var83) && (var83 <= 1.0))?Math.log((state.flips[j$var73]?var83:(1.0 - var83))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var83) && (var83 <= 1.0))?Math.log((state.flips[j$var73]?var83:(1.0 - var83))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var83) && (var83 <= 1.0))?Math.log((state.flips[j$var73]?var83:(1.0 - var83))):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var83) && (var83 <= 1.0))?Math.log((state.flips[j$var73]?var83:(1.0 - var83))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var83) && (var83 <= 1.0))?Math.log((state.flips[j$var73]?var83:(1.0 - var83))):Double.NEGATIVE_INFINITY)));
																	}
																	
																	// Recorded the probability of reaching sample task 87 with the current configuration.
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
			if(state.constrainedFlag$sample62[((i$var58 - 0) / 1)]) {
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
				int var61 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
				
				// Guards to ensure that st is only updated when there is a valid path.
				{
					{
						{
							state.st[i$var58] = var61;
						}
					}
				}
			}
		}
	}

	// Calculate the probability of the samples represented by sample30 using sampled
	// values.
	private final void logProbabilityValue$sample30() {
		// Determine if we need to calculate the values for sample task 30 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample30) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var29 = 0; var29 < state.noCats; var29 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						double[] cv$sampleValue = state.m[var29];
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
			state.logProbability$var30 = cv$sampleAccumulator;
			
			// Update the variable probability
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample30)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample30 = state.fixedFlag$sample30;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var29 = 0; var29 < state.noCats; var29 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var30;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample30)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var45 = 0; var45 < state.noFlips; var45 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						double cv$sampleValue = state.bias[var45];
						{
							{
								double var32 = 1.0;
								double var33 = 1.0;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var32, var33));
								
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
			state.logProbability$var46 = cv$sampleAccumulator;
			
			// Update the variable probability
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var45 = 0; var45 < state.noFlips; var45 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var46;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample47)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample62 using sampled
	// values.
	private final void logProbabilityValue$sample62() {
		// Determine if we need to calculate the values for sample task 62 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample62) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var58 = 0; i$var58 < state.noCats; i$var58 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = state.st[i$var58];
						{
							{
								double[] var59 = state.m[i$var58];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var59[cv$sampleValue])) && (var59[cv$sampleValue] <= 1.0))?Math.log(var59[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
				state.logProbability$sample62[((i$var58 - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample62)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample62 = (state.fixedFlag$sample62 && state.fixedFlag$sample30);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var58 = 0; i$var58 < state.noCats; i$var58 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample62[((i$var58 - 0) / 1)];
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
			if(state.fixedFlag$sample62)
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
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j$var73 = 0; j$var73 < state.noFlips; j$var73 += 1) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = state.flips[j$var73];
						{
							{
								// Reduction of array st
								// 
								// A generated name to prevent name collisions if the reduction is implemented more
								// than once in inference and probability code. Initialize the variable to the unit
								// value
								int reduceVar$var82$7 = 0;
								
								// For each index in the array to be reduced
								for(int cv$reduction78Index = 0; cv$reduction78Index < state.noCats; cv$reduction78Index += 1) {
									// Set the left hand term of the reduction function to the return variable value.
									int i$var79 = reduceVar$var82$7;
									
									// Set the right hand term to a value from the array st
									int j$var80 = state.st[cv$reduction78Index];
									
									// Execute the reduction function, saving the result into the return value.
									// 
									// Copy the result of the reduction into the variable returned by the reduction.
									reduceVar$var82$7 = (i$var79 + j$var80);
								}
								double var83 = state.bias[reduceVar$var82$7];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var83) && (var83 <= 1.0))?Math.log((cv$sampleValue?var83:(1.0 - var83))):Double.NEGATIVE_INFINITY));
								
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
				state.logProbability$sample87[((j$var73 - 0) / 1)] = cv$sampleProbability;
			}
			
			// Update the variable probability
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample87 = (state.fixedFlag$sample47 && state.fixedFlag$sample62);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j$var73 = 0; j$var73 < state.noFlips; j$var73 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample87[((j$var73 - 0) / 1)];
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

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = state.m[var29];
						if(!state.fixedFlag$sample30)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var30);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noFlips, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!state.fixedFlag$sample47)
							state.bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$i$var58, int forEnd$i$var58, int threadID$i$var58, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var58 = forStart$i$var58; i$var58 < forEnd$i$var58; i$var58 += 1) {
						if(!state.fixedFlag$sample62)
							state.st[i$var58] = DistributionSampling.sampleCategorical(RNG$1, state.m[i$var58], state.noStates);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noFlips, 1,
			(int forStart$j$var73, int forEnd$j$var73, int threadID$j$var73, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j$var73 = forStart$j$var73; j$var73 < forEnd$j$var73; j$var73 += 1) {
						// Reduction of array st
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						int reduceVar$var82$8 = 0;
						
						// For each index in the array to be reduced
						for(int cv$reduction78Index = 0; cv$reduction78Index < state.noCats; cv$reduction78Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							int i$var79 = reduceVar$var82$8;
							
							// Set the right hand term to a value from the array st
							int j$var80 = state.st[cv$reduction78Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$var82$8 = (i$var79 + j$var80);
						}
						state.flips[j$var73] = DistributionSampling.sampleBernoulli(RNG$1, state.bias[reduceVar$var82$8]);
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
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = state.m[var29];
						if(!state.fixedFlag$sample30)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var30);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noFlips, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!state.fixedFlag$sample47)
							state.bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$i$var58, int forEnd$i$var58, int threadID$i$var58, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var58 = forStart$i$var58; i$var58 < forEnd$i$var58; i$var58 += 1) {
						if(!state.fixedFlag$sample62)
							state.st[i$var58] = DistributionSampling.sampleCategorical(RNG$1, state.m[i$var58], state.noStates);
					}
			}
		);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = state.m[var29];
						if(!state.fixedFlag$sample30)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var30);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noFlips, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!state.fixedFlag$sample47)
							state.bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$i$var58, int forEnd$i$var58, int threadID$i$var58, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var58 = forStart$i$var58; i$var58 < forEnd$i$var58; i$var58 += 1) {
						if(!state.fixedFlag$sample62)
							state.st[i$var58] = DistributionSampling.sampleCategorical(RNG$1, state.m[i$var58], state.noStates);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noFlips, 1,
			(int forStart$j$var73, int forEnd$j$var73, int threadID$j$var73, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j$var73 = forStart$j$var73; j$var73 < forEnd$j$var73; j$var73 += 1) {
						// Reduction of array st
						// 
						// A generated name to prevent name collisions if the reduction is implemented more
						// than once in inference and probability code. Initialize the variable to the unit
						// value
						int reduceVar$var82$9 = 0;
						
						// For each index in the array to be reduced
						for(int cv$reduction78Index = 0; cv$reduction78Index < state.noCats; cv$reduction78Index += 1) {
							// Set the left hand term of the reduction function to the return variable value.
							int i$var79 = reduceVar$var82$9;
							
							// Set the right hand term to a value from the array st
							int j$var80 = state.st[cv$reduction78Index];
							
							// Execute the reduction function, saving the result into the return value.
							// 
							// Copy the result of the reduction into the variable returned by the reduction.
							reduceVar$var82$9 = (i$var79 + j$var80);
						}
						state.flips[j$var73] = DistributionSampling.sampleBernoulli(RNG$1, state.bias[reduceVar$var82$9]);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = state.m[var29];
						if(!state.fixedFlag$sample30)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var30);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noFlips, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!state.fixedFlag$sample47)
							state.bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$i$var58, int forEnd$i$var58, int threadID$i$var58, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var58 = forStart$i$var58; i$var58 < forEnd$i$var58; i$var58 += 1) {
						if(!state.fixedFlag$sample62)
							state.st[i$var58] = DistributionSampling.sampleCategorical(RNG$1, state.m[i$var58], state.noStates);
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
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = state.m[var29];
						if(!state.fixedFlag$sample30)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var30);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noFlips, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!state.fixedFlag$sample47)
							state.bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$i$var58, int forEnd$i$var58, int threadID$i$var58, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var58 = forStart$i$var58; i$var58 < forEnd$i$var58; i$var58 += 1) {
						if(!state.fixedFlag$sample62)
							state.st[i$var58] = DistributionSampling.sampleCategorical(RNG$1, state.m[i$var58], state.noStates);
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
			parallelFor(state.RNG$, 0, state.noCats, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
							if(!state.fixedFlag$sample30)
								inferSample30(var29, threadID$var29, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noFlips, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
							if(!state.fixedFlag$sample47)
								inferSample47(var45, threadID$var45, RNG$1);
						}
				}
			);
			for(int i$var58 = 0; i$var58 < state.noCats; i$var58 += 1) {
				if(!state.fixedFlag$sample62)
					inferSample62(i$var58);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i$var58 = (state.noCats - ((((state.noCats - 1) - 0) % 1) + 1)); i$var58 >= ((0 - 1) + 1); i$var58 -= 1) {
				if(!state.fixedFlag$sample62)
					inferSample62(i$var58);
			}
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noFlips, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
							if(!state.fixedFlag$sample47)
								inferSample47(var45, threadID$var45, RNG$1);
						}
				}
			);
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.noCats, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
							if(!state.fixedFlag$sample30)
								inferSample30(var29, threadID$var29, RNG$1);
						}
				}
			);
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						if(!state.constrainedFlag$sample30[((var29 - 0) / 1)])
							drawValueSample30(var29, threadID$var29, RNG$1);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.noFlips, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!state.constrainedFlag$sample47[((var45 - 0) / 1)])
							drawValueSample47(var45, threadID$var45, RNG$1);
					}
			}
		);
		for(int i$var58 = 0; i$var58 < state.noCats; i$var58 += 1) {
			if(!state.constrainedFlag$sample62[((i$var58 - 0) / 1)])
				drawValueSample62(i$var58);
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
		if(!state.fixedProbFlag$sample30)
			state.logProbability$var30 = Double.NaN;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample47)
			state.logProbability$var46 = Double.NaN;
		state.logProbability$st = 0.0;
		if(!state.fixedProbFlag$sample62) {
			for(int i$var58 = 0; i$var58 < state.noCats; i$var58 += 1)
				state.logProbability$sample62[((i$var58 - 0) / 1)] = Double.NaN;
		}
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample87) {
			for(int j$var73 = 0; j$var73 < state.noFlips; j$var73 += 1)
				state.logProbability$sample87[((j$var73 - 0) / 1)] = Double.NaN;
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.noFlips = state.length$flipsMeasured;
		state.noStates = (state.length$flipsMeasured / state.noCats);
		for(int i$var15 = 0; i$var15 < (state.length$flipsMeasured / state.noCats); i$var15 += 1)
			state.v[i$var15] = 0.1;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample47$1 = 0; index$constrainedFlag$sample47$1 < state.constrainedFlag$sample47.length; index$constrainedFlag$sample47$1 += 1)
			state.constrainedFlag$sample47[index$constrainedFlag$sample47$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample30$1 = 0; index$constrainedFlag$sample30$1 < state.constrainedFlag$sample30.length; index$constrainedFlag$sample30$1 += 1)
			state.constrainedFlag$sample30[index$constrainedFlag$sample30$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample62$1 = 0; index$constrainedFlag$sample62$1 < state.constrainedFlag$sample62.length; index$constrainedFlag$sample62$1 += 1)
			state.constrainedFlag$sample62[index$constrainedFlag$sample62$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample30)
			logProbabilityValue$sample30();
		if(state.fixedFlag$sample47)
			logProbabilityValue$sample47();
		if(state.fixedFlag$sample62)
			logProbabilityValue$sample62();
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
		logProbabilityValue$sample30();
		logProbabilityValue$sample47();
		logProbabilityValue$sample62();
		logProbabilityValue$sample87();
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
		logProbabilityValue$sample30();
		logProbabilityValue$sample47();
		logProbabilityValue$sample62();
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
		     + "model ReductionTest(boolean[] flipsMeasured, int noCats) {\n"
		     + "    int noFlips = flipsMeasured.length;\n"
		     + "    int noStates = noFlips/noCats;\n"
		     + "    \n"
		     + "    double[] v = new double[noStates];\n"
		     + "    for(int i:[0..noStates))\n"
		     + "        v[i] = 0.1;\n"
		     + "    \n"
		     + "    double[][] m = dirichlet(v).sample(noCats);\n"
		     + "    \n"
		     + "    double[] bias = beta(1.0, 1.0).sample(noFlips);\n"
		     + "    \n"
		     + "    int[] st = new int[noCats];\n"
		     + "\n"
		     + "\n"
		     + "    for(int i:[0..noCats))\n"
		     + "        st[i] = categorical(m[i]).sample();\n"
		     + "            \n"
		     + "    boolean[] flips = new boolean[noFlips];\n"
		     + "            \n"
		     + "    for(int j:[0..noFlips))\n"
		     + "        flips[j] = bernoulli(bias[sum(st)]).sample();\n"
		     + "\n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "    \n"
		     + "    private int sum(int[] a) {\n"
		     + "        return reduce(a, 0, (i,j) -> {\n"
		     + "            return i + j;\n"
		     + "        });\n"
		     + "    }\n"
		     + "}";
	}
}