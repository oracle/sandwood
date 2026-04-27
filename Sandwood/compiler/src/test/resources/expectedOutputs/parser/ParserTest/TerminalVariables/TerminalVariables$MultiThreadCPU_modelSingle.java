package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.TerminalVariables$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.TerminalVariables.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class TerminalVariables$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$var45$stateProbabilityGlobal;
		double[] cv$var50$stateProbabilityGlobal;
		double[] cv$var53$stateProbabilityGlobal;
		double[] cv$var55$stateProbabilityGlobal;
		double[] cv$var60$stateProbabilityGlobal;
		double[] cv$var65$stateProbabilityGlobal;
		double[] cv$var70$stateProbabilityGlobal;

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
			
			// Constructor for cv$var50$stateProbabilityGlobal
			{
				// Allocation of cv$var50$stateProbabilityGlobal for single threaded execution
				cv$var50$stateProbabilityGlobal = new double[2];
			}
			
			// Constructor for cv$var53$stateProbabilityGlobal
			{
				// Variable to record the maximum value of Task Get 53. Initially set to the value
				// of putTask 28.
				int cv$var43$max = 2;
				
				// Test if the input to putTask 44 is larger than the current values.
				cv$var43$max = Math.max(cv$var43$max, 2);
				
				// Allocation of cv$var53$stateProbabilityGlobal for single threaded execution
				cv$var53$stateProbabilityGlobal = new double[cv$var43$max];
			}
			
			// Constructor for cv$var55$stateProbabilityGlobal
			{
				// Allocation of cv$var55$stateProbabilityGlobal for single threaded execution
				cv$var55$stateProbabilityGlobal = new double[2];
			}
			
			// Constructor for cv$var60$stateProbabilityGlobal
			{
				// Allocation of cv$var60$stateProbabilityGlobal for single threaded execution
				cv$var60$stateProbabilityGlobal = new double[2];
			}
			
			// Constructor for cv$var65$stateProbabilityGlobal
			{
				// Allocation of cv$var65$stateProbabilityGlobal for single threaded execution
				cv$var65$stateProbabilityGlobal = new double[2];
			}
			
			// Constructor for cv$var70$stateProbabilityGlobal
			{
				// Allocation of cv$var70$stateProbabilityGlobal for single threaded execution
				cv$var70$stateProbabilityGlobal = new double[2];
			}
		}
	}


	public TerminalVariables$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample47
	private final void drawValueSample47() {
		state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	// Pick a value from the distribution for the unconditioned variable from sample52
	private final void drawValueSample52() {
		state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	// Pick a value from the distribution for the unconditioned variable from sample55
	private final void drawValueSample55() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$53_13 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 51.
		{
			{
				if((0 == state.c3))
					lengthCV$conditionals$53_13 = 2;
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 51.
		{
			{
				if((1 == state.c3))
					lengthCV$conditionals$53_13 = 2;
			}
		}
		state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_13);
	}

	// Pick a value from the distribution for the unconditioned variable from sample57
	private final void drawValueSample57() {
		state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	// Pick a value from the distribution for the unconditioned variable from sample60
	private final void drawValueSample60() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$58_9 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 56.
		{
			{
				if((0 == state.c5))
					lengthCV$conditionals$58_9 = 2;
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 56.
		{
			{
				if((1 == state.c5))
					lengthCV$conditionals$58_9 = 2;
			}
		}
		state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_9);
	}

	// Pick a value from the distribution for the unconditioned variable from sample62
	private final void drawValueSample62() {
		state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	// Pick a value from the distribution for the unconditioned variable from sample636
	private final void drawValueSample636() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$var601$634_15 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 110 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 138 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 172 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 201 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 242 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 271 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 306 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 337 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 383 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 412 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 446 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 475 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 518 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 549 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 586 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 617 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_15);
	}

	// Pick a value from the distribution for the unconditioned variable from sample65
	private final void drawValueSample65() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$63_9 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 61.
		{
			{
				if((0 == state.c7))
					lengthCV$conditionals$63_9 = 2;
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 61.
		{
			{
				if((1 == state.c7))
					lengthCV$conditionals$63_9 = 2;
			}
		}
		state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_9);
	}

	// Pick a value from the distribution for the unconditioned variable from sample67
	private final void drawValueSample67() {
		state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	// Pick a value from the distribution for the unconditioned variable from sample70
	private final void drawValueSample70() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$68_9 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 66.
		{
			{
				if((0 == state.c9))
					lengthCV$conditionals$68_9 = 2;
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 66.
		{
			{
				if((1 == state.c9))
					lengthCV$conditionals$68_9 = 2;
			}
		}
		state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_9);
	}

	// Pick a value from the distribution for the unconditioned variable from sample72
	private final void drawValueSample72() {
		state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	// Pick a value from the distribution for the unconditioned variable from sample75
	private final void drawValueSample75() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$73_9 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 71.
		{
			{
				if((0 == state.c11))
					lengthCV$conditionals$73_9 = 2;
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 71.
		{
			{
				if((1 == state.c11))
					lengthCV$conditionals$73_9 = 2;
			}
		}
		state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_9);
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
				state.c1 = cv$currentValue;
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$currentValue])) && (state.priors[cv$currentValue] <= 1.0))?Math.log(state.priors[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 47.
					{
						{
							{
								int traceTempVariable$c1$1_1 = cv$currentValue;
								
								// Processing sample task 50 of consumer random variable null.
								{
									{
										// Flag recording if this sample task of the consuming random variable is constrained.
										boolean cv$sampleConstrained = true;
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
																double[] var46 = state.conditionals[traceTempVariable$c1$1_1];
																
																// Allocate a local variable to hold the length of the array.
																int lengthCV$conditionals$48_4 = -1;
																
																// calculate array length.
																// 
																// Looking for a path between Put 28 and consumer double[] 46.
																{
																	{
																		if((0 == traceTempVariable$c1$1_1))
																			lengthCV$conditionals$48_4 = 2;
																	}
																}
																
																// Looking for a path between Put 44 and consumer double[] 46.
																{
																	{
																		if((1 == traceTempVariable$c1$1_1))
																			lengthCV$conditionals$48_4 = 2;
																	}
																}
																
																// Record the probability of sample task 50 generating output with current configuration.
																if(((Math.log(1.0) + ((((((0.0 <= state.c2) && (state.c2 < lengthCV$conditionals$48_4)) && (0 < lengthCV$conditionals$48_4)) && (0.0 <= var46[state.c2])) && (var46[state.c2] <= 1.0))?Math.log(var46[state.c2]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.c2) && (state.c2 < lengthCV$conditionals$48_4)) && (0 < lengthCV$conditionals$48_4)) && (0.0 <= var46[state.c2])) && (var46[state.c2] <= 1.0))?Math.log(var46[state.c2]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.c2) && (state.c2 < lengthCV$conditionals$48_4)) && (0 < lengthCV$conditionals$48_4)) && (0.0 <= var46[state.c2])) && (var46[state.c2] <= 1.0))?Math.log(var46[state.c2]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.c2) && (state.c2 < lengthCV$conditionals$48_4)) && (0 < lengthCV$conditionals$48_4)) && (0.0 <= var46[state.c2])) && (var46[state.c2] <= 1.0))?Math.log(var46[state.c2]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.c2) && (state.c2 < lengthCV$conditionals$48_4)) && (0 < lengthCV$conditionals$48_4)) && (0.0 <= var46[state.c2])) && (var46[state.c2] <= 1.0))?Math.log(var46[state.c2]):Double.NEGATIVE_INFINITY)));
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
					
					// Processing random variable 603.
					{
						{
							{
								int traceTempVariable$c1$6_1 = cv$currentValue;
								
								// Processing sample task 636 of consumer random variable null.
								{
									{
										// Flag recording if this sample task of the consuming random variable is constrained.
										boolean cv$sampleConstrained = state.fixedFlag$sample636;
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
																double[] var602 = state.a[state.c5][state.c9][traceTempVariable$c1$6_1][state.c4];
																
																// Allocate a local variable to hold the length of the array.
																int lengthCV$var601$634_11 = -1;
																
																// calculate array length.
																// 
																// Looking for a path between Put 110 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == state.c9)) {
																				if((0 == traceTempVariable$c1$6_1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 138 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == state.c9)) {
																				if((0 == traceTempVariable$c1$6_1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 172 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == state.c9)) {
																				if((1 == traceTempVariable$c1$6_1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 201 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == state.c9)) {
																				if((1 == traceTempVariable$c1$6_1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 242 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == state.c9)) {
																				if((0 == traceTempVariable$c1$6_1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 271 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == state.c9)) {
																				if((0 == traceTempVariable$c1$6_1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 306 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == state.c9)) {
																				if((1 == traceTempVariable$c1$6_1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 337 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == state.c9)) {
																				if((1 == traceTempVariable$c1$6_1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 383 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == state.c9)) {
																				if((0 == traceTempVariable$c1$6_1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 412 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == state.c9)) {
																				if((0 == traceTempVariable$c1$6_1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 446 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == state.c9)) {
																				if((1 == traceTempVariable$c1$6_1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 475 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == state.c9)) {
																				if((1 == traceTempVariable$c1$6_1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 518 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == state.c9)) {
																				if((0 == traceTempVariable$c1$6_1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 549 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == state.c9)) {
																				if((0 == traceTempVariable$c1$6_1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 586 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == state.c9)) {
																				if((1 == traceTempVariable$c1$6_1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 617 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == state.c9)) {
																				if((1 == traceTempVariable$c1$6_1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Record the probability of sample task 636 generating output with current configuration.
																if(((Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_11)) && (0 < lengthCV$var601$634_11)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_11)) && (0 < lengthCV$var601$634_11)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_11)) && (0 < lengthCV$var601$634_11)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_11)) && (0 < lengthCV$var601$634_11)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_11)) && (0 < lengthCV$var601$634_11)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)));
																}
																
																// Recorded the probability of reaching sample task 636 with the current configuration.
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
				state.c1 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 52 drawn from Categorical 49. Inference was performed using variable
	// marginalization.
	private final void inferSample52() {
		if(true) {
			state.constrainedFlag$sample52 = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = scratch.cv$var50$stateProbabilityGlobal;
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
				state.c3 = cv$currentValue;
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$currentValue])) && (state.priors[cv$currentValue] <= 1.0))?Math.log(state.priors[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 52.
					{
						{
							{
								int traceTempVariable$c3$1_1 = cv$currentValue;
								
								// Processing sample task 55 of consumer random variable null.
								{
									{
										// Flag recording if this sample task of the consuming random variable is constrained.
										boolean cv$sampleConstrained = (state.fixedFlag$sample55 || state.constrainedFlag$sample55);
										if(cv$sampleConstrained) {
											// Mark that the sample has observed constrained data.
											state.constrainedFlag$sample52 = true;
											
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
																double[] var51 = state.conditionals[traceTempVariable$c3$1_1];
																
																// Allocate a local variable to hold the length of the array.
																int lengthCV$conditionals$53_10 = -1;
																
																// calculate array length.
																// 
																// Looking for a path between Put 28 and consumer double[] 51.
																{
																	{
																		if((0 == traceTempVariable$c3$1_1))
																			lengthCV$conditionals$53_10 = 2;
																	}
																}
																
																// Looking for a path between Put 44 and consumer double[] 51.
																{
																	{
																		if((1 == traceTempVariable$c3$1_1))
																			lengthCV$conditionals$53_10 = 2;
																	}
																}
																
																// Record the probability of sample task 55 generating output with current configuration.
																if(((Math.log(1.0) + ((((((0.0 <= state.c4) && (state.c4 < lengthCV$conditionals$53_10)) && (0 < lengthCV$conditionals$53_10)) && (0.0 <= var51[state.c4])) && (var51[state.c4] <= 1.0))?Math.log(var51[state.c4]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.c4) && (state.c4 < lengthCV$conditionals$53_10)) && (0 < lengthCV$conditionals$53_10)) && (0.0 <= var51[state.c4])) && (var51[state.c4] <= 1.0))?Math.log(var51[state.c4]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.c4) && (state.c4 < lengthCV$conditionals$53_10)) && (0 < lengthCV$conditionals$53_10)) && (0.0 <= var51[state.c4])) && (var51[state.c4] <= 1.0))?Math.log(var51[state.c4]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.c4) && (state.c4 < lengthCV$conditionals$53_10)) && (0 < lengthCV$conditionals$53_10)) && (0.0 <= var51[state.c4])) && (var51[state.c4] <= 1.0))?Math.log(var51[state.c4]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.c4) && (state.c4 < lengthCV$conditionals$53_10)) && (0 < lengthCV$conditionals$53_10)) && (0.0 <= var51[state.c4])) && (var51[state.c4] <= 1.0))?Math.log(var51[state.c4]):Double.NEGATIVE_INFINITY)));
																}
																
																// Recorded the probability of reaching sample task 55 with the current configuration.
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
			if(state.constrainedFlag$sample52) {
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
				state.c3 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 55 drawn from Categorical 52. Inference was performed using variable
	// marginalization.
	private final void inferSample55() {
		if(true) {
			state.constrainedFlag$sample55 = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// Allocate a local variable to hold the length of the array.
				int lengthCV$conditionals$53_11 = -1;
				
				// calculate array length.
				// 
				// Looking for a path between Put 28 and consumer double[] 51.
				{
					{
						if((0 == state.c3))
							lengthCV$conditionals$53_11 = 2;
					}
				}
				
				// Looking for a path between Put 44 and consumer double[] 51.
				{
					{
						if((1 == state.c3))
							lengthCV$conditionals$53_11 = 2;
					}
				}
				
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, lengthCV$conditionals$53_11);
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
				
				// Write out the new value of the sample.
				state.c4 = cv$currentValue;
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// Constructing a random variable input for use later.
					double[] var51 = state.conditionals[state.c3];
					
					// Allocate a local variable to hold the length of the array.
					int lengthCV$conditionals$53_12 = -1;
					
					// calculate array length.
					// 
					// Looking for a path between Put 28 and consumer double[] 51.
					{
						{
							if((0 == state.c3))
								lengthCV$conditionals$53_12 = 2;
						}
					}
					
					// Looking for a path between Put 44 and consumer double[] 51.
					{
						{
							if((1 == state.c3))
								lengthCV$conditionals$53_12 = 2;
						}
					}
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < lengthCV$conditionals$53_12)) && (0 < lengthCV$conditionals$53_12)) && (0.0 <= var51[cv$currentValue])) && (var51[cv$currentValue] <= 1.0))?Math.log(var51[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 603.
					{
						{
							{
								int traceTempVariable$c4$5_1 = cv$currentValue;
								
								// Processing sample task 636 of consumer random variable null.
								{
									{
										// Flag recording if this sample task of the consuming random variable is constrained.
										boolean cv$sampleConstrained = state.fixedFlag$sample636;
										if(cv$sampleConstrained) {
											// Mark that the sample has observed constrained data.
											state.constrainedFlag$sample55 = true;
											
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
																double[] var602 = state.a[state.c5][state.c9][state.c1][traceTempVariable$c4$5_1];
																
																// Allocate a local variable to hold the length of the array.
																int lengthCV$var601$634_12 = -1;
																
																// calculate array length.
																// 
																// Looking for a path between Put 110 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == state.c9)) {
																				if((0 == state.c1)) {
																					if((0 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 138 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == state.c9)) {
																				if((0 == state.c1)) {
																					if((1 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 172 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == state.c9)) {
																				if((1 == state.c1)) {
																					if((0 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 201 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == state.c9)) {
																				if((1 == state.c1)) {
																					if((1 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 242 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == state.c9)) {
																				if((0 == state.c1)) {
																					if((0 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 271 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == state.c9)) {
																				if((0 == state.c1)) {
																					if((1 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 306 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == state.c9)) {
																				if((1 == state.c1)) {
																					if((0 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 337 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == state.c9)) {
																				if((1 == state.c1)) {
																					if((1 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 383 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == state.c9)) {
																				if((0 == state.c1)) {
																					if((0 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 412 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == state.c9)) {
																				if((0 == state.c1)) {
																					if((1 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 446 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == state.c9)) {
																				if((1 == state.c1)) {
																					if((0 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 475 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == state.c9)) {
																				if((1 == state.c1)) {
																					if((1 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 518 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == state.c9)) {
																				if((0 == state.c1)) {
																					if((0 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 549 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == state.c9)) {
																				if((0 == state.c1)) {
																					if((1 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 586 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == state.c9)) {
																				if((1 == state.c1)) {
																					if((0 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 617 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == state.c9)) {
																				if((1 == state.c1)) {
																					if((1 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Record the probability of sample task 636 generating output with current configuration.
																if(((Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_12)) && (0 < lengthCV$var601$634_12)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_12)) && (0 < lengthCV$var601$634_12)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_12)) && (0 < lengthCV$var601$634_12)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_12)) && (0 < lengthCV$var601$634_12)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_12)) && (0 < lengthCV$var601$634_12)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)));
																}
																
																// Recorded the probability of reaching sample task 636 with the current configuration.
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
			if(state.constrainedFlag$sample55) {
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
				state.c4 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 57 drawn from Categorical 54. Inference was performed using variable
	// marginalization.
	private final void inferSample57() {
		if(true) {
			state.constrainedFlag$sample57 = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = scratch.cv$var55$stateProbabilityGlobal;
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
				state.c5 = cv$currentValue;
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$currentValue])) && (state.priors[cv$currentValue] <= 1.0))?Math.log(state.priors[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 57.
					{
						{
							{
								int traceTempVariable$c5$1_1 = cv$currentValue;
								
								// Processing sample task 60 of consumer random variable null.
								{
									{
										// Flag recording if this sample task of the consuming random variable is constrained.
										boolean cv$sampleConstrained = state.fixedFlag$sample60;
										if(cv$sampleConstrained) {
											// Mark that the sample has observed constrained data.
											state.constrainedFlag$sample57 = true;
											
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
																double[] var56 = state.conditionals[traceTempVariable$c5$1_1];
																
																// Allocate a local variable to hold the length of the array.
																int lengthCV$conditionals$58_8 = -1;
																
																// calculate array length.
																// 
																// Looking for a path between Put 28 and consumer double[] 56.
																{
																	{
																		if((0 == traceTempVariable$c5$1_1))
																			lengthCV$conditionals$58_8 = 2;
																	}
																}
																
																// Looking for a path between Put 44 and consumer double[] 56.
																{
																	{
																		if((1 == traceTempVariable$c5$1_1))
																			lengthCV$conditionals$58_8 = 2;
																	}
																}
																
																// Record the probability of sample task 60 generating output with current configuration.
																if(((Math.log(1.0) + ((((((0.0 <= state.c6) && (state.c6 < lengthCV$conditionals$58_8)) && (0 < lengthCV$conditionals$58_8)) && (0.0 <= var56[state.c6])) && (var56[state.c6] <= 1.0))?Math.log(var56[state.c6]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.c6) && (state.c6 < lengthCV$conditionals$58_8)) && (0 < lengthCV$conditionals$58_8)) && (0.0 <= var56[state.c6])) && (var56[state.c6] <= 1.0))?Math.log(var56[state.c6]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.c6) && (state.c6 < lengthCV$conditionals$58_8)) && (0 < lengthCV$conditionals$58_8)) && (0.0 <= var56[state.c6])) && (var56[state.c6] <= 1.0))?Math.log(var56[state.c6]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.c6) && (state.c6 < lengthCV$conditionals$58_8)) && (0 < lengthCV$conditionals$58_8)) && (0.0 <= var56[state.c6])) && (var56[state.c6] <= 1.0))?Math.log(var56[state.c6]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.c6) && (state.c6 < lengthCV$conditionals$58_8)) && (0 < lengthCV$conditionals$58_8)) && (0.0 <= var56[state.c6])) && (var56[state.c6] <= 1.0))?Math.log(var56[state.c6]):Double.NEGATIVE_INFINITY)));
																}
																
																// Recorded the probability of reaching sample task 60 with the current configuration.
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
					
					// Processing random variable 603.
					{
						{
							{
								int traceTempVariable$c5$6_1 = cv$currentValue;
								
								// Processing sample task 636 of consumer random variable null.
								{
									{
										// Flag recording if this sample task of the consuming random variable is constrained.
										boolean cv$sampleConstrained = state.fixedFlag$sample636;
										if(cv$sampleConstrained) {
											// Mark that the sample has observed constrained data.
											state.constrainedFlag$sample57 = true;
											
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
																double[] var602 = state.a[traceTempVariable$c5$6_1][state.c9][state.c1][state.c4];
																
																// Allocate a local variable to hold the length of the array.
																int lengthCV$var601$634_13 = -1;
																
																// calculate array length.
																// 
																// Looking for a path between Put 110 and consumer double[] 602.
																{
																	{
																		if((0 == traceTempVariable$c5$6_1)) {
																			if((0 == state.c9)) {
																				if((0 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 138 and consumer double[] 602.
																{
																	{
																		if((0 == traceTempVariable$c5$6_1)) {
																			if((0 == state.c9)) {
																				if((0 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 172 and consumer double[] 602.
																{
																	{
																		if((0 == traceTempVariable$c5$6_1)) {
																			if((0 == state.c9)) {
																				if((1 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 201 and consumer double[] 602.
																{
																	{
																		if((0 == traceTempVariable$c5$6_1)) {
																			if((0 == state.c9)) {
																				if((1 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 242 and consumer double[] 602.
																{
																	{
																		if((0 == traceTempVariable$c5$6_1)) {
																			if((1 == state.c9)) {
																				if((0 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 271 and consumer double[] 602.
																{
																	{
																		if((0 == traceTempVariable$c5$6_1)) {
																			if((1 == state.c9)) {
																				if((0 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 306 and consumer double[] 602.
																{
																	{
																		if((0 == traceTempVariable$c5$6_1)) {
																			if((1 == state.c9)) {
																				if((1 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 337 and consumer double[] 602.
																{
																	{
																		if((0 == traceTempVariable$c5$6_1)) {
																			if((1 == state.c9)) {
																				if((1 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 383 and consumer double[] 602.
																{
																	{
																		if((1 == traceTempVariable$c5$6_1)) {
																			if((0 == state.c9)) {
																				if((0 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 412 and consumer double[] 602.
																{
																	{
																		if((1 == traceTempVariable$c5$6_1)) {
																			if((0 == state.c9)) {
																				if((0 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 446 and consumer double[] 602.
																{
																	{
																		if((1 == traceTempVariable$c5$6_1)) {
																			if((0 == state.c9)) {
																				if((1 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 475 and consumer double[] 602.
																{
																	{
																		if((1 == traceTempVariable$c5$6_1)) {
																			if((0 == state.c9)) {
																				if((1 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 518 and consumer double[] 602.
																{
																	{
																		if((1 == traceTempVariable$c5$6_1)) {
																			if((1 == state.c9)) {
																				if((0 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 549 and consumer double[] 602.
																{
																	{
																		if((1 == traceTempVariable$c5$6_1)) {
																			if((1 == state.c9)) {
																				if((0 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 586 and consumer double[] 602.
																{
																	{
																		if((1 == traceTempVariable$c5$6_1)) {
																			if((1 == state.c9)) {
																				if((1 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 617 and consumer double[] 602.
																{
																	{
																		if((1 == traceTempVariable$c5$6_1)) {
																			if((1 == state.c9)) {
																				if((1 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Record the probability of sample task 636 generating output with current configuration.
																if(((Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_13)) && (0 < lengthCV$var601$634_13)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_13)) && (0 < lengthCV$var601$634_13)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_13)) && (0 < lengthCV$var601$634_13)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_13)) && (0 < lengthCV$var601$634_13)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_13)) && (0 < lengthCV$var601$634_13)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)));
																}
																
																// Recorded the probability of reaching sample task 636 with the current configuration.
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
			if(state.constrainedFlag$sample57) {
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
				state.c5 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 62 drawn from Categorical 59. Inference was performed using variable
	// marginalization.
	private final void inferSample62() {
		if(true) {
			state.constrainedFlag$sample62 = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = scratch.cv$var60$stateProbabilityGlobal;
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
				state.c7 = cv$currentValue;
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$currentValue])) && (state.priors[cv$currentValue] <= 1.0))?Math.log(state.priors[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 62.
					{
						{
							{
								int traceTempVariable$c7$1_1 = cv$currentValue;
								
								// Processing sample task 65 of consumer random variable null.
								{
									{
										// Flag recording if this sample task of the consuming random variable is constrained.
										boolean cv$sampleConstrained = state.fixedFlag$sample65;
										if(cv$sampleConstrained) {
											// Mark that the sample has observed constrained data.
											state.constrainedFlag$sample62 = true;
											
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
																double[] var61 = state.conditionals[traceTempVariable$c7$1_1];
																
																// Allocate a local variable to hold the length of the array.
																int lengthCV$conditionals$63_8 = -1;
																
																// calculate array length.
																// 
																// Looking for a path between Put 28 and consumer double[] 61.
																{
																	{
																		if((0 == traceTempVariable$c7$1_1))
																			lengthCV$conditionals$63_8 = 2;
																	}
																}
																
																// Looking for a path between Put 44 and consumer double[] 61.
																{
																	{
																		if((1 == traceTempVariable$c7$1_1))
																			lengthCV$conditionals$63_8 = 2;
																	}
																}
																
																// Record the probability of sample task 65 generating output with current configuration.
																if(((Math.log(1.0) + ((((((0.0 <= state.c8) && (state.c8 < lengthCV$conditionals$63_8)) && (0 < lengthCV$conditionals$63_8)) && (0.0 <= var61[state.c8])) && (var61[state.c8] <= 1.0))?Math.log(var61[state.c8]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.c8) && (state.c8 < lengthCV$conditionals$63_8)) && (0 < lengthCV$conditionals$63_8)) && (0.0 <= var61[state.c8])) && (var61[state.c8] <= 1.0))?Math.log(var61[state.c8]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.c8) && (state.c8 < lengthCV$conditionals$63_8)) && (0 < lengthCV$conditionals$63_8)) && (0.0 <= var61[state.c8])) && (var61[state.c8] <= 1.0))?Math.log(var61[state.c8]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.c8) && (state.c8 < lengthCV$conditionals$63_8)) && (0 < lengthCV$conditionals$63_8)) && (0.0 <= var61[state.c8])) && (var61[state.c8] <= 1.0))?Math.log(var61[state.c8]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.c8) && (state.c8 < lengthCV$conditionals$63_8)) && (0 < lengthCV$conditionals$63_8)) && (0.0 <= var61[state.c8])) && (var61[state.c8] <= 1.0))?Math.log(var61[state.c8]):Double.NEGATIVE_INFINITY)));
																}
																
																// Recorded the probability of reaching sample task 65 with the current configuration.
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
			if(state.constrainedFlag$sample62) {
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
				state.c7 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 67 drawn from Categorical 64. Inference was performed using variable
	// marginalization.
	private final void inferSample67() {
		if(true) {
			state.constrainedFlag$sample67 = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = scratch.cv$var65$stateProbabilityGlobal;
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
				state.c9 = cv$currentValue;
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$currentValue])) && (state.priors[cv$currentValue] <= 1.0))?Math.log(state.priors[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 67.
					{
						{
							{
								int traceTempVariable$c9$1_1 = cv$currentValue;
								
								// Processing sample task 70 of consumer random variable null.
								{
									{
										// Flag recording if this sample task of the consuming random variable is constrained.
										boolean cv$sampleConstrained = state.fixedFlag$sample70;
										if(cv$sampleConstrained) {
											// Mark that the sample has observed constrained data.
											state.constrainedFlag$sample67 = true;
											
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
																double[] var66 = state.conditionals[traceTempVariable$c9$1_1];
																
																// Allocate a local variable to hold the length of the array.
																int lengthCV$conditionals$68_8 = -1;
																
																// calculate array length.
																// 
																// Looking for a path between Put 28 and consumer double[] 66.
																{
																	{
																		if((0 == traceTempVariable$c9$1_1))
																			lengthCV$conditionals$68_8 = 2;
																	}
																}
																
																// Looking for a path between Put 44 and consumer double[] 66.
																{
																	{
																		if((1 == traceTempVariable$c9$1_1))
																			lengthCV$conditionals$68_8 = 2;
																	}
																}
																
																// Record the probability of sample task 70 generating output with current configuration.
																if(((Math.log(1.0) + ((((((0.0 <= state.c10) && (state.c10 < lengthCV$conditionals$68_8)) && (0 < lengthCV$conditionals$68_8)) && (0.0 <= var66[state.c10])) && (var66[state.c10] <= 1.0))?Math.log(var66[state.c10]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.c10) && (state.c10 < lengthCV$conditionals$68_8)) && (0 < lengthCV$conditionals$68_8)) && (0.0 <= var66[state.c10])) && (var66[state.c10] <= 1.0))?Math.log(var66[state.c10]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.c10) && (state.c10 < lengthCV$conditionals$68_8)) && (0 < lengthCV$conditionals$68_8)) && (0.0 <= var66[state.c10])) && (var66[state.c10] <= 1.0))?Math.log(var66[state.c10]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.c10) && (state.c10 < lengthCV$conditionals$68_8)) && (0 < lengthCV$conditionals$68_8)) && (0.0 <= var66[state.c10])) && (var66[state.c10] <= 1.0))?Math.log(var66[state.c10]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.c10) && (state.c10 < lengthCV$conditionals$68_8)) && (0 < lengthCV$conditionals$68_8)) && (0.0 <= var66[state.c10])) && (var66[state.c10] <= 1.0))?Math.log(var66[state.c10]):Double.NEGATIVE_INFINITY)));
																}
																
																// Recorded the probability of reaching sample task 70 with the current configuration.
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
					
					// Processing random variable 603.
					{
						{
							{
								int traceTempVariable$c9$6_1 = cv$currentValue;
								
								// Processing sample task 636 of consumer random variable null.
								{
									{
										// Flag recording if this sample task of the consuming random variable is constrained.
										boolean cv$sampleConstrained = state.fixedFlag$sample636;
										if(cv$sampleConstrained) {
											// Mark that the sample has observed constrained data.
											state.constrainedFlag$sample67 = true;
											
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
																double[] var602 = state.a[state.c5][traceTempVariable$c9$6_1][state.c1][state.c4];
																
																// Allocate a local variable to hold the length of the array.
																int lengthCV$var601$634_14 = -1;
																
																// calculate array length.
																// 
																// Looking for a path between Put 110 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == traceTempVariable$c9$6_1)) {
																				if((0 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 138 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == traceTempVariable$c9$6_1)) {
																				if((0 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 172 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == traceTempVariable$c9$6_1)) {
																				if((1 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 201 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == traceTempVariable$c9$6_1)) {
																				if((1 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 242 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == traceTempVariable$c9$6_1)) {
																				if((0 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 271 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == traceTempVariable$c9$6_1)) {
																				if((0 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 306 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == traceTempVariable$c9$6_1)) {
																				if((1 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 337 and consumer double[] 602.
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == traceTempVariable$c9$6_1)) {
																				if((1 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 383 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == traceTempVariable$c9$6_1)) {
																				if((0 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 412 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == traceTempVariable$c9$6_1)) {
																				if((0 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 446 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == traceTempVariable$c9$6_1)) {
																				if((1 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 475 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == traceTempVariable$c9$6_1)) {
																				if((1 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 518 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == traceTempVariable$c9$6_1)) {
																				if((0 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 549 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == traceTempVariable$c9$6_1)) {
																				if((0 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 586 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == traceTempVariable$c9$6_1)) {
																				if((1 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Looking for a path between Put 617 and consumer double[] 602.
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == traceTempVariable$c9$6_1)) {
																				if((1 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																
																// Record the probability of sample task 636 generating output with current configuration.
																if(((Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_14)) && (0 < lengthCV$var601$634_14)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_14)) && (0 < lengthCV$var601$634_14)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_14)) && (0 < lengthCV$var601$634_14)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_14)) && (0 < lengthCV$var601$634_14)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_14)) && (0 < lengthCV$var601$634_14)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)));
																}
																
																// Recorded the probability of reaching sample task 636 with the current configuration.
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
			if(state.constrainedFlag$sample67) {
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
				state.c9 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 72 drawn from Categorical 69. Inference was performed using variable
	// marginalization.
	private final void inferSample72() {
		if(true) {
			state.constrainedFlag$sample72 = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, 2);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = scratch.cv$var70$stateProbabilityGlobal;
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
				state.c11 = cv$currentValue;
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$currentValue])) && (state.priors[cv$currentValue] <= 1.0))?Math.log(state.priors[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 72.
					{
						{
							{
								int traceTempVariable$c11$1_1 = cv$currentValue;
								
								// Processing sample task 75 of consumer random variable null.
								{
									{
										// Flag recording if this sample task of the consuming random variable is constrained.
										boolean cv$sampleConstrained = state.fixedFlag$sample75;
										if(cv$sampleConstrained) {
											// Mark that the sample has observed constrained data.
											state.constrainedFlag$sample72 = true;
											
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
																double[] var71 = state.conditionals[traceTempVariable$c11$1_1];
																
																// Allocate a local variable to hold the length of the array.
																int lengthCV$conditionals$73_8 = -1;
																
																// calculate array length.
																// 
																// Looking for a path between Put 28 and consumer double[] 71.
																{
																	{
																		if((0 == traceTempVariable$c11$1_1))
																			lengthCV$conditionals$73_8 = 2;
																	}
																}
																
																// Looking for a path between Put 44 and consumer double[] 71.
																{
																	{
																		if((1 == traceTempVariable$c11$1_1))
																			lengthCV$conditionals$73_8 = 2;
																	}
																}
																
																// Record the probability of sample task 75 generating output with current configuration.
																if(((Math.log(1.0) + ((((((0.0 <= state.c12) && (state.c12 < lengthCV$conditionals$73_8)) && (0 < lengthCV$conditionals$73_8)) && (0.0 <= var71[state.c12])) && (var71[state.c12] <= 1.0))?Math.log(var71[state.c12]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.c12) && (state.c12 < lengthCV$conditionals$73_8)) && (0 < lengthCV$conditionals$73_8)) && (0.0 <= var71[state.c12])) && (var71[state.c12] <= 1.0))?Math.log(var71[state.c12]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.c12) && (state.c12 < lengthCV$conditionals$73_8)) && (0 < lengthCV$conditionals$73_8)) && (0.0 <= var71[state.c12])) && (var71[state.c12] <= 1.0))?Math.log(var71[state.c12]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.c12) && (state.c12 < lengthCV$conditionals$73_8)) && (0 < lengthCV$conditionals$73_8)) && (0.0 <= var71[state.c12])) && (var71[state.c12] <= 1.0))?Math.log(var71[state.c12]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.c12) && (state.c12 < lengthCV$conditionals$73_8)) && (0 < lengthCV$conditionals$73_8)) && (0.0 <= var71[state.c12])) && (var71[state.c12] <= 1.0))?Math.log(var71[state.c12]):Double.NEGATIVE_INFINITY)));
																}
																
																// Recorded the probability of reaching sample task 75 with the current configuration.
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
			if(state.constrainedFlag$sample72) {
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
				state.c11 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
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
					int cv$sampleValue = state.c1;
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$sampleValue])) && (state.priors[cv$sampleValue] <= 1.0))?Math.log(state.priors[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			state.logProbability$c1 = cv$sampleProbability;
			
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
			double cv$sampleValue = state.logProbability$c1;
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
					int cv$sampleValue = state.c2;
					{
						{
							double[] var46 = state.conditionals[state.c1];
							
							// Allocate a local variable to hold the length of the array.
							int lengthCV$conditionals$48_5 = -1;
							
							// calculate array length.
							// 
							// Looking for a path between Put 28 and consumer double[] 46.
							{
								{
									if((0 == state.c1))
										lengthCV$conditionals$48_5 = 2;
								}
							}
							
							// Looking for a path between Put 44 and consumer double[] 46.
							{
								{
									if((1 == state.c1))
										lengthCV$conditionals$48_5 = 2;
								}
							}
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$conditionals$48_5)) && (0 < lengthCV$conditionals$48_5)) && (0.0 <= var46[cv$sampleValue])) && (var46[cv$sampleValue] <= 1.0))?Math.log(var46[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			state.logProbability$c2 = cv$sampleProbability;
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample50 = state.fixedFlag$sample47;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c2;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
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
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = state.c3;
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$sampleValue])) && (state.priors[cv$sampleValue] <= 1.0))?Math.log(state.priors[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			state.logProbability$c3 = cv$sampleProbability;
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample52)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample52 = state.fixedFlag$sample52;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c3;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample52)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample55 using sampled
	// values.
	private final void logProbabilityValue$sample55() {
		// Determine if we need to calculate the values for sample task 55 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample55) {
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
					int cv$sampleValue = state.c4;
					{
						{
							double[] var51 = state.conditionals[state.c3];
							
							// Allocate a local variable to hold the length of the array.
							int lengthCV$conditionals$53_14 = -1;
							
							// calculate array length.
							// 
							// Looking for a path between Put 28 and consumer double[] 51.
							{
								{
									if((0 == state.c3))
										lengthCV$conditionals$53_14 = 2;
								}
							}
							
							// Looking for a path between Put 44 and consumer double[] 51.
							{
								{
									if((1 == state.c3))
										lengthCV$conditionals$53_14 = 2;
								}
							}
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$conditionals$53_14)) && (0 < lengthCV$conditionals$53_14)) && (0.0 <= var51[cv$sampleValue])) && (var51[cv$sampleValue] <= 1.0))?Math.log(var51[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			state.logProbability$c4 = cv$sampleProbability;
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample55)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample55 = (state.fixedFlag$sample55 && state.fixedFlag$sample52);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c4;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample55)
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
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = state.c5;
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$sampleValue])) && (state.priors[cv$sampleValue] <= 1.0))?Math.log(state.priors[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			state.logProbability$c5 = cv$sampleProbability;
			
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
			double cv$sampleValue = state.logProbability$c5;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample60 using sampled
	// values.
	private final void logProbabilityValue$sample60() {
		// Determine if we need to calculate the values for sample task 60 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample60) {
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
					int cv$sampleValue = state.c6;
					{
						{
							double[] var56 = state.conditionals[state.c5];
							
							// Allocate a local variable to hold the length of the array.
							int lengthCV$conditionals$58_10 = -1;
							
							// calculate array length.
							// 
							// Looking for a path between Put 28 and consumer double[] 56.
							{
								{
									if((0 == state.c5))
										lengthCV$conditionals$58_10 = 2;
								}
							}
							
							// Looking for a path between Put 44 and consumer double[] 56.
							{
								{
									if((1 == state.c5))
										lengthCV$conditionals$58_10 = 2;
								}
							}
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$conditionals$58_10)) && (0 < lengthCV$conditionals$58_10)) && (0.0 <= var56[cv$sampleValue])) && (var56[cv$sampleValue] <= 1.0))?Math.log(var56[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			state.logProbability$c6 = cv$sampleProbability;
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample60)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample60 = (state.fixedFlag$sample60 && state.fixedFlag$sample57);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c6;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample60)
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
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = state.c7;
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$sampleValue])) && (state.priors[cv$sampleValue] <= 1.0))?Math.log(state.priors[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			state.logProbability$c7 = cv$sampleProbability;
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample62)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample62 = state.fixedFlag$sample62;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c7;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample62)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample636 using sampled
	// values.
	private final void logProbabilityValue$sample636() {
		// Determine if we need to calculate the values for sample task 636 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample636) {
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
					int cv$sampleValue = state.terminalVariable;
					{
						{
							double[] var602 = state.a[state.c5][state.c9][state.c1][state.c4];
							
							// Allocate a local variable to hold the length of the array.
							int lengthCV$var601$634_16 = -1;
							
							// calculate array length.
							// 
							// Looking for a path between Put 110 and consumer double[] 602.
							{
								{
									if((0 == state.c5)) {
										if((0 == state.c9)) {
											if((0 == state.c1)) {
												if((0 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							
							// Looking for a path between Put 138 and consumer double[] 602.
							{
								{
									if((0 == state.c5)) {
										if((0 == state.c9)) {
											if((0 == state.c1)) {
												if((1 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							
							// Looking for a path between Put 172 and consumer double[] 602.
							{
								{
									if((0 == state.c5)) {
										if((0 == state.c9)) {
											if((1 == state.c1)) {
												if((0 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							
							// Looking for a path between Put 201 and consumer double[] 602.
							{
								{
									if((0 == state.c5)) {
										if((0 == state.c9)) {
											if((1 == state.c1)) {
												if((1 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							
							// Looking for a path between Put 242 and consumer double[] 602.
							{
								{
									if((0 == state.c5)) {
										if((1 == state.c9)) {
											if((0 == state.c1)) {
												if((0 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							
							// Looking for a path between Put 271 and consumer double[] 602.
							{
								{
									if((0 == state.c5)) {
										if((1 == state.c9)) {
											if((0 == state.c1)) {
												if((1 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							
							// Looking for a path between Put 306 and consumer double[] 602.
							{
								{
									if((0 == state.c5)) {
										if((1 == state.c9)) {
											if((1 == state.c1)) {
												if((0 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							
							// Looking for a path between Put 337 and consumer double[] 602.
							{
								{
									if((0 == state.c5)) {
										if((1 == state.c9)) {
											if((1 == state.c1)) {
												if((1 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							
							// Looking for a path between Put 383 and consumer double[] 602.
							{
								{
									if((1 == state.c5)) {
										if((0 == state.c9)) {
											if((0 == state.c1)) {
												if((0 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							
							// Looking for a path between Put 412 and consumer double[] 602.
							{
								{
									if((1 == state.c5)) {
										if((0 == state.c9)) {
											if((0 == state.c1)) {
												if((1 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							
							// Looking for a path between Put 446 and consumer double[] 602.
							{
								{
									if((1 == state.c5)) {
										if((0 == state.c9)) {
											if((1 == state.c1)) {
												if((0 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							
							// Looking for a path between Put 475 and consumer double[] 602.
							{
								{
									if((1 == state.c5)) {
										if((0 == state.c9)) {
											if((1 == state.c1)) {
												if((1 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							
							// Looking for a path between Put 518 and consumer double[] 602.
							{
								{
									if((1 == state.c5)) {
										if((1 == state.c9)) {
											if((0 == state.c1)) {
												if((0 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							
							// Looking for a path between Put 549 and consumer double[] 602.
							{
								{
									if((1 == state.c5)) {
										if((1 == state.c9)) {
											if((0 == state.c1)) {
												if((1 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							
							// Looking for a path between Put 586 and consumer double[] 602.
							{
								{
									if((1 == state.c5)) {
										if((1 == state.c9)) {
											if((1 == state.c1)) {
												if((0 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							
							// Looking for a path between Put 617 and consumer double[] 602.
							{
								{
									if((1 == state.c5)) {
										if((1 == state.c9)) {
											if((1 == state.c1)) {
												if((1 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$var601$634_16)) && (0 < lengthCV$var601$634_16)) && (0.0 <= var602[cv$sampleValue])) && (var602[cv$sampleValue] <= 1.0))?Math.log(var602[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			state.logProbability$terminalVariable = cv$sampleProbability;
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample636)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample636 = ((((state.fixedFlag$sample636 && state.fixedFlag$sample47) && state.fixedFlag$sample55) && state.fixedFlag$sample57) && state.fixedFlag$sample67);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$terminalVariable;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample636)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample65 using sampled
	// values.
	private final void logProbabilityValue$sample65() {
		// Determine if we need to calculate the values for sample task 65 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample65) {
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
					int cv$sampleValue = state.c8;
					{
						{
							double[] var61 = state.conditionals[state.c7];
							
							// Allocate a local variable to hold the length of the array.
							int lengthCV$conditionals$63_10 = -1;
							
							// calculate array length.
							// 
							// Looking for a path between Put 28 and consumer double[] 61.
							{
								{
									if((0 == state.c7))
										lengthCV$conditionals$63_10 = 2;
								}
							}
							
							// Looking for a path between Put 44 and consumer double[] 61.
							{
								{
									if((1 == state.c7))
										lengthCV$conditionals$63_10 = 2;
								}
							}
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$conditionals$63_10)) && (0 < lengthCV$conditionals$63_10)) && (0.0 <= var61[cv$sampleValue])) && (var61[cv$sampleValue] <= 1.0))?Math.log(var61[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			state.logProbability$c8 = cv$sampleProbability;
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample65)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample65 = (state.fixedFlag$sample65 && state.fixedFlag$sample62);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c8;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample65)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample67 using sampled
	// values.
	private final void logProbabilityValue$sample67() {
		// Determine if we need to calculate the values for sample task 67 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample67) {
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
					int cv$sampleValue = state.c9;
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$sampleValue])) && (state.priors[cv$sampleValue] <= 1.0))?Math.log(state.priors[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			state.logProbability$c9 = cv$sampleProbability;
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample67)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample67 = state.fixedFlag$sample67;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c9;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample67)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample70 using sampled
	// values.
	private final void logProbabilityValue$sample70() {
		// Determine if we need to calculate the values for sample task 70 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample70) {
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
					int cv$sampleValue = state.c10;
					{
						{
							double[] var66 = state.conditionals[state.c9];
							
							// Allocate a local variable to hold the length of the array.
							int lengthCV$conditionals$68_10 = -1;
							
							// calculate array length.
							// 
							// Looking for a path between Put 28 and consumer double[] 66.
							{
								{
									if((0 == state.c9))
										lengthCV$conditionals$68_10 = 2;
								}
							}
							
							// Looking for a path between Put 44 and consumer double[] 66.
							{
								{
									if((1 == state.c9))
										lengthCV$conditionals$68_10 = 2;
								}
							}
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$conditionals$68_10)) && (0 < lengthCV$conditionals$68_10)) && (0.0 <= var66[cv$sampleValue])) && (var66[cv$sampleValue] <= 1.0))?Math.log(var66[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			state.logProbability$c10 = cv$sampleProbability;
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample70)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample70 = (state.fixedFlag$sample70 && state.fixedFlag$sample67);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c10;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample70)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample72 using sampled
	// values.
	private final void logProbabilityValue$sample72() {
		// Determine if we need to calculate the values for sample task 72 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample72) {
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
					int cv$sampleValue = state.c11;
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$sampleValue])) && (state.priors[cv$sampleValue] <= 1.0))?Math.log(state.priors[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			state.logProbability$c11 = cv$sampleProbability;
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample72)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample72 = state.fixedFlag$sample72;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c11;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample72)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample75 using sampled
	// values.
	private final void logProbabilityValue$sample75() {
		// Determine if we need to calculate the values for sample task 75 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample75) {
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
					int cv$sampleValue = state.c12;
					{
						{
							double[] var71 = state.conditionals[state.c11];
							
							// Allocate a local variable to hold the length of the array.
							int lengthCV$conditionals$73_10 = -1;
							
							// calculate array length.
							// 
							// Looking for a path between Put 28 and consumer double[] 71.
							{
								{
									if((0 == state.c11))
										lengthCV$conditionals$73_10 = 2;
								}
							}
							
							// Looking for a path between Put 44 and consumer double[] 71.
							{
								{
									if((1 == state.c11))
										lengthCV$conditionals$73_10 = 2;
								}
							}
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$conditionals$73_10)) && (0 < lengthCV$conditionals$73_10)) && (0.0 <= var71[cv$sampleValue])) && (var71[cv$sampleValue] <= 1.0))?Math.log(var71[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			state.logProbability$c12 = cv$sampleProbability;
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample75)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample75 = (state.fixedFlag$sample75 && state.fixedFlag$sample72);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c12;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample75)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample47)
			state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$48_6 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 46.
		{
			{
				if((0 == state.c1))
					lengthCV$conditionals$48_6 = 2;
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 46.
		{
			{
				if((1 == state.c1))
					lengthCV$conditionals$48_6 = 2;
			}
		}
		state.c2 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c1], lengthCV$conditionals$48_6);
		if(!state.fixedFlag$sample52)
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$53_15 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 51.
		{
			{
				if((0 == state.c3)) {
					if(!state.fixedFlag$sample55)
						lengthCV$conditionals$53_15 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 51.
		{
			{
				if((1 == state.c3)) {
					if(!state.fixedFlag$sample55)
						lengthCV$conditionals$53_15 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample55)
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_15);
		if(!state.fixedFlag$sample57)
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$58_11 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 56.
		{
			{
				if((0 == state.c5)) {
					if(!state.fixedFlag$sample60)
						lengthCV$conditionals$58_11 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 56.
		{
			{
				if((1 == state.c5)) {
					if(!state.fixedFlag$sample60)
						lengthCV$conditionals$58_11 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample60)
			state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_11);
		if(!state.fixedFlag$sample62)
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$63_11 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 61.
		{
			{
				if((0 == state.c7)) {
					if(!state.fixedFlag$sample65)
						lengthCV$conditionals$63_11 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 61.
		{
			{
				if((1 == state.c7)) {
					if(!state.fixedFlag$sample65)
						lengthCV$conditionals$63_11 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample65)
			state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_11);
		if(!state.fixedFlag$sample67)
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$68_11 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 66.
		{
			{
				if((0 == state.c9)) {
					if(!state.fixedFlag$sample70)
						lengthCV$conditionals$68_11 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 66.
		{
			{
				if((1 == state.c9)) {
					if(!state.fixedFlag$sample70)
						lengthCV$conditionals$68_11 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample70)
			state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_11);
		if(!state.fixedFlag$sample72)
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$73_11 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 71.
		{
			{
				if((0 == state.c11)) {
					if(!state.fixedFlag$sample75)
						lengthCV$conditionals$73_11 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 71.
		{
			{
				if((1 == state.c11)) {
					if(!state.fixedFlag$sample75)
						lengthCV$conditionals$73_11 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample75)
			state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_11);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$var601$634_17 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 110 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 138 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 172 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 201 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 242 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 271 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 306 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 337 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 383 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 412 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 446 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 475 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 518 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 549 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 586 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 617 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		if(!state.fixedFlag$sample636)
			state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_17);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample47)
			state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample52)
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$53_19 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 51.
		{
			{
				if((0 == state.c3)) {
					if(!state.fixedFlag$sample55)
						lengthCV$conditionals$53_19 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 51.
		{
			{
				if((1 == state.c3)) {
					if(!state.fixedFlag$sample55)
						lengthCV$conditionals$53_19 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample55)
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_19);
		if(!state.fixedFlag$sample57)
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$58_15 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 56.
		{
			{
				if((0 == state.c5)) {
					if(!state.fixedFlag$sample60)
						lengthCV$conditionals$58_15 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 56.
		{
			{
				if((1 == state.c5)) {
					if(!state.fixedFlag$sample60)
						lengthCV$conditionals$58_15 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample60)
			state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_15);
		if(!state.fixedFlag$sample62)
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$63_15 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 61.
		{
			{
				if((0 == state.c7)) {
					if(!state.fixedFlag$sample65)
						lengthCV$conditionals$63_15 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 61.
		{
			{
				if((1 == state.c7)) {
					if(!state.fixedFlag$sample65)
						lengthCV$conditionals$63_15 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample65)
			state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_15);
		if(!state.fixedFlag$sample67)
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$68_15 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 66.
		{
			{
				if((0 == state.c9)) {
					if(!state.fixedFlag$sample70)
						lengthCV$conditionals$68_15 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 66.
		{
			{
				if((1 == state.c9)) {
					if(!state.fixedFlag$sample70)
						lengthCV$conditionals$68_15 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample70)
			state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_15);
		if(!state.fixedFlag$sample72)
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$73_15 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 71.
		{
			{
				if((0 == state.c11)) {
					if(!state.fixedFlag$sample75)
						lengthCV$conditionals$73_15 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 71.
		{
			{
				if((1 == state.c11)) {
					if(!state.fixedFlag$sample75)
						lengthCV$conditionals$73_15 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample75)
			state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_15);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$var601$634_21 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 110 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 138 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 172 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 201 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 242 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 271 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 306 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 337 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 383 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 412 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 446 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 475 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 518 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 549 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 586 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 617 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		if(!state.fixedFlag$sample636)
			state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_21);
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample47)
			state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$48_7 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 46.
		{
			{
				if((0 == state.c1))
					lengthCV$conditionals$48_7 = 2;
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 46.
		{
			{
				if((1 == state.c1))
					lengthCV$conditionals$48_7 = 2;
			}
		}
		state.c2 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c1], lengthCV$conditionals$48_7);
		if(!state.fixedFlag$sample52)
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$53_16 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 51.
		{
			{
				if((0 == state.c3)) {
					if(!state.fixedFlag$sample55)
						lengthCV$conditionals$53_16 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 51.
		{
			{
				if((1 == state.c3)) {
					if(!state.fixedFlag$sample55)
						lengthCV$conditionals$53_16 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample55)
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_16);
		if(!state.fixedFlag$sample57)
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$58_12 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 56.
		{
			{
				if((0 == state.c5)) {
					if(!state.fixedFlag$sample60)
						lengthCV$conditionals$58_12 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 56.
		{
			{
				if((1 == state.c5)) {
					if(!state.fixedFlag$sample60)
						lengthCV$conditionals$58_12 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample60)
			state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_12);
		if(!state.fixedFlag$sample62)
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$63_12 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 61.
		{
			{
				if((0 == state.c7)) {
					if(!state.fixedFlag$sample65)
						lengthCV$conditionals$63_12 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 61.
		{
			{
				if((1 == state.c7)) {
					if(!state.fixedFlag$sample65)
						lengthCV$conditionals$63_12 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample65)
			state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_12);
		if(!state.fixedFlag$sample67)
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$68_12 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 66.
		{
			{
				if((0 == state.c9)) {
					if(!state.fixedFlag$sample70)
						lengthCV$conditionals$68_12 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 66.
		{
			{
				if((1 == state.c9)) {
					if(!state.fixedFlag$sample70)
						lengthCV$conditionals$68_12 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample70)
			state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_12);
		if(!state.fixedFlag$sample72)
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$73_12 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 71.
		{
			{
				if((0 == state.c11)) {
					if(!state.fixedFlag$sample75)
						lengthCV$conditionals$73_12 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 71.
		{
			{
				if((1 == state.c11)) {
					if(!state.fixedFlag$sample75)
						lengthCV$conditionals$73_12 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample75)
			state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_12);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$var601$634_18 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 110 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 138 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 172 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 201 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 242 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 271 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 306 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 337 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 383 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 412 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 446 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 475 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 518 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 549 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 586 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 617 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		if(!state.fixedFlag$sample636)
			state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_18);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample47)
			state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample52)
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$53_17 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 51.
		{
			{
				if((0 == state.c3)) {
					if(!state.fixedFlag$sample55)
						lengthCV$conditionals$53_17 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 51.
		{
			{
				if((1 == state.c3)) {
					if(!state.fixedFlag$sample55)
						lengthCV$conditionals$53_17 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample55)
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_17);
		if(!state.fixedFlag$sample57)
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$58_13 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 56.
		{
			{
				if((0 == state.c5)) {
					if(!state.fixedFlag$sample60)
						lengthCV$conditionals$58_13 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 56.
		{
			{
				if((1 == state.c5)) {
					if(!state.fixedFlag$sample60)
						lengthCV$conditionals$58_13 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample60)
			state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_13);
		if(!state.fixedFlag$sample62)
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$63_13 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 61.
		{
			{
				if((0 == state.c7)) {
					if(!state.fixedFlag$sample65)
						lengthCV$conditionals$63_13 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 61.
		{
			{
				if((1 == state.c7)) {
					if(!state.fixedFlag$sample65)
						lengthCV$conditionals$63_13 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample65)
			state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_13);
		if(!state.fixedFlag$sample67)
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$68_13 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 66.
		{
			{
				if((0 == state.c9)) {
					if(!state.fixedFlag$sample70)
						lengthCV$conditionals$68_13 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 66.
		{
			{
				if((1 == state.c9)) {
					if(!state.fixedFlag$sample70)
						lengthCV$conditionals$68_13 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample70)
			state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_13);
		if(!state.fixedFlag$sample72)
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$73_13 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 71.
		{
			{
				if((0 == state.c11)) {
					if(!state.fixedFlag$sample75)
						lengthCV$conditionals$73_13 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 71.
		{
			{
				if((1 == state.c11)) {
					if(!state.fixedFlag$sample75)
						lengthCV$conditionals$73_13 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample75)
			state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_13);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$var601$634_19 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 110 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 138 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 172 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 201 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 242 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 271 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 306 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 337 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 383 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 412 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 446 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 475 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 518 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 549 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 586 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 617 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		if(!state.fixedFlag$sample636)
			state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_19);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample47)
			state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample52)
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$53_18 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 51.
		{
			{
				if((0 == state.c3)) {
					if(!state.fixedFlag$sample55)
						lengthCV$conditionals$53_18 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 51.
		{
			{
				if((1 == state.c3)) {
					if(!state.fixedFlag$sample55)
						lengthCV$conditionals$53_18 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample55)
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_18);
		if(!state.fixedFlag$sample57)
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$58_14 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 56.
		{
			{
				if((0 == state.c5)) {
					if(!state.fixedFlag$sample60)
						lengthCV$conditionals$58_14 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 56.
		{
			{
				if((1 == state.c5)) {
					if(!state.fixedFlag$sample60)
						lengthCV$conditionals$58_14 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample60)
			state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_14);
		if(!state.fixedFlag$sample62)
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$63_14 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 61.
		{
			{
				if((0 == state.c7)) {
					if(!state.fixedFlag$sample65)
						lengthCV$conditionals$63_14 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 61.
		{
			{
				if((1 == state.c7)) {
					if(!state.fixedFlag$sample65)
						lengthCV$conditionals$63_14 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample65)
			state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_14);
		if(!state.fixedFlag$sample67)
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$68_14 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 66.
		{
			{
				if((0 == state.c9)) {
					if(!state.fixedFlag$sample70)
						lengthCV$conditionals$68_14 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 66.
		{
			{
				if((1 == state.c9)) {
					if(!state.fixedFlag$sample70)
						lengthCV$conditionals$68_14 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample70)
			state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_14);
		if(!state.fixedFlag$sample72)
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$conditionals$73_14 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 28 and consumer double[] 71.
		{
			{
				if((0 == state.c11)) {
					if(!state.fixedFlag$sample75)
						lengthCV$conditionals$73_14 = 2;
				}
			}
		}
		
		// Looking for a path between Put 44 and consumer double[] 71.
		{
			{
				if((1 == state.c11)) {
					if(!state.fixedFlag$sample75)
						lengthCV$conditionals$73_14 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample75)
			state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_14);
		
		// Allocate a local variable to hold the length of the array.
		int lengthCV$var601$634_20 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 110 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 138 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 172 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 201 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 242 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 271 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 306 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 337 and consumer double[] 602.
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 383 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 412 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 446 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 475 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 518 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 549 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 586 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		
		// Looking for a path between Put 617 and consumer double[] 602.
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		if(!state.fixedFlag$sample636)
			state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_20);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample47)
				inferSample47();
			if(!state.fixedFlag$sample52)
				inferSample52();
			if(!state.fixedFlag$sample55)
				inferSample55();
			if(!state.fixedFlag$sample57)
				inferSample57();
			if(!state.fixedFlag$sample62)
				inferSample62();
			if(!state.fixedFlag$sample67)
				inferSample67();
			if(!state.fixedFlag$sample72)
				inferSample72();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!state.fixedFlag$sample72)
				inferSample72();
			if(!state.fixedFlag$sample67)
				inferSample67();
			if(!state.fixedFlag$sample62)
				inferSample62();
			if(!state.fixedFlag$sample57)
				inferSample57();
			if(!state.fixedFlag$sample55)
				inferSample55();
			if(!state.fixedFlag$sample52)
				inferSample52();
			if(!state.fixedFlag$sample47)
				inferSample47();
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample47)
			drawValueSample47();
		if(!state.constrainedFlag$sample52)
			drawValueSample52();
		if(!state.constrainedFlag$sample55)
			drawValueSample55();
		if(!state.constrainedFlag$sample57)
			drawValueSample57();
		if(!state.fixedFlag$sample60)
			drawValueSample60();
		if(!state.constrainedFlag$sample62)
			drawValueSample62();
		if(!state.fixedFlag$sample65)
			drawValueSample65();
		if(!state.constrainedFlag$sample67)
			drawValueSample67();
		if(!state.fixedFlag$sample70)
			drawValueSample70();
		if(!state.constrainedFlag$sample72)
			drawValueSample72();
		if(!state.fixedFlag$sample75)
			drawValueSample75();
		if(!state.fixedFlag$sample636)
			drawValueSample636();
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
			state.logProbability$c1 = Double.NaN;
		if(!state.fixedProbFlag$sample50)
			state.logProbability$c2 = Double.NaN;
		if(!state.fixedProbFlag$sample52)
			state.logProbability$c3 = Double.NaN;
		if(!state.fixedProbFlag$sample55)
			state.logProbability$c4 = Double.NaN;
		if(!state.fixedProbFlag$sample57)
			state.logProbability$c5 = Double.NaN;
		if(!state.fixedProbFlag$sample60)
			state.logProbability$c6 = Double.NaN;
		if(!state.fixedProbFlag$sample62)
			state.logProbability$c7 = Double.NaN;
		if(!state.fixedProbFlag$sample65)
			state.logProbability$c8 = Double.NaN;
		if(!state.fixedProbFlag$sample67)
			state.logProbability$c9 = Double.NaN;
		if(!state.fixedProbFlag$sample70)
			state.logProbability$c10 = Double.NaN;
		if(!state.fixedProbFlag$sample72)
			state.logProbability$c11 = Double.NaN;
		if(!state.fixedProbFlag$sample75)
			state.logProbability$c12 = Double.NaN;
		if(!state.fixedProbFlag$sample636)
			state.logProbability$terminalVariable = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.priors[0] = 0.01;
		state.priors[1] = 0.99;
		double[] var15 = state.conditionals[0];
		var15[0] = 1.0;
		var15[1] = 0.0;
		double[] var30 = state.conditionals[1];
		var30[0] = 0.0;
		var30[1] = 1.0;
		double[][][][] var77 = state.a[0];
		double[][][] var79 = var77[0];
		double[][] var81 = var79[0];
		double[] var83 = var81[0];
		var83[0] = 0.3333333333333334;
		var83[1] = 0.3333333333333334;
		var83[2] = 0.0;
		var83[3] = 0.3333333333333334;
		var83[4] = 0.0;
		double[] var110 = var81[1];
		var110[0] = 0.3333333333333334;
		var110[1] = 0.3333333333333334;
		var110[2] = 0.0;
		var110[3] = 0.3333333333333334;
		var110[4] = 0.0;
		double[][] var140 = var79[1];
		double[] var142 = var140[0];
		var142[0] = 0.3333333333333334;
		var142[1] = 0.3333333333333334;
		var142[2] = 0.0;
		var142[3] = 0.3333333333333334;
		var142[4] = 0.0;
		double[] var169 = var140[1];
		var169[0] = 0.5;
		var169[1] = 0.5;
		var169[2] = 0.0;
		var169[3] = 0.0;
		var169[4] = 0.0;
		double[][][] var203 = var77[1];
		double[][] var205 = var203[0];
		double[] var207 = var205[0];
		var207[0] = 0.0;
		var207[1] = 0.5;
		var207[2] = 0.0;
		var207[3] = 0.5;
		var207[4] = 0.0;
		double[] var235 = var205[1];
		var235[0] = 0.0;
		var235[1] = 0.5;
		var235[2] = 0.0;
		var235[3] = 0.5;
		var235[4] = 0.0;
		double[][] var266 = var203[1];
		double[] var268 = var266[0];
		var268[0] = 0.0;
		var268[1] = 0.5;
		var268[2] = 0.0;
		var268[3] = 0.5;
		var268[4] = 0.0;
		double[] var296 = var266[1];
		var296[0] = 0.0;
		var296[1] = 1.0;
		var296[2] = 0.0;
		var296[3] = 0.0;
		var296[4] = 0.0;
		double[][][][] var335 = state.a[1];
		double[][][] var337 = var335[0];
		double[][] var339 = var337[0];
		double[] var341 = var339[0];
		var341[0] = 0.3333333333333334;
		var341[1] = 0.3333333333333334;
		var341[2] = 0.0;
		var341[3] = 0.3333333333333334;
		var341[4] = 0.0;
		double[] var368 = var339[1];
		var368[0] = 0.5;
		var368[1] = 0.5;
		var368[2] = 0.0;
		var368[3] = 0.0;
		var368[4] = 0.0;
		double[][] var399 = var337[1];
		double[] var401 = var399[0];
		var401[0] = 0.3333333333333334;
		var401[1] = 0.3333333333333334;
		var401[2] = 0.0;
		var401[3] = 0.3333333333333334;
		var401[4] = 0.0;
		double[] var428 = var399[1];
		var428[0] = 0.5;
		var428[1] = 0.5;
		var428[2] = 0.0;
		var428[3] = 0.0;
		var428[4] = 0.0;
		double[][][] var462 = var335[1];
		double[][] var464 = var462[0];
		double[] var466 = var464[0];
		var466[0] = 0.0;
		var466[1] = 0.0;
		var466[2] = 0.0;
		var466[3] = 1.0;
		var466[4] = 0.0;
		double[] var496 = var464[1];
		var496[0] = 0.0;
		var496[1] = 0.0;
		var496[2] = 0.0;
		var496[3] = 1.0;
		var496[4] = 0.0;
		double[][] var529 = var462[1];
		double[] var531 = var529[0];
		var531[0] = 0.0;
		var531[1] = 0.0;
		var531[2] = 0.0;
		var531[3] = 1.0;
		var531[4] = 0.0;
		double[] var561 = var529[1];
		var561[0] = 0.0;
		var561[1] = 0.0;
		var561[2] = 0.0;
		var561[3] = 0.0;
		var561[4] = 1.0;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(state.fixedFlag$sample47)
			logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		if(state.fixedFlag$sample52)
			logProbabilityValue$sample52();
		if(state.fixedFlag$sample55)
			logProbabilityValue$sample55();
		if(state.fixedFlag$sample57)
			logProbabilityValue$sample57();
		if(state.fixedFlag$sample60)
			logProbabilityValue$sample60();
		if(state.fixedFlag$sample62)
			logProbabilityValue$sample62();
		if(state.fixedFlag$sample65)
			logProbabilityValue$sample65();
		if(state.fixedFlag$sample67)
			logProbabilityValue$sample67();
		if(state.fixedFlag$sample70)
			logProbabilityValue$sample70();
		if(state.fixedFlag$sample72)
			logProbabilityValue$sample72();
		if(state.fixedFlag$sample75)
			logProbabilityValue$sample75();
		if(state.fixedFlag$sample636)
			logProbabilityValue$sample636();
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
		logProbabilityValue$sample52();
		logProbabilityValue$sample55();
		logProbabilityValue$sample57();
		logProbabilityValue$sample60();
		logProbabilityValue$sample62();
		logProbabilityValue$sample65();
		logProbabilityValue$sample67();
		logProbabilityValue$sample70();
		logProbabilityValue$sample72();
		logProbabilityValue$sample75();
		logProbabilityValue$sample636();
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
		logProbabilityValue$sample52();
		logProbabilityValue$sample55();
		logProbabilityValue$sample57();
		logProbabilityValue$sample60();
		logProbabilityValue$sample62();
		logProbabilityValue$sample65();
		logProbabilityValue$sample67();
		logProbabilityValue$sample70();
		logProbabilityValue$sample72();
		logProbabilityValue$sample75();
		logProbabilityValue$sample636();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		state.c2 = state.evidence;
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model TerminalVariables(int evidence) {\n"
		     + "    double[] priors = {.01, 0.99};\n"
		     + "    double[][] conditionals = {{1, 0}, {0, 1}};\n"
		     + "\n"
		     + "    int c1 = categorical(priors).sample();\n"
		     + "    int c2 = categorical(conditionals[c1]).sample();\n"
		     + "    \n"
		     + "    int c3 = categorical(priors).sample();\n"
		     + "    int c4 = categorical(conditionals[c3]).sample();\n"
		     + "    \n"
		     + "    int c5 = categorical(priors).sample();\n"
		     + "    int c6 = categorical(conditionals[c5]).sample();\n"
		     + "\n"
		     + "    int c7 = categorical(priors).sample();\n"
		     + "    int c8 = categorical(conditionals[c7]).sample();\n"
		     + "\n"
		     + "    int c9 = categorical(priors).sample();\n"
		     + "    int c10 = categorical(conditionals[c9]).sample();\n"
		     + "    \n"
		     + "    int c11 = categorical(priors).sample();\n"
		     + "    int c12 = categorical(conditionals[c11]).sample();\n"
		     + "\n"
		     + "    double[][][][][] a = {\n"
		     + "       {\n"
		     + "        {{{0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}, {0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}},\n"
		     + "         {{0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}, {0.5, 0.5, 0, 0, 0}}},\n"
		     + "        {{{0, 0.5, 0, 0.5, 0}, {0, 0.5, 0, 0.5, 0}},\n"
		     + "         {{0, 0.5, 0, 0.5, 0}, {0, 1, 0, 0, 0}}}\n"
		     + "       },\n"
		     + "       {\n"
		     + "         {{{0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}, {0.5, 0.5, 0, 0, 0}},\n"
		     + "          {{0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}, {0.5, 0.5, 0, 0, 0}}},\n"
		     + "         {{{0, 0, 0, 1, 0}, {0, 0, 0, 1, 0}},\n"
		     + "          {{0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}}}\n"
		     + "        }\n"
		     + "    };\n"
		     + "    int terminalVariable = categorical(a[c5][c9][c1][c4]).sample();\n"
		     + "    \n"
		     + "    // assert\n"
		     + "    c2.observe(evidence);\n"
		     + "}";
	}
}