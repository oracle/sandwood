package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements RaggedArray2$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] a;
	private double[][] b;
	private double[] c;
	private double[] cv$var79$stateProbabilityGlobal;
	private double[] cv$var82$stateProbabilityGlobal;
	private double[] distribution$sample84;
	private boolean fixedFlag$sample104 = false;
	private boolean fixedFlag$sample84 = false;
	private boolean fixedFlag$sample87 = false;
	private boolean fixedProbFlag$sample104 = false;
	private boolean fixedProbFlag$sample84 = false;
	private boolean fixedProbFlag$sample87 = false;
	private int i;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$i;
	private double logProbability$obs;
	private double logProbability$p;
	private double logProbability$var78;
	private double logProbability$var81;
	private double logProbability$var85;
	private double logProbability$var98;
	private double logProbability$y;
	private boolean[] obs;
	private boolean[] obs_measured;
	private double p;
	private boolean setFlag$obs = false;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray2$MultiThreadCPU(ExecutionTarget target) {
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

	// Getter for fixedFlag$sample104.
	@Override
	public final boolean get$fixedFlag$sample104() {
		return fixedFlag$sample104;
	}

	// Setter for fixedFlag$sample104.
	@Override
	public final void set$fixedFlag$sample104(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample104 including if probabilities
		// need to be updated.
		fixedFlag$sample104 = cv$value;
		
		// Should the probability of sample 104 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample104" with its value "cv$value".
		fixedProbFlag$sample104 = (cv$value && fixedProbFlag$sample104);
	}

	// Getter for fixedFlag$sample84.
	@Override
	public final boolean get$fixedFlag$sample84() {
		return fixedFlag$sample84;
	}

	// Setter for fixedFlag$sample84.
	@Override
	public final void set$fixedFlag$sample84(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample84 including if probabilities
		// need to be updated.
		fixedFlag$sample84 = cv$value;
		
		// Should the probability of sample 84 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample84" with its value "cv$value".
		fixedProbFlag$sample84 = (cv$value && fixedProbFlag$sample84);
		
		// Should the probability of sample 87 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample84" with its value "cv$value".
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
		
		// Should the probability of sample 104 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample84" with its value "cv$value".
		fixedProbFlag$sample104 = (cv$value && fixedProbFlag$sample104);
	}

	// Getter for fixedFlag$sample87.
	@Override
	public final boolean get$fixedFlag$sample87() {
		return fixedFlag$sample87;
	}

	// Setter for fixedFlag$sample87.
	@Override
	public final void set$fixedFlag$sample87(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample87 including if probabilities
		// need to be updated.
		fixedFlag$sample87 = cv$value;
		
		// Should the probability of sample 87 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample87" with its value "cv$value".
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
		
		// Should the probability of sample 104 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample87" with its value "cv$value".
		fixedProbFlag$sample104 = (cv$value && fixedProbFlag$sample104);
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
		
		// Unset the fixed probability flag for sample 87 as it depends on i.
		fixedProbFlag$sample87 = false;
		
		// Unset the fixed probability flag for sample 104 as it depends on i.
		fixedProbFlag$sample104 = false;
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
		
		// Unset the fixed probability flag for sample 104 as it depends on obs.
		fixedProbFlag$sample104 = false;
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
		
		// Unset the fixed probability flag for sample 84 as it depends on y.
		fixedProbFlag$sample84 = false;
		
		// Unset the fixed probability flag for sample 87 as it depends on y.
		fixedProbFlag$sample87 = false;
		
		// Unset the fixed probability flag for sample 104 as it depends on y.
		fixedProbFlag$sample104 = false;
	}

	// Calculate the probability of the samples represented by sample104 using probability
	// distributions.
	private final void logProbabilityDistribution$sample104() {
		// Determine if we need to calculate the values for sample task 104 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample104) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 104 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				boolean cv$sampleValue = obs[var97];
				
				// Enumerating the possible arguments for Bernoulli 85.
				// 
				// Enumerating the possible arguments for Bernoulli 85.
				// 
				// Enumerating the possible arguments for Bernoulli 85.
				// 
				// Enumerating the possible arguments for Bernoulli 85.
				// 
				// Enumerating the possible arguments for Bernoulli 85.
				if(fixedFlag$sample84) {
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
						// Substituted "index$sample84$4" with its value "0".
						double cv$probabilitySample84Value5 = distribution$sample84[0];
						
						// Store the value of the function call, so the function call is only made once.
						cv$distributionAccumulator = (Math.log(cv$probabilitySample84Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.2));
						
						// Add the probability of this distribution configuration to the accumulator.
						// 
						// An accumulator for the distributed probability space covered.
						cv$probabilityReached = cv$probabilitySample84Value5;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == i)) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample84$12" with its value "0".
						double cv$probabilitySample84Value13 = distribution$sample84[0];
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(cv$probabilitySample84Value13) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.8));
						
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
						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample84Value13);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((0 == i)) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample84$20" with its value "1".
						double cv$probabilitySample84Value21 = distribution$sample84[1];
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(cv$probabilitySample84Value21) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.4));
						
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
						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample84Value21);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == i)) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample84$28" with its value "1".
						double cv$probabilitySample84Value29 = distribution$sample84[1];
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(cv$probabilitySample84Value29) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.2));
						
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
						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample84Value29);
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((2 == i)) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample84$36" with its value "1".
						double cv$probabilitySample84Value37 = distribution$sample84[1];
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(cv$probabilitySample84Value37) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, 0.6));
						
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
						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample84Value37);
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
			logProbability$var85 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var98 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample104 = ((fixedFlag$sample104 && fixedFlag$sample84) && fixedFlag$sample87);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var85 = logProbability$var98;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$obs = (logProbability$obs + logProbability$var98);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var98);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var98);
		}
	}

	// Calculate the probability of the samples represented by sample84 using probability
	// distributions.
	private final void logProbabilityDistribution$sample84() {
		// Determine if we need to calculate the values for sample task 84 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample84) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample84) {
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
				logProbability$var78 = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$y = cv$distributionAccumulator;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(fixedFlag$sample87)
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
				// Substituted "fixedFlag$sample84" with its value "true".
				fixedProbFlag$sample84 = true;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var78 = logProbability$y;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample84 && fixedFlag$sample87))
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
			if(fixedFlag$sample84)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$y);
		}
	}

	// Calculate the probability of the samples represented by sample87 using probability
	// distributions.
	private final void logProbabilityDistribution$sample87() {
		// Determine if we need to calculate the values for sample task 87 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample87) {
			// Generating probabilities for sample task
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			
			// Enumerating the possible arguments for Categorical 81.
			// 
			// Enumerating the possible arguments for Categorical 81.
			if(fixedFlag$sample84) {
				if((0 == y)) {
					// Substituted "y" with its value "0".
					double[] var80 = a[0];
					
					// Store the value of the function call, so the function call is only made once.
					// 
					// The sample value to calculate the probability of generating
					cv$distributionAccumulator = (((0.0 <= i) && (i < var80.length))?Math.log(var80[i]):Double.NEGATIVE_INFINITY);
					
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					cv$probabilityReached = 1.0;
				}
				if((1 == y)) {
					// Substituted "y" with its value "1".
					double[] var80 = a[1];
					
					// Store the value of the function call, so the function call is only made once.
					// 
					// The sample value to calculate the probability of generating
					double cv$weightedProbability = (((0.0 <= i) && (i < var80.length))?Math.log(var80[i]):Double.NEGATIVE_INFINITY);
					
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
					// Substituted "index$sample84$3" with its value "0".
					double cv$probabilitySample84Value4 = distribution$sample84[0];
					double[] var80 = a[0];
					
					// Store the value of the function call, so the function call is only made once.
					// 
					// The sample value to calculate the probability of generating
					cv$distributionAccumulator = (Math.log(cv$probabilitySample84Value4) + (((0.0 <= i) && (i < var80.length))?Math.log(var80[i]):Double.NEGATIVE_INFINITY));
					
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					cv$probabilityReached = cv$probabilitySample84Value4;
				}
				
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "index$sample84$12" with its value "1".
				double cv$probabilitySample84Value13 = distribution$sample84[1];
				double[] var80 = a[1];
				
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				double cv$weightedProbability = (Math.log(cv$probabilitySample84Value13) + (((0.0 <= i) && (i < var80.length))?Math.log(var80[i]):Double.NEGATIVE_INFINITY));
				
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
				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample84Value13);
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
			logProbability$var81 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$i = cv$distributionAccumulator;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample84 && fixedFlag$sample87))
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
			if(fixedFlag$sample87)
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
			fixedProbFlag$sample87 = (fixedFlag$sample87 && fixedFlag$sample84);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var81 = logProbability$i;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample84 && fixedFlag$sample87))
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
			if(fixedFlag$sample87)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$i);
		}
	}

	// Calculate the probability of the samples represented by sample104 using sampled
	// values.
	private final void logProbabilityValue$sample104() {
		// Determine if we need to calculate the values for sample task 104 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample104) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var97 = 0; var97 < length$obs_measured; var97 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(obs[var97], p));
			logProbability$var85 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var98 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample104 = ((fixedFlag$sample104 && fixedFlag$sample84) && fixedFlag$sample87);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var85 = logProbability$var98;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$obs = (logProbability$obs + logProbability$var98);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var98);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var98);
		}
	}

	// Calculate the probability of the samples represented by sample84 using sampled
	// values.
	private final void logProbabilityValue$sample84() {
		// Determine if we need to calculate the values for sample task 84 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample84) {
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
			logProbability$var78 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample84)
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
			fixedProbFlag$sample84 = fixedFlag$sample84;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var78 = logProbability$y;
			
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
			if(fixedFlag$sample84)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$y);
		}
	}

	// Calculate the probability of the samples represented by sample87 using sampled
	// values.
	private final void logProbabilityValue$sample87() {
		// Determine if we need to calculate the values for sample task 87 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample87) {
			// Generating probabilities for sample task
			double[] var80 = a[y];
			
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
			double cv$distributionAccumulator = (((0.0 <= i) && (i < var80.length))?Math.log(var80[i]):Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var81 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample87)
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
			fixedProbFlag$sample87 = (fixedFlag$sample87 && fixedFlag$sample84);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var81 = logProbability$i;
			
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
			if(fixedFlag$sample87)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$i);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 84 drawn from Categorical 78. Inference was performed using variable
	// marginalization.
	private final void sample84() {
		{
			// Processing random variable 81.
			// 
			// Variable declaration of cv$temp$1$var80 moved.
			// 
			// Constructing a random variable input for use later.
			double[] cv$temp$1$var80 = a[0];
			
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
			// Recorded the probability of reaching sample task 87 with the current configuration.
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
			double cv$accumulatedProbabilities = ((((0.0 <= i) && (i < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[i]):Double.NEGATIVE_INFINITY) + ((0 < c.length)?Math.log(c[0]):Double.NEGATIVE_INFINITY));
			
			// Processing sample task 104 of consumer random variable null.
			for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Processing random variable 85.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				if((0 == i)) {
					// Substituted "cv$temp$3$p" with its value "0.2".
					cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2);
					
					// Recorded the probability of reaching sample task 104 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					cv$consumerDistributionProbabilityAccumulator = 0.0;
				}
				
				// Processing random variable 85.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				if((1 == i)) {
					// Record the probability of sample task 104 generating output with current configuration.
					// 
					// cv$temp$4$p's comment
					// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
					// the output of Sample task 84.
					if((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8) < cv$accumulatedConsumerProbabilities))
						// cv$temp$4$p's comment
						// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
						// the output of Sample task 84.
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
					else {
						// If the second value is -infinity.
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							// cv$temp$4$p's comment
							// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
							// the output of Sample task 84.
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8);
						else
							// cv$temp$4$p's comment
							// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
							// the output of Sample task 84.
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8));
					}
					
					// Recorded the probability of reaching sample task 104 with the current configuration.
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
			cv$var79$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Processing random variable 81.
		// 
		// Variable declaration of cv$temp$2$var80 moved.
		// 
		// Constructing a random variable input for use later.
		double[] cv$temp$2$var80 = a[1];
		
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
		// Recorded the probability of reaching sample task 87 with the current configuration.
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
		double cv$accumulatedProbabilities = ((((0.0 <= i) && (i < cv$temp$2$var80.length))?Math.log(cv$temp$2$var80[i]):Double.NEGATIVE_INFINITY) + ((1 < c.length)?Math.log(c[1]):Double.NEGATIVE_INFINITY));
		
		// Processing sample task 104 of consumer random variable null.
		for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
			
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			double cv$consumerDistributionProbabilityAccumulator = 1.0;
			if((0 == i)) {
				// cv$temp$5$p's comment
				// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
				// the output of Sample task 84.
				cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.4);
				
				// Recorded the probability of reaching sample task 104 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				cv$consumerDistributionProbabilityAccumulator = 0.0;
			}
			if((1 == i)) {
				// Record the probability of sample task 104 generating output with current configuration.
				// 
				// cv$temp$6$p's comment
				// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
				// the output of Sample task 84.
				if((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2) < cv$accumulatedConsumerProbabilities))
					// cv$temp$6$p's comment
					// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
					// the output of Sample task 84.
					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
				else {
					// If the second value is -infinity.
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						// cv$temp$6$p's comment
						// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
						// the output of Sample task 84.
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2);
					else
						// cv$temp$6$p's comment
						// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
						// the output of Sample task 84.
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2));
				}
				
				// Recorded the probability of reaching sample task 104 with the current configuration.
				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
			}
			if((2 == i)) {
				// Record the probability of sample task 104 generating output with current configuration.
				// 
				// cv$temp$7$p's comment
				// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
				// the output of Sample task 84.
				if((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6) < cv$accumulatedConsumerProbabilities))
					// cv$temp$7$p's comment
					// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
					// the output of Sample task 84.
					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
				else {
					// If the second value is -infinity.
					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
						// cv$temp$7$p's comment
						// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
						// the output of Sample task 84.
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6);
					else
						// cv$temp$7$p's comment
						// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
						// the output of Sample task 84.
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6));
				}
				
				// Recorded the probability of reaching sample task 104 with the current configuration.
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
		cv$var79$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var79$stateProbabilityGlobal[0];
		
		// Unrolled loop
		// 
		// Get a local reference to the scratch space.
		double cv$lseElementValue = cv$var79$stateProbabilityGlobal[1];
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
			cv$logSum = (Math.log((Math.exp((cv$var79$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var79$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Unrolled loop
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample84[0] = 0.5;
			
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample84[1] = 0.5;
		} else {
			// Unrolled loop
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample84[0] = Math.exp((cv$var79$stateProbabilityGlobal[0] - cv$logSum));
			
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample84[1] = Math.exp((cv$var79$stateProbabilityGlobal[1] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = 2; cv$indexName < cv$var79$stateProbabilityGlobal.length; cv$indexName += 1)
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample84[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 87 drawn from Categorical 81. Inference was performed using variable
	// marginalization.
	private final void sample87() {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		
		// Exploring all the possible state counts for random variable 81.
		// 
		// Enumerating the possible arguments for Categorical 81.
		// 
		// Enumerating the possible arguments for Categorical 81.
		if(fixedFlag$sample84) {
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
			// Exploring all the possible distribution values for random variable 81 creating
			// sample task 87.
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
			
			// Enumerating the possible arguments for Categorical 81.
			// 
			// Enumerating the possible arguments for Categorical 81.
			if(fixedFlag$sample84) {
				if((0 == y)) {
					// Record the reached probability density.
					// 
					// Initialize a counter to track the reached distributions.
					cv$reachedDistributionSourceRV = 1.0;
					
					// Variable declaration of cv$temp$0$var80 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "y" with its value "0".
					double[] cv$temp$0$var80 = a[0];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var80.length)?Math.log(cv$temp$0$var80[cv$valuePos]):Double.NEGATIVE_INFINITY);
					
					// Processing sample task 104 of consumer random variable null.
					for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Substituted "y" with its value "0".
						// 
						// Processing random variable 85.
						// 
						// Value of the variable at this index
						if((0 == cv$valuePos)) {
							// Substituted "cv$temp$4$p" with its value "0.2".
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2);
							
							// Recorded the probability of reaching sample task 104 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// Substituted "y" with its value "0".
						// 
						// Processing random variable 85.
						// 
						// Value of the variable at this index
						if((1 == cv$valuePos)) {
							// Record the probability of sample task 104 generating output with current configuration.
							// 
							// cv$temp$5$p's comment
							// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
							// the output of Sample task 87.
							if((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8) < cv$accumulatedConsumerProbabilities))
								// cv$temp$5$p's comment
								// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
								// the output of Sample task 87.
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// cv$temp$5$p's comment
									// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
									// the output of Sample task 87.
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8);
								else
									// cv$temp$5$p's comment
									// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
									// the output of Sample task 87.
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8));
							}
							
							// Recorded the probability of reaching sample task 104 with the current configuration.
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
					
					// Variable declaration of cv$temp$2$var80 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "y" with its value "1".
					double[] cv$temp$2$var80 = a[1];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$2$var80.length)?Math.log(cv$temp$2$var80[cv$valuePos]):Double.NEGATIVE_INFINITY);
					
					// Processing sample task 104 of consumer random variable null.
					for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Substituted "y" with its value "1".
						// 
						// Processing random variable 85.
						// 
						// Value of the variable at this index
						if((0 == cv$valuePos)) {
							// cv$temp$21$p's comment
							// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
							// the output of Sample task 87.
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.4);
							
							// Recorded the probability of reaching sample task 104 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// Substituted "y" with its value "1".
						// 
						// Processing random variable 85.
						// 
						// Value of the variable at this index
						if((1 == cv$valuePos)) {
							// Record the probability of sample task 104 generating output with current configuration.
							// 
							// cv$temp$22$p's comment
							// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
							// the output of Sample task 87.
							if((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2) < cv$accumulatedConsumerProbabilities))
								// cv$temp$22$p's comment
								// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
								// the output of Sample task 87.
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// cv$temp$22$p's comment
									// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
									// the output of Sample task 87.
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2);
								else
									// cv$temp$22$p's comment
									// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
									// the output of Sample task 87.
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2));
							}
							
							// Recorded the probability of reaching sample task 104 with the current configuration.
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
						}
						
						// Substituted "y" with its value "1".
						// 
						// Processing random variable 85.
						// 
						// Value of the variable at this index
						if((2 == cv$valuePos)) {
							// Record the probability of sample task 104 generating output with current configuration.
							// 
							// cv$temp$23$p's comment
							// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
							// the output of Sample task 87.
							if((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6) < cv$accumulatedConsumerProbabilities))
								// cv$temp$23$p's comment
								// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
								// the output of Sample task 87.
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// cv$temp$23$p's comment
									// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
									// the output of Sample task 87.
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6);
								else
									// cv$temp$23$p's comment
									// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
									// the output of Sample task 87.
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6));
							}
							
							// Recorded the probability of reaching sample task 104 with the current configuration.
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
					// Substituted "index$sample84$23" with its value "0".
					double cv$probabilitySample84Value24 = distribution$sample84[0];
					
					// Record the reached probability density.
					// 
					// Initialize a counter to track the reached distributions.
					cv$reachedDistributionSourceRV = cv$probabilitySample84Value24;
					
					// Variable declaration of cv$temp$1$var80 moved.
					// 
					// Constructing a random variable input for use later.
					double[] cv$temp$1$var80 = a[0];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample84Value24) + ((cv$valuePos < cv$temp$1$var80.length)?Math.log(cv$temp$1$var80[cv$valuePos]):Double.NEGATIVE_INFINITY));
					
					// Processing sample task 104 of consumer random variable null.
					for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Processing random variable 85.
						// 
						// Value of the variable at this index
						if((0 == cv$valuePos)) {
							// Substituted "cv$temp$9$p" with its value "0.2".
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2);
							
							// Recorded the probability of reaching sample task 104 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						
						// Processing random variable 85.
						// 
						// Value of the variable at this index
						if((1 == cv$valuePos)) {
							// Record the probability of sample task 104 generating output with current configuration.
							// 
							// Substituted "cv$temp$11$p" with its value "0.8".
							if((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8) < cv$accumulatedConsumerProbabilities))
								// Substituted "cv$temp$11$p" with its value "0.8".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								// If the second value is -infinity.
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									// Substituted "cv$temp$11$p" with its value "0.8".
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8);
								else
									// Substituted "cv$temp$11$p" with its value "0.8".
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var97], 0.8));
							}
							
							// Recorded the probability of reaching sample task 104 with the current configuration.
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
				// Substituted "index$sample84$32" with its value "1".
				double cv$probabilitySample84Value33 = distribution$sample84[1];
				
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample84Value33);
				
				// Variable declaration of cv$temp$3$var80 moved.
				// 
				// Constructing a random variable input for use later.
				double[] cv$temp$3$var80 = a[1];
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				// 
				// Value of the variable at this index
				double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample84Value33) + ((cv$valuePos < cv$temp$3$var80.length)?Math.log(cv$temp$3$var80[cv$valuePos]):Double.NEGATIVE_INFINITY));
				
				// Processing sample task 104 of consumer random variable null.
				for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Processing random variable 85.
					// 
					// Value of the variable at this index
					if((0 == cv$valuePos)) {
						// Substituted "cv$temp$28$p" with its value "0.4".
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.4);
						
						// Recorded the probability of reaching sample task 104 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					
					// Processing random variable 85.
					// 
					// Value of the variable at this index
					if((1 == cv$valuePos)) {
						// Record the probability of sample task 104 generating output with current configuration.
						// 
						// Substituted "cv$temp$30$p" with its value "0.2".
						if((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2) < cv$accumulatedConsumerProbabilities))
							// Substituted "cv$temp$30$p" with its value "0.2".
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								// Substituted "cv$temp$30$p" with its value "0.2".
								cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2);
							else
								// Substituted "cv$temp$30$p" with its value "0.2".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var97], 0.2));
						}
						
						// Recorded the probability of reaching sample task 104 with the current configuration.
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
					}
					
					// Processing random variable 85.
					// 
					// Value of the variable at this index
					if((2 == cv$valuePos)) {
						// Record the probability of sample task 104 generating output with current configuration.
						// 
						// Substituted "cv$temp$32$p" with its value "0.6".
						if((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6) < cv$accumulatedConsumerProbabilities))
							// Substituted "cv$temp$32$p" with its value "0.6".
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							// If the second value is -infinity.
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								// Substituted "cv$temp$32$p" with its value "0.6".
								cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6);
							else
								// Substituted "cv$temp$32$p" with its value "0.6".
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6))) + 1)) + DistributionSampling.logProbabilityBernoulli(obs[var97], 0.6));
						}
						
						// Recorded the probability of reaching sample task 104 with the current configuration.
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
			cv$var82$stateProbabilityGlobal[cv$valuePos] = (cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV));
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
		double cv$lseMax = cv$var82$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var82$stateProbabilityGlobal[cv$lseIndex];
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
				cv$lseSum = (cv$lseSum + Math.exp((cv$var82$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
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
				cv$var82$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var82$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var82$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var82$stateProbabilityGlobal.length; cv$indexName += 1)
			// Get a local reference to the scratch space.
			cv$var82$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the new value of the sample.
		// 
		// Get a local reference to the scratch space.
		i = DistributionSampling.sampleCategorical(RNG$, cv$var82$stateProbabilityGlobal);
		
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
		// Constructor for cv$var79$stateProbabilityGlobal
		// 
		// Allocation of cv$var79$stateProbabilityGlobal for single threaded execution
		cv$var79$stateProbabilityGlobal = new double[2];
		
		// Allocation of cv$var82$stateProbabilityGlobal for single threaded execution
		// 
		// Test if the input to putTask 37 is larger than the current values.
		cv$var82$stateProbabilityGlobal = new double[3];
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
		
		// Constructor for distribution$sample84
		distribution$sample84 = new double[2];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample84)
			y = DistributionSampling.sampleCategorical(RNG$, c);
		if(!fixedFlag$sample87)
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
		if((!fixedFlag$sample84 || !fixedFlag$sample87))
			p = b[y][i];
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample104)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$obs_measured, 1,
				(int forStart$var97, int forEnd$var97, int threadID$var97, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var97 = forStart$var97; var97 < forEnd$var97; var97 += 1)
							obs[var97] = DistributionSampling.sampleBernoulli(RNG$1, p);
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample84) {
			// Save the probability of each value
			// 
			// cv$distribution$sample84's comment
			// Create local copy of variable probabilities.
			// 
			// Probability for this value
			distribution$sample84[0] = ((0 < c.length)?c[0]:0.0);
			
			// Save the probability of each value
			// 
			// cv$distribution$sample84's comment
			// Create local copy of variable probabilities.
			// 
			// Probability for this value
			distribution$sample84[1] = ((1 < c.length)?c[1]:0.0);
		}
		if(!fixedFlag$sample87)
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample84)
			y = DistributionSampling.sampleCategorical(RNG$, c);
		if(!fixedFlag$sample87)
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
		if((!fixedFlag$sample84 || !fixedFlag$sample87))
			p = b[y][i];
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample84)
				sample84();
			if(!fixedFlag$sample87)
				sample87();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!fixedFlag$sample87)
				sample87();
			if(!fixedFlag$sample84)
				sample84();
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
		double[] var20 = a[1];
		var20[0] = 0.2;
		var20[1] = 0.3;
		var20[2] = 0.5;
		double[] var39 = b[0];
		var39[0] = 0.2;
		var39[1] = 0.8;
		double[] var52 = b[1];
		var52[0] = 0.4;
		var52[1] = 0.2;
		var52[2] = 0.6;
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
		logProbability$var78 = 0.0;
		logProbability$p = 0.0;
		if(!fixedProbFlag$sample84)
			logProbability$y = 0.0;
		logProbability$var81 = 0.0;
		if(!fixedProbFlag$sample87)
			logProbability$i = 0.0;
		logProbability$var85 = 0.0;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample104)
			logProbability$var98 = 0.0;
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
		if(fixedFlag$sample87)
			logProbabilityValue$sample87();
		logProbabilityValue$sample104();
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
		logProbabilityDistribution$sample84();
		logProbabilityDistribution$sample87();
		logProbabilityDistribution$sample104();
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
		logProbabilityValue$sample84();
		logProbabilityValue$sample87();
		logProbabilityValue$sample104();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample84)
			y = DistributionSampling.sampleCategorical(RNG$, c);
		if(!fixedFlag$sample87)
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
		if((!fixedFlag$sample84 || !fixedFlag$sample87))
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
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + " package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model RaggedArray2(boolean[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    double[][] b = {{0.2, 0.8}, {0.4, 0.2, 0.6}};\n"
		     + "    double[] c = { 0.35, 0.65 };\n"
		     + "    int y = categorical(c).sampleDistribution();\n"
		     + "    int i = categorical(a[y]).sample();\n"
		     + "    double p = b[y][i];\n"
		     + "    boolean[] obs = bernoulli(p).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}