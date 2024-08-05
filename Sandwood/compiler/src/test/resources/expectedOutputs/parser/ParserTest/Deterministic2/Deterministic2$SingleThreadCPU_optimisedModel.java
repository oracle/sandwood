package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Deterministic2$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Deterministic2$CoreInterface {
	
	// Declare the variables for the model.
	private int[] a;
	private int[] b;
	private double[] cv$distributionAccumulator$var33;
	private double[] cv$var17$countGlobal;
	private double[] cv$var34$stateProbabilityGlobal;
	private double[][] distribution$sample35;
	private boolean fixedFlag$sample18 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample48 = false;
	private boolean fixedProbFlag$sample18 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample48 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$a;
	private double logProbability$b;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample35;
	private double[] logProbability$sample48;
	private double logProbability$var12;
	private double logProbability$var17;
	private double[] logProbability$var33;
	private double[] logProbability$var46;
	private double[][] m;
	private int n;
	private boolean setFlag$a = false;
	private boolean setFlag$b = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean system$gibbsForward = true;
	private double[] v;

	public Deterministic2$SingleThreadCPU(ExecutionTarget target) {
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
		// Set a with flag to mark that it has been set so another array doesn't need to be
		// constructed
		a = cv$value;
		setFlag$a = true;
	}

	// Getter for b.
	@Override
	public final int[] get$b() {
		return b;
	}

	// Setter for b.
	@Override
	public final void set$b(int[] cv$value) {
		// Set b with flag to mark that it has been set so another array doesn't need to be
		// constructed
		b = cv$value;
		setFlag$b = true;
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
		
		// Should the probability of sample 35 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample18" with its value "cv$value".
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
		
		// Should the probability of sample 48 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample35" with its value "cv$value".
		fixedProbFlag$sample48 = (cv$value && fixedProbFlag$sample48);
	}

	// Getter for fixedFlag$sample48.
	@Override
	public final boolean get$fixedFlag$sample48() {
		return fixedFlag$sample48;
	}

	// Setter for fixedFlag$sample48.
	@Override
	public final void set$fixedFlag$sample48(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample48 including if probabilities
		// need to be updated.
		fixedFlag$sample48 = cv$value;
		
		// Should the probability of sample 48 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample48" with its value "cv$value".
		fixedProbFlag$sample48 = (cv$value && fixedProbFlag$sample48);
	}

	// Getter for flips.
	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	// Setter for flips.
	@Override
	public final void set$flips(boolean[] cv$value) {
		// Set flips with flag to mark that it has been set so another array doesn't need
		// to be constructed
		flips = cv$value;
		setFlag$flips = true;
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
		// Set m with flag to mark that it has been set so another array doesn't need to be
		// constructed
		m = cv$value;
		setFlag$m = true;
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

	// Calculate the probability of the samples represented by sample35 using probability
	// distributions.
	private final void logProbabilityDistribution$sample35() {
		// Determine if we need to calculate the values for sample task 35 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample35) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample35) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// The sample value to calculate the probability of generating
					int cv$sampleValue = a[i$var26];
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 == i$var26)) {
						// Substituted "i$var26" with its value "1".
						int var16 = b[1];
						if(((0 <= var16) && (var16 < 5))) {
							// Store the value of the function call, so the function call is only made once.
							// 
							// Substituted "i$var26" with its value "1".
							cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(cv$sampleValue, m[b[1]]);
							
							// Add the probability of this distribution configuration to the accumulator.
							// 
							// An accumulator for the distributed probability space covered.
							cv$probabilityReached = 1.0;
						}
					}
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Substituted "index$i$6_1" with its value "(i$var26 - 1)".
					if((2 <= i$var26)) {
						int var16 = b[i$var26];
						if(((0 <= var16) && (var16 < 5))) {
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = DistributionSampling.logProbabilityCategorical(cv$sampleValue, m[b[i$var26]]);
							
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
					logProbability$var33[(i$var26 - 1)] = cv$distributionAccumulator;
					
					// Store the sample task probability
					logProbability$sample35[(i$var26 - 1)] = cv$distributionAccumulator;
					
					// Guard to ensure that b is only updated once for this probability.
					if((i$var26 < (n - 1)))
						// Update the variable probability
						logProbability$b = (logProbability$b + cv$distributionAccumulator);
				}
				
				// Update the variable probability
				logProbability$a = (logProbability$a + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample35" with its value "true".
				fixedProbFlag$sample35 = fixedFlag$sample18;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				double cv$sampleValue = logProbability$sample35[(i$var26 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var33[(i$var26 - 1)] = cv$sampleValue;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((fixedFlag$sample35 && (i$var26 < (n - 1))))
					// Update the variable probability
					logProbability$b = (logProbability$b + cv$sampleValue);
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample35)
				// Update the variable probability
				logProbability$a = (logProbability$a + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample48 using probability
	// distributions.
	private final void logProbabilityDistribution$sample48() {
		// Determine if we need to calculate the values for sample task 48 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample48) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 48 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				boolean cv$sampleValue = flips[j];
				
				// Enumerating the possible arguments for Bernoulli 46.
				if(fixedFlag$sample35) {
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
					int i$var26 = (j + 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((i$var26 < n)) {
						// Enumerating the possible outputs of Categorical 33.
						for(int index$sample35$5 = 0; index$sample35$5 < 5; index$sample35$5 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample35Value6 = distribution$sample35[(i$var26 - 1)][index$sample35$5];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample35Value6) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, (1 / index$sample35$5)));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value6);
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
				logProbability$var46[j] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample48[j] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample48 = (fixedFlag$sample48 && fixedFlag$sample35);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample48[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var46[j] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample18 using sampled
	// values.
	private final void logProbabilityValue$sample18() {
		// Determine if we need to calculate the values for sample task 18 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample18) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var16 = 0; var16 < 5; var16 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var16], v));
			logProbability$var12 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var17 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample18)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample18 = fixedFlag$sample18;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var12 = logProbability$var17;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var17);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var17);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample18)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var17);
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
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(a[i$var26], m[b[i$var26]]);
				
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
				logProbability$var33[(i$var26 - 1)] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample35[(i$var26 - 1)] = cv$distributionAccumulator;
				
				// Guard to ensure that b is only updated once for this probability.
				if((i$var26 < (n - 1)))
					// Update the variable probability
					logProbability$b = (logProbability$b + cv$distributionAccumulator);
			}
			
			// Update the variable probability
			logProbability$a = (logProbability$a + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedFlag$sample18);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				double cv$sampleValue = logProbability$sample35[(i$var26 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var33[(i$var26 - 1)] = cv$sampleValue;
				
				// Guard to ensure that b is only updated once for this probability.
				if((i$var26 < (n - 1)))
					// Update the variable probability
					logProbability$b = (logProbability$b + cv$sampleValue);
			}
			
			// Update the variable probability
			logProbability$a = (logProbability$a + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample48 using sampled
	// values.
	private final void logProbabilityValue$sample48() {
		// Determine if we need to calculate the values for sample task 48 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample48) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[j], (1 / a[(j + 1)]));
				
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
				logProbability$var46[j] = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$sample48[j] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample48 = (fixedFlag$sample48 && fixedFlag$sample35);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				// Variable declaration of cv$rvAccumulator moved.
				double cv$rvAccumulator = logProbability$sample48[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var46[j] = cv$rvAccumulator;
			}
			
			// Update the variable probability
			logProbability$flips = (logProbability$flips + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 18 drawn from Dirichlet 12. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample18(int var16) {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var17$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample35) {
			// Processing random variable 33.
			// 
			// Looking for a path between Sample 18 and consumer Categorical 33.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((1 < n) && (var16 == b[1])))
				// Processing sample task 35 of consumer random variable null.
				// 
				// Increment the sample counter with the value sampled by sample task 35 of random
				// variable var33
				// 
				// A local reference to the scratch space.
				// 
				// Substituted "i$var26" with its value "1".
				cv$var17$countGlobal[a[1]] = (cv$var17$countGlobal[a[1]] + 1.0);
			for(int i$var26 = 2; i$var26 < n; i$var26 += 1) {
				if((var16 == b[i$var26]))
					// Processing sample task 35 of consumer random variable null.
					// 
					// Increment the sample counter with the value sampled by sample task 35 of random
					// variable var33
					// 
					// A local reference to the scratch space.
					cv$var17$countGlobal[a[i$var26]] = (cv$var17$countGlobal[a[i$var26]] + 1.0);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			// Processing random variable 33.
			// 
			// Looking for a path between Sample 18 and consumer Categorical 33.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((1 < n) && (var16 == b[1]))) {
				// Merge the distribution probabilities into the count
				// 
				// Get the length of the array
				for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
					// A local reference to the scratch space.
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					cv$var17$countGlobal[cv$loopIndex] = (cv$var17$countGlobal[cv$loopIndex] + distribution$sample35[0][cv$loopIndex]);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var16 < 5)) {
				for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
					int index$i$27 = (i$var26 - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 <= index$i$27)) {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
						// cv$probabilitySample35Value29's comment
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample35$28" with its value "var16".
						double cv$distributionProbability = distribution$sample35[(index$i$27 - 1)][var16];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
							// A local reference to the scratch space.
							cv$var17$countGlobal[cv$loopIndex] = (cv$var17$countGlobal[cv$loopIndex] + (distribution$sample35[(i$var26 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var17$countGlobal, m[var16]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 35 drawn from Categorical 33. Inference was performed using variable
	// marginalization.
	private final void sample35(int i$var26) {
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 33 creating
			// sample task 35.
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 == i$var26)) {
				// Substituted "i$var26" with its value "1".
				int var16 = b[1];
				if(((0 <= var16) && (var16 < 5))) {
					// Record the reached probability density.
					// 
					// Initialize a counter to track the reached distributions.
					cv$reachedDistributionSourceRV = 1.0;
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 48 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					// 
					// cv$temp$0$var32's comment
					// Variable declaration of cv$temp$0$var32 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Substituted "i$var26" with its value "1".
					cv$stateProbabilityValue = (DistributionSampling.logProbabilityBernoulli(flips[0], (1 / cv$valuePos)) + DistributionSampling.logProbabilityCategorical(cv$valuePos, m[b[1]]));
				}
			}
			int index$i$5 = (i$var26 - 1);
			
			// index$i$1's comment
			// Copy of index so that its values can be safely substituted
			// 
			// Substituted "index$i$5" with its value "(i$var26 - 1)".
			// 
			// Substituted "index$i$5" with its value "(i$var26 - 1)".
			// 
			// Substituted "index$i$5" with its value "(i$var26 - 1)".
			// 
			// Substituted "index$i$5" with its value "(i$var26 - 1)".
			if(((1 <= index$i$5) && !(index$i$5 == i$var26))) {
				// Enumerating the possible outputs of Categorical 33.
				for(int index$sample35$6 = 0; index$sample35$6 < 5; index$sample35$6 += 1) {
					// Update the probability of sampling this value from the distribution value.
					double cv$probabilitySample35Value7 = distribution$sample35[(index$i$5 - 1)][index$sample35$6];
					
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample35Value7);
					
					// Variable declaration of cv$accumulatedProbabilities moved.
					// Declaration comment was:
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$temp$2$var32" with its value "var32".
					// 
					// Constructing a random variable input for use later.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 48 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 48 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "j" with its value "(i$var26 - 1)".
					// 
					// cv$temp$5$var45's comment
					// Variable declaration of cv$temp$5$var45 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Processing random variable 46.
					// 
					// Looking for a path between Sample 35 and consumer Bernoulli 46.
					// 
					// Value of the variable at this index
					// 
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					// 
					// Substituted "cv$temp$2$var32" with its value "var32".
					// 
					// Constructing a random variable input for use later.
					double cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityBernoulli(flips[(i$var26 - 1)], (1 / cv$valuePos)) + Math.log(cv$probabilitySample35Value7)) + DistributionSampling.logProbabilityCategorical(cv$valuePos, m[index$sample35$6]));
					
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
			int index$i$24_2 = (i$var26 + 1);
			if((index$i$24_2 < n)) {
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < 5; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					cv$distributionAccumulator$var33[cv$i] = 0.0;
				
				// Declare and zero an accumulator for tracking the reached source probability space.
				double scopeVariable$reachedSourceProbability = 0.0;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == i$var26)) {
					// Substituted "i$var26" with its value "1".
					int index$var16$29_1 = b[1];
					if(((0 <= index$var16$29_1) && (index$var16$29_1 < 5)))
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						scopeVariable$reachedSourceProbability = 1.0;
				}
				int index$i$31 = (i$var26 - 1);
				
				// index$i$1's comment
				// Copy of index so that its values can be safely substituted
				// 
				// index$i$26's comment
				// Processing sample task 35 of consumer random variable null.
				// 
				// Copy of index so that its values can be safely substituted
				// 
				// Substituted "index$i$24_4" with its value "index$i$24_2".
				// 
				// Substituted "index$i$24_2" with its value "(i$var26 + 1)".
				// 
				// Substituted "index$i$24_2" with its value "(i$var26 + 1)".
				// 
				// Substituted "index$i$24_2" with its value "(i$var26 + 1)".
				// 
				// Substituted "index$i$24_2" with its value "(i$var26 + 1)".
				// 
				// Substituted "index$i$24_2" with its value "(i$var26 + 1)".
				if((((1 <= index$i$31) && !(index$i$31 == i$var26)) && !(index$i$31 == index$i$24_2))) {
					// Enumerating the possible outputs of Categorical 33.
					for(int index$sample35$32 = 0; index$sample35$32 < 5; index$sample35$32 += 1)
						// Add the probability of this argument configuration.
						// 
						// cv$probabilitySample35Value33's comment
						// Update the probability of sampling this value from the distribution value.
						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample35[(index$i$31 - 1)][index$sample35$32]);
				}
				
				// Add the current distribution to the distribution accumulator.
				// 
				// The probability of reaching the consumer with this set of consumer arguments
				// 
				// Substituted "cv$temp$6$var32" with its value "var32".
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 33.
				// 
				// Looking for a path between Sample 35 and consumer Categorical 33.
				// 
				// Value of the variable at this index
				DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var33, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "index$i$24_4" with its value "index$i$24_2".
				double[] cv$sampleDistribution = distribution$sample35[(index$i$24_2 - 1)];
				
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
					double cv$normalisedDistValue = (cv$distributionAccumulator$var33[cv$i] / scopeVariable$reachedSourceProbability);
					
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
			cv$var34$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample35[(i$var26 - 1)];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var34$stateProbabilityGlobal[0];
		
		// Find max value.
		// 
		// Get a local reference to the scratch space.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var34$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var34$stateProbabilityGlobal[cv$lseIndex];
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
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var34$stateProbabilityGlobal.length; cv$lseIndex += 1)
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var34$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
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
			for(int cv$indexName = 0; cv$indexName < cv$var34$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$localProbability[cv$indexName] = (1.0 / cv$var34$stateProbabilityGlobal.length);
		} else {
			// Normalize log space values and move to normal space
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = 0; cv$indexName < cv$var34$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$localProbability[cv$indexName] = Math.exp((cv$var34$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Allocation of cv$var17$countGlobal for single threaded execution
		cv$var17$countGlobal = new double[5];
		
		// Constructor for cv$distributionAccumulator$var33
		// 
		// Allocation of cv$distributionAccumulator$var33 for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 33. Initially set to the value
		// of putTask 19.
		cv$distributionAccumulator$var33 = new double[5];
		
		// Allocation of cv$var34$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 33. Initially set to the value
		// of putTask 19.
		cv$var34$stateProbabilityGlobal = new double[5];
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
			for(int var16 = 0; var16 < 5; var16 += 1)
				m[var16] = new double[5];
		}
		
		// If a has not been set already allocate space.
		if(!setFlag$a)
			// Constructor for a
			a = new int[n];
		
		// If b has not been set already allocate space.
		if(!setFlag$b)
			// Constructor for b
			b = new int[n];
		
		// If flips has not been set already allocate space.
		if(!setFlag$flips)
			// Constructor for flips
			flips = new boolean[n];
		
		// Constructor for distribution$sample35
		distribution$sample35 = new double[(n - 1)][];
		for(int i$var26 = 1; i$var26 < n; i$var26 += 1)
			distribution$sample35[(i$var26 - 1)] = new double[5];
		
		// Constructor for logProbability$var33
		logProbability$var33 = new double[(n - 1)];
		
		// Constructor for logProbability$sample35
		logProbability$sample35 = new double[(n - 1)];
		
		// Constructor for logProbability$var46
		logProbability$var46 = new double[n];
		
		// Constructor for logProbability$sample48
		logProbability$sample48 = new double[n];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample18) {
			for(int var16 = 0; var16 < 5; var16 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var16]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample35) {
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				b[i$var26] = a[(i$var26 - 1)];
				a[i$var26] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var26]]);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample48) {
			for(int j = 0; j < n; j += 1)
				flips[j] = DistributionSampling.sampleBernoulli(RNG$, (1 / a[(j + 1)]));
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample18) {
			for(int var16 = 0; var16 < 5; var16 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var16]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample35) {
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				// Create local copy of variable probabilities.
				double[] cv$distribution$sample35 = distribution$sample35[(i$var26 - 1)];
				for(int index$var33 = 0; index$var33 < 5; index$var33 += 1)
					// Zero the probability of each value
					cv$distribution$sample35[index$var33] = 0.0;
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 == i$var26)) {
					// Substituted "i$var26" with its value "1".
					int var16 = b[1];
					if(((0 <= var16) && (var16 < 5))) {
						// Substituted "i$var26" with its value "1".
						double[] var32 = m[b[1]];
						for(int index$var33 = 0; index$var33 < 5; index$var33 += 1)
							// Save the probability of each value
							cv$distribution$sample35[index$var33] = (cv$distribution$sample35[index$var33] + DistributionSampling.probabilityCategorical(index$var33, var32));
					}
				}
				int index$i$4 = (i$var26 - 1);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 <= index$i$4)) {
					// Enumerating the possible outputs of Categorical 33.
					for(int index$sample35$5 = 0; index$sample35$5 < 5; index$sample35$5 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample35Value6 = distribution$sample35[(index$i$4 - 1)][index$sample35$5];
						double[] var32 = m[index$sample35$5];
						for(int index$var33 = 0; index$var33 < 5; index$var33 += 1)
							// Save the probability of each value
							cv$distribution$sample35[index$var33] = (cv$distribution$sample35[index$var33] + (cv$probabilitySample35Value6 * DistributionSampling.probabilityCategorical(index$var33, var32)));
					}
				}
				
				// Sum the values in the array
				double cv$var33$sum = 0.0;
				for(int index$var33 = 0; index$var33 < 5; index$var33 += 1)
					// sum the probability of each value
					cv$var33$sum = (cv$var33$sum + cv$distribution$sample35[index$var33]);
				for(int index$var33 = 0; index$var33 < 5; index$var33 += 1)
					// Normalise the probability of each value
					cv$distribution$sample35[index$var33] = (cv$distribution$sample35[index$var33] / cv$var33$sum);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample18) {
			for(int var16 = 0; var16 < 5; var16 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var16]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample35) {
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				b[i$var26] = a[(i$var26 - 1)];
				a[i$var26] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var26]]);
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample18) {
				for(int var16 = 0; var16 < 5; var16 += 1)
					sample18(var16);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample35) {
				for(int i$var26 = 1; i$var26 < n; i$var26 += 1)
					sample35(i$var26);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample35) {
				for(int i$var26 = (n - 1); i$var26 >= 1; i$var26 -= 1)
					sample35(i$var26);
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample18) {
				for(int var16 = 4; var16 >= 0; var16 -= 1)
					sample18(var16);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		for(int i$var9 = 0; i$var9 < 5; i$var9 += 1)
			v[i$var9] = 0.1;
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
		logProbability$var12 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample18)
			logProbability$var17 = 0.0;
		for(int i$var26 = 1; i$var26 < n; i$var26 += 1)
			logProbability$var33[(i$var26 - 1)] = 0.0;
		logProbability$a = 0.0;
		logProbability$b = 0.0;
		if(!fixedProbFlag$sample35) {
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1)
				logProbability$sample35[(i$var26 - 1)] = 0.0;
		}
		for(int j = 0; j < n; j += 1)
			logProbability$var46[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample48) {
			for(int j = 0; j < n; j += 1)
				logProbability$sample48[j] = 0.0;
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
		if(fixedFlag$sample18)
			logProbabilityValue$sample18();
		logProbabilityValue$sample48();
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
		logProbabilityValue$sample18();
		logProbabilityDistribution$sample35();
		logProbabilityDistribution$sample48();
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
		logProbabilityValue$sample18();
		logProbabilityValue$sample35();
		logProbabilityValue$sample48();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample18) {
			for(int var16 = 0; var16 < 5; var16 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var16]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample35) {
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				b[i$var26] = a[(i$var26 - 1)];
				a[i$var26] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var26]]);
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
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1)
				b[i$var26] = a[(i$var26 - 1)];
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\n/**\n * A model for the fairness work.\n */\npublic model Deterministic2(int n, boolean[] flipsMeasured) {\n    int states = 5;\n\n    double[] v = new double[states];\n    for(int i:[0..states))\n        v[i] = 0.1;\n    \n    double[][] m = dirichlet(v).sample(states);\n\n    int[] a = new int[n];\n    int[] b = new int[n];\n    a[0] = 0;\n    for(int i=1; i<n; i++) {\n        b[i] = a[i-1];\n        a[i] = categorical(m[b[i]]).sampleDistribution();\n    }\n    \n    boolean[] flips = new boolean[n];\n            \n    for(int j:[0..n))\n        flips[j] = bernoulli(1/a[j+1]).sample();\n        flips.observe(flipsMeasured);\n}";
	}
}