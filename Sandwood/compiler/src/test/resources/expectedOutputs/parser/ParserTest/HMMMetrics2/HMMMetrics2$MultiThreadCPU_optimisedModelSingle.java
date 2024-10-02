package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMMetrics2$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] cv$distributionAccumulator$var129;
	private double[][] cv$var111$stateProbabilityGlobal;
	private double[][] cv$var130$stateProbabilityGlobal;
	private double[][] cv$var150$stateProbabilityGlobal;
	private double[] cv$var27$countGlobal;
	private double[][] cv$var40$countGlobal;
	private double[][] distribution$sample117;
	private double[][][] distribution$sample136;
	private boolean fixedFlag$sample117 = false;
	private boolean fixedFlag$sample136 = false;
	private boolean fixedFlag$sample158 = false;
	private boolean fixedFlag$sample170 = false;
	private boolean fixedFlag$sample30 = false;
	private boolean fixedFlag$sample43 = false;
	private boolean fixedFlag$sample63 = false;
	private boolean fixedFlag$sample79 = false;
	private boolean fixedFlag$sample95 = false;
	private boolean fixedProbFlag$sample117 = false;
	private boolean fixedProbFlag$sample136 = false;
	private boolean fixedProbFlag$sample158 = false;
	private boolean fixedProbFlag$sample170 = false;
	private boolean fixedProbFlag$sample30 = false;
	private boolean fixedProbFlag$sample43 = false;
	private boolean fixedProbFlag$sample63 = false;
	private boolean fixedProbFlag$sample79 = false;
	private boolean fixedProbFlag$sample95 = false;
	private boolean[][][] guard$sample117gaussian169$global;
	private boolean[][][] guard$sample136gaussian169$global;
	private double[] initialStateDistribution;
	private int[] length$metric;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$initialStateDistribution;
	private double logProbability$m;
	private double logProbability$metric_1d;
	private double logProbability$metric_g;
	private double logProbability$metric_mean;
	private double logProbability$metric_valid_1d;
	private double logProbability$metric_valid_bias;
	private double logProbability$metric_valid_g;
	private double logProbability$metric_var;
	private double[][] logProbability$sample158;
	private double[][] logProbability$sample170;
	private double logProbability$st;
	private double logProbability$var110;
	private double logProbability$var111;
	private double logProbability$var129;
	private double logProbability$var130;
	private double logProbability$var149;
	private double logProbability$var159;
	private double logProbability$var26;
	private double logProbability$var28;
	private double logProbability$var40;
	private double logProbability$var47;
	private double logProbability$var59;
	private double logProbability$var63;
	private double logProbability$var75;
	private double logProbability$var79;
	private double logProbability$var91;
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
	private boolean setFlag$initialStateDistribution = false;
	private boolean setFlag$m = false;
	private boolean setFlag$metric_g = false;
	private boolean setFlag$metric_mean = false;
	private boolean setFlag$metric_valid_bias = false;
	private boolean setFlag$metric_valid_g = false;
	private boolean setFlag$metric_var = false;
	private boolean setFlag$st = false;
	private int[][] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMMetrics2$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for fixedFlag$sample117.
	@Override
	public final boolean get$fixedFlag$sample117() {
		return fixedFlag$sample117;
	}

	// Setter for fixedFlag$sample117.
	@Override
	public final void set$fixedFlag$sample117(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample117 including if probabilities
		// need to be updated.
		fixedFlag$sample117 = cv$value;
		
		// Should the probability of sample 117 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample117" with its value "cv$value".
		fixedProbFlag$sample117 = (cv$value && fixedProbFlag$sample117);
		
		// Should the probability of sample 136 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample117" with its value "cv$value".
		fixedProbFlag$sample136 = (cv$value && fixedProbFlag$sample136);
		
		// Should the probability of sample 158 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample117" with its value "cv$value".
		fixedProbFlag$sample158 = (cv$value && fixedProbFlag$sample158);
		
		// Should the probability of sample 170 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample117" with its value "cv$value".
		fixedProbFlag$sample170 = (cv$value && fixedProbFlag$sample170);
	}

	// Getter for fixedFlag$sample136.
	@Override
	public final boolean get$fixedFlag$sample136() {
		return fixedFlag$sample136;
	}

	// Setter for fixedFlag$sample136.
	@Override
	public final void set$fixedFlag$sample136(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample136 including if probabilities
		// need to be updated.
		fixedFlag$sample136 = cv$value;
		
		// Should the probability of sample 136 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample136" with its value "cv$value".
		fixedProbFlag$sample136 = (cv$value && fixedProbFlag$sample136);
		
		// Should the probability of sample 158 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample136" with its value "cv$value".
		fixedProbFlag$sample158 = (cv$value && fixedProbFlag$sample158);
		
		// Should the probability of sample 170 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample136" with its value "cv$value".
		fixedProbFlag$sample170 = (cv$value && fixedProbFlag$sample170);
	}

	// Getter for fixedFlag$sample158.
	@Override
	public final boolean get$fixedFlag$sample158() {
		return fixedFlag$sample158;
	}

	// Setter for fixedFlag$sample158.
	@Override
	public final void set$fixedFlag$sample158(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample158 including if probabilities
		// need to be updated.
		fixedFlag$sample158 = cv$value;
		
		// Should the probability of sample 158 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample158" with its value "cv$value".
		fixedProbFlag$sample158 = (cv$value && fixedProbFlag$sample158);
	}

	// Getter for fixedFlag$sample170.
	@Override
	public final boolean get$fixedFlag$sample170() {
		return fixedFlag$sample170;
	}

	// Setter for fixedFlag$sample170.
	@Override
	public final void set$fixedFlag$sample170(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample170 including if probabilities
		// need to be updated.
		fixedFlag$sample170 = cv$value;
		
		// Should the probability of sample 170 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample170" with its value "cv$value".
		fixedProbFlag$sample170 = (cv$value && fixedProbFlag$sample170);
	}

	// Getter for fixedFlag$sample30.
	@Override
	public final boolean get$fixedFlag$sample30() {
		return fixedFlag$sample30;
	}

	// Setter for fixedFlag$sample30.
	@Override
	public final void set$fixedFlag$sample30(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample30 including if probabilities
		// need to be updated.
		fixedFlag$sample30 = cv$value;
		
		// Should the probability of sample 30 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample30" with its value "cv$value".
		fixedProbFlag$sample30 = (cv$value && fixedProbFlag$sample30);
		
		// Should the probability of sample 117 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample30" with its value "cv$value".
		fixedProbFlag$sample117 = (cv$value && fixedProbFlag$sample117);
	}

	// Getter for fixedFlag$sample43.
	@Override
	public final boolean get$fixedFlag$sample43() {
		return fixedFlag$sample43;
	}

	// Setter for fixedFlag$sample43.
	@Override
	public final void set$fixedFlag$sample43(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample43 including if probabilities
		// need to be updated.
		fixedFlag$sample43 = cv$value;
		
		// Should the probability of sample 43 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample43" with its value "cv$value".
		fixedProbFlag$sample43 = (cv$value && fixedProbFlag$sample43);
		
		// Should the probability of sample 136 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample43" with its value "cv$value".
		fixedProbFlag$sample136 = (cv$value && fixedProbFlag$sample136);
	}

	// Getter for fixedFlag$sample63.
	@Override
	public final boolean get$fixedFlag$sample63() {
		return fixedFlag$sample63;
	}

	// Setter for fixedFlag$sample63.
	@Override
	public final void set$fixedFlag$sample63(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample63 including if probabilities
		// need to be updated.
		fixedFlag$sample63 = cv$value;
		
		// Should the probability of sample 63 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample63" with its value "cv$value".
		fixedProbFlag$sample63 = (cv$value && fixedProbFlag$sample63);
		
		// Should the probability of sample 170 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample63" with its value "cv$value".
		fixedProbFlag$sample170 = (cv$value && fixedProbFlag$sample170);
	}

	// Getter for fixedFlag$sample79.
	@Override
	public final boolean get$fixedFlag$sample79() {
		return fixedFlag$sample79;
	}

	// Setter for fixedFlag$sample79.
	@Override
	public final void set$fixedFlag$sample79(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample79 including if probabilities
		// need to be updated.
		fixedFlag$sample79 = cv$value;
		
		// Should the probability of sample 79 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample79" with its value "cv$value".
		fixedProbFlag$sample79 = (cv$value && fixedProbFlag$sample79);
		
		// Should the probability of sample 170 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample79" with its value "cv$value".
		fixedProbFlag$sample170 = (cv$value && fixedProbFlag$sample170);
	}

	// Getter for fixedFlag$sample95.
	@Override
	public final boolean get$fixedFlag$sample95() {
		return fixedFlag$sample95;
	}

	// Setter for fixedFlag$sample95.
	@Override
	public final void set$fixedFlag$sample95(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample95 including if probabilities
		// need to be updated.
		fixedFlag$sample95 = cv$value;
		
		// Should the probability of sample 95 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample95" with its value "cv$value".
		fixedProbFlag$sample95 = (cv$value && fixedProbFlag$sample95);
		
		// Should the probability of sample 158 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample95" with its value "cv$value".
		fixedProbFlag$sample158 = (cv$value && fixedProbFlag$sample158);
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
		// Set initialStateDistribution with flag to mark that it has been set so another
		// array doesn't need to be constructed
		initialStateDistribution = cv$value;
		setFlag$initialStateDistribution = true;
		
		// Unset the fixed probability flag for sample 30 as it depends on initialStateDistribution.
		fixedProbFlag$sample30 = false;
		
		// Unset the fixed probability flag for sample 117 as it depends on initialStateDistribution.
		fixedProbFlag$sample117 = false;
	}

	// Getter for length$metric.
	@Override
	public final int[] get$length$metric() {
		return length$metric;
	}

	// Setter for length$metric.
	@Override
	public final void set$length$metric(int[] cv$value) {
		// Set length$metric with flag to mark that it has been set so another array doesn't
		// need to be constructed
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
		// Set m with flag to mark that it has been set so another array doesn't need to be
		// constructed
		m = cv$value;
		setFlag$m = true;
		
		// Unset the fixed probability flag for sample 43 as it depends on m.
		fixedProbFlag$sample43 = false;
		
		// Unset the fixed probability flag for sample 136 as it depends on m.
		fixedProbFlag$sample136 = false;
	}

	// Getter for metric.
	@Override
	public final double[][] get$metric() {
		return metric;
	}

	// Setter for metric.
	@Override
	public final void set$metric(double[][] cv$value) {
		// Set metric with flag to mark that it has been set so another array doesn't need
		// to be constructed
		metric = cv$value;
	}

	// Getter for metric_g.
	@Override
	public final double[][] get$metric_g() {
		return metric_g;
	}

	// Setter for metric_g.
	@Override
	public final void set$metric_g(double[][] cv$value) {
		// Set metric_g with flag to mark that it has been set so another array doesn't need
		// to be constructed
		metric_g = cv$value;
		setFlag$metric_g = true;
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
		// Set metric_mean with flag to mark that it has been set so another array doesn't
		// need to be constructed
		metric_mean = cv$value;
		setFlag$metric_mean = true;
		
		// Unset the fixed probability flag for sample 63 as it depends on metric_mean.
		fixedProbFlag$sample63 = false;
		
		// Unset the fixed probability flag for sample 170 as it depends on metric_mean.
		fixedProbFlag$sample170 = false;
	}

	// Getter for metric_valid.
	@Override
	public final boolean[][] get$metric_valid() {
		return metric_valid;
	}

	// Setter for metric_valid.
	@Override
	public final void set$metric_valid(boolean[][] cv$value) {
		// Set metric_valid with flag to mark that it has been set so another array doesn't
		// need to be constructed
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
		// Set metric_valid_bias with flag to mark that it has been set so another array doesn't
		// need to be constructed
		metric_valid_bias = cv$value;
		setFlag$metric_valid_bias = true;
		
		// Unset the fixed probability flag for sample 95 as it depends on metric_valid_bias.
		fixedProbFlag$sample95 = false;
		
		// Unset the fixed probability flag for sample 158 as it depends on metric_valid_bias.
		fixedProbFlag$sample158 = false;
	}

	// Getter for metric_valid_g.
	@Override
	public final boolean[][] get$metric_valid_g() {
		return metric_valid_g;
	}

	// Setter for metric_valid_g.
	@Override
	public final void set$metric_valid_g(boolean[][] cv$value) {
		// Set metric_valid_g with flag to mark that it has been set so another array doesn't
		// need to be constructed
		metric_valid_g = cv$value;
		setFlag$metric_valid_g = true;
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
		// Set metric_var with flag to mark that it has been set so another array doesn't
		// need to be constructed
		metric_var = cv$value;
		setFlag$metric_var = true;
		
		// Unset the fixed probability flag for sample 79 as it depends on metric_var.
		fixedProbFlag$sample79 = false;
		
		// Unset the fixed probability flag for sample 170 as it depends on metric_var.
		fixedProbFlag$sample170 = false;
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
		// Set st with flag to mark that it has been set so another array doesn't need to
		// be constructed
		st = cv$value;
		setFlag$st = true;
		
		// Unset the fixed probability flag for sample 117 as it depends on st.
		fixedProbFlag$sample117 = false;
		
		// Unset the fixed probability flag for sample 136 as it depends on st.
		fixedProbFlag$sample136 = false;
		
		// Unset the fixed probability flag for sample 158 as it depends on st.
		fixedProbFlag$sample158 = false;
		
		// Unset the fixed probability flag for sample 170 as it depends on st.
		fixedProbFlag$sample170 = false;
	}

	// Getter for v.
	@Override
	public final double[] get$v() {
		return v;
	}

	// Calculate the probability of the samples represented by sample117 using probability
	// distributions.
	private final void logProbabilityDistribution$sample117() {
		// Determine if we need to calculate the values for sample task 117 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample117) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample117) {
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
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
				}
				logProbability$var110 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var111 = cv$sampleAccumulator;
				
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
				// Substituted "fixedFlag$sample117" with its value "true".
				fixedProbFlag$sample117 = fixedFlag$sample30;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var110 = logProbability$var111;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample117)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$st = (logProbability$st + logProbability$var111);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var111);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample117)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var111);
		}
	}

	// Calculate the probability of the samples represented by sample136 using probability
	// distributions.
	private final void logProbabilityDistribution$sample136() {
		// Determine if we need to calculate the values for sample task 136 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample136) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample136) {
				// Generating probabilities for sample task
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int sample = 0; sample < noSamples; sample += 1) {
					for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[sample][timeStep$var122];
						
						// Enumerating the possible arguments for Categorical 129.
						if((1 == timeStep$var122)) {
							// Enumerating the possible arguments for Categorical 129.
							if(fixedFlag$sample117) {
								int var39 = st[sample][0];
								
								// Substituted "timeStep$var122" with its value "1".
								if(((0 <= var39) && (var39 < noStates))) {
									// Substituted "timeStep$var122" with its value "1".
									double[] var128 = m[st[sample][0]];
									
									// Store the value of the function call, so the function call is only made once.
									cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var128.length))?Math.log(var128[cv$sampleValue]):Double.NEGATIVE_INFINITY);
									
									// Add the probability of this distribution configuration to the accumulator.
									// 
									// An accumulator for the distributed probability space covered.
									cv$probabilityReached = 1.0;
								}
							} else {
								// Enumerating the possible outputs of Categorical 110.
								for(int index$sample117$6 = 0; index$sample117$6 < noStates; index$sample117$6 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "index$sample$5" with its value "sample".
									double cv$probabilitySample117Value7 = distribution$sample117[sample][index$sample117$6];
									double[] var128 = m[index$sample117$6];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample117Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var128.length))?Math.log(var128[cv$sampleValue]):Double.NEGATIVE_INFINITY));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample117Value7);
								}
							}
						}
						
						// Substituted "index$sample$13_1" with its value "sample".
						// 
						// Substituted "index$timeStep$13_2" with its value "(timeStep$var122 - 1)".
						if((2 <= timeStep$var122)) {
							int var39 = st[sample][(timeStep$var122 - 1)];
							if(((0 <= var39) && (var39 < noStates))) {
								double[] var128 = m[st[sample][(timeStep$var122 - 1)]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var128.length))?Math.log(var128[cv$sampleValue]):Double.NEGATIVE_INFINITY);
								
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
				logProbability$var129 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				// 
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$var130 = cv$sampleAccumulator;
				
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
				// Substituted "fixedFlag$sample136" with its value "true".
				fixedProbFlag$sample136 = (fixedFlag$sample43 && fixedFlag$sample117);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var129 = logProbability$var130;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample136)
				// Update the variable probability
				// 
				// Variable declaration of cv$accumulator moved.
				logProbability$st = (logProbability$st + logProbability$var130);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var130);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample136)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var130);
		}
	}

	// Calculate the probability of the samples represented by sample158 using probability
	// distributions.
	private final void logProbabilityDistribution$sample158() {
		// Determine if we need to calculate the values for sample task 158 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample158) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Look for paths between the variable and the sample task 158 including any distribution
					// values.
					// 
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = metric_valid_g[sample][timeStep$var145];
					
					// Enumerating the possible arguments for Bernoulli 149.
					if((0 == timeStep$var145)) {
						// Enumerating the possible arguments for Bernoulli 149.
						if(fixedFlag$sample117) {
							int var90 = st[sample][0];
							
							// Substituted "timeStep$var145" with its value "0".
							if(((0 <= var90) && (var90 < noStates))) {
								// Store the value of the function call, so the function call is only made once.
								// 
								// Substituted "timeStep$var145" with its value "0".
								cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, metric_valid_bias[st[sample][0]]);
								
								// Add the probability of this distribution configuration to the accumulator.
								// 
								// An accumulator for the distributed probability space covered.
								cv$probabilityReached = 1.0;
							}
						} else {
							// Enumerating the possible outputs of Categorical 110.
							for(int index$sample117$4 = 0; index$sample117$4 < noStates; index$sample117$4 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$sample$3" with its value "sample".
								double cv$probabilitySample117Value5 = distribution$sample117[sample][index$sample117$4];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample117Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, metric_valid_bias[index$sample117$4]));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample117Value5);
							}
						}
					}
					
					// Enumerating the possible arguments for Bernoulli 149.
					if((1 <= timeStep$var145)) {
						// Enumerating the possible arguments for Bernoulli 149.
						if(fixedFlag$sample136) {
							int var90 = st[sample][timeStep$var145];
							if(((0 <= var90) && (var90 < noStates))) {
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, metric_valid_bias[st[sample][timeStep$var145]]);
								
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
							// Enumerating the possible outputs of Categorical 129.
							for(int index$sample136$13 = 0; index$sample136$13 < noStates; index$sample136$13 += 1) {
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$sample$11" with its value "sample".
								double cv$probabilitySample136Value14 = distribution$sample136[sample][(timeStep$var145 - 1)][index$sample136$13];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(cv$probabilitySample136Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, metric_valid_bias[index$sample136$13]));
								
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
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample136Value14);
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
					
					// Store the sample task probability
					logProbability$sample158[sample][timeStep$var145] = cv$distributionAccumulator;
					
					// Update the variable probability
					logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$distributionAccumulator);
					
					// Update the variable probability
					logProbability$metric_g = (logProbability$metric_g + cv$distributionAccumulator);
				}
			}
			logProbability$var149 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample158 = (((fixedFlag$sample158 && fixedFlag$sample95) && fixedFlag$sample117) && fixedFlag$sample136);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					double cv$sampleValue = logProbability$sample158[sample][timeStep$var145];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Update the variable probability
					logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
					
					// Update the variable probability
					logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
				}
			}
			logProbability$var149 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample170 using probability
	// distributions.
	private final void logProbabilityDistribution$sample170() {
		// Determine if we need to calculate the values for sample task 170 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample170) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					if(metric_valid_g[sample][timeStep$var145]) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// The sample value to calculate the probability of generating
						double cv$sampleValue = metric_g[sample][timeStep$var145];
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(((0 == timeStep$var145) && metric_valid_g[sample][0])) {
							// Enumerating the possible arguments for Gaussian 159.
							// 
							// Enumerating the possible arguments for Gaussian 159.
							if(fixedFlag$sample117) {
								// Constraints moved from conditionals in inner loops/scopes/etc.
								if((0 <= st[sample][0])) {
									int var58 = st[sample][0];
									
									// Substituted "timeStep$var145" with its value "0".
									if(((0 <= var58) && (var58 < noStates))) {
										// Substituted "timeStep$var145" with its value "0".
										double var158 = metric_var[st[sample][0]];
										
										// Store the value of the function call, so the function call is only made once.
										// 
										// Substituted "timeStep$var145" with its value "0".
										cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[st[sample][0]]) / Math.sqrt(var158))) - (Math.log(var158) * 0.5));
										
										// Add the probability of this distribution configuration to the accumulator.
										// 
										// An accumulator for the distributed probability space covered.
										cv$probabilityReached = 1.0;
									}
								}
							} else {
								// Enumerating the possible outputs of Categorical 110.
								for(int index$sample117$4 = 0; index$sample117$4 < noStates; index$sample117$4 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "index$sample$3" with its value "sample".
									double cv$probabilitySample117Value5 = distribution$sample117[sample][index$sample117$4];
									double var158 = metric_var[index$sample117$4];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = ((Math.log(cv$probabilitySample117Value5) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[index$sample117$4]) / Math.sqrt(var158)))) - (Math.log(var158) * 0.5));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample117Value5);
								}
							}
						}
						
						// Enumerating the possible arguments for Gaussian 159.
						if((1 <= timeStep$var145)) {
							// Enumerating the possible arguments for Gaussian 159.
							if(fixedFlag$sample136) {
								// Constraints moved from conditionals in inner loops/scopes/etc.
								if((0 <= st[sample][timeStep$var145])) {
									int var58 = st[sample][timeStep$var145];
									if(((0 <= var58) && (var58 < noStates))) {
										double var158 = metric_var[st[sample][timeStep$var145]];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[st[sample][timeStep$var145]]) / Math.sqrt(var158))) - (Math.log(var158) * 0.5));
										
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
								// Enumerating the possible outputs of Categorical 129.
								for(int index$sample136$49 = 0; index$sample136$49 < noStates; index$sample136$49 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "index$sample$47" with its value "sample".
									double cv$probabilitySample136Value50 = distribution$sample136[sample][(timeStep$var145 - 1)][index$sample136$49];
									double var158 = metric_var[index$sample136$49];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = ((Math.log(cv$probabilitySample136Value50) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[index$sample136$49]) / Math.sqrt(var158)))) - (Math.log(var158) * 0.5));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample136Value50);
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
						
						// Store the sample task probability
						logProbability$sample170[sample][timeStep$var145] = cv$distributionAccumulator;
						
						// Update the variable probability
						logProbability$metric_g = (logProbability$metric_g + cv$distributionAccumulator);
					}
				}
			}
			logProbability$var159 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_1d = (logProbability$metric_1d + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample170 = ((((fixedFlag$sample170 && fixedFlag$sample63) && fixedFlag$sample79) && fixedFlag$sample117) && fixedFlag$sample136);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					if(metric_valid_g[sample][timeStep$var145]) {
						double cv$sampleValue = logProbability$sample170[sample][timeStep$var145];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						
						// Update the variable probability
						logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
					}
				}
			}
			logProbability$var159 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$metric_1d = (logProbability$metric_1d + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample117 using sampled
	// values.
	private final void logProbabilityValue$sample117() {
		// Determine if we need to calculate the values for sample task 117 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample117) {
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			logProbability$var110 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var111 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample117)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample117 = (fixedFlag$sample117 && fixedFlag$sample30);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var110 = logProbability$var111;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var111);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var111);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample117)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var111);
		}
	}

	// Calculate the probability of the samples represented by sample136 using sampled
	// values.
	private final void logProbabilityValue$sample136() {
		// Determine if we need to calculate the values for sample task 136 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample136) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[sample][timeStep$var122];
					double[] var128 = m[st[sample][(timeStep$var122 - 1)]];
					
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
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var128.length))?Math.log(var128[cv$sampleValue]):Double.NEGATIVE_INFINITY));
				}
			}
			logProbability$var129 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$var130 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample136)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample136 = ((fixedFlag$sample136 && fixedFlag$sample43) && fixedFlag$sample117);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var129 = logProbability$var130;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$st = (logProbability$st + logProbability$var130);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var130);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample136)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var130);
		}
	}

	// Calculate the probability of the samples represented by sample158 using sampled
	// values.
	private final void logProbabilityValue$sample158() {
		// Determine if we need to calculate the values for sample task 158 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample158) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
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
					double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample][timeStep$var145], metric_valid_bias[st[sample][timeStep$var145]]);
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
					
					// Store the sample task probability
					logProbability$sample158[sample][timeStep$var145] = cv$distributionAccumulator;
					
					// Update the variable probability
					logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$distributionAccumulator);
					
					// Update the variable probability
					logProbability$metric_g = (logProbability$metric_g + cv$distributionAccumulator);
				}
			}
			logProbability$var149 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample158 = (((fixedFlag$sample158 && fixedFlag$sample95) && fixedFlag$sample117) && fixedFlag$sample136);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					double cv$sampleValue = logProbability$sample158[sample][timeStep$var145];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Update the variable probability
					logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
					
					// Update the variable probability
					logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
				}
			}
			logProbability$var149 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample170 using sampled
	// values.
	private final void logProbabilityValue$sample170() {
		// Determine if we need to calculate the values for sample task 170 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample170) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					if(metric_valid_g[sample][timeStep$var145]) {
						double var158 = metric_var[st[sample][timeStep$var145]];
						
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
						double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - metric_mean[st[sample][timeStep$var145]]) / Math.sqrt(var158))) - (Math.log(var158) * 0.5));
						
						// Add the probability of this sample task to the sample task accumulator.
						cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
						
						// Store the sample task probability
						logProbability$sample170[sample][timeStep$var145] = cv$distributionAccumulator;
						
						// Update the variable probability
						logProbability$metric_g = (logProbability$metric_g + cv$distributionAccumulator);
					}
				}
			}
			logProbability$var159 = cv$sampleAccumulator;
			
			// Update the variable probability
			// 
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			// 
			// Accumulator for probabilities of instances of the random variable
			logProbability$metric_1d = (logProbability$metric_1d + cv$sampleAccumulator);
			
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
			fixedProbFlag$sample170 = ((((fixedFlag$sample170 && fixedFlag$sample63) && fixedFlag$sample79) && fixedFlag$sample117) && fixedFlag$sample136);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$rvAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					if(metric_valid_g[sample][timeStep$var145]) {
						double cv$sampleValue = logProbability$sample170[sample][timeStep$var145];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						
						// Update the variable probability
						logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
					}
				}
			}
			logProbability$var159 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$metric_1d = (logProbability$metric_1d + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	// Calculate the probability of the samples represented by sample30 using sampled
	// values.
	private final void logProbabilityValue$sample30() {
		// Determine if we need to calculate the values for sample task 30 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample30) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(initialStateDistribution, v);
			
			// Add the probability of this sample task to the sample task accumulator.
			// 
			// Accumulator for sample probabilities for a specific instance of the random variable.
			logProbability$var26 = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample30)
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
			fixedProbFlag$sample30 = fixedFlag$sample30;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var26 = logProbability$initialStateDistribution;
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$initialStateDistribution);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample30)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialStateDistribution);
		}
	}

	// Calculate the probability of the samples represented by sample43 using sampled
	// values.
	private final void logProbabilityValue$sample43() {
		// Determine if we need to calculate the values for sample task 43 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample43) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var39 = 0; var39 < noStates; var39 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var39], v));
			logProbability$var28 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var40 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample43)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample43 = fixedFlag$sample43;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var28 = logProbability$var40;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var40);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var40);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample43)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var40);
		}
	}

	// Calculate the probability of the samples represented by sample63 using sampled
	// values.
	private final void logProbabilityValue$sample63() {
		// Determine if we need to calculate the values for sample task 63 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample63) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var58 = 0; var58 < noStates; var58 += 1) {
				// The sample value to calculate the probability of generating
				double cv$sampleValue = metric_mean[var58];
				
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
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue <= 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY));
			}
			logProbability$var47 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var59 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample63)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample63 = fixedFlag$sample63;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var47 = logProbability$var59;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$metric_mean = (logProbability$metric_mean + logProbability$var59);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var59);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample63)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var59);
		}
	}

	// Calculate the probability of the samples represented by sample79 using sampled
	// values.
	private final void logProbabilityValue$sample79() {
		// Determine if we need to calculate the values for sample task 79 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample79) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var74 = 0; var74 < noStates; var74 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(metric_var[var74], 1.0, 1.0));
			logProbability$var63 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var75 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample79)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample79 = fixedFlag$sample79;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var63 = logProbability$var75;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$metric_var = (logProbability$metric_var + logProbability$var75);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var75);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample79)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var75);
		}
	}

	// Calculate the probability of the samples represented by sample95 using sampled
	// values.
	private final void logProbabilityValue$sample95() {
		// Determine if we need to calculate the values for sample task 95 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample95) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var90 = 0; var90 < noStates; var90 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(metric_valid_bias[var90], 1.0, 1.0));
			logProbability$var79 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var91 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample95)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample95 = fixedFlag$sample95;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			logProbability$var79 = logProbability$var91;
			
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + logProbability$var91);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var91);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample95)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var91);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 117 drawn from Categorical 110. Inference was performed using variable
	// marginalization.
	private final void sample117(int sample, int threadID$cv$sample, Rng RNG$) {
		// Variable declaration of cv$noStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
		// cv$noStates's comment
		// Calculate the number of states to evaluate.
		int cv$noStates = Math.max(0, noStates);
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var111$stateProbabilityGlobal[threadID$cv$sample];
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
			// Substituted "cv$temp$0$initialStateDistribution" with its value "initialStateDistribution".
			double cv$accumulatedProbabilities = ((cv$valuePos < initialStateDistribution.length)?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((fixedFlag$sample136 && (1 < length$metric[sample]))) {
				// Looking for a path between Sample 117 and consumer Categorical 129.
				// Processing sample task 136 of consumer random variable null.
				// 
				// Set an accumulator to sum the probabilities for each possible configuration of
				// inputs.
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				
				// Set an accumulator to record the consumer distributions not seen. Initially set
				// to 1 as seen values will be deducted from this value.
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				
				// Value of the variable at this index
				if((cv$valuePos < noStates)) {
					// Variable declaration of cv$temp$1$var128 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					double[] cv$temp$1$var128 = m[cv$valuePos];
					
					// Substituted "index$sample$3_2" with its value "sample".
					cv$accumulatedConsumerProbabilities = (((0.0 <= st[sample][1]) && (st[sample][1] < cv$temp$1$var128.length))?Math.log(cv$temp$1$var128[st[sample][1]]):Double.NEGATIVE_INFINITY);
					
					// Recorded the probability of reaching sample task 136 with the current configuration.
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
				// Processing random variable 149.
				{
					// Looking for a path between Sample 117 and consumer Bernoulli 149.
					// Processing sample task 158 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Value of the variable at this index
					if((cv$valuePos < noStates)) {
						// Substituted "index$sample$9_2" with its value "sample".
						// 
						// cv$temp$2$var148's comment
						// Variable declaration of cv$temp$2$var148 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample][0], metric_valid_bias[cv$valuePos]);
						
						// Recorded the probability of reaching sample task 158 with the current configuration.
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
					// Looking for a path between Sample 117 and consumer Gaussian 159.
					// Guard to check that at most one copy of the code is executed for a given random
					// variable instance.
					boolean[][] guard$sample117gaussian169 = guard$sample117gaussian169$global[threadID$cv$sample];
					
					// Set the flags to false
					// 
					// Substituted "timeStep$var145" with its value "0".
					guard$sample117gaussian169[sample][0] = false;
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if(!guard$sample117gaussian169[sample][0]) {
						// The body will execute, so should not be executed again
						// 
						// Substituted "timeStep$var145" with its value "0".
						guard$sample117gaussian169[sample][0] = true;
						
						// Processing sample task 170 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((cv$valuePos < noStates)) {
							// Variable declaration of cv$temp$4$var158 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							double cv$temp$4$var158 = metric_var[cv$valuePos];
							
							// Substituted "index$sample$15_2" with its value "sample".
							// 
							// cv$temp$3$var157's comment
							// Variable declaration of cv$temp$3$var157 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$4$var158))) - (Math.log(cv$temp$4$var158) * 0.5));
							
							// Recorded the probability of reaching sample task 170 with the current configuration.
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
					
					// Substituted "timeStep$var145" with its value "0".
					if(!guard$sample117gaussian169[sample][0]) {
						// The body will execute, so should not be executed again
						// 
						// Substituted "timeStep$var145" with its value "0".
						guard$sample117gaussian169[sample][0] = true;
						
						// Processing sample task 170 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Substituted "index$sample$16_2" with its value "sample".
						// 
						// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
						// the output of Sample task 117.
						// 
						// Value of the variable at this index
						if((cv$valuePos < noStates)) {
							// Variable declaration of cv$temp$12$var158 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
							// the output of Sample task 117.
							// 
							// Value of the variable at this index
							double cv$temp$12$var158 = metric_var[cv$valuePos];
							
							// Substituted "index$sample$16_2" with its value "sample".
							// 
							// cv$temp$11$var157's comment
							// Variable declaration of cv$temp$11$var157 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
							// the output of Sample task 117.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$12$var158))) - (Math.log(cv$temp$12$var158) * 0.5));
							
							// Recorded the probability of reaching sample task 170 with the current configuration.
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
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((!fixedFlag$sample136 && (1 < length$metric[sample]))) {
				// Looking for a path between Sample 117 and consumer Categorical 129.
				// Processing sample task 136 of consumer random variable null.
				// 
				// A local array to hold the accumulated distributions of the sample tasks for each
				// configuration of distributions.
				double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var129[threadID$cv$sample];
				
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$accumulatedConsumerDistributions[cv$i] = 0.0;
				
				// Zero an accumulator to track the probabilities reached.
				double cv$reachedDistributionProbability = 0.0;
				
				// Value of the variable at this index
				if((cv$valuePos < noStates)) {
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
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					// 
					// cv$temp$19$var128's comment
					// Variable declaration of cv$temp$19$var128 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, 1.0, m[cv$valuePos]);
				}
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "index$sample$67_2" with its value "sample".
				double[] cv$sampleDistribution = distribution$sample136[sample][0];
				
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
		double[] cv$localProbability = distribution$sample117[sample];
		
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
	// by sample task 136 drawn from Categorical 129. Inference was performed using variable
	// marginalization.
	private final void sample136(int sample, int timeStep$var122, int threadID$cv$sample, Rng RNG$) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		
		// Enumerating the possible arguments for Categorical 129.
		if((1 == timeStep$var122)) {
			// Enumerating the possible arguments for Categorical 129.
			if(fixedFlag$sample117) {
				int var39 = st[sample][0];
				
				// Substituted "timeStep$var122" with its value "1".
				if(((0 <= var39) && (var39 < noStates)))
					// variable marginalization
					// 
					// cv$noStates's comment
					// Calculate the number of states to evaluate.
					cv$noStates = Math.max(0, noStates);
			} else {
				// Enumerating the possible outputs of Categorical 110.
				if((0 < noStates))
					// variable marginalization
					cv$noStates = noStates;
			}
		}
		if(fixedFlag$sample136) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((2 <= timeStep$var122)) {
				int var39 = st[sample][(timeStep$var122 - 1)];
				if(((0 <= var39) && (var39 < noStates)))
					// variable marginalization
					cv$noStates = Math.max(cv$noStates, noStates);
			}
		} else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < noStates)) {
				int index$timeStep$14 = (timeStep$var122 - 1);
				
				// index$timeStep$1's comment
				// Exploring all the possible state counts for random variable 129.
				// 
				// Copy of index so that its values can be safely substituted
				// 
				// Substituted "index$timeStep$14" with its value "(timeStep$var122 - 1)".
				// 
				// Substituted "index$timeStep$14" with its value "(timeStep$var122 - 1)".
				// 
				// Substituted "index$timeStep$14" with its value "(timeStep$var122 - 1)".
				// 
				// Substituted "index$timeStep$14" with its value "(timeStep$var122 - 1)".
				if(((1 <= index$timeStep$14) && !(index$timeStep$14 == timeStep$var122)))
					// variable marginalization
					cv$noStates = Math.max(cv$noStates, noStates);
			}
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var130$stateProbabilityGlobal[threadID$cv$sample];
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 129 creating
			// sample task 136.
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Enumerating the possible arguments for Categorical 129.
			if((1 == timeStep$var122)) {
				// Enumerating the possible arguments for Categorical 129.
				if(fixedFlag$sample117) {
					int var39 = st[sample][0];
					
					// Substituted "timeStep$var122" with its value "1".
					if(((0 <= var39) && (var39 < noStates))) {
						// Record the reached probability density.
						// 
						// Initialize a counter to track the reached distributions.
						cv$reachedDistributionSourceRV = 1.0;
						
						// Variable declaration of cv$temp$0$var128 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Substituted "timeStep$var122" with its value "1".
						double[] cv$temp$0$var128 = m[st[sample][0]];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var128.length)?Math.log(cv$temp$0$var128[cv$valuePos]):Double.NEGATIVE_INFINITY);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 < length$metric[sample])) {
							{
								// Looking for a path between Sample 136 and consumer Bernoulli 149.
								// Processing sample task 158 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								
								// Value of the variable at this index
								if((cv$valuePos < noStates)) {
									// Substituted "index$sample$45_2" with its value "sample".
									// 
									// Substituted "timeStep$var145" with its value "1".
									// 
									// cv$temp$4$var148's comment
									// Variable declaration of cv$temp$4$var148 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample][1], metric_valid_bias[cv$valuePos]);
									
									// Recorded the probability of reaching sample task 158 with the current configuration.
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
								// Processing random variable 159.
								// 
								// Looking for a path between Sample 136 and consumer Gaussian 159.
								// 
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								boolean[][] guard$sample136gaussian169 = guard$sample136gaussian169$global[threadID$cv$sample];
								
								// Set the flags to false
								// 
								// Substituted "timeStep$var145" with its value "1".
								guard$sample136gaussian169[sample][1] = false;
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
								if(!guard$sample136gaussian169[sample][1]) {
									// The body will execute, so should not be executed again
									// 
									// Substituted "timeStep$var145" with its value "1".
									guard$sample136gaussian169[sample][1] = true;
									
									// Processing sample task 170 of consumer random variable null.
									// 
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Substituted "index$sample$69_2" with its value "sample".
									// 
									// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
									// the output of Sample task 136.
									// 
									// Value of the variable at this index
									if((cv$valuePos < noStates)) {
										// Variable declaration of cv$temp$11$var158 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double cv$temp$11$var158 = metric_var[cv$valuePos];
										
										// Substituted "index$sample$69_2" with its value "sample".
										// 
										// Substituted "timeStep$var145" with its value "1".
										// 
										// cv$temp$10$var157's comment
										// Variable declaration of cv$temp$10$var157 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$11$var158))) - (Math.log(cv$temp$11$var158) * 0.5));
										
										// Recorded the probability of reaching sample task 170 with the current configuration.
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
								
								// Substituted "timeStep$var122" with its value "1".
								// 
								// Substituted "timeStep$var145" with its value "1".
								if(!guard$sample136gaussian169[sample][1]) {
									// The body will execute, so should not be executed again
									// 
									// Substituted "timeStep$var145" with its value "1".
									guard$sample136gaussian169[sample][1] = true;
									
									// Processing sample task 170 of consumer random variable null.
									// 
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Constraints moved from conditionals in inner loops/scopes/etc.
									if((cv$valuePos < noStates)) {
										// Variable declaration of cv$temp$43$var158 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
										// the output of Sample task 136.
										// 
										// Value of the variable at this index
										double cv$temp$43$var158 = metric_var[cv$valuePos];
										
										// Substituted "index$sample$73_2" with its value "sample".
										// 
										// Substituted "timeStep$var145" with its value "1".
										// 
										// cv$temp$42$var157's comment
										// Variable declaration of cv$temp$42$var157 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
										// the output of Sample task 136.
										// 
										// Value of the variable at this index
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$43$var158))) - (Math.log(cv$temp$43$var158) * 0.5));
										
										// Recorded the probability of reaching sample task 170 with the current configuration.
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
							}
						}
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					// Enumerating the possible outputs of Categorical 110.
					for(int index$sample117$26 = 0; index$sample117$26 < noStates; index$sample117$26 += 1) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample$25" with its value "sample".
						double cv$probabilitySample117Value27 = distribution$sample117[sample][index$sample117$26];
						
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample117Value27);
						
						// Variable declaration of cv$temp$1$var128 moved.
						// 
						// Constructing a random variable input for use later.
						double[] cv$temp$1$var128 = m[index$sample117$26];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
						// Value of the variable at this index
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample117Value27) + ((cv$valuePos < cv$temp$1$var128.length)?Math.log(cv$temp$1$var128[cv$valuePos]):Double.NEGATIVE_INFINITY));
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((1 < length$metric[sample])) {
							{
								// Looking for a path between Sample 136 and consumer Bernoulli 149.
								// Processing sample task 158 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								
								// Value of the variable at this index
								if((cv$valuePos < noStates)) {
									// Substituted "index$sample$46_2" with its value "sample".
									// 
									// Substituted "timeStep$var145" with its value "1".
									// 
									// cv$temp$5$var148's comment
									// Variable declaration of cv$temp$5$var148 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Value of the variable at this index
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample][1], metric_valid_bias[cv$valuePos]);
									
									// Recorded the probability of reaching sample task 158 with the current configuration.
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
								// Processing random variable 159.
								// 
								// Looking for a path between Sample 136 and consumer Gaussian 159.
								// 
								// Guard to check that at most one copy of the code is executed for a given random
								// variable instance.
								boolean[][] guard$sample136gaussian169 = guard$sample136gaussian169$global[threadID$cv$sample];
								
								// Set the flags to false
								// 
								// Substituted "timeStep$var145" with its value "1".
								guard$sample136gaussian169[sample][1] = false;
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
								if(!guard$sample136gaussian169[sample][1]) {
									// The body will execute, so should not be executed again
									// 
									// Substituted "timeStep$var145" with its value "1".
									guard$sample136gaussian169[sample][1] = true;
									
									// Processing sample task 170 of consumer random variable null.
									// 
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Substituted "index$sample$70_2" with its value "sample".
									// 
									// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
									// the output of Sample task 136.
									// 
									// Value of the variable at this index
									if((cv$valuePos < noStates)) {
										// Variable declaration of cv$temp$19$var158 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										double cv$temp$19$var158 = metric_var[cv$valuePos];
										
										// Substituted "index$sample$70_2" with its value "sample".
										// 
										// Substituted "timeStep$var145" with its value "1".
										// 
										// cv$temp$18$var157's comment
										// Variable declaration of cv$temp$18$var157 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Value of the variable at this index
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$19$var158))) - (Math.log(cv$temp$19$var158) * 0.5));
										
										// Recorded the probability of reaching sample task 170 with the current configuration.
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
								
								// Substituted "timeStep$var122" with its value "1".
								// 
								// Substituted "timeStep$var145" with its value "1".
								if(!guard$sample136gaussian169[sample][1]) {
									// The body will execute, so should not be executed again
									// 
									// Substituted "timeStep$var145" with its value "1".
									guard$sample136gaussian169[sample][1] = true;
									
									// Processing sample task 170 of consumer random variable null.
									// 
									// Set an accumulator to sum the probabilities for each possible configuration of
									// inputs.
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									
									// Set an accumulator to record the consumer distributions not seen. Initially set
									// to 1 as seen values will be deducted from this value.
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									
									// Constraints moved from conditionals in inner loops/scopes/etc.
									if((cv$valuePos < noStates)) {
										// Variable declaration of cv$temp$51$var158 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
										// the output of Sample task 136.
										// 
										// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
										// the output of Sample task 136.
										// 
										// Value of the variable at this index
										double cv$temp$51$var158 = metric_var[cv$valuePos];
										
										// Substituted "index$sample$74_2" with its value "sample".
										// 
										// Substituted "timeStep$var145" with its value "1".
										// 
										// cv$temp$50$var157's comment
										// Variable declaration of cv$temp$50$var157 moved.
										// 
										// Constructing a random variable input for use later.
										// 
										// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
										// the output of Sample task 136.
										// 
										// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
										// the output of Sample task 136.
										// 
										// Value of the variable at this index
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$51$var158))) - (Math.log(cv$temp$51$var158) * 0.5));
										
										// Recorded the probability of reaching sample task 170 with the current configuration.
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
			int index$timeStep$34 = (timeStep$var122 - 1);
			
			// index$timeStep$22's comment
			// Copy of index so that its values can be safely substituted
			// 
			// Substituted "index$timeStep$34" with its value "(timeStep$var122 - 1)".
			// 
			// Substituted "index$timeStep$34" with its value "(timeStep$var122 - 1)".
			// 
			// Substituted "index$timeStep$34" with its value "(timeStep$var122 - 1)".
			// 
			// Substituted "index$timeStep$34" with its value "(timeStep$var122 - 1)".
			if(((1 <= index$timeStep$34) && !(index$timeStep$34 == timeStep$var122))) {
				// Enumerating the possible outputs of Categorical 129.
				for(int index$sample136$35 = 0; index$sample136$35 < noStates; index$sample136$35 += 1) {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample$33" with its value "sample".
					double cv$probabilitySample136Value36 = distribution$sample136[sample][(index$timeStep$34 - 1)][index$sample136$35];
					
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample136Value36);
					
					// Variable declaration of cv$temp$3$var128 moved.
					// 
					// Constructing a random variable input for use later.
					double[] cv$temp$3$var128 = m[index$sample136$35];
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
					// Value of the variable at this index
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample136Value36) + ((cv$valuePos < cv$temp$3$var128.length)?Math.log(cv$temp$3$var128[cv$valuePos]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 149.
					{
						// Looking for a path between Sample 136 and consumer Bernoulli 149.
						// Processing sample task 158 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						
						// Value of the variable at this index
						if((cv$valuePos < noStates)) {
							// Substituted "index$sample$48_2" with its value "sample".
							// 
							// cv$temp$7$var148's comment
							// Variable declaration of cv$temp$7$var148 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Value of the variable at this index
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample][timeStep$var122], metric_valid_bias[cv$valuePos]);
							
							// Recorded the probability of reaching sample task 158 with the current configuration.
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
					if(metric_valid_g[sample][timeStep$var122]) {
						// Looking for a path between Sample 136 and consumer Gaussian 159.
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[][] guard$sample136gaussian169 = guard$sample136gaussian169$global[threadID$cv$sample];
						
						// Set the flags to false
						// 
						// Substituted "timeStep$var145" with its value "timeStep$var122".
						guard$sample136gaussian169[sample][timeStep$var122] = false;
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!guard$sample136gaussian169[sample][timeStep$var122]) {
							// The body will execute, so should not be executed again
							// 
							// Substituted "timeStep$var145" with its value "timeStep$var122".
							guard$sample136gaussian169[sample][timeStep$var122] = true;
							
							// Processing sample task 170 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Substituted "index$sample$72_2" with its value "sample".
							// 
							// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
							// the output of Sample task 136.
							// 
							// Value of the variable at this index
							if((cv$valuePos < noStates)) {
								// Variable declaration of cv$temp$35$var158 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								double cv$temp$35$var158 = metric_var[cv$valuePos];
								
								// Substituted "index$sample$72_2" with its value "sample".
								// 
								// cv$temp$34$var157's comment
								// Variable declaration of cv$temp$34$var157 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var122] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$35$var158))) - (Math.log(cv$temp$35$var158) * 0.5));
								
								// Recorded the probability of reaching sample task 170 with the current configuration.
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
						if(!guard$sample136gaussian169[sample][timeStep$var122]) {
							// The body will execute, so should not be executed again
							// 
							// Substituted "timeStep$var145" with its value "timeStep$var122".
							guard$sample136gaussian169[sample][timeStep$var122] = true;
							
							// Processing sample task 170 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((cv$valuePos < noStates)) {
								// Variable declaration of cv$temp$67$var158 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
								// the output of Sample task 136.
								// 
								// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
								// the output of Sample task 136.
								// 
								// Value of the variable at this index
								double cv$temp$67$var158 = metric_var[cv$valuePos];
								
								// Substituted "index$sample$76_2" with its value "sample".
								// 
								// cv$temp$66$var157's comment
								// Variable declaration of cv$temp$66$var157 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
								// the output of Sample task 136.
								// 
								// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
								// the output of Sample task 136.
								// 
								// Value of the variable at this index
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var122] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$67$var158))) - (Math.log(cv$temp$67$var158) * 0.5));
								
								// Recorded the probability of reaching sample task 170 with the current configuration.
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
			int index$timeStep$269_3 = (timeStep$var122 + 1);
			if((index$timeStep$269_3 < length$metric[sample])) {
				// Processing sample task 136 of consumer random variable null.
				// A local array to hold the accumulated distributions of the sample tasks for each
				// configuration of distributions.
				double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var129[threadID$cv$sample];
				
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$accumulatedConsumerDistributions[cv$i] = 0.0;
				
				// Zero an accumulator to track the probabilities reached.
				double cv$reachedDistributionProbability = 0.0;
				
				// Processing random variable 129.
				// 
				// Looking for a path between Sample 136 and consumer Categorical 129.
				// 
				// Value of the variable at this index
				if((cv$valuePos < noStates)) {
					// Declare and zero an accumulator for tracking the reached source probability space.
					double scopeVariable$reachedSourceProbability = 0.0;
					
					// Enumerating the possible arguments for Categorical 129.
					if((1 == timeStep$var122)) {
						// Enumerating the possible arguments for Categorical 129.
						if(fixedFlag$sample117) {
							int index$var39$280_1 = st[sample][0];
							
							// Substituted "timeStep$var122" with its value "1".
							if(((0 <= index$var39$280_1) && (index$var39$280_1 < noStates)))
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							// Enumerating the possible outputs of Categorical 110.
							for(int index$sample117$276 = 0; index$sample117$276 < noStates; index$sample117$276 += 1)
								// Add the probability of this argument configuration.
								// 
								// cv$probabilitySample117Value277's comment
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "index$sample$275" with its value "sample".
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample117[sample][index$sample117$276]);
						}
					}
					int index$timeStep$284 = (timeStep$var122 - 1);
					
					// index$timeStep$271's comment
					// Copy of index so that its values can be safely substituted
					// 
					// Substituted "index$timeStep$269_3" with its value "(timeStep$var122 + 1)".
					// 
					// Substituted "index$timeStep$269_3" with its value "(timeStep$var122 + 1)".
					// 
					// Substituted "index$timeStep$269_3" with its value "(timeStep$var122 + 1)".
					// 
					// Substituted "index$timeStep$269_3" with its value "(timeStep$var122 + 1)".
					if((((1 <= index$timeStep$284) && !(index$timeStep$284 == timeStep$var122)) && !(index$timeStep$284 == index$timeStep$269_3))) {
						// Enumerating the possible outputs of Categorical 129.
						for(int index$sample136$285 = 0; index$sample136$285 < noStates; index$sample136$285 += 1)
							// Add the probability of this argument configuration.
							// 
							// cv$probabilitySample136Value286's comment
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$sample$283" with its value "sample".
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample136[sample][(index$timeStep$284 - 1)][index$sample136$285]);
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
					// cv$temp$72$var128's comment
					// Variable declaration of cv$temp$72$var128 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Processing random variable 129.
					// 
					// Looking for a path between Sample 136 and consumer Categorical 129.
					// 
					// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				}
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "index$sample$269_2" with its value "sample".
				double[] cv$sampleDistribution = distribution$sample136[sample][(index$timeStep$269_3 - 1)];
				
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
		double[] cv$localProbability = distribution$sample136[sample][(timeStep$var122 - 1)];
		
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
	// by sample task 158 drawn from Bernoulli 149. Inference was performed using variable
	// marginalization.
	private final void sample158(int sample, int timeStep$var145, int threadID$cv$timeStep$var145, Rng RNG$) {}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 30 drawn from Dirichlet 26. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample30() {
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var27$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample117) {
			// Processing random variable 110.
			for(int sample = 0; sample < noSamples; sample += 1)
				// Processing sample task 117 of consumer random variable null.
				// 
				// Increment the sample counter with the value sampled by sample task 117 of random
				// variable var110
				// 
				// A local reference to the scratch space.
				cv$var27$countGlobal[st[sample][0]] = (cv$var27$countGlobal[st[sample][0]] + 1.0);
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			for(int sample = 0; sample < noSamples; sample += 1) {
				// Processing sample task 117 of consumer random variable null.
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
					cv$var27$countGlobal[cv$loopIndex] = (cv$var27$countGlobal[cv$loopIndex] + distribution$sample117[sample][cv$loopIndex]);
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var27$countGlobal, initialStateDistribution);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 43 drawn from Dirichlet 28. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample43(int var39, int threadID$cv$var39, Rng RNG$) {
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var40$countGlobal[threadID$cv$var39];
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample136) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 < length$metric[sample])) {
					if(fixedFlag$sample117) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var39 == st[sample][0]))
							// Increment the sample counter with the value sampled by sample task 136 of random
							// variable var129
							// 
							// Substituted "timeStep$var122" with its value "1".
							cv$countLocal[st[sample][1]] = (cv$countLocal[st[sample][1]] + 1.0);
					} else
						// Processing sample task 136 of consumer random variable null.
						// 
						// Increment the sample counter with the value sampled by sample task 136 of random
						// variable var129
						// 
						// Substituted "index$sample$4" with its value "sample".
						// 
						// Substituted "index$sample117$5" with its value "var39".
						cv$countLocal[st[sample][1]] = (cv$countLocal[st[sample][1]] + distribution$sample117[sample][var39]);
				}
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var122 = 2; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
					if((var39 == st[sample][(timeStep$var122 - 1)]))
						// Processing sample task 136 of consumer random variable null.
						// 
						// Increment the sample counter with the value sampled by sample task 136 of random
						// variable var129
						cv$countLocal[st[sample][timeStep$var122]] = (cv$countLocal[st[sample][timeStep$var122]] + 1.0);
				}
			}
		}
		
		// Processing random variable 129.
		// 
		// Looking for a path between Sample 43 and consumer Categorical 129.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		else {
			for(int sample = 0; sample < noSamples; sample += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 < length$metric[sample])) {
					if(fixedFlag$sample117) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var39 == st[sample][0])) {
							// Merge the distribution probabilities into the count
							// 
							// Get the length of the array
							for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
								// The probability of reaching the consumer with this set of consumer arguments
								// 
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample136[sample][0][cv$loopIndex]);
						}
					} else {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
						// Substituted "index$sample$41" with its value "sample".
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						// 
						// Substituted "index$sample117$42" with its value "var39".
						double cv$distributionProbability = distribution$sample117[sample][var39];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							// Substituted "timeStep$var122" with its value "1".
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample136[sample][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
					int index$timeStep$52 = (timeStep$var122 - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Substituted "index$sample$51" with its value "sample".
					if((1 <= index$timeStep$52)) {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
						// Substituted "index$sample$51" with its value "sample".
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						// 
						// Substituted "index$sample136$53" with its value "var39".
						double cv$distributionProbability = distribution$sample136[sample][(index$timeStep$52 - 1)][var39];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample136[sample][(timeStep$var122 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		// 
		// A reference local to the function for the sample variable.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var39]);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 63 drawn from Uniform 47. Inference was performed using Metropolis-Hastings.
	private final void sample63(int var58, int threadID$cv$var58, Rng RNG$) {
		// The original value of the sample
		double cv$originalValue = metric_mean[var58];
		
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
			// Substituted "cv$temp$0$var45" with its value "0.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue <= 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY);
			
			// Processing random variable 159.
			// 
			// Looking for a path between Sample 63 and consumer Gaussian 159.
			for(int sample = 0; sample < noSamples; sample += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((metric_valid_g[sample][0] && (0 < length$metric[sample]))) {
					if(fixedFlag$sample117) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var58 == st[sample][0])) {
							// Processing sample task 170 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var74 = st[sample][0];
							
							// Substituted "timeStep$var145" with its value "0".
							if(((0 <= var74) && (var74 < noStates))) {
								// Variable declaration of cv$temp$3$var158 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "timeStep$var145" with its value "0".
								double cv$temp$3$var158 = metric_var[st[sample][0]];
								
								// Substituted "timeStep$var145" with its value "0".
								// 
								// cv$temp$2$var157's comment
								// Variable declaration of cv$temp$2$var157 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - cv$originalValue) / Math.sqrt(cv$temp$3$var158))) - (Math.log(cv$temp$3$var158) * 0.5));
								
								// Recorded the probability of reaching sample task 170 with the current configuration.
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
					} else {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample$4" with its value "sample".
						// 
						// Substituted "index$sample117$5" with its value "var58".
						double cv$probabilitySample117Value6 = distribution$sample117[sample][var58];
						
						// Variable declaration of cv$temp$9$var158 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
						// the output of Sample task 63.
						// 
						// Substituted "index$sample117$5" with its value "var58".
						double cv$temp$9$var158 = metric_var[var58];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 170 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "timeStep$var145" with its value "0".
						// 
						// cv$temp$8$var157's comment
						// Variable declaration of cv$temp$8$var157 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Set the current value to the current state of the tree.
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample117Value6) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - cv$originalValue) / Math.sqrt(cv$temp$9$var158)))) - (Math.log(cv$temp$9$var158) * 0.5));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 170 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 170 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample117Value6), 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							// 
							// Substituted "timeStep$var145" with its value "0".
							// 
							// cv$temp$9$var158's comment
							// Variable declaration of cv$temp$9$var158 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
							// the output of Sample task 63.
							// 
							// Substituted "index$sample117$5" with its value "var58".
							// 
							// cv$temp$9$var158's comment
							// Variable declaration of cv$temp$9$var158 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
							// the output of Sample task 63.
							// 
							// Substituted "index$sample117$5" with its value "var58".
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 1; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					if(metric_valid_g[sample][timeStep$var145]) {
						if(fixedFlag$sample136) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((var58 == st[sample][timeStep$var145])) {
								// Processing sample task 170 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var74 = st[sample][timeStep$var145];
								if(((0 <= var74) && (var74 < noStates))) {
									// Variable declaration of cv$temp$21$var158 moved.
									// 
									// Constructing a random variable input for use later.
									double cv$temp$21$var158 = metric_var[st[sample][timeStep$var145]];
									
									// cv$temp$20$var157's comment
									// Variable declaration of cv$temp$20$var157 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$originalValue) / Math.sqrt(cv$temp$21$var158))) - (Math.log(cv$temp$21$var158) * 0.5));
									
									// Recorded the probability of reaching sample task 170 with the current configuration.
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
						} else {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$sample$14" with its value "sample".
							// 
							// Substituted "index$sample136$16" with its value "var58".
							double cv$probabilitySample136Value17 = distribution$sample136[sample][(timeStep$var145 - 1)][var58];
							
							// Variable declaration of cv$temp$27$var158 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
							// the output of Sample task 63.
							// 
							// Substituted "index$sample136$16" with its value "var58".
							double cv$temp$27$var158 = metric_var[var58];
							
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Processing sample task 170 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// cv$temp$26$var157's comment
							// Variable declaration of cv$temp$26$var157 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample136Value17) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$originalValue) / Math.sqrt(cv$temp$27$var158)))) - (Math.log(cv$temp$27$var158) * 0.5));
							
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Recorded the probability of reaching sample task 170 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 170 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample136Value17), 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								// 
								// cv$temp$27$var158's comment
								// Variable declaration of cv$temp$27$var158 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
								// the output of Sample task 63.
								// 
								// Substituted "index$sample136$16" with its value "var58".
								// 
								// cv$temp$27$var158's comment
								// Variable declaration of cv$temp$27$var158 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
								// the output of Sample task 63.
								// 
								// Substituted "index$sample136$16" with its value "var58".
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
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
		metric_mean[var58] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$0$var45" with its value "0.0".
		double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue <= 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY);
		
		// Processing random variable 159.
		// 
		// Looking for a path between Sample 63 and consumer Gaussian 159.
		for(int sample = 0; sample < noSamples; sample += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((metric_valid_g[sample][0] && (0 < length$metric[sample]))) {
				if(fixedFlag$sample117) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var58 == st[sample][0])) {
						// Processing sample task 170 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var74 = st[sample][0];
						
						// Substituted "timeStep$var145" with its value "0".
						if(((0 <= var74) && (var74 < noStates))) {
							// Variable declaration of cv$temp$3$var158 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "timeStep$var145" with its value "0".
							double cv$temp$3$var158 = metric_var[st[sample][0]];
							
							// Substituted "timeStep$var145" with its value "0".
							// 
							// cv$temp$2$var157's comment
							// Variable declaration of cv$temp$2$var157 moved.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var158))) - (Math.log(cv$temp$3$var158) * 0.5));
							
							// Recorded the probability of reaching sample task 170 with the current configuration.
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
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample$4" with its value "sample".
					// 
					// Substituted "index$sample117$5" with its value "var58".
					double cv$probabilitySample117Value6 = distribution$sample117[sample][var58];
					
					// Variable declaration of cv$temp$9$var158 moved.
					// 
					// Constructing a random variable input for use later.
					// 
					// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
					// the output of Sample task 63.
					// 
					// Substituted "index$sample117$5" with its value "var58".
					double cv$temp$9$var158 = metric_var[var58];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 170 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "timeStep$var145" with its value "0".
					// 
					// cv$temp$8$var157's comment
					// Variable declaration of cv$temp$8$var157 moved.
					// 
					// Constructing a random variable input for use later.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample117Value6) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var158)))) - (Math.log(cv$temp$9$var158) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 170 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 170 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample117Value6), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Substituted "timeStep$var145" with its value "0".
						// 
						// cv$temp$9$var158's comment
						// Variable declaration of cv$temp$9$var158 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
						// the output of Sample task 63.
						// 
						// Substituted "index$sample117$5" with its value "var58".
						// 
						// cv$temp$9$var158's comment
						// Variable declaration of cv$temp$9$var158 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
						// the output of Sample task 63.
						// 
						// Substituted "index$sample117$5" with its value "var58".
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var145 = 1; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
				if(metric_valid_g[sample][timeStep$var145]) {
					if(fixedFlag$sample136) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var58 == st[sample][timeStep$var145])) {
							// Processing sample task 170 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var74 = st[sample][timeStep$var145];
							if(((0 <= var74) && (var74 < noStates))) {
								// Variable declaration of cv$temp$21$var158 moved.
								// 
								// Constructing a random variable input for use later.
								double cv$temp$21$var158 = metric_var[st[sample][timeStep$var145]];
								
								// cv$temp$20$var157's comment
								// Variable declaration of cv$temp$20$var157 moved.
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$proposedValue) / Math.sqrt(cv$temp$21$var158))) - (Math.log(cv$temp$21$var158) * 0.5));
								
								// Recorded the probability of reaching sample task 170 with the current configuration.
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
					} else {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample$14" with its value "sample".
						// 
						// Substituted "index$sample136$16" with its value "var58".
						double cv$probabilitySample136Value17 = distribution$sample136[sample][(timeStep$var145 - 1)][var58];
						
						// Variable declaration of cv$temp$27$var158 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
						// the output of Sample task 63.
						// 
						// Substituted "index$sample136$16" with its value "var58".
						double cv$temp$27$var158 = metric_var[var58];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 170 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// cv$temp$26$var157's comment
						// Variable declaration of cv$temp$26$var157 moved.
						// 
						// Constructing a random variable input for use later.
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample136Value17) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$proposedValue) / Math.sqrt(cv$temp$27$var158)))) - (Math.log(cv$temp$27$var158) * 0.5));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 170 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 170 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample136Value17), 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							// 
							// cv$temp$27$var158's comment
							// Variable declaration of cv$temp$27$var158 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
							// the output of Sample task 63.
							// 
							// Substituted "index$sample136$16" with its value "var58".
							// 
							// cv$temp$27$var158's comment
							// Variable declaration of cv$temp$27$var158 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
							// the output of Sample task 63.
							// 
							// Substituted "index$sample136$16" with its value "var58".
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
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
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			metric_mean[var58] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 79 drawn from InverseGamma 63. Inference was performed using Metropolis-Hastings.
	private final void sample79(int var74, int threadID$cv$var74, Rng RNG$) {
		// The original value of the sample
		double cv$originalValue = metric_var[var74];
		
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
			// Substituted "cv$temp$1$var62" with its value "1.0".
			// 
			// Set the current value to the current state of the tree.
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 1.0, 1.0);
			
			// Processing random variable 159.
			// 
			// Looking for a path between Sample 79 and consumer Gaussian 159.
			for(int sample = 0; sample < noSamples; sample += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((metric_valid_g[sample][0] && (0 < length$metric[sample]))) {
					if(fixedFlag$sample117) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var74 == st[sample][0])) {
							// Processing sample task 170 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var58 = st[sample][0];
							
							// Substituted "timeStep$var145" with its value "0".
							if(((0 <= var58) && (var58 < noStates))) {
								// Substituted "timeStep$var145" with its value "0".
								// 
								// cv$temp$2$var157's comment
								// Variable declaration of cv$temp$2$var157 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Substituted "timeStep$var145" with its value "0".
								// 
								// cv$temp$3$var158's comment
								// Variable declaration of cv$temp$3$var158 moved.
								// 
								// Constructing a random variable input for use later.
								// 
								// Set the current value to the current state of the tree.
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[st[sample][0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
								
								// Recorded the probability of reaching sample task 170 with the current configuration.
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
					} else {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample$4" with its value "sample".
						// 
						// Substituted "index$sample117$5" with its value "var74".
						double cv$probabilitySample117Value6 = distribution$sample117[sample][var74];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 170 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// Substituted "timeStep$var145" with its value "0".
						// 
						// cv$temp$9$var158's comment
						// Variable declaration of cv$temp$9$var158 moved.
						// 
						// Constructing a random variable input for use later.
						// 
						// Set the current value to the current state of the tree.
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample117Value6) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[var74]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 170 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 170 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample117Value6), 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							// 
							// Substituted "timeStep$var145" with its value "0".
							// 
							// The original value of the sample
							// 
							// The original value of the sample
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 1; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					if(metric_valid_g[sample][timeStep$var145]) {
						if(fixedFlag$sample136) {
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if((var74 == st[sample][timeStep$var145])) {
								// Processing sample task 170 of consumer random variable null.
								// 
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var58 = st[sample][timeStep$var145];
								if(((0 <= var58) && (var58 < noStates))) {
									// cv$temp$21$var158's comment
									// Variable declaration of cv$temp$21$var158 moved.
									// 
									// Constructing a random variable input for use later.
									// 
									// Set the current value to the current state of the tree.
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - metric_mean[st[sample][timeStep$var145]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
									
									// Recorded the probability of reaching sample task 170 with the current configuration.
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
						} else {
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$sample$14" with its value "sample".
							// 
							// Substituted "index$sample136$16" with its value "var74".
							double cv$probabilitySample136Value17 = distribution$sample136[sample][(timeStep$var145 - 1)][var74];
							
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Processing sample task 170 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
							// cv$temp$27$var158's comment
							// Variable declaration of cv$temp$27$var158 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Set the current value to the current state of the tree.
							double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample136Value17) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - metric_mean[var74]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
							
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
							// Declaration comment was:
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Recorded the probability of reaching sample task 170 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 170 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample136Value17), 0.0);
							
							// Multiply (log space add) in the probability of the sample task to the overall probability
							// for this configuration of the source random variable.
							if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
							else {
								// If the second value is -infinity.
								// 
								// The original value of the sample
								// 
								// The original value of the sample
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
								else
									cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
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
		metric_var[var74] = cv$proposedValue;
		
		// An accumulator to allow the value for each distribution to be constructed before
		// it is added to the index probabilities.
		// 
		// Substituted "cv$temp$1$var62" with its value "1.0".
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 1.0, 1.0);
		
		// Processing random variable 159.
		// 
		// Looking for a path between Sample 79 and consumer Gaussian 159.
		for(int sample = 0; sample < noSamples; sample += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((metric_valid_g[sample][0] && (0 < length$metric[sample]))) {
				if(fixedFlag$sample117) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var74 == st[sample][0])) {
						// Processing sample task 170 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var58 = st[sample][0];
						
						// Substituted "timeStep$var145" with its value "0".
						if(((0 <= var58) && (var58 < noStates))) {
							// Substituted "timeStep$var145" with its value "0".
							// 
							// cv$temp$2$var157's comment
							// Variable declaration of cv$temp$2$var157 moved.
							// 
							// Constructing a random variable input for use later.
							// 
							// Substituted "timeStep$var145" with its value "0".
							// 
							// cv$temp$3$var158's comment
							// Variable declaration of cv$temp$3$var158 moved.
							// 
							// Constructing a random variable input for use later.
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[st[sample][0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
							
							// Recorded the probability of reaching sample task 170 with the current configuration.
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
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample$4" with its value "sample".
					// 
					// Substituted "index$sample117$5" with its value "var74".
					double cv$probabilitySample117Value6 = distribution$sample117[sample][var74];
					
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Processing sample task 170 of consumer random variable null.
					// 
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					// 
					// Substituted "timeStep$var145" with its value "0".
					// 
					// cv$temp$9$var158's comment
					// Variable declaration of cv$temp$9$var158 moved.
					// 
					// Constructing a random variable input for use later.
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample117Value6) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[var74]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
					
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
					// Declaration comment was:
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Recorded the probability of reaching sample task 170 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 170 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample117Value6), 0.0);
					
					// Multiply (log space add) in the probability of the sample task to the overall probability
					// for this configuration of the source random variable.
					if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
					else {
						// If the second value is -infinity.
						// 
						// Substituted "timeStep$var145" with its value "0".
						// 
						// The proposed new value for the sample
						// 
						// The proposed new value for the sample
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
						else
							cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
					}
				}
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var145 = 1; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
				if(metric_valid_g[sample][timeStep$var145]) {
					if(fixedFlag$sample136) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var74 == st[sample][timeStep$var145])) {
							// Processing sample task 170 of consumer random variable null.
							// 
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var58 = st[sample][timeStep$var145];
							if(((0 <= var58) && (var58 < noStates))) {
								// cv$temp$21$var158's comment
								// Variable declaration of cv$temp$21$var158 moved.
								// 
								// Constructing a random variable input for use later.
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - metric_mean[st[sample][timeStep$var145]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
								
								// Recorded the probability of reaching sample task 170 with the current configuration.
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
					} else {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "index$sample$14" with its value "sample".
						// 
						// Substituted "index$sample136$16" with its value "var74".
						double cv$probabilitySample136Value17 = distribution$sample136[sample][(timeStep$var145 - 1)][var74];
						
						// Variable declaration of cv$accumulatedConsumerProbabilities moved.
						// Declaration comment was:
						// Processing sample task 170 of consumer random variable null.
						// 
						// Set an accumulator to sum the probabilities for each possible configuration of
						// inputs.
						// 
						// cv$temp$27$var158's comment
						// Variable declaration of cv$temp$27$var158 moved.
						// 
						// Constructing a random variable input for use later.
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample136Value17) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - metric_mean[var74]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
						
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Variable declaration of cv$consumerDistributionProbabilityAccumulator moved.
						// Declaration comment was:
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// Recorded the probability of reaching sample task 170 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						// 
						// A check to ensure rounding of floating point values can never result in a negative
						// value.
						// 
						// Recorded the probability of reaching sample task 170 with the current configuration.
						// 
						// Set an accumulator to record the consumer distributions not seen. Initially set
						// to 1 as seen values will be deducted from this value.
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample136Value17), 0.0);
						
						// Multiply (log space add) in the probability of the sample task to the overall probability
						// for this configuration of the source random variable.
						if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
						else {
							// If the second value is -infinity.
							// 
							// The proposed new value for the sample
							// 
							// The proposed new value for the sample
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
							else
								cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
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
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			metric_var[var74] = cv$originalValue;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 95 drawn from Beta 79. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample95(int var90, int threadID$cv$var90, Rng RNG$) {
		// Local variable to record the number of true samples.
		double cv$sum = 0.0;
		
		// Local variable to record the number of samples.
		double cv$count = 0.0;
		
		// Processing random variable 149.
		// 
		// Looking for a path between Sample 95 and consumer Bernoulli 149.
		for(int sample = 0; sample < noSamples; sample += 1) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((0 < length$metric[sample])) {
				if(fixedFlag$sample117) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var90 == st[sample][0])) {
						// Processing sample task 158 of consumer random variable null.
						// 
						// Include the value sampled by task 158 from random variable var149.
						// 
						// Increment the number of samples.
						cv$count = (cv$count + 1.0);
						
						// If the sample value was positive increase the count
						// 
						// Substituted "timeStep$var145" with its value "0".
						if(metric_valid_g[sample][0])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample$4" with its value "sample".
					// 
					// Substituted "index$sample117$5" with its value "var90".
					double cv$probabilitySample117Value6 = distribution$sample117[sample][var90];
					
					// Processing sample task 158 of consumer random variable null.
					// 
					// Include the value sampled by task 158 from random variable var149.
					// 
					// Increment the number of samples.
					cv$count = (cv$count + cv$probabilitySample117Value6);
					
					// If the sample value was positive increase the count
					// 
					// Substituted "timeStep$var145" with its value "0".
					if(metric_valid_g[sample][0])
						cv$sum = (cv$sum + cv$probabilitySample117Value6);
				}
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var145 = 1; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
				if(fixedFlag$sample136) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var90 == st[sample][timeStep$var145])) {
						// Processing sample task 158 of consumer random variable null.
						// 
						// Include the value sampled by task 158 from random variable var149.
						// 
						// Increment the number of samples.
						cv$count = (cv$count + 1.0);
						
						// If the sample value was positive increase the count
						if(metric_valid_g[sample][timeStep$var145])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$sample$14" with its value "sample".
					// 
					// Substituted "index$sample136$16" with its value "var90".
					double cv$probabilitySample136Value17 = distribution$sample136[sample][(timeStep$var145 - 1)][var90];
					
					// Processing sample task 158 of consumer random variable null.
					// 
					// Include the value sampled by task 158 from random variable var149.
					// 
					// Increment the number of samples.
					cv$count = (cv$count + cv$probabilitySample136Value17);
					
					// If the sample value was positive increase the count
					if(metric_valid_g[sample][timeStep$var145])
						cv$sum = (cv$sum + cv$probabilitySample136Value17);
				}
			}
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		metric_valid_bias[var90] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var27$countGlobal
		// 
		// Allocation of cv$var27$countGlobal for single threaded execution
		// 
		// Calculate the longest array this random variable could produce and allocate an
		// array large enough to handle this.
		cv$var27$countGlobal = new double[Math.max(0, noStates)];
		
		// Constructor for cv$var40$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			if((0 < noStates))
				cv$max = noStates;
			
			// Allocation of cv$var40$countGlobal for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var40$countGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var40$countGlobal[cv$index] = new double[cv$max];
		}
		
		// Constructor for cv$distributionAccumulator$var129
		{
			// Allocation of cv$distributionAccumulator$var129 for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$distributionAccumulator$var129 = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				// Variable to record the maximum value of Task Get 134. Initially set to the value
				// of putTask 44.
				cv$distributionAccumulator$var129[cv$index] = new double[noStates];
		}
		
		// Constructor for cv$var111$stateProbabilityGlobal
		{
			// Allocation of cv$var111$stateProbabilityGlobal for multithreaded execution
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var111$stateProbabilityGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var111$stateProbabilityGlobal[cv$index] = new double[noStates];
		}
		
		// Constructor for guard$sample117gaussian169$global
		{
			// Calculate the largest index of timeStep that is possible and allocate an array
			// to hold the guard for each of these.
			int cv$max_timeStep$var145 = 0;
			for(int sample = 0; sample < length$metric.length; sample += 1)
				cv$max_timeStep$var145 = Math.max(cv$max_timeStep$var145, length$metric[sample]);
			
			// Variable declaration of cv$max_sample moved.
			// Declaration comment was:
			// Calculate the largest index of sample that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_sample = length$metric.length;
			
			// Allocation of guard$sample117gaussian169$global for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			guard$sample117gaussian169$global = new boolean[cv$threadCount][][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				guard$sample117gaussian169$global[cv$index] = new boolean[cv$max_sample][cv$max_timeStep$var145];
		}
		
		// Constructor for cv$var130$stateProbabilityGlobal
		{
			// Allocation of cv$var130$stateProbabilityGlobal for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var130$stateProbabilityGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				// Variable to record the maximum value of Task Get 134. Initially set to the value
				// of putTask 44.
				cv$var130$stateProbabilityGlobal[cv$index] = new double[noStates];
		}
		
		// Constructor for guard$sample136gaussian169$global
		{
			// Calculate the largest index of timeStep that is possible and allocate an array
			// to hold the guard for each of these.
			int cv$max_timeStep$var145 = 0;
			for(int sample = 0; sample < length$metric.length; sample += 1)
				cv$max_timeStep$var145 = Math.max(cv$max_timeStep$var145, length$metric[sample]);
			
			// Variable declaration of cv$max_sample moved.
			// Declaration comment was:
			// Calculate the largest index of sample that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_sample = length$metric.length;
			
			// Allocation of guard$sample136gaussian169$global for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			guard$sample136gaussian169$global = new boolean[cv$threadCount][][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				guard$sample136gaussian169$global[cv$index] = new boolean[cv$max_sample][cv$max_timeStep$var145];
		}
		
		// Constructor for cv$var150$stateProbabilityGlobal
		// 
		// Allocation of cv$var150$stateProbabilityGlobal for multithreaded execution
		// 
		// Get the thread count.
		int cv$threadCount = threadCount();
		
		// Allocate an array to hold a copy per thread
		cv$var150$stateProbabilityGlobal = new double[cv$threadCount][];
		
		// Populate the array with a copy per thread
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var150$stateProbabilityGlobal[cv$index] = new double[2];
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for v
		v = new double[noStates];
		
		// If initialStateDistribution has not been set already allocate space.
		if(!setFlag$initialStateDistribution)
			// Constructor for initialStateDistribution
			initialStateDistribution = new double[noStates];
		
		// If m has not been set already allocate space.
		if(!setFlag$m) {
			// Constructor for m
			m = new double[noStates][];
			for(int var39 = 0; var39 < noStates; var39 += 1)
				m[var39] = new double[noStates];
		}
		
		// If st has not been set already allocate space.
		if(!setFlag$st) {
			// Constructor for st
			st = new int[length$metric.length][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				st[sample] = new int[length$metric[sample]];
		}
		
		// If metric_g has not been set already allocate space.
		if(!setFlag$metric_g) {
			// Constructor for metric_g
			metric_g = new double[length$metric.length][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				metric_g[sample] = new double[length$metric[sample]];
		}
		
		// If metric_valid_g has not been set already allocate space.
		if(!setFlag$metric_valid_g) {
			// Constructor for metric_valid_g
			metric_valid_g = new boolean[length$metric.length][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				metric_valid_g[sample] = new boolean[length$metric[sample]];
		}
		
		// If metric_mean has not been set already allocate space.
		if(!setFlag$metric_mean)
			// Constructor for metric_mean
			metric_mean = new double[noStates];
		
		// If metric_var has not been set already allocate space.
		if(!setFlag$metric_var)
			// Constructor for metric_var
			metric_var = new double[noStates];
		
		// If metric_valid_bias has not been set already allocate space.
		if(!setFlag$metric_valid_bias)
			// Constructor for metric_valid_bias
			metric_valid_bias = new double[noStates];
		
		// Constructor for distribution$sample117
		distribution$sample117 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			distribution$sample117[sample] = new double[noStates];
		
		// Constructor for distribution$sample136
		distribution$sample136 = new double[length$metric.length][][];
		for(int sample = 0; sample < length$metric.length; sample += 1) {
			double[][] subarray$0 = new double[(length$metric[sample] - 1)][];
			distribution$sample136[sample] = subarray$0;
			for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1)
				subarray$0[(timeStep$var122 - 1)] = new double[noStates];
		}
		
		// Constructor for logProbability$sample158
		logProbability$sample158 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$sample158[sample] = new double[length$metric[sample]];
		
		// Constructor for logProbability$sample170
		logProbability$sample170 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$sample170[sample] = new double[length$metric[sample]];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample30)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample43)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var39, int forEnd$var39, int threadID$var39, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var39 = forStart$var39; var39 < forEnd$var39; var39 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var39]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample63)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var58, int forEnd$var58, int threadID$var58, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var58 = forStart$var58; var58 < forEnd$var58; var58 += 1)
							metric_mean[var58] = (DistributionSampling.sampleUniform(RNG$1) * 100.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample79)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var74, int forEnd$var74, int threadID$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var74 = forStart$var74; var74 < forEnd$var74; var74 += 1)
							metric_var[var74] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample95)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var90, int forEnd$var90, int threadID$var90, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var90 = forStart$var90; var90 < forEnd$var90; var90 += 1)
							metric_valid_bias[var90] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
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
						if(!fixedFlag$sample117)
							st[sample][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample136) {
							int[] var123 = st[sample];
							for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1)
								var123[timeStep$var122] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample][(timeStep$var122 - 1)]]);
						}
						boolean[] metric_valid_1d = metric_valid_g[sample];
						double[] metric_1d = metric_g[sample];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, length$metric[sample], 1,
							(int forStart$timeStep$var145, int forEnd$timeStep$var145, int threadID$timeStep$var145, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int timeStep$var145 = forStart$timeStep$var145; timeStep$var145 < forEnd$timeStep$var145; timeStep$var145 += 1) {
										if(!fixedFlag$sample158)
											metric_valid_1d[timeStep$var145] = DistributionSampling.sampleBernoulli(RNG$2, metric_valid_bias[st[sample][timeStep$var145]]);
										if((metric_valid_g[sample][timeStep$var145] && (!fixedFlag$sample158 || !fixedFlag$sample170)))
											metric_1d[timeStep$var145] = ((Math.sqrt(metric_var[st[sample][timeStep$var145]]) * DistributionSampling.sampleGaussian(RNG$2)) + metric_mean[st[sample][timeStep$var145]]);
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
		if(!fixedFlag$sample30)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample43)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var39, int forEnd$var39, int threadID$var39, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var39 = forStart$var39; var39 < forEnd$var39; var39 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var39]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample63)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var58, int forEnd$var58, int threadID$var58, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var58 = forStart$var58; var58 < forEnd$var58; var58 += 1)
							metric_mean[var58] = (DistributionSampling.sampleUniform(RNG$1) * 100.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample79)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var74, int forEnd$var74, int threadID$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var74 = forStart$var74; var74 < forEnd$var74; var74 += 1)
							metric_var[var74] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample95)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var90, int forEnd$var90, int threadID$var90, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var90 = forStart$var90; var90 < forEnd$var90; var90 += 1)
							metric_valid_bias[var90] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
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
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample117) {
							// Create local copy of variable probabilities.
							double[] cv$distribution$sample117 = distribution$sample117[sample];
							for(int index$var110 = 0; index$var110 < noStates; index$var110 += 1)
								// Save the probability of each value
								// 
								// Probability for this value
								cv$distribution$sample117[index$var110] = ((index$var110 < initialStateDistribution.length)?initialStateDistribution[index$var110]:0.0);
						}
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample136) {
							for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
								// Create local copy of variable probabilities.
								double[] cv$distribution$sample136 = distribution$sample136[sample][(timeStep$var122 - 1)];
								for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1)
									// Zero the probability of each value
									cv$distribution$sample136[index$var129] = 0.0;
								
								// Iterate through possible values for var129's arguments.
								// 
								// Enumerating the possible arguments for Categorical 129.
								if((1 == timeStep$var122)) {
									// Iterate through possible values for var129's arguments.
									// 
									// Enumerating the possible arguments for Categorical 129.
									if(fixedFlag$sample117) {
										int var39 = st[sample][0];
										
										// Substituted "timeStep$var122" with its value "1".
										if(((0 <= var39) && (var39 < noStates))) {
											// Substituted "timeStep$var122" with its value "1".
											double[] var128 = m[st[sample][0]];
											for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1)
												// Save the probability of each value
												cv$distribution$sample136[index$var129] = (cv$distribution$sample136[index$var129] + ((index$var129 < var128.length)?var128[index$var129]:0.0));
										}
									} else {
										// Enumerating the possible outputs of Categorical 110.
										for(int index$sample117$3 = 0; index$sample117$3 < noStates; index$sample117$3 += 1) {
											// Update the probability of sampling this value from the distribution value.
											// 
											// Substituted "index$sample$2" with its value "sample".
											double cv$probabilitySample117Value4 = distribution$sample117[sample][index$sample117$3];
											double[] var128 = m[index$sample117$3];
											for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1)
												// Save the probability of each value
												cv$distribution$sample136[index$var129] = (cv$distribution$sample136[index$var129] + (cv$probabilitySample117Value4 * ((index$var129 < var128.length)?var128[index$var129]:0.0)));
										}
									}
								}
								int index$timeStep$11 = (timeStep$var122 - 1);
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
								// 
								// Substituted "index$sample$10" with its value "sample".
								if((1 <= index$timeStep$11)) {
									// Enumerating the possible outputs of Categorical 129.
									for(int index$sample136$12 = 0; index$sample136$12 < noStates; index$sample136$12 += 1) {
										// Update the probability of sampling this value from the distribution value.
										// 
										// Substituted "index$sample$10" with its value "sample".
										double cv$probabilitySample136Value13 = distribution$sample136[sample][(index$timeStep$11 - 1)][index$sample136$12];
										double[] var128 = m[index$sample136$12];
										for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1)
											// Save the probability of each value
											cv$distribution$sample136[index$var129] = (cv$distribution$sample136[index$var129] + (cv$probabilitySample136Value13 * ((index$var129 < var128.length)?var128[index$var129]:0.0)));
									}
								}
								
								// Sum the values in the array
								double cv$var129$sum = 0.0;
								for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1)
									// sum the probability of each value
									cv$var129$sum = (cv$var129$sum + cv$distribution$sample136[index$var129]);
								for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1)
									// Normalise the probability of each value
									cv$distribution$sample136[index$var129] = (cv$distribution$sample136[index$var129] / cv$var129$sum);
							}
						}
						boolean[] metric_valid_1d = metric_valid_g[sample];
						double[] metric_1d = metric_g[sample];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, length$metric[sample], 1,
							(int forStart$timeStep$var145, int forEnd$timeStep$var145, int threadID$timeStep$var145, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int timeStep$var145 = forStart$timeStep$var145; timeStep$var145 < forEnd$timeStep$var145; timeStep$var145 += 1) {
										if(!fixedFlag$sample158)
											metric_valid_1d[timeStep$var145] = DistributionSampling.sampleBernoulli(RNG$2, metric_valid_bias[st[sample][timeStep$var145]]);
										if((metric_valid_g[sample][timeStep$var145] && (!fixedFlag$sample158 || !fixedFlag$sample170)))
											metric_1d[timeStep$var145] = ((Math.sqrt(metric_var[st[sample][timeStep$var145]]) * DistributionSampling.sampleGaussian(RNG$2)) + metric_mean[st[sample][timeStep$var145]]);
									}
							}
						);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample30)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample43)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var39, int forEnd$var39, int threadID$var39, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var39 = forStart$var39; var39 < forEnd$var39; var39 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var39]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample63)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var58, int forEnd$var58, int threadID$var58, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var58 = forStart$var58; var58 < forEnd$var58; var58 += 1)
							metric_mean[var58] = (DistributionSampling.sampleUniform(RNG$1) * 100.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample79)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var74, int forEnd$var74, int threadID$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var74 = forStart$var74; var74 < forEnd$var74; var74 += 1)
							metric_var[var74] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample95)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var90, int forEnd$var90, int threadID$var90, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var90 = forStart$var90; var90 < forEnd$var90; var90 += 1)
							metric_valid_bias[var90] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
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
						if(!fixedFlag$sample117)
							st[sample][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample136) {
							int[] var123 = st[sample];
							for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1)
								var123[timeStep$var122] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample][(timeStep$var122 - 1)]]);
						}
						boolean[] metric_valid_1d = metric_valid_g[sample];
						double[] metric_1d = metric_g[sample];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, length$metric[sample], 1,
							(int forStart$timeStep$var145, int forEnd$timeStep$var145, int threadID$timeStep$var145, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int timeStep$var145 = forStart$timeStep$var145; timeStep$var145 < forEnd$timeStep$var145; timeStep$var145 += 1) {
										if(!fixedFlag$sample158)
											metric_valid_1d[timeStep$var145] = DistributionSampling.sampleBernoulli(RNG$2, metric_valid_bias[st[sample][timeStep$var145]]);
										if((metric_valid_g[sample][timeStep$var145] && (!fixedFlag$sample158 || !fixedFlag$sample170)))
											metric_1d[timeStep$var145] = ((Math.sqrt(metric_var[st[sample][timeStep$var145]]) * DistributionSampling.sampleGaussian(RNG$2)) + metric_mean[st[sample][timeStep$var145]]);
									}
							}
						);
					}
			}
		);
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample30)
				sample30();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample43)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var39, int forEnd$var39, int threadID$var39, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var39 = forStart$var39; var39 < forEnd$var39; var39 += 1)
								sample43(var39, threadID$var39, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample63)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var58, int forEnd$var58, int threadID$var58, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var58 = forStart$var58; var58 < forEnd$var58; var58 += 1)
								sample63(var58, threadID$var58, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample79)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var74, int forEnd$var74, int threadID$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var74 = forStart$var74; var74 < forEnd$var74; var74 += 1)
								sample79(var74, threadID$var74, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample95)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var90, int forEnd$var90, int threadID$var90, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var90 = forStart$var90; var90 < forEnd$var90; var90 += 1)
								sample95(var90, threadID$var90, RNG$1);
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
							if(!fixedFlag$sample117)
								sample117(sample, threadID$sample, RNG$1);
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!fixedFlag$sample136) {
								for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1)
									sample136(sample, timeStep$var122, threadID$sample, RNG$1);
							}
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!fixedFlag$sample158)
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, length$metric[sample], 1,
									(int forStart$timeStep$var145, int forEnd$timeStep$var145, int threadID$timeStep$var145, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int timeStep$var145 = forStart$timeStep$var145; timeStep$var145 < forEnd$timeStep$var145; timeStep$var145 += 1)
												sample158(sample, timeStep$var145, threadID$timeStep$var145, RNG$2);
									}
								);

						}
				}
			);
		}
		// Infer the samples in reverse chronological order.
		else {
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$index$sample, int forEnd$index$sample, int threadID$index$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int index$sample = forStart$index$sample; index$sample < forEnd$index$sample; index$sample += 1) {
							int sample = index$sample;
							int threadID$sample = threadID$index$sample;
							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!fixedFlag$sample158)
								//  Outer loop for dispatching multiple batches of iterations to execute in parallel
								parallelFor(RNG$1, 0, length$metric[sample], 1,
									(int forStart$timeStep$var145, int forEnd$timeStep$var145, int threadID$timeStep$var145, org.sandwood.random.internal.Rng RNG$2) -> { 
										
											// Inner loop for running batches of iterations, each batch has its own random number
											// generator.
											for(int timeStep$var145 = forStart$timeStep$var145; timeStep$var145 < forEnd$timeStep$var145; timeStep$var145 += 1)
												sample158(sample, timeStep$var145, threadID$timeStep$var145, RNG$2);
									}
								);

							
							// Constraints moved from conditionals in inner loops/scopes/etc.
							if(!fixedFlag$sample136) {
								for(int timeStep$var122 = (length$metric[sample] - 1); timeStep$var122 >= 1; timeStep$var122 -= 1)
									sample136(sample, timeStep$var122, threadID$sample, RNG$1);
							}
							if(!fixedFlag$sample117)
								sample117(sample, threadID$sample, RNG$1);
						}
				}
			);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample95)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var90, int forEnd$var90, int threadID$var90, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var90 = forStart$var90; var90 < forEnd$var90; var90 += 1)
								sample95(var90, threadID$var90, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample79)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var74, int forEnd$var74, int threadID$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var74 = forStart$var74; var74 < forEnd$var74; var74 += 1)
								sample79(var74, threadID$var74, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample63)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var58, int forEnd$var58, int threadID$var58, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var58 = forStart$var58; var58 < forEnd$var58; var58 += 1)
								sample63(var58, threadID$var58, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample43)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var39, int forEnd$var39, int threadID$var39, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var39 = forStart$var39; var39 < forEnd$var39; var39 += 1)
								sample43(var39, threadID$var39, RNG$1);
					}
				);

			if(!fixedFlag$sample30)
				sample30();
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
			(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1)
						v[var23] = 0.1;
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
		logProbability$var26 = 0.0;
		if(!fixedProbFlag$sample30)
			logProbability$initialStateDistribution = 0.0;
		logProbability$var28 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample43)
			logProbability$var40 = 0.0;
		logProbability$var47 = 0.0;
		logProbability$metric_mean = 0.0;
		if(!fixedProbFlag$sample63)
			logProbability$var59 = 0.0;
		logProbability$var63 = 0.0;
		logProbability$metric_var = 0.0;
		if(!fixedProbFlag$sample79)
			logProbability$var75 = 0.0;
		logProbability$var79 = 0.0;
		logProbability$metric_valid_bias = 0.0;
		if(!fixedProbFlag$sample95)
			logProbability$var91 = 0.0;
		logProbability$var110 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample117)
			logProbability$var111 = 0.0;
		logProbability$var129 = 0.0;
		if(!fixedProbFlag$sample136)
			logProbability$var130 = 0.0;
		logProbability$var149 = 0.0;
		logProbability$metric_g = 0.0;
		logProbability$metric_valid_1d = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample158) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1)
					logProbability$sample158[sample][timeStep$var145] = 0.0;
			}
		}
		logProbability$var159 = 0.0;
		logProbability$metric_1d = 0.0;
		if(!fixedProbFlag$sample170) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1)
					logProbability$sample170[sample][timeStep$var145] = 0.0;
			}
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
		if(fixedFlag$sample30)
			logProbabilityValue$sample30();
		if(fixedFlag$sample43)
			logProbabilityValue$sample43();
		if(fixedFlag$sample63)
			logProbabilityValue$sample63();
		if(fixedFlag$sample79)
			logProbabilityValue$sample79();
		if(fixedFlag$sample95)
			logProbabilityValue$sample95();
		logProbabilityValue$sample158();
		logProbabilityValue$sample170();
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
		logProbabilityValue$sample30();
		logProbabilityValue$sample43();
		logProbabilityValue$sample63();
		logProbabilityValue$sample79();
		logProbabilityValue$sample95();
		logProbabilityDistribution$sample117();
		logProbabilityDistribution$sample136();
		logProbabilityDistribution$sample158();
		logProbabilityDistribution$sample170();
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
		logProbabilityValue$sample30();
		logProbabilityValue$sample43();
		logProbabilityValue$sample63();
		logProbabilityValue$sample79();
		logProbabilityValue$sample95();
		logProbabilityValue$sample117();
		logProbabilityValue$sample136();
		logProbabilityValue$sample158();
		logProbabilityValue$sample170();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample30)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample43)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var39, int forEnd$var39, int threadID$var39, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var39 = forStart$var39; var39 < forEnd$var39; var39 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var39]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample63)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var58, int forEnd$var58, int threadID$var58, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var58 = forStart$var58; var58 < forEnd$var58; var58 += 1)
							metric_mean[var58] = (DistributionSampling.sampleUniform(RNG$1) * 100.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample79)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var74, int forEnd$var74, int threadID$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var74 = forStart$var74; var74 < forEnd$var74; var74 += 1)
							metric_var[var74] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample95)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var90, int forEnd$var90, int threadID$var90, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var90 = forStart$var90; var90 < forEnd$var90; var90 += 1)
							metric_valid_bias[var90] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
						if(!fixedFlag$sample117)
							st[sample][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution);
						
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if(!fixedFlag$sample136) {
							int[] var123 = st[sample];
							for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1)
								var123[timeStep$var122] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample][(timeStep$var122 - 1)]]);
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
			for(int timeStep$var145 = (length$metric[sample] - 1); timeStep$var145 >= 0; timeStep$var145 -= 1)
				metric_g[sample][timeStep$var145] = metric[sample][timeStep$var145];
		}
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