package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DistributionTest5$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DistributionTest5$CoreInterface {
	
	// Declare the variables for the model.
	private double[] cv$var15$stateProbabilityGlobal;
	private double[] cv$var23$stateProbabilityGlobal;
	private double[] cv$var9$stateProbabilityGlobal;
	private double[] distribution$sample12;
	private double[] distribution$sample18;
	private double[][] distribution$sample26;
	private boolean fixedFlag$sample12 = false;
	private boolean fixedFlag$sample18 = false;
	private boolean fixedFlag$sample26 = false;
	private boolean fixedFlag$sample55 = false;
	private boolean fixedProbFlag$sample12 = false;
	private boolean fixedProbFlag$sample18 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean fixedProbFlag$sample55 = false;
	private boolean[] guard$sample18bernoulli54$global;
	private boolean[] guard$sample26bernoulli54$global;
	private int length$value;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$sample26;
	private double[] logProbability$sample55;
	private double logProbability$v;
	private double logProbability$v1;
	private double logProbability$v2;
	private double logProbability$v3;
	private double logProbability$var14;
	private double logProbability$var15;
	private double[] logProbability$var22;
	private double[] logProbability$var51;
	private double logProbability$var8;
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

	// Getter for fixedFlag$sample12.
	@Override
	public final boolean get$fixedFlag$sample12() {
		return fixedFlag$sample12;
	}

	// Setter for fixedFlag$sample12.
	@Override
	public final void set$fixedFlag$sample12(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample12 including if probabilities
		// need to be updated.
		fixedFlag$sample12 = cv$value;
		
		// Should the probability of sample 12 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample12" with its value "cv$value".
		fixedProbFlag$sample12 = (cv$value && fixedProbFlag$sample12);
		
		// Should the probability of sample 55 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample12" with its value "cv$value".
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
	}

	// Getter for fixedFlag$sample18.
	@Override
	public final boolean get$fixedFlag$sample18() {
		return fixedFlag$sample18;
	}

	// Setter for fixedFlag$sample18.
	@Override
	public final void set$fixedFlag$sample18(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample18 including if probabilities
		// need to be updated.
		fixedFlag$sample18 = cv$value;
		
		// Should the probability of sample 18 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample18" with its value "cv$value".
		fixedProbFlag$sample18 = (cv$value && fixedProbFlag$sample18);
		
		// Should the probability of sample 55 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample18" with its value "cv$value".
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
	}

	// Getter for fixedFlag$sample26.
	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	// Setter for fixedFlag$sample26.
	@Override
	public final void set$fixedFlag$sample26(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample26 including if probabilities
		// need to be updated.
		fixedFlag$sample26 = cv$value;
		
		// Should the probability of sample 26 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample26" with its value "cv$value".
		fixedProbFlag$sample26 = (cv$value && fixedProbFlag$sample26);
		
		// Should the probability of sample 55 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample26" with its value "cv$value".
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
	}

	// Getter for fixedFlag$sample55.
	@Override
	public final boolean get$fixedFlag$sample55() {
		return fixedFlag$sample55;
	}

	// Setter for fixedFlag$sample55.
	@Override
	public final void set$fixedFlag$sample55(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample55 including if probabilities
		// need to be updated.
		fixedFlag$sample55 = cv$value;
		
		// Should the probability of sample 55 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample55" with its value "cv$value".
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
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

	// Setter for v.
	@Override
	public final void set$v(boolean[] cv$value) {
		// Set flags for all the side effects of v including if probabilities need to be updated.
		// Set v with flag to mark that it has been set so another array doesn't need to be
		// constructed
		v = cv$value;
		setFlag$v = true;
		
		// Unset the fixed probability flag for sample 55 as it depends on v.
		fixedProbFlag$sample55 = false;
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
		
		// Unset the fixed probability flag for sample 12 as it depends on v1.
		fixedProbFlag$sample12 = false;
		
		// Unset the fixed probability flag for sample 55 as it depends on v1.
		fixedProbFlag$sample55 = false;
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
		
		// Unset the fixed probability flag for sample 18 as it depends on v2.
		fixedProbFlag$sample18 = false;
		
		// Unset the fixed probability flag for sample 26 as it depends on v2.
		fixedProbFlag$sample26 = false;
		
		// Unset the fixed probability flag for sample 55 as it depends on v2.
		fixedProbFlag$sample55 = false;
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

	// Calculate the probability of the samples represented by sample12 using probability
	// distributions.
	private final void logProbabilityDistribution$sample12() {
		// Determine if we need to calculate the values for sample task 12 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample12) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample12) {
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
				logProbability$var8 = cv$distributionAccumulator;
				
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
				// Substituted "fixedFlag$sample12" with its value "true".
				fixedProbFlag$sample12 = true;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var8 = logProbability$v1;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$v1);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample12)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$v1);
		}
	}

	// Calculate the probability of the samples represented by sample18 using probability
	// distributions.
	private final void logProbabilityDistribution$sample18() {
		// Determine if we need to calculate the values for sample task 18 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample18) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample18) {
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
				logProbability$var14 = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$var15 = cv$distributionAccumulator;
				
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
				if((fixedFlag$sample26 && (0 <= size)))
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
				// Substituted "fixedFlag$sample18" with its value "true".
				fixedProbFlag$sample18 = true;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var14 = logProbability$var15;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(fixedFlag$sample18) {
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$v2 = (logProbability$v2 + logProbability$var15);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((fixedFlag$sample26 && (0 <= size)))
					// Update the variable probability
					// 
					// Variable declaration of cv$accumulator moved.
					logProbability$v3 = (logProbability$v3 + logProbability$var15);
			}
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var15);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample18)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var15);
		}
	}

	// Calculate the probability of the samples represented by sample26 using probability
	// distributions.
	private final void logProbabilityDistribution$sample26() {
		// Determine if we need to calculate the values for sample task 26 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample26) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample26) {
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
					logProbability$var22[i] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample26[i] = cv$distributionAccumulator;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(fixedFlag$sample18)
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
				// Substituted "fixedFlag$sample26" with its value "true".
				fixedProbFlag$sample26 = true;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				double cv$sampleValue = logProbability$sample26[i];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var22[i] = cv$sampleValue;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((fixedFlag$sample18 && fixedFlag$sample26))
					// Update the variable probability
					logProbability$v3 = (logProbability$v3 + cv$sampleValue);
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample26)
				// Update the variable probability
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample55 using probability
	// distributions.
	private final void logProbabilityDistribution$sample55() {
		// Determine if we need to calculate the values for sample task 55 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample55) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 55 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				boolean cv$sampleValue = v[j];
				
				// Enumerating the possible arguments for Bernoulli 51.
				if((0 == j)) {
					// Enumerating the possible arguments for Bernoulli 51.
					if(fixedFlag$sample12) {
						if(fixedFlag$sample18) {
							if(fixedFlag$sample26) {
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
								// Enumerating the possible outputs of Categorical 22.
								for(int index$sample26$318 = 0; index$sample26$318 < weightings.length; index$sample26$318 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "i" with its value "0".
									double cv$probabilitySample26Value319 = distribution$sample26[0][index$sample26$318];
									
									// Store the value of the function call, so the function call is only made once.
									// 
									// Substituted "j" with its value "0".
									double cv$weightedProbability = (Math.log(cv$probabilitySample26Value319) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[0]) + index$sample26$318) / index$sample26$318)));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value319);
								}
							}
						} else {
							// Enumerating the possible outputs of Categorical 14.
							for(int index$sample18$307 = 0; index$sample18$307 < weightings.length; index$sample18$307 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample18Value308 = distribution$sample18[index$sample18$307];
								if(fixedFlag$sample26) {
									// Store the value of the function call, so the function call is only made once.
									// 
									// Substituted "j" with its value "0".
									// 
									// Substituted "k" with its value "1".
									double cv$weightedProbability = (Math.log(cv$probabilitySample18Value308) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + index$sample18$307) + v2[1]) / v2[1])));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value308);
								} else {
									// Enumerating the possible outputs of Categorical 22.
									for(int index$sample26$324 = 0; index$sample26$324 < weightings.length; index$sample26$324 += 1) {
										// Update the probability of sampling this value from the distribution value.
										// 
										// Substituted "i" with its value "0".
										double cv$probabilitySample26Value325 = (cv$probabilitySample18Value308 * distribution$sample26[0][index$sample26$324]);
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(cv$probabilitySample26Value325) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + index$sample18$307) + index$sample26$324) / index$sample26$324)));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value325);
									}
								}
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 8.
						for(int index$sample12$302 = 0; index$sample12$302 < weightings.length; index$sample12$302 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample12Value303 = distribution$sample12[index$sample12$302];
							if(fixedFlag$sample18) {
								if(fixedFlag$sample26) {
									// Store the value of the function call, so the function call is only made once.
									// 
									// Substituted "j" with its value "0".
									// 
									// Substituted "k" with its value "1".
									double cv$weightedProbability = (Math.log(cv$probabilitySample12Value303) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$302 + v2[0]) + v2[1]) / v2[1])));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample12Value303);
								} else {
									// Enumerating the possible outputs of Categorical 22.
									for(int index$sample26$330 = 0; index$sample26$330 < weightings.length; index$sample26$330 += 1) {
										// Update the probability of sampling this value from the distribution value.
										// 
										// Substituted "i" with its value "0".
										double cv$probabilitySample26Value331 = (cv$probabilitySample12Value303 * distribution$sample26[0][index$sample26$330]);
										
										// Store the value of the function call, so the function call is only made once.
										// 
										// Substituted "j" with its value "0".
										double cv$weightedProbability = (Math.log(cv$probabilitySample26Value331) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$302 + v2[0]) + index$sample26$330) / index$sample26$330)));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value331);
									}
								}
							} else {
								// Enumerating the possible outputs of Categorical 14.
								for(int index$sample18$312 = 0; index$sample18$312 < weightings.length; index$sample18$312 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample18Value313 = (cv$probabilitySample12Value303 * distribution$sample18[index$sample18$312]);
									if(fixedFlag$sample26) {
										// Store the value of the function call, so the function call is only made once.
										// 
										// Substituted "j" with its value "0".
										// 
										// Substituted "k" with its value "1".
										double cv$weightedProbability = (Math.log(cv$probabilitySample18Value313) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$302 + index$sample18$312) + v2[1]) / v2[1])));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value313);
									} else {
										// Enumerating the possible outputs of Categorical 22.
										for(int index$sample26$336 = 0; index$sample26$336 < weightings.length; index$sample26$336 += 1) {
											// Update the probability of sampling this value from the distribution value.
											// 
											// Substituted "i" with its value "0".
											double cv$probabilitySample26Value337 = (cv$probabilitySample18Value313 * distribution$sample26[0][index$sample26$336]);
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(cv$probabilitySample26Value337) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$302 + index$sample18$312) + index$sample26$336) / index$sample26$336)));
											
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
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value337);
										}
									}
								}
							}
						}
					}
				}
				
				// Enumerating the possible arguments for Bernoulli 51.
				if(fixedFlag$sample12) {
					if(fixedFlag$sample26) {
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
							// Enumerating the possible outputs of Categorical 22.
							for(int index$sample26$440 = 0; index$sample26$440 < weightings.length; index$sample26$440 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample26Value441 = distribution$sample26[i][index$sample26$440];
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
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
									double cv$weightedProbability = (Math.log(cv$probabilitySample26Value441) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + index$sample26$440) + index$sample26$440) / index$sample26$440)));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value441);
								}
								
								// Substituted "index$i$452" with its value "j".
								else {
									// Enumerating the possible outputs of Categorical 22.
									for(int index$sample26$453 = 0; index$sample26$453 < weightings.length; index$sample26$453 += 1) {
										// Update the probability of sampling this value from the distribution value.
										// 
										// Substituted "index$i$452" with its value "j".
										double cv$probabilitySample26Value454 = (cv$probabilitySample26Value441 * distribution$sample26[j][index$sample26$453]);
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(cv$probabilitySample26Value454) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + index$sample26$440) + index$sample26$453) / index$sample26$453)));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value454);
									}
								}
							}
						}
					}
				} else {
					// Enumerating the possible outputs of Categorical 8.
					for(int index$sample12$434 = 0; index$sample12$434 < weightings.length; index$sample12$434 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample12Value435 = distribution$sample12[index$sample12$434];
						if(fixedFlag$sample26) {
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
										double cv$weightedProbability = (Math.log(cv$probabilitySample12Value435) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$434 + v2[j]) + v2[k]) / v2[(j + 1)])));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample12Value435);
									}
								}
							}
						} else {
							int i = (j - 1);
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((0 <= i)) {
								// Enumerating the possible outputs of Categorical 22.
								for(int index$sample26$446 = 0; index$sample26$446 < weightings.length; index$sample26$446 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample26Value447 = (cv$probabilitySample12Value435 * distribution$sample26[i][index$sample26$446]);
									
									// Constraints moved from conditionals in inner loops/scopes/etc.
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
										double cv$weightedProbability = (Math.log(cv$probabilitySample26Value447) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$434 + index$sample26$446) + index$sample26$446) / index$sample26$446)));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value447);
									}
									
									// Substituted "index$i$459" with its value "j".
									else {
										// Enumerating the possible outputs of Categorical 22.
										for(int index$sample26$460 = 0; index$sample26$460 < weightings.length; index$sample26$460 += 1) {
											// Update the probability of sampling this value from the distribution value.
											// 
											// Substituted "index$i$459" with its value "j".
											double cv$probabilitySample26Value461 = (cv$probabilitySample26Value447 * distribution$sample26[j][index$sample26$460]);
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(cv$probabilitySample26Value461) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$434 + index$sample26$446) + index$sample26$460) / index$sample26$460)));
											
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
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value461);
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
				logProbability$var51[j] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample55[j] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$v = (logProbability$v + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample55 = (((fixedFlag$sample55 && fixedFlag$sample12) && fixedFlag$sample18) && fixedFlag$sample26);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample55[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var51[j] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$v = (logProbability$v + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample12 using sampled
	// values.
	private final void logProbabilityValue$sample12() {
		// Determine if we need to calculate the values for sample task 12 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample12) {
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
			logProbability$var8 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample12)
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
			fixedProbFlag$sample12 = fixedFlag$sample12;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var8 = logProbability$v1;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$v1);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample12)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$v1);
		}
	}

	// Calculate the probability of the samples represented by sample18 using sampled
	// values.
	private final void logProbabilityValue$sample18() {
		// Determine if we need to calculate the values for sample task 18 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample18) {
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
			logProbability$var14 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var15 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample18)
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
			fixedProbFlag$sample18 = fixedFlag$sample18;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var14 = logProbability$var15;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$v2 = (logProbability$v2 + logProbability$var15);
			
			// Guard to ensure that v3 is only updated once for this probability.
			if((0 <= size))
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$v3 = (logProbability$v3 + logProbability$var15);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var15);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample18)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var15);
		}
	}

	// Calculate the probability of the samples represented by sample26 using sampled
	// values.
	private final void logProbabilityValue$sample26() {
		// Determine if we need to calculate the values for sample task 26 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample26) {
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
				logProbability$var22[i] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample26[i] = cv$distributionAccumulator;
				
				// Update the variable probability
				logProbability$v3 = (logProbability$v3 + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample26 = fixedFlag$sample26;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				double cv$sampleValue = logProbability$sample26[i];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var22[i] = cv$sampleValue;
				
				// Update the variable probability
				logProbability$v3 = (logProbability$v3 + cv$sampleValue);
			}
			
			// Update the variable probability
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample26)
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
				logProbability$var51[j] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample55[j] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$v = (logProbability$v + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample55 = (((fixedFlag$sample55 && fixedFlag$sample12) && fixedFlag$sample18) && fixedFlag$sample26);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample55[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var51[j] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$v = (logProbability$v + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 12 drawn from Categorical 8. Inference was performed using variable
	// marginalization.
	private final void sample12() {
		// Variable declaration of cv$noStates moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		int cv$noStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$weightings" with its value "weightings".
			double cv$accumulatedProbabilities = Math.log(weightings[cv$valuePos]);
			
			// Processing random variable 51.
			for(int j = 0; j < size; j += 1) {
				// Processing sample task 55 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Bernoulli 51 which is consuming
				// the output of Sample task 12.
				if((0 == j)) {
					// Enumerating the possible arguments for the variable Bernoulli 51 which is consuming
					// the output of Sample task 12.
					if(fixedFlag$sample18) {
						if(fixedFlag$sample26) {
							// Substituted "j" with its value "0".
							// 
							// Substituted "cv$temp$31$var50" with its value "var50".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "j" with its value "0".
							// 
							// Value of the variable at this index
							// 
							// Substituted "k" with its value "1".
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((cv$valuePos + v2[0]) + v2[1]) / v2[1]));
							
							// Recorded the probability of reaching sample task 55 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							// Enumerating the possible outputs of Categorical 22.
							for(int index$sample26$147 = 0; index$sample26$147 < weightings.length; index$sample26$147 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "i" with its value "0".
								double cv$probabilitySample26Value148 = distribution$sample26[0][index$sample26$147];
								
								// Constructing a random variable input for use later.
								// 
								// Substituted "j" with its value "0".
								// 
								// Value of the variable at this index
								double var50 = ((double)((cv$valuePos + v2[0]) + index$sample26$147) / index$sample26$147);
								
								// Record the probability of sample task 55 generating output with current configuration.
								// 
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$32$var50" with its value "var50".
								if(((Math.log(cv$probabilitySample26Value148) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$32$var50" with its value "var50".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value148) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$32$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value148) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									else
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$32$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value148) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample26Value148)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
								}
								
								// Recorded the probability of reaching sample task 55 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value148);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 14.
						for(int index$sample18$141 = 0; index$sample18$141 < weightings.length; index$sample18$141 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample18Value142 = distribution$sample18[index$sample18$141];
							if(fixedFlag$sample26) {
								// Constructing a random variable input for use later.
								// 
								// Substituted "j" with its value "0".
								// 
								// Value of the variable at this index
								// 
								// Substituted "k" with its value "1".
								double var50 = ((double)((cv$valuePos + index$sample18$141) + v2[1]) / v2[1]);
								
								// Record the probability of sample task 55 generating output with current configuration.
								// 
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$34$var50" with its value "var50".
								if(((Math.log(cv$probabilitySample18Value142) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$34$var50" with its value "var50".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value142) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$34$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value142) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									else
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$34$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value142) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample18Value142)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
								}
								
								// Recorded the probability of reaching sample task 55 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value142);
							} else {
								// Enumerating the possible outputs of Categorical 22.
								for(int index$sample26$153 = 0; index$sample26$153 < weightings.length; index$sample26$153 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "i" with its value "0".
									double cv$probabilitySample26Value154 = (cv$probabilitySample18Value142 * distribution$sample26[0][index$sample26$153]);
									
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var50 = ((double)((cv$valuePos + index$sample18$141) + index$sample26$153) / index$sample26$153);
									
									// Record the probability of sample task 55 generating output with current configuration.
									// 
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$35$var50" with its value "var50".
									if(((Math.log(cv$probabilitySample26Value154) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$35$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value154) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "j" with its value "0".
											// 
											// Substituted "cv$temp$35$var50" with its value "var50".
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value154) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
										else
											// Substituted "j" with its value "0".
											// 
											// Substituted "cv$temp$35$var50" with its value "var50".
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value154) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample26Value154)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									}
									
									// Recorded the probability of reaching sample task 55 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value154);
								}
							}
						}
					}
				}
				
				// Enumerating the possible arguments for the variable Bernoulli 51 which is consuming
				// the output of Sample task 12.
				if(fixedFlag$sample26) {
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
								// Variable declaration of cv$temp$43$var50 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$43$var50 = ((double)((cv$valuePos + v2[j]) + v2[k]) / v2[(j + 1)]);
								
								// Record the probability of sample task 55 generating output with current configuration.
								if((DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50);
									else
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50))) + 1)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50));
								}
								
								// Recorded the probability of reaching sample task 55 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
							}
						}
					}
				} else {
					int i = (j - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 <= i)) {
						// Enumerating the possible outputs of Categorical 22.
						for(int index$sample26$203 = 0; index$sample26$203 < weightings.length; index$sample26$203 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample26Value204 = distribution$sample26[i][index$sample26$203];
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
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
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var50 = ((double)((cv$valuePos + index$sample26$203) + index$sample26$203) / index$sample26$203);
								
								// Record the probability of sample task 55 generating output with current configuration.
								// 
								// Substituted "cv$temp$44$var50" with its value "var50".
								if(((Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], var50)) < cv$accumulatedConsumerProbabilities))
									// Substituted "cv$temp$44$var50" with its value "var50".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "cv$temp$44$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], var50));
									else
										// Substituted "cv$temp$44$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], var50)))) + 1)) + Math.log(cv$probabilitySample26Value204)) + DistributionSampling.logProbabilityBernoulli(v[j], var50));
								}
								
								// Recorded the probability of reaching sample task 55 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value204);
							}
							
							// Substituted "index$i$209" with its value "j".
							else {
								// Enumerating the possible outputs of Categorical 22.
								for(int index$sample26$210 = 0; index$sample26$210 < weightings.length; index$sample26$210 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "index$i$209" with its value "j".
									double cv$probabilitySample26Value211 = (cv$probabilitySample26Value204 * distribution$sample26[j][index$sample26$210]);
									
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var50 = ((double)((cv$valuePos + index$sample26$203) + index$sample26$210) / index$sample26$210);
									
									// Record the probability of sample task 55 generating output with current configuration.
									// 
									// Substituted "cv$temp$47$var50" with its value "var50".
									if(((Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], var50)) < cv$accumulatedConsumerProbabilities))
										// Substituted "cv$temp$47$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "cv$temp$47$var50" with its value "var50".
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], var50));
										else
											// Substituted "cv$temp$47$var50" with its value "var50".
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], var50)))) + 1)) + Math.log(cv$probabilitySample26Value211)) + DistributionSampling.logProbabilityBernoulli(v[j], var50));
									}
									
									// Recorded the probability of reaching sample task 55 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value211);
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
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
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
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
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
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				// Set the calculated probabilities to be the distribution values, and normalize
				// 
				// Local copy of the probability array
				distribution$sample12[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				// Set the calculated probabilities to be the distribution values, and normalize
				// 
				// Local copy of the probability array
				distribution$sample12[cv$indexName] = Math.exp((cv$var9$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var9$stateProbabilityGlobal.length; cv$indexName += 1)
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample12[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 18 drawn from Categorical 14. Inference was performed using variable
	// marginalization.
	private final void sample18() {
		// Variable declaration of cv$noStates moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		int cv$noStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
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
				guard$sample18bernoulli54$global[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample18bernoulli54$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample18bernoulli54$global[0] = true;
					
					// Processing sample task 55 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Bernoulli 51 which is consuming
					// the output of Sample task 18.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 51 which is consuming
					// the output of Sample task 18.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 51 which is consuming
					// the output of Sample task 18.
					if(fixedFlag$sample12) {
						if(fixedFlag$sample26) {
							// Substituted "j" with its value "0".
							// 
							// Substituted "cv$temp$27$var50" with its value "var50".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "j" with its value "0".
							// 
							// Value of the variable at this index
							// 
							// Substituted "k" with its value "1".
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((v1 + cv$valuePos) + v2[1]) / v2[1]));
							
							// Recorded the probability of reaching sample task 55 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							// Enumerating the possible outputs of Categorical 22.
							for(int index$sample26$130 = 0; index$sample26$130 < weightings.length; index$sample26$130 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "i" with its value "0".
								double cv$probabilitySample26Value131 = distribution$sample26[0][index$sample26$130];
								
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var50 = ((double)((v1 + cv$valuePos) + index$sample26$130) / index$sample26$130);
								
								// Record the probability of sample task 55 generating output with current configuration.
								// 
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$28$var50" with its value "var50".
								if(((Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$28$var50" with its value "var50".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$28$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									else
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$28$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample26Value131)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
								}
								
								// Recorded the probability of reaching sample task 55 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value131);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 8.
						for(int index$sample12$124 = 0; index$sample12$124 < weightings.length; index$sample12$124 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample12Value125 = distribution$sample12[index$sample12$124];
							if(fixedFlag$sample26) {
								// Constructing a random variable input for use later.
								// 
								// Substituted "j" with its value "0".
								// 
								// Value of the variable at this index
								// 
								// Substituted "k" with its value "1".
								double var50 = ((double)((index$sample12$124 + cv$valuePos) + v2[1]) / v2[1]);
								
								// Record the probability of sample task 55 generating output with current configuration.
								// 
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$30$var50" with its value "var50".
								if(((Math.log(cv$probabilitySample12Value125) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$30$var50" with its value "var50".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value125) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$30$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value125) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									else
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$30$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value125) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample12Value125)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
								}
								
								// Recorded the probability of reaching sample task 55 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value125);
							} else {
								// Enumerating the possible outputs of Categorical 22.
								for(int index$sample26$136 = 0; index$sample26$136 < weightings.length; index$sample26$136 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "i" with its value "0".
									double cv$probabilitySample26Value137 = (cv$probabilitySample12Value125 * distribution$sample26[0][index$sample26$136]);
									
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var50 = ((double)((index$sample12$124 + cv$valuePos) + index$sample26$136) / index$sample26$136);
									
									// Record the probability of sample task 55 generating output with current configuration.
									// 
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$31$var50" with its value "var50".
									if(((Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$31$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "j" with its value "0".
											// 
											// Substituted "cv$temp$31$var50" with its value "var50".
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
										else
											// Substituted "j" with its value "0".
											// 
											// Substituted "cv$temp$31$var50" with its value "var50".
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample26Value137)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									}
									
									// Recorded the probability of reaching sample task 55 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value137);
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
			cv$var15$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
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
		double cv$lseMax = cv$var15$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var15$stateProbabilityGlobal[cv$lseIndex];
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
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var15$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				// Set the calculated probabilities to be the distribution values, and normalize
				// 
				// Local copy of the probability array
				distribution$sample18[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				// Set the calculated probabilities to be the distribution values, and normalize
				// 
				// Local copy of the probability array
				distribution$sample18[cv$indexName] = Math.exp((cv$var15$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var15$stateProbabilityGlobal.length; cv$indexName += 1)
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample18[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 26 drawn from Categorical 22. Inference was performed using variable
	// marginalization.
	private final void sample26(int i) {
		// Variable declaration of cv$noStates moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		int cv$noStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
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
					guard$sample26bernoulli54$global[j] = false;
			}
			
			// Set the flags to false
			// 
			// Guard to check that at most one copy of the code is executed for a given random
			// variable instance.
			// 
			// j's comment
			// Substituted "k" with its value "(i + 1)".
			guard$sample26bernoulli54$global[i] = false;
			int j = (i + 1);
			if(((j < size) && !guard$sample26bernoulli54$global[j])) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample26bernoulli54$global[j] = true;
				
				// Processing sample task 55 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Bernoulli 51 which is consuming
				// the output of Sample task 26.
				if(fixedFlag$sample12) {
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
						// Substituted "cv$temp$23$var50" with its value "var50".
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[i], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
						
						// Recorded the probability of reaching sample task 55 with the current configuration.
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
						// Enumerating the possible outputs of Categorical 22.
						for(int index$sample26$122 = 0; index$sample26$122 < weightings.length; index$sample26$122 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$i$121" with its value "j".
							double cv$probabilitySample26Value123 = distribution$sample26[j][index$sample26$122];
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var50 = ((double)((v1 + cv$valuePos) + index$sample26$122) / index$sample26$122);
							
							// Record the probability of sample task 55 generating output with current configuration.
							// 
							// Substituted "cv$temp$26$var50" with its value "var50".
							if(((Math.log(cv$probabilitySample26Value123) + DistributionSampling.logProbabilityBernoulli(v[j], var50)) < cv$accumulatedConsumerProbabilities))
								// Substituted "cv$temp$26$var50" with its value "var50".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value123) + DistributionSampling.logProbabilityBernoulli(v[j], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "cv$temp$26$var50" with its value "var50".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value123) + DistributionSampling.logProbabilityBernoulli(v[j], var50));
								else
									// Substituted "cv$temp$26$var50" with its value "var50".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value123) + DistributionSampling.logProbabilityBernoulli(v[j], var50)))) + 1)) + Math.log(cv$probabilitySample26Value123)) + DistributionSampling.logProbabilityBernoulli(v[j], var50));
							}
							
							// Recorded the probability of reaching sample task 55 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value123);
						}
					}
				}
				
				// Enumerating the possible arguments for the variable Bernoulli 51 which is consuming
				// the output of Sample task 26.
				// 
				// Enumerating the possible arguments for the variable Bernoulli 51 which is consuming
				// the output of Sample task 26.
				else {
					// Enumerating the possible outputs of Categorical 8.
					for(int index$sample12$116 = 0; index$sample12$116 < weightings.length; index$sample12$116 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample12Value117 = distribution$sample12[index$sample12$116];
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((i == j)) {
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var50 = ((double)((index$sample12$116 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 55 generating output with current configuration.
							// 
							// Substituted "cv$temp$28$var50" with its value "var50".
							if(((Math.log(cv$probabilitySample12Value117) + DistributionSampling.logProbabilityBernoulli(v[i], var50)) < cv$accumulatedConsumerProbabilities))
								// Substituted "cv$temp$28$var50" with its value "var50".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value117) + DistributionSampling.logProbabilityBernoulli(v[i], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "cv$temp$28$var50" with its value "var50".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value117) + DistributionSampling.logProbabilityBernoulli(v[i], var50));
								else
									// Substituted "cv$temp$28$var50" with its value "var50".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value117) + DistributionSampling.logProbabilityBernoulli(v[i], var50)))) + 1)) + Math.log(cv$probabilitySample12Value117)) + DistributionSampling.logProbabilityBernoulli(v[i], var50));
							}
							
							// Recorded the probability of reaching sample task 55 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value117);
						}
						
						// index$i$2's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "index$i$127" with its value "j".
						else {
							// Enumerating the possible outputs of Categorical 22.
							for(int index$sample26$128 = 0; index$sample26$128 < weightings.length; index$sample26$128 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$i$127" with its value "j".
								double cv$probabilitySample26Value129 = (cv$probabilitySample12Value117 * distribution$sample26[j][index$sample26$128]);
								
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var50 = ((double)((index$sample12$116 + cv$valuePos) + index$sample26$128) / index$sample26$128);
								
								// Record the probability of sample task 55 generating output with current configuration.
								// 
								// Substituted "cv$temp$31$var50" with its value "var50".
								if(((Math.log(cv$probabilitySample26Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var50)) < cv$accumulatedConsumerProbabilities))
									// Substituted "cv$temp$31$var50" with its value "var50".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "cv$temp$31$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var50));
									else
										// Substituted "cv$temp$31$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var50)))) + 1)) + Math.log(cv$probabilitySample26Value129)) + DistributionSampling.logProbabilityBernoulli(v[j], var50));
								}
								
								// Recorded the probability of reaching sample task 55 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value129);
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
			if(!guard$sample26bernoulli54$global[i]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample26bernoulli54$global[i] = true;
				
				// Processing sample task 55 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Bernoulli 51 which is consuming
				// the output of Sample task 26.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == i)) {
					// Enumerating the possible arguments for the variable Bernoulli 51 which is consuming
					// the output of Sample task 26.
					if(fixedFlag$sample12) {
						if(fixedFlag$sample18) {
							// Substituted "j" with its value "i".
							// 
							// Substituted "cv$temp$39$var50" with its value "var50".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "j" with its value "i".
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((v1 + v2[0]) + cv$valuePos) / cv$valuePos));
							
							// Recorded the probability of reaching sample task 55 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							// Enumerating the possible outputs of Categorical 14.
							for(int index$sample18$191 = 0; index$sample18$191 < weightings.length; index$sample18$191 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample18Value192 = distribution$sample18[index$sample18$191];
								
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var50 = ((double)((v1 + index$sample18$191) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 55 generating output with current configuration.
								// 
								// Substituted "j" with its value "i".
								// 
								// Substituted "cv$temp$41$var50" with its value "var50".
								if(((Math.log(cv$probabilitySample18Value192) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "i".
									// 
									// Substituted "cv$temp$41$var50" with its value "var50".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value192) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "i".
										// 
										// Substituted "cv$temp$41$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value192) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									else
										// Substituted "j" with its value "i".
										// 
										// Substituted "cv$temp$41$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value192) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample18Value192)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
								}
								
								// Recorded the probability of reaching sample task 55 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value192);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 8.
						for(int index$sample12$186 = 0; index$sample12$186 < weightings.length; index$sample12$186 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample12Value187 = distribution$sample12[index$sample12$186];
							if(fixedFlag$sample18) {
								// Constructing a random variable input for use later.
								// 
								// Substituted "j" with its value "i".
								// 
								// Value of the variable at this index
								double var50 = ((double)((index$sample12$186 + v2[0]) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 55 generating output with current configuration.
								// 
								// Substituted "j" with its value "i".
								// 
								// Substituted "cv$temp$43$var50" with its value "var50".
								if(((Math.log(cv$probabilitySample12Value187) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "i".
									// 
									// Substituted "cv$temp$43$var50" with its value "var50".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value187) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "i".
										// 
										// Substituted "cv$temp$43$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value187) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									else
										// Substituted "j" with its value "i".
										// 
										// Substituted "cv$temp$43$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value187) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample12Value187)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
								}
								
								// Recorded the probability of reaching sample task 55 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value187);
							} else {
								// Enumerating the possible outputs of Categorical 14.
								for(int index$sample18$196 = 0; index$sample18$196 < weightings.length; index$sample18$196 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample18Value197 = (cv$probabilitySample12Value187 * distribution$sample18[index$sample18$196]);
									
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var50 = ((double)((index$sample12$186 + index$sample18$196) + cv$valuePos) / cv$valuePos);
									
									// Record the probability of sample task 55 generating output with current configuration.
									// 
									// Substituted "j" with its value "i".
									// 
									// Substituted "cv$temp$45$var50" with its value "var50".
									if(((Math.log(cv$probabilitySample18Value197) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
										// Substituted "j" with its value "i".
										// 
										// Substituted "cv$temp$45$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value197) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "j" with its value "i".
											// 
											// Substituted "cv$temp$45$var50" with its value "var50".
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value197) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
										else
											// Substituted "j" with its value "i".
											// 
											// Substituted "cv$temp$45$var50" with its value "var50".
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value197) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample18Value197)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									}
									
									// Recorded the probability of reaching sample task 55 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value197);
								}
							}
						}
					}
				}
				
				// Enumerating the possible arguments for the variable Bernoulli 51 which is consuming
				// the output of Sample task 26.
				if(fixedFlag$sample12) {
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
						// Enumerating the possible outputs of Categorical 22.
						for(int index$sample26$268 = 0; index$sample26$268 < weightings.length; index$sample26$268 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample26Value269 = distribution$sample26[index$i$267][index$sample26$268];
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var50 = ((double)((v1 + index$sample26$268) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 55 generating output with current configuration.
							// 
							// Substituted "j" with its value "i".
							// 
							// Substituted "cv$temp$57$var50" with its value "var50".
							if(((Math.log(cv$probabilitySample26Value269) + DistributionSampling.logProbabilityBernoulli(v[i], var50)) < cv$accumulatedConsumerProbabilities))
								// Substituted "j" with its value "i".
								// 
								// Substituted "cv$temp$57$var50" with its value "var50".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value269) + DistributionSampling.logProbabilityBernoulli(v[i], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "j" with its value "i".
									// 
									// Substituted "cv$temp$57$var50" with its value "var50".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value269) + DistributionSampling.logProbabilityBernoulli(v[i], var50));
								else
									// Substituted "j" with its value "i".
									// 
									// Substituted "cv$temp$57$var50" with its value "var50".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value269) + DistributionSampling.logProbabilityBernoulli(v[i], var50)))) + 1)) + Math.log(cv$probabilitySample26Value269)) + DistributionSampling.logProbabilityBernoulli(v[i], var50));
							}
							
							// Recorded the probability of reaching sample task 55 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value269);
						}
					}
				} else {
					// Enumerating the possible outputs of Categorical 8.
					for(int index$sample12$262 = 0; index$sample12$262 < weightings.length; index$sample12$262 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample12Value263 = distribution$sample12[index$sample12$262];
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
							// Enumerating the possible outputs of Categorical 22.
							for(int index$sample26$274 = 0; index$sample26$274 < weightings.length; index$sample26$274 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample26Value275 = (cv$probabilitySample12Value263 * distribution$sample26[index$i$273][index$sample26$274]);
								
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var50 = ((double)((index$sample12$262 + index$sample26$274) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 55 generating output with current configuration.
								// 
								// Substituted "j" with its value "i".
								// 
								// Substituted "cv$temp$62$var50" with its value "var50".
								if(((Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[i], var50)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "i".
									// 
									// Substituted "cv$temp$62$var50" with its value "var50".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[i], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "i".
										// 
										// Substituted "cv$temp$62$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[i], var50));
									else
										// Substituted "j" with its value "i".
										// 
										// Substituted "cv$temp$62$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[i], var50)))) + 1)) + Math.log(cv$probabilitySample26Value275)) + DistributionSampling.logProbabilityBernoulli(v[i], var50));
								}
								
								// Recorded the probability of reaching sample task 55 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value275);
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
			if(!guard$sample26bernoulli54$global[i]) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				// 
				// j's comment
				// Substituted "k" with its value "(i + 1)".
				guard$sample26bernoulli54$global[i] = true;
				
				// Processing sample task 55 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Bernoulli 51 which is consuming
				// the output of Sample task 26.
				// 
				// j's comment
				// Substituted "k" with its value "(i + 1)".
				if((0 == i)) {
					// Enumerating the possible arguments for the variable Bernoulli 51 which is consuming
					// the output of Sample task 26.
					if(fixedFlag$sample12) {
						if(fixedFlag$sample18) {
							// Substituted "j" with its value "0".
							// 
							// Substituted "cv$temp$79$var50" with its value "var50".
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "j" with its value "0".
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((v1 + v2[0]) + cv$valuePos) / cv$valuePos));
							
							// Recorded the probability of reaching sample task 55 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							// Enumerating the possible outputs of Categorical 14.
							for(int index$sample18$374 = 0; index$sample18$374 < weightings.length; index$sample18$374 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample18Value375 = distribution$sample18[index$sample18$374];
								
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var50 = ((double)((v1 + index$sample18$374) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 55 generating output with current configuration.
								// 
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$81$var50" with its value "var50".
								if(((Math.log(cv$probabilitySample18Value375) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$81$var50" with its value "var50".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value375) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$81$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value375) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									else
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$81$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value375) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample18Value375)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
								}
								
								// Recorded the probability of reaching sample task 55 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value375);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 8.
						for(int index$sample12$369 = 0; index$sample12$369 < weightings.length; index$sample12$369 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample12Value370 = distribution$sample12[index$sample12$369];
							if(fixedFlag$sample18) {
								// Constructing a random variable input for use later.
								// 
								// Substituted "j" with its value "0".
								// 
								// Value of the variable at this index
								double var50 = ((double)((index$sample12$369 + v2[0]) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 55 generating output with current configuration.
								// 
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$83$var50" with its value "var50".
								if(((Math.log(cv$probabilitySample12Value370) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$83$var50" with its value "var50".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value370) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$83$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value370) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									else
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$83$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value370) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample12Value370)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
								}
								
								// Recorded the probability of reaching sample task 55 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value370);
							} else {
								// Enumerating the possible outputs of Categorical 14.
								for(int index$sample18$379 = 0; index$sample18$379 < weightings.length; index$sample18$379 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample18Value380 = (cv$probabilitySample12Value370 * distribution$sample18[index$sample18$379]);
									
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var50 = ((double)((index$sample12$369 + index$sample18$379) + cv$valuePos) / cv$valuePos);
									
									// Record the probability of sample task 55 generating output with current configuration.
									// 
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$85$var50" with its value "var50".
									if(((Math.log(cv$probabilitySample18Value380) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$85$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value380) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "j" with its value "0".
											// 
											// Substituted "cv$temp$85$var50" with its value "var50".
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value380) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
										else
											// Substituted "j" with its value "0".
											// 
											// Substituted "cv$temp$85$var50" with its value "var50".
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value380) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample18Value380)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									}
									
									// Recorded the probability of reaching sample task 55 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value380);
								}
							}
						}
					}
				}
				
				// Enumerating the possible arguments for the variable Bernoulli 51 which is consuming
				// the output of Sample task 26.
				if(fixedFlag$sample12) {
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
						// Enumerating the possible outputs of Categorical 22.
						for(int index$sample26$414 = 0; index$sample26$414 < weightings.length; index$sample26$414 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample26Value415 = distribution$sample26[index$i$413][index$sample26$414];
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var50 = ((double)((v1 + index$sample26$414) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 55 generating output with current configuration.
							// 
							// Substituted "cv$temp$89$var50" with its value "var50".
							if(((Math.log(cv$probabilitySample26Value415) + DistributionSampling.logProbabilityBernoulli(v[i], var50)) < cv$accumulatedConsumerProbabilities))
								// Substituted "cv$temp$89$var50" with its value "var50".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value415) + DistributionSampling.logProbabilityBernoulli(v[i], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "cv$temp$89$var50" with its value "var50".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value415) + DistributionSampling.logProbabilityBernoulli(v[i], var50));
								else
									// Substituted "cv$temp$89$var50" with its value "var50".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value415) + DistributionSampling.logProbabilityBernoulli(v[i], var50)))) + 1)) + Math.log(cv$probabilitySample26Value415)) + DistributionSampling.logProbabilityBernoulli(v[i], var50));
							}
							
							// Recorded the probability of reaching sample task 55 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value415);
						}
					}
				} else {
					// Enumerating the possible outputs of Categorical 8.
					for(int index$sample12$408 = 0; index$sample12$408 < weightings.length; index$sample12$408 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample12Value409 = distribution$sample12[index$sample12$408];
						
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
							// Enumerating the possible outputs of Categorical 22.
							for(int index$sample26$420 = 0; index$sample26$420 < weightings.length; index$sample26$420 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample26Value421 = (cv$probabilitySample12Value409 * distribution$sample26[index$i$419][index$sample26$420]);
								
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var50 = ((double)((index$sample12$408 + index$sample26$420) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 55 generating output with current configuration.
								// 
								// Substituted "cv$temp$94$var50" with its value "var50".
								if(((Math.log(cv$probabilitySample26Value421) + DistributionSampling.logProbabilityBernoulli(v[i], var50)) < cv$accumulatedConsumerProbabilities))
									// Substituted "cv$temp$94$var50" with its value "var50".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value421) + DistributionSampling.logProbabilityBernoulli(v[i], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "cv$temp$94$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value421) + DistributionSampling.logProbabilityBernoulli(v[i], var50));
									else
										// Substituted "cv$temp$94$var50" with its value "var50".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value421) + DistributionSampling.logProbabilityBernoulli(v[i], var50)))) + 1)) + Math.log(cv$probabilitySample26Value421)) + DistributionSampling.logProbabilityBernoulli(v[i], var50));
								}
								
								// Recorded the probability of reaching sample task 55 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value421);
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
			cv$var23$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample26[i];
		
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
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
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
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
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
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$localProbability[cv$indexName] = Math.exp((cv$var23$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var23$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var9$stateProbabilityGlobal
		// 
		// Allocation of cv$var9$stateProbabilityGlobal for single threaded execution
		cv$var9$stateProbabilityGlobal = new double[weightings.length];
		
		// Constructor for cv$var15$stateProbabilityGlobal
		// 
		// Allocation of cv$var15$stateProbabilityGlobal for single threaded execution
		cv$var15$stateProbabilityGlobal = new double[weightings.length];
		
		// Constructor for guard$sample18bernoulli54$global
		// 
		// Allocation of guard$sample18bernoulli54$global for single threaded execution
		guard$sample18bernoulli54$global = new boolean[length$value];
		
		// Constructor for cv$var23$stateProbabilityGlobal
		// 
		// Allocation of cv$var23$stateProbabilityGlobal for single threaded execution
		cv$var23$stateProbabilityGlobal = new double[weightings.length];
		
		// Allocation of guard$sample26bernoulli54$global for single threaded execution
		guard$sample26bernoulli54$global = new boolean[length$value];
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
		
		// If v has not been set already allocate space.
		if(!setFlag$v)
			// Constructor for v
			v = new boolean[length$value];
		
		// Constructor for distribution$sample18
		distribution$sample18 = new double[weightings.length];
		
		// Constructor for distribution$sample26
		distribution$sample26 = new double[length$value][];
		for(int i = 0; i < length$value; i += 1)
			distribution$sample26[i] = new double[weightings.length];
		
		// Constructor for distribution$sample12
		distribution$sample12 = new double[weightings.length];
		
		// Constructor for logProbability$var22
		logProbability$var22 = new double[length$value];
		
		// Constructor for logProbability$sample26
		logProbability$sample26 = new double[length$value];
		
		// Constructor for logProbability$var51
		logProbability$var51 = new double[length$value];
		
		// Constructor for logProbability$sample55
		logProbability$sample55 = new double[length$value];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample18)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample26)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((!fixedFlag$sample18 || !fixedFlag$sample26))
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, (size + 1), 1,
				(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int k = forStart$k; k < forEnd$k; k += 1)
							v3[k] = v2[k];
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample55)
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
		if(!fixedFlag$sample12) {
			for(int index$var8 = 0; index$var8 < weightings.length; index$var8 += 1)
				// Save the probability of each value
				// 
				// cv$distribution$sample12's comment
				// Create local copy of variable probabilities.
				distribution$sample12[index$var8] = weightings[index$var8];
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample18) {
			for(int index$var14 = 0; index$var14 < weightings.length; index$var14 += 1)
				// Save the probability of each value
				// 
				// cv$distribution$sample18's comment
				// Create local copy of variable probabilities.
				distribution$sample18[index$var14] = weightings[index$var14];
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample26)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1) {
							// Create local copy of variable probabilities.
							double[] cv$distribution$sample26 = distribution$sample26[i];
							for(int index$var22 = 0; index$var22 < weightings.length; index$var22 += 1)
								// Save the probability of each value
								// 
								// Probability for this value
								cv$distribution$sample26[index$var22] = weightings[index$var22];
						}
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample18)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample26)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((!fixedFlag$sample18 || !fixedFlag$sample26))
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
			if(!fixedFlag$sample12)
				sample12();
			if(!fixedFlag$sample18)
				sample18();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample26) {
				for(int i = 0; i < size; i += 1)
					sample26(i);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample26) {
				for(int i = (size - 1); i >= 0; i -= 1)
					sample26(i);
			}
			if(!fixedFlag$sample18)
				sample18();
			if(!fixedFlag$sample12)
				sample12();
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
		logProbability$var8 = 0.0;
		if(!fixedProbFlag$sample12)
			logProbability$v1 = 0.0;
		logProbability$var14 = 0.0;
		logProbability$v3 = 0.0;
		logProbability$v2 = 0.0;
		if(!fixedProbFlag$sample18)
			logProbability$var15 = 0.0;
		for(int i = 0; i < size; i += 1)
			logProbability$var22[i] = 0.0;
		if(!fixedProbFlag$sample26) {
			for(int i = 0; i < size; i += 1)
				logProbability$sample26[i] = 0.0;
		}
		for(int j = 0; j < size; j += 1)
			logProbability$var51[j] = 0.0;
		logProbability$v = 0.0;
		if(!fixedProbFlag$sample55) {
			for(int j = 0; j < size; j += 1)
				logProbability$sample55[j] = 0.0;
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
		logProbabilityValue$sample55();
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
		logProbabilityDistribution$sample12();
		logProbabilityDistribution$sample18();
		logProbabilityDistribution$sample26();
		logProbabilityDistribution$sample55();
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
		logProbabilityValue$sample12();
		logProbabilityValue$sample18();
		logProbabilityValue$sample26();
		logProbabilityValue$sample55();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample18)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample26)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((!fixedFlag$sample18 || !fixedFlag$sample26))
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel DistributionTest5(double[] weightings, boolean[] value) {\n    int size = value.length;\n    \n    int v1 = categorical(weightings).sampleDistribution();\n    \n    int[] v2 = new int[size + 1];\n    v2[0] = categorical(weightings).sampleDistribution();\n    for(int i:[0..size))\n        v2[i + 1] = categorical(weightings).sampleDistribution();\n        \n        \n    int[] v3 = new int[size + 1];\n    for(int k:[0..size]) \n        v3[k] = v2[k];\n        \n    boolean[] v = new boolean[size];\n    for(int j:[0..size))\n        v[j] = bernoulli(((1.0*v1) + v2[j] + v3[j+1])/v2[j+1]).sample();\n        \n    v.observe(value);\n}\n";
	}
}