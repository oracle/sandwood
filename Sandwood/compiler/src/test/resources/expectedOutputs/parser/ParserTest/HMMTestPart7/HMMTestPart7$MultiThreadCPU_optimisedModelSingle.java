package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart7$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart7$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private double[] cv$distributionAccumulator$var71;
	private double[][] cv$var30$countGlobal;
	private double[] cv$var54$stateProbabilityGlobal;
	private double[] cv$var72$stateProbabilityGlobal;
	private double[] distribution$sample57;
	private double[][] distribution$sample75;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample48 = false;
	private boolean fixedFlag$sample57 = false;
	private boolean fixedFlag$sample75 = false;
	private boolean fixedFlag$sample91 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample48 = false;
	private boolean fixedProbFlag$sample57 = false;
	private boolean fixedProbFlag$sample75 = false;
	private boolean fixedProbFlag$sample91 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double logProbability$st;
	private double logProbability$var18;
	private double logProbability$var30;
	private double logProbability$var34;
	private double logProbability$var46;
	private double logProbability$var53;
	private double logProbability$var54;
	private double logProbability$var71;
	private double logProbability$var72;
	private double logProbability$var87;
	private double logProbability$var88;
	private double[][] m;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart7$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for bias.
	@Override
	public final double[] get$bias() {
		return bias;
	}

	// Setter for bias.
	@Override
	public final void set$bias(double[] cv$value) {
		// Set flags for all the side effects of bias including if probabilities need to be
		// updated.
		// Set bias with flag to mark that it has been set so another array doesn't need to
		// be constructed
		bias = cv$value;
		setFlag$bias = true;
		
		// Unset the fixed probability flag for sample 48 as it depends on bias.
		fixedProbFlag$sample48 = false;
		
		// Unset the fixed probability flag for sample 91 as it depends on bias.
		fixedProbFlag$sample91 = false;
	}

	// Getter for fixedFlag$sample31.
	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	// Setter for fixedFlag$sample31.
	@Override
	public final void set$fixedFlag$sample31(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample31 including if probabilities
		// need to be updated.
		fixedFlag$sample31 = cv$value;
		
		// Should the probability of sample 31 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample31" with its value "cv$value".
		fixedProbFlag$sample31 = (cv$value && fixedProbFlag$sample31);
		
		// Should the probability of sample 57 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample31" with its value "cv$value".
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		
		// Should the probability of sample 75 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample31" with its value "cv$value".
		fixedProbFlag$sample75 = (cv$value && fixedProbFlag$sample75);
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
		
		// Should the probability of sample 91 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample48" with its value "cv$value".
		fixedProbFlag$sample91 = (cv$value && fixedProbFlag$sample91);
	}

	// Getter for fixedFlag$sample57.
	@Override
	public final boolean get$fixedFlag$sample57() {
		return fixedFlag$sample57;
	}

	// Setter for fixedFlag$sample57.
	@Override
	public final void set$fixedFlag$sample57(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample57 including if probabilities
		// need to be updated.
		fixedFlag$sample57 = cv$value;
		
		// Should the probability of sample 57 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		
		// Should the probability of sample 75 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample75 = (cv$value && fixedProbFlag$sample75);
		
		// Should the probability of sample 91 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample91 = (cv$value && fixedProbFlag$sample91);
	}

	// Getter for fixedFlag$sample75.
	@Override
	public final boolean get$fixedFlag$sample75() {
		return fixedFlag$sample75;
	}

	// Setter for fixedFlag$sample75.
	@Override
	public final void set$fixedFlag$sample75(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample75 including if probabilities
		// need to be updated.
		fixedFlag$sample75 = cv$value;
		
		// Should the probability of sample 75 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample75" with its value "cv$value".
		fixedProbFlag$sample75 = (cv$value && fixedProbFlag$sample75);
		
		// Should the probability of sample 91 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample75" with its value "cv$value".
		fixedProbFlag$sample91 = (cv$value && fixedProbFlag$sample91);
	}

	// Getter for fixedFlag$sample91.
	@Override
	public final boolean get$fixedFlag$sample91() {
		return fixedFlag$sample91;
	}

	// Setter for fixedFlag$sample91.
	@Override
	public final void set$fixedFlag$sample91(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample91 including if probabilities
		// need to be updated.
		fixedFlag$sample91 = cv$value;
		
		// Should the probability of sample 91 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample91" with its value "cv$value".
		fixedProbFlag$sample91 = (cv$value && fixedProbFlag$sample91);
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
		
		// Unset the fixed probability flag for sample 91 as it depends on flips.
		fixedProbFlag$sample91 = false;
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

	// Getter for length$flipsMeasured.
	@Override
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	// Setter for length$flipsMeasured.
	@Override
	public final void set$length$flipsMeasured(int cv$value) {
		length$flipsMeasured = cv$value;
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

	// Getter for logProbability$bias.
	@Override
	public final double get$logProbability$bias() {
		return logProbability$bias;
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

	// Getter for logProbability$st.
	@Override
	public final double get$logProbability$st() {
		return logProbability$st;
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
		
		// Unset the fixed probability flag for sample 31 as it depends on m.
		fixedProbFlag$sample31 = false;
		
		// Unset the fixed probability flag for sample 57 as it depends on m.
		fixedProbFlag$sample57 = false;
		
		// Unset the fixed probability flag for sample 75 as it depends on m.
		fixedProbFlag$sample75 = false;
	}

	// Getter for samples.
	@Override
	public final int get$samples() {
		return samples;
	}

	// Getter for st.
	@Override
	public final int[] get$st() {
		return st;
	}

	// Setter for st.
	@Override
	public final void set$st(int[] cv$value) {
		// Set flags for all the side effects of st including if probabilities need to be
		// updated.
		// Set st with flag to mark that it has been set so another array doesn't need to
		// be constructed
		st = cv$value;
		setFlag$st = true;
		
		// Unset the fixed probability flag for sample 57 as it depends on st.
		fixedProbFlag$sample57 = false;
		
		// Unset the fixed probability flag for sample 75 as it depends on st.
		fixedProbFlag$sample75 = false;
		
		// Unset the fixed probability flag for sample 91 as it depends on st.
		fixedProbFlag$sample91 = false;
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

	// Calculate the probability of the samples represented by sample57 using probability
	// distributions.
	private final void logProbabilityDistribution$sample57() {
		// Determine if we need to calculate the values for sample task 57 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample57) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample57) {
				// Generating probabilities for sample task
				// The sample value to calculate the probability of generating
				int cv$sampleValue = st[0];
				double[] var52 = m[0];
				
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
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var52.length))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				logProbability$var53 = cv$distributionAccumulator;
				
				// Store the sample task probability
				logProbability$var54 = cv$distributionAccumulator;
				
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
				logProbability$st = (logProbability$st + cv$distributionAccumulator);
				
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
				// Substituted "fixedFlag$sample57" with its value "true".
				fixedProbFlag$sample57 = fixedFlag$sample31;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var53 = logProbability$var54;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample57)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$st = (logProbability$st + logProbability$var54);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var54);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample57)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var54);
		}
	}

	// Calculate the probability of the samples represented by sample75 using probability
	// distributions.
	private final void logProbabilityDistribution$sample75() {
		// Determine if we need to calculate the values for sample task 75 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample75) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample75) {
				// Generating probabilities for sample task
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[i$var66];
					
					// Enumerating the possible arguments for Categorical 71.
					if((1 == i$var66)) {
						// Enumerating the possible arguments for Categorical 71.
						if(fixedFlag$sample57) {
							int var29 = st[0];
							
							// Substituted "i$var66" with its value "1".
							if(((0 <= var29) && (var29 < 5))) {
								// Substituted "i$var66" with its value "1".
								double[] var70 = m[st[0]];
								
								// Store the value of the function call, so the function call is only made once.
								cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var70.length))?Math.log(var70[cv$sampleValue]):Double.NEGATIVE_INFINITY);
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						} else {
							// Enumerating the possible outputs of Categorical 53.
							for(int index$sample57$4 = 0; index$sample57$4 < 5; index$sample57$4 += 1) {
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample57Value5 = distribution$sample57[index$sample57$4];
								double[] var70 = m[index$sample57$4];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample57Value5) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var70.length))?Math.log(var70[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value5);
							}
						}
					}
					
					// Substituted "index$i$11_1" with its value "(i$var66 - 1)".
					if((2 <= i$var66)) {
						int var29 = st[(i$var66 - 1)];
						if(((0 <= var29) && (var29 < 5))) {
							double[] var70 = m[st[(i$var66 - 1)]];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var70.length))?Math.log(var70[cv$sampleValue]):Double.NEGATIVE_INFINITY);
							
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
				}
				logProbability$var71 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var72 = cv$sampleAccumulator;
				
				// Update the variable probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$st = (logProbability$st + cv$sampleAccumulator);
				
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
				// Substituted "fixedFlag$sample75" with its value "true".
				fixedProbFlag$sample75 = (fixedFlag$sample31 && fixedFlag$sample57);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var71 = logProbability$var72;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample75)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$st = (logProbability$st + logProbability$var72);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var72);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample75)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var72);
		}
	}

	// Calculate the probability of the samples represented by sample91 using probability
	// distributions.
	private final void logProbabilityDistribution$sample91() {
		// Determine if we need to calculate the values for sample task 91 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample91) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 91 including any distribution
				// values.
				// 
				// The sample value to calculate the probability of generating
				boolean cv$sampleValue = flips[j];
				
				// Enumerating the possible arguments for Bernoulli 87.
				if((0 == j)) {
					// Enumerating the possible arguments for Bernoulli 87.
					if(fixedFlag$sample57) {
						int var45 = st[0];
						
						// Substituted "j" with its value "0".
						if(((0 <= var45) && (var45 < 5))) {
							// Store the value of the function call, so the function call is only made once.
							// 
							// Substituted "j" with its value "0".
							cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, bias[st[0]]);
							
							// Add the probability of this distribution configuration to the accumulator.
							// 
							// An accumulator for the distributed probability space covered.
							cv$probabilityReached = 1.0;
						}
					} else {
						// Enumerating the possible outputs of Categorical 53.
						for(int index$sample57$3 = 0; index$sample57$3 < 5; index$sample57$3 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample57Value4 = distribution$sample57[index$sample57$3];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample57Value4) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, bias[index$sample57$3]));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value4);
						}
					}
				}
				
				// Enumerating the possible arguments for Bernoulli 87.
				if((1 <= j)) {
					// Enumerating the possible arguments for Bernoulli 87.
					if(fixedFlag$sample75) {
						int var45 = st[j];
						if(((0 <= var45) && (var45 < 5))) {
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, bias[st[j]]);
							
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
						// Enumerating the possible outputs of Categorical 71.
						for(int index$sample75$11 = 0; index$sample75$11 < 5; index$sample75$11 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "i$var66" with its value "j".
							double cv$probabilitySample75Value12 = distribution$sample75[(j - 1)][index$sample75$11];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample75Value12) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, bias[index$sample75$11]));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample75Value12);
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
			logProbability$var87 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var88 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample91 = (((fixedFlag$sample91 && fixedFlag$sample48) && fixedFlag$sample57) && fixedFlag$sample75);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var87 = logProbability$var88;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$flips = (logProbability$flips + logProbability$var88);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var88);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var88);
		}
	}

	// Calculate the probability of the samples represented by sample31 using sampled
	// values.
	private final void logProbabilityValue$sample31() {
		// Determine if we need to calculate the values for sample task 31 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample31) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var29 = 0; var29 < 5; var29 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var29], v));
			logProbability$var18 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var30 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample31)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample31 = fixedFlag$sample31;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var18 = logProbability$var30;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var30);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var30);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample31)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var30);
		}
	}

	// Calculate the probability of the samples represented by sample48 using sampled
	// values.
	private final void logProbabilityValue$sample48() {
		// Determine if we need to calculate the values for sample task 48 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample48) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var45 = 0; var45 < 5; var45 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(bias[var45], 1.0, 1.0));
			logProbability$var34 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var46 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample48)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample48 = fixedFlag$sample48;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var34 = logProbability$var46;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$bias = (logProbability$bias + logProbability$var46);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var46);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample48)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var46);
		}
	}

	// Calculate the probability of the samples represented by sample57 using sampled
	// values.
	private final void logProbabilityValue$sample57() {
		// Determine if we need to calculate the values for sample task 57 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample57) {
			// Generating probabilities for sample task
			// The sample value to calculate the probability of generating
			int cv$sampleValue = st[0];
			double[] var52 = m[0];
			
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
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var52.length))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var53 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$var54 = cv$distributionAccumulator;
			
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
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			
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
			if(fixedFlag$sample57)
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
			fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedFlag$sample31);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var53 = logProbability$var54;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var54);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var54);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample57)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var54);
		}
	}

	// Calculate the probability of the samples represented by sample75 using sampled
	// values.
	private final void logProbabilityValue$sample75() {
		// Determine if we need to calculate the values for sample task 75 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample75) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = st[i$var66];
				double[] var70 = m[st[(i$var66 - 1)]];
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var70.length))?Math.log(var70[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			logProbability$var71 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var72 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$st = (logProbability$st + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample75)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample75 = ((fixedFlag$sample75 && fixedFlag$sample31) && fixedFlag$sample57);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var71 = logProbability$var72;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var72);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var72);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample75)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var72);
		}
	}

	// Calculate the probability of the samples represented by sample91 using sampled
	// values.
	private final void logProbabilityValue$sample91() {
		// Determine if we need to calculate the values for sample task 91 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample91) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int j = 0; j < samples; j += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[j], bias[st[j]]));
			logProbability$var87 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var88 = cv$sampleAccumulator;
			
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
			fixedProbFlag$sample91 = (((fixedFlag$sample91 && fixedFlag$sample48) && fixedFlag$sample57) && fixedFlag$sample75);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var87 = logProbability$var88;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$flips = (logProbability$flips + logProbability$var88);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var88);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var88);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 31 drawn from Dirichlet 18. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample31(int var29, int threadID$cv$var29, Rng RNG$) {
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var30$countGlobal[threadID$cv$var29];
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(((var29 == 0) && fixedFlag$sample57))
			// Looking for a path between Sample 31 and consumer Categorical 53.
			// 
			// Processing sample task 57 of consumer random variable null.
			// 
			// Increment the sample counter with the value sampled by sample task 57 of random
			// variable var53
			cv$countLocal[st[0]] = (cv$countLocal[st[0]] + 1.0);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample75) {
			// Processing random variable 71.
			// 
			// Looking for a path between Sample 31 and consumer Categorical 71.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 < samples)) {
				if(fixedFlag$sample57) {
					if((var29 == st[0]))
						// Processing sample task 75 of consumer random variable null.
						// 
						// Increment the sample counter with the value sampled by sample task 75 of random
						// variable var71
						// 
						// Substituted "i$var66" with its value "1".
						cv$countLocal[st[1]] = (cv$countLocal[st[1]] + 1.0);
				} else {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var29 < 5))
						// Processing sample task 75 of consumer random variable null.
						// 
						// Increment the sample counter with the value sampled by sample task 75 of random
						// variable var71
						// 
						// cv$probabilitySample57Value7's comment
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample57$6" with its value "var29".
						cv$countLocal[st[1]] = (cv$countLocal[st[1]] + distribution$sample57[var29]);
				}
			}
			for(int i$var66 = 2; i$var66 < samples; i$var66 += 1) {
				if((var29 == st[(i$var66 - 1)]))
					// Processing sample task 75 of consumer random variable null.
					// 
					// Increment the sample counter with the value sampled by sample task 75 of random
					// variable var71
					cv$countLocal[st[i$var66]] = (cv$countLocal[st[i$var66]] + 1.0);
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(((var29 == 0) && !fixedFlag$sample57)) {
			// Looking for a path between Sample 31 and consumer Categorical 53.
			// 
			// Processing sample task 57 of consumer random variable null.
			// 
			// Merge the distribution probabilities into the count
			// 
			// Get the length of the array
			for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
				// The probability of reaching the consumer with this set of consumer arguments
				// 
				// Add the probability of this argument configuration.
				// 
				// Declare and zero an accumulator for tracking the reached source probability space.
				cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample57[cv$loopIndex]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample75) {
			// Processing random variable 71.
			// 
			// Looking for a path between Sample 31 and consumer Categorical 71.
			// 
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 < samples)) {
				if(fixedFlag$sample57) {
					if((var29 == st[0])) {
						// Processing sample task 75 of consumer random variable null.
						// 
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
							// The probability of reaching the consumer with this set of consumer arguments
							// 
							// Add the probability of this argument configuration.
							// 
							// Declare and zero an accumulator for tracking the reached source probability space.
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample75[0][cv$loopIndex]);
					}
				} else {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var29 < 5)) {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
						// cv$probabilitySample57Value38's comment
						// Update the probability of sampling this value from the distribution value.
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						// 
						// Substituted "index$sample57$37" with its value "var29".
						double cv$distributionProbability = distribution$sample57[var29];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
							// Substituted "i$var66" with its value "1".
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample75[0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((var29 < 5)) {
				for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
					int index$i$45 = (i$var66 - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((1 <= index$i$45)) {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
						// cv$probabilitySample75Value47's comment
						// Update the probability of sampling this value from the distribution value.
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						// 
						// Substituted "index$sample75$46" with its value "var29".
						double cv$distributionProbability = distribution$sample75[(index$i$45 - 1)][var29];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample75[(i$var66 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var29]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 48 drawn from Beta 34. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample48(int var45, int threadID$cv$var45, Rng RNG$) {
		// Local variable to record the number of true samples.
		double cv$sum = 0.0;
		
		// Local variable to record the number of samples.
		double cv$count = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < samples)) {
			if(fixedFlag$sample57) {
				if((var45 == st[0])) {
					// Processing sample task 91 of consumer random variable null.
					// 
					// Include the value sampled by task 91 from random variable var87.
					// Increment the number of samples.
					// 
					// Local variable to record the number of samples.
					cv$count = 1.0;
					
					// If the sample value was positive increase the count
					// 
					// Substituted "j" with its value "0".
					if(flips[0])
						// Local variable to record the number of true samples.
						cv$sum = 1.0;
				}
			} else {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var45 < 5)) {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample57$3" with its value "var45".
					double cv$probabilitySample57Value4 = distribution$sample57[var45];
					
					// Processing sample task 91 of consumer random variable null.
					// 
					// Include the value sampled by task 91 from random variable var87.
					// 
					// Increment the number of samples.
					// 
					// Local variable to record the number of samples.
					cv$count = cv$probabilitySample57Value4;
					
					// If the sample value was positive increase the count
					// 
					// Substituted "j" with its value "0".
					if(flips[0])
						// Local variable to record the number of true samples.
						cv$sum = cv$probabilitySample57Value4;
				}
			}
		}
		for(int j = 1; j < samples; j += 1) {
			if(fixedFlag$sample75) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var45 == st[j])) {
					// Processing sample task 91 of consumer random variable null.
					// 
					// Include the value sampled by task 91 from random variable var87.
					// 
					// Increment the number of samples.
					cv$count = (cv$count + 1.0);
					
					// If the sample value was positive increase the count
					if(flips[j])
						cv$sum = (cv$sum + 1.0);
				}
			} else {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((var45 < 5)) {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "i$var66" with its value "j".
					// 
					// Substituted "index$sample75$12" with its value "var45".
					double cv$probabilitySample75Value13 = distribution$sample75[(j - 1)][var45];
					
					// Processing sample task 91 of consumer random variable null.
					// 
					// Include the value sampled by task 91 from random variable var87.
					// 
					// Increment the number of samples.
					cv$count = (cv$count + cv$probabilitySample75Value13);
					
					// If the sample value was positive increase the count
					if(flips[j])
						cv$sum = (cv$sum + cv$probabilitySample75Value13);
				}
			}
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		bias[var45] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 57 drawn from Categorical 53. Inference was performed using variable
	// marginalization.
	private final void sample57() {
		// cv$noStates's comment
		// variable marginalization
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Variable declaration of cv$temp$0$var52 moved.
			// 
			// Constructing a random variable input for use later.
			double[] cv$temp$0$var52 = m[0];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Value of the variable at this index
			double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var52.length)?Math.log(cv$temp$0$var52[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample75 && (1 < samples))) {
				// Looking for a path between Sample 57 and consumer Categorical 71.
				// Variable declaration of cv$temp$1$var70 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				double[] cv$temp$1$var70 = m[cv$valuePos];
				
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 75 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Processing sample task 75 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "i$var66" with its value "1".
				cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < cv$temp$1$var70.length))?Math.log(cv$temp$1$var70[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			
			// Substituted "j" with its value "0".
			if((0 < samples))
				// Processing sample task 91 of consumer random variable null.
				// 
				// A check to ensure rounding of floating point values can never result in a negative
				// value.
				// 
				// Recorded the probability of reaching sample task 91 with the current configuration.
				// 
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				// 
				// Variable declaration of cv$accumulatedConsumerProbabilities moved.
				// Declaration comment was:
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				// 
				// Substituted "j" with its value "0".
				// 
				// cv$temp$2$var86's comment
				// Variable declaration of cv$temp$2$var86 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 87.
				// 
				// Looking for a path between Sample 57 and consumer Bernoulli 87.
				// 
				// Value of the variable at this index
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0], bias[cv$valuePos]) + cv$accumulatedProbabilities);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!fixedFlag$sample75 && (1 < samples))) {
				// Looking for a path between Sample 57 and consumer Categorical 71.
				// Processing sample task 75 of consumer random variable null.
				// 
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < 5; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					cv$distributionAccumulator$var71[cv$i] = 0.0;
				
				// Add the current distribution to the distribution accumulator.
				// 
				// The probability of reaching the consumer with this set of consumer arguments
				// 
				// Add the probability of this argument configuration.
				// 
				// Declare and zero an accumulator for tracking the reached source probability space.
				// 
				// cv$temp$3$var70's comment
				// Variable declaration of cv$temp$3$var70 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Value of the variable at this index
				DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var71, 1.0, m[cv$valuePos]);
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "i$var66" with its value "1".
				double[] cv$sampleDistribution = distribution$sample75[0];
				
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
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					double cv$normalisedDistValue = cv$distributionAccumulator$var71[cv$i];
					
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
				// Add the probability of this argument configuration.
				// 
				// Declare and zero an accumulator for tracking the reached source probability space.
				cv$accumulatedDistributionProbabilities = Math.log(cv$overlap);
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var54$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
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
		double cv$lseMax = cv$var54$stateProbabilityGlobal[0];
		
		// Unrolled loop
		{
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var54$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		{
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var54$stateProbabilityGlobal[2];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		{
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var54$stateProbabilityGlobal[3];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		
		// Get a local reference to the scratch space.
		double cv$lseElementValue = cv$var54$stateProbabilityGlobal[4];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		
		// If the maximum value is -infinity return -infinity.
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		
		// Sum the values in the array.
		else {
			// Initialise the sum of the array elements
			double cv$lseSum = 0.0;
			
			// Offset values, move to normal space, and sum.
			// 
			// cv$noStates's comment
			// variable marginalization
			for(int cv$lseIndex = 0; cv$lseIndex < 5; cv$lseIndex += 1)
				// Get a local reference to the scratch space.
				cv$lseSum = (cv$lseSum + Math.exp((cv$var54$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			// 
			// cv$noStates's comment
			// variable marginalization
			for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
				// Set the calculated probabilities to be the distribution values, and normalize
				// 
				// Local copy of the probability array
				distribution$sample57[cv$indexName] = 0.2;
		} else {
			// Normalize log space values and move to normal space
			// 
			// cv$noStates's comment
			// variable marginalization
			for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
				// Set the calculated probabilities to be the distribution values, and normalize
				// 
				// Local copy of the probability array
				distribution$sample57[cv$indexName] = Math.exp((cv$var54$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = 5; cv$indexName < cv$var54$stateProbabilityGlobal.length; cv$indexName += 1)
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			distribution$sample57[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 75 drawn from Categorical 71. Inference was performed using variable
	// marginalization.
	private final void sample75(int i$var66) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		
		// Enumerating the possible arguments for Categorical 71.
		if((1 == i$var66)) {
			// Enumerating the possible arguments for Categorical 71.
			if(fixedFlag$sample57) {
				int var29 = st[0];
				
				// Substituted "i$var66" with its value "1".
				if(((0 <= var29) && (var29 < 5)))
					// variable marginalization
					cv$noStates = 5;
			} else
				// variable marginalization
				cv$noStates = 5;
		}
		if(fixedFlag$sample75) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((2 <= i$var66)) {
				int var29 = st[(i$var66 - 1)];
				if(((0 <= var29) && (var29 < 5)))
					// variable marginalization
					cv$noStates = 5;
			}
		} else {
			int index$i$11 = (i$var66 - 1);
			
			// index$i$1's comment
			// Exploring all the possible state counts for random variable 71.
			// 
			// Copy of index so that its values can be safely substituted
			// 
			// Substituted "index$i$11" with its value "(i$var66 - 1)".
			// 
			// Substituted "index$i$11" with its value "(i$var66 - 1)".
			// 
			// Substituted "index$i$11" with its value "(i$var66 - 1)".
			// 
			// Substituted "index$i$11" with its value "(i$var66 - 1)".
			// 
			// Substituted "index$i$11" with its value "(i$var66 - 1)".
			// 
			// Substituted "index$i$11" with its value "(i$var66 - 1)".
			// 
			// Substituted "index$i$11" with its value "(i$var66 - 1)".
			if(((1 <= index$i$11) && !(index$i$11 == i$var66)))
				// variable marginalization
				cv$noStates = 5;
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 71 creating
			// sample task 75.
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Enumerating the possible arguments for Categorical 71.
			if((1 == i$var66)) {
				// Enumerating the possible arguments for Categorical 71.
				if(fixedFlag$sample57) {
					int var29 = st[0];
					
					// Substituted "i$var66" with its value "1".
					if(((0 <= var29) && (var29 < 5))) {
						// Record the reached probability density.
						// 
						// Initialize a counter to track the reached distributions.
						cv$reachedDistributionSourceRV = 1.0;
						
						// Variable declaration of cv$temp$0$var70 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "i$var66" with its value "1".
						double[] cv$temp$0$var70 = m[st[0]];
						
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 91 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						cv$stateProbabilityValue = (DistributionSampling.logProbabilityBernoulli(flips[1], bias[cv$valuePos]) + ((cv$valuePos < cv$temp$0$var70.length)?Math.log(cv$temp$0$var70[cv$valuePos]):Double.NEGATIVE_INFINITY));
					}
				} else {
					// Enumerating the possible outputs of Categorical 53.
					for(int index$sample57$21 = 0; index$sample57$21 < 5; index$sample57$21 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample57Value22 = distribution$sample57[index$sample57$21];
						
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value22);
						
						// Variable declaration of cv$temp$1$var70 moved.
						// 
						// Constructing a random variable input for use later.
						double[] cv$temp$1$var70 = m[index$sample57$21];
						
						// Variable declaration of cv$accumulatedProbabilities moved.
						// Declaration comment was:
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 91 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						double cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityBernoulli(flips[1], bias[cv$valuePos]) + Math.log(cv$probabilitySample57Value22)) + ((cv$valuePos < cv$temp$1$var70.length)?Math.log(cv$temp$1$var70[cv$valuePos]):Double.NEGATIVE_INFINITY));
						
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
			}
			int index$i$28 = (i$var66 - 1);
			
			// index$i$19's comment
			// Copy of index so that its values can be safely substituted
			// 
			// Substituted "index$i$28" with its value "(i$var66 - 1)".
			// 
			// Substituted "index$i$28" with its value "(i$var66 - 1)".
			// 
			// Substituted "index$i$28" with its value "(i$var66 - 1)".
			// 
			// Substituted "index$i$28" with its value "(i$var66 - 1)".
			// 
			// Substituted "index$i$28" with its value "(i$var66 - 1)".
			// 
			// Substituted "index$i$28" with its value "(i$var66 - 1)".
			// 
			// Substituted "index$i$28" with its value "(i$var66 - 1)".
			if(((1 <= index$i$28) && !(index$i$28 == i$var66))) {
				// Enumerating the possible outputs of Categorical 71.
				for(int index$sample75$29 = 0; index$sample75$29 < 5; index$sample75$29 += 1) {
					// Update the probability of sampling this value from the distribution value.
					double cv$probabilitySample75Value30 = distribution$sample75[(index$i$28 - 1)][index$sample75$29];
					
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample75Value30);
					
					// Variable declaration of cv$temp$3$var70 moved.
					// 
					// Constructing a random variable input for use later.
					double[] cv$temp$3$var70 = m[index$sample75$29];
					
					// Variable declaration of cv$accumulatedProbabilities moved.
					// Declaration comment was:
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 91 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					double cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityBernoulli(flips[i$var66], bias[cv$valuePos]) + Math.log(cv$probabilitySample75Value30)) + ((cv$valuePos < cv$temp$3$var70.length)?Math.log(cv$temp$3$var70[cv$valuePos]):Double.NEGATIVE_INFINITY));
					
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
			int index$i$55_2 = (i$var66 + 1);
			if((index$i$55_2 < samples)) {
				// Processing sample task 75 of consumer random variable null.
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < 5; cv$i += 1)
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					cv$distributionAccumulator$var71[cv$i] = 0.0;
				
				// Declare and zero an accumulator for tracking the reached source probability space.
				double scopeVariable$reachedSourceProbability = 0.0;
				
				// Enumerating the possible arguments for Categorical 71.
				if((1 == i$var66)) {
					// Enumerating the possible arguments for Categorical 71.
					if(fixedFlag$sample57) {
						int index$var29$64_1 = st[0];
						
						// Substituted "i$var66" with its value "1".
						if(((0 <= index$var29$64_1) && (index$var29$64_1 < 5)))
							// Add the probability of this argument configuration.
							// 
							// Declare and zero an accumulator for tracking the reached source probability space.
							scopeVariable$reachedSourceProbability = 1.0;
					} else {
						// Enumerating the possible outputs of Categorical 53.
						for(int index$sample57$60 = 0; index$sample57$60 < 5; index$sample57$60 += 1)
							// Add the probability of this argument configuration.
							// 
							// cv$probabilitySample57Value61's comment
							// Update the probability of sampling this value from the distribution value.
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample57[index$sample57$60]);
					}
				}
				int index$i$67 = (i$var66 - 1);
				
				// index$i$57's comment
				// Copy of index so that its values can be safely substituted
				// 
				// Substituted "index$i$55_2" with its value "(i$var66 + 1)".
				// 
				// Substituted "index$i$55_2" with its value "(i$var66 + 1)".
				// 
				// Substituted "index$i$55_2" with its value "(i$var66 + 1)".
				// 
				// Substituted "index$i$55_2" with its value "(i$var66 + 1)".
				// 
				// Substituted "index$i$55_2" with its value "(i$var66 + 1)".
				// 
				// Substituted "index$i$55_2" with its value "(i$var66 + 1)".
				// 
				// Substituted "index$i$55_2" with its value "(i$var66 + 1)".
				if((((1 <= index$i$67) && !(index$i$67 == i$var66)) && !(index$i$67 == index$i$55_2))) {
					// Enumerating the possible outputs of Categorical 71.
					for(int index$sample75$68 = 0; index$sample75$68 < 5; index$sample75$68 += 1)
						// Add the probability of this argument configuration.
						// 
						// cv$probabilitySample75Value69's comment
						// Update the probability of sampling this value from the distribution value.
						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample75[(index$i$67 - 1)][index$sample75$68]);
				}
				
				// Add the current distribution to the distribution accumulator.
				// 
				// The probability of reaching the consumer with this set of consumer arguments
				// 
				// cv$temp$8$var70's comment
				// Variable declaration of cv$temp$8$var70 moved.
				// 
				// Constructing a random variable input for use later.
				// 
				// Processing random variable 71.
				// 
				// Looking for a path between Sample 75 and consumer Categorical 71.
				// 
				// Value of the variable at this index
				DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var71, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				
				// A local copy of the samples' distribution.
				double[] cv$sampleDistribution = distribution$sample75[(index$i$55_2 - 1)];
				
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
					double cv$normalisedDistValue = (cv$distributionAccumulator$var71[cv$i] / scopeVariable$reachedSourceProbability);
					
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
			cv$var72$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample75[(i$var66 - 1)];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		// 
		// Get a local reference to the scratch space.
		double cv$lseMax = cv$var72$stateProbabilityGlobal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			// Get a local reference to the scratch space.
			double cv$lseElementValue = cv$var72$stateProbabilityGlobal[cv$lseIndex];
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
				cv$lseSum = (cv$lseSum + Math.exp((cv$var72$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			
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
				cv$localProbability[cv$indexName] = Math.exp((cv$var72$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		// 
		// Get a local reference to the scratch space.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var72$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Allocation of cv$var30$countGlobal for multithreaded execution
		// 
		// Get the thread count.
		int cv$threadCount = threadCount();
		
		// Allocate an array to hold a copy per thread
		cv$var30$countGlobal = new double[cv$threadCount][];
		
		// Populate the array with a copy per thread
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var30$countGlobal[cv$index] = new double[5];
		
		// Constructor for cv$distributionAccumulator$var71
		// 
		// Allocation of cv$distributionAccumulator$var71 for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 73. Initially set to the value
		// of putTask 32.
		cv$distributionAccumulator$var71 = new double[5];
		
		// Constructor for cv$var54$stateProbabilityGlobal
		// 
		// Allocation of cv$var54$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 55. Initially set to the value
		// of putTask 32.
		cv$var54$stateProbabilityGlobal = new double[5];
		
		// Allocation of cv$var72$stateProbabilityGlobal for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 73. Initially set to the value
		// of putTask 32.
		cv$var72$stateProbabilityGlobal = new double[5];
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
			for(int var29 = 0; var29 < 5; var29 += 1)
				m[var29] = new double[5];
		}
		
		// If bias has not been set already allocate space.
		if(!setFlag$bias)
			// Constructor for bias
			bias = new double[5];
		
		// If st has not been set already allocate space.
		if(!setFlag$st)
			// Constructor for st
			st = new int[length$flipsMeasured];
		
		// If flips has not been set already allocate space.
		if(!setFlag$flips)
			// Constructor for flips
			flips = new boolean[length$flipsMeasured];
		
		// Constructor for distribution$sample57
		distribution$sample57 = new double[5];
		
		// Constructor for distribution$sample75
		distribution$sample75 = new double[(length$flipsMeasured - 1)][];
		for(int i$var66 = 1; i$var66 < length$flipsMeasured; i$var66 += 1)
			distribution$sample75[(i$var66 - 1)] = new double[5];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample31)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var29]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample48)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample57)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample75) {
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1)
				st[i$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var66 - 1)]]);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample91)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int j = forStart$j; j < forEnd$j; j += 1)
							flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[st[j]]);
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample31)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var29]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample48)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample57) {
			double[] var52 = m[0];
			for(int index$var53 = 0; index$var53 < 5; index$var53 += 1)
				// Save the probability of each value
				// 
				// cv$distribution$sample57's comment
				// Create local copy of variable probabilities.
				distribution$sample57[index$var53] = ((index$var53 < var52.length)?var52[index$var53]:0.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample75) {
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
				// Create local copy of variable probabilities.
				double[] cv$distribution$sample75 = distribution$sample75[(i$var66 - 1)];
				for(int index$var71 = 0; index$var71 < 5; index$var71 += 1)
					// Zero the probability of each value
					cv$distribution$sample75[index$var71] = 0.0;
				
				// Iterate through possible values for var71's arguments.
				// 
				// Enumerating the possible arguments for Categorical 71.
				if((1 == i$var66)) {
					// Iterate through possible values for var71's arguments.
					// 
					// Enumerating the possible arguments for Categorical 71.
					if(fixedFlag$sample57) {
						int var29 = st[0];
						
						// Substituted "i$var66" with its value "1".
						if(((0 <= var29) && (var29 < 5))) {
							// Substituted "i$var66" with its value "1".
							double[] var70 = m[st[0]];
							for(int index$var71 = 0; index$var71 < 5; index$var71 += 1)
								// Save the probability of each value
								cv$distribution$sample75[index$var71] = (cv$distribution$sample75[index$var71] + ((index$var71 < var70.length)?var70[index$var71]:0.0));
						}
					} else {
						// Enumerating the possible outputs of Categorical 53.
						for(int index$sample57$2 = 0; index$sample57$2 < 5; index$sample57$2 += 1) {
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample57Value3 = distribution$sample57[index$sample57$2];
							double[] var70 = m[index$sample57$2];
							for(int index$var71 = 0; index$var71 < 5; index$var71 += 1)
								// Save the probability of each value
								cv$distribution$sample75[index$var71] = (cv$distribution$sample75[index$var71] + (cv$probabilitySample57Value3 * ((index$var71 < var70.length)?var70[index$var71]:0.0)));
						}
					}
				}
				int index$i$9 = (i$var66 - 1);
				
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 <= index$i$9)) {
					// Enumerating the possible outputs of Categorical 71.
					for(int index$sample75$10 = 0; index$sample75$10 < 5; index$sample75$10 += 1) {
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample75Value11 = distribution$sample75[(index$i$9 - 1)][index$sample75$10];
						double[] var70 = m[index$sample75$10];
						for(int index$var71 = 0; index$var71 < 5; index$var71 += 1)
							// Save the probability of each value
							cv$distribution$sample75[index$var71] = (cv$distribution$sample75[index$var71] + (cv$probabilitySample75Value11 * ((index$var71 < var70.length)?var70[index$var71]:0.0)));
					}
				}
				
				// Sum the values in the array
				double cv$var71$sum = 0.0;
				for(int index$var71 = 0; index$var71 < 5; index$var71 += 1)
					// sum the probability of each value
					cv$var71$sum = (cv$var71$sum + cv$distribution$sample75[index$var71]);
				for(int index$var71 = 0; index$var71 < 5; index$var71 += 1)
					// Normalise the probability of each value
					cv$distribution$sample75[index$var71] = (cv$distribution$sample75[index$var71] / cv$var71$sum);
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample31)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var29]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample48)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample57)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample75) {
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1)
				st[i$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var66 - 1)]]);
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample31)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
								sample31(var29, threadID$var29, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample48)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
								sample48(var45, threadID$var45, RNG$1);
					}
				);

			if(!fixedFlag$sample57)
				sample57();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample75) {
				for(int i$var66 = 1; i$var66 < samples; i$var66 += 1)
					sample75(i$var66);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample75) {
				for(int i$var66 = (samples - 1); i$var66 >= 1; i$var66 -= 1)
					sample75(i$var66);
			}
			if(!fixedFlag$sample57)
				sample57();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample48)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
								sample48(var45, threadID$var45, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample31)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
								sample31(var29, threadID$var29, RNG$1);
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
			(int forStart$i$var15, int forEnd$i$var15, int threadID$i$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var15 = forStart$i$var15; i$var15 < forEnd$i$var15; i$var15 += 1)
						v[i$var15] = 0.1;
			}
		);
		samples = length$flipsMeasured;
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
		logProbability$var18 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$var30 = 0.0;
		logProbability$var34 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample48)
			logProbability$var46 = 0.0;
		logProbability$var53 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample57)
			logProbability$var54 = 0.0;
		logProbability$var71 = 0.0;
		if(!fixedProbFlag$sample75)
			logProbability$var72 = 0.0;
		logProbability$var87 = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample91)
			logProbability$var88 = 0.0;
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
		if(fixedFlag$sample31)
			logProbabilityValue$sample31();
		if(fixedFlag$sample48)
			logProbabilityValue$sample48();
		logProbabilityValue$sample91();
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
		logProbabilityValue$sample31();
		logProbabilityValue$sample48();
		logProbabilityDistribution$sample57();
		logProbabilityDistribution$sample75();
		logProbabilityDistribution$sample91();
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
		logProbabilityValue$sample31();
		logProbabilityValue$sample48();
		logProbabilityValue$sample57();
		logProbabilityValue$sample75();
		logProbabilityValue$sample91();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample31)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var29]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample48)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample57)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample75) {
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1)
				st[i$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var66 - 1)]]);
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
	public final void setIntermediates() {}

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
		     + "model HMMTestPart7(boolean[] flipsMeasured) {\n"
		     + "        int states = 5;\n"
		     + "\n"
		     + "        double[] v = new double[states];\n"
		     + "        for(int i:[0..states))\n"
		     + "            v[i] = 0.1;\n"
		     + "        \n"
		     + "        double[][] m = dirichlet(v).sample(states);\n"
		     + "        double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "        int samples = flipsMeasured.length;\n"
		     + "        int[] st = new int[samples];\n"
		     + "\n"
		     + "        st[0] = categorical(m[0]).sampleDistribution();\n"
		     + "\n"
		     + "        for(int i:[1..samples))\n"
		     + "            st[i] = categorical(m[st[i-1]]).sampleDistribution();\n"
		     + "            \n"
		     + "        boolean[] flips = new boolean[samples];\n"
		     + "            \n"
		     + "        for(int j:[0..samples))\n"
		     + "            flips[j] = bernoulli(bias[st[j]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}