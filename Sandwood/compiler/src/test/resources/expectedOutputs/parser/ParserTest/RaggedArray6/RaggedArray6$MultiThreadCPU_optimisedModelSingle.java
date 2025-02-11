package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray6$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements RaggedArray6$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] a;
	private double[] b;
	private double[] cv$var38$stateProbabilityGlobal;
	private double[] d;
	private double[] distribution$sample41;
	private boolean fixedFlag$sample41 = false;
	private boolean fixedFlag$sample44 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedProbFlag$sample41 = false;
	private boolean fixedProbFlag$sample44 = false;
	private boolean fixedProbFlag$sample53 = false;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$d;
	private double logProbability$obs;
	private double logProbability$var37;
	private double logProbability$var40;
	private double logProbability$var43;
	private double logProbability$var49;
	private double logProbability$y;
	private boolean[] obs;
	private boolean[] obs_measured;
	private boolean setFlag$d = false;
	private boolean setFlag$obs = false;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray6$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for a.
	@Override
	public final double[][] get$a() {
		return a;
	}

	// Getter for b.
	@Override
	public final double[] get$b() {
		return b;
	}

	// Getter for d.
	@Override
	public final double[] get$d() {
		return d;
	}

	// Setter for d.
	@Override
	public final void set$d(double[] cv$value) {
		// Set flags for all the side effects of d including if probabilities need to be updated.
		// Set d with flag to mark that it has been set so another array doesn't need to be
		// constructed
		d = cv$value;
		setFlag$d = true;
		
		// Unset the fixed probability flag for sample 44 as it depends on d.
		fixedProbFlag$sample44 = false;
		
		// Unset the fixed probability flag for sample 53 as it depends on d.
		fixedProbFlag$sample53 = false;
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
		
		// Should the probability of sample 44 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample41" with its value "cv$value".
		fixedProbFlag$sample44 = (cv$value && fixedProbFlag$sample44);
		
		// Should the probability of sample 53 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample41" with its value "cv$value".
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
	}

	// Getter for fixedFlag$sample44.
	@Override
	public final boolean get$fixedFlag$sample44() {
		return fixedFlag$sample44;
	}

	// Setter for fixedFlag$sample44.
	@Override
	public final void set$fixedFlag$sample44(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample44 including if probabilities
		// need to be updated.
		fixedFlag$sample44 = cv$value;
		
		// Should the probability of sample 44 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample44" with its value "cv$value".
		fixedProbFlag$sample44 = (cv$value && fixedProbFlag$sample44);
		
		// Should the probability of sample 53 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample44" with its value "cv$value".
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
	}

	// Getter for fixedFlag$sample53.
	@Override
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	// Setter for fixedFlag$sample53.
	@Override
	public final void set$fixedFlag$sample53(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample53 including if probabilities
		// need to be updated.
		fixedFlag$sample53 = cv$value;
		
		// Should the probability of sample 53 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample53" with its value "cv$value".
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
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

	// Getter for logProbability$d.
	@Override
	public final double get$logProbability$d() {
		return logProbability$d;
	}

	// Getter for logProbability$obs.
	@Override
	public final double get$logProbability$obs() {
		return logProbability$obs;
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
		
		// Unset the fixed probability flag for sample 53 as it depends on obs.
		fixedProbFlag$sample53 = false;
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
		
		// Unset the fixed probability flag for sample 41 as it depends on y.
		fixedProbFlag$sample41 = false;
		
		// Unset the fixed probability flag for sample 44 as it depends on y.
		fixedProbFlag$sample44 = false;
		
		// Unset the fixed probability flag for sample 53 as it depends on y.
		fixedProbFlag$sample53 = false;
	}

	// Calculate the probability of the samples represented by sample41 using probability
	// distributions.
	private final void logProbabilityDistribution$sample41() {
		// Determine if we need to calculate the values for sample task 41 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample41) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample41) {
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
				double cv$distributionAccumulator = (((0.0 <= y) && (y < b.length))?Math.log(b[y]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$var37 = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$y = cv$distributionAccumulator;
				
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
				// Substituted "fixedFlag$sample41" with its value "true".
				fixedProbFlag$sample41 = true;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var37 = logProbability$y;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$y);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample41)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$y);
		}
	}

	// Calculate the probability of the samples represented by sample44 using probability
	// distributions.
	private final void logProbabilityDistribution$sample44() {
		// Determine if we need to calculate the values for sample task 44 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample44) {
			// Generating probabilities for sample task
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			
			// Enumerating the possible arguments for Dirichlet 40.
			// 
			// Enumerating the possible arguments for Dirichlet 40.
			if(fixedFlag$sample41) {
				if((0 == y)) {
					// Store the value of the function call, so the function call is only made once.
					// 
					// The sample value to calculate the probability of generating
					// 
					// Substituted "y" with its value "0".
					cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(d, a[0]);
					
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					cv$probabilityReached = 1.0;
				}
				if((1 == y)) {
					// Store the value of the function call, so the function call is only made once.
					// 
					// The sample value to calculate the probability of generating
					// 
					// Substituted "y" with its value "1".
					double cv$weightedProbability = DistributionSampling.logProbabilityDirichlet(d, a[1]);
					
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
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "index$sample41$3" with its value "0".
				double cv$probabilitySample41Value4 = distribution$sample41[0];
				
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				cv$distributionAccumulator = (Math.log(cv$probabilitySample41Value4) + DistributionSampling.logProbabilityDirichlet(d, a[0]));
				
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "index$sample41$12" with its value "1".
				double cv$probabilitySample41Value13 = distribution$sample41[1];
				
				// Store the value of the function call, so the function call is only made once.
				// 
				// The sample value to calculate the probability of generating
				double cv$weightedProbability = (Math.log(cv$probabilitySample41Value13) + DistributionSampling.logProbabilityDirichlet(d, a[1]));
				
				// Add the probability of this sample task to the distribution accumulator.
				if((cv$weightedProbability < cv$distributionAccumulator))
					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
				else {
					// If the second value is -infinity.
					// 
					// cv$probabilitySample41Value4's comment
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample41$3" with its value "0".
					// 
					// cv$probabilitySample41Value4's comment
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample41$3" with its value "0".
					// 
					// cv$probabilitySample41Value4's comment
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample41$3" with its value "0".
					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
						cv$distributionAccumulator = cv$weightedProbability;
					else
						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
				}
				
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// Add the probability of this distribution configuration to the accumulator.
				// 
				// An accumulator for the distributed probability space covered.
				cv$probabilityReached = (cv$probabilitySample41Value4 + cv$probabilitySample41Value13);
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
			logProbability$var40 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$d = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample44)
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
			fixedProbFlag$sample44 = (fixedFlag$sample44 && fixedFlag$sample41);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var40 = logProbability$d;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$d);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample44)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$d);
		}
	}

	// Calculate the probability of the samples represented by sample53 using probability
	// distributions.
	private final void logProbabilityDistribution$sample53() {
		// Determine if we need to calculate the values for sample task 53 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample53) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var48 = 0; var48 < length$obs_measured; var48 += 1) {
				// This value is not used before it is set again, so removing the value declaration.
				// 
				// An accumulator for log probabilities.
				double cv$distributionAccumulator;
				
				// This value is not used before it is set again, so removing the value declaration.
				// 
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached;
				
				// Look for paths between the variable and the sample task 53 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				boolean cv$sampleValue = obs[var48];
				
				// Enumerating the possible arguments for Bernoulli 43.
				if(fixedFlag$sample41) {
					// Store the value of the function call, so the function call is only made once.
					cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, d[y]);
					
					// Add the probability of this distribution configuration to the accumulator.
					// 
					// An accumulator for the distributed probability space covered.
					cv$probabilityReached = 1.0;
				} else {
					// Unrolled loop
					{
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample41$3" with its value "0".
						double cv$probabilitySample41Value4 = distribution$sample41[0];
						
						// Store the value of the function call, so the function call is only made once.
						// 
						// Substituted "index$sample41$3" with its value "0".
						cv$distributionAccumulator = (Math.log(cv$probabilitySample41Value4) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, d[0]));
						
						// Add the probability of this distribution configuration to the accumulator.
						// 
						// An accumulator for the distributed probability space covered.
						cv$probabilityReached = cv$probabilitySample41Value4;
					}
					
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample41$3" with its value "1".
					double cv$probabilitySample41Value4 = distribution$sample41[1];
					
					// Store the value of the function call, so the function call is only made once.
					// 
					// Substituted "index$sample41$3" with its value "1".
					double cv$weightedProbability = (Math.log(cv$probabilitySample41Value4) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, d[1]));
					
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
					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample41Value4);
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
			logProbability$var43 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var49 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample53 = ((fixedFlag$sample53 && fixedFlag$sample41) && fixedFlag$sample44);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var43 = logProbability$var49;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$obs = (logProbability$obs + logProbability$var49);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var49);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var49);
		}
	}

	// Calculate the probability of the samples represented by sample41 using sampled
	// values.
	private final void logProbabilityValue$sample41() {
		// Determine if we need to calculate the values for sample task 41 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample41) {
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
			double cv$distributionAccumulator = (((0.0 <= y) && (y < b.length))?Math.log(b[y]):Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var37 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$y = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample41)
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
			fixedProbFlag$sample41 = fixedFlag$sample41;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var37 = logProbability$y;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$y);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample41)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$y);
		}
	}

	// Calculate the probability of the samples represented by sample44 using sampled
	// values.
	private final void logProbabilityValue$sample44() {
		// Determine if we need to calculate the values for sample task 44 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample44) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(d, a[y]);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var40 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$d = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample44)
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
			fixedProbFlag$sample44 = (fixedFlag$sample44 && fixedFlag$sample41);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var40 = logProbability$d;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$d);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample44)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$d);
		}
	}

	// Calculate the probability of the samples represented by sample53 using sampled
	// values.
	private final void logProbabilityValue$sample53() {
		// Determine if we need to calculate the values for sample task 53 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample53) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(obs[var48], d[y]));
			logProbability$var43 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var49 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample53 = ((fixedFlag$sample53 && fixedFlag$sample41) && fixedFlag$sample44);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var43 = logProbability$var49;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$obs = (logProbability$obs + logProbability$var49);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var49);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var49);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 41 drawn from Categorical 37. Inference was performed using variable
	// marginalization.
	private final void sample41() {
		{
			// Variable declaration of cv$accumulatedProbabilities moved.
			// Declaration comment was:
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$b" with its value "b".
			// 
			// Substituted "cv$valuePos" with its value "0".
			// 
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 44 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$b" with its value "b".
			// 
			// Substituted "cv$valuePos" with its value "0".
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityDirichlet(d, a[0]) + ((0 < b.length)?Math.log(b[0]):Double.NEGATIVE_INFINITY));
			
			// Processing sample task 53 of consumer random variable null.
			for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 53 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$3$var42's comment
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 43.
				// 
				// Value of the variable at this index
				// 
				// Substituted "cv$valuePos" with its value "0".
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var48], d[0]) + cv$accumulatedProbabilities);
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var38$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		
		// Variable declaration of cv$accumulatedProbabilities moved.
		// Declaration comment was:
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$b" with its value "b".
		// 
		// Substituted "cv$valuePos" with its value "1".
		// 
		// A check to ensure rounding of floating point values can never result in a negative
		// value.
		// 
		// Recorded the probability of reaching sample task 44 with the current configuration.
		// 
		// Set an accumulator to record the consumer distributions not seen. Initially set
		// to 1 as seen values will be deducted from this value.
		// 
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$b" with its value "b".
		// 
		// Substituted "cv$valuePos" with its value "1".
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityDirichlet(d, a[1]) + ((1 < b.length)?Math.log(b[1]):Double.NEGATIVE_INFINITY));
		
		// Processing sample task 53 of consumer random variable null.
		for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
			// A check to ensure rounding of floating point values can never result in a negative
			// value.
			// 
			// Recorded the probability of reaching sample task 53 with the current configuration.
			// 
			// Set an accumulator to record the consumer distributions not seen. Initially set
			// to 1 as seen values will be deducted from this value.
			// 
			// Variable declaration of cv$accumulatedConsumerProbabilities moved.
			// Declaration comment was:
			// Set an accumulator to sum the probabilities for each possible configuration of
			// inputs.
			// 
			// cv$temp$3$var42's comment
			// Constructing a random variable input for use later.
			// 
			// Processing random variable 43.
			// 
			// Value of the variable at this index
			// 
			// Substituted "cv$valuePos" with its value "1".
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var48], d[1]) + cv$accumulatedProbabilities);
		
		// Save the calculated index value into the array of index value probabilities
		// 
		// Get a local reference to the scratch space.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		cv$var38$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var38$stateProbabilityGlobal[0];
		
		// Unrolled loop
		// 
		// Get a local reference to the scratch space.
		double cv$lseElementValue = cv$var38$stateProbabilityGlobal[1];
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
			cv$logSum = (Math.log((Math.exp((cv$var38$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var38$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Unrolled loop
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample41[0] = 0.5;
			
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample41[1] = 0.5;
		} else {
			// Unrolled loop
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample41[0] = Math.exp((cv$var38$stateProbabilityGlobal[0] - cv$logSum));
			
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample41[1] = Math.exp((cv$var38$stateProbabilityGlobal[1] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = 2; cv$indexName < cv$var38$stateProbabilityGlobal.length; cv$indexName += 1)
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample41[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 44 drawn from Dirichlet 40. Inference was performed using Metropolis-Hastings.
	private final void sample44() {
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// Calculate the probability of the random variable generating the original sampled
		// value.
		double cv$originalProbability;
		
		// A reference local to the function for the sample variable.
		int cv$arrayLength = d.length;
		
		// Pick a value in the array to adjust.
		int cv$indexToChange = (int)((double)cv$arrayLength * DistributionSampling.sampleUniform(RNG$));
		
		// Pick how much the value should be moved by. Initially this value is proposed as
		// a ratio of the current magnitude of the value, we will check to make sure the adjustment
		// will not make this value too large or other values too small and adjust if required
		// before it is applied.
		double cv$movementRatio = ((DistributionSampling.sampleBeta(RNG$, 5, 5) * 1.9999) - 1);
		
		// Calculate how much we are going to move the array index cv$indexToChange the by.
		// 
		// Allocate space for the proposed change to be stored as an absolute value
		double cv$proposedDifference;
		
		// Test if we are increasing or decreasing the value at the index. For each case calculate
		// the maximum valid adjustment.
		if((cv$movementRatio < 0))
			// The maximum reduction of the array at the index without going below 0 is the value
			// of the array at that index.
			// 
			// A reference local to the function for the sample variable.
			cv$proposedDifference = d[cv$indexToChange];
		else {
			// Calculate the maximum magnitude of the proposed index change.
			// Initially set the maximum to the amount that the value we are changing could increase
			// without exceeding 1
			// 
			// A reference local to the function for the sample variable.
			cv$proposedDifference = (1.0 - d[cv$indexToChange]);
			
			// For the array values up to the index we are going to change calculate the maximum
			// move possible.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1) {
				// Calculate the maximum change value that the value at array index cv$loopIndex could
				// support. Based on moving all other values by an equal amount.
				// 
				// A reference local to the function for the sample variable.
				double cv$temp = (d[cv$loopIndex] * (cv$arrayLength - 1));
				
				// If the maximum move is less than the proposed move update the move size.
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
			
			// For the array values after the index we are going to change calculate the maximum
			// move possible.
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1) {
				// Calculate the maximum change value that the value at array index cv$loopIndex could
				// support. Based on moving all other values by an equal amount.
				// 
				// A reference local to the function for the sample variable.
				double cv$temp = (d[cv$loopIndex] * (cv$arrayLength - 1));
				
				// If this is less than the proposed increase, change the proposed increase to this
				// value.
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
		}
		
		// Multiply the maximum adjustment by the adjustment ratio to get the actual adjustment
		// we are going to make.
		cv$proposedDifference = (cv$movementRatio * cv$proposedDifference);
		
		// Calculate how much each of the other indexes needs to be adjusted by in order to
		// maintain that the sum of the indexes is 1.
		double cv$rebalanceValue = (cv$proposedDifference / (cv$arrayLength - 1));
		{
			// Exploring all the possible distribution values for random variable 40 creating
			// sample task 44.
			// Initialize the summed probabilities to 0 in log space.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Enumerating the possible arguments for Dirichlet 40.
			// 
			// Enumerating the possible arguments for Dirichlet 40.
			if(fixedFlag$sample41) {
				if((0 == y)) {
					// Record the reached probability density.
					// 
					// Initialize a counter to track the reached distributions.
					cv$reachedDistributionSourceRV = 1.0;
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// A reference local to the function for the sample variable.
					// 
					// cv$temp$0$var39's comment
					// Variable declaration of cv$temp$0$var39 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "y" with its value "0".
					double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(d, a[0]);
					
					// Processing random variable 43.
					// 
					// Processing sample task 53 of consumer random variable null.
					for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 53 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// cv$temp$4$var42's comment
						// Variable declaration of cv$temp$4$var42 moved.
						// Declaration comment was:
						// Enumerating the possible arguments for the variable Bernoulli 43 which is consuming
						// the output of Sample task 44.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "y" with its value "0".
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var48], d[0]) + cv$accumulatedProbabilities);
					cv$stateProbabilityValue = cv$accumulatedProbabilities;
				}
				if((1 == y)) {
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// A reference local to the function for the sample variable.
					// 
					// cv$temp$2$var39's comment
					// Variable declaration of cv$temp$2$var39 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "y" with its value "1".
					double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(d, a[1]);
					
					// Processing random variable 43.
					// 
					// Processing sample task 53 of consumer random variable null.
					for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 53 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// cv$temp$7$var42's comment
						// Variable declaration of cv$temp$7$var42 moved.
						// Declaration comment was:
						// Enumerating the possible arguments for the variable Bernoulli 43 which is consuming
						// the output of Sample task 44.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "y" with its value "1".
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var48], d[1]) + cv$accumulatedProbabilities);
					
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
					// Substituted "index$sample41$2" with its value "0".
					double cv$probabilitySample41Value3 = distribution$sample41[0];
					
					// Record the reached probability density.
					// 
					// Initialize a counter to track the reached distributions.
					cv$reachedDistributionSourceRV = cv$probabilitySample41Value3;
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// A reference local to the function for the sample variable.
					// 
					// cv$temp$1$var39's comment
					// Variable declaration of cv$temp$1$var39 moved.
					// 
					// Constructing a random variable input for use later.
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample41Value3) + DistributionSampling.logProbabilityDirichlet(d, a[0]));
					
					// Processing random variable 43.
					// 
					// Processing sample task 53 of consumer random variable null.
					for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 53 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// cv$temp$5$var42's comment
						// Variable declaration of cv$temp$5$var42 moved.
						// Declaration comment was:
						// Enumerating the possible arguments for the variable Bernoulli 43 which is consuming
						// the output of Sample task 44.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var48], d[0]) + cv$accumulatedProbabilities);
					cv$stateProbabilityValue = cv$accumulatedProbabilities;
				}
				
				// Update the probability of sampling this value from the distribution value.
				// 
				// Substituted "index$sample41$11" with its value "1".
				double cv$probabilitySample41Value12 = distribution$sample41[1];
				
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample41Value12);
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				// 
				// A reference local to the function for the sample variable.
				// 
				// cv$temp$3$var39's comment
				// Variable declaration of cv$temp$3$var39 moved.
				// 
				// Constructing a random variable input for use later.
				double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample41Value12) + DistributionSampling.logProbabilityDirichlet(d, a[1]));
				
				// Processing random variable 43.
				// 
				// Processing sample task 53 of consumer random variable null.
				for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 53 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$8$var42's comment
					// Variable declaration of cv$temp$8$var42 moved.
					// Declaration comment was:
					// Enumerating the possible arguments for the variable Bernoulli 43 which is consuming
					// the output of Sample task 44.
					// 
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var48], d[1]) + cv$accumulatedProbabilities);
				
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
			
			// Initialize an accumulator to take the product of all the distribution probabilities
			// in log space.
			cv$originalProbability = (cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV));
		}
		
		// Exploring all the possible distribution values for random variable 40 creating
		// sample task 44.
		// 
		// Initialize the summed probabilities to 0 in log space.
		double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
		
		// Initialize a counter to track the reached distributions.
		double cv$reachedDistributionSourceRV = 0.0;
		
		// Update Sample and intermediate values
		// 
		// Update the sample value
		// 
		// Update all the indexes up to the index selected.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
			// A reference local to the function for the sample variable.
			d[cv$loopIndex] = (d[cv$loopIndex] - cv$rebalanceValue);
		
		// Update the selected index.
		// 
		// A reference local to the function for the sample variable.
		d[cv$indexToChange] = (d[cv$indexToChange] + cv$proposedDifference);
		
		// Update all the indexes after the index we selected.
		for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			// A reference local to the function for the sample variable.
			d[cv$loopIndex] = (d[cv$loopIndex] - cv$rebalanceValue);
		
		// Enumerating the possible arguments for Dirichlet 40.
		// 
		// Enumerating the possible arguments for Dirichlet 40.
		if(fixedFlag$sample41) {
			if((0 == y)) {
				// Record the reached probability density.
				// 
				// Initialize a counter to track the reached distributions.
				cv$reachedDistributionSourceRV = 1.0;
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				// 
				// A reference local to the function for the sample variable.
				// 
				// cv$temp$0$var39's comment
				// Variable declaration of cv$temp$0$var39 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Substituted "y" with its value "0".
				double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(d, a[0]);
				
				// Processing random variable 43.
				// 
				// Processing sample task 53 of consumer random variable null.
				for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 53 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$4$var42's comment
					// Variable declaration of cv$temp$4$var42 moved.
					// Declaration comment was:
					// Enumerating the possible arguments for the variable Bernoulli 43 which is consuming
					// the output of Sample task 44.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "y" with its value "0".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var48], d[0]) + cv$accumulatedProbabilities);
				cv$stateProbabilityValue = cv$accumulatedProbabilities;
			}
			if((1 == y)) {
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				// 
				// A reference local to the function for the sample variable.
				// 
				// cv$temp$2$var39's comment
				// Variable declaration of cv$temp$2$var39 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Substituted "y" with its value "1".
				double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(d, a[1]);
				
				// Processing random variable 43.
				// 
				// Processing sample task 53 of consumer random variable null.
				for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 53 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$7$var42's comment
					// Variable declaration of cv$temp$7$var42 moved.
					// Declaration comment was:
					// Enumerating the possible arguments for the variable Bernoulli 43 which is consuming
					// the output of Sample task 44.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "y" with its value "1".
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var48], d[1]) + cv$accumulatedProbabilities);
				
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
				// Substituted "index$sample41$2" with its value "0".
				double cv$probabilitySample41Value3 = distribution$sample41[0];
				
				// Record the reached probability density.
				// 
				// Initialize a counter to track the reached distributions.
				cv$reachedDistributionSourceRV = cv$probabilitySample41Value3;
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				// 
				// A reference local to the function for the sample variable.
				// 
				// cv$temp$1$var39's comment
				// Variable declaration of cv$temp$1$var39 moved.
				// 
				// Constructing a random variable input for use later.
				double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample41Value3) + DistributionSampling.logProbabilityDirichlet(d, a[0]));
				
				// Processing random variable 43.
				// 
				// Processing sample task 53 of consumer random variable null.
				for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 53 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// cv$temp$5$var42's comment
					// Variable declaration of cv$temp$5$var42 moved.
					// Declaration comment was:
					// Enumerating the possible arguments for the variable Bernoulli 43 which is consuming
					// the output of Sample task 44.
					// 
					// Constructing a random variable input for use later.
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var48], d[0]) + cv$accumulatedProbabilities);
				cv$stateProbabilityValue = cv$accumulatedProbabilities;
			}
			
			// Update the probability of sampling this value from the distribution value.
			// 
			// Substituted "index$sample41$11" with its value "1".
			double cv$probabilitySample41Value12 = distribution$sample41[1];
			
			// Record the reached probability density.
			cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample41Value12);
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// A reference local to the function for the sample variable.
			// 
			// cv$temp$3$var39's comment
			// Variable declaration of cv$temp$3$var39 moved.
			// 
			// Constructing a random variable input for use later.
			double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample41Value12) + DistributionSampling.logProbabilityDirichlet(d, a[1]));
			
			// Processing random variable 43.
			// 
			// Processing sample task 53 of consumer random variable null.
			for(int var48 = 0; var48 < length$obs_measured; var48 += 1)
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 53 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// cv$temp$8$var42's comment
				// Variable declaration of cv$temp$8$var42 moved.
				// Declaration comment was:
				// Enumerating the possible arguments for the variable Bernoulli 43 which is consuming
				// the output of Sample task 44.
				// 
				// Constructing a random variable input for use later.
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var48], d[1]) + cv$accumulatedProbabilities);
			
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
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// Initialize an accumulator to take the product of all the distribution probabilities
		// in log space.
		if(((cv$stateProbabilityValue - (Math.log(cv$reachedDistributionSourceRV) + cv$originalProbability)) <= Math.log(DistributionSampling.sampleUniform(RNG$)))) {
			// If it is not revert the sample value and intermediates to their original values.
			// 
			// Set the sample value
			// 
			// Calculate the new sample value
			// 
			// Update the sample value
			// Update all the indexes up to the index selected.
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
				// A reference local to the function for the sample variable.
				d[cv$loopIndex] = (d[cv$loopIndex] + cv$rebalanceValue);
			
			// Update the selected index.
			// 
			// A reference local to the function for the sample variable.
			d[cv$indexToChange] = (d[cv$indexToChange] - cv$proposedDifference);
			
			// Update all the indexes after the index we selected.
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				// A reference local to the function for the sample variable.
				d[cv$loopIndex] = (d[cv$loopIndex] + cv$rebalanceValue);
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Constructor for cv$var38$stateProbabilityGlobal
		// 
		// Allocate scratch space.
		// 
		// Allocation of cv$var38$stateProbabilityGlobal for single threaded execution
		cv$var38$stateProbabilityGlobal = new double[2];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for a
		a = new double[2][];
		a[0] = new double[2];
		a[1] = new double[3];
		
		// Constructor for b
		b = new double[2];
		
		// If d has not been set already allocate space.
		if(!setFlag$d)
			// Constructor for d
			d = new double[d.length];
		
		// If obs has not been set already allocate space.
		if(!setFlag$obs)
			// Constructor for obs
			obs = new boolean[length$obs_measured];
		
		// Constructor for distribution$sample41
		distribution$sample41 = new double[2];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample41)
			y = DistributionSampling.sampleCategorical(RNG$, b);
		if(!fixedFlag$sample44)
			DistributionSampling.sampleDirichlet(RNG$, a[y], d);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample53)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, length$obs_measured, 1,
				(int forStart$var48, int forEnd$var48, int threadID$var48, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var48 = forStart$var48; var48 < forEnd$var48; var48 += 1)
							obs[var48] = DistributionSampling.sampleBernoulli(RNG$1, d[y]);
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample41) {
			// Save the probability of each value
			// 
			// cv$distribution$sample41's comment
			// Create local copy of variable probabilities.
			// 
			// Probability for this value
			distribution$sample41[0] = ((0 < b.length)?b[0]:0.0);
			
			// Save the probability of each value
			// 
			// cv$distribution$sample41's comment
			// Create local copy of variable probabilities.
			// 
			// Probability for this value
			distribution$sample41[1] = ((1 < b.length)?b[1]:0.0);
		}
		if(!fixedFlag$sample44)
			DistributionSampling.sampleDirichlet(RNG$, a[y], d);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample41)
			y = DistributionSampling.sampleCategorical(RNG$, b);
		if(!fixedFlag$sample44)
			DistributionSampling.sampleDirichlet(RNG$, a[y], d);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample41)
				sample41();
			if(!fixedFlag$sample44)
				sample44();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!fixedFlag$sample44)
				sample44();
			if(!fixedFlag$sample41)
				sample41();
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
		b[0] = 0.35;
		b[1] = 0.65;
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
		logProbability$var37 = 0.0;
		if(!fixedProbFlag$sample41)
			logProbability$y = 0.0;
		logProbability$var40 = 0.0;
		if(!fixedProbFlag$sample44)
			logProbability$d = 0.0;
		logProbability$var43 = 0.0;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$var49 = 0.0;
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
		if(fixedFlag$sample44)
			logProbabilityValue$sample44();
		logProbabilityValue$sample53();
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
		logProbabilityDistribution$sample41();
		logProbabilityDistribution$sample44();
		logProbabilityDistribution$sample53();
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
		logProbabilityValue$sample41();
		logProbabilityValue$sample44();
		logProbabilityValue$sample53();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample41)
			y = DistributionSampling.sampleCategorical(RNG$, b);
		if(!fixedFlag$sample44)
			DistributionSampling.sampleDirichlet(RNG$, a[y], d);
		
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
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2025, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n \n package org.sandwood.compiler.tests.parser;\n\npublic model RaggedArray6(boolean[] obs_measured) {\n    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n    double[] b = { 0.35, 0.65 };\n    int y = categorical(b).sampleDistribution();\n    double[] d = dirichlet(a[y]).sample();\n    boolean[] obs = bernoulli(d[y]).sample(obs_measured.length);\n    obs.observe(obs_measured);\n}";
	}
}