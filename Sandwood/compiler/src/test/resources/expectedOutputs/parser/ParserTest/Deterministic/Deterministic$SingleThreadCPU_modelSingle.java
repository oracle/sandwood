package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Deterministic$SingleThreadCPU extends CoreModelSingleThreadCPU implements Deterministic$CoreInterface {

	// Declare the variables for the model.
	int[] a;
	int[] b;
	boolean[] constrainedFlag$sample29;
	boolean[] constrainedFlag$sample55;
	boolean fixedFlag$sample29 = false;
	boolean fixedFlag$sample55 = false;
	boolean fixedProbFlag$sample29 = false;
	boolean fixedProbFlag$sample55 = false;
	boolean fixedProbFlag$sample75 = false;
	boolean[] flips;
	boolean[] flipsMeasured;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$a;
	double logProbability$b;
	double logProbability$flips;
	double logProbability$m;
	double[] logProbability$sample55;
	double logProbability$var29;
	double logProbability$var74;
	double[][] m;
	int n;
	int states;
	boolean system$gibbsForward = true;
	double[] v;
	double[] cv$var29$countGlobal;
	double[] cv$var54$stateProbabilityGlobal;

	public Deterministic$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for a.
	@Override
	public final int[] get$a() {
		return a;
	}

	// Setter for a.
	@Override
	public final void set$a(int[] cv$value, boolean allocated$) {
		// Set flags for all the side effects of a including if probabilities need to be updated.
		a = cv$value;
		
		// Unset the fixed probability flag for sample 55 as it depends on a.
		fixedProbFlag$sample55 = false;
		
		// Unset the fixed probability flag for sample 75 as it depends on a.
		fixedProbFlag$sample75 = false;
	}

	// Getter for b.
	@Override
	public final int[] get$b() {
		return b;
	}

	// Getter for fixedFlag$sample29.
	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	// Setter for fixedFlag$sample29.
	@Override
	public final void set$fixedFlag$sample29(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample29 including if probabilities
		// need to be updated.
		fixedFlag$sample29 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample29$1 = 0; index$constrainedFlag$sample29$1 < constrainedFlag$sample29.length; index$constrainedFlag$sample29$1 += 1)
				constrainedFlag$sample29[index$constrainedFlag$sample29$1] = true;
		}
		
		// Should the probability of sample 29 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample29 = (fixedFlag$sample29 && fixedProbFlag$sample29);
		
		// Should the probability of sample 55 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample55 = (fixedFlag$sample29 && fixedProbFlag$sample55);
	}

	// Getter for fixedFlag$sample55.
	@Override
	public final boolean get$fixedFlag$sample55() {
		return fixedFlag$sample55;
	}

	// Setter for fixedFlag$sample55.
	@Override
	public final void set$fixedFlag$sample55(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample55 including if probabilities
		// need to be updated.
		fixedFlag$sample55 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample55$1 = 0; index$constrainedFlag$sample55$1 < constrainedFlag$sample55.length; index$constrainedFlag$sample55$1 += 1)
				constrainedFlag$sample55[index$constrainedFlag$sample55$1] = true;
		}
		
		// Should the probability of sample 55 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample55 = (fixedFlag$sample55 && fixedProbFlag$sample55);
		
		// Should the probability of sample 75 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample75 = (fixedFlag$sample55 && fixedProbFlag$sample75);
	}

	// Getter for flips.
	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	// Getter for flipsMeasured.
	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	// Setter for flipsMeasured.
	@Override
	public final void set$flipsMeasured(boolean[] cv$value, boolean allocated$) {
		flipsMeasured = cv$value;
	}

	// Getter for logProbability$$evidence.
	@Override
	public final double get$logProbability$$evidence() {
		return logProbability$$evidence;
	}

	// Getter for the probability of logProbability$$model.
	@Override
	public final double getCurrentLogProbability() {
		return logProbability$$model;
	}

	// Getter for logProbability$a.
	@Override
	public final double get$logProbability$a() {
		return logProbability$a;
	}

	// Getter for logProbability$b.
	@Override
	public final double get$logProbability$b() {
		return logProbability$b;
	}

	// Getter for logProbability$flips.
	@Override
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	// Getter for logProbability$m.
	@Override
	public final double get$logProbability$m() {
		return logProbability$m;
	}

	// Getter for m.
	@Override
	public final double[][] get$m() {
		return m;
	}

	// Setter for m.
	@Override
	public final void set$m(double[][] cv$value, boolean allocated$) {
		// Set flags for all the side effects of m including if probabilities need to be updated.
		m = cv$value;
		
		// Unset the fixed probability flag for sample 29 as it depends on m.
		fixedProbFlag$sample29 = false;
		
		// Unset the fixed probability flag for sample 55 as it depends on m.
		fixedProbFlag$sample55 = false;
	}

	// Getter for n.
	@Override
	public final int get$n() {
		return n;
	}

	// Setter for n.
	@Override
	public final void set$n(int cv$value, boolean allocated$) {
		n = cv$value;
	}

	// Getter for states.
	@Override
	public final int get$states() {
		return states;
	}

	// Getter for v.
	@Override
	public final double[] get$v() {
		return v;
	}

	// Pick a value from the distribution for the unconditioned variable from sample29
	private final void drawValueSample29(int var28) {
		double[] var29 = m[var28];
		DistributionSampling.sampleDirichlet(RNG$, v, states, var29);
	}

	// Pick a value from the distribution for the unconditioned variable from sample55
	private final void drawValueSample55(int i$var46) {
		a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], states);
		
		// Guards to ensure that b is only updated when there is a valid path.
		// 
		// Looking for a path between Sample 55 and consumer int[] 50.
		{
			{
				for(int index$i$1_1 = 1; index$i$1_1 < n; index$i$1_1 += 1) {
					if((i$var46 == (index$i$1_1 - 1))) {
						{
							b[index$i$1_1] = a[(index$i$1_1 - 1)];
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 29 drawn from Dirichlet 17. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample29(int var28) {
		if(true) {
			constrainedFlag$sample29[((var28 - 0) / 1)] = false;
			
			// A reference local to the function for the sample variable.
			double[] cv$targetLocal = m[var28];
			
			// A local reference to the scratch space.
			double[] cv$countLocal = cv$var29$countGlobal;
			
			// Get the length of the array
			int cv$arrayLength = states;
			
			// Initialize the array values to 0.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				// Processing random variable 53.
				{
					// Looking for a path between Sample 29 and consumer Categorical 53.
					{
						{
							for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
								if((var28 == b[i$var46])) {
									// Processing sample task 55 of consumer random variable null.
									{
										{
											// Flag recording if this sample task of the consuming random variable is constrained.
											boolean cv$sampleConstrained = (fixedFlag$sample55 || constrainedFlag$sample55[((i$var46 - 1) / 1)]);
											if(cv$sampleConstrained) {
												// Mark that the sample has observed constrained data.
												constrainedFlag$sample29[((var28 - 0) / 1)] = true;
												{
													{
														{
															{
																{
																	// Increment the sample counter with the value sampled by sample task 55 of random
																	// variable var53
																	cv$countLocal[a[i$var46]] = (cv$countLocal[a[i$var46]] + 1.0);
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
			if(constrainedFlag$sample29[((var28 - 0) / 1)])
				// Calculate the new sample value
				// 
				// Calculate a new sample value and write it into cv$targetLocal.
				Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, states);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 55 drawn from Categorical 53. Inference was performed using variable
	// marginalization.
	private final void inferSample55(int i$var46) {
		if(true) {
			constrainedFlag$sample55[((i$var46 - 1) / 1)] = false;
			
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, states);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var54$stateProbabilityGlobal;
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
				int var54 = cv$currentValue;
				
				// Guards to ensure that a is only updated when there is a valid path.
				{
					{
						{
							a[i$var46] = cv$currentValue;
						}
					}
				}
				
				// Guards to ensure that b is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 55 and consumer int[] 50.
				{
					{
						for(int index$i$2_1 = 1; index$i$2_1 < n; index$i$2_1 += 1) {
							if((i$var46 == (index$i$2_1 - 1))) {
								{
									b[index$i$2_1] = a[(index$i$2_1 - 1)];
								}
							}
						}
					}
				}
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// Constructing a random variable input for use later.
					double[] var52 = m[b[i$var46]];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < states)) && (0 < states)) && (0.0 <= var52[cv$currentValue])) && (var52[cv$currentValue] <= 1.0))?Math.log(var52[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 53.
					{
						// Looking for a path between Sample 55 and consumer Categorical 53.
						{
							{
								int traceTempVariable$var49$3_1 = cv$currentValue;
								for(int index$i$3_2 = 1; index$i$3_2 < n; index$i$3_2 += 1) {
									if((i$var46 == (index$i$3_2 - 1))) {
										int traceTempVariable$var51$3_3 = traceTempVariable$var49$3_1;
										for(int index$i$3_4 = 1; index$i$3_4 < n; index$i$3_4 += 1) {
											if((index$i$3_2 == index$i$3_4)) {
												// Processing sample task 55 of consumer random variable null.
												{
													{
														// Flag recording if this sample task of the consuming random variable is constrained.
														boolean cv$sampleConstrained = (fixedFlag$sample55 || constrainedFlag$sample55[((index$i$3_4 - 1) / 1)]);
														if(cv$sampleConstrained) {
															// Mark that the sample has observed constrained data.
															constrainedFlag$sample55[((i$var46 - 1) / 1)] = true;
															
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
																				double[] sc$var52$1 = m[traceTempVariable$var51$3_3];
																				
																				// Record the probability of sample task 55 generating output with current configuration.
																				if(((Math.log(1.0) + ((((((0.0 <= a[index$i$3_4]) && (a[index$i$3_4] < states)) && (0 < states)) && (0.0 <= sc$var52$1[a[index$i$3_4]])) && (sc$var52$1[a[index$i$3_4]] <= 1.0))?Math.log(sc$var52$1[a[index$i$3_4]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= a[index$i$3_4]) && (a[index$i$3_4] < states)) && (0 < states)) && (0.0 <= sc$var52$1[a[index$i$3_4]])) && (sc$var52$1[a[index$i$3_4]] <= 1.0))?Math.log(sc$var52$1[a[index$i$3_4]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= a[index$i$3_4]) && (a[index$i$3_4] < states)) && (0 < states)) && (0.0 <= sc$var52$1[a[index$i$3_4]])) && (sc$var52$1[a[index$i$3_4]] <= 1.0))?Math.log(sc$var52$1[a[index$i$3_4]]):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= a[index$i$3_4]) && (a[index$i$3_4] < states)) && (0 < states)) && (0.0 <= sc$var52$1[a[index$i$3_4]])) && (sc$var52$1[a[index$i$3_4]] <= 1.0))?Math.log(sc$var52$1[a[index$i$3_4]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= a[index$i$3_4]) && (a[index$i$3_4] < states)) && (0 < states)) && (0.0 <= sc$var52$1[a[index$i$3_4]])) && (sc$var52$1[a[index$i$3_4]] <= 1.0))?Math.log(sc$var52$1[a[index$i$3_4]]):Double.NEGATIVE_INFINITY)));
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
								}
							}
						}
					}
					
					// Processing random variable 73.
					{
						// Looking for a path between Sample 55 and consumer Bernoulli 73.
						{
							{
								int traceTempVariable$var70$6_1 = cv$currentValue;
								for(int j = 0; j < n; j += 1) {
									if((i$var46 == (j + 1))) {
										// Processing sample task 75 of consumer random variable null.
										{
											{
												// Flag recording if this sample task of the consuming random variable is constrained.
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													// Mark that the sample has observed constrained data.
													constrainedFlag$sample55[((i$var46 - 1) / 1)] = true;
													
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
																		double var72 = (double)(1 / traceTempVariable$var70$6_1);
																		
																		// Record the probability of sample task 75 generating output with current configuration.
																		if(((Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY)));
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
			if(constrainedFlag$sample55[((i$var46 - 1) / 1)]) {
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
				int var54 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates);
				
				// Guards to ensure that a is only updated when there is a valid path.
				{
					{
						{
							a[i$var46] = var54;
						}
					}
				}
				
				// Guards to ensure that b is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 55 and consumer int[] 50.
				{
					{
						for(int index$i$10_1 = 1; index$i$10_1 < n; index$i$10_1 += 1) {
							if((i$var46 == (index$i$10_1 - 1))) {
								{
									b[index$i$10_1] = a[(index$i$10_1 - 1)];
								}
							}
						}
					}
				}
			}
		}
	}

	// Calculate the probability of the samples represented by sample29 using sampled
	// values.
	private final void logProbabilityValue$sample29() {
		// Determine if we need to calculate the values for sample task 29 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample29) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var28 = 0; var28 < states; var28 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						double[] cv$sampleValue = m[var28];
						{
							{
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, states));
								
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
				logProbability$var29 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample29 = fixedFlag$sample29;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var28 = 0; var28 < states; var28 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var29;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample55 using sampled
	// values.
	private final void logProbabilityValue$sample55() {
		// Determine if we need to calculate the values for sample task 55 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample55) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = a[i$var46];
						{
							{
								double[] var52 = m[b[i$var46]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < states)) && (0 < states)) && (0.0 <= var52[cv$sampleValue])) && (var52[cv$sampleValue] <= 1.0))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
				
				// Only update the sample if it was reached, otherwise the NaN will be
				// erroneously over written.
				if(cv$sampleReached)
					// Store the sample task probability
					logProbability$sample55[((i$var46 - 1) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that b is only updated once for this probability.
				boolean cv$guard$b = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 55 and consumer int[] 50.
				{
					{
						for(int index$i$2_1 = 1; index$i$2_1 < n; index$i$2_1 += 1) {
							if((i$var46 == (index$i$2_1 - 1))) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$b) {
									// Set the guard so the update is only applied once.
									cv$guard$b = true;
									
									// Update the variable probability
									logProbability$b = (logProbability$b + cv$sampleProbability);
								}
							}
						}
					}
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			
			// Update the variable probability
			logProbability$a = (logProbability$a + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample55 = (fixedFlag$sample55 && fixedFlag$sample29);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				double cv$sampleValue = logProbability$sample55[((i$var46 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Guard to ensure that b is only updated once for this probability.
				boolean cv$guard$b = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 55 and consumer int[] 50.
				{
					{
						for(int index$i$3_1 = 1; index$i$3_1 < n; index$i$3_1 += 1) {
							if((i$var46 == (index$i$3_1 - 1))) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$b) {
									// Set the guard so the update is only applied once.
									cv$guard$b = true;
									
									// Update the variable probability
									logProbability$b = (logProbability$b + cv$sampleValue);
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			logProbability$a = (logProbability$a + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample75 using sampled
	// values.
	private final void logProbabilityValue$sample75() {
		// Determine if we need to calculate the values for sample task 75 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample75) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j = 0; j < n; j += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = flips[j];
						{
							{
								double var72 = (double)(1 / a[(j + 1)]);
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((cv$sampleValue?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY));
								
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
				logProbability$var74 = cv$accumulator;
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample75 = fixedFlag$sample55;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j = 0; j < n; j += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var74;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocate() {
		// Constructor for v
		{
			v = new double[5];
		}
		
		// If m has not been set already allocate space.
		if(!fixedFlag$sample29) {
			// Constructor for m
			{
				m = new double[5][];
				for(int var28 = 0; var28 < 5; var28 += 1)
					m[var28] = new double[5];
			}
		}
		
		// If a has not been set already allocate space.
		if(!fixedFlag$sample55) {
			// Constructor for a
			{
				a = new int[n];
			}
		}
		
		// Constructor for b
		{
			b = new int[n];
		}
		
		// Constructor for flips
		{
			flips = new boolean[n];
		}
		
		// Constructor for constrainedFlag$sample29
		{
			constrainedFlag$sample29 = new boolean[((((5 - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for constrainedFlag$sample55
		{
			constrainedFlag$sample55 = new boolean[((((n - 1) - 1) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample55
		{
			logProbability$sample55 = new double[((((n - 1) - 1) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var29$countGlobal
		{
			// Allocation of cv$var29$countGlobal for single threaded execution
			cv$var29$countGlobal = new double[5];
		}
		
		// Constructor for cv$var54$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 53. Initially set to the value
			// of putTask 30.
			int cv$var30$max = 5;
			
			// Allocation of cv$var54$stateProbabilityGlobal for single threaded execution
			cv$var54$stateProbabilityGlobal = new double[cv$var30$max];
		}
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		for(int var28 = 0; var28 < states; var28 += 1) {
			double[] var29 = m[var28];
			if(!fixedFlag$sample29)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var29);
		}
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			if(!fixedFlag$sample55)
				b[i$var46] = a[(i$var46 - 1)];
			if(!fixedFlag$sample55)
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], states);
		}
		for(int j = 0; j < n; j += 1)
			flips[j] = DistributionSampling.sampleBernoulli(RNG$, (1 / a[(j + 1)]));
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int var28 = 0; var28 < states; var28 += 1) {
			double[] var29 = m[var28];
			if(!fixedFlag$sample29)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var29);
		}
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			b[i$var46] = a[(i$var46 - 1)];
			if(!fixedFlag$sample55)
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], states);
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		for(int var28 = 0; var28 < states; var28 += 1) {
			double[] var29 = m[var28];
			if(!fixedFlag$sample29)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var29);
		}
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			b[i$var46] = a[(i$var46 - 1)];
			if(!fixedFlag$sample55)
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], states);
		}
		for(int j = 0; j < n; j += 1)
			flips[j] = DistributionSampling.sampleBernoulli(RNG$, (1 / a[(j + 1)]));
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var28 = 0; var28 < states; var28 += 1) {
			double[] var29 = m[var28];
			if(!fixedFlag$sample29)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var29);
		}
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			if(!fixedFlag$sample55)
				b[i$var46] = a[(i$var46 - 1)];
			if(!fixedFlag$sample55)
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], states);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		for(int var28 = 0; var28 < states; var28 += 1) {
			double[] var29 = m[var28];
			if(!fixedFlag$sample29)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var29);
		}
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			b[i$var46] = a[(i$var46 - 1)];
			if(!fixedFlag$sample55)
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], states);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			for(int var28 = 0; var28 < states; var28 += 1) {
				if(!fixedFlag$sample29)
					inferSample29(var28);
			}
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				if(!fixedFlag$sample55)
					inferSample55(i$var46);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int i$var46 = (n - ((((n - 1) - 1) % 1) + 1)); i$var46 >= ((1 - 1) + 1); i$var46 -= 1) {
				if(!fixedFlag$sample55)
					inferSample55(i$var46);
			}
			for(int var28 = (states - ((((states - 1) - 0) % 1) + 1)); var28 >= ((0 - 1) + 1); var28 -= 1) {
				if(!fixedFlag$sample29)
					inferSample29(var28);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
		for(int var28 = 0; var28 < states; var28 += 1) {
			if(!constrainedFlag$sample29[((var28 - 0) / 1)])
				drawValueSample29(var28);
		}
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			if(!constrainedFlag$sample55[((i$var46 - 1) / 1)])
				drawValueSample55(i$var46);
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
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$var29 = Double.NaN;
		logProbability$a = 0.0;
		logProbability$b = 0.0;
		if(!fixedProbFlag$sample55) {
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
				logProbability$sample55[((i$var46 - 1) / 1)] = Double.NaN;
		}
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample75)
			logProbability$var74 = Double.NaN;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		states = 5;
		for(int i$var14 = 0; i$var14 < 5; i$var14 += 1)
			v[i$var14] = 0.1;
		a[0] = 0;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample29$1 = 0; index$constrainedFlag$sample29$1 < constrainedFlag$sample29.length; index$constrainedFlag$sample29$1 += 1)
			constrainedFlag$sample29[index$constrainedFlag$sample29$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample55$1 = 0; index$constrainedFlag$sample55$1 < constrainedFlag$sample55.length; index$constrainedFlag$sample55$1 += 1)
			constrainedFlag$sample55[index$constrainedFlag$sample55$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(fixedFlag$sample29)
			logProbabilityValue$sample29();
		if(fixedFlag$sample55)
			logProbabilityValue$sample55();
		logProbabilityValue$sample75();
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
		logProbabilityValue$sample29();
		logProbabilityValue$sample55();
		logProbabilityValue$sample75();
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
		logProbabilityValue$sample29();
		logProbabilityValue$sample55();
		logProbabilityValue$sample75();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Deep copy between arrays
		boolean[] cv$source1 = flipsMeasured;
		boolean[] cv$target1 = flips;
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
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
			b[i$var46] = a[(i$var46 - 1)];
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
		     + "/**\n"
		     + " * A model for the fairness work.\n"
		     + " */\n"
		     + "public model Deterministic(int n, boolean[] flipsMeasured) {\n"
		     + "    int states = 5;\n"
		     + "\n"
		     + "    double[] v = new double[states];\n"
		     + "    for(int i:[0..states))\n"
		     + "        v[i] = 0.1;\n"
		     + "    \n"
		     + "    double[][] m = dirichlet(v).sample(states);\n"
		     + "\n"
		     + "    int[] a = new int[n];\n"
		     + "    int[] b = new int[n];\n"
		     + "    a[0] = 0;\n"
		     + "    for(int i=1; i<n; i++) {\n"
		     + "        b[i] = a[i-1];\n"
		     + "        a[i] = categorical(m[b[i]]).sample();\n"
		     + "    }\n"
		     + "    \n"
		     + "    boolean[] flips = new boolean[n];\n"
		     + "            \n"
		     + "    for(int j:[0..n))\n"
		     + "        flips[j] = bernoulli(1/a[j+1]).sample();\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}";
	}
}