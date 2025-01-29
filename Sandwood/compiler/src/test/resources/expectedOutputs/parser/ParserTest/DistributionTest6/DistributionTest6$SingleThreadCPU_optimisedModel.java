package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DistributionTest6$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements DistributionTest6$CoreInterface {
	
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
	private boolean fixedFlag$sample41 = false;
	private boolean fixedProbFlag$sample12 = false;
	private boolean fixedProbFlag$sample18 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean fixedProbFlag$sample41 = false;
	private boolean[] guard$sample18bernoulli40$global;
	private boolean[] guard$sample26bernoulli40$global;
	private int length$value;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$sample26;
	private double[] logProbability$sample41;
	private double logProbability$v;
	private double logProbability$v1;
	private double logProbability$v2;
	private double logProbability$var14;
	private double logProbability$var15;
	private double[] logProbability$var22;
	private double[] logProbability$var37;
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
		
		// Should the probability of sample 41 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample12" with its value "cv$value".
		fixedProbFlag$sample41 = (cv$value && fixedProbFlag$sample41);
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
		
		// Should the probability of sample 41 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample18" with its value "cv$value".
		fixedProbFlag$sample41 = (cv$value && fixedProbFlag$sample41);
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
		
		// Should the probability of sample 41 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample26" with its value "cv$value".
		fixedProbFlag$sample41 = (cv$value && fixedProbFlag$sample41);
	}

	// Getter for fixedFlag$sample41.
	@Override
	public final boolean get$fixedFlag$sample41() {
		return fixedFlag$sample41;
	}

	// Setter for fixedFlag$sample41.
	@Override
	public final void set$fixedFlag$sample41(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample41 including if probabilities
		// need to be updated.
		fixedFlag$sample41 = cv$value;
		
		// Should the probability of sample 41 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample41" with its value "cv$value".
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

	// Setter for v.
	@Override
	public final void set$v(boolean[] cv$value) {
		// Set flags for all the side effects of v including if probabilities need to be updated.
		// Set v with flag to mark that it has been set so another array doesn't need to be
		// constructed
		v = cv$value;
		setFlag$v = true;
		
		// Unset the fixed probability flag for sample 41 as it depends on v.
		fixedProbFlag$sample41 = false;
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
		// Set v2 with flag to mark that it has been set so another array doesn't need to
		// be constructed
		v2 = cv$value;
		setFlag$v2 = true;
		
		// Unset the fixed probability flag for sample 18 as it depends on v2.
		fixedProbFlag$sample18 = false;
		
		// Unset the fixed probability flag for sample 26 as it depends on v2.
		fixedProbFlag$sample26 = false;
		
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
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample26[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var22[i] = cv$rvAccumulator;
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
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == j)) {
					// Enumerating the possible arguments for Bernoulli 37.
					// 
					// Enumerating the possible arguments for Bernoulli 37.
					// 
					// Enumerating the possible arguments for Bernoulli 37.
					// 
					// Enumerating the possible arguments for Bernoulli 37.
					// 
					// Enumerating the possible arguments for Bernoulli 37.
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
					// Enumerating the possible arguments for Bernoulli 37.
					// 
					// Enumerating the possible arguments for Bernoulli 37.
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
				
				// Enumerating the possible arguments for Bernoulli 37.
				if(fixedFlag$sample12) {
					if(fixedFlag$sample26) {
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
							// Enumerating the possible outputs of Categorical 22.
							for(int index$sample26$440 = 0; index$sample26$440 < weightings.length; index$sample26$440 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample26Value441 = distribution$sample26[i][index$sample26$440];
								
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
								// Enumerating the possible outputs of Categorical 22.
								for(int index$sample26$446 = 0; index$sample26$446 < weightings.length; index$sample26$446 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample26Value447 = (cv$probabilitySample12Value435 * distribution$sample26[i][index$sample26$446]);
									
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
				logProbability$var37[j] = cv$distributionAccumulator;
				
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
			fixedProbFlag$sample41 = (((fixedFlag$sample41 && fixedFlag$sample12) && fixedFlag$sample18) && fixedFlag$sample26);
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
				logProbability$var37[j] = cv$rvAccumulator;
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
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample26[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var22[i] = cv$rvAccumulator;
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(v[j], ((double)((v1 + v2[j]) + v2[j]) / v2[j]));
				
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
				logProbability$var37[j] = cv$distributionAccumulator;
				
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
			fixedProbFlag$sample41 = (((fixedFlag$sample41 && fixedFlag$sample12) && fixedFlag$sample18) && fixedFlag$sample26);
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
				logProbability$var37[j] = cv$rvAccumulator;
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
		for(int cv$valuePos = 0; cv$valuePos < weightings.length; cv$valuePos += 1) {
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$weightings" with its value "weightings".
			double cv$accumulatedProbabilities = Math.log(weightings[cv$valuePos]);
			
			// Processing random variable 37.
			for(int j = 0; j < size; j += 1) {
				// Processing sample task 41 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Bernoulli 37 which is consuming
				// the output of Sample task 12.
				// 
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((0 == j)) {
					if(fixedFlag$sample18) {
						// Substituted "j" with its value "0".
						// 
						// Substituted "cv$temp$1$var36" with its value "var36".
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "j" with its value "0".
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((cv$valuePos + v2[0]) + v2[0]) / v2[0]));
						
						// Recorded the probability of reaching sample task 41 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						// Enumerating the possible outputs of Categorical 14.
						for(int index$sample18$4 = 0; index$sample18$4 < weightings.length; index$sample18$4 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample18Value5 = distribution$sample18[index$sample18$4];
							
							// Variable declaration of cv$temp$2$var36 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$2$var36 = ((double)((cv$valuePos + index$sample18$4) + index$sample18$4) / index$sample18$4);
							
							// Record the probability of sample task 41 generating output with current configuration.
							// 
							// Substituted "j" with its value "0".
							if(((Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var36)) < cv$accumulatedConsumerProbabilities))
								// Substituted "j" with its value "0".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var36));
								else
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var36)))) + 1)) + Math.log(cv$probabilitySample18Value5)) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var36));
							}
							
							// Recorded the probability of reaching sample task 41 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value5);
						}
					}
				}
				
				// Enumerating the possible arguments for the variable Bernoulli 37 which is consuming
				// the output of Sample task 12.
				if(fixedFlag$sample26) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 <= j)) {
						// Variable declaration of cv$temp$43$var36 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double cv$temp$43$var36 = ((double)((cv$valuePos + v2[j]) + v2[j]) / v2[j]);
						
						// Record the probability of sample task 41 generating output with current configuration.
						if((DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var36) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var36) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var36);
							else
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var36))) + 1)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var36));
						}
						
						// Recorded the probability of reaching sample task 41 with the current configuration.
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
					}
				} else {
					int i = (j - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 <= i)) {
						// Enumerating the possible outputs of Categorical 22.
						for(int index$sample26$203 = 0; index$sample26$203 < weightings.length; index$sample26$203 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample26Value204 = distribution$sample26[i][index$sample26$203];
							
							// Variable declaration of cv$temp$44$var36 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$44$var36 = ((double)((cv$valuePos + index$sample26$203) + index$sample26$203) / index$sample26$203);
							
							// Record the probability of sample task 41 generating output with current configuration.
							if(((Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var36)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var36));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var36)))) + 1)) + Math.log(cv$probabilitySample26Value204)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var36));
							}
							
							// Recorded the probability of reaching sample task 41 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value204);
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
		// 
		// Get a local reference to the scratch space.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var9$stateProbabilityGlobal.length; cv$lseIndex += 1) {
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
			// 
			// Get a local reference to the scratch space.
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var9$stateProbabilityGlobal.length; cv$lseIndex += 1)
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
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var9$stateProbabilityGlobal.length; cv$indexName += 1)
				// Set the calculated probabilities to be the distribution values, and normalize
				// 
				// Local copy of the probability array
				distribution$sample12[cv$indexName] = (1.0 / cv$var9$stateProbabilityGlobal.length);
		} else {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var9$stateProbabilityGlobal.length; cv$indexName += 1)
				// Set the calculated probabilities to be the distribution values, and normalize
				// 
				// Local copy of the probability array
				distribution$sample12[cv$indexName] = Math.exp((cv$var9$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 18 drawn from Categorical 14. Inference was performed using variable
	// marginalization.
	private final void sample18() {
		for(int cv$valuePos = 0; cv$valuePos < weightings.length; cv$valuePos += 1) {
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
				guard$sample18bernoulli40$global[0] = false;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(!guard$sample18bernoulli40$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample18bernoulli40$global[0] = true;
					
					// Processing sample task 41 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Bernoulli 37 which is consuming
					// the output of Sample task 18.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 37 which is consuming
					// the output of Sample task 18.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 37 which is consuming
					// the output of Sample task 18.
					if(fixedFlag$sample12) {
						// Substituted "j" with its value "0".
						// 
						// cv$temp$1$var36's comment
						// Variable declaration of cv$temp$1$var36 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
						
						// Recorded the probability of reaching sample task 41 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						// Enumerating the possible outputs of Categorical 8.
						for(int index$sample12$11 = 0; index$sample12$11 < weightings.length; index$sample12$11 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample12Value12 = distribution$sample12[index$sample12$11];
							
							// Variable declaration of cv$temp$6$var36 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$6$var36 = ((double)((index$sample12$11 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 41 generating output with current configuration.
							// 
							// Substituted "j" with its value "0".
							if(((Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$6$var36)) < cv$accumulatedConsumerProbabilities))
								// Substituted "j" with its value "0".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$6$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$6$var36));
								else
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$6$var36)))) + 1)) + Math.log(cv$probabilitySample12Value12)) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$6$var36));
							}
							
							// Recorded the probability of reaching sample task 41 with the current configuration.
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
				if(!guard$sample18bernoulli40$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample18bernoulli40$global[0] = true;
					
					// Processing sample task 41 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Bernoulli 37 which is consuming
					// the output of Sample task 18.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 37 which is consuming
					// the output of Sample task 18.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 37 which is consuming
					// the output of Sample task 18.
					if(fixedFlag$sample12) {
						// Substituted "j" with its value "0".
						// 
						// cv$temp$33$var36's comment
						// Variable declaration of cv$temp$33$var36 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
						
						// Recorded the probability of reaching sample task 41 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						// Enumerating the possible outputs of Categorical 8.
						for(int index$sample12$155 = 0; index$sample12$155 < weightings.length; index$sample12$155 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample12Value156 = distribution$sample12[index$sample12$155];
							
							// Variable declaration of cv$temp$38$var36 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$38$var36 = ((double)((index$sample12$155 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 41 generating output with current configuration.
							// 
							// Substituted "j" with its value "0".
							if(((Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$38$var36)) < cv$accumulatedConsumerProbabilities))
								// Substituted "j" with its value "0".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$38$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$38$var36));
								else
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$38$var36)))) + 1)) + Math.log(cv$probabilitySample12Value156)) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$38$var36));
							}
							
							// Recorded the probability of reaching sample task 41 with the current configuration.
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
				if(!guard$sample18bernoulli40$global[0]) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample18bernoulli40$global[0] = true;
					
					// Processing sample task 41 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Bernoulli 37 which is consuming
					// the output of Sample task 18.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 37 which is consuming
					// the output of Sample task 18.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 37 which is consuming
					// the output of Sample task 18.
					if(fixedFlag$sample12) {
						// Substituted "j" with its value "0".
						// 
						// cv$temp$65$var36's comment
						// Variable declaration of cv$temp$65$var36 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
						
						// Recorded the probability of reaching sample task 41 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						// Enumerating the possible outputs of Categorical 8.
						for(int index$sample12$299 = 0; index$sample12$299 < weightings.length; index$sample12$299 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample12Value300 = distribution$sample12[index$sample12$299];
							
							// Variable declaration of cv$temp$70$var36 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$70$var36 = ((double)((index$sample12$299 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 41 generating output with current configuration.
							// 
							// Substituted "j" with its value "0".
							if(((Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$70$var36)) < cv$accumulatedConsumerProbabilities))
								// Substituted "j" with its value "0".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$70$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$70$var36));
								else
									// Substituted "j" with its value "0".
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$70$var36)))) + 1)) + Math.log(cv$probabilitySample12Value300)) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$70$var36));
							}
							
							// Recorded the probability of reaching sample task 41 with the current configuration.
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
		// 
		// Get a local reference to the scratch space.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var15$stateProbabilityGlobal.length; cv$lseIndex += 1) {
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
			// 
			// Get a local reference to the scratch space.
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var15$stateProbabilityGlobal.length; cv$lseIndex += 1)
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
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var15$stateProbabilityGlobal.length; cv$indexName += 1)
				// Set the calculated probabilities to be the distribution values, and normalize
				// 
				// Local copy of the probability array
				distribution$sample18[cv$indexName] = (1.0 / cv$var15$stateProbabilityGlobal.length);
		} else {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var15$stateProbabilityGlobal.length; cv$indexName += 1)
				// Set the calculated probabilities to be the distribution values, and normalize
				// 
				// Local copy of the probability array
				distribution$sample18[cv$indexName] = Math.exp((cv$var15$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 26 drawn from Categorical 22. Inference was performed using variable
	// marginalization.
	private final void sample26(int i) {
		for(int cv$valuePos = 0; cv$valuePos < weightings.length; cv$valuePos += 1) {
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
					guard$sample26bernoulli40$global[j] = false;
			}
			{
				int j = (i + 1);
				if((j < size))
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample26bernoulli40$global[j] = false;
			}
			{
				int j = (i + 1);
				if((j < size))
					// Set the flags to false
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample26bernoulli40$global[j] = false;
			}
			{
				int j = (i + 1);
				if(((j < size) && !guard$sample26bernoulli40$global[j])) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample26bernoulli40$global[j] = true;
					
					// Processing sample task 41 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Bernoulli 37 which is consuming
					// the output of Sample task 26.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 37 which is consuming
					// the output of Sample task 26.
					if(fixedFlag$sample12) {
						// cv$temp$23$var36's comment
						// Variable declaration of cv$temp$23$var36 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[j], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
						
						// Recorded the probability of reaching sample task 41 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
						int index$i$132 = (j - 1);
						
						// index$i$1's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						if(!(index$i$132 == i)) {
							// Enumerating the possible outputs of Categorical 22.
							for(int index$sample26$133 = 0; index$sample26$133 < weightings.length; index$sample26$133 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample26Value134 = distribution$sample26[index$i$132][index$sample26$133];
								
								// Variable declaration of cv$temp$24$var36 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$24$var36 = ((double)((v1 + cv$valuePos) + cv$valuePos) / index$sample26$133);
								
								// Record the probability of sample task 41 generating output with current configuration.
								if(((Math.log(cv$probabilitySample26Value134) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var36)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value134) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value134) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var36));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value134) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var36)))) + 1)) + Math.log(cv$probabilitySample26Value134)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var36));
								}
								
								// Recorded the probability of reaching sample task 41 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value134);
							}
						}
						int index$i$120 = (j - 1);
						
						// index$i$1's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						if(!(index$i$120 == i)) {
							// Enumerating the possible outputs of Categorical 22.
							for(int index$sample26$121 = 0; index$sample26$121 < weightings.length; index$sample26$121 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample26Value122 = distribution$sample26[index$i$120][index$sample26$121];
								
								// Variable declaration of cv$temp$25$var36 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$25$var36 = ((double)((v1 + cv$valuePos) + index$sample26$121) / cv$valuePos);
								
								// Record the probability of sample task 41 generating output with current configuration.
								if(((Math.log(cv$probabilitySample26Value122) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var36)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value122) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value122) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var36));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value122) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var36)))) + 1)) + Math.log(cv$probabilitySample26Value122)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var36));
								}
								
								// Recorded the probability of reaching sample task 41 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value122);
								
								// Variable declaration of cv$temp$26$var36 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$26$var36 = ((double)((v1 + cv$valuePos) + index$sample26$121) / index$sample26$121);
								
								// Record the probability of sample task 41 generating output with current configuration.
								if(((Math.log(cv$probabilitySample26Value122) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var36)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value122) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value122) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var36));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value122) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var36)))) + 1)) + Math.log(cv$probabilitySample26Value122)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var36));
								}
								
								// Recorded the probability of reaching sample task 41 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value122);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 8.
						for(int index$sample12$115 = 0; index$sample12$115 < weightings.length; index$sample12$115 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample12Value116 = distribution$sample12[index$sample12$115];
							
							// Variable declaration of cv$temp$28$var36 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$28$var36 = ((double)((index$sample12$115 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 41 generating output with current configuration.
							if(((Math.log(cv$probabilitySample12Value116) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var36)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value116) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value116) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var36));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value116) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var36)))) + 1)) + Math.log(cv$probabilitySample12Value116)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var36));
							}
							
							// Recorded the probability of reaching sample task 41 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value116);
							int index$i$145 = (j - 1);
							
							// index$i$1's comment
							// Copy of index so that its values can be safely substituted
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							if(!(index$i$145 == i)) {
								// Enumerating the possible outputs of Categorical 22.
								for(int index$sample26$146 = 0; index$sample26$146 < weightings.length; index$sample26$146 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample26Value147 = (cv$probabilitySample12Value116 * distribution$sample26[index$i$145][index$sample26$146]);
									
									// Variable declaration of cv$temp$29$var36 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double cv$temp$29$var36 = ((double)((index$sample12$115 + cv$valuePos) + cv$valuePos) / index$sample26$146);
									
									// Record the probability of sample task 41 generating output with current configuration.
									if(((Math.log(cv$probabilitySample26Value147) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var36)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value147) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value147) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var36));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value147) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var36)))) + 1)) + Math.log(cv$probabilitySample26Value147)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var36));
									}
									
									// Recorded the probability of reaching sample task 41 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value147);
								}
							}
							int index$i$126 = (j - 1);
							
							// index$i$1's comment
							// Copy of index so that its values can be safely substituted
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							if(!(index$i$126 == i)) {
								// Enumerating the possible outputs of Categorical 22.
								for(int index$sample26$127 = 0; index$sample26$127 < weightings.length; index$sample26$127 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample26Value128 = (cv$probabilitySample12Value116 * distribution$sample26[index$i$126][index$sample26$127]);
									
									// Variable declaration of cv$temp$30$var36 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double cv$temp$30$var36 = ((double)((index$sample12$115 + cv$valuePos) + index$sample26$127) / cv$valuePos);
									
									// Record the probability of sample task 41 generating output with current configuration.
									if(((Math.log(cv$probabilitySample26Value128) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var36)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value128) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value128) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var36));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value128) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var36)))) + 1)) + Math.log(cv$probabilitySample26Value128)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var36));
									}
									
									// Recorded the probability of reaching sample task 41 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value128);
									
									// Variable declaration of cv$temp$31$var36 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double cv$temp$31$var36 = ((double)((index$sample12$115 + cv$valuePos) + index$sample26$127) / index$sample26$127);
									
									// Record the probability of sample task 41 generating output with current configuration.
									if(((Math.log(cv$probabilitySample26Value128) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var36)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value128) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value128) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var36));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value128) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var36)))) + 1)) + Math.log(cv$probabilitySample26Value128)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var36));
									}
									
									// Recorded the probability of reaching sample task 41 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value128);
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
				if(((j < size) && !guard$sample26bernoulli40$global[j])) {
					// The body will execute, so should not be executed again
					// 
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					guard$sample26bernoulli40$global[j] = true;
					
					// Processing sample task 41 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Bernoulli 37 which is consuming
					// the output of Sample task 26.
					// 
					// Enumerating the possible arguments for the variable Bernoulli 37 which is consuming
					// the output of Sample task 26.
					if(fixedFlag$sample12) {
						// cv$temp$55$var36's comment
						// Variable declaration of cv$temp$55$var36 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[j], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
						
						// Recorded the probability of reaching sample task 41 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
						int index$i$278 = (j - 1);
						
						// index$i$1's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						if(!(index$i$278 == i)) {
							// Enumerating the possible outputs of Categorical 22.
							for(int index$sample26$279 = 0; index$sample26$279 < weightings.length; index$sample26$279 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample26Value280 = distribution$sample26[index$i$278][index$sample26$279];
								
								// Variable declaration of cv$temp$56$var36 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$56$var36 = ((double)((v1 + cv$valuePos) + cv$valuePos) / index$sample26$279);
								
								// Record the probability of sample task 41 generating output with current configuration.
								if(((Math.log(cv$probabilitySample26Value280) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var36)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value280) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value280) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var36));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value280) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var36)))) + 1)) + Math.log(cv$probabilitySample26Value280)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var36));
								}
								
								// Recorded the probability of reaching sample task 41 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value280);
							}
						}
						int index$i$266 = (j - 1);
						
						// index$i$1's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						if(!(index$i$266 == i)) {
							// Enumerating the possible outputs of Categorical 22.
							for(int index$sample26$267 = 0; index$sample26$267 < weightings.length; index$sample26$267 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample26Value268 = distribution$sample26[index$i$266][index$sample26$267];
								
								// Variable declaration of cv$temp$57$var36 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$57$var36 = ((double)((v1 + index$sample26$267) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 41 generating output with current configuration.
								if(((Math.log(cv$probabilitySample26Value268) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var36)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value268) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value268) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var36));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value268) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var36)))) + 1)) + Math.log(cv$probabilitySample26Value268)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var36));
								}
								
								// Recorded the probability of reaching sample task 41 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value268);
								
								// Variable declaration of cv$temp$58$var36 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$58$var36 = ((double)((v1 + index$sample26$267) + cv$valuePos) / index$sample26$267);
								
								// Record the probability of sample task 41 generating output with current configuration.
								if(((Math.log(cv$probabilitySample26Value268) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var36)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value268) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value268) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var36));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value268) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var36)))) + 1)) + Math.log(cv$probabilitySample26Value268)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var36));
								}
								
								// Recorded the probability of reaching sample task 41 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value268);
							}
						}
					} else {
						// Enumerating the possible outputs of Categorical 8.
						for(int index$sample12$261 = 0; index$sample12$261 < weightings.length; index$sample12$261 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample12Value262 = distribution$sample12[index$sample12$261];
							
							// Variable declaration of cv$temp$60$var36 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$60$var36 = ((double)((index$sample12$261 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 41 generating output with current configuration.
							if(((Math.log(cv$probabilitySample12Value262) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var36)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value262) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value262) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var36));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value262) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var36)))) + 1)) + Math.log(cv$probabilitySample12Value262)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var36));
							}
							
							// Recorded the probability of reaching sample task 41 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value262);
							int index$i$291 = (j - 1);
							
							// index$i$1's comment
							// Copy of index so that its values can be safely substituted
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							if(!(index$i$291 == i)) {
								// Enumerating the possible outputs of Categorical 22.
								for(int index$sample26$292 = 0; index$sample26$292 < weightings.length; index$sample26$292 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample26Value293 = (cv$probabilitySample12Value262 * distribution$sample26[index$i$291][index$sample26$292]);
									
									// Variable declaration of cv$temp$61$var36 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double cv$temp$61$var36 = ((double)((index$sample12$261 + cv$valuePos) + cv$valuePos) / index$sample26$292);
									
									// Record the probability of sample task 41 generating output with current configuration.
									if(((Math.log(cv$probabilitySample26Value293) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var36)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value293) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value293) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var36));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value293) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var36)))) + 1)) + Math.log(cv$probabilitySample26Value293)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var36));
									}
									
									// Recorded the probability of reaching sample task 41 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value293);
								}
							}
							int index$i$272 = (j - 1);
							
							// index$i$1's comment
							// Copy of index so that its values can be safely substituted
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							// 
							// Substituted "j" with its value "(i + 1)".
							if(!(index$i$272 == i)) {
								// Enumerating the possible outputs of Categorical 22.
								for(int index$sample26$273 = 0; index$sample26$273 < weightings.length; index$sample26$273 += 1) {
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample26Value274 = (cv$probabilitySample12Value262 * distribution$sample26[index$i$272][index$sample26$273]);
									
									// Variable declaration of cv$temp$62$var36 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double cv$temp$62$var36 = ((double)((index$sample12$261 + index$sample26$273) + cv$valuePos) / cv$valuePos);
									
									// Record the probability of sample task 41 generating output with current configuration.
									if(((Math.log(cv$probabilitySample26Value274) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var36)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value274) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value274) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var36));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value274) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var36)))) + 1)) + Math.log(cv$probabilitySample26Value274)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var36));
									}
									
									// Recorded the probability of reaching sample task 41 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value274);
									
									// Variable declaration of cv$temp$63$var36 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double cv$temp$63$var36 = ((double)((index$sample12$261 + index$sample26$273) + cv$valuePos) / index$sample26$273);
									
									// Record the probability of sample task 41 generating output with current configuration.
									if(((Math.log(cv$probabilitySample26Value274) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var36)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value274) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										// If the second value is -infinity.
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value274) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var36));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value274) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var36)))) + 1)) + Math.log(cv$probabilitySample26Value274)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var36));
									}
									
									// Recorded the probability of reaching sample task 41 with the current configuration.
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value274);
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
			if(((j < size) && !guard$sample26bernoulli40$global[j])) {
				// The body will execute, so should not be executed again
				// 
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				guard$sample26bernoulli40$global[j] = true;
				
				// Processing sample task 41 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Enumerating the possible arguments for the variable Bernoulli 37 which is consuming
				// the output of Sample task 26.
				// 
				// Enumerating the possible arguments for the variable Bernoulli 37 which is consuming
				// the output of Sample task 26.
				if(fixedFlag$sample12) {
					// cv$temp$87$var36's comment
					// Variable declaration of cv$temp$87$var36 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[j], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
					
					// Recorded the probability of reaching sample task 41 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					cv$consumerDistributionProbabilityAccumulator = 0.0;
					int index$i$424 = (j - 1);
					
					// index$i$1's comment
					// Copy of index so that its values can be safely substituted
					// 
					// Substituted "j" with its value "(i + 1)".
					// 
					// Substituted "j" with its value "(i + 1)".
					// 
					// Substituted "j" with its value "(i + 1)".
					// 
					// Substituted "j" with its value "(i + 1)".
					if(!(index$i$424 == i)) {
						// Enumerating the possible outputs of Categorical 22.
						for(int index$sample26$425 = 0; index$sample26$425 < weightings.length; index$sample26$425 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample26Value426 = distribution$sample26[index$i$424][index$sample26$425];
							
							// Variable declaration of cv$temp$88$var36 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$88$var36 = ((double)((v1 + cv$valuePos) + index$sample26$425) / cv$valuePos);
							
							// Record the probability of sample task 41 generating output with current configuration.
							if(((Math.log(cv$probabilitySample26Value426) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var36)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value426) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value426) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var36));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value426) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var36)))) + 1)) + Math.log(cv$probabilitySample26Value426)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var36));
							}
							
							// Recorded the probability of reaching sample task 41 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value426);
						}
					}
					int index$i$412 = (j - 1);
					
					// index$i$1's comment
					// Copy of index so that its values can be safely substituted
					// 
					// Substituted "j" with its value "(i + 1)".
					// 
					// Substituted "j" with its value "(i + 1)".
					// 
					// Substituted "j" with its value "(i + 1)".
					// 
					// Substituted "j" with its value "(i + 1)".
					if(!(index$i$412 == i)) {
						// Enumerating the possible outputs of Categorical 22.
						for(int index$sample26$413 = 0; index$sample26$413 < weightings.length; index$sample26$413 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample26Value414 = distribution$sample26[index$i$412][index$sample26$413];
							
							// Variable declaration of cv$temp$89$var36 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$89$var36 = ((double)((v1 + index$sample26$413) + cv$valuePos) / cv$valuePos);
							
							// Record the probability of sample task 41 generating output with current configuration.
							if(((Math.log(cv$probabilitySample26Value414) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var36)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value414) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value414) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var36));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value414) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var36)))) + 1)) + Math.log(cv$probabilitySample26Value414)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var36));
							}
							
							// Recorded the probability of reaching sample task 41 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value414);
							
							// Variable declaration of cv$temp$90$var36 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$90$var36 = ((double)((v1 + index$sample26$413) + index$sample26$413) / cv$valuePos);
							
							// Record the probability of sample task 41 generating output with current configuration.
							if(((Math.log(cv$probabilitySample26Value414) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var36)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value414) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value414) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var36));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value414) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var36)))) + 1)) + Math.log(cv$probabilitySample26Value414)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var36));
							}
							
							// Recorded the probability of reaching sample task 41 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value414);
						}
					}
				} else {
					// Enumerating the possible outputs of Categorical 8.
					for(int index$sample12$407 = 0; index$sample12$407 < weightings.length; index$sample12$407 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample12Value408 = distribution$sample12[index$sample12$407];
						
						// Variable declaration of cv$temp$92$var36 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double cv$temp$92$var36 = ((double)((index$sample12$407 + cv$valuePos) + cv$valuePos) / cv$valuePos);
						
						// Record the probability of sample task 41 generating output with current configuration.
						if(((Math.log(cv$probabilitySample12Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var36)) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var36));
							else
								cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var36)))) + 1)) + Math.log(cv$probabilitySample12Value408)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var36));
						}
						
						// Recorded the probability of reaching sample task 41 with the current configuration.
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value408);
						int index$i$437 = (j - 1);
						
						// index$i$1's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						if(!(index$i$437 == i)) {
							// Enumerating the possible outputs of Categorical 22.
							for(int index$sample26$438 = 0; index$sample26$438 < weightings.length; index$sample26$438 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample26Value439 = (cv$probabilitySample12Value408 * distribution$sample26[index$i$437][index$sample26$438]);
								
								// Variable declaration of cv$temp$93$var36 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$93$var36 = ((double)((index$sample12$407 + cv$valuePos) + index$sample26$438) / cv$valuePos);
								
								// Record the probability of sample task 41 generating output with current configuration.
								if(((Math.log(cv$probabilitySample26Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var36)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var36));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var36)))) + 1)) + Math.log(cv$probabilitySample26Value439)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var36));
								}
								
								// Recorded the probability of reaching sample task 41 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value439);
							}
						}
						int index$i$418 = (j - 1);
						
						// index$i$1's comment
						// Copy of index so that its values can be safely substituted
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						// 
						// Substituted "j" with its value "(i + 1)".
						if(!(index$i$418 == i)) {
							// Enumerating the possible outputs of Categorical 22.
							for(int index$sample26$419 = 0; index$sample26$419 < weightings.length; index$sample26$419 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample26Value420 = (cv$probabilitySample12Value408 * distribution$sample26[index$i$418][index$sample26$419]);
								
								// Variable declaration of cv$temp$94$var36 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$94$var36 = ((double)((index$sample12$407 + index$sample26$419) + cv$valuePos) / cv$valuePos);
								
								// Record the probability of sample task 41 generating output with current configuration.
								if(((Math.log(cv$probabilitySample26Value420) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var36)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value420) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value420) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var36));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value420) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var36)))) + 1)) + Math.log(cv$probabilitySample26Value420)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var36));
								}
								
								// Recorded the probability of reaching sample task 41 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value420);
								
								// Variable declaration of cv$temp$95$var36 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$95$var36 = ((double)((index$sample12$407 + index$sample26$419) + index$sample26$419) / cv$valuePos);
								
								// Record the probability of sample task 41 generating output with current configuration.
								if(((Math.log(cv$probabilitySample26Value420) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var36)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value420) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									// If the second value is -infinity.
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value420) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var36));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value420) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var36)))) + 1)) + Math.log(cv$probabilitySample26Value420)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var36));
								}
								
								// Recorded the probability of reaching sample task 41 with the current configuration.
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value420);
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
		// 
		// Get a local reference to the scratch space.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var23$stateProbabilityGlobal.length; cv$lseIndex += 1) {
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
			// 
			// Get a local reference to the scratch space.
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var23$stateProbabilityGlobal.length; cv$lseIndex += 1)
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
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var23$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$localProbability[cv$indexName] = (1.0 / cv$var23$stateProbabilityGlobal.length);
		} else {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var23$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$localProbability[cv$indexName] = Math.exp((cv$var23$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
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
		
		// Constructor for guard$sample18bernoulli40$global
		// 
		// Allocation of guard$sample18bernoulli40$global for single threaded execution
		guard$sample18bernoulli40$global = new boolean[length$value];
		
		// Constructor for cv$var23$stateProbabilityGlobal
		// 
		// Allocation of cv$var23$stateProbabilityGlobal for single threaded execution
		cv$var23$stateProbabilityGlobal = new double[weightings.length];
		
		// Allocation of guard$sample26bernoulli40$global for single threaded execution
		guard$sample26bernoulli40$global = new boolean[length$value];
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
		
		// Constructor for logProbability$var37
		logProbability$var37 = new double[length$value];
		
		// Constructor for logProbability$sample41
		logProbability$sample41 = new double[length$value];
		
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
		if(!fixedFlag$sample26) {
			for(int i = 0; i < size; i += 1)
				v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$, weightings);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample41) {
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
		if(!fixedFlag$sample26) {
			for(int i = 0; i < size; i += 1) {
				// Create local copy of variable probabilities.
				double[] cv$distribution$sample26 = distribution$sample26[i];
				for(int index$var22 = 0; index$var22 < weightings.length; index$var22 += 1)
					// Save the probability of each value
					// 
					// Probability for this value
					cv$distribution$sample26[index$var22] = weightings[index$var22];
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
		if(!fixedFlag$sample26) {
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
			logProbability$var37[j] = 0.0;
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
		logProbabilityDistribution$sample12();
		logProbabilityDistribution$sample18();
		logProbabilityDistribution$sample26();
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
		logProbabilityValue$sample12();
		logProbabilityValue$sample18();
		logProbabilityValue$sample26();
		logProbabilityValue$sample41();
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
		if(!fixedFlag$sample26) {
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