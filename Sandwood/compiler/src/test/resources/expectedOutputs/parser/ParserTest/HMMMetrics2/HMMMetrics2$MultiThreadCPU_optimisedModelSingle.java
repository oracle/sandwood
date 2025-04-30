package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMMetrics2$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] cv$distributionAccumulator$var120;
	private double[][] cv$var102$stateProbabilityGlobal;
	private double[][] cv$var121$stateProbabilityGlobal;
	private double[] cv$var19$countGlobal;
	private double[][] cv$var32$countGlobal;
	private double[][] distribution$sample104;
	private double[][][] distribution$sample123;
	private boolean fixedFlag$sample104 = false;
	private boolean fixedFlag$sample123 = false;
	private boolean fixedFlag$sample19 = false;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedFlag$sample52 = false;
	private boolean fixedFlag$sample68 = false;
	private boolean fixedFlag$sample84 = false;
	private boolean fixedProbFlag$sample104 = false;
	private boolean fixedProbFlag$sample123 = false;
	private boolean fixedProbFlag$sample145 = false;
	private boolean fixedProbFlag$sample157 = false;
	private boolean fixedProbFlag$sample19 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean fixedProbFlag$sample52 = false;
	private boolean fixedProbFlag$sample68 = false;
	private boolean fixedProbFlag$sample84 = false;
	private boolean[][][] guard$sample104gaussian156$global;
	private boolean[][][] guard$sample123gaussian156$global;
	private double[] initialStateDistribution;
	private int[] length$metric;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$initialStateDistribution;
	private double logProbability$m;
	private double logProbability$metric_g;
	private double logProbability$metric_mean;
	private double logProbability$metric_valid_1d;
	private double logProbability$metric_valid_bias;
	private double logProbability$metric_valid_g;
	private double logProbability$metric_var;
	private double logProbability$st;
	private double logProbability$var101;
	private double logProbability$var102;
	private double logProbability$var120;
	private double logProbability$var121;
	private double logProbability$var140;
	private double logProbability$var141;
	private double logProbability$var150;
	private double logProbability$var151;
	private double logProbability$var18;
	private double logProbability$var20;
	private double logProbability$var32;
	private double logProbability$var39;
	private double logProbability$var51;
	private double logProbability$var55;
	private double logProbability$var67;
	private double logProbability$var71;
	private double logProbability$var83;
	private double[][] m;
	private double[][] metric;
	private double[][] metric_g;
	private double[] metric_mean;
	private boolean[][] metric_valid;
	private double[] metric_valid_bias;
	private boolean[][] metric_valid_g;
	private double[] metric_var;
	private int noSamples;
	private int noStates;
	private int[][] st;
	private boolean system$gibbsForward = true;
	private double[] v;
	private double[][] var151;

	public HMMMetrics2$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for distribution$sample104.
	@Override
	public final double[][] get$distribution$sample104() {
		return distribution$sample104;
	}

	// Setter for distribution$sample104.
	@Override
	public final void set$distribution$sample104(double[][] cv$value) {
		// Set distribution$sample104
		distribution$sample104 = cv$value;
	}

	// Getter for distribution$sample123.
	@Override
	public final double[][][] get$distribution$sample123() {
		return distribution$sample123;
	}

	// Setter for distribution$sample123.
	@Override
	public final void set$distribution$sample123(double[][][] cv$value) {
		// Set distribution$sample123
		distribution$sample123 = cv$value;
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
		
		// Should the probability of sample 123 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample104" with its value "cv$value".
		fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
		
		// Should the probability of sample 145 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample104" with its value "cv$value".
		fixedProbFlag$sample145 = (cv$value && fixedProbFlag$sample145);
		
		// Should the probability of sample 157 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample104" with its value "cv$value".
		fixedProbFlag$sample157 = (cv$value && fixedProbFlag$sample157);
	}

	// Getter for fixedFlag$sample123.
	@Override
	public final boolean get$fixedFlag$sample123() {
		return fixedFlag$sample123;
	}

	// Setter for fixedFlag$sample123.
	@Override
	public final void set$fixedFlag$sample123(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample123 including if probabilities
		// need to be updated.
		fixedFlag$sample123 = cv$value;
		
		// Should the probability of sample 123 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample123" with its value "cv$value".
		fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
		
		// Should the probability of sample 145 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample123" with its value "cv$value".
		fixedProbFlag$sample145 = (cv$value && fixedProbFlag$sample145);
		
		// Should the probability of sample 157 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample123" with its value "cv$value".
		fixedProbFlag$sample157 = (cv$value && fixedProbFlag$sample157);
	}

	// Getter for fixedFlag$sample19.
	@Override
	public final boolean get$fixedFlag$sample19() {
		return fixedFlag$sample19;
	}

	// Setter for fixedFlag$sample19.
	@Override
	public final void set$fixedFlag$sample19(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample19 including if probabilities
		// need to be updated.
		fixedFlag$sample19 = cv$value;
		
		// Should the probability of sample 19 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample19" with its value "cv$value".
		fixedProbFlag$sample19 = (cv$value && fixedProbFlag$sample19);
		
		// Should the probability of sample 104 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample19" with its value "cv$value".
		fixedProbFlag$sample104 = (cv$value && fixedProbFlag$sample104);
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
		
		// Should the probability of sample 123 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample32" with its value "cv$value".
		fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
	}

	// Getter for fixedFlag$sample52.
	@Override
	public final boolean get$fixedFlag$sample52() {
		return fixedFlag$sample52;
	}

	// Setter for fixedFlag$sample52.
	@Override
	public final void set$fixedFlag$sample52(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample52 including if probabilities
		// need to be updated.
		fixedFlag$sample52 = cv$value;
		
		// Should the probability of sample 52 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample52" with its value "cv$value".
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
		
		// Should the probability of sample 157 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample52" with its value "cv$value".
		fixedProbFlag$sample157 = (cv$value && fixedProbFlag$sample157);
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
		
		// Should the probability of sample 157 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample68" with its value "cv$value".
		fixedProbFlag$sample157 = (cv$value && fixedProbFlag$sample157);
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
		
		// Should the probability of sample 145 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample84" with its value "cv$value".
		fixedProbFlag$sample145 = (cv$value && fixedProbFlag$sample145);
	}

	// Getter for initialStateDistribution.
	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	// Setter for initialStateDistribution.
	@Override
	public final void set$initialStateDistribution(double[] cv$value) {
		// Set flags for all the side effects of initialStateDistribution including if probabilities
		// need to be updated.
		// Set initialStateDistribution
		initialStateDistribution = cv$value;
		
		// Unset the fixed probability flag for sample 19 as it depends on initialStateDistribution.
		fixedProbFlag$sample19 = false;
		
		// Unset the fixed probability flag for sample 104 as it depends on initialStateDistribution.
		fixedProbFlag$sample104 = false;
	}

	// Getter for length$metric.
	@Override
	public final int[] get$length$metric() {
		return length$metric;
	}

	// Setter for length$metric.
	@Override
	public final void set$length$metric(int[] cv$value) {
		// Set length$metric
		length$metric = cv$value;
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

	// Getter for logProbability$initialStateDistribution.
	@Override
	public final double get$logProbability$initialStateDistribution() {
		return logProbability$initialStateDistribution;
	}

	// Getter for logProbability$m.
	@Override
	public final double get$logProbability$m() {
		return logProbability$m;
	}

	// Getter for logProbability$metric_g.
	@Override
	public final double get$logProbability$metric_g() {
		return logProbability$metric_g;
	}

	// Getter for logProbability$metric_mean.
	@Override
	public final double get$logProbability$metric_mean() {
		return logProbability$metric_mean;
	}

	// Getter for logProbability$metric_valid_bias.
	@Override
	public final double get$logProbability$metric_valid_bias() {
		return logProbability$metric_valid_bias;
	}

	// Getter for logProbability$metric_valid_g.
	@Override
	public final double get$logProbability$metric_valid_g() {
		return logProbability$metric_valid_g;
	}

	// Getter for logProbability$metric_var.
	@Override
	public final double get$logProbability$metric_var() {
		return logProbability$metric_var;
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
		// Set m
		m = cv$value;
		
		// Unset the fixed probability flag for sample 32 as it depends on m.
		fixedProbFlag$sample32 = false;
		
		// Unset the fixed probability flag for sample 123 as it depends on m.
		fixedProbFlag$sample123 = false;
	}

	// Getter for metric.
	@Override
	public final double[][] get$metric() {
		return metric;
	}

	// Setter for metric.
	@Override
	public final void set$metric(double[][] cv$value) {
		// Set metric
		metric = cv$value;
	}

	// Getter for metric_g.
	@Override
	public final double[][] get$metric_g() {
		return metric_g;
	}

	// Getter for metric_mean.
	@Override
	public final double[] get$metric_mean() {
		return metric_mean;
	}

	// Setter for metric_mean.
	@Override
	public final void set$metric_mean(double[] cv$value) {
		// Set flags for all the side effects of metric_mean including if probabilities need
		// to be updated.
		// Set metric_mean
		metric_mean = cv$value;
		
		// Unset the fixed probability flag for sample 52 as it depends on metric_mean.
		fixedProbFlag$sample52 = false;
		
		// Unset the fixed probability flag for sample 157 as it depends on metric_mean.
		fixedProbFlag$sample157 = false;
	}

	// Getter for metric_valid.
	@Override
	public final boolean[][] get$metric_valid() {
		return metric_valid;
	}

	// Setter for metric_valid.
	@Override
	public final void set$metric_valid(boolean[][] cv$value) {
		// Set metric_valid
		metric_valid = cv$value;
	}

	// Getter for metric_valid_bias.
	@Override
	public final double[] get$metric_valid_bias() {
		return metric_valid_bias;
	}

	// Setter for metric_valid_bias.
	@Override
	public final void set$metric_valid_bias(double[] cv$value) {
		// Set flags for all the side effects of metric_valid_bias including if probabilities
		// need to be updated.
		// Set metric_valid_bias
		metric_valid_bias = cv$value;
		
		// Unset the fixed probability flag for sample 84 as it depends on metric_valid_bias.
		fixedProbFlag$sample84 = false;
		
		// Unset the fixed probability flag for sample 145 as it depends on metric_valid_bias.
		fixedProbFlag$sample145 = false;
	}

	// Getter for metric_valid_g.
	@Override
	public final boolean[][] get$metric_valid_g() {
		return metric_valid_g;
	}

	// Getter for metric_var.
	@Override
	public final double[] get$metric_var() {
		return metric_var;
	}

	// Setter for metric_var.
	@Override
	public final void set$metric_var(double[] cv$value) {
		// Set flags for all the side effects of metric_var including if probabilities need
		// to be updated.
		// Set metric_var
		metric_var = cv$value;
		
		// Unset the fixed probability flag for sample 68 as it depends on metric_var.
		fixedProbFlag$sample68 = false;
		
		// Unset the fixed probability flag for sample 157 as it depends on metric_var.
		fixedProbFlag$sample157 = false;
	}

	// Getter for noSamples.
	@Override
	public final int get$noSamples() {
		return noSamples;
	}

	// Getter for noStates.
	@Override
	public final int get$noStates() {
		return noStates;
	}

	// Setter for noStates.
	@Override
	public final void set$noStates(int cv$value) {
		noStates = cv$value;
	}

	// Getter for st.
	@Override
	public final int[][] get$st() {
		return st;
	}

	// Setter for st.
	@Override
	public final void set$st(int[][] cv$value) {
		// Set flags for all the side effects of st including if probabilities need to be
		// updated.
		// Set st
		st = cv$value;
		
		// Unset the fixed probability flag for sample 104 as it depends on st.
		fixedProbFlag$sample104 = false;
		
		// Unset the fixed probability flag for sample 123 as it depends on st.
		fixedProbFlag$sample123 = false;
		
		// Unset the fixed probability flag for sample 145 as it depends on st.
		fixedProbFlag$sample145 = false;
		
		// Unset the fixed probability flag for sample 157 as it depends on st.
		fixedProbFlag$sample157 = false;
	}

	// Getter for v.
	@Override
	public final double[] get$v() {
		return v;
	}

	// Calculate the probability of the samples represented by sample104 using probability
	// distributions.
	private final void logProbabilityDistribution$sample104() {
		// Determine if we need to calculate the values for sample task 104 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample104) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample104) {
				// Generating probabilities for sample task
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int sample = 0; sample < noSamples; sample += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[sample][0];
					
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
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
				}
				logProbability$var101 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var102 = cv$sampleAccumulator;
				
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
				// Substituted "fixedFlag$sample104" with its value "true".
				fixedProbFlag$sample104 = fixedFlag$sample19;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var101 = logProbability$var102;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample104)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$st = (logProbability$st + logProbability$var102);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var102);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample104)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var102);
		}
	}

	// Calculate the probability of the samples represented by sample123 using probability
	// distributions.
	private final void logProbabilityDistribution$sample123() {
		// Determine if we need to calculate the values for sample task 123 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample123) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample123) {
				// Generating probabilities for sample task
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int sample = 0; sample < noSamples; sample += 1) {
					for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[sample][timeStep$var113];
						
						// Enumerating the possible arguments for Categorical 120.
						if((1 == timeStep$var113)) {
							// Enumerating the possible arguments for Categorical 120.
							if(fixedFlag$sample104) {
								int var31 = st[sample][0];
								
								// Substituted "timeStep$var113" with its value "1".
								if(((0 <= var31) && (var31 < noStates))) {
									// Store the value of the function call, so the function call is only made once.
									// 
									// Substituted "timeStep$var113" with its value "1".
									cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[st[sample][0]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
									
									// Add the probability of this distribution configuration to the accumulator.
									// 
									// An accumulator for the distributed probability space covered.
									cv$probabilityReached = 1.0;
								}
							} else {
								// Enumerating the possible outputs of Categorical 101.
								for(int index$sample104$6 = 0; index$sample104$6 < noStates; index$sample104$6 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "index$sample$5" with its value "sample".
									double cv$probabilitySample104Value7 = distribution$sample104[sample][index$sample104$6];
									int var31 = st[sample][0];
									
									// Substituted "timeStep$var113" with its value "1".
									if(((0 <= var31) && (var31 < noStates))) {
										// Store the value of the function call, so the function call is only made once.
										// 
										// Substituted "timeStep$var113" with its value "1".
										double cv$weightedProbability = (Math.log(cv$probabilitySample104Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[st[sample][0]][cv$sampleValue]):Double.NEGATIVE_INFINITY));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value7);
									}
								}
							}
						}
						
						// Substituted "index$sample$13_1" with its value "sample".
						// 
						// Substituted "index$timeStep$13_2" with its value "(timeStep$var113 - 1)".
						if((2 <= timeStep$var113)) {
							int var31 = st[sample][(timeStep$var113 - 1)];
							if(((0 <= var31) && (var31 < noStates))) {
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[st[sample][(timeStep$var113 - 1)]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
								
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
				}
				logProbability$var120 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var121 = cv$sampleAccumulator;
				
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
				// Substituted "fixedFlag$sample123" with its value "true".
				fixedProbFlag$sample123 = (fixedFlag$sample32 && fixedFlag$sample104);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var120 = logProbability$var121;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample123)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$st = (logProbability$st + logProbability$var121);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var121);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample123)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var121);
		}
	}

	// Calculate the probability of the samples represented by sample145 using probability
	// distributions.
	private final void logProbabilityDistribution$sample145() {
		// Determine if we need to calculate the values for sample task 145 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample145) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Look for paths between the variable and the sample task 145 including any distribution
					// values.
					// 
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = metric_valid_g[sample][timeStep$var136];
					
					// Enumerating the possible arguments for Bernoulli 140.
					if((0 == timeStep$var136)) {
						// Enumerating the possible arguments for Bernoulli 140.
						if(fixedFlag$sample104) {
							int var82 = st[sample][0];
							
							// Substituted "timeStep$var136" with its value "0".
							if(((0 <= var82) && (var82 < noStates))) {
								// Substituted "timeStep$var136" with its value "0".
								double var139 = metric_valid_bias[st[sample][0]];
								
								// Store the value of the function call, so the function call is only made once.
								cv$distributionAccumulator = Math.log((cv$sampleValue?var139:(1.0 - var139)));
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						} else {
							// Enumerating the possible outputs of Categorical 101.
							for(int index$sample104$4 = 0; index$sample104$4 < noStates; index$sample104$4 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$sample$3" with its value "sample".
								double cv$probabilitySample104Value5 = distribution$sample104[sample][index$sample104$4];
								int var82 = st[sample][0];
								
								// Substituted "timeStep$var136" with its value "0".
								if(((0 <= var82) && (var82 < noStates))) {
									// Substituted "timeStep$var136" with its value "0".
									double var139 = metric_valid_bias[st[sample][0]];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample104Value5) + Math.log((cv$sampleValue?var139:(1.0 - var139))));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value5);
								}
							}
						}
					}
					
					// Enumerating the possible arguments for Bernoulli 140.
					if((1 <= timeStep$var136)) {
						// Enumerating the possible arguments for Bernoulli 140.
						if(fixedFlag$sample123) {
							int var82 = st[sample][timeStep$var136];
							if(((0 <= var82) && (var82 < noStates))) {
								double var139 = metric_valid_bias[st[sample][timeStep$var136]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = Math.log((cv$sampleValue?var139:(1.0 - var139)));
								
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
							// Enumerating the possible outputs of Categorical 120.
							for(int index$sample123$13 = 0; index$sample123$13 < noStates; index$sample123$13 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$sample$11" with its value "sample".
								double cv$probabilitySample123Value14 = distribution$sample123[sample][(timeStep$var136 - 1)][index$sample123$13];
								int var82 = st[sample][timeStep$var136];
								if(((0 <= var82) && (var82 < noStates))) {
									double var139 = metric_valid_bias[st[sample][timeStep$var136]];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample123Value14) + Math.log((cv$sampleValue?var139:(1.0 - var139))));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value14);
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
			}
			logProbability$var140 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var141 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$sampleAccumulator);
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample145 = ((fixedFlag$sample84 && fixedFlag$sample104) && fixedFlag$sample123);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var140 = logProbability$var141;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + logProbability$var141);
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$metric_valid_g = (logProbability$metric_valid_g + logProbability$var141);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var141);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var141);
		}
	}

	// Calculate the probability of the samples represented by sample157 using probability
	// distributions.
	private final void logProbabilityDistribution$sample157() {
		// Determine if we need to calculate the values for sample task 157 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample157) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136]) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// Look for paths between the variable and the sample task 157 including any distribution
						// values.
						// 
						// The sample value to calculate the probability of generating
						double cv$sampleValue = var151[sample][timeStep$var136];
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(((0 == timeStep$var136) && (0 <= st[sample][0]))) {
							// Enumerating the possible arguments for Gaussian 150.
							if(fixedFlag$sample104) {
								int var50 = st[sample][0];
								
								// Substituted "timeStep$var136" with its value "0".
								if(((0 <= var50) && (var50 < noStates))) {
									// Substituted "timeStep$var136" with its value "0".
									double var149 = metric_var[st[sample][0]];
									
									// Store the value of the function call, so the function call is only made once.
									// 
									// Substituted "timeStep$var136" with its value "0".
									cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[st[sample][0]]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5));
									
									// Add the probability of this distribution configuration to the accumulator.
									// 
									// An accumulator for the distributed probability space covered.
									cv$probabilityReached = 1.0;
								}
							} else {
								// Enumerating the possible outputs of Categorical 101.
								for(int index$sample104$4 = 0; index$sample104$4 < noStates; index$sample104$4 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "index$sample$3" with its value "sample".
									double cv$probabilitySample104Value5 = distribution$sample104[sample][index$sample104$4];
									int var50 = st[sample][0];
									
									// Substituted "timeStep$var136" with its value "0".
									if(((0 <= var50) && (var50 < noStates))) {
										// Substituted "timeStep$var136" with its value "0".
										double var149 = metric_var[st[sample][0]];
										
										// Store the value of the function call, so the function call is only made once.
										// 
										// Substituted "timeStep$var136" with its value "0".
										double cv$weightedProbability = ((Math.log(cv$probabilitySample104Value5) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[st[sample][0]]) / Math.sqrt(var149)))) - (Math.log(var149) * 0.5));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value5);
									}
								}
							}
						}
						
						// Enumerating the possible arguments for Gaussian 150.
						if(((1 <= timeStep$var136) && (0 <= st[sample][timeStep$var136]))) {
							// Enumerating the possible arguments for Gaussian 150.
							if(fixedFlag$sample123) {
								int var50 = st[sample][timeStep$var136];
								if(((0 <= var50) && (var50 < noStates))) {
									double var149 = metric_var[st[sample][timeStep$var136]];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[st[sample][timeStep$var136]]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5));
									
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
								// Enumerating the possible outputs of Categorical 120.
								for(int index$sample123$49 = 0; index$sample123$49 < noStates; index$sample123$49 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "index$sample$47" with its value "sample".
									double cv$probabilitySample123Value50 = distribution$sample123[sample][(timeStep$var136 - 1)][index$sample123$49];
									int var50 = st[sample][timeStep$var136];
									if(((0 <= var50) && (var50 < noStates))) {
										double var149 = metric_var[st[sample][timeStep$var136]];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = ((Math.log(cv$probabilitySample123Value50) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[st[sample][timeStep$var136]]) / Math.sqrt(var149)))) - (Math.log(var149) * 0.5));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value50);
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
				}
			}
			logProbability$var150 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var151 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_g = (logProbability$metric_g + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample157 = (((fixedFlag$sample52 && fixedFlag$sample68) && fixedFlag$sample104) && fixedFlag$sample123);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var150 = logProbability$var151;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$metric_g = (logProbability$metric_g + logProbability$var151);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var151);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var151);
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
			for(int sample = 0; sample < noSamples; sample += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = st[sample][0];
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			logProbability$var101 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var102 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample104)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample104 = (fixedFlag$sample104 && fixedFlag$sample19);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var101 = logProbability$var102;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var102);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var102);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample104)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var102);
		}
	}

	// Calculate the probability of the samples represented by sample123 using sampled
	// values.
	private final void logProbabilityValue$sample123() {
		// Determine if we need to calculate the values for sample task 123 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample123) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[sample][timeStep$var113];
					
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
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[st[sample][(timeStep$var113 - 1)]][cv$sampleValue]):Double.NEGATIVE_INFINITY));
				}
			}
			logProbability$var120 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var121 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample123)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample123 = ((fixedFlag$sample123 && fixedFlag$sample32) && fixedFlag$sample104);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var120 = logProbability$var121;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var121);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var121);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample123)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var121);
		}
	}

	// Calculate the probability of the samples represented by sample145 using sampled
	// values.
	private final void logProbabilityValue$sample145() {
		// Determine if we need to calculate the values for sample task 145 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample145) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					double var139 = metric_valid_bias[st[sample][timeStep$var136]];
					
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
					cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((metric_valid_g[sample][timeStep$var136]?var139:(1.0 - var139))));
				}
			}
			logProbability$var140 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var141 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$sampleAccumulator);
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample145 = ((fixedFlag$sample84 && fixedFlag$sample104) && fixedFlag$sample123);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var140 = logProbability$var141;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + logProbability$var141);
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$metric_valid_g = (logProbability$metric_valid_g + logProbability$var141);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var141);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var141);
		}
	}

	// Calculate the probability of the samples represented by sample157 using sampled
	// values.
	private final void logProbabilityValue$sample157() {
		// Determine if we need to calculate the values for sample task 157 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample157) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136]) {
						double var149 = metric_var[st[sample][timeStep$var136]];
						
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
						cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - metric_mean[st[sample][timeStep$var136]]) / Math.sqrt(var149)))) - (Math.log(var149) * 0.5));
					}
				}
			}
			logProbability$var150 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var151 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_g = (logProbability$metric_g + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample157 = (((fixedFlag$sample52 && fixedFlag$sample68) && fixedFlag$sample104) && fixedFlag$sample123);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var150 = logProbability$var151;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$metric_g = (logProbability$metric_g + logProbability$var151);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var151);
			
			// Variable declaration of cv$accumulator moved.
			logProbability$$evidence = (logProbability$$evidence + logProbability$var151);
		}
	}

	// Calculate the probability of the samples represented by sample19 using sampled
	// values.
	private final void logProbabilityValue$sample19() {
		// Determine if we need to calculate the values for sample task 19 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample19) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(initialStateDistribution, v, noStates);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var18 = cv$distributionAccumulator;
			
			// Store the sample task probability
			logProbability$initialStateDistribution = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample19)
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
			fixedProbFlag$sample19 = fixedFlag$sample19;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var18 = logProbability$initialStateDistribution;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$initialStateDistribution);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample19)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialStateDistribution);
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
			for(int var31 = 0; var31 < noStates; var31 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var31], v, noStates));
			logProbability$var20 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var32 = cv$sampleAccumulator;
			
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
			logProbability$var20 = logProbability$var32;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var32);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var32);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample32)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var32);
		}
	}

	// Calculate the probability of the samples represented by sample52 using sampled
	// values.
	private final void logProbabilityValue$sample52() {
		// Determine if we need to calculate the values for sample task 52 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample52) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var50 = 0; var50 < noStates; var50 += 1) {
				// The sample value to calculate the probability of generating
				double cv$sampleValue = metric_mean[var50];
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY));
			}
			logProbability$var39 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var51 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_mean = (logProbability$metric_mean + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample52)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample52 = fixedFlag$sample52;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var39 = logProbability$var51;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$metric_mean = (logProbability$metric_mean + logProbability$var51);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var51);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample52)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var51);
		}
	}

	// Calculate the probability of the samples represented by sample68 using sampled
	// values.
	private final void logProbabilityValue$sample68() {
		// Determine if we need to calculate the values for sample task 68 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample68) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var66 = 0; var66 < noStates; var66 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(metric_var[var66], 1.0, 1.0));
			logProbability$var55 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var67 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_var = (logProbability$metric_var + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample68)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample68 = fixedFlag$sample68;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var55 = logProbability$var67;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$metric_var = (logProbability$metric_var + logProbability$var67);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var67);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample68)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var67);
		}
	}

	// Calculate the probability of the samples represented by sample84 using sampled
	// values.
	private final void logProbabilityValue$sample84() {
		// Determine if we need to calculate the values for sample task 84 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample84) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var82 = 0; var82 < noStates; var82 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(metric_valid_bias[var82], 1.0, 1.0));
			logProbability$var71 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var83 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + cv$sampleAccumulator);
			
			// Add probability to model
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample84)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample84 = fixedFlag$sample84;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var71 = logProbability$var83;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + logProbability$var83);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var83);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample84)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var83);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 104 drawn from Categorical 101. Inference was performed using variable
	// marginalization.
	private final void sample104(int sample, int threadID$cv$sample, Rng RNG$) {
		// Variable declaration of cv$numNumStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
		// cv$numNumStates's comment
		// Calculate the number of states to evaluate.
		int cv$numNumStates = Math.max(0, noStates);
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var102$stateProbabilityGlobal[threadID$cv$sample];
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$initialStateDistribution" with its value "initialStateDistribution".
			// 
			// cv$temp$1$$var2889's comment
			// 
			// $var2889's comment
			// Constructing a random variable input for use later.
			double cv$accumulatedProbabilities = ((cv$valuePos < noStates)?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample123 && (1 < length$metric[sample]))) {
				// Looking for a path between Sample 104 and consumer Categorical 120.
				// Processing sample task 123 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				int var31 = st[sample][0];
				
				// Substituted "index$sample$3_2" with its value "sample".
				if(((0 <= var31) && (var31 < noStates))) {
					// Substituted "index$sample$3_2" with its value "sample".
					// 
					// cv$temp$3$$var2902's comment
					// 
					// $var2902's comment
					// Constructing a random variable input for use later.
					// 
					// cv$temp$2$var119's comment
					// Variable declaration of cv$temp$2$var119 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					cv$accumulatedConsumerProbabilities = (((0.0 <= st[sample][1]) && (st[sample][1] < noStates))?Math.log(m[cv$valuePos][st[sample][1]]):Double.NEGATIVE_INFINITY);
					
					// Recorded the probability of reaching sample task 123 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					cv$consumerDistributionProbabilityAccumulator = 0.0;
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
			if((0 < length$metric[sample])) {
				// Processing random variable 140.
				{
					// Looking for a path between Sample 104 and consumer Bernoulli 140.
					// Processing sample task 145 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var82 = st[sample][0];
					
					// Substituted "index$sample$9_2" with its value "sample".
					if(((0 <= var82) && (var82 < noStates))) {
						// Variable declaration of cv$temp$4$var139 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double cv$temp$4$var139 = metric_valid_bias[cv$valuePos];
						
						// Substituted "index$sample$9_2" with its value "sample".
						cv$accumulatedConsumerProbabilities = Math.log((metric_valid_g[sample][0]?cv$temp$4$var139:(1.0 - cv$temp$4$var139)));
						
						// Recorded the probability of reaching sample task 145 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				if(metric_valid_g[sample][0]) {
					// Looking for a path between Sample 104 and consumer Gaussian 150.
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					boolean[][] guard$sample104gaussian156 = guard$sample104gaussian156$global[threadID$cv$sample];
					
					// Set the flags to false
					// 
					// Substituted "timeStep$var136" with its value "0".
					guard$sample104gaussian156[sample][0] = false;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample104gaussian156[sample][0]) {
						// The body will execute, so should not be executed again
						// 
						// Substituted "timeStep$var136" with its value "0".
						guard$sample104gaussian156[sample][0] = true;
						
						// Processing sample task 157 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[sample][0])) {
							int var50 = st[sample][0];
							
							// Substituted "index$sample$15_2" with its value "sample".
							if(((0 <= var50) && (var50 < noStates))) {
								// Variable declaration of cv$temp$6$var149 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$6$var149 = metric_var[cv$valuePos];
								
								// Substituted "index$sample$15_2" with its value "sample".
								// 
								// cv$temp$5$var148's comment
								// Variable declaration of cv$temp$5$var148 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var151[sample][0] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$6$var149))) - (Math.log(cv$temp$6$var149) * 0.5));
								
								// Recorded the probability of reaching sample task 157 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
					
					// Substituted "timeStep$var136" with its value "0".
					if(!guard$sample104gaussian156[sample][0]) {
						// The body will execute, so should not be executed again
						// 
						// Substituted "timeStep$var136" with its value "0".
						guard$sample104gaussian156[sample][0] = true;
						
						// Processing sample task 157 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((0 <= st[sample][0])) {
							int var50 = st[sample][0];
							
							// Substituted "index$sample$16_2" with its value "sample".
							if(((0 <= var50) && (var50 < noStates))) {
								// Variable declaration of cv$temp$14$var149 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 150 which is consuming
								// the output of Sample task 104.
								// 
								// Value of the variable at this index
								double cv$temp$14$var149 = metric_var[cv$valuePos];
								
								// Substituted "index$sample$16_2" with its value "sample".
								// 
								// cv$temp$13$var148's comment
								// Variable declaration of cv$temp$13$var148 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 150 which is consuming
								// the output of Sample task 104.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var151[sample][0] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$14$var149))) - (Math.log(cv$temp$14$var149) * 0.5));
								
								// Recorded the probability of reaching sample task 157 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!fixedFlag$sample123 && (1 < length$metric[sample]))) {
				// Looking for a path between Sample 104 and consumer Categorical 120.
				// Processing sample task 123 of consumer random variable null.
				// 
				// A local array to hold the accumulated distributions of the sample tasks for each
				// configuration of distributions.
				double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var120[threadID$cv$sample];
				
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$accumulatedConsumerDistributions[cv$i] = 0.0;
				
				// Zero an accumulator to track the probabilities reached.
				double cv$reachedDistributionProbability = 0.0;
				int var31 = st[sample][0];
				
				// Substituted "index$sample$67_2" with its value "sample".
				if(((0 <= var31) && (var31 < noStates))) {
					// Record the reached distribution.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					cv$reachedDistributionProbability = 1.0;
					
					// Add the current distribution to the distribution accumulator.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// cv$temp$22$$var3069's comment
					// 
					// $var3069's comment
					// Constructing a random variable input for use later.
					// 
					// cv$temp$21$var119's comment
					// Variable declaration of cv$temp$21$var119 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, 1.0, m[cv$valuePos], noStates);
				}
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "index$sample$67_2" with its value "sample".
				double[] cv$sampleDistribution = distribution$sample123[sample][0];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					double cv$normalisedDistValue = (cv$accumulatedConsumerDistributions[cv$i] / cv$reachedDistributionProbability);
					
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
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$stateProbabilityLocal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample104[sample];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		double cv$lseMax = cv$stateProbabilityLocal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
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
			for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 123 drawn from Categorical 120. Inference was performed using variable
	// marginalization.
	private final void sample123(int sample, int timeStep$var113, int threadID$cv$sample, Rng RNG$) {
		// Calculate the number of states to evaluate.
		int cv$numNumStates = 0;
		
		// Enumerating the possible arguments for Categorical 120.
		if((1 == timeStep$var113)) {
			// Enumerating the possible arguments for Categorical 120.
			if(fixedFlag$sample104) {
				int var31 = st[sample][0];
				
				// Substituted "timeStep$var113" with its value "1".
				if(((0 <= var31) && (var31 < noStates)))
					// variable marginalization
					// 
					// cv$numNumStates's comment
					// Calculate the number of states to evaluate.
					cv$numNumStates = Math.max(0, noStates);
			} else {
				// Enumerating the possible outputs of Categorical 101.
				if((0 < noStates)) {
					int var31 = st[sample][0];
					
					// Substituted "timeStep$var113" with its value "1".
					if(((0 <= var31) && (var31 < noStates)))
						// variable marginalization
						cv$numNumStates = noStates;
				}
			}
		}
		if(fixedFlag$sample123) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((2 <= timeStep$var113)) {
				int var31 = st[sample][(timeStep$var113 - 1)];
				if(((0 <= var31) && (var31 < noStates)))
					// variable marginalization
					cv$numNumStates = Math.max(cv$numNumStates, noStates);
			}
		} else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < noStates)) {
				int index$timeStep$14 = (timeStep$var113 - 1);
				
				// index$timeStep$1's comment
				// Exploring all the possible state counts for random variable 120.
				// 
				// Copy of index so that its values can be safely substituted
				// 
				// Substituted "index$timeStep$14" with its value "(timeStep$var113 - 1)".
				// 
				// Substituted "index$timeStep$14" with its value "(timeStep$var113 - 1)".
				// 
				// Substituted "index$timeStep$14" with its value "(timeStep$var113 - 1)".
				// 
				// Substituted "index$timeStep$14" with its value "(timeStep$var113 - 1)".
				if(((1 <= index$timeStep$14) && !(index$timeStep$14 == timeStep$var113))) {
					int var31 = st[sample][(timeStep$var113 - 1)];
					if(((0 <= var31) && (var31 < noStates)))
						// variable marginalization
						cv$numNumStates = Math.max(cv$numNumStates, noStates);
				}
			}
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var121$stateProbabilityGlobal[threadID$cv$sample];
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 120 creating
			// sample task 123.
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Enumerating the possible arguments for Categorical 120.
			if((1 == timeStep$var113)) {
				// Enumerating the possible arguments for Categorical 120.
				if(fixedFlag$sample104) {
					int var31 = st[sample][0];
					
					// Substituted "timeStep$var113" with its value "1".
					if(((0 <= var31) && (var31 < noStates))) {
						// Record the reached probability density.
						// 
						// Initialize a counter to track the reached distributions.
						cv$reachedDistributionSourceRV = 1.0;
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						// 
						// cv$temp$1$$var3155's comment
						// 
						// $var3155's comment
						// Constructing a random variable input for use later.
						// 
						// cv$temp$0$var119's comment
						// Variable declaration of cv$temp$0$var119 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "timeStep$var113" with its value "1".
						double cv$accumulatedProbabilities = ((cv$valuePos < noStates)?Math.log(m[st[sample][0]][cv$valuePos]):Double.NEGATIVE_INFINITY);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 < length$metric[sample])) {
							{
								// Looking for a path between Sample 123 and consumer Bernoulli 140.
								// Processing sample task 145 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								
								// Substituted "timeStep$var136" with its value "1".
								int var82 = st[sample][1];
								
								// Substituted "index$sample$45_2" with its value "sample".
								if(((0 <= var82) && (var82 < noStates))) {
									// Variable declaration of cv$temp$8$var139 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									double cv$temp$8$var139 = metric_valid_bias[cv$valuePos];
									
									// Substituted "index$sample$45_2" with its value "sample".
									// 
									// Substituted "timeStep$var136" with its value "1".
									cv$accumulatedConsumerProbabilities = Math.log((metric_valid_g[sample][1]?cv$temp$8$var139:(1.0 - cv$temp$8$var139)));
									
									// Recorded the probability of reaching sample task 145 with the current configuration.
									// 
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									cv$consumerDistributionProbabilityAccumulator = 0.0;
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
							if(metric_valid_g[sample][1]) {
								// Processing random variable 150.
								// 
								// Looking for a path between Sample 123 and consumer Gaussian 150.
								// 
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								boolean[][] guard$sample123gaussian156 = guard$sample123gaussian156$global[threadID$cv$sample];
								
								// Set the flags to false
								// 
								// Substituted "timeStep$var136" with its value "1".
								guard$sample123gaussian156[sample][1] = false;
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
								if(!guard$sample123gaussian156[sample][1]) {
									// The body will execute, so should not be executed again
									// 
									// Substituted "timeStep$var136" with its value "1".
									guard$sample123gaussian156[sample][1] = true;
									
									// Processing sample task 157 of consumer random variable null.
									// 
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Constraints moved from conditionals in inner loops/scopes/etc.
									if((0 <= st[sample][1])) {
										// Enumerating the possible arguments for the variable Gaussian 150 which is consuming
										// the output of Sample task 123.
										// 
										// Substituted "timeStep$var136" with its value "1".
										int var50 = st[sample][1];
										
										// Substituted "index$sample$69_2" with its value "sample".
										if(((0 <= var50) && (var50 < noStates))) {
											// Variable declaration of cv$temp$15$var149 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Value of the variable at this index
											double cv$temp$15$var149 = metric_var[cv$valuePos];
											
											// Substituted "index$sample$69_2" with its value "sample".
											// 
											// Substituted "timeStep$var136" with its value "1".
											// 
											// cv$temp$14$var148's comment
											// Variable declaration of cv$temp$14$var148 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Value of the variable at this index
											cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var151[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$15$var149))) - (Math.log(cv$temp$15$var149) * 0.5));
											
											// Recorded the probability of reaching sample task 157 with the current configuration.
											// 
											// Set an accumulator to record the consumer distributions not seen. Initially set
											// to 1 as seen values will be deducted from this value.
											cv$consumerDistributionProbabilityAccumulator = 0.0;
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
								
								// Substituted "timeStep$var113" with its value "1".
								// 
								// Substituted "timeStep$var136" with its value "1".
								if(!guard$sample123gaussian156[sample][1]) {
									// The body will execute, so should not be executed again
									// 
									// Substituted "timeStep$var136" with its value "1".
									guard$sample123gaussian156[sample][1] = true;
									
									// Processing sample task 157 of consumer random variable null.
									// 
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Constraints moved from conditionals in inner loops/scopes/etc.
									if((0 <= st[sample][1])) {
										// Substituted "timeStep$var136" with its value "1".
										int var50 = st[sample][1];
										
										// Substituted "index$sample$73_2" with its value "sample".
										if(((0 <= var50) && (var50 < noStates))) {
											// Variable declaration of cv$temp$47$var149 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Enumerating the possible arguments for the variable Gaussian 150 which is consuming
											// the output of Sample task 123.
											// 
											// Value of the variable at this index
											double cv$temp$47$var149 = metric_var[cv$valuePos];
											
											// Substituted "index$sample$73_2" with its value "sample".
											// 
											// Substituted "timeStep$var136" with its value "1".
											// 
											// cv$temp$46$var148's comment
											// Variable declaration of cv$temp$46$var148 moved.
											// 
											// Constructing a random variable input for use later.
											// 
											// Enumerating the possible arguments for the variable Gaussian 150 which is consuming
											// the output of Sample task 123.
											// 
											// Value of the variable at this index
											cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var151[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$47$var149))) - (Math.log(cv$temp$47$var149) * 0.5));
											
											// Recorded the probability of reaching sample task 157 with the current configuration.
											// 
											// Set an accumulator to record the consumer distributions not seen. Initially set
											// to 1 as seen values will be deducted from this value.
											cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						}
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					// Enumerating the possible outputs of Categorical 101.
					for(int index$sample104$26 = 0; index$sample104$26 < noStates; index$sample104$26 += 1) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample$25" with its value "sample".
						double cv$probabilitySample104Value27 = distribution$sample104[sample][index$sample104$26];
						int var31 = st[sample][0];
						
						// Substituted "timeStep$var113" with its value "1".
						if(((0 <= var31) && (var31 < noStates))) {
							// Record the reached probability density.
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample104Value27);
							
							// An accumulator to allow the value for each distribution to be constructed before
							// it is added to the index probabilities.
							// 
							// Value of the variable at this index
							// 
							// cv$temp$3$$var3156's comment
							// 
							// $var3156's comment
							// Constructing a random variable input for use later.
							// 
							// cv$temp$2$var119's comment
							// Variable declaration of cv$temp$2$var119 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "timeStep$var113" with its value "1".
							double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample104Value27) + ((cv$valuePos < noStates)?Math.log(m[st[sample][0]][cv$valuePos]):Double.NEGATIVE_INFINITY));
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((1 < length$metric[sample])) {
								{
									// Looking for a path between Sample 123 and consumer Bernoulli 140.
									// Processing sample task 145 of consumer random variable null.
									// 
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Substituted "timeStep$var136" with its value "1".
									int var82 = st[sample][1];
									
									// Substituted "index$sample$46_2" with its value "sample".
									if(((0 <= var82) && (var82 < noStates))) {
										// Variable declaration of cv$temp$9$var139 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double cv$temp$9$var139 = metric_valid_bias[cv$valuePos];
										
										// Substituted "index$sample$46_2" with its value "sample".
										// 
										// Substituted "timeStep$var136" with its value "1".
										cv$accumulatedConsumerProbabilities = Math.log((metric_valid_g[sample][1]?cv$temp$9$var139:(1.0 - cv$temp$9$var139)));
										
										// Recorded the probability of reaching sample task 145 with the current configuration.
										// 
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										cv$consumerDistributionProbabilityAccumulator = 0.0;
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
								if(metric_valid_g[sample][1]) {
									// Processing random variable 150.
									// 
									// Looking for a path between Sample 123 and consumer Gaussian 150.
									// 
									// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									boolean[][] guard$sample123gaussian156 = guard$sample123gaussian156$global[threadID$cv$sample];
									
									// Set the flags to false
									// 
									// Substituted "timeStep$var136" with its value "1".
									guard$sample123gaussian156[sample][1] = false;
									
									// Constraints moved from conditionals in inner loops/scopes/etc.
									if(!guard$sample123gaussian156[sample][1]) {
										// The body will execute, so should not be executed again
										// 
										// Substituted "timeStep$var136" with its value "1".
										guard$sample123gaussian156[sample][1] = true;
										
										// Processing sample task 157 of consumer random variable null.
										// 
										// Set an accumulator to sum the probabilities for each possible configuration of
										// inputs.
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										
										// Constraints moved from conditionals in inner loops/scopes/etc.
										if((0 <= st[sample][1])) {
											// Enumerating the possible arguments for the variable Gaussian 150 which is consuming
											// the output of Sample task 123.
											// 
											// Substituted "timeStep$var136" with its value "1".
											int var50 = st[sample][1];
											
											// Substituted "index$sample$70_2" with its value "sample".
											if(((0 <= var50) && (var50 < noStates))) {
												// Variable declaration of cv$temp$23$var149 moved.
												// 
												// Constructing a random variable input for use later.
												// 
												// Value of the variable at this index
												double cv$temp$23$var149 = metric_var[cv$valuePos];
												
												// Substituted "index$sample$70_2" with its value "sample".
												// 
												// Substituted "timeStep$var136" with its value "1".
												// 
												// cv$temp$22$var148's comment
												// Variable declaration of cv$temp$22$var148 moved.
												// 
												// Constructing a random variable input for use later.
												// 
												// Value of the variable at this index
												cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var151[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$23$var149))) - (Math.log(cv$temp$23$var149) * 0.5));
												
												// Recorded the probability of reaching sample task 157 with the current configuration.
												// 
												// Set an accumulator to record the consumer distributions not seen. Initially set
												// to 1 as seen values will be deducted from this value.
												cv$consumerDistributionProbabilityAccumulator = 0.0;
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
									
									// Substituted "timeStep$var113" with its value "1".
									// 
									// Substituted "timeStep$var136" with its value "1".
									if(!guard$sample123gaussian156[sample][1]) {
										// The body will execute, so should not be executed again
										// 
										// Substituted "timeStep$var136" with its value "1".
										guard$sample123gaussian156[sample][1] = true;
										
										// Processing sample task 157 of consumer random variable null.
										// 
										// Set an accumulator to sum the probabilities for each possible configuration of
										// inputs.
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										
										// Constraints moved from conditionals in inner loops/scopes/etc.
										if((0 <= st[sample][1])) {
											// Substituted "timeStep$var136" with its value "1".
											int var50 = st[sample][1];
											
											// Substituted "index$sample$74_2" with its value "sample".
											if(((0 <= var50) && (var50 < noStates))) {
												// Variable declaration of cv$temp$55$var149 moved.
												// 
												// Constructing a random variable input for use later.
												// 
												// Enumerating the possible arguments for the variable Gaussian 150 which is consuming
												// the output of Sample task 123.
												// 
												// Enumerating the possible arguments for the variable Gaussian 150 which is consuming
												// the output of Sample task 123.
												// 
												// Value of the variable at this index
												double cv$temp$55$var149 = metric_var[cv$valuePos];
												
												// Substituted "index$sample$74_2" with its value "sample".
												// 
												// Substituted "timeStep$var136" with its value "1".
												// 
												// cv$temp$54$var148's comment
												// Variable declaration of cv$temp$54$var148 moved.
												// 
												// Constructing a random variable input for use later.
												// 
												// Enumerating the possible arguments for the variable Gaussian 150 which is consuming
												// the output of Sample task 123.
												// 
												// Enumerating the possible arguments for the variable Gaussian 150 which is consuming
												// the output of Sample task 123.
												// 
												// Value of the variable at this index
												cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var151[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$55$var149))) - (Math.log(cv$temp$55$var149) * 0.5));
												
												// Recorded the probability of reaching sample task 157 with the current configuration.
												// 
												// Set an accumulator to record the consumer distributions not seen. Initially set
												// to 1 as seen values will be deducted from this value.
												cv$consumerDistributionProbabilityAccumulator = 0.0;
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
					}
				}
			}
			int index$timeStep$34 = (timeStep$var113 - 1);
			
			// index$timeStep$22's comment
			// Copy of index so that its values can be safely substituted
			// 
			// Substituted "index$timeStep$34" with its value "(timeStep$var113 - 1)".
			// 
			// Substituted "index$timeStep$34" with its value "(timeStep$var113 - 1)".
			// 
			// Substituted "index$timeStep$34" with its value "(timeStep$var113 - 1)".
			// 
			// Substituted "index$timeStep$34" with its value "(timeStep$var113 - 1)".
			if(((1 <= index$timeStep$34) && !(index$timeStep$34 == timeStep$var113))) {
				// Enumerating the possible outputs of Categorical 120.
				for(int index$sample123$35 = 0; index$sample123$35 < noStates; index$sample123$35 += 1) {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample$33" with its value "sample".
					double cv$probabilitySample123Value36 = distribution$sample123[sample][(index$timeStep$34 - 1)][index$sample123$35];
					int var31 = st[sample][(timeStep$var113 - 1)];
					if(((0 <= var31) && (var31 < noStates))) {
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample123Value36);
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						// 
						// cv$temp$7$$var3158's comment
						// 
						// $var3158's comment
						// Constructing a random variable input for use later.
						// 
						// cv$temp$6$var119's comment
						// Variable declaration of cv$temp$6$var119 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample123Value36) + ((cv$valuePos < noStates)?Math.log(m[cv$valuePos][cv$valuePos]):Double.NEGATIVE_INFINITY));
						
						// Processing random variable 140.
						{
							// Looking for a path between Sample 123 and consumer Bernoulli 140.
							// Processing sample task 145 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var82 = st[sample][timeStep$var113];
							
							// Substituted "index$sample$48_2" with its value "sample".
							if(((0 <= var82) && (var82 < noStates))) {
								// Variable declaration of cv$temp$11$var139 moved.
								// 
								// Constructing a random variable input for use later.
								double cv$temp$11$var139 = metric_valid_bias[index$sample123$35];
								
								// Substituted "index$sample$48_2" with its value "sample".
								cv$accumulatedConsumerProbabilities = Math.log((metric_valid_g[sample][timeStep$var113]?cv$temp$11$var139:(1.0 - cv$temp$11$var139)));
								
								// Recorded the probability of reaching sample task 145 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						if(metric_valid_g[sample][timeStep$var113]) {
							// Looking for a path between Sample 123 and consumer Gaussian 150.
							// Guard to check that at most one copy of the code is executed for a given random
							// variable instance.
							boolean[][] guard$sample123gaussian156 = guard$sample123gaussian156$global[threadID$cv$sample];
							
							// Set the flags to false
							// 
							// Substituted "timeStep$var136" with its value "timeStep$var113".
							guard$sample123gaussian156[sample][timeStep$var113] = false;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!guard$sample123gaussian156[sample][timeStep$var113]) {
								// The body will execute, so should not be executed again
								// 
								// Substituted "timeStep$var136" with its value "timeStep$var113".
								guard$sample123gaussian156[sample][timeStep$var113] = true;
								
								// Processing sample task 157 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
								if((0 <= st[sample][timeStep$var113])) {
									// Enumerating the possible arguments for the variable Gaussian 150 which is consuming
									// the output of Sample task 123.
									int var50 = st[sample][timeStep$var113];
									
									// Substituted "index$sample$72_2" with its value "sample".
									if(((0 <= var50) && (var50 < noStates))) {
										// Variable declaration of cv$temp$39$var149 moved.
										// 
										// Constructing a random variable input for use later.
										double cv$temp$39$var149 = metric_var[index$sample123$35];
										
										// Substituted "index$sample$72_2" with its value "sample".
										// 
										// cv$temp$38$var148's comment
										// Variable declaration of cv$temp$38$var148 moved.
										// 
										// Constructing a random variable input for use later.
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var113] - metric_mean[index$sample123$35]) / Math.sqrt(cv$temp$39$var149))) - (Math.log(cv$temp$39$var149) * 0.5));
										
										// Recorded the probability of reaching sample task 157 with the current configuration.
										// 
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										cv$consumerDistributionProbabilityAccumulator = 0.0;
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
							if(!guard$sample123gaussian156[sample][timeStep$var113]) {
								// The body will execute, so should not be executed again
								// 
								// Substituted "timeStep$var136" with its value "timeStep$var113".
								guard$sample123gaussian156[sample][timeStep$var113] = true;
								
								// Processing sample task 157 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
								if((0 <= st[sample][timeStep$var113])) {
									int var50 = st[sample][timeStep$var113];
									
									// Substituted "index$sample$76_2" with its value "sample".
									if(((0 <= var50) && (var50 < noStates))) {
										// Variable declaration of cv$temp$71$var149 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 150 which is consuming
										// the output of Sample task 123.
										// 
										// Enumerating the possible arguments for the variable Gaussian 150 which is consuming
										// the output of Sample task 123.
										double cv$temp$71$var149 = metric_var[index$sample123$35];
										
										// Substituted "index$sample$76_2" with its value "sample".
										// 
										// cv$temp$70$var148's comment
										// Variable declaration of cv$temp$70$var148 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 150 which is consuming
										// the output of Sample task 123.
										// 
										// Enumerating the possible arguments for the variable Gaussian 150 which is consuming
										// the output of Sample task 123.
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var113] - metric_mean[index$sample123$35]) / Math.sqrt(cv$temp$71$var149))) - (Math.log(cv$temp$71$var149) * 0.5));
										
										// Recorded the probability of reaching sample task 157 with the current configuration.
										// 
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										cv$consumerDistributionProbabilityAccumulator = 0.0;
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
			int index$timeStep$269_3 = (timeStep$var113 + 1);
			if((index$timeStep$269_3 < length$metric[sample])) {
				// Processing sample task 123 of consumer random variable null.
				// A local array to hold the accumulated distributions of the sample tasks for each
				// configuration of distributions.
				double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var120[threadID$cv$sample];
				
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$accumulatedConsumerDistributions[cv$i] = 0.0;
				
				// Zero an accumulator to track the probabilities reached.
				double cv$reachedDistributionProbability = 0.0;
				int var31 = st[sample][(index$timeStep$269_3 - 1)];
				
				// Substituted "index$sample$269_2" with its value "sample".
				if(((0 <= var31) && (var31 < noStates))) {
					// Declare and zero an accumulator for tracking the reached source probability space.
					double scopeVariable$reachedSourceProbability = 0.0;
					
					// Enumerating the possible arguments for Categorical 120.
					if((1 == timeStep$var113)) {
						// Enumerating the possible arguments for Categorical 120.
						if(fixedFlag$sample104) {
							int index$var31$280_1 = st[sample][0];
							
							// Substituted "timeStep$var113" with its value "1".
							if(((0 <= index$var31$280_1) && (index$var31$280_1 < noStates)))
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							// Enumerating the possible outputs of Categorical 101.
							for(int index$sample104$276 = 0; index$sample104$276 < noStates; index$sample104$276 += 1) {
								int index$var31$281_1 = st[sample][0];
								
								// Substituted "timeStep$var113" with its value "1".
								if(((0 <= index$var31$281_1) && (index$var31$281_1 < noStates)))
									// Add the probability of this argument configuration.
									// 
									// cv$probabilitySample104Value277's comment
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "index$sample$275" with its value "sample".
									scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample104[sample][index$sample104$276]);
							}
						}
					}
					int index$timeStep$284 = (timeStep$var113 - 1);
					
					// index$timeStep$271's comment
					// Copy of index so that its values can be safely substituted
					// 
					// Substituted "index$timeStep$269_3" with its value "(timeStep$var113 + 1)".
					// 
					// Substituted "index$timeStep$269_3" with its value "(timeStep$var113 + 1)".
					// 
					// Substituted "index$timeStep$269_3" with its value "(timeStep$var113 + 1)".
					// 
					// Substituted "index$timeStep$269_3" with its value "(timeStep$var113 + 1)".
					if((((1 <= index$timeStep$284) && !(index$timeStep$284 == timeStep$var113)) && !(index$timeStep$284 == index$timeStep$269_3))) {
						// Enumerating the possible outputs of Categorical 120.
						for(int index$sample123$285 = 0; index$sample123$285 < noStates; index$sample123$285 += 1) {
							int index$var31$290_1 = st[sample][(timeStep$var113 - 1)];
							if(((0 <= index$var31$290_1) && (index$var31$290_1 < noStates)))
								// Add the probability of this argument configuration.
								// 
								// cv$probabilitySample123Value286's comment
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$sample$283" with its value "sample".
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample123[sample][(index$timeStep$284 - 1)][index$sample123$285]);
						}
					}
					
					// Record the reached distribution.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Zero an accumulator to track the probabilities reached.
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					
					// Add the current distribution to the distribution accumulator.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// cv$temp$77$$var3672's comment
					// 
					// $var3672's comment
					// Constructing a random variable input for use later.
					// 
					// cv$temp$76$var119's comment
					// Variable declaration of cv$temp$76$var119 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Processing random variable 120.
					// 
					// Looking for a path between Sample 123 and consumer Categorical 120.
					// 
					// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, scopeVariable$reachedSourceProbability, m[cv$valuePos], noStates);
				}
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "index$sample$269_2" with its value "sample".
				double[] cv$sampleDistribution = distribution$sample123[sample][(index$timeStep$269_3 - 1)];
				
				// The overlap of the distributions so far.
				double cv$overlap = 0.0;
				
				// Calculate the overlap for each element in the distribution
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					// Normalise the values in the calculated distribution
					double cv$normalisedDistValue = (cv$accumulatedConsumerDistributions[cv$i] / cv$reachedDistributionProbability);
					
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
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			
			// Save the calculated index value into the array of index value probabilities
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample123[sample][(timeStep$var113 - 1)];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The sum of all the probabilities in log space
		double cv$logSum;
		
		// Sum all the values
		// 
		// Initialise the max to the first element.
		double cv$lseMax = cv$stateProbabilityLocal[0];
		
		// Find max value.
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
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
			for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
			
			// Increment the value of the target, moving the value back into log space.
			// 
			// The sum of all the probabilities in log space
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 19 drawn from Dirichlet 18. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample19() {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var19$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample104) {
			// Processing random variable 101.
			for(int sample = 0; sample < noSamples; sample += 1)
				// Processing sample task 104 of consumer random variable null.
				// 
				// Increment the sample counter with the value sampled by sample task 104 of random
				// variable var101
				// 
				// A local reference to the scratch space.
				cv$var19$countGlobal[st[sample][0]] = (cv$var19$countGlobal[st[sample][0]] + 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			for(int sample = 0; sample < noSamples; sample += 1) {
				// Processing sample task 104 of consumer random variable null.
				// 
				// Merge the distribution probabilities into the count
				// 
				// Get the length of the array
				for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
					// A local reference to the scratch space.
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					cv$var19$countGlobal[cv$loopIndex] = (cv$var19$countGlobal[cv$loopIndex] + distribution$sample104[sample][cv$loopIndex]);
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var19$countGlobal, initialStateDistribution, noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 32 drawn from Dirichlet 20. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample32(int var31, int threadID$cv$var31, Rng RNG$) {
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var32$countGlobal[threadID$cv$var31];
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample123) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((var31 == st[sample][0]) && (1 < length$metric[sample]))) {
					if(fixedFlag$sample104)
						// Increment the sample counter with the value sampled by sample task 123 of random
						// variable var120
						// 
						// Substituted "timeStep$var113" with its value "1".
						cv$countLocal[st[sample][1]] = (cv$countLocal[st[sample][1]] + 1.0);
					else {
						// Enumerating the possible outputs of Categorical 101.
						for(int index$sample104$5 = 0; index$sample104$5 < noStates; index$sample104$5 += 1)
							// Increment the sample counter with the value sampled by sample task 123 of random
							// variable var120
							// 
							// Substituted "index$sample$4" with its value "sample".
							cv$countLocal[st[sample][1]] = (cv$countLocal[st[sample][1]] + distribution$sample104[sample][index$sample104$5]);
					}
				}
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var113 = 2; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
					if((var31 == st[sample][(timeStep$var113 - 1)]))
						// Processing sample task 123 of consumer random variable null.
						// 
						// Increment the sample counter with the value sampled by sample task 123 of random
						// variable var120
						cv$countLocal[st[sample][timeStep$var113]] = (cv$countLocal[st[sample][timeStep$var113]] + 1.0);
				}
			}
		}
		
		// Processing random variable 120.
		// 
		// Looking for a path between Sample 32 and consumer Categorical 120.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			for(int sample = 0; sample < noSamples; sample += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(((var31 == st[sample][0]) && (1 < length$metric[sample]))) {
					if(fixedFlag$sample104) {
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							// The probability of reaching the consumer with this set of consumer arguments
							// 
							// Add the probability of this argument configuration.
							// 
							// Declare and zero an accumulator for tracking the reached source probability space.
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample123[sample][0][cv$loopIndex]);
					} else {
						// Enumerating the possible outputs of Categorical 101.
						for(int index$sample104$42 = 0; index$sample104$42 < noStates; index$sample104$42 += 1) {
							// The probability of reaching the consumer with this set of consumer arguments
							// 
							// Substituted "index$sample$41" with its value "sample".
							// 
							// Add the probability of this argument configuration.
							// 
							// Declare and zero an accumulator for tracking the reached source probability space.
							double cv$distributionProbability = distribution$sample104[sample][index$sample104$42];
							
							// Merge the distribution probabilities into the count
							// 
							// Get the length of the array
							for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
								// Substituted "timeStep$var113" with its value "1".
								cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample123[sample][0][cv$loopIndex] * cv$distributionProbability));
						}
					}
				}
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
					if((var31 == st[sample][(timeStep$var113 - 1)])) {
						int index$timeStep$52 = (timeStep$var113 - 1);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						// 
						// Substituted "index$sample$51" with its value "sample".
						if((1 <= index$timeStep$52)) {
							// Enumerating the possible outputs of Categorical 120.
							for(int index$sample123$53 = 0; index$sample123$53 < noStates; index$sample123$53 += 1) {
								// The probability of reaching the consumer with this set of consumer arguments
								// 
								// Substituted "index$sample$51" with its value "sample".
								// 
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								double cv$distributionProbability = distribution$sample123[sample][(index$timeStep$52 - 1)][index$sample123$53];
								
								// Merge the distribution probabilities into the count
								// 
								// Get the length of the array
								for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
									cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample123[sample][(timeStep$var113 - 1)][cv$loopIndex] * cv$distributionProbability));
							}
						}
					}
				}
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var31], noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 52 drawn from Uniform 39. Inference was performed using Metropolis-Hastings.
	private final void sample52(int var50, int threadID$cv$var50, Rng RNG$) {
		// The original value of the sample
		double cv$originalValue = metric_mean[var50];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$var37" with its value "0.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue < 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY);
			
			// Processing random variable 150.
			// 
			// Looking for a path between Sample 52 and consumer Gaussian 150.
			for(int sample = 0; sample < noSamples; sample += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((((var50 == st[sample][0]) && (0 < length$metric[sample])) && metric_valid_g[sample][0])) {
					if(fixedFlag$sample104) {
						// Processing sample task 157 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var66 = st[sample][0];
						
						// Substituted "timeStep$var136" with its value "0".
						if(((0 <= var66) && (var66 < noStates))) {
							// Variable declaration of cv$temp$3$var149 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "timeStep$var136" with its value "0".
							double cv$temp$3$var149 = metric_var[st[sample][0]];
							
							// Substituted "timeStep$var136" with its value "0".
							// 
							// cv$temp$2$var148's comment
							// Variable declaration of cv$temp$2$var148 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var151[sample][0] - cv$originalValue) / Math.sqrt(cv$temp$3$var149))) - (Math.log(cv$temp$3$var149) * 0.5));
							
							// Recorded the probability of reaching sample task 157 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
					} else {
						// Enumerating the possible outputs of Categorical 101.
						for(int index$sample104$6 = 0; index$sample104$6 < noStates; index$sample104$6 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$sample$5" with its value "sample".
							double cv$probabilitySample104Value7 = distribution$sample104[sample][index$sample104$6];
							
							// Processing sample task 157 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Enumerating the possible arguments for the variable Gaussian 150 which is consuming
							// the output of Sample task 52.
							int var66 = st[sample][0];
							
							// Substituted "timeStep$var136" with its value "0".
							if(((0 <= var66) && (var66 < noStates))) {
								// Variable declaration of cv$temp$9$var149 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "timeStep$var136" with its value "0".
								double cv$temp$9$var149 = metric_var[st[sample][0]];
								
								// Substituted "timeStep$var136" with its value "0".
								// 
								// cv$temp$8$var148's comment
								// Variable declaration of cv$temp$8$var148 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample104Value7) + DistributionSampling.logProbabilityGaussian(((var151[sample][0] - cv$originalValue) / Math.sqrt(cv$temp$9$var149)))) - (Math.log(cv$temp$9$var149) * 0.5));
								
								// Recorded the probability of reaching sample task 157 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample104Value7);
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
				}
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 1; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if((metric_valid_g[sample][timeStep$var136] && (var50 == st[sample][timeStep$var136]))) {
						if(fixedFlag$sample123) {
							// Processing sample task 157 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var66 = st[sample][timeStep$var136];
							if(((0 <= var66) && (var66 < noStates))) {
								// Variable declaration of cv$temp$21$var149 moved.
								// 
								// Constructing a random variable input for use later.
								double cv$temp$21$var149 = metric_var[st[sample][timeStep$var136]];
								
								// cv$temp$20$var148's comment
								// Variable declaration of cv$temp$20$var148 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - cv$originalValue) / Math.sqrt(cv$temp$21$var149))) - (Math.log(cv$temp$21$var149) * 0.5));
								
								// Recorded the probability of reaching sample task 157 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						} else {
							// Enumerating the possible outputs of Categorical 120.
							for(int index$sample123$17 = 0; index$sample123$17 < noStates; index$sample123$17 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$sample$15" with its value "sample".
								double cv$probabilitySample123Value18 = distribution$sample123[sample][(timeStep$var136 - 1)][index$sample123$17];
								
								// Processing sample task 157 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var66 = st[sample][timeStep$var136];
								if(((0 <= var66) && (var66 < noStates))) {
									// Variable declaration of cv$temp$27$var149 moved.
									// 
									// Constructing a random variable input for use later.
									double cv$temp$27$var149 = metric_var[st[sample][timeStep$var136]];
									
									// cv$temp$26$var148's comment
									// Variable declaration of cv$temp$26$var148 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample123Value18) + DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - cv$originalValue) / Math.sqrt(cv$temp$27$var149)))) - (Math.log(cv$temp$27$var149) * 0.5));
									
									// Recorded the probability of reaching sample task 157 with the current configuration.
									// 
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample123Value18);
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
					}
				}
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Update Sample and intermediate values
		// 
		// Guards to ensure that metric_mean is only updated when there is a valid path.
		metric_mean[var50] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$var37" with its value "0.0".
		double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue < 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY);
		
		// Processing random variable 150.
		// 
		// Looking for a path between Sample 52 and consumer Gaussian 150.
		for(int sample = 0; sample < noSamples; sample += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((((var50 == st[sample][0]) && (0 < length$metric[sample])) && metric_valid_g[sample][0])) {
				if(fixedFlag$sample104) {
					// Processing sample task 157 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var66 = st[sample][0];
					
					// Substituted "timeStep$var136" with its value "0".
					if(((0 <= var66) && (var66 < noStates))) {
						// Variable declaration of cv$temp$3$var149 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "timeStep$var136" with its value "0".
						double cv$temp$3$var149 = metric_var[st[sample][0]];
						
						// Substituted "timeStep$var136" with its value "0".
						// 
						// cv$temp$2$var148's comment
						// Variable declaration of cv$temp$2$var148 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var151[sample][0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var149))) - (Math.log(cv$temp$3$var149) * 0.5));
						
						// Recorded the probability of reaching sample task 157 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					// Enumerating the possible outputs of Categorical 101.
					for(int index$sample104$6 = 0; index$sample104$6 < noStates; index$sample104$6 += 1) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample$5" with its value "sample".
						double cv$probabilitySample104Value7 = distribution$sample104[sample][index$sample104$6];
						
						// Processing sample task 157 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 150 which is consuming
						// the output of Sample task 52.
						int var66 = st[sample][0];
						
						// Substituted "timeStep$var136" with its value "0".
						if(((0 <= var66) && (var66 < noStates))) {
							// Variable declaration of cv$temp$9$var149 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "timeStep$var136" with its value "0".
							double cv$temp$9$var149 = metric_var[st[sample][0]];
							
							// Substituted "timeStep$var136" with its value "0".
							// 
							// cv$temp$8$var148's comment
							// Variable declaration of cv$temp$8$var148 moved.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample104Value7) + DistributionSampling.logProbabilityGaussian(((var151[sample][0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var149)))) - (Math.log(cv$temp$9$var149) * 0.5));
							
							// Recorded the probability of reaching sample task 157 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample104Value7);
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
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var136 = 1; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
				if((metric_valid_g[sample][timeStep$var136] && (var50 == st[sample][timeStep$var136]))) {
					if(fixedFlag$sample123) {
						// Processing sample task 157 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var66 = st[sample][timeStep$var136];
						if(((0 <= var66) && (var66 < noStates))) {
							// Variable declaration of cv$temp$21$var149 moved.
							// 
							// Constructing a random variable input for use later.
							double cv$temp$21$var149 = metric_var[st[sample][timeStep$var136]];
							
							// cv$temp$20$var148's comment
							// Variable declaration of cv$temp$20$var148 moved.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - cv$proposedValue) / Math.sqrt(cv$temp$21$var149))) - (Math.log(cv$temp$21$var149) * 0.5));
							
							// Recorded the probability of reaching sample task 157 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
					} else {
						// Enumerating the possible outputs of Categorical 120.
						for(int index$sample123$17 = 0; index$sample123$17 < noStates; index$sample123$17 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$sample$15" with its value "sample".
							double cv$probabilitySample123Value18 = distribution$sample123[sample][(timeStep$var136 - 1)][index$sample123$17];
							
							// Processing sample task 157 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var66 = st[sample][timeStep$var136];
							if(((0 <= var66) && (var66 < noStates))) {
								// Variable declaration of cv$temp$27$var149 moved.
								// 
								// Constructing a random variable input for use later.
								double cv$temp$27$var149 = metric_var[st[sample][timeStep$var136]];
								
								// cv$temp$26$var148's comment
								// Variable declaration of cv$temp$26$var148 moved.
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample123Value18) + DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - cv$proposedValue) / Math.sqrt(cv$temp$27$var149)))) - (Math.log(cv$temp$27$var149) * 0.5));
								
								// Recorded the probability of reaching sample task 157 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample123Value18);
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
				}
			}
		}
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// The probability ration for the proposed value and the current value.
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			// If it is not revert the changes.
			// 
			// Set the sample value
			// 
			// Guards to ensure that metric_mean is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			metric_mean[var50] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 68 drawn from InverseGamma 55. Inference was performed using Metropolis-Hastings.
	private final void sample68(int var66, int threadID$cv$var66, Rng RNG$) {
		// The original value of the sample
		double cv$originalValue = metric_var[var66];
		
		// This value is not used before it is set again, so removing the value declaration.
		// 
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		
		// Ensure the variance is at least 0.01
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$1$var54" with its value "1.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 1.0, 1.0);
			
			// Processing random variable 150.
			// 
			// Looking for a path between Sample 68 and consumer Gaussian 150.
			for(int sample = 0; sample < noSamples; sample += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((((var66 == st[sample][0]) && (0 < length$metric[sample])) && metric_valid_g[sample][0])) {
					if(fixedFlag$sample104) {
						// Processing sample task 157 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var50 = st[sample][0];
						
						// Substituted "timeStep$var136" with its value "0".
						if(((0 <= var50) && (var50 < noStates))) {
							// Substituted "timeStep$var136" with its value "0".
							// 
							// cv$temp$2$var148's comment
							// Variable declaration of cv$temp$2$var148 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "timeStep$var136" with its value "0".
							// 
							// cv$temp$3$var149's comment
							// Variable declaration of cv$temp$3$var149 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var151[sample][0] - metric_mean[st[sample][0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
							
							// Recorded the probability of reaching sample task 157 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
					} else {
						// Enumerating the possible outputs of Categorical 101.
						for(int index$sample104$6 = 0; index$sample104$6 < noStates; index$sample104$6 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$sample$5" with its value "sample".
							double cv$probabilitySample104Value7 = distribution$sample104[sample][index$sample104$6];
							
							// Processing sample task 157 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Enumerating the possible arguments for the variable Gaussian 150 which is consuming
							// the output of Sample task 68.
							int var50 = st[sample][0];
							
							// Substituted "timeStep$var136" with its value "0".
							if(((0 <= var50) && (var50 < noStates))) {
								// Substituted "timeStep$var136" with its value "0".
								// 
								// cv$temp$8$var148's comment
								// Variable declaration of cv$temp$8$var148 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "timeStep$var136" with its value "0".
								// 
								// cv$temp$9$var149's comment
								// Variable declaration of cv$temp$9$var149 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample104Value7) + DistributionSampling.logProbabilityGaussian(((var151[sample][0] - metric_mean[st[sample][0]]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
								
								// Recorded the probability of reaching sample task 157 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample104Value7);
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
				}
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 1; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if((metric_valid_g[sample][timeStep$var136] && (var66 == st[sample][timeStep$var136]))) {
						if(fixedFlag$sample123) {
							// Processing sample task 157 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var50 = st[sample][timeStep$var136];
							if(((0 <= var50) && (var50 < noStates))) {
								// cv$temp$21$var149's comment
								// Variable declaration of cv$temp$21$var149 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - metric_mean[st[sample][timeStep$var136]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
								
								// Recorded the probability of reaching sample task 157 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						} else {
							// Enumerating the possible outputs of Categorical 120.
							for(int index$sample123$17 = 0; index$sample123$17 < noStates; index$sample123$17 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$sample$15" with its value "sample".
								double cv$probabilitySample123Value18 = distribution$sample123[sample][(timeStep$var136 - 1)][index$sample123$17];
								
								// Processing sample task 157 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var50 = st[sample][timeStep$var136];
								if(((0 <= var50) && (var50 < noStates))) {
									// cv$temp$27$var149's comment
									// Variable declaration of cv$temp$27$var149 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample123Value18) + DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - metric_mean[st[sample][timeStep$var136]]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
									
									// Recorded the probability of reaching sample task 157 with the current configuration.
									// 
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample123Value18);
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
					}
				}
			}
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		
		// Update Sample and intermediate values
		// 
		// Guards to ensure that metric_var is only updated when there is a valid path.
		metric_var[var66] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var54" with its value "1.0".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 1.0, 1.0);
		
		// Processing random variable 150.
		// 
		// Looking for a path between Sample 68 and consumer Gaussian 150.
		for(int sample = 0; sample < noSamples; sample += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((((var66 == st[sample][0]) && (0 < length$metric[sample])) && metric_valid_g[sample][0])) {
				if(fixedFlag$sample104) {
					// Processing sample task 157 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var50 = st[sample][0];
					
					// Substituted "timeStep$var136" with its value "0".
					if(((0 <= var50) && (var50 < noStates))) {
						// Substituted "timeStep$var136" with its value "0".
						// 
						// cv$temp$2$var148's comment
						// Variable declaration of cv$temp$2$var148 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "timeStep$var136" with its value "0".
						// 
						// cv$temp$3$var149's comment
						// Variable declaration of cv$temp$3$var149 moved.
						// 
						// Constructing a random variable input for use later.
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var151[sample][0] - metric_mean[st[sample][0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
						
						// Recorded the probability of reaching sample task 157 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					// Enumerating the possible outputs of Categorical 101.
					for(int index$sample104$6 = 0; index$sample104$6 < noStates; index$sample104$6 += 1) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample$5" with its value "sample".
						double cv$probabilitySample104Value7 = distribution$sample104[sample][index$sample104$6];
						
						// Processing sample task 157 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Enumerating the possible arguments for the variable Gaussian 150 which is consuming
						// the output of Sample task 68.
						int var50 = st[sample][0];
						
						// Substituted "timeStep$var136" with its value "0".
						if(((0 <= var50) && (var50 < noStates))) {
							// Substituted "timeStep$var136" with its value "0".
							// 
							// cv$temp$8$var148's comment
							// Variable declaration of cv$temp$8$var148 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "timeStep$var136" with its value "0".
							// 
							// cv$temp$9$var149's comment
							// Variable declaration of cv$temp$9$var149 moved.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample104Value7) + DistributionSampling.logProbabilityGaussian(((var151[sample][0] - metric_mean[st[sample][0]]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
							
							// Recorded the probability of reaching sample task 157 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample104Value7);
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
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var136 = 1; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
				if((metric_valid_g[sample][timeStep$var136] && (var66 == st[sample][timeStep$var136]))) {
					if(fixedFlag$sample123) {
						// Processing sample task 157 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var50 = st[sample][timeStep$var136];
						if(((0 <= var50) && (var50 < noStates))) {
							// cv$temp$21$var149's comment
							// Variable declaration of cv$temp$21$var149 moved.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - metric_mean[st[sample][timeStep$var136]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
							
							// Recorded the probability of reaching sample task 157 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
					} else {
						// Enumerating the possible outputs of Categorical 120.
						for(int index$sample123$17 = 0; index$sample123$17 < noStates; index$sample123$17 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$sample$15" with its value "sample".
							double cv$probabilitySample123Value18 = distribution$sample123[sample][(timeStep$var136 - 1)][index$sample123$17];
							
							// Processing sample task 157 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var50 = st[sample][timeStep$var136];
							if(((0 <= var50) && (var50 < noStates))) {
								// cv$temp$27$var149's comment
								// Variable declaration of cv$temp$27$var149 moved.
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample123Value18) + DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - metric_mean[st[sample][timeStep$var136]]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
								
								// Recorded the probability of reaching sample task 157 with the current configuration.
								// 
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample123Value18);
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
				}
			}
		}
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		// 
		// The probability ration for the proposed value and the current value.
		// 
		// Initialize a log space accumulator to take the product of all the distribution
		// probabilities.
		// 
		// Record the reached probability density.
		// 
		// Initialize a counter to track the reached distributions.
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			// If it is not revert the changes.
			// 
			// Set the sample value
			// 
			// Guards to ensure that metric_var is only updated when there is a valid path.
			// 
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			metric_var[var66] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 84 drawn from Beta 71. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample84(int var82, int threadID$cv$var82, Rng RNG$) {
		// Local variable to record the number of true samples.
		double cv$sum = 0.0;
		
		// Local variable to record the number of samples.
		double cv$count = 0.0;
		
		// Processing random variable 140.
		// 
		// Looking for a path between Sample 84 and consumer Bernoulli 140.
		for(int sample = 0; sample < noSamples; sample += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(((var82 == st[sample][0]) && (0 < length$metric[sample]))) {
				if(fixedFlag$sample104) {
					// Processing sample task 145 of consumer random variable null.
					// 
					// Include the value sampled by task 145 from random variable var140.
					// 
					// Increment the number of samples.
					cv$count = (cv$count + 1.0);
					
					// If the sample value was positive increase the count
					// 
					// Substituted "timeStep$var136" with its value "0".
					if(metric_valid_g[sample][0])
						cv$sum = (cv$sum + 1.0);
				} else {
					// Enumerating the possible outputs of Categorical 101.
					for(int index$sample104$5 = 0; index$sample104$5 < noStates; index$sample104$5 += 1) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample$4" with its value "sample".
						double cv$probabilitySample104Value6 = distribution$sample104[sample][index$sample104$5];
						
						// Processing sample task 145 of consumer random variable null.
						// 
						// Include the value sampled by task 145 from random variable var140.
						// 
						// Increment the number of samples.
						cv$count = (cv$count + cv$probabilitySample104Value6);
						
						// If the sample value was positive increase the count
						// 
						// Substituted "timeStep$var136" with its value "0".
						if(metric_valid_g[sample][0])
							cv$sum = (cv$sum + cv$probabilitySample104Value6);
					}
				}
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var136 = 1; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
				if((var82 == st[sample][timeStep$var136])) {
					if(fixedFlag$sample123) {
						// Processing sample task 145 of consumer random variable null.
						// 
						// Include the value sampled by task 145 from random variable var140.
						// 
						// Increment the number of samples.
						cv$count = (cv$count + 1.0);
						
						// If the sample value was positive increase the count
						if(metric_valid_g[sample][timeStep$var136])
							cv$sum = (cv$sum + 1.0);
					} else {
						// Enumerating the possible outputs of Categorical 120.
						for(int index$sample123$16 = 0; index$sample123$16 < noStates; index$sample123$16 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$sample$14" with its value "sample".
							double cv$probabilitySample123Value17 = distribution$sample123[sample][(timeStep$var136 - 1)][index$sample123$16];
							
							// Processing sample task 145 of consumer random variable null.
							// 
							// Include the value sampled by task 145 from random variable var140.
							// 
							// Increment the number of samples.
							cv$count = (cv$count + cv$probabilitySample123Value17);
							
							// If the sample value was positive increase the count
							if(metric_valid_g[sample][timeStep$var136])
								cv$sum = (cv$sum + cv$probabilitySample123Value17);
						}
					}
				}
			}
		}
		
		// Guards to ensure that metric_valid_bias is only updated when there is a valid path.
		// 
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		metric_valid_bias[var82] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var19$countGlobal
		// 
		// Allocation of cv$var19$countGlobal for single threaded execution
		cv$var19$countGlobal = new double[noStates];
		
		// Constructor for cv$var32$countGlobal
		{
			// Allocation of cv$var32$countGlobal for multithreaded execution
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var32$countGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var32$countGlobal[cv$index] = new double[noStates];
		}
		
		// Constructor for cv$distributionAccumulator$var120
		{
			// Allocation of cv$distributionAccumulator$var120 for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$distributionAccumulator$var120 = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				// Variable to record the maximum value of Task Get 121. Initially set to the value
				// of putTask 33.
				cv$distributionAccumulator$var120[cv$index] = new double[noStates];
		}
		
		// Constructor for cv$var102$stateProbabilityGlobal
		{
			// Allocation of cv$var102$stateProbabilityGlobal for multithreaded execution
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var102$stateProbabilityGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var102$stateProbabilityGlobal[cv$index] = new double[noStates];
		}
		
		// Constructor for guard$sample104gaussian156$global
		{
			// Calculate the largest index of timeStep that is possible and allocate an array
			// to hold the guard for each of these.
			int cv$max_timeStep$var136 = 0;
			for(int sample = 0; sample < length$metric.length; sample += 1)
				cv$max_timeStep$var136 = Math.max(cv$max_timeStep$var136, length$metric[sample]);
			
			// Variable declaration of cv$max_sample moved.
			// Declaration comment was:
			// Calculate the largest index of sample that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_sample = length$metric.length;
			
			// Allocation of guard$sample104gaussian156$global for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			guard$sample104gaussian156$global = new boolean[cv$threadCount][][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				guard$sample104gaussian156$global[cv$index] = new boolean[cv$max_sample][cv$max_timeStep$var136];
		}
		
		// Constructor for cv$var121$stateProbabilityGlobal
		{
			// Allocation of cv$var121$stateProbabilityGlobal for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var121$stateProbabilityGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				// Variable to record the maximum value of Task Get 121. Initially set to the value
				// of putTask 33.
				cv$var121$stateProbabilityGlobal[cv$index] = new double[noStates];
		}
		
		// Constructor for guard$sample123gaussian156$global
		// 
		// Calculate the largest index of timeStep that is possible and allocate an array
		// to hold the guard for each of these.
		int cv$max_timeStep$var136 = 0;
		for(int sample = 0; sample < length$metric.length; sample += 1)
			cv$max_timeStep$var136 = Math.max(cv$max_timeStep$var136, length$metric[sample]);
		
		// Variable declaration of cv$max_sample moved.
		// Declaration comment was:
		// Calculate the largest index of sample that is possible and allocate an array to
		// hold the guard for each of these.
		int cv$max_sample = length$metric.length;
		
		// Allocation of guard$sample123gaussian156$global for multithreaded execution
		// 
		// Get the thread count.
		int cv$threadCount = threadCount();
		
		// Allocate an array to hold a copy per thread
		guard$sample123gaussian156$global = new boolean[cv$threadCount][][];
		
		// Populate the array with a copy per thread
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			guard$sample123gaussian156$global[cv$index] = new boolean[cv$max_sample][cv$max_timeStep$var136];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for v
		v = new double[noStates];
		
		// If initialStateDistribution has not been set already allocate space.
		if(!fixedFlag$sample19)
			// Constructor for initialStateDistribution
			initialStateDistribution = new double[noStates];
		
		// If m has not been set already allocate space.
		if(!fixedFlag$sample32) {
			// Constructor for m
			m = new double[noStates][];
			for(int var31 = 0; var31 < noStates; var31 += 1)
				m[var31] = new double[noStates];
		}
		
		// If st has not been set already allocate space.
		if((!fixedFlag$sample104 || !fixedFlag$sample123)) {
			// Constructor for st
			st = new int[length$metric.length][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				st[sample] = new int[length$metric[sample]];
		}
		
		// Constructor for metric_g
		metric_g = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			metric_g[sample] = new double[length$metric[sample]];
		
		// Constructor for metric_valid_g
		metric_valid_g = new boolean[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			metric_valid_g[sample] = new boolean[length$metric[sample]];
		
		// If metric_mean has not been set already allocate space.
		if(!fixedFlag$sample52)
			// Constructor for metric_mean
			metric_mean = new double[noStates];
		
		// If metric_var has not been set already allocate space.
		if(!fixedFlag$sample68)
			// Constructor for metric_var
			metric_var = new double[noStates];
		
		// If metric_valid_bias has not been set already allocate space.
		if(!fixedFlag$sample84)
			// Constructor for metric_valid_bias
			metric_valid_bias = new double[noStates];
		
		// Constructor for var151
		var151 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			var151[sample] = new double[length$metric[sample]];
		
		// Constructor for distribution$sample104
		distribution$sample104 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			distribution$sample104[sample] = new double[noStates];
		
		// Constructor for distribution$sample123
		distribution$sample123 = new double[length$metric.length][][];
		for(int sample = 0; sample < length$metric.length; sample += 1) {
			double[][] subarray$0 = new double[(length$metric[sample] - 1)][];
			distribution$sample123[sample] = subarray$0;
			for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1)
				subarray$0[(timeStep$var113 - 1)] = new double[noStates];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample32)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var31]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample52)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1)
							metric_mean[var50] = (DistributionSampling.sampleUniform(RNG$1) * 100.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample68)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1)
							metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample84)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1)
							metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$index$sample, int forEnd$index$sample, int threadID$index$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$sample = forStart$index$sample; index$sample < forEnd$index$sample; index$sample += 1) {
						int sample = index$sample;
						int threadID$sample = threadID$index$sample;
						if(!fixedFlag$sample104)
							st[sample][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution, noStates);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample123) {
							int[] var114 = st[sample];
							for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1)
								var114[timeStep$var113] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample][(timeStep$var113 - 1)]], noStates);
						}
						boolean[] metric_valid_1d = metric_valid_g[sample];
						double[] metric_1d = metric_g[sample];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, length$metric[sample], 1,
							(int forStart$timeStep$var136, int forEnd$timeStep$var136, int threadID$timeStep$var136, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int timeStep$var136 = forStart$timeStep$var136; timeStep$var136 < forEnd$timeStep$var136; timeStep$var136 += 1) {
										metric_valid_1d[timeStep$var136] = DistributionSampling.sampleBernoulli(RNG$2, metric_valid_bias[st[sample][timeStep$var136]]);
										if(metric_valid_1d[timeStep$var136]) {
											var151[sample][timeStep$var136] = ((Math.sqrt(metric_var[st[sample][timeStep$var136]]) * DistributionSampling.sampleGaussian(RNG$2)) + metric_mean[st[sample][timeStep$var136]]);
											metric_1d[timeStep$var136] = var151[sample][timeStep$var136];
										}
									}
							}
						);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample32)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var31]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample52)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1)
							metric_mean[var50] = (DistributionSampling.sampleUniform(RNG$1) * 100.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample68)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1)
							metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample84)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1)
							metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample104) {
							// Create local copy of variable probabilities.
							double[] cv$distribution$sample104 = distribution$sample104[sample];
							for(int index$var101 = 0; index$var101 < noStates; index$var101 += 1)
								// Save the probability of each value
								// 
								// Probability for this value
								cv$distribution$sample104[index$var101] = initialStateDistribution[index$var101];
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample123) {
							for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
								// Create local copy of variable probabilities.
								double[] cv$distribution$sample123 = distribution$sample123[sample][(timeStep$var113 - 1)];
								for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1)
									// Zero the probability of each value
									cv$distribution$sample123[index$var120] = 0.0;
								
								// Iterate through possible values for var120's arguments.
								// 
								// Enumerating the possible arguments for Categorical 120.
								if((1 == timeStep$var113)) {
									// Iterate through possible values for var120's arguments.
									// 
									// Enumerating the possible arguments for Categorical 120.
									if(fixedFlag$sample104) {
										int var31 = st[sample][0];
										
										// Substituted "timeStep$var113" with its value "1".
										if(((0 <= var31) && (var31 < noStates))) {
											// Substituted "timeStep$var113" with its value "1".
											double[] var119 = m[st[sample][0]];
											for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1)
												// Save the probability of each value
												cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + var119[index$var120]);
										}
									} else {
										// Enumerating the possible outputs of Categorical 101.
										for(int index$sample104$3 = 0; index$sample104$3 < noStates; index$sample104$3 += 1) {
											// Update the probability of sampling this value from the distribution value.
											// 
											// Substituted "index$sample$2" with its value "sample".
											double cv$probabilitySample104Value4 = distribution$sample104[sample][index$sample104$3];
											int var31 = st[sample][0];
											
											// Substituted "timeStep$var113" with its value "1".
											if(((0 <= var31) && (var31 < noStates))) {
												// Substituted "timeStep$var113" with its value "1".
												double[] var119 = m[st[sample][0]];
												for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1)
													// Save the probability of each value
													cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (cv$probabilitySample104Value4 * var119[index$var120]));
											}
										}
									}
								}
								int index$timeStep$11 = (timeStep$var113 - 1);
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
								// 
								// Substituted "index$sample$10" with its value "sample".
								if((1 <= index$timeStep$11)) {
									// Enumerating the possible outputs of Categorical 120.
									for(int index$sample123$12 = 0; index$sample123$12 < noStates; index$sample123$12 += 1) {
										// Update the probability of sampling this value from the distribution value.
										// 
										// Substituted "index$sample$10" with its value "sample".
										double cv$probabilitySample123Value13 = distribution$sample123[sample][(index$timeStep$11 - 1)][index$sample123$12];
										int var31 = st[sample][(timeStep$var113 - 1)];
										if(((0 <= var31) && (var31 < noStates))) {
											double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
											for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1)
												// Save the probability of each value
												cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (cv$probabilitySample123Value13 * var119[index$var120]));
										}
									}
								}
								
								// Sum the values in the array
								double cv$var120$sum = 0.0;
								for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1)
									// sum the probability of each value
									cv$var120$sum = (cv$var120$sum + cv$distribution$sample123[index$var120]);
								for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1)
									// Normalise the probability of each value
									cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] / cv$var120$sum);
							}
						}
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample32)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var31]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample52)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1)
							metric_mean[var50] = (DistributionSampling.sampleUniform(RNG$1) * 100.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample68)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1)
							metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample84)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1)
							metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
						if(!fixedFlag$sample104)
							st[sample][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution, noStates);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample123) {
							int[] var114 = st[sample];
							for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1)
								var114[timeStep$var113] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample][(timeStep$var113 - 1)]], noStates);
						}
					}
			}
		);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample19)
				sample19();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample32)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
								sample32(var31, threadID$var31, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample52)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1)
								sample52(var50, threadID$var50, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample68)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1)
								sample68(var66, threadID$var66, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample84)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1)
								sample84(var82, threadID$var82, RNG$1);
					}
				);

			
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
							if(!fixedFlag$sample104)
								sample104(sample, threadID$sample, RNG$1);
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!fixedFlag$sample123) {
								for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1)
									sample123(sample, timeStep$var113, threadID$sample, RNG$1);
							}
						}
				}
			);
		}
		// Infer the samples in reverse chronological order.
		else {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!fixedFlag$sample123) {
								for(int timeStep$var113 = (length$metric[sample] - 1); timeStep$var113 >= 1; timeStep$var113 -= 1)
									sample123(sample, timeStep$var113, threadID$sample, RNG$1);
							}
							if(!fixedFlag$sample104)
								sample104(sample, threadID$sample, RNG$1);
						}
				}
			);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample84)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1)
								sample84(var82, threadID$var82, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample68)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1)
								sample68(var66, threadID$var66, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample52)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1)
								sample52(var50, threadID$var50, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample32)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
								sample32(var31, threadID$var31, RNG$1);
					}
				);

			if(!fixedFlag$sample19)
				sample19();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		noSamples = length$metric.length;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1)
						v[var15] = 0.1;
			}
		);
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
		if(!fixedProbFlag$sample19)
			logProbability$initialStateDistribution = 0.0;
		logProbability$var20 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample32)
			logProbability$var32 = 0.0;
		logProbability$var39 = 0.0;
		logProbability$metric_mean = 0.0;
		if(!fixedProbFlag$sample52)
			logProbability$var51 = 0.0;
		logProbability$var55 = 0.0;
		logProbability$metric_var = 0.0;
		if(!fixedProbFlag$sample68)
			logProbability$var67 = 0.0;
		logProbability$var71 = 0.0;
		logProbability$metric_valid_bias = 0.0;
		if(!fixedProbFlag$sample84)
			logProbability$var83 = 0.0;
		logProbability$var101 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample104)
			logProbability$var102 = 0.0;
		logProbability$var120 = 0.0;
		if(!fixedProbFlag$sample123)
			logProbability$var121 = 0.0;
		logProbability$var140 = 0.0;
		logProbability$metric_valid_1d = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample145)
			logProbability$var141 = 0.0;
		logProbability$var150 = 0.0;
		logProbability$metric_g = 0.0;
		if(!fixedProbFlag$sample157)
			logProbability$var151 = 0.0;
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
		if(fixedFlag$sample19)
			logProbabilityValue$sample19();
		if(fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(fixedFlag$sample52)
			logProbabilityValue$sample52();
		if(fixedFlag$sample68)
			logProbabilityValue$sample68();
		if(fixedFlag$sample84)
			logProbabilityValue$sample84();
		logProbabilityValue$sample145();
		logProbabilityValue$sample157();
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
		logProbabilityValue$sample19();
		logProbabilityValue$sample32();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample84();
		logProbabilityDistribution$sample104();
		logProbabilityDistribution$sample123();
		logProbabilityDistribution$sample145();
		logProbabilityDistribution$sample157();
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
		logProbabilityValue$sample19();
		logProbabilityValue$sample32();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample84();
		logProbabilityValue$sample104();
		logProbabilityValue$sample123();
		logProbabilityValue$sample145();
		logProbabilityValue$sample157();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample32)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var31]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample52)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1)
							metric_mean[var50] = (DistributionSampling.sampleUniform(RNG$1) * 100.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample68)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1)
							metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample84)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1)
							metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
						if(!fixedFlag$sample104)
							st[sample][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution, noStates);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample123) {
							int[] var114 = st[sample];
							for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1)
								var114[timeStep$var113] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample][(timeStep$var113 - 1)]], noStates);
						}
					}
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
		int cv$length1 = metric_valid_g.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[] cv$source2 = metric_valid[cv$index1];
			boolean[] cv$target2 = metric_valid_g[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
		for(int sample = (noSamples - 1); sample >= 0; sample -= 1) {
			for(int timeStep$var136 = (length$metric[sample] - 1); timeStep$var136 >= 0; timeStep$var136 -= 1) {
				metric_g[sample][timeStep$var136] = metric[sample][timeStep$var136];
				if(metric_valid_g[sample][timeStep$var136])
					// Looking for a path between Put 158 and consumer double 154.
					var151[sample][timeStep$var136] = metric_g[sample][timeStep$var136];
			}
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model HMMMetrics2(\n"
		     + "               double[][] metric,\n"
		     + "               boolean[][] metric_valid, \n"
		     + "               int noStates) {\n"
		     + "    \n"
		     + "    int noSamples = metric.length;\n"
		     + "\n"
		     + "    // Construct arrays describing the probability of a move from 1 state to another.\n"
		     + "    double[] v = new double[noStates] <~ 0.1;\n"
		     + "    double[] initialStateDistribution = dirichlet(v).sample();\n"
		     + "    double[][] m = dirichlet(v).sample(noStates);\n"
		     + "\n"
		     + "    //Allocate space for states\n"
		     + "    int[][] st = new int[noSamples][];\n"
		     + "\n"
		     + "    //Allocate space for generated metrics \n"
		     + "    double[][] metric_g = new double[noSamples][];\n"
		     + "    boolean[][] metric_valid_g = new boolean[noSamples][];\n"
		     + "    \n"
		     + "    //Calculate priors for the metric\n"
		     + "    double[] metric_mean = uniform(0.0, 100.0).sample(noStates);\n"
		     + "    double[] metric_var = inverseGamma(1.0, 1.0).sample(noStates);\n"
		     + "    double[] metric_valid_bias = beta(1.0, 1.0).sample(noStates);\n"
		     + "    \n"
		     + "    // Compute the values of each metric value\n"
		     + "    for(int sample = 0; sample < noSamples; sample++) {\n"
		     + "        //Calculate all the state transitions\n"
		     + "        int streamLength = metric[sample].length;\n"
		     + "        \n"
		     + "        // Allocate space for the state.\n"
		     + "        st[sample] = new int[streamLength];\n"
		     + "        \n"
		     + "        // Set the initial state by sampling from a categorical with learnt weightings.\n"
		     + "        st[sample][0] = categorical(initialStateDistribution).sampleDistribution();\n"
		     + "        \n"
		     + "        // Calculate the remaining weightings\n"
		     + "        for(int timeStep = 1; timeStep < streamLength; timeStep++)\n"
		     + "            st[sample][timeStep] = categorical(m[st[sample][timeStep-1]]).sampleDistribution();\n"
		     + "        \n"
		     + "        //Calculate metric values\n"
		     + "        double[] metric_1d = new double[streamLength];\n"
		     + "        metric_g[sample] = metric_1d;\n"
		     + "\n"
		     + "        boolean[] metric_valid_1d = new boolean[streamLength];\n"
		     + "        metric_valid_g[sample] = metric_valid_1d;\n"
		     + "\n"
		     + "        //Generate values.\n"
		     + "        for(int timeStep = 0; timeStep < streamLength; timeStep++){\n"
		     + "            int currentState = st[sample][timeStep];\n"
		     + "            \n"
		     + "            metric_valid_1d[timeStep] = bernoulli(metric_valid_bias[currentState]).sample();\n"
		     + "            if(metric_valid_1d[timeStep])\n"
		     + "                metric_1d[timeStep] = gaussian(metric_mean[currentState], metric_var[currentState]).sample();\n"
		     + "            // Observing here as a cast is required and doing it inside the for loops removes the need duplicate them later.\n"
		     + "            metric_1d[timeStep].observe(metric[sample][timeStep]);\n"
		     + "        }\n"
		     + "    }\n"
		     + "\n"
		     + "    //Tie the values to the measured values.\n"
		     + "    metric_valid_g.observe(metric_valid);\n"
		     + "}";
	}
}