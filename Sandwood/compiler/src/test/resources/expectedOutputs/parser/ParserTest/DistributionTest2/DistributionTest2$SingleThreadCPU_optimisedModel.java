package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DistributionTest2$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements DistributionTest2$CoreInterface {
	
	// Declare the variables for the model.
	private double[] cv$var23$stateProbabilityGlobal;
	private double[] cv$var5$stateProbabilityGlobal;
	private double[] cv$var9$stateProbabilityGlobal;
	private double[][] distribution$sample23;
	private double[] distribution$sample5;
	private double[] distribution$sample9;
	private boolean fixedFlag$sample23 = false;
	private boolean fixedFlag$sample5 = false;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample23 = false;
	private boolean fixedProbFlag$sample41 = false;
	private boolean fixedProbFlag$sample5 = false;
	private boolean fixedProbFlag$sample9 = false;
	private int length$value;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$sample23;
	private double[] logProbability$sample41;
	private double logProbability$v;
	private double logProbability$v1;
	private double logProbability$v2;
	private double[] logProbability$var22;
	private double logProbability$var4;
	private double[] logProbability$var40;
	private double logProbability$var8;
	private double logProbability$var9;
	private int size;
	private boolean system$gibbsForward = true;
	private boolean[] v;
	private int v1;
	private int[] v2;
	private boolean[] value;
	private double[] weightings;

	public DistributionTest2$SingleThreadCPU(ExecutionTarget target) {
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
		// 
		// Substituted "fixedFlag$sample23" with its value "cv$value".
		fixedProbFlag$sample23 = (cv$value && fixedProbFlag$sample23);
		
		// Should the probability of sample 41 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample23" with its value "cv$value".
		fixedProbFlag$sample41 = (cv$value && fixedProbFlag$sample41);
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
		// 
		// Substituted "fixedFlag$sample5" with its value "cv$value".
		fixedProbFlag$sample5 = (cv$value && fixedProbFlag$sample5);
		
		// Should the probability of sample 41 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample5" with its value "cv$value".
		fixedProbFlag$sample41 = (cv$value && fixedProbFlag$sample41);
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
		// 
		// Substituted "fixedFlag$sample9" with its value "cv$value".
		fixedProbFlag$sample9 = (cv$value && fixedProbFlag$sample9);
		
		// Should the probability of sample 41 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample9" with its value "cv$value".
		fixedProbFlag$sample41 = (cv$value && fixedProbFlag$sample41);
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
		
		// Unset the fixed probability flag for sample 41 as it depends on v1.
		fixedProbFlag$sample41 = false;
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
		
		// Unset the fixed probability flag for sample 41 as it depends on v2.
		fixedProbFlag$sample41 = false;
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
				for(int i = 1; i < size; i += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = v2[i];
					
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
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					// 
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					logProbability$var22[(i - 1)] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample23[(i - 1)] = cv$distributionAccumulator;
				}
				
				// Update the variable probability
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample23" with its value "true".
				fixedProbFlag$sample23 = true;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 1; i < size; i += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample23[(i - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var22[(i - 1)] = cv$rvAccumulator;
			}
			
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

	// Calculate the probability of the samples represented by sample41 using probability
	// distributions.
	private final void logProbabilityDistribution$sample41() {
		// Determine if we need to calculate the values for sample task 41 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample41) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 41 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				boolean cv$sampleValue = v[j];
				
				// Enumerating the possible arguments for Bernoulli 40.
				if((0 == j)) {
					// Enumerating the possible arguments for Bernoulli 40.
					if(fixedFlag$sample5) {
						if(fixedFlag$sample9) {
							// Substituted "j" with its value "0".
							double var39 = ((double)v1 / v2[0]);
							
							// Store the value of the function call, so the function call is only made once.
							cv$distributionAccumulator = Math.log((cv$sampleValue?var39:(1.0 - var39)));
							
							// Add the probability of this distribution configuration to the accumulator.
							// 
							// An accumulator for the distributed probability space covered.
							cv$probabilityReached = 1.0;
						} else {
							// Enumerating the possible outputs of Categorical 8.
							for(int index$sample9$8 = 0; index$sample9$8 < weightings.length; index$sample9$8 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample9Value9 = distribution$sample9[index$sample9$8];
								
								// Substituted "j" with its value "0".
								double var39 = ((double)v1 / v2[0]);
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample9Value9) + Math.log((cv$sampleValue?var39:(1.0 - var39))));
								
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
					} else {
						// Enumerating the possible outputs of Categorical 4.
						for(int index$sample5$3 = 0; index$sample5$3 < weightings.length; index$sample5$3 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample5Value4 = distribution$sample5[index$sample5$3];
							if(fixedFlag$sample9) {
								// Substituted "j" with its value "0".
								double var39 = ((double)index$sample5$3 / v2[0]);
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample5Value4) + Math.log((cv$sampleValue?var39:(1.0 - var39))));
								
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
							} else {
								// Enumerating the possible outputs of Categorical 8.
								for(int index$sample9$13 = 0; index$sample9$13 < weightings.length; index$sample9$13 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample9Value14 = (cv$probabilitySample5Value4 * distribution$sample9[index$sample9$13]);
									
									// Substituted "j" with its value "0".
									double var39 = ((double)index$sample5$3 / v2[0]);
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample9Value14) + Math.log((cv$sampleValue?var39:(1.0 - var39))));
									
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
				
				// Enumerating the possible arguments for Bernoulli 40.
				if((1 <= j)) {
					// Enumerating the possible arguments for Bernoulli 40.
					if(fixedFlag$sample5) {
						if(fixedFlag$sample23) {
							double var39 = ((double)v1 / v2[j]);
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = Math.log((cv$sampleValue?var39:(1.0 - var39)));
							
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
						} else {
							// Enumerating the possible outputs of Categorical 22.
							for(int index$sample23$24 = 0; index$sample23$24 < weightings.length; index$sample23$24 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "i" with its value "j".
								double cv$probabilitySample23Value25 = distribution$sample23[(j - 1)][index$sample23$24];
								double var39 = ((double)v1 / v2[j]);
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample23Value25) + Math.log((cv$sampleValue?var39:(1.0 - var39))));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample23Value25);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 4.
						for(int index$sample5$18 = 0; index$sample5$18 < weightings.length; index$sample5$18 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample5Value19 = distribution$sample5[index$sample5$18];
							if(fixedFlag$sample23) {
								double var39 = ((double)index$sample5$18 / v2[j]);
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample5Value19) + Math.log((cv$sampleValue?var39:(1.0 - var39))));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value19);
							} else {
								// Enumerating the possible outputs of Categorical 22.
								for(int index$sample23$30 = 0; index$sample23$30 < weightings.length; index$sample23$30 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "i" with its value "j".
									double cv$probabilitySample23Value31 = (cv$probabilitySample5Value19 * distribution$sample23[(j - 1)][index$sample23$30]);
									double var39 = ((double)index$sample5$18 / v2[j]);
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample23Value31) + Math.log((cv$sampleValue?var39:(1.0 - var39))));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample23Value31);
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
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$var40[j] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample41[j] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$v = (logProbability$v + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample41 = ((fixedFlag$sample5 && fixedFlag$sample9) && fixedFlag$sample23);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample41[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var40[j] = cv$rvAccumulator;
			}
			
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
				double cv$distributionAccumulator = (((0.0 <= v1) && (v1 < weightings.length))?Math.log(weightings[v1]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$var4 = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$v1 = cv$distributionAccumulator;
				
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
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				
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
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample5" with its value "true".
				fixedProbFlag$sample5 = true;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var4 = logProbability$v1;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$v1);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample5)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$v1);
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
				// The sample value to calculate the probability of generating
				int cv$sampleValue = v2[0];
				
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
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$var8 = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$var9 = cv$distributionAccumulator;
				
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
				logProbability$v2 = (logProbability$v2 + cv$distributionAccumulator);
				
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
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				
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
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample9" with its value "true".
				fixedProbFlag$sample9 = true;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var8 = logProbability$var9;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample9)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$v2 = (logProbability$v2 + logProbability$var9);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var9);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample9)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var9);
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
			for(int i = 1; i < size; i += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = v2[i];
				
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
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$var22[(i - 1)] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample23[(i - 1)] = cv$distributionAccumulator;
			}
			
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
			for(int i = 1; i < size; i += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample23[(i - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var22[(i - 1)] = cv$rvAccumulator;
			}
			
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

	// Calculate the probability of the samples represented by sample41 using sampled
	// values.
	private final void logProbabilityValue$sample41() {
		// Determine if we need to calculate the values for sample task 41 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample41) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double var39 = ((double)v1 / v2[j]);
				
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
				double cv$distributionAccumulator = Math.log((v[j]?var39:(1.0 - var39)));
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$var40[j] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample41[j] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$v = (logProbability$v + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample41 = ((fixedFlag$sample5 && fixedFlag$sample9) && fixedFlag$sample23);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample41[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var40[j] = cv$rvAccumulator;
			}
			
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
			double cv$distributionAccumulator = (((0.0 <= v1) && (v1 < weightings.length))?Math.log(weightings[v1]):Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var4 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$v1 = cv$distributionAccumulator;
			
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
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample5)
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
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample5 = fixedFlag$sample5;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var4 = logProbability$v1;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$v1);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample5)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$v1);
		}
	}

	// Calculate the probability of the samples represented by sample9 using sampled values.
	private final void logProbabilityValue$sample9() {
		// Determine if we need to calculate the values for sample task 9 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample9) {
			// Generating probabilities for sample task
			// The sample value to calculate the probability of generating
			int cv$sampleValue = v2[0];
			
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
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var8 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var9 = cv$distributionAccumulator;
			
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
			logProbability$v2 = (logProbability$v2 + cv$distributionAccumulator);
			
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
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample9)
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
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample9 = fixedFlag$sample9;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var8 = logProbability$var9;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$v2 = (logProbability$v2 + logProbability$var9);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var9);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample9)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var9);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 23 drawn from Categorical 22. Inference was performed using variable
	// marginalization.
	private final void sample23(int i) {
		// Variable declaration of cv$numNumStates moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		int cv$numNumStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$weightings" with its value "weightings".
			double cv$accumulatedProbabilities = Math.log(weightings[cv$valuePos]);
			
			// Processing sample task 41 of consumer random variable null.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
			
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			double cv$consumerDistributionProbabilityAccumulator = 1.0;
			
			// Enumerating the possible arguments for the variable Bernoulli 40 which is consuming
			// the output of Sample task 23.
			if(fixedFlag$sample5) {
				// Variable declaration of cv$temp$2$var39 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 40.
				// 
				// Looking for a path between Sample 23 and consumer Bernoulli 40.
				// 
				// Value of the variable at this index
				double cv$temp$2$var39 = ((double)v1 / cv$valuePos);
				
				// Substituted "j" with its value "i".
				cv$accumulatedConsumerProbabilities = Math.log((v[i]?cv$temp$2$var39:(1.0 - cv$temp$2$var39)));
				
				// Recorded the probability of reaching sample task 41 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				cv$consumerDistributionProbabilityAccumulator = 0.0;
			} else {
				// Enumerating the possible outputs of Categorical 4.
				for(int index$sample5$6 = 0; index$sample5$6 < weightings.length; index$sample5$6 += 1) {
					// Update the probability of sampling this value from the distribution value.
					double cv$probabilitySample5Value7 = distribution$sample5[index$sample5$6];
					
					// Constructing a random variable input for use later.
					// 
					// Processing random variable 40.
					// 
					// Looking for a path between Sample 23 and consumer Bernoulli 40.
					// 
					// Value of the variable at this index
					double var39 = ((double)index$sample5$6 / cv$valuePos);
					
					// Record the probability of sample task 41 generating output with current configuration.
					// 
					// Substituted "j" with its value "i".
					// 
					// Substituted "cv$temp$3$var39" with its value "var39".
					if(((Math.log(cv$probabilitySample5Value7) + Math.log((v[i]?var39:(1.0 - var39)))) < cv$accumulatedConsumerProbabilities))
						// Substituted "j" with its value "i".
						// 
						// Substituted "cv$temp$3$var39" with its value "var39".
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value7) + Math.log((v[i]?var39:(1.0 - var39)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							// Substituted "j" with its value "i".
							// 
							// Substituted "cv$temp$3$var39" with its value "var39".
							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value7) + Math.log((v[i]?var39:(1.0 - var39))));
						else
							// Substituted "j" with its value "i".
							// 
							// Substituted "cv$temp$3$var39" with its value "var39".
							cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value7) + Math.log((v[i]?var39:(1.0 - var39)))))) + 1)) + Math.log(cv$probabilitySample5Value7)) + Math.log((v[i]?var39:(1.0 - var39))));
					}
					
					// Recorded the probability of reaching sample task 41 with the current configuration.
					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value7);
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
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var23$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample23[(i - 1)];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var23$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var23$stateProbabilityGlobal[cv$lseIndex];
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
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var23$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$localProbability[cv$indexName] = Math.exp((cv$var23$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var23$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 5 drawn from Categorical 4. Inference was performed using variable
	// marginalization.
	private final void sample5() {
		// Variable declaration of cv$numNumStates moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		int cv$numNumStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$weightings" with its value "weightings".
			double cv$accumulatedProbabilities = Math.log(weightings[cv$valuePos]);
			
			// Processing random variable 40.
			for(int j = 0; j < size; j += 1) {
				// Processing sample task 41 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Bernoulli 40 which is consuming
				// the output of Sample task 5.
				if((0 == j)) {
					if(fixedFlag$sample9) {
						// Constructing a random variable input for use later.
						// 
						// Substituted "j" with its value "0".
						// 
						// Value of the variable at this index
						double var39 = ((double)cv$valuePos / v2[0]);
						
						// Substituted "j" with its value "0".
						// 
						// Substituted "cv$temp$2$var39" with its value "var39".
						cv$accumulatedConsumerProbabilities = Math.log((v[0]?var39:(1.0 - var39)));
						
						// Recorded the probability of reaching sample task 41 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						// Enumerating the possible outputs of Categorical 8.
						for(int index$sample9$4 = 0; index$sample9$4 < weightings.length; index$sample9$4 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample9Value5 = distribution$sample9[index$sample9$4];
							
							// Constructing a random variable input for use later.
							// 
							// Substituted "j" with its value "0".
							// 
							// Value of the variable at this index
							double var39 = ((double)cv$valuePos / v2[0]);
							
							// Record the probability of sample task 41 generating output with current configuration.
							// 
							// Substituted "j" with its value "0".
							// 
							// Substituted "cv$temp$3$var39" with its value "var39".
							if(((Math.log(cv$probabilitySample9Value5) + Math.log((v[0]?var39:(1.0 - var39)))) < cv$accumulatedConsumerProbabilities))
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$3$var39" with its value "var39".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample9Value5) + Math.log((v[0]?var39:(1.0 - var39)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$3$var39" with its value "var39".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample9Value5) + Math.log((v[0]?var39:(1.0 - var39))));
								else
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$3$var39" with its value "var39".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample9Value5) + Math.log((v[0]?var39:(1.0 - var39)))))) + 1)) + Math.log(cv$probabilitySample9Value5)) + Math.log((v[0]?var39:(1.0 - var39))));
							}
							
							// Recorded the probability of reaching sample task 41 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample9Value5);
						}
					}
				}
				
				// Enumerating the possible arguments for the variable Bernoulli 40 which is consuming
				// the output of Sample task 5.
				if((1 <= j)) {
					// Enumerating the possible arguments for the variable Bernoulli 40 which is consuming
					// the output of Sample task 5.
					if(fixedFlag$sample23) {
						// Variable declaration of cv$temp$4$var39 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double cv$temp$4$var39 = ((double)cv$valuePos / v2[j]);
						
						// Record the probability of sample task 41 generating output with current configuration.
						if((Math.log((v[j]?cv$temp$4$var39:(1.0 - cv$temp$4$var39))) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((Math.log((v[j]?cv$temp$4$var39:(1.0 - cv$temp$4$var39))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = Math.log((v[j]?cv$temp$4$var39:(1.0 - cv$temp$4$var39)));
							else
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log((v[j]?cv$temp$4$var39:(1.0 - cv$temp$4$var39))))) + 1)) + Math.log((v[j]?cv$temp$4$var39:(1.0 - cv$temp$4$var39))));
						}
						
						// Recorded the probability of reaching sample task 41 with the current configuration.
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
					} else {
						// Enumerating the possible outputs of Categorical 22.
						for(int index$sample23$10 = 0; index$sample23$10 < weightings.length; index$sample23$10 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "i" with its value "j".
							double cv$probabilitySample23Value11 = distribution$sample23[(j - 1)][index$sample23$10];
							
							// Variable declaration of cv$temp$5$var39 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$5$var39 = ((double)cv$valuePos / v2[j]);
							
							// Record the probability of sample task 41 generating output with current configuration.
							if(((Math.log(cv$probabilitySample23Value11) + Math.log((v[j]?cv$temp$5$var39:(1.0 - cv$temp$5$var39)))) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample23Value11) + Math.log((v[j]?cv$temp$5$var39:(1.0 - cv$temp$5$var39)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample23Value11) + Math.log((v[j]?cv$temp$5$var39:(1.0 - cv$temp$5$var39))));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample23Value11) + Math.log((v[j]?cv$temp$5$var39:(1.0 - cv$temp$5$var39)))))) + 1)) + Math.log(cv$probabilitySample23Value11)) + Math.log((v[j]?cv$temp$5$var39:(1.0 - cv$temp$5$var39))));
							}
							
							// Recorded the probability of reaching sample task 41 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample23Value11);
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
			cv$var5$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var5$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var5$stateProbabilityGlobal[cv$lseIndex];
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
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var5$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				// Set the calculated probabilities to be the distribution values, and normalize
				// 
				// Local copy of the probability array
				distribution$sample5[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				// Set the calculated probabilities to be the distribution values, and normalize
				// 
				// Local copy of the probability array
				distribution$sample5[cv$indexName] = Math.exp((cv$var5$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var5$stateProbabilityGlobal.length; cv$indexName += 1)
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample5[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 9 drawn from Categorical 8. Inference was performed using variable
	// marginalization.
	private final void sample9() {
		// Variable declaration of cv$numNumStates moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		int cv$numNumStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$weightings" with its value "weightings".
			double cv$accumulatedProbabilities = Math.log(weightings[cv$valuePos]);
			
			// Substituted "j" with its value "0".
			if((0 < size)) {
				// Processing sample task 41 of consumer random variable null.
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Bernoulli 40 which is consuming
				// the output of Sample task 9.
				if(fixedFlag$sample5) {
					// Variable declaration of cv$temp$2$var39 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Processing random variable 40.
					// 
					// Looking for a path between Sample 9 and consumer Bernoulli 40.
					// 
					// Value of the variable at this index
					double cv$temp$2$var39 = ((double)v1 / cv$valuePos);
					
					// Substituted "j" with its value "0".
					cv$accumulatedConsumerProbabilities = Math.log((v[0]?cv$temp$2$var39:(1.0 - cv$temp$2$var39)));
					
					// Recorded the probability of reaching sample task 41 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					cv$consumerDistributionProbabilityAccumulator = 0.0;
				} else {
					// Enumerating the possible outputs of Categorical 4.
					for(int index$sample5$4 = 0; index$sample5$4 < weightings.length; index$sample5$4 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample5Value5 = distribution$sample5[index$sample5$4];
						
						// Constructing a random variable input for use later.
						// 
						// Processing random variable 40.
						// 
						// Looking for a path between Sample 9 and consumer Bernoulli 40.
						// 
						// Value of the variable at this index
						double var39 = ((double)index$sample5$4 / cv$valuePos);
						
						// Record the probability of sample task 41 generating output with current configuration.
						// 
						// Substituted "j" with its value "0".
						// 
						// Substituted "cv$temp$3$var39" with its value "var39".
						if(((Math.log(cv$probabilitySample5Value5) + Math.log((v[0]?var39:(1.0 - var39)))) < cv$accumulatedConsumerProbabilities))
							// Substituted "j" with its value "0".
							// 
							// Substituted "cv$temp$3$var39" with its value "var39".
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value5) + Math.log((v[0]?var39:(1.0 - var39)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$3$var39" with its value "var39".
								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value5) + Math.log((v[0]?var39:(1.0 - var39))));
							else
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$3$var39" with its value "var39".
								cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value5) + Math.log((v[0]?var39:(1.0 - var39)))))) + 1)) + Math.log(cv$probabilitySample5Value5)) + Math.log((v[0]?var39:(1.0 - var39))));
						}
						
						// Recorded the probability of reaching sample task 41 with the current configuration.
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value5);
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
			cv$var9$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var9$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var9$stateProbabilityGlobal[cv$lseIndex];
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
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var9$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				// Set the calculated probabilities to be the distribution values, and normalize
				// 
				// Local copy of the probability array
				distribution$sample9[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				// Set the calculated probabilities to be the distribution values, and normalize
				// 
				// Local copy of the probability array
				distribution$sample9[cv$indexName] = Math.exp((cv$var9$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var9$stateProbabilityGlobal.length; cv$indexName += 1)
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample9[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var5$stateProbabilityGlobal
		// 
		// Allocation of cv$var5$stateProbabilityGlobal for single threaded execution
		cv$var5$stateProbabilityGlobal = new double[weightings.length];
		
		// Constructor for cv$var9$stateProbabilityGlobal
		// 
		// Allocation of cv$var9$stateProbabilityGlobal for single threaded execution
		cv$var9$stateProbabilityGlobal = new double[weightings.length];
		
		// Constructor for cv$var23$stateProbabilityGlobal
		// 
		// Allocation of cv$var23$stateProbabilityGlobal for single threaded execution
		cv$var23$stateProbabilityGlobal = new double[weightings.length];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If v2 has not been set already allocate space.
		if((!fixedFlag$sample9 || !fixedFlag$sample23))
			// Constructor for v2
			v2 = new int[length$value];
		
		// Constructor for v
		v = new boolean[length$value];
		
		// Constructor for distribution$sample5
		distribution$sample5 = new double[weightings.length];
		
		// Constructor for distribution$sample23
		distribution$sample23 = new double[(length$value - 1)][];
		for(int i = 1; i < length$value; i += 1)
			distribution$sample23[(i - 1)] = new double[weightings.length];
		
		// Constructor for distribution$sample9
		distribution$sample9 = new double[weightings.length];
		
		// Constructor for logProbability$var22
		logProbability$var22 = new double[(length$value - 1)];
		
		// Constructor for logProbability$sample23
		logProbability$sample23 = new double[(length$value - 1)];
		
		// Constructor for logProbability$var40
		logProbability$var40 = new double[length$value];
		
		// Constructor for logProbability$sample41
		logProbability$sample41 = new double[length$value];
		
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
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample23) {
			for(int i = 1; i < size; i += 1)
				v2[i] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		}
		for(int j = 0; j < size; j += 1)
			v[j] = DistributionSampling.sampleBernoulli(RNG$, ((double)v1 / v2[j]));
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample5) {
			for(int index$var4 = 0; index$var4 < weightings.length; index$var4 += 1)
				// Save the probability of each value
				// 
				// cv$distribution$sample5's comment
				// Create local copy of variable probabilities.
				distribution$sample5[index$var4] = weightings[index$var4];
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample9) {
			for(int index$var8 = 0; index$var8 < weightings.length; index$var8 += 1)
				// Save the probability of each value
				// 
				// cv$distribution$sample9's comment
				// Create local copy of variable probabilities.
				distribution$sample9[index$var8] = weightings[index$var8];
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample23) {
			for(int i = 1; i < size; i += 1) {
				// Create local copy of variable probabilities.
				double[] cv$distribution$sample23 = distribution$sample23[(i - 1)];
				for(int index$var22 = 0; index$var22 < weightings.length; index$var22 += 1)
					// Save the probability of each value
					// 
					// Probability for this value
					cv$distribution$sample23[index$var22] = weightings[index$var22];
			}
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
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample23) {
			for(int i = 1; i < size; i += 1)
				v2[i] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
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
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample23) {
				for(int i = 1; i < size; i += 1)
					sample23(i);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample23) {
				for(int i = (size - 1); i >= 1; i -= 1)
					sample23(i);
			}
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
		logProbability$var4 = 0.0;
		if(!fixedProbFlag$sample5)
			logProbability$v1 = 0.0;
		logProbability$var8 = 0.0;
		logProbability$v2 = 0.0;
		if(!fixedProbFlag$sample9)
			logProbability$var9 = 0.0;
		for(int i = 1; i < size; i += 1)
			logProbability$var22[(i - 1)] = 0.0;
		if(!fixedProbFlag$sample23) {
			for(int i = 1; i < size; i += 1)
				logProbability$sample23[(i - 1)] = 0.0;
		}
		for(int j = 0; j < size; j += 1)
			logProbability$var40[j] = 0.0;
		logProbability$v = 0.0;
		if(!fixedProbFlag$sample41) {
			for(int j = 0; j < size; j += 1)
				logProbability$sample41[j] = 0.0;
		}
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
		logProbabilityValue$sample41();
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
		logProbabilityDistribution$sample41();
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
		logProbabilityValue$sample41();
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
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample23) {
			for(int i = 1; i < size; i += 1)
				v2[i] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		}
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = v.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			v[cv$index1] = value[cv$index1];
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
		     + "model DistributionTest2(double[] weightings, boolean[] value) {\n"
		     + "    int size = value.length;\n"
		     + "    \n"
		     + "    int v1 = categorical(weightings).sampleDistribution();\n"
		     + "    \n"
		     + "    int[] v2 = new int[size];\n"
		     + "    v2[0] = categorical(weightings).sampleDistribution();\n"
		     + "    for(int i:[1..size))\n"
		     + "        v2[i] = categorical(weightings).sampleDistribution();\n"
		     + "        \n"
		     + "    boolean[] v = new boolean[size];\n"
		     + "    for(int j:[0..size))\n"
		     + "        v[j] = bernoulli((1.0*v1)/v2[j]).sample();\n"
		     + "        \n"
		     + "    v.observe(value);\n"
		     + "}\n"
		     + "";
	}
}