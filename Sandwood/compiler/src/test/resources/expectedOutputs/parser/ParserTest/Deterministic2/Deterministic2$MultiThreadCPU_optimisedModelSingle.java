package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Deterministic2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Deterministic2$CoreInterface {
	
	// Declare the variables for the model.
	private int[] a;
	private int[] b;
	private double[] cv$distributionAccumulator$var55;
	private double[][] cv$var31$countGlobal;
	private double[] cv$var56$stateProbabilityGlobal;
	private double[][] distribution$sample58;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedFlag$sample58 = false;
	private boolean fixedFlag$sample78 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean fixedProbFlag$sample58 = false;
	private boolean fixedProbFlag$sample78 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$a;
	private double logProbability$b;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample58;
	private double logProbability$var19;
	private double logProbability$var31;
	private double logProbability$var55;
	private double logProbability$var75;
	private double logProbability$var76;
	private double[][] m;
	private int n;
	private boolean setFlag$a = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean system$gibbsForward = true;
	private double[] v;

	public Deterministic2$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for a.
	@Override
	public final int[] get$a() {
		return a;
	}

	// Setter for a.
	@Override
	public final void set$a(int[] cv$value) {
		// Set flags for all the side effects of a including if probabilities need to be updated.
		// Set a with flag to mark that it has been set so another array doesn't need to be
		// constructed
		a = cv$value;
		setFlag$a = true;
		
		// Unset the fixed probability flag for sample 58 as it depends on a.
		fixedProbFlag$sample58 = false;
		
		// Unset the fixed probability flag for sample 78 as it depends on a.
		fixedProbFlag$sample78 = false;
	}

	// Getter for b.
	@Override
	public final int[] get$b() {
		return b;
	}

	// Getter for fixedFlag$sample32.
	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	// Setter for fixedFlag$sample32.
	@Override
	public final void set$fixedFlag$sample32(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample32 including if probabilities
		// need to be updated.
		fixedFlag$sample32 = cv$value;
		
		// Should the probability of sample 32 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample32" with its value "cv$value".
		fixedProbFlag$sample32 = (cv$value && fixedProbFlag$sample32);
		
		// Should the probability of sample 58 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample32" with its value "cv$value".
		fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
	}

	// Getter for fixedFlag$sample58.
	@Override
	public final boolean get$fixedFlag$sample58() {
		return fixedFlag$sample58;
	}

	// Setter for fixedFlag$sample58.
	@Override
	public final void set$fixedFlag$sample58(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample58 including if probabilities
		// need to be updated.
		fixedFlag$sample58 = cv$value;
		
		// Should the probability of sample 58 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample58" with its value "cv$value".
		fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
		
		// Should the probability of sample 78 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample58" with its value "cv$value".
		fixedProbFlag$sample78 = (cv$value && fixedProbFlag$sample78);
	}

	// Getter for fixedFlag$sample78.
	@Override
	public final boolean get$fixedFlag$sample78() {
		return fixedFlag$sample78;
	}

	// Setter for fixedFlag$sample78.
	@Override
	public final void set$fixedFlag$sample78(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample78 including if probabilities
		// need to be updated.
		fixedFlag$sample78 = cv$value;
		
		// Should the probability of sample 78 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample78" with its value "cv$value".
		fixedProbFlag$sample78 = (cv$value && fixedProbFlag$sample78);
	}

	// Getter for flips.
	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	// Setter for flips.
	@Override
	public final void set$flips(boolean[] cv$value) {
		// Set flags for all the side effects of flips including if probabilities need to
		// be updated.
		// Set flips with flag to mark that it has been set so another array doesn't need
		// to be constructed
		flips = cv$value;
		setFlag$flips = true;
		
		// Unset the fixed probability flag for sample 78 as it depends on flips.
		fixedProbFlag$sample78 = false;
	}

	// Getter for flipsMeasured.
	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	// Setter for flipsMeasured.
	@Override
	public final void set$flipsMeasured(boolean[] cv$value) {
		// Set flipsMeasured with flag to mark that it has been set so another array doesn't
		// need to be constructed
		flipsMeasured = cv$value;
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

	// Getter for logProbability$a.
	@Override
	public final double get$logProbability$a() {
		return logProbability$a;
	}

	// Getter for logProbability$b.
	@Override
	public final double get$logProbability$b() {
		return logProbability$b;
	}

	// Getter for logProbability$flips.
	@Override
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	// Getter for logProbability$m.
	@Override
	public final double get$logProbability$m() {
		return logProbability$m;
	}

	// Getter for m.
	@Override
	public final double[][] get$m() {
		return m;
	}

	// Setter for m.
	@Override
	public final void set$m(double[][] cv$value) {
		// Set flags for all the side effects of m including if probabilities need to be updated.
		// Set m with flag to mark that it has been set so another array doesn't need to be
		// constructed
		m = cv$value;
		setFlag$m = true;
		
		// Unset the fixed probability flag for sample 32 as it depends on m.
		fixedProbFlag$sample32 = false;
		
		// Unset the fixed probability flag for sample 58 as it depends on m.
		fixedProbFlag$sample58 = false;
	}

	// Getter for n.
	@Override
	public final int get$n() {
		return n;
	}

	// Setter for n.
	@Override
	public final void set$n(int cv$value) {
		n = cv$value;
	}

	// Getter for states.
	@Override
	public final int get$states() {
		return 5;
	}

	// Getter for v.
	@Override
	public final double[] get$v() {
		return v;
	}

	// Calculate the probability of the samples represented by sample58 using probability
	// distributions.
	private final void logProbabilityDistribution$sample58() {
		// Determine if we need to calculate the values for sample task 58 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample58) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample58) {
				// Generating probabilities for sample task
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// The sample value to calculate the probability of generating
					int cv$sampleValue = a[i$var48];
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == i$var48)) {
						// Enumerating the possible arguments for Categorical 55.
						double[] var54 = m[0];
						
						// Store the value of the function call, so the function call is only made once.
						cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var54.length))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY);
						
						// Add the probability of this distribution configuration to the accumulator.
						// 
						// An accumulator for the distributed probability space covered.
						cv$probabilityReached = 1.0;
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Substituted "index$i$6_1" with its value "(i$var48 - 1)".
					if((2 <= i$var48)) {
						// Substituted "index$i$6_2" with its value "i$var48".
						int traceTempVariable$var53$6_3 = a[(i$var48 - 1)];
						
						// Substituted "index$i$6_2" with its value "i$var48".
						if(((0 <= traceTempVariable$var53$6_3) && (traceTempVariable$var53$6_3 < 5))) {
							double[] var54 = m[traceTempVariable$var53$6_3];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var54.length))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY);
							
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
					if((cv$probabilityReached == 0.0))
						// Return negative infinity if no distribution probability space is reached.
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						// Scale the probability relative to the observed distribution space.
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
					
					// Store the sample task probability
					logProbability$sample58[(i$var48 - 1)] = cv$distributionAccumulator;
					
					// Guard to ensure that b is only updated once for this probability.
					if((i$var48 < (n - 1)))
						// Update the variable probability
						logProbability$b = (logProbability$b + cv$distributionAccumulator);
				}
				logProbability$var55 = cv$sampleAccumulator;
				
				// Update the variable probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$a = (logProbability$a + cv$sampleAccumulator);
				
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
				// Substituted "fixedFlag$sample58" with its value "true".
				fixedProbFlag$sample58 = fixedFlag$sample32;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				double cv$sampleValue = logProbability$sample58[(i$var48 - 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((fixedFlag$sample58 && (i$var48 < (n - 1))))
					// Update the variable probability
					logProbability$b = (logProbability$b + cv$sampleValue);
			}
			logProbability$var55 = cv$rvAccumulator;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample58)
				// Update the variable probability
				logProbability$a = (logProbability$a + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample78 using probability
	// distributions.
	private final void logProbabilityDistribution$sample78() {
		// Determine if we need to calculate the values for sample task 78 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample78) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 78 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				boolean cv$sampleValue = flips[j];
				
				// Enumerating the possible arguments for Bernoulli 75.
				if(fixedFlag$sample58) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((j < (n - 1))) {
						// Store the value of the function call, so the function call is only made once.
						cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, (1 / a[(j + 1)]));
						
						// Add the probability of this distribution configuration to the accumulator.
						// 
						// An accumulator for the distributed probability space covered.
						cv$probabilityReached = 1.0;
					}
				} else {
					int i$var48 = (j + 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((i$var48 < n)) {
						// Enumerating the possible outputs of Categorical 55.
						for(int index$sample58$5 = 0; index$sample58$5 < 5; index$sample58$5 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample58Value6 = distribution$sample58[(i$var48 - 1)][index$sample58$5];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample58Value6) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, (1 / index$sample58$5)));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample58Value6);
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
			logProbability$var75 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var76 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample78 = (fixedFlag$sample78 && fixedFlag$sample58);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var75 = logProbability$var76;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$flips = (logProbability$flips + logProbability$var76);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var76);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var76);
		}
	}

	// Calculate the probability of the samples represented by sample32 using sampled
	// values.
	private final void logProbabilityValue$sample32() {
		// Determine if we need to calculate the values for sample task 32 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample32) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var30 = 0; var30 < 5; var30 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var30], v));
			logProbability$var19 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var31 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample32)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample32 = fixedFlag$sample32;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var19 = logProbability$var31;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var31);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var31);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample32)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var31);
		}
	}

	// Calculate the probability of the samples represented by sample58 using sampled
	// values.
	private final void logProbabilityValue$sample58() {
		// Determine if we need to calculate the values for sample task 58 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample58) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = a[i$var48];
				double[] var54 = m[b[i$var48]];
				
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
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var54.length))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample58[(i$var48 - 1)] = cv$distributionAccumulator;
				
				// Guard to ensure that b is only updated once for this probability.
				if((i$var48 < (n - 1)))
					// Update the variable probability
					logProbability$b = (logProbability$b + cv$distributionAccumulator);
			}
			logProbability$var55 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$a = (logProbability$a + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample58)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample58 = (fixedFlag$sample58 && fixedFlag$sample32);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				double cv$sampleValue = logProbability$sample58[(i$var48 - 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Guard to ensure that b is only updated once for this probability.
				if((i$var48 < (n - 1)))
					// Update the variable probability
					logProbability$b = (logProbability$b + cv$sampleValue);
			}
			logProbability$var55 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$a = (logProbability$a + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample78 using sampled
	// values.
	private final void logProbabilityValue$sample78() {
		// Determine if we need to calculate the values for sample task 78 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample78) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int j = 0; j < n; j += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[j], (1 / a[(j + 1)])));
			logProbability$var75 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var76 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample78 = (fixedFlag$sample78 && fixedFlag$sample58);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var75 = logProbability$var76;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$flips = (logProbability$flips + logProbability$var76);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var76);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var76);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 32 drawn from Dirichlet 19. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample32(int var30, int threadID$cv$var30, Rng RNG$) {
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var31$countGlobal[threadID$cv$var30];
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample58) {
			// Processing random variable 55.
			// 
			// Looking for a path between Sample 32 and consumer Categorical 55.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((1 < n) && (var30 == 0)))
				// Processing sample task 58 of consumer random variable null.
				// 
				// Increment the sample counter with the value sampled by sample task 58 of random
				// variable var55
				// 
				// Substituted "i$var48" with its value "1".
				cv$countLocal[a[1]] = (cv$countLocal[a[1]] + 1.0);
			for(int i$var48 = 2; i$var48 < n; i$var48 += 1) {
				if((var30 == a[(i$var48 - 1)]))
					// Processing sample task 58 of consumer random variable null.
					// 
					// Increment the sample counter with the value sampled by sample task 58 of random
					// variable var55
					cv$countLocal[a[i$var48]] = (cv$countLocal[a[i$var48]] + 1.0);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			// Processing random variable 55.
			// 
			// Looking for a path between Sample 32 and consumer Categorical 55.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((1 < n) && (var30 == 0))) {
				// Merge the distribution probabilities into the count
				// 
				// Get the length of the array
				for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample58[0][cv$loopIndex]);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var30 < 5)) {
				for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
					int index$i$27 = (i$var48 - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 <= index$i$27)) {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
						// cv$probabilitySample58Value29's comment
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample58$28" with its value "var30".
						double cv$distributionProbability = distribution$sample58[(index$i$27 - 1)][var30];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample58[(i$var48 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var30]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 58 drawn from Categorical 55. Inference was performed using variable
	// marginalization.
	private final void sample58(int i$var48) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((1 == i$var48))
			// variable marginalization
			cv$noStates = 5;
		if(fixedFlag$sample58) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((2 <= i$var48)) {
				// Substituted "index$i$5_2" with its value "i$var48".
				int traceTempVariable$var53$5_3 = a[(i$var48 - 1)];
				
				// Substituted "index$i$5_2" with its value "i$var48".
				if(((0 <= traceTempVariable$var53$5_3) && (traceTempVariable$var53$5_3 < 5)))
					// variable marginalization
					cv$noStates = 5;
			}
		} else {
			int index$i$6 = (i$var48 - 1);
			
			// index$i$1's comment
			// Exploring all the possible state counts for random variable 55.
			// 
			// Copy of index so that its values can be safely substituted
			// 
			// Substituted "index$i$6" with its value "(i$var48 - 1)".
			// 
			// Substituted "index$i$6" with its value "(i$var48 - 1)".
			// 
			// Substituted "index$i$6" with its value "(i$var48 - 1)".
			// 
			// Substituted "index$i$6" with its value "(i$var48 - 1)".
			if(((1 <= index$i$6) && !(index$i$6 == i$var48)))
				// variable marginalization
				cv$noStates = 5;
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 55 creating
			// sample task 58.
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == i$var48)) {
				// Record the reached probability density.
				// 
				// Initialize a counter to track the reached distributions.
				cv$reachedDistributionSourceRV = 1.0;
				
				// Variable declaration of cv$temp$0$var54 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Enumerating the possible arguments for Categorical 55.
				double[] cv$temp$0$var54 = m[0];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 78 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				// 
				// Value of the variable at this index
				cv$stateProbabilityValue = (DistributionSampling.logProbabilityBernoulli(flips[0], (1 / cv$valuePos)) + ((cv$valuePos < cv$temp$0$var54.length)?Math.log(cv$temp$0$var54[cv$valuePos]):Double.NEGATIVE_INFINITY));
			}
			int index$i$18 = (i$var48 - 1);
			
			// index$i$14's comment
			// Copy of index so that its values can be safely substituted
			// 
			// Substituted "index$i$18" with its value "(i$var48 - 1)".
			// 
			// Substituted "index$i$18" with its value "(i$var48 - 1)".
			// 
			// Substituted "index$i$18" with its value "(i$var48 - 1)".
			// 
			// Substituted "index$i$18" with its value "(i$var48 - 1)".
			if(((1 <= index$i$18) && !(index$i$18 == i$var48))) {
				// Enumerating the possible outputs of Categorical 55.
				for(int index$sample58$19 = 0; index$sample58$19 < 5; index$sample58$19 += 1) {
					// Update the probability of sampling this value from the distribution value.
					double cv$probabilitySample58Value20 = distribution$sample58[(index$i$18 - 1)][index$sample58$19];
					
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample58Value20);
					
					// Constructing a random variable input for use later.
					double[] var54 = m[index$sample58$19];
					
					// Variable declaration of cv$accumulatedProbabilities moved.
					// Declaration comment was:
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$temp$2$var54" with its value "var54".
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 78 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$temp$2$var54" with its value "var54".
					double cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityBernoulli(flips[(i$var48 - 1)], (1 / cv$valuePos)) + Math.log(cv$probabilitySample58Value20)) + ((cv$valuePos < var54.length)?Math.log(var54[cv$valuePos]):Double.NEGATIVE_INFINITY));
					
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
			}
			int index$i$37_2 = (i$var48 + 1);
			if((index$i$37_2 < n)) {
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < 5; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					cv$distributionAccumulator$var55[cv$i] = 0.0;
				
				// Declare and zero an accumulator for tracking the reached source probability space.
				double scopeVariable$reachedSourceProbability = 0.0;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == i$var48))
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					scopeVariable$reachedSourceProbability = 1.0;
				int index$i$44 = (i$var48 - 1);
				
				// index$i$14's comment
				// Copy of index so that its values can be safely substituted
				// 
				// index$i$39's comment
				// Processing sample task 58 of consumer random variable null.
				// 
				// Copy of index so that its values can be safely substituted
				// 
				// Substituted "index$i$37_4" with its value "index$i$37_2".
				// 
				// Substituted "index$i$37_2" with its value "(i$var48 + 1)".
				// 
				// Substituted "index$i$37_2" with its value "(i$var48 + 1)".
				// 
				// Substituted "index$i$37_2" with its value "(i$var48 + 1)".
				// 
				// Substituted "index$i$37_2" with its value "(i$var48 + 1)".
				// 
				// Substituted "index$i$37_2" with its value "(i$var48 + 1)".
				if((((1 <= index$i$44) && !(index$i$44 == i$var48)) && !(index$i$44 == index$i$37_2))) {
					// Enumerating the possible outputs of Categorical 55.
					for(int index$sample58$45 = 0; index$sample58$45 < 5; index$sample58$45 += 1)
						// Add the probability of this argument configuration.
						// 
						// cv$probabilitySample58Value46's comment
						// Update the probability of sampling this value from the distribution value.
						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample58[(index$i$44 - 1)][index$sample58$45]);
				}
				
				// Add the current distribution to the distribution accumulator.
				// 
				// The probability of reaching the consumer with this set of consumer arguments
				// 
				// Substituted "cv$temp$6$var54" with its value "var54".
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 55.
				// 
				// Looking for a path between Sample 58 and consumer Categorical 55.
				// 
				// Value of the variable at this index
				DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var55, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "index$i$37_4" with its value "index$i$37_2".
				double[] cv$sampleDistribution = distribution$sample58[(index$i$37_2 - 1)];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < 5; cv$i += 1) {
					// Normalise the values in the calculated distribution
					// 
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					// 
					// Record the reached distribution.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Zero an accumulator to track the probabilities reached.
					double cv$normalisedDistValue = (cv$distributionAccumulator$var55[cv$i] / scopeVariable$reachedSourceProbability);
					
					// Corresponding value from the sample distribution
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					
					// Calculate the overlap and store the result
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					
					// Calculate the overlap and store the result
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				
				// Scale and add the result to the combined results so far. A min is taken over the
				// reached distributions so that rounding cannot result in a value greater than one
				// as for a small probability this could give a negative value
				// 
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				// 
				// Record the reached distribution.
				// 
				// The probability of reaching the consumer with this set of consumer arguments
				// 
				// Zero an accumulator to track the probabilities reached.
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * scopeVariable$reachedSourceProbability) + 1.0) - Math.min(scopeVariable$reachedSourceProbability, 1.0)));
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			cv$var56$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample58[(i$var48 - 1)];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var56$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var56$stateProbabilityGlobal[cv$lseIndex];
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
				cv$lseSum = (cv$lseSum + Math.exp((cv$var56$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
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
				cv$localProbability[cv$indexName] = Math.exp((cv$var56$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var56$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Allocation of cv$var31$countGlobal for multithreaded execution
		// 
		// Get the thread count.
		int cv$threadCount = threadCount();
		
		// Allocate an array to hold a copy per thread
		cv$var31$countGlobal = new double[cv$threadCount][];
		
		// Populate the array with a copy per thread
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var31$countGlobal[cv$index] = new double[5];
		
		// Constructor for cv$distributionAccumulator$var55
		// 
		// Allocation of cv$distributionAccumulator$var55 for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 56. Initially set to the value
		// of putTask 33.
		cv$distributionAccumulator$var55 = new double[5];
		
		// Allocation of cv$var56$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 56. Initially set to the value
		// of putTask 33.
		cv$var56$stateProbabilityGlobal = new double[5];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for v
		v = new double[5];
		
		// If m has not been set already allocate space.
		if(!setFlag$m) {
			// Constructor for m
			m = new double[5][];
			for(int var30 = 0; var30 < 5; var30 += 1)
				m[var30] = new double[5];
		}
		
		// If a has not been set already allocate space.
		if(!setFlag$a)
			// Constructor for a
			a = new int[n];
		
		// Constructor for b
		b = new int[n];
		
		// If flips has not been set already allocate space.
		if(!setFlag$flips)
			// Constructor for flips
			flips = new boolean[n];
		
		// Constructor for distribution$sample58
		distribution$sample58 = new double[(n - 1)][];
		for(int i$var48 = 1; i$var48 < n; i$var48 += 1)
			distribution$sample58[(i$var48 - 1)] = new double[5];
		
		// Constructor for logProbability$sample58
		logProbability$sample58 = new double[(n - 1)];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample32)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var30, int forEnd$var30, int threadID$var30, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var30 = forStart$var30; var30 < forEnd$var30; var30 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var30]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample58) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				b[i$var48] = a[(i$var48 - 1)];
				a[i$var48] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var48]]);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample78)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, n, 1,
				(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j = forStart$j; j < forEnd$j; j += 1)
							flips[j] = DistributionSampling.sampleBernoulli(RNG$1, (1 / a[(j + 1)]));
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample32)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var30, int forEnd$var30, int threadID$var30, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var30 = forStart$var30; var30 < forEnd$var30; var30 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var30]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample58) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				// Create local copy of variable probabilities.
				double[] cv$distribution$sample58 = distribution$sample58[(i$var48 - 1)];
				for(int index$var55 = 0; index$var55 < 5; index$var55 += 1)
					// Zero the probability of each value
					cv$distribution$sample58[index$var55] = 0.0;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == i$var48)) {
					// Iterate through possible values for var55's arguments.
					// 
					// Enumerating the possible arguments for Categorical 55.
					double[] var54 = m[0];
					for(int index$var55 = 0; index$var55 < 5; index$var55 += 1)
						// Save the probability of each value
						cv$distribution$sample58[index$var55] = (cv$distribution$sample58[index$var55] + ((index$var55 < var54.length)?var54[index$var55]:0.0));
				}
				int index$i$4 = (i$var48 - 1);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 <= index$i$4)) {
					// Enumerating the possible outputs of Categorical 55.
					for(int index$sample58$5 = 0; index$sample58$5 < 5; index$sample58$5 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample58Value6 = distribution$sample58[(index$i$4 - 1)][index$sample58$5];
						double[] var54 = m[index$sample58$5];
						for(int index$var55 = 0; index$var55 < 5; index$var55 += 1)
							// Save the probability of each value
							cv$distribution$sample58[index$var55] = (cv$distribution$sample58[index$var55] + (cv$probabilitySample58Value6 * ((index$var55 < var54.length)?var54[index$var55]:0.0)));
					}
				}
				
				// Sum the values in the array
				double cv$var55$sum = 0.0;
				for(int index$var55 = 0; index$var55 < 5; index$var55 += 1)
					// sum the probability of each value
					cv$var55$sum = (cv$var55$sum + cv$distribution$sample58[index$var55]);
				for(int index$var55 = 0; index$var55 < 5; index$var55 += 1)
					// Normalise the probability of each value
					cv$distribution$sample58[index$var55] = (cv$distribution$sample58[index$var55] / cv$var55$sum);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample32)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var30, int forEnd$var30, int threadID$var30, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var30 = forStart$var30; var30 < forEnd$var30; var30 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var30]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample58) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				b[i$var48] = a[(i$var48 - 1)];
				a[i$var48] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var48]]);
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample32)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var30, int forEnd$var30, int threadID$var30, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var30 = forStart$var30; var30 < forEnd$var30; var30 += 1)
								sample32(var30, threadID$var30, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample58) {
				for(int i$var48 = 1; i$var48 < n; i$var48 += 1)
					sample58(i$var48);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample58) {
				for(int i$var48 = (n - 1); i$var48 >= 1; i$var48 -= 1)
					sample58(i$var48);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample32)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var30, int forEnd$var30, int threadID$var30, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var30 = forStart$var30; var30 < forEnd$var30; var30 += 1)
								sample32(var30, threadID$var30, RNG$1);
					}
				);

		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var16, int forEnd$i$var16, int threadID$i$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var16 = forStart$i$var16; i$var16 < forEnd$i$var16; i$var16 += 1)
						v[i$var16] = 0.1;
			}
		);
		a[0] = 0;
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
		logProbability$var19 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample32)
			logProbability$var31 = 0.0;
		logProbability$var55 = 0.0;
		logProbability$b = 0.0;
		logProbability$a = 0.0;
		if(!fixedProbFlag$sample58) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1)
				logProbability$sample58[(i$var48 - 1)] = 0.0;
		}
		logProbability$var75 = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample78)
			logProbability$var76 = 0.0;
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
		if(fixedFlag$sample32)
			logProbabilityValue$sample32();
		logProbabilityValue$sample78();
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
		// 
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using values only.
		logProbabilityValue$sample32();
		logProbabilityDistribution$sample58();
		logProbabilityDistribution$sample78();
	}

	// Method to calculate the probabilities of all the samples in the model including
	// those generating fixed data. In the process probabilities for all the random variables
	// and for the model as a whole will be calculated. This model only uses values.
	@Override
	public final void logModelProbabilitiesVal() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using distributions where
		// appropriate.
		// 
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using values only.
		logProbabilityValue$sample32();
		logProbabilityValue$sample58();
		logProbabilityValue$sample78();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample32)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var30, int forEnd$var30, int threadID$var30, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var30 = forStart$var30; var30 < forEnd$var30; var30 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var30]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample58) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				b[i$var48] = a[(i$var48 - 1)];
				a[i$var48] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var48]]);
			}
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
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = flipsMeasured[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(setFlag$a) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1)
				b[i$var48] = a[(i$var48 - 1)];
		}
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
		     + "/**\n"
		     + " * A model for the fairness work.\n"
		     + " */\n"
		     + "public model Deterministic2(int n, boolean[] flipsMeasured) {\n"
		     + "    int states = 5;\n"
		     + "\n"
		     + "    double[] v = new double[states];\n"
		     + "    for(int i:[0..states))\n"
		     + "        v[i] = 0.1;\n"
		     + "    \n"
		     + "    double[][] m = dirichlet(v).sample(states);\n"
		     + "\n"
		     + "    int[] a = new int[n];\n"
		     + "    int[] b = new int[n];\n"
		     + "    a[0] = 0;\n"
		     + "    for(int i=1; i<n; i++) {\n"
		     + "        b[i] = a[i-1];\n"
		     + "        a[i] = categorical(m[b[i]]).sampleDistribution();\n"
		     + "    }\n"
		     + "    \n"
		     + "    boolean[] flips = new boolean[n];\n"
		     + "            \n"
		     + "    for(int j:[0..n))\n"
		     + "        flips[j] = bernoulli(1/a[j+1]).sample();\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}";
	}
}