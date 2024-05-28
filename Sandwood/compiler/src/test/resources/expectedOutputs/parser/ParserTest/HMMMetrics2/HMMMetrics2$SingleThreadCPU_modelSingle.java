package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics2$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMMetrics2$CoreInterface {
	
	// Declare the variables for the model.
	private double[] cv$distributionAccumulator$var129;
	private double[] cv$var111$stateProbabilityGlobal;
	private double[] cv$var130$stateProbabilityGlobal;
	private double[] cv$var150$stateProbabilityGlobal;
	private double[] cv$var27$countGlobal;
	private double[] cv$var40$countGlobal;
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
	private boolean[][] guard$sample117gaussian169$global;
	private boolean[][] guard$sample136gaussian169$global;
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

	public HMMMetrics2$SingleThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample117 = (fixedFlag$sample117 && fixedProbFlag$sample117);
		
		// Should the probability of sample 136 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample136 = (fixedFlag$sample117 && fixedProbFlag$sample136);
		
		// Should the probability of sample 158 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample158 = (fixedFlag$sample117 && fixedProbFlag$sample158);
		
		// Should the probability of sample 170 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample170 = (fixedFlag$sample117 && fixedProbFlag$sample170);
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
		fixedProbFlag$sample136 = (fixedFlag$sample136 && fixedProbFlag$sample136);
		
		// Should the probability of sample 158 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample158 = (fixedFlag$sample136 && fixedProbFlag$sample158);
		
		// Should the probability of sample 170 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample170 = (fixedFlag$sample136 && fixedProbFlag$sample170);
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
		fixedProbFlag$sample158 = (fixedFlag$sample158 && fixedProbFlag$sample158);
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
		fixedProbFlag$sample170 = (fixedFlag$sample170 && fixedProbFlag$sample170);
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
		fixedProbFlag$sample30 = (fixedFlag$sample30 && fixedProbFlag$sample30);
		
		// Should the probability of sample 117 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample117 = (fixedFlag$sample30 && fixedProbFlag$sample117);
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
		fixedProbFlag$sample43 = (fixedFlag$sample43 && fixedProbFlag$sample43);
		
		// Should the probability of sample 136 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample136 = (fixedFlag$sample43 && fixedProbFlag$sample136);
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
		fixedProbFlag$sample63 = (fixedFlag$sample63 && fixedProbFlag$sample63);
		
		// Should the probability of sample 170 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample170 = (fixedFlag$sample63 && fixedProbFlag$sample170);
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
		fixedProbFlag$sample79 = (fixedFlag$sample79 && fixedProbFlag$sample79);
		
		// Should the probability of sample 170 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample170 = (fixedFlag$sample79 && fixedProbFlag$sample170);
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
		fixedProbFlag$sample95 = (fixedFlag$sample95 && fixedProbFlag$sample95);
		
		// Should the probability of sample 158 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample158 = (fixedFlag$sample95 && fixedProbFlag$sample158);
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
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int sample = 0; sample < noSamples; sample += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Copy of index so that its values can be safely substituted
					int index$sample$1 = sample;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[sample][0];
						{
							{
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
					}
					if((cv$probabilityReached == 0.0))
						// Return negative infinity if no distribution probability space is reached.
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						// Scale the probability relative to the observed distribution space.
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				}
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var110 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				logProbability$var111 = cv$accumulator;
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(fixedFlag$sample117)
					// Update the variable probability
					logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample117)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample117 = (fixedFlag$sample117 && fixedFlag$sample30);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var111;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var110 = cv$rvAccumulator;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample117)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample117)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
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
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				for(int sample = 0; sample < noSamples; sample += 1) {
					for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// Look for paths between the variable and the sample task 136 including any distribution
						// values.
						// 
						// Copy of index so that its values can be safely substituted
						int index$timeStep$1 = timeStep$var122;
						
						// Copy of index so that its values can be safely substituted
						int index$sample$2 = sample;
						{
							// The sample value to calculate the probability of generating
							int cv$sampleValue = st[sample][timeStep$var122];
							
							// Enumerating the possible arguments for Categorical 129.
							if(fixedFlag$sample117) {
								for(int index$sample$4_1 = 0; index$sample$4_1 < noSamples; index$sample$4_1 += 1) {
									if((index$sample$4_1 == sample)) {
										if((0 == (timeStep$var122 - 1))) {
											for(int var39 = 0; var39 < noStates; var39 += 1) {
												if((var39 == st[sample][(timeStep$var122 - 1)])) {
													{
														double[] var128 = m[st[sample][(timeStep$var122 - 1)]];
														
														// Store the value of the function call, so the function call is only made once.
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var128.length))?Math.log(var128[cv$sampleValue]):Double.NEGATIVE_INFINITY));
														
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
											}
										}
									}
								}
							} else {
								for(int index$sample$5 = 0; index$sample$5 < noSamples; index$sample$5 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 110.
										for(int index$sample117$6 = 0; index$sample117$6 < noStates; index$sample117$6 += 1) {
											int distributionTempVariable$var111$8 = index$sample117$6;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample117Value7 = (1.0 * distribution$sample117[((index$sample$5 - 0) / 1)][index$sample117$6]);
											int traceTempVariable$var127$9_1 = distributionTempVariable$var111$8;
											if((index$sample$5 == sample)) {
												if((0 == (timeStep$var122 - 1))) {
													for(int var39 = 0; var39 < noStates; var39 += 1) {
														if((var39 == traceTempVariable$var127$9_1)) {
															{
																double[] var128 = m[traceTempVariable$var127$9_1];
																
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
												}
											}
										}
									}
								}
							}
							
							// Enumerating the possible arguments for Categorical 129.
							if((index$sample$2 == sample)) {
								if((index$timeStep$1 == (timeStep$var122 - 1))) {
									for(int var39 = 0; var39 < noStates; var39 += 1) {
										if((var39 == st[sample][(timeStep$var122 - 1)])) {
											{
												double[] var128 = m[st[sample][(timeStep$var122 - 1)]];
												
												// Store the value of the function call, so the function call is only made once.
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var128.length))?Math.log(var128[cv$sampleValue]):Double.NEGATIVE_INFINITY));
												
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
									}
								}
							}
							if(fixedFlag$sample136) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1]; index$timeStep$13_2 += 1) {
										if((index$sample$13_1 == sample)) {
											if((index$timeStep$13_2 == (timeStep$var122 - 1))) {
												for(int var39 = 0; var39 < noStates; var39 += 1) {
													if((var39 == st[sample][(timeStep$var122 - 1)])) {
														{
															double[] var128 = m[st[sample][(timeStep$var122 - 1)]];
															
															// Store the value of the function call, so the function call is only made once.
															double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var128.length))?Math.log(var128[cv$sampleValue]):Double.NEGATIVE_INFINITY));
															
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
												}
											}
										}
									}
								}
							} else {
								for(int index$sample$14 = 0; index$sample$14 < noSamples; index$sample$14 += 1) {
									for(int index$timeStep$15 = 1; index$timeStep$15 < length$metric[index$sample$14]; index$timeStep$15 += 1) {
										if(!((index$sample$14 == index$sample$2) && (index$timeStep$15 == index$timeStep$1))) {
											// Enumerating the possible outputs of Categorical 129.
											for(int index$sample136$16 = 0; index$sample136$16 < noStates; index$sample136$16 += 1) {
												int distributionTempVariable$var130$18 = index$sample136$16;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample136Value17 = (1.0 * distribution$sample136[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample136$16]);
												int traceTempVariable$var127$19_1 = distributionTempVariable$var130$18;
												if((index$sample$14 == sample)) {
													if((index$timeStep$15 == (timeStep$var122 - 1))) {
														for(int var39 = 0; var39 < noStates; var39 += 1) {
															if((var39 == traceTempVariable$var127$19_1)) {
																{
																	double[] var128 = m[traceTempVariable$var127$19_1];
																	
																	// Store the value of the function call, so the function call is only made once.
																	double cv$weightedProbability = (Math.log(cv$probabilitySample136Value17) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var128.length))?Math.log(var128[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																	
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
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample136Value17);
																}
															}
														}
													}
												}
											}
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
						double cv$sampleProbability = cv$distributionAccumulator;
						
						// Add the probability of this sample task to the sample task accumulator.
						cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					}
				}
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var129 = cv$sampleAccumulator;
				
				// Store the random variable instance probability
				logProbability$var130 = cv$accumulator;
				
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if(fixedFlag$sample136)
					// Update the variable probability
					logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample136)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample136 = ((fixedFlag$sample136 && fixedFlag$sample43) && fixedFlag$sample117);
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var130;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var129 = cv$rvAccumulator;
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample136)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample136)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample158 using probability
	// distributions.
	private final void logProbabilityDistribution$sample158() {
		// Determine if we need to calculate the values for sample task 158 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample158) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
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
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = metric_valid_g[sample][timeStep$var145];
						
						// Enumerating the possible arguments for Bernoulli 149.
						if(fixedFlag$sample117) {
							for(int index$sample$2_1 = 0; index$sample$2_1 < noSamples; index$sample$2_1 += 1) {
								if((index$sample$2_1 == sample)) {
									if((0 == timeStep$var145)) {
										for(int var90 = 0; var90 < noStates; var90 += 1) {
											if((var90 == st[sample][timeStep$var145])) {
												{
													double var148 = metric_valid_bias[st[sample][timeStep$var145]];
													
													// Store the value of the function call, so the function call is only made once.
													double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var148));
													
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
										}
									}
								}
							}
						} else {
							for(int index$sample$3 = 0; index$sample$3 < noSamples; index$sample$3 += 1) {
								if(true) {
									// Enumerating the possible outputs of Categorical 110.
									for(int index$sample117$4 = 0; index$sample117$4 < noStates; index$sample117$4 += 1) {
										int distributionTempVariable$var111$6 = index$sample117$4;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample117Value5 = (1.0 * distribution$sample117[((index$sample$3 - 0) / 1)][index$sample117$4]);
										int traceTempVariable$currentState$7_1 = distributionTempVariable$var111$6;
										if((index$sample$3 == sample)) {
											if((0 == timeStep$var145)) {
												for(int var90 = 0; var90 < noStates; var90 += 1) {
													if((var90 == traceTempVariable$currentState$7_1)) {
														{
															double var148 = metric_valid_bias[traceTempVariable$currentState$7_1];
															
															// Store the value of the function call, so the function call is only made once.
															double cv$weightedProbability = (Math.log(cv$probabilitySample117Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var148));
															
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
											}
										}
									}
								}
							}
						}
						
						// Enumerating the possible arguments for Bernoulli 149.
						if(fixedFlag$sample136) {
							for(int index$sample$10_1 = 0; index$sample$10_1 < noSamples; index$sample$10_1 += 1) {
								for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$10_1]; timeStep$var122 += 1) {
									if((index$sample$10_1 == sample)) {
										if((timeStep$var122 == timeStep$var145)) {
											for(int var90 = 0; var90 < noStates; var90 += 1) {
												if((var90 == st[sample][timeStep$var145])) {
													{
														double var148 = metric_valid_bias[st[sample][timeStep$var145]];
														
														// Store the value of the function call, so the function call is only made once.
														double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var148));
														
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
											}
										}
									}
								}
							}
						} else {
							for(int index$sample$11 = 0; index$sample$11 < noSamples; index$sample$11 += 1) {
								for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$11]; timeStep$var122 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 129.
										for(int index$sample136$13 = 0; index$sample136$13 < noStates; index$sample136$13 += 1) {
											int distributionTempVariable$var130$15 = index$sample136$13;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample136Value14 = (1.0 * distribution$sample136[((index$sample$11 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$13]);
											int traceTempVariable$currentState$16_1 = distributionTempVariable$var130$15;
											if((index$sample$11 == sample)) {
												if((timeStep$var122 == timeStep$var145)) {
													for(int var90 = 0; var90 < noStates; var90 += 1) {
														if((var90 == traceTempVariable$currentState$16_1)) {
															{
																double var148 = metric_valid_bias[traceTempVariable$currentState$16_1];
																
																// Store the value of the function call, so the function call is only made once.
																double cv$weightedProbability = (Math.log(cv$probabilitySample136Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var148));
																
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
												}
											}
										}
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
					double cv$sampleProbability = cv$distributionAccumulator;
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					
					// Store the sample task probability
					logProbability$sample158[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = cv$sampleProbability;
					
					// Guard to ensure that metric_valid_g is only updated once for this probability.
					boolean cv$guard$metric_valid_g = false;
					
					// Guard to ensure that metric_g is only updated once for this probability.
					boolean cv$guard$metric_g = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					{
						// If the probability of the variable has not already been updated
						if(!cv$guard$metric_valid_g) {
							// Set the guard so the update is only applied once.
							cv$guard$metric_valid_g = true;
							
							// Update the variable probability
							logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleProbability);
						}
					}
					
					// Looking for a path between Sample 158 and consumer double[][] 162.
					{
						for(int index$timeStep$24_1 = 0; index$timeStep$24_1 < length$metric[sample]; index$timeStep$24_1 += 1) {
							if((timeStep$var145 == index$timeStep$24_1)) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$metric_g) {
									// Set the guard so the update is only applied once.
									cv$guard$metric_g = true;
									
									// Update the variable probability
									logProbability$metric_g = (logProbability$metric_g + cv$sampleProbability);
								}
							}
						}
					}
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var149 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample158 = (((fixedFlag$sample158 && fixedFlag$sample95) && fixedFlag$sample117) && fixedFlag$sample136);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					double cv$sampleValue = logProbability$sample158[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Guard to ensure that metric_valid_g is only updated once for this probability.
					boolean cv$guard$metric_valid_g = false;
					
					// Guard to ensure that metric_g is only updated once for this probability.
					boolean cv$guard$metric_g = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					{
						// If the probability of the variable has not already been updated
						if(!cv$guard$metric_valid_g) {
							// Set the guard so the update is only applied once.
							cv$guard$metric_valid_g = true;
							
							// Update the variable probability
							logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
						}
					}
					
					// Looking for a path between Sample 158 and consumer double[][] 162.
					{
						for(int index$timeStep$26_1 = 0; index$timeStep$26_1 < length$metric[sample]; index$timeStep$26_1 += 1) {
							if((timeStep$var145 == index$timeStep$26_1)) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$metric_g) {
									// Set the guard so the update is only applied once.
									cv$guard$metric_g = true;
									
									// Update the variable probability
									logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var149 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample170 using probability
	// distributions.
	private final void logProbabilityDistribution$sample170() {
		// Determine if we need to calculate the values for sample task 170 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample170) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					if(metric_valid_g[sample][timeStep$var145]) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// Look for paths between the variable and the sample task 170 including any distribution
						// values.
						{
							if(metric_valid_g[sample][timeStep$var145]) {
								// The sample value to calculate the probability of generating
								double cv$sampleValue = metric_g[sample][timeStep$var145];
								
								// Enumerating the possible arguments for Gaussian 159.
								if(fixedFlag$sample117) {
									for(int index$sample$2_1 = 0; index$sample$2_1 < noSamples; index$sample$2_1 += 1) {
										if((index$sample$2_1 == sample)) {
											if((0 == timeStep$var145)) {
												if(metric_valid_g[sample][timeStep$var145]) {
													for(int var58 = 0; var58 < noStates; var58 += 1) {
														if(metric_valid_g[sample][timeStep$var145]) {
															if((var58 == st[sample][timeStep$var145])) {
																for(int index$sample$10_1 = 0; index$sample$10_1 < noSamples; index$sample$10_1 += 1) {
																	if((index$sample$10_1 == sample)) {
																		if((0 == timeStep$var145)) {
																			if(metric_valid_g[sample][timeStep$var145]) {
																				for(int var74 = 0; var74 < noStates; var74 += 1) {
																					if(metric_valid_g[sample][timeStep$var145]) {
																						if((var74 == st[sample][timeStep$var145])) {
																							{
																								double var157 = metric_mean[st[sample][timeStep$var145]];
																								double var158 = metric_var[st[sample][timeStep$var145]];
																								
																								// Store the value of the function call, so the function call is only made once.
																								double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																								
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
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								} else {
									for(int index$sample$3 = 0; index$sample$3 < noSamples; index$sample$3 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 110.
											for(int index$sample117$4 = 0; index$sample117$4 < noStates; index$sample117$4 += 1) {
												int distributionTempVariable$var111$6 = index$sample117$4;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample117Value5 = (1.0 * distribution$sample117[((index$sample$3 - 0) / 1)][index$sample117$4]);
												int traceTempVariable$currentState$7_1 = distributionTempVariable$var111$6;
												if((index$sample$3 == sample)) {
													if((0 == timeStep$var145)) {
														if(metric_valid_g[sample][timeStep$var145]) {
															for(int var58 = 0; var58 < noStates; var58 += 1) {
																if(metric_valid_g[sample][timeStep$var145]) {
																	if((var58 == traceTempVariable$currentState$7_1)) {
																		int traceTempVariable$currentState$11_1 = distributionTempVariable$var111$6;
																		if((index$sample$3 == sample)) {
																			if((0 == timeStep$var145)) {
																				if(metric_valid_g[sample][timeStep$var145]) {
																					for(int var74 = 0; var74 < noStates; var74 += 1) {
																						if(metric_valid_g[sample][timeStep$var145]) {
																							if((var74 == traceTempVariable$currentState$11_1)) {
																								{
																									double var157 = metric_mean[traceTempVariable$currentState$11_1];
																									double var158 = metric_var[traceTempVariable$currentState$11_1];
																									
																									// Store the value of the function call, so the function call is only made once.
																									double cv$weightedProbability = (Math.log(cv$probabilitySample117Value5) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																									
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
																					}
																				}
																			}
																		}
																		for(int index$sample$12 = 0; index$sample$12 < noSamples; index$sample$12 += 1) {
																			if(!(index$sample$12 == index$sample$3)) {
																				// Enumerating the possible outputs of Categorical 110.
																				for(int index$sample117$13 = 0; index$sample117$13 < noStates; index$sample117$13 += 1) {
																					int distributionTempVariable$var111$15 = index$sample117$13;
																					
																					// Update the probability of sampling this value from the distribution value.
																					double cv$probabilitySample117Value14 = (cv$probabilitySample117Value5 * distribution$sample117[((index$sample$12 - 0) / 1)][index$sample117$13]);
																					int traceTempVariable$currentState$16_1 = distributionTempVariable$var111$15;
																					if((index$sample$12 == sample)) {
																						if((0 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$16_1)) {
																											{
																												double var157 = metric_mean[traceTempVariable$currentState$16_1];
																												double var158 = metric_var[traceTempVariable$currentState$16_1];
																												
																												// Store the value of the function call, so the function call is only made once.
																												double cv$weightedProbability = (Math.log(cv$probabilitySample117Value14) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																												
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
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample117Value14);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								
								// Enumerating the possible arguments for Gaussian 159.
								if(fixedFlag$sample117) {
									for(int index$sample$20_1 = 0; index$sample$20_1 < noSamples; index$sample$20_1 += 1) {
										if((index$sample$20_1 == sample)) {
											if((0 == timeStep$var145)) {
												if(metric_valid_g[sample][timeStep$var145]) {
													for(int var58 = 0; var58 < noStates; var58 += 1) {
														if(metric_valid_g[sample][timeStep$var145]) {
															if((var58 == st[sample][timeStep$var145])) {
																if(fixedFlag$sample136) {
																	for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																		for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$28_1]; timeStep$var122 += 1) {
																			if((index$sample$28_1 == sample)) {
																				if((timeStep$var122 == timeStep$var145)) {
																					if(metric_valid_g[sample][timeStep$var145]) {
																						for(int var74 = 0; var74 < noStates; var74 += 1) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								if((var74 == st[sample][timeStep$var145])) {
																									{
																										double var157 = metric_mean[st[sample][timeStep$var145]];
																										double var158 = metric_var[st[sample][timeStep$var145]];
																										
																										// Store the value of the function call, so the function call is only made once.
																										double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																										
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
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																} else {
																	for(int index$sample$29 = 0; index$sample$29 < noSamples; index$sample$29 += 1) {
																		for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$29]; timeStep$var122 += 1) {
																			if(true) {
																				// Enumerating the possible outputs of Categorical 129.
																				for(int index$sample136$31 = 0; index$sample136$31 < noStates; index$sample136$31 += 1) {
																					int distributionTempVariable$var130$33 = index$sample136$31;
																					
																					// Update the probability of sampling this value from the distribution value.
																					double cv$probabilitySample136Value32 = (1.0 * distribution$sample136[((index$sample$29 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$31]);
																					int traceTempVariable$currentState$34_1 = distributionTempVariable$var130$33;
																					if((index$sample$29 == sample)) {
																						if((timeStep$var122 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$34_1)) {
																											{
																												double var157 = metric_mean[traceTempVariable$currentState$34_1];
																												double var158 = metric_var[traceTempVariable$currentState$34_1];
																												
																												// Store the value of the function call, so the function call is only made once.
																												double cv$weightedProbability = (Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																												
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
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample136Value32);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								} else {
									for(int index$sample$21 = 0; index$sample$21 < noSamples; index$sample$21 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 110.
											for(int index$sample117$22 = 0; index$sample117$22 < noStates; index$sample117$22 += 1) {
												int distributionTempVariable$var111$24 = index$sample117$22;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample117Value23 = (1.0 * distribution$sample117[((index$sample$21 - 0) / 1)][index$sample117$22]);
												int traceTempVariable$currentState$25_1 = distributionTempVariable$var111$24;
												if((index$sample$21 == sample)) {
													if((0 == timeStep$var145)) {
														if(metric_valid_g[sample][timeStep$var145]) {
															for(int var58 = 0; var58 < noStates; var58 += 1) {
																if(metric_valid_g[sample][timeStep$var145]) {
																	if((var58 == traceTempVariable$currentState$25_1)) {
																		if(fixedFlag$sample136) {
																			for(int index$sample$35_1 = 0; index$sample$35_1 < noSamples; index$sample$35_1 += 1) {
																				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$35_1]; timeStep$var122 += 1) {
																					if((index$sample$35_1 == sample)) {
																						if((timeStep$var122 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$25_1)) {
																											{
																												double var157 = metric_mean[traceTempVariable$currentState$25_1];
																												double var158 = metric_var[traceTempVariable$currentState$25_1];
																												
																												// Store the value of the function call, so the function call is only made once.
																												double cv$weightedProbability = (Math.log(cv$probabilitySample117Value23) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																												
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
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample117Value23);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		} else {
																			for(int index$sample$36 = 0; index$sample$36 < noSamples; index$sample$36 += 1) {
																				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$36]; timeStep$var122 += 1) {
																					if(true) {
																						// Enumerating the possible outputs of Categorical 129.
																						for(int index$sample136$38 = 0; index$sample136$38 < noStates; index$sample136$38 += 1) {
																							int distributionTempVariable$var130$40 = index$sample136$38;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample136Value39 = (cv$probabilitySample117Value23 * distribution$sample136[((index$sample$36 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$38]);
																							int traceTempVariable$currentState$41_1 = distributionTempVariable$var130$40;
																							if((index$sample$36 == sample)) {
																								if((timeStep$var122 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$41_1)) {
																													{
																														double var157 = metric_mean[traceTempVariable$currentState$41_1];
																														double var158 = metric_var[traceTempVariable$currentState$41_1];
																														
																														// Store the value of the function call, so the function call is only made once.
																														double cv$weightedProbability = (Math.log(cv$probabilitySample136Value39) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																														
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
																														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample136Value39);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								
								// Enumerating the possible arguments for Gaussian 159.
								if(fixedFlag$sample136) {
									for(int index$sample$46_1 = 0; index$sample$46_1 < noSamples; index$sample$46_1 += 1) {
										for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$46_1]; timeStep$var122 += 1) {
											if((index$sample$46_1 == sample)) {
												if((timeStep$var122 == timeStep$var145)) {
													if(metric_valid_g[sample][timeStep$var145]) {
														for(int var58 = 0; var58 < noStates; var58 += 1) {
															if(metric_valid_g[sample][timeStep$var145]) {
																if((var58 == st[sample][timeStep$var145])) {
																	for(int index$sample$55_1 = 0; index$sample$55_1 < noSamples; index$sample$55_1 += 1) {
																		for(int index$timeStep$55_2 = 1; index$timeStep$55_2 < length$metric[index$sample$55_1]; index$timeStep$55_2 += 1) {
																			if((index$sample$55_1 == sample)) {
																				if((index$timeStep$55_2 == timeStep$var145)) {
																					if(metric_valid_g[sample][timeStep$var145]) {
																						for(int var74 = 0; var74 < noStates; var74 += 1) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								if((var74 == st[sample][timeStep$var145])) {
																									{
																										double var157 = metric_mean[st[sample][timeStep$var145]];
																										double var158 = metric_var[st[sample][timeStep$var145]];
																										
																										// Store the value of the function call, so the function call is only made once.
																										double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																										
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
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								} else {
									for(int index$sample$47 = 0; index$sample$47 < noSamples; index$sample$47 += 1) {
										for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$47]; timeStep$var122 += 1) {
											if(true) {
												// Enumerating the possible outputs of Categorical 129.
												for(int index$sample136$49 = 0; index$sample136$49 < noStates; index$sample136$49 += 1) {
													int distributionTempVariable$var130$51 = index$sample136$49;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample136Value50 = (1.0 * distribution$sample136[((index$sample$47 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$49]);
													int traceTempVariable$currentState$52_1 = distributionTempVariable$var130$51;
													if((index$sample$47 == sample)) {
														if((timeStep$var122 == timeStep$var145)) {
															if(metric_valid_g[sample][timeStep$var145]) {
																for(int var58 = 0; var58 < noStates; var58 += 1) {
																	if(metric_valid_g[sample][timeStep$var145]) {
																		if((var58 == traceTempVariable$currentState$52_1)) {
																			int traceTempVariable$currentState$56_1 = distributionTempVariable$var130$51;
																			if((index$sample$47 == sample)) {
																				if((timeStep$var122 == timeStep$var145)) {
																					if(metric_valid_g[sample][timeStep$var145]) {
																						for(int var74 = 0; var74 < noStates; var74 += 1) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								if((var74 == traceTempVariable$currentState$56_1)) {
																									{
																										double var157 = metric_mean[traceTempVariable$currentState$56_1];
																										double var158 = metric_var[traceTempVariable$currentState$56_1];
																										
																										// Store the value of the function call, so the function call is only made once.
																										double cv$weightedProbability = (Math.log(cv$probabilitySample136Value50) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																										
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
																						}
																					}
																				}
																			}
																			for(int index$sample$57 = 0; index$sample$57 < noSamples; index$sample$57 += 1) {
																				for(int index$timeStep$58 = 1; index$timeStep$58 < length$metric[index$sample$57]; index$timeStep$58 += 1) {
																					if(!((index$sample$57 == index$sample$47) && (index$timeStep$58 == timeStep$var122))) {
																						// Enumerating the possible outputs of Categorical 129.
																						for(int index$sample136$59 = 0; index$sample136$59 < noStates; index$sample136$59 += 1) {
																							int distributionTempVariable$var130$61 = index$sample136$59;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample136Value60 = (cv$probabilitySample136Value50 * distribution$sample136[((index$sample$57 - 0) / 1)][((index$timeStep$58 - 1) / 1)][index$sample136$59]);
																							int traceTempVariable$currentState$62_1 = distributionTempVariable$var130$61;
																							if((index$sample$57 == sample)) {
																								if((index$timeStep$58 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$62_1)) {
																													{
																														double var157 = metric_mean[traceTempVariable$currentState$62_1];
																														double var158 = metric_var[traceTempVariable$currentState$62_1];
																														
																														// Store the value of the function call, so the function call is only made once.
																														double cv$weightedProbability = (Math.log(cv$probabilitySample136Value60) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																														
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
																														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample136Value60);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								
								// Enumerating the possible arguments for Gaussian 159.
								if(fixedFlag$sample136) {
									for(int index$sample$66_1 = 0; index$sample$66_1 < noSamples; index$sample$66_1 += 1) {
										for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$66_1]; timeStep$var122 += 1) {
											if((index$sample$66_1 == sample)) {
												if((timeStep$var122 == timeStep$var145)) {
													if(metric_valid_g[sample][timeStep$var145]) {
														for(int var58 = 0; var58 < noStates; var58 += 1) {
															if(metric_valid_g[sample][timeStep$var145]) {
																if((var58 == st[sample][timeStep$var145])) {
																	if(fixedFlag$sample117) {
																		for(int index$sample$75_1 = 0; index$sample$75_1 < noSamples; index$sample$75_1 += 1) {
																			if((index$sample$75_1 == sample)) {
																				if((0 == timeStep$var145)) {
																					if(metric_valid_g[sample][timeStep$var145]) {
																						for(int var74 = 0; var74 < noStates; var74 += 1) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								if((var74 == st[sample][timeStep$var145])) {
																									{
																										double var157 = metric_mean[st[sample][timeStep$var145]];
																										double var158 = metric_var[st[sample][timeStep$var145]];
																										
																										// Store the value of the function call, so the function call is only made once.
																										double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																										
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
																							}
																						}
																					}
																				}
																			}
																		}
																	} else {
																		for(int index$sample$76 = 0; index$sample$76 < noSamples; index$sample$76 += 1) {
																			if(true) {
																				// Enumerating the possible outputs of Categorical 110.
																				for(int index$sample117$77 = 0; index$sample117$77 < noStates; index$sample117$77 += 1) {
																					int distributionTempVariable$var111$79 = index$sample117$77;
																					
																					// Update the probability of sampling this value from the distribution value.
																					double cv$probabilitySample117Value78 = (1.0 * distribution$sample117[((index$sample$76 - 0) / 1)][index$sample117$77]);
																					int traceTempVariable$currentState$80_1 = distributionTempVariable$var111$79;
																					if((index$sample$76 == sample)) {
																						if((0 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$80_1)) {
																											{
																												double var157 = metric_mean[traceTempVariable$currentState$80_1];
																												double var158 = metric_var[traceTempVariable$currentState$80_1];
																												
																												// Store the value of the function call, so the function call is only made once.
																												double cv$weightedProbability = (Math.log(cv$probabilitySample117Value78) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																												
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
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample117Value78);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								} else {
									for(int index$sample$67 = 0; index$sample$67 < noSamples; index$sample$67 += 1) {
										for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$67]; timeStep$var122 += 1) {
											if(true) {
												// Enumerating the possible outputs of Categorical 129.
												for(int index$sample136$69 = 0; index$sample136$69 < noStates; index$sample136$69 += 1) {
													int distributionTempVariable$var130$71 = index$sample136$69;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample136Value70 = (1.0 * distribution$sample136[((index$sample$67 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$69]);
													int traceTempVariable$currentState$72_1 = distributionTempVariable$var130$71;
													if((index$sample$67 == sample)) {
														if((timeStep$var122 == timeStep$var145)) {
															if(metric_valid_g[sample][timeStep$var145]) {
																for(int var58 = 0; var58 < noStates; var58 += 1) {
																	if(metric_valid_g[sample][timeStep$var145]) {
																		if((var58 == traceTempVariable$currentState$72_1)) {
																			if(fixedFlag$sample117) {
																				for(int index$sample$81_1 = 0; index$sample$81_1 < noSamples; index$sample$81_1 += 1) {
																					if((index$sample$81_1 == sample)) {
																						if((0 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$72_1)) {
																											{
																												double var157 = metric_mean[traceTempVariable$currentState$72_1];
																												double var158 = metric_var[traceTempVariable$currentState$72_1];
																												
																												// Store the value of the function call, so the function call is only made once.
																												double cv$weightedProbability = (Math.log(cv$probabilitySample136Value70) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																												
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
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample136Value70);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			} else {
																				for(int index$sample$82 = 0; index$sample$82 < noSamples; index$sample$82 += 1) {
																					if(true) {
																						// Enumerating the possible outputs of Categorical 110.
																						for(int index$sample117$83 = 0; index$sample117$83 < noStates; index$sample117$83 += 1) {
																							int distributionTempVariable$var111$85 = index$sample117$83;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample117Value84 = (cv$probabilitySample136Value70 * distribution$sample117[((index$sample$82 - 0) / 1)][index$sample117$83]);
																							int traceTempVariable$currentState$86_1 = distributionTempVariable$var111$85;
																							if((index$sample$82 == sample)) {
																								if((0 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$86_1)) {
																													{
																														double var157 = metric_mean[traceTempVariable$currentState$86_1];
																														double var158 = metric_var[traceTempVariable$currentState$86_1];
																														
																														// Store the value of the function call, so the function call is only made once.
																														double cv$weightedProbability = (Math.log(cv$probabilitySample117Value84) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																														
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
																														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample117Value84);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
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
						double cv$sampleProbability = cv$distributionAccumulator;
						
						// Add the probability of this sample task to the sample task accumulator.
						cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
						
						// Store the sample task probability
						logProbability$sample170[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = cv$sampleProbability;
						
						// Guard to ensure that metric_g is only updated once for this probability.
						boolean cv$guard$metric_g = false;
						
						// Add probability to constructed variables that have guards, so need per sample probabilities
						// from the combined probability
						{
							// If the probability of the variable has not already been updated
							if(!cv$guard$metric_g) {
								// Set the guard so the update is only applied once.
								cv$guard$metric_g = true;
								
								// Update the variable probability
								logProbability$metric_g = (logProbability$metric_g + cv$sampleProbability);
							}
						}
					}
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var159 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$metric_1d = (logProbability$metric_1d + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample170 = ((((fixedFlag$sample170 && fixedFlag$sample63) && fixedFlag$sample79) && fixedFlag$sample117) && fixedFlag$sample136);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					if(metric_valid_g[sample][timeStep$var145]) {
						double cv$sampleValue = logProbability$sample170[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						
						// Guard to ensure that metric_g is only updated once for this probability.
						boolean cv$guard$metric_g = false;
						
						// Add probability to constructed variables that have guards, so need per sample probabilities
						// from the combined probability
						{
							// If the probability of the variable has not already been updated
							if(!cv$guard$metric_g) {
								// Set the guard so the update is only applied once.
								cv$guard$metric_g = true;
								
								// Update the variable probability
								logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var159 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$metric_1d = (logProbability$metric_1d + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample117 using sampled
	// values.
	private final void logProbabilityValue$sample117() {
		// Determine if we need to calculate the values for sample task 117 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample117) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Copy of index so that its values can be safely substituted
				int index$sample$1 = sample;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[sample][0];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var110 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var111 = cv$accumulator;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample117)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample117 = (fixedFlag$sample117 && fixedFlag$sample30);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var111;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var110 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample117)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample136 using sampled
	// values.
	private final void logProbabilityValue$sample136() {
		// Determine if we need to calculate the values for sample task 136 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample136) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Copy of index so that its values can be safely substituted
					int index$timeStep$1 = timeStep$var122;
					
					// Copy of index so that its values can be safely substituted
					int index$sample$2 = sample;
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[sample][timeStep$var122];
						{
							{
								double[] var128 = m[st[sample][(timeStep$var122 - 1)]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var128.length))?Math.log(var128[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
					}
					if((cv$probabilityReached == 0.0))
						// Return negative infinity if no distribution probability space is reached.
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						// Scale the probability relative to the observed distribution space.
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var129 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var130 = cv$accumulator;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample136)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample136 = ((fixedFlag$sample136 && fixedFlag$sample43) && fixedFlag$sample117);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var130;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var129 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample136)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample158 using sampled
	// values.
	private final void logProbabilityValue$sample158() {
		// Determine if we need to calculate the values for sample task 158 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample158) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = metric_valid_g[sample][timeStep$var145];
						{
							{
								double var148 = metric_valid_bias[st[sample][timeStep$var145]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var148));
								
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
					}
					if((cv$probabilityReached == 0.0))
						// Return negative infinity if no distribution probability space is reached.
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						// Scale the probability relative to the observed distribution space.
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					
					// Add the probability of this sample task to the sample task accumulator.
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					
					// Store the sample task probability
					logProbability$sample158[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = cv$sampleProbability;
					
					// Guard to ensure that metric_valid_g is only updated once for this probability.
					boolean cv$guard$metric_valid_g = false;
					
					// Guard to ensure that metric_g is only updated once for this probability.
					boolean cv$guard$metric_g = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					{
						// If the probability of the variable has not already been updated
						if(!cv$guard$metric_valid_g) {
							// Set the guard so the update is only applied once.
							cv$guard$metric_valid_g = true;
							
							// Update the variable probability
							logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleProbability);
						}
					}
					
					// Looking for a path between Sample 158 and consumer double[][] 162.
					{
						for(int index$timeStep$4_1 = 0; index$timeStep$4_1 < length$metric[sample]; index$timeStep$4_1 += 1) {
							if((timeStep$var145 == index$timeStep$4_1)) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$metric_g) {
									// Set the guard so the update is only applied once.
									cv$guard$metric_g = true;
									
									// Update the variable probability
									logProbability$metric_g = (logProbability$metric_g + cv$sampleProbability);
								}
							}
						}
					}
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var149 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample158 = (((fixedFlag$sample158 && fixedFlag$sample95) && fixedFlag$sample117) && fixedFlag$sample136);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					double cv$sampleValue = logProbability$sample158[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					
					// Guard to ensure that metric_valid_g is only updated once for this probability.
					boolean cv$guard$metric_valid_g = false;
					
					// Guard to ensure that metric_g is only updated once for this probability.
					boolean cv$guard$metric_g = false;
					
					// Add probability to constructed variables that have guards, so need per sample probabilities
					// from the combined probability
					{
						// If the probability of the variable has not already been updated
						if(!cv$guard$metric_valid_g) {
							// Set the guard so the update is only applied once.
							cv$guard$metric_valid_g = true;
							
							// Update the variable probability
							logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
						}
					}
					
					// Looking for a path between Sample 158 and consumer double[][] 162.
					{
						for(int index$timeStep$6_1 = 0; index$timeStep$6_1 < length$metric[sample]; index$timeStep$6_1 += 1) {
							if((timeStep$var145 == index$timeStep$6_1)) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$metric_g) {
									// Set the guard so the update is only applied once.
									cv$guard$metric_g = true;
									
									// Update the variable probability
									logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var149 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample170 using sampled
	// values.
	private final void logProbabilityValue$sample170() {
		// Determine if we need to calculate the values for sample task 170 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample170) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					if(metric_valid_g[sample][timeStep$var145]) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						{
							if(metric_valid_g[sample][timeStep$var145]) {
								// The sample value to calculate the probability of generating
								double cv$sampleValue = metric_g[sample][timeStep$var145];
								{
									{
										double var157 = metric_mean[st[sample][timeStep$var145]];
										double var158 = metric_var[st[sample][timeStep$var145]];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
										
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
							}
						}
						if((cv$probabilityReached == 0.0))
							// Return negative infinity if no distribution probability space is reached.
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							// Scale the probability relative to the observed distribution space.
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						double cv$sampleProbability = cv$distributionAccumulator;
						
						// Add the probability of this sample task to the sample task accumulator.
						cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
						
						// Store the sample task probability
						logProbability$sample170[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = cv$sampleProbability;
						
						// Guard to ensure that metric_g is only updated once for this probability.
						boolean cv$guard$metric_g = false;
						
						// Add probability to constructed variables that have guards, so need per sample probabilities
						// from the combined probability
						{
							// If the probability of the variable has not already been updated
							if(!cv$guard$metric_g) {
								// Set the guard so the update is only applied once.
								cv$guard$metric_g = true;
								
								// Update the variable probability
								logProbability$metric_g = (logProbability$metric_g + cv$sampleProbability);
							}
						}
					}
				}
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var159 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$metric_1d = (logProbability$metric_1d + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample170 = ((((fixedFlag$sample170 && fixedFlag$sample63) && fixedFlag$sample79) && fixedFlag$sample117) && fixedFlag$sample136);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					if(metric_valid_g[sample][timeStep$var145]) {
						double cv$sampleValue = logProbability$sample170[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						
						// Guard to ensure that metric_g is only updated once for this probability.
						boolean cv$guard$metric_g = false;
						
						// Add probability to constructed variables that have guards, so need per sample probabilities
						// from the combined probability
						{
							// If the probability of the variable has not already been updated
							if(!cv$guard$metric_g) {
								// Set the guard so the update is only applied once.
								cv$guard$metric_g = true;
								
								// Update the variable probability
								logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var159 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$metric_1d = (logProbability$metric_1d + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample30 using sampled
	// values.
	private final void logProbabilityValue$sample30() {
		// Determine if we need to calculate the values for sample task 30 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample30) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				// The sample value to calculate the probability of generating
				double[] cv$sampleValue = initialStateDistribution;
				{
					{
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v));
						
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
			}
			if((cv$probabilityReached == 0.0))
				// Return negative infinity if no distribution probability space is reached.
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				// Scale the probability relative to the observed distribution space.
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			
			// Add the probability of this sample task to the sample task accumulator.
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var26 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$initialStateDistribution = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample30 = fixedFlag$sample30;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$initialStateDistribution;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var26 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample43 using sampled
	// values.
	private final void logProbabilityValue$sample43() {
		// Determine if we need to calculate the values for sample task 43 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample43) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var39 = 0; var39 < noStates; var39 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double[] cv$sampleValue = m[var39];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v));
							
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
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var28 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var40 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample43)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample43 = fixedFlag$sample43;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var40;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var28 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$m = (logProbability$m + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample43)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample63 using sampled
	// values.
	private final void logProbabilityValue$sample63() {
		// Determine if we need to calculate the values for sample task 63 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample63) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var58 = 0; var58 < noStates; var58 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = metric_mean[var58];
					{
						{
							double var45 = 0.0;
							double var46 = 100.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((var45 <= cv$sampleValue) && (cv$sampleValue <= var46))?(-Math.log((var46 - var45))):Double.NEGATIVE_INFINITY));
							
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
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var47 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var59 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$metric_mean = (logProbability$metric_mean + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample63)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample63 = fixedFlag$sample63;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var59;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var47 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$metric_mean = (logProbability$metric_mean + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample63)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample79 using sampled
	// values.
	private final void logProbabilityValue$sample79() {
		// Determine if we need to calculate the values for sample task 79 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample79) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var74 = 0; var74 < noStates; var74 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = metric_var[var74];
					{
						{
							double var61 = 1.0;
							double var62 = 1.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var61, var62));
							
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
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var63 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var75 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$metric_var = (logProbability$metric_var + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample79)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample79 = fixedFlag$sample79;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var75;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var63 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$metric_var = (logProbability$metric_var + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample79)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample95 using sampled
	// values.
	private final void logProbabilityValue$sample95() {
		// Determine if we need to calculate the values for sample task 95 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample95) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var90 = 0; var90 < noStates; var90 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = metric_valid_bias[var90];
					{
						{
							double var77 = 1.0;
							double var78 = 1.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var77, var78));
							
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
				}
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			
			// Add the probability of this instance of the random variable to the probability
			// of all instances of the random variable.
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var79 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var91 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample95 = fixedFlag$sample95;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var91;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var79 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 117 drawn from Categorical 110. Inference was performed using variable
	// marginalization.
	private final void sample117(int sample) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		
		// Copy of index so that its values can be safely substituted
		int index$sample$1 = sample;
		{
			// variable marginalization
			cv$noStates = Math.max(cv$noStates, noStates);
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var111$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Copy of index so that its values can be safely substituted
			int index$sample$2 = sample;
			
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// The value currently being tested
			int cv$currentValue;
			
			// Value of the variable at this index
			cv$currentValue = cv$valuePos;
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$initialStateDistribution;
				{
					cv$temp$0$initialStateDistribution = initialStateDistribution;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$initialStateDistribution.length))?Math.log(cv$temp$0$initialStateDistribution[cv$currentValue]):Double.NEGATIVE_INFINITY));
				
				// Processing random variable 129.
				{
					// Looking for a path between Sample 117 and consumer Categorical 129.
					{
						int traceTempVariable$var127$3_1 = cv$currentValue;
						for(int index$sample$3_2 = 0; index$sample$3_2 < noSamples; index$sample$3_2 += 1) {
							if((sample == index$sample$3_2)) {
								for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$3_2]; timeStep$var122 += 1) {
									if((0 == (timeStep$var122 - 1))) {
										if(fixedFlag$sample136) {
											// Processing sample task 136 of consumer random variable null.
											{
												// Copy of index so that its values can be safely substituted
												int index$timeStep$5 = timeStep$var122;
												
												// Copy of index so that its values can be safely substituted
												int index$sample$6 = index$sample$3_2;
												
												// Set an accumulator to sum the probabilities for each possible configuration of
												// inputs.
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												
												// Set an accumulator to record the consumer distributions not seen. Initially set
												// to 1 as seen values will be deducted from this value.
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													// Enumerating the possible arguments for the variable Categorical 129 which is consuming
													// the output of Sample task 117.
													for(int var39 = 0; var39 < noStates; var39 += 1) {
														if((var39 == traceTempVariable$var127$3_1)) {
															{
																{
																	double[] cv$temp$1$var128;
																	{
																		// Constructing a random variable input for use later.
																		double[] var128 = m[traceTempVariable$var127$3_1];
																		cv$temp$1$var128 = var128;
																	}
																	
																	// Record the probability of sample task 136 generating output with current configuration.
																	if(((Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var122]) && (st[index$sample$3_2][timeStep$var122] < cv$temp$1$var128.length))?Math.log(cv$temp$1$var128[st[index$sample$3_2][timeStep$var122]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var122]) && (st[index$sample$3_2][timeStep$var122] < cv$temp$1$var128.length))?Math.log(cv$temp$1$var128[st[index$sample$3_2][timeStep$var122]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var122]) && (st[index$sample$3_2][timeStep$var122] < cv$temp$1$var128.length))?Math.log(cv$temp$1$var128[st[index$sample$3_2][timeStep$var122]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var122]) && (st[index$sample$3_2][timeStep$var122] < cv$temp$1$var128.length))?Math.log(cv$temp$1$var128[st[index$sample$3_2][timeStep$var122]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var122]) && (st[index$sample$3_2][timeStep$var122] < cv$temp$1$var128.length))?Math.log(cv$temp$1$var128[st[index$sample$3_2][timeStep$var122]]):Double.NEGATIVE_INFINITY)));
																	}
																	
																	// Recorded the probability of reaching sample task 136 with the current configuration.
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
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
									}
								}
							}
						}
					}
				}
				
				// Processing random variable 149.
				{
					// Looking for a path between Sample 117 and consumer Bernoulli 149.
					{
						int traceTempVariable$currentState$9_1 = cv$currentValue;
						for(int index$sample$9_2 = 0; index$sample$9_2 < noSamples; index$sample$9_2 += 1) {
							if((sample == index$sample$9_2)) {
								for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$9_2]; timeStep$var145 += 1) {
									if((0 == timeStep$var145)) {
										// Processing sample task 158 of consumer random variable null.
										{
											// Set an accumulator to sum the probabilities for each possible configuration of
											// inputs.
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											
											// Set an accumulator to record the consumer distributions not seen. Initially set
											// to 1 as seen values will be deducted from this value.
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												// Enumerating the possible arguments for the variable Bernoulli 149 which is consuming
												// the output of Sample task 117.
												for(int var90 = 0; var90 < noStates; var90 += 1) {
													if((var90 == traceTempVariable$currentState$9_1)) {
														{
															{
																double cv$temp$2$var148;
																{
																	// Constructing a random variable input for use later.
																	double var148 = metric_valid_bias[traceTempVariable$currentState$9_1];
																	cv$temp$2$var148 = var148;
																}
																
																// Record the probability of sample task 158 generating output with current configuration.
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$9_2][timeStep$var145], cv$temp$2$var148)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$9_2][timeStep$var145], cv$temp$2$var148)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$9_2][timeStep$var145], cv$temp$2$var148));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$9_2][timeStep$var145], cv$temp$2$var148)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$9_2][timeStep$var145], cv$temp$2$var148)));
																}
																
																// Recorded the probability of reaching sample task 158 with the current configuration.
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
															}
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
								}
							}
						}
					}
				}
				
				// Processing random variable 159.
				{
					// Looking for a path between Sample 117 and consumer Gaussian 159.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[][] guard$sample117gaussian169 = guard$sample117gaussian169$global;
						for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
							if((sample == index$sample$13_1)) {
								for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$13_1]; timeStep$var145 += 1) {
									if((0 == timeStep$var145)) {
										if(metric_valid_g[index$sample$13_1][timeStep$var145])
											// Set the flags to false
											guard$sample117gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int index$sample$14_1 = 0; index$sample$14_1 < noSamples; index$sample$14_1 += 1) {
							if((sample == index$sample$14_1)) {
								for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$14_1]; timeStep$var145 += 1) {
									if((0 == timeStep$var145)) {
										if(metric_valid_g[index$sample$14_1][timeStep$var145])
											// Set the flags to false
											guard$sample117gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = false;
									}
								}
							}
						}
						int traceTempVariable$currentState$15_1 = cv$currentValue;
						for(int index$sample$15_2 = 0; index$sample$15_2 < noSamples; index$sample$15_2 += 1) {
							if((sample == index$sample$15_2)) {
								for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$15_2]; timeStep$var145 += 1) {
									if((0 == timeStep$var145)) {
										if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
											if(!guard$sample117gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample117gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = true;
												
												// Processing sample task 170 of consumer random variable null.
												{
													if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
														// Set an accumulator to sum the probabilities for each possible configuration of
														// inputs.
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														
														// Set an accumulator to record the consumer distributions not seen. Initially set
														// to 1 as seen values will be deducted from this value.
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
															// the output of Sample task 117.
															for(int var58 = 0; var58 < noStates; var58 += 1) {
																if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																	if((var58 == traceTempVariable$currentState$15_1)) {
																		int traceTempVariable$currentState$20_1 = cv$currentValue;
																		if((index$sample$2 == index$sample$15_2)) {
																			if((0 == timeStep$var145)) {
																				if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																					for(int var74 = 0; var74 < noStates; var74 += 1) {
																						if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																							if((var74 == traceTempVariable$currentState$20_1)) {
																								{
																									{
																										if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																											double cv$temp$3$var157;
																											{
																												// Constructing a random variable input for use later.
																												double var157 = metric_mean[traceTempVariable$currentState$20_1];
																												cv$temp$3$var157 = var157;
																											}
																											double cv$temp$4$var158;
																											{
																												// Constructing a random variable input for use later.
																												double var158 = metric_var[traceTempVariable$currentState$20_1];
																												cv$temp$4$var158 = var158;
																											}
																											
																											// Record the probability of sample task 170 generating output with current configuration.
																											if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$3$var157) / Math.sqrt(cv$temp$4$var158))) - (0.5 * Math.log(cv$temp$4$var158)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$3$var157) / Math.sqrt(cv$temp$4$var158))) - (0.5 * Math.log(cv$temp$4$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												// If the second value is -infinity.
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$3$var157) / Math.sqrt(cv$temp$4$var158))) - (0.5 * Math.log(cv$temp$4$var158))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$3$var157) / Math.sqrt(cv$temp$4$var158))) - (0.5 * Math.log(cv$temp$4$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$3$var157) / Math.sqrt(cv$temp$4$var158))) - (0.5 * Math.log(cv$temp$4$var158)))));
																											}
																											
																											// Recorded the probability of reaching sample task 170 with the current configuration.
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		for(int index$sample$21 = 0; index$sample$21 < noSamples; index$sample$21 += 1) {
																			if(!(index$sample$21 == index$sample$2)) {
																				// Enumerating the possible outputs of Categorical 110.
																				for(int index$sample117$22 = 0; index$sample117$22 < noStates; index$sample117$22 += 1) {
																					int distributionTempVariable$var111$24 = index$sample117$22;
																					
																					// Update the probability of sampling this value from the distribution value.
																					double cv$probabilitySample117Value23 = (1.0 * distribution$sample117[((index$sample$21 - 0) / 1)][index$sample117$22]);
																					int traceTempVariable$currentState$25_1 = distributionTempVariable$var111$24;
																					if((index$sample$21 == index$sample$15_2)) {
																						if((0 == timeStep$var145)) {
																							if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$25_1)) {
																											{
																												{
																													if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																														double cv$temp$5$var157;
																														{
																															// Constructing a random variable input for use later.
																															double var157 = metric_mean[traceTempVariable$currentState$25_1];
																															cv$temp$5$var157 = var157;
																														}
																														double cv$temp$6$var158;
																														{
																															// Constructing a random variable input for use later.
																															double var158 = metric_var[traceTempVariable$currentState$25_1];
																															cv$temp$6$var158 = var158;
																														}
																														
																														// Record the probability of sample task 170 generating output with current configuration.
																														if(((Math.log(cv$probabilitySample117Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$5$var157) / Math.sqrt(cv$temp$6$var158))) - (0.5 * Math.log(cv$temp$6$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$5$var157) / Math.sqrt(cv$temp$6$var158))) - (0.5 * Math.log(cv$temp$6$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$5$var157) / Math.sqrt(cv$temp$6$var158))) - (0.5 * Math.log(cv$temp$6$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$5$var157) / Math.sqrt(cv$temp$6$var158))) - (0.5 * Math.log(cv$temp$6$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$5$var157) / Math.sqrt(cv$temp$6$var158))) - (0.5 * Math.log(cv$temp$6$var158)))));
																														}
																														
																														// Recorded the probability of reaching sample task 170 with the current configuration.
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value23);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															
															// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
															// the output of Sample task 117.
															for(int var58 = 0; var58 < noStates; var58 += 1) {
																if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																	if((var58 == traceTempVariable$currentState$15_1)) {
																		if(fixedFlag$sample136) {
																			for(int index$sample$29_1 = 0; index$sample$29_1 < noSamples; index$sample$29_1 += 1) {
																				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$29_1]; timeStep$var122 += 1) {
																					if((index$sample$29_1 == index$sample$15_2)) {
																						if((timeStep$var122 == timeStep$var145)) {
																							if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$15_1)) {
																											{
																												{
																													if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																														double cv$temp$7$var157;
																														{
																															// Constructing a random variable input for use later.
																															double var157 = metric_mean[traceTempVariable$currentState$15_1];
																															cv$temp$7$var157 = var157;
																														}
																														double cv$temp$8$var158;
																														{
																															// Constructing a random variable input for use later.
																															double var158 = metric_var[traceTempVariable$currentState$15_1];
																															cv$temp$8$var158 = var158;
																														}
																														
																														// Record the probability of sample task 170 generating output with current configuration.
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$7$var157) / Math.sqrt(cv$temp$8$var158))) - (0.5 * Math.log(cv$temp$8$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$7$var157) / Math.sqrt(cv$temp$8$var158))) - (0.5 * Math.log(cv$temp$8$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$7$var157) / Math.sqrt(cv$temp$8$var158))) - (0.5 * Math.log(cv$temp$8$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$7$var157) / Math.sqrt(cv$temp$8$var158))) - (0.5 * Math.log(cv$temp$8$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$7$var157) / Math.sqrt(cv$temp$8$var158))) - (0.5 * Math.log(cv$temp$8$var158)))));
																														}
																														
																														// Recorded the probability of reaching sample task 170 with the current configuration.
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		} else {
																			for(int index$sample$30 = 0; index$sample$30 < noSamples; index$sample$30 += 1) {
																				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$30]; timeStep$var122 += 1) {
																					if(true) {
																						// Enumerating the possible outputs of Categorical 129.
																						for(int index$sample136$32 = 0; index$sample136$32 < noStates; index$sample136$32 += 1) {
																							int distributionTempVariable$var130$34 = index$sample136$32;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample136Value33 = (1.0 * distribution$sample136[((index$sample$30 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$32]);
																							int traceTempVariable$currentState$35_1 = distributionTempVariable$var130$34;
																							if((index$sample$30 == index$sample$15_2)) {
																								if((timeStep$var122 == timeStep$var145)) {
																									if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$35_1)) {
																													{
																														{
																															if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																																double cv$temp$9$var157;
																																{
																																	// Constructing a random variable input for use later.
																																	double var157 = metric_mean[traceTempVariable$currentState$35_1];
																																	cv$temp$9$var157 = var157;
																																}
																																double cv$temp$10$var158;
																																{
																																	// Constructing a random variable input for use later.
																																	double var158 = metric_var[traceTempVariable$currentState$35_1];
																																	cv$temp$10$var158 = var158;
																																}
																																
																																// Record the probability of sample task 170 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample136Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$9$var157) / Math.sqrt(cv$temp$10$var158))) - (0.5 * Math.log(cv$temp$10$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$9$var157) / Math.sqrt(cv$temp$10$var158))) - (0.5 * Math.log(cv$temp$10$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$9$var157) / Math.sqrt(cv$temp$10$var158))) - (0.5 * Math.log(cv$temp$10$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$9$var157) / Math.sqrt(cv$temp$10$var158))) - (0.5 * Math.log(cv$temp$10$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$9$var157) / Math.sqrt(cv$temp$10$var158))) - (0.5 * Math.log(cv$temp$10$var158)))));
																																}
																																
																																// Recorded the probability of reaching sample task 170 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value33);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
											}
										}
									}
								}
							}
						}
						int traceTempVariable$currentState$16_1 = cv$currentValue;
						for(int index$sample$16_2 = 0; index$sample$16_2 < noSamples; index$sample$16_2 += 1) {
							if((sample == index$sample$16_2)) {
								for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$16_2]; timeStep$var145 += 1) {
									if((0 == timeStep$var145)) {
										if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
											if(!guard$sample117gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample117gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = true;
												
												// Processing sample task 170 of consumer random variable null.
												{
													if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
														// Set an accumulator to sum the probabilities for each possible configuration of
														// inputs.
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														
														// Set an accumulator to record the consumer distributions not seen. Initially set
														// to 1 as seen values will be deducted from this value.
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
															// the output of Sample task 117.
															int traceTempVariable$currentState$38_1 = cv$currentValue;
															if((index$sample$2 == index$sample$16_2)) {
																if((0 == timeStep$var145)) {
																	if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																		for(int var58 = 0; var58 < noStates; var58 += 1) {
																			if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																				if((var58 == traceTempVariable$currentState$38_1)) {
																					for(int var74 = 0; var74 < noStates; var74 += 1) {
																						if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																							if((var74 == traceTempVariable$currentState$38_1)) {
																								{
																									{
																										if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																											double cv$temp$11$var157;
																											{
																												// Constructing a random variable input for use later.
																												double var157 = metric_mean[traceTempVariable$currentState$38_1];
																												cv$temp$11$var157 = var157;
																											}
																											double cv$temp$12$var158;
																											{
																												// Constructing a random variable input for use later.
																												double var158 = metric_var[traceTempVariable$currentState$38_1];
																												cv$temp$12$var158 = var158;
																											}
																											
																											// Record the probability of sample task 170 generating output with current configuration.
																											if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$11$var157) / Math.sqrt(cv$temp$12$var158))) - (0.5 * Math.log(cv$temp$12$var158)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$11$var157) / Math.sqrt(cv$temp$12$var158))) - (0.5 * Math.log(cv$temp$12$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												// If the second value is -infinity.
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$11$var157) / Math.sqrt(cv$temp$12$var158))) - (0.5 * Math.log(cv$temp$12$var158))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$11$var157) / Math.sqrt(cv$temp$12$var158))) - (0.5 * Math.log(cv$temp$12$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$11$var157) / Math.sqrt(cv$temp$12$var158))) - (0.5 * Math.log(cv$temp$12$var158)))));
																											}
																											
																											// Recorded the probability of reaching sample task 170 with the current configuration.
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															for(int index$sample$39 = 0; index$sample$39 < noSamples; index$sample$39 += 1) {
																if(!(index$sample$39 == index$sample$2)) {
																	// Enumerating the possible outputs of Categorical 110.
																	for(int index$sample117$40 = 0; index$sample117$40 < noStates; index$sample117$40 += 1) {
																		int distributionTempVariable$var111$42 = index$sample117$40;
																		
																		// Update the probability of sampling this value from the distribution value.
																		double cv$probabilitySample117Value41 = (1.0 * distribution$sample117[((index$sample$39 - 0) / 1)][index$sample117$40]);
																		int traceTempVariable$currentState$43_1 = distributionTempVariable$var111$42;
																		if((index$sample$39 == index$sample$16_2)) {
																			if((0 == timeStep$var145)) {
																				if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																					for(int var58 = 0; var58 < noStates; var58 += 1) {
																						if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																							if((var58 == traceTempVariable$currentState$43_1)) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$43_1)) {
																											{
																												{
																													if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																														double cv$temp$13$var157;
																														{
																															// Constructing a random variable input for use later.
																															double var157 = metric_mean[traceTempVariable$currentState$43_1];
																															cv$temp$13$var157 = var157;
																														}
																														double cv$temp$14$var158;
																														{
																															// Constructing a random variable input for use later.
																															double var158 = metric_var[traceTempVariable$currentState$43_1];
																															cv$temp$14$var158 = var158;
																														}
																														
																														// Record the probability of sample task 170 generating output with current configuration.
																														if(((Math.log(cv$probabilitySample117Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$13$var157) / Math.sqrt(cv$temp$14$var158))) - (0.5 * Math.log(cv$temp$14$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$13$var157) / Math.sqrt(cv$temp$14$var158))) - (0.5 * Math.log(cv$temp$14$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$13$var157) / Math.sqrt(cv$temp$14$var158))) - (0.5 * Math.log(cv$temp$14$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$13$var157) / Math.sqrt(cv$temp$14$var158))) - (0.5 * Math.log(cv$temp$14$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$13$var157) / Math.sqrt(cv$temp$14$var158))) - (0.5 * Math.log(cv$temp$14$var158)))));
																														}
																														
																														// Recorded the probability of reaching sample task 170 with the current configuration.
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value41);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															
															// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
															// the output of Sample task 117.
															if(fixedFlag$sample136) {
																for(int index$sample$48_1 = 0; index$sample$48_1 < noSamples; index$sample$48_1 += 1) {
																	for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$48_1]; timeStep$var122 += 1) {
																		if((index$sample$48_1 == index$sample$16_2)) {
																			if((timeStep$var122 == timeStep$var145)) {
																				if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																					for(int var58 = 0; var58 < noStates; var58 += 1) {
																						if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																							if((var58 == traceTempVariable$currentState$16_1)) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$16_1)) {
																											{
																												{
																													if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																														double cv$temp$15$var157;
																														{
																															// Constructing a random variable input for use later.
																															double var157 = metric_mean[traceTempVariable$currentState$16_1];
																															cv$temp$15$var157 = var157;
																														}
																														double cv$temp$16$var158;
																														{
																															// Constructing a random variable input for use later.
																															double var158 = metric_var[traceTempVariable$currentState$16_1];
																															cv$temp$16$var158 = var158;
																														}
																														
																														// Record the probability of sample task 170 generating output with current configuration.
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$15$var157) / Math.sqrt(cv$temp$16$var158))) - (0.5 * Math.log(cv$temp$16$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$15$var157) / Math.sqrt(cv$temp$16$var158))) - (0.5 * Math.log(cv$temp$16$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$15$var157) / Math.sqrt(cv$temp$16$var158))) - (0.5 * Math.log(cv$temp$16$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$15$var157) / Math.sqrt(cv$temp$16$var158))) - (0.5 * Math.log(cv$temp$16$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$15$var157) / Math.sqrt(cv$temp$16$var158))) - (0.5 * Math.log(cv$temp$16$var158)))));
																														}
																														
																														// Recorded the probability of reaching sample task 170 with the current configuration.
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															} else {
																for(int index$sample$49 = 0; index$sample$49 < noSamples; index$sample$49 += 1) {
																	for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$49]; timeStep$var122 += 1) {
																		if(true) {
																			// Enumerating the possible outputs of Categorical 129.
																			for(int index$sample136$51 = 0; index$sample136$51 < noStates; index$sample136$51 += 1) {
																				int distributionTempVariable$var130$53 = index$sample136$51;
																				
																				// Update the probability of sampling this value from the distribution value.
																				double cv$probabilitySample136Value52 = (1.0 * distribution$sample136[((index$sample$49 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$51]);
																				int traceTempVariable$currentState$54_1 = distributionTempVariable$var130$53;
																				if((index$sample$49 == index$sample$16_2)) {
																					if((timeStep$var122 == timeStep$var145)) {
																						if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																							for(int var58 = 0; var58 < noStates; var58 += 1) {
																								if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																									if((var58 == traceTempVariable$currentState$54_1)) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$54_1)) {
																													{
																														{
																															if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																																double cv$temp$17$var157;
																																{
																																	// Constructing a random variable input for use later.
																																	double var157 = metric_mean[traceTempVariable$currentState$54_1];
																																	cv$temp$17$var157 = var157;
																																}
																																double cv$temp$18$var158;
																																{
																																	// Constructing a random variable input for use later.
																																	double var158 = metric_var[traceTempVariable$currentState$54_1];
																																	cv$temp$18$var158 = var158;
																																}
																																
																																// Record the probability of sample task 170 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample136Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$17$var157) / Math.sqrt(cv$temp$18$var158))) - (0.5 * Math.log(cv$temp$18$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$17$var157) / Math.sqrt(cv$temp$18$var158))) - (0.5 * Math.log(cv$temp$18$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$17$var157) / Math.sqrt(cv$temp$18$var158))) - (0.5 * Math.log(cv$temp$18$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$17$var157) / Math.sqrt(cv$temp$18$var158))) - (0.5 * Math.log(cv$temp$18$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$17$var157) / Math.sqrt(cv$temp$18$var158))) - (0.5 * Math.log(cv$temp$18$var158)))));
																																}
																																
																																// Recorded the probability of reaching sample task 170 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value52);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
											}
										}
									}
								}
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
			
			// Processing random variable 129.
			{
				// Looking for a path between Sample 117 and consumer Categorical 129.
				{
					int traceTempVariable$var127$67_1 = cv$currentValue;
					for(int index$sample$67_2 = 0; index$sample$67_2 < noSamples; index$sample$67_2 += 1) {
						if((sample == index$sample$67_2)) {
							for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$67_2]; timeStep$var122 += 1) {
								if((0 == (timeStep$var122 - 1))) {
									if(!fixedFlag$sample136) {
										// Processing sample task 136 of consumer random variable null.
										{
											// Copy of index so that its values can be safely substituted
											int index$timeStep$69 = timeStep$var122;
											
											// Copy of index so that its values can be safely substituted
											int index$sample$70 = index$sample$67_2;
											
											// A local array to hold the accumulated distributions of the sample tasks for each
											// configuration of distributions.
											double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var129;
											
											// Zero all the elements in the distribution accumulator
											for(int cv$i = 0; cv$i < noStates; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											
											// Zero an accumulator to track the probabilities reached.
											double cv$reachedDistributionProbability = 0.0;
											
											// Enumerating the possible arguments for the variable Categorical 129 which is consuming
											// the output of Sample task 117.
											for(int var39 = 0; var39 < noStates; var39 += 1) {
												if((var39 == traceTempVariable$var127$67_1)) {
													{
														// Declare and zero an accumulator for tracking the reached source probability space.
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															// Add the probability of this argument configuration.
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														double[] cv$temp$19$var128;
														{
															// Constructing a random variable input for use later.
															double[] var128 = m[traceTempVariable$var127$67_1];
															cv$temp$19$var128 = var128;
														}
														
														// The probability of reaching the consumer with this set of consumer arguments
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
														
														// Record the reached distribution.
														cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
														
														// Add the current distribution to the distribution accumulator.
														DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$19$var128);
													}
												}
											}
											
											// A local copy of the samples' distribution.
											double[] cv$sampleDistribution = distribution$sample136[((index$sample$67_2 - 0) / 1)][((timeStep$var122 - 1) / 1)];
											
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
											cv$accumulatedDistributionProbabilities = (cv$accumulatedDistributionProbabilities + Math.log(((cv$overlap * cv$reachedDistributionProbability) + (1.0 - Math.min(cv$reachedDistributionProbability, 1.0)))));
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample117[((sample - 0) / 1)];
		
		// The sum of all the probabilities in log space
		double cv$logSum = 0.0;
		
		// Sum all the values
		{
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
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
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
	private final void sample136(int sample, int timeStep$var122) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		
		// Exploring all the possible state counts for random variable 129.
		// 
		// Copy of index so that its values can be safely substituted
		int index$timeStep$1 = timeStep$var122;
		
		// Copy of index so that its values can be safely substituted
		int index$sample$2 = sample;
		
		// Enumerating the possible arguments for Categorical 129.
		if(fixedFlag$sample117) {
			for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
				if((index$sample$3_1 == sample)) {
					if((0 == (timeStep$var122 - 1))) {
						for(int var39 = 0; var39 < noStates; var39 += 1) {
							if((var39 == st[sample][(timeStep$var122 - 1)]))
								// variable marginalization
								cv$noStates = Math.max(cv$noStates, noStates);
						}
					}
				}
			}
		} else {
			for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
				if(true) {
					// Enumerating the possible outputs of Categorical 110.
					for(int index$sample117$5 = 0; index$sample117$5 < noStates; index$sample117$5 += 1) {
						int distributionTempVariable$var111$7 = index$sample117$5;
						
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample117Value6 = (1.0 * distribution$sample117[((index$sample$4 - 0) / 1)][index$sample117$5]);
						int traceTempVariable$var127$8_1 = distributionTempVariable$var111$7;
						if((index$sample$4 == sample)) {
							if((0 == (timeStep$var122 - 1))) {
								for(int var39 = 0; var39 < noStates; var39 += 1) {
									if((var39 == traceTempVariable$var127$8_1))
										// variable marginalization
										cv$noStates = Math.max(cv$noStates, noStates);
								}
							}
						}
					}
				}
			}
		}
		
		// Enumerating the possible arguments for Categorical 129.
		if((index$sample$2 == sample)) {
			if((index$timeStep$1 == (timeStep$var122 - 1))) {
				for(int var39 = 0; var39 < noStates; var39 += 1) {
					if((var39 == st[sample][(timeStep$var122 - 1)]))
						// variable marginalization
						cv$noStates = Math.max(cv$noStates, noStates);
				}
			}
		}
		if(fixedFlag$sample136) {
			for(int index$sample$12_1 = 0; index$sample$12_1 < noSamples; index$sample$12_1 += 1) {
				for(int index$timeStep$12_2 = 1; index$timeStep$12_2 < length$metric[index$sample$12_1]; index$timeStep$12_2 += 1) {
					if((index$sample$12_1 == sample)) {
						if((index$timeStep$12_2 == (timeStep$var122 - 1))) {
							for(int var39 = 0; var39 < noStates; var39 += 1) {
								if((var39 == st[sample][(timeStep$var122 - 1)]))
									// variable marginalization
									cv$noStates = Math.max(cv$noStates, noStates);
							}
						}
					}
				}
			}
		} else {
			for(int index$sample$13 = 0; index$sample$13 < noSamples; index$sample$13 += 1) {
				for(int index$timeStep$14 = 1; index$timeStep$14 < length$metric[index$sample$13]; index$timeStep$14 += 1) {
					if(!((index$sample$13 == index$sample$2) && (index$timeStep$14 == index$timeStep$1))) {
						// Enumerating the possible outputs of Categorical 129.
						for(int index$sample136$15 = 0; index$sample136$15 < noStates; index$sample136$15 += 1) {
							int distributionTempVariable$var130$17 = index$sample136$15;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample136Value16 = (1.0 * distribution$sample136[((index$sample$13 - 0) / 1)][((index$timeStep$14 - 1) / 1)][index$sample136$15]);
							int traceTempVariable$var127$18_1 = distributionTempVariable$var130$17;
							if((index$sample$13 == sample)) {
								if((index$timeStep$14 == (timeStep$var122 - 1))) {
									for(int var39 = 0; var39 < noStates; var39 += 1) {
										if((var39 == traceTempVariable$var127$18_1))
											// variable marginalization
											cv$noStates = Math.max(cv$noStates, noStates);
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var130$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 129 creating
			// sample task 136.
			// Copy of index so that its values can be safely substituted
			int index$timeStep$22 = timeStep$var122;
			
			// Copy of index so that its values can be safely substituted
			int index$sample$23 = sample;
			
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// The value currently being tested
			int cv$currentValue;
			
			// Value of the variable at this index
			cv$currentValue = cv$valuePos;
			
			// Enumerating the possible arguments for Categorical 129.
			if(fixedFlag$sample117) {
				for(int index$sample$24_1 = 0; index$sample$24_1 < noSamples; index$sample$24_1 += 1) {
					if((index$sample$24_1 == sample)) {
						if((0 == (timeStep$var122 - 1))) {
							for(int var39 = 0; var39 < noStates; var39 += 1) {
								if((var39 == st[sample][(timeStep$var122 - 1)])) {
									// Record the reached probability density.
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
									double[] cv$temp$0$var128;
									{
										// Constructing a random variable input for use later.
										double[] var128 = m[st[sample][(timeStep$var122 - 1)]];
										cv$temp$0$var128 = var128;
									}
									
									// An accumulator to allow the value for each distribution to be constructed before
									// it is added to the index probabilities.
									double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var128.length))?Math.log(cv$temp$0$var128[cv$currentValue]):Double.NEGATIVE_INFINITY));
									
									// Processing random variable 129.
									{
										// Looking for a path between Sample 136 and consumer Categorical 129.
										{
											int traceTempVariable$var127$41_1 = cv$currentValue;
										}
									}
									
									// Processing random variable 149.
									{
										// Looking for a path between Sample 136 and consumer Bernoulli 149.
										{
											int traceTempVariable$currentState$45_1 = cv$currentValue;
											for(int index$sample$45_2 = 0; index$sample$45_2 < noSamples; index$sample$45_2 += 1) {
												if((sample == index$sample$45_2)) {
													for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$45_2]; timeStep$var145 += 1) {
														if((timeStep$var122 == timeStep$var145)) {
															// Processing sample task 158 of consumer random variable null.
															{
																// Set an accumulator to sum the probabilities for each possible configuration of
																// inputs.
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																
																// Set an accumulator to record the consumer distributions not seen. Initially set
																// to 1 as seen values will be deducted from this value.
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	// Enumerating the possible arguments for the variable Bernoulli 149 which is consuming
																	// the output of Sample task 136.
																	for(int var90 = 0; var90 < noStates; var90 += 1) {
																		if((var90 == traceTempVariable$currentState$45_1)) {
																			{
																				{
																					double cv$temp$4$var148;
																					{
																						// Constructing a random variable input for use later.
																						double var148 = metric_valid_bias[traceTempVariable$currentState$45_1];
																						cv$temp$4$var148 = var148;
																					}
																					
																					// Record the probability of sample task 158 generating output with current configuration.
																					if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$45_2][timeStep$var145], cv$temp$4$var148)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$45_2][timeStep$var145], cv$temp$4$var148)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						// If the second value is -infinity.
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$45_2][timeStep$var145], cv$temp$4$var148));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$45_2][timeStep$var145], cv$temp$4$var148)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$45_2][timeStep$var145], cv$temp$4$var148)));
																					}
																					
																					// Recorded the probability of reaching sample task 158 with the current configuration.
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
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
													}
												}
											}
										}
									}
									
									// Processing random variable 159.
									{
										// Looking for a path between Sample 136 and consumer Gaussian 159.
										{
											// Guard to check that at most one copy of the code is executed for a given random
											// variable instance.
											boolean[][] guard$sample136gaussian169 = guard$sample136gaussian169$global;
											for(int index$sample$61_1 = 0; index$sample$61_1 < noSamples; index$sample$61_1 += 1) {
												if((sample == index$sample$61_1)) {
													for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$61_1]; timeStep$var145 += 1) {
														if((timeStep$var122 == timeStep$var145)) {
															if(metric_valid_g[index$sample$61_1][timeStep$var145])
																// Set the flags to false
																guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = false;
														}
													}
												}
											}
											for(int index$sample$65_1 = 0; index$sample$65_1 < noSamples; index$sample$65_1 += 1) {
												if((sample == index$sample$65_1)) {
													for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$65_1]; timeStep$var145 += 1) {
														if((timeStep$var122 == timeStep$var145)) {
															if(metric_valid_g[index$sample$65_1][timeStep$var145])
																// Set the flags to false
																guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = false;
														}
													}
												}
											}
											int traceTempVariable$currentState$69_1 = cv$currentValue;
											for(int index$sample$69_2 = 0; index$sample$69_2 < noSamples; index$sample$69_2 += 1) {
												if((sample == index$sample$69_2)) {
													for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$69_2]; timeStep$var145 += 1) {
														if((timeStep$var122 == timeStep$var145)) {
															if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																if(!guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)]) {
																	// The body will execute, so should not be executed again
																	guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = true;
																	
																	// Processing sample task 170 of consumer random variable null.
																	{
																		if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																			// Set an accumulator to sum the probabilities for each possible configuration of
																			// inputs.
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			
																			// Set an accumulator to record the consumer distributions not seen. Initially set
																			// to 1 as seen values will be deducted from this value.
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																				// the output of Sample task 136.
																				for(int var58 = 0; var58 < noStates; var58 += 1) {
																					if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																						if((var58 == traceTempVariable$currentState$69_1)) {
																							for(int index$sample$86_1 = 0; index$sample$86_1 < noSamples; index$sample$86_1 += 1) {
																								if((index$sample$86_1 == index$sample$69_2)) {
																									if((0 == timeStep$var145)) {
																										if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																											for(int var74 = 0; var74 < noStates; var74 += 1) {
																												if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																													if((var74 == traceTempVariable$currentState$69_1)) {
																														{
																															{
																																if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																																	double cv$temp$8$var157;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var157 = metric_mean[traceTempVariable$currentState$69_1];
																																		cv$temp$8$var157 = var157;
																																	}
																																	double cv$temp$9$var158;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var158 = metric_var[traceTempVariable$currentState$69_1];
																																		cv$temp$9$var158 = var158;
																																	}
																																	
																																	// Record the probability of sample task 170 generating output with current configuration.
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		// If the second value is -infinity.
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))));
																																	}
																																	
																																	// Recorded the probability of reaching sample task 170 with the current configuration.
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				
																				// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																				// the output of Sample task 136.
																				for(int var58 = 0; var58 < noStates; var58 += 1) {
																					if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																						if((var58 == traceTempVariable$currentState$69_1)) {
																							int traceTempVariable$currentState$89_1 = cv$currentValue;
																							if((index$sample$23 == index$sample$69_2)) {
																								if((index$timeStep$22 == timeStep$var145)) {
																									if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$89_1)) {
																													{
																														{
																															if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																																double cv$temp$10$var157;
																																{
																																	// Constructing a random variable input for use later.
																																	double var157 = metric_mean[traceTempVariable$currentState$89_1];
																																	cv$temp$10$var157 = var157;
																																}
																																double cv$temp$11$var158;
																																{
																																	// Constructing a random variable input for use later.
																																	double var158 = metric_var[traceTempVariable$currentState$89_1];
																																	cv$temp$11$var158 = var158;
																																}
																																
																																// Record the probability of sample task 170 generating output with current configuration.
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))));
																																}
																																
																																// Recorded the probability of reaching sample task 170 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							for(int index$sample$90 = 0; index$sample$90 < noSamples; index$sample$90 += 1) {
																								for(int index$timeStep$91 = 1; index$timeStep$91 < length$metric[index$sample$90]; index$timeStep$91 += 1) {
																									if(!((index$sample$90 == index$sample$23) && (index$timeStep$91 == index$timeStep$22))) {
																										// Enumerating the possible outputs of Categorical 129.
																										for(int index$sample136$92 = 0; index$sample136$92 < noStates; index$sample136$92 += 1) {
																											int distributionTempVariable$var130$94 = index$sample136$92;
																											
																											// Update the probability of sampling this value from the distribution value.
																											double cv$probabilitySample136Value93 = (1.0 * distribution$sample136[((index$sample$90 - 0) / 1)][((index$timeStep$91 - 1) / 1)][index$sample136$92]);
																											int traceTempVariable$currentState$95_1 = distributionTempVariable$var130$94;
																											if((index$sample$90 == index$sample$69_2)) {
																												if((index$timeStep$91 == timeStep$var145)) {
																													if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																														for(int var74 = 0; var74 < noStates; var74 += 1) {
																															if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																																if((var74 == traceTempVariable$currentState$95_1)) {
																																	{
																																		{
																																			if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																																				double cv$temp$12$var157;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var157 = metric_mean[traceTempVariable$currentState$95_1];
																																					cv$temp$12$var157 = var157;
																																				}
																																				double cv$temp$13$var158;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var158 = metric_var[traceTempVariable$currentState$95_1];
																																					cv$temp$13$var158 = var158;
																																				}
																																				
																																				// Record the probability of sample task 170 generating output with current configuration.
																																				if(((Math.log(cv$probabilitySample136Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					// If the second value is -infinity.
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))));
																																				}
																																				
																																				// Recorded the probability of reaching sample task 170 with the current configuration.
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value93);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
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
																}
															}
														}
													}
												}
											}
											int traceTempVariable$currentState$73_1 = cv$currentValue;
											for(int index$sample$73_2 = 0; index$sample$73_2 < noSamples; index$sample$73_2 += 1) {
												if((sample == index$sample$73_2)) {
													for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$73_2]; timeStep$var145 += 1) {
														if((timeStep$var122 == timeStep$var145)) {
															if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																if(!guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)]) {
																	// The body will execute, so should not be executed again
																	guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = true;
																	
																	// Processing sample task 170 of consumer random variable null.
																	{
																		if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																			// Set an accumulator to sum the probabilities for each possible configuration of
																			// inputs.
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			
																			// Set an accumulator to record the consumer distributions not seen. Initially set
																			// to 1 as seen values will be deducted from this value.
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																				// the output of Sample task 136.
																				for(int index$sample$157_1 = 0; index$sample$157_1 < noSamples; index$sample$157_1 += 1) {
																					if((index$sample$157_1 == index$sample$73_2)) {
																						if((0 == timeStep$var145)) {
																							if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																								for(int var58 = 0; var58 < noStates; var58 += 1) {
																									if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																										if((var58 == traceTempVariable$currentState$73_1)) {
																											for(int var74 = 0; var74 < noStates; var74 += 1) {
																												if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																													if((var74 == traceTempVariable$currentState$73_1)) {
																														{
																															{
																																if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																																	double cv$temp$40$var157;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var157 = metric_mean[traceTempVariable$currentState$73_1];
																																		cv$temp$40$var157 = var157;
																																	}
																																	double cv$temp$41$var158;
																																	{
																																		// Constructing a random variable input for use later.
																																		double var158 = metric_var[traceTempVariable$currentState$73_1];
																																		cv$temp$41$var158 = var158;
																																	}
																																	
																																	// Record the probability of sample task 170 generating output with current configuration.
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$40$var157) / Math.sqrt(cv$temp$41$var158))) - (0.5 * Math.log(cv$temp$41$var158)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$40$var157) / Math.sqrt(cv$temp$41$var158))) - (0.5 * Math.log(cv$temp$41$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		// If the second value is -infinity.
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$40$var157) / Math.sqrt(cv$temp$41$var158))) - (0.5 * Math.log(cv$temp$41$var158))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$40$var157) / Math.sqrt(cv$temp$41$var158))) - (0.5 * Math.log(cv$temp$41$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$40$var157) / Math.sqrt(cv$temp$41$var158))) - (0.5 * Math.log(cv$temp$41$var158)))));
																																	}
																																	
																																	// Recorded the probability of reaching sample task 170 with the current configuration.
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				
																				// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																				// the output of Sample task 136.
																				int traceTempVariable$currentState$160_1 = cv$currentValue;
																				if((index$sample$23 == index$sample$73_2)) {
																					if((index$timeStep$22 == timeStep$var145)) {
																						if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																							for(int var58 = 0; var58 < noStates; var58 += 1) {
																								if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																									if((var58 == traceTempVariable$currentState$160_1)) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$160_1)) {
																													{
																														{
																															if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																																double cv$temp$42$var157;
																																{
																																	// Constructing a random variable input for use later.
																																	double var157 = metric_mean[traceTempVariable$currentState$160_1];
																																	cv$temp$42$var157 = var157;
																																}
																																double cv$temp$43$var158;
																																{
																																	// Constructing a random variable input for use later.
																																	double var158 = metric_var[traceTempVariable$currentState$160_1];
																																	cv$temp$43$var158 = var158;
																																}
																																
																																// Record the probability of sample task 170 generating output with current configuration.
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$42$var157) / Math.sqrt(cv$temp$43$var158))) - (0.5 * Math.log(cv$temp$43$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$42$var157) / Math.sqrt(cv$temp$43$var158))) - (0.5 * Math.log(cv$temp$43$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$42$var157) / Math.sqrt(cv$temp$43$var158))) - (0.5 * Math.log(cv$temp$43$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$42$var157) / Math.sqrt(cv$temp$43$var158))) - (0.5 * Math.log(cv$temp$43$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$42$var157) / Math.sqrt(cv$temp$43$var158))) - (0.5 * Math.log(cv$temp$43$var158)))));
																																}
																																
																																// Recorded the probability of reaching sample task 170 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				for(int index$sample$161 = 0; index$sample$161 < noSamples; index$sample$161 += 1) {
																					for(int index$timeStep$162 = 1; index$timeStep$162 < length$metric[index$sample$161]; index$timeStep$162 += 1) {
																						if(!((index$sample$161 == index$sample$23) && (index$timeStep$162 == index$timeStep$22))) {
																							// Enumerating the possible outputs of Categorical 129.
																							for(int index$sample136$163 = 0; index$sample136$163 < noStates; index$sample136$163 += 1) {
																								int distributionTempVariable$var130$165 = index$sample136$163;
																								
																								// Update the probability of sampling this value from the distribution value.
																								double cv$probabilitySample136Value164 = (1.0 * distribution$sample136[((index$sample$161 - 0) / 1)][((index$timeStep$162 - 1) / 1)][index$sample136$163]);
																								int traceTempVariable$currentState$166_1 = distributionTempVariable$var130$165;
																								if((index$sample$161 == index$sample$73_2)) {
																									if((index$timeStep$162 == timeStep$var145)) {
																										if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																											for(int var58 = 0; var58 < noStates; var58 += 1) {
																												if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																													if((var58 == traceTempVariable$currentState$166_1)) {
																														for(int var74 = 0; var74 < noStates; var74 += 1) {
																															if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																																if((var74 == traceTempVariable$currentState$166_1)) {
																																	{
																																		{
																																			if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																																				double cv$temp$44$var157;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var157 = metric_mean[traceTempVariable$currentState$166_1];
																																					cv$temp$44$var157 = var157;
																																				}
																																				double cv$temp$45$var158;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var158 = metric_var[traceTempVariable$currentState$166_1];
																																					cv$temp$45$var158 = var158;
																																				}
																																				
																																				// Record the probability of sample task 170 generating output with current configuration.
																																				if(((Math.log(cv$probabilitySample136Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$44$var157) / Math.sqrt(cv$temp$45$var158))) - (0.5 * Math.log(cv$temp$45$var158)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$44$var157) / Math.sqrt(cv$temp$45$var158))) - (0.5 * Math.log(cv$temp$45$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					// If the second value is -infinity.
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$44$var157) / Math.sqrt(cv$temp$45$var158))) - (0.5 * Math.log(cv$temp$45$var158))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$44$var157) / Math.sqrt(cv$temp$45$var158))) - (0.5 * Math.log(cv$temp$45$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$44$var157) / Math.sqrt(cv$temp$45$var158))) - (0.5 * Math.log(cv$temp$45$var158)))));
																																				}
																																				
																																				// Recorded the probability of reaching sample task 170 with the current configuration.
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value164);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
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
																}
															}
														}
													}
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
				}
			} else {
				for(int index$sample$25 = 0; index$sample$25 < noSamples; index$sample$25 += 1) {
					if(true) {
						// Enumerating the possible outputs of Categorical 110.
						for(int index$sample117$26 = 0; index$sample117$26 < noStates; index$sample117$26 += 1) {
							int distributionTempVariable$var111$28 = index$sample117$26;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample117Value27 = (1.0 * distribution$sample117[((index$sample$25 - 0) / 1)][index$sample117$26]);
							int traceTempVariable$var127$29_1 = distributionTempVariable$var111$28;
							if((index$sample$25 == sample)) {
								if((0 == (timeStep$var122 - 1))) {
									for(int var39 = 0; var39 < noStates; var39 += 1) {
										if((var39 == traceTempVariable$var127$29_1)) {
											// Record the reached probability density.
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample117Value27);
											double[] cv$temp$1$var128;
											{
												// Constructing a random variable input for use later.
												double[] var128 = m[traceTempVariable$var127$29_1];
												cv$temp$1$var128 = var128;
											}
											
											// An accumulator to allow the value for each distribution to be constructed before
											// it is added to the index probabilities.
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample117Value27) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var128.length))?Math.log(cv$temp$1$var128[cv$currentValue]):Double.NEGATIVE_INFINITY));
											
											// Processing random variable 129.
											{
												// Looking for a path between Sample 136 and consumer Categorical 129.
												{
													int traceTempVariable$var127$42_1 = cv$currentValue;
												}
											}
											
											// Processing random variable 149.
											{
												// Looking for a path between Sample 136 and consumer Bernoulli 149.
												{
													int traceTempVariable$currentState$46_1 = cv$currentValue;
													for(int index$sample$46_2 = 0; index$sample$46_2 < noSamples; index$sample$46_2 += 1) {
														if((sample == index$sample$46_2)) {
															for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$46_2]; timeStep$var145 += 1) {
																if((timeStep$var122 == timeStep$var145)) {
																	// Processing sample task 158 of consumer random variable null.
																	{
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Bernoulli 149 which is consuming
																			// the output of Sample task 136.
																			for(int var90 = 0; var90 < noStates; var90 += 1) {
																				if((var90 == traceTempVariable$currentState$46_1)) {
																					{
																						{
																							double cv$temp$5$var148;
																							{
																								// Constructing a random variable input for use later.
																								double var148 = metric_valid_bias[traceTempVariable$currentState$46_1];
																								cv$temp$5$var148 = var148;
																							}
																							
																							// Record the probability of sample task 158 generating output with current configuration.
																							if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$46_2][timeStep$var145], cv$temp$5$var148)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$46_2][timeStep$var145], cv$temp$5$var148)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$46_2][timeStep$var145], cv$temp$5$var148));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$46_2][timeStep$var145], cv$temp$5$var148)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$46_2][timeStep$var145], cv$temp$5$var148)));
																							}
																							
																							// Recorded the probability of reaching sample task 158 with the current configuration.
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
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
															}
														}
													}
												}
											}
											
											// Processing random variable 159.
											{
												// Looking for a path between Sample 136 and consumer Gaussian 159.
												{
													// Guard to check that at most one copy of the code is executed for a given random
													// variable instance.
													boolean[][] guard$sample136gaussian169 = guard$sample136gaussian169$global;
													for(int index$sample$62_1 = 0; index$sample$62_1 < noSamples; index$sample$62_1 += 1) {
														if((sample == index$sample$62_1)) {
															for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$62_1]; timeStep$var145 += 1) {
																if((timeStep$var122 == timeStep$var145)) {
																	if(metric_valid_g[index$sample$62_1][timeStep$var145])
																		// Set the flags to false
																		guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = false;
																}
															}
														}
													}
													for(int index$sample$66_1 = 0; index$sample$66_1 < noSamples; index$sample$66_1 += 1) {
														if((sample == index$sample$66_1)) {
															for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$66_1]; timeStep$var145 += 1) {
																if((timeStep$var122 == timeStep$var145)) {
																	if(metric_valid_g[index$sample$66_1][timeStep$var145])
																		// Set the flags to false
																		guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = false;
																}
															}
														}
													}
													int traceTempVariable$currentState$70_1 = cv$currentValue;
													for(int index$sample$70_2 = 0; index$sample$70_2 < noSamples; index$sample$70_2 += 1) {
														if((sample == index$sample$70_2)) {
															for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$70_2]; timeStep$var145 += 1) {
																if((timeStep$var122 == timeStep$var145)) {
																	if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																		if(!guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = true;
																			
																			// Processing sample task 170 of consumer random variable null.
																			{
																				if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																					// Set an accumulator to sum the probabilities for each possible configuration of
																					// inputs.
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					
																					// Set an accumulator to record the consumer distributions not seen. Initially set
																					// to 1 as seen values will be deducted from this value.
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																						// the output of Sample task 136.
																						for(int var58 = 0; var58 < noStates; var58 += 1) {
																							if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																								if((var58 == traceTempVariable$currentState$70_1)) {
																									int traceTempVariable$currentState$99_1 = distributionTempVariable$var111$28;
																									if((index$sample$25 == index$sample$70_2)) {
																										if((0 == timeStep$var145)) {
																											if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$99_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																																		double cv$temp$14$var157;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var157 = metric_mean[traceTempVariable$currentState$99_1];
																																			cv$temp$14$var157 = var157;
																																		}
																																		double cv$temp$15$var158;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var158 = metric_var[traceTempVariable$currentState$99_1];
																																			cv$temp$15$var158 = var158;
																																		}
																																		
																																		// Record the probability of sample task 170 generating output with current configuration.
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 170 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																									for(int index$sample$100 = 0; index$sample$100 < noSamples; index$sample$100 += 1) {
																										if(!(index$sample$100 == index$sample$25)) {
																											// Enumerating the possible outputs of Categorical 110.
																											for(int index$sample117$101 = 0; index$sample117$101 < noStates; index$sample117$101 += 1) {
																												int distributionTempVariable$var111$103 = index$sample117$101;
																												
																												// Update the probability of sampling this value from the distribution value.
																												double cv$probabilitySample117Value102 = (1.0 * distribution$sample117[((index$sample$100 - 0) / 1)][index$sample117$101]);
																												int traceTempVariable$currentState$104_1 = distributionTempVariable$var111$103;
																												if((index$sample$100 == index$sample$70_2)) {
																													if((0 == timeStep$var145)) {
																														if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																															for(int var74 = 0; var74 < noStates; var74 += 1) {
																																if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																																	if((var74 == traceTempVariable$currentState$104_1)) {
																																		{
																																			{
																																				if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																																					double cv$temp$16$var157;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var157 = metric_mean[traceTempVariable$currentState$104_1];
																																						cv$temp$16$var157 = var157;
																																					}
																																					double cv$temp$17$var158;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var158 = metric_var[traceTempVariable$currentState$104_1];
																																						cv$temp$17$var158 = var158;
																																					}
																																					
																																					// Record the probability of sample task 170 generating output with current configuration.
																																					if(((Math.log(cv$probabilitySample117Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						// If the second value is -infinity.
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))));
																																					}
																																					
																																					// Recorded the probability of reaching sample task 170 with the current configuration.
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value102);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						
																						// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																						// the output of Sample task 136.
																						for(int var58 = 0; var58 < noStates; var58 += 1) {
																							if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																								if((var58 == traceTempVariable$currentState$70_1)) {
																									int traceTempVariable$currentState$108_1 = cv$currentValue;
																									if((index$sample$23 == index$sample$70_2)) {
																										if((index$timeStep$22 == timeStep$var145)) {
																											if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$108_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																																		double cv$temp$18$var157;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var157 = metric_mean[traceTempVariable$currentState$108_1];
																																			cv$temp$18$var157 = var157;
																																		}
																																		double cv$temp$19$var158;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var158 = metric_var[traceTempVariable$currentState$108_1];
																																			cv$temp$19$var158 = var158;
																																		}
																																		
																																		// Record the probability of sample task 170 generating output with current configuration.
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 170 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																									for(int index$sample$109 = 0; index$sample$109 < noSamples; index$sample$109 += 1) {
																										for(int index$timeStep$110 = 1; index$timeStep$110 < length$metric[index$sample$109]; index$timeStep$110 += 1) {
																											if(!((index$sample$109 == index$sample$23) && (index$timeStep$110 == index$timeStep$22))) {
																												// Enumerating the possible outputs of Categorical 129.
																												for(int index$sample136$111 = 0; index$sample136$111 < noStates; index$sample136$111 += 1) {
																													int distributionTempVariable$var130$113 = index$sample136$111;
																													
																													// Update the probability of sampling this value from the distribution value.
																													double cv$probabilitySample136Value112 = (1.0 * distribution$sample136[((index$sample$109 - 0) / 1)][((index$timeStep$110 - 1) / 1)][index$sample136$111]);
																													int traceTempVariable$currentState$114_1 = distributionTempVariable$var130$113;
																													if((index$sample$109 == index$sample$70_2)) {
																														if((index$timeStep$110 == timeStep$var145)) {
																															if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																																for(int var74 = 0; var74 < noStates; var74 += 1) {
																																	if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																																		if((var74 == traceTempVariable$currentState$114_1)) {
																																			{
																																				{
																																					if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																																						double cv$temp$20$var157;
																																						{
																																							// Constructing a random variable input for use later.
																																							double var157 = metric_mean[traceTempVariable$currentState$114_1];
																																							cv$temp$20$var157 = var157;
																																						}
																																						double cv$temp$21$var158;
																																						{
																																							// Constructing a random variable input for use later.
																																							double var158 = metric_var[traceTempVariable$currentState$114_1];
																																							cv$temp$21$var158 = var158;
																																						}
																																						
																																						// Record the probability of sample task 170 generating output with current configuration.
																																						if(((Math.log(cv$probabilitySample136Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))));
																																						}
																																						
																																						// Recorded the probability of reaching sample task 170 with the current configuration.
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value112);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
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
																		}
																	}
																}
															}
														}
													}
													int traceTempVariable$currentState$74_1 = cv$currentValue;
													for(int index$sample$74_2 = 0; index$sample$74_2 < noSamples; index$sample$74_2 += 1) {
														if((sample == index$sample$74_2)) {
															for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$74_2]; timeStep$var145 += 1) {
																if((timeStep$var122 == timeStep$var145)) {
																	if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																		if(!guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = true;
																			
																			// Processing sample task 170 of consumer random variable null.
																			{
																				if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																					// Set an accumulator to sum the probabilities for each possible configuration of
																					// inputs.
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					
																					// Set an accumulator to record the consumer distributions not seen. Initially set
																					// to 1 as seen values will be deducted from this value.
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																						// the output of Sample task 136.
																						int traceTempVariable$currentState$171_1 = distributionTempVariable$var111$28;
																						if((index$sample$25 == index$sample$74_2)) {
																							if((0 == timeStep$var145)) {
																								if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																									for(int var58 = 0; var58 < noStates; var58 += 1) {
																										if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																											if((var58 == traceTempVariable$currentState$171_1)) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$171_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																																		double cv$temp$46$var157;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var157 = metric_mean[traceTempVariable$currentState$171_1];
																																			cv$temp$46$var157 = var157;
																																		}
																																		double cv$temp$47$var158;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var158 = metric_var[traceTempVariable$currentState$171_1];
																																			cv$temp$47$var158 = var158;
																																		}
																																		
																																		// Record the probability of sample task 170 generating output with current configuration.
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$46$var157) / Math.sqrt(cv$temp$47$var158))) - (0.5 * Math.log(cv$temp$47$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$46$var157) / Math.sqrt(cv$temp$47$var158))) - (0.5 * Math.log(cv$temp$47$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$46$var157) / Math.sqrt(cv$temp$47$var158))) - (0.5 * Math.log(cv$temp$47$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$46$var157) / Math.sqrt(cv$temp$47$var158))) - (0.5 * Math.log(cv$temp$47$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$46$var157) / Math.sqrt(cv$temp$47$var158))) - (0.5 * Math.log(cv$temp$47$var158)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 170 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						for(int index$sample$172 = 0; index$sample$172 < noSamples; index$sample$172 += 1) {
																							if(!(index$sample$172 == index$sample$25)) {
																								// Enumerating the possible outputs of Categorical 110.
																								for(int index$sample117$173 = 0; index$sample117$173 < noStates; index$sample117$173 += 1) {
																									int distributionTempVariable$var111$175 = index$sample117$173;
																									
																									// Update the probability of sampling this value from the distribution value.
																									double cv$probabilitySample117Value174 = (1.0 * distribution$sample117[((index$sample$172 - 0) / 1)][index$sample117$173]);
																									int traceTempVariable$currentState$176_1 = distributionTempVariable$var111$175;
																									if((index$sample$172 == index$sample$74_2)) {
																										if((0 == timeStep$var145)) {
																											if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																												for(int var58 = 0; var58 < noStates; var58 += 1) {
																													if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																														if((var58 == traceTempVariable$currentState$176_1)) {
																															for(int var74 = 0; var74 < noStates; var74 += 1) {
																																if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																																	if((var74 == traceTempVariable$currentState$176_1)) {
																																		{
																																			{
																																				if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																																					double cv$temp$48$var157;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var157 = metric_mean[traceTempVariable$currentState$176_1];
																																						cv$temp$48$var157 = var157;
																																					}
																																					double cv$temp$49$var158;
																																					{
																																						// Constructing a random variable input for use later.
																																						double var158 = metric_var[traceTempVariable$currentState$176_1];
																																						cv$temp$49$var158 = var158;
																																					}
																																					
																																					// Record the probability of sample task 170 generating output with current configuration.
																																					if(((Math.log(cv$probabilitySample117Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$48$var157) / Math.sqrt(cv$temp$49$var158))) - (0.5 * Math.log(cv$temp$49$var158)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$48$var157) / Math.sqrt(cv$temp$49$var158))) - (0.5 * Math.log(cv$temp$49$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						// If the second value is -infinity.
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$48$var157) / Math.sqrt(cv$temp$49$var158))) - (0.5 * Math.log(cv$temp$49$var158))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$48$var157) / Math.sqrt(cv$temp$49$var158))) - (0.5 * Math.log(cv$temp$49$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$48$var157) / Math.sqrt(cv$temp$49$var158))) - (0.5 * Math.log(cv$temp$49$var158)))));
																																					}
																																					
																																					// Recorded the probability of reaching sample task 170 with the current configuration.
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value174);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						
																						// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																						// the output of Sample task 136.
																						int traceTempVariable$currentState$181_1 = cv$currentValue;
																						if((index$sample$23 == index$sample$74_2)) {
																							if((index$timeStep$22 == timeStep$var145)) {
																								if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																									for(int var58 = 0; var58 < noStates; var58 += 1) {
																										if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																											if((var58 == traceTempVariable$currentState$181_1)) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$181_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																																		double cv$temp$50$var157;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var157 = metric_mean[traceTempVariable$currentState$181_1];
																																			cv$temp$50$var157 = var157;
																																		}
																																		double cv$temp$51$var158;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var158 = metric_var[traceTempVariable$currentState$181_1];
																																			cv$temp$51$var158 = var158;
																																		}
																																		
																																		// Record the probability of sample task 170 generating output with current configuration.
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$50$var157) / Math.sqrt(cv$temp$51$var158))) - (0.5 * Math.log(cv$temp$51$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$50$var157) / Math.sqrt(cv$temp$51$var158))) - (0.5 * Math.log(cv$temp$51$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$50$var157) / Math.sqrt(cv$temp$51$var158))) - (0.5 * Math.log(cv$temp$51$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$50$var157) / Math.sqrt(cv$temp$51$var158))) - (0.5 * Math.log(cv$temp$51$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$50$var157) / Math.sqrt(cv$temp$51$var158))) - (0.5 * Math.log(cv$temp$51$var158)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 170 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						for(int index$sample$182 = 0; index$sample$182 < noSamples; index$sample$182 += 1) {
																							for(int index$timeStep$183 = 1; index$timeStep$183 < length$metric[index$sample$182]; index$timeStep$183 += 1) {
																								if(!((index$sample$182 == index$sample$23) && (index$timeStep$183 == index$timeStep$22))) {
																									// Enumerating the possible outputs of Categorical 129.
																									for(int index$sample136$184 = 0; index$sample136$184 < noStates; index$sample136$184 += 1) {
																										int distributionTempVariable$var130$186 = index$sample136$184;
																										
																										// Update the probability of sampling this value from the distribution value.
																										double cv$probabilitySample136Value185 = (1.0 * distribution$sample136[((index$sample$182 - 0) / 1)][((index$timeStep$183 - 1) / 1)][index$sample136$184]);
																										int traceTempVariable$currentState$187_1 = distributionTempVariable$var130$186;
																										if((index$sample$182 == index$sample$74_2)) {
																											if((index$timeStep$183 == timeStep$var145)) {
																												if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																													for(int var58 = 0; var58 < noStates; var58 += 1) {
																														if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																															if((var58 == traceTempVariable$currentState$187_1)) {
																																for(int var74 = 0; var74 < noStates; var74 += 1) {
																																	if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																																		if((var74 == traceTempVariable$currentState$187_1)) {
																																			{
																																				{
																																					if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																																						double cv$temp$52$var157;
																																						{
																																							// Constructing a random variable input for use later.
																																							double var157 = metric_mean[traceTempVariable$currentState$187_1];
																																							cv$temp$52$var157 = var157;
																																						}
																																						double cv$temp$53$var158;
																																						{
																																							// Constructing a random variable input for use later.
																																							double var158 = metric_var[traceTempVariable$currentState$187_1];
																																							cv$temp$53$var158 = var158;
																																						}
																																						
																																						// Record the probability of sample task 170 generating output with current configuration.
																																						if(((Math.log(cv$probabilitySample136Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$52$var157) / Math.sqrt(cv$temp$53$var158))) - (0.5 * Math.log(cv$temp$53$var158)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$52$var157) / Math.sqrt(cv$temp$53$var158))) - (0.5 * Math.log(cv$temp$53$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$52$var157) / Math.sqrt(cv$temp$53$var158))) - (0.5 * Math.log(cv$temp$53$var158))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$52$var157) / Math.sqrt(cv$temp$53$var158))) - (0.5 * Math.log(cv$temp$53$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$52$var157) / Math.sqrt(cv$temp$53$var158))) - (0.5 * Math.log(cv$temp$53$var158)))));
																																						}
																																						
																																						// Recorded the probability of reaching sample task 170 with the current configuration.
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value185);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
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
																		}
																	}
																}
															}
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
						}
					}
				}
			}
			
			// Enumerating the possible arguments for Categorical 129.
			int traceTempVariable$var127$32_1 = cv$currentValue;
			if((index$sample$23 == sample)) {
				if((index$timeStep$22 == (timeStep$var122 - 1))) {
					for(int var39 = 0; var39 < noStates; var39 += 1) {
						if((var39 == traceTempVariable$var127$32_1)) {
							// Record the reached probability density.
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$2$var128;
							{
								// Constructing a random variable input for use later.
								double[] var128 = m[traceTempVariable$var127$32_1];
								cv$temp$2$var128 = var128;
							}
							
							// An accumulator to allow the value for each distribution to be constructed before
							// it is added to the index probabilities.
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var128.length))?Math.log(cv$temp$2$var128[cv$currentValue]):Double.NEGATIVE_INFINITY));
							
							// Processing random variable 129.
							{
								// Looking for a path between Sample 136 and consumer Categorical 129.
								{
									int traceTempVariable$var127$43_1 = cv$currentValue;
								}
							}
							
							// Processing random variable 149.
							{
								// Looking for a path between Sample 136 and consumer Bernoulli 149.
								{
									int traceTempVariable$currentState$47_1 = cv$currentValue;
									for(int index$sample$47_2 = 0; index$sample$47_2 < noSamples; index$sample$47_2 += 1) {
										if((sample == index$sample$47_2)) {
											for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$47_2]; timeStep$var145 += 1) {
												if((timeStep$var122 == timeStep$var145)) {
													// Processing sample task 158 of consumer random variable null.
													{
														// Set an accumulator to sum the probabilities for each possible configuration of
														// inputs.
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														
														// Set an accumulator to record the consumer distributions not seen. Initially set
														// to 1 as seen values will be deducted from this value.
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															// Enumerating the possible arguments for the variable Bernoulli 149 which is consuming
															// the output of Sample task 136.
															for(int var90 = 0; var90 < noStates; var90 += 1) {
																if((var90 == traceTempVariable$currentState$47_1)) {
																	{
																		{
																			double cv$temp$6$var148;
																			{
																				// Constructing a random variable input for use later.
																				double var148 = metric_valid_bias[traceTempVariable$currentState$47_1];
																				cv$temp$6$var148 = var148;
																			}
																			
																			// Record the probability of sample task 158 generating output with current configuration.
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$47_2][timeStep$var145], cv$temp$6$var148)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$47_2][timeStep$var145], cv$temp$6$var148)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$47_2][timeStep$var145], cv$temp$6$var148));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$47_2][timeStep$var145], cv$temp$6$var148)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$47_2][timeStep$var145], cv$temp$6$var148)));
																			}
																			
																			// Recorded the probability of reaching sample task 158 with the current configuration.
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
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
											}
										}
									}
								}
							}
							
							// Processing random variable 159.
							{
								// Looking for a path between Sample 136 and consumer Gaussian 159.
								{
									// Guard to check that at most one copy of the code is executed for a given random
									// variable instance.
									boolean[][] guard$sample136gaussian169 = guard$sample136gaussian169$global;
									for(int index$sample$63_1 = 0; index$sample$63_1 < noSamples; index$sample$63_1 += 1) {
										if((sample == index$sample$63_1)) {
											for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$63_1]; timeStep$var145 += 1) {
												if((timeStep$var122 == timeStep$var145)) {
													if(metric_valid_g[index$sample$63_1][timeStep$var145])
														// Set the flags to false
														guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = false;
												}
											}
										}
									}
									for(int index$sample$67_1 = 0; index$sample$67_1 < noSamples; index$sample$67_1 += 1) {
										if((sample == index$sample$67_1)) {
											for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$67_1]; timeStep$var145 += 1) {
												if((timeStep$var122 == timeStep$var145)) {
													if(metric_valid_g[index$sample$67_1][timeStep$var145])
														// Set the flags to false
														guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = false;
												}
											}
										}
									}
									int traceTempVariable$currentState$71_1 = cv$currentValue;
									for(int index$sample$71_2 = 0; index$sample$71_2 < noSamples; index$sample$71_2 += 1) {
										if((sample == index$sample$71_2)) {
											for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$71_2]; timeStep$var145 += 1) {
												if((timeStep$var122 == timeStep$var145)) {
													if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
														if(!guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = true;
															
															// Processing sample task 170 of consumer random variable null.
															{
																if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																	// Set an accumulator to sum the probabilities for each possible configuration of
																	// inputs.
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	
																	// Set an accumulator to record the consumer distributions not seen. Initially set
																	// to 1 as seen values will be deducted from this value.
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																		// the output of Sample task 136.
																		for(int var58 = 0; var58 < noStates; var58 += 1) {
																			if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																				if((var58 == traceTempVariable$currentState$71_1)) {
																					if(fixedFlag$sample117) {
																						for(int index$sample$118_1 = 0; index$sample$118_1 < noSamples; index$sample$118_1 += 1) {
																							if((index$sample$118_1 == index$sample$71_2)) {
																								if((0 == timeStep$var145)) {
																									if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$71_1)) {
																													{
																														{
																															if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																																double cv$temp$22$var157;
																																{
																																	// Constructing a random variable input for use later.
																																	double var157 = metric_mean[traceTempVariable$currentState$71_1];
																																	cv$temp$22$var157 = var157;
																																}
																																double cv$temp$23$var158;
																																{
																																	// Constructing a random variable input for use later.
																																	double var158 = metric_var[traceTempVariable$currentState$71_1];
																																	cv$temp$23$var158 = var158;
																																}
																																
																																// Record the probability of sample task 170 generating output with current configuration.
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))));
																																}
																																
																																// Recorded the probability of reaching sample task 170 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					} else {
																						for(int index$sample$119 = 0; index$sample$119 < noSamples; index$sample$119 += 1) {
																							if(true) {
																								// Enumerating the possible outputs of Categorical 110.
																								for(int index$sample117$120 = 0; index$sample117$120 < noStates; index$sample117$120 += 1) {
																									int distributionTempVariable$var111$122 = index$sample117$120;
																									
																									// Update the probability of sampling this value from the distribution value.
																									double cv$probabilitySample117Value121 = (1.0 * distribution$sample117[((index$sample$119 - 0) / 1)][index$sample117$120]);
																									int traceTempVariable$currentState$123_1 = distributionTempVariable$var111$122;
																									if((index$sample$119 == index$sample$71_2)) {
																										if((0 == timeStep$var145)) {
																											if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$123_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																																		double cv$temp$24$var157;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var157 = metric_mean[traceTempVariable$currentState$123_1];
																																			cv$temp$24$var157 = var157;
																																		}
																																		double cv$temp$25$var158;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var158 = metric_var[traceTempVariable$currentState$123_1];
																																			cv$temp$25$var158 = var158;
																																		}
																																		
																																		// Record the probability of sample task 170 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample117Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 170 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value121);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		
																		// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																		// the output of Sample task 136.
																		for(int var58 = 0; var58 < noStates; var58 += 1) {
																			if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																				if((var58 == traceTempVariable$currentState$71_1)) {
																					int traceTempVariable$currentState$127_1 = cv$currentValue;
																					if((index$sample$23 == index$sample$71_2)) {
																						if((index$timeStep$22 == timeStep$var145)) {
																							if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$127_1)) {
																											{
																												{
																													if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																														double cv$temp$26$var157;
																														{
																															// Constructing a random variable input for use later.
																															double var157 = metric_mean[traceTempVariable$currentState$127_1];
																															cv$temp$26$var157 = var157;
																														}
																														double cv$temp$27$var158;
																														{
																															// Constructing a random variable input for use later.
																															double var158 = metric_var[traceTempVariable$currentState$127_1];
																															cv$temp$27$var158 = var158;
																														}
																														
																														// Record the probability of sample task 170 generating output with current configuration.
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))));
																														}
																														
																														// Recorded the probability of reaching sample task 170 with the current configuration.
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$128 = 0; index$sample$128 < noSamples; index$sample$128 += 1) {
																						for(int index$timeStep$129 = 1; index$timeStep$129 < length$metric[index$sample$128]; index$timeStep$129 += 1) {
																							if(!((index$sample$128 == index$sample$23) && (index$timeStep$129 == index$timeStep$22))) {
																								// Enumerating the possible outputs of Categorical 129.
																								for(int index$sample136$130 = 0; index$sample136$130 < noStates; index$sample136$130 += 1) {
																									int distributionTempVariable$var130$132 = index$sample136$130;
																									
																									// Update the probability of sampling this value from the distribution value.
																									double cv$probabilitySample136Value131 = (1.0 * distribution$sample136[((index$sample$128 - 0) / 1)][((index$timeStep$129 - 1) / 1)][index$sample136$130]);
																									int traceTempVariable$currentState$133_1 = distributionTempVariable$var130$132;
																									if((index$sample$128 == index$sample$71_2)) {
																										if((index$timeStep$129 == timeStep$var145)) {
																											if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$133_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																																		double cv$temp$28$var157;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var157 = metric_mean[traceTempVariable$currentState$133_1];
																																			cv$temp$28$var157 = var157;
																																		}
																																		double cv$temp$29$var158;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var158 = metric_var[traceTempVariable$currentState$133_1];
																																			cv$temp$29$var158 = var158;
																																		}
																																		
																																		// Record the probability of sample task 170 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample136Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 170 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value131);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
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
														}
													}
												}
											}
										}
									}
									int traceTempVariable$currentState$75_1 = cv$currentValue;
									for(int index$sample$75_2 = 0; index$sample$75_2 < noSamples; index$sample$75_2 += 1) {
										if((sample == index$sample$75_2)) {
											for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$75_2]; timeStep$var145 += 1) {
												if((timeStep$var122 == timeStep$var145)) {
													if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
														if(!guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = true;
															
															// Processing sample task 170 of consumer random variable null.
															{
																if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																	// Set an accumulator to sum the probabilities for each possible configuration of
																	// inputs.
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	
																	// Set an accumulator to record the consumer distributions not seen. Initially set
																	// to 1 as seen values will be deducted from this value.
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																		// the output of Sample task 136.
																		if(fixedFlag$sample117) {
																			for(int index$sample$192_1 = 0; index$sample$192_1 < noSamples; index$sample$192_1 += 1) {
																				if((index$sample$192_1 == index$sample$75_2)) {
																					if((0 == timeStep$var145)) {
																						if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																							for(int var58 = 0; var58 < noStates; var58 += 1) {
																								if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																									if((var58 == traceTempVariable$currentState$75_1)) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$75_1)) {
																													{
																														{
																															if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																																double cv$temp$54$var157;
																																{
																																	// Constructing a random variable input for use later.
																																	double var157 = metric_mean[traceTempVariable$currentState$75_1];
																																	cv$temp$54$var157 = var157;
																																}
																																double cv$temp$55$var158;
																																{
																																	// Constructing a random variable input for use later.
																																	double var158 = metric_var[traceTempVariable$currentState$75_1];
																																	cv$temp$55$var158 = var158;
																																}
																																
																																// Record the probability of sample task 170 generating output with current configuration.
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$54$var157) / Math.sqrt(cv$temp$55$var158))) - (0.5 * Math.log(cv$temp$55$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$54$var157) / Math.sqrt(cv$temp$55$var158))) - (0.5 * Math.log(cv$temp$55$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$54$var157) / Math.sqrt(cv$temp$55$var158))) - (0.5 * Math.log(cv$temp$55$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$54$var157) / Math.sqrt(cv$temp$55$var158))) - (0.5 * Math.log(cv$temp$55$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$54$var157) / Math.sqrt(cv$temp$55$var158))) - (0.5 * Math.log(cv$temp$55$var158)))));
																																}
																																
																																// Recorded the probability of reaching sample task 170 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		} else {
																			for(int index$sample$193 = 0; index$sample$193 < noSamples; index$sample$193 += 1) {
																				if(true) {
																					// Enumerating the possible outputs of Categorical 110.
																					for(int index$sample117$194 = 0; index$sample117$194 < noStates; index$sample117$194 += 1) {
																						int distributionTempVariable$var111$196 = index$sample117$194;
																						
																						// Update the probability of sampling this value from the distribution value.
																						double cv$probabilitySample117Value195 = (1.0 * distribution$sample117[((index$sample$193 - 0) / 1)][index$sample117$194]);
																						int traceTempVariable$currentState$197_1 = distributionTempVariable$var111$196;
																						if((index$sample$193 == index$sample$75_2)) {
																							if((0 == timeStep$var145)) {
																								if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																									for(int var58 = 0; var58 < noStates; var58 += 1) {
																										if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																											if((var58 == traceTempVariable$currentState$197_1)) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$197_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																																		double cv$temp$56$var157;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var157 = metric_mean[traceTempVariable$currentState$197_1];
																																			cv$temp$56$var157 = var157;
																																		}
																																		double cv$temp$57$var158;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var158 = metric_var[traceTempVariable$currentState$197_1];
																																			cv$temp$57$var158 = var158;
																																		}
																																		
																																		// Record the probability of sample task 170 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample117Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$56$var157) / Math.sqrt(cv$temp$57$var158))) - (0.5 * Math.log(cv$temp$57$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$56$var157) / Math.sqrt(cv$temp$57$var158))) - (0.5 * Math.log(cv$temp$57$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$56$var157) / Math.sqrt(cv$temp$57$var158))) - (0.5 * Math.log(cv$temp$57$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$56$var157) / Math.sqrt(cv$temp$57$var158))) - (0.5 * Math.log(cv$temp$57$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$56$var157) / Math.sqrt(cv$temp$57$var158))) - (0.5 * Math.log(cv$temp$57$var158)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 170 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value195);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		
																		// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																		// the output of Sample task 136.
																		int traceTempVariable$currentState$202_1 = cv$currentValue;
																		if((index$sample$23 == index$sample$75_2)) {
																			if((index$timeStep$22 == timeStep$var145)) {
																				if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																					for(int var58 = 0; var58 < noStates; var58 += 1) {
																						if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																							if((var58 == traceTempVariable$currentState$202_1)) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$202_1)) {
																											{
																												{
																													if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																														double cv$temp$58$var157;
																														{
																															// Constructing a random variable input for use later.
																															double var157 = metric_mean[traceTempVariable$currentState$202_1];
																															cv$temp$58$var157 = var157;
																														}
																														double cv$temp$59$var158;
																														{
																															// Constructing a random variable input for use later.
																															double var158 = metric_var[traceTempVariable$currentState$202_1];
																															cv$temp$59$var158 = var158;
																														}
																														
																														// Record the probability of sample task 170 generating output with current configuration.
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$58$var157) / Math.sqrt(cv$temp$59$var158))) - (0.5 * Math.log(cv$temp$59$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$58$var157) / Math.sqrt(cv$temp$59$var158))) - (0.5 * Math.log(cv$temp$59$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$58$var157) / Math.sqrt(cv$temp$59$var158))) - (0.5 * Math.log(cv$temp$59$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$58$var157) / Math.sqrt(cv$temp$59$var158))) - (0.5 * Math.log(cv$temp$59$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$58$var157) / Math.sqrt(cv$temp$59$var158))) - (0.5 * Math.log(cv$temp$59$var158)))));
																														}
																														
																														// Recorded the probability of reaching sample task 170 with the current configuration.
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		for(int index$sample$203 = 0; index$sample$203 < noSamples; index$sample$203 += 1) {
																			for(int index$timeStep$204 = 1; index$timeStep$204 < length$metric[index$sample$203]; index$timeStep$204 += 1) {
																				if(!((index$sample$203 == index$sample$23) && (index$timeStep$204 == index$timeStep$22))) {
																					// Enumerating the possible outputs of Categorical 129.
																					for(int index$sample136$205 = 0; index$sample136$205 < noStates; index$sample136$205 += 1) {
																						int distributionTempVariable$var130$207 = index$sample136$205;
																						
																						// Update the probability of sampling this value from the distribution value.
																						double cv$probabilitySample136Value206 = (1.0 * distribution$sample136[((index$sample$203 - 0) / 1)][((index$timeStep$204 - 1) / 1)][index$sample136$205]);
																						int traceTempVariable$currentState$208_1 = distributionTempVariable$var130$207;
																						if((index$sample$203 == index$sample$75_2)) {
																							if((index$timeStep$204 == timeStep$var145)) {
																								if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																									for(int var58 = 0; var58 < noStates; var58 += 1) {
																										if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																											if((var58 == traceTempVariable$currentState$208_1)) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$208_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																																		double cv$temp$60$var157;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var157 = metric_mean[traceTempVariable$currentState$208_1];
																																			cv$temp$60$var157 = var157;
																																		}
																																		double cv$temp$61$var158;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var158 = metric_var[traceTempVariable$currentState$208_1];
																																			cv$temp$61$var158 = var158;
																																		}
																																		
																																		// Record the probability of sample task 170 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample136Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$60$var157) / Math.sqrt(cv$temp$61$var158))) - (0.5 * Math.log(cv$temp$61$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$60$var157) / Math.sqrt(cv$temp$61$var158))) - (0.5 * Math.log(cv$temp$61$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$60$var157) / Math.sqrt(cv$temp$61$var158))) - (0.5 * Math.log(cv$temp$61$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$60$var157) / Math.sqrt(cv$temp$61$var158))) - (0.5 * Math.log(cv$temp$61$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$60$var157) / Math.sqrt(cv$temp$61$var158))) - (0.5 * Math.log(cv$temp$61$var158)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 170 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value206);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
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
														}
													}
												}
											}
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
			for(int index$sample$33 = 0; index$sample$33 < noSamples; index$sample$33 += 1) {
				for(int index$timeStep$34 = 1; index$timeStep$34 < length$metric[index$sample$33]; index$timeStep$34 += 1) {
					if(!((index$sample$33 == index$sample$23) && (index$timeStep$34 == index$timeStep$22))) {
						// Enumerating the possible outputs of Categorical 129.
						for(int index$sample136$35 = 0; index$sample136$35 < noStates; index$sample136$35 += 1) {
							int distributionTempVariable$var130$37 = index$sample136$35;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample136Value36 = (1.0 * distribution$sample136[((index$sample$33 - 0) / 1)][((index$timeStep$34 - 1) / 1)][index$sample136$35]);
							int traceTempVariable$var127$38_1 = distributionTempVariable$var130$37;
							if((index$sample$33 == sample)) {
								if((index$timeStep$34 == (timeStep$var122 - 1))) {
									for(int var39 = 0; var39 < noStates; var39 += 1) {
										if((var39 == traceTempVariable$var127$38_1)) {
											// Record the reached probability density.
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample136Value36);
											double[] cv$temp$3$var128;
											{
												// Constructing a random variable input for use later.
												double[] var128 = m[traceTempVariable$var127$38_1];
												cv$temp$3$var128 = var128;
											}
											
											// An accumulator to allow the value for each distribution to be constructed before
											// it is added to the index probabilities.
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample136Value36) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$var128.length))?Math.log(cv$temp$3$var128[cv$currentValue]):Double.NEGATIVE_INFINITY));
											
											// Processing random variable 129.
											{
												// Looking for a path between Sample 136 and consumer Categorical 129.
												{
													int traceTempVariable$var127$44_1 = cv$currentValue;
												}
											}
											
											// Processing random variable 149.
											{
												// Looking for a path between Sample 136 and consumer Bernoulli 149.
												{
													int traceTempVariable$currentState$48_1 = cv$currentValue;
													for(int index$sample$48_2 = 0; index$sample$48_2 < noSamples; index$sample$48_2 += 1) {
														if((sample == index$sample$48_2)) {
															for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$48_2]; timeStep$var145 += 1) {
																if((timeStep$var122 == timeStep$var145)) {
																	// Processing sample task 158 of consumer random variable null.
																	{
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Bernoulli 149 which is consuming
																			// the output of Sample task 136.
																			for(int var90 = 0; var90 < noStates; var90 += 1) {
																				if((var90 == traceTempVariable$currentState$48_1)) {
																					{
																						{
																							double cv$temp$7$var148;
																							{
																								// Constructing a random variable input for use later.
																								double var148 = metric_valid_bias[traceTempVariable$currentState$48_1];
																								cv$temp$7$var148 = var148;
																							}
																							
																							// Record the probability of sample task 158 generating output with current configuration.
																							if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$48_2][timeStep$var145], cv$temp$7$var148)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$48_2][timeStep$var145], cv$temp$7$var148)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$48_2][timeStep$var145], cv$temp$7$var148));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$48_2][timeStep$var145], cv$temp$7$var148)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$48_2][timeStep$var145], cv$temp$7$var148)));
																							}
																							
																							// Recorded the probability of reaching sample task 158 with the current configuration.
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
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
															}
														}
													}
												}
											}
											
											// Processing random variable 159.
											{
												// Looking for a path between Sample 136 and consumer Gaussian 159.
												{
													// Guard to check that at most one copy of the code is executed for a given random
													// variable instance.
													boolean[][] guard$sample136gaussian169 = guard$sample136gaussian169$global;
													for(int index$sample$64_1 = 0; index$sample$64_1 < noSamples; index$sample$64_1 += 1) {
														if((sample == index$sample$64_1)) {
															for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$64_1]; timeStep$var145 += 1) {
																if((timeStep$var122 == timeStep$var145)) {
																	if(metric_valid_g[index$sample$64_1][timeStep$var145])
																		// Set the flags to false
																		guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = false;
																}
															}
														}
													}
													for(int index$sample$68_1 = 0; index$sample$68_1 < noSamples; index$sample$68_1 += 1) {
														if((sample == index$sample$68_1)) {
															for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$68_1]; timeStep$var145 += 1) {
																if((timeStep$var122 == timeStep$var145)) {
																	if(metric_valid_g[index$sample$68_1][timeStep$var145])
																		// Set the flags to false
																		guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = false;
																}
															}
														}
													}
													int traceTempVariable$currentState$72_1 = cv$currentValue;
													for(int index$sample$72_2 = 0; index$sample$72_2 < noSamples; index$sample$72_2 += 1) {
														if((sample == index$sample$72_2)) {
															for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$72_2]; timeStep$var145 += 1) {
																if((timeStep$var122 == timeStep$var145)) {
																	if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																		if(!guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = true;
																			
																			// Processing sample task 170 of consumer random variable null.
																			{
																				if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																					// Set an accumulator to sum the probabilities for each possible configuration of
																					// inputs.
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					
																					// Set an accumulator to record the consumer distributions not seen. Initially set
																					// to 1 as seen values will be deducted from this value.
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																						// the output of Sample task 136.
																						for(int var58 = 0; var58 < noStates; var58 += 1) {
																							if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																								if((var58 == traceTempVariable$currentState$72_1)) {
																									if(fixedFlag$sample117) {
																										for(int index$sample$137_1 = 0; index$sample$137_1 < noSamples; index$sample$137_1 += 1) {
																											if((index$sample$137_1 == index$sample$72_2)) {
																												if((0 == timeStep$var145)) {
																													if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																														for(int var74 = 0; var74 < noStates; var74 += 1) {
																															if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																																if((var74 == traceTempVariable$currentState$72_1)) {
																																	{
																																		{
																																			if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																																				double cv$temp$30$var157;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var157 = metric_mean[traceTempVariable$currentState$72_1];
																																					cv$temp$30$var157 = var157;
																																				}
																																				double cv$temp$31$var158;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var158 = metric_var[traceTempVariable$currentState$72_1];
																																					cv$temp$31$var158 = var158;
																																				}
																																				
																																				// Record the probability of sample task 170 generating output with current configuration.
																																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$30$var157) / Math.sqrt(cv$temp$31$var158))) - (0.5 * Math.log(cv$temp$31$var158)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$30$var157) / Math.sqrt(cv$temp$31$var158))) - (0.5 * Math.log(cv$temp$31$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					// If the second value is -infinity.
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$30$var157) / Math.sqrt(cv$temp$31$var158))) - (0.5 * Math.log(cv$temp$31$var158))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$30$var157) / Math.sqrt(cv$temp$31$var158))) - (0.5 * Math.log(cv$temp$31$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$30$var157) / Math.sqrt(cv$temp$31$var158))) - (0.5 * Math.log(cv$temp$31$var158)))));
																																				}
																																				
																																				// Recorded the probability of reaching sample task 170 with the current configuration.
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									} else {
																										for(int index$sample$138 = 0; index$sample$138 < noSamples; index$sample$138 += 1) {
																											if(true) {
																												// Enumerating the possible outputs of Categorical 110.
																												for(int index$sample117$139 = 0; index$sample117$139 < noStates; index$sample117$139 += 1) {
																													int distributionTempVariable$var111$141 = index$sample117$139;
																													
																													// Update the probability of sampling this value from the distribution value.
																													double cv$probabilitySample117Value140 = (1.0 * distribution$sample117[((index$sample$138 - 0) / 1)][index$sample117$139]);
																													int traceTempVariable$currentState$142_1 = distributionTempVariable$var111$141;
																													if((index$sample$138 == index$sample$72_2)) {
																														if((0 == timeStep$var145)) {
																															if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																																for(int var74 = 0; var74 < noStates; var74 += 1) {
																																	if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																																		if((var74 == traceTempVariable$currentState$142_1)) {
																																			{
																																				{
																																					if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																																						double cv$temp$32$var157;
																																						{
																																							// Constructing a random variable input for use later.
																																							double var157 = metric_mean[traceTempVariable$currentState$142_1];
																																							cv$temp$32$var157 = var157;
																																						}
																																						double cv$temp$33$var158;
																																						{
																																							// Constructing a random variable input for use later.
																																							double var158 = metric_var[traceTempVariable$currentState$142_1];
																																							cv$temp$33$var158 = var158;
																																						}
																																						
																																						// Record the probability of sample task 170 generating output with current configuration.
																																						if(((Math.log(cv$probabilitySample117Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$32$var157) / Math.sqrt(cv$temp$33$var158))) - (0.5 * Math.log(cv$temp$33$var158)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$32$var157) / Math.sqrt(cv$temp$33$var158))) - (0.5 * Math.log(cv$temp$33$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$32$var157) / Math.sqrt(cv$temp$33$var158))) - (0.5 * Math.log(cv$temp$33$var158))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$32$var157) / Math.sqrt(cv$temp$33$var158))) - (0.5 * Math.log(cv$temp$33$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$32$var157) / Math.sqrt(cv$temp$33$var158))) - (0.5 * Math.log(cv$temp$33$var158)))));
																																						}
																																						
																																						// Recorded the probability of reaching sample task 170 with the current configuration.
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value140);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						
																						// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																						// the output of Sample task 136.
																						for(int var58 = 0; var58 < noStates; var58 += 1) {
																							if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																								if((var58 == traceTempVariable$currentState$72_1)) {
																									int traceTempVariable$currentState$146_1 = cv$currentValue;
																									if((index$sample$23 == index$sample$72_2)) {
																										if((index$timeStep$22 == timeStep$var145)) {
																											if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$146_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																																		double cv$temp$34$var157;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var157 = metric_mean[traceTempVariable$currentState$146_1];
																																			cv$temp$34$var157 = var157;
																																		}
																																		double cv$temp$35$var158;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var158 = metric_var[traceTempVariable$currentState$146_1];
																																			cv$temp$35$var158 = var158;
																																		}
																																		
																																		// Record the probability of sample task 170 generating output with current configuration.
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$34$var157) / Math.sqrt(cv$temp$35$var158))) - (0.5 * Math.log(cv$temp$35$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$34$var157) / Math.sqrt(cv$temp$35$var158))) - (0.5 * Math.log(cv$temp$35$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$34$var157) / Math.sqrt(cv$temp$35$var158))) - (0.5 * Math.log(cv$temp$35$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$34$var157) / Math.sqrt(cv$temp$35$var158))) - (0.5 * Math.log(cv$temp$35$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$34$var157) / Math.sqrt(cv$temp$35$var158))) - (0.5 * Math.log(cv$temp$35$var158)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 170 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																									int traceTempVariable$currentState$147_1 = distributionTempVariable$var130$37;
																									if((index$sample$33 == index$sample$72_2)) {
																										if((index$timeStep$34 == timeStep$var145)) {
																											if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$147_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																																		double cv$temp$36$var157;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var157 = metric_mean[traceTempVariable$currentState$147_1];
																																			cv$temp$36$var157 = var157;
																																		}
																																		double cv$temp$37$var158;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var158 = metric_var[traceTempVariable$currentState$147_1];
																																			cv$temp$37$var158 = var158;
																																		}
																																		
																																		// Record the probability of sample task 170 generating output with current configuration.
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$36$var157) / Math.sqrt(cv$temp$37$var158))) - (0.5 * Math.log(cv$temp$37$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$36$var157) / Math.sqrt(cv$temp$37$var158))) - (0.5 * Math.log(cv$temp$37$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$36$var157) / Math.sqrt(cv$temp$37$var158))) - (0.5 * Math.log(cv$temp$37$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$36$var157) / Math.sqrt(cv$temp$37$var158))) - (0.5 * Math.log(cv$temp$37$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$36$var157) / Math.sqrt(cv$temp$37$var158))) - (0.5 * Math.log(cv$temp$37$var158)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 170 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																									for(int index$sample$148 = 0; index$sample$148 < noSamples; index$sample$148 += 1) {
																										for(int index$timeStep$149 = 1; index$timeStep$149 < length$metric[index$sample$148]; index$timeStep$149 += 1) {
																											if((!((index$sample$148 == index$sample$23) && (index$timeStep$149 == index$timeStep$22)) && !((index$sample$148 == index$sample$33) && (index$timeStep$149 == index$timeStep$34)))) {
																												// Enumerating the possible outputs of Categorical 129.
																												for(int index$sample136$150 = 0; index$sample136$150 < noStates; index$sample136$150 += 1) {
																													int distributionTempVariable$var130$152 = index$sample136$150;
																													
																													// Update the probability of sampling this value from the distribution value.
																													double cv$probabilitySample136Value151 = (1.0 * distribution$sample136[((index$sample$148 - 0) / 1)][((index$timeStep$149 - 1) / 1)][index$sample136$150]);
																													int traceTempVariable$currentState$153_1 = distributionTempVariable$var130$152;
																													if((index$sample$148 == index$sample$72_2)) {
																														if((index$timeStep$149 == timeStep$var145)) {
																															if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																																for(int var74 = 0; var74 < noStates; var74 += 1) {
																																	if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																																		if((var74 == traceTempVariable$currentState$153_1)) {
																																			{
																																				{
																																					if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																																						double cv$temp$38$var157;
																																						{
																																							// Constructing a random variable input for use later.
																																							double var157 = metric_mean[traceTempVariable$currentState$153_1];
																																							cv$temp$38$var157 = var157;
																																						}
																																						double cv$temp$39$var158;
																																						{
																																							// Constructing a random variable input for use later.
																																							double var158 = metric_var[traceTempVariable$currentState$153_1];
																																							cv$temp$39$var158 = var158;
																																						}
																																						
																																						// Record the probability of sample task 170 generating output with current configuration.
																																						if(((Math.log(cv$probabilitySample136Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$38$var157) / Math.sqrt(cv$temp$39$var158))) - (0.5 * Math.log(cv$temp$39$var158)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$38$var157) / Math.sqrt(cv$temp$39$var158))) - (0.5 * Math.log(cv$temp$39$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$38$var157) / Math.sqrt(cv$temp$39$var158))) - (0.5 * Math.log(cv$temp$39$var158))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$38$var157) / Math.sqrt(cv$temp$39$var158))) - (0.5 * Math.log(cv$temp$39$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$38$var157) / Math.sqrt(cv$temp$39$var158))) - (0.5 * Math.log(cv$temp$39$var158)))));
																																						}
																																						
																																						// Recorded the probability of reaching sample task 170 with the current configuration.
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value151);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
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
																		}
																	}
																}
															}
														}
													}
													int traceTempVariable$currentState$76_1 = cv$currentValue;
													for(int index$sample$76_2 = 0; index$sample$76_2 < noSamples; index$sample$76_2 += 1) {
														if((sample == index$sample$76_2)) {
															for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$76_2]; timeStep$var145 += 1) {
																if((timeStep$var122 == timeStep$var145)) {
																	if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																		if(!guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)]) {
																			// The body will execute, so should not be executed again
																			guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = true;
																			
																			// Processing sample task 170 of consumer random variable null.
																			{
																				if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																					// Set an accumulator to sum the probabilities for each possible configuration of
																					// inputs.
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					
																					// Set an accumulator to record the consumer distributions not seen. Initially set
																					// to 1 as seen values will be deducted from this value.
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																						// the output of Sample task 136.
																						if(fixedFlag$sample117) {
																							for(int index$sample$213_1 = 0; index$sample$213_1 < noSamples; index$sample$213_1 += 1) {
																								if((index$sample$213_1 == index$sample$76_2)) {
																									if((0 == timeStep$var145)) {
																										if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																											for(int var58 = 0; var58 < noStates; var58 += 1) {
																												if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																													if((var58 == traceTempVariable$currentState$76_1)) {
																														for(int var74 = 0; var74 < noStates; var74 += 1) {
																															if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																																if((var74 == traceTempVariable$currentState$76_1)) {
																																	{
																																		{
																																			if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																																				double cv$temp$62$var157;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var157 = metric_mean[traceTempVariable$currentState$76_1];
																																					cv$temp$62$var157 = var157;
																																				}
																																				double cv$temp$63$var158;
																																				{
																																					// Constructing a random variable input for use later.
																																					double var158 = metric_var[traceTempVariable$currentState$76_1];
																																					cv$temp$63$var158 = var158;
																																				}
																																				
																																				// Record the probability of sample task 170 generating output with current configuration.
																																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$62$var157) / Math.sqrt(cv$temp$63$var158))) - (0.5 * Math.log(cv$temp$63$var158)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$62$var157) / Math.sqrt(cv$temp$63$var158))) - (0.5 * Math.log(cv$temp$63$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					// If the second value is -infinity.
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$62$var157) / Math.sqrt(cv$temp$63$var158))) - (0.5 * Math.log(cv$temp$63$var158))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$62$var157) / Math.sqrt(cv$temp$63$var158))) - (0.5 * Math.log(cv$temp$63$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$62$var157) / Math.sqrt(cv$temp$63$var158))) - (0.5 * Math.log(cv$temp$63$var158)))));
																																				}
																																				
																																				// Recorded the probability of reaching sample task 170 with the current configuration.
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						} else {
																							for(int index$sample$214 = 0; index$sample$214 < noSamples; index$sample$214 += 1) {
																								if(true) {
																									// Enumerating the possible outputs of Categorical 110.
																									for(int index$sample117$215 = 0; index$sample117$215 < noStates; index$sample117$215 += 1) {
																										int distributionTempVariable$var111$217 = index$sample117$215;
																										
																										// Update the probability of sampling this value from the distribution value.
																										double cv$probabilitySample117Value216 = (1.0 * distribution$sample117[((index$sample$214 - 0) / 1)][index$sample117$215]);
																										int traceTempVariable$currentState$218_1 = distributionTempVariable$var111$217;
																										if((index$sample$214 == index$sample$76_2)) {
																											if((0 == timeStep$var145)) {
																												if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																													for(int var58 = 0; var58 < noStates; var58 += 1) {
																														if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																															if((var58 == traceTempVariable$currentState$218_1)) {
																																for(int var74 = 0; var74 < noStates; var74 += 1) {
																																	if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																																		if((var74 == traceTempVariable$currentState$218_1)) {
																																			{
																																				{
																																					if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																																						double cv$temp$64$var157;
																																						{
																																							// Constructing a random variable input for use later.
																																							double var157 = metric_mean[traceTempVariable$currentState$218_1];
																																							cv$temp$64$var157 = var157;
																																						}
																																						double cv$temp$65$var158;
																																						{
																																							// Constructing a random variable input for use later.
																																							double var158 = metric_var[traceTempVariable$currentState$218_1];
																																							cv$temp$65$var158 = var158;
																																						}
																																						
																																						// Record the probability of sample task 170 generating output with current configuration.
																																						if(((Math.log(cv$probabilitySample117Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$64$var157) / Math.sqrt(cv$temp$65$var158))) - (0.5 * Math.log(cv$temp$65$var158)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$64$var157) / Math.sqrt(cv$temp$65$var158))) - (0.5 * Math.log(cv$temp$65$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$64$var157) / Math.sqrt(cv$temp$65$var158))) - (0.5 * Math.log(cv$temp$65$var158))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$64$var157) / Math.sqrt(cv$temp$65$var158))) - (0.5 * Math.log(cv$temp$65$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$64$var157) / Math.sqrt(cv$temp$65$var158))) - (0.5 * Math.log(cv$temp$65$var158)))));
																																						}
																																						
																																						// Recorded the probability of reaching sample task 170 with the current configuration.
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value216);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						
																						// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																						// the output of Sample task 136.
																						int traceTempVariable$currentState$223_1 = cv$currentValue;
																						if((index$sample$23 == index$sample$76_2)) {
																							if((index$timeStep$22 == timeStep$var145)) {
																								if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																									for(int var58 = 0; var58 < noStates; var58 += 1) {
																										if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																											if((var58 == traceTempVariable$currentState$223_1)) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$223_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																																		double cv$temp$66$var157;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var157 = metric_mean[traceTempVariable$currentState$223_1];
																																			cv$temp$66$var157 = var157;
																																		}
																																		double cv$temp$67$var158;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var158 = metric_var[traceTempVariable$currentState$223_1];
																																			cv$temp$67$var158 = var158;
																																		}
																																		
																																		// Record the probability of sample task 170 generating output with current configuration.
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$66$var157) / Math.sqrt(cv$temp$67$var158))) - (0.5 * Math.log(cv$temp$67$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$66$var157) / Math.sqrt(cv$temp$67$var158))) - (0.5 * Math.log(cv$temp$67$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$66$var157) / Math.sqrt(cv$temp$67$var158))) - (0.5 * Math.log(cv$temp$67$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$66$var157) / Math.sqrt(cv$temp$67$var158))) - (0.5 * Math.log(cv$temp$67$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$66$var157) / Math.sqrt(cv$temp$67$var158))) - (0.5 * Math.log(cv$temp$67$var158)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 170 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						int traceTempVariable$currentState$224_1 = distributionTempVariable$var130$37;
																						if((index$sample$33 == index$sample$76_2)) {
																							if((index$timeStep$34 == timeStep$var145)) {
																								if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																									for(int var58 = 0; var58 < noStates; var58 += 1) {
																										if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																											if((var58 == traceTempVariable$currentState$224_1)) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$224_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																																		double cv$temp$68$var157;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var157 = metric_mean[traceTempVariable$currentState$224_1];
																																			cv$temp$68$var157 = var157;
																																		}
																																		double cv$temp$69$var158;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var158 = metric_var[traceTempVariable$currentState$224_1];
																																			cv$temp$69$var158 = var158;
																																		}
																																		
																																		// Record the probability of sample task 170 generating output with current configuration.
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$68$var157) / Math.sqrt(cv$temp$69$var158))) - (0.5 * Math.log(cv$temp$69$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$68$var157) / Math.sqrt(cv$temp$69$var158))) - (0.5 * Math.log(cv$temp$69$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$68$var157) / Math.sqrt(cv$temp$69$var158))) - (0.5 * Math.log(cv$temp$69$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$68$var157) / Math.sqrt(cv$temp$69$var158))) - (0.5 * Math.log(cv$temp$69$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$68$var157) / Math.sqrt(cv$temp$69$var158))) - (0.5 * Math.log(cv$temp$69$var158)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 170 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						for(int index$sample$225 = 0; index$sample$225 < noSamples; index$sample$225 += 1) {
																							for(int index$timeStep$226 = 1; index$timeStep$226 < length$metric[index$sample$225]; index$timeStep$226 += 1) {
																								if((!((index$sample$225 == index$sample$23) && (index$timeStep$226 == index$timeStep$22)) && !((index$sample$225 == index$sample$33) && (index$timeStep$226 == index$timeStep$34)))) {
																									// Enumerating the possible outputs of Categorical 129.
																									for(int index$sample136$227 = 0; index$sample136$227 < noStates; index$sample136$227 += 1) {
																										int distributionTempVariable$var130$229 = index$sample136$227;
																										
																										// Update the probability of sampling this value from the distribution value.
																										double cv$probabilitySample136Value228 = (1.0 * distribution$sample136[((index$sample$225 - 0) / 1)][((index$timeStep$226 - 1) / 1)][index$sample136$227]);
																										int traceTempVariable$currentState$230_1 = distributionTempVariable$var130$229;
																										if((index$sample$225 == index$sample$76_2)) {
																											if((index$timeStep$226 == timeStep$var145)) {
																												if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																													for(int var58 = 0; var58 < noStates; var58 += 1) {
																														if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																															if((var58 == traceTempVariable$currentState$230_1)) {
																																for(int var74 = 0; var74 < noStates; var74 += 1) {
																																	if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																																		if((var74 == traceTempVariable$currentState$230_1)) {
																																			{
																																				{
																																					if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																																						double cv$temp$70$var157;
																																						{
																																							// Constructing a random variable input for use later.
																																							double var157 = metric_mean[traceTempVariable$currentState$230_1];
																																							cv$temp$70$var157 = var157;
																																						}
																																						double cv$temp$71$var158;
																																						{
																																							// Constructing a random variable input for use later.
																																							double var158 = metric_var[traceTempVariable$currentState$230_1];
																																							cv$temp$71$var158 = var158;
																																						}
																																						
																																						// Record the probability of sample task 170 generating output with current configuration.
																																						if(((Math.log(cv$probabilitySample136Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$70$var157) / Math.sqrt(cv$temp$71$var158))) - (0.5 * Math.log(cv$temp$71$var158)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$70$var157) / Math.sqrt(cv$temp$71$var158))) - (0.5 * Math.log(cv$temp$71$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							// If the second value is -infinity.
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$70$var157) / Math.sqrt(cv$temp$71$var158))) - (0.5 * Math.log(cv$temp$71$var158))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$70$var157) / Math.sqrt(cv$temp$71$var158))) - (0.5 * Math.log(cv$temp$71$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$70$var157) / Math.sqrt(cv$temp$71$var158))) - (0.5 * Math.log(cv$temp$71$var158)))));
																																						}
																																						
																																						// Recorded the probability of reaching sample task 170 with the current configuration.
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value228);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
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
																		}
																	}
																}
															}
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
						}
					}
				}
			}
			
			// Processing random variable 129.
			{
				// Looking for a path between Sample 136 and consumer Categorical 129.
				{
					int traceTempVariable$var127$269_1 = cv$currentValue;
					for(int index$sample$269_2 = 0; index$sample$269_2 < noSamples; index$sample$269_2 += 1) {
						if((sample == index$sample$269_2)) {
							for(int index$timeStep$269_3 = 1; index$timeStep$269_3 < length$metric[index$sample$269_2]; index$timeStep$269_3 += 1) {
								if((timeStep$var122 == (index$timeStep$269_3 - 1))) {
									// Processing sample task 136 of consumer random variable null.
									{
										// Copy of index so that its values can be safely substituted
										int index$timeStep$271 = index$timeStep$269_3;
										
										// Copy of index so that its values can be safely substituted
										int index$sample$272 = index$sample$269_2;
										
										// A local array to hold the accumulated distributions of the sample tasks for each
										// configuration of distributions.
										double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var129;
										
										// Zero all the elements in the distribution accumulator
										for(int cv$i = 0; cv$i < noStates; cv$i += 1)
											cv$accumulatedConsumerDistributions[cv$i] = 0.0;
										
										// Zero an accumulator to track the probabilities reached.
										double cv$reachedDistributionProbability = 0.0;
										
										// Enumerating the possible arguments for the variable Categorical 129 which is consuming
										// the output of Sample task 136.
										for(int var39 = 0; var39 < noStates; var39 += 1) {
											if((var39 == traceTempVariable$var127$269_1)) {
												{
													// Declare and zero an accumulator for tracking the reached source probability space.
													double scopeVariable$reachedSourceProbability = 0.0;
													
													// Enumerating the possible arguments for Categorical 129.
													if(fixedFlag$sample117) {
														for(int index$sample$274_1 = 0; index$sample$274_1 < noSamples; index$sample$274_1 += 1) {
															if((index$sample$274_1 == sample)) {
																if((0 == (timeStep$var122 - 1))) {
																	for(int index$var39$280_1 = 0; index$var39$280_1 < noStates; index$var39$280_1 += 1) {
																		if((index$var39$280_1 == st[sample][(timeStep$var122 - 1)]))
																			// Add the probability of this argument configuration.
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																}
															}
														}
													} else {
														for(int index$sample$275 = 0; index$sample$275 < noSamples; index$sample$275 += 1) {
															if(true) {
																// Enumerating the possible outputs of Categorical 110.
																for(int index$sample117$276 = 0; index$sample117$276 < noStates; index$sample117$276 += 1) {
																	int distributionTempVariable$var111$278 = index$sample117$276;
																	
																	// Update the probability of sampling this value from the distribution value.
																	double cv$probabilitySample117Value277 = (1.0 * distribution$sample117[((index$sample$275 - 0) / 1)][index$sample117$276]);
																	int traceTempVariable$var127$279_1 = distributionTempVariable$var111$278;
																	if((index$sample$275 == sample)) {
																		if((0 == (timeStep$var122 - 1))) {
																			for(int index$var39$281_1 = 0; index$var39$281_1 < noStates; index$var39$281_1 += 1) {
																				if((index$var39$281_1 == traceTempVariable$var127$279_1))
																					// Add the probability of this argument configuration.
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample117Value277);
																			}
																		}
																	}
																}
															}
														}
													}
													
													// Enumerating the possible arguments for Categorical 129.
													int traceTempVariable$var127$282_1 = cv$currentValue;
													if((index$sample$23 == sample)) {
														if((index$timeStep$22 == (timeStep$var122 - 1))) {
															for(int index$var39$289_1 = 0; index$var39$289_1 < noStates; index$var39$289_1 += 1) {
																if((index$var39$289_1 == traceTempVariable$var127$282_1))
																	// Add the probability of this argument configuration.
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
														}
													}
													for(int index$sample$283 = 0; index$sample$283 < noSamples; index$sample$283 += 1) {
														for(int index$timeStep$284 = 1; index$timeStep$284 < length$metric[index$sample$283]; index$timeStep$284 += 1) {
															if((!((index$sample$283 == index$sample$23) && (index$timeStep$284 == index$timeStep$22)) && !((index$sample$283 == index$sample$272) && (index$timeStep$284 == index$timeStep$271)))) {
																// Enumerating the possible outputs of Categorical 129.
																for(int index$sample136$285 = 0; index$sample136$285 < noStates; index$sample136$285 += 1) {
																	int distributionTempVariable$var130$287 = index$sample136$285;
																	
																	// Update the probability of sampling this value from the distribution value.
																	double cv$probabilitySample136Value286 = (1.0 * distribution$sample136[((index$sample$283 - 0) / 1)][((index$timeStep$284 - 1) / 1)][index$sample136$285]);
																	int traceTempVariable$var127$288_1 = distributionTempVariable$var130$287;
																	if((index$sample$283 == sample)) {
																		if((index$timeStep$284 == (timeStep$var122 - 1))) {
																			for(int index$var39$290_1 = 0; index$var39$290_1 < noStates; index$var39$290_1 += 1) {
																				if((index$var39$290_1 == traceTempVariable$var127$288_1))
																					// Add the probability of this argument configuration.
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample136Value286);
																			}
																		}
																	}
																}
															}
														}
													}
													double[] cv$temp$72$var128;
													{
														// Constructing a random variable input for use later.
														double[] var128 = m[traceTempVariable$var127$269_1];
														cv$temp$72$var128 = var128;
													}
													
													// The probability of reaching the consumer with this set of consumer arguments
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													
													// Record the reached distribution.
													cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
													
													// Add the current distribution to the distribution accumulator.
													DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$72$var128);
												}
											}
										}
										
										// A local copy of the samples' distribution.
										double[] cv$sampleDistribution = distribution$sample136[((index$sample$269_2 - 0) / 1)][((index$timeStep$269_3 - 1) / 1)];
										
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
										cv$accumulatedDistributionProbabilities = (cv$accumulatedDistributionProbabilities + Math.log(((cv$overlap * cv$reachedDistributionProbability) + (1.0 - Math.min(cv$reachedDistributionProbability, 1.0)))));
									}
								}
							}
						}
					}
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample136[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)];
		
		// The sum of all the probabilities in log space
		double cv$logSum = 0.0;
		
		// Sum all the values
		{
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
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
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
	private final void sample158(int sample, int timeStep$var145) {
		// A guard to record if the variable is observed
		boolean cv$varObserved = false;
		
		// Look for a valid path to an observed variable
		{
			// Record that this sample is observed
			cv$varObserved = true;
		}
		if(!cv$varObserved) {
			// Calculate the number of states to evaluate.
			int cv$noStates = 0;
			
			// Exploring all the possible state counts for random variable 149.
			// 
			// Enumerating the possible arguments for Bernoulli 149.
			if(fixedFlag$sample117) {
				for(int index$sample$2_1 = 0; index$sample$2_1 < noSamples; index$sample$2_1 += 1) {
					if((index$sample$2_1 == sample)) {
						if((0 == timeStep$var145)) {
							for(int var90 = 0; var90 < noStates; var90 += 1) {
								if((var90 == st[sample][timeStep$var145]))
									// variable marginalization
									cv$noStates = Math.max(cv$noStates, 2);
							}
						}
					}
				}
			} else {
				for(int index$sample$3 = 0; index$sample$3 < noSamples; index$sample$3 += 1) {
					if(true) {
						// Enumerating the possible outputs of Categorical 110.
						for(int index$sample117$4 = 0; index$sample117$4 < noStates; index$sample117$4 += 1) {
							int distributionTempVariable$var111$6 = index$sample117$4;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample117Value5 = (1.0 * distribution$sample117[((index$sample$3 - 0) / 1)][index$sample117$4]);
							int traceTempVariable$currentState$7_1 = distributionTempVariable$var111$6;
							if((index$sample$3 == sample)) {
								if((0 == timeStep$var145)) {
									for(int var90 = 0; var90 < noStates; var90 += 1) {
										if((var90 == traceTempVariable$currentState$7_1))
											// variable marginalization
											cv$noStates = Math.max(cv$noStates, 2);
									}
								}
							}
						}
					}
				}
			}
			
			// Enumerating the possible arguments for Bernoulli 149.
			if(fixedFlag$sample136) {
				for(int index$sample$10_1 = 0; index$sample$10_1 < noSamples; index$sample$10_1 += 1) {
					for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$10_1]; timeStep$var122 += 1) {
						if((index$sample$10_1 == sample)) {
							if((timeStep$var122 == timeStep$var145)) {
								for(int var90 = 0; var90 < noStates; var90 += 1) {
									if((var90 == st[sample][timeStep$var145]))
										// variable marginalization
										cv$noStates = Math.max(cv$noStates, 2);
								}
							}
						}
					}
				}
			} else {
				for(int index$sample$11 = 0; index$sample$11 < noSamples; index$sample$11 += 1) {
					for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$11]; timeStep$var122 += 1) {
						if(true) {
							// Enumerating the possible outputs of Categorical 129.
							for(int index$sample136$13 = 0; index$sample136$13 < noStates; index$sample136$13 += 1) {
								int distributionTempVariable$var130$15 = index$sample136$13;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample136Value14 = (1.0 * distribution$sample136[((index$sample$11 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$13]);
								int traceTempVariable$currentState$16_1 = distributionTempVariable$var130$15;
								if((index$sample$11 == sample)) {
									if((timeStep$var122 == timeStep$var145)) {
										for(int var90 = 0; var90 < noStates; var90 += 1) {
											if((var90 == traceTempVariable$currentState$16_1))
												// variable marginalization
												cv$noStates = Math.max(cv$noStates, 2);
										}
									}
								}
							}
						}
					}
				}
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var150$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
				// Exploring all the possible distribution values for random variable 149 creating
				// sample task 158.
				// Initialize the summed probabilities to 0.
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				
				// Initialize a counter to track the reached distributions.
				double cv$reachedDistributionSourceRV = 0.0;
				
				// Initialize a log space accumulator to take the product of all the distribution
				// probabilities.
				double cv$accumulatedDistributionProbabilities = 0.0;
				
				// The value currently being tested
				boolean cv$currentValue;
				
				// Value of the variable at this index
				cv$currentValue = (cv$valuePos == 1);
				
				// Write out the value of the sample to a temporary variable prior to updating the
				// intermediate variables.
				boolean var150 = cv$currentValue;
				boolean[] metric_valid_1d = metric_valid_g[sample];
				metric_valid_1d[timeStep$var145] = cv$currentValue;
				
				// Guards to ensure that metric_g is only updated when there is a valid path.
				// 
				// Looking for a path between Sample 158 and consumer double[][] 162.
				{
					for(int index$timeStep$19_1 = 0; index$timeStep$19_1 < length$metric[sample]; index$timeStep$19_1 += 1) {
						if((timeStep$var145 == index$timeStep$19_1)) {
							{
								double[] metric_1d = metric_g[sample];
							}
						}
					}
				}
				
				// Enumerating the possible arguments for Bernoulli 149.
				if(fixedFlag$sample117) {
					for(int index$sample$20_1 = 0; index$sample$20_1 < noSamples; index$sample$20_1 += 1) {
						if((index$sample$20_1 == sample)) {
							if((0 == timeStep$var145)) {
								for(int var90 = 0; var90 < noStates; var90 += 1) {
									if((var90 == st[sample][timeStep$var145])) {
										// Record the reached probability density.
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
										double cv$temp$0$var148;
										{
											// Constructing a random variable input for use later.
											double var148 = metric_valid_bias[st[sample][timeStep$var145]];
											cv$temp$0$var148 = var148;
										}
										
										// An accumulator to allow the value for each distribution to be constructed before
										// it is added to the index probabilities.
										double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$currentValue, cv$temp$0$var148));
										
										// Processing conditional point171.
										{
											// Looking for a path between Sample 158 and consumer double[] 161.
											{
												for(int index$timeStep$37_1 = 0; index$timeStep$37_1 < length$metric[sample]; index$timeStep$37_1 += 1) {
													if((timeStep$var145 == index$timeStep$37_1)) {
														if(metric_valid_g[sample][index$timeStep$37_1]) {
															{
																// Set an accumulator to sum the probabilities for each possible configuration of
																// inputs.
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																
																// Set an accumulator to record the consumer distributions not seen. Initially set
																// to 1 as seen values will be deducted from this value.
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																
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
					}
				} else {
					for(int index$sample$21 = 0; index$sample$21 < noSamples; index$sample$21 += 1) {
						if(true) {
							// Enumerating the possible outputs of Categorical 110.
							for(int index$sample117$22 = 0; index$sample117$22 < noStates; index$sample117$22 += 1) {
								int distributionTempVariable$var111$24 = index$sample117$22;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample117Value23 = (1.0 * distribution$sample117[((index$sample$21 - 0) / 1)][index$sample117$22]);
								int traceTempVariable$currentState$25_1 = distributionTempVariable$var111$24;
								if((index$sample$21 == sample)) {
									if((0 == timeStep$var145)) {
										for(int var90 = 0; var90 < noStates; var90 += 1) {
											if((var90 == traceTempVariable$currentState$25_1)) {
												// Record the reached probability density.
												cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample117Value23);
												double cv$temp$1$var148;
												{
													// Constructing a random variable input for use later.
													double var148 = metric_valid_bias[traceTempVariable$currentState$25_1];
													cv$temp$1$var148 = var148;
												}
												
												// An accumulator to allow the value for each distribution to be constructed before
												// it is added to the index probabilities.
												double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample117Value23) + DistributionSampling.logProbabilityBernoulli(cv$currentValue, cv$temp$1$var148));
												
												// Processing conditional point171.
												{
													// Looking for a path between Sample 158 and consumer double[] 161.
													{
														for(int index$timeStep$38_1 = 0; index$timeStep$38_1 < length$metric[sample]; index$timeStep$38_1 += 1) {
															if((timeStep$var145 == index$timeStep$38_1)) {
																if(metric_valid_g[sample][index$timeStep$38_1]) {
																	{
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		
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
							}
						}
					}
				}
				
				// Enumerating the possible arguments for Bernoulli 149.
				if(fixedFlag$sample136) {
					for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
						for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$28_1]; timeStep$var122 += 1) {
							if((index$sample$28_1 == sample)) {
								if((timeStep$var122 == timeStep$var145)) {
									for(int var90 = 0; var90 < noStates; var90 += 1) {
										if((var90 == st[sample][timeStep$var145])) {
											// Record the reached probability density.
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
											double cv$temp$2$var148;
											{
												// Constructing a random variable input for use later.
												double var148 = metric_valid_bias[st[sample][timeStep$var145]];
												cv$temp$2$var148 = var148;
											}
											
											// An accumulator to allow the value for each distribution to be constructed before
											// it is added to the index probabilities.
											double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$currentValue, cv$temp$2$var148));
											
											// Processing conditional point171.
											{
												// Looking for a path between Sample 158 and consumer double[] 161.
												{
													for(int index$timeStep$39_1 = 0; index$timeStep$39_1 < length$metric[sample]; index$timeStep$39_1 += 1) {
														if((timeStep$var145 == index$timeStep$39_1)) {
															if(metric_valid_g[sample][index$timeStep$39_1]) {
																{
																	// Set an accumulator to sum the probabilities for each possible configuration of
																	// inputs.
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	
																	// Set an accumulator to record the consumer distributions not seen. Initially set
																	// to 1 as seen values will be deducted from this value.
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	
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
						}
					}
				} else {
					for(int index$sample$29 = 0; index$sample$29 < noSamples; index$sample$29 += 1) {
						for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$29]; timeStep$var122 += 1) {
							if(true) {
								// Enumerating the possible outputs of Categorical 129.
								for(int index$sample136$31 = 0; index$sample136$31 < noStates; index$sample136$31 += 1) {
									int distributionTempVariable$var130$33 = index$sample136$31;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample136Value32 = (1.0 * distribution$sample136[((index$sample$29 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$31]);
									int traceTempVariable$currentState$34_1 = distributionTempVariable$var130$33;
									if((index$sample$29 == sample)) {
										if((timeStep$var122 == timeStep$var145)) {
											for(int var90 = 0; var90 < noStates; var90 += 1) {
												if((var90 == traceTempVariable$currentState$34_1)) {
													// Record the reached probability density.
													cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample136Value32);
													double cv$temp$3$var148;
													{
														// Constructing a random variable input for use later.
														double var148 = metric_valid_bias[traceTempVariable$currentState$34_1];
														cv$temp$3$var148 = var148;
													}
													
													// An accumulator to allow the value for each distribution to be constructed before
													// it is added to the index probabilities.
													double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample136Value32) + DistributionSampling.logProbabilityBernoulli(cv$currentValue, cv$temp$3$var148));
													
													// Processing conditional point171.
													{
														// Looking for a path between Sample 158 and consumer double[] 161.
														{
															for(int index$timeStep$40_1 = 0; index$timeStep$40_1 < length$metric[sample]; index$timeStep$40_1 += 1) {
																if((timeStep$var145 == index$timeStep$40_1)) {
																	if(metric_valid_g[sample][index$timeStep$40_1]) {
																		{
																			// Set an accumulator to sum the probabilities for each possible configuration of
																			// inputs.
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			
																			// Set an accumulator to record the consumer distributions not seen. Initially set
																			// to 1 as seen values will be deducted from this value.
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			
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
								}
							}
						}
					}
				}
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			
			// The sum of all the probabilities in log space
			double cv$logSum = 0.0;
			
			// Sum all the values
			{
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
					cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
				}
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$noStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			boolean var150 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal) == 1);
			boolean[] metric_valid_1d = metric_valid_g[sample];
			metric_valid_1d[timeStep$var145] = var150;
			
			// Guards to ensure that metric_g is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 158 and consumer double[][] 162.
			{
				for(int index$timeStep$41_1 = 0; index$timeStep$41_1 < length$metric[sample]; index$timeStep$41_1 += 1) {
					if((timeStep$var145 == index$timeStep$41_1)) {
						{
							double[] metric_1d = metric_g[sample];
						}
					}
				}
			}
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 30 drawn from Dirichlet 26. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample30() {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = initialStateDistribution;
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var27$countGlobal;
		
		// Get the length of the array
		int cv$arrayLength = noStates;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 110.
			{
				{
					for(int sample = 0; sample < noSamples; sample += 1) {
						if(fixedFlag$sample117) {
							// Processing sample task 117 of consumer random variable null.
							{
								// Copy of index so that its values can be safely substituted
								int index$sample$3 = sample;
								{
									{
										{
											{
												// Increment the sample counter with the value sampled by sample task 117 of random
												// variable var110
												cv$countLocal[st[sample][0]] = (cv$countLocal[st[sample][0]] + 1.0);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Processing random variable 110.
		{
			{
				for(int sample = 0; sample < noSamples; sample += 1) {
					if(!fixedFlag$sample117) {
						// Processing sample task 117 of consumer random variable null.
						{
							// Copy of index so that its values can be safely substituted
							int index$sample$7 = sample;
							{
								{
									// Declare and zero an accumulator for tracking the reached source probability space.
									double scopeVariable$reachedSourceProbability = 0.0;
									{
										// Add the probability of this argument configuration.
										scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
									}
									
									// The probability of reaching the consumer with this set of consumer arguments
									double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
									
									// Merge the distribution probabilities into the count
									for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
										cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample117[((sample - 0) / 1)][cv$loopIndex] * cv$distributionProbability));
								}
							}
						}
					}
				}
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 43 drawn from Dirichlet 28. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void sample43(int var39) {
		// A reference local to the function for the sample variable.
		double[] cv$targetLocal = m[var39];
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var40$countGlobal;
		
		// Get the length of the array
		int cv$arrayLength = noStates;
		
		// Initialize the array values to 0.
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			// Processing random variable 129.
			{
				// Looking for a path between Sample 43 and consumer Categorical 129.
				{
					for(int sample = 0; sample < noSamples; sample += 1) {
						for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
							if(fixedFlag$sample117) {
								for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
									if((index$sample$3_1 == sample)) {
										if((0 == (timeStep$var122 - 1))) {
											if((var39 == st[sample][(timeStep$var122 - 1)])) {
												if(fixedFlag$sample136) {
													// Processing sample task 136 of consumer random variable null.
													{
														// Copy of index so that its values can be safely substituted
														int index$timeStep$23 = timeStep$var122;
														
														// Copy of index so that its values can be safely substituted
														int index$sample$24 = sample;
														{
															{
																{
																	{
																		// Increment the sample counter with the value sampled by sample task 136 of random
																		// variable var129
																		cv$countLocal[st[sample][timeStep$var122]] = (cv$countLocal[st[sample][timeStep$var122]] + 1.0);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							} else {
								for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 110.
										for(int index$sample117$5 = 0; index$sample117$5 < noStates; index$sample117$5 += 1) {
											int distributionTempVariable$var111$7 = index$sample117$5;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample117Value6 = (1.0 * distribution$sample117[((index$sample$4 - 0) / 1)][index$sample117$5]);
											int traceTempVariable$var127$8_1 = distributionTempVariable$var111$7;
											if((index$sample$4 == sample)) {
												if((0 == (timeStep$var122 - 1))) {
													if((var39 == traceTempVariable$var127$8_1)) {
														if(fixedFlag$sample136) {
															// Processing sample task 136 of consumer random variable null.
															{
																// Copy of index so that its values can be safely substituted
																int index$timeStep$26 = timeStep$var122;
																
																// Copy of index so that its values can be safely substituted
																int index$sample$27 = sample;
																{
																	{
																		{
																			{
																				// Increment the sample counter with the value sampled by sample task 136 of random
																				// variable var129
																				cv$countLocal[st[sample][timeStep$var122]] = (cv$countLocal[st[sample][timeStep$var122]] + cv$probabilitySample117Value6);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int sample = 0; sample < noSamples; sample += 1) {
						for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
							if(fixedFlag$sample136) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1]; index$timeStep$13_2 += 1) {
										if((index$sample$13_1 == sample)) {
											if((index$timeStep$13_2 == (timeStep$var122 - 1))) {
												if((var39 == st[sample][(timeStep$var122 - 1)])) {
													if(fixedFlag$sample136) {
														// Processing sample task 136 of consumer random variable null.
														{
															// Copy of index so that its values can be safely substituted
															int index$timeStep$29 = timeStep$var122;
															
															// Copy of index so that its values can be safely substituted
															int index$sample$30 = sample;
															{
																{
																	{
																		{
																			// Increment the sample counter with the value sampled by sample task 136 of random
																			// variable var129
																			cv$countLocal[st[sample][timeStep$var122]] = (cv$countLocal[st[sample][timeStep$var122]] + 1.0);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							} else {
								for(int index$sample$14 = 0; index$sample$14 < noSamples; index$sample$14 += 1) {
									for(int index$timeStep$15 = 1; index$timeStep$15 < length$metric[index$sample$14]; index$timeStep$15 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 129.
											for(int index$sample136$16 = 0; index$sample136$16 < noStates; index$sample136$16 += 1) {
												int distributionTempVariable$var130$18 = index$sample136$16;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample136Value17 = (1.0 * distribution$sample136[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample136$16]);
												int traceTempVariable$var127$19_1 = distributionTempVariable$var130$18;
												if((index$sample$14 == sample)) {
													if((index$timeStep$15 == (timeStep$var122 - 1))) {
														if((var39 == traceTempVariable$var127$19_1)) {
															if(fixedFlag$sample136) {
																// Processing sample task 136 of consumer random variable null.
																{
																	// Copy of index so that its values can be safely substituted
																	int index$timeStep$32 = timeStep$var122;
																	
																	// Copy of index so that its values can be safely substituted
																	int index$sample$33 = sample;
																	{
																		{
																			{
																				{
																					// Increment the sample counter with the value sampled by sample task 136 of random
																					// variable var129
																					cv$countLocal[st[sample][timeStep$var122]] = (cv$countLocal[st[sample][timeStep$var122]] + cv$probabilitySample136Value17);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Processing random variable 129.
		{
			// Looking for a path between Sample 43 and consumer Categorical 129.
			{
				for(int sample = 0; sample < noSamples; sample += 1) {
					for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
						if(fixedFlag$sample117) {
							for(int index$sample$40_1 = 0; index$sample$40_1 < noSamples; index$sample$40_1 += 1) {
								if((index$sample$40_1 == sample)) {
									if((0 == (timeStep$var122 - 1))) {
										if((var39 == st[sample][(timeStep$var122 - 1)])) {
											if(!fixedFlag$sample136) {
												// Processing sample task 136 of consumer random variable null.
												{
													// Copy of index so that its values can be safely substituted
													int index$timeStep$60 = timeStep$var122;
													
													// Copy of index so that its values can be safely substituted
													int index$sample$61 = sample;
													{
														{
															// Declare and zero an accumulator for tracking the reached source probability space.
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																// Add the probability of this argument configuration.
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															
															// The probability of reaching the consumer with this set of consumer arguments
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
															
															// Merge the distribution probabilities into the count
															for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample136[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
														}
													}
												}
											}
										}
									}
								}
							}
						} else {
							for(int index$sample$41 = 0; index$sample$41 < noSamples; index$sample$41 += 1) {
								if(true) {
									// Enumerating the possible outputs of Categorical 110.
									for(int index$sample117$42 = 0; index$sample117$42 < noStates; index$sample117$42 += 1) {
										int distributionTempVariable$var111$44 = index$sample117$42;
										
										// Update the probability of sampling this value from the distribution value.
										double cv$probabilitySample117Value43 = (1.0 * distribution$sample117[((index$sample$41 - 0) / 1)][index$sample117$42]);
										int traceTempVariable$var127$45_1 = distributionTempVariable$var111$44;
										if((index$sample$41 == sample)) {
											if((0 == (timeStep$var122 - 1))) {
												if((var39 == traceTempVariable$var127$45_1)) {
													if(!fixedFlag$sample136) {
														// Processing sample task 136 of consumer random variable null.
														{
															// Copy of index so that its values can be safely substituted
															int index$timeStep$63 = timeStep$var122;
															
															// Copy of index so that its values can be safely substituted
															int index$sample$64 = sample;
															{
																{
																	// Declare and zero an accumulator for tracking the reached source probability space.
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		// Add the probability of this argument configuration.
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																	
																	// The probability of reaching the consumer with this set of consumer arguments
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample117Value43);
																	
																	// Merge the distribution probabilities into the count
																	for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample136[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				for(int sample = 0; sample < noSamples; sample += 1) {
					for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
						if(fixedFlag$sample136) {
							for(int index$sample$50_1 = 0; index$sample$50_1 < noSamples; index$sample$50_1 += 1) {
								for(int index$timeStep$50_2 = 1; index$timeStep$50_2 < length$metric[index$sample$50_1]; index$timeStep$50_2 += 1) {
									if((index$sample$50_1 == sample)) {
										if((index$timeStep$50_2 == (timeStep$var122 - 1))) {
											if((var39 == st[sample][(timeStep$var122 - 1)])) {
												if(!fixedFlag$sample136) {
													// Processing sample task 136 of consumer random variable null.
													{
														// Copy of index so that its values can be safely substituted
														int index$timeStep$66 = timeStep$var122;
														
														// Copy of index so that its values can be safely substituted
														int index$sample$67 = sample;
														{
															{
																// Declare and zero an accumulator for tracking the reached source probability space.
																double scopeVariable$reachedSourceProbability = 0.0;
																{
																	// Add the probability of this argument configuration.
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
																
																// The probability of reaching the consumer with this set of consumer arguments
																double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																
																// Merge the distribution probabilities into the count
																for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																	cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample136[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
															}
														}
													}
												}
											}
										}
									}
								}
							}
						} else {
							for(int index$sample$51 = 0; index$sample$51 < noSamples; index$sample$51 += 1) {
								for(int index$timeStep$52 = 1; index$timeStep$52 < length$metric[index$sample$51]; index$timeStep$52 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 129.
										for(int index$sample136$53 = 0; index$sample136$53 < noStates; index$sample136$53 += 1) {
											int distributionTempVariable$var130$55 = index$sample136$53;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample136Value54 = (1.0 * distribution$sample136[((index$sample$51 - 0) / 1)][((index$timeStep$52 - 1) / 1)][index$sample136$53]);
											int traceTempVariable$var127$56_1 = distributionTempVariable$var130$55;
											if((index$sample$51 == sample)) {
												if((index$timeStep$52 == (timeStep$var122 - 1))) {
													if((var39 == traceTempVariable$var127$56_1)) {
														if(!fixedFlag$sample136) {
															// Processing sample task 136 of consumer random variable null.
															{
																// Copy of index so that its values can be safely substituted
																int index$timeStep$69 = timeStep$var122;
																
																// Copy of index so that its values can be safely substituted
																int index$sample$70 = sample;
																{
																	{
																		// Declare and zero an accumulator for tracking the reached source probability space.
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			// Add the probability of this argument configuration.
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		
																		// The probability of reaching the consumer with this set of consumer arguments
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample136Value54);
																		
																		// Merge the distribution probabilities into the count
																		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																			cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample136[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Calculate the new sample value
		// 
		// Calculate a new sample value and write it into cv$targetLocal.
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 63 drawn from Uniform 47. Inference was performed using Metropolis-Hastings.
	private final void sample63(int var58) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		{
			// Metropolis-Hastings
			cv$noStates = Math.max(cv$noStates, 2);
		}
		
		// The original value of the sample
		double cv$originalValue = metric_mean[var58];
		
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability = 0.0;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		
		// Ensure the variance is at least 0.01
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		
		// The probability of the random variable generating the new sample value.
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// The value currently being tested
			double cv$currentValue;
			if((cv$valuePos == 0))
				// Set the current value to the current state of the tree.
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				
				// Update Sample and intermediate values
				{
					// Write out the value of the sample to a temporary variable prior to updating the
					// intermediate variables.
					double var59 = cv$proposedValue;
					metric_mean[var58] = cv$currentValue;
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var45;
				{
					cv$temp$0$var45 = 0.0;
				}
				double cv$temp$1$var46;
				{
					cv$temp$1$var46 = 100.0;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((cv$temp$0$var45 <= cv$currentValue) && (cv$currentValue <= cv$temp$1$var46))?(-Math.log((cv$temp$1$var46 - cv$temp$0$var45))):Double.NEGATIVE_INFINITY));
				
				// Processing random variable 159.
				{
					// Looking for a path between Sample 63 and consumer Gaussian 159.
					{
						for(int sample = 0; sample < noSamples; sample += 1) {
							for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
								if(fixedFlag$sample117) {
									for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
										if((index$sample$3_1 == sample)) {
											if((0 == timeStep$var145)) {
												if(metric_valid_g[sample][timeStep$var145]) {
													double traceTempVariable$var157$9_1 = cv$currentValue;
													if(metric_valid_g[sample][timeStep$var145]) {
														if((var58 == st[sample][timeStep$var145])) {
															// Processing sample task 170 of consumer random variable null.
															{
																if(metric_valid_g[sample][timeStep$var145]) {
																	// Set an accumulator to sum the probabilities for each possible configuration of
																	// inputs.
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	
																	// Set an accumulator to record the consumer distributions not seen. Initially set
																	// to 1 as seen values will be deducted from this value.
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																		// the output of Sample task 63.
																		for(int index$sample$26_1 = 0; index$sample$26_1 < noSamples; index$sample$26_1 += 1) {
																			if((index$sample$26_1 == sample)) {
																				if((0 == timeStep$var145)) {
																					if(metric_valid_g[sample][timeStep$var145]) {
																						for(int var74 = 0; var74 < noStates; var74 += 1) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								if((var74 == st[sample][timeStep$var145])) {
																									{
																										{
																											if(metric_valid_g[sample][timeStep$var145]) {
																												double cv$temp$2$var157;
																												{
																													// Constructing a random variable input for use later.
																													double var157 = traceTempVariable$var157$9_1;
																													cv$temp$2$var157 = var157;
																												}
																												double cv$temp$3$var158;
																												{
																													// Constructing a random variable input for use later.
																													double var158 = metric_var[st[sample][timeStep$var145]];
																													cv$temp$3$var158 = var158;
																												}
																												
																												// Record the probability of sample task 170 generating output with current configuration.
																												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$2$var157) / Math.sqrt(cv$temp$3$var158))) - (0.5 * Math.log(cv$temp$3$var158)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$2$var157) / Math.sqrt(cv$temp$3$var158))) - (0.5 * Math.log(cv$temp$3$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$2$var157) / Math.sqrt(cv$temp$3$var158))) - (0.5 * Math.log(cv$temp$3$var158))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$2$var157) / Math.sqrt(cv$temp$3$var158))) - (0.5 * Math.log(cv$temp$3$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$2$var157) / Math.sqrt(cv$temp$3$var158))) - (0.5 * Math.log(cv$temp$3$var158)))));
																												}
																												
																												// Recorded the probability of reaching sample task 170 with the current configuration.
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		
																		// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																		// the output of Sample task 63.
																		if(fixedFlag$sample136) {
																			for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$28_1]; timeStep$var122 += 1) {
																					if((index$sample$28_1 == sample)) {
																						if((timeStep$var122 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var74 == st[sample][timeStep$var145])) {
																											{
																												{
																													if(metric_valid_g[sample][timeStep$var145]) {
																														double cv$temp$4$var157;
																														{
																															// Constructing a random variable input for use later.
																															double var157 = traceTempVariable$var157$9_1;
																															cv$temp$4$var157 = var157;
																														}
																														double cv$temp$5$var158;
																														{
																															// Constructing a random variable input for use later.
																															double var158 = metric_var[st[sample][timeStep$var145]];
																															cv$temp$5$var158 = var158;
																														}
																														
																														// Record the probability of sample task 170 generating output with current configuration.
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$4$var157) / Math.sqrt(cv$temp$5$var158))) - (0.5 * Math.log(cv$temp$5$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$4$var157) / Math.sqrt(cv$temp$5$var158))) - (0.5 * Math.log(cv$temp$5$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$4$var157) / Math.sqrt(cv$temp$5$var158))) - (0.5 * Math.log(cv$temp$5$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$4$var157) / Math.sqrt(cv$temp$5$var158))) - (0.5 * Math.log(cv$temp$5$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$4$var157) / Math.sqrt(cv$temp$5$var158))) - (0.5 * Math.log(cv$temp$5$var158)))));
																														}
																														
																														// Recorded the probability of reaching sample task 170 with the current configuration.
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		} else {
																			for(int index$sample$29 = 0; index$sample$29 < noSamples; index$sample$29 += 1) {
																				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$29]; timeStep$var122 += 1) {
																					if(true) {
																						// Enumerating the possible outputs of Categorical 129.
																						for(int index$sample136$31 = 0; index$sample136$31 < noStates; index$sample136$31 += 1) {
																							int distributionTempVariable$var130$33 = index$sample136$31;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample136Value32 = (1.0 * distribution$sample136[((index$sample$29 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$31]);
																							int traceTempVariable$currentState$34_1 = distributionTempVariable$var130$33;
																							if((index$sample$29 == sample)) {
																								if((timeStep$var122 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$34_1)) {
																													{
																														{
																															if(metric_valid_g[sample][timeStep$var145]) {
																																double cv$temp$6$var157;
																																{
																																	// Constructing a random variable input for use later.
																																	double var157 = traceTempVariable$var157$9_1;
																																	cv$temp$6$var157 = var157;
																																}
																																double cv$temp$7$var158;
																																{
																																	// Constructing a random variable input for use later.
																																	double var158 = metric_var[traceTempVariable$currentState$34_1];
																																	cv$temp$7$var158 = var158;
																																}
																																
																																// Record the probability of sample task 170 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$6$var157) / Math.sqrt(cv$temp$7$var158))) - (0.5 * Math.log(cv$temp$7$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$6$var157) / Math.sqrt(cv$temp$7$var158))) - (0.5 * Math.log(cv$temp$7$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$6$var157) / Math.sqrt(cv$temp$7$var158))) - (0.5 * Math.log(cv$temp$7$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$6$var157) / Math.sqrt(cv$temp$7$var158))) - (0.5 * Math.log(cv$temp$7$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$6$var157) / Math.sqrt(cv$temp$7$var158))) - (0.5 * Math.log(cv$temp$7$var158)))));
																																}
																																
																																// Recorded the probability of reaching sample task 170 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value32);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
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
														}
													}
												}
											}
										}
									}
								} else {
									for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 110.
											for(int index$sample117$5 = 0; index$sample117$5 < noStates; index$sample117$5 += 1) {
												int distributionTempVariable$var111$7 = index$sample117$5;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample117Value6 = (1.0 * distribution$sample117[((index$sample$4 - 0) / 1)][index$sample117$5]);
												int traceTempVariable$currentState$8_1 = distributionTempVariable$var111$7;
												if((index$sample$4 == sample)) {
													if((0 == timeStep$var145)) {
														if(metric_valid_g[sample][timeStep$var145]) {
															double traceTempVariable$var157$10_1 = cv$currentValue;
															if(metric_valid_g[sample][timeStep$var145]) {
																if((var58 == traceTempVariable$currentState$8_1)) {
																	// Processing sample task 170 of consumer random variable null.
																	{
																		if(metric_valid_g[sample][timeStep$var145]) {
																			// Set an accumulator to sum the probabilities for each possible configuration of
																			// inputs.
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			
																			// Set an accumulator to record the consumer distributions not seen. Initially set
																			// to 1 as seen values will be deducted from this value.
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																				// the output of Sample task 63.
																				int traceTempVariable$currentState$37_1 = distributionTempVariable$var111$7;
																				if((index$sample$4 == sample)) {
																					if((0 == timeStep$var145)) {
																						if(metric_valid_g[sample][timeStep$var145]) {
																							for(int var74 = 0; var74 < noStates; var74 += 1) {
																								if(metric_valid_g[sample][timeStep$var145]) {
																									if((var74 == traceTempVariable$currentState$37_1)) {
																										{
																											{
																												if(metric_valid_g[sample][timeStep$var145]) {
																													double cv$temp$8$var157;
																													{
																														// Constructing a random variable input for use later.
																														double var157 = traceTempVariable$var157$10_1;
																														cv$temp$8$var157 = var157;
																													}
																													double cv$temp$9$var158;
																													{
																														// Constructing a random variable input for use later.
																														double var158 = metric_var[traceTempVariable$currentState$37_1];
																														cv$temp$9$var158 = var158;
																													}
																													
																													// Record the probability of sample task 170 generating output with current configuration.
																													if(((Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														// If the second value is -infinity.
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))));
																													}
																													
																													// Recorded the probability of reaching sample task 170 with the current configuration.
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value6);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				for(int index$sample$38 = 0; index$sample$38 < noSamples; index$sample$38 += 1) {
																					if(!(index$sample$38 == index$sample$4)) {
																						// Enumerating the possible outputs of Categorical 110.
																						for(int index$sample117$39 = 0; index$sample117$39 < noStates; index$sample117$39 += 1) {
																							int distributionTempVariable$var111$41 = index$sample117$39;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample117Value40 = (cv$probabilitySample117Value6 * distribution$sample117[((index$sample$38 - 0) / 1)][index$sample117$39]);
																							int traceTempVariable$currentState$42_1 = distributionTempVariable$var111$41;
																							if((index$sample$38 == sample)) {
																								if((0 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$42_1)) {
																													{
																														{
																															if(metric_valid_g[sample][timeStep$var145]) {
																																double cv$temp$10$var157;
																																{
																																	// Constructing a random variable input for use later.
																																	double var157 = traceTempVariable$var157$10_1;
																																	cv$temp$10$var157 = var157;
																																}
																																double cv$temp$11$var158;
																																{
																																	// Constructing a random variable input for use later.
																																	double var158 = metric_var[traceTempVariable$currentState$42_1];
																																	cv$temp$11$var158 = var158;
																																}
																																
																																// Record the probability of sample task 170 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample117Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))));
																																}
																																
																																// Recorded the probability of reaching sample task 170 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value40);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				
																				// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																				// the output of Sample task 63.
																				if(fixedFlag$sample136) {
																					for(int index$sample$45_1 = 0; index$sample$45_1 < noSamples; index$sample$45_1 += 1) {
																						for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$45_1]; timeStep$var122 += 1) {
																							if((index$sample$45_1 == sample)) {
																								if((timeStep$var122 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$8_1)) {
																													{
																														{
																															if(metric_valid_g[sample][timeStep$var145]) {
																																double cv$temp$12$var157;
																																{
																																	// Constructing a random variable input for use later.
																																	double var157 = traceTempVariable$var157$10_1;
																																	cv$temp$12$var157 = var157;
																																}
																																double cv$temp$13$var158;
																																{
																																	// Constructing a random variable input for use later.
																																	double var158 = metric_var[traceTempVariable$currentState$8_1];
																																	cv$temp$13$var158 = var158;
																																}
																																
																																// Record the probability of sample task 170 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))));
																																}
																																
																																// Recorded the probability of reaching sample task 170 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value6);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				} else {
																					for(int index$sample$46 = 0; index$sample$46 < noSamples; index$sample$46 += 1) {
																						for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$46]; timeStep$var122 += 1) {
																							if(true) {
																								// Enumerating the possible outputs of Categorical 129.
																								for(int index$sample136$48 = 0; index$sample136$48 < noStates; index$sample136$48 += 1) {
																									int distributionTempVariable$var130$50 = index$sample136$48;
																									
																									// Update the probability of sampling this value from the distribution value.
																									double cv$probabilitySample136Value49 = (cv$probabilitySample117Value6 * distribution$sample136[((index$sample$46 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$48]);
																									int traceTempVariable$currentState$51_1 = distributionTempVariable$var130$50;
																									if((index$sample$46 == sample)) {
																										if((timeStep$var122 == timeStep$var145)) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[sample][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$51_1)) {
																															{
																																{
																																	if(metric_valid_g[sample][timeStep$var145]) {
																																		double cv$temp$14$var157;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var157 = traceTempVariable$var157$10_1;
																																			cv$temp$14$var157 = var157;
																																		}
																																		double cv$temp$15$var158;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var158 = metric_var[traceTempVariable$currentState$51_1];
																																			cv$temp$15$var158 = var158;
																																		}
																																		
																																		// Record the probability of sample task 170 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample136Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 170 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value49);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
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
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						for(int sample = 0; sample < noSamples; sample += 1) {
							for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
								if(fixedFlag$sample136) {
									for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
										for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$13_1]; timeStep$var122 += 1) {
											if((index$sample$13_1 == sample)) {
												if((timeStep$var122 == timeStep$var145)) {
													if(metric_valid_g[sample][timeStep$var145]) {
														double traceTempVariable$var157$20_1 = cv$currentValue;
														if(metric_valid_g[sample][timeStep$var145]) {
															if((var58 == st[sample][timeStep$var145])) {
																// Processing sample task 170 of consumer random variable null.
																{
																	if(metric_valid_g[sample][timeStep$var145]) {
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																			// the output of Sample task 63.
																			if(fixedFlag$sample117) {
																				for(int index$sample$54_1 = 0; index$sample$54_1 < noSamples; index$sample$54_1 += 1) {
																					if((index$sample$54_1 == sample)) {
																						if((0 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var74 == st[sample][timeStep$var145])) {
																											{
																												{
																													if(metric_valid_g[sample][timeStep$var145]) {
																														double cv$temp$16$var157;
																														{
																															// Constructing a random variable input for use later.
																															double var157 = traceTempVariable$var157$20_1;
																															cv$temp$16$var157 = var157;
																														}
																														double cv$temp$17$var158;
																														{
																															// Constructing a random variable input for use later.
																															double var158 = metric_var[st[sample][timeStep$var145]];
																															cv$temp$17$var158 = var158;
																														}
																														
																														// Record the probability of sample task 170 generating output with current configuration.
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))));
																														}
																														
																														// Recorded the probability of reaching sample task 170 with the current configuration.
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			} else {
																				for(int index$sample$55 = 0; index$sample$55 < noSamples; index$sample$55 += 1) {
																					if(true) {
																						// Enumerating the possible outputs of Categorical 110.
																						for(int index$sample117$56 = 0; index$sample117$56 < noStates; index$sample117$56 += 1) {
																							int distributionTempVariable$var111$58 = index$sample117$56;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample117Value57 = (1.0 * distribution$sample117[((index$sample$55 - 0) / 1)][index$sample117$56]);
																							int traceTempVariable$currentState$59_1 = distributionTempVariable$var111$58;
																							if((index$sample$55 == sample)) {
																								if((0 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$59_1)) {
																													{
																														{
																															if(metric_valid_g[sample][timeStep$var145]) {
																																double cv$temp$18$var157;
																																{
																																	// Constructing a random variable input for use later.
																																	double var157 = traceTempVariable$var157$20_1;
																																	cv$temp$18$var157 = var157;
																																}
																																double cv$temp$19$var158;
																																{
																																	// Constructing a random variable input for use later.
																																	double var158 = metric_var[traceTempVariable$currentState$59_1];
																																	cv$temp$19$var158 = var158;
																																}
																																
																																// Record the probability of sample task 170 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample117Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))));
																																}
																																
																																// Recorded the probability of reaching sample task 170 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value57);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			
																			// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																			// the output of Sample task 63.
																			for(int index$sample$62_1 = 0; index$sample$62_1 < noSamples; index$sample$62_1 += 1) {
																				for(int index$timeStep$62_2 = 1; index$timeStep$62_2 < length$metric[index$sample$62_1]; index$timeStep$62_2 += 1) {
																					if((index$sample$62_1 == sample)) {
																						if((index$timeStep$62_2 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var74 == st[sample][timeStep$var145])) {
																											{
																												{
																													if(metric_valid_g[sample][timeStep$var145]) {
																														double cv$temp$20$var157;
																														{
																															// Constructing a random variable input for use later.
																															double var157 = traceTempVariable$var157$20_1;
																															cv$temp$20$var157 = var157;
																														}
																														double cv$temp$21$var158;
																														{
																															// Constructing a random variable input for use later.
																															double var158 = metric_var[st[sample][timeStep$var145]];
																															cv$temp$21$var158 = var158;
																														}
																														
																														// Record the probability of sample task 170 generating output with current configuration.
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))));
																														}
																														
																														// Recorded the probability of reaching sample task 170 with the current configuration.
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
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
															}
														}
													}
												}
											}
										}
									}
								} else {
									for(int index$sample$14 = 0; index$sample$14 < noSamples; index$sample$14 += 1) {
										for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$14]; timeStep$var122 += 1) {
											if(true) {
												// Enumerating the possible outputs of Categorical 129.
												for(int index$sample136$16 = 0; index$sample136$16 < noStates; index$sample136$16 += 1) {
													int distributionTempVariable$var130$18 = index$sample136$16;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample136Value17 = (1.0 * distribution$sample136[((index$sample$14 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$16]);
													int traceTempVariable$currentState$19_1 = distributionTempVariable$var130$18;
													if((index$sample$14 == sample)) {
														if((timeStep$var122 == timeStep$var145)) {
															if(metric_valid_g[sample][timeStep$var145]) {
																double traceTempVariable$var157$21_1 = cv$currentValue;
																if(metric_valid_g[sample][timeStep$var145]) {
																	if((var58 == traceTempVariable$currentState$19_1)) {
																		// Processing sample task 170 of consumer random variable null.
																		{
																			if(metric_valid_g[sample][timeStep$var145]) {
																				// Set an accumulator to sum the probabilities for each possible configuration of
																				// inputs.
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				
																				// Set an accumulator to record the consumer distributions not seen. Initially set
																				// to 1 as seen values will be deducted from this value.
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																					// the output of Sample task 63.
																					if(fixedFlag$sample117) {
																						for(int index$sample$64_1 = 0; index$sample$64_1 < noSamples; index$sample$64_1 += 1) {
																							if((index$sample$64_1 == sample)) {
																								if((0 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$19_1)) {
																													{
																														{
																															if(metric_valid_g[sample][timeStep$var145]) {
																																double cv$temp$22$var157;
																																{
																																	// Constructing a random variable input for use later.
																																	double var157 = traceTempVariable$var157$21_1;
																																	cv$temp$22$var157 = var157;
																																}
																																double cv$temp$23$var158;
																																{
																																	// Constructing a random variable input for use later.
																																	double var158 = metric_var[traceTempVariable$currentState$19_1];
																																	cv$temp$23$var158 = var158;
																																}
																																
																																// Record the probability of sample task 170 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))));
																																}
																																
																																// Recorded the probability of reaching sample task 170 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value17);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					} else {
																						for(int index$sample$65 = 0; index$sample$65 < noSamples; index$sample$65 += 1) {
																							if(true) {
																								// Enumerating the possible outputs of Categorical 110.
																								for(int index$sample117$66 = 0; index$sample117$66 < noStates; index$sample117$66 += 1) {
																									int distributionTempVariable$var111$68 = index$sample117$66;
																									
																									// Update the probability of sampling this value from the distribution value.
																									double cv$probabilitySample117Value67 = (cv$probabilitySample136Value17 * distribution$sample117[((index$sample$65 - 0) / 1)][index$sample117$66]);
																									int traceTempVariable$currentState$69_1 = distributionTempVariable$var111$68;
																									if((index$sample$65 == sample)) {
																										if((0 == timeStep$var145)) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[sample][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$69_1)) {
																															{
																																{
																																	if(metric_valid_g[sample][timeStep$var145]) {
																																		double cv$temp$24$var157;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var157 = traceTempVariable$var157$21_1;
																																			cv$temp$24$var157 = var157;
																																		}
																																		double cv$temp$25$var158;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var158 = metric_var[traceTempVariable$currentState$69_1];
																																			cv$temp$25$var158 = var158;
																																		}
																																		
																																		// Record the probability of sample task 170 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample117Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 170 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value67);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					
																					// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																					// the output of Sample task 63.
																					int traceTempVariable$currentState$72_1 = distributionTempVariable$var130$18;
																					if((index$sample$14 == sample)) {
																						if((timeStep$var122 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$72_1)) {
																											{
																												{
																													if(metric_valid_g[sample][timeStep$var145]) {
																														double cv$temp$26$var157;
																														{
																															// Constructing a random variable input for use later.
																															double var157 = traceTempVariable$var157$21_1;
																															cv$temp$26$var157 = var157;
																														}
																														double cv$temp$27$var158;
																														{
																															// Constructing a random variable input for use later.
																															double var158 = metric_var[traceTempVariable$currentState$72_1];
																															cv$temp$27$var158 = var158;
																														}
																														
																														// Record the probability of sample task 170 generating output with current configuration.
																														if(((Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))));
																														}
																														
																														// Recorded the probability of reaching sample task 170 with the current configuration.
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value17);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$73 = 0; index$sample$73 < noSamples; index$sample$73 += 1) {
																						for(int index$timeStep$74 = 1; index$timeStep$74 < length$metric[index$sample$73]; index$timeStep$74 += 1) {
																							if(!((index$sample$73 == index$sample$14) && (index$timeStep$74 == timeStep$var122))) {
																								// Enumerating the possible outputs of Categorical 129.
																								for(int index$sample136$75 = 0; index$sample136$75 < noStates; index$sample136$75 += 1) {
																									int distributionTempVariable$var130$77 = index$sample136$75;
																									
																									// Update the probability of sampling this value from the distribution value.
																									double cv$probabilitySample136Value76 = (cv$probabilitySample136Value17 * distribution$sample136[((index$sample$73 - 0) / 1)][((index$timeStep$74 - 1) / 1)][index$sample136$75]);
																									int traceTempVariable$currentState$78_1 = distributionTempVariable$var130$77;
																									if((index$sample$73 == sample)) {
																										if((index$timeStep$74 == timeStep$var145)) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[sample][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$78_1)) {
																															{
																																{
																																	if(metric_valid_g[sample][timeStep$var145]) {
																																		double cv$temp$28$var157;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var157 = traceTempVariable$var157$21_1;
																																			cv$temp$28$var157 = var157;
																																		}
																																		double cv$temp$29$var158;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var158 = metric_var[traceTempVariable$currentState$78_1];
																																			cv$temp$29$var158 = var158;
																																		}
																																		
																																		// Record the probability of sample task 170 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample136Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 170 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value76);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
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
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
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
			
			// Save the probability of the original value.
			if((cv$valuePos == 0))
				cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			
			// Save the probability of the proposed value.
			else
				cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		
		// The probability ration for the proposed value and the current value.
		double cv$ratio = (cv$proposedProbability - cv$originalProbability);
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
			// If it is not revert the changes.
			// 
			// Set the sample value
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			double var59 = cv$originalValue;
			metric_mean[var58] = var59;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 79 drawn from InverseGamma 63. Inference was performed using Metropolis-Hastings.
	private final void sample79(int var74) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		{
			// Metropolis-Hastings
			cv$noStates = Math.max(cv$noStates, 2);
		}
		
		// The original value of the sample
		double cv$originalValue = metric_var[var74];
		
		// The probability of the random variable generating the originally sampled value
		double cv$originalProbability = 0.0;
		
		// Calculate a proposed variance.
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		
		// Ensure the variance is at least 0.01
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		
		// The proposed new value for the sample
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		
		// The probability of the random variable generating the new sample value.
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// The value currently being tested
			double cv$currentValue;
			if((cv$valuePos == 0))
				// Set the current value to the current state of the tree.
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				
				// Update Sample and intermediate values
				{
					// Write out the value of the sample to a temporary variable prior to updating the
					// intermediate variables.
					double var75 = cv$proposedValue;
					metric_var[var74] = cv$currentValue;
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var61;
				{
					cv$temp$0$var61 = 1.0;
				}
				double cv$temp$1$var62;
				{
					cv$temp$1$var62 = 1.0;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, cv$temp$0$var61, cv$temp$1$var62));
				
				// Processing random variable 159.
				{
					// Looking for a path between Sample 79 and consumer Gaussian 159.
					{
						for(int sample = 0; sample < noSamples; sample += 1) {
							for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
								if(fixedFlag$sample117) {
									for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
										if((index$sample$3_1 == sample)) {
											if((0 == timeStep$var145)) {
												if(metric_valid_g[sample][timeStep$var145]) {
													double traceTempVariable$var158$9_1 = cv$currentValue;
													if(metric_valid_g[sample][timeStep$var145]) {
														if((var74 == st[sample][timeStep$var145])) {
															// Processing sample task 170 of consumer random variable null.
															{
																if(metric_valid_g[sample][timeStep$var145]) {
																	// Set an accumulator to sum the probabilities for each possible configuration of
																	// inputs.
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	
																	// Set an accumulator to record the consumer distributions not seen. Initially set
																	// to 1 as seen values will be deducted from this value.
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																		// the output of Sample task 79.
																		for(int index$sample$26_1 = 0; index$sample$26_1 < noSamples; index$sample$26_1 += 1) {
																			if((index$sample$26_1 == sample)) {
																				if((0 == timeStep$var145)) {
																					if(metric_valid_g[sample][timeStep$var145]) {
																						for(int var58 = 0; var58 < noStates; var58 += 1) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								if((var58 == st[sample][timeStep$var145])) {
																									{
																										{
																											if(metric_valid_g[sample][timeStep$var145]) {
																												double cv$temp$2$var157;
																												{
																													// Constructing a random variable input for use later.
																													double var157 = metric_mean[st[sample][timeStep$var145]];
																													cv$temp$2$var157 = var157;
																												}
																												double cv$temp$3$var158;
																												{
																													// Constructing a random variable input for use later.
																													double var158 = traceTempVariable$var158$9_1;
																													cv$temp$3$var158 = var158;
																												}
																												
																												// Record the probability of sample task 170 generating output with current configuration.
																												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$2$var157) / Math.sqrt(cv$temp$3$var158))) - (0.5 * Math.log(cv$temp$3$var158)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$2$var157) / Math.sqrt(cv$temp$3$var158))) - (0.5 * Math.log(cv$temp$3$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													// If the second value is -infinity.
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$2$var157) / Math.sqrt(cv$temp$3$var158))) - (0.5 * Math.log(cv$temp$3$var158))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$2$var157) / Math.sqrt(cv$temp$3$var158))) - (0.5 * Math.log(cv$temp$3$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$2$var157) / Math.sqrt(cv$temp$3$var158))) - (0.5 * Math.log(cv$temp$3$var158)))));
																												}
																												
																												// Recorded the probability of reaching sample task 170 with the current configuration.
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		
																		// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																		// the output of Sample task 79.
																		if(fixedFlag$sample136) {
																			for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$28_1]; timeStep$var122 += 1) {
																					if((index$sample$28_1 == sample)) {
																						if((timeStep$var122 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var58 = 0; var58 < noStates; var58 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var58 == st[sample][timeStep$var145])) {
																											{
																												{
																													if(metric_valid_g[sample][timeStep$var145]) {
																														double cv$temp$4$var157;
																														{
																															// Constructing a random variable input for use later.
																															double var157 = metric_mean[st[sample][timeStep$var145]];
																															cv$temp$4$var157 = var157;
																														}
																														double cv$temp$5$var158;
																														{
																															// Constructing a random variable input for use later.
																															double var158 = traceTempVariable$var158$9_1;
																															cv$temp$5$var158 = var158;
																														}
																														
																														// Record the probability of sample task 170 generating output with current configuration.
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$4$var157) / Math.sqrt(cv$temp$5$var158))) - (0.5 * Math.log(cv$temp$5$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$4$var157) / Math.sqrt(cv$temp$5$var158))) - (0.5 * Math.log(cv$temp$5$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$4$var157) / Math.sqrt(cv$temp$5$var158))) - (0.5 * Math.log(cv$temp$5$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$4$var157) / Math.sqrt(cv$temp$5$var158))) - (0.5 * Math.log(cv$temp$5$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$4$var157) / Math.sqrt(cv$temp$5$var158))) - (0.5 * Math.log(cv$temp$5$var158)))));
																														}
																														
																														// Recorded the probability of reaching sample task 170 with the current configuration.
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		} else {
																			for(int index$sample$29 = 0; index$sample$29 < noSamples; index$sample$29 += 1) {
																				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$29]; timeStep$var122 += 1) {
																					if(true) {
																						// Enumerating the possible outputs of Categorical 129.
																						for(int index$sample136$31 = 0; index$sample136$31 < noStates; index$sample136$31 += 1) {
																							int distributionTempVariable$var130$33 = index$sample136$31;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample136Value32 = (1.0 * distribution$sample136[((index$sample$29 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$31]);
																							int traceTempVariable$currentState$34_1 = distributionTempVariable$var130$33;
																							if((index$sample$29 == sample)) {
																								if((timeStep$var122 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var58 = 0; var58 < noStates; var58 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var58 == traceTempVariable$currentState$34_1)) {
																													{
																														{
																															if(metric_valid_g[sample][timeStep$var145]) {
																																double cv$temp$6$var157;
																																{
																																	// Constructing a random variable input for use later.
																																	double var157 = metric_mean[traceTempVariable$currentState$34_1];
																																	cv$temp$6$var157 = var157;
																																}
																																double cv$temp$7$var158;
																																{
																																	// Constructing a random variable input for use later.
																																	double var158 = traceTempVariable$var158$9_1;
																																	cv$temp$7$var158 = var158;
																																}
																																
																																// Record the probability of sample task 170 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$6$var157) / Math.sqrt(cv$temp$7$var158))) - (0.5 * Math.log(cv$temp$7$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$6$var157) / Math.sqrt(cv$temp$7$var158))) - (0.5 * Math.log(cv$temp$7$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$6$var157) / Math.sqrt(cv$temp$7$var158))) - (0.5 * Math.log(cv$temp$7$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$6$var157) / Math.sqrt(cv$temp$7$var158))) - (0.5 * Math.log(cv$temp$7$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$6$var157) / Math.sqrt(cv$temp$7$var158))) - (0.5 * Math.log(cv$temp$7$var158)))));
																																}
																																
																																// Recorded the probability of reaching sample task 170 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value32);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
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
														}
													}
												}
											}
										}
									}
								} else {
									for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 110.
											for(int index$sample117$5 = 0; index$sample117$5 < noStates; index$sample117$5 += 1) {
												int distributionTempVariable$var111$7 = index$sample117$5;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample117Value6 = (1.0 * distribution$sample117[((index$sample$4 - 0) / 1)][index$sample117$5]);
												int traceTempVariable$currentState$8_1 = distributionTempVariable$var111$7;
												if((index$sample$4 == sample)) {
													if((0 == timeStep$var145)) {
														if(metric_valid_g[sample][timeStep$var145]) {
															double traceTempVariable$var158$10_1 = cv$currentValue;
															if(metric_valid_g[sample][timeStep$var145]) {
																if((var74 == traceTempVariable$currentState$8_1)) {
																	// Processing sample task 170 of consumer random variable null.
																	{
																		if(metric_valid_g[sample][timeStep$var145]) {
																			// Set an accumulator to sum the probabilities for each possible configuration of
																			// inputs.
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			
																			// Set an accumulator to record the consumer distributions not seen. Initially set
																			// to 1 as seen values will be deducted from this value.
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																				// the output of Sample task 79.
																				int traceTempVariable$currentState$37_1 = distributionTempVariable$var111$7;
																				if((index$sample$4 == sample)) {
																					if((0 == timeStep$var145)) {
																						if(metric_valid_g[sample][timeStep$var145]) {
																							for(int var58 = 0; var58 < noStates; var58 += 1) {
																								if(metric_valid_g[sample][timeStep$var145]) {
																									if((var58 == traceTempVariable$currentState$37_1)) {
																										{
																											{
																												if(metric_valid_g[sample][timeStep$var145]) {
																													double cv$temp$8$var157;
																													{
																														// Constructing a random variable input for use later.
																														double var157 = metric_mean[traceTempVariable$currentState$37_1];
																														cv$temp$8$var157 = var157;
																													}
																													double cv$temp$9$var158;
																													{
																														// Constructing a random variable input for use later.
																														double var158 = traceTempVariable$var158$10_1;
																														cv$temp$9$var158 = var158;
																													}
																													
																													// Record the probability of sample task 170 generating output with current configuration.
																													if(((Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														// If the second value is -infinity.
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))));
																													}
																													
																													// Recorded the probability of reaching sample task 170 with the current configuration.
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value6);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				for(int index$sample$38 = 0; index$sample$38 < noSamples; index$sample$38 += 1) {
																					if(!(index$sample$38 == index$sample$4)) {
																						// Enumerating the possible outputs of Categorical 110.
																						for(int index$sample117$39 = 0; index$sample117$39 < noStates; index$sample117$39 += 1) {
																							int distributionTempVariable$var111$41 = index$sample117$39;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample117Value40 = (cv$probabilitySample117Value6 * distribution$sample117[((index$sample$38 - 0) / 1)][index$sample117$39]);
																							int traceTempVariable$currentState$42_1 = distributionTempVariable$var111$41;
																							if((index$sample$38 == sample)) {
																								if((0 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var58 = 0; var58 < noStates; var58 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var58 == traceTempVariable$currentState$42_1)) {
																													{
																														{
																															if(metric_valid_g[sample][timeStep$var145]) {
																																double cv$temp$10$var157;
																																{
																																	// Constructing a random variable input for use later.
																																	double var157 = metric_mean[traceTempVariable$currentState$42_1];
																																	cv$temp$10$var157 = var157;
																																}
																																double cv$temp$11$var158;
																																{
																																	// Constructing a random variable input for use later.
																																	double var158 = traceTempVariable$var158$10_1;
																																	cv$temp$11$var158 = var158;
																																}
																																
																																// Record the probability of sample task 170 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample117Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))));
																																}
																																
																																// Recorded the probability of reaching sample task 170 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value40);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				
																				// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																				// the output of Sample task 79.
																				if(fixedFlag$sample136) {
																					for(int index$sample$45_1 = 0; index$sample$45_1 < noSamples; index$sample$45_1 += 1) {
																						for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$45_1]; timeStep$var122 += 1) {
																							if((index$sample$45_1 == sample)) {
																								if((timeStep$var122 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var58 = 0; var58 < noStates; var58 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var58 == traceTempVariable$currentState$8_1)) {
																													{
																														{
																															if(metric_valid_g[sample][timeStep$var145]) {
																																double cv$temp$12$var157;
																																{
																																	// Constructing a random variable input for use later.
																																	double var157 = metric_mean[traceTempVariable$currentState$8_1];
																																	cv$temp$12$var157 = var157;
																																}
																																double cv$temp$13$var158;
																																{
																																	// Constructing a random variable input for use later.
																																	double var158 = traceTempVariable$var158$10_1;
																																	cv$temp$13$var158 = var158;
																																}
																																
																																// Record the probability of sample task 170 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))));
																																}
																																
																																// Recorded the probability of reaching sample task 170 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value6);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				} else {
																					for(int index$sample$46 = 0; index$sample$46 < noSamples; index$sample$46 += 1) {
																						for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$46]; timeStep$var122 += 1) {
																							if(true) {
																								// Enumerating the possible outputs of Categorical 129.
																								for(int index$sample136$48 = 0; index$sample136$48 < noStates; index$sample136$48 += 1) {
																									int distributionTempVariable$var130$50 = index$sample136$48;
																									
																									// Update the probability of sampling this value from the distribution value.
																									double cv$probabilitySample136Value49 = (cv$probabilitySample117Value6 * distribution$sample136[((index$sample$46 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$48]);
																									int traceTempVariable$currentState$51_1 = distributionTempVariable$var130$50;
																									if((index$sample$46 == sample)) {
																										if((timeStep$var122 == timeStep$var145)) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												for(int var58 = 0; var58 < noStates; var58 += 1) {
																													if(metric_valid_g[sample][timeStep$var145]) {
																														if((var58 == traceTempVariable$currentState$51_1)) {
																															{
																																{
																																	if(metric_valid_g[sample][timeStep$var145]) {
																																		double cv$temp$14$var157;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var157 = metric_mean[traceTempVariable$currentState$51_1];
																																			cv$temp$14$var157 = var157;
																																		}
																																		double cv$temp$15$var158;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var158 = traceTempVariable$var158$10_1;
																																			cv$temp$15$var158 = var158;
																																		}
																																		
																																		// Record the probability of sample task 170 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample136Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 170 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value49);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
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
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						for(int sample = 0; sample < noSamples; sample += 1) {
							for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
								if(fixedFlag$sample136) {
									for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
										for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$13_1]; timeStep$var122 += 1) {
											if((index$sample$13_1 == sample)) {
												if((timeStep$var122 == timeStep$var145)) {
													if(metric_valid_g[sample][timeStep$var145]) {
														double traceTempVariable$var158$20_1 = cv$currentValue;
														if(metric_valid_g[sample][timeStep$var145]) {
															if((var74 == st[sample][timeStep$var145])) {
																// Processing sample task 170 of consumer random variable null.
																{
																	if(metric_valid_g[sample][timeStep$var145]) {
																		// Set an accumulator to sum the probabilities for each possible configuration of
																		// inputs.
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		
																		// Set an accumulator to record the consumer distributions not seen. Initially set
																		// to 1 as seen values will be deducted from this value.
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																			// the output of Sample task 79.
																			if(fixedFlag$sample117) {
																				for(int index$sample$54_1 = 0; index$sample$54_1 < noSamples; index$sample$54_1 += 1) {
																					if((index$sample$54_1 == sample)) {
																						if((0 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var58 = 0; var58 < noStates; var58 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var58 == st[sample][timeStep$var145])) {
																											{
																												{
																													if(metric_valid_g[sample][timeStep$var145]) {
																														double cv$temp$16$var157;
																														{
																															// Constructing a random variable input for use later.
																															double var157 = metric_mean[st[sample][timeStep$var145]];
																															cv$temp$16$var157 = var157;
																														}
																														double cv$temp$17$var158;
																														{
																															// Constructing a random variable input for use later.
																															double var158 = traceTempVariable$var158$20_1;
																															cv$temp$17$var158 = var158;
																														}
																														
																														// Record the probability of sample task 170 generating output with current configuration.
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))));
																														}
																														
																														// Recorded the probability of reaching sample task 170 with the current configuration.
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			} else {
																				for(int index$sample$55 = 0; index$sample$55 < noSamples; index$sample$55 += 1) {
																					if(true) {
																						// Enumerating the possible outputs of Categorical 110.
																						for(int index$sample117$56 = 0; index$sample117$56 < noStates; index$sample117$56 += 1) {
																							int distributionTempVariable$var111$58 = index$sample117$56;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample117Value57 = (1.0 * distribution$sample117[((index$sample$55 - 0) / 1)][index$sample117$56]);
																							int traceTempVariable$currentState$59_1 = distributionTempVariable$var111$58;
																							if((index$sample$55 == sample)) {
																								if((0 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var58 = 0; var58 < noStates; var58 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var58 == traceTempVariable$currentState$59_1)) {
																													{
																														{
																															if(metric_valid_g[sample][timeStep$var145]) {
																																double cv$temp$18$var157;
																																{
																																	// Constructing a random variable input for use later.
																																	double var157 = metric_mean[traceTempVariable$currentState$59_1];
																																	cv$temp$18$var157 = var157;
																																}
																																double cv$temp$19$var158;
																																{
																																	// Constructing a random variable input for use later.
																																	double var158 = traceTempVariable$var158$20_1;
																																	cv$temp$19$var158 = var158;
																																}
																																
																																// Record the probability of sample task 170 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample117Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))));
																																}
																																
																																// Recorded the probability of reaching sample task 170 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value57);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			
																			// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																			// the output of Sample task 79.
																			for(int index$sample$62_1 = 0; index$sample$62_1 < noSamples; index$sample$62_1 += 1) {
																				for(int index$timeStep$62_2 = 1; index$timeStep$62_2 < length$metric[index$sample$62_1]; index$timeStep$62_2 += 1) {
																					if((index$sample$62_1 == sample)) {
																						if((index$timeStep$62_2 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var58 = 0; var58 < noStates; var58 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var58 == st[sample][timeStep$var145])) {
																											{
																												{
																													if(metric_valid_g[sample][timeStep$var145]) {
																														double cv$temp$20$var157;
																														{
																															// Constructing a random variable input for use later.
																															double var157 = metric_mean[st[sample][timeStep$var145]];
																															cv$temp$20$var157 = var157;
																														}
																														double cv$temp$21$var158;
																														{
																															// Constructing a random variable input for use later.
																															double var158 = traceTempVariable$var158$20_1;
																															cv$temp$21$var158 = var158;
																														}
																														
																														// Record the probability of sample task 170 generating output with current configuration.
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))));
																														}
																														
																														// Recorded the probability of reaching sample task 170 with the current configuration.
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
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
															}
														}
													}
												}
											}
										}
									}
								} else {
									for(int index$sample$14 = 0; index$sample$14 < noSamples; index$sample$14 += 1) {
										for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$14]; timeStep$var122 += 1) {
											if(true) {
												// Enumerating the possible outputs of Categorical 129.
												for(int index$sample136$16 = 0; index$sample136$16 < noStates; index$sample136$16 += 1) {
													int distributionTempVariable$var130$18 = index$sample136$16;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample136Value17 = (1.0 * distribution$sample136[((index$sample$14 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$16]);
													int traceTempVariable$currentState$19_1 = distributionTempVariable$var130$18;
													if((index$sample$14 == sample)) {
														if((timeStep$var122 == timeStep$var145)) {
															if(metric_valid_g[sample][timeStep$var145]) {
																double traceTempVariable$var158$21_1 = cv$currentValue;
																if(metric_valid_g[sample][timeStep$var145]) {
																	if((var74 == traceTempVariable$currentState$19_1)) {
																		// Processing sample task 170 of consumer random variable null.
																		{
																			if(metric_valid_g[sample][timeStep$var145]) {
																				// Set an accumulator to sum the probabilities for each possible configuration of
																				// inputs.
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				
																				// Set an accumulator to record the consumer distributions not seen. Initially set
																				// to 1 as seen values will be deducted from this value.
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																					// the output of Sample task 79.
																					if(fixedFlag$sample117) {
																						for(int index$sample$64_1 = 0; index$sample$64_1 < noSamples; index$sample$64_1 += 1) {
																							if((index$sample$64_1 == sample)) {
																								if((0 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var58 = 0; var58 < noStates; var58 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var58 == traceTempVariable$currentState$19_1)) {
																													{
																														{
																															if(metric_valid_g[sample][timeStep$var145]) {
																																double cv$temp$22$var157;
																																{
																																	// Constructing a random variable input for use later.
																																	double var157 = metric_mean[traceTempVariable$currentState$19_1];
																																	cv$temp$22$var157 = var157;
																																}
																																double cv$temp$23$var158;
																																{
																																	// Constructing a random variable input for use later.
																																	double var158 = traceTempVariable$var158$21_1;
																																	cv$temp$23$var158 = var158;
																																}
																																
																																// Record the probability of sample task 170 generating output with current configuration.
																																if(((Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	// If the second value is -infinity.
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))));
																																}
																																
																																// Recorded the probability of reaching sample task 170 with the current configuration.
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value17);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					} else {
																						for(int index$sample$65 = 0; index$sample$65 < noSamples; index$sample$65 += 1) {
																							if(true) {
																								// Enumerating the possible outputs of Categorical 110.
																								for(int index$sample117$66 = 0; index$sample117$66 < noStates; index$sample117$66 += 1) {
																									int distributionTempVariable$var111$68 = index$sample117$66;
																									
																									// Update the probability of sampling this value from the distribution value.
																									double cv$probabilitySample117Value67 = (cv$probabilitySample136Value17 * distribution$sample117[((index$sample$65 - 0) / 1)][index$sample117$66]);
																									int traceTempVariable$currentState$69_1 = distributionTempVariable$var111$68;
																									if((index$sample$65 == sample)) {
																										if((0 == timeStep$var145)) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												for(int var58 = 0; var58 < noStates; var58 += 1) {
																													if(metric_valid_g[sample][timeStep$var145]) {
																														if((var58 == traceTempVariable$currentState$69_1)) {
																															{
																																{
																																	if(metric_valid_g[sample][timeStep$var145]) {
																																		double cv$temp$24$var157;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var157 = metric_mean[traceTempVariable$currentState$69_1];
																																			cv$temp$24$var157 = var157;
																																		}
																																		double cv$temp$25$var158;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var158 = traceTempVariable$var158$21_1;
																																			cv$temp$25$var158 = var158;
																																		}
																																		
																																		// Record the probability of sample task 170 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample117Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 170 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value67);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					
																					// Enumerating the possible arguments for the variable Gaussian 159 which is consuming
																					// the output of Sample task 79.
																					int traceTempVariable$currentState$72_1 = distributionTempVariable$var130$18;
																					if((index$sample$14 == sample)) {
																						if((timeStep$var122 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var58 = 0; var58 < noStates; var58 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var58 == traceTempVariable$currentState$72_1)) {
																											{
																												{
																													if(metric_valid_g[sample][timeStep$var145]) {
																														double cv$temp$26$var157;
																														{
																															// Constructing a random variable input for use later.
																															double var157 = metric_mean[traceTempVariable$currentState$72_1];
																															cv$temp$26$var157 = var157;
																														}
																														double cv$temp$27$var158;
																														{
																															// Constructing a random variable input for use later.
																															double var158 = traceTempVariable$var158$21_1;
																															cv$temp$27$var158 = var158;
																														}
																														
																														// Record the probability of sample task 170 generating output with current configuration.
																														if(((Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															// If the second value is -infinity.
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))));
																														}
																														
																														// Recorded the probability of reaching sample task 170 with the current configuration.
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value17);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$73 = 0; index$sample$73 < noSamples; index$sample$73 += 1) {
																						for(int index$timeStep$74 = 1; index$timeStep$74 < length$metric[index$sample$73]; index$timeStep$74 += 1) {
																							if(!((index$sample$73 == index$sample$14) && (index$timeStep$74 == timeStep$var122))) {
																								// Enumerating the possible outputs of Categorical 129.
																								for(int index$sample136$75 = 0; index$sample136$75 < noStates; index$sample136$75 += 1) {
																									int distributionTempVariable$var130$77 = index$sample136$75;
																									
																									// Update the probability of sampling this value from the distribution value.
																									double cv$probabilitySample136Value76 = (cv$probabilitySample136Value17 * distribution$sample136[((index$sample$73 - 0) / 1)][((index$timeStep$74 - 1) / 1)][index$sample136$75]);
																									int traceTempVariable$currentState$78_1 = distributionTempVariable$var130$77;
																									if((index$sample$73 == sample)) {
																										if((index$timeStep$74 == timeStep$var145)) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												for(int var58 = 0; var58 < noStates; var58 += 1) {
																													if(metric_valid_g[sample][timeStep$var145]) {
																														if((var58 == traceTempVariable$currentState$78_1)) {
																															{
																																{
																																	if(metric_valid_g[sample][timeStep$var145]) {
																																		double cv$temp$28$var157;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var157 = metric_mean[traceTempVariable$currentState$78_1];
																																			cv$temp$28$var157 = var157;
																																		}
																																		double cv$temp$29$var158;
																																		{
																																			// Constructing a random variable input for use later.
																																			double var158 = traceTempVariable$var158$21_1;
																																			cv$temp$29$var158 = var158;
																																		}
																																		
																																		// Record the probability of sample task 170 generating output with current configuration.
																																		if(((Math.log(cv$probabilitySample136Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			// If the second value is -infinity.
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))));
																																		}
																																		
																																		// Recorded the probability of reaching sample task 170 with the current configuration.
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value76);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
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
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
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
			
			// Save the probability of the original value.
			if((cv$valuePos == 0))
				cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			
			// Save the probability of the proposed value.
			else
				cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		
		// The probability ration for the proposed value and the current value.
		double cv$ratio = (cv$proposedProbability - cv$originalProbability);
		
		// Test if the probability of the sample is sufficient to keep the value. This needs
		// to be less than or equal as otherwise if the proposed value is not possible and
		// the random value is 0 an impossible value will be accepted.
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
			// If it is not revert the changes.
			// 
			// Set the sample value
			// Write out the value of the sample to a temporary variable prior to updating the
			// intermediate variables.
			double var75 = cv$originalValue;
			metric_var[var74] = var75;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 95 drawn from Beta 79. Inference was performed using a Beta to Bernoulli/Binomial
	// conjugate prior.
	private final void sample95(int var90) {
		// Local variable to record the number of true samples.
		double cv$sum = 0.0;
		
		// Local variable to record the number of samples.
		double cv$count = 0.0;
		{
			// Processing random variable 149.
			{
				// Looking for a path between Sample 95 and consumer Bernoulli 149.
				{
					for(int sample = 0; sample < noSamples; sample += 1) {
						for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
							if(fixedFlag$sample117) {
								for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
									if((index$sample$3_1 == sample)) {
										if((0 == timeStep$var145)) {
											if((var90 == st[sample][timeStep$var145])) {
												// Processing sample task 158 of consumer random variable null.
												{
													{
														{
															{
																{
																	// Include the value sampled by task 158 from random variable var149.
																	// Increment the number of samples.
																	cv$count = (cv$count + 1.0);
																	
																	// If the sample value was positive increase the count
																	if(metric_valid_g[sample][timeStep$var145])
																		cv$sum = (cv$sum + 1.0);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							} else {
								for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
									if(true) {
										// Enumerating the possible outputs of Categorical 110.
										for(int index$sample117$5 = 0; index$sample117$5 < noStates; index$sample117$5 += 1) {
											int distributionTempVariable$var111$7 = index$sample117$5;
											
											// Update the probability of sampling this value from the distribution value.
											double cv$probabilitySample117Value6 = (1.0 * distribution$sample117[((index$sample$4 - 0) / 1)][index$sample117$5]);
											int traceTempVariable$currentState$8_1 = distributionTempVariable$var111$7;
											if((index$sample$4 == sample)) {
												if((0 == timeStep$var145)) {
													if((var90 == traceTempVariable$currentState$8_1)) {
														// Processing sample task 158 of consumer random variable null.
														{
															{
																{
																	{
																		{
																			// Include the value sampled by task 158 from random variable var149.
																			// Increment the number of samples.
																			cv$count = (cv$count + cv$probabilitySample117Value6);
																			
																			// If the sample value was positive increase the count
																			if(metric_valid_g[sample][timeStep$var145])
																				cv$sum = (cv$sum + cv$probabilitySample117Value6);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int sample = 0; sample < noSamples; sample += 1) {
						for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
							if(fixedFlag$sample136) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$13_1]; timeStep$var122 += 1) {
										if((index$sample$13_1 == sample)) {
											if((timeStep$var122 == timeStep$var145)) {
												if((var90 == st[sample][timeStep$var145])) {
													// Processing sample task 158 of consumer random variable null.
													{
														{
															{
																{
																	{
																		// Include the value sampled by task 158 from random variable var149.
																		// Increment the number of samples.
																		cv$count = (cv$count + 1.0);
																		
																		// If the sample value was positive increase the count
																		if(metric_valid_g[sample][timeStep$var145])
																			cv$sum = (cv$sum + 1.0);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							} else {
								for(int index$sample$14 = 0; index$sample$14 < noSamples; index$sample$14 += 1) {
									for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$14]; timeStep$var122 += 1) {
										if(true) {
											// Enumerating the possible outputs of Categorical 129.
											for(int index$sample136$16 = 0; index$sample136$16 < noStates; index$sample136$16 += 1) {
												int distributionTempVariable$var130$18 = index$sample136$16;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample136Value17 = (1.0 * distribution$sample136[((index$sample$14 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$16]);
												int traceTempVariable$currentState$19_1 = distributionTempVariable$var130$18;
												if((index$sample$14 == sample)) {
													if((timeStep$var122 == timeStep$var145)) {
														if((var90 == traceTempVariable$currentState$19_1)) {
															// Processing sample task 158 of consumer random variable null.
															{
																{
																	{
																		{
																			{
																				// Include the value sampled by task 158 from random variable var149.
																				// Increment the number of samples.
																				cv$count = (cv$count + cv$probabilitySample136Value17);
																				
																				// If the sample value was positive increase the count
																				if(metric_valid_g[sample][timeStep$var145])
																					cv$sum = (cv$sum + cv$probabilitySample136Value17);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		// Write out the value of the sample to a temporary variable prior to updating the
		// intermediate variables.
		double var91 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		metric_valid_bias[var90] = var91;
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var27$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			cv$max = Math.max(cv$max, noStates);
			
			// Allocation of cv$var27$countGlobal for single threaded execution
			cv$var27$countGlobal = new double[cv$max];
		}
		
		// Constructor for cv$var40$countGlobal
		{
			// Calculate the longest array this random variable could produce and allocate an
			// array large enough to handle this.
			int cv$max = 0;
			for(int var39 = 0; var39 < noStates; var39 += 1)
				cv$max = Math.max(cv$max, noStates);
			
			// Allocation of cv$var40$countGlobal for single threaded execution
			cv$var40$countGlobal = new double[cv$max];
		}
		
		// Constructor for cv$distributionAccumulator$var129
		{
			// Variable to record the maximum value of Task Get 134. Initially set to the value
			// of putTask 44.
			int cv$var41$max = noStates;
			
			// Allocation of cv$distributionAccumulator$var129 for single threaded execution
			cv$distributionAccumulator$var129 = new double[cv$var41$max];
		}
		
		// Constructor for cv$var111$stateProbabilityGlobal
		{
			// Allocation of cv$var111$stateProbabilityGlobal for single threaded execution
			cv$var111$stateProbabilityGlobal = new double[noStates];
		}
		
		// Constructor for guard$sample117gaussian169$global
		{
			// Calculate the largest index of sample that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_sample = 0;
			
			// Calculate the largest index of timeStep that is possible and allocate an array
			// to hold the guard for each of these.
			int cv$max_timeStep$var145 = 0;
			for(int sample = 0; sample < length$metric.length; sample += 1)
				cv$max_timeStep$var145 = Math.max(cv$max_timeStep$var145, ((length$metric[sample] - 0) / 1));
			cv$max_sample = Math.max(cv$max_sample, ((length$metric.length - 0) / 1));
			
			// Allocation of guard$sample117gaussian169$global for single threaded execution
			guard$sample117gaussian169$global = new boolean[cv$max_sample][cv$max_timeStep$var145];
		}
		
		// Constructor for cv$var130$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 134. Initially set to the value
			// of putTask 44.
			int cv$var41$max = noStates;
			
			// Allocation of cv$var130$stateProbabilityGlobal for single threaded execution
			cv$var130$stateProbabilityGlobal = new double[cv$var41$max];
		}
		
		// Constructor for guard$sample136gaussian169$global
		{
			// Calculate the largest index of sample that is possible and allocate an array to
			// hold the guard for each of these.
			int cv$max_sample = 0;
			
			// Calculate the largest index of timeStep that is possible and allocate an array
			// to hold the guard for each of these.
			int cv$max_timeStep$var145 = 0;
			for(int sample = 0; sample < length$metric.length; sample += 1)
				cv$max_timeStep$var145 = Math.max(cv$max_timeStep$var145, ((length$metric[sample] - 0) / 1));
			cv$max_sample = Math.max(cv$max_sample, ((length$metric.length - 0) / 1));
			
			// Allocation of guard$sample136gaussian169$global for single threaded execution
			guard$sample136gaussian169$global = new boolean[cv$max_sample][cv$max_timeStep$var145];
		}
		
		// Constructor for cv$var150$stateProbabilityGlobal
		{
			// Allocation of cv$var150$stateProbabilityGlobal for single threaded execution
			cv$var150$stateProbabilityGlobal = new double[2];
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for v
		{
			v = new double[noStates];
		}
		
		// If initialStateDistribution has not been set already allocate space.
		if(!setFlag$initialStateDistribution) {
			// Constructor for initialStateDistribution
			{
				initialStateDistribution = new double[noStates];
			}
		}
		
		// If m has not been set already allocate space.
		if(!setFlag$m) {
			// Constructor for m
			{
				m = new double[noStates][];
				for(int var39 = 0; var39 < noStates; var39 += 1)
					m[var39] = new double[noStates];
			}
		}
		
		// If st has not been set already allocate space.
		if(!setFlag$st) {
			// Constructor for st
			{
				st = new int[length$metric.length][];
				for(int sample = 0; sample < length$metric.length; sample += 1)
					st[sample] = new int[length$metric[sample]];
			}
		}
		
		// If metric_g has not been set already allocate space.
		if(!setFlag$metric_g) {
			// Constructor for metric_g
			{
				metric_g = new double[length$metric.length][];
				for(int sample = 0; sample < length$metric.length; sample += 1)
					metric_g[sample] = new double[length$metric[sample]];
			}
		}
		
		// If metric_valid_g has not been set already allocate space.
		if(!setFlag$metric_valid_g) {
			// Constructor for metric_valid_g
			{
				metric_valid_g = new boolean[length$metric.length][];
				for(int sample = 0; sample < length$metric.length; sample += 1)
					metric_valid_g[sample] = new boolean[length$metric[sample]];
			}
		}
		
		// If metric_mean has not been set already allocate space.
		if(!setFlag$metric_mean) {
			// Constructor for metric_mean
			{
				metric_mean = new double[noStates];
			}
		}
		
		// If metric_var has not been set already allocate space.
		if(!setFlag$metric_var) {
			// Constructor for metric_var
			{
				metric_var = new double[noStates];
			}
		}
		
		// If metric_valid_bias has not been set already allocate space.
		if(!setFlag$metric_valid_bias) {
			// Constructor for metric_valid_bias
			{
				metric_valid_bias = new double[noStates];
			}
		}
		
		// Constructor for distribution$sample117
		{
			distribution$sample117 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				distribution$sample117[((sample - 0) / 1)] = new double[noStates];
		}
		
		// Constructor for distribution$sample136
		{
			distribution$sample136 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample = 0; sample < length$metric.length; sample += 1) {
				double[][] subarray$0 = new double[((((length$metric[sample] - 1) - 1) / 1) + 1)][];
				distribution$sample136[((sample - 0) / 1)] = subarray$0;
				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1)
					subarray$0[((timeStep$var122 - 1) / 1)] = new double[noStates];
			}
		}
		
		// Constructor for logProbability$sample158
		{
			logProbability$sample158 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$sample158[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample170
		{
			logProbability$sample170 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$sample170[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 0) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample30)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		for(int var39 = 0; var39 < noStates; var39 += 1) {
			double[] var40 = m[var39];
			if(!fixedFlag$sample43)
				DistributionSampling.sampleDirichlet(RNG$, v, var40);
		}
		for(int var58 = 0; var58 < noStates; var58 += 1) {
			if(!fixedFlag$sample63)
				metric_mean[var58] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		for(int var74 = 0; var74 < noStates; var74 += 1) {
			if(!fixedFlag$sample79)
				metric_var[var74] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int var90 = 0; var90 < noStates; var90 += 1) {
			if(!fixedFlag$sample95)
				metric_valid_bias[var90] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			int[] var108 = st[sample];
			if(!fixedFlag$sample117)
				var108[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			int[] var123 = st[sample];
			for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
				if(!fixedFlag$sample136)
					var123[timeStep$var122] = DistributionSampling.sampleCategorical(RNG$, m[st[sample][(timeStep$var122 - 1)]]);
			}
			boolean[] metric_valid_1d = metric_valid_g[sample];
			double[] metric_1d = metric_g[sample];
			for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
				if(!fixedFlag$sample158)
					metric_valid_1d[timeStep$var145] = DistributionSampling.sampleBernoulli(RNG$, metric_valid_bias[st[sample][timeStep$var145]]);
				if(metric_valid_g[sample][timeStep$var145]) {
					if(!(fixedFlag$sample158 && fixedFlag$sample170))
						metric_1d[timeStep$var145] = ((Math.sqrt(metric_var[st[sample][timeStep$var145]]) * DistributionSampling.sampleGaussian(RNG$)) + metric_mean[st[sample][timeStep$var145]]);
				}
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample30)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		for(int var39 = 0; var39 < noStates; var39 += 1) {
			double[] var40 = m[var39];
			if(!fixedFlag$sample43)
				DistributionSampling.sampleDirichlet(RNG$, v, var40);
		}
		for(int var58 = 0; var58 < noStates; var58 += 1) {
			if(!fixedFlag$sample63)
				metric_mean[var58] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		for(int var74 = 0; var74 < noStates; var74 += 1) {
			if(!fixedFlag$sample79)
				metric_var[var74] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int var90 = 0; var90 < noStates; var90 += 1) {
			if(!fixedFlag$sample95)
				metric_valid_bias[var90] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			// Create local copy of variable probabilities.
			double[] cv$distribution$sample117 = distribution$sample117[((sample - 0) / 1)];
			for(int index$var110 = 0; index$var110 < noStates; index$var110 += 1) {
				// Probability for this value
				double cv$value = (((0.0 <= index$var110) && (index$var110 < initialStateDistribution.length))?initialStateDistribution[index$var110]:0.0);
				if(!fixedFlag$sample117)
					// Save the probability of each value
					cv$distribution$sample117[index$var110] = cv$value;
			}
			for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
				// Create local copy of variable probabilities.
				double[] cv$distribution$sample136 = distribution$sample136[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)];
				for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1) {
					if(!fixedFlag$sample136)
						// Zero the probability of each value
						cv$distribution$sample136[index$var129] = 0.0;
				}
				
				// Iterate through possible values for var129's arguments.
				// 
				// Enumerating the possible arguments for Categorical 129.
				if(fixedFlag$sample117) {
					for(int index$sample$1_1 = 0; index$sample$1_1 < noSamples; index$sample$1_1 += 1) {
						if((index$sample$1_1 == sample)) {
							if((0 == (timeStep$var122 - 1))) {
								for(int var39 = 0; var39 < noStates; var39 += 1) {
									if((var39 == st[sample][(timeStep$var122 - 1)])) {
										{
											if(!fixedFlag$sample136) {
												double[] var128 = m[st[sample][(timeStep$var122 - 1)]];
												for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1)
													// Save the probability of each value
													cv$distribution$sample136[index$var129] = (cv$distribution$sample136[index$var129] + (1.0 * (((0.0 <= index$var129) && (index$var129 < var128.length))?var128[index$var129]:0.0)));
											}
										}
									}
								}
							}
						}
					}
				} else {
					for(int index$sample$2 = 0; index$sample$2 < noSamples; index$sample$2 += 1) {
						if(true) {
							// Enumerating the possible outputs of Categorical 110.
							for(int index$sample117$3 = 0; index$sample117$3 < noStates; index$sample117$3 += 1) {
								int distributionTempVariable$var111$5 = index$sample117$3;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample117Value4 = (1.0 * distribution$sample117[((index$sample$2 - 0) / 1)][index$sample117$3]);
								int traceTempVariable$var127$6_1 = distributionTempVariable$var111$5;
								if((index$sample$2 == sample)) {
									if((0 == (timeStep$var122 - 1))) {
										for(int var39 = 0; var39 < noStates; var39 += 1) {
											if((var39 == traceTempVariable$var127$6_1)) {
												{
													if(!fixedFlag$sample136) {
														double[] var128 = m[traceTempVariable$var127$6_1];
														for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1)
															// Save the probability of each value
															cv$distribution$sample136[index$var129] = (cv$distribution$sample136[index$var129] + (cv$probabilitySample117Value4 * (((0.0 <= index$var129) && (index$var129 < var128.length))?var128[index$var129]:0.0)));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				
				// Enumerating the possible arguments for Categorical 129.
				if(fixedFlag$sample136) {
					for(int index$sample$9_1 = 0; index$sample$9_1 < noSamples; index$sample$9_1 += 1) {
						for(int index$timeStep$9_2 = 1; index$timeStep$9_2 < length$metric[index$sample$9_1]; index$timeStep$9_2 += 1) {
							if((index$sample$9_1 == sample)) {
								if((index$timeStep$9_2 == (timeStep$var122 - 1))) {
									for(int var39 = 0; var39 < noStates; var39 += 1) {
										if((var39 == st[sample][(timeStep$var122 - 1)])) {
											{
												if(!fixedFlag$sample136) {
													double[] var128 = m[st[sample][(timeStep$var122 - 1)]];
													for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1)
														// Save the probability of each value
														cv$distribution$sample136[index$var129] = (cv$distribution$sample136[index$var129] + (1.0 * (((0.0 <= index$var129) && (index$var129 < var128.length))?var128[index$var129]:0.0)));
												}
											}
										}
									}
								}
							}
						}
					}
				} else {
					for(int index$sample$10 = 0; index$sample$10 < noSamples; index$sample$10 += 1) {
						for(int index$timeStep$11 = 1; index$timeStep$11 < length$metric[index$sample$10]; index$timeStep$11 += 1) {
							if(true) {
								// Enumerating the possible outputs of Categorical 129.
								for(int index$sample136$12 = 0; index$sample136$12 < noStates; index$sample136$12 += 1) {
									int distributionTempVariable$var130$14 = index$sample136$12;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample136Value13 = (1.0 * distribution$sample136[((index$sample$10 - 0) / 1)][((index$timeStep$11 - 1) / 1)][index$sample136$12]);
									int traceTempVariable$var127$15_1 = distributionTempVariable$var130$14;
									if((index$sample$10 == sample)) {
										if((index$timeStep$11 == (timeStep$var122 - 1))) {
											for(int var39 = 0; var39 < noStates; var39 += 1) {
												if((var39 == traceTempVariable$var127$15_1)) {
													{
														if(!fixedFlag$sample136) {
															double[] var128 = m[traceTempVariable$var127$15_1];
															for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1)
																// Save the probability of each value
																cv$distribution$sample136[index$var129] = (cv$distribution$sample136[index$var129] + (cv$probabilitySample136Value13 * (((0.0 <= index$var129) && (index$var129 < var128.length))?var128[index$var129]:0.0)));
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				
				// Sum the values in the array
				double cv$var129$sum = 0.0;
				for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1) {
					if(!fixedFlag$sample136)
						// sum the probability of each value
						cv$var129$sum = (cv$var129$sum + cv$distribution$sample136[index$var129]);
				}
				for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1) {
					if(!fixedFlag$sample136)
						// Normalise the probability of each value
						cv$distribution$sample136[index$var129] = (cv$distribution$sample136[index$var129] / cv$var129$sum);
				}
			}
			boolean[] metric_valid_1d = metric_valid_g[sample];
			double[] metric_1d = metric_g[sample];
			for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
				if(!fixedFlag$sample158)
					metric_valid_1d[timeStep$var145] = DistributionSampling.sampleBernoulli(RNG$, metric_valid_bias[st[sample][timeStep$var145]]);
				if(metric_valid_g[sample][timeStep$var145]) {
					if(!(fixedFlag$sample158 && fixedFlag$sample170))
						metric_1d[timeStep$var145] = ((Math.sqrt(metric_var[st[sample][timeStep$var145]]) * DistributionSampling.sampleGaussian(RNG$)) + metric_mean[st[sample][timeStep$var145]]);
				}
			}
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample30)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		for(int var39 = 0; var39 < noStates; var39 += 1) {
			double[] var40 = m[var39];
			if(!fixedFlag$sample43)
				DistributionSampling.sampleDirichlet(RNG$, v, var40);
		}
		for(int var58 = 0; var58 < noStates; var58 += 1) {
			if(!fixedFlag$sample63)
				metric_mean[var58] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		for(int var74 = 0; var74 < noStates; var74 += 1) {
			if(!fixedFlag$sample79)
				metric_var[var74] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int var90 = 0; var90 < noStates; var90 += 1) {
			if(!fixedFlag$sample95)
				metric_valid_bias[var90] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			int[] var108 = st[sample];
			if(!fixedFlag$sample117)
				var108[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			int[] var123 = st[sample];
			for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
				if(!fixedFlag$sample136)
					var123[timeStep$var122] = DistributionSampling.sampleCategorical(RNG$, m[st[sample][(timeStep$var122 - 1)]]);
			}
			boolean[] metric_valid_1d = metric_valid_g[sample];
			double[] metric_1d = metric_g[sample];
			for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
				if(!fixedFlag$sample158)
					metric_valid_1d[timeStep$var145] = DistributionSampling.sampleBernoulli(RNG$, metric_valid_bias[st[sample][timeStep$var145]]);
				if(metric_valid_g[sample][timeStep$var145]) {
					if(!(fixedFlag$sample158 && fixedFlag$sample170))
						metric_1d[timeStep$var145] = ((Math.sqrt(metric_var[st[sample][timeStep$var145]]) * DistributionSampling.sampleGaussian(RNG$)) + metric_mean[st[sample][timeStep$var145]]);
				}
			}
		}
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample30)
				sample30();
			for(int var39 = 0; var39 < noStates; var39 += 1) {
				if(!fixedFlag$sample43)
					sample43(var39);
			}
			for(int var58 = 0; var58 < noStates; var58 += 1) {
				if(!fixedFlag$sample63)
					sample63(var58);
			}
			for(int var74 = 0; var74 < noStates; var74 += 1) {
				if(!fixedFlag$sample79)
					sample79(var74);
			}
			for(int var90 = 0; var90 < noStates; var90 += 1) {
				if(!fixedFlag$sample95)
					sample95(var90);
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				if(!fixedFlag$sample117)
					sample117(sample);
				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
					if(!fixedFlag$sample136)
						sample136(sample, timeStep$var122);
				}
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					if(!fixedFlag$sample158)
						sample158(sample, timeStep$var145);
				}
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int sample = (noSamples - ((((noSamples - 1) - 0) % 1) + 1)); sample >= ((0 - 1) + 1); sample -= 1) {
				for(int timeStep$var145 = (length$metric[sample] - ((((length$metric[sample] - 1) - 0) % 1) + 1)); timeStep$var145 >= ((0 - 1) + 1); timeStep$var145 -= 1) {
					if(!fixedFlag$sample158)
						sample158(sample, timeStep$var145);
				}
				for(int timeStep$var122 = (length$metric[sample] - ((((length$metric[sample] - 1) - 1) % 1) + 1)); timeStep$var122 >= ((1 - 1) + 1); timeStep$var122 -= 1) {
					if(!fixedFlag$sample136)
						sample136(sample, timeStep$var122);
				}
				if(!fixedFlag$sample117)
					sample117(sample);
			}
			for(int var90 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var90 >= ((0 - 1) + 1); var90 -= 1) {
				if(!fixedFlag$sample95)
					sample95(var90);
			}
			for(int var74 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var74 >= ((0 - 1) + 1); var74 -= 1) {
				if(!fixedFlag$sample79)
					sample79(var74);
			}
			for(int var58 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var58 >= ((0 - 1) + 1); var58 -= 1) {
				if(!fixedFlag$sample63)
					sample63(var58);
			}
			for(int var39 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var39 >= ((0 - 1) + 1); var39 -= 1) {
				if(!fixedFlag$sample43)
					sample43(var39);
			}
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
		for(int var23 = 0; var23 < noStates; var23 += 1)
			v[var23] = 0.1;
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
					logProbability$sample158[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = 0.0;
			}
		}
		logProbability$var159 = 0.0;
		logProbability$metric_1d = 0.0;
		if(!fixedProbFlag$sample170) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1)
					logProbability$sample170[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = 0.0;
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
		for(int var39 = 0; var39 < noStates; var39 += 1) {
			double[] var40 = m[var39];
			if(!fixedFlag$sample43)
				DistributionSampling.sampleDirichlet(RNG$, v, var40);
		}
		for(int var58 = 0; var58 < noStates; var58 += 1) {
			if(!fixedFlag$sample63)
				metric_mean[var58] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		for(int var74 = 0; var74 < noStates; var74 += 1) {
			if(!fixedFlag$sample79)
				metric_var[var74] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int var90 = 0; var90 < noStates; var90 += 1) {
			if(!fixedFlag$sample95)
				metric_valid_bias[var90] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			int[] var108 = st[sample];
			if(!fixedFlag$sample117)
				var108[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			int[] var123 = st[sample];
			for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
				if(!fixedFlag$sample136)
					var123[timeStep$var122] = DistributionSampling.sampleCategorical(RNG$, m[st[sample][(timeStep$var122 - 1)]]);
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
		{
			// Deep copy between arrays
			boolean[][] cv$source1 = metric_valid;
			boolean[][] cv$target1 = metric_valid_g;
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
				boolean[] cv$source2 = cv$source1[cv$index1];
				boolean[] cv$target2 = cv$target1[cv$index1];
				int cv$length2 = cv$target2.length;
				for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
					cv$target2[cv$index2] = cv$source2[cv$index2];
			}
		}
		for(int sample = (noSamples - ((((noSamples - 1) - 0) % 1) + 1)); sample >= ((0 - 1) + 1); sample -= 1) {
			for(int timeStep$var145 = (length$metric[sample] - ((((length$metric[sample] - 1) - 0) % 1) + 1)); timeStep$var145 >= ((0 - 1) + 1); timeStep$var145 -= 1)
				metric_g[sample][timeStep$var145] = metric[sample][timeStep$var145];
		}
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMMetrics2(\n               double[][] metric,\n               boolean[][] metric_valid, \n               int noStates) {\n    \n    int noSamples = metric.length;\n\n    // Construct arrays describing the probability of a move from 1 state to another.\n    double[] v = new double[noStates] <~ 0.1;\n    double[] initialStateDistribution = dirichlet(v).sample();\n    double[][] m = dirichlet(v).sample(noStates);\n\n    //Allocate space for states\n    int[][] st = new int[noSamples][];\n\n    //Allocate space for generated metrics \n    double[][] metric_g = new double[noSamples][];\n    boolean[][] metric_valid_g = new boolean[noSamples][];\n    \n    //Calculate priors for the metric\n    double[] metric_mean = uniform(0.0, 100.0).sample(noStates);\n    double[] metric_var = inverseGamma(1.0, 1.0).sample(noStates);\n    double[] metric_valid_bias = beta(1.0, 1.0).sample(noStates);\n    \n    // Compute the values of each metric value\n    for(int sample = 0; sample < noSamples; sample++) {\n        //Calculate all the state transitions\n        int streamLength = metric[sample].length;\n        \n        // Allocate space for the state.\n        st[sample] = new int[streamLength];\n        \n        // Set the initial state by sampling from a categorical with learnt weightings.\n        st[sample][0] = categorical(initialStateDistribution).sampleDistribution();\n        \n        // Calculate the remaining weightings\n        for(int timeStep = 1; timeStep < streamLength; timeStep++)\n            st[sample][timeStep] = categorical(m[st[sample][timeStep-1]]).sampleDistribution();\n        \n        //Calculate metric values\n        double[] metric_1d = new double[streamLength];\n        metric_g[sample] = metric_1d;\n\n        boolean[] metric_valid_1d = new boolean[streamLength];\n        metric_valid_g[sample] = metric_valid_1d;\n\n        //Generate values.\n        for(int timeStep = 0; timeStep < streamLength; timeStep++){\n            int currentState = st[sample][timeStep];\n            \n            metric_valid_1d[timeStep] = bernoulli(metric_valid_bias[currentState]).sample();\n            if(metric_valid_1d[timeStep])\n                metric_1d[timeStep] = gaussian(metric_mean[currentState], metric_var[currentState]).sample();\n            // Observing here as a cast is required and doing it inside the for loops removes the need duplicate them later.\n            metric_1d[timeStep].observe(metric[sample][timeStep]);\n        }\n    }\n\n    //Tie the values to the measured values.\n    metric_valid_g.observe(metric_valid);\n}";
	}
}