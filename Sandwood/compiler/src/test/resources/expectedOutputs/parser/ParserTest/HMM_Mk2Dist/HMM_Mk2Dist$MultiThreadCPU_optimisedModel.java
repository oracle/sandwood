package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMM_Mk2Dist$MultiThreadCPU extends CoreModelMultiThreadCPU implements HMM_Mk2Dist$CoreInterface {

	// Declare the variables for the model.
	double[][] bias;
	boolean[][] constrainedFlag$sample126;
	boolean[] constrainedFlag$sample42;
	boolean[] constrainedFlag$sample57;
	boolean constrainedFlag$sample78 = true;
	boolean constrainedFlag$sample80 = true;
	boolean[] constrainedFlag$sample95;
	double[][][] distribution$sample126;
	double[][] distribution$sample95;
	int[][] events;
	int[][] eventsMeasured;
	boolean fixedFlag$sample126 = false;
	boolean fixedFlag$sample42 = false;
	boolean fixedFlag$sample57 = false;
	boolean fixedFlag$sample78 = false;
	boolean fixedFlag$sample80 = false;
	boolean fixedFlag$sample95 = false;
	boolean fixedProbFlag$sample126 = false;
	boolean fixedProbFlag$sample159 = false;
	boolean fixedProbFlag$sample42 = false;
	boolean fixedProbFlag$sample57 = false;
	boolean fixedProbFlag$sample78 = false;
	boolean fixedProbFlag$sample80 = false;
	boolean fixedProbFlag$sample95 = false;
	int initialState;
	int[] length$eventsMeasured;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$bias;
	double logProbability$events;
	double logProbability$initialState;
	double logProbability$m;
	double[][] logProbability$sample126;
	double[][] logProbability$sample159;
	double[] logProbability$sample95;
	double logProbability$st;
	double logProbability$var42;
	double logProbability$var56;
	double logProbability$weights;
	double[][] m;
	int noEvents;
	int noStates;
	int samples;
	int[][] st;
	boolean system$gibbsForward = true;
	double[] v;
	double[] v2;
	double[] weights;
	double[][] cv$distributionAccumulator$var122;
	double[] cv$distributionAccumulator$var91;
	double[][] cv$var123$stateProbabilityGlobal;
	double[][] cv$var42$countGlobal;
	double[][] cv$var56$countGlobal;
	double[] cv$var75$countGlobal;
	double[] cv$var77$stateProbabilityGlobal;
	double[][] cv$var92$stateProbabilityGlobal;

	public HMM_Mk2Dist$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for bias.
	@Override
	public final double[][] get$bias() {
		return bias;
	}

	// Setter for bias.
	@Override
	public final void set$bias(double[][] cv$value, boolean allocated$) {
		// Set flags for all the side effects of bias including if probabilities need to be
		// updated.
		bias = cv$value;
		
		// Unset the fixed probability flag for sample 57 as it depends on bias.
		fixedProbFlag$sample57 = false;
		
		// Unset the fixed probability flag for sample 159 as it depends on bias.
		fixedProbFlag$sample159 = false;
	}

	// Getter for distribution$sample126.
	@Override
	public final double[][][] get$distribution$sample126() {
		return distribution$sample126;
	}

	// Setter for distribution$sample126.
	@Override
	public final void set$distribution$sample126(double[][][] cv$value, boolean allocated$) {
		distribution$sample126 = cv$value;
	}

	// Getter for distribution$sample95.
	@Override
	public final double[][] get$distribution$sample95() {
		return distribution$sample95;
	}

	// Setter for distribution$sample95.
	@Override
	public final void set$distribution$sample95(double[][] cv$value, boolean allocated$) {
		distribution$sample95 = cv$value;
	}

	// Getter for events.
	@Override
	public final int[][] get$events() {
		return events;
	}

	// Getter for eventsMeasured.
	@Override
	public final int[][] get$eventsMeasured() {
		return eventsMeasured;
	}

	// Setter for eventsMeasured.
	@Override
	public final void set$eventsMeasured(int[][] cv$value, boolean allocated$) {
		eventsMeasured = cv$value;
	}

	// Getter for fixedFlag$sample126.
	@Override
	public final boolean get$fixedFlag$sample126() {
		return fixedFlag$sample126;
	}

	// Setter for fixedFlag$sample126.
	@Override
	public final void set$fixedFlag$sample126(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample126 including if probabilities
		// need to be updated.
		fixedFlag$sample126 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample126$1 = 0; index$constrainedFlag$sample126$1 < constrainedFlag$sample126.length; index$constrainedFlag$sample126$1 += 1) {
				boolean[] cv$constrainedFlag$sample126$1 = constrainedFlag$sample126[index$constrainedFlag$sample126$1];
				for(int index$constrainedFlag$sample126$2 = 0; index$constrainedFlag$sample126$2 < cv$constrainedFlag$sample126$1.length; index$constrainedFlag$sample126$2 += 1)
					cv$constrainedFlag$sample126$1[index$constrainedFlag$sample126$2] = true;
			}
		}
		
		// Should the probability of sample 126 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample126" with its value "cv$value".
		fixedProbFlag$sample126 = (cv$value && fixedProbFlag$sample126);
		
		// Should the probability of sample 159 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample126" with its value "cv$value".
		fixedProbFlag$sample159 = (cv$value && fixedProbFlag$sample159);
	}

	// Getter for fixedFlag$sample42.
	@Override
	public final boolean get$fixedFlag$sample42() {
		return fixedFlag$sample42;
	}

	// Setter for fixedFlag$sample42.
	@Override
	public final void set$fixedFlag$sample42(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample42 including if probabilities
		// need to be updated.
		fixedFlag$sample42 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample42$1 = 0; index$constrainedFlag$sample42$1 < constrainedFlag$sample42.length; index$constrainedFlag$sample42$1 += 1)
				constrainedFlag$sample42[index$constrainedFlag$sample42$1] = true;
		}
		
		// Should the probability of sample 42 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample42" with its value "cv$value".
		fixedProbFlag$sample42 = (cv$value && fixedProbFlag$sample42);
		
		// Should the probability of sample 95 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample42" with its value "cv$value".
		fixedProbFlag$sample95 = (cv$value && fixedProbFlag$sample95);
		
		// Should the probability of sample 126 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample42" with its value "cv$value".
		fixedProbFlag$sample126 = (cv$value && fixedProbFlag$sample126);
	}

	// Getter for fixedFlag$sample57.
	@Override
	public final boolean get$fixedFlag$sample57() {
		return fixedFlag$sample57;
	}

	// Setter for fixedFlag$sample57.
	@Override
	public final void set$fixedFlag$sample57(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample57 including if probabilities
		// need to be updated.
		fixedFlag$sample57 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample57$1 = 0; index$constrainedFlag$sample57$1 < constrainedFlag$sample57.length; index$constrainedFlag$sample57$1 += 1)
				constrainedFlag$sample57[index$constrainedFlag$sample57$1] = true;
		}
		
		// Should the probability of sample 57 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		
		// Should the probability of sample 159 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample57" with its value "cv$value".
		fixedProbFlag$sample159 = (cv$value && fixedProbFlag$sample159);
	}

	// Getter for fixedFlag$sample78.
	@Override
	public final boolean get$fixedFlag$sample78() {
		return fixedFlag$sample78;
	}

	// Setter for fixedFlag$sample78.
	@Override
	public final void set$fixedFlag$sample78(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample78 including if probabilities
		// need to be updated.
		fixedFlag$sample78 = cv$value;
		
		// Substituted "fixedFlag$sample78" with its value "cv$value".
		constrainedFlag$sample78 = (cv$value || constrainedFlag$sample78);
		
		// Should the probability of sample 78 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample78" with its value "cv$value".
		fixedProbFlag$sample78 = (cv$value && fixedProbFlag$sample78);
		
		// Should the probability of sample 80 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample78" with its value "cv$value".
		fixedProbFlag$sample80 = (cv$value && fixedProbFlag$sample80);
	}

	// Getter for fixedFlag$sample80.
	@Override
	public final boolean get$fixedFlag$sample80() {
		return fixedFlag$sample80;
	}

	// Setter for fixedFlag$sample80.
	@Override
	public final void set$fixedFlag$sample80(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample80 including if probabilities
		// need to be updated.
		fixedFlag$sample80 = cv$value;
		
		// Substituted "fixedFlag$sample80" with its value "cv$value".
		constrainedFlag$sample80 = (cv$value || constrainedFlag$sample80);
		
		// Should the probability of sample 80 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample80" with its value "cv$value".
		fixedProbFlag$sample80 = (cv$value && fixedProbFlag$sample80);
		
		// Should the probability of sample 95 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample80" with its value "cv$value".
		fixedProbFlag$sample95 = (cv$value && fixedProbFlag$sample95);
	}

	// Getter for fixedFlag$sample95.
	@Override
	public final boolean get$fixedFlag$sample95() {
		return fixedFlag$sample95;
	}

	// Setter for fixedFlag$sample95.
	@Override
	public final void set$fixedFlag$sample95(boolean cv$value, boolean allocated$) {
		// Set flags for all the side effects of fixedFlag$sample95 including if probabilities
		// need to be updated.
		fixedFlag$sample95 = cv$value;
		
		// If the model has been allocated update the constraints flags
		if(allocated$) {
			// Set all the values in the array
			for(int index$constrainedFlag$sample95$1 = 0; index$constrainedFlag$sample95$1 < constrainedFlag$sample95.length; index$constrainedFlag$sample95$1 += 1)
				constrainedFlag$sample95[index$constrainedFlag$sample95$1] = true;
		}
		
		// Should the probability of sample 95 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample95" with its value "cv$value".
		fixedProbFlag$sample95 = (cv$value && fixedProbFlag$sample95);
		
		// Should the probability of sample 126 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample95" with its value "cv$value".
		fixedProbFlag$sample126 = (cv$value && fixedProbFlag$sample126);
		
		// Should the probability of sample 159 be set to fixed. This will only every change
		// the flag to false.
		// 
		// Substituted "fixedFlag$sample95" with its value "cv$value".
		fixedProbFlag$sample159 = (cv$value && fixedProbFlag$sample159);
	}

	// Getter for initialState.
	@Override
	public final int get$initialState() {
		return initialState;
	}

	// Setter for initialState.
	@Override
	public final void set$initialState(int cv$value, boolean allocated$) {
		// Set flags for all the side effects of initialState including if probabilities need
		// to be updated.
		initialState = cv$value;
		
		// Unset the fixed probability flag for sample 80 as it depends on initialState.
		fixedProbFlag$sample80 = false;
		
		// Unset the fixed probability flag for sample 95 as it depends on initialState.
		fixedProbFlag$sample95 = false;
	}

	// Getter for length$eventsMeasured.
	@Override
	public final int[] get$length$eventsMeasured() {
		return length$eventsMeasured;
	}

	// Setter for length$eventsMeasured.
	@Override
	public final void set$length$eventsMeasured(int[] cv$value, boolean allocated$) {
		length$eventsMeasured = cv$value;
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

	// Getter for logProbability$events.
	@Override
	public final double get$logProbability$events() {
		return logProbability$events;
	}

	// Getter for logProbability$initialState.
	@Override
	public final double get$logProbability$initialState() {
		return logProbability$initialState;
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

	// Getter for logProbability$weights.
	@Override
	public final double get$logProbability$weights() {
		return logProbability$weights;
	}

	// Getter for m.
	@Override
	public final double[][] get$m() {
		return m;
	}

	// Setter for m.
	@Override
	public final void set$m(double[][] cv$value, boolean allocated$) {
		// Set flags for all the side effects of m including if probabilities need to be updated.
		m = cv$value;
		
		// Unset the fixed probability flag for sample 42 as it depends on m.
		fixedProbFlag$sample42 = false;
		
		// Unset the fixed probability flag for sample 95 as it depends on m.
		fixedProbFlag$sample95 = false;
		
		// Unset the fixed probability flag for sample 126 as it depends on m.
		fixedProbFlag$sample126 = false;
	}

	// Getter for noEvents.
	@Override
	public final int get$noEvents() {
		return noEvents;
	}

	// Setter for noEvents.
	@Override
	public final void set$noEvents(int cv$value, boolean allocated$) {
		noEvents = cv$value;
	}

	// Getter for noStates.
	@Override
	public final int get$noStates() {
		return noStates;
	}

	// Setter for noStates.
	@Override
	public final void set$noStates(int cv$value, boolean allocated$) {
		noStates = cv$value;
	}

	// Getter for samples.
	@Override
	public final int get$samples() {
		return samples;
	}

	// Getter for st.
	@Override
	public final int[][] get$st() {
		return st;
	}

	// Setter for st.
	@Override
	public final void set$st(int[][] cv$value, boolean allocated$) {
		// Set flags for all the side effects of st including if probabilities need to be
		// updated.
		st = cv$value;
		
		// Unset the fixed probability flag for sample 95 as it depends on st.
		fixedProbFlag$sample95 = false;
		
		// Unset the fixed probability flag for sample 126 as it depends on st.
		fixedProbFlag$sample126 = false;
		
		// Unset the fixed probability flag for sample 159 as it depends on st.
		fixedProbFlag$sample159 = false;
	}

	// Getter for v.
	@Override
	public final double[] get$v() {
		return v;
	}

	// Getter for v2.
	@Override
	public final double[] get$v2() {
		return v2;
	}

	// Getter for weights.
	@Override
	public final double[] get$weights() {
		return weights;
	}

	// Setter for weights.
	@Override
	public final void set$weights(double[] cv$value, boolean allocated$) {
		// Set flags for all the side effects of weights including if probabilities need to
		// be updated.
		weights = cv$value;
		
		// Unset the fixed probability flag for sample 78 as it depends on weights.
		fixedProbFlag$sample78 = false;
		
		// Unset the fixed probability flag for sample 80 as it depends on weights.
		fixedProbFlag$sample80 = false;
	}

	// Pick a value from the distribution for the unconditioned variable from sample126
	private final void drawValueSample126(int i$var104, int j$var115, int threadID$cv$i$var104, Rng RNG$) {
		st[i$var104][j$var115] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var104][(j$var115 - 1)]], noStates);
	}

	// Pick a value from the distribution for the unconditioned variable from sample42
	private final void drawValueSample42(int var41, int threadID$cv$var41, Rng RNG$) {
		DistributionSampling.sampleDirichlet(RNG$, v, noStates, m[var41]);
	}

	// Pick a value from the distribution for the unconditioned variable from sample57
	private final void drawValueSample57(int var55, int threadID$cv$var55, Rng RNG$) {
		DistributionSampling.sampleDirichlet(RNG$, v2, noEvents, bias[var55]);
	}

	// Pick a value from the distribution for the unconditioned variable from sample78
	private final void drawValueSample78() {
		DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
	}

	// Pick a value from the distribution for the unconditioned variable from sample80
	private final void drawValueSample80() {
		initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
	}

	// Pick a value from the distribution for the unconditioned variable from sample95
	private final void drawValueSample95(int i$var87, int threadID$cv$i$var87, Rng RNG$) {
		st[i$var87][0] = DistributionSampling.sampleCategorical(RNG$, m[initialState], noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 126 drawn from Categorical 122. Inference was performed using variable
	// marginalization.
	private final void inferSample126(int i$var104, int j$var115, int threadID$cv$i$var104, Rng RNG$) {
		constrainedFlag$sample126[i$var104][(j$var115 - 1)] = false;
		
		// Calculate the number of states to evaluate.
		int cv$numStates = 0;
		
		// Exploring all the possible state counts for random variable 122.
		// 
		// Enumerating the possible arguments for Categorical 122.
		if((1 == j$var115)) {
			// Exploring all the possible state counts for random variable 122.
			// 
			// Enumerating the possible arguments for Categorical 122.
			if(fixedFlag$sample95) {
				int var41 = st[i$var104][0];
				
												// Substituted "j$var115" with its value "1".
				if(((0 <= var41) && (var41 < noStates)))
					// variable marginalization
					// 
										// cv$numStates's comment
					// Calculate the number of states to evaluate.
					cv$numStates = Math.max(0, noStates);
			} else {
				// Enumerating the possible outputs of Categorical 91.
				if((0 < noStates))
					// variable marginalization
					cv$numStates = noStates;
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((0 < noStates)) {
			int index$j$13 = (j$var115 - 1);
			
												// index$i$2's comment
			// Copy of index so that its values can be safely substituted
			// 
									// Substituted "index$i$12" with its value "i$var104".
			// 
									// Substituted "index$j$13" with its value "(j$var115 - 1)".
			// 
									// Substituted "index$j$13" with its value "(j$var115 - 1)".
			// 
									// Substituted "index$j$13" with its value "(j$var115 - 1)".
			// 
									// Substituted "index$j$13" with its value "(j$var115 - 1)".
			// 
									// Substituted "index$j$13" with its value "(j$var115 - 1)".
			if(((1 <= index$j$13) && !(index$j$13 == j$var115)))
				// variable marginalization
				cv$numStates = noStates;
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var123$stateProbabilityGlobal[threadID$cv$i$var104];
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 122 creating
			// sample task 126.
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Enumerating the possible arguments for Categorical 122.
			if((1 == j$var115)) {
				// Enumerating the possible arguments for Categorical 122.
				if(fixedFlag$sample95) {
					int var41 = st[i$var104][0];
					
															// Substituted "j$var115" with its value "1".
					if(((0 <= var41) && (var41 < noStates))) {
						// Record the reached probability density.
						// 
						// Initialize a counter to track the reached distributions.
						cv$reachedDistributionSourceRV = 1.0;
						
						// Constructing a random variable input for use later.
						// 
						// Substituted "j$var115" with its value "1".
						double[] var121 = m[st[i$var104][0]];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
																		// Value of the variable at this index
						double cv$accumulatedProbabilities = ((((cv$valuePos < noStates) && (0.0 <= var121[cv$valuePos])) && (var121[cv$valuePos] <= 1.0))?Math.log(var121[cv$valuePos]):Double.NEGATIVE_INFINITY);
						
																		// Substituted "j$var115" with its value "1".
						// 
						// Substituted "j$var149" with its value "1".
						if((1 < length$eventsMeasured[i$var104])) {
							// Processing sample task 159 of consumer random variable null.
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "j$var115" with its value "1".
							constrainedFlag$sample126[i$var104][0] = true;
							
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							
							// Enumerating the possible arguments for the variable Categorical 154 which is consuming
							// the output of Sample task 126.
							// 
							// Looking for a path between Sample 126 and consumer Categorical 154.
							// 
							// Value of the variable at this index
							if((cv$valuePos < noStates)) {
								// Constructing a random variable input for use later.
								// 
								// Looking for a path between Sample 126 and consumer Categorical 154.
								// 
								// Value of the variable at this index
								double[] var153 = bias[cv$valuePos];
								
																								// Substituted "i$var136" with its value "i$var104".
								// 
																								// Substituted "j$var149" with its value "1".
								cv$accumulatedConsumerProbabilities = ((((((1.0 <= events[i$var104][1]) && (events[i$var104][1] < (noEvents + 1))) && (0 < noEvents)) && (0.0 <= var153[(events[i$var104][1] - 1)])) && (var153[(events[i$var104][1] - 1)] <= 1.0))?Math.log(var153[(events[i$var104][1] - 1)]):Double.NEGATIVE_INFINITY);
								
								// Recorded the probability of reaching sample task 159 with the current configuration.
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
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					// Enumerating the possible outputs of Categorical 91.
					for(int index$sample95$22 = 0; index$sample95$22 < noStates; index$sample95$22 += 1) {
						// Update the probability of sampling this value from the distribution value.
						// 
						// Substituted "i$var87" with its value "i$var104".
						double cv$probabilitySample95Value23 = distribution$sample95[i$var104][index$sample95$22];
						
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample95Value23);
						
						// Constructing a random variable input for use later.
						double[] var121 = m[index$sample95$22];
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						// 
																		// Value of the variable at this index
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample95Value23) + (((0.0 <= var121[cv$valuePos]) && (var121[cv$valuePos] <= 1.0))?Math.log(var121[cv$valuePos]):Double.NEGATIVE_INFINITY));
						
																		// Substituted "j$var115" with its value "1".
						// 
						// Substituted "j$var149" with its value "1".
						if((1 < length$eventsMeasured[i$var104])) {
							// Processing sample task 159 of consumer random variable null.
							// Mark that the sample has observed constrained data.
							// 
							// Substituted "j$var115" with its value "1".
							constrainedFlag$sample126[i$var104][0] = true;
							
							// Constructing a random variable input for use later.
							// 
							// Looking for a path between Sample 126 and consumer Categorical 154.
							// 
							// Value of the variable at this index
							double[] var153 = bias[cv$valuePos];
							
							// A check to ensure rounding of floating point values can never result in a negative
							// value.
							// 
							// Recorded the probability of reaching sample task 159 with the current configuration.
							// 
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							// 
							// Variable declaration of cv$accumulatedConsumerProbabilities moved.
							// Declaration comment was:
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							// 
																					// Substituted "i$var136" with its value "i$var104".
							// 
																					// Substituted "j$var149" with its value "1".
							cv$accumulatedProbabilities = (((((((1.0 <= events[i$var104][1]) && (events[i$var104][1] < (noEvents + 1))) && (0 < noEvents)) && (0.0 <= var153[(events[i$var104][1] - 1)])) && (var153[(events[i$var104][1] - 1)] <= 1.0))?Math.log(var153[(events[i$var104][1] - 1)]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			int index$j$30 = (j$var115 - 1);
			
												// index$i$2's comment
			// Copy of index so that its values can be safely substituted
			// 
									// Substituted "index$i$29" with its value "i$var104".
			// 
									// Substituted "index$j$30" with its value "(j$var115 - 1)".
			// 
									// Substituted "index$j$30" with its value "(j$var115 - 1)".
			// 
									// Substituted "index$j$30" with its value "(j$var115 - 1)".
			// 
									// Substituted "index$j$30" with its value "(j$var115 - 1)".
			// 
									// Substituted "index$j$30" with its value "(j$var115 - 1)".
			if(((1 <= index$j$30) && !(index$j$30 == j$var115))) {
				// Enumerating the possible outputs of Categorical 122.
				for(int index$sample126$31 = 0; index$sample126$31 < noStates; index$sample126$31 += 1) {
					// Update the probability of sampling this value from the distribution value.
					// 
					// Substituted "index$i$29" with its value "i$var104".
					double cv$probabilitySample126Value32 = distribution$sample126[i$var104][(index$j$30 - 1)][index$sample126$31];
					
					// Record the reached probability density.
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample126Value32);
					
					// Constructing a random variable input for use later.
					double[] var121 = m[index$sample126$31];
					
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample126[i$var104][(j$var115 - 1)] = true;
					
					// Constructing a random variable input for use later.
					// 
					// Looking for a path between Sample 126 and consumer Categorical 154.
					double[] var153 = bias[index$sample126$31];
					
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
					// Recorded the probability of reaching sample task 159 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
															// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					// 
															// Value of the variable at this index
					double cv$accumulatedProbabilities = ((((((((1.0 <= events[i$var104][j$var115]) && (events[i$var104][j$var115] < (noEvents + 1))) && (0 < noEvents)) && (0.0 <= var153[(events[i$var104][j$var115] - 1)])) && (var153[(events[i$var104][j$var115] - 1)] <= 1.0))?Math.log(var153[(events[i$var104][j$var115] - 1)]):Double.NEGATIVE_INFINITY) + Math.log(cv$probabilitySample126Value32)) + (((0.0 <= var121[cv$valuePos]) && (var121[cv$valuePos] <= 1.0))?Math.log(var121[cv$valuePos]):Double.NEGATIVE_INFINITY));
					
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
			int index$j$57_3 = (j$var115 + 1);
			if((index$j$57_3 < length$eventsMeasured[i$var104])) {
				// Processing sample task 126 of consumer random variable null.
				// A local array to hold the accumulated distributions of the sample tasks for each
				// configuration of distributions.
				double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var122[threadID$cv$i$var104];
				
				// Zero all the elements in the distribution accumulator
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$accumulatedConsumerDistributions[cv$i] = 0.0;
				
				// Zero an accumulator to track the probabilities reached.
				double cv$reachedDistributionProbability = 0.0;
				
				// Enumerating the possible arguments for the variable Categorical 122 which is consuming
				// the output of Sample task 126.
				// 
				// Processing random variable 122.
				// 
				// Looking for a path between Sample 126 and consumer Categorical 122.
				// 
				// Value of the variable at this index
				if((cv$valuePos < noStates)) {
					// Declare and zero an accumulator for tracking the reached source probability space.
					double scopeVariable$reachedSourceProbability = 0.0;
					
					// Enumerating the possible arguments for Categorical 122.
					if((1 == j$var115)) {
						// Enumerating the possible arguments for Categorical 122.
						if(fixedFlag$sample95) {
							int index$var41$68_1 = st[i$var104][0];
							
																					// Substituted "j$var115" with its value "1".
							if(((0 <= index$var41$68_1) && (index$var41$68_1 < noStates)))
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							// Enumerating the possible outputs of Categorical 91.
							for(int index$sample95$64 = 0; index$sample95$64 < noStates; index$sample95$64 += 1)
								// Add the probability of this argument configuration.
								// 
																// cv$probabilitySample95Value65's comment
								// Update the probability of sampling this value from the distribution value.
								// 
								// Substituted "i$var87" with its value "i$var104".
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample95[i$var104][index$sample95$64]);
						}
					}
					int index$j$72 = (j$var115 - 1);
					
																				// index$j$59's comment
					// Copy of index so that its values can be safely substituted
					// 
																				// index$i$60's comment
					// Copy of index so that its values can be safely substituted
					// 
					// Substituted "index$i$57_2" with its value "i$var104".
					// 
															// Substituted "index$j$57_3" with its value "(j$var115 + 1)".
					// 
															// Substituted "index$j$57_3" with its value "(j$var115 + 1)".
					// 
															// Substituted "index$j$57_3" with its value "(j$var115 + 1)".
					// 
															// Substituted "index$j$57_3" with its value "(j$var115 + 1)".
					// 
															// Substituted "index$j$57_3" with its value "(j$var115 + 1)".
					if((((1 <= index$j$72) && !(index$j$72 == j$var115)) && !(index$j$72 == index$j$57_3))) {
						// Enumerating the possible outputs of Categorical 122.
						for(int index$sample126$73 = 0; index$sample126$73 < noStates; index$sample126$73 += 1)
							// Add the probability of this argument configuration.
							// 
														// cv$probabilitySample126Value74's comment
							// Update the probability of sampling this value from the distribution value.
							// 
							// Substituted "index$i$71" with its value "i$var104".
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample126[i$var104][(index$j$72 - 1)][index$sample126$73]);
					}
					
					// Record the reached distribution.
					// 
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Zero an accumulator to track the probabilities reached.
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					
					// Add the current distribution to the distribution accumulator.
					// 
															// Constructing a random variable input for use later.
					// 
					// Processing random variable 122.
					// 
					// Looking for a path between Sample 126 and consumer Categorical 122.
					// 
					// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, scopeVariable$reachedSourceProbability, m[cv$valuePos], noStates);
				}
				
				// A local copy of the samples' distribution.
				// 
				// Substituted "index$i$57_2" with its value "i$var104".
				double[] cv$sampleDistribution = distribution$sample126[i$var104][(index$j$57_3 - 1)];
				
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
		if(constrainedFlag$sample126[i$var104][(j$var115 - 1)]) {
			// Set the calculated probabilities to be the distribution values, and normalize
			// Local copy of the probability array
			double[] cv$localProbability = distribution$sample126[i$var104][(j$var115 - 1)];
			
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			double cv$lseMax = cv$stateProbabilityLocal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else {
				// Initialize the sum of the array elements
				double cv$lseSum = 0.0;
				
				// Offset values, move to normal space, and sum.
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 42 drawn from Dirichlet 30. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample42(int var41, int threadID$cv$var41, Rng RNG$) {
		constrainedFlag$sample42[var41] = false;
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var42$countGlobal[threadID$cv$var41];
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(((var41 == initialState) && fixedFlag$sample95)) {
			// Looking for a path between Sample 42 and consumer Categorical 91.
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
				// Processing sample task 95 of consumer random variable null.
				// Mark that the sample has observed constrained data.
				constrainedFlag$sample42[var41] = true;
				
				// Increment the sample counter with the value sampled by sample task 95 of random
				// variable var91
				cv$countLocal[st[i$var87][0]] = (cv$countLocal[st[i$var87][0]] + 1.0);
			}
		}
		
		// Processing random variable 122.
		// 
		// Looking for a path between Sample 42 and consumer Categorical 122.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(fixedFlag$sample126) {
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 < length$eventsMeasured[i$var104])) {
					if(fixedFlag$sample95) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var41 == st[i$var104][0])) {
							// Mark that the sample has observed constrained data.
							constrainedFlag$sample42[var41] = true;
							
							// Increment the sample counter with the value sampled by sample task 126 of random
							// variable var122
							// 
																					// Substituted "j$var115" with its value "1".
							cv$countLocal[st[i$var104][1]] = (cv$countLocal[st[i$var104][1]] + 1.0);
						}
					} else {
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample42[var41] = true;
						
						// Increment the sample counter with the value sampled by sample task 126 of random
						// variable var122
						// 
																		// Substituted "i$var87" with its value "i$var104".
						// 
						// Substituted "index$sample95$9" with its value "var41".
						cv$countLocal[st[i$var104][1]] = (cv$countLocal[st[i$var104][1]] + distribution$sample95[i$var104][var41]);
					}
				}
			}
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 2; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
					if((var41 == st[i$var104][(j$var115 - 1)])) {
						// Processing sample task 126 of consumer random variable null.
						// 
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample42[var41] = true;
						
						// Increment the sample counter with the value sampled by sample task 126 of random
						// variable var122
						cv$countLocal[st[i$var104][j$var115]] = (cv$countLocal[st[i$var104][j$var115]] + 1.0);
					}
				}
			}
		}
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(((var41 == initialState) && !fixedFlag$sample95)) {
			// Looking for a path between Sample 42 and consumer Categorical 91.
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
				// Processing sample task 95 of consumer random variable null.
				// 
				// Merge the distribution probabilities into the count
				// 
				// Get the length of the array
				for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
					// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample95[i$var87][cv$loopIndex]);
			}
		}
		
		// Processing random variable 122.
		// 
		// Looking for a path between Sample 42 and consumer Categorical 122.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		// 
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample126) {
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if((1 < length$eventsMeasured[i$var104])) {
					if(fixedFlag$sample95) {
						// Constraints moved from conditionals in inner loops/scopes/etc.
						if((var41 == st[i$var104][0])) {
							// Merge the distribution probabilities into the count
							// 
							// Get the length of the array
							for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
																								// The probability of reaching the consumer with this set of consumer arguments
								// 
								// Add the probability of this argument configuration.
								// 
								// Declare and zero an accumulator for tracking the reached source probability space.
								cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample126[i$var104][0][cv$loopIndex]);
						}
					} else {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
																		// Substituted "i$var87" with its value "i$var104".
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						// 
						// Substituted "index$sample95$49" with its value "var41".
						double cv$distributionProbability = distribution$sample95[i$var104][var41];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							// Substituted "j$var115" with its value "1".
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample126[i$var104][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
					int index$j$59 = (j$var115 - 1);
					
					// Constraints moved from conditionals in inner loops/scopes/etc.
					// 
					// Substituted "index$i$58" with its value "i$var104".
					if((1 <= index$j$59)) {
						// The probability of reaching the consumer with this set of consumer arguments
						// 
																		// Substituted "index$i$58" with its value "i$var104".
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						// 
						// Substituted "index$sample126$60" with its value "var41".
						double cv$distributionProbability = distribution$sample126[i$var104][(index$j$59 - 1)][var41];
						
						// Merge the distribution probabilities into the count
						// 
						// Get the length of the array
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample126[i$var104][(j$var115 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		if(constrainedFlag$sample42[var41])
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
			// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var41], noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 57 drawn from Dirichlet 44. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample57(int var55, int threadID$cv$var55, Rng RNG$) {
		constrainedFlag$sample57[var55] = false;
		
		// A local reference to the scratch space.
		double[] cv$countLocal = cv$var56$countGlobal[threadID$cv$var55];
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noEvents; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		
		// Processing random variable 154.
		// 
		// Looking for a path between Sample 57 and consumer Categorical 154.
		for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
			for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
				if(fixedFlag$sample126) {
					// Constraints moved from conditionals in inner loops/scopes/etc.
					if((var55 == st[i$var136][j$var149])) {
						// Processing sample task 159 of consumer random variable null.
						// 
						// Mark that the sample has observed constrained data.
						constrainedFlag$sample57[var55] = true;
						
						// Increment the sample counter with the value sampled by sample task 159 of random
						// variable var154
						cv$countLocal[(events[i$var136][j$var149] - 1)] = (cv$countLocal[(events[i$var136][j$var149] - 1)] + 1.0);
					}
				} else {
					// Processing sample task 159 of consumer random variable null.
					// 
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample57[var55] = true;
					
					// Increment the sample counter with the value sampled by sample task 159 of random
					// variable var154
					// 
															// Substituted "i$var104" with its value "i$var136".
					// 
					// Substituted "index$sample126$16" with its value "var55".
					cv$countLocal[(events[i$var136][j$var149] - 1)] = (cv$countLocal[(events[i$var136][j$var149] - 1)] + distribution$sample126[i$var136][(j$var149 - 1)][var55]);
				}
			}
		}
		if(constrainedFlag$sample57[var55])
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
			// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v2, cv$countLocal, bias[var55], noEvents);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 78 drawn from Dirichlet 74. Inference was performed using a Dirichlet
	// to Categorical conjugate prior.
	private final void inferSample78() {
		constrainedFlag$sample78 = false;
		
		// Initialize the array values to 0.
		// 
		// Get the length of the array
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			// A local reference to the scratch space.
			cv$var75$countGlobal[cv$loopIndex] = 0.0;
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if((fixedFlag$sample80 || constrainedFlag$sample80)) {
			// Processing random variable 76.
			// 
			// Processing sample task 80 of consumer random variable null.
			// Mark that the sample has observed constrained data.
			constrainedFlag$sample78 = true;
			
			// Increment the sample counter with the value sampled by sample task 80 of random
			// variable var76
			// 
									// A local reference to the scratch space.
			cv$var75$countGlobal[initialState] = (cv$var75$countGlobal[initialState] + 1.0);
		}
		if(constrainedFlag$sample78)
			// Calculate the new sample value
			// 
			// Calculate a new sample value and write it into cv$targetLocal.
			// 
									// A reference local to the function for the sample variable.
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var75$countGlobal, weights, noStates);
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 80 drawn from Categorical 76. Inference was performed using variable
	// marginalization.
	private final void inferSample80() {
		constrainedFlag$sample80 = false;
		
		// Variable declaration of cv$numStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
				// cv$numStates's comment
		// Calculate the number of states to evaluate.
		int cv$numStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Write out the new value of the sample.
			// 
			// Value of the variable at this index
			initialState = cv$valuePos;
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			double cv$accumulatedProbabilities = (((((cv$valuePos < noStates) && (0 < noStates)) && (0.0 <= weights[cv$valuePos])) && (weights[cv$valuePos] <= 1.0))?Math.log(weights[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(fixedFlag$sample95) {
				for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample80 = true;
					
					// Constructing a random variable input for use later.
					// 
					// Value of the variable at this index
					double[] var90 = m[cv$valuePos];
					
					// A check to ensure rounding of floating point values can never result in a negative
					// value.
					// 
					// Recorded the probability of reaching sample task 95 with the current configuration.
					// 
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					// 
					// Variable declaration of cv$accumulatedConsumerProbabilities moved.
					// Declaration comment was:
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					cv$accumulatedProbabilities = (((((((0.0 <= st[i$var87][0]) && (st[i$var87][0] < noStates)) && (0 < noStates)) && (0.0 <= var90[st[i$var87][0]])) && (var90[st[i$var87][0]] <= 1.0))?Math.log(var90[st[i$var87][0]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			else {
				for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
					// Zero all the elements in the distribution accumulator
					for(int cv$i = 0; cv$i < noStates; cv$i += 1)
						// A local array to hold the accumulated distributions of the sample tasks for each
						// configuration of distributions.
						cv$distributionAccumulator$var91[cv$i] = 0.0;
					
					// Add the current distribution to the distribution accumulator.
					// 
															// Constructing a random variable input for use later.
					// 
															// Value of the variable at this index
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var91, 1.0, m[cv$valuePos], noStates);
					
					// A local copy of the samples' distribution.
					double[] cv$sampleDistribution = distribution$sample95[i$var87];
					
					// The overlap of the distributions so far.
					double cv$overlap = 0.0;
					
					// Calculate the overlap for each element in the distribution
					for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
						// Normalise the values in the calculated distribution
						// 
						// A local array to hold the accumulated distributions of the sample tasks for each
						// configuration of distributions.
						// 
						// Variable declaration of cv$reachedDistributionProbability moved.
						// Declaration comment was:
						// Zero an accumulator to track the probabilities reached.
						// 
						// Record the reached distribution.
						// 
																		// The probability of reaching the consumer with this set of consumer arguments
						// 
						// Add the probability of this argument configuration.
						// 
						// Declare and zero an accumulator for tracking the reached source probability space.
						double cv$normalisedDistValue = cv$distributionAccumulator$var91[cv$i];
						
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
															// Variable declaration of cv$reachedDistributionProbability moved.
					// Declaration comment was:
					// Zero an accumulator to track the probabilities reached.
					// 
					// Record the reached distribution.
					// 
															// The probability of reaching the consumer with this set of consumer arguments
					// 
					// Add the probability of this argument configuration.
					// 
					// Declare and zero an accumulator for tracking the reached source probability space.
					cv$accumulatedDistributionProbabilities = (cv$accumulatedDistributionProbabilities + Math.log(cv$overlap));
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Get a local reference to the scratch space.
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$var77$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		if(constrainedFlag$sample80) {
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			// 
			// Get a local reference to the scratch space.
			double cv$lseMax = cv$var77$stateProbabilityGlobal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				// Get a local reference to the scratch space.
				double cv$lseElementValue = cv$var77$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else {
				// Initialize the sum of the array elements
				double cv$lseSum = 0.0;
				
				// Offset values, move to normal space, and sum.
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					// Get a local reference to the scratch space.
					cv$lseSum = (cv$lseSum + Math.exp((cv$var77$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					// Get a local reference to the scratch space.
					cv$var77$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
															// Get a local reference to the scratch space.
					cv$var77$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var77$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			// 
			// Get a local reference to the scratch space.
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var77$stateProbabilityGlobal.length; cv$indexName += 1)
				// Get a local reference to the scratch space.
				cv$var77$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			// 
			// Get a local reference to the scratch space.
			initialState = DistributionSampling.sampleCategorical(RNG$, cv$var77$stateProbabilityGlobal, cv$numStates);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 95 drawn from Categorical 91. Inference was performed using variable
	// marginalization.
	private final void inferSample95(int i$var87, int threadID$cv$i$var87, Rng RNG$) {
		constrainedFlag$sample95[i$var87] = false;
		
		// Variable declaration of cv$numStates moved.
		// Declaration comment was:
		// Calculate the number of states to evaluate.
		// 
		// variable marginalization
		// 
				// cv$numStates's comment
		// Calculate the number of states to evaluate.
		int cv$numStates = Math.max(0, noStates);
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var92$stateProbabilityGlobal[threadID$cv$i$var87];
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// Constructing a random variable input for use later.
			double[] var90 = m[initialState];
			
			// An accumulator to allow the value for each distribution to be constructed before
			// it is added to the index probabilities.
			// 
									// Value of the variable at this index
			double cv$accumulatedProbabilities = (((((cv$valuePos < noStates) && (0 < noStates)) && (0.0 <= var90[cv$valuePos])) && (var90[cv$valuePos] <= 1.0))?Math.log(var90[cv$valuePos]):Double.NEGATIVE_INFINITY);
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if((1 < length$eventsMeasured[i$var87])) {
				// Constraints moved from conditionals in inner loops/scopes/etc.
				if(fixedFlag$sample126) {
					// Looking for a path between Sample 95 and consumer Categorical 122.
					// Mark that the sample has observed constrained data.
					constrainedFlag$sample95[i$var87] = true;
					
					// Set an accumulator to sum the probabilities for each possible configuration of
					// inputs.
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					
					// Set an accumulator to record the consumer distributions not seen. Initially set
					// to 1 as seen values will be deducted from this value.
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					
					// Enumerating the possible arguments for the variable Categorical 122 which is consuming
					// the output of Sample task 95.
					// 
					// Value of the variable at this index
					if((cv$valuePos < noStates)) {
						// Constructing a random variable input for use later.
						// 
						// Value of the variable at this index
						double[] var121 = m[cv$valuePos];
						
																		// Substituted "i$var104" with its value "i$var87".
						cv$accumulatedConsumerProbabilities = (((((0.0 <= st[i$var87][1]) && (st[i$var87][1] < noStates)) && (0.0 <= var121[st[i$var87][1]])) && (var121[st[i$var87][1]] <= 1.0))?Math.log(var121[st[i$var87][1]]):Double.NEGATIVE_INFINITY);
						
						// Recorded the probability of reaching sample task 126 with the current configuration.
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
				else {
					// Looking for a path between Sample 95 and consumer Categorical 122.
					// Processing sample task 126 of consumer random variable null.
					// 
					// A local array to hold the accumulated distributions of the sample tasks for each
					// configuration of distributions.
					double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var122[threadID$cv$i$var87];
					
					// Zero all the elements in the distribution accumulator
					for(int cv$i = 0; cv$i < noStates; cv$i += 1)
						cv$accumulatedConsumerDistributions[cv$i] = 0.0;
					
					// Zero an accumulator to track the probabilities reached.
					double cv$reachedDistributionProbability = 0.0;
					
					// Enumerating the possible arguments for the variable Categorical 122 which is consuming
					// the output of Sample task 95.
					// 
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
																		// Constructing a random variable input for use later.
						// 
																		// Value of the variable at this index
						DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, 1.0, m[cv$valuePos], noStates);
					}
					
					// A local copy of the samples' distribution.
					// 
															// Substituted "i$var104" with its value "i$var87".
					double[] cv$sampleDistribution = distribution$sample126[i$var87][0];
					
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
			}
			
			// Save the calculated index value into the array of index value probabilities
			// 
			// Record the reached probability density.
			// 
			// Initialize a counter to track the reached distributions.
			cv$stateProbabilityLocal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		if(constrainedFlag$sample95[i$var87]) {
			// Set the calculated probabilities to be the distribution values, and normalize
			// Local copy of the probability array
			double[] cv$localProbability = distribution$sample95[i$var87];
			
			// This value is not used before it is set again, so removing the value declaration.
			// 
			// The sum of all the probabilities in log space
			double cv$logSum;
			
			// Sum all the values
			// 
			// Initialize the max to the first element.
			double cv$lseMax = cv$stateProbabilityLocal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else {
				// Initialize the sum of the array elements
				double cv$lseSum = 0.0;
				
				// Offset values, move to normal space, and sum.
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				// 
				// The sum of all the probabilities in log space
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Calculate the probability of the samples represented by sample126 using probability
	// distributions.
	private final void logProbabilityDistribution$sample126() {
		// Determine if we need to calculate the values for sample task 126 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample126) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample126) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
					for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
						// An accumulator for log probabilities.
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						
						// An accumulator for the distributed probability space covered.
						double cv$probabilityReached = 0.0;
						
						// The sample value to calculate the probability of generating
						int cv$sampleValue = st[i$var104][j$var115];
						
						// Enumerating the possible arguments for Categorical 122.
						if((1 == j$var115)) {
							// Enumerating the possible arguments for Categorical 122.
							if(fixedFlag$sample95) {
								int var41 = st[i$var104][0];
								
																								// Substituted "j$var115" with its value "1".
								if(((0 <= var41) && (var41 < noStates))) {
									// Substituted "j$var115" with its value "1".
									double[] var121 = m[st[i$var104][0]];
									
									// Store the value of the function call, so the function call is only made once.
									cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0.0 <= var121[cv$sampleValue])) && (var121[cv$sampleValue] <= 1.0))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY);
									
									// Add the probability of this distribution configuration to the accumulator.
									// 
									// An accumulator for the distributed probability space covered.
									cv$probabilityReached = 1.0;
								}
							} else {
								// Enumerating the possible outputs of Categorical 91.
								for(int index$sample95$6 = 0; index$sample95$6 < noStates; index$sample95$6 += 1) {
									// Update the probability of sampling this value from the distribution value.
									// 
									// Substituted "i$var87" with its value "i$var104".
									double cv$probabilitySample95Value7 = distribution$sample95[i$var104][index$sample95$6];
									double[] var121 = m[index$sample95$6];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(cv$probabilitySample95Value7) + (((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0.0 <= var121[cv$sampleValue])) && (var121[cv$sampleValue] <= 1.0))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
									
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
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample95Value7);
								}
							}
						}
						
						// Substituted "index$i$13_1" with its value "i$var104".
						// 
						// Substituted "index$j$13_2" with its value "(j$var115 - 1)".
						if((2 <= j$var115)) {
							int var41 = st[i$var104][(j$var115 - 1)];
							if(((0 <= var41) && (var41 < noStates))) {
								double[] var121 = m[st[i$var104][(j$var115 - 1)]];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0.0 <= var121[cv$sampleValue])) && (var121[cv$sampleValue] <= 1.0))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY);
								
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
						
						// Store the sample task probability
						logProbability$sample126[i$var104][(j$var115 - 1)] = cv$distributionAccumulator;
					}
				}
				
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample126" with its value "true".
				fixedProbFlag$sample126 = (fixedFlag$sample42 && fixedFlag$sample95);
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
					cv$accumulator = (cv$accumulator + logProbability$sample126[i$var104][(j$var115 - 1)]);
			}
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample126)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample126)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample159 using probability
	// distributions.
	private final void logProbabilityDistribution$sample159() {
		// Determine if we need to calculate the values for sample task 159 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample159) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					
					// Look for paths between the variable and the sample task 159 including any distribution
					// values.
					// 
					// The sample value to calculate the probability of generating
					int cv$sampleValue = (events[i$var136][j$var149] - 1);
					
					// Enumerating the possible arguments for Categorical 154.
					if(fixedFlag$sample126) {
						int var55 = st[i$var136][j$var149];
						if(((0 <= var55) && (var55 < noStates))) {
							double[] var153 = bias[st[i$var136][j$var149]];
							
							// Store the value of the function call, so the function call is only made once.
							cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents)) && (0 < noEvents)) && (0.0 <= var153[cv$sampleValue])) && (var153[cv$sampleValue] <= 1.0))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY);
							
							// Add the probability of this distribution configuration to the accumulator.
							// 
							// An accumulator for the distributed probability space covered.
							cv$probabilityReached = 1.0;
						}
					} else {
						// Enumerating the possible outputs of Categorical 122.
						for(int index$sample126$13 = 0; index$sample126$13 < noStates; index$sample126$13 += 1) {
							// Update the probability of sampling this value from the distribution value.
							// 
																					// Substituted "i$var104" with its value "i$var136".
							double cv$probabilitySample126Value14 = distribution$sample126[i$var136][(j$var149 - 1)][index$sample126$13];
							double[] var153 = bias[index$sample126$13];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(cv$probabilitySample126Value14) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents)) && (0 < noEvents)) && (0.0 <= var153[cv$sampleValue])) && (var153[cv$sampleValue] <= 1.0))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample126Value14);
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
					
					// Store the sample task probability
					logProbability$sample159[i$var136][(j$var149 - 1)] = cv$distributionAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$events = (logProbability$events + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample159 = ((fixedFlag$sample57 && fixedFlag$sample95) && fixedFlag$sample126);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1)
					cv$accumulator = (cv$accumulator + logProbability$sample159[i$var136][(j$var149 - 1)]);
			}
			
			// Update the variable probability
			logProbability$events = (logProbability$events + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample95 using probability
	// distributions.
	private final void logProbabilityDistribution$sample95() {
		// Determine if we need to calculate the values for sample task 95 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample95) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample95) {
				// Generating probabilities for sample task
				// Accumulator for probabilities of instances of the random variable
				double cv$accumulator = 0.0;
				for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[i$var87][0];
					double[] var90 = m[initialState];
					
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
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var90[cv$sampleValue])) && (var90[cv$sampleValue] <= 1.0))?Math.log(var90[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					// 
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					
					// Store the sample task probability
					logProbability$sample95[i$var87] = cv$distributionAccumulator;
				}
				
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				// 
				// Substituted "fixedFlag$sample95" with its value "true".
				fixedProbFlag$sample95 = (fixedFlag$sample42 && fixedFlag$sample80);
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample95[i$var87]);
			
			// Make sure all the inputs have been fixed so the variable is not a distribution.
			if(fixedFlag$sample95)
				// Update the variable probability
				logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample126 using sampled
	// values.
	private final void logProbabilityValue$sample126() {
		// Determine if we need to calculate the values for sample task 126 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample126) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = st[i$var104][j$var115];
					double[] var121 = m[st[i$var104][(j$var115 - 1)]];
					
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
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var121[cv$sampleValue])) && (var121[cv$sampleValue] <= 1.0))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					// 
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					
					// Store the sample task probability
					logProbability$sample126[i$var104][(j$var115 - 1)] = cv$distributionAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample126)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample126 = ((fixedFlag$sample126 && fixedFlag$sample42) && fixedFlag$sample95);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
					cv$accumulator = (cv$accumulator + logProbability$sample126[i$var104][(j$var115 - 1)]);
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample126)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample159 using sampled
	// values.
	private final void logProbabilityValue$sample159() {
		// Determine if we need to calculate the values for sample task 159 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample159) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
					// The sample value to calculate the probability of generating
					int cv$sampleValue = (events[i$var136][j$var149] - 1);
					double[] var153 = bias[st[i$var136][j$var149]];
					
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
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents)) && (0 < noEvents)) && (0.0 <= var153[cv$sampleValue])) && (var153[cv$sampleValue] <= 1.0))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					
					// Add the probability of this instance of the random variable to the probability
					// of all instances of the random variable.
					// 
					// Add the probability of this sample task to the sample task accumulator.
					// 
					// Accumulator for sample probabilities for a specific instance of the random variable.
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					
					// Store the sample task probability
					logProbability$sample159[i$var136][(j$var149 - 1)] = cv$distributionAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$events = (logProbability$events + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample159 = ((fixedFlag$sample57 && fixedFlag$sample95) && fixedFlag$sample126);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1)
					cv$accumulator = (cv$accumulator + logProbability$sample159[i$var136][(j$var149 - 1)]);
			}
			
			// Update the variable probability
			logProbability$events = (logProbability$events + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample42 using sampled
	// values.
	private final void logProbabilityValue$sample42() {
		// Determine if we need to calculate the values for sample task 42 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample42) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var41 = 0; var41 < noStates; var41 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var41], v, noStates));
			
			// Store the random variable instance probability
			logProbability$var42 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample42)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample42 = fixedFlag$sample42;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$m = (logProbability$m + logProbability$var42);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var42);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample42)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var42);
		}
	}

	// Calculate the probability of the samples represented by sample57 using sampled
	// values.
	private final void logProbabilityValue$sample57() {
		// Determine if we need to calculate the values for sample task 57 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample57) {
			// Generating probabilities for sample task
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var55 = 0; var55 < noStates; var55 += 1)
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
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(bias[var55], v2, noEvents));
			
			// Store the random variable instance probability
			logProbability$var56 = cv$sampleAccumulator;
			
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
			if(fixedFlag$sample57)
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Accumulator for probabilities of instances of the random variable
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample57 = fixedFlag$sample57;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Update the variable probability
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$bias = (logProbability$bias + logProbability$var56);
			
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$var56);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample57)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$var56);
		}
	}

	// Calculate the probability of the samples represented by sample78 using sampled
	// values.
	private final void logProbabilityValue$sample78() {
		// Determine if we need to calculate the values for sample task 78 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample78) {
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(weights, v, noStates);
			
			// Store the sample task probability
			logProbability$weights = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample78)
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
			fixedProbFlag$sample78 = fixedFlag$sample78;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$weights);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample78)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$weights);
		}
	}

	// Calculate the probability of the samples represented by sample80 using sampled
	// values.
	private final void logProbabilityValue$sample80() {
		// Determine if we need to calculate the values for sample task 80 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample80) {
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
			double cv$distributionAccumulator = ((((((0.0 <= initialState) && (initialState < noStates)) && (0 < noStates)) && (0.0 <= weights[initialState])) && (weights[initialState] <= 1.0))?Math.log(weights[initialState]):Double.NEGATIVE_INFINITY);
			
			// Store the sample task probability
			logProbability$initialState = cv$distributionAccumulator;
			
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
			if(fixedFlag$sample80)
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
			fixedProbFlag$sample80 = (fixedFlag$sample80 && fixedFlag$sample78);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			// Add probability to model
			// 
			// Variable declaration of cv$accumulator moved.
			logProbability$$model = (logProbability$$model + logProbability$initialState);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample80)
				// Variable declaration of cv$accumulator moved.
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialState);
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
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
				// The sample value to calculate the probability of generating
				int cv$sampleValue = st[i$var87][0];
				double[] var90 = m[initialState];
				
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
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var90[cv$sampleValue])) && (var90[cv$sampleValue] <= 1.0))?Math.log(var90[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				// 
				// Add the probability of this sample task to the sample task accumulator.
				// 
				// Accumulator for sample probabilities for a specific instance of the random variable.
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				
				// Store the sample task probability
				logProbability$sample95[i$var87] = cv$distributionAccumulator;
			}
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample95 = ((fixedFlag$sample95 && fixedFlag$sample42) && fixedFlag$sample80);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample95[i$var87]);
			
			// Update the variable probability
			logProbability$st = (logProbability$st + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocate() {
		// Constructor for v
		v = new double[noStates];
		
		// Constructor for v2
		v2 = new double[noEvents];
		
		// If m has not been set already allocate space.
		if(!fixedFlag$sample42) {
			// Constructor for m
			m = new double[noStates][];
			for(int var41 = 0; var41 < noStates; var41 += 1)
				m[var41] = new double[noStates];
		}
		
		// If bias has not been set already allocate space.
		if(!fixedFlag$sample57) {
			// Constructor for bias
			bias = new double[noStates][];
			for(int var55 = 0; var55 < noStates; var55 += 1)
				bias[var55] = new double[noEvents];
		}
		
		// If st has not been set already allocate space.
		if((!fixedFlag$sample95 || !fixedFlag$sample126)) {
			// Constructor for st
			st = new int[length$eventsMeasured.length][];
			for(int i$var69 = 0; i$var69 < length$eventsMeasured.length; i$var69 += 1)
				st[i$var69] = new int[length$eventsMeasured[i$var69]];
		}
		
		// If weights has not been set already allocate space.
		if(!fixedFlag$sample78)
			// Constructor for weights
			weights = new double[noStates];
		
		// Constructor for events
		events = new int[length$eventsMeasured.length][];
		for(int i$var136 = 0; i$var136 < length$eventsMeasured.length; i$var136 += 1)
			events[i$var136] = new int[length$eventsMeasured[i$var136]];
		
		// Constructor for distribution$sample95
		distribution$sample95 = new double[length$eventsMeasured.length][];
		for(int i$var87 = 0; i$var87 < length$eventsMeasured.length; i$var87 += 1)
			distribution$sample95[i$var87] = new double[noStates];
		
		// Constructor for distribution$sample126
		distribution$sample126 = new double[length$eventsMeasured.length][][];
		for(int i$var104 = 0; i$var104 < length$eventsMeasured.length; i$var104 += 1) {
			double[][] subarray$0 = new double[(length$eventsMeasured[i$var104] - 1)][];
			distribution$sample126[i$var104] = subarray$0;
			for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
				subarray$0[(j$var115 - 1)] = new double[noStates];
		}
		
		// Constructor for constrainedFlag$sample95
		constrainedFlag$sample95 = new boolean[length$eventsMeasured.length];
		
		// Constructor for constrainedFlag$sample126
		constrainedFlag$sample126 = new boolean[length$eventsMeasured.length][];
		for(int i$var104 = 0; i$var104 < length$eventsMeasured.length; i$var104 += 1)
			constrainedFlag$sample126[i$var104] = new boolean[(length$eventsMeasured[i$var104] - 1)];
		
		// Constructor for constrainedFlag$sample42
		constrainedFlag$sample42 = new boolean[noStates];
		
		// Constructor for constrainedFlag$sample57
		constrainedFlag$sample57 = new boolean[noStates];
		
		// Constructor for logProbability$sample95
		logProbability$sample95 = new double[length$eventsMeasured.length];
		
		// Constructor for logProbability$sample126
		logProbability$sample126 = new double[length$eventsMeasured.length][];
		for(int i$var104 = 0; i$var104 < length$eventsMeasured.length; i$var104 += 1)
			logProbability$sample126[i$var104] = new double[(length$eventsMeasured[i$var104] - 1)];
		
		// Constructor for logProbability$sample159
		logProbability$sample159 = new double[length$eventsMeasured.length][];
		for(int i$var136 = 0; i$var136 < length$eventsMeasured.length; i$var136 += 1)
			logProbability$sample159[i$var136] = new double[(length$eventsMeasured[i$var136] - 1)];
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var42$countGlobal
		{
			// Allocation of cv$var42$countGlobal for multithreaded execution
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var42$countGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var42$countGlobal[cv$index] = new double[noStates];
		}
		
		// Constructor for cv$var56$countGlobal
		{
			// Allocation of cv$var56$countGlobal for multithreaded execution
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var56$countGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var56$countGlobal[cv$index] = new double[noEvents];
		}
		
		// Constructor for cv$var75$countGlobal
		// 
		// Allocation of cv$var75$countGlobal for single threaded execution
		cv$var75$countGlobal = new double[noStates];
		
		// Constructor for cv$distributionAccumulator$var91
		// 
		// Allocation of cv$distributionAccumulator$var91 for single threaded execution
		// 
		// Variable to record the maximum value of Task Get 93. Initially set to the value
		// of putTask 43.
		cv$distributionAccumulator$var91 = new double[noStates];
		
		// Constructor for cv$var77$stateProbabilityGlobal
		// 
		// Allocation of cv$var77$stateProbabilityGlobal for single threaded execution
		cv$var77$stateProbabilityGlobal = new double[noStates];
		
		// Constructor for cv$distributionAccumulator$var122
		{
			// Allocation of cv$distributionAccumulator$var122 for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$distributionAccumulator$var122 = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				// Variable to record the maximum value of Task Get 124. Initially set to the value
				// of putTask 43.
				cv$distributionAccumulator$var122[cv$index] = new double[noStates];
		}
		
		// Constructor for cv$var92$stateProbabilityGlobal
		{
			// Allocation of cv$var92$stateProbabilityGlobal for multithreaded execution
			// 
			// Get the thread count.
			int cv$threadCount = threadCount();
			
			// Allocate an array to hold a copy per thread
			cv$var92$stateProbabilityGlobal = new double[cv$threadCount][];
			
			// Populate the array with a copy per thread
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				// Variable to record the maximum value of Task Get 93. Initially set to the value
				// of putTask 43.
				cv$var92$stateProbabilityGlobal[cv$index] = new double[noStates];
		}
		
		// Allocation of cv$var123$stateProbabilityGlobal for multithreaded execution
		// 
		// Get the thread count.
		int cv$threadCount = threadCount();
		
		// Allocate an array to hold a copy per thread
		cv$var123$stateProbabilityGlobal = new double[cv$threadCount][];
		
		// Populate the array with a copy per thread
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			// Variable to record the maximum value of Task Get 124. Initially set to the value
			// of putTask 43.
			cv$var123$stateProbabilityGlobal[cv$index] = new double[noStates];
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample42)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var41]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample57)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v2, noEvents, bias[var55]);
				}
			);

		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample95)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1)
							st[i$var87][0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState], noStates);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample126)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
							int[] var116 = st[i$var104];
							for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var104][(j$var115 - 1)]], noStates);
						}
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$index$i$var136, int forEnd$index$i$var136, int threadID$index$i$var136, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var136 = forStart$index$i$var136; index$i$var136 < forEnd$index$i$var136; index$i$var136 += 1) {
						int i$var136 = index$i$var136;
						int threadID$i$var136 = threadID$index$i$var136;
						int[] var150 = events[i$var136];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 1, length$eventsMeasured[i$var136], 1,
							(int forStart$j$var149, int forEnd$j$var149, int threadID$j$var149, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var149 = forStart$j$var149; j$var149 < forEnd$j$var149; j$var149 += 1)
										var150[j$var149] = (DistributionSampling.sampleCategorical(RNG$2, bias[st[i$var136][j$var149]], noEvents) + 1);
							}
						);
					}
			}
		);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample42)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var41]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample57)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v2, noEvents, bias[var55]);
				}
			);

		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample95)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
							// Create local copy of variable probabilities.
							double[] cv$distribution$sample95 = distribution$sample95[i$var87];
							double[] var90 = m[initialState];
							for(int index$var91 = 0; index$var91 < noStates; index$var91 += 1)
								// Save the probability of each value
								// 
								// Probability for this value
								cv$distribution$sample95[index$var91] = (((0.0 <= var90[index$var91]) && (var90[index$var91] <= 1.0))?var90[index$var91]:0.0);
						}
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample126)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
							for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
								// Create local copy of variable probabilities.
								double[] cv$distribution$sample126 = distribution$sample126[i$var104][(j$var115 - 1)];
								for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1)
									// Zero the probability of each value
									cv$distribution$sample126[index$var122] = 0.0;
								
								// Iterate through possible values for var122's arguments.
								// 
								// Enumerating the possible arguments for Categorical 122.
								if((1 == j$var115)) {
									// Iterate through possible values for var122's arguments.
									// 
									// Enumerating the possible arguments for Categorical 122.
									if(fixedFlag$sample95) {
										int var41 = st[i$var104][0];
										
																														// Substituted "j$var115" with its value "1".
										if(((0 <= var41) && (var41 < noStates))) {
											// Substituted "j$var115" with its value "1".
											double[] var121 = m[st[i$var104][0]];
											for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1)
												// Save the probability of each value
												cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] + (((0.0 <= var121[index$var122]) && (var121[index$var122] <= 1.0))?var121[index$var122]:0.0));
										}
									} else {
										// Enumerating the possible outputs of Categorical 91.
										for(int index$sample95$3 = 0; index$sample95$3 < noStates; index$sample95$3 += 1) {
											// Update the probability of sampling this value from the distribution value.
											// 
											// Substituted "i$var87" with its value "i$var104".
											double cv$probabilitySample95Value4 = distribution$sample95[i$var104][index$sample95$3];
											double[] var121 = m[index$sample95$3];
											for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1)
												// Save the probability of each value
												cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] + (cv$probabilitySample95Value4 * (((0.0 <= var121[index$var122]) && (var121[index$var122] <= 1.0))?var121[index$var122]:0.0)));
										}
									}
								}
								int index$j$11 = (j$var115 - 1);
								
								// Constraints moved from conditionals in inner loops/scopes/etc.
								// 
								// Substituted "index$i$10" with its value "i$var104".
								if((1 <= index$j$11)) {
									// Enumerating the possible outputs of Categorical 122.
									for(int index$sample126$12 = 0; index$sample126$12 < noStates; index$sample126$12 += 1) {
										// Update the probability of sampling this value from the distribution value.
										// 
										// Substituted "index$i$10" with its value "i$var104".
										double cv$probabilitySample126Value13 = distribution$sample126[i$var104][(index$j$11 - 1)][index$sample126$12];
										double[] var121 = m[index$sample126$12];
										for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1)
											// Save the probability of each value
											cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] + (cv$probabilitySample126Value13 * (((0.0 <= var121[index$var122]) && (var121[index$var122] <= 1.0))?var121[index$var122]:0.0)));
									}
								}
								
								// Sum the values in the array
								double cv$var122$sum = 0.0;
								for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1)
									// sum the probability of each value
									cv$var122$sum = (cv$var122$sum + cv$distribution$sample126[index$var122]);
								for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1)
									// Normalise the probability of each value
									cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] / cv$var122$sum);
							}
						}
				}
			);

	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample42)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var41]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample57)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v2, noEvents, bias[var55]);
				}
			);

		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample95)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1)
							st[i$var87][0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState], noStates);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample126)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
							int[] var116 = st[i$var104];
							for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var104][(j$var115 - 1)]], noStates);
						}
				}
			);

		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$index$i$var136, int forEnd$index$i$var136, int threadID$index$i$var136, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i$var136 = forStart$index$i$var136; index$i$var136 < forEnd$index$i$var136; index$i$var136 += 1) {
						int i$var136 = index$i$var136;
						int threadID$i$var136 = threadID$index$i$var136;
						int[] var150 = events[i$var136];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 1, length$eventsMeasured[i$var136], 1,
							(int forStart$j$var149, int forEnd$j$var149, int threadID$j$var149, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var149 = forStart$j$var149; j$var149 < forEnd$j$var149; j$var149 += 1)
										var150[j$var149] = (DistributionSampling.sampleCategorical(RNG$2, bias[st[i$var136][j$var149]], noEvents) + 1);
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
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample42)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var41]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample57)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v2, noEvents, bias[var55]);
				}
			);

		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample95)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1)
							st[i$var87][0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState], noStates);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample126)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
							int[] var116 = st[i$var104];
							for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var104][(j$var115 - 1)]], noStates);
						}
				}
			);

	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample42)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var41]);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample57)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v2, noEvents, bias[var55]);
				}
			);

		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample95)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1)
							st[i$var87][0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState], noStates);
				}
			);

		
		// Constraints moved from conditionals in inner loops/scopes/etc.
		if(!fixedFlag$sample126)
			//  Outer loop for dispatching multiple batches of iterations to execute in parallel
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					
						// Inner loop for running batches of iterations, each batch has its own random number
						// generator.
						for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
							int[] var116 = st[i$var104];
							for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var104][(j$var115 - 1)]], noStates);
						}
				}
			);

	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample42)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
								inferSample42(var41, threadID$var41, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample57)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
								inferSample57(var55, threadID$var55, RNG$1);
					}
				);

			if(!fixedFlag$sample78)
				inferSample78();
			if(!fixedFlag$sample80)
				inferSample80();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample95)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, samples, 1,
					(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1)
								inferSample95(i$var87, threadID$i$var87, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample126)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, samples, 1,
					(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
								for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
									inferSample126(i$var104, j$var115, threadID$i$var104, RNG$1);
							}
					}
				);

		}
		// Infer the samples in reverse chronological order.
		else {
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample126)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, samples, 1,
					(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
								for(int j$var115 = (length$eventsMeasured[i$var104] - 1); j$var115 >= 1; j$var115 -= 1)
									inferSample126(i$var104, j$var115, threadID$i$var104, RNG$1);
							}
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample95)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, samples, 1,
					(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1)
								inferSample95(i$var87, threadID$i$var87, RNG$1);
					}
				);

			if(!fixedFlag$sample80)
				inferSample80();
			if(!fixedFlag$sample78)
				inferSample78();
			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample57)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
								inferSample57(var55, threadID$var55, RNG$1);
					}
				);

			
			// Constraints moved from conditionals in inner loops/scopes/etc.
			if(!fixedFlag$sample42)
				//  Outer loop for dispatching multiple batches of iterations to execute in parallel
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
						
							// Inner loop for running batches of iterations, each batch has its own random number
							// generator.
							for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
								inferSample42(var41, threadID$var41, RNG$1);
					}
				);

		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
						if(!constrainedFlag$sample42[var41])
							drawValueSample42(var41, threadID$var41, RNG$1);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
						if(!constrainedFlag$sample57[var55])
							drawValueSample57(var55, threadID$var55, RNG$1);
					}
			}
		);
		if(!constrainedFlag$sample78)
			drawValueSample78();
		if(!constrainedFlag$sample80)
			drawValueSample80();
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
						if(!constrainedFlag$sample95[i$var87])
							drawValueSample95(i$var87, threadID$i$var87, RNG$1);
					}
			}
		);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
						for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
							if(!constrainedFlag$sample126[i$var104][(j$var115 - 1)])
								drawValueSample126(i$var104, j$var115, threadID$i$var104, RNG$1);
						}
					}
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
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample42)
			logProbability$var42 = Double.NaN;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample57)
			logProbability$var56 = Double.NaN;
		if(!fixedProbFlag$sample78)
			logProbability$weights = Double.NaN;
		if(!fixedProbFlag$sample80)
			logProbability$initialState = Double.NaN;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample95) {
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1)
				logProbability$sample95[i$var87] = Double.NaN;
		}
		if(!fixedProbFlag$sample126) {
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
					logProbability$sample126[i$var104][(j$var115 - 1)] = Double.NaN;
			}
		}
		logProbability$events = 0.0;
		if(!fixedProbFlag$sample159) {
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1)
					logProbability$sample159[i$var136][(j$var149 - 1)] = Double.NaN;
			}
		}
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeModel() {
		for(int var14 = 0; var14 < noStates; var14 += 1)
			v[var14] = 0.1;
		for(int var27 = 0; var27 < noEvents; var27 += 1)
			v2[var27] = 0.1;
		samples = length$eventsMeasured.length;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample95$1 = 0; index$constrainedFlag$sample95$1 < constrainedFlag$sample95.length; index$constrainedFlag$sample95$1 += 1)
			constrainedFlag$sample95[index$constrainedFlag$sample95$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample126$1 = 0; index$constrainedFlag$sample126$1 < constrainedFlag$sample126.length; index$constrainedFlag$sample126$1 += 1) {
			boolean[] cv$constrainedFlag$sample126$1 = constrainedFlag$sample126[index$constrainedFlag$sample126$1];
			for(int index$constrainedFlag$sample126$2 = 0; index$constrainedFlag$sample126$2 < cv$constrainedFlag$sample126$1.length; index$constrainedFlag$sample126$2 += 1)
				cv$constrainedFlag$sample126$1[index$constrainedFlag$sample126$2] = true;
		}
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample42$1 = 0; index$constrainedFlag$sample42$1 < constrainedFlag$sample42.length; index$constrainedFlag$sample42$1 += 1)
			constrainedFlag$sample42[index$constrainedFlag$sample42$1] = true;
		
		// Set all the values in the array
		for(int index$constrainedFlag$sample57$1 = 0; index$constrainedFlag$sample57$1 < constrainedFlag$sample57.length; index$constrainedFlag$sample57$1 += 1)
			constrainedFlag$sample57[index$constrainedFlag$sample57$1] = true;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(fixedFlag$sample42)
			logProbabilityValue$sample42();
		if(fixedFlag$sample57)
			logProbabilityValue$sample57();
		if(fixedFlag$sample78)
			logProbabilityValue$sample78();
		if(fixedFlag$sample80)
			logProbabilityValue$sample80();
		logProbabilityValue$sample159();
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
		logProbabilityValue$sample42();
		logProbabilityValue$sample57();
		logProbabilityValue$sample78();
		logProbabilityValue$sample80();
		logProbabilityDistribution$sample95();
		logProbabilityDistribution$sample126();
		logProbabilityDistribution$sample159();
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
		logProbabilityValue$sample42();
		logProbabilityValue$sample57();
		logProbabilityValue$sample78();
		logProbabilityValue$sample80();
		logProbabilityValue$sample95();
		logProbabilityValue$sample126();
		logProbabilityValue$sample159();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		// Propagating values back from observations into the models intermediate variables.
		// 
		// Deep copy between arrays
		int cv$length1 = events.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = eventsMeasured[cv$index1];
			int[] cv$target2 = events[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
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
		     + " * Copyright (c) 2019-2023, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model HMM_Mk2Dist(int[][] eventsMeasured, int noStates, int noEvents) {\n"
		     + "        \n"
		     + "        // Construct arrays describing the probability of a move from 1 state to another.\n"
		     + "        double[] v = new double[noStates] <~ 0.1;\n"
		     + "        double[] v2 = new double[noEvents] <~ 0.1;\n"
		     + "        double[][] m = dirichlet(v).sample(noStates);\n"
		     + "        \n"
		     + "        // Construct the bias for each webpage.\n"
		     + "        double[][] bias = dirichlet(v2).sample(noStates);\n"
		     + "\n"
		     + "        // Determine how many samples the model will need to produce.\n"
		     + "        int samples = eventsMeasured.length;\n"
		     + "        \n"
		     + "        // Allocate space for the state, i.e. which webpage we are going to trigger an event on.\n"
		     + "        int[][] st = new int[samples][];\n"
		     + "        for(int i:[0 .. samples)){\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            st[i] = new int[streamLength];\n"
		     + "        }\n"
		     + "\n"
		     + "        // Set the initial state by sampling from a categorical with learnt weightings.\n"
		     + "        double[] weights = dirichlet(v).sample();\n"
		     + "        int initialState = categorical(weights).sample();\n"
		     + "        for(int i:[0..samples)) {\n"
		     + "            st[i][0] = categorical(m[initialState]).sampleDistribution();\n"
		     + "        }\n"
		     + "\n"
		     + "        //Determine the remaining states based on the previous state.\n"
		     + "        for(int i:[0 .. samples)){\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            for(int j:[1..streamLength)){\n"
		     + "                st[i][j] = categorical(m[st[i][j-1]]).sampleDistribution();\n"
		     + "            }\n"
		     + "        }\n"
		     + "            \n"
		     + "        //Generate each event.\n"
		     + "        int[][] events = new int[samples][];\n"
		     + "        for(int i:[0 .. samples)) {\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            events[i] = new int[streamLength];\n"
		     + "            for(int j:[1..streamLength)){\n"
		     + "                events[i][j] = categorical(bias[st[i][j]]).sample() + 1;\n"
		     + "            }\n"
		     + "        }\n"
		     + "\n"
		     + "        //Tie the values of the flips to the values we have measured.\n"
		     + "        events.observe(eventsMeasured);\n"
		     + "}\n"
		     + "";
	}
}