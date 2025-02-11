package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray2$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements RaggedArray2$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] a;
	private double[][] b;
	private double[] c;
	private double[] cv$var63$stateProbabilityGlobal;
	private double[] cv$var66$stateProbabilityGlobal;
	private double[] distribution$sample68;
	private boolean fixedFlag$sample68 = false;
	private boolean fixedFlag$sample71 = false;
	private boolean fixedFlag$sample81 = false;
	private boolean fixedProbFlag$sample68 = false;
	private boolean fixedProbFlag$sample71 = false;
	private boolean fixedProbFlag$sample81 = false;
	private int i;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$i;
	private double logProbability$obs;
	private double logProbability$p;
	private double logProbability$var62;
	private double logProbability$var65;
	private double logProbability$var69;
	private double logProbability$var75;
	private double logProbability$y;
	private boolean[] obs;
	private boolean[] obs_measured;
	private double p;
	private boolean setFlag$obs = false;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray2$SingleThreadCPU(ExecutionTarget target) {
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

	// Getter for c.
	@Override
	public final double[] get$c() {
		return c;
	}

	// Getter for fixedFlag$sample68.
	@Override
	public final boolean get$fixedFlag$sample68() {
		return fixedFlag$sample68;
	}

	// Setter for fixedFlag$sample68.
	@Override
	public final void set$fixedFlag$sample68(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample68 including if probabilities
		// need to be updated.
		fixedFlag$sample68 = cv$value;
		
		// Should the probability of sample 68 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample68" with its value "cv$value".
		fixedProbFlag$sample68 = (cv$value && fixedProbFlag$sample68);
		
		// Should the probability of sample 71 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample68" with its value "cv$value".
		fixedProbFlag$sample71 = (cv$value && fixedProbFlag$sample71);
		
		// Should the probability of sample 81 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample68" with its value "cv$value".
		fixedProbFlag$sample81 = (cv$value && fixedProbFlag$sample81);
	}

	// Getter for fixedFlag$sample71.
	@Override
	public final boolean get$fixedFlag$sample71() {
		return fixedFlag$sample71;
	}

	// Setter for fixedFlag$sample71.
	@Override
	public final void set$fixedFlag$sample71(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample71 including if probabilities
		// need to be updated.
		fixedFlag$sample71 = cv$value;
		
		// Should the probability of sample 71 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample71" with its value "cv$value".
		fixedProbFlag$sample71 = (cv$value && fixedProbFlag$sample71);
		
		// Should the probability of sample 81 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample71" with its value "cv$value".
		fixedProbFlag$sample81 = (cv$value && fixedProbFlag$sample81);
	}

	// Getter for fixedFlag$sample81.
	@Override
	public final boolean get$fixedFlag$sample81() {
		return fixedFlag$sample81;
	}

	// Setter for fixedFlag$sample81.
	@Override
	public final void set$fixedFlag$sample81(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample81 including if probabilities
		// need to be updated.
		fixedFlag$sample81 = cv$value;
		
		// Should the probability of sample 81 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample81" with its value "cv$value".
		fixedProbFlag$sample81 = (cv$value && fixedProbFlag$sample81);
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
		
		// Unset the fixed probability flag for sample 71 as it depends on i.
		fixedProbFlag$sample71 = false;
		
		// Unset the fixed probability flag for sample 81 as it depends on i.
		fixedProbFlag$sample81 = false;
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

	// Getter for logProbability$p.
	@Override
	public final double get$logProbability$p() {
		return logProbability$p;
	}

	// Getter for logProbability$y.
	@Override
	public final double get$logProbability$y() {
		return logProbability$y;
	}

	// Getter for obs.
	@Override
	public final boolean[] get$obs() {
		return obs;
	}

	// Setter for obs.
	@Override
	public final void set$obs(boolean[] cv$value) {
		// Set flags for all the side effects of obs including if probabilities need to be
		// updated.
		// Set obs with flag to mark that it has been set so another array doesn't need to
		// be constructed
		obs = cv$value;
		setFlag$obs = true;
		
		// Unset the fixed probability flag for sample 81 as it depends on obs.
		fixedProbFlag$sample81 = false;
	}

	// Getter for obs_measured.
	@Override
	public final boolean[] get$obs_measured() {
		return obs_measured;
	}

	// Setter for obs_measured.
	@Override
	public final void set$obs_measured(boolean[] cv$value) {
		// Set obs_measured with flag to mark that it has been set so another array doesn't
		// need to be constructed
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
		// Set flags for all the side effects of y including if probabilities need to be updated.
		y = cv$value;
		
		// Unset the fixed probability flag for sample 68 as it depends on y.
		fixedProbFlag$sample68 = false;
		
		// Unset the fixed probability flag for sample 71 as it depends on y.
		fixedProbFlag$sample71 = false;
		
		// Unset the fixed probability flag for sample 81 as it depends on y.
		fixedProbFlag$sample81 = false;
	}

	// Calculate the probability of the samples represented by sample68 using probability
	// distributions.
	private final void logProbabilityDistribution$sample68() {
		// Determine if we need to calculate the values for sample task 68 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample68) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample68) {
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
				double cv$distributionAccumulator = (((0.0 <= y) && (y < c.length))?Math.log(c[y]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$var62 = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$y = cv$distributionAccumulator;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(fixedFlag$sample71)
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
					logProbability$p = (logProbability$p + cv$distributionAccumulator);
				
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
				// Substituted "fixedFlag$sample68" with its value "true".
				fixedProbFlag$sample68 = true;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var62 = logProbability$y;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample68 && fixedFlag$sample71))
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$p = (logProbability$p + logProbability$y);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$y);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample68)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$y);
		}
	}

	// Calculate the probability of the samples represented by sample71 using probability
	// distributions.
	private final void logProbabilityDistribution$sample71() {
		// Determine if we need to calculate the values for sample task 71 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample71) {
			// Generating probabilities for sample task
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			
			// Enumerating the possible arguments for Categorical 65.
			// 
			// Enumerating the possible arguments for Categorical 65.
			if(fixedFlag$sample68) {
				if((0 == y)) {
					// Substituted "y" with its value "0".
					double[] var64 = a[0];
					
					// Store the value of the function call, so the function call is only made once.
					// 
					// The sample value to calculate the probability of generating
					cv$distributionAccumulator = (((0.0 <= i) && (i < var64.length))?Math.log(var64[i]):Double.NEGATIVE_INFINITY);
					
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					cv$probabilityReached = 1.0;
				}
				if((1 == y)) {
					// Substituted "y" with its value "1".
					double[] var64 = a[1];
					
					// Store the value of the function call, so the function call is only made once.
					// 
					// The sample value to calculate the probability of generating
					double cv$weightedProbability = (((0.0 <= i) && (i < var64.length))?Math.log(var64[i]):Double.NEGATIVE_INFINITY);
					
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
				{
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample68$3" with its value "0".
					double cv$probabilitySample68Value4 = distribution$sample68[0];
					double[] var64 = a[0];
					
					// Store the value of the function call, so the function call is only made once.
					// 
					// The sample value to calculate the probability of generating
					cv$distributionAccumulator = (Math.log(cv$probabilitySample68Value4) + (((0.0 <= i) && (i < var64.length))?Math.log(var64[i]):Double.NEGATIVE_INFINITY));
					
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					cv$probabilityReached = cv$probabilitySample68Value4;
				}
				
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "index$sample68$12" with its value "1".
				double cv$probabilitySample68Value13 = distribution$sample68[1];
				double[] var64 = a[1];
				
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				double cv$weightedProbability = (Math.log(cv$probabilitySample68Value13) + (((0.0 <= i) && (i < var64.length))?Math.log(var64[i]):Double.NEGATIVE_INFINITY));
				
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
				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample68Value13);
			}
			if((cv$probabilityReached == 0.0))
				// Return negative infinity if no distribution probability space is reached.
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				// Scale the probability relative to the observed distribution space.
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var65 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$i = cv$distributionAccumulator;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample68 && fixedFlag$sample71))
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
				logProbability$p = (logProbability$p + cv$distributionAccumulator);
			
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
			if(fixedFlag$sample71)
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
			fixedProbFlag$sample71 = (fixedFlag$sample71 && fixedFlag$sample68);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var65 = logProbability$i;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample68 && fixedFlag$sample71))
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$p = (logProbability$p + logProbability$i);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$i);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample71)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$i);
		}
	}

	// Calculate the probability of the samples represented by sample81 using probability
	// distributions.
	private final void logProbabilityDistribution$sample81() {
		// Determine if we need to calculate the values for sample task 81 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample81) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var74 = 0; var74 < length$obs_measured; var74 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 81 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				boolean cv$sampleValue = obs[var74];
				
				// Enumerating the possible arguments for Bernoulli 69.
				// 
				// Enumerating the possible arguments for Bernoulli 69.
				// 
				// Enumerating the possible arguments for Bernoulli 69.
				// 
				// Enumerating the possible arguments for Bernoulli 69.
				// 
				// Enumerating the possible arguments for Bernoulli 69.
				if(fixedFlag$sample68) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == y)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == i)) {
							// Store the value of the function call, so the function call is only made once.
							cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.2);
							
							// Add the probability of this distribution configuration to the accumulator.
							// 
							// An accumulator for the distributed probability space covered.
							cv$probabilityReached = 1.0;
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == i)) {
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.8);
							
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
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == y)) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 == i)) {
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.4);
							
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
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 == i)) {
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.2);
							
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
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((2 == i)) {
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.6);
							
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
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == i)) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample68$4" with its value "0".
						double cv$probabilitySample68Value5 = distribution$sample68[0];
						
						// Store the value of the function call, so the function call is only made once.
						cv$distributionAccumulator = (Math.log(cv$probabilitySample68Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.2));
						
						// Add the probability of this distribution configuration to the accumulator.
						// 
						// An accumulator for the distributed probability space covered.
						cv$probabilityReached = cv$probabilitySample68Value5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == i)) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample68$12" with its value "0".
						double cv$probabilitySample68Value13 = distribution$sample68[0];
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(cv$probabilitySample68Value13) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.8));
						
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
						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample68Value13);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == i)) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample68$20" with its value "1".
						double cv$probabilitySample68Value21 = distribution$sample68[1];
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(cv$probabilitySample68Value21) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.4));
						
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
						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample68Value21);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == i)) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample68$28" with its value "1".
						double cv$probabilitySample68Value29 = distribution$sample68[1];
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(cv$probabilitySample68Value29) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.2));
						
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
						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample68Value29);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((2 == i)) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample68$36" with its value "1".
						double cv$probabilitySample68Value37 = distribution$sample68[1];
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(cv$probabilitySample68Value37) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.6));
						
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
						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample68Value37);
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
			logProbability$var69 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var75 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$obs = (logProbability$obs + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample81 = ((fixedFlag$sample81 && fixedFlag$sample68) && fixedFlag$sample71);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var69 = logProbability$var75;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$obs = (logProbability$obs + logProbability$var75);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var75);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var75);
		}
	}

	// Calculate the probability of the samples represented by sample68 using sampled
	// values.
	private final void logProbabilityValue$sample68() {
		// Determine if we need to calculate the values for sample task 68 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample68) {
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
			double cv$distributionAccumulator = (((0.0 <= y) && (y < c.length))?Math.log(c[y]):Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var62 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$y = cv$distributionAccumulator;
			
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
			logProbability$p = (logProbability$p + cv$distributionAccumulator);
			
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
			if(fixedFlag$sample68)
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
			fixedProbFlag$sample68 = fixedFlag$sample68;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var62 = logProbability$y;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$p = (logProbability$p + logProbability$y);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$y);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample68)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$y);
		}
	}

	// Calculate the probability of the samples represented by sample71 using sampled
	// values.
	private final void logProbabilityValue$sample71() {
		// Determine if we need to calculate the values for sample task 71 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample71) {
			// Generating probabilities for sample task
			double[] var64 = a[y];
			
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
			double cv$distributionAccumulator = (((0.0 <= i) && (i < var64.length))?Math.log(var64[i]):Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var65 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$i = cv$distributionAccumulator;
			
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
			logProbability$p = (logProbability$p + cv$distributionAccumulator);
			
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
			if(fixedFlag$sample71)
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
			fixedProbFlag$sample71 = (fixedFlag$sample71 && fixedFlag$sample68);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var65 = logProbability$i;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$p = (logProbability$p + logProbability$i);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$i);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample71)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$i);
		}
	}

	// Calculate the probability of the samples represented by sample81 using sampled
	// values.
	private final void logProbabilityValue$sample81() {
		// Determine if we need to calculate the values for sample task 81 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample81) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var74 = 0; var74 < length$obs_measured; var74 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(obs[var74], p));
			logProbability$var69 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var75 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$obs = (logProbability$obs + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample81 = ((fixedFlag$sample81 && fixedFlag$sample68) && fixedFlag$sample71);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var69 = logProbability$var75;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$obs = (logProbability$obs + logProbability$var75);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var75);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var75);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 68 drawn from Categorical 62. Inference was performed using variable
	// marginalization.
	private final void sample68() {
		{
			// Processing random variable 65.
			// 
			// Variable declaration of cv$temp$1$var64 moved.
			// 
			// Constructing a random variable input for use later.
			double[] cv$temp$1$var64 = a[0];
			
			// Variable declaration of cv$accumulatedProbabilities moved.
			// Declaration comment was:
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$c" with its value "c".
			// 
			// Substituted "cv$valuePos" with its value "0".
			// 
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 71 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$c" with its value "c".
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = ((((0.0 <= i) && (i < cv$temp$1$var64.length))?Math.log(cv$temp$1$var64[i]):Double.NEGATIVE_INFINITY) + ((0 < c.length)?Math.log(c[0]):Double.NEGATIVE_INFINITY));
			
			// Processing sample task 81 of consumer random variable null.
			for(int var74 = 0; var74 < length$obs_measured; var74 += 1) {
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Processing random variable 69.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				if((0 == i)) {
					// Substituted "cv$temp$3$p" with its value "0.2".
					cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var74], 0.2);
					
					// Recorded the probability of reaching sample task 81 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					cv$consumerDistributionProbabilityAccumulator = 0.0;
				}
				
				// Processing random variable 69.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				if((1 == i)) {
					// Record the probability of sample task 81 generating output with current configuration.
					// 
					// cv$temp$4$p's comment
					// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
					// the output of Sample task 68.
					if((DistributionSampling.logProbabilityBernoulli(obs[var74], 0.8) < cv$accumulatedConsumerProbabilities))
						// cv$temp$4$p's comment
						// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
						// the output of Sample task 68.
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var74], 0.8) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							// cv$temp$4$p's comment
							// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
							// the output of Sample task 68.
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var74], 0.8);
						else
							// cv$temp$4$p's comment
							// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
							// the output of Sample task 68.
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var74], 0.8))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var74], 0.8));
					}
					
					// Recorded the probability of reaching sample task 81 with the current configuration.
					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
			cv$var63$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Processing random variable 65.
		// 
		// Variable declaration of cv$temp$2$var64 moved.
		// 
		// Constructing a random variable input for use later.
		double[] cv$temp$2$var64 = a[1];
		
		// Variable declaration of cv$accumulatedProbabilities moved.
		// Declaration comment was:
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$c" with its value "c".
		// 
		// Substituted "cv$valuePos" with its value "1".
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 71 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$c" with its value "c".
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = ((((0.0 <= i) && (i < cv$temp$2$var64.length))?Math.log(cv$temp$2$var64[i]):Double.NEGATIVE_INFINITY) + ((1 < c.length)?Math.log(c[1]):Double.NEGATIVE_INFINITY));
		
		// Processing sample task 81 of consumer random variable null.
		for(int var74 = 0; var74 < length$obs_measured; var74 += 1) {
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
			
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			double cv$consumerDistributionProbabilityAccumulator = 1.0;
			if((0 == i)) {
				// cv$temp$5$p's comment
				// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
				// the output of Sample task 68.
				cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var74], 0.4);
				
				// Recorded the probability of reaching sample task 81 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				cv$consumerDistributionProbabilityAccumulator = 0.0;
			}
			if((1 == i)) {
				// Record the probability of sample task 81 generating output with current configuration.
				// 
				// cv$temp$6$p's comment
				// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
				// the output of Sample task 68.
				if((DistributionSampling.logProbabilityBernoulli(obs[var74], 0.2) < cv$accumulatedConsumerProbabilities))
					// cv$temp$6$p's comment
					// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
					// the output of Sample task 68.
					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var74], 0.2) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
				else {
					// If the second value is -infinity.
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						// cv$temp$6$p's comment
						// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
						// the output of Sample task 68.
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var74], 0.2);
					else
						// cv$temp$6$p's comment
						// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
						// the output of Sample task 68.
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var74], 0.2))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var74], 0.2));
				}
				
				// Recorded the probability of reaching sample task 81 with the current configuration.
				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
			}
			if((2 == i)) {
				// Record the probability of sample task 81 generating output with current configuration.
				// 
				// cv$temp$7$p's comment
				// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
				// the output of Sample task 68.
				if((DistributionSampling.logProbabilityBernoulli(obs[var74], 0.6) < cv$accumulatedConsumerProbabilities))
					// cv$temp$7$p's comment
					// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
					// the output of Sample task 68.
					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var74], 0.6) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
				else {
					// If the second value is -infinity.
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						// cv$temp$7$p's comment
						// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
						// the output of Sample task 68.
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var74], 0.6);
					else
						// cv$temp$7$p's comment
						// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
						// the output of Sample task 68.
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var74], 0.6))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var74], 0.6));
				}
				
				// Recorded the probability of reaching sample task 81 with the current configuration.
				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
		cv$var63$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var63$stateProbabilityGlobal[0];
		
		// Unrolled loop
		// 
		// Get a local reference to the scratch space.
		double cv$lseElementValue = cv$var63$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		
		// If the maximum value is -infinity return -infinity.
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		
		// Sum the values in the array.
		else
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			// 
			// Get a local reference to the scratch space.
			// 
			// Get a local reference to the scratch space.
			// 
			// Initialise the sum of the array elements
			cv$logSum = (Math.log((Math.exp((cv$var63$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var63$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Unrolled loop
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample68[0] = 0.5;
			
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample68[1] = 0.5;
		} else {
			// Unrolled loop
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample68[0] = Math.exp((cv$var63$stateProbabilityGlobal[0] - cv$logSum));
			
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample68[1] = Math.exp((cv$var63$stateProbabilityGlobal[1] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = 2; cv$indexName < cv$var63$stateProbabilityGlobal.length; cv$indexName += 1)
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample68[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 71 drawn from Categorical 65. Inference was performed using variable
	// marginalization.
	private final void sample71() {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		
		// Exploring all the possible state counts for random variable 65.
		// 
		// Enumerating the possible arguments for Categorical 65.
		// 
		// Enumerating the possible arguments for Categorical 65.
		if(fixedFlag$sample68) {
			if((0 == y))
				// variable marginalization
				// 
				// Substituted "y" with its value "0".
				cv$noStates = a[0].length;
			if((1 == y))
				// variable marginalization
				// 
				// Substituted "y" with its value "1".
				cv$noStates = Math.max(cv$noStates, a[1].length);
		} else
			// variable marginalization
			// 
			// cv$noStates's comment
			// variable marginalization
			// 
			// cv$noStates's comment
			// Calculate the number of states to evaluate.
			cv$noStates = Math.max(a[0].length, a[1].length);
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 65 creating
			// sample task 71.
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			i = cv$valuePos;
			
			// Guards to ensure that p is only updated when there is a valid path.
			// 
			// Write out the new sample value.
			// 
			// Value of the variable at this index
			p = b[y][cv$valuePos];
			
			// Enumerating the possible arguments for Categorical 65.
			// 
			// Enumerating the possible arguments for Categorical 65.
			if(fixedFlag$sample68) {
				if((0 == y)) {
					// Record the reached probability density.
					// 
					// Initialize a counter to track the reached distributions.
					cv$reachedDistributionSourceRV = 1.0;
					
					// Variable declaration of cv$temp$0$var64 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "y" with its value "0".
					double[] cv$temp$0$var64 = a[0];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var64.length)?Math.log(cv$temp$0$var64[cv$valuePos]):Double.NEGATIVE_INFINITY);
					
					// Processing sample task 81 of consumer random variable null.
					for(int var74 = 0; var74 < length$obs_measured; var74 += 1) {
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Substituted "y" with its value "0".
						// 
						// Processing random variable 69.
						// 
						// Value of the variable at this index
						if((0 == cv$valuePos)) {
							// Substituted "cv$temp$4$p" with its value "0.2".
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var74], 0.2);
							
							// Recorded the probability of reaching sample task 81 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// Substituted "y" with its value "0".
						// 
						// Processing random variable 69.
						// 
						// Value of the variable at this index
						if((1 == cv$valuePos)) {
							// Record the probability of sample task 81 generating output with current configuration.
							// 
							// cv$temp$5$p's comment
							// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
							// the output of Sample task 71.
							if((DistributionSampling.logProbabilityBernoulli(obs[var74], 0.8) < cv$accumulatedConsumerProbabilities))
								// cv$temp$5$p's comment
								// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
								// the output of Sample task 71.
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var74], 0.8) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// cv$temp$5$p's comment
									// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
									// the output of Sample task 71.
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var74], 0.8);
								else
									// cv$temp$5$p's comment
									// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
									// the output of Sample task 71.
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var74], 0.8))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var74], 0.8));
							}
							
							// Recorded the probability of reaching sample task 81 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
					cv$stateProbabilityValue = cv$accumulatedProbabilities;
				}
				if((1 == y)) {
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// Variable declaration of cv$temp$2$var64 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "y" with its value "1".
					double[] cv$temp$2$var64 = a[1];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$2$var64.length)?Math.log(cv$temp$2$var64[cv$valuePos]):Double.NEGATIVE_INFINITY);
					
					// Processing sample task 81 of consumer random variable null.
					for(int var74 = 0; var74 < length$obs_measured; var74 += 1) {
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Substituted "y" with its value "1".
						// 
						// Processing random variable 69.
						// 
						// Value of the variable at this index
						if((0 == cv$valuePos)) {
							// cv$temp$21$p's comment
							// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
							// the output of Sample task 71.
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var74], 0.4);
							
							// Recorded the probability of reaching sample task 81 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// Substituted "y" with its value "1".
						// 
						// Processing random variable 69.
						// 
						// Value of the variable at this index
						if((1 == cv$valuePos)) {
							// Record the probability of sample task 81 generating output with current configuration.
							// 
							// cv$temp$22$p's comment
							// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
							// the output of Sample task 71.
							if((DistributionSampling.logProbabilityBernoulli(obs[var74], 0.2) < cv$accumulatedConsumerProbabilities))
								// cv$temp$22$p's comment
								// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
								// the output of Sample task 71.
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var74], 0.2) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// cv$temp$22$p's comment
									// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
									// the output of Sample task 71.
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var74], 0.2);
								else
									// cv$temp$22$p's comment
									// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
									// the output of Sample task 71.
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var74], 0.2))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var74], 0.2));
							}
							
							// Recorded the probability of reaching sample task 81 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
						}
						
						// Substituted "y" with its value "1".
						// 
						// Processing random variable 69.
						// 
						// Value of the variable at this index
						if((2 == cv$valuePos)) {
							// Record the probability of sample task 81 generating output with current configuration.
							// 
							// cv$temp$23$p's comment
							// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
							// the output of Sample task 71.
							if((DistributionSampling.logProbabilityBernoulli(obs[var74], 0.6) < cv$accumulatedConsumerProbabilities))
								// cv$temp$23$p's comment
								// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
								// the output of Sample task 71.
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var74], 0.6) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// cv$temp$23$p's comment
									// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
									// the output of Sample task 71.
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var74], 0.6);
								else
									// cv$temp$23$p's comment
									// Enumerating the possible arguments for the variable Bernoulli 69 which is consuming
									// the output of Sample task 71.
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var74], 0.6))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var74], 0.6));
							}
							
							// Recorded the probability of reaching sample task 81 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
			} else {
				{
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample68$23" with its value "0".
					double cv$probabilitySample68Value24 = distribution$sample68[0];
					
					// Record the reached probability density.
					// 
					// Initialize a counter to track the reached distributions.
					cv$reachedDistributionSourceRV = cv$probabilitySample68Value24;
					
					// Variable declaration of cv$temp$1$var64 moved.
					// 
					// Constructing a random variable input for use later.
					double[] cv$temp$1$var64 = a[0];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample68Value24) + ((cv$valuePos < cv$temp$1$var64.length)?Math.log(cv$temp$1$var64[cv$valuePos]):Double.NEGATIVE_INFINITY));
					
					// Processing sample task 81 of consumer random variable null.
					for(int var74 = 0; var74 < length$obs_measured; var74 += 1) {
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Processing random variable 69.
						// 
						// Value of the variable at this index
						if((0 == cv$valuePos)) {
							// Substituted "cv$temp$9$p" with its value "0.2".
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var74], 0.2);
							
							// Recorded the probability of reaching sample task 81 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// Processing random variable 69.
						// 
						// Value of the variable at this index
						if((1 == cv$valuePos)) {
							// Record the probability of sample task 81 generating output with current configuration.
							// 
							// Substituted "cv$temp$11$p" with its value "0.8".
							if((DistributionSampling.logProbabilityBernoulli(obs[var74], 0.8) < cv$accumulatedConsumerProbabilities))
								// Substituted "cv$temp$11$p" with its value "0.8".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var74], 0.8) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "cv$temp$11$p" with its value "0.8".
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var74], 0.8);
								else
									// Substituted "cv$temp$11$p" with its value "0.8".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var74], 0.8))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var74], 0.8));
							}
							
							// Recorded the probability of reaching sample task 81 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
					cv$stateProbabilityValue = cv$accumulatedProbabilities;
				}
				
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "index$sample68$32" with its value "1".
				double cv$probabilitySample68Value33 = distribution$sample68[1];
				
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample68Value33);
				
				// Variable declaration of cv$temp$3$var64 moved.
				// 
				// Constructing a random variable input for use later.
				double[] cv$temp$3$var64 = a[1];
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				// 
				// Value of the variable at this index
				double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample68Value33) + ((cv$valuePos < cv$temp$3$var64.length)?Math.log(cv$temp$3$var64[cv$valuePos]):Double.NEGATIVE_INFINITY));
				
				// Processing sample task 81 of consumer random variable null.
				for(int var74 = 0; var74 < length$obs_measured; var74 += 1) {
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Processing random variable 69.
					// 
					// Value of the variable at this index
					if((0 == cv$valuePos)) {
						// Substituted "cv$temp$28$p" with its value "0.4".
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var74], 0.4);
						
						// Recorded the probability of reaching sample task 81 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					
					// Processing random variable 69.
					// 
					// Value of the variable at this index
					if((1 == cv$valuePos)) {
						// Record the probability of sample task 81 generating output with current configuration.
						// 
						// Substituted "cv$temp$30$p" with its value "0.2".
						if((DistributionSampling.logProbabilityBernoulli(obs[var74], 0.2) < cv$accumulatedConsumerProbabilities))
							// Substituted "cv$temp$30$p" with its value "0.2".
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var74], 0.2) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								// Substituted "cv$temp$30$p" with its value "0.2".
								cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var74], 0.2);
							else
								// Substituted "cv$temp$30$p" with its value "0.2".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var74], 0.2))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var74], 0.2));
						}
						
						// Recorded the probability of reaching sample task 81 with the current configuration.
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
					}
					
					// Processing random variable 69.
					// 
					// Value of the variable at this index
					if((2 == cv$valuePos)) {
						// Record the probability of sample task 81 generating output with current configuration.
						// 
						// Substituted "cv$temp$32$p" with its value "0.6".
						if((DistributionSampling.logProbabilityBernoulli(obs[var74], 0.6) < cv$accumulatedConsumerProbabilities))
							// Substituted "cv$temp$32$p" with its value "0.6".
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var74], 0.6) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								// Substituted "cv$temp$32$p" with its value "0.6".
								cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var74], 0.6);
							else
								// Substituted "cv$temp$32$p" with its value "0.6".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var74], 0.6))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var74], 0.6));
						}
						
						// Recorded the probability of reaching sample task 81 with the current configuration.
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
			// 
			// Get a local reference to the scratch space.
			cv$var66$stateProbabilityGlobal[cv$valuePos] = (cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV));
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
		double cv$lseMax = cv$var66$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var66$stateProbabilityGlobal[cv$lseIndex];
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
				cv$lseSum = (cv$lseSum + Math.exp((cv$var66$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
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
				cv$var66$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var66$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var66$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var66$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var66$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the new value of the sample.
		// 
		// Get a local reference to the scratch space.
		i = DistributionSampling.sampleCategorical(RNG$, cv$var66$stateProbabilityGlobal);
		
		// Guards to ensure that p is only updated when there is a valid path.
		// 
		// Write out the new sample value.
		p = b[y][i];
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var63$stateProbabilityGlobal
		// 
		// Allocation of cv$var63$stateProbabilityGlobal for single threaded execution
		cv$var63$stateProbabilityGlobal = new double[2];
		
		// Allocation of cv$var66$stateProbabilityGlobal for single threaded execution
		// 
		// Test if the input to putTask 30 is larger than the current values.
		cv$var66$stateProbabilityGlobal = new double[3];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for a
		a = new double[2][];
		a[0] = new double[2];
		a[1] = new double[3];
		
		// Constructor for b
		b = new double[2][];
		b[0] = new double[2];
		b[1] = new double[3];
		
		// Constructor for c
		c = new double[2];
		
		// If obs has not been set already allocate space.
		if(!setFlag$obs)
			// Constructor for obs
			obs = new boolean[length$obs_measured];
		
		// Constructor for distribution$sample68
		distribution$sample68 = new double[2];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample68)
			y = DistributionSampling.sampleCategorical(RNG$, c);
		if(!fixedFlag$sample71)
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
		if((!fixedFlag$sample68 || !fixedFlag$sample71))
			p = b[y][i];
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample81) {
			for(int var74 = 0; var74 < length$obs_measured; var74 += 1)
				obs[var74] = DistributionSampling.sampleBernoulli(RNG$, p);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample68) {
			// Save the probability of each value
			// 
			// cv$distribution$sample68's comment
			// Create local copy of variable probabilities.
			// 
			// Probability for this value
			distribution$sample68[0] = ((0 < c.length)?c[0]:0.0);
			
			// Save the probability of each value
			// 
			// cv$distribution$sample68's comment
			// Create local copy of variable probabilities.
			// 
			// Probability for this value
			distribution$sample68[1] = ((1 < c.length)?c[1]:0.0);
		}
		if(!fixedFlag$sample71)
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample68)
			y = DistributionSampling.sampleCategorical(RNG$, c);
		if(!fixedFlag$sample71)
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
		if((!fixedFlag$sample68 || !fixedFlag$sample71))
			p = b[y][i];
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample68)
				sample68();
			if(!fixedFlag$sample71)
				sample71();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!fixedFlag$sample71)
				sample71();
			if(!fixedFlag$sample68)
				sample68();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		double[] var7 = a[0];
		var7[0] = 0.4;
		var7[1] = 0.6;
		double[] var17 = a[1];
		var17[0] = 0.2;
		var17[1] = 0.3;
		var17[2] = 0.5;
		double[] var32 = b[0];
		var32[0] = 0.2;
		var32[1] = 0.8;
		double[] var42 = b[1];
		var42[0] = 0.4;
		var42[1] = 0.2;
		var42[2] = 0.6;
		c[0] = 0.35;
		c[1] = 0.65;
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
		logProbability$var62 = 0.0;
		logProbability$p = 0.0;
		if(!fixedProbFlag$sample68)
			logProbability$y = 0.0;
		logProbability$var65 = 0.0;
		if(!fixedProbFlag$sample71)
			logProbability$i = 0.0;
		logProbability$var69 = 0.0;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample81)
			logProbability$var75 = 0.0;
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
		if(fixedFlag$sample71)
			logProbabilityValue$sample71();
		logProbabilityValue$sample81();
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
		logProbabilityDistribution$sample68();
		logProbabilityDistribution$sample71();
		logProbabilityDistribution$sample81();
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
		logProbabilityValue$sample68();
		logProbabilityValue$sample71();
		logProbabilityValue$sample81();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample68)
			y = DistributionSampling.sampleCategorical(RNG$, c);
		if(!fixedFlag$sample71)
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
		if((!fixedFlag$sample68 || !fixedFlag$sample71))
			p = b[y][i];
		
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
		int cv$length1 = obs.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			obs[cv$index1] = obs_measured[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		p = b[y][i];
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2025, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n \n package org.sandwood.compiler.tests.parser;\n\npublic model RaggedArray2(boolean[] obs_measured) {\n    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n    double[][] b = {{0.2, 0.8}, {0.4, 0.2, 0.6}};\n    double[] c = { 0.35, 0.65 };\n    int y = categorical(c).sampleDistribution();\n    int i = categorical(a[y]).sample();\n    double p = b[y][i];\n    boolean[] obs = bernoulli(p).sample(obs_measured.length);\n    obs.observe(obs_measured);\n}";
	}
}