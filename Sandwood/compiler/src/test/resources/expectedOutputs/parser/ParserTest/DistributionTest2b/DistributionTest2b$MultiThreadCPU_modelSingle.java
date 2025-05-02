package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DistributionTest2b$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DistributionTest2b$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] cv$var23$stateProbabilityGlobal;
	private double[] cv$var36$stateProbabilityGlobal;
	private double[] cv$var5$stateProbabilityGlobal;
	private double[] cv$var9$stateProbabilityGlobal;
	private double[][] distribution$sample23;
	private double[] distribution$sample5;
	private double[] distribution$sample9;
	private boolean fixedFlag$sample23 = false;
	private boolean fixedFlag$sample36 = false;
	private boolean fixedFlag$sample5 = false;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample23 = false;
	private boolean fixedProbFlag$sample36 = false;
	private boolean fixedProbFlag$sample43 = false;
	private boolean fixedProbFlag$sample5 = false;
	private boolean fixedProbFlag$sample9 = false;
	private int length$value;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$c;
	private double logProbability$v;
	private double logProbability$v1;
	private double logProbability$v2;
	private double logProbability$v3;
	private double logProbability$var22;
	private double logProbability$var23;
	private double logProbability$var42;
	private double logProbability$var43;
	private double logProbability$var8;
	private double logProbability$var9;
	private int size;
	private boolean system$gibbsForward = true;
	private boolean[] v;
	private int v1;
	private int[] v2;
	private int[] v3;
	private boolean[] value;
	private double[] weightings;

	public DistributionTest2b$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for distribution$sample23.
	@Override
	public final double[][] get$distribution$sample23() {
		return distribution$sample23;
	}

	// Setter for distribution$sample23.
	@Override
	public final void set$distribution$sample23(double[][] cv$value) {
		// Set distribution$sample23
		distribution$sample23 = cv$value;
	}

	// Getter for distribution$sample5.
	@Override
	public final double[] get$distribution$sample5() {
		return distribution$sample5;
	}

	// Setter for distribution$sample5.
	@Override
	public final void set$distribution$sample5(double[] cv$value) {
		// Set distribution$sample5
		distribution$sample5 = cv$value;
	}

	// Getter for distribution$sample9.
	@Override
	public final double[] get$distribution$sample9() {
		return distribution$sample9;
	}

	// Setter for distribution$sample9.
	@Override
	public final void set$distribution$sample9(double[] cv$value) {
		// Set distribution$sample9
		distribution$sample9 = cv$value;
	}

	// Getter for fixedFlag$sample23.
	@Override
	public final boolean get$fixedFlag$sample23() {
		return fixedFlag$sample23;
	}

	// Setter for fixedFlag$sample23.
	@Override
	public final void set$fixedFlag$sample23(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample23 including if probabilities
		// need to be updated.
		fixedFlag$sample23 = cv$value;
		
		// Should the probability of sample 23 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample23 = (fixedFlag$sample23 && fixedProbFlag$sample23);
		
		// Should the probability of sample 43 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample43 = (fixedFlag$sample23 && fixedProbFlag$sample43);
	}

	// Getter for fixedFlag$sample36.
	@Override
	public final boolean get$fixedFlag$sample36() {
		return fixedFlag$sample36;
	}

	// Setter for fixedFlag$sample36.
	@Override
	public final void set$fixedFlag$sample36(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample36 including if probabilities
		// need to be updated.
		fixedFlag$sample36 = cv$value;
		
		// Should the probability of sample 36 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample36 = (fixedFlag$sample36 && fixedProbFlag$sample36);
		
		// Should the probability of sample 43 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample43 = (fixedFlag$sample36 && fixedProbFlag$sample43);
	}

	// Getter for fixedFlag$sample5.
	@Override
	public final boolean get$fixedFlag$sample5() {
		return fixedFlag$sample5;
	}

	// Setter for fixedFlag$sample5.
	@Override
	public final void set$fixedFlag$sample5(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample5 including if probabilities
		// need to be updated.
		fixedFlag$sample5 = cv$value;
		
		// Should the probability of sample 5 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample5 = (fixedFlag$sample5 && fixedProbFlag$sample5);
		
		// Should the probability of sample 43 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample43 = (fixedFlag$sample5 && fixedProbFlag$sample43);
	}

	// Getter for fixedFlag$sample9.
	@Override
	public final boolean get$fixedFlag$sample9() {
		return fixedFlag$sample9;
	}

	// Setter for fixedFlag$sample9.
	@Override
	public final void set$fixedFlag$sample9(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample9 including if probabilities
		// need to be updated.
		fixedFlag$sample9 = cv$value;
		
		// Should the probability of sample 9 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample9 = (fixedFlag$sample9 && fixedProbFlag$sample9);
		
		// Should the probability of sample 43 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample43 = (fixedFlag$sample9 && fixedProbFlag$sample43);
	}

	// Getter for length$value.
	@Override
	public final int get$length$value() {
		return length$value;
	}

	// Setter for length$value.
	@Override
	public final void set$length$value(int cv$value) {
		length$value = cv$value;
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

	// Getter for logProbability$c.
	@Override
	public final double get$logProbability$c() {
		return logProbability$c;
	}

	// Getter for logProbability$v.
	@Override
	public final double get$logProbability$v() {
		return logProbability$v;
	}

	// Getter for logProbability$v1.
	@Override
	public final double get$logProbability$v1() {
		return logProbability$v1;
	}

	// Getter for logProbability$v2.
	@Override
	public final double get$logProbability$v2() {
		return logProbability$v2;
	}

	// Getter for logProbability$v3.
	@Override
	public final double get$logProbability$v3() {
		return logProbability$v3;
	}

	// Getter for size.
	@Override
	public final int get$size() {
		return size;
	}

	// Getter for v.
	@Override
	public final boolean[] get$v() {
		return v;
	}

	// Getter for v1.
	@Override
	public final int get$v1() {
		return v1;
	}

	// Setter for v1.
	@Override
	public final void set$v1(int cv$value) {
		// Set flags for all the side effects of v1 including if probabilities need to be
		// updated.
		v1 = cv$value;
		
		// Unset the fixed probability flag for sample 5 as it depends on v1.
		fixedProbFlag$sample5 = false;
		
		// Unset the fixed probability flag for sample 43 as it depends on v1.
		fixedProbFlag$sample43 = false;
	}

	// Getter for v2.
	@Override
	public final int[] get$v2() {
		return v2;
	}

	// Setter for v2.
	@Override
	public final void set$v2(int[] cv$value) {
		// Set flags for all the side effects of v2 including if probabilities need to be
		// updated.
		// Set v2
		v2 = cv$value;
		
		// Unset the fixed probability flag for sample 9 as it depends on v2.
		fixedProbFlag$sample9 = false;
		
		// Unset the fixed probability flag for sample 23 as it depends on v2.
		fixedProbFlag$sample23 = false;
		
		// Unset the fixed probability flag for sample 43 as it depends on v2.
		fixedProbFlag$sample43 = false;
	}

	// Getter for v3.
	@Override
	public final int[] get$v3() {
		return v3;
	}

	// Setter for v3.
	@Override
	public final void set$v3(int[] cv$value) {
		// Set v3
		v3 = cv$value;
	}

	// Getter for value.
	@Override
	public final boolean[] get$value() {
		return value;
	}

	// Setter for value.
	@Override
	public final void set$value(boolean[] cv$value) {
		// Set value
		value = cv$value;
	}

	// Getter for weightings.
	@Override
	public final double[] get$weightings() {
		return weightings;
	}

	// Setter for weightings.
	@Override
	public final void set$weightings(double[] cv$value) {
		// Set weightings
		weightings = cv$value;
	}

	// Calculate the probability of the samples represented by sample23 using probability
	// distributions.
	private final void logProbabilityDistribution$sample23() {
		// Determine if we need to calculate the values for sample task 23 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample23) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample23) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// A guard to check if the sample value is ever reached.
				boolean cv$sampleReached = false;
				for(int i = 1; i < size; i += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Copy of index so that its values can be safely substituted
					int index$i$1 = i;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = v2[i];
						{
							{
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
					logProbability$var22 = cv$sampleAccumulator;
				
				// Only update the sample if it was reached, otherwise the NaN will be
				// erroneously over written.
				if(cv$sampleReached)
					// Store the random variable instance probability
					logProbability$var23 = cv$accumulator;
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(fixedFlag$sample23)
					// Update the variable probability
					logProbability$v2 = (logProbability$v2 + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample23)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample23 = fixedFlag$sample23;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i = 1; i < size; i += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var23;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(cv$sampleReached)
				logProbability$var22 = cv$rvAccumulator;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample23)
				// Update the variable probability
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample23)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample43 using probability
	// distributions.
	private final void logProbabilityDistribution$sample43() {
		// Determine if we need to calculate the values for sample task 43 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample43) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j = 0; j < size; j += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 43 including any distribution
				// values.
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = v[j];
					
					// Enumerating the possible arguments for Bernoulli 42.
					if(fixedFlag$sample5) {
						if(fixedFlag$sample9) {
							if((0 == j)) {
								{
									double var41 = ((1.0 * v1) / (v2[j] + v3[((j - 0) / 1)]));
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var41:(1.0 - var41))));
									
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
						} else {
							if(true) {
								// Enumerating the possible outputs of Categorical 8.
								for(int index$sample9$8 = 0; index$sample9$8 < weightings.length; index$sample9$8 += 1) {
									int distributionTempVariable$var9$10 = index$sample9$8;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample9Value9 = (1.0 * distribution$sample9[index$sample9$8]);
									if((0 == j)) {
										{
											double var41 = ((1.0 * v1) / (v2[j] + v3[((j - 0) / 1)]));
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(cv$probabilitySample9Value9) + Math.log((cv$sampleValue?var41:(1.0 - var41))));
											
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
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample9Value9);
										}
									}
								}
							}
						}
					} else {
						if(true) {
							// Enumerating the possible outputs of Categorical 4.
							for(int index$sample5$3 = 0; index$sample5$3 < weightings.length; index$sample5$3 += 1) {
								int distributionTempVariable$v1$5 = index$sample5$3;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample5Value4 = (1.0 * distribution$sample5[index$sample5$3]);
								if(fixedFlag$sample9) {
									if((0 == j)) {
										{
											double var41 = ((1.0 * distributionTempVariable$v1$5) / (v2[j] + v3[((j - 0) / 1)]));
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(cv$probabilitySample5Value4) + Math.log((cv$sampleValue?var41:(1.0 - var41))));
											
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
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value4);
										}
									}
								} else {
									if(true) {
										// Enumerating the possible outputs of Categorical 8.
										for(int index$sample9$13 = 0; index$sample9$13 < weightings.length; index$sample9$13 += 1) {
											int distributionTempVariable$var9$15 = index$sample9$13;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample9Value14 = (cv$probabilitySample5Value4 * distribution$sample9[index$sample9$13]);
											if((0 == j)) {
												{
													double var41 = ((1.0 * distributionTempVariable$v1$5) / (v2[j] + v3[((j - 0) / 1)]));
													
													// Store the value of the function call, so the function call is only made once.
													double cv$weightedProbability = (Math.log(cv$probabilitySample9Value14) + Math.log((cv$sampleValue?var41:(1.0 - var41))));
													
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
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample9Value14);
												}
											}
										}
									}
								}
							}
						}
					}
					
					// Enumerating the possible arguments for Bernoulli 42.
					if(fixedFlag$sample5) {
						if(fixedFlag$sample23) {
							for(int i = 1; i < size; i += 1) {
								if((i == j)) {
									{
										double var41 = ((1.0 * v1) / (v2[j] + v3[((j - 0) / 1)]));
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var41:(1.0 - var41))));
										
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
						} else {
							for(int i = 1; i < size; i += 1) {
								if(true) {
									// Enumerating the possible outputs of Categorical 22.
									for(int index$sample23$28 = 0; index$sample23$28 < weightings.length; index$sample23$28 += 1) {
										int distributionTempVariable$var23$30 = index$sample23$28;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample23Value29 = (1.0 * distribution$sample23[((i - 1) / 1)][index$sample23$28]);
										if((i == j)) {
											{
												double var41 = ((1.0 * v1) / (v2[j] + v3[((j - 0) / 1)]));
												
												// Store the value of the function call, so the function call is only made once.
												double cv$weightedProbability = (Math.log(cv$probabilitySample23Value29) + Math.log((cv$sampleValue?var41:(1.0 - var41))));
												
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
												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample23Value29);
											}
										}
									}
								}
							}
						}
					} else {
						if(true) {
							// Enumerating the possible outputs of Categorical 4.
							for(int index$sample5$22 = 0; index$sample5$22 < weightings.length; index$sample5$22 += 1) {
								int distributionTempVariable$v1$24 = index$sample5$22;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample5Value23 = (1.0 * distribution$sample5[index$sample5$22]);
								if(fixedFlag$sample23) {
									for(int i = 1; i < size; i += 1) {
										if((i == j)) {
											{
												double var41 = ((1.0 * distributionTempVariable$v1$24) / (v2[j] + v3[((j - 0) / 1)]));
												
												// Store the value of the function call, so the function call is only made once.
												double cv$weightedProbability = (Math.log(cv$probabilitySample5Value23) + Math.log((cv$sampleValue?var41:(1.0 - var41))));
												
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
												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value23);
											}
										}
									}
								} else {
									for(int i = 1; i < size; i += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 22.
											for(int index$sample23$34 = 0; index$sample23$34 < weightings.length; index$sample23$34 += 1) {
												int distributionTempVariable$var23$36 = index$sample23$34;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample23Value35 = (cv$probabilitySample5Value23 * distribution$sample23[((i - 1) / 1)][index$sample23$34]);
												if((i == j)) {
													{
														double var41 = ((1.0 * distributionTempVariable$v1$24) / (v2[j] + v3[((j - 0) / 1)]));
														
														// Store the value of the function call, so the function call is only made once.
														double cv$weightedProbability = (Math.log(cv$probabilitySample23Value35) + Math.log((cv$sampleValue?var41:(1.0 - var41))));
														
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
														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample23Value35);
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
				logProbability$var42 = cv$sampleAccumulator;
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var43 = cv$accumulator;
			
			// Update the variable probability
			logProbability$v = (logProbability$v + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample43 = (((fixedFlag$sample5 && fixedFlag$sample9) && fixedFlag$sample23) && fixedFlag$sample36);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j = 0; j < size; j += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var43;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(cv$sampleReached)
				logProbability$var42 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$v = (logProbability$v + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample5 using probability
	// distributions.
	private final void logProbabilityDistribution$sample5() {
		// Determine if we need to calculate the values for sample task 5 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample5) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample5) {
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
					int cv$sampleValue = v1;
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
				logProbability$c = (logProbability$c + cv$sampleAccumulator);
				
				// Store the sample task probability
				logProbability$v1 = cv$sampleProbability;
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample5)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample5 = fixedFlag$sample5;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$v1;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$c = (logProbability$c + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample9 using probability
	// distributions.
	private final void logProbabilityDistribution$sample9() {
		// Determine if we need to calculate the values for sample task 9 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample9) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample9) {
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
					int cv$sampleValue = v2[0];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
				logProbability$var8 = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$var9 = cv$sampleProbability;
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(fixedFlag$sample9)
					// Update the variable probability
					logProbability$v2 = (logProbability$v2 + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample9)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample9 = fixedFlag$sample9;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var9;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var8 = cv$rvAccumulator;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample9)
				// Update the variable probability
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample23 using sampled
	// values.
	private final void logProbabilityValue$sample23() {
		// Determine if we need to calculate the values for sample task 23 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample23) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i = 1; i < size; i += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Copy of index so that its values can be safely substituted
				int index$i$1 = i;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = v2[i];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
				logProbability$var22 = cv$sampleAccumulator;
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var23 = cv$accumulator;
			
			// Update the variable probability
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample23)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample23 = fixedFlag$sample23;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int i = 1; i < size; i += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var23;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(cv$sampleReached)
				logProbability$var22 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample23)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample36 using sampled
	// values.
	private final void logProbabilityValue$sample36() {
		// Determine if we need to calculate the values for sample task 36 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample36) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j = 0; j < size; j += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = v3[((j - 0) / 1)];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			logProbability$c = (logProbability$c + cv$sampleAccumulator);
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$v3 = cv$sampleAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample36 = fixedFlag$sample36;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j = 0; j < size; j += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$v3;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$c = (logProbability$c + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample43 using sampled
	// values.
	private final void logProbabilityValue$sample43() {
		// Determine if we need to calculate the values for sample task 43 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample43) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j = 0; j < size; j += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = v[j];
					{
						{
							double var41 = ((1.0 * v1) / (v2[j] + v3[((j - 0) / 1)]));
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var41:(1.0 - var41))));
							
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
				logProbability$var42 = cv$sampleAccumulator;
			
			// Only update the sample if it was reached, otherwise the NaN will be
			// erroneously over written.
			if(cv$sampleReached)
				// Store the random variable instance probability
				logProbability$var43 = cv$accumulator;
			
			// Update the variable probability
			logProbability$v = (logProbability$v + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample43 = (((fixedFlag$sample5 && fixedFlag$sample9) && fixedFlag$sample23) && fixedFlag$sample36);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			for(int j = 0; j < size; j += 1)
				// Record that the sample was reached.
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var43;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(cv$sampleReached)
				logProbability$var42 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$v = (logProbability$v + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample5 using sampled values.
	private final void logProbabilityValue$sample5() {
		// Determine if we need to calculate the values for sample task 5 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample5) {
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
				int cv$sampleValue = v1;
				{
					{
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
						
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
			logProbability$c = (logProbability$c + cv$sampleAccumulator);
			
			// Store the sample task probability
			logProbability$v1 = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample5 = fixedFlag$sample5;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$v1;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$c = (logProbability$c + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample9 using sampled values.
	private final void logProbabilityValue$sample9() {
		// Determine if we need to calculate the values for sample task 9 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample9) {
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
				int cv$sampleValue = v2[0];
				{
					{
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
						
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
			logProbability$var8 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$var9 = cv$sampleProbability;
			
			// Update the variable probability
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample9 = fixedFlag$sample9;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var9;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var8 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 23 drawn from Categorical 22. Inference was performed using variable
	// marginalization.
	private final void sample23(int i, int threadID$cv$i, Rng RNG$) {
		if(true) {
			// Calculate the number of states to evaluate.
			int cv$numNumStates = 0;
			
			// Copy of index so that its values can be safely substituted
			int index$i$1 = i;
			{
				// variable marginalization
				cv$numNumStates = Math.max(cv$numNumStates, weightings.length);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var23$stateProbabilityGlobal[threadID$cv$i];
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				// Copy of index so that its values can be safely substituted
				int index$i$2 = i;
				
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
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] cv$temp$0$weightings;
					{
						cv$temp$0$weightings = weightings;
					}
					int cv$temp$1$$var558;
					{
						cv$temp$1$$var558 = weightings.length;
					}
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var558))?Math.log(cv$temp$0$weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 42.
					{
						// Looking for a path between Sample 23 and consumer Bernoulli 42.
						{
							int traceTempVariable$var39$3_1 = cv$currentValue;
							for(int j = 0; j < size; j += 1) {
								if((i == j)) {
									// Processing sample task 43 of consumer random variable null.
									{
										// Set an accumulator to sum the probabilities for each possible configuration of
										// inputs.
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											// Enumerating the possible arguments for the variable Bernoulli 42 which is consuming
											// the output of Sample task 23.
											if(fixedFlag$sample5) {
												{
													{
														double cv$temp$2$var41;
														{
															// Constructing a random variable input for use later.
															double var41 = ((1.0 * v1) / (traceTempVariable$var39$3_1 + v3[((j - 0) / 1)]));
															cv$temp$2$var41 = var41;
														}
														
														// Record the probability of sample task 43 generating output with current configuration.
														if(((Math.log(1.0) + Math.log((v[j]?cv$temp$2$var41:(1.0 - cv$temp$2$var41)))) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$2$var41:(1.0 - cv$temp$2$var41)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															// If the second value is -infinity.
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$2$var41:(1.0 - cv$temp$2$var41))));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$2$var41:(1.0 - cv$temp$2$var41)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$2$var41:(1.0 - cv$temp$2$var41)))));
														}
														
														// Recorded the probability of reaching sample task 43 with the current configuration.
														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
													}
												}
											} else {
												if(true) {
													// Enumerating the possible outputs of Categorical 4.
													for(int index$sample5$6 = 0; index$sample5$6 < weightings.length; index$sample5$6 += 1) {
														int distributionTempVariable$v1$8 = index$sample5$6;
														
														// Update the probability of sampling this value from the distribution value.
														double cv$probabilitySample5Value7 = (1.0 * distribution$sample5[index$sample5$6]);
														{
															{
																double cv$temp$3$var41;
																{
																	// Constructing a random variable input for use later.
																	double var41 = ((1.0 * distributionTempVariable$v1$8) / (traceTempVariable$var39$3_1 + v3[((j - 0) / 1)]));
																	cv$temp$3$var41 = var41;
																}
																
																// Record the probability of sample task 43 generating output with current configuration.
																if(((Math.log(cv$probabilitySample5Value7) + Math.log((v[j]?cv$temp$3$var41:(1.0 - cv$temp$3$var41)))) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value7) + Math.log((v[j]?cv$temp$3$var41:(1.0 - cv$temp$3$var41)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value7) + Math.log((v[j]?cv$temp$3$var41:(1.0 - cv$temp$3$var41))));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value7) + Math.log((v[j]?cv$temp$3$var41:(1.0 - cv$temp$3$var41)))))) + 1)) + (Math.log(cv$probabilitySample5Value7) + Math.log((v[j]?cv$temp$3$var41:(1.0 - cv$temp$3$var41)))));
																}
																
																// Recorded the probability of reaching sample task 43 with the current configuration.
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value7);
															}
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
			
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			double[] cv$localProbability = distribution$sample23[((i - 1) / 1)];
			
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
					cv$localProbability[cv$indexName] = (1.0 / cv$numNumStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 36 drawn from c. Inference was performed using variable marginalization.
	private final void sample36(int j) {
		if(true) {
			// Calculate the number of states to evaluate.
			int cv$numNumStates = 0;
			{
				// variable marginalization
				cv$numNumStates = Math.max(cv$numNumStates, weightings.length);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var36$stateProbabilityGlobal;
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
				v3[((j - 0) / 1)] = cv$currentValue;
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] cv$temp$0$weightings;
					{
						cv$temp$0$weightings = weightings;
					}
					int cv$temp$1$$var593;
					{
						cv$temp$1$$var593 = weightings.length;
					}
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var593))?Math.log(cv$temp$0$weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 42.
					{
						{
							// Processing sample task 43 of consumer random variable null.
							{
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								{
									// Enumerating the possible arguments for the variable Bernoulli 42 which is consuming
									// the output of Sample task 36.
									if(fixedFlag$sample5) {
										if(fixedFlag$sample9) {
											if((0 == j)) {
												{
													{
														double cv$temp$2$var41;
														{
															// Constructing a random variable input for use later.
															double var41 = ((1.0 * v1) / (v2[j] + cv$currentValue));
															cv$temp$2$var41 = var41;
														}
														
														// Record the probability of sample task 43 generating output with current configuration.
														if(((Math.log(1.0) + Math.log((v[j]?cv$temp$2$var41:(1.0 - cv$temp$2$var41)))) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$2$var41:(1.0 - cv$temp$2$var41)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															// If the second value is -infinity.
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$2$var41:(1.0 - cv$temp$2$var41))));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$2$var41:(1.0 - cv$temp$2$var41)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$2$var41:(1.0 - cv$temp$2$var41)))));
														}
														
														// Recorded the probability of reaching sample task 43 with the current configuration.
														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
													}
												}
											}
										} else {
											if(true) {
												// Enumerating the possible outputs of Categorical 8.
												for(int index$sample9$9 = 0; index$sample9$9 < weightings.length; index$sample9$9 += 1) {
													int distributionTempVariable$var9$11 = index$sample9$9;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample9Value10 = (1.0 * distribution$sample9[index$sample9$9]);
													if((0 == j)) {
														{
															{
																double cv$temp$3$var41;
																{
																	// Constructing a random variable input for use later.
																	double var41 = ((1.0 * v1) / (v2[j] + cv$currentValue));
																	cv$temp$3$var41 = var41;
																}
																
																// Record the probability of sample task 43 generating output with current configuration.
																if(((Math.log(cv$probabilitySample9Value10) + Math.log((v[j]?cv$temp$3$var41:(1.0 - cv$temp$3$var41)))) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample9Value10) + Math.log((v[j]?cv$temp$3$var41:(1.0 - cv$temp$3$var41)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample9Value10) + Math.log((v[j]?cv$temp$3$var41:(1.0 - cv$temp$3$var41))));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample9Value10) + Math.log((v[j]?cv$temp$3$var41:(1.0 - cv$temp$3$var41)))))) + 1)) + (Math.log(cv$probabilitySample9Value10) + Math.log((v[j]?cv$temp$3$var41:(1.0 - cv$temp$3$var41)))));
																}
																
																// Recorded the probability of reaching sample task 43 with the current configuration.
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample9Value10);
															}
														}
													}
												}
											}
										}
									} else {
										if(true) {
											// Enumerating the possible outputs of Categorical 4.
											for(int index$sample5$4 = 0; index$sample5$4 < weightings.length; index$sample5$4 += 1) {
												int distributionTempVariable$v1$6 = index$sample5$4;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample5Value5 = (1.0 * distribution$sample5[index$sample5$4]);
												if(fixedFlag$sample9) {
													if((0 == j)) {
														{
															{
																double cv$temp$4$var41;
																{
																	// Constructing a random variable input for use later.
																	double var41 = ((1.0 * distributionTempVariable$v1$6) / (v2[j] + cv$currentValue));
																	cv$temp$4$var41 = var41;
																}
																
																// Record the probability of sample task 43 generating output with current configuration.
																if(((Math.log(cv$probabilitySample5Value5) + Math.log((v[j]?cv$temp$4$var41:(1.0 - cv$temp$4$var41)))) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value5) + Math.log((v[j]?cv$temp$4$var41:(1.0 - cv$temp$4$var41)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value5) + Math.log((v[j]?cv$temp$4$var41:(1.0 - cv$temp$4$var41))));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value5) + Math.log((v[j]?cv$temp$4$var41:(1.0 - cv$temp$4$var41)))))) + 1)) + (Math.log(cv$probabilitySample5Value5) + Math.log((v[j]?cv$temp$4$var41:(1.0 - cv$temp$4$var41)))));
																}
																
																// Recorded the probability of reaching sample task 43 with the current configuration.
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value5);
															}
														}
													}
												} else {
													if(true) {
														// Enumerating the possible outputs of Categorical 8.
														for(int index$sample9$14 = 0; index$sample9$14 < weightings.length; index$sample9$14 += 1) {
															int distributionTempVariable$var9$16 = index$sample9$14;
															
															// Update the probability of sampling this value from the distribution value.
															double cv$probabilitySample9Value15 = (cv$probabilitySample5Value5 * distribution$sample9[index$sample9$14]);
															if((0 == j)) {
																{
																	{
																		double cv$temp$5$var41;
																		{
																			// Constructing a random variable input for use later.
																			double var41 = ((1.0 * distributionTempVariable$v1$6) / (v2[j] + cv$currentValue));
																			cv$temp$5$var41 = var41;
																		}
																		
																		// Record the probability of sample task 43 generating output with current configuration.
																		if(((Math.log(cv$probabilitySample9Value15) + Math.log((v[j]?cv$temp$5$var41:(1.0 - cv$temp$5$var41)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample9Value15) + Math.log((v[j]?cv$temp$5$var41:(1.0 - cv$temp$5$var41)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample9Value15) + Math.log((v[j]?cv$temp$5$var41:(1.0 - cv$temp$5$var41))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample9Value15) + Math.log((v[j]?cv$temp$5$var41:(1.0 - cv$temp$5$var41)))))) + 1)) + (Math.log(cv$probabilitySample9Value15) + Math.log((v[j]?cv$temp$5$var41:(1.0 - cv$temp$5$var41)))));
																		}
																		
																		// Recorded the probability of reaching sample task 43 with the current configuration.
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample9Value15);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
									
									// Enumerating the possible arguments for the variable Bernoulli 42 which is consuming
									// the output of Sample task 36.
									if(fixedFlag$sample5) {
										if(fixedFlag$sample23) {
											for(int i = 1; i < size; i += 1) {
												if((i == j)) {
													{
														{
															double cv$temp$6$var41;
															{
																// Constructing a random variable input for use later.
																double var41 = ((1.0 * v1) / (v2[j] + cv$currentValue));
																cv$temp$6$var41 = var41;
															}
															
															// Record the probability of sample task 43 generating output with current configuration.
															if(((Math.log(1.0) + Math.log((v[j]?cv$temp$6$var41:(1.0 - cv$temp$6$var41)))) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$6$var41:(1.0 - cv$temp$6$var41)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																// If the second value is -infinity.
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$6$var41:(1.0 - cv$temp$6$var41))));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$6$var41:(1.0 - cv$temp$6$var41)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$6$var41:(1.0 - cv$temp$6$var41)))));
															}
															
															// Recorded the probability of reaching sample task 43 with the current configuration.
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
														}
													}
												}
											}
										} else {
											for(int i = 1; i < size; i += 1) {
												if(true) {
													// Enumerating the possible outputs of Categorical 22.
													for(int index$sample23$25 = 0; index$sample23$25 < weightings.length; index$sample23$25 += 1) {
														int distributionTempVariable$var23$27 = index$sample23$25;
														
														// Update the probability of sampling this value from the distribution value.
														double cv$probabilitySample23Value26 = (1.0 * distribution$sample23[((i - 1) / 1)][index$sample23$25]);
														if((i == j)) {
															{
																{
																	double cv$temp$7$var41;
																	{
																		// Constructing a random variable input for use later.
																		double var41 = ((1.0 * v1) / (v2[j] + cv$currentValue));
																		cv$temp$7$var41 = var41;
																	}
																	
																	// Record the probability of sample task 43 generating output with current configuration.
																	if(((Math.log(cv$probabilitySample23Value26) + Math.log((v[j]?cv$temp$7$var41:(1.0 - cv$temp$7$var41)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample23Value26) + Math.log((v[j]?cv$temp$7$var41:(1.0 - cv$temp$7$var41)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample23Value26) + Math.log((v[j]?cv$temp$7$var41:(1.0 - cv$temp$7$var41))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample23Value26) + Math.log((v[j]?cv$temp$7$var41:(1.0 - cv$temp$7$var41)))))) + 1)) + (Math.log(cv$probabilitySample23Value26) + Math.log((v[j]?cv$temp$7$var41:(1.0 - cv$temp$7$var41)))));
																	}
																	
																	// Recorded the probability of reaching sample task 43 with the current configuration.
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample23Value26);
																}
															}
														}
													}
												}
											}
										}
									} else {
										if(true) {
											// Enumerating the possible outputs of Categorical 4.
											for(int index$sample5$19 = 0; index$sample5$19 < weightings.length; index$sample5$19 += 1) {
												int distributionTempVariable$v1$21 = index$sample5$19;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample5Value20 = (1.0 * distribution$sample5[index$sample5$19]);
												if(fixedFlag$sample23) {
													for(int i = 1; i < size; i += 1) {
														if((i == j)) {
															{
																{
																	double cv$temp$8$var41;
																	{
																		// Constructing a random variable input for use later.
																		double var41 = ((1.0 * distributionTempVariable$v1$21) / (v2[j] + cv$currentValue));
																		cv$temp$8$var41 = var41;
																	}
																	
																	// Record the probability of sample task 43 generating output with current configuration.
																	if(((Math.log(cv$probabilitySample5Value20) + Math.log((v[j]?cv$temp$8$var41:(1.0 - cv$temp$8$var41)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value20) + Math.log((v[j]?cv$temp$8$var41:(1.0 - cv$temp$8$var41)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value20) + Math.log((v[j]?cv$temp$8$var41:(1.0 - cv$temp$8$var41))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value20) + Math.log((v[j]?cv$temp$8$var41:(1.0 - cv$temp$8$var41)))))) + 1)) + (Math.log(cv$probabilitySample5Value20) + Math.log((v[j]?cv$temp$8$var41:(1.0 - cv$temp$8$var41)))));
																	}
																	
																	// Recorded the probability of reaching sample task 43 with the current configuration.
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value20);
																}
															}
														}
													}
												} else {
													for(int i = 1; i < size; i += 1) {
														if(true) {
															// Enumerating the possible outputs of Categorical 22.
															for(int index$sample23$31 = 0; index$sample23$31 < weightings.length; index$sample23$31 += 1) {
																int distributionTempVariable$var23$33 = index$sample23$31;
																
																// Update the probability of sampling this value from the distribution value.
																double cv$probabilitySample23Value32 = (cv$probabilitySample5Value20 * distribution$sample23[((i - 1) / 1)][index$sample23$31]);
																if((i == j)) {
																	{
																		{
																			double cv$temp$9$var41;
																			{
																				// Constructing a random variable input for use later.
																				double var41 = ((1.0 * distributionTempVariable$v1$21) / (v2[j] + cv$currentValue));
																				cv$temp$9$var41 = var41;
																			}
																			
																			// Record the probability of sample task 43 generating output with current configuration.
																			if(((Math.log(cv$probabilitySample23Value32) + Math.log((v[j]?cv$temp$9$var41:(1.0 - cv$temp$9$var41)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample23Value32) + Math.log((v[j]?cv$temp$9$var41:(1.0 - cv$temp$9$var41)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample23Value32) + Math.log((v[j]?cv$temp$9$var41:(1.0 - cv$temp$9$var41))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample23Value32) + Math.log((v[j]?cv$temp$9$var41:(1.0 - cv$temp$9$var41)))))) + 1)) + (Math.log(cv$probabilitySample23Value32) + Math.log((v[j]?cv$temp$9$var41:(1.0 - cv$temp$9$var41)))));
																			}
																			
																			// Recorded the probability of reaching sample task 43 with the current configuration.
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample23Value32);
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
			v3[((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 5 drawn from c. Inference was performed using variable marginalization.
	private final void sample5() {
		if(true) {
			// Calculate the number of states to evaluate.
			int cv$numNumStates = 0;
			{
				// variable marginalization
				cv$numNumStates = Math.max(cv$numNumStates, weightings.length);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var5$stateProbabilityGlobal;
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
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] cv$temp$0$weightings;
					{
						cv$temp$0$weightings = weightings;
					}
					int cv$temp$1$$var481;
					{
						cv$temp$1$$var481 = weightings.length;
					}
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var481))?Math.log(cv$temp$0$weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 42.
					{
						{
							for(int j = 0; j < size; j += 1) {
								int traceTempVariable$v1$1_2 = cv$currentValue;
								
								// Processing sample task 43 of consumer random variable null.
								{
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										// Enumerating the possible arguments for the variable Bernoulli 42 which is consuming
										// the output of Sample task 5.
										if(fixedFlag$sample9) {
											if((0 == j)) {
												{
													{
														double cv$temp$2$var41;
														{
															// Constructing a random variable input for use later.
															double var41 = ((1.0 * traceTempVariable$v1$1_2) / (v2[j] + v3[((j - 0) / 1)]));
															cv$temp$2$var41 = var41;
														}
														
														// Record the probability of sample task 43 generating output with current configuration.
														if(((Math.log(1.0) + Math.log((v[j]?cv$temp$2$var41:(1.0 - cv$temp$2$var41)))) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$2$var41:(1.0 - cv$temp$2$var41)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															// If the second value is -infinity.
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$2$var41:(1.0 - cv$temp$2$var41))));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$2$var41:(1.0 - cv$temp$2$var41)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$2$var41:(1.0 - cv$temp$2$var41)))));
														}
														
														// Recorded the probability of reaching sample task 43 with the current configuration.
														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
													}
												}
											}
										} else {
											if(true) {
												// Enumerating the possible outputs of Categorical 8.
												for(int index$sample9$4 = 0; index$sample9$4 < weightings.length; index$sample9$4 += 1) {
													int distributionTempVariable$var9$6 = index$sample9$4;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample9Value5 = (1.0 * distribution$sample9[index$sample9$4]);
													if((0 == j)) {
														{
															{
																double cv$temp$3$var41;
																{
																	// Constructing a random variable input for use later.
																	double var41 = ((1.0 * traceTempVariable$v1$1_2) / (v2[j] + v3[((j - 0) / 1)]));
																	cv$temp$3$var41 = var41;
																}
																
																// Record the probability of sample task 43 generating output with current configuration.
																if(((Math.log(cv$probabilitySample9Value5) + Math.log((v[j]?cv$temp$3$var41:(1.0 - cv$temp$3$var41)))) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample9Value5) + Math.log((v[j]?cv$temp$3$var41:(1.0 - cv$temp$3$var41)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample9Value5) + Math.log((v[j]?cv$temp$3$var41:(1.0 - cv$temp$3$var41))));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample9Value5) + Math.log((v[j]?cv$temp$3$var41:(1.0 - cv$temp$3$var41)))))) + 1)) + (Math.log(cv$probabilitySample9Value5) + Math.log((v[j]?cv$temp$3$var41:(1.0 - cv$temp$3$var41)))));
																}
																
																// Recorded the probability of reaching sample task 43 with the current configuration.
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample9Value5);
															}
														}
													}
												}
											}
										}
										
										// Enumerating the possible arguments for the variable Bernoulli 42 which is consuming
										// the output of Sample task 5.
										if(fixedFlag$sample23) {
											for(int i = 1; i < size; i += 1) {
												if((i == j)) {
													{
														{
															double cv$temp$4$var41;
															{
																// Constructing a random variable input for use later.
																double var41 = ((1.0 * traceTempVariable$v1$1_2) / (v2[j] + v3[((j - 0) / 1)]));
																cv$temp$4$var41 = var41;
															}
															
															// Record the probability of sample task 43 generating output with current configuration.
															if(((Math.log(1.0) + Math.log((v[j]?cv$temp$4$var41:(1.0 - cv$temp$4$var41)))) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$4$var41:(1.0 - cv$temp$4$var41)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																// If the second value is -infinity.
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$4$var41:(1.0 - cv$temp$4$var41))));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$4$var41:(1.0 - cv$temp$4$var41)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$4$var41:(1.0 - cv$temp$4$var41)))));
															}
															
															// Recorded the probability of reaching sample task 43 with the current configuration.
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
														}
													}
												}
											}
										} else {
											for(int i = 1; i < size; i += 1) {
												if(true) {
													// Enumerating the possible outputs of Categorical 22.
													for(int index$sample23$12 = 0; index$sample23$12 < weightings.length; index$sample23$12 += 1) {
														int distributionTempVariable$var23$14 = index$sample23$12;
														
														// Update the probability of sampling this value from the distribution value.
														double cv$probabilitySample23Value13 = (1.0 * distribution$sample23[((i - 1) / 1)][index$sample23$12]);
														if((i == j)) {
															{
																{
																	double cv$temp$5$var41;
																	{
																		// Constructing a random variable input for use later.
																		double var41 = ((1.0 * traceTempVariable$v1$1_2) / (v2[j] + v3[((j - 0) / 1)]));
																		cv$temp$5$var41 = var41;
																	}
																	
																	// Record the probability of sample task 43 generating output with current configuration.
																	if(((Math.log(cv$probabilitySample23Value13) + Math.log((v[j]?cv$temp$5$var41:(1.0 - cv$temp$5$var41)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample23Value13) + Math.log((v[j]?cv$temp$5$var41:(1.0 - cv$temp$5$var41)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample23Value13) + Math.log((v[j]?cv$temp$5$var41:(1.0 - cv$temp$5$var41))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample23Value13) + Math.log((v[j]?cv$temp$5$var41:(1.0 - cv$temp$5$var41)))))) + 1)) + (Math.log(cv$probabilitySample23Value13) + Math.log((v[j]?cv$temp$5$var41:(1.0 - cv$temp$5$var41)))));
																	}
																	
																	// Recorded the probability of reaching sample task 43 with the current configuration.
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample23Value13);
																}
															}
														}
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
			
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			double[] cv$localProbability = distribution$sample5;
			
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
					cv$localProbability[cv$indexName] = (1.0 / cv$numNumStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 9 drawn from Categorical 8. Inference was performed using variable
	// marginalization.
	private final void sample9() {
		if(true) {
			// Calculate the number of states to evaluate.
			int cv$numNumStates = 0;
			{
				// variable marginalization
				cv$numNumStates = Math.max(cv$numNumStates, weightings.length);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var9$stateProbabilityGlobal;
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
				{
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] cv$temp$0$weightings;
					{
						cv$temp$0$weightings = weightings;
					}
					int cv$temp$1$$var525;
					{
						cv$temp$1$$var525 = weightings.length;
					}
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var525))?Math.log(cv$temp$0$weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 42.
					{
						// Looking for a path between Sample 9 and consumer Bernoulli 42.
						{
							int traceTempVariable$var39$1_1 = cv$currentValue;
							for(int j = 0; j < size; j += 1) {
								if((0 == j)) {
									// Processing sample task 43 of consumer random variable null.
									{
										// Set an accumulator to sum the probabilities for each possible configuration of
										// inputs.
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											// Enumerating the possible arguments for the variable Bernoulli 42 which is consuming
											// the output of Sample task 9.
											if(fixedFlag$sample5) {
												{
													{
														double cv$temp$2$var41;
														{
															// Constructing a random variable input for use later.
															double var41 = ((1.0 * v1) / (traceTempVariable$var39$1_1 + v3[((j - 0) / 1)]));
															cv$temp$2$var41 = var41;
														}
														
														// Record the probability of sample task 43 generating output with current configuration.
														if(((Math.log(1.0) + Math.log((v[j]?cv$temp$2$var41:(1.0 - cv$temp$2$var41)))) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$2$var41:(1.0 - cv$temp$2$var41)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															// If the second value is -infinity.
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$2$var41:(1.0 - cv$temp$2$var41))));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$2$var41:(1.0 - cv$temp$2$var41)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$2$var41:(1.0 - cv$temp$2$var41)))));
														}
														
														// Recorded the probability of reaching sample task 43 with the current configuration.
														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
													}
												}
											} else {
												if(true) {
													// Enumerating the possible outputs of Categorical 4.
													for(int index$sample5$4 = 0; index$sample5$4 < weightings.length; index$sample5$4 += 1) {
														int distributionTempVariable$v1$6 = index$sample5$4;
														
														// Update the probability of sampling this value from the distribution value.
														double cv$probabilitySample5Value5 = (1.0 * distribution$sample5[index$sample5$4]);
														{
															{
																double cv$temp$3$var41;
																{
																	// Constructing a random variable input for use later.
																	double var41 = ((1.0 * distributionTempVariable$v1$6) / (traceTempVariable$var39$1_1 + v3[((j - 0) / 1)]));
																	cv$temp$3$var41 = var41;
																}
																
																// Record the probability of sample task 43 generating output with current configuration.
																if(((Math.log(cv$probabilitySample5Value5) + Math.log((v[j]?cv$temp$3$var41:(1.0 - cv$temp$3$var41)))) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value5) + Math.log((v[j]?cv$temp$3$var41:(1.0 - cv$temp$3$var41)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value5) + Math.log((v[j]?cv$temp$3$var41:(1.0 - cv$temp$3$var41))));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value5) + Math.log((v[j]?cv$temp$3$var41:(1.0 - cv$temp$3$var41)))))) + 1)) + (Math.log(cv$probabilitySample5Value5) + Math.log((v[j]?cv$temp$3$var41:(1.0 - cv$temp$3$var41)))));
																}
																
																// Recorded the probability of reaching sample task 43 with the current configuration.
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value5);
															}
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
			
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			double[] cv$localProbability = distribution$sample9;
			
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
					cv$localProbability[cv$indexName] = (1.0 / cv$numNumStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var5$stateProbabilityGlobal
		{
			// Allocation of cv$var5$stateProbabilityGlobal for single threaded execution
			cv$var5$stateProbabilityGlobal = new double[weightings.length];
		}
		
		// Constructor for cv$var9$stateProbabilityGlobal
		{
			// Allocation of cv$var9$stateProbabilityGlobal for single threaded execution
			cv$var9$stateProbabilityGlobal = new double[weightings.length];
		}
		
		// Constructor for cv$var23$stateProbabilityGlobal
		{
			// Allocation of cv$var23$stateProbabilityGlobal for multithreaded execution
			{
				// Get the thread count.
				int cv$threadCount = threadCount();
				
				// Allocate an array to hold a copy per thread
				cv$var23$stateProbabilityGlobal = new double[cv$threadCount][];
				
				// Populate the array with a copy per thread
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var23$stateProbabilityGlobal[cv$index] = new double[weightings.length];
			}
		}
		
		// Constructor for cv$var36$stateProbabilityGlobal
		{
			// Allocation of cv$var36$stateProbabilityGlobal for single threaded execution
			cv$var36$stateProbabilityGlobal = new double[weightings.length];
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If v2 has not been set already allocate space.
		if((!fixedFlag$sample9 || !fixedFlag$sample23)) {
			// Constructor for v2
			{
				v2 = new int[length$value];
			}
		}
		
		// Constructor for v
		{
			v = new boolean[length$value];
		}
		
		// If v3 has not been set already allocate space.
		if(!fixedFlag$sample36) {
			// Constructor for v3
			{
				v3 = new int[((((length$value - 1) - 0) / 1) + 1)];
			}
		}
		
		// Constructor for distribution$sample5
		{
			distribution$sample5 = new double[weightings.length];
		}
		
		// Constructor for distribution$sample23
		{
			distribution$sample23 = new double[((((length$value - 1) - 1) / 1) + 1)][];
			for(int i = 1; i < length$value; i += 1)
				distribution$sample23[((i - 1) / 1)] = new double[weightings.length];
		}
		
		// Constructor for distribution$sample9
		{
			distribution$sample9 = new double[weightings.length];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample9)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 1, size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample23)
							v2[i] = DistributionSampling.sampleCategorical(RNG$1, weightings, weightings.length);
					}
			}
		);
		for(int j = 0; j < size; j += 1) {
			if(!fixedFlag$sample36)
				v3[((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
			v[j] = DistributionSampling.sampleBernoulli(RNG$, ((1.0 * v1) / (v2[j] + v3[((j - 0) / 1)])));
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Create local copy of variable probabilities.
		double[] cv$distribution$sample5 = distribution$sample5;
		for(int index$c = 0; index$c < weightings.length; index$c += 1) {
			// Probability for this value
			double cv$value = (((0.0 <= index$c) && (index$c < weightings.length))?weightings[index$c]:0.0);
			if(!(fixedFlag$sample5 && fixedFlag$sample36))
				// Save the probability of each value
				cv$distribution$sample5[index$c] = cv$value;
		}
		
		// Create local copy of variable probabilities.
		double[] cv$distribution$sample9 = distribution$sample9;
		for(int index$var8 = 0; index$var8 < weightings.length; index$var8 += 1) {
			// Probability for this value
			double cv$value = (((0.0 <= index$var8) && (index$var8 < weightings.length))?weightings[index$var8]:0.0);
			if(!fixedFlag$sample9)
				// Save the probability of each value
				cv$distribution$sample9[index$var8] = cv$value;
		}
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 1, size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i = forStart$i; i < forEnd$i; i += 1) {
						// Create local copy of variable probabilities.
						double[] cv$distribution$sample23 = distribution$sample23[((i - 1) / 1)];
						for(int index$var22 = 0; index$var22 < weightings.length; index$var22 += 1) {
							// Probability for this value
							double cv$value = (((0.0 <= index$var22) && (index$var22 < weightings.length))?weightings[index$var22]:0.0);
							if(!fixedFlag$sample23)
								// Save the probability of each value
								cv$distribution$sample23[index$var22] = cv$value;
						}
					}
			}
		);
		for(int j = 0; j < size; j += 1) {
			if(!fixedFlag$sample36)
				v3[((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample9)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 1, size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample23)
							v2[i] = DistributionSampling.sampleCategorical(RNG$1, weightings, weightings.length);
					}
			}
		);
		for(int j = 0; j < size; j += 1) {
			if(!fixedFlag$sample36)
				v3[((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample5)
				sample5();
			if(!fixedFlag$sample9)
				sample9();
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1) {
							if(!fixedFlag$sample23)
								sample23(i, threadID$i, RNG$1);
						}
				}
			);
			for(int j = 0; j < size; j += 1) {
				if(!fixedFlag$sample36)
					sample36(j);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int j = (size - ((((size - 1) - 0) % 1) + 1)); j >= ((0 - 1) + 1); j -= 1) {
				if(!fixedFlag$sample36)
					sample36(j);
			}
			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1) {
							if(!fixedFlag$sample23)
								sample23(i, threadID$i, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample9)
				sample9();
			if(!fixedFlag$sample5)
				sample5();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		size = length$value;
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
		logProbability$c = 0.0;
		if(!fixedProbFlag$sample5)
			logProbability$v1 = Double.NaN;
		logProbability$var8 = 0.0;
		logProbability$v2 = 0.0;
		if(!fixedProbFlag$sample9)
			logProbability$var9 = Double.NaN;
		logProbability$var22 = Double.NaN;
		if(!fixedProbFlag$sample23)
			logProbability$var23 = Double.NaN;
		if(!fixedProbFlag$sample36)
			logProbability$v3 = Double.NaN;
		logProbability$var42 = Double.NaN;
		logProbability$v = 0.0;
		if(!fixedProbFlag$sample43)
			logProbability$var43 = Double.NaN;
	}

	// Method to generate a new random state for the model excluding any fixed values
	// and then calculate its probability.
	@Override
	public final void logEvidenceGeneration() {
		// Generate values for all the samples in the model that were not fixed or observed.
		forwardGenerationValuesNoOutputs();
		
		// Calculate the probability for the resulting model.
		logEvidenceProbabilities();
	}

	// Construct the evidence probabilities.
	private final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(fixedFlag$sample36)
			logProbabilityValue$sample36();
		logProbabilityValue$sample43();
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
		logProbabilityDistribution$sample9();
		logProbabilityDistribution$sample23();
		logProbabilityValue$sample36();
		logProbabilityDistribution$sample43();
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
		logProbabilityValue$sample9();
		logProbabilityValue$sample23();
		logProbabilityValue$sample36();
		logProbabilityValue$sample43();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample9)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 1, size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample23)
							v2[i] = DistributionSampling.sampleCategorical(RNG$1, weightings, weightings.length);
					}
			}
		);
		for(int j = 0; j < size; j += 1) {
			if(!fixedFlag$sample36)
				v3[((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		}
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Deep copy between arrays
		boolean[] cv$source1 = value;
		boolean[] cv$target1 = v;
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
		     + "model DistributionTest2b(double[] weightings, boolean[] value) {\n"
		     + "    int size = value.length;\n"
		     + "    \n"
		     + "    Categorical c = new Categorical(weightings);\n"
		     + "    int v1 = c.sampleDistribution();\n"
		     + "    \n"
		     + "    int[] v2 = new int[size];\n"
		     + "    v2[0] = categorical(weightings).sampleDistribution();\n"
		     + "    for(int i:[1..size))\n"
		     + "        v2[i] = categorical(weightings).sampleDistribution();\n"
		     + "        \n"
		     + "    boolean[] v = new boolean[size];\n"
		     + "    for(int j:[0..size)) {\n"
		     + "        int v3 = c.sample();\n"
		     + "        v[j] = bernoulli((1.0*v1)/(v2[j] + v3)).sample();\n"
		     + "    }\n"
		     + "        \n"
		     + "    v.observe(value);\n"
		     + "}\n"
		     + "";
	}
}