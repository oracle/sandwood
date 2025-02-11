package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DistributionTest2b$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DistributionTest2b$CoreInterface {
	
	// Declare the variables for the model.
	private double[] cv$var13$stateProbabilityGlobal;
	private double[][] cv$var19$stateProbabilityGlobal;
	private double[] cv$var25$stateProbabilityGlobal;
	private double[] cv$var9$stateProbabilityGlobal;
	private double[] distribution$sample12;
	private double[] distribution$sample16;
	private double[][] distribution$sample22;
	private boolean fixedFlag$sample12 = false;
	private boolean fixedFlag$sample16 = false;
	private boolean fixedFlag$sample22 = false;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedProbFlag$sample12 = false;
	private boolean fixedProbFlag$sample16 = false;
	private boolean fixedProbFlag$sample22 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample35 = false;
	private int length$value;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$c;
	private double[] logProbability$sample22;
	private double[] logProbability$sample35;
	private double logProbability$v;
	private double logProbability$v1;
	private double logProbability$v2;
	private double logProbability$v3;
	private double logProbability$var12;
	private double logProbability$var13;
	private double[] logProbability$var18;
	private double[] logProbability$var31;
	private boolean setFlag$v = false;
	private boolean setFlag$v2 = false;
	private boolean setFlag$v3 = false;
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
		
		// Should the probability of sample 35 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample12" with its value "cv$value".
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
	}

	// Getter for fixedFlag$sample16.
	@Override
	public final boolean get$fixedFlag$sample16() {
		return fixedFlag$sample16;
	}

	// Setter for fixedFlag$sample16.
	@Override
	public final void set$fixedFlag$sample16(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample16 including if probabilities
		// need to be updated.
		fixedFlag$sample16 = cv$value;
		
		// Should the probability of sample 16 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample16" with its value "cv$value".
		fixedProbFlag$sample16 = (cv$value && fixedProbFlag$sample16);
		
		// Should the probability of sample 35 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample16" with its value "cv$value".
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
	}

	// Getter for fixedFlag$sample22.
	@Override
	public final boolean get$fixedFlag$sample22() {
		return fixedFlag$sample22;
	}

	// Setter for fixedFlag$sample22.
	@Override
	public final void set$fixedFlag$sample22(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample22 including if probabilities
		// need to be updated.
		fixedFlag$sample22 = cv$value;
		
		// Should the probability of sample 22 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample22" with its value "cv$value".
		fixedProbFlag$sample22 = (cv$value && fixedProbFlag$sample22);
		
		// Should the probability of sample 35 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample22" with its value "cv$value".
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
	}

	// Getter for fixedFlag$sample28.
	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	// Setter for fixedFlag$sample28.
	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample28 including if probabilities
		// need to be updated.
		fixedFlag$sample28 = cv$value;
		
		// Should the probability of sample 28 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample28" with its value "cv$value".
		fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
		
		// Should the probability of sample 35 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample28" with its value "cv$value".
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
	}

	// Getter for fixedFlag$sample35.
	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	// Setter for fixedFlag$sample35.
	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample35 including if probabilities
		// need to be updated.
		fixedFlag$sample35 = cv$value;
		
		// Should the probability of sample 35 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample35" with its value "cv$value".
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
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

	// Setter for v.
	@Override
	public final void set$v(boolean[] cv$value) {
		// Set flags for all the side effects of v including if probabilities need to be updated.
		// Set v with flag to mark that it has been set so another array doesn't need to be
		// constructed
		v = cv$value;
		setFlag$v = true;
		
		// Unset the fixed probability flag for sample 35 as it depends on v.
		fixedProbFlag$sample35 = false;
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
		
		// Unset the fixed probability flag for sample 35 as it depends on v1.
		fixedProbFlag$sample35 = false;
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
		
		// Unset the fixed probability flag for sample 16 as it depends on v2.
		fixedProbFlag$sample16 = false;
		
		// Unset the fixed probability flag for sample 22 as it depends on v2.
		fixedProbFlag$sample22 = false;
		
		// Unset the fixed probability flag for sample 35 as it depends on v2.
		fixedProbFlag$sample35 = false;
	}

	// Getter for v3.
	@Override
	public final int[] get$v3() {
		return v3;
	}

	// Setter for v3.
	@Override
	public final void set$v3(int[] cv$value) {
		// Set v3 with flag to mark that it has been set so another array doesn't need to
		// be constructed
		v3 = cv$value;
		setFlag$v3 = true;
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
				logProbability$c = (logProbability$c + cv$distributionAccumulator);
				
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
			logProbability$c = (logProbability$c + logProbability$v1);
			
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

	// Calculate the probability of the samples represented by sample16 using probability
	// distributions.
	private final void logProbabilityDistribution$sample16() {
		// Determine if we need to calculate the values for sample task 16 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample16) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample16) {
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
				logProbability$var12 = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$var13 = cv$distributionAccumulator;
				
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
				// Substituted "fixedFlag$sample16" with its value "true".
				fixedProbFlag$sample16 = true;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var12 = logProbability$var13;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample16)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$v2 = (logProbability$v2 + logProbability$var13);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var13);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample16)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var13);
		}
	}

	// Calculate the probability of the samples represented by sample22 using probability
	// distributions.
	private final void logProbabilityDistribution$sample22() {
		// Determine if we need to calculate the values for sample task 22 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample22) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample22) {
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
					logProbability$var18[(i - 1)] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample22[(i - 1)] = cv$distributionAccumulator;
				}
				
				// Update the variable probability
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample22" with its value "true".
				fixedProbFlag$sample22 = true;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 1; i < size; i += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample22[(i - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var18[(i - 1)] = cv$rvAccumulator;
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample22)
				// Update the variable probability
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample35 using probability
	// distributions.
	private final void logProbabilityDistribution$sample35() {
		// Determine if we need to calculate the values for sample task 35 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample35) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 35 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				boolean cv$sampleValue = v[j];
				
				// Enumerating the possible arguments for Bernoulli 31.
				if((0 == j)) {
					// Enumerating the possible arguments for Bernoulli 31.
					if(fixedFlag$sample12) {
						if(fixedFlag$sample16) {
							// Store the value of the function call, so the function call is only made once.
							// 
							// Substituted "j" with its value "0".
							cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)v1 / (v2[0] + v3[0])));
							
							// Add the probability of this distribution configuration to the accumulator.
							// 
							// An accumulator for the distributed probability space covered.
							cv$probabilityReached = 1.0;
						} else {
							// Enumerating the possible outputs of Categorical 12.
							for(int index$sample16$8 = 0; index$sample16$8 < weightings.length; index$sample16$8 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample16Value9 = distribution$sample16[index$sample16$8];
								
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "j" with its value "0".
								double cv$weightedProbability = (Math.log(cv$probabilitySample16Value9) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)v1 / (index$sample16$8 + v3[0]))));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample16Value9);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 8.
						for(int index$sample12$3 = 0; index$sample12$3 < weightings.length; index$sample12$3 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample12Value4 = distribution$sample12[index$sample12$3];
							if(fixedFlag$sample16) {
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "j" with its value "0".
								double cv$weightedProbability = (Math.log(cv$probabilitySample12Value4) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)index$sample12$3 / (v2[0] + v3[0]))));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample12Value4);
							} else {
								// Enumerating the possible outputs of Categorical 12.
								for(int index$sample16$13 = 0; index$sample16$13 < weightings.length; index$sample16$13 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample16Value14 = (cv$probabilitySample12Value4 * distribution$sample16[index$sample16$13]);
									
									// Store the value of the function call, so the function call is only made once.
									// 
									// Substituted "j" with its value "0".
									double cv$weightedProbability = (Math.log(cv$probabilitySample16Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)index$sample12$3 / (index$sample16$13 + v3[0]))));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample16Value14);
								}
							}
						}
					}
				}
				
				// Enumerating the possible arguments for Bernoulli 31.
				if((1 <= j)) {
					// Enumerating the possible arguments for Bernoulli 31.
					if(fixedFlag$sample12) {
						if(fixedFlag$sample22) {
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)v1 / (v2[j] + v3[j])));
							
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
							// Enumerating the possible outputs of Categorical 18.
							for(int index$sample22$28 = 0; index$sample22$28 < weightings.length; index$sample22$28 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "i" with its value "j".
								double cv$probabilitySample22Value29 = distribution$sample22[(j - 1)][index$sample22$28];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample22Value29) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)v1 / (index$sample22$28 + v3[j]))));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample22Value29);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 8.
						for(int index$sample12$22 = 0; index$sample12$22 < weightings.length; index$sample12$22 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample12Value23 = distribution$sample12[index$sample12$22];
							if(fixedFlag$sample22) {
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample12Value23) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)index$sample12$22 / (v2[j] + v3[j]))));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample12Value23);
							} else {
								// Enumerating the possible outputs of Categorical 18.
								for(int index$sample22$34 = 0; index$sample22$34 < weightings.length; index$sample22$34 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "i" with its value "j".
									double cv$probabilitySample22Value35 = (cv$probabilitySample12Value23 * distribution$sample22[(j - 1)][index$sample22$34]);
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample22Value35) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)index$sample12$22 / (index$sample22$34 + v3[j]))));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample22Value35);
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
				logProbability$var31[j] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample35[j] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$v = (logProbability$v + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample35 = ((((fixedFlag$sample35 && fixedFlag$sample12) && fixedFlag$sample16) && fixedFlag$sample22) && fixedFlag$sample28);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample35[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var31[j] = cv$rvAccumulator;
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
			logProbability$c = (logProbability$c + cv$distributionAccumulator);
			
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
			logProbability$c = (logProbability$c + logProbability$v1);
			
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

	// Calculate the probability of the samples represented by sample16 using sampled
	// values.
	private final void logProbabilityValue$sample16() {
		// Determine if we need to calculate the values for sample task 16 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample16) {
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
			logProbability$var12 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var13 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample16)
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
			fixedProbFlag$sample16 = fixedFlag$sample16;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var12 = logProbability$var13;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$v2 = (logProbability$v2 + logProbability$var13);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var13);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample16)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var13);
		}
	}

	// Calculate the probability of the samples represented by sample22 using sampled
	// values.
	private final void logProbabilityValue$sample22() {
		// Determine if we need to calculate the values for sample task 22 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample22) {
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
				logProbability$var18[(i - 1)] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample22[(i - 1)] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample22 = fixedFlag$sample22;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 1; i < size; i += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample22[(i - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var18[(i - 1)] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample28 using sampled
	// values.
	private final void logProbabilityValue$sample28() {
		// Determine if we need to calculate the values for sample task 28 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample28) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = v3[j];
				
				// Add the probability of this sample task to the sample task accumulator.
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			logProbability$c = (logProbability$c + cv$sampleAccumulator);
			
			// Store the random variable instance probability
			logProbability$v3 = cv$sampleAccumulator;
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample28)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample28 = fixedFlag$sample28;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$c = (logProbability$c + logProbability$v3);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$v3);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample28)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$v3);
		}
	}

	// Calculate the probability of the samples represented by sample35 using sampled
	// values.
	private final void logProbabilityValue$sample35() {
		// Determine if we need to calculate the values for sample task 35 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample35) {
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(v[j], ((double)v1 / (v2[j] + v3[j])));
				
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
				logProbability$var31[j] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample35[j] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$v = (logProbability$v + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample35 = ((((fixedFlag$sample35 && fixedFlag$sample12) && fixedFlag$sample16) && fixedFlag$sample22) && fixedFlag$sample28);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample35[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var31[j] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$v = (logProbability$v + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 12 drawn from c. Inference was performed using variable marginalization.
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
			
			// Processing random variable 31.
			for(int j = 0; j < size; j += 1) {
				// Processing sample task 35 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Bernoulli 31 which is consuming
				// the output of Sample task 12.
				if((0 == j)) {
					if(fixedFlag$sample16) {
						// Substituted "j" with its value "0".
						// 
						// Substituted "cv$temp$1$var30" with its value "var30".
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "j" with its value "0".
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)cv$valuePos / (v2[0] + v3[0])));
						
						// Recorded the probability of reaching sample task 35 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						// Enumerating the possible outputs of Categorical 12.
						for(int index$sample16$4 = 0; index$sample16$4 < weightings.length; index$sample16$4 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample16Value5 = distribution$sample16[index$sample16$4];
							
							// Constructing a random variable input for use later.
							// 
							// Substituted "j" with its value "0".
							// 
							// Value of the variable at this index
							double var30 = ((double)cv$valuePos / (index$sample16$4 + v3[0]));
							
							// Record the probability of sample task 35 generating output with current configuration.
							// 
							// Substituted "j" with its value "0".
							// 
							// Substituted "cv$temp$2$var30" with its value "var30".
							if(((Math.log(cv$probabilitySample16Value5) + DistributionSampling.logProbabilityBernoulli(v[0], var30)) < cv$accumulatedConsumerProbabilities))
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$2$var30" with its value "var30".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample16Value5) + DistributionSampling.logProbabilityBernoulli(v[0], var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$2$var30" with its value "var30".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample16Value5) + DistributionSampling.logProbabilityBernoulli(v[0], var30));
								else
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$2$var30" with its value "var30".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample16Value5) + DistributionSampling.logProbabilityBernoulli(v[0], var30)))) + 1)) + Math.log(cv$probabilitySample16Value5)) + DistributionSampling.logProbabilityBernoulli(v[0], var30));
							}
							
							// Recorded the probability of reaching sample task 35 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample16Value5);
						}
					}
				}
				
				// Enumerating the possible arguments for the variable Bernoulli 31 which is consuming
				// the output of Sample task 12.
				if((1 <= j)) {
					// Enumerating the possible arguments for the variable Bernoulli 31 which is consuming
					// the output of Sample task 12.
					if(fixedFlag$sample22) {
						// Variable declaration of cv$temp$3$var30 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double cv$temp$3$var30 = ((double)cv$valuePos / (v2[j] + v3[j]));
						
						// Record the probability of sample task 35 generating output with current configuration.
						if((DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var30) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var30) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var30);
							else
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var30))) + 1)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var30));
						}
						
						// Recorded the probability of reaching sample task 35 with the current configuration.
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
					} else {
						// Enumerating the possible outputs of Categorical 18.
						for(int index$sample22$12 = 0; index$sample22$12 < weightings.length; index$sample22$12 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "i" with its value "j".
							double cv$probabilitySample22Value13 = distribution$sample22[(j - 1)][index$sample22$12];
							
							// Variable declaration of cv$temp$4$var30 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$4$var30 = ((double)cv$valuePos / (index$sample22$12 + v3[j]));
							
							// Record the probability of sample task 35 generating output with current configuration.
							if(((Math.log(cv$probabilitySample22Value13) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var30)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample22Value13) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample22Value13) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var30));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample22Value13) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var30)))) + 1)) + Math.log(cv$probabilitySample22Value13)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var30));
							}
							
							// Recorded the probability of reaching sample task 35 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample22Value13);
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
	// by sample task 16 drawn from Categorical 12. Inference was performed using variable
	// marginalization.
	private final void sample16() {
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
			
			// Substituted "j" with its value "0".
			if((0 < size)) {
				// Processing sample task 35 of consumer random variable null.
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Bernoulli 31 which is consuming
				// the output of Sample task 16.
				if(fixedFlag$sample12) {
					// Substituted "j" with its value "0".
					// 
					// Substituted "cv$temp$1$var30" with its value "var30".
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "j" with its value "0".
					// 
					// Processing random variable 31.
					// 
					// Looking for a path between Sample 16 and consumer Bernoulli 31.
					// 
					// Value of the variable at this index
					cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)v1 / (cv$valuePos + v3[0])));
					
					// Recorded the probability of reaching sample task 35 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					cv$consumerDistributionProbabilityAccumulator = 0.0;
				} else {
					// Enumerating the possible outputs of Categorical 8.
					for(int index$sample12$4 = 0; index$sample12$4 < weightings.length; index$sample12$4 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample12Value5 = distribution$sample12[index$sample12$4];
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j" with its value "0".
						// 
						// Processing random variable 31.
						// 
						// Looking for a path between Sample 16 and consumer Bernoulli 31.
						// 
						// Value of the variable at this index
						double var30 = ((double)index$sample12$4 / (cv$valuePos + v3[0]));
						
						// Record the probability of sample task 35 generating output with current configuration.
						// 
						// Substituted "j" with its value "0".
						// 
						// Substituted "cv$temp$2$var30" with its value "var30".
						if(((Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[0], var30)) < cv$accumulatedConsumerProbabilities))
							// Substituted "j" with its value "0".
							// 
							// Substituted "cv$temp$2$var30" with its value "var30".
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[0], var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$2$var30" with its value "var30".
								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[0], var30));
							else
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$2$var30" with its value "var30".
								cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[0], var30)))) + 1)) + Math.log(cv$probabilitySample12Value5)) + DistributionSampling.logProbabilityBernoulli(v[0], var30));
						}
						
						// Recorded the probability of reaching sample task 35 with the current configuration.
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value5);
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
			cv$var13$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
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
		double cv$lseMax = cv$var13$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var13$stateProbabilityGlobal[cv$lseIndex];
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
				cv$lseSum = (cv$lseSum + Math.exp((cv$var13$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
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
				distribution$sample16[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				// Set the calculated probabilities to be the distribution values, and normalize
				// 
				// Local copy of the probability array
				distribution$sample16[cv$indexName] = Math.exp((cv$var13$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var13$stateProbabilityGlobal.length; cv$indexName += 1)
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample16[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 22 drawn from Categorical 18. Inference was performed using variable
	// marginalization.
	private final void sample22(int i, int threadID$cv$i, Rng RNG$) {
		// Variable declaration of cv$noStates moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		int cv$noStates = weightings.length;
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var19$stateProbabilityGlobal[threadID$cv$i];
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$weightings" with its value "weightings".
			double cv$accumulatedProbabilities = Math.log(weightings[cv$valuePos]);
			
			// Processing sample task 35 of consumer random variable null.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
			
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			double cv$consumerDistributionProbabilityAccumulator = 1.0;
			
			// Enumerating the possible arguments for the variable Bernoulli 31 which is consuming
			// the output of Sample task 22.
			if(fixedFlag$sample12) {
				// Substituted "j" with its value "i".
				// 
				// Substituted "cv$temp$1$var30" with its value "var30".
				// 
				// Constructing a random variable input for use later.
				// 
				// Substituted "j" with its value "i".
				// 
				// Processing random variable 31.
				// 
				// Looking for a path between Sample 22 and consumer Bernoulli 31.
				// 
				// Value of the variable at this index
				cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[i], ((double)v1 / (cv$valuePos + v3[i])));
				
				// Recorded the probability of reaching sample task 35 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				cv$consumerDistributionProbabilityAccumulator = 0.0;
			} else {
				// Enumerating the possible outputs of Categorical 8.
				for(int index$sample12$6 = 0; index$sample12$6 < weightings.length; index$sample12$6 += 1) {
					// Update the probability of sampling this value from the distribution value.
					double cv$probabilitySample12Value7 = distribution$sample12[index$sample12$6];
					
					// Constructing a random variable input for use later.
					// 
					// Substituted "j" with its value "i".
					// 
					// Processing random variable 31.
					// 
					// Looking for a path between Sample 22 and consumer Bernoulli 31.
					// 
					// Value of the variable at this index
					double var30 = ((double)index$sample12$6 / (cv$valuePos + v3[i]));
					
					// Record the probability of sample task 35 generating output with current configuration.
					// 
					// Substituted "j" with its value "i".
					// 
					// Substituted "cv$temp$2$var30" with its value "var30".
					if(((Math.log(cv$probabilitySample12Value7) + DistributionSampling.logProbabilityBernoulli(v[i], var30)) < cv$accumulatedConsumerProbabilities))
						// Substituted "j" with its value "i".
						// 
						// Substituted "cv$temp$2$var30" with its value "var30".
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value7) + DistributionSampling.logProbabilityBernoulli(v[i], var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							// Substituted "j" with its value "i".
							// 
							// Substituted "cv$temp$2$var30" with its value "var30".
							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value7) + DistributionSampling.logProbabilityBernoulli(v[i], var30));
						else
							// Substituted "j" with its value "i".
							// 
							// Substituted "cv$temp$2$var30" with its value "var30".
							cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value7) + DistributionSampling.logProbabilityBernoulli(v[i], var30)))) + 1)) + Math.log(cv$probabilitySample12Value7)) + DistributionSampling.logProbabilityBernoulli(v[i], var30));
					}
					
					// Recorded the probability of reaching sample task 35 with the current configuration.
					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value7);
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
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$stateProbabilityLocal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample22[(i - 1)];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		double cv$lseMax = cv$stateProbabilityLocal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
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
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
			
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
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 28 drawn from c. Inference was performed using variable marginalization.
	private final void sample28(int j) {
		// Variable declaration of cv$noStates moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		int cv$noStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			v3[j] = cv$valuePos;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$weightings" with its value "weightings".
			double cv$accumulatedProbabilities = Math.log(weightings[cv$valuePos]);
			
			// Processing random variable 31.
			// 
			// Processing sample task 35 of consumer random variable null.
			// 
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
			
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			double cv$consumerDistributionProbabilityAccumulator = 1.0;
			
			// Enumerating the possible arguments for the variable Bernoulli 31 which is consuming
			// the output of Sample task 28.
			if((0 == j)) {
				if(fixedFlag$sample12) {
					if(fixedFlag$sample16) {
						// Substituted "j" with its value "0".
						// 
						// cv$temp$1$var30's comment
						// Variable declaration of cv$temp$1$var30 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)v1 / (v2[0] + cv$valuePos)));
						
						// Recorded the probability of reaching sample task 35 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						// Enumerating the possible outputs of Categorical 12.
						for(int index$sample16$9 = 0; index$sample16$9 < weightings.length; index$sample16$9 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample16Value10 = distribution$sample16[index$sample16$9];
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var30 = ((double)v1 / (index$sample16$9 + cv$valuePos));
							
							// Record the probability of sample task 35 generating output with current configuration.
							// 
							// Substituted "j" with its value "0".
							// 
							// Substituted "cv$temp$2$var30" with its value "var30".
							if(((Math.log(cv$probabilitySample16Value10) + DistributionSampling.logProbabilityBernoulli(v[0], var30)) < cv$accumulatedConsumerProbabilities))
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$2$var30" with its value "var30".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample16Value10) + DistributionSampling.logProbabilityBernoulli(v[0], var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$2$var30" with its value "var30".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample16Value10) + DistributionSampling.logProbabilityBernoulli(v[0], var30));
								else
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$2$var30" with its value "var30".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample16Value10) + DistributionSampling.logProbabilityBernoulli(v[0], var30)))) + 1)) + Math.log(cv$probabilitySample16Value10)) + DistributionSampling.logProbabilityBernoulli(v[0], var30));
							}
							
							// Recorded the probability of reaching sample task 35 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample16Value10);
						}
					}
				} else {
					// Enumerating the possible outputs of Categorical 8.
					for(int index$sample12$4 = 0; index$sample12$4 < weightings.length; index$sample12$4 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample12Value5 = distribution$sample12[index$sample12$4];
						if(fixedFlag$sample16) {
							// Variable declaration of cv$temp$3$var30 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$3$var30 = ((double)index$sample12$4 / (v2[0] + cv$valuePos));
							
							// Record the probability of sample task 35 generating output with current configuration.
							// 
							// Substituted "j" with its value "0".
							if(((Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$3$var30)) < cv$accumulatedConsumerProbabilities))
								// Substituted "j" with its value "0".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$3$var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$3$var30));
								else
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$3$var30)))) + 1)) + Math.log(cv$probabilitySample12Value5)) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$3$var30));
							}
							
							// Recorded the probability of reaching sample task 35 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value5);
						} else {
							// Enumerating the possible outputs of Categorical 12.
							for(int index$sample16$14 = 0; index$sample16$14 < weightings.length; index$sample16$14 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample16Value15 = (cv$probabilitySample12Value5 * distribution$sample16[index$sample16$14]);
								
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var30 = ((double)index$sample12$4 / (index$sample16$14 + cv$valuePos));
								
								// Record the probability of sample task 35 generating output with current configuration.
								// 
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$4$var30" with its value "var30".
								if(((Math.log(cv$probabilitySample16Value15) + DistributionSampling.logProbabilityBernoulli(v[0], var30)) < cv$accumulatedConsumerProbabilities))
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$4$var30" with its value "var30".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample16Value15) + DistributionSampling.logProbabilityBernoulli(v[0], var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$4$var30" with its value "var30".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample16Value15) + DistributionSampling.logProbabilityBernoulli(v[0], var30));
									else
										// Substituted "j" with its value "0".
										// 
										// Substituted "cv$temp$4$var30" with its value "var30".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample16Value15) + DistributionSampling.logProbabilityBernoulli(v[0], var30)))) + 1)) + Math.log(cv$probabilitySample16Value15)) + DistributionSampling.logProbabilityBernoulli(v[0], var30));
								}
								
								// Recorded the probability of reaching sample task 35 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample16Value15);
							}
						}
					}
				}
			}
			
			// Enumerating the possible arguments for the variable Bernoulli 31 which is consuming
			// the output of Sample task 28.
			if((1 <= j)) {
				// Enumerating the possible arguments for the variable Bernoulli 31 which is consuming
				// the output of Sample task 28.
				if(fixedFlag$sample12) {
					if(fixedFlag$sample22) {
						// Variable declaration of cv$temp$5$var30 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double cv$temp$5$var30 = ((double)v1 / (v2[j] + cv$valuePos));
						
						// Record the probability of sample task 35 generating output with current configuration.
						if((DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var30) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var30) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var30);
							else
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var30))) + 1)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var30));
						}
						
						// Recorded the probability of reaching sample task 35 with the current configuration.
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
					} else {
						// Enumerating the possible outputs of Categorical 18.
						for(int index$sample22$25 = 0; index$sample22$25 < weightings.length; index$sample22$25 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "i" with its value "j".
							double cv$probabilitySample22Value26 = distribution$sample22[(j - 1)][index$sample22$25];
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var30 = ((double)v1 / (index$sample22$25 + cv$valuePos));
							
							// Record the probability of sample task 35 generating output with current configuration.
							// 
							// Substituted "cv$temp$6$var30" with its value "var30".
							if(((Math.log(cv$probabilitySample22Value26) + DistributionSampling.logProbabilityBernoulli(v[j], var30)) < cv$accumulatedConsumerProbabilities))
								// Substituted "cv$temp$6$var30" with its value "var30".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample22Value26) + DistributionSampling.logProbabilityBernoulli(v[j], var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "cv$temp$6$var30" with its value "var30".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample22Value26) + DistributionSampling.logProbabilityBernoulli(v[j], var30));
								else
									// Substituted "cv$temp$6$var30" with its value "var30".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample22Value26) + DistributionSampling.logProbabilityBernoulli(v[j], var30)))) + 1)) + Math.log(cv$probabilitySample22Value26)) + DistributionSampling.logProbabilityBernoulli(v[j], var30));
							}
							
							// Recorded the probability of reaching sample task 35 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample22Value26);
						}
					}
				} else {
					// Enumerating the possible outputs of Categorical 8.
					for(int index$sample12$19 = 0; index$sample12$19 < weightings.length; index$sample12$19 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample12Value20 = distribution$sample12[index$sample12$19];
						if(fixedFlag$sample22) {
							// Variable declaration of cv$temp$7$var30 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$7$var30 = ((double)index$sample12$19 / (v2[j] + cv$valuePos));
							
							// Record the probability of sample task 35 generating output with current configuration.
							if(((Math.log(cv$probabilitySample12Value20) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var30)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value20) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value20) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var30));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value20) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var30)))) + 1)) + Math.log(cv$probabilitySample12Value20)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var30));
							}
							
							// Recorded the probability of reaching sample task 35 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value20);
						} else {
							// Enumerating the possible outputs of Categorical 18.
							for(int index$sample22$31 = 0; index$sample22$31 < weightings.length; index$sample22$31 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "i" with its value "j".
								double cv$probabilitySample22Value32 = (cv$probabilitySample12Value20 * distribution$sample22[(j - 1)][index$sample22$31]);
								
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var30 = ((double)index$sample12$19 / (index$sample22$31 + cv$valuePos));
								
								// Record the probability of sample task 35 generating output with current configuration.
								// 
								// Substituted "cv$temp$8$var30" with its value "var30".
								if(((Math.log(cv$probabilitySample22Value32) + DistributionSampling.logProbabilityBernoulli(v[j], var30)) < cv$accumulatedConsumerProbabilities))
									// Substituted "cv$temp$8$var30" with its value "var30".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample22Value32) + DistributionSampling.logProbabilityBernoulli(v[j], var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "cv$temp$8$var30" with its value "var30".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample22Value32) + DistributionSampling.logProbabilityBernoulli(v[j], var30));
									else
										// Substituted "cv$temp$8$var30" with its value "var30".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample22Value32) + DistributionSampling.logProbabilityBernoulli(v[j], var30)))) + 1)) + Math.log(cv$probabilitySample22Value32)) + DistributionSampling.logProbabilityBernoulli(v[j], var30));
								}
								
								// Recorded the probability of reaching sample task 35 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample22Value32);
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
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var25$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
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
		double cv$lseMax = cv$var25$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var25$stateProbabilityGlobal[cv$lseIndex];
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
				cv$lseSum = (cv$lseSum + Math.exp((cv$var25$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var25$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var25$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var25$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var25$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var25$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the new value of the sample.
		// 
		// Get a local reference to the scratch space.
		v3[j] = DistributionSampling.sampleCategorical(RNG$, cv$var25$stateProbabilityGlobal);
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
		
		// Constructor for cv$var13$stateProbabilityGlobal
		// 
		// Allocation of cv$var13$stateProbabilityGlobal for single threaded execution
		cv$var13$stateProbabilityGlobal = new double[weightings.length];
		
		// Constructor for cv$var19$stateProbabilityGlobal
		// 
		// Allocation of cv$var19$stateProbabilityGlobal for multithreaded execution
		// 
		// Get the thread count.
		int cv$threadCount = threadCount();
		
		// Allocate an array to hold a copy per thread
		cv$var19$stateProbabilityGlobal = new double[cv$threadCount][];
		
		// Populate the array with a copy per thread
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var19$stateProbabilityGlobal[cv$index] = new double[weightings.length];
		
		// Constructor for cv$var25$stateProbabilityGlobal
		// 
		// Allocation of cv$var25$stateProbabilityGlobal for single threaded execution
		cv$var25$stateProbabilityGlobal = new double[weightings.length];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If v2 has not been set already allocate space.
		if(!setFlag$v2)
			// Constructor for v2
			v2 = new int[length$value];
		
		// If v has not been set already allocate space.
		if(!setFlag$v)
			// Constructor for v
			v = new boolean[length$value];
		
		// If v3 has not been set already allocate space.
		if(!setFlag$v3)
			// Constructor for v3
			v3 = new int[length$value];
		
		// Constructor for distribution$sample22
		distribution$sample22 = new double[(length$value - 1)][];
		for(int i = 1; i < length$value; i += 1)
			distribution$sample22[(i - 1)] = new double[weightings.length];
		
		// Constructor for distribution$sample12
		distribution$sample12 = new double[weightings.length];
		
		// Constructor for distribution$sample16
		distribution$sample16 = new double[weightings.length];
		
		// Constructor for logProbability$var18
		logProbability$var18 = new double[(length$value - 1)];
		
		// Constructor for logProbability$sample22
		logProbability$sample22 = new double[(length$value - 1)];
		
		// Constructor for logProbability$var31
		logProbability$var31 = new double[length$value];
		
		// Constructor for logProbability$sample35
		logProbability$sample35 = new double[length$value];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample16)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample22)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[i] = DistributionSampling.sampleCategorical(RNG$1, weightings);
				}
			);

		for(int j = 0; j < size; j += 1) {
			if(!fixedFlag$sample28)
				v3[j] = DistributionSampling.sampleCategorical(RNG$, weightings);
			if(!fixedFlag$sample35)
				v[j] = DistributionSampling.sampleBernoulli(RNG$, ((double)v1 / (v2[j] + v3[j])));
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((!fixedFlag$sample12 || !fixedFlag$sample28)) {
			for(int index$c = 0; index$c < weightings.length; index$c += 1)
				// Save the probability of each value
				// 
				// cv$distribution$sample12's comment
				// Create local copy of variable probabilities.
				distribution$sample12[index$c] = weightings[index$c];
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample16) {
			for(int index$var12 = 0; index$var12 < weightings.length; index$var12 += 1)
				// Save the probability of each value
				// 
				// cv$distribution$sample16's comment
				// Create local copy of variable probabilities.
				distribution$sample16[index$var12] = weightings[index$var12];
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample22)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1) {
							// Create local copy of variable probabilities.
							double[] cv$distribution$sample22 = distribution$sample22[(i - 1)];
							for(int index$var18 = 0; index$var18 < weightings.length; index$var18 += 1)
								// Save the probability of each value
								// 
								// Probability for this value
								cv$distribution$sample22[index$var18] = weightings[index$var18];
						}
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample28) {
			for(int j = 0; j < size; j += 1)
				v3[j] = DistributionSampling.sampleCategorical(RNG$, weightings);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample16)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample22)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[i] = DistributionSampling.sampleCategorical(RNG$1, weightings);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample28) {
			for(int j = 0; j < size; j += 1)
				v3[j] = DistributionSampling.sampleCategorical(RNG$, weightings);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample12)
				sample12();
			if(!fixedFlag$sample16)
				sample16();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample22)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 1, size, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int i = forStart$i; i < forEnd$i; i += 1)
								sample22(i, threadID$i, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample28) {
				for(int j = 0; j < size; j += 1)
					sample28(j);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample28) {
				for(int j = (size - 1); j >= 0; j -= 1)
					sample28(j);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample22)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 1, size, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int i = forStart$i; i < forEnd$i; i += 1)
								sample22(i, threadID$i, RNG$1);
					}
				);

			if(!fixedFlag$sample16)
				sample16();
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
		logProbability$c = 0.0;
		if(!fixedProbFlag$sample12)
			logProbability$v1 = 0.0;
		logProbability$var12 = 0.0;
		logProbability$v2 = 0.0;
		if(!fixedProbFlag$sample16)
			logProbability$var13 = 0.0;
		for(int i = 1; i < size; i += 1)
			logProbability$var18[(i - 1)] = 0.0;
		if(!fixedProbFlag$sample22) {
			for(int i = 1; i < size; i += 1)
				logProbability$sample22[(i - 1)] = 0.0;
		}
		if(!fixedProbFlag$sample28)
			logProbability$v3 = 0.0;
		for(int j = 0; j < size; j += 1)
			logProbability$var31[j] = 0.0;
		logProbability$v = 0.0;
		if(!fixedProbFlag$sample35) {
			for(int j = 0; j < size; j += 1)
				logProbability$sample35[j] = 0.0;
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
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		logProbabilityValue$sample35();
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
		logProbabilityDistribution$sample16();
		logProbabilityDistribution$sample22();
		logProbabilityValue$sample28();
		logProbabilityDistribution$sample35();
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
		logProbabilityValue$sample16();
		logProbabilityValue$sample22();
		logProbabilityValue$sample28();
		logProbabilityValue$sample35();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample16)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample22)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 1, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[i] = DistributionSampling.sampleCategorical(RNG$1, weightings);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample28) {
			for(int j = 0; j < size; j += 1)
				v3[j] = DistributionSampling.sampleCategorical(RNG$, weightings);
		}
		
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
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel DistributionTest2b(double[] weightings, boolean[] value) {\n    int size = value.length;\n    \n    Categorical c = new Categorical(weightings);\n    int v1 = c.sampleDistribution();\n    \n    int[] v2 = new int[size];\n    v2[0] = categorical(weightings).sampleDistribution();\n    for(int i:[1..size))\n        v2[i] = categorical(weightings).sampleDistribution();\n        \n    boolean[] v = new boolean[size];\n    for(int j:[0..size)) {\n        int v3 = c.sample();\n        v[j] = bernoulli((1.0*v1)/(v2[j] + v3)).sample();\n    }\n        \n    v.observe(value);\n}\n";
	}
}