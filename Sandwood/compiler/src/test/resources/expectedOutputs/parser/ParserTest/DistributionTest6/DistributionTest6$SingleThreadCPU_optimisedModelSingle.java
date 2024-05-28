package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DistributionTest6$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements DistributionTest6$CoreInterface {
	
	// Declare the variables for the model.
	private double[] cv$var15$stateProbabilityGlobal;
	private double[] cv$var31$stateProbabilityGlobal;
	private double[] cv$var9$stateProbabilityGlobal;
	private double[] distribution$sample12;
	private double[] distribution$sample18;
	private double[][] distribution$sample34;
	private boolean fixedFlag$sample12 = false;
	private boolean fixedFlag$sample18 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample56 = false;
	private boolean fixedProbFlag$sample12 = false;
	private boolean fixedProbFlag$sample18 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample56 = false;
	private boolean[] guard$sample18bernoulli55$global;
	private boolean[] guard$sample34bernoulli55$global;
	private int length$value;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$v;
	private double logProbability$v1;
	private double logProbability$v2;
	private double logProbability$var14;
	private double logProbability$var15;
	private double logProbability$var30;
	private double logProbability$var31;
	private double logProbability$var52;
	private double logProbability$var53;
	private double logProbability$var8;
	private boolean setFlag$v = false;
	private boolean setFlag$v2 = false;
	private int size;
	private boolean system$gibbsForward = true;
	private boolean[] v;
	private int v1;
	private int[] v2;
	private boolean[] value;
	private double[] weightings;

	public DistributionTest6$SingleThreadCPU(ExecutionTarget target) {
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
		
		// Should the probability of sample 56 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample12" with its value "cv$value".
		fixedProbFlag$sample56 = (cv$value && fixedProbFlag$sample56);
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
		
		// Should the probability of sample 56 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample18" with its value "cv$value".
		fixedProbFlag$sample56 = (cv$value && fixedProbFlag$sample56);
	}

	// Getter for fixedFlag$sample34.
	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	// Setter for fixedFlag$sample34.
	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample34 including if probabilities
		// need to be updated.
		fixedFlag$sample34 = cv$value;
		
		// Should the probability of sample 34 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample34" with its value "cv$value".
		fixedProbFlag$sample34 = (cv$value && fixedProbFlag$sample34);
		
		// Should the probability of sample 56 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample34" with its value "cv$value".
		fixedProbFlag$sample56 = (cv$value && fixedProbFlag$sample56);
	}

	// Getter for fixedFlag$sample56.
	@Override
	public final boolean get$fixedFlag$sample56() {
		return fixedFlag$sample56;
	}

	// Setter for fixedFlag$sample56.
	@Override
	public final void set$fixedFlag$sample56(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample56 including if probabilities
		// need to be updated.
		fixedFlag$sample56 = cv$value;
		
		// Should the probability of sample 56 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample56" with its value "cv$value".
		fixedProbFlag$sample56 = (cv$value && fixedProbFlag$sample56);
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

	// Setter for v.
	@Override
	public final void set$v(boolean[] cv$value) {
		// Set flags for all the side effects of v including if probabilities need to be updated.
		// Set v with flag to mark that it has been set so another array doesn't need to be
		// constructed
		v = cv$value;
		setFlag$v = true;
		
		// Unset the fixed probability flag for sample 56 as it depends on v.
		fixedProbFlag$sample56 = false;
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
		
		// Unset the fixed probability flag for sample 56 as it depends on v1.
		fixedProbFlag$sample56 = false;
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
		
		// Unset the fixed probability flag for sample 34 as it depends on v2.
		fixedProbFlag$sample34 = false;
		
		// Unset the fixed probability flag for sample 56 as it depends on v2.
		fixedProbFlag$sample56 = false;
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
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample18)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$v2 = (logProbability$v2 + logProbability$var15);
			
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

	// Calculate the probability of the samples represented by sample34 using probability
	// distributions.
	private final void logProbabilityDistribution$sample34() {
		// Determine if we need to calculate the values for sample task 34 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample34) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample34) {
				// Generating probabilities for sample task
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int i = 0; i < size; i += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = v2[(i + 1)];
					
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
				logProbability$var30 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var31 = cv$sampleAccumulator;
				
				// Update the variable probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$v2 = (logProbability$v2 + cv$sampleAccumulator);
				
				// Add probability to model
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample34" with its value "true".
				fixedProbFlag$sample34 = true;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var30 = logProbability$var31;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample34)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$v2 = (logProbability$v2 + logProbability$var31);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var31);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample34)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var31);
		}
	}

	// Calculate the probability of the samples represented by sample56 using probability
	// distributions.
	private final void logProbabilityDistribution$sample56() {
		// Determine if we need to calculate the values for sample task 56 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample56) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 56 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				boolean cv$sampleValue = v[j];
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == j)) {
					// Enumerating the possible arguments for Bernoulli 52.
					// 
					// Enumerating the possible arguments for Bernoulli 52.
					// 
					// Enumerating the possible arguments for Bernoulli 52.
					// 
					// Enumerating the possible arguments for Bernoulli 52.
					// 
					// Enumerating the possible arguments for Bernoulli 52.
					if(fixedFlag$sample12) {
						if(fixedFlag$sample18) {
							// Store the value of the function call, so the function call is only made once.
							// 
							// Substituted "j" with its value "0".
							cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[0]) + v2[0]) / v2[0]));
							
							// Add the probability of this distribution configuration to the accumulator.
							// 
							// An accumulator for the distributed probability space covered.
							cv$probabilityReached = 1.0;
						} else {
							// Enumerating the possible outputs of Categorical 14.
							for(int index$sample18$8 = 0; index$sample18$8 < weightings.length; index$sample18$8 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample18Value9 = distribution$sample18[index$sample18$8];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample18Value9) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + index$sample18$8) + index$sample18$8) / index$sample18$8)));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value9);
							}
						}
					}
					// Enumerating the possible arguments for Bernoulli 52.
					// 
					// Enumerating the possible arguments for Bernoulli 52.
					else {
						// Enumerating the possible outputs of Categorical 8.
						for(int index$sample12$3 = 0; index$sample12$3 < weightings.length; index$sample12$3 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample12Value4 = distribution$sample12[index$sample12$3];
							if(fixedFlag$sample18) {
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "j" with its value "0".
								double cv$weightedProbability = (Math.log(cv$probabilitySample12Value4) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$3 + v2[0]) + v2[0]) / v2[0])));
								
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
								// Enumerating the possible outputs of Categorical 14.
								for(int index$sample18$13 = 0; index$sample18$13 < weightings.length; index$sample18$13 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample18Value14 = (cv$probabilitySample12Value4 * distribution$sample18[index$sample18$13]);
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample18Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$3 + index$sample18$13) + index$sample18$13) / index$sample18$13)));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value14);
								}
							}
						}
					}
				}
				
				// Enumerating the possible arguments for Bernoulli 52.
				if(fixedFlag$sample12) {
					if(fixedFlag$sample34) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 <= j)) {
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[j]) + v2[j]) / v2[j]));
							
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
							// Enumerating the possible outputs of Categorical 30.
							for(int index$sample34$440 = 0; index$sample34$440 < weightings.length; index$sample34$440 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample34Value441 = distribution$sample34[i][index$sample34$440];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample34Value441) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + index$sample34$440) + index$sample34$440) / index$sample34$440)));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value441);
							}
						}
					}
				} else {
					// Enumerating the possible outputs of Categorical 8.
					for(int index$sample12$434 = 0; index$sample12$434 < weightings.length; index$sample12$434 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample12Value435 = distribution$sample12[index$sample12$434];
						if(fixedFlag$sample34) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((1 <= j)) {
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample12Value435) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$434 + v2[j]) + v2[j]) / v2[j])));
								
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
						} else {
							int i = (j - 1);
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((0 <= i)) {
								// Enumerating the possible outputs of Categorical 30.
								for(int index$sample34$446 = 0; index$sample34$446 < weightings.length; index$sample34$446 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample34Value447 = (cv$probabilitySample12Value435 * distribution$sample34[i][index$sample34$446]);
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample34Value447) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$434 + index$sample34$446) + index$sample34$446) / index$sample34$446)));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value447);
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
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
			}
			logProbability$var52 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var53 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$v = (logProbability$v + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample56 = (((fixedFlag$sample56 && fixedFlag$sample12) && fixedFlag$sample18) && fixedFlag$sample34);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var52 = logProbability$var53;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$v = (logProbability$v + logProbability$var53);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var53);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var53);
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

	// Calculate the probability of the samples represented by sample34 using sampled
	// values.
	private final void logProbabilityValue$sample34() {
		// Determine if we need to calculate the values for sample task 34 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample34) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = v2[(i + 1)];
				
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
			logProbability$var30 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var31 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$v2 = (logProbability$v2 + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample34)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample34 = fixedFlag$sample34;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var30 = logProbability$var31;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$v2 = (logProbability$v2 + logProbability$var31);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var31);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample34)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var31);
		}
	}

	// Calculate the probability of the samples represented by sample56 using sampled
	// values.
	private final void logProbabilityValue$sample56() {
		// Determine if we need to calculate the values for sample task 56 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample56) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int j = 0; j < size; j += 1)
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
				// 
				// The sample value to calculate the probability of generating
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(v[j], ((double)((v1 + v2[j]) + v2[j]) / v2[j])));
			logProbability$var52 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var53 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$v = (logProbability$v + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample56 = (((fixedFlag$sample56 && fixedFlag$sample12) && fixedFlag$sample18) && fixedFlag$sample34);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var52 = logProbability$var53;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$v = (logProbability$v + logProbability$var53);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var53);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var53);
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
			
			// Processing random variable 52.
			for(int j = 0; j < size; j += 1) {
				// Processing sample task 56 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
				// the output of Sample task 12.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == j)) {
					if(fixedFlag$sample18) {
						// Substituted "j" with its value "0".
						// 
						// Substituted "cv$temp$1$var51" with its value "var51".
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "j" with its value "0".
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((cv$valuePos + v2[0]) + v2[0]) / v2[0]));
						
						// Recorded the probability of reaching sample task 56 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						// Enumerating the possible outputs of Categorical 14.
						for(int index$sample18$4 = 0; index$sample18$4 < weightings.length; index$sample18$4 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample18Value5 = distribution$sample18[index$sample18$4];
							
							// Variable declaration of cv$temp$2$var51 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$2$var51 = ((double)((cv$valuePos + index$sample18$4) + index$sample18$4) / index$sample18$4);
							
							// Record the probability of sample task 56 generating output with current configuration.
							// 
							// Substituted "j" with its value "0".
							if(((Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var51)) < cv$accumulatedConsumerProbabilities))
								// Substituted "j" with its value "0".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var51));
								else
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var51)))) + 1)) + Math.log(cv$probabilitySample18Value5)) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var51));
							}
							
							// Recorded the probability of reaching sample task 56 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value5);
						}
					}
				}
				
				// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
				// the output of Sample task 12.
				if(fixedFlag$sample34) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 <= j)) {
						// Variable declaration of cv$temp$43$var51 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double cv$temp$43$var51 = ((double)((cv$valuePos + v2[j]) + v2[j]) / v2[j]);
						
						// Record the probability of sample task 56 generating output with current configuration.
						if((DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var51) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var51) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var51);
							else
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var51))) + 1)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var51));
						}
						
						// Recorded the probability of reaching sample task 56 with the current configuration.
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
					}
				} else {
					int i = (j - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 <= i)) {
						// Enumerating the possible outputs of Categorical 30.
						for(int index$sample34$203 = 0; index$sample34$203 < weightings.length; index$sample34$203 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample34Value204 = distribution$sample34[i][index$sample34$203];
							
							// Variable declaration of cv$temp$44$var51 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$44$var51 = ((double)((cv$valuePos + index$sample34$203) + index$sample34$203) / index$sample34$203);
							
							// Record the probability of sample task 56 generating output with current configuration.
							if(((Math.log(cv$probabilitySample34Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var51)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var51));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var51)))) + 1)) + Math.log(cv$probabilitySample34Value204)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var51));
							}
							
							// Recorded the probability of reaching sample task 56 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value204);
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
				guard$sample18bernoulli55$global[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample18bernoulli55$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample18bernoulli55$global[0] = true;
					
					// Processing sample task 56 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
					// the output of Sample task 18.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
					// the output of Sample task 18.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
					// the output of Sample task 18.
					if(fixedFlag$sample12) {
						// Substituted "j" with its value "0".
						// 
						// cv$temp$1$var51's comment
						// Variable declaration of cv$temp$1$var51 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
						
						// Recorded the probability of reaching sample task 56 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						// Enumerating the possible outputs of Categorical 8.
						for(int index$sample12$11 = 0; index$sample12$11 < weightings.length; index$sample12$11 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample12Value12 = distribution$sample12[index$sample12$11];
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var51 = ((double)((index$sample12$11 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 56 generating output with current configuration.
							// 
							// Substituted "j" with its value "0".
							// 
							// Substituted "cv$temp$6$var51" with its value "var51".
							if(((Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[0], var51)) < cv$accumulatedConsumerProbabilities))
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$6$var51" with its value "var51".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[0], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$6$var51" with its value "var51".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[0], var51));
								else
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$6$var51" with its value "var51".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[0], var51)))) + 1)) + Math.log(cv$probabilitySample12Value12)) + DistributionSampling.logProbabilityBernoulli(v[0], var51));
							}
							
							// Recorded the probability of reaching sample task 56 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value12);
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
				if(!guard$sample18bernoulli55$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample18bernoulli55$global[0] = true;
					
					// Processing sample task 56 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
					// the output of Sample task 18.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
					// the output of Sample task 18.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
					// the output of Sample task 18.
					if(fixedFlag$sample12) {
						// Substituted "j" with its value "0".
						// 
						// cv$temp$33$var51's comment
						// Variable declaration of cv$temp$33$var51 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
						
						// Recorded the probability of reaching sample task 56 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						// Enumerating the possible outputs of Categorical 8.
						for(int index$sample12$155 = 0; index$sample12$155 < weightings.length; index$sample12$155 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample12Value156 = distribution$sample12[index$sample12$155];
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var51 = ((double)((index$sample12$155 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 56 generating output with current configuration.
							// 
							// Substituted "j" with its value "0".
							// 
							// Substituted "cv$temp$38$var51" with its value "var51".
							if(((Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[0], var51)) < cv$accumulatedConsumerProbabilities))
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$38$var51" with its value "var51".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[0], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$38$var51" with its value "var51".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[0], var51));
								else
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$38$var51" with its value "var51".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[0], var51)))) + 1)) + Math.log(cv$probabilitySample12Value156)) + DistributionSampling.logProbabilityBernoulli(v[0], var51));
							}
							
							// Recorded the probability of reaching sample task 56 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value156);
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
				
				// Substituted "j" with its value "0".
				if(!guard$sample18bernoulli55$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample18bernoulli55$global[0] = true;
					
					// Processing sample task 56 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
					// the output of Sample task 18.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
					// the output of Sample task 18.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
					// the output of Sample task 18.
					if(fixedFlag$sample12) {
						// Substituted "j" with its value "0".
						// 
						// cv$temp$65$var51's comment
						// Variable declaration of cv$temp$65$var51 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
						
						// Recorded the probability of reaching sample task 56 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						// Enumerating the possible outputs of Categorical 8.
						for(int index$sample12$299 = 0; index$sample12$299 < weightings.length; index$sample12$299 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample12Value300 = distribution$sample12[index$sample12$299];
							
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var51 = ((double)((index$sample12$299 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 56 generating output with current configuration.
							// 
							// Substituted "j" with its value "0".
							// 
							// Substituted "cv$temp$70$var51" with its value "var51".
							if(((Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[0], var51)) < cv$accumulatedConsumerProbabilities))
								// Substituted "j" with its value "0".
								// 
								// Substituted "cv$temp$70$var51" with its value "var51".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[0], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$70$var51" with its value "var51".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[0], var51));
								else
									// Substituted "j" with its value "0".
									// 
									// Substituted "cv$temp$70$var51" with its value "var51".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[0], var51)))) + 1)) + Math.log(cv$probabilitySample12Value300)) + DistributionSampling.logProbabilityBernoulli(v[0], var51));
							}
							
							// Recorded the probability of reaching sample task 56 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value300);
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
	// by sample task 34 drawn from Categorical 30. Inference was performed using variable
	// marginalization.
	private final void sample34(int i) {
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
					guard$sample34bernoulli55$global[j] = false;
			}
			{
				int j = (i + 1);
				if((j < size))
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample34bernoulli55$global[j] = false;
			}
			{
				int j = (i + 1);
				if((j < size))
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample34bernoulli55$global[j] = false;
			}
			{
				int j = (i + 1);
				if(((j < size) && !guard$sample34bernoulli55$global[j])) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample34bernoulli55$global[j] = true;
					
					// Processing sample task 56 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
					// the output of Sample task 34.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
					// the output of Sample task 34.
					if(fixedFlag$sample12) {
						// cv$temp$23$var51's comment
						// Variable declaration of cv$temp$23$var51 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[j], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
						
						// Recorded the probability of reaching sample task 56 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
						int index$i$133 = (j - 1);
						
						// index$i$2's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						if(!(index$i$133 == i)) {
							// Enumerating the possible outputs of Categorical 30.
							for(int index$sample34$134 = 0; index$sample34$134 < weightings.length; index$sample34$134 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample34Value135 = distribution$sample34[index$i$133][index$sample34$134];
								
								// Variable declaration of cv$temp$24$var51 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$24$var51 = ((double)((v1 + cv$valuePos) + cv$valuePos) / index$sample34$134);
								
								// Record the probability of sample task 56 generating output with current configuration.
								if(((Math.log(cv$probabilitySample34Value135) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var51)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value135) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value135) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var51));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value135) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var51)))) + 1)) + Math.log(cv$probabilitySample34Value135)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var51));
								}
								
								// Recorded the probability of reaching sample task 56 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value135);
							}
						}
						int index$i$121 = (j - 1);
						
						// index$i$2's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						if(!(index$i$121 == i)) {
							// Enumerating the possible outputs of Categorical 30.
							for(int index$sample34$122 = 0; index$sample34$122 < weightings.length; index$sample34$122 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample34Value123 = distribution$sample34[index$i$121][index$sample34$122];
								
								// Variable declaration of cv$temp$25$var51 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$25$var51 = ((double)((v1 + cv$valuePos) + index$sample34$122) / cv$valuePos);
								
								// Record the probability of sample task 56 generating output with current configuration.
								if(((Math.log(cv$probabilitySample34Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var51)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var51));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var51)))) + 1)) + Math.log(cv$probabilitySample34Value123)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var51));
								}
								
								// Recorded the probability of reaching sample task 56 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value123);
								
								// Variable declaration of cv$temp$26$var51 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$26$var51 = ((double)((v1 + cv$valuePos) + index$sample34$122) / index$sample34$122);
								
								// Record the probability of sample task 56 generating output with current configuration.
								if(((Math.log(cv$probabilitySample34Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var51)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var51));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var51)))) + 1)) + Math.log(cv$probabilitySample34Value123)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var51));
								}
								
								// Recorded the probability of reaching sample task 56 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value123);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 8.
						for(int index$sample12$116 = 0; index$sample12$116 < weightings.length; index$sample12$116 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample12Value117 = distribution$sample12[index$sample12$116];
							{
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var51 = ((double)((index$sample12$116 + cv$valuePos) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 56 generating output with current configuration.
								// 
								// Substituted "cv$temp$28$var51" with its value "var51".
								if(((Math.log(cv$probabilitySample12Value117) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
									// Substituted "cv$temp$28$var51" with its value "var51".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value117) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "cv$temp$28$var51" with its value "var51".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value117) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
									else
										// Substituted "cv$temp$28$var51" with its value "var51".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value117) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample12Value117)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
								}
								
								// Recorded the probability of reaching sample task 56 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value117);
							}
							int index$i$146 = (j - 1);
							
							// index$i$2's comment
							// Copy of index so that its values can be safely substituted
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							if(!(index$i$146 == i)) {
								// Enumerating the possible outputs of Categorical 30.
								for(int index$sample34$147 = 0; index$sample34$147 < weightings.length; index$sample34$147 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample34Value148 = (cv$probabilitySample12Value117 * distribution$sample34[index$i$146][index$sample34$147]);
									
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var51 = ((double)((index$sample12$116 + cv$valuePos) + cv$valuePos) / index$sample34$147);
									
									// Record the probability of sample task 56 generating output with current configuration.
									// 
									// Substituted "cv$temp$29$var51" with its value "var51".
									if(((Math.log(cv$probabilitySample34Value148) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
										// Substituted "cv$temp$29$var51" with its value "var51".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value148) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "cv$temp$29$var51" with its value "var51".
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value148) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
										else
											// Substituted "cv$temp$29$var51" with its value "var51".
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value148) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample34Value148)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
									}
									
									// Recorded the probability of reaching sample task 56 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value148);
								}
							}
							int index$i$127 = (j - 1);
							
							// index$i$2's comment
							// Copy of index so that its values can be safely substituted
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							if(!(index$i$127 == i)) {
								// Enumerating the possible outputs of Categorical 30.
								for(int index$sample34$128 = 0; index$sample34$128 < weightings.length; index$sample34$128 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample34Value129 = (cv$probabilitySample12Value117 * distribution$sample34[index$i$127][index$sample34$128]);
									{
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double var51 = ((double)((index$sample12$116 + cv$valuePos) + index$sample34$128) / cv$valuePos);
										
										// Record the probability of sample task 56 generating output with current configuration.
										// 
										// Substituted "cv$temp$30$var51" with its value "var51".
										if(((Math.log(cv$probabilitySample34Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
											// Substituted "cv$temp$30$var51" with its value "var51".
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											// If the second value is -infinity.
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												// Substituted "cv$temp$30$var51" with its value "var51".
												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
											else
												// Substituted "cv$temp$30$var51" with its value "var51".
												cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample34Value129)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
										}
										
										// Recorded the probability of reaching sample task 56 with the current configuration.
										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value129);
									}
									
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var51 = ((double)((index$sample12$116 + cv$valuePos) + index$sample34$128) / index$sample34$128);
									
									// Record the probability of sample task 56 generating output with current configuration.
									// 
									// Substituted "cv$temp$31$var51" with its value "var51".
									if(((Math.log(cv$probabilitySample34Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
										// Substituted "cv$temp$31$var51" with its value "var51".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "cv$temp$31$var51" with its value "var51".
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
										else
											// Substituted "cv$temp$31$var51" with its value "var51".
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample34Value129)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
									}
									
									// Recorded the probability of reaching sample task 56 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value129);
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
			{
				int j = (i + 1);
				if(((j < size) && !guard$sample34bernoulli55$global[j])) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample34bernoulli55$global[j] = true;
					
					// Processing sample task 56 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
					// the output of Sample task 34.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
					// the output of Sample task 34.
					if(fixedFlag$sample12) {
						// cv$temp$55$var51's comment
						// Variable declaration of cv$temp$55$var51 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[j], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
						
						// Recorded the probability of reaching sample task 56 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
						int index$i$279 = (j - 1);
						
						// index$i$2's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						if(!(index$i$279 == i)) {
							// Enumerating the possible outputs of Categorical 30.
							for(int index$sample34$280 = 0; index$sample34$280 < weightings.length; index$sample34$280 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample34Value281 = distribution$sample34[index$i$279][index$sample34$280];
								
								// Variable declaration of cv$temp$56$var51 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$56$var51 = ((double)((v1 + cv$valuePos) + cv$valuePos) / index$sample34$280);
								
								// Record the probability of sample task 56 generating output with current configuration.
								if(((Math.log(cv$probabilitySample34Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var51)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var51));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var51)))) + 1)) + Math.log(cv$probabilitySample34Value281)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var51));
								}
								
								// Recorded the probability of reaching sample task 56 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value281);
							}
						}
						int index$i$267 = (j - 1);
						
						// index$i$2's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						if(!(index$i$267 == i)) {
							// Enumerating the possible outputs of Categorical 30.
							for(int index$sample34$268 = 0; index$sample34$268 < weightings.length; index$sample34$268 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample34Value269 = distribution$sample34[index$i$267][index$sample34$268];
								
								// Variable declaration of cv$temp$57$var51 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$57$var51 = ((double)((v1 + index$sample34$268) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 56 generating output with current configuration.
								if(((Math.log(cv$probabilitySample34Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var51)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var51));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var51)))) + 1)) + Math.log(cv$probabilitySample34Value269)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var51));
								}
								
								// Recorded the probability of reaching sample task 56 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value269);
								
								// Variable declaration of cv$temp$58$var51 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$58$var51 = ((double)((v1 + index$sample34$268) + cv$valuePos) / index$sample34$268);
								
								// Record the probability of sample task 56 generating output with current configuration.
								if(((Math.log(cv$probabilitySample34Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var51)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var51));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var51)))) + 1)) + Math.log(cv$probabilitySample34Value269)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var51));
								}
								
								// Recorded the probability of reaching sample task 56 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value269);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 8.
						for(int index$sample12$262 = 0; index$sample12$262 < weightings.length; index$sample12$262 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample12Value263 = distribution$sample12[index$sample12$262];
							{
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var51 = ((double)((index$sample12$262 + cv$valuePos) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 56 generating output with current configuration.
								// 
								// Substituted "cv$temp$60$var51" with its value "var51".
								if(((Math.log(cv$probabilitySample12Value263) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
									// Substituted "cv$temp$60$var51" with its value "var51".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value263) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "cv$temp$60$var51" with its value "var51".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value263) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
									else
										// Substituted "cv$temp$60$var51" with its value "var51".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value263) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample12Value263)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
								}
								
								// Recorded the probability of reaching sample task 56 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value263);
							}
							int index$i$292 = (j - 1);
							
							// index$i$2's comment
							// Copy of index so that its values can be safely substituted
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							if(!(index$i$292 == i)) {
								// Enumerating the possible outputs of Categorical 30.
								for(int index$sample34$293 = 0; index$sample34$293 < weightings.length; index$sample34$293 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample34Value294 = (cv$probabilitySample12Value263 * distribution$sample34[index$i$292][index$sample34$293]);
									
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var51 = ((double)((index$sample12$262 + cv$valuePos) + cv$valuePos) / index$sample34$293);
									
									// Record the probability of sample task 56 generating output with current configuration.
									// 
									// Substituted "cv$temp$61$var51" with its value "var51".
									if(((Math.log(cv$probabilitySample34Value294) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
										// Substituted "cv$temp$61$var51" with its value "var51".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value294) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "cv$temp$61$var51" with its value "var51".
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value294) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
										else
											// Substituted "cv$temp$61$var51" with its value "var51".
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value294) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample34Value294)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
									}
									
									// Recorded the probability of reaching sample task 56 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value294);
								}
							}
							int index$i$273 = (j - 1);
							
							// index$i$2's comment
							// Copy of index so that its values can be safely substituted
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							if(!(index$i$273 == i)) {
								// Enumerating the possible outputs of Categorical 30.
								for(int index$sample34$274 = 0; index$sample34$274 < weightings.length; index$sample34$274 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample34Value275 = (cv$probabilitySample12Value263 * distribution$sample34[index$i$273][index$sample34$274]);
									{
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double var51 = ((double)((index$sample12$262 + index$sample34$274) + cv$valuePos) / cv$valuePos);
										
										// Record the probability of sample task 56 generating output with current configuration.
										// 
										// Substituted "cv$temp$62$var51" with its value "var51".
										if(((Math.log(cv$probabilitySample34Value275) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
											// Substituted "cv$temp$62$var51" with its value "var51".
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value275) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											// If the second value is -infinity.
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												// Substituted "cv$temp$62$var51" with its value "var51".
												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value275) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
											else
												// Substituted "cv$temp$62$var51" with its value "var51".
												cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value275) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample34Value275)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
										}
										
										// Recorded the probability of reaching sample task 56 with the current configuration.
										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value275);
									}
									
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var51 = ((double)((index$sample12$262 + index$sample34$274) + cv$valuePos) / index$sample34$274);
									
									// Record the probability of sample task 56 generating output with current configuration.
									// 
									// Substituted "cv$temp$63$var51" with its value "var51".
									if(((Math.log(cv$probabilitySample34Value275) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
										// Substituted "cv$temp$63$var51" with its value "var51".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value275) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "cv$temp$63$var51" with its value "var51".
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value275) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
										else
											// Substituted "cv$temp$63$var51" with its value "var51".
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value275) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample34Value275)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
									}
									
									// Recorded the probability of reaching sample task 56 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value275);
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
			int j = (i + 1);
			if(((j < size) && !guard$sample34bernoulli55$global[j])) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample34bernoulli55$global[j] = true;
				
				// Processing sample task 56 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
				// the output of Sample task 34.
				// 
				// Enumerating the possible arguments for the variable Bernoulli 52 which is consuming
				// the output of Sample task 34.
				if(fixedFlag$sample12) {
					// cv$temp$87$var51's comment
					// Variable declaration of cv$temp$87$var51 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[j], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
					
					// Recorded the probability of reaching sample task 56 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					cv$consumerDistributionProbabilityAccumulator = 0.0;
					int index$i$425 = (j - 1);
					
					// index$i$2's comment
					// Copy of index so that its values can be safely substituted
					// 
					// Substituted "j" with its value "(i + 1)".
					// 
					// Substituted "j" with its value "(i + 1)".
					// 
					// Substituted "j" with its value "(i + 1)".
					// 
					// Substituted "j" with its value "(i + 1)".
					if(!(index$i$425 == i)) {
						// Enumerating the possible outputs of Categorical 30.
						for(int index$sample34$426 = 0; index$sample34$426 < weightings.length; index$sample34$426 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample34Value427 = distribution$sample34[index$i$425][index$sample34$426];
							
							// Variable declaration of cv$temp$88$var51 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$88$var51 = ((double)((v1 + cv$valuePos) + index$sample34$426) / cv$valuePos);
							
							// Record the probability of sample task 56 generating output with current configuration.
							if(((Math.log(cv$probabilitySample34Value427) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var51)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value427) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value427) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var51));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value427) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var51)))) + 1)) + Math.log(cv$probabilitySample34Value427)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var51));
							}
							
							// Recorded the probability of reaching sample task 56 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value427);
						}
					}
					int index$i$413 = (j - 1);
					
					// index$i$2's comment
					// Copy of index so that its values can be safely substituted
					// 
					// Substituted "j" with its value "(i + 1)".
					// 
					// Substituted "j" with its value "(i + 1)".
					// 
					// Substituted "j" with its value "(i + 1)".
					// 
					// Substituted "j" with its value "(i + 1)".
					if(!(index$i$413 == i)) {
						// Enumerating the possible outputs of Categorical 30.
						for(int index$sample34$414 = 0; index$sample34$414 < weightings.length; index$sample34$414 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample34Value415 = distribution$sample34[index$i$413][index$sample34$414];
							
							// Variable declaration of cv$temp$89$var51 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$89$var51 = ((double)((v1 + index$sample34$414) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 56 generating output with current configuration.
							if(((Math.log(cv$probabilitySample34Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var51)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var51));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var51)))) + 1)) + Math.log(cv$probabilitySample34Value415)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var51));
							}
							
							// Recorded the probability of reaching sample task 56 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value415);
							
							// Variable declaration of cv$temp$90$var51 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$90$var51 = ((double)((v1 + index$sample34$414) + index$sample34$414) / cv$valuePos);
							
							// Record the probability of sample task 56 generating output with current configuration.
							if(((Math.log(cv$probabilitySample34Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var51)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var51));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var51)))) + 1)) + Math.log(cv$probabilitySample34Value415)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var51));
							}
							
							// Recorded the probability of reaching sample task 56 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value415);
						}
					}
				} else {
					// Enumerating the possible outputs of Categorical 8.
					for(int index$sample12$408 = 0; index$sample12$408 < weightings.length; index$sample12$408 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample12Value409 = distribution$sample12[index$sample12$408];
						{
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double var51 = ((double)((index$sample12$408 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 56 generating output with current configuration.
							// 
							// Substituted "cv$temp$92$var51" with its value "var51".
							if(((Math.log(cv$probabilitySample12Value409) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
								// Substituted "cv$temp$92$var51" with its value "var51".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value409) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "cv$temp$92$var51" with its value "var51".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value409) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
								else
									// Substituted "cv$temp$92$var51" with its value "var51".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value409) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample12Value409)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
							}
							
							// Recorded the probability of reaching sample task 56 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value409);
						}
						int index$i$438 = (j - 1);
						
						// index$i$2's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						if(!(index$i$438 == i)) {
							// Enumerating the possible outputs of Categorical 30.
							for(int index$sample34$439 = 0; index$sample34$439 < weightings.length; index$sample34$439 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample34Value440 = (cv$probabilitySample12Value409 * distribution$sample34[index$i$438][index$sample34$439]);
								
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var51 = ((double)((index$sample12$408 + cv$valuePos) + index$sample34$439) / cv$valuePos);
								
								// Record the probability of sample task 56 generating output with current configuration.
								// 
								// Substituted "cv$temp$93$var51" with its value "var51".
								if(((Math.log(cv$probabilitySample34Value440) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
									// Substituted "cv$temp$93$var51" with its value "var51".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value440) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "cv$temp$93$var51" with its value "var51".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value440) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
									else
										// Substituted "cv$temp$93$var51" with its value "var51".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value440) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample34Value440)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
								}
								
								// Recorded the probability of reaching sample task 56 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value440);
							}
						}
						int index$i$419 = (j - 1);
						
						// index$i$2's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						if(!(index$i$419 == i)) {
							// Enumerating the possible outputs of Categorical 30.
							for(int index$sample34$420 = 0; index$sample34$420 < weightings.length; index$sample34$420 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample34Value421 = (cv$probabilitySample12Value409 * distribution$sample34[index$i$419][index$sample34$420]);
								{
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double var51 = ((double)((index$sample12$408 + index$sample34$420) + cv$valuePos) / cv$valuePos);
									
									// Record the probability of sample task 56 generating output with current configuration.
									// 
									// Substituted "cv$temp$94$var51" with its value "var51".
									if(((Math.log(cv$probabilitySample34Value421) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
										// Substituted "cv$temp$94$var51" with its value "var51".
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value421) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											// Substituted "cv$temp$94$var51" with its value "var51".
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value421) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
										else
											// Substituted "cv$temp$94$var51" with its value "var51".
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value421) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample34Value421)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
									}
									
									// Recorded the probability of reaching sample task 56 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value421);
								}
								
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double var51 = ((double)((index$sample12$408 + index$sample34$420) + index$sample34$420) / cv$valuePos);
								
								// Record the probability of sample task 56 generating output with current configuration.
								// 
								// Substituted "cv$temp$95$var51" with its value "var51".
								if(((Math.log(cv$probabilitySample34Value421) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
									// Substituted "cv$temp$95$var51" with its value "var51".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value421) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										// Substituted "cv$temp$95$var51" with its value "var51".
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value421) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
									else
										// Substituted "cv$temp$95$var51" with its value "var51".
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value421) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample34Value421)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
								}
								
								// Recorded the probability of reaching sample task 56 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value421);
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
			cv$var31$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample34[i];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var31$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var31$stateProbabilityGlobal[cv$lseIndex];
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
				cv$lseSum = (cv$lseSum + Math.exp((cv$var31$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
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
				cv$localProbability[cv$indexName] = Math.exp((cv$var31$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var31$stateProbabilityGlobal.length; cv$indexName += 1)
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
		
		// Constructor for guard$sample18bernoulli55$global
		// 
		// Allocation of guard$sample18bernoulli55$global for single threaded execution
		guard$sample18bernoulli55$global = new boolean[length$value];
		
		// Constructor for cv$var31$stateProbabilityGlobal
		// 
		// Allocation of cv$var31$stateProbabilityGlobal for single threaded execution
		cv$var31$stateProbabilityGlobal = new double[weightings.length];
		
		// Allocation of guard$sample34bernoulli55$global for single threaded execution
		guard$sample34bernoulli55$global = new boolean[length$value];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If v2 has not been set already allocate space.
		if(!setFlag$v2)
			// Constructor for v2
			v2 = new int[(length$value + 1)];
		
		// If v has not been set already allocate space.
		if(!setFlag$v)
			// Constructor for v
			v = new boolean[length$value];
		
		// Constructor for distribution$sample18
		distribution$sample18 = new double[weightings.length];
		
		// Constructor for distribution$sample34
		distribution$sample34 = new double[length$value][];
		for(int i = 0; i < length$value; i += 1)
			distribution$sample34[i] = new double[weightings.length];
		
		// Constructor for distribution$sample12
		distribution$sample12 = new double[weightings.length];
		
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
		if(!fixedFlag$sample34) {
			for(int i = 0; i < size; i += 1)
				v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$, weightings);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample56) {
			for(int j = 0; j < size; j += 1)
				v[j] = DistributionSampling.sampleBernoulli(RNG$, ((double)((v1 + v2[j]) + v2[j]) / v2[j]));
		}
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
		if(!fixedFlag$sample34) {
			for(int i = 0; i < size; i += 1) {
				// Create local copy of variable probabilities.
				double[] cv$distribution$sample34 = distribution$sample34[i];
				for(int index$var30 = 0; index$var30 < weightings.length; index$var30 += 1)
					// Save the probability of each value
					// 
					// Probability for this value
					cv$distribution$sample34[index$var30] = weightings[index$var30];
			}
		}
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
		if(!fixedFlag$sample34) {
			for(int i = 0; i < size; i += 1)
				v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$, weightings);
		}
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
			if(!fixedFlag$sample34) {
				for(int i = 0; i < size; i += 1)
					sample34(i);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample34) {
				for(int i = (size - 1); i >= 0; i -= 1)
					sample34(i);
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
		logProbability$v2 = 0.0;
		if(!fixedProbFlag$sample18)
			logProbability$var15 = 0.0;
		logProbability$var30 = 0.0;
		if(!fixedProbFlag$sample34)
			logProbability$var31 = 0.0;
		logProbability$var52 = 0.0;
		logProbability$v = 0.0;
		if(!fixedProbFlag$sample56)
			logProbability$var53 = 0.0;
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
		logProbabilityValue$sample56();
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
		logProbabilityDistribution$sample34();
		logProbabilityDistribution$sample56();
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
		logProbabilityValue$sample34();
		logProbabilityValue$sample56();
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
		if(!fixedFlag$sample34) {
			for(int i = 0; i < size; i += 1)
				v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$, weightings);
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel DistributionTest6(double[] weightings, boolean[] value) {\n    int size = value.length;\n    \n    int v1 = categorical(weightings).sampleDistribution();\n    \n    int[] v2 = new int[size + 1];\n    v2[0] = categorical(weightings).sampleDistribution();\n    for(int i:[0..size))\n        v2[i + 1] = categorical(weightings).sampleDistribution();\n        \n    boolean[] v = new boolean[size];\n    for(int j:[0..size))\n        v[j] = bernoulli(((1.0*v1) + v2[j] + v2[j])/v2[j]).sample();\n        \n    v.observe(value);\n}\n";
	}
}