package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements RaggedArray$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] a;
	private double[][] b;
	private double[] cv$var69$stateProbabilityGlobal;
	private boolean fixedFlag$sample73 = false;
	private boolean fixedProbFlag$sample73 = false;
	private boolean fixedProbFlag$sample89 = false;
	private int i;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$i;
	private double logProbability$obs;
	private double logProbability$var68;
	private double logProbability$var72;
	private double logProbability$var85;
	private boolean[] obs;
	private boolean[] obs_measured;
	private double p;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for a.
	@Override
	public final double[][] get$a() {
		return a;
	}

	// Getter for b.
	@Override
	public final double[][] get$b() {
		return b;
	}

	// Getter for fixedFlag$sample73.
	@Override
	public final boolean get$fixedFlag$sample73() {
		return fixedFlag$sample73;
	}

	// Setter for fixedFlag$sample73.
	@Override
	public final void set$fixedFlag$sample73(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample73 including if probabilities
		// need to be updated.
		fixedFlag$sample73 = cv$value;
		
		// Should the probability of sample 73 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample73 = (fixedFlag$sample73 && fixedProbFlag$sample73);
		
		// Should the probability of sample 89 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample89 = (fixedFlag$sample73 && fixedProbFlag$sample89);
	}

	// Getter for i.
	@Override
	public final int get$i() {
		return i;
	}

	// Setter for i.
	@Override
	public final void set$i(int cv$value) {
		// Set flags for all the side effects of i including if probabilities need to be updated.
		i = cv$value;
		
		// Unset the fixed probability flag for sample 73 as it depends on i.
		fixedProbFlag$sample73 = false;
		
		// Unset the fixed probability flag for sample 89 as it depends on i.
		fixedProbFlag$sample89 = false;
	}

	// Getter for length$obs_measured.
	@Override
	public final int get$length$obs_measured() {
		return length$obs_measured;
	}

	// Setter for length$obs_measured.
	@Override
	public final void set$length$obs_measured(int cv$value) {
		length$obs_measured = cv$value;
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

	// Getter for logProbability$i.
	@Override
	public final double get$logProbability$i() {
		return logProbability$i;
	}

	// Getter for logProbability$obs.
	@Override
	public final double get$logProbability$obs() {
		return logProbability$obs;
	}

	// Getter for obs.
	@Override
	public final boolean[] get$obs() {
		return obs;
	}

	// Getter for obs_measured.
	@Override
	public final boolean[] get$obs_measured() {
		return obs_measured;
	}

	// Setter for obs_measured.
	@Override
	public final void set$obs_measured(boolean[] cv$value) {
		// Set obs_measured
		obs_measured = cv$value;
	}

	// Getter for p.
	@Override
	public final double get$p() {
		return p;
	}

	// Getter for y.
	@Override
	public final int get$y() {
		return y;
	}

	// Setter for y.
	@Override
	public final void set$y(int cv$value) {
		y = cv$value;
	}

	// Calculate the probability of the samples represented by sample73 using sampled
	// values.
	private final void logProbabilityValue$sample73() {
		// Determine if we need to calculate the values for sample task 73 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample73) {
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
				// The sample value to calculate the probability of generating
				int cv$sampleValue = i;
				{
					{
						double[] var67 = a[y];
						
						// Allocate a local variable to hold the length of the array.
						int lengthCV$a$71_2 = -1;
						
						// calculate array length.
						// 
						// Looking for a path between Put 17 and consumer double[] 67.
						{
							if((0 == y))
								lengthCV$a$71_2 = 2;
						}
						
						// Looking for a path between Put 35 and consumer double[] 67.
						{
							if((1 == y))
								lengthCV$a$71_2 = 3;
						}
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$a$71_2))?Math.log(var67[cv$sampleValue]):Double.NEGATIVE_INFINITY));
						
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
			logProbability$var68 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$i = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample73)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample73 = fixedFlag$sample73;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$i;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var68 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample73)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample89 using sampled
	// values.
	private final void logProbabilityValue$sample89() {
		// Determine if we need to calculate the values for sample task 89 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample89) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var84 = 0; var84 < length$obs_measured; var84 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = obs[var84];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?p:(1.0 - p))));
							
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
			if(cv$sampleReached)
				logProbability$var72 = cv$sampleAccumulator;
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var85 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$obs = (logProbability$obs + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample89 = fixedFlag$sample73;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int var84 = 0; var84 < length$obs_measured; var84 += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var85;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(cv$sampleReached)
				logProbability$var72 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$obs = (logProbability$obs + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 73 drawn from Categorical 68. Inference was performed using variable
	// marginalization.
	private final void sample73() {
		if(true) {
			// Calculate the number of states to evaluate.
			int cv$numNumStates = 0;
			{
				// Allocate a local variable to hold the length of the array.
				int lengthCV$a$71_0 = -1;
				
				// calculate array length.
				// 
				// Looking for a path between Put 17 and consumer double[] 67.
				{
					if((0 == y))
						lengthCV$a$71_0 = 2;
				}
				
				// Looking for a path between Put 35 and consumer double[] 67.
				{
					if((1 == y))
						lengthCV$a$71_0 = 3;
				}
				
				// variable marginalization
				cv$numNumStates = Math.max(cv$numNumStates, lengthCV$a$71_0);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var69$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
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
				i = cv$currentValue;
				
				// Guards to ensure that p is only updated when there is a valid path.
				{
					{
						// Write out the new sample value.
						p = b[y][cv$currentValue];
					}
				}
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] cv$temp$0$var67;
					{
						cv$temp$0$var67 = a[y];
					}
					int cv$temp$1$$var159;
					{
						// Allocate a local variable to hold the length of the array.
						int lengthCV$a$71_1 = -1;
						
						// calculate array length.
						// 
						// Looking for a path between Put 17 and consumer double[] 67.
						{
							if((0 == y))
								lengthCV$a$71_1 = 2;
						}
						
						// Looking for a path between Put 35 and consumer double[] 67.
						{
							if((1 == y))
								lengthCV$a$71_1 = 3;
						}
						cv$temp$1$$var159 = lengthCV$a$71_1;
					}
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var159))?Math.log(cv$temp$0$var67[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 72.
					{
						{
							int traceTempVariable$i$6_1 = cv$currentValue;
							
							// Processing sample task 89 of consumer random variable null.
							{
								for(int var84 = 0; var84 < length$obs_measured; var84 += 1) {
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
													double cv$temp$2$p;
													{
														cv$temp$2$p = p;
													}
													
													// Record the probability of sample task 89 generating output with current configuration.
													if(((Math.log(1.0) + Math.log((obs[var84]?cv$temp$2$p:(1.0 - cv$temp$2$p)))) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((obs[var84]?cv$temp$2$p:(1.0 - cv$temp$2$p)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((obs[var84]?cv$temp$2$p:(1.0 - cv$temp$2$p))));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((obs[var84]?cv$temp$2$p:(1.0 - cv$temp$2$p)))))) + 1)) + (Math.log(1.0) + Math.log((obs[var84]?cv$temp$2$p:(1.0 - cv$temp$2$p)))));
													}
													
													// Recorded the probability of reaching sample task 89 with the current configuration.
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
			
			// The sum of all the probabilities in log space
			double cv$logSum = 0.0;
			
			// Sum all the values
			{
				// Initialise the max to the first element.
				double cv$lseMax = cv$stateProbabilityLocal[0];
				
				// Find max value.
				for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
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
					for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
						cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
					
					// Increment the value of the target, moving the value back into log space.
					cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
				}
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numNumStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			i = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates);
			
			// Guards to ensure that p is only updated when there is a valid path.
			{
				{
					// Write out the new sample value.
					p = b[y][i];
				}
			}
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Variable to record the maximum value of Task Get 71. Initially set to the value
		// of putTask 17.
		int cv$var34$max = 2;
		
		// Test if the input to putTask 35 is larger than the current values.
		cv$var34$max = Math.max(cv$var34$max, 3);
		
		// Allocation of cv$var69$stateProbabilityGlobal for single threaded execution
		cv$var69$stateProbabilityGlobal = new double[cv$var34$max];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for a
		{
			a = new double[2][];
			a[0] = new double[2];
			a[1] = new double[3];
		}
		
		// Constructor for b
		{
			b = new double[2][];
			b[0] = new double[2];
			b[1] = new double[3];
		}
		
		// Constructor for obs
		{
			obs = new boolean[length$obs_measured];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$71_3 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 17 and consumer double[] 67.
		{
			if((0 == y)) {
				if(!fixedFlag$sample73)
					lengthCV$a$71_3 = 2;
			}
		}
		
		// Looking for a path between Put 35 and consumer double[] 67.
		{
			if((1 == y)) {
				if(!fixedFlag$sample73)
					lengthCV$a$71_3 = 3;
			}
		}
		if(!fixedFlag$sample73)
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$71_3);
		if(!fixedFlag$sample73)
			p = b[y][i];
		for(int var84 = 0; var84 < length$obs_measured; var84 += 1)
			obs[var84] = DistributionSampling.sampleBernoulli(RNG$, p);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$71_7 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 17 and consumer double[] 67.
		{
			if((0 == y)) {
				if(!fixedFlag$sample73)
					lengthCV$a$71_7 = 2;
			}
		}
		
		// Looking for a path between Put 35 and consumer double[] 67.
		{
			if((1 == y)) {
				if(!fixedFlag$sample73)
					lengthCV$a$71_7 = 3;
			}
		}
		if(!fixedFlag$sample73)
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$71_7);
		p = b[y][i];
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$71_4 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 17 and consumer double[] 67.
		{
			if((0 == y)) {
				if(!fixedFlag$sample73)
					lengthCV$a$71_4 = 2;
			}
		}
		
		// Looking for a path between Put 35 and consumer double[] 67.
		{
			if((1 == y)) {
				if(!fixedFlag$sample73)
					lengthCV$a$71_4 = 3;
			}
		}
		if(!fixedFlag$sample73)
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$71_4);
		p = b[y][i];
		for(int var84 = 0; var84 < length$obs_measured; var84 += 1)
			obs[var84] = DistributionSampling.sampleBernoulli(RNG$, p);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$71_5 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 17 and consumer double[] 67.
		{
			if((0 == y)) {
				if(!fixedFlag$sample73)
					lengthCV$a$71_5 = 2;
			}
		}
		
		// Looking for a path between Put 35 and consumer double[] 67.
		{
			if((1 == y)) {
				if(!fixedFlag$sample73)
					lengthCV$a$71_5 = 3;
			}
		}
		if(!fixedFlag$sample73)
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$71_5);
		if(!fixedFlag$sample73)
			p = b[y][i];
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Allocate a local variable to hold the length of the array.
		int lengthCV$a$71_6 = -1;
		
		// calculate array length.
		// 
		// Looking for a path between Put 17 and consumer double[] 67.
		{
			if((0 == y)) {
				if(!fixedFlag$sample73)
					lengthCV$a$71_6 = 2;
			}
		}
		
		// Looking for a path between Put 35 and consumer double[] 67.
		{
			if((1 == y)) {
				if(!fixedFlag$sample73)
					lengthCV$a$71_6 = 3;
			}
		}
		if(!fixedFlag$sample73)
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$71_6);
		p = b[y][i];
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample73)
				sample73();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!fixedFlag$sample73)
				sample73();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		double[] var6 = a[0];
		var6[0] = 0.4;
		var6[1] = 0.6;
		double[] var19 = a[1];
		var19[0] = 0.2;
		var19[1] = 0.3;
		var19[2] = 0.5;
		double[] var38 = b[0];
		var38[0] = 0.2;
		var38[1] = 0.8;
		double[] var51 = b[1];
		var51[0] = 0.4;
		var51[1] = 0.2;
		var51[2] = 0.6;
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
		logProbability$var68 = 0.0;
		if(!fixedProbFlag$sample73)
			logProbability$i = Double.NaN;
		logProbability$var72 = Double.NaN;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample89)
			logProbability$var85 = Double.NaN;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(fixedFlag$sample73)
			logProbabilityValue$sample73();
		logProbabilityValue$sample89();
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
		logProbabilityValue$sample73();
		logProbabilityValue$sample89();
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
		logProbabilityValue$sample73();
		logProbabilityValue$sample89();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Deep copy between arrays
		boolean[] cv$source1 = obs_measured;
		boolean[] cv$target1 = obs;
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
		p = b[y][i];
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model RaggedArray(int y, boolean[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    double[][] b = {{0.2, 0.8}, {0.4, 0.2, 0.6}};\n"
		     + "    \n"
		     + "    int i = categorical(a[y]).sample();\n"
		     + "    double p = b[y][i];\n"
		     + "    boolean[] obs = bernoulli(p).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}