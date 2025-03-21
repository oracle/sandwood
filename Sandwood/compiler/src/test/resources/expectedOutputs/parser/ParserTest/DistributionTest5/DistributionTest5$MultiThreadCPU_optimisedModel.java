package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DistributionTest5$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DistributionTest5$CoreInterface {
	
	// Declare the variables for the model.
	private double[] cv$var11$stateProbabilityGlobal;
	private double[] cv$var27$stateProbabilityGlobal;
	private double[] cv$var5$stateProbabilityGlobal;
	private double[] distribution$sample11;
	private double[][] distribution$sample27;
	private double[] distribution$sample5;
	private boolean fixedFlag$sample11 = false;
	private boolean fixedFlag$sample27 = false;
	private boolean fixedFlag$sample5 = false;
	private boolean fixedProbFlag$sample11 = false;
	private boolean fixedProbFlag$sample27 = false;
	private boolean fixedProbFlag$sample5 = false;
	private boolean fixedProbFlag$sample70 = false;
	private boolean[] guard$sample11bernoulli69$global;
	private boolean[] guard$sample27bernoulli69$global;
	private int length$value;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$sample27;
	private double[] logProbability$sample70;
	private double logProbability$v;
	private double logProbability$v1;
	private double logProbability$v2;
	private double logProbability$v3;
	private double logProbability$var10;
	private double logProbability$var11;
	private double[] logProbability$var26;
	private double logProbability$var4;
	private double[] logProbability$var69;
	private boolean setFlag$v = false;
	private boolean setFlag$v2 = false;
	private int size;
	private boolean system$gibbsForward = true;
	private boolean[] v;
	private int v1;
	private int[] v2;
	private int[] v3;
	private boolean[] value;
	private double[] weightings;

	public DistributionTest5$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for fixedFlag$sample11.
	@Override
	public final boolean get$fixedFlag$sample11() {
		return fixedFlag$sample11;
	}

	// Setter for fixedFlag$sample11.
	@Override
	public final void set$fixedFlag$sample11(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample11 including if probabilities
		// need to be updated.
		fixedFlag$sample11 = cv$value;
		
		// Should the probability of sample 11 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample11" with its value "cv$value".
		fixedProbFlag$sample11 = (cv$value && fixedProbFlag$sample11);
		
		// Should the probability of sample 70 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample11" with its value "cv$value".
		fixedProbFlag$sample70 = (cv$value && fixedProbFlag$sample70);
	}

	// Getter for fixedFlag$sample27.
	@Override
	public final boolean get$fixedFlag$sample27() {
		return fixedFlag$sample27;
	}

	// Setter for fixedFlag$sample27.
	@Override
	public final void set$fixedFlag$sample27(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample27 including if probabilities
		// need to be updated.
		fixedFlag$sample27 = cv$value;
		
		// Should the probability of sample 27 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample27" with its value "cv$value".
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
		
		// Should the probability of sample 70 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample27" with its value "cv$value".
		fixedProbFlag$sample70 = (cv$value && fixedProbFlag$sample70);
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
		
		// Should the probability of sample 70 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample5" with its value "cv$value".
		fixedProbFlag$sample70 = (cv$value && fixedProbFlag$sample70);
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
		
		// Unset the fixed probability flag for sample 70 as it depends on v1.
		fixedProbFlag$sample70 = false;
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
		// Set v2 with flag to mark that it has been set so another array doesn't need to
		// be constructed
		v2 = cv$value;
		setFlag$v2 = true;
		
		// Unset the fixed probability flag for sample 11 as it depends on v2.
		fixedProbFlag$sample11 = false;
		
		// Unset the fixed probability flag for sample 27 as it depends on v2.
		fixedProbFlag$sample27 = false;
		
		// Unset the fixed probability flag for sample 70 as it depends on v2.
		fixedProbFlag$sample70 = false;
	}

	// Getter for v3.
	@Override
	public final int[] get$v3() {
		return v3;
	}

	// Getter for value.
	@Override
	public final boolean[] get$value() {
		return value;
	}

	// Setter for value.
	@Override
	public final void set$value(boolean[] cv$value) {
		// Set value with flag to mark that it has been set so another array doesn't need
		// to be constructed
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
		// Set weightings with flag to mark that it has been set so another array doesn't
		// need to be constructed
		weightings = cv$value;
	}

	// Calculate the probability of the samples represented by sample11 using probability
	// distributions.
	private final void logProbabilityDistribution$sample11() {
		// Determine if we need to calculate the values for sample task 11 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample11) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample11) {
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
				logProbability$var10 = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$var11 = cv$distributionAccumulator;
				
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
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((fixedFlag$sample27 && (0 <= size)))
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
					logProbability$v3 = (logProbability$v3 + cv$distributionAccumulator);
				
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
				// Substituted "fixedFlag$sample11" with its value "true".
				fixedProbFlag$sample11 = true;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var10 = logProbability$var11;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(fixedFlag$sample11) {
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$v2 = (logProbability$v2 + logProbability$var11);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((fixedFlag$sample27 && (0 <= size)))
					// Update the variable probability
					// 
					// Variable declaration of cv$accumulator moved.
					logProbability$v3 = (logProbability$v3 + logProbability$var11);
			}
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var11);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample11)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var11);
		}
	}

	// Calculate the probability of the samples represented by sample27 using probability
	// distributions.
	private final void logProbabilityDistribution$sample27() {
		// Determine if we need to calculate the values for sample task 27 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample27) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample27) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int i = 0; i < size; i += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = v2[(i + 1)];
					
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
					logProbability$var26[i] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample27[i] = cv$distributionAccumulator;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(fixedFlag$sample11)
						// Update the variable probability
						logProbability$v3 = (logProbability$v3 + cv$distributionAccumulator);
				}
				
				// Update the variable probability
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample27" with its value "true".
				fixedProbFlag$sample27 = true;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				double cv$sampleValue = logProbability$sample27[i];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var26[i] = cv$sampleValue;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((fixedFlag$sample11 && fixedFlag$sample27))
					// Update the variable probability
					logProbability$v3 = (logProbability$v3 + cv$sampleValue);
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample27)
				// Update the variable probability
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample27)
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

	// Calculate the probability of the samples represented by sample70 using probability
	// distributions.
	private final void logProbabilityDistribution$sample70() {
		// Determine if we need to calculate the values for sample task 70 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample70) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 70 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				boolean cv$sampleValue = v[j];
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == j)) {
					// Enumerating the possible arguments for Bernoulli 69.
					if(fixedFlag$sample5) {
						if(fixedFlag$sample11) {
							if(fixedFlag$sample27) {
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "j" with its value "0".
								// 
								// Substituted "k" with its value "1".
								cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[0]) + v2[1]) / v2[1]));
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							} else {
								// Enumerating the possible outputs of Categorical 26.
								for(int index$sample27$318 = 0; index$sample27$318 < weightings.length; index$sample27$318 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "i" with its value "0".
									double cv$probabilitySample27Value319 = distribution$sample27[0][index$sample27$318];
									
									// Store the value of the function call, so the function call is only made once.
									// 
									// Substituted "j" with its value "0".
									// 
									// Substituted "k" with its value "1".
									double cv$weightedProbability = (Math.log(cv$probabilitySample27Value319) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[0]) + v2[1]) / v2[1])));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value319);
								}
							}
						} else {
							// Enumerating the possible outputs of Categorical 10.
							for(int index$sample11$307 = 0; index$sample11$307 < weightings.length; index$sample11$307 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample11Value308 = distribution$sample11[index$sample11$307];
								if(fixedFlag$sample27) {
									// Store the value of the function call, so the function call is only made once.
									// 
									// Substituted "j" with its value "0".
									// 
									// Substituted "k" with its value "1".
									double cv$weightedProbability = (Math.log(cv$probabilitySample11Value308) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[0]) + v2[1]) / v2[1])));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value308);
								} else {
									// Enumerating the possible outputs of Categorical 26.
									for(int index$sample27$324 = 0; index$sample27$324 < weightings.length; index$sample27$324 += 1) {
										// Update the probability of sampling this value from the distribution value.
										// 
										// Substituted "i" with its value "0".
										double cv$probabilitySample27Value325 = (cv$probabilitySample11Value308 * distribution$sample27[0][index$sample27$324]);
										
										// Store the value of the function call, so the function call is only made once.
										// 
										// Substituted "j" with its value "0".
										// 
										// Substituted "k" with its value "1".
										double cv$weightedProbability = (Math.log(cv$probabilitySample27Value325) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[0]) + v2[1]) / v2[1])));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value325);
									}
								}
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 4.
						for(int index$sample5$302 = 0; index$sample5$302 < weightings.length; index$sample5$302 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample5Value303 = distribution$sample5[index$sample5$302];
							if(fixedFlag$sample11) {
								if(fixedFlag$sample27) {
									// Store the value of the function call, so the function call is only made once.
									// 
									// Substituted "j" with its value "0".
									// 
									// Substituted "k" with its value "1".
									double cv$weightedProbability = (Math.log(cv$probabilitySample5Value303) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample5$302 + v2[0]) + v2[1]) / v2[1])));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value303);
								} else {
									// Enumerating the possible outputs of Categorical 26.
									for(int index$sample27$330 = 0; index$sample27$330 < weightings.length; index$sample27$330 += 1) {
										// Update the probability of sampling this value from the distribution value.
										// 
										// Substituted "i" with its value "0".
										double cv$probabilitySample27Value331 = (cv$probabilitySample5Value303 * distribution$sample27[0][index$sample27$330]);
										
										// Store the value of the function call, so the function call is only made once.
										// 
										// Substituted "j" with its value "0".
										// 
										// Substituted "k" with its value "1".
										double cv$weightedProbability = (Math.log(cv$probabilitySample27Value331) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample5$302 + v2[0]) + v2[1]) / v2[1])));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value331);
									}
								}
							} else {
								// Enumerating the possible outputs of Categorical 10.
								for(int index$sample11$312 = 0; index$sample11$312 < weightings.length; index$sample11$312 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample11Value313 = (cv$probabilitySample5Value303 * distribution$sample11[index$sample11$312]);
									if(fixedFlag$sample27) {
										// Store the value of the function call, so the function call is only made once.
										// 
										// Substituted "j" with its value "0".
										// 
										// Substituted "k" with its value "1".
										double cv$weightedProbability = (Math.log(cv$probabilitySample11Value313) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample5$302 + v2[0]) + v2[1]) / v2[1])));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value313);
									} else {
										// Enumerating the possible outputs of Categorical 26.
										for(int index$sample27$336 = 0; index$sample27$336 < weightings.length; index$sample27$336 += 1) {
											// Update the probability of sampling this value from the distribution value.
											// 
											// Substituted "i" with its value "0".
											double cv$probabilitySample27Value337 = (cv$probabilitySample11Value313 * distribution$sample27[0][index$sample27$336]);
											
											// Store the value of the function call, so the function call is only made once.
											// 
											// Substituted "j" with its value "0".
											// 
											// Substituted "k" with its value "1".
											double cv$weightedProbability = (Math.log(cv$probabilitySample27Value337) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample5$302 + v2[0]) + v2[1]) / v2[1])));
											
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
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value337);
										}
									}
								}
							}
						}
					}
				}
				
				// Enumerating the possible arguments for Bernoulli 69.
				if(fixedFlag$sample5) {
					if(fixedFlag$sample27) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 <= j)) {
							for(int index$i$450_1 = 0; index$i$450_1 < size; index$i$450_1 += 1) {
								int k = (index$i$450_1 + 1);
								
								// Substituted "k" with its value "(index$i$450_1 + 1)".
								// 
								// Substituted "k" with its value "(index$i$450_1 + 1)".
								// 
								// Substituted "k" with its value "(index$i$450_1 + 1)".
								// 
								// Substituted "k" with its value "(index$i$450_1 + 1)".
								// 
								// Substituted "k" with its value "(index$i$450_1 + 1)".
								// 
								// Substituted "k" with its value "(index$i$450_1 + 1)".
								if((k == (j + 1))) {
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[j]) + v2[k]) / v2[(j + 1)]));
									
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
						int i = (j - 1);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= i)) {
							// Enumerating the possible outputs of Categorical 26.
							for(int index$sample27$440 = 0; index$sample27$440 < weightings.length; index$sample27$440 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample27Value441 = distribution$sample27[i][index$sample27$440];
								{
									int k = (i + 1);
									
									// Substituted "i" with its value "(j - 1)".
									// 
									// Substituted "i" with its value "(j - 1)".
									// 
									// Substituted "i" with its value "(j - 1)".
									// 
									// Substituted "i" with its value "(j - 1)".
									// 
									// Substituted "i" with its value "(j - 1)".
									// 
									// Substituted "i" with its value "(j - 1)".
									if((k == (j + 1))) {
										int traceTempVariable$var63$451_2 = v2[k];
										
										// Substituted "i" with its value "(j - 1)".
										// 
										// Substituted "i" with its value "(j - 1)".
										// 
										// Substituted "i" with its value "(j - 1)".
										// 
										// Substituted "i" with its value "(j - 1)".
										// 
										// Substituted "i" with its value "(j - 1)".
										// 
										// Substituted "i" with its value "(j - 1)".
										if((i == j)) {
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(cv$probabilitySample27Value441) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[j]) + traceTempVariable$var63$451_2) / v2[(j + 1)])));
											
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
										
										// Substituted "index$i$466" with its value "j".
										else {
											// Enumerating the possible outputs of Categorical 26.
											for(int index$sample27$467 = 0; index$sample27$467 < weightings.length; index$sample27$467 += 1) {
												// Update the probability of sampling this value from the distribution value.
												// 
												// Substituted "index$i$466" with its value "j".
												double cv$probabilitySample27Value468 = (cv$probabilitySample27Value441 * distribution$sample27[j][index$sample27$467]);
												
												// Store the value of the function call, so the function call is only made once.
												double cv$weightedProbability = (Math.log(cv$probabilitySample27Value468) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[j]) + traceTempVariable$var63$451_2) / v2[(j + 1)])));
												
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
												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value468);
											}
										}
									}
								}
								for(int index$i$452 = 0; index$i$452 < size; index$i$452 += 1) {
									if(!(index$i$452 == i)) {
										// Enumerating the possible outputs of Categorical 26.
										for(int index$sample27$453 = 0; index$sample27$453 < weightings.length; index$sample27$453 += 1) {
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample27Value454 = (cv$probabilitySample27Value441 * distribution$sample27[index$i$452][index$sample27$453]);
											int k = (index$i$452 + 1);
											
											// Substituted "k" with its value "(index$i$452 + 1)".
											// 
											// Substituted "k" with its value "(index$i$452 + 1)".
											// 
											// Substituted "k" with its value "(index$i$452 + 1)".
											// 
											// Substituted "k" with its value "(index$i$452 + 1)".
											// 
											// Substituted "k" with its value "(index$i$452 + 1)".
											// 
											// Substituted "k" with its value "(index$i$452 + 1)".
											if((k == (j + 1))) {
												int traceTempVariable$var63$456_2 = v2[k];
												if((i == j)) {
													// Store the value of the function call, so the function call is only made once.
													double cv$weightedProbability = (Math.log(cv$probabilitySample27Value454) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[j]) + traceTempVariable$var63$456_2) / v2[(j + 1)])));
													
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
												if((index$i$452 == j)) {
													// Store the value of the function call, so the function call is only made once.
													double cv$weightedProbability = (Math.log(cv$probabilitySample27Value454) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[j]) + traceTempVariable$var63$456_2) / v2[(j + 1)])));
													
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
												
												// Substituted "index$i$473" with its value "j".
												if((!(j == i) && !(j == index$i$452))) {
													// Enumerating the possible outputs of Categorical 26.
													for(int index$sample27$474 = 0; index$sample27$474 < weightings.length; index$sample27$474 += 1) {
														// Update the probability of sampling this value from the distribution value.
														// 
														// Substituted "index$i$473" with its value "j".
														double cv$probabilitySample27Value475 = (cv$probabilitySample27Value454 * distribution$sample27[j][index$sample27$474]);
														
														// Store the value of the function call, so the function call is only made once.
														double cv$weightedProbability = (Math.log(cv$probabilitySample27Value475) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[j]) + traceTempVariable$var63$456_2) / v2[(j + 1)])));
														
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
														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value475);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				} else {
					// Enumerating the possible outputs of Categorical 4.
					for(int index$sample5$434 = 0; index$sample5$434 < weightings.length; index$sample5$434 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample5Value435 = distribution$sample5[index$sample5$434];
						if(fixedFlag$sample27) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((1 <= j)) {
								for(int index$i$457_1 = 0; index$i$457_1 < size; index$i$457_1 += 1) {
									int k = (index$i$457_1 + 1);
									
									// Substituted "k" with its value "(index$i$457_1 + 1)".
									// 
									// Substituted "k" with its value "(index$i$457_1 + 1)".
									// 
									// Substituted "k" with its value "(index$i$457_1 + 1)".
									// 
									// Substituted "k" with its value "(index$i$457_1 + 1)".
									// 
									// Substituted "k" with its value "(index$i$457_1 + 1)".
									// 
									// Substituted "k" with its value "(index$i$457_1 + 1)".
									if((k == (j + 1))) {
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(cv$probabilitySample5Value435) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample5$434 + v2[j]) + v2[k]) / v2[(j + 1)])));
										
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
								}
							}
						} else {
							int i = (j - 1);
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((0 <= i)) {
								// Enumerating the possible outputs of Categorical 26.
								for(int index$sample27$446 = 0; index$sample27$446 < weightings.length; index$sample27$446 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample27Value447 = (cv$probabilitySample5Value435 * distribution$sample27[i][index$sample27$446]);
									{
										int k = (i + 1);
										
										// Substituted "i" with its value "(j - 1)".
										// 
										// Substituted "i" with its value "(j - 1)".
										// 
										// Substituted "i" with its value "(j - 1)".
										// 
										// Substituted "i" with its value "(j - 1)".
										// 
										// Substituted "i" with its value "(j - 1)".
										// 
										// Substituted "i" with its value "(j - 1)".
										if((k == (j + 1))) {
											int traceTempVariable$var63$458_2 = v2[k];
											
											// Substituted "i" with its value "(j - 1)".
											// 
											// Substituted "i" with its value "(j - 1)".
											// 
											// Substituted "i" with its value "(j - 1)".
											// 
											// Substituted "i" with its value "(j - 1)".
											// 
											// Substituted "i" with its value "(j - 1)".
											// 
											// Substituted "i" with its value "(j - 1)".
											if((i == j)) {
												// Store the value of the function call, so the function call is only made once.
												double cv$weightedProbability = (Math.log(cv$probabilitySample27Value447) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample5$434 + v2[j]) + traceTempVariable$var63$458_2) / v2[(j + 1)])));
												
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
											
											// Substituted "index$i$480" with its value "j".
											else {
												// Enumerating the possible outputs of Categorical 26.
												for(int index$sample27$481 = 0; index$sample27$481 < weightings.length; index$sample27$481 += 1) {
													// Update the probability of sampling this value from the distribution value.
													// 
													// Substituted "index$i$480" with its value "j".
													double cv$probabilitySample27Value482 = (cv$probabilitySample27Value447 * distribution$sample27[j][index$sample27$481]);
													
													// Store the value of the function call, so the function call is only made once.
													double cv$weightedProbability = (Math.log(cv$probabilitySample27Value482) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample5$434 + v2[j]) + traceTempVariable$var63$458_2) / v2[(j + 1)])));
													
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
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value482);
												}
											}
										}
									}
									for(int index$i$459 = 0; index$i$459 < size; index$i$459 += 1) {
										if(!(index$i$459 == i)) {
											// Enumerating the possible outputs of Categorical 26.
											for(int index$sample27$460 = 0; index$sample27$460 < weightings.length; index$sample27$460 += 1) {
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample27Value461 = (cv$probabilitySample27Value447 * distribution$sample27[index$i$459][index$sample27$460]);
												int k = (index$i$459 + 1);
												
												// Substituted "k" with its value "(index$i$459 + 1)".
												// 
												// Substituted "k" with its value "(index$i$459 + 1)".
												// 
												// Substituted "k" with its value "(index$i$459 + 1)".
												// 
												// Substituted "k" with its value "(index$i$459 + 1)".
												// 
												// Substituted "k" with its value "(index$i$459 + 1)".
												// 
												// Substituted "k" with its value "(index$i$459 + 1)".
												if((k == (j + 1))) {
													int traceTempVariable$var63$463_2 = v2[k];
													if((i == j)) {
														// Store the value of the function call, so the function call is only made once.
														double cv$weightedProbability = (Math.log(cv$probabilitySample27Value461) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample5$434 + v2[j]) + traceTempVariable$var63$463_2) / v2[(j + 1)])));
														
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
													if((index$i$459 == j)) {
														// Store the value of the function call, so the function call is only made once.
														double cv$weightedProbability = (Math.log(cv$probabilitySample27Value461) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample5$434 + v2[j]) + traceTempVariable$var63$463_2) / v2[(j + 1)])));
														
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
													
													// Substituted "index$i$487" with its value "j".
													if((!(j == i) && !(j == index$i$459))) {
														// Enumerating the possible outputs of Categorical 26.
														for(int index$sample27$488 = 0; index$sample27$488 < weightings.length; index$sample27$488 += 1) {
															// Update the probability of sampling this value from the distribution value.
															// 
															// Substituted "index$i$487" with its value "j".
															double cv$probabilitySample27Value489 = (cv$probabilitySample27Value461 * distribution$sample27[j][index$sample27$488]);
															
															// Store the value of the function call, so the function call is only made once.
															double cv$weightedProbability = (Math.log(cv$probabilitySample27Value489) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample5$434 + v2[j]) + traceTempVariable$var63$463_2) / v2[(j + 1)])));
															
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
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value489);
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
				logProbability$var69[j] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample70[j] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$v = (logProbability$v + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample70 = ((fixedFlag$sample5 && fixedFlag$sample11) && fixedFlag$sample27);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample70[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var69[j] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$v = (logProbability$v + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample11 using sampled
	// values.
	private final void logProbabilityValue$sample11() {
		// Determine if we need to calculate the values for sample task 11 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample11) {
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
			logProbability$var10 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var11 = cv$distributionAccumulator;
			
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
			
			// Guard to ensure that v3 is only updated once for this probability.
			if((0 <= size))
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
				logProbability$v3 = (logProbability$v3 + cv$distributionAccumulator);
			
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
			if(fixedFlag$sample11)
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
			fixedProbFlag$sample11 = fixedFlag$sample11;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var10 = logProbability$var11;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$v2 = (logProbability$v2 + logProbability$var11);
			
			// Guard to ensure that v3 is only updated once for this probability.
			if((0 <= size))
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$v3 = (logProbability$v3 + logProbability$var11);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var11);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample11)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var11);
		}
	}

	// Calculate the probability of the samples represented by sample27 using sampled
	// values.
	private final void logProbabilityValue$sample27() {
		// Determine if we need to calculate the values for sample task 27 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample27) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = v2[(i + 1)];
				
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
				logProbability$var26[i] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample27[i] = cv$distributionAccumulator;
				
				// Update the variable probability
				logProbability$v3 = (logProbability$v3 + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample27 = fixedFlag$sample27;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				double cv$sampleValue = logProbability$sample27[i];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var26[i] = cv$sampleValue;
				
				// Update the variable probability
				logProbability$v3 = (logProbability$v3 + cv$sampleValue);
			}
			
			// Update the variable probability
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample27)
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

	// Calculate the probability of the samples represented by sample70 using sampled
	// values.
	private final void logProbabilityValue$sample70() {
		// Determine if we need to calculate the values for sample task 70 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample70) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(v[j], ((double)((v1 + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]));
				
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
				logProbability$var69[j] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample70[j] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$v = (logProbability$v + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample70 = ((fixedFlag$sample5 && fixedFlag$sample11) && fixedFlag$sample27);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample70[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var69[j] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$v = (logProbability$v + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 11 drawn from Categorical 10. Inference was performed using variable
	// marginalization.
	private final void sample11() {
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
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < size)) {
				// Set the flags to false
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample11bernoulli69$global[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample11bernoulli69$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample11bernoulli69$global[0] = true;
					
					// Processing sample task 70 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
					// the output of Sample task 11.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
					// the output of Sample task 11.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
					// the output of Sample task 11.
					if(fixedFlag$sample5) {
						if(fixedFlag$sample27) {
							// Substituted "j" with its value "0".
							// 
							// Substituted "cv$temp$28$var68" with its value "var68".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "j" with its value "0".
							// 
							// Value of the variable at this index
							// 
							// Substituted "k" with its value "1".
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((v1 + cv$valuePos) + v2[1]) / v2[1]));
							
							// Recorded the probability of reaching sample task 70 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							// Enumerating the possible outputs of Categorical 26.
							for(int index$sample27$130 = 0; index$sample27$130 < weightings.length; index$sample27$130 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "i" with its value "0".
								double cv$probabilitySample27Value131 = distribution$sample27[0][index$sample27$130];
								
								// Constructing a random variable input for use later.
								// 
								// Substituted "j" with its value "0".
								// 
								// Value of the variable at this index
								// 
								// Substituted "k" with its value "1".
								double var68 = ((double)((v1 + cv$valuePos) + v2[1]) / v2[1]);
								
								// Record the probability of sample task 70 generating output with current configuration.
								// 
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$29$var68" with its value "var68".
								if(((Math.log(cv$probabilitySample27Value131) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$29$var68" with its value "var68".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value131) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$29$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value131) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									else
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$29$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value131) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample27Value131)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
								}
								
								// Recorded the probability of reaching sample task 70 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value131);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 4.
						for(int index$sample5$124 = 0; index$sample5$124 < weightings.length; index$sample5$124 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample5Value125 = distribution$sample5[index$sample5$124];
							if(fixedFlag$sample27) {
								// Constructing a random variable input for use later.
								// 
								// Substituted "j" with its value "0".
								// 
								// Value of the variable at this index
								// 
								// Substituted "k" with its value "1".
								double var68 = ((double)((index$sample5$124 + cv$valuePos) + v2[1]) / v2[1]);
								
								// Record the probability of sample task 70 generating output with current configuration.
								// 
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$31$var68" with its value "var68".
								if(((Math.log(cv$probabilitySample5Value125) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$31$var68" with its value "var68".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value125) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$31$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value125) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									else
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$31$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value125) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample5Value125)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
								}
								
								// Recorded the probability of reaching sample task 70 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value125);
							} else {
								// Enumerating the possible outputs of Categorical 26.
								for(int index$sample27$136 = 0; index$sample27$136 < weightings.length; index$sample27$136 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "i" with its value "0".
									double cv$probabilitySample27Value137 = (cv$probabilitySample5Value125 * distribution$sample27[0][index$sample27$136]);
									
									// Constructing a random variable input for use later.
									// 
									// Substituted "j" with its value "0".
									// 
									// Value of the variable at this index
									// 
									// Substituted "k" with its value "1".
									double var68 = ((double)((index$sample5$124 + cv$valuePos) + v2[1]) / v2[1]);
									
									// Record the probability of sample task 70 generating output with current configuration.
									// 
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$32$var68" with its value "var68".
									if(((Math.log(cv$probabilitySample27Value137) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$32$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value137) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "j" with its value "0".
											// 
											// Substituted "cv$temp$32$var68" with its value "var68".
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value137) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
										else
											// Substituted "j" with its value "0".
											// 
											// Substituted "cv$temp$32$var68" with its value "var68".
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value137) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample27Value137)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									}
									
									// Recorded the probability of reaching sample task 70 with the current configuration.
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
			cv$var11$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
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
		double cv$lseMax = cv$var11$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var11$stateProbabilityGlobal[cv$lseIndex];
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
				cv$lseSum = (cv$lseSum + Math.exp((cv$var11$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
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
				distribution$sample11[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				// Set the calculated probabilities to be the distribution values, and normalize
				// 
				// Local copy of the probability array
				distribution$sample11[cv$indexName] = Math.exp((cv$var11$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var11$stateProbabilityGlobal.length; cv$indexName += 1)
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample11[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 27 drawn from Categorical 26. Inference was performed using variable
	// marginalization.
	private final void sample27(int i) {
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
			{
				int j = (i + 1);
				if((j < size))
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample27bernoulli69$global[j] = false;
			}
			
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// j's comment
			// Substituted "k" with its value "(i + 1)".
			guard$sample27bernoulli69$global[i] = false;
			int j = (i + 1);
			if(((j < size) && !guard$sample27bernoulli69$global[j])) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample27bernoulli69$global[j] = true;
				
				// Processing sample task 70 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
				// the output of Sample task 27.
				if(fixedFlag$sample5) {
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
						// Substituted "cv$temp$24$var68" with its value "var68".
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[i], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
						
						// Recorded the probability of reaching sample task 70 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					// index$i$2's comment
					// Copy of index so that its values can be safely substituted
					// 
					// Substituted "index$i$121" with its value "j".
					else {
						// Enumerating the possible outputs of Categorical 26.
						for(int index$sample27$122 = 0; index$sample27$122 < weightings.length; index$sample27$122 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$i$121" with its value "j".
							double cv$probabilitySample27Value123 = distribution$sample27[j][index$sample27$122];
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var68 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 70 generating output with current configuration.
							// 
							// Substituted "cv$temp$27$var68" with its value "var68".
							if(((Math.log(cv$probabilitySample27Value123) + DistributionSampling.logProbabilityBernoulli(v[j], var68)) < cv$accumulatedConsumerProbabilities))
								// Substituted "cv$temp$27$var68" with its value "var68".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value123) + DistributionSampling.logProbabilityBernoulli(v[j], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "cv$temp$27$var68" with its value "var68".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value123) + DistributionSampling.logProbabilityBernoulli(v[j], var68));
								else
									// Substituted "cv$temp$27$var68" with its value "var68".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value123) + DistributionSampling.logProbabilityBernoulli(v[j], var68)))) + 1)) + Math.log(cv$probabilitySample27Value123)) + DistributionSampling.logProbabilityBernoulli(v[j], var68));
							}
							
							// Recorded the probability of reaching sample task 70 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value123);
						}
					}
				}
				
				// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
				// the output of Sample task 27.
				// 
				// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
				// the output of Sample task 27.
				else {
					// Enumerating the possible outputs of Categorical 4.
					for(int index$sample5$116 = 0; index$sample5$116 < weightings.length; index$sample5$116 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample5Value117 = distribution$sample5[index$sample5$116];
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((i == j)) {
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var68 = ((double)((index$sample5$116 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 70 generating output with current configuration.
							// 
							// Substituted "cv$temp$29$var68" with its value "var68".
							if(((Math.log(cv$probabilitySample5Value117) + DistributionSampling.logProbabilityBernoulli(v[i], var68)) < cv$accumulatedConsumerProbabilities))
								// Substituted "cv$temp$29$var68" with its value "var68".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value117) + DistributionSampling.logProbabilityBernoulli(v[i], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "cv$temp$29$var68" with its value "var68".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value117) + DistributionSampling.logProbabilityBernoulli(v[i], var68));
								else
									// Substituted "cv$temp$29$var68" with its value "var68".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value117) + DistributionSampling.logProbabilityBernoulli(v[i], var68)))) + 1)) + Math.log(cv$probabilitySample5Value117)) + DistributionSampling.logProbabilityBernoulli(v[i], var68));
							}
							
							// Recorded the probability of reaching sample task 70 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value117);
						}
						
						// index$i$2's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "index$i$127" with its value "j".
						else {
							// Enumerating the possible outputs of Categorical 26.
							for(int index$sample27$128 = 0; index$sample27$128 < weightings.length; index$sample27$128 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$i$127" with its value "j".
								double cv$probabilitySample27Value129 = (cv$probabilitySample5Value117 * distribution$sample27[j][index$sample27$128]);
								
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var68 = ((double)((index$sample5$116 + cv$valuePos) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 70 generating output with current configuration.
								// 
								// Substituted "cv$temp$32$var68" with its value "var68".
								if(((Math.log(cv$probabilitySample27Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var68)) < cv$accumulatedConsumerProbabilities))
									// Substituted "cv$temp$32$var68" with its value "var68".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "cv$temp$32$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var68));
									else
										// Substituted "cv$temp$32$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var68)))) + 1)) + Math.log(cv$probabilitySample27Value129)) + DistributionSampling.logProbabilityBernoulli(v[j], var68));
								}
								
								// Recorded the probability of reaching sample task 70 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value129);
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
			if(!guard$sample27bernoulli69$global[i]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample27bernoulli69$global[i] = true;
				
				// Processing sample task 70 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
				// the output of Sample task 27.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == i)) {
					// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
					// the output of Sample task 27.
					if(fixedFlag$sample5) {
						if(fixedFlag$sample11) {
							// Substituted "j" with its value "i".
							// 
							// Substituted "cv$temp$40$var68" with its value "var68".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "j" with its value "i".
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((v1 + v2[0]) + cv$valuePos) / cv$valuePos));
							
							// Recorded the probability of reaching sample task 70 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							// Enumerating the possible outputs of Categorical 10.
							for(int index$sample11$191 = 0; index$sample11$191 < weightings.length; index$sample11$191 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample11Value192 = distribution$sample11[index$sample11$191];
								
								// Constructing a random variable input for use later.
								// 
								// Substituted "j" with its value "i".
								// 
								// Value of the variable at this index
								double var68 = ((double)((v1 + v2[0]) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 70 generating output with current configuration.
								// 
								// Substituted "j" with its value "i".
								// 
								// Substituted "cv$temp$42$var68" with its value "var68".
								if(((Math.log(cv$probabilitySample11Value192) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "i".
									// 
									// Substituted "cv$temp$42$var68" with its value "var68".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value192) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "i".
										// 
										// Substituted "cv$temp$42$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value192) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									else
										// Substituted "j" with its value "i".
										// 
										// Substituted "cv$temp$42$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value192) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample11Value192)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
								}
								
								// Recorded the probability of reaching sample task 70 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value192);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 4.
						for(int index$sample5$186 = 0; index$sample5$186 < weightings.length; index$sample5$186 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample5Value187 = distribution$sample5[index$sample5$186];
							if(fixedFlag$sample11) {
								// Constructing a random variable input for use later.
								// 
								// Substituted "j" with its value "i".
								// 
								// Value of the variable at this index
								double var68 = ((double)((index$sample5$186 + v2[0]) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 70 generating output with current configuration.
								// 
								// Substituted "j" with its value "i".
								// 
								// Substituted "cv$temp$44$var68" with its value "var68".
								if(((Math.log(cv$probabilitySample5Value187) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "i".
									// 
									// Substituted "cv$temp$44$var68" with its value "var68".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value187) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "i".
										// 
										// Substituted "cv$temp$44$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value187) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									else
										// Substituted "j" with its value "i".
										// 
										// Substituted "cv$temp$44$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value187) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample5Value187)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
								}
								
								// Recorded the probability of reaching sample task 70 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value187);
							} else {
								// Enumerating the possible outputs of Categorical 10.
								for(int index$sample11$196 = 0; index$sample11$196 < weightings.length; index$sample11$196 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample11Value197 = (cv$probabilitySample5Value187 * distribution$sample11[index$sample11$196]);
									
									// Constructing a random variable input for use later.
									// 
									// Substituted "j" with its value "i".
									// 
									// Value of the variable at this index
									double var68 = ((double)((index$sample5$186 + v2[0]) + cv$valuePos) / cv$valuePos);
									
									// Record the probability of sample task 70 generating output with current configuration.
									// 
									// Substituted "j" with its value "i".
									// 
									// Substituted "cv$temp$46$var68" with its value "var68".
									if(((Math.log(cv$probabilitySample11Value197) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
										// Substituted "j" with its value "i".
										// 
										// Substituted "cv$temp$46$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value197) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "j" with its value "i".
											// 
											// Substituted "cv$temp$46$var68" with its value "var68".
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value197) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
										else
											// Substituted "j" with its value "i".
											// 
											// Substituted "cv$temp$46$var68" with its value "var68".
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value197) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample11Value197)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									}
									
									// Recorded the probability of reaching sample task 70 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value197);
								}
							}
						}
					}
				}
				
				// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
				// the output of Sample task 27.
				if(fixedFlag$sample5) {
					int index$i$267 = (i - 1);
					
					// index$i$2's comment
					// Copy of index so that its values can be safely substituted
					// 
					// Substituted "index$i$267" with its value "(i - 1)".
					// 
					// Substituted "index$i$267" with its value "(i - 1)".
					// 
					// Substituted "index$i$267" with its value "(i - 1)".
					// 
					// Substituted "index$i$267" with its value "(i - 1)".
					// 
					// Substituted "index$i$267" with its value "(i - 1)".
					if(((0 <= index$i$267) && !(index$i$267 == i))) {
						// Enumerating the possible outputs of Categorical 26.
						for(int index$sample27$268 = 0; index$sample27$268 < weightings.length; index$sample27$268 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample27Value269 = distribution$sample27[index$i$267][index$sample27$268];
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var68 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 70 generating output with current configuration.
							// 
							// Substituted "j" with its value "i".
							// 
							// Substituted "cv$temp$58$var68" with its value "var68".
							if(((Math.log(cv$probabilitySample27Value269) + DistributionSampling.logProbabilityBernoulli(v[i], var68)) < cv$accumulatedConsumerProbabilities))
								// Substituted "j" with its value "i".
								// 
								// Substituted "cv$temp$58$var68" with its value "var68".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value269) + DistributionSampling.logProbabilityBernoulli(v[i], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "j" with its value "i".
									// 
									// Substituted "cv$temp$58$var68" with its value "var68".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value269) + DistributionSampling.logProbabilityBernoulli(v[i], var68));
								else
									// Substituted "j" with its value "i".
									// 
									// Substituted "cv$temp$58$var68" with its value "var68".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value269) + DistributionSampling.logProbabilityBernoulli(v[i], var68)))) + 1)) + Math.log(cv$probabilitySample27Value269)) + DistributionSampling.logProbabilityBernoulli(v[i], var68));
							}
							
							// Recorded the probability of reaching sample task 70 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value269);
						}
					}
				} else {
					// Enumerating the possible outputs of Categorical 4.
					for(int index$sample5$262 = 0; index$sample5$262 < weightings.length; index$sample5$262 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample5Value263 = distribution$sample5[index$sample5$262];
						int index$i$273 = (i - 1);
						
						// index$i$2's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "index$i$273" with its value "(i - 1)".
						// 
						// Substituted "index$i$273" with its value "(i - 1)".
						// 
						// Substituted "index$i$273" with its value "(i - 1)".
						// 
						// Substituted "index$i$273" with its value "(i - 1)".
						// 
						// Substituted "index$i$273" with its value "(i - 1)".
						if(((0 <= index$i$273) && !(index$i$273 == i))) {
							// Enumerating the possible outputs of Categorical 26.
							for(int index$sample27$274 = 0; index$sample27$274 < weightings.length; index$sample27$274 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample27Value275 = (cv$probabilitySample5Value263 * distribution$sample27[index$i$273][index$sample27$274]);
								
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var68 = ((double)((index$sample5$262 + cv$valuePos) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 70 generating output with current configuration.
								// 
								// Substituted "j" with its value "i".
								// 
								// Substituted "cv$temp$63$var68" with its value "var68".
								if(((Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[i], var68)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "i".
									// 
									// Substituted "cv$temp$63$var68" with its value "var68".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[i], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "i".
										// 
										// Substituted "cv$temp$63$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[i], var68));
									else
										// Substituted "j" with its value "i".
										// 
										// Substituted "cv$temp$63$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[i], var68)))) + 1)) + Math.log(cv$probabilitySample27Value275)) + DistributionSampling.logProbabilityBernoulli(v[i], var68));
								}
								
								// Recorded the probability of reaching sample task 70 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value275);
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
			
			// j's comment
			// Substituted "k" with its value "(i + 1)".
			if(!guard$sample27bernoulli69$global[i]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// j's comment
				// Substituted "k" with its value "(i + 1)".
				guard$sample27bernoulli69$global[i] = true;
				
				// Processing sample task 70 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
				// the output of Sample task 27.
				// 
				// j's comment
				// Substituted "k" with its value "(i + 1)".
				if((0 == i)) {
					// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
					// the output of Sample task 27.
					if(fixedFlag$sample5) {
						if(fixedFlag$sample11) {
							// Substituted "j" with its value "0".
							// 
							// Substituted "cv$temp$80$var68" with its value "var68".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "j" with its value "0".
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((v1 + v2[0]) + cv$valuePos) / cv$valuePos));
							
							// Recorded the probability of reaching sample task 70 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							// Enumerating the possible outputs of Categorical 10.
							for(int index$sample11$374 = 0; index$sample11$374 < weightings.length; index$sample11$374 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample11Value375 = distribution$sample11[index$sample11$374];
								
								// Constructing a random variable input for use later.
								// 
								// Substituted "j" with its value "0".
								// 
								// Value of the variable at this index
								double var68 = ((double)((v1 + v2[0]) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 70 generating output with current configuration.
								// 
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$82$var68" with its value "var68".
								if(((Math.log(cv$probabilitySample11Value375) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$82$var68" with its value "var68".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value375) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$82$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value375) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									else
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$82$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value375) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample11Value375)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
								}
								
								// Recorded the probability of reaching sample task 70 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value375);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 4.
						for(int index$sample5$369 = 0; index$sample5$369 < weightings.length; index$sample5$369 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample5Value370 = distribution$sample5[index$sample5$369];
							if(fixedFlag$sample11) {
								// Constructing a random variable input for use later.
								// 
								// Substituted "j" with its value "0".
								// 
								// Value of the variable at this index
								double var68 = ((double)((index$sample5$369 + v2[0]) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 70 generating output with current configuration.
								// 
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$84$var68" with its value "var68".
								if(((Math.log(cv$probabilitySample5Value370) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$84$var68" with its value "var68".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value370) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$84$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value370) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									else
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$84$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value370) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample5Value370)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
								}
								
								// Recorded the probability of reaching sample task 70 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value370);
							} else {
								// Enumerating the possible outputs of Categorical 10.
								for(int index$sample11$379 = 0; index$sample11$379 < weightings.length; index$sample11$379 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample11Value380 = (cv$probabilitySample5Value370 * distribution$sample11[index$sample11$379]);
									
									// Constructing a random variable input for use later.
									// 
									// Substituted "j" with its value "0".
									// 
									// Value of the variable at this index
									double var68 = ((double)((index$sample5$369 + v2[0]) + cv$valuePos) / cv$valuePos);
									
									// Record the probability of sample task 70 generating output with current configuration.
									// 
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$86$var68" with its value "var68".
									if(((Math.log(cv$probabilitySample11Value380) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$86$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value380) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "j" with its value "0".
											// 
											// Substituted "cv$temp$86$var68" with its value "var68".
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value380) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
										else
											// Substituted "j" with its value "0".
											// 
											// Substituted "cv$temp$86$var68" with its value "var68".
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value380) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample11Value380)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									}
									
									// Recorded the probability of reaching sample task 70 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value380);
								}
							}
						}
					}
				}
				
				// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
				// the output of Sample task 27.
				if(fixedFlag$sample5) {
					// j's comment
					// Substituted "k" with its value "(i + 1)".
					int index$i$413 = (i - 1);
					
					// index$i$2's comment
					// Copy of index so that its values can be safely substituted
					// 
					// Substituted "index$i$413" with its value "(j - 1)".
					// 
					// index$i$413's comment
					// 
					// j's comment
					// Substituted "k" with its value "(i + 1)".
					// 
					// index$i$413's comment
					// 
					// j's comment
					// Substituted "k" with its value "(i + 1)".
					// 
					// index$i$413's comment
					// 
					// j's comment
					// Substituted "k" with its value "(i + 1)".
					if(((0 <= index$i$413) && !(index$i$413 == i))) {
						// Enumerating the possible outputs of Categorical 26.
						for(int index$sample27$414 = 0; index$sample27$414 < weightings.length; index$sample27$414 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample27Value415 = distribution$sample27[index$i$413][index$sample27$414];
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var68 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 70 generating output with current configuration.
							// 
							// Substituted "cv$temp$90$var68" with its value "var68".
							if(((Math.log(cv$probabilitySample27Value415) + DistributionSampling.logProbabilityBernoulli(v[i], var68)) < cv$accumulatedConsumerProbabilities))
								// Substituted "cv$temp$90$var68" with its value "var68".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value415) + DistributionSampling.logProbabilityBernoulli(v[i], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "cv$temp$90$var68" with its value "var68".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value415) + DistributionSampling.logProbabilityBernoulli(v[i], var68));
								else
									// Substituted "cv$temp$90$var68" with its value "var68".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value415) + DistributionSampling.logProbabilityBernoulli(v[i], var68)))) + 1)) + Math.log(cv$probabilitySample27Value415)) + DistributionSampling.logProbabilityBernoulli(v[i], var68));
							}
							
							// Recorded the probability of reaching sample task 70 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value415);
						}
					}
				} else {
					// Enumerating the possible outputs of Categorical 4.
					for(int index$sample5$408 = 0; index$sample5$408 < weightings.length; index$sample5$408 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample5Value409 = distribution$sample5[index$sample5$408];
						
						// j's comment
						// Substituted "k" with its value "(i + 1)".
						int index$i$419 = (i - 1);
						
						// index$i$2's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "index$i$419" with its value "(j - 1)".
						// 
						// index$i$419's comment
						// 
						// j's comment
						// Substituted "k" with its value "(i + 1)".
						// 
						// index$i$419's comment
						// 
						// j's comment
						// Substituted "k" with its value "(i + 1)".
						// 
						// index$i$419's comment
						// 
						// j's comment
						// Substituted "k" with its value "(i + 1)".
						if(((0 <= index$i$419) && !(index$i$419 == i))) {
							// Enumerating the possible outputs of Categorical 26.
							for(int index$sample27$420 = 0; index$sample27$420 < weightings.length; index$sample27$420 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample27Value421 = (cv$probabilitySample5Value409 * distribution$sample27[index$i$419][index$sample27$420]);
								
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var68 = ((double)((index$sample5$408 + cv$valuePos) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 70 generating output with current configuration.
								// 
								// Substituted "cv$temp$95$var68" with its value "var68".
								if(((Math.log(cv$probabilitySample27Value421) + DistributionSampling.logProbabilityBernoulli(v[i], var68)) < cv$accumulatedConsumerProbabilities))
									// Substituted "cv$temp$95$var68" with its value "var68".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value421) + DistributionSampling.logProbabilityBernoulli(v[i], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "cv$temp$95$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value421) + DistributionSampling.logProbabilityBernoulli(v[i], var68));
									else
										// Substituted "cv$temp$95$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value421) + DistributionSampling.logProbabilityBernoulli(v[i], var68)))) + 1)) + Math.log(cv$probabilitySample27Value421)) + DistributionSampling.logProbabilityBernoulli(v[i], var68));
								}
								
								// Recorded the probability of reaching sample task 70 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value421);
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
			cv$var27$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample27[i];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var27$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var27$stateProbabilityGlobal[cv$lseIndex];
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
				cv$lseSum = (cv$lseSum + Math.exp((cv$var27$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
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
				cv$localProbability[cv$indexName] = Math.exp((cv$var27$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var27$stateProbabilityGlobal.length; cv$indexName += 1)
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
			
			// Processing random variable 69.
			for(int j = 0; j < size; j += 1) {
				// Processing sample task 70 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == j)) {
					// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
					// the output of Sample task 5.
					if(fixedFlag$sample11) {
						if(fixedFlag$sample27) {
							// Substituted "j" with its value "0".
							// 
							// Substituted "cv$temp$32$var68" with its value "var68".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "j" with its value "0".
							// 
							// Value of the variable at this index
							// 
							// Substituted "k" with its value "1".
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((cv$valuePos + v2[0]) + v2[1]) / v2[1]));
							
							// Recorded the probability of reaching sample task 70 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							// Enumerating the possible outputs of Categorical 26.
							for(int index$sample27$147 = 0; index$sample27$147 < weightings.length; index$sample27$147 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "i" with its value "0".
								double cv$probabilitySample27Value148 = distribution$sample27[0][index$sample27$147];
								
								// Constructing a random variable input for use later.
								// 
								// Substituted "j" with its value "0".
								// 
								// Value of the variable at this index
								// 
								// Substituted "k" with its value "1".
								double var68 = ((double)((cv$valuePos + v2[0]) + v2[1]) / v2[1]);
								
								// Record the probability of sample task 70 generating output with current configuration.
								// 
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$33$var68" with its value "var68".
								if(((Math.log(cv$probabilitySample27Value148) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$33$var68" with its value "var68".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value148) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$33$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value148) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									else
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$33$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value148) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample27Value148)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
								}
								
								// Recorded the probability of reaching sample task 70 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value148);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 10.
						for(int index$sample11$141 = 0; index$sample11$141 < weightings.length; index$sample11$141 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample11Value142 = distribution$sample11[index$sample11$141];
							if(fixedFlag$sample27) {
								// Constructing a random variable input for use later.
								// 
								// Substituted "j" with its value "0".
								// 
								// Value of the variable at this index
								// 
								// Substituted "k" with its value "1".
								double var68 = ((double)((cv$valuePos + v2[0]) + v2[1]) / v2[1]);
								
								// Record the probability of sample task 70 generating output with current configuration.
								// 
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$35$var68" with its value "var68".
								if(((Math.log(cv$probabilitySample11Value142) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$35$var68" with its value "var68".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value142) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$35$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value142) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									else
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$35$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value142) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample11Value142)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
								}
								
								// Recorded the probability of reaching sample task 70 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value142);
							} else {
								// Enumerating the possible outputs of Categorical 26.
								for(int index$sample27$153 = 0; index$sample27$153 < weightings.length; index$sample27$153 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "i" with its value "0".
									double cv$probabilitySample27Value154 = (cv$probabilitySample11Value142 * distribution$sample27[0][index$sample27$153]);
									
									// Constructing a random variable input for use later.
									// 
									// Substituted "j" with its value "0".
									// 
									// Value of the variable at this index
									// 
									// Substituted "k" with its value "1".
									double var68 = ((double)((cv$valuePos + v2[0]) + v2[1]) / v2[1]);
									
									// Record the probability of sample task 70 generating output with current configuration.
									// 
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$36$var68" with its value "var68".
									if(((Math.log(cv$probabilitySample27Value154) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$36$var68" with its value "var68".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value154) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "j" with its value "0".
											// 
											// Substituted "cv$temp$36$var68" with its value "var68".
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value154) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
										else
											// Substituted "j" with its value "0".
											// 
											// Substituted "cv$temp$36$var68" with its value "var68".
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value154) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample27Value154)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									}
									
									// Recorded the probability of reaching sample task 70 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value154);
								}
							}
						}
					}
				}
				
				// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
				// the output of Sample task 5.
				if(fixedFlag$sample27) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 <= j)) {
						for(int index$i$207_1 = 0; index$i$207_1 < size; index$i$207_1 += 1) {
							int k = (index$i$207_1 + 1);
							
							// Substituted "k" with its value "(index$i$207_1 + 1)".
							// 
							// Substituted "k" with its value "(index$i$207_1 + 1)".
							// 
							// Substituted "k" with its value "(index$i$207_1 + 1)".
							// 
							// Substituted "k" with its value "(index$i$207_1 + 1)".
							// 
							// Substituted "k" with its value "(index$i$207_1 + 1)".
							// 
							// Substituted "k" with its value "(index$i$207_1 + 1)".
							if((k == (j + 1))) {
								// Variable declaration of cv$temp$44$var68 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$44$var68 = ((double)((cv$valuePos + v2[j]) + v2[k]) / v2[(j + 1)]);
								
								// Record the probability of sample task 70 generating output with current configuration.
								if((DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var68) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var68) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var68);
									else
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var68))) + 1)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var68));
								}
								
								// Recorded the probability of reaching sample task 70 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
							}
						}
					}
				} else {
					int i = (j - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 <= i)) {
						// Enumerating the possible outputs of Categorical 26.
						for(int index$sample27$203 = 0; index$sample27$203 < weightings.length; index$sample27$203 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample27Value204 = distribution$sample27[i][index$sample27$203];
							{
								int k = (i + 1);
								
								// Substituted "i" with its value "(j - 1)".
								// 
								// Substituted "i" with its value "(j - 1)".
								// 
								// Substituted "i" with its value "(j - 1)".
								// 
								// Substituted "i" with its value "(j - 1)".
								// 
								// Substituted "i" with its value "(j - 1)".
								// 
								// Substituted "i" with its value "(j - 1)".
								if((k == (j + 1))) {
									int traceTempVariable$var63$208_2 = v2[k];
									
									// Substituted "i" with its value "(j - 1)".
									// 
									// Substituted "i" with its value "(j - 1)".
									// 
									// Substituted "i" with its value "(j - 1)".
									// 
									// Substituted "i" with its value "(j - 1)".
									// 
									// Substituted "i" with its value "(j - 1)".
									// 
									// Substituted "i" with its value "(j - 1)".
									if((i == j)) {
										// Variable declaration of cv$temp$45$var68 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double cv$temp$45$var68 = ((double)((cv$valuePos + v2[j]) + traceTempVariable$var63$208_2) / v2[(j + 1)]);
										
										// Record the probability of sample task 70 generating output with current configuration.
										if(((Math.log(cv$probabilitySample27Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var68)) < cv$accumulatedConsumerProbabilities))
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											// If the second value is -infinity.
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var68));
											else
												cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var68)))) + 1)) + Math.log(cv$probabilitySample27Value204)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var68));
										}
										
										// Recorded the probability of reaching sample task 70 with the current configuration.
										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value204);
									}
									
									// Substituted "index$i$216" with its value "j".
									else {
										// Enumerating the possible outputs of Categorical 26.
										for(int index$sample27$217 = 0; index$sample27$217 < weightings.length; index$sample27$217 += 1) {
											// Update the probability of sampling this value from the distribution value.
											// 
											// Substituted "index$i$216" with its value "j".
											double cv$probabilitySample27Value218 = (cv$probabilitySample27Value204 * distribution$sample27[j][index$sample27$217]);
											
											// Variable declaration of cv$temp$46$var68 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Value of the variable at this index
											double cv$temp$46$var68 = ((double)((cv$valuePos + v2[j]) + traceTempVariable$var63$208_2) / v2[(j + 1)]);
											
											// Record the probability of sample task 70 generating output with current configuration.
											if(((Math.log(cv$probabilitySample27Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var68)) < cv$accumulatedConsumerProbabilities))
												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
											else {
												// If the second value is -infinity.
												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var68));
												else
													cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var68)))) + 1)) + Math.log(cv$probabilitySample27Value218)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var68));
											}
											
											// Recorded the probability of reaching sample task 70 with the current configuration.
											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value218);
										}
									}
								}
							}
							for(int index$i$209 = 0; index$i$209 < size; index$i$209 += 1) {
								if(!(index$i$209 == i)) {
									// Enumerating the possible outputs of Categorical 26.
									for(int index$sample27$210 = 0; index$sample27$210 < weightings.length; index$sample27$210 += 1) {
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample27Value211 = (cv$probabilitySample27Value204 * distribution$sample27[index$i$209][index$sample27$210]);
										int k = (index$i$209 + 1);
										
										// Substituted "k" with its value "(index$i$209 + 1)".
										// 
										// Substituted "k" with its value "(index$i$209 + 1)".
										// 
										// Substituted "k" with its value "(index$i$209 + 1)".
										// 
										// Substituted "k" with its value "(index$i$209 + 1)".
										// 
										// Substituted "k" with its value "(index$i$209 + 1)".
										// 
										// Substituted "k" with its value "(index$i$209 + 1)".
										if((k == (j + 1))) {
											int traceTempVariable$var63$213_2 = v2[k];
											if((i == j)) {
												// Variable declaration of cv$temp$47$var68 moved.
												// 
												// Constructing a random variable input for use later.
												// 
												// Value of the variable at this index
												double cv$temp$47$var68 = ((double)((cv$valuePos + v2[j]) + traceTempVariable$var63$213_2) / v2[(j + 1)]);
												
												// Record the probability of sample task 70 generating output with current configuration.
												if(((Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var68)) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													// If the second value is -infinity.
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var68));
													else
														cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var68)))) + 1)) + Math.log(cv$probabilitySample27Value211)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var68));
												}
												
												// Recorded the probability of reaching sample task 70 with the current configuration.
												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value211);
											}
											if((index$i$209 == j)) {
												// Variable declaration of cv$temp$48$var68 moved.
												// 
												// Constructing a random variable input for use later.
												// 
												// Value of the variable at this index
												double cv$temp$48$var68 = ((double)((cv$valuePos + v2[j]) + traceTempVariable$var63$213_2) / v2[(j + 1)]);
												
												// Record the probability of sample task 70 generating output with current configuration.
												if(((Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var68)) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													// If the second value is -infinity.
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var68));
													else
														cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var68)))) + 1)) + Math.log(cv$probabilitySample27Value211)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var68));
												}
												
												// Recorded the probability of reaching sample task 70 with the current configuration.
												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value211);
											}
											
											// Substituted "index$i$223" with its value "j".
											if((!(j == i) && !(j == index$i$209))) {
												// Enumerating the possible outputs of Categorical 26.
												for(int index$sample27$224 = 0; index$sample27$224 < weightings.length; index$sample27$224 += 1) {
													// Update the probability of sampling this value from the distribution value.
													// 
													// Substituted "index$i$223" with its value "j".
													double cv$probabilitySample27Value225 = (cv$probabilitySample27Value211 * distribution$sample27[j][index$sample27$224]);
													
													// Variable declaration of cv$temp$49$var68 moved.
													// 
													// Constructing a random variable input for use later.
													// 
													// Value of the variable at this index
													double cv$temp$49$var68 = ((double)((cv$valuePos + v2[j]) + traceTempVariable$var63$213_2) / v2[(j + 1)]);
													
													// Record the probability of sample task 70 generating output with current configuration.
													if(((Math.log(cv$probabilitySample27Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var68)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var68));
														else
															cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var68)))) + 1)) + Math.log(cv$probabilitySample27Value225)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var68));
													}
													
													// Recorded the probability of reaching sample task 70 with the current configuration.
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value225);
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
		
		// Constructor for cv$var11$stateProbabilityGlobal
		// 
		// Allocation of cv$var11$stateProbabilityGlobal for single threaded execution
		cv$var11$stateProbabilityGlobal = new double[weightings.length];
		
		// Constructor for guard$sample11bernoulli69$global
		// 
		// Allocation of guard$sample11bernoulli69$global for single threaded execution
		guard$sample11bernoulli69$global = new boolean[length$value];
		
		// Constructor for cv$var27$stateProbabilityGlobal
		// 
		// Allocation of cv$var27$stateProbabilityGlobal for single threaded execution
		cv$var27$stateProbabilityGlobal = new double[weightings.length];
		
		// Allocation of guard$sample27bernoulli69$global for single threaded execution
		guard$sample27bernoulli69$global = new boolean[length$value];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If v2 has not been set already allocate space.
		if(!setFlag$v2)
			// Constructor for v2
			v2 = new int[(length$value + 1)];
		
		// Constructor for v3
		v3 = new int[(length$value + 1)];
		
		// Constructor for v
		v = new boolean[length$value];
		
		// Constructor for distribution$sample5
		distribution$sample5 = new double[weightings.length];
		
		// Constructor for distribution$sample11
		distribution$sample11 = new double[weightings.length];
		
		// Constructor for distribution$sample27
		distribution$sample27 = new double[length$value][];
		for(int i = 0; i < length$value; i += 1)
			distribution$sample27[i] = new double[weightings.length];
		
		// Constructor for logProbability$var26
		logProbability$var26 = new double[length$value];
		
		// Constructor for logProbability$sample27
		logProbability$sample27 = new double[length$value];
		
		// Constructor for logProbability$var69
		logProbability$var69 = new double[length$value];
		
		// Constructor for logProbability$sample70
		logProbability$sample70 = new double[length$value];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample11)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample27)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings, weightings.length);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((!fixedFlag$sample11 || !fixedFlag$sample27))
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, (size + 1), 1,
				(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int k = forStart$k; k < forEnd$k; k += 1)
							v3[k] = v2[k];
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, size, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int j = forStart$j; j < forEnd$j; j += 1)
						v[j] = DistributionSampling.sampleBernoulli(RNG$1, ((double)((v1 + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]));
			}
		);
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
		if(!fixedFlag$sample11) {
			for(int index$var10 = 0; index$var10 < weightings.length; index$var10 += 1)
				// Save the probability of each value
				// 
				// cv$distribution$sample11's comment
				// Create local copy of variable probabilities.
				distribution$sample11[index$var10] = weightings[index$var10];
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample27)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1) {
							// Create local copy of variable probabilities.
							double[] cv$distribution$sample27 = distribution$sample27[i];
							for(int index$var26 = 0; index$var26 < weightings.length; index$var26 += 1)
								// Save the probability of each value
								// 
								// Probability for this value
								cv$distribution$sample27[index$var26] = weightings[index$var26];
						}
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample11)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample27)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings, weightings.length);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((!fixedFlag$sample11 || !fixedFlag$sample27))
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, (size + 1), 1,
				(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int k = forStart$k; k < forEnd$k; k += 1)
							v3[k] = v2[k];
				}
			);

	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample5)
				sample5();
			if(!fixedFlag$sample11)
				sample11();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample27) {
				for(int i = 0; i < size; i += 1)
					sample27(i);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample27) {
				for(int i = (size - 1); i >= 0; i -= 1)
					sample27(i);
			}
			if(!fixedFlag$sample11)
				sample11();
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
		logProbability$var10 = 0.0;
		logProbability$v2 = 0.0;
		logProbability$v3 = 0.0;
		if(!fixedProbFlag$sample11)
			logProbability$var11 = 0.0;
		for(int i = 0; i < size; i += 1)
			logProbability$var26[i] = 0.0;
		if(!fixedProbFlag$sample27) {
			for(int i = 0; i < size; i += 1)
				logProbability$sample27[i] = 0.0;
		}
		for(int j = 0; j < size; j += 1)
			logProbability$var69[j] = 0.0;
		logProbability$v = 0.0;
		if(!fixedProbFlag$sample70) {
			for(int j = 0; j < size; j += 1)
				logProbability$sample70[j] = 0.0;
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
		logProbabilityValue$sample70();
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
		logProbabilityDistribution$sample70();
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
		logProbabilityValue$sample70();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample11)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample27)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings, weightings.length);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((!fixedFlag$sample11 || !fixedFlag$sample27))
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, (size + 1), 1,
				(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int k = forStart$k; k < forEnd$k; k += 1)
							v3[k] = v2[k];
				}
			);

		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = v.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			v[cv$index1] = value[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(setFlag$v2)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, (size + 1), 1,
				(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int k = forStart$k; k < forEnd$k; k += 1)
							v3[k] = v2[k];
				}
			);

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
		     + "model DistributionTest5(double[] weightings, boolean[] value) {\n"
		     + "    int size = value.length;\n"
		     + "    \n"
		     + "    int v1 = categorical(weightings).sampleDistribution();\n"
		     + "    \n"
		     + "    int[] v2 = new int[size + 1];\n"
		     + "    v2[0] = categorical(weightings).sampleDistribution();\n"
		     + "    for(int i:[0..size))\n"
		     + "        v2[i + 1] = categorical(weightings).sampleDistribution();\n"
		     + "        \n"
		     + "        \n"
		     + "    int[] v3 = new int[size + 1];\n"
		     + "    for(int k:[0..size]) \n"
		     + "        v3[k] = v2[k];\n"
		     + "        \n"
		     + "    boolean[] v = new boolean[size];\n"
		     + "    for(int j:[0..size))\n"
		     + "        v[j] = bernoulli(((1.0*v1) + v2[j] + v3[j+1])/v2[j+1]).sample();\n"
		     + "        \n"
		     + "    v.observe(value);\n"
		     + "}\n"
		     + "";
	}
}