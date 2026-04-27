package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.DistributionTest4$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.DistributionTest4.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DistributionTest4$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		// Declare the scratch variables for the model.
		double[] cv$var11$stateProbabilityGlobal;
		double[] cv$var27$stateProbabilityGlobal;
		double[] cv$var5$stateProbabilityGlobal;
		boolean[] guard$sample11bernoulli52$global;
		boolean[] guard$sample27bernoulli52$global;

		// Method to allocate space temporary variables used by the inference methods. Allocating
		// here prevents repeated allocation and deallocation, and makes the code more amenable
		// to GPU execution.
		@Override
		public final void allocateScratch() {
			// Allocate scratch space.
			// Constructor for cv$var5$stateProbabilityGlobal
			// 
			// Allocation of cv$var5$stateProbabilityGlobal for single threaded execution
			cv$var5$stateProbabilityGlobal = new double[state.weightings.length];
			
			// Constructor for cv$var11$stateProbabilityGlobal
			// 
			// Allocation of cv$var11$stateProbabilityGlobal for single threaded execution
			cv$var11$stateProbabilityGlobal = new double[state.weightings.length];
			
			// Constructor for guard$sample11bernoulli52$global
			// 
			// Allocation of guard$sample11bernoulli52$global for single threaded execution
			guard$sample11bernoulli52$global = new boolean[state.length$value];
			
			// Constructor for cv$var27$stateProbabilityGlobal
			// 
			// Allocation of cv$var27$stateProbabilityGlobal for single threaded execution
			cv$var27$stateProbabilityGlobal = new double[state.weightings.length];
			
			// Allocation of guard$sample27bernoulli52$global for single threaded execution
			guard$sample27bernoulli52$global = new boolean[state.length$value];
		}
	}


	public DistributionTest4$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	// Pick a value from the distribution for the unconditioned variable from sample11
	private final void drawValueSample11() {
		state.v2[0] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
	}

	// Pick a value from the distribution for the unconditioned variable from sample27
	private final void drawValueSample27(int i) {
		state.v2[(i + 1)] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
	}

	// Pick a value from the distribution for the unconditioned variable from sample5
	private final void drawValueSample5() {
		state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 11 drawn from Categorical 10. Inference was performed using variable
	// marginalization.
	private final void inferSample11() {
		state.constrainedFlag$sample11 = false;
		
		// Variable declaration of cv$numStates moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		int cv$numStates = state.weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Constructing a random variable input for use later.
			int $var2942 = state.weightings.length;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var2942) && (0 < $var2942)) && (0.0 <= state.weightings[cv$valuePos])) && (state.weightings[cv$valuePos] <= 1.0))?Math.log(state.weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < state.size)) {
				// Set the flags to false
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample11bernoulli52$global[0] = false;
				
				// Substituted "j" with its value "0".
				if(!scratch.guard$sample11bernoulli52$global[0]) {
					// The body will execute, so should not be executed again
					// 
															// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample11bernoulli52$global[0] = true;
					
					// Mark that the sample has observed constrained data.
					state.constrainedFlag$sample11 = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
					// the output of Sample task 11.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
					// the output of Sample task 11.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
					// the output of Sample task 11.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
					// the output of Sample task 11.
					if(state.fixedFlag$sample5) {
						if(state.fixedFlag$sample27) {
							// Constructing a random variable input for use later.
							// 
																					// Substituted "j" with its value "0".
							// 
																					// Value of the variable at this index
							double var51 = ((double)((state.v1 + cv$valuePos) + state.v2[1]) / state.v2[1]);
							
							// Substituted "j" with its value "0".
							cv$accumulatedConsumerProbabilities = (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 53 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							// Enumerating the possible outputs of Categorical 26.
							for(int index$sample27$130 = 0; index$sample27$130 < state.weightings.length; index$sample27$130 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "i" with its value "0".
								double cv$probabilitySample27Value131 = state.distribution$sample27[0][index$sample27$130];
								
								// Constructing a random variable input for use later.
								// 
																								// Value of the variable at this index
								double var51 = ((double)((state.v1 + cv$valuePos) + index$sample27$130) / index$sample27$130);
								
								// Record the probability of sample task 53 generating output with current configuration.
								// 
								// Substituted "j" with its value "0".
								if(((Math.log(cv$probabilitySample27Value131) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value131) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "0".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value131) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
																														// Substituted "j" with its value "0".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value131) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value131)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								}
								
								// Recorded the probability of reaching sample task 53 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value131);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 4.
						for(int index$sample5$124 = 0; index$sample5$124 < state.weightings.length; index$sample5$124 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample5Value125 = state.distribution$sample5[index$sample5$124];
							if(state.fixedFlag$sample27) {
								// Constructing a random variable input for use later.
								// 
																								// Substituted "j" with its value "0".
								// 
																								// Value of the variable at this index
								double var51 = ((double)((index$sample5$124 + cv$valuePos) + state.v2[1]) / state.v2[1]);
								
								// Record the probability of sample task 53 generating output with current configuration.
								// 
								// Substituted "j" with its value "0".
								if(((Math.log(cv$probabilitySample5Value125) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value125) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "0".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value125) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
																														// Substituted "j" with its value "0".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value125) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value125)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								}
								
								// Recorded the probability of reaching sample task 53 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value125);
							} else {
								// Enumerating the possible outputs of Categorical 26.
								for(int index$sample27$136 = 0; index$sample27$136 < state.weightings.length; index$sample27$136 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "i" with its value "0".
									double cv$probabilitySample27Value137 = (cv$probabilitySample5Value125 * state.distribution$sample27[0][index$sample27$136]);
									
									// Constructing a random variable input for use later.
									// 
																											// Value of the variable at this index
									double var51 = ((double)((index$sample5$124 + cv$valuePos) + index$sample27$136) / index$sample27$136);
									
									// Record the probability of sample task 53 generating output with current configuration.
									// 
									// Substituted "j" with its value "0".
									if(((Math.log(cv$probabilitySample27Value137) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										// Substituted "j" with its value "0".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value137) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "j" with its value "0".
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value137) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
										else
																																	// Substituted "j" with its value "0".
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value137) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value137)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									}
									
									// Recorded the probability of reaching sample task 53 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value137);
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
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var11$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample11) {
			// Set the calculated probabilities to be the distribution values, and normalize
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var11$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var11$stateProbabilityGlobal[cv$lseIndex];
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
					// Get a local reference to the scratch space.
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var11$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Local copy of the probability array
					state.distribution$sample11[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
															// Local copy of the probability array
					state.distribution$sample11[cv$indexName] = Math.exp((scratch.cv$var11$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var11$stateProbabilityGlobal.length; cv$indexName += 1)
				// Local copy of the probability array
				state.distribution$sample11[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 27 drawn from Categorical 26. Inference was performed using variable
	// marginalization.
	private final void inferSample27(int i) {
		state.constrainedFlag$sample27[i] = false;
		
		// Variable declaration of cv$numStates moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		int cv$numStates = state.weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Constructing a random variable input for use later.
			int $var3599 = state.weightings.length;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var3599) && (0 < $var3599)) && (0.0 <= state.weightings[cv$valuePos])) && (state.weightings[cv$valuePos] <= 1.0))?Math.log(state.weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			{
				int j = (i + 1);
				if((j < state.size))
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					scratch.guard$sample27bernoulli52$global[j] = false;
			}
			
			// Set the flags to false
			// 
									// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			scratch.guard$sample27bernoulli52$global[i] = false;
			int j = (i + 1);
			if(((j < state.size) && !scratch.guard$sample27bernoulli52$global[j])) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample27bernoulli52$global[j] = true;
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample27[i] = true;
				
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
				// the output of Sample task 27.
				// 
				// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
				// the output of Sample task 27.
				// 
				// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
				// the output of Sample task 27.
				if(state.fixedFlag$sample5) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Substituted "j" with its value "(i + 1)".
					// 
					// Substituted "j" with its value "(i + 1)".
					// 
					// Substituted "j" with its value "(i + 1)".
					// 
					// Substituted "j" with its value "(i + 1)".
					if((i == j)) {
						// Constructing a random variable input for use later.
						// 
																		// Value of the variable at this index
						double var51 = ((double)((state.v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
						
						// Substituted "j" with its value "i".
						cv$accumulatedConsumerProbabilities = (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY);
						
						// Recorded the probability of reaching sample task 53 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
																				// index$i$1's comment
					// Copy of index so that its values can be safely substituted
					// 
															// Substituted "index$i$120" with its value "j".
					else {
						// Enumerating the possible outputs of Categorical 26.
						for(int index$sample27$121 = 0; index$sample27$121 < state.weightings.length; index$sample27$121 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$i$120" with its value "j".
							double cv$probabilitySample27Value122 = state.distribution$sample27[j][index$sample27$121];
							
							// Constructing a random variable input for use later.
							// 
																					// Value of the variable at this index
							double var51 = ((double)((state.v1 + cv$valuePos) + index$sample27$121) / index$sample27$121);
							
							// Record the probability of sample task 53 generating output with current configuration.
							if(((Math.log(cv$probabilitySample27Value122) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value122) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value122) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value122) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value122)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
							}
							
							// Recorded the probability of reaching sample task 53 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value122);
						}
					}
				}
				
				// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
				// the output of Sample task 27.
				else {
					// Enumerating the possible outputs of Categorical 4.
					for(int index$sample5$115 = 0; index$sample5$115 < state.weightings.length; index$sample5$115 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample5Value116 = state.distribution$sample5[index$sample5$115];
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((i == j)) {
							// Constructing a random variable input for use later.
							// 
																					// Value of the variable at this index
							double var51 = ((double)((index$sample5$115 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 53 generating output with current configuration.
							// 
							// Substituted "j" with its value "i".
							if(((Math.log(cv$probabilitySample5Value116) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								// Substituted "j" with its value "i".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value116) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "j" with its value "i".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value116) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								else
																											// Substituted "j" with its value "i".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value116) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value116)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
							}
							
							// Recorded the probability of reaching sample task 53 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value116);
						}
						
																								// index$i$1's comment
						// Copy of index so that its values can be safely substituted
						// 
																		// Substituted "index$i$126" with its value "j".
						else {
							// Enumerating the possible outputs of Categorical 26.
							for(int index$sample27$127 = 0; index$sample27$127 < state.weightings.length; index$sample27$127 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$i$126" with its value "j".
								double cv$probabilitySample27Value128 = (cv$probabilitySample5Value116 * state.distribution$sample27[j][index$sample27$127]);
								
								// Constructing a random variable input for use later.
								// 
																								// Value of the variable at this index
								double var51 = ((double)((index$sample5$115 + cv$valuePos) + index$sample27$127) / index$sample27$127);
								
								// Record the probability of sample task 53 generating output with current configuration.
								if(((Math.log(cv$probabilitySample27Value128) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value128) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value128) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value128) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value128)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								}
								
								// Recorded the probability of reaching sample task 53 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value128);
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
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!scratch.guard$sample27bernoulli52$global[i]) {
				// The body will execute, so should not be executed again
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample27bernoulli52$global[i] = true;
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample27[i] = true;
				
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
				// the output of Sample task 27.
				if((0 == i)) {
					// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
					// the output of Sample task 27.
					if(state.fixedFlag$sample5) {
						if(state.fixedFlag$sample11) {
							// Constructing a random variable input for use later.
							// 
																					// Substituted "j" with its value "i".
							// 
																					// Value of the variable at this index
							double var51 = ((double)((state.v1 + state.v2[0]) + cv$valuePos) / cv$valuePos);
							
							// Substituted "j" with its value "i".
							// 
							// Substituted "i" with its value "0".
							cv$accumulatedConsumerProbabilities = (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 53 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							// Enumerating the possible outputs of Categorical 10.
							for(int index$sample11$227 = 0; index$sample11$227 < state.weightings.length; index$sample11$227 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample11Value228 = state.distribution$sample11[index$sample11$227];
								
								// Constructing a random variable input for use later.
								// 
																								// Value of the variable at this index
								double var51 = ((double)((state.v1 + index$sample11$227) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 53 generating output with current configuration.
								// 
								// Substituted "j" with its value "i".
								// 
								// Substituted "i" with its value "0".
								if(((Math.log(cv$probabilitySample11Value228) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "i".
									// 
									// Substituted "i" with its value "0".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value228) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "i".
										// 
										// Substituted "i" with its value "0".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value228) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
																														// Substituted "j" with its value "i".
										// 
																														// Substituted "i" with its value "0".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value228) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample11Value228)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								}
								
								// Recorded the probability of reaching sample task 53 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value228);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 4.
						for(int index$sample5$222 = 0; index$sample5$222 < state.weightings.length; index$sample5$222 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample5Value223 = state.distribution$sample5[index$sample5$222];
							if(state.fixedFlag$sample11) {
								// Constructing a random variable input for use later.
								// 
																								// Substituted "j" with its value "i".
								// 
																								// Value of the variable at this index
								double var51 = ((double)((index$sample5$222 + state.v2[0]) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 53 generating output with current configuration.
								// 
								// Substituted "j" with its value "i".
								// 
								// Substituted "i" with its value "0".
								if(((Math.log(cv$probabilitySample5Value223) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "i".
									// 
									// Substituted "i" with its value "0".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value223) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "i".
										// 
										// Substituted "i" with its value "0".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value223) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
																														// Substituted "j" with its value "i".
										// 
																														// Substituted "i" with its value "0".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value223) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value223)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								}
								
								// Recorded the probability of reaching sample task 53 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value223);
							} else {
								// Enumerating the possible outputs of Categorical 10.
								for(int index$sample11$232 = 0; index$sample11$232 < state.weightings.length; index$sample11$232 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample11Value233 = (cv$probabilitySample5Value223 * state.distribution$sample11[index$sample11$232]);
									
									// Constructing a random variable input for use later.
									// 
																											// Value of the variable at this index
									double var51 = ((double)((index$sample5$222 + index$sample11$232) + cv$valuePos) / cv$valuePos);
									
									// Record the probability of sample task 53 generating output with current configuration.
									// 
									// Substituted "j" with its value "i".
									// 
									// Substituted "i" with its value "0".
									if(((Math.log(cv$probabilitySample11Value233) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										// Substituted "j" with its value "i".
										// 
										// Substituted "i" with its value "0".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value233) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "j" with its value "i".
											// 
											// Substituted "i" with its value "0".
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value233) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
										else
																																	// Substituted "j" with its value "i".
											// 
																																	// Substituted "i" with its value "0".
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value233) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample11Value233)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									}
									
									// Recorded the probability of reaching sample task 53 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value233);
								}
							}
						}
					}
				}
				
				// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
				// the output of Sample task 27.
				if(state.fixedFlag$sample5) {
					int index$i$266 = (i - 1);
					
																				// index$i$1's comment
					// Copy of index so that its values can be safely substituted
					// 
															// Substituted "index$i$266" with its value "(i - 1)".
					// 
															// Substituted "index$i$266" with its value "(i - 1)".
					// 
															// Substituted "index$i$266" with its value "(i - 1)".
					// 
															// Substituted "index$i$266" with its value "(i - 1)".
					if(((0 <= index$i$266) && !(index$i$266 == i))) {
						// Enumerating the possible outputs of Categorical 26.
						for(int index$sample27$267 = 0; index$sample27$267 < state.weightings.length; index$sample27$267 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample27Value268 = state.distribution$sample27[index$i$266][index$sample27$267];
							
							// Constructing a random variable input for use later.
							// 
																					// Value of the variable at this index
							double var51 = ((double)((state.v1 + index$sample27$267) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 53 generating output with current configuration.
							// 
							// Substituted "j" with its value "i".
							if(((Math.log(cv$probabilitySample27Value268) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								// Substituted "j" with its value "i".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value268) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "j" with its value "i".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value268) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								else
																											// Substituted "j" with its value "i".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value268) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value268)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
							}
							
							// Recorded the probability of reaching sample task 53 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value268);
						}
					}
				} else {
					// Enumerating the possible outputs of Categorical 4.
					for(int index$sample5$261 = 0; index$sample5$261 < state.weightings.length; index$sample5$261 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample5Value262 = state.distribution$sample5[index$sample5$261];
						int index$i$272 = (i - 1);
						
																								// index$i$1's comment
						// Copy of index so that its values can be safely substituted
						// 
																		// Substituted "index$i$272" with its value "(i - 1)".
						// 
																		// Substituted "index$i$272" with its value "(i - 1)".
						// 
																		// Substituted "index$i$272" with its value "(i - 1)".
						// 
																		// Substituted "index$i$272" with its value "(i - 1)".
						if(((0 <= index$i$272) && !(index$i$272 == i))) {
							// Enumerating the possible outputs of Categorical 26.
							for(int index$sample27$273 = 0; index$sample27$273 < state.weightings.length; index$sample27$273 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample27Value274 = (cv$probabilitySample5Value262 * state.distribution$sample27[index$i$272][index$sample27$273]);
								
								// Constructing a random variable input for use later.
								// 
																								// Value of the variable at this index
								double var51 = ((double)((index$sample5$261 + index$sample27$273) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 53 generating output with current configuration.
								// 
								// Substituted "j" with its value "i".
								if(((Math.log(cv$probabilitySample27Value274) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "i".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value274) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "i".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value274) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
																														// Substituted "j" with its value "i".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value274) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value274)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								}
								
								// Recorded the probability of reaching sample task 53 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value274);
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
			if(!scratch.guard$sample27bernoulli52$global[i]) {
				// The body will execute, so should not be executed again
				// 
												// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				scratch.guard$sample27bernoulli52$global[i] = true;
				
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample27[i] = true;
				
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
				// the output of Sample task 27.
				if((0 == i)) {
					// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
					// the output of Sample task 27.
					if(state.fixedFlag$sample5) {
						if(state.fixedFlag$sample11) {
							// Constructing a random variable input for use later.
							// 
																					// Substituted "j" with its value "i".
							// 
																					// Value of the variable at this index
							double var51 = ((double)((state.v1 + state.v2[0]) + cv$valuePos) / cv$valuePos);
							
							// Substituted "j" with its value "i".
							// 
							// Substituted "i" with its value "0".
							cv$accumulatedConsumerProbabilities = (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 53 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							// Enumerating the possible outputs of Categorical 10.
							for(int index$sample11$373 = 0; index$sample11$373 < state.weightings.length; index$sample11$373 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample11Value374 = state.distribution$sample11[index$sample11$373];
								
								// Constructing a random variable input for use later.
								// 
																								// Value of the variable at this index
								double var51 = ((double)((state.v1 + index$sample11$373) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 53 generating output with current configuration.
								// 
								// Substituted "j" with its value "i".
								// 
								// Substituted "i" with its value "0".
								if(((Math.log(cv$probabilitySample11Value374) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "i".
									// 
									// Substituted "i" with its value "0".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value374) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "i".
										// 
										// Substituted "i" with its value "0".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value374) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
																														// Substituted "j" with its value "i".
										// 
																														// Substituted "i" with its value "0".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value374) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample11Value374)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								}
								
								// Recorded the probability of reaching sample task 53 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value374);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 4.
						for(int index$sample5$368 = 0; index$sample5$368 < state.weightings.length; index$sample5$368 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample5Value369 = state.distribution$sample5[index$sample5$368];
							if(state.fixedFlag$sample11) {
								// Constructing a random variable input for use later.
								// 
																								// Substituted "j" with its value "i".
								// 
																								// Value of the variable at this index
								double var51 = ((double)((index$sample5$368 + state.v2[0]) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 53 generating output with current configuration.
								// 
								// Substituted "j" with its value "i".
								// 
								// Substituted "i" with its value "0".
								if(((Math.log(cv$probabilitySample5Value369) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "i".
									// 
									// Substituted "i" with its value "0".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value369) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "i".
										// 
										// Substituted "i" with its value "0".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value369) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
																														// Substituted "j" with its value "i".
										// 
																														// Substituted "i" with its value "0".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value369) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value369)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								}
								
								// Recorded the probability of reaching sample task 53 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value369);
							} else {
								// Enumerating the possible outputs of Categorical 10.
								for(int index$sample11$378 = 0; index$sample11$378 < state.weightings.length; index$sample11$378 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample11Value379 = (cv$probabilitySample5Value369 * state.distribution$sample11[index$sample11$378]);
									
									// Constructing a random variable input for use later.
									// 
																											// Value of the variable at this index
									double var51 = ((double)((index$sample5$368 + index$sample11$378) + cv$valuePos) / cv$valuePos);
									
									// Record the probability of sample task 53 generating output with current configuration.
									// 
									// Substituted "j" with its value "i".
									// 
									// Substituted "i" with its value "0".
									if(((Math.log(cv$probabilitySample11Value379) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										// Substituted "j" with its value "i".
										// 
										// Substituted "i" with its value "0".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value379) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "j" with its value "i".
											// 
											// Substituted "i" with its value "0".
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value379) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
										else
																																	// Substituted "j" with its value "i".
											// 
																																	// Substituted "i" with its value "0".
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value379) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample11Value379)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									}
									
									// Recorded the probability of reaching sample task 53 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value379);
								}
							}
						}
					}
				}
				
				// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
				// the output of Sample task 27.
				if(state.fixedFlag$sample5) {
					int index$i$412 = (i - 1);
					
																				// index$i$1's comment
					// Copy of index so that its values can be safely substituted
					// 
															// Substituted "index$i$412" with its value "(i - 1)".
					// 
															// Substituted "index$i$412" with its value "(i - 1)".
					// 
															// Substituted "index$i$412" with its value "(i - 1)".
					// 
															// Substituted "index$i$412" with its value "(i - 1)".
					if(((0 <= index$i$412) && !(index$i$412 == i))) {
						// Enumerating the possible outputs of Categorical 26.
						for(int index$sample27$413 = 0; index$sample27$413 < state.weightings.length; index$sample27$413 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample27Value414 = state.distribution$sample27[index$i$412][index$sample27$413];
							
							// Constructing a random variable input for use later.
							// 
																					// Value of the variable at this index
							double var51 = ((double)((state.v1 + index$sample27$413) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 53 generating output with current configuration.
							// 
							// Substituted "j" with its value "i".
							if(((Math.log(cv$probabilitySample27Value414) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								// Substituted "j" with its value "i".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value414) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "j" with its value "i".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value414) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								else
																											// Substituted "j" with its value "i".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value414) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value414)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
							}
							
							// Recorded the probability of reaching sample task 53 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value414);
						}
					}
				} else {
					// Enumerating the possible outputs of Categorical 4.
					for(int index$sample5$407 = 0; index$sample5$407 < state.weightings.length; index$sample5$407 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample5Value408 = state.distribution$sample5[index$sample5$407];
						int index$i$418 = (i - 1);
						
																								// index$i$1's comment
						// Copy of index so that its values can be safely substituted
						// 
																		// Substituted "index$i$418" with its value "(i - 1)".
						// 
																		// Substituted "index$i$418" with its value "(i - 1)".
						// 
																		// Substituted "index$i$418" with its value "(i - 1)".
						// 
																		// Substituted "index$i$418" with its value "(i - 1)".
						if(((0 <= index$i$418) && !(index$i$418 == i))) {
							// Enumerating the possible outputs of Categorical 26.
							for(int index$sample27$419 = 0; index$sample27$419 < state.weightings.length; index$sample27$419 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample27Value420 = (cv$probabilitySample5Value408 * state.distribution$sample27[index$i$418][index$sample27$419]);
								
								// Constructing a random variable input for use later.
								// 
																								// Value of the variable at this index
								double var51 = ((double)((index$sample5$407 + index$sample27$419) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 53 generating output with current configuration.
								// 
								// Substituted "j" with its value "i".
								if(((Math.log(cv$probabilitySample27Value420) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "i".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value420) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "i".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value420) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
																														// Substituted "j" with its value "i".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value420) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value420)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								}
								
								// Recorded the probability of reaching sample task 53 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value420);
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
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var27$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample27[i]) {
			// Set the calculated probabilities to be the distribution values, and normalize
			// Local copy of the probability array
			double[] cv$localProbability = state.distribution$sample27[i];
			
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var27$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var27$stateProbabilityGlobal[cv$lseIndex];
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
					// Get a local reference to the scratch space.
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var27$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Get a local reference to the scratch space.
					cv$localProbability[cv$indexName] = Math.exp((scratch.cv$var27$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var27$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 5 drawn from Categorical 4. Inference was performed using variable
	// marginalization.
	private final void inferSample5() {
		state.constrainedFlag$sample5 = false;
		
		// Variable declaration of cv$numStates moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		int cv$numStates = state.weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Constructing a random variable input for use later.
			int $var2612 = state.weightings.length;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var2612) && (0 < $var2612)) && (0.0 <= state.weightings[cv$valuePos])) && (state.weightings[cv$valuePos] <= 1.0))?Math.log(state.weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			for(int j = 0; j < state.size; j += 1) {
				// Mark that the sample has observed constrained data.
				state.constrainedFlag$sample5 = true;
				
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
				// the output of Sample task 5.
				if((0 == j)) {
					// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
					// the output of Sample task 5.
					if(state.fixedFlag$sample11) {
						if(state.fixedFlag$sample27) {
							// Constructing a random variable input for use later.
							// 
																					// Substituted "j" with its value "0".
							// 
																					// Value of the variable at this index
							double var51 = ((double)((cv$valuePos + state.v2[0]) + state.v2[1]) / state.v2[1]);
							
							// Substituted "j" with its value "0".
							cv$accumulatedConsumerProbabilities = (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY);
							
							// Recorded the probability of reaching sample task 53 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							// Enumerating the possible outputs of Categorical 26.
							for(int index$sample27$177 = 0; index$sample27$177 < state.weightings.length; index$sample27$177 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "i" with its value "0".
								double cv$probabilitySample27Value178 = state.distribution$sample27[0][index$sample27$177];
								
								// Constructing a random variable input for use later.
								// 
																								// Substituted "j" with its value "0".
								// 
																								// Value of the variable at this index
								double var51 = ((double)((cv$valuePos + state.v2[0]) + index$sample27$177) / index$sample27$177);
								
								// Record the probability of sample task 53 generating output with current configuration.
								// 
								// Substituted "j" with its value "0".
								if(((Math.log(cv$probabilitySample27Value178) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value178) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "0".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value178) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
																														// Substituted "j" with its value "0".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value178) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value178)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								}
								
								// Recorded the probability of reaching sample task 53 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value178);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 10.
						for(int index$sample11$171 = 0; index$sample11$171 < state.weightings.length; index$sample11$171 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample11Value172 = state.distribution$sample11[index$sample11$171];
							if(state.fixedFlag$sample27) {
								// Constructing a random variable input for use later.
								// 
																								// Substituted "j" with its value "0".
								// 
																								// Value of the variable at this index
								double var51 = ((double)((cv$valuePos + index$sample11$171) + state.v2[1]) / state.v2[1]);
								
								// Record the probability of sample task 53 generating output with current configuration.
								// 
								// Substituted "j" with its value "0".
								if(((Math.log(cv$probabilitySample11Value172) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value172) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "0".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value172) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
																														// Substituted "j" with its value "0".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value172) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample11Value172)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								}
								
								// Recorded the probability of reaching sample task 53 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value172);
							} else {
								// Enumerating the possible outputs of Categorical 26.
								for(int index$sample27$183 = 0; index$sample27$183 < state.weightings.length; index$sample27$183 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "i" with its value "0".
									double cv$probabilitySample27Value184 = (cv$probabilitySample11Value172 * state.distribution$sample27[0][index$sample27$183]);
									
									// Constructing a random variable input for use later.
									// 
																											// Value of the variable at this index
									double var51 = ((double)((cv$valuePos + index$sample11$171) + index$sample27$183) / index$sample27$183);
									
									// Record the probability of sample task 53 generating output with current configuration.
									// 
									// Substituted "j" with its value "0".
									if(((Math.log(cv$probabilitySample27Value184) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										// Substituted "j" with its value "0".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value184) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "j" with its value "0".
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value184) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
										else
																																	// Substituted "j" with its value "0".
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value184) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value184)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									}
									
									// Recorded the probability of reaching sample task 53 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value184);
								}
							}
						}
					}
				}
				
				// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
				// the output of Sample task 5.
				if(state.fixedFlag$sample27) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 <= j)) {
						// Constructing a random variable input for use later.
						// 
																		// Value of the variable at this index
						double var51 = ((double)((cv$valuePos + state.v2[j]) + state.v2[(j + 1)]) / state.v2[(j + 1)]);
						
						// Record the probability of sample task 53 generating output with current configuration.
						if(((((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY);
							else
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY))) + 1)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
						}
						
						// Recorded the probability of reaching sample task 53 with the current configuration.
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
					}
				} else {
					int i = (j - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 <= i)) {
						// Enumerating the possible outputs of Categorical 26.
						for(int index$sample27$203 = 0; index$sample27$203 < state.weightings.length; index$sample27$203 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample27Value204 = state.distribution$sample27[i][index$sample27$203];
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							// 
							// Substituted "i" with its value "(j - 1)".
							// 
							// Substituted "i" with its value "(j - 1)".
							// 
							// Substituted "i" with its value "(j - 1)".
							// 
							// Substituted "i" with its value "(j - 1)".
							if((i == j)) {
								// Constructing a random variable input for use later.
								// 
																								// Value of the variable at this index
								double var51 = ((double)((cv$valuePos + index$sample27$203) + index$sample27$203) / index$sample27$203);
								
								// Record the probability of sample task 53 generating output with current configuration.
								if(((Math.log(cv$probabilitySample27Value204) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value204) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value204) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value204) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value204)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								}
								
								// Recorded the probability of reaching sample task 53 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value204);
							}
							
																					// Substituted "index$i$209" with its value "j".
							else {
								// Enumerating the possible outputs of Categorical 26.
								for(int index$sample27$210 = 0; index$sample27$210 < state.weightings.length; index$sample27$210 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "index$i$209" with its value "j".
									double cv$probabilitySample27Value211 = (cv$probabilitySample27Value204 * state.distribution$sample27[j][index$sample27$210]);
									
									// Constructing a random variable input for use later.
									// 
																											// Value of the variable at this index
									double var51 = ((double)((cv$valuePos + index$sample27$203) + index$sample27$210) / index$sample27$210);
									
									// Record the probability of sample task 53 generating output with current configuration.
									if(((Math.log(cv$probabilitySample27Value211) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value211) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value211) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value211) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value211)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									}
									
									// Recorded the probability of reaching sample task 53 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value211);
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
			
			// Save the calculated index value into the array of index value probabilities
			// 
									// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			scratch.cv$var5$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample5) {
			// Set the calculated probabilities to be the distribution values, and normalize
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = scratch.cv$var5$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = scratch.cv$var5$stateProbabilityGlobal[cv$lseIndex];
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
					// Get a local reference to the scratch space.
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var5$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Local copy of the probability array
					state.distribution$sample5[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
															// Local copy of the probability array
					state.distribution$sample5[cv$indexName] = Math.exp((scratch.cv$var5$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var5$stateProbabilityGlobal.length; cv$indexName += 1)
				// Local copy of the probability array
				state.distribution$sample5[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Calculate the probability of the samples represented by sample11 using probability
	// distributions.
	private final void logProbabilityDistribution$sample11() {
		// Determine if we need to calculate the values for sample task 11 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample11) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(state.fixedFlag$sample11) {
				// Generating probabilities for sample task
				// The sample value to calculate the probability of generating
				int cv$sampleValue = state.v2[0];
				
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[cv$sampleValue])) && (state.weightings[cv$sampleValue] <= 1.0))?Math.log(state.weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Store the sample task probability
				state.logProbability$var11 = cv$distributionAccumulator;
				
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				state.logProbability$v2 = (state.logProbability$v2 + cv$distributionAccumulator);
				
				// Add probability to model
				// 
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
				
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample11" with its value "true".
				state.fixedProbFlag$sample11 = true;
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(state.fixedFlag$sample11)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				state.logProbability$v2 = (state.logProbability$v2 + state.logProbability$var11);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var11);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample11)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var11);
		}
	}

	// Calculate the probability of the samples represented by sample27 using probability
	// distributions.
	private final void logProbabilityDistribution$sample27() {
		// Determine if we need to calculate the values for sample task 27 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample27) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(state.fixedFlag$sample27) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int i = 0; i < state.size; i += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = state.v2[(i + 1)];
					
					// Variable declaration of cv$distributionAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$distributionAccumulator moved.
					// Declaration comment was:
					// An accumulator for log probabilities.
					// 
					// Store the value of the function call, so the function call is only made once.
					// 
					// Scale the probability relative to the observed distribution space.
					// 
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					// 
					// Variable declaration of cv$distributionAccumulator moved.
					// Declaration comment was:
					// An accumulator for log probabilities.
					// 
					// Store the value of the function call, so the function call is only made once.
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[cv$sampleValue])) && (state.weightings[cv$sampleValue] <= 1.0))?Math.log(state.weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					// 
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					
					// Store the sample task probability
					state.logProbability$sample27[i] = cv$distributionAccumulator;
				}
				
				// Update the variable probability
				state.logProbability$v2 = (state.logProbability$v2 + cv$accumulator);
				
				// Add probability to model
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample27" with its value "true".
				state.fixedProbFlag$sample27 = true;
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.size; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample27[i]);
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(state.fixedFlag$sample27)
				// Update the variable probability
				state.logProbability$v2 = (state.logProbability$v2 + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample27)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample5 using probability
	// distributions.
	private final void logProbabilityDistribution$sample5() {
		// Determine if we need to calculate the values for sample task 5 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample5) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(state.fixedFlag$sample5) {
				// Generating probabilities for sample task
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
												// The sample value to calculate the probability of generating
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
												// The sample value to calculate the probability of generating
				double cv$distributionAccumulator = ((((((0.0 <= state.v1) && (state.v1 < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[state.v1])) && (state.weightings[state.v1] <= 1.0))?Math.log(state.weightings[state.v1]):Double.NEGATIVE_INFINITY);
				
				// Store the sample task probability
				state.logProbability$v1 = cv$distributionAccumulator;
				
				// Add probability to model
				// 
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
				
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample5" with its value "true".
				state.fixedProbFlag$sample5 = true;
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$v1);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample5)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$v1);
		}
	}

	// Calculate the probability of the samples represented by sample53 using probability
	// distributions.
	private final void logProbabilityDistribution$sample53() {
		// Determine if we need to calculate the values for sample task 53 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample53) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.size; j += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 53 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				boolean cv$sampleValue = state.v[j];
				
				// Enumerating the possible arguments for Bernoulli 52.
				if((0 == j)) {
					// Enumerating the possible arguments for Bernoulli 52.
					if(state.fixedFlag$sample5) {
						if(state.fixedFlag$sample11) {
							if(state.fixedFlag$sample27) {
																								// Substituted "j" with its value "0".
								double var51 = ((double)((state.v1 + state.v2[0]) + state.v2[1]) / state.v2[1]);
								
								// Store the value of the function call, so the function call is only made once.
								cv$distributionAccumulator = (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY);
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							} else {
								// Enumerating the possible outputs of Categorical 26.
								for(int index$sample27$383 = 0; index$sample27$383 < state.weightings.length; index$sample27$383 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "i" with its value "0".
									double cv$probabilitySample27Value384 = state.distribution$sample27[0][index$sample27$383];
									
																											// Substituted "j" with its value "0".
									double var51 = ((double)((state.v1 + state.v2[0]) + index$sample27$383) / index$sample27$383);
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample27Value384) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value384);
								}
							}
						} else {
							// Enumerating the possible outputs of Categorical 10.
							for(int index$sample11$372 = 0; index$sample11$372 < state.weightings.length; index$sample11$372 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample11Value373 = state.distribution$sample11[index$sample11$372];
								if(state.fixedFlag$sample27) {
																											// Substituted "j" with its value "0".
									double var51 = ((double)((state.v1 + index$sample11$372) + state.v2[1]) / state.v2[1]);
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample11Value373) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value373);
								} else {
									// Enumerating the possible outputs of Categorical 26.
									for(int index$sample27$389 = 0; index$sample27$389 < state.weightings.length; index$sample27$389 += 1) {
										// Update the probability of sampling this value from the distribution value.
										// 
										// Substituted "i" with its value "0".
										double cv$probabilitySample27Value390 = (cv$probabilitySample11Value373 * state.distribution$sample27[0][index$sample27$389]);
										double var51 = ((double)((state.v1 + index$sample11$372) + index$sample27$389) / index$sample27$389);
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(cv$probabilitySample27Value390) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value390);
									}
								}
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 4.
						for(int index$sample5$367 = 0; index$sample5$367 < state.weightings.length; index$sample5$367 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample5Value368 = state.distribution$sample5[index$sample5$367];
							if(state.fixedFlag$sample11) {
								if(state.fixedFlag$sample27) {
																											// Substituted "j" with its value "0".
									double var51 = ((double)((index$sample5$367 + state.v2[0]) + state.v2[1]) / state.v2[1]);
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample5Value368) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value368);
								} else {
									// Enumerating the possible outputs of Categorical 26.
									for(int index$sample27$395 = 0; index$sample27$395 < state.weightings.length; index$sample27$395 += 1) {
										// Update the probability of sampling this value from the distribution value.
										// 
										// Substituted "i" with its value "0".
										double cv$probabilitySample27Value396 = (cv$probabilitySample5Value368 * state.distribution$sample27[0][index$sample27$395]);
										
																														// Substituted "j" with its value "0".
										double var51 = ((double)((index$sample5$367 + state.v2[0]) + index$sample27$395) / index$sample27$395);
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(cv$probabilitySample27Value396) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value396);
									}
								}
							} else {
								// Enumerating the possible outputs of Categorical 10.
								for(int index$sample11$377 = 0; index$sample11$377 < state.weightings.length; index$sample11$377 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample11Value378 = (cv$probabilitySample5Value368 * state.distribution$sample11[index$sample11$377]);
									if(state.fixedFlag$sample27) {
																														// Substituted "j" with its value "0".
										double var51 = ((double)((index$sample5$367 + index$sample11$377) + state.v2[1]) / state.v2[1]);
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(cv$probabilitySample11Value378) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value378);
									} else {
										// Enumerating the possible outputs of Categorical 26.
										for(int index$sample27$401 = 0; index$sample27$401 < state.weightings.length; index$sample27$401 += 1) {
											// Update the probability of sampling this value from the distribution value.
											// 
											// Substituted "i" with its value "0".
											double cv$probabilitySample27Value402 = (cv$probabilitySample11Value378 * state.distribution$sample27[0][index$sample27$401]);
											double var51 = ((double)((index$sample5$367 + index$sample11$377) + index$sample27$401) / index$sample27$401);
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(cv$probabilitySample27Value402) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
											
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
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value402);
										}
									}
								}
							}
						}
					}
				}
				
				// Enumerating the possible arguments for Bernoulli 52.
				if(state.fixedFlag$sample5) {
					if(state.fixedFlag$sample27) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 <= j)) {
							double var51 = ((double)((state.v1 + state.v2[j]) + state.v2[(j + 1)]) / state.v2[(j + 1)]);
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY);
							
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
					} else {
						int i = (j - 1);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= i)) {
							// Enumerating the possible outputs of Categorical 26.
							for(int index$sample27$440 = 0; index$sample27$440 < state.weightings.length; index$sample27$440 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample27Value441 = state.distribution$sample27[i][index$sample27$440];
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
								// 
								// Substituted "i" with its value "(j - 1)".
								// 
								// Substituted "i" with its value "(j - 1)".
								// 
								// Substituted "i" with its value "(j - 1)".
								// 
								// Substituted "i" with its value "(j - 1)".
								if((i == j)) {
									double var51 = ((double)((state.v1 + index$sample27$440) + index$sample27$440) / index$sample27$440);
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample27Value441) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value441);
								}
								
																								// Substituted "index$i$452" with its value "j".
								else {
									// Enumerating the possible outputs of Categorical 26.
									for(int index$sample27$453 = 0; index$sample27$453 < state.weightings.length; index$sample27$453 += 1) {
										// Update the probability of sampling this value from the distribution value.
										// 
										// Substituted "index$i$452" with its value "j".
										double cv$probabilitySample27Value454 = (cv$probabilitySample27Value441 * state.distribution$sample27[j][index$sample27$453]);
										double var51 = ((double)((state.v1 + index$sample27$440) + index$sample27$453) / index$sample27$453);
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(cv$probabilitySample27Value454) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value454);
									}
								}
							}
						}
					}
				} else {
					// Enumerating the possible outputs of Categorical 4.
					for(int index$sample5$434 = 0; index$sample5$434 < state.weightings.length; index$sample5$434 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample5Value435 = state.distribution$sample5[index$sample5$434];
						if(state.fixedFlag$sample27) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((1 <= j)) {
								double var51 = ((double)((index$sample5$434 + state.v2[j]) + state.v2[(j + 1)]) / state.v2[(j + 1)]);
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample5Value435) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value435);
							}
						} else {
							int i = (j - 1);
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((0 <= i)) {
								// Enumerating the possible outputs of Categorical 26.
								for(int index$sample27$446 = 0; index$sample27$446 < state.weightings.length; index$sample27$446 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample27Value447 = (cv$probabilitySample5Value435 * state.distribution$sample27[i][index$sample27$446]);
									
									// Constraints moved from conditionals in inner loops/scopes/etc.
									// 
									// Substituted "i" with its value "(j - 1)".
									// 
									// Substituted "i" with its value "(j - 1)".
									// 
									// Substituted "i" with its value "(j - 1)".
									// 
									// Substituted "i" with its value "(j - 1)".
									if((i == j)) {
										double var51 = ((double)((index$sample5$434 + index$sample27$446) + index$sample27$446) / index$sample27$446);
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(cv$probabilitySample27Value447) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value447);
									}
									
																											// Substituted "index$i$459" with its value "j".
									else {
										// Enumerating the possible outputs of Categorical 26.
										for(int index$sample27$460 = 0; index$sample27$460 < state.weightings.length; index$sample27$460 += 1) {
											// Update the probability of sampling this value from the distribution value.
											// 
											// Substituted "index$i$459" with its value "j".
											double cv$probabilitySample27Value461 = (cv$probabilitySample27Value447 * state.distribution$sample27[j][index$sample27$460]);
											double var51 = ((double)((index$sample5$434 + index$sample27$446) + index$sample27$460) / index$sample27$460);
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(cv$probabilitySample27Value461) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
											
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
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value461);
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
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample53[j] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$v = (state.logProbability$v + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample53 = ((state.fixedFlag$sample5 && state.fixedFlag$sample11) && state.fixedFlag$sample27);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.size; j += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample53[j]);
			
			// Update the variable probability
			state.logProbability$v = (state.logProbability$v + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample11 using sampled
	// values.
	private final void logProbabilityValue$sample11() {
		// Determine if we need to calculate the values for sample task 11 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample11) {
			// Generating probabilities for sample task
			// The sample value to calculate the probability of generating
			int cv$sampleValue = state.v2[0];
			
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[cv$sampleValue])) && (state.weightings[cv$sampleValue] <= 1.0))?Math.log(state.weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$var11 = cv$distributionAccumulator;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			state.logProbability$v2 = (state.logProbability$v2 + cv$distributionAccumulator);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample11)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample11 = state.fixedFlag$sample11;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$v2 = (state.logProbability$v2 + state.logProbability$var11);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var11);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample11)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var11);
		}
	}

	// Calculate the probability of the samples represented by sample27 using sampled
	// values.
	private final void logProbabilityValue$sample27() {
		// Determine if we need to calculate the values for sample task 27 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample27) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.size; i += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = state.v2[(i + 1)];
				
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[cv$sampleValue])) && (state.weightings[cv$sampleValue] <= 1.0))?Math.log(state.weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample27[i] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$v2 = (state.logProbability$v2 + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample27)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample27 = state.fixedFlag$sample27;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.size; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample27[i]);
			
			// Update the variable probability
			state.logProbability$v2 = (state.logProbability$v2 + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample27)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample5 using sampled values.
	private final void logProbabilityValue$sample5() {
		// Determine if we need to calculate the values for sample task 5 or if we should
		// just use cached values.
		if(!state.fixedProbFlag$sample5) {
			// Generating probabilities for sample task
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			// 
			// Scale the probability relative to the observed distribution space.
			// 
			// Add the probability of this distribution configuration to the accumulator.
			// 
			// An accumulator for the distributed probability space covered.
			// 
			// Variable declaration of cv$distributionAccumulator moved.
			// Declaration comment was:
			// An accumulator for log probabilities.
			// 
			// Store the value of the function call, so the function call is only made once.
			// 
									// The sample value to calculate the probability of generating
			double cv$distributionAccumulator = ((((((0.0 <= state.v1) && (state.v1 < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[state.v1])) && (state.weightings[state.v1] <= 1.0))?Math.log(state.weightings[state.v1]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			state.logProbability$v1 = cv$distributionAccumulator;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			// Declaration comment was:
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			// 
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample5)
				// Variable declaration of cv$accumulator moved.
				// Declaration comment was:
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample5 = state.fixedFlag$sample5;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$v1);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(state.fixedFlag$sample5)
				// Variable declaration of cv$accumulator moved.
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$v1);
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
			for(int j = 0; j < state.size; j += 1) {
				double var51 = ((double)((state.v1 + state.v2[j]) + state.v2[(j + 1)]) / state.v2[(j + 1)]);
				
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				// 
				// Scale the probability relative to the observed distribution space.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				// 
				// Variable declaration of cv$distributionAccumulator moved.
				// Declaration comment was:
				// An accumulator for log probabilities.
				// 
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				double cv$distributionAccumulator = (((0.0 <= var51) && (var51 <= 1.0))?Math.log((state.v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				state.logProbability$sample53[j] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			state.logProbability$v = (state.logProbability$v + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			state.fixedProbFlag$sample53 = ((state.fixedFlag$sample5 && state.fixedFlag$sample11) && state.fixedFlag$sample27);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.size; j += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample53[j]);
			
			// Update the variable probability
			state.logProbability$v = (state.logProbability$v + cv$accumulator);
			
			// Add probability to model
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample5)
			state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample11)
			state.v2[0] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample27)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							state.v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, state.weightings, state.weightings.length);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.size, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j = forStart$j; j < forEnd$j; j += 1)
						state.v[j] = DistributionSampling.sampleBernoulli(RNG$1, ((double)((state.v1 + state.v2[j]) + state.v2[(j + 1)]) / state.v2[(j + 1)]));
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample5) {
			for(int index$var4 = 0; index$var4 < state.weightings.length; index$var4 += 1)
				// Save the probability of each value
				// 
																// cv$distribution$sample5's comment
				// Create local copy of variable probabilities.
				state.distribution$sample5[index$var4] = ((((0 < state.weightings.length) && (0.0 <= state.weightings[index$var4])) && (state.weightings[index$var4] <= 1.0))?state.weightings[index$var4]:0.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample11) {
			for(int index$var10 = 0; index$var10 < state.weightings.length; index$var10 += 1)
				// Save the probability of each value
				// 
																// cv$distribution$sample11's comment
				// Create local copy of variable probabilities.
				state.distribution$sample11[index$var10] = ((((0 < state.weightings.length) && (0.0 <= state.weightings[index$var10])) && (state.weightings[index$var10] <= 1.0))?state.weightings[index$var10]:0.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample27)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1) {
							// Create local copy of variable probabilities.
							double[] cv$distribution$sample27 = state.distribution$sample27[i];
							for(int index$var26 = 0; index$var26 < state.weightings.length; index$var26 += 1)
								// Save the probability of each value
								// 
								// Probability for this value
								cv$distribution$sample27[index$var26] = ((((0 < state.weightings.length) && (0.0 <= state.weightings[index$var26])) && (state.weightings[index$var26] <= 1.0))?state.weightings[index$var26]:0.0);
						}
				}
			);

	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample5)
			state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample11)
			state.v2[0] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample27)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							state.v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, state.weightings, state.weightings.length);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(state.RNG$, 0, state.size, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j = forStart$j; j < forEnd$j; j += 1)
						state.v[j] = DistributionSampling.sampleBernoulli(RNG$1, ((double)((state.v1 + state.v2[j]) + state.v2[(j + 1)]) / state.v2[(j + 1)]));
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample5)
			state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample11)
			state.v2[0] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample27)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							state.v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, state.weightings, state.weightings.length);
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample5)
			state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample11)
			state.v2[0] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!state.fixedFlag$sample27)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(state.RNG$, 0, state.size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							state.v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, state.weightings, state.weightings.length);
				}
			);

	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample5)
				inferSample5();
			if(!state.fixedFlag$sample11)
				inferSample11();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample27) {
				for(int i = 0; i < state.size; i += 1)
					inferSample27(i);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!state.fixedFlag$sample27) {
				for(int i = (state.size - 1); i >= 0; i -= 1)
					inferSample27(i);
			}
			if(!state.fixedFlag$sample11)
				inferSample11();
			if(!state.fixedFlag$sample5)
				inferSample5();
		}
		
		// Reverse the direction of execution for the next iteration
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample5)
			drawValueSample5();
		if(!state.constrainedFlag$sample11)
			drawValueSample11();
		for(int i = 0; i < state.size; i += 1) {
			if(!state.constrainedFlag$sample27[i])
				drawValueSample27(i);
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
		if(!state.fixedProbFlag$sample5)
			state.logProbability$v1 = Double.NaN;
		state.logProbability$v2 = 0.0;
		if(!state.fixedProbFlag$sample11)
			state.logProbability$var11 = Double.NaN;
		if(!state.fixedProbFlag$sample27) {
			for(int i = 0; i < state.size; i += 1)
				state.logProbability$sample27[i] = Double.NaN;
		}
		state.logProbability$v = 0.0;
		if(!state.fixedProbFlag$sample53) {
			for(int j = 0; j < state.size; j += 1)
				state.logProbability$sample53[j] = Double.NaN;
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		state.size = state.length$value;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample27$1 = 0; index$constrainedFlag$sample27$1 < state.constrainedFlag$sample27.length; index$constrainedFlag$sample27$1 += 1)
			state.constrainedFlag$sample27[index$constrainedFlag$sample27$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		logProbabilityValue$sample53();
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
		logProbabilityDistribution$sample5();
		logProbabilityDistribution$sample11();
		logProbabilityDistribution$sample27();
		logProbabilityDistribution$sample53();
	}

	// Method to calculate the probabilities of all the samples in the model including
	// those generating fixed data. In the process probabilities for all the random variables
	// and for the model as a whole will be calculated. This model only uses values.
	@Override
	public final void logModelProbabilitiesVal() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using values only.
		logProbabilityValue$sample5();
		logProbabilityValue$sample11();
		logProbabilityValue$sample27();
		logProbabilityValue$sample53();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = state.v.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.v[cv$index1] = state.value[cv$index1];
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
		     + "model DistributionTest4(double[] weightings, boolean[] value) {\n"
		     + "    int size = value.length;\n"
		     + "    \n"
		     + "    int v1 = categorical(weightings).sampleDistribution();\n"
		     + "    \n"
		     + "    int[] v2 = new int[size + 1];\n"
		     + "    v2[0] = categorical(weightings).sampleDistribution();\n"
		     + "    for(int i:[0..size))\n"
		     + "        v2[i + 1] = categorical(weightings).sampleDistribution();\n"
		     + "        \n"
		     + "    boolean[] v = new boolean[size];\n"
		     + "    for(int j:[0..size))\n"
		     + "        v[j] = bernoulli(((1.0*v1) + v2[j] + v2[j+1])/v2[j+1]).sample();\n"
		     + "        \n"
		     + "    v.observe(value);\n"
		     + "}\n"
		     + "";
	}
}